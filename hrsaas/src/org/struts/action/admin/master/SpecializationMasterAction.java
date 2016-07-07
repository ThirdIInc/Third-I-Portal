package org.struts.action.admin.master;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.master.SpecializationMaster;
import org.paradyne.model.admin.master.SpecializationMasterModel;
/**
 * 
 * @author Prasad
 *
 */

public class SpecializationMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.admin.master.SpecializationMasterAction.class); 
	
	SpecializationMaster bean;
	public void prepare_local() throws Exception {
		bean = new SpecializationMaster();
		bean.setMenuCode(739);

	}
	public void prepare_withLoginProfileDetails() throws Exception {
	}

	public Object getModel() {
		return bean;
	}
	public SpecializationMaster getBean() {
		return bean;
	}
	public void setBean(SpecializationMaster bean) {
		this.bean = bean;
	}
	
	public String input() throws Exception{
		/*Default Method with default modeCode(1)*/		
		getNavigationPanel(1);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context,session);
		bean.setFlagShow("true");
		//bean.setSpecializationStatus("");
		//callPage();
		model.getRecords(bean, request);
		model.terminate();
		return "view";
	}
	
	public String addNew() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context,session);
		model.getRecords(bean, request);
		bean.setEditFlag(false);
		model.terminate();
		return "success";
	}
	
	public String edit() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
		model.getSpecialization(bean);
		model.getRecords(bean, request);
		bean.setEditFlag(true);
		model.terminate();
		return "success";
	}
	
	
	/**
	 * following function is called when the cancel button in the second page is clicked.
	 * @return
	 * @throws Exception
	 */
	public String cancelSec() throws Exception{
			getNavigationPanel(1);
			SpecializationMasterModel model=new SpecializationMasterModel();
			model.initiate(context, session);
			model.getRecords(bean, request);
			bean.setFlagShow("true");
			model.terminate();
			reset();
		return "view";
	}
	
	/**
	 * following function is called when the cancel button in the first page is clicked
	 * @return
	 * @throws Exception
	 */
	public String cancelFirst() throws Exception{
		String str;
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
		if(bean.getEditFlag()){
				str="view";
				getNavigationPanel(3);
				bean.setSpecializationView(true);
				model.getSpecialization(bean);
				model.getRecords(bean, request);
		}else{
				str="view";
				getNavigationPanel(1);
				bean.setFlagShow("true");
				model.getRecords(bean, request);
				reset();
		}
		model.terminate();
		return str;
	}
	
	
	
	public String save() throws Exception {
		//Default Method with Edit modeCode(3)
		String result = "";
		String str;
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
		 {
		if(bean.getSpecializationCode().equals("")){
			 result = model.addSpecialization(bean);
			 logger.info("result of model flag--->"+result);
			 if(result.equals("saved")){
				 str="view";
				 bean.setSpecializationView(true);
				 getNavigationPanel(3);
				addActionMessage(getText("addMessage", ""));
			
			}
			 else if(result.equals("duplicate")){
				 str="success";
				 getNavigationPanel(2);
				addActionMessage(getText("Duplicate entry found!"));
				  bean.setEditFlag(false);
			}
			 else{
				 str="success";
				 getNavigationPanel(2);
				 addActionMessage(getText("Record can not be saved!"));
				  bean.setEditFlag(false);
			 }
		}
		else{
			result = model.modSpecialization(bean);
			
			logger.info("Information of Edit Flag in Action-----> "+result);
			
			if(result.equals("modified")){
				str="view";
				getNavigationPanel(3);
				bean.setSpecializationView(true);
				addActionMessage(getText("Record updated successfully!"));
			}
			else if(result.equals("duplicate")){
				str="success";
				getNavigationPanel(2);
				addActionMessage(getText("Duplicate entry found!"));
				 bean.setEditFlag(false);
			}
			else{
				str="success";
				getNavigationPanel(2);
				addActionMessage(getText("Record can not be updated!"));
				 bean.setEditFlag(false);
			}
				 
	  }
		 
		model.getRecords(bean,request);
		
	  }
		 model.terminate();
		 return str;
	 }
		
	
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
				
		boolean result=model.deleteSpecialization(bean);
		
		logger.info("result in Delete---->"+result);
		bean.setFlagShow("true");
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
			model.getRecords(bean, request);
			reset();
			//callPage();
		}else{
			addActionMessage(getText("This record is referenced in some other resources\nso can not be deleted!"));
			model.getRecords(bean, request);
			//callPage();
		}
		model.terminate();
		
		return "view";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context,session);
		bean.setSpecializationCode("");
		bean.setSpecializationName("");
		bean.setSpecializationAbbr("");
		bean.setSpecializationDesc("");
		bean.setSpecializationStatus("");
		model.terminate();
		return "success";
	}
	
	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String  getRecord() throws Exception{
		
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
		model.getSpecialization(bean);
		bean.setSaveFlag(true);
		model.getRecords(bean,request);
		model.terminate();
		return "success";
	}
	
	public String callPage1() throws Exception {
		getNavigationPanel(1);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
		bean.setPageFlag(true);
		//bean.setOnLoadFlag("true");
		model.getRecords(bean,request);
		model.terminate();
		reset();
		
		return "success";
	}
	public String callPage2() throws Exception {
		getNavigationPanel(1);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
		model.getRecords(bean,request);
		bean.setPageFlag(true);
		model.terminate();
		reset();
		
		return "success";
	}
	
	
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context, session);
		model.getRecords(bean,request);
		bean.setFlagShow("true");
		model.terminate();
		reset();
		
		return "view";
	}
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		getNavigationPanel(3);
		SpecializationMasterModel model=new SpecializationMasterModel();
		model.initiate(context,session);
		model.getSpecializationOnDoubleClick(bean);
		model.getRecords(bean,request);
		bean.setSpecializationView(true);
	   bean.setEditFlag(true);
		model.terminate();
		return "view";
	}
	
	public String delete1() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		String[] code=request.getParameterValues("hdeleteCode");
		boolean result;
		SpecializationMasterModel model = new SpecializationMasterModel();
		model.initiate(context,session);
		result=model.delChkdRec(bean,code);
		if(result){
			addActionMessage(getText("delMessage",""));
		}
		else{
			addActionMessage("One or more records can not be deleted \n as they are associated with some other records");
		}
		
		model.getRecords(bean, request);
		bean.setFlagShow("true");
		reset();
		model.terminate();
		return "view";
	}
	/**
	 * 	 
	 *  The Following Method is used to display Search Window to get Record to modify 
	 */
	
	public String f9Action() throws Exception {
		String query = " SELECT SPEC_NAME,SPEC_ABBR,DECODE(SPEC_STATUS,'A','Active','D','Deactive'),SPEC_ID  FROM HRMS_SPECIALIZATION  ORDER BY SPEC_ID";
		String []headers ={getMessage("spec.name"),getMessage("spec.abbr"), getMessage("spec.stat")};
			String []headerwidth={"40", "40", "20"};
			String fieldNames[]={"specializationName", "specializationAbbr", "specializationStatus", "specializationCode"};
			int []columnIndex={0,1, 2, 3};
			String submitFlage ="true";
			String submitToMethod ="SpecializationMaster_details.action";
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			return "f9page";
	}
	public String details() {
		
		getNavigationPanel(3);
		SpecializationMasterModel model = new SpecializationMasterModel();
		model.initiate(context, session);
		model.getSpecializationRec(bean);
		bean.setSpecializationView(true);
	    model.getRecords(bean,request);
		model.terminate();
		return "view";
	}
	/**
	 * following function is called to get the Pdf report for list of Specialization records
	 * 
	 */
	public String report(){
		getNavigationPanel(3);
		SpecializationMasterModel model = new SpecializationMasterModel();	
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("spec.name"),getMessage("spec.abbr"),getMessage("spec.stat")};
		model.generateReport(bean,response,label);
		model.terminate();
		return null;
	}
}
