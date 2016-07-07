package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.NewHireRehire;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;


public class NewHireRehireModel extends ModelBase {
		static org.apache.log4j.Logger logger = org.apache.log4j.Logger
		.getLogger(NewHireRehireModel.class);
		
	
		public void getPendingList(NewHireRehire bean,
				HttpServletRequest request, String userId) {
			try {
				Object[][] draftListData = null;
				ArrayList draftvoucherList = new ArrayList();
				
				Object[][] inProcessListData = null;
				ArrayList inProcessVoucherList = new ArrayList();
				
				Object[][] sentBackListData = null;
				ArrayList sentBackVoucherList = new ArrayList();
				
				// For drafted application Begins
				String selQuery = " SELECT TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
									+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition')" 
									+ " FROM HRMS_D1_NEW_HIRE_REHIRE"
									+ " WHERE HIRE_STATUS = 'D' AND HIRE_INITIATOR = "+ bean.getUserEmpId()+" ORDER BY HIRE_REHIRE_ID DESC "; 
				draftListData = getSqlModel().getSingleResult(selQuery);
				
				String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(),
						draftListData.length, 20);
				if (pageIndexDrafted == null) {
					pageIndexDrafted[0] = "0";
					pageIndexDrafted[1] = "20";
					pageIndexDrafted[2] = "1";
					pageIndexDrafted[3] = "1";
					pageIndexDrafted[4] = "";
				}
				
				request.setAttribute("totalDraftPage", Integer.parseInt(String
						.valueOf(pageIndexDrafted[2])));
				request.setAttribute("draftPageNo", Integer.parseInt(String
						.valueOf(pageIndexDrafted[3])));
				if (pageIndexDrafted[4].equals("1"))
					bean.setMyPage("1");
				
				if (draftListData != null && draftListData.length > 0) {
					bean.setDraftVoucherListLength(true);
					for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
					.parseInt(pageIndexDrafted[1]); i++) {
						NewHireRehire beanItt = new NewHireRehire();
						
						beanItt.setTrackingNo(checkNull(String
								.valueOf(draftListData[i][0])));
						beanItt.setEmployeeName(checkNull(String
								.valueOf(draftListData[i][1])));
						
						beanItt.setApplicationDate(checkNull(String
								.valueOf(draftListData[i][2])));
						
						beanItt.setHireRehireId(checkNull(String
								.valueOf(draftListData[i][3])));
						
						beanItt.setActionReasonItr(checkNull(String
								.valueOf(draftListData[i][4])));
						draftvoucherList.add(beanItt);
					}
					bean.setDraftVoucherIteratorList(draftvoucherList);
				}
				// For drafted application Ends
				
				// For in-Process application Begins
				String inProcessQuery = " select TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
					+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE"
					+ " WHERE HIRE_STATUS = 'P' AND HIRE_INITIATOR = "+ bean.getUserEmpId()+" ORDER BY HIRE_REHIRE_ID DESC "; 
				inProcessListData = getSqlModel().getSingleResult(inProcessQuery);
				
