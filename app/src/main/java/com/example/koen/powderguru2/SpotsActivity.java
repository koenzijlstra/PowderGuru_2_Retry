package com.example.koen.powderguru2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class SpotsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots);

        Bundle extras = getIntent().getExtras();
        // String city = extras.getString("city");
        // Toast.makeText(this, city, Toast.LENGTH_LONG).show();

        String[] spots = {"Zell am See", "Gerlos", "Moscow"};

//        // get data from firebase using user id.
//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        // if (user != null) {
//        String uid = user.getUid();

        ArrayAdapter<String> theadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spots);
        ListView spotslistview = (ListView) findViewById(R.id.spots);
        spotslistview.setAdapter(theadapter);

        spotslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String clickedspot = String.valueOf(adapterView.getItemAtPosition(position));
                clickedspot = clickedspot.replace(" ", "+");
                new SnowAsynctasks(SpotsActivity.this, clickedspot).execute();
                finish();
            }
        });
    }
}


