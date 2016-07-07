/**
 * 
 */
package org.struts.action.TravelManagement.Config;

import org.paradyne.bean.TravelManagement.Config.TravelPolicy;
import org.paradyne.model.TravelManagement.Config.TravelPolicyModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0417
 *
 */
public class TravelPolicyAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	TravelPolicy trplc=null;
	public void prepare_local() throws Exception {
		trplc = new TravelPolicy();
	 	trplc.setMenuCode(771);   
	 //	callonload();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return trplc;
	}

	public TravelPolicy getTrplc() {
		return trplc;
	}

	public void setTrplc(TravelPolicy trplc) {
		this.trplc = trplc;
	}
	
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		//callonload();
	}
	
	public String input() throws Exception { 
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session);
        model.callSearchDtl(trplc);
        model.terminate();  
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String callonload()
	{
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session);
        model.callOnlod(trplc);
        model.terminate();
		return "success";
	}
	
	public String firstNext()
	{  
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session);
		if(trplc.getSearchFlag().equals("true"))
		{
			System.out.println("in search=====first======= ");
			model.savedGradeDetails(trplc); 
		}else{ 
			model.callEmpGrade(trplc); 
		} 
		model.callSecondPrevious(trplc,request);
		model.callForExTrMode(trplc,request); 
		model.terminate();
		if(trplc.getEditFlag().equals("true"))
		{ 
			getNavigationPanel(6);
		}else{
			getNavigationPanel(3);
		} 
		return "saveSecond";
	}
	
	public String previousSecond()
	{    
		System.out.println("in Previous Second====== ");
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session); 
		model.callSecondPrevious(trplc,request);
		model.callForExTrMode(trplc,request);
		model.terminate();
		if(trplc.getEditFlag().equals("true"))
		{ 
			getNavigationPanel(5);
		}else{
			getNavigationPanel(2);
		} 
	    
		return "success";
	}
	
	public String secondNext()
	{  
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session); 
		if(trplc.getSearchFlag().equals("true"))
		{ System.out.println("in search======second====== ");
			 model.savedExpTravelDetails(trplc);      
		}else
		{
			if(trplc.getExpTravelFlag().equals("false"))  {
			 	 model.callForSecondSett(trplc);     }
			else{
				model.callForExTrMode(trplc,request);
				} 
		}
		model.callSecondPrevious(trplc,request); 
		model.terminate(); 
		if(trplc.getEditFlag().equals("true")){
			getNavigationPanel(7);
		}else{
			getNavigationPanel(4);
		} 
		return "saveThird";
	} 
	
	public String previousThird()
	{   
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session); 
		trplc.setExpTravelFlag("true");
		model.callSecondPrevious(trplc,request);
		model.callForExTrMode(trplc,request);
		model.terminate();
		if(trplc.getEditFlag().equals("true")){
			getNavigationPanel(6);
		}else{
			   getNavigationPanel(3);
		}  
		return "saveSecond";
	}
	
	public String addNew()
	{    trplc.setSearchFlag("false");
		 getNavigationPanel(2);
		return "success";
	}
	
	public String cancel()
	{
	TravelPolicyModel model = new TravelPolicyModel();
	model.initiate(context, session);
    model.callSearchDtl(trplc);
    model.terminate();  
    reset();
	getNavigationPanel(1);
	return INPUT;
	}
	
	public String save()
	{ 
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session);
		if(trplc.getSearchFlag().equals("false"))
		{   boolean flag=model.save(trplc,request);
			if(flag)
			{
				addActionMessage("Record saved successfully."); 
			}
		}else
		{      boolean flag=model.update(trplc,request);
			if(flag)
			{
				addActionMessage("Record updated successfully."); 
			}
		}
		trplc.setSearchFlag("true");
		secondNext();
		model.terminate();
		getNavigationPanel(7);
		return "saveThird";
	}
	
	public String edit()
	{  
	    TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session);
		model.savedPolicyDetails(trplc);
		model.terminate();
		System.out.println("trplc.getEditFlag() ========== "+trplc.getEditFlag());
		if(trplc.getEditFlag().equals("true"))
		{ getNavigationPanel(5);
		}else{
		getNavigationPanel(2);
		}
		return "success";
	}
	public void reset()
	{
		trplc.setPolicyId("");
		trplc.setPolicyName("");
		trplc.setPolicySysDate("");
		trplc.setPolicyAbbr("");
		trplc.setTravelGrade("");
		trplc.setTravelGradeId("");
		trplc.setEffectDate("");
		trplc.setSettleDays("");
		trplc.setDesc("");
		trplc.setExpCateTravel("");
		trplc.setExpCateTravelId("");
		trplc.setExpCateHotelId("");
		trplc.setExpCateHotel("");
		trplc.setGuidLines("");
		trplc.setStatus("A"); 
	}
	
	public String delete() throws Exception
	{  
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session);
		boolean flag=model.callDelete(trplc);
		if(flag)
		{
		addActionMessage("Record deleted successfully.");	
		}else{
			addActionMessage("Record cannot be deleted \n as they are associated with some other records");
		}
		model.terminate();
		input();
		reset();
		getNavigationPanel(1);
		return "input";
	}
	
	public String f9Details()
	{   
		TravelPolicyModel model = new TravelPolicyModel();
		model.initiate(context, session);
		model.callEdit(trplc,request);
		model.terminate();
		 getNavigationPanel(2);
		return "success";
	}
	
	
	public String f9action()
	{
		try
		{
			System.out.println("In F9Action-------------------+++++++++");
	String query = " SELECT POLICY_NAME,POLICY_ABBR, TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),DECODE(POLICY_STATUS,'A','Active','D','Deactive'),"  
			+" POLICY_STATUS , POLICY_ID  FROM HRMS_TMS_TRAVEL_POLICY  "
			+" ORDER BY POLICY_ID,POLICY_NAME,POLICY_ABBR,POLICY_EFFECTIVE_DATE ";
							 
			
			String[] headers = {getMessage("policy.name") ,getMessage("abbreviation"),getMessage("effective.date"),getMessage("searchStatus")};

			String[] headerWidth = {"40","20","15","15"};

			String[] fieldNames = {"policyName","policyAbbr","effectDate","status","status","policyId"};

			int[] columnIndex = {0,1,2,3,4,5};   

			String submitFlag = "true";

			String submitToMethod = "TravelPolicy_edit.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 			
			return null;
		}
	}  // end of f9Journey
	
	
	public String f9TravelGrade()
	{
		try
		{
			String query = "  SELECT  TRAVEL_GRADE_NAME,  TRAVEL_GRADE_ID  FROM HRMS_TMS_TRAVEL_GRADE "
                          +"  WHERE TRAVEL_GRADE_STATUS ='A' ORDER BY TRAVEL_GRADE_NAME  ";
			 
			
			String[] headers = {getMessage("travel.grade.name")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"travelGrade","travelGradeId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 			
			return null;
		}
	}  // end of f9Journey
	 
	
	
	public String f9ExpenseCatTravel()
	{
		try
		{
			String query = " SELECT  EXP_CATEGORY_NAME, DECODE(EXP_CATEGORY_UNIT,'D','Per Day','T','Per Tour','K','Per Kilometer'), EXP_CATEGORY_ID "
					     +" FROM HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_STATUS  ='A' ORDER BY EXP_CATEGORY_NAME  ";
			 
			
			String[] headers = {getMessage("expense.category.name"),getMessage("expense.category.unit")};

			String[] headerWidth = {"80","20"};

			String[] fieldNames = {"expCateTravel","expCateTravelUnit","expCateTravelId"};

			int[] columnIndex = {0,1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 			
			return null;
		}
	}  // end of f9Journey
	
	public String f9ExpHotel()
	{
		try
		{
			String query = " SELECT  EXP_CATEGORY_NAME, DECODE(EXP_CATEGORY_UNIT,'D','Per Day','T','Per Tour','K','Per Kilometer'), EXP_CATEGORY_ID "
					     +" FROM HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_STATUS  ='A' ORDER BY EXP_CATEGORY_NAME  ";
			 
			
			String[] headers = {getMessage("expense.category1"),getMessage("expense.unit1")};

			String[] headerWidth = {"80","20"};

			String[] fieldNames = {"expCateHotel","expCateHotelUnit","expCateHotelId"};
			    
			int[] columnIndex = {0,1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 			
			return null;
		}
	}  // end of f9Journey



}
