/**
 * 
 */
package org.paradyne.model.payroll;

import java.util.ArrayList;

import org.paradyne.bean.payroll.CashClaimApproval;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;

/**
 * @author varunk
 *
 */
public class CashClaimApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void getRecords(CashClaimApproval bean, String status) {
		
		Object empObject[] = new Object[3];
		empObject[2]	   =	bean.getUserEmpId();
		empObject[1]	   =	bean.getUserEmpId();
		empObject[0]	   =   status; 
		Object empObject1[] = new Object[2];
		empObject1[0]	   =	bean.getUserEmpId();
		empObject1[1]	   =	bean.getUserEmpId();
		
		Object [][]result=null;
		logger.info("empObject[1]==============="+empObject[1]);
		if(status.equals("A") || status.equals("D")){
			String query="SELECT CLAIM_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '|| "
			+" HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME as name,CLAIM_EMPID, "
			+" TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),CLAIM_APPL_LEVEL,CLAIM_STATUS "
			+" FROM HRMS_CASH_CLAIM_HDR "
			+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
			+" WHERE (CLAIM_APPROVER= ? OR CLAIM_ALTER_APPROVER=?) AND CLAIM_STATUS IN ('A','D') ";
			result = getSqlModel().getSingleResult(query,empObject1);
		}else {
			String query="SELECT CLAIM_ID,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || " 
				+" HRMS_EMP_OFFC.EMP_LNAME,CLAIM_EMPID,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),CLAIM_APPL_LEVEL,CLAIM_STATUS  "
				+" FROM HRMS_CASH_CLAIM_HDR "
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
				+" WHERE CLAIM_STATUS=? AND (CLAIM_APPROVER=? OR CLAIM_ALTER_APPROVER=?)";
			result = getSqlModel().getSingleResult(query,empObject);
		}
		
		ArrayList<Object> appList = new ArrayList<Object>();
		
		bean.setStatus(status);
		
		if(result!=null && result.length!=0){
			for (int i = 0; i < result.length; i++) {
				CashClaimApproval approval = new CashClaimApproval();
				approval.setClaimCode(String.valueOf(result[i][0]));
				approval.setEmpID(String.valueOf(result[i][3]));
				approval.setEmpToken(String.valueOf(result[i][1]));
				approval.setEmpName(String.valueOf(result[i][2]));
				approval.setAppliedDate(String.valueOf(result[i][4]));
				approval.setCheckStatus(String.valueOf(result[i][6]));
				if(status.equals("A"))
					approval.setStatusNew("Approved");
				if(status.equals("R"))
					approval.setStatusNew("Rejected");
				if(status.equals("D"))
					approval.setStatusNew("Disbursed");
				approval.setLevel(String.valueOf(result[i][5]));
				approval.setComment("");
				logger.info("size of list=="+appList.size());
				
				if(!bean.getStatus().equals("P")){
					approval.setApprflag("true");
					logger.info("inside ifffffffff   "+approval.getApprflag());
				}else{
					approval.setApprflag("false");
					logger.info("inside elseeeeeee   "+approval.getApprflag());
				}
				bean.setNoData("false");
				appList.add(approval);
			}
			bean.setListLength(String.valueOf(appList.size()));
		}
		else{
			bean.setNoData("true");
			bean.setListLength("0");
		}
		
