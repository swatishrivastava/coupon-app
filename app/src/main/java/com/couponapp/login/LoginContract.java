package com.couponapp.login;


import com.couponapp.BasePresenter;
import com.couponapp.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void signInSuccessful(UserInfo userInfo);
        void signInFailed();

    }

    interface Presenter extends BasePresenter {
        void sighIn(String email,
                    String password);

        void signUp(String email,
                    String password, String name);
    }
}
