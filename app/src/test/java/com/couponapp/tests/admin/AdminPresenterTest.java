package com.couponapp.tests.admin;

import com.couponapp.admin.AdminContract;
import com.couponapp.admin.AdminPresenter;
import com.couponapp.home.category.CategoryPresenter;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class AdminPresenterTest {

    @Mock
    FirebaseDatabase mockFirebaseDatabase;

    @Mock
    AdminContract.View mockView;

    AdminPresenter adminPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        adminPresenter = new AdminPresenter(mockView, mockFirebaseDatabase);
    }

    @Test
    public void testPrequiste(){
        Assert.assertNotNull(adminPresenter);
    }


}