		logger.info("setListLength=="+bean.getListLength()+"appList.size())=="+appList.size());
		bean.setRecordList(appList);
		logger.info("setApprflag=="+bean.getApprflag());
		
	}
	
	public boolean changeApplStatus(CashClaimApproval bean, Object [][]empFlow, String claimCode,String empCode){
		boolean result=false;
		
		Object[][] to_mailIds =new Object[1][1];	
		Object[][] from_mailIds =new Object[1][1];	
		if(empFlow!=null ){
			if(empFlow.length!=0){
				Object [][]updateApprover = new Object[1][4];
				updateApprover[0][0]	  = empFlow[0][2];
				updateApprover[0][1]	  = empFlow[0][0];
				updateApprover[0][2]	  = empFlow[0][3];
				updateApprover[0][3]	  = claimCode;
				
				logger.info("level  "+updateApprover[0][0]+" app "+updateApprover[0][1]+" assetCode  "+updateApprover[0][2]);
				
				String updQuery="UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_APPL_LEVEL = ?," +
								"CLAIM_APPROVER=?,CLAIM_ALTER_APPROVER=? WHERE CLAIM_ID=? ";
				result = getSqlModel().singleExecute(updQuery,updateApprover);
				
				try {
					
					to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
					from_mailIds[0][0]=empCode;
												
					MailUtility mail=new MailUtility();
					mail.initiate(context, session);
					logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds,"Cash", "", "P");
									
					mail.terminate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Object[][]statusChanged = new Object[1][2];
				statusChanged[0][0]     = "A";
				statusChanged[0][1]		= claimCode;
				
				String query = "UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_STATUS = ? WHERE CLAIM_ID = ?";
				result = getSqlModel().singleExecute(query,statusChanged);
			    }
		}else{
			Object[][]statusChanged = new Object[1][2];
			statusChanged[0][0]     = "A";
			statusChanged[0][1]		= claimCode;
			
			String query = "UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_STATUS = ? WHERE CLAIM_ID = ?";
			result = getSqlModel().singleExecute(query,statusChanged);
		    }
		getRecords(bean,bean.getStatus());
		return result;
	}
	
	public boolean forward(CashClaimApproval bean, String status, String claimCode, String empCode,String comments) {
		Object [][] changeStatus = new Object[1][5];
		boolean result = false;
		//bean.setRemark();
		changeStatus[0][0] = claimCode;
		changeStatus[0][1] = bean.getUserEmpId();
		changeStatus[0][2] = comments;
		changeStatus[0][3] = status;
		changeStatus[0][4] = claimCode;
		 Object[][] to_mailIds =new Object[1][1];	
			Object[][] from_mailIds =new Object[1][1];	

		
		if(status.equals("A")){
			String insQuery="INSERT INTO HRMS_CASH_CLAIM_PATH (CASH_CODE,APPROVER_CODE,APPR_DATE,CASH_REMARK,CASH_STATUS,CASH_PATH_ID) "
				  +" VALUES(?, ?, TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), ?, ?," +
				   " (SELECT (NVL(MAX(CASH_PATH_ID),0)+1)FROM HRMS_CASH_CLAIM_PATH WHERE CASH_CODE=? ))";
			result = getSqlModel().singleExecute(insQuery,changeStatus);
			
			String updOnHldAmt="UPDATE HRMS_CASH_BALANCE SET CASH_ONHOLD_AMT=0 "
				  			  +" WHERE EMP_ID = "+empCode+"";
			result = getSqlModel().singleExecute(updOnHldAmt);
		}
		else if(String.valueOf(status).equals("R")){
			Object[][]reject = new Object[1][2];
			reject[0][0]	 = String.valueOf(status);
			reject[0][1]	 = String.valueOf(claimCode);
			
			String query = "UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_STATUS = ? WHERE CLAIM_ID = ?";
			result = getSqlModel().singleExecute(query,reject);
			
			String insQuery="INSERT INTO HRMS_CASH_CLAIM_PATH (CASH_CODE,APPROVER_CODE,APPR_DATE,CASH_REMARK,CASH_STATUS,CASH_PATH_ID) "
						  +" VALUES(?, ?, TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), ?, ?," +
						   " (SELECT (NVL(MAX(CASH_PATH_ID),0)+1)FROM HRMS_CASH_CLAIM_PATH WHERE CASH_CODE=? ))";
			result = getSqlModel().singleExecute(insQuery,changeStatus);
			
			String selCashBalAmt="SELECT nvl(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) FROM HRMS_CASH_BALANCE "  
								+" WHERE EMP_ID="+empCode+" ORDER BY CASH_CREDIT_CODE";
			Object balanceAmt[][] = getSqlModel().getSingleResult(selCashBalAmt);
			String amt ="";
			if(balanceAmt.length >0){
				for (int i = 0; i < balanceAmt.length; i++) {
					 amt = String.valueOf(Double.parseDouble(String.valueOf(balanceAmt[i][0])) + 
							 				Double.parseDouble(String.valueOf(balanceAmt[i][1])));
					 
					 String updQuery="UPDATE HRMS_CASH_BALANCE SET CASH_BALANCE_AMT = "+amt+"" +
					 				 " WHERE EMP_ID="+empCode+" ";
					 result = getSqlModel().singleExecute(updQuery);
				}
			}
					String updOnHold = "UPDATE HRMS_CASH_BALANCE SET CASH_ONHOLD_AMT=0 WHERE EMP_ID="+empCode+" ";
					result = getSqlModel().singleExecute(updOnHold);
		}
		getRecords(bean,bean.getStatus());
		return result;
	}

}
