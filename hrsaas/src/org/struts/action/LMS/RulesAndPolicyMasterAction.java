package org.struts.action.LMS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.LMS.RulesAndPolicyMasterBean;
import org.paradyne.model.LMS.RulesAndPolicyMasterModel;

import org.struts.lib.ParaActionSupport;

public class RulesAndPolicyMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RulesAndPolicyMasterAction.class);

	RulesAndPolicyMasterBean bean;

	public RulesAndPolicyMasterBean getBean() {
		return bean;
	}

	public void setBean(RulesAndPolicyMasterBean bean) {
		this.bean = bean;
	}

	public void prepare_local() throws Exception {

		bean = new RulesAndPolicyMasterBean();
		bean.setMenuCode(1164);
		getNavigationPanel(1);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		RulesAndPolicyMasterAction.logger = logger;
	}

	public String input() throws Exception {

		try {

			RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(1);
		// councilBean.setEnableAll("Y");
		return INPUT;
	}

	public String addNew() {
		try {
			reset();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(3);
		return SUCCESS;
	}

	/*
	 * public String back() {
	 * 
	 * TradeUnionWorkCouncilModel councilModel = new
	 * TradeUnionWorkCouncilModel(); councilModel.initiate(context, session);
	 * councilModel.getList(councilBean, request); councilModel.terminate();
	 * 
	 * getNavigationPanel(1);
	 * 
	 * return INPUT; }
	 */

	public String back() throws Exception {
		input();
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		return INPUT;
	}

	public String save() {
		RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getRulesAndPolicyID().equals("")) {
			result = model.save(bean, request);

			if (result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("duplicate record found");
			}
		} else {

			result = model.update(bean, request);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if else
			else {
				addActionMessage("duplicate record found");
			}// end of else
		}
		// model.getList(bean, request);
		model.terminate();
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(1);
		bean.setEnableAll("Y");

		return INPUT;
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();
		model.initiate(context, session);
		boolean result = model.delRecord(bean);
		model.getList(bean, request);
		model.terminate();

		if (result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		bean.setCategoryType("");
		bean.setCommitteeType("");
		bean.setName("");
		bean.setDetails("");
		// bean.setRoleType("");
		bean.setEnableAll("Y");
		return INPUT;
	}

	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	/*
	 * This function called when edit button clicked on jsp after record get
	 * saved
	 */
	public String edit() throws Exception {
		try {
			RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();

			String custID = request.getParameter("rulesAndPolicyID");
			System.out.println("custID = " + custID);

			model.initiate(context, session);
			model.setRulesAndPolicyRecord(bean, custID);
			model.terminate();
			getNavigationPanel(3);
			bean.setEnableAll("Y");

		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}

	public String reset() throws Exception {
		try {
			bean.setRulesAndPolicyID("");
			/*
			 * bean.setCommitteeType(""); bean.setEmpToken("");
			 * bean.setEmpName(""); bean.setTrainingReceivedFlag("");
			 * bean.setRoleType("");
			 */

			getNavigationPanel(3);
		} catch (Exception e) {
			logger.error("Error in reset" + e);

		}
		return SUCCESS;
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	/*
	 * public String delete() throws Exception { RulesAndPolicyMasterModel model =
	 * new RulesAndPolicyMasterModel(); model.initiate(context, session);
	 * boolean result = model.delRecord(bean); model.getList(bean, request);
	 * model.terminate();
	 * 
	 * if (result) { addActionMessage(getMessage("delete")); } else {
	 * addActionMessage(getMessage("del.error")); }// end of else
	 * getNavigationPanel(1); bean.setCommitteeType(""); bean.setEmpToken("");
	 * bean.setEmpName(""); bean.setTrainingReceivedFlag("");
	 * bean.setRoleType("");
	 * 
	 * return INPUT; }
	 */

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 */

	public String deleteChkRecords() throws Exception {

		try {
			String code[] = request.getParameterValues("hiddenCode");
			RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();
			model.initiate(context, session);
			boolean result = model.deleteCheckedRecords(bean, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
				reset();
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
			model.getList(bean, request);
			getNavigationPanel(1);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured : " + e);
		}
		return INPUT;

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 */
	public String calforedit() throws Exception {
		try {
			RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();
			String custID = request.getParameter("rulesAndPolicyID");
			System.out.println("custID = " + custID);
			model.initiate(context, session);
			model.calforedit(bean, custID, request);

			getNavigationPanel(2);
			bean.setEnableAll("N");

			model.terminate();

		} catch (Exception e) {
			System.out.println("Error Occured : " + e);
		}
		// cbean.setEnableAll("N");
		return SUCCESS;
	}// End of calforedit()

	public String addMultipleProof() {

		try {
			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");
			String[] proofFileName = request
					.getParameterValues("proofFileName");
			RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(srNo, proofName, bean);
			model.setProofList(srNo, proofName, bean);
			// model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());
			bean.setUploadLocFileName("");
			// model.setEligibleAmountAccordingToPolicy(claimApp);
			// setItteratorData();

			// claimApp.setAddNewFlag("true");
			// claimApp.setEnableAll("true");

			// setNavigationPanel();
			getNavigationPanel(3);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addMultipleProof--------" + e);
		}

		return SUCCESS;
	}

	public void viewAttachedProof() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");

			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "/TMS/" + poolName
					+ "/Tickets/" + fileName;

			oStream = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch", e);
			addActionMessage("File not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}

		// return null;
	}

	public String removeUploadFile() {

		try {

			String[] srNo = request.getParameterValues("proofSrNo"); // serial

			String[] proofName = request.getParameterValues("proofName");// keep

			// String[] proofFileName =
			// request.getParameterValues("proofFileName");
			RulesAndPolicyMasterModel model = new RulesAndPolicyMasterModel();
			model.initiate(context, session);
			// trvlClaimDtl();
			// setExpDtls();
			// model.removeUploadFile(srNo, proofName, proofFileName, claimApp);
		 model.removeUploadFile(srNo, proofName, bean);

			/*
			 * claimApp.setSchFlag("true"); claimApp.setAddNewFlag("true"); //
			 * claimApp.setUploadFlag("true"); setNavigationPanel();
			 */
		 getNavigationPanel(3);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile--------" + e);
		}

		return SUCCESS;
	}

}
