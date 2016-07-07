package org.struts.action.voucher;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.paradyne.bean.voucher.VoucherApplication;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.model.voucher.VoucherApplicationModel;
import org.paradyne.model.voucher.VoucherApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 *  @modified By AA1711
 */
public class VoucherApplicationAction extends ParaActionSupport{
  private static final long serialVersionUID = 1L;
  static Logger logger = Logger.getLogger(VoucherApplicationAction.class);
  VoucherApplication bean = null;

  /** (non-Javadoc)
 * @see org.struts.lib.ParaActionSupport#prepare_local()
 */
 public void prepare_local() throws Exception{
    this.bean = new VoucherApplication();
    this.bean.setMenuCode(372);
  }

  /** (non-Javadoc)
 * @see com.opensymphony.xwork2.ModelDriven#getModel()
 */
 public Object getModel() {
    return this.bean;
  }

  /** (non-Javadoc)
 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
 */
 public void prepare_withLoginProfileDetails() throws Exception {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    //this.bean.setEmpId(this.bean.getUserEmpId());
    model.checkFlag(this.bean);
    model.terminate();
  }

  /** (non-Javadoc)
 * @see com.opensymphony.xwork2.ActionSupport#input()
 */
 public String input() throws Exception {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    this.bean.setSListType("pending");
    model.getAllPendingApplications(this.bean, this.request);
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  /**Method name: getApprovedList()
 * Used to display Approved List 
 * @return String
 * @throws Exception
 */
 public String getApprovedList() throws Exception {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    this.bean.setSListType("approved");
    model.getApprovedApplications(this.bean, this.request);
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  /** Method name:  getRejectedList() 
 * Used to display Rejected List 
 * @return String
 * @throws Exception
 */
 public String getRejectedList() throws Exception {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    this.bean.setSListType("rejected");
    model.getRejectedApplications(this.bean, this.request);
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  /**Method name: addNew()
   * Used to add New record
 * @return String
 * @throws Exception
 */
 public String addNew() throws Exception {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    this.bean.setEmpId(this.bean.getUserEmpId());
    this.bean.setShowFlag("true");
    this.bean.setVoucherDetailsFlag("true");
    if (this.bean.isGeneralFlag()) {
      model.getLoginEmployeeDetails(this.bean, this.request);
    }
    else {
      model.getLoginEmployeeDetails(this.bean, this.request);
    }
    loadMainApproverList(this.bean.getUserEmpId());

    model.terminate();
    this.bean.setHiddenStatus("D");
    this.bean.setStatus("D");
    getNavigationPanel(2);
    return "showData";
  }

  /**Method name: f9Action() 
 * @return String 
 * @throws Exception
 */
 public String f9action() throws Exception {
    String query = " SELECT VOUCHER_TYPE, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,  TO_CHAR(APP_DATE,'DD-MM-YYYY'),  VCH_TOTALAMT, CASE WHEN STATUS = 'P' AND APPL_LEVEL != 1 THEN 'Forwarded' WHEN STATUS = 'P' THEN 'Pending'  WHEN STATUS = 'A' THEN 'Approved' WHEN STATUS = 'R' THEN 'Rejected' WHEN STATUS = 'C' THEN 'Canceled' ELSE '' END, EMP_CODE, VOUCHER_APPL_CODE, APPROVED_BY  FROM HRMS_VOUCHER_APPL  INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID)  LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)";

    if (this.bean.isGeneralFlag()) {
      query = query + " WHERE EMP_CODE = " + this.bean.getUserEmpId();
    }
    query = query + " ORDER BY APP_DATE DESC ";
    String[] headers = { getMessage("vouchertype"), getMessage("employee"), getMessage("date"), getMessage("Total.Amount"), getMessage("status") };
    String[] headerWidth = { "20", "30", "15", "15", "20" };
    String[] fieldNames = { "type", "ename", "vchDate", "totalamount", "hiddenStatus", "empId", "vCode", "approverCode" };
    int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };
    String submitFlag = "true";
    String submitToMethod = "VoucherApplication_callforedit.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  /**Method name: f9ActionEname()
   * Used to display Employee List on Search click 
 * @return String
 * @throws Exception
 */
  public String f9actionEname()throws Exception{
    String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,  "
    				+ " NVL(RANK_NAME, ' '), NVL(DEPT_NAME, ' '), NVL(CADRE_NAME, ' '), "
    				+ " EMP_ID FROM HRMS_EMP_OFFC  "
    				+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID)  "
    				+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
    				+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)"
    				+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) "
    				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) ";
    query = query + getprofileQuery(this.bean);

