package org.struts.action.admin.increment;

import java.util.ArrayList;
import java.util.Date;

import org.paradyne.bean.admin.increment.AnnualIncrement;
import org.paradyne.model.admin.increment.AnnualIncrementModel;

import sun.util.calendar.CalendarDate;


/**
 * @author sunil 
 *
 */
public class AnnualIncrementAction extends org.struts.lib.ParaActionSupport {
	
	AnnualIncrement annIncre;

	/**
	 * @return the annIncre
	 */
	public AnnualIncrement getAnnIncre() {
		return annIncre;
	}

	/**
	 * @param annIncre the annIncre to set
	 */
	public void setAnnIncre(AnnualIncrement annIncre) {
		this.annIncre = annIncre;
	}
	
	
	public Object getModel(){
		
		return annIncre;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		annIncre = new AnnualIncrement();
		annIncre.setMenuCode(162);
				
	}
	public String annRecord()throws Exception {	
		logger.info("************************");
		AnnualIncrementModel model = new AnnualIncrementModel();
			model.initiate(context,session);
			model.search(annIncre);
			model.terminate();
		
		return "success";
	}
	
	public String save()throws Exception {
		logger.info("lsave ");
		String[] empId = request.getParameterValues("empId");
		String[] currDate = request.getParameterValues("currDate");
		String[] newBasic = request.getParameterValues("newBasic");
		String[] nonQualified = request.getParameterValues("nonQualified");
		
		String[] prevDate = request.getParameterValues("prevDate");
		String[] currBasic = request.getParameterValues("currBasic");
		String[] deuDate = request.getParameterValues("dueDate");
		String[] payScale = request.getParameterValues("payScale");
		
		
		String[] dceListArr = request.getParameterValues("dceList");
		String[] dceDateArr = request.getParameterValues("dceDate");
		String[] dceSrlArr = request.getParameterValues("dceSrl");
		String[] lockArr = request.getParameterValues("lock");
		String[] removeArr = request.getParameterValues("removeChk");
		String[] arrearTo = request.getParameterValues("arrearTo");
		
		AnnualIncrementModel model = new AnnualIncrementModel();
		model.initiate(context,session);
		boolean result = model.add(annIncre,empId,currDate,newBasic,nonQualified,prevDate,
										currBasic,deuDate,dceListArr,dceDateArr,dceSrlArr,lockArr,removeArr,payScale,arrearTo);
		logger.info("4");
		logger.info("8888888");
		model.terminate();
		
		if(result) {
			addActionMessage(getText("addMessage",""));
		} else {
			addActionMessage("Data can not be added");
		}
		logger.info("5");
		return "success";
	}
	
	public String viewRecord()throws Exception {	
		logger.info("");
		AnnualIncrementModel model = new AnnualIncrementModel();
			model.initiate(context,session);
			model.viewAnualIncrement(annIncre);
			model.terminate();
		
		return "success";
	}
	public String viewAfterAdd()throws Exception {	
		String[] empId = request.getParameterValues("empId");
		String[] hiddenEmp = request.getParameterValues("hiddenEmp");
		String[] currDate = request.getParameterValues("currDate");
		String[] newBasic = request.getParameterValues("newBasic");
		String[] nonQualified = request.getParameterValues("nonQualified");
		
		String[] prevDate = request.getParameterValues("prevDate");
		String[] currBasic = request.getParameterValues("currBasic");
		String[] deuDate = request.getParameterValues("dueDate");
		String[] payScale = request.getParameterValues("payScale");	
		String[] dceListArr = request.getParameterValues("dceList");
		String[] dceDateArr = request.getParameterValues("dceDate");
		String[] dceSrlArr = request.getParameterValues("dceSrl");
		String[] arrearTo = request.getParameterValues("arrearTo");
		AnnualIncrementModel model = new AnnualIncrementModel();
		model.initiate(context,session);
		model.viewAfterAdd(annIncre,empId,hiddenEmp,currDate,newBasic,
							nonQualified,prevDate,currBasic,deuDate,payScale,dceListArr,dceDateArr,dceSrlArr,arrearTo);
		model.terminate();
	
		return "success";
	}
	
	
	
	/*public String f9action() throws Exception {
		*//**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 *//*
		String query = "SELECT PAYBILL_ID,PAYBILL_GROUP  FROM HRMS_PAYBILL ORDER BY PAYBILL_ID ";		
					
		
		*//**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 *//* 
		String[] headers={"PayBill ID ","PayBill Name"};
		
		String[] headerWidth={"30", "70"};
		
		*//**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * *//* 	
		
		String[] fieldNames={"annIncre.payBill","annIncre.payBillName"};
		
		*//**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 *//* 
		int[] columnIndex={0,1};
		
		*//**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 *//*
		String submitFlag = "false";
	
		*//**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 *//*
		String submitToMethod="";
		
		*//**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 *//*
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String addNewEmployee(){
		
		
		return "success";
	}*/
	
	public String f9NewEmployee(){
			
		String query=" SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '),"
			+"	NVL(TO_CHAR(EMP_INCR_DATE,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY')),EMP_ID "
	  		+"  FROM  HRMS_EMP_OFFC "
			+"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
			+"	WHERE  EMP_STATUS='S' ORDER BY EMP_ID ";
		
		String[] headers={"Token No.","Employee Name","Last Increment"};
		
		String[] headerWidth={"15", "70","15"};	
		
		String[] fieldNames={"empToken","hiddenEmpName","hiddenPrevDate","hiddenEmpCode"};
		
		int[] columnIndex={0,1,2,3};
		
		String submitFlag = "true";
		
		String submitToMethod="AnnualIncrement_viewAfterAdd.action";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
}	
	