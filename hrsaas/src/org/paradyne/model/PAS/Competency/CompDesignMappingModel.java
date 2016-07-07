package org.paradyne.model.PAS.Competency;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompDesigMapping;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class CompDesignMappingModel extends ModelBase
{
  static Logger logger = Logger.getLogger(CompDesignMappingModel.class);

  public void competencyDesigMappingListData(CompDesigMapping compBean, HttpServletRequest request)
  {
    try
    {
      ArrayList list = null;

      Object[][] repData = getSqlModel().getSingleResult(getQuery(4));
      if ((repData != null) && (repData.length > 0)) {
        compBean.setModeLength("true");
        compBean.setTotalRecords(String.valueOf(repData.length));
        String[] pageIndex = Utility.doPaging(compBean.getMyPage(), 
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
          compBean.setMyPage("1");
        list = new ArrayList();
        int i = Integer.parseInt(pageIndex[0]);
        for (; i < 
          Integer.parseInt(pageIndex[1]); i++) {
          CompDesigMapping bean1 = new CompDesigMapping();
          String query = " SELECT  RANK_ID,RANK_NAME\tFROM HRMS_RANK  WHERE RANK_ID=" + 
            repData[i][0];
          Object[][] data = getSqlModel().getSingleResult(query);
          bean1.setDesigCode(checkNull(String.valueOf(data[0][0])));
          bean1.setDesigName(checkNull(String.valueOf(data[0][1])));
          list.add(bean1);
        }
        compBean.setCompDesigMappingList(list);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean moditem(CompDesigMapping compBean, String[] srNoIt, String[] desigNameIt, String[] desigCodeIt, String[] compNameIt, String[] compCodeIt, String[] wtNameIt, String[] categoryIt, String[] categoryCodeIt, String[] proLevelIt)
  {
    boolean isDuplicate = false;
    int total = 0;
    int weightage = 0;
    ArrayList tableList = new ArrayList();
    if ((desigNameIt != null) && (desigNameIt.length > 0)) {
      for (int i = 0; i < desigNameIt.length; i++) {
        if ((!desigNameIt[i].equals(compBean.getDesgName())) || 
          (!compNameIt[i].equals(compBean.getCompetencyName())) || 
          (!wtNameIt[i].equals(compBean.getCompWeightname())) || 
          (!categoryIt[i].equals(compBean.getCategory())) || 
          (!proLevelIt[i].equals(compBean.getProLevel()))) continue;
        isDuplicate = true;
      }

      for (int j = 0; j < compNameIt.length; j++) {
        System.out.println(" HIDDEN EDIT  " + compBean.getHiddenEdit());
        if (j == Integer.parseInt(compBean.getHiddenEdit()) - 1) {
          if (isDuplicate) {
            System.out.println("=======if===============");
            CompDesigMapping bean = new CompDesigMapping();
            bean.setDesigNameIt(desigNameIt[j]);
            bean.setDesigCodeIt(desigCodeIt[j]);
            bean.setCompetencyNameIt(compNameIt[j]);
            bean.setCompetencyCodeIt(compCodeIt[j]);
            bean.setWeightNameIt(wtNameIt[j]);
            bean.setCategoryIt(categoryIt[j]);
            bean.setCategoryCodeIt(categoryCodeIt[j]);
            bean.setProLevelIt(proLevelIt[j]);

            tableList.add(bean);
          }
          else {
            System.out.println("========else==============");
            CompDesigMapping bean1 = new CompDesigMapping();
            bean1.setDesigNameIt(compBean.getDesgName());
            bean1.setDesigCodeIt(compBean.getDesgCode());
            bean1.setCompetencyNameIt(compBean.getCompetencyName());
            bean1.setCompetencyCodeIt(compBean.getCompetencyCode());
            bean1.setWeightNameIt(compBean.getCompWeightname());
            bean1.setCategoryIt(compBean.getCategory());
            bean1.setCategoryCodeIt(compBean.getCategoryCode());
            bean1.setProLevelIt(compBean.getProLevel());
            tableList.add(bean1);
          }
        }
        else {
          System.out.println("==========out else============");
          CompDesigMapping bean = new CompDesigMapping();
          bean.setDesigNameIt(desigNameIt[j]);
          bean.setDesigCodeIt(desigCodeIt[j]);
          bean.setCompetencyNameIt(compNameIt[j]);
          bean.setCompetencyCodeIt(compCodeIt[j]);
          bean.setWeightNameIt(wtNameIt[j]);
          bean.setCategoryIt(categoryIt[j]);
          bean.setCategoryCodeIt(categoryCodeIt[j]);
          bean.setProLevelIt(proLevelIt[j]);

          tableList.add(bean);
        }

      }

    }

    weightage = compBean.getCompWeightname().equals("") ? 0 : 
      Integer.parseInt(compBean.getCompWeightname());

    int oldValue = compBean.getOldvalue().equals("") ? 0 : 
      Integer.parseInt(compBean.getOldvalue());

    total = total + (Integer.parseInt(compBean.getTotalWeightage()) - oldValue + 
      weightage);

    compBean.setCompDataList(tableList);
    compBean.setTotalWeightage(String.valueOf(total));
    return isDuplicate;
  }

  public boolean addItem(CompDesigMapping compBean, String[] srNoIterator, String[] desigNameIt, String[] desigCodeIt, String[] compNameIt, String[] compCodeIt, String[] wtNameIt, String[] categoryIt, String[] categoryCodeIt, String[] proLevelIt)
  {
    boolean isDuplicate = false;
    int total = 0;
    try {
      ArrayList list = new ArrayList();
      if ((srNoIterator != null) && (srNoIterator.length > 0)) {
        for (int i = 0; i < desigNameIt.length; i++) {
          CompDesigMapping innerBean = new CompDesigMapping();
          innerBean.setCompetencyNameIt(compNameIt[i]);
          if (innerBean.getCompetencyNameIt().equals(
            compBean.getCompetencyName())) {
            isDuplicate = true;
          }

          innerBean.setCompetencyCodeIt(compCodeIt[i]);

          innerBean.setDesigCodeIt(desigCodeIt[i]);
          innerBean.setDesigNameIt(desigNameIt[i]);
          innerBean.setCategoryIt(categoryIt[i]);
          innerBean.setCategoryCodeIt(categoryCodeIt[i]);
          innerBean.setProLevelIt(proLevelIt[i]);

          innerBean.setWeightNameIt(wtNameIt[i]);

          list.add(innerBean);
        }
      }

      if (!isDuplicate) {
        System.out.println("=======||||||||||||=======");
        CompDesigMapping innerBean = new CompDesigMapping();
        innerBean.setDesigNameIt(compBean.getDesgName());
        innerBean.setDesigCodeIt(compBean.getDesgCode());
        innerBean.setCompetencyNameIt(compBean.getCompetencyName());
        innerBean.setCompetencyCodeIt(compBean.getCompetencyCode());
        innerBean.setWeightNameIt(compBean.getCompWeightname());

        innerBean.setCategoryIt(compBean.getCategory());
        innerBean.setCategoryCodeIt(compBean.getCategoryCode());
        innerBean.setProLevelIt(compBean.getProLevel());

        int weightage = compBean.getCompWeightname().equals("") ? 0 : 
          Integer.parseInt(compBean.getCompWeightname());

        int finalTotal = compBean.getTotalWeightage().equals("") ? 0 : 
          Integer.parseInt(compBean.getTotalWeightage());

        total = finalTotal + weightage;
        list.add(innerBean);
      }
      else {
        int finalTotal = compBean.getTotalWeightage().equals("") ? 0 : 
          Integer.parseInt(compBean.getTotalWeightage());

        total = finalTotal;
      }
      compBean.setCompDataList(list);
      compBean.setTotalWeightage(String.valueOf(total));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return isDuplicate;
  }

  public boolean saveListData(CompDesigMapping compBean, String[] desigCodeIt, String[] comCodeIt, String[] wtnameIt, String[] proLevelIt, String[] catgyCodeIt, HttpServletRequest request)
  {
    boolean result = false;
    if ((desigCodeIt.length > 0) && (desigCodeIt != null)) {
      Object[][] compDesgMapData = new Object[desigCodeIt.length][5];
      for (int i = 0; i < compDesgMapData.length; i++) {
        compDesgMapData[i][0] = 
          checkNull(String.valueOf(desigCodeIt[i]));
        compDesgMapData[i][1] = checkNull(String.valueOf(comCodeIt[i]));
        compDesgMapData[i][2] = checkNull(String.valueOf(proLevelIt[i]));
        compDesgMapData[i][3] = 
          checkNull(String.valueOf(catgyCodeIt[i]));
        compDesgMapData[i][4] = checkNull(String.valueOf(wtnameIt[i]));
      }
      String code = "";
      for (int i = 0; i < desigCodeIt.length; i++) {
        if (i == 0)
          code = desigCodeIt[i];
        else
          code = code + "," + desigCodeIt[i];
      }
      String delQry = "DELETE FROM HRMS_COMPETENCY_ROLE WHERE COMPETENCY_ROLE IN(" + 
        code + ")";
      result = getSqlModel().singleExecute(delQry);
      String insertQry = "INSERT INTO HRMS_COMPETENCY_ROLE(COMPETENCY_ROLE,COMPETENCY_CODE, COMPETENCY_LEVEL, COMPETENCY_TYPE,COMPETENCY_WEIGHTAGE) VALUES(?,?,?,?,?)";
      result = getSqlModel().singleExecute(insertQry, compDesgMapData);
    }
    return result;
  }

  public void getComDesgMappingData(CompDesigMapping compBean, String[] desigCodeIt)
  {
    String query = "SELECT COMPETENCY_ROLE, COMPETENCY_TITLE,HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,COMPETENCY_TYPE, COMPETENCY_LEVEL,COMPETENCY_WEIGHTAGE FROM HRMS_COMPETENCY_ROLE INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) WHERE ";

    String code = "";
    if (desigCodeIt != null) {
      for (int i = 0; i < desigCodeIt.length; i++) {
        if (i == 0)
          code = desigCodeIt[i];
        else
          code = code + "," + desigCodeIt[i];
      }
    }
    query = query + "COMPETENCY_ROLE IN (" + code + ")";
    query = query + "   ORDER BY COMPETENCY_ROLE ";

    Object[][] data = getSqlModel().getSingleResult(query);
    ArrayList list = new ArrayList();
    CompDesigMapping bean1 = null;
    for (int i = 0; i < data.length; i++) {
      String divNameQry = "SELECT RANK_ID,RANK_NAME FROM  HRMS_RANK WHERE RANK_ID=" + 
        data[i][0];
      Object[][] divName = getSqlModel().getSingleResult(divNameQry);
      bean1 = new CompDesigMapping();
      bean1.setSrNoIt(String.valueOf(i + 1));
      bean1.setDesigCodeIt(checkNull(String.valueOf(divName[0][0])));
      bean1.setDesigNameIt(checkNull(String.valueOf(divName[0][1])));
      bean1.setCompetencyNameIt(checkNull(String.valueOf(data[i][1])));
      bean1.setCompetencyCodeIt(checkNull(String.valueOf(data[i][2])));
      if (data[i][3] != null) {
        String catgryQry = "SELECT  COMP_CATEGORY,COMP_CATEGORY_ID FROM HRMS_COMP_CATEGORIES WHERE COMP_CATEGORY_ID=" + 
          data[i][3];
        Object[][] catgryData = getSqlModel()
          .getSingleResult(catgryQry);
        if ((catgryData != null) && (catgryData.length > 0)) {
          bean1.setCategoryIt(String.valueOf(catgryData[0][0]));
          bean1.setCategoryCodeIt(String.valueOf(catgryData[0][1]));
        } else {
          bean1.setCategoryIt("");
          bean1.setCategoryCodeIt("");
        }
      } else {
        bean1.setCategoryIt("");
        bean1.setCategoryCodeIt("");
      }
      bean1.setProLevelIt(checkNull(String.valueOf(data[i][4])));
      bean1.setWeightNameIt(checkNull(String.valueOf(data[i][5])));
      list.add(bean1);
    }
    compBean.setCompDataList(list);
  }

  public boolean deleteData(CompDesigMapping compBean, String[] desigCodeIt) {
    boolean result = false;
    try {
      String code = "";
      for (int i = 0; i < desigCodeIt.length; i++) {
        if (i == 0)
          code = desigCodeIt[i];
        else
          code = code + "," + desigCodeIt[i];
      }
      System.out.println("=====CODE======" + code);
      String delQry = "DELETE FROM HRMS_COMPETENCY_ROLE WHERE COMPETENCY_ROLE IN(" + 
        code + ")";
      result = getSqlModel().singleExecute(delQry);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void showRecord(CompDesigMapping compBean, HttpServletRequest request) {
    String query = "SELECT COMPETENCY_ROLE, COMPETENCY_TYPE,COMPETENCY_CODE, COMPETENCY_LEVEL FROM HRMS_COMPETENCY_ROLE WHERE ";
    System.out.println("===DESIG CODE===" + 
      request.getParameter("desigCode"));
    String code = request.getParameter("desigCode");
    if (!compBean.getDesigCode().equals("")) {
      query = query + "COMPETENCY_ROLE IN (" + code + ")";
    }
    query = query + "   ORDER BY COMPETENCY_ROLE ";
    Object[][] data = getSqlModel().getSingleResult(query);
    ArrayList list = new ArrayList();
    for (int i = 0; i < data.length; i++) {
      String divNameQry = "SELECT RANK_ID,RANK_NAME FROM  HRMS_RANK WHERE RANK_ID=" + 
        data[i][0];
      Object[][] divName = getSqlModel().getSingleResult(divNameQry);
      compBean.setDesigCodeIt(checkNull(String.valueOf(divName[0][0])));
      compBean.setDesigNameIt(checkNull(String.valueOf(divName[0][1])));
      compBean.setCompetencyNameIt(checkNull(String.valueOf(data[i][1])));
      compBean.setCompetencyCodeIt(checkNull(String.valueOf(data[i][2])));
      compBean.setProLevelIt(checkNull(String.valueOf(data[i][3])));
      list.add(compBean);
    }
    compBean.setCompDataList(list);
  }

  public void deleteCompetency(CompDesigMapping compBean, String compCode, String desgCode, String[] srNoIt, String[] desigNameIt, String[] desigCodeIt, String[] compNameIt, String[] compCodeIt, String[] wtnameIt, String[] catgryIt, String[] catgryCodeIt, String[] proLevelIt)
  {
    ArrayList tableList = new ArrayList();
    String delQry = "DELETE FROM HRMS_COMPETENCY_ROLE WHERE COMPETENCY_CODE=" + 
      compCode + " AND COMPETENCY_ROLE=" + desgCode;
    getSqlModel().singleExecute(delQry);

    if ((desigNameIt != null) && (desigNameIt.length > 0)) {
      CompDesigMapping bean1 = null;
      for (int i = 0; i < desigNameIt.length; i++) {
        if (!compCodeIt[i].equals(compCode)) {
          bean1 = new CompDesigMapping();
          bean1.setSrNoIt(String.valueOf(i + 1));
          bean1.setDesigNameIt(desigNameIt[i]);
          bean1.setDesigCodeIt(desigCodeIt[i]);
          bean1.setCompetencyNameIt(compNameIt[i]);
          bean1.setCompetencyCodeIt(compCodeIt[i]);
          bean1.setWeightNameIt(wtnameIt[i]);
          bean1.setCategoryIt(catgryIt[i]);
          bean1.setCategoryCodeIt(catgryCodeIt[i]);
          bean1.setProLevelIt(proLevelIt[i]);
          tableList.add(bean1);
        }
      }

    }

    int weightage = compBean.getCompWeightname().equals("") ? 0 : 
      Integer.parseInt(compBean.getCompWeightname());

    int oldValue = compBean.getOldvalue().equals("") ? 0 : 
      Integer.parseInt(compBean.getOldvalue());
    int total = 0;

    total = total + (Integer.parseInt(compBean.getTotalWeightage()) - oldValue + 
      weightage);

    compBean.setTotalWeightage(String.valueOf(total));

    compBean.setCompDataList(tableList);
  }

  public void showCompetencyData(CompDesigMapping compBean, HttpServletRequest request)
  {
    String query = "SELECT COMPETENCY_ROLE, COMPETENCY_TITLE,HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,COMPETENCY_TYPE, COMPETENCY_LEVEL,COMPETENCY_WEIGHTAGE FROM HRMS_COMPETENCY_ROLE INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) WHERE ";

    query = query + " HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE =" + 
      compBean.getDesgCode();
    query = query + "   ORDER BY COMPETENCY_ROLE ";
    Object[][] data = getSqlModel().getSingleResult(query);
    Object[][] catgryData = (Object[][])null;
    int total = 0;
    ArrayList list = new ArrayList();
    if ((data != null) && (data.length > 0)) {
      CompDesigMapping bean1 = null;
      for (int i = 0; i < data.length; i++) {
        String divNameQry = "SELECT RANK_ID,RANK_NAME FROM  HRMS_RANK WHERE RANK_ID=" + 
          data[i][0];
        Object[][] divName = getSqlModel().getSingleResult(divNameQry);
        bean1 = new CompDesigMapping();
        bean1.setSrNoIt(String.valueOf(i + 1));
        bean1.setDesigCodeIt(checkNull(String.valueOf(divName[0][0])));
        bean1.setDesigNameIt(checkNull(String.valueOf(divName[0][1])));
        bean1
          .setCompetencyNameIt(checkNull(
          String.valueOf(data[i][1])));
        bean1
          .setCompetencyCodeIt(checkNull(
          String.valueOf(data[i][2])));
        if (data[i][3] != null) {
          String catgryQry = "SELECT  COMP_CATEGORY,COMP_CATEGORY_ID FROM HRMS_COMP_CATEGORIES WHERE COMP_CATEGORY_ID=" + 
            data[i][3];
          catgryData = getSqlModel().getSingleResult(catgryQry);
          if ((catgryData != null) && (catgryData.length > 0)) {
            bean1.setCategoryIt(
              checkNull(String.valueOf(catgryData[0][0])));
            bean1.setCategoryCodeIt(
              checkNull(String.valueOf(catgryData[0][1])));
          } else {
            bean1.setCategoryIt("");
            bean1.setCategoryCodeIt("");
          }
        }
        else {
          bean1.setCategoryIt("");
          bean1.setCategoryCodeIt("");
        }

        bean1.setProLevelIt(checkNull(String.valueOf(data[i][4])));
        bean1.setWeightNameIt(checkNull(String.valueOf(data[i][5])));

        int competencyWeightage = compBean.getCompWeightname().equals(
          "") ? 
          0 : 
          Integer.parseInt(compBean.getCompWeightname());

        total = total + (Integer.parseInt(bean1.getWeightNameIt()) + 
          competencyWeightage);

        list.add(bean1);
      }
    }
    compBean.setTotalWeightage(String.valueOf(total));
    compBean.setCompDataList(list);
  }

  public void showData(CompDesigMapping compBean, String[] srNoIterator, String[] desigNameIt, String[] desigCodeIt, String[] compNameIt, String[] compCodeIt, String[] categoryIt, String[] categoryCodeIt, String[] proLevelIt)
  {
    String query = "SELECT COMPETENCY_ROLE, COMPETENCY_TYPE,COMPETENCY_CODE, COMPETENCY_LEVEL FROM HRMS_COMPETENCY_ROLE WHERE ";
    query = query + "COMPETENCY_ROLE =" + compBean.getDesgCode();
    query = query + "   ORDER BY COMPETENCY_ROLE ";
    Object[][] data = getSqlModel().getSingleResult(query);
    ArrayList list = new ArrayList();

    if ((data != null) && (data.length > 0)) {
      System.out.println("============ELSE((((((((9=))))))))))=========");
      CompDesigMapping bean1 = null;
      for (int i = 0; i < data.length; i++) {
        String divNameQry = "SELECT RANK_ID,RANK_NAME FROM  HRMS_RANK WHERE RANK_ID=" + 
          data[i][0];
        Object[][] divName = getSqlModel().getSingleResult(divNameQry);
        bean1 = new CompDesigMapping();
        bean1.setSrNoIt(String.valueOf(i + 1));
        bean1.setDesigCodeIt(checkNull(String.valueOf(divName[0][0])));
        bean1.setDesigNameIt(checkNull(String.valueOf(divName[0][1])));
        bean1
          .setCompetencyNameIt(checkNull(
          String.valueOf(data[i][1])));
        bean1
          .setCompetencyCodeIt(checkNull(
          String.valueOf(data[i][2])));
        bean1.setCategoryIt(checkNull(String.valueOf(data[i][3])));
        bean1.setCategoryCodeIt(checkNull(String.valueOf(data[i][4])));
        bean1.setProLevelIt(checkNull(String.valueOf(data[i][5])));
        list.add(bean1);
      }
    } else {
      System.out.println("============ELSE((((((((9==========");
      if ((srNoIterator != null) && (srNoIterator.length > 0)) {
        CompDesigMapping bean1 = null;
        for (int i = 0; i < srNoIterator.length; i++) {
          bean1 = new CompDesigMapping();
          bean1.setSrNoIt(String.valueOf(i + 1));
          bean1.setDesigCodeIt(
            checkNull(String.valueOf(desigCodeIt[i])));
          bean1.setDesigNameIt(
            checkNull(String.valueOf(desigNameIt[i])));
          bean1.setCompetencyNameIt(
            checkNull(String.valueOf(compNameIt[i])));
          bean1.setCompetencyCodeIt(
            checkNull(String.valueOf(compCodeIt[i])));
          bean1
            .setCategoryIt(checkNull(
            String.valueOf(categoryIt[i])));
          bean1.setCategoryCodeIt(
            checkNull(String.valueOf(categoryCodeIt[i])));
          bean1
            .setProLevelIt(checkNull(
            String.valueOf(proLevelIt[i])));
          list.add(bean1);
        }
      }
    }
    compBean.setCompDataList(list);
  }

  public void calforedit(CompDesigMapping compBean, HttpServletRequest request)
  {
    String query = "SELECT COMPETENCY_ROLE, COMPETENCY_TYPE,COMPETENCY_CODE, COMPETENCY_LEVEL FROM HRMS_COMPETENCY_ROLE  where ";

    if (!compBean.getHiddencode().equals("")) {
      query = query + "COMPETENCY_ROLE IN (" + compBean.getHiddencode() + 
        ")";
    } else {
      String[] desgCode = request.getParameterValues("desigCodeIt");
      String code = "";
      for (int i = 0; i < desgCode.length; i++) {
        if (i == 0)
          code = desgCode[i];
        else
          code = code + "," + desgCode[i];
      }
      query = query + "COMPETENCY_ROLE IN (" + code + ")";
    }
    query = query + "   ORDER BY COMPETENCY_ROLE ";

    Object[][] data = getSqlModel().getSingleResult(query);
    ArrayList list = new ArrayList();
    for (int i = 0; i < data.length; i++) {
      String divNameQry = "SELECT RANK_ID,RANK_NAME FROM  HRMS_RANK WHERE RANK_ID=" + 
        data[i][0];
      Object[][] divName = getSqlModel().getSingleResult(divNameQry);
      compBean.setDesigCodeIt(checkNull(String.valueOf(divName[0][0])));
      compBean.setDesigNameIt(checkNull(String.valueOf(divName[0][1])));
      compBean.setCompetencyNameIt(checkNull(String.valueOf(data[i][1])));
      compBean.setCompetencyCodeIt(checkNull(String.valueOf(data[i][2])));
      compBean.setProLevelIt(checkNull(String.valueOf(data[i][3])));
      list.add(compBean);
    }
    compBean.setCompDataList(list);
  }

  public boolean deleteEmptype(CompDesigMapping am, String[] code)
  {
    boolean result = false;
    boolean flag = false;
    if (code != null) {
      for (int i = 0; i < code.length; i++) {
        if (!code[i].equals("")) {
          Object[][] delete = new Object[1][1];
          delete[0][0] = code[i];
          flag = getSqlModel().singleExecute(getQuery(3), delete);
          if (flag)
            result = true;
        }
      }
    }
    return result;
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }
    return result;
  }
}