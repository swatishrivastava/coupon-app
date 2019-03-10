package com.couponapp.home.category;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryClient implements CategoryClientInterface {
    public final static String CATEGORIES = "Categories";
    private FirebaseDatabase firebaseDatabase;


    public CategoryClient(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    @Override
    public List<String> fetchAllCategories() {
        final List<String> categoryPojoList = new ArrayList<>();
        DatabaseReference myRef = firebaseDatabase.getReference(CATEGORIES);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot category : dataSnapshot.getChildren()) {
                    categoryPojoList.add(category.getValue()
                            .toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(CategoryClient.class.getName(), "Failed to get all categories");
            }
        });
        return categoryPojoList;
    }
}
