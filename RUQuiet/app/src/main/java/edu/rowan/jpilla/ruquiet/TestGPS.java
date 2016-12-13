package edu.rowan.jpilla.ruquiet;

/**
 * Created by Justin on 12/12/2016.
 */

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class TestGPS extends Activity implements     GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient mGoogleApiClient = null;
    //private Location mLocation = null;
    private LocationManager mLocationManager = null;
    private LocationRequest mLocationRequest = null;

    private TextView tv1 = null;
    private TextView tv2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        tv1 = (TextView) findViewById(R.id.latitudeText);
        tv2 = (TextView) findViewById(R.id.longitudeText);
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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Log.d("mno",String.valueOf(mGoogleApiClient.isConnected()));

        if (location != null)
        {
            tv1.setText(String.valueOf(location.getLatitude()));
            tv2.setText(String.valueOf(location.getLongitude()));
            Toast.makeText(this, location.getLatitude()+" "+location.getLongitude(), Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "location is null", Toast.LENGTH_SHORT).show();
    }
}
