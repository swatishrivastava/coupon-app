package com.couponapp.home.deals;

public class Deal implements NewDealInterface {
    private String companyName;
    private String description;
    private String category;
    private String companyUrl;

    public Deal(final String companyName, final String description, final String category,
                final String companyUrl) {
        this.companyName = companyName;
        this.description = description;
        this.category = category;
        this.companyUrl=companyUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }


}
