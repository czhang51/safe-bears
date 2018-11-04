package com.example.czhan.safebears;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.parse.Parse;
import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ViewPager viewPager;
    FrameLayout flContainer;
    BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    private GoogleMap mMap;
    
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
        /*
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        SupportMapFragment map = new SupportMapFragment();
        TestFragment map1 = new TestFragment();
        TestFragment map2 = new TestFragment();

        map.getMapAsync(this);
        vpAdapter.addFragment(map);
        vpAdapter.addFragment(map1);
        vpAdapter.addFragment(map2);

        viewPager.setAdapter(vpAdapter);
        viewPager.setCurrentItem(0);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
