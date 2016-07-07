package org.struts.action.TravelManagement.TravelClaim;

import java.util.TreeMap;
import org.paradyne.bean.TravelManagement.TravelClaim.AdvCaimDisb;
import org.paradyne.lib.sms.SMSUtil;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelClaim.AdvCaimDisbModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author AA0623
 *
 */
public class AdvCaimDisbAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AdvCaimDisbAction.class);
	AdvCaimDisb disb;

	@Override
	public void prepare_local() throws Exception {
		disb = new AdvCaimDisb();
		disb.setMenuCode(974);
	}

	public Object getModel() {
		return disb;
	}

	public AdvCaimDisb getDisb() {
		return disb;
	}

	public void setDisb(AdvCaimDisb disb) {
		this.disb = disb;
	}
	
	public String input() throws Exception {
		try {
			disb.setListType("pending");
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			//CALLS ADVANCE DEFAULTERS LIST
			model.advanceDefaulter(disb);
			// CALLS LIST OF PENDING APPLICATIONS
			model.pendAdvDisbList(disb);
			disb.setStatus("P");
			model.terminate();
			
		} catch (Exception e) {
			logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK
		return INPUT;
	}
	
	/**
	 * @author REEBA JOSEPH
	 * @return
	 * @throws Exception
	 */
	public String acceptClaimDisbursement() throws Exception {
		try {
			String cailmAppId = request.getParameter("cailmAppId");
			String travelApplId = request.getParameter("travelApplId");
			String travelApplCode = request.getParameter("travelApplCode");
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			String updateClaimDisb = " UPDATE TMS_CLAIM_APPL SET EXP_APP_STATUS = 'Q' WHERE EXP_APPID="
					+ cailmAppId
					+ " AND EXP_TRVL_APPID="
					+ travelApplId
					+ " AND EXP_TRVL_APPCODE=" + travelApplCode;
			boolean result = model.getSqlModel().singleExecute(updateClaimDisb);
			if (result)
				addActionMessage("Claim accepted for disbursement");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in acceptClaimDisbursement method : " + e);
			e.printStackTrace();
		}//end of try-catch block
		input();
		return INPUT;
	}// end of method acceptClaimDisbursement
	
	public String deductAdvance() throws Exception {
		AdvCaimDisbModel model = new AdvCaimDisbModel();
		model.initiate(context, session);
		String employeecode = request.getParameter("empCode");
		String applicationid = request.getParameter("applId");
		String advanceAmt = request.getParameter("advanceAmt");
		String status = request.getParameter("status");
		model.editAdvanceDefaulter(disb, employeecode, applicationid, advanceAmt);
		model.terminate();
		if(status.equals("O"))
			getNavigationPanel(1);
		else{
			getNavigationPanel(4);
			disb.setEnableAll("N");
		}
		return "advanceDefaulter";
	}
	
	public String f9debitHead() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
		String query = " SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_NAME";
		
		 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		String[] headers = {"Debit Code","Debit Name"};

		
		  //DEFINE THE PERCENT WIDTH OF EACH COLUMN
		String[] headerWidth = {"20", "80"};

		
		 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 // FIELDNAMES
		String[] fieldNames = { "debitCode","debitName" };

		
		 // SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 // CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 // IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		  
		 //NOTE: COLUMN NUMBERS STARTS WITH 0
		int[] columnIndex = {0,1};

		
		 // WHEN SET TO 'true' WILL SUBMIT THE FORM
		String submitFlag = "false";

		
		 // IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 // FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 // ACTION>_<METHOD TO CALL>.action
		String submitToMethod = "";

		
		 // CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	} 
	
	public String getClosedList() {

		try {
			disb.setListType("closed");
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			model.closedDefaultersList(disb);
			// CALLS LIST OF PENDING APPLICATIONS
			model.closedAdvDisbList(disb, request);
			//model.callClosedRecoveryDtlList(disb, request);
			model.terminate();
			
		} catch (Exception e) {
			logger.error("Exception in getclosed method : " + e);
		}// E
		return INPUT;
	}
	
	//when double click on pending advance disburse list opens a new jsp page pendingAdvDidb.jsp
	//having employee information details and payment details
	public String calforedit() {
		try {
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			String employeecode = request.getParameter("hiddencode");
			String applicationid = request.getParameter("hiddenapplId");
			String applicationcode = request.getParameter("hiddenapplCode");
			String guestflag = request.getParameter("hiddengustflag");
			model.initiate(context, session);
			model.editpendAdvDisbList(disb, employeecode, applicationcode,
					applicationid, guestflag, request);
			//ADDED BY REEBA
			TreeMap<String, String> payModeMap = model.setPayModes(disb);
			disb.setPayModeMap(payModeMap);
			String query = "Select count(*) from  TMS_ADV_DISBURSEMENT where TRVL_APPID="
					+ applicationid + "  AND TRVL_APPCODE=" + applicationcode;
			Object obj[][] = model.getSqlModel().getSingleResult(query);
			//method for retriving saved  grade list when go to next page from policy form
			if (obj != null && obj.length > 0) {
				model.callItList(disb);
			}
			String hidApplStatus = request.getParameter("hidApplStatus");
			logger.info("hidApplStatus============" + hidApplStatus);
			;
			model.terminate();
			if (hidApplStatus.trim().equals("Revoked")) {
				disb.setShowRevokeStatus("true");
				getNavigationPanel(4);
			} else if (hidApplStatus.trim().equals("T")) {
				getNavigationPanel(4);
			} else {
				disb.setShowRevokeStatus("false");
				getNavigationPanel(1);
			}
			
		String btnPanelQuery =" SELECT * FROM TMS_ADV_DISBURSEMENT where TRVL_APPID="+applicationid;
		Object[][]dataObj=model.getSqlModel().getSingleResult(btnPanelQuery);
		
		if(dataObj!=null && dataObj.length>0)
		{
			getNavigationPanel(4);
		}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendingadvdisburse";
	}

	/**
	 * edit for closed application
	 * @return
	 */
	public String callForclosedEdit() {
		try {
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			String closedemployeecode = request
					.getParameter("closedhiddencode");
			String closedapplicationid = request
					.getParameter("closedhiddenapplId");
			String closedapplicationcode = request
					.getParameter("closedhiddenapplCode");
			model.initiate(context, session);
			model.editclosedAdvDisbList(disb, closedemployeecode,
					closedapplicationcode, closedapplicationid, request);
			disb.setClosedadvapplId(closedapplicationid);
			disb.setClosedadvapplCode(closedapplicationcode);
			model.callClosedItList(disb, request);
			//ADDED BY REEBA
			TreeMap<String, String> payModeMap = model.setPayModes(disb);
			disb.setPayModeMap(payModeMap);
			model.terminate();
			getNavigationPanel(4);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "closedadvancelist";
	}
	
	//removing from list in pendingAdvance.jsp
	public String removeRow() {
		String empid = disb.getPendingEmpId();
		String applid = request.getParameter("pendingapplId");
		String applCode = request.getParameter("pendingapplCode");
		String date[] = request.getParameterValues("itpaymentdate");
		String paymode[] = request.getParameterValues("itpaymentmode");
		String amount[] = request.getParameterValues("itamount");
		String comment[] = request.getParameterValues("itcomment");
		String[] chkno = request.getParameterValues("chkno");
		String[] checkdate = request.getParameterValues("checkdate");
		String[] accountno = request.getParameterValues("accountno");
		String[] bankname = request.getParameterValues("bankname");
		String[] bankid = request.getParameterValues("itbankid");
		String[] rownum = request.getParameterValues("rownum");
		String guestflag = request.getParameter("hiddengustflag");
		
		//ADDED BY REEBA
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");

		String hiddenchkfld[] = request.getParameterValues("hdeleteSkill");
		AdvCaimDisbModel model = new AdvCaimDisbModel();
		model.initiate(context, session);
		model.removeJourneyDtls(disb, date, paymode,
				amount, hiddenchkfld, comment, chkno, checkdate, accountno,
				bankname, bankid,rownum,month,year);
		model.editpendAdvDisbList(disb, empid, applCode, applid,guestflag,request);
		//ADDED BY REEBA
		TreeMap<String, String> payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);
		model.terminate();
		getNavigationPanel(1);
		return "pendingadvdisburse";

	}
	public String saveFun() {
		String date[] = request.getParameterValues("itpaymentdate");
		String paymode[] = request.getParameterValues("itpaymentmode");
		String amount[] = request.getParameterValues("itamount");
		String comment[] = request.getParameterValues("itcomment");
		String applid = request.getParameter("pendingapplId");
		String applCode = request.getParameter("pendingapplCode");
		String advpaid = request.getParameter("hiddenAdvPaid");
		String balamount = request.getParameter("hiddenBalanceAmt");
		String reqrdadvamount = request.getParameter("pendingadvAmount");
		String status = request.getParameter("status");//not used
		String[] chkno = request.getParameterValues("chkno");
		String[] checkdate = request.getParameterValues("checkdate");
		String[] accountno = request.getParameterValues("accountno");
		String[] bankid = request.getParameterValues("itbankid");
		//ADDED BY REEBA
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");
		AdvCaimDisbModel model = new AdvCaimDisbModel();
		model.initiate(context, session);
		
		logger.info("######### STATUS ##################"+status);
		status = "T";
		
		boolean result = model.save(disb, date, paymode, amount, comment,
				applid, applCode, advpaid, balamount, reqrdadvamount, status,
				chkno, checkdate, accountno, bankid, month, year);
		if (result) {
				addActionMessage("Advance added to statement");
		} else {
				addActionMessage("Advance could not be added");
		}
		//ADDED BY REEBA
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TreeMap<String, String> payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);
		
		model.terminate();
		getNavigationPanel(1);
		return INPUT;

	}

	public String returnHome() {
		try {
			input();
		} catch (Exception e) {
			logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK
		return INPUT;
	}

	//for selscting bank name from search window
	public String f9Bank() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  BANK_MICR_CODE, NVL(BANK_NAME,' ') , BANK_IFSC_CODE, NVL(BRANCH_NAME, ' '), NVL(BANK_BSR_CODE,' ') " 
			+ " FROM HRMS_BANK   ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Bank Micr Code","Bank Name", "IFSC Code", "Branch", "Bank BSR Code" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20","25","20","25","10" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "bankid", "bank","bankIFSCCode","bankBranch", "bankBSRCode"  };

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

	//for adding the row
	public String addRow() {
		System.out.println("Add Row---------------------->"+disb.getPendingEmpId());
		String empid = disb.getPendingEmpId();
		String applid = request.getParameter("pendingapplId");
		String applCode = request.getParameter("pendingapplCode");
		String SrNo[] = request.getParameterValues("SrNo");
		String date[] = request.getParameterValues("itpaymentdate");
		String paymode[] = request.getParameterValues("itpaymentmode");
		String amount[] = request.getParameterValues("itamount");
		String comment[] = request.getParameterValues("itcomment");
		String[] chkno = request.getParameterValues("chkno");
		String[] checkdate = request.getParameterValues("checkdate");
		String[] accountno = request.getParameterValues("accountno");
		String[] bankname = request.getParameterValues("bankname");
		String[] bankid = request.getParameterValues("itbankid");
		String[] rownum = request.getParameterValues("rownum");
		String guestflag = request.getParameter("hiddengustflag");
		//ADDED BY REEBA
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");
		AdvCaimDisbModel model = new AdvCaimDisbModel();
		model.initiate(context, session);
		if (!disb.getCheckEdit().equals("")) {
			model.modData(disb, SrNo, date, paymode, chkno, checkdate,
					accountno, bankname, amount, comment, bankid,rownum, month, year);
		} else {

			model.addData(disb, SrNo, date, paymode, chkno, checkdate,
					accountno, bankname, amount, comment, bankid,rownum, month, year);
		}
		model.editpendAdvDisbList(disb, empid, applCode, applid,guestflag,request);
		//ADDED BY REEBA
		TreeMap<String, String> payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);
		disb.setPaymentDate("");
		disb.setCheckNumber("");
		disb.setChkDate("");
		disb.setAccount("");
		disb.setComment("");
		disb.setCheckEdit("");
		model.terminate();
		getNavigationPanel(1);
		return "pendingadvdisburse";

	}
	
	public String f9division() throws Exception {
		
		try {
			String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
			if (disb.getUserProfileDivision() != null && disb.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN (" + disb.getUserProfileDivision()
						+ ")";
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
	
	public String generateBankStatementReport(){
		try {
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			model.initiate(context, session);
			model.generateStatementReport(disb, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//method must remove from here
	public String sendAdvanceDisbursementMailSMS(){
		try {
			AdvCaimDisbModel model = new AdvCaimDisbModel();
			boolean result = false;
			try {
				String[] applCode = request.getParameterValues("hdeleteCode");
				String[] checkedBox = request.getParameterValues("checkedBox");
				String[] applCodeValues = new String[checkedBox.length];

				if (checkedBox != null && checkedBox.length > 0) {
					for (int i = 0, j = 0; i < applCode.length; i++) {
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
				try {
					String empIdQuery = "SELECT TRVL_APPID, APPL_EMP_CODE, TRVL_APPCODE, APPL_TRVL_ID, NVL(ADV_DISB_ADVANCE_AMT,0) FROM TMS_ADV_DISBURSEMENT "
							+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_CODE=TMS_ADV_DISBURSEMENT.TRVL_APPCODE "
							+ " AND TMS_APP_EMPDTL.APPL_ID=TMS_ADV_DISBURSEMENT.TRVL_APPID) "
							+ " WHERE TRVL_APPCODE IN ( " + disbApplCode + ")";

					Object codeObj[][] = model.getSqlModel().getSingleResult(
							empIdQuery);

					String updateDisbStatusQuery = "UPDATE TMS_ADV_DISBURSEMENT  SET ADV_DISB_STATUS = 'C'"
							+ " WHERE TRVL_APPCODE = ? AND TRVL_APPID = ? ";

					if (codeObj != null && codeObj.length > 0) {
						Object updateObj[][] = new Object[codeObj.length][2];
						for (int i = 0; i < codeObj.length; i++) {

							updateObj[i][0] = String.valueOf(codeObj[i][2]);
							updateObj[i][1] = String.valueOf(codeObj[i][0]);

							logger.info("Travel SMS Util");
							Object[][] mobileNoObj = model
									.getSqlModel()
									.getSingleResult(
											"SELECT APPL_EMP_CONTACT FROM TMS_APP_EMPDTL WHERE APPL_ID="
													+ String
															.valueOf(codeObj[i][0])
													+ " AND APPL_EMP_CODE="
													+ String
															.valueOf(codeObj[i][1]));
							;
							String message = "Tour Id "
									+ String.valueOf(codeObj[i][3])
									+ " advance amount of Rs."
									+ String.valueOf(codeObj[i][4])
									+ " is disbursed to your account.";
							SMSUtil su = new SMSUtil();
							su.init(model.getSqlModel());
							if (mobileNoObj != null
									&& mobileNoObj[0][0] != null
									&& !String.valueOf(mobileNoObj[0][0])
											.equals("")) {
								su.send(String.valueOf(mobileNoObj[0][0]),
										message);
							}
							su.close();

							//=======CALL EMAIL TEMPLATE=======ADDED BY REEBA
							EmailTemplateBody template = new EmailTemplateBody();
							template.initiate(context, session);
							template
									.setEmailTemplate("Travel Advance disbursement to applicant");
							template.getTemplateQueries();
							try {
								EmailTemplateQuery templateQuery1 = template
										.getTemplateQuery(1); // FROM
								// EMAIL
								templateQuery1.setParameter(1, disb
										.getUserEmpId());
							} catch (Exception e) {

							}
							try {
								EmailTemplateQuery templateQuery2 = template
										.getTemplateQuery(2); // TO
								// EMAIL
								logger.info("employeecode========="
										+ String.valueOf(codeObj[i][1]));
								templateQuery2.setParameter(1, String
										.valueOf(codeObj[i][1]));
							} catch (Exception e) {

							}
							try {
								// Subject + Body
								EmailTemplateQuery templateQuery3 = template
										.getTemplateQuery(3);
								templateQuery3.setParameter(1, String
										.valueOf(codeObj[i][1]));//APPL_EMP_CODE
								templateQuery3.setParameter(2, String
										.valueOf(codeObj[i][0]));//TMS_APP_EMPDTL.APPL_ID
								templateQuery3.setParameter(3, String
										.valueOf(codeObj[i][2]));//TMS_APP_EMPDTL.APPL_CODE
							} catch (Exception e) {

							}
							try {
								EmailTemplateQuery templateQuery4 = template
										.getTemplateQuery(4);
								templateQuery4.setParameter(1, String
										.valueOf(codeObj[i][2]));//TMS_ADV_DISBURSEMENT.TRVL_APPCODE
								templateQuery4.setParameter(2, String
										.valueOf(codeObj[i][0]));//TMS_ADV_DISBURSEMENT.TRVL_APPID
							} catch (Exception e) {

							}
							try {
								// Subject + Body
								EmailTemplateQuery templateQuery5 = template
										.getTemplateQuery(5);
								templateQuery5.setParameter(1, String
										.valueOf(codeObj[i][1]));//APPL_EMP_CODE
								templateQuery5.setParameter(2, String
										.valueOf(codeObj[i][0]));//TMS_APP_EMPDTL.APPL_ID
								templateQuery5.setParameter(3, String
										.valueOf(codeObj[i][2]));//TMS_APP_EMPDTL.APPL_CODE
							} catch (Exception e) {

							}
							try {
								// Subject + Body
								EmailTemplateQuery templateQuery6 = template
										.getTemplateQuery(6);
								templateQuery6.setParameter(1, disb
										.getUserEmpId());//EXP_APPID
							} catch (Exception e) {

							}

							try {
								template.configMailAlert();
								
								
						 	try {
									MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
									processAlerts.initiate(context, session);
									processAlerts.removeProcessAlert(String.valueOf(codeObj[i][0]), "Travel");
									processAlerts.terminate();
								} catch (Exception e) {

								}

								try {
									String linkParam = "applicationId="+ String.valueOf(codeObj[i][0]);
									String alertlink = "/TMS/TravelApplication_viewApplications.action";
									 
									template.sendProcessManagerAlert(disb
											.getUserEmpId(), "Travel", "I",
											String.valueOf(codeObj[i][0]), "1",
											linkParam, alertlink,
											"Advance Disbursed", String
													.valueOf(codeObj[i][1]),
											"0", "", String
													.valueOf(codeObj[i][1]),
											disb.getUserEmpId());
								} catch (Exception e) {
									// TODO: handle exception
								} 
								//SEND CC
								/*String ccIds = "";
								String ccQuery = " SELECT APPL_INITIATOR, APPL_MAIN_APPROVER, APPL_KEEP_INFO "
										+ " FROM TMS_APPLICATION WHERE APPL_ID=" + applid;
								Object[][] ccObj = model.getSqlModel().getSingleResult(ccQuery);
								if (ccObj != null && ccObj.length > 0) {
									ccIds = ccObj[0][0] + "," + ccObj[0][1] + "," + ccObj[0][2];
								}
								logger.info("ccIds========" + ccIds);
								if (ccIds != null) {
									template.sendApplicationMailToKeepInfo(ccIds);
								}*/
								// send mail
								template.sendApplicationMail();
								template.clearParameters();
								template.terminate();
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						result = model.getSqlModel().singleExecute(
								updateDisbStatusQuery, updateObj);
					}
				} catch (Exception e) {

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result) {
				addActionMessage("Advance disbursed successfully");
			} else {
				addActionMessage("Record cannot be saved ");
			}
			try {
				input();
			} catch (Exception e) {
				e.printStackTrace();
			}
			TreeMap<String, String> payModeMap = model.setPayModes(disb);
			disb.setPayModeMap(payModeMap);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String sendClaimDisbursementMailSMS() {

		AdvCaimDisbModel model = new AdvCaimDisbModel();
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
					+ " EXP_TRVL_APPCODE AS TRVL_APP_CODE, EXP_APP_EMPID AS EMP_ID, EXP_TRVL_APPID, EXP_TRVL_ID, EXP_DISB_BALANCE_AMT FROM TMS_EXP_DISBURSEMENT "
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
						logger.info("Travel SMS Util");
						Object[][] mobileNoObj = model.getSqlModel()
								.getSingleResult(
										"SELECT APPL_EMP_CONTACT FROM TMS_APP_EMPDTL WHERE APPL_ID="
												+ String.valueOf(codeObj[i][4]));
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
					
					// =======CALL EMAIL TEMPLATE=======ADDED BY REEBA
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template
							.setEmailTemplate("Travel Claim disbursement to applicant");
					template.getTemplateQueries();
					try {
						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);
						templateQuery1.setParameter(1, disb.getUserEmpId());
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
						templateQuery6.setParameter(1, disb.getUserEmpId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					template.configMailAlert();
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
		TreeMap<String, String> payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);

		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String clearFilter(){
		AdvCaimDisbModel model = new AdvCaimDisbModel();
		model.initiate(context, session);
		disb.setDivisionCode("");
		disb.setDivisionName("");
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TreeMap<String, String> payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String saveDefaulterData(){
		AdvCaimDisbModel model = new AdvCaimDisbModel();
		model.initiate(context, session);
		try {
			String date[] = request.getParameterValues("itpaymentdate");
			String paymode[] = request.getParameterValues("itpaymentmode");
			String amount[] = request.getParameterValues("itamount");
			String comment[] = request.getParameterValues("itcomment");
			String applid = request.getParameter("pendingapplId");
			String applCode = request.getParameter("pendingapplCode");
			String advpaid = request.getParameter("hiddenAdvPaid");
			String balamount = request.getParameter("hiddenBalanceAmt");
			String reqrdadvamount = request.getParameter("pendingadvAmount");
			String[] chkno = request.getParameterValues("chkno");
			String[] checkdate = request.getParameterValues("checkdate");
			String[] accountno = request.getParameterValues("accountno");
			String[] bankid = request.getParameterValues("itbankid");
			//ADDED BY REEBA
			String[] month = request.getParameterValues("month");
			String[] year = request.getParameterValues("year");

			String status = "C";
			boolean result = model.saveDefaulterDetails(disb, date, paymode, amount, comment,
					applid, applCode, advpaid, balamount, reqrdadvamount,
					status, chkno, checkdate, accountno, bankid, month, year);
			if (result) {
				addActionMessage("Advance deducted successfully");
			} else {
				addActionMessage("Advance could not be deducted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TreeMap<String, String> payModeMap = model.setPayModes(disb);
		disb.setPayModeMap(payModeMap);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
}
