package org.struts.action.CR;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.paradyne.bean.CR.ManagerComponents;
import org.paradyne.model.CR.AccountMasterModel;
import org.paradyne.model.CR.ManageComponentsModel;
import org.struts.lib.ParaActionSupport;

public class ManageComponentsAction extends ParaActionSupport{

	ManagerComponents bean;

	  static Logger logger = Logger.getLogger(ManageComponentsAction.class);

	  public ManagerComponents getBean()
	  {
	    return this.bean;
	  }

	  public void setTm(ManagerComponents bean) {
	    this.bean = bean;
	  }

	  public Object getModel()
	  {
	    return this.bean;
	  }

	  public String input()
	    throws Exception
	  {
		  getNavigationPanel(1);
//	    AccountMasterModel model = new AccountMasterModel();
//	    model.initiate(this.context, this.session);
//	    String searchMessage = this.request.getParameter("searchMessage");
//	    this.bean.getHiddenSearchMessage();
//	    
//	    if(!(bean.getCrmCode().equals(""))){
//			String searchCRMMessage = bean.getCrmCode();
//			model.accountDetails(this.bean, this.request, this.bean.getHiddenSearchMessage(),searchCRMMessage);
//		
//		}else{
//			model.accountDetails(this.bean, this.request, this.bean.getHiddenSearchMessage(),"");
//		}
//	    
//	   //// model.accountDetails(this.bean, this.request, this.bean.getHiddenSearchMessage());
//	    this.bean.setHiddenSearchMessage(this.bean.getHiddenSearchMessage());
   
//	    model.terminate();

	    return "input";
	  }

	  public String addNew() {
	    try {
	      String poolName = String.valueOf(this.session
	        .getAttribute("session_pool"));
	      if ((!poolName.equals("")) && (poolName != null)) {
	        poolName = "/" + poolName;
	      }

	      String dataPath = getText("data_path") + "/upload" + poolName + 
	        "/CR/";

	      this.bean.setDataPath(dataPath);

	      getNavigationPanel(2);
	      return "success";
	    } catch (Exception e) {
	      logger.error("Exception in addNew in action:" + e);
	    }return null;
	  }

	  public String customerHistoryReport()
	  {
	    return "customerHistoryReport";
	  }

	  public String generateCustomerHistoryReport()
	  {
//	    CustomerHistoryReportModel model = new CustomerHistoryReportModel();
//	    model.initiate(this.context, this.session);
//	    String reportPath = "";
//	    model.getReport(this.bean, this.response, this.request, reportPath);
//	    model.terminate();
	    return null;
	  }

	  public String cancel()
	  {
	    try {
//	      AccountMasterModel model = new AccountMasterModel();
//	      model.initiate(this.context, this.session);
//	      String searchMessage = this.request.getParameter("searchMessage");
//	      this.bean.setHiddenSearchMessage(searchMessage);
//
//	    ///  model.accountDetails(this.bean, this.request, searchMessage);
//	      if(!(bean.getCrmCode().equals(""))){
//	  		String searchCRMMessage = bean.getCrmCode();
//	  		model.accountDetails(this.bean, this.request, searchMessage,searchCRMMessage);
//	  	
//	  	}else{
//	  		model.accountDetails(this.bean, this.request, searchMessage,"");
//	  	}
//	      
//	      
//	      reset();
//	      getNavigationPanel(1);
//	      model.terminate();
//	      return input();
	    } catch (Exception e) {
	      logger.error("Exception in addNew in action:" + e);
	    }return null;
	  }

	  public void prepare_local() throws Exception {
	    this.bean = new ManagerComponents();
	    this.bean.setMenuCode(5048);
	  }

	  public void prepare_withLoginProfileDetails()
	    throws Exception
	  {
	  }

	  public String callPage()
	    throws Exception
	  {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);

