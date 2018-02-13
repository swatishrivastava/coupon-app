package com.couponapp.home.deals;

import com.couponapp.BasePresenter;
import com.couponapp.BaseView;

import java.util.List;

/**
 * Created by swatishrivastava on 2/7/2018.
 */

public interface DealContract {

    interface View extends BaseView<DealContract.Presenter> {

        void showAllDeals(List<DealPojo> dealPojoList);
    }

    interface Presenter extends BasePresenter {

        void fetchAllDeals();
        void fetchAllDealsByCategory(String categoryName);
    }
}
