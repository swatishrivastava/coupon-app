package com.couponapp.home.deals;

import com.couponapp.home.UseCase;

public interface DealRepository {
    void fetchAllDeals(UseCase.Callback callback);

}
