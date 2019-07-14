package com.couponapp.admin;

import com.couponapp.application.BasePresenter;
import com.couponapp.application.BaseView;
import com.couponapp.home.deals.DealDto;


public interface AdminContract {

    interface View extends BaseView<AdminPresenter>{
        void showDealSavedOnUi();
    }

    interface AdminPresenter extends BasePresenter{
        void saveDeal(DealDto dealDto);

    }
}
