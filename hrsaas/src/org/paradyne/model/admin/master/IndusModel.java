package org.paradyne.model.admin.master;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.admin.master.IndusMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.admin.master.RankMasterAction;

public class IndusModel extends ModelBase
{
  static Logger logger = Logger.getLogger(
    RankMasterAction.class);

  public String addIndustry(IndusMaster bean)
  {
    Object[][] add = new Object[1][4];
    String flag = "";
    boolean result = false;
    try {
      add[0][0] = bean.getIndustryName().trim();
      logger.info("add[0][0]--->" + add[0][0]);
      add[0][1] = bean.getIndustryAbbr().trim();
      logger.info("add[0][0]--->" + add[0][1]);
      add[0][2] = bean.getIndustryDesc();
      logger.info("add[0][0]--->" + add[0][2]);
      add[0][3] = bean.getIndustryStatus();
      logger.info("add[0][0]--->" + add[0][3]);

      if (!checkDuplicate(bean))
      {
        result = getSqlModel().singleExecute(getQuery(1), add);

        if (result)
        {
          String query = " SELECT MAX(INDUS_CODE) FROM HRMS_INDUS_TYPE";

          Object[][] data = getSqlModel().getSingleResult(query);

          System.out.println("INDUSTRY CODE IS------->" + data[0][0]);

          String query1 = " SELECT INDUS_NAME\t,INDUS_ABBRIVIATION\t,INDUS_DESCRIPTION,DECODE(INDUS_STATUS,'A','Active','D','Deactive'),INDUS_CODE FROM  HRMS_INDUS_TYPE WHERE INDUS_CODE=" + 
            String.valueOf(data[0][0]);

          logger.info("query1 in addDesignation---->" + query1);

          Object[][] Data = getSqlModel().getSingleResult(query1);
          logger.info("IndustryName--->" + Data[0][0]);
          bean.setIndustryName(checkNull(String.valueOf(Data[0][0])));
          logger.info("IndustryAbbr--->" + Data[0][1]);
          bean.setIndustryAbbr(checkNull(String.valueOf(Data[0][1])));
          logger.info("IndustryDesc--->" + Data[0][2]);
          bean.setIndustryDesc(checkNull(String.valueOf(Data[0][2])));
          logger.info("PageStatus--->" + Data[0][3]);
          bean.setPageStatus(checkNull(String.valueOf(Data[0][3])));
          logger.info("IndustryCode--->" + Data[0][4]);
          bean.setIndustryCode(checkNull(String.valueOf(Data[0][4])));
          flag = "saved";
        }
        else {
          flag = "error";
        }
      }
      else
      {
        String query = "SELECT DECODE(INDUS_STATUS,'A','Active','D','Deactive')  FROM HRMS_INDUS_TYPE where  INDUS_STATUS='" + 
          bean.getIndustryStatus() + "'";

        Object[][] data = getSqlModel().getSingleResult(query);
        flag = "duplicate";
      }
    } catch (Exception e) {
      logger.error("Exception was rised--->");
    }
    return flag;
  }

  public String modIndustry(IndusMaster bean)
  {
    Object[][] data = new Object[1][5];
    String editFlag = "";
    boolean result = false;
    try {
      data[0][0] = bean.getIndustryName();
      data[0][1] = bean.getIndustryAbbr();
      data[0][2] = bean.getIndustryDesc();
      data[0][3] = bean.getIndustryStatus();
      data[0][4] = bean.getIndustryCode();
      if (!checkDuplicateMod(bean)) {
        String query = "UPDATE HRMS_INDUS_TYPE SET INDUS_NAME=?,INDUS_ABBRIVIATION=? ,INDUS_DESCRIPTION=?,INDUS_STATUS=? where INDUS_CODE=?";
        result = getSqlModel().singleExecute(query, data);
        if (result) {
          String query1 = "SELECT INDUS_NAME,INDUS_ABBRIVIATION,INDUS_DESCRIPTION,DECODE(INDUS_STATUS,'A','Active','D','Deactive'),INDUS_CODE FROM HRMS_INDUS_TYPE where INDUS_CODE =" + bean.getIndustryCode();
          Object[][] data1 = getSqlModel().getSingleResult(query1);
          logger.info("IndustryName--->" + data[0][0]);
          bean.setIndustryName(checkNull(String.valueOf(data1[0][0]).trim()));
          logger.info("IndustryAbbr--->" + data[0][1]);
          bean.setIndustryAbbr(checkNull(String.valueOf(data1[0][1]).trim()));
          logger.info("IndustryDesc--->" + data[0][3]);
          bean.setIndustryDesc(checkNull(String.valueOf(data1[0][2]).trim()));
          logger.info("PageStatus--->" + data[0][2]);
          bean.setPageStatus(checkNull(String.valueOf(data1[0][3])));
          logger.info("IndustryCode--->" + data[0][4]);
          bean.setIndustryCode(checkNull(String.valueOf(data1[0][4]).trim()));
          editFlag = "modified";
        }
        else
        {
          editFlag = "error";
        }
      }
      else
      {
        editFlag = "duplicate";
      }
    } catch (Exception e) {
      logger.error("Exception was rised---->");
    }
    return editFlag;
  }

