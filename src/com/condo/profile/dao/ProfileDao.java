package com.condo.profile.dao;

import com.condo.menu.bean.MenuBean;
import com.condo.profile.ProfileBean;
import com.condo.tx.TxException;
import com.condo.tx.dao.CommonDAOImpl;
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
public class ProfileDao extends CommonDAOImpl
{


    public ProfileDao(int txSessionID) throws TxException
    {
        super(txSessionID);
    }

    public void saveProfile(ProfileBean bean) throws TxException
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

    public ProfileBean findProfileByUserId(String userID) throws TxException
    {
        try
        {
            Criteria crit = session.createCriteria(ProfileBean.class);
            crit.add(Restrictions.eq("userID", userID));
            crit.add(Restrictions.eq("status", 1));
            return (ProfileBean) crit.uniqueResult();

        }
        catch (HibernateException e)
        {
            throw new TxException("Fail to Fetch menu information. ");
        }
    }


}
