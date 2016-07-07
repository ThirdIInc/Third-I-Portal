package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.EmployeeCheckList;
import org.paradyne.bean.payroll.EmpCredit;
import org.paradyne.bean.payroll.EmpDebit;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

public class EmpDebitModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	EmpDebit debit = null;
	AuditTrail trial;
	public boolean addDebitData(Object[] tableData) {

		logger.info("FIRING QUERY");

		logger.info("QRY NO. 1 " + getQuery(1));

		Object[][] bean = new Object[1][3];

		bean[0][0] = tableData[0];
		bean[0][1] = tableData[1];
		bean[0][2] = tableData[2];

		logger.info("Gettin' Debit Data: " + bean[0][0] + "\t:"
				+ bean[0][1] + "\n");

		logger.info("Query No. " + getQuery(1));

		boolean result = getSqlModel().singleExecute(getQuery(1), bean);

		return result;
	}
	
	public Object[][] chkEmpId(String emp){
		
		logger.info("Checking Emp Id");
		
		return getSqlModel().getSingleResult(getQuery(5),new Object[]{emp});
		
		
		
	}

	public boolean updateDebitData(Object[] tableData) {

		logger.info("QRY NO. 2 " + getQuery(2));

		logger.info("UPDATING FIRING QUERY");


		Object[][] bean = new Object[1][3];

		bean[0][0] = tableData[1];
		bean[0][1] = tableData[0];
		bean[0][2] = tableData[2];

		logger.info("Updaring Debit Data: " + bean[0][0] + "\t:"
				+ bean[0][1] +"\t"+bean[0][2] + "\n");

		logger.info("Query No. " + getQuery(1));

		boolean result = getSqlModel().singleExecute(getQuery(2), bean);

		return result;
	}

	public boolean deleteDebitData(EmpDebit debit) {

		logger.info("QRY NO. 3 " + getQuery(3));

		Object[][] bean = new Object[1][1];

		bean[0][0] = debit.getEmpId();

		logger.info("Gettin' Debit Data: " + bean[0][0] + "\n");

		logger.info("Query No. " + getQuery(3));

		boolean result = getSqlModel().singleExecute(getQuery(3), bean);
		
		return result;
	}

	public void showGeneralEmpData(EmpDebit bean, String empId)
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

		logger.info("Query :" + getQuery(4));

		logger.info("Rows Data :" + bean.length);

		for (int i = 0; i < bean.length; i++) {

			logger.info("\t" + bean[0][0]+"\t"+bean[0][1]+"\t");

		}
		return bean;
	}
	
	public void getChkList(EmpDebit debit)
	{
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),new Object[][]{});
		ArrayList chk=new ArrayList();
		for(int i=0; i<data.length;i++)
		{
			EmpDebit bean=new EmpDebit();

			bean.setHeaderId(String.valueOf(data[i][0]));
			bean.setHeadName(String.valueOf(data[i][1]));
			 
			chk.add(bean);
		}
		debit.setArrayList(chk);
	}
	
	
	public Object[][] showEmp(EmpDebit debit,String empId ,HttpServletRequest request)
	{
		String qury="  SELECT DISTINCT NVL(DEBIT_AMT,0),HRMS_DEBIT_HEAD.DEBIT_CODE ,HRMS_DEBIT_HEAD.DEBIT_NAME , "+
		" NVL(DEBIT_APPLICABLE,'Y') , EMP_ID,CASE WHEN DEBIT_PERIODICITY='M' THEN 'Monthly' WHEN DEBIT_PERIODICITY='Q' THEN 'Quarterly' WHEN DEBIT_PERIODICITY='A' THEN 'Annually' WHEN DEBIT_PERIODICITY='H' THEN 'Half Yearly' ELSE ' ' END 	FROM HRMS_EMP_DEBIT "+    
		" RIGHT JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_EMP_DEBIT.DEBIT_CODE "+
		" AND EMP_ID="+empId+")  ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE ";
		Object amt[][]=getSqlModel().getSingleResult(qury);
		ArrayList atlist = new ArrayList();
		HashMap  hmp =new HashMap ();
		logger.info("into model ---------");
		
		String query="SELECT NVL(DEBIT_AMT,0) FROM HRMS_EMP_DEBIT INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE) WHERE EMP_ID="+debit.getEmpId()+" and DEBIT_PERIODICITY='M' ";
		Object debitAmt[][]=getSqlModel().getSingleResult(query);
		
		String halfQuery="SELECT NVL(DEBIT_AMT,0) FROM HRMS_EMP_DEBIT INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE) WHERE EMP_ID="+debit.getEmpId()+" and DEBIT_PERIODICITY='H' ";
		Object debitHalfAmt[][]=getSqlModel().getSingleResult(halfQuery);
		
		String quartQuery="SELECT NVL(DEBIT_AMT,0) FROM HRMS_EMP_DEBIT INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE) WHERE EMP_ID="+debit.getEmpId()+" and DEBIT_PERIODICITY='Q' ";
		Object debitQuartAmt[][]=getSqlModel().getSingleResult(quartQuery);
		
		String annualQuery="SELECT NVL(DEBIT_AMT,0) FROM HRMS_EMP_DEBIT INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE) WHERE EMP_ID="+debit.getEmpId()+" and DEBIT_PERIODICITY='A' ";
		Object debitAnnualAmt[][]=getSqlModel().getSingleResult(annualQuery);
		
		
		
		
		
		double  sum=0.0;
		double HalfSum=0.0;
		double quartSum=0.0;
		double anlSum=0.0;
		double annualSum=0.0;
		for(int j=0;j<debitAmt.length;j++){
			sum+=Double.parseDouble(String.valueOf(debitAmt[j][0]));
			
			
		}
		
		for(int j=0;j<debitHalfAmt.length;j++){
			HalfSum+=Double.parseDouble(String.valueOf(debitHalfAmt[j][0]));
			
			
		}
		
		for(int j=0;j<debitQuartAmt.length;j++){
			quartSum+=Double.parseDouble(String.valueOf(debitQuartAmt[j][0]));
			
			
		}
		
		for(int j=0;j<debitAnnualAmt.length;j++){
			anlSum+=Double.parseDouble(String.valueOf(debitAnnualAmt[j][0]));
			
			
		}
		
		
		annualSum=12*sum+2*HalfSum+quartSum*4+anlSum;
		
		for (int i = 0; i < amt.length; i++) {
			
			EmpDebit  bean1 = new EmpDebit();
			bean1.setSalHead(String.valueOf(amt[i][2]));
			bean1.setPeriod(String.valueOf(amt[i][5]));
			bean1.setGeneralFlag(debit.isGeneralFlag());
			// removing check box as instructed by sachin on 18/08/2007
			
			/*hmp.put(String.valueOf(amt[i][1]), String.valueOf(amt[i][3]));
			if(String.valueOf(amt[i][3]).equalsIgnoreCase(String.valueOf("y")))
			{
				bean1.setChkFlag("true");
				logger.info("the value of amount is before"+amt[i][3]);
				amt[i][3]=String.valueOf("true");
			}
			else
			{
				bean1.setChkFlag("false");
			}*/
			atlist.add(bean1);
		}
		logger.info("***********************************"+String.valueOf(sum));
		debit.setTotalAmt(String.valueOf(Math.round(sum)));
		debit.setAnnuallAmt(String.valueOf(Math.round(annualSum)));
		
		request.setAttribute("data",hmp);
		debit.setAtt(atlist);
		return amt;
		
	}
	public void addCreditData(Object[] tableData, EmpDebit debit2, HttpServletRequest request) {
		String qury=" SELECT HRMS_EMP_DEBIT.DEBIT_AMT  FROM HRMS_EMP_DEBIT  WHERE HRMS_EMP_DEBIT.DEBIT_CODE="+ tableData[0]+"  AND " +
				"  HRMS_EMP_DEBIT.EMP_ID ="+ tableData[3];
				Object amt[][]=getSqlModel().getSingleResult(qury);
				logger.info("FIRING QUERY");
				/*DEBIT_CODE,DEBIT_APPLICABLE,DEBIT_AMT,EMP_ID*/
				logger.info("*********************************before"+tableData[2]);
				if(amt.length==0)
				{
				Object[][] bean = new Object[1][4];

				bean[0][0] = tableData[0];//code
				bean[0][1] = String.valueOf("Y");
				bean[0][2] = tableData[2];//amt
				bean[0][3] = tableData[3];//empid
			
				
				boolean result = getSqlModel().singleExecute(getQuery(1), bean);
				if(result){
					
					trial = new AuditTrail(debit2.getUserEmpId());
					/** AUDIT TRIAL INITIALIZE	**/
					trial.initiate(context, session);
					trial.setParameters("HRMS_EMP_DEBIT", new String[] { "DEBIT_CODE","EMP_ID" },
							new String[] { String.valueOf(tableData[0]), debit2.getEmpId() }, debit2.getEmpId());
					trial.setComments("DEBIT_CODE   :"+String.valueOf(tableData[0]));
					trial.executeADDTrail(request);	
				}
				
				
				}
				else
				{
					
					logger.info("===USER LOGIN CODE====" + debit2.getUserEmpId());
					trial = new AuditTrail(debit2.getUserEmpId());
					/** AUDIT TRIAL INITIALIZE	**/
					trial.initiate(context, session);
					trial.setParameters("HRMS_EMP_DEBIT", new String[] { "DEBIT_CODE","EMP_ID" },
							new String[] { String.valueOf(tableData[0]),debit2.getEmpId() }, debit2.getEmpId());
					trial.beginTrail();
					trial.setComments("DEBIT_CODE   :"+String.valueOf(tableData[0]));
					
					String upd=" UPDATE HRMS_EMP_DEBIT SET  DEBIT_AMT="+tableData[2]+"  , DEBIT_APPLICABLE='"+String.valueOf("Y")+"'  WHERE DEBIT_CODE="+tableData[0]+" AND EMP_ID="+tableData[3];
					boolean result = getSqlModel().singleExecute(upd);	
					logger.info(upd);
					 /**	AUDIT TRAIL	EXECUTION **/
					trial.executeMODTrail(request);
					logger.info("*********************************before"+tableData[2]);
					/*bean[0][0] = tableData[1];
					bean[0][1] = tableData[0];
					bean[0][2] = tableData[2];
					boolean result = getSqlModel().singleExecute(getQuery(2), bean);*/
					
				}
			}
	

}
