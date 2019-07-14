package com.couponapp.home.deals;

import com.couponapp.home.UseCase;

import java.util.ArrayList;

public class DealPresenter implements DealContract.Presenter, ICallback {
    private DealContract.View view;
    private UseCase dealsUseCase;

    public DealPresenter(DealContract.View viewObj, UseCase dealUsecase) {
        this.view = viewObj;
        this.dealsUseCase = dealUsecase;
        this.view.setPresenter(this);
    }

    @Override
    public void fetchAllDeals() {
        dealsUseCase.execute(this);
    }

    @Override
    public void fetchAllDealsByCategory(String categoryName) {
       /* List<DealDto> allDealsByCategory = dealClientInterface.getAllDealsByCategory(categoryName);
        if (allDealsByCategory.isEmpty()) {
            view.failedToGetDeals();
        } else view.showAllDeals(getListOfDealsFromDealDto(allDealsByCategory));*/
    }


    @Override
    public void start() {

    }

    @Override
    public void onSuccess(Object o) {
        ArrayList allDeals = (ArrayList) o;
        if (allDeals.isEmpty()) {
            view.failedToGetDeals();
        } else view.showAllDeals(allDeals);
    }

    @Override
    public void onFail(Throwable throwable) {
        view.failedToGetDeals();
    }
}
