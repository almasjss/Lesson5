package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    private EditText editTextName;
    private TextView textViewWelcome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewWelcome = view.findViewById(R.id.text_view_welcome);
        editTextName = view.findViewById(R.id.edit_text_name);
        Button buttonSave = view.findViewById(R.id.button_save);

        String welcomeMessage = getArguments() != null ? getArguments().getString("welcome_message") : "";
        textViewWelcome.setText(welcomeMessage);

        loadAndDisplaySavedName();

        buttonSave.setOnClickListener(v -> {
            saveName();
            loadAndDisplaySavedName();
        });

        return view;
    }

    private void saveName() {
        String name = editTextName.getText().toString();
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name", name);
        editor.apply();
    }

    private void loadAndDisplaySavedName() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("user_name", "");
        textViewWelcome.setText("Добро пожаловать, " + savedName + "!");
        editTextName.setText(savedName);
    }
}
