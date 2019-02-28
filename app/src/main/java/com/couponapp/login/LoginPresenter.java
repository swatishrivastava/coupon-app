package com.couponapp.login;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPresenter implements LoginContract.Presenter {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private LoginContract.View view;

    public LoginPresenter(FirebaseAuth firebaseAuthObj,
                          FirebaseDatabase databaseObj,
                          LoginContract.View viewObj) {
        this.firebaseAuth = firebaseAuthObj;
        this.view = viewObj;
        this.database = databaseObj;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            getUserInfo(currentUser.getUid());
        }
    }


    @Override
    public void sighIn(String email,
                       String password) {
        if (isCredentialEmpty(email, password)) {
            view.showToastForInCorrectCredentials();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Log.e(LoginPresenter.class.getName(), "sign in successful");
                                getUserInfo(user.getUid());

                            } else {
                                Log.e(LoginPresenter.class.getName(), "sign in failed");
                                view.signInFailed();
                            }
                        }


                    });
        }

    }

    @Override
    public void signUp(String email,
                       String password,
                       final String name) {
        if (isCredentialEmpty(email, password)) {
            view.showToastForInCorrectCredentials();
            return;
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                UserInfo userInfo = saveUserInfo(task.getResult()
                                                                         .getUser(), name);
                                view.signInSuccessful(userInfo);

                            } else {
                                view.signInFailed();
                            }

                        }
                    });
        }
    }


    private UserInfo saveUserInfo(FirebaseUser user,
                                  String displayName) {
        UserInfo userDetailPojo = new UserInfo();
        userDetailPojo.setEmail(user.getEmail());
        userDetailPojo.setId(user.getUid());
        userDetailPojo.setName(displayName);
        userDetailPojo.setRole(LoginConstants.CONSUMER);
        DatabaseReference mDatabase = database.getReference(LoginConstants.USERS_TABLE);
        String userId = mDatabase.push()
                .getKey();
        mDatabase.child(userId)
                .setValue(userDetailPojo);
        return userDetailPojo;
    }


    private void getUserInfo(String id) {
        DatabaseReference databaseReference = database.getReference(LoginConstants.USERS_TABLE);
        Query query = databaseReference.orderByChild(LoginConstants.USER_ID)
                .equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot user : dataSnapshot.getChildren()) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setRole(user.child(LoginConstants.USER_ROLE)
                                             .getValue()
                                             .toString());
                    userInfo.setName(user.child(LoginConstants.USER_NAME)
                                             .getValue()
                                             .toString());
                    view.signInSuccessful(userInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(LoginPresenter.class.getSimpleName(),
                      "error  data for user===  " + databaseError);
                view.signInFailed();
            }
        });
    }


    private boolean isCredentialEmpty(String email,
                                      String password) {
        return email== null || email.equalsIgnoreCase("") && password== null || password.equalsIgnoreCase("") ? true : false;
    }

}
