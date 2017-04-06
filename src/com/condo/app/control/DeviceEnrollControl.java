package com.condo.app.control;

import com.condo.app.bean.AppGenericResponse;
import com.condo.profile.ProfileBean;
import com.condo.util.CondoException;
import com.condo.web.ParamWrapper;
import com.condo.web.WebDictionary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * package com.ibbl.app.control;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 02-Feb-16(4:48 PM)
 * Last modification by: $Author: harun $
 * Last modification on $Date: 2016/11/06 03:54:28 $
 * Current revision: $Revision: 1.20 $
 * <p/>
 * Revision History:
 * ------------------
 **/
@Controller
@RequestMapping("/services/appEnroll")
public class DeviceEnrollControl
{
    private Log log = LogFactory.getLog(this.getClass());

    @RequestMapping(value = "/initAppEnroll", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public AppGenericResponse initAppEnroll(@RequestParam(value = "userId", required = true) String userId,
                                            @RequestParam(value = "appId", required = true) String appId)
    {
        AppGenericResponse response = new AppGenericResponse();
        response.setStatus(WebDictionary.STATUS_FAIL);

        try
        {
            userId = ParamWrapper.unWrapParamValue(userId);
            appId = ParamWrapper.unWrapParamValue(appId);
        }
        catch (CondoException e)
        {
            return response;
        }


        try
        {
            ProfileValidator validator=new ProfileValidator();
            ProfileBean profileBean = validator.validateProfile(userId);
            if (profileBean != null)
            {
                log.debug("Init Enroll request for: " + profileBean.getUserID());
                response.setStatus(WebDictionary.STATUS_SUCCESS);

            }
        }
        catch (Exception ex)
        {
            response.setNote("Invalid User Request. Please contact with administrator");
            response.setObject(null);
            return response;
        }


//        try
//        {
//            String token = SysInitializer.getInstance().getTokenVerifier().
//                    generateToken(profile.getProfileID() + "-appEnrollCode");
//            String smsText = SMSTemplateHandler.getInstance().templateAppDeviceEnrollCode(token);
//
//
//            SMSController smsController = new SMSController();
//            smsController.sendSingleText(profile.getUserDetail().getPhoneNo(), smsText);
//
//            statusBean.setEnrollStatus(EnrollStatusBean.STATUS_SUCCESS);
//        }
//        catch (Exception e)
//        {
//            log.error(this.getClass().getName(), e);
//            return statusBean;
//        }
//
        return response;
    }

    @RequestMapping(value = "/enqueueAppEnroll", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public AppGenericResponse enqueueAppEnroll(@RequestParam(value = "userId", required = true) String userId,
                                             @RequestParam(value = "appId", required = true) String appDeviceId,
                                             @RequestParam(value = "otp", required = true) String otp,
                                             @RequestParam(value = "model", required = true) String model,
                                             @RequestParam(value = "manufacturer", required = true) String manufacturer
                                            )
    {
        AppGenericResponse response = new AppGenericResponse();
        response.setStatus(WebDictionary.STATUS_FAIL);

        try
        {
            userId = ParamWrapper.unWrapParamValue(userId);
            appDeviceId = ParamWrapper.unWrapParamValue(appDeviceId);
            otp = ParamWrapper.unWrapParamValue(otp);
            model = ParamWrapper.unWrapParamValue(model);
            manufacturer = ParamWrapper.unWrapParamValue(manufacturer);
        }
        catch (CondoException e)
        {
            return response;
        }

        try
        {
            ProfileValidator validator=new ProfileValidator();
            ProfileBean profileBean = validator.validateProfile(userId);
            if (profileBean != null)
            {
                log.debug("Init Enroll request for: " + profileBean.getUserID());
                log.debug("App ID : "+appDeviceId);
                log.debug("OTP : "+otp);
                log.debug("Model : "+model);
                log.debug("Manufacturer : "+manufacturer);
                response.setStatus(WebDictionary.STATUS_SUCCESS);

            }
        }
        catch (Exception ex)
        {
            response.setNote("Invalid User Request. Please contact with administrator");
            response.setObject(null);
            return response;
        }
        return response;

    }
//
//
//    @RequestMapping(value = "/resendActivation", method = RequestMethod.GET,
//            headers = "Accept=application/json", produces = {"application/json"})
//    @ResponseBody
//    public EnrollStatusBean resendActivation(@RequestParam(value = "userId", required = true) String userId,
//                                             @RequestParam(value = "appId", required = true) String appDeviceId)
//    {
//        EnrollStatusBean statusBean = new EnrollStatusBean();
//        statusBean.setEnrollStatus(EnrollStatusBean.STATUS_FAIL);
//
//        Profile profile = null;
//        try
//        {
//            userId = ParamWrapper.unWrapParamValue(userId);
//            appDeviceId = ParamWrapper.unWrapParamValue(appDeviceId);
//
//            profile = super.init(userId);
//        }
//        catch (PortalException e)
//        {
//            statusBean.setNote("Profile is not in active status");
//            return statusBean;
//        }
//
//        ProfileDeviceInfo profileDeviceInfo = null;
//        try
//        {
//            profileDeviceInfo = fetchProfileDeviceInfoByDeviceId(profile.getProfileID(), appDeviceId);
//        }
//        catch (PortalException e)
//        {
//            statusBean.setNote("Device info not found");
//            return statusBean;
//        }
//        if (profileDeviceInfo == null)
//        {
//            statusBean.setNote("Device info is not enrolled");
//            return statusBean;
//        }
//
//        try
//        {
//            //Send activation code
//            String token = SysInitializer.getInstance().getTokenVerifier().generateToken(profile.getProfileID() +
//                    "-activeDevice");
//            MailMan mailer = MailMan.getInstance();
//            if (!mailer.activateDeviceOTP(profile, token))
//            {
//                statusBean.setNote("Failed to send activation code. Please try again.");
//                return statusBean;
//            }
//
//            statusBean.setEnrollStatus(EnrollStatusBean.STATUS_SUCCESS);
//        }
//        catch (PortalException e)
//        {
//            statusBean.setNote("Device Activation failed. "
//                    + StrutsMessageUtil.getResourceMessage(e.getMessage(), e.getParams()));
//            return statusBean;
//        }
//
//        return statusBean;
//    }
//
//
//    @RequestMapping(value = "/checkAppEnroll", method = RequestMethod.GET,
//            headers = "Accept=application/json", produces = {"application/json"})
//    @ResponseBody
//    public EnrollStatusBean checkAppEnroll(@RequestParam(value = "userId", required = true) String userId,
//                                           @RequestParam(value = "appId", required = true) String appDeviceId,
//                                           @RequestParam(value = "activationCode", required = true) String activationCode)
//    {
//        EnrollStatusBean statusBean = new EnrollStatusBean();
//        statusBean.setEnrollStatus(EnrollStatusBean.STATUS_FAIL);
//
//        Profile profile = null;
//        try
//        {
//            userId = ParamWrapper.unWrapParamValue(userId);
//            appDeviceId = ParamWrapper.unWrapParamValue(appDeviceId);
//            activationCode = ParamWrapper.unWrapParamValue(activationCode).trim();
//            profile = super.init(userId);
//        }
//        catch (PortalException e)
//        {
//            statusBean.setNote("Profile is not in active status");
//            return statusBean;
//        }
//
//        try
//        {
//            ProfileDeviceInfo profileDeviceInfo = fetchProfileDeviceInfoByDeviceId(profile.getProfileID(), appDeviceId);
//            if (profileDeviceInfo == null)
//            {
//                statusBean.setNote("Device info is not enrolled");
//            }
//            else
//            {
//                if (profileDeviceInfo.getStatus() == ProfileDeviceInfo.APP_DEVICE_STATUS_ACTIVE)
//                {
//                    statusBean.setEnrollStatus(EnrollStatusBean.STATUS_SUCCESS);
//                    return statusBean;
//                }
//            }
//
//            String tokenKey = profile.getProfileID() + "-activeDevice";
//            if (!SysInitializer.getInstance().getTokenVerifier().verifyToken(tokenKey, activationCode))
//            {
//                statusBean.setNote("Activation code doesn't match");
//                return statusBean;
//            }
//
//            activateDevice(profile, appDeviceId);
//            statusBean.setEnrollStatus(EnrollStatusBean.STATUS_SUCCESS);
//        }
//        catch (PortalException e)
//        {
//            statusBean.setNote("Device Activation failed. "
//                    + StrutsMessageUtil.getResourceMessage(e.getMessage(), e.getParams()));
//            return statusBean;
//        }
//
//        return statusBean;
//    }
//
//    /**
//     * Activate device
//     *
//     * @param profile
//     * @param deviceId
//     * @return
//     * @throws PortalException
//     */
//    public Profile activateDevice(Profile profile, String deviceId) throws PortalException
//    {
//        boolean isDeviceEnrolled = Boolean.FALSE;
//
//        if (profile.getDeviceInfos() != null)
//        {
//            for (ProfileDeviceInfo profileDeviceInfo : profile.getDeviceInfos())
//            {
//                if (profileDeviceInfo.getDeviceId().equals(deviceId))
//                {
//                    profileDeviceInfo.setStatus(ProfileDeviceInfo.APP_DEVICE_STATUS_ACTIVE);
//                    isDeviceEnrolled = Boolean.TRUE;
//                }
//                else
//                {
//                    profileDeviceInfo.setStatus(ProfileDeviceInfo.APP_DEVICE_STATUS_DEACTIVE);
//                }
//            }
//        }
//
//        if (!isDeviceEnrolled)
//        {
//            throw new PortalException("errors.detail", "Device is not in enroll status. Please contact with administrator");
//        }
//
//        profile = assignAppPrivilege(profile);
//        try
//        {
//            ProfileManagementController pmc = new ProfileManagementController();
//            pmc.updateProfile(profile);
//        }
//        catch (Exception e)
//        {
//            throw new PortalException("errors.detail", e, "error while activating app device");
//        }
//
//        return profile;
//    }
//
//
//    /**
//     * Assign apps privileges to profile
//     *
//     * @param profile
//     * @return
//     * @throws PortalException
//     */
//    private Profile assignAppPrivilege(Profile profile) throws PortalException
//    {
//
//        TxControl txc = null;
//        List<Privilege> privilegesForApps = null;
//
//        try
//        {
//            txc = initPersistence();
//            PrivilegeDAO privilegeDAO = new PrivilegeDAO(persister.getTxSession());
//            privilegesForApps = privilegeDAO.getPrivListByFeatureType(Feature.FEATURE_TYPE_ICELLULAR);
//
//            txc.commitTx();
//        }
//        catch (PortalException e)
//        {
//            safeRollback(txc, e);
//        }
//        finally
//        {
//            endPersistence();
//        }
//
//        List<String> profilePrivilegeIdList = new ArrayList<String>();
//        if (profile.getPrivileges() != null)
//        {
//            for (ProfilePrivilege profilePrivilege : profile.getPrivileges())
//            {
//                profilePrivilegeIdList.add(String.valueOf(profilePrivilege.getPrivilegeID()));
//            }
//        }
//
//        if (privilegesForApps != null)
//        {
//            for (Privilege appPrivilege : privilegesForApps)
//            {
//                if (!profilePrivilegeIdList.contains(String.valueOf(appPrivilege.getId())))
//                {
//                    ProfilePrivilege profilePrivilege = new ProfilePrivilege();
//                    profilePrivilege.setPrivilegeID(appPrivilege.getId());
//                    profilePrivilege.setProfile(profile);
//                    profile.getPrivileges().add(profilePrivilege);
//                }
//            }
//        }
//
//        return profile;
//
//    }
//
//
//    private ProfileDeviceInfo fetchProfileDeviceInfoByDeviceId(Long profileId, String deviceId) throws PortalException
//    {
//        TxControl txc = null;
//
//        txc = initPersistence();
//
//        try
//        {
//            ProfileDeviceDao profileDeviceDao = new ProfileDeviceDao(persister.getTxSession());
//            ProfileDeviceInfo deviceInfo = profileDeviceDao.fetchProfileDeviceInfoByDeviceId(profileId, deviceId);
//
//            txc.commitTx();
//            return deviceInfo;
//        }
//        catch (PortalException e)
//        {
//            safeRollback(txc, e);
//        }
//        finally
//        {
//            endPersistence();
//        }
//
//        return null;
//    }

}
