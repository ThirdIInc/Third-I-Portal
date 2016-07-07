package org.struts.action.payroll;

import org.paradyne.bean.payroll.NdaAttend;
import org.paradyne.model.payroll.NdaAttendModel;


public class NdaAttendAction extends org.struts.lib.ParaActionSupport {

	private static final long serialVersionUID = 1L;
	
     NdaAttend bean;
	 public Object getModel() {
	
		return bean;
	}
	
	public String setList ()
	{
		
		NdaAttendModel model=new NdaAttendModel();
		model.initiate(context,session);
		
		String pbId = request.getParameter("pbId");
		logger.info("the value of paybill id is"+pbId);
		boolean boo= model.getNdaRecord(bean,pbId);
		if(boo)
		{
		bean.setSavFlag("true");
		}else {
			addActionMessage("No Record to View");
		}
		model.terminate();
		return "success";
		
	}
	
	public String	report()
	{
		NdaAttendModel model=new NdaAttendModel();
		model.initiate(context,session);
		
		String pbId = request.getParameter("pbId");
		logger.info("the value of paybill id is"+pbId);
		model.getReport(bean,pbId,response);
		model.terminate();
		return null;
		
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		  bean=new NdaAttend();
		  bean.setMenuCode(136);
}
	public String save()throws Exception
	{
		NdaAttendModel model=new NdaAttendModel();
		model.initiate(context,session);
		String []empId = request.getParameterValues("empId");
		String pbId = request.getParameter("pbId");
		boolean result=false;
		String[]ndahrs = request.getParameterValues("ndaApprove");
		String[]emptoken = request.getParameterValues("empToken");
		String[]empname = request.getParameterValues("empName");
		//String[]shift = request.getParameterValues("shift");
		
		
		logger.info("the value of paybill id is"+pbId);
		if (empId!=null)
		{
		result=model.getNdaSave(bean,pbId,ndahrs,empId,emptoken,empname);
		}
		model.terminate();
		setList();
		if(result)
		{
			addActionMessage("Record Saved Successfully");
		}
		else
		{
			addActionMessage("Record can not be Saved ");
		}
		return "success";
	}
	
	
	//====================balaji============
	public String reset()
	{
		bean.setPbId("");
		bean.setNdaDate("");
		return "success";
	}
	//======================================
	
	public String f9action() throws Exception {
		String sql = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID "; 

		;

		String[] headers = { "Paybill Id", "Paybill Group"};

		String[] headersWidth = { "20", "20" };

		String[] fieldName = { "pbId", "pbgrp"};

		String submitFlag = "false";
		String submitToMethod = "NdaAttend_setList.action";

		int[] columnIndex = { 0, 1 };

		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}


}
