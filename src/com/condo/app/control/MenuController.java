package com.condo.app.control;

import com.condo.app.bean.AppGenericResponse;
import com.condo.menu.bean.MenuBean;
import com.condo.menu.bean.MenuResponseBean;
import com.condo.menu.bl.MenuDBAccessBL;
import com.condo.profile.ProfileBean;
import com.condo.profile.ProfileDBAccessBL;
import com.condo.util.CondoException;
import com.condo.web.ParamWrapper;
import com.condo.web.WebDictionary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * package com.ibbl.portal.auth.controller;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: Majhar
 * Date: 23-01-16(2:18 PM)
 * Last modification by: $Author: harun $
 * Last modification on $Date: 2016/11/06 04:13:58 $
 * Current revision: $Revision: 1.27 $
 * <p/>
 * Revision History:
 * ------------------
 */

@Controller
@RequestMapping("/services/condoMenuGenerator")
public class MenuController
{
    private Log log= LogFactory.getLog(this.getClass());
    public MenuController()
    {
    }

    @RequestMapping(value = "/generateMenu", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public AppGenericResponse getLoginSession(@RequestParam(value = "userId", required = true) String userId,
                                            HttpServletRequest request)
    {
        AppGenericResponse response = new AppGenericResponse();
        response.setStatus(WebDictionary.STATUS_FAIL);
        MenuResponseBean menuResponseBean=new MenuResponseBean();
        int userType=0;

        try
        {
            userId = ParamWrapper.unWrapParamValue(userId);
        }
        catch (CondoException e)
        {
            return response;
        }

        try
        {
            ProfileDBAccessBL bl = new ProfileDBAccessBL();
            ProfileBean profileBean = bl.findProfileByUserId(userId);
            if (profileBean != null)
            {
                menuResponseBean.setFirstName(profileBean.getFirstName());
                menuResponseBean.setLastName(profileBean.getLastName());
                menuResponseBean.setWelcomeText(null);
                userType=profileBean.getUserType();
            }

        }
        catch (Exception ex)
        {
            response.setNote("Invalid User Request. Please contact with administrator");
            response.setObject(null);
            return response;
        }

        try
        {
            MenuDBAccessBL bl = new MenuDBAccessBL();
            List<MenuBean> menuBeanList = bl.findMenuByUserType(userType);
            if (menuBeanList != null && menuBeanList.size() > 0)
            {
                response.setStatus(WebDictionary.STATUS_SUCCESS);
                response.setNote(null);
                menuResponseBean.setMenuList(menuBeanList);
                response.setObject(menuResponseBean);
            }

            else
            {
                response.setNote("Menu Not found for the requested user.");
                response.setObject(menuResponseBean);
            }

        }
        catch (Exception ex)
        {
            response.setNote("Fail to Fetch Menu information from Server.");
            response.setObject(null);
        }
        log.debug(response.toString());
        return response;
    }

}
