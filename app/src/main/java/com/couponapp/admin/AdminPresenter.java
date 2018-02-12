package com.couponapp.admin;

import com.couponapp.home.deals.DealPojo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminPresenter implements AdminContract.AdminPresenter {


    AdminContract.View view;
    FirebaseDatabase firebaseDatabase;

    public AdminPresenter(AdminContract.View viewObj, FirebaseDatabase firebaseDatabaseObj){
        this.view=viewObj;
        this.firebaseDatabase=firebaseDatabaseObj;
    }

    @Override
    public void saveDeal(DealPojo dealPojo) {
        DatabaseReference mDatabase = firebaseDatabase.getReference("Deals");
        String dealId = mDatabase.push()
                .getKey();
        mDatabase.child(dealId)
                .setValue(dealPojo);
        view.showDealSavedOnUi();
    }

    @Override
    public void start() {

    }
}
