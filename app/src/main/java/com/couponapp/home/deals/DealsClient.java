package com.couponapp.home.deals;

import android.util.Log;

import com.couponapp.utils.DealConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DealsClient implements DealClientInterface {
    private FirebaseDatabase firebaseDatabase;

    public DealsClient(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    @Override
    public List<DealPojo> getAllDeals() {
        final List<DealPojo> dealPojoList = new ArrayList<>();
        DatabaseReference myRef = firebaseDatabase.getReference(DealConstants.DEALS);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot deal : dataSnapshot.getChildren()) {
                    DealPojo dealPojo = getDealPojoWithData(deal);
                    dealPojoList.add(dealPojo);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(DealPresenter.class.getSimpleName(), "Failed to read value.",
                        error.toException());
            }
        });
        return dealPojoList;
    }

    @Override
    public List<DealPojo> getAllDealsByCategory(String categoryName) {
        final List<DealPojo> dealPojoList = new ArrayList<>();
        DatabaseReference myRef = firebaseDatabase.getReference(DealConstants.DEALS);
        Query query = myRef.orderByChild(DealConstants.CATEGORY)
                .equalTo(categoryName);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot deal : dataSnapshot.getChildren()) {
                    DealPojo dealPojo = getDealPojoWithData(deal);
                    dealPojoList.add(dealPojo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(DealPresenter.class.getSimpleName(), "Failed to read value.",
                        databaseError.toException());
            }
        });


        return dealPojoList;
    }

    private DealPojo getDealPojoWithData(com.google.firebase.database.DataSnapshot deal) {
        DealPojo dealPojo = new DealPojo();
        dealPojo.setCompanyName(deal.child(DealConstants.DEAL_COMPANY_NAME)
                .getValue()
                .toString());
        dealPojo.setCategory(deal.child(DealConstants.DEAL_CATEGORY)
                .getValue()
                .toString());
        dealPojo.setDescription(deal.child(DealConstants.DEAL_DESCRIPTION)
                .getValue()
                .toString());
        dealPojo.setExpiry_date(deal.child(DealConstants.DEAL_EXPIRY_DATE)
                .getValue()
                .toString());
        dealPojo.setLocation(deal.child(DealConstants.DEAL_LOCATION)
                .getValue()
                .toString());
        dealPojo.setCompanyUrl(deal.child(DealConstants.DEAL_COMPANY_URL)
                .getValue()
                .toString());

        return dealPojo;
    }
}
