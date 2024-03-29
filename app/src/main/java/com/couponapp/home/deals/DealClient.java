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
    public static final String LOGGER = DealClient.class.getName();
    private DealInterface service;

    public DealClient(Retrofit retrofit) {
        this.service = retrofit.create(DealInterface.class);
    }


    @Override
    public void fetchAllDeals(UseCase.Callback callback) {
        final Call<List<DealDto>> allDeals = service.getAllDeals();
        allDeals.enqueue(new Callback<List<DealDto>>() {
            @Override
            public void onResponse(Call<List<DealDto>> call, Response<List<DealDto>> response) {
                Log.e(LOGGER, "Response from backend" + response.body().toString());
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<DealDto>> call, Throwable t) {
                Log.e(LOGGER, t.getMessage());
                callback.onError(t);
            }
        });
    }
}

