package com.couponapp.home.category;

import com.couponapp.home.UseCase;

public interface CategoryRespository {

    void fetchAllCategories(UseCase.Callback callback);
}
