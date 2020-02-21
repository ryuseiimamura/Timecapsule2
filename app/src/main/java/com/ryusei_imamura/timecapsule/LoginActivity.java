package com.ryusei_imamura.timecapsule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    private EditText mEmailField;
    private EditText mPasswdField;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = (TextView) findViewById(R.id.textView);

        mEmailField = (EditText) findViewById(R.id.emailField);
        mPasswdField = (EditText) findViewById(R.id.passwdField);

        findViewById(R.id.emailSignUpButton).setOnClickListener(this);
        findViewById(R.id.emailLoginButton).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.emailLoginButton:
                signIn(mEmailField.getText().toString(), mPasswdField.getText().toString());
                break;
            case R.id.emailSignUpButton:
                creatAccount(mEmailField.getText().toString(), mPasswdField.getText().toString());
                break;
        }
    }

    private void creatAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            changeAvtivity();
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            changeAvtivity();
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswdField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswdField.setError("Required.");
            valid = false;
        } else {
            mPasswdField.setError(null);
        }

        return valid;
    }

    private void changeAvtivity() {
        Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}