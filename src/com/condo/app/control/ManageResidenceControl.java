package com.condo.app.control;

import com.condo.app.bean.AppGenericResponse;
import com.condo.profile.ProfileBean;
import com.condo.residence.bean.ResidenceInfoBean;
import com.condo.residence.bl.ResidenceDBAccessBL;
import com.condo.util.CondoDictionary;
import com.condo.util.CondoException;
import com.condo.visitor.bean.VisitorInfoBean;
import com.condo.visitor.bl.VisitorDBAccessBL;
import com.condo.web.ParamWrapper;
import com.condo.web.WebDictionary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@RequestMapping("/services/manageResidence")
public class ManageResidenceControl
{
    private Log log = LogFactory.getLog(this.getClass());

    public ManageResidenceControl()
    {
    }

    @RequestMapping(value = "/findAllResidence", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public AppGenericResponse updatePasswordExpiry(@RequestParam(value = "userId", required = true) String userId,
                                                   @RequestParam(value = "appId", required = true) String appDeviceId,
                                                   HttpServletRequest request)
    {
        AppGenericResponse response = new AppGenericResponse();
        try
        {
            userId = ParamWrapper.unWrapParamValue(userId);
            appDeviceId = ParamWrapper.unWrapParamValue(appDeviceId);
            log.debug(appDeviceId);
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
                ResidenceDBAccessBL bl = new ResidenceDBAccessBL();
                List<ResidenceInfoBean> residenceInfoBeenList= bl.fetchResidenceByStatus(CondoDictionary.RESIDENCE_STATUS_ACTIVE);
                response.setObject(residenceInfoBeenList);
                response.setStatus(WebDictionary.STATUS_SUCCESS);
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
