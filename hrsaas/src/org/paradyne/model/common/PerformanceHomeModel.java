package org.paradyne.model.common;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.common.HrmHome;
import org.paradyne.bean.common.MyPage;
import org.paradyne.bean.common.PerformanceAppr;
import org.paradyne.bean.common.RecruitmentHome;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import java.util.*;
/*
 * author:Pradeep S
 * Date:30-01-2008
 */

import javax.servlet.http.HttpServletRequest;

public class PerformanceHomeModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	public void getEDmp(PerformanceAppr pa){
		
		String query="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  FROM HRMS_EMP_OFFC WHERE EMP_ID < 50  ";
		Object[][] param=getSqlModel().getSingleResult(query);
		
		TreeMap tm=new TreeMap();
		for(int i=0;i<param.length;i++ ){
			
			tm.put(i,String.valueOf(param[i][0]));
			pa.setEmpMap(tm);
			
		}
		
	}
	
	public String[][] processGenInfo(Document document, HttpServletRequest request) throws Exception {
		
		System.out.println("in process1111111------------------------------->>");
		  String[][] link =null;
		  
	      List fonts = document.selectNodes("//MYPAGE/PERFORMANCE[@name='MYPERFORMANCE']/LINK");
	      link = new String[fonts.size()][2];
	      int count=0;
	      for (Iterator iter = fonts.iterator(); iter.hasNext();) {
	      	Element font = (Element) iter.next();
	      	System.out.println("in FOR1111111------------------------------->>"+font);
	      	logger.info("attribute name-------------------------------------------------"+font.attributeValue("empId"));
	      	String id = font.attributeValue("empId");
	      	String value = font.attributeValue("value");
	      	logger.info("attribute value------------------------------------------------"+font.attributeValue("value"));
	          System.out.println("found font: " + font.getData());
	          request.setAttribute("empId",id);
	          request.setAttribute("value",value);
	          link[count][1]=font.attributeValue("empId");
	          link[count][0]=font.attributeValue("value");
	          count++;
	      }
	      return link;
	}
	
