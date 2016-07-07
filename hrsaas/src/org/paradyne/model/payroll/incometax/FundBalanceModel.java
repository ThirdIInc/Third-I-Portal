package org.paradyne.model.payroll.incometax;



import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.payroll.incometax.FundBalanceMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class FundBalanceModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	FundBalanceMaster fundBalance = null;

	public String[][] getRecord(FundBalanceMaster fundBalance, String query) {

		logger.info("In getRecord Model===========1");
		Object[][] data = getSqlModel().getSingleResult(query);

		String[][] total = new String[data.length][6];
		for (int i = 0; i < data.length; i++) {

			logger.info("---------------------------------------------"
					+ total.length);
			total[i][0] = String.valueOf(data[i][0]);
			total[i][1] = String.valueOf(data[i][1]);
			total[i][2] = String.valueOf(data[i][2]);
			total[i][3] = String.valueOf(data[i][3]);
			total[i][4] = String.valueOf(data[i][4]);
			int x = Integer.parseInt(String.valueOf(total[i][3]));

			int y = Integer.parseInt(String.valueOf(total[i][4]));

			total[i][5] = String.valueOf(x + y);

		}

		/* tabRec.setRecList(list); */
		return total;

	}

	public boolean deleteRecord(String[] empIds, FundBalanceMaster fundBalance)
			throws Exception {
		/*
		 * String query ="select
		 * HRMS_FUND_BALANCE.DEBIT_CODE,EMP_ID,BALANCE_PREVIOUS_AMT,BALANCE_CURRENT_AMT
		 * from HRMS_FUND_BALANCE"+ "inner JOIN HRMS_EMP_OFFC ON
		 * (HRMS_EMP_OFFC.EMP_ID = HRMS_FUND_BALANCE.EMP_ID)"+ " where
		 * HRMS_EMP_OFFC.EMP_PAYBILL="+fundBalance.getPaybillID()+""; Object[][]
		 * delete = getSqlModel().getSingleResult(query); Object[][] delete=new
		 * Object[1][3]; delete[0][0]=empId; delete[0][1]=tdscal.getFromYear();
		 * delete[0][2]=tdscal.getToYear(); logger.info("In
		 * FundBal***********2**"+empIds);
		 */
		
		Object[][] delObj=new Object[empIds.length][2];
		for (int i = 0; i < empIds.length; i++) {
			delObj[i][0]=empIds[i];
			delObj[i][1]=fundBalance.getDebitCode();
			
		}
		String query1 = "delete from HRMS_FUND_BALANCE where HRMS_FUND_BALANCE.EMP_ID=?"
				
				+ "  and DEBIT_CODE=?";
				

		boolean result1 = getSqlModel().singleExecute(query1,delObj);

		return result1;

	}

	public boolean saveRecord(String []empIds ,String[] prebal, String[] curbal, FundBalanceMaster fundBalance)throws Exception
	{
		Object[][] addObj=new Object[empIds.length][4];
	for (int i = 0; i < empIds.length; i++) {
		addObj[i][0]=empIds[i];
		addObj[i][1]=prebal[i];
		addObj[i][2]=curbal[i];
		addObj[i][3]=fundBalance.getDebitCode();
	}
		
		String query ="insert into HRMS_FUND_BALANCE (EMP_ID,BALANCE_PREVIOUS_AMT,BALANCE_CURRENT_AMT,DEBIT_CODE)"+
					  "values (?,?,?,?)";
				
  
		boolean result = getSqlModel().singleExecute(query,addObj );
		return result;
		
	}
	
	public boolean callReport( FundBalanceMaster fundBalance,
			HttpServletResponse response) {

		String type = "Txt";
		String title = " ";

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);
		rg = getHeader(rg, fundBalance);
		rg.createReport(response);
		return true;

	}
	
	public ReportGenerator getHeader(ReportGenerator rg, FundBalanceMaster fundBalance) {
				
		String query =" SELECT  HRMS_EMP_OFFC.EMP_TOKEN, "   
		     +"HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||" 
		    + "HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," 
		    +" NVL(HRMS_FUND_BALANCE.BALANCE_PREVIOUS_AMT,0),NVL(HRMS_FUND_BALANCE.BALANCE_CURRENT_AMT,0), NVL(HRMS_FUND_BALANCE.BALANCE_PREVIOUS_AMT,0)+NVL(HRMS_FUND_BALANCE.BALANCE_CURRENT_AMT,0),HRMS_EMP_OFFC.EMP_ID"
		     +" FROM HRMS_EMP_OFFC   "
		      
		   + "LEFT JOIN HRMS_FUND_BALANCE ON (HRMS_EMP_OFFC.EMP_ID = HRMS_FUND_BALANCE.EMP_ID AND HRMS_FUND_BALANCE.DEBIT_CODE ="+fundBalance.getDebitCode()+")" 
		   + "LEFT JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE= HRMS_FUND_BALANCE.DEBIT_CODE) "
		    +" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE " 
		       
		   + " where HRMS_EMP_OFFC.emp_paybill="+fundBalance.getPaybillID()+" order by emp_id "; 
		
	
		Object[][] result = getSqlModel().getSingleResult(query); 
		 
		Object finalObj[][]=null;
		try {
			finalObj = new Object[result.length][6];
		
		for (int i = 0; i < finalObj.length; i++) {
			finalObj[i][0]=i+1;
			finalObj[i][1]=result[i][0];
			finalObj[i][2]=result[i][1];
			finalObj[i][3]=result[i][2];
			finalObj[i][4]=result[i][3];
			finalObj[i][5]=result[i][4];
			
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		//rg.genHeader("");
		String header = " FUND  BALANCE REPORT FOR "+fundBalance.getDebitName();
		rg.addFormatedText(header, 2, 0, 1, 0);
		String colNames[]={"Sr No.","Token No.","Employee Name","Previous Balance","Current Balance","Total"};
		int []cellwidth={20,20,20,20,20,20};
		int []alignmnet={1,1,1,1,1,1};
		rg.tableBody(colNames, finalObj, cellwidth, alignmnet,rg,20,3,header);	
		
		return rg;
		
	}

}