				String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(),
						inProcessListData.length, 20);
				if (pageIndexDrafted == null) {
					pageIndexInProcess[0] = "0";
					pageIndexInProcess[1] = "20";
					pageIndexInProcess[2] = "1";
					pageIndexInProcess[3] = "1";
					pageIndexInProcess[4] = "";
				}
				
				request.setAttribute("totalInProcessPage", Integer.parseInt(String
						.valueOf(pageIndexInProcess[2])));
				request.setAttribute("inProcessPageNo", Integer.parseInt(String
						.valueOf(pageIndexInProcess[3])));
				if (pageIndexInProcess[4].equals("1"))
					bean.setMyPageInProcess("1");
				
				if (inProcessListData != null && inProcessListData.length > 0) {
					bean.setInProcessVoucherListLength(true);
					for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer
					.parseInt(pageIndexInProcess[1]); i++) {
						NewHireRehire beanItt = new NewHireRehire();
						beanItt.setTrackingNo(checkNull(String
								.valueOf(inProcessListData[i][0])));
						beanItt.setEmployeeName(checkNull(String
								.valueOf(inProcessListData[i][1])));
						
						beanItt.setApplicationDate(checkNull(String
								.valueOf(inProcessListData[i][2])));
						
						beanItt.setHireRehireId(checkNull(String
								.valueOf(inProcessListData[i][3])));
						
						beanItt.setActionReasonItr(checkNull(String
								.valueOf(inProcessListData[i][4])));
						inProcessVoucherList.add(beanItt);
					}
					bean.setInProcessVoucherIteratorList(inProcessVoucherList);
				}
				// For in-Process application Ends
				
				
				// Sent-Back application Begins
				String sentBackQuery = " select TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
					+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE"
					+ " WHERE HIRE_STATUS = 'B' AND HIRE_INITIATOR = "+ bean.getUserEmpId()+" ORDER BY HIRE_REHIRE_ID DESC "; 
				sentBackListData = getSqlModel().getSingleResult(sentBackQuery);
				
				String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(),
						sentBackListData.length, 20);
				if (pageIndexSentBack == null) {
					pageIndexSentBack[0] = "0";
					pageIndexSentBack[1] = "20";
					pageIndexSentBack[2] = "1";
					pageIndexSentBack[3] = "1";
					pageIndexSentBack[4] = "";
				}
				
				request.setAttribute("totalSentBackPage", Integer.parseInt(String
						.valueOf(pageIndexSentBack[2])));
				request.setAttribute("sentBackPageNo", Integer.parseInt(String
						.valueOf(pageIndexSentBack[3])));
				if (pageIndexSentBack[4].equals("1"))
					bean.setMyPageSentBack("1");
				
				if (sentBackListData != null && sentBackListData.length > 0) {
					bean.setSentBackVoucherListLength(true);
					for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer
					.parseInt(pageIndexSentBack[1]); i++) {
						NewHireRehire beanItt = new NewHireRehire();
						beanItt.setTrackingNo(checkNull(String
								.valueOf(sentBackListData[i][0])));
						beanItt.setEmployeeName(checkNull(String
								.valueOf(sentBackListData[i][1])));
						
						beanItt.setApplicationDate(checkNull(String
								.valueOf(sentBackListData[i][2])));
						
						beanItt.setHireRehireId(checkNull(String
								.valueOf(sentBackListData[i][3])));
						
						beanItt.setActionReasonItr(checkNull(String
								.valueOf(sentBackListData[i][4])));
						sentBackVoucherList.add(beanItt);
					}
					bean.setSentBackVoucherIteratorList(sentBackVoucherList);
				}
				// Sent-Back application Ends
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void getApproverCommentList(NewHireRehire bean, String requestID) {
			String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, HIRE_COMMENTS, "
				+ " TO_CHAR(HIRE_APPROVED_DATE, 'DD-MM-YYYY') AS HIRE_APPROVED_DATE, "
				+ " DECODE(HIRE_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
				+ " FROM HRMS_D1_NEW_HIRE_DATA_PATH " 
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_NEW_HIRE_DATA_PATH.HIRE_APPROVER) "
				+ " WHERE HIRE_APPLICATION_ID = " + requestID + " ORDER BY HIRE_APPLICATION_ID DESC";
			
			Object[][] apprCommentListObj = getSqlModel().getSingleResult(apprCommentListSql);
			ArrayList approverList = new ArrayList<Object>(apprCommentListObj.length);
			if(apprCommentListObj !=null && apprCommentListObj.length>0)
			{
				bean.setApproverCommentsFlag(true);
				for(int i = 0; i < apprCommentListObj.length; i++) {
					NewHireRehire innerBean = new NewHireRehire();
					innerBean.setApprName(checkNull(String.valueOf(apprCommentListObj[i][0])));
					innerBean.setApprComments(checkNull(String.valueOf(apprCommentListObj[i][1])));
					innerBean.setApprDate(checkNull(String.valueOf(apprCommentListObj[i][2])));
					innerBean.setApprStatus(checkNull(String.valueOf(apprCommentListObj[i][3])));
					approverList.add(innerBean);
				}
				bean.setApproverCommentList(approverList);
			}
		}
		

		public void getSystemDate(NewHireRehire reqbean) {
			try {
				String systemDateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
				Object[][] systemDateObject = getSqlModel().getSingleResult(systemDateQuery);
				if(systemDateObject !=null && systemDateObject.length>0)
				{
					reqbean.setApplicationDate(checkNull(String.valueOf(systemDateObject[0][0])));
				}
				
				String fromNameQuery = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID ="+reqbean.getUserEmpId();
				Object[][] fromNameObject = getSqlModel().getSingleResult(fromNameQuery);
				if(fromNameObject !=null && fromNameObject.length>0)
				{
					reqbean.setEmployeeID(checkNull(String.valueOf(fromNameObject[0][0])));
					reqbean.setEmployeeToken(checkNull(String.valueOf(fromNameObject[0][1])));
					reqbean.setFromName(checkNull(String.valueOf(fromNameObject[0][2])));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		public String checkNull(String result) {
			if (result == null || result.equals("") || result.equals("null")) {
				return "";
			} else {
				return result;
			}
		}



		public boolean draftFunction(NewHireRehire reqbean) {
			boolean result = false;
			System.out.println("Status : "+reqbean.getHireStatus());
			try {
				Object addObj[][] = new Object[1][55];
				addObj[0][0] = reqbean.getEmpFirstName();
				addObj[0][1] = reqbean.getEmpMiddleName();
				addObj[0][2] = reqbean.getEmpLastName();
				addObj[0][3] = reqbean.getSocialSecurityNumber();
				addObj[0][4] = reqbean.getSocialInsuranceNumber();
				addObj[0][5] = reqbean.getEmpHomeAddress();
				addObj[0][6] = reqbean.getCityName();
				
				addObj[0][7] = reqbean.getStateName();
				addObj[0][8] = reqbean.getCountry();
				
				addObj[0][9] = reqbean.getZip();
				
				
				addObj[0][10] = reqbean.getHomePhoneNumber();
				addObj[0][11] = reqbean.getReqNumber();
				addObj[0][12] = reqbean.getSex();
				addObj[0][13] = reqbean.getMaritalStatus();
				addObj[0][14] = reqbean.getQualCode();
				addObj[0][15] = reqbean.getBirthDate();
				addObj[0][16] = reqbean.getMediumCode();
				addObj[0][17] = reqbean.getCastCode();
				addObj[0][18] = reqbean.getHireDate();
				
				addObj[0][19] = reqbean.getActionReason();
				System.out.println("reqbean.getActionReason()===" + reqbean.getActionReason());
				addObj[0][20] = reqbean.getJobCode();
				addObj[0][21] = reqbean.getJobTitle();
				addObj[0][22] = reqbean.getAcquisitionDate();
				addObj[0][23] = reqbean.getAcquiredCompany();
				addObj[0][24] = reqbean.getPreacqusitionDate();
				System.out.println("reqbean.getUserProfileRadioOptionValue()========" + reqbean.getUserProfileRadioOptionValue());
						if (reqbean.getUserProfileRadioOptionValue().equals("Ho")) {
					addObj[0][25] = "H";
				} else 
					if (reqbean.getUserProfileRadioOptionValue().equals("Tr"))
				{
					addObj[0][25] = "T";
				}
					else 
						if (reqbean.getUserProfileRadioOptionValue().equals("Cu"))
					{
						addObj[0][25] = "C";
					}
						else 
							if (reqbean.getUserProfileRadioOptionValue().equals("De"))
						{
							addObj[0][25] = "D";
						}
							else 
								if (reqbean.getUserProfileRadioOptionValue().equals("Va"))
							{
								addObj[0][25] = "V";
							}
					else
					{
						addObj[0][25] = "";
					}
				addObj[0][26] = reqbean.getShiftCode();
				addObj[0][27] = reqbean.getRegTemp();
				addObj[0][28] = reqbean.getFlsaStatus();
					addObj[0][29] = reqbean.getCustomerName();
				addObj[0][30] = reqbean.getPhysicalAddress();
				addObj[0][31] = reqbean.getCustCityName();
				addObj[0][32] = reqbean.getCustStateName();
				addObj[0][33] = reqbean.getCustZipCode();
				addObj[0][34] = reqbean.getCustShiftCode();
				addObj[0][35] = reqbean.getCustRegTemp();
				addObj[0][36] = reqbean.getCustflsaStatus();
				
				/*addObj[0][37] = reqbean.getDecisionPhysicalAddress();
				addObj[0][38] = reqbean.getDecisionCityId();
				addObj[0][39] = reqbean.getDecisionStateName();
				addObj[0][40] = reqbean.getDecisionzip();
				addObj[0][41] = reqbean.getDecisionShiftCode();
				addObj[0][42] = reqbean.getRegTempDecision();
				addObj[0][43] = reqbean.getDecisionflsaStatus();*/
				
			//	addObj[0][44] = reqbean.getSalaryPlan();
				String salaryPlan = "GBR";
				if (!reqbean.getCustZipCode().equals("")) {
					salaryPlan = reqbean.getSalaryPlan();
				} /*else {
					salaryPlan = reqbean.getSalaryPlan('salaryPlan');
				}*/
				addObj[0][37] =  salaryPlan;
				
				
				addObj[0][38] = reqbean.getPayGroup();
				addObj[0][39] = reqbean.getCadreCode();
				addObj[0][40] = reqbean.getWeeklyHours();
				addObj[0][41] = reqbean.getBiweeklySalary();
				addObj[0][42] = reqbean.getAnnualSalary();
				addObj[0][43] = reqbean.getDeptCode();
				addObj[0][44] = reqbean.getExecutiveCode();
				addObj[0][45] = reqbean.getOfficeCityName();
				addObj[0][46] = reqbean.getOfficeStateName();
				addObj[0][47] = reqbean.getApproverCode();
				addObj[0][48] = reqbean.getHireStatus();
				
				String autoCode = "";
				String selQuery = "SELECT NVL(MAX(HIRE_REHIRE_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(HIRE_REHIRE_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_NEW_HIRE_REHIRE	";
				Object[][] selData = getSqlModel().getSingleResult(selQuery);
				if (reqbean.getRequestID().equals("")) {			
					autoCode = String.valueOf(selData[0][0]);
					System.out.println("autoCode==="+ autoCode);
					reqbean.setRequestID(autoCode);			
					/**
						 * SET TRACKING NO
						 */String qq="SELECT NVL(MAX(HIRE_REHIRE_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(HIRE_REHIRE_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_NEW_HIRE_REHIRE	";
							Object[][]obj=getSqlModel().getSingleResult(qq);
							if(obj !=null && obj.length>0){			
							try {
								ApplCodeTemplateModel model = new ApplCodeTemplateModel();
								model.initiate(context, session);
								String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]), "D1-HIRE", reqbean.getUserEmpId(),String.valueOf(obj[0][2]));
								reqbean.setTrackingNo(checkNull(applnCode));
								System.out.println("applnCode%%%%%" + applnCode);
								model.terminate();
							} catch (Exception e) {
								// TODO: handle exception
							}
							}			
				}
				
				addObj[0][49] = reqbean.getTrackingNo();
				addObj[0][50] = reqbean.getInitiatorCode();
			//	addObj[0][47] = reqbean.getInitiatorDate();
				addObj[0][51] = reqbean.getExeEmployeeCode();
				addObj[0][52] = reqbean.getFulltimeParttime();
				addObj[0][53] = reqbean.getCustfulltimeParttime();
				addObj[0][54] = reqbean.getEmailAddress().trim();//Addded for email Address by nilesh on 13th Dec 11
				
				
				String insertQuery = "INSERT INTO HRMS_D1_NEW_HIRE_REHIRE"
					+ "(HIRE_REHIRE_ID, EMP_FIRST_NAME, EMP_MIDDLE_NAME, EMP_LAST_NAME , " +
							" EMP_SOCIAL_SECRTY_NUMBER, EMP_SOCIAL_INSURANCE_NUMBER , " +
							" EMP_ADDRESS, EMP_CITY,EMP_STATE, EMP_COUNTRY, EMP_PIN_CODE , " +
							" EMP_HOME_PHONE, EMP_REQ_NUMBER" +
							" , EMP_SEX, EMP_MARITAL_STATUS , " +
							" EMP_EDUCATION , EMP_BIRTH_DATE , EMP_REFFERAL_SOURCE , EMP_ETHNIC_GRP , " +
							" HIRE_DATE, ACTION_REASON,JOB_CODE, JOB_TITLE, ACQUISITION_DATE, ACQUIRED_COMPANY, PREACQUSITION_DATE "+
							", PHY_WRK_LOCATION, HOME_SHIFT_CODE, " +
							" HOME_REG_TEMP, HOME_FLSA_STATUS , CUSTOMER_NAME, CUST_PHY_ADDRESS, " +
							" CUST_CITY,CUST_STATE, CUST_PIN_CODE, CUST_SHIFT_CODE, CUST_REG_TEMP," +
							" CUST_FLSA_STATUS , " +
							" SALARY_PLAN, PAY_GROUP, GRADE, WEEKLY_HOURS, BIWEEKLY_SALARY, ANNUAL_SALARY, " +
							" DEPT_CODE, EXECUTIVE_CODE, OFFICE_CITY,OFFICE_STATE, HIRE_APPROVER, HIRE_STATUS,TRACKING_NUMBER,HIRE_INITIATOR, HIRE_INITIATOR_DATE,EXE_EMP_CODE,FULL_PART_TIME,CUST_FULL_PART_TIME, HIRE_EMAIL_ADD )"
					+ " VALUES((SELECT NVL(MAX(HIRE_REHIRE_ID),0)+1 FROM HRMS_D1_NEW_HIRE_REHIRE),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?)";
				/*,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/

				
				result = getSqlModel().singleExecute(insertQuery, addObj);
				
				
				if (result) {
					String autoCodeQuery = " SELECT NVL(MAX(HIRE_REHIRE_ID),0) FROM HRMS_D1_NEW_HIRE_REHIRE ";
					Object[][] data = getSqlModel().getSingleResult(
							autoCodeQuery);
					if (data != null && data.length > 0) {
						reqbean.setRequestID(String.valueOf(data[0][0]));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}



		public boolean updateRecords(NewHireRehire reqbean) {

			boolean result=false;
			System.out.println("Status : "+reqbean.getHireStatus());
			try {
				Object updateObj[][] = new Object[1][54];
				updateObj[0][0] = reqbean.getEmpFirstName();
				updateObj[0][1] = reqbean.getEmpMiddleName();
				updateObj[0][2] = reqbean.getEmpLastName();
				updateObj[0][3] = reqbean.getSocialSecurityNumber();
				updateObj[0][4] = reqbean.getSocialInsuranceNumber();
				updateObj[0][5] = reqbean.getEmpHomeAddress();
				updateObj[0][6] = reqbean.getCityName();
				updateObj[0][7] = reqbean.getStateName();
				updateObj[0][8] = reqbean.getCountry();
				
				updateObj[0][9] = reqbean.getZip();
				
				
				updateObj[0][10] = reqbean.getHomePhoneNumber();
				updateObj[0][11] = reqbean.getReqNumber();
				updateObj[0][12] = reqbean.getSex();
				updateObj[0][13] = reqbean.getMaritalStatus();
				updateObj[0][14] = reqbean.getQualCode();
				updateObj[0][15] = reqbean.getBirthDate();
				updateObj[0][16] = reqbean.getMediumCode();
				updateObj[0][17] = reqbean.getCastCode();
				updateObj[0][18] = reqbean.getHireDate();
				
				updateObj[0][19] = reqbean.getActionReason();
				updateObj[0][20] = reqbean.getJobCode();
				updateObj[0][21] = reqbean.getJobTitle();
				updateObj[0][22] = reqbean.getAcquisitionDate();
				updateObj[0][23] = reqbean.getAcquiredCompany();
				updateObj[0][24] = reqbean.getPreacqusitionDate();
				System.out.println("reqbean.getUserProfileRadioOptionValue()========" + reqbean.getUserProfileRadioOptionValue());
						if (reqbean.getUserProfileRadioOptionValue().equals("Ho")) {
					updateObj[0][25] = "H";
				} else 
					if (reqbean.getUserProfileRadioOptionValue().equals("Tr"))
				{
					updateObj[0][25] = "T";
				}
					else 
						if (reqbean.getUserProfileRadioOptionValue().equals("Cu"))
					{
						updateObj[0][25] = "C";
					}
						else 
							if (reqbean.getUserProfileRadioOptionValue().equals("De"))
						{
							updateObj[0][25] = "D";
						}
							else 
								if (reqbean.getUserProfileRadioOptionValue().equals("Va"))
							{
								updateObj[0][25] = "V";
							}
					else
					{
						updateObj[0][25] = "";
					}
				updateObj[0][26] = reqbean.getShiftCode();
				updateObj[0][27] = reqbean.getRegTemp();
				updateObj[0][28] = reqbean.getFlsaStatus();
					updateObj[0][29] = reqbean.getCustomerName();
				updateObj[0][30] = reqbean.getPhysicalAddress();
				updateObj[0][31] = reqbean.getCustCityName();
				updateObj[0][32] = reqbean.getCustStateName();
				updateObj[0][33] = reqbean.getCustZipCode();
				updateObj[0][34] = reqbean.getCustShiftCode();
				updateObj[0][35] = reqbean.getCustRegTemp();
				updateObj[0][36] = reqbean.getCustflsaStatus();
				
				/*updateObj[0][37] = reqbean.getDecisionPhysicalAddress();
				updateObj[0][38] = reqbean.getDecisionCityId();
				updateObj[0][39] = reqbean.getDecisionStateName();
				updateObj[0][40] = reqbean.getDecisionzip();
				updateObj[0][41] = reqbean.getDecisionShiftCode();
				updateObj[0][42] = reqbean.getRegTempDecision();
				updateObj[0][43] = reqbean.getDecisionflsaStatus();*/
				
			//	updateObj[0][44] = reqbean.getSalaryPlan();
				String salaryPlan = "GBR";
				if (!reqbean.getCustZipCode().equals("")) {
					salaryPlan = reqbean.getSalaryPlan();
				} /*else {
					salaryPlan = reqbean.getSalaryPlan('salaryPlan');
				}*/
				updateObj[0][37] =  salaryPlan;
				
				
				updateObj[0][38] = reqbean.getPayGroup();
				updateObj[0][39] = reqbean.getCadreCode();
				updateObj[0][40] = reqbean.getWeeklyHours();
				updateObj[0][41] = reqbean.getBiweeklySalary();
				updateObj[0][42] = reqbean.getAnnualSalary();
				updateObj[0][43] = reqbean.getDeptCode();
				updateObj[0][44] = reqbean.getExecutiveCode();
				updateObj[0][45] = reqbean.getOfficeCityName();
				updateObj[0][46] = reqbean.getOfficeStateName();
				updateObj[0][47] = reqbean.getApproverCode();
				updateObj[0][48] = reqbean.getHireStatus();
				updateObj[0][49] = reqbean.getExeEmployeeCode();
				updateObj[0][50] = reqbean.getFulltimeParttime();
				updateObj[0][51] = reqbean.getCustfulltimeParttime();
				updateObj[0][52] = reqbean.getEmailAddress().trim();
				updateObj[0][53] = reqbean.getRequestID();
				
				
				String insertQuery = "UPDATE HRMS_D1_NEW_HIRE_REHIRE SET "
					+ " EMP_FIRST_NAME = ? , EMP_MIDDLE_NAME = ? , EMP_LAST_NAME = ?  , " +
							" EMP_SOCIAL_SECRTY_NUMBER = ? , EMP_SOCIAL_INSURANCE_NUMBER = ? , " +
							" EMP_ADDRESS = ? , EMP_CITY = ? , EMP_STATE = ? , EMP_COUNTRY = ? , EMP_PIN_CODE  = ? , " +
							" EMP_HOME_PHONE = ? , EMP_REQ_NUMBER = ? " +
							" , EMP_SEX = ? , EMP_MARITAL_STATUS = ?  , " +
							" EMP_EDUCATION  = ? , EMP_BIRTH_DATE = to_date(?,'DD-MM-YYYY')   , EMP_REFFERAL_SOURCE  = ? , EMP_ETHNIC_GRP  = ? , " +
							" HIRE_DATE = to_date(?,'DD-MM-YYYY')  , ACTION_REASON = ? ,JOB_CODE = ? , JOB_TITLE = ? , ACQUISITION_DATE = to_date(?,'DD-MM-YYYY')  , ACQUIRED_COMPANY = ? , PREACQUSITION_DATE  = to_date(?,'DD-MM-YYYY')  "+
							", PHY_WRK_LOCATION = ? , HOME_SHIFT_CODE = ? , " +
							" HOME_REG_TEMP = ? , HOME_FLSA_STATUS  = ? , CUSTOMER_NAME = ? , CUST_PHY_ADDRESS = ? , " +
							" CUST_CITY = ? , CUST_STATE = ? , CUST_PIN_CODE = ? , CUST_SHIFT_CODE = ? , CUST_REG_TEMP = ? ," +
							" CUST_FLSA_STATUS = ?  ,  " +
							" SALARY_PLAN = ? , PAY_GROUP = ? , GRADE = ? , WEEKLY_HOURS = ? , BIWEEKLY_SALARY = ? , ANNUAL_SALARY = ? , " +
							" DEPT_CODE = ? , EXECUTIVE_CODE = ? , OFFICE_CITY = ? , OFFICE_STATE = ? , HIRE_APPROVER = ? , HIRE_STATUS = ? , EXE_EMP_CODE = ? ,FULL_PART_TIME=?, CUST_FULL_PART_TIME =? , HIRE_EMAIL_ADD =? WHERE HIRE_REHIRE_ID=?";
				
				result = getSqlModel().singleExecute(insertQuery, updateObj);
					
				
				
			}	
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return result;
		}




		public void editApplicationFunction(NewHireRehire reqbean,
				String requestID) {
			try {
				String editQuery = "SELECT HIRE_REHIRE_ID, EMP_FIRST_NAME, EMP_MIDDLE_NAME, EMP_LAST_NAME, "
									+ " EMP_SOCIAL_SECRTY_NUMBER, EMP_SOCIAL_INSURANCE_NUMBER, EMP_ADDRESS,  "
									+ " EMP_CITY, EMP_STATE, EMP_COUNTRY, EMP_PIN_CODE,  "
									+ " EMP_HOME_PHONE, EMP_REQ_NUMBER, EMP_SEX, EMP_MARITAL_STATUS,  "
									+ " EMP_EDUCATION,QUA_NAME, TO_CHAR(EMP_BIRTH_DATE,'DD-MM-YYYY'), EMP_REFFERAL_SOURCE,REFERRAL_SOURCE_NAME,  "
									+ " EMP_ETHNIC_GRP, ETHINICITY AS ETHNIC_NAME , TO_CHAR(HIRE_DATE,'DD-MM-YYYY'), ACTION_REASON, JOB_CODE, JOB_TITLE," +
											" TO_CHAR(ACQUISITION_DATE,'DD-MM-YYYY'), ACQUIRED_COMPANY,TO_CHAR(PREACQUSITION_DATE,'DD-MM-YYYY'), PHY_WRK_LOCATION,  "
									+ " HOME_SHIFT_CODE, HOME_SHIFT.SHIFT_NAME AS HOME_SHIFT_NAME ,  "
									+ " HOME_REG_TEMP, HOME_FLSA_STATUS, CUSTOMER_NAME, CUST_PHY_ADDRESS,  "
									+ " CUST_CITY, CUST_STATE, CUST_PIN_CODE, CUST_SHIFT_CODE," +
											"CUST_SHIFT.SHIFT_NAME AS CUST_SHIFT_NAME , CUST_REG_TEMP,  "
									+ " CUST_FLSA_STATUS,  "
									+ " HRMS_D1_NEW_HIRE_REHIRE.SALARY_PLAN, PAY_GROUP, GRADE,CADRE_NAME, WEEKLY_HOURS, BIWEEKLY_SALARY, ANNUAL_SALARY, DEPT_CODE,  "
									+ " EXECUTIVE_CODE,RANK_NAME, OFFICE_CITY, OFFICE_STATE, " 
											/*"HIRE_APPROVER,OFFC1.EMP_TOKEN AS APPROVER_TOKEN,   "
									+ " OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,   "*/
									+ "  TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , HIRE_STATUS,HIRE_LEVEL,TRACKING_NUMBER ,HIRE_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(HIRE_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') "
									+ " , NVL(HRMS_DEPT.DEPT_NAME||' - '||DEPT_ABBR, ' '),EXE_EMP_CODE, EXECUTIVE.EMP_TOKEN, EXECUTIVE.EMP_FNAME || ' ' || EXECUTIVE.EMP_MNAME || ' ' || EXECUTIVE.EMP_LNAME AS ENAME, FULL_PART_TIME, CUST_FULL_PART_TIME , HIRE_EMAIL_ADD from HRMS_D1_NEW_HIRE_REHIRE "
									+ " LEFT JOIN HRMS_SHIFT HOME_SHIFT ON(HOME_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.HOME_SHIFT_CODE) "
									//+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.EMP_CITY) "
									+ " LEFT JOIN HRMS_SHIFT SHIFT ON(SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_SHIFT_CODE) "
								//	+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.HIRE_APPROVER) "
									+ " LEFT JOIN HRMS_QUA ON (HRMS_QUA.QUA_ID = HRMS_D1_NEW_HIRE_REHIRE.EMP_EDUCATION) " 
									+ " LEFT JOIN HRMS_D1_REFERRAL on (HRMS_D1_REFERRAL.REFERRAL_ID =HRMS_D1_NEW_HIRE_REHIRE.EMP_REFFERAL_SOURCE) "
								//	+ " LEFT JOIN HRMS_LOCATION CUST_CITY ON(CUST_CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.CUST_CITY) "
									//+ " LEFT JOIN HRMS_LOCATION DECISION_CITY ON(DECISION_CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.DECISION_CITY) "
									//+ " LEFT JOIN HRMS_SHIFT DECISION_SHIFT ON(DECISION_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.DECISION_SHIFT_CODE) "
									+ " LEFT JOIN HRMS_SHIFT CUST_SHIFT ON(CUST_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_SHIFT_CODE) "
									+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID =HRMS_D1_NEW_HIRE_REHIRE.GRADE) "
								//	+ " LEFT JOIN HRMS_LOCATION OFFICE_CITY ON(OFFICE_CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.OFFICE_CITY) "
									//+ " LEFT JOIN HRMS_CAST ON (HRMS_CAST.CAST_ID = HRMS_D1_NEW_HIRE_REHIRE.EMP_ETHNIC_GRP) "
								//	+ " LEFT JOIN HRMS_D1_HIRE_ZIP ON (HRMS_D1_HIRE_ZIP.ZIP_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_PIN_CODE)  "
									+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_NEW_HIRE_REHIRE.EXECUTIVE_CODE) "
									+ " LEFT JOIN HRMS_D1_ETHIC ON (HRMS_D1_ETHIC.ETHIC_CODE = HRMS_D1_NEW_HIRE_REHIRE.EMP_ETHNIC_GRP) "
									+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.HIRE_INITIATOR)  "
									+ " LEFT JOIN HRMS_EMP_OFFC EXECUTIVE ON(EXECUTIVE.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.EXE_EMP_CODE)  "
									+" LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=HRMS_D1_NEW_HIRE_REHIRE.DEPT_CODE )"
								   + "  WHERE HIRE_REHIRE_ID = "+requestID;
				Object[][] editObj = getSqlModel().getSingleResult(editQuery);
				if(editObj!=null && editObj.length>0)
				{
					reqbean.setRequestID(checkNull(String.valueOf(editObj[0][0])));
					reqbean.setEmpFirstName(checkNull(String.valueOf(editObj[0][1])));
					reqbean.setEmpMiddleName(checkNull(String.valueOf(editObj[0][2])));
					reqbean.setEmpLastName(checkNull(String.valueOf(editObj[0][3])));
					reqbean.setSocialSecurityNumber(checkNull(String.valueOf(editObj[0][4])));
					reqbean.setSocialInsuranceNumber(checkNull(String.valueOf(editObj[0][5])));
					reqbean.setEmpHomeAddress(checkNull(String.valueOf(editObj[0][6])));
					//reqbean.setCityId(checkNull(String.valueOf(editObj[0][7])));
					reqbean.setCityName(checkNull(String.valueOf(editObj[0][7])));
					
					reqbean.setStateName(checkNull(String.valueOf(editObj[0][8])));
					reqbean.setCountry(checkNull(String.valueOf(editObj[0][9])));
					reqbean.setZip(checkNull(String.valueOf(editObj[0][10])));
					reqbean.setHomePhoneNumber(checkNull(String.valueOf(editObj[0][11])));
					reqbean.setReqNumber(checkNull(String.valueOf(editObj[0][12])));
					reqbean.setSex(checkNull(String.valueOf(editObj[0][13])));
					reqbean.setMaritalStatus(checkNull(String.valueOf(editObj[0][14])));
					reqbean.setQualCode(checkNull(String.valueOf(editObj[0][15])));
					reqbean.setQualifyName(checkNull(String.valueOf(editObj[0][16])));
					reqbean.setBirthDate(checkNull(String.valueOf(editObj[0][17])));
					reqbean.setMediumCode(checkNull(String.valueOf(editObj[0][18])));
					reqbean.setMediumName(checkNull(String.valueOf(editObj[0][19])));
					reqbean.setCastCode(checkNull(String.valueOf(editObj[0][20])));
					reqbean.setCastName(checkNull(String.valueOf(editObj[0][21])));
					reqbean.setHireDate(checkNull(String.valueOf(editObj[0][22])));
					reqbean.setActionReason(checkNull(String.valueOf(editObj[0][23])));
					
					reqbean.setJobCode(checkNull(String.valueOf(editObj[0][24])));
					reqbean.setJobTitle(checkNull(String.valueOf(editObj[0][25])));
					reqbean.setAcquisitionDate(checkNull(String.valueOf(editObj[0][26])));
					reqbean.setAcquiredCompany(checkNull(String.valueOf(editObj[0][27])));
					reqbean.setPreacqusitionDate(checkNull(String.valueOf(editObj[0][28])));
					//reqbean.setUserProfile(checkNull(String.valueOf(editObj[0][30])));
					
					if (String.valueOf(editObj[0][29]).equals("H")) {
						reqbean.setUserProfile("Ho");
						reqbean.setUserProfileRadioOptionValue("Ho");
					} else if (String.valueOf(editObj[0][29]).equals("T")) {
						reqbean.setUserProfile("Tr");
						reqbean.setUserProfileRadioOptionValue("Tr");
					} else if (String.valueOf(editObj[0][29]).equals("C")) {
						reqbean.setUserProfile("Cu");
						reqbean.setUserProfileRadioOptionValue("Cu");
					} else if (String.valueOf(editObj[0][29]).equals("D")) {
						reqbean.setUserProfile("De");
						reqbean.setUserProfileRadioOptionValue("De");
					} else if (String.valueOf(editObj[0][29]).equals("V")) {
						reqbean.setUserProfile("Va");
						reqbean.setUserProfileRadioOptionValue("Va");
					} else {
						reqbean.setUserProfile("");
						reqbean.setUserProfileRadioOptionValue("");
					}
					reqbean.setShiftCode(checkNull(String.valueOf(editObj[0][30])));
					reqbean.setShiftType(checkNull(String.valueOf(editObj[0][31])));
					reqbean.setRegTemp(checkNull(String.valueOf(editObj[0][32])));
					reqbean.setFlsaStatus(checkNull(String.valueOf(editObj[0][33])));
					reqbean.setCustomerName(checkNull(String.valueOf(editObj[0][34])));
					reqbean.setPhysicalAddress(checkNull(String.valueOf(editObj[0][35])));
					//reqbean.setCustCityId(checkNull(String.valueOf(editObj[0][37])));
					reqbean.setCustCityName(checkNull(String.valueOf(editObj[0][36])));
					reqbean.setCustStateName(checkNull(String.valueOf(editObj[0][37])));
					reqbean.setCustZipCode(checkNull(String.valueOf(editObj[0][38])));
					//reqbean.setCustZipCode(checkNull(String.valueOf(editObj[0][41])));
					reqbean.setCustShiftCode(checkNull(String.valueOf(editObj[0][39])));
					reqbean.setCustShiftType(checkNull(String.valueOf(editObj[0][40])));
					reqbean.setCustRegTemp(checkNull(String.valueOf(editObj[0][41])));
					reqbean.setCustflsaStatus(checkNull(String.valueOf(editObj[0][42])));
					reqbean.setSalaryPlan(checkNull(String.valueOf(editObj[0][43])));
					System.out.println("##################" + editObj[0][43]);
					reqbean.setPayGroup(checkNull(String.valueOf(editObj[0][44])));
					reqbean.setCadreCode(checkNull(String.valueOf(editObj[0][45])));
					reqbean.setCadreName(checkNull(String.valueOf(editObj[0][46])));
					reqbean.setWeeklyHours(checkNull(String.valueOf(editObj[0][47])));
					reqbean.setBiweeklySalary(checkNull(String.valueOf(editObj[0][48])));
					reqbean.setAnnualSalary(checkNull(String.valueOf(editObj[0][49])));
					reqbean.setDeptCode(checkNull(String.valueOf(editObj[0][50])));
					reqbean.setExecutiveCode(checkNull(String.valueOf(editObj[0][51])));
					reqbean.setExecutiveName(checkNull(String.valueOf(editObj[0][52])));
					//reqbean.setOfficeCityId(checkNull(String.valueOf(editObj[0][55])));
					reqbean.setOfficeCityName(checkNull(String.valueOf(editObj[0][53])));
					reqbean.setOfficeStateName(checkNull(String.valueOf(editObj[0][54])));
					/*reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][55])));
					reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][56])));
					reqbean.setApproverName(checkNull(String.valueOf(editObj[0][57])));*/
					reqbean.setApplicationDate(checkNull(String.valueOf(editObj[0][55])));
					reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][58])));
					reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][59])));
					reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][60])));
					reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][61])));
					reqbean.setDeptName(checkNull(String.valueOf(editObj[0][62])));
					reqbean.setExeEmployeeCode(checkNull(String.valueOf(editObj[0][63])));
					reqbean.setExeEmployeeToken(checkNull(String.valueOf(editObj[0][64])));
					reqbean.setExeEmployeeName(checkNull(String.valueOf(editObj[0][65])));
					
					reqbean.setFulltimeParttime(checkNull(String.valueOf(editObj[0][66])));
					reqbean.setCustfulltimeParttime(checkNull(String.valueOf(editObj[0][67])));
					reqbean.setEmailAddress(checkNull(String.valueOf(editObj[0][68])));
				
					getApproverCommentList(reqbean, requestID);
					
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
		
		
		public void viewApplicationFunction(NewHireRehire reqbean,
				String requestID) {
			try {
				String editQuery = "SELECT HIRE_REHIRE_ID, EMP_FIRST_NAME, EMP_MIDDLE_NAME, EMP_LAST_NAME, "
					+ " EMP_SOCIAL_SECRTY_NUMBER, EMP_SOCIAL_INSURANCE_NUMBER, EMP_ADDRESS,  "
					+ " EMP_CITY, EMP_STATE, EMP_COUNTRY, EMP_PIN_CODE,  "
					+ " EMP_HOME_PHONE, EMP_REQ_NUMBER, EMP_SEX, EMP_MARITAL_STATUS,  "
					+ " EMP_EDUCATION,QUA_NAME, TO_CHAR(EMP_BIRTH_DATE,'DD-MM-YYYY'), EMP_REFFERAL_SOURCE,REFERRAL_SOURCE_NAME,  "
					+ " EMP_ETHNIC_GRP, ETHINICITY AS ETHNIC_NAME , TO_CHAR(HIRE_DATE,'DD-MM-YYYY'), ACTION_REASON, JOB_CODE, JOB_TITLE," +
							" TO_CHAR(ACQUISITION_DATE,'DD-MM-YYYY'), ACQUIRED_COMPANY,TO_CHAR(PREACQUSITION_DATE,'DD-MM-YYYY'), PHY_WRK_LOCATION,  "
					+ " HOME_SHIFT_CODE, HOME_SHIFT.SHIFT_NAME AS HOME_SHIFT_NAME ,  "
					+ " HOME_REG_TEMP, HOME_FLSA_STATUS, CUSTOMER_NAME, CUST_PHY_ADDRESS,  "
					+ " CUST_CITY, CUST_STATE, CUST_PIN_CODE, CUST_SHIFT_CODE," +
							"CUST_SHIFT.SHIFT_NAME AS CUST_SHIFT_NAME , CUST_REG_TEMP,  "
					+ " CUST_FLSA_STATUS,  "
					+ " HRMS_D1_NEW_HIRE_REHIRE.SALARY_PLAN, PAY_GROUP, GRADE,CADRE_NAME, WEEKLY_HOURS, BIWEEKLY_SALARY, ANNUAL_SALARY, DEPT_CODE,  "
					+ " EXECUTIVE_CODE,RANK_NAME, OFFICE_CITY, OFFICE_STATE, " 
					/*		"HIRE_APPROVER,OFFC1.EMP_TOKEN AS APPROVER_TOKEN,   "
					+ " OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,   "*/
					+ "  TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , HIRE_STATUS,HIRE_LEVEL,TRACKING_NUMBER ,HIRE_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(HIRE_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') "
					+ " , NVL(HRMS_DEPT.DEPT_NAME||' - '||DEPT_ABBR, ' '),EXE_EMP_CODE, EXECUTIVE.EMP_TOKEN, EXECUTIVE.EMP_FNAME || ' ' || EXECUTIVE.EMP_MNAME || ' ' || EXECUTIVE.EMP_LNAME AS ENAME,HIRE_TOKEN, CENTER_NAME, SHIFT_NAME, EMP_RANK.RANK_NAME AS EMP_RANK , DIV_NAME, ADD_EMAIL, FULL_PART_TIME, CUST_FULL_PART_TIME ,TYPE_NAME , HIRE_EMAIL_ADD from HRMS_D1_NEW_HIRE_REHIRE "
					+ " LEFT JOIN HRMS_SHIFT HOME_SHIFT ON(HOME_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.HOME_SHIFT_CODE) "
					//+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.EMP_CITY) "
					+ " LEFT JOIN HRMS_SHIFT SHIFT ON(SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_SHIFT_CODE) "
				//	+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.HIRE_APPROVER) "
					+ " LEFT JOIN HRMS_QUA ON (HRMS_QUA.QUA_ID = HRMS_D1_NEW_HIRE_REHIRE.EMP_EDUCATION) " 
					+ " LEFT JOIN HRMS_D1_REFERRAL on (HRMS_D1_REFERRAL.REFERRAL_ID =HRMS_D1_NEW_HIRE_REHIRE.EMP_REFFERAL_SOURCE) "
				//	+ " LEFT JOIN HRMS_LOCATION CUST_CITY ON(CUST_CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.CUST_CITY) "
					//+ " LEFT JOIN HRMS_LOCATION DECISION_CITY ON(DECISION_CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.DECISION_CITY) "
					//+ " LEFT JOIN HRMS_SHIFT DECISION_SHIFT ON(DECISION_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.DECISION_SHIFT_CODE) "
					+ " LEFT JOIN HRMS_SHIFT CUST_SHIFT ON(CUST_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_SHIFT_CODE) "
					+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID =HRMS_D1_NEW_HIRE_REHIRE.GRADE) "
				//	+ " LEFT JOIN HRMS_LOCATION OFFICE_CITY ON(OFFICE_CITY.LOCATION_CODE = HRMS_D1_NEW_HIRE_REHIRE.OFFICE_CITY) "
					+ " LEFT JOIN HRMS_D1_ETHIC ON (HRMS_D1_ETHIC.ETHIC_CODE = HRMS_D1_NEW_HIRE_REHIRE.EMP_ETHNIC_GRP) "
					//+ " LEFT JOIN HRMS_D1_HIRE_ZIP ON (HRMS_D1_HIRE_ZIP.ZIP_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_PIN_CODE)  "
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_NEW_HIRE_REHIRE.EXECUTIVE_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.HIRE_INITIATOR)  "
					+ " LEFT JOIN HRMS_EMP_OFFC EXECUTIVE ON(EXECUTIVE.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.EXE_EMP_CODE)  "
					+" LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=HRMS_D1_NEW_HIRE_REHIRE.DEPT_CODE )"
					
					+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_CENTER) "
					+ " LEFT JOIN HRMS_SHIFT  ON(HRMS_SHIFT.SHIFT_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_SHIFT) "
					+ " LEFT JOIN HRMS_RANK EMP_RANK ON(EMP_RANK.RANK_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_RANK) "
					+ " LEFT JOIN HRMS_DIVISION  ON(HRMS_DIVISION.DIV_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_DIV) "
					+ " left JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_TYPE)  "
								   + "  WHERE HIRE_REHIRE_ID = "+requestID;
				Object[][] editObj = getSqlModel().getSingleResult(editQuery);
				if(editObj!=null && editObj.length>0)
				{
					reqbean.setHireRehireId(checkNull(String.valueOf(editObj[0][0])));
					//reqbean.setRequestID(checkNull(String.valueOf(editObj[0][0])));
					reqbean.setEmpFirstName(checkNull(String.valueOf(editObj[0][1])));
					reqbean.setEmpMiddleName(checkNull(String.valueOf(editObj[0][2])));
					reqbean.setEmpLastName(checkNull(String.valueOf(editObj[0][3])));
					reqbean.setSocialSecurityNumber(checkNull(String.valueOf(editObj[0][4])));
					reqbean.setSocialInsuranceNumber(checkNull(String.valueOf(editObj[0][5])));
					reqbean.setEmpHomeAddress(checkNull(String.valueOf(editObj[0][6])));
					//reqbean.setCityId(checkNull(String.valueOf(editObj[0][7])));
					reqbean.setCityName(checkNull(String.valueOf(editObj[0][7])));
					
					reqbean.setStateName(checkNull(String.valueOf(editObj[0][9])));
					reqbean.setCountry(checkNull(String.valueOf(editObj[0][8])));
					reqbean.setZip(checkNull(String.valueOf(editObj[0][10])));
					reqbean.setHomePhoneNumber(checkNull(String.valueOf(editObj[0][11])));
					reqbean.setReqNumber(checkNull(String.valueOf(editObj[0][12])));
					reqbean.setSex(checkNull(String.valueOf(editObj[0][13])));
					reqbean.setMaritalStatus(checkNull(String.valueOf(editObj[0][14])));
					reqbean.setQualCode(checkNull(String.valueOf(editObj[0][15])));
					reqbean.setQualifyName(checkNull(String.valueOf(editObj[0][16])));
					reqbean.setBirthDate(checkNull(String.valueOf(editObj[0][17])));
					reqbean.setMediumCode(checkNull(String.valueOf(editObj[0][18])));
					reqbean.setMediumName(checkNull(String.valueOf(editObj[0][19])));
					reqbean.setCastCode(checkNull(String.valueOf(editObj[0][20])));
					reqbean.setCastName(checkNull(String.valueOf(editObj[0][21])));
					reqbean.setHireDate(checkNull(String.valueOf(editObj[0][22])));
					reqbean.setActionReason(checkNull(String.valueOf(editObj[0][23])));
					
					reqbean.setJobCode(checkNull(String.valueOf(editObj[0][24])));
					reqbean.setJobTitle(checkNull(String.valueOf(editObj[0][25])));
					reqbean.setAcquisitionDate(checkNull(String.valueOf(editObj[0][26])));
					reqbean.setAcquiredCompany(checkNull(String.valueOf(editObj[0][27])));
					reqbean.setPreacqusitionDate(checkNull(String.valueOf(editObj[0][28])));
					//reqbean.setUserProfile(checkNull(String.valueOf(editObj[0][30])));
					
					if (String.valueOf(editObj[0][29]).equals("H")) {
						reqbean.setUserProfile("Ho");
						reqbean.setUserProfileRadioOptionValue("Ho");
					} else if (String.valueOf(editObj[0][29]).equals("T")) {
						reqbean.setUserProfile("Tr");
						reqbean.setUserProfileRadioOptionValue("Tr");
					} else if (String.valueOf(editObj[0][29]).equals("C")) {
						reqbean.setUserProfile("Cu");
						reqbean.setUserProfileRadioOptionValue("Cu");
					} else if (String.valueOf(editObj[0][29]).equals("D")) {
						reqbean.setUserProfile("De");
						reqbean.setUserProfileRadioOptionValue("De");
					} else if (String.valueOf(editObj[0][29]).equals("V")) {
						reqbean.setUserProfile("Va");
						reqbean.setUserProfileRadioOptionValue("Va");
					} else {
						reqbean.setUserProfile("");
						reqbean.setUserProfileRadioOptionValue("");
					}
					reqbean.setShiftCode(checkNull(String.valueOf(editObj[0][30])));
					reqbean.setShiftType(checkNull(String.valueOf(editObj[0][31])));
					reqbean.setRegTemp(checkNull(String.valueOf(editObj[0][32])));
					reqbean.setFlsaStatus(checkNull(String.valueOf(editObj[0][33])));
					reqbean.setCustomerName(checkNull(String.valueOf(editObj[0][34])));
					reqbean.setPhysicalAddress(checkNull(String.valueOf(editObj[0][35])));
					//reqbean.setCustCityId(checkNull(String.valueOf(editObj[0][37])));
					reqbean.setCustCityName(checkNull(String.valueOf(editObj[0][36])));
					reqbean.setCustStateName(checkNull(String.valueOf(editObj[0][37])));
					reqbean.setCustZipCode(checkNull(String.valueOf(editObj[0][38])));
					//reqbean.setCustZipCode(checkNull(String.valueOf(editObj[0][41])));
					reqbean.setCustShiftCode(checkNull(String.valueOf(editObj[0][39])));
					reqbean.setCustShiftType(checkNull(String.valueOf(editObj[0][40])));
					reqbean.setCustRegTemp(checkNull(String.valueOf(editObj[0][41])));
					reqbean.setCustflsaStatus(checkNull(String.valueOf(editObj[0][42])));
					reqbean.setSalaryPlan(checkNull(String.valueOf(editObj[0][43])));
					System.out.println("##################" + editObj[0][43]);
					reqbean.setPayGroup(checkNull(String.valueOf(editObj[0][44])));
					reqbean.setCadreCode(checkNull(String.valueOf(editObj[0][45])));
					reqbean.setCadreName(checkNull(String.valueOf(editObj[0][46])));
					reqbean.setWeeklyHours(checkNull(String.valueOf(editObj[0][47])));
					reqbean.setBiweeklySalary(checkNull(String.valueOf(editObj[0][48])));
					reqbean.setAnnualSalary(checkNull(String.valueOf(editObj[0][49])));
					reqbean.setDeptCode(checkNull(String.valueOf(editObj[0][50])));
					reqbean.setExecutiveCode(checkNull(String.valueOf(editObj[0][51])));
					reqbean.setExecutiveName(checkNull(String.valueOf(editObj[0][52])));
					//reqbean.setOfficeCityId(checkNull(String.valueOf(editObj[0][55])));
					reqbean.setOfficeCityName(checkNull(String.valueOf(editObj[0][53])));
					reqbean.setOfficeStateName(checkNull(String.valueOf(editObj[0][54])));
					/*reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][55])));
					reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][56])));
					reqbean.setApproverName(checkNull(String.valueOf(editObj[0][57])));*/
					reqbean.setApplicationDate(checkNull(String.valueOf(editObj[0][55])));
					reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][56])));
					reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][59])));
					reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][60])));
					reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][61])));
					reqbean.setDeptName(checkNull(String.valueOf(editObj[0][62])));
					reqbean.setExeEmployeeCode(checkNull(String.valueOf(editObj[0][63])));
					reqbean.setExeEmployeeToken(checkNull(String.valueOf(editObj[0][64])));
					reqbean.setExeEmployeeName(checkNull(String.valueOf(editObj[0][65])));
					
					reqbean.setEmpToken(checkNull(String.valueOf(editObj[0][66])));
					
					reqbean.setCenterName(checkNull(String.valueOf(editObj[0][67])));
					reqbean.setShiftTypeAppr(checkNull(String.valueOf(editObj[0][68])));
					
					reqbean.setRankName(checkNull(String.valueOf(editObj[0][69])));
					reqbean.setDivName(checkNull(String.valueOf(editObj[0][70])));
					reqbean.setEmailId(checkNull(String.valueOf(editObj[0][71])));
					
					reqbean.setFulltimeParttime(checkNull(String.valueOf(editObj[0][72])));
					reqbean.setCustfulltimeParttime(checkNull(String.valueOf(editObj[0][73])));
					reqbean.setEmpTypeName(checkNull(String.valueOf(editObj[0][74])));
					reqbean.setEmailAddress(checkNull(String.valueOf(editObj[0][75])));
					
					
					getApproverCommentList(reqbean, requestID);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		
		


		public boolean delRecord(NewHireRehire bean) {
			Object del[][] = null;
			try {
				del = new Object[1][1];
				// to delete the single record after clicking on saving or searching
				// button
				del[0][0] = bean.getRequestID();
			} catch(Exception e) {
				// TODO: handle exception
			}
			String deleteQuery = "DELETE FROM HRMS_D1_NEW_HIRE_REHIRE WHERE HIRE_REHIRE_ID=? ";

			return getSqlModel().singleExecute(deleteQuery, del);
		}




		public boolean sendForApprovalFunction(NewHireRehire reqbean) {
			boolean result = false;
			if(reqbean.getRequestID().equals(""))
			{
				result = draftFunction(reqbean);
			}else
			{
				result = updateRecords(reqbean);
				try {
					String changeStatusQuery = "UPDATE HRMS_D1_NEW_HIRE_REHIRE SET HIRE_STATUS = 'P' WHERE HIRE_REHIRE_ID = "+reqbean.getRequestID();
					result =  getSqlModel().singleExecute(changeStatusQuery);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		public boolean isCurrentUser(String userId) {
			String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
			Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
			if(currentUserObj != null && currentUserObj.length > 0) {
				return true;
			}
			return false;
		}
		
		
		public boolean cancelFunction(NewHireRehire bean, String status) {			
			boolean result = false;			
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_NEW_HIRE_REHIRE SET HIRE_STATUS = '"+status+"'"
											+" WHERE HIRE_REHIRE_ID = " + bean.getHireRehireId();
				
				result =  getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		
		
		
		public void getApprovedList(NewHireRehire reqbean,
				HttpServletRequest request, String userId) {
			
			try {
				Object[][] approvedListData = null;
				ArrayList approvedList = new ArrayList();
				
				Object[][] approvedCancellationListData = null;
				ArrayList approvedCancellationList = new ArrayList();
				
				// Approved application Begins
				String selQuery = " select TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
					+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE"
					+ " WHERE HIRE_STATUS = 'A' AND HIRE_INITIATOR = "+ reqbean.getUserEmpId()+" ORDER BY HIRE_REHIRE_ID DESC "; 
				approvedListData = getSqlModel().getSingleResult(selQuery);
					
				String[] pageIndexApproved = Utility.doPaging(reqbean.getMyPageApproved(),
						approvedListData.length, 20);
				if (pageIndexApproved == null) {
					pageIndexApproved[0] = "0";
					pageIndexApproved[1] = "20";
					pageIndexApproved[2] = "1";
					pageIndexApproved[3] = "1";
					pageIndexApproved[4] = "";
				}
				
				request.setAttribute("totalApprovedPage", Integer.parseInt(String
						.valueOf(pageIndexApproved[2])));
				request.setAttribute("approvedPageNo", Integer.parseInt(String
						.valueOf(pageIndexApproved[3])));
				if (pageIndexApproved[4].equals("1"))
					reqbean.setMyPageApproved("1");
				
				if (approvedListData != null && approvedListData.length > 0) {
					reqbean.setApprovedVoucherListLength(true);
					for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
					.parseInt(pageIndexApproved[1]); i++) {
						NewHireRehire beanItt = new NewHireRehire();
						beanItt.setTrackingNo(checkNull(String
								.valueOf(approvedListData[i][0])));
						beanItt.setEmployeeName(checkNull(String
								.valueOf(approvedListData[i][1])));
						
						beanItt.setApplicationDate(checkNull(String
								.valueOf(approvedListData[i][2])));
						
						beanItt.setHireRehireId(checkNull(String
								.valueOf(approvedListData[i][3])));
						
						beanItt.setActionReasonItr(checkNull(String
								.valueOf(approvedListData[i][4])));
						approvedList.add(beanItt);
					}
					reqbean.setApprovedVoucherIteratorList(approvedList);
				}
				// Approved application Ends
				
				// Approved cancellation application Begins
				String approvedcancellationQuery = " select TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
					+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE"
					+ " WHERE HIRE_STATUS = 'X' AND HIRE_INITIATOR = "+ reqbean.getUserEmpId()+" ORDER BY HIRE_REHIRE_ID DESC "; 
				approvedCancellationListData = getSqlModel().getSingleResult(approvedcancellationQuery);
						
				String[] pageIndexApprovedCancel = Utility.doPaging(reqbean.getMyPageApprovedCancel(),
						approvedCancellationListData.length, 20);
				if (pageIndexApprovedCancel == null) {
					pageIndexApprovedCancel[0] = "0";
					pageIndexApprovedCancel[1] = "20";
					pageIndexApprovedCancel[2] = "1";
					pageIndexApprovedCancel[3] = "1";
					pageIndexApprovedCancel[4] = "";
				}
				
				request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String
						.valueOf(pageIndexApprovedCancel[2])));
				request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String
						.valueOf(pageIndexApprovedCancel[3])));
				if (pageIndexApprovedCancel[4].equals("1"))
					reqbean.setMyPageApprovedCancel("1");
				
				if (approvedCancellationListData != null && approvedCancellationListData.length > 0) {
					reqbean.setApprovedCancellationVoucherListLength(true);
					for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer
					.parseInt(pageIndexApprovedCancel[1]); i++) {
						NewHireRehire beanItt = new NewHireRehire();
						beanItt.setTrackingNo(checkNull(String
								.valueOf(approvedCancellationListData[i][0])));
						beanItt.setEmployeeName(checkNull(String
								.valueOf(approvedCancellationListData[i][1])));
						
						beanItt.setApplicationDate(checkNull(String
								.valueOf(approvedCancellationListData[i][2])));
						
						beanItt.setHireRehireId(checkNull(String
								.valueOf(approvedCancellationListData[i][3])));
						
						beanItt.setActionReasonItr(checkNull(String
								.valueOf(approvedCancellationListData[i][4])));
						approvedCancellationList.add(beanItt);
					}
					reqbean.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
				}
				// Approved cancellation application Ends
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}




		public void getCancelledList(NewHireRehire reqbean,
				HttpServletRequest request, String userId) {
			
			try {
				Object[][] cancelledListData = null;
				ArrayList cancelledList = new ArrayList();
				
				// Cancelled application Begins
				String selQuery =  " select TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
					+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE"
					+ " WHERE HIRE_STATUS = 'C' AND HIRE_INITIATOR = "+ reqbean.getUserEmpId()+"  ORDER BY HIRE_REHIRE_ID DESC "; 
				cancelledListData = getSqlModel().getSingleResult(selQuery);
					
				String[] pageIndexCancel = Utility.doPaging(reqbean.getMyPageCancel(),
						cancelledListData.length, 20);
				if (pageIndexCancel == null) {
					pageIndexCancel[0] = "0";
					pageIndexCancel[1] = "20";
					pageIndexCancel[2] = "1";
					pageIndexCancel[3] = "1";
					pageIndexCancel[4] = "";
				}
				
				request.setAttribute("totalCancelPage", Integer.parseInt(String
						.valueOf(pageIndexCancel[2])));
				request.setAttribute("cancelPageNo", Integer.parseInt(String
						.valueOf(pageIndexCancel[3])));
				if (pageIndexCancel[4].equals("1"))
					reqbean.setMyPageCancel("1");
				
				
				if (cancelledListData != null && cancelledListData.length > 0) {
					reqbean.setCancelledVoucherListLength(true);
					for (int i = Integer.parseInt(pageIndexCancel[0]); i < Integer
					.parseInt(pageIndexCancel[1]); i++)  {
						NewHireRehire beanItt = new NewHireRehire();
						beanItt.setTrackingNo(checkNull(String
								.valueOf(cancelledListData[i][0])));
						beanItt.setEmployeeName(checkNull(String
								.valueOf(cancelledListData[i][1])));
						
						beanItt.setApplicationDate(checkNull(String
								.valueOf(cancelledListData[i][2])));
						
						beanItt.setHireRehireId(checkNull(String
								.valueOf(cancelledListData[i][3])));
						
						beanItt.setActionReasonItr(checkNull(String
								.valueOf(cancelledListData[i][4])));
						cancelledList.add(beanItt);
					}
					reqbean.setCancelledVoucherIteratorList(cancelledList);
				}
				// Cancelled application Ends
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}




		public void getRejectedList(NewHireRehire reqbean,
				HttpServletRequest request, String userId) {

			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			
			Object[][] rejectedCancellationListData = null;
			ArrayList rejectedCancellationList = new ArrayList();
			
			// Rejected application Begins
			String selQuery =  " select TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
				+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE"
				+ " WHERE HIRE_STATUS = 'R' AND HIRE_INITIATOR = "+ reqbean.getUserEmpId()+" ORDER BY HIRE_REHIRE_ID DESC "; 
			rejectedListData = getSqlModel().getSingleResult(selQuery);
				
			String[] pageIndexRejected = Utility.doPaging(reqbean.getMyPageRejected(),
					rejectedListData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}
			
			request.setAttribute("totalRejectedPage", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				reqbean.setMyPageRejected("1");
			
			if (rejectedListData != null && rejectedListData.length > 0) {
				reqbean.setRejectedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
				.parseInt(pageIndexRejected[1]); i++)  {
					NewHireRehire beanItt = new NewHireRehire();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][1])));
					
					beanItt.setApplicationDate(checkNull(String
							.valueOf(rejectedListData[i][2])));
					
					beanItt.setHireRehireId(checkNull(String
							.valueOf(rejectedListData[i][3])));
					
					beanItt.setActionReasonItr(checkNull(String
							.valueOf(rejectedListData[i][4])));
					rejectedList.add(beanItt);
				}
				reqbean.setRejectedVoucherIteratorList(rejectedList);
			}
			// Rejected application Ends
			
			// Rejected cancellation application Begins
			String approvedcancellationQuery =  " select TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ," 
				+ " HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE"
				+ " WHERE HIRE_STATUS = 'Z' AND HIRE_INITIATOR = "+ reqbean.getUserEmpId()+" ORDER BY HIRE_REHIRE_ID DESC "; 
			rejectedCancellationListData = getSqlModel().getSingleResult(approvedcancellationQuery);
				
			String[] pageIndexRejectedCancellation = Utility.doPaging(reqbean.getMyPageCancelRejected(),
					rejectedCancellationListData.length, 20);
			if (pageIndexRejectedCancellation == null) {
				pageIndexRejectedCancellation[0] = "0";
				pageIndexRejectedCancellation[1] = "20";
				pageIndexRejectedCancellation[2] = "1";
				pageIndexRejectedCancellation[3] = "1";
				pageIndexRejectedCancellation[4] = "";
			}
			
			request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String
					.valueOf(pageIndexRejectedCancellation[2])));
			request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String
					.valueOf(pageIndexRejectedCancellation[3])));
			if (pageIndexRejectedCancellation[4].equals("1"))
				reqbean.setMyPageCancelRejected("1");
			
			if (rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {
				reqbean.setRejectedCancelVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer
				.parseInt(pageIndexRejectedCancellation[1]); i++) {
					NewHireRehire beanItt = new NewHireRehire();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedCancellationListData[i][1])));
					
					beanItt.setApplicationDate(checkNull(String
							.valueOf(rejectedCancellationListData[i][2])));
					
					beanItt.setHireRehireId(checkNull(String
							.valueOf(rejectedCancellationListData[i][3])));
					
					beanItt.setActionReasonItr(checkNull(String
							.valueOf(rejectedCancellationListData[i][4])));
					rejectedCancellationList.add(beanItt);
				}
				reqbean.setRejectedCancelVoucherIteratorList(rejectedCancellationList);
			}
			// Rejected cancellation application Ends
		}

		// added new by ganesh 
		public void getStateCountry(NewHireRehire bean) {
			String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE =" + bean.getCityId() + ") ";
			Object[][] stateCode = getSqlModel().getSingleResult(query);

			if(stateCode.length > 0) {
				bean.setStateName(checkNull(String.valueOf(stateCode[0][1])));
				String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
				Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
				if(countryName.length > 0) {
					bean.setCountry(checkNull(String.valueOf(countryName[0][1])));
				}// end of nested if
				else bean.setCountry("");
			}// end of if
			String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}
			
			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
			+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
			+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
			
			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorObj!=null && initiatorObj.length >0){
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			
		}

		public void getCustState(NewHireRehire bean) {
			String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE =" + bean.getCustCityId() + ") ";
			Object[][] stateCode = getSqlModel().getSingleResult(query);

			if(stateCode.length > 0) {
				bean.setCustStateName(checkNull(String.valueOf(stateCode[0][1])));
				/*String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
				Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
				if(countryName.length > 0) {
					bean.setCountry(checkNull(String.valueOf(countryName[0][1])));*/
				}// end of nested if
			//	else bean.setCountry("");
			String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}
			
			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
			+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
			+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
			
			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorObj!=null && initiatorObj.length >0){
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			}// end of if

		public void getDecisionState(NewHireRehire bean) {
			String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE =" + bean.getDecisionCityId() + ") ";
			Object[][] stateCode = getSqlModel().getSingleResult(query);

			if(stateCode.length > 0) {
				bean.setDecisionStateName(checkNull(String.valueOf(stateCode[0][1])));
				/*String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
				Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
				if(countryName.length > 0) {
					bean.setCountry(checkNull(String.valueOf(countryName[0][1])));*/
				}// end of nested if
			//	else bean.setCountry("");
			String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}
			
			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
			+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
			+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
			
			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorObj!=null && initiatorObj.length >0){
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			}// end of if

		public void getOfficeState(NewHireRehire bean) {
			String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE =" + bean.getOfficeCityId() + ") ";
			Object[][] stateCode = getSqlModel().getSingleResult(query);

			if(stateCode.length > 0) {
				bean.setOfficeStateName(checkNull(String.valueOf(stateCode[0][1])));
				/*String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
				Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
				if(countryName.length > 0) {
					bean.setCountry(checkNull(String.valueOf(countryName[0][1])));*/
				}// end of nested if
			//	else bean.setCountry("");
			String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}
			
			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
			+ "	INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
			+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
			
			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorObj!=null && initiatorObj.length >0){
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			}// end of if

		public void getSalaryPlan(NewHireRehire bean) {
			String query = "SELECT SALARY_PLAN FROM HRMS_D1_HIRE_ZIP where ZIP_ID =substr(" + bean.getCustZipId()+" ,"+bean.getCustZipId().length()+",3)";
			System.out.println("query" + query);
			Object[][] salaryPlan = getSqlModel().getSingleResult(query);

			if(salaryPlan.length > 0) {
				bean.setSalaryPlan(checkNull(String.valueOf(salaryPlan[0][0])));
				/*String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
				Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
				if(countryName.length > 0) {
					bean.setCountry(checkNull(String.valueOf(countryName[0][1])));*/
				}// end of nested if
				else bean.setSalaryPlan("GBR");
			
		}

		public boolean isUserAHRApprover(String userId) {
		String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H' AND APP_SETTINGS_EMP_ID = " + userId;
		Object[][] hrApproverObj = getSqlModel().getSingleResult(hrApproverSql);

		if(hrApproverObj != null && hrApproverObj.length > 0) { return true; }

		return false;
	}

