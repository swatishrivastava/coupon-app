package com.couponapp.login;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class LoginPresenter implements LoginContract.Presenter {

    private FirebaseAuth firebaseAuth;
    private LoginContract.View view;

    public LoginPresenter(FirebaseAuth firebaseAuthObj,
                          LoginContract.View viewObj) {
        this.firebaseAuth = firebaseAuthObj;
        this.view = viewObj;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void sighIn(String email,
                       String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            view.signInSuccessful();
                        } else {
                            System.out.print("failed reason "+ task.isSuccessful());
                            view.signInFailed();
                        }
                    }
                });

    }

    @Override
    public void signUp(String email,
                       String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            view.signInSuccessful();
                        } else {
                            view.signInFailed();
                        }
                    }
                });
    }


}
