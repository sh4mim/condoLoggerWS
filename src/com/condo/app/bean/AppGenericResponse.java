package com.condo.app.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * package com.ibbl.app.bean;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 11-Feb-16(11:42 AM)
 * Last modification by: $Author: harun $
 * Last modification on $Date: 2016/10/01 07:31:45 $
 * Current revision: $Revision: 1.4 $
 * <p/>
 * Revision History:
 * ------------------
 **/
@JsonAutoDetect
public class AppGenericResponse implements Serializable
{
    @JsonProperty("statusCode")
    protected int status;
    protected String note;
    protected Object object;

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
}
