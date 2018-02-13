package com.couponapp.admin;

import com.couponapp.BasePresenter;
import com.couponapp.BaseView;
import com.couponapp.home.deals.DealPojo;


public interface AdminContract {

    interface View extends BaseView<AdminPresenter>{
        void showDealSavedOnUi();
    }

    interface AdminPresenter extends BasePresenter{
        void saveDeal(DealPojo dealPojo);

    }
}
