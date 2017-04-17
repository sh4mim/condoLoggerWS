package com.condo.profile;

import com.condo.menu.bean.MenuBean;
import com.condo.menu.dao.MenuDao;
import com.condo.profile.dao.ProfileDao;
import com.condo.tx.TxController;
import com.condo.tx.TxException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class ProfileDBAccessBL
{

    private Log log = LogFactory.getLog(this.getClass());

    public void saveProfile(ProfileBean profileBean) throws TxException
    {
        TxController txController = TxController.getInstance();
        int txSessionID = txController.initPersistence();
        try
        {
            ProfileDao profileDao = new ProfileDao(txSessionID);
            profileDao.save(profileBean);
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

    public void updateProfile(ProfileBean profileBean) throws TxException
    {
        TxController txController = TxController.getInstance();
        int txSessionID = txController.initPersistence();
        try
        {
            log.debug("Profile update called");
            ProfileDao profileDao = new ProfileDao(txSessionID);
            profileDao.update(profileBean);

            txController.commitPersistence(txSessionID);
        }
        catch (Exception ex)
        {
            log.error("Profile update error : ,"+ ex );
            txController.rollbackPersistence(txSessionID);
        }

        finally
        {
            txController.closeTxSession(txSessionID);
        }
    }

    public ProfileBean findProfileByUserId(String userID) throws TxException
    {
        TxController txController = TxController.getInstance();
        ProfileBean returnBean = null;
        int txSessionID = txController.initPersistence();
        try
        {
            ProfileDao profileDao = new ProfileDao(txSessionID);
            returnBean = profileDao.findProfileByUserId(userID);
            txController.commitPersistence(txSessionID);
            return returnBean;
        }
        catch (Exception ex)
        {
            txController.rollbackPersistence(txSessionID);
            throw new TxException("Fail to Fetch Profile Information. ");
        }

        finally
        {
            txController.closeTxSession(txSessionID);
        }

    }


}