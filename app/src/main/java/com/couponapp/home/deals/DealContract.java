package com.couponapp.home.deals;

import com.couponapp.application.BasePresenter;
import com.couponapp.application.BaseView;

import java.util.List;


public interface DealContract {

    interface View extends BaseView<DealContract.Presenter> {
        void showAllDeals(List<Deal> dealDtoList);
        void failedToGetDeals();
    }

    interface Presenter extends BasePresenter {

        void fetchAllDeals();
        void fetchAllDealsByCategory(String categoryName);
    }
}
