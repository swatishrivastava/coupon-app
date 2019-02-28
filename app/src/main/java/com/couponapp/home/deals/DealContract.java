package com.couponapp.home.deals;

import com.couponapp.BasePresenter;
import com.couponapp.BaseView;

import java.util.List;


public interface DealContract {

    interface View extends BaseView<DealContract.Presenter> {
        void showAllDeals(List<DealPojo> dealPojoList);
        void failedToGetDeals();
    }

    interface Presenter extends BasePresenter {

        void fetchAllDeals();
        void fetchAllDealsByCategory(String categoryName);
    }
}
