package org.paradyne.model.PAS.GoalSetting;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.PAS.GoalSetting.EmployeeGoalApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class EmployeeGoalApprovalModel extends ModelBase
{
  public void getEmpGoalList(EmployeeGoalApproval bean, String status, HttpServletRequest request)
  {
    String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, GOAL_HDR_ID,GOAL_NAME,NVL(GOAL_REPORTING,'reporting') ,GOAL_APPROVER_ID FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) WHERE EMP_GOAL_APPROVAL_STATUS=" + 
      status + " AND GOAL_APPROVER_ID=" + bean.getUserEmpId();

    Object[][] empGoalData = getSqlModel().getSingleResult(query);
    String[] pageIndex = Utility.doPaging(bean.getMyPage(), 
      empGoalData.length, 20);
    if (pageIndex == null) {
      pageIndex[0] = "0";
      pageIndex[1] = "20";
      pageIndex[2] = "1";
      pageIndex[3] = "1";
      pageIndex[4] = "";
    }

    request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[2]))));
    request.setAttribute("PageNo", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[3]))));
    if (pageIndex[4].equals("1"))
      bean.setMyPage("1");
    try {
      if ((empGoalData != null) && (empGoalData.length > 0)) {
        ArrayList tableList = new ArrayList();
        int i = Integer.parseInt(pageIndex[0]);
        for (; i < 
          Integer.parseInt(pageIndex[1]); i++) {
          EmployeeGoalApproval beanList = new EmployeeGoalApproval();
          beanList.setEmpToken(String.valueOf(empGoalData[i][0]));
          beanList.setEmpName(String.valueOf(empGoalData[i][1]));
          beanList.setEmpGoalId(String.valueOf(empGoalData[i][2]));
          beanList.setGoalPeriod(String.valueOf(empGoalData[i][3]));
          beanList.setStatus(status);
          beanList.setReportingType(String.valueOf(empGoalData[i][4]));
          beanList.setApproverCodeList(String.valueOf(empGoalData[i][5]));
          tableList.add(beanList);
        }

        if (status.equals("3")) {
          bean.setApprovedList(tableList);
          if (tableList.size() > 0)
            bean.setApprovedLength("true");
          else
            bean.setApprovedLength("false");
        }
        else {
          bean.setPendingList(tableList);
          if (tableList.size() > 0)
            bean.setPendingLength("true");
          else
            bean.setPendingLength("false");
        }
      }
    }
    catch (Exception e) {
      bean.setApprovedLength("false");
      bean.setPendingLength("false");
    }
  }

  public void getEmpGoalListApproved(EmployeeGoalApproval bean, String status, HttpServletRequest request)
  {
    String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, GOAL_HDR_ID,GOAL_NAME  ,NVL(GOAL_REPORTING,'reporting'),GOAL_APPROVER_ID FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) INNER JOIN HRMS_GOAL_PATH ON (GOAL_CODE=GOAL_HDR_ID) WHERE GOAL_PATH_STATUS=" + 
      status + " AND GOAL_APPROVER=" + bean.getUserEmpId();

    Object[][] empGoalData = getSqlModel().getSingleResult(query);
    String[] pageIndex = Utility.doPaging(bean.getMyPageApproved(), 
      empGoalData.length, 20);
    if (pageIndex == null) {
      pageIndex[0] = "0";
      pageIndex[1] = "20";
      pageIndex[2] = "1";
      pageIndex[3] = "1";
      pageIndex[4] = "";
    }

    request.setAttribute("totalPageApproved", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[2]))));
    request.setAttribute("PageNoApproved", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[3]))));
    if (pageIndex[4].equals("1"))
      bean.setMyPageApproved("1");
    try {
      if ((empGoalData != null) && (empGoalData.length > 0)) {
        ArrayList tableList = new ArrayList();
        int i = Integer.parseInt(pageIndex[0]);
        for (; i < 
          Integer.parseInt(pageIndex[1]); i++) {
          EmployeeGoalApproval beanList = new EmployeeGoalApproval();
          beanList.setEmpToken(String.valueOf(empGoalData[i][0]));
          beanList.setEmpName(String.valueOf(empGoalData[i][1]));
          beanList.setEmpGoalId(String.valueOf(empGoalData[i][2]));
          beanList.setGoalPeriod(String.valueOf(empGoalData[i][3]));
          beanList.setStatus(status);
          beanList.setReportingType(String.valueOf(empGoalData[i][4]));
          beanList.setApproverCodeList(String.valueOf(empGoalData[i][5]));
          tableList.add(beanList);
        }

        if (status.equals("3")) {
          bean.setApprovedList(tableList);
          if (tableList.size() > 0)
            bean.setApprovedLength("true");
          else
            bean.setApprovedLength("false");
        }
        else {
          bean.setPendingList(tableList);
          if (tableList.size() > 0)
            bean.setPendingLength("true");
          else
            bean.setPendingLength("false");
        }
      }
    }
    catch (Exception e) {
      bean.setApprovedLength("false");
      bean.setPendingLength("false");
    }
  }

  public boolean updateApprovalStatus(String empGoalIdString, String status) {
    String updateQuery = "UPDATE HRMS_GOAL_EMP_HDR SET EMP_GOAL_APPROVAL_STATUS = " + status + " WHERE GOAL_HDR_ID IN( " + empGoalIdString + ")";

    return getSqlModel().singleExecute(updateQuery);
  }
}