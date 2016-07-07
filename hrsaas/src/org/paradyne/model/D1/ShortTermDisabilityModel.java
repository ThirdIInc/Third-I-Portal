package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ShortTermDisabilityBean;
import org.paradyne.bean.D1.ShortTermDisabilityBean;
import org.paradyne.bean.D1.WorkersCompInjury;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.common.ApplCodeTemplateModel;

public class ShortTermDisabilityModel extends ModelBase {

	public boolean draft(ShortTermDisabilityBean bean, String status) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Object[][]data=new Object[1][15];
		data[0][0]=bean.getEmployeeCode();
		data[0][1]=bean.getStdEffectiveDate();
		data[0][2]=bean.getStdEligibleDate();
		data[0][3]=bean.getActionSTO();
		data[0][4]=bean.getRegionSTO();
		data[0][5]=bean.getWorkRelatedHidden().equals("Yes")?"Y":"N";
		data[0][6]=bean.getDidEmployeereturnHidden().equals("Yes")?"Y":"N";
		
		data[0][7]=bean.getDateEmpReturn();	
		data[0][8]=bean.getActionRFD();
		data[0][9]=bean.getRegionRFD();
		data[0][10]=bean.getNoOfWorkingHrs();
		data[0][11]=bean.getDaysOfAbsence();
		
		data[0][12]=status;
		
		data[0][13]=bean.getEmployeeDeptNo();
		data[0][14]=bean.getExecutive();
		
		String shortTermCode="";
		if (bean.getShortTermCode().equals("")) {
			String selQuery = "SELECT NVL(MAX(SHORT_TERM_CODE),0)+1,TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_D1_SHORT_TERM_DISB ";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			shortTermCode = String.valueOf(selData[0][0]);
			bean.setShortTermCode(shortTermCode);
			
			/**
			 * SET TRACKING NO
			 */
			if(selData !=null && selData.length>0){			
				try {
					ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					String applnCode = model.generateApplicationCode(bean.getShortTermCode(), "D1-SHORTDIS", bean.getEmployeeCode(),String.valueOf(selData[0][1]));
					bean.setTrackingNo(checkNull(applnCode));
					model.terminate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				}	
			
			
		}
		String delQuery="DELETE FROM HRMS_D1_SHORT_TERM_DISB WHERE SHORT_TERM_CODE= "+bean.getShortTermCode();
		flag=getSqlModel().singleExecute(delQuery);
		for (int i = 0; i < data[0].length; i++) {
			System.out.println("data[0] :::::::::: "+data[0][i]);
		}
		
		String query="INSERT INTO HRMS_D1_SHORT_TERM_DISB(SHORT_TERM_CODE, SHORT_TERM_EMP_ID, SHORT_TERM_STD_EFF_DATE, SHORT_TERM_STD_ELG_DATE, ACTION_STO, REGION_STO, IS_THIS_WORK_RLT_INJURY, DID_EMP_RETURN, DATE_EMP_RETURN, ACTION_RFD, REASON_RFD,  NO_OF_WRK_HRS,DAY_OF_ABSENCE, STATUS, APPLICATIONB_DATE,SHORT_TERM_CREATED_BY,COMPLETED_BY,COMPLETED_ON,TRACKING_NO,SHORT_DEPT_NUMBER,SHORT_EXECUTIVE)" +
						"	VALUES("+bean.getShortTermCode()+",?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?" +
						",TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'HH24:MI'),?,?	,sysdate,'"+bean.getUserEmpId()+"','"+bean.getUserEmpId()+"',SYSDATE,'"+bean.getTrackingNo()+"',?,?)";
		if(flag){
			flag=getSqlModel().singleExecute(query,data);
		}
		
		return flag;
	}

	public boolean delete(ShortTermDisabilityBean bean) {
		boolean flag=false;
		String delQuery="DELETE FROM HRMS_D1_SHORT_TERM_DISB WHERE SHORT_TERM_CODE= "+bean.getShortTermCode();
		flag=getSqlModel().singleExecute(delQuery);
		return flag;
	}

