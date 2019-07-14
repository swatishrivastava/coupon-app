package com.couponapp.home.category;

import android.util.Log;

import com.couponapp.home.UseCase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryClient implements CategoryRespository {

    public static final String LOGGER = CategoryClient.class.getName();
    private CategoryInterface service;

    public CategoryClient(Retrofit retrofit) {
        this.service = retrofit.create(CategoryInterface.class);
    }

    @Override
    public void fetchAllCategories(UseCase.Callback callback) {
        final List<CategoryDto> categoryDtoArrayList = new ArrayList<>();
        final Call<List<CategoryDto>> allDeals = service.getAllCategories();
        allDeals.enqueue(new Callback<List<CategoryDto>>() {
            @Override
            public void onResponse(Call<List<CategoryDto>> call, Response<List<CategoryDto>> response) {
                categoryDtoArrayList.addAll(response.body());
                callback.onSuccess(categoryDtoArrayList);
                Log.e(LOGGER, "Response from service" + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<CategoryDto>> call, Throwable t) {
                Log.e(LOGGER, t.getMessage());
                callback.onError(t);
            }
        });

    }
}
