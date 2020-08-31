package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.Map;
import com.example.myapplication.fragment.Setting;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SignIn extends AppCompatActivity {


    // private static int SPLASH_TIME_OUT = 4000;
   // private static int SPLASH_TIME_OUT = 4000;
//    RecyclerView mRecyclerView;
//    FirebaseDatabase mFirebaseDatabase;
//    DatabaseReference mRef;

    BottomNavigationView bottomNavigationView;
    private static final int MY_REQUEST_CODE = 7177;
    List<AuthUI.IdpConfig> providers;
    Button btn_sign_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Actionbar
//        ActionBar actionBar = getSupportActionBar();
        //set title
//        actionBar.setTitle("Post List");

        //RecyclerView
//        mRecyclerView = findViewById(R.id.recycle);
////        mRecyclerView.setHasFixedSize(true);
//
//        //LinearLayout
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        //send Query to firebase
//
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mRef = mFirebaseDatabase.getReference("Data");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Location Permission", "location permission before call = " + mLocationPermissionsGranted);
        mLocationPermissionsGranted = getLocationPermission();
        Log.i("Location Permission", "location permission after call = " + mLocationPermissionsGranted);

        if (mLocationPermissionsGranted) {
            Log.i("Getting location", "about to call get Device location");

            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // return;
            }
        }

        bottomNavigationView = findViewById(R.id.bottomNav);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                getDeviceLocation();

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.search:
                        fragment = new Map();
                        break;
                    case R.id.bookmark:
                        fragment = new Setting();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });


        btn_sign_out = (Button) findViewById(R.id.btn_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(SignIn.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                btn_sign_out.setEnabled(false);
                                showSignInOptions();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignIn.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        showSignInOptions();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<model, ViewHolder> firebaseRecyclerAdapter =
//                new FirebaseRecyclerAdapter<model, ViewHolder>(model.class, R.layout.row, ViewHolder.class,mRef) {
//                    @Override
//                    protected void populateViewHolder(ViewHolder viewHolder, model model, int i) {
//                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImg());
//                    }
//                };
//        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
//    }


    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.MyTheme)
                        .setLogo(R.drawable.naturecalls2)
                        .build()
                , MY_REQUEST_CODE

        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
                btn_sign_out.setEnabled(true);

            } else {
                Toast.makeText(this, "" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //map stuff


    public FusedLocationProviderClient fusedLocationClient;
    public static Location currentLocation;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;

    private void getDeviceLocation() {
        Log.d("", "getDeviceLocation: getting the devices current location");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = fusedLocationClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d("", "onComplete: found location!");
                            currentLocation = (Location) task.getResult();
                            //           Log.i("current location", "currentLocation: " + currentLocation.getLatitude() + " " + currentLocation.getLongitude());
//                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.814759, -111.784555)));
//                            googleMap.moveCamera(CameraUpdateFactory.zoomTo(18));
                            //     moveToCurrentLocation();
                            //        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
                        } else {
                            Log.d("", "onComplete: current location is null");
                            // Toast.makeText(this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("", "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }


    private boolean getLocationPermission() {
        Log.d("get", "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
                //    initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
        return false;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("", "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d("", "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d("", "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    //                initMap();
                }
            }
        }
    }



}
