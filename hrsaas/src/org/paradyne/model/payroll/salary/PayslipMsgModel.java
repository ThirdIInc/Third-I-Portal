package org.paradyne.model.payroll.salary;

import org.paradyne.bean.payroll.salary.PayslipMsg;
import org.paradyne.lib.ModelBase;

public class PayslipMsgModel extends ModelBase {

	public boolean add(PayslipMsg payslipMsg) {
		boolean result = false;
		try {
			Object addObj[][] = new Object[1][5];
			addObj[0][0] = payslipMsg.getMsgName();
			addObj[0][1] = payslipMsg.getMonth();
			addObj[0][2] = payslipMsg.getYear();
			if (payslipMsg.getDivisionName().equals("")) {
				//addObj[0][3]="-1";
				addObj[0][3] = "-1";
			} else {
				addObj[0][3] = payslipMsg.getDivisionId();
			}
			System.out.println("addObj[0][4] ......... " + addObj[0][4]);
			if (payslipMsg.getEmpID().equals("")) {
				addObj[0][4] = "-1";
			} else if(payslipMsg.getEmpID()!=null && !payslipMsg.getEmpID().equals("")){
				addObj[0][4] = payslipMsg.getEmpID();
			}
			//Commented bcoz MSG_PAYBILL is not needed
			/*String query = "INSERT INTO HRMS_PAYSLIP_MSG (MSG_ID,MSG_MESSAGE,MSG_MONTH,MSG_YEAR,MSG_PAYBILL,EMP_ID)" +
					" VALUES ((SELECT NVL(MAX(MSG_ID),0)+1 FROM HRMS_PAYSLIP_MSG),'"+addObj[0][0]+"',"+addObj[0][1]+","+addObj[0][2]+","+addObj[0][3]+","+addObj[0][4]+") ";
			System.out.println("insert query-----------"+query);*/
			
			//Added MSG_DIVISION in place of MSG_PAYBILL by Abhijit
			String query = "INSERT INTO HRMS_PAYSLIP_MSG (MSG_ID,MSG_MESSAGE,MSG_MONTH,MSG_YEAR,MSG_DIVISION,EMP_ID)"
					+ " VALUES ((SELECT NVL(MAX(MSG_ID),0)+1 FROM HRMS_PAYSLIP_MSG),'"
					+ addObj[0][0]
					+ "',"
					+ addObj[0][1]
					+ ","
					+ addObj[0][2]
					+ "," + addObj[0][3] + "," + addObj[0][4] + ") ";
			//Ended by ABhijit
			/*
			 * if(result.length>0) { return false; }else { Object [] [] addObj = new
			 * Object [1][2]; addObj[0][0]=bean.getCasteName().trim();
			 * addObj[0][1]=bean.getCasteCatgCode().trim(); return
			 * getSqlModel().singleExecute(getQuery(1),addObj); }
			 */
			result = getSqlModel().singleExecute(query);
			if(result){
				result= true;
			}else{
				result= false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(PayslipMsg payslipMsg) {
		//Commented bcoz MSG_PAYBILL is not needed
		/*String query = "UPDATE HRMS_PAYSLIP_MSG SET MSG_MESSAGE='"+payslipMsg.getMsgName()+"',MSG_MONTH="+payslipMsg.getMonth()+"," +
				"MSG_YEAR="+payslipMsg.getYear()+",MSG_PAYBILL="+payslipMsg.getPayBillNo()+",EMP_ID="+payslipMsg.getEmpID()+" WHERE MSG_ID="+payslipMsg.getMsgID()+" ";
		*/
		
		//Updated MSG_DIVISION in place of MSG_PAYBILL by Abhijit
		String query = "UPDATE HRMS_PAYSLIP_MSG SET MSG_MESSAGE='"+payslipMsg.getMsgName()+"',MSG_MONTH="+payslipMsg.getMonth()+"," +
		"MSG_YEAR="+payslipMsg.getYear()+",MSG_DIVISION="+payslipMsg.getDivisionName()+",EMP_ID="+payslipMsg.getEmpID()+" WHERE MSG_ID="+payslipMsg.getMsgID()+" ";
		//Ended by Abhijit
		
		//Object result[][] = getSqlModel().getSingleResult(query);
		
		/*
		 * if(result.length>0) { return false; }else { Object [] [] addObj = new
		 * Object [1][2]; addObj[0][0]=bean.getCasteName().trim();
		 * addObj[0][1]=bean.getCasteCatgCode().trim(); return
		 * getSqlModel().singleExecute(getQuery(1),addObj); }
		 */
		return getSqlModel().singleExecute(query);
	}
	
	public boolean delete(PayslipMsg payslipMsg)
	{
		
		String query ="DELETE FROM HRMS_PAYSLIP_MSG WHERE MSG_ID="+payslipMsg.getMsgID()+"";
		/*Object [] [] addObj  = new Object [1][1];
		addObj[0][0]=payslipMsg.getCasteCode();  */
		return getSqlModel().singleExecute(query);
	}

	public void data(PayslipMsg payslipMsg) {
		String query ="SELECT MSG_MESSAGE FROM HRMS_PAYSLIP_MSG WHERE MSG_ID="+payslipMsg.getMsgID();
		Object msg[][]=getSqlModel().getSingleResult(query);
		
		payslipMsg.setMsgName(String.valueOf(msg[0][0]));
		
	}

}
