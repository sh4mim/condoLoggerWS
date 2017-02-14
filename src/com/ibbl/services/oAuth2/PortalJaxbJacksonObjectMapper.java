package com.ibbl.services.oAuth2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * package com.ibbl.app;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: Administrator
 * Date: 2/11/2016(3:30 PM)
 * Last modification by: $Author: harun $
 * Last modification on $Date: 2016/03/01 09:18:15 $
 * Current revision: $Revision: 1.1 $
 * <p/>
 * Revision History:
 * ------------------
 **/
public class PortalJaxbJacksonObjectMapper extends ObjectMapper
{

    public PortalJaxbJacksonObjectMapper()
    {

        this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY)
                .setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);

        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
