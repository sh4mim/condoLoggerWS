package com.condo.profile;

import java.io.Serializable;


public class ProfileBean implements Serializable
{
    private int profileID;
    private String userID;
    private String passWD;
    private int status;

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

    public String getPassWD()
    {
        return passWD;
    }

    public void setPassWD(String passWD)
    {
        this.passWD = passWD;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
