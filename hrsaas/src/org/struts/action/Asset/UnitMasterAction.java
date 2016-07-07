package org.struts.action.Asset;

import org.paradyne.bean.Asset.UnitMaster;
import org.paradyne.model.Asset.UnitMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author Krishna
 *
 */
public class UnitMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(UnitMasterAction.class);
	/**
	 * Declare UnitMaster reference variable
	 */
	UnitMaster unitMaster;

	

	/**
	 * Over ridden prepare_local() method
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		unitMaster = new UnitMaster();
		unitMaster.setMenuCode(640);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return unitMaster;
	}

	public String input() throws Exception {
		unitMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	/* method name : f9action 
	 * purpose     : to show all the details for the selected application
	 * return type : String
	 * parameter   : none
	 */

	public String f9action() throws Exception {

		String query = "SELECT UNIT_CODE,UNIT_NAME FROM HRMS_UNIT_MASTER ";

		String[] headers = { "Unit Code", getMessage("unit.name") };

		String[] headerWidth = { "30", "50" };
		String[] fieldNames = { "unitCode", "unitName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "UnitMaster_data.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/* method name : save 
	 * purpose     : to add new record or to modify the existing record
	 * return type : String
	 * parameter   : none
	 */

	public String save() throws Exception{
		UnitMasterModel model = new UnitMasterModel();
		model.initiate(context, session);
		boolean result;
		if (unitMaster.getUnitCode().equals("")) {
			result = model.add(unitMaster);
			if (result) {
				addActionMessage("Record saved successfully.");
				getNavigationPanel(3);
				//reset();
			} else {
				addActionMessage("Duplicate entry found.");
				getNavigationPanel(1);
				return input();
				
			}
		}//end o0f if
		else {
			result = model.mod(unitMaster);
			if (result) {
				addActionMessage("Record Modified Successfully.");
				getNavigationPanel(3);
			}//end of if
			else {
				addActionMessage("Duplicate entry found.");
				getNavigationPanel(1);
				return input();
				
			}//end of else
		}//end of else
		model.Data(unitMaster, request);
		model.terminate();
		return "Data";
	}

	/* method name : delete 
	 * purpose     : to delete the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String delete() {
		UnitMasterModel model = new UnitMasterModel();
		model.initiate(context, session);
		boolean result = model.delete(unitMaster);
		if (result) {
			addActionMessage("Record Deleted Successfully.");
			reset();
		}//end of if 
		else {
			addActionMessage("This record is referenced in other resources.So can't delete.");
		}//end of else
		model.Data(unitMaster, request);
		model.terminate();
		getNavigationPanel(1);
		return "success";
		}

	/* method name : reset 
	 * purpose     : to reset all the form fields and set all values to empty strings
	 * return type : String
	 * parameter   : none
	 */
	public String reset() {
		unitMaster.setUnitCode("");
		unitMaster.setUnitName("");
		unitMaster.setShow("");
		unitMaster.setHdeleteCode("");
		unitMaster.setMyPage("");
		unitMaster.setHiddencode("");
		getNavigationPanel(2);
		return "Data";
	}

	/* method name : prepare_withLoginProfileDetails 
	 * purpose     : to retrieve the  details at the time page loading
	 * return type : void
	 * parameter   : none
	 */

	public void prepare_withLoginProfileDetails() throws Exception {
		UnitMasterModel model = new UnitMasterModel();
		model.initiate(context, session);
		model.Data(unitMaster, request);
		model.terminate();
	}

	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			reset();
			return "Data";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	/* method name : callPage
	 * purpose     : to displays the records in the form
	 * return type : String
	 * parameter   : none
	 */

	public String callPage() throws Exception {

		UnitMasterModel model = new UnitMasterModel();
		model.initiate(context, session);
		model.Data(unitMaster, request);
		model.terminate();
		return SUCCESS;

	}

	/* method name : calforedit
	 * purpose     : to edit the records 
	 * return type : String
	 * parameter   : none
	 * throws      : Exception
	 */

	public String calforedit() throws Exception {
		try {
			UnitMasterModel model = new UnitMasterModel();
			model.initiate(context, session);
			model.calforedit(unitMaster);
			getNavigationPanel(3);
			unitMaster.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Data";
	}

	/* method name : calfordelete
	 * purpose     : to delete the selected Record 
	 * return type : String
	 * parameter   : none
	 */
	public String calfordelete() {
		UnitMasterModel model = new UnitMasterModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(unitMaster);
		if (result) {
			addActionMessage(getText("Record  Deleted Successfully."));
		}//end of if
		else {
			addActionMessage("Record can not be deleted.");
		}//end of else
		model.Data(unitMaster, request);
		model.terminate();
		return "success";
	}

	public String data() throws Exception {
		try {
			UnitMasterModel model = new UnitMasterModel();
			model.initiate(context, session);
			model.setData(unitMaster);
			getNavigationPanel(3);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Data";
	}
	
	/* method name : delete1
	 * purpose     : to delete the record selected by check on the Check Box
	 * return type : String
	 * parameter   : none
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		UnitMasterModel model = new UnitMasterModel();

		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(unitMaster, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.Data(unitMaster, request);
		model.terminate();
		
		getNavigationPanel(1);
		return "success";

	}

	/* method name : report 
	 * purpose     : to generate the report for the selected application
	 * return type : String
	 * parameter   : none
	 */

	public String report() {
		UnitMasterModel model = new UnitMasterModel();
		model.initiate(context, session);
		model.report(unitMaster, request, response);
		model.terminate();
		return null;

	}

	/**
	 * @return the unitMaster
	 */
	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	/**
	 * @param unitMaster the unitMaster to set
	 */
	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}
}
