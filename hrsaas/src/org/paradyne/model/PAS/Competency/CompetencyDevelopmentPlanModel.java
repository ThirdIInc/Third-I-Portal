package org.paradyne.model.PAS.Competency;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompetencyDevelopmentPlan;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class CompetencyDevelopmentPlanModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ModelBase.class);
  CompetencyDevelopmentPlan bean = null;

  public boolean checkDuplicate(CompetencyDevelopmentPlan bean)
  {
    boolean result = false;
    String query = "SELECT * FROM HRMS_COMPETENCY_DEV_PLAN WHERE UPPER(HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_PLAN) LIKE '" + 
      bean.getCompDevName().trim().toUpperCase() + "'";
    Object[][] data = getSqlModel().getSingleResult(query);
    if ((data != null) && (data.length > 0)) {
      result = true;
    }
    return result;
  }

  public boolean checkDuplicateMod(CompetencyDevelopmentPlan bean)
  {
    boolean result = false;
    String query = "SELECT * FROM HRMS_COMPETENCY_DEV_PLAN WHERE UPPER(HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_PLAN) LIKE '" + 
      bean.getCompDevName().trim().toUpperCase() + 
      "' AND HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE not in(" + bean.getCompDevCode() + ")";
    Object[][] data = getSqlModel().getSingleResult(query);
    if (data.length == 0) {
      result = true;
    }
    return result;
  }

  public boolean addCompDev(CompetencyDevelopmentPlan bean)
  {
    boolean result = false;
    String query = "SELECT * FROM HRMS_COMPETENCY_DEV_PLAN WHERE UPPER(HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_PLAN) LIKE '" + 
      bean.getCompDevName().trim().toUpperCase() + "'";
    Object[][] data = getSqlModel().getSingleResult(query);
    if (data.length == 0)
    {
      Object[][] saveObj = new Object[1][2];
      saveObj[0][0] = bean.getCompDevName();
      saveObj[0][1] = bean.getCompDevDesc();

      result = getSqlModel().singleExecute(getQuery(1), saveObj);

      String selQuery = "SELECT MAX(HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE) FROM HRMS_COMPETENCY_DEV_PLAN";

      Object[][] datasel = getSqlModel().getSingleResult(selQuery);
      bean.setCompDevCode(String.valueOf(datasel[0][0]));
    }
    else {
      return false;
    }

    return result;
  }

  public boolean modCompDev(CompetencyDevelopmentPlan bean)
  {
    if (checkDuplicateMod(bean)) {
      Object[][] modObj = new Object[1][3];
      modObj[0][0] = bean.getCompDevName().trim();
      modObj[0][1] = bean.getCompDevDesc();
      modObj[0][2] = bean.getCompDevCode();

      return getSqlModel().singleExecute(getQuery(2), modObj);
    }

    return false;
  }

  public boolean deleteCompDevSelected(CompetencyDevelopmentPlan bean)
  {
    Object[][] delObj = new Object[1][1];
    delObj[0][0] = bean.getCompDevCode();
    return getSqlModel().singleExecute(getQuery(3), delObj);
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void hasData1(CompetencyDevelopmentPlan tm, HttpServletRequest request)
  {
    Object[][] repData = getSqlModel().getSingleResult(getQuery(4));
    ArrayList List = new ArrayList();
    if ((repData != null) && (repData.length > 0)) {
      tm.setModeLength("true");
      tm.setTotalRecords(String.valueOf(repData.length));

      String[] pageIndex = Utility.doPaging(tm.getMyPage(), repData.length, 20);
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
        tm.setMyPage("1");
      }
      for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++)
      {
        CompetencyDevelopmentPlan bean1 = new CompetencyDevelopmentPlan();
        bean1.setCompDevCode(checkNull(String.valueOf(repData[i][0])));
        bean1.setCompDevName(checkNull(String.valueOf(repData[i][1])));
        bean1.setCompDevDesc(checkNull(String.valueOf(repData[i][2])));
        List.add(bean1);
      }
      tm.setTitleList(List);
    }
    else {
      tm.setModeLength("false");
      tm.setTotalRecords("0");
      tm.setTitleList(List);
    }
  }

  public void calforedit(CompetencyDevelopmentPlan bean)
  {
    String query = " SELECT  HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE, HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_PLAN, HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_DESC FROM HRMS_COMPETENCY_DEV_PLAN  WHERE HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE= " + 
      bean.getHiddencode() + " ";
    Object[][] data = getSqlModel().getSingleResult(query);
    bean.setCompDevCode(String.valueOf(data[0][0]));
    bean.setCompDevName(String.valueOf(data[0][1]));
    bean.setCompDevDesc(String.valueOf(data[0][2]));
  }

  public boolean calfordelete(CompetencyDevelopmentPlan tm)
  {
    Object[][] delete = new Object[1][1];
    delete[0][0] = tm.getHiddencode();
    return getSqlModel().singleExecute(getQuery(3), delete);
  }

  public boolean deleteCompDev(CompetencyDevelopmentPlan tm, String[] code)
  {
    boolean result = false;
    boolean flag = false;
    int cnt = 0;
    if (code != null) {
      for (int i = 0; i < code.length; i++)
      {
        if (code[i].equals(""))
          continue;
        Object[][] delete = new Object[1][1];
        delete[0][0] = code[i];
        flag = getSqlModel().singleExecute(getQuery(3), delete);
        if (!flag) {
          cnt++;
        }
      }

    }

    if (cnt > 0) {
      result = false;
    }
    else
      result = true;
    return result;
  }

  public void data(CompetencyDevelopmentPlan bean, HttpServletRequest request)
  {
    try
    {
      String Query = " SELECT  HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_PLAN, HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_DESC ,HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE  FROM HRMS_COMPETENCY_DEV_PLAN WHERE HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE=" + 
        bean.getCompDevCode() + "  ";
      Object[][] data = getSqlModel().getSingleResult(Query);
      if ((data != null) && (data.length > 0)) {
        bean.setCompDevName(checkNull(String.valueOf(data[0][0])));
        bean.setCompDevDesc(checkNull(String.valueOf(data[0][1])));
        bean.setCompDevCode(checkNull(String.valueOf(data[0][2])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}