package org.paradyne.model.admin.master;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.master.BranchHolidayMaster;
import org.paradyne.lib.ModelBase;

/**
 * Bhushan
 * May 7, 2008
**/

/**
 * To define a business logic for viewing and applying holidays branch wise
**/

public class BranchHolidayMasterModel extends ModelBase
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	/**
	 * Saves the holidays
	**/
	/**
	 * @param bean
	 * @param request
	 * @return String as message whether records modified successfully or not.
	**/
	public String save(BranchHolidayMaster bean, HttpServletRequest request)
	{
		String result = "";
		boolean flag =false;
		try
		{
			String brnCode = bean.getBrnCode();			
			Object[] hDayDate = request.getParameterValues("hDayDate");
			Object[] applHDay = request.getParameterValues("applHDay");
			
			/**
			 * Delete already saved records
			**/
			
			String query = " DELETE FROM HRMS_HOLIDAY_BRANCH WHERE CENTER_ID="+brnCode
			+" and to_CHAR(HOLI_DATE,'YYYY')='"+bean.getYear().trim()+"' "; 
					 
			flag =getSqlModel().singleExecute(query);
			
			/**
			 * Save holiday records
			**/
			if(flag)
			{
				for(int i = 0; i < hDayDate.length; i++)
				{
					if(String.valueOf(applHDay[i]).equals("true"))
					{
						Object[][] param = new Object[1][3];
						param[0][0] = brnCode;
						param[0][1] = String.valueOf(hDayDate[i]);
						param[0][2] = String.valueOf("Y");
						getSqlModel().singleExecute(getQuery(3), param);
					} //end of if statement
				} //end of for loop
				result = "Records updated successfully.";
			}
			else{
				result = "Record can't be updated!";
			}
			
			showHolidays(bean);//show saved holidays
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			result = "Record can't be updated!";
		} //end of try-catch block
		return result;
	}
	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	/**
	 * Shows the holidays
	**/
	/**
	 * @return String SUCCESS
	**/
	public String showHolidays(BranchHolidayMaster bean)
	{
		String result = "";
		try
		{
			String brnCode = bean.getBrnCode();
			String year = bean.getYear();
			
			/**
			 * Get saved holidays
			**/
			Object[][] holidayObj = getSqlModel().getSingleResult(getQuery(1), new Object[] {brnCode, year});
			
			if(holidayObj.length < 1)
			{
				result = "Holidays Not Available!";
			} //end of if statement
			else
			{
				ArrayList<Object> holidayList = new ArrayList<Object>();
				int selHolidays = 0;
				for(int i = 0; i < holidayObj.length; i++)
				{ 
					BranchHolidayMaster bean1 = new BranchHolidayMaster();
					bean1.setHDayDate(checkNull(String.valueOf(holidayObj[i][0])));
					bean1.setOccasion(checkNull(String.valueOf(holidayObj[i][1])));
					if(String.valueOf(holidayObj[i][2]).equals("Y"))//Check previously holiday record is selected or not
					{
					    bean1.setApplHDay(true);
					    selHolidays++;
					} //end of if statement
					else
					{
					    bean1.setApplHDay(false);
					} //end of else statement
					holidayList.add(bean1);
				} //end of for loop
				bean.setHolidayList(holidayList);
				
				if(selHolidays == holidayObj.length)
				{
				    bean.setAllHolidays(true);
				} //end of if statement
				bean.setListFlag(true);
				result = "";
			} //end of else statement
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			result = "Holidays Not Available!";
		} //end of try-catch block
		return result;
	}
}