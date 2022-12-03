package com.example.nfcscanner;

import android.os.Bundle;
import android.os.StrictMode;

import java.io.IOException;
import java.net.URL;

import org.apache.hc.client5.http.fluent.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
//import org.apache.http.client.fluent.*;

import androidx.appcompat.app.AppCompatActivity;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class otpActivity extends AppCompatActivity {
    static String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

         url = "https://emailvalidation.abstractapi.com/v1/?api_key=591ec08a25fe4e29bd6368b13685fee1&email=kjbarnes6410@gmail.com";
        makeAbstractRequest();

    }
    private static void makeAbstractRequest() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

            Content content = Request.get(url).execute().returnContent();
            System.out.println(content);
        }
        catch (Exception error) { System.out.println(error); }
    }


}