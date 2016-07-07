package org.paradyne.model.common;
import org.paradyne.bean.common.AnalyticHome;
import org.paradyne.lib.ModelBase;
import java.util.*;
/**
 * Date:-01-02-2008
 * 
 * Modified by Lakkichand
 * Date :29-032008
 */
public class AnalyticHomeModel extends ModelBase {

	
	
	

	
	
public void getRecruitData(AnalyticHome ah){
		
		String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Recruitment' ";
		Object[][] values=getSqlModel().getSingleResult(query);		
		
		String query1=" SELECT MENU_LINK,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME) FROM HRMS_MENU " 
		+ " left join  HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE) "		
		+ " WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
		Object[][] param=getSqlModel().getSingleResult(query1);	
		
		ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
		
		for(int i=0;i<param.length;i++){
			AnalyticHome bean=new AnalyticHome();
			bean.setRecruitLink(String.valueOf(param[i][0]));
			bean.setRecruitName(String.valueOf(param[i][1]));
			
			list.add(bean);
			
						
		}
		
		ah.setRecruitList(list);
		
		
		
	}

public void getHrmData(AnalyticHome ah){
	
	String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='HRM' ";
	Object[][] values=getSqlModel().getSingleResult(query);		
	
	String query1=" SELECT MENU_LINK,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME) FROM HRMS_MENU " +
	 " left join  HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE) "		
	+ " WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
	Object[][] param=getSqlModel().getSingleResult(query1);	
	
	ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
	
	for(int i=0;i<param.length;i++){
		AnalyticHome bean=new AnalyticHome();
		bean.setHrmLink(String.valueOf(param[i][0]));
		bean.setHrmName(String.valueOf(param[i][1]));
		
		list.add(bean);
		
					
	}
	
	ah.setHrmList(list);
	
	
	
}
public void getPayrollData(AnalyticHome ah){
	
	String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Payroll' ";
	Object[][] values=getSqlModel().getSingleResult(query);		
	
	String query1=" SELECT MENU_LINK,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
	Object[][] param=getSqlModel().getSingleResult(query1);	
	
	ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
	
	for(int i=0;i<param.length;i++){
		AnalyticHome bean=new AnalyticHome();
		bean.setPayrollLink(String.valueOf(param[i][0]));
		bean.setPayrollName(String.valueOf(param[i][1]));
		
		list.add(bean);
		
					
	}
	
	ah.setPayrollList(list);
	
	
	
}
	
	public void getLeaveDetails(AnalyticHome ah){
		
		String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Leave Management' ";
		Object[][] values=getSqlModel().getSingleResult(query);		
		
		String query1=" SELECT MENU_LINK,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
		Object[][] param=getSqlModel().getSingleResult(query1);	
		
		ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
		
		for(int i=0;i<param.length;i++){
			AnalyticHome bean=new AnalyticHome();
			bean.setLeaveLink(String.valueOf(param[i][0]));
			bean.setLeaveName(String.valueOf(param[i][1]));
			
			list.add(bean);
			
						
		}
		
		ah.setLeaveList(list);
		
		
		
	}
	
public void getAttendanceDetails(AnalyticHome ah){
		
		String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Attendance Management' ";
		Object[][] values=getSqlModel().getSingleResult(query);		
		
		String query1=" SELECT MENU_LINK,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
		Object[][] param=getSqlModel().getSingleResult(query1);	
		
		ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
		
		for(int i=0;i<param.length;i++){
			AnalyticHome bean=new AnalyticHome();
			bean.setAttendanceLink(String.valueOf(param[i][0]));
			bean.setAttendanceName(String.valueOf(param[i][1]));
			
			list.add(bean);
						
		   }
		
		ah.setAttendanceList(list);
		
		
		
	}


