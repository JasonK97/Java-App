package com.example.myapplication.fragment;

public class UserHelperClass {
    String signName, userEmail, requests;
    public UserHelperClass() {
    }

    public UserHelperClass(String signName, String userEmail, String requests) {
        this.signName = signName;
        this.userEmail = userEmail;
        this.requests = requests;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }
}
