package com.ibbl.menu;

import java.io.Serializable;


public class MenuBean implements Serializable
{
    private int menuID;
    private int menuOrder;
    private String menuText;
    private String menuLink;
    private int status;
    private String rAppVersion;
    private int parentID;
    private String iconClass;
    private int isPopMenu;
    private int menuType;

    public int getMenuID()
    {
        return menuID;
    }

    public void setMenuID(int menuID)
    {
        this.menuID = menuID;
    }

    public int getMenuOrder()
    {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder)
    {
        this.menuOrder = menuOrder;
    }

    public String getMenuText()
    {
        return menuText;
    }

    public void setMenuText(String menuText)
    {
        this.menuText = menuText;
    }

    public String getMenuLink()
    {
        return menuLink;
    }

    public void setMenuLink(String menuLink)
    {
        this.menuLink = menuLink;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getrAppVersion()
    {
        return rAppVersion;
    }

    public void setrAppVersion(String rAppVersion)
    {
        this.rAppVersion = rAppVersion;
    }

    public int getParentID()
    {
        return parentID;
    }

    public void setParentID(int parentID)
    {
        this.parentID = parentID;
    }

    public String getIconClass()
    {
        return iconClass;
    }

    public void setIconClass(String iconClass)
    {
        this.iconClass = iconClass;
    }

    public int getIsPopMenu()
    {
        return isPopMenu;
    }

    public void setIsPopMenu(int isPopMenu)
    {
        this.isPopMenu = isPopMenu;
    }

    public int getMenuType()
    {
        return menuType;
    }

    public void setMenuType(int menuType)
    {
        this.menuType = menuType;
    }
}
