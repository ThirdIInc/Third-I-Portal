package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.RankMaster;
import org.paradyne.model.admin.master.RankModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author tinshuk.banafar
 *
 */
public class RankMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.admin.master.RankMasterAction.class); 
	
	RankMaster bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		bean = new RankMaster();
		bean.setMenuCode(26);

	}
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		//callPage();
	}

	public Object getModel() {
		return bean;
	}
	public RankMaster getBean() {
		return bean;
	}
	public void setBean(RankMaster bean) {
		this.bean = bean;
	}

//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "rankData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String input() throws Exception {
		RankModel model=new RankModel();
		model.initiate(context,session);
		model.getRecords(bean, request);
		bean.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String cancel() {
		RankModel model=new RankModel();
		model.initiate(context,session);
		model.getRecords(bean, request);
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
	
	
	
	public String edit() throws Exception{
		getNavigationPanel(2);
		RankModel model=new RankModel();
		model.initiate(context, session);
		model.getDesignation(bean, bean.getDesignationCode());
		bean.setEditFlag("true");
		//bean.setCancelFlag("false");
		model.terminate();
		return "success";
	}
	
	public String cancelSecond() throws Exception{
		getNavigationPanel(1);
		RankModel model=new RankModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		reset();
		model.terminate();
		return "view";
	}


	/**
	 * following function is called when add new record and to update a record in the jsp page
	 * @return
	 */
	
	public String save() throws Exception {
		
		String result="";
		String page;
		
		
		
		 RankModel model=new RankModel();
		model.initiate(context, session);
		
		 {
		 if(bean.getDesignationCode().equals("")){
		 result=model.addDesignation(bean);
			 if(result.equals("saved")){
				 addActionMessage(getText("addMessage", ""));
				 getNavigationPanel(3);
				 page="rankData";
			}
			 else if(result.equals("duplicate")){
				 addActionMessage(getText("Duplicate entry found!"));
				 getNavigationPanel(1);
				 page="success";
			}
			 else{
				 addActionMessage(getText("Record can not be saved!"));
				 getNavigationPanel(1);
				 page="success";
			 }
		}
		else{
			result = model.modDesignation(bean);			
			if(result.equals("modified")){
				addActionMessage(getText("Record updated successfully!"));
				 getNavigationPanel(3);
				 page="rankData";
		
			}
			else if(result.equals("duplicate")){
				addActionMessage(getText("Duplicate entry found!"));
				 getNavigationPanel(1);
				page="success";
			}
			else{
				addActionMessage(getText("Record Can not be updated!"));
				 getNavigationPanel(1);
				page="success";
			}
		}
		model.getRecords(bean,request);
		}
		 model.terminate();
		 return page;
	}
	
	
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		getNavigationPanel(1);
		RankModel model=new RankModel();
		model.initiate(context, session);
		boolean result=model.deleteDesignation(bean);
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
			//reset();
			//callPage();
		}else{
			addActionMessage("This record is referenced in some other records \n so can not be deleted");
			//callPage();
		}
		model.getRecords(bean,request);
		model.terminate();
		getNavigationPanel(1);
		bean.setDesignationCode("");
		bean.setDesignationName("");
		bean.setDesignationAbbr("");
		bean.setDesignationDesc("");
		bean.setIsActive("");
		return "success";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		bean.setDesignationCode("");
		bean.setDesignationName("");
		bean.setDesignationAbbr("");
		bean.setDesignationDesc("");
		bean.setIsActive("N");
		bean.setHdeleteCode("");
		bean.setHiddencode("");
		bean.setSalaryRange("");
		getNavigationPanel(2);
		return "rankData";
	}
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		RankModel model=new RankModel();
		model.initiate(context, session);
		model.getRecords(bean,request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
		//reset();
		//return "view";
	}
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit(){
		try {
			RankModel model = new RankModel();
			model.initiate(context, session);
			String designationCode = request.getParameter("code");
			model.getDesignation(bean, designationCode);
			getNavigationPanel(3);
			bean.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rankData";
	}
	
	
	public String delete1()throws Exception {
		getNavigationPanel(1);
		String code[]=request.getParameterValues("hdeleteCode");
		RankModel model = new RankModel();
		model.initiate(context,session);
		for (int i = 0; i < code.length; i++) {
			System.out.println("############# code ###################"+String.valueOf(code[i]));
		}
		boolean result =model.delChkdRec(bean,code);
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("One or more records can not be deleted \n as they are associated with some other records.");
			}
		model.getRecords(bean, request);
		getNavigationPanel(1);
		//reset();
		model.terminate();
		return "success";
	}
	/**
	 * 	 
	 *  The Following Method is used to display Search Window to get Record to modify 
	 */

	
	public String f9Action() throws Exception {
		
		String query = " SELECT NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_RANK.RANK_ABBR,' '),DECODE(HRMS_RANK.IS_ACTIVE,'Y','Yes','N','No','No'),HRMS_RANK.RANK_ID, NVL(HRMS_RANK.SALARY_RANGE,' ')  FROM HRMS_RANK ORDER BY HRMS_RANK.RANK_ID ";
			
			String []headers ={getMessage("desg.name"), getMessage("desg.abbr"), getMessage("is.active")};
			String []headerwidth={"30", "30", "0"};
			
			
			String fieldNames[]={"designationName", "designationAbbr", "isActive", "designationCode", "salaryRange"};
		
			int []columnIndex={0,1,2,3,4};
		
			String submitFlage ="true";
			
			String submitToMethod ="RankMaster_details.action";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String details() {
		RankModel model = new RankModel();
		model.initiate(context, session);
		model.getDesignation(bean, bean.getDesignationCode());
		model.terminate();
		getNavigationPanel(3);
		return "rankData";
	}
	/**
	 * following function is called to get the Pdf report for list of Designation records
	 * 
	 */
	public String report() throws Exception{
		//getNavigationPanel(3);
		RankModel model = new RankModel();	
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("desg.name"),getMessage("desg.abbr"),getMessage("is.active")};
		model.generateReport(bean,response,label);
		model.terminate();
		return null;
	}

/* for generating report. Added by Tinshuk Banafar*/
public String getReport() throws Exception {
	RankModel model = new RankModel();
	model.initiate(context, session);
	
	String reportPath ="";

	model.getRankReport(bean, request, response, reportPath);
	model.terminate();
	return null;
}

public final String mailReport(){
	try {
		final RankModel model = new RankModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		String reportPath =  getText("data_path") + "/Report/Master" + poolName + "/";
		
		String checkBoxVar = bean.getHiddenChechfrmId();
		System.out.println("bean.getHiddenChechfrmId()====="
				+ bean.getHiddenChechfrmId());
		
		model.getRankReport(bean, request, response, reportPath);
		model.terminate();
	    } 
	   catch (Exception e) {
		e.printStackTrace();
	   }
	return "mailReport";
}


}
