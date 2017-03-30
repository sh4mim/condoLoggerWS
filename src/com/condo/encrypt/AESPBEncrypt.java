package com.condo.encrypt;


import com.condo.util.CondoException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

/**
 * package com.ibbl.portal.util.encrypt;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: Administrator
 * Date: 2/24/2016(11:35 AM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2016/03/01 06:02:18 $
 * Current revision: $Revision: 1.2 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class AESPBEncrypt
{
    private static final int pswdIterations = 10;
    private static final int keySize = 128;
    private static final String PASSWORD = "C0nd0S3cr3tK$";
    private static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
    private static final String IV = "12345678901234567890123456789012";
    Cipher cipher;
    SecretKey key;


    public AESPBEncrypt() throws CondoException
    {
        try
        {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(PASSWORD.toCharArray(), Hex.decodeHex(SALT.toCharArray()), pswdIterations, keySize);
            key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        }
        catch (Exception e)
        {
            throw new CondoException("errors.detail", e, "Unable to load AESEncrypt");
        }

    }

    public String encrypt(String plainText) throws CondoException
    {
        try
        {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(Hex.decodeHex(IV.toCharArray())));
            byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes());
            return new Base64().encodeAsString(encryptedTextBytes);
        }
        catch (Exception ex)
        {
            throw new CondoException("errors.detail", ex, "Error encrypting text using AESEncrypt");
        }
    }

    public String decrypt(String encText) throws CondoException
    {
        try
        {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(Hex.decodeHex(IV.toCharArray())));
            byte[] encData = new Base64().decode(encText);
            byte[] decText = cipher.doFinal(encData);
            return new String(decText);
        }
        catch (Exception ex)
        {
            throw new CondoException("errors.detail", ex, "Error decrypting text using AESEncrypt");
        }
    }
}
