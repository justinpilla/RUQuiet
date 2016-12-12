package edu.rowan.jpilla.ruquiet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SelectBuildings extends Activity {

    ListView listView;
   // ArrayList<Building> newBuildings;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_buildings);

      //  newBuildings = ;

        listView = (ListView) findViewById(R.id.listview);
        CustomAdapter adapter = new CustomAdapter(this);
        listView.setAdapter(adapter);
        checkBox = (CheckBox) findViewById(R.id.text1);


      /*  for(Building b : MainActivity.buildingList){
            int i = MainActivity.buildingList.indexOf(b);
            System.out.println(i);
            MainActivity.buildingList.get(i).setEnabled(checkBox.isEnabled());
            buildingNames.add(b.getName());
        }*/






    }

   private class CustomAdapter extends ArrayAdapter<Building>{

       Context context;


       public CustomAdapter(Context context){
           super(context, R.layout.select_buildings_list, MainActivity.buildingList);
           this.context = context;

       }


       public View getView(final int position, View convertView, ViewGroup parent){
           LayoutInflater inflater = ((Activity) context).getLayoutInflater();
           convertView = inflater.inflate(R.layout.select_buildings_list, parent, false);
           System.out.println("NAAAME " + MainActivity.buildingList.get(position).getName());
           CheckBox cb = (CheckBox) convertView.findViewById(R.id.box1);
           cb.setText(MainActivity.buildingList.get(position).getName());
           if(MainActivity.buildingList.get(position).getEnabled() == true){
               cb.setChecked(true);
           }
           else{
               cb.setChecked(false);
           }
           cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked == true){
                        MainActivity.buildingList.get(position).setEnabled(true);
                        System.out.println("CHECKED NOW");

                    }
                   else{
                        MainActivity.buildingList.get(position).setEnabled(false);
                        System.out.println("UNCHECKED NOW");

                    }
               }
           });


           return convertView;
       }


   }
}