    query = query + " AND EMP_CENTER = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = " + this.bean.getUserEmpId() + " ) "  
      				+ " AND EMP_STATUS = 'S' ORDER BY EMP_ID ";

    String[] headers = { getMessage("employee.id"), getMessage("employee"), getMessage("designation"), getMessage("department"), getMessage("grade") };
    String[] headerWidth = { "10", "30", "20", "20", "20" };
    String[] fieldNames = { "empToken", "ename", "designation", "department", "grade", "empId" };
    int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
    String submitFlag = "true";
    String submitToMethod = "VoucherApplication_setApproverDetail.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  /**Method name: f9actionhd()
   * Used to display Voucher Head List 
 * @return String
 * @throws Exception
 */
 public String f9actionhd()throws Exception{
    String query = " SELECT VCH_CODE, VCH_NAME FROM HRMS_VCH_HD ORDER BY VCH_CODE ";
    String[] headers = { "Voucher Code", "Voucher Name" };
    String[] headerWidth = { "20", "80" };
    String[] fieldNames = { "vchHeadCode", "VoucherHeadName" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  /**Method name: f9KeepInformedEmployee()
   * Used to display Employee List on search 
 * @return String
 */
 public String f9KeepInformedEmployee() {
    String[] eId = this.request.getParameterValues("keepInformedEmpCode");
    String str = this.bean.getEmpId();
    if (eId != null) {
      for (int i = 0; i < eId.length; i++)
        str = str + "," + eId[i];
    }
    try{
      String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN," 
      				 + " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||"
      				 + " HRMS_EMP_OFFC.EMP_LNAME,EMP_ID \tFROM HRMS_EMP_OFFC ";

      query = query + " WHERE EMP_STATUS='S' ";
      if ((this.bean.getUserProfileDivision() != null) && (this.bean.getUserProfileDivision().length() > 0))
        query = query + " AND EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
      	query = query + " AND EMP_ID NOT IN(" + str + ") ";
      	query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";

      String[] headers = { getMessage("kiEmpToken"), getMessage("kiEmpName") };
      String[] headerWidth = { "20", "80" };
      String[] fieldNames = { "kiEmpToken", "kiEmpName", "kiEmpCode" };
      int[] columnIndex = { 0, 1, 2 };
      String submitFlag = "false";
      String submitToMethod = "";
      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
      return "f9page";
    } catch (Exception e) {
      logger.error(e.getMessage());
    }return "";
  }

  /**Method name:  addItem()
   * Used to add Voucher Details in Iterator 
 * @return String
 * @throws Exception
 */
public String addItem() throws Exception{
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);

    if (this.bean.getCheckEdit().equals(""))
      model.addVoucher(this.bean, this.request, 1);
    else {
      model.editVoucher(this.bean, this.request);
    }
    clear();
    model.terminate();
    if ((this.bean.getHiddenStatus().equalsIgnoreCase("D")) || (this.bean.getHiddenStatus().equalsIgnoreCase("W"))) {
      if ((this.bean.getVCode() != null) && (this.bean.getVCode().equals("")))
        getNavigationPanel(2);
      else {
        getNavigationPanel(3);
      }
    }
    else if (this.bean.getHiddenStatus().equalsIgnoreCase("P")) {
      getNavigationPanel(4);
    } else if (this.bean.getHiddenStatus().equalsIgnoreCase("B")) {
      this.bean.setStatus(this.bean.getHiddenStatus());
      this.bean.setPrevAppCommentListFlag("true");
      this.bean.setShowFlag("true");
      model.setApprover(this.bean);

      getNavigationPanel(5);
    } else {
      getNavigationPanel(2);
    }
    this.bean.setStatus(this.bean.getHiddenStatus());
    this.bean.setKiEmpName("");
    this.bean.setKiEmpCode("");
    this.bean.setKiEmpToken("");
    String[] serialNo = this.request.getParameterValues("serialNo");
    String[] empToken = this.request.getParameterValues("keepInformedEmpId");
    String[] empName = this.request.getParameterValues("keepInformedEmpName");
    String[] empCode = this.request.getParameterValues("keepInformedEmpCode");
    model.displayIteratorOfKeep(this.bean, serialNo, empCode, empToken, empName);
    loadMainApproverList(this.bean.getEmpId());
    this.bean.setVoucherDetailsFlag("true");
    return "showData";
  }

  /**Method name: clear
 * Used to Clear Voucher Details
 */
public void clear()
  {
    this.bean.setVchHeadCode("");
    this.bean.setVoucherHeadName("");
    this.bean.setVamount("");
    this.bean.setVremark("");
    this.bean.setCheckEdit("");
    this.bean.setHproof("");
    this.bean.setUploadFileName("");
  }

  /**Method name: remove()
   * Used to remove Voucher detail
 * @return String
 * @throws Exception
 */
public String remove()throws Exception{
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    model.addVoucher(this.bean, this.request, 0);
    model.terminate();
    if ((this.bean.getHiddenStatus().equalsIgnoreCase("D")) || (this.bean.getHiddenStatus().equalsIgnoreCase("W"))) {
      if ((this.bean.getVCode() != null) && (this.bean.getVCode().equals("")))
        getNavigationPanel(2);
      else
        getNavigationPanel(3);
    }
    else if (this.bean.getHiddenStatus().equalsIgnoreCase("P")) {
      getNavigationPanel(4);
    } else if (this.bean.getHiddenStatus().equalsIgnoreCase("B")) {
      getNavigationPanel(5);
      model.setApprover(this.bean);
    } else {
      getNavigationPanel(2);
    }
    this.bean.setVoucherDetailsFlag("true");
    loadMainApproverList(this.bean.getEmpId());
    this.bean.setKiEmpName("");
    this.bean.setKiEmpCode("");
    this.bean.setKiEmpToken("");
    String[] serialNo = this.request.getParameterValues("serialNo");
    String[] empToken = this.request.getParameterValues("keepInformedEmpId");
    String[] empName = this.request.getParameterValues("keepInformedEmpName");
    String[] empCode = this.request.getParameterValues("keepInformedEmpCode");
    model.displayIteratorOfKeep(this.bean, serialNo, empCode, empToken, empName);
    return "showData";
  }

  /**Method name: save()
   * Used to save all the detail in Database 
 * @return String
 * @throws Exception
 */
public String save()throws Exception{
    Object[][] empFlow = (Object[][])null;
    String status = this.request.getParameter("hiddenStatus");
    Object[] srNo = this.request.getParameterValues("srNo");
    Object[] vHeadCode = this.request.getParameterValues("vchCode");
    Object[] vHeadNm = this.request.getParameterValues("VoucherHead");
    Object[] amount = this.request.getParameterValues("vamt");
    Object[] remark = this.request.getParameterValues("vrem");
    Object[] proof = this.request.getParameterValues("vproof");
    Object[] upload = this.request.getParameterValues("uploadFile");
    String [] keepInformList = request.getParameterValues("keepInformedEmpCode");
    if ((status != null) && (!status.equals(""))) {
      this.bean.setHiddenStatus(status);
      this.bean.setStatus(status);
    }    
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);

    if (this.bean.getVoucherMgrFlg().equals("Y")) {
      empFlow = generateEmpFlow(this.bean.getEmpId(), "Cash", 1);
    } /*else if (this.bean.getVoucherAdminFlg().equals("Y")) {
      String ownerQuery = " SELECT CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT  EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID= " + 
        this.bean.getEmpId() + ")" + 
        " AND CONF_TYPE='CashAdmin'";
      empFlow = model.getSqlModel().getSingleResult(ownerQuery);
    } else if (this.bean.getVoucherAccFlg().equals("Y")) {
      String ownerQuery = " SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT  EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID= " + 
        this.bean.getEmpId() + ")" + 
        " AND CONF_TYPE='CashAccount'";
      empFlow = model.getSqlModel().getSingleResult(ownerQuery);
    }*/

    String flag = "";
    if ((empFlow != null) && (empFlow.length > 0)) {
      if (this.bean.getVCode().equals("")) {
        flag = model.save(this.request, this.bean, empFlow, srNo, vHeadCode, amount, remark, proof, upload, keepInformList);
        if (flag.equals("saved")) {
          if (status.equals("D")) {
            sendProcessManagerAlertDraft();
            addActionMessage("Your application has been Draft successfully.");
          }
          else if (status.equals("P")) {
            String str = (empFlow != null) && (empFlow.length > 0) ? 
              String.valueOf(empFlow[0][0]) : 
              "0";
            try {
              MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
              processAlerts.initiate(this.context, this.session);
              processAlerts.removeProcessAlert(this.bean.getVCode(), "Voucher");
              processAlerts.terminate();
            } catch (Exception e) {
              e.printStackTrace();
            }
            try {
              model.sendProcessManagerAlert(this.request, this.bean.getVCode(), 
                this.bean.getEmpId(), str, empFlow, keepInformList, 
                this.bean.getVoucherMgrFlg(), 
                this.bean.getVoucherAdminFlg(), 
                this.bean.getVoucherAccFlg(), "Voucher");
            } catch (Exception e) {
              e.printStackTrace();
            }
            addActionMessage("Your application has been sent for approval successfully.");
          }
          else {
            addActionMessage(getMessage("save"));
          }
          reset();
        } else if (flag.equals("notsaved")) {
          addActionMessage("Record can't be saved.");
          model.addVoucher(this.bean, this.request, 2);
        }
      } else {
        flag = model.modify(this.request, this.bean, empFlow, srNo, vHeadCode, amount, remark, proof, upload,keepInformList);
        if (flag.equals("modified")){
          if (status.equals("D")) {
            sendProcessManagerAlertDraft();
            addActionMessage("Your application has been Draft successfully.");
          }
          else if (status.equals("P")) {
            String str = (empFlow != null) && (empFlow.length > 0) ? 
              String.valueOf(empFlow[0][0]) : 
              "0";
            try{
              MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();

              processAlerts.initiate(this.context, this.session);
              processAlerts.removeProcessAlert(this.bean.getVCode(), "Voucher");
              processAlerts.terminate();
            } catch (Exception e) {
              e.printStackTrace();
            }
            try {
              model.sendProcessManagerAlert(this.request, this.bean.getVCode(), 
                this.bean.getEmpId(), str, empFlow, keepInformList, 
                this.bean.getVoucherMgrFlg(), 
                this.bean.getVoucherAdminFlg(), 
                this.bean.getVoucherAccFlg(), "Voucher");
            } catch (Exception e) {
              e.printStackTrace();
            }
            addActionMessage("Your application has been sent for approval successfully.");
          }
          else {
            addActionMessage(getMessage("save"));
          }reset();
        } else if (flag.equals("notmodified")) {
          addActionMessage("Record can't be updated.");
          model.addVoucher(this.bean, this.request, 2);
        }
      }
    }
    else {
      addActionMessage("Reporting structure not defined for the employee\n" + this.bean.getEname());
      addActionMessage("Please contact HR manager !");
      model.addVoucher(this.bean, this.request, 2);
    }
    this.bean.setSListType("pending");
    model.getAllPendingApplications(this.bean, this.request);
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  /**Method name: reset()
   * Used to reset all the fields
 * @return String
 */
public String reset() {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    this.bean.setVCode("");
    this.bean.setVoucherType("");
    if (!this.bean.isGeneralFlag()) {
      this.bean.setEname("");
      this.bean.setDepartment("");
      this.bean.setDesignation("");
      this.bean.setGrade("");
      this.bean.setEmpToken("");
    }
    this.bean.setVrem("");
    this.bean.setVoucherHead("");
    this.bean.setVamt("");
    this.bean.setStatus("");
    this.bean.setTotalamount("");
    this.bean.setDetails("");
    this.bean.setVchCode("");
    this.bean.setChkfield("");
    this.bean.setVamount("");
    this.bean.setVchHeadCode("");
    this.bean.setVoucherHeadName("");
    this.bean.setVremark("");
    this.bean.setHproof("");
    this.bean.setCheckEdit("");
    this.bean.setTableLength("");
    this.bean.setUploadFileName("");
    this.bean.setList(null);
    String query = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY')FROM DUAL";
    Object[][] objDate = model.getSqlModel().getSingleResult(query);
    this.bean.setVchDate(String.valueOf(objDate[0][0]));
    model.terminate();
    getNavigationPanel(2);
    return "showData";
  }

  /**Method name: callforedit()
   * Used to display Saved record
 * @return String
 * @throws Exception
 */
public String callforedit() throws Exception {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    try {
      String voucherID = this.request.getParameter("voucherCode");
      String voucherStatus = this.request.getParameter("voucherStatus");
      this.bean.setHiddenStatus(voucherStatus);
      this.bean.setStatus(voucherStatus);
      this.bean.setSelectedVCode(voucherID);
      this.bean.setVCode(voucherID);
      String source = request.getParameter("src");    
      bean.setSource(source);
      boolean isKeepInformTo = false;
      String empQuery="SELECT EMP_CODE FROM HRMS_VOUCHER_APPL "
				+ " WHERE VOUCHER_APPL_CODE=" +bean.getVCode();
      Object[][] empCodeObj= model.getSqlModel().getSingleResult(empQuery);
      if (empCodeObj != null && empCodeObj.length > 0) {
			bean.setEmpId(String.valueOf(empCodeObj[0][0]));
		}
      Object[][] empFlow = generateEmpFlow(this.bean.getEmpId(), "Cash", 1);      
      model.setApprover(this.bean);
      loadMainApproverList(this.bean.getEmpId());
      model.showEmp(this.bean);
      if (this.bean.getHiddenStatus().equalsIgnoreCase("D")) {
        getNavigationPanel(3);
        this.bean.setShowFlag("true");
        this.bean.setVoucherDetailsFlag("true");
        this.bean.setEditFlag1("false");
        isKeepInformTo = model.getKeepInformedRecord(this.bean);
        model.getRecord(this.bean);
      } else if (this.bean.getHiddenStatus().equalsIgnoreCase("P")) {
        this.bean.setSForward(model.checkWithdrawIsPossible(this.bean));
        this.bean.setVoucherDetailsFlag("false");
        isKeepInformTo = model.getKeepInformedRecord(this.bean);
        if (this.bean.getSForward().equalsIgnoreCase("Y")) {
          this.bean.setPrevAppCommentListFlag("true");
          this.bean.setStatus("F");
          getNavigationPanel(6);          
        } else if (isKeepInformTo) {
          getNavigationPanel(6);
        } else {
          getNavigationPanel(4);
        }
        this.bean.setEditFlag1("true");
        this.bean.setShowFlag("false");      
        model.getRecord(this.bean);
      }
      else if (this.bean.getHiddenStatus().equalsIgnoreCase("B")) {
        model.setApprLevelOnceAgain(this.bean, empFlow);
        this.bean.setPrevAppCommentListFlag("true");
        this.bean.setVoucherDetailsFlag("true");
        isKeepInformTo = model.getKeepInformedRecord(this.bean);
        model.setApprover(this.bean);       
        this.bean.setShowFlag("true");
        if (isKeepInformTo)
          getNavigationPanel(6);
        else {
          getNavigationPanel(5);
        }        
        this.bean.setEditFlag1("false");
        model.getRecord(this.bean);
      } else if ((this.bean.getHiddenStatus().equalsIgnoreCase("A")) || (this.bean.getHiddenStatus().equalsIgnoreCase("R"))) {
        this.bean.setApprComments("false");
        this.bean.setPrevAppCommentListFlag("true");
        getNavigationPanel(6);

        this.bean.setShowFlag("false");
        this.bean.setEditFlag1("true");
        this.bean.setVoucherDetailsFlag("false");
        isKeepInformTo = model.getKeepInformedRecord(this.bean);
        model.getRecord(this.bean);
      }
      else if (this.bean.getHiddenStatus().equalsIgnoreCase("W")) {
        getNavigationPanel(6);
        this.bean.setShowFlag("false");
        this.bean.setVoucherDetailsFlag("false");
        isKeepInformTo = model.getKeepInformedRecord(this.bean);
        model.getRecord(this.bean);
      }     
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    model.terminate();
    return "showData";
  }

  /**Method name: report()
   * Used to display Report of Particular Record 
 * @return String
 * @throws Exception
 */
public String report() throws Exception {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    String voucherID = this.request.getParameter("voucherCode");
    this.bean.setVCode(voucherID);
    model.getReport(this.request, this.response, this.bean);
    model.terminate();
    return null;
  }

  /**Method name: retriveForApproval()
   * Used to display Saved record
 * @return String
 */
public String retriveForApproval() {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    boolean isAppComment = false;
    String source = request.getParameter("src");
    String vCode = this.request.getParameter("voucherCode");
    String vStatus = this.request.getParameter("status");
    bean.setSource(source);
    String queryStatus = "SELECT VOUCHER_STATUS FROM HRMS_VOUCHER_PATH WHERE VOUCHER_CODE =" + vCode;
    Object[][] objStatus = model.getSqlModel().getSingleResult(queryStatus);
    this.bean.setStatus(vStatus);
    this.bean.setVCode(vCode);   
    model.setApplication(this.bean);
    model.showEmp(this.bean);
    loadMainApproverList(this.bean.getEmpId());
    model.getKeepInformedRecord(this.bean);
    isAppComment = model.setApprover(this.bean);
    this.bean.setIsApprove("true");
    clear();
    model.loadVchReamrk(this.bean);  
    String status = this.bean.getStatus();
    if (status.equals("B")) {
      this.bean.setPrevAppCommentListFlag("true");
      this.bean.setPrevAppCommentFlag("true");
      this.bean.setShowFlag("true");
      this.bean.setVoucherDetailsFlag("true");
      this.bean.setEditFlag1("false");
      model.getRecord(this.bean);
      getNavigationPanel(10);
    }
    if (status.equals("P")) {
      if (isAppComment) {
        this.bean.setPrevAppCommentListFlag("true");
      }
      else {
        this.bean.setPrevAppCommentListFlag("false");
      }
      this.bean.setApprComments("true");
      this.bean.setPrevAppCommentFlag("true"); 
      this.bean.setVoucherDetailsFlag("false");
      this.bean.setEditFlag1("true");
      model.getRecord(this.bean);
      getNavigationPanel(9);
    }
    if (status.equals("F")) {
      this.bean.setPrevAppCommentListFlag("true");
      this.bean.setApprComments("true");
      this.bean.setPrevAppCommentFlag("true");
      this.bean.setVoucherDetailsFlag("false");
      this.bean.setEditFlag1("true");
      model.getRecord(this.bean);
      getNavigationPanel(9);
    }
    if (status.equals("R") || status.equals("A") ) {
      getNavigationPanel(10);
      this.bean.setPrevAppCommentListFlag("true");
      this.bean.setVoucherDetailsFlag("false");
      this.bean.setEditFlag1("true");
      model.getRecord(this.bean);      
    }   
    model.terminate();
    return "showData";
  }

  /**Method name: checkNull()
 * @param result
 * @return
 */
public static String checkNull(String result) {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }
    return result;
  }

  /**Method name: loadMainApproverList()
   * Used to display Approver Name
 * @param empCode
 */
public void loadMainApproverList(String empCode){
    try {
      VoucherApplicationModel model = new VoucherApplicationModel();
      model.initiate(this.context, this.session);
      ReportingModel model1 = new ReportingModel();
      model1.initiate(this.context, this.session);
      Object[][] empFlow = model1.generateEmpFlow(empCode, "Cash");
      model.setMainApproverData(this.bean, empFlow);
      model1.terminate();
      model.terminate();
    }
    catch (Exception e) {
      logger.error("Exception in setApproverList------" + e);
      e.printStackTrace();
    }
  }

  /**Method name: back()
   * Used to display List
 * @return String
 */
public String back() {
    try {
      VoucherApplicationModel model = new VoucherApplicationModel();
      model.initiate(this.context, this.session);
      this.bean.setSListType("pending");
      model.getAllPendingApplications(this.bean, this.request);
      model.terminate();
      getNavigationPanel(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  /**Method name: sendProcessManagerAlertDraft()
 * Used to display Draft alert 
 */
public void sendProcessManagerAlertDraft(){
    try{
      FileInputStream alertFin = new FileInputStream(getText("data_path") + 
        "\\Alerts\\alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String applicantID = this.bean.getEmpId();
      String module = "Voucher";
      String applnID = this.bean.getVCode();
      String level = "1";
      String link = "/voucher/VoucherApplication_callforedit.action";
      String linkParam = "voucherCode=" + applnID + "&voucherStatus=D";
      String message = alertProp.getProperty("draftAlertMessage");
      message = message.replace("<#EMP_NAME#>", 
        this.bean.getEname().trim());
      message = message.replace("<#APPL_TYPE#>", "Voucher");
      template.sendProcessManagerAlertDraft(applicantID, module, "A", 
        applnID, level, linkParam, link, message, "Draft", 
        applicantID, applicantID);
      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**Method name: withdrawn()
   * Used to Withdrawn record 
 * @return String
 */
public String withdrawn() {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    boolean result = false;
    try {
      result = model.withdrawApplication(this.bean);
      if (this.bean.getHiddenStatus().equals("P"))
        if (result) {
          sendProcessManagerAlertForWithdraw();
          addActionMessage("Application has been withdrawn successfully");
        }
        else {
          addActionMessage("Application could not be withdrawn ");
        }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.bean.setSListType("pending");
    model.getAllPendingApplications(this.bean, this.request);
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  /**Method name: sendProcessManagerAlertForWithdraw()
 * Used to display Withdrawn Alert 
 */
public void sendProcessManagerAlertForWithdraw() {
    try {
      ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
      processAlerts.initiate(this.context, this.session);
      processAlerts.removeProcessAlert(String.valueOf(
        this.bean.getVCode()), "Voucher");
      processAlerts.terminate();
      VoucherApprovalModel model = new VoucherApprovalModel();
      model.initiate(this.context, this.session);
      String query = "SELECT APPROVED_BY, VOUCHER_KEEP_INFORM FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE =" + 
        this.bean.getVCode();
      Object[][] selectObj = model.getSqlModel().getSingleResult(query);
      String managerId = "";
      String keepInformedId = "";
      if ((selectObj != null) && (selectObj.length > 0)) {
        managerId = String.valueOf(selectObj[0][0]);
        keepInformedId = String.valueOf(selectObj[0][1]);
      }

      FileInputStream alertFin = new FileInputStream(getText("data_path") + 
        "/Alerts/alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String applicantID = this.bean.getEmpId();
      String module = "Voucher";
      String applnID = this.bean.getVCode();
      String level = "1";
      String link = "/voucher/VoucherApplication_callforedit.action";
      String linkParam = "voucherCode=" + applnID + "&voucherStatus=W";
      String message = alertProp.getProperty("withdrawAlertMessage");
      message = message.replace("<#EMP_NAME#>", 
        this.bean.getEname().trim());
      message = message.replace("<#APPL_TYPE#>", "Voucher");
      template.sendProcessManagerAlertWithdraw(applicantID, module, "I", 
        applnID, level, linkParam, link, message, "Withdraw", 
        applicantID, applicantID, keepInformedId, managerId);
      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**Method name: approveRejSendBackApp()
   * Used to Approved, Reject and Send Back record 
 * @return String
 */
public String approveRejSendBackApp() {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);    
    try {
      boolean isAppComment = false;
      model.setApplication(this.bean);
      model.getRecord(this.bean);
      model.showEmp(this.bean);
      loadMainApproverList(this.bean.getEmpId());
      isAppComment = model.setApprover(this.bean);
      this.bean.setIsApprove("true");
      model.getKeepInformedRecord(this.bean);
      VoucherApprovalModel approvalmodel = new VoucherApprovalModel();
      approvalmodel.initiate(this.context, this.session);

      String appStatus = approvalmodel.approveRejectSendBackApp(this.request, 
        this.bean.getEmpId(), this.bean.getVCode(), this.bean.getHiddenStatus(), 
        this.bean.getApproverComments(), this.bean.getUserEmpId(), 
        this.bean.getLevel(), this.bean.getVchDate());
      if (appStatus.equals("rejected"))
        addActionMessage("Application rejected successfully");
      else if (appStatus.equals("sendback"))
        addActionMessage("Application sent back successfully");
      else {
        addActionMessage("Application approved successfully");
      }
      approvalmodel.terminate();
      this.bean.setSListType("pending");
      model.getAllPendingApplications(this.bean, this.request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.terminate();
    if (bean.getSource().equals("mymessages")) {
		return "mymessages";
	} else {
		  return "voucherApproval";
	}
  
  }
  
  /** Method name: addKeepInformToList()
   * Used to display KeepInformed ti into Iterator 
 * @return String
 */
public String addKeepInformToList() {
    VoucherApplicationModel model = new VoucherApplicationModel();
    model.initiate(this.context, this.session);
    String[] serialNo = this.request.getParameterValues("serialNo");
    String[] empID = this.request.getParameterValues("keepInformedEmpCode");
    String[] empToken = this.request.getParameterValues("keepInformedEmpId");
    String[] empName = this.request.getParameterValues("keepInformedEmpName");    
    //model.displayIteratorOfKeep(this.bean, serialNo, empID, empToken, empName);
    this.bean.setVoucherDetailsFlag("true");
    model.setKeepInformTo(this.bean, serialNo, empID, empToken, empName);  
    this.bean.setKiEmpCode("");
    this.bean.setKiEmpName("");
    this.bean.setKiEmpToken("");
    loadMainApproverList(this.bean.getEmpId());
    
    this.bean.setShowFlag("true");
    if (this.bean.getHiddenStatus().equals("B")) {
      this.bean.setPrevAppCommentListFlag("true");
      model.setApprover(this.bean);
      getNavigationPanel(5);
    }
    else {
      getNavigationPanel(2);
    }
    model.checked(request, bean);
    model.terminate();
    return "showData";
  }

  /**Method name: deleteVoucher()
   * Used to delete Voucher Record Saved in Detail and header table 
 * @return String
 */
public String deleteVoucher(){
    try{
      getNavigationPanel(1);
      VoucherApplicationModel model = new VoucherApplicationModel();
      model.initiate(this.context, this.session);
      boolean result = model.deleteVoucherDetails(this.bean, this.request);
      model.getAllPendingApplications(this.bean, this.request);
      if (result) {
        deleteProcessManagerAlert("Voucher", this.bean.getVCode());
        addActionMessage("Record deleted successfully.");
      }
      this.bean.setSListType("pending");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.bean.getSource().equals("mymessages")) {
      return "mymessages";
    }
    if (this.bean.getSource().equals("myservices"))
    {
      return "serviceJsp";
    }
    return "success";
  }

  /**Method name: deleteProcessManagerAlert()
   * Used to delete alert of deleted record  
 * @param moduleName
 * @param applicationCode
 */
public void deleteProcessManagerAlert(String moduleName, String applicationCode){
    try{
      VoucherApplicationModel model = new VoucherApplicationModel();
      model.initiate(this.context, this.session);
      String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID=" + 
        applicationCode + 
        " AND UPPER(ALERT_MODULE) LIKE '" + 
        moduleName.trim().toUpperCase() + "'";
      model.getSqlModel().singleExecute(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**Method name: removeKeepInformed()
   * Used to delete Keep Informed to Record 
 * @return  String
 */
public String removeKeepInformed() {
		try {
			if (bean.getHiddenStatus().equals("B")) {
				getNavigationPanel(5);
			} else {
				getNavigationPanel(2);
			}			
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpCode");
			String[] empToken = request.getParameterValues("keepInformedEmpId");
			String[] empName = request.getParameterValues("keepInformedEmpName");
			String vchCode = request.getParameter("vCode");
			VoucherApplicationModel model = new VoucherApplicationModel();
			model.initiate(context, session);
			String removeEmpId=request.getParameter("kiInfrEmployeeCode");
			bean.setVoucherDetailsFlag("true");			
			loadMainApproverList(this.bean.getEmpId());
			model.removeKeepInformedData(serialNo, empCode,empToken, empName,bean,removeEmpId,vchCode);
			bean.setCheckRemove("");
			bean.setStatus(bean.getHiddenStatus());
			loadMainApproverList(this.bean.getEmpId());			
			if(this.bean.getHiddenStatus().equals("B")){
				model.getRecord(this.bean);
				this.bean.setPrevAppCommentListFlag("true");
				this.bean.setPrevAppCommentFlag("true");
				model.setApprover(bean);
			}
			else{
				model.checked(request, bean);
			}
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformed--------" + e);
		}
		return "showData";

	}

 public String backToApprovalList(){
	 return "voucherApproval";
 }
 
 public String setApproverDetail(){
	 VoucherApplicationModel model= new VoucherApplicationModel();
	 model.initiate(context, session);
	 try {
		String empCode = bean.getEmpId();
		ReportingModel model1 = new ReportingModel();
		model1.initiate(this.context, this.session);
		Object[][] empFlow = model1.generateEmpFlow(empCode, "Cash");
		model.setMainApproverData(this.bean, empFlow);
		model1.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	getNavigationPanel(2);
	model.terminate();
	return "showData";
 }
}