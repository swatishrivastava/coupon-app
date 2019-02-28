package com.couponapp.tests.deals;


import com.couponapp.utils.DealConstants;
import com.couponapp.home.deals.DealContract;
import com.couponapp.home.deals.DealPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DealPresenterTest {

    @Mock
    FirebaseDatabase mockFirebaseDatabase;

    @Mock
    DealContract.View mockView;

    DealPresenter dealPresenter;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dealPresenter = new DealPresenter(mockFirebaseDatabase, mockView);
    }

    @Test
    public void testPrequisite() {
        Assert.assertNotNull(dealPresenter);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        DealPresenter dealPresenter = new DealPresenter(mockFirebaseDatabase, mockView);
        Mockito.verify(mockView)
                .setPresenter(dealPresenter);
    }

    @Test
    public void testFetchAllDeals() {
        DatabaseReference databaseReference = Mockito.mock(DatabaseReference.class);
        Mockito.when(mockFirebaseDatabase.getReference(DealConstants.DEALS))
                .thenReturn(databaseReference);
        dealPresenter.fetchAllDeals();
        Assert.assertNotNull(databaseReference);
    }
}
