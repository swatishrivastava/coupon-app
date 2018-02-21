package com.couponapp.home.category;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private FirebaseDatabase firebaseDatabase;
    private CategoryContract.View view;
    public  final static String CATEGORIES="Categories";

    public CategoryPresenter(FirebaseDatabase firebaseDatabase,
                             CategoryContract.View viewObj) {
        this.firebaseDatabase = firebaseDatabase;
        this.view = viewObj;
        this.view.setPresenter(this);
    }


    @Override
    public void fetchAllCategories() {
        final List<String> categoryPojoList = new ArrayList<>();
        DatabaseReference myRef = firebaseDatabase.getReference(CATEGORIES);
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
