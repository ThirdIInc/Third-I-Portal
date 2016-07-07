package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.DonationMaster;
import org.paradyne.model.admin.master.DonationMasterModel;
import org.struts.lib.ParaActionSupport;

public class DonationMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.admin.master.DonationMasterAction.class); 
	
	DonationMaster bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		bean = new DonationMaster();
		bean.setMenuCode(2234);

	}
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		//callPage();
	}

	public Object getModel() {
		return bean;
	}
	public DonationMaster getBean() {
		return bean;
	}
	public void setBean(DonationMaster bean) {
		this.bean = bean;
	}

//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "donationData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String input() throws Exception {
		DonationMasterModel model=new DonationMasterModel();
		model.initiate(context,session);
		model.getRecords(bean, request);
		bean.setEnableAll("Y");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	
	
	/**
	 * This function is used for Edit 
	 * @return success
	 * @throws Exception
	 */
	public String edit() throws Exception{
		getNavigationPanel(2);
		DonationMasterModel model=new DonationMasterModel();
		model.initiate(context, session);
		model.getDonation(bean);
		model.getRecords(bean, request);
		bean.setEditFlag("true");
		//bean.setCancelFlag("false");
		model.terminate();
		return "success";
	}
	
	
	
	
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		getNavigationPanel(1);
		DonationMasterModel model=new DonationMasterModel();
		model.initiate(context, session);
		boolean result=model.deleteDonation(bean);
		if(result){
			addActionMessage(getMessage("delete"));
			//reset();
			//callPage();
		}else{
			addActionMessage("This record is referenced in some other records \n so can not be deleted");
			//callPage();
		}
		model.getRecords(bean,request);
		model.terminate();
		getNavigationPanel(1);
		bean.setDonationCode("");
		bean.setDonationName("");
		bean.setPercentage("");
		
		bean.setIsActive("");
		return "success";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		DonationMasterModel model=new DonationMasterModel();
		model.initiate(context,session);
		bean.setDonationCode("");
		bean.setDonationName("");
		bean.setPercentage("");
		bean.setIsActive("");
		bean.setHdeleteCode("");
		bean.setHiddencode("");
		bean.setDonationHiddenCode("");
		getNavigationPanel(2);
		
		model.getRecords(bean, request);
		model.terminate();
		return "donationData";
	}
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		DonationMasterModel model=new DonationMasterModel();
		model.initiate(context, session);
		model.getRecords(bean,request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
		//reset();
		//return "view";
	}
	
	
	
	/**
	 * following function is called to get the Pdf report for list of Designation records
	 * 
	 */
	public String report(){
		//getNavigationPanel(3);
		DonationMasterModel model = new DonationMasterModel();	
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("donation.name"),getMessage("donation.percentage"),getMessage("is.active")};
		model.generateReport(bean,response,label);
		model.terminate();
		return null;
	}
	
	//add new
	/**
	 * Method to save the Donation details of an employee. 
	 * @return String
	 * @throws Exception
	 */

	public String addDonationDtl() throws Exception {
		DonationMasterModel model = new DonationMasterModel();	
		model.initiate(context, session);
		final boolean result;
		if (bean.getDonationHiddenCode().equals("")) {
			result = model.addDonationDtl(bean,request);
						
			if (result) {
				this.addActionMessage("Records Saved successfully.");
				 model.getRecords(bean, request);
					//bean.setEnableAll("Y");
					
				//	return SUCCESS;
				//reset();
			} else {
				this.addActionMessage("Donation Name already Exist. Please Enter new Donation Name.");
				model.getRecords(bean, request);
				bean.setEnableAll("Y");
				getNavigationPanel(2);
				//return SUCCESS;
			}
			
			
		}// end of if
		else {
			result = model.modDonationDtl(bean,request);
			this.addActionMessage("Records Updated successfully.");
			model.getRecords(bean, request);
			//bean.setEnableAll("Y");
			reset();
			//return SUCCESS;
		}// end of else
		model.terminate();
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
}
