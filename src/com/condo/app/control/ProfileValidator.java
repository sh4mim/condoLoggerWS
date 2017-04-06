package com.condo.app.control;

import com.condo.profile.ProfileBean;
import com.condo.profile.ProfileDBAccessBL;

/**
 * package com.condo.app.control;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: Administrator
 * Date: 4/2/2017(4:48 PM)
 * Last modification by: $Author: Administrator $
 * Last modification on $Date: 4/2/2017 (4:48 PM) $
 * Current revision: $Revision: 1.1 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class ProfileValidator
{
    public ProfileBean validateProfile(String userId)
    {
        try
        {
            ProfileDBAccessBL bl = new ProfileDBAccessBL();
            ProfileBean profileBean = bl.findProfileByUserId(userId);
            if (profileBean != null)
            {
                return profileBean;
            }

        }
        catch (Exception ex)
        {
            return null;
        }
        return null;
    }

}
