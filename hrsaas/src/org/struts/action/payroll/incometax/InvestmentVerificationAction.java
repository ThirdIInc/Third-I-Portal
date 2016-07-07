package org.struts.action.payroll.incometax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.InvestmentVerification;
import org.paradyne.model.payroll.incometax.InvestmentVerificationModel;
import org.struts.lib.ParaActionSupport;

public class InvestmentVerificationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InvestmentVerificationAction.class);

	InvestmentVerification invbean;
	
	public final void prepare_local() throws Exception {
		invbean=new InvestmentVerification();
		invbean.setMenuCode(909);

	}

	public final Object getModel() {
		return invbean;
	}
	
	/* This method displays the default page mapped.
	 * input
	 */
	public final String input() {
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/upload/" + poolName + "/investment/";
			invbean.setDataPath(dataPath);
			fetchDefaultDate();
			if(invbean.isSettlementFlag()){
				invbean.setSettlementApplnStatus(request.getParameter("applnStatus"));
				submit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/**Following function is called to show the division name and division id in the jsp.
	 * 
	 */
	public final String f9div() throws Exception {

		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' '),'','','' FROM HRMS_DIVISION ";
		if(invbean.getUserProfileDivision() !=null && invbean.getUserProfileDivision().length()>0){
			query+= " WHERE DIV_ID IN ("+invbean.getUserProfileDivision() +")"; 
		}
		
		query+= " ORDER BY DIV_ID ";
			
		
		String[] headers = {getMessage("division.code"),getMessage("division") };

		String[] headerWidth = { "20","80" };
        
		String[] fieldNames = { "divId","divName","empName","empToken","empId"};
		
		int[] columnIndex = { 0 ,1,2,3,4};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	/**Following function is called to show the department name and department id in the jsp.
	 * 
	 */
	public final String f9dept() throws Exception {
		
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' '),'','',''  FROM HRMS_DEPT ";
		
		if(invbean.getDeptId()!=null && !invbean.getDeptId().equals("")) {
			query +=" where  DEPT_ID not in( "+invbean.getDeptId()+" )";
		}
			
		query+= " ORDER BY DEPT_ID ";
				
		logger.info("query....!"+query);
		String[] headers = {getMessage("department.code"),getMessage("department") };

		String[] headerWidth = { "20","80" };
        String[] fieldNames = { "deptId","deptName","empName","empToken","empId"};
        
		int[] columnIndex = { 0 ,1,2,3,4};

		String submitFlag = "false";

		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	/*
	 * Following function is called to show the branch name and branch id in the jsp 
	 */
	
	public final String f9brn() throws Exception {

		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' '),'','','' FROM HRMS_CENTER " ;
		
		if(invbean.getBranchId()!=null && !invbean.getBranchId().equals("")){	
			query +=" WHERE  CENTER_ID NOT IN( "+invbean.getBranchId()+" )";
			logger.info("when condition is true...!"+invbean.getBranchId());
		}
			
		query+= " ORDER BY CENTER_ID ";
				
		logger.info("query....!"+query);
		
		String[] headers = {getMessage("branch.code"),getMessage("branch") };
		
		String[] headerWidth = { "20","80" };
        
		String[] fieldNames = { "branchId","branchName","empName","empToken","empId"}; 
		
		int[] columnIndex = { 0 ,1,2,3,4}; 

		String submitFlag = "false";

		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/** Following function is called to show the employee name and employee id in the jsp 
	 * 
	 */
	
	public final String f9employee(){ 
	 
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) AS NAME, HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC";
			
		query += getprofileQuery(invbean);
		
		query +=" ORDER BY HRMS_EMP_OFFC.EMP_ID";	
		
		String []headers ={getMessage("employee.id"),getMessage("employee")};
	
		String []headerwidth={"20","80"};
	
		String []fieldNames={"empToken", "empName", "empId"};
	
		int []columnIndex={0, 1, 2};
	
		String submitFlage ="true";
	
		String submitToMethod = "InvestmentVerification_submit.action";
	
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
	
		return "f9page";
	}
	
	/**Following function is called to show the investment name and investment id in the jsp. 
	 * 
	 */
	
	public final String f9section(){
		try {
			String query = " SELECT HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_CODE FROM HRMS_TAX_INVESTMENT  ";
					/*+ " HRMS_TAX_INVESTMENT.FROM_YEAR ="
					+ invbean.getFromYear()
					+ " AND HRMS_TAX_INVESTMENT.TO_YEAR ="
					+ invbean.getToYear();*/
			String[] headers = { "Investment Name" };
			String[] headerwidth = { "100" };
			String[] fieldNames = { "investmentName", "investmentCode" };
			String submitFlag = "false";
			String submitToMethod = " ";
			int[] columnIndex = { 0, 1 };
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/**Following function is called to show the paybill name and paybill id in the jsp.
	 * 
	 */
	
	public final String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query +=" ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String[] headers = { "Paybill Code", getMessage("pay.bill")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "paybillId", "paybillName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/**Following function displays the investment details as per the filter selected.
	 * 
	 */
	
	public final String submit() {
		try {
			InvestmentVerificationModel model = new InvestmentVerificationModel();
			model.initiate(context, session);
			handleFlags();
			model.investmentDetails(invbean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**Following function saves the verified investment details.
	 * 
	 */
	
	public final String saveVerifiedInvestments() {
		boolean flag = false;
		try {
			InvestmentVerificationModel model = new InvestmentVerificationModel();
			model.initiate(context, session);
			
			/*Checking if the verififcation for the year is already done*/
			String verifiedQuery = "SELECT NVL(TDS_LOCK_FLAG,'N') FROM HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM="+invbean.getFromYear();
			Object[][] verfiedObj = model.getSqlModel().getSingleResult(verifiedQuery);
			
			if(verfiedObj != null && verfiedObj.length > 0){
				if("N".equals(String.valueOf(verfiedObj[0][0]))){
					/*Exemptions variables to save*/
					Object[] exemptionsInvestmentIdItt = request.getParameterValues("exemptionsInvestmentIdItt");
					Object[] exemptionsVerifiedItt = request.getParameterValues("exemptionsVerifiedItt");
					Object[] exemptionsVerifiedAmountItt = request.getParameterValues("exemptionsVerifiedAmountItt");
					Object[] exemptionsEmpIdItt = request.getParameterValues("exemptionsEmpIdItt");
					/*Other variables to save*/
					Object[] otherInvestmentIdItt = request.getParameterValues("otherInvestmentIdItt");
					Object[] otherVerifiedItt = request.getParameterValues("otherVerifiedItt");
					Object[] otherVerifiedAmountItt = request.getParameterValues("otherVerifiedAmountItt");
					Object[] otherEmpIdItt = request.getParameterValues("otherEmpIdItt");
					/*DeductionsVIA variables to save*/
					Object[] deductionsVIAInvestmentIdItt = request.getParameterValues("deductionsVIAInvestmentIdItt");
					Object[] deductionsVIAVerifiedItt = request.getParameterValues("deductionsVIAVerifiedItt");
					Object[] deductionsVIAVerifiedAmountItt = request.getParameterValues("deductionsVIAVerifiedAmountItt");
					Object[] deductionsVIAEmpIdItt = request.getParameterValues("deductionsVIAEmpIdItt");
					/*DeductionsVI variables to save*/
					Object[] deductionsVIInvestmentIdItt = request.getParameterValues("deductionsVIInvestmentIdItt");
					Object[] deductionsVIVerifiedItt = request.getParameterValues("deductionsVIVerifiedItt");
					Object[] deductionsVIVerifiedAmountItt = request.getParameterValues("deductionsVIVerifiedAmountItt");
					Object[] deductionsVIEmpIdItt = request.getParameterValues("deductionsVIEmpIdItt");
					/*Donations variables to save*/
					Object[] donationIdItt = request.getParameterValues("donationIdItt");
					Object[] donationVerifiedItt = request.getParameterValues("donationVerifiedItt");
					Object[] donationVerifiedAmountItt = request.getParameterValues("donationVerifiedAmountItt");
					Object[] donationEmpIdItt = request.getParameterValues("donationEmpIdItt");
					
					Object [][] finalInvestmentObject = null;
					
					int updateObjectLength = 0;
					if(invbean.getExemptionsListViewFlag()){
						if(exemptionsInvestmentIdItt != null && exemptionsInvestmentIdItt.length > 0){
							updateObjectLength+= exemptionsInvestmentIdItt.length;
						}
					}
					if(invbean.getOtherListViewFlag()){
						if(otherInvestmentIdItt != null && otherInvestmentIdItt.length > 0){
							updateObjectLength+= otherInvestmentIdItt.length;
						}
					}
					if(invbean.getDeductionsVIAListViewFlag()){
						if(deductionsVIAInvestmentIdItt != null && deductionsVIAInvestmentIdItt.length > 0){
							updateObjectLength+= deductionsVIAInvestmentIdItt.length;
						}
					}
					if(invbean.getDeductionsVIListViewFlag()){
						if(deductionsVIInvestmentIdItt != null && deductionsVIInvestmentIdItt.length > 0){
							updateObjectLength+= deductionsVIInvestmentIdItt.length;
						}
					}
					
					finalInvestmentObject = new Object[updateObjectLength][4];
					
					for (int i = 0; i < finalInvestmentObject.length; ) {
						if(exemptionsInvestmentIdItt != null && exemptionsInvestmentIdItt.length > 0){
							for (int j= 0; j < exemptionsInvestmentIdItt.length; j++) {
								finalInvestmentObject[i][0] = String.valueOf(exemptionsVerifiedItt[j]);
								finalInvestmentObject[i][1] = String.valueOf(exemptionsVerifiedAmountItt[j]);
								finalInvestmentObject[i][2] = String.valueOf(exemptionsInvestmentIdItt[j]);
								finalInvestmentObject[i][3] = String.valueOf(exemptionsEmpIdItt[j]);
								i++;
							}
						}
						if(otherInvestmentIdItt != null && otherInvestmentIdItt.length > 0){
							for (int k = 0; k < otherInvestmentIdItt.length; k++) {
								finalInvestmentObject[i][0] = String.valueOf(otherVerifiedItt[k]);
								finalInvestmentObject[i][1] = String.valueOf(otherVerifiedAmountItt[k]);
								finalInvestmentObject[i][2] = String.valueOf(otherInvestmentIdItt[k]);
								finalInvestmentObject[i][3] = String.valueOf(otherEmpIdItt[k]);
								i++;
							}
						}
						if(deductionsVIAInvestmentIdItt != null && deductionsVIAInvestmentIdItt.length > 0){
							for (int l = 0; l < deductionsVIAInvestmentIdItt.length; l++) {
								finalInvestmentObject[i][0] = String.valueOf(deductionsVIAVerifiedItt[l]);
								finalInvestmentObject[i][1] = String.valueOf(deductionsVIAVerifiedAmountItt[l]);
								finalInvestmentObject[i][2] = String.valueOf(deductionsVIAInvestmentIdItt[l]);
								finalInvestmentObject[i][3] = String.valueOf(deductionsVIAEmpIdItt[l]);
								i++;
							}
						}
						if(deductionsVIInvestmentIdItt != null && deductionsVIInvestmentIdItt.length > 0){
							for (int m = 0; m < deductionsVIInvestmentIdItt.length; m++) {
								finalInvestmentObject[i][0] = String.valueOf(deductionsVIVerifiedItt[m]);
								finalInvestmentObject[i][1] = String.valueOf(deductionsVIVerifiedAmountItt[m]);
								finalInvestmentObject[i][2] = String.valueOf(deductionsVIInvestmentIdItt[m]);
								finalInvestmentObject[i][3] = String.valueOf(deductionsVIEmpIdItt[m]);
								i++;
							}
						}
					}
					
					/*Preparing donations investment object*/
					Object [][] donationObject = null;
					if(invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
						if(invbean.getDonationListViewFlag()){
							if(donationIdItt != null && donationIdItt.length > 0){
								donationObject = new Object[donationIdItt.length][4];
								for (int i = 0; i < donationIdItt.length; i++) {
									donationObject[i][0] = String.valueOf(donationVerifiedItt[i]);
									donationObject[i][1] = String.valueOf(donationVerifiedAmountItt[i]);
									donationObject[i][2] = String.valueOf(donationIdItt[i]);
									donationObject[i][3] = String.valueOf(donationEmpIdItt[i]);
								}
							}
						}
					}
					
					/*Saving investments*/
					
					flag = model.saveVerifiedInvestmentDetails(invbean, finalInvestmentObject, donationObject);
					
					if (flag) {
						addActionMessage("Investment verification details updated successfully.");
						if(invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
							saveMonthlyInvestments();
						}
					} else {
						addActionMessage("Investment verification details cannot be updated.");
					}
					model.investmentDetails(invbean,request);
					model.terminate();
				} else {
					addActionMessage("Taxation process is finalized for this financial year.");
					submit();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**Following function resets the form fields.
	 * 
	 */
	
	public final String reset() {
		
		invbean.setEmpId("");
		invbean.setEmpName("");
		invbean.setEmpToken("");
		invbean.setDivId("");
		invbean.setDivName("");
		invbean.setBranchId("");
		invbean.setBranchName("");
		invbean.setDeptId("");
		invbean.setDeptName("");
		invbean.setFromYear("");
		invbean.setToYear("");
		invbean.setInvestverifylist(null);
		invbean.setMyPage("");
		invbean.setShow("");
		invbean.setEstatus("");
		invbean.setListType("");
		invbean.setInvestmentCode("");
		invbean.setInvestmentName("");
		invbean.setPaginationFlag(false);
		invbean.setEmpListFlag(false);
		invbean.setMonthlyFlag(false);
		invbean.setExemptionsListViewFlag(false);
		invbean.setOtherListViewFlag(false);
		invbean.setDeductionsVIAListViewFlag(false);
		invbean.setDeductionsVIListViewFlag(false);
		invbean.setDonationListViewFlag(false);
		invbean.setEmpSaveFlag(false);
		invbean.setProofAttachedFlag(false);
		fetchDefaultDate();
		return SUCCESS;
	}
  
  /** This method is used to view the doc.
	 * @Method viewDoc
	**/
	public final void viewDoc()throws Exception{
		OutputStream oStream = null;response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		String mimeType = "";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			} //end of if
			fileName= request.getParameter("fileName");
			logger.info("fileName--->>>"+fileName);
			fileName = fileName.replace(".", "#");
			String[]extension = fileName.split("#");
			String ext = extension[extension.length-1];
			fileName = fileName.replace("#", ".");
			logger.info("extext--->>>"+ext);
			if(ext.equals("pdf")){
				mimeType = "acrobat";
			} //end of if
			else if(ext.equals("doc")){
				mimeType = "msword";
			} //end of else if
			else if(ext.equals("xls")){
				mimeType = "msexcel";
			} //end of else if
			else if(ext.equals("xlsx")){
				mimeType = "msexcel";
			} //end of else
			else if(ext.equals("jpg")){
				mimeType = "jpg";
			} //end of else if
			else if(ext.equals("txt")){
				mimeType = "txt";
			} //end of else if
			else if(ext.equals("gif")){
				mimeType = "gif";
			} //end of else if
			//if file name is null, open a blank document
			if(fileName==null){
				if(fileName.length()<=0){
					fileName = "blank.doc";
				} //end of if
			} //end of if
			//for getting server path where configuration files are saved.
			String path = getText("data_path") +"/upload/" + poolName+ "/investment/"+fileName;
			oStream = response.getOutputStream();
			
			response.setHeader("Content-type", "application/"+mimeType+""); 
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1)
			{
				oStream.write(iChar);
			} //end of while
			
		}
		catch (FileNotFoundException e) {
			
			logger.error("-----in file not found catch",e);
			addActionMessage("Investment proof document not found");
		} //end of catch
		catch (Exception e) {
			//e.printStackTrace();
		} //end of catch
		finally{
			logger.info("in finally for closing");
			if(fsStream!=null){
			fsStream.close();  
			} //end of if
			if(oStream!=null){
			oStream.flush();
			oStream.close();
			} //end of if
		} //end of finally
		//return null;
	} //end of method
	
	/**This function generates the report displaying the investment details.
	 * 
	 */
	public final String genReport(){
		InvestmentVerificationModel model=new InvestmentVerificationModel();
		model.initiate(context, session);
		model.generateReport(invbean,response);
		model.terminate();
		return null;
		
	}
	
	/**This function displays the attached files.
	 * 
	 */
	public final void viewAttachmentReceipt(){
		try {
			String uploadFileName = request.getParameter("attachmentFile");
			System.out.println("############ uploadFileName ##############"+uploadFileName);
			String dataPath = invbean.getDataPath();
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} else if (ext.equals("doc")) {
					mimeType = "msword";
				} else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} else if (ext.equals("txt")) {
					mimeType = "txt";
				} else if (ext.equals("gif")) {
					mimeType = "gif";
				}
				// if file name is null, open a blank document
				if(fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					}
				}

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/"+ mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename=\""+ fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}

			} catch (FileNotFoundException e) {

				addActionMessage("proof document not found");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** This function updates the modified verified status of the listed monthly accommodation investments 
	 * 
	 * @return
	 */
	public final void saveMonthlyInvestments(){
		boolean result = false;
		try {
			InvestmentVerificationModel model = new InvestmentVerificationModel();
			model.initiate(context, session);
			
			Object[] monthlyRentCode = request.getParameterValues("monthlyRentCodeItt");
			Object[] monthlyEmployeeId = request.getParameterValues("monthlyEmployeeIdItt");
			Object[] monthlyRent = request.getParameterValues("monthlyRentPaidVerifiedItt");
			Object[] monthlyIsMetro = request.getParameterValues("monthlyIsMetroVerifiedItt");
			Object[] monthlyCompany = request.getParameterValues("monthlyCompanyOwnedHouseVerifiedItt");
			Object[] monthlyCityPopulation = request.getParameterValues("monthlyCityPopulationVerifiedItt");
			Object[] monthlyCarUsed = request.getParameterValues("monthlyCarUsedVerifiedItt");
			Object[] monthlyCubicCapacity = request.getParameterValues("monthlyCubicCapacityVerifiedItt");
			Object[] monthlyDriverUsed = request.getParameterValues("monthlyDriverUsedVerifiedItt");
			Object[] monthlyHouseRentPaidByCompany = request.getParameterValues("monthlyHouseRentPaidByCompanyVerifiedItt");
			Object[] monthlyInIndia = request.getParameterValues("monthlyInIndiaVerifiedItt");
			Object[] investmentMonth = request.getParameterValues("investmentMonthItt");
			
			result = model.updateMonthlyAccommodationDetails(invbean, monthlyRentCode, monthlyEmployeeId, monthlyRent, monthlyIsMetro, monthlyCompany, monthlyCityPopulation, monthlyCarUsed, monthlyCubicCapacity, monthlyDriverUsed, monthlyHouseRentPaidByCompany, monthlyInIndia, investmentMonth);
			
			if(result){
				addActionMessage("Monthly accommodation & conveyance records updated successfully.");
			}else{
				addActionMessage("Error updating monthly accommodation & conveyance records.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This function sets the default date when the employee is selected.
	 * 
	 */
	
	public final void fetchDefaultDate(){
		String sysDate ="";
		String[] split = null;
		int currentMonth = 0;
		int year =0000;	
		if(invbean.isSettlementFlag()){
			sysDate = request.getParameter("resignDate");
			split = sysDate.split("-");
		}else{
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			sysDate = formater.format(date);	
			split = sysDate.split("/");
		}
		currentMonth =  Integer.parseInt((split[1]));
		year =  Integer.parseInt((split[2]));
		if(currentMonth<4){
			year = year -1;
		} 
		invbean.setFromYear(String.valueOf(year));
		invbean.setToYear(String.valueOf(year + 1));
	}
	
	
	/** This function is used to navigate through the employees in either direction i.e next or previous.
	 * 
	 * @return view
	 */
	public final String fetchNextOrPreviousEmployee(){
		try {
			int empNo = 0;
			if(invbean.getEmpSaveFlag()){
				saveVerifiedInvestments();	
			}
			if(invbean.getNavigateTo().equals("N")){
				empNo = Integer.parseInt(invbean.getEmpId())+1;
			}else{
				empNo = Integer.parseInt(invbean.getEmpId())-1;	
			}
			
			InvestmentVerificationModel model = new InvestmentVerificationModel();
			model.initiate(context, session);
			
			String nextEmpQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN, ' '), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') NAME FROM HRMS_EMP_OFFC"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID =" + empNo + " ORDER BY HRMS_EMP_OFFC.EMP_ID";
			
			Object [][] empIdObject = model.getSqlModel().getSingleResult(nextEmpQuery);
			
			if(empIdObject != null && empIdObject.length > 0){
				invbean.setEmpId(String.valueOf(empIdObject[0][0]));
				invbean.setEmpToken(String.valueOf(empIdObject[0][1]));
				invbean.setEmpName(String.valueOf(empIdObject[0][2]));
			} 
			handleFlags();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submit();
	}
	
	public final void handleFlags(){
		invbean.setPaginationFlag(false);
		invbean.setEmpListFlag(false);
		invbean.setMonthlyFlag(false);
		invbean.setExemptionsListViewFlag(false);
		invbean.setOtherListViewFlag(false);
		invbean.setDeductionsVIAListViewFlag(false);
		invbean.setDeductionsVIListViewFlag(false);
		invbean.setDonationListViewFlag(false);
		invbean.setProofAttachedFlag(false);
	}
}
