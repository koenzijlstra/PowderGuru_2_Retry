package com.example.koen.powderguru2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/*
* Koen Zijlstra, 10741615
*
* In this activity the user can log in. When user is already logged in, go to main activity.
* when something goes wrong with logging in, toast to user what went wrong. User can navigate to
* sign up activity when user is not registered yet.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // when user is logged in already, go to mainactivity
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        // get both edittext fields
        inputEmail = (EditText) findViewById(R.id.emaillogin);
        inputPassword = (EditText) findViewById(R.id.passwordlogin);

        // get email and password strings
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        // toast when email or password is empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter an email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter a password!", Toast.LENGTH_SHORT).show();
            return;
        }

        // authenticate user, function signinwithemailandpassword is given by firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If sign in fails, display a message to the user
                    if (!task.isSuccessful()) {
                        // when entered password is too small, prompt user for longer password
                        if (password.length() < 6) {
                            inputPassword.setError("Password too short, should be least 6 characters!");
                            // when auth failed but password is long enough, toast that email or password is wrong
                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    // If sign in succeeds the auth state listener will be notified and logic to
                    // handle the signed in user can be handled in the listener.
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
    }

    // when button "not registered yet? "is clicked, go to sign up activity
    public void gotoregister(View view){
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }
}