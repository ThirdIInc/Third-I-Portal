package org.struts.action.Asset;

import org.paradyne.bean.Asset.WareHouseMaster;
import org.paradyne.model.Asset.WareHouseModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 * 
 */
public class WareHouseMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	WareHouseMaster wareHouse;

	public void prepare_local() throws Exception {
		wareHouse = new WareHouseMaster();
		wareHouse.setMenuCode(641);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return wareHouse;
	}

	public WareHouseMaster getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(WareHouseMaster wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String input() throws Exception {
		try {
			wareHouse.setEnableAll("N");
			WareHouseModel model = new WareHouseModel();
			model.initiate(context, session);
			model.Data(wareHouse, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return input();
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "Data";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String calforedit() throws Exception {
		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		model.showDetails(wareHouse);
		model.terminate();
		getNavigationPanel(3);
		wareHouse.setEnableAll("N");
		return "Data";
	}

	/*
	 * public void prepare_withLoginProfileDetails() throws Exception {
	 * WareHouseModel model = new WareHouseModel(); model.initiate(context,
	 * session); model.Data(wareHouse, request); model.terminate(); }
	 */

	public String f9actionResponsible() throws Exception {

		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME),HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_ADDRESS.ADD_MOBILE,HRMS_EMP_ADDRESS.ADD_EMAIL "
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID ) ";

		query += getprofileQuery(wareHouse);
		query += " AND EMP_STATUS='S' AND ADD_TYPE='P'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		String[] headers = { "Employee ID", "Employee Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "respToken", "respName", "respCode",
				"mobileno", "emailid" };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9actionCentralizeBranch() throws Exception {

		String query = "SELECT CENTER_ID,CENTER_NAME  FROM HRMS_CENTER  ORDER BY CENTER_ID";
		String[] headers = { "Branch Code", "Branch Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "centralizeBranchCode", "centralizeBranchName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "WareHouseMaster_addCentralizeBranch.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9action() throws Exception {

		String query = "SELECT WAREHOUSE_CODE,WAREHOUSE_NAME,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME,"
				+ " WAREHOUSE_RESPONSIBLE_PERSON,EMP_TOKEN FROM HRMS_WAREHOUSE_MASTER "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_WAREHOUSE_MASTER.WAREHOUSE_RESPONSIBLE_PERSON) "
				+ " ORDER BY WAREHOUSE_CODE";

		String[] headers = { "Ware House Code", getMessage("warehouse.name"),
				getMessage("warehouse.resperson") };
		String[] headerWidth = { "15", "43", "42" };
		String[] fieldNames = { "wareHousecode", "wareHouseName", "respName",
				"respCode", "respToken" };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitFlag = "true";
		String submitToMethod = "WareHouseMaster_showDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String showDetails() {
		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		model.showDetails(wareHouse);
		model.terminate();
		getNavigationPanel(3);
		return "Data";
	}

	public String branchList() {
		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		// model.showDetails(wareHouse);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String f9actionBranchWiseSelect() throws Exception {
		String branchCode = "";
		String[] branchIds = request.getParameterValues("branchCodeIterator");
		if (branchIds != null) {
			for (int i = 0; i < branchIds.length - 1; i++)
				branchCode += branchIds[i] + ",";
			branchCode += branchIds[branchIds.length - 1];
		} else
			branchCode = "0";
		String condition = "";
		String wareHouseCode = checkNull(wareHouse.getWareHousecode());
		logger.info("wareHouseCode String===" + wareHouseCode);
		if (!(wareHouseCode.equals("null") || wareHouseCode.equals("") || wareHouseCode == null)) {
			condition = "WHERE WAREHOUSE_CODE !="
					+ wareHouse.getWareHousecode();
		}
		String query = "SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER WHERE CENTER_ID NOT IN("
				+ branchCode
				+ ")"
				+ " AND CENTER_ID NOT IN(SELECT WAREHOUSE_BRANCH FROM HRMS_WAREHOUSE_BRANCH "
				+ condition + ")" + " ORDER BY UPPER(CENTER_NAME)";

		String[] headers = { getMessage("branch") };
		String[] headerWidth = { "80" };
		String[] fieldNames = { "branchName", "branchCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "WareHouseMaster_branchList.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		// wareHouse.setWareHousecode(wareHouseCode);
		return "f9page";
	}

	public String reset() {
		wareHouse.setBranchCode("");
		wareHouse.setBranchFlag("false");
		wareHouse.setBranchName("");
		wareHouse.setCentralizeBranchCode("");
		wareHouse.setCentralizeBranchName("");
		wareHouse.setCentralizeFlag("false");
		wareHouse.setRespCode("");
		wareHouse.setRespName("");
		wareHouse.setRespToken("");
		wareHouse.setTableLength("");
		wareHouse.setWareHousecode("");
		wareHouse.setWareHouseName("");
		wareHouse.setEmailid("");
		wareHouse.setMobileno("");
		getNavigationPanel(2);
		return "Data";
	}

	public String addBranch() {
		try {
			logger.info("addBranch....!!");
			String branchCode[] = request
					.getParameterValues("branchCodeIterator");
			String branchName[] = request
					.getParameterValues("branchNameIterator");
			if (branchCode != null && branchCode.length > 0) {
				logger.info("addBranch....!!" + branchCode.length);
			} else {
				logger.info("addBranch....!!");
			}
			WareHouseModel model = new WareHouseModel();
			model.initiate(context, session);
			model.addBranch(wareHouse, branchCode, branchName);
			logger.info("after addBranch....function...!!");
			wareHouse.setBranchFlag("true");
			wareHouse.setBranchCode("");
			wareHouse.setBranchName("");
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.info("" + e);
		}
		wareHouse.setEnableAll("Y");
		logger.info("addBranch....!!");
		return "Data";
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}
	}

	public String save() {
		WareHouseModel model = new WareHouseModel();
		boolean result = false;
		String branchCode[] = request.getParameterValues("branchCodeIterator");
		String[] branchName = request.getParameterValues("branchNameIterator");
		model.initiate(context, session);
		if (wareHouse.getWareHousecode().equals("")) {
			result = model.save(wareHouse, branchCode);
			if (result) {
				addActionMessage("Record saved successfully.");
				// reset();
				getNavigationPanel(3);
			} else {
				reset();
				getNavigationPanel(1);
				addActionMessage("Duplicate entry found.");
				// showBranchList();
			}
		} else {
			result = model.update(wareHouse, branchCode);
			if (result) {
				addActionMessage("Record updated successfully.");
				// reset();
				getNavigationPanel(3);
			} else {
				addActionMessage("Duplicate entry found.");
				// showBranchList();
				getNavigationPanel(1);
			}
		}
		model.showBranchList(wareHouse, branchCode, branchName);
		model.Data(wareHouse, request);
		model.terminate();

		return "Data";
	}

	public String showBranchList() {
		String[] branchCode = request.getParameterValues("branchCodeIterator");
		String[] branchName = request.getParameterValues("branchNameIterator");
		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		model.showBranchList(wareHouse, branchCode, branchName);
		model.terminate();
		getNavigationPanel(2);
		return "Data";
	}

	public String addCentralizeBranch() {
		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		model.addCentralizeBranch(wareHouse);
		wareHouse.setCentralizeBranchCode("");
		wareHouse.setCentralizeBranchName("");
		getNavigationPanel(2);
		model.terminate();
		return SUCCESS;
	}

	public String deleteBranch() {
		try {
			String[] branchCode = request
					.getParameterValues("branchCodeIterator");
			String[] branchName = request
					.getParameterValues("branchNameIterator");
			WareHouseModel model = new WareHouseModel();
			model.initiate(context, session);
			model.deleteBranch(wareHouse, branchCode, branchName);
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Data";
	}

	public String clearList() {
		wareHouse.setTableLength("");
		return SUCCESS;
	}

	public String delete() {
		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		if (model.deleteRecord(wareHouse)) {
			addActionMessage("Record deleted successfully.");

		} else {
			addActionMessage("This record is referenced in other resources.So can't delete.");
			// showBranchList();
		}
		model.Data(wareHouse, request);
		model.terminate();
		reset();
		getNavigationPanel(1);

		return SUCCESS;
	}

	public String report() {
		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		model.report(wareHouse, request, response);
		model.terminate();
		return null;
	}

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		WareHouseModel model = new WareHouseModel();
		model.initiate(context, session);
		boolean result = false;
		result = model.deleteCheckedRecords(wareHouse, code);

		if (result) {
			addActionMessage(getMessage("delete"));
			// reset();
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.Data(wareHouse, request);
		model.terminate();
		getNavigationPanel(1);
		return "success";
	}

}
