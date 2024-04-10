package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.afinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {
PinView p1;
String code_by_system;
TextView resendcode;
FloatingActionButton f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);
        p1=findViewById(R.id.verification_code);
        resendcode=findViewById(R.id.resend_code);
        Intent i = getIntent();
        String phone=i.getStringExtra("phone");
        f1=findViewById(R.id.verification_code_button);
        sendToUser(phone);
        resendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Verification.this, "A code will be sent to you shortly", Toast.LENGTH_SHORT).show();
                sendToUser(phone);
            }
        });
    }

    private void sendToUser(String phone) {
       // PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,60,TimeUnit.SECONDS,
               // TaskExecutors.MAIN_THREAD,
         //   mCallBacks);
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            code_by_system=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                p1.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Verification.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(code_by_system,code);
        signInWithPhoneAuthCredential(credential);
    }


        private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
          FirebaseAuth fireBaseAuth = FirebaseAuth.getInstance();
            fireBaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Verification.this, "Verification  completed ",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Verification.this,LogingScreen.class);
                                startActivity(intent);


                                // Update UI
                            } else {
                                // Sign in failed, display a message and update the UI

                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    Toast.makeText(Verification.this, "Verification not completed try again",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }


  public void callNextScreenFromOtp(View view){
        String code = p1.getText().toString();
        if(!code.isEmpty()){
            verifyCode(code);
        }

  }
}