  public boolean deleteIndustry(IndusMaster bean)
  {
    Object[][] del = new Object[1][1];

    del[0][0] = bean.getIndustryCode();

    logger.info("Desig Code del[0][0]--->" + del[0][0]);

    return getSqlModel().singleExecute(getQuery(3), del);
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void getIndustry(IndusMaster bean)
  {
    try
    {
      logger.info("bean.getDesignationCode--->" + bean.getIndustryCode());

      String query = " SELECT INDUS_NAME,INDUS_ABBRIVIATION,INDUS_DESCRIPTION,INDUS_STATUS,INDUS_CODE,DECODE(INDUS_STATUS,'A','Active','D','Deactive')  FROM HRMS_INDUS_TYPE   WHERE INDUS_CODE=" + 
        bean.getIndustryCode();
      Object[][] data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0)) {
        bean.setIndustryName(checkNull(String.valueOf(data[0][0]).trim()));
        bean.setIndustryAbbr(checkNull(String.valueOf(data[0][1]).trim()));
        bean.setIndustryDesc(checkNull(String.valueOf(data[0][2])));
        bean.setIndustryStatus(checkNull(String.valueOf(data[0][3])));
        bean.setIndustryCode(checkNull(String.valueOf(data[0][4])));
        bean.setPageStatus(checkNull(String.valueOf(data[0][5])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getIndustryRec(IndusMaster bean) {
    try {
      logger.info("bean.getDesignationCode--->" + bean.getIndustryCode());
      String query = " SELECT INDUS_NAME,INDUS_ABBRIVIATION,INDUS_DESCRIPTION,DECODE(INDUS_STATUS,'A','Active','D','Deactive'),INDUS_CODE  FROM HRMS_INDUS_TYPE   WHERE INDUS_CODE=" + 
        bean.getIndustryCode();
      Object[][] data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0)) {
        bean.setIndustryName(checkNull(String.valueOf(data[0][0]).trim()));
        bean.setIndustryAbbr(checkNull(String.valueOf(data[0][1]).trim()));
        bean.setIndustryDesc(checkNull(String.valueOf(data[0][2])));
        bean.setIndustryStatus(checkNull(String.valueOf(data[0][3])));
        bean.setIndustryCode(checkNull(String.valueOf(data[0][4])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getIndustryOnDoubleClick(IndusMaster bean) {
    try {
      String query = " SELECT NVL(INDUS_NAME,' '),NVL(INDUS_ABBRIVIATION,' '),DECODE(INDUS_STATUS,'A','Active','D','Deactive'),NVL(INDUS_DESCRIPTION,' '),INDUS_CODE,INDUS_STATUS  FROM HRMS_INDUS_TYPE WHERE INDUS_CODE=" + 
        bean.getHiddencode();

      Object[][] data = getSqlModel().getSingleResult(query);

      if ((data != null) && (data.length > 0))
      {
        bean.setIndustryName(String.valueOf(data[0][0]).trim());
        bean.setIndustryAbbr(String.valueOf(data[0][1]).trim());
        bean.setPageStatus(String.valueOf(data[0][2]));
        bean.setIndustryDesc(String.valueOf(data[0][3]).trim());
        bean.setIndustryCode(String.valueOf(data[0][4]));
        bean.setIndustryStatus(checkNull(String.valueOf(data[0][5])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getRecords(IndusMaster bean, HttpServletRequest request)
  {
    try
    {
      int length = 0;
      String query = " SELECT NVL(INDUS_NAME,' '),NVL(INDUS_ABBRIVIATION,' '),CASE WHEN INDUS_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(INDUS_DESCRIPTION,' '),INDUS_CODE  FROM HRMS_INDUS_TYPE ORDER BY INDUS_CODE";

      Object[][] data = getSqlModel().getSingleResult(query);

      ArrayList obj = new ArrayList();
      String[] pageIndex = Utility.doPaging(bean.getMyPage(), data.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }
      request.setAttribute("abc", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
      request.setAttribute("xyz", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1"))
        bean.setMyPage("1");
      for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++)
      {
        IndusMaster bean1 = new IndusMaster();
        bean1.setIndustryName(String.valueOf(data[i][0]).trim());
        bean1.setIndustryAbbr(String.valueOf(data[i][1]).trim());
        bean1.setIndustryStatus(String.valueOf(data[i][2]));
        bean1.setIndustryCode(String.valueOf(data[i][4]));
        obj.add(bean1);
      }
      bean.setIndustryList(obj);
      length = data.length;
      bean.setTotalRecords(String.valueOf(length));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean delChkdRec(IndusMaster bean, String[] code)
  {
    boolean result = false;
    int count = 0;
    if (code != null)
    {
      for (int i = 0; i < code.length; i++) {
        if (!code[i].equals("")) {
          Object[][] delete = new Object[1][1];
          delete[0][0] = code[i];
          String query = "DELETE FROM HRMS_INDUS_TYPE WHERE INDUS_CODE=? ";
          result = getSqlModel().singleExecute(query, delete);
          if (!result) {
            count++;
          }
        }
      }
    }
    if (count != 0)
    {
      result = false;
      return result;
    }

    result = true;
    return true;
  }

  public boolean checkDuplicate(IndusMaster bean)
  {
    boolean result = false;
    String query = "SELECT * FROM HRMS_INDUS_TYPE WHERE UPPER(INDUS_NAME) LIKE '" + 
      bean.getIndustryName().trim().toUpperCase() + "'";

    Object[][] data = getSqlModel().getSingleResult(query);
    if ((data != null) && (data.length > 0)) {
      result = true;
    }
    return result;
  }

  public boolean checkDuplicateMod(IndusMaster bean)
  {
    boolean result = false;
    Object[] value = new Object[1];
    Object[][] data = (Object[][])null;
    String indusName = bean.getIndustryName().trim().toUpperCase();
    try {
      value[0] = bean.getIndustryName().trim().toUpperCase();
    } catch (Exception e) {
      logger.error("in catch block exception ======", e);
    }
    try {
      String query = "SELECT * FROM HRMS_INDUS_TYPE WHERE UPPER(INDUS_NAME)=?AND INDUS_CODE not in(" + 
        bean.getIndustryCode() + ")";
      data = getSqlModel().getSingleResult(query, value);
    } catch (Exception e) {
      logger.error("exception rised catch block ========", e);
    }
    if ((data != null) && (data.length > 0)) {
      result = true;
    }
    logger.info("bean.getIndustryCode()--->" + bean.getIndustryCode());
    logger.info("result in checkDuplicateMod--->" + result);
    return result;
  }

  public void generateReport(IndusMaster indusMaster, HttpServletResponse response, String[] label)
  {
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String toDay = sdf.format(today);
    String reportName = "Industry Master";
    ReportGenerator rg = new ReportGenerator("Pdf", reportName);
    rg.setFName("Industry Master.Pdf");
    String queryDes = "SELECT INDUS_NAME,INDUS_ABBRIVIATION,DECODE(INDUS_STATUS,'A','Active','D','Deactive') FROM HRMS_INDUS_TYPE ORDER BY INDUS_CODE";

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
      int[] cellwidth = { 5, 20, 20, 20 };
      int[] alignment = { 1 };
      rg.addTextBold("Industry Master", 0, 1, 0);
      rg.addText("\n", 0, 0, 0);
      rg.addTextBold("Date :" + toDay, 0, 2, 0);
      rg.addText("\n", 0, 0, 0);
      rg.tableBody(label, Data, cellwidth, alignment);
    } else {
      rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
    }

    rg.createReport(response);
  }
}