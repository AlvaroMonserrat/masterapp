package com.newforce.myapp.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.newforce.myapp.MainActivity;
import com.newforce.myapp.R;

import java.util.Objects;

public class AuthActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AppLogger";
    private static final String ACTIVITY_TAG = AuthActivity.class.getSimpleName();

    private FirebaseAuth mAuth;

    private EditText emailText;
    private EditText passwordText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Log.i(LOG_TAG, "onCreate: " + ACTIVITY_TAG);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            Log.i(LOG_TAG, "AuthCurrentUser: " + mAuth.getCurrentUser().getEmail());
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        emailText = findViewById(R.id.edit_text_email);
        passwordText = findViewById(R.id.edit_text_password);
        progressBar = findViewById(R.id.progress_bar);
    }


    public void onClickRegister(View view){
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
        Log.i(LOG_TAG, "onClickRegister: " + ACTIVITY_TAG);
    }


    public void onClickSigIn(View view) {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(!isValidatedEmail(email) && !isValidatedPassword(password)){
            return;
        }

        if(!isValidatedEmail(email) || !isValidatedPassword(password)){
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    try{
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        emailText.setError("Correo o Contraseña Incorrecta");
                        passwordText.setError(e.getMessage());
                        emailText.requestFocus();
                        progressBar.setVisibility(View.INVISIBLE);
                    }catch (Exception e){
                        Log.e(LOG_TAG, Objects.requireNonNull(e.getMessage()));
                    }
                }else{
                    Log.i(LOG_TAG, "onCompleteSignUp: " + task.getResult());
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(AuthActivity.this, "Welcome! " , Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        });

    }

    private boolean isValidatedEmail(String email){

        if(TextUtils.isEmpty(email)){
            emailText.setError("Ingrese un Email.");
            return false;
        }

        return true;
    }

    private boolean isValidatedPassword(String password){
        if(TextUtils.isEmpty(password)){
            passwordText.setError("Ingrese una contraseña.");
            return false;
        }

        if(password.length() < 6){
            passwordText.setError("La contraseña debe tener 6 o más caracteres");
            return false;
        }
        return true;
    }
}