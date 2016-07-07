/**
 * 
 */
package org.paradyne.model.leave;

import java.util.Vector;
import org.paradyne.bean.leave.LeaveEntitle;
import org.paradyne.lib.ModelBase;

/**
 * @author Lakkichand_Golegaonkar
 * 
 */
/**
 */
public class LeaveEntitleModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	int yearStartWith = 1;
	int month = 0;
	int year = 0;
	

	/**
	 * THIS METHOD IS USED FOR INSERTING BLANK RECORD INTO HRMS_LEAVE_BALANCE
	 * 
	 * @param entitle
	 */
	public void insertBlank(LeaveEntitle entitle) {

		String selQuery = " SELECT DISTINCT HRMS_LEAVE_ENTITLE_TEMP.EMP_ID,HRMS_LEAVE_POLICY_DTL.LEAVE_CODE "
				+ " FROM HRMS_LEAVE_ENTITLE_TEMP  "
				+ " INNER JOIN HRMS_LEAVE_POLICY_DTL  ON(HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE= HRMS_LEAVE_ENTITLE_TEMP.LEAVE_POLICY_CODE  AND LEAVE_APPLICABLE='Y' AND LEAVE_CREDIT_INTERVAL IS NOT NULL ) "
				+ " MINUS  SELECT EMP_ID,LEAVE_CODE  FROM HRMS_LEAVE_BALANCE ";

		Object[][] insObj = getSqlModel().getSingleResult(selQuery);

		if (insObj != null && insObj.length > 0) {
			String insertLeaveQuery = " INSERT INTO HRMS_LEAVE_BALANCE (EMP_ID,LEAVE_CODE,LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE) "
					+ " VALUES(?,?,0,0)";
			getSqlModel().singleExecute(insertLeaveQuery, insObj);
		}

	}// end of insertBlank

	/**
	 * THIS METHOD IS USED FOR LEAVE ENTITLE PROCESS
	 * 
	 * @param entitle
	 * @return String
	 */
	public String checkProcess(LeaveEntitle entitle) {

		String res = "";
		month =Integer.parseInt(entitle.getMonth());
		year =Integer.parseInt(entitle.getYear());
		try {

			Object[] selObj = new Object[3];
			selObj[0] = entitle.getMonth();// entitle month
			selObj[1] = entitle.getYear();// entitle year
			selObj[2] = entitle.getDivisionCode();// division code
			res = "";
			int month = Integer.parseInt(entitle.getMonth());// entitle month
			int year = Integer.parseInt(entitle.getYear());// entitle year
			String selQuery = " SELECT * FROM HRMS_LEAVE_PROCESS WHERE LEAVE_MONTH="
					+ entitle.getMonth()
					+ " AND LEAVE_YEAR="
					+ entitle.getYear()
					+ " AND DIV_CODE= "
					+ entitle.getDivisionCode();
			
			String backupQuery = " INSERT INTO HRMS_LEAVE_HISTORY (EMP_ID,LEAVE_CODE,LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE,LEAVE_HRS_OPENING_BALANCE,LEAVE_HRS_CLOSING_BALANCE,LEAVE_MONTH,LEAVE_YEAR,DIV_CODE) "
					+ " (SELECT HRMS_LEAVE_BALANCE.EMP_ID,LEAVE_CODE,LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE,LEAVE_HRS_OPENING_BALANCE,LEAVE_HRS_CLOSING_BALANCE, "
					+ getPrevMonth(month)
					+ ","
					+ getPrevYear(month, year)
					+ ","
					+ entitle.getDivisionCode()
					+ " FROM HRMS_LEAVE_BALANCE "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  =  HRMS_LEAVE_BALANCE.EMP_ID) "
					+ " WHERE HRMS_EMP_OFFC.EMP_DIV="+entitle.getDivisionCode()+")";// inserting record into HRMS_LEAVE_HISTORY
			Object[][] flag = null;
			flag = getSqlModel().getSingleResult(selQuery);
			if (flag != null && flag.length != 0) {
				res = "Leave for this data already has been processed.";

			} // end of if
			else {
				try {
					getSqlModel().singleExecute(backupQuery);
				} catch (Exception e) {
				}
				processLeave(entitle);

				res = "Leave for this data processed successfully.";
			}// end of else
		} catch (Exception e) {
		}
		return res;

	}// end of checkProcess

	/**
	 * THIS METHOD IS USED FOR LEAVE ENTITLE PROCESS
	 * 
	 * @param entitle
	 * @return boolean
	 */
	private boolean processLeave(LeaveEntitle entitle) {

		try {
			String insertQuery = "INSERT INTO HRMS_LEAVE_PROCESS (LEAVE_MONTH,LEAVE_YEAR,LEAVE_ENTITLEDATE,DIV_CODE) VALUES (?,?,TO_DATE(?,'DD-MM-YYYY'),?)";

			String empIdsQuery = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'AND HRMS_EMP_OFFC.EMP_DIV="
					+ entitle.getDivisionCode() + " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE IS NOT NULL ORDER BY EMP_ID ";

			Object empIdsObj[][] = getSqlModel().getSingleResult(empIdsQuery);

			Object insertTempObj[][] = new Object[empIdsObj.length][3];

			for (int i = 0; i < empIdsObj.length; i++) {

				insertTempObj[i][0] = empIdsObj[i][0];
				insertTempObj[i][1] = "";
				insertTempObj[i][2] = entitle.getDivisionCode();
			}

			String delTempQuery = " DELETE FROM HRMS_LEAVE_ENTITLE_TEMP WHERE DIV_CODE="+entitle.getDivisionCode();//div wise delete 

			getSqlModel().singleExecute(delTempQuery);

			String insertTempQuery = " INSERT INTO HRMS_LEAVE_ENTITLE_TEMP (EMP_ID, LEAVE_POLICY_CODE,DIV_CODE) VALUES(?,?,?)";

			getSqlModel().singleExecute(insertTempQuery, insertTempObj);
		

			String selectQuery = " SELECT EMP_DIVISION, EMP_DEPT, EMP_CENTER, EMP_RANK, EMP_TYPE,EMP_ID, LEAVE_POLICY_CODE, POLICY_SETTING_CODE "
					+ "  FROM HRMS_LEAVE_POLICY_SETTING WHERE EMP_DIVISION= "
					+ entitle.getDivisionCode()
					+ "  ORDER BY "
					+ "  EMP_ID DESC , "
					+ " (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+ "
					+ "(CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
					+ " (CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+  "
					+ " (CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END )+  "
					+ "  (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";

			Object selObj[][] = getSqlModel().getSingleResult(selectQuery);

			String query = "";
			Object[][] policyCodeObj = null;

			logger.info("selObj.length----------------" + selObj.length);

			for (int i = 0; i < selObj.length; i++) {
				policyCodeObj = new Object[selObj.length][1];
				policyCodeObj[i][0] = selObj[i][6];

				query = "	UPDATE HRMS_LEAVE_ENTITLE_TEMP SET LEAVE_POLICY_CODE="
						+ policyCodeObj[i][0]
						+ " WHERE EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC  WHERE  EMP_STATUS='S' AND ";

				if (!(String.valueOf(selObj[i][5]).equals(""))
						&& !(String.valueOf(selObj[i][5]) == null)
						&& !String.valueOf(selObj[i][5]).equals("null")) {
					// if emp id not null
					query += " EMP_ID =" + String.valueOf(selObj[i][5])
							+ " AND ";
				}// end if emp id not null
				else {// emp id is null
					if (!(String.valueOf(selObj[i][4]).equals(""))
							&& !(String.valueOf(selObj[i][4]) == null)
							&& !String.valueOf(selObj[i][4]).equals("null")) {
						// if emp type not null
						query += " EMP_TYPE =" + String.valueOf(selObj[i][4])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][1]).equals(""))
							&& !(String.valueOf(selObj[i][1]) == null)
							&& !String.valueOf(selObj[i][1]).equals("null")) {
						// if dept not null
						query += " EMP_DEPT =" + String.valueOf(selObj[i][1])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][2]).equals(""))
							&& !(String.valueOf(selObj[i][2]) == null)
							&& !String.valueOf(selObj[i][2]).equals("null")) {
						// if branch not null
						query += " EMP_CENTER =" + String.valueOf(selObj[i][2])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][3]).equals(""))
							&& !(String.valueOf(selObj[i][3]) == null)
							&& !String.valueOf(selObj[i][3]).equals("null")) {
						// if desg not null
						query += " EMP_RANK =" + String.valueOf(selObj[i][3])
								+ " AND ";
					}// end if
				}// end else
				query += " EMP_DIV =" + String.valueOf(selObj[i][0]) + " )";

				getSqlModel().singleExecute(query);

			}
			
			String policyCodeQuery = "SELECT LEAVE_POLICY_CODE,NVL(LEAVE_MGTYEAR_MONTH_START,1) ,DECODE(LEAVE_NEWJOINEE_DATE_FLAG,'C','EMP_CONFIRM_DATE','EMP_REGULAR_DATE') FROM HRMS_LEAVE_POLICY_HDR  WHERE HRMS_LEAVE_POLICY_HDR.DIV_CODE="
					+ entitle.getDivisionCode()
					+ " ORDER BY  LEAVE_POLICY_CODE ";

			Object policy_Code[][] = getSqlModel().getSingleResult(
					policyCodeQuery);

		
			/*
			 * 	To update the leave balance of each employee 
			 * 
			 * 
			 */
			String updateBalQuery = " UPDATE HRMS_LEAVE_BALANCE  SET LEAVE_OPENING_BALANCE =CASE WHEN 1=? THEN NVL(LEAVE_OPENING_BALANCE,0)+ "
					+ " LEAST( (DECODE(?,0,9999,?)-NVL(LEAVE_CLOSING_BALANCE,0)) , ? )"
					+ "  WHEN 1=? THEN NVL(LEAVE_CLOSING_BALANCE,0)+? "
					+ " ELSE ? END, "
					+ " LEAVE_CLOSING_BALANCE = CASE WHEN 1=? THEN "
					+ " NVL(LEAVE_CLOSING_BALANCE,0)+ LEAST( (DECODE(?,0,9999,?)-NVL(LEAVE_CLOSING_BALANCE,0)) , ? ) "
					+ " ELSE ? END ,"
					+ " LEAVE_SERV_OPENING_BALANCE =NVL(LEAVE_SERV_OPENING_BALANCE,0)+?"
					//+ " LEAVE_SERV_OPENING_BALANCE= "
					+ " WHERE LEAVE_CODE = ? AND EMP_ID=? ";
		
					/**
					 * 
					 * UPDATE HRMS_LEAVE_BALANCE SET LEAVE_OPENING_BALANCE =
					 * NVL(LEAVE_OPENING_BALANCE,0)+ LEAST( (DECODE('Max
					 * Balance',0,9999,'Max Balance')-NVL(LEAVE_CLOSING_BALANCE,0)) ,
					 * 'Entitled Leaves' ), LEAVE_CLOSING_BALANCE =
					 * NVL(LEAVE_CLOSING_BALANCE,0)+ LEAST( (DECODE('Max
					 * Balance',0,9999,'Max Balance')-NVL(LEAVE_CLOSING_BALANCE,0)) ,
					 * 'Entitled Leaves' ) WHERE LEAVE_CODE = 'lEAVE cODE' AND EMP_ID IN
					 * (SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND
					 * EMP_TYPE = 'leave type' AND "+DOJ_DOC+" <
					 * TO_DATE(?,'DD-MM-YYYY'))
					 * 
					 * 
					 */
			// inserting Blank Records
			insertBlank(entitle);
			
			String no_of_leaves_query="SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_CODE,LEAVE_CREDIT_INTERVAL,LEAVE_CREDIT_TYPE " 
						+" FROM HRMS_LEAVE_POLICY_DTL WHERE HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE=? "//LeavepolicyCode
						+" AND HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE='Y' AND HRMS_LEAVE_POLICY_DTL.LEAVE_CREDIT_INTERVAL IS NOT NULL ";
						
			
			String all_months_entitle_query="SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_CODE,NVL(LEAVE_ENTITLE,0),NVL(LEAVE_MAX_ACCM_UPTO,0),LEAVE_CREDIT_INTERVAL," 
						+" LEAVE_IS_CARRIED_FORWARD,  NVL(LEAVE_CARRY_FORWARD_MAXLIMIT,0),NVL(HRMS_LEAVE_POLICY_ENTITLE.LEAVE_POLICY_MONTH,0), " 
						+" NVL(LEAVE_BEFORE_DAY,15), NVL(LEAVE_ENTITLE_BEFORE,0), NVL(LEAVE_ENTITLE_AFTER,0),LEAVE_CREDIT_TYPE " 
						+" FROM HRMS_LEAVE_POLICY_DTL "
						+" INNER JOIN HRMS_LEAVE_POLICY_ENTITLE ON(HRMS_LEAVE_POLICY_ENTITLE.LEAVE_POLICY_CODE=HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE "
						+" AND HRMS_LEAVE_POLICY_ENTITLE.LEAVE_CODE=HRMS_LEAVE_POLICY_DTL.LEAVE_CODE) "
						+" WHERE HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE=? AND HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=? ";//leavepolicyCode;

				
			Vector<Object> updVector = new Vector<Object>();
			
			for (int i = 0; i < policy_Code.length; i++) {
											
				Object[] policy = new Object[1];
				logger.info("policy_Code[i][0]..."+policy_Code[i][0]);
				
				String DOJ_DOC=String.valueOf(policy_Code[i][2]);
				
				
				policy[0] = policy_Code[i][0];
				yearStartWith = Integer.parseInt(String
						.valueOf(policy_Code[i][1]));
				Object[][] entitle_leave= getSqlModel().getSingleResult(
						no_of_leaves_query, policy);
				for (int j = 0; j < entitle_leave.length; j++) {
					

					String new_joinee_query=" SELECT  HRMS_EMP_OFFC.EMP_ID AS EMP_ID ,TO_CHAR(NVL(HRMS_EMP_OFFC."+DOJ_DOC+" ,HRMS_EMP_OFFC.EMP_REGULAR_DATE),'DD-MM-YYYY') AS DOJ, "
								+" HRMS_LEAVE_BALANCE.LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE "
								+" FROM HRMS_EMP_OFFC " 
								+" INNER JOIN HRMS_LEAVE_BALANCE ON (HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
								+" INNER JOIN HRMS_LEAVE_ENTITLE_TEMP ON (HRMS_LEAVE_ENTITLE_TEMP.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND HRMS_LEAVE_ENTITLE_TEMP.LEAVE_POLICY_CODE=?) "
								+" WHERE EMP_REGULAR_DATE is not null AND HRMS_LEAVE_ENTITLE_TEMP.DIV_CODE=? AND " // and policy is not null
								+" HRMS_LEAVE_BALANCE.LEAVE_CODE=? AND  NVL(HRMS_LEAVE_BALANCE.LEAVE_OPENING_BALANCE,0)+NVL(HRMS_LEAVE_BALANCE.LEAVE_SERV_OPENING_BALANCE,0)=0 ";
							
					String month_date="01-"+get2CharMonth(month)+"-"+year;
					
					
					
					String reg_emp_query=" SELECT  HRMS_EMP_OFFC.EMP_ID AS EMP_ID ,TO_CHAR(NVL(HRMS_EMP_OFFC."+DOJ_DOC+",EMP_REGULAR_DATE),'DD-MM-YYYY') AS DOJ, "
								+" HRMS_LEAVE_BALANCE.LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE "
								+" FROM HRMS_EMP_OFFC INNER JOIN HRMS_LEAVE_BALANCE ON (HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID ) "
								+" INNER JOIN HRMS_LEAVE_ENTITLE_TEMP ON (HRMS_LEAVE_ENTITLE_TEMP.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND HRMS_LEAVE_ENTITLE_TEMP.LEAVE_POLICY_CODE=?) "
								+" WHERE HRMS_LEAVE_ENTITLE_TEMP.DIV_CODE=? AND  "
								+" HRMS_LEAVE_BALANCE.LEAVE_CODE=? AND NVL(HRMS_EMP_OFFC."+DOJ_DOC+",EMP_REGULAR_DATE)<=TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+month_date+"','DD-MM-YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY') AND HRMS_EMP_OFFC.EMP_ID "
								+" NOT IN(SELECT RESIGN_EMP FROM HRMS_RESIGN " 
								+" WHERE EMP_REGULAR_DATE is not null AND RESIGN_SEPR_DATE<=TO_DATE('"+get2CharMonth(month)+"-"+year+"','MM-YYYY') AND RESIGN_WITHDRAWN='N') ";
					Object[] leaveType=new Object[2];
					leaveType[0]=policy_Code[i][0];
					leaveType[1]=entitle_leave[j][0];
					
					Object[][] entitle_leave_policy= getSqlModel().getSingleResult(
							all_months_entitle_query, leaveType);
					
					Object[] emp_obj_param=new Object[3];
					emp_obj_param[0]=policy_Code[i][0];
					emp_obj_param[1]=entitle.getDivisionCode();
					emp_obj_param[2]=entitle_leave[j][0];
					
					/*for (int k = 0; k < emp_obj_param.length; k++) {
						logger.info("...emp_obj_param...."+emp_obj_param[k]);
					}*/
					
					int mth = 0;
					if (yearStartWith == 1) {
						mth = 12;
						
					} // end of if
					else {
						mth = yearStartWith - 1;
					}// end of else
					logger.info("yearStartWith    "+yearStartWith );
					logger.info("yearStartWith    "+mth );
					//boolean processFlag=false;
					/***
					 * FOR NEW JOINEE GROUP
					 */
					if(String.valueOf(entitle_leave[j][2]).trim().equals("PO"))
					{
						logger.info("IF LEAVE TYPE IS POST...    ");
						//Quarter array
						int mgtYearQuarterlyArray[] = { getExactMonth(mth + 3),
								getExactMonth(mth + 6), getExactMonth(mth + 9),
								getExactMonth(mth + 12) };
						//Half year array
						int mgtHalfYearArray[] = { getExactMonth(mth + 6),
								getExactMonth(mth + 12) };
											
						logger.info("month...."+month);
						logger.info("String.valueOf(entitle_leave[j][1])   "+String.valueOf(entitle_leave[j][1]));
						
						for (int k = 0; k < mgtHalfYearArray.length; k++) {
							logger.info("mgtHalfYearArray  :"+mgtYearQuarterlyArray[k]);
						}
						
						
						if((mgtYearQuarterlyArray[0]==month
								|| mgtYearQuarterlyArray[1]==month
								|| mgtYearQuarterlyArray[2]==month
								|| mgtYearQuarterlyArray[3]==month)
								&& String.valueOf(entitle_leave[j][1]).trim().equals("Qu")){
							logger.info("Queterly..."+month);
							//processFlag=true;
							new_joinee_query+=" AND HRMS_EMP_OFFC."+DOJ_DOC+"<=TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+month_date+"','DD-MM-YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY') "
									+" AND HRMS_EMP_OFFC."+DOJ_DOC+" >TO_DATE(TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+month_date+"','DD-MM-YYYY')),-3),'DD-MM-YYYY'),'DD-MM-YYYY')";
						}
						else if((mgtHalfYearArray[0]==month
								|| mgtHalfYearArray[1]==month)
								&& String.valueOf(entitle_leave[j][1]).trim().equals("Hy")){
							//processFlag=true;
							logger.info("HalfYearly..."+month);
							new_joinee_query+=" AND HRMS_EMP_OFFC."+DOJ_DOC+"<=TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+month_date+"','DD-MM-YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY') "
							+" AND HRMS_EMP_OFFC."+DOJ_DOC+" >TO_DATE(TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+month_date+"','DD-MM-YYYY')),-6),'DD-MM-YYYY'),'DD-MM-YYYY')";
						}
						else if(mth==month && String.valueOf(entitle_leave[j][1]).trim().equals("Ye")){
							//processFlag=true;
							logger.info("Yearly..."+month);
							new_joinee_query+=" AND HRMS_EMP_OFFC."+DOJ_DOC+"<=TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+month_date+"','DD-MM-YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY') "
							+" AND HRMS_EMP_OFFC."+DOJ_DOC+" >TO_DATE(TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+month_date+"','DD-MM-YYYY')),-12),'DD-MM-YYYY'),'DD-MM-YYYY')";
						}
						else if(String.valueOf(entitle_leave[j][1]).trim().equals("Mo")){
							new_joinee_query+=" AND TO_CHAR(HRMS_EMP_OFFC."+DOJ_DOC+",'MM-YYYY')='"+get2CharMonth(month)+"-"+year+"'";
							//processFlag=true;
						}
						
						//logger.info("processFlag   "+processFlag);
						/**
						 *  IF THE MONTH PROCESS IS NOT IN QURTER OR HALF_YEAR OR YEAR 
						 */
						//processFlag=true;
						//if(processFlag){
						/**
						 * CARRY FORWARD BLOCK *********** STARTS ***************
						 */
						String[] carry_forwordArray=new String[7];
						if(month==mth){
							/**
							 * 	to check max carry forward balance at year end.
							 *  
							 */
							carry_forwordArray[0]="0";
							carry_forwordArray[1]=String.valueOf(entitle_leave_policy[0][5]);
							carry_forwordArray[2]=String.valueOf(entitle_leave_policy[0][5]);
							carry_forwordArray[5]=String.valueOf(entitle_leave_policy[0][5]);
							carry_forwordArray[6]=String.valueOf(entitle_leave_policy[0][5]);
							if (String.valueOf(entitle_leave_policy[0][4]).trim()
									.equals("Y")) {								
								
								carry_forwordArray[3]="1";
								carry_forwordArray[4]="1";
								
							} // end of if
							else {
								carry_forwordArray[3]="0";
								carry_forwordArray[4]="0";
							}// end of else
						}
						else{
							/**
							 * 	to check max accumulation balance at any point of time.
							 *  
							 */
							carry_forwordArray[0]="1";
							carry_forwordArray[1]=String.valueOf(entitle_leave_policy[0][2]);
							carry_forwordArray[2]=String.valueOf(entitle_leave_policy[0][2]);
							carry_forwordArray[3]="0";
							carry_forwordArray[4]="1";
							carry_forwordArray[5]=String.valueOf(entitle_leave_policy[0][2]);
							carry_forwordArray[6]=String.valueOf(entitle_leave_policy[0][2]);
						}
						/**
						 * CARRY FORWARD BLOCK *********** ENDS ***************
						 */
						
						 
						
						logger.info("new_joinee  : "+new_joinee_query);
						 
						Object[][] new_joinee= getSqlModel().getSingleResult(
								new_joinee_query, emp_obj_param);
						
						/**
						 * THIS IS FOR NEW JOINEE GROUP
						 */
						for (int k = 0; k < new_joinee.length; k++) {

							updVector.add(carry_forwordArray[0]);// CARRY FORWORD
							updVector.add(carry_forwordArray[1]);// Max Balance
							updVector.add(carry_forwordArray[2]);// Max Balance
							
							/* ENTITLEMENT FOR THE NEW JOINEE AS PER THIEIR DATE OF JOINING MONTH
							 */
							String leaveEntitle = getNewJoineeEntitle(String.valueOf(new_joinee[k][1]),entitle_leave_policy);
							
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(carry_forwordArray[3]);
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(carry_forwordArray[4]);
							updVector.add(carry_forwordArray[5]);// Max Balance
							updVector.add(carry_forwordArray[6]);// Max Balance
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves add to service opening balance
							updVector.add(entitle_leave[j][0]);// Leave Code
							updVector.add(new_joinee[k][0]);//Employee Code
							
						}//END OF NEW JOINEE LOOP
						/**
						 * THIS IS FOR REGULAR EMPLOYEE
						 */
						if(new_joinee!=null && new_joinee.length>0){
							String newJoinStr=getValue(new_joinee);
							logger.info("newJoinStr...."+newJoinStr);
							reg_emp_query+= " AND  HRMS_EMP_OFFC.EMP_ID  NOT IN("+newJoinStr+")";
						}
						 
						
						Object[][] reg_emp= getSqlModel().getSingleResult(
								reg_emp_query, emp_obj_param);
						for (int k = 0; k < reg_emp.length; k++) {
							updVector.add(carry_forwordArray[0]);// CARRY FORWORD
							updVector.add(carry_forwordArray[1]);// Max Balance
							updVector.add(carry_forwordArray[2]);// Max Balance
							
							/* ENTITLEMENT FOR REGULAR EMPLOYEE FOR PROCESSING MONTH
							 */
							String leaveEntitle = getRegularEntitle(month,entitle_leave_policy);
							
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(carry_forwordArray[3]);
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(carry_forwordArray[4]);
							updVector.add(carry_forwordArray[5]);// Max Balance
							updVector.add(carry_forwordArray[6]);// Max Balance
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves add to service opening balance
							updVector.add(entitle_leave[j][0]);// Leave Code
							updVector.add(reg_emp[k][0]);// Employee Code
						}//END OF REGULAR EMPLOYEE LOOP
						//}
						
					}//END OF POST LEAVE--------
					else{
						
						/**
						 * CARRY FORWARD BLOCK *********** STARTS ***************
						 */
						String[] carry_forwordArray=new String[7];
						logger.info("month...."+month);
						logger.info("mth.... "+mth);
						if(month==mth){
							/**
							 * 	to check max carry forward balance at year end.
							 *  
							 */
							carry_forwordArray[0]="0";
							carry_forwordArray[1]=String.valueOf(entitle_leave_policy[0][5]);
							carry_forwordArray[2]=String.valueOf(entitle_leave_policy[0][5]);
							carry_forwordArray[5]=String.valueOf(entitle_leave_policy[0][5]);
							carry_forwordArray[6]=String.valueOf(entitle_leave_policy[0][5]);
							if (String.valueOf(entitle_leave_policy[0][4]).trim()
									.equals("Y")) {		
								logger.info("entitle_leave_policy[0][5]...."+entitle_leave_policy[0][5]);
								
								carry_forwordArray[3]="1";
								carry_forwordArray[4]="1";
								
							} // end of if
							else {
								carry_forwordArray[3]="0";
								carry_forwordArray[4]="0";
							}// end of else
						}
						else{
							/**
							 * 	to check max accumulation balance at any point of time.
							 *  
							 */
							//logger.info("entitle_leave_policy[0][2]...."+entitle_leave_policy[0][2]);
							carry_forwordArray[0]="1";
							carry_forwordArray[1]=String.valueOf(entitle_leave_policy[0][2]);
							carry_forwordArray[2]=String.valueOf(entitle_leave_policy[0][2]);
							carry_forwordArray[3]="0";
							carry_forwordArray[4]="1";
							carry_forwordArray[5]=String.valueOf(entitle_leave_policy[0][2]);
							carry_forwordArray[6]=String.valueOf(entitle_leave_policy[0][2]);
						}
						/**
						 * CARRY FORWARD BLOCK *********** ENDS ***************
						 */
						 if(String.valueOf(entitle_leave[j][1]).trim().equals("Mo")){
								new_joinee_query+=" AND TO_CHAR(HRMS_EMP_OFFC."+DOJ_DOC+",'MM-YYYY')='"+get2CharMonth(month)+"-"+year+"'";
								//processFlag=true;
						 	}
						
						Object[][] new_joinee= getSqlModel().getSingleResult(
								new_joinee_query, emp_obj_param);
						/**
						 * THIS IS FOR NEW JOINEE GROUP
						 */
						for (int k = 0; k < new_joinee.length; k++) {
							updVector.add(carry_forwordArray[0]);// CARRY FORWORD
							updVector.add(carry_forwordArray[1]);// Max Balance
							updVector.add(carry_forwordArray[2]);// Max Balance
							//logger.info("carry_forwordArray[2]       "+carry_forwordArray[2]);
							/* ENTITLEMENT FOR THE NEW JOINEE AS PER THEIR DATE OF JOINING MONTH
							 */
							String leaveEntitle = getNewJoineeEntitle(String.valueOf(new_joinee[k][1]),entitle_leave_policy);
							
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(carry_forwordArray[3]);
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled
							updVector.add(carry_forwordArray[4]); 
							updVector.add(carry_forwordArray[5]);// Max Balance
							updVector.add(carry_forwordArray[6]);// Max Balance
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves add to service opening balance
							updVector.add(entitle_leave[j][0]);// Leave Code
							updVector.add(new_joinee[k][0]);// Employee Code
							
						}//END OF NEW JOINEE LOOP
						/**
						 * THIS IS FOR REGULAR EMPLOYEE
						 */
						if(new_joinee!=null && new_joinee.length>0){
							for (int k = 0; k < new_joinee.length; k++) {
							//logger.info("new_joinee..."+new_joinee[k][0]);	
							}
							String newJoinStr=getValue(new_joinee);
							reg_emp_query+=" AND  HRMS_EMP_OFFC.EMP_ID  NOT IN("+newJoinStr+")";
						}
						Object[][] reg_emp= getSqlModel().getSingleResult(
								reg_emp_query, emp_obj_param);
						
						logger.info("emp_obj_param...."+emp_obj_param[0]);
						logger.info("emp_obj_param...."+emp_obj_param[1]);
						logger.info("emp_obj_param...."+emp_obj_param[2]);
						for (int k = 0; k < reg_emp.length; k++) {
							updVector.add(carry_forwordArray[0]);// CARRY FORWARD
							updVector.add(carry_forwordArray[1]);// Max Balance
							updVector.add(carry_forwordArray[2]);// Max Balance
							
							//logger.info("carry_forwordArray[2]  REG     "+carry_forwordArray[2]);
							/* ENTITLEMENT FOR REGULAR EMPLOYEE FOR PROCESSING MONTH
							 */
							String leaveEntitle = getRegularEntitle(month,entitle_leave_policy);
							
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(carry_forwordArray[3]);
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(carry_forwordArray[4]);
							updVector.add(carry_forwordArray[5]);// Max Balance
							updVector.add(carry_forwordArray[6]);// Max Balance
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves
							updVector.add(leaveEntitle);// Entitled Leaves add to service opening balance
							updVector.add(entitle_leave[j][0]);// Leave Code
							updVector.add(reg_emp[k][0]);//Employee Code
						}//END OF REGULAR EMPLOYEE LOOP
						
					}
					
				}
				
			}
			

			// UPDATE OBJECT
			Object[][] updObj = new Object[updVector.size() / 15][15];
			logger.info("updVector.size()......" + updVector.size());
			for (int i = 0; i < updVector.size(); i++) {
				//logger.info("updObj........i"+updVector.get(i));
			}
			try {
				int counter = 0;
				for (int l = 0; l < updVector.size() / 15; l++) {
					updObj[l][0] = Integer.parseInt(String.valueOf(updVector.get(counter++)));//CARRY FORWARD
					updObj[l][1] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//MAX BALANCE
					updObj[l][2] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//MAX BALANCE
					updObj[l][3] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//ENTITLE
					updObj[l][4] = Integer.parseInt(String.valueOf(updVector.get(counter++)));//CARRY FORWARD
					updObj[l][5] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//ENTITLE
					updObj[l][6] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//ENTITLE
					updObj[l][7] = Integer.parseInt(String.valueOf(updVector.get(counter++)));//CARRY FORWARD
					updObj[l][8] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//MAX BALANCE
					updObj[l][9] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//MAX BALANCE
					updObj[l][10] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//ENTITLE
					updObj[l][11] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//ENTITLE
					updObj[l][12] = Double.parseDouble(String.valueOf(updVector.get(counter++)));//ENTITLE
					updObj[l][13] = Integer.parseInt(String.valueOf(updVector.get(counter++)));//LEAVE CODE
					updObj[l][14] = Integer.parseInt(String.valueOf(updVector.get(counter++)));///EMP CODE
				}
				/*for (int k = 0; k < updObj.length; k++) {
					for (int k2 = 0; k2 < updObj[0].length; k2++) {
						try {
							logger.info("updObj...."+
									updObj[k][k2]);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					logger.info("....BREAK...");
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			boolean flag_update=getSqlModel().singleExecute(updateBalQuery, updObj);
			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = entitle.getMonth();// entitle month
			insertObj[0][1] = entitle.getYear();// entitle year
			insertObj[0][2] = entitle.getEntitleDate();// entitle date
			insertObj[0][3] = entitle.getDivisionCode();// division code
			
			if(flag_update){
				getSqlModel().singleExecute(insertQuery, insertObj);
			}

			String delQuery = " DELETE FROM HRMS_LEAVE_ENTITLE_TEMP WHERE DIV_CODE="+entitle.getDivisionCode();

			//getSqlModel().singleExecute(delQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}// end of processLeave


/**
 * 
 * @param joindate
 * @param policy_obj
 * @return leaves to be entitle depends on  onBeforeEntitle and afterEntitle
 */
	public String getNewJoineeEntitle(String joindate, Object[][] policy_obj) {
		/**
		 * Check with the date of joining in respective month
		 */
		String leaveEntitle="";
		int joinDay=Integer.parseInt(joindate.substring(0,2));
		int joinMonth=Integer.parseInt(joindate.substring(3,5));
		for (int i = 0; i < policy_obj.length; i++) {
			if(joinMonth==Integer.parseInt(String.valueOf(policy_obj[i][6]))){
				if(joinDay<=Integer.parseInt(String.valueOf(policy_obj[i][7]))){
					leaveEntitle=String.valueOf(policy_obj[i][8]);
				}else{
					leaveEntitle=String.valueOf(policy_obj[i][9]);
				}
			}
		}
		
		return leaveEntitle;
	}
	/**
	 * 
	 * @param joindate
	 * @param policy_obj
	 * @return leaves to be entitle for the processing month
	 */
	public String getRegularEntitle(int month, Object[][] policy_obj) {
		/**
		 * Check with the date of joining in respective month
		 */
		String leaveEntitle="";
		for (int i = 0; i < policy_obj.length; i++) {
			if(month==Integer.parseInt(String.valueOf(policy_obj[i][6]))){
				leaveEntitle=String.valueOf(policy_obj[i][1]);
			}
		}
		
		return leaveEntitle;
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public String getValue(Object[][] obj) {
		String str = "";
		try {
			for (int i = 0; i < obj.length; i++) {
				str += String.valueOf(obj[i][0]) + ",";
			}// end of loop

			str = str.substring(0, str.length() - 1);
		} catch (RuntimeException e) {
			logger.error("Exception in getValue metjod : " + e);
		}

		return str;

	}

	/**
	 * THIS METHOD IS USED FOR GETTING VALUE OF NEXT MONTH
	 * 
	 * @param mon
	 * @return int
	 */
	public int getNextMonth(int mon) {
		int m = 0;
		if (mon == 12) {
			m = 1;
		}// end of if
		else {
			m = mon + 1;
		}// end of else
		return m;
	}// end of getNextMonth

	/**
	 * THIS METHOD IS USED FOR GETTING VALUE OF YEAR
	 * 
	 * @param mon
	 * @param year
	 * @return int
	 */
	public int getNextYear(int mon, int year) {
		int y = 0;
		if (mon == 12) {
			y = year + 1;
		} // end of if
		else {
			y = year;
		}// end of else
		return y;
	}// end of getNextYear

	/**
	 * THIS METHOD IS USED FOR GETTING VALUE OF MONTH
	 * 
	 * @param mon
	 * @return month
	 */

	public int getExactMonth(int mon) {
		if (mon > 12) {
			return mon - 12;
		}// end of if
		return mon;
	}// end of getExactMonth

	/**
	 * THIS METHOD IS USED FOR GETTING VALUE OF PREVIOUS MONTH
	 * 
	 * @param mon
	 * @return month
	 */
	public int getPrevMonth(int mon) {
		if (mon == 1) {
			return 12;
		}// end of if
		else
			return mon - 1;
	}// end of getPrevMonth

	/**
	 * THIS METHOD IS USED FOR GETTING VALUE OF YEAR
	 * 
	 * @param mon
	 * @param year
	 * @return int
	 */
	public int getPrevYear(int mon, int year) {
		int y = 0;
		if (mon == 1) {
			y = year - 1;
		} // end of if
		else {
			y = year;
		}// end of else
		return y;
	}// end of getNextYear
	
	public String get2CharMonth(int mm) {
		String month=""+mm;
		if(mm<10){
			month="0"+mm;
		}
		return month;
	}

}// end of class

