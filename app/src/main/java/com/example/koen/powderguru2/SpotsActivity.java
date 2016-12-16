package com.example.koen.powderguru2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class SpotsActivity extends AppCompatActivity {

    private ListView listView;
    private DBmanager dBmanager;
    private Listadapter listadapter;
    private ArrayList<Cityobj> allcities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots);

        // String[] spots = {"Zell am See", "Gerlos", "Moscow"};

//        // get data from firebase using user id.
//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        // if (user != null) {
//        String uid = user.getUid();

//        ArrayAdapter<String> theadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spots);
//        spotslistview.setAdapter(theadapter);
//
//        spotslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String clickedspot = String.valueOf(adapterView.getItemAtPosition(position));
//                clickedspot = clickedspot.replace(" ", "+");
//                new SnowAsynctasks(SpotsActivity.this, clickedspot).execute();
//                finish();
//            }
//        });

        dBmanager = new DBmanager(getApplicationContext());
        listView = (ListView) findViewById(R.id.spots);

        if (savedInstanceState != null) {
            allcities = savedInstanceState.getParcelableArrayList("alkey");
            listadapter = new Listadapter(getApplicationContext(), allcities);
            listView.setAdapter(listadapter);
        } else {
            // set content of the listview
            lvcontent(getApplicationContext());
        }

        // start function that deletes item when item is hold
        createlongclicklistener(listView);
    }

    public void lvcontent (Context context) {
        // retrieve list of all todos
        List cities = dBmanager.getallcities();

        // new arraylist todo_ objects
        allcities = new ArrayList<>();

        // loop over list, get objects, remember id, string and checked. add to arraylist
        for (int i = 0; i < cities.size(); i++){
            Cityobj cityobj = (Cityobj) cities.get(i);
            boolean checked = cityobj.ischecked();
            Integer id = cityobj.getId();
            String city = cityobj.getText();
            allcities.add(new Cityobj(id, city, checked));
        }

        // create a new Listadapter, set as adapter for listview
        listadapter = new Listadapter(getApplicationContext(), allcities);
        listView.setAdapter(listadapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("alkey", allcities);
    }

//
//    // closes the keyboard that showes up when/after typing. from http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
//    public void closekeys () {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//    }

    // set longclicklistner, delete item that was clicked from the database and from the listview
    public void createlongclicklistener(ListView view) {
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cityobj city = (Cityobj) listView.getItemAtPosition(position);
                dBmanager.delete(city.getId());
                listadapter.remove(city); // still unchecked call :(
                // notify the listadapter that the dataset has been changed
                listadapter.notifyDataSetChanged();
                return false;
            }
        });
    }

}


