package com.example.nfcscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivityTutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutorial);

        //initialize button object and listener for button press
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //goto activity mainActivityIpAddress
                Intent myIntent = new Intent(MainActivityTutorial.this, MainActivityIpAddress.class);
                MainActivityTutorial.this.startActivity(myIntent);
            }
        });

    }
}