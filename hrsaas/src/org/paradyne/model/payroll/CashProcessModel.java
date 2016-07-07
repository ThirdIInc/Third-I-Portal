package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.NonIndustrialSalary;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


/**
 *
 * @author venkatesh
 *
 */
public class CashProcessModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);

	NonIndustrialSalary nonIndustrialSalary = null;

	// GET EMP_ID THOSE FULLFILL SORTING CRITERIA

	/**
	 * @param nonIndustrialSalary
	 * @return
	 */
	public Object[][] getEmpId(NonIndustrialSalary nonIndustrialSalary) {
		Object emp_id[][] = null;
		try {
			/*
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
			 *
			 */

			String selectSalary ="SELECT EMP_ID,EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC"
								+" WHERE EMP_STATUS='S' ";

			/*String selectSalary = " SELECT HRMS_SALARY_"+nonIndustrialSalary.getYear()+".EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME " +

			" FROM HRMS_SALARY_"+nonIndustrialSalary.getYear()+" inner join HRMS_EMP_OFFC  on HRMS_SALARY_"+nonIndustrialSalary.getYear()+" .EMP_ID = HRMS_EMP_OFFC.EMP_ID " +

			" WHERE SAL_LEDGER_CODE="+ nonIndustrialSalary.getAttCode();*/
			if(!nonIndustrialSalary.getBranchName().equals("")){
				selectSalary+=" AND EMP_CENTER="+nonIndustrialSalary.getBranchCode();
			}if(!nonIndustrialSalary.getDeptName().equals("")){
				selectSalary+=" AND EMP_DEPT="+nonIndustrialSalary.getDeptCode();
			}if(!nonIndustrialSalary.getPayBillName().equals("")){
				selectSalary+=" AND EMP_PAYBILL="+nonIndustrialSalary.getPayBillNo();
			}if(!nonIndustrialSalary.getTypeName().equals("")){
				selectSalary+=" AND EMP_TYPE="+nonIndustrialSalary.getTypeCode();
			}if(!nonIndustrialSalary.getDivisionName().equals("")){
				selectSalary+=" AND EMP_DIV="+nonIndustrialSalary.getDivisionCode();
			}
			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp_id;
	}



	/**
	 * @param nonIndustrialSalary
	 * @param cashCode
	 * @return
	 */
	public Object[][] getEmpId(NonIndustrialSalary nonIndustrialSalary,String cashCode) {
		Object emp_id[][] = null;
		try {
			/*
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
			 *
			 */

			String selectSalary ="SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC"
								+" INNER JOIN HRMS_CASH_DTL ON(HRMS_CASH_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
								+" WHERE EMP_STATUS='S' AND HRMS_CASH_DTL.CASH_CODE="+cashCode;

			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp_id;
	}


	// GET CREDIT CODE AND CREDIT ABBR

	public Object[][] getCreditHeader() {
		Object credit_header[][] = null;
		try {
			//UPDATED BY REEBA
			String selectCredit = "SELECT CREDIT_CODE,  TRIM(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD " +
							      " WHERE CREDIT_PERIODICITY='M' AND CREDIT_REIMBURSEMENT='Y' "
									//+ " AND CREDIT_PAYFLAG='N' "
									+ " ORDER BY CREDIT_CODE";
			/*
			 * FOR GETTING CREDIT CODE AND CREDIT ABBR WHICH USED FOR DISPLAYING
			 * AS NAME OF CREDIT ON SCREEN
			 *
			 */
			credit_header = getSqlModel().getSingleResult(selectCredit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credit_header;

	}

	// GET DEBIT CODE AND DEBIT ABBR
	public Object[][] getDebitHeader() {

		Object debit_header[][] = null;

		try {

			String selectDebit = "SELECT DEBIT_CODE,  trim(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";

			//FOR GETTING DEBIT CODE AND DEBIT ABBR WHICH USED FOR DISPLAYING
			// AS NAME OF DEBIT ON SCREEN

			debit_header = getSqlModel().getSingleResult(selectDebit);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit_header;

	}

	/*
	 *
	 * SET DEBIT HEADER AND CREDIT HEADER and get COMPLETE ROWS OF CREDIT AND
	 * DEBIT OF EMPLOYEES
	 */

	/**
	 * @param nonIndustrialSalary
	 * @param request
	 * @return
	 */
	public Object[][] getSalary(NonIndustrialSalary nonIndustrialSalary,long daysDiff,HttpServletRequest request) {

		Object credit_header[][] = getCreditHeader();
		request.setAttribute("creditLength",credit_header);

		Object emp_id[][] = getEmpId(nonIndustrialSalary);

		if(emp_id!=null && emp_id.length>0){
			
		

		ArrayList<NonIndustrialSalary> creditNames = new ArrayList<NonIndustrialSalary>();


		for (int i = 0; i < credit_header.length; i++) {
			/*
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 *
			 */
			NonIndustrialSalary creditName = new NonIndustrialSalary();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);
		}

		nonIndustrialSalary.setCreditHeader(creditNames);



		/*
		 * TOTAL LENGTH OF COULUM
		 */
		// int c = debit_header.length + credit_header.length;
		/*
		 * NO OF TOTAL ROWS IS R
		 */
		int r = emp_id.length;
		Object[][] rows = new Object[r][];

		for (int i = 0; i < emp_id.length; i++) {

			/*
			 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND SET EACH
			 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
			 * TOTAL NO OF EMPLOYEE
			 */

			Object[][] row = getRow(String.valueOf(emp_id[i][0]), String
					.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),
					 credit_header,daysDiff,nonIndustrialSalary);

			rows[i] = row[0];



		}

		return rows;
		
	}
	return null;

	}


	// GET CURRENT CREDIT CODE AND VALUE OF EMPLOYEE
	/**
	 * @param emp_id
	 * @param nonIndustrialSalary
	 * @return
	 */
	public Object[][] getCredit(String emp_id,NonIndustrialSalary nonIndustrialSalary) {
		Object[][] credit_amount = null;
		Object[][] credit_sal_amount = null;

		try {
			//UPDATED BY REEBA
			String selectCredits = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0)  FROM HRMS_CREDIT_HEAD "
								  +" LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID='"
								  + emp_id + "'  ) WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_REIMBURSEMENT='Y'"
								  //+ " AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='N' "
								  +" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";

			credit_amount = getSqlModel().getSingleResult(selectCredits);

		} catch (Exception e) {

		}



		if (credit_amount != null) {
			credit_sal_amount = new Object[credit_amount.length][2];

			for (int i = 0; i < credit_amount.length; i++) {
				credit_sal_amount[i][0] = credit_amount[i][0];

							try {
								credit_sal_amount[i][1] = Float
										.parseFloat(String
												.valueOf(credit_amount[i][1]));

							} catch (Exception e) {
								// TODO: handle exception
							}
					}
			}

		return credit_sal_amount;

	}


	/**
	 * @param emp_id
	 * @param emp_token
	 * @param emp_name
	 * @param creditLength
	 * @param nonIndustrialSalary
	 * @return
	 */
	public Object[][] getRow(String emp_id, String emp_token, String emp_name,
			Object creditLength[][],long daysDiff,
			NonIndustrialSalary nonIndustrialSalary) {

		// THIS IS ORIGINAL CREDIT AMOUNT ON WHICH MANUPILATION HAS TO DO
		Object[][] credit_amount = getCredit(emp_id,nonIndustrialSalary);

		Object[][] total_amount = null;
		float totalCredit = 0;


		int total_coulum = creditLength.length +  4;

		total_amount = new Object[1][total_coulum];

		// TO LIST EMP ID, EMP NAME, EMP TOKEN
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_token;
			total_amount[0][2] = emp_name;
		} catch (Exception e) {
			logger.info("error in emp data" + e);
		}

		try {
			int k = 0;
			int c = 0;
			for (int j = 0; j < total_coulum - 3; j++) {


					/*
					 * TO DISPLAY INDIVIDUAL CREDITS
					 *
					 */
				if (j < creditLength.length) {
					try {

						total_amount[0][j + 3] = "0";
						try {
							if (credit_amount[c][1] != null){
								/*
								 * FOR FILTERING NULL VALUES FROM DATA IF DATA
								 * IS NULL IT WILL TREATED AS O VALUES
								 */

								//CALCULATE CREDIT HEAD AMOUNT FOR A DAY
								double totalCrAmt=Double.parseDouble(""+credit_amount[c][1]);
								double crAmtPerDay=totalCrAmt/30;
								double totalCrPayable=Math.round(crAmtPerDay*daysDiff);
								//System.out.println("TOTAL="+totalCrAmt+",PER DAY="+crAmtPerDay+",PAYABLE="+totalCrPayable);
								//total_amount[0][j + 3] = credit_amount[c][1];
								total_amount[0][j + 3] = totalCrPayable;
							totalCredit = totalCredit + Float.parseFloat(String.valueOf(total_amount[0][j + 3]));
							}
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						logger.info("Error in if  loop which is credit ");
					}
				}
				else if (j == creditLength.length) {
					/*
					 * TO DISPALY TOTAL CREDIT WHEN ALL CREDIT PLACED ON THEIR
					 * POSITION THEN TOTAL CREDIT FIELD WILL BE FILLED //
					 *
					 */
					total_amount[0][j + 3] = totalCredit;
				}


			}

		} catch (Exception e) {
			logger.info("Error is nothing " + e);
		}
		logger.info("total credit" + totalCredit);

		return total_amount;
	}


/**
 * THIS METHOD RETRIEVES THE CASH PROCESS DETAILS OF AN EMPLOYEE FOR A CASH CODE
 * @param emp_id
 * @param bean
 * @param cashCode
 * @return
 */
Object [][]getEmpCredit(String emp_id,NonIndustrialSalary bean,String cashCode,Object creditMasterObj[][]){

	String sql=" SELECT CASH_CREDIT_CODE, NVL(CASH_AMOUNT,0) FROM HRMS_CASH_DTL "
		      +" LEFT JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_CASH_DTL.CASH_CREDIT_CODE)"
			  +" WHERE EMP_ID="+emp_id+" AND CASH_CODE="+cashCode
			  +" ORDER BY HRMS_CASH_DTL.CASH_CREDIT_CODE";
	Object data[][]=getSqlModel().getSingleResult(sql);
	data =compareObjWithMaster(creditMasterObj,data);
	return data;

}


	/**
	 * THIS METHOD RETRIEVES THE EXISTING CASH RECORDS OF AN EMPLOYEE FROM THE HRMS_CASH_DTL
	 * TABLE.
	 *
	 * @param emp_id
	 * @param emp_token
	 * @param emp_name
	 * @param creditLength
	 * @param nonIndustrialSalary
	 * @param cashCode
	 * @return
	 */
	public Object[][] getRow(String emp_id, String emp_token, String emp_name,
			Object creditLength[][],NonIndustrialSalary nonIndustrialSalary,String cashCode) {


		Object [][]totalAmount=null;
		float totalCredit = 0;
		int cols = creditLength.length +  4; //COLUMN COUNT
		totalAmount=new Object[1][cols];
		
		totalAmount[0][0] = emp_id;
		totalAmount[0][1] = emp_token;
		totalAmount[0][2] = emp_name;

		logger.info("COLS:"+cols);
		Object[][] credit_amount = getEmpCredit(emp_id,nonIndustrialSalary,cashCode,creditLength);
		int c=0;
		for(int j=0;j<cols-3;j++){

			if (j < creditLength.length) { //FOR CREDIT AMOUNT

				totalAmount[0][j + 3] = credit_amount[c][1];
				totalCredit+=Float.parseFloat(String.valueOf(totalAmount[0][j + 3]));
				c++;

			}else if (j == creditLength.length) { //FOR CREDIT TOTAL FOR THE ROW

				totalAmount[0][j + 3] = totalCredit;

			}

		}

		return totalAmount;

	}


	/**
	 * SAVE THE CURRENT CREDIT OF EMPLOYEE INTO SALARIED TABLE
	 * @param rows
	 * @param c
	 * @param emp_id
	 * @param total_credit
	 * @param bean
	 * @return
	 */
	public boolean save(Object[][] rows, Object[][] c,String[] emp_id,
			NonIndustrialSalary bean) {


			boolean insert = false;
			boolean update = false;

			String fields="";//QUERY FIELDS
			String values=""; //QUERY VALUES

			String SQL="INSERT INTO HRMS_CASH_HDR(CASH_CODE, CASH_FROMDATE, CASH_TODATE, ";

						if(!bean.getDivisionCode().equals("")){//IF DIVISION SELECTED
							fields+=" CASH_DIVISION,";
							values+=bean.getDivisionCode()+",";
						}if(!bean.getBranchCode().equals("")){//IF BRANCH SELECTED
							fields+=" CASH_BRANCH,";
							values+=bean.getBranchCode()+",";
						}if(!bean.getDeptCode().equals("")){//IF DEPARTMENT SELECTED
							fields+=" CASH_DEPT,";
							values+=bean.getDeptCode()+",";
						}if(!bean.getTypeCode().equals("")){//IF EMPLOYEE TYPE SELECTED
							fields+=" CASH_EMPTYPE,";
							values+=bean.getTypeCode()+",";
						}if(!bean.getPayBillNo().equals("")){//IF PAYBILL SELECTED
							fields+=" CASH_PAYBILL,";
							values+=bean.getPayBillNo()+",";
						}

					SQL+=fields+"CASH_STATUS) VALUES( (SELECT NVL(MAX(CASH_CODE),0)+1 FROM HRMS_CASH_HDR ),TO_DATE('"
						       +bean.getFrmDate()+"','DD-MM-YYYY'), TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY'),"
							   +values+"'S')";
		 try {
			 insert=getSqlModel().singleExecute(SQL);



			if(insert){ //IF RECORD INSERTED IN HRMS_CASH_HDR


				String maxSql=" SELECT MAX(CASH_CODE) FROM HRMS_CASH_HDR ";
				Object data[][]=getSqlModel().getSingleResult(maxSql);
				bean.setCashCode(""+data[0][0]);

				for (int i = 0; i < emp_id.length; i++) { //ITERATE THROUGH EMPLOYEES

						Object CreditData[][] = new Object[c.length][3];
						String insertCredit = " INSERT INTO HRMS_CASH_DTL(EMP_ID, CASH_CREDIT_CODE, CASH_AMOUNT, CASH_CODE)"
										    + " VALUES(?,?,?,"+data[0][0]+")";

						for (int j = 0; j < c.length ; j++) {//ITERATE THROUGH THE CREDIT CODE

								logger.info("for insert");
								CreditData[j][0] = emp_id[i]; // emp_id
								CreditData[j][1] = c[j][0]; // credit_code
								CreditData[j][2] = rows[i][j]; // amount

						}
						insert  = getSqlModel().singleExecute(insertCredit, CreditData);
				}

			}else{
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return insert;
	}


	/**
	 * THIS METHOD UPDATES THE CASH PROCESSS DETAILS RECORDS FOR A SPECIFIC PROCESS
	 * @param rows
	 * @param c
	 * @param emp_id
	 * @param bean
	 * @return
	 */
	public boolean update(Object[][] rows, Object[][] c,String[] emp_id,
			NonIndustrialSalary bean){

		boolean insert = false;

	 try {
			String deleteCredit="DELETE FROM HRMS_CASH_DTL WHERE CASH_CODE="+bean.getCashCode();
			boolean delete=getSqlModel().singleExecute(deleteCredit);

			if(delete){//IF RECORD DELEDTED

				for (int i = 0; i < emp_id.length; i++) { //ITERATE THROUGH EMPLOYEES

					Object CreditData[][] = new Object[c.length][3];
					String insertCredit = " INSERT INTO HRMS_CASH_DTL(EMP_ID, CASH_CREDIT_CODE, CASH_AMOUNT, CASH_CODE)"
									    + " VALUES(?,?,?,"+bean.getCashCode()+")";

					for (int j = 0; j < c.length ; j++) {//ITERATE THROUGH THE CREDIT CODE

							logger.info("for insert");
							CreditData[j][0] = emp_id[i]; // emp_id
							CreditData[j][1] = c[j][0]; // credit_code
							CreditData[j][2] = rows[i][j]; // amount

					}
					insert  = getSqlModel().singleExecute(insertCredit, CreditData);
				}

	 		}





	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	return insert;


	}


	/**
	 * @param rows
	 * @param c
	 * @param emp_id
	 * @param nonIndustrialSalary
	 * @return
	 */
	public boolean finalCash(Object[][] rows, Object[][] c,
			String[] emp_id,
			NonIndustrialSalary bean){

		boolean update=false;
		Object[][] cash_empid = null;

		if(!bean.getStatus().equals("Finalised")){ //IF PROCESSED NOT FINALISED YET

			for (int i = 0; i < emp_id.length; i++) {

				for (int j = 0; j < c.length ; j++) {

					String chkCashBal = "SELECT EMP_ID,CASH_BALANCE_AMT FROM HRMS_CASH_BALANCE WHERE EMP_ID="+emp_id[i]+" AND CASH_CREDIT_CODE="+c[j][0];
					cash_empid = getSqlModel().getSingleResult(chkCashBal);

					if(cash_empid==null || cash_empid.length<=0){//IF RECORD FOR A

						String insertCashBal = "INSERT INTO HRMS_CASH_BALANCE (EMP_ID,CASH_CREDIT_CODE,CASH_BALANCE_AMT) VALUES ("+emp_id[i]+","+c[j][0]+","+rows[i][j]+") ";
						update  = getSqlModel().singleExecute(insertCashBal);

					}else{

						float cashAmt= Float.parseFloat(String.valueOf(rows[i][j]))+Float.parseFloat(String.valueOf(cash_empid[0][1]));
						String updCashBal = "UPDATE HRMS_CASH_BALANCE SET CASH_BALANCE_AMT=CASH_BALANCE_AMT+"+rows[i][j]+" where " +
								"  emp_id="+emp_id[i]+" and CASH_CREDIT_CODE="+c[j][0];
						update  = getSqlModel().singleExecute(updCashBal);

					}

				}//loop for j ends


			}//loop for i ends

	}else{//PROCESS ALREADY FINALISED

		return false;

	}

		return update;
	}

	/**
	 * THIS METHOD UPDATES THE STATUS OF THE CASH PROCESS IN HRMS_CASH_HDR to 'F'-Finalise
	 * @param bean
	 * @return
	 */
	public boolean updateCashProcessStatus(NonIndustrialSalary bean){

		String updateCashPro="UPDATE HRMS_CASH_HDR SET CASH_STATUS='F' WHERE CASH_CODE="+bean.getCashCode();
		return getSqlModel().singleExecute(updateCashPro);
	}



	/**
	 * THIS METHOD CHECKS WHETHER THE A PROCESS IN THE GIVEN DATE RANGE, ALONG WITH
	 * THE FILTERS ALREADY EXISTS OR NOT. IT THUS RETURNS THE STATUS
	 * @param bean
	 * @return
	 */
	public String checkCashStatus(NonIndustrialSalary bean) {
		String lockQuery = "SELECT CASH_STATUS FROM HRMS_CASH_HDR WHERE  "
 	    +"( ( TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY') >=CASH_FROMDATE AND (TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')<=CASH_TODATE OR TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')>=CASH_TODATE) )"
 	   +" OR( TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY') <=CASH_FROMDATE AND (TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')<=CASH_TODATE OR TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')>=CASH_TODATE) )"
 	   +" OR( TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY') =CASH_FROMDATE AND TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')=CASH_TODATE  ) )";

		if(!bean.getBranchCode().equals("")){
			lockQuery = lockQuery + " AND CASH_BRANCH="+bean.getBranchCode();
		}
		if(!bean.getTypeCode().equals("")){
			lockQuery = lockQuery +" AND CASH_EMPTYPE="+bean.getTypeCode();
		}
		if(!bean.getPayBillNo().equals("")){
			lockQuery = lockQuery + " AND CASH_PAYBILL="+bean.getPayBillNo();
		}
		if(!bean.getDeptCode().equals("")){
			lockQuery = lockQuery +" AND CASH_DEPT="+bean.getDeptCode();
		}
		if(!bean.getDivisionCode().equals("")){
			lockQuery = lockQuery +" AND CASH_DIVISION="+bean.getDivisionCode();
		}

		String check = "";
		try {
			Object[][] result = getSqlModel().getSingleResult(lockQuery);

			if (result.length > 0) {
				check = String.valueOf(result[0][0]);

			}

		} catch (Exception e) {

		}
		return check;
	}



	/**
	 * @param nonIndustrialSalary
	 */
	public void getFilters(NonIndustrialSalary nonIndustrialSalary){
		try {
				String query="SELECT DECODE(CONF_BRN_FLAG,'Y','true','N','false'),DECODE(CONF_DEPT_FLAG,'Y','true','N','false'),DECODE(CONF_PAYBILL_FLAG,'Y','true','N','false')" +
						",DECODE(CONF_EMPTYPE_FLAG,'Y','true','N','false'),DECODE(CONF_DIVISION_FLAG,'Y','true','N','false') FROM HRMS_SALARY_CONF";
				Object[][] result = getSqlModel().getSingleResult(query);
				nonIndustrialSalary.setBranchFlag(String.valueOf(result[0][0]));
				nonIndustrialSalary.setDepartmentFlag(String.valueOf(result[0][1]));
				nonIndustrialSalary.setPaybillFlag(String.valueOf(result[0][2]));
				nonIndustrialSalary.setEmptypeFlag(String.valueOf(result[0][3]));
				nonIndustrialSalary.setDivisionFlag(String.valueOf(result[0][4]));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * THIS METHOD RETURNS FILTERS SELECTED DURING SPECIFIC TO THE PROCESS
	 */
	public void  setProcessFilters(NonIndustrialSalary  bean){

		String sql="SELECT NVL(TYPE_NAME,' '),NVL(PAYBILL_GROUP,' '),NVL(DEPT_NAME,' '),NVL(CENTER_NAME,' '),NVL(DIV_NAME,' ')"
					+" FROM HRMS_CASH_HDR"
					+" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_CASH_HDR.CASH_EMPTYPE)"
					+" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_CASH_HDR.CASH_PAYBILL)"
					+" LEFT  JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_CASH_HDR.CASH_DEPT)"
					+" LEFT  JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_CASH_HDR.CASH_BRANCH)"
					+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_CASH_HDR.CASH_DIVISION)"
					+" WHERE HRMS_CASH_HDR.CASH_CODE="+bean.getCashCode();
		Object data[][]=getSqlModel().getSingleResult(sql);
		if(data!=null && data.length>0){
			bean.setTypeName(""+data[0][0]);
			bean.setPayBillNo(""+data[0][1]);
			bean.setDeptName(""+data[0][2]);
			bean.setBranchName(""+data[0][3]);
			bean.setDivisionName(""+data[0][4]);
		}
		
		
	}
		
	
	
	/**
	 * @param bean
	 * @param request
	 * @return
	 */
	public Object [][] getCashProcessDetails(NonIndustrialSalary bean,HttpServletRequest request){

		Object creditHead[][]=getCreditHeader();//GET CREDIT HEADS
		Object empId[][]=getEmpId(bean,bean.getCashCode());//GET EMPLOYEE DETAILS BASED ON CASH PROCESS

		//SET THE CREDIT HEADERS ON THE ARRAYLIST
		ArrayList<NonIndustrialSalary> creditNames = new ArrayList<NonIndustrialSalary>();

		for (int i = 0; i < creditHead.length; i++) {
			/*
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 *
			 */
			NonIndustrialSalary creditName = new NonIndustrialSalary();
			creditName.setCreditCode(String.valueOf(creditHead[i][0]));
			creditName.setCreditName(String.valueOf(creditHead[i][1]));
			creditNames.add(creditName);
		}

		bean.setCreditHeader(creditNames);

		int r = empId.length;
		Object[][] rows = new Object[r][];

		for (int i = 0; i < empId.length; i++) {


			 /* getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND SET EACH
			 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
			 * TOTAL NO OF EMPLPYEE
			 */

			Object[][] row = getRow(String.valueOf(empId[i][0]), String
					.valueOf(empId[i][1]), String.valueOf(empId[i][2]),
					creditHead,nonIndustrialSalary,bean.getCashCode());

			rows[i] = row[0];


		}
		request.setAttribute("creditLength",creditHead);
		return rows;


	}

	public long dateDifferenceInDays(String stDate,String endDate){

		 /*Calendar calendar1 = Calendar.getInstance();
		    Calendar calendar2 = Calendar.getInstance();
		    calendar1.set(2007, 01, 10);
		    calendar2.set(2007, 07, 01);
		    long milliseconds1 = calendar1.getTimeInMillis();
		    long milliseconds2 = calendar2.getTimeInMillis();
		    long diff = milliseconds2 - milliseconds1;
		    long diffSeconds = diff / 1000;
		    long diffMinutes = diff / (60 * 1000);
		    long diffHours = diff / (60 * 60 * 1000);
		    long diffDays = diff / (24 * 60 * 60 * 1000);
		    System.out.println("\nThe Date Different Example");
		    System.out.println("Time in milliseconds: " + diff
		 + " milliseconds.");
		    System.out.println("Time in seconds: " + diffSeconds
		 + " seconds.");
		    System.out.println("Time in minutes: " + diffMinutes
		+ " minutes.");
		    System.out.println("Time in hours: " + diffHours
		+ " hours.");
		    System.out.println("Time in days: " + diffDays
		+ " days.");*/

		String sql="SELECT TO_DATE('"+endDate+"','DD-MM-YYYY')-TO_DATE('"+stDate+"','DD-MM-YYYY')+1 FROM DUAL";
		Object data[][]=getSqlModel().getSingleResult(sql);

		return Long.parseLong(data[0][0].toString());

	}
	 public Object[][]compareObjWithMaster(Object creditMasterObj[][],Object [][]empCreditObj){
		 Object [][]returnObj=new Object[creditMasterObj.length][2];
			int j = 0;
			try{
			for (int i = 0; i < returnObj.length; i++) {
				if(j<empCreditObj.length && String.valueOf(creditMasterObj[i][0]).equals(String.valueOf(empCreditObj[j][0]))){
					
						returnObj[i][0] = empCreditObj[j][0];
						returnObj[i][1] = empCreditObj[j++][1];
					
				}else{
					returnObj[i][0] = creditMasterObj[i][0];
					returnObj[i][1] = 0;
					
				}
			}
			}catch (Exception e) {
				return empCreditObj;
			}
		 
		 return returnObj;
	 }

}