	public void input(ShortTermDisabilityBean bean, HttpServletRequest request, String string) {
		
		String query="SELECT NVL(TRACKING_NO,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(APPLICATIONB_DATE,'DD-MM-YYYY') "
					+"	,HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_EMP_ID,SHORT_TERM_CODE FROM HRMS_D1_SHORT_TERM_DISB "
					+"	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_EMP_ID) ";
	
				query += " WHERE 1=1 ";
				
		
		boolean result=false;
		String draft=query+" AND STATUS='D' ";
		if(bean.getApptype().equals("")){
			draft+= " AND SHORT_TERM_CREATED_BY= "+bean.getUserEmpId();
			result=true;
		}
		else{
			result=getReportingSetting(bean.getUserEmpId());
		}
		draft = draft + " ORDER BY HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_CODE DESC  ";
		
		Object[][]data=getSqlModel().getSingleResult(draft);
		String[] pageIndex=null;
		 pageIndex = new org.paradyne.lib.Utility().doPaging(bean
				.getMyPage(), data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		/**appr
		 * CHECK CONDITION FOR APPROVAL
		 */
		
		
		
		bean.setListDraft(null);
		/**
		 * DRAFT APPLICATION
		 */
		bean.setHeaderName("Draft");
		if(data !=null && data.length>0&& result){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
			.parseInt(String.valueOf(pageIndex[1])); i++) {
				ShortTermDisabilityBean bean1=new ShortTermDisabilityBean();
				bean1.setIttEmployeeToken(String.valueOf(data[i][0]));
				bean1.setIttEmployee(String.valueOf(data[i][1]));
				bean1.setIttApplicationDate(String.valueOf(data[i][2]));
				bean1.setIttshortTermCode(String.valueOf(data[i][4]));
				bean1.setButtonName("Edit Application");
				list.add(bean1);
			}
			bean.setListDraft(list);
		}
		
		if(bean.getFlag().equals("SS")){
			bean.setHeaderName("Submitted Application");
			bean.setListDraft(null);
			result=false;
				String progress=query+" AND STATUS IN('P') ";
				if(bean.getApptype().equals("")){
					progress+= " AND SHORT_TERM_CREATED_BY= "+bean.getUserEmpId();
					result=true;
				}
				else{
					result=getReportingSetting(bean.getUserEmpId());
					System.out.println("result=false;result=false;result=false;result=false; :::11::::::   "+result);
				}
				
				progress = progress + " ORDER BY HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_CODE DESC  ";
				Object[][]objProgress=getSqlModel().getSingleResult(progress);
				 pageIndex = new org.paradyne.lib.Utility().doPaging(bean
							.getMyPage(), objProgress.length, 20);
				
				//String[]pageIndexRe=setData(bean,request,objProgress,"totalPage","pageNo",bean.getMyPage(),"");
				if(objProgress !=null && objProgress.length>0 && result){	
					ArrayList list =new ArrayList();
					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
						ShortTermDisabilityBean bean1=new ShortTermDisabilityBean();
						bean1.setIttEmployeeToken(String.valueOf(objProgress[i][0]));
						bean1.setIttEmployee(String.valueOf(objProgress[i][1]));
						bean1.setIttApplicationDate(String.valueOf(objProgress[i][2]));
						//bean1.setIttEmployeeId(String.valueOf(objProgress[i][3]));
						bean1.setIttshortTermCode(String.valueOf(objProgress[i][4]));
						bean1.setButtonName("Edit Application");
						list.add(bean1);
					}
					bean.setListDraft(list);
				}
		}	
		
