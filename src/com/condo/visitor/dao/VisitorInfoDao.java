package com.condo.visitor.dao;

import com.condo.menu.bean.MenuBean;
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
public class VisitorInfoDao extends CommonDAOImpl
{


    public VisitorInfoDao(int txSessionID) throws TxException
    {
        super(txSessionID);
    }

    public void SaveVisitorInfo(VisitorInfoBean bean) throws TxException
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
    public List<VisitorInfoBean> findVisitorInfoByStatus(int status) throws TxException
    {
        try
        {
            Criteria crit = session.createCriteria(VisitorInfoBean.class);
            crit.add(Restrictions.eq("status", status));
            List<VisitorInfoBean> visitorInfoBeanList = crit.list();
            if (visitorInfoBeanList.size() > 0)
            {
                return visitorInfoBeanList;
            }
            else
            {
                return null;
            }
        }
        catch (HibernateException e)
        {
            throw new TxException("Fail to Fetch menu information. ");
        }
    }


}