	    getNavigationPanel(1);
	    model.terminate();
	    return "success";
	  }

	  public String calforedit()
	    throws Exception
	  {
	    String poolName = String.valueOf(this.session
	      .getAttribute("session_pool"));
	    if ((!poolName.equals("")) && (poolName != null)) {
	      poolName = "/" + poolName;
	    }

	    String dataPath = getText("data_path") + "/upload" + poolName + 
	      "/CR/";

	    this.bean.setDataPath(dataPath);

	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);
	    String requestID = this.request.getParameter("accountId");
//	    model.calForEdit(this.bean, requestID);
	    getNavigationPanel(2);
	    this.bean.setEnableAll("Y");
	    model.terminate();
	    return "success";
	  }

	  public String reset()
	    throws Exception
	  {
	    try
	    {
	      String poolName = String.valueOf(this.session
	        .getAttribute("session_pool"));
	      if ((!poolName.equals("")) && (poolName != null)) {
	        poolName = "/" + poolName;
	      }

	      String dataPath = getText("data_path") + "/upload" + poolName + 
	        "/CapitalExpenditure/";

	      this.bean.setDataPath(dataPath);

//	      this.bean.setAccountCode("");
//	      this.bean.setBusinessName("");
//	      this.bean.setAccountId("");
//	      this.bean.setParentCode("");
//	      this.bean.setParentName("");
//	      this.bean.setContactName("");
//	      this.bean.setEmailAddress("");
//	      this.bean.setAddress("");
//	      this.bean.setCityId("");
//	      this.bean.setCityName("");
//	      this.bean.setCountryName("");
//	      this.bean.setStateName("");
//	      this.bean.setZipCode("");
//	      this.bean.setHiddenCheckBoxFlag("");
	      getNavigationPanel(2);
	    }
	    catch (Exception e) {
	      logger.error("Error in reset" + e);
	    }
	    return "success";
	  }

	  public String save()  throws Exception{
		 ManageComponentsModel model=new ManageComponentsModel();
		 model.initiate(this.context, this.session);
		 boolean result = model.saveComponent(this.bean);		 
		 String str=model.setCompnentID();
		 		 
		 boolean result1= model.saveParameters(this.bean);
		 
		 
		 
		 

		 if(result){
			 addActionMessage("Save Successfully");
		 }
		 else{
			 addActionMessage("Something is wrong");
		 }
//	    AccountMasterModel model = new AccountMasterModel();
//	    model.initiate(this.context, this.session);
//
//	    if (this.bean.getAccountCode().equals("")) {
//	      boolean result = model.addAccount(this.bean);
//	      if (result) {
//	        addActionMessage(getMessage("save"));
//	        getNavigationPanel(2);
//	      }
//	      else
//	      {
//	        addActionMessage("Account ID is already present.Please select new Account ID.");
//
//	        getNavigationPanel(2);
//	      }
//	    }
//	    else
//	    {
//	      boolean result = model.modAccount(this.bean, this.request);
//	      if (result) {
//	        addActionMessage(getMessage("update"));
//	        getNavigationPanel(2);
//	      }
//	      else
//	      {
//	        addActionMessage("Account ID is already present.Please select new Account ID.");
//
//	        getNavigationPanel(2);
//	      }
//
//	    }
//
//	    this.bean.setEnableAll("Y");
	    return "success";
	  }
	  public String data() throws Exception {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);

