package org.struts.action.payroll;


import org.paradyne.bean.payroll.NdaCalculateBean;
import org.paradyne.model.payroll.NdaCalcModel;


public class NdaCalcAction extends org.struts.lib.ParaActionSupport{

	private static final long serialVersionUID = 1L;
	NdaCalculateBean bean;
	 public Object getModel() {
		
		return bean;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		bean=new NdaCalculateBean();
		bean.setMenuCode(137);
		
	}
	public String getRecord()
	{
		NdaCalcModel model=new NdaCalcModel();
		model.initiate(context,session);
		String pbId = request.getParameter("pbId");
	 boolean result= model.getrecord(bean,pbId);
	 bean.setPbgrp(bean.getPbgrp());
	 bean.setPbId(pbId);
	 if(result)
	 {
		 
	 }else
	 {
		 bean.setFlag("false");
		 addActionError("No Record Found");
	 }
		model.terminate();
		return "success";
		
	}
	public String view()
	{
		NdaCalcModel model=new NdaCalcModel();
		model.initiate(context,session);
		String pbId = request.getParameter("pbId");
		model.getview(bean,pbId);
		 bean.setPbgrp(bean.getPbgrp());
		 bean.setPbId(pbId);
		model.terminate();
		return "success";
	}
	public String save()
	{
		NdaCalcModel model=new NdaCalcModel();
		model.initiate(context,session);
		String[] empid = request.getParameterValues("empid");
		String[] hrs = request.getParameterValues("ndaHrs");
		String[] amount = request.getParameterValues("ndaAmount");
		boolean result=model.save(bean,empid,amount,hrs);
		if(result)
		{
			addActionMessage("Record Saved Sucessfully");
			bean.setFlag("true");
			view();
		}
		 bean.setPbgrp(bean.getPbgrp());
		model.terminate();
		return "success";
	}
	//======================balaji=========
	public String reset()
	{
	      bean.setMonth("");
	      bean.setYear("");
	      bean.setEmpType("");
	      bean.setPbgrp("");
		return "success";
	}
	//=======================================
	
	
	public String	report()
	{
		NdaCalcModel model=new NdaCalcModel();
		model.initiate(context,session);
		String pbId = request.getParameter("pbId");
		 model.getreport(bean,pbId,response);
		 bean.setPbgrp(bean.getPbgrp());
		 bean.setPbId(pbId);
		model.terminate();
		
		return null;
		
	}
	
	public String f9Group() throws Exception {
		
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
	

	public String f9Emp() throws Exception {
		
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE ";

		
		String[] headers = { "TYPE NAME" };

		
		String[] headerWidth = { "100" };

	

		String[] fieldNames = { "empType","srtEmp" };

		
		int[] columnIndex = { 0, 1 };

	
		String submitFlag = "false";

		
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}


}
