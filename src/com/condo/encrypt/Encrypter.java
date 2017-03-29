package com.condo.encrypt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
 * Encrypter.java
 * Copyright (C) 2002-2006 Islami Bank Bangladesh Limited
 *
 * Original author: himu
 * Created on:      Aug 24, 2006 (1:08:00 PM)
 *
 * Last modification by: $Author: parvege $
 * Last modification on: $Date: 2015/12/06 10:10:05 $
 * Current revision:     $Revision: 1.1.1.1 $
 *
 */

public class Encrypter
{
    private static Encrypter encrypter = new Encrypter();
    private static Log log = LogFactory.getLog(Encrypter.class);

    private String ALGO = "";
    public static final String ENCRYPT_MD5 = "MD5";
    public static final String ENCRYPT_SHA_1 = "SHA-1";
    public static final String ENCRYPT_SHA_256 = "SHA-256";
    public static final String ENCRYPT_SHA_384 = "SHA-384";
    public static final String ENCRYPT_SHA_512 = "SHA-512";

    private Encrypter()
    {
    }

    /**
     * @param algorithm
     * @return
     */
    public static Encrypter getEncrypter(String algorithm)
    {
        encrypter.ALGO = algorithm;
        return encrypter;
    }


    public static String encryptOneWay(String source)
    {
        StringBuffer codesb = new StringBuffer();

        try
        {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] array = digest.digest(source.getBytes());
            for (byte anArray : array)
            {
                int b = anArray & 0xFF;
                if (b < 0x10) codesb.append('0');
                codesb.append(Integer.toHexString(b));
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return codesb.toString();
    }


}
