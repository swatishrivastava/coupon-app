package com.couponapp.home.deals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class GetDealsUseCaseTest {

    @Mock
    private DealRepository dealRepository;
    private GetDealsUseCase getDealsUseCase;
    @Mock
    private UseCaseCallback callback;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        getDealsUseCase = new GetDealsUseCase(dealRepository);

    }

    @Test
    public void verifyFetchAllDealsCalled() {
        getDealsUseCase.execute(Mockito.any());
        verify(dealRepository).fetchAllDeals(Mockito.any());
    }


    @Test
    public void verifyOnSuccessWhenDealsReceived() {
        Mockito.doNothing().when(callback).onSuccess(Mockito.any());
        List<DealDto> list = new ArrayList<>();
        DealDto dealDto = new DealDto();
        dealDto.setCategory("TestCategory");
        dealDto.setCompanyName("TestCompany");
        dealDto.setDescription("TestDes");
        dealDto.setExpiry_date("TestExpiry");
        dealDto.setLocation("TestLocation");
        list.add(dealDto);
        getDealsUseCase.onSuccess(list);
        ArgumentCaptor argumentCaptor = ArgumentCaptor.forClass(Deal.class);
        //verify(callback).onSuccess(argumentCaptor.capture());
    }

    @Test
    public void verifyOnFailCalled() {
        doNothing().when(dealRepository).fetchAllDeals(Mockito.any());
        getDealsUseCase.execute(callback);
        getDealsUseCase.onError(null);
        verify(callback).onFail(Mockito.any());

    }

}