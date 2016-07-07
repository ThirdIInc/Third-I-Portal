package org.struts.action.DataMigration;

import org.paradyne.bean.DataMigration.PersonalPartialUpload;
import org.paradyne.model.DataMigration.PersonalPartialUploadModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Prakash Shetkar
 * Date 29 April 2010
 *
 */
public class PersonalPartialUploadAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PersonalPartialUploadAction.class);

	PersonalPartialUpload persPartialUpload;
	@Override
	public void prepare_local() throws Exception {
		persPartialUpload =new PersonalPartialUpload();
		persPartialUpload.setMenuCode(1033);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return persPartialUpload;
	}
	
	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		persPartialUpload.setDataPath(dataPath);
		return SUCCESS;
	}
	
	public String f9desgAction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DISTINCT RANK_NAME,RANK_ID FROM  HRMS_RANK  "		  			 
		       	      +" ORDER BY  RANK_NAME ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		*/
		String[] headers={getMessage("designation")};
		
		String[] headerWidth={"100"};
		
		String[] fieldNames={"desgName","desgCode"};
		 
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		String submitToMethod="";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String f9divAction() throws Exception {
		String query = " SELECT DISTINCT DIV_NAME,DIV_ID FROM  HRMS_DIVISION "	;
		
			if(persPartialUpload.getUserProfileDivision() !=null && persPartialUpload.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+persPartialUpload.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_NAME ";
		String[] headers={getMessage("division")};
		
		String[] headerWidth={"100"};
		
		
		String[] fieldNames={"divName","divCode"};
		
		 
		int[] columnIndex={0,1};
		
		
		String submitFlag = "false";
		
		String submitToMethod="";
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
	public String f9empTypeAction() throws Exception {
		String query = " SELECT  TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE";

		String[] headers = { getMessage("employee.type") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "empType","empTypeCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9deptAction() throws Exception {
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT "
						+ " ORDER BY  DEPT_NAME ";
		
		String[] headers={getMessage("department")};
		
		String[] headerWidth={"100"};
		
		
		String[] fieldNames={"deptName","deptCode"};
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		String submitToMethod="";
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	/*
	 * Following function is called in jsp page to show the branch code and name
	 */
	public String f9branchAction() throws Exception {
		
		String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
						+ " ORDER BY CENTER_NAME ";
		
		String[] headers={getMessage("branch")};
		
		String[] headerWidth={"100"};
		
		String[] fieldNames={"branchName","branchCode"};
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		String submitToMethod="";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9gradeAction() throws Exception {
		String query = " SELECT CADRE_NAME,CADRE_ID FROM HRMS_CADRE "
						+ " ORDER BY CADRE_NAME ";
		
		String[] headers={getMessage("grade")};
		
		String[] headerWidth={"100"};
		
		String[] fieldNames={"gradeName","gradeCode"};
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		String submitToMethod="";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String downloadTemplate(){
		PersonalPartialUploadModel model=new PersonalPartialUploadModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path");
		String application="Personal and Account Details";
		String rangeValue = request.getParameter("rangeValue");	
		model.downloadTemplate(persPartialUpload,request,response,poolName,dataPath,application,rangeValue);
		model.terminate();
		return null;
	}
	
	public String uploadTemplate(){
		PersonalPartialUploadModel model=new PersonalPartialUploadModel();
		model.initiate(context, session);
		String dataPath = persPartialUpload.getDataPath();
		dataPath+=persPartialUpload.getUploadFileName();
		model.uploaddPersonalTemplate(dataPath,persPartialUpload);
		model.terminate();
		return SUCCESS;
	}
	
	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");
			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	
	public String reset() {
		try {
			persPartialUpload.setDivName("");
			persPartialUpload.setDivCode("");
			persPartialUpload.setBranchName("");
			persPartialUpload.setBranchCode("");
			persPartialUpload.setDeptName("");
			persPartialUpload.setDeptCode("");
			persPartialUpload.setDesgName("");
			persPartialUpload.setDesgCode("");
			persPartialUpload.setEmpType("");
			persPartialUpload.setEmpTypeCode("");
			persPartialUpload.setGradeName("");
			persPartialUpload.setGradeCode("");
			persPartialUpload.setBloodGroupChk("");
			persPartialUpload.setWeightChk("");
			persPartialUpload.setHeightChk("");
			persPartialUpload.setReligionChk("");
			persPartialUpload.setNationalityChk("");
			persPartialUpload.setHobbiesChk("");
			persPartialUpload.setCasteCatChk("");
			persPartialUpload.setCasteChk("");
			persPartialUpload.setSubCasteChk("");
			persPartialUpload.setIsHandicapChk("");
			persPartialUpload.setHandicapDescChk("");
			persPartialUpload.setIdMarkChk("");
			persPartialUpload.setPassportNoChk("");
			persPartialUpload.setPassExpDateChk("");
			persPartialUpload.setHomeTownChk("");
			persPartialUpload.setMaritalStatusChk("");
			persPartialUpload.setMarriageDateChk("");
			persPartialUpload.setPfNoChk("");
			persPartialUpload.setPanNoChk("");
			persPartialUpload.setEsicNoChk("");
			persPartialUpload.setGratuityNoChk("");
			persPartialUpload.setSalACNoChk("");
			persPartialUpload.setSalBankChk("");
			persPartialUpload.setPensionACNoChk("");
			persPartialUpload.setPensionBankChk("");
			persPartialUpload.setPensionableChk("");
			persPartialUpload.setReimbAcNoChk("");
			persPartialUpload.setReimbBankChk("");
			persPartialUpload.setPfTrustApplChk("");
			persPartialUpload.setEpfApplChk("");
			persPartialUpload.setGpfApplChk("");
			persPartialUpload.setVpfApplChk("");
			persPartialUpload.setCostCenterChk("");
			persPartialUpload.setAccountCatgChk("");
			persPartialUpload.setSubCostCenterChk("");
			persPartialUpload.setPayModeChk("");
			
			persPartialUpload.setUploadFileName("");
			persPartialUpload.setStatus("");
			persPartialUpload.setNote("");
			persPartialUpload.setFileUploaded(false);
			persPartialUpload.setDataExists(false);
			
		} catch(Exception e) {
			logger.error("Exception in reset:" + e);
		}
		return SUCCESS;
	}
	
	public String getURL(){
		PersonalPartialUploadModel model = new PersonalPartialUploadModel();
		model.initiate(context, session);
		boolean result = model.generateUrlList(request, response, persPartialUpload);
		if(!result)
			addActionMessage("No records found for selected search criteria.");
		model.terminate();
		return SUCCESS;
	}
}
