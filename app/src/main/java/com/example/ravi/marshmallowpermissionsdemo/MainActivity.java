package com.example.ravi.marshmallowpermissionsdemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private LocationManager mLocationManager;

    String[] permissionsArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionsArray = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_CONTACTS};
    getLastBestLocation();

    }


    private void getLastBestLocation() {

      if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
              && ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
          Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
         } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)
              || ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)) {
          for (int i2 = 0; i2 <2 ; i2++) {
              AlertDialog.Builder explantDilaog = new AlertDialog.Builder(this);
              explantDilaog.setMessage("Access fine location is for getting ur current latlang");
              explantDilaog.setTitle("Please click ok to continue");
              final int finalI = i2;
              explantDilaog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      ActivityCompat.requestPermissions(MainActivity.this, new String[]{permissionsArray[finalI]}, 151);
                      Toast.makeText(MainActivity.this, "You understood the permission!", Toast.LENGTH_SHORT).show();
                  }
              });
              explantDilaog.setNegativeButton("canecl", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                  }
              });
              explantDilaog.show();

          }

         }else{
             ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
             Manifest.permission.READ_CONTACTS}, 151);
             Toast.makeText(this, "No permission", Toast.LENGTH_SHORT).show();
         }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted FINE successfully", Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(this, "Permision denied,so please give permission manually", Toast.LENGTH_SHORT).show();



        if(grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission granted READ successfully", Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(this, "Permision denied,so please give permission manually", Toast.LENGTH_SHORT).show();
    }
}
