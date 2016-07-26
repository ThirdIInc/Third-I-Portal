package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.Recruitment.ConvertToEmp;
import org.paradyne.lib.SqlModel;
import org.paradyne.model.recruitment.ConvertToEmpModel;
import org.struts.lib.ParaActionSupport;

public class ConvertToEmpAction extends ParaActionSupport
{
  ConvertToEmp convertEmp = null;
  static Logger logger = Logger.getLogger(PostResumeAction.class);

  public void prepare_local() throws Exception {
    this.convertEmp = new ConvertToEmp();
    this.convertEmp.setMenuCode(822);
  }

  public Object getModel() {
    return this.convertEmp;
  }

  public void prepare_withLoginProfileDetails() throws Exception
  {
  }

  public ConvertToEmp getConvertEmp() {
    return this.convertEmp;
  }

  public void setConvertEmp(ConvertToEmp convertEmp) {
    this.convertEmp = convertEmp;
  }

  public String reset()
  {
    try {
      this.convertEmp.setBranchCode("");
      this.convertEmp.setBranchName("");
      this.convertEmp.setDeptCode("");
      this.convertEmp.setDeptName("");
      this.convertEmp.setDesgCode("");
      this.convertEmp.setDesgName("");
      this.convertEmp.setDivisionCode("");
      this.convertEmp.setDivisionName("");
      this.convertEmp.setSrNo("");
      this.convertEmp.setShowFilterValue(true);
      this.convertEmp.setChkSerch("");
      input();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "success";
  }

  public String showRec() {
    try {
      getNavigationPanel(1);
      ConvertToEmpModel model = new ConvertToEmpModel();
      model.initiate(this.context, this.session);
      String code = this.request.getParameter("reqCode");
      String candCode = this.request.getParameter("candCode");
      String publishCodeItr = this.request.getParameter("publishCodeItr");

      this.convertEmp.setAppointmentCode(publishCodeItr);
      model.getRec(code, this.convertEmp, candCode);
      String reqname = getMessage("reqs.code");
      String position = getMessage("position");
      String candname = getMessage("cand.name");
      String emp = getMessage("employee.type");
      model.getRecords(this.convertEmp, this.request);

      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String input() throws Exception {
    try {
      getNavigationPanel(1);

      this.request.setAttribute("stat", "Convert Employee Due List");
      ConvertToEmpModel model = new ConvertToEmpModel();
      model.initiate(this.context, this.session);
      String reqname = getMessage("reqs.code");
      String position = getMessage("position");
      String candname = getMessage("cand.name");
      String emp = getMessage("employee.type");
      model.getRecords(this.convertEmp, this.request);
      this.convertEmp.setBranchCode("");
      this.convertEmp.setBranchName("");
      this.convertEmp.setDeptCode("");
      this.convertEmp.setDeptName("");
      this.convertEmp.setDesgCode("");
      this.convertEmp.setDesgName("");
      this.convertEmp.setDivisionCode("");
      this.convertEmp.setDivisionName("");
      this.convertEmp.setSrNo("");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String showCandList() throws Exception
  {
    try {
      getNavigationPanel(1);
      String stat = "";
      String status = this.request.getParameter("status");
      if (status.equals("N")) {
        this.convertEmp.setRadioFlag("N");
        stat = "Convert Employee Due List";
      }
      String code = this.request.getParameter("reqCode");
      String candCode = this.request.getParameter("candCode");
      this.request.setAttribute("stat", stat);
      ConvertToEmpModel model = new ConvertToEmpModel();
      model.initiate(this.context, this.session);
      model.getRecords(this.convertEmp, this.request);
      model.getRec(code, this.convertEmp, candCode);

      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String search() throws Exception {
    try {
      getNavigationPanel(1);
      this.request.setAttribute("stat", "Convert Employee Due List");
      ConvertToEmpModel model = new ConvertToEmpModel();
      model.initiate(this.context, this.session);
      String status = "N";
      String stat = "Convert Employee Due List";
      this.request.setAttribute("stat", stat);
      this.convertEmp.setApplyFilterFlag("true");
      this.convertEmp.setEnableFilterValue(true);
      this.convertEmp.setMyPage("1");
      this.convertEmp.setSearchFlag("true");
      String reqname = getMessage("reqs.code");
      String position = getMessage("position");
      String candname = getMessage("cand.name");
      String emp = getMessage("employee.type");
      model.getSearch(this.convertEmp, reqname, position, candname, emp, 
        this.request);
      this.convertEmp.setChkSerch("true");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String showEmpList() {
    try {
      this.convertEmp.setEmplFlag("true");
      getNavigationPanel(2);
      String stat = "";
      String status = this.request.getParameter("status");
      if (status.equals("Y")) {
        this.convertEmp.setRadioFlag("Y");
        stat = "Converted Employee List";
      }
      this.request.setAttribute("stat", stat);
      ConvertToEmpModel model = new ConvertToEmpModel();
      model.initiate(this.context, this.session);
      model.getEmployeeData(this.convertEmp, this.request);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String convertEmp() throws Exception
  {
    try {
      getNavigationPanel(1);

      String requisitionCode = "";
      String candidateCode = "";
      String appointmentCode = convertEmp.getHiddenappointmentCode();

      requisitionCode = this.convertEmp.getReqsCode();
      candidateCode = this.convertEmp.getCandidateCode();
    
      System.out.println("requisitionCode :: " + requisitionCode);
      System.out.println("candidateCode :: " + candidateCode);
      ConvertToEmpModel model = new ConvertToEmpModel();
      model.initiate(this.context, this.session);
      boolean result = model.convertEmp(requisitionCode, 
        candidateCode, this.convertEmp);
      if (result) {
        String updateCandidateStatus = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_SHORT_STATUS = 'E' WHERE CAND_CODE = " + 
          candidateCode;
        model.getSqlModel().singleExecute(updateCandidateStatus);
        this.convertEmp.setRadioChk("false");
        this.convertEmp.setBranchCode("");
        this.convertEmp.setBranchName("");
        this.convertEmp.setDeptCode("");
        this.convertEmp.setDeptName("");
        this.convertEmp.setDesgCode(""); 
        this.convertEmp.setDesgName("");
        this.convertEmp.setDivisionCode("");
        this.convertEmp.setDivisionName("");

       /* String updateVacancyStatisticsQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET VAC_STATUS='C' , VAC_CLOSE_DATE= SYSDATE   WHERE VAC_APPOINT_CODE = " + 
          appointmentCode + " AND VAC_REQS_CODE = " + requisitionCode + " AND VAC_APPOINT_GIVEN = 'Y' AND VAC_STATUS='O' AND ROWNUM=1";
        model.getSqlModel().singleExecute(updateVacancyStatisticsQuery);*/
        addActionMessage("Candidate converted successfully");
      } else {
        addActionMessage("Unable to convert this candidate.\n Please check the details again.");
      }
      String reqname = getMessage("reqs.code");
      String position = getMessage("position");
      String candname = getMessage("cand.name");
      String emp = getMessage("employee.type");
      model.getRecords(this.convertEmp, this.request);
      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public void viewCV() throws Exception {
    OutputStream oStream = null; this.response.getOutputStream();
    FileInputStream fsStream = null;
    String fileName = "";
    try {
      String poolName = String.valueOf(this.session.getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "/" + poolName;
      }
      fileName = this.request.getParameter("fileName");
      if ((fileName == null) && 
        (fileName.length() <= 0)) {
        fileName = "blank.doc";
      }

      String path = getText("data_path") + "/datafiles/" + poolName + "/resume/" + fileName;
      oStream = this.response.getOutputStream();
      this.response.setHeader("Content-type", "application/msword");
      this.response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");

      fsStream = new FileInputStream(path);
      int iChar;
      while ((iChar = fsStream.read()) != -1)
      {
        
        oStream.write(iChar);
      }
    }
    catch (FileNotFoundException e) {
      addActionMessage("Resume not found");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (fsStream != null) {
        fsStream.close();
      }
      if (oStream != null) {
        oStream.flush();
        oStream.close();
      }
    }
  }

  public String clearFilter() throws Exception
  {
    try
    {
      getNavigationPanel(1);
      ConvertToEmpModel model = new ConvertToEmpModel();
      model.initiate(this.context, this.session);
      this.convertEmp.setRequisitionId("");
      this.convertEmp.setRequisitionName("");
      this.convertEmp.setPositionId("");
      this.convertEmp.setPositionName("");
      this.convertEmp.setCandCode1("");
      this.convertEmp.setCandidateName1("");
      this.convertEmp.setEmployeeName("");
      this.convertEmp.setEmpTypeId("");
      this.convertEmp.setChkSerch("");
      String position = getMessage("position");
      String candname = getMessage("cand.name");
      String emp = getMessage("employee.type");
      String reqname = getMessage("reqs.code");
      model.getSearch(this.convertEmp, reqname, position, candname, emp, 
        this.request);
      this.convertEmp.setShowFilterValue(true);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String f9Division()
    throws Exception
  {
    String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";

    if ((this.convertEmp.getUserProfileDivision() != null) && (this.convertEmp.getUserProfileDivision().length() > 0))
      query = query + " WHERE DIV_ID IN (" + this.convertEmp.getUserProfileDivision() + ")";
    query = query + " ORDER BY  DIV_ID ";

    String[] headers = { getMessage("division") };

    String[] headerWidth = { "50" };

    String[] fieldNames = { "divisionName", "divisionCode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9Dept()
    throws Exception
  {
    String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT  ORDER BY DEPT_ID ";

    String[] headers = { getMessage("department") };

    String[] headerWidth = { "50" };

    String[] fieldNames = { "deptName", "deptCode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9Branch()
    throws Exception
  {
    String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER  ORDER BY  CENTER_ID ";

    String[] headers = { getMessage("branch") };

    String[] headerWidth = { "50" };

    String[] fieldNames = { "branchName", "branchCode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9Desg()
    throws Exception
  {
    String query = " SELECT DISTINCT TO_CHAR(RANK_NAME),RANK_ID FROM  HRMS_RANK   ORDER BY  RANK_ID ";

    String[] headers = { getMessage("designation") };

    String[] headerWidth = { "50" };

    String[] fieldNames = { "desgName", "desgCode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9Position()
    throws Exception
  {
    String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

    String[] headers = { getMessage("position") };

    String[] headerWidth = { "100" };

    String[] fieldNames = { "positionName", "positionId" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9EmployeeType()
    throws Exception
  {
    String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC  ORDER BY EMP_ID ";

    String[] headers = { getMessage("employee.type") };

    String[] headerWidth = { "100" };

    String[] fieldNames = { "employeeName", "empTypeId" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9Requisition()
    throws Exception
  {
    String query = "SELECT  REQS_NAME,HRMS_REC_REQS_HDR.REQS_CODE FROM HRMS_REC_REQS_HDR ORDER BY REQS_CODE ";

    String[] headers = { getMessage("reqs.code") };

    String[] headerWidth = { "20" };

    String[] fieldNames = { "requisitionName", "requisitionId" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9CandidateAction()
  {
    String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ,CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_CODE";

    String[] headers = { getMessage("cand.name") };

    String[] headerWidth = { "30", "60" };

    String[] fieldNames = { "candidateName1", "candCode1" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

    return "f9page";
  }
}