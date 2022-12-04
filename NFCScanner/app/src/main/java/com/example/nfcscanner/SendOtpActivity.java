package com.example.nfcscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class SendOtpActivity extends AppCompatActivity {
    private static final int PER_LOGIN = 1000;
    EditText editEmail;
    Button buttonGetOTP;
    EditText PASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        EditText inputMobile = findViewById(R.id.inputMobile);
        Button buttonGetOTP = findViewById(R.id.buttonGetOTP);
        ProgressBar pb = findViewById(R.id.progressBar);
        String password = "SecuritySystem123";
         PASS = findViewById(R.id.inputPassword);

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.INVISIBLE);
                String tmp = PASS.getText().toString();

                if (tmp.equals(password)) {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+1" + inputMobile.getText().toString(),
                            60, //TODO: change length of time
                            TimeUnit.SECONDS,
                            SendOtpActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    pb.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    pb.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);
                                    Toast.makeText(SendOtpActivity.this, "Phone number is invalid. Please try again", Toast.LENGTH_SHORT).show();


                                }

                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    pb.setVisibility(View.GONE);
                                    buttonGetOTP.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(getApplicationContext(), VerifyOtpActivity.class);
                                    intent.putExtra("mobile", inputMobile.getText().toString());
                                    intent.putExtra("verificationId", verificationId);
                                    startActivity(intent);
                                }
                            }
                    );


                }
                else{
                    pb.setVisibility(View.GONE);
                    buttonGetOTP.setVisibility(View.VISIBLE);
                    Toast.makeText(SendOtpActivity.this, "Incorrect Supervisor Password", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}