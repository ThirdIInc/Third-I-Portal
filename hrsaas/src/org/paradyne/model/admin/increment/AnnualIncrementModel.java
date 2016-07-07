package org.paradyne.model.admin.increment;

import java.util.ArrayList;
import java.util.Calendar;

import org.paradyne.lib.BeanBase;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.bean.admin.increment.AnnualIncrement;


import java.util.*;

/**
 * 
 * @author sunil
 *
 */

public class AnnualIncrementModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	org.paradyne.bean.admin.increment.AnnualIncrement annIncre=null;
	
	public void search(AnnualIncrement annIncre){
		logger.info("search");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		Object addObj[] =new Object[2];
	 	addObj[0] =annIncre.getMonth();
	 	logger.info("addObj[0]: "+addObj[0]);
	 	addObj[1] =annIncre.getYear();
	 	logger.info("addObj[1]: "+addObj[1]);
		
	 	cal.setTime(Utility.getDate("01-"+addObj[0]+"-"+addObj[1]));
		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		
		logger.info("totalDays: "+totalDays);
	
	/*	String query=" SELECT HRMS_INCR_HDR.EMP_ID,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
			+"	INCR_PAY_BASIC,TO_CHAR(INCR_EFFECTIVE_DATE,'DD-MM-YYYY')"
	  		+"  FROM HRMS_INCR_HDR "
			+"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_INCR_HDR.EMP_ID)"
			+"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
			+"	WHERE INCR_EFFECTIVE_DATE+365< TO_DATE('"+totalDays+"-"+addObj[0]+"-"+addObj[1]+"','DD-MM-YYYY') and EMP_STATUS='S'"
			+"	ORDER BY HRMS_INCR_HDR.EMP_ID ";
*/
		
		String query=" SELECT EMP_ID,NVL(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '),"
					+"	NVL(TO_CHAR(EMP_INCR_DATE,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'))"
			  		+"  FROM  HRMS_EMP_OFFC "
				//	+"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_INCR_HDR.EMP_ID)"
					+"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+"	WHERE NVL(HRMS_EMP_OFFC.EMP_INCR_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)+365 <= TO_DATE('"+totalDays+"-"+addObj[0]+"-"+addObj[1]+"','DD-MM-YYYY') "
					+"	AND EMP_STATUS='S' ORDER BY EMP_ID ";
	
		Object[][] data = getSqlModel().getSingleResult(query);
	
		ArrayList<Object> incrList = new ArrayList<Object>();
		try{
		for(int i=0; i<data.length; i++) {	
			logger.info("data.length: "+data.length);
			/*String date=" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_LEAVE_DTL";
			Object[][] dateData = getSqlModel().getSingleResult(date);
*/
			String eol=" SELECT HRMS_LEAVE_DTL.EMP_ID,NVL(SUM(LEAVE_DAYS),0)  FROM HRMS_LEAVE_DTL "
						+"	LEFT JOIN HRMS_INCR_HDR ON(HRMS_INCR_HDR.EMP_ID=HRMS_LEAVE_DTL.EMP_ID)"
						+"	WHERE LEAVE_CODE=5 AND (LEAVE_FROM_DATE  BETWEEN TO_DATE('"+totalDays+"-"+addObj[0]+"-"+addObj[1]+"','DD-MM-YYYY') AND"
						+"	TO_DATE('"+String.valueOf(data[i][2])+"','DD-MM-YYYY')) and HRMS_LEAVE_DTL.EMP_ID ="+String.valueOf(data[i][0])
						+"	AND LEAVE_DTL_STATUS='A' GROUP BY HRMS_LEAVE_DTL.EMP_ID  ";
			Object[][] eolData = getSqlModel().getSingleResult(eol);
/*
			String totalLeave="	SELECT HRMS_LEAVE_DTL.EMP_ID,NVL(SUM(LEAVE_DAYS),0)  FROM HRMS_LEAVE_DTL "
						+"	LEFT JOIN HRMS_INCR_HDR ON(HRMS_INCR_HDR.EMP_ID=HRMS_LEAVE_DTL.EMP_ID)"
						+"	WHERE  (LEAVE_FROM_DATE  BETWEEN TO_DATE('"+totalDays+"-"+addObj[0]+"-"+addObj[1]+"','DD-MM-YYYY') AND"
						+"	TO_DATE('"+dateData[0][0]+"','DD-MM-YYYY')) and HRMS_LEAVE_DTL.EMP_ID ="+data[i][0]
						+"	GROUP BY HRMS_LEAVE_DTL.EMP_ID  ";
			
			Object[][] totalLeaveData = getSqlModel().getSingleResult(totalLeave);
*/
			String absent=" SELECT COUNT(*) ,ATT_EMP_ID FROM HRMS_DAILY_ATTENDANCE_"+annIncre.getYear() 
					//	+"	LEFT JOIN HRMS_INCR_HDR ON(HRMS_INCR_HDR.EMP_ID=HRMS_DAILY_ATTENDANCE_"+annIncre.getYear()+".ATT_EMP_ID)"
						+"	WHERE  ATT_STATUS='AA' AND ATT_LEAVE_STATUS IS NULL AND ATT_DATE BETWEEN TO_DATE('"+totalDays+"-"+addObj[0]+"-"+addObj[1]+"','DD-MM-YYYY')"
						+"	AND TO_DATE('"+String.valueOf(data[0][2])+"','DD-MM-YYYY') AND ATT_EMP_ID ="+String.valueOf(data[i][0])
						+"	GROUP BY ATT_EMP_ID ";
			
			Object[][] absentData = getSqlModel().getSingleResult(absent);
			logger.info("absentData.length"+absentData.length);
			int eolTotal=0;
		//	int present=0;
			int absentTotal=0;
		
			try {
				if (eolData.length > 0) {
					logger.info("eolData.length" + eolData.length);
					eolTotal = Integer.parseInt(String.valueOf(eolData[0][1]));

					logger.info("total********:" + eolTotal);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			logger.info("total********11111:"+eolTotal);		

			try {
				if (absentData.length > 0) {
					logger.info("absentData.length" + absentData.length);
					absentTotal = Integer.parseInt(String
							.valueOf(absentData[0][0]));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			logger.info("present********:"+absentTotal);
	
			
			String finalDate =" SELECT EMP_ID FROM HRMS_EMP_OFFC "
							+" WHERE NVL(HRMS_EMP_OFFC.EMP_INCR_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)+365+"+eolTotal+absentTotal
							+" <= TO_DATE('"+totalDays+"-"+addObj[0]+"-"+addObj[1]+"','DD-MM-YYYY')"
							+" AND EMP_ID ="+String.valueOf(data[i][0]);
			
			Object[][] finalDateData = getSqlModel().getSingleResult(finalDate);
			
			if (finalDateData.length >0) {
				
				/*String pay = "SELECT HRMS_INCR_HDR.EMP_ID,HRMS_PAYSCALE.PAYSC_ID,INCR_PAY_BASIC FROM HRMS_INCR_HDR "
						+ "	LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_INCR_HDR.EMP_ID)"
						+ "	LEFT JOIN HRMS_PAYSCALE ON(HRMS_PAYSCALE.PAYSC_ID=HRMS_SALARY_MISC.SAL_PAYSCALE)"
						+ "	WHERE INCR_EFFECTIVE_DATE+365< TO_DATE('"
						+ totalDays
						+ "-"
						+ addObj[0]
						+ "-"
						+ addObj[1]
						+ "','DD-MM-YYYY')"
						+ "  AND HRMS_INCR_HDR.EMP_ID ="
						+ data[i][0];*/
				
				String pay = " SELECT HRMS_EMP_OFFC.EMP_ID,HRMS_PAYSCALE.PAYSC_ID,NVL(CREDIT_AMT,0),HRMS_PAYSCALE.PAYSC_NAME FROM HRMS_EMP_OFFC"
							+" LEFT JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_CREDIT.EMP_ID)"
							+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
							+" LEFT JOIN HRMS_PAYSCALE ON(HRMS_PAYSCALE.PAYSC_ID=HRMS_SALARY_MISC.SAL_PAYSCALE)"
							+" WHERE  HRMS_EMP_OFFC.EMP_ID ="+String.valueOf(data[i][0])+" AND CREDIT_CODE =1";
				Object[][] payData = getSqlModel().getSingleResult(pay);
				
				logger.info("payData.length" + payData.length);
				Object[][] payScaleData =null;
				
				try {
					String payScale = "SELECT NVL(PAYSC_START_AMT,0),NVL(PAYSC_INCR_AMT1,0),NVL(PAYSC_FINAL_AMT1,0)," +
							" NVL(PAYSC_INCR_AMT2,0),NVL(PAYSC_FINAL_AMT2,0),NVL(PAYSC_INCR_AMT3,0),"
							+ "	NVL(PAYSC_FINAL_AMT3,0),NVL(PAYSC_INCR_AMT4,0),NVL(PAYSC_FINAL_AMT4,0)," +
							"  NVL(PAYSC_INCR_AMT5,0),NVL(PAYSC_FINAL_AMT5,0) FROM HRMS_PAYSCALE "
							+ "	WHERE PAYSC_ID="+ payData[0][1];
					payScaleData = getSqlModel().getSingleResult(payScale);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if (payScaleData !=null && payScaleData.length >0) {
					logger.info("payScaleData.length"+ payScaleData.length);
					int basic = 0;
					if ((Integer.parseInt(String.valueOf(payData[0][2]))) >= (Integer
							.parseInt(String.valueOf(payScaleData[0][0])))
							&& (Integer.parseInt(String.valueOf(payData[0][2]))) < (Integer
									.parseInt(String
											.valueOf(payScaleData[0][2])))) {

						basic = Integer.parseInt(String.valueOf(payData[0][2]))
								+ Integer.parseInt(String
										.valueOf(payScaleData[0][1]));
					} else if ((Integer.parseInt(String.valueOf(payData[0][2]))) >= (Integer
							.parseInt(String.valueOf(payScaleData[0][2])))
							&& (Integer.parseInt(String.valueOf(payData[0][2]))) < (Integer
									.parseInt(String
											.valueOf(payScaleData[0][4])))) {
						
						basic = Integer.parseInt(String.valueOf(payData[0][2]))
								+ Integer.parseInt(String
										.valueOf(payScaleData[0][3]));
					} else if ((Integer.parseInt(String.valueOf(payData[0][2]))) >= (Integer
							.parseInt(String.valueOf(payScaleData[0][4])))
							&& (Integer.parseInt(String.valueOf(payData[0][2]))) <(Integer
									.parseInt(String
											.valueOf(payScaleData[0][6])))) {

						basic = Integer.parseInt(String.valueOf(payData[0][2]))
								+ Integer.parseInt(String
										.valueOf(payScaleData[0][5]));
					} else if ((Integer.parseInt(String.valueOf(payData[0][2]))) >= (Integer
							.parseInt(String.valueOf(payScaleData[0][6])))
							&& (Integer.parseInt(String.valueOf(payData[0][2]))) < (Integer
									.parseInt(String
											.valueOf(payScaleData[0][8])))) {

						basic = Integer.parseInt(String.valueOf(payData[0][2]))
								+ Integer.parseInt(String
										.valueOf(payScaleData[0][7]));
					} else if ((Integer.parseInt(String.valueOf(payData[0][2]))) >= (Integer
							.parseInt(String.valueOf(payScaleData[0][8])))
							&& (Integer.parseInt(String.valueOf(payData[0][2]))) < (Integer
									.parseInt(String
											.valueOf(payScaleData[0][10])))) {

						basic = Integer.parseInt(String.valueOf(payData[0][2]))
								+ Integer.parseInt(String
										.valueOf(payScaleData[0][9]));
					} else if ((Integer.parseInt(String.valueOf(payData[0][2]))) >= (Integer
							.parseInt(String.valueOf(payScaleData[0][10])))) {
						basic = Integer.parseInt(String.valueOf(payData[0][2]));

					}
					logger.info("finalDateData:" + finalDateData.length);
					
					AnnualIncrement bean1 = new AnnualIncrement();
					
					bean1.setEmpId(String.valueOf(data[i][0]));
					bean1.setEmpName(String.valueOf(data[i][1]));
					bean1.setHiddenEmp(String.valueOf(data[i][1]));
					bean1.setPrevDate(String.valueOf(data[i][2]));
					
					Calendar currentcalendarday = new Utility().getCalanderDate(String.valueOf(data[i][2]));
					currentcalendarday.add(Calendar.DATE, 365+eolTotal+absentTotal);
					String currentDate =String.valueOf(currentcalendarday.get(Calendar.DATE))+ "-"
								+ String.valueOf(currentcalendarday.get(Calendar.MONTH) + 1)
								+ "-" + String.valueOf(currentcalendarday.get(Calendar.YEAR));
					bean1.setCurrDate(currentDate);
					
					bean1.setNonQualified(String.valueOf(eolTotal+absentTotal));
					
					String month =annIncre.getMonth();
					String year = annIncre.getYear();
					String newMonth = (month.equals("12")) ? month="1" : ""+(Integer.parseInt(month)+1);
					String newYear = (month.equals("1")) ? ""+(Integer.parseInt(year)+1) : year;
							
					String effectiveDate="01-"+newMonth+"-"+newYear;
					bean1.setDueDate(effectiveDate);
					
					bean1.setPayScale(String.valueOf(payData[0][1]));
					bean1.setPayScaleName(String.valueOf(payData[0][3]));
					bean1.setCurrBasic(String.valueOf(payData[0][2]));//data
					bean1.setNewBasic(String.valueOf(basic));
					bean1.setDceList("");
					bean1.setDceDate("");
					bean1.setArrearTo("");
					bean1.setDceSrl("");
					bean1.setLock(String.valueOf(data[i][0]));
					bean1.setIslockFlag("false");
					bean1.setIsRemoveFlag("false");
					incrList.add(bean1);
				} //end of payScal id check condition
				
			}
		 
		 } //end for-loop
		}catch (Exception e) {
			e.printStackTrace();
		}
	 annIncre.setIncrList(incrList);
	
	}
		 
	public boolean add(AnnualIncrement bean,String empId[],String currDate[],
			String newBasic[],String nonQualified[],String prevDate[],String currBasic[],String dueDate[],
			String[] dceListArr,String[] dceDateArr,String[] dceSrlArr,String[] lockArr,String[] removeArr,String[] payScale,String[] arrear) {
		
		logger.info("add");
		logger.info("empId:"+empId.length);
		
		boolean insertRec =false;
		Object[][] param = null;
		Object[][] delete = null;
		String[] effectDate = null;
		Object updateOffc[][] =null;
		Object removeIncr[][] =null;
		Object incrBasic[][] =null;
		if(lockArr !=null){
			updateOffc = new Object[lockArr.length][2];
			incrBasic = new Object[lockArr.length][2];
			effectDate = new String [lockArr.length];
		}
		if(removeArr !=null){
			removeIncr = new Object[removeArr.length][3];
			
		}
		if(empId !=null && empId.length>0){
			logger.info("empId.length: "+empId.length);
			
			param = new Object[empId.length][15];
			delete = new Object[empId.length][2];
			for(int i=0; i<empId.length; i++) {
			    param[i][0] = empId[i];
				param[i][1] = prevDate[i];
				param[i][2] = currDate[i];
				param[i][3] = nonQualified[i];
				param[i][4] = dueDate[i];
				param[i][5] = currBasic[i];
				param[i][6] = newBasic[i];
				param[i][7] = bean.getMonth();
				param[i][8] = bean.getYear();
				param[i][9] = dceListArr[i];
				param[i][10] = dceDateArr[i];
				param[i][11] = dceSrlArr[i];
				param[i][12] = "N";
				param[i][13] = payScale[i];
				param[i][14] = arrear[i];
				if(lockArr !=null){//CHECK FOR LOCK
					for (int j = 0; j < lockArr.length; j++) {
						if(lockArr[j].equals(empId[i])){
							param[i][12] ="L";
							updateOffc[j][0] =dueDate[i];
							updateOffc[j][1] =empId[i];
							
							incrBasic[j][0] = newBasic[i];
							incrBasic[j][1] = empId[i];
							effectDate[j] = dueDate[i];
						}
					}
				}
				if(removeArr !=null){ //CHECK FOR REMOVE
					for (int j = 0; j < removeArr.length; j++) {
						if(removeArr[j].equals(empId[i])){
							removeIncr[j][0] =bean.getMonth();
							removeIncr[j][1] =bean.getYear();
							removeIncr[j][2] =empId[i];
							
						}
					}
				}
				
				delete[i][0]=bean.getMonth();
				delete[i][1]=bean.getYear();
		   }//end for loop
			boolean deleteRec=getSqlModel().singleExecute(getQuery(2),delete);
			insertRec=getSqlModel().singleExecute(getQuery(1),param);
			/**
			 * INSERT OLD CREDIT INTO HRMS_INCR_CREDIT
			 */
			backupData(lockArr, effectDate, "OLD");
			
			if(insertRec && lockArr !=null){
				String updateOffcQuery ="UPDATE HRMS_EMP_OFFC SET EMP_INCR_DATE =TO_DATE(?,'DD-MM-YYYY') WHERE EMP_ID =? ";
				getSqlModel().singleExecute(updateOffcQuery,updateOffc);
				/**
				 * UPDATE HRMS_EMP_CREDIT WITH INCRIMENTED VALUE
				 */
				updateEmployeeCredit(incrBasic);
				/**
				 * UPDATE HRMS_INCR_CREDIT WITH INCRIMENTED VALUE
				 */
				backupData(lockArr, effectDate, "NEW");
				
			}
			if(removeArr !=null){
				String updateOffcQuery ="DELETE FROM HRMS_INCR_HDR WHERE INCR_MTH =? AND INCR_YEAR=? AND EMP_ID =? ";
				getSqlModel().singleExecute(updateOffcQuery,removeIncr);
			}
	    }		
		return insertRec;					
	}
	public void viewAnualIncrement(AnnualIncrement bean){
		
		String selectQuery =" SELECT HRMS_INCR_HDR.EMP_ID,"
							+" NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
							+"  TO_CHAR(INCR_PREVIOUS_DATE,'DD-MM-YYYY'), TO_CHAR(INCR_CURR_DATE,'DD-MM-YYYY'),"
							+" NVL(INCR_NONQUALIFIED_DAYS,0),TO_CHAR(INCR_EFFECTIVE_DATE,'DD-MM-YYYY'),"
							+" INCR_PAY_BASIC,INCR_NEW_BASIC,INCR_DCE_LIST,TO_CHAR(INCR_DCE_DATE,'DD-MM-YYYY'),INCR_DCE_SERIAL_NO,"
							+" INCR_PAYSCALE,INCR_ISLOCKED,HRMS_PAYSCALE.PAYSC_NAME,TO_CHAR(INCR_ARREAR_TODATE,'DD-MM-YYYY')"
							+" FROM HRMS_INCR_HDR"
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_INCR_HDR.EMP_ID)"
							+"  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
							+"  LEFT JOIN HRMS_PAYSCALE ON(HRMS_PAYSCALE.PAYSC_ID=HRMS_INCR_HDR.INCR_PAYSCALE) "
							+" WHERE INCR_MTH ="+bean.getMonth()+"  AND INCR_YEAR="+bean.getYear();		
		Object incremntedObj[][] = getSqlModel().getSingleResult(selectQuery);
		
		ArrayList<Object> viewList = new ArrayList<Object>();
			for (int i = 0; i < incremntedObj.length; i++) {
				AnnualIncrement viewIncr = new AnnualIncrement();
				viewIncr.setEmpId(String.valueOf(incremntedObj[i][0]));
				viewIncr.setEmpName(String.valueOf(incremntedObj[i][1]));
				viewIncr.setHiddenEmp(String.valueOf(incremntedObj[i][1]));
				viewIncr.setPrevDate(String.valueOf(incremntedObj[i][2]));
				Object curObj = incremntedObj[i][3];
				if(curObj ==null)
					viewIncr.setCurrDate("");
				else
					viewIncr.setCurrDate(String.valueOf(incremntedObj[i][3]));
				viewIncr.setNonQualified(String.valueOf(incremntedObj[i][4]));
				viewIncr.setDueDate(String.valueOf(incremntedObj[i][5]));
				viewIncr.setCurrBasic(String.valueOf(incremntedObj[i][6]));
				viewIncr.setNewBasic(String.valueOf(incremntedObj[i][7]));
				Object dcelistObj = incremntedObj[i][8];
				if(dcelistObj ==null)
					viewIncr.setDceList("");
				else
					viewIncr.setDceList(String.valueOf(incremntedObj[i][8]));
				
				Object dcedateObj = incremntedObj[i][9];
				if(dcedateObj ==null)
					viewIncr.setDceDate("");
				else
					viewIncr.setDceDate(String.valueOf(incremntedObj[i][9]));
				
				Object dceNoObj = incremntedObj[i][10];
				if(dceNoObj ==null)
					viewIncr.setDceSrl("");
				else
					viewIncr.setDceSrl(String.valueOf(incremntedObj[i][10]));
				
				Object payObj = incremntedObj[i][11];
				if(payObj == null)
					viewIncr.setPayScale("");
				else
					viewIncr.setPayScale(String.valueOf(incremntedObj[i][11]));
				
				Object payNameObj = incremntedObj[i][13];
				if(payNameObj == null || String.valueOf(payNameObj).equals("null"))
					viewIncr.setPayScaleName("");
				else
					viewIncr.setPayScaleName(String.valueOf(incremntedObj[i][13]));
				
				Object arrObj = incremntedObj[i][14];
				if(arrObj == null)
					viewIncr.setArrearTo("");
				else
					viewIncr.setArrearTo(String.valueOf(incremntedObj[i][14]));
				
				if(String.valueOf(incremntedObj[i][12]).equals("L")){
					viewIncr.setIslockFlag("true");
					viewIncr.setIsRemoveFlag("true");
				}
				viewList.add(viewIncr);
			}
		
			bean.setIncrList(viewList);
	}
	/**
	 * METHOD FOR CALCULATE EMPLOYEE CREDIT DEPEND ON NEW BASIC
	 * creditData[0][NEW BASIC],creditData[0][EMPID]
	 * @param creditData[1][2]
	 * @return
	 */
	public boolean updateEmployeeCredit(Object creditData[][]){
		boolean isUpdate =false;
			String updateBasic ="UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT =? WHERE EMP_ID =? AND CREDIT_CODE=1";
			boolean result= getSqlModel().singleExecute(updateBasic,creditData);
			String formulaQuery ="SELECT CREDIT_CODE,CREDIT_FORMULA FROM  HRMS_CREDIT_HEAD "
							 +" WHERE CREDIT_FORMULA IS NOT NULL ORDER BY CREDIT_PRIORITY ";
			Object formulaObj[][] = getSqlModel().getSingleResult(formulaQuery);
			
			Object incrCredit[][] =new Object[1][3];		
			int count =0;
			
			if (formulaObj!=null && formulaObj.length >0) {
				for (int i = 0; i < creditData.length; i++) {
					for (int j = 0; j < formulaObj.length; j++) {
						double amount = 0;

						try {
							amount = Math
									.round((Utility
											.expressionEvaluate(new Utility()
													.generateFormula(
															String
																	.valueOf(creditData[i][1]),
															String
																	.valueOf(formulaObj[j][1]),
															context))));
						} catch (Exception e) {
							// TODO: handle exception
						}
						if (String.valueOf(formulaObj[j][0]).equals("5")) {
							if (amount < 3000) {
								amount = 90;
							} else if (amount > 3000 && amount <= 4499) {
								amount = 125;
							} else if (amount >= 4500 && amount <= 5999) {
								amount = 200;
							} else if (amount >= 6000) {
								amount = 300;
							}
						}
						incrCredit[0][0] = amount;
						incrCredit[0][1] = String.valueOf(creditData[i][1]);
						incrCredit[0][2] = String.valueOf(formulaObj[j][0]);

						//	 count++;
						String updateIncr = "UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT =? WHERE EMP_ID =? AND CREDIT_CODE=?";
						isUpdate = getSqlModel().singleExecute(updateIncr,
								incrCredit);

					}
				}
			}
		return isUpdate;
	}
	
	public boolean backupData(Object []lockArr,Object []dueDate,String oldnew){
		boolean result =false;
		if(lockArr !=null){
			for (int i = 0; i < lockArr.length; i++) {
				String selectOldCredit ="SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,  NVL(CREDIT_AMT,0)  "
					+" FROM HRMS_CREDIT_HEAD LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE "
					+" AND  EMP_ID='"+ lockArr[i]+"'  ) ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
				
				Object oldCreditObj[][] = getSqlModel().getSingleResult(selectOldCredit);
				Object insertOldData[][] = new Object[oldCreditObj.length][4];
				for (int j = 0; j < oldCreditObj.length; j++) {
					insertOldData[j][0] =oldCreditObj[j][1];
					insertOldData[j][1] =oldCreditObj[j][0];
					insertOldData[j][2] =lockArr[i];
					insertOldData[j][3] =dueDate[i];
				}
				String insertOldCredit ="";
				if (oldnew.equals("OLD")) {
					 insertOldCredit = "INSERT INTO HRMS_INCR_CREDIT (CREDIT_OLD_AMT,CREDIT_CODE,EMP_ID,EFFECTIVE_DATE)"
							+ " VALUES(?,?,?,TO_DATE(?,'DD-MM-YYYY'))";
				}else if(oldnew.equals("NEW")){
					insertOldCredit = "UPDATE HRMS_INCR_CREDIT SET CREDIT_NEW_AMT =? WHERE CREDIT_CODE =? AND EMP_ID=? AND EFFECTIVE_DATE =TO_DATE(?,'DD-MM-YYYY')";
							
				}
				result=getSqlModel().singleExecute(insertOldCredit,insertOldData);
				
			}
			
		}
		
		return result;
	}
	
	public void viewAfterAdd(AnnualIncrement bean,String[] empId,String[] hiddenEmp,String[] currDate,String[] newBasic,
							String[] nonQualified,String[] prevDate,String[] currBasic,String[] deuDate,
							String[] payScale,String[] dceListArr,String[] dceDateArr,String[] dceSrlArr,String[] arrear){
		ArrayList afterAddList = new ArrayList();
		

		String selectCredits = " SELECT NVL(CREDIT_AMT,0),NVL(SAL_PAYSCALE,0)  FROM HRMS_EMP_OFFC "
								+"  LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_CREDIT.EMP_ID AND CREDIT_CODE=1)"
								+"  LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID )"
								+" WHERE HRMS_EMP_OFFC.EMP_ID ="+bean.getHiddenEmpCode();
		Object [][]newEmployData = getSqlModel().getSingleResult(selectCredits);
		
		if(empId!=null){
			for (int i = 0; i < empId.length; i++) {
				AnnualIncrement incrBean = new AnnualIncrement();
				incrBean.setEmpId(empId[i]);
				incrBean.setEmpName(hiddenEmp[i]);
				incrBean.setHiddenEmp(hiddenEmp[i]);
				incrBean.setPrevDate(prevDate[i]);
				incrBean.setCurrDate(currDate[i]);
				
				incrBean.setNonQualified(nonQualified[i]);

				incrBean.setDueDate(deuDate[i]);
				
				incrBean.setPayScale(payScale[i]);
				incrBean.setCurrBasic(currBasic[i]);//data
				incrBean.setNewBasic(newBasic[i]);
				incrBean.setArrearTo(arrear[i]);
				incrBean.setDceList("");
				incrBean.setDceDate("");
				incrBean.setDceSrl("");
				incrBean.setLock(empId[i]);
				incrBean.setIslockFlag("false");
				incrBean.setIsRemoveFlag("false");
				afterAddList.add(incrBean);
			}
		}
		AnnualIncrement incrBean = new AnnualIncrement();
		incrBean.setEmpId(bean.getHiddenEmpCode());
		incrBean.setEmpName(bean.getHiddenEmpName());
		incrBean.setHiddenEmp(bean.getHiddenEmpName());
		Object prevObj = bean.getHiddenPrevDate();
		if(prevObj !=null )
			incrBean.setPrevDate(bean.getHiddenPrevDate());
		else
			incrBean.setPrevDate("");
		
		incrBean.setCurrDate("");
		
		incrBean.setNonQualified("0");

		incrBean.setDueDate("");
		if(newEmployData !=null && newEmployData.length >0){
			incrBean.setPayScale(String.valueOf(newEmployData[0][1]));
			incrBean.setCurrBasic(String.valueOf(newEmployData[0][0]));
		}else{
			incrBean.setPayScale("0");
			incrBean.setCurrBasic("");
		}
		incrBean.setNewBasic("");
		incrBean.setDceList("");
		incrBean.setDceDate("");
		incrBean.setDceSrl("");
		incrBean.setLock(bean.getHiddenEmpCode());
		incrBean.setIslockFlag("false");
		incrBean.setIsRemoveFlag("false");
		afterAddList.add(incrBean);
		bean.setIncrList(afterAddList);
	}
	
	
}	


