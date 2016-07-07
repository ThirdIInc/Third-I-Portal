package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.MailEventsMaster;
import org.paradyne.model.admin.master.MailEventsMasterModel;
import org.struts.lib.ParaActionSupport;

/*
 * @author:saipavan voleti.
 * Dt:13/8/2009
 */
public class MailEventsMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MailEventsMasterAction.class);
	MailEventsMaster mebean;

	public MailEventsMaster getMebean() {
		return mebean;
	}

	public void setMebean(MailEventsMaster mebean) {
		this.mebean = mebean;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		mebean = new MailEventsMaster();
		mebean.setMenuCode(926);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return mebean;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 * setting the all the records in a list at the time of loading the form.
	 */
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("-->prepare_withLoginProfileDetails");
		MailEventsMasterModel model = new MailEventsMasterModel();
		model.initiate(context, session);
		model.Data(mebean, request);
		logger.info("after complete data method...!");
		//model.setModulenames(mebean);
		logger.info("after complet setting map...!");
		model.terminate();

	}

	/**
	 * @return String after adding/modifying the record.
	 */
	public String save() {
		MailEventsMasterModel model = new MailEventsMasterModel();
		model.initiate(context, session);
		boolean result = false;
		if (!mebean.getMailEventcode().equals("")) {
		
			result = model.modrecord(mebean);
			if (result) {
				
				addActionMessage("Record modified Sucessfully.");
			} else
				addActionMessage("Record cannot be modified.\n EventName should be unique in this Module.");
			
		} else {
			result = model.addrecord(mebean);
			if (result)
				addActionMessage("Record saved successfully.");
			else
				addActionMessage("Record cann't be added.\n EventName should be unique in this Module.");

		}

		model.Data(mebean, request);
		model.terminate();
		return reset();
	}

	/**
	 * @return String after clearing the all the fields.
	 * @throws Exception
	 */
	public String reset() {
		mebean.setMailEventcode("");
		mebean.setMailEventName("");
		mebean.setModuleName("");
		mebean.setDupModulecode("");		
		mebean.setMailEventdesc("");
		mebean.setHiddencode("");

		return "success";
	}

	/**
	 * @return String after deleting the selected record.
	 * @throws Exception
	 */
	public String delete() {
		MailEventsMasterModel model = new MailEventsMasterModel();
		model.initiate(context, session);
		boolean result = model.deleterecord(mebean);
		if (result)
			addActionMessage("Record deleted successfully.");
		else
			addActionMessage("This record is referenced in other resources.So cannot delete.!");
		model.Data(mebean, request);
		reset();
		model.terminate();
		return "success";
	}

	/**
	 * @return String after setting the record details.
	 * @throws Exception
	 */
	public String calforedit() throws Exception {

		MailEventsMasterModel model = new MailEventsMasterModel();
		model.initiate(context, session);
		logger.info("call for edit...!");
		model.callForEdit(mebean);
		logger.info("call for edit..callForEdit.!");
		model.Data(mebean, request);
		logger.info("Data for Data..callForEdit.!");
		model.terminate();
		return "success";
	}

	/**
	 * @return String after setting the records for appropriate page no.
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		MailEventsMasterModel model = new MailEventsMasterModel();
		model.initiate(context, session);
		logger.info("callPage----->"+mebean.getMyPage());
		model.Data(mebean, request);
		logger.info("callPage end----->"+mebean.getMyPage());
		model.terminate();

		return "success";

	}

	/**
	 * @return String after deleting the checked records.
	 * @throws Exception
	 */
	public String deleteCheckedRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		MailEventsMasterModel model = new MailEventsMasterModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(mebean, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		mebean.setMyPage("1");
		mebean.setShow("1");

		model.Data(mebean, request);
		model.terminate();

		return reset();

	}

	/**
	 * @return f9page after setting f9 action fields.
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		String query = " select EVENT_CODE,EVENT_NAME,HRMS_MODULE.MODULE_NAME,EVENT_MODULE_CODE from HRMS_MAIL_EVENTS "
					 +" inner join HRMS_MODULE on (HRMS_MODULE.MODULE_CODE=HRMS_MAIL_EVENTS.EVENT_MODULE_CODE)"
					 +" order by EVENT_MODULE_CODE,EVENT_NAME ";

		String[] headers = { "Event Code",getMessage("conf1"),getMessage("conf3")};            
		  //"Event Name","Event Module Name" };
		String[] headerWidth = { "25", "40","30" };
		String[] fieldNames = { "mailEventcode","mailEventName","moduleName", "dupModulecode" };
		int[] columnIndex = { 0,1,2,3 };
		String submitFlag = "true";

		String submitToMethod = "MailEventsMaster_details.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
	public String f9ModuleName() throws Exception {
		String query = " SELECT MODULE_CODE, MODULE_NAME FROM HRMS_MODULE order by MODULE_CODE ";

		String[] headers = { "Module Code",getMessage("conf3")};            
		  //"Event Name","Event Module Name" };
		String[] headerWidth = { "25", "50" };
		//String[] fieldNames = { "mailEventcode","dupModulecode" };
		String[] fieldNames = { "dupModulecode","moduleName" };
		int[] columnIndex = { 0,1};
		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String details() {
		MailEventsMasterModel model = new MailEventsMasterModel();
		model.initiate(context, session);
		model.details(mebean);
		model.terminate();
		return "success";

	}

}
