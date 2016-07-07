/**
 * 
 */
package org.paradyne.model.settings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.settings.SettingMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author_Pankaj_Jain_&_Prakash_Shetkar
 * @purpose : This form is used to Save or Edit HomePage Dashlet Information
 * 			  HomePage Setting Link 
 * @Modified By AA1711 - TO view that Records According to Applicable 
 * 						Division Wise on Dashlet 
 * @Date 22-Jan-2013
 */

public class SettingMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SettingMasterModel.class);

	private String alertMessage = "";

	String exceptionMessage = "";

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	/**
	 * Method Name:addPoll()
	 * 
	 * @purpose : Adding code and question with first option
	 * @param bean
	 * @return boolean
	 */
	public boolean addPoll(SettingMaster bean) {
		try {
			Object[][] addPoll = new Object[1][2];
			addPoll[0][0] = bean.getQuestion();
			addPoll[0][1] = bean.getExpiry();
			/** Insert Data Into HRMS_POLL_HDR Table */
			boolean res = getSqlModel().singleExecute(getQuery(1), addPoll);
			String query = " SELECT MAX(POLL_CODE) FROM HRMS_POLL_HDR ";
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setPollCode(String.valueOf(data[0][0]));
			addOption(bean);
			return res;
		} catch (Exception e) {
			logger.error("Exception caught in Add Poll" + e);
			return false;
		}
	}

	/**
	 * Method Name: addOption()
	 * 
	 * @purpose : Adding options for poll
	 * @param bean
	 * @return query Boolean
	 */
	public boolean addOption(SettingMaster bean) {
		try {
			String query1 = " SELECT NVL(MAX(POLL_OPTION_CODE),0)+1 FROM HRMS_POLL_DTL WHERE POLL_CODE="
					+ bean.getPollCode();
			Object[][] data1 = getSqlModel().getSingleResult(query1);
			Object[][] addOption = new Object[1][4];
			addOption[0][0] = bean.getPollCode();// Optinion Poll Code
			addOption[0][1] = data1[0][0];// Option Code
			addOption[0][2] = bean.getOption();// Option Description
			addOption[0][3] = bean.getOptionColor();// Option Color
			/** Insert Option Detail in HRMS_POLL_DTL Table */
			return getSqlModel().singleExecute(getQuery(2), addOption);
		} catch (Exception e) {
			logger.error("Exception caught in Add Option" + e);
			return false;
		}
	}

	/**
	 * Method Name: updateOption()
	 * 
	 * @Purpose: Update options for poll
	 * @param bean
	 * @return boolean - query
	 */
	public boolean updateOption(SettingMaster bean) {
		try {
			Object updateObj[][] = new Object[1][4];
			updateObj[0][0] = bean.getOption();// Option Description
			updateObj[0][1] = bean.getOptionColor();// Option Color
			updateObj[0][2] = bean.getPollCode();// Opinion Ques Code
			updateObj[0][3] = bean.getHiddenOptionCode();// Option Code
			/** Update Option in HRMS_POLL_DTL Table */
			String query1 = "UPDATE HRMS_POLL_DTL SET POLL_OPTION_DESC = ?, POLL_OPTION_COLOR = ? "
					+ " WHERE POLL_CODE = ? AND POLL_OPTION_CODE = ? ";

			return getSqlModel().singleExecute(query1, updateObj);
		} catch (Exception e) {
			logger.error("Exception caught in Update Option" + e);
			return false;
		}
	}

	/**
	 * Method Name: deletePoll()
	 * 
	 * @purpose : To delete a poll and its options
	 * @param bean
	 * @param poolDir
	 */
	public void deletePoll(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_poll();
			String delQuery;
			delQuery = "DELETE FROM HRMS_POLL_DTL WHERE POLL_CODE = "
					+ bean.getPollCode();
			getSqlModel().singleExecute(delQuery);
			delQuery = "DELETE FROM HRMS_POLL_HDR WHERE POLL_CODE = "
					+ bean.getPollCode();
			getSqlModel().singleExecute(delQuery);
			String delEmp = "DELETE FROM HRMS_POLL_EMP WHERE POLL_CODE = "
					+ bean.getPollCode();
			getSqlModel().singleExecute(delEmp);
			// Writing into XML
			xml_poll(poolDir);
			bean.setHiddenCode_poll("");
			// return result;
		} catch (Exception e) {
			logger.error("Exception caught in Delete Poll" + e);
		}
	}

	/**
	 * Method Name: showRecord()
	 * 
	 * @Purpose : Set List After Search Or Save
	 * @param bean
	 */
	public void showRecord(SettingMaster bean) {
		try {
			String query = "SELECT  POLL_OPTION_CODE , POLL_OPTION_DESC,POLL_OPTION_COLOR FROM HRMS_POLL_DTL WHERE POLL_CODE = "
					+ bean.getPollCode() + " ORDER BY POLL_OPTION_CODE";
			Object data[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = 0; i < data.length; i++) {
				SettingMaster bean1 = new SettingMaster();
				bean1.setOptionCode(String.valueOf(data[i][0]));// Option Code
				bean1.setOptionList(String.valueOf(data[i][1]));// Option
																// Description
				bean1.setColor(String.valueOf(data[i][2]));// Option Color
				list.add(bean1);
			}// end of loop
			if (bean.getExpiry().trim().equals(""))
				bean.setExpiry("");
			bean.setList(list);
		} catch (Exception e) {
			logger.error("Exception caught in Show Record :" + e);
		}
	}

	/**
	 * Method Name: getRecord()
	 * 
	 * @purpose : Setting option And Its Color for Edit
	 * @param bean
	 */
	public void getRecord(SettingMaster bean) {
		try {
			String query = "SELECT POLL_OPTION_DESC,POLL_OPTION_COLOR FROM HRMS_POLL_DTL WHERE POLL_CODE= "
					+ bean.getPollCode()
					+ " AND POLL_OPTION_CODE = "
					+ bean.getHiddenOptionCode();
			Object data[][] = getSqlModel().getSingleResult(query);
			bean.setOption(String.valueOf(data[0][0]));
			bean.setOptionColor(String.valueOf(data[0][1]));

		} catch (Exception e) {
			logger.error("Exception caught in Get Record : " + e);
		}
	}

	/**
	 * Method Name: delRecord()
	 * 
	 * @purpose: Deleting Poll options. Options as well as no. of votes on each
	 *           option
	 * @param bean
	 * @param poolDir
	 * @return boolean - Query
	 */
	public boolean delRecord(SettingMaster bean, String poolDir) {
		boolean result = false;
		Object[][] delCode = new Object[1][2];
		delCode[0][1] = bean.getHiddenOptionCode();// Option Code
		delCode[0][0] = bean.getPollCode();// Poll_code
		try {
			String delQuery = "DELETE FROM HRMS_POLL_DTL WHERE POLL_CODE = ?  AND POLL_OPTION_CODE=?";
			String delEmpPoll = "DELETE FROM HRMS_POLL_EMP WHERE POLL_CODE = ?  AND POLL_OPTION_CODE=?";
			boolean result1 = getSqlModel().singleExecute(delQuery, delCode);
			boolean result2 = getSqlModel().singleExecute(delEmpPoll, delCode);
			if (result1 && result2) {
				result = true;
				// Writing into XML
				xml_poll(poolDir);
			}// end of if
			String xmlWriteFlagQuery = "SELECT MAX(POLL_CODE) FROM HRMS_POLL_HDR";
			Object[][] flag = getSqlModel().getSingleResult(xmlWriteFlagQuery);
			try {
				if (String.valueOf(flag[0][0]).equals(bean.getPollCode())) {
					xml_poll(poolDir);
					return result;
				}// end of if
			} catch (Exception e) {
				logger.error("Exception caught in DELETE - ii : " + e);
				return result;
			}
			return result;
		} catch (Exception e) {
			logger.error("Exception caught in DELETE - i : " + e);
			return result;
		}
	}

	/**
	 * Method Name: saveRecord() saving POll XML is written on SAVE Method
	 * 
	 * @param bean
	 * @param path
	 * @return Boolean - Query
	 */
	public boolean saveRecord(SettingMaster bean, String path) {
		try {
			Object[][] updateObj = new Object[1][4];
			updateObj[0][0] = bean.getQuestion();// Question Description
			updateObj[0][1] = bean.getExpiry();// Expiry Date
			updateObj[0][2] = checkNull(bean.getPollDivCode());// Division Code
			if (bean.getExpiry().trim().equals(""))
				updateObj[0][1] = "";
			updateObj[0][3] = bean.getPollCode();
			String query = " UPDATE HRMS_POLL_HDR SET POLL_QUESTION = ?, "
					+ " EXPIRY_DATE = TO_DATE(?,'DD-MM-YYYY'),POLL_DIVISION=? "
					+ " WHERE  POLL_CODE = ? ";
			getSqlModel().singleExecute(query, updateObj);
			xml_poll(path);
			return true;
		} catch (Exception e) {
			logger.error("Exception caught in SAVE : " + e);
			return false;
		}

	}

	/**
	 * Writing XML
	 * 
	 * @param path
	 */
	public void xml_poll(String path) {
		if (!(path == null || path.equals("") || path.equals(null)))
			path = "\\" + path;
		try {
			// WRITE
			new XMLReaderWriter().write(buildPoll("HRHOME", "OPINION POLL"),
					path);
		} catch (Exception e) {
			logger.error("Exception caught in XML writing : " + e);
		}
	}

	/**
	 * Building XML for POLL
	 * 
	 * @param head
	 * @param subhead
	 * @return XML Document
	 */
	public Document buildPoll(String head, String subhead) {
		String query1 = "SELECT POLL_CODE,POLL_QUESTION FROM HRMS_POLL_HDR WHERE POLL_CODE	=(SELECT MAX(POLL_CODE) "
				+ " FROM HRMS_POLL_HDR) " + " ORDER BY POLL_DATE	 DESC";
		Object data[][] = getSqlModel().getSingleResult(query1);
		// create document
		Document document = DocumentHelper.createDocument();
		Element header;
		Element element;
		Element root = document.addElement(head);
		if (data != null && data.length > 0) {
			// Writing CODE & QUESTION into XML
			header = root.addElement("POLL").addAttribute("name", subhead);
			element = header.addElement("QUESTION").addAttribute("id",
					String.valueOf(data[0][0])).addAttribute("name",
					String.valueOf(data[0][1]));

			// No. of votes on options
			String query2 = " SELECT POLL_OPTION_CODE , COUNT(*) FROM HRMS_POLL_EMP "
					+ " WHERE  POLL_CODE = "
					+ String.valueOf(data[0][0])
					+ " GROUP BY POLL_OPTION_CODE  "
					+ " UNION  "
					+ " SELECT POLL_OPTION_CODE ,0  FROM HRMS_POLL_DTL WHERE POLL_OPTION_CODE NOT IN (SELECT DISTINCT POLL_OPTION_CODE FROM HRMS_POLL_EMP WHERE POLL_CODE = "
					+ String.valueOf(data[0][0])
					+ ") "
					+ " AND POLL_CODE = "
					+ String.valueOf(data[0][0]);

			Object data1[][] = getSqlModel().getSingleResult(query2);
			for (int i = 0; i < data1.length; i++) {
				// Option code,description & color
				String query3 = " SELECT POLL_OPTION_CODE , POLL_OPTION_DESC,POLL_OPTION_COLOR FROM HRMS_POLL_DTL  "
						+ " WHERE POLL_CODE="
						+ String.valueOf(data[0][0])
						+ "  AND POLL_OPTION_CODE="
						+ String.valueOf(data1[i][0]);
				try {
					// Writing options value & color
					Object optionObj[][] = getSqlModel()
							.getSingleResult(query3);
					optionObj[0][1] = String.valueOf(optionObj[0][1]).replace(
							"'", "%26apos;").replace("&", "%26");
					element.addElement("OPTION").addAttribute("id",
							String.valueOf(data1[i][0])).addAttribute("name",
							String.valueOf(optionObj[0][1])).addAttribute(
							"value", String.valueOf(data1[i][1])).addAttribute(
							"color", String.valueOf(optionObj[0][2]));
				} catch (Exception e) {
					logger.error("Exception caught _BUILD POLL_ : " + e);
				}
			}
		}// end of if
		else {
			header = root.addElement("POLL").addAttribute("name", subhead);
			element = header.addElement("QUESTION").addAttribute("id",
					String.valueOf("id")).addAttribute("name",
					String.valueOf("nme"));

			element.addElement("OPTION").addAttribute("id",
					String.valueOf("dd")).addAttribute("name",
					String.valueOf("dd")).addAttribute("value",
					String.valueOf("clr")).addAttribute("color",
					String.valueOf("fsdfs"));
		}// end of else
		return document;
	}

	/**
	 * Method Name: saveHrComm()
	 * 
	 * @purpose : Saving HR Communication
	 * @param hrCommBean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int saveHrComm(SettingMaster hrCommBean, String status,
			String poolDir) { // Saving HR Communication{
		try {
			Object[][] addLink = new Object[1][4];
			int flagValue = 0;
			if (status.equals("save")) {
				addLink[0][0] = hrCommBean.getLinkNameHr();
				if (hrCommBean.getUploadHr().equals("")) {
					addLink[0][1] = hrCommBean.getLinkHr();
					addLink[0][3] = "L";
				}// END nested if
				else {
					addLink[0][1] = "../pages/upload/"
							+ hrCommBean.getUploadHr();
					addLink[0][3] = "D";
				}// END else
				if (hrCommBean.getCheckHr().equals("true"))
					addLink[0][2] = "Y";
				else
					addLink[0][2] = "N";
				if (hrCommBean.getHiddenCode_Hr().equals("")) {
					getSqlModel().singleExecute(getQuery(3), addLink);
					flagValue = 1;
				} // END nested if
				else {// y
					Object[][] hiddenCode = new Object[1][5];
					hiddenCode[0][0] = hrCommBean.getLinkNameHr();
					if (String.valueOf(addLink[0][3]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ hrCommBean.getUploadHr();
					else
						hiddenCode[0][1] = hrCommBean.getLinkHr();
					hiddenCode[0][2] = String.valueOf(addLink[0][3]);
					hiddenCode[0][3] = String.valueOf(addLink[0][2]);
					hiddenCode[0][4] = hrCommBean.getHiddenCode_Hr();
					getSqlModel().singleExecute(getQuery(5), hiddenCode);
					flagValue = 2;
				}// END else y
				Object[][] xmlHrData = getSqlModel().getSingleResult(
						getQuery(35));
				if (!(poolDir == null || poolDir.equals("") || poolDir
						.equals(null)))
					poolDir = "\\" + poolDir;
				String fileName = context.getRealPath("\\")
						+ "WEB-INF\\classes\\org\\paradyne\\dataFiles\\hrWork_Comm\\hrWorkComm.xml";			
				try {
					// write XML
					new XMLReaderWriter().write(buildDocument("HRHOME",
							"HRWORQ COMMUNICATION", xmlHrData), fileName);
				} catch (Exception e) {
					logger.error("Exception---1: " + e);
				}
			}// END if "save"

			Object[][] getLinkData = getSqlModel().getSingleResult(getQuery(4));
			ArrayList<Object> list_hr = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_Hr = new SettingMaster();
				beanLink_Hr.setLinkCode_Hr(String.valueOf(getLinkData[i][0]));
				beanLink_Hr.setLinkName_Hr(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				beanLink_Hr.setLinkFile_hr(complete);
				list_hr.add(beanLink_Hr);
			}// END of loop x
			hrCommBean.setList_hrLink(list_hr);

			return flagValue;
		} catch (Exception e) {
			logger.error("Exception in Save HrWork Comm : " + e);
			return 0;
		}
	}

	/**
	 * Method Name: buildDocument() XML CREATION for HrWorK Communication
	 * 
	 * @param head
	 * @param subHead
	 * @param hrHome
	 * @return document creates XML
	 */
	public Document buildDocument(String head, String subHead, Object[][] hrHome) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(head);

		Element header;
		Element data;

		header = root.addElement("COMMUNICATION").addAttribute("name", subHead);
		for (int i = 0; i < hrHome.length; i++)
			data = header.addElement("Link").addAttribute("id",
					"" + hrHome[i][0].toString()).addAttribute("name",
					"" + hrHome[i][1].toString()).addAttribute("value",
					"" + hrHome[i][2].toString());
		return document;
	}

	/**
	 * Method Name: buildDocumentMail()
	 * @param head
	 * @param mailInbound
	 * @param mailOutbound
	 * @param sysMailId
	 * @return Document
	 */
	public Document buildDocumentMail(String head, Object[][] mailInbound,
			Object[][] mailOutbound, Object[][] sysMailId) {
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		header = root.addElement("SYSMAILIDS").addAttribute("name",
				"SYSTEM MAILS");
		for (int i = 0; i < sysMailId.length; i++) {
			try {
				data = header.addElement("DATA").addAttribute("mailId",
						"" + sysMailId[i][0].toString()).addAttribute(
						"Password", "" + sysMailId[i][1].toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return document;
	}

	/**
	 * Method Name: saveBbs() Saving Bulletin Board
	 * @param BbsBean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int saveBbs(SettingMaster BbsBean, String status, String poolDir) {
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][4];
				addLink[0][0] = BbsBean.getLinkNameBbs();
				if (BbsBean.getUploadBbs().equals("")) {
					addLink[0][1] = BbsBean.getLinkBbs();
					addLink[0][3] = "L";
				}// END nested if
				else {
					addLink[0][1] = "../pages/upload/" + BbsBean.getUploadBbs();
					addLink[0][3] = "D";
				}// END else
				if (BbsBean.getCheckBulletin().equals("true"))
					addLink[0][2] = "Y";
				else
					addLink[0][2] = "N";
				if (BbsBean.getHiddenCode_Bbs().equals("")) {
					getSqlModel().singleExecute(getQuery(26), addLink);
					flagValue = 1;
				} else {// y
					Object[][] hiddenCode = new Object[1][5];
					hiddenCode[0][0] = BbsBean.getLinkNameBbs();
					if (String.valueOf(addLink[0][3]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ BbsBean.getUploadBbs();
					else
						hiddenCode[0][1] = BbsBean.getLinkBbs();
					hiddenCode[0][2] = String.valueOf(addLink[0][3]);
					hiddenCode[0][3] = String.valueOf(addLink[0][2]);
					hiddenCode[0][4] = BbsBean.getHiddenCode_Bbs();
					getSqlModel().singleExecute(getQuery(28), hiddenCode);
					flagValue = 2;
				}// END else y
				Object[][] getbulletinXmlData = getSqlModel().getSingleResult(
						getQuery(38));
				if (!(poolDir == null || poolDir.equals("") || poolDir
						.equals(null)))
					poolDir = "\\" + poolDir;
				// String fileName
				// =context.getRealPath("\\")+"\\WEB-INF\\classes\\org\\paradyne\\dataFiles\\"+poolDir+"\\bulletin\\bulletin.xml";
				try {
					// Write XML
					new XMLReaderWriter().write(buildDocument("HRHOME",
							"BULLETIN BOARD", getbulletinXmlData), poolDir);
				} catch (Exception e) {
					logger.error("Exception Caught 1-----" + e);
				}
			}// END if "save"
			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(27));

			ArrayList<Object> list_Bbs = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_Bbs = new SettingMaster();
				beanLink_Bbs.setLinkCode_Bbs(String.valueOf(getLinkData[i][0]));
				beanLink_Bbs.setLinkName_Bbs(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				beanLink_Bbs.setLinkFile_Bbs(complete);
				list_Bbs.add(beanLink_Bbs);
			}// END of loop x
			BbsBean.setList_BbsLink(list_Bbs);

			return flagValue;
		} catch (Exception e) {
			logger.error("Exception caught in Saving Bulletin Board : " + e);
			return 0;
		}

	}

	/**Method Name: saveCorporate()
	 * @purpose : Saving Corporate Info Settings 
	 * @param corporateBean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int saveCorporate(SettingMaster corporateBean, String status,
			String poolDir) {
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][5];
				addLink[0][0] = String.valueOf(corporateBean.getLinkNameCs());
				if (corporateBean.getUploadCs().equals("")) {
					addLink[0][1] = corporateBean.getLinkCs();//Link Name
					addLink[0][3] = "L";
				} // END nested if
				else {
					addLink[0][1] = "../pages/upload/"
							+ corporateBean.getUploadCs();
					addLink[0][3] = "D";
				}// END else
				if (corporateBean.getCheckCorp().equals("true"))
					addLink[0][2] = "Y";
				else
					addLink[0][2] = "N";

				addLink[0][4] = checkNull(corporateBean.getApplDivisionCode())
						.trim();//Applicable Division Code

				if (corporateBean.getHiddenCode_Cs().equals("")) {
					getSqlModel().singleExecute(getQuery(7), addLink);
					flagValue = 1;
				} //End if
				else {
					Object[][] hiddenCode = new Object[1][6];
					hiddenCode[0][0] = corporateBean.getLinkNameCs();
					if (String.valueOf(addLink[0][3]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ corporateBean.getUploadCs();
					else
						hiddenCode[0][1] = corporateBean.getLinkCs();
					hiddenCode[0][2] = String.valueOf(addLink[0][3]);
					hiddenCode[0][3] = String.valueOf(addLink[0][2]);
					hiddenCode[0][4] = checkNull(
							corporateBean.getApplDivisionCode()).trim();//Applicable Division Code
					hiddenCode[0][5] = corporateBean.getHiddenCode_Cs();
					getSqlModel().singleExecute(getQuery(9), hiddenCode);
					flagValue = 2;
				}// END else y
				xml_Cs(poolDir);
			}// END if "save"

			Object[][] getLinkData = getSqlModel().getSingleResult(getQuery(8));
			ArrayList<Object> list_cs = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_Cs = new SettingMaster();
				beanLink_Cs.setLinkCode_Cs(String.valueOf(getLinkData[i][0]));
				beanLink_Cs.setLinkName_Cs(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				if (String.valueOf(getLinkData[i][4]).equals("L")) {					
					beanLink_Cs.setLinkFile_Cs(link);
				}// END if
				else
					beanLink_Cs.setLinkFile_Cs(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				beanLink_Cs.setLinkActive_Cs(String.valueOf(getLinkData[i][3]));
				// beanLink_Cs.setLinkDivision_Cs(String.valueOf(getLinkData[i][4]));
				list_cs.add(beanLink_Cs);
			}// END of loop x
			corporateBean.setList_csLink(list_cs);
			return flagValue;
		} catch (Exception e) {
			logger.error("Exception Caught in Save Corporate : " + e);
			return 0;
		}
	}

	/**Method Name: xml_Cs()
	 * @purpose : Writing XML 
	 * @param poolDir
	 */
	public void xml_Cs(String poolDir) {
		Object[][] xmlCrInData = getSqlModel().getSingleResult(getQuery(36));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;
		try {
			// XML write
			new XMLReaderWriter().write(buildDocument("HRHOME",
					"CORPORATE INFO", xmlCrInData), poolDir);
		} catch (Exception e) {
			logger.error("Exception caught in writing: " + e);
		}
	}

	/**
	 *Method : xml_Ts(). 
	 *@Purpose : Writing XML
	 * @param poolDir
	 * @return nothing.
	 */
	public void xml_Ts(String poolDir) {
		Object[][] xmlTipsInfoData = getSqlModel()
				.getSingleResult(getQuery(58));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;
		try {
			// XML write
			new XMLReaderWriter().write(buildDocument("HRHOME", "TIPS LINK",
					xmlTipsInfoData), poolDir);
		} catch (Exception e) {
			logger.error("Exception caught in writing: " + e);
		}
	}

	/**Method Name: saveLink()
	 * @purpose : Saving Quick Link Settings
	 * @param quickBean
	 * @param poolDir
	 * @param chkStatus
	 * @param linkCode
	 * @return integer value
	 */
	public int saveLink(SettingMaster quickBean, String poolDir,
			String[] chkStatus, String[] linkCode){
		try {
			for (int i = 0; i < chkStatus.length; i++) {// loop x
				Object updateObj[][] = new Object[1][2];
				updateObj[0][0] = chkStatus[i];
				updateObj[0][1] = linkCode[i];			
				getSqlModel().singleExecute(getQuery(33), updateObj);
			}// END of loop x
			Object[][] xmlQuickData = getSqlModel().getSingleResult(
					getQuery(48));
			if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
				poolDir = "\\" + poolDir;
			// String fileName
			// =context.getRealPath("\\")+"\\WEB-INF\\classes\\org\\paradyne\\dataFiles\\"+poolDir+"\\general\\general.xml";
			try {
				new XMLReaderWriter().write(buildDocument("HRHOME", "QUICK",
						xmlQuickData), poolDir);
			} catch (Exception e) {
				logger.error("Exception Caught 1 : " + e);
			}
			showQlinkInfo(quickBean);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**Method Name: saveTips
	 * @param tipsBean :Used to get field data.
	 * @param status :Checked for save.
	 * @param poolDir :Path.
	 * @return int.
	 */
	public int saveTips(SettingMaster tipsBean, String status, String poolDir){
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][5];
				addLink[0][0] = String.valueOf(tipsBean.getTipsName());
				if (tipsBean.getUploadTs().equals("")) {
					addLink[0][1] = tipsBean.getLinkTs();
					addLink[0][3] = "L";
				} // END nested if
				else {
					addLink[0][1] = "../pages/upload/" + tipsBean.getUploadTs();
					addLink[0][3] = "D";
				}// END else
				if (tipsBean.getActiveTip().equals("true"))
					addLink[0][2] = "Y";
				else
					addLink[0][2] = "N";

				addLink[0][4] = tipsBean.getDivisionCode().trim();

				if (tipsBean.getHiddenCode_TS().equals("")) {
					getSqlModel().singleExecute(getQuery(54), addLink);
					flagValue = 1;
				} else {// y
					Object[][] hiddenCode = new Object[1][6];
					hiddenCode[0][0] = tipsBean.getTipsName();
					if (String.valueOf(addLink[0][3]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ tipsBean.getUploadTs();
					else
						hiddenCode[0][1] = tipsBean.getLinkTs();
					hiddenCode[0][2] = String.valueOf(addLink[0][3]);
					hiddenCode[0][3] = String.valueOf(addLink[0][2]);

					hiddenCode[0][4] = tipsBean.getDivisionCode().trim();

					hiddenCode[0][5] = tipsBean.getHiddenCode_TS();
					getSqlModel().singleExecute(getQuery(55), hiddenCode);
					flagValue = 2;
				}// END else y
				xml_Ts(poolDir);
			}// END if "save"
			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(56));			
			ArrayList<Object> list_ts = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {
				SettingMaster beanLink_Ts = new SettingMaster();
				beanLink_Ts.setLinkCode_TL(String.valueOf(getLinkData[i][0]));
				beanLink_Ts.setLinkName_TL(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				if (String.valueOf(getLinkData[i][4]).equals("L")) {
					beanLink_Ts.setLinkFile_TL(link);
				}// END if
				else
					beanLink_Ts.setLinkFile_TL(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				beanLink_Ts.setLinkActive_TL(String.valueOf(getLinkData[i][3]));

				list_ts.add(beanLink_Ts);
			}// END of loop x
			tipsBean.setList_TipsLink(list_ts);
			return flagValue;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**Method Name: saveEmail()
	 * @purpose:  Saving Email Settings
	 * @param emailBean
	 * @param poolDir
	 * @param chkStatus
	 * @param linkCode
	 * @return integer value
	 */
	public int saveEmail(SettingMaster emailBean, String poolDir,
			String[] chkStatus, String[] linkCode) {
		try {
			for (int i = 0; i < chkStatus.length; i++) {// loop x
				Object updateObj[][] = new Object[1][2];
				updateObj[0][0] = chkStatus[i];
				updateObj[0][1] = linkCode[i];
				getSqlModel().singleExecute(getQuery(50), updateObj);
			}// END of loop x

			showEmailInfo(emailBean);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**Method Name: saveKnowledge()
	 * @purpose Saving Knowledge Settings
	 * @param knowledgeBean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int saveKnowledge(SettingMaster knowledgeBean, String status,
			String poolDir) {
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][6];
				addLink[0][0] = String.valueOf(knowledgeBean.getLinkNameKs());
				/**To insert Record */
				if (knowledgeBean.getUploadKs().equals("")) {
					addLink[0][1] = knowledgeBean.getLinkKs();//Link Name
					addLink[0][3] = "L";
				} // END nested if
				else {
					addLink[0][1] = "../pages/upload/"
							+ knowledgeBean.getUploadKs();
					addLink[0][3] = "D";
				}// END else
				if (knowledgeBean.getCheckKnow().equals("true")) {
					addLink[0][2] = "Y";
				}// END nested if
				else
					addLink[0][2] = "N";
				addLink[0][4] = checkNull(knowledgeBean.getKnowDivCode());//Division Code

				if (knowledgeBean.getKnowCatName() != ""
						&& knowledgeBean.getKnowCatNmSelect().trim().equals(
								"Other")) {
					addLink[0][5] = knowledgeBean.getKnowCatName();
				}//end if 
				else {
					addLink[0][5] = knowledgeBean.getKnowCatNmSelect().trim();
				}//end else
				if (knowledgeBean.getHiddenCode_Ks().equals("")) {
					getSqlModel().singleExecute(getQuery(11), addLink);
					flagValue = 1;
				} // END nested if
				/**To Update Knowledge Record*/
				else {
					Object[][] hiddenCode = new Object[1][7];
					hiddenCode[0][0] = knowledgeBean.getLinkNameKs();
					if (String.valueOf(addLink[0][3]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ knowledgeBean.getUploadKs();
					else
						hiddenCode[0][1] = knowledgeBean.getLinkKs();
					hiddenCode[0][2] = String.valueOf(addLink[0][3]);
					hiddenCode[0][3] = String.valueOf(addLink[0][2]);

					hiddenCode[0][4] = checkNull(knowledgeBean.getKnowDivCode());//Division Code

					if (knowledgeBean.getKnowCatName() != ""
							&& knowledgeBean.getKnowCatNmSelect().trim()
									.equals("Other")) {
						hiddenCode[0][5] = knowledgeBean.getKnowCatName();
					} else {
						hiddenCode[0][5] = knowledgeBean.getKnowCatNmSelect()
								.trim();
					}
					hiddenCode[0][6] = knowledgeBean.getHiddenCode_Ks();
					getSqlModel().singleExecute(getQuery(13), hiddenCode);
					flagValue = 2;
				}// END else y
				xml_Ks(poolDir);
			}// END if "save"

			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(12));
			ArrayList<Object> list_ks = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_Ks = new SettingMaster();
				beanLink_Ks.setLinkCode_Ks(String.valueOf(getLinkData[i][0]));
				beanLink_Ks.setLinkName_Ks(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				if (String.valueOf(getLinkData[i][4]).equals("L")) {
					beanLink_Ks.setLinkFile_Ks(link);
				}// END nested if
				else
					beanLink_Ks.setLinkFile_Ks(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				beanLink_Ks.setLinkActive_Ks(String.valueOf(getLinkData[i][3]));
				if (!(String.valueOf(getLinkData[i][5]).equals("null") || String
						.valueOf(getLinkData[i][5]) == null)) {
					beanLink_Ks
							.setCatName_Ks(String.valueOf(getLinkData[i][5]));
				} else
					beanLink_Ks.setCatName_Ks("Other");
				list_ks.add(beanLink_Ks);
			}// END of loop x
			knowledgeBean.setList_ksLink(list_ks);
			return flagValue;
		} catch (Exception e) {
			logger.error("Exception Caught in Saving Knowledge : " + e);
			return 0;
		}

	}

	/**Method Name: xml_Ks()
	 * @purpose : Writing XML knowledge Info
	 * @param poolDir
	 */
	public void xml_Ks(String poolDir) {
		Object[][] xmlKnowData = getSqlModel().getSingleResult(getQuery(37));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;

		try {
			// Write XML
			new XMLReaderWriter().write(buildDocument("HRHOME", "KNOWLEDGE",
					xmlKnowData), poolDir);
		} catch (Exception e) {
			logger.error("Exception Caught writing XML : " + e);
		}
	}

	/**Method Name :saveGeneral()
	 * @purpose : Saving General Info
	 * @param bean
	 * @param poolDir
	 * @param chkStatus
	 * @param linkCode
	 * @return integer value
	 */
	public int saveGeneral(SettingMaster bean, String poolDir,
			String[] chkStatus, String[] linkCode) {
		try {		
			for (int i = 0; i < chkStatus.length; i++) {// loop x
				Object updateObj[][] = new Object[1][2];
				updateObj[0][0] = chkStatus[i];
				updateObj[0][1] = linkCode[i];				
				getSqlModel().singleExecute(getQuery(42), updateObj);
			}// END of loop x
			Object[][] xmlGeneralData = getSqlModel().getSingleResult(
					getQuery(45));
			if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
				poolDir = "\\" + poolDir;
			try {				
				// write XML
				new XMLReaderWriter().write(buildDocument("HRHOME", "GENERAL",
						xmlGeneralData), poolDir);
			} catch (Exception e) {
				logger.error("Exception Caught 1 : " + e);
			}
			/**To show General information*/
			showGeneralInfo(bean);
			return 1;

		} catch (Exception e) {
			logger.error("Exception Caught in Saving General Info : " + e);
			return 0;
		}
	}

	/**Method Name: showGeneralInfo()
	 * @purpose Saving General Information Settings
	 * @param bean
	 */
	public void showGeneralInfo(SettingMaster bean) {
		try {
			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(41));
			ArrayList<Object> list_gs = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_Gs = new SettingMaster();
				beanLink_Gs.setLinkCode_Gs(String.valueOf(getLinkData[i][0]));
				beanLink_Gs.setLinkName_Gs(String.valueOf(getLinkData[i][1]));

				if (String.valueOf(getLinkData[i][2]).equals("Y")) {					
					beanLink_Gs.setCheck_G("true");
				} // END nested if
				else
					beanLink_Gs.setCheck_G("false");
				list_gs.add(beanLink_Gs);
			}// END of loop x
			bean.setList_gsLink(list_gs);
		} catch (Exception e) {
			logger.error("Exception Caught in Saving General Info : " + e);
		}
	}

	/**Method Name:showQlinkInfo() 
	 * @purpose Retrieving Quick Link Info 
	 * @param bean
	 */
	public void showQlinkInfo(SettingMaster bean) {
		try {
			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(32));
			ArrayList<Object> list_Ql = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_Ql = new SettingMaster();
				beanLink_Ql.setLinkCode_Ql(String.valueOf(getLinkData[i][0]));
				beanLink_Ql.setLinkName_Ql(String.valueOf(getLinkData[i][1]));
				if (String.valueOf(getLinkData[i][2]).equals("Y")) {				
					beanLink_Ql.setCheck_Q("true");
				}// END if
				else
					beanLink_Ql.setCheck_Q("false");

				list_Ql.add(beanLink_Ql);
			}// END of loop x
			bean.setList_QlLink(list_Ql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: showEmailInfo()
	 * @purpose Showing Email Info
	 * @param bean
	 */
	public void showEmailInfo(SettingMaster bean) {
		try {
			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(49));
			ArrayList<Object> list_em = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_em = new SettingMaster();
				beanLink_em.setLinkCode_EM(String.valueOf(getLinkData[i][0]));
				beanLink_em.setLinkName_EM(String.valueOf(getLinkData[i][1]));
				if (String.valueOf(getLinkData[i][2]).equals("Y")) {					
					beanLink_em.setCheck_EM("true");
				} // END if
				else
					beanLink_em.setCheck_EM("false");

				list_em.add(beanLink_em);
			}// END of loop x
			bean.setList_EM(list_em);
		} catch (Exception e) {
			logger.error("Exception Caught in Showing EMAIL info : " + e);
		}
	}

	/**Method Name: editGs()
	 * @purpose Edit General Info settings
	 * @param bean
	 */
	public void editGs(SettingMaster bean) {
		try {
			String query = "SELECT GENERAL_LINK_NAME, NVL(GENERAL_LINK_PATH,''), GENERAL_STATUS,GENERAL_VIEW_FLAG,GENERAL_TYPE FROM HRMS_SETTINGS_GENERAL WHERE GENERAL_ID = "
					+ bean.getHiddenCode_Gs();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameGs(String.valueOf(resultData[0][0]));
			bean.setCheckGeneral("true");
			if (String.valueOf(resultData[0][3]).equals("N"))
				bean.setCheckGeneral("false");
			bean.setGenType(String.valueOf(resultData[0][4]));
			String status = (String.valueOf(resultData[0][2]));
			if (status.equals("D")) {
				bean.setCheckFlag_gs("false");
				String link = String.valueOf(resultData[0][1]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadGs(complete);
			} // END if
			else if (status.equals("L")) {
				bean.setCheckFlag_gs("true");
				bean.setLinkGs(String.valueOf(resultData[0][1]));
			}// END else if
		} catch (Exception e) {
			logger.error("Exception Caught in Edit General Info : " + e);
		}
	}

	/**Method Name: deleteGs
	 * @purpose Delete General Info
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteGs(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Gs();
			boolean result = getSqlModel().singleExecute(getQuery(47), delCode);
			bean.setHiddenCode_Gs("");
			return result;
		} catch (Exception e) {
			logger.error("Exception Caught in Deleting General Info : " + e);
			return false;
		}
	}

	/**Method Name: saveCompanyComm()
	 * @purpose Saving corporate Company Communication Setting 
	 * @param companyCommBean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int saveCompanyComm(SettingMaster companyCommBean, String status,
			String poolDir) {
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][5];
				addLink[0][0] = String
						.valueOf(companyCommBean.getLinkNameCms());
				if (companyCommBean.getUploadCms().equals("")) {
					addLink[0][1] = "" + companyCommBean.getLinkCms();
					addLink[0][3] = "L";
				}// END nested if
				else {
					addLink[0][1] = "../pages/upload/"
							+ companyCommBean.getUploadCms();
					addLink[0][3] = "D";
				}// END else
				if (companyCommBean.getCheckComp().equals("true"))
					addLink[0][2] = "Y";
				else
					addLink[0][2] = "N";
				addLink[0][4] = checkNull(companyCommBean.getCorpDivCode());//Division Code

				if (companyCommBean.getHiddenCode_Cms().equals("")) {
					getSqlModel().singleExecute(getQuery(15), addLink);
					flagValue = 1;
				} // END nested if
				else {// y
					Object[][] hiddenCode = new Object[1][6];
					hiddenCode[0][0] = companyCommBean.getLinkNameCms();
					if (String.valueOf(addLink[0][3]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ companyCommBean.getUploadCms();
					else
						hiddenCode[0][1] = companyCommBean.getLinkCms();
					hiddenCode[0][2] = String.valueOf(addLink[0][3]);
					hiddenCode[0][3] = String.valueOf(addLink[0][2]);
					hiddenCode[0][4] = checkNull(companyCommBean
							.getCorpDivCode());//Division Code
					hiddenCode[0][5] = companyCommBean.getHiddenCode_Cms();
					getSqlModel().singleExecute(getQuery(17), hiddenCode);
					flagValue = 2;
				}// END else y
				xml_Cms(poolDir);
			}// END if "save"
			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(16));
			ArrayList<Object> list_cms = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SettingMaster beanLink_Cms = new SettingMaster();
				beanLink_Cms.setLinkCode_Cms(String.valueOf(getLinkData[i][0]));
				beanLink_Cms.setLinkName_Cms(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				if (String.valueOf(getLinkData[i][4]).equals("L")) {					
					beanLink_Cms.setLinkFile_Cms(link);
				}// END if
				else
					beanLink_Cms.setLinkFile_Cms(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				beanLink_Cms.setLinkActive_Cms(String
						.valueOf(getLinkData[i][3]));
				list_cms.add(beanLink_Cms);
			}// END of loop x
			companyCommBean.setList_cmsLink(list_cms);
			return flagValue;
		} catch (Exception e) {
			logger
					.error("Exception in Saving corporte Company Communication Setting: "
							+ e);
			return 0;
		}
	}

	/**Method Name:xml_Cms()
	 * @purpose Writing XML for Corporate Communication
	 * @param poolDir
	 */
	public void xml_Cms(String poolDir) {
		Object[][] xmlCompData = getSqlModel().getSingleResult(getQuery(46));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;

		try {
			new XMLReaderWriter().write(buildDocument("HRHOME",
					"CORPORATE COMMUNICATION", xmlCompData), poolDir);
		} catch (Exception e) {
			logger
					.error("Exception in writing XML : Corporate Communication : "
							+ e);
		}
	}

	/**Method Name: editHr()
	 *@purpose  Edit Hr Communication settings
	 * @param bean
	 */
	public void editHr(SettingMaster bean) {
		try {
			String query = "SELECT HRCOMM_LINKNAME, NVL(HRCOMM_LINK,' '), HRCOMM_STATUS,HRCOMM_FLAG FROM HRMS_SETTINGS_HRCOMM WHERE HRCOMM_CODE = "
					+ bean.getHiddenCode_Hr();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameHr(String.valueOf(resultData[0][0]));
			bean.setCheckHr(String.valueOf(resultData[0][3]));
			String status = (String.valueOf(resultData[0][2]));
			if (status.equals("D")) {
				bean.setCheckFlag_hr("false");
				String link = (String.valueOf(resultData[0][1]));
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadHr(complete);
			}// END if
			else if (status.equals("L")) {
				bean.setCheckFlag_hr("true");
				bean.setLinkHr(String.valueOf(resultData[0][1]));
			}// END else if
			String view = bean.getCheckHr();			
			if (view.equals("Y"))
				bean.setCheckHr("true");
			else
				bean.setCheckHr("false");
		} catch (Exception e) {
			logger
					.error("Exception in editing HR COmmunication Settings: "
							+ e);
		}
	}

	/**Method Name:editBbs()
	 * @purpose Edit Bulletin Board Settings
	 * @param bean
	 */
	public void editBbs(SettingMaster bean) {
		try {
			String query = "SELECT BULLETIN_LINKNAME, NVL(BULLETIN_LINK,' '), BULLETIN_STATUS,BULLETIN_FLAG FROM HRMS_SETTINGS_BULLETIN WHERE BULLETIN_CODE = "
					+ bean.getHiddenCode_Bbs();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameBbs(String.valueOf(resultData[0][0]));
			bean.setCheckBulletin(String.valueOf(resultData[0][3]));
			String status = (String.valueOf(resultData[0][2]));
			if (status.equals("D")) {
				bean.setCheckFlag_Bbs("false");
				String link = (String.valueOf(resultData[0][1]));
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadBbs(complete);

			} // END if
			else if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);// .substring(7,String.valueOf(resultData[0][1]).length());
				bean.setCheckFlag_Bbs("true");
				bean.setLinkBbs(linkName);
			}// END else if
			String view = bean.getCheckBulletin();
			if (view.equals("Y"))
				bean.setCheckBulletin("true");
			else
				bean.setCheckBulletin("false");
		} catch (Exception e) {
			logger.error("Exception in editing Bulletin Board Settings: " + e);
		}

	}

	/**Method Name: editCs()
	 * @purpose Edit Corporate Info Settings 
	 * @param bean
	 */
	public void editCs(SettingMaster bean) {
		String getName = "";
		String code = "";
		try {
			String query = " SELECT CORPINFO_LINKNAME, NVL(CORPINFO_LINK,' '), "
					+ " CORPINFO_STATUS , CORPINFO_FLAG, NVL(CORPINFO_DIVISION,'') "
					+ " FROM HRMS_SETTINGS_CORPINFO WHERE CORPINFO_CODE = "
					+ bean.getHiddenCode_Cs();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameCs(String.valueOf(resultData[0][0]));//Link Name
			bean.setCheckCorp(String.valueOf(resultData[0][3]));//Active flag
			String status = (String.valueOf(resultData[0][2]));//Status
			bean.setApplDivisionCode(String.valueOf(resultData[0][4]));//Division Code
			if (status.equals("D")) {
				bean.setCheckFlag_cs("false");
				String link = String.valueOf(resultData[0][1]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadCs(complete);
			}// END if
			else if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);// .substring(7,String.valueOf(resultData[0][1]).length());
				bean.setCheckFlag_cs("true");
				bean.setLinkCs(linkName);
			}// END else if
			String view = bean.getCheckCorp();
			if (view.equals("Y"))
				bean.setCheckCorp("true");
			else
				bean.setCheckCorp("false");
			if (!(resultData[0][4] == null
					|| String.valueOf(resultData[0][4]).equals("") || String
					.valueOf(resultData[0][4]).equals("null"))) {
				String divQuery = "SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
						+ String.valueOf(resultData[0][4]) + ")";
				Object[][] obj = getSqlModel().getSingleResult(divQuery);
				code = String.valueOf(resultData[0][4]);
				getName = Utility.getNameByKey(obj, code);
				bean.setApplDivisionName(getName);//Division name with comma Seprated 
			}
		} catch (Exception e) {
			logger
					.error("Exception caught in eiditng corporate Info Settings : "
							+ e);
			e.printStackTrace();
		}
	}

	/**Method Name: editTs()
	 * @purpose Edit Corporate Info Settings 
	 * @param bean
	 */
	public void editTs(SettingMaster bean) {
		try {
			String query = " SELECT TIP_LINKNAME, NVL(TIP_LINK,' '), TIP_STATUS , TIP_FLAG  FROM HRMS_SETTINGS_TIPS WHERE TIP_CODE = "
					+ bean.getHiddenCode_TS();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setTipsName(String.valueOf(resultData[0][0]));
			bean.setActiveTip(String.valueOf(resultData[0][3]));
			String status = (String.valueOf(resultData[0][2]));
			if (status.equals("D")) {
				bean.setCheckFlag_TS("false");
				String link = String.valueOf(resultData[0][1]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadTs(complete);				
			}// END if
			if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);// .substring(7,String.valueOf(resultData[0][1]).length());
				bean.setCheckFlag_TS("true");
				bean.setLinkTs(linkName);
			}// END else if
			String view = bean.getActiveTip().trim();
			if (view.equals("Y")) {
				bean.setActiveTip("true");
			} else {
				bean.setActiveTip("false");
			}
			String divisionQuery = "  SELECT NVL(TIP_DIVISION,'') FROM HRMS_SETTINGS_TIPS "
					+ "  WHERE TIP_CODE =" + bean.getHiddenCode_TS().trim();
			Object divisionObj[][] = getSqlModel().getSingleResult(
					divisionQuery);
			String str = "";
			if (divisionObj != null && divisionObj.length > 0) {
				String divNameQuery = " SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
						+ String.valueOf(divisionObj[0][0]) + ")";

				Object divNameQueryObj[][] = getSqlModel().getSingleResult(
						divNameQuery);
				if (divNameQueryObj != null && divNameQueryObj.length > 0) {
					Object[][] divisionDataObj = new Object[divNameQueryObj.length][1];
					for (int i = 0; i < divNameQueryObj.length; i++) {
						bean.setDivisionCode(String.valueOf(divisionObj[0][0]));
						divisionDataObj[i][0] = String
								.valueOf(divNameQueryObj[i][0]);
					}
					if (divisionDataObj != null && divisionDataObj.length > 0) {
						for (int j = 0; j < divisionDataObj.length; j++) {
							str += String.valueOf(j + 1) + ".";
							str += String.valueOf(divisionDataObj[j][0])
									+ "\n ";
							bean.setDivisionName(str);
						}
					}
				}
			}
		} catch (Exception e) {
			logger
					.error("Exception caught in eiditng corporate Info Settings : "
							+ e);			
		}
	}

	/**Method Name: editQl()
	 * @purpose Editing Quick Links 
	 * @param bean
	 */
	public void editQl(SettingMaster bean) {
		try {
			String query = "SELECT QUICK_ADMIN_LINKNAME,NVL(QUICK_ADMIN_LINK,' '),QUICK_ADMIN_STATUS FROM HRMS_SETTINGS_QUICKLINK_ADMIN WHERE QUICK_ADMIN_CODE = "
					+ bean.getHiddenCode_Ql();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameQl(String.valueOf(resultData[0][0]));
			String status = (String.valueOf(resultData[0][2]));
			if (status.equals("D")) {
				bean.setCheckFlag_Ql("false");
				String link = String.valueOf(resultData[0][1]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadQl(complete);
			}// END if
			else if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);// .substring(7,String.valueOf(resultData[0][1]).length());
				bean.setCheckFlag_Ql("true");
				bean.setLinkQl(linkName);
			}// END else if
		} catch (Exception e) {
			logger.error("Exception caught in eiditng Quick Links Settings : "
					+ e);
		}
	}

	/**Method Name: editKs()
	 * @purpose Editing Knowledge Settings 
	 * @param bean
	 */
	public void editKs(SettingMaster bean) {
		String getName = "";
		String code = "";
		try {
			String query = "SELECT KNOW_LINKNAME, NVL(KNOW_LINK,' '), KNOW_STATUS ,"
					+ " KNOW_FLAG, KNOW_DIVISION, KNOW_CATEGORY"
					+ " FROM HRMS_SETTINGS_KNOWLEDGE WHERE KNOW_CODE = "
					+ bean.getHiddenCode_Ks();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameKs(String.valueOf(resultData[0][0]));//Link Name
			bean.setCheckKnow(String.valueOf(resultData[0][3]));//Active Flag
			String status = (String.valueOf(resultData[0][2]));//Status
			bean.setKnowDivCode(String.valueOf(resultData[0][4]));//Division Code
			if (!(String.valueOf(resultData[0][5]).equals("null"))
					|| String.valueOf(resultData[0][5]) == null) {
				bean.setKnowCatName(String.valueOf(resultData[0][5]));//Category Name
				bean.setKnowCatNmSelect(String.valueOf(resultData[0][5]));//Category Name DropDown
			} else {
				bean.setKnowCatName("Other");
				bean.setKnowCatNmSelect("Other");
			}
			if (status.equals("D")) {
				bean.setCheckFlag_ks("false");
				String link = String.valueOf(resultData[0][1]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadKs(complete);
			} // END if
			else if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);
				bean.setCheckFlag_ks("true");
				bean.setLinkKs(linkName);
			}// END else if
			String view = bean.getCheckKnow();
			if (view.equals("Y"))
				bean.setCheckKnow("true");
			else
				bean.setCheckKnow("false");
			if (!(resultData[0][4] == null
					|| String.valueOf(resultData[0][4]).equals("") || String
					.valueOf(resultData[0][4]).equals("null"))) {
				String divQuery = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
						+ String.valueOf(resultData[0][4]) + ")";
				Object[][] obj = getSqlModel().getSingleResult(divQuery);
				code = String.valueOf(resultData[0][4]);
				getName = Utility.getNameByKey(obj, code);
				bean.setKnowDivName(getName);//Division Name Comma seprated 
			}
			setCategory(bean);
		} catch (Exception e) {
			logger.error("Exception caught in editng Knowledge Settings : "
							+ e);
		}
	}

	/**Method Name: editCms()
	 * @purpose Editing Corporate Messages 
	 * @param bean
	 */
	public void editCms(SettingMaster bean) {
		String getName = "";
		String code = "";
		try {
			String query = "SELECT CORPMSG_LINKNAME, NVL(CORPMSG_LINK,' '), "
					+ " CORPMSG_STATUS ,CORPMSG_FLAG, NVL(CORPMSG_DIVISION,'')  "
					+ " FROM HRMS_SETTINGS_CORPMSG WHERE CORPMSG_CODE = "
					+ bean.getHiddenCode_Cms();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameCms(String.valueOf(resultData[0][0]));//Link Name
			bean.setCheckComp(String.valueOf(resultData[0][3]));//Active Flag
			String status = (String.valueOf(resultData[0][2]));//Status
			bean.setCorpDivCode(String.valueOf(resultData[0][4]));//Division Code
			if (status.equals("D")) {
				bean.setCheckFlag_cms("false");
				String link = String.valueOf(resultData[0][1]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				bean.setUploadCms(complete);
			}// END if
			else if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);// .substring(7,String.valueOf(resultData[0][1]).length());
				bean.setCheckFlag_cms("true");
				bean.setLinkCms(linkName);
			}// END else if
			String view = bean.getCheckComp();
			if (view.equals("Y"))
				bean.setCheckComp("true");
			else
				bean.setCheckComp("false");

			if (!(resultData[0][4] == null
					|| String.valueOf(resultData[0][4]).equals("") || String
					.valueOf(resultData[0][4]).equals("null"))) {
				String divNameQuery = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
						+ String.valueOf(resultData[0][4]) + ")";

				Object obj[][] = getSqlModel().getSingleResult(divNameQuery);
				code = String.valueOf(resultData[0][4]);
				getName = Utility.getNameByKey(obj, code);
				bean.setCorpDivName(getName);//Division Name Comma Seprated
			}
		} catch (Exception e) {
			logger.error("Exception caught in Editing Corporate Messages :  "
					+ e);
		}
	}

	/**Method Name: deleteHr()
	 * @purpose Deleting Hr Communication Settings
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteHr(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Hr();
			boolean result = getSqlModel().singleExecute(getQuery(6), delCode);
			bean.setHiddenCode_Hr("");
			return result;
		} catch (Exception e) {
			logger
					.error("Exception caught in Deleting Hr Communication Settings :  "
							+ e);
			return false;
		}
	}

	/**Method Name: deleteBbs()
	 * @purpose Deleting Bulletin Board Details
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteBbs(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Bbs();
			boolean result = getSqlModel().singleExecute(getQuery(29), delCode);
			bean.setHiddenCode_Bbs("");
			return result;
		} catch (Exception e) {
			logger
					.error("Exception caught in Deleting Bulletin Board Settings :  "
							+ e);
			return false;
		}
	}

	/**Method Name: deleteCs()
	 * @purpose Deleting Corporate Settings details 
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteCs(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Cs();
			boolean result = getSqlModel().singleExecute(getQuery(10), delCode);
			xml_Cs(poolDir);
			bean.setHiddenCode_Cs("");
			return result;
		} catch (Exception e) {
			logger.error("Exception caught in Deleting Corporate Settings :  "
					+ e);
			return false;
		}
	}

	/**Method Name: deleteTs()
	 * @purpose Deleting Corporate Settings details 
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteTs(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_TS();
			boolean result = getSqlModel().singleExecute(getQuery(57), delCode);
			xml_Ts(poolDir);
			bean.setHiddenCode_TS("");
			return result;
		} catch (Exception e) {
			logger.error("Exception caught in Deleting Tips Settings :  " + e);
			return false;
		}
	}

	/**Method Name: deleteKs()
	 * @purpose Deleting Knowledge Settings details
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteKs(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Ks();
			boolean result = getSqlModel().singleExecute(getQuery(14), delCode);
			xml_Ks(poolDir);
			return result;
		} catch (Exception e) {
			logger.error("Exception caught in Deleting Knowledge Settings :  "
					+ e);
			return false;
		}
	}

	/**Method Name: deleteQl()
	 * @purpose Deleting Quick Links
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteQl(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Ql();
			boolean result = getSqlModel().singleExecute(getQuery(34), delCode);
			return result;
		} catch (Exception e) {
			logger.error("Exception caught in Deleting Quick Link Settings :  "
					+ e);
			return false;
		}
	}

	/**Method Name: deleteCms()
	 * @purpose Deleting Corporate Messages
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteCms(SettingMaster bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Cms();
			boolean result = getSqlModel().singleExecute(getQuery(18), delCode);
			xml_Cms(poolDir);
			bean.setHiddenCode_Cms("");
			return result;
		} catch (Exception e) {
			logger.error("Exception caught in Deleting Corporate Messages :  "
					+ e);
			return false;
		}
	}

	/**Method Name: addThougth()
	 * @purpose To add a thought
	 * @param bean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int addThougth(SettingMaster bean, String status, String poolDir) {
		try {
			int value = 0;
			if (status.equals("save")) {
				Object[][] saveData = new Object[1][1];
				saveData[0][0] = bean.getThougth().trim();
				if (bean.getHiddenCode_th().equals("")) {
					if (getSqlModel().singleExecute(getQuery(21), saveData))
						value = 1;
				} // END nested if
				else {
					Object[][] hiddenCode = new Object[1][2];
					hiddenCode[0][0] = bean.getThougth().trim();
					hiddenCode[0][1] = bean.getHiddenCode_th();
					if (getSqlModel().singleExecute(getQuery(23), hiddenCode))
						value = 2;
				}// END else
				// write XML
				xml_Thought(poolDir);
			}// END if "save"

			Object data[][] = getSqlModel().getSingleResult(getQuery(22));
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = 0; i < data.length; i++) {// loop x
				SettingMaster thBean = new SettingMaster();
				thBean.setThougthCode(String.valueOf(data[i][0]));
				thBean.setThougthName(String.valueOf(data[i][1]).trim());
				thBean.setThougthDate(String.valueOf(data[i][2]));
				list.add(thBean);

			}// END of loop x
			bean.setList_thougth(list);
			return value;

		} catch (Exception e) {
			logger.error("Exception caught in Adding Thought :  " + e);
			return 0;
		}
	}

	/**Method Name: editThougth()
	 * @purpose Edit Thought
	 * @param bean
	 */
	public void editThougth(SettingMaster bean) {
		try {
			String query = " SELECT THOUGHT_CODE,THOUGHT_NAME FROM HRMS_SETTINGS_THOUGHT WHERE THOUGHT_CODE = "
					+ bean.getHiddenCode_th();
			Object data[][] = getSqlModel().getSingleResult(query);
			bean.setHiddenCode_th(String.valueOf(data[0][0]));
			bean.setThougth(String.valueOf(data[0][1]).trim());
		} catch (Exception e) {
			logger.error("Exception caught in Editing Thought :  " + e);
		}
	}

	/**Method Name: deleteThougth()
	 * @purpose Deleting Thought
	 * @param bean
	 * @return boolean
	 */
	public boolean deleteThougth(SettingMaster bean) {
		try {
			Object delCode[][] = new Object[1][1];
			delCode[0][0] = String.valueOf(bean.getHiddenCode_th());
			boolean flag = getSqlModel().singleExecute(getQuery(24), delCode);
			return flag;
		} catch (Exception e) {
			logger.error("Exception caught in Deleting Thought :  " + e);
			return false;
		}
	}

	/**Method Name: xml_Thought()
	 * @purpose Writing XML for Thought
	 * @param poolDir
	 */
	public void xml_Thought(String poolDir) {
		Object xmlThought[][] = getSqlModel().getSingleResult(getQuery(39));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;
		if (xmlThought == null)
			xmlThought = new Object[0][0];
		try {
			new XMLReaderWriter().write(buildDocument("HRHOME", "THOUGHT",
					xmlThought), poolDir);
		} catch (Exception e) {
			logger.error("Exception caught in Writing XML For tHought :  " + e);
		}
	}

	/**
	 * Inbound Mail Settings Configuration Set fields after save method 
	 * @param bean
	 * @return boolean
	 */
	/*
	 * public boolean saveMailBoxConf(SettingMaster bean) { try { String
	 * mailquery = "SELECT
	 * MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,MAILBOX_PASSW
	 * FROM HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'I'"; Object[][] addMail =
	 * getSqlModel().getSingleResult(mailquery);
	 * bean.setMailServer(checkNull(String.valueOf(addMail[0][0])));
	 * bean.setMailProtocol(checkNull(String.valueOf(addMail[0][1])));
	 * bean.setMailPort(checkNull(String.valueOf(addMail[0][2])));
	 * bean.setMailUsername(checkNull(String.valueOf(addMail[0][3]))); if
	 * (String.valueOf(addMail[0][3]).equals("null")) bean.setMailUsername("");
	 * bean.setMailPasswd(checkNull(String.valueOf(addMail[0][4]))); return
	 * true; } catch (Exception e) { logger.error("Exception caught in Saving
	 * Inbound Mail Settings : " + e); return false; } }
	 */
	/**
	 * Outbound Mail Settings Configuration For Setting the fields after save
	 * Method
	 * 
	 * @param bean
	 * @return boolean
	 */
	/*
	 * public boolean saveMailBoxConfOut(SettingMaster bean) { try { String
	 * mailquery = "SELECT
	 * MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,nvl(MAILBOX_USERID,'
	 * '),nvl(MAILBOX_PASSW,' '),MAILBOX_AUTH_CHK,MAILBOX_POP_BEFORE_SMTP FROM
	 * HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'O' "; Object[][] addMail =
	 * getSqlModel().getSingleResult(mailquery);
	 * bean.setMailServerOut(checkNull(String.valueOf(addMail[0][0])));
	 * bean.setMailProtocolOut(checkNull(String.valueOf(addMail[0][1])));
	 * bean.setMailPortOut(checkNull(String.valueOf(addMail[0][2])));
	 * bean.setMailUsernameOut(checkNull(String.valueOf(addMail[0][3])));
	 * bean.setMailPasswdOut(checkNull(String.valueOf(addMail[0][4])));
	 * bean.setAuthChk("true"); if (String.valueOf(addMail[0][5]).equals("N"))
	 * bean.setAuthChk("false"); logger.info("setPbSmtp( ---------
	 * "+checkNull(String.valueOf(addMail[0][6])));
	 * bean.setPbSmtp(checkNull(String.valueOf(addMail[0][6]))); return true; }
	 * catch (Exception e) { logger .error("Exception caught in Saving Outbound
	 * Mail Settings : " + e); return false; } }
	 */
	/**
	 * Save Inbound Mail Settings
	 * 
	 * @param bean
	 * @return boolean
	 */
	/*
	 * public boolean addMailBoxConf(SettingMaster bean, String poolDir) { try {
	 * boolean flag = false; String Query = "SELECT MAILBOX_PORT from
	 * HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'I' "; Object[][] getResult =
	 * getSqlModel().getSingleResult(Query);
	 * 
	 * Object[][] addMailbox = new Object[1][8]; addMailbox[0][0] =
	 * bean.getMailServer(); addMailbox[0][1] = bean.getMailProtocol();
	 * addMailbox[0][2] = bean.getMailPort(); addMailbox[0][3] =
	 * bean.getMailUsername(); addMailbox[0][4] = bean.getMailPasswd();
	 * addMailbox[0][5] = ""; addMailbox[0][6] = ""; addMailbox[0][7] = "I";
	 * logger.info(" User Name " + addMailbox[0][3]); logger.info(" Password " +
	 * addMailbox[0][4]); if (getResult.length == 0) { logger.info("Inside If " +
	 * getResult.length); flag = getSqlModel().singleExecute(getQuery(25),
	 * addMailbox); }// END if else { logger.info("Inside Else " +
	 * getResult.length); flag = getSqlModel().singleExecute(getQuery(30),
	 * addMailbox); }
	 * 
	 * xml_mail(poolDir);
	 * 
	 * return flag; // END else } catch (Exception e) { logger.error("Exception
	 * caught in Adding Inbound Mail Settings : " + e); return false; } }
	 */

	public void xml_mail(String poolDir) {
		String mailInQuery = " SELECT MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,MAILBOX_PASSW from HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'I' ";
		Object[][] mailInResult = getSqlModel().getSingleResult(mailInQuery);
		String mailOutQuery = " SELECT MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_POP_BEFORE_SMTP from HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'O' ";
		Object[][] mailOutResult = getSqlModel().getSingleResult(mailOutQuery);
		String sysQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM  HRMS_SETTINGS_SYSMAILID ";
		Object[][] sysResult = getSqlModel().getSingleResult(sysQuery);
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;
		try {
			// XML write
			new XMLReaderWriter().write(buildDocumentMail("MAILBOX",
					mailInResult, mailOutResult, sysResult), poolDir);
		} catch (Exception e) {
			logger.error("Exception caught in writing: " + e);
		}

	}


	/*
	 * public boolean addMailBoxConfOut(SettingMaster bean, String poolDir) {
	 * try { boolean flag = false; String Query = "SELECT MAILBOX_PORT from
	 * HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG = 'O'"; Object[][] getResult =
	 * getSqlModel().getSingleResult(Query); Object[][] addMailbox = new
	 * Object[1][8]; addMailbox[0][0] = bean.getMailServerOut();
	 * addMailbox[0][1] = bean.getMailProtocolOut(); addMailbox[0][2] =
	 * bean.getMailPortOut(); addMailbox[0][3] = bean.getMailUsernameOut();
	 * addMailbox[0][4] = bean.getMailPasswdOut(); addMailbox[0][5] = "Y";
	 * addMailbox[0][6] = bean.getPbSmtp(); addMailbox[0][7] = "O";
	 * logger.info("bean.getPbSmtp()"+bean.getPbSmtp()); if
	 * (bean.getAuthChk().equals("false")) { addMailbox[0][5] = "N";
	 * addMailbox[0][3] = ""; addMailbox[0][4] = ""; }// END if
	 * logger.info("Auth Chk " + addMailbox[0][6]); logger.info(" User Name " +
	 * addMailbox[0][3]); logger.info(" Password " + addMailbox[0][4]); if
	 * (getResult.length == 0) flag = getSqlModel().singleExecute(getQuery(25),
	 * addMailbox); else { flag = getSqlModel().singleExecute(getQuery(30),
	 * addMailbox); } xml_mail(poolDir); return flag; } catch (Exception e) {
	 * logger .error("Exception caught in Adding Outbound Mail Settings : " +
	 * e); return false; } }
	 */
	/**Method Name: savePassSetting()
	 * @purpose saving Password Settings
	 * @param setting
	 * @return boolean
	 */
	public boolean savePassSetting(SettingMaster setting) {
		try {
			Object[][] addPass = new Object[1][18];
			if (setting.getExpFlag().equals("true")) {
				addPass[0][0] = "Y";
			} // END if
			else
				addPass[0][0] = "N";
			addPass[0][1] = checkNull(setting.getExpPeriodicity());
			if (setting.getReuseFlag().equals("true")) {
				addPass[0][2] = "Y";
			} // END if
			else
				addPass[0][2] = "N";
			addPass[0][3] = checkNull(setting.getReusePassPed());
			if (setting.getLockFlag().equals("true")) {
				addPass[0][4] = "Y";
			} // END if
			else
				addPass[0][4] = "N";
			addPass[0][5] = checkNull(setting.getLockPrd());
			addPass[0][6] = checkNull(setting.getLockAttmpt());
			addPass[0][7] = "N";
			if (setting.getIncludeAlpha().equals("true"))
				addPass[0][7] = "Y";
			addPass[0][8] = "N";
			if (setting.getIncludeSpChar().equals("true"))
				addPass[0][8] = "Y";
			addPass[0][9] = "N";
			if (setting.getIncludeNum().equals("true"))
				addPass[0][9] = "Y";
			addPass[0][10] = "N";
			if (setting.getIncludeUpCase().equals("true"))
				addPass[0][10] = "Y";
			addPass[0][11] = checkNull(setting.getMinPwdLen());
			addPass[0][12] = checkNull(setting.getMaxPwdLen());
			addPass[0][13] = "N";
			if (setting.getVirtualKey().equals("true")) {
				addPass[0][13] = "Y";
				setting.setReuseFlag("true");
			}
			addPass[0][14] = "N";
			if (setting.getEnforceKey().equals("true"))
				addPass[0][14] = "Y";			
			addPass[0][15] = "N";
			if (setting.getEnableTxtImg().equals("true")) {
				addPass[0][15] = "Y";
			} else {
				addPass[0][15] = "N";
			}

			if (setting.getSecureQue().equals("true")) {
				addPass[0][16] = "Y";
			} else {
				addPass[0][16] = "N";
			}

			if (setting.getForgotPassQuestion().equals("true")) {
				addPass[0][17] = "Y";
			} else {
				addPass[0][17] = "N";
			}

			getSqlModel().singleExecute(getQuery(53));
			return getSqlModel().singleExecute(getQuery(52), addPass);
		} catch (Exception e) {
			logger
					.error("Exception caught in Saving Password Settings :  "
							+ e);
			return false;
		}
	}

	/**
	 * Set fields after asve Password Settings
	 * 
	 * @param setting
	 */
	public void showPassSetting(SettingMaster setting) {
		try {
			String Query = "SELECT PWD_EXPIRY_FLAG,PWD_EXPIRY_PERIODICITY,PWD_REUSE_FLAG,PWD_REUSE_PERIODICITY"
					+ ",PWD_LOCK_FLAG,PWD_LOCK_PERIODICITY,PWD_LOCK_ATTEMPTS,"
					+ " PWD_INCLUDE_ALPHA,PWD_INCLUDE_SPCHAR,PWD_INCLUDE_NUM,PWD_INCLUDE_UPCASE,PWD_MIN_LENGTH,PWD_MAX_LENGTH,PWD_ENABLE_KB,PWD_ENABLE_TXTIMG,PWD_ENABLE_SECUREQUE"
					+ " ,PWD_ENABLE_FORGOT_QUES from HRMS_SETTINGS_PWD ";
			Object[][] Data = getSqlModel().getSingleResult(Query);
			if (Data.length > 0) {
				/* expiry flag */
				if (String.valueOf(Data[0][0]).equals("Y")) {
					setting.setExpFlag("true");
				}// END nested if
				else
					setting.setExpFlag("false");

				/* Expiry periodicity */
				setting.setExpPeriodicity(String.valueOf(Data[0][1]));

				/* reuse flag */
				if (String.valueOf(Data[0][2]).equals("Y")) {
					setting.setReuseFlag("true");
				}// END nested if
				else
					setting.setReuseFlag("false");

				/* Reuse periodicity */
				setting.setReusePassPed(String.valueOf(Data[0][3]));

				/* Lock flag */
				if (String.valueOf(Data[0][4]).equals("Y")) {
					setting.setLockFlag("true");					
				}// END nested if
				else
					setting.setLockFlag("false");

				/* Lock Period */
				setting.setLockPrd(String.valueOf(Data[0][5]));
				/* Lock Attempts */
				setting.setLockAttmpt(String.valueOf(Data[0][6]));

				/* Include Alphabets */
				setting.setIncludeAlpha("false");
				if (String.valueOf(Data[0][7]).equals("Y"))
					setting.setIncludeAlpha("true");

				/* Include Special Characters */
				setting.setIncludeSpChar("false");
				if (String.valueOf(Data[0][8]).equals("Y"))
					setting.setIncludeSpChar("true");

				/* Include Numbers */
				setting.setIncludeNum("false");
				if (String.valueOf(Data[0][9]).equals("Y"))
					setting.setIncludeNum("true");

				/* Atleast one upper case letter */
				setting.setIncludeUpCase("false");
				if (String.valueOf(Data[0][10]).equals("Y"))
					setting.setIncludeUpCase("true");

				/* Minimum length */
				setting.setMinPwdLen(checkNull(String.valueOf(Data[0][11])));
				/* maximum length */
				setting.setMaxPwdLen(checkNull(String.valueOf(Data[0][12])));
				if (String.valueOf(Data[0][13]).equals("Y"))
					setting.setVirtualKey("true");
				else
					setting.setVirtualKey("false");
				if (String.valueOf(Data[0][14]).equals("Y"))
					setting.setEnableTxtImg("true");
				else
					setting.setEnableTxtImg("false");
				if (String.valueOf(Data[0][15]).equals("Y"))
					setting.setSecureQue("true");
				else
					setting.setSecureQue("false");

				if (String.valueOf(Data[0][16]).equals("Y")) {
					setting.setForgotPassQuestion("true");
				} else {
					setting.setForgotPassQuestion("false");
				}

			}// END if chking length
		} catch (Exception e) {
			logger.error("Exception caught in Setting Password Settings :  "
					+ e);
		}
	}


	/*
	 * public boolean saveSysMail(SettingMaster bean, String poolDir) {
	 * 
	 * try { boolean flag = false; Object[][] saveObj = new Object[1][2];
	 * saveObj[0][0] = bean.getSysEmail(); saveObj[0][1] =
	 * bean.getSysEmailPsswd(); flag = getSqlModel().singleExecute(getQuery(54),
	 * saveObj); xml_mail(poolDir); return flag; } catch (Exception e) { logger
	 * .error("Exception caught in Saving System Mail id and password : " + e);
	 * return false; } }
	 */
	
	/*
	 * public boolean updateSysMail(SettingMaster bean) { try { Object[][]
	 * updateObj = new Object[1][3]; updateObj[0][0] = bean.getSysEmail();
	 * updateObj[0][1] = bean.getSysEmailPsswd(); updateObj[0][2] =
	 * bean.getHiddenMailCode(); return
	 * getSqlModel().singleExecute(getQuery(55), updateObj); } catch (Exception
	 * e) { return false; } }
	 */
	
	/*
	 * public void editSysMail(SettingMaster bean) { try { String query = "
	 * SELECT SYSMAIL_EMAIL_ID FROM HRMS_SETTINGS_SYSMAILID " + " WHERE
	 * SYSMAIL_CODE = " + bean.getHiddenMailCode(); Object resultData[][] =
	 * getSqlModel().getSingleResult(query);
	 * bean.setSysEmail(String.valueOf(resultData[0][0])); } catch (Exception e) { //
	 * Settings : " + e); } }
	 */
	/*
	 * public boolean deleteSysMail(SettingMaster bean) { try { String query =
	 * "DELETE FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_CODE = " +
	 * bean.getHiddenMailCode(); return getSqlModel().singleExecute(query); }
	 * catch (Exception e) { logger.error("Exception caught in Deleting Sys Mail
	 * Settings : " + e); return false; } }
	 */
	/*
	 * public void showSysMail(SettingMaster bean) { try { logger.info("Inside
	 * Sys Mail"); ArrayList<Object> list = new ArrayList<Object>(); Object
	 * resultData[][] = getSqlModel().getSingleResult(getQuery(56)); if
	 * (resultData != null & resultData.length > 0) { for (int i = 0; i <
	 * resultData.length; i++) {// loop x SettingMaster mailBean = new
	 * SettingMaster();
	 * mailBean.setSysMailCode(String.valueOf(resultData[i][0]));
	 * mailBean.setSysEmailId(String.valueOf(resultData[i][1]));
	 * list.add(mailBean); }// END loop x }// END if bean.setSysMailList(list); }
	 * catch (Exception e) {e.printStackStrace();} }
	 */
	/**Method Name: checkNull()
	 * @purpose Removing null values
	 * @param result
	 * @return String replaces null with ""
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// END if
		else {
			return result;
		}// END else
	}

	/**Method Name:testConnection()
	 * @purpose This method is used for checking mail setting and mail addresses.
	 * @param mailBoxData
	 * @param bean
	 * @return String as a error message
	 */
	private String testConnection(Object[][] mailBoxData, SettingMaster bean) {
		Session session1 = null;
		BodyPart messageBodyPart = new MimeBodyPart();
		javax.mail.Transport t = null;
		Store store = null;
		final String userName = String.valueOf(mailBoxData[0][3]);
		final String pass = String.valueOf(mailBoxData[0][4]);
		/**
		 * Make host and port as persistent
		 */	
		Properties props = new Properties();
		props.put("mail.smtp.host", String.valueOf(mailBoxData[0][0]).trim());
		props.put("mail.smtp.port", String.valueOf(mailBoxData[0][2]).trim());
		if (String.valueOf(mailBoxData[0][9]).equals("pop3/ssl")) {
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.ssl.trust", "*");
		}

		if (String.valueOf(mailBoxData[0][5]).equals("Y")) {
			// Authentication is true
			props.put("mail.smtp.auth", "true");
			/**
			 * Authenticator -: Authenticates the network connection
			 * PasswordAuthentication -: Repository for a user name and a
			 * password used by Authenticator.
			 */
			Authenticator auth = null;
			try {
				auth = new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, pass);
					}
				};
			} catch (Exception e) {
				return exceptionMessage += "\n" + e.getMessage();
			}
			try {
				session1 = Session.getInstance(props, auth);// Creates a mail
				// session
			} catch (Exception e) {
				return exceptionMessage += "\n" + e;
			}
		} // end of if statement
		else {
			try {
				session1 = Session.getInstance(props);
			} catch (Exception e) {
				return exceptionMessage += "\n" + e;
			}
		}
		if (String.valueOf(mailBoxData[0][7]).equals("Y")) {
			String inBoundSettingQuery = "  SELECT MAILBOX_SERVER, MAILBOX_PROTOCOL, MAILBOX_PORT, "
					+ " MAILBOX_USERID, MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_FLAG ,MAILBOX_POP_BEFORE_SMTP "
					+ " FROM "
					+ "	HRMS_SETTINGS_MAILBOX "
					+ "	WHERE "
					+ "	MAILBOX_FLAG='I'";
			Object inBoundSettingObj[][] = getSqlModel().getSingleResult(
					inBoundSettingQuery);
			try {
				store = session1.getStore("pop3");
			} catch (NoSuchProviderException e1) {
				return exceptionMessage += "\n" + e1;
			}
			try {
				store.connect(String.valueOf(inBoundSettingObj[0][0]), String
						.valueOf(inBoundSettingObj[0][3]), String
						.valueOf(inBoundSettingObj[0][4]));
			} catch (Exception e) {
				return exceptionMessage += "\n" + e;
			}
		} else {
			/**
			 * Transport -: An abstract class that models a message transport
			 */
			try {
				t = session1.getTransport("smtp");// Get a Transport object
				// that implements the
				// specified protocol
			} catch (NoSuchProviderException e1) {
				return exceptionMessage += "\n" + e1;
			} // end of try-catch block
			try {
				if (t != null) {
					t.connect();// Makes a connection to the service
				}
			} catch (AuthenticationFailedException ae) {
				return exceptionMessage += "\n"
						+ "Authentication Failed.\nPlease verify Authentication username and password.";

			} catch (Exception e) {
				return exceptionMessage += "\n" + e.getMessage();

			} // end of try-catch block
		}
		session1.setDebug(true);
		javax.mail.Message message = new MimeMessage(session1);
		MimeMultipart multipart = new MimeMultipart("related");
		try {
			((MimeMessage) message).setSubject("This is a Test Mail.");
		} catch (Exception e) {
			return exceptionMessage += "\n" + e.getMessage();
		}
		InternetAddress fromEmail = null;
		/*
		 * try { fromEmail = new InternetAddress(bean.getFromMailId().trim(),
		 * true); } catch (AddressException e) { return exceptionMessage += "\n" +
		 * "Invalid from address."; }
		 */
		try {
			message.setFrom(fromEmail);
		} catch (Exception e) {
			return exceptionMessage += "\n" + "Invalid from address.";
		}
		try {
			// message.addHeader("To", bean.getToMailId().trim());
		} catch (Exception e) {
			return exceptionMessage += "\n" + e.getMessage();
		}
		try {
			messageBodyPart.setContent("Testing Mail Connection ", "text/html");
		} catch (Exception e) {
			return exceptionMessage += "\n" + e.getMessage();
		}
		try {
			multipart.addBodyPart(messageBodyPart);
		} catch (Exception e) {
			return exceptionMessage += "\n" + e.getMessage();
		}
		try {
			((Part) message).setContent(multipart);
		} catch (Exception e) {
			return exceptionMessage += "\n" + e.getMessage();
		}
		/*
		 * InternetAddress[] toEmail = new InternetAddress[1]; try { toEmail[0] =
		 * new InternetAddress(bean.getToMailId().trim(), true); } catch
		 * (AddressException e) { return exceptionMessage += "\n" + "Invalid to
		 * address."; }
		 */
		/*
		 * try { message.addRecipients(RecipientType.TO, toEmail); } catch
		 * (Exception e) { //exceptionMessage+="\n"+"Invalid to address."; }
		 */

		if (String.valueOf(mailBoxData[0][7]).equals("Y")) {
			try {
				Transport.send(message);
			} catch (MessagingException e1) {
				return exceptionMessage += "\n" + e1.getMessage();
			}
			try {
				store.close();
			} catch (MessagingException e1) {
				return exceptionMessage += "\n" + e1.getMessage();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				return exceptionMessage += "\n" + e.getMessage();
			}
		} else {
			try {
				if (t.isConnected()) {
					// t.sendMessage(message, toEmail);
					t.send(message);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}

			} catch (javax.mail.MessagingException se) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
				Address[] ads = ((SendFailedException) se)
						.getValidUnsentAddresses();
				if (ads != null) {
					for (int i = 0; i < ads.length; i++) {
						exceptionMessage += "\nInvalid Addresses:" + ads[i];
					}
				}
				return exceptionMessage += "\n" + se.getCause();
			}
		}

		return "Mail delivered successfully";
	}

	/**Method Name:testMailConnection()
	 * @purpose To test Mail connection 
	 * @param bean
	 * @return String 
	 */
	public String testMailConnection(SettingMaster bean) {
		String str = "Message:\n\n";
		try {

			String getMailBox = " SELECT MAILBOX_SERVER, MAILBOX_PROTOCOL, MAILBOX_PORT, MAILBOX_USERID, MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_FLAG,MAILBOX_POP_BEFORE_SMTP FROM "
					+ "	HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG='O' ";
			Object[][] mailBoxData = getSqlModel().getSingleResult(getMailBox);
			str += testConnection(mailBoxData, bean);
		} catch (Exception e) {
			logger.error("Exception in testMailConnection------------" + e);
			e.printStackTrace();
		}
		return str;
	}

	/**Method Name: showDivision()
	 * @purpose To show Division of Opininon Poll While Click on Edit
	 * @param bean
	 */
	public void showDivision(SettingMaster bean) {
		Object dataObj[][] = null;
		String code = "";
		String getName = "";
		try {
			String pollDivQuery = "SELECT NVL(POLL_DIVISION,''),POLL_CODE"
					+ " FROM HRMS_POLL_HDR" + " WHERE POLL_CODE="
					+ bean.getPollCode();//Poll Code
			dataObj = getSqlModel().getSingleResult(pollDivQuery);
			if (!(dataObj[0][0] == null
					|| String.valueOf(dataObj[0][0]).equals("") || String
					.valueOf(dataObj[0][0]).equals("null"))) {
				String divQuery = "SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
						+ String.valueOf(dataObj[0][0]) + ")";
				Object[][] obj = getSqlModel().getSingleResult(divQuery);
				code = String.valueOf(dataObj[0][0]);// check Login Emp's Division 
				getName = Utility.getNameByKey(obj, code);
				bean.setPollDivName(getName);
				bean.setDivisionCode(code);//Diivision Name with Comma Seprated 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setCategory()
	 * @purpose To Show Category Name 
	 * @param setting
	 */
	public void setCategory(SettingMaster setting) {
		try {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			String mapQuery = "SELECT DISTINCT NVL (KNOW_CATEGORY,'')"
					+ " FROM HRMS_SETTINGS_KNOWLEDGE ";
			Object[][] mapObj = getSqlModel().getSingleResult(mapQuery);
			if (mapObj != null && mapObj.length > 0) {
				for (int i = 0; i < mapObj.length; i++) {
					if (String.valueOf(mapObj[i][0]).equals("null")) {//category name 
					} else {
						map.put(String.valueOf(mapObj[i][0]), String
								.valueOf(mapObj[i][0]));
					}
				}
			}
			map.put("Other", "Other");
			setting.setKnowMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
