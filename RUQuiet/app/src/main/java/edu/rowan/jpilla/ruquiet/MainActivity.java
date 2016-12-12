package edu.rowan.jpilla.ruquiet;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Button tomaps;
    Button selectBuildings;

    public static ArrayList<Building> buildingList;

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

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
        buildingList.add(new Building("Wilson Hall", new LatLng(39.71090493948744, -75.12188047170639), new LatLng(39.71064908184027, -75.12108787894249), new LatLng(39.710743996884624, -75.1209644973278), new LatLng(39.710873989016065, -75.12095645070076), new LatLng(39.711047311476975, -75.12146070599556), new LatLng(39.71133618127746, -75.12129977345467), new LatLng(39.711185556461, -75.12074187397957), new LatLng(39.7115177559615, -75.1205675303936), new LatLng(39.71181694047791, -75.12148216366768)));
        buildingList.add(new Building("Robinson Hall",new LatLng(39.710542, -75.120683), new LatLng(39.710309, -75.119969), new LatLng(39.710623, -75.119795), new LatLng(39.710858, -75.120519)));
        buildingList.add(new Building("Science Hall", new LatLng(39.71019,-75.120958), new LatLng(39.70967,-75.121215), new LatLng(39.709369,-75.120287), new LatLng(39.709889,-75.120024), new LatLng(39.709885,-75.120362), new LatLng(39.709782,-75.120437), new LatLng(39.709893,-75.120609), new LatLng(39.709918,-75.120765), new LatLng(39.710108,-75.120663)));
        buildingList.add(new Building("James Hall", new LatLng(39.712064,-75.119936), new LatLng(39.711676,-75.120145), new LatLng(39.711338,-75.119083), new LatLng(39.711726,-75.118847)));
        buildingList.add(new Building("Rowan Hall", new LatLng(39.712551,-75.122701), new LatLng(39.712448,-75.122803), new LatLng(39.712093,-75.122412), new LatLng(39.711676,-75.122589), new LatLng(39.711618,-75.122342), new LatLng(39.712365,-75.121897), new LatLng(39.712489,-75.122267), new LatLng(39.712303,-75.122358)));
        Collections.sort(buildingList, new Comparator<Building>() {
            @Override
            public int compare(Building lhs, Building rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    Location mLastLocation;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        catch (SecurityException e){Toast.makeText(this, "NO PERMISSION", Toast.LENGTH_LONG);}

        if (mLastLocation != null) {
            Toast.makeText(this, mLastLocation.getLatitude() + " " + mLastLocation.getLongitude(), Toast.LENGTH_LONG);
        }



    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
