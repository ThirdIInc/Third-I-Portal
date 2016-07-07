/**
 * 
 */
package org.struts.action.payroll.incometax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.incometax.*;
import org.paradyne.model.payroll.salary.Form12BAModel;
import org.struts.lib.*;

/**
 * @author Varunk
 *
 */
public class EmpInvestmentMasterAction extends ParaActionSupport {

	EmpInvestmentMaster empInvestment;

	/**
	 *
	 * 
	 */
	public EmpInvestmentMasterAction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the empInvestment
	 */
	public EmpInvestmentMaster getEmpInvestment() {
		return empInvestment;
	}

	/**
	 * @param empInvestment the empInvestment to set
	 */
	public void setEmpInvestment(EmpInvestmentMaster empInvestment) {
		this.empInvestment = empInvestment;
	}

	public Object getModel() {
		return empInvestment;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		empInvestment = new EmpInvestmentMaster();
		empInvestment.setMenuCode(159);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null )) {
			poolName = "/" + poolName;
		}
		//for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/upload" + poolName+ "/investment/";
		logger.info("data path "+dataPath);
		
		empInvestment.setDataPath(dataPath);
		
		model.initiate(context, session);
		/**
		 * this method is called when the general user logs in the system.
		 */
		if (empInvestment.isGeneralFlag()) {
			empInvestment.setEmpID(empInvestment.getUserEmpId());
			GenEmpRecord();
		}
		
		
		
	//	model.setInvestmentDeclaration(empInvestment);
		model.terminate();
		
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		String split[] = sysDate.split("/");
		int currentMonth =  Integer.parseInt((split[1]));
		int year =  Integer.parseInt((split[2]));
		if(currentMonth<4){
			year = year -1;
		} //end of if
		empInvestment.setFromYear(String.valueOf(year));
		empInvestment.setToYear(String.valueOf(year + 1));
		
	}

	/**
	 * The record for the general employee is retrieved and set in the field.
	 * @return
	 */
	public String GenEmpRecord() {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		empInvestment = model.generalRecord(empInvestment);
		model.terminate();
		return "success";
	}//end of GenEmpRecord method

	/**
	 * This method is used to save the Employee Investment
	 * @return empInvRecord() method
	 * @throws Exception
	 */
	public String save() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		if(empInvestment.getIsProofAttach().equals("N")){
			empInvestment.setUploadFileName(" ");
		}
		boolean result1 = false, result2 = false;
		if (!empInvestment.getIsFromEdit().equals("true")) {
			result1 = model.addEmpInv(empInvestment,request);
			if (result1 == false) {
				addActionMessage("Investement Already Added.Please Edit the Investment ");
				return reset();
			}//end of if

		}//end of if
		else {
			result2 = model.updateEmpInv(empInvestment,request);
		}//end of else
		model.terminate();

		if (result1) {
			addActionMessage(getText("addMessage", ""));
			empInvestment.setInvAmount("");
			empInvestment.setInvValid("");
			empInvestment.setInvCode("");
			empInvestment.setInvName("");
			empInvestment.setIsFromEdit("");
			empInvestment.setInvChapter("");
			empInvestment.setInvSection("");
			empInvestment.setInvLimit("");
			/**
			 * this method is used to retrieve the previously saved employee Investment record
			 */
			return empInvRecord();
		}//end of if 
		else if (result2 == false) {
			addActionMessage("Investement  can not be added");
		}//end of else if
		if (result2) {
			addActionMessage("Record Modified Successfully.");
			empInvestment.setInvAmount("");
			empInvestment.setInvValid("");
			empInvestment.setInvCode("");
			empInvestment.setInvName("");
			empInvestment.setIsFromEdit("");
			empInvestment.setInvChapter("");
			empInvestment.setInvSection("");
			empInvestment.setInvLimit("");
			empInvestment.setIsProofAttach("");
			empInvestment.setUploadFileName("");
			return empInvRecord();

		}//end of if
		else {
			addActionMessage("Problem in Modification");
		}//end of else
		return empInvRecord();
	}//end of save method

	/**
	 * This method is used to display the report in the PDF format.
	 * @return
	 * @throws Exception
	 */
	public void report() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		//boolean result = model.getReport(empInvestment, response);
		String reportPath = "";
		model.generateReport(empInvestment,request, response, reportPath);
		/*if (!result) {
			addActionMessage("No Data Available");
		} else {
			addActionMessage("Problem in Report Generation");
		}*/
		model.terminate();

		//return null;
	}//end of report method

	/**
	 * this method is used to reset all the fields
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {

			empInvestment.setInvAmount("");
			empInvestment.setInvCode("");
			empInvestment.setInvName("");
			empInvestment.setInvLimit("");
			empInvestment.setInvChapter("");
			empInvestment.setInvSection("");
			empInvestment.setInvValid("");
			empInvestment.setUploadFileName("");
			empInvestment.setInvestmentType("");
			//empInvestment.setUploadDocName("");
		} catch (Exception e) {
			logger.error("Error in reset method");
		}
		return empInvRecord();
	}//end of reset method

	public String edit() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		/**
		 * this method is used to get the investments
		 */
		model.getRecord(empInvestment);

		empInvestment.setIsFromEdit("true");

		model.terminate();
		return empInvRecord();
	}//end of edit method

	/**
	 * This method is used to get all the investment record and the debits records of the employee.
	 * @return
	 * @throws Exception
	 */
	public String empInvRecord() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		model.getEmpInvRecord(empInvestment);
		model.getTaxRecord(empInvestment);
		model.terminate();
		return SUCCESS;
	}//end of empInvRecord method

	/**
	 * This method is used to retrieve the record of the previously saved investments and the tax record
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		empInvestment.setInvestmentType("");
		//model.getEmpInvRecord(empInvestment);
		//model.getTaxRecord(empInvestment);
		//empInvestment.setChkEdit("true");
		model.terminate();
		return SUCCESS;
	}//end of view method

	/**
	 * This method is used to delete the investments which was earlier added
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		boolean result = model.delEmpInvRecord(empInvestment,request);
		model.terminate();
		if (result) {
			addActionMessage(getText("delMessage", ""));
			return empInvRecord();
		} else {
			addActionMessage("Investment  can not be deleted");
		}
		return empInvRecord();
	}//end of delete method
	
	
	/**
	 * @Method viewCV
	 * @Purpose to view the details of the candidate resume
	**/
	public void viewDoc()throws Exception{
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
			String path = getText("data_path") + "/upload/" + poolName+ "/investment/"+fileName;
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
			e.printStackTrace();
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
	

	/**
	 * This action is called to get the list of all the employee records.
	 * @return
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
	
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ " NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_CENTER.CENTER_NAME,' ') ,HRMS_SALARY_MISC.SAL_PANNO, HRMS_EMP_OFFC.EMP_ID,HRMS_CENTER.CENTER_ID,HRMS_EMP_OFFC.EMP_GENDER," +
				  " TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'YYYY'),TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE ,'DD-MM-YYYY'), " +
				  " HRMS_DEPT.DEPT_NAME,HRMS_CADRE.CADRE_NAME FROM HRMS_EMP_OFFC  	"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	"
				+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ " LEFT JOIN  HRMS_SALARY_MISC ON  HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID"
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ "LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
				+ "LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"	;
		
				if (empInvestment.getUserProfileDivision() != null
						&& empInvestment.getUserProfileDivision().length() > 0) {
					query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
							+ empInvestment.getUserProfileDivision() + ")";
				}
		
			query +=" ORDER BY EMP_ID ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */

		String[] headers = { getMessage("employee.id"),
				getMessage("employee"), getMessage("designation"),
				getMessage("branch")};

		String[] headerWidth = { "10", "30", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "empInvestment.empTokenNo",
				"empInvestment.empName", "empInvestment.designation",
				"empInvestment.center","empInvestment.panNum", "empInvestment.empID","empInvestment.centerId",
				"empInvestment.gender","empInvestment.age","empInvestment.joiningDate","empInvestment.department","empInvestment.grade"};

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4,5,6,7,8,9,10,11};

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "EmployeeInvestment_view.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9empaction method

	/**
	 * This action is called to get the list of all the investment names.
	 * @return
	 * @throws Exception
	 */
	public String f9invaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "select INV_CODE,NVL(INV_NAME,''),NVL(INV_CHAPTER,''),NVL(INV_SECTION,''),NVL(INV_LIMIT1,0) from HRMS_TAX_INVESTMENT WHERE INV_TYPE = 'I' ORDER BY INV_CODE";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */

		String[] headers = { getMessage("taxf9InvstCd"),
				getMessage("taxInvstName"), getMessage("taxInvChptr"),
				getMessage("taxInvSec"), getMessage("taxInvstLmt") };
		String[] headerWidth = { "20", "20", "20", "20", "20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "empInvestment.invCode",
				"empInvestment.invName", "empInvestment.invChapter",
				"empInvestment.invSection", "empInvestment.invLimit" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9invaction method

	/**
	 * This method is used to get the tax records of the employees. Of the debits like Income Tax,
	 * Provident Fund.
	 * @return
	 * @throws Exception
	 */
	public String invRecord() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		model.getTaxInvRecord(empInvestment);
		empInvRecord();
		model.terminate();
		return "success";
	}//end of invRecord method

	public String f9action() {

		String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ " INV_FINYEAR_FROM,INV_FINYEAR_TO,EMP_ID,HRMS_EMP_INVESTMENT.INV_CODE FROM HRMS_EMP_INVESTMENT "
				+ " INNER JOIN HRMS_TAX_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_INVESTMENT.EMP_ID)"
				+ " AND HRMS_TAX_INVESTMENT.INV_TYPE = 'I'";
				
		String[] headers = { getMessage("employee.id"),
				getMessage("employee"), getMessage("taxFinYrFrm"),
				getMessage("taxFinYrTo") };
		String[] headerwidth = { "20", "25", "25", "25" };
		String[] fieldNames = { "empInvestment.empTokenNo",
				"empInvestment.empName", "empInvestment.empID",
				"empInvestment.fromYear", "empInvestment.toYear",
				"empInvestment.invCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
		String submitFlage = "true";
		String submitToMethod = "EmployeeInvestment_f9searchretrive.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}
	
	 public String resetEmpSection()
	 {
		 
		 try {
			 
			 empInvestment.setInvCode("");
			 empInvestment.setEmpID("");
			 empInvestment.setEmpTokenNo("");
			 empInvestment.setEmpName("");
			 empInvestment.setDepartment("");
			 empInvestment.setCenterId("");
			 empInvestment.setCenter("");
			 empInvestment.setGender("");
			 empInvestment.setJoinedMonth("");
			 empInvestment.setAge("");
			 empInvestment.setFromYear("");
			 empInvestment.setToYear("");
			 empInvestment.setChkEdit("false");
			 empInvestment.setDesignation("");
			 empInvestment.setGrade("");
			 empInvestment.setJoiningDate("");
			 empInvestment.setPanNum("");
			 empInvestment.setInvestmentType("");

		 } catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	 }

	public String f9searchretrive() {
		logger.info("your at f9searchretrive action. ");

		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		model.getEmpInvRecord(empInvestment);
		model.getTaxRecord(empInvestment);
		empInvestment.setChkEdit("true");
		model.terminate();
		return "success";
	}
	
	/**
	 * ===========START OF HOUSE RENT DECLARATION METHODS==================
	 */
	
	/**
	 * following function is called when the view button is clicked in the house rent form.
	 * @return
	 */
	public String viewHra ()  {
		try{
			HouseRentModel model =new HouseRentModel();
			model.initiate(context,session);
			Object rows[][] =model.getRentAmt(empInvestment,request);
			model.getOwnerDetails(empInvestment);
			request.setAttribute("rows",rows);
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
			return "hraPage";
	}
	
	/**
	 * following function is called to add or modify a record
	 * @return
	 */
	public String saveHra(){
		try{
		HouseRentModel model=new HouseRentModel();
		model.initiate(context, session);
		String monCode[]=request.getParameterValues("monthNo");//Retrieves the month no
		String amt[]=request.getParameterValues("amount");//Amount
		String prfAttach[]=request.getParameterValues("proofAttach");//proofAttach
		boolean result=false;
		String str="";
		boolean res=model.getRentCode(empInvestment);
			
		if(res){
			result=model.modRent(empInvestment, monCode, amt,prfAttach);
			model.getRentAmt(empInvestment,request);
			if(result){
				str="Record updated Successfully";
			}
		}//End if condition
		else {
			//System.out.println("B4===addRent==>");
			result=model.addRent(empInvestment, monCode, amt,prfAttach);
			model.getRentAmt(empInvestment,request);
			if(result){
				str="Record saved Successfully";
			} //end of if
		}//End else condition
		model.terminate();
		addActionMessage(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "hraPage";
	}
	/**
	 * following function is called when click on href button in the House Rent form
	 */
	
	public void viewProof() throws IOException {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		String mimeType = "";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			} //end of if
			fileName= request.getParameter("prfAttach");
			fileName = fileName.replace(".", "#");
			String[]extension = fileName.split("#");
			String ext = extension[extension.length-1];
			fileName = fileName.replace("#", ".");
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
				mimeType = "msexcel1";
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
			
			String path = request.getParameter("dataPath")+"/"+fileName;
			oStream = response.getOutputStream();
			/*logger.info("path =="+path);
			logger.info("mimeType =="+mimeType);
			logger.info("vxcvxcvxc"+"application/"+mimeType);*/
			if(ext.equals("pdf")){
				//response.setHeader("Content-type", "application/"+mimeType+"");
			} //end of if
			else{
				response.setHeader("Content-type", "application/"+mimeType+"");
			}
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1)
			{
				oStream.write(iChar);
			} //end of while
			
		}
		catch (FileNotFoundException e) {
			addActionMessage("proof document not found");
		} //end of catch
		catch (Exception e) {
			e.printStackTrace();
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
	}
	/**
	 * following function is called when delete button is clicked in the house rent form 
	 * @return
	 */
	public String deleteHra(){
		HouseRentModel model=new HouseRentModel();
		model.initiate(context, session);
		boolean result=model.deleteRent(empInvestment);
		try {
			Object rows[][] = model.getRentAmt(empInvestment, request);
			request.setAttribute("rows", rows);
		} catch (Exception e) {
				// TODO: handle exception
		}
		model.terminate();
		if(result){
			addActionMessage("Record deleted Successfully.");
		}
		return "hraPage";
	}
	//Added by Ganesh 11/10/2011
	/**
	 * This function is used when Investment section button is clicked.
	 * @return success
	 */
	public String viewInvDecReocrd(){
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		String invDecType = request.getParameter("invType");
		String frmYear = empInvestment.getFromYear();
		String toYear = empInvestment.getToYear();
		if(invDecType.equals("E")){
			empInvestment.setInvestmentType("E");
		}
		if(invDecType.equals("D")){
			empInvestment.setInvestmentType("D");
		}
		if(invDecType.equals("S")){
			empInvestment.setInvestmentType("S");
		}
		if(invDecType.equals("O")){
			empInvestment.setInvestmentType("O");
		}
		if(invDecType.equals("M")){
			empInvestment.setInvestmentType("M");
		}
		if(invDecType.equals("C")){
			empInvestment.setInvestmentType("C");
		}
		if(invDecType.equals("M")){
			empInvestment.setMonthAccomodationFlag(true);
			if(empInvestment.getIttRentCode()==""){
				empInvestment.setMonthIttFlag(true);
			} else {
				empInvestment.setMonthIttFlag(false);
			}
			empInvestment.setInvDecFlag(false);
			model.getMonthAccomdation(empInvestment,request,invDecType,frmYear,toYear);
		} else if(invDecType.equals("C")){
			empInvestment.setDonationFlag(true);
			empInvestment.setInvDecFlag(false);
			
			model.getDonationDtls(empInvestment,request,invDecType);
			
			
		} else {
			empInvestment.setInvDecFlag(true);
			empInvestment.setMonthAccomodationFlag(false);
		}
		
		model.viewInvDecReocrd(empInvestment,invDecType,frmYear,toYear);
		model.checkInvDeclCutOffDtls(empInvestment,frmYear,toYear);
		model.terminate();
		return "success"; 
	}
	
	

	/**
	 * THis function is used to view Attachment.
	 * @throws Exception
	 */
	public void viewAttachedProof() throws Exception {
		try {
			String uploadFileName = request.getParameter("fileName");
			String dataPath = request.getParameter("dataPath");
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
				if (fileName == null) {
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
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename=\""
						+ fileName + "\"");

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

	
	
	/**
	 * This method is used to save the Employee Investment
	 * @return empInvRecord() method
	 * @throws Exception
	 */
	public String saveInvestment() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		
		final boolean result;
			result =  model.saveEmpInv(empInvestment,request);
			
			if (result) {
				this.addActionMessage("Records Saved successfully.");
				
			} else {
				this.addActionMessage("Records Saved successfully.");
				
			}
		
			String invDecType = empInvestment.getHiddenfrmId();
			String frmYear = empInvestment.getFromYear();
			String toYear = empInvestment.getToYear();
			empInvestment.setInvDecFlag(true);
			model.viewInvDecReocrd(empInvestment,invDecType,frmYear,toYear);
			
		model.terminate();
		
		return SUCCESS;
	}//end of save method
	
	public String addMultipleProof() {

		try {
			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");
			String[] proofFileName = request
					.getParameterValues("proofFileName");
			EmpInvestmentModel model = new EmpInvestmentModel();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(srNo, proofName, empInvestment);
			model.setProofList(srNo, proofName, empInvestment);
			// model.getEmployeeDtls(claimApp, claimApp.getUserEmpId());
			empInvestment.setUploadLocFileName("");
			String invDecType = request.getParameter("invType");
			String frmYear = empInvestment.getFromYear();
			String toYear = empInvestment.getToYear();
			if(invDecType.equals("M")){
				empInvestment.setMonthAccomodationFlag(true);
				empInvestment.setInvDecFlag(false);
				model.getMonthAccomdation(empInvestment,request,invDecType,frmYear,toYear);
			} else {
				empInvestment.setInvDecFlag(true);
				empInvestment.setMonthAccomodationFlag(false);
			}
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addMultipleProof--------" + e);
		}

		return SUCCESS;
	}
	
	public String removeUploadFile() {

		try {

			String[] srNo = request.getParameterValues("proofSrNo"); // serial

			String[] proofName = request.getParameterValues("proofName");// keep

			// String[] proofFileName =
			// request.getParameterValues("proofFileName");
			EmpInvestmentModel model = new EmpInvestmentModel();
			model.initiate(context, session);
			// trvlClaimDtl();
			// setExpDtls();
			// model.removeUploadFile(srNo, proofName, proofFileName, claimApp);
		 model.removeUploadFile(srNo, proofName, empInvestment);

			/*
			 * claimApp.setSchFlag("true"); claimApp.setAddNewFlag("true"); //
			 * claimApp.setUploadFlag("true"); setNavigationPanel();
			 */
		 String invDecType = request.getParameter("invType");
			String frmYear = empInvestment.getFromYear();
			String toYear = empInvestment.getToYear();
			if(invDecType.equals("M")){
				empInvestment.setMonthAccomodationFlag(true);
				empInvestment.setInvDecFlag(false);
				model.getMonthAccomdation(empInvestment,request,invDecType,frmYear,toYear);
			} else {
				empInvestment.setInvDecFlag(true);
				empInvestment.setMonthAccomodationFlag(false);
			}
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile--------" + e);
		}

		return SUCCESS;
	}
	
	/**
	 * This method is used to save the Employee Investment
	 * @return empInvRecord() method
	 * @throws Exception
	 */
	public String saveRentInvestment() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		
		final boolean result;
			///result =  model.saveEmpRentInv(empInvestment,request);
			if ("".equals(this.empInvestment.getIttRentCode())) {
				result = model.saveEmpRentInv(empInvestment,request);
				if (result) {
					
					this.addActionMessage("Monthly Accommodation & Conveyance Declaration Saved successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}
			} else {
				result = model.updateEmpRentInv(empInvestment, request);
				if (result) {
					
					this.addActionMessage("Monthly Accommodation & Conveyance Declaration update successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}
			}
			
			/*
			
			if (result) {
				this.addActionMessage("Records Saved successfully.");
				
			} else {
				this.addActionMessage("Error Occured.");
				
			}*/
		
			String invDecType = empInvestment.getHiddenfrmId();
			String frmYear = empInvestment.getFromYear();
			String toYear = empInvestment.getToYear();
			empInvestment.setInvDecFlag(false);
			model.viewInvDecReocrd(empInvestment,invDecType,frmYear,toYear);
			
		model.terminate();
		
		return SUCCESS;
	}//end of save method
	
	/**
	 * This method is used to save the Employee Investment
	 * @return empInvRecord() method
	 * @throws Exception
	 */
	public String saveDonation() throws Exception {
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		
		final boolean result;
	/*	if (model.checkInvDeclCutOffDtls(this.empInvestment)) {
			if (model.checkInvDeclperiodDtls(this.empInvestment)) {*/
			result =  model.saveEmpDonation(empInvestment,request);
			
			if (result) {
				this.addActionMessage("Records Saved successfully.");
				
			} else {
				this.addActionMessage("Error occured while saving.");
				
			}
		
			String invDecType = empInvestment.getHiddenfrmId();
			String frmYear = empInvestment.getFromYear();
			String toYear = empInvestment.getToYear();
			empInvestment.setInvDecFlag(false);
			empInvestment.setDonationFlag(true);
			model.getDonationDtls(empInvestment,request,invDecType);
			/*}
			
			else {
				this.addActionMessage("Investment Declaration period is not configured.Please contact your Administrator.");
				///this.reset();
				///this.getNavigationPanel(2);
			}
		}
		
		else {
			this.addActionMessage("Investment Declaration Cut-off Date is not configured. Please contact your Administrator.");
			///this.reset();
			///this.getNavigationPanel(2);
		}*/
		
		model.terminate();
		
		return SUCCESS;
	}//end of save method
	
	
	public final String mailReport(){
		try {
			EmpInvestmentModel model = new EmpInvestmentModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
			
			model.generateReport(empInvestment,request, response, reportPath);
		//	model.generateReport(bean, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	public String input(){
		EmpInvestmentModel model = new EmpInvestmentModel();
		model.initiate(context, session);
		String frmYear = empInvestment.getFromYear();
		String toYear = empInvestment.getToYear();
		
		///model.checkInvDeclCutOffDtls(empInvestment,frmYear,toYear);
		///model.checkInvDeclperiodDtls(empInvestment,frmYear,toYear);
		model.terminate();
		//deleteFile();
		return INPUT;
	}
}
