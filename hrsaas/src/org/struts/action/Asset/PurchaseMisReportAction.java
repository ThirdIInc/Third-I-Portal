package org.struts.action.Asset;

import org.paradyne.bean.Asset.PurchaseMisReport;
import org.paradyne.model.Asset.PurchaseMisReportModel;
import org.struts.lib.ParaActionSupport;

public class PurchaseMisReportAction extends ParaActionSupport {

	 PurchaseMisReport purchasebean;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		purchasebean=new PurchaseMisReport();
		purchasebean.setMenuCode(660);
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return purchasebean;
	}
	public String report(){
		PurchaseMisReportModel model;
		try {
			model = new PurchaseMisReportModel();
			model.initiate(context, session);
			model.getReport(request, response, purchasebean);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
public String f9actionEname() throws Exception {
		
		/*
		 * Select the Employee F9
		 * 
		 * 
		 */
	
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+" EMP_ID FROM HRMS_EMP_OFFC ";
			//	+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
		
		query += getprofileQuery(purchasebean);
	 
		query +=" ORDER BY EMP_ID ";

		
		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"10", "90" };

		String[] fieldNames = {"empToken","ename", "empCode" };

		int[] columnIndex = {0,1,2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
public String reset(){
	purchasebean.setEmpCode("");
	purchasebean.setEname("");
	purchasebean.setFrmDate("");
	purchasebean.setToDate("");
	purchasebean.setEmpToken("");
	purchasebean.setStatus("");
	purchasebean.setVendorCode("");
	purchasebean.setVendorName("");
	purchasebean.setSubtypeCode ("");
	purchasebean.setSubtypeName("");
	purchasebean.setCategoryCode("");
	purchasebean.setCategoryName("");
	purchasebean.setDesigCode("");
	purchasebean.setDesigName("");
	purchasebean.setBranchCode("");
	purchasebean.setBranchName("");
	purchasebean.setDeptCode("");
	purchasebean.setDeptName("");
	purchasebean.setDivisioncode("");
	purchasebean.setDivisionName("");
	return SUCCESS;
}

public PurchaseMisReport getOutMisbean() {
	return purchasebean;
}

public void setOutMisbean(PurchaseMisReport purchasebean) {
	this.purchasebean = purchasebean;
}

public String f9actionVen() throws Exception {
	
	String query = "SELECT VENDOR_CODE, VENDOR_NAME FROM HRMS_VENDOR where VENDOR_TYPE='S' ORDER BY VENDOR_CODE ";
	String[] headers = {getMessage("venid"), getMessage("venName")};
	String[] headerWidth = {"10", "90" };
	String[] fieldNames = {"vendorCode","vendorName"};
	int[] columnIndex = {0,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
	
	return "f9page";
}

public String f9actionDept() throws Exception {
	
	String query = "Select DEPT_ID,DEPT_NAME from HRMS_DEPT order by DEPT_ID";
	String[] headers = {getMessage("department.code"),getMessage("department")};
	String[] headerWidth = {"10", "90" };
	String[] fieldNames = {"deptCode" ,"deptName"};
	int[] columnIndex = {0,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
	
	return "f9page";
}

public String f9actionBranch() throws Exception {
	
	String query = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID ";
	String[] headers = {getMessage("branch.code"),getMessage("branch")};
	String[] headerWidth = {"10", "90" };
	String[] fieldNames = {"branchCode","branchName"};
	int[] columnIndex = {0,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9actionDesig() throws Exception {
	
	String query = " SELECT  RANK_ID,RANK_NAME FROM HRMS_RANK ORDER BY RANK_ID";
	String[] headers = {getMessage("designation.code"),getMessage("designation")};
	String[] headerWidth = {"10", "90" };
	String[] fieldNames = {"desigCode","desigName"};
	int[] columnIndex = {0,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9actionCate() throws Exception {
	
	String query = "SELECT ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME FROM HRMS_ASSET_CATEGORY  WHERE ASSET_ISACTIVE = 'Y'  ORDER BY ASSET_CATEGORY_CODE ";
	String[] headers = {getMessage("category.code"),getMessage("ass.cat")};
	String[] headerWidth = {"10", "90" };
	String[] fieldNames = {"categoryCode","categoryName"};
	int[] columnIndex = {0,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9actionSubtype() throws Exception {
	
	String query = "SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,'') FROM HRMS_ASSET_SUBTYPE WHERE ASSET_SUBTYPE_ISACTIVE='Y'  ORDER BY ASSET_SUBTYPE_CODE ";
	String[] headers = {getMessage("subcode"),getMessage("subtype")};
	String[] headerWidth = {"10", "90" };
	String[] fieldNames = {"subtypeCode","subtypeName"};
	int[] columnIndex = {0,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9actionDiv() throws Exception {
	String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION  ";	
	
	if(purchasebean.getUserProfileDivision() !=null && purchasebean.getUserProfileDivision().length()>0)
		query+= " WHERE DIV_ID IN ("+purchasebean.getUserProfileDivision() +")"; 
		query+= " ORDER BY UPPER(DIV_NAME) ";
		
	String[] headers={getMessage("division")};
	String[] headerWidth = {"100"};	
	String[] fieldNames = {"divisionName","divisioncode"};
	int[] columnIndex = {0,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
	
	return "f9page";
}




}
