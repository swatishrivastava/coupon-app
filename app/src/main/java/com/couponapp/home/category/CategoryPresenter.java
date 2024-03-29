package com.couponapp.home.category;

import com.couponapp.home.UseCase;
import com.couponapp.home.deals.UseCaseCallback;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter, UseCaseCallback {

    private UseCase categoryUsecase;
    private CategoryContract.View view;

    public CategoryPresenter(UseCase categoryUsecase,
                             CategoryContract.View viewObj) {
        this.categoryUsecase = categoryUsecase;
        this.view = viewObj;
        this.view.setPresenter(this);
    }

    @Override
    public void fetchAllCategories() {
        categoryUsecase.execute(this);

    }

    @Override
    public void openSelectedCategoryDeals(String categoryName) {
        view.showSelectedCategoryDealsUI(categoryName);
    }


    @Override
    public void start() {

    }

    @Override
    public <T> void onSuccess(List<T> o) {
        List<Category> categories = (ArrayList) o;
        if (categories.isEmpty()) {
            view.failedToGetCategory();
        } else {
            view.showAllCategory(categories);
        }
    }

    @Override
    public void onFail(Throwable throwable) {
        view.failedToGetCategory();
    }
}
