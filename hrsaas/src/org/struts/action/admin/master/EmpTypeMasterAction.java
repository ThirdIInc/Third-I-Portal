/**
 * @author lakkichand
 * @date 24 Apr 2007
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.EmpTypeMaster;
import org.paradyne.model.admin.master.EmpTypeModel;
import org.paradyne.model.admin.master.RankModel;
import org.paradyne.model.payroll.MonthlyEDSummaryReportModel;
import org.paradyne.model.payroll.PayrollZoneMasterModel;
import org.struts.lib.ParaActionSupport;

public class EmpTypeMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmpTypeMasterAction.class);
	EmpTypeMaster typMaster;
	String poolDir = "";
	String fileName = "";
	
	
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "showData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);
		
		model.calforedit(typMaster);
		typeRecord();
		getNavigationPanel(3);
		typMaster.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);
		
		model.employeeSearch(typMaster, request);
		getNavigationPanel(1);
		
		model.terminate();
		return SUCCESS;
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

	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);
		
		fileName = getText("data_path") + "/datafiles/" + poolDir + "/xml/Payroll/salaryZone_emptype.xml";

		boolean result = model.deleteType(typMaster);
		model.employeeSearch(typMaster, request);
		
		model.terminate();
		
		if(result) {
			PayrollZoneMasterModel payrollZoneMasterModel = new PayrollZoneMasterModel();
			payrollZoneMasterModel.initiate(context, session);
			
			payrollZoneMasterModel.xml_salaryZoneEmpType(fileName);
			
			payrollZoneMasterModel.terminate();
			
			addActionMessage(getMessage("delete"));
			
			
		} else {
			addActionMessage(getMessage("del.error"));
		}
		getNavigationPanel(1);
		typMaster.setTypeID("");
		typMaster.setTypeName("");
		typMaster.setTypeAbbr("");
		typMaster.setEsiZone("");
		typMaster.setPtZone("");
		typMaster.setPfZone("");
		return "success";
	}

	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);
		
		fileName = getText("data_path") + "/datafiles/" + poolDir + "/xml/Payroll/salaryZone_emptype.xml";
		boolean result = model.deleteEmptype(typMaster, code);
		
		if(result) {
			typMaster.setTypeID("");
			typMaster.setTypeName("");
			typMaster.setTypeAbbr("");
		}
		
		if(result) {
			PayrollZoneMasterModel model1 = new PayrollZoneMasterModel();
			model1.initiate(context, session);
			model1.xml_salaryZoneEmpType(fileName);
			addActionMessage(getMessage("delete"));
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}

		model.employeeSearch(typMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ORDER BY UPPER(TYPE_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("employee.type")};

		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"typMaster.typeName", "typMaster.typeID"};

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
		String submitToMethod = "EmpTypeMaster_typeRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @return the typMaster
	 */
	public Object getModel() {
		return typMaster;
	}

	/**
	 * @return the typMaster
	 */
	public EmpTypeMaster getTypMaster() {
		return typMaster;
	}

	@Override
	public String input() throws Exception {
		typMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}

	public void prepare_local() throws Exception {
		typMaster = new EmpTypeMaster();
		typMaster.setMenuCode(41);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);

		model.employeeSearch(typMaster, request);
		
		model.terminate();
	}

	/**
	 * To generate report
	 * @return null
	 * @throws Exception
	 */
	public void report() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("employee.type"),getMessage("typeabbr"),getMessage("is.active")};
		model.getReport(typMaster, request, response, context, session,label);
		
		model.terminate();
	}

	/**
	 * To reset the fields
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			typMaster.setTypeID("");
			typMaster.setTypeName("");
			typMaster.setTypeAbbr("");
			typMaster.setEsiZone("");
			typMaster.setPtZone("");
			typMaster.setPfZone("");
			typMaster.setIsActive("");
			getNavigationPanel(2);
		} catch(Exception e) {
			logger.error("Error in reset" + e);
		}
		return "showData";
	}

	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);

		fileName = getText("data_path") + "/datafiles/" + poolDir + "/xml/Payroll/salaryZone_emptype.xml";
		boolean result;

		if(typMaster.getTypeID().equals("")) {
			result = model.addType(typMaster);
			if(result) {
				PayrollZoneMasterModel model1 = new PayrollZoneMasterModel();
				model1.initiate(context, session);
				
				model1.xml_salaryZoneEmpType(fileName);
				
				model.terminate();
				
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "showData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return SUCCESS;
			}
		} else {
			result = model.modType(typMaster);
			model.terminate();
			
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "showData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return SUCCESS;
			}
		}
	}
	
	/**
	 * @param typMaster the typMaster to set
	 */
	public void setTypMaster(EmpTypeMaster typMaster) {
		this.typMaster = typMaster;
		typMaster.setMenuCode(41);
	}
	
	/**
	 * To set the fields after search
	 * @return String
	 * @throws Exception
	 */
	public String typeRecord() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);
		
		model.getTypeRecord(typMaster);
		getNavigationPanel(3);
		
		model.terminate();
		return "showData";
	}
	
	//Added by Tinshuk S.B.
	public String getReport() throws Exception {
		EmpTypeModel model = new EmpTypeModel();
		model.initiate(context, session);
		String reportPath="";		
		model.getEmpTypeReport(typMaster, request, response, reportPath);
		model.terminate();
		return null;
	}

	public final String mailReport(){
		try {
			final EmpTypeModel model = new EmpTypeModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Master" + poolName + "/";
			model.getEmpTypeReport(typMaster, request, response, reportPath);
			model.terminate();
		    } 
		   catch (Exception e) {
			e.printStackTrace();
		   }
		return "mailReport";
	}

}