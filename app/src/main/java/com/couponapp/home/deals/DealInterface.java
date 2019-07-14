package com.couponapp.home.deals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DealInterface {

    @GET("coupon-app/deal")
    Call<List<DealDto>> getAllDeals();
}
