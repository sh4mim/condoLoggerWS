package com.condo.residence.bl;

import com.condo.residence.bean.ResidenceInfoBean;
import com.condo.residence.dao.ResidenceInfoDao;
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
public class ResidenceDBAccessBL
{



    public List<ResidenceInfoBean> fetchResidenceByStatus(int status) throws TxException
    {
        TxController txController = TxController.getInstance();
        List<ResidenceInfoBean> residenceInfoBeanList=null;
        int txSessionID = txController.initPersistence();
        try
        {
            ResidenceInfoDao residenceInfoDao = new ResidenceInfoDao(txSessionID);
            residenceInfoBeanList = residenceInfoDao.findResidenceByStatus(status);
            txController.commitPersistence(txSessionID);
            return residenceInfoBeanList;
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