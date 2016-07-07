/**
 * 
 */
package org.struts.action.Asset;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.paradyne.bean.Asset.PurchaseOrder;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.Asset.AssetEmployeeModel;
import org.paradyne.model.Asset.PurchaseOrderModel;
import org.paradyne.model.Asset.RateListModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.extraWorkBenefits.ExtraWorkApplicationModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 * 
 */
public class PurchaseOrderAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.Asset.PurchaseOrderAction.class);
	PurchaseOrder purchaseOrder;

	public void prepare_local() throws Exception {
		purchaseOrder = new PurchaseOrder();
		purchaseOrder.setMenuCode(651);
	}

	public Object getModel() {
		return purchaseOrder;
	}

	public String input() {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			// model.showList(purchaseOrder, request);
			purchaseOrder.setListType("pending");
			model.getAllTypeOfApplications(purchaseOrder, request,
					purchaseOrder.getUserEmpId());
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (purchaseOrder.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (purchaseOrder.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return INPUT;
		}	
	}

	public String getApprovedList() throws Exception {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			// model.showList(purchaseOrder, request);
			purchaseOrder.setListType("approved");
			model.getApprovedList(purchaseOrder, request, purchaseOrder
					.getUserEmpId());
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String getRejectedList() throws Exception {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			// model.showList(purchaseOrder, request);
			purchaseOrder.setListType("rejected");
			model.getRejectedList(purchaseOrder, request, purchaseOrder
					.getUserEmpId());
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String getAssignedList() throws Exception {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			// model.showList(purchaseOrder, request);
			purchaseOrder.setListType("assigned");
			model.getAssignedList(purchaseOrder, request, purchaseOrder
					.getUserEmpId());
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String addNew() throws Exception {

		try {
			
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			purchaseOrder.setSource(source);
			
			setApproverList(purchaseOrder.getUserEmpId());
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.getComponyLogo(request);

			model.terminate();
			getNavigationPanel(12);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String setVendorAddress() {
		PurchaseOrderModel model = new PurchaseOrderModel();
		try {

			model.initiate(context, session);

			String query = " SELECT VENDOR_ADDRESS FROM HRMS_VENDOR "
					+ " WHERE VENDOR_CODE="
					+ purchaseOrder.getVendorCode().trim();

			Object addressObj[][] = model.getSqlModel().getSingleResult(query);
			if (addressObj != null && addressObj.length > 0) {
				purchaseOrder.setVendorAddress(String.valueOf(addressObj[0][0])
						.trim());
			}

			model.setKeepInformList(request, purchaseOrder);
			model.getComponyLogo(request);
			model.setAssetList(request, purchaseOrder);
			if (!purchaseOrder.getEmpCode().equals("")) {
				setApproverList(purchaseOrder.getEmpCode());
			} else {
				setApproverList(purchaseOrder.getUserEmpId());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (purchaseOrder.getHiddenStatus().equals("D")) {
			getNavigationPanel(3);
		} else {
			getNavigationPanel(12);
		}
		model.terminate();
		return SUCCESS;
	}

	public String reset1() {
		try {
			setApproverList(purchaseOrder.getEmpCode());
			logger.info("status =========================================  "
					+ purchaseOrder.getStatus() + "======");

			// assetEmployee.setComments("");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			if (purchaseOrder.getHiddenStatus().equals("D"))
				purchaseOrder.setStatus(purchaseOrder.getStatus());
			else
				purchaseOrder.setStatus("");
			model.getComponyLogo(request);
			model.setKeepInformList(request, purchaseOrder);
			showList();
			purchaseOrder.setEnableAll("Y");
			model.terminate();
			if (purchaseOrder.getStatus().equals("D"))
				getNavigationPanel(3);
			else
				getNavigationPanel(12);

			model.terminate();
			if (purchaseOrder.getStatus().equals("D"))
				getNavigationPanel(3);
			else
				getNavigationPanel(12);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String f9action() throws Exception {

		String query = " SELECT PURCHASE_CODE,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'), "
				+ " CASE WHEN PURCHASE_STATUS='P' AND PURCHASE_LEVEL!=1   THEN 'Forwarded' WHEN PURCHASE_STATUS='P' THEN 'Pending' WHEN PURCHASE_STATUS='A' THEN 'Approved' WHEN PURCHASE_STATUS='R' THEN 'Rejected' "
				+ " WHEN PURCHASE_STATUS='C' THEN 'Canceled' ELSE '' end, "
				+ " PURCHASE_EMP_CODE,PURCHASE_VENDOR_CODE,VENDOR_NAME,LOCATION_NAME FROM HRMS_ASSET_PURCHASE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
				+ " INNER JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_PURCHASE_HDR.PURCHASE_VENDOR_CODE and VENDOR_TYPE='S' ) "
				+ " INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY) ";
		if (purchaseOrder.isGeneralFlag()) {
			query += " WHERE PURCHASE_EMP_CODE=" + purchaseOrder.getEmpCode();
		}

		query += " ORDER BY PURCHASE_CODE DESC ";

		String[] headers = { getMessage("purchaseCode"),
				getMessage("employee.id"), getMessage("employee"),
				getMessage("date"), getMessage("status") };

		String[] headerWidth = { "10", "20", "30", "20", "10" };

		String[] fieldNames = { "purchaseCode", "empToken", "empName",
				"orderDate", "status", "empCode", "vendorCode", "vendorName",
				"cityName" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		String submitFlag = "true";

		String submitToMethod = "PurchaseOrder_showRecords.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9ActionEmp() throws Exception {

		String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, EMP_ID "
				+ "FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(purchaseOrder);

		query += " AND EMP_STATUS = 'S' ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "empToken", "empName", "empCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "PurchaseOrder_reset1.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionVendor() throws Exception {

		String query = " SELECT VENDOR_CODE, VENDOR_NAME,VENDOR_CONTACT_NO FROM HRMS_VENDOR "
				+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY) "
				+ " WHERE VENDOR_TYPE='S' AND VENDOR_ISACTIVE='Y' ORDER BY VENDOR_CODE ";

		String[] headers = { getMessage("venCode"), getMessage("venName") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "vendorCode", "vendorName", "vendorContact" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "PurchaseOrder_setVendorAddress.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionCostCenter() throws Exception {

		String query = " SELECT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID ";

		String[] headers = { getMessage("costCenterId"),
				getMessage("costcenter") };

		String[] headerWidth = { "20", "40" };

		String[] fieldNames = { "costCenterId", "costcenter" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionSubCostCenter() throws Exception {

		String query = " SELECT SUB_COST_CENTER_ID,SUB_COST_CENTER_NAME FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID="
				+ purchaseOrder.getCostCenterId()
				+ " ORDER BY SUB_COST_CENTER_ID";

		String[] headers = { getMessage("subcostCenterId"),
				getMessage("subcostcenter") };

		String[] headerWidth = { "20", "40" };

		String[] fieldNames = { "subcostCenterId", "subcostcenter" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * public String f9actionSubType() throws Exception {
	 * 
	 * 
	 * String query = " SELECT RATELIST_ITEM_CODE,
	 * NVL(ASSET_SUBTYPE_NAME,''),RATELIST_PRICE,UNIT_NAME FROM
	 * HRMS_ASSET_RATELIST_DTL " +" INNER JOIN HRMS_ASSET_SUBTYPE on
	 * (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_RATELIST_DTL.RATELIST_ITEM_CODE)" +"
	 * INNER JOIN HRMS_UNIT_MASTER on
	 * (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)" +"
	 * INNER JOIN HRMS_ASSET_RATELIST
	 * on(HRMS_ASSET_RATELIST.RATELIST_CODE=HRMS_ASSET_RATELIST_DTL.RATELIST_CODE)" +"
	 * WHERE RATELIST_VENDOR= "+purchaseOrder.getVendorCode()+" ORDER BY
	 * RATELIST_ITEM_CODE ";
	 * 
	 * 
	 * String[] headers={"Code", "Asset Sub-Type"};
	 * 
	 * String[] headerWidth={"20", "80"};
	 * 
	 * String[] fieldNames={"subTypeCode","subTypeName","price","unit"};
	 * 
	 * 
	 * int[] columnIndex={0, 1, 2, 3};
	 * 
	 * 
	 * String submitFlag = "true";
	 * 
	 * 
	 * String submitToMethod="PurchaseOrder_showList.action";
	 * 
	 * 
	 * setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	 * 
	 * return "f9page"; }
	 */
	public String f9actionSubType() throws Exception {

		String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,''),UNIT_NAME  FROM HRMS_ASSET_SUBTYPE   "
				+ " INNER JOIN HRMS_UNIT_MASTER ON (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)"
				+ " ORDER BY ASSET_SUBTYPE_CODE ";

		String[] headers = { getMessage("code"), getMessage("asset") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "subTypeCode", "subTypeName", "unit" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "PurchaseOrder_getPrice.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String reset() {

		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			if (!(purchaseOrder.isGeneralFlag())) {
				purchaseOrder.setEmpCode("");
				purchaseOrder.setEmpToken("");
				purchaseOrder.setEmpName("");
			}
			purchaseOrder.setPurchaseCode("");
			purchaseOrder.setParaId("");
			purchaseOrder.setPrice("");
			purchaseOrder.setVendorCode("");
			purchaseOrder.setVendorName("");
			purchaseOrder.setQuantity("");
			purchaseOrder.setSubTypeCode("");
			purchaseOrder.setSubTypeName("");
			purchaseOrder.setUnit("Unit");
			purchaseOrder.setOrderDate("");
			purchaseOrder.setTotalPrice("");
			purchaseOrder.setCityName("");
			purchaseOrder.setComments("");
			purchaseOrder.setTotalAmount("");
			purchaseOrder.setStatus("");
			purchaseOrder.setHiddenStatus("");
			purchaseOrder.setPurchaseOrderNo("");
			purchaseOrder.setShippingCost("");
			purchaseOrder.setPurchaseNoItt("");
			purchaseOrder.setPriceAdjust("");
			purchaseOrder.setAdditionalTaxRate("");
			purchaseOrder.setAdditionalTaxAmount("");
			purchaseOrder.setSalesTaxAmount("");
			purchaseOrder.setSalesTax("");
			purchaseOrder.setTotalallAmount("");
			purchaseOrder.setTotalnetAmount("");
			purchaseOrder.setDiscount("");
			purchaseOrder.setCaldiscount("");
			purchaseOrder.setCreatedDate("");
			purchaseOrder.setCostcenter("");
			purchaseOrder.setRemarks("");
			purchaseOrder.setTerms("");
			purchaseOrder.setCostCenterId("");
			purchaseOrder.setSubcostcenter("");
			purchaseOrder.setSubcostCenterId("");
			purchaseOrder.setVendorAddress("");
			purchaseOrder.setVendorContact("");
			purchaseOrder.setShippingAddress("");
			purchaseOrder.setBillingAddress("");
			purchaseOrder.setPoName("");
			purchaseOrder.setRequiredBy("");
			purchaseOrder.setShippingAddress("");
			purchaseOrder.setBillingAddress("");
			purchaseOrder.setVendorAddress("");
			purchaseOrder.setVendorContact("");
			purchaseOrder.setOrderDate("");
			// purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			model.getComponyLogo(request);

			getNavigationPanel(12);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String addItem() {
		boolean result = false;
		try {
			/*
			 * String
			 * []vendorCode=request.getParameterValues("vendorCodeIterator");
			 * String
			 * []vendorName=request.getParameterValues("vendorNameIterator");
			 */
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			String[] quantity = request.getParameterValues("quantityIterator");
			String[] total = request.getParameterValues("totalIterator");
			String[] city = request.getParameterValues("cityIterator");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			if (purchaseOrder.getParaId().equals("")) {
				model.addItem(purchaseOrder, itemCode, itemName, price, unit,
						quantity, total, city);
			} else {
				model.editItem(purchaseOrder, itemCode, itemName, price, unit,
						quantity, total, city);
			}
			if (purchaseOrder.getIsApprove().equals("true")) {
				model.showApproverComments(purchaseOrder);
			}
			model.setKeepInformList(request, purchaseOrder);
			model.getComponyLogo(request);
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			resetDetails();
			if (purchaseOrder.getStatus().equals("D"))
				getNavigationPanel(3);
			else
				getNavigationPanel(12);
			if (!purchaseOrder.getEmpCode().equals("")) {
				setApproverList(purchaseOrder.getEmpCode());
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String removeItem() {
		try {
			/*
			 * String
			 * []vendorCode=request.getParameterValues("vendorCodeIterator");
			 * String
			 * []vendorName=request.getParameterValues("vendorNameIterator");
			 */
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			String[] quantity = request.getParameterValues("quantityIterator");
			String[] total = request.getParameterValues("totalIterator");
			String[] city = request.getParameterValues("cityIterator");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.removeItem(purchaseOrder, itemCode, itemName, price, unit,
					quantity, total, city);
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			model.getComponyLogo(request);
			if (purchaseOrder.getIsApprove().equals("true")) {
				model.showApproverComments(purchaseOrder);
			}
			purchaseOrder.setParaId("");
			/*
			 * if (purchaseOrder.getPurchaseCode().equals(""))
			 * getNavigationPanel(3); else if
			 * (purchaseOrder.getHiddenStatus().equals("P"))
			 * getNavigationPanel(6); else getNavigationPanel(3);
			 */
			addActionMessage("Record deleted successfully.");

			if (purchaseOrder.getStatus().equals("D"))
				getNavigationPanel(3);
			else
				getNavigationPanel(12);
			if (!purchaseOrder.getEmpCode().equals("")) {
				setApproverList(purchaseOrder.getEmpCode());
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String showList() {
		try {
			/*
			 * String
			 * []vendorCode=request.getParameterValues("vendorCodeIterator");
			 * String
			 * []vendorName=request.getParameterValues("vendorNameIterator");
			 */
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			String[] quantity = request.getParameterValues("quantityIterator");
			String[] total = request.getParameterValues("totalIterator");
			String[] city = request.getParameterValues("cityIterator");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			model.showList(purchaseOrder, itemCode, itemName, price, unit,
					quantity, total, city);
			model.getComponyLogo(request);
			if (purchaseOrder.getIsApprove().equals("true")) {
				model.showApproverComments(purchaseOrder);
			}
			model.terminate();
			if (purchaseOrder.getStatus().equals("D"))
				getNavigationPanel(3);
			else
				getNavigationPanel(12);

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (!purchaseOrder.getEmpCode().equals("")) {
			setApproverList(purchaseOrder.getEmpCode());
		}

		return SUCCESS;
	}

	public String save() {
		boolean result = false;
		try {
			/*
			 * String
			 * []vendorCode=request.getParameterValues("vendorCodeIterator");
			 * String
			 * []vendorName=request.getParameterValues("vendorNameIterator");
			 */
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			String[] quantity = request.getParameterValues("quantityIterator");
			String[] total = request.getParameterValues("totalIterator");
			String[] city = request.getParameterValues("cityIterator");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			Object[][] empFlow = generateEmpFlow(purchaseOrder.getEmpCode(),
					"Purchase", 1);
			String msg = "";
			if (empFlow != null && empFlow.length != 0) {
				if (purchaseOrder.getPurchaseCode().equals("")) {

					result = model.save(purchaseOrder, itemCode, price,
							quantity, total, empFlow, request);
					if (result) {
						if (purchaseOrder.getHiddenStatus().equals("D")) {
							
							try {
								sendProcessManagerAlertDraft();
							} catch (Exception e) {
								// TODO: handle exception
							}
							msg = "Application drafted successfully \n Reference ID:-"
									+ purchaseOrder.getReferenceId();
						}
						if (purchaseOrder.getHiddenStatus().equals("P")) {
							
							/**
							 * Remove Process manager alert entry from mypage
							 */
							try {
								/*MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
								processAlerts.initiate(context, session);
								processAlerts.removeProcessAlert(purchaseOrder
										.getPurchaseCode(), "Purchase Order");
								processAlerts.terminate();*/
								ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
								processAlerts.initiate(context, session);

								processAlerts.removeProcessAlert(String.valueOf(purchaseOrder.getPurchaseCode()),
										"Purchase Order");
								processAlerts.terminate();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							msg = "Your application has been sent for approval successfully.\n Reference ID:-"
									+ purchaseOrder.getReferenceId();

						}
						addActionMessage(msg);

						// ------------------------Process Manager
						// Alert------------------------start
						try {
							String applnID = purchaseOrder.getPurchaseCode();
							String module = "Purchase Order";
							String applicant = purchaseOrder.getEmpCode();
							String approver = String.valueOf(empFlow[0][0]);
							String applnDate = purchaseOrder.getOrderDate();
							String keepInfo[] = request
									.getParameterValues("keepInformedEmpId");
							System.out
									.println("The check befor : sendApplicationAlert : *************");
							
							
							
							
							
							sendApplicationAlert(applnID, module, applicant,
									approver, applnDate, purchaseOrder
											.getHiddenStatus(), keepInfo,empFlow );
							System.out
									.println("The check after : sendApplicationAlert : *************");
						} catch (Exception e) {
							logger.error(e);
						}
						// ------------------------Process Manager
						// Alert------------------------end
						model.getComponyLogo(request);
						model.showRecords(purchaseOrder);
						purchaseOrder
								.setStatus(purchaseOrder.getHiddenStatus());
						if (purchaseOrder.getStatus().equals("F")) {
							if (purchaseOrder.getSource().equals("mymessages")) {
								return "mymessages";
							} 
							else if (purchaseOrder.getSource().equals("myservices"))
							{
								return "serviceJsp";
							}
							else{
							return input();
							}
						}
						getNavigationPanel(3);
						// reset();
					} else {
						addActionMessage("Record cant be saved.");
						model.showList(purchaseOrder, itemCode, itemName,
								price, unit, quantity, total, city);
						getNavigationPanel(3);
					}

				} else {

					result = model.update(purchaseOrder, itemCode, price,
							quantity, total, empFlow, request);
					if (result) {

						if (purchaseOrder.getHiddenStatus().equals("D")) {
							
							try {
								sendProcessManagerAlertDraft();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							msg = "Application drafted successfully \n Reference ID:-"
									+ purchaseOrder.getPurchaseOrderNo();
						}

						if (purchaseOrder.getHiddenStatus().equals("P")) {
							msg = "Your application has been sent for approval successfully.\n Reference ID:-"
									+ purchaseOrder.getPurchaseOrderNo();
						}
						if (purchaseOrder.getHiddenStatus().equals("A")) {
							msg = "Application approved successfully .\n Reference ID:-"
									+ purchaseOrder.getPurchaseOrderNo();
						}
						if (purchaseOrder.getHiddenStatus().equals("W")) {
							msg = "Application has been withdrawn successfully. \n Reference ID:-"
									+ purchaseOrder.getPurchaseOrderNo();
						}
						if (purchaseOrder.getHiddenStatus().equals("B")) {
							msg = "Application sendback successfully. \n Reference ID:-"
									+ purchaseOrder.getPurchaseOrderNo();
						}

						addActionMessage(msg);

						// ------------------------Process Manager
						// Alert------------------------start
						try {
							String applnID = purchaseOrder.getPurchaseCode();
							String module = "Purchase Order";
							String applicant = purchaseOrder.getEmpCode();
							String approver = String.valueOf(empFlow[0][0]);
							String applnDate = purchaseOrder.getOrderDate();
							String keepInfo[] = request
									.getParameterValues("keepInformedEmpId");
							System.out
									.println("The check befor : sendApplicationAlert : *************");
							
							/**
							 * Remove Process manager alert entry from mypage
							 */
							if (purchaseOrder.getHiddenStatus().equals("P"))
							{
							try {
								MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
								processAlerts.initiate(context, session);
								processAlerts.removeProcessAlert(purchaseOrder
										.getPurchaseCode(), "Purchase Order");
								processAlerts.terminate();
							} catch (Exception e) {
								e.printStackTrace();
							}
							}
							
							
							sendApplicationAlert(applnID, module, applicant,
									approver, applnDate, purchaseOrder
											.getHiddenStatus(), keepInfo,empFlow);
							System.out
									.println("The check after : sendApplicationAlert : *************");
						} catch (Exception e) {
							logger.error(e);
						}
						// ------------------------Process Manager
						// Alert------------------------end

						model.showRecords(purchaseOrder);
						purchaseOrder
								.setStatus(purchaseOrder.getHiddenStatus());
						if (purchaseOrder.getStatus().equals("F")) {
							if (purchaseOrder.getSource().equals("mymessages")) {
								return "mymessages";
							} 
							else if (purchaseOrder.getSource().equals("myservices"))
							{
								return "serviceJsp";
							}
							else{
							return input();
							}
						}
						getNavigationPanel(3);
						// reset();
					} else {
						addActionMessage("Record cant be updated.");
						model.showList(purchaseOrder, itemCode, itemName,
								price, unit, quantity, total, city);
						getNavigationPanel(3);
					}
				}
			} else {
				addActionMessage("Reporting structure not defined for the employee\n"
						+ purchaseOrder.getEmpName());
				addActionMessage("Please contact HR manager.");

				getNavigationPanel(2);
				purchaseOrder.setEnableAll("Y");
				showList();
			}
			model.getComponyLogo(request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (purchaseOrder.getHiddenStatus().equals("P")) {
			
			if (purchaseOrder.getSource().equals("mymessages")) {
				return "mymessages";
			} 
			else if (purchaseOrder.getSource().equals("myservices"))
			{
				return "serviceJsp";
			}
			else{
			return input();
			}
		}
		
		if (purchaseOrder.getSource().equals("mymessages")) {
			return "mymessages";
		} 
		else if (purchaseOrder.getSource().equals("myservices"))
		{
			return "serviceJsp";
		}
		else{
		return SUCCESS;
		}
	}

	public String saveByApprover() {
		boolean result = false;
		try {
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			String[] quantity = request.getParameterValues("quantityIterator");
			String[] total = request.getParameterValues("totalIterator");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			result = model.saveByApprover(purchaseOrder, itemCode, price,
					quantity, total);
			if (result) {
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage("Record can't be updated .");
			}
			getNavigationPanel(2);
			showList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String showRecords() {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.showRecords(purchaseOrder);
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			model.terminate();
			purchaseOrder.setEnableAll("N");
			getNavigationPanel(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/*
	 * method name : callPage purpose : to displays the records in the form
	 * return type : String parameter : none
	 */
	public String callPage() throws Exception {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.showList(purchaseOrder, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;

	}

	public String getPrice() {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.showPrice(purchaseOrder);
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			model.getComponyLogo(request);
			model.setKeepInformList(request, purchaseOrder);
			showList();
			purchaseOrder.setEnableAll("Y");
			model.terminate();
			if (purchaseOrder.getStatus().equals("D"))
				getNavigationPanel(3);
			else
				getNavigationPanel(12);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return reset1();
	}

	public String deletePurchaseOrder() {
		try {
			boolean result = false;
			logger.info("approvercode in delete action before model"
					+ purchaseOrder.getApproverCode());
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			result = model.delete(purchaseOrder);
			// result = model.cancelAppl(purchaseOrder);
			if (result) {
				addActionMessage("Record deleted successfully");

			} else {
				addActionMessage("Record can't be deleted.");
			}
			purchaseOrder.setListType("pending");
			model.getAllTypeOfApplications(purchaseOrder, request,
					purchaseOrder.getUserEmpId());
			purchaseOrder.setStatus("");
			purchaseOrder.setHiddenStatus("");
			
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				processAlerts.removeProcessAlert(purchaseOrder
						.getPurchaseCode(), "Purchase Order");
				processAlerts.terminate();
			// model.showList(purchaseOrder, request);
			model.terminate();
			getNavigationPanel(1);

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (purchaseOrder.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (purchaseOrder.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return INPUT;
		}	
	}

	public String delete() {
		boolean result = false;
		logger.info("approvercode in delete action before model"
				+ purchaseOrder.getApproverCode());
		PurchaseOrderModel model = new PurchaseOrderModel();
		model.initiate(context, session);
		// result = model.delete(purchaseOrder);
		result = model.cancelAppl(purchaseOrder);
		if (result) {
			deleteProcessManagerAlert("Purchase Order", purchaseOrder.getPurchaseCode());
			addActionMessage("Record deleted successfully");
			// ------------------------Process Manager
			// Alert------------------------start
			 
			// ------------------------Process Manager
			// Alert------------------------end
			reset();
		} else {
			addActionMessage("Record can't be canceled.");
		}
		if (!purchaseOrder.getEmpCode().equals("")) {
			setApproverList(purchaseOrder.getEmpCode());
		}

		model.terminate();
		return SUCCESS;
	}
	
	
	public void deleteProcessManagerAlert(String moduleName,
			String purchaseCode) {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ purchaseCode
					+ " AND UPPER(ALERT_MODULE) LIKE '"
					+ moduleName.trim().toUpperCase() + "'";
			model.getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String resetDetails() {
		purchaseOrder.setPrice("");
		/*
		 * purchaseOrder.setVendorCode(""); purchaseOrder.setVendorName("");
		 */
		purchaseOrder.setQuantity("");
		purchaseOrder.setSubTypeCode("");
		purchaseOrder.setSubTypeName("");
		purchaseOrder.setUnit("Unit");
		purchaseOrder.setParaId("");
		purchaseOrder.setTotalPrice("");
		// purchaseOrder.setCityName("");
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String callByApprover() {
		try {
			
		 	String source =request.getParameter("src");
			System.out.println("source--------------" + source);
			purchaseOrder.setSource(source);
			
			String purchaseCode = request.getParameter("hiddenPurchaseCode");
			
			String level = request.getParameter("level");
			
			
			 purchaseOrder.setApplicationLevel(Integer.parseInt(level));
			
			
			purchaseOrder.setPurchaseCode(purchaseCode);
			purchaseOrder.setIsApprove("true");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.showEmpData(purchaseOrder);
			model.showRecords(purchaseOrder);
			model.showApproverComments(purchaseOrder);
			model.getComponyLogo(request);
			purchaseOrder.setKeepInfoFlag("false");
			purchaseOrder.setInsertFlag("false");
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			String query = " SELECT PURCHASE_APPROVER_ID,PURCHASE_STATUS FROM HRMS_ASSET_PURCHASE_HDR WHERE PURCHASE_EMP_CODE="
					+ purchaseOrder.getEmpCode()
					+ " AND PURCHASE_CODE="
					+ purchaseOrder.getPurchaseCode();
			Object approverIdObj[][] = model.getSqlModel().getSingleResult(
					query);
			Object[][] empFlow = generateEmpFlow(purchaseOrder.getEmpCode(),
					"Purchase", purchaseOrder.getApplicationLevel());
			String approverId = "";
			if (empFlow != null && empFlow.length > 0) {

				approverId = String.valueOf(empFlow[0][0]);
			}
			if (approverIdObj != null && approverIdObj.length > 0) {
				if (String.valueOf(approverIdObj[0][0]).equals(approverId)
						&& String.valueOf(approverIdObj[0][1]).equals("P")) {
					getNavigationPanel(8);
					purchaseOrder.setApproverCommentsFlag("true");
					purchaseOrder.setHiddenPurchaseCode(purchaseCode);
				} else {
					purchaseOrder.setEnableAll("N");
					getNavigationPanel(9);
				}

			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String callforedit() {
		try {
			
			//String purchaseCode = request.getParameter("hiddencode");
			

		 	String source =request.getParameter("src");
					System.out.println("source--------------" + source);
					purchaseOrder.setSource(source);
					
			
		 String purchaseCode =request.getParameter("applicationCode");
		 
		 String applStatus = request.getParameter("applStatus");
			
			
			purchaseOrder.setPurchaseCode(purchaseCode);
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.showEmpData(purchaseOrder);
			boolean isKeepInformedLogin =model.showRecords(purchaseOrder);
			model.getComponyLogo(request);
			purchaseOrder.setStatus(applStatus);
			String s = purchaseOrder.getHiddenStatus();
			if (s.equals("D")) {
				getNavigationPanel(3);
				// model.showApproverComments(purchaseOrder);
			}
			if (s.equals("P")) {
				getNavigationPanel(4);
				purchaseOrder.setEnableAll("N");
				model.showApproverComments(purchaseOrder);
				purchaseOrder.setKeepInfoFlag("false");
				purchaseOrder.setInsertFlag("false");
			}
			if (s.equals("F")) {
				getNavigationPanel(6);
				purchaseOrder.setEnableAll("N");
				model.showApproverComments(purchaseOrder);
				purchaseOrder.setIsApprove("true");
				purchaseOrder.setCommentFlag("true");
				purchaseOrder.setKeepInfoFlag("false");
				purchaseOrder.setInsertFlag("false");
			}
			if (s.equals("B")) {
				// purchaseOrder.setIsSentBack("true");
				getNavigationPanel(5);
				purchaseOrder.setCommentFlag("true");
				model.showApproverComments(purchaseOrder);
				// assetEmployee.setAppCommentFlag("true");
			}
			if (s.equals("A") || s.equals("R")||s.equals("W")) {
				getNavigationPanel(6);
				purchaseOrder.setEnableAll("N");
				purchaseOrder.setIsApprove("true");
				purchaseOrder.setCommentFlag("true");
				model.showApproverComments(purchaseOrder);
				purchaseOrder.setKeepInfoFlag("false");
				purchaseOrder.setInsertFlag("false");
			}
			if (s.equals("S")) {
				getNavigationPanel(6);
				purchaseOrder.setEnableAll("N");
				purchaseOrder.setIsApprove("true");
				purchaseOrder.setCommentFlag("true");
				model.showApproverComments(purchaseOrder);
				purchaseOrder.setKeepInfoFlag("false");
				purchaseOrder.setInsertFlag("false");
			}
			boolean isApproverLogin=false;
			if (!purchaseOrder.getEmpCode().equals("")) {
				isApproverLogin =setApproverList(purchaseOrder.getEmpCode());
			}
			
			if(isKeepInformedLogin)
			{
				getNavigationPanel(6);
				purchaseOrder.setEnableAll("N");
				purchaseOrder.setIsApprove("true");
				purchaseOrder.setCommentFlag("true");
				model.showApproverComments(purchaseOrder);
				purchaseOrder.setKeepInfoFlag("false");
				purchaseOrder.setInsertFlag("false");
			}
			System.out.println("isApproverLogin------"+isApproverLogin);
			if(isApproverLogin)
			{
				getNavigationPanel(6);
				purchaseOrder.setEnableAll("N");
				purchaseOrder.setIsApprove("true");
				purchaseOrder.setCommentFlag("true");
				model.showApproverComments(purchaseOrder);
				purchaseOrder.setKeepInfoFlag("false");
				purchaseOrder.setInsertFlag("false");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String withdrawApplication() {
		PurchaseOrderModel model = new PurchaseOrderModel();
		model.initiate(context, session);
		try {
			boolean result = model.withdrawApplication(purchaseOrder);
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
		purchaseOrder.setListType("pending");
		model.getAllTypeOfApplications(purchaseOrder, request, purchaseOrder
				.getUserEmpId());
		model.terminate();
		if (purchaseOrder.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (purchaseOrder.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return INPUT;
		}	
	}

	public String callForInward() {
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			String purchaseCode = request.getParameter("hiddenPurchaseCode");
			model.initiate(context, session);
			purchaseOrder.setPurchaseCode(purchaseCode);
			model.showEmpData(purchaseOrder);
			model.showRecordsForInward(purchaseOrder);
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			purchaseOrder.setInwardFlag("true");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "PurchaseInwardDetails";
	}

	public String report() {
		try {

			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);

			Object[][] empFlow = generateEmpFlow(purchaseOrder.getEmpCode(),
					"Purchase", 2);
			// Object[][]
			// empFirstFlow=generateEmpFlow(purchaseOrder.getEmpCode(),
			// "Purchase")

			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			Object[][] empFlow1 = model1.generateEmpFlow(purchaseOrder
					.getEmpCode(), "Purchase");
			model.getComponyLogo(request);

			String fileName = (String) request.getAttribute("logo");

			String filePath = context.getRealPath("/") + "pages/Logo/"
					+ session.getAttribute("session_pool") + "/" + fileName;

			model.genReport(request, response, purchaseOrder, filePath,
					empFlow, empFlow1);
			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String cancelItem() {
		boolean result = false;
		PurchaseOrderModel model = new PurchaseOrderModel();
		model.initiate(context, session);
		result = model.cancelItem(purchaseOrder);
		if (result) {
			addActionMessage("Item canceled succesfully.");
		} else {
			addActionMessage("Item can't be canceled.");
		}
		model.showRecordsForInward(purchaseOrder);
		model.terminate();
		return "PurchaseInwardDetails";
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String addKeepInformedEmpList() {

		try {

			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("keepInformedEmpId");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpName");// keep informed
			// employee name
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, purchaseOrder);
			model.setKeepInformed(serialNo, empCode, empName, purchaseOrder);
			model.getComponyLogo(request);
			model.setAssetList(request, purchaseOrder);
			if (!purchaseOrder.getEmpCode().equals("")) {
				setApproverList(purchaseOrder.getEmpCode());
			}

			purchaseOrder.setEmployeeId("");
			purchaseOrder.setEmployeeName("");
			purchaseOrder.setEmployeeToken("");
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addKeepInformedEmpList-----" + e);
		}
		if (purchaseOrder.getStatus().equals("D"))
			getNavigationPanel(3);
		else
			getNavigationPanel(12);
		return SUCCESS;
	}// end of addKeepInformedEmpList

	public String removeKeepInformed() {
		try {
			if (!purchaseOrder.getEmpCode().equals("")) {
				setApproverList(purchaseOrder.getEmpCode());
			}

			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			model.removeKeepInformedData(serialNo, empCode, empName,
					purchaseOrder);
			model.setAssetList(request, purchaseOrder);
			model.getComponyLogo(request);
			purchaseOrder.setEmployeeName("");
			purchaseOrder.setEmployeeId("");
			purchaseOrder.setEmployeeToken("");
			purchaseOrder.setStatus(purchaseOrder.getHiddenStatus());
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformed--------" + e);
		}
		if (purchaseOrder.getStatus().equals("D"))
			getNavigationPanel(3);
		else
			getNavigationPanel(12);
		return SUCCESS;

	}

	public boolean setApproverList(String empCode) {
		boolean flag =false;
		try {
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			Object[][] empFlow = model1.generateEmpFlow(empCode, "Purchase");
			  flag =model.setApproverData(purchaseOrder, empFlow);
			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
		return flag ;
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
		PurchaseOrderModel model = new PurchaseOrderModel();
		model.initiate(context, session);
		logger.info("general flag==" + purchaseOrder.isGeneralFlag());
		if (purchaseOrder.isGeneralFlag()) {
			purchaseOrder.setEmpCode(purchaseOrder.getUserEmpId());
			// model.setEmpData(assetEmployee);
			model.getEmployeeDetails(purchaseOrder);
		}

		if (!purchaseOrder.getEmpCode().equals("")) {
			setApproverList(purchaseOrder.getEmpCode());
		} else {
			setApproverList(purchaseOrder.getUserEmpId());
		}

		model.terminate();
	}

	public void sendApplicationAlert(String applnID, String module,
			String applicant, String approver, String applnDate, String status,
			String keepInfo[], Object[][] empFlow) {
		try {
			String msgType = "";
			String level = "1";
			if (status.equals("P")) {
				msgType = "A";
				// String level = "1";

				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);

				template1.setEmailTemplate("PURCHASE -MAIL TO APPLICANT");

				template1.getTemplateQueries();

				EmailTemplateQuery templateQuery11 = template1
						.getTemplateQuery(1); // FROM
				// EMAIL

				EmailTemplateQuery templateQuery12 = template1
						.getTemplateQuery(2); // TO
				// EMAIL
				templateQuery12.setParameter(1, applicant);

				EmailTemplateQuery templateQuery13 = template1
						.getTemplateQuery(3);
				templateQuery13.setParameter(1, applnID);

				EmailTemplateQuery templateQuery14 = template1
						.getTemplateQuery(4);
				templateQuery14.setParameter(1, applnID);

				EmailTemplateQuery templateQuery15 = template1
						.getTemplateQuery(5);
				templateQuery15.setParameter(1, applnID);

				template1.configMailAlert();
				
				String applicantID =purchaseOrder.getEmpCode();
				
				
				try {
					
					// create process alerts
					String keepInformId = "";
					try {
						if (keepInfo != null) {
							for (int i = 0; i < keepInfo.length; i++) {
								if (i == keepInfo.length - 1) {
									keepInformId += String.valueOf(keepInfo[i]);
								} else {
									keepInformId += String.valueOf(keepInfo[i]) + ",";

								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			 
				String link = "/asset/PurchaseOrder_callforedit.action";
				
				String linkParam = "applicationCode=" + applnID + "&applStatus=P";
				
				
					template1.sendProcessManagerAlert("", module, "I",
							applnID, level, linkParam, link, "Pending",
							applicantID, "",keepInformId, applicantID,applicantID);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			/*	template1.sendProcessManagerAlert(approver, module, msgType,
						applnID, level);*/

				template1.sendApplicationMail();
				template1.clearParameters();
				template1.terminate();

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template
						.setEmailTemplate("PURCHASE ORDER -APPLICANT TO APPROVER");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, applicant);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, approver);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applnID);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);

				template.configMailAlert();

			/*	String[] link_parameter = new String[3];
				String[] link_label = new String[3];
				// String applicationType = "TYD";
				String applicationType = "PurchaseOrderAppl";
				link_parameter[0] = applicationType + "#" + approver
						+ "#applicationDtls#";

				String link = "/asset/PurchaseOrder_callByApprover.action?hiddenPurchaseCode="
						+ applnID + "$status=P$applicationLevel=1";
				// link= PPEncrypter.encrypt(link);
				System.out.println("applicationDtls  ..." + link);
				link_parameter[0] += link;
				link_label[0] = "here";
*/
				
			
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				String applicationType = "PurchaseOrderAppl";
				link_param[0] = applicationType + "#" + applicantID + "#" + applnID
						+ "#" + "A" + "#" + "..." + "#" + approver + "#" + level;
				link_param[1] = applicationType + "#" + applicantID + "#" + applnID
						+ "#" + "R" + "#" + "..." + "#" + approver + "#" + level;
				link_param[2] = applicationType + "#" + applicantID + "#" + applnID
						+ "#" + "B" + "#" + "..." + "#" + approver + "#" + level;
				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";
				
				
				if (keepInfo != null) {
					template.sendApplicationMailToKeepInfo(keepInfo);
				}
				//template.addOnlineLink(request, link_parameter, link_label);

				template.addOnlineLink(request, link_param, link_label);

				
			  String	alertlink = "/asset/PurchaseOrder_callByApprover.action";
			  String	linkParam = "hiddenPurchaseCode=" + applnID
						+ "&level=1";
				try {
					template.sendProcessManagerAlert(approver, module, msgType,
							applnID, level, linkParam, alertlink, "Pending",
							applicantID, String.valueOf(empFlow[0][3]), "", "",applicantID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
		/*		template.sendProcessManagerAlert(approver, module, msgType,
						applnID, level);
*/
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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
		// logger.info("value of str-----------------" + str);

		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "	FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S' ";
			if (purchaseOrder.getUserProfileDivision() != null
					&& purchaseOrder.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN ("
						+ purchaseOrder.getUserProfileDivision() + ")";
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

	/*
	 * method name : delete1 purpose : to delete the record selected by check on
	 * the Check Box return type : String parameter : none
	 */

	public String delete1() throws Exception {
		try {
			String code[] = request.getParameterValues("hdeleteCode");
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			boolean result = model.deletecheckedRecords(purchaseOrder, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
			}// end of if
			else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}// end of else
			model.showList(purchaseOrder, request);
			getNavigationPanel(1);
			model.terminate();
			reset();
			setApproverList(purchaseOrder.getEmpCode());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;

	}

	public String cancel() {
		try {
			reset();
			return input();
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
		System.out.println("sendProcessManagerAlertDraft call -----");
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String empID = purchaseOrder.getEmpCode();
			String module = "Purchase Order";
			String applnID =purchaseOrder.getPurchaseCode();
			String level = "1";
			
			String link = "/asset/PurchaseOrder_callforedit.action";
			
			String linkParam = "applicationCode=" + applnID + "&applStatus=D";
			
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", purchaseOrder
					.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "Purchase Order");
			template.sendProcessManagerAlertDraft(empID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					empID,empID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	
	
	/*This method is used to send withdraw email to Applicant Approver and Keep Informed to*/
	public void sendProcessManagerAlertForWithdraw() {
		try {
			
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(String.valueOf(purchaseOrder.getPurchaseCode()),
					"Purchase Order");
			processAlerts.terminate();
			
			PurchaseOrderModel model = new PurchaseOrderModel();
			model.initiate(context, session);
			
			String query =" SELECT PURCHASE_APPROVER_ID ,PURCHASE_KEEP_INFORM FROM  HRMS_ASSET_PURCHASE_HDR WHERE PURCHASE_CODE="+purchaseOrder.getPurchaseCode();
			
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
			String applicantID = purchaseOrder.getEmpCode();
			String module = "Purchase Order";
			String applnID = purchaseOrder.getPurchaseCode();
			String level = "1";
			String link = "/asset/PurchaseOrder_callforedit.action";
			String linkParam = "applicationCode=" + applnID + "&applStatus=W";
			String message = alertProp.getProperty("withdrawAlertMessage");
			message = message.replace("<#EMP_NAME#>", purchaseOrder.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "Purchase Order");
			template.sendProcessManagerAlertWithdraw(applicantID, module, "I",
					applnID, level, linkParam, link, message, "Withdraw",
					applicantID, applicantID,keepInformedId,managerId);
			template.terminate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	
 

}
