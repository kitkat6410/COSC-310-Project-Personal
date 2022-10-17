package com.example.nfcscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    String errorDetected = "No NFC Tag Detected";
    String readSuccess = "Tag Read Successfully!";
    String readError = "Tag Read Error!";

    String securityLevel;
    boolean NFCToggle;

    //NfcAdapter nfcAdapter;
    //PendingIntent pendingIntent;
    //IntentFilter writingTagFilters;
    //Tag myTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.securitylevels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        TextView textViewNFCInfo = findViewById(R.id.textViewNFCInfo);
        TextView textViewAccess = findViewById(R.id.textViewAccess);

        Spinner spinnerLevel = findViewById(R.id.spinnerLevel);
        spinnerLevel.setAdapter(adapter);
        spinnerLevel.setOnItemSelectedListener(this);

        Button buttonNFCToggle = findViewById(R.id.buttonNFCToggle);
        Button buttonScan = findViewById(R.id.buttonScan);

        buttonNFCToggle.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if(!NFCToggle) {
                    NFCToggle = true;
                    buttonNFCToggle.setText("Disable NFC");
                }
                else {
                    NFCToggle = false;
                    buttonNFCToggle.setText("Enable NFC");
                }



            }
        });

        buttonScan.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if(!NFCToggle) {
                    //TODO: toast message saying nfc disabled
                }
                else {
                    //TODO: functionality
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        securityLevel = adapterView.getSelectedItem().toString();

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }
}
