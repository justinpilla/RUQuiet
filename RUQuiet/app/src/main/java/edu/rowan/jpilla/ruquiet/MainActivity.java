package edu.rowan.jpilla.ruquiet;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    Button tomaps;
    Button selectBuildings;

    static List<Building> buildingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tomaps = (Button) findViewById(R.id.tomaps);
        tomaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });

        selectBuildings = (Button) findViewById(R.id.selectBuildings);
        selectBuildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectBuildings.class);
                startActivity(intent);
            }
        });
        buildingList = new ArrayList<>();
        buildingList.add(new Building("Wilson Hall new", new LatLng(39.71090493948744, -75.12188047170639), new LatLng(39.71064908184027, -75.12108787894249), new LatLng(39.710743996884624, -75.1209644973278), new LatLng(39.710873989016065, -75.12095645070076), new LatLng(39.711047311476975, -75.12146070599556), new LatLng(39.71133618127746, -75.12129977345467), new LatLng(39.711185556461, -75.12074187397957), new LatLng(39.7115177559615, -75.1205675303936), new LatLng(39.71181694047791, -75.12148216366768)));
        buildingList.add(new Building("Robinson Hall new",new LatLng(39.710542, -75.120683), new LatLng(39.710309, -75.119969), new LatLng(39.710623, -75.119795), new LatLng(39.710858, -75.120519)));

    }

}
