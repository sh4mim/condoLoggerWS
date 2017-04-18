package com.condo.visitor.dao;

import com.condo.menu.bean.MenuBean;
import com.condo.tx.TxException;
import com.condo.tx.dao.CommonDAOImpl;
import com.condo.visitor.bean.VisitorInfoBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * package com.condo.payProc.dl.dao;
 * Copyright (C) 2002-2003 Islami Bank Bangladesh Limited
 * <p/>
 * Original author: parvege
 * Date: 9/2/13(4:38 PM)
 * Last modification by: $Author: parvege $
 * Last modification on $Date: 2014/08/03 05:38:20 $
 * Current revision: $Revision: 1.2 $
 * <p/>
 * Revision History:
 * ------------------
 */
public class VisitorInfoDao extends CommonDAOImpl
{

    private Log log = LogFactory.getLog(this.getClass());

    public VisitorInfoDao(int txSessionID) throws TxException
    {
        super(txSessionID);
    }

    public void SaveVisitorInfo(VisitorInfoBean bean) throws TxException
    {
        try
        {
            super.save(bean);
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
    }

    public List<VisitorInfoBean> findVisitorInfoByStatus(String dwellerID, int status) throws TxException
    {

        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(new Date());
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);

        Calendar toCal = Calendar.getInstance();
        toCal.setTime(new Date());
        toCal.set(Calendar.HOUR_OF_DAY, 23);
        toCal.set(Calendar.MINUTE, 59);
        toCal.set(Calendar.SECOND, 59);

        try
        {
            Criteria crit = session.createCriteria(VisitorInfoBean.class);
            crit.add(Restrictions.eq("visitingTo", dwellerID));
            crit.add(Restrictions.eq("status", status));
            crit.add(Restrictions.between("possibleTime",new Date(fromCal.getTimeInMillis()),new Date(toCal.getTimeInMillis())));

            List<VisitorInfoBean> visitorInfoBeanList = crit.list();
            if (visitorInfoBeanList.size() > 0)
            {
                return visitorInfoBeanList;
            }
            else
            {
                return null;
            }
        }
        catch (HibernateException e)
        {
            throw new TxException("Fail to Fetch Guest information. ");
        }
    }

    public List<VisitorInfoBean> findAllGuestList() throws TxException
    {

        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(new Date());
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);

        Calendar toCal = Calendar.getInstance();
        toCal.setTime(new Date());
        toCal.set(Calendar.HOUR_OF_DAY, 23);
        toCal.set(Calendar.MINUTE, 59);
        toCal.set(Calendar.SECOND, 59);

        try
        {
            Query query = session.createSQLQuery("SELECT V.NAME,V.PHONE_NO,V.NID,V.VEHICLE_NO,V.EXPECTED_GUEST,\n" +
                    "    V.POSSIBLE_TIME,V.STATUS,V.IMAGE_URL ,R.NAME DWELLER_NAME,\n" +
                    "    R.PHONE_NO DWELLER_PHONE,R.EMAIL, R.BLOCK_NO,R.FLAT_NO,R.IMAGE_URL DWELLER_IMAGE\n" +
                    "    FROM T_VISITOR_INFO V,T_RESIDENCE_DETAILS R\n" +
                    "    WHERE R.STATUS=:status AND V.VISITING_TO=R.USER_ID\n" +
                    "    AND V.POSSIBLE_TIME BETWEEN  :visitingDateFrom AND :visitingDateTo");

            query.setParameter("status", 1);
            query.setParameter("visitingDateFrom", fromCal);
            query.setParameter("visitingDateTo", toCal);
            List list = query.list();

            log.debug("Method Name: findAllGuestList, List size :" + list.size());
            if (list.size() > 0)
            {
                List<VisitorInfoBean> visitorInfoBeanList = new ArrayList<VisitorInfoBean>();
                log.debug("List Size is  " + list.size());
                for (int i = 0; i < list.size(); i++)
                {
                    Object[] obj = (Object[]) list.get(i);
                    VisitorInfoBean item = new VisitorInfoBean();
                    item.setName(String.valueOf(obj[0]));
                    item.setPhoneNo(String.valueOf(obj[1]));
                    item.setNid(String.valueOf(obj[2]));
                    item.setVehicleNo(String.valueOf(obj[3]));
                    item.setExpectedGuest(Integer.parseInt(String.valueOf(obj[4])));
//                    item.setPossibleTime(Date.parse(String.valueOf(obj[4]));
                    item.setStatus(Integer.parseInt(String.valueOf(obj[6])));
                    item.setImageUrl(String.valueOf(obj[7]));
                    item.setDwellerName(String.valueOf(obj[8]));
                    item.setDwellerPhone(String.valueOf(obj[9]));
                    item.setDwellerEmail(String.valueOf(obj[10]));
                    item.setBlockNo(String.valueOf(obj[11]));
                    item.setFlatNo(String.valueOf(obj[12]));
                    item.setDwellerImage(String.valueOf(obj[13]));
                    visitorInfoBeanList.add(i, item);
                }

                return visitorInfoBeanList;
            }
            else
            {
                return null;
            }
        }
        catch (Exception ex)
        {
           throw new TxException("Fail to fetch guest information ",ex);
        }
    }

}
