package com.couponapp.home.deals;

import java.util.List;

public interface DealClientInterface {
    List<DealPojo> getAllDeals();
    List<DealPojo> getAllDealsByCategory(String categoryName);
}
