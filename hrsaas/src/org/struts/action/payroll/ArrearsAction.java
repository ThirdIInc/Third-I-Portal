/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.ArrearsBean;
import org.paradyne.model.payroll.ArrearsModel;


/**
 * @author MuzaffarS
 * 
 */
public class ArrearsAction extends org.struts.lib.ParaActionSupport {

	ArrearsBean bean;

	private static final long serialVersionUID = 1L;

	 public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		bean = new ArrearsBean();
		bean.setMenuCode(124);
	}

	public String process() {
		
		ArrearsModel model = new ArrearsModel();
		model.initiate(context,session);
		String pbId = request.getParameter("pbId");
		
		Object c[][]=model.getCreditHeader();
		Object d[][]=model.getDebitHeader();
		Object rows[][]=model.getSalary(bean);
		
		request.setAttribute("creditLength",c);
		request.setAttribute("debitLength",d);
		request.setAttribute("rows", rows);
		logger.info("the lenth of rows is rrrrrrooooowwwwwwsss"+rows.length);		
		model.terminate();
		return "success";
	}
	public String saveData()
	{
		try {
			
			ArrearsModel model = new ArrearsModel();
		model.initiate(context,session);
		Object c[][]=model.getCreditHeader();
		Object d[][]=model.getDebitHeader();
		String[] emp_id=request.getParameterValues("emp_id");
		String[] month=request.getParameterValues("mon");
		
		// the emp id coming here is 4
		String frm = bean.getFrmmonth();
		String tom = bean.getTomonth();
		String year=bean.getYear();
		int mons = Integer.parseInt(String.valueOf(tom))
				- Integer.parseInt(String.valueOf(frm));
		logger.info("the value of c"+c.length);
		String total_credit[]=new String [emp_id.length];
		String total_creditDue[]=new String [emp_id.length];
		String total_debit[]=new String [emp_id.length];
		String total_debitDue[]=new String [emp_id.length];
		//setting the length of total credit
		try
		{
			for(int i=0;i<emp_id.length;i++)
			{
				
				total_credit[i]=request.getParameter("totalCredit"+i);
				total_creditDue[i]=request.getParameter("totalCredDue"+i);
				total_debit[i]=request.getParameter("totalDebit"+i);
				total_debitDue[i]=request.getParameter("totalDebitDue"+i);
				logger.info("---------------********************%%%%%%%%%%%%%%%%%%%^^^^^^^^^^^^^^"+String.valueOf(emp_id[i]));				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Object [][] rows=new Object[emp_id.length][2*c.length+2*d.length]; 
		
		for(int i=0;i<emp_id.length;i++)
		{
			//logger.info(emp_id[i]);
			rows[i]=request.getParameterValues(String.valueOf(i));
			
		}
		 model.save( rows , c , emp_id, year, total_credit , total_creditDue ,bean ,month,total_debit,total_debitDue,d);
	   
		model.terminate();
	} catch (Exception e) {
		logger.info("Error in Non Industrial action save "+e);
	}
	bean.setEmpType("");
	return "success";
	}
	
	public String view() {
		ArrearsModel model = new ArrearsModel();
		model.initiate(context,session);
		Object c[][]=model.getCreditHeader();
		Object d[][]=model.getDebitHeader();
		Object rows[][]=model.getViewArr(bean);
		request.setAttribute("creditLength",c);
		request.setAttribute("debitLength",d);
		request.setAttribute("rows", rows);
		model.terminate();
		
		return "success";
		
	}
	public String report() throws Exception
	{	
		logger.info("into reoport ppppppppppppppppppppppppppptttttttttttttttttttttttttttttt");
		ArrearsModel model = new ArrearsModel();
		model.initiate(context,session);
		try{
		model.generateReport(bean,response);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.terminate();
		return null;
	}

	public String f9Group() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
						+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
		
		query += getprofilePaybillQuery(bean);
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "PAY BILL NAME","PAY BILL NO" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "80","20" };

		String[] fieldName = { "pbId", "pbgrp" };

		String submitFlag = "false";
		String submitToMethod = "NdaAttend_setList.action";

		int[] columnIndex = { 0, 1 };

		setF9Window(query, headers, headerWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Emp() throws Exception {

		String query = " SELECT PAYARR_TO_YEAR , PAYARR_FROM_MONTH , PAYARR_TO_MONTH , PAYARR_PAYBILL FROM HRMS_PAY_ARREAR_HDR ";

		String[] headers = { "YEAR" ,"FROM MONTH" ,"TO MONTH" ,"PAYBILL ID " };

		String[] headerWidth = { "20", "20","30","30" };

		String[] fieldNames = { "year", "frmmonth" ,"tomonth","pbId" };

		int[] columnIndex = { 0, 1 ,2, 3};

		String submitFlag = "true";

		String submitToMethod = "Arrears_viewArr.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public ArrearsBean getBean() {
		return bean;
	}

}
