/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.PayrollZoneMaster;
import org.paradyne.model.payroll.PayrollZoneMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author saip
 *
 */
public class PayrollZoneMasterAction extends ParaActionSupport {
	PayrollZoneMaster payzone;
	String poolDir = "";
	String fileName = "";
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PayrollZoneMasterAction.class);

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		payzone=new PayrollZoneMaster();
		payzone.setMenuCode(628);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
		logger.info("Inside prepare " + poolDir);

	}
	public void prepare_withLoginProfileDetails() throws Exception {
				empTypeData();
		}
	public Object getModel() {
		// TODO Auto-generated method stub
		return payzone;
	}
public String empTypeData(){
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
	model.initiate(context,session);
	
	model.EmpTypeData(payzone,request);
	model.terminate();
	return "success";
}
public String getEmpExceptionData(){
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
	model.initiate(context,session);
	
	model.getEmpExceptionData(payzone,request);
	model.terminate();
	return "success";
}
public String branchData(){
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
	model.initiate(context,session);
	model.BranchData(payzone,request);
	model.terminate();
	return "success";
}
	public PayrollZoneMaster getPayzone() {
		return payzone;
	}

	public void setPayzone(PayrollZoneMaster payzone) {
		this.payzone = payzone;
	}

	public String branchUpdate() {
		PayrollZoneMasterModel model=new PayrollZoneMasterModel();
		String[] Branchid = request.getParameterValues("BranchID");
		Object[] BranchEsi  = request.getParameterValues("BranchEsiZone");
		Object[] BranchPt = request.getParameterValues("BranchPtZone");
		//Object[] BranchPf = request.getParameterValues("BranchPfZone");
		Object[] BranchMetro = request.getParameterValues("Branchmetro");
		Object[] Esicode = request.getParameterValues("esiCode");//ESI Code filed Added by Nilesh 24th June 11
		//Object[] Ptcity = request.getParameterValues("Ptaxcitycode");
		Object[] Ptcity=new Object[Branchid.length];
		//Ptaxcitycode
		for (int i = 0; i < Branchid.length; i++) {
			if(!request.getParameter("Ptaxcitycode"+i).equals(""))
			Ptcity[i] = request.getParameter("Ptaxcitycode"+i);
			else
				Ptcity[i] ="";
			logger.info("values...!!"+i+" content..." +String.valueOf(Ptcity[i]));
		}
		//Ptaxcityname
				
		//boolean result=model.branchupdating(payzone,Branchid,BranchEsi,BranchPt,BranchPf);
	
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
		+ "/xml/Payroll/salaryZone_branch.xml";
		
		String str ="";
		str=model.branchupdating(payzone,Branchid,BranchEsi,BranchPt,fileName,Ptcity,BranchMetro,Esicode);
		if(str=="modified"){
			addActionMessage("Record updated successfully.");
			
		}
		else
			addActionMessage("Error while updating record.");
		model.BranchData(payzone,request);
		model.terminate();
		
		return reset();
	}
	public String empUpdate() {
		PayrollZoneMasterModel model=new PayrollZoneMasterModel();
		Object[] Empid = request.getParameterValues("EmptypeID");
		Object[] EmpEsi  = request.getParameterValues("ETesiZone");
		Object[] EmpPt = request.getParameterValues("ETptZone");
		//Object[] EmpPf = request.getParameterValues("ETpfZone");
		Object[] EmpPfMinAmt = request.getParameterValues("ETpfMinAmt");
		
		logger.info("BEFore going to model........!");
		//boolean result= model.EmployeeTypeupdating(payzone,Empid,EmpEsi,EmpPt,EmpPf);
		
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
		+ "/xml/Payroll/salaryZone_emptype.xml";
		
		String str =""; 
		str=model.EmployeeTypeupdating(payzone,Empid,EmpEsi,EmpPt,fileName,EmpPfMinAmt);
		if(str=="modified"){
			addActionMessage("Record updated successfully.");
			
		}
		else
			addActionMessage("Error while updating record.");
			
		model.EmpTypeData(payzone,request);
		model.terminate();
		return reset();
	}
	public String callforEmpedit() {
		PayrollZoneMasterModel model=new PayrollZoneMasterModel();
		model.initiate(context, session);
		model.callforempedit(payzone);
		model.terminate();
		
		return "success";
	}
	public String callforBranchedit() {
		PayrollZoneMasterModel model=new PayrollZoneMasterModel();
		model.initiate(context, session);
		model.callforbranchedit(payzone);
		model.terminate();
		
		return "success";
	}

public String callPage() throws Exception {
		
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
			model.initiate(context,session);
			model.EmpTypeData(payzone,request);
			model.terminate();
		 return SUCCESS;
		
		}
public String callPageBr() throws Exception {
	
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
			model.initiate(context,session);
			model.BranchData(payzone,request);
			model.terminate();
		 return reset();
		
		}
