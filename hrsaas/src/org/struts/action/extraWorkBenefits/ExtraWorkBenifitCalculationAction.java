package org.struts.action.extraWorkBenefits;

import org.paradyne.bean.extraWorkBenefits.ExtraWorkBenifitCalculation;
import org.paradyne.model.extraWorkBenefits.ExtraWorkBenifitCalculationModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.struts.lib.ParaActionSupport;

public class ExtraWorkBenifitCalculationAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExtraWorkBenifitCalculationAction.class);

	ExtraWorkBenifitCalculation extraWorkBenifitCal;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		extraWorkBenifitCal = new ExtraWorkBenifitCalculation();
		extraWorkBenifitCal.setMenuCode(963);
	}
	 
	public String input() {
		try {
			getNavigationPanel(1);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return extraWorkBenifitCal;
	}

	public ExtraWorkBenifitCalculation getExtraWorkBenifitCal() {
		return extraWorkBenifitCal;
	}

	public void setExtraWorkBenifitCal(
			ExtraWorkBenifitCalculation extraWorkBenifitCal) {
		this.extraWorkBenifitCal = extraWorkBenifitCal;
	}

	public String f9division() throws Exception {
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
	 
		if(extraWorkBenifitCal.getUserProfileDivision() !=null && extraWorkBenifitCal.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+extraWorkBenifitCal.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";

		String[] headers = { getMessage("division.code"),
				getMessage("division") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "divisionCode", "divisionName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9branch method

	public String reset() {
		try {
			extraWorkBenifitCal.setDivisionCode("");
			extraWorkBenifitCal.setDivisionName("");
			extraWorkBenifitCal.setMonth("");
			extraWorkBenifitCal.setYear("");
			extraWorkBenifitCal.setSalarymonth("");
			extraWorkBenifitCal.setSalaryyear("");
			extraWorkBenifitCal.setProcessCode("");
			extraWorkBenifitCal.setSalaryCheck("");
			extraWorkBenifitCal.setLockFlag("");
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String process() {
		try {
			ExtraWorkBenifitCalculationModel model = new ExtraWorkBenifitCalculationModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/xml/Payroll/";
			String checkQue = "  SELECT EXTRAWORK_PROCESS_MONTH,EXTRAWORK_PROCESS_YEAR FROM HRMS_EXTRAWORK_PROCESS_HDR "
					+ " WHERE EXTRAWORK_PROCESS_DIVISION="
					+ extraWorkBenifitCal.getDivisionCode();

			Object data[][] = model.getSqlModel().getSingleResult(checkQue);

			if (extraWorkBenifitCal.getLockFlag().equals("LOCK")) {
				addActionMessage("Record already Locked so can not processed");
				if(!extraWorkBenifitCal.getProcessCode().equals(""))
					model.setProcessedRecord(extraWorkBenifitCal, request);
					getNavigationPanel(2);
			} else {
				if (data != null && data.length > 0) {
					if (String.valueOf(data[0][0]).equals(
							extraWorkBenifitCal.getMonth())
							&& String.valueOf(data[0][1]).equals(
									extraWorkBenifitCal.getYear())) {
						
						String str=getMonth(Integer.parseInt(extraWorkBenifitCal.getMonth()));
						addActionMessage("Data already Process for "+str+" "+"-"+" "+extraWorkBenifitCal.getYear()+""); 
						if(!extraWorkBenifitCal.getProcessCode().equals(""))
						model.setProcessedRecord(extraWorkBenifitCal, request);
						//reset();
						getNavigationPanel(1);
					}
					else
					{
						boolean result =model.processData(extraWorkBenifitCal, path, request);
						
						if(result)
						{
							addActionMessage("Data Processed successfully");
							if(!extraWorkBenifitCal.getProcessCode().equals(""))
							model.setProcessedRecord(extraWorkBenifitCal, request);
							getNavigationPanel(2);
						}
						else
						{
							addActionMessage("No data to process");
							getNavigationPanel(1);
						}
							
					}

				} else {
				boolean result =model.processData(extraWorkBenifitCal, path, request);
				
				if(result)
				{
					addActionMessage("Data Processed successfully");
					if(!extraWorkBenifitCal.getProcessCode().equals(""))
					model.setProcessedRecord(extraWorkBenifitCal, request);
					getNavigationPanel(2);
				}
				else
				{
					addActionMessage("No data to process");
					getNavigationPanel(1);
				}
					

				}
			}

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in process----------" + e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			ExtraWorkBenifitCalculationModel model = new ExtraWorkBenifitCalculationModel();
			model.initiate(context, session);
			String empCode[] = request.getParameterValues("employeeId");// employee
			// id
			String extraWorkDate[] = request
					.getParameterValues("extraWorkDate");// extra
			// work
			// date
			String extraWorkBenifitFor[] = request
					.getParameterValues("benifitFor");// benifit
			// for
			String extraWorkBenifitType[] = request
					.getParameterValues("benifitType");// benifit for
			
			String creditedTo[] = request
			.getParameterValues("creditedTo");// benifit for
	
			String extraworkFromTime[] = request
			.getParameterValues("extraworkFromTime");//from time
	
			String extraworkToTime[] = request
			.getParameterValues("extraworkToTime");// to time
	
			String creditHeadCode[] = request
					.getParameterValues("creditHeadCode");// Credit
			Object[][] rows = null;
			if (empCode != null && empCode.length > 0) {
				rows = new Object[empCode.length][creditHeadCode.length];
				for (int i = 0; i < empCode.length; i++) {

					rows[i] = request.getParameterValues(String.valueOf(i));
					

				}
			}
			
			String totAmount[] =request
			.getParameterValues("totAmount");
			
		 
			if (extraWorkBenifitCal.getProcessCode().equals("")) {
			 
					boolean result = model.saveExtraBenifitRecord(
							extraWorkBenifitCal, empCode, extraWorkDate,
							extraWorkBenifitFor, extraWorkBenifitType, rows,
							creditHeadCode,totAmount,creditedTo,extraworkFromTime,extraworkToTime);
					if (result) {
						addActionMessage("Record saved successfully");
						model.setProcessedRecord(extraWorkBenifitCal, request);
						getNavigationPanel(3);
						//reset();
						//model.setProcessedRecord(extraWorkBenifitCal,request);
					} else {
						model.setProcessedRecord(extraWorkBenifitCal, request);
						addActionMessage("Record can not saved");
						getNavigationPanel(3);
					}
			 
				
			} else {
				 
					boolean result = model.updateExtraBenifitRecord(
							extraWorkBenifitCal, empCode, extraWorkDate,
							extraWorkBenifitFor, extraWorkBenifitType, rows,
							creditHeadCode,totAmount,extraworkFromTime,extraworkToTime);
					if (result) {
						addActionMessage("Record updated successfully");
						model.setProcessedRecord(extraWorkBenifitCal, request);
						getNavigationPanel(3);
						//reset();
						//model.setProcessedRecord(extraWorkBenifitCal,request);
					} else {
						addActionMessage("Record can not updated");
					}
				}
				
			 
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in save----------" + e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String f9searchaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT HRMS_DIVISION.DIV_NAME,DECODE(EXTRAWORK_PROCESS_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'),NVL(EXTRAWORK_PROCESS_YEAR,0),DECODE(EXTRAWORK_PROCESS_FLAG,'Y','LOCK','N','UNLOCK'),HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_DIVISION "
				+ " ,EXTRAWORK_INCLUDE_SAL_FLAG,NVL(EXTRAWORK_INCLUDE_SAL_MONTH,0),EXTRAWORK_INCLUDE_SAL_YEAR,EXTRAWORK_PROCESS_MONTH,EXTRAWORK_PROCESS_CODE "
				+ " 	FROM HRMS_EXTRAWORK_PROCESS_HDR "
				+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_DIVISION) ";
			 
		
		if(extraWorkBenifitCal.getUserProfileDivision() !=null && extraWorkBenifitCal.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+extraWorkBenifitCal.getUserProfileDivision() +")"; 
			query+= "ORDER BY EXTRAWORK_PROCESS_CODE ";


		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division", "Month", "Year", "Status" };

		String[] headerWidth = { "40", "20", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divisionName", "month", "year", "lockFlag",
				"divisionCode", "salaryCheck", "salarymonth", "salaryyear",
				"month", "processCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ExtraWorkBenifitCalculation_setProcessedRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end of f9empaction

	public String setProcessedRecord() {
		try {
			ExtraWorkBenifitCalculationModel model = new ExtraWorkBenifitCalculationModel();
			model.initiate(context, session);
			model.setProcessedRecord(extraWorkBenifitCal, request);
			if (extraWorkBenifitCal.getLockFlag().equals("LOCK")) {
				 getNavigationPanel(4);
				 extraWorkBenifitCal.setEnableAll("N");
			}
			else
			{
				getNavigationPanel(3);
			}
			
			String str = model.setSalFlag(extraWorkBenifitCal);
			if (str.equals("Y"))
				extraWorkBenifitCal.setSalaryCheck("true");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setProcessedRecord----------" + e);
		}
		return SUCCESS;

	}

	public String lock() {
		try {
			ExtraWorkBenifitCalculationModel model = new ExtraWorkBenifitCalculationModel();
			model.initiate(context, session);
			String empCode[] = request.getParameterValues("employeeId");// employee
			String extraWorkDate[] = request
			.getParameterValues("extraWorkDate");// extra
			String extraworkToTime[] = request
			.getParameterValues("extraworkToTime");// to time
			String extraworkFromTime[] = request
			.getParameterValues("extraworkFromTime");//from time
			String totAmount[] =request
			.getParameterValues("totAmount");
			
				boolean res = model.lockRecord(extraWorkBenifitCal,empCode,extraWorkDate,extraworkFromTime,extraworkToTime,totAmount);

				if (res) {
					model.setProcessedRecord(extraWorkBenifitCal, request);
					addActionMessage("Record locked successfully");
					extraWorkBenifitCal.setLockFlag("LOCK");
					getNavigationPanel(4);
					 
				} else {
					model.setProcessedRecord(extraWorkBenifitCal, request);
					addActionMessage("Record can not locked");
					getNavigationPanel(4);
				}
		

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setProcessedRecord----------" + e);
		}
		return SUCCESS;

	}
	public String unlock() {
		try {
			ExtraWorkBenifitCalculationModel model = new ExtraWorkBenifitCalculationModel();
			model.initiate(context, session);
			
			String empCode[] = request.getParameterValues("employeeId");// employee
			String extraWorkDate[] = request
			.getParameterValues("extraWorkDate");// extra
			String extraworkToTime[] = request
			.getParameterValues("extraworkToTime");// to time
			String extraworkFromTime[] = request
			.getParameterValues("extraworkFromTime");//from time
		 
		 boolean res = model.unlockRecord(extraWorkBenifitCal,empCode,extraWorkDate,extraworkFromTime,extraworkToTime);

				if (res) {
					model.setProcessedRecord(extraWorkBenifitCal, request);
					//addActionMessage("Record unlocked successfully");
					extraWorkBenifitCal.setLockFlag("UNLOCK");
					getNavigationPanel(3);
					//reset();
				} else {
					model.setProcessedRecord(extraWorkBenifitCal, request);
					//addActionMessage("Record can not unlocked");
					getNavigationPanel(3);
			}

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setProcessedRecord----------" + e);
		}
		return SUCCESS;

	}
	
	public String back()
	{
		reset();
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String delete()
	{
		try {
			ExtraWorkBenifitCalculationModel model = new ExtraWorkBenifitCalculationModel();
			model.initiate(context, session);
			
			String extraworkFromTime[] = request
			.getParameterValues("extraworkFromTime");//from time
	
			String extraworkToTime[] = request
			.getParameterValues("extraworkToTime");// to time
			
			String empCode[] = request.getParameterValues("employeeId");// employee
			// id
			String extraWorkDate[] = request
					.getParameterValues("extraWorkDate");// extra
			// work
			// date
			
			boolean res = model.deleteRecord(extraWorkBenifitCal,extraWorkDate,empCode,extraworkFromTime,extraworkToTime);
			if(res)
			{
				addActionMessage("Record deleted successfully");
				reset();
				getNavigationPanel(1);
			}
			else
			{
				addActionMessage("Record can't deleted");
			}
		} catch (Exception e) {
			logger.error("Exception in delete----------" + e);
		}
		return SUCCESS;
	}
	
	public String getMonth(int month)
	{
		String str="";
		switch (month)
		{
		 	case 1 : str="January";
						break;
			case 2 : str= "February";
			break;
			case 3 : str= "March";
			break;
			case 4 : str= "April";
			break;
			case 5 : str= "May";
			break;
			case 6 : str= "June";
			break;
			case 7 : str= "July";
			break;
			case 8 : str= "August";
			break;
			case 9 : str= "September";
			break;
			case 10 : str= "October";
			break;
			case 11 : str= "November";
			break;
			case 12 : str= "December";
		}
		return str;
	}
}
