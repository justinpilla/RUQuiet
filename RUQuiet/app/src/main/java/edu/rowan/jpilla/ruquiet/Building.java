package edu.rowan.jpilla.ruquiet;

import android.widget.CheckBox;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 12/8/2016.
 */

public class Building {



    String name = "Default";
    LatLng[] coords;
    Boolean enabled;


    public Building(String n, LatLng... l){

        this.name = n;
        this.coords = l;

        this.enabled = true;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng[] getCoords() {
        return coords;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
