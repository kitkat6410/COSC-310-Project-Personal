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

        EditText editText = findViewById((R.id.editText));
        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ipAddress = editText.getText().toString();
                if(ipAddress.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter an IP address!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Intent myIntent = new Intent(MainActivityIpAddress.this, MainActivity.class);
                    myIntent.putExtra("ip_address", ipAddress); //Optional parameters
                    MainActivityIpAddress.this.startActivity(myIntent);
                }
            }
        });

    }
}