//	    model.gerSelectRecord(this.bean, this.request);
	    getNavigationPanel(2);
	    model.terminate();
	    this.bean.setEnableAll("Y");
	    return "success";
	  }

	  public String f9action() throws Exception {
	    String query = " SELECT ACCOUNT_ID, ACCOUNT_NAME,   TO_CHAR(RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM'),TO_CHAR(RECORD_MODIFIEDON,'DD-MM-YYYY HH:MM AM')  , DECODE(IS_ACTIVE,'Y','Yes','N','No'), ACCOUNT_CODE FROM CR_CLIENT_MASTER ORDER BY  ACCOUNT_CODE ASC ";

	    String[] headers = { getMessage("acc.id"), getMessage("business.name"), "Created On", "Last Modified", getMessage("is.active") };

	    String[] headerWidth = { "20", "20", "20", "20", "20" };

	    String[] fieldNames = { "accountId", "businessName", "recordCreatedOn", "recordModifiedOn", "isActive", "accountCode" };

	    int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
	    String submitFlag = "true";

	    String submitToMethod = "AccountMaster_data.action";

	    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	      submitFlag, submitToMethod);

	    return "f9page";
	  }

	  public String f9cityaction()
	    throws Exception
	  {
	    String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ORDER BY LOCATION_CODE ";
	    String[] headers = { getMessage("city.code"), getMessage("city") };

	    String[] headerWidth = { "10", "45" };
	    String[] fieldNames = { "cityId", "cityName" };
	    int[] columnIndex = { 0, 1 };
	    String submitFlag = "true";
	    String submitToMethod = "AccountMaster_getState.action ";

	    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	      submitFlag, submitToMethod);

	    return "f9page";
	  }

	  public String getState()
	    throws Exception
	  {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);

	    getNavigationPanel(2);
	    this.bean.setEnableAll("Y");
	    model.terminate();
	    return "success";
	  }

	  public String f9parent()
	  {
	    String query = "SELECT ACCOUNT_NAME,ACCOUNT_CODE FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='Y'";
//	    if (!this.bean.getAccountCode().equals("")) {
//	      query = query + " AND ACCOUNT_CODE NOT IN (" + this.bean.getAccountCode() + ")";
//	    }
	    query = query + " ORDER BY  ACCOUNT_CODE ASC ";

	    String[] headers = { getMessage("business.name") };

	    String[] headerWidth = { "100" };

	    String[] fieldNames = { "parentName", "parentCode" };

	    int[] columnIndex = { 0, 1 };

	    String submitFlag = "false";

	    String submitToMethod = "";

	    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

	    return "f9page";
	  }

	  public String callForCRMMapping()
	    throws Exception
	  {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);
	    String requestID = this.request.getParameter("accountId");
//	    model.callForCRMMapping(this.bean, requestID);
	    getNavigationPanel(3);
	    this.bean.setEnableAll("Y");
	    model.terminate();
	    return "crmMappingDtl";
	  }

	  public String callForBusinessUser()
	    throws Exception
	  {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);
	    String requestID = this.request.getParameter("accountId");
