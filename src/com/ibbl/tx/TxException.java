package com.ibbl.tx;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * package com.ibbl.tx;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 7/11/13(12:03 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/06/18 17:15:50 $
 * Current revision: $Revision: 1.2 $
 * <p/>
 * Revision History:
 * ------------------
 */
public class TxException extends Exception
{
    private static Logger log = LogManager.getLogger(TxException.class);

    String[] params = null;

    public TxException()
    {
        super();
    }

    public TxException(String message)
    {
        super(message);
        log.error(message);
    }

    public TxException(String message, Throwable cause)
    {
        super(message, cause);
        log.error(message + " CAUSE: " + cause);
    }

    public TxException(Throwable cause)
    {
        super(cause);
        log.error("TxException CAUSE: " + cause);
    }

    public TxException(String message, String... params)
    {
        super(message);
        this.params = params;
        log.error(createLogMessage(message, params));
    }

    public TxException(String message, Throwable cause, String... params)
    {
        super(message, cause);
        this.params = params;
        log.error(createLogMessage(message, params) + ", CAUSE: " + cause);
    }

    public String[] getParams()
    {
        return params;
    }

    private String createLogMessage(String message, String... params)
    {
        StringBuffer sb = new StringBuffer(message);
        for (String param : params)
            sb.append(", ").append(param);
        return sb.toString();
    }
}
