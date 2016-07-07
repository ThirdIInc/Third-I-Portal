package org.struts.action.payroll;

import org.paradyne.bean.payroll.Tdsperquisites;
import org.paradyne.model.payroll.TdsperquisitesModel;
import org.struts.lib.ParaActionSupport;

public class TdsperquisitesAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TdsperquisitesAction.class);
	Tdsperquisites bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean=new Tdsperquisites();
		bean.setMenuCode(729);

	}
	public void prepare_withLoginProfileDetails() throws Exception {
		TdsperquisitesModel model = new TdsperquisitesModel();	
				model.initiate(context,session);
			    model.Data(bean,request);
				model.terminate();
		}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public Tdsperquisites getBean() {
		return bean;
	}

	public void setBean(Tdsperquisites bean) {
		this.bean = bean;
	}

	public String addNew() throws Exception {
		getNavigationPanel(2);
		return "showData";
	}
	
	public String input() throws Exception {
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String reset()throws Exception {
		
		bean.setTdsperquisitescode("");
		bean.setTdsperquisitesname("");
		bean.setPerquisitesAbbr("");
		bean.setVariance("");
		bean.setTaxable("");
		bean.setHiddencode("");
		bean.setCreditexempt("");
		bean.setTaxCode("");
		bean.setExemptSectionNo("");
		getNavigationPanel(2);
		return "showData";
	}

	public String cancel() throws Exception {
		reset();
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		return SUCCESS;
	}
	
public String save()throws Exception {
	TdsperquisitesModel model = new TdsperquisitesModel();		
		model.initiate(context,session);
		boolean str;
		if(bean.getTdsperquisitescode().equals("")){
			str = model.addPerquisite(bean);
			if(str)
			 {
				 addActionMessage("Record saved successfully");
			 }
			 else{
				 addActionMessage("Tds perquisites can not be added.");
			 }
		}else{
			str = model.modPerquisite(bean);
			if(str)
			 {
				 addActionMessage("Record updated successfully");
			 }
			 else{
				 addActionMessage("Tds perquisites can not be updated.");
			 }
		}
		model.Data(bean,request);
		model.terminate();
		getNavigationPanel(3);
		return "showData";
	}

public String delete() throws Exception
{
	
	TdsperquisitesModel model = new TdsperquisitesModel();	
	model.initiate(context,session);
	boolean result = model.deletePerquisite(bean);
	
	if(result)
	{addActionMessage("Record deleted successfully.");
		}else {
		addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
	}
	reset();
	getNavigationPanel(1);
	model.Data(bean,request);
	model.terminate();
	return SUCCESS;
}

public String f9action() throws Exception 
{	
	String query = "SELECT PERQ_CODE,NVL(PERQ_NAME,' '),NVL(PERQ_ABBR,' '),NVL(PERQ_PERIOD,'M'),PERQ_TAXABLE_FLAG FROM HRMS_PERQUISITE_HEAD ORDER BY PERQ_CODE";		
	String[] headers={"Perquisite Code", getMessage("perquisites.name"),getMessage("perquisites.abbr")};
	String[] headerWidth={"20","60","20"};
	String[] fieldNames={"tdsperquisitescode","tdsperquisitesname","perquisitesAbbr","variance","taxable"}; 
	int[] columnIndex={0,1,2,3,4};
	String submitFlag = "true";
	String submitToMethod="Tdsperquisites_TdsperRecord.action";
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	return "f9page";
}

public String TdsperRecord() throws Exception {
	TdsperquisitesModel model = new TdsperquisitesModel();
	model.initiate(context, session);
	model.getRecord(bean);
	bean.setEnableAll("N");
	getNavigationPanel(3);
	model.terminate();
	return "showData";
}

public String report() throws Exception 
{
	TdsperquisitesModel model = new TdsperquisitesModel();
	model.initiate(context,session);
	model.getReport(bean,request,response);
	model.terminate();
	return null;
	

}

	public String callPage() throws Exception {
		
		TdsperquisitesModel model = new TdsperquisitesModel();
			model.initiate(context,session);
			model.Data(bean,request);
			getNavigationPanel(1);
			model.terminate();
		 return SUCCESS;
		
		}
	
	public String calforedit() throws Exception{
		TdsperquisitesModel model = new TdsperquisitesModel();
		model.initiate(context,session);
		model.calforedit(bean);
		model.Data(bean,request);
		getNavigationPanel(3);
		bean.setEnableAll("N");
		model.terminate();
		return "showData";
	}
	



public String delete1()throws Exception {
		String code[]=request.getParameterValues("hdeleteCode");
		TdsperquisitesModel model = new TdsperquisitesModel();
		model.initiate(context,session);
		boolean result =model.deletecheckedRecords(bean,code);
		if(result) {
				addActionMessage("Records deleted successfully.");
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
		reset();
		getNavigationPanel(1);
		model.Data(bean,request);
		model.terminate();
		return SUCCESS;

	}
	

public String f9taxaction() throws Exception {

	logger.info("in f9 action");

	String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

	String[] headers = { "Investment Code", "Investment Section" };

	String[] headerWidth = { "20", "60" };

	String[] fieldNames = { "taxCode", "exemptSectionNo" };

	int[] columnIndex = { 0, 1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}


	
	
	
	
}
