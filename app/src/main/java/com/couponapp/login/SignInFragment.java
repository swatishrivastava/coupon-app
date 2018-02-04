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
import android.widget.TextView;
import android.widget.Toast;

import com.couponapp.home.HomeActivity;

import example.couponapp.com.couponapp.R;


public class SignInFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter presenterObj;
    private EditText editText_username;
    private EditText editText_email;
    private EditText editText_password;
    private EditText editText_confirm_password;
    private Button   sign_in_button;
    private Button   sign_up_button;
    private TextView sign_up_label;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_layout, container, false);
        initializeView(root);
        return root;
    }

    private void initializeView(View view){
        editText_email =view.findViewById(R.id.editText_email);
        editText_password=view.findViewById(R.id.editText_password);
        editText_username=view.findViewById(R.id.editText_username);
        editText_confirm_password=view.findViewById(R.id.editText_confirm_password);
        sign_in_button=view.findViewById(R.id.sign_in_btn);
        sign_up_button=view.findViewById(R.id.sign_up_btn);
        sign_up_label=view.findViewById(R.id.sigh_in_label);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterObj.sighIn(editText_email.getText().toString(), editText_password.getText().toString());
            }
        });


        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterObj.signUp(editText_email.getText().toString(), editText_password.getText().toString());
            }
        });

        sign_up_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_username.setVisibility(View.VISIBLE);
                editText_confirm_password.setVisibility(View.VISIBLE);
                sign_up_button.setVisibility(View.VISIBLE);
                sign_in_button.setVisibility(View.GONE);
            }
        });
    }


    private void clearAllViews(){
        editText_username.clearComposingText();
        editText_confirm_password.clearComposingText();
        editText_email.clearComposingText();
        editText_password.clearComposingText();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterObj.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
     this.presenterObj=presenter;

    }

    @Override
    public void signInSuccessful() {
        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
        clearAllViews();
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void signInFailed() {
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
        clearAllViews();

    }



}
