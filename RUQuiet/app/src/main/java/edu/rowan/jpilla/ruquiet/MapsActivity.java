package edu.rowan.jpilla.ruquiet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.709802, -75.118701), 16.0f));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(18.5f);



        // Draw overlay on each building to show what the GPS accounts for.
        // GREEN = enabled
        // RED = disabled

        for (Building b : MainActivity.buildingList) {

            Polygon p;


            if (b.getEnabled() == true) {
                p = mMap.addPolygon(new PolygonOptions().add(b.getCoords())
                        .strokeColor(Color.GREEN)
                        .fillColor(Color.argb(100, 0, 0, 255)));
            } else {
                p = mMap.addPolygon(new PolygonOptions().add(b.getCoords())
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(100, 0, 0, 255)));
            }




        }
    }
}
