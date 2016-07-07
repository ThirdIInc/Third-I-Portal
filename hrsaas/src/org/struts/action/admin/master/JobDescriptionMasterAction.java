/**
 * 
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.JobDescriptionMaster;
import org.paradyne.model.admin.master.CheckListModel;
import org.paradyne.model.admin.master.JobDescMasterModel;
import org.paradyne.model.recruitment.DomainMasterModel;
import org.struts.lib.ParaActionSupport;


/**
 * @author shashikant
 * modified by Prasad
 *
 */
public class JobDescriptionMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.admin.master.JobDescriptionMasterAction.class); 

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	JobDescriptionMaster jdMaster;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		jdMaster=new JobDescriptionMaster();
		jdMaster.setFlagRole("false");
		jdMaster.setMenuCode(458);
		prepare_withLoginProfileLoginDetails();

	}
public void prepare_withLoginProfileLoginDetails() throws Exception{
		
	JobDescMasterModel model=new JobDescMasterModel();
	model.initiate(context,session);
	
	model.terminate();
	
	
	}

public String input() throws Exception {
	getNavigationPanel(1);
	JobDescMasterModel model=new JobDescMasterModel();
	model.initiate(context,session);
	model.Data(jdMaster,request);
	jdMaster.setFlagView("true");
	//jdMaster.setOnLoadFlag("true");
	model.terminate();
	
	return "view";
	
}

/**
 * following function is called when Add New Button is clicked.
 * @return
 * @throws Exception
 */
public String addNew() throws Exception{
	getNavigationPanel(2);
	JobDescMasterModel model=new JobDescMasterModel();
	model.initiate(context,session);
	model.Data(jdMaster,request);
	jdMaster.setEditFlag("false");
	model.terminate();
	return "success";
	
}

/**
 * following function is called when cancel button is clicked
 * @return
 * @throws Exception
 */
public String cancelSecond() throws Exception{
	logger.info("Cancel Second---->");
	getNavigationPanel(1);
	JobDescMasterModel model=new JobDescMasterModel();
	model.initiate(context, session);
	model.Data(jdMaster, request);
	jdMaster.setFlagView("true");
	reset();
	model.terminate();
	return "view";
}

public String cancelFirst() throws Exception{
	logger.info("Cancel First---->");
	String str;
	JobDescMasterModel model=new JobDescMasterModel();
	model.initiate(context, session);
	logger.info("bean.getJdCode---->"+jdMaster.getJdCode());
	if(jdMaster.getEditFlag().equals("true")){
		logger.info("If-------->");
		model.getRecord(jdMaster);
		model.Data(jdMaster,request);
		jdMaster.setJobView(true);
		getNavigationPanel(3);
		str="view";
	}else{
		logger.info("Else-------->");
		getNavigationPanel(1);
		model.Data(jdMaster, request);
		reset();
		jdMaster.setFlagView("true");
		str="view";
	}
	return str;
}
/**
 * following function is called when Edit button is clicked.
 * @return
 * @throws Exception
 */
public String edit() throws Exception{
	getNavigationPanel(2);
	JobDescMasterModel model=new JobDescMasterModel();
	model.initiate(context, session);
	model.getRecord(jdMaster);
	model.Data(jdMaster,request);
	jdMaster.setEditFlag("true");
	model.terminate();
	return "success";
	
	
}
/**
 * following function is called to set the values in their respective fields when a record is selected from search window.
 * @return
 * @throws Exception
 */
public String getRec() throws Exception{
	getNavigationPanel(3);
	JobDescMasterModel model=new JobDescMasterModel();
	model.initiate(context, session);
	model.getRecord(jdMaster);
	jdMaster.setJobView(true);
	jdMaster.setFlagView("false");
	model.Data(jdMaster,request);
	//jdMaster.setFlag("true");
	model.terminate();
	return "view";
	
	
}


	public Object getModel() {
		// TODO Auto-generated method stub
		return jdMaster;
	}

	public JobDescriptionMaster getJdMaster() {
		return jdMaster;
	}

	public void setJdMaster(JobDescriptionMaster jdMaster) {
		this.jdMaster = jdMaster;
	}
/**
 * following function is called by default to display the list values.
 * @return
 * @throws Exception
 */
public String callPage() throws Exception {
			getNavigationPanel(1);
			JobDescMasterModel model = new JobDescMasterModel();
			model.initiate(context,session);
			//jdMaster.setOnLoadFlag("true");
			model.Data(jdMaster, request);
			jdMaster.setFlagView("true");
			reset();
			model.terminate();
			return "view";
		
		}
	/**
	 * following function is called to set the values when a record is double clicked from the list.
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception{
			getNavigationPanel(3);
			JobDescMasterModel model = new JobDescMasterModel();
			model.initiate(context,session);
			jdMaster.setEditFlag("false");
			jdMaster.setJobView(true);
			jdMaster.setJdCode(jdMaster.getHiddenCode());
			model.calOnEdit(jdMaster);
			model.Data(jdMaster,request);
			model.terminate();
			return "view";
	}

/**
 * following function is called when delete button is clicked from the table list
 * @return
 * @throws Exception
 */
	
public String deleteRow()throws Exception {
		
		getNavigationPanel(1);
		String code[]=request.getParameterValues("hdeleteCode");
		
		JobDescMasterModel model = new JobDescMasterModel();
		
		model.initiate(context,session);
		jdMaster.setFlagView("true");
		boolean result =model.delChkdRec(jdMaster,code);
		logger.info("result in delete method of JD--->"+result);
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records.");
			}
		
				
			//jdMaster.setOnLoadFlag("true");
		model.Data(jdMaster, request);
		reset();
		model.terminate();
		
		return "view";

	}




