package com.couponapp.home.deals;

import com.couponapp.utils.DealConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DealPresenter implements DealContract.Presenter {
    private DealContract.View view;
    private DealClientInterface dealClientInterface;

    public DealPresenter(DealContract.View viewObj, DealClientInterface dealsClient) {
        this.view = viewObj;
        this.dealClientInterface = dealsClient;
        this.view.setPresenter(this);
    }

    @Override
    public void fetchAllDeals() {
        List<DealPojo> allDeals = dealClientInterface.getAllDeals();
        if (allDeals.isEmpty()) {
            view.failedToGetDeals();
        } else view.showAllDeals(allDeals);
    }


    @Override
    public void fetchAllDealsByCategory(String categoryName) {
        List<DealPojo> allDealsByCategory = dealClientInterface.getAllDealsByCategory(categoryName);
        if (allDealsByCategory.isEmpty()) {
            view.failedToGetDeals();
        } else view.showAllDeals(allDealsByCategory);
    }


    @Override
    public void start() {

    }
}
