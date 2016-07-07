package org.paradyne.model.D1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.DepartmentandLocationChangeBean;
import org.paradyne.bean.D1.PersonalDataChange;
import org.paradyne.bean.LMS.AccommodationMasterBean;
import org.paradyne.bean.admin.srd.AddressDetails;
import org.paradyne.bean.leave.LeaveApplication;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;

public class DepartmentandLocationChangeModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DepartmentandLocationChangeModel.class);
	/**
	 * For inserting record into DB
	 * 
	 * @param checkStatus
	 * @param request
	 */
	public boolean addDeptData(DepartmentandLocationChangeBean bean,
			HttpServletRequest request, String checkStatus) {
		{
			boolean result=false;
		
try{
			Object addObj[][] = new Object[1][20];
			addObj[0][0] = bean.getEmpId();// Number
			addObj[0][1] = bean.getEffectivedateofChange().trim();// Date
			if (bean.getTo_departmentID() != null
					&& !bean.getTo_departmentID().equals("")) {
				addObj[0][2] = bean.getTo_departmentID().trim();// varchar
			} else {
				addObj[0][2] = 0;
			}
			addObj[0][3] = bean.getTo_workPhone().trim();// number
			addObj[0][4] = bean.getTo_managerID();// number
			//addObj[0][5] = bean.getRadioValue().trim();
			addObj[0][5] = "";
			addObj[0][6] = bean.getRadioYNValue();
			if(bean.getApproverCode()!=null && !bean.getApproverCode().equals("")){
				addObj[0][7] = bean.getApproverCode().trim();
				}else{
					addObj[0][7] = bean.getFirstApproverCode().trim();
				}
			addObj[0][8] = bean.getAddress1().trim();
			addObj[0][9] = bean.getAddress2().trim();
			addObj[0][10] = bean.getAddress3().trim();
			//addObj[0][11] = bean.getTo_workPhone().trim();
			addObj[0][11] = bean.getPhone1().trim();
			//addObj[0][12] = bean.getCityId().trim();
			addObj[0][12] = bean.getCity().trim();
			addObj[0][13] = bean.getState().trim();
			addObj[0][14] = bean.getCountry().trim();
			addObj[0][15] = checkStatus.trim();
			addObj[0][16]= bean.getCompletedByCode();
			
			//Tracking Number
			String qq="SELECT NVL(MAX(DEPT_LOC_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(DEPT_LOC_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_DEPT_LOC_CHANGE	";
			Object[][]obj=getSqlModel().getSingleResult(qq);
			if(obj !=null && obj.length>0){
				//bean.setAuthorizedToken(checkNull(String.valueOf(obj[0][0])));
			}
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			String token ="";
			token = tempModel.generateApplicationCode(String.valueOf(obj[0][1]), "D1-DEPT", bean.getEmpId(), String.valueOf(obj[0][2]));
			System.out.println("token---"+token);
			bean.setAuthorizedToken(token);
			//End Tracking Number
			
			addObj[0][17]= bean.getAuthorizedToken().trim();
			addObj[0][18]= bean.getExecutive().trim();
			addObj[0][19]= bean.getDeptCodeSelect().trim();
			getSqlModel().singleExecute(getQuery(1), addObj);
			
			//Here id set
			String maxId="SELECT MAX(DEPT_LOC_ID) FROM HRMS_D1_DEPT_LOC_CHANGE	";
			Object[][] idobj=getSqlModel().getSingleResult(maxId);
			if(idobj !=null && idobj.length>0){
				bean.setDeptCode(String.valueOf(idobj[0][0]));
			}
			//End id
			result=true;
			
}catch(Exception e){
	e.printStackTrace();
}
	return result;
		}
	}

	/* for checking duplicate entry of record during insertion */

	public boolean checkDuplicateAdd(DepartmentandLocationChangeBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_DEPT_LOC_CHANGE WHERE UPPER(DEPT_LOC_EMP_ID) LIKE '"
				+ bean.getEmpNum().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/** Modifing the record */

	public boolean update(DepartmentandLocationChangeBean bean,
			HttpServletRequest request, String status) {
		System.out.println("inside update method**********");

		Object addObj[][] = new Object[1][21];

		addObj[0][0] = bean.getEmpId();// Number
		addObj[0][1] = bean.getEffectivedateofChange().trim();// Date
		addObj[0][2] = bean.getTo_departmentID().trim();
		addObj[0][3] = bean.getTo_workPhone().trim();// number
		addObj[0][4] = bean.getTo_managerID();// number
		//addObj[0][5] = bean.getRadioValue().trim();
		addObj[0][5] = "";
		addObj[0][6] = bean.getRadioYNValue();
		if(bean.getApproverCode()!=null && !bean.getApproverCode().equals("")){
			addObj[0][7] = bean.getApproverCode().trim();
		}else{
			addObj[0][7] = bean.getFirstApproverCode().trim();
		}
		addObj[0][8] = bean.getAddress1().trim();
		addObj[0][9] = bean.getAddress2().trim();
		addObj[0][10] = bean.getAddress3().trim();
		addObj[0][11] = bean.getPhone1().trim();
		//addObj[0][12] = bean.getCityId().trim();
		addObj[0][12] = bean.getCity().trim();
		addObj[0][13] = bean.getState().trim();
		addObj[0][14] = bean.getCountry().trim();
		addObj[0][15] = status;
		addObj[0][16]= bean.getCompletedByCode().trim();
		
		addObj[0][17]= bean.getAuthorizedToken().trim();
		addObj[0][18]= bean.getExecutive().trim();
		addObj[0][19]= bean.getDeptCodeSelect().trim();
		addObj[0][20] = bean.getDeptCode().trim();

		
		for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[i].length; j++) {
				logger
						.info("insertObj[" + i + "][" + j + "]  "
								+ addObj[i][j]);
			}
		}
		
		return getSqlModel().singleExecute(getQuery(2), addObj);
	}

	/* Draft Data */
	public void initialData(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		Object[][] regData =null;
		
		
		String draftQuery=" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+ " WHERE DPT.DEPT_APPROVER_STATUS='D' and DEPT_INITIATOR="+ userId+"  ORDER BY DPT.DEPT_LOC_ID DESC";

		//+ " WHERE DPT.DEPT_APPROVER_STATUS='D' ORDER BY DPT.DEPT_LOC_DATE ";
		regData = getSqlModel().getSingleResult(draftQuery);
		
		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPage(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			deptlocChangeBean.setDraftVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));

				List.add(bean);
			}// end of loop

			deptlocChangeBean.setDraftList(List);
		}

		/*else {

			deptlocChangeBean.setDraftList(null);

		}*/
	}

	/* Pending Data */
	public void pendingData(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		//Object[][] regData = getSqlModel().getSingleResult(getQuery(6));
		Object[][] regData = null;
		
		String pendingList=" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+ " WHERE DPT.DEPT_APPROVER_STATUS IN('P','F') and DEPT_INITIATOR="+ userId+"   ORDER BY DPT.DEPT_LOC_ID  DESC";
		//+ " WHERE DPT.DEPT_APPROVER_STATUS='P' ORDER BY DPT.DEPT_LOC_DATE ";
		
		regData = getSqlModel().getSingleResult(pendingList);
		
		
		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPageApproved(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPageApproved("1");
			ArrayList<Object> List = new ArrayList<Object>();
			deptlocChangeBean.setInProcessVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));
				List.add(bean);
			}// end of loop

			deptlocChangeBean.setPedingList(List);
		}

		/*else {

			deptlocChangeBean.setPedingList(null);

		}*/
	}
	
	

	/* Pending Data */
	public void sendBack(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		//Object[][] regData = getSqlModel().getSingleResult(getQuery(9));
		Object[][] regData = null;
		
		String sendBackQuery= " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+ " WHERE DPT.DEPT_APPROVER_STATUS='B' and DEPT_INITIATOR="+ userId+"  ORDER BY DPT.DEPT_LOC_ID DESC ";
		//+ " WHERE DPT.DEPT_APPROVER_STATUS='B' ORDER BY DPT.DEPT_LOC_DATE ";
		regData = getSqlModel().getSingleResult(sendBackQuery);
		
		
		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPageReturn(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalSendBackPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageSendBackNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPageReturn("1");
			ArrayList<Object> List = new ArrayList<Object>();
			deptlocChangeBean.setSentBackVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));
				List.add(bean);
			}// end of loop

			deptlocChangeBean.setReturnList(List);
		}

		/*else {

			deptlocChangeBean.setReturnList(null);

		}*/
	}

	/* Approved Data */
	public void approvedData(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		//Object[][] regData = getSqlModel().getSingleResult(getQuery(7));
		Object[][] regData = null;
		
		 String approveQuery =" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+ " WHERE DPT.DEPT_APPROVER_STATUS='A' and DEPT_INITIATOR="+ userId+"  ORDER BY DPT.DEPT_LOC_ID DESC ";
		//+ " WHERE DPT.DEPT_APPROVER_STATUS='A' ORDER BY DPT.DEPT_LOC_DATE ";
		 
		 regData = getSqlModel().getSingleResult(approveQuery);

		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPageApprovedList(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageApprovedNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPageApprovedList("1");
			ArrayList<Object> List = new ArrayList<Object>();
			deptlocChangeBean.setApprovedVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));
				List.add(bean);
			}// end of loop

			deptlocChangeBean.setApprovedList(List);
		}

		/*else {

			deptlocChangeBean.setApprovedList(null);

		}*/
	}

	/* Rejected List */

	public void rejectedData(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		//Object[][] regData = getSqlModel().getSingleResult(getQuery(8));

		Object[][] regData = null;
		String rejectQuery=" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+ " WHERE DPT.DEPT_APPROVER_STATUS='R' and DEPT_INITIATOR="+ userId+"  ORDER BY DPT.DEPT_LOC_ID DESC ";
		//+ " WHERE DPT.DEPT_APPROVER_STATUS='R' ORDER BY DPT.DEPT_LOC_DATE ";
		
		regData = getSqlModel().getSingleResult(rejectQuery);
		
		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPageReject(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalRejectedPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageRejectedNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPageReject("1");
			ArrayList<Object> List = new ArrayList<Object>();
			deptlocChangeBean.setRejectedVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));
				List.add(bean);
			}// end of loop

			deptlocChangeBean.setRejectedList(List);
		}

		/*else {

			deptlocChangeBean.setRejectedList(null);

		}*/
	}

	public void setApproverData(
			DepartmentandLocationChangeBean deptlocChangeBean,
			Object[][] empFlow) {

		/*
		 * String str = "0"; if (empFlow != null) { for (int i = 0; i <
		 * empFlow.length; i++) { str += "," + empFlow[i][0]; } }
		 * logger.info("str________________" + str);
		 */

		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						DepartmentandLocationChangeBean deptBean = new DepartmentandLocationChangeBean();
						deptBean.setApproName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						deptBean.setSrNoIterator(srNo);
						arrList.add(deptBean);
					}
					deptlocChangeBean.setApproverList(arrList);
				}

			}
		} catch (Exception e) {
			// logger.error("Exception in setApproverData------" + e);
		}
	}

	public void editData(DepartmentandLocationChangeBean deptlocChangeBean,HttpServletRequest request) {

		try {

			System.out.println("inside editData method ##########");
			System.out.println("deptlocChangeBean.getDeptCode()=="
					+ deptlocChangeBean.getDeptCode());
			/*String query = " select nvl(DEPT_LOC_EMP_ID,''),nvl(hrms_emp_offc.EMP_TOKEN,''),nvl(hrms_emp_offc.EMP_FNAME,''),nvl(hrms_emp_offc.EMP_MNAME,''),nvl(hrms_emp_offc.EMP_LNAME,''),nvl(hrms_rank.RANK_NAME,'') "
	   			+"  ,nvl(hrms_d1_region.REGION_NAME,''),nvl(hrms_dept.DEPT_NAME,''),to_char(DEPT_LOC_DATE,'dd-mm-yyyy'), "
	   			+"  nvl(HRMS_D1_DEPARTMENT.DEPT_NUMBER,''),nvl(HRMS_EMP_ADDRESS.ADD_PH1,''),to_char(e1.EMP_FNAME||' '|| e1.EMP_MNAME||' '|| e1.EMP_LNAME),nvl(DEPT_LOC_DEPT_NO,''),nvl(dept.DEPT_NUMBER,'') ,"
	   			+"  nvl(DEPT_LOC_PHONE,''), nvl(DEPT_LOC_MANAGER,''),to_char(man.EMP_FNAME||' '|| man.EMP_MNAME||' '|| man.EMP_LNAME),nvl(DEPT_LOC_TYPE,''),nvl(DEPT_ADD1,''),"
	   			+"  nvl(DEPT_ADD2,''), nvl(DEPT_ADD3,''), "
	   			+"  nvl(DEPT_PHONE,''), nvl(DEPT_CITY,''),nvl(DPET_PINCODE,''),nvl(DEPT_STATE,''),nvl(DEPT_COUNTRY,''),"
	   			+"  nvl(DEPT_LOC_WORK_HOME,'') ,nvl(DEPT_LOC_APPROVER,''), "
	   			+"  to_char(app.EMP_FNAME||' '|| app.EMP_MNAME||' '|| app.EMP_LNAME),nvl(DEPT_APPROVER_STATUS,'') ,nvl(HRMS_D1_DEPARTMENT.DEPT_NUMBER,''),nvl(app.EMP_TOKEN,''), hrms_emp_offc.EMP_REPORTING_OFFICER,DEPT_INITIATOR,to_char(DEPT_APPLICATION_DATE,'dd-mm-yyyy HH24:MI'),DEPT_FILE_HEADER_NAME "
	   			+"  from HRMS_D1_DEPT_LOC_CHANGE " 
	   			+"  inner join hrms_emp_offc on (hrms_emp_offc.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_EMP_ID )  "
	   			+"   LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID)  "
	   			+"   LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)  "
	   			+"   LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)   "
	   			+"   Left join hrms_emp_offc e1 on (e1.emp_id=hrms_emp_offc.EMP_REPORTING_OFFICER) "   
	   			+"  left join HRMS_D1_DEPARTMENT on(hrms_emp_offc.EMP_DEPT_NO=HRMS_D1_DEPARTMENT.DEPT_ID) " 
	   			+"  left join HRMS_D1_DEPARTMENT dept on(HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_DEPT_NO=dept.DEPT_ID)	  "	
	   			+"   left join hrms_emp_offc man on (man.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER) 	"
	   			//+"  left join HRMS_LOCATION on(HRMS_LOCATION.LOCATION_CODE=HRMS_D1_DEPT_LOC_CHANGE.DEPT_CITY)	 "	
	   			+"  left join hrms_emp_offc app on(app.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER) "
	   			+"  LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
	   			+"  WHERE DEPT_LOC_ID=  " + deptlocChangeBean.getDeptCode();*/
			
			String query = " select nvl(DEPT_LOC_EMP_ID,''),nvl(hrms_emp_offc.EMP_TOKEN,''),nvl(hrms_emp_offc.EMP_FNAME,''),nvl(hrms_emp_offc.EMP_MNAME,''),nvl(hrms_emp_offc.EMP_LNAME,''),nvl(DEPT_EXECUTIVE,'') " 
				+" ,nvl(hrms_d1_region.REGION_NAME,''),nvl(hrms_dept.DEPT_NAME,''),to_char(DEPT_LOC_DATE,'dd-mm-yyyy'),  " 
				+" nvl(dept.DEPT_NAME,''),nvl(HRMS_EMP_ADDRESS.ADD_PH1,''),to_char(e1.EMP_FNAME||' '|| e1.EMP_MNAME||' '|| e1.EMP_LNAME),nvl(DEPT_LOC_DEPT_NO,''),nvl(dept1.DEPT_NAME,'') , " 
				+" nvl(DEPT_LOC_PHONE,''), nvl(DEPT_LOC_MANAGER,''),to_char(man.EMP_FNAME||' '|| man.EMP_MNAME||' '|| man.EMP_LNAME),nvl(DEPT_LOC_TYPE,''),nvl(DEPT_ADD1,''), " 
				+" nvl(DEPT_ADD2,''), nvl(DEPT_ADD3,''), " 
				+" nvl(DEPT_PHONE,''), nvl(DEPT_CITY,''),nvl(DPET_PINCODE,''),nvl(DEPT_STATE,''),nvl(DEPT_COUNTRY,''), " 
				+" nvl(DEPT_LOC_WORK_HOME,'') ,nvl(DEPT_LOC_APPROVER,''),  " 
				+" to_char(app.EMP_FNAME||' '|| app.EMP_MNAME||' '|| app.EMP_LNAME),nvl(DEPT_APPROVER_STATUS,'') ,nvl(hrms_dept.DEPT_NAME,''),nvl(app.EMP_TOKEN,''), hrms_emp_offc.EMP_REPORTING_OFFICER,DEPT_INITIATOR,to_char(DEPT_APPLICATION_DATE,'dd-mm-yyyy HH24:MI'),DEPT_FILE_HEADER_NAME,DEPT_CODE "  
				+" , emp.EMP_TOKEN ,To_char(emp.EMP_FNAME||' '||emp.EMP_MNAME||' '||emp.EMP_LNAME) from HRMS_D1_DEPT_LOC_CHANGE   " 
				+" inner join hrms_emp_offc on (hrms_emp_offc.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_EMP_ID )  "  
				+" LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID)   " 
				+" LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)   " 
				+" LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)   "  
				+" Left join hrms_emp_offc e1 on (e1.emp_id=hrms_emp_offc.EMP_REPORTING_OFFICER)     " 
				+" left join hrms_emp_offc man on (man.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER)  " 	
				+" left join hrms_emp_offc app on(app.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER)  " 
				+" LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " 
				//+" LEFT JOIN hrms_dept dept ON(dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
				+ "LEFT JOIN hrms_dept dept ON(dept.DEPT_ID = HRMS_D1_DEPT_LOC_CHANGE.DEPT_CODE  ) "
				+" LEFT JOIN hrms_dept  dept1 ON(dept1.DEPT_ID = HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_DEPT_NO)  "
				+ "left join hrms_emp_offc emp on(emp.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER)"
				+" WHERE DEPT_LOC_ID= " + deptlocChangeBean.getDeptCode();
			 


			Object[][] data = getSqlModel().getSingleResult(query);
			deptlocChangeBean.setEmpId(checkNull(String.valueOf(data[0][0])));
			deptlocChangeBean.setEmpNum(checkNull(String.valueOf(data[0][1])));
			deptlocChangeBean.setFname(checkNull(String.valueOf(data[0][2])));
			deptlocChangeBean.setMname(checkNull(String.valueOf(data[0][3])));
			deptlocChangeBean.setLname(checkNull(String.valueOf(data[0][4])));
			
			deptlocChangeBean.setEmpName(deptlocChangeBean.getFname()+ " " +deptlocChangeBean.getMname()+ " " +deptlocChangeBean.getLname());
			System.out.println("name here---"+deptlocChangeBean.getEmpName());
			deptlocChangeBean.setExecutive(checkNull(String.valueOf(data[0][5])));
			//deptlocChangeBean.setRegion(checkNull(String.valueOf(data[0][6])));
			//deptlocChangeBean.setArea(checkNull(String.valueOf(data[0][7])));
			deptlocChangeBean.setEffectivedateofChange(checkNull(String
					.valueOf(data[0][8])));// Date
			
			deptlocChangeBean.setDeptNum(checkNull(String.valueOf(data[0][9])));
			
			deptlocChangeBean.setFrom_workPhone(checkNull(String
					.valueOf(data[0][10])));// number
			deptlocChangeBean.setFrom_managerName(checkNull(String
					.valueOf(data[0][11])));// number

			deptlocChangeBean.setTo_departmentID(checkNull(String
					.valueOf(data[0][12])));
			deptlocChangeBean.setTo_department_Num(checkNull(String.valueOf(data[0][13])));
			deptlocChangeBean.setTo_workPhone(checkNull(String
					.valueOf(data[0][14])));// number
			
			deptlocChangeBean.setTo_managerID(checkNull(String
					.valueOf(data[0][15])));
			deptlocChangeBean.setTo_managerName(checkNull(String
					.valueOf(data[0][16])));// number

			//deptlocChangeBean.setPhysicaLocation(checkNull(String.valueOf(data[0][17])));
		/*	if (String.valueOf(data[0][17]).equals("H")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("T")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("C")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("O")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("W")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}
			*/
			deptlocChangeBean.setAddress1(checkNull(String.valueOf(data[0][18])));
			deptlocChangeBean.setAddress2(checkNull(String.valueOf(data[0][19])));
			deptlocChangeBean.setAddress3(checkNull(String.valueOf(data[0][20])));

			deptlocChangeBean.setPhone1(checkNull(String.valueOf(data[0][21])));
			deptlocChangeBean.setCity(checkNull(String.valueOf(data[0][22])));
			//deptlocChangeBean.setCity(checkNull(String.valueOf(data[0][23])));
			
			deptlocChangeBean.setState(checkNull(String.valueOf(data[0][24])));
			deptlocChangeBean.setCountry(checkNull(String.valueOf(data[0][25])));
			//deptlocChangeBean.setWorkHome(checkNull(String.valueOf(data[0][26])));
			if (String.valueOf(data[0][26]).equals("Y")) {
				deptlocChangeBean.setRadioYNValue(checkNull(String.valueOf(data[0][26])));
				request.setAttribute("workLocation", String.valueOf(data[0][26]));
			}else if (String.valueOf(data[0][26]).equals("N")) {
				deptlocChangeBean.setRadioYNValue(checkNull(String.valueOf(data[0][26])));
				request.setAttribute("workLocation", String.valueOf(data[0][26]));
			}
			//deptlocChangeBean.setFirstApproverCode(checkNull(String.valueOf(data[0][27])));
			//deptlocChangeBean.setApproverCode(checkNull(String.valueOf(data[0][27])));
			
			Object[][] empFlow = null;
			try {
				ReportingModel reporting = new ReportingModel();
				reporting.initiate(context, session);
				empFlow = reporting.generateEmpFlow(deptlocChangeBean.getEmpId(),
						"D1", 1);
				reporting.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {

				String setApprover = String.valueOf(empFlow[0][0]);
				// Approver Section Begins
				if (!checkNull(String.valueOf(data[0][27])).equals(
						setApprover)) {
					deptlocChangeBean.setApproverCode(checkNull(String
							.valueOf(data[0][27])));
					System.out.println("data[0][17]==" + data[0][27]);
					deptlocChangeBean.setApproverToken(checkNull(String
							.valueOf(data[0][37])));
					System.out.println("data[0][21]==" + data[0][37]);
					deptlocChangeBean.setSelectapproverName(checkNull(String
							.valueOf(data[0][38])));
					System.out.println("data[0][22]==" + data[0][38]);
				}

				// Approver Section Ends
			} else {

				deptlocChangeBean
						.setApproverCode(checkNull(String
								.valueOf(data[0][27])));
				System.out.println("data[0][17] #####" + data[0][27]);
				deptlocChangeBean
						.setApproverToken(checkNull(String
								.valueOf(data[0][37])));
				System.out.println("data[0][21]#####" + data[0][37]);
				deptlocChangeBean
						.setSelectapproverName(checkNull(String
								.valueOf(data[0][38])));
				System.out.println("data[0][22]#####" + data[0][38]);
			}
			
			//deptlocChangeBean.setApproverName(checkNull(String.valueOf(data[0][28])));
			//deptlocChangeBean.setSelectapproverName(checkNull(String.valueOf(data[0][28])));
			deptlocChangeBean.setApplnStatus(checkNull(String.valueOf(data[0][29])));
			deptlocChangeBean.setApplnActualStatus(deptlocChangeBean.getApplnStatus());
			
			deptlocChangeBean.setFrom_department_Num(checkNull(String.valueOf(data[0][30])));
			//deptlocChangeBean.setAppToken(checkNull(String.valueOf(data[0][31])));
		//	deptlocChangeBean.setApproverToken(checkNull(String.valueOf(data[0][31])));
			System.out.println("here-1--"+String.valueOf(data[0][27]));
			System.out.println("here-2--"+String.valueOf(data[0][28]));
			
			/*if(String.valueOf(data[0][32])!=null){
				Object [][] dataDefaultObj=null;
				String defaultManager="select  EMP_ID,EMP_TOKEN,to_char(hrms_emp_offc.EMP_FNAME||' '|| hrms_emp_offc.EMP_MNAME||' '|| hrms_emp_offc.EMP_LNAME) from hrms_emp_offc "
					+ " where EMP_ID="+String.valueOf(data[0][32]);
				dataDefaultObj = getSqlModel().getSingleResult(defaultManager);
				if(dataDefaultObj!=null && dataDefaultObj.length >0){
					deptlocChangeBean.setFirstApproverCode(checkNull(String.valueOf(dataDefaultObj[0][0])));
					deptlocChangeBean.setAppToken(checkNull(String.valueOf(dataDefaultObj[0][1])));
					deptlocChangeBean.setApproverName(checkNull(String.valueOf(dataDefaultObj[0][2])));
				}
			}*/
			
			deptlocChangeBean.setCompletedByCode(String.valueOf(data[0][33]));
			System.out.println("here new date---"+String.valueOf(data[0][34]));
			deptlocChangeBean.setCompletedDate(String.valueOf(data[0][34]));
			deptlocChangeBean.setAuthorizedToken(String.valueOf(data[0][35]));
			deptlocChangeBean.setDeptCodeSelect(String.valueOf(data[0][36]));
			System.out.println("here gfgdfgdf date---"+deptlocChangeBean.getCompletedDate());
			if(String.valueOf(data[0][33])!=null){
				Object [][] completedByObj=null;
				String completedByQuery="select  EMP_ID,EMP_TOKEN,to_char(hrms_emp_offc.EMP_FNAME||' '|| hrms_emp_offc.EMP_MNAME||' '|| hrms_emp_offc.EMP_LNAME) from hrms_emp_offc "
					+ " where EMP_ID="+String.valueOf(data[0][33]);
				completedByObj = getSqlModel().getSingleResult(completedByQuery);
				if(completedByObj!=null && completedByObj.length >0){
					deptlocChangeBean.setCompletedByCode(checkNull(String.valueOf(completedByObj[0][0])));
					deptlocChangeBean.setCompletedByToken(checkNull(String.valueOf(completedByObj[0][1])));
					deptlocChangeBean.setCompletedBy(checkNull(String.valueOf(completedByObj[0][2])));
				}
			}
			
			
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					logger
							.info("insertObj[" + i + "][" + j + "]  "
									+ data[i][j]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	public void view(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request, String deptDataId) {

		try {

			System.out.println("inside editData method ##########");
			System.out.println("deptlocChangeBean.getDeptCode()=="
					+ deptDataId);
			/*String query = " select nvl(DEPT_LOC_EMP_ID,''),nvl(hrms_emp_offc.EMP_TOKEN,''),nvl(hrms_emp_offc.EMP_FNAME,''),nvl(hrms_emp_offc.EMP_MNAME,''),nvl(hrms_emp_offc.EMP_LNAME,''),nvl(hrms_rank.RANK_NAME,'') "
	   			+"  ,nvl(hrms_d1_region.REGION_NAME,''),nvl(hrms_dept.DEPT_NAME,''),to_char(DEPT_LOC_DATE,'dd-mm-yyyy'), "
	   			+"  nvl(HRMS_D1_DEPARTMENT.DEPT_NUMBER,''),nvl(HRMS_EMP_ADDRESS.ADD_PH1,''),to_char(e1.EMP_FNAME||' '|| e1.EMP_MNAME||' '|| e1.EMP_LNAME),nvl(DEPT_LOC_DEPT_NO,''),nvl(dept.DEPT_NUMBER,'') ,"
	   			+"  nvl(DEPT_LOC_PHONE,''), nvl(DEPT_LOC_MANAGER,''),to_char(man.EMP_FNAME||' '|| man.EMP_MNAME||' '|| man.EMP_LNAME),nvl(DEPT_LOC_TYPE,''),nvl(DEPT_ADD1,''),"
	   			+"  nvl(DEPT_ADD2,''), nvl(DEPT_ADD3,''), "
	   			+"  nvl(DEPT_PHONE,''), nvl(DEPT_CITY,''),nvl(DPET_PINCODE,''),nvl(DEPT_STATE,''),nvl(DEPT_COUNTRY,''),"
	   			+"  nvl(DEPT_LOC_WORK_HOME,'') ,nvl(DEPT_LOC_APPROVER,''), "
	   			+"  to_char(app.EMP_FNAME||' '|| app.EMP_MNAME||' '|| app.EMP_LNAME),nvl(DEPT_APPROVER_STATUS,'') ,nvl(HRMS_D1_DEPARTMENT.DEPT_NUMBER,''),nvl(app.EMP_TOKEN,''), hrms_emp_offc.EMP_REPORTING_OFFICER ,HRMS_D1_DEPT_LOC_CHANGE.DEPT_LEVEL,DEPT_LOC_ID,DEPT_INITIATOR,to_char(DEPT_APPLICATION_DATE,'dd-mm-yyyy HH24:MI'),DEPT_FILE_HEADER_NAME  "
	   			+"  from HRMS_D1_DEPT_LOC_CHANGE " 
	   			+"  inner join hrms_emp_offc on (hrms_emp_offc.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_EMP_ID )  "
	   			+"   LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID)  "
	   			+"   LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)  "
	   			+"   LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)   "
	   			+"   Left join hrms_emp_offc e1 on (e1.emp_id=hrms_emp_offc.EMP_REPORTING_OFFICER) "   
	   			+"  left join HRMS_D1_DEPARTMENT on(hrms_emp_offc.EMP_DEPT_NO=HRMS_D1_DEPARTMENT.DEPT_ID) " 
	   			+"  left join HRMS_D1_DEPARTMENT dept on(HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_DEPT_NO=dept.DEPT_ID)	  "	
	   			+"   left join hrms_emp_offc man on (man.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER) 	"
	   			//+"  left join HRMS_LOCATION on(HRMS_LOCATION.LOCATION_CODE=HRMS_D1_DEPT_LOC_CHANGE.DEPT_CITY)	 "	
	   			+"  left join hrms_emp_offc app on(app.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER) "
	   			+"  LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
	   			+"  WHERE DEPT_LOC_ID=  " + deptDataId;*/

			String query = " select nvl(DEPT_LOC_EMP_ID,''),nvl(hrms_emp_offc.EMP_TOKEN,''),nvl(hrms_emp_offc.EMP_FNAME,''),nvl(hrms_emp_offc.EMP_MNAME,''),nvl(hrms_emp_offc.EMP_LNAME,''),nvl(DEPT_EXECUTIVE,'')  "
 			 +"  ,nvl(hrms_d1_region.REGION_NAME,''),nvl(hrms_dept.DEPT_NAME,''),to_char(DEPT_LOC_DATE,'dd-mm-yyyy'),   "
 			 +"  nvl(dept1.DEPT_NAME,''),nvl(HRMS_EMP_ADDRESS.ADD_PH1,''),to_char(e1.EMP_FNAME||' '|| e1.EMP_MNAME||' '|| e1.EMP_LNAME),nvl(DEPT_LOC_DEPT_NO,''),nvl(dept2.DEPT_NAME,'') ,  "
 			 +" nvl(DEPT_LOC_PHONE,''), nvl(DEPT_LOC_MANAGER,''),to_char(man.EMP_FNAME||' '|| man.EMP_MNAME||' '|| man.EMP_LNAME),nvl(DEPT_LOC_TYPE,''),nvl(DEPT_ADD1,''),  "
 			 +" nvl(DEPT_ADD2,''), nvl(DEPT_ADD3,''),   "
 			 +"  nvl(DEPT_PHONE,''), nvl(DEPT_CITY,''),nvl(DPET_PINCODE,''),nvl(DEPT_STATE,''),nvl(DEPT_COUNTRY,''),  "
 			 +"  nvl(DEPT_LOC_WORK_HOME,'') ,nvl(DEPT_LOC_APPROVER,''),   "
 			 +"  to_char(app.EMP_FNAME||' '|| app.EMP_MNAME||' '|| app.EMP_LNAME),nvl(DEPT_APPROVER_STATUS,'') ,nvl(dept1.DEPT_NAME,''),nvl(app.EMP_TOKEN,''), hrms_emp_offc.EMP_REPORTING_OFFICER ,HRMS_D1_DEPT_LOC_CHANGE.DEPT_LEVEL,DEPT_LOC_ID,DEPT_INITIATOR,to_char(DEPT_APPLICATION_DATE,'dd-mm-yyyy HH24:MI'),DEPT_FILE_HEADER_NAME  "  
 			 +"  ,DEPT_CODE," + "emp.EMP_TOKEN ,To_char(emp.EMP_FNAME||' '||emp.EMP_MNAME||' '||emp.EMP_LNAME)"
 			 +		" from HRMS_D1_DEPT_LOC_CHANGE   "
 			 +"   inner join hrms_emp_offc on (hrms_emp_offc.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_EMP_ID )  "  
			+"  LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID)    "
			+"  LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)  "  
			+"  LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_D1_DEPT_LOC_CHANGE.DEPT_CODE)     "
			+"  Left join hrms_emp_offc e1 on (e1.emp_id=hrms_emp_offc.EMP_REPORTING_OFFICER)  "    
			+"  LEFT JOIN hrms_dept dept1 ON(dept1.DEPT_ID = HRMS_D1_DEPT_LOC_CHANGE.DEPT_CODE)  "
			+"  LEFT JOIN hrms_dept dept2 ON(dept2.DEPT_ID = HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_DEPT_NO)  "
			+" left join hrms_emp_offc man on (man.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER) 	  "
			+" left join hrms_emp_offc app on(app.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER)   "
			+" LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)   "
			+ "left join hrms_emp_offc emp on(emp.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER)"
			+" WHERE DEPT_LOC_ID=   " + deptDataId;
			  

			
			
			Object[][] data = getSqlModel().getSingleResult(query);
			deptlocChangeBean.setEmpId(checkNull(String.valueOf(data[0][0])));
			deptlocChangeBean.setEmpNum(checkNull(String.valueOf(data[0][1])));
			deptlocChangeBean.setFname(checkNull(String.valueOf(data[0][2])));
			deptlocChangeBean.setMname(checkNull(String.valueOf(data[0][3])));
			deptlocChangeBean.setLname(checkNull(String.valueOf(data[0][4])));
			
			deptlocChangeBean.setEmpName(deptlocChangeBean.getFname()+ " " +deptlocChangeBean.getMname()+ " " +deptlocChangeBean.getLname());
			System.out.println("name here---"+deptlocChangeBean.getEmpName());
			deptlocChangeBean.setExecutive(checkNull(String.valueOf(data[0][5])));
			//deptlocChangeBean.setRegion(checkNull(String.valueOf(data[0][6])));
			//deptlocChangeBean.setArea(checkNull(String.valueOf(data[0][7])));
			deptlocChangeBean.setEffectivedateofChange(checkNull(String
					.valueOf(data[0][8])));// Date
			
			deptlocChangeBean.setDeptNum(checkNull(String.valueOf(data[0][9])));
			
			deptlocChangeBean.setFrom_workPhone(checkNull(String
					.valueOf(data[0][10])));// number
			deptlocChangeBean.setFrom_managerName(checkNull(String
					.valueOf(data[0][11])));// number

			deptlocChangeBean.setTo_departmentID(checkNull(String
					.valueOf(data[0][12])));
			deptlocChangeBean.setTo_department_Num(checkNull(String.valueOf(data[0][13])));
			deptlocChangeBean.setTo_workPhone(checkNull(String
					.valueOf(data[0][14])));// number
			
			deptlocChangeBean.setTo_managerID(checkNull(String
					.valueOf(data[0][15])));
			deptlocChangeBean.setTo_managerName(checkNull(String
					.valueOf(data[0][16])));// number

			//deptlocChangeBean.setPhysicaLocation(checkNull(String.valueOf(data[0][17])));
		/*	if (String.valueOf(data[0][17]).equals("H")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("T")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("C")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("O")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}else if (String.valueOf(data[0][17]).equals("W")) {
				deptlocChangeBean.setRadioValue(checkNull(String.valueOf(data[0][17])));
				request.setAttribute("physicalLocation", String.valueOf(data[0][17]));
			}
			*/
			deptlocChangeBean.setAddress1(checkNull(String.valueOf(data[0][18])));
			deptlocChangeBean.setAddress2(checkNull(String.valueOf(data[0][19])));
			deptlocChangeBean.setAddress3(checkNull(String.valueOf(data[0][20])));

			deptlocChangeBean.setPhone1(checkNull(String.valueOf(data[0][21])));
			deptlocChangeBean.setCity(checkNull(String.valueOf(data[0][22])));
			//deptlocChangeBean.setCity(checkNull(String.valueOf(data[0][23])));
			
			deptlocChangeBean.setState(checkNull(String.valueOf(data[0][24])));
			deptlocChangeBean.setCountry(checkNull(String.valueOf(data[0][25])));
			//deptlocChangeBean.setWorkHome(checkNull(String.valueOf(data[0][26])));
			if (String.valueOf(data[0][26]).equals("Y")) {
				deptlocChangeBean.setRadioYNValue(checkNull(String.valueOf(data[0][26])));
				request.setAttribute("workLocation", String.valueOf(data[0][26]));
			}else if (String.valueOf(data[0][26]).equals("N")) {
				deptlocChangeBean.setRadioYNValue(checkNull(String.valueOf(data[0][26])));
				request.setAttribute("workLocation", String.valueOf(data[0][26]));
			}
			//deptlocChangeBean.setFirstApproverCode(checkNull(String.valueOf(data[0][27])));
			///deptlocChangeBean.setApproverCode(checkNull(String.valueOf(data[0][27])));
			
			
			Object[][] empFlow = null;
			try {
				ReportingModel reporting = new ReportingModel();
				reporting.initiate(context, session);
				empFlow = reporting.generateEmpFlow(deptlocChangeBean.getEmpId(),
						"D1", 1);
				reporting.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {

				String setApprover = String.valueOf(empFlow[0][0]);
				// Approver Section Begins
				if (!checkNull(String.valueOf(data[0][27])).equals(
						setApprover)) {
					deptlocChangeBean.setApproverCode(checkNull(String
							.valueOf(data[0][27])));
					System.out.println("data[0][17]==" + data[0][27]);
					deptlocChangeBean.setApproverToken(checkNull(String
							.valueOf(data[0][39])));
					System.out.println("data[0][21]==" + data[0][39]);
					deptlocChangeBean.setSelectapproverName(checkNull(String
							.valueOf(data[0][40])));
					System.out.println("data[0][22]==" + data[0][40]);
				}

				// Approver Section Ends
			} else {

				deptlocChangeBean
						.setApproverCode(checkNull(String
								.valueOf(data[0][27])));
				System.out.println("data[0][17] #####" + data[0][27]);
				deptlocChangeBean
						.setApproverToken(checkNull(String
								.valueOf(data[0][39])));
				System.out.println("data[0][21]#####" + data[0][39]);
				deptlocChangeBean
						.setSelectapproverName(checkNull(String
								.valueOf(data[0][40])));
				System.out.println("data[0][22]#####" + data[0][40]);
			}
			
			
			
			//deptlocChangeBean.setApproverName(checkNull(String.valueOf(data[0][28])));
		//	deptlocChangeBean.setSelectapproverName(checkNull(String.valueOf(data[0][28])));
			deptlocChangeBean.setApplnStatus(checkNull(String.valueOf(data[0][29])));
			deptlocChangeBean.setApplnActualStatus(deptlocChangeBean.getApplnStatus());
			
			deptlocChangeBean.setFrom_department_Num(checkNull(String.valueOf(data[0][30])));
			//deptlocChangeBean.setAppToken(checkNull(String.valueOf(data[0][31])));
			//deptlocChangeBean.setApproverToken(checkNull(String.valueOf(data[0][31])));
			System.out.println("here-1--"+String.valueOf(data[0][27]));
			System.out.println("here-2--"+String.valueOf(data[0][28]));
			
			/*if(String.valueOf(data[0][32])!=null){
				Object [][] dataDefaultObj=null;
				String defaultManager="select  EMP_ID,EMP_TOKEN,to_char(hrms_emp_offc.EMP_FNAME||' '|| hrms_emp_offc.EMP_MNAME||' '|| hrms_emp_offc.EMP_LNAME) from hrms_emp_offc "
					+ " where EMP_ID="+String.valueOf(data[0][32]);
				dataDefaultObj = getSqlModel().getSingleResult(defaultManager);
				if(dataDefaultObj!=null && dataDefaultObj.length >0){
					deptlocChangeBean.setApproverCode(checkNull(String.valueOf(dataDefaultObj[0][0])));
					deptlocChangeBean.setApproverToken(checkNull(String.valueOf(dataDefaultObj[0][1])));
					deptlocChangeBean.setSelectapproverName(checkNull(String.valueOf(dataDefaultObj[0][2])));
				}
			}*/
			System.out.println("String.valueOf(data[0][33])---"+String.valueOf(data[0][33]));
			deptlocChangeBean.setLevel(checkNull(String.valueOf(data[0][33])));
			deptlocChangeBean.setDeptCode(checkNull(String.valueOf(data[0][34])));
			
			deptlocChangeBean.setCompletedByCode(String.valueOf(data[0][35]));
			System.out.println("here new date---"+String.valueOf(data[0][36]));
			deptlocChangeBean.setCompletedDate(String.valueOf(data[0][36]));
			deptlocChangeBean.setAuthorizedToken(String.valueOf(data[0][37]));
			deptlocChangeBean.setDeptCodeSelect(String.valueOf(data[0][38]));
			System.out.println("here gfgdfgdf date---"+deptlocChangeBean.getCompletedDate());
			if(String.valueOf(data[0][35])!=null){
				Object [][] completedByObj=null;
				String completedByQuery="select  EMP_ID,EMP_TOKEN,to_char(hrms_emp_offc.EMP_FNAME||' '|| hrms_emp_offc.EMP_MNAME||' '|| hrms_emp_offc.EMP_LNAME) from hrms_emp_offc "
					+ " where EMP_ID="+String.valueOf(data[0][35]);
				completedByObj = getSqlModel().getSingleResult(completedByQuery);
				if(completedByObj!=null && completedByObj.length >0){
					deptlocChangeBean.setCompletedByCode(checkNull(String.valueOf(completedByObj[0][0])));
					deptlocChangeBean.setCompletedByToken(checkNull(String.valueOf(completedByObj[0][1])));
					deptlocChangeBean.setCompletedBy(checkNull(String.valueOf(completedByObj[0][2])));
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/*	public void view(DepartmentandLocationChangeBean bean,
			HttpServletRequest request, String deptDataId) {

		try {
			String query = " SELECT DEPT_LOC_ID, DEPT_LOC_EMP_ID, TO_CHAR(DEPT_LOC_DATE,'DD-MM-YYYY'),DEPT_LOC_DEPT_NO, DEPT_LOC_PHONE, DEPT_LOC_MANAGER, "
					+ " DEPT_LOC_TYPE, DEPT_LOC_WORK_HOME, DEPT_LOC_APPROVER,DEPT_APPROVER_STATUS, DEPT_ADD1, DEPT_ADD2,"
					+ " DEPT_ADD3, DEPT_PHONE, DEPT_CITY,DEPT_STATE, DPET_PINCODE,DEPT_COUNTRY, "
					+ " hrms_emp_offc.EMP_FNAME,hrms_emp_offc.EMP_MNAME,hrms_emp_offc.EMP_LNAME,hrms_emp_offc.EMP_TOKEN, "
					+ "hrms_rank.RANK_NAME,HRMS_D1_REGION.REGION_NAME,HRMS_LOCATION.LOCATION_NAME,HRMS_D1_DEPARTMENT.DEPT_NUMBER,  "
					+ " to_char(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),d1.DEPT_NUMBER,HRMS_EMP_ADDRESS.ADD_PH1, "
					+ " to_char(hrms_emp_offc.EMP_FNAME||' '||hrms_emp_offc.EMP_MNAME||' '||hrms_emp_offc.EMP_LNAME),dept.DEPT_NAME ,"
					+ " to_char(man.EMP_FNAME||' '||man.EMP_MNAME||' '||man.EMP_LNAME) ,nvl(DEPT_APPROVER_COMMENT,''),HRMS_D1_DEPT_LOC_CHANGE.DEPT_LEVEL, man.EMP_ID,man.EMP_TOKEN "
					+ " FROM HRMS_D1_DEPT_LOC_CHANGE "
					+ " inner join hrms_emp_offc on(HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_EMP_ID=hrms_emp_offc.EMP_ID) "
					+ " inner join hrms_rank on(hrms_rank.RANK_ID=hrms_emp_offc.EMP_RANK) "
					+ " Left join HRMS_D1_REGION on(HRMS_D1_REGION.REGION_ID=hrms_emp_offc.EMP_REGION_ID) "
					+ " Left join HRMS_LOCATION on(HRMS_LOCATION.LOCATION_CODE=HRMS_D1_DEPT_LOC_CHANGE.DEPT_CITY)"
					+ " Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_DEPT_NO) "
					+ " Left join hrms_emp_offc m1 on(m1.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER) "
					+ " Left join HRMS_D1_DEPARTMENT d1 on(hrms_emp_offc.EMP_DEPT_NO=d1.DEPT_ID) "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_EMP_ID)"
					+ " LEFT JOIN HRMS_DEPT dept on(hrms_emp_offc.EMP_DEPT=dept.DEPT_ID) "
					+ " left join hrms_emp_offc man on(HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_APPROVER=man.EMP_ID)"
					+ " where DEPT_LOC_ID=" + deptDataId;

			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setDeptCode(checkNull(String.valueOf(data[0][0])));
			bean.setEmpId(checkNull(String.valueOf(data[0][1])));
			bean
					.setEffectivedateofChange(checkNull(String
							.valueOf(data[0][2])));
			bean.setFname(checkNull(String.valueOf(data[0][18])));
			bean.setMname(checkNull(String.valueOf(data[0][19])));
			bean.setLname(checkNull(String.valueOf(data[0][20])));
			bean.setEmpName(" "+" "+bean.getFname()+ "  " +bean.getMname()+ "  " +bean.getLname());
			
			bean.setEmpNum(checkNull(String.valueOf(data[0][21])));
			bean.setRegion(checkNull(String.valueOf(data[0][23])));
			bean.setExecutive(checkNull(String.valueOf(data[0][22])));
			bean.setCity(checkNull(String.valueOf(data[0][24])));
			System.out.println("radio value---" + String.valueOf(data[0][6]));
			if (String.valueOf(data[0][6]).equals("H")) {
				bean.setRadioValue(checkNull(String.valueOf(data[0][6])));
				request.setAttribute("physicalLocation", String
						.valueOf(data[0][6]));
			} else if (String.valueOf(data[0][6]).equals("T")) {
				bean.setRadioValue(checkNull(String.valueOf(data[0][6])));
				request.setAttribute("physicalLocation", String
						.valueOf(data[0][6]));
			} else if (String.valueOf(data[0][6]).equals("C")) {
				bean.setRadioValue(checkNull(String.valueOf(data[0][6])));
				request.setAttribute("physicalLocation", String
						.valueOf(data[0][6]));
			} else if (String.valueOf(data[0][6]).equals("W")) {
				bean.setRadioValue(checkNull(String.valueOf(data[0][6])));
				request.setAttribute("physicalLocation", String
						.valueOf(data[0][6]));
			} else if (String.valueOf(data[0][6]).equals("O")) {
				bean.setRadioValue(checkNull(String.valueOf(data[0][6])));
				request.setAttribute("physicalLocation", String
						.valueOf(data[0][6]));
			}
			bean.setAddress1(checkNull(String.valueOf(data[0][10])));
			bean.setAddress2(checkNull(String.valueOf(data[0][11])));
			bean.setAddress3(checkNull(String.valueOf(data[0][12])));
			bean.setPhone1(checkNull(String.valueOf(data[0][13])));
			bean.setCityId(checkNull(String.valueOf(data[0][14])));
			bean.setState(checkNull(String.valueOf(data[0][15])));
			bean.setCountry(checkNull(String.valueOf(data[0][17])));
			bean.setTo_department_Num(checkNull(String.valueOf(data[0][25])));
			bean.setDeptNum(checkNull(String.valueOf(data[0][25])));
			bean.setFrom_department_Num(checkNull(String.valueOf(data[0][27])));
			bean.setFrom_workPhone(checkNull(String.valueOf(data[0][28])));
			bean.setFrom_managerName(checkNull(String.valueOf(data[0][29])));
			bean.setTo_managerName(checkNull(String.valueOf(data[0][26])));
			bean.setArea(checkNull(String.valueOf(data[0][30])));
			bean.setTo_workPhone(checkNull(String.valueOf(data[0][4])));
			System.out.println("String.valueOf(data[0][7])0000---"
					+ String.valueOf(data[0][7]));
			if (String.valueOf(data[0][7]).equals("Y")) {
				System.out.println("in if----");
				bean.setRadioYNValue(checkNull(String.valueOf(data[0][7])));
				System.out.println("in if----" + bean.getRadioYNValue());
				request
						.setAttribute("workLocation", String
								.valueOf(data[0][7]));
				System.out.println("in if--rrrrrr--"
						+ request.getAttribute("workLocation"));
			} else if (String.valueOf(data[0][7]).equals("N")) {
				System.out.println("in else----");
				bean.setRadioYNValue(checkNull(String.valueOf(data[0][7])));
				request
						.setAttribute("workLocation", String
								.valueOf(data[0][7]));
			}
			bean.setSelectapproverName(checkNull(String.valueOf(data[0][31])));
			bean.setApplnStatus(checkNull(String.valueOf(data[0][9])));
			//bean.setStatus(checkNull(String.valueOf(data[0][9])));
			//Approver Comments 
			/*if(String.valueOf(data[0][32])!=null && !String.valueOf(data[0][32]).equals("") && !String.valueOf(data[0][32]).equals("null")){
				bean.setApproverComments(checkNull(String.valueOf(data[0][32])));
			}else{
				bean.setApproverComments("");
			}*/
		/*	System.out.println("level in model--"+String.valueOf(data[0][33]));
			
			bean.setLevel(String.valueOf(data[0][33]));
			
			bean.setApproverCode(String.valueOf(data[0][34]));
			bean.setApproverToken(String.valueOf(data[0][35]));
			System.out.println("level in model--"+bean.getLevel());

		} catch (Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}
	}*/

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	public void getStateCountry(DepartmentandLocationChangeBean addDet) {
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ addDet.getCityId() + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);

		if (stateCode.length > 0) {
			addDet.setState(checkNull(String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if (countryName.length > 0) {
				addDet.setCountry(checkNull(String.valueOf(countryName[0][1])));
			}// end of nested if
			else
				addDet.setCountry("");
		}// end of if
	}

	
	public void getManagerName(String approverCode,DepartmentandLocationChangeBean addDet) {
		Object[][] data=null;
		String query="";
		System.out.println("deptlocChangeBean.getFirstApproverCode()=="+approverCode);
		if(!approverCode.equals("0") && !approverCode.equals("")){
			System.out.println("inside if---"+approverCode);
			query = " SELECT EMP_TOKEN,to_char(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME),EMP_ID from HRMS_EMP_OFFC "
					 + " where EMP_ID="+approverCode;
			data= getSqlModel().getSingleResult(query);
			if (data.length > 0 && data!=null) {
					addDet.setAppToken(checkNull(String.valueOf(data[0][0])));
					addDet.setApproverName(checkNull(String.valueOf(data[0][1])));
					addDet.setFirstApproverCode(checkNull(String.valueOf(data[0][2])));
					addDet.setFrom_managerName(addDet.getApproverName());
				}else{
					addDet.setAppToken("");
					addDet.setApproverName("");
					addDet.setFirstApproverCode("0");
					addDet.setFrom_managerName("");
				}
		}else {
			addDet.setAppToken("");
			addDet.setApproverName("");
			addDet.setFirstApproverCode("0");
			addDet.setFrom_managerName("");
		}
		
			
		
		
	}
	
	
	public void getDefaultManagerName(DepartmentandLocationChangeBean addDet) {
		Object[][] data=null;
		String reportingCode="";
		String query="";
		String query1="";
		System.out.println("reportingCode---"+addDet.getEmpId());
		
		
		
		if(addDet.getDeptCode()!=null && !addDet.getDeptCode().equals("")) {
			query = " SELECT EMP_REPORTING_OFFICER,EMP_TOKEN,to_char(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME) from HRMS_EMP_OFFC "
					 + " where EMP_ID="+addDet.getEmpId();
			data= getSqlModel().getSingleResult(query);
			if (data.length > 0) {
				reportingCode=String.valueOf(data[0][0]);
				}
			
			Object[][] data1=null;
			System.out.println("reportingCode---"+reportingCode);
			if(reportingCode!=null){
				query1 = " SELECT EMP_TOKEN,to_char(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME) from HRMS_EMP_OFFC "
					 + " where EMP_ID="+reportingCode;
						data1= getSqlModel().getSingleResult(query1);
				if(data1!=null && data1.length>0){
					addDet.setAppToken(String.valueOf(data1[0][0]));
					addDet.setApproverName(String.valueOf(data1[0][1]));
				}else{
					addDet.setAppToken("");
					addDet.setApproverName("");
				}
			}
		}
	}
	public void approvedCancelData(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		//Object[][] regData = getSqlModel().getSingleResult(getQuery(10));
		Object[][] regData = null;
		
		String approvedCancelDataQuery=" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+ " WHERE DPT.DEPT_APPROVER_STATUS='X' and DEPT_INITIATOR="+ userId+"  ORDER BY DPT.DEPT_LOC_DATE ";
		//+ " WHERE DPT.DEPT_APPROVER_STATUS='X' ORDER BY DPT.DEPT_LOC_DATE ";
		
		regData = getSqlModel().getSingleResult(approvedCancelDataQuery);
		
		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPageApprovedCancelList(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPageApprovedCancelList("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));
				List.add(bean);
			}// end of loop

			deptlocChangeBean.setApprovedCancelList(List);
		}

		/*else {

			deptlocChangeBean.setApprovedCancelList(null);

		}*/
	}
	
	public void approvedRejectedData(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		Object[][] regData = getSqlModel().getSingleResult(getQuery(11));

		String approvedRejectedDataQuery=" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+ " WHERE DPT.DEPT_APPROVER_STATUS='Z' and DEPT_INITIATOR="+ userId+"  ORDER BY DPT.DEPT_LOC_DATE ";
			//+ " WHERE DPT.DEPT_APPROVER_STATUS='Z' ORDER BY DPT.DEPT_LOC_DATE ";
		regData = getSqlModel().getSingleResult(approvedRejectedDataQuery);
		
		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPageRejectCancel(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPageRejectCancel("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));

				List.add(bean);
			}// end of loop

			deptlocChangeBean.setApprovedRejectList(List);
		}

		/*else {

			deptlocChangeBean.setApprovedRejectList(null);

		}*/
	}
	
	public void cancel(DepartmentandLocationChangeBean deptlocChangeBean,
			HttpServletRequest request,String userId) {

		Object[][] regData = getSqlModel().getSingleResult(getQuery(12));
		
		String cancelQuery=" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+ " FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+ " Inner JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+ " WHERE DPT.DEPT_APPROVER_STATUS='C' and DEPT_INITIATOR="+ userId+"  ORDER BY DPT.DEPT_LOC_DATE ";
		//+ " WHERE DPT.DEPT_APPROVER_STATUS='C' ORDER BY DPT.DEPT_LOC_DATE ";

		regData = getSqlModel().getSingleResult(cancelQuery);
		
		if (regData != null && regData.length > 0) {
			deptlocChangeBean.setModeLength("true");

			deptlocChangeBean.setTotalNoOfRecords(String
					.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(
					deptlocChangeBean.getMyPageCancel(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				deptlocChangeBean.setMyPageCancel("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DepartmentandLocationChangeBean bean = new DepartmentandLocationChangeBean();
				bean.setDeptCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEmpNum(checkNull(String.valueOf(regData[i][1])));
				bean.setFname(checkNull(String.valueOf(regData[i][2])));
				bean.setDepAppDate(checkNull(String.valueOf(regData[i][3])));
				bean.setAuthorizedToken(checkNull(String.valueOf(regData[i][4])));
				List.add(bean);
			}// end of loop

			deptlocChangeBean.setCancelList(List);
		}

		/*else {

			deptlocChangeBean.setCancelList(null);

		}*/
	}
	public boolean delete(DepartmentandLocationChangeBean bean, HttpServletRequest request) {
		boolean result = false;

		String hId = bean.getDeptCode();

		String delQuery = "DELETE FROM HRMS_D1_DEPT_LOC_CHANGE WHERE DEPT_LOC_ID="+hId;
		result = getSqlModel().singleExecute(delQuery);
		return result;
	}
	
	public void getEmployeeDetails(String empId,DepartmentandLocationChangeBean bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;
			
			
			
			/*String query = " SELECT EMP_TOKEN,to_char(EMP_FNAME ||' ' ||EMP_MNAME||' ' ||EMP_LNAME),RANK_NAME,REGION_NAME, "
				+ "DEPT_NAME,HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_D1_DEPARTMENT.DEPT_NUMBER ,ADD_PH1,to_char(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME) ,HRMS_EMP_OFFC.EMP_ID ,NVL(EMP_REPORTING_OFFICER,0)"
				+ " FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID) "
				+ " LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID) "
				+ " LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+ " Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+ " Left join HRMS_D1_DEPT_LOC_CHANGE on (HRMS_EMP_OFFC.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";*/
			String query = " SELECT EMP_TOKEN,to_char(EMP_FNAME ||' ' ||EMP_MNAME||' ' ||EMP_LNAME),RANK_NAME,REGION_NAME, " 
				+ " DEPT_NAME,dept.DEPT_ID,dept.DEPT_NAME,dept.DEPT_NAME ,ADD_PH1,to_char(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME) ,HRMS_EMP_OFFC.EMP_ID ,NVL(EMP_REPORTING_OFFICER,0)  "
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
				+ " LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID)  "
				+ " LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID) " 
				+ " LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) " 
				+ " LEFT JOIN hrms_dept dept ON(dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO)  "
				+ " Left join HRMS_D1_DEPT_LOC_CHANGE on (HRMS_EMP_OFFC.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER)  "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=? ";
			
			
			Object[][] data = getSqlModel().getSingleResult(query,
					beanObj);
		
				bean.setEmpNum(checkNull(String.valueOf(data[0][0])));
				bean.setEmpName(checkNull(String.valueOf(data[0][1])));
				bean.setExecutive(checkNull(String.valueOf(data[0][2])));
				//bean.setExecutive(checkNull(String.valueOf(data[0][2])));
				bean.setRegion(checkNull(String.valueOf(data[0][3])));
				bean.setArea(checkNull(String.valueOf(data[0][4])));
				bean.setDeptCodeSelect(checkNull(String.valueOf(data[0][5])));
				bean.setDeptNum(checkNull(String.valueOf(data[0][6])));
				bean.setFrom_department_Num(checkNull(String.valueOf(data[0][7])));
				bean.setFrom_workPhone(checkNull(String.valueOf(data[0][8])));
				bean.setFrom_managerName(checkNull(String.valueOf(data[0][9])));
				bean.setEmpId(checkNull(String.valueOf(data[0][10])));
				System.out.println("Value         "+String.valueOf(data[0][10]));
				bean.setFirstApproverCode(String.valueOf(data[0][11]));
				 
				
				if(!bean.getFirstApproverCode().equals("0")){
				query = " SELECT EMP_TOKEN,to_char(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME),EMP_ID from HRMS_EMP_OFFC "
					 + " where EMP_ID="+String.valueOf(data[0][10]);
			data= getSqlModel().getSingleResult(query);
			if (data.length > 0 && data!=null) {
				bean.setAppToken(checkNull(String.valueOf(data[0][0])));
				bean.setApproverName(checkNull(String.valueOf(data[0][1])));
				bean.setFirstApproverCode(checkNull(String.valueOf(data[0][2])));
				bean.setFrom_managerName(bean.getApproverName());
				}
			}
				
				
			String dateQuery="SELECT to_char(sysdate,'dd-mm-yyyy hh24:mi') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setCompletedDate(String.valueOf(dateObj[0][0]));
			}
			bean.setCompletedByCode(bean.getEmpId());
			bean.setCompletedByToken(bean.getEmpNum());
			bean.setCompletedBy(bean.getEmpName());
			
			// end of for loop
		} catch (Exception e) {
		}
		
	}
	public Object[][] getApproverCommentList(String deptDataChangeId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, nvl(DEPT_COMMENTS,''), "
			+ " TO_CHAR(DEPT_DATE, 'DD-MM-YYYY') AS DEPT_DATE, "
			+ " DECODE(DEPT_STATUS, 'A', 'Approved', 'P', 'Pending','F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
			+ " FROM HRMS_D1_DEPT_DATA_PATH PATH " + " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.DEPT_APPROVER) "
			+ " WHERE DEPT_LOC_ID = " + deptDataChangeId + " Order by DATA_PATH_ID ";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}
}
