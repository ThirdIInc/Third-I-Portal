package org.struts.action.Asset;

import java.io.FileInputStream;
import java.util.Properties;

import org.paradyne.bean.Asset.AssetEmployee;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.Asset.AssetApprovalModel;
import org.paradyne.model.Asset.AssetEmployeeModel;
import org.paradyne.model.Asset.PurchaseOrderModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class AssetEmployeeAction extends ParaActionSupport {
	AssetEmployee assetEmployee;

	@Override
	public void prepare_local() throws Exception {
		assetEmployee = new AssetEmployee();
		assetEmployee.setMenuCode(380);
	}

	public Object getModel() {
		return assetEmployee;
	}

	public AssetEmployee getAssetEmployee() {
		return assetEmployee;
	}

	public void setAssetEmployee(AssetEmployee assetEmployee) {
		this.assetEmployee = assetEmployee;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.Asset.AssetEmployeeAction.class);

	/*
	 * public String f9action() throws Exception {
	 * 
	 * String query = " SELECT ASSMT_CODE,EMP_TOKEN,(TITLE_NAME||'
	 * '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||
	 * HRMS_EMP_OFFC.EMP_LNAME),EMP_CODE FROM HRMS_ASSMT " +" INNER JOIN
	 * HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSMT.EMP_CODE)" +" LEFT JOIN
	 * HRMS_TITLE ON(HRMS_TITLE .TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)" ;
	 * 
	 * logger.info("assetEmployee.isGeneralFlag=="+assetEmployee.isGeneralFlag());
	 * if(assetEmployee.isGeneralFlag()){ query+=" WHERE EMP_CODE="
	 * +assetEmployee.getUserEmpId(); } query +=" ORDER BY ASSMT_CODE ";
	 * String[] headers={"Code","Employee ID","Employee Name"};
	 * 
	 * String[] headerWidth={"20","20","60"};
	 * 
	 * String[] fieldNames={"code","empToken","empName","empCode"};
	 * 
	 * 
	 * int[] columnIndex={0,1,2,3};
	 * 
	 * 
	 * String submitFlag = "true";
	 * 
	 * 
	 * String submitToMethod="AssetEmployee_showDetails.action";
	 * 
	 * 
	 * setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	 * 
	 * return "f9page"; }
	 */

	public String input() {
		try {
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			getNavigationPanel(1);
			logger.info("input");
			assetEmployee.setListType("pending");
			model.getAllTypeOfApplications(assetEmployee, request,
					assetEmployee.getUserEmpId());
			model.terminate();
			
			if (assetEmployee.getSource().equals("mymessages")) {
				return "mymessages";
			} else if (assetEmployee.getSource().equals("myservices")) {
				return "serviceJsp";
			} else {
				return "input";
			}
		
		} catch (Exception e) {
			logger.error("Exception in input");
			return null;
		}
	}

	public String callPage() throws Exception {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);

		if (assetEmployee.getListType().equals("approved"))
			model.getApprovedList(assetEmployee, request, assetEmployee
					.getUserEmpId());
		if (assetEmployee.getListType().equals("rejected"))
			model.getRejectedList(assetEmployee, request, assetEmployee
					.getUserEmpId());
		if (assetEmployee.getListType().equals("assigned"))
			model.getAssignedList(assetEmployee, request, assetEmployee
					.getUserEmpId());

		model.getAllTypeOfApplications(assetEmployee, request, assetEmployee
				.getUserEmpId());

		model.terminate();
		getNavigationPanel(1);
		return INPUT;

	}

	public String addNew() throws Exception {

		try {
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			assetEmployee.setSource(source);
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * Aprroved list
	 */
	public String getApprovedList() {
		try {
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			assetEmployee.setListType("approved");
			// assetEmployee.setListLengthApproved("true");
			getNavigationPanel(1);
			model.getApprovedList(assetEmployee, request, assetEmployee
					.getUserEmpId());
			model.terminate();
			return "input";
		} catch (Exception e) {
			logger.error("Exception in getApproved list");
			return null;
		}
	}

	/*
	 * Rejected List
	 */
	public String getRejectedList() {
		try {
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			assetEmployee.setListType("rejected");
			// assetEmployee.setListLengthRejected("true");
			getNavigationPanel(1);
			model.getRejectedList(assetEmployee, request, assetEmployee
					.getUserEmpId());
			model.terminate();
			return "input";
		} catch (Exception e) {
			logger.error("Exception in getRejectedList");
			return null;
		}
	}

	/*
	 * Assigned List
	 */
	public String getAssignedList() {
		try {
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			assetEmployee.setListType("assigned");
			// assetEmployee.setListLengthRejected("true");
			getNavigationPanel(1);
			model.getAssignedList(assetEmployee, request, assetEmployee
					.getUserEmpId());
			model.terminate();
			return "input";
		} catch (Exception e) {
			logger.error("Exception in getRejectedList");
			return null;
		}
	}

	public String f9action() throws Exception {

		/*
		 * String query = " SELECT ASSMT_CODE,EMP_TOKEN,(TITLE_NAME||'
		 * '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||
		 * HRMS_EMP_OFFC.EMP_LNAME),EMP_CODE FROM HRMS_ASSMT " +" INNER JOIN
		 * HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSMT.EMP_CODE)" +" LEFT
		 * JOIN HRMS_TITLE ON(HRMS_TITLE
		 * .TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)" ;
		 */
		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(ASSET_APPL_DATE,'DD-MM-YYYY'),"
				+ " CASE WHEN ASSET_STATUS='P' AND ASSET_APPL_LEVEL!=1   THEN 'Forwarded' WHEN ASSET_STATUS='P' THEN 'Pending' "
				+ " WHEN ASSET_STATUS='A' THEN 'Approved' WHEN ASSET_STATUS='R' THEN 'Rejected' WHEN ASSET_STATUS='S' THEN 'Assigned' WHEN ASSET_STATUS='B' THEN 'SentBack' else '' end,CASE WHEN ASSET_STATUS='P' AND ASSET_APPL_LEVEL!=1   THEN 'F' else ASSET_STATUS  end, "
				+ " ASSET_EMP_ID,ASSET_APPL_CODE, ASSET_APPL_APPROVER FROM HRMS_ASSET_APPLICATION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_APPLICATION.ASSET_EMP_ID) ";
		// + " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE
		// .TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";

		logger.info("assetEmployee.isGeneralFlag=="
				+ assetEmployee.isGeneralFlag());
		if (assetEmployee.isGeneralFlag()) {
			query += " WHERE EMP_ID=" + assetEmployee.getUserEmpId();
		}
		query += " ORDER BY ASSET_APPL_DATE desc ";
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("assetAppDate"), getMessage("assetStatus") };
		String[] headerWidth = { "10", "30", "30", "30" };

		String[] fieldNames = { "empToken", "empName", "assignDate1", "status",
				"status", "empCode", "code", "approverCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };

		String submitFlag = "true";

		String submitToMethod = "AssetEmployee_showDetails.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionEmp() throws Exception {

		/*
		 * String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)" +" FROM
		 * HRMS_EMP_OFFC ORDER BY EMP_ID ";
		 */
		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  ";

		query += getprofileQuery(assetEmployee);

		query += " AND EMP_STATUS='S' ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empToken", "empName", "empCode" };

		int[] columnIndex = { 0, 1, 2 };

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "true";

		String submitToMethod = "AssetEmployee_reset1.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * public String f9actionAsst() throws Exception {
	 * 
	 * String query = " SELECT
	 * ASSET_MASTER_CODE,ASSET_CATEGORY_NAME,ASSET_INVENTORY_CODE,ASSET_SUBTYPE_NAME,ASSET_AVAILABLE
	 * FROM HRMS_ASSET_MASTER " +" INNER JOIN HRMS_ASSET_CATEGORY ON
	 * (HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE =
	 * HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE)" +" INNER JOIN
	 * HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE =
	 * HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE)";
	 * 
	 * String[] headers={"Asset Code","Asset Name","Inventory Code","Asset
	 * Sub-Type"};
	 * 
	 * String[] headerWidth={"10","30","30","30"};
	 * 
	 * String[]
	 * fieldNames={"asstCode1","asstHdType1","invCode1","assetSubType","assetAvailable"};
	 * 
	 * 
	 * int[] columnIndex={0,1,2,3,4};
	 * 
	 * 
	 * String submitFlag = "false";
	 * 
	 * 
	 * String submitToMethod="";
	 * 
	 * 
	 * setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	 * 
	 * return "f9page"; }
	 */
	public String f9actionCategory() throws Exception {

		String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,''),NVL('',' '),NVL('',' ') FROM HRMS_ASSET_CATEGORY "
				+ "  ORDER BY ASSET_CATEGORY_CODE ";

		String[] headers = { getMessage("assetCode"), getMessage("assetCat") };
		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "asstCode1", "asstHdType1", "subTypeCode",
				"assetSubType" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionSubType() throws Exception {

		logger.info("asset category code in F9==="
				+ assetEmployee.getAsstCode1());
		/*String query = " SELECT DISTINCT HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME,DECODE(ASSET_INVENTORY_TYPE,'S','false','I','true'),UNIT_NAME  FROM HRMS_ASSET_MASTER "
				+ " LEFT JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE)"
				+ " LEFT JOIN HRMS_ASSET_MASTER_DTL ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
				+ " LEFT JOIN HRMS_UNIT_MASTER ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT = HRMS_UNIT_MASTER.UNIT_CODE) "
				+ " LEFT JOIN HRMS_WAREHOUSE_BRANCH ON(HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH )"
				+ " WHERE HRMS_EMP_OFFC.EMP_ID ="
				+ assetEmployee.getEmpCode()
				+ " AND HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE="
				+ assetEmployee.getAsstCode1();*/
		
		String query = " SELECT DISTINCT HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE, HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_NAME,"
			+" DECODE(HRMS_ASSET_SUBTYPE.ASSET_INVENTORY_TYPE,'S','False','I','True'), HRMS_UNIT_MASTER.UNIT_NAME"
			+" FROM HRMS_ASSET_SUBTYPE"
			+" LEFT JOIN HRMS_ASSET_MASTER ON HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE = HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE"
			+" LEFT JOIN HRMS_UNIT_MASTER ON HRMS_UNIT_MASTER.UNIT_CODE = HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT"
			+" WHERE HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE= "+ assetEmployee.getAsstCode1();

		String[] headers = { getMessage("acode"), getMessage("assetSubType") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "subTypeCode", "assetSubType", "assetInvType",
				"assetUnit" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "AssetEmployee_showItem1.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * public String reset(){ if(!assetEmployee.isGeneralFlag()){
	 * assetEmployee.setCode(""); assetEmployee.setEmpName("");
	 * assetEmployee.setEmpCode(""); assetEmployee.setSrNo("");
	 * assetEmployee.setInvCode(""); assetEmployee.setAsstHdType("");
	 * assetEmployee.setAssignDate(""); assetEmployee.setReturnDate("");
	 * assetEmployee.setInvCode1(""); assetEmployee.setAsstHdType1("");
	 * assetEmployee.setAssignDate1(""); assetEmployee.setReturnDate1("");
	 * assetEmployee.setEmpToken(""); } return SUCCESS; }
	 */
	public String reset() {
		assetEmployee.setCode("");
		if (!assetEmployee.isGeneralFlag()) {
			assetEmployee.setEmpName("");
			assetEmployee.setEmpCode("");
			assetEmployee.setEmpToken("");
			assetEmployee.setBranch("");
			assetEmployee.setDesig("");
			assetEmployee.setDept("");
		}
		assetEmployee.setAsstHdType1("");
		assetEmployee.setAssignDate1("");
		assetEmployee.setSubTypeCode("");
		assetEmployee.setAssetSubType("");
		assetEmployee.setAssetRequired("");
		assetEmployee.setTableLength("");
		assetEmployee.setParaId("");
		assetEmployee.setComments("");
		assetEmployee.setApproverCode("");
		getNavigationPanel(2);

		return SUCCESS;
	}

	public String showAsset() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		model.showRecord(assetEmployee, request);
		assetEmployee.setAsstCode1("");
		assetEmployee.setAsstHdType1("");
		assetEmployee.setInvCode1("");
		assetEmployee.setAssignDate1("");
		assetEmployee.setReturnDate1("");
		model.terminate();
		return SUCCESS;
	}

	/*
	 * ADD THE ASSET LIST IN THE TABLE
	 * 
	 */
	public String addItem() {

		String returnPage = "";
		try {
			String[] srNo = request.getParameterValues("srNo");
			String[] assetCode = request.getParameterValues("assetCode");
			String[] subType = request
					.getParameterValues("assetSubTypeIterator");
			String[] subTypeCode = request
					.getParameterValues("subTypeCodeIterator");
			String[] assetRequired = request
					.getParameterValues("assetRequiredIterator");
			String[] asstHdType = request.getParameterValues("asstHdType");
			String[] asstUnit = request.getParameterValues("assetUnitIterator");
			String[] partialIt = request.getParameterValues("partialAssignIt");
			String[] invType = request
					.getParameterValues("assetInvTypeIterator");
			logger.info("inside addItem method name" + request.getMethod());
			AssetEmployeeModel model = new AssetEmployeeModel();
			assetEmployee.setStatus(assetEmployee.getHiddenStatus());
			logger.info("inside addItem");
			
			boolean result = false;
			model.initiate(context, session);
			if (assetEmployee.getParaId().equals("")) {
				model.addItem(assetEmployee, srNo, assetCode, asstHdType,
						subType, subTypeCode, assetRequired, asstUnit,
						partialIt, invType);
			} else {
				model.editItem(assetEmployee, assetCode, asstHdType,
						subTypeCode, subType, assetRequired, asstUnit,
						partialIt, invType);
			}
			logger.info("result length" + result);
			model.setKeepInformList(request, assetEmployee);
			returnPage = "";
			if (assetEmployee.getIsAssign().equals("true")) {
				returnPage = "AssignmentEdit";
				showAssignedList();
			} else {
				returnPage = "success";
			}
			reset1();
			/*
			 * logger.info("before addAsset");
			 * if(assetEmployee.getCode().equals("")){
			 * 
			 * result=model.addAsset(assetEmployee); }else{
			 * result=model.modAsset(assetEmployee); }
			 */
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPage;
	}

	public String reset1() {
		try {
			setApproverList(assetEmployee.getEmpCode());
			logger.info("status =========================================  "
					+ assetEmployee.getStatus() + "======");
			getNavigationPanel(2);
			if (!assetEmployee.getCode().equals("")) {
				getNavigationPanel(3);
				if (assetEmployee.getStatus() != null
						&& (assetEmployee.getStatus().equals("P") || assetEmployee
								.getStatus().equals("F"))) {
					getNavigationPanel(8);
					assetEmployee.setApproverCommentsFlag("true");
				}
				if (assetEmployee.getStatus() != null
						&& assetEmployee.getStatus().equals("B")) {
					getNavigationPanel(5);
				}
			}
			assetEmployee.setAsstCode1("");
			assetEmployee.setAsstHdType1("");
			assetEmployee.setInvCode1("");
			assetEmployee.setSubTypeCode("");
			assetEmployee.setAssetSubType("");
			assetEmployee.setAssetRequired("");
			assetEmployee.setAssetUnit("");
			assetEmployee.setParaId("");
			assetEmployee.setAssetInvType("");
			// assetEmployee.setComments("");
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			model.getEmployeeDetails(assetEmployee);
			model.getApproverComment(assetEmployee);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String showItem1() {
		String[] assetCode = request.getParameterValues("assetCode");
		String[] subType = request.getParameterValues("assetSubTypeIterator");
		String[] subTypeCode = request
				.getParameterValues("subTypeCodeIterator");
		String[] assetRequired = request
				.getParameterValues("assetRequiredIterator");
		String[] asstHdType = request.getParameterValues("asstHdType");
		String[] asstUnit = request.getParameterValues("assetUnitIterator");
		String[] partialIt = request.getParameterValues("partialAssignIt");
		String[] invType = request.getParameterValues("assetInvTypeIterator");
		AssetEmployeeModel model = new AssetEmployeeModel();

		assetEmployee.setStatus(assetEmployee.getHiddenStatus());
		String returnPage = "";

		try {
			for (int i = 0; i < invType.length; i++) {
				logger.info("invType in showItem1 action==" + invType[i]);
			}

			model.initiate(context, session);

			model.showList1(assetEmployee, assetCode, asstHdType, subTypeCode,
					subType, assetRequired, asstUnit, partialIt, invType);
			assetEmployee.setAssetRequired("");

			logger.info("assign list in action "
					+ assetEmployee.getAssignList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (assetEmployee.getIsAssign().equals("true")) {
			returnPage = "AssignmentEdit";
			showAssignedList();
		} else {
			returnPage = "success";
		}
		model.terminate();
		logger.info("returnpage==" + returnPage);
		return returnPage;
	}

	public String showItem() {
		String[] srNo = request.getParameterValues("srNo");
		String[] assetCode = request.getParameterValues("assetCode");
		String[] asstHdType = request.getParameterValues("asstHdType");
		String[] subType = request.getParameterValues("assetSubTypeIterator");
		String[] subTypeCode = request
				.getParameterValues("subTypeCodeIterator");
		String[] assetRequired = request
				.getParameterValues("assetRequiredIterator");
		String[] partialIt = request.getParameterValues("partialAssignIt");
		String[] invType = request.getParameterValues("assetInvTypeIterator");
		assetEmployee.setStatus(assetEmployee.getHiddenStatus());
		logger.info("inside addItem");
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		model.showList(assetEmployee, srNo, assetCode, asstHdType, subType,
				subTypeCode, assetRequired, partialIt, invType);

		assetEmployee.setAsstCode1("");
		assetEmployee.setAsstHdType1("");
		assetEmployee.setInvCode1("");
		assetEmployee.setSubTypeCode("");
		assetEmployee.setAssetSubType("");
		assetEmployee.setAssetRequired("");
		/*
		 * logger.info("before addAsset");
		 * if(assetEmployee.getCode().equals("")){
		 * 
		 * result=model.addAsset(assetEmployee); }else{
		 * result=model.modAsset(assetEmployee); }
		 */
		model.terminate();

		return SUCCESS;
	}

	public String showDetails() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		model.showRecord(assetEmployee, request);
		model.terminate();
		return SUCCESS;
	}

	/*
	 * public void prepare_withLoginProfileDetails() throws Exception {
	 * model.initiate(context,session); logger.info("general
	 * flag=="+assetEmployee.isGeneralFlag());
	 * if(assetEmployee.isGeneralFlag()){
	 * assetEmployee.setEmpCode(assetEmployee.getUserEmpId());
	 * model.setEmpData(assetEmployee); model.showRecord(assetEmployee,
	 * request); } model.terminate(); }
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		logger.info("general flag==" + assetEmployee.isGeneralFlag());
		if (assetEmployee.isGeneralFlag()) {
			assetEmployee.setEmpCode(assetEmployee.getUserEmpId());
			// model.setEmpData(assetEmployee);
			model.getEmployeeDetails(assetEmployee);
		}
		//System.out.println("The employe Code : ---------  : "+assetEmployee.getEmpCode());
		//setApproverList(assetEmployee.getEmpCode());
		model.terminate();
	}

	/*
	 * update function is used to update asset details by approver
	 */
	public String update() {

		try {
			logger.info("update function");
			String[] assetCode = request.getParameterValues("assetCode");
			logger.info("assetCode length - " + assetCode.length);
			String[] subType = request
					.getParameterValues("subTypeCodeIterator");
			logger.info("subType length - " + subType.length);
			String[] assetRequired = request
					.getParameterValues("assetRequiredIterator");
			logger.info("assetRequired length - " + assetRequired.length);
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			boolean result = model.updateAssetDetails(assetEmployee, assetCode,
					subType, assetRequired);
			if (result)
				addActionMessage("Record updated Successfully");
			else
				addActionMessage("Record can't update");
			getNavigationPanel(8);
			showItem1();
			model.getApproverComment(assetEmployee);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exeption Update function --- " + e);
			return null;
		}
	}

	public String save() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		String result = "";
		model.initiate(context, session);

		String[] flag1 = request.getParameterValues("assetCode");
		// String[] assignedDate = request.getParameterValues("assignDate");
		// String[] returnDate = request.getParameterValues("returnDate");
		String[] subtype = request.getParameterValues("subTypeCodeIterator");
		String[] assetRequired = request
				.getParameterValues("assetRequiredIterator");

		String assetAssignDate[] = request
				.getParameterValues("assetAssignDate");

		Object[][] empFlow = generateEmpFlow(assetEmployee.getEmpCode(),
				"Asset", 1);
		/*
		 * get the assigner code i.e. responsible person for warehouse to which
		 * applicant employee belongs
		 * 
		 * method:getAssignerCode
		 * 
		 */
		// String assigner = model.getAssignerCode(assetEmployee);
		// logger.info("assigner code ===" + assigner);
		logger.info("empFlow - " + empFlow);
		try {
			if ((empFlow == null || (empFlow.equals("null") || empFlow.length == 0))
					&& !assetEmployee.getHiddenStatus().equals("D")) {
				addActionMessage("Reporting structure not defined for the employee\n"
						+ assetEmployee.getEmpName());
				addActionMessage("Please contact HR manager.");
				// showItem();
				getNavigationPanel(1);
				assetEmployee.setListType("pending");
				model.getAllTypeOfApplications(assetEmployee, request,
						assetEmployee.getUserEmpId());
				model.terminate();
				
				if (assetEmployee.getSource().equals("mymessages")) {
					return "mymessages";
				} else if (assetEmployee.getSource().equals("myservices")) {
					return "serviceJsp";
				} else {
					return "input";
				}
 

			} else
				result = model.save(assetEmployee, flag1, subtype,
						assetRequired, empFlow, request);
			if (result.equals("Record can't be saved.")) {
				showItem1();
				addActionMessage(result);
				getNavigationPanel(2);
				if (assetEmployee.getSource().equals("mymessages")) {
					return "mymessages";
				} else if (assetEmployee.getSource().equals("myservices")) {
					return "serviceJsp";
				} else {
					return SUCCESS;
				}

			}
			if (result.equals("Record can't be updated.")) {
				showItem1();
				addActionMessage(result);
				getNavigationPanel(3);
				
				if (assetEmployee.getSource().equals("mymessages")) {
					return "mymessages";
				} else if (assetEmployee.getSource().equals("myservices")) {
					return "serviceJsp";
				} else {
					return SUCCESS;
				}
				 
			}

			if ((result.equals("Record saved successfully.") || result
					.equals("Record updated successfully."))
					&& assetEmployee.getHiddenStatus().equals("P")) {
				// ------------------------Process Manager
				// Alert------------------------start
				try {
					addActionMessage("Application sent for approval.\nReference ID:-"
							+ assetEmployee.getReferenceId().trim());
					String applnID = assetEmployee.getCode();
					String module = "Asset";
					String applicant = assetEmployee.getEmpCode();
					String approver = String.valueOf(empFlow[0][0]);
					String applnDate = assetEmployee.getAssignDate1();
					String keepInfo[] = request
							.getParameterValues("keepInformedEmpId");

					/**
					 * Remove Process manager alert entry from mypage
					 */
					try {
						MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
						processAlerts.initiate(context, session);
						processAlerts.removeProcessAlert(assetEmployee
								.getCode(), "Asset");
						processAlerts.terminate();
					} catch (Exception e) {
						e.printStackTrace();
					}

					//Mail Code
					sendApplicationAlert(applnID, module, applicant, approver,
							applnDate, keepInfo, assetEmployee.getUserEmpId());
				} catch (Exception e) {
					logger.error(e);
				}
				getNavigationPanel(1);
				model.getAllTypeOfApplications(assetEmployee, request,
						assetEmployee.getUserEmpId());
				assetEmployee.setListType("pending");
				model.terminate();
				
				if (assetEmployee.getSource().equals("mymessages")) {
					return "mymessages";
				} else if (assetEmployee.getSource().equals("myservices")) {
					return "serviceJsp";
				} else {
					return "input";
				}
			 
				//return "input";
				// ------------------------Process Manager
				// Alert------------------------end
			}// End of if
			else {
				addActionMessage("Application saved successfully.\nReference ID:-"
						+ assetEmployee.getReferenceId().trim());
				model.showRecord(assetEmployee, request);
				getNavigationPanel(3);
				showItem1();
				//return SUCCESS;

				/**
				 * call sendProcessManagerAlertDraft for saving draft entry into
				 * process manager alert table.
				 */

				sendProcessManagerAlertDraft();
				if (assetEmployee.getSource().equals("mymessages")) {
					return "mymessages";
				} else if (assetEmployee.getSource().equals("myservices")) {
					return "serviceJsp";
				} else {
					return SUCCESS;
				}
			}// End of else

		} catch (Exception e) {
			addActionMessage("Reporting structure not defined for the employee\n"
					+ assetEmployee.getEmpName());
			addActionMessage("Please contact HR manager.");
			logger.error("Exception " + e);
			e.printStackTrace();
			showItem1();
			getNavigationPanel(3);
			if (assetEmployee.getSource().equals("mymessages")) {
				return "mymessages";
			} else if (assetEmployee.getSource().equals("myservices")) {
				return "serviceJsp";
			} else {
				return SUCCESS;
			}
		}
		/*
		 * if(assetEmployee.isGeneralFlag()){ model.showRecord(assetEmployee,
		 * request, 1); }else
		 */

		// showItem();
		// return SUCCESS;
	}

	public void deleteProcessManagerAlert(String moduleName,
			String applicationCode) {
		try {
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ applicationCode
					+ " AND UPPER(ALERT_MODULE) LIKE '"
					+ moduleName.trim().toUpperCase() + "'";
			model.getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String delete() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		boolean result;
		// result = model.delRecord(assetEmployee);
		result = model.cancelRecord(assetEmployee);
		if (result) {

			deleteProcessManagerAlert("Asset", assetEmployee.getCode());

			addActionMessage("Record deleted successfully.");
			// ------------------------Process Manager
			// Alert------------------------start
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			String applnID = assetEmployee.getCode();
			String module = "Asset";
			processAlerts.removeProcessAlert(applnID, module);
			// ------------------------Process Manager
			// Alert------------------------end
			reset();
		} else {
			addActionError("Record can't be deleted.");
		}
		getNavigationPanel(1);
		assetEmployee.setListType("pending");
		model.getAllTypeOfApplications(assetEmployee, request, assetEmployee
				.getUserEmpId());
		model.terminate();

		if (assetEmployee.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "input";
		}
		//return "input";
	}

	/*
	 * method name : withdrawApplication purpose : to withdraw conference
	 * application return type : String parameter : none
	 */
	public String withdrawApplication() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		try {
			boolean result = model.withdrawApplication(assetEmployee);
			logger.info("Withdraw Application Result -------- " + result);
			if (result) {
				sendProcessManagerAlertForWithdraw();
				addActionMessage("Application has been withdrawn successfully");
			} else {
				addActionMessage("Application could not be withdrawn ");
			}
		} catch (Exception e) {
			logger.error("Exception in WithdrawApplicaton");
		}
		getNavigationPanel(1);
		assetEmployee.setListType("pending");
		model.getAllTypeOfApplications(assetEmployee, request, assetEmployee
				.getUserEmpId());
		model.terminate();
		
		if (assetEmployee.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (assetEmployee.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return INPUT;
		}
	}

	/*
	 * public String edit() throws Exception { model.initiate(context, session);
	 * model.getRecord(assetEmployee);
	 * 
	 * model.terminate(); model.showRecord(assetEmployee, request); return
	 * SUCCESS; }
	 */

	public String remove() {
		setApproverList(assetEmployee.getEmpCode());
		String[] categoryCode = request.getParameterValues("assetCode");
		String[] categoryName = request.getParameterValues("asstHdType");
		String[] subTypeCode = request
				.getParameterValues("subTypeCodeIterator");
		String[] subTypeName = request
				.getParameterValues("assetSubTypeIterator");
		String[] quantityReq = request
				.getParameterValues("assetRequiredIterator");
		String[] assetUnit = request.getParameterValues("assetUnitIterator");
		String[] partialIt = request.getParameterValues("partialAssignIt");
		String[] invType = request.getParameterValues("assetInvTypeIterator");
		assetEmployee.setStatus(assetEmployee.getHiddenStatus());

		String returnPage = "";
		if (assetEmployee.getIsAssign().equals("true")) {
			returnPage = "AssignmentEdit";
		} else {
			returnPage = "success";
		}
		AssetEmployeeModel model = new AssetEmployeeModel();
		showAssignedList();
		model.initiate(context, session);
		model.removeItem(assetEmployee, categoryCode, categoryName,
				subTypeCode, subTypeName, quantityReq, assetUnit, partialIt,
				invType);
		assetEmployee.setParaId("");
		getNavigationPanel(2);

		if (!assetEmployee.getCode().equals(""))
			getNavigationPanel(3);
		if (assetEmployee.getStatus() != null
				&& assetEmployee.getStatus().equals("P")
				|| assetEmployee.getStatus().equals("F")) {
			// getNavigationPanel(10);
			getNavigationPanel(8);
			assetEmployee.setApproverCommentsFlag("true");
		}
		if (assetEmployee.getStatus() != null
				&& assetEmployee.getStatus().equals("B")) {
			getNavigationPanel(5);
		}
		model.setKeepInformList(request, assetEmployee);
		model.getApproverComment(assetEmployee);
		model.terminate();
		return returnPage;
	}

	/*
	 * 
	 * show the details for the approver method: callByApprover()
	 * 
	 */
	public String callByApprover() {
		try {

			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			assetEmployee.setSource(source);

			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			String assetCode = request.getParameter("hiddenAssetCode") != null ? request
					.getParameter("hiddenAssetCode")
					: "";

			//String employeeCode = request.getParameter("empCode")!=null?request.getParameter("empCode"):"";
			//assetEmployee.setEmpCode(employeeCode);
			//int applicationLevel = assetEmployee.getApplicationLevel();
			int applicationLevel = Integer.parseInt(request
					.getParameter("applicationLevel") != null ? request
					.getParameter("applicationLevel") : "1");

			System.out.println("applicationLevel   " + applicationLevel);

			assetEmployee.setCode(assetCode);
			assetEmployee.setApproverCommentsFlag("true");
			model.showRecord(assetEmployee, request);
			model.showEmpDetails(assetEmployee);
			model.getEmployeeDetails(assetEmployee);
			model.setApproverComments(assetEmployee);
			// assetEmployee.setIsApprove("true");
			// assetEmployee.setCommentFlag("true");

			String query = " SELECT ASSET_APPL_APPROVER,ASSET_STATUS FROM HRMS_ASSET_APPLICATION WHERE ASSET_EMP_ID="
					+ assetEmployee.getEmpCode()
					+ " AND ASSET_APPL_CODE="
					+ assetEmployee.getCode();

			Object approverIdObj[][] = model.getSqlModel().getSingleResult(
					query);

			Object[][] empFlow = generateEmpFlow(assetEmployee.getEmpCode(),
					"Asset", assetEmployee.getApplicationLevel());

			String approverId = "";
			if (empFlow != null && empFlow.length > 0) {

				approverId = String.valueOf(empFlow[0][0]);
			}

			String isApproverLogin = "";

			if (approverIdObj != null && approverIdObj.length > 0) {

				/**
				 * to find approver and alternate approver login.
				 */
				String empCode = "SELECT ASSET_EMP_ID ,ASSET_APPL_APPROVER ,ASSET_ALTER_APPROVER FROM HRMS_ASSET_APPLICATION "
						+ " WHERE ASSET_APPL_CODE=" + assetEmployee.getCode();

				Object empCodeObj[][] = model.getSqlModel().getSingleResult(
						empCode);
				if (empCodeObj != null && empCodeObj.length > 0) {

					if (String.valueOf(empCodeObj[0][1]).equals(
							assetEmployee.getUserEmpId())
							|| String.valueOf(empCodeObj[0][2]).equals(
									assetEmployee.getUserEmpId())) {
						isApproverLogin = "Y";
					}
				}

				if (String.valueOf(approverIdObj[0][0]).equals(approverId)
						&& String.valueOf(approverIdObj[0][1]).equals("P")) {

					System.out.println("In if loop------------------");
					getNavigationPanel(8);
					assetEmployee.setKeepInfoFlag("false");
				} else {
					assetEmployee.setEnableAll("N");
					assetEmployee.setApproverCommentsFlag("false");
					assetEmployee.setKeepInfoFlag("false");
					getNavigationPanel(11);
				}
				if (assetEmployee.getStatus().equals("A")
						|| assetEmployee.getStatus().equals("R")) {
					assetEmployee.setEnableAll("N");
					assetEmployee.setApproverCommentsFlag("false");
					assetEmployee.setKeepInfoFlag("false");
					getNavigationPanel(11);
				}
				if (assetEmployee.getStatus().equals("S")) {
					assetEmployee.setEnableAll("N");
					assetEmployee.setAssignCommentsFlag("true");
					assetEmployee.setEnableAll("N");
					getNavigationPanel(11);
				}
				System.out.println("isApproverLogin      " + isApproverLogin);
				if (isApproverLogin.equals("Y")) {
					//getNavigationPanel(11);
				}

				assetEmployee.setIsApprove("true");
			}
			setApproverList(assetEmployee.getEmpCode());//
			// logger.info("assetEmployee.getIsApprove() ------
			// "+assetEmployee.getIsApprove());
			// logger.info("assetEmployee.getIsSentBack() -----
			// "+assetEmployee.getIsSentBack());
			// logger.info("assetEmployee.getCommentFlag() ----
			// "+assetEmployee.getCommentFlag());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void showApplicationDetails() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		model.showEmpDetails(assetEmployee);

		if (assetEmployee.getIsAssign().equals("true")) {
			model.showAssignedRecord(assetEmployee, request);
		} else {
			model.showRecord(assetEmployee, request);
		}
		model.setApproverComments(assetEmployee);
		model.checkAssignment(assetEmployee);
		model.terminate();
	}

	public void showAssignmentDetails() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		model.showEmpDetails(assetEmployee);
		model.showAssignedRecord(assetEmployee, request);
		model.setApproverComments(assetEmployee);
		model.checkAssignment(assetEmployee);
		model.terminate();
	}

	public String callByAssigner() {
		
		String source = request.getParameter("source");
		System.out.println("source--------------" + source);
		assetEmployee.setSource(source);
		
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		String assetCode = request.getParameter("hiddenAssetCode");
		assetEmployee.setCode(assetCode);
		showAssignmentDetails();
		assetEmployee.setIsAssign("true");
		assetEmployee.setIsApprove("false");
		model.terminate();
		
		
		return "AssignmentEdit";
	}

	public String saveAssignment() {
		try {
			
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			boolean result = false;
			String inventoryCode = "";
			String masterCode = "";
			logger.info("assetEmployee.getTableLength()==="
					+ assetEmployee.getTableLength());
			String invCodeArray[] = new String[Integer.parseInt(assetEmployee
					.getTableLength())];
			String masterCodeArray[] = new String[Integer
					.parseInt(assetEmployee.getTableLength())];
			String assetitemcodeArray[] = new String[Integer
					.parseInt(assetEmployee.getTableLength())];
			String quantityAssigned[] = request
					.getParameterValues("quantityAssigned");
			String categoryCode[] = request.getParameterValues("assetCode");
			String subTypeCode[] = request
					.getParameterValues("subTypeCodeIterator");
			String applCode[] = request.getParameterValues("applCode");
			String invType[] = request.getParameterValues("returnFlagIterator");
			String assetAssignDate[] = request
					.getParameterValues("assetAssignDate");
			String assignQuantity[] = request
					.getParameterValues("quantityAssigned");
			String assetitemcode = "";
			for (int i = 1; i <= Integer.parseInt(assetEmployee
					.getTableLength()); i++) {
				inventoryCode = request.getParameter("inventoryCode" + i);
				masterCode = request.getParameter("assetMasterCode" + i);
				assetitemcode = request.getParameter("assetItemcode" + i);
				invCodeArray[i - 1] = inventoryCode;
				masterCodeArray[i - 1] = masterCode;
				assetitemcodeArray[i - 1] = assetitemcode;
			}
			result = model.saveAssignment(assetEmployee, invCodeArray,
					quantityAssigned, categoryCode, subTypeCode,
					masterCodeArray, applCode, invType, assetAssignDate,
					assignQuantity, assetitemcodeArray);

			if (result) {
				addActionMessage("Asset assigned successfully.");
				//reset();
			} else {
				addActionMessage("Asset can't be assigned.");
				showList();
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (assetEmployee.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (assetEmployee.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return showSavedAssignment();
		}
		
	}

	public String rejectApplication() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		boolean result = false;
		result = model.rejectApplication(assetEmployee);

		model.terminate();

		if (result) {
			addActionMessage("Asset application rejected successfully.");
			// ------------------------Process Manager
			// Alert------------------------start
			try {
				String applnID = assetEmployee.getCode();
				String module = "Asset";
				String applicant = assetEmployee.getEmpCode();
				String approver = assetEmployee.getUserEmpId();
				String applnDate = assetEmployee.getAssignDate1();
				sendBackApplicationAlert(applnID, module, applicant, approver,
						applnDate, "reject");
			} catch (Exception e) {
				logger.error(e);
			}

		} else {
			addActionMessage("Application can't be rejected.");

		}
		showApplicationDetails();
		
		if (assetEmployee.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (assetEmployee.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return SUCCESS;
		}
		
		
	}

	public String sendBackToEmp() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		boolean result = false;
		result = model.sendBackToEmp(assetEmployee);

		model.terminate();

		if (result) {
			addActionMessage("Asset application has been sent back to Employee.");
			// ------------------------Process Manager
			// Alert------------------------start
			try {
				String applnID = assetEmployee.getCode();
				String module = "Asset";
				String applicant = assetEmployee.getEmpCode();
				String approver = assetEmployee.getUserEmpId();
				String applnDate = assetEmployee.getAssignDate1();
				sendBackApplicationAlert(applnID, module, applicant, approver,
						applnDate, "sendBack");
			} catch (Exception e) {
				logger.error(e);
			}
			// ------------------------Process Manager
			// Alert------------------------end

		} else {
			addActionMessage("Application can't be sent back.");

		}
		showApplicationDetails();
		if (assetEmployee.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (assetEmployee.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return input();
		}
		
	}

	public String showForAssignment() {
		try {
			
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			assetEmployee.setSource(source);
			
			String assetCode = request.getParameter("hiddenAssetCode");
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			assetEmployee.setCode(assetCode);
			model.showEmpDetails(assetEmployee);
			model.showForAssignment(assetEmployee);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "assignAsset";
	}

	public String showSavedAssignment() {
		try {
			String assetCode = assetEmployee.getCode();
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			assetEmployee.setCode(assetCode);
			model.showEmpDetails(assetEmployee);
			model.showForAssignment(assetEmployee);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "assignAsset";
	}

	public String showList() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);

		logger.info("assetEmployee.getTableLength()==="
				+ assetEmployee.getTableLength());
		String categoryCode[] = request.getParameterValues("assetCode");
		String subTypeCode[] = request
				.getParameterValues("subTypeCodeIterator");
		String returnFlag[] = request.getParameterValues("returnFlagIterator");
		String categoryName[] = request.getParameterValues("asstHdType");
		String subTypeName[] = request
				.getParameterValues("assetSubTypeIterator");
		String requiredQuantity[] = request
				.getParameterValues("assetRequiredIterator");
		String[] partialIt = request.getParameterValues("partialAssignIt");
		String[] invType = request.getParameterValues("assetInvTypeIterator");

		model.showList(assetEmployee, categoryCode, categoryName, subTypeCode,
				subTypeName, requiredQuantity, returnFlag, partialIt, invType);

		model.terminate();
		return "assignAsset";
	}

	public String showAssignedList() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);

		String categoryCode[] = request.getParameterValues("assetCodeAssigned");
		String subTypeCode[] = request
				.getParameterValues("subTypeCodeIteratorAssigned");
		String categoryName[] = request
				.getParameterValues("asstHdTypeAssigned");
		String subTypeName[] = request
				.getParameterValues("assetSubTypeIteratorAssigned");
		String assignedQuantity[] = request
				.getParameterValues("assetAssignedIterator");
		String[] assetUnit = request
				.getParameterValues("assetUnitIteratorAssigned");

		model.showAssignedList(assetEmployee, categoryCode, categoryName,
				subTypeCode, subTypeName, assignedQuantity, assetUnit);
		model.terminate();
		return "AssignmentEdit";
	}

	/*
	 * select Inventory for assignment method: f9actionInventory();
	 * 
	 */
	public String f9actionInventory() throws Exception {
		String invCode = "";

		/*
		 * filter the Inventory code from selection which is already present in
		 * the list
		 * 
		 * 
		 */
		try {
			for (int i = 1; i <= Integer.parseInt(assetEmployee
					.getTableLength()); i++)
				invCode += "'" + request.getParameter("inventoryCode" + i)
						+ "',";
			invCode = (invCode.substring(0, invCode.length() - 1)).replaceAll(
					"''", "' '");
		} catch (Exception e) {
			invCode = "' '";
		}
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session); /*
		 * get the warehouse code for which
		 * logged in person is responsible
		 * method: getWarehouseCodes
		 */
		String wareHouseCode = model.getWarehouseCodes(assetEmployee);

		logger.info("wareHouseCode in the list ===" + wareHouseCode);
		logger.info("invcode in the list ===" + invCode);
		logger.info("asset category code in f9actionInventory==="
				+ assetEmployee.getHiddenCategoryCode());
		logger.info("asset SubType code in f9actionInventory==="
				+ assetEmployee.getHiddenSubTypeCode());

		String query = "SELECT HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE,HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE,HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE,HRMS_ASSET_MASTER_DTL.ASSET_ITEM_CODE FROM HRMS_ASSET_MASTER_DTL  "
				+ " INNER JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
				+ " WHERE ASSET_ASSISGN_FLAG ='U' AND ASSET_CATEGORY_CODE ="
				+ assetEmployee.getHiddenCategoryCode()
				+ " AND HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE NOT IN ("
				+ invCode
				+ ") AND ASSET_WAREHOUSE_CODE ="
				+ assetEmployee.getEmpWarehouseCode()
				+ " AND ASSET_SUBTYPE_CODE ="
				+ assetEmployee.getHiddenSubTypeCode()
				+ " AND HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE >0  ";

		String[] headers = { getMessage("acode"),
				getMessage("asset.Inventary"), getMessage("asset.QtyAvailable") };

		String[] headerWidth = { "20", "60", "20" };

		String[] fieldNames = { "assetMasterCode" + assetEmployee.getRowId(),
				"inventoryCode" + assetEmployee.getRowId(),
				"quantityAvailable" + assetEmployee.getRowId(),
				"assetItemcode" + assetEmployee.getRowId() };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String report() {
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		model.getReport(assetEmployee, request, response);
		model.terminate();
		return null;
	}

	public String requstFromOtherWarehouse() {
		String categoryReq = request.getParameter("reqCategoryCode");
		String categoryNameReq = request.getParameter("reqCategory");
		String subTypeReq = request.getParameter("reqSubTypeCode");
		String subTypeNameReq = request.getParameter("reqSubType");

		String empCode = request.getParameter("empCode");
		logger.info("empcode in request Action" + empCode);
		assetEmployee.setEmpCode(empCode);
		assetEmployee.setReqCategory(categoryNameReq);
		assetEmployee.setReqCategoryCode(categoryReq);
		assetEmployee.setReqSubType(subTypeNameReq);
		assetEmployee.setReqSubTypeCode(subTypeReq);
		assetEmployee.setEmpWarehouseCode(request
				.getParameter("empWarehouseCode"));

		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		model.showWarehouseListForRequest(assetEmployee);
		model.terminate();

		return "requstForm";
	}

	public String saveRequest() {
		boolean result = false;
		try {
			String empCode = request.getParameter("empCode");
			String[] warehouseCode = request
					.getParameterValues("warehouseCode");
			String[] masterCodeReq = request
					.getParameterValues("masterCodeReq");
			String[] inventoryCodeReq = request
					.getParameterValues("inventoryCodeReq");
			String[] requiredReq = request.getParameterValues("requiredReq");
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			logger.info("empcode in save request Action" + empCode);
			assetEmployee.setEmpCode(empCode);
			result = model.saveRequest(assetEmployee, warehouseCode,
					masterCodeReq, inventoryCodeReq, requiredReq);
			if (result) {
				addActionMessage("Request forwarded successfully.");
			} else {
				addActionMessage("Request cant be forwarded.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String transferAsset() {
		logger.info("inside transferAsset action");
		boolean result = false;
		String invCodeArray[] = new String[Integer.parseInt(request
				.getParameter("tableList"))];
		String masterCodeArray[] = new String[Integer.parseInt(request
				.getParameter("tableList"))];
		String quantityAssigned[] = request
				.getParameterValues("quantityAssigned");
		String sourceWarehouse[] = request
				.getParameterValues("sourceWarehouse");
		String destiWarehouse[] = request.getParameterValues("destWarehouse");
		String inventoryCode = "";
		String masterCode = "";
		String reqCode[] = request.getParameterValues("reqCode");
		String invType[] = request.getParameterValues("returnFlagIterator");
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		for (int i = 1; i <= Integer.parseInt((request
				.getParameter("tableList"))); i++) {
			inventoryCode = request.getParameter("inventoryCode" + i);
			masterCode = request.getParameter("assetMasterCode" + i);
			invCodeArray[i - 1] = inventoryCode;
			masterCodeArray[i - 1] = masterCode;
		}
		result = model.updateWarehouseCode(assetEmployee, invCodeArray,
				masterCodeArray, quantityAssigned, sourceWarehouse, reqCode,
				destiWarehouse);
		if (result) {
			addActionMessage("Asset transfered successfully.");
		} else {
			addActionMessage("Asset cant be transfred.");
		}
		model.terminate();
		return "AssetAssignment";
	}

	public String saveByApprover() {

		String result = "";
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		assetEmployee.setStatus(assetEmployee.getHiddenStatus());
		String[] flag1 = request.getParameterValues("assetCode");
		// String[] assignedDate = request.getParameterValues("assignDate");
		// String[] returnDate = request.getParameterValues("returnDate");
		String[] subtype = request.getParameterValues("subTypeCodeIterator");
		String[] assetRequired = request
				.getParameterValues("assetRequiredIterator");
		String[] partialIt = request.getParameterValues("partialAssignIt");

		try {

			result = model.saveByApprover(assetEmployee, flag1, subtype,
					assetRequired, partialIt);

			addActionMessage(result);
		} catch (Exception e) {

			e.printStackTrace();
			showItem();

		}
		/*
		 * if(assetEmployee.isGeneralFlag()){ model.showRecord(assetEmployee,
		 * request, 1); }else
		 */
		String[] srNo = request.getParameterValues("srNo");
		String[] assetCode = request.getParameterValues("assetCode");
		String[] asstHdType = request.getParameterValues("asstHdType");
		String[] subType = request.getParameterValues("assetSubTypeIterator");
		String[] subTypeCode = request
				.getParameterValues("subTypeCodeIterator");
		String[] assetRequired1 = request
				.getParameterValues("assetRequiredIterator");
		String[] assetUnit1 = request.getParameterValues("assetUnitIterator");
		String[] invType = request.getParameterValues("assetInvTypeIterator");

		model.showList1(assetEmployee, assetCode, asstHdType, subTypeCode,
				subType, assetRequired1, assetUnit1, partialIt, invType);
		model.terminate();
		return SUCCESS;
	}

	public String saveByAssigner() {

		boolean result = false;
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session);
		assetEmployee.setStatus(assetEmployee.getHiddenStatus());
		String[] categoryCodeAssigned = request
				.getParameterValues("assetCodeAssigned");
		String[] categoryCode = request.getParameterValues("assetCode");
		if (categoryCodeAssigned == null && categoryCode == null) {
			addActionMessage("Both Assigned and Unassigned Asset Lists can't be empty");
		} else {

			try {

				result = model.saveByAssigner(assetEmployee, request);

				if (result) {
					addActionMessage(getMessage("update"));
					showApplicationDetails();
				} else {
					addActionMessage(getMessage("update.error"));
					showItem1();
					showAssignedList();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		/*
		 * if(assetEmployee.isGeneralFlag()){ model.showRecord(assetEmployee,
		 * request, 1); }else
		 */
		// showApplicationDetails();
		model.terminate();
		return "AssignmentEdit";
	}

	public void sendApplicationAlert(String applnID, String module,
			String applicant, String approver, String applnDate,
			String keepInfo[], String loginEmpId) {
		try {

			Object[][] empFlow = generateEmpFlow(applicant, "Asset", 1);

			String alertlink = "";
			String alertlinkParam = "";

			String msgType = "A";
			String level = "1";

			EmailTemplateBody template1 = new EmailTemplateBody();
			template1.initiate(context, session);

			template1.setEmailTemplate("ASSET APPLICATION -MAIL TO APPLICANT");

			template1.getTemplateQueries();

			EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); // FROM
			// EMAIL

			EmailTemplateQuery templateQuery12 = template1.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery12.setParameter(1, applicant);

			EmailTemplateQuery templateQuery13 = template1.getTemplateQuery(3);
			templateQuery13.setParameter(1, applnID);

			EmailTemplateQuery templateQuery14 = template1.getTemplateQuery(4);
			templateQuery14.setParameter(1, applnID);

			EmailTemplateQuery templateQuery15 = template1.getTemplateQuery(5);
			templateQuery15.setParameter(1, applnID);

			template1.configMailAlert();

			String keepInformId="";
			try {
				if (keepInfo != null) {
					for (int i = 0; i < keepInfo.length; i++) {
						if (i == keepInfo.length - 1) {
							keepInformId +=String.valueOf(keepInfo[i]);
						} else {
							keepInformId += String.valueOf(keepInfo[i]) + ",";

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * Process Manager Alert Start
			 */
			try {
				alertlink = "/asset/AssetEmployee_callforedit.action";
				alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus=P";

				template1.sendProcessManagerAlert(applicant, module, "I", applnID,
						level, alertlinkParam, alertlink, "Pending",
						loginEmpId, "", keepInformId, "", applicant);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * Process Manager Alert End
			 */
			template1.sendApplicationMail();
			template1.clearParameters();
			template1.terminate();

			/**
			 * ASSET APPLICATION -APPLICANT TO APPROVER
			 */

			msgType = "A";
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template
					.setEmailTemplate("ASSET APPLICATION -APPLICANT TO APPROVER");

			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, applicant);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, approver);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			template.configMailAlert();

			String[] link_parameter = new String[3];
			String[] link_label = new String[3];
			// String applicationType = "TYD";
			String applicationType = "AssetAppl";

			link_parameter[0] = applicationType + "#" + approver
					+ "#applicationDtls#";

			String link = "/asset/AssetEmployee_callByApprover.action?hiddenAssetCode="
					+ applnID + "$status=P$applicationLevel=1";
			// link= PPEncrypter.encrypt(link);
			System.out.println("applicationDtls  ..." + link);
			link_parameter[0] += link;
			link_label[0] = "here";

			/* process alerts for send for approval*/

			alertlink = "/asset/AssetEmployee_callByApprover.action";
			alertlinkParam = "hiddenAssetCode=" + applnID
					+ "&status=P&applicationLevel=1";
			try {
				template.sendProcessManagerAlert(approver, module, msgType,
						applnID, level, alertlinkParam, alertlink, "Pending",
						applicant, String.valueOf(empFlow[0][3]), "", "",
						applicant);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (keepInfo != null) {
				template.sendApplicationMailToKeepInfo(keepInfo);
			}
			template.addOnlineLink(request, link_parameter, link_label);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void sendBackApplicationAlert(String applnID, String module,
			String applicant, String approver, String applnDate,
			String applnStatus) {
		try {
			String msgType = "I";
			String level = "1";
			module = "Asset";
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applnID, module);
		
			String empID = "";
			if (applnStatus.equals("reject")) {
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("ASSET - APPROVER REJECT");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, approver);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, applicant);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, approver);

				template.configMailAlert();
				try {
					String alertlink = "/asset/AssetEmployee_callforedit.action";
					String alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus=R";
					template.sendProcessManagerAlert(approver, module, "I",
							applnID, "", alertlinkParam, alertlink, applnStatus,
							applicant, "", "", applicant,approver);
					
					template.sendProcessManagerAlert(empID, module, msgType,
							applnID, "1");
					template.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();
			} else {
				empID = applicant;
				msgType = "I";
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template
						.setEmailTemplate("ASSET APPLICATION -SENT BACK BY ASSIGNER");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, approver);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, applicant);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, approver);

				template.configMailAlert();
				
				String str="";
				
				String alertlink="";
				String alertlinkParam="";
                 
					
					alertlink = "/asset/AssetEmployee_callforedit.action";
					alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus=D";
					applnStatus = "SentBack";
					template.sendProcessManagerAlert("", module, "A",
							applnID, "", alertlinkParam, alertlink,
							applnStatus, applicant, "", "",
							applicant, approver);
					
					alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus=R";

					template.sendProcessManagerAlert(approver,
							module, "I", applnID, "",
							alertlinkParam, alertlink, applnStatus, applicant,
							"", "", "", approver);

				template.sendProcessManagerAlert(applicant, module, msgType,
						applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/*
	 * method name : callforedit purpose : to edit the record from list return
	 * type : String parameter : none
	 */
	public String callforedit() {
		try {

			//For Nevigation

			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			assetEmployee.setSource(source);
			String isApproverLogin = "";
			String isKeepInfoLogin = "";

			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			String id = request.getParameter("hiddencode") != null ? request
					.getParameter("hiddencode") : "";
			assetEmployee.setCode(id);
			// model.callforedit(assetEmployee,request);
			isKeepInfoLogin = model.showRecord(assetEmployee, request);
			model.getEmployeeDetails(assetEmployee);
			//String s = assetEmployee.getHiddenStatus();
			//String id = assetEmployee.getHiddencode();
			id = assetEmployee.getHiddencode();

			String s = request.getParameter("hiddenStatus") != null ? request
					.getParameter("hiddenStatus") : "";
			if (s.equals("D")) {
				getNavigationPanel(3);
				model.getApproverComment(assetEmployee);

			}
			if (s.equals("P") ) {
				getNavigationPanel(4);
				assetEmployee.setEnableAll("N");
				model.getApproverComment(assetEmployee);
				assetEmployee.setKeepInfoFlag("false");
			}
			if (s.equals("F")) {
				getNavigationPanel(6);
				assetEmployee.setEnableAll("N");
				model.getApproverComment(assetEmployee);
				assetEmployee.setIsApprove("true");
				assetEmployee.setCommentFlag("true");
				assetEmployee.setKeepInfoFlag("false");
			}
			if (s.equals("B")) {
				assetEmployee.setIsSentBack("true");
				getNavigationPanel(5);
				assetEmployee.setCommentFlag("true");
				model.getApproverComment(assetEmployee);
				//assetEmployee.setKeepInfoFlag("false");
				// assetEmployee.setAppCommentFlag("true");
			}
			if (s.equals("A") || s.equals("R")|| s.equals("W")) {
				getNavigationPanel(6);
				assetEmployee.setEnableAll("N");
				assetEmployee.setIsApprove("true");
				assetEmployee.setCommentFlag("true");
				assetEmployee.setKeepInfoFlag("false");
				model.getApproverComment(assetEmployee);
			}
			if (s.equals("S")) {
				getNavigationPanel(6);
				assetEmployee.setEnableAll("N");
				assetEmployee.setIsApprove("true");
				assetEmployee.setCommentFlag("true");
				assetEmployee.setKeepInfoFlag("false");
				model.getApproverComment(assetEmployee);
			}

			/**
			 * to find approver and alternate approver login.
			 */
			String empCode = " SELECT ASSET_EMP_ID ,NVL(ASSET_APPL_APPROVER,0) , NVL(ASSET_ALTER_APPROVER,0) FROM HRMS_ASSET_APPLICATION "
					+ " WHERE ASSET_APPL_CODE=" + assetEmployee.getCode();

			Object empCodeObj[][] = model.getSqlModel()
					.getSingleResult(empCode);
			if (empCodeObj != null && empCodeObj.length > 0) {

				if (String.valueOf(empCodeObj[0][1]).equals(
						assetEmployee.getUserEmpId())
						|| String.valueOf(empCodeObj[0][2]).equals(
								assetEmployee.getUserEmpId())) {
					isApproverLogin = "Y";
				}
			}
			//isApproverLogin.equals("Y")||
			if (isKeepInfoLogin.equals("Y")
					|| assetEmployee.getHiddenStatus().equals("F")) {
				getNavigationPanel(6);
				assetEmployee.setEnableAll("N");
				model.getApproverComment(assetEmployee);
				assetEmployee.setKeepInfoFlag("false");
			}

			setApproverList(assetEmployee.getEmpCode());
			model.terminate();

			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in call for edit " + e);
			return null;
		}
	}

	public String f9KeepInformedEmployee() {
		String[] eId = request.getParameterValues("keepInformedEmpId");
		String str = "0";
		if (eId != null) {
			for (int i = 0; i < eId.length; i++) {
				str += "," + eId[i];
			}
		}

		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "	FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S' ";
			if (assetEmployee.getUserProfileDivision() != null
					&& assetEmployee.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN ("
						+ assetEmployee.getUserProfileDivision() + ")";
			query += " AND EMP_ID NOT IN(" + str + ") ";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };

			String[] headerWidth = { "20", "80" };

			String[] fieldNames = { "employeeToken", "employeeName",
					"employeeId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		} // end of try-catch block
	} // end of f9Branch

	public String addKeepInformedEmpList() {

		try {
			setApproverList(assetEmployee.getEmpCode());
			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("keepInformedEmpId");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpName");// keep informed
			// employee name
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, assetEmployee);
			model.setKeepInformed(serialNo, empCode, empName, assetEmployee);
			model.setAssetList(request, assetEmployee);
			assetEmployee.setEmployeeName("");
			assetEmployee.setEmployeeId("");
			assetEmployee.setEmployeeToken("");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addKeepInformedEmpList-----" + e);
		}
		getNavigationPanel(2);
		return SUCCESS;
	}// end of addKeepInformedEmpList

	public String removeKeepInformed() {
		try {
			setApproverList(assetEmployee.getEmpCode());
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			model.removeKeepInformedData(serialNo, empCode, empName,
					assetEmployee);
			model.setAssetList(request, assetEmployee);
			assetEmployee.setEmployeeName("");
			assetEmployee.setEmployeeId("");
			assetEmployee.setEmployeeToken("");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformed--------" + e);
		}
		getNavigationPanel(2);
		return SUCCESS;

	}

	public void setApproverList(String empCode) {
		try {
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			Object[][] empFlow = model1.generateEmpFlow(empCode, "Asset");
			model.setApproverData(assetEmployee, empFlow);
			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			//String applicantID = assetEmployee.getEmpCode();
			String applicantID = assetEmployee.getUserEmpId();
			String module = "Asset";
			String applnID = assetEmployee.getCode();
			String level = "1";
			String link = "/asset/AssetEmployee_callforedit.action";
			String linkParam = "hiddencode=" + applnID + "&hiddenStatus=D";
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", assetEmployee
					.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "Asset");
			template.sendProcessManagerAlertDraft(applicantID, module, msgType,
					applnID, level, linkParam, link, message, "Draft",
					applicantID, applicantID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendProcessManagerAlertForWithdraw() {
		try {
			
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(String.valueOf(assetEmployee.getCode()),
					"Asset");
			processAlerts.terminate();
			
			AssetEmployeeModel model = new AssetEmployeeModel();
			model.initiate(context, session);
			
			String query =" SELECT PURCHASE_APPROVER_ID ,PURCHASE_KEEP_INFORM FROM  HRMS_ASSET_PURCHASE_HDR ";
			
			Object[][]selectObj =model.getSqlModel().getSingleResult(query);
			
			String managerId="";
			
			String keepInformedId="";
			
			if(selectObj!=null && selectObj.length>0)
			{
				managerId=String.valueOf(selectObj[0][0]);
				keepInformedId=String.valueOf(selectObj[0][1]);
			}
		    System.out.println("here inside sendProcessManagerAlertWithdrawn----");
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");			
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = assetEmployee.getEmpCode();
			String module = "Asset";
			String applnID = assetEmployee.getCode();
			String level = "1";
			String link = "/asset/AssetEmployee_callforedit.action";
			String linkParam = "hiddencode=" + applnID + "&hiddenStatus=W";
			String message = alertProp.getProperty("withdrawAlertMessage");
			message = message.replace("<#EMP_NAME#>", assetEmployee.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "Asset");
			template.sendProcessManagerAlertWithdraw(applicantID, module, "I",
					applnID, level, linkParam, link, message, "Withdraw",
					applicantID, applicantID,keepInformedId,managerId);
			template.terminate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
