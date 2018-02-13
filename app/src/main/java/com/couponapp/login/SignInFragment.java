package com.couponapp.login;

import android.content.Intent;
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

import com.couponapp.home.DealsHomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.couponapp.com.couponapp.R;


public class SignInFragment extends Fragment implements LoginContract.View {

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
    private LoginContract.Presenter presenterObj;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_layout, container, false);
        unbinder = ButterKnife.bind(this, root);
        initializeView();
        return root;
    }

    private void initializeView() {
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterObj.sighIn(editTextEmail.getText()
                                            .toString(), editTextPassword.getText()
                                            .toString());
                signInBtn.setVisibility(View.INVISIBLE);
            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterObj.signUp(editTextEmail.getText()
                                            .toString(), editTextPassword.getText()
                                            .toString(), editTextUsername.getText()
                                            .toString());
                signInBtn.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.INVISIBLE);

            }
        });

        sighInLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUsername.setVisibility(View.VISIBLE);
                editTextConfirmPassword.setVisibility(View.VISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signInBtn.setVisibility(View.GONE);
            }
        });
    }


    private void clearAllViews() {
        editTextUsername.clearComposingText();
        editTextConfirmPassword.clearComposingText();
        editTextEmail.clearComposingText();
        editTextPassword.clearComposingText();
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
    public void signInSuccessful(UserPojo userPojo) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT)
                .show();
        clearAllViews();
        Intent intent = new Intent(getContext(), DealsHomeActivity.class);
        intent.putExtra(DealsHomeActivity.USER_INFO, userPojo);
        startActivity(intent);
    }

    @Override
    public void signInFailed() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT)
                .show();
        clearAllViews();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
