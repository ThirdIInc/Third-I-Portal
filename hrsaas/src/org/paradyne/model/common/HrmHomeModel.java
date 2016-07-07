/**
 * 
 */
package org.paradyne.model.common;
import org.paradyne.bean.common.HrmHome;
import org.paradyne.bean.common.PayrollHome;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.common.MyPage;
import org.paradyne.bean.settlement.SettlementDetails;
import org.paradyne.lib.ModelBase;

import sun.util.logging.resources.logging;

/**
 * @author Pradeep Sahoo
 *
 */
public class HrmHomeModel extends ModelBase {
	
	public Object[][] getCategory(HrmHome hm){
		
		
		String catgQuery= "select count(*),catg_name from  HRMS_EMP_pers inner join hrms_catg on(HRMS_EMP_pers.emp_caste_catg=hrms_catg.catg_id) group by emp_caste_catg,catg_name ";		
		Object[][] ob = getSqlModel().getSingleResult(catgQuery);
		
		int sum=0;
		for(int i=0;i<ob.length;i++) {
			sum=sum+Integer.parseInt(String.valueOf(ob[i][0]));
					
		}
		hm.setTot(String.valueOf(sum));
		
		return ob;
		
		
	}
	
	public Object[][] getQualification(HrmHome hm){
		String qualQuery= "select count(*),qua_name from HRMS_EMP_QUA inner join hrms_qua on(HRMS_EMP_QUA.QUA_MAST_CODE=HRMS_QUA.QUA_ID) group by QUA_MAST_CODE,QUA_NAME ";
		Object[][] values = getSqlModel().getSingleResult(qualQuery);
		
		int sum=0;
		for(int i=0;i<values.length;i++) {
			sum=sum+Integer.parseInt(String.valueOf(values[i][0]));
					
		}
		hm.setTot1(String.valueOf(sum));
		return values;
		
		
		
	}
	
	public Object[][] getDepartment(HrmHome hm){
		/*String deptQuery=" select count(*),dept_name from  HRMS_EMP_OFFC "
						//	+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
							+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_CENTER.CENTER_DEPT_ID)"
							+" group by CENTER_DEPT_ID,dept_name ";
		*/
		
		
		String deptQuery="  select count(*),nvl(dept_abbr,dept_name) from  HRMS_EMP_OFFC  "
						+"   INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC .EMP_DEPT) "
						+"	  where EMP_STATUS ='S' group by HRMS_EMP_OFFC .EMP_DEPT,dept_abbr,dept_name ";
		Object[][] values=getSqlModel().getSingleResult(deptQuery);
		
		
		int sum=0;
		for(int i=0;i<values.length;i++) {
			sum=sum+Integer.parseInt(String.valueOf(values[i][0]));
					
		}
		hm.setTot2(String.valueOf(sum));
		return values;
		
		
	}
	
	
	
	
	public void getSuggestion(HrmHome hm){
		
		String query="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(SUGGESTION_DATE,'DD-MM-YYYY'),SUGGESTION_SUBJECT,SUGGESTION_CODE FROM HRMS_SUGGESTION " 
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SUGGESTION.SUGGETION_EMP_ID) WHERE SUGGESTION_FLAG='A' AND rownum < 16 ORDER BY SUGGESTION_DATE desc ";
		Object[][] values=getSqlModel().getSingleResult(query);
		
		ArrayList<HrmHome> hmSt=new ArrayList<HrmHome>();
		System.out.println("values.len"+values.length);
		for(int i=0;i< values.length;i++) {
			
		HrmHome bean=new HrmHome();
		bean.setName(String.valueOf(values[i][0]));
		if(values[i][1]==null){
		bean.setDate("");
		}
		else
			bean.setDate(String.valueOf(values[i][1]));
		if(values[i][2]==null){
			bean.setSubject("");
		}
		else
		bean.setSubject(String.valueOf(values[i][2]));
		bean.setSuggId(String.valueOf(values[i][3]));
		hmSt.add(bean);
		}
		
