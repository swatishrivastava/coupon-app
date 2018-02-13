package com.couponapp.home.category;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swatishrivastava on 2/7/2018.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    FirebaseDatabase firebaseDatabase;
    CategoryContract.View view;

    public CategoryPresenter(FirebaseDatabase firebaseDatabase,
                             CategoryContract.View viewObj) {
        this.firebaseDatabase = firebaseDatabase;
        this.view = viewObj;
        this.view.setPresenter(this);
    }


    @Override
    public void fetchAllCategories() {
        final List<String> categoryPojoList = new ArrayList<>();
        DatabaseReference myRef = firebaseDatabase.getReference("Categories");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot category : dataSnapshot.getChildren()) {
                    categoryPojoList.add(category.getValue()
                                                 .toString());
                }
                view.showAllCategory(categoryPojoList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }

    @Override
    public void openSelectedCategoryDeals(String categoryName) {
         view.showSelectedCategoryDealsUI(categoryName);
    }



    @Override
    public void start() {

    }
}
