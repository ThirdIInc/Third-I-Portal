
package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.paradyne.bean.payroll.incometax.TdsCalculation;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.payroll.incometax.TdsCalculationModel;


import org.struts.lib.ParaActionSupport;

public class TdsCalculatorAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	TdsCalculation tdscal;
	
	public void prepare_local() throws Exception {
		tdscal=new TdsCalculation();
		tdscal.setMenuCode(565);
		// TODO Auto-generated method stub
	}
	 
	public Object getModel() {
		// TODO Auto-generated method stub
		return tdscal;
	}

	public TdsCalculation getTdscal() {
		return tdscal;
	}

	/**
	 * @param tdscal the tdscal to set
	 */
	public void setTdscal(TdsCalculation tdscal) {
		this.tdscal = tdscal;
	}
	/**
	 * method called onload, used to set the from year & to year byDefault
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			tdscal.setSource(source);
			
			TdsCalculationModel model = new TdsCalculationModel();
			model.initiate(context, session);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysDate = formater.format(date);
			String split[] = sysDate.split("/");
			int currentMonth = Integer.parseInt((split[1]));
			int year = Integer.parseInt((split[2]));
			if (currentMonth < 4) {
				tdscal.setCurrentYear(String.valueOf(year - 1));
				year = year - 1;
			} else {
				tdscal.setCurrentYear(String.valueOf(year));
			}
			double remianMonth = 0;
			double month = Double.parseDouble(split[1]);
			if (month > 3 && month <= 12) {
				remianMonth = 15 - month;
			}//end of if
			else if (month <= 3) {
				remianMonth = 3 - month;
			}//end of else if
			/**
			 * the value of remain month is set. This remain month is calculated from the current month to the end of 
			 * the financial year which is considered as March
			 */
			tdscal.setRemainMonthsHidden(String
					.valueOf(Math.round(remianMonth)));
			model.getFilters(tdscal);
			if (tdscal.isGeneralFlag()) {
				tdscal.setEmpID(tdscal.getUserEmpId());
				GenEmpRecord();
			}//end of if
			model.terminate();
			tdscal.setFromYear(String.valueOf(year));
			tdscal.setToYear(String.valueOf(year + 1));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * this method is called to retrieve the records for general employee
	 * @return
	 */
	public String GenEmpRecord(){
		TdsCalculationModel model = new TdsCalculationModel();
		model.initiate(context, session);
		tdscal=model.generalRecord(tdscal);
		model.terminate();
		return "success";
	}//end of if GenEmpRecord


	/**
	 * this method resets all the fields
	 * @return
	 */
	public String reset(){
		tdscal.setPayBillName("");
		tdscal.setDeptName("");
		tdscal.setDivisionName("");
		tdscal.setTypeName("");
		tdscal.setEmpName("");
		tdscal.setEmpCenter("");
		tdscal.setEmpRank("");
		tdscal.setEmpAge("");
		tdscal.setEmpGender("");
		tdscal.setEmpGrossSalList(null);
		tdscal.setEmpExemptInvList(null);
		tdscal.setEmpOtherInvList(null);
		tdscal.setEmpVIAInvList(null);
		tdscal.setEmpRebateInvList(null);
		tdscal.setEmpParaInvList(null);
		tdscal.setPTaxAmt("");
		tdscal.setTotalExemptInvAmt("");
		tdscal.setTotalGrossAmt("");
		tdscal.setTotalOtherInvAmt("");
		tdscal.setTotalParaInvAmt("");
		tdscal.setTotalRebateInvAmt("");
		tdscal.setTotalVIAInvAmt("");
		tdscal.setTotalNetInvAmt("");
		tdscal.setTotalTax("");
		tdscal.setEduCess("");
		tdscal.setTaxIncome("");
		tdscal.setTaxOnIncome("");
		tdscal.setTaxPaid("");
		tdscal.setTaxPerMon("");
		tdscal.setTaxPerMonth("");
		tdscal.setToYear("");
		tdscal.setFromYear("");
		tdscal.setRemainMonths("");
		tdscal.setSurCharge("");
		tdscal.setNetTax("");
		tdscal.setNetTaxableIncome("");
		tdscal.setEmpToken("");
		//GenEmpRecord();
		return "success";
	}//end of if reset
	
	
	/**
	 * This method is used to view the details of the calculator
	 */
	public String viewCalculator() throws Exception {
		TdsCalculationModel model=new TdsCalculationModel();
		model.initiate(context,session);
		
		Object[][]view = model.viewCalculator(tdscal,request);
		/**
		 * the respected message is displayed according to the value of object view
		 */
		if(String.valueOf(view[0][0]).equals("1")){
			addActionMessage("Tax Parameters Not Available");
		}//end of if
		if(String.valueOf(view[0][0]).equals("2")){
			addActionMessage("Tax Slabs Not Available");
		}//end of if
		if(String.valueOf(view[0][0]).equals("3")){
			addActionMessage("No Record's To View For The Financial Year Entered");
		}//end of if
		tdscal.setChkCalulateFlag(true);
		model.terminate();
		return SUCCESS;
	}//end of viewCalculator


	/**
	 * this method is used to display all the employee records
	 * @return
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		
		String sql = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN , "+
		" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+  
		" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,"+
		"  HRMS_EMP_OFFC.EMP_ID,EMP_GENDER,TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(EMP_DOB,'YYYY'),HRMS_EMP_OFFC.EMP_ID,HRMS_LOCATION.LOCATION_PARENT_CODE,TO_CHAR(EMP_REGULAR_DATE ,'DD-MM-YYYY')    FROM HRMS_EMP_OFFC  "+
		" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+ 
		" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+ 
		" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " +
		" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_LOCATION) " ;
		sql += getprofileQuery(tdscal);
		sql +="  ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| "+
		" HRMS_EMP_OFFC.EMP_LNAME ";
		
		String[] headers = { getMessage("employee.id"),
				getMessage("employee"),
				getMessage("branch"), getMessage("designation") };
		
		String[] headersWidth = {"20","20","20","40"};
		
		String[] fieldName = {"empToken","empName","empCenter","empRank","empID","empGender","empAge","empID","centerId","empJoinedDate"};
		String submitFlag = "false";
		
		int[] columnIndex = {0,1,2,3,4,5,6,7,8,9};
		String submitToMethod = "";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9action
}//end of class
