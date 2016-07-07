package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.Form16;
import org.paradyne.model.payroll.incometax.Form16Model;
import org.struts.lib.ParaActionSupport;

/**
 * @author Venkatesh
 *
 */
public class Form16Action extends ParaActionSupport {

	Form16 form16;

	/**
	 * @return the form16
	 */
	public Form16 getForm16() {
		return form16;
	}

	/**
	 * @param form16 the form16 to set
	 */
	public void setForm16(Form16 form16) {
		this.form16 = form16;
	}

	public Object getModel() {
		return form16;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		form16 = new Form16();
		form16.setMenuCode(288);
	}
	
	public void prepare_withLoginProfileDetails()throws Exception {
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysdate = formater.format(date);
		String[]split = sysdate.split("/");
		int year = Integer.parseInt(String.valueOf(split[2]));
		form16.setFromYear(String.valueOf(year));
		form16.setToYear(String.valueOf(year + 1));
	}

	public String report() throws Exception {
		Form16Model model = new Form16Model();
		model.initiate(context, session);
		model.getReport(form16, response);
		model.terminate();

		return null;
	}

	/*public String getEmpFather()throws Exception
	{
		Form16Model model=new Form16Model(); 
		model.initiate(context,session);
		model.getFatherRecord(form16,response);
		model.terminate();
		return SUCCESS;
	}*/

	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */

		logger.info("In f9 action===========1");
		String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ " NVL(RANK_NAME,' '),NVL(CENTER_ID||'-'||CENTER_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  	"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	"
				+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ " ORDER BY EMP_ID ";

		/*
		"SELECT EMP_ID,TO_CHAR(EMP_FNAME||'  '||EMP_LNAME),EMP_DEPT,TO_CHAR(DEPT_NAME),EMP_CENTER,TO_CHAR(CENTER_NAME)"
						+" FROM HRMS_EMP_OFFC "
						+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
						+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+"  ORDER BY EMP_ID ";	*/

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("designation"), getMessage("branch") };

		String[] headerWidth = { "10", "30", "30", "30" };
		logger.info("In f9 action===========2");

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "form16.empTokenNo", "form16.empName",
				"form16.rank", "form16.center", "form16.empID" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		logger.info("In f9 action===========3");

		String submitToMethod = "";

		logger.info("In f9 action===========4");

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*public String f9signAuthority() throws Exception {
	 *//**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	/*
			
			logger.info("In f9 action===========1");
			String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
	  					+" NVL(RANK_NAME,' '),EMP_ID "
						+" FROM HRMS_EMP_OFFC "
						+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " +
						 " WHERE EMP_STATUS='S' ";
			
	 *//**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */
	/* 
			String[] headers={"Employee Id","Employee Name","Designation"};
			
			String[] headerWidth={"15","30", "20"};
			
	 *//**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */
	/* 	
			
			String[] fieldNames={"form16.signAuthtoken","form16.signAuthName","form16.signAuthEmpDesg","form16.signAuthEmpId"};
			
	 *//**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */
	/* 
			int[] columnIndex={0,1,2,3};
			
	 *//**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	/*
			String submitFlag = "true";
			
	 *//**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	/*
			logger.info("In f9 action===========3");
			
			
			String submitToMethod="Form16_getEmpFather.action";
			
			logger.info("In f9 action===========4");
			
	 *//**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	/*
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			
			return "f9page";
		}*/
}
