package com.couponapp.tests.category;

import com.couponapp.home.category.CategoryContract;
import com.couponapp.home.category.CategoryPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class CategoryPresenterTest {
   /* @Mock
    private CategoryClientInterface categoryClientInterface;
    @Mock
    private CategoryContract.View mockView;

    private CategoryPresenter categoryPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        categoryPresenter = new CategoryPresenter(categoryClientInterface, mockView);
    }

    @Test
    public void testPrequiste() {
        Assert.assertNotNull(categoryPresenter);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        categoryPresenter =
                new CategoryPresenter(categoryClientInterface, mockView);
        verify(mockView).setPresenter(categoryPresenter);
    }


    @Test
    public void testOpenSelectedCategoryDeals() {
        categoryPresenter.openSelectedCategoryDeals("testCategory");
        Mockito.verify(mockView).showSelectedCategoryDealsUI("testCategory");
    }

    @Test
    public void testShowAllCategoriesOnUI(){
        List<String> listOfCategoris=new ArrayList<>();
        listOfCategoris.add("Food");
        listOfCategoris.add("Music");
        Mockito.when(categoryClientInterface.fetchAllCategories()).thenReturn(listOfCategoris);
        categoryPresenter.fetchAllCategories();
        Mockito.verify(mockView, times(1)).showAllCategory(anyList());

    }

    @Test
    public void doNotShowCategoriesWhenReceivedEmptyListFromBackend(){
        Mockito.when(categoryClientInterface.fetchAllCategories()).thenReturn(new ArrayList<String>());
        categoryPresenter.fetchAllCategories();
        Mockito.verify(mockView).failedToGetCategory();

    }*/
}
