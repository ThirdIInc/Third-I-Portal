/**
 * 
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.ESIMaster;
import org.paradyne.model.admin.master.ESIMasterModel;
import org.paradyne.model.payroll.DebitModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author ritac
 *
 */
public class ESIMasterAction extends ParaActionSupport {

	ESIMaster esiMaster;
	String poolDir = "";
	String fileName = "";

	public ESIMaster getEsiMaster() {
		return esiMaster;
	}

	public void setEsiMaster(ESIMaster esiMaster) {
		this.esiMaster = esiMaster;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ESIMasterAction.class);

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		esiMaster = new ESIMaster();
		esiMaster.setMenuCode(464);

		poolDir = String.valueOf(session.getAttribute("session_pool"));
		logger.info("Inside prepare " + poolDir);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return esiMaster;
	}

	public String input() throws Exception {
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String addNew() throws Exception {
		getNavigationPanel(2);
		return "showData";
	}

	public String save() {

		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);

		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/esiParameter.xml";
		/*if(esiMaster.getEsiCode()==null || esiMaster.getEsiCode().equals(""))
		{
		model.saveESI(esiMaster);
		//	addActionMessage("Record Saved Successfully");
		}
		else
		{
			model.updateESI(esiMaster);
			//addActionMessage("Record Modified Successfully");				
		}
		model.terminate();*/
		String str = "";
		if (esiMaster.getEsiCode() == null || esiMaster.getEsiCode().equals("")) {
			//  write the action function  like this ....!  model.function(passbean as argument);

			str = model.saveESI(esiMaster, fileName);

		} else {

			str = model.updateESI(esiMaster, fileName);

		}
		getNavigationPanel(3);
		model.Data(esiMaster, request);
		model.terminate();
		addActionMessage(getText(str));

		return "showData";

	}

	public String reset() {

		esiMaster.setEsiDate("");
		esiMaster.setEsiGross("");
		esiMaster.setEsiEmp("");
		esiMaster.setEsiComp("");
		esiMaster.setEsiDebitName("");
		esiMaster.setEsiFormula("");
		esiMaster.setEsiCode("");
		esiMaster.setStartmonth("");
		esiMaster.setEndmonth("");
		getNavigationPanel(2);
		return "showData";

	}

	public String cancel() throws Exception {

		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		model.Data(esiMaster, request);
		reset();
		getNavigationPanel(1);
		esiMaster.setEnableAll("Y");
		model.terminate();
		return SUCCESS;
	}

	public String delete() {
		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/esiParameter.xml";
		String result = "";
		result = model.deleteESI(esiMaster, fileName);
		if (result == "deleted") {
			addActionMessage("Record Deleted Successfully");

		} else {
			addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
		}
		reset();
		getNavigationPanel(1);
		model.Data(esiMaster, request);
		model.terminate();
		return SUCCESS;

	}

	public String f9action() {
		String query = "SELECT ESI_CODE,TO_CHAR(ESI_DATE,'DD-MM-YYYY'),NVL(ESI_GROSS_UPTO,''),NVL(ESI_EMP_PERCENTAGE,''),NVL(ESI_COMP_PERCENTAGE,''),NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,''),NVL(ESI_DEBIT_CODE,''),ESI_MONTH_START,ESI_MONTH_END"
				+ " FROM HRMS_ESI "
				+ " LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ESI.ESI_DEBIT_CODE)"
				+ " ORDER BY ESI_CODE";

		String[] headers = { "ESI Code", getMessage("esi.date"),
				getMessage("esi.grossupto") };
		String[] headerwidth = { "10", "40", "50" };
		String[] fieldNames = { "esiMaster.esiCode", "esiMaster.esiDate",
				"esiMaster.esiGross", "esiMaster.esiEmp", "esiMaster.esiComp",
				"esiMaster.esiDebitName", "esiMaster.esiDebitCode",
				"startmonth", "endmonth" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		String submitFlage = "true";
		String submitToMethod = "ESIMaster_EsiRecord.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String EsiRecord() {
		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		model.getEsiRecord(esiMaster);
		getNavigationPanel(3);
		esiMaster.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	public String f9debitaction() {
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		String[] headers = { "Debit Code", "Debit Name" };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "esiMaster.esiDebitCode",
				"esiMaster.esiDebitName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String report() throws Exception {
		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		model.getReport(esiMaster, request, response, context, session);
		model.terminate();
		return null;
	}
	//Added by Nilesh D on 6th Feb 2012.
	public String generateReport() throws Exception {
		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		 String reportPath="";			
		model.generateReport(esiMaster, request, response,reportPath);
		model.terminate();
		return null;
	}
	
    public final String mailReport(){
		try {
			final ESIMasterModel model = new ESIMasterModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Master" + poolName + "/";
			model.generateReport(esiMaster, request,response,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);

		model.Data(esiMaster, request);
		model.terminate();
	}

	public String callPage() throws Exception {

		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		model.Data(esiMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	public String calforedit() throws Exception {
		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		model.calforedit(esiMaster);
		//getRecord();

		model.Data(esiMaster, request);
		getNavigationPanel(3);
		esiMaster.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	public String calfordelete() {
		ESIMasterModel model = new ESIMasterModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(esiMaster);
		if (result) {
			addActionMessage(getText("Record  Deleted Successfully"));
			//reseting all bean varibles	
			esiMaster.setEsiDate("");
			esiMaster.setEsiGross("");
			esiMaster.setEsiEmp("");
			esiMaster.setEsiComp("");
			esiMaster.setEsiDebitName("");
			esiMaster.setEsiFormula("");
			esiMaster.setEsiCode("");
		} else {
			addActionMessage("This record is referenced in other resources.So cannot delete.");
		}

		model.Data(esiMaster, request);
		model.terminate();

		return "success";
	}

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		ESIMasterModel model = new ESIMasterModel();

		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/esiParameter.xml";
		String result = "";
		result = model.deletecheckedRecords(esiMaster, code, fileName);

		if (result == "deleted") {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		reset();
		getNavigationPanel(1);
		model.Data(esiMaster, request);
		model.terminate();

		return SUCCESS;

	}

}
