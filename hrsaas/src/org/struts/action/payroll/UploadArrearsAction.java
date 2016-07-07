/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.UploadArrears;
import org.paradyne.model.payroll.UploadArrearsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class UploadArrearsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadArrearsAction.class);
	UploadArrears uploadArrears;
	@Override
	public void prepare_local() throws Exception {
		uploadArrears = new UploadArrears();
		uploadArrears.setMenuCode(1106);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return uploadArrears;
	}
	
	public String f9CreditAction() throws Exception 
	{
		
		
		String query = "SELECT CREDIT_NAME,CREDIT_CODE FROM HRMS_CREDIT_HEAD" +
					   " WHERE CREDIT_APPLICABLE_ARREARS = 'Y' AND CREDIT_PAYFLAG = 'Y' ORDER BY CREDIT_CODE";		
		
		
		String[] headers={getMessage("credit.Name")};
		
		String[] headerWidth={"100"};
		
		
		String[] fieldNames={"uploadCreditName","uploadCreditCode"};
		
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:" + e);
			return "";
		}
	}

	public String f9Department() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"departmentName", "departmentId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Department:" + e);
			return "";
		}
	}

	/**
	 * Popup window contains list of all division
	 * 
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			query += " ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Division:" + e);
			return "";
		}
	} // end of f9Div
	
	
	/** This function is used to select the paybill.
	 * @return search page
	 */
	public final String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL WHERE 1=1"; 
			if(!(uploadArrears.getUserProfilePaybill().equals(""))) {
				query += " AND HRMS_PAYBILL.PAYBILL_ID IN ("+uploadArrears.getUserProfilePaybill()+")";
			}
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";

			String[] headers = { "Paybill Code", getMessage("pay.bill")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "paybillId", "paybillName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String downLoadFile(){
		UploadArrearsModel model = new UploadArrearsModel();
		model.initiate(context, session);
		model.downloadTemplate(uploadArrears,response);
		model.terminate();
		return null;
	}
	public String reset(){
		uploadArrears.setBranchId("");
		uploadArrears.setBranchName("");
		uploadArrears.setDepartmentId("");
		uploadArrears.setDepartmentName("");
		uploadArrears.setDivisionId("");
		uploadArrears.setDivisionName("");
		uploadArrears.setMonth("");
		uploadArrears.setYear("");
		uploadArrears.setUploadFileName("");
		uploadArrears.setUploadCreditCode("");
		uploadArrears.setUploadCreditName("");
		uploadArrears.setPaybillId("");
		uploadArrears.setPaybillName("");
		
		return SUCCESS;
	}
	public String uploadArrears(){
		UploadArrearsModel model = new UploadArrearsModel();
		model.initiate(context, session);
		String msg =model.uploadArrears(uploadArrears,response);
		logger.info("msg--"+msg);
		if(msg.equals("success")){
			addActionMessage("Arrears uploaded successfully.");
			reset();
		}else{
			addActionMessage("Errors in uploading Arrears.");
		}
		model.terminate();
		return SUCCESS;
	}

}
