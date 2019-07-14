package com.couponapp.tests.deals;


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

public class DealPresenterTest {
    @Mock
    private DealContract.View mockView;

    @Mock
    private DealClientInterface dealClientInterface;

    private DealPresenter dealPresenter;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dealPresenter = new DealPresenter(mockView, dealClientInterface);
    }

    @Test
    public void testPrequisite() {
        Assert.assertNotNull(dealPresenter);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        Mockito.verify(mockView)
                .setPresenter(dealPresenter);
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
        Mockito.when(dealClientInterface.getAllDeals()).thenReturn(list);
        dealPresenter.fetchAllDeals();
        Mockito.verify(dealClientInterface, Mockito.times(1)).getAllDeals();
        Mockito.verify(mockView, Mockito.times(1)).showAllDeals(Mockito.anyList());

    }

    @Test
    public void failedToShowDealsWhenDealListEmpty() {
        List<DealDto> list = new ArrayList<>();
        Mockito.when(dealClientInterface.getAllDeals()).thenReturn(list);
        dealPresenter.fetchAllDeals();
        Mockito.verify(dealClientInterface, Mockito.times(1)).getAllDeals();
        Mockito.verify(mockView, Mockito.times(1)).failedToGetDeals();

    }

    @Test
    public void verifyViewShowAllDealCalledWhenGetListOfDealsByCategory() {
        List<DealDto> list = new ArrayList<>();
        DealDto dealDto = new DealDto();
        dealDto.setCategory("TestCategory");
        dealDto.setCompanyName("TestCompany");
        dealDto.setDescription("TestDes");
        dealDto.setExpiry_date("TestExpiry");
        dealDto.setLocation("TestLocation");
        list.add(dealDto);
        Mockito.when(dealClientInterface.getAllDealsByCategory("TestCategory"))
                .thenReturn(list);
        dealPresenter.fetchAllDealsByCategory("TestCategory");
        Mockito.verify(dealClientInterface, Mockito.times(1))
                .getAllDealsByCategory("TestCategory");
        Mockito.verify(mockView, Mockito.times(1)).showAllDeals(Mockito.anyList());

    }

    @Test
    public void failedToShowDealsWhenDealListEmptyByCategory() {
        List<DealDto> list = new ArrayList<>();
        Mockito.when(dealClientInterface.getAllDealsByCategory("TestCategory"))
                .thenReturn(list);
        dealPresenter.fetchAllDealsByCategory("TestCategory");
        Mockito.verify(dealClientInterface, Mockito.times(1))
                .getAllDealsByCategory("TestCategory");
        Mockito.verify(mockView, Mockito.times(1)).failedToGetDeals();

    }

}
