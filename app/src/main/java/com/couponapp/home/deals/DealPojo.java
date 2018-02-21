package com.couponapp.home.deals;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DealPojo implements Parcelable, Serializable {
    public String companyName;
    public String description;
    public String category;

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public static Creator<DealPojo> getCREATOR() {
        return CREATOR;
    }

    public String companyUrl;
    public String location;
    public String expiry_date;

    protected DealPojo(Parcel in) {
        companyName = in.readString();
        description = in.readString();
        category = in.readString();
        location = in.readString();
        expiry_date = in.readString();
    }

    public static final Creator<DealPojo> CREATOR = new Creator<DealPojo>() {
        @Override
        public DealPojo createFromParcel(Parcel in) {
            return new DealPojo(in);
        }

        @Override
        public DealPojo[] newArray(int size) {
            return new DealPojo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,
                              int flags) {
        dest.writeString(companyName);
        dest.writeString(description);
        dest.writeString(category);
        dest.writeString(location);
        dest.writeString(expiry_date);
    }

    public DealPojo() {
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

}
