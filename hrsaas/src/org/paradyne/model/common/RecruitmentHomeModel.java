package org.paradyne.model.common;

import org.paradyne.bean.common.HrmHome;
import org.paradyne.bean.common.RecruitmentHome;
import org.paradyne.lib.ModelBase;



import java.util.*;
/*
 * author:Pradeep Kumar Sahoo
 * Date:31-01-2008
 */

import javax.servlet.http.HttpServletRequest;

public class RecruitmentHomeModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(RecruitmentHomeModel.class);
	public Object[][] getDepartment(RecruitmentHome rh){
		String deptQuery=" select count(*),dept_name from  HRMS_EMP_OFFC "
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
							+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_CENTER.CENTER_DEPT_ID)"
							+" group by CENTER_DEPT_ID,dept_name ";
		Object[][] values=getSqlModel().getSingleResult(deptQuery);
		
		
		int sum=0;
		for(int i=0;i<values.length;i++) {
			sum=sum+Integer.parseInt(String.valueOf(values[i][0]));
					
		}
		rh.setTotal(String.valueOf(sum));
		return values;
		
	
	
	
	
	}
	
public Object[][] getCenter(RecruitmentHome rh){
		
		
		String yr=" select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value=getSqlModel().getSingleResult(yr);
		
		
		String query=" select distinct  center_dept_id,center_name,sal_net_salary from hrms_salary_"+String.valueOf(value[0][0])+" inner join   hrms_center on  "
					+" hrms_salary_"+String.valueOf(value[0][0])+".sal_emp_center=hrms_center.center_id "
					+" inner join hrms_dept on(hrms_dept.dept_id=hrms_center.center_dept_id)"
					+" where rownum < 10 group by center_dept_id,center_name,sal_net_salary ";
		
		Object[][]ob=getSqlModel().getSingleResult(query);
		
	
		return ob;
		
		
		
	}
	
	
	public Object[][] getPayBill(RecruitmentHome rh){
		
		String yr=" select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value=getSqlModel().getSingleResult(yr);
		
		String query=" select distinct paybill_group,sal_net_salary from hrms_salary_"+String.valueOf(value[0][0])
					+"	inner join hrms_paybill on (hrms_salary_"+String.valueOf(value[0][0])+".sal_emp_paybill=hrms_paybill.paybill_id) "
					+"	where paybill_group in(11089,111004,11106,1111008) and rownum < 15  group by paybill_group   ,sal_net_salary ";


		Object[][]ob=getSqlModel().getSingleResult(query);
		return ob;
		
	}

	public void getRecord(RecruitmentHome recHome, HttpServletRequest request) {
		
		Object[][]data = null;
		try {
			String query = "SELECT PUB_REQS_CODE,REQS_NAME,RANK_NAME,PUB_ASG_VAC,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),REQS_POSITION,VACAN_DTL_CODE "
						+" FROM HRMS_REC_VACPUB_RECDTL "
						+" INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
						+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
						+" LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
						+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
						+" WHERE PUB_REC_EMPID="+recHome.getUserEmpId()+" AND REQS_STATUS='O' AND REQS_APPROVAL_STATUS='A'";
			if(recHome.getUserProfileDivision() !=null && recHome.getUserProfileDivision().length()>0)
				query+= " AND REQS_DIVISION IN ("+recHome.getUserProfileDivision() +")"; 
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in getRecord query",e);
		}
		
		if(data ==null){
			
		}
		else if (data.length == 0) {
			
		}
		else{
			ArrayList<Object> tableList = new ArrayList<Object>();
			
			for (int i = 0; i < data.length; i++) {
				 RecruitmentHome bean = new RecruitmentHome();
				 bean.setRequisitionCode(String.valueOf(data[i][0]));
				 bean.setRequisitionName(String.valueOf(data[i][1]));
				 bean.setPosition(String.valueOf(data[i][2]));
				 bean.setNoOfVacancies(String.valueOf(data[i][3]));
				 if(String.valueOf(data[i][4]).equals("") || String.valueOf(data[i][4]).equals("null")){
					 bean.setRequiredDate(String.valueOf(""));
				 }else{
				    bean.setRequiredDate(String.valueOf(data[i][4]));
				 }
				 bean.setPositionId(String.valueOf(data[i][5]));
				 bean.setVacanDtlCode(String.valueOf(data[i][6]));
				 tableList.add(bean);
			}
			recHome.setList(tableList);//table list have been set
		}
		
	}
	/**
	 * following function is called when the employee Referral  button is clicked .
	 * This function will display the Requisition details and responsible person.
	 * @param bean
	 */	
	
