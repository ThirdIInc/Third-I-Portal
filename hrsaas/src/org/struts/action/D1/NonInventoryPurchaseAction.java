package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.paradyne.bean.D1.NonInventoryPurchaseBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.NonInventoryPurchaseModel;
import org.paradyne.model.admin.srd.AddressDetailsModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.RegularizationModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class NonInventoryPurchaseAction extends ParaActionSupport {

	NonInventoryPurchaseBean bean;

	public void prepare_local() throws Exception {
		bean = new NonInventoryPurchaseBean();
		bean.setMenuCode(1183);
	}

	public NonInventoryPurchaseBean getBean() {
		return bean;
	}

	public void setBean(NonInventoryPurchaseBean bean) {
		this.bean = bean;
	}

	public Object getModel() {
		return bean;
	}

	public String input() throws Exception {
		getNavigationPanel(1);
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		model.initiate(context, session);
		model.input(bean, request);
		model.terminate();
		resetEmp();
		return INPUT;
	}

	public String back() throws Exception {

		return input();
	}

	public String addApplication() throws Exception {
		try {
			resetEmp();
			// if (bean.isGeneralFlag()) {
			NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
			model.initiate(context, session);
			model.setGeneralData(bean, request);
			model.setCompletedBy(bean);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/"
					+ poolName + "/";
			bean.setDataPath(dataPath);
			setApproverList(bean.getEmployeeCode());
			model.terminate();
			// }
			getNavigationPanel(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String resetEmp() throws Exception {
		// getNavigationPanel(1);
		bean.setEmployeeName("");
		bean.setEmployeeToken("");
		bean.setNonInventoryCode("");

		bean.setEmployeeCode("");
		bean.setExtension("");
		// START:Shiping Infomation
		bean.setAddress("");
		bean.setCityId("");
		bean.setZip("");
		bean.setAttn("");
		bean.setShippingPhoneNumber("");
		// END:Shiping Infomation
		// START:Imprint Infomation
		bean.setImprintId("");
		bean.setImprintName("");
		bean.setCompanyName("");
		bean.setTitle("");
		bean.setCompanyAddress("");
		bean.setCompCityNameCode("");
		bean.setCompanyState("");
		bean.setCompanyZip("");
		bean.setCompPhoneNumber("");
		bean.setCompFaxNumber("");
		bean.setCompOtherNumber("");
		bean.setCompDescription("");
		bean.setInternet("");
		// END:Imprint Infomation
		// Description # of Lots Description # of Lots
		bean.setBusinessCardsRegular("");
		bean.setEnvelope10x13("");
		bean.setBusinessCardsCne("");
		bean.setLetterheadRegular("");
		bean.setBusinessCardsLogo("");
		bean.setLetterheadManager("");
		bean.setEnvelopeRegular("");
		bean.setMemoPads("");
		bean.setEnvelopeWindow("");
		bean.setMemoLoose("");
		bean.setEnvelope12Window("");
		bean.setLabelFrom("");
		bean.setEnvelope9x12("");
		bean.setLabelFromTo("");
		bean.setLetterheadLogo("");
		bean.setMemoManager("");
		// Description # of Lots Description # of Lots
		// Additional Infomation
		bean.setAdditionalName("");
		bean.setAdditionalCompanyName("");
		bean.setAdditionalTitle("");
		bean.setAdditionalAddress("");
		bean.setAdditionalCityCode("");
		bean.setAdditionalState("");
		bean.setAdditionalZip("");
		bean.setAdditionalPhoneNumber("");
		bean.setAdditionalFax("");
		bean.setAdditionalOtherNumber("");
		bean.setAdditionalDesc("");
		bean.setAdditionalInternet("");
		return SUCCESS;
	}

	public String reset() throws Exception {
		// getNavigationPanel(1);
		bean.setEmployeeName("");
		bean.setEmployeeToken("");
		bean.setNonInventoryCode("");

		bean.setEmployeeCode("");
		bean.setExtension("");
		// START:Shiping Infomation
		bean.setAddress("");
		bean.setCityId("");
		bean.setZip("");
		bean.setAttn("");
		// END:Shiping Infomation
		// START:Imprint Infomation
		bean.setImprintId("");
		bean.setImprintName("");
		bean.setCompanyName("");
		bean.setTitle("");
		bean.setCompanyAddress("");
		bean.setCompCityNameCode("");
		bean.setCompanyState("");
		bean.setCompanyZip("");
		bean.setCompPhoneNumber("");
		bean.setCompFaxNumber("");
		bean.setCompOtherNumber("");
		bean.setCompDescription("");
		bean.setInternet("");
		// END:Imprint Infomation
		// Description # of Lots Description # of Lots
		bean.setBusinessCardsRegular("");
		bean.setEnvelope10x13("");
		bean.setBusinessCardsCne("");
		bean.setLetterheadRegular("");
		bean.setBusinessCardsLogo("");
		bean.setLetterheadManager("");
		bean.setEnvelopeRegular("");
		bean.setMemoPads("");
		bean.setEnvelopeWindow("");
		bean.setMemoLoose("");
		bean.setEnvelope12Window("");
		bean.setLabelFrom("");
		bean.setEnvelope9x12("");
		bean.setLabelFromTo("");
		bean.setLetterheadLogo("");
		bean.setMemoManager("");
		// Description # of Lots Description # of Lots
		// Additional Infomation
		bean.setAdditionalName("");
		bean.setAdditionalCompanyName("");
		bean.setAdditionalTitle("");
		bean.setAdditionalAddress("");
		bean.setAdditionalCityCode("");
		bean.setAdditionalState("");
		bean.setAdditionalZip("");
		bean.setAdditionalPhoneNumber("");
		bean.setAdditionalFax("");
		bean.setAdditionalOtherNumber("");
		bean.setAdditionalDesc("");
		bean.setAdditionalInternet("");
		
		bean.setProjectDesc("");
		bean.setBusinessObj("");
		bean.setRecommendations("");
		bean.setBusinessBenefits("");
		bean.setResposiblePersonCode("");
		bean.setResposiblePersonName("");
		bean.setAssetInformation("");
		bean.setAccount("");
		bean.setComment("");
		bean.setComments("");
		bean.setShipVia("");
		bean.setCear("");
		
		
		bean.setAsiVendorID("");
		bean.setQty("");
		bean.setUnit("");
		bean.setPrice("");
		bean.setManufacture("");
		bean.setMrfPart("");
		bean.setVendorEmailId("");
		bean.setVendorName("");
		bean.setVendorPart("");
		bean.setVendorPhoneNo("");
		bean.setVendorShipVia("");
		bean.setDesc("");
		bean.setSelectTypeCode("");
		bean.setSelectTypeName("");
		bean.setNoOfLots("");

		System.out.println("bean.isGeneralFlag() ##########  "
				+ bean.isGeneralFlag());
		// if (bean.isGeneralFlag()) {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		model.initiate(context, session);
		model.setGeneralData(bean, request);
		try {
			setApproverList(bean.getEmployeeCode());
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
		// }
		getNavigationPanel(4);
		return SUCCESS;
	}

	/**
	 * METHOD TO DRAFT APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */

	public String draft() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		// getNavigationPanel(3);
		model.initiate(context, session);
		String ittselectTypeCode[] = request
				.getParameterValues("ittselectTypeCode");
		String ittnoOfLots[] = request.getParameterValues("ittnoOfLots");
		String[] aaprCode = request.getParameterValues("approverCode");
		boolean result = model.draft(bean, "D", ittselectTypeCode, ittnoOfLots,
				aaprCode, request);
		if (result) {
			addActionMessage("Application saved successfully");
		} else {
			addActionMessage("Application not saved successfully");
		}
		model.terminate();
		getSelectTYpe();
		getProductToList();
		getNavigationPanel(3);
		return SUCCESS;
	}

	/**
	 * METHOD FOR SEND FOR APPROVAL
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendForApproval() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		model.initiate(context, session);
		String ittselectTypeCode[] = request
				.getParameterValues("ittselectTypeCode");
		String ittnoOfLots[] = request.getParameterValues("ittnoOfLots");
		String[] aaprCode = request.getParameterValues("approverCode");
		boolean result = model.draft(bean, "P", ittselectTypeCode, ittnoOfLots,
				aaprCode, request);
		if (result) {
			addActionMessage("Application send for approval successfully \n Tracking No: "
					+ bean.getFileheaderName());
			try {
				String appCode = "SELECT DECODE(CHANGE_MANG_CODE,NULL,APPROVER_CODE,CHANGE_MANG_CODE) FROM HRMS_D1_NON_INVENTORY_PURCHASE WHERE  NON_PURCHASE_ID="
						+ bean.getNonInventoryCode();
				Object[][] appObj = model.getSqlModel()
						.getSingleResult(appCode);
				if (appObj != null && appObj.length > 0) {
					String applicationType = "NonInventory";
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					String empCode = bean.getEmployeeCode();
					String applCode = bean.getNonInventoryCode();

					link_param[0] = applicationType + "#" + applCode + "#"
							+ String.valueOf(appObj[0][0]) + "#" + "..." + "#"
							+ "A";

					link_param[1] = applicationType + "#" + applCode + "#"
							+ String.valueOf(appObj[0][0]) + "#" + "..." + "#"
							+ "R";
					link_param[2] = applicationType + "#" + applCode + "#"
							+ String.valueOf(appObj[0][0]) + "#" + "..." + "#"
							+ "B";

					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";

					sendMailMethod(
							"Non Inventory purchase applicationl to first approver for approval",
							bean.getEmployeeCode(), String
									.valueOf(appObj[0][0]), bean
									.getNonInventoryCode(), null, null);
				}
			} catch (Exception e) {
			}
		} else {
			addActionMessage("Application not send for approval successfully");
		}
		model.terminate();
		return input();
	}

	/**
	 * METHOD FOR DELETE APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */

	public String delete() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		getNavigationPanel(1);
		model.initiate(context, session);
		boolean result = model.delete(bean);
		bean.setNonInventoryCode("");
		if (result) {
			addActionMessage("Application deleted successfully");
		} else {
			addActionMessage("Application not delete successfully");
		}
		model.terminate();
		return input();
	}

	/**
	 * METHOD FOR EDIT
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editApplication() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();

		model.initiate(context, session);
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			bean.setNonInventoryCode(applCode);
		}
		boolean result = model.editApplication(bean);
		model.getApproverComments(bean);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName
				+ "/";
		bean.setDataPath(dataPath);
		// getProductToList();
		model.terminate();
		try {
			setApproverList(bean.getEmployeeCode());
		} catch (Exception e) {
			// TODO: handle exception
		}

		//String[] status = request.getParameterValues("status");
		/*
		 * if(status !=null){
		 * if(status[0].equals("P")||status[0].equals("R")||status[0].equals("A")){
		 * System.out.println("status ################ : "+status[0]);
		 * getNavigationPanel(2); bean.setEnableAll("N"); } }
		 */
		if (bean.getStatus().equals("") || bean.getStatus().equals("D")
				|| bean.getStatus().equals("B")) {
			getNavigationPanel(3);
		} else {
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		System.out.println("bean.getStatus() :::::  " + bean.getStatus());
		//FOR SUPER USER
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			bean.setEnableAll("N");
		}
		return SUCCESS;
	}

	public String addSelectTYpe() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		getNavigationPanel(1);
		model.initiate(context, session);
		String ittselectTypeCode[] = request
				.getParameterValues("ittselectTypeCode");
		String ittselectTypeName[] = request
				.getParameterValues("ittselectTypeName");
		String ittnoOfLots[] = request.getParameterValues("ittnoOfLots");
		boolean result = model.addSelectTYpe(bean, ittselectTypeCode,
				ittselectTypeName, ittnoOfLots, "add");
		addActionMessage("Record added successfully");
		getProductToList();
		model.terminate();
		bean.setSelectTypeCode("");
		bean.setSelectTypeName("");
		bean.setNoOfLots("");
		getNavigationPanel(3);
		setApproverList(bean.getEmployeeCode());
		return SUCCESS;
	}

	public String addProductToList() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		getNavigationPanel(1);
		model.initiate(context, session);
		String ittqty[] = request.getParameterValues("ittqty");
		String ittunit[] = request.getParameterValues("ittunit");
		String ittprice[] = request.getParameterValues("ittprice");
		String ittmanufacture[] = request.getParameterValues("ittmanufacture");
		String ittmrfPart[] = request.getParameterValues("ittmrfPart");
		String ittvendorPart[] = request.getParameterValues("ittvendorPart");
		String ittcomment[] = request.getParameterValues("ittdesc");

		boolean result = model.addToList(bean, ittqty, ittunit, ittprice,
				ittmanufacture, ittmrfPart, ittvendorPart, ittcomment, "add");
		addActionMessage("Record added successfully");
		model.terminate();
		bean.setQty("");
		bean.setUnit("");
		bean.setPrice("");
		bean.setManufacture("");
		bean.setMrfPart("");
		bean.setVendorPart("");
		bean.setDesc("");
		getNavigationPanel(3);
		setApproverList(bean.getEmployeeCode());
		getSelectTYpe();
		return SUCCESS;
	}

	public String removeProductToList() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		getNavigationPanel(1);
		model.initiate(context, session);
		String ittqty[] = request.getParameterValues("ittqty");
		String ittunit[] = request.getParameterValues("ittunit");
		String ittprice[] = request.getParameterValues("ittprice");
		String ittmanufacture[] = request.getParameterValues("ittmanufacture");
		String ittmrfPart[] = request.getParameterValues("ittmrfPart");
		String ittvendorPart[] = request.getParameterValues("ittvendorPart");
		String ittcomment[] = request.getParameterValues("ittdesc");
		boolean result = model
				.addToList(bean, ittqty, ittunit, ittprice, ittmanufacture,
						ittmrfPart, ittvendorPart, ittcomment, "remove");
		model.terminate();
		bean.setQty("");
		bean.setUnit("");
		bean.setPrice("");
		bean.setManufacture("");
		bean.setMrfPart("");
		bean.setVendorPart("");
		bean.setDesc("");
		getNavigationPanel(3);
		setApproverList(bean.getEmployeeCode());
		getSelectTYpe();
		return SUCCESS;
	}

	public String getProductToList() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		getNavigationPanel(1);
		model.initiate(context, session);
		String ittqty[] = request.getParameterValues("ittqty");
		String ittunit[] = request.getParameterValues("ittunit");
		String ittprice[] = request.getParameterValues("ittprice");
		String ittmanufacture[] = request.getParameterValues("ittmanufacture");
		String ittmrfPart[] = request.getParameterValues("ittmrfPart");
		String ittvendorPart[] = request.getParameterValues("ittvendorPart");
		String ittcomment[] = request.getParameterValues("ittdesc");

		boolean result = model.addToList(bean, ittqty, ittunit, ittprice,
				ittmanufacture, ittmrfPart, ittvendorPart, ittcomment, "");
		model.terminate();
		bean.setQty("");
		bean.setUnit("");
		bean.setPrice("");
		bean.setManufacture("");
		bean.setMrfPart("");
		bean.setVendorPart("");
		bean.setDesc("");

		getNavigationPanel(3);
		setApproverList(bean.getEmployeeCode());
		return SUCCESS;
	}

	public String getSelectTYpe() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();

		model.initiate(context, session);
		String ittselectTypeCode[] = request
				.getParameterValues("ittselectTypeCode");
		String ittselectTypeName[] = request
				.getParameterValues("ittselectTypeName");
		String ittnoOfLots[] = request.getParameterValues("ittnoOfLots");

		boolean result = model.addSelectTYpe(bean, ittselectTypeCode,
				ittselectTypeName, ittnoOfLots, "");
		model.terminate();
		getNavigationPanel(3);
		return SUCCESS;
	}

	public String removeSelectType() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		getNavigationPanel(3);
		model.initiate(context, session);
		String ittselectTypeCode[] = request
				.getParameterValues("ittselectTypeCode");
		String ittselectTypeName[] = request
				.getParameterValues("ittselectTypeName");
		String ittnoOfLots[] = request.getParameterValues("ittnoOfLots");

		boolean result = model.addSelectTYpe(bean, ittselectTypeCode,
				ittselectTypeName, ittnoOfLots, "remove");
		getProductToList();
		model.terminate();
		setApproverList(bean.getEmployeeCode());
		getNavigationPanel(3);
		return SUCCESS;
	}

	public String f9Employee() throws Exception {

		String query = "    SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,NVL(ADD_PH1,' '),"
				+ "	NVL(ADD_FAX,' '),OFFC.EMP_ID   "
				+ "	FROM HRMS_EMP_OFFC OFFC  	LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P') "
				+ "	LEFT JOIN HRMS_EMP_OFFC MGR ON(MGR.EMP_ID = OFFC.EMP_REPORTING_OFFICER)  ";

		if (bean.isGeneralFlag()) {
			query += " WHERE OFFC.EMP_ID=" + bean.getUserEmpId();
		} else {
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				query += " WHERE OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				query += " WHERE 1=1 ";
			}
		}
		query += " ORDER BY UPPER(OFFC.EMP_FNAME) ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "30", "30" };

		String[] fieldNames = { "employeeToken", "employeeName",
				"preparerPhone", "preparerFax", "employeeCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4 };

		String submitFlag = "true";

		String submitToMethod = "NonInventoryPurchase_setApproval.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String setApproval() throws Exception {
		setApproverList(bean.getEmployeeCode());
		getSelectTYpe();
		getNavigationPanel(3);
		bean.setEnableAll("Y");
		return SUCCESS;
	}

	public void setApproverList(String empCode) {
		ReportingModel model = new ReportingModel();
		getNavigationPanel(3);
		model.initiate(context, session);
		Object[][] empFlow = null;
		try {
			if (!empCode.equals("")) {
				empFlow = model.generateEmpFlow(empCode, "D1");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		NonInventoryPurchaseModel modelNon = new NonInventoryPurchaseModel();
		modelNon.initiate(context, session);
		if (empFlow != null && empFlow.length > 0) {
			modelNon.setApproverData(bean, empFlow);
		} else {
			bean.setCheckData("0");
		}
	}

	public String f9cityaction() throws Exception {

		String query = " SELECT  CITY.LOCATION_CODE,CITY.LOCATION_NAME,STATE.LOCATION_NAME ,COUNTRY.LOCATION_NAME   FROM HRMS_LOCATION CITY "
				+ "   LEFT JOIN HRMS_LOCATION STATE ON(CITY.LOCATION_PARENT_CODE=STATE.LOCATION_CODE)	"
				+ "    LEFT JOIN HRMS_LOCATION COUNTRY ON(STATE.LOCATION_PARENT_CODE=COUNTRY.LOCATION_CODE)	"
				+ " 	 WHERE CITY.LOCATION_LEVEL_CODE = 2  	 ORDER BY CITY.LOCATION_CODE	 ";

		String[] headers = { getMessage("city.code"), getMessage("city") };

		String[] headerWidth = { "10", "45" };

		String[] fieldNames = { "cityId", "cityName", "stateName", "country" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "NonInventoryPurchase_getState.action ";

		bean.setMasterMenuCode(20);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String getState() throws Exception {

		setApproverList(bean.getEmployeeCode());
		getSelectTYpe();
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ bean.getCityId() + ") ";
		Object[][] stateCode = model.getSqlModel().getSingleResult(query);

		if (stateCode.length > 0) {
			bean.setStateName((String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
			Object[][] countryName = model.getSqlModel().getSingleResult(
					coutryQuery);
			if (countryName.length > 0) {
				bean.setCountry((String.valueOf(countryName[0][1])));
			}// end of nested if
			else
				bean.setCountry("");
		}// end of if
		model.terminate();
		getNavigationPanel(3);
		return SUCCESS;
	}

	/**
	 * IMPRINT MASTER TABLE
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public String f9imprintaction() throws Exception {
		String query = " SELECT IMPRINT_NAME,IMPRINT_ID FROM HRMS_D1_IMPRINT_TYPE ORDER BY IMPRINT_ID";
		String[] headers = { getMessage("imprint.type") };
		String[] headerWidth = { "10" };
		String[] fieldNames = { "imprintType", "imprintId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9selecttype() throws Exception {
		String query = " SELECT STATIONARY_NAME,STATIONARY_ID FROM HRMS_D1_STATIONARY ORDER BY STATIONARY_ID";
		String[] headers = { getMessage("select.type") };
		String[] headerWidth = { "10" };
		String[] fieldNames = { "selectTypeName", "selectTypeCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * IMPRINT MASTER TABLE
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public String f9changeMang() throws Exception {

		String query = " SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC	  ";
		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_ID NOT IN (" + bean.getEmployeeCode() + ") ";
		query += " AND EMP_STATUS='S'";
		query += " ORDER BY UPPER(EMP_FNAME) ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "approverToken", "selectapproverName",
				"changeApproverCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9responsible() throws Exception {
		String query = " SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC	  ";
		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}

		query += " ORDER BY UPPER(EMP_FNAME) ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "resposiblePersonToken",
				"resposiblePersonName", "resposiblePersonCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9additioncity() throws Exception {

		String query = " SELECT  CITY.LOCATION_CODE,CITY.LOCATION_NAME,STATE.LOCATION_NAME   FROM HRMS_LOCATION CITY "
				+ "   LEFT JOIN HRMS_LOCATION STATE ON(CITY.LOCATION_PARENT_CODE=STATE.LOCATION_CODE)	"
				+ "    LEFT JOIN HRMS_LOCATION COUNTRY ON(STATE.LOCATION_PARENT_CODE=COUNTRY.LOCATION_CODE)	"
				+ " 	 WHERE CITY.LOCATION_LEVEL_CODE = 2  	 ORDER BY CITY.LOCATION_CODE	 ";
		String[] headers = { getMessage("city.code"), getMessage("city") };

		String[] headerWidth = { "10", "45" };

		String[] fieldNames = { "additionalCityCode", "additionalCity",
				"additionalState" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "NonInventoryPurchase_getadditionState.action ";

		bean.setMasterMenuCode(20);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String getadditionState() throws Exception {
		getNavigationPanel(3);
		setApproverList(bean.getEmployeeCode());
		getSelectTYpe();
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ bean.getAdditionalCityCode() + ") ";
		Object[][] stateCode = model.getSqlModel().getSingleResult(query);
		if (stateCode.length > 0) {
			bean.setAdditionalState((String.valueOf(stateCode[0][1])));
		}// end of if
		model.terminate();
		return SUCCESS;
	}

	public String f9imprintcit() throws Exception {

		String query = " SELECT  CITY.LOCATION_CODE,CITY.LOCATION_NAME,STATE.LOCATION_NAME   FROM HRMS_LOCATION CITY "
				+ "   LEFT JOIN HRMS_LOCATION STATE ON(CITY.LOCATION_PARENT_CODE=STATE.LOCATION_CODE)	"
				+ "    LEFT JOIN HRMS_LOCATION COUNTRY ON(STATE.LOCATION_PARENT_CODE=COUNTRY.LOCATION_CODE)	"
				+ " 	 WHERE CITY.LOCATION_LEVEL_CODE = 2  	 ORDER BY CITY.LOCATION_CODE	 ";
		String[] headers = { getMessage("city.code"), getMessage("city") };

		String[] headerWidth = { "10", "45" };

		String[] fieldNames = { "compCityNameCode", "compCityName",
				"companyState" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "NonInventoryPurchase_getImprintState.action ";

		bean.setMasterMenuCode(20);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String getImprintState() throws Exception {
		getNavigationPanel(3);
		setApproverList(bean.getEmployeeCode());
		getSelectTYpe();
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ bean.getCompCityNameCode() + ") ";
		Object[][] stateCode = model.getSqlModel().getSingleResult(query);
		if (stateCode.length > 0) {
			bean.setCompanyState((String.valueOf(stateCode[0][1])));
		}// end of if
		model.terminate();
		return SUCCESS;
	}

	/**
	 * SEND MAIL
	 */

	public String sendMailMethod(String tempName, String fromEmp,
			String approveCode, String applicationCode

			, String[] link_param, String[] link_label) throws Exception {
		try {
			
			String path=bean.getDataPath()+bean.getUploadFileName();
			
			Object[][] eventData = null;
			Object[][] templateData = null;
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);

			String templateQuery = "SELECT TEMPLATE_NAME "
					+ " FROM HRMS_EMAILTEMPLATE_HDR  "
					+ " WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"
					+ tempName + "'";
			templateData = model.getSqlModel().getSingleResult(templateQuery);
			// if(templateData !=null && templateData.length>0){
			String templateName = tempName.trim();
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);

			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
																				// EMAIL
			if (!fromEmp.equals("")) {
				templateQuery1.setParameter(1, fromEmp);
			}

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
																				// EMAIL
			templateQuery2.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationCode);

			template.configMailAlert();
			if (link_param != null && link_param.length > 0) {
				template.addOnlineLink(request, link_param, link_label);
			}
			
			if(!bean.getUploadFileName().equals("")){
				String[] attachedFile = new String[1];
				attachedFile[0] = path;
				template.sendApplMailWithAttachment(attachedFile);
			}
			else{
				template.sendApplicationMail();	
			}
			template.clearParameters();
			template.terminate();
			// }

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}

	public void getData() throws IOException {
		String custZipCode = request.getParameter("custZipCode");
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(custZipCode);
		printWriter.flush();
	}

	/**
	 * METHOD TO SET DEPARTMENT
	 */
	public String f9department() throws Exception {
		//String query = " SELECT  DEPT_NAME,DEPT_ABBR,DEPT_ID FROM  HRMS_DEPT WHERE DEPT_PARENT_CODE IS NULL  ORDER BY DEPT_ID DESC ";
		String query = "SELECT  DEPT_NAME,DEPT_ABBR,DEPT_ID FROM HRMS_DEPT WHERE DEPT_PARENT_CODE IS NULL ";

        if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
				query += " AND DEPT_DIV_CODE IN ("
						+ bean.getUserProfileDivision() + ")";
			}
        query += " ORDER BY DEPT_ID DESC";
		String[] headers = { getMessage("department"),"Department Name" };
		
		

		String[] headerWidth = { "50" ,"50"};

		String[] fieldNames = { "department","departmentAbbr", "departmentCode" };

		int[] columnIndex = { 0, 1,2 };

		String submitFlag = "false";

		String submitToMethod = "NonInventoryPurchase_setDeptName.action";

		bean.setMasterMenuCode(2048);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String setDeptName() throws Exception {
		String query = " SELECT  DEPT_NAME||'-'||DEPT_ABBR,DEPT_ID FROM  HRMS_DEPT WHERE  DEPT_ID  = "+bean.getDepartmentCode();
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		model.initiate(context, session);
		Object[][]data=model.getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0){
		bean.setDepartment(String.valueOf(data[0][0]));	
		}
		return SUCCESS;
	}
	
	
	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");

			/*
			 * MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
			 * response);
			 */

			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} // end of if
				else if (ext.equals("doc")) {
					mimeType = "msword";
				} // end of else if
				else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} // end of else if
				else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} // end of else
				else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} // end of else if
				else if (ext.equals("txt")) {
					mimeType = "txt";
				} // end of else if
				else if (ext.equals("gif")) {
					mimeType = "gif";
				} // end of else if
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
				} // end of if
				else {
					response.setHeader("Content-type", "application/" + mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename= \"" + fileName + "\"");
				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while
			} catch (FileNotFoundException e) {
				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
		} catch (Exception e) {
			// logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
}