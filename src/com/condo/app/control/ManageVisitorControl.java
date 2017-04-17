package com.condo.app.control;

import com.condo.app.bean.AppGenericResponse;
import com.condo.profile.ProfileBean;
import com.condo.profile.ProfileDBAccessBL;
import com.condo.util.CondoDictionary;
import com.condo.util.CondoException;
import com.condo.visitor.bean.VisitorInfoBean;
import com.condo.visitor.bl.VisitorDBAccessBL;
import com.condo.web.ParamWrapper;
import com.condo.web.WebDictionary;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@RequestMapping("/services/manageVisitor")
public class ManageVisitorControl
{
    private Log log = LogFactory.getLog(this.getClass());

    public ManageVisitorControl()
    {
    }

    @RequestMapping(value = "/entryNewVisitor", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public AppGenericResponse updatePasswordExpiry(@RequestParam(value = "userId", required = true) String userId,
                                                   @RequestParam(value = "appId", required = true) String appDeviceId,
                                                   @RequestParam(value = "name", required = true) String name,
                                                   @RequestParam(value = "phoneNo", required = true) String phoneNo,
                                                   @RequestParam(value = "nid", required = true) String nid,
                                                   @RequestParam(value = "vehicleNo", required = true) String vehicleNo,
                                                   @RequestParam(value = "expectedGuest", required = true) String expectedGuest,
                                                   @RequestParam(value = "possibleTime", required = true) String possibleTime,
                                                   @RequestParam(value = "imageUrl", required = true) String imageUrl,
                                                   HttpServletRequest request)
    {
        AppGenericResponse response = new AppGenericResponse();
        try
        {
            userId = ParamWrapper.unWrapParamValue(userId);
            appDeviceId = ParamWrapper.unWrapParamValue(appDeviceId);
            name = ParamWrapper.unWrapParamValue(name);
            phoneNo = ParamWrapper.unWrapParamValue(phoneNo);
            nid = ParamWrapper.unWrapParamValue(nid);
            vehicleNo = ParamWrapper.unWrapParamValue(vehicleNo);
            expectedGuest = ParamWrapper.unWrapParamValue(expectedGuest);
            possibleTime = ParamWrapper.unWrapParamValue(possibleTime);
            imageUrl = ParamWrapper.unWrapParamValue(imageUrl);
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
                VisitorInfoBean bean = new VisitorInfoBean();
                bean.setName(name);
                bean.setPhoneNo(phoneNo);
                bean.setNid(nid);
                bean.setVehicleNo(vehicleNo);
                bean.setExpectedGuest(Integer.valueOf(expectedGuest));
                bean.setVisitingTo(userId);
                bean.setImageUrl(imageUrl);
                bean.setStatus(CondoDictionary.VISITOR_STATUS_ENQUEUED);
                VisitorDBAccessBL bl = new VisitorDBAccessBL();
//                bean.setPossibleTime(DateUtil.parseDate(possibleTime,DateUtil.);
                bl.saveVisitor(bean);
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
