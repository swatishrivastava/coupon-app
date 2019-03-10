package com.couponapp.home.category;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryClientInterface categoryClientInterface;
    private CategoryContract.View view;

    public CategoryPresenter(CategoryClientInterface categoryClient,
                             CategoryContract.View viewObj) {
        this.categoryClientInterface = categoryClient;
        this.view = viewObj;
        this.view.setPresenter(this);
    }

    @Override
    public void fetchAllCategories() {
        List<String> strings = categoryClientInterface.fetchAllCategories();
        if (strings.isEmpty()) {
            view.failedToGetCategory();
        } else {
            view.showAllCategory(strings);
        }

    }

    @Override
    public void openSelectedCategoryDeals(String categoryName) {
        view.showSelectedCategoryDealsUI(categoryName);
    }


    @Override
    public void start() {

    }
}
