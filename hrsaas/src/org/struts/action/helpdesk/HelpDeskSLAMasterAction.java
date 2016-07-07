/**
 * 
 */
package org.struts.action.helpdesk;

import org.paradyne.bean.helpdesk.HelpDeskSLAMaster;
import org.paradyne.model.helpdesk.HelpDeskSLAMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0623
 *
 */
public class HelpDeskSLAMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.action.helpdesk.HelpDeskSLAMasterAction.class);
	
	HelpDeskSLAMaster slaMaster;
	/**
	 * @return the slaMaster
	 */
	public HelpDeskSLAMaster getSlaMaster() {
		return slaMaster;
	}

	/**
	 * @param slaMaster the slaMaster to set
	 */
	public void setSlaMaster(HelpDeskSLAMaster slaMaster) {
		this.slaMaster = slaMaster;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		slaMaster = new HelpDeskSLAMaster();
		slaMaster.setMenuCode(1041);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return slaMaster;
	}
	
	@Override
	public String input() {
		try {
			HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
			model.initiate(context, session);
			model.getRecords(slaMaster, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		slaMaster.setEnableAll("N");
		return INPUT;
	}
	
	public String addNew() {
		try {
			reset();
			HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
			model.initiate(context, session);
			model.getSLACategories(slaMaster);
			model.terminate();
			getNavigationPanel(2);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**
	 * following function is called to reset the fields.
	 * 
	 * @return
	 */
	public String reset() {
		HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
		model.initiate(context, session);
		slaMaster.setSlaCode("");
		slaMaster.setSlaName("");
		slaMaster.setSlaDesc("");
		slaMaster.setHdeleteCode("");
		model.getSLACategories(slaMaster);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String f9Action() throws Exception {

		String query = " SELECT NVL(SLA_NAME,' '), NVL(SLA_DESC,' '),SLA_ID  FROM HELPDESK_SLA_HDR "
			+ "ORDER BY SLA_ID";

		String[] headers = { getMessage("sla.name"), getMessage("sla.desc") };
		String[] headerwidth = { "40", "60" };

		String fieldNames[] = { "slaName", "slaDesc", "slaCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlage = "true";

		String submitToMethod = "HelpDeskSLAMaster_details.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}
	
	/**
	 * following function is called to set the field values when a record is
	 * selected from the search window
	 * 
	 * @throws Exception
	 */
	public String details() {
		
		try {
			slaMaster.setHiddencode(slaMaster.getSlaCode());
			HelpDeskSLAMasterModel model;
			model = new HelpDeskSLAMasterModel();
			model.initiate(context, session);
			model.getSLADtl(slaMaster);
			model.getSLACategories(slaMaster);
			model.getSavedSLACategories(slaMaster);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		slaMaster.setEnableAll("N");
		return "success";
	}
	
	public String save() {

		String result = "";
		String page="";
		
		HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
		try {
			String[] id = request.getParameterValues("slaCategId");
			String[] duration = request.getParameterValues("slaDuration");
			String[] durType = request.getParameterValues("durType");
			String[] escalateIdOne = request
					.getParameterValues("escalateOneEmpId");
			String[] durationOne = request.getParameterValues("escDurationOne");
			String[] durationTypeOne = request.getParameterValues("durTypeOne");
			String[] escalateIdTwo = request
					.getParameterValues("escalateTwoEmpId");
			String[] durationTwo = request.getParameterValues("escDurationTwo");
			String[] durationTypeTwo = request.getParameterValues("durTypeTwo");
			String[] escalateIdThree = request
					.getParameterValues("escalateThreeEmpId");
			String[] durationThree = request
					.getParameterValues("escDurationThree");
			String[] durationTypeThree = request
					.getParameterValues("durTypeThree");
			int slaCategories = slaMaster.getSlaCategoryCounter();
			
			model.initiate(context, session);
			if (slaMaster.getSlaCode().equals("")) {
				result = model.addSlaHeader(slaMaster, id, duration,
						durType, escalateIdOne, durationOne,
						durationTypeOne, escalateIdTwo, durationTwo,
						durationTypeTwo, escalateIdThree, durationThree,
						durationTypeThree, slaCategories);
				if (result.equals("saved")) {
					model.getSavedSLACategories(slaMaster);
					addActionMessage(getText("addMessage", ""));
					getNavigationPanel(3);
					slaMaster.setEnableAll("N");
					page = "success";
				} else if (result.equals("duplicate")) {
					addActionMessage(getText("Duplicate entry found!"));
					getNavigationPanel(1);
					input();
					page = "input";
				} else {
					addActionMessage(getText("Record can not be saved!"));
					getNavigationPanel(1);
					input();
					page = "input";
				}
			} else {
				result = model.modSlaHeader(slaMaster, id, duration,
						durType, escalateIdOne, durationOne,
						durationTypeOne, escalateIdTwo, durationTwo,
						durationTypeTwo, escalateIdThree, durationThree,
						durationTypeThree, slaCategories);
				if (result.equals("modified")) {
					model.getSavedSLACategories(slaMaster);
					addActionMessage(getText("Record updated successfully!"));
					getNavigationPanel(3);
					slaMaster.setEnableAll("N");
					page = "success";

				} else if (result.equals("duplicate")) {
					addActionMessage(getText("Duplicate entry found!"));
					getNavigationPanel(1);
					input();
					page = "input";
				} else {
					addActionMessage(getText("Record cannot be updated!"));
					getNavigationPanel(1);
					input();
					page = "input";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return page;
	}
	
	/**
	 * following function is called to display all the records when the link is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
		model.initiate(context, session);
		model.getRecords(slaMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}

	/**
	 * following function is called when
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() {
		try {
			HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
			model.initiate(context, session);
			model.getSLADtl(slaMaster);
			model.getSLACategories(slaMaster);
			model.getSavedSLACategories(slaMaster);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		slaMaster.setEnableAll("N");
		return SUCCESS;
	}

	public String deleteList() throws Exception {
		getNavigationPanel(1);
		String code[] = request.getParameterValues("hdeleteCode");
		HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
		model.initiate(context, session);
		boolean result = model.delChkdRec(slaMaster, code);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can not be deleted \n as they are associated with some other records.");
		}
		model.getRecords(slaMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		getNavigationPanel(1);
		HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteSLA(slaMaster);
		if (result) {
			addActionMessage(getText("Record deleted successfully!"));
		} else {
			addActionMessage("This record is referenced in some other records \n so can not be deleted");
		}
		model.terminate();
		reset();
		model.getRecords(slaMaster, request);
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String f9EscalateToEmployeeOne() throws Exception {
		String  id = slaMaster.getRowId();
		
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";
		query += getprofileQuery(slaMaster);
		query += " ORDER BY EMP_ID  ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "15", "30" };
		logger.info("id>>>>" + id);
		
		String[] fieldNames = { "escalateOneEmpToken"+id, "escalateOneEmpName"+id, "escalateOneEmpId"+id };

		int[] columnIndex = { 0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9EscalateToEmployeeTwo() throws Exception {
		String  id = slaMaster.getRowId();
		
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
			+ "	HRMS_EMP_OFFC.EMP_ID "
			+ " FROM HRMS_EMP_OFFC "
			+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
			+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
			+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";
		query += getprofileQuery(slaMaster);
		query += " ORDER BY EMP_ID  ";
		
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		
		String[] headerWidth = { "15", "30" };
		logger.info("id>>>>" + id);
		
		String[] fieldNames = { "escalateTwoEmpToken"+id, "escalateTwoEmpName"+id, "escalateTwoEmpId"+id };
		
		int[] columnIndex = { 0, 1, 2};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	public String f9EscalateToEmployeeThree() throws Exception {
		String  id = slaMaster.getRowId();
		
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
			+ "	HRMS_EMP_OFFC.EMP_ID "
			+ " FROM HRMS_EMP_OFFC "
			+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
			+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
			+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";
		query += getprofileQuery(slaMaster);
		query += " ORDER BY EMP_ID  ";
		
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		
		String[] headerWidth = { "15", "30" };
		logger.info("id>>>>" + id);
		
		String[] fieldNames = { "escalateThreeEmpToken"+id, "escalateThreeEmpName"+id, "escalateThreeEmpId"+id };
		
		int[] columnIndex = { 0, 1, 2};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String edit(){
		try {
			HelpDeskSLAMasterModel model = new HelpDeskSLAMasterModel();
			model.initiate(context, session);
			model.getSLADtl(slaMaster);
			model.getSLACategories(slaMaster);
			model.getSavedSLACategories(slaMaster);
			getNavigationPanel(2);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String back() {
		input();
		return INPUT;
	}
}
