/**
 * 
 */
package org.struts.action.payroll;

import java.io.PrintWriter;

import org.paradyne.bean.payroll.CashArrears;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.payroll.CashArrearsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class CashArrearsAction extends ParaActionSupport {
	CashArrears bean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ModelBase.class);
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new CashArrears();
		bean.setMenuCode(669);
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public String calArrears()
	{
		try
		{
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			Object[][] rows = model.calArrears(bean);
			if(rows == null || rows.length < 1)
			{
				addActionMessage("No records found for processing");
				reset();
			}
			request.setAttribute("rows", rows);
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return SUCCESS;
		}
	}
	
	public String f9Search()
	{
		try {
			String query = " SELECT DIV_NAME,CENTER_NAME,DECODE(ARREARS_STATUS,'L','Locked','Pending'),ARREARS_CODE,DIV_ID,CENTER_ID "  			 
						  +" FROM HRMS_CASH_ARREARS_HDR "
						  +" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_CASH_ARREARS_HDR.ARREARS_DIVISION) "
						  +" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_CASH_ARREARS_HDR.ARREARS_BRANCH) ";

			String[] headers = {getMessage("division"),getMessage("branch"),getMessage("status")};

			String[] headerWidth = {"40","40","20"};

			String[] fieldNames = {"divName","brnName","status","arrCode","divCode","brnCode"};

			int[] columnIndex = {0,1,2,3,4,5};

			String submitFlag = "true";

			String submitToMethod = "CashArrears_viewRecords.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String saveArrears()
	{
		
		try {
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			Object c[][] = model.getCreditHeader(bean);
			String[] emp_id = request.getParameterValues("eId"); // EMPLOYEES ID
			String arrdays[] = request.getParameterValues("arrDays");
			String promCode[] = request.getParameterValues("promotionCode");
			String onHold[] = request.getParameterValues("empOnHoldFlag");
			String[][] rows = new String[emp_id.length][c.length];
			for (int i = 1; i <= emp_id.length; i++) {
				/**
				 * FOR GETTING CREDIT AND DEBIT FROM JSP
				 */
				rows[i-1] = request.getParameterValues(String.valueOf(i)); // WILL GET ALL CREDITS AND DEBIT
			}
			//SAVE METHOD CALL PASSING ALL EMPLOYEE ID, CREDIT,DEBIT, TOTAL, NETSAL, TOTAL, DAYS, MONTH, YEAR
			int record = model.save(rows, c, emp_id,arrdays, bean,promCode,onHold);
			bean.setEArrMonth(bean.getEArrMonth());
			bean.setMonthName(bean.getEArrMonth());
			if (record == 1)
			{
				reset();
				addActionMessage("Arrears Saved Successfully");
			}
			else if(record == 2)
			{
				reset();
				addActionMessage("Arrears Modified Successfully");
			}
			else
				addActionMessage("Error While saving Arrears");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			return SUCCESS;
		}
	}

	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"brnName", "brnCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public String f9Dept()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public String f9Div()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			 
			if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME) ";
			 
			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public String f9EmpType()
	{
		try
		{
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"typeName", "typeCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public String f9PayBill()
	{
		try
		{
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
			+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query +=getprofilePaybillQuery(bean);
			
			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillNo"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}		
	}
	
	public String viewRecords()
	{
		try {
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			Object [][] rows = model.showArrearsRecords(bean);
			request.setAttribute("rows", rows);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
	}
	
	public String lockArrears()
	{
		try {
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			Object creditCode[][]  = model.getCreditHeader(bean);
			String type = request.getParameter("type");
			String empId[] = request.getParameterValues("eId");
			String creditValue[][] = new String[empId.length][creditCode.length];
			logger.info("Credit Code length >>>>>>>>>>>>>>>>>> "+creditCode.length);
			for (int i = 0; i < creditValue.length; i++) {
				creditValue[i] = request.getParameterValues(""+(i+1));
				for (int j = 0; j < creditValue[0].length; j++) {
					logger.info("Value >>>>>>>>>>>>>>>>>>>>>>>>. "+creditValue[i][j]);
				}
			}
			logger.info("Credit Value Length ---------------- "+creditValue.length);
			model.lockArrears(bean,type,empId,creditValue,creditCode);
			if(type.equals("lock"))
				addActionMessage("Arrears locked Successfully");
			else
				addActionMessage("Arrears locked Successfully");
			viewRecords();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String reset() {
		try {
			bean.setArrCode("");
			bean.setFlag(false);
			bean.setStatus("");
			bean.setMonthRef("");
			bean.setMonthName("");
			bean.setEArrMonth("");
			bean.setProccessDate("");
			bean.setEArrYear("");
			bean.setEmpChkFlag("false");
			bean.setBrnCode("");
			bean.setBrnName("");
			bean.setDivCode("");
			bean.setDivName("");
			bean.setTypeCode("");
			bean.setTypeName("");
			bean.setPayBillNo("");
			bean.setPayBillName("");
			bean.setDeptCode("");
			bean.setDeptName("");
			bean.setPayinSalFlag("false");
			bean.setSavedFlag("false");
			return SUCCESS;
		} catch (Exception e) {
			return SUCCESS;
		}
	}
	
	public String delete()
	{
		try {
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			boolean flag = model.delete(bean);
			if(flag)
			{
				addActionMessage("Record Deleted Successfully");
				reset();
			}
			else
				addActionMessage("Record can't be Deleted");
			return SUCCESS;
		} catch (Exception e) {
			return SUCCESS;
		}
	}
	
	public String removeRecord()
	{
		try {
			String[] removeEmp = request.getParameterValues("chkEmp");
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			Object c[][] = model.getCreditHeader(bean);
			String[] emp_id = request.getParameterValues("eId"); // EMPLOYEES ID
			String[] emp_Token = request.getParameterValues("eToken");
			String[] emp_Name = request.getParameterValues("eName");
			String total_credit[] = request.getParameterValues("totCredit");
			String total_debit[] = request.getParameterValues("totDebit");
			String sal_Days[] = request.getParameterValues("salDays");
			String arrdays[] = request.getParameterValues("arrDays");
			String netPay[] = request.getParameterValues("totAmtPay");
			String forMonth[] = request.getParameterValues("hMonth");
			String forYear[] = request.getParameterValues("year");
			String promCode[] = request.getParameterValues("promotionCode");
			Object[][] rows = new Object[emp_id.length][c.length];
			for (int i = 1; i <= emp_id.length; i++) {
				/**
				 * FOR GETTING CREDIT AND DEBIT FROM JSP
				 */
				rows[i-1] = request.getParameterValues(String.valueOf(i)); // WILL GET ALL CREDITS AND DEBIT
			}
			String [] empId = new String[emp_id.length-removeEmp.length];
			String [] empToken = new String[emp_id.length-removeEmp.length];
			String [] empName = new String[emp_id.length-removeEmp.length];
			String [] totCredit = new String[emp_id.length-removeEmp.length];
			String [] totDebit = new String[emp_id.length-removeEmp.length];
			String [] salDays = new String[emp_id.length-removeEmp.length];
			String [] arr_Days = new String[emp_id.length-removeEmp.length];
			String [] net_pay = new String[emp_id.length-removeEmp.length];
			String [] for_month = new String[emp_id.length-removeEmp.length];
			String [] for_year = new String[emp_id.length-removeEmp.length];
			String [] prom_code = new String[emp_id.length-removeEmp.length];
			Object[][] rowsAmt = new Object[emp_id.length-removeEmp.length][c.length];
			int j=0;
			for (int i = 0; i < emp_id.length; i++) {
				boolean flag = true;
				if(removeEmp != null && removeEmp.length  > 0)
				{
					for (int k = 0; k < removeEmp.length; k++) {
						logger.info("Remove >>>>>>>>>>>> "+removeEmp[k]);
						if(i+1==Integer.parseInt(removeEmp[k]))
						{
							logger.info("I m in break");
							flag=false;
							break;
						}
					}
				}
				if(!flag)
					continue;
				else
				{
					if(j < empId.length)
					{
						empId[j] = emp_id[i];
						empToken[j] = emp_Token[i];
						empName[j] = emp_Name[i];
						totCredit[j] = total_credit[i];
						totDebit[j] = total_debit[i];
						salDays[j] = sal_Days[i];
						arr_Days[j] = arrdays[i];
						net_pay[j] = netPay[i];
						for_month[j] = forMonth[i];
						for_year[j] = forYear[i];
						prom_code[j] = promCode[i];
						rowsAmt[j] = rows[i];
						j++;
					}
				}
			}
	//		model.removeEmpRecord(bean,empId,empToken,empName,totCredit,totDebit,salDays,arr_Days,
		//						  net_pay,for_month,for_year,prom_code,rowsAmt,c,request);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
	}
	
	public String onHoldSave()throws Exception
	{
		try {
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			String onHoldVal[] = request.getParameterValues("chkEmp");
			String[] empId = new String[onHoldVal.length];
			String[] month = new String[onHoldVal.length];
			String[] year = new String[onHoldVal.length];
			String[] temp = null;
			String arrCode = bean.getArrCode();
			for (int i = 0; i < onHoldVal.length; i++) {
				temp = onHoldVal[i].split("&");
				empId[i] = temp[0];
				month[i] = temp[1];
				year[i] = temp[2];
			}
			model.onHoldSave(empId, month, year, arrCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String removeOnHoldSave()throws Exception
	{
		try {
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			String onHoldVal[] = request.getParameterValues("chkEmp");
			String[] empId = new String[onHoldVal.length];
			String[] month = new String[onHoldVal.length];
			String[] year = new String[onHoldVal.length];
			String[] temp = null;
			String arrCode = bean.getArrCode();
			for (int i = 0; i < onHoldVal.length; i++) {
				temp = onHoldVal[i].split("&");
				empId[i] = temp[0];
				month[i] = temp[1];
				year[i] = temp[2];
			}
			model.removeOnHoldSave(empId, month, year, arrCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String recalculate() throws Exception
	{
		try {
			CashArrearsModel model = new CashArrearsModel();
			model.initiate(context, session);
			String recalVal[] = request.getParameterValues("chkEmp");
			String recalEmpId[] = null;
			String promCode[] = null;
			String rowNo[] = null;
			String responseText = "";
			if (recalVal != null) {
				recalEmpId = new String[recalVal.length];
				promCode = new String[recalVal.length];
				rowNo = new String[recalVal.length];
				String temp[] = new String[5];
				for (int i = 0; i < recalVal.length; i++) {
					temp = recalVal[i].split("&");
					recalEmpId[i] = temp[0];
					promCode[i] = temp[1];
					rowNo[i] = temp[2];
				}
				Object recalObj[][] = model.recalculate(recalEmpId,promCode, bean);
				for (int i = 0; i < recalObj.length; i++) {
					responseText += rowNo[i] + "#";
					for (int j = 0; j < recalObj[0].length; j++) {
						if (j == (recalObj[0].length - 1)) {
							responseText = responseText
									+ String.valueOf(recalObj[i][j]);
						} else
							responseText = responseText
									+ String.valueOf(recalObj[i][j]) + "#";
					}
					if (i == (recalObj.length - 1)) {

					} else
						responseText = responseText + "@";
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responseText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void reportCashArrears()
	{
		CashArrearsModel model = new CashArrearsModel();
		model.initiate(context, session);
		model.reportCashArrears(bean,response);
		model.terminate();
	}
}
