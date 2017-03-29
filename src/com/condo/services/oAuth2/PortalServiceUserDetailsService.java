package com.condo.services.oAuth2;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * package com.test.hash;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: Administrator
 * Date: 1/13/2016(11:55 AM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2016/01/13 08:42:00 $
 * Current revision: $Revision: 1.1 $
 * <p/>
 * Revision History:
 * ------------------
 **/

public class PortalServiceUserDetailsService extends JdbcDaoImpl
{

    PortalServiceUserDetails portalServiceUserDetails = null;

    public PortalServiceUserDetails getPortalServiceUserDetails()
    {
        return portalServiceUserDetails;
    }

    public void setPortalServiceUserDetails(PortalServiceUserDetails portalServiceUserDetails)
    {
        this.portalServiceUserDetails = portalServiceUserDetails;
    }

    @Override
    public List<UserDetails> loadUsersByUsername(String username)
    {
        return getJdbcTemplate().query(super.getUsersByUsernameQuery(), new String[]{username},
                new RowMapper<UserDetails>()
                {
                    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        boolean enabled = rs.getBoolean("enabled");
                        long profileId = rs.getLong("profileId");


                        boolean accountNonExpired = true;
                        boolean credentialsNonExpired = true;
                        boolean accountNonLocked = true;

                        portalServiceUserDetails = new PortalServiceUserDetails(username, password, enabled, accountNonExpired, credentialsNonExpired,
                                accountNonLocked, AuthorityUtils.NO_AUTHORITIES);
                        portalServiceUserDetails.setProfileId(profileId);

                        return portalServiceUserDetails;
                    }

                });
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
                                            List<GrantedAuthority> combinedAuthorities)
    {
        String returnUsername = userFromUserQuery.getUsername();

        if (!isUsernameBasedPrimaryKey())
        {
            returnUsername = username;
        }

        final PortalServiceUserDetails portalServiceUserDetails = new PortalServiceUserDetails(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
                userFromUserQuery.isAccountNonExpired(), userFromUserQuery.isCredentialsNonExpired(),
                userFromUserQuery.isAccountNonLocked(), combinedAuthorities);

        portalServiceUserDetails.setId(((PortalServiceUserDetails) userFromUserQuery).getId());
        return portalServiceUserDetails;
    }
}