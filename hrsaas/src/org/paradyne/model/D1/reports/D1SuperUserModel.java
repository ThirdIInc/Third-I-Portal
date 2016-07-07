package org.paradyne.model.D1.reports;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.reports.D1SuperUser;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class D1SuperUserModel extends ModelBase {

	public void view(D1SuperUser bean, HttpServletRequest request) {
		try {
		bean.setList(null);
		ArrayList list = new ArrayList();
		String query = getQuery(bean.getApplicationType(), bean.getApplicantCode(), bean.getApplicationStatus());
		Object[][] data = getSqlModel().getSingleResult(query);
		
		String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), data.length, 20);
		if(pageIndexDrafted == null) {
			pageIndexDrafted[0] = "0";
			pageIndexDrafted[1] = "20";
			pageIndexDrafted[2] = "1";
			pageIndexDrafted[3] = "1";
			pageIndexDrafted[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
		if(pageIndexDrafted[4].equals("1")) bean.setMyPage("1");
		bean.setList(null);
		
		if (data != null && data.length > 0) {
			bean.setSuperUserListLength(true);
			for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
				.parseInt(pageIndexDrafted[1]); i++) {
				
				D1SuperUser listBean = new D1SuperUser();
				listBean.setIttTrackingNo(String.valueOf(data[i][0]));
				listBean.setIttEmpToken(String.valueOf(data[i][1]));
				listBean.setIttEmpame(String.valueOf(data[i][2]));
				listBean.setIttApplicationType(String.valueOf(data[i][3]));
				listBean.setIttApplicationDate(String.valueOf(data[i][4]));
				listBean.setIttStatus(String.valueOf(data[i][5]));
				listBean.setIttApplicationCode(String.valueOf(data[i][6]));
				listBean.setIttPendingWith(String.valueOf(data[i][7]));
				
				if(bean.getApplicationType().equals("D1-ISCR")){
					System.out.println("Information System Change Request");
					bean.setScheduleDateFlag("true");
				listBean.setIttScheduleDate(String.valueOf(data[i][8]));
				System.err.println("data[i][8]=============="+ data[i][8]);
				} else {
					
				}
				
				if(bean.getApplicationStatus().equals("S")){
					listBean.setIttPendingWith("Pending with Group");
				}
				if(bean.getApplicationStatus().equals("A")){
					listBean.setIttPendingWith("");
				}
				if(bean.getApplicationStatus().equals("R")){
					listBean.setIttPendingWith("");
				}
				if(bean.getApplicationStatus().equals("B")){
					listBean.setIttPendingWith("");
				}if(bean.getApplicationStatus().equals("D")){
					listBean.setIttPendingWith("");
				}
				
				list.add(listBean);
			}
			bean.setList(list);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public String getQueryBRD(String applType, String applicant, String applStatus) {
		String query = "";
		String order="5";
//For Brd  Application
		
		if (applType.equals("D1-BRD")) {
			order="PROJ_COMPLETED_DATE";
			query += " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'), STAGE_NAME, HRMS_D1_ROLE.ROLE_NAME,DECODE(PROJ_STATUS,'D','Draft','F','Forwarded','C','Closed'),(hrms_emp_offc.EMP_TOKEN||''||'-'||''||hrms_emp_offc.EMP_FNAME||' '||hrms_emp_offc.EMP_MNAME||'  '||hrms_emp_offc.EMP_LNAME),BUSINESS_CODE "
				+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
				+ " left join hrms_emp_offc on(hrms_emp_offc.emp_id = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID) "
				+ " left join HRMS_D1_STAGE on(HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE) "
				+ " left join HRMS_D1_ROLE on(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO)  WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND PROJ_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND PROJ_INITIATOR ='"+applicant+"'";
			}
		}
		
		
		query += " ORDER BY "+order+" DESC ";
		return query;
		
	}
	/**
	 * GET QUERY
	 */
	public String getQuery(String applType, String applicant, String applStatus) {
		String query = "";
		String order="5";
		if (applType.equals("D1-ENROLL")) {
			order="APPLICAION_DATE";
			query += "SELECT HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,    "
					+ "	OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME ,'Class Enrollment',TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), DECODE(STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'D', 'Draft') AS STATUS "
					+ "	,ENROLL_ID,'Pending With Group'	FROM HRMS_D1_CLASS_ENROLL	"
					+ "		 INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID) WHERE 1=1 ";
			
			if(!applStatus.equals("")){
				query += " AND STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-PERSONAL")) {
			order="APPLICATION_DATE";
			query += "SELECT HRMS_D1_PERSONAL_REQUISITION.TRACKING_NUMBER,  "
					+ "OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME ,'Personal Requisition',TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), DECODE(PERS_REQ_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
					+ "	,PERSONAL_REQ_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME	FROM HRMS_D1_PERSONAL_REQUISITION	"
					+ "		 INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.HIRING_MGNR_ID)" +
							" LEFT JOIN HRMS_EMP_OFFC MNG on(MNG.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.NEXT_APPR_CODE)    WHERE 1=1 ";
			if(!applStatus.equals("")){
				query += " AND PERS_REQ_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-DATACHG")) {
			order="APPLICATION_DATE";
			query += "SELECT HRMS_D1_PERSONAL_DATA_CHANGE.TRACKING_NUMBER,  "
					+ "OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME ,'Personal Data Change',TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), DECODE(PERS_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
					+ "	,PERS_CHANGE_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME	FROM HRMS_D1_PERSONAL_DATA_CHANGE	"
					+ "		 INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)" +
							" LEFT join HRMS_EMP_OFFC MNG on(MNG.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_APPROVER)    WHERE 1=1 ";

			if(!applStatus.equals("")){
				query += " AND PERS_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-HIRE")) {
			order="APPLICATION_DATE";
			query += "SELECT HRMS_D1_NEW_HIRE_REHIRE.TRACKING_NUMBER,  "
					+ "OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME ,'Hire/Rehire',TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), DECODE(HIRE_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
					+ "	,HIRE_REHIRE_ID , 'Pending with Group'	FROM HRMS_D1_NEW_HIRE_REHIRE	"
					+ "		 INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.HIRE_INITIATOR) WHERE 1=1 ";

			if(!applStatus.equals("")){
				query += " AND HIRE_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}

		if (applType.equals("D1-CASHADV")) {
			order="APPLICAION_DATE";
			query += "SELECT HRMS_D1_CASH_ADVANCE.TRACKING_NUMBER,  "
					+ "OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME ,'Cash Advance Request',TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), DECODE(STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
					+ "	,CASH_ADV_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME	FROM HRMS_D1_CASH_ADVANCE	"
					+ "		 INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID) " +
							"  LEFT join HRMS_EMP_OFFC MNG on(MNG.EMP_ID = HRMS_D1_CASH_ADVANCE.APPROVER_CODE)   WHERE 1=1 ";
			if(!applStatus.equals("")){
				query += " AND STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		
		}

		if (applType.equals("D1-ISCR")) {
			order="CREATED_ON";
			query += "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER,  "
					+ "OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME ,'Information System Change Request',TO_CHAR(CREATED_ON,'DD-MM-YYYY'), DECODE(APPL_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
					+ "	,INF_CNG_CODE,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME , TO_CHAR(CNG_DATE,'DD-MM-YYYY')	FROM HRMS_D1_INF_CNG_REQ	"
					+ "		 INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) " +
							" LEFT join HRMS_EMP_OFFC MNG on(MNG.EMP_ID = HRMS_D1_INF_CNG_REQ.APPROVER_CODE)   WHERE 1=1 ";

			if(!applStatus.equals("")){
				query += " AND APPL_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-EXP")) {
			order="APPLICATION_DATE";
			query += "SELECT NVL(FILE_HEADER_NAME,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Expense Request',TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), " 
				+" DECODE(STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS, "
				+" EXPENSE_CODE, "
				+" CASE WHEN STATUS IN ('Z','S') THEN 'Pending With Group' ELSE "  
				+" TO_CHAR(MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME) END "
				+" FROM HRMS_D1_EXPENSE_APPR_REQ "
				+" INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID) " 
				+" LEFT JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=DECODE(HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_APPR_CODE,NULL,HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CHANGE_MNG,HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_APPR_CODE))  WHERE 1=1";
			
			if(!applStatus.equals("")){
				if(applStatus.equals("S")){
					query += " AND STATUS IN('S','Z')";
				}
				else{
					query += " AND STATUS ='"+applStatus+"'";
				}
				
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-NONINV")) {
			order="NON_INVNTY_APPL_DATE";
			query += "SELECT TRACKING_NO,OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Non Inventory Purchase',TO_CHAR(NON_INVNTY_APPL_DATE,'DD-MM-YYYY'), DECODE(NON_INVNTY_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft', 'L', 'Pending with Group') AS STATUS "
				+ "	,NON_PURCHASE_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME FROM HRMS_D1_NON_INVENTORY_PURCHASE "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID)" +
						" LEFT JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=DECODE(HRMS_D1_NON_INVENTORY_PURCHASE.APPROVER_CODE,NULL,HRMS_D1_NON_INVENTORY_PURCHASE.CHANGE_MANG_CODE,HRMS_D1_NON_INVENTORY_PURCHASE.APPROVER_CODE)) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND NON_INVNTY_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-INJURY")) {
			order="APPLICATION_DATE";
			query+="SELECT NVL(TRACKING_NO,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Workers Comp Injury/Illness',TO_CHAR(WORKERS_APPLICATION_DATE,'DD-MM-YYYY') , DECODE(WORKERS_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+"	,WORKERS_ID,DECODE(WORKERS_STATUS,'D',' ','Pending with Group') FROM HRMS_D1_WRKRS_COMP_INJURY "
				+"	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID) WHERE 1=1";
			
			if(!applStatus.equals("")){
				if(applStatus.equals("S")){
					query += " AND WORKERS_STATUS IN('P')";
				}
				else if(applStatus.equals("A")){
					query += " AND WORKERS_STATUS IN('Z','A')";
				}
				else
				query += " AND WORKERS_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-SHORTDIS")) {
			order="APPLICATIONB_DATE";
			query+="SELECT NVL(TRACKING_NO,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Short Term Disability',TO_CHAR(APPLICATIONB_DATE,'DD-MM-YYYY')  , DECODE(STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+"	,SHORT_TERM_CODE,DECODE(STATUS,'D',' ','Pending with Group') FROM HRMS_D1_SHORT_TERM_DISB "
				+"	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_EMP_ID) WHERE 1=1";
			
			if(!applStatus.equals("")){
				if(applStatus.equals("S")){
					query += " AND STATUS IN('P')";
				}
				else
				query += " AND STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-QUICK PROJECT")) {
			order="COMPLETED_ON";
			query += "SELECT NVL(TRACKING_NO,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Quick Project',TO_CHAR(COMPLETED_ON,'DD-MM-YYYY') , DECODE(STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	,PROJECT_CODE,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME FROM HRMS_D1_QUICK_PROJECT "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_QUICK_PROJECT.MANAGER_CODE) " +
						"LEFT JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_QUICK_PROJECT.NEXT_APPR_CODE) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-AUTHTRVL")) {
			order="TRAVEL_APPLICATION_DATE";
			query += "SELECT NVL(TRAVEL_FILE_HEADER_NAME,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Authorized Travel Request',TO_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY') , DECODE(TRAVEL_APPROVER_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,TRAVEL_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME FROM HRMS_D1_TRAVEL_REQUEST "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) " +
						"LEFT JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPROVER_CODE) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND TRAVEL_APPROVER_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-REPCHG")) {
			order="REQ_DATE";
			query += "SELECT NVL(REQ_TRACKING_NO,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Report Request Change',TO_CHAR(REQ_DATE,'DD-MM-YYYY') , DECODE(REQ_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,REQ_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME FROM HRMS_D1_REPORT_REQ_CHANGE "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_REPORT_REQ_CHANGE.REQ_EMP_ID) " +
						" LEFt JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_REPORT_REQ_CHANGE.REQ_APPROVER) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND REQ_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-REQBACKOUT")) {
			order="VOUCHER_REQ_TO_DATE";
			query += "SELECT NVL(VOUCHER_TRACKING_NUMBER,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Request to Back Out Voucher ',TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY') , DECODE(VOUCHER_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,VOUCHER_REQUEST_ID,'Pending with Group' FROM HRMS_D1_REQ_BACK_OUT "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_REQ_BACK_OUT.VOUCHER_FROM_EMP) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND VOUCHER_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-CLASS")) {
			order="PERS_INITIATOR_DATE";
			query += "SELECT NVL(TRACKING_NUMBER,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Class Request ',TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY') , DECODE(STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,CLASS_REQUEST_ID,'Pending with Group' FROM HRMS_D1_CLASS_REQUEST "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_CLASS_REQUEST.CLASS_INITIATOR) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-DEPT")) {
			order="DEPT_APPLICATION_DATE";
			query += "SELECT NVL(DEPT_FILE_HEADER_NAME,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Department/Location Change ',TO_CHAR(DEPT_APPLICATION_DATE,'DD-MM-YYYY') , DECODE(DEPT_APPROVER_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,DEPT_LOC_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME FROM HRMS_D1_DEPT_LOC_CHANGE "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_EMP_ID) " +
						" LEFt JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND DEPT_APPROVER_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-TERMNT")) {
			order="TER_TERMINATION_DATE";
			query += "SELECT NVL(TER_TRACKING_NUMBER,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Termination Form',TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY') , DECODE(TER_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,TER_ID,'Pending with Group' FROM HRMS_D1_TERMINATION "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_TERMINATION.TER_EMP_ID) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND TER_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-ASIPO")) {
			order="ASIPO_COMPLETED_ON";
			query += "SELECT NVL(ASIPO_TRACKING_NUMBER,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'ASIPO Reconciliation Form',TO_CHAR(ASIPO_COMPLETED_ON,'DD-MM-YYYY') , DECODE(ASIPO_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,ASIPO_APPLICATION_ID,'Pending with Group' FROM HRMS_D1_ASIPO "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_ASIPO.ASIPO_COMPLETED_BY) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND ASIPO_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-HWSW")) {
			order="HWSW_COMPLETED_ON";
			query += "SELECT NVL(HWSW_TRACKING_NUMBER,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Hardware Software Request',TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY') , DECODE(HWSW_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft','H','Forwarded by group') AS STATUS "
				+ "	 ,HWSW_REQ_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_COMPLETED_BY) " +
						" LEFt JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_APPOVER_ID) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND HWSW_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-CCR")) {
			order="CREDIT_INITIATOR_DATE";
			query += "SELECT NVL(CREDIT_TRACKING_NUMBER,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Credit Check Request',TO_CHAR(CREDIT_INITIATOR_DATE,'DD-MM-YYYY') , DECODE(CREDIT_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,CREDIT_ID,'Pending with Group'  FROM HRMS_D1_CREDIT_CHECK_REQ "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_CREDIT_CHECK_REQ.CREDIT_INITIATOR) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND CREDIT_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-CDROM")) {
			order="CDROM_APP_DATE";
			query += "SELECT NVL(CDROM_FILE_HEADER_NAME,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'CD ROM Request',TO_CHAR(CDROM_APP_DATE,'DD-MM-YYYY') , DECODE(CDROM_APPROVER_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,CDROM_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME FROM HRMS_D1_CDROM_REQUEST "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" +
						"LEFT JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_CDROM_REQUEST.CDROM_APPROVER_CODE) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND CDROM_APPROVER_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-CBT")) {
			order="CBT_COMPLETED_ON";
			query += "SELECT NVL(CBT_TRACKING_NUM,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'CBT Self Study Enrollment',TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY') , DECODE(CBT_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,CBT_ID,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME  FROM HRMS_D1_CBT_REQUEST "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_CBT_REQUEST.CBT_EMP_ID)" +
						"LEFT JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_CBT_REQUEST.CBT_APPROVER_ID) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND CBT_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		if (applType.equals("D1-APPLN_SEC_REQ")) {
			order="APPLN_SEC_REQ_DATE";
			query += "SELECT NVL(APPLN_SEC_TRACKING_NO,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME||' - '||NVL(APPLN_SEC_EMP_TOKEN,' ')||' '||NVL(APPLN_SEC_EMP_NAME,' '),'Application Security Request',TO_CHAR(APPLN_SEC_REQ_DATE,'DD-MM-YYYY') , DECODE(APPLN_SEC_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,APPLN_SEC_ID,'Pending with Group'  FROM HRMS_D1_APPLN_SECURITY "
				+ "	  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_APPLN_SECURITY.APPLN_SEC_MGR_ID) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND APPLN_SEC_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		
		if (applType.equals("D1-CEAR")) {
			order="EXP_APPLICATION_DATE";
			query += "SELECT NVL(EXP_TRACKING_NUMBER,' '),OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME,'Capital Expenditure',TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY') , DECODE(EXP_APPLICATION_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Auhtorized SignOff', 'Z', 'Auhtorized SignOff', 'D', 'Draft') AS STATUS "
				+ "	 ,EXP_CODE,MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME  FROM HRMS_D1_CAPITALEXP "
				+ "	 INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_CAPITALEXP.EXP_EMP_ID) " +
						"LEFT JOIN HRMS_EMP_OFFC MNG ON(MNG.EMP_ID=HRMS_D1_CAPITALEXP.EXP_APPROVER_CODE) WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND EXP_APPLICATION_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND OFFC.EMP_ID  ='"+applicant+"'";
			}
		}
		//For Brd  Application
		
		if (applType.equals("D1-BRD")) {
			order="PROJ_COMPLETED_DATE";
			query += " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'), STAGE_NAME, HRMS_D1_ROLE.ROLE_NAME,(hrms_emp_offc.EMP_TOKEN||''||'-'||''||hrms_emp_offc.EMP_FNAME||' '||hrms_emp_offc.EMP_MNAME||'  '||hrms_emp_offc.EMP_LNAME),BUSINESS_CODE "
				+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
				+ " left join hrms_emp_offc on(hrms_emp_offc.emp_id = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID) "
				+ " left join HRMS_D1_STAGE on(HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE) "
				+ " left join HRMS_D1_ROLE on(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO)  WHERE 1=1";
			
			if(!applStatus.equals("")){
				query += " AND PROJ_STATUS ='"+applStatus+"'";
			}
			if(!applicant.equals("")){
				query += " AND PROJ_INITIATOR ='"+applicant+"'";
			}
		}
		
		
		query += " ORDER BY "+order+" DESC ";
		return query;
	}

	public void viewApplication(D1SuperUser bean, HttpServletRequest request) {
		try {
			bean.setBrdList(null);
			ArrayList list = new ArrayList();
			String query = getQueryBRD(bean.getApplicationType(), bean.getApplicantCode(), bean.getApplicationStatus());
			Object[][] data = getSqlModel().getSingleResult(query);
			
			String[] pageIndexBRD = Utility.doPaging(bean.getMyPageOngoing(), data.length, 20);
			if(pageIndexBRD == null) {
				pageIndexBRD[0] = "0";
				pageIndexBRD[1] = "20";
				pageIndexBRD[2] = "1";
				pageIndexBRD[3] = "1";
				pageIndexBRD[4] = "";
			}

			request.setAttribute("totalPageBRD", Integer.parseInt(String.valueOf(pageIndexBRD[2])));
			request.setAttribute("pageNoBRD", Integer.parseInt(String.valueOf(pageIndexBRD[3])));
			if(pageIndexBRD[4].equals("1")) bean.setMyPageOngoing("1");
			bean.setBrdList(null);
			
			if (data != null && data.length > 0) {
				bean.setSuperUserBRDListLength(true);
				for (int i = Integer.parseInt(pageIndexBRD[0]); i < Integer.parseInt(pageIndexBRD[1]); i++) {
					
					D1SuperUser listBrdBean = new D1SuperUser();
					listBrdBean.setIttBrdNo(checkNull(String.valueOf(data[i][0])));
					listBrdBean.setIttProjName(checkNull(String.valueOf(data[i][1])));
					listBrdBean.setIttExpDate(checkNull(String.valueOf(data[i][2])));
					listBrdBean.setIttCurrentStage(checkNull(String.valueOf(data[i][3])));
					listBrdBean.setIttPendingWithRole(checkNull(String.valueOf(data[i][4])));
					listBrdBean.setIttProjStatus(checkNull(String.valueOf(data[i][5])));
					listBrdBean.setIttPendingWithName(checkNull(String.valueOf(data[i][6])));
					listBrdBean.setIttHiddenCode(checkNull(String.valueOf(data[i][7])));
					
					list.add(listBrdBean);
				}
				bean.setBrdList(list);
			}
			} catch(Exception e) {
				e.printStackTrace();
			}

		
	}
	
	/**
	 * Method to check string is null or not.
	 * 
	 * @param result -
	 *            Check null or not
	 * @return - String after checking null
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}
	
	
}
