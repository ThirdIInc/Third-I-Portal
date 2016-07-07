package org.struts.action.leave;

import org.apache.log4j.Logger;
import org.paradyne.bean.leave.LeaveMisReport;
import org.paradyne.model.leave.LeaveMisReportModel;
import org.struts.lib.ParaActionSupport;

public class LeaveMisReportAction extends ParaActionSupport
{
  private static final long serialVersionUID = -5038297292374565829L;
  LeaveMisReport leaveMisReport;
  static Logger logger = Logger.getLogger(ParaActionSupport.class);

  public void setLeaveMisReport(LeaveMisReport leaveMisReport)
  {
    this.leaveMisReport = leaveMisReport;
  }

  public LeaveMisReport getLeaveMisReport()
  {
    return this.leaveMisReport;
  }

  public Object getModel() {
    return this.leaveMisReport;
  }

  public void prepare_local()
    throws Exception
  {
    this.leaveMisReport = new LeaveMisReport();
    this.leaveMisReport.setMenuCode(186);
  }

  public void prepare_withLoginProfileDetails() throws Exception
  {
    if (this.leaveMisReport.isGeneralFlag()) {
    	System.out.println("leaveMisReport.isGeneralFlag()------------------"+leaveMisReport.isGeneralFlag());
      LeaveMisReportModel model = new LeaveMisReportModel();
      model.initiate(this.context, this.session);
      model.getEmployeeDetails(this.leaveMisReport.getUserEmpId(), 
        this.leaveMisReport);
      model.terminate();
    }
  }

  public String report(){
    LeaveMisReportModel model = new LeaveMisReportModel();
    model.initiate(this.context, this.session);
    String reportPath = "";
    model.getReport(this.leaveMisReport, this.request, this.response, reportPath);
    model.terminate();
    clear();
    return null;
  }
  
