package com.ibbl.menu.dao;

import com.ibbl.menu.MenuBean;
import com.ibbl.profile.ProfileBean;
import com.ibbl.tx.TxException;
import com.ibbl.tx.dao.CommonDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * package com.ibbl.payProc.dl.dao;
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


}
