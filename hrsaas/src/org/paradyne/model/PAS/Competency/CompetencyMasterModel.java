package org.paradyne.model.PAS.Competency;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompetencyMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.lib.ParaActionSupport;

public class CompetencyMasterModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ParaActionSupport.class);

  public void competencyData(CompetencyMaster competency, HttpServletRequest request)
  {
    Object[][] repData = getSqlModel().getSingleResult(getQuery(10));
    if ((repData != null) && (repData.length > 0)) {
      competency.setModeLength("true");
      competency.setTotalRecords(String.valueOf(repData.length));

      String[] pageIndex = Utility.doPaging(competency.getMyPage(), 
        repData.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }

      request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(
        String.valueOf(pageIndex[2]))));
      request.setAttribute("pageNo", Integer.valueOf(Integer.parseInt(
        String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1"))
        competency.setMyPage("1");
      ArrayList List = new ArrayList();
      int i = Integer.parseInt(pageIndex[0]);
      for (; i < 
        Integer.parseInt(pageIndex[1]); i++) {
        CompetencyMaster competency1 = new CompetencyMaster();
        competency1.setCompetencyCode(
          checkNull(String.valueOf(repData[i][0])));
        competency1.setCompetencyTitle(
          checkNull(String.valueOf(repData[i][3])));
        competency1.setCompetencyDesc(
          checkNull(String.valueOf(repData[i][1])));
        competency1.setCompetencyIndicator(
          checkNull(String.valueOf(repData[i][2])));

        List.add(competency1);
      }

      competency.setCompetencylist(List);
    }
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }
    return result;
  }

  public void getReport(CompetencyMaster competency, HttpServletRequest request, HttpServletResponse response, ServletContext context, HttpSession session, String[] label)
  {
    String reportName = "Competency Master";
    ReportGenerator rg = new ReportGenerator("Pdf", 
      reportName);
    rg.setFName("Competency Master.Pdf");
    String queryDes = " SELECT subStr(COMPETENCY_TITLE,1,50), COMPETENCY_DESC, COMPETENCY_INDICATOR FROM HRMS_COMPETENCY_HDR  ORDER BY upper(COMPETENCY_CODE)";

    Object[][] data = getSqlModel().getSingleResult(queryDes);
    Object[][] Data = new Object[data.length][4];
    if ((data != null) && (data.length > 0)) {
      int j = 1;
      for (int i = 0; i < data.length; i++) {
        Data[i][0] = Integer.valueOf(j);
        Data[i][1] = data[i][0];
        Data[i][2] = data[i][1];
        Data[i][3] = data[i][2];

        j++;
      }
      int[] cellwidth = { 15, 60, 25, 25 };
      int[] alignment = { 1 };
      rg.addTextBold("Competency Master", 0, 1, 0);
      rg.addText("\n", 0, 0, 0);
      rg.tableBody(label, Data, cellwidth, alignment);
    } else {
      rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
    }

    rg.createReport(response);
  }

  public void getDuplicate(CompetencyMaster competency, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc)
  {
    ArrayList tableList = new ArrayList();
    if (competencyLevel != null)
    {
      for (int i = 0; i < competencyLevel.length; i++) {
        CompetencyMaster bean = new CompetencyMaster();
        bean.setCompetencyLevel(competencyLevel[i]);
        bean.setCompetencyLevelTitle(competencyLevelTitle[i]);
        bean.setCompetencyLevelDesc(competencyLevelDesc[i]);
        tableList.add(bean);
      }
      competency.setCompetencyLevelList(tableList);
    }
  }

  public boolean addItem_old(CompetencyMaster competency, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc, int check)
  {
    boolean isDuplicate = false;
    ArrayList tableList = new ArrayList();
    int i = 0;

    if (competencyLevel != null) {
      for (i = 0; i < competencyLevel.length; i++) {
        CompetencyMaster bean = new CompetencyMaster();
        if (competencyLevel[i].equals(competency.getCompLevel())) {
          isDuplicate = true;
        }
        bean.setCompetencyLevel(competencyLevel[i]);
        bean.setCompetencyLevelTitle(competencyLevelTitle[i]);
        bean.setCompetencyLevelDesc(competencyLevelDesc[i]);
        tableList.add(bean);
      }

    }

    if ((!isDuplicate) && 
      (check == 1)) {
      competency.setCompetencyLevel(competency.getCompLevel());
      competency.setCompetencyLevelTitle(
        competency.getCompLevelTitle());
      competency
        .setCompetencyLevelDesc(competency.getCompLevelDesc());
      isDuplicate = true;
    }

    if (check == 0) {
      tableList.remove(Integer.parseInt(competency.getSubcode()) - 1);
    }
    tableList.add(competency);
    if (tableList.size() != 0)
      competency.setTableLength("1");
    else
      competency.setTableLength("0");
    competency.setCompetencyLevelList(tableList);

    return isDuplicate;
  }

  public boolean moditem(CompetencyMaster competency, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc)
  {
    boolean isDuplicate = false;

    ArrayList tableList = new ArrayList();
    int i = 0;
    if ((competencyLevel != null) && (competencyLevel.length > 0)) {
      for (i = 0; i < competencyLevel.length; i++) {
        int cnt = 0;
        if ((!competencyLevel[i].equals(competency.getCompLevel())) || 
          (!competencyLevelTitle[i].equals(competency.getCompLevelTitle())) || 
          (!competencyLevelDesc[i].equals(competency.getCompLevelDesc()))) {
          continue;
        }
        isDuplicate = true;
      }

      for (int j = 0; j < competencyLevel.length; j++) {
        System.out.println(" HIDDEN EDIT  " + competency.getHiddenEdit());
        if (j == Integer.parseInt(competency.getHiddenEdit()) - 1) {
          if (isDuplicate) {
            System.out.println("=======if===============");
            CompetencyMaster bean = new CompetencyMaster();
            bean.setCompetencyLevel(competencyLevel[j]);
            bean.setCompetencyLevelTitle(competencyLevelTitle[j]);
            bean.setCompetencyLevelDesc(competencyLevelDesc[j]);
            tableList.add(bean);
          }
          else {
            System.out.println("========else==============");
            CompetencyMaster bean1 = new CompetencyMaster();
            bean1.setCompetencyLevel(competency.getCompLevel());
            bean1.setCompetencyLevelTitle(competency.getCompLevelTitle());
            bean1.setCompetencyLevelDesc(competency.getCompLevelDesc());
            tableList.add(bean1);
          }
        }
        else {
          System.out.println("==========out else============");
          CompetencyMaster bean = new CompetencyMaster();
          bean.setCompetencyLevel(competencyLevel[j]);
          bean.setCompetencyLevelTitle(competencyLevelTitle[j]);
          bean.setCompetencyLevelDesc(competencyLevelDesc[j]);
          tableList.add(bean);
        }

      }

    }

    if (tableList.size() != 0)
      competency.setTableLength("1");
    else
      competency.setTableLength("0");
    competency.setCompetencyLevelList(tableList);

    return isDuplicate;
  }

  public boolean addCompentency(CompetencyMaster competency, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc)
  {
    System.out.println("---------------addCompentency----------");
    String Query1 = "SELECT COMPETENCY_CODE FROM HRMS_COMPETENCY_HDR WHERE COMPETENCY_TITLE='" + 
      competency.getCompetencyTitle() + "'";
    Object[][] Recordlength = getSqlModel().getSingleResult(Query1);
    if (Recordlength.length > 0) {
      return false;
    }

    String query = " SELECT NVL(MAX(COMPETENCY_CODE),0)+1 FROM HRMS_COMPETENCY_HDR ";

    Object[][] code = getSqlModel().getSingleResult(query);
    System.out
      .println("---------competency.getCompetencyTitle()--------------" + 
      competency.getCompetencyTitle());
    Object[][] obj = new Object[1][4];
    obj[0][0] = code[0][0];
    obj[0][1] = competency.getCompetencyTitle();
    obj[0][2] = competency.getCompetencyDesc();
    obj[0][3] = competency.getCompetencyIndicator();

    boolean result = getSqlModel().singleExecute(getQuery(1), obj);
    if (result) {
      competency.setCompetencyCode(String.valueOf(code[0][0]));
      result = addOption(competency, competencyLevel, 
        competencyLevelTitle, competencyLevelDesc);
    }

    return result;
  }

  public boolean addOption(CompetencyMaster competency, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc)
  {
    System.out.println("---------------addCompetencyLevel----------");
    boolean result = false;
    Object[][] addObj = new Object[1][4];
    if (competencyLevel != null) {
      for (int i = 0; i < competencyLevel.length; i++) {
        addObj[0][0] = competency.getCompetencyCode();
        addObj[0][1] = competencyLevel[i];
        addObj[0][2] = competencyLevelTitle[i];
        addObj[0][3] = competencyLevelDesc[i];

        result = getSqlModel().singleExecute(getQuery(2), addObj);
      }
    }
    return result;
  }

  public void showRecord(CompetencyMaster competency) {
    System.out
      .println("---------------showRecord----competency.getCompetencyCode()------" + 
      competency.getCompetencyCode());
    String query = "SELECT COMPETENCY_LEVEL, COMPETENCY_TITLE, COMPETENCY_LEVEL_DESC,COMPETENCY_CODE  FROM HRMS_COMPETENCY_DTL WHERE COMPETENCY_CODE = " + 
      competency.getCompetencyCode() + " ORDER BY COMPETENCY_LEVEL";

    Object[][] data = getSqlModel().getSingleResult(query);

    ArrayList list = new ArrayList();
    for (int i = 0; i < data.length; i++) {
      CompetencyMaster bean1 = new CompetencyMaster();
      bean1.setCompetencyLevel(String.valueOf(data[i][0]));
      bean1.setCompetencyLevelTitle(String.valueOf(data[i][1]));
      bean1.setCompetencyLevelDesc(String.valueOf(data[i][2]));

      list.add(bean1);
    }
    competency.setCompetencyLevelList(list);
    if (list.size() == 0)
      competency.setTableLength("0");
    else
      competency.setTableLength("1");
  }

  public boolean modCompentency(CompetencyMaster competency, String[] competencyCode, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc)
  {
    System.out.println("---------------modCompentency----------");
    boolean result1 = false;
    boolean result2 = false;
    Object[][] obj = new Object[1][4];
    obj[0][0] = competency.getCompetencyTitle();

    obj[0][1] = competency.getCompetencyDesc();

    obj[0][2] = competency.getCompetencyIndicator();

    obj[0][3] = competency.getCompetencyCode();

    boolean result = getSqlModel().singleExecute(getQuery(9), obj);
    if (result) {
      String queryDel = "DELETE FROM HRMS_COMPETENCY_DTL WHERE COMPETENCY_CODE= " + 
        competencyCode[0];
      result1 = getSqlModel().singleExecute(queryDel);
      if (result1)
        result2 = addOption(competency, competencyLevel, 
          competencyLevelTitle, competencyLevelDesc);
    }
    return result;
  }

  public void calforedit(CompetencyMaster competency) {
    System.out.println("---------------calforedit----------");
    String query = "SELECT  COMPETENCY_CODE, COMPETENCY_DESC, COMPETENCY_INDICATOR, COMPETENCY_TITLE FROM HRMS_COMPETENCY_HDR  where COMPETENCY_CODE=" + 
      competency.getHiddenCode();
    Object[][] data = getSqlModel().getSingleResult(query);
    competency.setCompetencyCode(checkNull(String.valueOf(data[0][0])));
    competency.setCompetencyDesc(checkNull(String.valueOf(data[0][1])));
    competency.setCompetencyIndicator(checkNull(String.valueOf(data[0][2])));
    competency.setCompetencyTitle(checkNull(String.valueOf(data[0][3])));
    showRecord(competency);
  }

  public boolean deleteCompetency(CompetencyMaster competency)
  {
    System.out.println("---------------deleteCompetency----------");
    Object[][] delObj = new Object[1][1];
    delObj[0][0] = competency.getCompetencyCode();
    boolean res = getSqlModel().singleExecute(getQuery(3), delObj);
    if (res) {
      return getSqlModel().singleExecute(getQuery(4), delObj);
    }
    return false;
  }

  public boolean checkeddeletecompetency(CompetencyMaster competency, String[] code) {
    System.out.println("&&&---checkeddeletecompetency--&&" + code);
    boolean result = false;
    int count = 0;
    if (code != null) {
      for (int i = 0; i < code.length; i++)
      {
        if (code[i].equals(""))
          continue;
        Object[][] delete = new Object[1][1];
        delete[0][0] = code[i];

        boolean res = getSqlModel().singleExecute(getQuery(3), 
          delete);
        if (res) {
          result = getSqlModel().singleExecute(getQuery(4), 
            delete);
        }
        if (!result) {
          count++;
        }
      }
    }
    if (count != 0) {
      result = false;
    }
    else {
      result = true;
    }
    return result;
  }

  public void deleteLevel(CompetencyMaster compBean, String levelCode, String compCode, String[] level, String[] levelTitle, String[] levelDesc, String[] comCodeIt)
  {
    ArrayList tableList = new ArrayList();
    if (!compCode.equals("")) {
      System.out.println("=====DELETE======" + compCode);
      String delQry = "DELETE FROM HRMS_COMPETENCY_DTL WHERE COMPETENCY_LEVEL=" + 
        levelCode + " AND COMPETENCY_CODE=" + compCode;
      getSqlModel().singleExecute(delQry);
    }
    if ((level != null) && (level.length > 0)) {
      CompetencyMaster bean1 = null;
      for (int i = 0; i < level.length; i++) {
        if (!level[i].equals(levelCode)) {
          bean1 = new CompetencyMaster();
          bean1.setCompetencyLevel(checkNull(String.valueOf(level[i])));
          bean1.setCompetencyLevelTitle(checkNull(String.valueOf(levelTitle[i])));
          bean1.setCompetencyLevelDesc(checkNull(String.valueOf(levelDesc[i])));
          tableList.add(bean1);
        }
      }
    }

    compBean.setCompetencyLevelList(tableList);
  }

  public boolean addItem(CompetencyMaster mainBean, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc)
  {
    boolean isDuplicate = false;
    boolean result = false;
    try
    {
      ArrayList list = new ArrayList();
      if ((competencyLevel != null) && (competencyLevel.length > 0)) {
        for (int i = 0; i < competencyLevelDesc.length; i++) {
          CompetencyMaster innerBean = new CompetencyMaster();
          innerBean.setCompetencyLevel(competencyLevel[i]);

          if (innerBean.getCompetencyLevel().equals(mainBean.getCompLevel())) {
            isDuplicate = true;
            result = false;
          }

          innerBean.setCompetencyLevelTitle(competencyLevelTitle[i]);
          innerBean.setCompetencyLevelDesc(competencyLevelDesc[i]);
          list.add(innerBean);
        }
      }

      if (!isDuplicate)
      {
        CompetencyMaster innerBean = new CompetencyMaster();
        innerBean.setCompetencyLevel(mainBean.getCompLevel());
        innerBean.setCompetencyLevelTitle(mainBean.getCompLevelTitle());
        innerBean.setCompetencyLevelDesc(mainBean.getCompLevelDesc());
        list.add(innerBean);
        result = true;
      }
      mainBean.setCompetencyLevelList(list);
      if (list.size() != 0)
        mainBean.setTableLength("1");
      else
        mainBean.setTableLength("0");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
  public boolean moditem_old(CompetencyMaster competency, String[] competencyLevel, String[] competencyLevelTitle, String[] competencyLevelDesc) {
    boolean isDuplicate = false;
    boolean result = false;
    try {
      ArrayList list = new ArrayList();
      int i = 0;
      if (competencyLevel != null) {
        for (i = 0; i < competencyLevel.length; i++) {
          CompetencyMaster innerBean = new CompetencyMaster();
          innerBean.setCompetencyLevel(competencyLevel[i]);
          if (innerBean.getCompetencyLevel().equals(competency.getCompLevel())) {
            isDuplicate = true;
            result = false;
          }
          else if (competencyLevel[i].equals(competency.getHiddenEdit())) {
            innerBean.setCompetencyLevel(competency.getCompLevel());
            innerBean.setCompetencyLevelTitle(competency.getCompLevelTitle());
            innerBean.setCompetencyLevelDesc(competency.getCompLevelDesc());
          }

          innerBean.setCompetencyLevelTitle(competencyLevelTitle[i]);
          innerBean.setCompetencyLevelDesc(competencyLevelDesc[i]);
          list.add(innerBean);
        }
      }

      if (list.size() != 0)
        competency.setTableLength("1");
      else {
        competency.setTableLength("0");
      }
      competency.setCompetencyLevelList(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}