/**
 * 
 */
package org.struts.action.payroll.pf;

import org.paradyne.bean.payroll.pf.PfInterestCal;
import org.paradyne.model.payroll.pf.PfInterestCalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0554
 *
 */
public class PfInterestCalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PfInterestCalAction.class);
	PfInterestCal pfIntCal ;
	@Override
	public void prepare_local() throws Exception {
		pfIntCal = new PfInterestCal();
		pfIntCal.setMenuCode(936);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return pfIntCal;
	}

	public PfInterestCal getPfIntCal() {
		return pfIntCal;
	}

	public void setPfIntCal(PfInterestCal pfIntCal) {
		this.pfIntCal = pfIntCal;
	}
	
	public String f9emp() throws Exception {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
			+" (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) AS NAME ,NVL(CENTER_NAME,' '),HRMS_EMP_OFFC.EMP_ID,EMP_STATUS, "
			+" DIV_NAME,DEPT_NAME,RANK_NAME,NVL(SAL_GPFNO,' ') FROM HRMS_EMP_OFFC  "
			+" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
			+" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
			+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
			+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
			+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
			+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
			+" WHERE SAL_PFTRUST_FLAG='Y' AND EMP_STATUS='S'  ORDER BY UPPER(NAME)";


	String[] headers = {getMessage("employee.id"),getMessage("employee"),getMessage("branch") };

	String[] headerWidth = { "15", "35","25" };

	String[] fieldNames = { "empToken",
			"empName","branchName","empId","empStatus","divName","deptName","desgName","empPfNo" };

	int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String getDetails(){
		PfInterestCalModel model = new PfInterestCalModel();
		model.initiate(context, session);
		//model.getDetails(pfIntCal);
		logger.info("cal type in action =="+pfIntCal.getCalcType());
		model.calculatePf(pfIntCal,request);
		model.terminate();
		return SUCCESS;
	}
	public String input(){
		return SUCCESS;
	}
	public String save(){
		PfInterestCalModel model = new PfInterestCalModel();
		model.initiate(context, session);
		if(model.save(pfIntCal,request)){
			addActionMessage(getMessage("save"));
			reset();
		}else{
			addActionMessage(getMessage("save.error"));
		}
		model.terminate();
		return SUCCESS;
	}
	public String reCalculate(){
		PfInterestCalModel model = new PfInterestCalModel();
		model.initiate(context, session);
		
		if(model.updateEmpPfLedger(pfIntCal,request)){
			addActionMessage(getMessage("update"));
		}else{
			addActionMessage(getMessage("update.error"));
		}
		displayPfDetails();
		model.terminate();
		return SUCCESS;
	}
	public String displayPfDetails(){
		PfInterestCalModel model = new PfInterestCalModel();
		model.initiate(context, session);
		model.displayPfDetails(pfIntCal, request);
		model.terminate();
		return SUCCESS;
	}
	public String reset(){
		pfIntCal.setEmpId("");
		pfIntCal.setEmpName("");
		pfIntCal.setEmpPfNo("");
		pfIntCal.setEmpToken("");
		pfIntCal.setEmpStatus("");
		pfIntCal.setDivName("");
		pfIntCal.setBranchName("");
		pfIntCal.setDeptName("");
		pfIntCal.setDesgName("");
		pfIntCal.setFromYear("");
		pfIntCal.setToMonth("");
		pfIntCal.setToYear("");
		pfIntCal.setCalcFlag("false");
		pfIntCal.setTotDeposite("");
		pfIntCal.setTotWithdraw("");
		pfIntCal.setIntAmount("");
		pfIntCal.setIntRate("");
		pfIntCal.setOpeningBalance("");
		pfIntCal.setClosingBalance("");
		pfIntCal.setTotPfLoan("");
		pfIntCal.setTotPfMPCont("");
		pfIntCal.setTotPfProg("");
		pfIntCal.setTotPfProgActual("");
		pfIntCal.setTotPfRefund("");
		pfIntCal.setTotPfRepay("");
		pfIntCal.setTotPfSub("");
		
		pfIntCal.setApplBranchCode("");
		pfIntCal.setApplBranchName("");
		pfIntCal.setApplDeptCode("");
		pfIntCal.setApplDeptName("");
		pfIntCal.setApplDesgCode("");
		pfIntCal.setApplDesgName("");
		pfIntCal.setApplDivisionCode("");
		pfIntCal.setApplDivisionName("");
		pfIntCal.setApplEmpCode("");
		pfIntCal.setApplEmpName("");
		pfIntCal.setApplETypeCode("");
		pfIntCal.setApplETypeName("");
		pfIntCal.setApplGradeCode("");
		pfIntCal.setApplGradeName("");
		//pfIntCal.setFilterFlag("false");
		return SUCCESS;
	}
	
	public String f9applDiv(){
		String query ="SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ORDER BY UPPER (DIV_NAME)";
		String header =getMessage("division");
		String textAreaId ="paraFrm_applDivisionName";
				
		String hiddenFieldId ="paraFrm_applDivisionCode";
		
		String submitFlag ="";
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applDept(){
		String query ="SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER (DEPT_NAME)";

		String header =getMessage("department");

		String textAreaId ="paraFrm_applDeptName";
		
		String hiddenFieldId ="paraFrm_applDeptCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applBranch(){
		String query ="SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER (CENTER_NAME)";
		
		String header =getMessage("branch");
		
		String textAreaId ="paraFrm_applBranchName";
		
		String hiddenFieldId ="paraFrm_applBranchCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applDesg(){
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
		
		String header =getMessage("designation");
		
		String textAreaId ="paraFrm_applDesgName";
		
		String hiddenFieldId ="paraFrm_applDesgCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applGrade(){
		String query = " SELECT CADRE_ID,CADRE_NAME  FROM HRMS_CADRE ORDER BY UPPER(CADRE_NAME)";
		
		String header =getMessage("grade");
		
		String textAreaId ="paraFrm_applGradeName";
		
		String hiddenFieldId ="paraFrm_applGradeCode";
		
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9applEType(){
		String query = " SELECT TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE ORDER BY UPPER(TYPE_NAME)";
		
		String header =getMessage("employee.type");
		
		String textAreaId ="paraFrm_applETypeName";
		
		String hiddenFieldId ="paraFrm_applETypeCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applEmp(){
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+" HRMS_EMP_OFFC.EMP_LNAME) AS NAME  FROM HRMS_EMP_OFFC" 
				+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+" WHERE SAL_PFTRUST_FLAG='Y' ORDER BY UPPER(NAME)";
		
		String header =getMessage("employee");
		
		String textAreaId ="paraFrm_applEmpName";
		
		String hiddenFieldId ="paraFrm_applEmpCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String openEmpCalcWindow(){
		PfInterestCalModel model = new PfInterestCalModel();
		pfIntCal.setEmpId(request.getParameter("empId"));
		model.initiate(context, session);
		model.setEmployeeDetails(pfIntCal);
		model.getEmpSalDetails(pfIntCal);
		model.terminate();
		return "pfIntestCalForEmp";
	}

}
