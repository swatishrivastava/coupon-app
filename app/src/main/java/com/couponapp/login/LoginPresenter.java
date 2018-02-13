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
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            getUserInfo(user.getUid());

                        } else {
                            System.out.print("failed reason " + task.isSuccessful());
                            view.signInFailed();
                        }
                    }
                });

    }

    @Override
    public void signUp(String email,
                       String password,
                       final String name) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserPojo userPojo = saveUserInfo(task.getResult()
                                                                     .getUser(), name);
                            view.signInSuccessful(userPojo);

                        } else {
                            view.signInFailed();
                        }

                    }
                });
    }


    private UserPojo saveUserInfo(FirebaseUser user,
                                  String displayName) {
        UserPojo userDetailPojo = new UserPojo();
        userDetailPojo.setEmail(user.getEmail());
        userDetailPojo.setId(user.getUid());
        userDetailPojo.setName(displayName);
        userDetailPojo.setRole("consumer");
        DatabaseReference mDatabase = database.getReference("Users");
        String userId = mDatabase.push()
                .getKey();
        mDatabase.child(userId)
                .setValue(userDetailPojo);
        return userDetailPojo;
    }


    private void getUserInfo(String id) {
        Log.e("test", " User id == " + id);
        DatabaseReference databaseReference = database.getReference("Users");
        Query query = databaseReference.orderByChild("id")
                .equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("test", " data for user===  " + dataSnapshot);
                for (com.google.firebase.database.DataSnapshot user : dataSnapshot.getChildren()) {
                    UserPojo userPojo = new UserPojo();
                    userPojo.setRole(user.child("role")
                                             .getValue()
                                             .toString());
                    userPojo.setName(user.child("name")
                                             .getValue()
                                             .toString());
                    view.signInSuccessful(userPojo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("test", "error  data for user===  " + databaseError);

            }
        });
    }

}
