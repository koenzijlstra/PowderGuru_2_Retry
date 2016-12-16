package com.example.koen.powderguru2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
* Koen Zijlstra, 10741615
*
* Mainactivity, is started when user is logged in. When user is not logged in, automatically
* navigate to login screen because of authstatelistener. User can sign out (and then navigates to login),
* go to Spotsactivity or search for a city (required) and country (optional). Sends user to
* PredcitionsActivity when asynctasks is done, in the meanwhile keyboard is hidden.
*/
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    EditText userinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        // authstatelistener that starts login activity when user is not logged in
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    // logs user out
    public void signout(View view){
        auth.signOut();
    }

    // create authstatelistener
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    // remove authstatelistener
    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    // Intent that starts SpotsActivity
    public void seeSpots (View view){
        Intent Spots = new Intent(this, SpotsActivity.class);
        startActivity(Spots);

    }

    // when search button is clicked and input is empty, toast. Else hide keys and make new snowasynctasks
    // with string that user entered
    public void search(View view){
        userinput = (EditText) findViewById(R.id.userinput);
        String input = userinput.getText().toString();

        if (input.isEmpty()){
            Toast.makeText(this, "Please enter a city", Toast.LENGTH_LONG).show();
        }
        else{
            input = input.replace(" ", "+");

            // hide the keyboard (altough only for a short time, looks cleaner than keeping keyboard visible)
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            // create new Asynctasks, give context and user input as arguments
            new SnowAsynctasks(MainActivity.this, input).execute();
        }
    }
}