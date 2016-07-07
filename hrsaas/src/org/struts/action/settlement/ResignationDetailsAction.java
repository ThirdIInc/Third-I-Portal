package org.struts.action.settlement;

import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationDetails;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.settlement.ResignationDetailsModel;
import org.struts.lib.ParaActionSupport;

public class ResignationDetailsAction extends ParaActionSupport{ 
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(ParaActionSupport.class);
  ResignationDetails resig;

  public void prepare_local()
    throws Exception
  {
    this.resig = new ResignationDetails();
    this.resig.setMenuCode(368);
  }

  public Object getModel(){
    return this.resig;
  }

  public ResignationDetails getResig() {
    return this.resig;
  }

  public void setResig(ResignationDetails resig) {
    this.resig = resig;
  }

  public String input() throws Exception{
    reset();
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    model.callResignList(this.resig, this.request, getprofileQuery(this.resig));
    model.terminate();
    getNavigationPanel(1);
    this.resig.setEnableAll("Y");
    return "input";
  }

  public String addNew() {
    try {
      getNavigationPanel(2);
      this.resig.setListFlag("false");
      return "success";
    } catch (Exception e) {
      logger.error("Exception in addNew in action:" + e);
    }return null;
  }

  public void prepare_withLoginProfileDetails() throws Exception{
    getNavigationPanel(1);
    if (this.resig.getWaveOffPeriod().equals("")) {
      this.resig.setWaveOffPeriod("0");
    }
    if (this.resig.getNoticePeriodActual().equals("")) {
        this.resig.setNoticePeriodActual("0");
    }
    if (this.resig.isGeneralFlag()){
      this.resig.setEmpCode(this.resig.getUserEmpId());

      resignationDetailRecord();
    }
  }

  public String resignationDetailRecord()
    throws Exception{
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    this.resig = model.getRecord(this.resig);
    return "success";
  }

  public String save(){
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    try {
      if (this.resig.getRegCode().equals("")) {
        String flag = model.save(this.resig);
        if (flag.equals("saved")) {
          addActionMessage(getMessage("save"));
          this.resig.setTemplateFlag(true);
          String query = "SELECT MAX(RESIGN_CODE) FROM HRMS_RESIGN";
          Object[][] regCode = model.getSqlModel().getSingleResult(
            query);
          this.resig.setRegCode(String.valueOf(regCode[0][0]));
          getNavigationPanel(3);
          this.resig.setEnableAll("N");
        }
        else if (flag.equals("notSaved")) {
          addActionMessage(getMessage("save.error"));
          reset();
          getNavigationPanel(2);
        }
        else if (flag.equals("sameResg")) {
          addActionMessage("Resignation is already done for " + 
            this.resig.getEmpName() + " on resignation date " + 
            this.resig.getResignDate());
          reset();
          getNavigationPanel(2);
        }
        else {
          addActionMessage(getMessage("save.error"));
          reset();
          getNavigationPanel(2);
        }
      }
      else{
        boolean flag = model.update(this.resig);
        if (flag) {
          addActionMessage(getMessage("update"));
          this.resig.setTemplateFlag(true);
          getNavigationPanel(3);
          this.resig.setEnableAll("N");
        }
      }
    }
    catch (Exception e) {
      logger.error("Error in save method  : " + e);
    }
    model.terminate();
    return "success";
  }

  public String delete()
    throws Exception{
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    boolean flag = model.delete(this.resig);
    if (flag)
      addActionMessage(getMessage("delete"));
    else {
      addActionMessage(getMessage("del.error"));
    }
    model.terminate();
    input();
    return "success";
  }

  public String delete1() throws Exception{
    getNavigationPanel(1);
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    String[] code = this.request.getParameterValues("hdeleteCode");
    boolean result = model.delChkdRec(this.resig, code);
    if (result) {
      addActionMessage(getMessage("delete"));
    }
    else
      addActionMessage(getMessage("multiple.del.error"));
    input();
    this.resig.setListFlag("true");
    return "success";
  }

