package pl.localdeals.localdealsapp;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

public class GpsTracker extends Service implements LocationListener {

    private final Context context;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    private Location location;
    protected LocationManager locationManager;

    public GpsTracker(Context parent_context){
        this.context = parent_context;
    }

    public Location getDeviceLocation() {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);
        int a = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            int i = 0;
            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000, 10, this);
                }
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                    i = 0;
                }
            }
            if (location == null) {
                if(isNetworkEnabled){
                    locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0,0, this);
                }
                if(locationManager != null){
                    location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                }
            }

        }
        if (location == null){
            location.setLatitude(0);
            location.setLongitude(0);
        }
        return location;
    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onLocationChanged(Location location) {
        getDeviceLocation();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
