package org.struts.action.TravelManagement.TravelClaim;

import java.util.TreeMap;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsClmDisbursement;
import org.paradyne.lib.sms.SMSUtil;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelClaim.TmsClmDisbursementModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class TmsClmDisbursementAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsClmDisbursementAction.class);
	TmsClmDisbursement clmDisbrmnt;

	public void prepare_local() throws Exception {
		clmDisbrmnt = new TmsClmDisbursement();
		clmDisbrmnt.setMenuCode(974);
	}

	public Object getModel() {
		return clmDisbrmnt;
	}

	public String input() throws Exception {
		try {
			clmDisbrmnt.setListType("pending");
			TmsClmDisbursementModel claimModel = new TmsClmDisbursementModel();
			claimModel.initiate(context, session);
			claimModel.callStatus(clmDisbrmnt, "P", request);
			clmDisbrmnt.setStatus("P");
			claimModel.terminate();

		} catch (Exception e) {
			logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK
		
		if (clmDisbrmnt.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (clmDisbrmnt.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return SUCCESS;
		}
	}
	
	
	public String generateBankStatementReport(){
		try {
			TmsClmDisbursementModel model = new TmsClmDisbursementModel();
			model.initiate(context, session);
			model.generateStatementReport(clmDisbrmnt, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getClosedList() {

		try {
			clmDisbrmnt.setListType("closed");
			// FOR CLAIM LIST ADDED BY REEBA
			TmsClmDisbursementModel claimModel = new TmsClmDisbursementModel();
			claimModel.initiate(context, session);
			claimModel.callStatus(clmDisbrmnt, "C", request);
			clmDisbrmnt.setStatus("C");
			claimModel.terminate();

		} catch (Exception e) {
			logger.error("Exception in getclosed method : " + e);
		}// E
		return INPUT;
	}

	public String callStatus() {
		TmsClmDisbursementModel model = new TmsClmDisbursementModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		if (status.equals("C")) {
			clmDisbrmnt.setDisburseFlag("false");
			clmDisbrmnt.setPen("false");
			clmDisbrmnt.setClsd("true");
		}
		else {
			clmDisbrmnt.setDisburseFlag("true");
			clmDisbrmnt.setPen("true");
			clmDisbrmnt.setClsd("false");
		}
		clmDisbrmnt.setStatus(status);
		model.terminate();
		return "success";
	}

	public String callView() {
		try {
			
			String source = request.getParameter("src");

			//String source =(String) request.getAttribute("src");

			System.out.println("source--------------" + source);
			clmDisbrmnt.setSource(source);
			
			TmsClmDisbursementModel model = new TmsClmDisbursementModel();
			model.initiate(context, session);
			model.view(clmDisbrmnt, request);
			// ADDED BY REEBA
			TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
			clmDisbrmnt.setPayModeMap(payModeMap);
			String status = request.getParameter("tmsClmStatus");
			
			System.out.println("status-----"+status);
			String disStatus = request.getParameter("disStatus");
			boolean flag = model.chkDisburseId(clmDisbrmnt);
			if (flag) {
				model.getDisburseDetails(clmDisbrmnt);
				if (status.equals("Q") && disStatus.equals("C")) {
					clmDisbrmnt.setDisburseFlag("false");
				} else if (disStatus.equals("C")) {
					clmDisbrmnt.setDisburseFlag("true");
				} else if (disStatus.equals("P")) {
					clmDisbrmnt.setDisburseFlag("false");
				}
			} else if (status.equals("A") || status.equals("D")) {
				if (disStatus.equals("C")) {
					model.getDisburseDetails(clmDisbrmnt);
					clmDisbrmnt.setDisburseFlag("true");
				} else {
					clmDisbrmnt.setDisburseStatus("S");
					clmDisbrmnt.setDisburseFlag("false");
				}
			} else if (status.equals("F")) {
				clmDisbrmnt.setDisburseFlag("true");
				clmDisbrmnt.setShowRevokeStatus("true");
			} else {
				if (disStatus.equals("C") && !status.equals("Q")) {
					model.getDisburseDetails(clmDisbrmnt);
					clmDisbrmnt.setDisburseFlag("true");
				} else {
					if (status.equals("Q")) {
						clmDisbrmnt.setDisburseFlag("false");
					} else {
						clmDisbrmnt.setDisburseFlag("true");
					}
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trvlClmDisbrView";

	}

	public String addPayDtls() throws Exception {
		try {
			TmsClmDisbursementModel model = new TmsClmDisbursementModel();
			model.initiate(context, session);
			// ADDED BY REEBA
			TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
			clmDisbrmnt.setPayModeMap(payModeMap);
			model.addPayDtls(clmDisbrmnt, request);
			reset();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trvlClmDisbrView";
	}

	public String editPayDtls() throws Exception {
		TmsClmDisbursementModel model = new TmsClmDisbursementModel();
		model.initiate(context, session);
		// ADDED BY REEBA
		TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
		clmDisbrmnt.setPayModeMap(payModeMap);
		model.editDisburseDetails(clmDisbrmnt, request);
		reset();
		model.terminate();
		return "trvlClmDisbrView";
	}

	public String removePayDtls() throws Exception {
		TmsClmDisbursementModel model = new TmsClmDisbursementModel();
		model.initiate(context, session);
		// ADDED BY REEBA
		TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
		clmDisbrmnt.setPayModeMap(payModeMap);
		model.removePayDtls(clmDisbrmnt, request);
		model.terminate();
		return "trvlClmDisbrView";
	}

	public void reset() {
		System.out.println("call to rest method");
		clmDisbrmnt.setDisburseAccount("");
		clmDisbrmnt.setDisburseChqDate("");
		clmDisbrmnt.setDisburseChqNo("");
		clmDisbrmnt.setDisburseDate("");
		clmDisbrmnt.setDisburseAmount("");
		clmDisbrmnt.setDisburseBankName("");
		clmDisbrmnt.setBankCode("");
		clmDisbrmnt.setDisburseMode("CA");
		clmDisbrmnt.setDisbursementComment("");
		clmDisbrmnt.setParaId("");
		// ADDED BY REEBA
		TmsClmDisbursementModel model = new TmsClmDisbursementModel();
		model.initiate(context, session);
		TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
		clmDisbrmnt.setPayModeMap(payModeMap);
		model.terminate();
	}

	public String saveData() {
		try {
			TmsClmDisbursementModel model = new TmsClmDisbursementModel();
			model.initiate(context, session);
			boolean result = model.chkDisburseIdforSave(clmDisbrmnt);
			
			System.out.println("result  "+result);
			if (result) {
				if (model.updateData(clmDisbrmnt, request)) {
					addActionMessage("Record updated successfully");
				}
			} else {
				if (model.saveData(clmDisbrmnt, request)) {
					addActionMessage("Record saved successully.");
				}
			}
			TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
			clmDisbrmnt.setPayModeMap(payModeMap);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			clmDisbrmnt.setSource("");
			 return input();	
		}		 
		catch(Exception e){
			e.printStackTrace();
		}
		 return SUCCESS;
		/*if (clmDisbrmnt.getSource().equals("mymessages")) {
			return "mymessages";
		}   else {
			return "trvlClmDisbrView";
		}*/
		
	}

	public String f9Bank() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  BANK_MICR_CODE, NVL(BANK_NAME,' ') , BANK_IFSC_CODE, NVL(BRANCH_NAME, ' '), NVL(BANK_BSR_CODE,' ') "
				+ " FROM HRMS_BANK   ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Bank Micr Code", "Bank Name", "IFSC Code",
				"Branch", "Bank BSR Code" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "25", "20", "25", "10" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "bankCode", "disburseBankName", "bankIFSCCode",
				"bankBranch", "bankBSRCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
	public String f9creditCode() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query =  "  SELECT CREDIT_NAME, CREDIT_CODE FROM HRMS_CREDIT_HEAD " 
				+" WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_PRIORITY,CREDIT_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Credit Head" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "creditName", "creditCode" };

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
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9recoveryDebit() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEBIT_NAME, DEBIT_CODE FROM HRMS_DEBIT_HEAD "
				+ " WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("recovery.debit") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "recoveryDebit", "recoveryDebitCode" };

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
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String callViewAck() {
		try {
			
			String source = request.getParameter("src");

			//String source =(String) request.getAttribute("src");

			System.out.println("source--------------" + source);
			clmDisbrmnt.setSource(source);
			
			String tmsClmAppId = request.getParameter("tmsClmAppId");
			String trvlAppId = request.getParameter("trvlAppId");
			String trvlAppCode = request.getParameter("trvlAppCode");
			String applicantId = request.getParameter("applicantId");
		 
			TmsClmDisbursementModel model = new TmsClmDisbursementModel();
			model.initiate(context, session);
			model.viewAck(clmDisbrmnt, request, tmsClmAppId, trvlAppId,
					trvlAppCode, applicantId);
			model.terminate();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return "trvlClmAckView";

	}

	public String f9division() throws Exception {

		try {
			String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
			if (clmDisbrmnt.getUserProfileDivision() != null
					&& clmDisbrmnt.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ clmDisbrmnt.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";
			String[] headers = { "Division Name" };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "divisionName", "divisionCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String clearFilter() {
		try {
			TmsClmDisbursementModel model = new TmsClmDisbursementModel();
			model.initiate(context, session);
			clmDisbrmnt.setDivisionCode("");
			clmDisbrmnt.setDivisionName("");
			try {
				input();
			} catch (Exception e) {
				e.printStackTrace();
			}
			TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
			clmDisbrmnt.setPayModeMap(payModeMap);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			
		}
		return INPUT;

	}
	
	
	public String sendClaimDisbursementMailSMS() {

		TmsClmDisbursementModel model = new TmsClmDisbursementModel();
		boolean result = false;
		try {
			String[] applCode = request.getParameterValues("hidClaimCode");
			String[] checkedBox = request.getParameterValues("clmChkBox");
			String[] applCodeValues=null;
			if (checkedBox != null && checkedBox.length > 0) {
				applCodeValues = new String[checkedBox.length];
				for (int i = 0, j=0; i < applCode.length; i++) {
					if (applCode != null && applCode.length > 0
							&& !applCode[i].equals("")) {
							applCodeValues[j] = String.valueOf(applCode[i]);
							j++;
					}
				}
			}
			String disbApplCode = "";
			if (applCodeValues != null && applCodeValues.length > 0) {
				for (int i = 0; i < applCodeValues.length; i++) {
						if (applCodeValues.length - 1 == i) {
							disbApplCode += applCodeValues[i];
						} else {
							disbApplCode += applCodeValues[i] + ",";
						}
				}
			}
			model.initiate(context, session);
			String empIdQuery = "SELECT EXP_DISB_ID AS DISBURSE_ID, EXP_DISB_EXP_ID AS CLAIM_APPL_ID,  "
					+ " EXP_TRVL_APPCODE AS TRVL_APP_CODE, EXP_APP_EMPID AS EMP_ID, EXP_TRVL_APPID, EXP_TRVL_ID, EXP_DISB_BALANCE_AMT ,EXP_APPID FROM TMS_EXP_DISBURSEMENT "
					+ " INNER JOIN TMS_CLAIM_APPL ON (TMS_CLAIM_APPL.EXP_APPID = TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID) "
					+ " WHERE EXP_TRVL_APPCODE IN (" + disbApplCode + ")";
			Object codeObj[][] = model.getSqlModel()
					.getSingleResult(empIdQuery);

			String updateDisbStatusQuery = "UPDATE TMS_EXP_DISBURSEMENT  SET EXP_DISB_STATUS = 'C'"
					+ " WHERE EXP_DISB_ID = ? AND EXP_DISB_EXP_ID = ? ";
			if (codeObj != null && codeObj.length > 0) {
				Object updateObj[][] = new Object[codeObj.length][2];
				for (int i = 0; i < codeObj.length; i++) {
					updateObj[i][0] = String.valueOf(codeObj[i][0]);
					updateObj[i][1] = String.valueOf(codeObj[i][1]);
					 
					/* SMS UTILITY*/
					try {
						System.out.println("Travel SMS Util");
						Object[][] mobileNoObj = model.getSqlModel()
								.getSingleResult(
										"SELECT APPL_EMP_CONTACT FROM TMS_APP_EMPDTL WHERE APPL_ID="
												+ String.valueOf(codeObj[i][4])+" AND APPL_EMP_CODE="+ String.valueOf(codeObj[i][3])); 
						String message="Travel claim id "+String.valueOf(codeObj[i][5])+" claim amount of Rs."+String.valueOf(codeObj[i][6])+" is disbursed to your account.";
						SMSUtil su = new SMSUtil();
						su.init(model.getSqlModel());
						if (mobileNoObj != null && mobileNoObj[0][0] != null
								&& !String.valueOf(mobileNoObj[0][0]).equals("")) {
							su.send(String.valueOf(mobileNoObj[0][0]), message);
						}
						su.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					try {
						MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
						processAlerts.initiate(context, session);
						processAlerts.removeProcessAlert(String.valueOf(codeObj[i][7]), "Travel Claim");
						processAlerts.terminate();
					} catch (Exception e) {

					}

					
					// =======CALL EMAIL TEMPLATE=======ADDED BY REEBA
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template
							.setEmailTemplate("Travel Claim disbursement to applicant");
					template.getTemplateQueries();
					try {
						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);
						templateQuery1.setParameter(1, clmDisbrmnt.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);
						templateQuery2.setParameter(1, String
								.valueOf(codeObj[i][3]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						// Subject + Body
						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, String
								.valueOf(codeObj[i][1]));// EXP_APPID
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery4 = template
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, String
								.valueOf(codeObj[i][0]));// EXP_DISB_BAL_ID
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						// Subject + Body
						EmailTemplateQuery templateQuery5 = template
								.getTemplateQuery(5);
						templateQuery5.setParameter(1, String
								.valueOf(codeObj[i][1]));// EXP_APPID
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						// Subject + Body
						EmailTemplateQuery templateQuery6 = template
								.getTemplateQuery(6);
						templateQuery6.setParameter(1, clmDisbrmnt.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					template.configMailAlert();					
				try {
					
					String ids=String.valueOf(codeObj[i][7]);
					/*String linkParam = "applnStatus=A&status=process&claimApplnCode="
							+ String.valueOf(codeObj[i][7]);*/
					
					String linkParam ="tmsClmAppId="+ids+"&tmsClmStatus=D&disStatus=C&trvlAppId="+ids+
					"&trvlAppCode="+ids;
					String alertlink = "/TMS/TravelExpDisbrsmnt_callView.action";
					                
					
					template.sendProcessManagerAlert(clmDisbrmnt.getUserEmpId(), "Travel Claim", "I",
							String.valueOf(codeObj[i][7]), "1", linkParam,alertlink, "Disbursed",
							String.valueOf(codeObj[i][3]), "0", "", String.valueOf(codeObj[i][3]),
							clmDisbrmnt.getUserEmpId());
				} catch (Exception e) {
					e.printStackTrace();
				}
					// send mail
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}
				result = model.getSqlModel().singleExecute(
						updateDisbStatusQuery, updateObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result) {
			addActionMessage("Claim disbursed successfully");
		} else {
			addActionMessage("Record cannot be saved ");
		}
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
		clmDisbrmnt.setPayModeMap(payModeMap);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
}
