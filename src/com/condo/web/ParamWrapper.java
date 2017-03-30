package com.condo.web;

import com.condo.encrypt.AESPBEncrypt;
import com.condo.util.CondoException;


/**
 * package com.ibbl.app.web;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 02-Mar-16(12:42 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2016/03/02 08:35:46 $
 * Current revision: $Revision: 1.3 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class ParamWrapper
{
    public static String wrapParamValue(String paramValue) throws CondoException
    {
        return new AESPBEncrypt().encrypt(paramValue);
    }


    public static String unWrapParamValue(String encParamValue) throws CondoException
    {
        return new AESPBEncrypt().decrypt(encParamValue);
    }
}
