package com.condo.app.control;

import com.condo.app.bean.AppGenericResponse;
import com.condo.profile.ProfileBean;
import com.condo.profile.ProfileDBAccessBL;
import com.condo.tx.TxException;
import com.condo.util.CondoException;
import com.condo.web.ParamWrapper;
import com.condo.web.WebDictionary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * package com.ibbl.app.control;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 27-Apr-16(1:39 PM)
 * Last modification by: $Author: harun $
 * Last modification on $Date: 2016/11/06 03:54:28 $
 * Current revision: $Revision: 1.5 $
 * <p/>
 * Revision History:
 * ------------------
 **/
@Controller
@RequestMapping("/services/condoAppAuth")
public class PasswordServiceControl
{
    private Log log = LogFactory.getLog(this.getClass());

    public PasswordServiceControl()
    {
    }

    @RequestMapping(value = "/updateUserPIN", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public AppGenericResponse updatePasswordExpiry(@RequestParam(value = "userId", required = true) String userId,
                                                   @RequestParam(value = "appId", required = true) String appDeviceId,
                                                   @RequestParam(value = "newPIN", required = true) String newPIN,
                                                   HttpServletRequest request)
    {
        AppGenericResponse response = new AppGenericResponse();
        try
        {
            userId = ParamWrapper.unWrapParamValue(userId);
            appDeviceId = ParamWrapper.unWrapParamValue(appDeviceId);
            newPIN = ParamWrapper.unWrapParamValue(newPIN);
        }
        catch (CondoException e)
        {
            return response;
        }

        try
        {
            ProfileValidator validator = new ProfileValidator();
            ProfileBean profileBean = validator.validateProfile(userId);
            if (profileBean != null)
            {
                log.debug("Password Change Request for User : " + profileBean.getUserID());
                log.debug("Device ID : " + appDeviceId);
                log.debug("New PIN : " + newPIN);
                ProfileDBAccessBL bl = new ProfileDBAccessBL();
                profileBean.setPin(newPIN);
                try
                {
                    bl.updateProfile(profileBean);
                    response.setStatus(WebDictionary.STATUS_SUCCESS);
                }
                catch (TxException ex)
                {
                    response.setNote(ex.getLocalizedMessage());
                    response.setObject(null);
                    return response;
                }
            }
        }
        catch (Exception ex)
        {
            response.setNote("Request from Invalid User. Please contact with administrator");
            response.setObject(null);
            return response;
        }

        return response;
    }
}
