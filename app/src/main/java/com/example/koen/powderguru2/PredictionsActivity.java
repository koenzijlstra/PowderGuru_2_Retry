package com.example.koen.powderguru2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PredictionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictions);

        // get data from asynctasks
        Bundle extras = getIntent().getExtras();
        String city = extras.getString("city");
        String datetomorrow = extras.getString("date");
        String temptwelve = extras.getString("temp12");
        String suntwelve = extras.getString("sun");
        String max = extras.getString("max");
        String min = extras.getString("min");

        // get snowpredictions
        String zero = extras.getString("0");
        String three = extras.getString("3");
        String six = extras.getString("6");
        String nine = extras.getString("9");
        String twelve = extras.getString("12");
        String fifteen = extras.getString("15");
        String eighteen = extras.getString("18");
        String twentyone = extras.getString("21");

        // set name of searched city
        TextView title = (TextView) findViewById(R.id.query);
        title.setText(city);

        // set textview that displays date of tomorrow
        TextView date = (TextView) findViewById(R.id.date);
        String weatherdate1 = "Ski weather for tomorrow (";
        String weatherdate2 = ") in";
        String fulldate = weatherdate1 + datetomorrow + weatherdate2;
        date.setText(fulldate);

        // set text of temperature at 12:00
        TextView twelvetemp = (TextView) findViewById(R.id.temp12);
        String tmp1 = "Temp (at 12:00) = ";
        String tmp2 = " °C";
        String fulltemp = tmp1 + temptwelve + tmp2;
        twelvetemp.setText(fulltemp);

        // set chance of sunshine
        String sun1 = "Chance of sunshine = ";
        String sun2 = " %";
        String fullsun = sun1 + suntwelve + sun2;
        TextView sun = (TextView) findViewById(R.id.sun);
        sun.setText(fullsun);

        // set max temp
        String max1 = "Max temp = ";
        String max2 = " °C";
        String fullmax = max1 + max + max2;
        TextView maxtemp = (TextView) findViewById(R.id.max);
        maxtemp.setText(fullmax);

        String min1 = "Min temp = ";
        String min2 = " °C";
        String fullmin = min1 + min + min2;
        TextView mintemp = (TextView) findViewById(R.id.min);
        mintemp.setText(fullmin);


        // set snowpredictions

        TextView snow0 = (TextView) findViewById(R.id.nul);
        String snow01 = "00:00 : ";
        String snow02 = "%";
        String full0 = snow01 + zero + snow02;
        snow0.setText(full0);

        TextView snow3 = (TextView) findViewById(R.id.drie);
        String snow31 = "03:00 : ";
        String snow32 = "%";
        String full3 = snow31 + three + snow32;
        snow3.setText(full3);

        TextView snow6 = (TextView) findViewById(R.id.zes);
        String snow61 = "06:00 : ";
        String snow62 = "%";
        String full6 = snow61 + six + snow62;
        snow6.setText(full6);

        TextView snow9 = (TextView) findViewById(R.id.negen);
        String snow91 = "09:00 : ";
        String snow92 = "%";
        String full9 = snow91 + nine + snow92;
        snow9.setText(full9);

        TextView snow12 = (TextView) findViewById(R.id.twaalf);
        String snow121 = "12:00 : ";
        String snow122 = "%";
        String full12 = snow121 + twelve + snow122;
        snow12.setText(full12);

        TextView snow15 = (TextView) findViewById(R.id.vijftien);
        String snow151 = "15:00 : ";
        String snow152 = "%";
        String full15 = snow151 + fifteen + snow152;
        snow15.setText(full15);

        TextView snow18 = (TextView) findViewById(R.id.achtien);
        String snow181 = "18:00 : ";
        String snow182 = "%";
        String full18 = snow181 + eighteen + snow182;
        snow18.setText(full18);

        TextView snow21 = (TextView) findViewById(R.id.twentyone);
        String snow211 = "21:00 : ";
        String snow212 = "%";
        String full21 = snow211 + twentyone + snow212;
        snow21.setText(full21);

    }

    public void seeSpots (View view){
//        Bundle extras = getIntent().getExtras();
//        String city = extras.getString("city");
        Intent Spots = new Intent(this, SpotsActivity.class);
//        Spots.putExtra("city", city);
        startActivity(Spots);
        finish();
    }

    public void dbtest (View view){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Bundle extras = getIntent().getExtras();
        String city = extras.getString("city");

        // Cityobj saved = new Cityobj(city);

        mDatabase.child("users").child(uid).child("cities").push().setValue(city);
    }

//
//        Bundle extras = getIntent().getExtras();
//        String city = extras.getString("city");
//        City_object city_object = new City_object(city);
//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        // if (user != null) {
//        assert user != null;
//        String uid = user.getUid();
//        DatabaseReference users = mDatabase.child("users");
//
//        mDatabase.child("users").child(uid).setValue(city_object);
//
//        // succesvol, toast
//        //Toast.makeText(this, "jasss", Toast.LENGTH_LONG).show();
//        // geen succes, toast
//    }


}
