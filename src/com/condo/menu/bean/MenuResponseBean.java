package com.condo.menu.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * package com.ibbl.app.bean;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 11-Feb-16(11:42 AM)
 * Last modification by: $Author: harun $
 * Last modification on $Date: 2016/10/01 07:31:45 $
 * Current revision: $Revision: 1.4 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class MenuResponseBean implements Serializable
{

    protected List<MenuBean> menuList=new ArrayList<MenuBean>();
    protected String firstName;
    protected String lastName;
    protected String welcomeText;

    public List<MenuBean> getMenuList()
    {
        return menuList;
    }

    public void setMenuList(List<MenuBean> menuList)
    {
        this.menuList = menuList;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getWelcomeText()
    {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText)
    {
        this.welcomeText = welcomeText;
    }
}