  public final String mailReport() {
	    try {
	      LeaveMisReportModel model = new LeaveMisReportModel();
	      model.initiate(this.context, this.session);
	      String poolName = String.valueOf(this.session
	        .getAttribute("session_pool"));
	      if ((!poolName.equals("")) && (poolName != null)) {
	        poolName = "/" + poolName;
	      }
	      String reportPath = getText("data_path") + "/Report/Master" + 
	        poolName + "/";
	      model.getReport(this.leaveMisReport, this.request, this.response, reportPath);
	      model.terminate();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "mailReport";
	  }
  
  public String clear()
  {
    if (!this.leaveMisReport.isGeneralFlag())
    {
      this.leaveMisReport.setEmpId("");
      this.leaveMisReport.setEmpName("");
      this.leaveMisReport.setToken("");
      this.leaveMisReport.setDivCode("");
      this.leaveMisReport.setDivName("");
    }

    this.leaveMisReport.setRank("");
    this.leaveMisReport.setCenterNo("");
    this.leaveMisReport.setCenterName("");
    this.leaveMisReport.setCenter("");
    this.leaveMisReport.setFromDate("");
    this.leaveMisReport.setToDate("");
    this.leaveMisReport.setCategory("");
    this.leaveMisReport.setEmpType("");
    this.leaveMisReport.setDeptName("");
    this.leaveMisReport.setDesgName("");
    this.leaveMisReport.setLevType("");
    this.leaveMisReport.setDeptCode("");
    this.leaveMisReport.setDesgCode("");
    this.leaveMisReport.setLevCode("");
    this.leaveMisReport.setReportType("");
    return "success";
  }

  public String leaveStatus() {
    LeaveMisReportModel model = new LeaveMisReportModel();
    model.initiate(this.context, this.session);
    boolean result = model.generateLeaveStatus(this.leaveMisReport);
    if (result) {
      addActionMessage("Record updated successfully.");
    }
    model.terminate();
    return "success";
  }

  public String chk()
    throws Exception
  {
    this.leaveMisReport.setCenterNo("");
    this.leaveMisReport.setCenterName("");

    this.leaveMisReport.setServiceStatus("");
    this.leaveMisReport.setEmpType("");
    this.leaveMisReport.setLevCode("");
    this.leaveMisReport.setLevType("");
    this.leaveMisReport.setTypeCode("");
    this.leaveMisReport.setDeptName("");
    this.leaveMisReport.setDesgName("");
    this.leaveMisReport.setDeptCode("");
    this.leaveMisReport.setDesgCode("");

    return "success";
  }

  public String chk1()
    throws Exception
  {
    this.leaveMisReport.setEmpId("");
    this.leaveMisReport.setEmpName("");
    this.leaveMisReport.setRank("");
    this.leaveMisReport.setCenter("");
    this.leaveMisReport.setToken("");

    return "success";
  }

  public String f9type()
    throws Exception
  {
    try
    {
      String query = " SELECT    TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE WHERE HRMS_EMP_TYPE.IS_ACTIVE='Y' ORDER BY HRMS_EMP_TYPE.TYPE_ID";

      String header = getMessage("type.name");

      String textAreaId = "paraFrm_leaveMisReport_empType";
      String hiddenFieldId = "paraFrm_leaveMisReport_typeCode";

      String submitFlag = "false";
      String submitToMethod = "";
      setMultiSelectF9(query, header, textAreaId, hiddenFieldId, 
        submitFlag, submitToMethod);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "f9multiSelect";
  }

  public String f9action()
    throws Exception
  {
    String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,  CENTER_NAME,RANK_NAME,EMP_ID FROM HRMS_EMP_OFFC LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) LEFT JOIN HRMS_RANK   ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) ";

   if(! this.leaveMisReport.getDivCode().equals("") && this.leaveMisReport.getDivCode().length() >0 ){
	   query = query + getprofileQuery(this.leaveMisReport);
	   query = query + " AND EMP_DIV IN(" + this.leaveMisReport.getDivCode() + ")";
   }
    query = query + " AND HRMS_EMP_OFFC.EMP_STATUS='S' ORDER BY EMP_ID ASC ";
    String[] headers = { getMessage("employee.id"), getMessage("employee") };
    String[] headerWidth = { "15", "35" };
    String[] fieldNames = { "leaveMisReport.token", 
      "leaveMisReport.empName", "leaveMisReport.center", 
      "leaveMisReport.rank", "leaveMisReport.empId" };
    int[] columnIndex = { 0, 1, 2, 3, 4 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9div()
    throws Exception
  {
    try
    {
      String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION ";
      query = query + " WHERE IS_ACTIVE= 'Y' ";
      if (!this.leaveMisReport.getUserProfileDivision().equals("")) {
        query = query + " AND HRMS_DIVISION.DIV_ID IN (" + 
          this.leaveMisReport.getUserProfileDivision() + ")";
      }
      query = query + " ORDER BY  HRMS_DIVISION.DIV_ID ";

      String textAreaId = "paraFrm_leaveMisReport_divName";
      String hiddenFieldId = "paraFrm_leaveMisReport_divCode";

      String submitFlag = "false";
      String submitToMethod = "";
      setMultiSelectF9(query, getMessage("Division"), textAreaId, 
        hiddenFieldId, submitFlag, submitToMethod);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "f9multiSelect";
  }

  public String f9dept()
    throws Exception
  {
    try
    {
      String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT WHERE HRMS_DEPT.IS_ACTIVE='Y' ORDER BY HRMS_DEPT.DEPT_ID";

      String header = getMessage("dept.name");

      String textAreaId = "paraFrm_leaveMisReport_deptName";
      String hiddenFieldId = "paraFrm_leaveMisReport_deptCode";

      String submitFlag = "false";
      String submitToMethod = "";
      setMultiSelectF9(query, header, textAreaId, hiddenFieldId, 
        submitFlag, submitToMethod);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "f9multiSelect";
  }

  public String f9desg()
    throws Exception
  {
    try
    {
      String query = " SELECT DISTINCT  HRMS_RANK.RANK_ID, NVL(HRMS_RANK.RANK_NAME,' ') FROM HRMS_RANK  WHERE HRMS_RANK.IS_ACTIVE='Y' ORDER BY HRMS_RANK.RANK_ID";

      String header = getMessage("dept.name");

      String textAreaId = "paraFrm_leaveMisReport_desgName";
      String hiddenFieldId = "paraFrm_leaveMisReport_desgCode";
      String submitFlag = "false";
      String submitToMethod = "";
      setMultiSelectF9(query, header, textAreaId, hiddenFieldId, 
        submitFlag, submitToMethod);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "f9multiSelect";
  }

  public String f9ltypeaction()
    throws Exception
  {
    try
    {
      String query = " SELECT  HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME\tFROM HRMS_LEAVE  WHERE HRMS_LEAVE.IS_ACTIVE='Y'  ORDER BY HRMS_LEAVE.LEAVE_ID";

      String header = getMessage("levtype");

      String textAreaId = "paraFrm_leaveMisReport_levType";
      String hiddenFieldId = "paraFrm_leaveMisReport_levCode";
      String submitFlag = "false";
      String submitToMethod = "";
      setMultiSelectF9(query, header, textAreaId, hiddenFieldId, 
        submitFlag, submitToMethod);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "f9multiSelect";
  }

  public String f9center()
    throws Exception
  {
    try
    {
      String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER WHERE HRMS_CENTER.IS_ACTIVE='Y'";
      query = query + " ORDER BY HRMS_CENTER.CENTER_ID";

      String header = getMessage("center.name");

      String textAreaId = "paraFrm_leaveMisReport_centerName";
      String hiddenFieldId = "paraFrm_leaveMisReport_centerNo";

      String submitFlag = "false";
      String submitToMethod = "";
      setMultiSelectF9(query, header, textAreaId, hiddenFieldId, 
        submitFlag, submitToMethod);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "f9multiSelect";
  }
}