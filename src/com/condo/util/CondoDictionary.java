package com.condo.util;

import java.io.Serializable;

/**
 * package com.condo.util;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: Administrator
 * Date: 4/17/2017(12:26 PM)
 * Last modification by: $Author: Administrator $
 * Last modification on $Date: 4/17/2017 (12:26 PM) $
 * Current revision: $Revision: 1.1 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class CondoDictionary implements Serializable
{
    public static int VISITOR_STATUS_ENQUEUED = 8;
    public static int VISITOR_STATUS_ACCEPTED = 1;
    public static int VISITOR_STATUS_REJECTED = 2;
    public static int VISITOR_STATUS_FAIL_TO_VERIFY = 3;
    public static int RESIDENCE_STATUS_ACTIVE = 1;
}
