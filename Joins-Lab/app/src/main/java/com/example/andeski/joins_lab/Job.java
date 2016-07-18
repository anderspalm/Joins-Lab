package com.example.andeski.joins_lab;

/**
 * Created by andeski on 7/16/16.
 */
public class Job {

    private Integer mSSN;
    private String mCompany;
    private String mSalary;
    private String mExperience;

    public Job(Integer ssn, String company, String salary, String experience){
        mCompany = company;
        mExperience = experience;
        mSalary = salary;
        mSSN = ssn;
    }

    public Integer getmSSN() {
        return mSSN;
    }

    public void setmSSN(Integer mSSN) {
        this.mSSN = mSSN;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmSalary() {
        return mSalary;
    }

    public void setmSalary(String mSalary) {
        this.mSalary = mSalary;
    }

    public String getmExperience() {
        return mExperience;
    }

    public void setmExperience(String mExperience) {
        this.mExperience = mExperience;
    }
}
