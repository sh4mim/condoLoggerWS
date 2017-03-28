package com.ibbl.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * package com.ibbl.app.web;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: harun
 * Date: 04-Feb-16(5:45 PM)
 * Last modification by: $Author: harun $
 * Last modification on $Date: 2016/02/03 11:48:42 $
 * Current revision: $Revision: 1.1 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class CORSFilter extends OncePerRequestFilter
{
    private static final Log LOG = LogFactory.getLog(CORSFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        response.addHeader("Access-Control-Allow-Origin", "*");

        LOG.debug("Sending Header....");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "300");


        filterChain.doFilter(request, response);
    }

}