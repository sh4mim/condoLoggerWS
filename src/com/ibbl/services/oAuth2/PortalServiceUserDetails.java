package com.ibbl.services.oAuth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * package com.test.hash;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: Administrator
 * Date: 1/13/2016(10:30 AM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2016/01/13 08:42:00 $
 * Current revision: $Revision: 1.1 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class PortalServiceUserDetails extends User
{

    private Long profileId;
    private int id;

    public PortalServiceUserDetails(String username, String password, boolean enabled,
                                    boolean accountNonExpired, boolean credentialsNonExpired,
                                    boolean accountNonLocked,
                                    Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);

    }

    public Long getProfileId()
    {
        return profileId;
    }

    public void setProfileId(Long profileId)
    {
        this.profileId = profileId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
