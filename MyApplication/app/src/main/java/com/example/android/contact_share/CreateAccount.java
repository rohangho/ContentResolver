package com.example.android.contact_share;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class CreateAccount extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";

    EditText number;

    Button submit;
    private String phoneVerificationID;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallback;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private FirebaseAuth fbAuth;
    EditText code;
    Button sign_In;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        number=(EditText)findViewById(R.id.number);

        submit=(Button)findViewById(R.id.submit);
        code=(EditText)findViewById(R.id.code);
        sign_In=(Button)findViewById(R.id.sign_in);


        fbAuth=FirebaseAuth.getInstance();

    }


    public void SUBMIT_DETAIL(View v)
    {
        String phone_number=number.getText().toString();
        setUpVerificationCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone_number,60, TimeUnit.SECONDS,this,verificationCallback
        );

    }

    private void setUpVerificationCallbacks() {

        verificationCallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            @Override
            public void onVerificationCompleted(PhoneAuthCredential Credential)
            {
                signInWithPhoneAuthCredential(Credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if(e instanceof FirebaseAuthInvalidCredentialsException)
                Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_LONG).show();
                else {
                    Log.e(TAG, "onVerificationFailed: ", e);
                    Toast.makeText(getApplicationContext(), "Text quota full ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCodeSent(String verificationId,PhoneAuthProvider.ForceResendingToken token)
            {
                phoneVerificationID=verificationId;

            }

        };



    }

    public void verifyCode(View view)
    {
        String code_Entered = code.getText().toString();
        Log.e("i am the code",code_Entered);
        PhoneAuthCredential crediential=PhoneAuthProvider.getCredential(phoneVerificationID,code_Entered);
        signInWithPhoneAuthCredential(crediential);
        Toast.makeText(this, "Verifying...", Toast.LENGTH_SHORT).show();

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential crediential) {
        fbAuth.signInWithCredential(crediential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                        }
                        else{
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            //Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
