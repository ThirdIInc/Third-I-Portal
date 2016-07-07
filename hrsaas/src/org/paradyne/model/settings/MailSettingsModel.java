package org.paradyne.model.settings;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.Recruitment.InterviewSchedule;
import org.paradyne.bean.settings.MailSettings;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author_Pankaj_Jain_&_Prakash_Shetkar
 * @modified by VISHWAMBHAR DESHPANDE
 * @modified by ANANTHA LAKSHMI
 */
public class MailSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MailSettingsModel.class);

	String exceptionMessage = "";

	/**
	 * Removing null values
	 * @param result
	 * @return String replaces null with ""
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}else {
			return result;
		}
	}

	/**
	 * Save Inbound Mail Settings
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean addMailBoxConf(MailSettings bean, String poolDir) {
		try {
			boolean flag = false;
			String Query = "SELECT MAILBOX_PORT from HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'I' ";
			Object[][] getResult = getSqlModel().getSingleResult(Query);

			Object[][] addMailbox = new Object[1][8];
			addMailbox[0][0] = bean.getMailServer();
			addMailbox[0][1] = bean.getMailProtocol();
			addMailbox[0][2] = bean.getMailPort();
			addMailbox[0][3] = bean.getMailUsername();
			addMailbox[0][4] = bean.getMailPasswd();
			addMailbox[0][5] = "";
			addMailbox[0][6] = "";
			addMailbox[0][7] = "I";
			logger.info(" User Name " + addMailbox[0][3]);
			logger.info(" Password " + addMailbox[0][4]);
			if (getResult.length == 0) {
				logger.info("Inside If " + getResult.length);
				flag = getSqlModel().singleExecute(getQuery(1), addMailbox);
			}// END if
			else {
				logger.info("Inside Else " + getResult.length);
				flag = getSqlModel().singleExecute(getQuery(2), addMailbox);
			}

			xml_mail(poolDir);

			return flag;

			// END else

		} catch (Exception e) {
			logger.error("Exception caught in Adding Inbound Mail Settings :  "
					+ e);
			return false;
		}

	}

	/**
	 * Inbound Mail Settings Configuration Set fields after save method
	 * 
	 * @param bean
	 * @return boolean
	 */

	public boolean saveMailBoxConf(MailSettings bean) {
		try {
			String mailquery = "SELECT MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,MAILBOX_PASSW FROM HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'I'";
			Object[][] addMail = getSqlModel().getSingleResult(mailquery);
			bean.setMailServer(checkNull(String.valueOf(addMail[0][0])));
			bean.setMailProtocol(checkNull(String.valueOf(addMail[0][1])));
			bean.setMailPort(checkNull(String.valueOf(addMail[0][2])));
			bean.setMailUsername(checkNull(String.valueOf(addMail[0][3])));
			if (String.valueOf(addMail[0][3]).equals("null"))
				bean.setMailUsername("");
			bean.setMailPasswd(checkNull(String.valueOf(addMail[0][4])));
			return true;
		} catch (Exception e) {
			logger.error("Exception caught in Saving Inbound Mail Settings :  "
					+ e);
			return false;
		}

	}

	/**
	 * Outbound Mail Settings Configuration For Setting the fields after save
	 * Method
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean saveMailBoxConfOut(MailSettings bean) {
		try {
			String mailquery = "SELECT MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,nvl(MAILBOX_USERID,' '),nvl(MAILBOX_PASSW,' '),MAILBOX_AUTH_CHK,MAILBOX_POP_BEFORE_SMTP FROM HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'O' ";
			Object[][] addMail = getSqlModel().getSingleResult(mailquery);
			bean.setMailServerOut(checkNull(String.valueOf(addMail[0][0])));
			bean.setMailProtocolOut(checkNull(String.valueOf(addMail[0][1])));
			bean.setMailPortOut(checkNull(String.valueOf(addMail[0][2])));
			//	bean.setMailUsernameOut(checkNull(String.valueOf(addMail[0][3])));
			//	bean.setMailPasswdOut(checkNull(String.valueOf(addMail[0][4])));
			bean.setAuthChk("true");
			if (String.valueOf(addMail[0][5]).equals("N"))
				bean.setAuthChk("false");
			logger.info("setPbSmtp( --------- "
					+ checkNull(String.valueOf(addMail[0][6])));
			bean.setPbSmtp(checkNull(String.valueOf(addMail[0][6])));
			return true;
		} catch (Exception e) {
			logger
					.error("Exception caught in Saving Outbound Mail Settings :  "
							+ e);
			return false;
		}

	}

	/**
	 * Setting System Mail fields
	 * 
	 * @param bean
	 */
	public void showSysMail(MailSettings bean) {
		try {
			logger.info("Inside Sys Mail");
			String query = "";
			if (bean.getHiddenCode().equals("")) {
				String query1 = " SELECT NVL(MAX(SERVER_CODE),0)+1 FROM HRMS_EMAIL_SERVER ";
				Object data[][] = getSqlModel().getSingleResult(query1);

				query = " SELECT SYSMAIL_CODE,SYSMAIL_EMAIL_ID FROM HRMS_SETTINGS_SYSMAILID "
						+ " WHERE SYSMAIL_SERVER_CODE="
						+ String.valueOf(data[0][0]);
			} else {
				query = " SELECT SYSMAIL_CODE,SYSMAIL_EMAIL_ID FROM HRMS_SETTINGS_SYSMAILID "
						+ " WHERE SYSMAIL_SERVER_CODE=" + bean.getHiddenCode();

			}

			ArrayList<Object> list = new ArrayList<Object>();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			if (resultData != null & resultData.length > 0) {
				for (int i = 0; i < resultData.length; i++) {// loop x
					MailSettings mailBean = new MailSettings();
					mailBean.setSysMailCode(String.valueOf(resultData[i][0]));
					mailBean.setSysEmailId(String.valueOf(resultData[i][1]));
					list.add(mailBean);
				}// END loop x
			}// END if
			bean.setSysMailList(list);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception caught in Setting System Mail  :  " + e);
		}
	}

	/**
	 * Saving Outbound mail Settings
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean addMailBoxConfOut(MailSettings bean, String poolDir) {
		try {
			boolean flag = false;
			String Query = "SELECT MAILBOX_PORT from HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'O'";
			Object[][] getResult = getSqlModel().getSingleResult(Query);
			Object[][] addMailbox = new Object[1][8];
			addMailbox[0][0] = bean.getMailServerOut();
			addMailbox[0][1] = bean.getMailProtocolOut();
			addMailbox[0][2] = bean.getMailPortOut();
			//	addMailbox[0][3] = bean.getMailUsernameOut();
			//	addMailbox[0][4] = bean.getMailPasswdOut();
			addMailbox[0][5] = "Y";
			addMailbox[0][6] = bean.getPbSmtp();
			addMailbox[0][7] = "O";
			logger.info("bean.getPbSmtp()" + bean.getPbSmtp());
			if (bean.getAuthChk().equals("false")) {
				addMailbox[0][5] = "N";
				addMailbox[0][3] = "";
				addMailbox[0][4] = "";
			}// END if
			logger.info("Auth Chk " + addMailbox[0][6]);
			logger.info(" User Name " + addMailbox[0][3]);
			logger.info(" Password " + addMailbox[0][4]);
			if (getResult.length == 0)
				flag = getSqlModel().singleExecute(getQuery(1), addMailbox);
			else {
				flag = getSqlModel().singleExecute(getQuery(2), addMailbox);
			}
			xml_mail(poolDir);
			return flag;
		} catch (Exception e) {
			logger
					.error("Exception caught in Adding Outbound Mail Settings :  "
							+ e);
			return false;
		}

	}

	public void xml_mail(String poolDir) {

		String mailInQuery = " SELECT MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,MAILBOX_PASSW from HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'I' ";
		Object[][] mailInResult = getSqlModel().getSingleResult(mailInQuery);
		String mailOutQuery = " SELECT MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_POP_BEFORE_SMTP from HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'O' ";
		Object[][] mailOutResult = getSqlModel().getSingleResult(mailOutQuery);
		String sysQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM  HRMS_SETTINGS_SYSMAILID ";
		Object[][] sysResult = getSqlModel().getSingleResult(sysQuery);
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;

		logger.info("file Name------------- " + poolDir);

		try {
			// XML write
			new XMLReaderWriter().write(buildDocumentMail("MAILBOX",
					mailInResult, mailOutResult, sysResult), poolDir);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception caught in writing: " + e);
		}

	}

	public Document buildDocumentMail(String head, Object[][] mailInbound,
			Object[][] mailOutbound, Object[][] sysMailId) {
		System.out.println("Debjani____________");
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(head);

		Element header;
		Element data;

		header = root.addElement("INBOUND").addAttribute("name",
				"INBOUND SETTING");
		try {
			data = header.addElement("DATA").addAttribute("server",
					mailInbound[0][0].toString()).addAttribute("protocol",
					mailInbound[0][1].toString()).addAttribute("port",
					mailInbound[0][2].toString()).addAttribute("username",
					mailInbound[0][3].toString()).addAttribute("password",
					mailInbound[0][4].toString());
		} catch (Exception e) {

			// TODO: handle exception
		}
		header = root.addElement("OUTBOUND").addAttribute("name",
				"OUTBOUND SETTING");
		try {
			data = header.addElement("DATA").addAttribute("server",
					mailOutbound[0][0].toString()).addAttribute("protocol",
					mailOutbound[0][1].toString()).addAttribute("port",
					mailOutbound[0][2].toString()).addAttribute("username",
					mailOutbound[0][3].toString()).addAttribute("password",
					mailOutbound[0][4].toString()).addAttribute("authRequired",
					mailOutbound[0][5].toString()).addAttribute("pbsmtp",
					mailOutbound[0][6].toString());
		} catch (Exception e) {

			// TODO: handle exception
		}
		header = root.addElement("SYSMAILIDS").addAttribute("name",
				"SYSTEM MAILS");
		for (int i = 0; i < sysMailId.length; i++) {
			try {
				data = header.addElement("DATA").addAttribute("mailId",
						"" + sysMailId[i][0].toString()).addAttribute(
						"Password", "" + sysMailId[i][1].toString());
			} catch (Exception e) {

				// TODO: handle exception
			}
		}
		return document;
	}

	/**
	 * Save System Mail id and password
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean saveSysMail(MailSettings bean) {

		try {
			boolean flag = false;

			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = bean.getSysEmail();
			saveObj[0][1] = bean.getSysEmailPsswd();
			if (bean.getHiddenCode().equals("")) {
				String query = " SELECT NVL(MAX(SERVER_CODE),0)+1 FROM HRMS_EMAIL_SERVER ";
				Object data[][] = getSqlModel().getSingleResult(query);
				saveObj[0][2] = String.valueOf(data[0][0]);
			} else {
				saveObj[0][2] = bean.getHiddenCode();
			}
			flag = getSqlModel().singleExecute(getQuery(3), saveObj);
			//xml_mail(poolDir);
			return flag;
		} catch (Exception e) {
			logger
					.error("Exception caught in Saving System Mail id and password :  "
							+ e);
			return false;
		}
	}

	/**
	 * Updating System Mail Settings
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean updateSysMail(MailSettings bean) {
		try {

			Object[][] updateObj = new Object[1][4];
			updateObj[0][0] = bean.getSysEmail();
			updateObj[0][1] = bean.getSysEmailPsswd();
			updateObj[0][2] = bean.getHiddenMailCode();
			if (bean.getHiddenCode().equals("")) {
				String query = " SELECT NVL(MAX(SERVER_CODE),0)+1 FROM HRMS_EMAIL_SERVER ";
				Object data[][] = getSqlModel().getSingleResult(query);
				updateObj[0][3] = String.valueOf(data[0][0]);
			} else {
				updateObj[0][3] = bean.getHiddenCode();
			}
			return getSqlModel().singleExecute(getQuery(4), updateObj);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Editing System Mail Settings
	 * 
	 * @param bean
	 */
	public void editSysMail(MailSettings bean) {
		try {
			String query = " SELECT SYSMAIL_EMAIL_ID FROM HRMS_SETTINGS_SYSMAILID "
					+ " WHERE SYSMAIL_CODE = " + bean.getHiddenMailCode();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setSysEmail(String.valueOf(resultData[0][0]));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception caught in Editing Sys Mail Settings :  "
					+ e);
		}
	}

	/**
	 * Deleting data under System Mails
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean deleteSysMail(MailSettings bean) {
		try {
			String query = "DELETE FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_CODE = "
					+ bean.getHiddenMailCode();
			return getSqlModel().singleExecute(query);
		} catch (Exception e) {
			logger.error("Exception caught in Deleting Sys Mail Settings :  "
					+ e);
			return false;
		}
	}
	public String testMailConnection(MailSettings bean)
	{
		String exceptionMessage = "Message:\n\n";
		try {
			Object[][] mailBoxData = null;
			MailModel model = new MailModel();
			model.initiate(context, session);
			if(bean.getPbSmtp().equals("true"))
			{
				mailBoxData = model.getServerMailBox(bean.getUserEmpId(), bean.getFromMailId()
						.trim());
			}
			else
			{
				mailBoxData = model.getServerMailBox("", bean.getFromMailId()
						.trim());
			}
			model.terminate();
			if (bean.getHiddenCode().equals("")
					|| bean.getHiddenCode().equals("null")) {
				exceptionMessage="Please save record first then test connection.";
				return exceptionMessage;
			}
			
			if (!bean.getHiddenCode().equals("")
					|| bean.getHiddenCode().equals("null")) {
				String userNamePassQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID "
						+ " WHERE SYSMAIL_CODE = "
						+ bean.getHiddenMailCode()
						+ " AND SYSMAIL_SERVER_CODE=" + bean.getHiddenCode();
				Object[][] userNamePassQueryObj = getSqlModel()
						.getSingleResult(userNamePassQuery);
				System.out.println("userNamePassQueryObj.length"
						+ userNamePassQueryObj.length);
				if (userNamePassQueryObj.length == 0) {
					return "Please save record first then test connection.";
				}
			}
			exceptionMessage += testConnection(mailBoxData, bean);
		} catch (Exception e) {
			 
		}
		return exceptionMessage;
	}
	 
	 
	private String testConnection(Object[][] mailBoxData, MailSettings bean)
	{
		String exceptionMessage = "";
		try {
			MailUtility mod = new MailUtility();
			mod.initiate(context, session);
			String[] newToMailIds = new String[1];
			String[] newFromMailId = new String[1];
			newToMailIds[0] = bean.getToMailId().trim();
			newFromMailId[0] = bean.getFromMailId().trim();
			String message = mod.sendMail(newToMailIds, newFromMailId,
					"This is test mail", "Testing Mail Connection", "", "",false);
			logger.info("value of message- in- testConnection:::::::::"
					+ message);
			if (message.equals("")) {
				exceptionMessage = "Mail delivered successfully";
			} else {
				exceptionMessage = message;
			}
			mod.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return exceptionMessage;
	}
	 

	/**
	 * Method for save button
	 * @param bean
	 * @param sysMailPassword 
	 * @param srNo2 
	 * @param srNo 
	 */
	public boolean saveMailSetting(MailSettings bean, String[] srNo,
			String[] sysemailId, String[] sysMailPassword,String divCode) {

		boolean result = false;
		try {
			String insertMailSettingQuery = " INSERT INTO HRMS_EMAIL_SERVER(SERVER_CODE, DOMAIN_NAME, SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,"
					+ "  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE, SERVER_AUTH_REQUIRED, SERVER_POP_BEFORE_SMTP, SERVER_SYSTEMMAIL_FLAG, "
					+" SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG, NUMBER_OF_MAILS_SEND,SERVER_DIVISION) "
					+ "	  VALUES((SELECT NVL(MAX(SERVER_CODE),0)+1 FROM HRMS_EMAIL_SERVER),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String authorizationChk = "N";
			String popBeforeSmtpChk = "N";
			String sendSysMailChk = "N";
			String logonusingcheck = "S";
			
			String useSystemMailIdForAll = "N";

			Object[][] saveMailSettingObj = new Object[1][16];
			saveMailSettingObj[0][0] = bean.getMailServerName();//mail server name
			saveMailSettingObj[0][1] = bean.getMailServer();//mailbox server inbound
			saveMailSettingObj[0][2] = bean.getMailPort();//mail port inbound
			saveMailSettingObj[0][3] = bean.getMailProtocol();//mail protocol inbound
			saveMailSettingObj[0][4] = bean.getMailServerOut();//mailbox server outbound
			saveMailSettingObj[0][5] = bean.getMailPortOut();//mail port outbound
			saveMailSettingObj[0][6] = bean.getMailProtocolOut();//mail protocol outbound
			if (bean.getAuthChk().equals("true")) {
				authorizationChk = "Y";
			}// END if
			saveMailSettingObj[0][7] = authorizationChk;//authorization check

			if (bean.getChkPBSmtp().equals("true")) {
				popBeforeSmtpChk = "Y";
			}// END if
			saveMailSettingObj[0][8] = popBeforeSmtpChk;//pop before smtp check

			if (bean.getSendSysMailChk().equals("true")) {
				sendSysMailChk = "Y";
			}// END if

			if (bean.getHiddenRadio().equals("")) {
				logonusingcheck = "S";
			}// END if

			if (bean.getHiddenRadio().equals("D")) {
				logonusingcheck = "D";
			}// END if
			
			String systemFlagQuery = " SELECT SERVER_SYSTEMMAIL_FLAG FROM  HRMS_EMAIL_SERVER ORDER BY SERVER_CODE";

			Object[][] data = getSqlModel().getSingleResult(systemFlagQuery);
			if (bean.getSendSysMailChk().equals("true")) {
				if (data != null && data.length > 0) {
					for (int i = 0; i < data.length; i++) {

						if (String.valueOf(data[i][0]).equals("Y")) {
							String updateQuery = " UPDATE HRMS_EMAIL_SERVER SET SERVER_SYSTEMMAIL_FLAG='N' ";
							getSqlModel().singleExecute(updateQuery);
						}
					}
				}
			}
			saveMailSettingObj[0][9] = sendSysMailChk;//Send System Mail 
			saveMailSettingObj[0][10] = logonusingcheck;
			saveMailSettingObj[0][11] = bean.getLogonUserName().trim();
			saveMailSettingObj[0][12] = bean.getLogonPassword().trim();
			
			if (bean.getUseSystemMailIdForAll().equals("true")) {
				useSystemMailIdForAll = "Y";
			}
			saveMailSettingObj[0][13] = useSystemMailIdForAll;
			saveMailSettingObj[0][14] = bean.getNumberOfMailsSend().trim();
			saveMailSettingObj[0][15]=divCode;
			
			result = getSqlModel().singleExecute(insertMailSettingQuery,saveMailSettingObj);

			String query = " SELECT NVL(MAX(SERVER_CODE),0) FROM HRMS_EMAIL_SERVER ";
			Object serverCodeObj[][] = getSqlModel().getSingleResult(query);
			Object[][] param = null;
			if (srNo != null && srNo.length > 0) {
				param = new Object[srNo.length][4];
				for (int i = 0; i < srNo.length; i++) {
					param[i][0] = srNo[i];
					param[i][1] = sysemailId[i];
					param[i][2] = sysMailPassword[i];
					param[i][3] = String.valueOf(serverCodeObj[0][0]);

				}
				result = getSqlModel().singleExecute(getQuery(3), param);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getSavedServerNames(MailSettings bean) {
		// TODO Auto-generated method stub
		try {

			String query = "  SELECT SERVER_CODE,DOMAIN_NAME,SERVER_IN_IP,SERVER_OUT_IP FROM HRMS_EMAIL_SERVER "
					+ " ORDER BY SERVER_CODE ";
			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < data.length; i++) {
					MailSettings bean1 = new MailSettings();
					bean1.setIttServerCode(checkNull(String.valueOf(data[i][0])));
					bean1.setIttServerName(checkNull(String.valueOf(data[i][1])));
					bean1.setInboundServer(checkNull(String.valueOf(data[i][2])));
					bean1.setOutboundServer(checkNull(String.valueOf(data[i][3])));
					//	bean1.setIttUserName(checkNull(String.valueOf(data[i][2])));
					list.add(bean1);
				}
				bean.setList(list);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void callEdit(MailSettings bean) {
		// TODO Auto-generated method stub
		try {

			String query = " SELECT SERVER_CODE, DOMAIN_NAME, SERVER_IN_IP, "
					+ "  SERVER_IN_PORT, SERVER_IN_TYPE, SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE, SERVER_AUTH_REQUIRED, "
					+ "  SERVER_POP_BEFORE_SMTP, SERVER_SYSTEMMAIL_FLAG "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG, NUMBER_OF_MAILS_SEND,SERVER_DIVISION "
					+ " FROM HRMS_EMAIL_SERVER " + " WHERE  SERVER_CODE="
					+ bean.getHiddenCode();

			Object data[][] = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				bean.setServerCode(checkNull(String.valueOf(data[0][0])));
				bean.setMailServerName(checkNull(String.valueOf(data[0][1])));
				bean.setMailServer(checkNull(String.valueOf(data[0][2])));
				bean.setMailPort(checkNull(String.valueOf(data[0][3])));
				bean.setMailProtocol(checkNull(String.valueOf(data[0][4])));
				bean.setMailServerOut(checkNull(String.valueOf(data[0][5])));
				bean.setMailPortOut(checkNull(String.valueOf(data[0][6])));
				bean.setMailProtocolOut(checkNull(String.valueOf(data[0][7])));
				if (String.valueOf(data[0][8]).equals("N")) {
					bean.setAuthChk("false");
				} else {
					bean.setAuthChk("true");
				}
				if (String.valueOf(data[0][9]).equals("N")) {
					bean.setChkPBSmtp("false");
				} else {
					bean.setChkPBSmtp("true");
				}
				if (String.valueOf(data[0][10]).equals("N")) {
					bean.setSendSysMailChk("false");
				} else {
					bean.setSendSysMailChk("true");
				}
				if (String.valueOf(data[0][11]).equals("S")) {
					bean.setHiddenRadio("S");
				} else {
					bean.setHiddenRadio("D");
				}
				bean.setLogonUserName(checkNull(String.valueOf(data[0][12])));
				bean.setLogonPassword(checkNull(String.valueOf(data[0][13])));	
				System.out.println("--------DATA------"+data[0][14]);
				if (String.valueOf(data[0][14]).equals("N")) {
					bean.setUseSystemMailIdForAll("false");
				} else {
					bean.setUseSystemMailIdForAll("true");
				}
				
				bean.setNumberOfMailsSend(checkNull(String.valueOf(data[0][15])));
				String strDivCode=String.valueOf(data[0][16]);
				System.out.println("strDivCode--------"+strDivCode);
				String appDivName="";
				String appDivCode="";
					String queryDivName="SELECT DISTINCT  NVL(DIV_NAME,' '),DIV_ID FROM HRMS_DIVISION WHERE  DIV_ID IN ("+strDivCode+")";
					Object divData[][] = getSqlModel().getSingleResult(queryDivName);
					for(int i=0;i<divData.length;i++){
						if(i==0){
							appDivName=String.valueOf(divData[i][0]);
							appDivCode=String.valueOf(divData[i][1]);
						}else{
							appDivName=appDivName+",\n"+String.valueOf( divData[i][0]);
							appDivCode=appDivCode+","+String.valueOf( divData[i][1]);
							System.out.println("appDivName----"+appDivName);
						}
					}
						
				bean.setDivisionCode(checkNull(appDivCode));
				bean.setDivision(checkNull(appDivName));
				String sysMailIdData = " SELECT SYSMAIL_CODE,SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID "
						+ " WHERE SYSMAIL_SERVER_CODE="
						+ bean.getHiddenCode()
						+ " ORDER BY SYSMAIL_CODE ";

				Object resultData[][] = getSqlModel().getSingleResult(sysMailIdData);
				ArrayList<Object> list = new ArrayList<Object>();
				if (resultData != null & resultData.length > 0) {
					for (int i = 0; i < resultData.length; i++) {// loop x
						MailSettings mailBean = new MailSettings();
						mailBean.setSysMailCode(checkNull(String
								.valueOf(resultData[i][0])));
						mailBean.setSysEmailId(checkNull(String
								.valueOf(resultData[i][1])));
						mailBean.setSysMailPassword(checkNull(String
								.valueOf(resultData[i][2])));
						mailBean.setShowTestConnectionFlag("true");
						list.add(mailBean);
					}// END loop x
				}// END if
				bean.setSysMailList(list);
			}
		} catch (Exception e) {
			logger.info("Exception in callEdit-------------" + e);
		}
	}

	public boolean callDelete(MailSettings bean) {
		// TODO Auto-generated method stub

		boolean res = false;
		try {

			String query = "DELETE FROM HRMS_EMAIL_SERVER  WHERE SERVER_CODE IN("
					+ bean.getHiddenCode() + ")";
			res = getSqlModel().singleExecute(query);

			String delSysmailIdQuery = " DELETE FROM HRMS_SETTINGS_SYSMAILID  WHERE SYSMAIL_SERVER_CODE IN("
					+ bean.getHiddenCode() + ")";

			res = getSqlModel().singleExecute(delSysmailIdQuery);

			bean.setHiddenCode("");
		} catch (Exception e) {
			logger.info("Exception in callDelete-------------" + e);
		}
		return res;
	}

	public boolean updateMailSetting(MailSettings bean, String[] srNo,
			String[] sysemailId, String[] sysMailPassword,String divCode) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			String delQuery = " DELETE FROM HRMS_EMAIL_SERVER WHERE  SERVER_CODE="
					+ bean.getHiddenCode();

			result = getSqlModel().singleExecute(delQuery);

			if (result) {
				String insertMailSettingQuery = " INSERT INTO HRMS_EMAIL_SERVER(SERVER_CODE, DOMAIN_NAME, SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,"
						+ "  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE, SERVER_AUTH_REQUIRED, SERVER_POP_BEFORE_SMTP, SERVER_SYSTEMMAIL_FLAG,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD,SERVER_USESYSTEMMAILID_FLAG,NUMBER_OF_MAILS_SEND,SERVER_DIVISION) "
						+ "	  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String authorizationChk = "N";
				String popBeforeSmtpChk = "N";
				String sendSysMailChk = "N";
				String logonusingcheck = "S";
				
				String useSystemMailIdForAll = "N";

				Object[][] saveMailSettingObj = new Object[1][17];
				saveMailSettingObj[0][0] = bean.getHiddenCode();//mail server code
				saveMailSettingObj[0][1] = bean.getMailServerName();//mail server name
				saveMailSettingObj[0][2] = bean.getMailServer();//mailbox server inbound
				saveMailSettingObj[0][3] = bean.getMailPort();//mail port inbound
				saveMailSettingObj[0][4] = bean.getMailProtocol();//mail protocol inbound
				saveMailSettingObj[0][5] = bean.getMailServerOut();//mailbox server outbound
				saveMailSettingObj[0][6] = bean.getMailPortOut();//mail port outbound
				saveMailSettingObj[0][7] = bean.getMailProtocolOut();//mail protocol outbound
				if (bean.getAuthChk().equals("true")) {
					authorizationChk = "Y";
				}// END if
				saveMailSettingObj[0][8] = authorizationChk;//authorization check

				if (bean.getChkPBSmtp().equals("true")) {
					popBeforeSmtpChk = "Y";
				}// END if
				saveMailSettingObj[0][9] = popBeforeSmtpChk;//pop before smtp check

				if (bean.getSendSysMailChk().equals("true")) {
					sendSysMailChk = "Y";
				}// END if

				if (bean.getHiddenRadio().equals("")) {
					logonusingcheck = "S";
				}// END if

				if (bean.getHiddenRadio().equals("D")) {
					logonusingcheck = "D";
				}// END if

				String systemFlagQuery = " SELECT SERVER_SYSTEMMAIL_FLAG FROM  HRMS_EMAIL_SERVER ORDER BY SERVER_CODE";

				Object[][] data = getSqlModel()
						.getSingleResult(systemFlagQuery);
				if (bean.getSendSysMailChk().equals("true")) {
					if (data != null && data.length > 0) {
						for (int i = 0; i < data.length; i++) {

							if (String.valueOf(data[i][0]).equals("Y")) {
								String updateQuery = " UPDATE HRMS_EMAIL_SERVER SET SERVER_SYSTEMMAIL_FLAG='N' ";
								getSqlModel().singleExecute(updateQuery);
							}
						}
					}
				}

				saveMailSettingObj[0][10] = sendSysMailChk;//Send System Mail 
				//saveMailSettingObj[0][11] = bean.getMailUsername();//mailbox user name
				//saveMailSettingObj[0][12] = bean.getMailPasswd();//mailbox password

				saveMailSettingObj[0][11] = logonusingcheck;

				saveMailSettingObj[0][12] = bean.getLogonUserName().trim();

				saveMailSettingObj[0][13] = bean.getLogonPassword().trim();
				
				if (bean.getUseSystemMailIdForAll().equals("true")) {
					useSystemMailIdForAll = "Y";
				}// END if
				
				saveMailSettingObj[0][14] = useSystemMailIdForAll;
				
				saveMailSettingObj[0][15] = bean.getNumberOfMailsSend().trim();
				
				
					saveMailSettingObj[0][16]=divCode;
				
				
				result = getSqlModel().singleExecute(insertMailSettingQuery,
						saveMailSettingObj);

				String deleteQuery = " DELETE FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE="
						+ bean.getHiddenCode();

				getSqlModel().singleExecute(deleteQuery);

			 
				Object[][] param = null;
				if (srNo != null && srNo.length > 0) {
					param = new Object[srNo.length][4];
					for (int i = 0; i < srNo.length; i++) {
						param[i][0] = srNo[i];
						param[i][1] = sysemailId[i];
						param[i][2] = sysMailPassword[i];
						param[i][3] = bean.getHiddenCode();

					}
					result = getSqlModel().singleExecute(getQuery(3), param);
				}

				/*String query = " SELECT NVL(MAX(SERVER_CODE),0) FROM HRMS_EMAIL_SERVER ";
				Object serverCodeObj[][] = getSqlModel().getSingleResult(query);
				Object[][] param = null;
				if (srNo !=null  && srNo.length > 0) {
					param = new Object[srNo.length][4];
					for (int i= 0; i < srNo.length; i++) {
						param[i][0]=sysemailId[i];
						param[i][1]=sysMailPassword[i];
					    param[i][2] = srNo[i];
					    param[i][3] = String.valueOf(serverCodeObj[0][0]);
					}
					
				}
				result = getSqlModel().singleExecute(getQuery(3), param);*/

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public boolean addSysmail(String[] srNo, String[] sysemailId,
			String[] sysMailPassword, int check, MailSettings bean) {
		// TODO Auto-generated method stub

		boolean result = false;
		ArrayList<Object> list = new ArrayList<Object>();

		if (srNo != null && srNo.length > 0) {
			for (int i = 0; i < srNo.length; i++) {
				MailSettings mailSettingBean = new MailSettings();
				mailSettingBean.setSysMailCode(String.valueOf(i + 1));
				mailSettingBean.setSysEmailId(sysemailId[i]);
				mailSettingBean.setSysMailPassword(sysMailPassword[i]);
				list.add(mailSettingBean);
				result = true;
			}
		}
		if (check == 1) {
			MailSettings mailSettingBean = new MailSettings();
			mailSettingBean.setSysMailCode(String.valueOf(list.size() + 1));
			mailSettingBean.setSysEmailId(bean.getSysEmail());
			mailSettingBean.setSysMailPassword(bean.getSysEmailPsswd());
			list.add(mailSettingBean);
			result = true;
		}

		bean.setSysMailList(list);
		return result;
	}

	public void editSystemMailId(MailSettings bean, String[] srNo,
			String[] sysemailId, String[] sysMailPassword) {
		// TODO Auto-generated method stub
		ArrayList<Object> list = new ArrayList<Object>();

		if (srNo != null && srNo.length > 0) {
			for (int i = 0; i < srNo.length; i++) {
				MailSettings mailSettingBean = new MailSettings();

				System.out.println("bean.getHiddenMailCode()==============="
						+ bean.getHiddenMailCode());

				if (i == Integer.parseInt(bean.getHiddenMailCode()) - 1) {
					mailSettingBean.setSysMailCode(String.valueOf(i + 1));
					mailSettingBean.setSysEmailId(bean.getSysEmail());
					mailSettingBean.setSysMailPassword(bean.getSysEmailPsswd());
				} else {
					mailSettingBean.setSysMailCode(String.valueOf(i + 1));
					mailSettingBean.setSysEmailId(sysemailId[i]);
					mailSettingBean.setSysMailPassword(sysMailPassword[i]);
				}
				list.add(mailSettingBean);
			}
		}
		bean.setSysMailList(list);
	}

	public boolean updateSystemMail(String[] srNo, String[] sysemailId,
			String[] sysMailPassword, int check, MailSettings bean) {
		// TODO Auto-generated method stub
		ArrayList<Object> list = new ArrayList<Object>();

		if (srNo != null && srNo.length > 0) {
			for (int i = 0; i < srNo.length; i++) {
				MailSettings mailSettingBean = new MailSettings();

				System.out.println("bean.getHiddenMailCode()==============="
						+ bean.getHiddenMailCode());

				if (i == Integer.parseInt(bean.getHiddenMailCode()) - 1) {
					mailSettingBean.setSysMailCode(String.valueOf(i + 1));
					mailSettingBean.setSysEmailId(bean.getSysEmail());
					mailSettingBean.setSysMailPassword(bean.getSysEmailPsswd());
				} else {
					mailSettingBean.setSysMailCode(String.valueOf(i + 1));
					mailSettingBean.setSysEmailId(sysemailId[i]);
					mailSettingBean.setSysMailPassword(sysMailPassword[i]);
				}
				list.add(mailSettingBean);
			}
		}
		bean.setSysMailList(list);

		return true;
	}

	public boolean deleteSystemMail(MailSettings bean, String[] srNo,
			String[] sysemailId, String[] sysMailPassword) {
		// TODO Auto-generated method stub
		ArrayList<Object> list = new ArrayList<Object>();

		if (srNo != null && srNo.length > 0) {
			for (int i = 0; i < srNo.length; i++) {
				MailSettings mailSettingBean = new MailSettings();
				mailSettingBean.setSysMailCode(String.valueOf(i + 1));
				mailSettingBean.setSysEmailId(sysemailId[i]);
				mailSettingBean.setSysMailPassword(sysMailPassword[i]);
				list.add(mailSettingBean);
			}
			logger.info("bean.getHiddenMailCode()" + bean.getHiddenMailCode());
			list.remove(Integer.parseInt(bean.getHiddenMailCode()) - 1);
		}
		bean.setSysMailList(list);
		return true;
	}

	public void displayIttData(MailSettings bean, String[] srNo,
			String[] sysemailId, String[] sysMailPassword) {
		ArrayList<Object> list = new ArrayList<Object>();

		if (srNo != null && srNo.length > 0) {
			for (int i = 0; i < srNo.length; i++) {
				MailSettings mailSettingBean = new MailSettings();
				mailSettingBean.setSysMailCode(String.valueOf(i + 1));
				mailSettingBean.setSysEmailId(sysemailId[i]);
				mailSettingBean.setSysMailPassword(sysMailPassword[i]);
				list.add(mailSettingBean);

			}
			bean.setSysMailList(list);
		}

	}

 
	public String checkDuplictaeMailIdWithIterator(String[] srNo,
			String[] sysemailId, MailSettings bean) {
		String result = "";
		try {
			if (srNo != null && srNo.length > 0) {
				for (int i = 0; i < srNo.length; i++) {
					if (i == Integer.parseInt(bean.getHiddenMailCode()) - 1) 
					{
						
					}
					else
					{
						if (bean.getSysEmail().equals(String.valueOf(sysemailId[i]))) {
							result = "alreadyPresent";
						}
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in checkDuplictaeMailIdWithIterator-------------"+e);
		}

		return result;
	}
	
public void intChecklist(MailSettings bean, HttpServletRequest request) {
		
	String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
	query+= "ORDER BY Upper(NVL(DIV_NAME,' '))";
		Object obj[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> arr = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {
			MailSettings bean1 = new MailSettings();
			bean1.setDivisionCode(String.valueOf(obj[i][0]));
			bean1.setDivision(String.valueOf(obj[i][1]));
			arr.add(bean1);

		}
		bean.setDivisionList(arr);
		String str = request.getParameter("id");
		String[] divCode=str.split(",");
		String strDivCode="";
		for(int i=0;i<divCode.length;i++){
			if(i==0)
				strDivCode=divCode[i];
			else
				strDivCode=strDivCode+" "+divCode[i];
			
		}
		bean.setDivisionCode(strDivCode);
		
	}
 
}
