package com.example.nfcscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityIpAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ip_address);

        //initialize editText and Button objects
        EditText editText = findViewById((R.id.editText));
        Button button2 = findViewById(R.id.button2);

        //on button click
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //save user inputted ipv4 Address to string 'ipAddress'
                String ipAddress = editText.getText().toString();
                //if ipv4 address is blank
                if(ipAddress.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter an IP address!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //else send ipv4 address to mainActivity and goto mainActivity
                else {
                    Intent myIntent = new Intent(MainActivityIpAddress.this, MainActivity.class);
                    myIntent.putExtra("ip_address", ipAddress); //Optional parameters
                    MainActivityIpAddress.this.startActivity(myIntent);
                }
            }
        });

    }
}