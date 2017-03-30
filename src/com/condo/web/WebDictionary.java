package com.condo.web;

import java.io.Serializable;

/**
 * package com.ibbl.app.bean;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 03-Feb-16(10:49 AM)
 * Last modification by: $Author: majhar $
 * Last modification on $Date: 2017/01/01 10:43:38 $
 * Current revision: $Revision: 1.3 $
 * <p/>
 * Revision History:
 * ------------------
 */

public class WebDictionary implements Serializable
{
    public static int STATUS_ACTIVE = 1;
    public static int STATUS_SUCCESS = 1;
    public static int STATUS_FAIL = 0;

    public static int MENU_TYPE_TOP = 1;
    public static int MENU_TYPE_FOOTER = 2;

    public static int MENU_ID_PARENT = 0;
}
