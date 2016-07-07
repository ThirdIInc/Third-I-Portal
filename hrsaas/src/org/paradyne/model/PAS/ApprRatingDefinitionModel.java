package org.paradyne.model.PAS;

import org.paradyne.bean.PAS.ApprRatingDefinition;
import org.paradyne.lib.ModelBase;

public class ApprRatingDefinitionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ApprRatingDefinitionModel.class);

	public Object[][] getPhaseRatingFormula(ApprRatingDefinition apprRating) {
		// TODO Auto-generated method stub
		Object[][] data = null;
		Object[][]	total =null;

		try {
			String query = " SELECT APPR_PHASE_ID,APPR_PHASE_NAME,APPR_PHASE_ORDER   FROM "
					+ " PAS_APPR_PHASE_CONFIG WHERE APPR_ID ="
					+ apprRating.getApprId()
					+ " AND APPR_PHASE_STATUS ='A' "
					+ " ORDER BY APPR_PHASE_ORDER  ";

			data = getSqlModel().getSingleResult(query);
			
				total = new String[data.length][4];
			
			for (int i = 0; i < data.length; i++) {
				total[i][0] =checkNull(String.valueOf(data[i][0]));  
				total[i][1] =checkNull(String.valueOf(data[i][1])); 
				total[i][2] =checkNull(String.valueOf(data[i][2])); 
				total[i][3] = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public Object[][] getSectionWiseRatingFormula(ApprRatingDefinition apprRating) {
		// TODO Auto-generated method stub
		Object[][] data = null;
		Object[][]	total =null;

		try {
			String query = " SELECT APPR_SECTION_ID,APPR_SECTION_NAME,APPR_SECTION_ORDER FROM PAS_APPR_SECTION_HDR  "
				+" WHERE APPR_ID="+apprRating.getApprId()+" AND APPR_TEMPLATE_ID ="+apprRating.getTemplateCode()
				+" ORDER BY APPR_SECTION_ORDER " ;
			data = getSqlModel().getSingleResult(query);
				total = new String[data.length][3];
			for (int i = 0; i < data.length; i++) {
				total[i][0] =checkNull(String.valueOf(data[i][0]));  
				total[i][1] =checkNull(String.valueOf(data[i][1])); 
				total[i][2] =checkNull(String.valueOf(data[i][2])); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}


	public boolean saveFormula(ApprRatingDefinition apprRating,
			String[] phaseId, String[] phaseRatingFormula) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			
			String delQuery = " DELETE FROM PAS_RATING WHERE APPR_ID="+apprRating.getApprId()+" AND APPR_TEMPALTE_CODE = "+apprRating.getTemplateCode();
			
			getSqlModel().singleExecute(delQuery);
			
			Object[][] data = new Object[1][5];
			String insertQuery = " INSERT INTO PAS_RATING (APPR_ID, APPR_QUES_FORMULS, APPR_SCORE_FORMULA,APPR_TEMPALTE_CODE,APPR_PREV_RATING_FORMULA)"
					+ " VALUES(?,?,?,?,?)";

			data[0][0] = apprRating.getApprId();
			data[0][1] = apprRating.getQuesRatingFormula();
			data[0][2] = apprRating.getFinalRatingFormula();
			data[0][3] = apprRating.getTemplateCode();
			data[0][4] = apprRating.getPreRatingFormula();

			result = getSqlModel().singleExecute(insertQuery, data);

			if (result) {
				if (phaseId != null && phaseId.length > 0) {
					
					String delPhaseRecord ="  DELETE FROM PAS_RATING_PHASE WHERE APPR_ID="+apprRating.getApprId()+" AND APPR_TEMPLATE_CODE = "+apprRating.getTemplateCode();
					
					getSqlModel().singleExecute(delPhaseRecord);
					
					String insertPhaseRatingQuery = " INSERT INTO PAS_RATING_PHASE(APPR_ID, PHASE_ID, APPR_PHASE_SCORE_FORMULA,APPR_TEMPLATE_CODE) "
							+ " VALUES(?,?,?,?) ";

					Object[][] phaseDataObj = new Object[phaseId.length][4];

					for (int i = 0; i < phaseId.length; i++) {
						phaseDataObj[i][0] = apprRating.getApprId();
						phaseDataObj[i][1] = phaseId[i];
						phaseDataObj[i][2] = phaseRatingFormula[i];
						phaseDataObj[i][3] = apprRating.getTemplateCode();
					}

					result = getSqlModel().singleExecute(
							insertPhaseRatingQuery, phaseDataObj);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public void displayRecord(ApprRatingDefinition apprRating) {
		// TODO Auto-generated method stub
		try {

			String selQuery = " SELECT PAS_RATING.APPR_ID,PAS_APPR_CALENDAR.APPR_CAL_CODE, APPR_QUES_FORMULS, APPR_SCORE_FORMULA,NVL(APPR_TEMPALTE_CODE,0),APPR_PREV_RATING_FORMULA FROM PAS_RATING " 
				+" INNER JOIN PAS_APPR_CALENDAR ON PAS_APPR_CALENDAR.APPR_ID = PAS_RATING.APPR_ID "
				+" WHERE PAS_RATING.APPR_ID=" + apprRating.getApprId()+" AND APPR_TEMPALTE_CODE = "+apprRating.getTemplateCode()+" ORDER BY  PAS_RATING.APPR_ID ";
 
			Object selectData[][] = getSqlModel().getSingleResult(selQuery);
			
			if(selectData!=null && selectData.length >0 )
			{
				apprRating.setApprId(checkNull(String.valueOf(selectData[0][0])));
				apprRating.setApprCode(checkNull(String.valueOf(selectData[0][1])));
				apprRating.setQuesRatingFormula(checkNull(String.valueOf(selectData[0][2])));
				apprRating.setFinalRatingFormula(checkNull(String.valueOf(selectData[0][3])));
				apprRating.setTemplateCode(checkNull(String.valueOf(selectData[0][4])));
				apprRating.setPreRatingFormula(checkNull(String.valueOf(selectData[0][5])));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	/**
	 * Removing null values
	 * 
	 * @param result
	 * @return String replaces null with ""
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// END if
		else {
			return result;
		}// END else
	}

	public Object[][] displayPhases(ApprRatingDefinition apprRating) {
		Object data[][]=null;
		Object total[][]=null;
		try {
			// TODO Auto-generated method stub
			String query = " SELECT PAS_RATING_PHASE.PHASE_ID,APPR_PHASE_NAME,APPR_PHASE_ORDER,APPR_PHASE_SCORE_FORMULA FROM PAS_RATING_PHASE "
				+" INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_RATING_PHASE.PHASE_ID "
				+" WHERE PAS_RATING_PHASE.APPR_ID ="+apprRating.getApprId()+"AND PAS_RATING_PHASE.APPR_TEMPLATE_CODE = "+apprRating.getTemplateCode()+" ORDER BY APPR_PHASE_ORDER ";
			  data  = getSqlModel().getSingleResult(query);
				total = new String[data.length][4];
				for (int i = 0; i < data.length; i++) {
					total[i][0] =checkNull(String.valueOf(data[i][0]));  
					total[i][1] =checkNull(String.valueOf(data[i][1])); 
					total[i][2] =checkNull(String.valueOf(data[i][2])); 
					total[i][3] =checkNull(String.valueOf(data[i][3])); 
				}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return total;
	}


}
