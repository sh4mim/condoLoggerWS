//package com.ibbl.services;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * package com.condo.services;
// * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
// * <p/>
// * Original author: parvege
// * Date: 3/16/2015(11:05 AM)
// * Last modification by: $Author: parvege $
// * Last modification on $Date: 2015/12/06 10:10:05 $
// * Current revision: $Revision: 1.1.1.1 $
// * <p/>
// * Revision History:
// * ------------------
// */
//
//
//@Controller
//public class HelloController
//{
//
//    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
//    public ModelAndView welcomePage()
//    {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "iCellular");
//        model.addObject("message", "Welcome!. Please Call your desired web service with Proper Spec, Provided by IBBL");
//        model.setViewName("welcome");
//        return model;
//
//    }
//
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout)
//    {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null)
//        {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null)
//        {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//
//        return model;
//
//    }
//}
