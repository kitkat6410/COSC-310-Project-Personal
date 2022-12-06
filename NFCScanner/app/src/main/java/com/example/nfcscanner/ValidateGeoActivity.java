package com.example.nfcscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.*;
import org.json.JSONException;
import org.json.JSONObject;

public class ValidateGeoActivity extends AppCompatActivity {
    String url="https://ipgeolocation.abstractapi.com/v1/?api_key=d5ef0cf9f728469389c1fb40e5e25a7d"+
            "&fields=city,region_geoname_id,security";
    String city;
    int region;
    Boolean isVPN;
    TextView GeoMessage;
    ProgressBar pb2;
    Button Try;
    Button Continue;
    Boolean worked = false;
    String ipAddress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_geo);
        Intent intent = getIntent();
        Try = findViewById(R.id.TryButton);
        Continue = findViewById(R.id.ContinueButton);
        ipAddress = intent.getStringExtra("ip_address");
        pb2 = findViewById(R.id.pb2);
        GeoMessage = findViewById(R.id.GeoMessage);
        pb2.setVisibility(View.VISIBLE);

        makeAbstractRequest();
    }
    private void makeAbstractRequest(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Content content = Request.get(url).execute().returnContent();
                    String type = String.valueOf(content.getType());


                    JSONObject obj = new JSONObject(String.valueOf(content));
                   // System.out.println(content);
                    city = obj.getString("city");
                    region = Integer.parseInt(obj.getString("region_geoname_id"));
                    String vpn = obj.getString("security");
                    vpn = vpn.substring(10,15);
                    // vpn = "true";
                    isVPN = Boolean.valueOf(vpn);
                    if(isVPN){
                        pb2.setVisibility(View.INVISIBLE);
                        Try.setVisibility(View.INVISIBLE);
                        GeoMessage.setText("Error! Please disconnect from you VPN");

                    }
                    else if(region == 5909050){
                        pb2.setVisibility(View.INVISIBLE);
                        Continue.setVisibility(View.VISIBLE);

                        worked = true;

                        GeoMessage.setText("Welcome " + city + " User!");

                    }
                    else{
                        pb2.setVisibility(View.INVISIBLE);
                       // Try.setVisibility(View.VISIBLE);
                        GeoMessage.setText("Error! This System only works in BC, Canada");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


  public void onTry(View view){
      Intent intent = new Intent(this, ValidateGeoActivity.class);
      intent.putExtra("ipAddress", ipAddress);
      startActivity(intent);


  }
  public void onContinue(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ipAddress", ipAddress);
        startActivity(intent);
  }



}

