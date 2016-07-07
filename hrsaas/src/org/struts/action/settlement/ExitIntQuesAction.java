package org.struts.action.settlement;

import java.util.Date;
import org.paradyne.bean.settlement.ExitIntQues;
import org.paradyne.model.settlement.ExitIntQuesModel;
import org.struts.lib.ParaActionSupport;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * @author Rita
 * @since 29/11/2007
 */
public class ExitIntQuesAction extends ParaActionSupport {

	ExitIntQues intques;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		intques = new ExitIntQues();
		intques.setMenuCode(369);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		ExitIntQuesModel model = new ExitIntQuesModel();
		model.initiate(context, session);
		getRecord();
		model.terminate();

	}

	public Object getModel() {
		return intques;
	}

	public ExitIntQues getIntques() {
		return intques;
	}

	public void setIntques(ExitIntQues intques) {
		this.intques = intques;
	}

	/**
	 * Method to clear the form
	 * 
	 * @return String
	 *
	 */
	public String reset() {
		intques.setEmpId("");
		intques.setEmpName("");
		intques.setEmpToken("");
		intques.setExitCode("");
		intques.setBranch("");
		intques.setDesignation("");
		intques.setGrade("");
		intques.setJoinDate("");
		intques.setLeaveDate("");
		intques.setComment("");
		intques.setExIntcode("");
		intques.setFlag("false");
		intques.setResignDate("");
		intques.setSepDate("");
		intques.setQuesDtlflag("false");
		intques.setQuedetails("0");
		intques.setResignCode("");
		intques.setResignId("");
		intques.setExIntcode("");
		intques.setQuestion("");
		intques.setQuestionCode("");
		intques.setQuestionValue("");
		intques.setQuestion("");
		intques.setQuedetails("");
		intques.setComment("");
		intques.setRating(null);
		intques.setRatingCode(null);
		return SUCCESS;
	}

	/**
	 * Method to add new record 
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() {
		try {
			ExitIntQuesModel model = new ExitIntQuesModel();
			model.initiate(context, session);
			String code = intques.getExIntcode();
			String[] quesCode = (String[]) request
					.getParameterValues("questionCode");
			String[] rating = (String[]) request.getParameterValues("rating");
			boolean res = false;
			String[] comment = (String[]) request.getParameterValues("comment");
			if (intques.getExIntcode() == null
					|| intques.getExIntcode().equals("null")
					|| (intques.getExIntcode().equals(""))) {
				res = model.saveExitCode(intques, quesCode, rating, comment);
				if (res) {
					addActionMessage("Record Saved Successfully");
					reset();
				} else {
					addActionMessage("Record can't be Saved");
					reset();
				}
			} else {
				String query = " UPDATE HRMS_EXIT_HDR SET EXIT_DATE=TO_DATE('"+intques.getLeaveDate()+"','DD-MM-YYYY') "
							 + " WHERE RESIGN_CODE = "+code;
				res = model.getSqlModel().singleExecute(query);
				if(res){
					model.updateDtl(intques, code, quesCode, comment, rating);
					addActionMessage("Record Updated Successfully");
					reset();
				}else {
					addActionMessage("Record cannot be updated");
					reset();
				}
			}
			model.terminate();
			return "success";
		} catch (Exception e) {
			logger.error("Exception in save() method	:"+e);
			return "";
		}
	}

	/*
	 * public String saveDtl() {logger.info("in save dtl function"); try {
	 * 
	 * 
	 * ExitIntQuesModel model= new ExitIntQuesModel();
	 * model.initiate(context,session);
	 * 
	 * String code= intques.getCode(); String ec=intques.getEmpId();
	 *  // String[] exCode=(String[])request.getParameterValues("exitCode");
	 * String[] quesCode=(String[])request.getParameterValues("questionCode");
	 * String[] rating = (String[])request.getParameterValues("rating");
	 * String[] comment=(String[])request.getParameterValues("comment"); for
	 * (int i = 0; i < rating.length; i++) {
	 * 
	 *  // logger.info("ex code======================"+exCode[i]);
	 * logger.info("qqqqqqqqqccccccccccc======================"+quesCode[i]);
	 * logger.info("ratingrating======================"+rating[i]);
	 * 
	 * logger.info("ques comment======================"+comment[i]); }
	 * 
	 * 
	 * model.saveQdtl(quesCode,comment,rating);
	 * 
	 * model.terminate(); return "success"; } catch (Exception e) {  return ""; } }
	 */

	/**
	 * Method to get record before saving 
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getRecord() {
		try {
			ExitIntQuesModel model = new ExitIntQuesModel();
			model.initiate(context, session);
			Date toDate = new Date();
			SimpleDateFormat today = new SimpleDateFormat("dd-MM-yyyy");
			String day = today.format(toDate);
			intques.setLeaveDate(day);
			model.getDisplay(intques);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in getRecord() method	:"+e);
			return ERROR;
		}
	}

	/**
	 * Method to get saved records
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getComment() {
		try {
			ExitIntQuesModel model = new ExitIntQuesModel();
			model.initiate(context, session);
			model.getComment(intques);
			model.terminate();

			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in getComment() method	:"+e);
			return ERROR;
		}
	}

	/**
	 * Method call on f9 action
	 * 
	 * @return String
	 * 
	 */
	public String getexIntApp() {

		ExitIntQuesModel model = new ExitIntQuesModel();
		model.initiate(context, session);
		if (intques.getExIntcode() == null
				|| intques.getExIntcode().equals("null")
				|| intques.getExIntcode().equals("")) {
			model.getexIntApp(intques);
			getRecord();
		} else {
			getComment();

		}
		model.terminate();

		return SUCCESS;
	}

	/**
	 * Method to delete the saved record
	 * 
	 * @return String
	 * 
	 */
	public String delete() {
		ExitIntQuesModel model = new ExitIntQuesModel();
		model.initiate(context, session);
		String msg = model.deleteRecord(intques);
		addActionMessage(msg);
		reset();
		model.terminate();

		return SUCCESS;
	}

	/**
	 * Method to generate the Report 
	 * 
	 */
	public void report() throws Exception {

		ExitIntQuesModel model = new ExitIntQuesModel();
		model.initiate(context, session);
		String extIntCode=request.getParameter("exIntcode");
		if(extIntCode!=null && !(extIntCode.equals("") && !extIntCode.equals("null"))){
			intques.setExIntcode(extIntCode);
		}
		model.report(intques, response);
		model.terminate();

	}

	/**
	 * Search (f9) for getting resigned employee 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "";
		if (intques.isGeneralFlag()) {
			query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " DECODE(NVL(TO_CHAR(HRMS_EXIT_HDR.RESIGN_CODE),'R'),'R','Not Interviewed','Interviewed'),"
					+ " TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),NVL(RANK_NAME,' '),NVL(CENTER_NAME,' '),TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),HRMS_RESIGN.RESIGN_EMP,HRMS_EMP_OFFC.EMP_ID,HRMS_RESIGN.RESIGN_CODE,HRMS_EXIT_HDR.RESIGN_CODE exresg"
					+ " FROM HRMS_RESIGN"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EXIT_HDR on(HRMS_EXIT_HDR.RESIGN_CODE=HRMS_RESIGN.RESIGN_CODE)"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID="
					+ intques.getUserEmpId()
					+ " ORDER BY RESIGN_DATE DESC";
		} else {
			query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),DECODE(NVL(TO_CHAR(HRMS_EXIT_HDR.RESIGN_CODE),'R'),'R','Not Interviewed','Interviewed'),"
					+ " NVL(RANK_NAME,' '),NVL(CENTER_NAME,' '),TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),HRMS_RESIGN.RESIGN_EMP,HRMS_EMP_OFFC.EMP_ID,HRMS_RESIGN.RESIGN_CODE,HRMS_EXIT_HDR.RESIGN_CODE exresg"
					+ " FROM HRMS_RESIGN"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EXIT_HDR on(HRMS_EXIT_HDR.RESIGN_CODE=HRMS_RESIGN.RESIGN_CODE)"
					+ " ORDER BY RESIGN_DATE DESC";

		}// end else
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Id", "Employee Name",
				"Resignation Date", "Status" };
		String[] headerWidth = { "20", "40", "20", "20" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "intques.empToken", "intques.empName",
				"intques.resignDate", "intques.isInterviewed",
				"intques.designation", "intques.branch", "intques.sepDate",
				"intques.joinDate", "intques.resignId", "intques.empId",
				"intques.resignCode", "intques.exIntcode" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

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
		String submitToMethod = "ExitInterview_getexIntApp.action";
		// ExitInterview_getRecord.action
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * public String exIntAppaction() throws Exception {
	 *//**
	 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 * OUTPUT
	 */
	/*
	 * logger.info("emppppppppppppppid==="+intques.getEmpId());
	 * logger.info("emppppppppppppppid11111111==="+intques.getUserEmpId());
	 * 
	 * String query = "SELECT DISTINCT
	 * HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||'
	 * '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," + "
	 * NVL(RANK_NAME,' '),NVL(DEPT_NAME,' '),NVL(CADRE_NAME,'
	 * '),NVL(CENTER_NAME,' '),"+ "
	 * TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_EXITQUESHDR.EXITQUESHDR_DATE,'DD-MM-YYYY'),HRMS_EXITQUESHDR.EXITQUESHDR_CODE
	 * FROM HRMS_EMP_OFFC"+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =
	 * HRMS_EMP_OFFC.EMP_DESG)"+ " LEFT JOIN HRMS_CENTER
	 * ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"+ " LEFT JOIN
	 * HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_CENTER.CENTER_DEPT_ID)"+ " LEFT JOIN
	 * HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_GRADE)"+ " inner
	 * JOIN HRMS_EXITQUESHDR ON(HRMS_EXITQUESHDR.EXITQUESHDR_ECODE =
	 * HRMS_EMP_OFFC.EMP_ID)"+ " ORDER BY HRMS_EMP_OFFC.EMP_ID";
	 * 
	 * String que="SELECT distinct
	 * HRMS_EXITQUESHDR.EXITQUESHDR_CODE,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||'
	 * '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'
	 * '||HRMS_EMP_OFFC.EMP_LNAME,"+ " NVL(RANK_NAME,' '),NVL(DEPT_NAME,'
	 * '),NVL(CENTER_NAME,' '),"+ "
	 * TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_EXITQUESHDR.EXITQUESHDR_DATE,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_ID "+ "
	 * FROM HRMS_EXITQUESHDR"+ " INNER JOIN HRMS_EMP_OFFC
	 * ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EXITQUESHDR.EXITQUESHDR_ECODE)"+ " inner
	 * JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"+ "
	 * INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_DESG)"+ "
	 * INNER JOIN HRMS_CENTER
	 * ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"+ " INNER JOIN
	 * HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"+ " INNER JOIN
	 * HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_GRADE)"+ " INNER
	 * JOIN HRMS_EXITQUESDTL
	 * ON(HRMS_EXITQUESDTL.EXITQUESDTL_CODE=HRMS_EXITQUESHDR.EXITQUESHDR_CODE)"+ "
	 * ORDER BY HRMS_EXITQUESHDR.EXITQUESHDR_CODE";
	 * 
	 * 
	 * 
	 * 
	 *//**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	/*
	 * String[] headers = { "Exit Interview Code" ,"Employee Name"}; String[]
	 * headerWidth = { "20","80" };
	 *//**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */
	/*
	 * 
	 * String[] fieldNames =
	 * {"intques.exIntcode","intques.empToken","intques.empName","intques.designation","intques.department","intques.branch","intques.joinDate","intques.leaveDate","intques.empId"};
	 *//**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
	 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
	 * 
	 * NOTE: COLUMN NUMBERS STARTS WITH 0
	 * 
	 */
	/*
	 * int[] columnIndex = {0,1,2,3,4,5,6,7,8};
	 * 
	 *//**
	 * WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	/*
	 * String submitFlag = "true";
	 * 
	 *//**
	 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
	 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
	 * ACTION>_<METHOD TO CALL>.action
	 */
	/*
			String submitToMethod = "ExitInterview_getComment.action";
				//ExitInterview_getRecord.action
	 *//**
	 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 */
	/*

			setF9Window(que,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);

			return "f9page";
		}*/
}
