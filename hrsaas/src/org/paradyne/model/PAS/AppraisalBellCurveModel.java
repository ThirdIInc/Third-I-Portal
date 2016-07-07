package org.paradyne.model.PAS;

import java.util.ArrayList;

import org.paradyne.bean.PAS.AppraisalBellCurve;
import org.paradyne.lib.ModelBase;

public class AppraisalBellCurveModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppraisalBellCurveModel.class);

	public boolean getBellCurveData(AppraisalBellCurve bean) throws Exception {
		logger.info("In AppraisalBellCurveModel.getXAxisData() method Model");
		boolean flg = false;
		try {
			if (null != bean.getSAppId()) {
				Object[] sApprId = new Object[1];
				sApprId[0] = bean.getSAppId();
				
				/*String empQuery=" SELECT APPR_SCORE_VALUE,COUNT(APPR_FINAL_SCORE_VALUE),APPR_SCORE_FROM,APPR_SCORE_TO "
								+" FROM PAS_APPR_SCORE "
								+" RIGHT JOIN PAS_APPR_OVERALL_RATING ON(PAS_APPR_OVERALL_RATING.APPR_SCORE_VALUE = PAS_APPR_SCORE.APPR_FINAL_SCORE_VALUE "
								+" AND PAS_APPR_OVERALL_RATING.APPR_ID = PAS_APPR_SCORE.APPR_ID ) " 
								+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID "
								+" WHERE PAS_APPR_OVERALL_RATING.APPR_ID = "+bean.getSAppId();//+" AND APPR_SCORE_FINALIZE='Y' ";
			*/	
				String empQuery=" SELECT PAS_APPR_OVERALL_RATING.APPR_SCORE_VALUE,COUNT(APPR_FINAL_SCORE_VALUE),APPR_SCORE_FROM,APPR_SCORE_TO "  
								+"FROM PAS_APPR_SCORE "  
								+"INNER JOIN PAS_APPR_OVERALL_RATING ON(PAS_APPR_OVERALL_RATING.APPR_ID = PAS_APPR_SCORE.APPR_ID ) "+  
								"INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID "+  
								"WHERE PAS_APPR_OVERALL_RATING.APPR_ID = "+bean.getSAppId()+" AND ROUND(APPR_FINAL_SCORE) BETWEEN APPR_SCORE_FROM AND APPR_SCORE_TO ";//+" AND APPR_SCORE_FINALIZE='Y' ";

					
						if(!bean.getDivCode().equals(""))
							empQuery += " AND HRMS_EMP_OFFC.EMP_DIV = "+bean.getDivCode();
					
						if(!bean.getBranchCode().equals(""))
							empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+bean.getBranchCode();
					
						if(!bean.getDeptCode().equals(""))
							empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+bean.getDeptCode();
					
						if(!bean.getApprEmpCode().equals(""))
							empQuery += "  AND PAS_APPR_SCORE.EMP_ID in (SELECT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL "
									   +" INNER JOIN PAS_APPR_APPRAISER ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID and APPR_ID = "+bean.getSAppId()+" )"
									   +" WHERE APPR_APPRAISER_CODE IS NOT NULL and APPR_APPRAISER_CODE = "+bean.getApprEmpCode()+")";
						
								
					empQuery +=" GROUP BY  APPR_SCORE_VALUE,APPR_SCORE_ID,APPR_SCORE_FROM,APPR_SCORE_TO " 
								+" ORDER BY APPR_SCORE_ID ";
				
				Object[][] objData = getSqlModel().getSingleResult(empQuery);
				ArrayList<Object> lstData = new ArrayList<Object>();

				AppraisalBellCurve bean1 = null;
				if (objData != null && objData.length != 0) {
					for (int i = 0; i < objData.length; i++) {
						if (null != objData[i][0]) {
							bean1 = new AppraisalBellCurve();

							double dMidPoint = (Double.parseDouble(""
									+ objData[i][2]) + (Double.parseDouble(""
									+ objData[i][3]))) / 2;
							bean1.setSFinalScore(String.valueOf(dMidPoint)); 		 /* Final Score (X-Axis) */
							bean1.setSNoOfEmployess(String.valueOf(objData[i][1]));  /* No of Employee's (Y-Axis) */
							bean1.setSApprScoreValue(String.valueOf(objData[i][0])); /* Category */
							bean1.setSApprScoreFrom(String.valueOf(objData[i][2]));  /* Score From */
							bean1.setSApprScoreTo(String.valueOf(objData[i][3]));    /* Score To */
							lstData.add(bean1);
						}
					}
					bean.setLstBellCurveData(lstData);
					flg = true;
				} else {
					flg = false;
				}
			}
		} catch (Exception e) {
			logger.error("Error in AppraisalBellCurveModel.getXAxisData() method Model : " + e.getMessage());
		} finally {
			return flg;
		}
	}
}
