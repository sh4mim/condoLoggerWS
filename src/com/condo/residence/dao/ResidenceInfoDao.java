package com.condo.residence.dao;

import com.condo.menu.bean.MenuBean;
import com.condo.residence.bean.ResidenceInfoBean;
import com.condo.tx.TxException;
import com.condo.tx.dao.CommonDAOImpl;
import com.condo.visitor.bean.VisitorInfoBean;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * package com.condo.payProc.dl.dao;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: parvege
 * Date: 9/2/13(4:38 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/08/03 05:38:20 $
 * Current revision: $Revision: 1.2 $
 * <p/>
 * Revision History:
 * ------------------
 */
public class ResidenceInfoDao extends CommonDAOImpl
{


    public ResidenceInfoDao(int txSessionID) throws TxException
    {
        super(txSessionID);
    }

    public void ResidenceInfo(ResidenceInfoBean bean) throws TxException
    {
        try
        {
            super.save(bean);
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
    }
    public List<ResidenceInfoBean> findResidenceByStatus(int status) throws TxException
    {
        try
        {
            Criteria crit = session.createCriteria(ResidenceInfoBean.class);
            crit.add(Restrictions.eq("status", status));
            List<ResidenceInfoBean> residenceInfoBeenList = crit.list();
            if (residenceInfoBeenList.size() > 0)
            {
                return residenceInfoBeenList;
            }
            else
            {
                return null;
            }
        }
        catch (HibernateException e)
        {
            throw new TxException("Fail to Fetch Residence information. ");
        }
    }


}
