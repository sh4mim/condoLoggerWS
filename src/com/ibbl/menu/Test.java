package com.ibbl.menu;



import com.ibbl.encrypt.Encrypter;
import com.ibbl.profile.ProfileBean;
import com.ibbl.profile.ProfileDBAccessBL;
import com.ibbl.tx.TxException;

/**
 * Created by uzzal on 09-Feb-17.
 */
public class Test
{
    public static void main(String[] args)
    {
        ProfileBean profile=new ProfileBean();
        profile.setProfileID(100);
        profile.setUserID("uzzalkuet@gmail.com");
        profile.setPassWD(Encrypter.encryptOneWay("pass123"));
        profile.setStatus(1);
        ProfileDBAccessBL bl=new ProfileDBAccessBL();
        try
        {
            bl.saveProfile(profile);
        }
        catch (TxException e)
        {
            e.printStackTrace();
        }
    }
}
