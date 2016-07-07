package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.BranchHolidayMaster;
import org.paradyne.model.admin.master.BranchHolidayMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan
 * May 7, 2008
**/

/**
 * To view and apply the holidays branch wise
**/

public class BranchHolidayMasterAction extends ParaActionSupport
{
	BranchHolidayMaster bean;
	
	/**
	 * Popup window contains list of all branches 
	**/
	/**
	 * @return String f9page
	**/
	public String f9Branch()
	{
		try
		{
			String sqlQuery = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";
			
			String[] headers={getMessage("branch")};
			
			String[] headerWidth={"100"};
			
			String[] fieldNames={"brnName","brnCode"};
			
			int[] columnIndex = {0, 1};
			
			String submitFlag = "true";
			
			String submitToMethod = "BranchHolidayMaster_resetHolidays.action";
			
			bean.setMasterMenuCode(31);
			
			setF9Window(sqlQuery, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} //end of try-catch block
	}
	
	/**
	 * Overriding super class method
	**/
	/**
	 * @return java bean object
	**/
	public Object getModel()
	{
		return bean;
	}

	/**
	 * Overriding super class method
	**/
	public void prepare_local() throws Exception
	{
		bean = new BranchHolidayMaster();
		bean.setMenuCode(621);
	}

	/**
	 * Resets the fields
	**/
	/**
	 * @return String SUCCESS
	**/
	public String reset()
	{
		try
		{
			bean.setBrnCode("");
			bean.setBrnName("");
			bean.setYear("");
			bean.setHolidayList(null);
			bean.setListFlag(false);
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} //end of try-catch block
	}

	/**
	 * Resets the holiday list
	**/
	/**
	 * @return String SUCCESS
	**/
	public String resetHolidays()
	{
		try
		{
			bean.setHolidayList(null);
			bean.setListFlag(false);
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} //end of try-catch block
	}

	/**
	 * Saves the holidays
	**/
	/**
	 * @return String SUCCESS
	**/
	public String save()
	{
		try
		{
			BranchHolidayMasterModel model = new BranchHolidayMasterModel();
			model.initiate(context, session);
			String result = model.save(bean, request);
			if(!result.equals(""))
			{
				addActionMessage(result);
			} //end of if statement
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} //end of try-catch block
	}

	/**
	 * Shows the holidays
	**/
	/**
	 * @return String SUCCESS
	**/
	public String showHolidays()
	{
		try
		{
			BranchHolidayMasterModel model = new BranchHolidayMasterModel();
			model.initiate(context, session);
			String result = model.showHolidays(bean);
			if(!result.equals(""))
			{
				addActionMessage(result);
			} //end of if statement
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} //end of try-catch block
	}
}