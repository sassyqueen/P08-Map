package p08_map.android.myapplicationdev.com.p08_map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    private GoogleMap map;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        spn = (Spinner)findViewById(R.id.spinner);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                LatLng singapore = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        11));

                LatLng north = new LatLng(1.441073, 103.772070);

                final Marker n = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));


                LatLng central = new LatLng(1.297802, 103.847441);
                final Marker c = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                LatLng east = new LatLng(1.367149, 103.928021);
                final Marker e = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                UiSettings ui2 = map.getUiSettings();
                ui2.setZoomControlsEnabled(true);


                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null) {
                            LatLng north = new LatLng(1.441073, 103.772070);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(north,
                                    15));

                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    Toast.makeText(MainActivity.this, marker.getTitle() + " : "+ marker.getSnippet(), Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            });

                        }
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null) {
                            LatLng central = new LatLng(1.297802, 103.847441);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,
                                    15));

                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    Toast.makeText(MainActivity.this, marker.getTitle() + " : "+ marker.getSnippet(), Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            });

                        }
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null) {
                            LatLng east = new LatLng(1.367149, 103.928021);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(east,
                                    15));

                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    Toast.makeText(MainActivity.this, marker.getTitle() + " : "+ marker.getSnippet(), Toast.LENGTH_SHORT).show();
                                    return false;
                                }
                            });

                        }
                    }
                });


                //spinner
               spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       if (spn.getSelectedItemPosition() == 0){
                           btn3.performClick();
                       }
                       else if (spn.getSelectedItemPosition() == 1){
                           btn2.performClick();
                       }
                       else if (spn.getSelectedItemPosition() == 2)
                       {
                           btn1.performClick();
                       }
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> adapterView) {

                   }
               });
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION);

                    if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                        System.out.println("enabled");
                    } else {
                        Log.e("GMap - Permission", "GPS access has not been granted");
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