public String reset() {
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
	model.initiate(context,session);
	
	payzone.setBranchEsiZone("");
	payzone.setBranchhiddencode("");
	payzone.setBranchID("");
	payzone.setBranchPfZone("");
	payzone.setBranchPtZone("");
	payzone.setBranchName("");
	payzone.setETesiZone("");
	payzone.setETptZone("");
	payzone.setETpfZone("");
	payzone.setEmptypeID("");
	payzone.setTypeName("");
	payzone.setEmployeetypehiddencode("");
	
	model.terminate();
	return "success";
	
}
public String f9Emptype() throws Exception 
{
	String query = " SELECT  TYPE_ID,TYPE_NAME,TYPE_ESI,TYPE_PT,TYPE_PF FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ";	
	
	String[] headers={"Employee Type Code", "Employee Type"};
	String[] headerWidth={"20", "80"};
	String[] fieldNames={"EmptypeID","typeName","ETesiZone","ETptZone","ETpfZone"}; 
	int[] columnIndex={0,1,2,3,4};
	String submitFlag = "false";
	String submitToMethod="";
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	return "f9page";
}
public String f9Branch() throws Exception 
{
	String query = " SELECT CENTER_ID, NVl(CENTER_NAME,' '), NVL(DECODE(CENTER_ESI,'Y','Yes','N','No'),' '),NVL(DECODE(CENTER_PT,'Y','Yes','N','No'),' '),"
		            +"NVL(DECODE(CENTER_PF,'Y','Yes','N','No'),' ') FROM  HRMS_CENTER ORDER BY CENTER_ID ";	
	String[] headers={"Branch Code", "Branch Name"};
	String[] headerWidth={"20","80"};
	String[] fieldNames={"BranchID","BranchName","BranchEsiZone","BranchPtZone","BranchPfZone"};  
	int[] columnIndex={0,1,2,3,4};
	String submitFlag = "false";
	String submitToMethod="";
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	return "f9page";
}


public String f9actionEmp() throws Exception 
{
	String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,CENTER_NAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				+" LEFT JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER) "
				+" WHERE (SAL_PTAX_FLAG != 'N' OR SAL_PTAX_FLAG IS NULL) AND CENTER_PT='Y' ORDER BY UPPER(NAME) ";	
	String[] headers={getMessage("employee.id"),getMessage("employee"), getMessage("branch")};
	String[] headerWidth={"20","60","20"};
	String[] fieldNames={"empToken","empName","empBranch","empCode"};  
	int[] columnIndex={0,1,2,3};
	String submitFlag = "false";
	String submitToMethod="";
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	return "f9page";
}
public String f9city() throws Exception {
	
	String query = "SELECT  LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE  LOCATION_LEVEL_CODE=1 ORDER BY LOCATION_CODE";

	String[] headers={"State Code", "State Name"};
	
	String[] headerWidth={"20", "80"};
	
	/*String Branchid = request.getParameter("dupid");
	System.out.println("value....!!"+Branchid);*/
	System.out.println("value....!!"+payzone.getRowId());
  /* if (payzone.getDupid().equals("")||payzone.getDupid()==null) {
	   payzone.setDupid("0");
	   
	
}*/
	//String[] fieldNames={"Ptaxcitycode","Ptaxcityname"};
	String[] fieldNames={"Ptaxcitycode"+payzone.getRowId(),"Ptaxcityname"+payzone.getRowId()};
	
	int[] columnIndex={0,1};
	
	String submitFlag = "false";

	String submitToMethod="";
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}
public String addEmployeeToException(){
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
	model.initiate(context,session);
	boolean isDataAvailable = model.checkSalMiscData(payzone);
	if(model.addEmployeeToException(payzone,isDataAvailable)){
		addActionMessage("Employee added to excepetion list");
	}else{
		addActionMessage("Employee can't be added to excepetion list");
	}
	model.getEmpExceptionData(payzone,request);
	payzone.setEmpCode("");
	payzone.setEmpName("");
	payzone.setEmpToken("");
	model.terminate();
	
	return getEmpExceptionData();
}

public String removeEmployee(){
	String []empCodes = request.getParameterValues("hdeleteCode");
	String empIdString ="";
	try{
		for (int i = 0; i < empCodes.length; i++) {
			if(empCodes[i] != null && !empCodes[i].equals("")&& !empCodes[i].equals("null")){
				if(i==empCodes.length-1)
				empIdString +=empCodes[i];
				else empIdString += empCodes[i]+",";
			}
		}
		if(empIdString.lastIndexOf(",")==(empIdString.length()-1)){
			empIdString = empIdString.substring(0,empIdString.length()-1);
		}
	}catch (Exception e) {
		logger.error("exception "+e);
	}
	PayrollZoneMasterModel model=new PayrollZoneMasterModel();
	model.initiate(context,session);
	if(model.removeEmployee(empIdString)){
		addActionMessage("Employee removed from excepetion list");
		}else{
			addActionMessage("Employee can't be removed from excepetion list");
		}	
	
	model.terminate();
	return getEmpExceptionData();
}
	
}
