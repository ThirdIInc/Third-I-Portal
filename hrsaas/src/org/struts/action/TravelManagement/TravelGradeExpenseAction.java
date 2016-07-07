/**
 * Balaji Mane
 * Date 17-10-2008
 * purpose of this class is to save,modify and delete the gradewise expense of travel.
 */
package org.struts.action.TravelManagement;

import org.paradyne.bean.TravelManagement.TravelGradeExpense;
import org.paradyne.model.TravelManagement.TravelGradeExpenseModel;
import org.struts.lib.ParaActionSupport;

public class TravelGradeExpenseAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	 
	TravelGradeExpense trGrade = null;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		trGrade = new TravelGradeExpense();
		trGrade.setMenuCode(690); 
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return trGrade;
	}

	public TravelGradeExpense getTrGrade() {
		return trGrade;
	}

	public void setTrGrade(TravelGradeExpense trGrade) {
		this.trGrade = trGrade;
	}
	
	public String callOnLoad()
	{
		TravelGradeExpenseModel model = new TravelGradeExpenseModel();
		model.initiate(context, session);
		model.callOnLoad(trGrade);
		model.terminate();
		return "success";
	}
	
	public String save()
	{
		TravelGradeExpenseModel model = new TravelGradeExpenseModel();
		model.initiate(context, session);
		boolean flag = model.save(trGrade,request);
		if(flag)
		{
			addActionMessage("Record saved successfully.");
			reset();
		}else
		{
			addActionMessage("Record can't saved.");
			callOnLoad();
		}
		
		model.terminate();
		return "success";
	}
	
	public String delete()
	{
		TravelGradeExpenseModel model = new TravelGradeExpenseModel();
		model.initiate(context, session);
		boolean flag = model.delete(trGrade);
		if(flag)
		{
			addActionMessage("Record deleted successfully.");
			reset();
		}else
		{
			addActionMessage("Record can't deleted.");
			callOnLoad();
		}
		 
		model.terminate();
		return "success";
	}
	
	public String update()
	{
		TravelGradeExpenseModel model = new TravelGradeExpenseModel();
		model.initiate(context, session);
		boolean flag = model.update(trGrade,request);
		if(flag)
		{
			addActionMessage("Record updated successfully.");
			reset();
		}else
		{
			addActionMessage("Record can't updated.");
			callOnLoad();
		}
		
		model.terminate();
		return "success";
	}
	
	public String reset()
	{
		trGrade.setTrGradeId(""); 
		trGrade.setTravelOtherCost("");
		trGrade.setHotelOtherCost("");
		trGrade.setHotelPerDayCost("");
		trGrade.setGradeCode("");
		trGrade.setGradeName("");
		trGrade.setPocketCost("");
		trGrade.setFoodCost("");
		callOnLoad();
		return "success";
	}
	
	public String callF9dtl()
	{
		TravelGradeExpenseModel model = new TravelGradeExpenseModel();
		model.initiate(context, session);
		model.callF9dtl(trGrade);		 
		model.terminate();
		return "success";
	}
	
	public String report()
	{
		TravelGradeExpenseModel model = new TravelGradeExpenseModel();
		model.initiate(context, session);
		model.getReport(request, response,trGrade);		 
		model.terminate();
		return null;
	}
	
	
	/**
	 * @This method is used for show the Grade
	 */
	public String f9Grade()
	{
		try
		{
			String query = " SELECT DISTINCT CADRE_NAME ,CADRE_ID  FROM  HRMS_CADRE ORDER BY CADRE_NAME  ";
			 
			
			String[] headers = {"Grade Name"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"trGrade.gradeName","trGrade.gradeCode"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}  // end of f9Journey
	
	/**
	 * @This method is used for show the exsting saved record
	 */
	public String f9action()
	{
		try
		{
			String query = "SELECT CADRE_NAME ,TRAVEL_GRADE_OTHER_TRAVEL_COST,TRAVEL_GRADE_OTHER_HOTEL_COST, TRAVEL_GRADE_HOTEL_COST, "
						   +" TRAVEL_GRADE_CODE ,TRAVEL_GRADE_HDR_ID ,TRAVEL_GRADE_POCKET_COST, TRAVEL_GRADE_FOOD_COST FROM HRMS_TRAVEL_GRADE_HDR "
						   +" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_TRAVEL_GRADE_HDR.TRAVEL_GRADE_CODE)";
			 
			
			String[] headers = {"Grade Name","Travel Miscellaneous Expenses","Lodging allowance per Day","Lodging Miscellaneous allowance per day"};

			String[] headerWidth = {"25","25","25","25"};

			String[] fieldNames = {"trGrade.gradeName","trGrade.travelOtherCost","trGrade.hotelOtherCost","trGrade.hotelPerDayCost","trGrade.gradeCode" ,"trGrade.trGradeId","trGrade.pocketCost","trGrade.foodCost"};

			int[] columnIndex = {0,1,2,3,4,5,6,7};

			String submitFlag = "true";

			String submitToMethod = "TravelGradeExpense_callF9dtl.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}  // end of f9Journey
	

}
