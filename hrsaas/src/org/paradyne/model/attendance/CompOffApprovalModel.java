package org.paradyne.model.attendance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.paradyne.bean.attendance.CompOffApproval;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;

/**
 * @author balajim date 11-08-2008
 */

public class CompOffApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CompOffApprovalModel.class);
	
	public void callDtl(CompOffApproval bean, String cmpId) {
		ArrayList<Object> compList = new ArrayList<Object>();
		String query = "  SELECT  COMPDTL_PROJECT , TO_CHAR(COMPDTL_PROJECTDATE,'DD-MM-YYYY') , COMPDTL_STARTTIME, COMPDTL_ENDTIME,  " + " DECODE(COMPDTL_MONTH,'1','January','2','Febuary','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'),  " + " COMPDTL_YEAR ,COMPDTL_MONTH	FROM HRMS_COMPOFF_DTL " + " WHERE COMPDTL_COMPID =" + cmpId;
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data.length > 0) {
			for(int i = 0; i < data.length; i++) {
				CompOffApproval bean1 = new CompOffApproval();
				bean1.setHprojName(String.valueOf(data[i][0]));
				bean1.setHDate(String.valueOf(data[i][1]));
				bean1.setHsTime(String.valueOf(data[i][2]));
				bean1.setHeTime(String.valueOf(data[i][3]));
				bean1.setHmonth(String.valueOf(data[i][4]));
				bean1.setHyear(String.valueOf(data[i][5]));
				bean1.setHidMon(String.valueOf(data[i][6]));
				compList.add(bean1);
			}
			bean.setCompList(compList);
		}
	}

	/**
	 * @param bean
	 * @param status
	 */
	public void callStatus(CompOffApproval bean, String status) {
		String pathQuery = "SELECT COMPOFF_REMARK FROM HRMS_COMPOFF_PATH WHERE APPROVER_CODE=" + bean.getUserEmpId() + " AND COMPID = ?";

		String query = "  SELECT EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME, " + " TO_CHAR(COMP_APPDATE,'DD-MM-YYYY'), COMP_STATUS ,COMP_LEVEL, COMP_ID ,EMP_TOKEN FROM HRMS_COMPOFF_HDR " + "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_COMPOFF_HDR.COMP_EMPID)  " + "  LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) " + "   WHERE COMP_STATUS ='" + status + "' AND ( COMP_APPROVER = " + bean.getUserEmpId() + " OR COMP_ALT_APPROVER= " + bean.getUserEmpId() + " ) order by COMP_APPDATE desc ";

		Object[] addObj = new Object[2];
		addObj[0] = status;
		addObj[1] = bean.getUserEmpId();

		Object[][] result = getSqlModel().getSingleResult(query);
		ArrayList<Object> cmpList = new ArrayList<Object>();

		if(result != null && result.length != 0) {
			for(int i = 0; i < result.length; i++) {
				CompOffApproval bean1 = new CompOffApproval();
				bean1.setCompEmpId(String.valueOf(result[i][0]));
				bean1.setEmpName(String.valueOf(result[i][1]));
				bean1.setAppDate(String.valueOf(result[i][2]));
				bean1.setCheckStatus(String.valueOf(result[i][3]));
				if(status.equals("A"))
					bean1.setStatusNew("Approved");
				if(status.equals("R"))
					bean1.setStatusNew("Rejected");
				bean1.setLevel(String.valueOf(result[i][4]));
				bean1.setCompId(String.valueOf(result[i][5]));
				bean1.setCompEmpToken(String.valueOf(result[i][6]));

				Object[][] data = getSqlModel().getSingleResult(pathQuery, new Object[]{String.valueOf((result[i][5]))});
				if(data.length == 0 || String.valueOf(data[0][0]).equals("null")) {
					bean1.setRemark("");
				} else {
					bean1.setRemark(String.valueOf(data[0][0]));
				}
				cmpList.add(bean1);
			} // end of for loop
			bean.setNoData("false");
		} else if(result.length == 0) {
			bean.setListLength("0");
			bean.setNoData("true");
		}
		bean.setStatus(status);
		bean.setCmpList(cmpList);
	}

	/**
	 * @param bean
	 * @param empFlow
	 * @param compId
	 * @param compEmpId
	 * @return String after changing all application status.
	 */
	public String changeApplStatus(CompOffApproval bean, Object[][] empFlow, String compId, String compEmpId) {
		boolean flowResult = false;
		boolean result = false;
		String leaveBal = "";
		Object[][] to_mailIds = new Object[1][1];
		Object[][] from_mailIds = new Object[1][1];

		if(empFlow != null && empFlow.length != 0) { // next approval person is there...!
			Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2]; // status level
			updateApprover[0][1] = empFlow[0][0]; // next approval id:
			updateApprover[0][2] = empFlow[0][3];
			updateApprover[0][3] = compId; // application id
			flowResult = getSqlModel().singleExecute(getQuery(2), updateApprover);

			/*try { // when no approver is there tht time u r sending mail..!
				to_mailIds[0][0] = empFlow[0][0]; // compEmpId
				from_mailIds[0][0] = compEmpId; // bean.getEmpId();

				MailUtility mail = new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds, "CompOff", "", "P");

				mail.terminate();
			} catch(Exception e) {
				logger.error(e);
			}*/
		} else {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "A";
			statusChanged[0][1] = compId;
			result = getSqlModel().singleExecute(getQuery(3), statusChanged);
			if(result) { // after updating application status...! modifying the leaves & then sending mail
				leaveBal = setLeaveBal(bean, compId, compEmpId);
				// sending mail
				/*try {
					to_mailIds[0][0] = String.valueOf(compEmpId); // applicant
					from_mailIds[0][0] = bean.getUserEmpId(); // bean.getEmpId();

					MailUtility mail = new MailUtility();
					mail.initiate(context, session);
					mail.sendMail(to_mailIds, from_mailIds, "CompOff", "", "A");
					mail.terminate();
				} catch(Exception e) {
					logger.error(e);
				}*/
			}
		}

		if(flowResult) {
			return "Saved";
		}

		if(result) {
			if(leaveBal == "Saved") {
				return "Saved";
			} else if(leaveBal == "notSaved") {
				return "notSaved";
			} else if(leaveBal == "notComOff") {
				return "notComOff";
			}
			return "Saved";
		} else {
			return "notSaved";
		}
	}

	/**
	 * @param bean
	 * @param status
	 * @param compId
	 * @param comments
	 */
	public void forward(CompOffApproval bean, String status, String compId, String comments) {
		Object[][] addObj = new Object[1][4];
		//Object[][] to_mailIds = new Object[1][1];
		//Object[][] from_mailIds = new Object[1][1];

		addObj[0][0] = compId;
		addObj[0][1] = bean.getUserEmpId();
		addObj[0][2] = comments;
		addObj[0][3] = status;

		if(String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status);
			reject[0][1] = String.valueOf(compId);
			getSqlModel().singleExecute(getQuery(3), reject);

			try {
				// any problem regarding to query and details ask sai pavan voleti
				// this query for retrieving applicant id for compoff.
				/*String empquery = "SELECT COMP_EMPID FROM HRMS_COMPOFF_HDR WHERE COMP_ID = " + String.valueOf(compId);
				Object[][] employeeid = getSqlModel().getSingleResult(empquery);

				to_mailIds[0][0] = employeeid[0][0]; // applicant
				from_mailIds[0][0] = bean.getUserEmpId(); // approver person id
				MailUtility mail = new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds, "CompOff", "", "R");
				mail.terminate();*/
			} catch(Exception e) {
				logger.error(e);
			}// end of trycatch block
		}
		getSqlModel().singleExecute(getQuery(1), addObj);
	}

	public void setApplication(CompOffApproval bean, String cmpId) {
		try {
			bean.setCompId(cmpId);

			String sql = "  SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , 	" + " DEPT_NAME,RANK_NAME,TO_CHAR(COMP_APPDATE,'DD-MM-YYYY') ,COMP_STATUS ,COMP_EMPID   FROM HRMS_COMPOFF_HDR  " + " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_COMPOFF_HDR .COMP_EMPID ) " + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )  " + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) " + "  WHERE COMP_ID = " + cmpId;
			Object[][] result = getSqlModel().getSingleResult(sql);
			bean.setEmpToken(String.valueOf(result[0][0]));
			bean.setEName(String.valueOf(result[0][1]));
			bean.setDept(String.valueOf(result[0][2]));
			bean.setDesg(String.valueOf(result[0][3]));
			bean.setADate(String.valueOf(result[0][4]));
			bean.setStatus(String.valueOf(result[0][5]));
			bean.setEmpId(String.valueOf(result[0][6]));
		} catch(Exception e) {
			logger.error(e);
		}
	}

	public void setApprover(CompOffApproval bean) {
		String query = "  SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '), " + "  TO_CHAR(APPR_DATE,'DD-MM-YYYY'),COMPOFF_REMARK    FROM HRMS_COMPOFF_PATH  " + "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMPOFF_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) " + " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)" + "  WHERE COMPID = " + bean.getCompId() + " ORDER BY APPR_DATE";
		Object apprData[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> apprList = new ArrayList<Object>();

		try {
			if(apprData != null && apprData.length != 0) {
				for(int i = 0; i < apprData.length; i++) {
					CompOffApproval bean1 = new CompOffApproval();
					bean1.setApprName(String.valueOf(apprData[i][1]));
					bean1.setApprDate(String.valueOf(apprData[i][2]));
					if(String.valueOf(apprData[i][3]).equals("null") || String.valueOf(apprData[i][3]) == null) {
						bean1.setApprComments("");
					} else {
						bean1.setApprComments(String.valueOf(apprData[i][3]));
					}
					apprList.add(bean1);
				}
			}			
			bean.setApproveList(apprList);
		} catch(Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param bean
	 * @param compId
	 * @param compEmpId
	 * @return a string after changing the leave balance based on comp off
	 */
	public String setLeaveBal(CompOffApproval bean, String compId, String compEmpId) {
		try {
			boolean upResult = true;
			boolean insertResult = false;
			Object[][] compStatus = null;
			Object[][] leaveObj = null;
			
			String status = "";
			String compQue = "SELECT DISTINCT CONF_COMP_OFF FROM HRMS_ATTENDANCE_CONF ";
			Object[][] compObj = getSqlModel().getSingleResult(compQue);
			double Totcompoffday=0.0;
			Totcompoffday=compoffCalculation(bean, compId, compEmpId);
			

			double count = 0;
			double openCount = 0;
			double finalLeave = 0;
			double finalOpenLev = 0;

			if((compObj.length > 0) && (compObj != null) && (!String.valueOf(compObj[0][0]).equals("")) && (!String.valueOf(compObj[0][0]).equals("null"))) {

				String EmpChkSql = "SELECT EMP_ID  FROM HRMS_LEAVE_BALANCE WHERE EMP_ID =" + compEmpId + "AND LEAVE_CODE=" + compObj[0][0];
				Object[][] EmpChkData = getSqlModel().getSingleResult(EmpChkSql);
				
				if(EmpChkData.length > 0 && EmpChkData != null) {
					String leaveQue = "SELECT DISTINCT NVL(LEAVE_CLOSING_BALANCE,0), NVL(LEAVE_OPENING_BALANCE,0) FROM HRMS_LEAVE_BALANCE" + " WHERE EMP_ID = " + compEmpId + " AND LEAVE_CODE=" + compObj[0][0];
					leaveObj = getSqlModel().getSingleResult(leaveQue);

					if(leaveObj.length > 0) {
						count = Double.parseDouble(String.valueOf(leaveObj[0][0]));
						openCount = Double.parseDouble(String.valueOf(leaveObj[0][1]));
						/*
						String statQue = "SELECT COMPDTL_COMPID, COMPDTL_STARTTIME, COMPDTL_ENDTIME FROM  HRMS_COMPOFF_DTL LEFT JOIN  HRMS_COMPOFF_HDR ON (COMPDTL_COMPID = COMP_ID) " +
						"  WHERE  HRMS_COMPOFF_HDR.COMP_STATUS='A' and COMP_EMPID=" + compEmpId + " AND COMP_ID = " + compId;
						compStatus = getSqlModel().getSingleResult(statQue);
						
						System.out.println("statQue:-------->"+statQue);
						*/

						double numComOff = Totcompoffday;  // previously 0 now directly we r assigning...!
						/*if(compStatus.length > 0) {
							logger.info("noof compoff increment...!!!"+compStatus[0][0]);
							numComOff = Integer.parseInt("" + compStatus[0][0]);
						}*/
						finalLeave = count + numComOff;
						finalOpenLev = openCount + numComOff;
						
						logger.info("Final compoff increment...!!!"+numComOff);

						String upLeaveBal = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=" + finalLeave + ", LEAVE_OPENING_BALANCE = " + finalOpenLev + " WHERE EMP_ID=" + compEmpId + " and LEAVE_CODE=" + compObj[0][0];
						upResult = getSqlModel().singleExecute(upLeaveBal);
					}

					if(upResult) {
						status = "Saved";
						return status;
					} else {
						status = "notSaved";
						return status;
					}
				} else {
					 //" SELECT count(COMP_ID) " +
					/*
					String statQue =" SELECT COMPDTL_COMPID, COMPDTL_STARTTIME, COMPDTL_ENDTIME FROM  HRMS_COMPOFF_DTL LEFT JOIN  HRMS_COMPOFF_HDR ON (COMPDTL_COMPID = COMP_ID)" 
							+ "  WHERE  HRMS_COMPOFF_HDR.COMP_STATUS='A' and COMP_EMPID=" + compEmpId + " AND COMP_ID = " + compId;
					compStatus = getSqlModel().getSingleResult(statQue);
					logger.info("Query...!!"+statQue);
					logger.info("compoff increment...!!"+compStatus[0][0]);
					
					*/

					Object[][] insertObj = new Object[1][5];
					insertObj[0][0] = compEmpId;
					insertObj[0][1] = String.valueOf(compObj[0][0]);
					insertObj[0][2] = Totcompoffday; //String.valueOf(compStatus[0][0]);
					insertObj[0][3] = Totcompoffday;  // String.valueOf(compStatus[0][0]);
					insertObj[0][4] = "0";

					String insertNewLeaveSql = "INSERT INTO HRMS_LEAVE_BALANCE (EMP_ID,LEAVE_CODE,LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE,LEAVE_ONHOLD)" + " VALUES(?,?,?,?,?)";
					insertResult = getSqlModel().singleExecute(insertNewLeaveSql, insertObj);
					if(insertResult) {
						status = "Saved";
						return status;
					} else {
						status = "notSaved";
						return status;
					}
				}
			} else {
				return "notComOff";
			}
		} catch(Exception e) {
			logger.error(e);
			return "notSaved";
		}// end of try catch block
	}
	
	public double compoffCalculation(CompOffApproval bean, String compId, String compEmpId) {
		double totCompoffdays=0.0;
		try{
		String shiftQuery="select TO_CHAR(SHIFT_START_TIME,'HH24:MI:SS'),TO_CHAR(SHIFT_END_TIME,'HH24:MI:SS'),TO_CHAR(SHIFT_FIRST_HALF_DAY,'HH24:MI:SS'),"
			  +" TO_CHAR(SHIFT_SECOND_HALF_DAY,'HH24:MI:SS') from HRMS_SHIFT "
			  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID)" 
			  +" where HRMS_EMP_OFFC.EMP_ID="+String.valueOf(compEmpId);
			
			Object[][] shiftDtl = getSqlModel().getSingleResult(shiftQuery);
			
			String statQue = "SELECT COMPDTL_COMPID, TO_CHAR(To_date(COMPDTL_STARTTIME,'HH24:MI:SS'),'HH24:MI:SS'),TO_CHAR(To_date(COMPDTL_ENDTIME,'HH24:MI:SS'),'HH24:MI:SS') FROM  HRMS_COMPOFF_DTL LEFT JOIN  HRMS_COMPOFF_HDR ON (COMPDTL_COMPID = COMP_ID) " +
			"  WHERE  HRMS_COMPOFF_HDR.COMP_STATUS='A' and COMP_EMPID=" + compEmpId + " AND COMP_ID = " + compId;
			Object compdtl[][] = getSqlModel().getSingleResult(statQue);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			
			if(shiftDtl!=null &&shiftDtl.length>0)
			{
				
				Date dateFirstHalfTime = sdf.parse(String.valueOf(shiftDtl[0][2]));	//convert fist half day time in to date format
				Date dateSecondHalfTime = sdf.parse(String.valueOf(shiftDtl[0][3]));	//convert second half day time in to date format
				
				if(compdtl!=null && compdtl.length>0)
				{
					for (int i = 0; i < compdtl.length; i++) {
						
					
					Date compInTime = sdf.parse(String.valueOf(compdtl[i][1]));
					Date compEndTime = sdf.parse(String.valueOf(compdtl[i][2]));
					
					if(compInTime.equals("00:00:00") || (compInTime.after(dateFirstHalfTime)&& compEndTime.before(dateSecondHalfTime))) {
						totCompoffdays+=0.5;
						logger.info("When first half day occer......!!"+totCompoffdays+"  "+compInTime+"  "+compEndTime);
					}
					else if(compEndTime.equals("00:00:00") || (compInTime.after(dateSecondHalfTime)&& compEndTime.after(dateSecondHalfTime))) {
						totCompoffdays+=0.5;
						logger.info("When second half day occer......!!"+totCompoffdays+"  "+compInTime+"  "+compEndTime);
					}
					else if(compInTime.equals("00:00:00") || (compInTime.before(dateFirstHalfTime)&& compEndTime.before(dateFirstHalfTime))) {
						totCompoffdays+=0.5;
						logger.info("When before first half day occer......!!"+totCompoffdays+"  "+compInTime+"  "+compEndTime);
					}
					else
						totCompoffdays+=1;
					
				}
					logger.info("AFter End of Calculation....!!"+totCompoffdays);
				
			}
			}
			return totCompoffdays;
		}catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is Raise..."+e);
			return 0.0;
		}
	
		
	}
	
}