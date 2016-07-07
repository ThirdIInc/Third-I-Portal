package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.PAS.ApprFormStart;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;

public class ApprFormStartModel extends ModelBase {

	
	public void getAppraisalDueList(ApprFormStart bean){
		try{
			
			Object[][] apprData = getSqlModel().getSingleResult(getQuery(2),new Object[]{bean.getUserEmpId()});
			
			
			
			Object[][] resultData = getSqlModel().getSingleResult(getQuery(1),new Object[]{bean.getUserEmpId(),bean.getUserEmpId()});
			
			ArrayList<Object> obj = new ArrayList<Object>();
			
			if(resultData!=null && resultData.length!=0){
				
				for (int i = 0; i < resultData.length; i++) {
					ApprFormStart bean1 = new ApprFormStart();
					
					bean1.setApprCode(checkNull(String.valueOf(resultData[i][0])));
					bean1.setApprStartDate(checkNull(String.valueOf(resultData[i][1])));
					bean1.setApprEndDate(checkNull(String.valueOf(resultData[i][2])));
					bean1.setHapprId(checkNull(String.valueOf(resultData[i][3])));
					obj.add(bean1);

				}
								
				bean.setDataList(obj);
				}else{
					bean.setNoData("true");
					
				}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/* 
	 * method name : checkNull
	 * purpose     : check the string is null or not
	 * return type : String
	 * parameter   : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  // end of if
		else {
			return result;
		} // end of else
	}
	
	public void getAppraisalFinalizedList(ApprFormStart apprFormStart) {
		
		try {
			String finalizedQuery = "SELECT  DISTINCT APPR_CAL_CODE, HRMS_EMP_OFFC.EMP_ID,HRMS_PROMOTION.PROM_CODE, PAS_APPR_MGMNT_PANEL_REVIEW.TEMPLATE_CODE,EMP_FNAME||' '||EMP_LNAME,  "
					+ " TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY') FROM PAS_APPR_MGMNT_PANEL_REVIEW "
					+ " INNER JOIN PAS_APPR_CALENDAR  ON (PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) "
					+ " INNER JOIN PAS_APPR_PHASE_SCHEDULE  ON (PAS_APPR_PHASE_SCHEDULE.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID ) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN HRMS_PROMOTION ON (HRMS_PROMOTION.PROM_CODE=PAS_APPR_MGMNT_PANEL_REVIEW.EMP_PROM_CODE) "
					+ " WHERE PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID="+apprFormStart.getUserEmpId()+" AND ( PUBLISH_LETTER_FLAG='Y' AND HRMS_PROMOTION.LOCK_FLAG ='Y')";
			Object[][] finalizedObj = getSqlModel().getSingleResult(finalizedQuery);
			
			ArrayList<Object> finalObj = new ArrayList<Object>();
			
			if(finalizedObj!=null && finalizedObj.length > 0){
				for (int i = 0; i < finalizedObj.length; i++) {
					ApprFormStart bean1 = new ApprFormStart();
					
					bean1.setFinalApprCodeItt(checkNull(String.valueOf(finalizedObj[i][0])));
					bean1.setFinalApprEmpIdItt(checkNull(String.valueOf(finalizedObj[i][1])));
					bean1.setFinalApprPromoCodeItt(checkNull(String.valueOf(finalizedObj[i][2])));
					bean1.setFinalTemplateCodeItt(checkNull(String.valueOf(finalizedObj[i][3])));
					bean1.setFinalApprEmpNameItt(checkNull(String.valueOf(finalizedObj[i][4])));
					bean1.setFinalStartDateItt(String.valueOf(finalizedObj[i][5]));
					bean1.setFinalEndDateItt(String.valueOf(finalizedObj[i][6]));
					finalObj.add(bean1);
				}
				apprFormStart.setFinalizedList(finalObj);
				
			}else{
				apprFormStart.setFinalData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getTemplate(HttpServletRequest request,HttpServletResponse response,String tempCode,String promCode,String empId,String empName){
		String signAuthCode = "1";
		String finaldata = "";
		String secSignAuthCode = "2";
		try {
			Template template = new Template(tempCode);
			template.initiate(context, session);
			template.getTemplateQueries();
			/*try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(3);
				templateQuery1.setParameter(1, promCode);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
*/
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*try {
				TemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}*/

			String comlpeteTemplate = template.execute(request, response,
					"APPRAISAL_2010-11",true,empId,empName);

			try {
				comlpeteTemplate=comlpeteTemplate.replaceAll("&nbsp;", "");
				comlpeteTemplate=comlpeteTemplate.replaceAll("& ", "&amp;");
				finaldata = "<html>" + comlpeteTemplate + "</html>";
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finaldata;
	}

}