		hm.setList(hmSt); 
	}
	
	
	public Object[][] getCenter(HrmHome hrm){
		
		
		String yr=" select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value=getSqlModel().getSingleResult(yr);
		
		
		String query=" select distinct  center_dept_id,center_name,sal_net_salary from hrms_salary_"+String.valueOf(value[0][0])+" inner join   hrms_center on  "
					+" hrms_salary_"+String.valueOf(value[0][0])+".sal_emp_center=hrms_center.center_id "
					+" inner join hrms_dept on(hrms_dept.dept_id=hrms_center.center_dept_id)"
					+" where rownum < 10 group by center_dept_id,center_name,sal_net_salary ";
		
		Object[][]ob=getSqlModel().getSingleResult(query);
		
	
		return ob;
		
		
		
	}
	
	
	public Object[][] getPayBill(HrmHome hrm){
		
		String yr=" select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value=getSqlModel().getSingleResult(yr);
		
		String query=" select distinct paybill_group,sal_net_salary from hrms_salary_"+String.valueOf(value[0][0])
					+"	inner join hrms_paybill on (hrms_salary_"+String.valueOf(value[0][0])+".sal_emp_paybill=hrms_paybill.paybill_id) "
					+"	where paybill_group in(11089,111004,11106,1111008) and rownum < 15  group by paybill_group   ,sal_net_salary ";


		Object[][]ob=getSqlModel().getSingleResult(query);
		return ob;
		
	}
	
	
	
	 public void getIncrDue(HrmHome hrm) {
		 
		 
		 String query="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		 Object[][] value=getSqlModel().getSingleResult(query);
		 
		 String str= " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,CENTER_NAME,TO_CHAR(EMP_INCR_DATE+365,'DD-MM-YYYY') ,"
		 +" TO_DATE('"+String.valueOf(value[0][0])+"','DD-MM-YYYY') -(EMP_INCR_DATE+365)"
		 +" FROM HRMS_EMP_OFFC "
		 +" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
		 +" WHERE EMP_INCR_DATE+365 < TO_DATE('"+String.valueOf(value[0][0])+"','DD-MM-YYYY') ";
		 
		 Object[][] param=getSqlModel().getSingleResult(str);
		 
		 ArrayList<HrmHome> hmSt=new ArrayList<HrmHome>();
			System.out.println("values.len pradeep "+param.length);
			for(int i=0;i< param.length;i++) {
				
			HrmHome bean=new HrmHome();
			bean.setEmpName(String.valueOf(param[i][0]));
			bean.setBranch(String.valueOf(param[i][1]));
			bean.setDueDate(String.valueOf(param[i][2]));
			bean.setPendingDays(String.valueOf(param[i][3]));
			hmSt.add(bean);
			}
			
			hrm.setIncrList(hmSt); 
		 
		 
		 
		 
	 }

	public void showOnSubject(HrmHome hrm, String suggId) {
		// TODO Auto-generated method stub
		String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, CENTER_NAME, RANK_NAME, TO_CHAR(SUGGESTION_DATE,'DD-MM-YYYY'), SUGGESTION_SUBJECT, " 
					 + " SUGGESTION_IMPLEMENT, SUGGETION_IMPROVE, DEPT_NAME "
					 + " FROM HRMS_EMP_OFFC "
					 + " LEFT JOIN HRMS_SUGGESTION ON (HRMS_SUGGESTION.SUGGETION_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					 + " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				 	 + " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					 + " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "	
					 + " WHERE SUGGESTION_CODE = "+suggId;
		Object data[][] = getSqlModel().getSingleResult(query);
		
		
		if(data.length>0 ){
			hrm.setEmplName(String.valueOf(data[0][0]));
			hrm.setEmpbranch(String.valueOf(data[0][1]));
			hrm.setEmpdesg(String.valueOf(data[0][2]));
			hrm.setSuggDate(String.valueOf(data[0][3]));
			hrm.setSuggestion(String.valueOf(data[0][4]));
			hrm.setSuggimple(checkNull(String.valueOf(data[0][5])));
			hrm.setSuggimprove(checkNull(String.valueOf(data[0][6])));
			hrm.setEmpdept(String.valueOf(data[0][7]));
		}
		
	}
	public String checkNull(String result) { // check for null

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	public void showSettlementDue(HrmHome hrm) {
		// TODO Auto-generated method stub
		String query="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, CENTER_NAME,  TO_CHAR(SETTL_SEPRDT,'DD-MM-YYYY'),"		
					+ "round(to_number(to_date(sysdate,'DD-MM-YYYY')-to_date(SETTL_SEPRDT,'DD-MM-YYYY'))) "
					+ " FROM  HRMS_SETTL_HDR"					
					+ " LEFT JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO)" 
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID)" 
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)" 
					+ " WHERE  TO_DATE(SYSDATE,'DD-MM-YYYY') > TO_DATE(SETTL_SEPRDT,'DD-MM-YYYY')"
					+ " AND SETTL_LOCKFLAG='N'";				 	
					
		Object data[][] = getSqlModel().getSingleResult(query);		
		 ArrayList<Object> arrList=new ArrayList<Object>();
		if(data.length>0 ){
		for(int i=0;i< data.length;i++) {			
			HrmHome bean=new HrmHome();
			bean.setDueEmpName(String.valueOf(data[i][0]));
			bean.setDueEmpbranch(String.valueOf(data[i][1]));
			bean.setDueEmpDate(String.valueOf(data[i][2]));
			bean.setPendingSince(String.valueOf(data[i][3]));
			arrList.add(bean);
			}		
		hrm.setList(arrList);
		}
		
		
	}
	//Added by priyanka
	
	public void getHrmMenuList(HttpServletRequest request,
			HrmHome bean, String menuType) {
		try {

			if (menuType == null) {
				menuType = "MN";
			}
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String groupQuery = " select DISTINCT MENU_GROUP,MENU_GROUP_ORDER, MENU_IMAGE from HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and HRMS_PROFILE_DTL.PROFILE_CODE IN ("
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
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN ("+bean.getMultipleProfileCode()+") and MENU_TYPE='" + menuType + "' "
					+ " AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER,MENU_PLACEINMENU";

			/*
			 * String query = "SELECT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME,
			 * MENU_SERVICE_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP FROM
			 * HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE =
			 * HRMS_PROFILE_DTL.MENU_CODE and HRMS_PROFILE_DTL.PROFILE_CODE ='" +
			 * bean.getUserProfileId() + "') WHERE MENU_TYPE='" + menuType + "'
			 * AND MENU_ISRELEASED='Y' ORDER BY MENU_GROUP,MENU_PLACEINMENU";
			 */
			Object data[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("serviceMenulst", data);
			request.setAttribute("groupData", groupData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Ended by priyanka
	
	
	
	
	

	}
