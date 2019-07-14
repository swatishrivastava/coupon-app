package com.couponapp.home.category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface CategoryInterface {

    @GET("coupon-app/category")
    Call<List<CategoryDto>> getAllCategories();

}
