package edu.rowan.jpilla.ruquiet;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 12/8/2016.
 */

public class Building {

    String name;
    List<LatLng> coords;
    Boolean enabled;

    public Building(){

        this.name = "Default";
        this.coords = new ArrayList<>();
        this.enabled = true;

    }

    public void addtoCoords(LatLng c){
        coords.add(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LatLng> getCoords() {
        return coords;
    }

    public void setCoords(List<LatLng> coords) {
        this.coords = coords;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
