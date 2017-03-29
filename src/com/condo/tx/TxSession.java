package com.condo.tx;

import org.hibernate.Session;

/**
 * package condo.tx;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: Oct 7, 2008(3:34:35 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/02/11 08:45:12 $
 * Current revision: $Revision: 1.1.1.1 $
 * <p/>
 * Revision History:
 * ------------------
 */
public abstract class TxSession
{
    protected Session session = null;

    public abstract int beginTx() throws TxException;

    public abstract void commitTx() throws TxException;

    public abstract void rollbackTx() throws TxException;

    /**
     * @param session i.e. database session
     * @return instance of TxSession implentation object. If implementation is changed and done to another classe,
     *         only change will be inside this method.
     * @throws TxException contains error/warning/notification
     */
    public static TxSession getInstance(Session session) throws TxException
    {
        return new TxSessionImpl(session);
    }

    /**
     * @return org.hibernate.Session Object
     */
    public Session getSession()
    {
        return session;
    }
}

