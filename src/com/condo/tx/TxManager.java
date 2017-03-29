package com.condo.tx;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * package condo.tx;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: Oct 7, 2008(3:27:48 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/09/20 08:11:15 $
 * Current revision: $Revision: 1.2 $
 * <p/>
 * Revision History:
 * ------------------
 */

/**
 * The class create database session, manage session
 */
public class TxManager
{
    private static final Object _SYNC_ = new Object();
    private static SessionFactory sf = null;

    private TxSession txSession = null;
    private static Log log = LogFactory.getLog(TxManager.class);

    private TxManager()
    {
    }

    /**
     * The method is introduced to ensure encapsulation. Called will call createInstance method so that
     *
     * @return instance of TxManager
     * @throws com.condo.tx.TxException
     */
    public static TxManager createInstance() throws TxException
    {
        TxManager pm;

        try
        {
            synchronized (_SYNC_)
            {
                if (sf == null)
                {
                    //sf = new Configuration().configure().buildSessionFactory();
                    log.debug("Trying to create connection with the database.");
                    Configuration configuration = new Configuration();
                    configuration.configure("com/condo/config/hibernate.cfg.xml");
                    ServiceRegistryBuilder serviceRegistryBuilder
                            = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
                    sf = configuration.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
                }
            }

            pm = new TxManager();

            Session session = sf.openSession();

            log.info("Connection established");
            pm.txSession = TxSession.getInstance(session);
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("error.tx.initdb", e);
        }

        return pm;
    }

    /**
     * @return instance of TxSession that was created during creation of TxManager instance
     * @throws com.condo.tx.TxException
     */
    public TxSession getTxSession() throws TxException
    {
        if (null == txSession)
        {
            throw new TxException("error.tx.noTxSession");
        }
        return txSession;
    }

    /**
     * Session is closed if session is opened or dirty
     *
     * @throws com.condo.tx.TxException
     */
    public void close() throws TxException
    {
        try
        {
            if (txSession.getSession().isOpen() || txSession.getSession().isDirty())
            {
                txSession.getSession().close();
            }
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("error.tx.close", e);
        }
    }

    /**
     * Open a new Session
     *
     * @throws com.condo.tx.TxException .If already session already opened, exception is thrown
     */
    public void openSession() throws TxException
    {
        try
        {
            if (null != txSession && (txSession.getSession().isOpen()))
            {
                throw new TxException("error.tx.runningSession");
            }
            txSession = TxSession.getInstance(sf.openSession());
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new TxException("error.tx.open", e);
        }
    }
}
