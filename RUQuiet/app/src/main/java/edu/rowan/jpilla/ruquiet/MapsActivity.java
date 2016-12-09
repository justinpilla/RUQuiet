package edu.rowan.jpilla.ruquiet;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

 //   Building robinsonb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

      //  robinsonb = new Building("Rob", new LatLng(39.710542, -75.120683), new LatLng(39.710309, -75.119969), new LatLng(39.710623, -75.119795), new LatLng(39.710858, -75.120519) );

      /*  try {
            System.out.println("ROB POINTS:" + robinsonb.getCoords().toString());
        }
        catch (NullPointerException e){}*/
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at Rowan and move the camera
        LatLng rowan = new LatLng(39.709802, -75.118701);
        mMap.addMarker(new MarkerOptions().position(rowan).title("Rowan University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.709802, -75.118701) , 16.0f));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(18.5f);


/*
        Polygon Robinson = mMap.addPolygon(new PolygonOptions()
                .add(robinsonb.getCoords())
                .strokeColor(Color.RED)
                .fillColor(Color.argb(100, 0, 0, 255)));
*/

 /*       Polygon Wilson = mMap.addPolygon(new PolygonOptions()
        .add(new LatLng(39.71090493948744, -75.12188047170639), new LatLng(39.71064908184027, -75.12108787894249), new LatLng(39.710743996884624, -75.1209644973278), new LatLng(39.710873989016065, -75.12095645070076), new LatLng(39.711047311476975, -75.12146070599556), new LatLng(39.71133618127746, -75.12129977345467), new LatLng(39.711185556461, -75.12074187397957), new LatLng(39.7115177559615, -75.1205675303936), new LatLng(39.71181694047791, -75.12148216366768))
        .strokeColor(Color.RED)
        .fillColor(Color.argb(100, 0, 0, 255)));

        System.out.println("POINTS" + Wilson.getPoints());*/

        for (Building b : MainActivity.buildingList){
            mMap.addPolygon(new PolygonOptions().add(b.getCoords())
                    .strokeColor(Color.RED)
                    .fillColor(Color.argb(100, 0, 0, 255)));
        }
    }
}
