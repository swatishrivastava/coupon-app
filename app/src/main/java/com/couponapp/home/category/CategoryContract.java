package com.couponapp.home.category;

import com.couponapp.application.BasePresenter;
import com.couponapp.application.BaseView;

import java.util.List;

public interface CategoryContract {

    interface View extends BaseView<CategoryContract.Presenter> {
         void showAllCategory(List<String> categoryList);
         void showSelectedCategoryDealsUI(String categoryName);
         void failedToGetCategory();

    }

    interface Presenter extends BasePresenter {
        void fetchAllCategories();
        void openSelectedCategoryDeals(String categoryName);

    }

     interface OnCategoryClickListener{
        void onCategoryClick(String categoryName);


    }

}
