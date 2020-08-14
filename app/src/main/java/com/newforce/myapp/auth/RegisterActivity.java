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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.newforce.myapp.R;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AppLogger";
    private static final String ACTIVITY_TAG = RegisterActivity.class.getSimpleName();

    private FirebaseAuth mAuth;

    private EditText emailText;
    private EditText passwordText;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.label_register);
        setContentView(R.layout.activity_register);

        Log.i(LOG_TAG, "onCreate: " + ACTIVITY_TAG);
        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.edit_text_email);
        passwordText = findViewById(R.id.edit_text_password);
        progressBar = findViewById(R.id.progress_bar);
    }

    public void onClickLogin(View view){
        Intent loginIntent = new Intent(this, AuthActivity.class);
        startActivity(loginIntent);
        Log.i(LOG_TAG, "onClickRegister: " + ACTIVITY_TAG);
        finish();
    }

    public void onClickSignUp(View view) {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(!isValidatedEmail(email) && !isValidatedPassword(password)){
            return;
        }

        if(!isValidatedEmail(email) || !isValidatedPassword(password)){
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //
                if(!task.isSuccessful()){
                    try{
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthUserCollisionException e){
                        emailText.setError("El correo ya existe!");
                        emailText.requestFocus();
                        progressBar.setVisibility(View.INVISIBLE);
                    }catch (Exception e){
                        Log.e(LOG_TAG, Objects.requireNonNull(e.getMessage()));
                    }
                }else{
                    Log.i(LOG_TAG, "onCompleteSignUp: " + task.getResult());
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(RegisterActivity.this, "Se ha Registrado con exito!", Toast.LENGTH_LONG).show();
                }
            }
        });

            /*
                if(!task.isSuccessful()) {
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e) {
                        mTxtPassword.setError(getString(R.string.error_weak_password));
                        mTxtPassword.requestFocus();
                    } catch(FirebaseAuthInvalidCredentialsException e) {
                        mTxtEmail.setError(getString(R.string.error_invalid_email));
                        mTxtEmail.requestFocus();
                    } catch(FirebaseAuthUserCollisionException e) {
                        mTxtEmail.setError(getString(R.string.error_user_exists));
                        mTxtEmail.requestFocus();
                    } catch(Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
             */
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