		if(bean.getFlag().equals("RS")){	
			bean.setListDraft(null);
			result=false;
			bean.setHeaderName("ReSubmitted Application");
			String progress=query+" AND STATUS IN('A') ";
			if(bean.getApptype().equals("")){
				progress+= " AND SHORT_TERM_CREATED_BY= "+bean.getUserEmpId();
				result=true;
			}
			else{
				result=getReportingSetting(bean.getUserEmpId());
				System.out.println("result=false;result=false;result=false;result=false; :::::::::   "+result);
			}
			progress = progress + " ORDER BY HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_CODE DESC  ";
			Object[][]objProgress=getSqlModel().getSingleResult(progress);
			 pageIndex = new org.paradyne.lib.Utility().doPaging(bean
						.getMyPage(), objProgress.length, 20);
			//String[]pageIndexRe=setData(bean,request,objProgress,"totalPage","pageNo",bean.getMyPage(),"");
			if(objProgress !=null && objProgress.length>0 && result){	
				ArrayList list =new ArrayList();
				for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
				.parseInt(String.valueOf(pageIndex[1])); i++) {
					ShortTermDisabilityBean bean1=new ShortTermDisabilityBean();
					bean1.setIttEmployeeToken(String.valueOf(objProgress[i][0]));
					bean1.setIttEmployee(String.valueOf(objProgress[i][1]));
					bean1.setIttApplicationDate(String.valueOf(objProgress[i][2]));
					//bean1.setIttEmployeeId(String.valueOf(objProgress[i][3]));
					bean1.setIttshortTermCode(String.valueOf(objProgress[i][4]));
					bean1.setButtonName("View Application");
					list.add(bean1);
				}
				bean.setListDraft(list);
			}
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
		
		
		/*
		
		
		*//**
		 * IN PROGRESS
		 *//*
		String progress=query+" AND STATUS IN('L')  ";
		progress = progress + " ORDER BY HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_CODE DESC  ";
		Object[][]objProgress=getSqlModel().getSingleResult(progress);
		String[]pageIndexRe=setData(bean,request,objProgress,"totalPageP","pageNoP",bean.getMyPage1(),"1");
		if(objProgress !=null && objProgress.length>0){	
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexRe[0])); i < Integer
			.parseInt(String.valueOf(pageIndexRe[1])); i++) {
				ShortTermDisabilityBean bean1=new ShortTermDisabilityBean();
				bean1.setIttEmployeeToken(String.valueOf(objProgress[i][0]));
				bean1.setIttEmployee(String.valueOf(objProgress[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objProgress[i][2]));
				//bean1.setIttEmployeeId(String.valueOf(objProgress[i][3]));
				bean1.setIttshortTermCode(String.valueOf(objProgress[i][4]));
				list.add(bean1);
			}
			bean.setListInProgress(list);
		}
		
		*//**
		 * APPROVED PPLICATION
		 *//*
		String approve=query+" AND STATUS='P'  ";
		if(bean.getFlag().equals("PP")){
			approve = approve + " ORDER BY HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_CODE DESC  ";
			Object[][]objApprove=getSqlModel().getSingleResult(approve);
			String[]pageIndexAppr=setData(bean,request,objApprove,"totalPage","pageNo",bean.getMyPage(),"");
		if(objApprove !=null && objApprove.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
			.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
				ShortTermDisabilityBean bean1=new ShortTermDisabilityBean();
				bean1.setIttEmployeeToken(String.valueOf(objApprove[i][0]));
				bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objApprove[i][2]));
				//bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
				bean1.setIttshortTermCode(String.valueOf(objApprove[i][4]));
				list.add(bean1);
			}
			bean.setListApprove(list);
		}
		}
		
		*//**
		 * REJECT APPLICATION
		 *//*
		String reject=query+" AND STATUS='R'  ";
		
				if(bean.getFlag().equals("RR")){
					reject = reject + " ORDER BY HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_CODE DESC  ";
					Object[][]objReject=getSqlModel().getSingleResult(reject);
					String[]pageIndexR=setData(bean,request,objReject,"totalPage","pageNo",bean.getMyPage(),"");		
				if(objReject !=null && objReject.length>0){
					ArrayList list =new ArrayList();
					for (int i = Integer.parseInt(String.valueOf(pageIndexR[0])); i < Integer
					.parseInt(String.valueOf(pageIndexR[1])); i++) {
						ShortTermDisabilityBean bean1=new ShortTermDisabilityBean();
						bean1.setIttEmployeeToken(String.valueOf(objReject[i][0]));
						bean1.setIttEmployee(String.valueOf(objReject[i][1]));
						bean1.setIttApplicationDate(String.valueOf(objReject[i][2]));
						//bean1.setIttEmployeeId(String.valueOf(objReject[i][3]));
						bean1.setIttshortTermCode(String.valueOf(objReject[i][4]));
						list.add(bean1);
					}
					bean.setListReject(list);
				}
			}		
				
		*//**
		 * SEND BACK APPLICATION
		 *//*
		String sendBack=query+" AND STATUS='B'  ";
		sendBack = sendBack + " ORDER BY HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_CODE DESC  ";
		Object[][]objSendBack=getSqlModel().getSingleResult(sendBack);
		if(objSendBack !=null && objSendBack.length>0){			
			String[]pageIndexB=setData(bean,request,objSendBack,"totalPageB","pageNoB",bean.getMyPage2(),"2");	
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexB[0])); i < Integer
			.parseInt(String.valueOf(pageIndexB[1])); i++) {
				ShortTermDisabilityBean bean1=new ShortTermDisabilityBean();
				bean1.setIttEmployeeToken(String.valueOf(objSendBack[i][0]));
				bean1.setIttEmployee(String.valueOf(objSendBack[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objSendBack[i][2]));
				//bean1.setIttEmployeeId(String.valueOf(objSendBack[i][3]));
				bean1.setIttshortTermCode(String.valueOf(objSendBack[i][4]));
				list.add(bean1);
			}
			bean.setListSendBack(list);
		}	*/	
		
		
		
		
	}
	public String[] setData(ShortTermDisabilityBean bean, HttpServletRequest request,Object[][]data,String totalPage,String pageNo,String page,String type){
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

	public boolean editApplication(ShortTermDisabilityBean bean) {
			String query="SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,SHORT_DEPT_NUMBER,SHORT_EXECUTIVE, SHORT_TERM_EMP_ID, TO_CHAR(SHORT_TERM_STD_EFF_DATE,'DD-MM-YYYY'), TO_CHAR(SHORT_TERM_STD_ELG_DATE,'DD-MM-YYYY'), ACTION_STO, REGION_STO, DECODE(IS_THIS_WORK_RLT_INJURY,'N','No','Yes'),  DECODE(DID_EMP_RETURN,'N','No','Yes'), TO_CHAR(DATE_EMP_RETURN,'DD-MM-YYYY'), ACTION_RFD, REASON_RFD,  TO_CHAR(NO_OF_WRK_HRS,'HH24:MI'),DAY_OF_ABSENCE, STATUS, APPLICATIONB_DATE " +
					"  ,COMPLETED_BY.EMP_TOKEN||'-'||COMPLETED_BY.EMP_FNAME||' '||COMPLETED_BY.EMP_MNAME||' '||COMPLETED_BY.EMP_LNAME,TO_CHAR(COMPLETED_ON,'DD-MM-YYYY HH24:MI'),TRACKING_NO    FROM  HRMS_D1_SHORT_TERM_DISB "
						+"	INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_SHORT_TERM_DISB.SHORT_TERM_EMP_ID) "
						+"	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=OFFC.EMP_RANK)" +
						"  LEFT JOIN HRMS_EMP_OFFC COMPLETED_BY ON(COMPLETED_BY.EMP_ID = HRMS_D1_SHORT_TERM_DISB.COMPLETED_BY)    LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID=OFFC.EMP_DEPT_NO) WHERE SHORT_TERM_CODE= "+bean.getShortTermCode();
			Object[][]data=getSqlModel().getSingleResult(query);
			if(data !=null && data.length>0){
				bean.setEmployeeToken(checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
				bean.setEmployeeDeptNo(checkNull(String.valueOf(data[0][2])));
				bean.setExecutive(checkNull(String.valueOf(data[0][3])));
				bean.setEmployeeCode(checkNull(String.valueOf(data[0][4])));
				bean.setStdEffectiveDate(checkNull(String.valueOf(data[0][5])));
				bean.setStdEligibleDate(checkNull(String.valueOf(data[0][6])));
				bean.setActionSTO(checkNull(String.valueOf(data[0][7])));
				bean.setRegionSTO(checkNull(String.valueOf(data[0][8])));
				//
				bean.setWorkRelatedHidden(checkNull(String.valueOf(data[0][9])));
				
				bean.setDidEmployeereturnHidden(checkNull(String.valueOf(data[0][10])));
				bean.setDateEmpReturn(checkNull(String.valueOf(data[0][11])));
				bean.setActionRFD(checkNull(String.valueOf(data[0][12])));
				bean.setRegionRFD(checkNull(String.valueOf(data[0][13])));
				bean.setNoOfWorkingHrs(checkNull(String.valueOf(data[0][14])));
				bean.setDaysOfAbsence(checkNull(String.valueOf(data[0][15])));
				bean.setStatus(checkNull(String.valueOf(data[0][16])));
				bean.setCompletedBy(checkNull(String.valueOf(data[0][18])));
				bean.setCompletedOn(checkNull(String.valueOf(data[0][19])));
				bean.setTrackingNo(checkNull(String.valueOf(data[0][20])));
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
	public Object[][] getReporting() {
		String query="SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H')AND APP_SETTINGS_EMP_ID IS NOT NULL  ";
			Object[][]data=getSqlModel().getSingleResult(query);
			return data;
			}
	/**
	 * GET HR GROUP EMAIL ID
	 * @param code
	 * @return
	 */
	public Object[][] getHRGroupEmail() {
		String query="SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H')  AND APP_EMAIL_ID IS NOT NULL  ";
			Object[][]data=getSqlModel().getSingleResult(query);
			return data;
			}
	
	public boolean getReportingSetting(String code) {
		boolean flag=false;
		String query="SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H') AND APP_SETTINGS_EMP_ID IN("+code+") ";
			Object[][]data=getSqlModel().getSingleResult(query);
			if(data !=null && data.length>0){
				flag=true;
			}
			return flag;
		
	}
	public void setCompletedBy(ShortTermDisabilityBean bean) {
		String query="SELECT EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId();
		Object[][]obj=getSqlModel().getSingleResult(query);
		if(obj !=null && obj.length>0){
			bean.setCompletedBy(String.valueOf(obj[0][0]));
			bean.setCompletedOn(String.valueOf(obj[0][1]));
		}		
	}
	
}
