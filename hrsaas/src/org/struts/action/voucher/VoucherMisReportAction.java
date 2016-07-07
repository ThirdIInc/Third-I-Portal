/**
 * 
 */
package org.struts.action.voucher;

import org.paradyne.bean.voucher.VoucherMisReport;
import org.paradyne.model.voucher.VoucherMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 *
 */
public class VoucherMisReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	VoucherMisReport voucherRep;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		this.voucherRep=new VoucherMisReport();
		voucherRep.setMenuCode(374);
	}
	
	public String input() throws Exception {
		VoucherMisReportModel model = new VoucherMisReportModel();
		model.initiate(context, session);
		if (voucherRep.isGeneralFlag())	{
			voucherRep.setEmpCode(voucherRep.getUserEmpId());
			model.getLoginUserInfo(voucherRep, request);
		}
		model.terminate();
		return INPUT;
	}
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return voucherRep;
	}

	public VoucherMisReport getVoucherRep() {
		return voucherRep;
	}

	public void setVoucherRep(VoucherMisReport voucherRep) {
		this.voucherRep = voucherRep;
	}
	public String report(){
		VoucherMisReportModel model=new VoucherMisReportModel();
		model.initiate(context, session);
		logger.info("inside report action");
		model.getReport(request,response ,voucherRep);
		model.terminate();
		return null;
	}
public String f9actionEname() throws Exception {
		
		/*
		 * Select the Employee F9
		 * 
		 * 
		 */
	
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+" EMP_ID FROM HRMS_EMP_OFFC"
				+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)";
		
				query += getprofileQuery(voucherRep);
				
				query +=" AND EMP_STATUS ='S' ORDER BY EMP_ID";

		
		String[] headers = {getMessage("employee.id"),getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empToken","ename", "empCode" };

		int[] columnIndex = {  0,1,2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}

public String f9Division() throws Exception {
	
	String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
	
	if(voucherRep.getUserProfileDivision() !=null && voucherRep.getUserProfileDivision().length()>0)
		query+= " WHERE DIV_ID IN ("+voucherRep.getUserProfileDivision() +")"; 
		query+= " ORDER BY  DIV_ID ";
		
	String[] headers = { getMessage("division.code"),
			getMessage("division") };

	String[] headerWidth = { "30", "70" };

	String[] fieldNames = { "divCode", "division" };

	
	int[] columnIndex = { 0, 1 };

	
	String submitFlag = "false";


	
	String submitToMethod = "";

	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}

public String f9Branch() throws Exception {
	
	String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
			+ " ORDER BY  CENTER_ID ";

	String[] headers = { getMessage("branch.code"),
			getMessage("branch") };

	String[] headerWidth = { "30", "70" };

	String[] fieldNames = { "branchCode", "branch" };

	
	int[] columnIndex = { 0, 1 };

	
	String submitFlag = "false";


	
	String submitToMethod = "";

	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}
public String f9Department() throws Exception {
	
	String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
			+ " ORDER BY  DEPT_ID ";

	String[] headers = { getMessage("department.code"),
			getMessage("department") };

	String[] headerWidth = { "30", "70" };

	String[] fieldNames = { "deptCode", "department" };

	
	int[] columnIndex = { 0, 1 };

	
	String submitFlag = "false";


	
	String submitToMethod = "";

	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}
public String f9Deignation() throws Exception {
	
	String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
			+ " ORDER BY  RANK_ID ";

	String[] headers = { getMessage("designation.code"),
			getMessage("designation") };

	String[] headerWidth = { "30", "70" };

	String[] fieldNames = { "desgCode", "designation" };

	
	int[] columnIndex = { 0, 1 };

	
	String submitFlag = "false";


	
	String submitToMethod = "";

	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}
public String f9VchHead() throws Exception {
	
	String query = " SELECT VCH_CODE,VCH_NAME FROM HRMS_VCH_HD "
			+ " ORDER BY  VCH_CODE ";

	String[] headers = { getMessage("vchHeadCode"),
			getMessage("vchHead") };

	String[] headerWidth = { "30", "70" };

	String[] fieldNames = { "vchHeadCode", "vchHeadName" };

	
	int[] columnIndex = { 0, 1 };

	
	String submitFlag = "false";


	
	String submitToMethod = "";

	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}
public String reset(){
	voucherRep.setEmpCode("");
	voucherRep.setEname("");
	voucherRep.setFrmDate("");
	voucherRep.setToDate("");
	voucherRep.setEmpToken("");
	voucherRep.setStatus("");
	voucherRep.setDivCode("");
	voucherRep.setDivision("");
	voucherRep.setDepartment("");
	voucherRep.setDeptCode("");
	voucherRep.setBranch("");
	voucherRep.setBranchCode("");
	voucherRep.setDesgCode("");
	voucherRep.setDesignation("");
	voucherRep.setVchHeadCode("");
	voucherRep.setVchHeadName("");
	
	return SUCCESS;
}

}
