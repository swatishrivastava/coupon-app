package com.couponapp.tests.category;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryClientTest {

    @Mock
    FirebaseDatabase firebaseDatabase;
    private CategoryClient categoryClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        categoryClient=new CategoryClient(firebaseDatabase);
    }

    @Test
    public void returnEmptyListOfCategories(){


    }
}