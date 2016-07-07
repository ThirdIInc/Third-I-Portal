package org.struts.action.common;

import org.paradyne.bean.common.ReportingStructure;
import org.paradyne.model.common.ReportingStructureModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author tarunc
 * @date Jan 15, 2008
 * @description ReportingStructureAction class serves as action for reporting structure form
 * 				to save, update and the reporting structures for various applications
 */
public class ReportingStructureAction extends ParaActionSupport {

	ReportingStructure ReportingStr;
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		ReportingStr = new ReportingStructure();
		ReportingStr.setMenuCode(401);
		// TODO Auto-generated method stub
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			logger.info("in prepare_withLoginProfileDetails");
			String reportType = request.getParameter("structureKey");
			logger.info("------------------------------------------reportType"
					+ reportType);
			ReportingStr.setStructureType(reportType);
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			model.getDefinedStructureNames(ReportingStr);
			model.setDropdownValue(ReportingStr);
			model.terminate();
			// TODO Auto-generated method stub
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return ReportingStr;
	}
	
	/**
	 * @return the ReportingStr
	 */
	public ReportingStructure getReportingStr() {
		return ReportingStr;
	}
	
	/**
	 * @param reportingStr the ReportingStr to set
	 */
	public void setReportingStr(ReportingStructure reportingStr) {
		ReportingStr = reportingStr;
	}
	
	/**
	 * @method saveReportingStructure
	 * @purpose to save record for reporting structure
	 * @return String
	 */
	public String saveReportingStructure(){
		logger.info("inside saveReportingStructure");
		
		/* getting the parameters for approver details
		 */
		String [] approverId    = request.getParameterValues("empIdIterator");      //Approver id
		String [] approverToken = request.getParameterValues("empTokenIterator");	//Approver Token no
		String [] approverName  = request.getParameterValues("empNameIterator");	//Approver Name
		String [] designation   = request.getParameterValues("desgIdIterator");		//Approver Designation
		String [] alternateApproverId   = request.getParameterValues("alternateEmpIdIterator");//Alternate Approver Id
		
		//printing the requested data
		if(approverId != null){
			for (int i = 0; i < approverId.length; i++) {
				logger.info("addEmployee id "+approverId [i]);
				logger.info("addEmployee token "+approverToken[i]);
				logger.info("addEmployee name "+approverName [i]);
				logger.info("addEmployee desg "+designation [i]);
			}	//end of for loop
		}	//end of if
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		
		/*call to model.saveReportingStructure method
		 * to actually save the data
		 */
		boolean result = model.saveReportingStructure(ReportingStr, approverId, approverToken, approverName, designation, alternateApproverId);
		
		if(result){
			addActionMessage(getText("addMessage", "")); //if record saved successfully then show the saved message
			reset();
		}	//end of if
		else{
			addActionMessage("Record can not be saved"); //show record can not be saved on error
			addApprover();
		}	//end of else
		
		model.terminate();
		setStructureType();		//call to setStructureType to set the structure type i.e. Leave, Loan etc
		checkHeader();
		return "success";
	}
	
	/**
	 * @method addApprover
	 * @purpose to add approver for reporting structure in the list
	 * @return String
	 */
	public String addApprover(){
		logger.info("inside addEmployee");
		
		/* getting the parameters for approver details
		 */
		String srNo		    	= ReportingStr.getSrNo();		//srNo
		String [] approverId    = request.getParameterValues("empIdIterator");		//Approver id
		String [] approverToken = request.getParameterValues("empTokenIterator");	//Approver token no
		String [] approverName  = request.getParameterValues("empNameIterator");	//Approver name
		/*String [] designation   = request.getParameterValues("desgNameIterator");	//Approver designation 
		String [] designationId = request.getParameterValues("desgIdIterator");		//Approver designation id
*/		String [] alternateEmpId  = request.getParameterValues("alternateEmpIdIterator");//Alternate Approver Id
		String [] alternateEmpToken  = request.getParameterValues("alternateEmpTokenIterator");//Alternate Approver Token
		String [] alternateEmpName  = request.getParameterValues("alternateEmpNameIterator");//Alternate Approver Name
		
		logger.info("sr no "+srNo);
		logger.info("srNo=========== "+ReportingStr.getSrNo());
		
		//printing the requested data
		if(approverId != null){
			for (int i = 0; i < approverId.length; i++) {
				logger.info("approver id "+approverId [i]);
				logger.info("approver token "+approverToken [i]);
				logger.info("approver name "+approverName [i]);
				logger.info("alternateEmpName "+alternateEmpName[i]);
				logger.info("alternateEmpToken "+alternateEmpToken [i]);
				//logger.info("addEmployee desg "+designation [i]);
				logger.info(" alternateEmpId "+ alternateEmpId[i]);
			}	//end of for loop
		}	//end of if
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		

		/*call to model.setApprover method to actually add the approver details*/
		
		/*model.setApprover(ReportingStr, approverId, approverToken, approverName, designation, designationId,
				alternateEmpId, alternateEmpToken, alternateEmpName);*/
		model.addApprover(ReportingStr, approverId, approverToken, approverName, alternateEmpId, alternateEmpToken, alternateEmpName);
		model.terminate();
		
		/*
		 * reset all the approver related fields to null after adding the details
		 */
		ReportingStr.setEmpId("");
		ReportingStr.setEmpNameAdd("");
		ReportingStr.setEmpTokenAdd("");
		ReportingStr.setDesgId("");
		ReportingStr.setDesgName("");
		ReportingStr.setSrNo("");
		ReportingStr.setAlternateEmpId("");
		ReportingStr.setAlternateEmpName("");
		ReportingStr.setAlternateEmpToken("");
		
		setStructureType();		//call to setStructureType to set the structure type i.e. Leave, Loan etc
		checkHeader();
		return "success";
	}
	
	/**
	 * @method deleteRecord
	 * @purpose to delete the selected record
	 * @return String
	 */
	public String deleteRecord(){
		logger.info("inside delete");
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		
		/*
		 * call to model.deleteRecord to actually delete the record
		 */
		String result = model.deleteRecord(ReportingStr);
		model.terminate();

		if(result.equals("deleted")){
			addActionMessage(getText("delMessage", ""));  //show the record deleted successfully message on success
			reset();
		}	//end of if
		else if(result.equals("error")){
			addActionMessage("Record can not be deleted");	//show record can not be deleted message on error
		}	//end of elseif
		else{
			addActionMessage("There is no record to delete for the selected criteria");	//if there is no record in database then show this message
		}	//end of else
		/*
		 * reset all the approver related fields to null after adding the details
		 */
		ReportingStr.setEmpId("");
		ReportingStr.setEmpNameAdd("");
		ReportingStr.setEmpTokenAdd("");
		ReportingStr.setDesgId("");
		ReportingStr.setDesgName("");
		ReportingStr.setSrNo("");
		ReportingStr.setAlternateEmpId("");
		ReportingStr.setAlternateEmpName("");
		ReportingStr.setAlternateEmpToken("");
		setStructureType();		//call to setStructureType to set the structure type i.e. Leave, Loan etc.
		return "success";
	}
	
	/**
	 * @method deleteApprover
	 * @purpose to delete the selected approver from the list
	 * @return String
	 */
	public String deleteApprover(){
		logger.info("inside delete");
		
		/* getting the parameters for approver details
		 */
		String srNo					= ReportingStr.getSrNo();							//sr no
		String [] approverId    	= request.getParameterValues("empIdIterator");		//Approver id
		String [] approverToken 	= request.getParameterValues("empTokenIterator");	//Approver token no
		String [] approverName  	= request.getParameterValues("empNameIterator");	//Approver name
		String [] designation   	= request.getParameterValues("desgNameIterator");	//Approver Designation
		String [] designationId 	= request.getParameterValues("desgIdIterator");		//Approver Designation id
		String [] alternateEmpId    = request.getParameterValues("alternateEmpIdIterator");	//Alternate Approver id
		String [] alternateEmpToken = request.getParameterValues("alternateEmpTokenIterator");//Alternate Approver token no
		String [] alternateEmpName  = request.getParameterValues("alternateEmpNameIterator");//Alternate Approver name
		
		logger.info("srNo "+srNo);
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		
		/*
		 * call to model.deleteApprover to actually delete the approver details
		 */
		model.deleteApprover(ReportingStr, srNo, approverId, approverToken, approverName, designation, designationId,
								alternateEmpId, alternateEmpToken, alternateEmpName);
		model.terminate();
		
		setStructureType();		//call to setStructureType to set the structure type i.e. Leave, Loan etc.
		/*
		 * reset all the approver related fields to null after adding the details
		 */
		ReportingStr.setEmpId("");
		ReportingStr.setEmpNameAdd("");
		ReportingStr.setEmpTokenAdd("");
		ReportingStr.setDesgId("");
		ReportingStr.setDesgName("");
		ReportingStr.setSrNo("");
		ReportingStr.setAlternateEmpId("");
		ReportingStr.setAlternateEmpName("");
		ReportingStr.setAlternateEmpToken("");
		return "success";
	}
	
	/**
	 * @method reset
	 * @purpose to clear all the form fields
	 * @return String
	 */
	public String reset(){
		ReportingStr.setDeptCode("");
		ReportingStr.setReqDept("");
		ReportingStr.setBrnCode("");
		ReportingStr.setReqBrn("");
		ReportingStr.setEmpCode("");
		ReportingStr.setEmpId("");
		ReportingStr.setEmpName("");
		ReportingStr.setEmpNameAdd("");
		ReportingStr.setDesgId("");
		ReportingStr.setDesgName("");
		ReportingStr.setDesignationName("");
		ReportingStr.setDesignationCode("");
		ReportingStr.setEmpTokenAdd("");
		ReportingStr.setEmpTokenNo("");
		ReportingStr.setAlternateEmpId("");
		ReportingStr.setAlternateEmpName("");
		ReportingStr.setAlternateEmpToken("");
		ReportingStr.setSrNo("");
		
		setStructureType();
		return "success";
	}

	/**
	 * @method report
	 * @purpose to generate the report for the selected record
	 * @return String
	 */
	public String report(){
		logger.info("inside report method");
		
		String reportType = request.getParameter("reportType");
		logger.info("reportType  "+reportType);
		String structureKey =  request.getParameter("structureKey");
		
		if(reportType.equals("S")){
			ReportingStr.setStructureType(ReportingStr.getStructure());
			ReportingStr.setReportingType(ReportingStr.getStructure());
	
			setStructureType();
		}	//end of if
		ReportingStr.setReportingType(structureKey);
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);								//initialize the model context
		boolean result;
		result = model.report(ReportingStr, response);			//call to model.report to select report data
		logger.info("i**************-------------------"+result);
		if(!result){
			addActionMessage("Report can not be generated");
		}	//end of if
		model.terminate();										//terminate the model context
		
		//if(reportType.equals("R")){
			setStructureType();
		//}
		return null;
	}
	
	/**
	 * @method showApproverData
	 * @purpose to retrieve the approver details and display them in tabular format
	 * @return String
	 */
	public String showApproverData(){
		logger.info("inside showTableData");
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		String result = model.showApproverData(ReportingStr);
		model.terminate();
		
		/*if(result.equals("no data")){
			addActionMessage("There is no record to display for the selected criteria");
		}*/
		setStructureType();
		checkHeader();
		return "success";
	}
	
	/**
	 * @method setStructureType
	 * @purpose to set the form type to which reporting structure will be defined
	 */
	public void setStructureType(){
		
		ReportingStructureModel model = null ;
		try {
			model = new ReportingStructureModel();
			model.initiate(context, session);
			String reportingType = ReportingStr.getStructureType();
			logger.info("reporting type is------" + reportingType);
			String structureType = "";
			Object reportingStrForObj[][] = model
					.getDBValueForreportingStructure();
			if (reportingStrForObj != null && reportingStrForObj.length > 0) {
				for (int i = 0; i < reportingStrForObj.length; i++) {
					if (reportingType.equals(String
							.valueOf(reportingStrForObj[i][0]))) {
						structureType = String
								.valueOf(reportingStrForObj[i][1]);

						logger.info("structureType is in action         "
								+ structureType);
						ReportingStr.setLinkType(structureType);
					}
				}
				request.setAttribute("structureType", structureType);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
		
		/*if(reportingType.equals("Leave")){
			logger.info("reporting type is Leave");
			structureType = "Leave";
			ReportingStr.setLinkType("Leave");
		}	//end of if
		else if(reportingType.equals("Requi")){
			logger.info("reporting type is Requisition");
			structureType = "Requisition";
			ReportingStr.setLinkType("Requisition");
		}	//end of elseif
		else if(reportingType.equals("Cash")){
			logger.info("reporting type is Cash");
			structureType = "Cash Voucher";
			ReportingStr.setLinkType("Cash Voucher");
		}	//end of elseif
		else if(reportingType.equals("Train")){
			logger.info("reporting type is Training");
			structureType = "Training";
			ReportingStr.setLinkType("Training");
		}	//end of elseif
		else if(reportingType.equals("Tran")){
			logger.info("reporting type is Transfer");
			structureType = "Transfer";
			ReportingStr.setLinkType("Transfer");
		}	//end of elseif
		else if(reportingType.equals("Sugg")){
			logger.info("reporting type is Suggestion");
			structureType = "Suggestion";
			ReportingStr.setLinkType("Suggestion");
		}	//end of elseif
		else if(reportingType.equals("Help")){
			logger.info("reporting type is Help Desk");
			structureType = "Help Desk";
			ReportingStr.setLinkType("Help Desk");
		}	//end of elseif
		else if(reportingType.equals("LTC")){
			logger.info("reporting type is LTC Advance");
			structureType = "LTC Advance";
			ReportingStr.setLinkType("LTC Advance");
		}	//end of elseif
		else if(reportingType.equals("Festi")){
			logger.info("reporting type is Festival Advance");
			structureType = "Festival Advance";
			ReportingStr.setLinkType("Festival Advance");
		}	//end of elseif
		else if(reportingType.equals("Other")){
			logger.info("reporting type is Other Advance");
			structureType = "Other Advance";
			ReportingStr.setLinkType("Other Advance");
		}	//end of elseif
		else if(reportingType.equals("Deput")){
			logger.info("reporting type is Deputation");
			structureType = "Deputation";
			ReportingStr.setLinkType("Deputation");
		}	//end of elseif
		else if(reportingType.equals("HBA")){
			logger.info("reporting type is HBA");
			structureType = "HBA";
			ReportingStr.setLinkType("HBA");
		}	//end of elseif
		else if(reportingType.equals("GPF")){
			logger.info("reporting type is GPF");
			structureType = "GPF";
			ReportingStr.setLinkType("GPF");
		}	//end of elseif
		else if(reportingType.equals("CTF")){
			logger.info("reporting type is Children Tution Fee");
			structureType = "Children Tution Fee";
			ReportingStr.setLinkType("Children Tution Fee");
		}	//end of elseif
		else if(reportingType.equals("Medi")){
			logger.info("reporting type is Medical Advance");
			structureType = "Medical Advance";
			ReportingStr.setLinkType("Medical Advance");
		}	//end of elseif
		else if(reportingType.equals("TYD")){
			logger.info("reporting type is Travel");
			structureType = "Travel";
			ReportingStr.setLinkType("Travel");
		}	//end of elseif
		else if(reportingType.equals("Appra")){
			logger.info("reporting type is Appraisal");
			structureType = "Appraisal";
			ReportingStr.setLinkType("Appraisal");
		}	//end of elseif
		else if(reportingType.equals("Confere")){
			logger.info("reporting type is Conference");
			structureType = "Conference";
			ReportingStr.setLinkType("Conference");
		}	//end of elseif
		else if(reportingType.equals("Loan")){
			logger.info("reporting type is Loan");
			structureType = "Loan";
			ReportingStr.setLinkType("Loan");
		}	//end of elseif
		else if(reportingType.equals("Asset")){
			logger.info("reporting type is Asset");
			structureType = "Asset";
			ReportingStr.setLinkType("Asset");
		}	//end of elseif
		else if(reportingType.equals("Purchase")){
			logger.info("reporting type is Purchase");
			structureType = "Purchase";
			ReportingStr.setLinkType("Purchase");
		}	//end of elseif
		else if(reportingType.equals("Cash Process")){
			logger.info("reporting type is Cash Process");
			structureType = "Cash Process";
			ReportingStr.setLinkType("Cash Process");
		}	//end of elseif
		else if(reportingType.equals("Recruitment")){
			logger.info("reporting type is Recruitment");
			structureType = "Recruitment";
			ReportingStr.setLinkType("Recruitment");
		}	//end of elseif
		else if(reportingType.equals("ExtraDayBenefit")){
			logger.info("reporting type is Extra Day Benefit");
			structureType = "Extra Day Benefit";
			ReportingStr.setLinkType("Extra Day Benefit");
		}	//end of elseif
		else if(reportingType.equals("Outdoor")){
			logger.info("reporting type is Outdoor Visit");
			structureType = "Outdoor Visit";
			ReportingStr.setLinkType("Outdoor Visit");
		}	//end of elseif
		else if(reportingType.equals("Resign")){
			logger.info("reporting type is Resignation");
			structureType = "Resignation";
			ReportingStr.setLinkType("Resignation");
		}	//end of elseif
*/		
		
	}
	
	/**
	 * @method shuffleColumnsAction
	 * @purpose to change the order of approvers in the list
	 * @return String
	 */
	public String shuffleColumnsAction(){
		String srNo		        	= ReportingStr.getSrNo();	
		String buttonType       	= request.getParameter("type");
		String [] approverId    	= request.getParameterValues("empIdIterator");		//Approver id
		String [] approverToken 	= request.getParameterValues("empTokenIterator");	//Approver token no
		String [] approverName  	= request.getParameterValues("empNameIterator");	//Approver name
		String [] designation   	= request.getParameterValues("desgNameIterator");	//Approver Designation
		String [] designationId	    = request.getParameterValues("desgIdIterator");		//Approver Designation id
		String [] srNoIterator      = request.getParameterValues("srNoIterator");		//Approver Designation id
		String [] alternateEmpId    = request.getParameterValues("alternateEmpIdIterator");	//Alternate Approver id
		String [] alternateEmpToken = request.getParameterValues("alternateEmpTokenIterator");//Alternate Approver Token
		String [] alternateEmpName  = request.getParameterValues("alternateEmpNameIterator");//Alternate Approver Name
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		
		model.shuffleColumns(ReportingStr, srNo, approverId, approverToken, approverName, designation, 
					designationId, srNoIterator, buttonType, alternateEmpId, alternateEmpToken, alternateEmpName);
		model.terminate();
		
		setStructureType();		//call to setStructureType to set the structure type i.e. Leave, Loan etc.
		
		logger.info("sr no "+srNo+" type "+buttonType+" rowNum "+ReportingStr.getRowNum()+" srNoIterator "+srNoIterator);
		return "success";
	}
	
	/**
	 * @method forwardToMainPage
	 * @purpose to forward the execution to the main page
	 * @return String
	 */
	public String forwardToMainPage(){
		ReportingStr.setStructureType(ReportingStr.getStructureFor());
		ReportingStr.setReportingType(ReportingStr.getStructureFor());
		setStructureType();
		checkHeader();
		return "success";
	}
	
	/**
	 * @method forwardToDefaultPage
	 * @purpose to forward the execution to the middle page
	 * @return String
	 */
	public String forwardToDefaultPage(){
		ReportingStr.setStructureType(ReportingStr.getStructure());
		ReportingStr.setReportingType(ReportingStr.getStructure());

		setStructureType();
		checkHeader();
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context, session);
		String applTypeQuery = " SELECT REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE " 
			+ " WHERE REPORTING_APPL_TYPE_ABBREV = '"+ReportingStr.getStructureType()+"'";
		Object[][] applTypeCode = model.getSqlModel().getSingleResult(applTypeQuery);
		if(applTypeCode != null && applTypeCode.length > 0)
			model.setManagerHierarchies(ReportingStr, applTypeCode);
		
		return "defaultPage";
	}
	
	/**
	 * @method goToDefaultPage
	 * @purpose to forward the execution to the middle page returning from the last page
	 * @return String
	 */
	public String goToDefaultPage(){
		setStructureType();
		checkHeader();
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context, session);
		String applTypeQuery = " SELECT REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE " 
			+ " WHERE REPORTING_APPL_TYPE_ABBREV = '"+ReportingStr.getStructureType()+"'";
		Object[][] applTypeCode = model.getSqlModel().getSingleResult(applTypeQuery);
		if(applTypeCode != null && applTypeCode.length > 0)
			model.setManagerHierarchies(ReportingStr, applTypeCode);
		model.terminate();
		return "defaultPage";
	}
	
	/**
	 * @method goToStartingPage
	 * @purpose to forward the execution to the main page returning from the last page
	 * @return String
	 */
	public String goToStartingPage(){
		return "mainPage";
	}
	
	/**
	 * @method checkHeader
	 * @purpose to set the headers of the pages while going from one page to another
	 * @return String
	 */
	public String checkHeader(){
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context, session);
		model.checkDefault(ReportingStr);
		model.terminate();
		return "success";
	}
	
	public String saveManagerHierarchy(){
		try {
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			
			String structureType = request.getParameter("structureKey");
			logger.info("structureType inside saveManagerHierarchy ====="+structureType);
			try {
				Object reportingStrForObj[][] = model.getDBValueForreportingStructure();
				if (reportingStrForObj != null && reportingStrForObj.length > 0) {
					for (int i = 0; i < reportingStrForObj.length; i++) {
						if (structureType != null) {
							if (structureType.trim().equals(String
									.valueOf(reportingStrForObj[i][1]))) {
								structureType = String
										.valueOf(reportingStrForObj[i][0]);
							}
						}
					}

				}
				ReportingStr.setStructureType(structureType);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			ReportingStr.setReportingType(ReportingStr.getStructureFor());
			
			String updateHdrTable = " UPDATE HRMS_REPORTING_STRUCTUREHDR SET REPORTINGHDR_SAME_AS = 'null' , "
				+ " REPORTINGHDR_ISDEFAULT = 'N' where REPORTINGHDR_TYPE = '"+structureType+"'";
			model.getSqlModel().singleExecute(updateHdrTable);
			
			String fromEmpID = ReportingStr.getFromEmpIdValues();
			String toEmpID   = ReportingStr.getToEmpIdValues();
			logger.info("fromEmpID====== "+fromEmpID);
			logger.info("toEmpID========  "+toEmpID);
			
			String[] splitFromEmp = ReportingStr.getFromEmpIdValues().split(",");
			String[] splitToEmp   = ReportingStr.getToEmpIdValues().split(",");
			
			//printing the requested data
			if(splitFromEmp != null){
				for (int i = 0; i < splitFromEmp.length; i++) {
					logger.info("splitFromEmp======"+splitFromEmp[i]);
					logger.info("splitToEmp====="+splitToEmp[i]);
				}	//end of for loop
			}	//end of if
			
			String applTypeQuery = " SELECT REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE " 
				+ " WHERE REPORTING_APPL_TYPE_ABBREV = '"+structureType+"'";
			Object[][] applTypeCode = model.getSqlModel().getSingleResult(applTypeQuery);
			
			boolean result = model.saveManagerHierarchy(ReportingStr,
					splitFromEmp, splitToEmp, structureType, applTypeCode);
			if(result){
				String defaultType = model.decodeStructure(ReportingStr, structureType);
				logger.info("defaultType===="+defaultType);
				addActionMessage("The reporting hierarchy of " + defaultType
						+ " is set as per Reporting Manager under Official Details.");
			}else{
				addActionMessage("Error while setting hierarchy ");
			}
			setStructureType();
			
			model.setManagerHierarchies(ReportingStr, applTypeCode);
			
			model.terminate();
			return "defaultPage";

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @method defaultFunction
	 * @purpose to check whether the structure for this particular 
	 * 			type is default for all or not
	 * @return String
	 */
	public String defaultFunction() {
		try {
			logger.info("inside defaultFunction");
			
			ReportingStr.setStructureType(ReportingStr.getStructureFor());
			ReportingStr.setReportingType(ReportingStr.getStructureFor());
			
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			String msg = model.defaultAll(ReportingStr);
			addActionMessage(msg);
			setStructureType();
			checkHeader();
			model.terminate();
			return "defaultPage";

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @method sameAsFunction
	 * @purpose to check whether the structure for this particular 
	 * 			type is same as any other type
	 * @return String
	 */
	public String sameAsFunction() {
		try {
			logger.info("inside sameAsFunction");
			
			ReportingStr.setStructureType(ReportingStr.getStructureFor());
			ReportingStr.setReportingType(ReportingStr.getStructureFor());
			
			ReportingStructureModel model = new ReportingStructureModel();
			model.initiate(context, session);
			String msg = model.sameAs(ReportingStr);
			addActionMessage(msg);
			setStructureType();
			checkHeader();
			model.terminate();
			return "defaultPage";

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @Method Name : rollBack
	 * @Purpose     : to roll back the records or to remove either 
	 * 					as default or same as 
	 * @param	    : none
	 * @return      : defaultPage
	**/
	public String rollBack() {
		logger.info("in side rollBack");
		
		ReportingStr.setStructureType(ReportingStr.getStructureFor());
		ReportingStr.setReportingType(ReportingStr.getStructureFor());
		
		ReportingStructureModel model = new ReportingStructureModel();
		
		model.initiate(context, session);
		String msg = model.rollBack(ReportingStr);
		addActionMessage(msg);
		setStructureType();
		model.terminate();
		return "defaultPage";
	}

	/**
	 * @method f9Department
	 * @purpose to select the department name
	 * @return String
	 * @throws Exception
	 */
	public String f9Department() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT "
						+"ORDER BY DEPT_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 
		 */ 
		String [] headers = {"Department Code", getMessage("department")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "70"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"ReportingStr.deptCode", "ReportingStr.reqDept"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReportingStr_showApproverData.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * @method f9Branch
	 * @purpose to select the branch name
	 * @return String
	 * @throws Exception
	 */
	public String f9Branch() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER "
						+"ORDER BY CENTER_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {"Branch Code", getMessage("branch")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "70"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"ReportingStr.brnCode", "ReportingStr.reqBrn"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReportingStr_showApproverData.action";
		//ReportingStr_showTableData.action
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * @method f9Selectemp
	 * @purpose to select the employee name
	 * @return String
	 * @throws Exception
	 */
	public String f9Selectemp() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
						+"FROM HRMS_EMP_OFFC "
						+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
						+"WHERE EMP_STATUS = 'S' ";
		
						if (ReportingStr.getUserProfileDivision() != null
								&& ReportingStr.getUserProfileDivision().length() > 0) {
							query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
									+ ReportingStr.getUserProfileDivision() + ")";
						}
						query +="ORDER BY EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {"Emlpoyee Id", getMessage("reporting.employee.name")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "70"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"ReportingStr.empTokenNo", "ReportingStr.empName", "ReportingStr.empCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReportingStr_showApproverData.action";
		//ReportingStr_showTableData.action
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * @method f9SelectempAdd
	 * @purpose to select the approver name
	 * @return String
	 * @throws Exception
	 */
	public String f9SelectempAdd() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME, " 
					   +"EMP_ID " 
					   +"FROM HRMS_EMP_OFFC "
					   +"WHERE EMP_STATUS = 'S' ";
					   
		
		String empId   = ReportingStr.getEmpCode();
		
		if(empId != null && !empId.equals("")){
			query   += "AND EMP_ID != "+empId;
		}
		
		query += " ORDER BY EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("reporting.appr.id"), getMessage("reporting.appr.name")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "70"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"ReportingStr.empTokenAdd", "ReportingStr.empNameAdd",  "ReportingStr.empId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * @method f9SelectAlternateApprover
	 * @purpose to select the alternate approver name
	 * @return String
	 * @throws Exception
	 */
	public String f9SelectAlternateApprover() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)NAME, " 
					   +"EMP_ID " 
					   +"FROM HRMS_EMP_OFFC "
					   +"WHERE EMP_STATUS = 'S' ";
			   
		String empId   = ReportingStr.getEmpCode();
		
		if(empId != null && !empId.equals("")){
			query   += "AND EMP_ID != "+empId;
		}
		
		query += " ORDER BY EMP_ID";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("reporting.alter.appr.id"), getMessage("reporting.alter.appr.name")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "70"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"alternateEmpToken", "alternateEmpName",  "alternateEmpId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	/**
	 * @method f9Designation
	 * @purpose to select the designation name
	 * @return String
	 * @throws Exception
	 */
	public String f9Designation() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT RANK_ID, RANK_NAME FROM HRMS_RANK ORDER BY RANK_ID";
		
		String empId   = ReportingStr.getEmpCode();
		logger.info("emp id  "+empId );
		
		/*if(empId.equals("")){
			query   = "SELECT RANK_ID, RANK_NAME FROM HRMS_RANK "
					  +"ORDER BY RANK_ID";
		}else{
			query   = "SELECT RANK_ID, RANK_NAME FROM HRMS_RANK "
					  +"ORDER BY RANK_ID";
		}*/
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {"Designation Code", getMessage("designation")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "70"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"ReportingStr.designationCode", "ReportingStr.designationName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReportingStr_showApproverData.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String gotoExceptionPage() throws Exception {
		logger.info("structure in new page========"+request.getParameter("hiddenStructure"));
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		String structureType = "";
		try {
			Object reportingStrForObj[][] = model.getDBValueForreportingStructure();
			if (reportingStrForObj != null && reportingStrForObj.length > 0) {
				for (int i = 0; i < reportingStrForObj.length; i++) {
					if (request.getParameter("hiddenStructure") != null) {
						if (request.getParameter("hiddenStructure").trim().equals(String
								.valueOf(reportingStrForObj[i][1]))) {
							structureType = String
									.valueOf(reportingStrForObj[i][0]);
						}
					}
				}

			}
			if(request.getParameter("hiddenStructure")!=null)
				ReportingStr.setHiddenStrucutre(request.getParameter("hiddenStructure"));
			ReportingStr.setStructureType(structureType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String applTypeQuery = " SELECT REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE " 
			+ " WHERE REPORTING_APPL_TYPE_ABBREV = '"+structureType+"'";
		Object[][] applTypeCode = model.getSqlModel().getSingleResult(applTypeQuery);
		model.setManagerHierarchies(ReportingStr, applTypeCode);
		model.terminate();
		setStructureType();
		//checkHeader();
		return "exceptionPage";
	}
	
	public String f9fromEmployee() throws Exception{
		
		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
						+"FROM HRMS_EMP_OFFC "
						+"WHERE EMP_STATUS = 'S' ";
		
		if (ReportingStr.getUserProfileDivision() != null
				&& ReportingStr.getUserProfileDivision().length() > 0) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
					+ ReportingStr.getUserProfileDivision() + ")";
		}
		query +="ORDER BY EMP_ID";
		
		String [] headers = {getMessage("employee.id"), getMessage("employee")};
		
		String [] headerWidth = {"30", "70"};
		
		String [] fieldNames = {"fromEmpToken", "fromEmpName", "fromEmpCode"};
		
		int [] columnIndex = {0, 1, 2};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9toEmployee() throws Exception{
		
		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
						+"FROM HRMS_EMP_OFFC "
						+"WHERE EMP_STATUS = 'S' ";
		
		if (ReportingStr.getUserProfileDivision() != null
				&& ReportingStr.getUserProfileDivision().length() > 0) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
					+ ReportingStr.getUserProfileDivision() + ")";
		}
		
		String empId   = ReportingStr.getFromEmpCode();
		
		if(empId != null && !empId.equals("")){
			query   += "AND EMP_ID != "+empId;
		}
		query +="ORDER BY EMP_ID";
		
		String [] headers = {getMessage("employee.id"), getMessage("employee")};
		
		String [] headerWidth = {"30", "70"};
		
		String [] fieldNames = {"toEmpToken", "toEmpName", "toEmpCode"};
		
		int [] columnIndex = {0, 1, 2};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String addExceptions() throws Exception {
		
		String srNo		    	= ReportingStr.getExcepSrNo();		//srNo
		String [] fromEmpIdIt    = request.getParameterValues("fromEmpIdIt");		
		String [] fromEmpTokenIt = request.getParameterValues("fromEmpTokenIt");	
		String [] fromEmpNameIt  = request.getParameterValues("fromEmpNameIt");	//
		String [] toEmpIdIt  = request.getParameterValues("toEmpIdIt");
		String [] toEmpTokenIt  = request.getParameterValues("toEmpTokenIt");
		String [] toEmpNameIt  = request.getParameterValues("toEmpNameIt");
		
		logger.info("srNo========="+srNo);
		//printing the requested data
		if(fromEmpIdIt != null){
			for (int i = 0; i < fromEmpIdIt.length; i++) {
				logger.info("fromEmpIdIt id "+fromEmpIdIt [i]);
				logger.info("fromEmpTokenIt token "+fromEmpTokenIt [i]);
				logger.info("fromEmpNameIt name "+fromEmpNameIt [i]);
				logger.info("toEmpNameIt "+toEmpNameIt[i]);
				logger.info("toEmpTokenIt "+toEmpTokenIt [i]);
				logger.info(" toEmpIdIt "+ toEmpIdIt[i]);
			}	//end of for loop
		}	//end of if
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		model.addExceptionEmployees(ReportingStr, fromEmpIdIt, fromEmpTokenIt,
				fromEmpNameIt, toEmpIdIt, toEmpTokenIt, toEmpNameIt);
		
		ReportingStr.setFromEmpCode("");
		ReportingStr.setFromEmpToken("");
		ReportingStr.setFromEmpName("");
		ReportingStr.setToEmpCode("");
		ReportingStr.setToEmpToken("");
		ReportingStr.setToEmpName("");
		ReportingStr.setExcepSrNo("");
		
		
		String sameAsStructure = "";
		logger.info("structure type in add======"+request.getParameter("hiddenStructure"));
		try {
			Object reportingStrForObj[][] = model.getDBValueForreportingStructure();
			if (reportingStrForObj != null && reportingStrForObj.length > 0) {
				for (int i = 0; i < reportingStrForObj.length; i++) {
					if (request.getParameter("hiddenStructure") != null) {
						if (request.getParameter("hiddenStructure").trim().equals(String
								.valueOf(reportingStrForObj[i][1]))) {
							sameAsStructure = String
									.valueOf(reportingStrForObj[i][0]);
						}
					}
				}

			}
			if(request.getParameter("hiddenStructure")!=null)
				ReportingStr.setHiddenStrucutre(request.getParameter("hiddenStructure"));
			ReportingStr.setStructureType(sameAsStructure);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setStructureType();		//call to setStructureType to set the structure type i.e. Leave, Loan etc
		//checkHeader();
		/*String applTypeQuery = " SELECT REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE " 
			+ " WHERE REPORTING_APPL_TYPE_ABBREV = '"+ReportingStr.getStructureType()+"'";
		Object[][] applTypeCode = model.getSqlModel().getSingleResult(applTypeQuery);
		model.setManagerHierarchies(ReportingStr, applTypeCode);*/
		
		model.terminate();
		return "exceptionPage";
	}
	
	public String deleteExceptionEmp(){
		logger.info("inside delete");
		
		/* getting the parameters for approver details
		 */
		String srNo		    	= ReportingStr.getExcepSrNo();		//srNo
		String [] fromEmpIdIt    = request.getParameterValues("fromEmpIdIt");		
		String [] fromEmpTokenIt = request.getParameterValues("fromEmpTokenIt");	
		String [] fromEmpNameIt  = request.getParameterValues("fromEmpNameIt");	//
		String [] toEmpIdIt  = request.getParameterValues("toEmpIdIt");
		String [] toEmpTokenIt  = request.getParameterValues("toEmpTokenIt");
		String [] toEmpNameIt  = request.getParameterValues("toEmpNameIt");
		
		logger.info("srNo "+srNo);
		
		ReportingStructureModel model = new ReportingStructureModel();
		model.initiate(context,session);
		
		/*
		 * call to model.deleteApprover to actually delete the approver details
		 */
		model.deleteEmployeeExceptions(ReportingStr, srNo, fromEmpIdIt, fromEmpTokenIt,
				fromEmpNameIt, toEmpIdIt, toEmpTokenIt, toEmpNameIt);
		model.terminate();
		
		String sameAsStructure = "";
		logger.info("structure type in add======"+request.getParameter("hiddenStructure"));
		try {
			Object reportingStrForObj[][] = model.getDBValueForreportingStructure();
			if (reportingStrForObj != null && reportingStrForObj.length > 0) {
				for (int i = 0; i < reportingStrForObj.length; i++) {
					if (request.getParameter("hiddenStructure") != null) {
						if (request.getParameter("hiddenStructure").trim().equals(String
								.valueOf(reportingStrForObj[i][1]))) {
							sameAsStructure = String
									.valueOf(reportingStrForObj[i][0]);
						}
					}
				}

			}
			if(request.getParameter("hiddenStructure")!=null)
				ReportingStr.setHiddenStrucutre(request.getParameter("hiddenStructure"));
			ReportingStr.setStructureType(sameAsStructure);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		setStructureType();
		//checkHeader();		//call to setStructureType to set the structure type i.e. Leave, Loan etc.
		/*String applTypeQuery = " SELECT REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE " 
			+ " WHERE REPORTING_APPL_TYPE_ABBREV = '"+ReportingStr.getStructureType()+"'";
		Object[][] applTypeCode = model.getSqlModel().getSingleResult(applTypeQuery);
		model.setManagerHierarchies(ReportingStr, applTypeCode);*/
		/*
		 * reset all the approver related fields to null after adding the details
		 */
		ReportingStr.setFromEmpCode("");
		ReportingStr.setFromEmpToken("");
		ReportingStr.setFromEmpName("");
		ReportingStr.setToEmpCode("");
		ReportingStr.setToEmpToken("");
		ReportingStr.setToEmpName("");
		ReportingStr.setExcepSrNo("");
		return "exceptionPage";
	}
}
