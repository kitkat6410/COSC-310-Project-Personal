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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    //
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writingTagFilters[];

    //
    Tag myTag;
    Context context;
    TextView nfc_contents;
    Button testButton;

    //String and Int Variables to determine level of NFC Card emulated and Access level requested to enter
    String AccessLevelString, stringNFCContent;
    int intAccessLevel, intNFCContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textview "textViewAccess"
        TextView textViewAccess = findViewById(R.id.textViewAccess);

        //ArrayAdapter to set spinners "SpinnerCompanyRolesAccess" and "spinnerEmulateNFCCard" to values from string.xml "companyroles"
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.companyroles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Spinner "spinnerCompanyRolesAccess"
        Spinner spinnerCompanyRoleAccess = findViewById(R.id.spinnerCompanyRoleAccess);
        spinnerCompanyRoleAccess.setAdapter(adapter);

        //
        nfc_contents = findViewById(R.id.nfc_contents);
        testButton =  findViewById(R.id.testButton);
        context = this;

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //spinner value chosen for "access level" stored to string and then integer variable
                AccessLevelString = spinnerCompanyRoleAccess.getSelectedItem().toString();
                switch (AccessLevelString) {
                    case "CONFERENCE":
                        intAccessLevel = 0;
                        break;
                    case "GUEST":
                        intAccessLevel = 1;
                        break;
                    case "EMPLOYEE":
                        intAccessLevel = 2;
                        break;
                    case "CEO":
                        intAccessLevel = 3;
                        break;
                }

                //spinner value chosen for "emulated NFC card" stored to string and then integer variable

                switch (stringNFCContent) {
                    case "CONFERENCE":
                        intNFCContent = 0;
                        break;
                    case "GUEST":
                        intNFCContent = 1;
                        break;
                    case "EMPLOYEE":
                        intNFCContent = 2;
                        break;
                    case "CEO":
                        intNFCContent = 3;
                        break;
                }

                //if NFC card access level lower then requested access, goto function "access denied", other wise goto "access granted"
                if (intNFCContent != 0 && intAccessLevel == 0) {
                    accessDenied(textViewAccess);
                }
                else if (intNFCContent < intAccessLevel) {
                    accessDenied(textViewAccess);
                }
                else {
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
        stringNFCContent = "";
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"

        try {
            // Get the Text
            stringNFCContent = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        nfc_contents.setText("Current NFC Content: " + stringNFCContent);
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
        //on successful access attempt, change "textViewAccess" to access granted and play animation
        textViewAccess.setText("ACCESS GRANTED");

        //settings for animation
        Animation blink = new AlphaAnimation(0.f, 1.f);
        blink.setDuration(500);
        blink.setStartOffset(20);
        blink.setRepeatMode(Animation.REVERSE);
        blink.setRepeatCount(5);
        blink.setAnimationListener(new Animation.AnimationListener(){

            //play animation and with settings described below
            @Override
            public void onAnimationStart(Animation animation){
                textViewAccess.setTextColor(Color.parseColor("#0FFF00"));
            }
            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation){
                textViewAccess.setTextColor(Color.parseColor("#FFFFFF"));
                textViewAccess.setText("ACCESS PENDING");
            }
        });
        textViewAccess.startAnimation(blink);
    }

    private void accessDenied(TextView textViewAccess) {
        //on unsuccessful access attempt, change "textViewAccess" to access denied and play animation
        textViewAccess.setText("ACCESS DENIED");

        //settings for animation
        Animation blink = new AlphaAnimation(0.f, 1.f);
        blink.setDuration(500);
        blink.setStartOffset(20);
        blink.setRepeatMode(Animation.REVERSE);
        blink.setRepeatCount(5);
        blink.setAnimationListener(new Animation.AnimationListener(){

            //play animation and with settings described below
            @Override
            public void onAnimationStart(Animation animation){
                textViewAccess.setTextColor(Color.parseColor("#FF0000"));
            }
            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation){
                textViewAccess.setTextColor(Color.parseColor("#FFFFFF"));
                textViewAccess.setText("ACCESS PENDING");
            }
        });
        textViewAccess.startAnimation(blink);
    }
}