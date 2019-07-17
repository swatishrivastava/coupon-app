package com.couponapp.login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.couponapp.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.couponapp.com.couponapp.R;


public class SignInFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.editText_username)
    EditText editTextUsername;
    @BindView(R.id.editText_email)
    EditText editTextEmail;
    @BindView(R.id.editText_password)
    EditText editTextPassword;
    @BindView(R.id.editText_confirm_password)
    EditText editTextConfirmPassword;
    @BindView(R.id.sign_in_btn)
    Button signInBtn;
    @BindView(R.id.sign_up_btn)
    Button signUpBtn;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.sigh_in)
    TextView sighIn;
    @BindView(R.id.sigh_in_label)
    TextView sighInLabel;
    Unbinder unbinder;
    @BindView(R.id.skip_login)
    TextView skipLogin;
    @BindView(R.id.app_title)
    TextView appTitle;

    private LoginContract.Presenter presenterObj;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_layout, container, false);
        unbinder = ButterKnife.bind(this, root);
        initializeView();
        Typeface typeface = getResources().getFont(R.font.brush_script);
        appTitle.setTypeface(typeface);
        return root;
    }

    private void initializeView() {
        signInBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
        sighInLabel.setOnClickListener(this);
        skipLogin.setOnClickListener(this);
    }


    private void clearAllViews() {
        editTextUsername.setText("");
        editTextConfirmPassword.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        signInBtn.setVisibility(View.VISIBLE);
        sighIn.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterObj.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenterObj = presenter;

    }

    @Override
    public void signInSuccessful(UserInfo userInfo) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getString(R.string.login_successful_msg), Toast.LENGTH_SHORT)
                .show();
        clearAllViews();
        startDealActivity(userInfo);
    }


    private void startDealActivity(UserInfo userInfo) {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.putExtra(HomeActivity.USER_INFO, userInfo);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void signInFailed() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getString(R.string.login_failed_msg), Toast.LENGTH_SHORT)
                .show();
        clearAllViews();

    }

    @Override
    public void showToastForInCorrectCredentials() {
        clearAllViews();
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getString(R.string.correct_credential_msg), Toast.LENGTH_SHORT)
                .show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_btn: {
                presenterObj.sighIn(editTextEmail.getText()
                                            .toString(), editTextPassword.getText()
                                            .toString());
                signInBtn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.sign_up_btn: {

                presenterObj.signUp(editTextEmail.getText()
                                            .toString(), editTextPassword.getText()
                                            .toString(), editTextUsername.getText()
                                            .toString());
                signInBtn.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.INVISIBLE);
                break;
            }
            case R.id.skip_login: {
                startDealActivity(new UserInfo());
                break;
            }

            case R.id.sigh_in_label: {
                editTextUsername.setVisibility(View.VISIBLE);
                editTextConfirmPassword.setVisibility(View.VISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signInBtn.setVisibility(View.GONE);
            }
        }
    }


}
