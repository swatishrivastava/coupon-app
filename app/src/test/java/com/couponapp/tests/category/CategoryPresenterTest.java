package com.couponapp.tests.category;

import com.couponapp.home.category.CategoryContract;
import com.couponapp.home.category.CategoryPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class CategoryPresenterTest {


    @Mock
    FirebaseDatabase mockFirebaseDatabase;

    @Mock
    CategoryContract.View mockView;

    CategoryPresenter categoryPresenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        categoryPresenter=new CategoryPresenter(mockFirebaseDatabase, mockView);
    }

    @Test
    public void testPrequiste(){
        Assert.assertNotNull(categoryPresenter);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        categoryPresenter =
                new CategoryPresenter(mockFirebaseDatabase, mockView);
        verify(mockView).setPresenter(categoryPresenter);
    }


    @Test
    public void testOpenSelectedCategoryDeals(){
        categoryPresenter.openSelectedCategoryDeals("testCategory");
        Mockito.verify(mockView).showSelectedCategoryDealsUI("testCategory");
    }

    @Test
    public void testFetchAllCategories(){
        DatabaseReference databaseReference=Mockito.mock(DatabaseReference.class);
        Mockito.when(mockFirebaseDatabase.getReference(CategoryPresenter.CATEGORIES)).thenReturn(databaseReference);
        categoryPresenter.fetchAllCategories();
        Assert.assertNotNull(databaseReference);


    }

}