public void getReferalprogramRecord(RecruitmentHome recHome, HttpServletRequest request) {
		
		Object[][]data = null;
		Object[][]Totvacancys = null;
		try {
			/*String query = "SELECT PUB_REQS_CODE,REQS_NAME,RANK_NAME,PUB_ASG_VAC,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),REQS_POSITION,VACAN_DTL_CODE "
						+" FROM HRMS_REC_VACPUB_RECDTL "
						+" INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
						+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
						+" LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
						+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
						+" WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS='A' AND  PUB_TO_REF='Y'";*/
			
		String query = "SELECT distinct PUB_REQS_CODE,REQS_NAME,RANK_NAME"
								+" FROM HRMS_REC_VACPUB_RECDTL "
								+" INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
								+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
								+" LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
								+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
								+" WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS='A' AND  PUB_TO_REF='Y'";
		if(recHome.getUserProfileDivision() !=null && recHome.getUserProfileDivision().length()>0)
			query+= "  AND REQS_DIVISION IN ("+recHome.getUserProfileDivision() +")"; 
			data = getSqlModel().getSingleResult(query);
		
		
		if(data ==null ||data.length == 0){
			
		}
		else{
			String vcl="0";
			for (int j = 0; j < data.length; j++) {
				vcl+=","+String.valueOf(data[j][0]);			
				
			}
			
			/*String totvacancys="SELECT sum(VACAN_NUMBERS)as tab1,REQS_CODE  FROM HRMS_REC_REQS_VACDTL" 
            +" where REQS_CODE  in ("+vcl+") group by REQS_CODE";*/
			
			String totvacancys = " SELECT SUM(VACAN_NUMBERS)AS TAB1, REQS_CODE  FROM HRMS_REC_REQS_VACDTL "
						+ " WHERE REQS_CODE  IN ("+vcl+") AND VACAN_STATUS = 'P' GROUP BY REQS_CODE,VACAN_STATUS";
			
			Totvacancys = getSqlModel().getSingleResult(totvacancys);
			
			ArrayList<Object> tableList = new ArrayList<Object>();
			
			for (int i = 0; i < data.length; i++) {
				 RecruitmentHome bean = new RecruitmentHome();
				
				 bean.setRequisitionCode(String.valueOf(data[i][0]));
				 bean.setRequisitionName(checkNull(String.valueOf(data[i][1])));
				 bean.setPosition(checkNull(String.valueOf(data[i][2])));
				 bean.setNoOfVacancies(String.valueOf(Totvacancys[i][0]));
				 
				/* if(String.valueOf(data[i][4]).equals("") || String.valueOf(data[i][4]).equals("null")){
					 bean.setRequiredDate(String.valueOf(""));
				 }else{
				    bean.setRequiredDate(String.valueOf(data[i][4]));
				 }
				 bean.setPositionId(String.valueOf(data[i][5]));
				 bean.setVacanDtlCode(String.valueOf(data[i][6]));*/
				 tableList.add(bean);
			}
			recHome.setReferalList(tableList);//table list have been set
		}
		} catch (Exception e) {
			logger.error("exception in getRecord query",e);
		}
		
	}

