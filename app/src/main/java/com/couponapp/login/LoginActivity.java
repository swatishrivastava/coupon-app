package com.couponapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.couponapp.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import example.couponapp.com.couponapp.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        SignInFragment signInFragment = SignInFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_content_frame, signInFragment,
                         SignInFragment.class.getSimpleName())
                .addToBackStack(SignInFragment.class.getSimpleName())
                .commit();
        mAuth = FirebaseAuth.getInstance();
        new LoginPresenter(mAuth, signInFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {

        } else {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }
}
