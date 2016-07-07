package org.struts.action.CR;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.paradyne.bean.CR.DecisionOneOTBudget;
import org.paradyne.model.CR.DataReconcileModel;
import org.paradyne.model.CR.DecisionOneOTBudgetModel;
import org.struts.lib.ParaActionSupport;


/**
 * @Date July 19, 2013
 */
public class DecisionOneOTBudgetAction extends ParaActionSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DecisionOneOTBudget bean = null;

  static Logger logger = Logger.getLogger(ParaActionSupport.class);

  public DecisionOneOTBudget getBean()
  {
    return this.bean;
  }

  public void setTm(DecisionOneOTBudget bean) {
    this.bean = bean;
  }

  public Object getModel()
  {
    return this.bean;
  }

 /**
  *  @purpose:The first method called when form is loaded
  *  @return String "SUCCESS"
  */ 
  public String input()
    throws Exception
  {
<<<<<<< DecisionOneOTBudgetAction.java
	  String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/DecisionOneOTBudget_input.action";
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);
=======
		String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/DecisionOneOTBudget_input.action";
		response.sendRedirect(D1_URL);
>>>>>>> 1.3
	    return INPUT;
  }

 

  /* (non-Javadoc)
 * @see org.struts.lib.ParaActionSupport#prepare_local()
 */
public void prepare_local() throws Exception {
    this.bean = new DecisionOneOTBudget();
   /// this.bean.setMenuCode(5051);//5051 menu code is set
  }


	/**
	 * @return
	 */
	public String callOTDetails()
		{
		try{
			DecisionOneOTBudgetModel model=new DecisionOneOTBudgetModel();
			model.initiate(this.context, this.session);
			String yearMonth=request.getParameter("yearmonth");
			request.setAttribute("yearMonth", yearMonth);
			
			String queryParam="?param=1";
			queryParam+="&yearMonth="+yearMonth+"&otMonth="+this.bean.getOtMonth()+"&otYear="+this.bean.getOtYear();
			
			//queryParam+="&searchMessage="+searchMessage;
			//getNavigationPanel(1);
			String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/DecisionOneOTBudget_callOTDetails.action"+queryParam;
			System.out.println(D1_URL);
			response.sendRedirect(D1_URL);
			
			
			/*this.bean.setYearMonth(yearMonth);
			model.checkData(this.bean);
			model.getbudgetDetailforMonthYear(this.bean);
//			model.setlatest(this.bean);
			model.OTBudgetEmpList(bean,request);
			
			bean.setViewOTDtlFlag(true);
			bean.setBudgetAmt("");
			bean.setBudgetDaily("");
			bean.setBudgetWeekly("");
			bean.setBudgetPer("");
			bean.setOtPersonType("");
			bean.setRegionID("");
			bean.setPersonID("");
			bean.setRegionName("");
			bean.setReportingID("");*/
		
			model.terminate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
		return "success";
	}
	public String setLatestData(){
		try {
			DecisionOneOTBudgetModel model=new DecisionOneOTBudgetModel();
			model.initiate(this.context, this.session);
			model.setlatestData(this.bean,request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";		
		}
	
	
	
	
	
	public String saveTotalBugdetforMonth()throws Exception
	{
		DecisionOneOTBudgetModel model=new DecisionOneOTBudgetModel();
		model.initiate(this.context, this.session);
		String totalBudget=request.getParameter("total_budget");
		String noOfWorkingDays=request.getParameter("noWorkingDay");
	//	String yearMonth=request.getParameter("yearMonth");
		this.bean.setTotalBudget(totalBudget);
		this.bean.setNumberOfWorkingDays(noOfWorkingDays);
		//this.bean.setYearMonth(yearMonth);
		model.setTotalbudgetForMonthYear(this.bean,request);
		model.OTBudgetEmpList(this.bean, request);
		return "success";
	}
	
	public String calculateBudget(){
		String autoid=request.getParameter("autoid");
		this.bean.setAutoid(autoid);
		DecisionOneOTBudgetModel model=new DecisionOneOTBudgetModel();
		model.initiate(this.context, this.session);
		model.setBudgetAmount(this.bean,request);
		//model.OTBudgetEmpList(this.bean, request);
		return "success";
	}

	public String addOTBudget(){
		DecisionOneOTBudgetModel model=new DecisionOneOTBudgetModel();
		model.initiate(this.context, this.session);
		String autoID=request.getParameter("autoID");
		this.bean.setAutoid(autoID);
		model.addOTBudget(this.bean, request);
		return "success";
	}

	public String editBudget(){
		DecisionOneOTBudgetModel model=new DecisionOneOTBudgetModel();
		model.initiate(this.context, this.session);
		String budgetAutoID=request.getParameter("autoid");
		this.bean.setAutoid(budgetAutoID);
		model.editBudget(this.bean,request);
		return "success";
	}
	
	public String deleteBudget(){
		DecisionOneOTBudgetModel model=new DecisionOneOTBudgetModel();
		model.initiate(this.context, this.session);
		String autoid=request.getParameter("autoid");
		model.deleteBudgetRecords(this.bean,autoid);
		model.OTBudgetEmpList(this.bean, request);
		
		return "success";
	}
	
	
	public String f9RegionID()throws Exception{
		String query="select ot_region_id,ot_region_name from cr_otregion";
		 String[] headers = { "Region Id", "Region Name" };
	      String[] headerWidth = { "20", "80" };
	      String[] fieldNames = { "regionID", "regionName" };
	      int[] columnIndex = { 0, 1 };
	      String submitFlag = "false";
	      String submitToMethod = "";
	    /*  setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	        "false", "POOL_D1");*/
	      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	    		  submitFlag,submitToMethod, "POOL_D1");
		return "f9page";
	}
	
	public String f9ReportingID()throws Exception{
		String query="";
		if(this.bean.getOtPersonType().equalsIgnoreCase("M")){
			query="";	
		}
		else{
		 query="select person_id,person_name from cr_otmonthbudget " +
		 		"where monthyear=" +
		 		this.bean.getYearMonth() +
		 		" and region_id=" +
		 		this.bean.getRegionID()+
		 		"and person_type<>'S'";
		}
		  String[] headers = { "Reporting ID","Reporting Name" };
	      String[] headerWidth = { "20","80" };
	      String[] fieldNames = { "ReportingID","ReportingName" };
	      int[] columnIndex = { 0,1 };
	      String submitFlag = "false";
	      String submitToMethod = "";
	      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	    		  submitFlag,submitToMethod, "POOL_D1");
		
		return "f9page";
	}
	public String f9PersonID()throws Exception{
		String query="SELECT PERSON_ID,FIRST_NAME+' '+LAST_NAME Person_name " +
				" from cr_otdata union select supervisor_id,supervisor_first+' '+supervisor_last Person_name " +
				" from cr_otdata union select manager2_id,manager2_first+' '+manager2_last Person_name from cr_otdata ";
		String[] headers = { "Person Id", "Person Name" };
	      String[] headerWidth = { "20", "80" };
	      String[] fieldNames = { "PersonID", "PersonName" };
	      int[] columnIndex = { 0, 1 };
	      String submitFlag = "false";
	      String submitToMethod = "";
	      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	    		  submitFlag,submitToMethod, "POOL_D1");
		
		return "f9page";
	}
	
	

}