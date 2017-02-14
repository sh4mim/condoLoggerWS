package com.ibbl.services.oAuth2;

import com.hashing.password.demo.bcrypt.BCrypt;
import com.ibbl.encrypt.Encrypter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PortalServicePwdEncoder extends BCryptPasswordEncoder
{
    private Log log = LogFactory.getLog(this.getClass());
    private PortalServiceUserDetailsService userDetailsService;

    public PortalServicePwdEncoder()
    {
    }

    public PortalServiceUserDetailsService getUserDetailsService()
    {
        return userDetailsService;
    }

    public void setUserDetailsService(PortalServiceUserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }


    public PortalServicePwdEncoder(PortalServiceUserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String encode(CharSequence rawPassword)
    {
        if (userDetailsService != null && userDetailsService.getPortalServiceUserDetails() != null)
        {
            return BCrypt.hashpw(userDetailsService.getPortalServiceUserDetails().getUsername() + rawPassword, BCrypt.gensalt(12));
//            return rawPassword;
        }
        else
        {
            return null;
        }
    }


    @Override
    public boolean matches(CharSequence rawPasswd, String encodedPasswd)
    {
        boolean isValid = false;
        if (rawPasswd == null || encodedPasswd == null)
        {
            return isValid;
        }
        log.debug("Password encoder Validating authentication > User Name : " + userDetailsService.getPortalServiceUserDetails().getUsername());
        isValid = validateAuthentication((String) rawPasswd, encodedPasswd);

        return isValid;
    }

    private boolean validateAuthentication(String rawpassword, String encPass)
    {
        boolean isValid = Boolean.FALSE;

        try
        {
//            Profile profile = pmc.getProfileComplete(userDetailsService.getPortalServiceUserDetails().getUsername());
//            isValid = profile.validatePassword(rawpassword.toString());
            isValid= Encrypter.encryptOneWay(rawpassword).equals(encPass);

        }
        catch (Exception e)
        {
            //
        }

        return isValid;

    }
}
