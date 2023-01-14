package com.example.homelesspeopleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class ExploreToMapPage extends AppCompatActivity {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_to_map_page);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance("https://shouvo-mapapp.firebaseio.com/").getReference("Users");
        userID = firebaseUser.getUid();

        ExtendedFloatingActionButton shelterButton = findViewById(R.id.shelter);
        ExtendedFloatingActionButton foodButton = findViewById(R.id.food);
        ExtendedFloatingActionButton hospitalButton = findViewById(R.id.hospital);
        ExtendedFloatingActionButton policeStationButton = findViewById(R.id.policestation);
        ExtendedFloatingActionButton rehabButton = findViewById(R.id.rehab);
        ExtendedFloatingActionButton childCareButton = findViewById(R.id.childcare);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(this);

        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getMyLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

        shelterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if(userProfile!=null){
                            String address = userProfile.address.toLowerCase();
                            String raozan = "raozan";
                            if(address.equals(raozan)==true){
                                startActivity(new Intent(ExploreToMapPage.this,ShelterLocation.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ExploreToMapPage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if(userProfile!=null){
                            String address = userProfile.address.toLowerCase();
                            String raozan = "raozan";
                            if(address.equals(raozan)==true){
                                startActivity(new Intent(ExploreToMapPage.this,FoodLocation.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ExploreToMapPage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if(userProfile!=null){
                            String address = userProfile.address.toLowerCase();
                            String raozan = "raozan";
                            if(address.equals(raozan)==true){
                                startActivity(new Intent(ExploreToMapPage.this,HospitalLocation.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ExploreToMapPage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        policeStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if(userProfile!=null){
                            String address = userProfile.address.toLowerCase();
                            String raozan = "raozan";
                            if(address.equals(raozan)==true){
                                startActivity(new Intent(ExploreToMapPage.this,PoliceStationLocation.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ExploreToMapPage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        rehabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if(userProfile!=null){
                            String address = userProfile.address.toLowerCase();
                            String raozan = "raozan";
                            if(address.equals(raozan)==true){
                                startActivity(new Intent(ExploreToMapPage.this,RehabLocation.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ExploreToMapPage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        childCareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if(userProfile!=null){
                            String address = userProfile.address.toLowerCase();
                            String raozan = "raozan";
                            if(address.equals(raozan)==true){
                                startActivity(new Intent(ExploreToMapPage.this,ChildCareLocation.class));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ExploreToMapPage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng currloc = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(currloc)
                                    .title("I am here");
                            googleMap.addMarker(options);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currloc, 16f));

                        }
                    });
                }
            }
        });
    }
}
