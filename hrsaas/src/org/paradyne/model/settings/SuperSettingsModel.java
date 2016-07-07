/**
 * 
 */
package org.paradyne.model.settings;

import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.settings.SuperSettings;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.struts.action.common.LoginAction;

/**
 * @author_Pankaj_Jain
 * 
 */
public class SuperSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoginAction.class);

	/**
	 * Saving HrWorK Communication Settings Writes XML
	 * 
	 * @param hrCommBean
	 * @param status
	 * @param poolDir
	 * @param file
	 * @return integer value
	 */
	public int saveHrComm(SuperSettings hrCommBean, String status,
			String poolDir, String file) // Saving HR Communication
	{
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
				}// END else "y"

			}// END if " save"
			// write XML
			xml_Hr(file);
			Object[][] getLinkData = getSqlModel().getSingleResult(getQuery(4));
			ArrayList<Object> list_hr = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SuperSettings beanLink_Hr = new SuperSettings();
				beanLink_Hr.setLinkCode_Hr(String.valueOf(getLinkData[i][0]));
				beanLink_Hr.setLinkName_Hr(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				if (String.valueOf(getLinkData[i][3]).equals("D"))
					beanLink_Hr.setLinkFile_Hr(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				else
					beanLink_Hr.setLinkFile_Hr(link);
				beanLink_Hr.setLinkActive_Hr(String.valueOf(getLinkData[i][4]));
				list_hr.add(beanLink_Hr);
			}// END of loop x
			hrCommBean.setList_hrLink(list_hr);

			return flagValue;
		} catch (Exception e) {
			logger
					.error("Exception caught in Saving HrWork CXommunication Settings Model : "
							+ e);
			return 0;
		}
	}

	/**
	 * Write XML for HrWorK Communication
	 * 
	 * @param poolDir
	 */
	public void xml_Hr(String poolDir) {
		Object[][] xmlHrData = getSqlModel().getSingleResult(getQuery(35));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;// path

		try {
			// Write XML
			new XMLReaderWriter().write(buildDocument("HRHOME",
					"HRWORQ COMMUNICATION", xmlHrData), poolDir);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception caught Writing HrWorK communication XML : "
					+ e);
		}
	}

	/**
	 * Editing HrWorK Communication Settings
	 * 
	 * @param bean
	 */
	public void editHr(SuperSettings bean) {
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
			} // ENd if
			else if (status.equals("L")) {
				bean.setCheckFlag_hr("true");
				bean.setLinkHr(String.valueOf(resultData[0][1]));
			}// END else if
			String view = bean.getCheckHr();
			System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiii" + view);
			if (view.equals("Y"))
				bean.setCheckHr("true");
			else
				bean.setCheckHr("false");
		} catch (Exception e) {
			logger.error("Editing HrWork Communication : " + e);
		}
	}

	/**
	 * Deleting records of HrWorK Communication from list
	 * 
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteHr(SuperSettings bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Hr();
			boolean result = getSqlModel().singleExecute(getQuery(6), delCode);
			// write XML
			xml_Hr(poolDir);
			bean.setHiddenCode_Hr("");
			return result;
		} catch (Exception e) {
			logger.error("Exception Caught in Delete - HrWork comm : " + e);
			return false;
		}
	}

	/**
	 * Building XML File for HrWorK Communication Identifying elements and nodes
	 * 
	 * @param head
	 * @param subHead
	 * @param hrHome
	 * @return document XML file
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
	 * Saving Quick Link Details
	 * 
	 * @param quickBean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int saveLink(SuperSettings quickBean, String status, String poolDir)

	{
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][4];
				addLink[0][0] = String.valueOf(quickBean.getLinkNameQl());
				addLink[0][1] = quickBean.getHiddenlinkPathQl();
				addLink[0][2] = "L";

				if (quickBean.getCheckQuick().equals("true"))
					addLink[0][3] = "Y";
				else
					addLink[0][3] = "N";
				if (quickBean.getHiddenCode_Ql().equals("")) {
					getSqlModel().singleExecute(getQuery(31), addLink);
					flagValue = 1;
				} // END nested if
				else {
					Object[][] hiddenCode = new Object[1][5];
					hiddenCode[0][0] = quickBean.getLinkNameQl();
					hiddenCode[0][1] = quickBean.getHiddenlinkPathQl();
					hiddenCode[0][2] = String.valueOf(addLink[0][2]);
					hiddenCode[0][3] = String.valueOf(addLink[0][3]);
					hiddenCode[0][4] = quickBean.getHiddenCode_Ql();
					getSqlModel().singleExecute(getQuery(33), hiddenCode);
					flagValue = 2;
				}// END else
			}// END if "save"
			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(32));
			ArrayList<Object> list_Ql = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SuperSettings beanLink_Ql = new SuperSettings();
				beanLink_Ql.setLinkCode_Ql(String.valueOf(getLinkData[i][0]));
				beanLink_Ql.setLinkName_Ql(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				if (String.valueOf(getLinkData[i][3]).equals("D"))
					beanLink_Ql.setLinkFile_Ql(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				else
					beanLink_Ql.setLinkFile_Ql(link);
				beanLink_Ql.setLinkActive_Ql(String.valueOf(getLinkData[i][4]));
				list_Ql.add(beanLink_Ql);
			}// END of loop x

			// write XML
			XMLwrite(poolDir, 48, "quickLink");
			quickBean.setList_QlLink(list_Ql);
			return flagValue;
		} catch (Exception e) {
			logger.error("Exception Caught in Saving Quick Links : " + e);
			return 0;
		}
	}

	/**
	 * Saving Payroll Settings
	 * 
	 * @param setting
	 * @return boolean inserts data
	 */
	public boolean saveAppSetting(SuperSettings setting) {
		// TODO Auto-generated method stub

		Object[][] addPass = new Object[1][22];
		System.out.println("-------------" + setting.getBrnFlag());
		if (setting.getBrnFlag().equals("true")) {
			addPass[0][0] = "Y";
		}// ENd if
		else
			addPass[0][0] = "N";

		if (setting.getDeptFlag().equals("true")) {
			addPass[0][1] = "Y";
		} // End if
		else
			addPass[0][1] = "N";

		if (setting.getPaybillFlag().equals("true")) {
			addPass[0][2] = "Y";
		}// END if
		else
			addPass[0][2] = "N";

		if (setting.getEmptypeFlag().equals("true")) {
			addPass[0][3] = "Y";
		}// END if
		else
			addPass[0][3] = "N";

		if (setting.getDivFlag().equals("true")) {
			addPass[0][4] = "Y";
		} // END if
		else
			addPass[0][4] = "N";

		addPass[0][5] = "";

		if (setting.getRecordsPerPage().equals("")) {
			addPass[0][6] = "25";
		}// ENd if
		else
			addPass[0][6] = setting.getRecordsPerPage();
		
		logger.info("Join Flag Value =========================== "+setting.getJoinPay());
		logger.info("Sep Flag Value =========================== "+setting.getSepPay());
		
		addPass[0][7] = setting.getJoinPay();
		addPass[0][8] = setting.getSepPay();
		
		if (setting.getRecoveryFlag().equals("true")) {
			addPass[0][9] = "Y";
			addPass[0][21] =setting.getRecDebitCode();
		}// END if
		else{
			addPass[0][9] = "N";
			addPass[0][21] ="";
		}
		
		/*if (setting.getProfessionalTaxFlag().equals("true")) {
			addPass[0][10] = "Y";
		}// END if
		else
			addPass[0][10] = "N";*/
		
		if(setting.getSuspWorkFlag().equals("true")){
			addPass[0][10]="Y";
		}else{
			addPass[0][10] = "N";
		}
		
		if(setting.getTaxWorkFlag().equals("true")){
			addPass[0][11]="Y";
		}else{
			addPass[0][11] = "N";
		}
		
		/* EPF */
		if(setting.getSEPFflag().equalsIgnoreCase("true"))
			addPass[0][12]="Y";
		else
			addPass[0][12]="N";
		
		/* GPF */
		if(setting.getSGPFflag().equalsIgnoreCase("true"))
			addPass[0][13]="Y";
		else
			addPass[0][13]="N";
		
		/* VPF */
		if(setting.getSVPFflag().equalsIgnoreCase("true"))
			addPass[0][14]="Y";
		else
			addPass[0][14]="N";
		
		/* PFT */
		if(setting.getSPFTflag().equalsIgnoreCase("true"))
			addPass[0][15]="Y";
		else
			addPass[0][15]="N";
		
		logger.info("Extra work........"+setting.getExtraWorkFlag());
		if (setting.getExtraWorkFlag().equals("true")) {
			addPass[0][16] = "Y";
		}// END if
		else
			addPass[0][16] = "N";
		
		addPass[0][17] = setting.getCreditRound();
		addPass[0][18] = setting.getTotalCreditRound();
		addPass[0][19] = setting.getTotalDebitRound();
		addPass[0][20] = setting.getNetPayRound();
		
		getSqlModel().singleExecute(getQuery(50));
		return getSqlModel().singleExecute(getQuery(49), addPass);
	}

	/**
	 * Set fields under Payroll Settings After Save
	 * 
	 * @param setting
	 */
	public void showAppSetting(SuperSettings setting) {
		// TODO Auto-generated method stub
		String Query = "SELECT CONF_BRN_FLAG,CONF_DEPT_FLAG,CONF_PAYBILL_FLAG,CONF_EMPTYPE_FLAG,CONF_DIVISION_FLAG, "
				+ " CONF_RECORDS_PER_PAGE ,CONF_JOINDAYS_FLAG,CONF_LEAVEDAYS_FLAG,CONF_RECOVERY_FLAG,'',CONF_SUSP_FLAG,CONF_TAX_WORKFLOW_FLAG,CONF_EPF, CONF_GPF, CONF_VPF, CONF_PFTRUST, EXTRAWORK_FLAG, "
				+" CONF_CREDIT_ROUND,CONF_TOTCREDIT_OPTION,CONF_TOTDEBIT_ROUND,CONF_NETPAY_ROUND,DEBIT_NAME,CONF_RECOVERY_DEBIT "
				+" FROM HRMS_SALARY_CONF "
				+" LEFT JOIN HRMS_DEBIT_HEAD ON DEBIT_CODE =CONF_RECOVERY_DEBIT";
		

		Object[][] Data = getSqlModel().getSingleResult(Query);

		if (Data != null && Data.length > 0) {
			if (Data[0][0].equals("Y")) {
				setting.setBrnFlag("true");
			} // END nested if
			else
				setting.setBrnFlag("false");

			if (Data[0][1].equals("Y")) {
				setting.setDeptFlag("true");
			}// END nested if
			else
				setting.setDeptFlag("false");

			if (Data[0][2].equals("Y")) {
				setting.setPaybillFlag("true");
			} // END nested if
			else
				setting.setPaybillFlag("false");

			if (Data[0][3].equals("Y")) {
				setting.setEmptypeFlag("true");

			} // END nested if
			else
				setting.setEmptypeFlag("false");

			if (Data[0][4].equals("Y")) {
				setting.setDivFlag("true");

			} // END nested if
			else
				setting.setDivFlag("false");

			setting.setRecordsPerPage(String.valueOf(Data[0][5]));

			if (Data[0][6].equals("Y")) {
				setting.setSalDurJoinFlag("true");
			} else
				setting.setSalDurJoinFlag("false");

			if (Data[0][7].equals("Y")) {
				setting.setSalDurLeaveFlag("true");
			} else
				setting.setSalDurLeaveFlag("false");
			
			
			if(Data[0][8].equals("Y")){
				setting.setRecoveryFlag("true");
				
			}else
				setting.setRecoveryFlag("false");
			
			
			/*if(Data[0][9].equals("Y")){
				setting.setProfessionalTaxFlag("true");
				
			}else
				setting.setProfessionalTaxFlag("false");*/
			
			if(Data[0][10].equals("Y")){
				setting.setSuspWorkFlag("true");
				
			}else
				setting.setSuspWorkFlag("false");
			
			if(Data[0][11].equals("Y")){
				setting.setTaxWorkFlag("true");
				
			}else
				setting.setTaxWorkFlag("false");
			
			if(Data[0][12].equals("Y"))
				setting.setSEPFflag("true");
			else
				setting.setSEPFflag("false");
			
			if(Data[0][13].equals("Y"))
				setting.setSGPFflag("true");
			else
				setting.setSGPFflag("false");
			
			if(Data[0][14].equals("Y"))
				setting.setSVPFflag("true");
			else
				setting.setSVPFflag("false");
			
			
			if(Data[0][15].equals("Y"))
				setting.setSPFTflag("true");
			else
				setting.setSPFTflag("false");
			
			if(Data[0][16].equals("Y"))
				setting.setExtraWorkFlag("true");
			else
				setting.setExtraWorkFlag("false");

			setting.setCreditRound(String.valueOf(Data[0][17]));
			setting.setTotalCreditRound(String.valueOf(Data[0][18]));
			setting.setTotalDebitRound(String.valueOf(Data[0][19]));
			setting.setNetPayRound(String.valueOf(Data[0][20]));
			setting.setRecDebitName(Utility.checkNull(String.valueOf(Data[0][21])));
			setting.setRecDebitCode(Utility.checkNull(String.valueOf(Data[0][22])));
			
		}// END if "length"
		else
		{
			setting.setBrnFlag("false");
			setting.setDeptFlag("false");
			setting.setDivFlag("true");
			setting.setEmptypeFlag("false");
			setting.setPaybillFlag("false");
			setting.setRecordsPerPage("25");
			setting.setSalDurJoinFlag("false");
			setting.setSepPay("false");
			setting.setRecoveryFlag("false");
			setting.setProfessionalTaxFlag("false");
			setting.setSuspWorkFlag("false");
			
			setting.setSEPFflag("false");
			setting.setSGPFflag("false");
			setting.setSPFTflag("false");
			setting.setSVPFflag("false");
			
			setting.setExtraWorkFlag("false");
			setting.setCreditRound("0");
			setting.setTotalCreditRound("0");
			setting.setTotalDebitRound("0");
			setting.setNetPayRound("0");
			setting.setRecDebitName("");
			setting.setRecDebitCode("");
		}
	}

	/**
	 * Write XML for Quick LInks and General Settings
	 * 
	 * @param poolDir
	 * @param queryCode
	 * @param type
	 */
	public void XMLwrite(String poolDir, int queryCode, String type) {
		Object[][] xmlData = getSqlModel().getSingleResult(getQuery(queryCode));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;
		try {
			if (type.equals("quickLink"))
				// write XML for quick link
				new XMLReaderWriter().write(buildDocument("HRHOME", "QUICK",
						xmlData), poolDir);
			else if (type.equals("general"))
				// write XML for general settings
				new XMLReaderWriter().write(buildDocument("HRHOME", "GENERAL",
						xmlData), poolDir);

		} catch (Exception e) {
			logger.error("Exception caught in writing XML : QL & GS : " + e);
		}

	}

	/**
	 * Editing Quick Links under the List
	 * 
	 * @param bean
	 */
	public void editQl(SuperSettings bean) {
		try {
			String query = "SELECT QUICK_ADMIN_LINKNAME,NVL(QUICK_ADMIN_LINK,' '),QUICK_ADMIN_STATUS,QUICK_SUPER_FLAG FROM HRMS_SETTINGS_QUICKLINK_ADMIN WHERE QUICK_ADMIN_CODE = "
					+ bean.getHiddenCode_Ql();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameQl(String.valueOf(resultData[0][0]));
			bean.setCheckQuick(String.valueOf(resultData[0][3]));
			String status = (String.valueOf(resultData[0][2]));
			if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);// .substring(7,String.valueOf(resultData[0][1]).length());
				bean.setHiddenlinkPathQl(linkName);
			}// END if
			String view = bean.getCheckQuick();
			System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiii" + view);
			if (view.equals("Y"))
				bean.setCheckQuick("true");
			else
				bean.setCheckQuick("false");
		} catch (Exception e) {
			logger.error("Exception Caught In Editing Quick Links : " + e);
		}

	}

	/**
	 * Deleting List of Quick Links
	 * 
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteQl(SuperSettings bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Ql();
			boolean result = getSqlModel().singleExecute(getQuery(34), delCode);
			// writing XML
			XMLwrite(poolDir, 48, "quickLink");
			return result;
		} catch (Exception e) {
			logger.error("Exception Caught in De;leting Quick Links : " + e);
			return false;
		}
	}

	/**
	 * Save General Settings Write XML
	 * 
	 * @param bean
	 * @param status
	 * @param poolDir
	 * @return integer value
	 */
	public int saveGeneral(SuperSettings bean, String status, String poolDir) {
		try {
			Object[][] addLink = new Object[1][5];
			int flagValue = 0;
			if (status.equals("save")) {
				addLink[0][0] = bean.getLinkNameGs();
				if (bean.getUploadGs().equals("")) {
					// addLink[0][1] = "http://"+bean.getLinkGs();
					addLink[0][1] = bean.getLinkGs();
					addLink[0][2] = "L";
				} // END nested if
				else {
					addLink[0][1] = "../pages/upload/" + bean.getUploadGs();
					addLink[0][2] = "D";
				}// END else

				if (bean.getCheckGeneral().equals("true"))
					addLink[0][3] = "Y";
				else
					addLink[0][3] = "N";
				addLink[0][4] = bean.getUserEmpId();

				if (bean.getHiddenCode_Gs().equals("")) {
					getSqlModel().singleExecute(getQuery(40), addLink);
					flagValue = 1;
				} else {// y
					Object[][] hiddenCode = new Object[1][5];
					hiddenCode[0][0] = bean.getLinkNameGs();
					if (String.valueOf(addLink[0][2]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ bean.getUploadGs();
					else
						hiddenCode[0][1] = bean.getLinkGs();

					// hiddenCode[0][1] = "http://"+bean.getLinkGs();

					hiddenCode[0][2] = String.valueOf(addLink[0][2]);
					hiddenCode[0][3] = String.valueOf(addLink[0][3]);
					hiddenCode[0][4] = "" + bean.getHiddenCode_Gs();
					getSqlModel().singleExecute(getQuery(42), hiddenCode);
					flagValue = 2;
				}// END else "y"

			}// END if "save"

			Object[][] getLinkData = getSqlModel()
					.getSingleResult(getQuery(41));
			ArrayList<Object> list_gs = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {// loop x
				SuperSettings beanLink_Gs = new SuperSettings();
				beanLink_Gs.setLinkCode_Gs(String.valueOf(getLinkData[i][0]));
				beanLink_Gs.setLinkName_Gs(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				// beanLink_Gs.setType_Gs(String.valueOf(getLinkData[i][3]));
				if (String.valueOf(getLinkData[i][3]).equals("D"))
					beanLink_Gs.setLinkFile_Gs(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				else
					beanLink_Gs.setLinkFile_Gs(link);
				beanLink_Gs.setLinkActive_Gs(String.valueOf(getLinkData[i][4]));
				list_gs.add(beanLink_Gs);
			}// END of loop x

			// XML write
			XMLwrite(poolDir, 45, "general");
			bean.setList_gsLink(list_gs);
			return flagValue;

		} catch (Exception e) {
			logger.error("Exception Caught in Saving General Settings : " + e);
			return 0;
		}
	}

	/**
	 * Edit List under General Settings
	 * 
	 * @param bean
	 */
	public void editGs(SuperSettings bean) {
		try {
			String query = "SELECT GENERAL_LINK_NAME, NVL(GENERAL_LINK_PATH,' '), GENERAL_STATUS,GENERAL_SUPERFLAG FROM HRMS_SETTINGS_GENERAL WHERE GENERAL_ID = "
					+ bean.getHiddenCode_Gs();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setLinkNameGs(String.valueOf(resultData[0][0]));
			bean.setCheckGeneral("true");
			if (String.valueOf(resultData[0][3]).equals("N"))
				bean.setCheckGeneral("false");
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
			logger.error("Exception caught i nEditing General Settings " + e);
		}
	}

	/**
	 * Deleting list under General Settings
	 * 
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteGs(SuperSettings bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_Gs();
			logger.info("delcode" + String.valueOf(delCode[0][0]));
			boolean result = getSqlModel().singleExecute(getQuery(47), delCode);
			logger.info("FLAG  " + result);
			bean.setHiddenCode_Gs("");
			XMLwrite(poolDir, 45, "general");
			return result;
		} catch (Exception e) {
			logger
					.error("Exception Caught in Deleting General Settings : "
							+ e);
			return false;
		}
	}

}
