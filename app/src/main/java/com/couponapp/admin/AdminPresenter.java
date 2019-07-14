package com.couponapp.admin;

import com.couponapp.utils.DealConstants;
import com.couponapp.home.deals.DealDto;
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
    public void saveDeal(DealDto dealDto) {
        DatabaseReference mDatabase = firebaseDatabase.getReference(DealConstants.DEALS);
        String dealId = mDatabase.push()
                .getKey();
        mDatabase.child(dealId)
                .setValue(dealDto);
        view.showDealSavedOnUi();
    }

    @Override
    public void start() {

    }
}
