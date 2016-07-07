package org.paradyne.model.PAS.Competency;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.Competency.CompetencyMonitoring;
import org.paradyne.lib.ModelBase;

public class CompetencyMonitoringModel extends ModelBase {

	public void setCompApproverList(CompetencyMonitoring compMontbean,
			HttpServletRequest request) {
		String reportingType="";
		String sqlQuery="SELECT COMP_REPORTING FROM HRMS_COMP_CONFIG WHERE COMP_ID ="+compMontbean.getCompId();
		Object[][] dataObj = getSqlModel().getSingleResult(sqlQuery);
		if(dataObj!=null && dataObj.length>0)
		{
			reportingType=checkNull(String.valueOf(dataObj[0][0]));
		}
		if(reportingType.equals("goal"))
		{
			String compApproveQuery="SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME,'Self',DECODE(COMP_ASSESSMENT_STATUS,'N','Pending','Completed') AS STATUS FROM HRMS_COMP_EMP_ASSESSMENT_HDR "+ 
									"INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+ 
									"WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compMontbean.getAssessmentId()+" AND HRMS_EMP_OFFC.EMP_ID= "+compMontbean.getCompEmpId()+
									"UNION ALL "+
									"SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME, "+ 
									"DECODE(REPORTINGDTL_APPROVER_TYPE,'A','Approver','R','Reviewer'), "+
									"CASE  WHEN HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = HRMS_EMP_OFFC.EMP_ID THEN "+ 
									"DECODE(COMP_ASSESSMENT_STATUS,'A','Approved','P','Pending') "+
									"ELSE 'Completed' END AS STATUS FROM HRMS_REPTNG_STRUCTDTL_GOAL "+  
									"INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGDTL_EMP_ID) "+ 
									"INNER JOIN HRMS_REPTNG_STRUCTHDR_GOAL ON (HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGHDR_CODE=HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE) "+
									"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON (HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID=HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_EMP_ID AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compMontbean.getAssessmentId()+" ) "+
									"WHERE HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_EMP_ID="+compMontbean.getCompEmpId();
			Object[][] compApproverObj = getSqlModel().getSingleResult(compApproveQuery);
			//if(compApproverObj)
		}
		
	}

	/*
	 * method name : checkNull purpose : check the string is null or not return
	 * type : String parameter : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	/**
	 * @param compMontbean
	 * @param request
	 * @return
	 */
	public boolean sendbackCompetency(CompetencyMonitoring compMontbean,
			HttpServletRequest request) {
		boolean result=false;
		try {
			if (compMontbean.getCompEmpId().equals(
					compMontbean.getSendbackEmpId())) {
				String sendBackQuery = "UPDATE HRMS_COMP_ASSESMENT_HDR SET COMP_ASSESMENT_STATUS='N' WHERE COMP_EMP_ID = "
						+ compMontbean.getSendbackEmpId()
						+ " AND COMP_ASSESMENT_ID="
						+ compMontbean.getAssessmentId();
				result = getSqlModel().singleExecute(sendBackQuery);
				String deleteHDRQuery = "DELETE FROM HRMS_COMP_EMP_ASSESSMENT_HDR WHERE EMP_ID = "
						+ compMontbean.getCompEmpId()
						+ " AND COMP_ASSESSMENT_ID="
						+ compMontbean.getAssessmentId();
				result = getSqlModel().singleExecute(deleteHDRQuery);
			} else {
				String sendBackQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET COMP_ASSESSMENT_STATUS='P',COMP_ASSESSER_ID="
						+ compMontbean.getSendbackEmpId()
						+ " WHERE EMP_ID = "
						+ compMontbean.getCompEmpId()
						+ " AND COMP_ASSESSMENT_ID="
						+ compMontbean.getAssessmentId();
				result = getSqlModel().singleExecute(sendBackQuery);
				String sendBackUpdateQuery = "UPDATE HRMS_COMP_ASSESMENT_HDR SET COMP_ASSESMENT_STATUS='Y' WHERE COMP_EMP_ID = "
					+ compMontbean.getSendbackEmpId()
					+ " AND COMP_ASSESMENT_ID="
					+ compMontbean.getAssessmentId();
			result = getSqlModel().singleExecute(sendBackUpdateQuery);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
