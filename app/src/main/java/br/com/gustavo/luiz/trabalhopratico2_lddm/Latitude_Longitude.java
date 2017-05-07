package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.Manifest;

public class Latitude_Longitude extends AppCompatActivity
{
    private TextView latitudeText;               // Texto latitude
    private TextView longitudeText;              // Texto longitude
    private FloatingActionButton floatingButton; // botao para ver a latitude e longitude
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onResume( )
    {
        super.onResume();

        if(broadcastReceiver == null)
        {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                    latitudeText.setText("" + intent.getExtras().get("lat"));
                    longitudeText.setText("" + intent.getExtras().get("lon"));
                }// end onReceive( )
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }// end onResume( )

    @Override
    protected void onDestroy( )
    {
        super.onDestroy( );

        if(broadcastReceiver != null)
        {
            unregisterReceiver(broadcastReceiver);
        }// end if
    }// end onDestroy( )

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latitude_longitude);

        // definir dados
        latitudeText  = (TextView) findViewById(R.id.latitudeTexto);
        longitudeText = (TextView) findViewById(R.id.longitudeTexto);
        floatingButton = (FloatingActionButton) findViewById(R.id.fab);

        if(!runtime_permissions( ))
        {
            enable_buttons( );
        }// end if

//        /* GPS */
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location)
//            {
//                // mostrando na tela
//                latitudeText.setText("" + location.getLatitude( ));
//                longitudeText.setText("" + location.getLongitude( ));
//            }// end onLocationChanged( )
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle){}// end onStatusChanged( )
//
//            @Override
//            public void onProviderEnabled(String s){}// end onProviderEnabled( )
//
//            @Override
//            public void onProviderDisabled(String s)
//            {
//                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(i);
//            }// end onProviderDisabled( )
//        };
//
//        // configurando o botao
//        configure_button( );
    }// end onCreate( )

    private void enable_buttons( )
    {
        floatingButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                startService(i);
            }// end onClick( )
        });
    }// end enable_buttons( )

    private boolean runtime_permissions( )
    {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
           ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }// end if

        return false;
    }// end runtime_permissions( )

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                enable_buttons( );
            }
            else
            {
                runtime_permissions( );
            }// end if
        }// end if
    }// end onRequestPermissionsResult( )

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    {
//        switch(requestCode)
//        {
//            case 10:
//                configure_button();
//                break;
//            default:
//                break;
//        }
//    }// end onRequestPermissionsResult( )
//
//    void configure_button( )
//    {
//        // first check for permissions
//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//        {
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//            {
//                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}, 10);
//            }// end if
//            return;
//        }
//
//        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
//        floatingButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                //noinspection MissingPermission
//                locationManager.requestLocationUpdates("gps", 600, 0, locationListener);
//            }// end onClick( )
//        });
//    }// end configure_button( )

//    /**
//     * Metodo onResume( ).
//     */
//    @Override
//    public void onResume( )
//    {
//        super.onResume( );
//    }// end onResume( )
}// end class
