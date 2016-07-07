package org.struts.action.PAS;

import java.io.IOException;
import java.io.PrintWriter;
import org.paradyne.bean.PAS.ScoreBalancer;
import org.paradyne.model.PAS.ApprFormSectionModel;
import org.paradyne.model.PAS.ScoreBalancerModel;
import org.struts.lib.ParaActionSupport;

public class ScoreBalancerAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ScoreBalancerAction.class);
	

	ScoreBalancer scoreBalancer;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		scoreBalancer = new ScoreBalancer();
		scoreBalancer.setMenuCode(761);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return scoreBalancer;
	}

	public ScoreBalancer getScoreBalancer() {
		return scoreBalancer;
	}

	public void setScoreBalancer(ScoreBalancer scoreBalancer) {
		this.scoreBalancer = scoreBalancer;
	}

	public String input() throws Exception {
		getNavigationPanel(1);
		scoreBalancer.setEnableAll("Y");
		request.setAttribute("abc", 0);
		request.setAttribute("xyz", 0);
		return INPUT;
	}
	public String paging(){
		getNavigationPanel(1);
		ScoreBalancerModel model = new ScoreBalancerModel();
		model.initiate(context, session);
		model.getEmpList(scoreBalancer,request);
		//model.getEmpList1(scoreBalancer,request);
		model.terminate();
		return SUCCESS;
	}
	
	public String f9SelectAppraisal() throws Exception {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DISTINCT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_SCORE.APPR_ID, "
				+ "'','','','','','','','' "
				+ " FROM PAS_APPR_SCORE "
				+ " INNER JOIN PAS_APPR_CALENDAR ON (PAS_APPR_SCORE.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) "
				+ " ORDER BY PAS_APPR_SCORE.APPR_ID ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("appraisal.code"), "Start Date", "End Date" };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "apprCode", "startDate", "endDate", "apprId","divCode","divName","branchCode","branchName","deptCode","deptNameUp","apprEmpCode","apprEmpName" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ScoreBalancer_retrieveEmployee.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Div()
	{
		try
		{
			String query = " SELECT  DISTINCT DIV_NAME,DIV_ID FROM HRMS_DIVISION "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+scoreBalancer.getApprId() 
				+" ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "true";

			String submitToMethod = "ScoreBalancer_retrieveEmployee.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT  DISTINCT CENTER_NAME,CENTER_ID FROM HRMS_CENTER " 
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+scoreBalancer.getApprId() 
				+" ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "true";

			String submitToMethod = "ScoreBalancer_retrieveEmployee.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	/**
	 * Popup window contains list of all departments
	**/
	/**
	 * @return String f9page
	**/
	public String f9Dept()
	{
		try
		{
			String query = " SELECT DISTINCT DEPT_NAME,DEPT_ID FROM  HRMS_DEPT "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+scoreBalancer.getApprId() 
				+" ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptNameUp", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "true";

			String submitToMethod = "ScoreBalancer_retrieveEmployee.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Dept
		
	public String f9Employee()throws Exception
	{
		String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME,APPR_APPRAISER_CODE FROM PAS_APPR_APPRAISER " 
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE "
							+" WHERE APPR_ID = "+scoreBalancer.getApprId()+" AND APPR_APPRAISER_CODE IS NOT NULL "
							+" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME)";  
		
		String[] headers = {getMessage("appraiser")};
		String[] headerWidth = { "100" };
		String[] fieldNames = {"apprEmpName", "apprEmpCode" };
		int[] columnIndex = { 0,1 };
		String submitFlag = "true";
		String submitToMethod = "ScoreBalancer_retrieveEmployee.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	public String retrieveEmployee() {
		getNavigationPanel(1);
		ScoreBalancerModel model = new ScoreBalancerModel();
		model.initiate(context, session);
		scoreBalancer.setMyPage("");
		scoreBalancer.setShow("");
		model.getEmpList(scoreBalancer,request);
		
		//model.getEmpList1(scoreBalancer,request);
		model.terminate();
		return SUCCESS;
	}
	
	/* method name : callPage
	 * purpose     : to displays the records in the form
	 * return type : String
	 * parameter   : none
	 */

	public String callPage() throws Exception {
		getNavigationPanel(1);
		ScoreBalancerModel model = new ScoreBalancerModel();
		model.initiate(context, session);
		model.getEmpList1(scoreBalancer,request);
		model.terminate();
		return SUCCESS;

	}
	
	public String save(){
		boolean result= false;
		ScoreBalancerModel model = new ScoreBalancerModel();
		model.initiate(context, session);
		
		Object []empCode = request.getParameterValues("empId");
		Object []operand = request.getParameterValues("hoprand");
		Object []adjust = request.getParameterValues("adjustFactor");
		Object []finalScore = request.getParameterValues("apprFinalScore");
		Object []templateCode = request.getParameterValues("templateCode");
		
		String apprCode= scoreBalancer.getApprId();
		logger.info("empcodeeeeeeeeeeee"+empCode.length);
		logger.info("operandeeeeeeeeeeee"+operand.length);
		logger.info("adjusteeeeeeeeeeee"+adjust.length);
		logger.info("finalScoreeeeeeeeeeeee"+finalScore.length);
		try {
			result = model.save(empCode,operand,adjust,finalScore,apprCode,templateCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(result){
			addActionMessage(getMessage("update"));
		}else{
			addActionMessage(getMessage("update.error"));
		}
		getNavigationPanel(1);
		model.getEmpList(scoreBalancer,request);
		//model.getEmpList(scoreBalancer);
		model.terminate();
		return SUCCESS;
	}
	
	public String finalizeEmployees()
	{
		String chkEmp[]=request.getParameterValues("chkEmp");
		String apprCode=request.getParameter("apprId");
		
		Object[][] updateObj = new Object[chkEmp.length][8];
		for (int i = 0; i < updateObj.length; i++) {
			chkEmp[i]=chkEmp[i].replace("P", "+");
			chkEmp[i]=chkEmp[i].replace("M", "-");
			String temp[] = chkEmp[i].split("&");
			updateObj[i][0] = temp[0];
			updateObj[i][1] = temp[1];
			updateObj[i][2] = temp[2];
			updateObj[i][3] = temp[3];
			updateObj[i][4] = "";
			updateObj[i][5] = "";
			updateObj[i][6] = temp[4];
			updateObj[i][7] = apprCode;
			
		}
		ScoreBalancerModel model = new ScoreBalancerModel();
		model.initiate(context, session);
		model.finalizeEmpScore(updateObj);
		model.terminate();
		return null;
	}
	
	public void recalEmployees()
	{
		try {
			String chkEmp[]=request.getParameterValues("chkEmp");
			String apprCode=request.getParameter("apprId");
			ScoreBalancerModel modelScore = new ScoreBalancerModel();
			modelScore.initiate(context, session);
			
			ApprFormSectionModel model = new ApprFormSectionModel();
			model.initiate(context, session);
			String message="";
			for (int i = 0; i < chkEmp.length; i++) {
				String temp[] = chkEmp[i].split("&");
				logger.info("template --"+String.valueOf(temp[5]));
				logger.info("employee --"+String.valueOf(temp[4]));
				model.ratingCalculation(apprCode, String.valueOf(temp[5]),String.valueOf(temp[4]));
				message += modelScore.getEmployeeScore(apprCode, String.valueOf(temp[4]));
				message += String.valueOf(temp[6])+"@";
			}
			model.terminate();
			modelScore.terminate();
			// set message in a response
			response.setContentType("text/html");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(message);
			printWriter.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	// public String reset(){
	// try{
	// input();
	// }catch(Exception e){
	//			
	// }
	// getNavigationPanel(1);
	// return INPUT;
	//	}
}
