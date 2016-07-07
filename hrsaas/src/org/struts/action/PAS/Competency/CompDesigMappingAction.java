package org.struts.action.PAS.Competency;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompDesigMapping;
import org.paradyne.model.PAS.Competency.CompDesignMappingModel;
import org.struts.lib.ParaActionSupport;

public class CompDesigMappingAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(CompDesigMappingAction.class);
  CompDesigMapping bean = null;

  public Object getModel() {
    return this.bean;
  }

  public void prepare_local() throws Exception {
    this.bean = new CompDesigMapping();
    this.bean.setMenuCode(2267);
  }

  public String input() {
    try {
      CompDesignMappingModel model = new CompDesignMappingModel();
      model.initiate(this.context, this.session);
      model.competencyDesigMappingListData(this.bean, this.request);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    getNavigationPanel(2);
    return "CompDesignMappingData";
  }

  public String delete() throws Exception {
    CompDesignMappingModel model = new CompDesignMappingModel();
    model.initiate(this.context, this.session);
    String compCode = this.request.getParameter("compCode");
    String desgCode = this.request.getParameter("desgCode");
    String[] srNoIt = this.request.getParameterValues("srNoIt");
    String[] desigNameIt = this.request.getParameterValues("desigNameIt");
    String[] desigCodeIt = this.request.getParameterValues("desigCodeIt");
    String[] compNameIt = this.request.getParameterValues("competencyNameIt");
    String[] comCodeIt = this.request.getParameterValues("competencyCodeIt");
    String[] wtnameIt = this.request.getParameterValues("weightNameIt");
    String[] catgryIt = this.request.getParameterValues("categoryIt");
    String[] catgryCodeIt = this.request.getParameterValues("categoryCodeIt");
    String[] proLevelIt = this.request.getParameterValues("proLevelIt");

    model.deleteCompetency(this.bean, compCode, desgCode, srNoIt, desigNameIt, desigCodeIt, compNameIt, comCodeIt, wtnameIt, catgryIt, catgryCodeIt, proLevelIt);
    this.bean.setHiddenEdit("");
    model.terminate();
    this.bean.setDesigCode("");
    getNavigationPanel(2);
    return "CompDesignMappingData";
  }
  public String editListData() throws Exception {
    CompDesignMappingModel model = new CompDesignMappingModel();
    model.initiate(this.context, this.session);
    model.calforedit(this.bean, this.request);
    getNavigationPanel(2);
    model.terminate();
    return "CompDesignMappingData";
  }

  public String f9desg()
    throws Exception
  {
    String query = " SELECT  RANK_ID,RANK_NAME\tFROM HRMS_RANK  ";

    String[] headers = { getMessage("desgCode"), getMessage("desgName") };

    String[] headerWidth = { "15", "35" };

    String[] fieldNames = { "desgCode", "desgName" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "true";

    String submitToMethod = "CompDesigMapping_showCompetecnyListData.action";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
  public String showCompetecnyListData() {
    try {
      CompDesignMappingModel model = new CompDesignMappingModel();
      model.initiate(this.context, this.session);
      model.showCompetencyData(this.bean, this.request);
      chk();
      model.terminate();
      getNavigationPanel(2);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "CompDesignMappingData";
  }

  public String f9compAction()
    throws Exception
  {
    String query = " SELECT COMPETENCY_CODE, COMPETENCY_TITLE FROM HRMS_COMPETENCY_HDR ORDER BY  COMPETENCY_CODE ";

    String[] headers = { getMessage("competencyCode"), getMessage("competencyName") };

    String[] headerWidth = { "30", "30" };

    String[] fieldNames = { "competencyCode", "competencyName" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9levelAction()
    throws Exception
  {
    String query = " SELECT  COMPETENCY_LEVEL FROM HRMS_COMPETENCY_DTL  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_DTL.COMPETENCY_CODE) WHERE HRMS_COMPETENCY_HDR.COMPETENCY_TITLE='" + 
      this.bean.getCompetencyName() + "'" + 
      " ORDER BY COMPETENCY_LEVEL";

    String[] headers = { getMessage("profreciencyLevel") };

    String[] headerWidth = { "30" };

    String[] fieldNames = { "proLevel" };

    int[] columnIndex = new int[1];

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9categoryAction()
    throws Exception
  {
    String query = " SELECT  COMP_CATEGORY,COMP_CATEGORY_ID FROM HRMS_COMP_CATEGORIES  ORDER BY COMP_CATEGORY_ID";

    String[] headers = { getMessage("category") };

    String[] headerWidth = { "30" };

    String[] fieldNames = { "category", "categoryCode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
  public String addItem() {
    try {
      String result = "";
      String srNo = this.bean.getSrNoIt();
      String[] srNoIterator = this.request.getParameterValues("srNoIt");
      String[] desigNameIt = this.request.getParameterValues("desigNameIt");
      String[] desigCodeIt = this.request.getParameterValues("desigCodeIt");
      String[] compNameIt = this.request.getParameterValues("competencyNameIt");
      String[] compCodeIt = this.request.getParameterValues("competencyCodeIt");
      String[] wtNameIt = this.request.getParameterValues("weightNameIt");

      String[] catgryIt = this.request.getParameterValues("categoryIt");
      String[] catgryCodeIt = this.request.getParameterValues("categoryCodeIt");
      String[] proLevelIt = this.request.getParameterValues("proLevelIt");

      CompDesignMappingModel model = new CompDesignMappingModel();
      model.initiate(this.context, this.session);

      if (this.bean.getHiddenEdit().equals("")) {
        boolean res = model.addItem(this.bean, srNoIterator, desigNameIt, desigCodeIt, compNameIt, compCodeIt, wtNameIt, catgryIt, catgryCodeIt, proLevelIt);

        if (res) {
          addActionMessage("Duplicate records should not allowed");
          getNavigationPanel(2);
          return "CompDesignMappingData";
        }
      } else {
        boolean res = model.moditem(this.bean, srNoIterator, desigNameIt, desigCodeIt, compNameIt, compCodeIt, wtNameIt, catgryIt, catgryCodeIt, proLevelIt);
        if (res) {
          addActionMessage("Duplicate records should not allowed");
          getNavigationPanel(2);
          return "CompDesignMappingData";
        }
      }
      this.bean.setCompetencyName("");
      this.bean.setCompetencyCode("");
      this.bean.setCompWeightname("");
      this.bean.setOldvalue("");
      this.bean.setProLevel("");
      this.bean.setCategory("");
      this.bean.setCategoryCode("");
      this.bean.setHiddenEdit("");
      model.terminate();
      getNavigationPanel(2);
      this.bean.setEnableAll("Y");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "CompDesignMappingData";
  }

  public String save()
  {
    try {
      String[] srNoIt = this.request.getParameterValues("srNoIt");
      String[] desigCodeIt = this.request.getParameterValues("desigCodeIt");
      String[] comCodeIt = this.request.getParameterValues("competencyCodeIt");
      String[] wtnameIt = this.request.getParameterValues("weightNameIt");
      String[] catCodeIt = this.request.getParameterValues("categoryCodeIt");
      String[] proLevelIt = this.request.getParameterValues("proLevelIt");
      CompDesignMappingModel model = new CompDesignMappingModel();
      model.initiate(this.context, this.session);
      boolean result = model.saveListData(this.bean, desigCodeIt, comCodeIt, wtnameIt, proLevelIt, catCodeIt, this.request);
      if (result)
        addActionMessage("Record saved succesfully");
      else {
        addActionMessage("Record not saved succesfully");
      }

      this.bean.setDesgCode("");
      this.bean.setDesgName("");
      this.bean.setCompetencyName("");
      this.bean.setCompetencyCode("");
      this.bean.setCompWeightname("");
      this.bean.setProLevel("");
      this.bean.setCategory("");
      this.bean.setCategoryCode("");
      this.bean.setHiddenEdit("");

      model.getComDesgMappingData(this.bean, desigCodeIt);
      getNavigationPanel(2);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "CompDesignMappingData";
  }

  public String f9action()
    throws Exception
  {
    String query = " SELECT  RANK_NAME,RANK_ID \tFROM HRMS_RANK  INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE=HRMS_RANK.RANK_ID)";

    String[] headers = { getMessage("desgName") };

    String[] headerWidth = { "50" };

    String[] fieldNames = { "desigName", "desigCode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "true";

    String submitToMethod = "CompDesigMapping_showRecordData.action";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String showRecordData()
  {
    try {
      CompDesignMappingModel model = new CompDesignMappingModel();
      model.initiate(this.context, this.session);
      System.out.println("Code1" + this.bean.getDesgCode());
      System.out.println("2" + this.bean.getDesigCode());
      System.out.println("Code Name================" + this.bean.getDesigName());
      model.showRecord(this.bean, this.request);
      getNavigationPanel(3);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "CompDesignMappingData";
  }

  public String deleteListData()
  {
    boolean result = false;
    String[] desgCodeIt = this.request.getParameterValues("desigCodeIt");
    CompDesignMappingModel model = new CompDesignMappingModel();
    model.initiate(this.context, this.session);
    result = model.deleteData(this.bean, desgCodeIt);
    if (result)
      addActionMessage("Record deleted succesfully");
    else {
      addActionMessage("Record not deleted succesfully");
    }
    model.competencyDesigMappingListData(this.bean, this.request);
    model.terminate();
    getNavigationPanel(1);
    return "input";
  }

  public String reset()
    throws Exception
  {
    try
    {
      this.bean.setDesgCode("");
      this.bean.setDesgName("");
      this.bean.setCompetencyName("");
      this.bean.setCompetencyCode("");
      this.bean.setProLevel("");
      this.bean.setDesigCodeIt("");
      this.bean.setDesigNameIt("");
      this.bean.setCompetencyNameIt("");
      this.bean.setCompetencyCodeIt("");
      this.bean.setProLevelIt("");
      this.bean.setCategory("");
      this.bean.setCategoryCode("");
      this.bean.setEditFlag("false");
      this.bean.setEnableAll("N");
      this.bean.setTotalWeightage("");
      getNavigationPanel(2);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Error in reset");
    }
    return "CompDesignMappingData";
  }

  public void chk() throws Exception {
    this.bean.setCompetencyName("");
    this.bean.setCompetencyCode("");
    this.bean.setProLevel("");
    this.bean.setDesigCodeIt("");
    this.bean.setDesigNameIt("");
    this.bean.setCompetencyNameIt("");
    this.bean.setCompetencyCodeIt("");
    this.bean.setWeightNameIt("");
    this.bean.setProLevelIt("");
    this.bean.setCategory("");
    this.bean.setCategoryCode("");
    this.bean.setCategoryIt("");
    this.bean.setCategoryCodeIt("");
  }
}