package edu.rowan.jpilla.ruquiet;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends Activity implements     GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static ArrayList<Building> buildingList;
    Button tomaps;
    Button selectBuildings;
    TextView mLatitudeText = null;
    TextView mLongitudeText = null;


    private GoogleApiClient mGoogleApiClient = null;
    //private Location mLocation = null;
    private LocationManager mLocationManager = null;
    private LocationRequest mLocationRequest = null;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mLatitudeText = (TextView) findViewById(R.id.latitudeText);
        mLongitudeText = (TextView) findViewById(R.id.longitudeText);

        if(mLatitudeText == null){
            Log.d("whynull", "TV IS NULL");
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(7000);
        mLocationRequest.setSmallestDisplacement(1);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

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
        buildingList.add(new Building("Wilson Hall", new LatLng(39.71090493948744, -75.12188047170639), new LatLng(39.71064908184027, -75.12108787894249), new LatLng(39.710743996884624, -75.1209644973278), new LatLng(39.710873989016065, -75.12095645070076), new LatLng(39.711047311476975, -75.12146070599556), new LatLng(39.71133618127746, -75.12129977345467), new LatLng(39.711185556461, -75.12074187397957), new LatLng(39.7115177559615, -75.1205675303936), new LatLng(39.71181694047791, -75.12148216366768)));
        buildingList.add(new Building("Robinson Hall", new LatLng(39.710542, -75.120683), new LatLng(39.710309, -75.119969), new LatLng(39.710623, -75.119795), new LatLng(39.710858, -75.120519)));
        buildingList.add(new Building("Science Hall", new LatLng(39.71019, -75.120958), new LatLng(39.70967, -75.121215), new LatLng(39.709369, -75.120287), new LatLng(39.709889, -75.120024), new LatLng(39.709885, -75.120362), new LatLng(39.709782, -75.120437), new LatLng(39.709893, -75.120609), new LatLng(39.709918, -75.120765), new LatLng(39.710108, -75.120663)));
        buildingList.add(new Building("James Hall", new LatLng(39.712064, -75.119936), new LatLng(39.711676, -75.120145), new LatLng(39.711338, -75.119083), new LatLng(39.711726, -75.118847)));
        buildingList.add(new Building("Rowan Hall", new LatLng(39.712551, -75.122701), new LatLng(39.712448, -75.122803), new LatLng(39.712093, -75.122412), new LatLng(39.711676, -75.122589), new LatLng(39.711618, -75.122342), new LatLng(39.712365, -75.121897), new LatLng(39.712489, -75.122267), new LatLng(39.712303, -75.122358)));
        buildingList.add(new Building("Bunce Hall", new LatLng(39.707391, -75.120778), new LatLng(39.707122,-75.120904), new LatLng(39.707203,-75.121191), new LatLng(39.707005,-75.121306), new LatLng(39.706716,-75.120427), new LatLng(39.706918,-75.120311), new LatLng(39.707013,-75.120601), new LatLng(39.707277,-75.120424)));
        buildingList.add(new Building("Bozorth Hall", new LatLng(39.708434,-75.121722), new LatLng(39.708152,-75.121856), new LatLng(39.707983,-75.122235), new LatLng(39.707983,-75.122235), new LatLng(39.707906,-75.121771), new LatLng(39.707558,-75.121588), new LatLng(39.707467,-75.12128), new LatLng(39.70781,-75.121242), new LatLng(39.707997,-75.121553), new LatLng(39.708325,-75.12132)));
        buildingList.add(new Building("Hawthorne Hall", new LatLng(39.708965,-75.121355), new LatLng(39.708903,-75.121387), new LatLng(39.708864,-75.121296), new LatLng(39.708585,-75.121419), new LatLng(39.708538,-75.121264), new LatLng(39.70888,-75.12107)));
        buildingList.add(new Building("Library", new LatLng(39.709444,-75.119142), new LatLng(39.708827,-75.119442), new LatLng(39.708693,-75.119027), new LatLng(39.709142,-75.118783), new LatLng(39.709107,-75.118592), new LatLng(39.70931,-75.118525)));
        buildingList.add(new Building("Tech Park", new LatLng(39.719927,-75.145961), new LatLng(39.719659,-75.146104), new LatLng(39.71943,-75.145366), new LatLng(39.719698,-75.145226)));

        Collections.sort(buildingList, new Comparator<Building>() {
            @Override
            public int compare(Building lhs, Building rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Log.d("abc", "def");

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            Log.d("nope", "permission denied");

        }
        Log.d("ghi", "jkl");

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        Log.d("connected", String.valueOf(mGoogleApiClient.isConnected()));

   /* if (mLocation != null) {
        tv1.setText(String.valueOf(mLocation.getLatitude()));
        tv2.setText(String.valueOf(mLocation.getLongitude()));
    } else
        Toast.makeText(this, "mLocation is null", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {

        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {

        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        Log.d("mno",String.valueOf(mGoogleApiClient.isConnected()));

        if (location != null)
        {
            mLatitudeText.setText(String.valueOf(location.getLatitude()));
            mLongitudeText.setText(String.valueOf(location.getLongitude()));
            Toast.makeText(this, location.getLatitude()+" "+location.getLongitude(), Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "location is null", Toast.LENGTH_SHORT).show();
    }


}
