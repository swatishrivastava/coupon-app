package com.couponapp.home.deals;

import java.util.List;

public interface UseCaseCallback {
    <T> void onSuccess(List<T> o);

    void onFail(Throwable throwable);
}
