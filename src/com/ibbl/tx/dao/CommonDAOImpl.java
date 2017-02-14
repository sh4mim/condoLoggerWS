package com.ibbl.tx.dao;

import com.ibbl.tx.TxController;
import com.ibbl.tx.TxException;
import com.ibbl.tx.TxSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * package com.ibbl.tx.dao;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 9/2/13(4:38 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/02/11 08:45:12 $
 * Current revision: $Revision: 1.1.1.1 $
 * <p/>
 * Revision History:
 * ------------------
 */
public class CommonDAOImpl
{
    protected TxSession txSession = null;
    protected Session session = null;

    private CommonDAOImpl()
    {
    }


    /**
     * Only constructor for DAO
     *
     * @param txID
     */
    public CommonDAOImpl(int txID) throws TxException
    {
        this.txSession = TxController.getTxSession(txID);
        session = this.txSession.getSession();
    }


    public Object save(Object daoObject) throws TxException
    {
        Object id = null;

        try
        {
            id = session.save(daoObject);

        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("error.dao.save", e, daoObject.getClass().getSimpleName());
        }

        return id;
    }


    public void saveOrUpdate(Object daoObject) throws TxException
    {
        try
        {
            session.saveOrUpdate(daoObject);
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("error.dao.save", e, daoObject.getClass().getSimpleName());
        }

    }


    public void update(Object daoObject) throws TxException
    {
        try
        {
            session.update(daoObject);

        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("error.dao.update", e, daoObject.getClass().getSimpleName());
        }

    }
}
