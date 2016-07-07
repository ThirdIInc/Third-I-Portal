package org.paradyne.model.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.lib.ModelBase;
import org.paradyne.bean.admin.srd.EmployeeCheckList;
import org.paradyne.bean.common.HrmHome;
import org.paradyne.bean.common.LeaveHome;

/*
 * author:pradeep Kumar Sahoo
 * Date:17.01.2008
 */

public class LeaveHomeModel extends ModelBase {

	public void empOnLeave(LeaveHome lh) {

		String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY'),"
				+ " NVL(HRMS_LEAVE_HDR.LEAVE_COMMENTS,' '),NVL(HRMS_LEAVE_HDR.LEAVE_CONTACT_DETAILS,' ') FROM HRMS_EMP_OFFC INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " INNER JOIN HRMS_LEAVE_HDR ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID)"
				+ " WHERE HRMS_LEAVE_HDR.LEAVE_STATUS='A' AND HRMS_LEAVE_DTL.LEAVE_FROM_DATE < (SELECT SYSDATE FROM DUAL)"
				+ " AND HRMS_LEAVE_DTL.LEAVE_TO_DATE > (SELECT SYSDATE FROM DUAL) ";

		Object[][] values = getSqlModel().getSingleResult(query);

		ArrayList<LeaveHome> leaveSt = new ArrayList<LeaveHome>();
		System.out.println("values.len" + values.length);
		for (int i = 0; i < values.length; i++) {

			LeaveHome bean = new LeaveHome();
			bean.setName(String.valueOf(values[i][0]));
			bean.setFromDt(String.valueOf(values[i][1]));
			bean.setToDt(String.valueOf(values[i][2]));
			bean.setReason(String.valueOf(values[i][3]));
			bean.setContactinfo(String.valueOf(values[i][4]));
			leaveSt.add(bean);
		}
		System.out.println("leaveSt.len" + leaveSt.size());
		lh.setList1(leaveSt);

	}

	public void empOnPending(LeaveHome lh) {

		String query = " SELECT DISTINCT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY'),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY  : HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY'))" +
				",DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY  : HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')) "
				+ " FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_LEAVE_HDR ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID) "
				+ " INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE= HRMS_LEAVE_HDR.LEAVE_APPL_CODE) "
				+ " WHERE HRMS_LEAVE_HDR.LEAVE_STATUS='P' AND  EMP_ID="+lh.getUserEmpId();

		Object[][] values = getSqlModel().getSingleResult(query);
		ArrayList<LeaveHome> leaveSt = new ArrayList<LeaveHome>();
		System.out.println("values.len" + values.length);
		for (int i = 0; i < values.length; i++) {

			LeaveHome bean = new LeaveHome();
			bean.setEmpName(String.valueOf(values[i][0]));
			System.out.println("----------------emp name---------------"
					+ bean.getEmpName());
			bean.setAppDate(String.valueOf(values[i][1]));
			bean.setFrmDate(String.valueOf(values[i][2]));
			bean.setTdate(String.valueOf(values[i][3]));
			leaveSt.add(bean);
		}
		System.out.println("leaveSt.len" + leaveSt.size());
		lh.setList(leaveSt);

	}

	public Object[][] overAll(LeaveHome lh) {
		String yr = " select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value = getSqlModel().getSingleResult(yr);

		String query = "  SELECT DISTINCT COUNT(*) FROM HRMS_DAILY_ATTENDANCE_"
				+ String.valueOf(value[0][0]) + " where att_status='AA'";
		Object[][] absData = getSqlModel().getSingleResult(query);

		String sql = "   SELECT DISTINCT COUNT(*) FROM HRMS_DAILY_ATTENDANCE_"
				+ String.valueOf(value[0][0]) + " where att_status='XX'";
		Object[][] prsntData = getSqlModel().getSingleResult(sql);

		String query1 = "   select count(*) from hrms_daily_attendance_"
				+ String.valueOf(value[0][0])
				+ "  where ATT_LEAVE_STATUS='LL' ";

		Object[][] leaveData = getSqlModel().getSingleResult(query1);

		String query2 = " select count(*),'Late' from hrms_daily_attendance_"
				+ String.valueOf(value[0][0]) + " where ATT_LATE_HRS > 0";

		Object[][] lateData = getSqlModel().getSingleResult(query2);

		Object[][] finalData = null;
		int count = 0;
		int l1 = 0, l2 = 0, l3 = 0, l4 = 0;

		try {
			if (absData != null) {
				count += absData.length;
			}
			if (prsntData != null) {
				count += prsntData.length;
			}
			if (leaveData != null) {
				count += leaveData.length;
			}
			if (lateData != null) {
				count += lateData.length;
			}

		} catch (Exception e) {

		}

		finalData = new Object[4][1];
		finalData[0][0] = "" + prsntData != null ? prsntData[0][0] : "";// Present
		finalData[1][0] = "" + absData != null ? absData[0][0] : "";// Abs

		finalData[2][0] = "" + leaveData != null ? leaveData[0][0] : "";// Leave
		finalData[3][0] = "" + lateData != null ? lateData[0][0] : "";// Late

		return finalData;

	}

	public Object[][] getBranch(LeaveHome hm) {
		String yr = " select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value = getSqlModel().getSingleResult(yr);
		String qualQuery = " select count(*),center_name from HRMS_CENTER inner join hrms_emp_offc on(hrms_emp_offc.emp_center=hrms_center.center_id) "
				+ " inner join hrms_daily_attendance_"
				+ String.valueOf(value[0][0])
				+ " on(hrms_daily_attendance_"
				+ String.valueOf(value[0][0])
				+ ".att_emp_id=hrms_emp_offc.emp_id)"
				+ " where att_status in ('XX','AA') "
				+ " group by center_name ";
		Object[][] values = getSqlModel().getSingleResult(qualQuery);

		// int sum=0;
		// for(int i=0;i<values.length;i++) {
		// sum=sum+Integer.parseInt(String.valueOf(values[i][0]));

		// }
		// hm.setTot(String.valueOf(sum));
		return values;

	}

	public Object[][] getDepartment(LeaveHome lh) {
		String yr = " select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value = getSqlModel().getSingleResult(yr);
		String deptQuery = " select count(*),dept_name from  HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_CENTER.CENTER_DEPT_ID)"
				+ " inner join hrms_daily_attendance_"
				+ String.valueOf(value[0][0]) + " on(hrms_daily_attendance_"
				+ String.valueOf(value[0][0])
				+ ".att_emp_id=hrms_emp_offc.emp_id)"
				+ " where att_status in ('XX','AA') " + " group by dept_name ";
		Object[][] values = getSqlModel().getSingleResult(deptQuery);

		// int sum=0;
		// for(int i=0;i<values.length;i++) {
		// sum=sum+Integer.parseInt(String.valueOf(values[i][0]));

		// }
		// lh.setTot1(String.valueOf(sum));
		return values;

	}

	public void getLeaveDetails(LeaveHome lhm) {
		// TODO Auto-generated method stub
		String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),"
				+ "  TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
				+ " FROM HRMS_LEAVE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID)"
				+ " WHERE  LEAVE_STATUS='P' AND (APPROVED_BY="+lhm.getUserEmpId()+" OR ALTER_APPROVER="+lhm.getUserEmpId()+") ";

		Object[][] value = getSqlModel().getSingleResult(query);
		ArrayList list = new ArrayList();

		if (value != null && value.length > 0) {
			for (int i = 0; i < value.length; i++) {
				LeaveHome bean = new LeaveHome();
				bean.setEmployeeName(checkNull(String.valueOf(value[i][0])));
				bean.setApplDate(checkNull(String.valueOf(value[i][1])));
				bean.setLeaveFrmDate(checkNull(String.valueOf(value[i][2])));
				bean.setLeaveToDate(checkNull(String.valueOf(value[i][3])));
				list.add(bean);
			}
			lhm.setLeaveList(list);
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void getLeaveinfoDetails(LeaveHome lh) {
		
		// TODO Auto-generated method stub
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') "
		 +"  ,HRMS_LEAVE_HDR.LEAVE_MEDICAL_CERT "
		+"   FROM HRMS_LEAVE_HDR "
		+"   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID)"
		 
		+"   WHERE  LEAVE_STATUS='A' "
		+"   AND   TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY') <= TO_CHAR(SYSDATE+15,'DD-MM-YYYY')"
		+"   AND HRMS_EMP_OFFC.EMP_DIV=(Select EMP_DIV from HRMS_EMP_OFFC where EMP_ID="+lh.getUserEmpId()+") AND HRMS_EMP_OFFC.EMP_DEPT=(Select EMP_DEPT from HRMS_EMP_OFFC WHERE EMP_ID="+lh.getUserEmpId()+")";
 

		Object[][] value = getSqlModel().getSingleResult(query);
		ArrayList emplist = new ArrayList();

		if (value != null && value.length > 0) {
			for (int i = 0; i < value.length; i++) {
				LeaveHome bean = new LeaveHome();
				bean.setEName(checkNull(String.valueOf(value[i][0])));
				bean.setLeaveFromDate(checkNull(String.valueOf(value[i][1])));
				bean.setLevToDate(checkNull(String.valueOf(value[i][2])));
				bean.setReasoninfo(checkNull(String.valueOf(value[i][3])));
				emplist.add(bean);
			}
			lh.setEmpLeaveList(emplist);
		}

	}
	
	
//Added by priyanka
	
	public void getLeaveAttendanceMenuList(HttpServletRequest request,
			LeaveHome bean, String menuType) {
		try {

			if (menuType == null) {
				menuType = "MN";
			}
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String groupQuery = " select DISTINCT MENU_GROUP,MENU_GROUP_ORDER, MENU_IMAGE from HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and HRMS_PROFILE_DTL.PROFILE_CODE IN("
					+ bean.getMultipleProfileCode()
					+ "))"
					+ " WHERE MENU_TYPE='"
					+ menuType
					+ "' AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER ";
			Object groupData[][] = getSqlModel().getSingleResult(groupQuery);

			String query = " SELECT DISTINCT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP,MENU_GROUP_ORDER,MENU_PLACEINMENU FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ "	OR PROFILE_GENERAL_FLAG ='Y'))"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN("+bean.getMultipleProfileCode()+") and MENU_TYPE='" + menuType + "' "
					+ " AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER,MENU_PLACEINMENU";
			Object data[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("serviceMenulst", data);
			request.setAttribute("groupData", groupData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Ended by priyanka
	
		
	}