/**
 * following function is called when the search window is clicked in the jsp page.
 * @return
 * @throws Exception
 */

public String f9action() throws Exception {
	
	String query = " SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(JOB_DESC_NAME,' ')," +
			"CASE WHEN JOB_DESC_STATUS='A' THEN 'Active' ELSE 'Deactive' END, JOB_DESC_CODE FROM HRMS_JOB_DESCRIPTION "
					+" ORDER BY JOB_DESC_CODE"; 
	logger.info("Query--->"+query);
	String[] headers = {getMessage("jdesc.edate"), getMessage("jdesc.jname"),getMessage("jdesc.statu")};
    String[] headerWidth = { "20", "40","20"};

    String[] fieldNames = {"jdMaster.jdEffDate","jdMaster.jdDescName","jdMaster.status","jdMaster.jdCode"};
    int[] columnIndex = {0,1,2,3};
    logger.info("After Column Index----");
	  String submitFlag = "true";
    String submitToMethod = "JobDescriptionMaster_getRec.action";
    logger.info("After submit to Method---");
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
    logger.info("After setF9Window---");
   return "f9page";
}
public String f9gradeName(){
	
	/**
	 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 * OUTPUT
	 */
	String query = " SELECT CADRE_NAME , CADRE_ID  FROM HRMS_CADRE  ORDER BY CADRE_NAME  ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = { getMessage("grade") };

	String[] headerWidth = { "100" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */
	String[] fieldNames = { "gradeName", "gradeId" };

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
	 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
	 * COLUMN NUMBERS STARTS WITH 0
	 */
	int[] columnIndex = { 0, 1 };

	/**
	 * WHEN SET TO 'true' WILL SUBMIT THE FORM
	 */
	String submitFlag = "false";

	/**
	 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
	 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
	 * ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod = "";

	/**
	 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 */
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	
	return "f9page";
}

/**
 * following function is called to add a new record.
 * @return
 * @throws Exception
 */
	public String save()throws Exception{
		String result = "";
		String page="";
		JobDescMasterModel model =new JobDescMasterModel();
		model.initiate(context,session);
		
		if(jdMaster.getJdCode().equals("")){
			
			 result=model.saveData(jdMaster);
			 
			 if(result.equals("saved")){
				getNavigationPanel(3);
				jdMaster.setJobView(true);
				addActionMessage("Record saved successfully!");
				page="view";
				
			 }
			else if(result.equals("duplicate")){
				getNavigationPanel(2);
				jdMaster.setEditFlag("false");
				addActionMessage("Duplicate entry found!");
				page="success";
			}
			else{
				getNavigationPanel(2);
				jdMaster.setEditFlag("false");
				addActionMessage("Record can not be saved!");
				page="success";
			}
		}
		else{
			result=model.upDataData(jdMaster);
			
			if(result.equals("modified")){
				getNavigationPanel(3);
				jdMaster.setJobView(true);
				addActionMessage("Record updated successfully!");
				page="view";
			}
			else if(result.equals("duplicate")){
				getNavigationPanel(2);
				jdMaster.setEditFlag("false");
				addActionMessage("Duplicate entry found!");
				page="success";
			}
			else{
				getNavigationPanel(2);
				jdMaster.setEditFlag("false");
				addActionMessage("Record can not be updated!");
				page="success";
			}
		}
		model.Data(jdMaster, request);
		model.terminate();
	return page;
	}
	/**
	 * following function is called to update a record 
	 * @return
	 * @throws Exception
	 */
	public String update()throws Exception{
		String result="";
		
		JobDescMasterModel model =new JobDescMasterModel();
		model.initiate(context,session);
		result=model.upDataData(jdMaster);
		if(result.equals("modified")){
			addActionMessage("Record updated successfully!");
			jdMaster.setJobView(true);
			jdMaster.setFlag("true");
		}
		else if(result.equals("duplicate")){
			addActionMessage("Duplicate entry found!");
			jdMaster.setSaveFlag("true");
		}
		else{
			addActionMessage("Record can not be update!");
		}
		model.Data(jdMaster, request);
		
		model.terminate();
		
		return SUCCESS;
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 * @throws Exception
	 */
	public String reset()throws Exception{
	jdMaster.setJdDesc("");
	jdMaster.setJdDescName("");
	jdMaster.setJdEffDate("");
	jdMaster.setJdRoleDesc("");
	jdMaster.setJdCode("");
	jdMaster.setStatus("");
	jdMaster.setRecruitmentDays("");
	jdMaster.setGradeId("");
	jdMaster.setGradeName("");
	
		return SUCCESS;
		}
	
	
	/**
	 * following function is called when delete button is clicked to delete a selected record.
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		getNavigationPanel(1);
		JobDescMasterModel model =new JobDescMasterModel();
		model.initiate(context,session);
		jdMaster.setFlagView("true");
		boolean flag=	model.deletedata(jdMaster);
		if(flag){
			addActionMessage("Record deleted successfully |");
		}
		else addActionMessage("This record is referenced in some other resources.\nso can not be deleted!");
	
		model.Data(jdMaster,request);
		model.terminate();
		reset();
		return "view";
		}
	/**
	 * following function is called to get the Pdf report for list of Job Description Master records
	 * 
	 */
	public String report(){
		getNavigationPanel(3);
		JobDescMasterModel model = new JobDescMasterModel();	
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("jdesc.edate"),getMessage("jdesc.jname"),getMessage("jdesc.statu")};
		model.generateReport(jdMaster,response,label);
		model.terminate();
		return null;
	}
}