	public void getApprData(AnalyticHome ah){
		
		String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Performance Appraisal' ";
		Object[][] values=getSqlModel().getSingleResult(query);		
		
		String query1=" SELECT MENU_LINK,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
		Object[][] param=getSqlModel().getSingleResult(query1);	
		
		ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
		
		for(int i=0;i<param.length;i++){
			AnalyticHome bean=new AnalyticHome();
			bean.setAppraisalLink(String.valueOf(param[i][0]));
			bean.setAppraisalName(String.valueOf(param[i][1]));
			
			list.add(bean);
						
		   }
		
		ah.setApprList(list);
		
		
	}
	
	
public void getTrainingData(AnalyticHome ah){
		
		String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Training & Development' ";
		Object[][] values=getSqlModel().getSingleResult(query);		
		Object[][] param = null ;
		
		if(values != null && values.length >0){
			String query1 = " SELECT MENU_LINK,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="
					+ String.valueOf(values[0][0]);
			param = getSqlModel().getSingleResult(query1);
		}
		ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
		if(param != null){
			for(int i=0;i<param.length;i++){
				AnalyticHome bean=new AnalyticHome();
				bean.setTrainingLink(String.valueOf(param[i][0]));
				bean.setTrainingName(String.valueOf(param[i][1]));
				
				list.add(bean);
							
			   }
		}
		ah.setTrList(list);
		
		
	}

public void getTaxData(AnalyticHome ah){
	
	String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Taxation' ";
	Object[][] values=getSqlModel().getSingleResult(query);		
	
	String query1=" SELECT MENU_LINK,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
	Object[][] param=getSqlModel().getSingleResult(query1);	
	
	ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
	
	for(int i=0;i<param.length;i++){
		AnalyticHome bean=new AnalyticHome();
		bean.setTaxLink(String.valueOf(param[i][0]));
		bean.setTaxName(String.valueOf(param[i][1]));
		
		list.add(bean);
					
	   }
	
	ah.setTaxList(list);
	
	
}


public void getAdminData(AnalyticHome ah){
	
	String query=" SELECT MENU_CODE FROM HRMS_MENU WHERE MENU_NAME='Administration' ";
	Object[][] values=getSqlModel().getSingleResult(query);		
	
	String query1=" SELECT MENU_LINK,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="+String.valueOf(values[0][0]);
	Object[][] param=getSqlModel().getSingleResult(query1);	
	
	ArrayList<AnalyticHome> list=new ArrayList<AnalyticHome>();
	
	for(int i=0;i<param.length;i++){
		AnalyticHome bean=new AnalyticHome();
		bean.setAdminLink(String.valueOf(param[i][0]));
		bean.setAdminName(String.valueOf(param[i][1]));
		
		list.add(bean);
					
	   }
	
	ah.setAdminList(list);
	
	
}

public String[][] getDashlets(AnalyticHome anHome) {
	// TODO Auto-generated method stub
	String query="SELECT HRMS_MENU.MENU_CODE,MENU_NAME FROM HRMS_MENU" 
			+ " INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE "
			+ "  and HRMS_PROFILE_DTL.PROFILE_CODE ='"
			+ anHome.getUserProfileId()+"'AND ( PROFILE_INSERT_FLAG='Y' "
			+" OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' " 
			+" OR PROFILE_GENERAL_FLAG ='Y'))"
			+" WHERE MENU_PARENT_CODE=414";
	String[][] strObj = null;
	Object[][] obj = getSqlModel().getSingleResult(query);
	if (obj != null && obj.length>0) {
		strObj = new String[obj.length][2];

		for (int i = 0; i < obj.length; i++) {
			strObj[i][0] = String.valueOf(obj[i][0]);
			strObj[i][1] = String.valueOf(obj[i][1]);

		}
	}

	return strObj;
	
	
}

public String[][] getIndividualLinks(AnalyticHome ah,String menuCode,String contextPath) {
	// TODO Auto-generated method stub
	System.out.println("llllllllllllllllllllll"+menuCode);
	String query="SELECT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME)," +
			" CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
			+ contextPath
			+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK" 
			+" FROM HRMS_MENU "
			+ " LEFT JOIN  HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE) "
			+ " INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE "
			+ "  and HRMS_PROFILE_DTL.PROFILE_CODE ='"
			+ ah.getUserProfileId()+"' AND ( PROFILE_INSERT_FLAG='Y' "
			+" OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' " 
			+" OR PROFILE_GENERAL_FLAG ='Y'))"
			+" WHERE HRMS_MENU.MENU_LINK IS NOT NULL "
			
			+ " START WITH HRMS_MENU.MENU_CODE = "
			+ menuCode
			+ " CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE "
			+ " ORDER BY HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_CODE"; 
	String[][] strObj = null;
	Object[][] obj = getSqlModel().getSingleResult(query);
	if (obj != null && obj.length>0) {
		strObj = new String[obj.length][3];

		for (int i = 0; i < obj.length; i++) {
			strObj[i][0] = String.valueOf(obj[i][0]);
			strObj[i][1] = String.valueOf(obj[i][1]);
			strObj[i][2] = String.valueOf(obj[i][2]);
		
		}
	}
	else
		strObj=new String[0][0];

	return strObj;
}



}