  public String callforedit() throws Exception {
    System.out.println("hidden code=============" + this.resig.getHiddencode());
    getNavigationPanel(3);
    String resgCode = this.request.getParameter("resignCode");
    this.resig.setRegCode(resgCode);
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    model.setWithDrw(this.resig, resgCode);
    model.callResignList(this.resig, this.request, getprofileQuery(this.resig));
    this.resig.setListFlag("false");
    this.resig.setEnableAll("N");
    model.terminate();
    return "success";
  }

  public String cancel(){
    try {
      reset();
      input();
      getNavigationPanel(1);
      return "success";
    } catch (Exception e) {
      logger.error("Exception in cancel in action:" + e);
    }return null;
  }

  public String f9LetterTemplate(){
    String query = " SELECT TEMPLATE_NAME, TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_TYPE='A' ORDER BY TEMPLATE_ID";
    String[] headers = { "Template Name" };
    String[] headerWidth = { "100" };
    String[] fieldNames = { "template", "templateCode" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
    submitFlag, submitToMethod);
    return "f9page";
  }

  public String resignEmployeeList(){
    try{
      ResignationDetailsModel model = new ResignationDetailsModel();
      model.initiate(this.context, this.session);
      model.getResignEmployeeList(this.resig);
      model.terminate();
    }
    catch (Exception localException)
    {
    }
    return "resignEmpList";
  }

  public String reportForDues(){
    try{
      ResignationDetailsModel model = new ResignationDetailsModel();
      model.initiate(this.context, this.session);
      model.getreportForDues(this.response, this.resig);
      model.terminate();
    }
    catch (Exception e) {
      logger.error("Exception in reportForDues----------------" + e);
    }
    return null;
  }

