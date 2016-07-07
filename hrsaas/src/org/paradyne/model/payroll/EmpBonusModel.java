package org.paradyne.model.payroll;

 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.bean.admin.increment.SeniorityList;
import org.paradyne.bean.payroll.*;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public class EmpBonusModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	public boolean saveList(EmpBonus bean,String[] empIdVals, String[] empBonDaysVals,
			String[] empAmtVals ) {

		// String del="SELECT BONUS_CODE,EMP_PAYBILL FROM HRMS_EMP_BONUS WHERE
		// BONUS_CODE="+bean.getBonusCode()+" and
		// EMP_PAYBILL="+bean.getPaybillId()+"";
		Object[][] idObj = new Object[1][2];
		idObj[0][0] = bean.getBonusCode();
		idObj[0][1] = bean.getPaybillId();
		// Object[][] delete = getSqlModel().getSingleResult(del);

		boolean result = getSqlModel().singleExecute(getQuery(8), idObj);
		Object[][] addObj = new Object[empIdVals.length][5];
		for (int i = 0; i < empIdVals.length; i++) {

			//logger.info("Amount =======" + empAmtVals[i]);
			
			addObj[i][0] = empIdVals[i];
			addObj[i][1] = empBonDaysVals[i];
			addObj[i][2] = empAmtVals[i];
			addObj[i][3] = bean.getPaybillId();
			addObj[i][4] = bean.getBonusCode();

			

		}
		boolean result1 =  getSqlModel().singleExecute(getQuery(5), addObj);

		return true;
	}

	public Object[][] viewData(EmpBonus bonus) {

		String quer = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),HRMS_TITLE.TITLE_NAME||' '||NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ')   ,"
				+ " HRMS_EMP_BONUS.BONUS_DAYS_ELIGIBLE,HRMS_EMP_BONUS.BONUS_AMOUNT, HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_BONUS.BONUS_CODE,HRMS_EMP_BONUS.EMP_PAYBILL "
				+ " FROM HRMS_EMP_BONUS "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_BONUS.EMP_ID)"
				+"  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " WHERE HRMS_EMP_BONUS.BONUS_CODE = "
				+ bonus.getBonusCode()
				+ " AND HRMS_EMP_BONUS.EMP_PAYBILL = "
				+ bonus.getPaybillId();
				

		

		Object[][] result = getSqlModel().getSingleResult(quer);
		ArrayList array = new ArrayList();

		for (int i = 0; i < result.length; i++) {
			EmpBonus bean = new EmpBonus();
			bean.setCEmpToken(String.valueOf(result[i][0]));
			bean.setCEmpName(String.valueOf(result[i][1]));

			bean.setCEmpBonusDays(String.valueOf(result[i][2]));
			bean.setCAmount(String.valueOf(result[i][3]));
			array.add(bean);

		}

		bonus.setList(array);
		
		return result;
	}

	
	public void getTableData(EmpBonus bonus, ServletContext context) {

		String query1 = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ')   "
				+ " FROM HRMS_EMP_OFFC " 
				+"  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+" WHERE HRMS_EMP_OFFC.EMP_TYPE="
				+ bonus.getTypeCode()
				+ " AND HRMS_EMP_OFFC.EMP_PAYBILL="
				+ bonus.getPaybillId();
		Object[][] result = getSqlModel().getSingleResult(query1);

		logger.info("empppppppppppppp" + bonus.getTypeCode());
		String fromDate = bonus.getBonusFrom();
		String toDate = bonus.getBonusTo();
		String[][] dateObj = getDateRange(fromDate, toDate);
		// int levDays = 0;

		ArrayList list = new ArrayList();

		try {

			for (int i = 0; i < result.length; i++) {

				EmpBonus bonusBean = new EmpBonus();

				int newNull = 0;
				String isNull = "";
				int bonusdays = 0;
				String eolDays = "";
				int abDays=0;
				int edays=0;
				int newdays = 0;
				int bdays=0;
				int newNulldays=0;
				 bdays = Integer.parseInt(bonus.getBonusDays());
			
				 for (int j = 0; j < dateObj.length; j++) {

					try {
						String query = "SELECT ATT_DATE FROM HRMS_DAILY_ATTENDANCE_"
								+ dateObj[j][0].substring(6, 10)
								+ " WHERE ATT_STATUS='AA' AND ATT_LEAVE_STATUS IS NULL "
								+ " AND ATT_DATE>=TO_DATE('"
								+ dateObj[j][0]
								+ "','DD-MM-YYYY') AND ATT_DATE<=TO_DATE('"
								+ dateObj[j][1]
								+ "','DD-MM-YYYY') AND ATT_EMP_ID="
								+ String.valueOf(result[i][0]);
						
						Object[][] data=null;
					
						
						try {
							data = getSqlModel().getSingleResult(query);
							abDays=data.length;
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						isNull = String.valueOf(abDays);
						newNull =Integer.parseInt(isNull);
						logger.info("newnullllllllllllllll--------"+newNull);
						newNulldays=newNull+newNulldays;
						logger.info("newnullllllllllllllll dayssssssss"+newNulldays);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				
					
				
				}
				
				int newEol=0;
				int newEoldays=0;
		 for (int k = 0; k < dateObj.length; k++) {

						
			//	logger.info("=============="+String.valueOf(dateObj[j][0]));
		 try{
			 String eolQuery= "SELECT SUM(LEAVE_DAYS) FROM HRMS_LEAVE_DTL "
					+" WHERE EMP_ID="+String.valueOf(result[i][0]) 
					+" AND LEAVE_CODE=5 AND LEAVE_FROM_DATE>=TO_DATE( '"
									+ String.valueOf(dateObj[k][0])
									+ "','DD-MM-YYYY') "
					+" AND LEAVE_TO_DATE<=TO_DATE( '"
									+String.valueOf(dateObj[k][1])
									+ "','DD-MM-YYYY')";

			 Object[][] leave1=null;
			 leave1 = getSqlModel().getSingleResult(eolQuery);
			 eolDays = String.valueOf(leave1[0][0]);
			 try {
				
				 if(leave1.length<0 || eolDays.equals("null"))
					{
					 newEol=0;
					 logger.info("edaysssssssssssssssss"+edays);
					}
				 else{
					 newEol =Integer.parseInt(eolDays);
					 logger.info("newEol=============="+newEol);
				 }
				
			 } catch (Exception e) {
			// TODO: handle exception
			 }
		
			
			 newEoldays=newEol+newEoldays;
			 logger.info("newEoldayssssssssssssssss======="+newEoldays);
		
		/*if(leave1.length<0 || LeaveLL.equals("null"))
		{
			newEol=0;
		}
		else{
			
			 newEol=Integer.parseInt(LeaveLL);
			
			 logger.info(" newwwwwwwwwww eollllllllllllllllllll"+newEol); 
			
	    	}
		newdays = newNulldays + newEol;
		System.out
				.println("new daysssssssssssssssssssss" + newdays);

				 }*/
			 }catch(Exception e)
			 {
						 
			 }
		 }
		 
				 newdays=newNulldays+newEoldays;
				 logger.info("newdays======="+newdays);	 
				 bonusdays = bdays - newdays;
					
			
				// logger.info("new bonusdaysssssssss" + bonusdays);
				bonusBean.setCEmpId(String.valueOf(result[i][0]));
				bonusBean.setCEmpToken(String.valueOf(result[i][1]));

				bonusBean.setCEmpName(String.valueOf(result[i][2]));

				/*
				 * if (String.valueOf(data[0][0]).equals("null")) {
				 * bonusBean.setCEmpBonusDays("0"); } else {
				 */
				
				// }
				
				bonusBean.setCEmpBonusDays(String.valueOf(bonusdays));
				String q2 = "SELECT BONUS_FORMULA FROM HRMS_BONUS_PARAMETER WHERE BONUS_CODE="
						+ bonus.getBonusCode();
				Object[][] res = getSqlModel().getSingleResult(q2);
				String formula = String.valueOf(res[0][0]);
				/*logger.info("formulaaaaaaaaa" + formula);*/

				// logger.info("--------------------"+String.valueOf(result[i][0]));
				// for (int j = 0; j < result.length; j++) {
				double amt = Utility.expressionEvaluate(new Utility()
						.generateFormula(String.valueOf(result[i][0]), formula,
								context));

				String dd = "SELECT  MONTHS_BETWEEN (TO_DATE('"+toDate+"','DD-MM-YYYY'),EMP_REGULAR_DATE)FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ String.valueOf(result[i][0]);
				logger.info("service monthssssss====DSDLKJLK;SDGLSDFHGL;HLLUCKY====="+amt);
				Object[][] dd1 = getSqlModel().getSingleResult(dd);
				String ddnew = "0";
				
				try {
					if (dd1[0][0]!= null){
						
						ddnew = String.valueOf(dd1[0][0]);
						ddnew=Utility.twoDecimals(ddnew);
					logger.info("service monthssssssssssssss"+ddnew);
					}
					else{
						
						bonusdays=0;
						bonusBean.setCEmpBonusDays(String.valueOf(bonusdays));
						logger.info("service monthssssss========="+ddnew);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				double new_amt = 0.0;
				
				if (Double.parseDouble(ddnew) < 6) {
					new_amt = 0.0;
					bonusdays=0;
					bonusBean.setCEmpBonusDays(String.valueOf(bonusdays));
				}
				else if (Double.parseDouble(ddnew) <= 12
						&& Double.parseDouble(ddnew) > 6) {
					new_amt = ((Math.min(2500.00, amt) / 12) * Double
							.parseDouble(ddnew))
							/ 30
							* (Double.parseDouble(bonusBean.getCEmpBonusDays()));
					/*logger.info("for new emp");*/

				}			
								
				else {
					new_amt = ((Math.min(2500.00, amt) / 30) * (Double
							.parseDouble(bonusBean.getCEmpBonusDays())));
					/*logger.info("for exp emp");*/
				}
				String calc_amt = Utility.twoDecimals(new_amt);
				
				bonusBean.setCAmount(String.valueOf(calc_amt));
				// }
				list.add(bonusBean);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		bonus.setList(list);

		// logger.info("RESULT SET "+result[0][0]);

	}

	public String[][] getDateRange(String d1, String d2) {
		// 01-01-2007

		int y1 = Integer.parseInt(d1.substring(6, 10));
		int y2 = Integer.parseInt(d2.substring(6, 10));
		int diff = y2 - y1;
		int incr = y1;
		String data[][] = new String[diff + 1][2];
		for (int i = 0; i <= diff; i++) {
			// Start year
			if (i == 0) {
				data[i][0] = d1;
				if (y1 == y2) {
					data[i][1] = d2;
				} else {
					data[i][1] = "31-12-" + y1;
				}
			}

			else if (i == diff) { // end year
				data[i][0] = "01-01-" + y2;
				data[i][1] = d2;
			} else {// middle year
				data[i][0] = "01-01-" + (++incr);
				data[i][1] = "31-12-" + (incr);
			}

		}

		for (int j = 0; j < data.length; j++) {
			logger.info("" + data[j][0] + "-----" + data[j][1]);
		}
		return data;

	}

}
