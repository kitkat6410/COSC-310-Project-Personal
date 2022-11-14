package com.example.nfcscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.*;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    //inti
    Tag detectedTag;
    NfcAdapter nfcAdapter;
    IntentFilter[] readTagFilters;
    PendingIntent pendingIntent;

    //
    Context context;
    TextView nfc_contents;
    Button testButton;

    //String and Int Variables to determine level of NFC Card emulated and Access level requested to enter
    String roomString, stringNFCContent, finalData, accessAttemptInfo;
    int intRoom, intNFCContent;

    @SuppressLint("UnspecifiedImmutableFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textview "textViewAccess"
        TextView textViewAccess = findViewById(R.id.textViewAccess);

        //ArrayAdapter to set spinners "SpinnerCompanyRolesAccess" and "spinnerEmulateNFCCard" to values from string.xml "companyroles"
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Spinner "spinnerCompanyRolesAccess"
        Spinner spinnerRoomAccess = findViewById(R.id.spinnerRoomAccess);
        spinnerRoomAccess.setAdapter(adapter);

        //
        nfc_contents = findViewById(R.id.nfc_contents);
        testButton = findViewById(R.id.testButton);
        context = this;

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //spinner value chosen for "access level" stored to string and then integer variable
                roomString = spinnerRoomAccess.getSelectedItem().toString();
                switch (roomString) {
                    case "FRONT DOOR":
                        intRoom = 0;
                        break;
                    case "CONFERENCE ROOM":
                        intRoom = 1;
                        break;
                    case "BATHROOM 1":
                        intRoom = 2;
                        break;
                    case "BATHROOM 2":
                        intRoom = 3;
                        break;
                    case "CEO OFFICE":
                        intRoom = 4;
                        break;
                    case "OFFICE 2":
                        intRoom = 5;
                        break;
                    case "OFFICE 1":
                        intRoom = 6;
                        break;
                    case "KITCHEN":
                        intRoom = 7;
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
                switch (intNFCContent) {
                    case 0:
                        if (intRoom ==  1) {
                            accessGranted(textViewAccess);
                            collectData(stringNFCContent, intRoom, true);
                        }
                        else {
                            accessDenied(textViewAccess);
                            collectData(stringNFCContent, intRoom, false);
                        }
                        break;

                    case 1:
                        if (intRoom == 0 || intRoom == 2 || intRoom == 3) {
                            accessGranted(textViewAccess);
                            collectData(stringNFCContent, intRoom, true);
                        }
                        else {
                            accessDenied(textViewAccess);
                            collectData(stringNFCContent, intRoom, false);
                        }
                        break;

                    case 2:
                        if (intRoom == 0 || intRoom == 2 || intRoom == 3 || intRoom == 5 || intRoom == 6 || intRoom == 7) {
                            accessGranted(textViewAccess);
                            collectData(stringNFCContent, intRoom, true);
                        }
                        else {
                            accessDenied(textViewAccess);
                            collectData(stringNFCContent, intRoom, false);
                        }
                        break;

                    case 3:
                        if (intRoom == 0 || intRoom == 2 || intRoom == 3 || intRoom == 4 || intRoom == 7) {
                            accessGranted(textViewAccess);
                            collectData(stringNFCContent, intRoom, true);
                        }
                        else {
                            accessDenied(textViewAccess);
                            collectData(stringNFCContent, intRoom, false);
                        }
                        break;
                }
            }
        });

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter filter2 = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        readTagFilters = new IntentFilter[]{tagDetected,filter2};
    }

    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        setIntent(intent);
        if (getIntent().getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            readFromTag(getIntent());
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, readTagFilters, null);
    }


    @SuppressLint("SetTextI18n")
    public void readFromTag(Intent intent){

        Ndef ndef = Ndef.get(detectedTag);

        try{
            ndef.connect();

            Parcelable[] messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (messages != null) {
                NdefMessage[] ndefMessages = new NdefMessage[messages.length];
                for (int i = 0; i < messages.length; i++) {
                    ndefMessages[i] = (NdefMessage) messages[i];
                }
                NdefRecord record = ndefMessages[0].getRecords()[0];

                byte[] payload = record.getPayload();
                stringNFCContent = new String(payload);
                stringNFCContent = stringNFCContent.substring(3);
                nfc_contents.setText("Current NFC Content: " + stringNFCContent);

                ndef.close();

            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot Read From Tag.", Toast.LENGTH_LONG).show();
            System.out.println(e);
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

    private void collectData(String stringNFCContent, int intRoom, boolean access) {
        finalData = (stringNFCContent + "," + intRoom + "," + access);
        sendData();
    }

    private void sendData() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DataOutputStream dataOutputStream;
                try(Socket socket = new Socket("10.0.0.14",4000)){
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    while(true) {
                        if(!accessAttemptInfo.equals(finalData)) {
                            dataOutputStream.writeUTF(finalData);
                        }
                        accessAttemptInfo = finalData;
                    }

                }   catch (Exception e){
                    System.out.println(e);
                }
            }
        });
    }
}