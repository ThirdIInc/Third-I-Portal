package org.struts.action.payroll.tablerecovery;

import org.paradyne.bean.payroll.tablerecovery.*;
import org.paradyne.model.payroll.tablerecovery.*;
import org.struts.lib.*;   
import com.svcon.jdbf.DBFReader;
import com.svcon.jdbf.JDBFException;


/**
 * @author Venkatesh
 *
 */
public class TableRecoveryAction extends ParaActionSupport{
	
	TableRecovery tabRec;
	
	Object[] objectArr1;
	Object[] token,amt;
	 int count=0;
	
 private static DBFReader dbfIn;
 
	
	
	/**
	 * @return the tabRec
	 */
	public TableRecovery getTabRec() {
		return tabRec;
	}

	/**
	 * @param tabRec the tabRec to set
	 */
	public void setTabRec(TableRecovery tabRec) {
		this.tabRec = tabRec;
	}

	 public Object getModel() {
		return tabRec;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		tabRec=new TableRecovery();
	}
	
	public String display()throws Exception
	{
		String fullFile=context.getRealPath("/")+ "pages/files/Recovery/" +tabRec.getUploadFileName() ; 
		logger.info("+++++++++++++"+tabRec.getDebitCode());
		TableRecoveryModel model=new TableRecoveryModel();
		model.initiate(context,session);
		String query =" SELECT  HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,   "+
						"HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+
						"NVL(HRMS_EMP_DEBIT.DEBIT_AMT,0) FROM HRMS_EMP_OFFC "+  
						"left JOIN HRMS_EMP_DEBIT ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_DEBIT.EMP_ID  and HRMS_EMP_DEBIT.debit_code="+tabRec.getDebitCode()+")  "+
						"LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";
					
		query += getprofileQuery(tabRec);
		query += "and emp_paybill="+tabRec.getPayBillNo()+" order by emp_id" ;
		logger.info("In display --------------------------------query="+query);
		String[][] data = model.getRecord(tabRec,query);
		 request.setAttribute("data", data);
		 logger.info("&&&&&&&&&&&&&&&&&&&After"+data.length);
		 logger.info("**********Path in upload.jsp@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+tabRec.getUploadFileName());
			
		 if(!tabRec.getUploadFileName().equals(""))
		 {
		  logger.info("**********Path in upload.jsp@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+fullFile);
		
		 try {
				dbfIn = new DBFReader(new String(fullFile));
				 logger.info("*********in first try ");
				
				while (dbfIn.hasNextRecord()) 
				{
					try {
						
						for (int i = 0; i <= dbfIn.getFieldCount(); i++) {
							
							Object objectArr[] = dbfIn.nextRecord();
							//objectArr1=objectArr;
							logger.info(count+"  "+ objectArr[1]+"   "+objectArr[4]);
							count++;
							
						}
					} catch (JDBFException dbfFieldEx) {
						logger.info("Cannot get field!"
								+ dbfFieldEx.getMessage());
						
					}
				}
				
				
				
			
		 } catch (JDBFException dbfReadEx) {
				System.out
						.println("Cannot open dbf file!" + dbfReadEx.getMessage());
				
			}
		 finally{
			 dbfIn.close();
		 }
		
		 token=new Object[count+1];
		 amt=new Object[count+1];
		 
		 try {
				dbfIn = new DBFReader(new String(fullFile));
				
				
				int count1=0;
				while (dbfIn.hasNextRecord()) 
				{
					
					try {
						
						for (int i = 0; i <= dbfIn.getFieldCount(); i++) {
							
							Object objectArr[] = dbfIn.nextRecord();
							
							token[count1] =objectArr[1];
							amt[count1] = objectArr[4];
							logger.info("ddddddddddddddddd"+objectArr[1]+objectArr[4]+count1);
							count1++;
						}
					} catch (JDBFException dbfFieldEx) {
						logger.info("Cannot get field!"
								+ dbfFieldEx.getMessage());
						
					}
				}
				
				
				
			
		 } catch (JDBFException dbfReadEx) {
				System.out
						.println("Cannot open dbf file----------------------!" + dbfReadEx.getMessage());
				
			}
		 finally{
			 dbfIn.close();
		 }
		 
		 
		 
		 model.saveData(tabRec,token,amt);
		 }
		 if(data.length>0)
			 tabRec.setDisplay("true");
		model.terminate();
		
		return "success";
	}
	
	public String save()throws Exception
	{
		TableRecoveryModel model=new TableRecoveryModel();
		model.initiate(context,session);
		String[] empIds=(String[])request.getParameterValues("empId");
		
		String[] debAmt=(String[])request.getParameterValues("debAmt");
		String[] salAmt=(String[])request.getParameterValues("salAmt");
		logger.info("&&&&&&&&&&&&&&&&&&&After Get emp  Ids length----------------------"+empIds.length);
	
		logger.info("-------------After Get debAmt length----------------------"+debAmt.length);
		logger.info("-------------After Get salAmt length----------------------"+salAmt.length);
		
		for(int i=0; i<empIds.length;i++)
		{
			boolean result=model.updDeb(empIds[i],tabRec,debAmt[i],salAmt[i]);
			
		}
		return "success";
	}
	
	public String f9action()throws Exception
	{
		logger.info("In f9 action===========1");
		String query = "SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_NAME,REC_DEBIT_CODE FROM HRMS_RECOVERY_CONF "+  
						" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_RECOVERY_CONF.REC_DEBIT_CODE "+
						" WHERE EMP_ID="+tabRec.getUserEmpId()+" ORDER BY REC_DEBIT_CODE ";
		
		String[] headers={"Debit Name"};
		
		String[] headerWidth={"30"};
		logger.info("In f9 action===========2");
		
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"tabRec.debitName","tabRec.debitCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		logger.info("In f9 action===========3");
		
		
		String submitToMethod="";
		
		logger.info("In f9 action===========4");
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
						+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
		
		query += getprofilePaybillQuery(tabRec);
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "PAY BILL NAME","PAY BILL NO" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "80","20" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "tabRec.payBillNo","tabRec.payBillNo"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0,1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
}