 package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.lsmp.djep.xjep.XJep;
import org.nfunk.jep.Node;
import org.paradyne.bean.payroll.EmpCredit;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author MuzaffarS
 *
 */
public class EmpCreditModel extends ModelBase {  
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	NumberFormat formatter = new DecimalFormat("#0.00");
	AuditTrail trial = null;
	
	public void showGeneralEmpData(EmpCredit bean, String empId) {
		try{
			
		
		String empQuery = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN , " 
						 +" HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "  
						 +" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,NVL(SALGRADE_TYPE,' ') ,SALGRADE_CODE, "
						 +" HRMS_EMP_OFFC.EMP_ID, "
						 +" HRMS_SALARY_MISC.FORMULA_ID, NVL(HRMS_FORMULABUILDER_HDR.FORMULA_NAME,' '), "
						 +" HRMS_SALARY_MISC.GROSS_AMT,DECODE(HRMS_SALARY_MISC.SAL_EPF_FLAG,'Y','true','N','false') "
						 +" FROM HRMS_EMP_OFFC  "
						 +" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) " 
						 +" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "  
						 +" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
						 +" LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_EMP_OFFC.EMP_SAL_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE) "
						 +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						 +" LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_SALARY_MISC.FORMULA_ID) "
						 +" where emp_id = "+empId;
		
		Object[][] empData = getSqlModel().getSingleResult(empQuery);
		bean.setEmpToken(String.valueOf(empData[0][0]));
		bean.setEmpName(String.valueOf(empData[0][1]));
		bean.setEmpCenter(String.valueOf(empData[0][2]));
		bean.setEmpRank(String.valueOf(empData[0][3]));
		bean.setGradeName(String.valueOf(empData[0][4]));
		bean.setGradeId(String.valueOf(empData[0][5]));
		bean.setEmpId(String.valueOf(empData[0][6]));
		bean.setFrmId(String.valueOf(empData[0][7]));
		bean.setFrmName(String.valueOf(empData[0][8]));
		bean.setGrsAmt(String.valueOf(empData[0][9]));
		bean.setPfFlag(String.valueOf(empData[0][10]));
		//pfMethod(bean, empId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void showEmp(EmpCredit empCredit, HttpServletRequest request, String empId, boolean editFlag){ 
		
		/*Fetching employees ctc, salary formula and salary grade*/
		
		String empctcQuery = "SELECT  NVL(HRMS_SALARY_MISC.CTC,0), HRMS_SALARY_MISC.FORMULA_ID, NVL(HRMS_FORMULABUILDER_HDR.FORMULA_NAME,' '), NVL(SALGRADE_TYPE,' '), HRMS_SALGRADE_HDR.SALGRADE_CODE, NVL(GROSS_AMT,0) "
			+ " FROM HRMS_EMP_OFFC "   
			+ " LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_EMP_OFFC.EMP_SAL_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE)"  
			+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " 
			+ " LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_SALARY_MISC.FORMULA_ID)"  
			+ " WHERE HRMS_EMP_OFFC.EMP_ID="
			+empId;
		
		Object empCtcObject[][]=getSqlModel().getSingleResult(empctcQuery);
		
		if(empCtcObject!=null && empCtcObject.length >0){
			empCredit.setCtcAmt(String.valueOf(empCtcObject[0][0]));
			empCredit.setFrmId(Utility.checkNull(String.valueOf(empCtcObject[0][1])));
			empCredit.setFrmName(Utility.checkNull(String.valueOf(empCtcObject[0][2])));
			empCredit.setGradeName(Utility.checkNull(String.valueOf(empCtcObject[0][3])));
			empCredit.setGradeId(Utility.checkNull(String.valueOf(empCtcObject[0][4])));
			empCredit.setGrsAmt(String.valueOf(empCtcObject[0][5]));
		}
		
		String query="";
		
		if(editFlag){
			query = "SELECT CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),NVL(CREDIT_AMT,0) , "
				+ " DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_EMP_CREDIT "  
				+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)" 
				+ " WHERE EMP_ID="+empId 
				+ " UNION ALL "
				+ " SELECT CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),0 , "
				+ " DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD " 
				+ " WHERE CREDIT_HEAD_TYPE='S' and CREDIT_CODE not in (select HRMS_EMP_CREDIT.CREDIT_CODE from HRMS_EMP_CREDIT where EMP_ID="+empId+")" 
				+ " ORDER BY 4,5";
			
			/*query = "SELECT  CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),NVL(CREDIT_AMT,0)"
					+" ,DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_EMP_CREDIT.CREDIT_CODE FROM HRMS_EMP_CREDIT "
					+" RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)"
					+" WHERE EMP_ID="+empId 
					+" ORDER BY ORD,HRMS_EMP_CREDIT.CREDIT_CODE";*/
		}else{
			logger.info("################ EDIT FLAG ################"+editFlag);
			
			query = "SELECT  CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),NVL(CREDIT_AMT,0)"
				+" ,DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_EMP_CREDIT.CREDIT_CODE FROM HRMS_EMP_CREDIT "
				+" RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)"
				+" WHERE EMP_ID="+empId +" and HRMS_EMP_CREDIT.CREDIT_AMT>0"
				+" ORDER BY ORD,HRMS_EMP_CREDIT.CREDIT_CODE";
		}
			showIncrementHistory(empCredit, request, query);
		
		//-----------------------------------------------
		ctcMethod(empCredit);
		fetchIncrementPeriod(empCredit);
				
	}

