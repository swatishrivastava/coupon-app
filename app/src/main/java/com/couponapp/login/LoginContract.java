package com.couponapp.login;


import com.couponapp.application.BasePresenter;
import com.couponapp.application.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void signInSuccessful(UserInfo userInfo);
        void signInFailed();
        void showToastForInCorrectCredentials();

    }

    interface Presenter extends BasePresenter {
        void sighIn(String email,
                    String password);

        void signUp(String email,
                    String password, String name);
    }
}