//	    model.callForBusinessUser(this.bean, requestID, this.request);
	    this.bean.setEnableAll("Y");
	    model.terminate();
	    return "businessUserDtl";
	  }

	  public String f9informTo() throws Exception
	  {
	    try {
	      String[] empCode = this.request.getParameterValues("keepInformToCode");
	      String code = this.bean.getUserEmpId();
	      if (empCode != null) {
	        for (int i = 0; i < empCode.length; i++) {
	          code = code + "," + empCode[i];
	        }
	      }

	      String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME \t,EMP_ID  FROM HRMS_EMP_OFFC    ";

	      query = query + " WHERE 1=1 AND EMP_STATUS='S'";
	      if ((this.bean.getUserProfileDivision() != null) && 
	        (((String) this.bean.getUserProfileDivision()).length() > 0)) {
	        query = query + "AND EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
	      }

	      query = query + "ORDER BY EMP_ID ";
	      String[] headers = { "Employee Id", "Employee Name" };
	      String[] headerWidth = { "20", "80" };
	      String[] fieldNames = { "informToken", "informName", "informCode" };
	      int[] columnIndex = { 0, 1, 2 };
	      String submitFlag = "false";
	      String submitToMethod = "";
	      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	        "false", "");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "f9page";
	  }

	  public String f9report() {
	    try {
	      String query = " SELECT REPORT_CODE, REPORT_NAME FROM CR_REPORT_MASTER   ORDER BY UPPER (REPORT_NAME) ";

	      String header = getMessage("reports");
	      String textAreaId = "paraFrm_reportName";

	      String hiddenFieldId = "paraFrm_reportCode";

	      String submitFlag = "";
	      String submitToMethod = "";

	      setMultiSelectF9(query, header, textAreaId, hiddenFieldId, 
	        submitFlag, submitToMethod);
	      return "f9multiSelect";
	    }
	    catch (Exception e) {
	      logger
	        .error("Error in f9report method Action : " + 
	        e.getMessage());
	    }return "";
	  }

	  public String saveCrmMappingDtl()
	  {
//	    AccountMasterModel model = new AccountMasterModel();
//	    model.initiate(this.context, this.session);
//
//	    String[] keepInformToCode = this.request.getParameterValues("keepInformToCode");
//	    boolean result = model.saveCrmMappingDtl(this.bean, this.request, keepInformToCode);
//
//	    if (result) {
//	      addActionMessage(getMessage("save"));
//	      String requestID = this.bean.getAccountCode();
//	      model.callForCRMMapping(this.bean, requestID);
//	    }
//	    else
//	    {
//	      addActionMessage("duplicate record found");
//	    }
//
//	    getNavigationPanel(3);
//	    this.bean.setEnableAll("Y");

	    return "crmMappingDtl";
	  }
       public String addClientUserLstDtl() {
//	    AccountMasterModel model = new AccountMasterModel();
//	    model.initiate(this.context, this.session);
//
//	    String email = this.bean.getEmailClientAddress().trim();
//	    if (this.bean.getParacode().equals(""))
//	    {
//	      boolean result = model.saveClientUserLstDtl(this.bean, this.request);
//	      String userId;
//	      if (result) {
//	        addActionMessage(getMessage("save"));
//
//	        String requestID = this.bean.getAccountCode();
//	        model.callForBusinessUser(this.bean, requestID, this.request);
//	        this.bean.setFirstName("");
//	        this.bean.setLastName("");
//	        this.bean.setEmailClientAddress("");
//	        this.bean.setCellNumber("");
//	        this.bean.setParentClientCode("");
//	        this.bean.setParentClientName("");
//
//	        String applicationId = this.bean.getHiddencode();
//	        userId = this.bean.getUserEmpId();
//	      }
//	      else
//	      {
//	        addActionMessage("E-mail ID is already present. Please select new E-mail ID.");
//	        String requestID = this.bean.getAccountCode();
//	        model.callForBusinessUser(this.bean, requestID, this.request);
//	      }
//	    }
//	    else
//	    {
//	      System.out.println("UPDATEEEEEE");
//	      boolean result = model.modClientUserLstDtl(this.bean, this.request);
//
//	      addActionMessage("Records Updated successfully.");
//	      String requestID = this.bean.getAccountCode();
//	      model.callForBusinessUser(this.bean, requestID, this.request);
//	      this.bean.setFirstName("");
//	      this.bean.setLastName("");
//	      this.bean.setEmailClientAddress("");
//	      this.bean.setCellNumber("");
//	      this.bean.setParentClientCode("");
//	      this.bean.setParentClientName("");
//	    }
//
//	    this.bean.setEnableAll("Y");
	    return "businessUserDtl";
	  }

