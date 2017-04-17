package com.condo.residence.bean;

import java.io.Serializable;
import java.util.Date;


public class ResidenceInfoBean implements Serializable
{
    private long OID;
    private String name;
    private String phoneNo;
    private String email;
    private String blockNO;
    private String flatNo;
    private String imageUrl;
    private int status;
    private String userID;

    public long getOID()
    {
        return OID;
    }

    public void setOID(long OID)
    {
        this.OID = OID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getBlockNO()
    {
        return blockNO;
    }

    public void setBlockNO(String blockNO)
    {
        this.blockNO = blockNO;
    }

    public String getFlatNo()
    {
        return flatNo;
    }

    public void setFlatNo(String flatNo)
    {
        this.flatNo = flatNo;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
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
