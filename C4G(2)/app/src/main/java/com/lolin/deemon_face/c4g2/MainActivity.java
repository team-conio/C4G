package com.lolin.deemon_face.c4g2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    SignInButton googleBtn;
    FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private final static int RC_SIGN_IN = 2;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onStart() {
        super.onStart ();
        mAuth.addAuthStateListener (mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance ();
        googleBtn = findViewById (R.id.main_Btn_googleSignIn);

        googleBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mProgressDialog.setMessage ("Logging in please wait!");
                mProgressDialog.show ();
                signIn ();

                mProgressDialog.dismiss ();

            }
        });
        mAuthListner = new FirebaseAuth.AuthStateListener () {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser () != null){
                    startActivity (new Intent (MainActivity.this,SecondActivity.class));
                }
            }
        };

    }
}