  public String report(){
    try{
      String tempCode = this.request.getParameter("templateCode");
      String resigCode = this.resig.getRegCode();      
      Template template = new Template(tempCode);
      template.initiate(this.context, this.session);

      template.getTemplateQueries();
      TemplateQuery templateQuery1 = template.getTemplateQuery(1);

      TemplateQuery templateQuery2 = template.getTemplateQuery(2);
      templateQuery2.setParameter(1, resigCode);

      String comleteTemplate = template.execute(this.request, this.response, 
        "ACCEPTANCE_LETTER");
      logger.info("comleteTemplate....." + comleteTemplate);
    } catch (Exception e) {
      try {
        String type = "Txt";
        String title = "Acceptance letter";
        e.printStackTrace();
        String finaldata = "<html></html>";
        ReportGenerator rg = new ReportGenerator(
          type, title);
        byte[] buf = finaldata.getBytes();
        this.response.setContentType("application/msword");
        this.response.getOutputStream().write(buf);
        this.response.setHeader("Content-Disposition", 
          "attachment; filename=\"ACCEPTANCE_LETTER.doc\"");
        this.response.setHeader("cache-control", "no-cache");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
    return null;
  }

  public String f9appraction()
    throws Exception{
    String query = "  SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,NVL(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,'') \t,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC ";
    query = query + getprofileQuery(this.resig);
    query = query + " AND EMP_STATUS='S'";
    query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";
    String[] headers = { getMessage("employee.id"), getMessage("employee") };
    String[] headerWidth = { "20", "80" };
    String[] fieldNames = { "apprToken", "apprName", "apprCode" };
    int[] columnIndex = { 0, 1, 2 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9accByAction()
    throws Exception{
    String query = "  SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,NVL(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,'') \t,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC ";
    query = query + getprofileQuery(this.resig);
    query = query + " AND EMP_STATUS='S'";
    query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";
    String[] headers = { getMessage("employee.id"), getMessage("employee") };
    String[] headerWidth = { "20", "80" };
    String[] fieldNames = { "accByToken", "acceptedBy", "accByCode" };
    int[] columnIndex = { 0, 1, 2 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
    submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9action()
    throws Exception{
    String query = "  SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,NVL(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,'') \t, NVL(RANK_NAME,' '),NVL(CENTER_NAME,'')    ,NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),HRMS_EMP_OFFC.EMP_ID,NVL(CADRE_NAME,' ') FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   LEFT JOIN HRMS_EMP_QUA ON(HRMS_EMP_QUA.EMP_ID = HRMS_EMP_OFFC.EMP_ID)   LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)    LEFT JOIN HRMS_QUA ON(HRMS_QUA.QUA_ID=HRMS_EMP_QUA.QUA_MAST_CODE)     LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)";
    query = query + getprofileQuery(this.resig);
    query = query + " AND EMP_STATUS='S'";
    query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";
    String[] headers = { getMessage("employee.id"), getMessage("employee") };
    String[] headerWidth = { "20", "80" };
    String[] fieldNames = { "employeeToken", "empName", "designation", 
      "branch", "dateofjoin", "empCode", "cadreName" };
    int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };
    String submitFlag = "true";
    String submitToMethod = "ResignationDetails_setData.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String search()
    throws Exception{
    String query = " SELECT DISTINCT (EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), TO_CHAR(RESIGN_DATE,'DD-MM-YYYY') ,HRMS_RESIGN.RESIGN_CODE, NVL(RESIGN_NOTICE_PERIOD,'0'),RESIGN_STATUS, \tNVL(RANK_NAME,' '),NVL(CENTER_NAME,''), \tNVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),' '), HRMS_EMP_OFFC.EMP_ID,SETTL_LOCKFLAG,HRMS_EMP_OFFC.EMP_TOKEN, RESIGN_DATE  FROM HRMS_RESIGN \tLEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)\tLEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)\tLEFT JOIN HRMS_EMP_QUA ON(HRMS_EMP_QUA.EMP_ID = HRMS_RESIGN.RESIGN_EMP)\tLEFT JOIN HRMS_QUA ON(HRMS_QUA.QUA_ID=HRMS_EMP_QUA.QUA_MAST_CODE)  \tLEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE) ";
    query = query + getprofileQuery(this.resig);
    query = query + "\tORDER BY RESIGN_DATE DESC ";
    try{
      String[] headers = { getMessage("employee"), getMessage("resigndt") };
      String[] headerWidth = { "60", "40" };
      String[] fieldNames = { "empName", "resignDate", "regCode" };
      int[] columnIndex = { 0, 1, 2 };
      String submitFlag = "true";
      String submitToMethod = "ResignationDetails_view.action";
      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
        submitFlag, submitToMethod);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "f9page";
  }

  public String view(){
    getNavigationPanel(3);
    System.out.println("regcode ======" + this.resig.getRegCode());
    String resgCode = this.resig.getRegCode();
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    model.setWithDrw(this.resig, resgCode);
    model.terminate();
    this.resig.setTemplateFlag(true);
    this.resig.setEnableAll("N");
    return "success";
  }

  public String setData(){
    getNavigationPanel(2);
    ResignationDetailsModel model = new ResignationDetailsModel();
    model.initiate(this.context, this.session);
    model.setData(this.resig);
    model.terminate();
    return "success";
  }

  public String reset(){
    getNavigationPanel(2);
    this.resig.setEmpCode("");
    this.resig.setEmpName("");
    this.resig.setBranch("");
    this.resig.setDesignation("");
    this.resig.setDateofjoin("");
    this.resig.setNoticePeriod("");
    this.resig.setResignDate("");
    this.resig.setAcceptDate("");
    this.resig.setSepdate("");
    this.resig.setReason("");
    this.resig.setRemark("");
    this.resig.setRegCode("");
    this.resig.setEmployeeToken("");
    this.resig.setStatus("");
    this.resig.setWithDrawn("");
    this.resig.setNoticeperiodselect("");
    this.resig.setNoticePeriodActual("");
    this.resig.setApprCode("");
    this.resig.setApprName("");
    this.resig.setApprToken("");
    this.resig.setWaveOffPeriod("");
    this.resig.setAccByCode("");
    this.resig.setAcceptedBy("");
    this.resig.setAccByToken("");
    this.resig.setHrComments("");
    this.resig.setCadreName("");
    return "success";
  }
}