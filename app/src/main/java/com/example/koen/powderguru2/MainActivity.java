package com.example.koen.powderguru2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*

 */
public class MainActivity extends AppCompatActivity {

    private Button  signOut;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    EditText userinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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

        signOut = (Button) findViewById(R.id.sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        userinput = (EditText) findViewById(R.id.userinput);

//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() { // op internet: mauthlistener = alsdfjlsdf
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    String uid = user.getUid();
//                    Log.d("geen null", uid);
//                } else {
//                    System.out.println("user is null");
//                }
//            }
//        };

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null){
//            String uid = user.getUid();
//            Log.d("geen null", uid);
//        } else {
//            System.out.println("user is null");
//        }


    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }


    public void seeSpots (View view){
        Intent Spots = new Intent(this, SpotsActivity.class);
        startActivity(Spots);

    }

    public void search(View view){
        String input = userinput.getText().toString();

        if (input.isEmpty()){
            Toast.makeText(this, "Please enter a city", Toast.LENGTH_LONG).show();
        }
        else{
            input = input.replace(" ", "+");
            new SnowAsynctasks(MainActivity.this, input).execute();

        }
    }
}