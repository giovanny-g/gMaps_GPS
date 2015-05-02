package com.example.giovanny.map_gps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.io.IOException;


public class map_GPS extends Activity {

    TextView tvTitle, tvLatitud, tvLongitud, tvLatitud_coordinates, tvLongitud_coordinates;
    GoogleMap googleMap;
    MapView mapView;
    LocationManager lm;
    Location location;
    double latitud, longitud;
    //int latitud, longitud;

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__gps);

       tvTitle = (TextView) findViewById(R.id.tvTitle);
       tvLatitud = (TextView) findViewById(R.id.tvLatitud);
       tvLongitud = (TextView) findViewById(R.id.tvLongitud);
       tvLatitud_coordinates = (TextView) findViewById(R.id.tvlatitud_coordinates);
       tvLongitud_coordinates = (TextView) findViewById(R.id.tvlongitud_coordinates);
       mapView = (MapView) findViewById(R.id.gmaps);

       tvTitle.setTextSize(getResources().getDimension(R.dimen.title_size));
       tvLatitud.setTextSize(getResources().getDimension(R.dimen.text_size));
       tvLongitud.setTextSize(getResources().getDimension(R.dimen.text_size));
       tvLatitud_coordinates.setTextSize(getResources().getDimension(R.dimen.text_size));
       tvLongitud_coordinates.setTextSize(getResources().getDimension(R.dimen.text_size));

       mapView.onCreate(savedInstanceState);
       googleMap = mapView.getMap();

       googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
       googleMap.setMyLocationEnabled(true);

       getLocation();

    }

    public void getLocation(){

        final LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {


                latitud = (location.getLatitude());
                longitud = (location.getLongitude());

                tvLatitud_coordinates.setText(" "+String.valueOf(latitud));//+"°");
                tvLongitud_coordinates.setText(" "+String.valueOf(longitud));//+"°");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        /*lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //lm .requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, locationListener);
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);*/

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                1, locationListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map__g, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
