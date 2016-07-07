package org.struts.action.payroll;

import java.util.ArrayList;
import java.util.Date;

 import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.srd.EmployeeCheckList;
import org.paradyne.bean.payroll.*;
import org.paradyne.lib.AuditTrail;
import org.paradyne.model.admin.srd.EmployeeCheckListModel;
import org.paradyne.model.payroll.EmpCreditModel;
import org.paradyne.model.payroll.EmpDebitModel;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;

import com.ibm.icu.util.Calendar;

public class EmpDebitAction extends ParaActionSupport {

	EmpDebit debit;
	
	AuditTrail trial;

	public EmpDebit getDebit() {
		return debit;
	}

	public void setDebit(EmpDebit debit) {
		this.debit = debit;
	}

	 public Object getModel() {

		return debit;
	}

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		debit = new EmpDebit();
		debit.setMenuCode(102);
		
	}
	
	public void prepare_withLoginProfileDetails()
	{
		if(debit.isGeneralFlag())
		{
			EmpDebitModel model = new EmpDebitModel();
			model.initiate(context, session);
			String empId = debit.getUserEmpId();
			model.showGeneralEmpData(debit,empId);
			empDetail();
			model.terminate();
			
		}
	}

	public String delete() {

		logger.info("Upadating debit detail records");

		EmpDebitModel model = new EmpDebitModel();
		model.initiate(context,session);

		trial = new AuditTrail(debit.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE * */
		trial.initiate(context, session);
		trial.setParameters("HRMS_EMP_DEBIT", new String[] { "EMP_ID" },
				new String[] { debit.getEmpId() }, debit.getEmpId());

		/** AUDIT TRAIL EXECUTION * */
		trial.executeDELTrail(request);
		
		boolean result = model.deleteDebitData(debit);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {

			addActionMessage("Cannot be Deleted");
		}
		empDetail();
		reset();
		return SUCCESS;
	}
	public String save() throws Exception {
		logger.info(" into the save method of ");
		EmpDebitModel model = new EmpDebitModel();
		model.initiate(context,session);
	
		String empId = debit.getEmpId();
		String qury="  SELECT DISTINCT NVL(DEBIT_AMT,0),HRMS_DEBIT_HEAD.DEBIT_CODE ,HRMS_DEBIT_HEAD.DEBIT_NAME , "+
		"  NVL(DEBIT_APPLICABLE,'Y') , EMP_ID	FROM HRMS_EMP_DEBIT "+    
		"  RIGHT JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_EMP_DEBIT.DEBIT_CODE "+
		"  AND EMP_ID="+empId+")  ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE ";
		Object amt[][]=model.getSqlModel().getSingleResult(qury);
		String[] amount = request.getParameterValues("amount");
		//String[] chkSubmit = request.getParameterValues("chkdata");
		
		

		for (int i = 0; i < amount.length; i++) {

			Object[] bean = new Object[4];
			/*if (String.valueOf("Y").equalsIgnoreCase(
					String.valueOf(chkSubmit[i]))) {
				logger.info("the value of Y   credit code:" + amt[i][1]);
				bean[0] = amt[i][1];//code debit
				bean[1] = String.valueOf("Y");//applicable
				bean[2] = amount[i];//amount
				bean[3] = empId;//empid
			} else {
				logger.info("the value of N   credit code:" + amt[i][1]);
				bean[0] = amt[i][1];
				bean[1] = String.valueOf("N");
				bean[2] = 0;
				bean[3] = empId;

			}*/
			if(amount[i].trim().equalsIgnoreCase(String.valueOf("")) || amount[i]==null)
			{
				amount[i]=String.valueOf("0");
			}
			
			bean[0] = amt[i][1];//code debit
			bean[1] = String.valueOf("Y");//applicable
			bean[2] = amount[i];//amount			
			bean[3] = empId;//empid
			
			logger.info("credit code " + bean[0] + "amount is" + bean[1]
					+ "emp id is" + bean[2]);

			model.addCreditData(bean,debit,request);			
			model.terminate();

		}
		
		/**
		 * Following code calculates the tax
		 * and updates tax process
		 */
		try {
			CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
			taxmodel.initiate(context, session);
			logger.info("I m calling tax calculation method");
			Object[][] empList = new Object[1][1];
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int fromYear = Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)));
			int month = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)));
			if(month <= 3)
				fromYear--;
			empList[0][0] = debit.getEmpId();
			if(empList != null && empList.length > 0)
				taxmodel.calculateTax(empList,String.valueOf(fromYear),String.valueOf(fromYear+1));
			taxmodel.terminate();
		} catch (Exception e) {
			logger.error("Exception in save() while calling calculateTax : "+e);

	}
		
	
		
		addActionMessage(getText("addMessage", ""));
		reset();
		return "success";

	}

	public String reset() {		 
		 debit.setEmpId("");
		 debit.setEmpDebit("");
		debit.setAmount("");
		debit.setAmountText("");
		debit.setEmpCenter("");
		debit.setChkBox("");
		debit.setDebitCode("");
		debit.setDepartment("");
		debit.setEmpId("");
		debit.setEmpName("");
		debit.setHeaderId("");
		debit.setHeadName("");
		debit.setEmpRank("");
		debit.setEmpToken("");
		debit.setTotalAmt("");
		debit.setPeriod("");
		debit.setAnnuallAmt("");
		debit.setAtt(new ArrayList<Object>());
		
		return "success";
	}
	public String empDetail() {
		EmpDebitModel model = new EmpDebitModel();
		
		Object addObj[] = new Object[1];
		model.initiate(context,session);
		String id = debit.getEmpId();
		logger.info("emp id ------------------------"
				+ debit.getEmpId());
		addObj[0] = debit.getEmpId();
		//String obj =id.substring(1,addObj.length-2);
		logger.info("the object is  " + id.length()+"last index"+id.lastIndexOf(2)+"objet substring");
		Object rows[][] = model.showEmp(debit, id, request);
		request.setAttribute("rows", rows);
		debit.setFlag("true");
		model.terminate();
		return "success";
	}

	public String f9actionEmpId() {

		String sql = "SELECT   HRMS_EMP_OFFC.EMP_TOKEN  ,  "+
		" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS NAME, "+ 
		" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, "+
		" decode(EMP_STATUS,'S','Service','N','Resigned','E','Terminated','R','Retired'),HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_RANK ON  "+
		" (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+
		" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+
		" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "+
		" left join HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  ";
		sql += getprofileQuery(debit);
		sql += "	ORDER BY NAME";
		

		String[] headers = {  getMessage("employee.id") , getMessage("employee"), getMessage("branch"),
				getMessage("designation"),getMessage("status") };

		String[] headersWidth = { "20", "30", "20", "20","20" };

		String[] fieldName = { "empToken" , 
				"empName", "empCenter", "empRank","empstatus", "empId" };

		String submitFlag = "true";
		
		int[] columnIndex = {0,1,2,3,4,5};
		String submitToMethod = "EmpDebit_empDetail.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}
