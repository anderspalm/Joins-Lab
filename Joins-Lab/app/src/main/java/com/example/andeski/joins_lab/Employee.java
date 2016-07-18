package com.example.andeski.joins_lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Employee extends AppCompatActivity {

    private Integer mSSN;
    private String mFirstName;
    private String mSecondName;
    private String mYOB;
    private String mCity;
    private Integer mId;

    public Employee(){}

    public Employee( Integer _id, Integer ssn, String firstName, String secondName, String yOB, String city){
        mId = _id;
        mSSN = ssn;
        mFirstName = firstName;
        mSecondName = secondName;
        mYOB = yOB;
        mCity = city;
    }

    public void setmSSN(Integer mSSN) {
        this.mSSN = mSSN;
    }

    public Integer getmSSN() {
        return mSSN;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmSecondName() {
        return mSecondName;
    }

    public void setmSecondName(String mSecondName) {
        this.mSecondName = mSecondName;
    }

    public String getmYOB() {
        return mYOB;
    }

    public void setmYOB(String mYOB) {
        this.mYOB = mYOB;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }


}
