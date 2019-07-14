package com.couponapp.home.deals;

import com.couponapp.home.UseCase;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetDealsUseCase implements UseCase, UseCase.Callback {

    private DealRepository dealRepository;
    private ICallback callback;

    public GetDealsUseCase(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public void execute(ICallback callback) {
        dealRepository.fetchAllDeals(this );
        this.callback=callback;

    }

    @Override
    public void onSuccess(Object o) {
        ArrayList allDeals = (ArrayList) o;
        callback.onSuccess(getListOfDealEntityFromDealDto(allDeals));
    }

    @Override
    public void onError(Throwable throwable) {
        callback.onFail(throwable);
    }

    private List<Deal> getListOfDealEntityFromDealDto(List<DealDto> allDeals) {
        return allDeals.stream().map(dealDto -> dealDto.getDealFromDto()).collect(toList());

    }

}
