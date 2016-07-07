package org.struts.action.D1;

import org.paradyne.bean.D1.VenodrMasterBean;
import org.paradyne.model.D1.ApprovalSettingsModel;
import org.paradyne.model.D1.RegionMasterModel;
import org.paradyne.model.D1.VendorModel;
import org.struts.lib.ParaActionSupport;

public class VendorMasterAction extends ParaActionSupport {

	VenodrMasterBean vendorBean;

	public void prepare_local() throws Exception {

		vendorBean = new VenodrMasterBean();
		vendorBean.setMenuCode(2007);

	}

	public Object getModel() {

		return vendorBean;
	}

	public VenodrMasterBean getVendorBean() {
		return vendorBean;
	}

	public void setVendorBean(VenodrMasterBean vendorBean) {
		this.vendorBean = vendorBean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		VendorModel model = new VendorModel();
		model.initiate(context, session);
		model.terminate();

	}

	public String callPage() throws Exception {

		VendorModel model = new VendorModel();
		model.initiate(context, session);
		input();
		getNavigationPanel(1);
		model.terminate();

		return "input";
	}

	public String input() {
		System.out.println("inside input method");
		VendorModel model = new VendorModel();
		model.initiate(context, session);
		model.onloadListData(vendorBean, request);
		getNavigationPanel(1);
		return "input";

	}

	public String addNew() {
		VendorModel model = new VendorModel();
		model.initiate(context, session);
		reset();
		getNavigationPanel(2);
		return "success";
	}

	/** Method call for Saving Data* */
	public String saveVendorData() throws Exception {
		try {

			System.out.println("in save call-------------------------");
			VendorModel model = new VendorModel();
			model.initiate(context, session);
			boolean result;
			System.out.println("VendorCode here ------- "
					+ vendorBean.getVendorCode());

			if (vendorBean.getVendorCode().equals("")) {
				result = model.insertVendorData(vendorBean);
				System.out.println("result here we get is --------------->"
						+ result);
				if (result) {

					addActionMessage(getMessage("save"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
					// new added
				}
			} else {

				result = model.updateVendorData(vendorBean);

				if (result) {
					addActionMessage(getMessage("update"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();// new added

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		vendorBean.setEnableAll("N");
		return "success";

	}

	public String reset() {
		vendorBean.setVendorName("");
		vendorBean.setEinNumber("");
		vendorBean.setPostboxAddress("");
		vendorBean.setCity("");
		vendorBean.setState("");
		vendorBean.setZip("");
		vendorBean.setPhoneNumber("");
		vendorBean.setContactName("");
		vendorBean.setComments1("");
		vendorBean.setComments2("");
		vendorBean.setSendPO("");
		vendorBean.setDiscPercent("");
		vendorBean.setNetDays("");
		vendorBean.setRemitAddress("");
		vendorBean.setRemitName("");
		vendorBean.setRemitCity("");
		vendorBean.setRemitZip("");
		vendorBean.setRemitState("");
		vendorBean.setRemitAddress("");
		vendorBean.setRemitName("");
		vendorBean.setRemitCity("");
		vendorBean.setFreightMessage("");
		vendorBean.setMinOrder("");
		vendorBean.setFob("");
		vendorBean.setTaxMessage("");
		vendorBean.setClassCode("");
		vendorBean.setShipVia("");
		vendorBean.setVendorCode("");

		getNavigationPanel(2);
		return "success";
	}

	public String cancel() {
		input();
		getNavigationPanel(1);
		vendorBean.setEnableAll("Y");
		return "input";
	}

	/** This f9action is for Search pop up window */
	public String f9city() throws Exception {

		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION";

		String[] headers = { "CityId", getMessage("city") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "cityId", "city" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "VendorMaster_getState.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String getState() throws Exception {
		VendorModel model = new VendorModel();
		model = new VendorModel();
		model.initiate(context, session);
		model.getStateCountry(vendorBean);
		model.terminate();
		getNavigationPanel(2);
		vendorBean.setEnableAll("Y");
		return SUCCESS;
	}

	/** This f9action is for Search pop up window */
	public String f9remitcity() throws Exception {

		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION";

		String[] headers = { "RemitCityId", getMessage("city") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "remitCityId", "remitCity" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "VendorMaster_getremitState.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String getremitState() throws Exception {
		VendorModel model = new VendorModel();
		model = new VendorModel();
		model.initiate(context, session);
		model.getremitStateCountry(vendorBean);
		model.terminate();
		getNavigationPanel(2);
		vendorBean.setEnableAll("Y");
		return SUCCESS;
	}

	public String delete() throws Exception {
		VendorModel model = new VendorModel();
		model.initiate(context, session);
		System.out.println("delete method ------------------------");
		boolean result;
		result = model.delete(vendorBean, request);
		model.onloadListData(vendorBean, request);
		model.terminate();

		if (result) {
			addActionMessage("Record Deleted successfully.");

		}

		getNavigationPanel(1);

		return "input";
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 */

	public String deleteChkRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		VendorModel model = new VendorModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(vendorBean, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.onloadListData(vendorBean, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";

	}

	/** Method call on Double clicked on jsp for edit* */
	public String editVendorApp() {
		try {
			VendorModel model = new VendorModel();
			model.initiate(context, session);
			model.editVendorApp(vendorBean, request);
			request.setAttribute("sendPO", vendorBean.getRadioValue());
			getNavigationPanel(3);
			vendorBean.setEnableAll("N");
			model.terminate();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

	/* Search Button Functionality */

	public String setRecord() throws Exception {
		try {
			VendorModel model = new VendorModel();
			model.initiate(context, session);
			model.editVendorApp(vendorBean,request);
			model.terminate();
			getNavigationPanel(3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		vendorBean.setEnableAll("N");
		return "success";
	}

	/** This f9action is for Search pop up window */
	public String f9action() throws Exception {

		String query = "SELECT VENDOR_EIN_NUMBER,VENDOR_NAME,VENDOR_PO_ADDRESS,VENDOR_ZIP, HRMS_LOCATION.LOCATION_NAME,"
				+ "VENDOR_PHONE_NO,VENDOR_SEND_PO,VENDOR_CONTACT_NAME,VENDOR_REMIT_NAME,VENDOR_REMIT_ADDRESS,VENDOR_REMIT_CITY,VENDOR_REMIT_STATE,"
				+ "VEDOR_DISC_PERCENT,VENDOR_NET_DAYS,VENDOR_COMMENTS1,VENDOR_CLASS_CODE,VENDOR_MIN_ORDER,VENDOR_FRIEGHT_MSG,"
				+ "VENDOR_TAX_MSG,VENDOR_SHIP_VIA,VENDOR_FOB,VENDOR_COMMENTS2,VENDOR_ID"
				+ " FROM HRMS_D1_VENDOR "
				+ " LEFT JOIN HRMS_LOCATION on(HRMS_LOCATION.LOCATION_CODE = HRMS_D1_VENDOR.VENDOR_ID)";

		String[] headers = { getMessage("ein"), getMessage("vendor_name") };

		String[] headerWidth = { "15", "25" };

		String[] fieldNames = { "einNumber", "vendorName", "postboxAddress",
				"zip", "city", "phoneNumber", "sendPO", "contactName",
				"remitName", "remitAddress", "remitCity", "remitState",
				"discPercent", "netDays", "comments1", "classCode", "minOrder",
				"freightMessage", "taxMessage", "shipVia", "fob", "comments2",
				"vendorCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18, 19, 20, 21, 22 };

		String submitFlag = "true";

		String submitToMethod = "VendorMaster_setRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}
