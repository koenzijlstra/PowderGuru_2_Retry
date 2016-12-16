package com.example.koen.powderguru2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/*
* Koen Zijlstra, 10741615
*
* Activity that displays the spots that the user saved (as well as the examples when user did not delete them yet).
* When checkbox is clicked, change not only view of checkbox but also boolean "checked" of city object.
* When an item is longclicked, it gets deleted from the listview and the table.
*/
public class SpotsActivity extends AppCompatActivity {

    private ListView listView;
    private DBmanager dBmanager;
    private Listadapter listadapter;
    private ArrayList<Cityobj> allcities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots);

        // get a dbmanager, get the listview with its id
        dBmanager = new DBmanager(getApplicationContext());
        listView = (ListView) findViewById(R.id.spots);

        // when clicked on an item of the listview, get that city object. get the string "city"
        // of that object and give it to new snowasynctasks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cityobj clickedspot = (Cityobj) adapterView.getItemAtPosition(position);
                String clicked = clickedspot.getText();
                new SnowAsynctasks(SpotsActivity.this, clicked).execute();
                finish();
            }
        });

        // savedinstancestate keeps checkboxes checked etc. when switched to landscape mode
        if (savedInstanceState != null) {
            allcities = savedInstanceState.getParcelableArrayList("alkey");
            listadapter = new Listadapter(getApplicationContext(), allcities);
            listView.setAdapter(listadapter);
        } else {
            // set content of the listview
            lvcontent(getApplicationContext());
        }

        // call function that deletes item when item is hold
        createlongclicklistener(listView);
    }

    // set the content of the listview
    public void lvcontent (Context context) {
        // retrieve list of all cities
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

    // set longclicklistner, delete item that was clicked from the database and from the listview
    public void createlongclicklistener(ListView view) {
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cityobj city = (Cityobj) listView.getItemAtPosition(position);
                dBmanager.delete(city.getId());
                listadapter.remove(city);
                // notify the listadapter that the dataset has been changed
                listadapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}


