/**
 * 
 */
package org.paradyne.model.common;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.ModelBase;

/**
 * @author sunil
 * @Date 03-04-2007
 * 
 */
public class UserProfileModel extends ModelBase { 
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public boolean[] getAccessRights(String menuCode, String profileCode) {

		// QUERY TO GET THE INSERT , UPDATE , ETC RIGHTS
		String centerSql = "SELECT MAX(PROFILE_INSERT_FLAG),MAX(PROFILE_UPDATE_FLAG),MAX(PROFILE_DELETE_FLAG)," 
		+" MAX(PROFILE_VIEW_FLAG)," 
		+" CASE WHEN"
		+" (MAX(PROFILE_INSERT_FLAG)='Y'  OR MAX(PROFILE_UPDATE_FLAG)='Y' OR MAX(PROFILE_DELETE_FLAG)='Y' OR  MAX(PROFILE_VIEW_FLAG)='Y')"
		+" THEN "
		+" MIN(PROFILE_GENERAL_FLAG)"
		+" ELSE"
		+" MAX(PROFILE_GENERAL_FLAG) "
		+" END"
		+" FROM HRMS_PROFILE_DTL" 
		+" WHERE PROFILE_CODE IN("+ profileCode + ") " + " AND MENU_CODE ='" + menuCode + "'";
		Object acessObj[][] = getSqlModel().getSingleResult(centerSql);
		boolean acessFlag[] = new boolean[5];
		try {
			if (acessFlag.length != 0) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j <acessObj.length ; j++) {					
						if (acessObj[j][i].equals("Y")) {
							acessFlag[i] = true;
							break;
						} else {
							acessFlag[i] = false;
						}
					}
				}
			}
		} catch (Exception e) {
		//	e.printStackTrace();

		}
		return acessFlag;
	}
	
	public String getProfileDivision(String profileCode) {
		// RETURN A QUAMA SEPARATED LIST OF CENTERS
		logger.info("ModelBase model------------profilecode"
				+ profileCode);
		Object divisionObj[][]=null;
		String strDivision = "";			

		if(profileCode !=null && profileCode.length()>0 && !profileCode.equals("null")) {
			String divisionSql = "SELECT DISTINCT DIV_CODE FROM HRMS_PROFILE_DIVISION WHERE PROFILE_CODE IN("
				+ profileCode + ")";
			divisionObj= getSqlModel().getSingleResult(divisionSql);	
			
			try {
				if(divisionObj!=null && divisionObj.length>0){	
				for (int i = 0; i < divisionObj.length; i++) {
					strDivision += String.valueOf(divisionObj[i][0]).toString() + ",";
				}
				strDivision = strDivision.substring(0, strDivision.length() - 1);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		logger.info("ModelBase model------------center" + strDivision);
		return strDivision;
	}
	
	public String getProfileCenters(String profileCode) {
		// RETURN A QUAMA SEPARATED LIST OF CENTERS
		logger.info("ModelBase model------------profilecode"
				+ profileCode);
		String centerSql = "SELECT CENTER_CODE,CENTER_CODE  FROM HRMS_PROFILE_CENTER WHERE PROFILE_CODE='"
				+ profileCode + "'";
		Object centerObj[][] = getSqlModel().getSingleResult(centerSql);
		String strCenter = "";
		try {
			for (int i = 0; i < centerObj.length; i++) {
				strCenter += String.valueOf(centerObj[i][0]).toString() + ",";
			}
			strCenter = strCenter.substring(0, strCenter.length() - 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.info("ModelBase model------------center" + strCenter);
		return strCenter;
	}

	public String getProfileEmpTypes(String profileCode) {
		// RETURN A QUAMA SEPARATED LIST OF EMPLOYEE TYPES
		String empSql = "SELECT PROFILE_EMPTYPE,PROFILE_EMPTYPE FROM HRMS_PROFILE_EMPTYPE WHERE PROFILE_CODE='"
				+ profileCode + "'";
		Object empObj[][] = getSqlModel().getSingleResult(empSql);
		String strEmp = "";
		try {
			for (int i = 0; i < empObj.length; i++) {
				strEmp += String.valueOf(empObj[i][0]) + ",";
			}
			strEmp = strEmp.substring(0, strEmp.length() - 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return strEmp;
	}

	public String getProfilePaybill(String profileCode) {
		// RETURN A QUAMA SEPARATED LIST OF PYBILL GROUPS
		String payybillSql = "SELECT PAYBILL_NO, PAYBILL_NO FROM HRMS_PROFILE_PAYBILL WHERE PROFILE_CODE='"
				+ profileCode + "'";
		Object payybillObj[][] = getSqlModel().getSingleResult(payybillSql);
		String strPayybill = "";
		try {
			for (int i = 0; i < payybillObj.length; i++) {
				strPayybill += String.valueOf(payybillObj[i][0]) + ",";
			}
			strPayybill = strPayybill.substring(0, strPayybill.length() - 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return strPayybill;
	}
	String str= "";
	public String selectRecomended(String empId){
			
		String query = " SELECT NVL(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER,0),"
						+" OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME "
						+" FROM HRMS_EMP_OFFC "
						+" LEFT JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID =HRMS_EMP_OFFC.EMP_REPORTING_OFFICER)"
						+" WHERE HRMS_EMP_OFFC.EMP_ID ='"+empId+"' ";
		Object[][] values = getSqlModel().getSingleResult(query);
		if(values.length >0){
			logger.info("Reoprting offcer---------"+String.valueOf(values[0][0]));
			str =String.valueOf(values[0][0])+",";
	//		str += String.valueOf(values[0][0])+",";
			str += selectRecomended(String.valueOf(values[0][0]));
		}
		logger.info("---------------"+str);
		return str;
		
	}
	
	public String generateFormula(String empCode,String formula, String old_new){
	//	formula ="((#C1#+#C4#-(#D5#+#C3#))*#D5#)/20";
		String[] operant =formula.split("#");
		logger.info(""+operant.length);
		
		int creditCount = 0;
		int debitCount = 0;
		String creditStr ="";
		String debitStr ="";
		for (int i = 0; i < operant.length; i++) {
			if(operant[i].startsWith("C")){
				String credit =operant[i].substring(1, operant[i].length());
				creditStr +=credit+",";
		        creditCount++;
			}else if(operant[i].startsWith("D")){
				String debit =operant[i].substring(1, operant[i].length());
		          debitStr +=debit+",";
				debitCount++;
			}
			
		}
		
		String []debitArray = new String[debitCount];
		String []creditArray = new String[creditCount];
		creditCount =0;
		debitCount =0;
		for (int i = 0; i < operant.length; i++) {
			if(operant[i].startsWith("C")){
				
				try {
					creditArray[creditCount] = operant[i].substring(1,
							operant[i].length());
					creditCount++;
				} catch (Exception e) {
					// TODO: handle exception
				}				
				
			}else if(operant[i].startsWith("D")){
				
				try {
					debitArray[debitCount] = operant[i].substring(1, operant[i]
							.length());
					debitCount++;
				} catch (Exception e) {
					// TODO: handle exception
				}				
			}
		}
		
		
		String creditCodes ="0";
		try {
			creditCodes = creditStr.substring(0, creditStr.length() - 1);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		String debitCodes ="0";
		try {
			debitCodes = debitStr.substring(0, debitStr.length() - 1);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		Object[][] creditValues =null;
		Object[][] debitValues = null;
		
			if (!(creditCodes.equals("0"))) {
				
				String creditQuery="";
				if (old_new.equals("")) {
					creditQuery = " SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0)  FROM HRMS_CREDIT_HEAD "
							+ " LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE "
							+ " AND  EMP_ID="
							+ empCode
							+ ") WHERE HRMS_CREDIT_HEAD.CREDIT_CODE IN("
							+ creditCodes + ")";
				}
				else if (old_new.equals("NDA")) {
					creditQuery = " SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT_PRECOMMISSION,0)  FROM HRMS_CREDIT_HEAD "
						+ " LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE "
						+ " AND  EMP_ID="
						+ empCode
						+ ") WHERE HRMS_CREDIT_HEAD.CREDIT_CODE IN("
						+ creditCodes + ")";
				}
				
				else{
					creditQuery = " SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_OLD_AMT,0)  FROM HRMS_CREDIT_HEAD "
						+ " LEFT JOIN HRMS_INCR_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_INCR_CREDIT.CREDIT_CODE "
						+ " AND  EMP_ID="
						+ empCode
						+ ") WHERE HRMS_CREDIT_HEAD.CREDIT_CODE IN("
						+ creditCodes + ")";
				}
				
				
				creditValues = getSqlModel().getSingleResult(creditQuery);
			}
		
			
			if (!(debitCodes.equals("0"))) {
				String debitQuery = " SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(DEBIT_AMT,0)  FROM HRMS_DEBIT_HEAD "
						+ " LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE "
						+ " AND  EMP_ID="
						+ empCode
						+ ") WHERE HRMS_DEBIT_HEAD.DEBIT_CODE IN("
						+ debitCodes
						+ ")";
				debitValues = getSqlModel().getSingleResult(debitQuery);
			}			
			creditCount =0;
			debitCount =0;
			for (int i = 0; i < operant.length; i++) {
				if(operant[i].startsWith("C")){
					
					try {
						for (int j = 0; j < creditValues.length; j++) {
							if (creditArray[creditCount].equals(String
									.valueOf(creditValues[j][0]))) {
								operant[i] = String.valueOf(creditValues[j][1]);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}					
					creditCount++;
				}else if(operant[i].startsWith("D")){
					
					try {
						for (int j = 0; j < debitValues.length; j++) {
							if (debitArray[debitCount].equals(String
									.valueOf(debitValues[j][0]))) {
								operant[i] = String.valueOf(debitValues[j][1]);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}					
					debitCount++;
				
				}
				
			}
		
			String finalFormula ="";
			for (int i = 0; i < operant.length; i++) {
				finalFormula +=operant[i]; 
			}
			
			logger.info("Formula is ----------------------"+finalFormula);
		return finalFormula;
	}
	
	public String generateFormulaPay(Object[][] credit_amount,String formula, String old_new){
		//	formula ="((#C1#+#C4#-(#D5#+#C3#))*#D5#)/20";
			String[] operant =formula.split("#");
			//logger.info(""+operant.length);
			ArrayList creditList =new ArrayList();
			int creditCount = 0;
			int debitCount = 0;
			String creditStr ="";
			String debitStr ="";
			for (int i = 0; i < operant.length; i++) {
				if(operant[i].startsWith("C")){
					String credit =operant[i].substring(1, operant[i].length());
					creditList.add(String.valueOf(credit));
					creditStr +=credit+",";
			        creditCount++;
				}else if(operant[i].startsWith("D")){
					String debit =operant[i].substring(1, operant[i].length());
			          debitStr +=debit+",";
					debitCount++;
				}
				
			}
			
			String []debitArray = new String[debitCount];
			String []creditArray = new String[creditCount];
			creditCount =0;
			debitCount =0;
			for (int i = 0; i < operant.length; i++) {
				if(operant[i].startsWith("C")){
					
					try {
						creditArray[creditCount] = operant[i].substring(1,
								operant[i].length());
						creditCount++;
					} catch (Exception e) {
						// TODO: handle exception
					}				
					
				}else if(operant[i].startsWith("D")){
					
					try {
						debitArray[debitCount] = operant[i].substring(1, operant[i]
								.length());
						debitCount++;
					} catch (Exception e) {
						// TODO: handle exception
					}				
				}
			}
			
			
			String creditCodes ="0";
			try {
				creditCodes = creditStr.substring(0, creditStr.length() - 1);
			} catch (Exception e) {
				// TODO: handle exception
			}		
			
			String debitCodes ="0";
			try {
				debitCodes = debitStr.substring(0, debitStr.length() - 1);
			} catch (Exception e) {
				// TODO: handle exception
			}		
			int index=0;
			Object[][] creditValues =new Object[creditList.size()][2];
			Object[][] debitValues = null;
			
				if (!(creditCodes.equals("0"))) {
					for (int i = 0; i < credit_amount.length; i++) {
						for (int j = 0; j < creditList.size(); j++) {
							if(String.valueOf(credit_amount[i][0]).trim().equals(String.valueOf(creditList.get(j)).trim())){
							creditValues[index][0] = String.valueOf(credit_amount[i][0]);
							creditValues[index][1] = String.valueOf(credit_amount[i][1]);
							index++;
							}
						}
					}
				
				}
			
				
				if (!(debitCodes.equals("0"))) {
					
				}			
				creditCount =0;
				debitCount =0;
				for (int i = 0; i < operant.length; i++) {
					if(operant[i].startsWith("C")){
						
						try {
							for (int j = 0; j < creditValues.length; j++) {
								if (creditArray[creditCount].equals(String
										.valueOf(creditValues[j][0]))) {
									operant[i] = String.valueOf(creditValues[j][1]);
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}					
						creditCount++;
					}else if(operant[i].startsWith("D")){
					
					}
				}
			
				String finalFormula ="";
				for (int i = 0; i < operant.length; i++) {
					if(operant[i].startsWith("C")){
						operant[i]="0";
					}
					finalFormula +=operant[i]; 
				}
				
				//logger.info("Formula is ----------------------"+finalFormula);
			return finalFormula;
		}
		
	public String generateFormulaSal(String empCode,String formula, String ledgerCode,String year){
		//	formula ="((#C1#+#C4#-(#D5#+#C3#))*#D5#)/20";
			String[] operant =formula.split("#");
			logger.info(""+operant.length);
			
			int creditCount = 0;
			int debitCount = 0;
			String creditStr ="";
			String debitStr ="";
			for (int i = 0; i < operant.length; i++) {
				if(operant[i].startsWith("C")){
					String credit =operant[i].substring(1, operant[i].length());
					creditStr +=credit+",";
			        creditCount++;
				}else if(operant[i].startsWith("D")){
					String debit =operant[i].substring(1, operant[i].length());
			          debitStr +=debit+",";
					debitCount++;
				}
				
			}
			
			String []debitArray = new String[debitCount];
			String []creditArray = new String[creditCount];
			creditCount =0;
			debitCount =0;
			for (int i = 0; i < operant.length; i++) {
				if(operant[i].startsWith("C")){
					
					try {
						creditArray[creditCount] = operant[i].substring(1,
								operant[i].length());
						creditCount++;
					} catch (Exception e) {
						// TODO: handle exception
					}				
					
				}else if(operant[i].startsWith("D")){
					
					try {
						debitArray[debitCount] = operant[i].substring(1, operant[i]
								.length());
						debitCount++;
					} catch (Exception e) {
						// TODO: handle exception
					}				
				}
			}
			
			
			String creditCodes ="0";
			try {
				creditCodes = creditStr.substring(0, creditStr.length() - 1);
			} catch (Exception e) {
				// TODO: handle exception
			}		
			
			String debitCodes ="0";
			try {
				debitCodes = debitStr.substring(0, debitStr.length() - 1);
			} catch (Exception e) {
				// TODO: handle exception
			}		
			
			Object[][] creditValues =null;
			Object[][] debitValues = null;
			
				if (!(creditCodes.equals("0"))) {
					
				
						String salQuery="select hrms_sal_credits_"+year+".SAL_CREDIT_CODE,nvl(SAL_AMOUNT,0) "+
						" from hrms_sal_credits_"+year+" "+
						" left join hrms_credit_head on hrms_credit_head.credit_code = " +
						"hrms_sal_credits_"+year+".sal_credit_code "+
						" where SAL_LEDGER_CODE="+ledgerCode+" and emp_id="+empCode+" and  " +
								"  hrms_sal_credits_"+year+".SAL_CREDIT_CODE IN("
							+ creditCodes + ")";
				
					creditValues = getSqlModel().getSingleResult(salQuery);
				}
				
				if (!(debitCodes.equals("0"))) {
								
					 String debQuery =" SELECT HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE,NVL(SAL_AMOUNT,0) "
						 +" FROM HRMS_SAL_DEBITS_"+year+" "
						 +" left JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE)  "
						 +" WHERE SAL_LEDGER_CODE ="+ledgerCode+" and emp_id="+empCode+" and " +
						 		" HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE IN("
							+ debitCodes
							+ ")";
					 				 
					debitValues = getSqlModel().getSingleResult(debQuery);
				}			
				creditCount =0;
				debitCount =0;
				for (int i = 0; i < operant.length; i++) {
					if(operant[i].startsWith("C")){
						
						try {
							for (int j = 0; j < creditValues.length; j++) {
								if (creditArray[creditCount].equals(String
										.valueOf(creditValues[j][0]))) {
									operant[i] = String.valueOf(creditValues[j][1]);
								}
								else{
									operant[i]="0";
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}					
						creditCount++;
					}else if(operant[i].startsWith("D")){
						
						try {
							for (int j = 0; j < debitValues.length; j++) {
								if (debitArray[debitCount].equals(String
										.valueOf(debitValues[j][0]))) {
									operant[i] = String.valueOf(debitValues[j][1]);
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}					
						debitCount++;
					
					}
					
				}
			
				String finalFormula ="";
				for (int i = 0; i < operant.length; i++) {
					finalFormula +=operant[i]; 
				}
				
				logger.info("Formula is ----------------------"+finalFormula);
			return finalFormula;
		}
	
	public void addAlertMessages(String applType,String applCode,String empId){
		
		String insertQuery=" INSERT INTO HRMS_MESSAGES (MESSAGE_ID,MESSAGE_SUBJECT,MESSAGE_DETAILS,MESSAGE_DATE,MESSAGE_APPLICATION_CODE "
				+" ,MESSAGE_TYPE,MESSAGE_TO,MESSAGE_FROM) "
				+" VALUES(SEQ_MESSAGE_ID.NEXTVAL,?,?,SYSDATE ,?,?,?,? )";
		
		String messageFrom="";
		//approved By...
		String userQuery="SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),NVL(HRMS_RANK.RANK_NAME,' '), "
				+" HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+"WHERE EMP_ID="+empId;
		
		
		Object[][] userResult = getSqlModel().getSingleResult(userQuery);
		Object[][] result=null;
		int rows=0;	
		String[] sub=new String[2];
		String[] details=new String[2];
		
		String[] link=new String[2];
		if(applType.equals("ChildClaim")){
			
			//for subject and details
			String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+" HRMS_CHILD_HDR.EMP_ID, "
				+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
				+" NVL(HRMS_RANK.RANK_NAME,' '), CHILD_APP_PENDING_WITH	, " 
				+" HRMS_CHILD_HDR.CHILD_APP_STATUS, TO_CHAR(CHILD_APP_DATE,'DD-MM-YYYY') ,"
				+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
				+" NVL(R1.RANK_NAME,' ')"
				+" FROM HRMS_CHILD_HDR  "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_CHILD_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
				+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_CHILD_HDR.CHILD_APP_PENDING_WITH=OFFC.EMP_ID) " 
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
				+" WHERE CHILD_APP_CODE="+applCode;
			
			result = getSqlModel().getSingleResult(query);
			
			
				
			if(result!=null && result.length>0)	{
			if(String.valueOf(result[0][6]).equals("P")){
				
				//for Recomending Officer
				sub[0]="Children Tution Fee Claim - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
				details[0]="The following Claim is pending for recommendation . Please recommend/reject the application. "; 
				link[0]="#<a target='_blank'    href='../advances/ChildClaimAppr_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
				
				
				//for Applicant
				sub[1]="Children Tution Fee Claim - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
				details[1]="Your Children Tution Fee Claim is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
				//link[1]="#<a target='_blank'    href='../adv<a target='_blank'    target='_blank'   ces/ChildClaimAppr_approve.action'>Click here To view the application</a>";
				
				rows=2;
				messageFrom=String.valueOf(result[0][2]);
			}
				else if(String.valueOf(result[0][6]).equals("C")){
				
				//for Approver Officer
				sub[0]="Children Tution Fee Claim - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  ";
				link[0]="#<a target='_blank'    href='../advances/ChildClaimAppr_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";

				
				//for Applicant
				sub[1]="Children Tution Fee Claim - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
				details[1]="Your Children Tution Fee Claim is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
				//link[1]="#<a target='_blank'    href='../advances/ChildClaimAppr_approve.action'>Click here To view the application</a>";
				
				rows=2;
				messageFrom=empId;
				
				}
			
				else if(String.valueOf(result[0][6]).equals("A")){
				
					
			/*	//for Approver Officer
				sub[0]="Children Tution Fee Claim - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  "
				+"#Applicant Name:"+String.valueOf(result[0][1])
				+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
				+"#Date of application :"+String.valueOf(result[0][7])
				+"#Application details :";
			*/	
				
					
				//for Applicant	
				sub[0]="Children Tution Fee Claim - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
				details[0]="Your Children Tution Fee Claim is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
				//link[0]="#<a target='_blank'    href='../advances/ChildClaimAppr_approve.action'>Click here To view the application</a>";
				
				rows=1;
				messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("M")){
					
					//for Applicant
					sub[0]="Children Tution Fee Claim - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Children Tution Fee Claim is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					//link[0]="#<a target='_blank'    href='../advances/ChildClaimAppr_approve.action'>Click here To view the application</a>";
					
					rows=1;
					messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("G")){
					
					//for Applicant
					sub[0]="Children Tution Fee Claim - Approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Children Tution Fee Claim is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					//link[0]="#<a target='_blank'    href='../advances/ChildClaimAppr_approve.action'>Click here To view the application</a>";
					
					rows=1;
					messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("R")){
					
					//for Applicant
					sub[0]="Children Tution Fee Claim - Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Children Tution Fee Claim is Rejected By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					//link[0]="#<a target='_blank'    href='../advances/ChildClaimAppr_approve.action'>Click here To view the application</a>";
					
					rows=1;
					messageFrom=empId;
				}
		}
			
			
		}//end of ChildClaim if
		
		if(applType.equals("TempGPF")){
		
			String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+" HRMS_TEMP_GPF.EMP_ID, "
				+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
				+" NVL(HRMS_RANK.RANK_NAME,' '), TGPF_PENDING_WITH		, " 
				+" HRMS_TEMP_GPF.TGPF_STATUS, TO_CHAR(TGPF_APPL_DATE,'DD-MM-YYYY') ,"
				+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
				+" NVL(R1.RANK_NAME,' ')"
				+" FROM HRMS_TEMP_GPF  "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_TEMP_GPF.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
				+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_TEMP_GPF.TGPF_PENDING_WITH=OFFC.EMP_ID) " 
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
				+" WHERE TGPF_ID="+applCode;
			
			result = getSqlModel().getSingleResult(query);
			
			
				
			if(result!=null && result.length>0)	{
			if(String.valueOf(result[0][6]).equals("P")){
				
				//for Recomending Officer
				sub[0]="Temoprary GPF Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
				details[0]="The following Claim is pending for recommendation . Please recommend/reject the application. "; 
				link[0]="#<a target='_blank'    href='../advances/GPFTempApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
				
				
				//for Applicant
				sub[1]="Temoprary GPF Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
				details[1]="Your Temoprary GPF Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
			
				
				rows=2;
				messageFrom=String.valueOf(result[0][2]);
			}
				else if(String.valueOf(result[0][6]).equals("C")){
				
				//for Approver Officer
				sub[0]="Temoprary GPF Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  ";
				link[0]="#<a target='_blank'    href='../advances/GPFTempApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
				
				//for Applicant
				sub[1]="Temoprary GPF Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
				details[1]="Your Temoprary GPF Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
			
				
				rows=2;
				messageFrom=empId;
				
				}
			
				else if(String.valueOf(result[0][6]).equals("A")){
				
					
			/*	//for Approver Officer
				sub[0]="Temoprary GPF Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  "
				+"#Applicant Name:"+String.valueOf(result[0][1])
				+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
				+"#Date of application :"+String.valueOf(result[0][7])
				+"#Application details :";
			*/	
				
					
					//for Applicant	
				sub[0]="Temoprary GPF Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
				details[0]="Your Temoprary GPF Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
				
				rows=1;
				messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("M")){
				
					//for Applicant
					sub[0]="Temoprary GPF Application - Approved By  PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Temoprary GPF Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
					
				}
			
				else if(String.valueOf(result[0][6]).equals("G")){
					
					//for Applicant
					sub[0]="Temoprary GPF Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Temoprary GPF Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("R")){
					
					//for Applicant
					sub[0]="Temoprary GPF Application - Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Temoprary GPF Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
		}
					
			
		}//end of TempGPF if
		
		if(applType.equals("FinalGPF")){
			
			String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+" HRMS_FINAL_GPF.EMP_ID, "
				+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
				+" NVL(HRMS_RANK.RANK_NAME,' '), FGPF_PENDING_WITH		, " 
				+" HRMS_FINAL_GPF.FGPF_STATUS, TO_CHAR(FGPF_APPL_DATE,'DD-MM-YYYY') ,"
				+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
				+" NVL(R1.RANK_NAME,' ')"
				+" FROM HRMS_FINAL_GPF  "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_FINAL_GPF.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
				+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_FINAL_GPF.FGPF_PENDING_WITH=OFFC.EMP_ID) " 
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
				+" WHERE FGPF_ID="+applCode;
			
			result = getSqlModel().getSingleResult(query);
			
			
				
			if(result!=null && result.length>0)	{
			if(String.valueOf(result[0][6]).equals("P")){
				
				//for Recomending Officer
				sub[0]="Final GPF Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
				details[0]="The following Claim is pending for recommendation . Please recommend/reject the application. "; 
				link[0]="#<a target='_blank'    href='../advances/GPFFinalApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
				
				
				//for Applicant
				sub[1]="Final GPF Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
				details[1]="Your Final GPF Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
			
				
				rows=2;
				messageFrom=String.valueOf(result[0][2]);
			}
				else if(String.valueOf(result[0][6]).equals("C")){
				
				//for Approver Officer
				sub[0]="Final GPF Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  ";
				link[0]="#<a target='_blank'    href='../advances/GPFFinalApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
				
				//for Applicant
				sub[1]="Final GPF Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
				details[1]="Your Final GPF Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
			
				
				rows=2;
				messageFrom=empId;
				
				}
			
				else if(String.valueOf(result[0][6]).equals("A")){
				
					
			/*	//for Approver Officer
				sub[0]="Final GPF Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  "
				+"#Applicant Name:"+String.valueOf(result[0][1])
				+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
				+"#Date of application :"+String.valueOf(result[0][7])
				+"#Application details :";
			*/	
				
				//for Applicant
				sub[0]="Final GPF Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
				details[0]="Your Final GPF Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
				
				rows=1;
				messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("M")){
					
					//for Applicant
					sub[0]="Final GPF Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Final GPF Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("G")){
					
					//for Applicant
					sub[0]="Final GPF Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Final GPF Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("R")){
					
					//for Applicant
					sub[0]="Final GPF Application - Rejected By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Final GPF Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
		}
					
			
		}
		
		if(applType.equals("MediAdv")){
			
			String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+" HRMS_MEDI_APP.MEDI_EMP_ID, "
				+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
				+" NVL(HRMS_RANK.RANK_NAME,' '), MEDI_PENDING_WITH, " 
				+" HRMS_MEDI_APP.MEDI_FASTATUS, TO_CHAR(MEDI_APP_DATE,'DD-MM-YYYY') ,"
				+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
				+" NVL(R1.RANK_NAME,' ')"
				+" FROM HRMS_MEDI_APP  "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_MEDI_APP.MEDI_EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
				+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_MEDI_APP.MEDI_PENDING_WITH=OFFC.EMP_ID) " 
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
				+" WHERE MEDI_ID="+applCode;
			
			result = getSqlModel().getSingleResult(query);
			
			
			if(result!=null && result.length>0)	{
			
			if(String.valueOf(result[0][6]).equals("P")){
				
				//for Recomending Officer
				sub[0]="Medical Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
				details[0]="The following advance is pending for recommendation . Please recommend/reject the application. "; 
				link[0]="#<a target='_blank'    href='../advances/MedicalApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
				
				
				//for Applicant
				sub[1]="Medical Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
				details[1]="Your Medical Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
			
				
				rows=2;
				messageFrom=String.valueOf(result[0][2]);
			}
				else if(String.valueOf(result[0][6]).equals("C")){
				
				//for Approver Officer
				sub[0]="Medical Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following advance is pending for your approval. Please approve/reject the application.  ";
				link[0]="#<a target='_blank'    href='../advances/MedicalApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
				
				//for Applicant
				sub[1]="Medical Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
				details[1]="Your Medical Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
			
				
				rows=2;
				messageFrom=empId;
				
				}
			
				else if(String.valueOf(result[0][6]).equals("A")){
				
					
			/*	//for Approver Officer
				sub[0]="Medical Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  "
				+"#Applicant Name:"+String.valueOf(result[0][1])
				+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
				+"#Date of application :"+String.valueOf(result[0][7])
				+"#Application details :";
			*/	
				
				//for Applicant
				sub[0]="Medical Advance Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
				details[0]="Your Medical Advance Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
				
				rows=1;
				messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("M")){
					
					//for Applicant
					sub[0]="Medical Advance Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Medical Advance Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("G")){
					
					//for Applicant
					sub[0]="Medical Advance Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Medical Advance Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("R")){
					
					//for Applicant
					sub[0]="Medical Advance Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Medical Advance Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
		}
					
			
		}
		
		if(applType.equals("MediClaim")){
			
			String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+" HRMS_MEDICAL_HDR.EMP_ID, "
				+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
				+" NVL(HRMS_RANK.RANK_NAME,' '), MEDI_PENDING_WITH, " 
				+" HRMS_MEDICAL_HDR.MEDI_STATUS, TO_CHAR(MEDI_APP_DATE,'DD-MM-YYYY') ,"
				+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
				+" NVL(R1.RANK_NAME,' ')"
				+" FROM HRMS_MEDICAL_HDR  "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_MEDICAL_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
				+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_MEDICAL_HDR.MEDI_PENDING_WITH=OFFC.EMP_ID) " 
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
				+" WHERE MEDI_ID="+applCode;
			
			result = getSqlModel().getSingleResult(query);
			
			
			if(result!=null && result.length>0)	{
			
			if(String.valueOf(result[0][6]).equals("P")){
				
				//for Recomending Officer
				sub[0]="Medical Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
				details[0]="The following Claim is pending for recommendation . Please recommend/reject the application. "; 
				link[0]="#<a target='_blank'    href='../advances/MedicalClaimApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
				
				
				//for Applicant
				sub[1]="Medical Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
				details[1]="Your Medical Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
			
				
				rows=2;
				messageFrom=String.valueOf(result[0][2]);
			}
				else if(String.valueOf(result[0][6]).equals("C")){
				
				//for Approver Officer
				sub[0]="Medical Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  ";
				link[0]="#<a target='_blank'    href='../advances/MedicalClaimApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
				
				//for Applicant
				sub[1]="Medical Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
				details[1]="Your Medical Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
			
				
				rows=2;
				messageFrom=empId;
				
				}
			
				else if(String.valueOf(result[0][6]).equals("A")){
				
					
			/*	//for Approver Officer
				sub[0]="Medical Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following claim is pending for your approval. Please approve/reject the application.  "
				+"#Applicant Name:"+String.valueOf(result[0][1])
				+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
				+"#Date of application :"+String.valueOf(result[0][7])
				+"#Application details :";
			*/	
				
				//for Applicant
				sub[0]="Medical Claim Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
				details[0]="Your Medical Claim Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
				
				rows=1;
				messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("M")){
					
					//for Applicant
					sub[0]="Medical Claim Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Medical Claim Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("G")){
					
					//for Applicant
					sub[0]="Medical Claim Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Medical Claim Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("R")){
					
					//for Applicant
					sub[0]="Medical Claim Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your Medical Claim Application is approved By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}	
		}
			
		}
		
			if(applType.equals("LTCAdv")){
			
			String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+" HRMS_LTC_HDR.EMP_ID, "
				+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
				+" NVL(HRMS_RANK.RANK_NAME,' '), LTC_PENDING_WITH, " 
				+" HRMS_LTC_HDR.LTC_STATUS, TO_CHAR(LTC_APPL_DATE,'DD-MM-YYYY') ,"
				+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
				+" NVL(R1.RANK_NAME,' ')"
				+" FROM HRMS_LTC_HDR  "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_LTC_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
				+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_LTC_HDR.LTC_PENDING_WITH=OFFC.EMP_ID) " 
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
				+" WHERE LTC_CODE="+applCode;
			
			result = getSqlModel().getSingleResult(query);
			
			
				
			if(result!=null && result.length>0)	{
			if(String.valueOf(result[0][6]).equals("P")){
				
				//for Recomending Officer
				sub[0]="LTC Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
				details[0]="The following advance is pending for recommendation . Please recommend/reject the application. "; 
				link[0]="#<a target='_blank'    href='../advances/LTCAdvApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
				
				
				//for Applicant
				sub[1]="LTC Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
				details[1]="Your LTC Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
			
				
				rows=2;
				messageFrom=String.valueOf(result[0][2]);
			}
				else if(String.valueOf(result[0][6]).equals("C")){
				
				//for Approver Officer
				sub[0]="LTC Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
				details[0]="The following advance is pending for your approval. Please approve/reject the application.  ";
				link[0]="#<a target='_blank'    href='../advances/LTCAdvApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
				
				//for Applicant
				sub[1]="LTC Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
				details[1]="Your LTC Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
			
				
				rows=2;
				messageFrom=empId;
				
				}
			
				else if(String.valueOf(result[0][6]).equals("A")){
				
					
			
				
				//for Applicant
				sub[0]="LTC Advance Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
				details[0]="Your LTC Advance Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
				
				rows=1;
				messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("M")){
					
					//for Applicant
					sub[0]="LTC Advance Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your LTC Advance Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
				else if(String.valueOf(result[0][6]).equals("G")){
					
					//for Applicant
					sub[0]="LTC Advance Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your LTC Advance Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
			
				else if(String.valueOf(result[0][6]).equals("R")){
					
					//for Applicant
					sub[0]="LTC Advance Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your LTC Advance Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
				}
			}
					
			
		}
			
			if(applType.equals("LTCClaim")){
				
				String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+" HRMS_LTC_CLAIM_HDR.EMP_ID, "
					+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
					+" NVL(HRMS_RANK.RANK_NAME,' '), LTC_PENDING_WITH, " 
					+" HRMS_LTC_CLAIM_HDR.LTC_STATUS, TO_CHAR(LTC_APPL_DATE,'DD-MM-YYYY') ,"
					+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
					+" NVL(R1.RANK_NAME,' ')"
					+" FROM HRMS_LTC_CLAIM_HDR  "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_LTC_CLAIM_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
					+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_LTC_CLAIM_HDR.LTC_PENDING_WITH=OFFC.EMP_ID) " 
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
					+" WHERE LTC_CODE="+applCode;
				
				result = getSqlModel().getSingleResult(query);
				
				
					
				if(result!=null && result.length>0)	{
				if(String.valueOf(result[0][6]).equals("P")){
					
					//for Recomending Officer
					sub[0]="LTC Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
					details[0]="The following claim is pending for recommendation . Please recommend/reject the application. "; 
					link[0]="#<a target='_blank'    href='../advances/LTCClaimApp_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
					
					
					//for Applicant
					sub[1]="LTC Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
					details[1]="Your LTC Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
				
					
					rows=2;
					messageFrom=String.valueOf(result[0][2]);
				}
					else if(String.valueOf(result[0][6]).equals("C")){
					
					//for Approver Officer
					sub[0]="LTC Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
					details[0]="The following claim is pending for your approval. Please approve/reject the application.  ";
					link[0]="#<a target='_blank'    href='../advances/LTCClaimApp_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
					
					//for Applicant
					sub[1]="LTC Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
					details[1]="Your LTC Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
				
					
					rows=2;
					messageFrom=empId;
					
					}
				
					else if(String.valueOf(result[0][6]).equals("A")){
					
					
					//for Applicant
					sub[0]="LTC Claim Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
					details[0]="Your LTC Claim Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
					
					rows=1;
					messageFrom=empId;
					}
				
					else if(String.valueOf(result[0][6]).equals("M")){
						
						//for Applicant
						sub[0]="LTC Claim Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your LTC Claim Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
					else if(String.valueOf(result[0][6]).equals("G")){
						
						//for Applicant
						sub[0]="LTC Claim Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your LTC Claim Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
						
					else if(String.valueOf(result[0][6]).equals("R")){
						
						//for Applicant
						sub[0]="LTC Claim Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your LTC Claim Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
			}
				
			}
			if(applType.equals("DeputAdv")){
				
				String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+" HRMS_DEPUTATION_HDR.EMP_ID, "
					+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
					+" NVL(HRMS_RANK.RANK_NAME,' '), DEPUTATION_PENDING_WITH, " 
					+" HRMS_DEPUTATION_HDR.DEPUTATION_STATUS, TO_CHAR(DEPUTATION_APPL_DATE,'DD-MM-YYYY') ,"
					+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
					+" NVL(R1.RANK_NAME,' ')"
					+" FROM HRMS_DEPUTATION_HDR  "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_DEPUTATION_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
					+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_DEPUTATION_HDR.DEPUTATION_PENDING_WITH=OFFC.EMP_ID) " 
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
					+" WHERE DEPUTATION_CODE="+applCode;
				
				result = getSqlModel().getSingleResult(query);
				
				
					
				if(result!=null && result.length>0)	{
				if(String.valueOf(result[0][6]).equals("P")){
					
					//for Recomending Officer
					sub[0]="Deputation Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
					details[0]="The following advance is pending for recommendation . Please recommend/reject the application. "; 
					link[0]="#<a target='_blank'    href='../advances/DeputationAdvAppr_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
					
					
					//for Applicant
					sub[1]="Deputation Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
					details[1]="Your Deputation Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
				
					
					rows=2;
					messageFrom=String.valueOf(result[0][2]);
				}
					else if(String.valueOf(result[0][6]).equals("C")){
					
					//for Approver Officer
					sub[0]="Deputation Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
					details[0]="The following advance is pending for your approval. Please approve/reject the application.  ";
					link[0]="#<a target='_blank'    href='../advances/DeputationAdvAppr_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
					
					//for Applicant
					sub[1]="Deputation Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
					details[1]="Your Deputation Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
				
					
					rows=2;
					messageFrom=empId;
					
					}
				
					else if(String.valueOf(result[0][6]).equals("A")){
									
						//for Applicant
						sub[0]="Deputation Advance Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Advance Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
				
					else if(String.valueOf(result[0][6]).equals("M")){
						
						//for Applicant
						sub[0]="Deputation Advance Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Advance Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
					else if(String.valueOf(result[0][6]).equals("G")){
						
						//for Applicant
						sub[0]="Deputation Advance Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Advance Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
					else if(String.valueOf(result[0][6]).equals("R")){
						
						//for Applicant
						sub[0]="Deputation Advance Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Advance Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
			}
						
				
			}
			
			if(applType.equals("DeputClaim")){
				
				String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+" HRMS_DEPUTATION_CLAIM_HDR.EMP_ID, "
					+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
					+" NVL(HRMS_RANK.RANK_NAME,' '), DEPUTATION_CLAIM_PENDING_WITH, " 
					+" HRMS_DEPUTATION_CLAIM_HDR.DEPUTATION_CLAIM_STATUS, TO_CHAR(DEPUTATION_CLAIM_APPL_DATE,'DD-MM-YYYY') ,"
					+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
					+" NVL(R1.RANK_NAME,' ')"
					+" FROM HRMS_DEPUTATION_CLAIM_HDR  "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_DEPUTATION_CLAIM_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
					+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_DEPUTATION_CLAIM_HDR.DEPUTATION_CLAIM_PENDING_WITH	=OFFC.EMP_ID) " 
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
					+" WHERE DEPUTATION_CLAIM_CODE="+applCode;
				
				result = getSqlModel().getSingleResult(query);
				
				
					
				if(result!=null && result.length>0)	{
				if(String.valueOf(result[0][6]).equals("P")){
					
					//for Recomending Officer
					sub[0]="Deputation Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
					details[0]="The following claim is pending for recommendation . Please recommend/reject the application. "; 
					link[0]="#<a target='_blank'    href='../advances/LTCClaimApp_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
					
					
					//for Applicant
					sub[1]="Deputation Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
					details[1]="Your Deputation Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
				
					
					rows=2;
					messageFrom=String.valueOf(result[0][2]);
				}
					else if(String.valueOf(result[0][6]).equals("C")){
					
					//for Approver Officer
					sub[0]="Deputation Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
					details[0]="The following claim is pending for your approval. Please approve/reject the application.  ";
					link[0]="#<a target='_blank'    href='../advances/LTCClaimApp_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
					
					//for Applicant
					sub[1]="Deputation Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
					details[1]="Your Deputation Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
				
					
					rows=2;
					messageFrom=empId;
					
					}
				
					else if(String.valueOf(result[0][6]).equals("A")){
						//for Applicant
						sub[0]="Deputation Claim Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Claim Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
				
					else if(String.valueOf(result[0][6]).equals("M")){
						
						//for Applicant
						sub[0]="Deputation Claim Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Claim Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
					else if(String.valueOf(result[0][6]).equals("G")){
						
						//for Applicant
						sub[0]="Deputation Claim Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Claim Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
					else if(String.valueOf(result[0][6]).equals("R")){
						
						//for Applicant
						sub[0]="Deputation Claim Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your Deputation Claim Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
			}
						
				
			}
			
			
				if(applType.equals("HBAAdv")){
				
				String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+" HRMS_HBA_HDR.EMP_ID, "
					+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
					+" NVL(HRMS_RANK.RANK_NAME,' '), HBA_PENDING_WITH, " 
					+" HRMS_HBA_HDR.HBA_STATUS, TO_CHAR(HBA_APPL_DATE,'DD-MM-YYYY') ,"
					+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
					+" NVL(R1.RANK_NAME,' ')"
					+" FROM HRMS_HBA_HDR  "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_HBA_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
					+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_HBA_HDR.HBA_PENDING_WITH=OFFC.EMP_ID) " 
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
					+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
					+" WHERE HBA_ID	="+applCode;
				
				result = getSqlModel().getSingleResult(query);
				
				
				if(result!=null && result.length>0)	{
				
				if(String.valueOf(result[0][6]).equals("P")){
					
					//for Recomending Officer
					sub[0]="HBA Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
					details[0]="The following advance is pending for recommendation . Please recommend/reject the application. "; 
					link[0]="#<a target='_blank'    href='../advances/HBAApprove_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
					
					
					//for Applicant
					sub[1]="HBA Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
					details[1]="Your HBA Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
				
					
					rows=2;
					messageFrom=String.valueOf(result[0][2]);
				}
					else if(String.valueOf(result[0][6]).equals("C")){
					
					//for Approver Officer
					sub[0]="HBA Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
					details[0]="The following advance is pending for your approval. Please approve/reject the application.  ";
					link[0]="#<a target='_blank'    href='../advances/HBAApprove_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
					
					//for Applicant
					sub[1]="HBA Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
					details[1]="Your HBA Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
				
					
					rows=2;
					messageFrom=empId;
					
					}
				
					else if(String.valueOf(result[0][6]).equals("A")){
				
						//for Applicant
						sub[0]="HBA Advance Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your HBA Advance Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
				
					else if(String.valueOf(result[0][6]).equals("M")){
						
						//for Applicant
						sub[0]="HBA Advance Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your HBA Advance Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
					else if(String.valueOf(result[0][6]).equals("G")){
						
						//for Applicant
						sub[0]="HBA Advance Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your HBA Advance Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
					else if(String.valueOf(result[0][6]).equals("R")){
						
						//for Applicant
						sub[0]="HBA Advance Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
						details[0]="Your HBA Advance Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
						
						rows=1;
						messageFrom=empId;
					}
				}
				
			}
				
				if(applType.equals("FestivalAdv")){
					
					String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
						+" HRMS_FESTIVAL_HDR.EMP_ID, "
						+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
						+" NVL(HRMS_RANK.RANK_NAME,' '), FESTIVAL_ADVANCE_PEND_WITH	, " 
						+" HRMS_FESTIVAL_HDR.FESTIVAL_STATUS, TO_CHAR(FESTIVAL_DATE,'DD-MM-YYYY') ,"
						+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
						+" NVL(R1.RANK_NAME,' ')"
						+" FROM HRMS_FESTIVAL_HDR  "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_FESTIVAL_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
						+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_FESTIVAL_HDR.FESTIVAL_ADVANCE_PEND_WITH=OFFC.EMP_ID) " 
						+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
						+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
						+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
						+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
						+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
						+" WHERE FESTIVAL_CODE="+applCode;
					
					result = getSqlModel().getSingleResult(query);
					
					
					if(result!=null && result.length>0)	{
					
					if(String.valueOf(result[0][6]).equals("P")){
						
						//for Recomending Officer
						sub[0]="Festival  Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
						details[0]="The following advance is pending for recommendation . Please recommend/reject the application. "; 
						link[0]="#<a target='_blank'    href='../advances/FestAdvanceApprove_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
						
						
						//for Applicant
						sub[1]="Festival  Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
						details[1]="Your Festival  Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
					
						
						rows=2;
						messageFrom=String.valueOf(result[0][2]);
					}
						else if(String.valueOf(result[0][6]).equals("C")){
						
						//for Approver Officer
						sub[0]="Festival  Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
						details[0]="The following advance is pending for your approval. Please approve/reject the application.  ";
						link[0]="#<a target='_blank'    href='../advances/FestAdvanceApprove_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
						
						//for Applicant
						sub[1]="Festival  Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
						details[1]="Your Festival  Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
					
						
						rows=2;
						messageFrom=empId;
						
						}
					
						else if(String.valueOf(result[0][6]).equals("A")){
				
							//for Applicant
							sub[0]="Festival  Advance Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Festival  Advance Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
					
						else if(String.valueOf(result[0][6]).equals("M")){
							
							//for Applicant
							sub[0]="Festival  Advance Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Festival  Advance Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
						else if(String.valueOf(result[0][6]).equals("G")){
							
							//for Applicant
							sub[0]="Festival  Advance Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Festival  Advance Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
						else if(String.valueOf(result[0][6]).equals("R")){
							
							//for Applicant
							sub[0]="Festival  Advance Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Festival  Advance Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
				}
							
					
				}
				
					if(applType.equals("OtherAdv")){
					
					String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
						+" HRMS_ADVANCE_HDR.EMP_ID, "
						+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
						+" NVL(HRMS_RANK.RANK_NAME,' '), ADV_PENDING_WITH		, " 
						+" HRMS_ADVANCE_HDR.ADV_STATUS	, TO_CHAR(ADV_DATE,'DD-MM-YYYY') ,"
						+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
						+" NVL(R1.RANK_NAME,' ')"
						+" FROM HRMS_ADVANCE_HDR  "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_ADVANCE_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
						+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_ADVANCE_HDR.ADV_PENDING_WITH	=OFFC.EMP_ID) " 
						+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
						+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
						+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
						+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
						+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
						+" WHERE ADV_CODE="+applCode;
					
					result = getSqlModel().getSingleResult(query);
					
					
					if(result!=null && result.length>0)	{
					
					if(String.valueOf(result[0][6]).equals("P")){
						
						//for Recomending Officer
						sub[0]="Other  Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
						details[0]="The following advance is pending for recommendation . Please recommend/reject the application. "; 
						link[0]="#<a target='_blank'    href='../advances/AdvanceSanc_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
						
						
						//for Applicant
						sub[1]="Other  Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
						details[1]="Your Other  Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
					
						
						rows=2;
						messageFrom=String.valueOf(result[0][2]);
					}
						else if(String.valueOf(result[0][6]).equals("C")){
						
						//for Approver Officer
						sub[0]="Other  Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
						details[0]="The following advance is pending for your approval. Please approve/reject the application.  ";
						link[0]="#<a target='_blank'    href='../advances/AdvanceSanc_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
						
						//for Applicant
						sub[1]="Other  Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
						details[1]="Your Other  Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
					
						
						rows=2;
						messageFrom=empId;
						
						}
					
						else if(String.valueOf(result[0][6]).equals("A")){
					
							//for Applicant
							sub[0]="Other  Advance Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Other  Advance Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
					
						else if(String.valueOf(result[0][6]).equals("M")){
							
							//for Applicant
							sub[0]="Other  Advance Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Other  Advance Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
						else if(String.valueOf(result[0][6]).equals("G")){
							
							//for Applicant
							sub[0]="Other  Advance Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Other  Advance Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
						else if(String.valueOf(result[0][6]).equals("R")){
							
							//for Applicant
							sub[0]="Other  Advance Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your Other  Advance Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
				}
							
					
				}
					
					if(applType.equals("TransferApp")){
						
						String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
							+" HRMS_TRANSFER.EMP_ID, "
							+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
							+" NVL(HRMS_RANK.RANK_NAME,' '), TRANSFER_PROPOSED_BY			, " 
							+" HRMS_TRANSFER.TRANSFER_STATUS, TO_CHAR(TRANSFER_APPLICATION_DATE	,'DD-MM-YYYY') ,"
							+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
							+" NVL(R1.RANK_NAME,' ')"
							+" FROM HRMS_TRANSFER  "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
							+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_TRANSFER.TRANSFER_PROPOSED_BY	=OFFC.EMP_ID) " 
							+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
							+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
							+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
							+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
							+" WHERE TRANSFER_CODE="+applCode;
						
						result = getSqlModel().getSingleResult(query);
						
						
						if(result!=null && result.length>0)	{
						
						if(String.valueOf(result[0][6]).equals("P")){
							
							//for Recomending Officer
							sub[0]="Transfer Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
							details[0]="The following application is pending for recommendation . Please recommend/reject the application. "; 
							link[0]="#<a target='_blank'    href='../advances/AdvanceSanc_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
							
							
							//for Applicant
							sub[1]="Transfer Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
							details[1]="Your Transfer Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
						
							
							rows=2;
							messageFrom=String.valueOf(result[0][2]);
						}
							else if(String.valueOf(result[0][6]).equals("C")){
							
							//for Approver Officer
							sub[0]="Transfer Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
							details[0]="The following application is pending for your approval. Please approve/reject the application.  ";
							link[0]="#<a target='_blank'    href='../advances/AdvanceSanc_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
							
							//for Applicant
							sub[1]="Transfer Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
							details[1]="Your Transfer Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
						
							
							rows=2;
							messageFrom=empId;
							
							}
						
							else if(String.valueOf(result[0][6]).equals("A")){
						
								//for Applicant
								sub[0]="Transfer Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your Transfer Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
						
							else if(String.valueOf(result[0][6]).equals("M")){
								
								//for Applicant
								sub[0]="Transfer Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your Transfer Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
							else if(String.valueOf(result[0][6]).equals("G")){
								
								//for Applicant
								sub[0]="Transfer Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your Transfer Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
							else if(String.valueOf(result[0][6]).equals("R")){
								
								//for Applicant
								sub[0]="Transfer Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your Transfer Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
					}
								
						
					}
				
					if(applType.equals("TyDutyAdv")){
					
					String	query =" SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), " 
							+"  HRMS_TYDUTY_PROPOSAL_DTL.EMP_ID, "
							+"  EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
							+"  NVL(HRMS_RANK.RANK_NAME,' '), TYDUTY_PENDING_WITH,  "
							+"  HRMS_TYDUTY_ADVANCE.TYDUTY_ADVANCE_STATUS, TO_CHAR(TYDUTY_APPL_DATE	,'DD-MM-YYYY') , "
							+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), " 
							+"  NVL(R1.RANK_NAME,' ') "
							+"  FROM HRMS_TYDUTY_ADVANCE   "
							 
							+"  INNER JOIN HRMS_TYDUTY_PROPOSAL_DTL ON(HRMS_TYDUTY_PROPOSAL_DTL.TYDUTY_CODE=HRMS_TYDUTY_ADVANCE.TYDUTY_CODE) "  
							+"  INNER JOIN HRMS_EMP_OFFC ON(HRMS_TYDUTY_PROPOSAL_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
							+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_TYDUTY_ADVANCE.TYDUTY_PENDING_WITH=OFFC.EMP_ID) "  
							+"  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) " 
							+"  LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE)  "
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) " 
							+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "  
							+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) " 
							+" WHERE HRMS_TYDUTY_ADVANCE.TYDUTY_CODE="+applCode;
					
					result = getSqlModel().getSingleResult(query);
					
					
					if(result!=null && result.length>0)	{
					
					if(String.valueOf(result[0][6]).equals("P")){
						
						//for Recomending Officer
						sub[0]="TyDuty  Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
						details[0]="The following advance is pending for recommendation . Please recommend/reject the application. "; 
						link[0]="#<a target='_blank'    href='../tyduty/TyDutyAdvApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
						
						
						//for Applicant
						sub[1]="TyDuty  Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
						details[1]="Your TyDuty  Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
					
						
						rows=2;
						messageFrom=String.valueOf(result[0][2]);
					}
						else if(String.valueOf(result[0][6]).equals("C")){
						
						//for Approver Officer
						sub[0]="TyDuty  Advance Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
						details[0]="The following advance is pending for your approval. Please approve/reject the application.  ";
						link[0]="#<a target='_blank'    href='../tyduty/TyDutyAdvApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
						
						//for Applicant
						sub[1]="TyDuty  Advance Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
						details[1]="Your TyDuty  Advance Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
					
						
						rows=2;
						messageFrom=empId;
						
						}
					
						else if(String.valueOf(result[0][6]).equals("A")){
					
							//for Applicant
							sub[0]="TyDuty  Advance Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your TyDuty  Advance Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
					
						else if(String.valueOf(result[0][6]).equals("M")){
							
							//for Applicant
							sub[0]="TyDuty  Advance Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your TyDuty  Advance Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
						else if(String.valueOf(result[0][6]).equals("G")){
							
							//for Applicant
							sub[0]="TyDuty  Advance Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your TyDuty  Advance Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
						else if(String.valueOf(result[0][6]).equals("R")){
							
							//for Applicant
							sub[0]="TyDuty  Advance Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
							details[0]="Your TyDuty  Advance Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
							
							rows=1;
							messageFrom=empId;
						}
					}
							
					
				}
					
					if(applType.equals("TyDutyClaim")){
						
						String	query =" SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), " 
								+"  HRMS_TYDUTY_PROPOSAL_DTL.EMP_ID, "
								+"  EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
								+"  NVL(HRMS_RANK.RANK_NAME,' '), TYDUTY_PENDING_WITH,  "
								+"  HRMS_TYDUTY_ADVANCE.TYDUTY_CLAIM_STATUS	, TO_CHAR(TYDUTY_APPL_DATE	,'DD-MM-YYYY') , "
								+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), " 
								+"  NVL(R1.RANK_NAME,' ') "
								+"  FROM HRMS_TYDUTY_CLAIM   "
								 
								+"  INNER JOIN HRMS_TYDUTY_PROPOSAL_DTL ON(HRMS_TYDUTY_PROPOSAL_DTL.TYDUTY_CODE=HRMS_TYDUTY_CLAIM.TYDUTY_CODE) "  
								+"  INNER JOIN HRMS_EMP_OFFC ON(HRMS_TYDUTY_PROPOSAL_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
								+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_TYDUTY_CLAIM.TYDUTY_PENDING_WITH=OFFC.EMP_ID) "  
								+"  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) " 
								+"  LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE)  "
								+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) " 
								+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "  
								+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) " 
								+" WHERE HRMS_TYDUTY_CLAIM.TYDUTY_CODE="+applCode;
						
						result = getSqlModel().getSingleResult(query);
						
						
						if(result!=null && result.length>0)	{
						
						if(String.valueOf(result[0][6]).equals("P")){
							
							//for Recomending Officer
							sub[0]="TyDuty  Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
							details[0]="The following claim is pending for recommendation . Please recommend/reject the application. "; 
							link[0]="#<a target='_blank'    href='../tyduty/TyDutyClaimApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
							
							
							//for Applicant
							sub[1]="TyDuty  Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
							details[1]="Your TyDuty  Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
						
							
							rows=2;
							messageFrom=String.valueOf(result[0][2]);
						}
							else if(String.valueOf(result[0][6]).equals("C")){
							
							//for Approver Officer
							sub[0]="TyDuty  Claim Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
							details[0]="The following claim is pending for your approval. Please approve/reject the application.  ";
							link[0]="#<a target='_blank'    href='../tyduty/TyDutyClaimApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
							
							//for Applicant
							sub[1]="TyDuty  Claim Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
							details[1]="Your TyDuty  Claim Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
						
							
							rows=2;
							messageFrom=empId;
							
							}
						
							else if(String.valueOf(result[0][6]).equals("A")){
					
								//for Applicant
								sub[0]="TyDuty  Claim Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your TyDuty  Claim Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
						
							else if(String.valueOf(result[0][6]).equals("M")){
								
								//for Applicant
								sub[0]="TyDuty  Claim Application - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your TyDuty  Claim Application is approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
							else if(String.valueOf(result[0][6]).equals("G")){
								
								//for Applicant
								sub[0]="TyDuty  Claim Application - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your TyDuty  Claim Application is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
							else if(String.valueOf(result[0][6]).equals("R")){
								
								//for Applicant
								sub[0]="TyDuty  Claim Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your TyDuty  Claim Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
					}
								
						
					}
					
					if(applType.equals("LeaveApp")){
						
						String	query =" SELECT EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
							+" HRMS_LEAVE_HDR.EMP_ID, "
							+" EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),  "
							+" NVL(HRMS_RANK.RANK_NAME,' '), LEAVE_APPL_PENDING_WITH, " 
							+" HRMS_LEAVE_HDR.LEAVE_STATUS, TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY') ,"
							+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '), "
							+" NVL(R1.RANK_NAME,' ')"
							+" FROM HRMS_LEAVE_HDR  "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_LEAVE_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
							+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_LEAVE_HDR.LEAVE_APPL_PENDING_WITH=OFFC.EMP_ID) " 
							+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
							+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE) "
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
							+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
							+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK) "
							+" WHERE LEAVE_APPL_CODE="+applCode;
						
						result = getSqlModel().getSingleResult(query);
						
						
						if(result!=null && result.length>0)	{
						
						if(String.valueOf(result[0][6]).equals("P")){
							
							//for Recomending Officer
							sub[0]="Leave Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
							details[0]="The following application is pending for recommendation . Please recommend/reject the application. "; 
							link[0]="#<a target='_blank'    href='../leaves/LeaveApproval_approve.action'><font color='blue' >Click here To recomend/reject the application</font></a>";
							
							
							//for Applicant
							sub[1]="Leave Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
							details[1]="Your Leave Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. ";
						
							
							rows=2;
							messageFrom=String.valueOf(result[0][2]);
						}
							else if(String.valueOf(result[0][6]).equals("C")){
							
							//for Approver Officer
							sub[0]="Leave Application - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval ";	
							details[0]="The following application is pending for your approval. Please approve/reject the application.  ";
							link[0]="#<a target='_blank'    href='../leaves/LeaveApproval_approve.action'><font color='blue' >Click here To approve/reject the application</font></a>";
							
							//for Applicant
							sub[1]="Leave Application - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
							details[1]="Your Leave Application is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. ";
						
							
							rows=2;
							messageFrom=empId;
							
							}
						
							else if(String.valueOf(result[0][6]).equals("A")){
							
								//for Applicant
								sub[0]="Leave Application - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your Leave Application is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
						
							else if(String.valueOf(result[0][6]).equals("N")){
								
								//for Applicant
								sub[0]="Leave Application - Cancelled By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your Leave Application is Cancelled By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
						
							else if(String.valueOf(result[0][6]).equals("R")){
								
								//for Applicant
								sub[0]="Leave Application - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
								details[0]="Your Leave Application is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";
								
								rows=1;
								messageFrom=empId;
							}
						}
								
						
					}
					if(applType.equals("TyDutyProp")){
						
						String	query =" SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "  
							+" HRMS_TYDUTY_PROPOSAL.PROPOSAL_INIT_BY,  " 
							+" HRMS_EMP_OFFC.EMP_CENTER||'-'||NVL(HRMS_CENTER.CENTER_NAME,' '),   " 
							+" NVL(HRMS_RANK.RANK_NAME,' '), PROPOSAL_PENDING_WITH,   " 
							+" HRMS_TYDUTY_PROPOSAL.PROPOSAL_STATUS, TO_CHAR(PROPOSAL_APPL_DATE,'DD-MM-YYYY') , " 
							+" T1.TITLE_NAME||' '||NVL(OFFC.EMP_FNAME,' ')||' '||NVL(OFFC.EMP_MNAME,' ')||' '||NVL(OFFC.EMP_LNAME,' '),  " 
							+" NVL(R1.RANK_NAME,' '),HRMS_TYDUTY_PROPOSAL_DTL.EMP_ID ,OFFCDTL.EMP_TOKEN ,  " 
							+" T2.TITLE_NAME||' '||NVL(OFFCDTL.EMP_FNAME,' ')||' '||NVL(OFFCDTL.EMP_MNAME,' ')||' '||NVL(OFFCDTL.EMP_LNAME,' '),  " 
							+" NVL(R2.RANK_NAME,' '),OFFCDTL.EMP_CENTER||'-'||NVL(C1.CENTER_NAME,' ') " 
							+" FROM HRMS_TYDUTY_PROPOSAL  " 
							+" INNER JOIN HRMS_TYDUTY_PROPOSAL_DTL ON (HRMS_TYDUTY_PROPOSAL_DTL.PROPOSAL_CODE = HRMS_TYDUTY_PROPOSAL.PROPOSAL_CODE) " 
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_TYDUTY_PROPOSAL.PROPOSAL_INIT_BY=HRMS_EMP_OFFC.EMP_ID)   " 
							+" INNER JOIN HRMS_EMP_OFFC OFFC ON(HRMS_TYDUTY_PROPOSAL.PROPOSAL_PENDING_WITH=OFFC.EMP_ID)  "  
							+" INNER JOIN HRMS_EMP_OFFC OFFCDTL ON(HRMS_TYDUTY_PROPOSAL_DTL.EMP_ID =OFFCDTL.EMP_ID)  "  
							+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  " 
							+" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=OFFC.EMP_TITLE_CODE)  " 
							+" LEFT JOIN HRMS_TITLE T2 ON(T2.TITLE_CODE=OFFCDTL.EMP_TITLE_CODE)  " 
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  " 
							+" LEFT JOIN HRMS_CENTER C1 ON(C1.CENTER_ID=OFFCDTL.EMP_CENTER)  " 
							+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)   " 
							+" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=OFFC.EMP_RANK)  " 
							+" LEFT JOIN HRMS_RANK R2 ON(R2.RANK_ID=OFFCDTL.EMP_RANK) " 
							+" WHERE HRMS_TYDUTY_PROPOSAL.PROPOSAL_CODE="+applCode;
						
						result = getSqlModel().getSingleResult(query);
						
						
						if(result!=null && result.length>0)	{
							String[]subject=new String[result.length+1];
							String[] subdetails=new String[result.length+1];
							String[] mesgFrom=new String[result.length+1];
							String[] mesgTo=new String[result.length+1];
							int no_of_rows=0;
						
							
						
						if(String.valueOf(result[0][6]).equals("P")){
							
							//for Recomending Officer
							subject[0]="Temporary Duty Proposal - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
							subdetails[0]="The following proposal is pending for recommendation . Please recommend/reject the application. : " 
								+"#Initiated By:"+String.valueOf(result[0][1])
								+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
								+"#Date of application :"+String.valueOf(result[0][7])
								+"#Application details :"
								+"#<a target='_blank'    href='../tyduty/TyDutyApproval_approve.action' target='_blank'><font color='blue' >Click here To recomend/reject the application</font></a>";
							mesgFrom[0]=String.valueOf(result[0][2]);
							mesgTo[0]=String.valueOf(result[0][5]);
							
							//for Applicant
							for (int i = 0; i <result.length; i++) {
								subject[i+1]="Temporary Duty proposal  - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
								subdetails[i+1]="Your Temporary Duty proposal is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. "
								+"#Date of application :"+String.valueOf(result[0][7])
								+"#Application details :";
								mesgFrom[i+1]=String.valueOf(result[i][10]);//from employee himself
								//mesgFrom[i]=String.valueOf(result[i][2]);//from initiating officer 
								mesgTo[i+1]=String.valueOf(result[i][10]);
							}
							
							no_of_rows=result.length+1;
						}

						else if(String.valueOf(result[0][6]).equals("C1")){
							
							//for Recomending Officer
							subject[0]="Temporary Duty Proposal - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for recommendation .";	
							subdetails[0]="The following proposal is pending for recommendation . Please recommend/reject the application. : " 
								+"#Initiated By:"+String.valueOf(result[0][1])
								+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
								+"#Date of application :"+String.valueOf(result[0][7])
								+"#Application details :"
								+"#<a target='_blank'    href='../tyduty/TyDutyApproval_approve.action' ><font color='blue' >Click here To recomend/reject the application</font></font></a>";
							mesgFrom[0]=empId;
							mesgTo[0]=String.valueOf(result[0][5]);
							
							//for Applicant
							for (int i = 0; i < result.length; i++) {
								subject[i+1]="Temporary Duty proposal  - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation";	
								subdetails[i+1]="Your Temporary Duty proposal is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for recommendation. "
								+"#Date of application :"+String.valueOf(result[0][7])
								+"#Application details :";
								mesgFrom[i+1]=empId;//user ID
								mesgTo[i+1]=String.valueOf(result[i][10]);
							}
							no_of_rows=result.length+1;
							
						}
							else if(String.valueOf(result[0][6]).equals("C2")){
							
								//for Recomending Officer
								subject[0]="Temporary Duty Proposal - '"+String.valueOf(result[0][0])+" "+String.valueOf(result[0][1])+" "+String.valueOf(result[0][4])+"'  - pending for approval .";	
								details[0]="The following proposal is pending for approval . Please approve/reject the application.#Initiated By : " 
									+"#Initiated By:"+String.valueOf(result[0][1])
									+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
									+"#Date of application :"+String.valueOf(result[0][7])
									+"#Application details :"
									+"#<a target='_blank'    href='../tyduty/TyDutyApproval_approve.action' ><font color='blue' >Click here To approve/reject the application</font></a>";
								mesgFrom[0]=empId;
								mesgTo[0]=String.valueOf(result[0][5]);
								
								//for Applicant
								for (int i = 0; i < result.length; i++) {
									subject[i+1]="Temporary Duty proposal  - forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval";	
									subdetails[i+1]="Your Temporary Duty proposal is forwarded to '"+String.valueOf(result[0][8])+" "+String.valueOf(result[0][9])+"' for approval. "
									+"#Date of application :"+String.valueOf(result[0][7])
									+"#Application details :";
									mesgFrom[i+1]=empId;//user ID
									mesgTo[i+1]=String.valueOf(result[i][10]);
								}
								no_of_rows=result.length+1;
							}
						
							else if(String.valueOf(result[0][6]).equals("A")){
					
								for (int i = 0; i < result.length; i++) {
														
									//for Applicant
									subject[i]="Temporary Duty proposal - Approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
									subdetails[i]="Your Temporary Duty proposal is approved By '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'"
									+"#Date of application :"+String.valueOf(result[0][7])
									+"#Application details :";
									mesgFrom[i]=empId;//user ID
									mesgTo[i]=String.valueOf(result[i][10]);
								}
								no_of_rows=result.length;
							
							}
						
							else if(String.valueOf(result[0][6]).equals("M")){
								
								for (int i = 0; i < result.length; i++) {
									//for Applicant
									subject[i]="Temporary Duty proposal - Approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
									subdetails[i]="Your Temporary Duty proposal approved By PM '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'"
									+"#Date of application :"+String.valueOf(result[0][7])
									+"#Application details :";
									mesgFrom[i]=empId;//user ID
									mesgTo[i]=String.valueOf(result[i][10]);
								}
								no_of_rows=result.length;
							}
							else if(String.valueOf(result[0][6]).equals("G")){
								for (int i = 0; i < result.length; i++) {
									//for Applicant
									subject[i]="Temporary Duty proposal - Approved By  CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
									subdetails[i]="Your Temporary Duty proposal is approved By CDA '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'"
									+"#Date of application :"+String.valueOf(result[0][7])
									+"#Application details :";
									mesgFrom[i]=empId;//user ID
									mesgTo[i]=String.valueOf(result[i][10]);
								}
							
							}
						
							else if(String.valueOf(result[0][6]).equals("R")){
								for (int i = 0; i < result.length; i++) {
									//for Applicant
									subject[i]="Temporary Duty proposal - Rejected By   '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'";	
									subdetails[i]="Your Temporary Duty proposal is Rejected By  '"+String.valueOf(userResult[0][1])+" "+String.valueOf(userResult[0][2])+"'"
									+"#Date of application :"+String.valueOf(result[0][7])
									+"#Application details :";
									mesgFrom[i]=empId;//user ID
									mesgTo[i]=String.valueOf(result[i][10]);
								}
								no_of_rows=result.length;
							}
						
						Object[][] insertData=new Object[no_of_rows][6] ;
						
						
						
						for (int i = 0; i < no_of_rows; i++) {
							logger.info(subject[i]);	
							logger.info(subdetails[i]);	
							logger.info(mesgTo[i]);	
							logger.info(mesgFrom[i]);	
							
							
							insertData[i][0]= subject[i];
							insertData[i][1]= subdetails[i];
							insertData[i][2]= applCode;
							insertData[i][3]= applType;
							insertData[i][4]= mesgTo[i];
							insertData[i][5]= mesgFrom[i];
						}
						getSqlModel().singleExecute(insertQuery, insertData);		
						}
						
						
					}
					
					
			
		
		if(rows==2){
		details[0]+="#Applicant Name:"+String.valueOf(result[0][1])
		+"#Rank:"+String.valueOf(result[0][4])+"#Center:"+String.valueOf(result[0][3])
		+"#Date of application :"+String.valueOf(result[0][7])
		+"#Application details :";
		if(link[0]!=null)
			details[0]+=link[0];
		
		details[1]+="#Date of application :"+String.valueOf(result[0][7]);
		//+"#Application details :"
		if(link[1]!=null)
			details[1]+=link[1];
		}
		if(rows==1){
			details[0]+="#Date of application :"+String.valueOf(result[0][7])
			+"#Application details :";
			if(link[0]!=null)
				details[0]+=link[0];
		}
		
		
		
		Object[][] insertData=new Object[rows][6] ;
		if(rows==1){
			
			
			// for  Applicant	
			insertData[0][0]= sub[0]; //Message Subject
			insertData[0][1]= details[0]; //Message Details
			insertData[0][2]= applCode; //Application Code
			insertData[0][3]= applType; //Application Type
			insertData[0][4]= String.valueOf(result[0][2]); //Message To
			insertData[0][5]= messageFrom;//Message From
			
		
		}
		if(rows==2){
			
			// for  Officer	
			insertData[0][0]= sub[0];
			insertData[0][1]= details[0];
			insertData[0][2]= applCode;
			insertData[0][3]= applType;
			insertData[0][4]= String.valueOf(result[0][5]);
			insertData[0][5]= messageFrom;
			
			// for  Applicant	
			insertData[1][0]= sub[1];
			insertData[1][1]= details[1];
			insertData[1][2]= applCode;
			insertData[1][3]= applType;
			insertData[1][4]= String.valueOf(result[0][2]);
			insertData[1][5]= messageFrom;
		}
		
		try {
			if(rows!=0)
			getSqlModel().singleExecute(insertQuery, insertData);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
	
}
	
	public Object[][] selectServiceFlag() {
		logger.info(" inside selectOracleData() ");
		
		String selectSql=" SELECT COMPANY_VAS_FLAG FROM HRMS_COMPANY  ";
		return getSqlModel().getSingleResult(selectSql);
	}

	
	
}