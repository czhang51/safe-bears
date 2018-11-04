package com.example.czhan.safebears;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.parse.Parse;
import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    ViewPager viewPager;
    FrameLayout flContainer;
    BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    private GoogleMap mMap;

    private Location mLastKnownLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng mLastKnownLatLng;
    private boolean mRequestingLocationUpdates;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();

        flContainer = findViewById(R.id.flContainer);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.viewPager);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                viewPager.setVisibility(View.VISIBLE);
                flContainer.setVisibility(View.INVISIBLE);
                switch(item.getItemId()) {
                    case R.id.action:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.action1:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.action2:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }


        });

        SupportMapFragment map1 = new SupportMapFragment();
        TestFragment map = new TestFragment();
        TestFragment map2 = new TestFragment();

        map1.getMapAsync(this);
        mLastKnownLocation = new Location("uwu");
        mLastKnownLocation.setLatitude(-33.852);
        mLastKnownLocation.setLongitude(151.211);
        vpAdapter.addFragment(map);
        vpAdapter.addFragment(map1);
        vpAdapter.addFragment(map2);

        viewPager.setAdapter(vpAdapter);
        viewPager.setCurrentItem(1);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    mLastKnownLocation = location;
                    mLastKnownLatLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mLastKnownLatLng));
                }
            };
        };

        mLocationRequest = LocationRequest.create();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            mMap.setMyLocationEnabled(true);
        }
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("tag", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("tag", "Can't find style. Error: ", e);
        }
/*
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            mLastKnownLocation = location;
                            mLastKnownLatLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(mLastKnownLatLng));
                        }
                    }
                });

        mLastKnownLatLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mLastKnownLatLng));
*/
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback,
                    null /* Looper */);
        } else {
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    public void report (View view) {
        Intent intent = new Intent(this, EntryActivity.class);
        startActivity(intent);
    }
}
