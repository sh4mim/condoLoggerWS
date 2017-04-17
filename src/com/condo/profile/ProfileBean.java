package com.condo.profile;

import java.io.Serializable;


public class ProfileBean implements Serializable
{
    private int profileID;
    private String userID;
    private String pin;
    private int status;
    private Integer userType;


    public int getProfileID()
    {
        return profileID;
    }

    public void setProfileID(int profileID)
    {
        this.profileID = profileID;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Integer getUserType()
    {
        return userType;
    }

    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }
}
