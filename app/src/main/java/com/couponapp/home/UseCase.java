package com.couponapp.home;

import com.couponapp.home.deals.ICallback;

public interface UseCase {

    void execute(ICallback callback);

    interface Callback {
        void onSuccess(Object o);
        void onError(Throwable throwable);
    }

}
