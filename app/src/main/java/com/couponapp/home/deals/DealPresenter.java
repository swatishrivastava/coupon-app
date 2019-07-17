package com.couponapp.home.deals;

import com.couponapp.home.UseCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DealPresenter implements DealContract.Presenter, UseCaseCallback {
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
    public void onSuccess(List allDeals) {
        if (allDeals.isEmpty()) {
            view.failedToGetDeals();
        } else view.<NewDealInterface>showAllDeals(allDeals);
    }

    @Override
    public void onFail(Throwable throwable) {
        view.failedToGetDeals();
    }

    @Override
    public void start() {

    }
}
