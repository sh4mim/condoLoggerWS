package com.condo.visitor.bean;

import java.io.Serializable;
import java.util.Date;


public class VisitorInfoBean implements Serializable
{
    private long OID;
    private String name;
    private String phoneNo;
    private String vehicleNo;
    private String nid;
    private int expectedGuest;
    private Date possibleTime;
    private String visitingTo;
    private String imageUrl;
    private int status;

    ////This field is for Joining and display
    private String dwellerName;
    private String dwellerPhone;
    private String dwellerEmail;
    private String blockNo;
    private String flatNo;
    private String dwellerImage;

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

    public String getVehicleNo()
    {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }

    public String getNid()
    {
        return nid;
    }

    public void setNid(String nid)
    {
        this.nid = nid;
    }

    public int getExpectedGuest()
    {
        return expectedGuest;
    }

    public void setExpectedGuest(int expectedGuest)
    {
        this.expectedGuest = expectedGuest;
    }

    public Date getPossibleTime()
    {
        return possibleTime;
    }

    public void setPossibleTime(Date possibleTime)
    {
        this.possibleTime = possibleTime;
    }

    public String getVisitingTo()
    {
        return visitingTo;
    }

    public void setVisitingTo(String visitingTo)
    {
        this.visitingTo = visitingTo;
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

    public String getDwellerName()
    {
        return dwellerName;
    }

    public void setDwellerName(String dwellerName)
    {
        this.dwellerName = dwellerName;
    }

    public String getDwellerPhone()
    {
        return dwellerPhone;
    }

    public void setDwellerPhone(String dwellerPhone)
    {
        this.dwellerPhone = dwellerPhone;
    }

    public String getDwellerEmail()
    {
        return dwellerEmail;
    }

    public void setDwellerEmail(String dwellerEmail)
    {
        this.dwellerEmail = dwellerEmail;
    }

    public String getBlockNo()
    {
        return blockNo;
    }

    public void setBlockNo(String blockNo)
    {
        this.blockNo = blockNo;
    }

    public String getFlatNo()
    {
        return flatNo;
    }

    public void setFlatNo(String flatNo)
    {
        this.flatNo = flatNo;
    }

    public String getDwellerImage()
    {
        return dwellerImage;
    }

    public void setDwellerImage(String dwellerImage)
    {
        this.dwellerImage = dwellerImage;
    }
}
