package com.example.koen.powderguru2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.CompoundButton;
import java.util.List;

/*
* Koen Zijlstra, 10741615
*
* listadapter for the listview in spotsactivity. receives all city objects as argument
 */

public class Listadapter extends ArrayAdapter {

    // constructor
    public Listadapter(Context context, List allcities) {
        super(context, 0, allcities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // get city object at position
        final Cityobj city = (Cityobj) getItem(position);

        // use listitem.xml as layout for each item
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);
        }

        // find textview of item, set text (string "city")
        TextView item = (TextView) convertView.findViewById(R.id.item);
        if (city != null) {
            item.setText((CharSequence) city.getText());
        }

        // get checkbox via id, set the looks of the checkbox to its state
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.done);
        checkBox.setChecked(city.ischecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                city.setChecked(isChecked);
            }
        });

        // return the view of the row/item
        return convertView;
    }
}
