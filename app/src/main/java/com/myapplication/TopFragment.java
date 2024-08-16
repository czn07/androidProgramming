package com.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopFragment extends Fragment {
    EditText email, password;
    Button login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login,null);
        email = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String emailValue = email.getText().toString().trim();

                if (getActivity() instanceof FragmentExampleActivity) {
                    ((FragmentExampleActivity) getActivity()).setBottomFragmentTitle(emailValue);
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailValue = email.getText().toString().trim();

                if (getActivity() instanceof FragmentExampleActivity) {
                    ((FragmentExampleActivity) getActivity()).setBottomFragmentTitle(emailValue);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