// function to set salary plan when zip code is entered.		
		public String addSalaryPlan(HttpServletRequest request) {
			String message = "GBR";
			try {
				String zipCode = request.getParameter("custZipCode").trim();
				//if(zipCode.length()==6){
					zipCode = zipCode.substring(zipCode.length()-3,zipCode.length());
					System.out.println("ZIP CODE#################" + zipCode.substring(zipCode.length()-3,zipCode.length()));
					String query = " select zip_code ,SALARY_PLAN from HRMS_D1_HIRE_ZIP ";

					Object[][] salaryQueryObj = getSqlModel().getSingleResult(query);
					if(salaryQueryObj!=null && salaryQueryObj.length >0)
					{
						for (int i = 0; i < salaryQueryObj.length; i++) {
							String splitArr[]=String.valueOf(salaryQueryObj[i][0]).split(",");
							for (int j = 0; j < splitArr.length; j++) {
								
								System.out.println("String.valueOf(splitArr[j])  "+String.valueOf(splitArr[j]));
								
								System.out.println("zipCode "+zipCode );
								
								if(String.valueOf(splitArr[j]).trim().equals(zipCode))
								{
									message =String.valueOf(salaryQueryObj[i][1]);
									break;
								}
							}
						}
					}
					else{
						message ="GBR";
					}
					
				/*}else{
					message ="GBR";
				}*/
				
				System.out.println("IN AJAX FUNCTION");
			} catch(Exception e) {
				logger.error("Exception in Add Salary Plan:" + e);
				e.printStackTrace();
			}
			System.out.println("message"+message);
			return message;
		}

		public void getEmployeeDetails(String empId, NewHireRehire bean) {
			Object[] beanObj = null;
			try {
				beanObj = new Object[1];
				beanObj[0] = empId;

				
				String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
				Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
				if(dateObj!=null && dateObj.length >0){
					bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
				}
				
				String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
				+ "	INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
				+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + empId;
				
				Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
				if(initiatorObj!=null && initiatorObj.length >0){
					bean.setInitiatorCode(bean.getUserEmpId());
					bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
					bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
				}
				
				
			} catch(Exception e) {
				logger.info("Exception in getEmployeeDetails------------" + e);
			}

		}
		
		public void viewOfficialDetails(NewHireRehire bean, String code) {
			
			String officeQuery = " select EMP_DIV,DIV_NAME,EMP_CENTER,CENTER_NAME,EMP_RANK,RANK_NAME,EMP_SHIFT,SHIFT_NAME,EMP_TYPE ,TYPE_NAME, "
					+ "  EMP_TOKEN, hrms_emp_address.ADD_EMAIL from  hrms_emp_offc "
					+ "   left join hrms_division on(hrms_division.DIV_ID = hrms_emp_offc.EMP_DIV) "
					+ "  left join hrms_center on(hrms_center.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
					+ "  left join hrms_rank on(hrms_rank.RANK_ID = hrms_emp_offc.EMP_RANK) "
					+ "  left join hrms_shift on(hrms_shift.SHIFT_ID = hrms_emp_offc.EMP_SHIFT) "
					+ "  left join hrms_emp_type on(hrms_emp_type.TYPE_ID = hrms_emp_offc.EMP_TYPE) "
					+ "  left join hrms_emp_address on(hrms_emp_address.EMP_ID = hrms_emp_offc.EMP_ID)"
					+ "  where EMP_STATUS = 'S' AND  hrms_emp_offc.EMP_ID = "+code;
			Object[][] offcData = getSqlModel().getSingleResult(officeQuery);
			if (offcData != null && offcData.length > 0) {
				bean.setDivCode(checkNull(String.valueOf(offcData[0][0])));
				bean.setDivName(checkNull(String.valueOf(offcData[0][1])));

				bean.setCenterCode(checkNull(String.valueOf(offcData[0][2])));
				bean.setCenterName(checkNull(String.valueOf(offcData[0][3])));

				bean.setRankCode(checkNull(String.valueOf(offcData[0][4])));
				bean.setRankName(checkNull(String.valueOf(offcData[0][5])));

				bean.setShiftCodeAppr(checkNull(String.valueOf(offcData[0][6])));
				bean.setShiftTypeAppr(checkNull(String.valueOf(offcData[0][7])));

				bean.setEmpTypeCode(checkNull(String.valueOf(offcData[0][8])));
				bean.setEmpTypeName(checkNull(String.valueOf(offcData[0][9])));
				bean.setEmpToken(checkNull(String.valueOf(offcData[0][10])));
				bean.setEmailId(checkNull(String.valueOf(offcData[0][11])));

			}

		}
		
		
		
		
}
