package com.ibbl.tx;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: Jul 30, 2009(5:39:10 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/06/18 17:15:50 $
 * Current revision: $Revision: 1.2 $
 * <p/>
 * Revision History:
 * ------------------
 */
public class TxController
{
    /**
     * Stores TxSession in HashMap with txID
     */
    private static HashMap<Integer, TxSession> txSessionMap = new HashMap<Integer, TxSession>();

    private static final Object _SYNC_ = new Object();

    protected TxManager txManager = null;

    private static Logger log = LogManager.getLogger(TxController.class);

    private TxController()
    {

    }

    /**
     * @return instance of TxController
     */
    public static TxController getInstance()
    {
        return new TxController();
    }

    /**
     * @return Transaction Session ID
     * @throws com.ibbl.tx.TxException contains error/notification
     */
    public int initPersistence() throws TxException
    {
        synchronized (_SYNC_)
        {
            if (null == txManager)
            {
                txManager = TxManager.createInstance();
            }
            else
            {
                txManager.openSession();
            }
        }

        TxSession txSession = txManager.getTxSession();
        int sessionID = txSession.beginTx();
        txSessionMap.put(sessionID, txSession);

        log.info("Persistence initialized");

        return sessionID;
    }

    /**
     * This method commits transaction
     *
     * @param txSessionID transaction session ID
     * @throws com.ibbl.tx.TxException contains error message
     */
    public void commitPersistence(int txSessionID) throws TxException
    {
        synchronized (_SYNC_)
        {
            try
            {
                TxSession txSession = getTxSession(txSessionID);
                txSession.commitTx();
                log.info("Tx commited for tx session ID : " + txSessionID);

                // session must be closed in this method. because this is last point of commit or rollback tx.
                // Following code will be executed only no exception occured during commit/roleback.
                // If any exception occurs, system will go catch block, execute closeTxSession() and
                // finally will throw the exception.
//                closeTxSession(txSessionID);
            }
            catch (TxException e)
            {
                log.error("System failed to commit tx for tx id:" + txSessionID);
                //close session is not required to call. That will be done by caller by calling rollback;
                throw e;

            }
        }
    }

    /**
     * Discard the transactions and rollback
     *
     * @param txSessionID transaction session ID
     * @throws com.ibbl.tx.TxException contains error message
     */
    public void rollbackPersistence(int txSessionID) throws TxException
    {
        synchronized (_SYNC_)
        {
            try
            {
                TxSession txSession = getTxSession(txSessionID);

                txSession.rollbackTx();
                log.info("Tx is rollbacked for tx session ID : " + txSessionID);

                // session must be closed in this method. because this is last point of commit or rollback tx.
                // Following code will be executed only no exception occured during commit/roleback.
                // If any exception occurs, system will go catch block, execute closeTxSession() and
                // finally will throw the exception.
//                closeTxSession(txSessionID);
            }
            catch (TxException e)
            {
//                closeTxSession(txSessionID);

                throw e;
            }
            catch (Exception e)
            {
//                closeTxSession(txSessionID);
            }
        }
    }

    /**
     * @param txSessionID i.e. Transaction session ID that is unique
     * @return TxSession for param tx Session ID
     * @throws com.ibbl.tx.TxException returns error message if any
     */
    public static TxSession getTxSession(int txSessionID) throws TxException
    {
        if (txSessionMap.get(txSessionID) != null)
            return txSessionMap.get(txSessionID);
        else
            throw new TxException("error.tx.session.notFound");
    }

    /**
     * The method close the session.
     *
     * @param txSessionID i.e. Transaction session ID that is unique
     * @throws com.ibbl.tx.TxException Returns error message if any.
     */
    public void closeTxSession(int txSessionID) throws TxException
    {
        if (txSessionMap.get(txSessionID) != null)
        {
            TxSession txSession = txSessionMap.get(txSessionID);
            if (txSession.getSession().isOpen())
            {
                txSession.getSession().close();
            }
            else if (txSession.getSession().isDirty())
            {
                txSession.getSession().close();
            }

            txSessionMap.remove(txSessionID);
            log.info("TxSession closed successfully for tx Session ID: " + txSessionID);
        }
        else
            throw new TxException("error.tx.session.notFound");
    }

}
