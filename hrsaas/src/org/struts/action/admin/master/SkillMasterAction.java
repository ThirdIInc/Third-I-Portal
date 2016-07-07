package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.SkillMaster;
import org.paradyne.model.admin.master.SkillMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author sai
 * modified by Pradeep 
 * Date:05-01-2009
 *
 */
public class SkillMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SkillMasterAction.class);
	
	SkillMaster skillMaster;
	
	public SkillMaster getSkillMaster() {
		return skillMaster;
	}

	public void setSkillMaster(SkillMaster skillMaster) {
		this.skillMaster = skillMaster;
	}

	public void prepare_local() throws Exception {
		skillMaster=new SkillMaster();
		skillMaster.setMenuCode(459);
		
	}
	public void prepare_withLoginProfileDetails() throws Exception {
	
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
	
		return skillMaster;
	}
	/**
	 * following function is called when cancel button is clicked in the first page
	 * @return
	 * @throws Exception
	 */
	public String cancelFirst() throws Exception{
		String str;
		SkillMasterModel model=new SkillMasterModel();
		model.initiate(context, session);
		logger.info("value of edit flag"+skillMaster.getEditFlag());
		if(skillMaster.getCancelFlg().equals("true")){
			getNavigationPanel(3);
			skillMaster.setFlagView("true");
			model.details1(skillMaster);
			model.Data(skillMaster,request);
			str="view";
		}else{
			getNavigationPanel(1);
			model.Data(skillMaster,request);
			skillMaster.setFlagShow("true");
			reset();
			str="view";
		}

		model.terminate();
		return str;
	}
	/**
	 * following function is called when the Cancel button in the second page is clicked.
	 * @return
	 * @throws Exception
	 */
	public String cancelSec() throws Exception {
		getNavigationPanel(1);
		SkillMasterModel model=new SkillMasterModel();
		model.initiate(context,session);
		model.Data(skillMaster,request);
		skillMaster.setFlagShow("true");
		reset();
		model.terminate();
		return "view";
	}
	
	/**
	 * following function is called when the page is loaded
	 */
	public String input() throws Exception {
		getNavigationPanel(1);
		SkillMasterModel model=new SkillMasterModel();
		model.initiate(context,session);
		skillMaster.setFlagView("false");
		model.Data(skillMaster,request);
		skillMaster.setFlagShow("true");
		model.terminate();
		return "view";
		
		
	}
	
	

	public void reset()throws Exception{
		skillMaster.setSkills("");
		skillMaster.setSkillsAbbr("");
		skillMaster.setSkillCode("");
		skillMaster.setDesciption("");
		skillMaster.setStatus("");
		skillMaster.setHdeleteCode("");
		skillMaster.setHiddencode("");
		skillMaster.setFlagView("false");
		
	}
	
	/**
	 * following function is called to add a new record.
	 * @return
	 * @throws Exception
	 */	
	public String save()throws Exception{
		logger.info("in save method");
		
		String result="";
		getNavigationPanel(2);
		String str;
		
		SkillMasterModel model=new SkillMasterModel();
		model.initiate(context,session);
		
		if(skillMaster.getSkillCode().equals(""))	
		{
			result =model.saveData(skillMaster);
			
			if(result.equals("saved"))
			{
				addActionMessage("Record saved successfully!");
				getNavigationPanel(3);
				skillMaster.setFlagView("true");
				str="view";
			}
			else if(result.equals("duplicate"))
			{
				addActionMessage("Duplicate entry found!");
				skillMaster.setCancelFlg("false");
				getNavigationPanel(2);
				str="success";
			}
			else
			{
				addActionMessage("Record can not be saved!");
				skillMaster.setCancelFlg("false");
				getNavigationPanel(2);
				str="success";
			}
		}
		else
		{
			result = model.updateData(skillMaster);
			if(result.equals("modified"))
			{
				addActionMessage("Record updated successfully!");
				getNavigationPanel(3);
				skillMaster.setFlagView("true");
				str="view";
			}
			else if(result.equals("duplicate"))
			{
				addActionMessage("Duplicate entry found!");
				skillMaster.setCancelFlg("false");
				getNavigationPanel(2);
				str="success";
			}
			else
			{
				addActionMessage("Record can not be updated!");
				skillMaster.setCancelFlg("false");
				getNavigationPanel(2);
				str="success";
			}
		}
		model.Data(skillMaster,request);
		model.terminate();
		return str;
	}
	
	
	/**
	 * following function is called to delete a selected record.
	 */
	public String delete()throws Exception{
		getNavigationPanel(1);
		SkillMasterModel model=new SkillMasterModel();
		model.initiate(context,session);
		boolean flag=model.deleteData(skillMaster);
		if(flag){
		addActionMessage("Record deleted successfully");
		}
		else
			addActionMessage("This record is referenced in other resources.\nSo can not be deleted!");
	
		model.Data(skillMaster, request);
		skillMaster.setFlagShow("true");
		reset();
		model.terminate();
		
		return "view";
	}
	
	/**
	 * following function is called when the page is loaded and to display all the records
	 * @return
	 * @throws Exception
	 */
	
	public String callPage() throws Exception {
		getNavigationPanel(1);
		SkillMasterModel model = new SkillMasterModel();
			model.initiate(context,session);
			model.Data(skillMaster, request);
			skillMaster.setFlagShow("true");
		//	reset();
			model.terminate();
		 return "view";
		
		}
	
	public String callPageList() throws Exception {
		getNavigationPanel(2);
		SkillMasterModel model = new SkillMasterModel();
			model.initiate(context,session);
			model.Data(skillMaster, request);
			reset();
			model.terminate();
		  return "success"; 
		}
	
	/**
	 * following function is called when a record is double clicked from the list
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception{
		getNavigationPanel(3);
		//skillMaster.setCancelFlg("false");
		SkillMasterModel model = new SkillMasterModel();
		model.initiate(context,session);
		skillMaster.setFlagView("true");
		model.calforedit(skillMaster);
		model.Data(skillMaster, request);
		model.terminate();
		return "view";
		
	}
	/**
	 * following function is called when the search window is clicked
	 * @return
	 * @throws Exception
	 */
	public String f9action() throws Exception 
	{
		String query = "SELECT SKILL_NAME,SKILL_ABBR,DECODE(SKILL_STATUS,'A','Active','D','Deactive'),SKILL_ID FROM HRMS_SKILL ORDER BY SKILL_ID ";		
		String[] headers={getMessage("skill.name"),getMessage("skill.abbr"),getMessage("skill.stat")};
		String[] headerWidth={"20","20","10"};
		String[] fieldNames={"skillMaster.skills","skillMaster.skillsAbbr","status","skillMaster.skillCode"};
		int[] columnIndex={0,1,2,3}; 
		String submitFlag = "true";
		
		String submitToMethod="SkillMaster_details.action";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	/**
	 * following function is called when a record is selected from the search window.
	 * @return
	 */
	 public String details() {
		
		 System.out.println("details         details-----------------");
		 SkillMasterModel model = new SkillMasterModel();
		  model.initiate(context,session);
		 model.details1(skillMaster);
		 getNavigationPanel(3);
		 skillMaster.setFlag("true");
		 skillMaster.setFlagView("true");
		//skillMaster.setFlagShow("false");
	model.Data(skillMaster, request);
		 System.out.println("details  after       details-----------------");
		  model.terminate();
		 return "view";
		
	}
	
/**
 * following function is called to delete records from the list when the corresponding rows are checked
 * @return
 * @throws Exception
 */
	public String deleteRow()throws Exception {
			getNavigationPanel(1);
			String code[]=request.getParameterValues("hdeleteCode");
			SkillMasterModel model = new SkillMasterModel();
			model.initiate(context,session);
			boolean result =model.deletecheckedRecords(skillMaster,code);
			if(result) {
					addActionMessage(getText("delMessage",""));
				} else {
					addActionMessage("One or more records can not be deleted \n as they are associated with some other records!");
				}
			model.Data(skillMaster, request);
			skillMaster.setFlagShow("true");
			reset();
			model.terminate();
			return "view";
		}
	
	/**
	 * following function is called when Add New button is clicked.
	 * @return
	 * @throws Exception
	 */
	public String addNew() throws Exception{
		 getNavigationPanel(2);
		 SkillMasterModel model = new SkillMasterModel();
		 model.initiate(context,session);
		 model.Data(skillMaster,request);
		 skillMaster.setCancelFlg("false");
		 model.terminate();
		 return "success";
	}
/**
 * following function is called when edit button is clicked
 * @return
 * @throws Exception
 */	
	public String edit() throws Exception {
		getNavigationPanel(2);
		 SkillMasterModel model = new SkillMasterModel();
		  model.initiate(context,session);
		  skillMaster.setCancelFlg("true");
		  model.details(skillMaster);
		  model.Data(skillMaster, request);
		  model.terminate();
		return "success";
}
/**
 * following function is called to get the Pdf report for list of Skill Master records
 * 
 */
public String report(){
	getNavigationPanel(3);
	SkillMasterModel model = new SkillMasterModel();	
	model.initiate(context,session);
	String[]label={"Sr.No",getMessage("skill.name"),getMessage("skill.abbr"),getMessage("skill.stat")};
	model.generateReport(skillMaster,response,label);
	model.terminate();
	return null;
}	
}