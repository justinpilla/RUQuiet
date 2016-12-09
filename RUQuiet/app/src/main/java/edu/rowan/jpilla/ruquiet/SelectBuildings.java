package edu.rowan.jpilla.ruquiet;

import android.app.Activity;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SelectBuildings extends Activity {

    ListView listView;
    List<String> buildingNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_buildings);

        listView = (ListView) findViewById(R.id.listview);
        buildingNames = new ArrayList();

        for(Building b : MainActivity.buildingList){
            buildingNames.add(b.getName());
        }



        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.select_buildings_list, R.id.text1, buildingNames);
        listView.setAdapter(adapter);
    }
}
