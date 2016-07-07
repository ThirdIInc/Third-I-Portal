/*
 * @author SaiPavanKumar 
 * Date:11-08-2008
 */
package org.struts.action.TravelManagement;

import org.paradyne.bean.TravelManagement.TravelAdminMaster;
import org.paradyne.model.TravelManagement.TravelAdminMasterModel;
import org.struts.lib.ParaActionSupport;

public class TravelAdminMasterAction extends ParaActionSupport {

	TravelAdminMaster Travelbean;

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		Travelbean = new TravelAdminMaster();
		Travelbean.setMenuCode(672);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return Travelbean;
	}

	public TravelAdminMaster getTravelbean() {
		return Travelbean;
	}

	public void setTravelbean(TravelAdminMaster travelbean) {
		Travelbean = travelbean;
	}

	public String f9emp() {

		try {
			String query = "SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as name, EMP_ID "
					+ "FROM HRMS_EMP_OFFC ";

			query += getprofileQuery(Travelbean);
			query += " AND EMP_STATUS='S'";
			query += "	ORDER BY upper(name)";

			String[] headers = { "Employee ID", getMessage("travel.empname") };
			String[] headerwidth = { "40", "60" };
			String[] fieldNames = { "empToken", "Employeename", "empid" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";

	}

	public String f9branch() {
		/*System.out.println("ssstarting...!");
		Travelbean.setEmpid("");
		Travelbean.setEmployeename("");
		System.out.println("eeeeeeeeeeeeee...!"+Travelbean.getEmployeename());
		System.out.println("eeeeeeeeeeeeee...!"+Travelbean.getEmpid());*/

		String query = " Select CENTER_ID,CENTER_NAME from hrms_center order by CENTER_ID";
		String[] headers = { "Branch ID", getMessage("travel.branch") };

		String[] headerwidth = { "40", "60" };

		String[] fieldNames = { "Branchcode", "Branchname" };

		int[] columnIndex = { 0, 1 };

		String submitFlage = "true";

		String submitToMethod = "TravelAdminMaster_reset1.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		/*System.out.println("sssssssssssssssssssss");
		Travelbean.setEmpid("");
		Travelbean.setEmployeename("");*/
		System.out.println("eeeeeeeeeeeeee...!" + Travelbean.getBranchname());
		return "f9page";

	}

	public String f9search() {

		String query = " select TRAVEL_ADMIN_CODE,HRMS_CENTER.CENTER_NAME,(office.EMP_FNAME||' '||office.EMP_MNAME||' '||office.EMP_LNAME) as name "
				+ " ,TRAVEL_ADMIN_BRANCH_CODE,TRAVEL_ADMIN_EMP_ID from hrms_travel_admin "
				+ " Left join  hrms_emp_offc office on(office.EMP_ID=hrms_travel_admin.TRAVEL_ADMIN_EMP_ID)"
				+ " Left join  HRMS_CENTER  on(HRMS_CENTER.CENTER_ID=hrms_travel_admin.TRAVEL_ADMIN_BRANCH_CODE)"
				+ " order by TRAVEL_ADMIN_CODE";
		String[] headers = { "Travel Code", getMessage("travel.branch"),
				getMessage("travel.empname") };

		String[] headerwidth = { "20", "40", "40" };

		String[] fieldNames = { "travelmastercode", "Branchname",
				"Employeename", "Branchcode", "empid" };

		int[] columnIndex = { 0, 1, 2, 3, 4 };

		String submitFlage = "false";

		String submitToMethod = " ";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		//System.out.println("eeeeeeeeeeeeee...!"+Travelbean.getEmployeename());
		return "f9page";

	}

	public String save() {
		TravelAdminMasterModel model = new TravelAdminMasterModel();
		model.initiate(context, session);
		if (Travelbean.getTravelmastercode().equals("")) {
			boolean result = model.adding(Travelbean);
			if (result)
				addActionMessage("Record saved successfully.");
			else
				addActionMessage("Record can't be saved!");

		} else {
			boolean result = model.modify(Travelbean);
			if (result)
				addActionMessage("Record updated successfully!");
			else
				addActionMessage("Record can't be updated!");

		}
		model.Data(Travelbean, request);
		model.terminate();
		return reset();

	}

	public String delete() {
		TravelAdminMasterModel model = new TravelAdminMasterModel();
		model.initiate(context, session);
		boolean result = model.delete(Travelbean);
		if (result)
			addActionMessage("Record deleted successfully!");
		else
			addActionMessage("Record can't be deleted!");
		model.Data(Travelbean, request);
		model.terminate();
		return reset();

	}

	public String reset() {
		Travelbean.setTravelmastercode("");
		Travelbean.setEmpid("");
		Travelbean.setEmployeename("");
		Travelbean.setBranchcode("");
		Travelbean.setBranchname("");
		return "success";

	}

	public String reset1() {

		Travelbean.setEmpid("");
		Travelbean.setEmployeename("");
		return "success";

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TravelAdminMasterModel model = new TravelAdminMasterModel();
		model.initiate(context, session);

		model.Data(Travelbean, request);
		model.terminate();
	}

	public String callPage() throws Exception {

		TravelAdminMasterModel model = new TravelAdminMasterModel();
		model.initiate(context, session);
		model.Data(Travelbean, request);
		model.terminate();
		return SUCCESS;

	}

	public String calforedit() throws Exception {
		TravelAdminMasterModel model = new TravelAdminMasterModel();
		model.initiate(context, session);
		model.calforedit(Travelbean);
		//getRecord();

		model.Data(Travelbean, request);
		model.terminate();
		return "success";
	}

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		TravelAdminMasterModel model = new TravelAdminMasterModel();

		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(Travelbean, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.Data(Travelbean, request);
		model.terminate();

		return reset();

	}

	public String report() {
		TravelAdminMasterModel model = new TravelAdminMasterModel();
		model.initiate(context, session);
		model.getReport(request, response, Travelbean);
		model.terminate();
		return null;
	}

}
