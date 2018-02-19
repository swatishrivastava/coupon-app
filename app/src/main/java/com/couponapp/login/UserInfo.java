package com.couponapp.login;


import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable{

    public String id;
    public String name;
    public String email;
    public String role;
    public String password;

    public UserInfo(){}

    protected UserInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        role = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest,
                              int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(role);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getPassword() {
        return password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
