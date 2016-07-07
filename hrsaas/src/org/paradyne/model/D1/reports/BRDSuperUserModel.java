package org.paradyne.model.D1.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.reports.BRDSuperUser;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class BRDSuperUserModel extends ModelBase
{
  public void brdCurrentStage(BRDSuperUser bean)
  {
    try
    {
      TreeMap currentStageMap = new TreeMap();
      String stageQuery = "SELECT STAGE_CODE,STAGE_NAME FROM HRMS_D1_STAGE ORDER BY STAGE_CODE";
      Object[][] emptypeObj = getSqlModel().getSingleResult(stageQuery);
      if ((emptypeObj != null) && (emptypeObj.length > 0)) {
        for (int i = 0; i < emptypeObj.length; i++) {
          currentStageMap.put(String.valueOf(emptypeObj[i][0]), 
            String.valueOf(emptypeObj[i][1]));
        }
      }
      bean.setCurrentStageList(currentStageMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void viewBrdApplicationList(BRDSuperUser brdSuperUserBean, HttpServletRequest request)
  {
    try
    {
      String retrieveDataQuery = "SELECT HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO,"
    	  					+ " HRMS_D1_BUSINESS_REQUIREMENT.PROJECT_NAME,  TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_END_DATE, 'DD-MM-YYYY'),"
    	  					+ " HRMS_D1_STAGE.STAGE_NAME, HRMS_D1_ROLE.ROLE_NAME,  "
    	  					+ " DECODE(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS, 'D','Draft', 'F','Forwarded', 'C','Closed', 'Z', 'Cancel'), "
    	  					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE,"
    	  					+ " DECODE(HRMS_D1_BUSINESS_PATH.BUSINESS_ACTIVITY_STATUS,'S','Started','N','Not Started','H','OnHold'), "
    	  					+ " TO_CHAR(HRMS_D1_BUSINESS_PATH.BUSINESS_FORCASTED_DATE, 'DD-MM-YYYY'),PROJ_STATUS "
    	  					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
    	  					+ " LEFT JOIN HRMS_D1_STAGE ON (HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE)"
    	  					+ " INNER JOIN HRMS_D1_ROLE ON (HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO) "
    	  					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID) "
    	  					+ " LEFT JOIN HRMS_D1_BUSINESS_PATH ON (HRMS_D1_BUSINESS_PATH.BUSINESS_CODE = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE)"
    	  					+ " WHERE 1=1";

      if ((!brdSuperUserBean.getApplicationStatus().equals("-1")) && (!brdSuperUserBean.getApplicationStatus().equals(""))) {
        retrieveDataQuery = retrieveDataQuery + " AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS = '" + brdSuperUserBean.getApplicationStatus() + "'";
      }

      if ((!brdSuperUserBean.getCurrentStage().equals("-1")) && (!brdSuperUserBean.getCurrentStage().equals(""))) {
        retrieveDataQuery = retrieveDataQuery + " AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE = '" + brdSuperUserBean.getCurrentStage() + "'";
      }

      if (!brdSuperUserBean.getTicketNumber().equals("")) {
        retrieveDataQuery = retrieveDataQuery + " AND HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO = '" + brdSuperUserBean.getTicketNumber() + "'";
      }

      if (!brdSuperUserBean.getInitiatorId().equals("")) {
        retrieveDataQuery = retrieveDataQuery + " AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY = '" + brdSuperUserBean.getInitiatorId() + "'";
      }

      if (!brdSuperUserBean.getPendingWithEmpId().equals("")) {
        retrieveDataQuery = retrieveDataQuery + " AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID = '" + brdSuperUserBean.getPendingWithEmpId() + "'";
      }

      if ((!brdSuperUserBean.getFromDate().trim().equals("")) && (!brdSuperUserBean.getToDate().trim().equals(""))) {
        retrieveDataQuery = retrieveDataQuery + " AND TO_DATE(TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') BETWEEN TO_DATE('" + brdSuperUserBean.getFromDate() + "','DD-MM-YYYY') AND TO_DATE('" + brdSuperUserBean.getToDate() + "','DD-MM-YYYY') ";
      }

      retrieveDataQuery = retrieveDataQuery + " ORDER BY HRMS_D1_BUSINESS_PATH.BUSINESS_APPR_DATE ";

      Object[][] initialDataObj = getSqlModel().getSingleResult(retrieveDataQuery);
      Object[][] applicationObj = (Object[][])null;
      if ((initialDataObj != null) && (initialDataObj.length > 0)) {
        HashMap map = new HashMap();
        for (int i = 0; i < initialDataObj.length; i++) {
          map.put(String.valueOf(initialDataObj[i][7]), initialDataObj[i]);
        }
        applicationObj = new Object[map.size()][initialDataObj[0].length];
        Iterator itKeyList = null;
        Object key = null;
        Set keySet = map.keySet();
        itKeyList = keySet.iterator();
        int countIncr = 0;
        while (itKeyList.hasNext()) {
          key = itKeyList.next();
          Object[] value = (Object[])map.get(key);
          for (int i = 0; i < value.length; i++) {
            applicationObj[countIncr][i] = value[i];
          }
          countIncr++;
        }

      }

      if ((applicationObj != null) && (applicationObj.length > 0)) {
        String[] pageIndex = Utility.doPaging(brdSuperUserBean.getMyPage(), applicationObj.length, 20);
        if (pageIndex == null) {
          pageIndex[0] = "0";
          pageIndex[1] = "20";
          pageIndex[2] = "1";
          pageIndex[3] = "1";
          pageIndex[4] = "";
        }

        request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
        request.setAttribute("pageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
        if (pageIndex[4].equals("1")) {
          brdSuperUserBean.setMyPage("1");
        }

        brdSuperUserBean.setBrdSuperUserPagingFlag(true);
        ArrayList tempList = new ArrayList();
        for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
          BRDSuperUser innerBean = new BRDSuperUser();
          innerBean.setBrdTicketNoItr(checkNull(String.valueOf(applicationObj[i][0])));
          innerBean.setProjectNameItr(checkNull(String.valueOf(applicationObj[i][1])));
          innerBean.setExpectedDateItr(checkNull(String.valueOf(applicationObj[i][2])));
          innerBean.setCurrentStageItr(checkNull(String.valueOf(applicationObj[i][3])));
          innerBean.setPendingWithRoleItr(checkNull(String.valueOf(applicationObj[i][4])));
          innerBean.setStatusItr(checkNull(String.valueOf(applicationObj[i][5])));
          innerBean.setPendingWithNameItr(checkNull(String.valueOf(applicationObj[i][6])));
          innerBean.setBrdApplicationIdItr(checkNull(String.valueOf(applicationObj[i][7])));
          innerBean.setCurrentActivityItr(checkNull(String.valueOf(applicationObj[i][8])));
          innerBean.setForecastedCompletionDateItr(checkNull(String.valueOf(applicationObj[i][9])));
          tempList.add(innerBean);
        }
        brdSuperUserBean.setApplicationList(tempList);
      } else {
        brdSuperUserBean.setNodataFlag(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("")) || (result.equals("null"))) {
      return "";
    }
    return result;
  }
}