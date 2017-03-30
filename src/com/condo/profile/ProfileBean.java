package com.condo.profile;

import java.io.Serializable;


public class ProfileBean implements Serializable
{
    private int profileID;
    private String userID;
    private String passWD;
    private int status;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
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
