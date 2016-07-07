/**
 * 
 */
package org.struts.action.helpdesk;

import org.paradyne.bean.helpdesk.HelpDeskSubReqType;
import org.paradyne.model.helpdesk.HelpDeskSubReqTypeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0623
 *
 */
public class HelpDeskSubReqTypeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.action.helpdesk.HelpDeskSubReqTypeAction.class);
	
	HelpDeskSubReqType subReqType; 

	/**
	 * @return the subReqType
	 */
	public HelpDeskSubReqType getSubReqType() {
		return subReqType;
	}

	/**
	 * @param subReqType the subReqType to set
	 */
	public void setSubReqType(HelpDeskSubReqType subReqType) {
		this.subReqType = subReqType;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		subReqType = new HelpDeskSubReqType();
		subReqType.setMenuCode(1042);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return subReqType;
	}
	
	@Override
	public String input() throws Exception {
		getNavigationPanel(1);
		HelpDeskSubReqTypeModel model = new HelpDeskSubReqTypeModel();
		model.initiate(context, session);
		model.getRecords(subReqType, request);
		subReqType.setEnableAll("Y");
		model.terminate();
		return INPUT;
	}
	
	public String addNew() {
		try {
			logger.info("in add new");
			reset();
			getNavigationPanel(2);
			logger.info("in add new");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String f9Action() throws Exception {

		String query = " SELECT NVL(HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_NAME,' '), NVL(HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,' '), " 
			+ " NVL(HELPDESK_SLA_HDR.SLA_NAME,' '), " 
			+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID, " 
			+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID, "
			+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA " 
			+ " FROM HELPDESK_REQUEST_SUBTYPE "
			+ " LEFT JOIN HELPDESK_REQUEST_TYPE  ON (HELPDESK_REQUEST_TYPE .REQUEST_TYPE_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID) " 
			+ " LEFT JOIN HELPDESK_SLA_HDR ON (HELPDESK_SLA_HDR.SLA_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA) "
			+ " ORDER BY HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID, HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID";

		String[] headers = { getMessage("subreq.type"), getMessage("req.type"), getMessage("sla.name") };
		String[] headerwidth = { "35", "35", "30" };

		String fieldNames[] = { "subReqType", "reqType", "slaName", "subReqTypeCode",
				"reqTypeCode", "slaCode" };

		int[] columnIndex = { 0, 1, 2, 3,4 ,5 };

		String submitFlage = "true";

		String submitToMethod = "HelpDeskSubReqType_details.action";

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
		HelpDeskSubReqTypeModel model;
		try {
			subReqType.setHidReqTypeCode(subReqType.getReqTypeCode());
			model = new HelpDeskSubReqTypeModel();
			model.initiate(context, session);
			model.getSubRequestTypes(subReqType, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return "success";
	}
	
	public String f9reqtypeaction() throws Exception {

		String query = " SELECT NVL(REQUEST_TYPE_NAME,' '), REQUEST_TYPE_ID "
				+ " FROM HELPDESK_REQUEST_TYPE "
				+ " WHERE IS_ACTIVE='Y' "
				+ " ORDER BY REQUEST_TYPE_ID ";

		String[] headers = { getMessage("req.type") };
		String[] headerwidth = { "100" };

		String fieldNames[] = { "reqType", "reqTypeCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlage = "false";

		String submitToMethod = "HelpDeskSubReqType_details.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}
	
	public String f9slaaction() throws Exception {

		String query = " SELECT NVL(SLA_NAME,' '), SLA_ID "
				+ " FROM HELPDESK_SLA_HDR "
				+ " WHERE IS_ACTIVE='Y'"
				+ " ORDER BY SLA_ID ";

		String[] headers = { getMessage("sla.name") };
		String[] headerwidth = { "100" };

		String fieldNames[] = { "slaName", "slaCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlage = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}
	
	public String save() throws Exception{
		String result = "";
		String page;

		HelpDeskSubReqTypeModel model = new HelpDeskSubReqTypeModel();
		model.initiate(context, session);

		if (subReqType.getSubReqTypeCode().equals("")) {
			result = model.addSubRequestTypes(subReqType);
			if (result.equals("saved")) {
				subReqType.setHidReqTypeCode(subReqType.getReqTypeCode());
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
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
			logger.info("subReqType.getHidReqTypeCode()  : "+subReqType.getHidReqTypeCode());
			
			logger.info("HidReqTypeCode()  : "+subReqType.getHiddenReqCode());
			result = model.modSubRequestTypes(subReqType);
			if (result.equals("modified")) {
				logger.info("reqtype code  :"+subReqType.getReqTypeCode());
				subReqType.setHidReqTypeCode(subReqType.getReqTypeCode());
				addActionMessage(getText("Record updated successfully!"));
				getNavigationPanel(3);
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
		model.getSubRequestTypes(subReqType, request);
		model.terminate();
		return page;
	}
	
	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		getNavigationPanel(1);
		HelpDeskSubReqTypeModel model = new HelpDeskSubReqTypeModel();
		model.initiate(context, session);
		boolean result = model.deleteReqType(subReqType);
		if (result) {
			addActionMessage(getText("Record deleted successfully!"));
		} else {
			addActionMessage("This record is referenced in some other records \n so can not be deleted");
		}
		model.terminate();
		reset();
		model.getRecords(subReqType, request);
		getNavigationPanel(1);
		return INPUT;
	}
	

	/**
	 * following function is called to reset the fields.
	 * 
	 * @return
	 */
	public String reset() {
		subReqType.setReqType("");
		subReqType.setReqTypeCode("");
		subReqType.setSubReqType("");
		subReqType.setSubReqTypeCode("");
		subReqType.setSlaCode("");
		subReqType.setSlaName("");
		subReqType.setHiddenReqCode("");
		subReqType.setHiddenSubReqCode("");
		subReqType.setHidReqTypeCode("");
		subReqType.setIsLinkAsset("false");
		subReqType.setAssetTypeId("");
		subReqType.setAssetType("");
		getNavigationPanel(2);
		return SUCCESS;
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
		HelpDeskSubReqTypeModel model = new HelpDeskSubReqTypeModel();
		model.initiate(context, session);
		model.getRecords(subReqType, request);
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
	public String calforedit() throws Exception {
		try {
			HelpDeskSubReqTypeModel model = new HelpDeskSubReqTypeModel();
			model.initiate(context, session);
			model.getSubReqTypeOnDblClick(subReqType, request);
			subReqType.setHidReqTypeCode(subReqType.getHiddenReqCode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		subReqType.setEnableAll("N");
		return SUCCESS;
	}
	
	public String deleteList() throws Exception {
		getNavigationPanel(1);
		String subReqCode[] = request.getParameterValues("hdeleteCode");
		for (int i = 0; i < subReqCode.length; i++) {
			logger.info("sub reqcodes  : "+subReqCode[i]);
		}
		/*String subReqCode[] = request.getParameterValues("hdeleteSubCode");
		for (int j = 0; j < subReqCode.length; j++) {
			logger.info("subReqCodes  : "+subReqCode[j]);
		}*/
		HelpDeskSubReqTypeModel model = new HelpDeskSubReqTypeModel();
		model.initiate(context, session);
		boolean result = model.delChkdRec(subReqType, subReqCode);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can not be deleted \n as they are associated with some other records.");
		}
		model.getRecords(subReqType, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	public String f9assetType() {
		
		try {
			String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,'') FROM HRMS_ASSET_CATEGORY "
					+ "  ORDER BY ASSET_CATEGORY_CODE ";
			String[] headers = { "Asset Type Id", getMessage("assettyp") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "assetTypeId", "assetType" };
			int[] columnIndex = { 0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String edit(){
		try {
			HelpDeskSubReqTypeModel model = new HelpDeskSubReqTypeModel();
			model.initiate(context, session);
			model.getSubReqTypeOnDblClick(subReqType, request);
			subReqType.setHidReqTypeCode(subReqType.getHiddenReqCode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	

}
