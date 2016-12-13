package edu.rowan.jpilla.ruquiet;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SetupActivity extends Activity {

    private TextView mainText;
    private Button gps;
    private Button wifiButton;

    WifiManager wm;
    List<WifiConfiguration> configs;

    boolean wifiRowan = false;

    boolean gpsDone = false;
    boolean wiFidone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mainText = (TextView) findViewById(R.id.mainText);
        gps = (Button) findViewById(R.id.gps);
        wifiButton = (Button) findViewById(R.id.wifi);

        wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                | ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            gps.setEnabled(false);
            gpsDone = true;
        }
        if(wm.isWifiEnabled()){
            wifiButton.setEnabled(false);
            wiFidone = true;
        }


        appReady();

        wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (wm.isWifiEnabled()){
                    configs = wm.getConfiguredNetworks();





                    Log.d("TEEEST", "TEEEEEEST");

                    for(WifiConfiguration wc : configs){
                        Log.d("wifiname", "SSIDDDDDDD: " + wc.SSID);
                        if (wc.SSID.contains("Tell My WiFi I Love Her")){
                            wifiRowan = true;
                            //        Toast.makeText(SetupActivity.this, "WiFi config complete.", Toast.LENGTH_LONG).show();
                            wifiButton.setEnabled(false);
                            wifiButton.setText("WiFi config complete");
                            wiFidone = true;
                            appReady();
                        }
                    }

                    if(!wifiRowan){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SetupActivity.this, R.style.MyAlertDialogStyle);
                        builder.setTitle("WiFi Config Error");
                        builder.setMessage("Connect to RowanSecure, then try again.");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                        builder.show();
                    }


                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(SetupActivity.this, R.style.MyAlertDialogStyle);
                    builder.setTitle("WiFi Config Error");
                    builder.setMessage("Please enable WiFi, then try again.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    builder.show();
                }





                //       Log.d("WIFI", configs.toString());
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(SetupActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        gps.setEnabled(false);
                        gps.setText("Success!");
                        gpsDone = true;
                        appReady();

                    }

                }

            }
        }

    }

    public void appReady(){
        if (gpsDone && wiFidone) {
            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);

            return;
        }
    }
}