	public void ctcMethod(EmpCredit empCredit){
	try {
		double annualAmt=0;
		//pfMethod(empCredit, empCredit.getEmpId());
		String creditQuery = "SELECT  NVL(ROUND(SUM(CASE WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='M' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 12  WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='Q' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 4 WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='H' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 2 WHEN HRMS_CREDIT_HEAD.CREDIT_PERIODICITY ='A' THEN  NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) * 1 ELSE  0 END )),0)"
				+ " FROM HRMS_CREDIT_HEAD " 
				+ " LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE) "  
				+ " WHERE HRMS_EMP_CREDIT.EMP_ID="+empCredit.getEmpId()+" AND HRMS_CREDIT_HEAD .CREDIT_IS_CTC_COMPONENT='Y'";
		
		Object [][]creditAmtObj=getSqlModel().getSingleResult(creditQuery);
		
		annualAmt=Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(creditAmtObj[0][0]))));
		
		empCredit.setCtcAmt(formatter.format(annualAmt));
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
	public boolean addCredit(EmpCredit bean) {
		Object addObj[][] = new Object[1][8];

		addObj[0][0] = bean.getEmpCredit();
		addObj[0][1] = bean.getEmpId();
		addObj[0][2] = bean.getEmpAmount();
		logger.info("into model");
		return getSqlModel().singleExecute(getQuery(1), addObj);
	}

	public boolean modCredit(EmpCredit bean) {
		Object addObj[][] = new Object[1][3];
		
		addObj[0][0] = bean.getEmpAmount();
		addObj[0][1] = bean.getEmpId();
		addObj[0][2] = bean.getEmpCredit();
		boolean result= getSqlModel().singleExecute(getQuery(2), addObj);
	 		
 		return result;
 		
		
		
	}

	public boolean deleteCredit(EmpCredit bean) {
		String del=" DELETE FROM HRMS_EMP_CREDIT WHERE EMP_ID="+bean.getEmpId();
      boolean result=false;
	try {
		result = getSqlModel().singleExecute(del);
		//result1 = getSqlModel().singleExecute(getQuery(8), bean1);
	} catch (Exception e) {
		e.printStackTrace();
	}
		return (result);

	}

	public void getCreditRecord(EmpCredit bean) {
		Object addObj[] = new Object[1];

		addObj[0] = bean.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			logger.info("I am 2");
			bean.setEmpCredit(String.valueOf(data[i][0]));
			bean.setEmpName(String.valueOf(data[i][1]));
			bean.setEmpDepart(String.valueOf(data[i][2]));
			bean.setEmpCenter(String.valueOf(data[i][3]));
			bean.setEmpTrade(String.valueOf(data[i][4]));
			bean.setEmpRank(String.valueOf(data[i][5]));
			bean.setEmpAmount(String.valueOf(data[i][6]));

		}

	}

	public void getCreditReport(EmpCredit bean) {
		Object addObj[] = new Object[1];
		ArrayList<Object> dispList = new ArrayList();

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		for (int i = 0; i < data.length; i++) {
			EmpCredit bean1 = new EmpCredit();
			logger.info("I am 2");
			bean1.setEmpId(String.valueOf(data[i][0]));
			bean1.setEmpCredit(String.valueOf(data[i][1]));
			bean1.setEmpAmount(String.valueOf(data[i][2]));

			dispList.add(bean1);
		}
		bean.setEmpCreditArray(dispList);
	}

	public void getTableDetails(EmpCredit bean) {

		logger.info("GET TABLE DETAILS:");

		Object[] addObj = new Object[1];
		ArrayList list = new ArrayList();
		addObj[0] = bean.getEmpCredit();

		logger.info("Credit CODE ITERATion " + addObj[0]);

		Object[][] result = getSqlModel().getSingleResult(getQuery(7));
		for (int i = 0; i < result.length; i++) {
			EmpCredit bean1 = new EmpCredit();
			logger.info("I am 2");
			bean1.setSalHead(String.valueOf(result[i][0]));

			list.add(bean1);
		}

		logger.info("TABLE DATA SIZE:" + list.size());
		bean.setAtt(list); // setting array list
	}

	public void getChkList(EmpCredit bean) {
		String sqlSelect = "SELECT CREDIT_CODE ,CREDIT_NAME  FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		Object[][] data = getSqlModel().getSingleResult(sqlSelect,
				new Object[][] {});

		ArrayList<EmpCredit> atlist = new ArrayList<EmpCredit>();
		logger.info("into model ---------");
		for (int i = 0; i < data.length; i++) {
			EmpCredit bean1 = new EmpCredit();
			bean1.setChkId(String.valueOf(data[i][0]));
			logger.info("into mode data1" + String.valueOf(data[i][0]));
			bean1.setSalHead(String.valueOf(data[i][1]));
			logger.info("into mode data1" + String.valueOf(data[i][0]));
			bean1.setChkSubmit("");

			atlist.add(bean1);
		}
		bean.setAtt(atlist);
	}

	public Object[][] chkEmpId(String emp) {

		logger.info("Checking Emp Id");

		return getSqlModel().getSingleResult(getQuery(5), new Object[] { emp });

	}

	public void updateSalGrade(EmpCredit bean){
		Object[][] updateGrade=new Object[1][2];
		updateGrade[0][0]=bean.getGradeId();
		updateGrade[0][1]=bean.getEmpId();
		getSqlModel().singleExecute(getQuery(6), updateGrade);
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	
	public void updateFormula(EmpCredit bean){
		try {
			Object[][] updateObj = new Object[1][7];
			updateObj[0][0] = checkNull(bean.getFrmId()); //formula id
			updateObj[0][1] = checkNull(bean.getGrsAmt()); //gross amount
			updateObj[0][2] = checkNull(bean.getTotalamt()); //total monthly salary
			updateObj[0][3] = bean.getAnnualAmt(); //total annual salary
			updateObj[0][4] = bean.getAnnualAmtPer(); //total annual perquisite
			ctcMethod(bean);
			updateObj[0][5] = bean.getCtcAmt(); //ctc
			updateObj[0][6] = bean.getEmpId();//emp id
			getSqlModel().singleExecute(getQuery(9), updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String addCreditData(Object[] tableData, EmpCredit empCredit, HttpServletRequest request) {
		
		boolean result;
		String str=null;
		String qury=" SELECT HRMS_EMP_CREDIT.CREDIT_AMT FROM HRMS_EMP_CREDIT WHERE HRMS_EMP_CREDIT.CREDIT_CODE="+ tableData[0]+"  AND " +
		"  HRMS_EMP_CREDIT.EMP_ID ="+ tableData[3];
		Object amt[][]=getSqlModel().getSingleResult(qury);
		logger.info("FIRING QUERY");
	
		if(amt.length==0)
		{
			
		Object[][] bean = new Object[1][4];
		bean[0][0] = tableData[0];
		bean[0][1] = tableData[1];
		bean[0][2] = tableData[2];
		bean[0][3] = tableData[3];
	    result = getSqlModel().singleExecute(getQuery(1), bean);
	    if(result){  
		    trial = new AuditTrail(empCredit.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE * */
			trial.initiate(context, session);

			trial.setParameters("HRMS_EMP_CREDIT", new String[] { "CREDIT_CODE","EMP_ID"},
					new String[] { String.valueOf(tableData[0]),empCredit.getEmpId() }, empCredit.getEmpId());
			
			trial.setComments("CREDIT_CODE   :"+String.valueOf(tableData[0]));

			/** AUDIT TRAIL EXECUTION * */
			trial.executeADDTrail(request);
		  }
	    str="Record saved Successfully!";
		
		 
		}
		
		
		else
		{
			logger.info("===USER LOGIN CODE====" + empCredit.getUserEmpId());
			trial = new AuditTrail(empCredit.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session);
			trial.setParameters("HRMS_EMP_CREDIT", new String[] { "CREDIT_CODE","EMP_ID" },
					new String[] { String.valueOf(tableData[0]),empCredit.getEmpId() }, empCredit.getEmpId());
			trial.beginTrail();
			trial.setComments("CREDIT_CODE   :"+String.valueOf(tableData[0]));

		

			String upd="UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT="+tableData[2]+", CREDIT_APPLICABLE='"+tableData[1]+"' WHERE CREDIT_CODE="+tableData[0]+" AND EMP_ID="+tableData[3];
			
			
			logger.info(upd);
			 result = getSqlModel().singleExecute(upd);	

			 /**	AUDIT TRAIL	EXECUTION **/
							trial.executeMODTrail(request);
			 str="Record updated Successfully!";
			 
		}
		return str;
		
	}

	public void view(String empId,String []chkCode, EmpCredit bean) {
		String sqlSelect = " SELECT 	HRMS_EMP_CREDIT.CREDIT_CODE,"
				+ " HRMS_CREDIT_HEAD.CREDIT_NAME,"
				+ " HRMS_EMP_CREDIT.CREDIT_AMT,CREDIT_AMT_PRECOMMISSION"
				+ " FROM HRMS_EMP_CREDIT"
				+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_EMP_CREDIT.CREDIT_CODE	=	HRMS_CREDIT_HEAD.CREDIT_CODE)"
				+ " WHERE HRMS_EMP_CREDIT.EMP_ID =" + empId;
		Object[][] data = getSqlModel().getSingleResult(sqlSelect,
				new Object[][] {});

		ArrayList<EmpCredit> atlist = new ArrayList<EmpCredit>();
		logger.info("into model ---------");
		for (int i = 0; i < chkCode.length; i++) {
			EmpCredit bean1 = new EmpCredit();
			bean1.setChkId(String.valueOf(data[i][0]));
			logger.info("into mode data1" + String.valueOf(data[i][0]));
			bean1.setSalHead(String.valueOf(data[i][1]));
			logger.info("into mode data1" + String.valueOf(data[i][1]));
			bean1.setEmpAmount(String.valueOf(data[i][2]));
			bean1.setPreCommAmt(String.valueOf(data[0][3]));

			atlist.add(bean1);
		}
		bean.setAtt(atlist);

	}
	
	public String executeFormula(String sal_formula, double CTC, Object[][] tableData, ArrayList frmData) {

		try {
			
		String str[] = sal_formula.split("#");
		String frml = "";
		for (int i = 0; i < str.length; i++) {

			logger.info("Printing Str[i] " + str[i]);
			if (str[i].equals("CTC")) {
				logger.info("Inside CTC");
				frml += "" + CTC;
			}

			else {
				String flag = "false",strValue="";
				int cnt = 1;
				
				if(str[i].equals("")||str[i].equals("null")){
					strValue=str[i];
				}
				else if(str[i].length() == 1){
					strValue=str[i];
				}
				else if(str[i].contains("C"))
					strValue = str[i].substring(1,str[i].length());
				else
					strValue=str[i];
				
				for (int z = 0; z < tableData.length; z++) {
					
					if (String.valueOf(strValue).trim().equals("" + cnt))// *12)/100
																		// (1*12)/100
					{
						logger.info("checking for count=======" + z);
						for (int j = 0; j < tableData.length; j++) {
							if (String.valueOf(tableData[j][0]).trim().equals(
									"" + cnt)) {
								frml += String.valueOf(tableData[j][3]);
								flag = "true";
							}
						}
					}
					cnt++;
				}
				if (flag.equals("false")) {
					frml += "" + strValue;// (1000*12)/100
					logger.info("frml======>>>" + frml);
				}

			}
		}
		return expressionEvaluate(frml);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String expressionEvaluate(String formula) 
   	{
		try 
		{
			XJep j = new XJep();			
			try 
			{    
				
							
				 j.addStandardConstants();       
				 j.addStandardFunctions();       
				 j.addComplex();       
				 j.setAllowUndeclared(true);       
				 j.setImplicitMul(true);        
				 j.setAllowAssignment(true);	
				
				// parse expression    
				 
				Node node = j.parse(formula);    // print it    				 				
				//System.out.println("node========>>>......"+node);
				Node processed = j.preprocess(node);  
				Node simp = j.simplify(processed);	 
				//System.out.println("processed========="+simp);
				Object value = j.evaluate(simp);	
				logger.info("processed========="+value.toString());
				double vv=Double.parseDouble(String.valueOf(value));
				logger.info("value========="+vv);
				return ""+vv;
				//return ""+Utility.twoDecimals(Double.parseDouble(String.valueOf((value))));
			}
			catch(Exception e) {}
		} 
		catch (Exception e) 
		{
			return "0";
		}
		return "0";		
	}   

	public void addPreqData(Object[] tableData) {
		
		//0 code 1 amount 2 id
		logger.info("addCreditData");
		String qury=" SELECT HRMS_EMP_PERQUISITE.PERQ_AMT  FROM HRMS_EMP_PERQUISITE  WHERE HRMS_EMP_PERQUISITE.PERQ_CODE="+ tableData[0]+"  AND " +
				"  HRMS_EMP_PERQUISITE.EMP_ID ="+ tableData[2];
				Object amt[][]=getSqlModel().getSingleResult(qury);
				logger.info("FIRING QUERY");
				/*PERQ_CODE,DEBIT_APPLICABLE,DEBIT_AMT,EMP_ID*/
				logger.info("*********************************before"+tableData[2]);
				if(amt.length==0)
				{
				Object[][] bean = new Object[1][3];

				bean[0][0] = tableData[0];//code
				
				bean[0][1] = tableData[1];//amt
				bean[0][2] = tableData[2];//empid
                  logger.info("insert query...!!");
				boolean result = getSqlModel().singleExecute(getQuery(7), bean);
				
				
				}
				else
				{
					String upd=" UPDATE HRMS_EMP_PERQUISITE SET  PERQ_AMT="+tableData[1]+"    WHERE PERQ_CODE="+tableData[0]+" AND EMP_ID="+tableData[2];
					logger.info(upd);
					boolean result = getSqlModel().singleExecute(upd);	
					logger.info(upd);
					logger.info("*********************************before"+tableData[2]);
					
				}
			}

	public Object[][] showFormula(EmpCredit empCredit,	HttpServletRequest request) {
		
		Object[][] tableData = null;
		try {
			String creditsQuery = "SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME ,"
					+ " CASE WHEN CREDIT_PERIODICITY='M' THEN 'Monthly' WHEN CREDIT_PERIODICITY='Q' THEN 'Quarterly'"
					+ " WHEN CREDIT_PERIODICITY='A' THEN 'Annually' WHEN CREDIT_PERIODICITY='H' THEN 'Half Yearly' ELSE ' ' END, "
					+ " NVL(CREDIT_AMT,0), DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD "
					+ " FROM HRMS_EMP_CREDIT "
					+ " RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE AND EMP_ID="
					+ empCredit.getEmpId()
					+ ") "
					+ " ORDER BY ORD ,HRMS_CREDIT_HEAD.CREDIT_CODE";
			Object[][] creditsObj = getSqlModel().getSingleResult(creditsQuery);

			if (creditsObj != null && creditsObj.length > 0) {
				tableData = new Object[creditsObj.length][4];
				for (int i = 0; i < creditsObj.length; i++) {
					tableData[i][0] = String.valueOf(creditsObj[i][0]);
					tableData[i][1] = String.valueOf(creditsObj[i][1]);
					tableData[i][2] = String.valueOf(creditsObj[i][2]);
					tableData[i][3] = String.valueOf(creditsObj[i][3]);
				}
			}
			String formulaQuery = " SELECT SAL_CODE, SAL_TYPE,SAL_FORMULA FROM HRMS_FORMULABUILDER_HDR "
					+ " INNER JOIN HRMS_FORMULABUILDER_DTL ON(HRMS_FORMULABUILDER_DTL.FORMULA_ID = HRMS_FORMULABUILDER_HDR.FORMULA_ID) "
					+ " WHERE HRMS_FORMULABUILDER_HDR.FORMULA_ID = "
					+ empCredit.getFrmId() + " ORDER BY SAL_CODE";
			Object[][] formulaObj = getSqlModel().getSingleResult(formulaQuery);
			if (empCredit.getGrsAmt().length() > 0) {
				double CTC = Double.parseDouble(empCredit.getGrsAmt());

				for (int j = 0; j < tableData.length; j++) {
					tableData[j][3] = "0.00";
				}

				ArrayList<String> diffData = new ArrayList<String>();
				ArrayList<String> formData = new ArrayList<String>();

				for (int j = 0; j < tableData.length; j++) {

					/* Loop for checking length of salary codes
					 * This loop calculates new salary amount according to salary code .
					 */

					for (int i = 0; i < formulaObj.length; i++) {

						/* Loop for checking formula type according to formula code.
						 * */
						String exec = "";
						if (String.valueOf(tableData[j][0]).equals(
								String.valueOf(formulaObj[i][0]))) {

							/* If loop for checking equal salary code
							 * It calculates new amount if salary code is equal to salary code of formula 
							 * */
							String sal_type = String.valueOf(formulaObj[i][1]);
							String sal_formula = String.valueOf(formulaObj[i][2]);

							if (sal_type.trim().equals("Fi")) {

								/* if loop for calculating new amount when salary type is fixed 
								 * */

								tableData[j][3] = sal_formula;
							} //end of if
							else if (sal_type.trim().equals("Fr")) {

								/* if loop for calculating new amount when salary type is formula 
								 * */

								try {
									exec = executeFormula(sal_formula, CTC, tableData, formData);
									logger.info("exec===" + exec);
								} catch (Exception e) {
									e.printStackTrace();
								}
								tableData[j][3] = formatter.format(Double.parseDouble(String.valueOf(exec)));
								// logger.info("tableData[j][3]==="+tableData[j][3]);
							} //end of else if
							else if (sal_type.trim().equals("Df")) {

								/* if loop for calculating new amount when salary type is difference 
								 * */

								diffData.add(String.valueOf(tableData[j][0]));
								tableData[j][3] = "0.00";
							}//end of else if

						}// end of if
					}//end of  i loop
				} //end of j loop

				double sum = 0.00;

				for (int k = 0; k < tableData.length; k++) {

					/*  calculates difference according to periodicity
					 * */

					try {

						if (String.valueOf(tableData[k][2]).equals("Annually")) {
							sum += (Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[k][3])))));
						} // end of if - Annually
						else if (String.valueOf(tableData[k][2]).equals("Quarterly")) {
							sum += (Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[k][3])) * 4)));
						} //end of else if - Quarterly
						else if (String.valueOf(tableData[k][2]).equals("Half Yearly")) {
							sum += (Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[k][3])) * 2)));
						} //end of else if = Half yearly
						else if (String.valueOf(tableData[k][2]).equals("Monthly")) {
							//logger.info("String.valueOf(tableData[k][3]====)"+String.valueOf(tableData[k][3]));
							sum += (Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[k][3])) * 12)));
						} //end of else if - Monthly
					} catch (Exception e) {
						e.printStackTrace();
					}//end of try-catch block
				}// end of k loop

				double cal = 0.00;
				if (CTC >= sum) {
					cal = ((CTC - sum) / 12);
				}
				if (CTC <= sum) {
					cal = ((sum - CTC) / 12);
				}

				for (int j = 0; j < tableData.length; j++) {

					for (int i = 0; i < diffData.size(); i++) {
						if (String.valueOf(tableData[j][0]).equals(diffData.get(i))) {
							if (String.valueOf(tableData[j][2]).equals("Annually")) {
								tableData[j][3] = formatter.format(cal * 12);
							}
							if (String.valueOf(tableData[j][2]).equals("Half Yearly")) {
								tableData[j][3] = formatter.format(cal * 6);
							}
							if (String.valueOf(tableData[j][2]).equals("Quarterly")) {
								tableData[j][3] = formatter.format(cal * 4);
							}
							if (String.valueOf(tableData[j][2]).equals("Monthly")) {
								tableData[j][3] = formatter.format(cal);
							}
						} //end of if
					} // end of i loop
				}//end of j loop
			}//end of if
			// Calculation for total Annually
			double temp = 0;
			for (int i = 0; i < tableData.length; i++) {

				if (String.valueOf(tableData[i][2]).equals("Annually")) {
					temp += (Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3])))));
				}
				if (String.valueOf(tableData[i][2]).equals("Half Yearly")) {
					temp += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3])) * 2));
				}
				if (String.valueOf(tableData[i][2]).equals("Quarterly")) {
					temp += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3])) * 4));
				}

				if (String.valueOf(tableData[i][2]).equals("Monthly")) {
					temp += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3])) * 12));
				}

			}
			empCredit.setAnnualAmt(formatter.format((temp)));
			// calculation for Monthly
			double monthTemp = 0;
			for (int i = 0; i < tableData.length; i++) {

				if (String.valueOf(tableData[i][2]).equals("Monthly")) {
					monthTemp += Double.parseDouble(String.valueOf(tableData[i][3]));
				}

			}
			empCredit.setTotalamt(formatter.format(monthTemp));
			// -----------------------------
			String countQuery = " select count(*),HRMS_CREDIT_HEAD.CREDIT_PERIODICITY from HRMS_CREDIT_HEAD "
					+ " group by HRMS_CREDIT_HEAD.CREDIT_PERIODICITY";
			Object[][] periLength = getSqlModel().getSingleResult(countQuery);
			Object[][] mdata = null;
			Object[][] qdata = null;
			Object[][] hdata = null;
			Object[][] adata = null;
			if (periLength != null && periLength.length > 0) {
				for (int i = 0; i < periLength.length; i++) {
					if (String.valueOf(periLength[i][1]).equals("M")) {
						mdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
					}
					if (String.valueOf(periLength[i][1]).equals("Q")) {
						qdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
					}
					if (String.valueOf(periLength[i][1]).equals("H")) {
						hdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
					}
					if (String.valueOf(periLength[i][1]).equals("A")) {
						adata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
					}
				}
			}
			double monthAMT = 0.0;
			double annualSum = 0.0;
			if (tableData != null && tableData.length > 0) {
				ArrayList innerList = new ArrayList();
				for (int i = 0; i < tableData.length; i++) {
					EmpCredit bean = new EmpCredit();
					bean.setCreditNameItt(String.valueOf(tableData[i][1]).toUpperCase());
					bean.setCreditPeriodItt(String.valueOf(tableData[i][2]));
					bean.setCreditAmountItt(Utility.twoDecimals(String.valueOf(tableData[i][3])));
					bean.setCredCode(String.valueOf(tableData[i][0]));

					if (String.valueOf(tableData[i][2]).equals("Monthly")) {
						monthAMT += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3]))));
						annualSum += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3])) * 12));
					} else if (String.valueOf(tableData[i][2]).equals("Quarterly")) {
						annualSum += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3])) * 4));
					} else if (String.valueOf(tableData[i][2]).equals("Half Yearly")) {
						annualSum += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3])) * 2));
					} else if (String.valueOf(tableData[i][2]).equals("Annually")) {
						annualSum += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(tableData[i][3]))));
					}

					innerList.add(bean);
				}
				//annualSum+=monthAMT;
				empCredit.setSalHeaderList(innerList);
				empCredit.setTotalamt(formatter.format(Double.parseDouble(String.valueOf(monthAMT))));
				empCredit.setAnnualAmt(formatter.format(Double.parseDouble(String.valueOf(annualSum))));
			} // End of if
			// ----------------------
			for (int i = 0; i < tableData.length; i++) {

				EmpCredit bean1 = new EmpCredit();
				bean1.setCredCode(String.valueOf(tableData[i][0]));
				bean1.setSalHead(String.valueOf(tableData[i][1]));
				bean1.setPeriod(String.valueOf(tableData[i][2]));
				bean1.setAmount(String.valueOf(tableData[i][3]));
				bean1.setGeneralFlag(empCredit.isGeneralFlag());
				//atlist.add(bean1);
			}
			ctcMethod(empCredit);
			fetchIncrementPeriod(empCredit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableData;
	}
	
	
	/**
	 * This method fetches employee's all increment periods
	 * @param empCredit 
	 */
	
	public HashMap fetchIncrementPeriod(EmpCredit empCredit){
		HashMap periodMap = new HashMap();
		try {
			String query = "SELECT HRMS_PROMOTION.PROM_CODE, TO_CHAR(HRMS_PROMOTION.EFFECT_DATE,'MONTH-YYYY') AS PERIOD FROM HRMS_PROMOTION WHERE EMP_CODE="
				+empCredit.getEmpId()+" AND HRMS_PROMOTION.LOCK_FLAG='Y' ORDER BY HRMS_PROMOTION.PROM_CODE DESC" ;
			Object[][] incPeriodObj = getSqlModel().getSingleResult(query);
			
			if(incPeriodObj!=null && incPeriodObj.length >0){
				for (int i = 0; i < incPeriodObj.length; i++) {
					periodMap.put(String.valueOf(incPeriodObj[i][0]), String.valueOf(incPeriodObj[i][1]));
	
				}
			periodMap = (HashMap<Object, Object>) org.paradyne.lib.Utility.sortMapByValue(periodMap, null, true);
			}else{
				periodMap.put("N/A","N/A");
			}
			empCredit.setIncrementHistoryMap(periodMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return periodMap;
	}
	
	/**
	 * This method fetches employee increment details
	 * @param empCredit 
	 * @param request
	 * @param query  
	 */
	
	public void showIncrementHistory(EmpCredit empCredit, HttpServletRequest request, String query) {
		try {
			Object salHeaderObj[][] = getSqlModel().getSingleResult(query);
			double monthAMT = 0.0;
			double annualSum = 0.0;
			if(salHeaderObj!=null && salHeaderObj.length >0){
				ArrayList innerList= new ArrayList();
				for (int i = 0; i < salHeaderObj.length; i++) {
					EmpCredit bean = new EmpCredit();
					bean.setCreditNameItt(String.valueOf(salHeaderObj[i][0]).toUpperCase());
					bean.setCreditPeriodItt(String.valueOf(salHeaderObj[i][1]));
					bean.setCreditAmountItt(Utility.twoDecimals(String.valueOf(salHeaderObj[i][2])));
					bean.setCredCode(String.valueOf(salHeaderObj[i][4]));
					
					if(String.valueOf(salHeaderObj[i][1]).equals("Monthly")){
						monthAMT += Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(salHeaderObj[i][2]))));
						annualSum+=Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(salHeaderObj[i][2]))*12));
					}
					else if(String.valueOf(salHeaderObj[i][1]).equals("Quarterly")){
						annualSum+=Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(salHeaderObj[i][2]))*4));
					}
					else if(String.valueOf(salHeaderObj[i][1]).equals("Half Yearly")){
						annualSum+=Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(salHeaderObj[i][2]))*2));
					}
					else if(String.valueOf(salHeaderObj[i][1]).equals("Annually")){
						annualSum+=Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(salHeaderObj[i][2]))));
					}
					innerList.add(bean);
				}
				empCredit.setSalHeaderList(innerList);
				empCredit.setTotalamt(formatter.format(Double.parseDouble(String.valueOf(monthAMT))));
				empCredit.setAnnualAmt(formatter.format(Double.parseDouble(String.valueOf(annualSum))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method fetches employee details by employeeId
	 * @param empCredit 
	 * @param employeeId
	 * @param editFlag  
	 */
	
	public void fetchEmployeeDetailsByEmployeeId(EmpCredit empCredit, HttpServletRequest request, String employeeId, boolean editFlag){
		try {
			String empDetailQuery = "SELECT HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, NVL(SALGRADE_TYPE,' '),"
					+ " SALGRADE_CODE, HRMS_EMP_OFFC.EMP_ID, HRMS_SALARY_MISC.GROSS_AMT,"
					+ " DECODE(HRMS_SALARY_MISC.SAL_EPF_FLAG,'Y','true','N','false'), NVL(SAL_ACCNO_REGULAR,' ')," 
					+ " NVL(SAL_PANNO,' '), NVL(DEPT_NAME,' '), DEPT_ID , NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),"
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
				
				empCredit.setEmpCenter(String.valueOf(empDetailObj[0][0]));
				empCredit.setEmpRank(String.valueOf(empDetailObj[0][1]));
				empCredit.setGradeName(String.valueOf(empDetailObj[0][2]));
				empCredit.setGradeId(String.valueOf(empDetailObj[0][3]));
				empCredit.setEmpId(String.valueOf(empDetailObj[0][4]));
				empCredit.setGrsAmt(String.valueOf(empDetailObj[0][5]));
				empCredit.setPfFlag(String.valueOf(empDetailObj[0][6]));
				empCredit.setEmpAccountNo(String.valueOf(empDetailObj[0][7]));
				empCredit.setEmpPanNo(String.valueOf(empDetailObj[0][8]));
				empCredit.setEmpDeptName(String.valueOf(empDetailObj[0][9]));
				empCredit.setEmpDeptId(String.valueOf(empDetailObj[0][11]));
				empCredit.setJoiningDate(String.valueOf(empDetailObj[0][11]));
				empCredit.setEmpGradeName(String.valueOf(empDetailObj[0][12]));
				empCredit.setEmpGradeId(String.valueOf(empDetailObj[0][13]));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		showEmp(empCredit, request, employeeId, editFlag);
	}
	/**
	 * This method fetches employee details as per the selected promotion date
	 * @param empCredit 
	 * @param promotionCode 
	 */
	
	public void fetchEmployeeDetailsByPromotionCode(EmpCredit empCredit, String promotionCode){
		try {
			 String empDetailQuery = "SELECT   HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS NAME,"  
					+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,"
					+ " NVL(SALGRADE_TYPE,' ') ,SALGRADE_CODE, HRMS_EMP_OFFC.EMP_ID,"   
					+ " HRMS_SALARY_MISC.GROSS_AMT,DECODE(HRMS_SALARY_MISC.SAL_EPF_FLAG,'Y','true','N','false'), NVL(SAL_ACCNO_REGULAR,' '), "
					+ " NVL(SAL_PANNO,' '), NVL(DEPT_NAME,' '), DEPT_ID , TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), NVL(CADRE_NAME,' '), HRMS_PROMOTION.PRO_GRADE, NVL(CTC,0)"   
					+ " FROM HRMS_EMP_OFFC "   
					+ " INNER JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.EMP_CODE = HRMS_EMP_OFFC.EMP_ID AND HRMS_PROMOTION.PROM_CODE ="+promotionCode+")"
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_PROMOTION.PRO_DESG)"  
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_PROMOTION.PRO_BRANCH)" 
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)"  
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_PROMOTION.PRO_DEPT )" 
					+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_PROMOTION.PRO_GRADE)"
					+ " LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_PROMOTION.PROM_SALGRADE)"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)" 
					+ " LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_SALARY_MISC.FORMULA_ID)";
			Object empDetailObj[][] = getSqlModel().getSingleResult(empDetailQuery);
			
			if(empDetailObj!=null && empDetailObj.length>0){
			
				empCredit.setEmpToken(String.valueOf(empDetailObj[0][0]));
				empCredit.setEmpName(String.valueOf(empDetailObj[0][1]));
				empCredit.setEmpCenter(String.valueOf(empDetailObj[0][2]));
				empCredit.setEmpRank(String.valueOf(empDetailObj[0][3]));
				empCredit.setGradeName(String.valueOf(empDetailObj[0][4]));
				empCredit.setGradeId(String.valueOf(empDetailObj[0][5]));
				empCredit.setEmpId(String.valueOf(empDetailObj[0][6]));
				empCredit.setGrsAmt(String.valueOf(empDetailObj[0][7]));
				empCredit.setPfFlag(String.valueOf(empDetailObj[0][8]));
				empCredit.setEmpAccountNo(String.valueOf(empDetailObj[0][9]));
				empCredit.setEmpPanNo(String.valueOf(empDetailObj[0][10]));
				empCredit.setEmpDeptName(String.valueOf(empDetailObj[0][11]));
				empCredit.setEmpDeptId(String.valueOf(empDetailObj[0][12]));
				empCredit.setJoiningDate(String.valueOf(empDetailObj[0][13]));
				empCredit.setEmpGradeName(String.valueOf(empDetailObj[0][14]));
				empCredit.setEmpGradeId(String.valueOf(empDetailObj[0][15]));
				empCredit.setCtcAmt(String.valueOf(empDetailObj[0][16]));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		fetchIncrementPeriod(empCredit);
	}
}
