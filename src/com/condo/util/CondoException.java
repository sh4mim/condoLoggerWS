package com.condo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Enumeration;
import java.util.ResourceBundle;
/*
 * PortalException.java
 * Copyright (C) 2002-2006 Islami Bank Bangladesh Limited
 *
 * Original author: himu
 * Created on:      Aug 20, 2006 (12:23:23 PM)
 *
 * Last modification by: $Author: parvege $
 * Last modification on: $Date: 2015/12/06 10:10:05 $
 * Current revision:     $Revision: 1.1.1.1 $
 *
 */

public class CondoException extends Exception
{
    private  Log log = LogFactory.getLog(this.getClass());

    String[] params = null;

    public CondoException()
    {
        super();
    }

    public CondoException(String message)
    {
        super(message);
        log.error(message);
    }

    public CondoException(String message, Throwable cause)
    {
        super(message, cause);
        log.error(message + " CAUSE: ", cause);
    }

    public CondoException(Throwable cause)
    {
        super(cause);
        log.error("PortalException CAUSE: ", cause);
    }

    public CondoException(String message, String... params)
    {
        super(message);
        this.params = params;
        log.error(createLogMessage(message, params));
    }

    public CondoException(String message, Throwable cause, String... params)
    {
        super(message, cause);
        this.params = params;
        log.error(createLogMessage(message, params) + ", CAUSE: ", cause);
    }

    public String[] getParams()
    {
        return params;
    }

    public String getParamAsString()
    {
        StringBuilder buffer = new StringBuilder(this.getMessage());
        if (params != null)
            for (String s : params)
                buffer.append(s).append(";");

        return buffer.toString();
    }

    private String createLogMessage(String message, String... params)
    {
        StringBuffer sb = new StringBuffer(message);
        for (String param : params)
            sb.append(", ").append(param);
        sb.append(" >> ");
        sb.append("UI Message: ").append(getResourceMessage(message, params));
        return sb.toString();
    }

    private String getResourceMessage(String resourceKey, String params[])
    {
        String message = "";
        ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
        Enumeration bundleKeys = rb.getKeys();

        while (bundleKeys.hasMoreElements())
        {
            String key = (String) bundleKeys.nextElement();
            if (key.equals(resourceKey))
            {
                String value = rb.getString(key);
                if (params != null && params.length > 0)
                {
                    for (int i = 0; i < params.length; i++)
                        value = value.replace("{" + i + "}", params[i]);
                }

                message = value;
            }
        }

        return message;
    }
}
