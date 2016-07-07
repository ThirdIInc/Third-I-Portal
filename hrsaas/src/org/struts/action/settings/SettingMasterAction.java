package org.struts.action.settings;

import java.util.ArrayList;

import org.paradyne.bean.settings.SettingMaster;
import org.paradyne.model.settings.SettingMasterModel;
import org.struts.action.common.LoginAction;
import org.struts.lib.ParaActionSupport;

/**
 *@purpose : This form is used to Save or Edit HomePage Dashlet Information
 * 			  HomePage Setting Link 
 * @Modified By AA1711 - TO view that Records According to Applicable 
 * 						Division Wise on Dashlet 
 * @Date 22-Jan-2013
 */
public class SettingMasterAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoginAction.class);
	SettingMaster setting;
	String poolDir = "";
	String fileName = "";

	/**
	 * @return String
	 */
	public String getPoolDir() {
		return poolDir;
	}

	/**
	 * @param poolDir
	 */
	public void setPoolDir(String poolDir) {
		this.poolDir = poolDir;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		setting = new SettingMaster();
		poolDir = String.valueOf(session.getAttribute("session_pool"));
		setting.setMenuCode(416);
	}

	public Object getModel() { 
		return setting;
	}

	/**
	 * @return SettingMaster Bean
	 */
	public SettingMaster getSetting() {
		return setting;
	}

	/**
	 * @param setting
	 */
	public void setSetting(SettingMaster setting) {
		this.setting = setting;
	}

	/** (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		setting.setDivFlag_CR(true);
		setting.setHiddenDivId("CR");
		showOnlyInfo();
		model.terminate();
	}

	/**Method Name: addPoll()
	 * @purpose Add a poll and adding options for each Poll (XML is written ONLY on SAVE
	 * @return String
	 */
	public String addPoll() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		if (setting.getPollCode().equals("")) {
			// Adding code and question
			boolean result = model.addPoll(setting);
			if (result)
				addActionMessage(getMessage("save"));
			else
				addActionMessage(getMessage("save.error"));

		}// end of if
		else {
			int flag = 1;
			boolean res = false;
			boolean result = false;
			if (setting.getHiddenOptionCode().equals(""))
				// Adding option
				res = model.addOption(setting);
			else {
				// Updating Option
				result = model.updateOption(setting);
				flag = 2;
			}// end of else
			if (res == true && flag == 1)
				addActionMessage("Option Added Successfully !");
			else if (result == true && flag == 2)
				addActionMessage("Option Modified Successfully");
			else
				addActionMessage("Option cant be Added.");
		}// end of else
		setting.setHiddenOptionCode("");
		setting.setOption("");
		setting.setOptionColor("");
		// Set fields after saving
		model.showRecord(setting);
		model.terminate();
		return "poll";

	}

	/**Method Name: deletePoll()
	 * @purpose To delete a Poll Deleting Poll Question as well as Options
	 * @return String
	 */
	public String deletePoll() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// Path for writing into XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\poll\\poll.xml";
		/**To Delete Record from HRMS_POLL_DTL, HRMS_POLL_HDR, HRMS_POLL_EMP*/
		model.deletePoll(setting, fileName);
		showOnlyInfo();
		model.terminate();
		resetPoll();
		setting.setHiddenOptionCode("");
		addActionMessage("Poll Deleted Successfully");
		return "poll";
	}

	/**Method Name: resetPoll()
	 * @purpose Reset all fields of Poll JSP
	 * @return String
	 */
	public String resetPoll() {
		setting.setPollCode("");
		setting.setOption("");
		setting.setOptionCode("");
		setting.setList(new ArrayList<Object>());
		setting.setQuestion("");
		setting.setOptionColor("");
		setting.setHiddenCode_poll("");
		setting.setExpiry("");
		setting.setPollDivCode("");
		setting.setPollDivName("");
		return "poll";
	}

	
	/**Method Name: f9actionPoll()
	 * @purpose Search Poll
	 * @return f9page
	 * @throws Exception
	 */
	public String f9actionPoll() throws Exception {
		String query = " SELECT POLL_CODE, "
					  + " POLL_QUESTION ,NVL(TO_CHAR(EXPIRY_DATE,'DD-MM-YYYY'),' '),"
					  + " TO_CHAR(POLL_DATE,'DD-MM-YYYY'),NVL(POLL_DIVISION,'') "
					  + " FROM HRMS_POLL_HDR " 
					  + " ORDER BY POLL_CODE "; 
		String[] headers = { "Poll Code", getMessage("question"),
				getMessage("poll.expiry"), "Date" };
		String[] headerWidth = { "10", "60", "15", "15" };
		String[] fieldNames = {"setting.pollCode", "setting.question",
				"setting.expiry","pollDate","pollDivCode"};
		int[] columnIndex = { 0, 1, 2, 3, 4};
		String submitFlag ="true";
		String submitToMethod = "SettingMaster_showRecord.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**Method Name:showRecord()
	 * @purpose Set fields after searching any Poll or Adding
	 * @return String
	 */
	public String showRecord() {

		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		setting.setPollDivName("");
		model.showDivision(setting);		
		model.showRecord(setting);
		model.terminate();
		return "poll";
	}

	/**Method Name: edit()
	 * @purpose To edit the options of POll
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.getRecord(setting);
		model.showRecord(setting);
		model.terminate();
		return "poll";
	}

	/**Method Name: delete()
	 * @purpose To delete the options of Poll
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path for writing into XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\poll\\poll.xml";
		model.delRecord(setting, fileName);
		setting.setOptionCode("");
		setting.setOptionColor("");
		setting.setOption("");
		setting.setHiddenOptionCode("");
		model.showRecord(setting);
		addActionMessage("Option Deleted Successfully");
		model.terminate();
		return "poll";
	}

	/**Method Name: savePoll()
	 * @purpose Saving Poll
	 * @return String
	 */
	public String savePoll() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// Writing into XML on Save
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\poll\\poll.xml";	
		boolean flag = model.saveRecord(setting, fileName);
		if (flag)
			addActionMessage("Poll Modified Successfully");
		else
			addActionMessage("Duplicate Entry Found. Poll Cannot be Added.");
		resetPoll();
		model.terminate();
		return "poll";
	}

	/**Method Name: saveHrComm()
	 * @purpose : Save Company Information List 
	 * @return String
	 */
	public String saveHrComm() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// call for model
		int value = model.saveHrComm(setting, "save", poolDir);
		setting.setCheckFlag_hr("true");
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		setting.setCheckHr("");
		model.terminate();
		return "success";
	}

	/**Method Name:
	 * @purpose : Reset Corporate Info page
	 * @return String
	 */
	public String reset_cs() {
		try {
			setting.setUploadCs("");
			setting.setLinkNameCs("");
			setting.setLinkCs("");
			setting.setCheckFlag_cs("true");
			setting.setCheckCorp("false");
			setting.setApplDivisionCode("");
			setting.setApplDivisionName("");
			showCorporate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "corporate";

	}
	
	/**
	 * Method Name: resetTips().
	 * @Purpose : Reset Tips Info Data.
	 * @return String
	 */
	public String resetTips() {
		try {
			setting.setUploadTs("");
			setting.setTipsName("");
			setting.setLinkTs("");
			setting.setHiddenCode_TS("");
			setting.setCheckFlag_TS("true");
			setting.setActiveTip("false");
			setting.setDivisionCode("");
			setting.setDivisionName("");
			showTipsLink();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tipsLink";

	}
	
	

	/**Method Name: reset_cms()
	 * @purpose Reset corporate communication page
	 * @return String
	 */
	public String reset_cms() {

		try {
			setting.setUploadCms("");
			setting.setLinkNameCms("");
			setting.setLinkCms("");
			setting.setCheckFlag_cms("true");
			setting.setCheckComp("false");
			setting.setCorpDivCode("");
			setting.setCorpDivName("");
			showCompanyComm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "compComm";
	}

	/**Method Name: reset_ks()
	 * @purpose Resets knowledge zInformation page
	 * @return String
	 */
	public String reset_ks() {
		try {
			SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			setting.setUploadKs("");
			setting.setLinkNameKs("");
			setting.setLinkKs("");
			setting.setCheckFlag_ks("true");
			setting.setCheckKnow("false");
			setting.setKnowDivCode("");
			setting.setKnowDivName("");
			setting.setKnowCatNmSelect("");
			showKnowledge();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "knowledge";
	}

	/**Method Name: saveLink()
	 * @purpose Save Quick Links
	 * @return String
	 */
	public String saveLink() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		String[] chkStatus = request.getParameterValues("chkQlink");
		String[] linkCode = request.getParameterValues("linkCode_Ql");
		if (chkStatus != null) {		
			fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\quick_links\\quick.xml";// path to write XML
			int value = model.saveLink(setting, fileName, chkStatus, linkCode);
			if (value == 0)
				addActionMessage("Record can't be Saved!");
			else
				addActionMessage("Record Saved Successfully!");
			model.showQlinkInfo(setting);
			model.terminate();
		} // END if checkStatus
		else
			addActionMessage("No link Available to save.");
		return "quickLink";

	}

	/**Method Name: saveEmail()
	 * @purpose Save Emails on HrWork
	 * @return String
	 */
	public String saveEmail() {
		try {
			SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			String[] chkStatus = request.getParameterValues("chkemail");
			String[] linkCode = request.getParameterValues("linkCode_EM");
			// path to write XML
			fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\email_info\\email_info.xml";
			int value = 0;
			if (chkStatus != null) {				
				value = model.saveEmail(setting, fileName, chkStatus, linkCode);
				if (value == 0)
					addActionMessage("Record can't be Saved!");
				else
					addActionMessage("Record Saved Successfully!");
			} // END if checkStatus
			else
				addActionMessage("No Link Avialable to Save");
			model.terminate();
			return "eMail";
		} catch (Exception e) {
			logger.error("Exception Caught in  Save Email: " + e);
			addActionMessage("Error while saving email");
			return "eMail";
		}
	}

	/**Method Name: saveBbs()
	 * @purpose Save hrWork Communication List 
	 * @return String
	 */
	public String saveBbs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\bulletin\\bulletin.xml";
		int value = model.saveBbs(setting, "save", fileName);
		setting.setUploadBbs("");
		setting.setLinkNameBbs("");
		setting.setLinkCs("");
		setting.setHiddenCode_Bbs("");
		setting.setCheckFlag_Bbs("true");
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		setting.setCheckBulletin("");
		model.terminate();
		return "success";
	}

	/**Method Name: saveCorporate()
	 * @purpose : Save Corpoarte Information List
	 * @return String
	 */
	public String saveCorporate() {
		try {
		 	SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			// path to write XML
			fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\corp_info\\corp.xml";
			int value = model.saveCorporate(setting, "save", fileName);
			reset_cs();
			setting.setCheckFlag_cs("true");
			if (value == 0)
				addActionMessage("Duplicate Entry Found. Record cant be Added!");
			else if (value == 1)
				addActionMessage("Record Saved Successfully");
			else
				addActionMessage("Record Modified Successfully");
			setting.setCheckCorp("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "corporate";
	}

	/**
	 * Method : saveTips().
	 * @Purpose : Save Tips Page Information.
	 * @return String.
	 */
	public String saveTips() {
		try {
		 	SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			// path to write XML
			fileName = getText("data_path") + "\\datafiles\\" + poolDir+ "\\xml\\tips_info\\tips.xml";
			int value = model.saveTips(setting, "save", fileName);
			resetTips();
			setting.setCheckFlag_TS("true");
			if (value == 0)
				addActionMessage("Duplicate Entry Found. Record cant be Added!");
			else if (value == 1)
				addActionMessage("Record Saved Successfully");
			else
				addActionMessage("Record Modified Successfully");
			setting.setActiveTip("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tipsLink";
	}

	
	/**Method Name: saveKnowledge()
	 * @purpose Save knowledge Information List
	 * @return String
	 */
	public String saveKnowledge() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\knowledge\\know.xml";
		int value = model.saveKnowledge(setting, "save", fileName);
		reset_ks();
		setting.setCheckFlag_ks("true");
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		setting.setCheckKnow("");
		model.terminate();
		return "knowledge";
	}

	/**Method Name: saveGeneral()
	 * @purpose Save general Information List
	 * @return String
	 */
	public String saveGeneral() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		String[] chkStatus = request.getParameterValues("chkGlink");
		String[] linkCode = request.getParameterValues("linkCode_Gs");
		if (chkStatus != null) {			
			// path to write XML
			fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\general\\general.xml";
			int value = model.saveGeneral(setting, fileName, chkStatus,
					linkCode);
			if (value == 0)
				addActionMessage("Record can't be Saved!");
			else
				addActionMessage("Record Saved Successfully!");
			model.showGeneralInfo(setting);
		} else
			addActionMessage("No link available to save");
		return "genInfo";
	}

	/**Method Name: saveCompanyComm()
	 * @purpose Save Company Communication List
	 * @return String
	 */
	public String saveCompanyComm() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\corp_comm\\cmp.xml";
		int value = model.saveCompanyComm(setting, "save", fileName);
		reset_cms();
		setting.setCheckFlag_cms("true");
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		setting.setCheckComp("");
		model.terminate();
		return "compComm";
	}

	/**Method Name: 
	 * @purpose Edit Corporate Information List
	 * @return String
	 */
	public String editHr() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.editHr(setting);

		model.terminate();
		return "success";
	}

	/**Method Name: editBbs()
	 * @purpose Edit HrWorK Communication List
	 * @return String
	 */
	public String editBbs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.editBbs(setting);
		model.terminate();
		return "success";
	}

	/**Method Name: editCs()
	 * @purpose Edit Corporate List
	 * @return String
	 */
	public String editCs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		reset_cs();
		model.editCs(setting);
		model.saveCorporate(setting, "show", poolDir);
		setting.setCheckFlag_cms("true");
		setting.setCheckFlag_ks("true");
		model.terminate();
		return "corporate";
	}

	/**
	 * Method Name: editTs().
	 * @Purpose : Edit Tips List Data.
	 * @return String.
	 */
	public String editTs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		try {
			model.editTs(setting);
			model.saveTips(setting, "show", poolDir);
		} catch (RuntimeException e) {			
			e.printStackTrace();
		}
		model.terminate();
		return "tipsLink";
	}
	
	/**Method Name: editKs()
	 * @purpose Edit Knowledge Information List
	 * @return String
	 */
	public String editKs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		reset_ks();
		model.editKs(setting);
		setting.setCheckFlag_cms("true");
		setting.setCheckFlag_cs("true");
		model.saveKnowledge(setting, "show", poolDir);
		model.terminate();
		return "knowledge";
	}

	/**Method Name: editGs()
	 * @purpose : Edit General Information List
	 * @return String
	 */
	public String editGs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.editGs(setting);
		model.terminate();
		return "genInfo";
	}

	/**Method Name: editCms()
	 * @purpose Edit Company Communication List
	 * @return String
	 */
	public String editCms() {
		try {
			SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			reset_cms();
			model.editCms(setting);
			setting.setCheckFlag_cs("true");
			setting.setCheckFlag_ks("true");
			model.saveCompanyComm(setting, "show", poolDir);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "compComm";
	}

	/**Method Name: deleteHr()
	 * @purpose Delete HrWork Information List 
	 * @return String
	 */
	public String deleteHr() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteHr(setting, poolDir);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			setting.setUploadHr("");
			setting.setLinkNameHr("");
			setting.setLinkHr("");
			setting.setHiddenCode_Hr("");
		} // END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// ENd else
		showOnlyInfo();
		model.terminate();
		return "success";
	}

	/**Method Name: deleteBbs()
	 * @purpose Delete HrWork Communication List
	 * @return String
	 */
	public String deleteBbs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\bulletin\\bulletin.xml";
		boolean result = model.deleteBbs(setting, fileName);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			setting.setUploadBbs("");
			setting.setLinkNameBbs("");
			setting.setLinkBbs("");
			setting.setHiddenCode_Bbs("");
		}// END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// END else
		showOnlyInfo();
		model.terminate();
		return "success";
	}

	/**Method Name: deleteCs()
	 * @purpose Delete Corporate Information List
	 * @return String
	 */
	public String deleteCs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\corp_info\\corp.xml";
		boolean result = model.deleteCs(setting, fileName);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			setting.setUploadCs("");
			setting.setLinkNameCs("");
			setting.setLinkCs("");
			setting.setHiddenCode_Cs("");
		} // END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// END else
		showCorporate();
		model.terminate();
		return "corporate";
	}

	/**
	 * Method Name:deleteTs().
	 * @Purpose : Delete Particular Record.
	 * @return String.
	 */
	public String deleteTs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		try {
			fileName = getText("data_path") + "\\datafiles\\" + poolDir+ "\\xml\\tips_info\\tips.xml";
			boolean result = model.deleteTs(setting, fileName);
			if (result) {
				addActionMessage("Record Deleted Successfully!");
				setting.setUploadTs("");
				setting.setTipsName("");
				setting.setLinkTs("");
				setting.setHiddenCode_TS("");
			} // END if
			else {
				addActionMessage("Record Can't be Deleted!");
			}// END else
			showTipsLink();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		model.terminate();
		return "tipsLink";
	}

	
	
	/**Method Name: deleteKs()
	 * @purpose Delete Knowledge Information List
	 * @return String
	 */
	public String deleteKs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\knowledge\\know.xml";
		boolean result = model.deleteKs(setting, fileName);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			setting.setUploadKs("");
			setting.setLinkNameKs("");
			setting.setLinkKs("");
			setting.setHiddenCode_Ks("");
		} // END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// END else
		showKnowledge();
		model.terminate();
		return "knowledge";
	}

	/**Method Name:deleteQl()
	 * @purpose Delete Quick Links List
	 * @return String
	 */
	public String deleteQl() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteQl(setting, poolDir);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			setting.setUploadQl("");
			setting.setLinkNameQl("");
			setting.setLinkQl("");
			setting.setHiddenCode_Ql("");
		} // END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// END else
		showOnlyInfo();
		model.terminate();
		return "quickLink";
	}

	/**Method Name : deleteGs()
	 * @purpose Delete General Information List
	 * @return String
	 */
	public String deleteGs() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteGs(setting, poolDir);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			setting.setUploadGs("");
			setting.setLinkNameGs("");
			setting.setLinkGs("");
			setting.setHiddenCode_Gs("");
		}// END if
		else
			addActionMessage("Record Can't be Deleted!");
		showOnlyInfo();
		model.terminate();
		return "genInfo";
	}

	/**Method Name: deleteCms()
	 * @purpose Delete Corporate Communication List 
	 * @return String
	 */
	public String deleteCms() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\corp_comm\\cmp.xml";
		boolean result = model.deleteCms(setting, fileName);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			setting.setUploadCms("");
			setting.setLinkNameCms("");
			setting.setLinkCms("");
			setting.setHiddenCode_Cms("");
		} // END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// END else
		showCompanyComm();
		model.terminate();
		return "compComm";
	}

	/**Method Name: resetDivFlag()
	 * @purpose To Reset Flags
	 */
	public void resetDivFlag() {
		setting.setDivFlag_CR(false);
		setting.setDivFlag_KN(false);
		setting.setDivFlag_GE(false);
		setting.setDivFlag_CM(false);
		setting.setDivFlag_PO(false);
		setting.setDivFlag_TH(false);
		setting.setDivFlag_QL(false);
		setting.setDivFlag_ML(false);
		setting.setDivFlag_EM(false);
		setting.setDivFlag_PS(false);
	}

	/**Method Name: showOnlyInfo()
	 * @purpose Displaying the details after saving All Homepage Settings function
	 * @return String
	 */
	public String showOnlyInfo() {
		try {
			SettingMasterModel model = new SettingMasterModel();
			resetPoll();
			// resetsysMail();
			resetDivFlag();
			model.initiate(context, session);
			String divId = setting.getHiddenDivId();
			if (divId.equals("CR")) {
				model.saveCorporate(setting, "show", poolDir);
				setting.setDivFlag_CR(true);
			} // END "CR"
			else if (divId.equals("KN")) {
				model.saveKnowledge(setting, "show", poolDir);
				setting.setDivFlag_KN(true);
			} // END "KN"
			else if (divId.equals("CM")) {
				model.saveCompanyComm(setting, "show", poolDir);
				setting.setDivFlag_CM(true);
			} // END "CM"
			else if (divId.equals("TH")) {
				model.addThougth(setting, "show", poolDir);
				setting.setDivFlag_TH(true);
			} // END "TH"
			else if (divId.equals("QL")) {
				model.showQlinkInfo(setting);
				setting.setDivFlag_QL(true);
			} // END "QL"
			else if (divId.equals("GE")) {
				model.showGeneralInfo(setting);
				setting.setDivFlag_GE(true);
			} // END "GE"
			else if (divId.equals("EM")) {
				model.showEmailInfo(setting);
				setting.setDivFlag_EM(true);
			} // END "EM"
			else if (divId.equals("ML")) {
				// model.saveMailBoxConf(setting);
				// model.saveMailBoxConfOut(setting);
				// model.showSysMail(setting);
				setting.setDivFlag_ML(true);
			} // END "ML"
			else if (divId.equals("PO")) {
				setting.setDivFlag_PO(true);
			}// END "PS"
			else if (divId.equals("PS")) {
				showPassSetting();
				setting.setDivFlag_PS(true);
			}
			else if (divId.equals("TS")) {
				model.saveTips(setting, "show", poolDir);
				setting.setDivFlag_TS(true);
			}
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception Caught in Show info Method : " + e);
			return SUCCESS;
		}
	}

	/**Method Name: showCorporate()
	 * @purpose : Show corp info after saving
	 * @return String
	 */
	public String showCorporate() {
		try {
			SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			model.saveCorporate(setting, "show", poolDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "corporate";
	}

	/**
	 * Method Name: showTipsLink().
	 * @Purpose : Show Tips info after saving
	 * @return String.
	 */
	public String showTipsLink() {
		try {
			SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			model.saveTips(setting, "show", poolDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tipsLink";
	}
	
	/**Method Name: showKnowledge()
	 * @purpose : Show knowledge info after saving
	 * @return String
	 */
	public String showKnowledge() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.saveKnowledge(setting, "show", poolDir);
		model.setCategory(setting);
		return "knowledge";
	}

	/**Method Name: showGeneralInfo()
	 * @purpose Show General Information
	 * @return String
	 */
	public String showGeneralInfo() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.showGeneralInfo(setting);
		return "genInfo";
	}

	/**Method Name: showPoll()
	 * @purpose Show POLL after saving
	 * @return String
	 */
	public String showPoll() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.showGeneralInfo(setting);
		return "poll";
	}

	/**Method Name: showCompanyComm()
	 * @purpose Show Company communication after saving
	 * @return String
	 */
	public String showCompanyComm() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.saveCompanyComm(setting, "show", poolDir);
		return "compComm";
	}

	/**Method Name: showThought()
	 * @purpose Show thought details after saving
	 * @return String
	 */
	public String showThought() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.addThougth(setting, "show", poolDir);
		return "thought";
	}

	/*
	 * public String showMail() { SettingMasterModel model = new
	 * SettingMasterModel(); model.initiate(context, session);
	 * model.saveMailBoxConf(setting); model.saveMailBoxConfOut(setting);
	 * model.showSysMail(setting); logger.info("Inside CR"); return "mail"; }
	 */
	/**Method Name: showQuickLink()
	 * @purpose Show Quick Links after saving
	 * @return String
	 */
	public String showQuickLink() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.showQlinkInfo(setting);
		return "quickLink";
	}

	
	/**Method Name: showEmail()
	 * @purpose Show Emails after saving
	 * @return String
	 */
	public String showEmail() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.showEmailInfo(setting);
		return "eMail";
	}

	/**Method Name:  editQl()
	 *@purpose  Edit Quick Link List 
	 * @return String
	 */
	public String editQl() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.editQl(setting);
		model.terminate();
		return "quickLink";
	}

	/**Method Name: addThougth()
	 * @purpose Add new Thought
	 * @return String
	 */
	public String addThougth() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\thought\\thought.xml";
		int res = model.addThougth(setting, "save", fileName);
		// model.xml_Thought(poolDir);
		if (res == 0)
			addActionMessage("Duplicate Entry Found");
		else if (res == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		setting.setThougth("");
		setting.setHiddenCode_th("");
		model.terminate();
		return "thought";
	}

	/**Method Name: editThougth()
	 *@purpose  Edit Thought
	 * @return String
	 */
	public String editThougth() {
		SettingMasterModel model = new SettingMasterModel();
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\thought\\thought.xml";
		model.initiate(context, session);
		model.editThougth(setting);
		model.terminate();
		model.addThougth(setting, "show", fileName);
		return "thought";
	}

	/**Method Name: 
	 * @purpose Delete Thought
	 * @return String
	 */
	public String deleteThougth() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		boolean res = model.deleteThougth(setting);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\thought\\thought.xml";
		model.xml_Thought(fileName);
		if (res)
			addActionMessage("Record Deleted Successfully!");
		else
			addActionMessage("Record Can't be Deleted!");
		setting.setThougth("");
		setting.setHiddenCode_th("");
		model.addThougth(setting, "show", poolDir);
		model.terminate();
		return "thought";
	}

	/**Method Name: savePasswordSetting()
	 * @purpose Saving Password Settings
	 * @return String
	 */
	public String savePasswordSetting() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		boolean flag = model.savePassSetting(setting);
		if (flag)
			addActionMessage("Settings Saved Successfully");
		else
			addActionMessage("Settings Cant be Saved.");
		model.terminate();
		return "passSetting";
	}

	/**Method Name: showPassSetting()
	 * @purpose Display the saved password Settings
	 * @return String
	 */
	public String showPassSetting() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.showPassSetting(setting);
		model.terminate();
		return "passSetting";
	}

	// Action For MailBox Configuration Settings

	/**Method Name: saveMailbox()
	 * @purpose Save MailBox Settings inbound mail settings 
	 * @return String
	 */

	public String saveMailbox() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// boolean flag = model.addMailBoxConf(setting);
		// if (flag)
		addActionMessage("Configuration Saved Successfully");
		// else
		addActionMessage("Configuration Cant be Saved.");
		// model.saveMailBoxConf(setting);
		// model.saveMailBoxConfOut(setting);
		// model.showSysMail(setting);
		// model.terminate();
		return "mail";
	}

	/**Method Name : saveMailboxOut()
	 * @purpose:  Save MailBox Settings outbound mail settings
	 * @return String
	 */
	public String saveMailboxOut() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// boolean flag = model.addMailBoxConfOut(setting);
		// if (flag)
		addActionMessage("Configuration Saved Successfully");
		// else
		addActionMessage("Configuration Cant be Saved.");
		// model.saveMailBoxConf(setting);
		// model.saveMailBoxConfOut(setting);
		// model.showSysMail(setting);
		// model.terminate();
		return "mail";
	}

	/**Method Name : saveSysMail()
	 * @purpose Saving system mail ID
	 * @return String
	 */
	public String saveSysMail() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// logger.info("hidden Code" + setting.getHiddenMailCode() + "value");
		// if (setting.getHiddenMailCode() == null
		// || setting.getHiddenMailCode().equals("")) {// x
		// if (model.saveSysMail(setting))
		addActionMessage("Sytem Mail Id Added Successfully!");
		// else
		addActionMessage("Sytem Mail Id Already Exist!");
		// }// END x
		// else {// y
		// if (model.updateSysMail(setting))
		// addActionMessage("System Mail Id Modified Successfully!");
		// else
		// addActionMessage("System Mail Id Already Exist");
		// }// END y
		// resetsysMail();
		// model.saveMailBoxConf(setting);
		// model.saveMailBoxConfOut(setting);
		// model.showSysMail(setting);
		// model.terminate();
		return "mail";
	}
	
	/*
	 * public String editSysMail() { SettingMasterModel model = new
	 * SettingMasterModel(); model.initiate(context, session);
	 * //model.editSysMail(setting); model.terminate(); //
	 * model.showSysMail(setting); return "mail"; }
	 */
	/**Method Name: deleteSysMail()
	 * @purpose Delete system mail id
	 * @return String
	 */
	public String deleteSysMail() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		/*
		 * if (model.deleteSysMail(setting)) addActionMessage("System Mail Id
		 * Deleted Successfully"); else addActionMessage("System Mail Id cannot
		 * be Deleted"); model.showSysMail(setting); resetsysMail();
		 * model.showSysMail(setting);
		 */
		model.terminate();
		return "mail";
	}
	/*
	 * public void resetsysMail() { setting.setSysEmail("");
	 * setting.setSysEmailPsswd(""); setting.setHiddenMailCode(""); }
	 */
	

	/**Method Name: f9applDiv()
	 * @purpose To display Division List on Search option
	 * @return String
	 */
	public String f9applDiv(){		
		String query ="SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";
	 	if(setting.getUserProfileDivision() !=null && setting.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+setting.getUserProfileDivision() +")"; 
			query+= " ORDER BY UPPER (DIV_NAME) ";
	 
		String header =getMessage("division");
		String textAreaId =request.getParameter("divName");
				
		String hiddenFieldId =request.getParameter("divCode");
		
		String submitFlag ="";
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	/** Method Name : f9division().
	 * @Purpose : Division F9 search.
	 * @return String.
	 */
	public String f9division(){	
		String query ="SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";
	 	if(setting.getUserProfileDivision() !=null && setting.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+setting.getUserProfileDivision() +")"; 
			query+= " ORDER BY UPPER (DIV_NAME) ";
		String header =getMessage("division");
		String textAreaId =request.getParameter("divName");	
		String hiddenFieldId =request.getParameter("divCode");
		String submitFlag ="";
		String submitToMethod ="";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
  }
}
