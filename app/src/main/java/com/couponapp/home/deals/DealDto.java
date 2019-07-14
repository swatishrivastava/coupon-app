package com.couponapp.home.deals;


import com.google.gson.annotations.SerializedName;


public class DealDto {

    @SerializedName("id")
    private String id;
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("description")
    private String description;
    @SerializedName("expiry_date")
    private String expiry_date;
    @SerializedName("category")
    private String category;
    @SerializedName("companyUrl")
    private String companyUrl;
    @SerializedName("location")
    private String location;

    public DealDto() {
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Deal getDealFromDto() {
        return new Deal(this.companyName, this.description, this.category, this.companyUrl);
    }

}
