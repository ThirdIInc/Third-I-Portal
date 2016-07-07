package org.struts.action.admin.srd;

import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.srd.OfficialDetail;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.OfficialDetailModel;

import com.lowagie.text.Image;

/**
 * @author radha OfficialDetailAction class to insert,update,Reset and view any
 *         employees Official details
 * 
 */
public class OfficialDetailAction extends org.struts.lib.ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OfficialDetailAction.class);
	OfficialDetail offDetail;
	String poolDir = "";
	String fileName = "";

	/**
	 * Method to view the existing Employee details.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "  SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ "	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') ,DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated','A','Absconding') as Status, EMP_ID FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
		query += getprofileQuery(offDetail);
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation"),
				getMessage("status") };
		String[] headerWidth = { "20", "30", "30", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken", "firstName", "centerName",
				"rankName", "status", "empCode" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "OfficialDetail_OfficialDetailRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Center
	 * 
	 * @return Center
	 * @throws Exception
	 */
	public String f9centeraction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER WHERE IS_ACTIVE='Y'"
				+ " ORDER BY  CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		String[] headerWidth = { "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "centerCode", "centerName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		offDetail.setMasterMenuCode(31);

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the department. *
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department.code"),
				getMessage("department") };

		String[] headerWidth = { "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptCode", "deptName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		offDetail.setMasterMenuCode(23);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE IS_ACTIVE='Y'";
		if (offDetail.getUserProfileDivision() != null
				&& offDetail.getUserProfileDivision().length() > 0)
			query += " AND DIV_ID IN (" + offDetail.getUserProfileDivision()
					+ ")";
		query += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		offDetail.setMasterMenuCode(42);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9gradeaction() throws Exception {

		String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE WHERE CADRE_IS_ACTIVE='Y' "
				+ " ORDER BY CADRE_ID ";

		String[] headers = { getMessage("grade.code"), getMessage("grade") };

		String[] headerWidth = { "20", "80" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		String[] fieldNames = { "cadreCode", "cadreName" };
		offDetail.setMasterMenuCode(19);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Pay bill group.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9payBillaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL "
				+ " ORDER BY  PAYBILL_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("pay.bill.no"), getMessage("pay.bill") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "payBillId", "payBillName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		offDetail.setMasterMenuCode(214);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the designation of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9rankaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM  HRMS_RANK WHERE IS_ACTIVE='Y'"
				+ " ORDER BY  RANK_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "rankCode", "rankName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		offDetail.setMasterMenuCode(26);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Reporting Person of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9reporingToaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " WHERE EMP_STATUS	='S' ";
		System.out.println("offDetail.getEmpCode()" + offDetail.getEmpCode());
		try {
			if (!(offDetail.getEmpCode().equals("") || offDetail.getEmpCode()
					.equals("null"))) {
				query += "  AND EMP_ID NOT IN (SELECT HRMS_EMP_OFFC.EMP_ID "
						+ " FROM HRMS_EMP_OFFC "
						+ " WHERE HRMS_EMP_OFFC.EMP_ID ="
						+ offDetail.getEmpCode() + ")";
				// updated by prajakta for adding new record function
				/*
				 * if(offDetail.isNewFlag()){ query += " CONNECT BY PRIOR EMP_ID =
				 * "+offDetail.getUserEmpId()+" )"; } else{ query += " CONNECT
				 * BY PRIOR EMP_ID = "+offDetail.getReportingID()+" )"; }
				 */
				// updated by prajakta ends
						//+ " WHERE HRMS_EMP_OFFC.EMP_ID ="+offDetail.getEmpCode()+")";
				//updated by prajakta for adding new record function
				/*if(offDetail.isNewFlag()){
					query += " CONNECT BY PRIOR EMP_ID = "+offDetail.getUserEmpId()+" )";
				}
				else{
					if(offDetail.getReportingID().equals("")|| offDetail.getReportingID()==null || offDetail.getReportingID().equals("null")){
						query += " CONNECT BY PRIOR EMP_ID = "+offDetail.getUserEmpId()+" )";
					}else{
					query += " CONNECT BY PRIOR EMP_ID = "+offDetail.getReportingID()+" )";
					}
				}*/
				//updated by prajakta ends
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		query += "  ORDER BY EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "reportingToken", "reportingTo", "reportingID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Shift of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9shiftaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT "
				+ " ORDER BY  SHIFT_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("shift.code"), getMessage("shift") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "shiftCode", "shiftType" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		offDetail.setMasterMenuCode(93);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select Title
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9titleaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT TITLE_CODE,TITLE_NAME FROM  HRMS_TITLE WHERE IS_ACTIVE='Y'"
				+ " ORDER BY  TITLE_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("title.code"), getMessage("title") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "title", "titleName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		offDetail.setMasterMenuCode(206);
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the trade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9tradeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT TRADE_CODE,TRADE_NAME FROM  HRMS_TRADE   "
				+ " ORDER BY TRADE_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("trade.code"), getMessage("trade") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "tradeCode", "tradeName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		offDetail.setMasterMenuCode(28);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * To select Employee Type
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9typeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TYPE_ID,TO_CHAR(TYPE_NAME) FROM  HRMS_EMP_TYPE  "
				+ " ORDER BY  TYPE_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.type.code"),
				getMessage("employee.type") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "typeCode", "typeName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		offDetail.setMasterMenuCode(41);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to generate Employee Token
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String GenerateToken() throws Exception {
		OfficialDetailModel model = new OfficialDetailModel();
		model.initiate(context, session);
		model.getToken(offDetail);
		model.terminate();
		return "success";
	}

	public Object getModel() {
		return offDetail;
	}

	/**
	 * @return the offDetail
	 */
	public OfficialDetail getOffDetail() {
		return offDetail;
	}

	/**
	 * Method to set the value of each field after Search.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String OfficialDetailRecord() throws Exception {

		OfficialDetailModel model = new OfficialDetailModel();

		model.initiate(context, session);

		boolean f = offDetail.isEditFlag();

		System.out.println("edit value" + f);
		offDetail = model.getRecord(offDetail);
		String token = offDetail.getEmpToken();
		String photo = offDetail.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		String img = model.getServletContext().getRealPath("//")
				+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		offDetail.setFlag("false");
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}// end of catch
		request.setAttribute("profilePhoto", photo);

		if (!offDetail.isGeneralFlag()) {
			HttpSession session = request.getSession();
			session.setAttribute("srdEmpCode", offDetail.getEmpCode());
		}// end of if

		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map1 = dmu.getGenderXml("gender");
		offDetail.setGendermap(map1);
		// TreeMap map2 = dmu.getGenderXml("status");
		// offDetail.setStatusmap(map2);
		dmu.terminate();
		getMyProfileEmpName();
		model.terminate();

		// getNavigationPanel(3);
		return "success";
	}// end of OfficialDetailRecord

	/**
	 * Method to create instance of bean.
	 */
	public void prepare_local() throws Exception {

		offDetail = new OfficialDetail();
		offDetail.setMenuCode(43);
	}

	/**
	 * Method to get the login user details while form loading.
	 * 
	 * @throws Exception
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		showHrm();
		offDetail.setFlag("true");
		offDetail.setNewFlag(false);
		if (offDetail.isGeneralFlag()) {
			offDetail.setIsNotGeneralUser("false");
			offDetail.setEmpCode(offDetail.getUserEmpId());
			OfficialDetailRecord();
		}// end of if
		else {
			offDetail.setIsNotGeneralUser("true");
			String str = (String) request.getSession().getAttribute(
					"srdEmpCode");
			if (str == null || str.equals("")) {
				str = offDetail.getUserEmpId();
			}
			offDetail.setEmpCode(str);
			OfficialDetailRecord();
		}// end of else
		OfficialDetailModel model = new OfficialDetailModel();
		model.initiate(context, session);

		String token = offDetail.getEmpToken();
		String img = model.getServletContext().getRealPath("//")
		// + "/pages/images/employee/" + token + ".jpg";
				+ "/pages/images/employee/12336L.jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			token = "NoImage";
			e.printStackTrace();
		}// end of catch

		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map1 = dmu.getGenderXml("gender");
		offDetail.setGendermap(map1);
		// TreeMap map2 = dmu.getGenderXml("status");
		// offDetail.setStatusmap(map2);
		dmu.terminate();
		request.setAttribute("tokenNo", token);
		/*
		 * Object[][] d1DataFilters = model.getFilters(offDetail);
		 *  // Set the filters on a page via been
		 * //offDetail.setDivD1Flag(offDetail.getDiv)
		 * offDetail.setDeptCode(String.valueOf(d1DataFilters[0][0]));
		 * offDetail.setSsnFlag(Boolean.parseBoolean(String.valueOf(d1DataFilters[0][1])));
		 * offDetail.setSinFlag(Boolean.parseBoolean(String.valueOf(d1DataFilters[0][2])));
		 * offDetail.setDeptNoFlag(Boolean.parseBoolean(String.valueOf(d1DataFilters[0][3])));
		 * offDetail.setRegionFlag(Boolean.parseBoolean(String.valueOf(d1DataFilters[0][4])));
		 * offDetail.setExeFlag(Boolean.parseBoolean(String.valueOf(d1DataFilters[0][5])));
		 * offDetail.setDivD1Flag(Boolean.parseBoolean(String.valueOf(d1DataFilters[0][6])));
		 * 
		 * request.setAttribute("checkValue",String.valueOf(d1DataFilters[0][6]));
		 * 
		 * for(int v=0; v< d1DataFilters.length; v++){ for(int w=0;w<
		 * d1DataFilters[0].length;w++){
		 * System.out.println("d1DataFilters["+v+"]["+w+"]"+d1DataFilters[v][w]); } }
		 */

		getMyProfileEmpName();
	}

	/**
	 * Method to resets the field
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			// if(!(offDetail.isGeneralFlag())){
			offDetail.setEmpCode("");
			offDetail.setProfileEmpName("");
			offDetail.setEmpToken("");
			offDetail.setRankName("");
			offDetail.setCenterName("");
			offDetail.setFirstName("");
			offDetail.setTitle("");
			offDetail.setTitleName("");
			offDetail.setMiddleName("");
			offDetail.setLastName("");
			offDetail.setGender("");
			offDetail.setBirthDate("");
			offDetail.setDeptName("");
			offDetail.setDivName("");
			offDetail.setCenterCode("");
			offDetail.setCenterName("");
			offDetail.setRankCode("");
			offDetail.setRankName("");
			offDetail.setTradeCode("");
			offDetail.setTradeName("");
			offDetail.setTypeCode("");
			offDetail.setTypeName("");
			offDetail.setDisciplineCode("");
			offDetail.setDisciplineName("");
			offDetail.setCadreCode("");
			offDetail.setCadreName("");
			offDetail.setShiftCode("");
			offDetail.setShiftType("");
			offDetail.setStatus("");
			offDetail.setRegularDate("");
			offDetail.setLeaveDate("");
			offDetail.setReportingTo("");
			offDetail.setReportingID("");
			offDetail.setBornFlag("");
			offDetail.setReportingToken("");
			offDetail.setPayBillId("");
			offDetail.setPayBillName("");
			offDetail.setGrade("");
			offDetail.setDesgName("");
			offDetail.setDesgCode("");
			offDetail.setSeniDate("");
			offDetail.setLastIncrDt("");
			offDetail.setUploadFileName("");
			offDetail.setDivCode("");
			offDetail.setDeptCode("");
			offDetail.setRankCode("");
			offDetail.setConfirmDate("");
			offDetail.setServiceTenureValue("");
			offDetail.setGroupjoinDate("");
			offDetail.setRoleName("");
			offDetail.setEmpName("");
			offDetail.setDeptCode("");
			String photo = "NoImage.jpg";
			request.removeAttribute("photo");
			request.setAttribute("profilePhoto", photo);
			// }

		}// end of try
		catch (Exception e) {
			e.printStackTrace();
		}// end of catch
		// offDetail.setEnableAll("N");
		offDetail.setEditFlag(true);
		getNavigationPanel(2);
		getMyProfileEmpName();
		return SUCCESS;

	}

	/**
	 * Method to save the Employee details.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		boolean result = false;
		try {
			OfficialDetailModel model = new OfficialDetailModel();
			model.initiate(context, session);
			String str = null;
			if (offDetail.getEmpCode() == null
					|| offDetail.getEmpCode().equals("")) {
				result = model.addOffDtl(offDetail, request);
				if (result) {
					addActionMessage("Record Saved Successfully");
					offDetail.setEditFlag(false);
					OfficialDetailRecord();

				} else {
					addActionMessage("Employee Id already exists");
					offDetail.setEditFlag(true);
					offDetail.setServiceTenureValue("");
				}

			} else {
				result = model.modOffDtl(offDetail, request);
				if (result) {
					addActionMessage("Record updated successfully");
					offDetail.setEditFlag(false);
					OfficialDetailRecord();
				} else {
					addActionMessage("Employee Id already exists");
					offDetail.setEditFlag(true);
					offDetail.setServiceTenureValue("");
				}

			}
			getMyProfileEmpName();
			model.terminate();
			// addActionMessage(str);

		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		// offDetail.setEditFlag(false);

		return "success";
	}

	/**
	 * Method to add record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String add() throws Exception {
		OfficialDetailModel model = new OfficialDetailModel();
		model.initiate(context, session);
		offDetail.setEditFlag(true);
		offDetail.setNewFlag(true);
		reset();
		getMyProfileEmpName();
		model.terminate();
		return "success";
	}

	/**
	 * @param offDetail
	 *            the offDetail to set
	 */
	public void setOffDetail(OfficialDetail offDetail) {
		this.offDetail = offDetail;
	}

	/**
	 * Method to display Menu name
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String showHrm() throws Exception {
		OfficialDetailModel model = new OfficialDetailModel();
		model.initiate(context, session);
		String code = offDetail.getMultipleProfileCode();
		model.showHRM(code);
		model.terminate();
		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	@Override
	public String input() throws Exception {
		// updated by Anantha lakshmi
		String strEmp = "";
		try {
			strEmp = (String) request.getSession().getAttribute("srdEmpCode");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (strEmp != null && !strEmp.equals("")) {
			offDetail.setEmpCode(strEmp);
		}
		OfficialDetailRecord();
		offDetail.setEditFlag(false);
		getMyProfileEmpName();
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String addNew() {
		// getNavigationPanel(2);
		return SUCCESS;
	}

	public String cancel() {
		try {
			OfficialDetailModel model = new OfficialDetailModel();
			model.initiate(context, session);
			offDetail.setEditFlag(false);
			if (offDetail.isGeneralFlag()) {
				offDetail.setIsNotGeneralUser("false");
				offDetail.setEmpCode(offDetail.getUserEmpId());
				OfficialDetailRecord();
			}// end of if
			else {
				offDetail.setIsNotGeneralUser("true");
				String str = (String) request.getSession().getAttribute(
						"srdEmpCode");
				if (str == null || str.equals("")) {
					str = offDetail.getUserEmpId();
				}
				offDetail.setEmpCode(str);
			}
			OfficialDetailRecord();
			getMyProfileEmpName();
			model.terminate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";

	}

	// End Added by janisha for D1 Integration

	public String employeeDetails() {
		try {
			OfficialDetailModel model = new OfficialDetailModel();
			model.initiate(context, session);
			OfficialDetailRecord();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map1 = dmu.getGenderXml("gender");
		offDetail.setGendermap(map1);
		// TreeMap map2 = dmu.getGenderXml("status");
		// offDetail.setStatusmap(map2);
		dmu.terminate();
		getMyProfileEmpName();
		offDetail.setEditFlag(true);
		return SUCCESS;
	}

	public void getMyProfileEmpName() {
		OfficialDetailModel model = new OfficialDetailModel();
		model.initiate(context, session);
		model.getProfileEmpName(offDetail);
		model.terminate();

	}

	public String update() {
		try {
			String uploadImage = request.getParameter("userImageFileName");
			System.out.println("Image..........." + uploadImage);
			OfficialDetailModel model = new OfficialDetailModel();
			model.initiate(context, session);
			String query = "UPDATE HRMS_EMP_OFFC SET EMP_PHOTO='" + uploadImage
					+ "' WHERE EMP_ID=" + offDetail.getEmpCode();
			boolean result = model.getSqlModel().singleExecute(query);
			OfficialDetailRecord();
			model.terminate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "input";
	}
}