package com.condo.menu.dao;

import com.condo.menu.bean.MenuBean;
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
public class MenuDao extends CommonDAOImpl
{


    public MenuDao(int txSessionID) throws TxException
    {
        super(txSessionID);
    }

    public void saveMenu(MenuBean bean) throws TxException
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
    public List<MenuBean> findMenuListByUserType(int userType) throws TxException
    {
        try
        {
            Criteria crit = session.createCriteria(MenuBean.class);
            crit.add(Restrictions.eq("menuType", userType));
            crit.add(Restrictions.eq("status", 1));
            List<MenuBean> menuBeanList = crit.list();
            if (menuBeanList.size() > 0)
            {
                return menuBeanList;
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
