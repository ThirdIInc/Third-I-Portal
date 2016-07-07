package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.WorkersCompInjury;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.leave.TimeUtility;

public class WorkersCompInjuryModel extends ModelBase {

	public boolean draft(WorkersCompInjury bean,String status) {
		boolean result=false;
		Object[][]data=new Object[1][29];
		data[0][0]=bean.getEmpId();
		data[0][1]=bean.getNoOfDependancy();
		data[0][2]=bean.getDoi();
		data[0][3]=bean.getToi();//TIME
		data[0][4]=bean.getHrsWorkedDate();
		data[0][5]=bean.getNormalWorkingHrsFrom();//TIME
		data[0][6]=bean.getNormalWorkingHrsTo();//TIME
		data[0][7]=bean.getDke();
		data[0][8]=bean.getInjuryReportedName();
		data[0][9]=bean.getTitle();
		data[0][10]=bean.getIncidentResult();
		data[0][11]=bean.getProbableLengthDisability();
		data[0][12]=bean.getLostWorkDayDate();
		data[0][13]=bean.getInjuredReturnWork();
		data[0][14]=bean.getDor();		
		data[0][15]=bean.getTor();//TIME
		data[0][16]=bean.getAddressAccident();
		data[0][17]=bean.getAddressPhone();
		data[0][18]=bean.getDescribeInjury();
		data[0][19]=bean.getPartOfBodyAffected();
		data[0][20]=bean.getNameAddressPhy();
		data[0][21]=bean.getTypeOfTretment();
		data[0][22]=bean.getReasonToDoubt();
		String workerCode=bean.getWorkersCode();
		if(workerCode.equals("")){
			String selQuery="SELECT NVL(MAX(WORKERS_ID),0)+1 ,TO_CHAR(SYSDATE,'DD-MM-YYYY')   FROM HRMS_D1_WRKRS_COMP_INJURY ";
			Object[][]selData=getSqlModel().getSingleResult(selQuery);
			workerCode=String.valueOf(selData[0][0]);
			bean.setWorkersCode(workerCode);
			
			/**
			 * SET TRACKING NO
			 */
			if(selData !=null && selData.length>0){			
				try {
					ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					String applnCode = model.generateApplicationCode(bean.getWorkersCode(), "D1-INJURY", bean.getEmpId(),String.valueOf(selData[0][1]));
					bean.setTrackingNo(checkNull(applnCode));
					model.terminate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				}	
		}
		
		String first="0";
		String comm="";
		String selQuery="DELETE FROM HRMS_D1_WRKRS_COMP_INJURY WHERE WORKERS_ID="+workerCode;
		result=getSqlModel().singleExecute(selQuery);
		
		data[0][23]=bean.getEmpHomeState();
		data[0][24]=bean.getCompanyWork();
		data[0][25]=bean.getDepartment();
		data[0][26]=bean.getExecutive();
		data[0][27]=bean.getManagerPhone();
		data[0][28]=bean.getManagerName();
		
		String query="INSERT INTO HRMS_D1_WRKRS_COMP_INJURY(WORKERS_ID,WORKERS_EMP_ID, WORKERS_NO_OF_DEPNDNCY, WORKERS_DOI, WORKERS_TOI, WORKERS_HRS_WRK_DOI, WORKERS_NWRK_HRS_FROM, WORKERS_NWRK_HRS_TO, WORKERS_DEK, WORKERS_INJURY_REPORTED_TO, WORKERS_TITLE, WORKERS_LOSS_WORKDAY, WORKERS_POSSIBLE_LENGTH, WORKERS_LOST_WORK_DATE, WORKERS_RETURN_TO_WORK, WORKERS_DOR, WORKERS_TOR, WORKERS_ADD_ACCIDENT, WORKERS_ADD_PHNO_LOCAL_D1, WORKERS_DESC_INJURY, WORKERS_PART_OF_BODY_AFFECT, WORKERS_NAME_ADD_PHYS, WORKERS_TYPE_TRETMENT, WORKERS_REASON,WORKERS_APPLICATION_DATE,WORKERS_STATUS,WORKERS_REPORTING_TO,COMMENTS,COMPLETED_BY,COMPLETED_ON,TRACKING_NO,WORKERS_HOME_STATE, WORKERS_COUNTRY, WORKERS_DEPT, WORKERS_EXECUTIVE, WORKERS_MANG_PHONE, WORKERS_MANG_NAME)"
					+" VALUES("+workerCode+",?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'HH24:MI'),?,?,?,?,?,?,?,SYSDATE,'"+status+"',"+first+",'"+comm+"',"+bean.getUserEmpId()+",SYSDATE,'"+bean.getTrackingNo()+"',?,?,?,?,?,?)  "	;
		if(result)
		result=getSqlModel().singleExecute(query,data);		
		return result;
	}

	public boolean delete(WorkersCompInjury bean) {
		boolean result=false;
		String selQuery="DELETE FROM HRMS_D1_WRKRS_COMP_INJURY WHERE WORKERS_ID="+bean.getWorkersCode();
		result=getSqlModel().singleExecute(selQuery);	
		if(result){
			selQuery="DELETE FROM HRMS_D1_WORKER_INJURY_PATH WHERE WORKERS_ID="+bean.getWorkersCode();
			result=getSqlModel().singleExecute(selQuery);
		}
		return result;
	}

	public boolean input(WorkersCompInjury bean, HttpServletRequest request) {
		
		/**
		 * DRAFT APPLICATION
		 */
		
		String query="SELECT NVL(TRACKING_NO,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(WORKERS_APPLICATION_DATE,'DD-MM-YYYY') "
					+"	,HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID,WORKERS_ID FROM HRMS_D1_WRKRS_COMP_INJURY "
					+"	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID) ";
		query += " WHERE HRMS_D1_WRKRS_COMP_INJURY.COMPLETED_BY=" + bean.getUserEmpId();
		
		/*if (bean.isGeneralFlag()) {
			
		}
		else{
			query += " WHERE 1=1 ";
		}*/
		
		String draft=query+" AND WORKERS_STATUS='D' ORDER BY WORKERS_ID DESC";
	
		
		Object[][]data=getSqlModel().getSingleResult(draft);
		
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean
				.getMyPage(), data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		
		
		
		
		
		if(data !=null && data.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
			.parseInt(String.valueOf(pageIndex[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(data[i][0]));
				bean1.setIttEmployee(String.valueOf(data[i][1]));
				bean1.setIttApplicationDate(String.valueOf(data[i][2]));
				bean1.setIttEmployeeId(String.valueOf(data[i][3]));
				bean1.setIttWorkersCode(String.valueOf(data[i][4]));
				list.add(bean1);
			}
			bean.setListDraft(list);
		}
		/**
		 * IN PROGRESS
		 */
		String progress=query+" AND WORKERS_STATUS='P'  ORDER BY WORKERS_ID DESC ";
		Object[][]objProgress=getSqlModel().getSingleResult(progress);
		String[]pageIndexRe=setData(bean,request,objProgress,"totalPageP","pageNoP",bean.getMyPage1(),"1");
		if(objProgress !=null && objProgress.length>0){
			
			
			
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexRe[0])); i < Integer
			.parseInt(String.valueOf(pageIndexRe[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(objProgress[i][0]));
				bean1.setIttEmployee(String.valueOf(objProgress[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objProgress[i][2]));
				bean1.setIttEmployeeId(String.valueOf(objProgress[i][3]));
				bean1.setIttWorkersCode(String.valueOf(objProgress[i][4]));
				list.add(bean1);
			}
			bean.setListInProgress(list);
		}
		/**
		 * APPROVED
		 */
		String approve=query+" AND WORKERS_STATUS='A'  ORDER BY WORKERS_ID DESC ";
		if(bean.getFlag().equals("AA")){
			Object[][]objApprove=getSqlModel().getSingleResult(approve);
			String[]pageIndexAppr=setData(bean,request,objApprove,"totalPage","pageNo",bean.getMyPage(),"");
		if(objApprove !=null && objApprove.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
			.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(objApprove[i][0]));
				bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objApprove[i][2]));
				bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
				bean1.setIttWorkersCode(String.valueOf(objApprove[i][4]));
				list.add(bean1);
			}
			bean.setListApprove(list);
		}
		}
		/**
		 * REJECT APPLICATION
		 */
		String reject=query+" AND WORKERS_STATUS='R'  ORDER BY WORKERS_ID DESC ";
		
		
		
		if(bean.getFlag().equals("RR")){
			Object[][]objReject=getSqlModel().getSingleResult(reject);
			String[]pageIndexR=setData(bean,request,objReject,"totalPage","pageNo",bean.getMyPage(),"");		
		if(objReject !=null && objReject.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexR[0])); i < Integer
			.parseInt(String.valueOf(pageIndexR[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(objReject[i][0]));
				bean1.setIttEmployee(String.valueOf(objReject[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objReject[i][2]));
				bean1.setIttEmployeeId(String.valueOf(objReject[i][3]));
				bean1.setIttWorkersCode(String.valueOf(objReject[i][4]));
				list.add(bean1);
			}
			bean.setListReject(list);
		}
	}
		
		/**
		 * RESUBMIT APPLICATION
		 */
		String resubmit=query+" AND WORKERS_STATUS='Z'  ORDER BY WORKERS_ID DESC ";
	if(bean.getFlag().equals("BB")){
			Object[][]objResubmitt=getSqlModel().getSingleResult(resubmit);
			String[]pageIndexR=setData(bean,request,objResubmitt,"totalPage","pageNo",bean.getMyPage(),"");		
		if(objResubmitt !=null && objResubmitt.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexR[0])); i < Integer
			.parseInt(String.valueOf(pageIndexR[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(objResubmitt[i][0]));
				bean1.setIttEmployee(String.valueOf(objResubmitt[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objResubmitt[i][2]));
				bean1.setIttEmployeeId(String.valueOf(objResubmitt[i][3]));
				bean1.setIttWorkersCode(String.valueOf(objResubmitt[i][4]));
				list.add(bean1);
			}
			bean.setListResubmit(list);
		}
	}
		
		
		
		/**
		 * SEND BACK APPLICATION
		 */
		String sendBack=query+" AND WORKERS_STATUS='B'  ORDER BY WORKERS_ID DESC ";
		Object[][]objSendBack=getSqlModel().getSingleResult(sendBack);
		if(objSendBack !=null && objSendBack.length>0){
			
			String[]pageIndexB=setData(bean,request,objSendBack,"totalPageB","pageNoB",bean.getMyPage2(),"2");	
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexB[0])); i < Integer
			.parseInt(String.valueOf(pageIndexB[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(objSendBack[i][0]));
				bean1.setIttEmployee(String.valueOf(objSendBack[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objSendBack[i][2]));
				bean1.setIttEmployeeId(String.valueOf(objSendBack[i][3]));
				bean1.setIttWorkersCode(String.valueOf(objSendBack[i][4]));
				list.add(bean1);
			}
			bean.setListSendBack(list);
		}
		return false;
	}

	public boolean editApplication(WorkersCompInjury bean) {
		/*String query = "   SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID,ADD_STATE,EMP_SSN,EMP_SIN,DEPT_NAME, "
			+"  RANK_NAME AS EXECUTIVE,REGION_NAME "
			+"  ,WORKERS_NO_OF_DEPNDNCY, WORKERS_DOI, WORKERS_TOI, WORKERS_HRS_WRK_DOI, WORKERS_NWRK_HRS_FROM, WORKERS_NWRK_HRS_TO, WORKERS_DEK, WORKERS_INJURY_REPORTED_TO, WORKERS_TITLE, WORKERS_LOSS_WORKDAY, WORKERS_POSSIBLE_LENGTH, WORKERS_LOST_WORK_DATE, WORKERS_RETURN_TO_WORK, WORKERS_DOR, WORKERS_TOR, WORKERS_ADD_ACCIDENT, WORKERS_ADD_PHNO_LOCAL_D1, WORKERS_DESC_INJURY, WORKERS_PART_OF_BODY_AFFECT, WORKERS_NAME_ADD_PHYS, WORKERS_TYPE_TRETMENT, WORKERS_REASON, WORKERS_APPLICATION_DATE   "
			+"   FROM HRMS_D1_WRKRS_COMP_INJURY "
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID) "
			+"  LEFT JOIN HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " 
			+"   LEFT JOIN HRMS_D1_REGION ON(HRMS_D1_REGION.REGION_ID=HRMS_EMP_OFFC.EMP_REGION_ID)"
			+"	LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND  HRMS_EMP_ADDRESS.ADD_TYPE='P') "
			+"   LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
			+"	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
			+"	WHERE WORKERS_ID= " +bean.getWorkersCode()
			+"   ORDER BY   WORKERS_ID  ";*/
		bean.setFlagResubmit("");
		String qq="SELECT WORKERS_STATUS FROM HRMS_D1_WRKRS_COMP_INJURY WHERE WORKERS_STATUS='P' AND WORKERS_REPORTING_TO=2 AND WORKERS_ID=" +bean.getWorkersCode();
		Object[][]obj=getSqlModel().getSingleResult(qq);
		if(obj !=null && obj.length>0){
			bean.setFlagResubmit("true");
		}
		
		
		String query1="SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,OFFC.EMP_ID,WORKERS_HOME_STATE,OFFC.EMP_SSN,OFFC.EMP_SIN,WORKERS_DEPT,   WORKERS_EXECUTIVE AS EXECUTIVE,REGION_NAME, WORKERS_MANG_NAME,WORKERS_MANG_PHONE "
			+"		,DECODE(EMP_MARITAL_STATUS,'M','Married','U','Unmarried','W','Widow','D','Divorce','A','Widower') "
			+"				 ,WORKERS_NO_OF_DEPNDNCY, TO_CHAR(WORKERS_DOI,'DD-MM-YYYY'), TO_CHAR(WORKERS_TOI,'HH24:MI'), TO_CHAR(WORKERS_HRS_WRK_DOI,'HH24:MI'), TO_CHAR(WORKERS_NWRK_HRS_FROM,'HH24:MI'), TO_CHAR(WORKERS_NWRK_HRS_TO,'HH24:MI'), TO_CHAR(WORKERS_DEK,'DD-MM-YYYY'), WORKERS_INJURY_REPORTED_TO, WORKERS_TITLE, WORKERS_LOSS_WORKDAY, WORKERS_POSSIBLE_LENGTH, TO_CHAR(WORKERS_LOST_WORK_DATE,'DD-MM-YYYY'), WORKERS_RETURN_TO_WORK, TO_CHAR(WORKERS_DOR,'DD-MM-YYYY'), TO_CHAR(WORKERS_TOR,'HH24:MI'), WORKERS_ADD_ACCIDENT, WORKERS_ADD_PHNO_LOCAL_D1, WORKERS_DESC_INJURY, WORKERS_PART_OF_BODY_AFFECT, WORKERS_NAME_ADD_PHYS, WORKERS_TYPE_TRETMENT, WORKERS_REASON, WORKERS_APPLICATION_DATE, WORKERS_ID	"	
			+"			,COMMENTS,WORKERS_STATUS,COMPLETED_BY.EMP_TOKEN||'-'||COMPLETED_BY.EMP_FNAME||' '||COMPLETED_BY.EMP_MNAME||' '||COMPLETED_BY.EMP_LNAME,TO_CHAR(COMPLETED_ON,'DD-MM-YYYY HH24:MI'),TRACKING_NO,WORKERS_COUNTRY	FROM HRMS_D1_WRKRS_COMP_INJURY  "
			+"				  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID)"
			+"				 LEFT JOIN HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=OFFC.EMP_ID)   "  
			+"				LEFT JOIN HRMS_D1_REGION ON(HRMS_D1_REGION.REGION_ID=OFFC.EMP_REGION_ID)	"
			+"				LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P')  "   
			+"				LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=OFFC.EMP_DEPT) 	"
			+"				LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=OFFC.EMP_RANK)"
			+"				LEFT JOIN HRMS_EMP_OFFC MGR ON(MGR.EMP_ID = OFFC.EMP_REPORTING_OFFICER)  "
			+"				LEFT JOIN HRMS_EMP_OFFC COMPLETED_BY ON(COMPLETED_BY.EMP_ID = HRMS_D1_WRKRS_COMP_INJURY.COMPLETED_BY)  "
			+"				LEFT JOIN HRMS_EMP_ADDRESS ADD2 ON(ADD2.EMP_ID=MGR.EMP_ID AND  ADD2.ADD_TYPE='P')" +
			"  	WHERE WORKERS_ID= " +bean.getWorkersCode()+" ";
		
		
		Object[][]data=getSqlModel().getSingleResult(query1);
		if(data !=null && data.length>0){
			bean.setEmpToken(checkNull(String.valueOf(data[0][0])));
			bean.setEmpName(checkNull(String.valueOf(data[0][1])));
			bean.setEmpId(checkNull(String.valueOf(data[0][2])));
			bean.setEmpHomeState(checkNull(String.valueOf(data[0][3])));
			bean.setEmpSSN(checkNull(String.valueOf(data[0][4])));
			bean.setSocialInsNo(checkNull(String.valueOf(data[0][5])));
			bean.setDepartment(checkNull(String.valueOf(data[0][6])));
			bean.setExecutive(checkNull(String.valueOf(data[0][7])));
			bean.setRegion(checkNull(String.valueOf(data[0][8])));
			bean.setManagerName(checkNull(String.valueOf(data[0][9])));
			bean.setManagerPhone(checkNull(String.valueOf(data[0][10])));
			bean.setMaritalStatus(checkNull(String.valueOf(data[0][11])));
			
			bean.setNoOfDependancy(checkNull(String.valueOf(data[0][12])));
			bean.setDoi(checkNull(String.valueOf(data[0][13])));
			bean.setToi(checkNull(String.valueOf(data[0][14])));
			bean.setHrsWorkedDate(checkNull(String.valueOf(data[0][15])));
			
			
			bean.setNormalWorkingHrsFrom(checkNull(String.valueOf(data[0][16])));
			bean.setNormalWorkingHrsTo(checkNull(String.valueOf(data[0][17])));
			bean.setDke(checkNull(String.valueOf(data[0][18])));
			bean.setInjuryReportedName(checkNull(String.valueOf(data[0][19])));
			bean.setTitle(checkNull(String.valueOf(data[0][20])));			
			bean.setIncidentResult(checkNull(String.valueOf(data[0][21])));
			bean.setProbableLengthDisability(checkNull(String.valueOf(data[0][22])));			
			bean.setLostWorkDayDate(checkNull(String.valueOf(data[0][23])));			
			bean.setInjuredReturnWork(checkNull(String.valueOf(data[0][24])));			
			bean.setDor(checkNull(String.valueOf(data[0][25])));
			bean.setTor(checkNull(String.valueOf(data[0][26])));			
			bean.setAddressAccident(checkNull(String.valueOf(data[0][27])));
			bean.setAddressPhone(checkNull(String.valueOf(data[0][28])));
			bean.setDescribeInjury(checkNull(String.valueOf(data[0][29])));
			bean.setPartOfBodyAffected(checkNull(String.valueOf(data[0][30])));
			
			bean.setNameAddressPhy(checkNull(String.valueOf(data[0][31])));
			bean.setTypeOfTretment(checkNull(String.valueOf(data[0][32])));
			bean.setReasonToDoubt(checkNull(String.valueOf(data[0][33])));
			bean.setWorkersCode(checkNull(String.valueOf(data[0][35])));
			bean.setComments(checkNull(String.valueOf(data[0][36])));
			if(checkNull(String.valueOf(data[0][37])).equals("B")){
			bean.setFlagSendBack("BB");
			}
			bean.setCompletedBy(checkNull(String.valueOf(data[0][38])));
			bean.setCompletedOn(checkNull(String.valueOf(data[0][39])));
			bean.setTrackingNo(checkNull(String.valueOf(data[0][40])));
			bean.setCompanyWork(checkNull(String.valueOf(data[0][41])));
		}
		
		return false;
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public String checkReporting(WorkersCompInjury bean) {
			String rr="";
		String query="SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H') ";
			Object[][]data=getSqlModel().getSingleResult(query);
			if(data !=null && data.length>0){
				bean.setReportingOfficer(String.valueOf(data[0][0]));
			}
			else{
				rr="Reporting structure not define";
			}
			/*
			 * date of return should be greater than date of injury
			 */
			Utility utiliy = new Utility();
			int chkDate1 = utiliy.checkDate(bean.getDoi(), bean.getDor());
			if(chkDate1==0){
				int toi=TimeUtility.getMinute(bean.getToi());
				int tor=TimeUtility.getMinute(bean.getTor());
				if(toi>tor){
					rr="Date returned time should be greater than time of injury";
				}
			}
		return rr;
	}
	public String[] setData(WorkersCompInjury bean, HttpServletRequest request,Object[][]data,String totalPage,String pageNo,String page,String type){
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(page, data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute(totalPage, Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute(pageNo, Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1")){
			bean.setMyPage("1");
		if(type.equals("1")){
			bean.setMyPage1("1");
		}
		if(type.equals("2")){
			bean.setMyPage2("1");
		}
		}
		return pageIndex;
	}
	public boolean check(WorkersCompInjury bean) {
		String aa="SELECT WORKERS_STATUS FROM HRMS_D1_WRKRS_COMP_INJURY WHERE WORKERS_ID="+bean.getWorkersCode()+" AND WORKERS_STATUS='P'";
		Object[][]data=getSqlModel().getSingleResult(aa);
		boolean flag=false;
		if(data !=null && data.length>0){
			flag=true;
			return flag;
		}
		return flag;
	}

	public boolean resubmit(WorkersCompInjury bean, String status) {
		Object[][]data=new Object[1][6];
		data[0][0]=status;
		data[0][1]="2";
		data[0][2]=bean.getInjuredReturnWork();
		
		data[0][3]=bean.getDor();
		data[0][4]=bean.getTor();
		data[0][5]=bean.getWorkersCode();
		
		
		
		
		String qq="UPDATE HRMS_D1_WRKRS_COMP_INJURY SET WORKERS_STATUS=?,WORKERS_REPORTING_TO=?,WORKERS_RETURN_TO_WORK=?,WORKERS_DOR=TO_DATE(?,'DD-MM-YYYY'),WORKERS_TOR=TO_DATE(?,'HH24:MI') WHERE WORKERS_ID=?";
		return getSqlModel().singleExecute(qq,data);
		//return true;
	}
	
	public Object[][] getReporting() {
	String query="SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H') AND APP_SETTINGS_EMP_ID IS NOT NULL	 ";
		Object[][]data=getSqlModel().getSingleResult(query);
		return data;
		}
	/**
	 * METHOD TO GET HR GROUP EMAIL IDS
	 * @param bean
	 * @return
	 */
	public Object[][] getHRGroupEMail() {
		String query="SELECT APP_EMAIL_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H') AND APP_EMAIL_ID IS NOT NULL	 ";
			Object[][]data=getSqlModel().getSingleResult(query);
			return data;
			}
	
	
	public String setApproverComments(WorkersCompInjury bean){		
		String qq="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,WORKERS_COMMENTS,TO_CHAR(WORKERS_DATE,'DD-MM-YYYY'),DECODE(WORKERS_STATUS,'A','Approved','R','Reject','B','Send Back','P','Pending','Z','Pending for Resubmit') FROM HRMS_D1_WORKER_INJURY_PATH " 
					+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_D1_WORKER_INJURY_PATH.WORKERS_APPROVER=HRMS_EMP_OFFC.EMP_ID) "
					+"	WHERE HRMS_D1_WORKER_INJURY_PATH .WORKERS_ID="+bean.getWorkersCode()+" ORDER BY WORKERS_PATH_ID";	
		Object[][]data=getSqlModel().getSingleResult(qq);
		ArrayList list =new ArrayList();
		if(data !=null && data.length>0){
			for (int i = 0; i < data.length; i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttApproverName(checkNull(String.valueOf(data[i][0])));
				bean1.setIttComments(checkNull(String.valueOf(data[i][1])));
				bean1.setIttApplicationDate(checkNull(String.valueOf(data[i][2])));
				bean1.setIttStatus(checkNull(String.valueOf(data[i][3])));				
				list.add(bean1);
			}	
			bean.setListComment(list);
		}		
		return "";
	}

	public void setCompletedBy(WorkersCompInjury bean) {
		String query="SELECT EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId();
		Object[][]obj=getSqlModel().getSingleResult(query);
		if(obj !=null && obj.length>0){
			bean.setCompletedBy(String.valueOf(obj[0][0]));
			bean.setCompletedOn(String.valueOf(obj[0][1]));
		}		
	}
	
}