//	  private void sendFinalMail(String applicationId, String userId, String email)
//	  {
//	    try {
//	      AccountMasterModel model = new AccountMasterModel();
//
//	      model.initiate(this.context, this.session);
//	      EmailTemplateBody templateApp = new EmailTemplateBody();
//	      templateApp.initiate(this.context, this.session);
//	      templateApp.setEmailTemplate("DecisionOne reporting portal Login information");
//	      templateApp.getTemplateQueries();
//	      EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
//	      templateQueryApp1.setParameter(1, userId);
//	      EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
//	      templateQueryApp2.setParameter(1, email.trim());
//	      EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
//	      templateQueryApp3.setParameter(1, applicationId);
//	      templateApp.configMailAlert();
//	      templateApp.sendApplicationMail();
//	      templateApp.clearParameters();
//	      templateApp.terminate();
//	      model.terminate();
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	  }

	  public String deleteClientUserLstDtl()
	    throws Exception
	  {
	    try
	    {
	      AccountMasterModel model = new AccountMasterModel();
	      model.initiate(this.context, this.session);
	      String clientUserID = this.request.getParameter("clientUserId");
//	      boolean result = model.delClientUserLstDtl(this.bean, this.request, clientUserID);
//	      String requestID = this.bean.getAccountCode();
//	      model.callForBusinessUser(this.bean, requestID, this.request);
	      model.terminate();
//	      if (result) {
//	        addActionMessage(getMessage("delete"));
//	        reset();
//	      } else {
//	        addActionMessage(getMessage("no result"));
//	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return "businessUserDtl";
	  }

	  public String f9ClientParent()
	  {
	    try
	    {
	      String code = this.request.getParameter("accountParentCode");

	      String query = " SELECT ACCOUNT_CODE,ACCOUNT_NAME||' '||DECODE(PARENT_FLAG,'Y','(P)','N',' ') FROM CR_CLIENT_MASTER WHERE  1=1 ";
	      if (!code.equals("")) {
	        query = query + " AND (PARENT_CODE IN (" + code + ")OR ACCOUNT_CODE=" + code + ")";
	      }
	      query = query + " ORDER BY  ACCOUNT_CODE ASC";
	      String header = getMessage("parent");
	      String textAreaId = "paraFrm_parentClientName";
	      String hiddenFieldId = "paraFrm_parentClientCode";
	      String submitFlag = "";
	      String submitToMethod = "";
	      setMultiSelectF9(query, header, textAreaId, hiddenFieldId, 
	        submitFlag, submitToMethod);
	      return "f9multiSelect";
	    } catch (Exception e) {
	      logger
	        .error("Error in f9report method Action : " + 
	        e.getMessage());
	    }return "";
	  }

	  public String applyFilter()
	  {
	    try {
	      AccountMasterModel model = new AccountMasterModel();
	      model.initiate(this.context, this.session);

	      
	      String searchMessage = this.request.getParameter("searchMessage");
//	      if(!(bean.getCrmCode().equals(""))){
//				String searchCRMMessage = bean.getCrmCode();
//				model.applyFilters(this.bean, this.request, searchMessage,searchCRMMessage);
//			
//			}else{
//				model.applyFilters(this.bean, this.request, searchMessage,"");
//			}
	    ///  model.applyFilters(this.bean, this.request, searchMessage);
//	      this.bean.setHiddenSearchMessage(searchMessage);
	      getNavigationPanel(1);
	      model.terminate();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "input";
	  }

	  
	  /*public String getCRMDetailsAction()
	  {
	    try {
	      AccountMasterModel model = new AccountMasterModel();
	      model.initiate(this.context, this.session);

	      String searchMessage = bean.getCrmCode();

	      model.applyCRMFilters(this.bean, this.request, searchMessage);
	      this.bean.setHiddenSearchMessage(searchMessage);
	      getNavigationPanel(1);
	      model.terminate();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "input";
	  }*/
	  
	  public void viewUploadedFile() {
	    try {
	      String uploadFileName = this.request.getParameter("uploadFileName");
	      String dataPath = this.request.getParameter("dataPath");

	      OutputStream oStream = null;
	      this.response.getOutputStream();
	      FileInputStream fsStream = null;
	      String fileName = "";
	      String mimeType = "";
	      try {
	        String poolName = String.valueOf(this.session
	          .getAttribute("session_pool"));
	        if ((!poolName.equals("")) && (poolName != null)) {
	          poolName = "/" + poolName;
	        }
	        fileName = uploadFileName;
	        fileName = fileName.replace(".", "#");
	        String[] extension = fileName.split("#");
	        String ext = extension[(extension.length - 1)];
	        fileName = fileName.replace("#", ".");
	        if (ext.equals("pdf")) {
	          mimeType = "acrobat";
	        }
	        else if (ext.equals("doc")) {
	          mimeType = "msword";
	        }
	        else if (ext.equals("xls")) {
	          mimeType = "msexcel";
	        }
	        else if (ext.equals("xlsx")) {
	          mimeType = "msexcel";
	        }
	        else if (ext.equals("jpg")) {
	          mimeType = "jpg";
	        }
	        else if (ext.equals("txt")) {
	          mimeType = "txt";
	        }
	        else if (ext.equals("gif")) {
	          mimeType = "gif";
	        }

	        if ((fileName == null) && 
	          (fileName.length() <= 0)) {
	          fileName = "blank.doc";
	        }

	        String path = dataPath + fileName;
	        oStream = this.response.getOutputStream();
	        if (!ext.equals("pdf"))
	        {
	          this.response.setHeader("Content-type", "application/" + mimeType);
	        }

	        this.response.setHeader("Content-disposition", "inline;filename=\"" + 
	          fileName + "\"");

	        fsStream = new FileInputStream(path);
	        int iChar;
	        while ((iChar = fsStream.read()) != -1)
	        {
	          //int iChar;
	          oStream.write(iChar);
	        }
	      }
	      catch (FileNotFoundException e)
	      {
	        addActionMessage("proof document not found");
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
	    catch (Exception localException1)
	    {
	    }
	  }

	  public String editClientUserLstDtl()
	    throws Exception
	  {
	    logger.info("inside edit");
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);
//	    String clientUserID = this.bean.getParacode();
//
//	    model.editClientUserLstDtl(this.bean, this.request, clientUserID);
//	    String requestID = this.bean.getAccountCode();
//	    model.callForBusinessUser(this.bean, requestID, this.request);
	    model.terminate();
	    getNavigationPanel(2);
	    this.bean.setEnableAll("Y");
	    return "businessUserDtl";
	  }

	  public String report()
	  {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);
	    String reportPath = "";
//	    model.getReport(this.bean, this.response, this.request, reportPath);
	    model.terminate();
	    return null;
	  }

	  public String callClientReport()
	  {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);
	    String reportPath = "";
//	    model.getClientReport(this.bean, this.response, this.request, reportPath);
	    model.terminate();
	    return null;
	  }

	  public String callCRMReport()
	  {
	    AccountMasterModel model = new AccountMasterModel();
	    model.initiate(this.context, this.session);
	    String reportPath = "";
//	    model.getCRMReport(this.bean, this.response, this.request, reportPath);
	    model.terminate();
	    return null;
	  }
	  /**
		 * To search employees to whom CRM is to be assigned
		 * 
		 * @return f9page
		 */
		public String f9crmEmployee() {

			try {
				String query = "SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),EMP_ID "
						+ " FROM HRMS_EMP_OFFC "
						+ " LEFT JOIN HRMS_TITLE t1 ON(t1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
						+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)    "
						+ "   WHERE   EMP_STATUS ='S' ";

				///query += " CONNECT BY PRIOR EMP_ID = EMP_REPORTING_OFFICER ";

				logger.info("F9 Query :  " + query);

				String[] headers = { "Employee Id", "Employee Name" };
				String[] headerwidth = { "20", "80" };

				String[] fieldNames = { "crmToken",
						"crmName", "crmCode" };
				int[] columnIndex = { 0, 1, 2 };
				String submitFlage = "true";
				String submitToMethod = "AccountMaster_applyFilter.action";
				setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
						submitFlage, submitToMethod);
			} catch (Exception e) {
				logger.error("Exception in f9 action : " + e);
			}

			return "f9page";

		}

	

}
