package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.CompanyMaster;
import org.paradyne.model.admin.master.CompanyMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author balajim
 */
public class CompanyMasterAction extends ParaActionSupport {
	CompanyMaster company;

	public String data() {

		CompanyMasterModel model = new CompanyMasterModel();
		model.initiate(context, session);

		// clear all the text fields using bean object.
		 model.data(company);

		model.terminate();
		return "success";

	}

	public String delete() {
		CompanyMasterModel model = new CompanyMasterModel();
		model.initiate(context, session);
		
		boolean result = model.delete(company);
		if(result) {
			addActionMessage("Record Deleted Successfully !");
			company.setCompCode("");
			company.setCompName("");
			company.setAddress("");
			company.setCityId("");
			company.setCityName("");
			company.setTelephone("");
			company.setWebsite("");
			company.setTanNo("");
			company.setBankName("");
			company.setPincode("");
			company.setUploadFileName("");
		} else {
			addActionMessage("Record can't Deleted");
		}
		
		model.terminate();
		return "success";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT COMPANY_CODE, COMPANY_NAME FROM HRMS_COMPANY ORDER BY COMPANY_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("company.code"), getMessage("company")};

		String[] headerWidth = {"20", "60"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"company.compCode", "company.compName"};
		
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};
		
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "Company_data.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9city() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT LOCATION_CODE,LOCATION_NAME  FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE=2  ORDER BY LOCATION_CODE ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("city.code"), getMessage("city")};

		String[] headerWidth = {"20", "60"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"company.cityId", "company.cityName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		company.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public Object getModel() {
		return company;
	}

	public void prepare_local() throws Exception {
		try {
			company = new CompanyMaster();
			company.setMenuCode(457);
			CompanyMasterModel model = new CompanyMasterModel();
			model.initiate(context, session);
			model.data(company);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String reset() {
		company.setCompCode("");
		company.setCompName("");
		company.setAddress("");
		company.setCityId("");
		company.setCityName("");
		company.setTelephone("");
		company.setWebsite("");
		company.setTanNo("");
		company.setBankName("");
		company.setPincode("");
		company.setUploadFileName("");

		return "success";
	}

	public String save() {
		CompanyMasterModel model = new CompanyMasterModel();
		model.initiate(context, session);
		
		if(company.getCompCode().equals("")) {
			boolean result = model.save(company);
			if(result) {
				addActionMessage("Record Saved Successfully !");
				/*company.setCompCode("");
				company.setCompName("");
				company.setAddress("");
				company.setCityId("");
				company.setCityName("");
				company.setTelephone("");
				company.setWebsite("");
				company.setTanNo("");
				company.setBankName("");
				company.setPincode("");
				company.setUploadFileName("");*/
			} else {
				addActionMessage("Record can not be saved");
			}
		} else {
			boolean result = model.update(company);
			if(result) {
				addActionMessage(getText("modMessage", ""));
			} else {
				addActionMessage("Record can not be modified");
			}
		}
		model.data(company);
		model.terminate();
		return "success";
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
}