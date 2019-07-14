package com.couponapp.home.category;

import com.couponapp.home.UseCase;
import com.couponapp.home.deals.ICallback;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetCategoriesUseCase implements UseCase, UseCase.Callback {

    private CategoryRespository categoryRespository;
    private ICallback callback;

    public GetCategoriesUseCase(CategoryRespository categoryRespository) {
        this.categoryRespository = categoryRespository;
    }

    @Override
    public void execute(ICallback callback) {
        categoryRespository.fetchAllCategories(this);
        this.callback = callback;
    }

    @Override
    public void onSuccess(Object o) {
        List<CategoryDto> categoryDtoList = (ArrayList) o;
        callback.onSuccess(categoryDtoList.stream()
                .map(categoryDto -> categoryDto.getCategoryEntity()).collect(toList()));
    }

    @Override
    public void onError(Throwable throwable) {
        callback.onFail(throwable);
    }
}
