package com.condo.tx;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * package condo.tx;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: Oct 7, 2008(3:32:51 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/02/11 08:45:12 $
 * Current revision: $Revision: 1.1.1.1 $
 * <p/>
 * Revision History:
 * ------------------
 */
public class TxSessionImpl extends TxSession
{

    private Transaction transaction = null;

    private TxSessionImpl()
    {
    }

    protected TxSessionImpl(Session session) throws TxException
    {
        if (null == session)
        {
            throw new TxException("error.tx.session.notFound");
        }
        this.session = session;
    }

    public int beginTx() throws TxException
    {
        if (null == session)
        {
            throw new TxException("error.tx.session.notFound");
        }
        try
        {
            transaction = session.beginTransaction();
            return transaction.hashCode();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("errors.general", e, e.getCause().getMessage());
        }
    }

    public void commitTx() throws TxException
    {
        try
        {
            if (session.isOpen() && null != transaction
                    && !transaction.wasCommitted()
                    && !transaction.wasRolledBack())
            {
                transaction.commit();
            }
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("errors.general", e, e.getCause().getMessage());
        }
    }

    public void rollbackTx() throws TxException
    {
        try
        {
            if (session.isOpen() && null != transaction
                    && !transaction.wasCommitted()
                    && !transaction.wasRolledBack())
            {
                transaction.rollback();
            }
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("errors.general", e, e.getCause().getMessage());
        }
    }

}