public String checkNull(String result) {
	if (result == null || result.equals("null")) {
		return "";
	}	//end of if
	else {
		return result;
	}	//end of else
}
/**
 * following function is called when the Recruitment button is clicked .
 * This function will display the list of candidates in the whose interviews has been scheduled 
 * and the person who will make the login will be able to see the details
 * @param bean
 */	
	
	public void getIntrvwrSchedule(RecruitmentHome bean){
		try{
			ArrayList<Object> list=new ArrayList<Object>();
			String query="SELECT INT_REQS_CODE,INT_CAND_CODE,INT_CODE,CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
						+" RANK_NAME,NVL(TO_CHAR(HRMS_REC_SCHINT_DTL.INT_DATE,'DD-MM-YYYY'),' '),INT_TIME"
						+" FROM HRMS_REC_SCHINT_DTL "
						+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_SCHINT_DTL.INT_REQS_CODE) "
						+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION) "
						+" INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHINT_DTL.INT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
						+" WHERE INT_CONDUCT_STATUS='N' AND INT_VIEWER_EMPID="+bean.getUserEmpId();
			Object[][] data = getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				for(int i=0;i<data.length;i++){
				RecruitmentHome bean1=new RecruitmentHome();
				 bean1.setIntvwrReqCode(String.valueOf(data[i][0]));
				 bean1.setIntrvwrCandCode(String.valueOf(data[i][1]));
				 bean1.setIntvwCode(String.valueOf(data[i][2]));
				 bean1.setIntrvwrCandName(String.valueOf(data[i][3]));
				 bean1.setIntrvwrPos(String.valueOf(data[i][4]));
					if(String.valueOf(data[i][5]).equals("") || String.valueOf(data[i][5]).equals("null")){
						 bean1.setIntrvwrDt("");
					}else{
					     bean1.setIntrvwrDt(String.valueOf(data[i][5]));
					}
				// bean1.setIntrvwrDt(String.valueOf(data[i][5]));
				 if(String.valueOf(data[i][6]).equals("null") || String.valueOf(data[i][6]).equals("")){
					 bean1.setIntvwrTime("");
				 }else{
					 bean1.setIntvwrTime(String.valueOf(data[i][6]));
				 }
				 list.add(bean1);
				}
				
			bean.setIntrvwrList(list);	
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void getInterviewSchedule(RecruitmentHome bean){
		
		String query="SELECT REQS_NAME,RANK_NAME, CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
					+" TO_CHAR(HRMS_REC_SCHINT_DTL.INT_DATE,'DD-MM-YYYY'), HRMS_REC_SCHINT_DTL.INT_TIME,"
					+" EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
					+" ,HRMS_REC_SCHINT.INT_CODE, HRMS_REC_SCHINT.INT_REQS_CODE, HRMS_REC_CAND_DATABANK.CAND_CODE "
					+" FROM  HRMS_REC_SCHINT "
					+" INNER JOIN HRMS_REC_SCHINT_DTL ON(HRMS_REC_SCHINT.INT_CODE=HRMS_REC_SCHINT_DTL.INT_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)"
					+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_SCHINT_DTL.INT_REQS_CODE)"
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+" INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHINT_DTL.INT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+" WHERE INT_CONDUCT_STATUS='N' AND INT_REC_EMPID="+bean.getUserEmpId();
		Object[][] data = getSqlModel().getSingleResult(query);
		
		ArrayList<Object> list=new ArrayList<Object>();
		if(data!=null && data.length >0){
			
			for(int i=0;i<data.length;i++){
				RecruitmentHome bean1=new RecruitmentHome();
				bean1.setReqsName(String.valueOf(data[i][0]));
				bean1.setPositionName(String.valueOf(data[i][1]));
				bean1.setCandName(String.valueOf(data[i][2]));
				if(String.valueOf(data[i][3]).equals("") || String.valueOf(data[i][3]).equals("null")){
					 bean1.setIntDtae("");
				}else{
				     bean1.setIntDtae(String.valueOf(data[i][3]));
				}
				 if(String.valueOf(data[i][4]).equals("null") || String.valueOf(data[i][4]).equals("")){
					 bean1.setIntTime("");
				 }else{
					 bean1.setIntTime(String.valueOf(data[i][4]));
				 }
			
				bean1.setInterviewerName(String.valueOf(data[i][5]));
				bean1.setReqsCode(String.valueOf(data[i][7]));
				bean1.setInterviewCode(String.valueOf(data[i][6]));
				bean1.setCandCode(String.valueOf(data[i][8]));
				list.add(bean1);
			}
			bean.setInterviewList(list);
			
			
		}
		
	}

	public void getquaAnalysisRecord(RecruitmentHome recHome, HttpServletRequest request) {
		Object[][]quaRecord = null;
		try {
			String query = "SELECT QUA_NAME,COUNT(QUA_MAST_CODE) FROM HRMS_EMP_QUA "
					+ " INNER JOIN HRMS_QUA ON (HRMS_QUA.QUA_ID = HRMS_EMP_QUA.QUA_MAST_CODE)  "
					+ " GROUP BY QUA_MAST_CODE,QUA_NAME ";
			quaRecord = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in qua record analysis",e);
		} //end of catch
		request.setAttribute("analysisData", quaRecord);
	} //end of getquaAnalysisRecord method

	public void getManpowerDistributionAnalysis(RecruitmentHome recHome,HttpServletRequest request) {
		String currentId="";
		String previousId="";
		String graph="0";
		String query = "";
		try {
			currentId = request.getParameter("currentId");
			previousId = request.getParameter("previousId");
			graph = request.getParameter("graph");
			logger.info("currentId==="+currentId);
			logger.info("previousId==="+previousId);
			logger.info("graph==="+graph);
		} catch (Exception e) {
			logger.error("request",e);
		} //end of catch
		
		Object[][]manpowerData = null;
		Object[][]axisData = new Object[1][1];
		if(graph==null || graph.equals("0") ){
			axisData[0][0] = "Division";
			request.setAttribute("axisName", axisData);
			query = "SELECT EMP_DIV,nvl(DIV_ABBR,div_name),COUNT(EMP_DIV) AS NO_OF_EMP,EMP_DIV,1 FROM HRMS_EMP_OFFC "
				+" inner join HRMS_DIVISION on (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				+" WHERE EMP_STATUS='S'"
				+" GROUP BY EMP_DIV,div_name,DIV_ABBR";
		}
		else if(graph.equals("1")){
			axisData[0][0] = "Branch";
			request.setAttribute("axisName", axisData);
			query = "SELECT EMP_CENTER,NVL(CENTER_ABBR,CENTER_NAME), COUNT(EMP_CENTER),EMP_DIV,2 "
			+" FROM HRMS_EMP_OFFC "
			+" inner join HRMS_CENTER  on (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
			+" WHERE EMP_DIV = "+previousId+" AND EMP_STATUS='S'"
			+" GROUP BY EMP_CENTER,CENTER_ABBR,CENTER_NAME,EMP_DIV";
		}
		else if(graph.equals("2")){
			axisData[0][0] = "Department";
			request.setAttribute("axisName", axisData);
			query = "SELECT EMP_DEPT,NVL(DEPT_ABBR,DEPT_NAME), COUNT(EMP_DEPT),EMP_DIV,3 "
			+" FROM HRMS_EMP_OFFC "
			+" inner join HRMS_DEPT on (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
			+" WHERE EMP_DIV = "+previousId+" AND EMP_CENTER = "+currentId+" AND EMP_STATUS='S' "
			+" GROUP BY EMP_DEPT,DEPT_ABBR,DEPT_NAME,EMP_DIV";
		}
		try {
			manpowerData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in get manpower data analysis",e);
		} //end of catch
		
		if(manpowerData !=null && manpowerData.length >0){
			request.setAttribute("distributionData", manpowerData);
		} //end of if
		
	} //end of getManpowerDistributionAnalysis method
	
//Added by priyanka
	
	public void getRecruitmentMenuList(HttpServletRequest request,
			RecruitmentHome bean, String menuType) {
		try {

			if (menuType == null) {
				menuType = "MN";
			}

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
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN("+bean.getMultipleProfileCode()+") and MENU_TYPE='" + menuType + "' "
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
	
	
	
	
	
	
	
} //end of class
