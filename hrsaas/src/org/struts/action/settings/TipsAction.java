package org.struts.action.settings;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

import org.paradyne.bean.settings.TipsBean;
import org.paradyne.lib.email.HtmlEmail;
import org.paradyne.model.settings.TipsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Nilesh Dhandare 12th Jan 2012.
 * 
 */
public class TipsAction extends ParaActionSupport {

	TipsBean tipsBean;
	String poolDir = "";
	String fileName = "";

	String[][] fromMailId = null;
	int mailCount = 0;
	HtmlEmail email = null;
	static PrintWriter output = null;
	static PrintWriter out = null;
	static String imagePath = "";
	int fireCounter = 0;

	public String getPoolDir() {
		return poolDir;
	}

	public void setPoolDir(String poolDir) {
		this.poolDir = poolDir;
	}

	public void prepare_local() throws Exception {

		tipsBean = new TipsBean();
		poolDir = String.valueOf(session.getAttribute("session_pool"));
		tipsBean.setMenuCode(2280);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {

		return tipsBean;
	}

	/**
	 * @return the tipsBean
	 */
	public TipsBean getTipsBean() {
		return tipsBean;
	}

	/**
	 * @param tipsBean
	 *            the tipsBean to set
	 */
	public void setTipsBean(TipsBean tipsBean) {
		this.tipsBean = tipsBean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TipsModel model = new TipsModel();
		model.initiate(context, session);
		tipsBean.setDivFlag_TS(true);
		tipsBean.setHiddenDivId("TS");
		showOnlyInfo();
		model.terminate();
	}

	/**
	 *  Method : saveTips(). Purpose : Save Tips
	 * Page Information.
	 * 
	 * @return String.
	 */
	public String saveTips() {
		try {

			TipsModel model = new TipsModel();
			model.initiate(context, session);
			// path to write XML
			fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\tips_info\\tips.xml";
			int value = model.saveTips(tipsBean, "save", fileName);
			resetTips();
			tipsBean.setCheckFlag_TS("true");
			if (value == 0)
				addActionMessage("Duplicate Entry Found. Record cant be Added!");
			else if (value == 1)
				addActionMessage("Record Saved Successfully");
			else
				addActionMessage("Record Modified Successfully");
			tipsBean.setActiveTip("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**
	 * Method : resetTips(). Purpose : Reset Tips
	 * Info Data.
	 * 
	 * @return String
	 */
	public String resetTips() {
		try {
			tipsBean.setUploadTs("");
			tipsBean.setTipsName("");
			tipsBean.setLinkTs("");
			tipsBean.setHiddenCode_TS("");
			tipsBean.setCheckFlag_TS("true");
			tipsBean.setActiveTip("false");
			tipsBean.setDivisionCode("");
			tipsBean.setDivisionName("");
			showTipsLink();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";

	}

	/**
	 * Method : editTs(). Purpose : Edit Tips
	 * List Data.
	 * 
	 * @return String.
	 */
	public String editTs() {
		TipsModel model = new TipsModel();
		model.initiate(context, session);
		try {
			model.editTs(tipsBean);
			model.saveTips(tipsBean, "show", poolDir);
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		model.terminate();
		return "input";
	}

	/**
	 * Method :deleteTs(). Purpose : Delete
	 * Particular Record.
	 * 
	 * @return String.
	 */
	public String deleteTs() {
		TipsModel model = new TipsModel();
		model.initiate(context, session);
		// path to write XML
		try {
			fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\tips_info\\tips.xml";
			boolean result = model.deleteTs(tipsBean, fileName);
			if (result) {
				addActionMessage("Record Deleted Successfully!");
				tipsBean.setUploadTs("");
				tipsBean.setTipsName("");
				tipsBean.setLinkTs("");
				tipsBean.setHiddenCode_TS("");
			} // END if
			else {
				addActionMessage("Record Can't be Deleted!");
			}// END else
			showTipsLink();
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		model.terminate();
		return "input";
	}

	/**
	 * Method : showTipsLink(). Purpose : Show Tips info after saving
	 * @return String.
	 */
	public String showTipsLink() {
		try {
			TipsModel model = new TipsModel();
			model.initiate(context, session);
			model.saveTips(tipsBean, "show", poolDir);
		} catch (Exception e) {

		}
		return "input";
	}

	/**
	 * Displaying the details after saving All Home Page Settings function
	 * @return
	 */
	public String showOnlyInfo() {
		try {

			TipsModel model = new TipsModel();
			resetDivFlag();
			model.initiate(context, session);
			String divId = tipsBean.getHiddenDivId();
			if (divId.equals("TS")) {
				model.saveTips(tipsBean, "show", poolDir);
				tipsBean.setDivFlag_TS(true);
			}
			model.terminate();
			return "input";
		} catch (Exception e) {
			e.printStackTrace();
			return "input";
		}
	}

	public void resetDivFlag() {
		tipsBean.setDivFlag_TS(false);

	}

	public String publishTips() {
		return "viewReq";
	}

	public String sendData() {
		TipsModel model = new TipsModel();
		model.initiate(context, session);

		String subject = tipsBean.getSubject().trim();
		String myTextBody = tipsBean.getMyTextarea().trim();

		try {
			getEmployeeData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public String[][] getDefaultMailIds() {
		TipsModel model = new TipsModel();
		model.initiate(context, session);
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = model.getSqlModel().getSingleResult(fromQuery);
		String[][] mailIds = new String[fromEmp.length][2];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i][0] = String.valueOf(fromEmp[i][0]);
			mailIds[i][1] = String.valueOf(fromEmp[i][1]);
		}
		model.terminate();

		return mailIds;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean getEmployeeData() {
		try {
			
			TipsModel model = new TipsModel();
			model.initiate(context, session);
			System.out
					.println("In getEmployeeData----------------------------");

			String settingQry = "SELECT AUTO_SEND,INDIV_DEPT,ALL_DEPT,INDIV_BRANCH,ALL_BRANCH,INDIV_DESG,ALL_DESG,INDIV_DIV,ALL_DIV,TEMPLATE_ID ,NVL(TEMP_FLAG,'S'),NVL(INDIV_EMP,'N'),NVL(INCLUDE_DEPT,'Y'), NVL(INCLUDE_DESG,'N'), NVL(INCLUDE_EMAIL,'N'), NVL(INCLUDE_PHONE,'N'),NVL(GROUP_ID,'N'),NVL(GROUP_MAILID,'') FROM HRMS_BIRTHDAYMAIL_SETTINGS ";
			// //
			Object[][] settingObj = model.getSqlModel().getSingleResult(
					settingQry);
	
			//Object templateObj[][] = null;
			String[][] fromMailIds = getDefaultMailIds();
			
			// if indv.. div
			if (false) {
				//getIndividualDivMail(settingObj);
			}// end of individual division if
			// forr loop
			else {
				
				
			
			
					try {
						/*System.out
								.println("E111111111111111111111111111111111111111111");
						String dept = "";
						String branch = "";
						String rank = "";
						String div = "";
						String empId = "";

				

						dept = dept.substring(0, dept.length() - 1);
						branch = branch.substring(0, branch.length() - 1);
						rank = rank.substring(0, rank.length() - 1);
						div = div.substring(0, div.length() - 1);
						empId = empId.substring(0, empId.length() - 1);*/
					
						String toEmpQry = " SELECT HRMS_EMP_OFFC.EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
								+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
								+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
								+ " WHERE   HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P' "
								+ " AND ADD_EMAIL IS NOT NULL ";

						/**
						 * FOR INDIVIDUAL EMPLOYEE
						 */
						;
						Object[][] toEmpObj = model.getSqlModel()
								.getSingleResult(toEmpQry);

						// "reeba.joseph@glodyne.com",
						String[] toEmpAddArr = new String[toEmpObj.length];
						if (toEmpObj != null && toEmpObj.length > 0) {
							for (int j = 0; j < toEmpObj.length; j++) {
								toEmpAddArr[j] = String.valueOf(toEmpObj[j][1]);
								System.out
										.println("toEmpAddArr[j] -----@@@@@@@@@@@@-----"
												+ toEmpAddArr[j]);

							} // end of loop
						} // end of if
						else {
							return false;
						} // end of else

						// Added by Lakkichand for sending mails to
						// Group mail Id
						if (String.valueOf(settingObj[0][16]).equals("Y")) {
							String groupId_str = String
									.valueOf(settingObj[0][17]);
							String[] groupIds = groupId_str.split(",");
							toEmpAddArr = new String[groupIds.length];
							for (int i = 0; i < groupIds.length; i++) {
								toEmpAddArr[i] = groupIds[i];
								System.out
										.println("toEmpAddArr[i] Group Mails -----&&&&&&&&&&&&&&&-----"
												+ toEmpAddArr[i]);

							}
						}
					
						Object[][] mailBoxData = getServerMailBox("",
								fromMailIds[0][0]);

						System.out.println("TOTAL NO OF RECORD :"
								+ toEmpAddArr.length);
						if (String.valueOf(settingObj[0][10]).equals("S")) {

							String subject = tipsBean.getSubject().trim();
							String mailData = tipsBean.getMyTextarea().trim();
							sendMail(toEmpAddArr, fromMailIds, subject,
									mailData, mailBoxData);

						} else {
		
							String subject = tipsBean.getSubject().trim();
							String mailData = tipsBean.getMyTextarea().trim();
							sendMail(toEmpAddArr, fromMailIds, subject,
									mailData, mailBoxData);
						}

					} catch (Exception e) {
						
						System.out.println("Exception while executing query :"+ e);
						e.printStackTrace();
						System.out.println(e.getMessage());
				
						return false;
					} // end of catch
				} // end if 2
			
		} catch (Exception e) {
			
			System.out.println("Exception in method " + e);
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		return true;
	}

/*	private boolean getIndividualDivMail(Object[][] settingObj) {

		try {
			TipsModel model = new TipsModel();
			model.initiate(context, session);

			String companyQry = " SELECT COMPANY_NAME ,TO_CHAR(SYSDATE,'DDth MON') FROM HRMS_COMPANY ";
			Object[][] companyObj = model.getSqlModel().getSingleResult(
					companyQry);
			Object templateObj[][] = null;
			String[][] fromMailIds = getDefaultMailIds();
			String divQuery = "SELECT DIV_ID FROM HRMS_DIVISION ";
			Object[][] divObj = model.getSqlModel().getSingleResult(divQuery);
			if (divObj != null && divObj.length > 0) {

				for (int k = 0; k < divObj.length; k++) {
					System.out
							.println(" inside empList query ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; ");
					String empList = "SELECT  EMP_FNAME,HRMS_EMP_OFFC.EMP_ID,NVL(EMP_DEPT,''),"
							+ " NVL(EMP_CENTER,''),  NVL(EMP_RANK,''),NVL(EMP_DIV, ''),"
							+ " NVL(INITCAP(EMP_FNAME),' ')||' '||NVL(INITCAP(EMP_LNAME),' '),"
							+ " NVL(DEPT_NAME,''),NVL(INITCAP(CENTER_NAME),' '),NVL(RANK_NAME,' ') "
							+ " ,NVL(ADD_EMAIL,' '),NVL(ADD_MOBILE,' ') "
							+ "	FROM HRMS_EMP_OFFC   "
							+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
							+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
							+ "	INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
							+ "	INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
							+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
							+ "	WHERE EMP_STATUS='S' AND EMP_DIV="
							+ String.valueOf(divObj[k][0]);
					Object[][] empBdayListObj = model.getSqlModel()
							.getSingleResult(empList);

					String templateData = " SELECT TEMPLATE_BODY,TEMPLATE_ID FROM HRMS_BIRTHDAYTEMPLATE_HDR WHERE TEMPLATE_ID IN ( "
							+ " "
							+ String.valueOf(settingObj[0][9])
							+ " ) AND TEMPLATE_USE_FLAG='N' ORDER BY TEMPLATE_ID ";

					

					if (empBdayListObj != null && empBdayListObj.length > 0) // if 2
					{
						try {

							String dept = "";
							String branch = "";
							String rank = "";
							String div = "";
							String empId = "";

							String replaceWithTable = " <center><DIV style='align:center;width: 750px;'> ";

							for (int j = 0; j < empBdayListObj.length; j++) {

								dept += String.valueOf(empBdayListObj[j][2])
										+ ",";
								branch += String.valueOf(empBdayListObj[j][3])
										+ ",";
								rank += String.valueOf(empBdayListObj[j][4])
										+ ",";
								div += String.valueOf(empBdayListObj[j][5])
										+ ",";

								empId += String.valueOf(empBdayListObj[j][1])
										+ ",";
								
								replaceWithTable += "<DIV style='float:left;width:320px;text-align:left'>"
										+ String.valueOf(empBdayListObj[j][6])
										+ "</DIV><DIV style='float:left;text-align:left'> ";

								*//***********************************************
								 * if desg....yes add desg
								 *//*
								if (String.valueOf(settingObj[0][13]).equals(
										"Y")) {
									replaceWithTable += String
											.valueOf(empBdayListObj[j][9])
											+ "<br>  ";

								}
								*//***********************************************
								 * 
								 * 
								 * /***********************************************
								 * if dept....yes add dept
								 *//*
								if (String.valueOf(settingObj[0][12]).equals(
										"Y")) {
									replaceWithTable += String
											.valueOf(empBdayListObj[j][7])
											+ "<br>  "
											+ String
													.valueOf(empBdayListObj[j][8])
											+ "<br>  ";
								}
								*//***********************************************
								 * 
								 * /***********************************************
								 * if email....yes add email
								 *//*
								if (String.valueOf(settingObj[0][14]).equals(
										"Y")) {
									replaceWithTable += String
											.valueOf(empBdayListObj[j][10])
											+ "<br>  ";
								}
								*//***********************************************
								 * if phone....yes add phone
								 *//*
								if (String.valueOf(settingObj[0][15]).equals(
										"Y")) {
									replaceWithTable += String
											.valueOf(empBdayListObj[j][11]);
								}
								replaceWithTable += "<br><br>";
								replaceWithTable += "</DIV><br><br><br>";

								System.out
										.println("h11111111111-----------------------------------------------");

							}

							replaceWithTable += " </DIV></center> ";
							System.out
									.println("h22222222-----------------------------------------------");
							dept = dept.substring(0, dept.length() - 1);
							branch = branch.substring(0, branch.length() - 1);
							rank = rank.substring(0, rank.length() - 1);
							div = div.substring(0, div.length() - 1);
							empId = empId.substring(0, empId.length() - 1);
							// int cnt=0;
							// for (int i = 0; i < empBdayObj.length; i++) {
							System.out
									.println("h33333333333-----------------------------------------------");
							templateObj = model.getSqlModel().getSingleResult(
									templateData);

							if (templateObj != null && templateObj.length == 0) {
								System.out
										.println("h4444444444444-----------------------------------------------");

								String update = "UPDATE HRMS_BIRTHDAYTEMPLATE_HDR SET TEMPLATE_USE_FLAG='N' ";
								model.getSqlModel().singleExecute(update);
								templateObj = model.getSqlModel()
										.getSingleResult(templateData);
							}

							String toEmpQry = " SELECT HRMS_EMP_OFFC.EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
									+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
									+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
									+ " WHERE   HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P' "
									+ " AND ADD_EMAIL IS NOT NULL  AND EMP_DIV="
									+ String.valueOf(divObj[k][0]);
							System.out
									.println("h55555555555555-----------------------------------------------");

							toEmpQry += "  ORDER BY EMP_ID ";
							Object[][] toEmpObj = model.getSqlModel()
									.getSingleResult(toEmpQry);
							System.out
									.println("toEmpObj.length here is ::::::::::::::::::::::::::::::: = "
											+ toEmpObj.length);

							String[] toEmpAddArr = new String[toEmpObj.length];
							if (toEmpObj != null && toEmpObj.length > 0) {
								for (int j = 0; j < toEmpObj.length; j++) {
									toEmpAddArr[j] = String
											.valueOf(toEmpObj[j][1]);
									System.out
											.println("toEmpAddArr[j] -----------"
													+ toEmpAddArr[j]);

								}
							} else {
								return false;
							} // end of else

							// Added by Lakkichand for sending mails to Group
							// mail id

							if (String.valueOf(settingObj[0][16]).equals("Y")) {
								String groupId_str = String
										.valueOf(settingObj[0][17]);
								String[] groupIds = groupId_str.split(",");
								toEmpAddArr = new String[groupIds.length];
								for (int i = 0; i < groupIds.length; i++) {
									toEmpAddArr[i] = groupIds[i];
									System.out
											.println("toEmpAddArr[i] of group employees ------------  "
													+ toEmpAddArr[i]);

								}
							}

							String messageText = String
									.valueOf(templateObj[0][0]);
							messageText = messageText.replace(
									"&lt;#COMPANY_NAME#&gt;", String
											.valueOf(companyObj[0][0]));

							messageText = messageText.replace(
									"&lt;#BIRTH_DATE#&gt;", String
											.valueOf(companyObj[0][1]));

							messageText = messageText.replace(
									"&lt;#EMP_LIST#&gt;", replaceWithTable);

							Object[][] mailBoxData = getServerMailBox("",
									fromMailIds[0][0]);

							System.out
									.println("H77777777777---------------------------------------");
							System.out.println("TOTAL NO OF RECORD :"
									+ toEmpAddArr.length);
							if (String.valueOf(settingObj[0][10]).equals("S")) {

								String subject = tipsBean.getSubject().trim();
								System.out
										.println("subject get here ===================== "
												+ subject);
								String mailData = tipsBean.getMyTextarea()
										.trim();
								System.out
										.println("mail composer data here we get  ===================== "
												+ mailData);
								sendMail(toEmpAddArr, fromMailIds, subject,
										mailData, mailBoxData);

							} else {
								String[] multiMessageText = new String[templateObj.length];
								for (int j = 0; j < multiMessageText.length; j++) {
									String multimessageText = String
											.valueOf(templateObj[j][0]);
									multimessageText = tipsBean.getSubject()
											.trim();
									multiMessageText[j] = multimessageText;
								}

								String textBodyMss = "";
								textBodyMss = String
										.valueOf(multiMessageText[0]);
								String update = "UPDATE HRMS_BIRTHDAYTEMPLATE_HDR SET TEMPLATE_USE_FLAG='Y' WHERE TEMPLATE_ID="
										+ templateObj[0][1];
								model.getSqlModel().singleExecute(update);
								String subject = tipsBean.getSubject().trim();
								String mailData = tipsBean.getMyTextarea()
										.trim();
								sendMail(toEmpAddArr, fromMailIds, subject,
										mailData, mailBoxData);
							}

							// }

						} catch (Exception e) {
							System.out.println();
							System.out
									.println("Exception while executing query :"
											+ e);
							e.printStackTrace();
							System.out.println(e.getMessage());
							System.out.println();
							return false;
						} // end of catch
					} // end if 2
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}*/

	/**
	 * 
	 * @param empId
	 * @return
	 */
	public Object[][] getServerMailBox(String empId, String fromMailId) {
		TipsModel model = new TipsModel();
		model.initiate(context, session);

		System.out.println("empId  in getServerMailBox   " + empId);
		System.out.println("fromMailId  in getServerMailBox   " + fromMailId);
		String getMailBox = "";
		Object[][] empMailBoxData = null;
		Object[][] sysMailData = null;

		if (empId != null && !empId.equals("")) {// this is for pop before
			// smtp check
			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,EMAIL_USER_NAME,EMAIL_USER_PASS, SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,EMAIL_USER_NAME  "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG " // newly
					// added
					// for
					// logon
					// using
					// same
					// or
					// different
					// Id
					+ " NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER  "
					+ " INNER JOIN HRMS_EMAIL_ACCOUNT ON (HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE=HRMS_EMAIL_SERVER.SERVER_CODE) "
					+ " WHERE EMAIL_EMP_ID="
					+ empId
					+ "  AND EMAIL_OFFICIAL_FLAG='Y'";
			empMailBoxData = model.getSqlModel().getSingleResult(getMailBox);
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = model.getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						System.out.println("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						System.out.println("fromMailId   emp Id present:"
								+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
			System.out.println(" in POP BEFORE SMTP");
		}
		if (empMailBoxData == null || empMailBoxData.length == 0) {

			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,' ',' ',SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,'' "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD,SERVER_USESYSTEMMAILID_FLAG " // newly
					// added
					// for
					// logon
					// using
					// same
					// or
					// different
					// Id
					+ " ,NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER "
					+ " WHERE SERVER_SYSTEMMAIL_FLAG='Y' ";
			empMailBoxData = model.getSqlModel().getSingleResult(getMailBox);
			System.out.println(" ELSE POP");
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = model.getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						System.out.println("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						System.out.println("fromMailId   emp Id present:"
								+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
		}
		if (sysMailData != null && sysMailData.length > 0) {
			for (int i = 0; i < sysMailData[0].length; i++) {
				System.out.println("  EMAIL SERVER DATA  : "
						+ String.valueOf(sysMailData[0][i]));
			}
		}
		if (empMailBoxData != null && empMailBoxData.length > 0) {
			for (int i = 0; i < empMailBoxData.length; i++) {
				for (int j = 0; j < empMailBoxData[0].length; j++) {
					System.out.println("empMailBoxData   [" + i + "][" + j
							+ "]" + empMailBoxData[i][j]);
				}

			}

		}

		return empMailBoxData;
	}

	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData) {
		System.out
				.println("in send mail functionality {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{");
		fromMailId = fromMailIds;
System.out.println("fromMailId ========== "+ fromMailId);
		// email. setDebug(true);
		try {
			// HtmlEmail email=null;
			int patch = Integer.parseInt(String.valueOf(mailBoxData[0][16]));
			if (patch == 0) {
				patch = 80;
			}

			if (patch > 0) {
				System.out.println("patch" + patch);
				int count = toMailId.length / patch;
				int rem = toMailId.length % patch;
				if (rem > 0) {
					count = count + 1;
				}
				int k = 0;
				if (patch > toMailId.length) {
					patch = toMailId.length;
				}
				/*
				 * AutoBirthday.out.println(); AutoBirthday.out .write(" COUNT
				 * *** NO OF TIMES MAIN FOR LOPP RUNNUNG :" + count);
				 */
				for (int i = 0; i < count; i++) {
					String[] tomailIds = null;
					if (i == count - 1) {
						if (rem > 0) {
							tomailIds = new String[rem];
						} else {
							tomailIds = new String[patch];
						}
					} else {
						tomailIds = new String[patch];
					}
					System.out.println("\n");
					System.out.println("Batch Number : " + (i + 1));
					System.out
							.println("Email Batch Size : " + tomailIds.length);
					for (int j = 0; j < tomailIds.length; j++) {
						tomailIds[j] = toMailId[k];
						k++;
						System.out.println("K  ::::::::+++++:::::::: "+ k);
						System.out.println("tomailIds[j] ---------++++++------- "+tomailIds[j]);
						
					}
					// HtmlEmail email=setHtmlEmail(mailBoxData, subject,
					// textBody,
					// request, tomailIds);
					fireCounter = 0;

					fireEmail(mailBoxData, subject, textBody, tomailIds);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	

	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		
		
		System.out
				.println("in fire email call --------------++++++++++++-----------------------");
		System.out
				.println("subject --------------++++++++++++-----------------------"
						+ subject);
		System.out
				.println("textBody --------------++++++++++++-----------------------"
						+ textBody);

		try {
			System.out.println("mailCount -------- "+ mailCount);
			for (int i = 0; i < tomailIds.length; i++) {
				System.out.println("tomailIds in fireEmail are ---->>>>> "+tomailIds[i] );
			}
			System.out.println("mailCount -------- "+ mailCount);
			

			
				
			
			
			if (mailCount < fromMailId.length) {
				/*
				 * if(mailCount==0){ email=setHtmlEmail(mailBoxData, subject,
				 * textBody, request, tomailIds); }
				 */
				email = setHtmlEmail(mailBoxData, subject, textBody, tomailIds);
				email.setFrom(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);
				email.addTo(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);

				/*
				 * AutoBirthday.out.write("USER NAME " +
				 * fromMailId[mailCount][0] + " PASS " +
				 * fromMailId[mailCount][1]);
				 */
				if (String.valueOf(mailBoxData[0][5]).equals("Y")) {
					if (String.valueOf(mailBoxData[0][12]).equals("D")) {
						email.setAuthentication(String
								.valueOf(mailBoxData[mailCount][3]), String
								.valueOf(mailBoxData[0][4]));

					} else {
						email.setAuthentication(String
								.valueOf(fromMailId[mailCount][0]), String
								.valueOf(fromMailId[mailCount][1]));

					}
				}
				if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
					try {
						email.setPopBeforeSmtp(true, String
								.valueOf(mailBoxData[0][7]), String
								.valueOf(mailBoxData[0][3]), String
								.valueOf(mailBoxData[0][4]));

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				if (String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")) {
					email.setSmtpWithTLS(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}
				if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
					email.setSmtpWithSSL(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}

				String str = email.send();
				// mailCount ++;
				/*
				 * AutoBirthday.out.println(); AutoBirthday.out.write("send
				 * Error :" + str);
				 */
				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
					fireCounter++;
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						// AutoBirthday.out.println();
						// AutoBirthday.out.write(" unsentList...Valid
						// "+unsentList.get(i));
						String[] splitedStr = unsentList.get(i).toString()
								.split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1]
								.length() - 1);

					}

					System.out.println("\n Unsent Valid Email address size :"
							+ unsentList.size());

					List invalidList = email.getInvalidList();
					System.out.println("\n invalid address :");
					for (int i = 0; i < invalidList.size(); i++) {
						System.out.println("\n" + invalidList.get(i));
					}
					System.out.println("Value of fireCounter is::::::::       "
							+ fireCounter);

					if (fireCounter < 5) {
						System.out.println("\n. Resending Emails - counter :"
								+ fireCounter);
						fireEmail(mailBoxData, subject, textBody, toObj);
					}
					// addTo_CC(email, toObj);
					// str= email.send();
				}
				// AutoBirthday.out.println();AutoBirthday.out.write("MAIL SENT
				// SUCCESSFULLY *********** ... FOR SYSMAIL ID "+mailCount);

			} else {
				System.out.println("SYSTEM MAIL IDS OVER....");
			}

			/*
			 * System.out .println("mail send
			 * successfully________________________________ 1
			 * mailCount"+mailCount);
			 */
		} catch (Exception e) {
			/*
			 * email=setHtmlEmail(mailBoxData, subject, textBody, request,
			 * tomailIds); mailCount++;
			 */
			mailCount++;
			System.out.println("EXCEPTION________________________________ ");
			e.printStackTrace();
			System.out.println("" + e);
			System.out.println();
			// fireEmail(mailBoxData, subject, textBody, request, tomailIds);
		}

	}

	public HtmlEmail setHtmlEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		HtmlEmail email = new HtmlEmail();
		String replacedString = "";

		try {
			
			for (int i = 0; i < tomailIds.length; i++) {
				System.out.println("tomailIds in setHtmlEmail are ---->>>>> "+tomailIds[i] );
			}
			
			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][1])));// 80
			email.setSubject("" + subject);
			replacedString = getHtmlText(email, textBody);
			// replacedString=getHtmlTextWithBackground(email, replacedString,
			// request);
			email.setHtmlMsg(replacedString);
			email
					.setTextMsg("Your email client does not support HTML messages");
			addTo_CC(email, tomailIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}

	public String getHtmlText(HtmlEmail email, String textBody) {
		
		System.out.println("textBody =======>>>>>>>>>"+ textBody);
		textBody = textBody.replace("src=\"", "$");
		StringTokenizer st = new StringTokenizer(textBody, "$");
		System.out.println("st ----------------->>>>>>>> "+ st);
		int counter = 0;
		String replacedString = "";
		String cid = "";
		while (st.hasMoreTokens()) {
			String new_tokens = st.nextToken();
			// AutoBirthday.out.println();AutoBirthday.out.write("new_tokens :"
			// + new_tokens);
			// AutoBirthday.out.println();AutoBirthday.out.write("\n-----------------------------------------------");
			if (counter != 0) {
				int endofURL = new_tokens.indexOf("\"");
				// AutoBirthday.out.println();AutoBirthday.out.write("...endofURL.."
				// + endofURL);
				String src = new_tokens.substring(0, endofURL);
				// AutoBirthday.out.println();AutoBirthday.out.write("src====="+src);
				// if (!src.startsWith("http")) {
				// src =
				// "D:\\Struts2\\hrsaas-21\\12\\2009\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\"+src;
				src = imagePath + "\\webapps" + src;
				// }
				// AutoBirthday.out.println();AutoBirthday.out.write("src====="+src);
				try {
					cid = email.embed(new File(src), "Image001" + counter);
					// URL url = new URL(src);
					// cid = email.embed(url, "Image001"+counter);
				} catch (Exception e) {
					e.printStackTrace();
				}
				replacedString += "src=\"cid:"
						+ cid
						+ "\""
						+ new_tokens.substring(endofURL + 1, new_tokens
								.length());
			} else {
				replacedString += new_tokens;
			}
			counter++;
		}

		// AutoBirthday.out.println();AutoBirthday.out.write("messages :" +
		// replacedString);
		System.out.println("replacedString  :  " + replacedString);
		return replacedString;

	}

	public HtmlEmail addTo_CC(HtmlEmail email, String[] toObj) {

		try {
			
			System.out.println("email ----------------(())(()))) "+ email);
			for (int i = 0; i < toObj.length; i++) {
				System.out.println("toObj[i] ----------------(())(()))) "+ toObj[i]);
			}
			System.out.println("toObj.length here is ^^^^^^^^^^^^^^^ = "+ toObj.length);
			
			// AutoBirthday.out.println();
			// AutoBirthday.out.write("************************************************************************************");
			for (int i = 0; i < toObj.length; i++) {
				// AutoBirthday.out.println();
				// AutoBirthday.out.write(" "+toObj[i]+" ");
				// AutoBirthday.out.println();
				email.addBcc(toObj[i]);
			}
			// AutoBirthday.out.println();
			// AutoBirthday.out.write("************************************************************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return email;
	}
}
