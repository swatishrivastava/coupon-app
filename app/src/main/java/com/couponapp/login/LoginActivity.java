package com.couponapp.login;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import example.couponapp.com.couponapp.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final static String PUSH_NOTIFICATION="pushNotifications" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity_layout);
        FirebaseMessaging.getInstance().subscribeToTopic(PUSH_NOTIFICATION);
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
        FirebaseMessaging.getInstance().unsubscribeFromTopic(PUSH_NOTIFICATION);

    }


    @Override
    public void onBackPressed() {
        android.support.v4.app.Fragment f =
                getSupportFragmentManager().findFragmentById(R.id.tab_layout);

        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        if (count == 1) {
            finish();
        }

        super.onBackPressed();
    }
}
