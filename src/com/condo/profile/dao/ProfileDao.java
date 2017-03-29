package com.condo.profile.dao;

import com.condo.profile.ProfileBean;
import com.condo.tx.TxException;
import com.condo.tx.dao.CommonDAOImpl;
import org.hibernate.HibernateException;

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


}
