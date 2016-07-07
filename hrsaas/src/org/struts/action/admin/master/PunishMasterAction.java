/**
 * 
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.PunishMaster;
import org.paradyne.model.admin.master.PunishMasterModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author riteshr
 * 
 */
public class PunishMasterAction extends ParaActionSupport {

	PunishMaster punishMaster = null;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		punishMaster = new PunishMaster();
		punishMaster.setMenuCode(255);

	}

	public Object getModel() {

		return punishMaster;
	}

	public PunishMaster getPunishMaster() {
		return punishMaster;
	}

	public void setPunishMaster(PunishMaster punishMaster) {
		this.punishMaster = punishMaster;
	}
	

//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "punishData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String input() throws Exception {
		punishMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String getCredit(){
		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context,session);
		model.getCreditPunish(punishMaster,request);
		getNavigationPanel(3);
		model.terminate();
		
	return "punishData";	
	}
	

	/**
	 * called on load to set the list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionPunish() throws Exception {

		String query = " SELECT PUNISH_NAME, NVL(FINANCIAL_IMPLICATIONS,' '),IS_MAJOR, PUNISH_ID FROM HRMS_PUNISHMENT ORDER BY upper(PUNISH_NAME) ";
		String[] headers = { getMessage("punishname")};
		String[] headerWidth = {  "100" };
		String[] fieldNames = { 
				"punishMaster.punishName", "punishMaster.fImplication",
				"punishMaster.isMajor" ,"punishMaster.punishId"};
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlag = "true";
		String submitToMethod ="PunishMaster_getCredit.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * called on load to set the list
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);

		model.punisData(punishMaster, request);
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);
		model.punisData(punishMaster,request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String calforedit() throws Exception {
		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);
		model.calforedit(punishMaster,request);
		model.getCreditPunish(punishMaster,request);
		model.punisData(punishMaster,request);
		getNavigationPanel(3);
		punishMaster.setEnableAll("N");
		model.terminate();
		return "punishData";
	}

	/*
	 * public String calfordelete() { PunishMasterModel model = new
	 * PunishMasterModel(); model.initiate(context,session); boolean result;
	 * result = model.calfordelete(punishMaster); if(result){
	 * addActionMessage(getText("Record deleted Successfully"));
	 * 
	 * punishMaster.setPunishId(""); punishMaster.setPunishName("");
	 * punishMaster.setIsMajor(""); punishMaster.setFImplication(""); } else{
	 * addActionMessage("One or more records can't be deleted \n as they are
	 * associated with some other record(s) ."); }
	 * 
	 * model.punisData(punishMaster,request); model.terminate();
	 * 
	 * return "success"; }
	 */

	/*
	 * public String delete1()throws Exception { String
	 * code[]=request.getParameterValues("hdeleteCode");
	 * 
	 * PunishMasterModel model = new PunishMasterModel();
	 * 
	 * model.initiate(context,session); boolean result
	 * =model.deletePunishment(punishMaster,code); if(result) {
	 * addActionMessage("Record deleted successfully"); reset();
	 * 
	 *  } else { addActionMessage("One or more records can't be deleted \n as
	 * they are associated with some other record(s) ."); }
	 * 
	 * 
	 * 
	 * model.punisData(punishMaster,request); model.terminate();
	 * 
	 * return "success";
	 *  }
	 */

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		PunishMasterModel model = new PunishMasterModel();

		model.initiate(context, session);
		String result = model.deletecheckedRecords(punishMaster, code);

		if (result.equals("true")) {
			addActionMessage(getMessage("delete"));
			reset();
		}// end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		punishMaster.setIsCredit("false");
		model.punisData(punishMaster, request);
		getNavigationPanel(1);
		model.terminate();

		return "success";

	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String save() throws Exception {
		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);
		boolean result;
		String []creditId=request.getParameterValues("creditCode");
		String []percent=request.getParameterValues("creditPercent");
		if (punishMaster.getPunishId().equals("")) {
			logger.info("else addDept");
			
			result = model.savePunish(punishMaster,creditId,percent,request);
			if (result) {
				addActionMessage(getMessage("save"));
				model.getCreditPunish(punishMaster,request);
				getNavigationPanel(3);
				return "punishData";
			}// end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}// end of else
		} // end of nested if
		else {

			result = model.modPunish(punishMaster,creditId,percent);
			if (result) {
				addActionMessage(getMessage("update"));
				model.getCreditPunish(punishMaster,request);
				getNavigationPanel(3);
				return "punishData";
			}// end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}// end of else
		}// end of else
	/*	model.punisData(punishMaster, request);
		punishMaster.setIsCredit("false");
		request.setAttribute("creditData","false");
		model.terminate();

		return reset();*/
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {

		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);
		String result = model.deleteRecord(punishMaster);
		model.punisData(punishMaster, request);
		//punishMaster.setIsCredit("false");
		model.terminate();
		addActionMessage(result);
		reset();
		getNavigationPanel(1);
		return "success";
		//reset();
		//return "success";

	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {

			punishMaster.setPunishId("");
			punishMaster.setPunishName("");
			punishMaster.setIsMajor("");
			punishMaster.setFImplication("");
			
			
			getNavigationPanel(2);
			
		} catch (Exception e) {

			logger.error("Error in reset" + e);
		}
		return "punishData";
	}

	/*
	 * public String report() throws Exception{ PunishMasterModel model = new
	 * PunishMasterModel(); model.initiate(context,session);
	 * model.getReport(punishMaster); model.terminate(); return "report"; }
	 */

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("punishname"),getMessage("financial"),getMessage("ismajor")};
		model.getReport(punishMaster, request, response, context, session,label);
		model.terminate();
		return null;
	}
	
/**
 * following function is called when the check box is checked
 * @return
 */	
	public String callCreditHead() {
		getNavigationPanel(2);
		punishMaster.setFlag(true);
		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);
		
		if(punishMaster.getPunishId().equals("") || punishMaster.getPunishId().equals("null")){
			model.getCreditDetails(punishMaster);
		}else{
			if(model.chkPunishId(punishMaster))
				model.getCreditPunish(punishMaster,request);
			else
				model.getCreditDetails(punishMaster);
		}
		punishMaster.setEnableAll("Y");
		model.terminate();
		return "punishData";
	}

	public String callCreditHeads() {
		getNavigationPanel(2);
		PunishMasterModel model = new PunishMasterModel();
		model.initiate(context, session);
		punishMaster.setFlag(false);
		model.terminate();
		return "punishData";
	}
	
	
}
