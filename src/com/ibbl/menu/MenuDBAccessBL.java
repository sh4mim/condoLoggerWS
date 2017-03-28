package com.ibbl.menu;

import com.ibbl.menu.dao.MenuDao;
import com.ibbl.profile.*;
import com.ibbl.profile.dao.ProfileDao;
import com.ibbl.tx.TxController;
import com.ibbl.tx.TxException;


/**
 * package com.ibbl.payProc.bl;
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
public class MenuDBAccessBL
{


    public void saveMenu(MenuBean bean) throws TxException
    {
        TxController txController = TxController.getInstance();
        int txSessionID = txController.initPersistence();
        try
        {
            MenuDao menuDao = new MenuDao(txSessionID);
            menuDao.save(bean);
            txController.commitPersistence(txSessionID);
        }
        catch (Exception ex)
        {
            txController.rollbackPersistence(txSessionID);
        }

        finally
        {
            txController.closeTxSession(txSessionID);
        }
    }


}