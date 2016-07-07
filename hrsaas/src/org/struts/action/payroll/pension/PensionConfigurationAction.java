package org.struts.action.payroll.pension;

import org.paradyne.bean.payroll.pension.PensionConfiguration;
import org.paradyne.model.payroll.pension.PensionConfigurationModel;
import org.struts.action.D1.BusinessUnitAction;
import org.struts.lib.ParaActionSupport;

public class PensionConfigurationAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PensionConfigurationAction.class);

	PensionConfiguration bean = null;

	@Override
	public void prepare_local() throws Exception {
		bean = new PensionConfiguration();
		bean.setMenuCode(948);
	}

	public Object getModel() {
		return bean;
	}

	public void setPensionConfiguration(PensionConfiguration bean) {
		this.bean = bean;
	}

	public String input() throws Exception {
		PensionConfigurationModel model = new PensionConfigurationModel();
		model.initiate(context, session);
		try {
			if (bean.getSPensionTypeCode().equals("")) {
				bean.setSPensionTypeCode("1");
			}
		} catch (Exception e) {
			bean.setSPensionTypeCode("1");
		}
		
		bean.setPrePensionTypeCode("");
		model.terminate();
		//bean.setReadFlag("true");
		
		//getNavigationPanel(2);
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			model.showPensionConfigurationList(bean, request);
			getNavigationPanel(2);
		} else {
			model.getPensionSplitAmpunt(bean,request);
			getNavigationPanel(4);
		}
		model.getPensionDetailsList(bean);
		return SUCCESS;
	}
	
	public String addNew() throws Exception {
		bean.setSMode("new");
		//getNavigationPanel(2);
		
		return SUCCESS;
	}
	
	public String save() throws Exception {
		PensionConfigurationModel model = new PensionConfigurationModel();
		Boolean bResult = false;
		model.initiate(context, session);
		String pensionType="";
		if(bean.getPrePensionTypeCode().equals("")){
			pensionType = bean.getSPensionTypeCode();
		}else 
			pensionType = bean.getPrePensionTypeCode();
		
		if (bean.getSPensionCode().equals("")) {
			bResult = model.savePensionConfiguration(bean,pensionType);
			
			if (bResult) {
				addActionMessage(getMessage("save"));
			} else 	{
				addActionMessage(getMessage("save.error"));
			}
		}
		else {
			bResult = model.updatePensionConfiguration(bean,pensionType);
			if (bResult) {
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("update.error"));
			}
		}
		reset();
		model.terminate();
		input();
		//setPensionConfigurationDataAfterSave(bean); 
		
		//bean.setReadFlag("false");
		
		//getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String proceedWithoutSave() throws Exception {
		input();
		reset();
		return SUCCESS;
	}
	
	public String callPage() throws Exception {
		PensionConfigurationModel model = new PensionConfigurationModel();
		model.initiate(context, session);
		model.showPensionConfigurationList(bean, request);
		model.terminate();
		//getNavigationPanel(1);
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			getNavigationPanel(2);
		} else {
			getNavigationPanel(4);
		}
		return SUCCESS;
	}
	
	public String reset() throws Exception{
		bean.setHiddencode("");
		bean.setMyPage("1");
		bean.setPrePensionTypeCode("");
		bean.setReadFlag("");
		bean.setSMode("");
		bean.setSPensionAvgEmol("");
		bean.setSPensionAvgEmol1("");
		bean.setSPensionAvgEmol2("");
		bean.setSPensionAvgEmol3("");
		bean.setSPensionAvgEmol4("");
		bean.setSPensionCode("");
		bean.setSPensionCompFormula("");
		bean.setSPensionCompFormula1("");
		bean.setSPensionCompFormula2("");
		bean.setSPensionCompFormula3("");
		bean.setSPensionCompFormula4("");
		bean.setSPensionEffFrm("");
		bean.setSPensionEffFrm1("");
		bean.setSPensionEffFrm2("");
		bean.setSPensionEffFrm3("");
		bean.setSPensionEffFrm4("");
		bean.setSPensionEmolFormula("");
		bean.setSPensionEmolFormula1("");
		bean.setSPensionEmolFormula2("");
		bean.setSPensionEmolFormula3("");
		bean.setSPensionEmolFormula4("");
		bean.setSPensionEmolMonths("");
		bean.setSPensionEmolMonths1("");
		bean.setSPensionEmolMonths2("");
		bean.setSPensionEmolMonths3("");
		bean.setSPensionEmolMonths4("");
		bean.setSPensionEmpStatus("");
		bean.setSPensionEmpStatus1("");
		bean.setSPensionEmpStatus2("");
		bean.setSPensionEmpStatus3("");
		bean.setSPensionEmpStatus4("");
		bean.setSPensionFormula("");
		bean.setSPensionFormula1("");
		bean.setSPensionFormula2("");
		bean.setSPensionFormula4("");
		bean.setSPensionFormula3("");
		bean.setSPensionMaxService("");
		bean.setSPensionMaxService1("");
		bean.setSPensionMaxService2("");
		bean.setSPensionMaxService3("");
		bean.setSPensionMaxService4("");
		bean.setSPensionMinAmt("");
		bean.setSPensionMinAmt1("");
		bean.setSPensionMinAmt2("");
		bean.setSPensionMinAmt3("");
		bean.setSPensionMinAmt4("");
		bean.setSPensionMinService("");
		bean.setSPensionMinService1("");
		bean.setSPensionMinService2("");
		bean.setSPensionMinService3("");
		bean.setSPensionMinService4("");
		bean.setSPensionVolWeiyears("");
		bean.setSPensionVolWeiyears1("");
		bean.setSPensionVolWeiyears2("");
		bean.setSPensionVolWeiyears3("");
		bean.setSPensionVolWeiyears4("");
		
		
		bean.setSPensionAmtCreditCode1("");
		bean.setSPensionAmtCreditHead1("");
		bean.setSPensionAmtCreditValue1("");
		bean.setSPensionAmtCreditCode2("");
		bean.setSPensionAmtCreditHead2("");
		bean.setSPensionAmtCreditValue2("");
		bean.setSPensionAmtCreditCode3("");
		bean.setSPensionAmtCreditHead3("");
		bean.setSPensionAmtCreditValue3("");
		
		input();
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			getNavigationPanel(2);
		} else {
			getNavigationPanel(4);
		}
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		PensionConfigurationModel model = new PensionConfigurationModel();
		model.initiate(context, session);
		model.calforedit(bean);
		model.showPensionConfigurationList(bean, request);
		bean.setSMode("update");
		
		model.terminate();
		//getNavigationPanel(2);
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			getNavigationPanel(2);
		} else {
			getNavigationPanel(4);
		}
		return SUCCESS;
	}
	
	public String editSearch() throws Exception
	{
		PensionConfigurationModel model = new PensionConfigurationModel();
		model.initiate(context, session);
		bean.setSMode("update");
		setPensionConfigurationData(bean);
		model.terminate();
		//getNavigationPanel(2);
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			getNavigationPanel(2);
		} else {
			getNavigationPanel(4);
		}
		return SUCCESS;
	}
	
	private void setPensionConfigurationData(PensionConfiguration bean) {
		try {
			if (bean.getSPensionTypeCode().equals("1")) {
				
				bean.setSPensionEffFrm1(bean.getSPensionEffFrm());
				bean.setSPensionMinService1(bean.getSPensionMinService());
				bean.setSPensionMaxService1(bean.getSPensionMaxService());
				bean.setSPensionMinAmt1(bean.getSPensionMinAmt());
				bean.setSPensionEmpStatus1(bean.getSPensionEmpStatus());
				bean.setSPensionEmolFormula1(bean.getSPensionEmolFormula());
				bean.setSPensionEmolMonths1(bean.getSPensionEmolMonths());
				bean.setSPensionFormula1(bean.getSPensionFormula());
				bean.setSPensionVolWeiyears1(bean.getSPensionVolWeiyears());
				bean.setSPensionCompFormula1(bean.getSPensionCompFormula());
				bean.setSPensionAvgEmol1(bean.getSPensionAvgEmol());
				
			} else if (bean.getSPensionTypeCode().equals("2")) {
				
				bean.setSPensionEffFrm2(bean.getSPensionEffFrm());
				bean.setSPensionMinService2(bean.getSPensionMinService());
				bean.setSPensionMaxService2(bean.getSPensionMaxService());
				bean.setSPensionMinAmt2(bean.getSPensionMinAmt());
				bean.setSPensionEmpStatus2(bean.getSPensionEmpStatus());
				bean.setSPensionEmolFormula2(bean.getSPensionEmolFormula());
				bean.setSPensionEmolMonths2(bean.getSPensionEmolMonths());
				bean.setSPensionFormula2(bean.getSPensionFormula());
				bean.setSPensionVolWeiyears2(bean.getSPensionVolWeiyears());
				bean.setSPensionCompFormula2(bean.getSPensionCompFormula());
				bean.setSPensionAvgEmol2(bean.getSPensionAvgEmol());
				
			} else if (bean.getSPensionTypeCode().equals("3")) {
				
				bean.setSPensionEffFrm3(bean.getSPensionEffFrm());
				bean.setSPensionMinService3(bean.getSPensionMinService());
				bean.setSPensionMaxService3(bean.getSPensionMaxService());
				bean.setSPensionMinAmt3(bean.getSPensionMinAmt());
				bean.setSPensionEmpStatus3(bean.getSPensionEmpStatus());
				bean.setSPensionEmolFormula3(bean.getSPensionEmolFormula());
				bean.setSPensionEmolMonths3(bean.getSPensionEmolMonths());
				bean.setSPensionFormula3(bean.getSPensionFormula());
				bean.setSPensionVolWeiyears3(bean.getSPensionVolWeiyears());
				bean.setSPensionCompFormula3(bean.getSPensionCompFormula());
				bean.setSPensionAvgEmol3(bean.getSPensionAvgEmol());
				
			} else if (bean.getSPensionTypeCode().equals("4")) {
				
				bean.setSPensionEffFrm4(bean.getSPensionEffFrm());
				bean.setSPensionMinService4(bean.getSPensionMinService());
				bean.setSPensionMaxService4(bean.getSPensionMaxService());
				bean.setSPensionMinAmt4(bean.getSPensionMinAmt());
				bean.setSPensionEmpStatus4(bean.getSPensionEmpStatus());
				bean.setSPensionEmolFormula4(bean.getSPensionEmolFormula());
				bean.setSPensionEmolMonths4(bean.getSPensionEmolMonths());
				bean.setSPensionFormula4(bean.getSPensionFormula());
				bean.setSPensionVolWeiyears4(bean.getSPensionVolWeiyears());
				bean.setSPensionCompFormula4(bean.getSPensionCompFormula());
				bean.setSPensionAvgEmol4(bean.getSPensionAvgEmol());
				
			}

		} catch (Exception e) {

		}
	}
	
	
	private void setPensionConfigurationDataAfterSave(PensionConfiguration bean) {
		try {
			if (bean.getSPensionTypeCode().equals("1")) {
				
				bean.setSPensionEffFrm(bean.getSPensionEffFrm1());
				bean.setSPensionMinService(bean.getSPensionMinService1());
				bean.setSPensionMaxService(bean.getSPensionMaxService1());
				bean.setSPensionMinAmt(bean.getSPensionMinAmt1());
				bean.setSPensionEmpStatus(bean.getSPensionEmpStatus1());
				bean.setSPensionEmolFormula(bean.getSPensionEmolFormula1());
				bean.setSPensionEmolMonths(bean.getSPensionEmolMonths1());
				bean.setSPensionFormula(bean.getSPensionFormula1());
				bean.setSPensionVolWeiyears(bean.getSPensionVolWeiyears1());
				bean.setSPensionCompFormula(bean.getSPensionCompFormula1());
				bean.setSPensionAvgEmol(bean.getSPensionAvgEmol1());
				
			} else if (bean.getSPensionTypeCode().equals("2")) {
				
				bean.setSPensionEffFrm(bean.getSPensionEffFrm2());
				bean.setSPensionMinService(bean.getSPensionMinService2());
				bean.setSPensionMaxService(bean.getSPensionMaxService2());
				bean.setSPensionMinAmt(bean.getSPensionMinAmt2());
				bean.setSPensionEmpStatus(bean.getSPensionEmpStatus2());
				bean.setSPensionEmolFormula(bean.getSPensionEmolFormula2());
				bean.setSPensionEmolMonths(bean.getSPensionEmolMonths2());
				bean.setSPensionFormula(bean.getSPensionFormula2());
				bean.setSPensionVolWeiyears(bean.getSPensionVolWeiyears2());
				bean.setSPensionCompFormula(bean.getSPensionCompFormula2());
				bean.setSPensionAvgEmol(bean.getSPensionAvgEmol2());
				
			} else if (bean.getSPensionTypeCode().equals("3")) {
				
				bean.setSPensionEffFrm(bean.getSPensionEffFrm3());
				bean.setSPensionMinService(bean.getSPensionMinService3());
				bean.setSPensionMaxService(bean.getSPensionMaxService3());
				bean.setSPensionMinAmt(bean.getSPensionMinAmt3());
				bean.setSPensionEmpStatus(bean.getSPensionEmpStatus3());
				bean.setSPensionEmolFormula(bean.getSPensionEmolFormula3());
				bean.setSPensionEmolMonths(bean.getSPensionEmolMonths3());
				bean.setSPensionFormula(bean.getSPensionFormula3());
				bean.setSPensionVolWeiyears(bean.getSPensionVolWeiyears3());
				bean.setSPensionCompFormula(bean.getSPensionCompFormula3());
				bean.setSPensionAvgEmol(bean.getSPensionAvgEmol3());
				
			} else if (bean.getSPensionTypeCode().equals("4")) {
				
				bean.setSPensionEffFrm(bean.getSPensionEffFrm4());
				bean.setSPensionMinService(bean.getSPensionMinService4());
				bean.setSPensionMaxService(bean.getSPensionMaxService4());
				bean.setSPensionMinAmt(bean.getSPensionMinAmt4());
				bean.setSPensionEmpStatus(bean.getSPensionEmpStatus4());
				bean.setSPensionEmolFormula(bean.getSPensionEmolFormula4());
				bean.setSPensionEmolMonths(bean.getSPensionEmolMonths4());
				bean.setSPensionFormula(bean.getSPensionFormula4());
				bean.setSPensionVolWeiyears(bean.getSPensionVolWeiyears4());
				bean.setSPensionCompFormula(bean.getSPensionCompFormula4());
				bean.setSPensionAvgEmol(bean.getSPensionAvgEmol4());
				
			}

		} catch (Exception e) {

		}
	}
	
	
	public String deleteMultipleRecords() throws Exception
	{
		PensionConfigurationModel model = new PensionConfigurationModel();
		Boolean bResult = false;
		
		model.initiate(context, session);
		
		String code[] = request.getParameterValues("hdeleteCode");
		String sPensionCode = "";
		
		for (int i = 0; i < code.length; i++) {
			if(!(String.valueOf(code[i]).equals("")))
			{
				sPensionCode += code[i]+",";
			}
		}
		
		String aPensionCode[] = sPensionCode.split(",");
		bResult = model.deletePensionConfiguration(aPensionCode);
		
		if (bResult) 
		{
			addActionMessage(getMessage("delete"));
		} 
		else 
		{
			addActionMessage(getMessage("multiple.del.error"));
		}
		
		/* Retrieve the updated list from database */
		//
		model.showPensionConfigurationList(bean, request);
		reset();
		model.terminate();
		//getNavigationPanel(1);
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			getNavigationPanel(2);
		} else {
			getNavigationPanel(4);
		}
		//bean.setReadFlag("true");
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{
		PensionConfigurationModel model = new PensionConfigurationModel();
		Boolean bResult = false;
		String sDelCode[] = new String[1];
		
		model.initiate(context, session);
		
		sDelCode[0] = bean.getSPensionCode();
		
		bResult = model.deletePensionConfiguration(sDelCode);
		
		if (bResult) 
		{
			addActionMessage(getMessage("delete"));
		} 
		else 
		{
			addActionMessage(getMessage("del.error"));
		}
		
		/* Retrieve the updated list from database */
		reset();
		model.showPensionConfigurationList(bean, request);
		model.terminate();
		//getNavigationPanel(1);
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			getNavigationPanel(2);
		} else {
			getNavigationPanel(4);
		}
		//bean.setReadFlag("true");
		return SUCCESS;
	}
	
	public String getPensionConfigurationDetails() throws Exception {
		bean.setReadFlag("false");
		bean.setSMode("new");
		PensionConfigurationModel model = new PensionConfigurationModel();
		model.initiate(context, session);
		model.getPensionConfigurationDetails(bean);
		model.showPensionConfigurationList(bean, request);
		model.terminate();
		//getNavigationPanel(3);
		if (!(bean.getSPensionTypeCode().equals("5"))) { 
			getNavigationPanel(2);
		} else {
			getNavigationPanel(4);
		}
		return SUCCESS;
	}
	
	public String f9action() {
		String query = " SELECT " 
					 + "	PENS_CODE,"
					 + "	DECODE(PENS_TYPE_CODE,"  
					 + "	1, 'Superannuation', " 
					 + "	2, 'Voluntary', " 
					 + "	3, 'Compulsory', "
					 + "	4, 'Death', "
					 + "	'-') AS PENS_TYPE_CODE, "
					 + "	PENS_MIN_SERVICE, "
					 + "	PENS_MAX_SERVICE, "
					 + "	CASE WHEN PENS_FORMULA IS NULL THEN PENS_COMP_FORMULA ELSE PENS_FORMULA END AS PENS_FORMULA, "
					 + "	TO_CHAR(PENS_MIN_AMOUNT, '99,999,990.99') AS PENS_MIN_AMOUNT " 
					 + " FROM "
					 + "	HRMS_PENSION_CONF WHERE PENS_TYPE_CODE= "+bean.getSPensionTypeCode() 
					 + " ORDER BY " 
					 + "	PENS_CODE ";
  
		String [] headers = {"Pension Code","Pension Type","Minimum Service","Maximum Service","Pension Formula", "Minimum Amount"};
		String [] headerWidth = {"15","15","15","15","20","20"};
		
		String [] fieldNames = {"sPensionCode", "sPensionTypeCode", "sPensionMinService", "sPensionMaxService", "sPensionFormula", "sPensionMinAmt"};
		
		int [] columnIndex = {0, 1, 2, 3, 4, 5};
		String submitFlag = "true";
		
		String submitToMethod = "PensionConfiguration_getPensionConfigurationDetails.action";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9pensionAmtBasic() {
		String query = " SELECT " 
					 + "	CREDIT_NAME, "
					 + "	CREDIT_CODE "  
					 + " FROM "
					 + "	HRMS_CREDIT_HEAD " 
					 + " ORDER BY " 
					 + "	CREDIT_NAME ";
  
		String [] headers = {"Credit Head"};
		String [] headerWidth = {"100"};
		
		String [] fieldNames = {"sPensionAmtCreditHead1", "sPensionAmtCreditCode1"};
		
		int [] columnIndex = {0, 1};
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9pensionAmtDP() {
		String query = " SELECT " 
					 + "	CREDIT_NAME, "
					 + "	CREDIT_CODE "  
					 + " FROM "
					 + "	HRMS_CREDIT_HEAD " 
					 + " ORDER BY " 
					 + "	CREDIT_NAME ";
  
		String [] headers = {"Credit Head"};
		String [] headerWidth = {"100"};
		
		String [] fieldNames = {"sPensionAmtCreditHead2", "sPensionAmtCreditCode2"};
		
		int [] columnIndex = {0, 1};
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9pensionAmtDA() {
		String query = " SELECT " 
					 + "	CREDIT_NAME, "
					 + "	CREDIT_CODE "  
					 + " FROM "
					 + "	HRMS_CREDIT_HEAD " 
					 + " ORDER BY " 
					 + "	CREDIT_NAME ";
  
		String [] headers = {"Credit Head"};
		String [] headerWidth = {"100"};
		
		String [] fieldNames = {"sPensionAmtCreditHead3", "sPensionAmtCreditCode3"};
		
		int [] columnIndex = {0, 1};
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * Purpose - Used to add record into list.
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String addData() throws Exception {
		PensionConfigurationModel model = new PensionConfigurationModel();
		model.initiate(context, session);
		
		boolean result=false;
		try {
		if (bean.getParaId().equals("")) {
		
			result = model.addPension(bean);//FOR INSERTING DATA
			if (result) {
				addActionMessage("Record Added Successfully");
				
			}
			else {
				addActionMessage("Record cannot be added");
			}

		} else {
			boolean result1 = model.updatePension(bean);//FOR UPDATING DATA
			if (result1) {
				addActionMessage("Record Updated Successfully");
				
			} else {
				addActionMessage("Record cannot be updated");
			}
		}
		resetPension();
		model.getPensionDetailsList(bean);
		model.terminate();

		}
		catch (Exception e) {
		e.printStackTrace();
	}
	
	return "success";
	
}
			
	public String resetPension () {
    bean.setCreditFormula("");
    bean.setCreditHead("");
    bean.setCreditHeadCode("");
    bean.setPensionValue("");
	bean.setParaId("");
	bean.setEditDataId("");
		
	return "success"; 	
	}
		
		
	public String editData() {
		System.out.println("editData call---------------");
		String editCode = request.getParameter("editCode"); 
		PensionConfigurationModel model = new PensionConfigurationModel();
			model.initiate(context,session);
			model.editPensionRecord(bean, editCode);
			model.getPensionDetailsList(bean);
			model.terminate();

			return "success";

		}
	
	/**
	 ** METHOD FOR DELETE SELECTED RECORD
	 * @return SUCCESS
	 */
	public String deleteData() {
		
		String deleteCode = request.getParameter("deleteCode"); 
		PensionConfigurationModel model = new PensionConfigurationModel();
		model.initiate(context,session);
		boolean result1 = false;
		try {
			result1 = model.deletePensionRecord(bean,deleteCode);
			if (result1) {
				addActionMessage("Record Deleted Successfully");
			}

			model.getPensionDetailsList(bean);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
		}
		model.terminate();
		return SUCCESS;
	}

	
	
	/**
	 * purpose - Set Credit Name.
	 * @return f9page.
	 */
	public String f9creditName()  {

		String query = "  SELECT CREDIT_CODE,CREDIT_NAME  FROM HRMS_CREDIT_HEAD "
				           + " where CREDIT_CODE NOT IN (SELECT CREDIT_CODE FROM HRMS_PENSION_CREDIT_CONF) ";
	
		final String[] headers = {"Head Id", getMessage("credit.head")};

		final String[] headerWidth = {"20", "80"};

		final String[] fieldNames = {"creditHeadCode", "creditHead"};

		final int[] columnIndex = {0, 1};

		final String submitFlag = "false";

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}	
	
	
	
}
