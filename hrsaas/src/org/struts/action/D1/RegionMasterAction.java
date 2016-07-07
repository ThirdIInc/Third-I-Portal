package org.struts.action.D1;

import org.paradyne.bean.D1.RegionMasterBean;
import org.paradyne.bean.LMS.EmployeeTypeBean;
import org.paradyne.model.D1.BusinessUnitModel;
import org.paradyne.model.D1.RegionMasterModel;
import org.paradyne.model.TravelManagement.Master.CityGradeModel;
import org.paradyne.model.admin.master.DeptModel;
import org.struts.lib.ParaActionSupport;

public class RegionMasterAction extends ParaActionSupport {

	RegionMasterBean regionBean;

	public void prepare_local() throws Exception {

		regionBean = new RegionMasterBean();
		regionBean.setMenuCode(1162);
	}

	public Object getModel() {

		return regionBean;
	}

	public RegionMasterBean getRegionBean() {
		return regionBean;
	}

	public void setRegionBean(RegionMasterBean regionBean) {
		this.regionBean = regionBean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		RegionMasterModel model = new RegionMasterModel();
		model.initiate(context, session);
		model.initialData(regionBean, request);

		model.terminate();

	}

	public String input() throws Exception {
		System.out.println("Inside Input method-------->");
		RegionMasterModel model = new RegionMasterModel();
		model.initiate(context, session);
		model.initialData(regionBean, request);
		getNavigationPanel(1);
		return "input";
	}

	/** Add New Function* */
	public String addNew() {
		try {

			getNavigationPanel(2);
			reset();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		RegionMasterModel model = new RegionMasterModel();
		model.initiate(context, session);
		model.initialData(regionBean, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";
	}

	/** Method call for Saving Data* */
	public String save() throws Exception {
		try {
			RegionMasterModel model = new RegionMasterModel();
			model.initiate(context, session);
			boolean result;
			System.out.println("RegionCode here -------> "
					+ regionBean.getRegionCode());

			if (regionBean.getRegionCode().equals("")) {
				result = model.addRegionData(regionBean);
				System.out.println("result here we get is --------------->"
						+ result);
				if (result) {

					addActionMessage(getMessage("save"));
					
				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
				}
			} else {

				result = model.update(regionBean);

				if (result) {
					addActionMessage(getMessage("update"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		regionBean.setEnableAll("N");
		return "success";

	}

	/** This method is called on reset button */
	public String reset() {

		regionBean.setRegionName("");
		regionBean.setRegionCode("");
		getNavigationPanel(2);
		return "success";

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		try {
			RegionMasterModel model = new RegionMasterModel();
			model.initiate(context, session);
			model.calforedit(regionBean);
			getNavigationPanel(3);
			regionBean.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/** This method is called on back button */
	public String cancel() {
		try {
			getNavigationPanel(1);
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);

			return "input";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 */

	public String deleteChkRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		RegionMasterModel model = new RegionMasterModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(regionBean, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.initialData(regionBean, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";

	}

	public String delete() throws Exception {
		RegionMasterModel model = new RegionMasterModel();
		model.initiate(context, session);
		System.out.println("delete method ------------------------");
		boolean result;
		result = model.delete(regionBean, request);
		model.initialData(regionBean, request);
		model.terminate();

		if (result) {
			addActionMessage("Record Deleted successfully.");

		}

		getNavigationPanel(1);

		return "input";
	}

	public String setRecord() throws Exception {
		try {
			RegionMasterModel model = new RegionMasterModel();
			model.initiate(context, session);
			model.terminate();
			getNavigationPanel(3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		regionBean.setEnableAll("N");
		return "success";
	}

	/** This f9action is for Search pop up window */
	public String f9action() throws Exception {

		String query = "  SELECT REGION_NAME,REGION_ID FROM HRMS_D1_REGION ORDER BY REGION_NAME";

		String[] headers = { getMessage("region_name") };

		String[] headerWidth = { "50" };

		String[] fieldNames = { "regionName", "regionCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "Region_setRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}
