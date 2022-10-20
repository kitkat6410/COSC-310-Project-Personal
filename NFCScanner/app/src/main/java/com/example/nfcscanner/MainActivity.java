package com.example.nfcscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writingTagFilters[];

    Tag myTag;
    Context context;
    TextView nfc_contents;
    Button testButton;

    String testAccessLevelString, testNFCCardString;
    int testAccessLevelInteger, testNFCCardInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewAccess = findViewById(R.id.textViewAccess);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.companyroles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerCompanyRoleAccess = findViewById(R.id.spinnerCompanyRoleAccess);
        spinnerCompanyRoleAccess.setAdapter(adapter);

        Spinner spinnerEmulateNFCCard = findViewById(R.id.spinnerEmulateNFCCard);
        spinnerEmulateNFCCard.setAdapter(adapter);

        nfc_contents = (TextView) findViewById(R.id.nfc_contents);
        testButton =  findViewById(R.id.testButton);
        context = this;

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testAccessLevelString = spinnerCompanyRoleAccess.getSelectedItem().toString();
                if (testAccessLevelString.equals("GUEST")) {
                    testAccessLevelInteger = 0;
                }
                else if (testAccessLevelString.equals("EMPLOYEE")) {
                    testAccessLevelInteger = 1;
                }
                else if (testAccessLevelString.equals("CEO")) {
                    testAccessLevelInteger = 2;
                }

                testNFCCardString = spinnerEmulateNFCCard.getSelectedItem().toString();
                if (testNFCCardString.equals("GUEST")) {
                    testNFCCardInteger = 0;
                }
                else if (testNFCCardString.equals("EMPLOYEE")) {
                    testNFCCardInteger = 1;
                }
                else if (testNFCCardString.equals("CEO")) {
                    testNFCCardInteger = 2;
                }

                if (testNFCCardInteger < testAccessLevelInteger) {
                    accessDenied(textViewAccess);
                }
                else if (testNFCCardInteger >= testAccessLevelInteger) {
                    accessGranted(textViewAccess);
                }
            }
        });

        //TODO: Is this really necessary? If you're trying to use a NFC app on a non NFC compatible device then... :/
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null){
            Toast.makeText(this, "This device does not support NFC", Toast.LENGTH_SHORT).show();
            finish();
        }

        //TODO: Determine if better solution for automatically detecting NFC card is available other then idle intent

        readFromIntent(getIntent());
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writingTagFilters = new IntentFilter[] { tagDetected };
    }

    private void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    private void buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;
        String text = "";
        // String tagId = new String(msgs[0].getRecords()[0].getType());
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"
        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");

        try {
            // Get the Text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        nfc_contents.setText("Current NFC Content: " + text);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        readFromIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    private void accessGranted(TextView textViewAccess) {
        //TODO: add additional function of having access text 'flashing' and return to "ACCESS PENDING" before function returns
        textViewAccess.setText("ACCESS GRANTED");

        for(int i = 0; i < 5; i++) {
            textViewAccess.setTextColor(Color.parseColor("#0FFF00"));
            //Thread.sleep(1000);
            //textViewAccess.setTextColor(Color.parseColor("#FFFFFF"));
        }
       //textViewAccess.setText("ACCESS PENDING");
    }

    private void accessDenied(TextView textViewAccess) {
        //TODO: add additional function of having access text 'flashing' and return to "ACCESS PENDING" before function returns
        textViewAccess.setText("ACCESS DENIED");

        for(int i = 0; i < 5; i++) {

            textViewAccess.setTextColor(Color.parseColor("#FF0000"));
            //Thread.sleep(1000);
            //textViewAccess.setTextColor(Color.parseColor("#FFFFFF"));
        }
        //textViewAccess.setText("ACCESS PENDING");
    }
}