package org.struts.action.admin.master;

import org.struts.lib.ParaActionSupport;

import org.paradyne.bean.admin.master.QualificationMaster;
import org.paradyne.model.TravelManagement.Master.ProjectMasterModel;
import org.paradyne.model.admin.master.DivisionModel;
import org.paradyne.model.admin.master.QualificationModel;
import org.paradyne.model.admin.master.RankModel;
import org.paradyne.model.recruitment.DomainMasterModel;

/**
 * 
 * @author Prasad
 *
 */

public class QualificationMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.admin.master.QualificationMasterAction.class); 
	
	QualificationMaster bean;
	public void prepare_local() throws Exception {
		bean = new QualificationMaster();
		bean.setMenuCode(25);
		getNavigationPanel(1);
	}
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
	
		model.getRecords(bean, request);
		model.terminate();
		reset();
		getNavigationPanel(1);
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	public QualificationMaster getBean() {
		return bean;
	}
	public void setBean(QualificationMaster bean) {
		this.bean = bean;
	}
	
	
	
	public String input() throws Exception{
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
	
		model.getRecords(bean, request);
		model.terminate();
		getNavigationPanel(1);

		
		return INPUT;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "qualificationData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	public String edit() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
		model.getQualificationEdt(bean);
		model.getRecords(bean, request);
		bean.setEditFlag(true);
		model.terminate();
		return "success";
		
		
	}

	public String cancelSec() throws Exception{
		getNavigationPanel(1);
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		model.terminate();
		reset();
		return "view";
		
	}
	
	public String cancelFirst() throws Exception{
		
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
		if(bean.getEditFlag()){
			model.getQualificationRec(bean);
			model.getRecords(bean, request);
			bean.setQualiView(true);
			getNavigationPanel(3);
		}else{
			model.getRecords(bean, request);
			getNavigationPanel(1);
			reset();
		}
		
		
		bean.setOnLoadFlag(true);
		bean.setLoadFlag(false);
		model.terminate();
		
		return "view";
		
	}
	/**
	 * following function is called when user wants to save or update a record  
	 * @return
	 */
	public String save(){
		System.out.println("............................................................................");
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getQualificationID().equals("")) {
		result=model.save(bean);
		
		if(result) {
			addActionMessage(getMessage("save"));
			
		} else {
			addActionMessage("Type can not be added");
		}
		}
		else {
			result = model.update(bean);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("Type can not be added");
			}// end of else
		}
		
		model.terminate();
		getNavigationPanel(3);
		
		return "qualificationData";
	}
	
	/**public String save() throws Exception {
		String result = "";
		String str;
		QualificationModel model=new QualificationModel();
		model.initiate(context,session);
		
		 {
			 if(bean.getQuaID().equals("")){
				 result=model.addQualification(bean);
				 if(result.equals("saved"))
				 {
					 addActionMessage(getText("Record saved successfully."));
					 getNavigationPanel(3);
					 str="qualificationData";
				
				 }
				 else if(result.equals("duplicate")){
					
					 addActionMessage(getText("Duplicate entry found."));
					 getNavigationPanel(1);
					 
					 str="success";
					
				 }
				 else {					
					 addActionMessage(getText("Record can not be saved."));
					 getNavigationPanel(1);
					 str="success";
					 
				 }
			 }
			 else{
				 result=model.modQualification(bean);
				 if(result.equals("modified"))
				 {				
					addActionMessage(getText("Record updated successfully."));
					 getNavigationPanel(3);
					 str="qualificationData";
				
					
				 }
				 else if(result.equals("duplicate")){ 
					 addActionMessage(getText("Duplicate entry found."));
					 getNavigationPanel(1);
					 str="success";
					
				 }
				 else{
					 addActionMessage(getText("Record can't be updated."));
					 getNavigationPanel(1);
					 str="success";
					
				 }
			 }
	
		
		}
	
		 return str;
	}*/
	
	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
		
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
				
		boolean result=model.deleteQualification(bean);
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
			reset();
			callPage();
		}else{
			addActionMessage(getText("This record is referenced in some other resources.\nso can not be deleted!"));
			callPage();
		}
		getNavigationPanel(1);
		model.terminate();
		
		return "success";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		bean.setQuaID("");
		bean.setQuaName("");
		bean.setQuaAbbr("");
		bean.setQualevel("");
		bean.setDesciption("");
		bean.setIsactive("");
		bean.setQualificationID("");
		getNavigationPanel(2);
		return "qualificationData";
	}
	
	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String  getRecord() throws Exception{
		QualificationModel model=new QualificationModel();
		model.initiate(context, session);
		model.getQualification(bean);
		bean.setSaveFlag(true);
		model.getRecords(bean,request);
		model.terminate();
		return "success";
	}
	
	
		
	public String calforedit() throws Exception {
		getNavigationPanel(3);
		QualificationModel model=new QualificationModel();
		model.initiate(context,session);
		model.getQualificationOnDoubleClick(bean);
		model.getRecords(bean,request);
		getNavigationPanel(3);
		bean.setEnableAll("N");
		model.terminate();
		return "qualificationData";
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
	/**public String delete1() throws Exception{
			
		QualificationModel model = new QualificationModel();
		model.initiate(context,session);
		String[] code=request.getParameterValues("hdeleteCode");
		boolean result=model.delChkdRec(bean,code);
		if(result){
			addActionMessage(getText("delMessage",""));
		}
		else{
			addActionMessage("One or more records can not be deleted \n as they are associated with some other records. ");
		}
		bean.setOnLoadFlag(true);
		model.getRecords(bean, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}*/
	public String delete1(){
		String code[] = request.getParameterValues("hdeleteCode");
		String quaId[]=request.getParameterValues("quaID");
		//for (int j = 0; j < code.length; j++) {
			//System.out.println("......"+code[j]);
		//}
		//for (int i = 0; i < code.length; i++) {
			//System.out.println("......"+empId[i]);
		//}
		QualificationModel model = new QualificationModel();
		model.initiate(context, session);
		boolean result = model.delChkdRec(bean, code,quaId,request);
		if(result)
		{
			addActionMessage("Record deleted successfully");
		}
		else
		{
			addActionMessage("Please Select record");
		}
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	/**
	 * 	 
	 *  The Following Method is used to display Search Window to get Record to modify 
	 */

	
	public String f9Action() throws Exception {
		
		
		//String query = " SELECT QUA_NAME,QUA_ABBR,DECODE(QUA_STATUS,'A','Active','D','Deactive'),QUA_ID FROM HRMS_QUA ORDER BY upper(QUA_NAME)";
		String query=" SELECT NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),CASE WHEN QUA_ISACTIVE='Y' THEN 'Yes' ELSE 'No' END,QUA_ID  FROM HRMS_QUA"
			+" ORDER BY upper(QUA_NAME)";
			String []headers ={getMessage("qual.name"),getMessage("qual.abbr"),getMessage("qual.isactive")};
			String []headerwidth={"20","20","10"};
			
			
			String fieldNames[]={"quaName","quaAbbr","isactive","qualificationID"};
		
			int []columnIndex={0,1,2,3};
		
			String submitFlage ="true";
			
			String submitToMethod ="QualificationMaster_details.action";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
	public String details() {
		System.out.println(".................");
		
		//QualificationModel model = new QualificationModel();
		//model.initiate(context,session);
     	//model.getQualificationRec(bean);
     	
		 //model.terminate();
		 getNavigationPanel(3);
		 bean.setEnableAll("N");
		return "qualificationData";
		
	}
	/**
	 * following function is used to get the Pdf Report
	 */
	public String report(){
		getNavigationPanel(3);
		QualificationModel model = new QualificationModel();	
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("qual.name"),getMessage("qual.abbr"),getMessage("qual.isactive")};
		model.generateReport(bean,response,label);
		model.terminate();
		return null;
	}
}
