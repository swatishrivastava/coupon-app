package com.couponapp.home.category;

import com.couponapp.BasePresenter;
import com.couponapp.BaseView;

import java.util.List;

public interface CategoryContract {

    interface View extends BaseView<CategoryContract.Presenter> {
        void showAllCategory(List<String> categoryList);
        void showSelectedCategoryDealsUI(String categoryName);

    }

    interface Presenter extends BasePresenter {
        void fetchAllCategories();
        void openSelectedCategoryDeals(String categoryName);

    }

     interface OnCategoryClickListener{
        void onCategoryClick(String categoryName);


    }

}
