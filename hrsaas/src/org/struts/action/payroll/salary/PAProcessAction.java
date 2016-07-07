package org.struts.action.payroll.salary;
import java.util.ArrayList;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.salary.PAProcess;
import org.paradyne.model.payroll.salary.PAProcessModel;
/**
 * @author Balaji
 * 11-08-2008
 */
/**
 * This class is used for calculate the allowance of employees.
**/
public class PAProcessAction extends ParaActionSupport{

	PAProcess paPro;

	/**
	 * @return
	 */
	public PAProcess getPaPro() {
		return paPro;
	}

	/**
	 * @param paPro
	 */
	public void setPaPro(PAProcess paPro) {
		this.paPro = paPro;
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return paPro;
	}
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub		
		paPro=new PAProcess(); 
		paPro.setMenuCode(629);
	}
	 
	
	
	/**
	 * THIS METHOD PROCESSES THE EMPLOYEE SPECIFIC
	 * ALLOWANCE/INCENTIVE 
	 * @throws Exception
	 */
	/**
	 * @return String Type success
	 */
	public String process()
	{
		PAProcessModel model = new PAProcessModel();
		model.initiate(context, session);
	 	model.callProcess(paPro,request);
	 	callFlag();
		model.terminate(); 
		return "success";
	}
	/**
	  This method for setting the flag
	 */
	
	/**
	 * @return String Type success
	 */
	public void callFlag()
	{
		// for dispaly the table 
		     String data ="data";
			 request.setAttribute("data", data);
		 
		if(paPro.getHidDisbType().equals("A"))
		{
			paPro.setRadioFlag("true"); 
			paPro.setApprFlag("true"); 
		}else
		{
			paPro.setRadioFlag("false");  
			paPro.setApprFlag("false");
		}
		if(!(paPro.getPaProcessId().equals("")))
		{
		  paPro.setLockFlag("true");
		}
		 paPro.setDisbursFlag("true"); 
		if(paPro.getFinaliseFlag().equals("L"))
		{
			paPro.setLockActivateFlag("false"); 
			
		}else
		{
			paPro.setLockActivateFlag("true"); 
		}
		if(paPro.getHiddenChk().equals("Y") || paPro.getSalaryFlag().equals("true"))
		{
			 String chkBox="chkBox";
			request.setAttribute("chkBox", chkBox);
		}
	}
	
	/**
	 * THIS METHOD SAVES THE ALLOWANCE DETAILS and UPDATES
	 * @throws Exception
	 */
	/**
	 * @return String Type success
	 * @throws Exception
	 */
	public String save()throws Exception{
		String []  EmpNo = (String[]) request.getParameterValues("itEmpId");
		boolean result = false; 
		PAProcessModel model = new PAProcessModel(); 
		model.initiate(context, session);
		if(EmpNo!=null && EmpNo.length > 0) //if there is no any record
		   {
	 
			 	if(paPro.getPaProcessId().equals(""))
				{
						result = model.savePAProcess(paPro ,request);
						if(result)
						{
							addActionMessage(getMessage("save"));
							reset();
						}else
						{
							addActionMessage("Record can't Saved !");
						}
				}else
				{
					result = model.updatePAProcess(paPro ,request);
					if(result)
					{
						addActionMessage(getMessage("update"));
						reset();
					}else
					{
						addActionMessage("Record can't Updated !");
					}
				}
		   }else
		   {
			   if(paPro.getPaProcessId().equals(""))
				{
				   addActionMessage("There is no Record to save!");
				}else
				{
					addActionMessage("There is no Record to update!");
				}
			   reset();
		   }
		 
		model.terminate();
		return "success";
	}

	/**
	 * THIS METHOD FOR DELETING THE RECORD
	 */
	/**
	 * @return String Type success	
	 */
	public String delete()
	{
		PAProcessModel model = new PAProcessModel();
		model.initiate(context, session);
	 	boolean result = model.delete(paPro);
	 	if(result)
	 	{
	 		addActionMessage(getMessage("delete"));
	 		reset();
	 	}else
	 	{
	 		addActionMessage("Record can't Deleted !");
	 	}
		model.terminate();
		return "success";
	} 
	/**
	 * THIS METHOD FOR RESET ALL THE VARAIBLE
	 */
	/**
	 * @return String Type success	
	 */
	public String reset()
	{
		paPro.setPaProcessId("");
		paPro.setComponentId("");
		paPro.setComponentName("");
		paPro.setPeriod("");
		paPro.setFrmMonth("-1");
		paPro.setFrmYear("");
		paPro.setToMonth("-1");
		paPro.setToYear("");
		paPro.setDivisionId("");
		paPro.setDivisionName("");
		paPro.setEmpTypeId("");
		paPro.setEmpTypeName("");
		paPro.setPayBillId("");
		paPro.setPayBillName("");
		paPro.setBranchId("");
		paPro.setBranchName("");
		paPro.setDeptId("");
		paPro.setDeptName("");
		paPro.setSalFrmMonth("0");
		paPro.setSalFrmYear("");
		paPro.setEmpId("");
		paPro.setEmpName("");	
		paPro.setSalaryFlag("false");
		paPro.setHiddenChk("");
		paPro.setHidDisbType("");
		paPro.setPayMode("B");
		paPro.setProcessStatus("");
		paPro.setPaFromDate("");
		paPro.setPaToDate("");
		paPro.setDesgnName("");
		paPro.setDesgnId("");
		return "success";
	}	
	 
	/**
	 * THIS USED FOR GETTING THE DETAILS OF PROCESS
	 */
	/**
	 * @return String Type success	
	 */
	 public String callF9Details()
	 {
		    PAProcessModel model = new PAProcessModel();
			model.initiate(context, session);
		 	model.callF9Details(paPro,request);
		 	callFlag(); 
		 	callF9Compenent();
			model.terminate();
			return "success";
	 } 
	    /**
		 *This method is used for lock update the status as L
		 */
		/**
		 * @return String Type null	
		 */
	 public String Lock()
	 {
		    PAProcessModel model = new PAProcessModel();
			model.initiate(context, session);
		    model.callLock(paPro,request,response);  
			model.terminate(); 
			/* if(result)
			 	 addActionMessage("Periodic Allowance Process Lock Successfully ! ");
			 	else
			 		addActionMessage("Periodic Allowance Process Can't Lock !");*/
			 		
			return null;
	 }
	    /**
		 *This method is used for  unlock update the status as U 
		 */
		/**
		 * @return String Type null	
		 */
	 public String unLock()
	 {
		     PAProcessModel model = new PAProcessModel();
			 model.initiate(context, session);
			 model.callUnLock(paPro,request,response);  
			 model.terminate(); 
			/*if(result)
			 	 addActionMessage("Periodic Allowance Process UnLock Successfully ! ");
			 	else
			 		addActionMessage("Periodic Allowance Process Can't UnLock !");*/
			return null;
	 }
	    /**
		 *This method is used for set the component abbr.
		 */
		/**
		 * @return String Type null	
		 */
	 
	 public String callF9Compenent()
	 {
		 paPro.setCompAbbrPer(paPro.getCompAbbr().trim()+"(%)");
		 paPro.setCompAbbrAmtRs(paPro.getCompAbbr().trim()+" Amt.(Rs.)");
		 return "success";
	 }
		/**
		 * @This method is used for show the existing process and set the field value on jsp for selected process
		 */
		/**
		 * @return String Type null	
		 */
	   
	public String f9Search()
	{
		try
		{
			String query = "SELECT DISTINCT  TO_CHAR(ALLW_PROCESS_DATE,'DD-MM-YYYY'), TO_CHAR(ALLW_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(ALLW_TO_DATE,'DD-MM-YYYY') ,CREDIT_NAME,NVL(DIV_NAME,' ') "
						 + " ,NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),NVL(TYPE_NAME,' ') ,NVL(RANK_NAME,' '),NVL(PAYBILL_GROUP,' '), ALLW_CREDIT_HEAD, "
						 +"  ALLW_FINALIZE_FLAG, " 
						 +"	TO_CHAR(ALLW_PROCESS_DATE,'DD-MM-YYYY'), ALLW_APPRAISAL_RATING_FLAG, "
						 +"	ALLW_DIVISION , ALLW_DEPT,ALLW_BRANCH, " 
						 +"	ALLW_EMPTYPE,  ALLW_PAYBILL,  "
						 +"	ALLW_DESG, ALLW_EMP_ID,NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '), "
						 +"	ALLW_PAY_IN_SALARY,ALLW_PAY_MONTH, ALLW_PAY_YEAR ,HRMS_CREDIT_HEAD.CREDIT_ABBR ," 
						 + "  CASE WHEN CREDIT_PERIODICITY ='M' THEN 'Monthly'  "
						 +" WHEN CREDIT_PERIODICITY='Q' THEN 'Quarterly'  WHEN CREDIT_PERIODICITY='H' THEN 'Half Yearly' " 
						 +" WHEN CREDIT_PERIODICITY='A' THEN 'Annually'  END ,ALLW_PAY_MODE,ALLW_ID FROM HRMS_ALLOWANCE_HDR "
						 +"	LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD ) "
						 +"	LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_ALLOWANCE_HDR.ALLW_DIVISION) "
						 +"	LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_ALLOWANCE_HDR.ALLW_DEPT) "
						 +"	LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_ALLOWANCE_HDR.ALLW_BRANCH) "
						 +"	LEFT JOIN  HRMS_EMP_TYPE ON ( HRMS_EMP_TYPE.TYPE_ID = HRMS_ALLOWANCE_HDR.ALLW_EMPTYPE) "
						 +"	LEFT JOIN  HRMS_PAYBILL ON ( HRMS_PAYBILL.PAYBILL_ID = HRMS_ALLOWANCE_HDR.ALLW_PAYBILL) "
						 +"	LEFT JOIN  HRMS_RANK ON ( HRMS_RANK.RANK_ID = HRMS_ALLOWANCE_HDR.ALLW_DESG) "
						 +"	LEFT JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_ALLOWANCE_HDR.ALLW_EMP_ID) ";
						 if(paPro.getUserProfileDivision() !=null && paPro.getUserProfileDivision().length()>0)
								query+= " WHERE DIV_ID IN ("+paPro.getUserProfileDivision() +")"; 
						 query+="	ORDER BY ALLW_ID ";
			
			String[] headers = {getMessage("procdate"),getMessage("frmDate"), getMessage("toDate") ,getMessage("compName"),getMessage("division"),getMessage("branch"),getMessage("department")};

			String[] headerWidth = {"20","20", "20","35","35", "35","35"};

			String[] fieldNames = {"processDate","paFromDate","paToDate","componentName","divisionName","branchName","deptName","EmpTypeName","desgnName","payBillName","componentId","finaliseFlag",
					"processDate","hidDisbType"	,"divisionId","deptId","branchId","EmpTypeId",
					"payBillId","desgnId","paPro.empId","empName","hiddenChk","salFrmMonth","salFrmYear","compAbbr","period" ,"payMode","paProcessId"};

			int[] columnIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};

			String submitFlag = "true";

			String submitToMethod = "PAProcess_callF9Details.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			
			
			return "f9page";
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
   } 
	/**
	 * @This method is used for show the employee name and set employee field value on jsp for selected employee
	 */
	/**
	 * @return String Type null	
	 */
	
	public String f9Employee()
	{
		try
		{
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , EMP_ID "
				           +" FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(paPro);
			
			query +=" WHERE EMP_STATUS ='S'  ORDER BY EMP_ID ";
			String[] headers = {getMessage("employee.id"),getMessage("employee")};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"empToken","empName","paPro.empId"};

			int[] columnIndex = {0,1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
   } 
	/**
	 * @This method is used for show the branch name and set branch field value on jsp for selected branch
	 */
	/**
	 * @return String Type null	
	 */
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * @This method is used for show the division name and set division field value on jsp for selected division
	 */
	/**
	 * @return String Type null	
	 */
	public String f9Division()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			if(paPro.getUserProfileDivision() !=null && paPro.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+paPro.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME) ";
			 
			String[] headers = {getMessage("division") };

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @This method is used for show the department name and set department field value on jsp for selected department
	 */
	/**
	 * @return String Type null	
	 */
	
	public String f9Department()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @This method is used for show the Employee type and set Employee type field value on jsp for selected Employee type
	 */
	/**
	 * @return String Type null	
	 */	
	public String f9EmpType()
	{
		try
		{
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ORDER BY UPPER(TYPE_NAME) ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"EmpTypeName", "EmpTypeId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @This method is used for show the paybill group and set paybill field value on jsp for selected paybill
	 */
	 /**
	 * @return String Type null	
	 */	
	public String f9PayBill()
	{
		try
		{
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
			+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
		 	query +=getprofilePaybillQuery(paPro);
			
			String[] headers = {getMessage("pay.bill")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"payBillName", "payBillId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}		
	}
	/**
	 * @This method is used for show the Credit name and set Credit field value on jsp for selected Credit name
	 */
	 /**
	 * @return String Type null	
	 */	
	
	public String f9Component()
	{
		try
		{
			String query = "  SELECT CREDIT_NAME , CASE WHEN CREDIT_PERIODICITY ='M' THEN 'Monthly'  "
							+" WHEN CREDIT_PERIODICITY='Q' THEN 'Quarterly'  WHEN CREDIT_PERIODICITY='H' THEN 'Half Yearly' " 
							+" WHEN CREDIT_PERIODICITY='A' THEN 'Annually'  END ,CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_ABBR   "
							+" FROM  HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";

			String[] headers = {getMessage("compName"),getMessage("period")};

			String[] headerWidth = {"60","40"};

			String[] fieldNames = {"componentName","period", "componentId","compAbbr"};

			int[] columnIndex = {0,1,2,3};

			String submitFlag = "true";

			String submitToMethod = "PAProcess_callF9Compenent.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * @This method is used for show the Designation and set Designation value on jsp for selected Designation
	 */
	 /**
	 * @return String Type null	
	 */	
	public String f9Designation()
	{
		try
		{
			String query = " SELECT RANK_NAME ,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

			String[] headers = {getMessage("designation")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"desgnName", "desgnId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
}
