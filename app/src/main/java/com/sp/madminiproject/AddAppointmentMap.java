package com.sp.madminiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class AddAppointmentMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mymap;
    private SearchView mapSearch;
    private Marker dynamicMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment_map);
        setTitle("Map");

        mapSearch = findViewById(R.id.searchView);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = mapSearch.getQuery().toString();
                List<Address> addressList = null;

                if (location != null) {
                    Geocoder geocoder = new Geocoder(AddAppointmentMap.this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        // Remove existing dynamic marker
                        if (dynamicMarker != null) {
                            dynamicMarker.remove();
                        }

                        // Add new dynamic marker
                        dynamicMarker = mymap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mymap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(AddAppointmentMap.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mymap = googleMap;

        Marker nhgpMarker = addStaticMarker("NHGP Yishun Polyclinic", 1.430425, 103.839118);
        Marker sembawangMarker = addStaticMarker("Sembawang Polyclinic", 1.448275, 103.822629);

        // Set up marker click listener
        mymap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Handle marker click event
                if (marker.equals(nhgpMarker)) {
                    // Action for NHGP marker click
                    Toast.makeText(AddAppointmentMap.this, "NHGP Yishun Polyclinic Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddAppointmentMap.this, YishunPolyclinic.class);
                    startActivity(intent);
                } else if (marker.equals(sembawangMarker)) {
                    // Action for Sembawang marker click
                    Toast.makeText(AddAppointmentMap.this, "Sembawang Polyclinic Clicked", Toast.LENGTH_SHORT).show();
                }

                // Return true to consume the event (prevents default behavior)
                return true;
            }
        });

    }

    private Marker addStaticMarker(String title, double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        return mymap.addMarker(new MarkerOptions().position(latLng).title(title));
    }
}
