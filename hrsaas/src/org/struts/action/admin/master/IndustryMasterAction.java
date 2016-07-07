package org.struts.action.admin.master;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.master.IndusMaster;
import org.paradyne.model.admin.master.IndusModel;

/**
 * 
 * @author Prasad
 *
 */

public class IndustryMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.admin.master.IndustryMasterAction.class); 
	
	IndusMaster bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		bean = new IndusMaster();
		bean.setMenuCode(573);

	}
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		//callPage();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	public IndusMaster getBean() {
		return bean;
	}
	public void setBean(IndusMaster bean) {
		this.bean = bean;
	}
	
	public String input() throws Exception{
		//Default Method with default modeCode(1)		
		getNavigationPanel(1);
		IndusModel model=new IndusModel();
		model.initiate(context, session);
		bean.setFlagShow("true");
		callPage();
		model.terminate();
		return "view";
	}
	
	public String addNew() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		IndusModel model=new IndusModel();
		model.initiate(context,session);
		model.getRecords(bean,request);
		bean.setEditFlag("false");
		model.terminate();
		return "success";
		
	}
	
	public String edit() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		IndusModel model=new IndusModel();
		model.initiate(context, session);
		model.getIndustry(bean);
		model.getRecords(bean, request);
		bean.setEditFlag("true");
		model.terminate();
		return "success";
	}
	
	public String cancelSecond() throws Exception{
		logger.info("Cancel Second---->");
		getNavigationPanel(1);
		IndusModel model=new IndusModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setFlagShow("true");
		reset();
		model.terminate();
		return "view";
	}

	public String cancelFirst() throws Exception{
		logger.info("Cancel First---->");
		String str;
		IndusModel model=new IndusModel();
		model.initiate(context, session);
		if(bean.getEditFlag().equals("true")){
			logger.info("If-------->");
			model.getIndustry(bean);
			model.getRecords(bean, request);
			bean.setIndustryView(true);
			getNavigationPanel(3);
			str="view";
		}else{
			logger.info("Else-------->");
			getNavigationPanel(1);
			model.getRecords(bean, request);
			bean.setFlagShow("true");
			reset();
			str="view";
		}
		return str;
	}
	/**
	 * following function is called when add new record is clicked in the jsp page
	 * @return
	 */
		
	public String save() throws Exception {
		String result="";
		//Default Method with Edit modeCode(3)
		String page="";
		IndusModel model=new IndusModel();
		model.initiate(context, session);
		
		 {
			 if(bean.getIndustryCode().equals("")){
				 result=model.addIndustry(bean);
				 if(result.equals("saved"))
				 {
					 getNavigationPanel(3);
					 bean.setIndustryView(true);
					 addActionMessage(getText("Record saved successfully!"));
					 page="view";
				 }
				 else if(result.equals("duplicate"))
				 {
					getNavigationPanel(2);
					addActionMessage(getText("Duplicate entry found!"));
					bean.setEditFlag("false");
					page="success";
				 }
				 else
				 {
					 getNavigationPanel(2);
					 addActionMessage(getText("Record can not be saved!"));
					 bean.setEditFlag("false");
					 page="success";
				 }
			}
			else{
				logger.info("inside else of save() method---->");
				 result=model.modIndustry(bean);
				 logger.info("result in else--->"+result);
				 if(result.equals("modified"))
				 {
					 getNavigationPanel(3);
					 bean.setIndustryView(true);
					 addActionMessage(getText("Record updated successfully!"));
					 page="view";
				 }
				 else if(result.equals("duplicate"))
				 {
					 getNavigationPanel(2);
					 addActionMessage(getText("Duplicate entry found!"));
					 bean.setEditFlag("false");
					 page="success";
				 }
				 else
				 {
					 getNavigationPanel(2);
					 addActionMessage("Record can not be updated!");	
					 bean.setEditFlag("false");
					 page="success";
				 }
			}
		model.getRecords(bean,request);
		}
		 model.terminate();
		 return page;
	}
	
	/**
	 * following function is called when update button is clicked in the jsp page  
	 * @return
	 */
	/*public String update() throws Exception{
		Default Method with save modeCode(2)
		getNavigationPanel(2);
		IndusModel model=new IndusModel();
		model.initiate(context, session);
		boolean result=model.modIndustry(bean);
		if(result){
			addActionMessage(getText("Record updated successfully!"));
			callPage();
		}else{
			addActionMessage(getText("Record can't be updated!"));
		}
		bean.setModFlag(true);
		//bean.setEditFlag(true);
		//bean.setSaveFlag(true);
		bean.setFlag(true);
		model.terminate();
		
		return "success";
	}*/
	
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		IndusModel model=new IndusModel();
		model.initiate(context, session);
				
		boolean result=model.deleteIndustry(bean);
		
		logger.info("result in Delete---->"+result);
		
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
			
		}else{
			addActionMessage(getText("This record is referenced in other resources.\no can not be deleted"));
		}
		callPage();
		model.terminate();
		
		return "view";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		bean.setIndustryCode("");
		bean.setIndustryName("");
		bean.setIndustryAbbr("");
		bean.setIndustryDesc("");
		bean.setIndustryStatus("A");
		return "success";
	}
	
	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String  getRecord() throws Exception{
		
		IndusModel model=new IndusModel();
		model.initiate(context, session);
		model.getIndustry(bean);
		model.getRecords(bean,request);
		model.terminate();
		return "success";
	}
	
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		IndusModel model=new IndusModel();
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
		logger.info("Calforedit inside indus ");
		IndusModel model=new IndusModel();
		model.initiate(context,session);
		model.getIndustryOnDoubleClick(bean);
		model.getRecords(bean,request);
		bean.setIndustryView(true);
		bean.setEditFlag("false");
		model.terminate();
		return "view";
	}
	
	
	public String delete1()throws Exception {
		
		getNavigationPanel(1);
		String code[]=request.getParameterValues("hdeleteCode");
		IndusModel model = new IndusModel();
		
		model.initiate(context,session);
		boolean result =model.delChkdRec(bean,code);
		
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("One or more records can not be deleted \n as they are associated with some other records");
			}
		callPage();
		model.terminate();
		return "view";

	}
	
	
	/**
	 * 	 
	 *  The Following Method is used to display Search Window to get Record to modify 
	 */

	
	public String f9Action() throws Exception {
		
		String query = " SELECT NVL(INDUS_NAME,' '),NVL(INDUS_ABBRIVIATION,' '),DECODE(INDUS_STATUS,'A','Active','D','Deactive'),INDUS_CODE  FROM HRMS_INDUS_TYPE ORDER BY INDUS_CODE";
			
			String []headers ={getMessage("indu.name"),getMessage("indu.abbr"),getMessage("indu.stat")};
			String []headerwidth={"20","20","10"};
			
			
			String fieldNames[]={"industryName","industryAbbr","industryStatus","industryCode"};
		
			int []columnIndex={0,1,2,3};
		
			String submitFlage ="true";
			
			String submitToMethod ="IndustryMaster_details.action";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
	public String details() {
		getNavigationPanel(3);
		IndusModel model = new IndusModel();
		model.initiate(context, session);
		model.getIndustry(bean);
		model.getRecords(bean,request);
		bean.setIndustryView(true);
		model.terminate();
		return "view";
	}
	/**
	 * following function is called to get the Pdf report for list of Industry records
	 * 
	 */
	public String report(){
		getNavigationPanel(3);
		IndusModel model = new IndusModel();	
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("indu.name"),getMessage("indu.abbr"),getMessage("indu.stat")};
		model.generateReport(bean,response,label);
		model.terminate();
		return null;
	}
}
