package com.couponapp.home.deals;

import android.util.Log;

import com.couponapp.home.UseCase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DealClient implements DealRepository {
    private DealInterface service;

    public DealClient(Retrofit retrofit) {
        this.service = retrofit.create(DealInterface.class);
    }


    @Override
    public void fetchAllDeals(UseCase.Callback callback) {
        final List<DealDto> dealDtoList = new ArrayList<>();
        final Call<List<DealDto>> allDeals = service.getAllDeals();
        allDeals.enqueue(new Callback<List<DealDto>>() {
            @Override
            public void onResponse(Call<List<DealDto>> call, Response<List<DealDto>> response) {
                dealDtoList.addAll(response.body());
                callback.onSuccess(dealDtoList);
                Log.e(DealClient.class.getName(), "Response from service" + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<DealDto>> call, Throwable t) {
                Log.e(DealClient.class.getName(), t.getMessage());
                callback.onError(t);
            }
        });
    }
}

