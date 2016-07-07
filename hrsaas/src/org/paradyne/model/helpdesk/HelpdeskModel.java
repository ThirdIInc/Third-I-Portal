package org.paradyne.model.helpdesk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.helpdesk.HelpDesk;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

/**
 * @author Reeba_Joseph
 * @date 16-04-2010
 * @description HelpdeskModel class serves as model for help desk application
 *              form to write business logic to save, update, delete help desk
 *              application
 */
public class HelpdeskModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpdeskModel.class);

	public void getPendingRequests(HelpDesk helpdesk, HttpServletRequest request,
			String empId) {
		try {

			String listQuery = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " TO_CHAR(HELPDESK_REQUEST_HDR.REQUEST_DATE,'DD-MM-YYYY'),HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR, "
					+ " NVL(HELPDESK_REQUEST_HDR.REQUEST_TOKEN,HELPDESK_REQUEST_HDR.REQUEST_ID),HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS, NVL(STATUS_ISLAST,' '), STATUS_ORDER, NVL(STATUS_CATAGORY_NAME,' '),  "
					+ " DECODE(REQUEST_APPL_STATUS,'D','Draft',STATUS_CATAGORY_NAME)STATUS, "
					+ " REQUEST_SUBJECT,HELPDESK_DEPT.DEPT_NAME,HELPDESK_REQUEST_HDR.REQUEST_ID "
					+ " FROM HELPDESK_REQUEST_HDR "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR) "
					+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV = HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS) "
					+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
					+ " WHERE HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS NOT IN ('C','N','R') AND HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY= "
					+ empId + " ORDER BY REQUEST_DATE DESC";

			Object pendingData[][] = getSqlModel().getSingleResult(listQuery);

			String[] pageIndexPending = Utility.doPaging(helpdesk.getMyPage(),
					pendingData.length, 20);
			if (pageIndexPending == null) {
				pageIndexPending[0] = "0";
				pageIndexPending[1] = "20";
				pageIndexPending[2] = "1";
				pageIndexPending[3] = "1";
				pageIndexPending[4] = "";
			}// END IF

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexPending[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexPending[3])));
			if (pageIndexPending[4].equals("1"))
				helpdesk.setMyPage("1");

			if (pendingData != null && pendingData.length > 0) {
				helpdesk.setPendingLength("true");
				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexPending[0]); i < Integer
						.parseInt(pageIndexPending[1]); i++) {
					HelpDesk pendingBean = new HelpDesk();
					pendingBean.setReqEmpToken(String
							.valueOf(pendingData[i][0]));
					pendingBean
							.setReqEmpName(String.valueOf(pendingData[i][1]));
					pendingBean.setReqDate(String.valueOf(pendingData[i][2]));
					pendingBean.setPendingEmpId(String
							.valueOf(pendingData[i][3]));
					pendingBean.setPendingReqNo(String
							.valueOf(pendingData[i][4]));
					pendingBean.setReqStatus(String.valueOf(pendingData[i][5]));
					pendingBean.setReqStatusIsLast(String
							.valueOf(pendingData[i][6]));
					pendingBean.setReqStatusOrder(String
							.valueOf(pendingData[i][7]));
					//pendingBean.setReqStatusName(String
					//		.valueOf(pendingData[i][8]));
					
					if (String.valueOf(pendingData[i][9]).equals(" ")) {
						logger.info("in i-9 blank");
						pendingBean.setReqStatusName(String.valueOf(pendingData[i][8]));
					} else {
						logger.info("in else");
						pendingBean.setReqStatusName(String.valueOf(pendingData[i][9]));
					}
				
					pendingBean.setReqSubject(String.valueOf(pendingData[i][10]));
					pendingBean.setPendingReqDept(String.valueOf(pendingData[i][11]));
					pendingBean.setPendingReqCode(String.valueOf(pendingData[i][12]));
					
					pendingList.add(pendingBean);
				}// END FOR LOOP
				helpdesk.setPendingList(pendingList);
			}// END IF DATA NOT NULL
		} catch (Exception e) {
			logger.error("Exception in getPendingList------" + e);
			e.printStackTrace();
		}// END TRY CATCH BLOCK

	}

	public void getClosedList(HelpDesk helpdesk, HttpServletRequest request,
			String empId) {
		try {
			String listQuery = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " TO_CHAR(HELPDESK_REQUEST_HDR.REQUEST_DATE,'DD-MM-YYYY'),HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR, "
					+ " NVL(HELPDESK_REQUEST_HDR.REQUEST_TOKEN,HELPDESK_REQUEST_HDR.REQUEST_ID),HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS, NVL(STATUS_ISLAST,' '), STATUS_ORDER, NVL(STATUS_CATAGORY_NAME,' '),  "
					+ " DECODE(REQUEST_APPL_STATUS,'D','Draft','N','Rejected','C','Closed',STATUS_CATAGORY_NAME) STATUS, "
					+ " REQUEST_SUBJECT,HELPDESK_DEPT.DEPT_NAME,HELPDESK_REQUEST_HDR.REQUEST_ID "
					+ " FROM HELPDESK_REQUEST_HDR "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR) "
					+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV = HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS) "
					+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
					+ " WHERE HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS IN ('C','N','R') AND HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY= "
					+ empId + " ORDER BY REQUEST_DATE DESC";

			Object closedData[][] = getSqlModel().getSingleResult(listQuery);

			String[] pageIndexClosed = Utility.doPaging(helpdesk
					.getMyPageClosed(), closedData.length, 20);
			if (pageIndexClosed == null) {
				pageIndexClosed[0] = "0";
				pageIndexClosed[1] = "20";
				pageIndexClosed[2] = "1";
				pageIndexClosed[3] = "1";
				pageIndexClosed[4] = "";
			}// END IF

			request.setAttribute("totalPageClosed", Integer.parseInt(String
					.valueOf(pageIndexClosed[2])));
			request.setAttribute("PageNoClosed", Integer.parseInt(String
					.valueOf(pageIndexClosed[3])));
			if (pageIndexClosed[4].equals("1"))
				helpdesk.setMyPageClosed("1");

			if (closedData != null && closedData.length > 0) {

				helpdesk.setClosedLength("true");
				ArrayList<Object> closedList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexClosed[0]); i < Integer
						.parseInt(pageIndexClosed[1]); i++) {

					HelpDesk closedBean = new HelpDesk();
					closedBean.setReqEmpToken(String.valueOf(closedData[i][0]));
					closedBean.setReqEmpName(String.valueOf(closedData[i][1]));
					closedBean.setReqDate(String.valueOf(closedData[i][2]));
					closedBean.setClosedEmpId(String.valueOf(closedData[i][3]));
					closedBean.setClosedReqNo(String.valueOf(closedData[i][4]));
					closedBean.setReqStatus(String.valueOf(closedData[i][5]));
					closedBean.setReqStatusIsLast(String
							.valueOf(closedData[i][6]));
					closedBean.setReqStatusOrder(String
							.valueOf(closedData[i][7]));
					//closedBean.setReqStatusName(String
					//		.valueOf(closedData[i][8]));
					
					if (String.valueOf(closedData[i][9]).equals(" ")) {
						logger.info("in i-9 blank");
						closedBean.setReqStatusName(String.valueOf(closedData[i][8]));
					} else {
						logger.info("in else");
						closedBean.setReqStatusName(String.valueOf(closedData[i][9]));
					}
					
					if(String.valueOf(closedData[i][10]).length()>15){
						closedBean.setReqSubject(String.valueOf(closedData[i][10]).substring(0, 15)+"...");
					}else
						closedBean.setReqSubject(String.valueOf(closedData[i][10]));
					closedBean.setClosedReqDept(String.valueOf(closedData[i][11]));
					closedBean.setClosedReqCode(String.valueOf(closedData[i][12]));
					closedList.add(closedBean);

				}// END LOOP
				helpdesk.setClosedList(closedList);
			}// END IF DATA NOT NULL

		} catch (Exception e) {
			logger.error("Exception in getClosedList------" + e);
		}// END TRY CATCH BLOCK
	}
	
	/**
	 * @method save
	 * @purpose to insert the help desk application details in HRMS_HELP table
	 * @param bean
	 *            to pop up all the form field values
	 * @param deptFlow
	 *            to get the approver code for the selected department
	 * @param status
	 * @param proofName 
	 * @return boolean
	 */
	public boolean save(HelpDesk bean, Object[][] empFlow, String status, String[] proofName) {
		String uploaddocument = "";
		if (proofName != null && proofName.length > 0) {
			for (int i = 0; i < proofName.length; i++) {
				uploaddocument += proofName[i] + ",";
			}
			uploaddocument = uploaddocument.substring(0, uploaddocument
					.length() - 1);
			logger.info("upload doc :  "+uploaddocument);
		}
		
		Object[][] saveObj = new Object[1][17];

		//saveObj[0][0] = bean.getAppDate();// REQUEST_DATE
		saveObj[0][0] = bean.getRadioCheck();// REQUEST_APPLIED_FOR S or O or C
		saveObj[0][1] = bean.getRequestForEmpId();// REQUEST_POSTED_FOR
		saveObj[0][2] = bean.getUserEmpId();// REQUEST_POSTED_BY
		saveObj[0][3] = bean.getReqDeptCode();// REQUEST_APPLIED_TO_DEPT
		saveObj[0][4] = bean.getReqTypeCode();// REQUEST_TYPE
		saveObj[0][5] = bean.getSubReqTypeCode();// REQUEST_SUBTYPE
		saveObj[0][6] = bean.getSubject().trim();// REQUEST_SUBJECT
		saveObj[0][7] = bean.getComments();// REQUEST_DESC
		saveObj[0][8] = uploaddocument;// REQUEST_FILE_UPLOAD
		saveObj[0][9] = status;// REQUEST_APPL_STATUS == OPEN
		saveObj[0][10] =( empFlow!=null && empFlow.length >0 )? String.valueOf(empFlow[0][0]): "" ;// REQUEST_PENDING_WITH
		saveObj[0][11] = bean.getClientName();// REQUEST_CLIENT_NAME
		if(bean.getIsManagerApproval().equals("Y")){
			if(status.equals("D")){
				saveObj[0][12] = "N"; // REQUEST_MGR_STATUS = N 
			}else{
				saveObj[0][12] = "P"; // REQUEST_MGR_STATUS = P = Pending for manager approval
			}
		}
		else{
			saveObj[0][12] = "N"; // N = Not Applicable
		}
		saveObj[0][13] = "N"; //REQUEST_EMP_STATUS O = Open
		if(bean.getIsAssetRequest().equals("Y")){
			saveObj[0][14] = bean.getAssetTypeCode();//ASSET_TYPE
			saveObj[0][15] = bean.getAssetSubTypeCode();//ASSET_SUB_TYPE
			saveObj[0][16] = bean.getAssetQuantity(); // ASSET_QUANTITY
		}else{
			saveObj[0][14] = "";//ASSET_TYPE
			saveObj[0][15] = "";//ASSET_SUB_TYPE
			saveObj[0][16] = ""; // ASSET_QUANTITY
		}
		
		
		//ADDING AUTO GENERATED REQUEST_ID
		
		String autoGenTokenQuery=" SELECT * FROM HRMS_APPL_CODE_TEMPLATE WHERE APPL_TYPE='Help'";
		Object[][]isAutoGenTokenQuery=getSqlModel().getSingleResult(autoGenTokenQuery);
		logger.info("isAutoGenTokenQuery.length : "+isAutoGenTokenQuery.length);
		
		boolean result=false;
		if(isAutoGenTokenQuery!=null && isAutoGenTokenQuery.length>0){
			result = getSqlModel().singleExecute(getQuery(1), saveObj);
			if (result) {
				String query1 = "SELECT MAX(REQUEST_ID) FROM HELPDESK_REQUEST_HDR";
				Object[][] data = getSqlModel().getSingleResult(query1);
				bean.setHelpcode(String.valueOf(data[0][0]));
			}
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			//UPDATE REQUEST_TOKEN
			String token ="";
			token = tempModel.generateApplicationCode("", "Help", bean.getEmpId(), sysDate);
			logger.info("token   : "+token);
			String updateQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_TOKEN ='"+token+"' WHERE REQUEST_ID="+bean.getHelpcode();
			getSqlModel().singleExecute(updateQuery);
		}
		else {//if(bean.getReqToken().trim().equals("")){
			logger.info("token   : "+bean.getReqToken());
			result = getSqlModel().singleExecute(getQuery(1), saveObj);
			if (result) {
				String query1 = "SELECT MAX(REQUEST_ID) FROM HELPDESK_REQUEST_HDR";
				Object[][] data = getSqlModel().getSingleResult(query1);
				bean.setHelpcode(String.valueOf(data[0][0]));
				String updateQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_TOKEN ='"+bean.getHelpcode()
						+"' WHERE REQUEST_ID="+bean.getHelpcode();
				getSqlModel().singleExecute(updateQuery);
			}
		}
		String tokenQuery = "SELECT NVL(REQUEST_TOKEN,' ') FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID="+bean.getHelpcode();
		Object[][] tokenObj = getSqlModel().getSingleResult(tokenQuery);
		if(tokenObj!=null && tokenObj.length > 0){
			bean.setReqToken(String.valueOf(tokenObj[0][0]));
		}
		return result;
	}
	
	public boolean saveDetails(HelpDesk bean, String str, String status) {

		boolean result = false;
		try {
			
			Object[][] dtlObj = new Object[1][7];
			dtlObj[0][0] = bean.getHelpcode();// REQUEST_ID
			
			dtlObj[0][1] = bean.getUserEmpId();// REQUEST_ACTION_BY
			dtlObj[0][2] = str;// REQUEST_FORWARDED_TO
			dtlObj[0][3] = status;// REQUEST_APPL_STATUS
			if (status.equals("O") || status.equals("C")) {
				dtlObj[0][4] = bean.getReopenCloseDesc();// REQUEST_COMMENTS
			} else
				dtlObj[0][4] = bean.getDescription();// REQUEST_COMMENTS
			
			dtlObj[0][5] = 0;//ACTUAL_CALL_DURATION
			dtlObj[0][6] = 0;//REQUEST_CALL_DURATION
			
			
			
		/*	String dateQuery = "SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY HH24:MI:SS')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID =" +bean.getHelpcode();
			Object[][]dateQueryObj=getSqlModel().getSingleResult(dateQuery);
			if(dateQueryObj!=null && dateQueryObj[0][0] !=null){
				String datetime="SELECT TO_NUMBER(TO_CHAR(TO_DATE('1','J')+(SYSDATE - TO_DATE('"+String.valueOf(dateQueryObj[0][0])+"','DD-MM-YYYY HH24:MI:SS') ),'J')-1)DAYS,TO_CHAR(TO_DATE('00:00:00','HH24:MI:SS')+(SYSDATE - TO_DATE('"+String.valueOf(dateQueryObj[0][0])+"','DD-MM-YYYY HH24:MI:SS')),'HH24:MI:SS') TIME"+
				 "  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID="+ bean.getHelpcode() ;
				Object[][]datetimeObj=getSqlModel().getSingleResult(datetime);
				if(datetimeObj!=null && datetimeObj.length>0){
					String days=String.valueOf(datetimeObj[0][0]);
					String time= String.valueOf(datetimeObj[0][1]);
					String[] splitTime = time.split(":");
					String hourdata=splitTime[0];
					String minutedata = splitTime[1];
					String seconddata =splitTime[2];
					
					String finalDateTime="";
					if(!days.equals("0")){
						finalDateTime +=days+" Days ";
					}
					if(!hourdata.equals("00")){
						finalDateTime +=hourdata+" Hours ";
					}
					
					if(!minutedata.equals("00")){
						finalDateTime +=minutedata+" Minutes ";
					}
					if(!seconddata.equals("00")){
						finalDateTime +=seconddata+" Seconds ";
					}
					dtlObj[0][5] = finalDateTime;
				}
				
			}else{
				dtlObj[0][5] = "00.00";
			}*/
			
			// INSERT INTO DTL TABLE
			String insertDtl = " INSERT INTO HELPDESK_ACTIVITY_LOG (REQUEST_ID, REQUEST_ACTION_BY, REQUEST_FORWARDED_TO, "
					+ " REQUEST_STATUS, REQUEST_DATE, REQUEST_COMMENTS,ACTUAL_CALL_DURATION,REQUEST_CALL_DURATION) VALUES(?, ?, ?, ?, SYSDATE, ?,?,?)";
			result = getSqlModel().singleExecute(insertDtl, dtlObj);
			
			double totalMinutes =0.0;
			double totalActualduration=0.0;
			String updateReqSumQuery = "";
			Object[][] updateReqSumObj = null;
			String sumQuery = "SELECT SUM(REQUEST_CALL_DURATION),SUM(ACTUAL_CALL_DURATION) from HELPDESK_ACTIVITY_LOG where REQUEST_ID = "+bean.getHelpcode();
			Object[][] sumObj = getSqlModel().getSingleResult(sumQuery);
			if(sumObj!=null && sumObj.length >0){
				totalMinutes = Double.parseDouble(String.valueOf(sumObj[0][0]));
				totalActualduration = Double.parseDouble(String.valueOf(sumObj[0][1]));
			}
			updateReqSumQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_COMPLETION_TIME = ?,ACTUAL_COMPLITION_TIME=?,REQUEST_COMPLITION_DATE=SYSDATE WHERE REQUEST_ID=?";
			updateReqSumObj = new Object[1][3];
			
			
			updateReqSumObj[0][0] = totalMinutes;
			updateReqSumObj[0][1] = totalActualduration;
			updateReqSumObj[0][2] =  bean.getHelpcode();
			
			
			result = getSqlModel().singleExecute(updateReqSumQuery,
					updateReqSumObj);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @method update
	 * @purpose to update the selected application details in HRMS_HELP table
	 * @param bean
	 *            to pop up all the form field values
	 * @param deptFlow
	 *            to get the approver code for the selected department
	 * @param status 
	 * @param proofName 
	 * @return boolean
	 */
	public boolean update(HelpDesk bean, String forwardedTo, String status, String[] proofName) {
		String uploaddocument = "";
		if (proofName != null && proofName.length > 0) {
			for (int i = 0; i < proofName.length; i++) {
				uploaddocument += proofName[i] + ",";
			}
			uploaddocument = uploaddocument.substring(0, uploaddocument
					.length() - 1);
			logger.info("upload doc :  "+uploaddocument);
		}
		
		Object[][] updateObj = new Object[1][18];

		//updateObj[0][0] = bean.getAppDate();// REQUEST_DATE
		updateObj[0][0] = bean.getRadioCheck();// REQUEST_APPLIED_FOR S or O or C
		updateObj[0][1] = bean.getRequestForEmpId();// REQUEST_POSTED_FOR
		updateObj[0][2] = bean.getUserEmpId();// REQUEST_POSTED_BY
		updateObj[0][3] = bean.getReqDeptCode();// REQUEST_APPLIED_TO_DEPT
		updateObj[0][4] = bean.getReqTypeCode();// REQUEST_TYPE
		updateObj[0][5] = bean.getSubReqTypeCode();// REQUEST_SUBTYPE
		updateObj[0][6] = bean.getSubject();// REQUEST_SUBJECT
		updateObj[0][7] = bean.getComments();// REQUEST_DESC
		updateObj[0][8] = uploaddocument;// REQUEST_FILE_UPLOAD
		updateObj[0][9] = status;// REQUEST_STATUS
		updateObj[0][10] = forwardedTo;// REQUEST_PENDING_WITH
		updateObj[0][11] = bean.getClientName();// REQUEST_CLIENT_NAME
		if(bean.getIsManagerApproval().equals("Y")){
			updateObj[0][12] = "P"; // REQUEST_MGR_STATUS = P = Pending for manager approval
		}
		else{
			updateObj[0][12] = "N"; // N = Not Applicable
		}
		updateObj[0][13] = "N"; //REQUEST_EMP_STATUS O = Open
		if(bean.getIsAssetRequest().equals("Y")){
			updateObj[0][14] = bean.getAssetTypeCode();//ASSET_TYPE
			updateObj[0][15] = bean.getAssetSubTypeCode();//ASSET_SUB_TYPE
			updateObj[0][16] = bean.getAssetQuantity(); // ASSET_QUANTITY
		}else{
			updateObj[0][14] = "";//ASSET_TYPE
			updateObj[0][15] = "";//ASSET_SUB_TYPE
			updateObj[0][16] = ""; // ASSET_QUANTITY
		}
		updateObj[0][17] = bean.getHelpcode();
		logger.info("helpdesk code.... " + bean.getHelpcode());

		return getSqlModel().singleExecute(getQuery(2), updateObj);
	}
	
	public boolean updateHdrWhileProcess(HelpDesk helpdesk, String forwardedTo,	String status) {
		boolean result = false;
		try {
			String query1 = "SELECT REQUEST_PENDING_WITH FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID ="
				+helpdesk.getHelpcode();
			Object[][] data = getSqlModel().getSingleResult(query1);
			if(data!=null && data.length > 0){
				forwardedTo = String.valueOf(data[0][0]);
			}
			Object[][] updateObj = new Object[1][3];
			updateObj[0][0] = status;// REQUEST_STATUS
			updateObj[0][1] = forwardedTo;// REQUEST_PENDING_WITH
			updateObj[0][2] = helpdesk.getHelpcode();
			String updateQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_APPL_STATUS=?, REQUEST_PENDING_WITH=?  "
					+ " WHERE REQUEST_ID = ? ";
			result = getSqlModel().singleExecute(updateQuery, updateObj);
			
			Object[][] updateLog = new Object[1][5];
			updateLog[0][0] = helpdesk.getRequestId();// REQUEST_ID
			updateLog[0][1] = helpdesk.getUserEmpId();// REQUEST_ACTION_BY
			updateLog[0][2] = forwardedTo;// REQUEST_FORWARDED_TO
			updateLog[0][3] = status;// REQUEST_APPL_STATUS
			updateLog[0][4] = helpdesk.getReopenComments();// REQUEST_COMMENTS
			// ACTUAL_CALL_DURATION
			String MadateQuery = "SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY HH24:MI')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID ="
				+ helpdesk.getRequestId();
			Object[][] MaxdateQueryObj = getSqlModel().getSingleResult(MadateQuery);
			
			if(MaxdateQueryObj!=null && MaxdateQueryObj.length >0){
				
				String diffDateTime ="SELECT (SYSDATE- TO_DATE('"+String.valueOf(MaxdateQueryObj[0][0])+"','DD-MM-YYYY HH24:MI'))*24 *60 FROM DUAL";
				Object[][] diffDateTimeObj = getSqlModel().getSingleResult(diffDateTime);
				
				if(diffDateTimeObj !=null && diffDateTimeObj.length >0){
					updateLog[0][5] =String.valueOf(diffDateTimeObj[0][0]) ;
				}else{
					updateLog[0][5] = 0;
				}
					
			}
			updateLog[0][6] = 0;// REQUEST_CALL_DURATION
			
			String insertLog = " INSERT INTO HELPDESK_ACTIVITY_LOG (REQUEST_ID, REQUEST_ACTION_BY, REQUEST_FORWARDED_TO, "
					+ " REQUEST_STATUS, REQUEST_DATE, REQUEST_COMMENTS,ACTUAL_CALL_DURATION, REQUEST_CALL_DURATION) VALUES(?, ?, ?, ?, SYSDATE, ?,?,?)";
			
			getSqlModel().singleExecute(insertLog, updateLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result;
	}
	
	/**
	 * @method delete
	 * @purpose to delete the selected application details from HRMS_HELP table
	 * @param bean
	 *            to pop up all the form field values
	 * @return boolean
	 */
	public boolean delete(HelpDesk bean) {
		boolean result = false;
		Object[][] delObj = new Object[1][1];
		delObj[0][0] = bean.getHelpcode();
		result = getSqlModel().singleExecute(getQuery(3), delObj);

		return result;
	}


	/**
	 * @method checkNull
	 * @purpose to check whether the value of the string parameter is null or
	 *          not
	 * @param result
	 *            value of the parameter to be checked
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	/**
	 * @method getDisplay
	 * @purpose to set the application date to sysdate
	 * @param bean
	 *            to pop up all the form field values
	 */
	public void getDisplay(HelpDesk bean) {
		String query = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";

		Object[][] date = getSqlModel().getSingleResult(query);

		bean.setAppDate(String.valueOf(date[0][0]));
	}

	/**
	 * @method setDate
	 * @purpose to set the application date to sysdate
	 * @param bean
	 *            to pop up all the form field values
	 * @return object array containing sysdate
	 */
	public Object[][] setDate(HelpDesk bean) {
		logger.info("set date in model");

		String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";

		Object[][] date = getSqlModel().getSingleResult(dateQuery);
		logger.info("date==" + date[0][0]);

		return date;
	}

	/**
	 * @method getEmployeeDetails
	 * @purpose to retrieve all the details for the selected employee
	 * @param empId
	 *            to get the selected employee code
	 * @param bean
	 *            to pop up all the form field values
	 * @param request 
	 */
	public HelpDesk getEmployeeDetails(String reqAppCode, HelpDesk bean, HttpServletRequest request) {

		Object[] requestId = new Object[1];
		requestId[0] = reqAppCode;
		
 		logger.info("empDtails.requestId------" + requestId[0] );
		


		Object[][] empDtail = getSqlModel().getSingleResult(getQuery(4),	requestId);

		logger.info("empDtails.length------" + empDtail.length+"____________"+requestId[0] );
		if(empDtail != null && empDtail.length > 0) {
			bean.setHelpcode(reqAppCode);
			
			bean.setReqToken(String.valueOf(empDtail[0][0]));//req token
			bean.setReqDate(String.valueOf(empDtail[0][1]));//req date
			bean.setEmpId(String.valueOf(empDtail[0][3]));// posted by Id
			bean.setEmpToken(String.valueOf(empDtail[0][4]));//posted by token
			bean.setEmpName(String.valueOf(empDtail[0][5]));// posted by name
			bean.setBranchCode(String.valueOf(empDtail[0][6]));//branch code
			bean.setBranchName(String.valueOf(empDtail[0][7]));//branch name
			bean.setDeptId(String.valueOf(empDtail[0][8]));//dept Id
			bean.setDeptName(String.valueOf(empDtail[0][9]));//dept name
			bean.setDesgCode(String.valueOf(empDtail[0][10]));//designation Id
			bean.setDesgName(String.valueOf(empDtail[0][11]));//designation name
			
			
			if(String.valueOf(empDtail[0][2]).equals("S")){
				bean.setRequestForEmpId(String.valueOf(empDtail[0][12]));//req appl for
				bean.setInitiatorId(String.valueOf(empDtail[0][12]));//initiator Id
				bean.setInitiatorToken(String.valueOf(empDtail[0][13]));//initiator token
				bean.setInitiatorName(String.valueOf(empDtail[0][14]));//initiator name
			}else if(String.valueOf(empDtail[0][2]).equals("O")){
				bean.setRequestForEmpId(String.valueOf(empDtail[0][12]));//req appl for
				bean.setOtherEmpId(String.valueOf(empDtail[0][12]));//other emp Id
				bean.setOtherEmpToken(String.valueOf(empDtail[0][13]));//other emp token
				bean.setOtherEmpName(String.valueOf(empDtail[0][14]));//other emp name
			}else{
				bean.setClientName(String.valueOf(empDtail[0][15]));//client name
			}
			request.setAttribute("radioStatus", String.valueOf(empDtail[0][2]));
			bean.setReqDeptCode(String.valueOf(empDtail[0][16]));//req dept
			bean.setReqDeptName(String.valueOf(empDtail[0][17]));//req dept name
			bean.setReqTypeCode(String.valueOf(empDtail[0][18]));// req type code
			bean.setReqType(String.valueOf(empDtail[0][19]));// req type code
			bean.setSubReqTypeCode(String.valueOf(empDtail[0][20]));//req sub type
			bean.setSubReqType(String.valueOf(empDtail[0][21]));//req sub type
			bean.setSubject(String.valueOf(empDtail[0][22]));//subject
			bean.setComments(String.valueOf(empDtail[0][23]));//req desc
			
			if(!String.valueOf(empDtail[0][24]).trim().equals("")){
				ArrayList<HelpDesk> proofList = new ArrayList<HelpDesk>();
				String[] proofNames = String.valueOf(empDtail[0][24]).split(",");
				for (int i = 0; i < proofNames.length; i++) {
					HelpDesk bean1 = new HelpDesk();
					bean1.setProofName(proofNames[i]);
					bean1.setProofSrNo(""+(i+1));// sr no
					proofList.add(bean1);
				}
				bean.setProofList(proofList);
				bean.setProofListFlag(true);
			}
			bean.setPendingEmpId(String.valueOf(empDtail[0][25]));//pending emp Id
			bean.setPendingEmpToken(String.valueOf(empDtail[0][26]));//pending emp token
			bean.setPendingEmpName(String.valueOf(empDtail[0][27]));//pending emp name
			bean.setReqManagerStatus(String.valueOf(empDtail[0][28]));//manager status
			bean.setReqEmpStatus(String.valueOf(empDtail[0][29]));//emp status
			bean.setHiddenStatus(String.valueOf(empDtail[0][30]));//request status
			bean.setDecodedReqStatus(String.valueOf(empDtail[0][31]));//request status
			bean.setAssetTypeCode(String.valueOf(empDtail[0][32]));//asset type code
			bean.setAssetType(String.valueOf(empDtail[0][33]));//asset type
			bean.setAssetSubTypeCode(String.valueOf(empDtail[0][34]));//asset sub type code
			bean.setAssetSubType(String.valueOf(empDtail[0][35]));//asset sub type	
			bean.setAssetQuantity(String.valueOf(empDtail[0][36]));//asset quantity
			bean.setRequestId(String.valueOf(empDtail[0][37]));//request Id
			bean.setReqEmpContactNo(String.valueOf(empDtail[0][38]));
			bean.setReqEmpExtensionNo(String.valueOf(empDtail[0][39]));
			bean.setReqEmpEmailId(String.valueOf(empDtail[0][40]));
		}
		
		//updated By Anantha lakshmi
		String mgrAppr = "SELECT IS_MANAGER_APPROVAL FROM HELPDESK_REQUEST_SUBTYPE "
			 +" INNER JOIN HELPDESK_REQUEST_HDR ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID=HELPDESK_REQUEST_HDR.REQUEST_SUBTYPE)"
			 +" WHERE REQUEST_ID="+reqAppCode;
		
		Object[][] mgrApprObj = getSqlModel().getSingleResult(mgrAppr);
		if(mgrApprObj != null && mgrApprObj.length > 0){
			bean.setIsManagerApproval(String.valueOf(mgrApprObj[0][0]));
		}

		return bean;
	}
	
	
	public String getStatus(HelpDesk helpdesk) {
		logger.info("subtype code   : " + helpdesk.getSubReqTypeCode());
		logger.info("reqtype code   : " + helpdesk.getReqTypeCode());
		String slaQuery = "SELECT REQUEST_SUBTYPE_SLA FROM HELPDESK_REQUEST_SUBTYPE "
				+ " WHERE REQUEST_SUBTYPE_ID="
				+ helpdesk.getSubReqTypeCode()
				+ " AND REQUEST_TYPE_ID= " + helpdesk.getReqTypeCode();
		Object[][] SLAObj = getSqlModel().getSingleResult(slaQuery);

		String statusQuery = "SELECT HELPDESK_SLA_DTL.STATUS_CATAGORY_ID, NVL(STATUS_CATAGORY_NAME,' '), "
				+ " STATUS_ABBREV, STATUS_ORDER, STATUS_ISLAST "
				+ " FROM HELPDESK_SLA_DTL "
				+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_ID = HELPDESK_SLA_DTL.STATUS_CATAGORY_ID) "
				+ " WHERE SLA_ID=" + String.valueOf(SLAObj[0][0]);
		Object[][] statusObj = getSqlModel().getSingleResult(statusQuery);

		String status = "";
		if (statusObj != null && statusObj.length > 0) {
			for (int i = 0; i < statusObj.length; i++) {
				status += String.valueOf(statusObj[0][1]) + ",";
			}
		} else {
			String statusMasterQuery = "SELECT STATUS_CATAGORY_ID, NVL(STATUS_CATAGORY_NAME,' '),STATUS_ABBREV "
					+ " STATUS_ORDER, STATUS_ISLAST FROM HELPDESK_SLA_STATUS_CATAGORY ";
			Object[][] statusMasterObj = getSqlModel().getSingleResult(
					statusMasterQuery);
			if (statusMasterObj != null && statusMasterObj.length > 0) {
				for (int i = 0; i < statusMasterObj.length; i++) {
					status += String.valueOf(statusMasterObj[0][1]) + ",";
				}
			}
		}
		logger.info("status    : " + status);
		return status;
	}


	public void generateMailTemplate(HelpDesk helpdesk,
			HttpServletRequest request, Object[][] empFlow) {

		try {
			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' '), MODULE_NAME FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " LEFT JOIN HRMS_MODULE ON (HRMS_MODULE.MODULE_CODE = HRMS_MAIL_EVENTS.EVENT_MODULE_CODE) "
					+ " WHERE EVENT_CODE=109";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);
			String templateCode = "";
			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("N")) {
					logger.info("Event Option Flage is False.");
					return;
				}

				if (tempData[0][1] != null
						&& !String.valueOf(tempData[0][1]).equals("")) {
					templateCode = String.valueOf(tempData[0][1]);
				} else {
					logger.info("Template is not Defined." + templateCode);
					return;
				}

			} else {
				logger.info("Event Template is not Defined.");
				return;
			}
			String query = "SELECT REQUEST_POSTED_BY, REQUEST_PENDING_WITH, REQUEST_ID FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID="
					+"'" + helpdesk.getHelpcode()+"'";
			Object queryObj[][] = getSqlModel().getSingleResult(query);

			String msgType = "A";
			String fromID = String.valueOf(queryObj[0][0]);
			String toId = String.valueOf(queryObj[0][1]);
			String module = String.valueOf(tempData[0][4]);
			String applnID = String.valueOf(queryObj[0][2]);
			String level = "1";

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(String.valueOf(tempData[0][3]));
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, fromID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, toId);

			// Subject + Body
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			template.configMailAlert();


			// send mail
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			
			
			/*
			 * Mail to employee regarding leave application
			 * submission
			 */

			EmailTemplateBody template_applicant = new EmailTemplateBody();
			template_applicant.initiate(context, session);

			template_applicant
					.setEmailTemplate("HELPDESK_1_Request Submission to Requester Mail");
			template_applicant.getTemplateQueries();

			EmailTemplateQuery templateQueryApp1 = template_applicant
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, String.valueOf(queryObj[0][0]));

			EmailTemplateQuery templateQueryApp2 = template_applicant
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, String.valueOf(queryObj[0][0]));

			// Subject + Body
			EmailTemplateQuery templateQueryApp3 = template_applicant
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applnID);

			template_applicant.configMailAlert();

			// create process alerts
			
			// send mail
			template_applicant.sendApplicationMail();
			template_applicant.clearParameters();
			template_applicant.terminate();
			

			//generateMailTemplateForAutoResponse(bean, empFlow);
		} catch (Exception e) {
			logger.error("generateMailTemplate" + e);
		}// END TRY CATCH BLOCK

	}


	public void setSavedRequests(HelpDesk helpdesk, String reqId) {
		String query = "SELECT REQUEST_ID, TO_CHAR(REQUEST_DATE,'DD-MM-YYYY'), REQUEST_APPLIED_FOR, "
				+ " OFF1.EMP_FNAME||' '||OFF1.EMP_MNAME||' '||OFF1.EMP_LNAME REQ_FOR, REQUEST_POSTED_FOR, "
				+ " OFF2.EMP_FNAME||' '||OFF2.EMP_MNAME||' '||OFF2.EMP_LNAME REQ_BY, REQUEST_POSTED_BY, HELPDESK_DEPT.DEPT_NAME, "
				+ " REQUEST_APPLIED_TO_DEPT, HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME, REQUEST_TYPE,  HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_NAME, "
				+ " REQUEST_SUBTYPE, REQUEST_SUBJECT, REQUEST_DESC, NVL(REQUEST_FILE_UPLOAD,' '), "
				+ " REQUEST_APPL_STATUS, OFF3.EMP_FNAME||' '||OFF3.EMP_MNAME||' '||OFF3.EMP_LNAME PENDING_WITH, REQUEST_PENDING_WITH, REQUEST_CLIENT_NAME, "
				+ " OFF1.EMP_TOKEN "
				+ " FROM HELPDESK_REQUEST_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC OFF1 ON (OFF1.EMP_ID = HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR) "
				+ " LEFT JOIN HRMS_EMP_OFFC OFF2 ON (OFF2.EMP_ID = HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY) "
				+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
				+ " LEFT JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_TYPE) "
				+ " LEFT JOIN HELPDESK_REQUEST_SUBTYPE ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_SUBTYPE) "
				+ " LEFT JOIN HRMS_EMP_OFFC OFF3 ON (OFF3.EMP_ID = HELPDESK_REQUEST_HDR.REQUEST_PENDING_WITH) "
				+ " WHERE REQUEST_ID='" + reqId+"'";
		Object[][] data = getSqlModel().getSingleResult(query);
		helpdesk.setHelpcode(String.valueOf(data[0][0]));
		helpdesk.setReqDate(String.valueOf(data[0][1]));
		helpdesk.setAppFor(String.valueOf(data[0][2]));
		helpdesk.setHAppFor(String.valueOf(data[0][2]));
		helpdesk.setReqDeptName(String.valueOf(data[0][7]));
		helpdesk.setReqDeptCode(String.valueOf(data[0][8]));
		helpdesk.setReqType(String.valueOf(data[0][9]));
		helpdesk.setReqTypeCode(String.valueOf(data[0][10]));
		helpdesk.setSubReqType(String.valueOf(data[0][11]));
		helpdesk.setSubReqTypeCode(String.valueOf(data[0][12]));
		helpdesk.setSubject(String.valueOf(data[0][13]));
		helpdesk.setComments(String.valueOf(data[0][14]));
		//helpdesk.setUploadDoc(String.valueOf(data[0][15]));
		// String.valueOf(data[0][15]) //upload
		helpdesk.setStatus(String.valueOf(data[0][16]));
		// String.valueOf(data[0][17]) //pending with
		// String.valueOf(data[0][18]) //pending with code
		helpdesk.setClientName(String.valueOf(data[0][19]));
		if(!String.valueOf(data[0][15]).equals(""))
			setFileList(helpdesk,String.valueOf(data[0][15]));
	}
	
	public void setFileList(HelpDesk helpdesk, String document) {
		logger.info("in setFileList");
		String[] splitDocument = document.split(",");
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (splitDocument != null && splitDocument.length > 0) {
			for (int j = 0; j < splitDocument.length; j++) {
				if(!splitDocument[j].equals("")){
					logger.info("in for loopo ------"+ splitDocument[j]);
					HelpDesk bean = new HelpDesk();
					bean.setProofSrNo(String.valueOf(j + 1));
					bean.setProofName(splitDocument[j]);
					tableList.add(bean);
				}
			}
			helpdesk.setProofList(tableList);
		}
		
	}

	// METHODS FOR APPROVAL
	
	/**
	 * THIS METHOD IS USED FOR DISPLAYING EMPLOYEE INFORMARTION
	 * 
	 * @param leaveApp
	 * @return String
	 */
	public String showEmp(HelpDesk bean) {
		try {
			Object[] emp = new Object[1];
			emp[0] = bean.getEmpId();
			Object[][] empdata = getSqlModel()
					.getSingleResult(getQuery(4), emp);

			bean.setEmpId(String.valueOf(empdata[0][0]));
			bean.setEmpName(String.valueOf(empdata[0][1]));
			logger.info("emp namee : "+bean.getEmpName());
			bean.setBranchCode(String.valueOf(empdata[0][2]));
			bean.setBranchName(String.valueOf(empdata[0][3]));
			bean.setDesgCode(String.valueOf(empdata[0][4]));
			bean.setDesgName(String.valueOf(empdata[0][5]));
			bean.setDeptCode(String.valueOf(empdata[0][6]));
			bean.setDeptName(String.valueOf(empdata[0][7]));
			bean.setEmpToken(String.valueOf(empdata[0][8]));

		} catch (Exception e) {
			logger.error("Exception in showEmp--------- " + e);
		}// END TRY CATCH BLOCK
		return "success";

	}// end of showEmp

	public void setStatus(HelpDesk helpdesk) {
		logger.info("subtype code   : " + helpdesk.getSubReqTypeCode());
		logger.info("reqtype code   : " + helpdesk.getReqTypeCode());
		String slaQuery = "SELECT REQUEST_SUBTYPE_SLA FROM HELPDESK_REQUEST_SUBTYPE "
				+ " WHERE REQUEST_SUBTYPE_ID="
				+ helpdesk.getSubReqTypeCode()
				+ " AND REQUEST_TYPE_ID= " + helpdesk.getReqTypeCode();
		Object[][] SLAObj = getSqlModel().getSingleResult(slaQuery);

		TreeMap map = new TreeMap();

		String statusQuery = "SELECT HELPDESK_SLA_DTL.STATUS_CATAGORY_ID, NVL(STATUS_CATAGORY_NAME,' '), "
				+ " NVL(STATUS_ABBREV, ' '), STATUS_ORDER"
				+ " FROM HELPDESK_SLA_DTL "
				+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_ID = HELPDESK_SLA_DTL.STATUS_CATAGORY_ID) "
				+ " WHERE SLA_ID=" + String.valueOf(SLAObj[0][0]);
		Object[][] statusObj = getSqlModel().getSingleResult(statusQuery);

		if (statusObj != null && statusObj.length > 0) {
			for (int i = 0; i < statusObj.length; i++) {
				logger.info("Status : " + String.valueOf(statusObj[i][1]));
				map.put(String.valueOf(statusObj[i][2]), String
						.valueOf(statusObj[i][1]));
			}
		} else {
			String statsQuery = " SELECT STATUS_CATAGORY_ID, NVL(STATUS_CATAGORY_NAME,' '), NVL(STATUS_ABBREV, ' '),STATUS_ORDER "
					+ " FROM HELPDESK_SLA_STATUS_CATAGORY ORDER BY STATUS_CATAGORY_ID";
			Object[][] statsObj = getSqlModel().getSingleResult(statsQuery);
			if (statsObj != null && statsObj.length > 0) {
				for (int i = 0; i < statsObj.length; i++) {
					logger.info("Status : " + String.valueOf(statsObj[i][1]));
					map.put(String.valueOf(statsObj[i][2]), String
							.valueOf(statsObj[i][1]));
				}
			}
		}
		helpdesk.setTmap(map);
	}

	public void setSLAList(HelpDesk helpdesk) {
		String slaQuery = "SELECT REQUEST_SUBTYPE_SLA,NVL(SLA_NAME,' ') FROM HELPDESK_REQUEST_SUBTYPE "
				+ " LEFT JOIN HELPDESK_SLA_HDR on (HELPDESK_SLA_HDR.SLA_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA)"
				+ " WHERE REQUEST_SUBTYPE_ID = "
				+ helpdesk.getSubReqTypeCode()
				+ " AND " + " REQUEST_TYPE_ID = " + helpdesk.getReqTypeCode();
		Object[][] slaExist = getSqlModel().getSingleResult(slaQuery);
		if (slaExist != null && slaExist.length > 0) {
			// SET SLA NAME
			helpdesk.setSlaName(String.valueOf(slaExist[0][1]));
			ArrayList<Object> slaList = new ArrayList<Object>();
			String slaListQuery = " SELECT  NVL(STATUS_CATAGORY_NAME,' '), SLA_STATUS_CATAGORY_DURATION||' '|| "
					+ " DECODE(SLA_STATUS_CATAGORY_DURATION_T, 'H', 'Hours', 'D', 'Days') "
					+ " FROM HELPDESK_SLA_HDR "
					+ " LEFT JOIN HELPDESK_SLA_DTL ON (HELPDESK_SLA_DTL.SLA_ID = HELPDESK_SLA_HDR.SLA_ID) "
					+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_ID = HELPDESK_SLA_DTL.STATUS_CATAGORY_ID) "
					+ " WHERE HELPDESK_SLA_DTL.SLA_ID= " + slaExist[0][0]
					+ " ORDER BY STATUS_ORDER";
					                                               
			Object[][] slaListObj = getSqlModel().getSingleResult(slaListQuery);
			if (slaListObj != null && slaListObj.length > 0) {
				for (int i = 0; i < slaListObj.length; i++) {
					HelpDesk bean = new HelpDesk();
					bean.setStatusCategory(String.valueOf(slaListObj[i][0]));// STATUS
																				// NAME
					bean.setStatusDuration(String.valueOf(slaListObj[i][1]));// CATEGORY
																				// DURATION
					slaList.add(bean);
				}
				helpdesk.setSlaList(slaList);
				helpdesk.setSlaListFlag("true");
			} else {
				// SET SLA LENGTH
				helpdesk.setSlaListFlag("false");
			}

		}

	}

	public void setReqList(HelpDesk helpdesk) {
		ArrayList<Object> reqDtlList = new ArrayList<Object>();

		String dtlListQuery = " SELECT offc1.EMP_FNAME ||' '||offc1.EMP_MNAME ||' '|| offc1.EMP_LNAME PREV_OWNER, "
				+ " offc2.EMP_FNAME ||' '||offc2.EMP_MNAME ||' '|| offc2.EMP_LNAME NEW_OWNER, NVL(STATUS_CATAGORY_NAME,'') PREV_STATUS, "
				+ " TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH24:MI:SS'), NVL(REQUEST_COMMENTS,' '), "
				+ " CASE WHEN REQUEST_STATUS='P' THEN 'PENDING' WHEN REQUEST_STATUS='O' THEN 'REOPEN' WHEN REQUEST_STATUS='C' THEN 'CLOSED' "
				+ " ELSE ' ' END PREV_STATUS "
				+ " FROM HELPDESK_REQUEST_DTL "
				+ " INNER JOIN HRMS_EMP_OFFC offc1 ON (offc1.EMP_ID = HELPDESK_REQUEST_DTL.REQUEST_STAKEHOLDER) "
				+ " INNER JOIN HRMS_EMP_OFFC offc2 ON (offc2.EMP_ID = HELPDESK_REQUEST_DTL.REQUEST_FORWARDED_TO) "
				+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV = HELPDESK_REQUEST_DTL.REQUEST_STATUS)"
				// + " LEFT JOIN HELPDESK_REQUEST_HDR ON
				// (HELPDESK_REQUEST_HDR.REQUEST_ID =
				// HELPDESK_REQUEST_DTL.REQUEST_ID) "
				+ " WHERE REQUEST_ID ='" + helpdesk.getHelpcode()+"'"
				+ " ORDER BY REQUEST_DATE DESC";

		Object[][] dtlListObj = getSqlModel().getSingleResult(dtlListQuery);
		if (dtlListObj != null && dtlListObj.length > 0) {
			for (int i = 0; i < dtlListObj.length; i++) {
				HelpDesk bean = new HelpDesk();
				bean.setPrevOwner(String.valueOf(dtlListObj[i][0]));
				bean.setNewOwner(String.valueOf(dtlListObj[i][1]));
				logger.info("String.valueOf(dtlListObj[i][5])   : "
						+ String.valueOf(dtlListObj[i][5]));
				if (String.valueOf(dtlListObj[i][5]).equals(" ")) {
					logger.info("in i-5 blank");
					bean.setChkStatus(String.valueOf(dtlListObj[i][2]));
				} else {
					logger.info("in else");
					bean.setChkStatus(String.valueOf(dtlListObj[i][5]));
				}
				logger.info("String.valueOf(dtlListObj[i][2])   : "
						+ String.valueOf(dtlListObj[i][2]));
				bean.setChkDate(String.valueOf(dtlListObj[i][3]));
				bean.setChkComments(String.valueOf(dtlListObj[i][4]));
				reqDtlList.add(bean);
			}
			helpdesk.setReqDtlList(reqDtlList);
			helpdesk.setReqDtlListFlag("true");
		} else {
			// SET SLA LENGTH
			helpdesk.setReqDtlListFlag("false");
		}

	}

	public void getRecord(HelpDesk helpdesk) {
		try {
			Object[] param = new Object[1];
			param[0] = helpdesk.getHelpcode();
			// getquery(7) for selecting the status ,details,level
			Object applData[][] = getSqlModel().getSingleResult(getQuery(9),
					param);
			// 1) setting the status of the application 1)if level is 1 & status
			// will be set to value coming from database
			if (String.valueOf(applData[0][1]).equals("1")) {

				helpdesk.setStatus(String.valueOf(applData[0][0]));
				helpdesk.setHiddenStatus(String.valueOf(applData[0][0]));
			}// end of if
			// 2)if level is higher than 1 & application is pending status will
			// be set to "forwarded"

			else if (!(String.valueOf(applData[0][1]).equals("1"))
					&& String.valueOf(applData[0][0]).equals("P")) {
				helpdesk.setStatus("F");
				helpdesk.setHiddenStatus("F");
			}// end of else if

			// 3)if level is higher than 1 & application is not pending status
			// will be set to value coming from database
			else {
				helpdesk.setStatus(String.valueOf(applData[0][0]));
				helpdesk.setHiddenStatus(String.valueOf(applData[0][0]));
			}// end of else
			helpdesk.setLevel(Integer.parseInt(String.valueOf(applData[0][1])));
		} catch (Exception e) {
			logger.error("Exception in getRecord: " + e);
		}
		
	}

	public void displayIteratorValueForUploadProof(String[] srNo,
			String[] proofName, HelpDesk helpdesk) {
		try {
			ArrayList<HelpDesk> proofList = new ArrayList<HelpDesk>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					HelpDesk bean = new HelpDesk();
					bean.setProofName(proofName[i]);
					bean.setProofSrNo(srNo[i]);// sr no
					proofList.add(bean);
				}
				helpdesk.setProofList(proofList);

			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForUploadProof------"
							+ e);
		}
		
	}

	public void setProofList(String[] srNo, String[] proofName,
			HelpDesk helpdesk) {
		logger.info("in setProofList");
		try {
			HelpDesk bean = new HelpDesk();
			bean.setProofName(helpdesk.getUploadFileName());
			ArrayList<HelpDesk> proofList = displayNewValueProofList(
					srNo, proofName, helpdesk);
			bean.setProofSrNo(String.valueOf(proofList.size() + 1));// sr no
			proofList.add(bean);
			helpdesk.setProofList(proofList);
			helpdesk.setSetFilesFlag("true");
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}
		
	}

	private ArrayList<HelpDesk> displayNewValueProofList(String[] srNo,
			String[] proofName, HelpDesk helpdesk) {
		ArrayList<HelpDesk> proofList = null;
		logger.info("in displayNewValueProofList");
		try {
			proofList = new ArrayList<HelpDesk>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					HelpDesk bean = new HelpDesk();
					bean.setProofName(proofName[i]);
					bean.setProofSrNo(srNo[i]);
					proofList.add(bean);

				}
				helpdesk.setSetFilesFlag("true");
			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}
	
	public void removeUploadFile(String[] srNo, String[] proofName,
			HelpDesk helpdesk) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					HelpDesk bean = new HelpDesk();
					bean.setProofSrNo(String.valueOf(i + 1));
					bean.setProofName(proofName[i]);
					tableList.add(bean);
				}
				tableList.remove(Integer.parseInt(helpdesk
						.getCheckRemoveUpload()) - 1);
				helpdesk.setSetFilesFlag("true");
			}

			helpdesk.setProofList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile------" + e);
		}

	}

	public void getInitiatorDetails(HelpDesk helpdesk, String userEmpId) {
		try {
			String initiatorQuery = " SELECT  HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, CENTER_ID,"
					+ " NVL(CENTER_NAME,' ') AS BRANCH,DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' '),RANK_ID, NVL(RANK_NAME,' '), "
					+ " NVL(ADD_MOBILE,' '), NVL(ADD_EXTENSION,0), NVL(ADD_EMAIL,' ')"
					+ " FROM HRMS_EMP_OFFC"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID and ADD_TYPE='P')"
					+ " WHERE  HRMS_EMP_OFFC.EMP_ID= "
					+userEmpId;
			Object[][] initiatorDetail = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorDetail != null && initiatorDetail.length > 0){
				helpdesk.setEmpId(String.valueOf(initiatorDetail[0][0]));// posted by Id
				helpdesk.setEmpToken(String.valueOf(initiatorDetail[0][1]));//posted by token
				helpdesk.setEmpName(String.valueOf(initiatorDetail[0][2]));// posted by name
				helpdesk.setBranchCode(String.valueOf(initiatorDetail[0][3]));//branch code
				helpdesk.setBranchName(String.valueOf(initiatorDetail[0][4]));//branch name
				helpdesk.setDeptId(String.valueOf(initiatorDetail[0][5]));//dept Id
				helpdesk.setDeptName(String.valueOf(initiatorDetail[0][6]));//dept name
				helpdesk.setDesgCode(String.valueOf(initiatorDetail[0][7]));//designation Id
				helpdesk.setDesgName(String.valueOf(initiatorDetail[0][8]));//designation name
				helpdesk.setHiddenStatus("O");
				helpdesk.setDecodedReqStatus("Open");
				helpdesk.setReqEmpContactNo(String.valueOf(initiatorDetail[0][9]));
				helpdesk.setReqEmpExtensionNo(String.valueOf(initiatorDetail[0][10]));
				helpdesk.setReqEmpEmailId(String.valueOf(initiatorDetail[0][11]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void setActivityLogDetails(HelpDesk bean){
		String query="SELECT FROM_EMP.EMP_FNAME||' '||FROM_EMP.EMP_MNAME||' '||FROM_EMP.EMP_LNAME,TO_EMP.EMP_FNAME||' '||TO_EMP.EMP_MNAME||' '||TO_EMP.EMP_LNAME,"
				+" DECODE(HELPDESK_ACTIVITY_LOG.REQUEST_STATUS,'C','Closed','N','Rejected',HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME),TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH24:MI:SS'),"
				+"NVL(REQUEST_COMMENTS,'') FROM HELPDESK_ACTIVITY_LOG "
				+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV = REQUEST_STATUS)"
				+" LEFT JOIN HRMS_EMP_OFFC FROM_EMP  ON (FROM_EMP.EMP_ID=REQUEST_ACTION_BY)"
				+" LEFT JOIN HRMS_EMP_OFFC TO_EMP ON (TO_EMP.EMP_ID=REQUEST_FORWARDED_TO)"
				+"WHERE REQUEST_ID="+bean.getRequestId()+" ORDER BY REQUEST_DATE DESC";
	Object [][]logObj=getSqlModel().getSingleResult(query);
	if(logObj !=null && logObj.length>0){
		ArrayList<Object> logList=new ArrayList<Object>();
		for (int i = 0; i < logObj.length; i++) {
			HelpDesk beanList=new HelpDesk();
			beanList.setLogFromEmp(Utility.checkNull(String.valueOf(logObj[i][0])));
			beanList.setLogToEmp(Utility.checkNull(String.valueOf(logObj[i][1])));
			beanList.setLogStatus(Utility.checkNull(String.valueOf(logObj[i][2])));
			beanList.setLogDate(Utility.checkNull(String.valueOf(logObj[i][3])));
			beanList.setLogComments(Utility.checkNull(String.valueOf(logObj[i][4])));
			logList.add(beanList);
		}
		bean.setLogList(logList);
		bean.setLogLength("true");
	}
	}

	public boolean closeHelpdeskRequest(HelpDesk helpdesk) {
	boolean result=false;
	try {
		String updateQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_EMP_STATUS='C', REQUEST_APPL_STATUS='C' "
				+ " WHERE REQUEST_ID ="
				+helpdesk.getRequestId();
		result = getSqlModel().singleExecute(updateQuery);
		

		MypageProcessManagerAlertsModel	  processAlerts = new  MypageProcessManagerAlertsModel();
		  processAlerts.initiate(context, session);
		  processAlerts.removeProcessAlert(helpdesk.getRequestId(), "HelpDesk");
		  processAlerts.terminate();
		
		Object[][] insertObj = new Object[1][5];
		insertObj[0][0] = helpdesk.getRequestId();// REQUEST_ID
		insertObj[0][1] = helpdesk.getUserEmpId();// REQUEST_ACTION_BY
		insertObj[0][2] = "0";/// REQUEST_FORWARDED_TO
		insertObj[0][3] = "C";// REQUEST_APPL_STATUS
		insertObj[0][4] = helpdesk.getReopenComments();// REQUEST_COMMENTS
		
		String insertLog = " INSERT INTO HELPDESK_ACTIVITY_LOG (REQUEST_ID, REQUEST_ACTION_BY, REQUEST_FORWARDED_TO, "
				+ " REQUEST_STATUS, REQUEST_DATE, REQUEST_COMMENTS) VALUES(?, ?, ?, ?, SYSDATE, ?)";
		
		getSqlModel().singleExecute(insertLog, insertObj);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	}

	public void getAllTypeOfApplications(HelpDesk helpdesk,
			HttpServletRequest request, String userEmpId) {
		// TODO Auto-generated method stub
		
	}

	public boolean withdrawApplication(HelpDesk helpdesk) {
		// TODO Auto-generated method stub
			boolean result = false;
			try {
				String sqlquery = "SELECT REQUEST_ID,REQUEST_APPL_STATUS FROM HELPDESK_REQUEST_HDR  WHERE REQUEST_APPL_STATUS = 'P' AND PURCHASE_LEVEL = '1' AND PURCHASE_CODE = "
						+ helpdesk.getHelpcode();
				Object[][] obj = getSqlModel().getSingleResult(sqlquery);
				if (obj.length > 0) {
					String query = " UPDATE HELPDESK_REQUEST_HDR SET REQUEST_APPL_STATUS = 'W' , REQUEST_APPL_LEVEL = '1' WHERE REQUEST_ID =  "
							+ helpdesk.getHelpcode();
					result = getSqlModel().singleExecute(query);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;
	
	}


}
