package com.condo.visitor.bl;

import com.condo.menu.bean.MenuBean;
import com.condo.menu.dao.MenuDao;
import com.condo.tx.TxController;
import com.condo.tx.TxException;
import com.condo.visitor.bean.VisitorInfoBean;
import com.condo.visitor.dao.VisitorInfoDao;

import java.util.List;


/**
 * package com.condo.payProc.bl;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: parvege
 * Date: 9/2/13(4:38 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/08/03 05:38:20 $
 * Current revision: $Revision: 1.3 $
 * <p/>
 * Revision History:
 * ------------------
 */
public class VisitorDBAccessBL
{


    public void saveVisitor(VisitorInfoBean bean) throws TxException
    {
        TxController txController = TxController.getInstance();
        int txSessionID = txController.initPersistence();
        try
        {
            VisitorInfoDao visitorInfoDao = new VisitorInfoDao(txSessionID);
            visitorInfoDao.save(bean);
            txController.commitPersistence(txSessionID);
        }
        catch (Exception ex)
        {
            txController.rollbackPersistence(txSessionID);
            throw new TxException("Fail to insert visitor information.");
        }

        finally
        {
            txController.closeTxSession(txSessionID);
        }
    }

    public void updateVisitorInfo(long oid) throws TxException
    {
        TxController txc = TxController.getInstance();
        int txID = txc.initPersistence();
        VisitorInfoDao visitorInfoDao=new VisitorInfoDao(txID);
        try
        {
            visitorInfoDao.updateVisitorInfo(oid);
            txc.commitPersistence(txID);
        }

        catch (TxException e)
        {
            txc.rollbackPersistence(txID);
            throw new TxException(e.getMessage());
        }
        finally
        {
            txc.closeTxSession(txID);
        }
    }

    public List<VisitorInfoBean> findVisitorInfoByStatus(String dwellerID,int status) throws TxException
    {
        TxController txController = TxController.getInstance();
        List<VisitorInfoBean> visitorInfoBeanList=null;
        int txSessionID = txController.initPersistence();
        try
        {
            VisitorInfoDao visitorInfoDao = new VisitorInfoDao(txSessionID);
            visitorInfoBeanList = visitorInfoDao.findVisitorInfoByStatus(dwellerID,status);
            txController.commitPersistence(txSessionID);
            return visitorInfoBeanList;
        }
        catch (Exception ex)
        {
            txController.rollbackPersistence(txSessionID);
            throw new TxException(ex.getLocalizedMessage());
        }

        finally
        {
            txController.closeTxSession(txSessionID);
        }

    }

    public List<VisitorInfoBean> findAllGuestList() throws TxException
    {
        TxController txController = TxController.getInstance();
        List<VisitorInfoBean> visitorInfoBeanList=null;
        int txSessionID = txController.initPersistence();
        try
        {
            VisitorInfoDao visitorInfoDao = new VisitorInfoDao(txSessionID);
            visitorInfoBeanList = visitorInfoDao.findAllGuestList();
            txController.commitPersistence(txSessionID);
            return visitorInfoBeanList;
        }
        catch (Exception ex)
        {
            txController.rollbackPersistence(txSessionID);
            throw new TxException(ex.getLocalizedMessage());
        }

        finally
        {
            txController.closeTxSession(txSessionID);
        }

    }



}