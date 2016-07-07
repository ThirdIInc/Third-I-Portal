package org.struts.action.payroll.incometax;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.struts.lib.*; 
import org.paradyne.bean.payroll.incometax.HouseRent;
import org.paradyne.model.admin.srd.AddressDetailsModel;
import org.paradyne.model.payroll.incometax.*;
import javax.servlet.http.*;
/*
 * author:Pradeep Kumar Sahoo
 * Date:26.03.2008
 */

public class HouseRentAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	HouseRent hr;

	public HouseRent getHr() {
		return hr;
	}

	public void setHr(HouseRent hr) {
		this.hr = hr;
	}
		
	public Object getModel() {
			return hr;
	}
	
	public void prepare_local() throws Exception {
		hr=new HouseRent();
		hr.setMenuCode(550);
	}
	
	/**
	 * following function is called when delete button is clicked in the house rent form 
	 * @return
	 */
	public String delete(){
		HouseRentModel model=new HouseRentModel();
		model.initiate(context, session);
				boolean result=model.deleteRent(hr);
				hr.setRentCode("");
				hr.setEmpid("");
				hr.setEmpName("");
				hr.setDesignation("");
				hr.setCenter("");
				hr.setEmpTokenNo("");
				hr.setFromYear("");
				hr.setToYear("");
				hr.setRentList(null);
		model.terminate();
		
		if(result){
			addActionMessage("Record deleted Successfully.");
		}
		return "success";
	}
	
	/**
	 * following function is called to add or modify a record
	 * @return
	 */
	public String save(){
		try{
		HouseRentModel model=new HouseRentModel();
		model.initiate(context, session);
				String monCode[]=request.getParameterValues("monthNo");//Retrieves the month no
				String amt[]=request.getParameterValues("amount");//Amount
				boolean result=false;
				String str="";
				boolean res=model.getRentCode(hr);
			
				if(res){
					result=model.modRent(hr, monCode, amt);
					model.getRentAmt(hr,request);
					if(result){
						str="Record updated Successfully";
					}
					
					
				}//End if condition
				
				else {
					
					result=model.addRent(hr, monCode, amt);
					model.getRentAmt(hr,request);
					if(result){
						str="Record saved Successfully";
						}
				}//End else condition
		

			
		model.terminate();
		addActionMessage(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
		
	}
	/*
	 * This function is called to reset the form 
	 */
	public String reset() throws Exception {
		HouseRentModel model=new HouseRentModel();
		model.initiate(context, session);
				hr.setRentCode("");
				hr.setEmpid("");
				hr.setEmpName("");
				hr.setDesignation("");
				hr.setCenter("");
				hr.setEmpTokenNo("");
				hr.setFromYear("");
				hr.setToYear("");
				hr.setRentList(null);
		model.terminate();
		return "success";
		
		
	}
	/**
	 * following function is called when a general user makes login
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		
			HouseRentModel model = new HouseRentModel();
			model.initiate(context,session);
			model.getEmployeeDetails(hr.getUserEmpId(),hr);
			model.terminate();
			
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String sysdate = format.format(date);
			String split[] = sysdate.split("/");
			int year = Integer.parseInt(String.valueOf(split[2]));
			hr.setFromYear(String.valueOf(year));
			hr.setToYear(String.valueOf(year + 1));
	}
	
	public String year() throws Exception {
			HouseRentModel model=new HouseRentModel();
			model.initiate(context, session);
			model.getFinYear(hr);
			model.terminate();
			return "success";
		
	}
	/**
	 * following function is called when the view button is clicked in the house rent form.
	 * @return
	 */
	public String view ()  {
		try{
			HouseRentModel model =new HouseRentModel();
			model.initiate(context,session);
			Object rows[][] =model.getRentAmt(hr,request);
			request.setAttribute("rows",rows);
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
			return "success";
	}
	
	
	
	/**
	 * following function is called when the employee is selected from the pop up window
	 * @return
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		
		String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')," +
				"	NVL(RANK_NAME,' '),NVL(CENTER_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  	" +
				"LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	" +
				"LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ";
				query+=getprofileQuery(hr);
				query+=" ORDER BY EMP_ID  ";;
				//+" LEFT JOIN HRMS_HOUSERENT_HDR ON(HRMS_HOUSERENT_HDR.RENT_EMPID=HRMS_EMP_OFFC.EMP_ID)  ORDER BY EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
				String[] headers = { getMessage("employee.id"),
						getMessage("employee"), getMessage("designation"),
						getMessage("branch") };		String[] headerWidth={"10", "30","30","30"};
		
		
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"empTokenNo","empName","designation","center","empid"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
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
	
		
	
	
	
	
	

}
