package org.struts.action.payroll;

import org.paradyne.bean.payroll.EsicForm6;
import org.paradyne.model.payroll.EsicForm6Model;
import org.struts.lib.ParaActionSupport;

public class EsicForm6Action extends ParaActionSupport {
	EsicForm6 esicBean;
	
	public void prepare_local() throws Exception {
		esicBean = new EsicForm6();
		esicBean.setMenuCode(2113);
	}

	public Object getModel() {
		return esicBean;
	}
	
	public String input(){
		return INPUT;
	}
	
	public String f9divisionAction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		String query = " SELECT HRMS_DIVISION.DIV_ID, HRMS_DIVISION.DIV_NAME FROM HRMS_DIVISION   ";
		
		
		if(esicBean.getUserProfileDivision() !=null && esicBean.getUserProfileDivision().length()>0)
			query+= " WHERE HRMS_DIVISION.DIV_ID IN ("+esicBean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  HRMS_DIVISION.DIV_ID ";
			
			
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("division.code"),getMessage("division")};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"divisionCode","divisionName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public void report(){
		try {
			EsicForm6Model model = new EsicForm6Model();
			model.initiate(context, session);
			String logoPath = getText("data_path")+ "/logos/eGov/PFSmall.bmp";
			model.report(esicBean, request, response, logoPath,"");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final String f9reportType() {
		try {
			//String query = " SELECT 'Pdf', 'Excel', 'Html', 'Word' FROM DUAL";
			String query = " SELECT 'Pdf' FROM DUAL";
			//String[][] type = new String[][]{{"PDF"},{"Xls"},{"Doc"},{"Html"}};
			String[][] type = new String[][]{{"Xls"},{"PDF"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"repType"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "EsicForm6_reportMail.action";
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	
	public final String reportMail(){
		try {
			EsicForm6Model model = new EsicForm6Model();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String logoPath = getText("data_path")+ "/logos/eGov/PFSmall.bmp";
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.report(esicBean, request, response, logoPath, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
}
