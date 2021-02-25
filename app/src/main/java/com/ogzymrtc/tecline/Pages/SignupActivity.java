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
import com.ogzymrtc.tecline.R;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    TextInputLayout emailText, pwdText, confirmPwdText;
    String email, pwd, confirmPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.signupEmail);
        pwdText = findViewById(R.id.signupPwd);
        confirmPwdText = findViewById(R.id.signupConfirmPwd);

    }
    public void signUpButtonClick(View v){
        email = emailText.getEditText().getText().toString();
        pwd = pwdText.getEditText().getText().toString();
        confirmPwd = confirmPwdText.getEditText().getText().toString();
        if (email.matches("") || pwd.matches("") || confirmPwd.matches("")){
            Toast.makeText(this, "Email Or Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            if (pwd.equals(confirmPwd)){
                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(SignupActivity.this, FeedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                Toast.makeText(this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
            }
        }

    }
}