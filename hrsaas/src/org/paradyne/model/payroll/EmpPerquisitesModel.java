package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.payroll.EmpPerquisites;
import org.paradyne.lib.ModelBase;

public class EmpPerquisitesModel extends ModelBase { 
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmpPerquisitesModel.class); 

	EmpPerquisites bean = null;

	public boolean addDebitData(Object[] tableData) {
		
		logger.info("addDebitData in model query no 1:" + getQuery(1));

		Object[][] bean = new Object[1][3];
        
		bean[0][0] = tableData[0];
		bean[0][1] = tableData[1];
		bean[0][2] = tableData[2];

		
		boolean result = getSqlModel().singleExecute(getQuery(1), bean);

		return result;
	}
	
	public Object[][] chkEmpId(String emp){
		
		logger.info(" model Checking Emp Id"+emp);
		
		return getSqlModel().getSingleResult(getQuery(5),new Object[]{emp});
		
		
		
	}

	public boolean updateDebitData(Object[] tableData) {
		Object[][] bean = new Object[1][3];

		bean[0][0] = tableData[1];
		bean[0][1] = tableData[0];
		bean[0][2] = tableData[2];

		logger.info("Updaring Debit Data: " + bean[0][0] + "\t:"
				+ bean[0][1] +"\t"+bean[0][2] + "\n");

		logger.info("Query No. " + getQuery(1));

		boolean result = getSqlModel().singleExecute(getQuery(2),bean);

		return result;
	}

	public boolean deleteDebitData(EmpPerquisites debit) {
	    	Object[][] bean = new Object[1][3];
	    	bean[0][0] = debit.getEmpId();
	    	bean[0][1] = debit.getFromYear();
	    	bean[0][2] = debit.getToYear();
          boolean result=false;
		try {
			result = getSqlModel().singleExecute(getQuery(3), bean);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public void showGeneralEmpData(EmpPerquisites bean, String empId)
	{
		 try {
			String empQuery = "SELECT   HRMS_EMP_OFFC.EMP_TOKEN  ,  "
					+ " HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, "
					+ " HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_RANK ON  "
					+ " (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					+ " left join HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + empId;
			Object empData[][] = getSqlModel().getSingleResult(empQuery);
			bean.setEmpToken(String.valueOf(empData[0][0]));
			bean.setEmpName(String.valueOf(empData[0][1]));
			bean.setEmpCenter(String.valueOf(empData[0][2]));
			bean.setEmpRank(String.valueOf(empData[0][3]));
			bean.setEmpId(String.valueOf(empData[0][4]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] getTableData() {

		Object[][] bean = getSqlModel().getSingleResult(getQuery(4),
				new Object[] {});

		logger.info("in model u r getTableData method Query 4:" + getQuery(4));

		logger.info("Rows Data :" + bean.length);

	/*	for (int i = 0; i < bean.length; i++) {

			logger.info("\t" + bean[0][0]+"\t"+bean[0][1]+"\t");

		}*/
		return bean;
	}
	
	public void getChkList(EmpPerquisites debit)
	{
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),new Object[][]{});
		ArrayList chk=new ArrayList();
		for(int i=0; i<data.length;i++)
		{
			EmpPerquisites bean=new EmpPerquisites();

			bean.setHeaderId(String.valueOf(data[i][0]));
			bean.setHeadName(String.valueOf(data[i][1]));
			 
			chk.add(bean);
		}
		debit.setArrayList(chk);
	}
	
	public void showEmp(EmpPerquisites bean, HttpServletRequest request, String empId, boolean editFlag){
		try {
			String query="";
			logger.info("################ EDIT FLAG ################"+editFlag);
			if(editFlag){
				query = "SELECT DISTINCT HRMS_PERQUISITE_HEAD.PERQ_NAME, DECODE(PERQ_PERIOD,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'), NVL(HRMS_EMP_PERQUISITE.PERQ_AMT,0) "
					+ " ,DECODE(HRMS_PERQUISITE_HEAD.PERQ_PERIOD,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_PERQUISITE_HEAD.PERQ_CODE "
					+ " FROM HRMS_EMP_PERQUISITE "
					+ " RIGHT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_EMP_PERQUISITE.PERQ_CODE  AND EMP_ID="+empId+")"
					+ " WHERE HRMS_EMP_PERQUISITE.FROM_YEAR ="+bean.getFromYear()
					+ " UNION ALL "
					+ " SELECT DISTINCT HRMS_PERQUISITE_HEAD.PERQ_NAME, DECODE(PERQ_PERIOD,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'), 0 "
					+ " ,DECODE(HRMS_PERQUISITE_HEAD.PERQ_PERIOD,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_PERQUISITE_HEAD.PERQ_CODE "
					+ " FROM HRMS_PERQUISITE_HEAD "
					+ " WHERE  HRMS_PERQUISITE_HEAD.PERQ_CODE NOT IN (SELECT HRMS_EMP_PERQUISITE.PERQ_CODE FROM HRMS_EMP_PERQUISITE WHERE EMP_ID="+empId+" AND  HRMS_EMP_PERQUISITE.FROM_YEAR ="+bean.getFromYear()+")" 
					+ " ORDER BY 4";
			}else{
				
				query = "SELECT DISTINCT HRMS_PERQUISITE_HEAD.PERQ_NAME, DECODE(PERQ_PERIOD,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'), NVL(PERQ_AMT,0)"
					+ " ,DECODE(PERQ_PERIOD,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_PERQUISITE_HEAD.PERQ_CODE "
					+ " FROM HRMS_EMP_PERQUISITE "
					+ " RIGHT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_EMP_PERQUISITE.PERQ_CODE  AND EMP_ID="
					+ empId + ")"
					+ " WHERE  PERQ_AMT>0 AND HRMS_EMP_PERQUISITE.FROM_YEAR ="+bean.getFromYear()+" AND HRMS_EMP_PERQUISITE.TO_YEAR ="+bean.getToYear()
					+ " ORDER BY ORD";
			}
			
			showIncrementHistory(bean, request, query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method fetches employee increment details
	 * @param bean 
	 * @param request
	 * @param query  
	 */
	
	public void showIncrementHistory(EmpPerquisites empPerq, HttpServletRequest request, String query) {
		try {
			Object salHeaderObj[][] = getSqlModel().getSingleResult(query);
			double monthAMT = 0.0;
			double annualSum = 0.0;
			if(salHeaderObj!=null && salHeaderObj.length >0){
				ArrayList innerList= new ArrayList();
				for (int i = 0; i < salHeaderObj.length; i++) {
					EmpPerquisites bean = new EmpPerquisites();
					bean.setPerqNameItt(String.valueOf(salHeaderObj[i][0]).toUpperCase());
					bean.setPerqPeriodItt(String.valueOf(salHeaderObj[i][1]));
					bean.setPerqAmountItt(String.valueOf(salHeaderObj[i][2]));
					bean.setPerqCodeItt(String.valueOf(salHeaderObj[i][4]));
					
					if(String.valueOf(salHeaderObj[i][1]).equals("Monthly")){
						monthAMT += Double.parseDouble(String.valueOf(salHeaderObj[i][2]));
						annualSum+=Double.parseDouble(String.valueOf(salHeaderObj[i][2]))*12;
					}
					else if(String.valueOf(salHeaderObj[i][1]).equals("Quarterly")){
						annualSum+=Double.parseDouble(String.valueOf(salHeaderObj[i][2]))*4;
					}
					else if(String.valueOf(salHeaderObj[i][1]).equals("Half Yearly")){
						annualSum+=Double.parseDouble(String.valueOf(salHeaderObj[i][2]))*2;
					}
					else if(String.valueOf(salHeaderObj[i][1]).equals("Annually")){
						annualSum+=Double.parseDouble(String.valueOf(salHeaderObj[i][2]));
					}
					innerList.add(bean);
				}
				empPerq.setSalHeaderList(innerList);
				empPerq.setTotalAmt(String.valueOf(monthAMT));
				empPerq.setAnnualAmt(String.valueOf(annualSum));
			}else{
				empPerq.setTotalAmt("");
				empPerq.setAnnualAmt("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		empPerq.setPerquisiteFlag(true);
	}
	
	
	public boolean addCreditData(EmpPerquisites perqbean, String empId, String fromYr, String toYr, String[] perqCode, String[] amount) {
		
		boolean addResult = false;
		boolean deleteResult = false;
		
		String deletePerqs = "DELETE FROM HRMS_EMP_PERQUISITE WHERE EMP_ID ="+empId+" AND FROM_YEAR = "+fromYr+" AND TO_YEAR="+toYr;
		deleteResult = getSqlModel().singleExecute(deletePerqs);
		
		if(deleteResult){
		Object[][] insertObject = null;	
			if(amount!=null && amount.length >0){
				insertObject = new Object [amount.length][5];
				for (int i = 0; i < insertObject.length; i++) {
					insertObject[i][0] = empId;
					insertObject[i][1] = String.valueOf(perqCode[i]);
					insertObject[i][2] = String.valueOf(amount[i]);;
					insertObject[i][3] = fromYr;
					insertObject[i][4] = toYr;
				}
			}
			
			if(insertObject !=null && insertObject.length > 0){
				addResult = getSqlModel().singleExecute(getQuery(1), insertObject);
			}
		}
		return 	addResult;	
	}
	
	/**
	 * This method fetches employee details by employeeId
	 * @param empPerq 
	 * @param employeeId
	 * @param editFlag  
	 */
	
	public void fetchEmployeeDetailsByEmployeeId(EmpPerquisites empPerq, HttpServletRequest request, String employeeId){
		try {
			
			String empDetailQuery = "SELECT HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, NVL(SALGRADE_TYPE,' '),"
					+ " SALGRADE_CODE, HRMS_EMP_OFFC.EMP_ID, HRMS_SALARY_MISC.GROSS_AMT,"
					+ " DECODE(HRMS_SALARY_MISC.SAL_EPF_FLAG,'Y','true','N','false'), NVL(SAL_ACCNO_REGULAR,' ')," 
					+ " NVL(SAL_PANNO,' '), NVL(DEPT_NAME,' '), DEPT_ID , TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),"
					+ " NVL(CADRE_NAME,' '), HRMS_EMP_OFFC.EMP_CADRE "    
					+ " FROM HRMS_EMP_OFFC "   
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"  
					+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"  
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)" 
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )"
					+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_EMP_OFFC.EMP_SAL_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE)"  
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"  
					+ " LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_SALARY_MISC.FORMULA_ID)"  
					+ " WHERE HRMS_EMP_OFFC.EMP_ID="+employeeId;
		
			Object empDetailObj[][] = getSqlModel().getSingleResult(empDetailQuery);
			
			if(empDetailObj!=null && empDetailObj.length>0){
				
				empPerq.setEmpCenter(String.valueOf(empDetailObj[0][0]));
				empPerq.setEmpRank(String.valueOf(empDetailObj[0][1]));
				empPerq.setEmpId(String.valueOf(empDetailObj[0][4]));
				empPerq.setEmpAccountNo(String.valueOf(empDetailObj[0][7]));
				empPerq.setEmpPanNo(String.valueOf(empDetailObj[0][8]));
				empPerq.setEmpDeptName(String.valueOf(empDetailObj[0][9]));
				empPerq.setEmpDeptId(String.valueOf(empDetailObj[0][11]));
				empPerq.setJoiningDate(String.valueOf(empDetailObj[0][11]));
				empPerq.setEmpGradeName(String.valueOf(empDetailObj[0][12]));
				empPerq.setEmpGradeId(String.valueOf(empDetailObj[0][13]));
				showEmp(empPerq, request, String.valueOf(empDetailObj[0][4]), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
