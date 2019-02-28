package com.couponapp.tests.login;


import com.couponapp.login.LoginContract;
import com.couponapp.login.LoginPresenter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoginPresenterTest {

    @Mock
    FirebaseDatabase firebaseDatabase;

    @Mock
    FirebaseAuth firebaseAuth;

    @Mock
    LoginContract.View loginView;
    @Mock
    Task<AuthResult> task;

    LoginPresenter loginPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter(firebaseAuth, firebaseDatabase, loginView);
    }


    @Test
    public void testPrequisite() {
        Assert.assertNotNull(loginPresenter);
        Assert.assertNotNull(firebaseDatabase);
        Assert.assertNotNull(firebaseAuth);
        Assert.assertNotNull(loginView);

    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        LoginPresenter loginPresenter =
                new LoginPresenter(firebaseAuth, firebaseDatabase, loginView);
        Mockito.verify(loginView)
                .setPresenter(loginPresenter);
    }

    @Test
    public void testForEmptyCredential() {
        loginPresenter.sighIn("", "");
        Mockito.verify(loginView)
                .showToastForInCorrectCredentials();
    }


    @Test
    public void verifySignIn() {
        Mockito.when(
                firebaseAuth.signInWithEmailAndPassword(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(task);
        loginPresenter.sighIn("test@test.com", "test");

        Mockito.verify(firebaseAuth)
                .signInWithEmailAndPassword(Mockito.anyString(), Mockito.anyString());
    }


    @Test
    public void verifySignUp() {
        Mockito.when(firebaseAuth.createUserWithEmailAndPassword(Mockito.anyString(),
                                                                 Mockito.anyString()))
                .thenReturn(task);
        loginPresenter.signUp("test@test.com", "test", "test");

        Mockito.verify(firebaseAuth)
                .createUserWithEmailAndPassword(Mockito.anyString(), Mockito.anyString());
    }
}
