package org.struts.action.payroll.salary;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.salary.PAProcessReport;
import org.paradyne.model.payroll.salary.PAProcessModel;
import org.paradyne.model.payroll.salary.PAProcessReportModel;

/**
 * @author Hemant Nagam
 * dated : 14/08/2008
 */
public class PAProcessReportAction extends ParaActionSupport{

	PAProcessReport paRpt;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		paRpt=new PAProcessReport();
		paRpt.setMenuCode(637);
	}

	public PAProcessReport getPaRpt() {
		return paRpt;
	}

	public void setPaRpt(PAProcessReport paRpt) {
		this.paRpt = paRpt;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return paRpt;
	}

	/**
	 * THIS METHOD GENERATES THE PAPROCESS REPORT
	 * @return String type null
	 * @throws Exception
	 */
	public String  report()throws Exception{
		
		PAProcessReportModel model =new PAProcessReportModel();
		model.initiate(context, session);
		model.report(paRpt,response);
		model.terminate();
		
		return null;		
	}
	/**
	 * THIS METHOD CLEAR ALL THE FIELD VALUE ON JSP.
	 * @return String type success
	 * @throws Exception
	 */
	
	public String reset()throws Exception{
		
		paRpt.setEmpName("");
		paRpt.setEmpId("");
		paRpt.setEmpCode("");
		paRpt.setEmpTypeId("");
		paRpt.setEmpType("");
		paRpt.setDesignationId("");
		paRpt.setDesignation("");
		paRpt.setDivisionId("");
		paRpt.setDivision("");
		paRpt.setBranchId("");
		paRpt.setBranch("");
		paRpt.setDepartmentId("");
		paRpt.setDepartment("");
		paRpt.setComponentId("");
		paRpt.setComponent("");
		paRpt.setPaDate("");
		paRpt.setPaId("");
		paRpt.setRptType("-1");
		paRpt.setPayMode("-1");
		return "success";
		
	}
	
	/**
	 * THIS METHOD SHOW THE EXISTING PROCESSES 
	 */
	/**
	 * @return String Type NULL
	 */
	
	public String f9PaDate(){
		
		try
		{
			/*String query = " SELECT TO_CHAR(ALLW_PROCESS_DATE,'DD-MM-YYYY'),DECODE(ALLW_MONTH_FROM,'1','JAN','2','FEB','3','MAR','4','APR','5','MAY','6',"
						  +" 'JUNE','7','JUL','8','AUG','9','SEP' ,'10','OCT','11','NOV','12','DEC')||' '||ALLW_YEAR_FROM||' - '||"
						  +" DECODE(ALLW_MONTH_TO,'1','JAN','2','FEB','3','MAR','4','APR','5','MAY','6','JUNE','7','JUL','8','AUG',"
						  +" '9','SEP' ,'10','OCT','11','NOV','12','DEC')||' '||ALLW_YEAR_TO"
						  +" AS PADATE,CREDIT_NAME,ALLW_ID FROM HRMS_ALLOWANCE_HDR"
						  +" INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD)";
			*/
			String query="SELECT TO_CHAR(ALLW_PROCESS_DATE,'DD-MM-YYYY'),"
				 		+" DECODE(TO_CHAR(ALLW_FROM_DATE,'MM'),'01','JAN','02','FEB','03','MAR','04','APR','05','MAY','06', 'JUNE','07',"
				 		+" 'JUL','08','AUG','09','SEP' ,'10','OCT','11','NOV','12','DEC')||' '||TO_CHAR(ALLW_FROM_DATE,'YYYY')"
				 		+" ||' - '|| DECODE(TO_CHAR(ALLW_TO_DATE,'MM'),'01','JAN','02','FEB','03','MAR','04','APR','05','MAY','06',"
				 		+" 'JUNE','07','JUL','08','AUG', '09','SEP' ,'10','OCT','11','NOV','12','DEC')||' '||TO_CHAR(ALLW_TO_DATE,'YYYY')" 
				 		+" AS PADATE,CREDIT_NAME,DIV_NAME,ALLW_DIVISION,ALLW_ID ,CREDIT_CODE FROM HRMS_ALLOWANCE_HDR" 
				 		+" INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD)"
				 		+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ALLOWANCE_HDR.ALLW_DIVISION)";
			
			String[] headers = {getMessage("process.date"),getMessage("allowance.duration"),getMessage("component"),getMessage("division")};

			String[] headerWidth = {"10","10","15","15"};
			

			String[] fieldNames = {"paDate","paDate","component","division","divisionId","paId","componentId"};
			 

			int[] columnIndex = {0,1,2,3,4,5,6};

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
	 * THIS METHOD SHOW THE EMPLOYEE NAME
	 */
	/**
	 * @return String Type NULL
	 */
	
	public String f9Emp()
	{
		try
		{
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,DIV_NAME, "
						 +"	CENTER_NAME,DEPT_NAME,RANK_NAME,TYPE_NAME,EMP_ID,"  
						 +"  DIV_ID,CENTER_ID,DEPT_ID,RANK_ID,TYPE_ID FROM HRMS_EMP_OFFC "
						 +"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
						 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
						 +"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
						 +" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV )"
						 +"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
						 +" INNER JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE ) ";
			
			query += getprofileQuery(paRpt);
			
			query +="	AND EMP_STATUS ='S'  ORDER BY EMP_ID ";
			
			String[] headers = {getMessage("employee.id"),getMessage("employee"),getMessage("division")};

			String[] headerWidth = {"15","50","35"};

			String[] fieldNames = {"empCode","empName","division","branch","department","designation","empType",
					           "empId","divisionId","branchId","departmentId","designationId","empTypeId"};
			 
			int[] columnIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12};

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
	 * THIS METHOD SHOW THE EMPLOYEE TYPE
	 */
	/**
	 * @return String Type NULL
	 */

	public String f9EmpType()
	{
		try
		{
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ORDER BY UPPER(TYPE_NAME) ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"empType", "empTypeId"};  

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
	 * THIS METHOD SHOW THE BRANCH NAME
	 */
	/**
	 * @return String Type NULL
	 */
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};  

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branch", "branchId"};

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
	 * THIS METHOD SHOW THE DIVISION NAME
	 */
	/**
	 * @return String Type NULL
	 */
	
	public String f9Division()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION  ";
			
			if(paRpt.getUserProfileDivision() !=null && paRpt.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+paRpt.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME) ";
			
			

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"division", "divisionId"};

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
	 * THIS METHOD SHOW THE DEPARTMENT NAME
	 */
	/**
	 * @return String Type NULL
	 */
	
	public String f9Department()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")}; 

			String[] headerWidth = {"100"};

			String[] fieldNames = {"department", "departmentId"};

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
	
	
	
	/*public String f9PayBill()
	{
		try
		{
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
			+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
		 	query +=getprofilePaybillQuery(paPro);
			
			String[] headers = {"PAY BILL NAME", "PAY BILL NO"};

			String[] headerWidth = {"80", "20"};

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
	}*/
	
	/**
	 * THIS METHOD SHOW THE SALARY COMPONENET.
	 */
	/**
	 * @return String Type NULL
	 */
	
	public String f9Component()
	{
		try
		{
			String query = "  SELECT CREDIT_NAME ,CREDIT_CODE, CASE WHEN CREDIT_PERIODICITY ='M' THEN 'Monthly'  "
							+" WHEN CREDIT_PERIODICITY='Q' THEN 'Quarterly'  WHEN CREDIT_PERIODICITY='H' THEN 'Half Yearly' " 
							+" WHEN CREDIT_PERIODICITY='A' THEN 'Annually'  END ,HRMS_CREDIT_HEAD.CREDIT_ABBR   "
							+" FROM  HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";

			String[] headers = {getMessage("component")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"component", "componentId"};

			int[] columnIndex = {0,1};

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
	 * THIS METHOD SHOW THE DESIGNATION NAME
	 */
	/**
	 * @return String Type NULL
	 */
	
	public String f9Designation()
	{
		try
		{
			String query = " SELECT RANK_NAME ,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

			String[] headers = {getMessage("designation")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"designation", "designationId"};

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