public Object[][] getCenter(PerformanceAppr rh){
		
		
		String yr=" select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value=getSqlModel().getSingleResult(yr);
		
		
		String query=" select distinct  center_dept_id,center_name,sal_net_salary from hrms_salary_"+String.valueOf(value[0][0])+" inner join   hrms_center on  "
					+" hrms_salary_"+String.valueOf(value[0][0])+".sal_emp_center=hrms_center.center_id "
					+" inner join hrms_dept on(hrms_dept.dept_id=hrms_center.center_dept_id)"
					+" where rownum < 10 group by center_dept_id,center_name,sal_net_salary ";
		
		Object[][]ob=getSqlModel().getSingleResult(query);
		
	
		return ob;
		
		
		
	}
	
	
	public Object[][] getPayBill(PerformanceAppr rh){
		
		String yr=" select to_char(sysdate,'YYYY') from dual  ";
		Object[][] value=getSqlModel().getSingleResult(yr);
		
		String query=" select distinct paybill_group,sal_net_salary from hrms_salary_"+String.valueOf(value[0][0])
					+"	inner join hrms_paybill on (hrms_salary_"+String.valueOf(value[0][0])+".sal_emp_paybill=hrms_paybill.paybill_id) "
					+"	where paybill_group in(11089,111004,11106,1111008) and rownum < 15  group by paybill_group   ,sal_net_salary ";


		Object[][]ob=getSqlModel().getSingleResult(query);
		return ob;
		
	}

	public void getMyPerf(PerformanceAppr bean,HttpServletRequest request) {
		// TODO Auto-generated method stub
		// String query="SELECT nvl(APPR_SCORE,'0') FROM HRMS_APPRAISAL WHERE
		// APPR_EMP_ID ="+myPage.getUserEmpId()+" AND CONF_CODE = (SELECT
		// MAX(CONF_CODE) FROM HRMS_APPRCONFIG_PRD_HDR ) AND APPR_STATUS='A'";
		
		String query = "SELECT NVL(APPR_FINAL_SCORE,'0') FROM PAS_APPR_SCORE  WHERE EMP_ID = "+bean.getUserEmpId()
				+" AND APPR_ID = (SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR) ";

		String pref = "0.0";
		
		Object[][] data = getSqlModel().getSingleResult(query);
		Object[][] ratingData = getSqlModel().getSingleResult("SELECT APPR_SCORE_FROM,APPR_SCORE_TO,APPR_SCORE_DESCRIPTION FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID = (SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR)");
		if (data != null && data.length > 0) {
			pref = String.valueOf(data[0][0]);
		}
		request.setAttribute("finalScore", pref);
		request.setAttribute("ratingData", ratingData);
	}


	
	public Vector showDeptEffData()
	{
		try {
			String deptQuery = " SELECT DISTINCT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
							  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
							  +" ORDER BY DEPT_NAME ";
			
			Object deptId[][] = getSqlModel().getSingleResult(deptQuery);
			
			String apprCOdeQuery = " SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR ";
			
			Object apprCode [][] =getSqlModel().getSingleResult(apprCOdeQuery);
			
			//String ratingQuery = " SELECT RATING FROM HRMS_APPRAISAL_RATING ";
			
			String ratingQuery = " SELECT APPR_SCORE_VALUE FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID = "+apprCode[0][0]+" ORDER BY APPR_SCORE_ID ";//"+apprCode[0][0]+"
			
			Object rating[][] = getSqlModel().getSingleResult(ratingQuery);
			logger.info(" Rating-------------             ------- "+rating.length);
			Object deptEffcData[][] = new String[deptId.length][rating.length+1];
			String getDeptEffQuery = "";
			Object count[][] = null;
			Vector v = new Vector() ;
			for (int i = 0; i < deptId.length; i++) {
				deptEffcData[i][0] = String.valueOf(deptId[i][1]);
				for (int j = 0; j < rating.length; j++) {
					/*getDeptEffQuery = " SELECT COUNT(APPR_CODE) FROM HRMS_APPRAISAL  "
							+ " WHERE APPR_DEPT = "
							+ deptId[i][0]+" AND APPR_STATUS = 'A' "
							+ " AND APPR_SCORE_DESC = '"+String.valueOf(rating[j][0])+"'" ;*/
					
					getDeptEffQuery =" SELECT COUNT(PAS_APPR_SCORE.EMP_ID) FROM PAS_APPR_SCORE "
							+" INNER JOIN HRMS_EMP_OFFC ON (PAS_APPR_SCORE.EMP_ID = HRMS_EMP_OFFC.EMP_ID )"
							+" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
							+" WHERE APPR_ID = "+apprCode[0][0]+" AND"
							+" HRMS_EMP_OFFC.EMP_DEPT = "+deptId[i][0]+" AND "
							+" PAS_APPR_SCORE.APPR_FINAL_SCORE_VALUE LIKE '"+String.valueOf(rating[j][0])
							+"' AND APPR_SCORE_FINALIZE='Y'" ;                                       
					
					count = getSqlModel().getSingleResult(getDeptEffQuery);
					deptEffcData[i][j + 1] = String.valueOf(count[0][0]);
				}
			}
			logger.info(" Rating-------------             ------- "+deptEffcData.length);
			v.add(rating);
			v.add(deptEffcData);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
public  Object [][] getPhases(){
		
		String apprCOdeQuery = " SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR ";
		
		Object apprCode [][] =getSqlModel().getSingleResult(apprCOdeQuery);
		
		
		String phaseQuery = " SELECT APPR_PHASE_ID,APPR_PHASE_NAME,APPR_ID,APPR_IS_SELFPHASE FROM PAS_APPR_PHASE_CONFIG "
			+" WHERE APPR_ID = "+apprCode[0][0]
			+" AND APPR_PHASE_STATUS ='A'"
			+" ORDER BY APPR_PHASE_ORDER ";

		Object phaseCode [][] = getSqlModel().getSingleResult(phaseQuery);
		
		
		return phaseCode;

	}

public String[][] performancePageApprisalInfo(Object[][] phaseObj)
{
	try {
		String deptQuery = " SELECT DISTINCT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
			              +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						  +" ORDER BY DEPT_NAME ";
		
		Object[][] deptData = getSqlModel().getSingleResult(deptQuery);
		
					
		String resultData[][] = new String[deptData.length][phaseObj.length + 1];
		String previousCOunt= "";
		String apprCode=""+phaseObj[0][2];
		
		for (int i = 0; i < deptData.length; i++) {
			
			String empCountQuery = " SELECT COUNT(*) FROM PAS_APPR_ELIGIBLE_EMP "
					+ " INNER JOIN HRMS_EMP_OFFC ON (PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " WHERE APPR_EMP_STATUS = 'A' AND APPR_ID = "+apprCode+" AND EMP_DEPT IS NOT NULL AND DEPT_ID = " + String.valueOf(deptData[i][0]);
			
			Object[][] totalEmpDeptCount = getSqlModel().getSingleResult(empCountQuery);
			
							
			resultData[i][0] = String.valueOf(deptData[i][1]);
			
			for(int p=0;p<phaseObj.length;p++){
				 
				
				String phaseCheckQuery = " SELECT  COUNT(distinct(PAS_APPR_TRACKING.EMP_ID)) FROM PAS_APPR_TRACKING "
					 			 +" INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_TRACKING.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					 			 +" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					 			 +" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_TRACKING.PHASE_ID) "
					 			 +" WHERE PAS_APPR_TRACKING.PHASE_ID = "+phaseObj[p][0]+" AND PHASE_FORWARD = 'Y' AND APPR_ID = "+apprCode
					 			 +" AND EMP_DEPT IS NOT NULL AND DEPT_ID = " + String.valueOf(deptData[i][0]);                                                                                                        
				
				
				Object[][] countObj = getSqlModel().getSingleResult(phaseCheckQuery); 
				
				if(p == 0){
					resultData[i][1+p] = String.valueOf(countObj[0][0]) + " / "
									+ String.valueOf(totalEmpDeptCount[0][0]);
					previousCOunt=String.valueOf(countObj[0][0]);
				}
				else{
					resultData[i][1+p] = String.valueOf(countObj[0][0]) + " / "
					+ previousCOunt;
					
					previousCOunt = String.valueOf(countObj[0][0]);
					
				}
							
			}
			
		}
		
		
		return resultData;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	} //end of try catch block
	
}


public void getEmpPerf(PerformanceAppr bean,HttpServletRequest request,boolean onload) {
	// TODO Auto-generated method stub
	//String query="SELECT nvl(APPR_SCORE,'0') FROM HRMS_APPRAISAL WHERE  APPR_EMP_ID ="+myPage.getUserEmpId()+" AND  CONF_CODE = (SELECT MAX(CONF_CODE) FROM HRMS_APPRCONFIG_PRD_HDR ) AND APPR_STATUS='A'";
	String pref="";
	String empId=bean.getEmpId();
	String apprId=bean.getApprId();
	Object[][] ratingData =null;
	Object [][]minMaxRating=null;
	if(!onload){
		
	String query="SELECT NVL(APPR_FINAL_SCORE,'0') FROM PAS_APPR_SCORE  WHERE EMP_ID ="+empId+" AND APPR_ID="+apprId +" AND APPR_SCORE_FINALIZE ='Y' "; // AND APPR_ID = (SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR) ";
	
	Object [][]data=getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		pref=String.valueOf(data[0][0]);
	}
	
	ratingData = getSqlModel().getSingleResult("SELECT APPR_SCORE_FROM,APPR_SCORE_TO,APPR_SCORE_VALUE,APPR_SCORE_DESCRIPTION FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID="+apprId+" ORDER BY APPR_SCORE_FROM");
	
	}else{
		empId =bean.getUserEmpId();
		Object [][]empData =getSqlModel().getSingleResult("SELECT (EMP_FNAME||' '||EMP_MNAME|| ' ' ||EMP_LNAME) FROM HRMS_EMP_OFFC WHERE EMP_ID="+empId);
		String apprDataQuery ="SELECT PAS_APPR_ELIGIBLE_EMP.APPR_ID,APPR_CAL_CODE FROM PAS_APPR_ELIGIBLE_EMP"
							+" INNER JOIN PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_ELIGIBLE_EMP.APPR_ID) WHERE APPR_EMP_ID="+empId
							+" ORDER BY PAS_APPR_ELIGIBLE_EMP.APPR_ID DESC";
		
		Object apprData [][]=getSqlModel().getSingleResult(apprDataQuery);
		try{
		bean.setEmpId(empId);
		bean.setEmpName(String.valueOf(empData[0][0]));
		bean.setApprId(String.valueOf(apprData[0][0]));
		bean.setApprCode(String.valueOf(apprData[0][1]));
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		logger.info("getEmpnamme==="+bean.getEmpName());
		logger.info("getApprId==="+bean.getApprId());
		logger.info("getEmpId==="+bean.getEmpId());
		String query="SELECT NVL(APPR_FINAL_SCORE,'0') FROM PAS_APPR_SCORE  WHERE EMP_ID ="+empId+" AND APPR_ID="+bean.getApprId() +" AND APPR_SCORE_FINALIZE ='Y'";		 
		
		Object [][]data=getSqlModel().getSingleResult(query);
		
		if(data!=null && data.length>0){
			pref=String.valueOf(data[0][0]);
		}
		
		
		
		ratingData = getSqlModel().getSingleResult("SELECT APPR_SCORE_FROM,APPR_SCORE_TO,APPR_SCORE_VALUE,APPR_SCORE_DESCRIPTION FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID="+bean.getApprId()+" ORDER BY APPR_SCORE_FROM");
	}
	minMaxRating=getSqlModel().getSingleResult("SELECT  NVL(APPR_MAX_RATING,0),NVL(APPR_MIN_RATING,0) FROM PAS_APPR_QUESTION_RATING_HDR WHERE APPR_ID="+bean.getApprId());
	request.setAttribute("finalScore", pref);
	request.setAttribute("ratingData", ratingData);
	request.setAttribute("minMaxRating", minMaxRating);
}

public String getPendingAppraisalXML(Object [][]phaseObj){
	
	String deptQuery = " SELECT DISTINCT DEPT_ID,NVL(DEPT_ABBR,DEPT_NAME) FROM HRMS_DEPT "
        +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
        //+" WHERE DEPT_ID NOT IN(2,3,4)"
        	//+" WHERE DEPT_ID >10"
		  +" ORDER BY NVL(DEPT_ABBR,DEPT_NAME) ";

Object[][] deptData = getSqlModel().getSingleResult(deptQuery);
Object[][] deptDataTemp = new Object[deptData.length][phaseObj.length];

String strXML = "";
String apprCode="";
try{
	apprCode=""+phaseObj[0][2];
}catch (Exception e) {
	// TODO: handle exception
}

if(deptData !=null || deptData.length>0){
	  strXML = strXML + "  <categories>"; 
  for (int k = 0; k < deptData.length; k++) {
	   //Convert data to XML and append
	
    strXML = strXML + "<category label='"+String.valueOf(deptData[k][1]).replace("&", "\\&")+"'/>";
 }
  strXML = strXML + "  </categories>";
}

for (int i = 0; i < deptData.length; i++) {
	String empCountQuery = " SELECT COUNT(*) FROM PAS_APPR_ELIGIBLE_EMP "
		+ " INNER JOIN HRMS_EMP_OFFC ON (PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
		+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
		+ " WHERE APPR_EMP_STATUS = 'A' AND APPR_ID = "+apprCode+" AND EMP_DEPT IS NOT NULL AND DEPT_ID = " + String.valueOf(deptData[i][0]);

	Object[][] totalEmpDeptCount = getSqlModel().getSingleResult(empCountQuery);
	double previousCount =0;
	for (int p = 0; p < phaseObj.length; p++) {
	String phaseCheckQuery ="";
	if(String.valueOf(phaseObj[p][3]).equals("Y")){
		phaseCheckQuery = " SELECT COUNT(*) FROM PAS_APPR_ELIGIBLE_EMP "
				+ " INNER JOIN HRMS_EMP_OFFC ON (PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE APPR_EMP_STATUS = 'A' AND APPR_ID = "+apprCode+" AND EMP_DEPT IS NOT NULL AND DEPT_ID = " + String.valueOf(deptData[i][0])
				+ " AND APPR_EMP_ID IN(SELECT EMP_ID FROM PAS_APPR_TRACKING WHERE PHASE_ID ="+phaseObj[p][0]+" AND PHASE_ISCOMPLETE='Y' AND APPR_ID = "+apprCode+") ";
	}else{
	phaseCheckQuery = " SELECT  COUNT(distinct(PAS_APPR_TRACKING.EMP_ID)) FROM PAS_APPR_TRACKING "
 			 +" INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_TRACKING.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
 			 +" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
 			 +" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_TRACKING.PHASE_ID) "
 			 +" WHERE PAS_APPR_TRACKING.PHASE_ID = "+phaseObj[p][0]+" AND PHASE_ISCOMPLETE = 'Y' AND APPR_ID = "+apprCode
 			 +" AND EMP_DEPT IS NOT NULL AND DEPT_ID = " + String.valueOf(deptData[i][0]);                                                                                                        
	}
	Object[][] countObj = getSqlModel().getSingleResult(phaseCheckQuery); 
	if(countObj !=null){
		if(p==0){
			deptDataTemp [i][p] =Double.parseDouble(String.valueOf(totalEmpDeptCount[0][0]))-Double.parseDouble(String.valueOf(countObj[0][0]));
			previousCount =Double.parseDouble(String.valueOf(countObj[0][0]));
		}else{
			
				deptDataTemp [i][p] =previousCount-Double.parseDouble(String.valueOf(countObj[0][0]));
				previousCount =Double.parseDouble(String.valueOf(countObj[0][0]));
			
		}
	}
	else{
			deptDataTemp [i][p] ="0";
			previousCount =0;
	}
	}
}
/*for (int i = 0; i < deptDataTemp.length; i++) {
	for (int j = 0; j < deptDataTemp[0].length; j++) {
		logger.info("deptDataTemp["+i+"]["+j+"]==="+deptDataTemp[i][j]);
	}
}*/
for(int p=0;p<phaseObj.length;p++){
	strXML = strXML +"<dataset seriesName='"+String.valueOf(phaseObj[p][1])+"' showValues='0'>";
	for (int i = 0; i < deptDataTemp.length; i++) {
			strXML = strXML +"<set value='"+String.valueOf(deptDataTemp[i][p])+"'/> ";
		
	}
	strXML = strXML + "</dataset>";
}

logger.info("strXML in model="+strXML);

return strXML;
}


public String getDeptPerformanceXML(){
	
	String deptQuery = " SELECT EMP_DEPT,NVL(DEPT_ABBR,DEPT_NAME),NVL(AVG(APPR_SCORE),0) FROM PAS_APPR_SCORE "
					+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=PAS_APPR_SCORE.EMP_ID)"
					+"	INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=EMP_DEPT)"
					+"	WHERE APPR_ID=(SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR)"
					+"	GROUP BY EMP_DEPT,NVL(DEPT_ABBR,DEPT_NAME) ";

Object[][] deptData = getSqlModel().getSingleResult(deptQuery);

	String organizationQuery = " SELECT NVL(AVG(APPR_SCORE),0) FROM PAS_APPR_SCORE "
					+"	WHERE APPR_ID=(SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR)";
			
	Object[][] organizationData = getSqlModel().getSingleResult(organizationQuery);
String strXML = "";
if(deptData !=null || deptData.length>0){
  for (int k = 0; k < deptData.length; k++) {
	   //Convert data to XML and append
	
    strXML = strXML + "<set label='"+String.valueOf(deptData[k][1]).replace("&", "\\&")+"' showValue='1' value='"+String.valueOf(deptData[k][2]).replace("&", "\\&")+"' />";
 }
 

strXML = strXML + " <trendLines>"
				+ " <line startValue='"+Utility.twoDecimals(String.valueOf(organizationData[0][0]))+"' color='FF0000' showValue='1' isTrendZone ='0' toolText='Average,"+String.valueOf(organizationData[0][0])+"' displayvalue='Organizational Average' />"
				+ " </trendLines>";
}
return strXML;

}


//Method added by Prajakta B
/** @author Prajakta.Bhandare
 * Method to get menu list on main page
 */
public void getPasMenuList(HttpServletRequest request,
		PerformanceAppr bean, String menuType){

	try {

		if (menuType == null) {
			menuType = "MN";
		}

		String groupQuery = " select DISTINCT MENU_GROUP,MENU_GROUP_ORDER, MENU_IMAGE from HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and HRMS_PROFILE_DTL.PROFILE_CODE IN"
				+ bean.getUserProfileId()
				+ ")"
				+ " WHERE MENU_TYPE='"
				+ menuType
				+ "' AND MENU_ISRELEASED='Y' "
				+ " ORDER BY MENU_GROUP_ORDER ";
		Object groupData[][] = getSqlModel().getSingleResult(groupQuery);

		String query = " SELECT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
				+ "	OR PROFILE_GENERAL_FLAG ='Y'))"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE ="+bean.getUserProfileId()+" and MENU_TYPE='" + menuType + "' "
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
//Ends Method added by Prajakta B
}
