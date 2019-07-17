package com.couponapp.home;

import com.couponapp.home.deals.UseCaseCallback;

public interface UseCase {

    void execute(UseCaseCallback callback);

    interface Callback {
        void onSuccess(Object o);
        void onError(Throwable throwable);
    }

}
