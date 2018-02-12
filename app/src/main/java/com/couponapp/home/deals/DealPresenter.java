package com.couponapp.home.deals;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DealPresenter implements DealContract.Presenter {
    FirebaseDatabase firebaseDatabase;
    DealContract.View view;

    public DealPresenter(FirebaseDatabase firebaseDatabase,
                         DealContract.View viewObj) {
        this.firebaseDatabase = firebaseDatabase;
        this.view = viewObj;
        this.view.setPresenter(this);
    }

    @Override
    public void fetchAllDeals() {
        final List<DealPojo> dealPojoList = new ArrayList<>();
        DatabaseReference myRef = firebaseDatabase.getReference("Deals");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("test", "data from firebase" + dataSnapshot);
                for (com.google.firebase.database.DataSnapshot deal : dataSnapshot.getChildren()) {
                    DealPojo dealPojo = getDealPojoWithData(deal);
                    dealPojoList.add(dealPojo);
                }
                view.showAllDeals(dealPojoList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException());
            }
        });
    }



    @Override
    public void fetchAllDealsByCategory(String categoryName) {
        final List<DealPojo> dealPojoList = new ArrayList<>();

        DatabaseReference myRef = firebaseDatabase.getReference("Deals");
        Query query=myRef.orderByChild("category").equalTo(categoryName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("test", "Deals from firebase by category== " + dataSnapshot);
                for (com.google.firebase.database.DataSnapshot deal : dataSnapshot.getChildren()) {
                    DealPojo dealPojo = getDealPojoWithData(deal);
                    dealPojoList.add(dealPojo);
                }
                view.showAllDeals(dealPojoList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        view.showAllDeals(dealPojoList);
    }

    private DealPojo getDealPojoWithData(com.google.firebase.database.DataSnapshot deal) {
        DealPojo dealPojo = new DealPojo();
        dealPojo.setCompanyName(deal.child("companyName")
                                        .getValue()
                                        .toString());
        dealPojo.setCategory(deal.child("category")
                                     .getValue()
                                     .toString());
        dealPojo.setDescription(deal.child("description")
                                        .getValue()
                                        .toString());
        dealPojo.setExpiry_date(deal.child("expiry_date")
                                        .getValue()
                                        .toString());
        dealPojo.setLocation(deal.child("location")
                                     .getValue()
                                     .toString());
        dealPojo.setCompanyUrl(deal.child("companyUrl")
                                       .getValue()
                                       .toString());

        return dealPojo;
    }
    @Override
    public void start() {

    }
}
