package com.example.nfcscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hc.client5.http.fluent.*;
import org.json.JSONException;
import org.json.JSONObject;

public class ValidateGeoActivity extends AppCompatActivity {
    String url="https://ipgeolocation.abstractapi.com/v1/?api_key=d5ef0cf9f728469389c1fb40e5e25a7d"+
            "&fields=city,country";
    String city;
    String country;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_geo);
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
                    country = obj.getString("country");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //  try {

    //      Content content = Request.get(String.valueOf(url))
    //             .execute().returnContent();

    //     System.out.println(content);
    //  }
    //  catch (IOException error) { System.out.println(error); }



}

