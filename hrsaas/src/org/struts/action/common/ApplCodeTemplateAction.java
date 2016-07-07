/**
 * 
 */
package org.struts.action.common;

import org.paradyne.bean.common.ApplCodeTemplate;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class ApplCodeTemplateAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.action.common.ApplCodeTemplateAction.class);
	ApplCodeTemplate applCodetTemplate =null;
	public void prepare_local() throws Exception {
		applCodetTemplate = new ApplCodeTemplate();
		applCodetTemplate.setMenuCode(923);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return applCodetTemplate;
	}
	public String next(){
		applCodetTemplate.setMainPageFlag("false");
		request.setAttribute("applicationType", getapplicatioTypeStr(applCodetTemplate.getApplicationType()));
		ApplCodeTemplateModel model=new ApplCodeTemplateModel();
		model.initiate(context, session);
		model.getTemplateDetails(applCodetTemplate,request);
		model.terminate();
		return SUCCESS;
	
	}
	public String getapplicatioTypeStr(String applnType){
		String applnTypeStr ="";
		if(applnType.equals("Default")){
			logger.info("application type is Default");
			applnTypeStr = "Default";
		}	//end of if
		if(applnType.equals("Leave")){
			logger.info("application type is Leave");
			applnTypeStr = "Leave";
		}	//end of if
		else if(applnType.equals("Requi")){
			logger.info("application type is Requisition");
			applnTypeStr = "Requisition";
		}	//end of elseif
		else if(applnType.equals("Cash")){
			logger.info("application type is Cash");
			applnTypeStr = "Cash Voucher";
		}	//end of elseif
		else if(applnType.equals("Train")){
			logger.info("application type is Training");
			applnTypeStr = "Training";
		}	//end of elseif
		else if(applnType.equals("Tran")){
			logger.info("application type is Transfer");
			applnTypeStr = "Transfer";
		}	//end of elseif
		else if(applnType.equals("Sugg")){
			logger.info("application type is Suggestion");
			applnTypeStr = "Suggestion";
		}	//end of elseif
		else if(applnType.equals("Help")){
			logger.info("application type is Help Desk");
			applnTypeStr = "Help Desk";
		}	//end of elseif
		else if(applnType.equals("LTC")){
			logger.info("application type is LTC Advance");
			applnTypeStr = "LTC Advance";
		}	//end of elseif
		else if(applnType.equals("EmpId")){
			logger.info("application type is Employee Id");
			applnTypeStr = "Employee Id";
		}	//end of elseif
		
		/*else if(applnType.equals("Festi")){
			logger.info("application type is Festival Advance");
			applnTypeStr = "Festival Advance";
		}	//end of elseif
		else if(applnType.equals("Other")){
			logger.info("application type is Other Advance");
			applnTypeStr = "Other Advance";
		}	//end of elseif
		else if(applnType.equals("Deput")){
			logger.info("application type is Deputation");
			applnTypeStr = "Deputation";
		}	//end of elseif
		else if(applnType.equals("HBA")){
			logger.info("application type is HBA");
			applnTypeStr = "HBA";
		}	//end of elseif
		else if(applnType.equals("GPF")){
			logger.info("application type is GPF");
			applnTypeStr = "GPF";
		}	//end of elseif
		else if(applnType.equals("CTF")){
			logger.info("application type is Children Tution Fee");
			applnTypeStr = "Children Tution Fee";
		}	//end of elseif
		else if(applnType.equals("Medi")){
			logger.info("application type is Medical Advance");
			applnTypeStr = "Medical Advance";
		}	//end of elseif
*/		else if(applnType.equals("TYD")){
			logger.info("application type is Travel");
			applnTypeStr = "Travel";
		}	//end of elseif
		else if(applnType.equals("Appra")){
			logger.info("application type is Appraisal");
			applnTypeStr = "Appraisal";
		}	//end of elseif
		else if(applnType.equals("Confere")){
			logger.info("application type is Conference");
			applnTypeStr = "Conference";
		}	//end of elseif
		else if(applnType.equals("Loan")){
			logger.info("application type is Loan");
			applnTypeStr = "Loan";
		}	//end of elseif
		else if(applnType.equals("Asset")){
			logger.info("application type is Asset");
			applnTypeStr = "Asset";
		}	//end of elseif
		else if(applnType.equals("Purchase")){
			logger.info("application type is Purchase");
			applnTypeStr = "Purchase";
		}	//end of elseif
		/*else if(applnType.equals("Cash Process")){
			logger.info("application type is Cash Process");
			applnTypeStr = "Cash Process";
		}	//end of elseif
*/		else if(applnType.equals("Recruitment")){
			logger.info("application type is Recruitment");
			applnTypeStr = "Recruitment";
		}	
		else if(applnType.equals("REIMB")){
			logger.info("application type is Reimbursement");
			applnTypeStr = "Reimbursement";
		}	//end of elseif
		return applnTypeStr;
	}
	public String back(){
		applCodetTemplate.setMainPageFlag("true");
		request.setAttribute("applicationType", "");
		applCodetTemplate.setApplicationType("");
		return SUCCESS;
	
	}
	public String delete(){
		String tempString = applCodetTemplate.getApplicationType();
		applCodetTemplate.setApplicationType("");
		ApplCodeTemplateModel model=new ApplCodeTemplateModel();
		model.initiate(context, session);
		if(model.delete(applCodetTemplate)){
			addActionMessage("Record deleted sucessfully");
			reset();
		}else{
			addActionMessage(getMessage("del.error"));
			
		}
		applCodetTemplate.setApplicationType(tempString);
		next();
		model.terminate();
		return SUCCESS;
	
	}
	public String save(){
		ApplCodeTemplateModel model = new ApplCodeTemplateModel();
		model.initiate(context, session);
		String tempString = applCodetTemplate.getApplicationType();
		String autoIdType = applCodetTemplate.getAutoGenIdType();
		boolean result =true;
		if(applCodetTemplate.getApplCodeType().equals("T")){
		 result = checkAutoId(autoIdType);
		}
		 if(!result){
				addActionMessage("Please add "+autoIdType+" element in the template as Autogenerated Id Type is "+autoIdType+"-wise");
				applCodetTemplate.setMainPageFlag("false");
				request.setAttribute("applicationType", getapplicatioTypeStr(tempString));
			}
		 else{
		if(model.save(applCodetTemplate)){
			addActionMessage("Record saved sucessfully");
			reset();
		}else{
			addActionMessage("Record can't be saved");
			applCodetTemplate.setMainPageFlag("false");
			request.setAttribute("applicationType", getapplicatioTypeStr(tempString));
		}
		
		 }
		 model.terminate();
		return SUCCESS;
	}
	public boolean checkAutoId(String autoIdType){
		boolean result= false;
		String template = applCodetTemplate.getHiddenTemplateName();
		
		if(autoIdType.equals("Division")){
			result = template.contains("<#DIVISION#>");
			
		}else if (autoIdType.equals("Branch")){
			result = template.contains("<#BRANCH#>");
			
		}else if (autoIdType.equals("Department")){
			result = template.contains("<#DEPARTMENT#>");
		}	
		else if (autoIdType.equals("First Name")){
			result = template.contains("<#FNAME INITIAL#>");
		}
		else if (autoIdType.equals("Last Name")){
			result = template.contains("<#LNAME INITIAL#>");
					
		}else
			result = true;
		return result;
	}
	public String reset(){
		applCodetTemplate.setApplicationType("");
		applCodetTemplate.setTemplateId("");
		applCodetTemplate.setTemplateName("");
		applCodetTemplate.setHiddenTemplateName("");
		applCodetTemplate.setAutoGenIdType("");
		applCodetTemplate.setResetType("");
		applCodetTemplate.setAutoIdFlag("");
		applCodetTemplate.setMainPageFlag("true");
		applCodetTemplate.setSeperatorValue("");
		applCodetTemplate.setTextToAppend("");
		applCodetTemplate.setAutoIdDigits("");
		return SUCCESS;
	}

}
