/**
 * 
 */
package org.struts.action.helpdesk;

import org.paradyne.bean.helpdesk.HelpDeskStatusCatg;
import org.paradyne.model.helpdesk.HelpDeskStatusCatgModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0623
 *
 */
public class HelpDeskStatusCatgAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.helpdesk.HelpDeskStatusCatgAction.class);

	HelpDeskStatusCatg statusCatg;
	/**
	 * @return the statusCatg
	 */
	public HelpDeskStatusCatg getStatusCatg() {
		return statusCatg;
	}

	/**
	 * @param statusCatg the statusCatg to set
	 */
	public void setStatusCatg(HelpDeskStatusCatg statusCatg) {
		this.statusCatg = statusCatg;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		statusCatg = new HelpDeskStatusCatg();
		statusCatg.setMenuCode(1046);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return statusCatg;
	}
	
	@Override
	public String input() throws Exception {
		HelpDeskStatusCatgModel model = new HelpDeskStatusCatgModel();
		model.initiate(context, session);
		model.getStatusCategList(statusCatg,request);
		model.terminate();
		getNavigationPanel(1);
		statusCatg.setEnableAll("N");
		return INPUT;
	}
	
	public String addNew() throws Exception {
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String f9Action() throws Exception {
		
		String query = " SELECT NVL(STATUS_CATAGORY_NAME,' '), NVL(STATUS_ABBREV,' '), "
			+ " STATUS_CATAGORY_ID"
			+ " FROM HELPDESK_SLA_STATUS_CATAGORY "
			+ " ORDER BY STATUS_CATAGORY_ID ";
			
			String []headers ={getMessage("status.categ"), getMessage("status.abbrev")};
			String []headerwidth={"65", "35"};
			
			
			String fieldNames[]={"statusCateg", "statusAbbrev", "statusCategCode"};
		
			int []columnIndex={0,1,2};
		
			String submitFlage ="true";
			
			String submitToMethod ="HelpDeskStatusCateg_details.action";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
	
	public String details() {
		getNavigationPanel(3);
		HelpDeskStatusCatgModel model = new HelpDeskStatusCatgModel();
		model.initiate(context, session);
		model.getRecords(statusCatg,request);
		getNavigationPanel(3);
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
		HelpDeskStatusCatgModel model=new HelpDeskStatusCatgModel();
		model.initiate(context, session);
		model.getStatusCategList(statusCatg,request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		statusCatg.setEditHidcode("1");
		HelpDeskStatusCatgModel model=new HelpDeskStatusCatgModel();
		model.initiate(context,session);
		model.getStatusCategOnDblClick(statusCatg);
		getNavigationPanel(3);
		statusCatg.setEnableAll("N");
		model.terminate();
		return SUCCESS;
	}
	
	public String deleteList()throws Exception {
		getNavigationPanel(1);
		String code[]=request.getParameterValues("hdeleteCode");
		HelpDeskStatusCatgModel model = new HelpDeskStatusCatgModel();
		model.initiate(context,session);
		boolean result =model.delChkdRec(code);
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("One or more records can not be deleted \n as they are associated with some other records.");
			}
		model.getStatusCategList(statusCatg, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	public String reset(){
		statusCatg.setStatusCategCode("");
		statusCatg.setStatusCateg("");
		statusCatg.setStatusAbbrev("");
		statusCatg.setStatusOrder("");
		statusCatg.setIsStatusLast("");
		//statusCatg.setHdeleteCode("");
		statusCatg.setHiddencode("");
		statusCatg.setEditHidcode("");
		//statusCatg.setHidDeptCode("");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		getNavigationPanel(1);
		HelpDeskStatusCatgModel model=new HelpDeskStatusCatgModel();
		model.initiate(context, session);
		boolean result=model.deleteStatusCateg(statusCatg);
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
		}else{
			addActionMessage("This record is referenced in some other records \n so can not be deleted");
		}
		model.getStatusCategList(statusCatg,request);
		model.terminate();
		getNavigationPanel(1);
		statusCatg.setStatusCategCode("");
		statusCatg.setStatusCateg("");
		statusCatg.setStatusAbbrev("");
		statusCatg.setStatusOrder("");
		statusCatg.setIsStatusLast("");
		return INPUT;
	}
	
	public String save() throws Exception {

		String result = "";
		String page;

		HelpDeskStatusCatgModel model = new HelpDeskStatusCatgModel();
		model.initiate(context, session);
		logger.info("Edit hid code : "+statusCatg.getEditHidcode());

		if (statusCatg.getEditHidcode().equals("")) {
			result = model.addStatusCategories(statusCatg);
			if (result.equals("saved")) {
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
				page = "success";
			} else if (result.equals("duplicate")) {
				addActionMessage(getText("Duplicate entry found!"));
				getNavigationPanel(1);
				input();
				page = "input";
			} else if (result.equals("last")) {
				addActionMessage(getText("Another status has already been defined as last."));
				getNavigationPanel(1);
				input();
				page = "input";
			} else {
				addActionMessage(getText("Record can not be saved!"));
				getNavigationPanel(1);
				input();
				page = "input";
			}
		} else {
			result = model.modStatusCategories(statusCatg);
			if (result.equals("modified")) {
				addActionMessage(getText("Record updated successfully!"));
				getNavigationPanel(3);
				page = "success";

			} else if (result.equals("duplicate")) {
				addActionMessage(getText("Duplicate entry found!"));
				getNavigationPanel(1);
				input();
				page = "input";
			} else if (result.equals("last")) {
				addActionMessage(getText("Another status has already been defined as last."));
				getNavigationPanel(1);
				input();
				page = "input";
			} else {
				addActionMessage(getText("Record Can not be updated!"));
				getNavigationPanel(1);
				input();
				page = "input";
			}
			model.getStatusCategList(statusCatg, request);
		}
		model.terminate();
		return page;
	}

}
