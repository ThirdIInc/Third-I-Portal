package org.struts.action.PAS.GoalSetting;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.GoalReportingStructure;
import org.paradyne.model.PAS.GoalSetting.GoalReportingStructureModel;
import org.struts.lib.ParaActionSupport;

public class GoalReportingStructureAction extends ParaActionSupport
{
  GoalReportingStructure goalBean;
  Logger logger = Logger.getLogger(ParaActionSupport.class);

  public GoalReportingStructure getGoalBean() { return this.goalBean; }

  public void setGoalBean(GoalReportingStructure goalBean)
  {
    this.goalBean = goalBean;
  }

  public void prepare_local() throws Exception {
    this.goalBean = new GoalReportingStructure();
    this.goalBean.setMenuCode(1172);
  }

  public Object getModel() {
    return this.goalBean;
  }

  public String input()
  {
    return "input";
  }

  public String f9Department()
    throws Exception
  {
    String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";

    String[] headers = { "Department Code", "Department Name" };
    String[] headerWidth = { "30", "70" };
    String[] fieldNames = { "deptCode", "reqDept" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "true";
    String submitToMethod = "GoalReportingStructure_showApproverData.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

    return "f9page";
  }

  public String showApproverData()
  {
    System.out.println("###########################################");
    GoalReportingStructureModel model = new GoalReportingStructureModel();
    model.initiate(this.context, this.session);
    String result = model.showApproverData(this.goalBean);
    model.terminate();
    return "input";
  }

  public String f9Branch()
    throws Exception
  {
    String query = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID";

    String[] headers = { "Branch Code", "Branch Name" };
    String[] headerWidth = { "30", "70" };
    String[] fieldNames = { "brnCode", "reqBrn" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "true";
    String submitToMethod = "GoalReportingStructure_showApproverData.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9Designation()
    throws Exception
  {
    String query = "SELECT RANK_ID, RANK_NAME FROM HRMS_RANK ORDER BY RANK_ID";

    String empId = this.goalBean.getEmpCode();
    String[] headers = { "Designation Code", "Designation Name" };
    String[] headerWidth = { "30", "70" };
    String[] fieldNames = { "designationCode", "designationName" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "true";
    String submitToMethod = "GoalReportingStructure_showApproverData.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9Selectemp()
    throws Exception
  {
    String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID FROM HRMS_EMP_OFFC LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) WHERE EMP_STATUS = 'S' ";

    if ((this.goalBean.getUserProfileDivision() != null) && 
      (this.goalBean.getUserProfileDivision().length() > 0)) {
      query = query + " AND HRMS_EMP_OFFC.EMP_DIV IN (" + 
        this.goalBean.getUserProfileDivision() + ")";
    }
    query = query + "ORDER BY EMP_ID";

    String[] headers = { "Emlpoyee Id", getMessage("reporting.employee.name") };
    String[] headerWidth = { "30", "70" };
    String[] fieldNames = { "empTokenNo", "empName", "empCode" };
    int[] columnIndex = { 0, 1, 2 };
    String submitFlag = "true";
    String submitToMethod = "GoalReportingStructure_showApproverData.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9SelectempAdd()
    throws Exception
  {
    String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS = 'S' ";

    String empId = this.goalBean.getEmpCode();
    if ((empId != null) && (!empId.equals(""))) {
      query = query + "AND EMP_ID != " + empId;
    }

    String headerCode = this.goalBean.getHdrCode();
    if ((headerCode != null) && (!headerCode.equals(""))) {
      query = query + " AND EMP_ID NOT IN(SELECT REPORTINGDTL_EMP_ID FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + headerCode + ")";
    }

    query = query + " ORDER BY EMP_ID";

    String[] headers = { getMessage("reporting.appr.id"), getMessage("reporting.appr.name") };
    String[] headerWidth = { "30", "70" };
    String[] fieldNames = { "empTokenAdd", "empNameAdd", "empId" };
    int[] columnIndex = { 0, 1, 2 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  public String addApprover()
  {
    try
    {
      String srNo = this.goalBean.getSrNo();
      String[] approverId = this.request.getParameterValues("empIdIterator");
      String[] approverToken = this.request.getParameterValues("empTokenIterator");
      String[] approverName = this.request.getParameterValues("empNameIterator");
      String[] apprreview = this.request.getParameterValues("apprreviewIterator");
      String[] view = this.request.getParameterValues("viewIterator");
      String[] rating = this.request.getParameterValues("ratingIterator");
      String[] comments = this.request.getParameterValues("commentsIterator");
      String prorityType = this.request.getParameter("type");
      System.out.println("prorityType============" + prorityType);
      GoalReportingStructureModel model = new GoalReportingStructureModel();
      model.initiate(this.context, this.session);
      model.addApprover(this.goalBean, approverId, approverToken, approverName, view, rating, comments, apprreview);
      model.terminate();
      this.goalBean.setEmpId("");
      this.goalBean.setEmpNameAdd("");
      this.goalBean.setEmpTokenAdd("");
      this.goalBean.setDesgId("");
      this.goalBean.setDesgName("");
      this.goalBean.setSrNo("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String deleteApprover()
  {
    try
    {
      String srNo = this.goalBean.getSrNo();
      String[] approverId = this.request.getParameterValues("empIdIterator");
      String[] approverToken = this.request
        .getParameterValues("empTokenIterator");
      String[] approverName = this.request
        .getParameterValues("empNameIterator");
      String[] designation = this.request
        .getParameterValues("desgNameIterator");
      String[] designationId = this.request
        .getParameterValues("desgIdIterator");
      String[] view = this.request.getParameterValues("viewIterator");
      String[] rating = this.request.getParameterValues("ratingIterator");
      String[] comments = this.request.getParameterValues("commentsIterator");
      String[] apprReview = this.request.getParameterValues("apprreviewIterator");
      GoalReportingStructureModel model = new GoalReportingStructureModel();
      model.initiate(this.context, this.session);
      model.deleteApprover(this.goalBean, srNo, approverId, approverToken, 
        approverName, designation, designationId, view, rating, comments, apprReview);
      model.terminate();
      this.goalBean.setEmpId("");
      this.goalBean.setEmpNameAdd("");
      this.goalBean.setEmpTokenAdd("");
      this.goalBean.setDesgId("");
      this.goalBean.setDesgName("");
      this.goalBean.setSrNo("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String saveReportingStructure()
  {
    try
    {
      String[] approverId = this.request.getParameterValues("empIdIterator");
      String[] approverToken = this.request.getParameterValues("empTokenIterator");
      String[] approverName = this.request.getParameterValues("empNameIterator");
      String[] designation = this.request.getParameterValues("desgIdIterator");
      String[] view = this.request.getParameterValues("viewIterator");
      String[] rating = this.request.getParameterValues("ratingIterator");
      String[] comments = this.request.getParameterValues("commentsIterator");
      String[] apprReview = this.request.getParameterValues("apprreviewIterator");

      GoalReportingStructureModel model = new GoalReportingStructureModel();
      model.initiate(this.context, this.session);
      boolean result = model.saveReportingStructure(this.goalBean, approverId, view, rating, comments, apprReview);
      if (result) {
        addActionMessage(getText("addMessage", ""));
        reset();
      } else {
        addActionMessage("Record can not be saved");
        addApprover();
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String shuffleColumnsAction()
  {
    String srNo = this.goalBean.getSrNo();
    String buttonType = this.request.getParameter("type");
    String[] approverId = this.request.getParameterValues("empIdIterator");
    String[] approverToken = this.request.getParameterValues("empTokenIterator");
    String[] approverName = this.request.getParameterValues("empNameIterator");
    String[] designation = this.request.getParameterValues("desgNameIterator");
    String[] designationId = this.request.getParameterValues("desgIdIterator");
    String[] srNoIterator = this.request.getParameterValues("srNoIterator");
    String[] apprReview = this.request.getParameterValues("apprreviewIterator");
    String[] view = this.request.getParameterValues("viewIterator");
    String[] rating = this.request.getParameterValues("ratingIterator");
    String[] comments = this.request.getParameterValues("commentsIterator");

    GoalReportingStructureModel model = new GoalReportingStructureModel();
    model.initiate(this.context, this.session);

    model.shuffleColumns(this.goalBean, srNo, approverId, approverToken, approverName, designation, 
      designationId, srNoIterator, buttonType, view, rating, comments, apprReview);
    this.goalBean.setSrNo("");
    model.terminate();
    return "input";
  }

  public String viewApproverListReportStructure()
  {
    try
    {
      GoalReportingStructureModel model = new GoalReportingStructureModel();
      model.initiate(this.context, this.session);
      model.genApproverListReport(this.goalBean, this.request, this.response);
      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String reset() {
    try {
      this.goalBean.setDeptCode("");
      this.goalBean.setReqDept("");
      this.goalBean.setBrnCode("");
      this.goalBean.setReqBrn("");
      this.goalBean.setEmpCode("");
      this.goalBean.setEmpId("");
      this.goalBean.setEmpName("");
      this.goalBean.setEmpNameAdd("");
      this.goalBean.setDesgId("");
      this.goalBean.setDesgName("");
      this.goalBean.setDesignationName("");
      this.goalBean.setDesignationCode("");
      this.goalBean.setEmpTokenAdd("");
      this.goalBean.setEmpTokenNo("");
      this.goalBean.setSrNo("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }
}