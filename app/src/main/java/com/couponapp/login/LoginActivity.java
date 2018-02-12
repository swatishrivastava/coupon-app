package com.couponapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.couponapp.home.DealsHomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import example.couponapp.com.couponapp.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");
        SignInFragment signInFragment = SignInFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_content_frame, signInFragment,
                         SignInFragment.class.getSimpleName())
                .addToBackStack(SignInFragment.class.getSimpleName())
                .commit();
        mAuth = FirebaseAuth.getInstance();
        new LoginPresenter(mAuth, FirebaseDatabase.getInstance(),signInFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseMessaging.getInstance().unsubscribeFromTopic("pushNotifications");

    }
}
