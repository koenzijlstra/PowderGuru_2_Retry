package com.example.koen.powderguru2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
* Koen Zijlstra, 10741615
*
* Activity that shows info about (ski) weather the next day. Started when user searched for a city in
* MainActivity. User can navigate to saved spots (SpotsActivity), go back to main with device back button
* or save the city that user is currently viewing. When save is clicked, the string "city" is given
* to dbmanager, which creates a city object.
*/

public class PredictionsActivity extends AppCompatActivity {
    private DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictions);

        setinfo();
        setsnowpredictions();
    }

    // set all info except snow predictions
    public void setinfo (){

        // get data from asynctasks
        Bundle extras = getIntent().getExtras();
        String city = extras.getString("city");
        String datetomorrow = extras.getString("date");
        String temptwelve = extras.getString("temp12");
        String suntwelve = extras.getString("sun");
        String max = extras.getString("max");
        String min = extras.getString("min");

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

    }

    // set snow predictions
    public void setsnowpredictions(){
        Bundle extras = getIntent().getExtras();

        // get 3-hourly snowpredictions
        String zero = extras.getString("0");
        String three = extras.getString("3");
        String six = extras.getString("6");
        String nine = extras.getString("9");
        String twelve = extras.getString("12");
        String fifteen = extras.getString("15");
        String eighteen = extras.getString("18");
        String twentyone = extras.getString("21");

        // set snowpredictions for all 8 moments
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

    // go to SpotsActivity
    public void seeSpots (View view){
        Intent Spots = new Intent(this, SpotsActivity.class);
        startActivity(Spots);
        finish();
    }

    // add current city to sql database by giving string "city" to db manager that inserts cityobject
    // in table dbcity. toast saved + the name of the city
    public void addspot (View view){
        Bundle extras = getIntent().getExtras();
        String city = extras.getString("city");

        dBmanager = new DBmanager(getApplicationContext());
        dBmanager.insert(city);

        String toast1 = "saved ";
        String toastfull = toast1 + city;
        Toast.makeText(this, toastfull, Toast.LENGTH_SHORT).show();
    }
}
