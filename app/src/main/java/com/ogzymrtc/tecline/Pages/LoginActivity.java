package com.ogzymrtc.tecline.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ogzymrtc.tecline.R;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    TextInputLayout emailText, pwdText;
    String email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.loginEmail);
        pwdText = findViewById(R.id.loginPassword);

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void signUpTextClick(View v){
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
    public void  loginButtonClick(View v){
        email = emailText.getEditText().getText().toString();
        pwd = pwdText.getEditText().getText().toString();
        if (email.matches("") || pwd.matches("")){
            Toast.makeText(this, "Email Or Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}