package com.couponapp.tests.deals;


import com.couponapp.home.UseCase;
import com.couponapp.home.deals.DealContract;
import com.couponapp.home.deals.DealDto;
import com.couponapp.home.deals.DealPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class DealPresenterTest {
    @Mock
    private DealContract.View mockView;

    @Mock
    private UseCase useCase;

    private DealPresenter dealPresenter;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dealPresenter = new DealPresenter(mockView, useCase);
    }

    @Test
    public void testPrequisite() {
        Assert.assertNotNull(dealPresenter);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        verify(mockView)
                .setPresenter(dealPresenter);
    }


    @Test
    public void verifyExecuteCalledOfUseCase() {
        dealPresenter.fetchAllDeals();
        verify(useCase).execute(Mockito.any());
    }


    @Test
    public void verifyViewShowAllDealCalledWhenGetListOfDeals() {
        List<DealDto> list = new ArrayList<>();
        DealDto dealDto = new DealDto();
        dealDto.setCategory("TestCategory");
        dealDto.setCompanyName("TestCompany");
        dealDto.setDescription("TestDes");
        dealDto.setExpiry_date("TestExpiry");
        dealDto.setLocation("TestLocation");
        list.add(dealDto);
        dealPresenter.onSuccess(list);
        verify(mockView, Mockito.times(1)).showAllDeals(Mockito.anyList());

    }

    @Test
    public void failedToShowDealsWhenDealListEmpty() {
        dealPresenter.onFail(null);
        verify(mockView, Mockito.times(1)).failedToGetDeals();

    }


}
