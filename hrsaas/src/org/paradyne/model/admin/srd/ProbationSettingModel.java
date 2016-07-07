package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import org.paradyne.bean.admin.srd.ProbationSetting;
import org.paradyne.lib.ModelBase;


public class ProbationSettingModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ProbationSettingModel.class);
	
	public void setRecord(ProbationSetting probationSetting) {
		try {
			 
		String hdrQuery = " SELECT PROBATION_APPLICABLE,HRMS_EMP_TYPE.TYPE_NAME,PROBATION_DAYS,PROBATION_EMPTYPE "
			+ " FROM HRMS_PROBATION_HDR "
			+ " INNER JOIN  HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_PROBATION_HDR.PROBATION_EMPTYPE) " ;
			
				Object objData[][] = getSqlModel().getSingleResult(hdrQuery);
				
				if(objData!=null && objData.length >0)
				{
					if(String.valueOf(objData[0][0]).equals("Y"))
					{
						probationSetting.setProbationApp("true");
					}
					else
					{
						probationSetting.setProbationApp("false");
					}
					probationSetting.setEmpType(checkNull(String.valueOf(objData[0][1])));
					probationSetting.setProbationDays(checkNull(String.valueOf(objData[0][2])));
					probationSetting.setEmpTypeCode(checkNull(String.valueOf(objData[0][3])));
				}
		
			String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
					+ " ORDER BY CADRE_ID ";
			Object gradeDataObj[][] = getSqlModel().getSingleResult(query);
			if (gradeDataObj != null && gradeDataObj.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < gradeDataObj.length; i++) {
					ProbationSetting bean = new ProbationSetting();
					bean.setGradeId(checkNull(String.valueOf(gradeDataObj[i][0])));
					bean.setGradeName(checkNull(String.valueOf(gradeDataObj[i][1])));
					bean.setProbationGradeDays("0");
					list.add(bean);
				}
				probationSetting.setGradeList(list);
			}
		} catch (Exception e) {
			logger.error("Exception in setRecord-------"+e);
		}
			
	}

	public boolean saveProbation(ProbationSetting probationSetting, String[] gradeIds, String[] gradeDays) {
		Object insertHdrObj[][]=null;
	 	Object insertDtlObj[][]=null;
	  	boolean result=false;
	  	boolean result1=false;
	  	boolean flag=false;
		try {
			// TODO Auto-generated method stub
			insertHdrObj = new Object[1][3];
			insertHdrObj[0][0] = probationSetting.getProbationApp();
			if (String.valueOf(insertHdrObj[0][0]).equals("true")) {
				insertHdrObj[0][0] = "Y";
			} else {
				insertHdrObj[0][0] = "N";
			}
			insertHdrObj[0][1] = probationSetting.getEmpTypeCode();
			insertHdrObj[0][2] = probationSetting.getProbationDays();
			String insertHdrQuery = " INSERT INTO HRMS_PROBATION_HDR(PROBATION_APPLICABLE, PROBATION_EMPTYPE, PROBATION_DAYS)"
					+ " VALUES(?,?,?)";
			result =getSqlModel().singleExecute(insertHdrQuery, insertHdrObj);
			
			if(result)
			{
				if(gradeIds!=null)
				{	
					insertDtlObj=new Object[gradeIds.length][2];
					for (int i = 0; i < gradeIds.length; i++) {
						insertDtlObj[i][0]=gradeIds[i];
						insertDtlObj[i][1]=gradeDays[i];
					}
					String insertDtlQuery = " INSERT INTO HRMS_PROBATION_DTL(PROBATION_GRADE, PROBATION_GRADEDAYS)"
						+ " VALUES(?,?)";
					result1 = getSqlModel().singleExecute(insertDtlQuery, insertDtlObj);
				}
			}
			if(result & result1)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
			
		} catch (Exception e) {
			logger.error("Exception in saveProbation-------"+e);
		}
		return flag;
	}

	public void setSavedRecord(ProbationSetting probationSetting) {
		try {
			// TODO Auto-generated method stub
			String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME),HRMS_PROBATION_DTL.PROBATION_GRADEDAYS FROM  HRMS_CADRE "
					+ "  INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE=HRMS_CADRE.CADRE_ID)  "
					+ " ORDER BY CADRE_ID ";
			Object gradeDataObj[][] = getSqlModel().getSingleResult(query);
			if (gradeDataObj != null && gradeDataObj.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < gradeDataObj.length; i++) {
					ProbationSetting bean = new ProbationSetting();
					bean.setGradeId(checkNull(String.valueOf(gradeDataObj[i][0])));
					bean.setGradeName(checkNull(String.valueOf(gradeDataObj[i][1])));
					bean.setProbationGradeDays(checkNull(String
							.valueOf(gradeDataObj[i][2])));
					list.add(bean);
				}
				probationSetting.setGradeList(list);
			}
		} catch (Exception e) {
			logger.error("Exception in setSavedRecord-------"+e);
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public boolean updateProbation(ProbationSetting probationSetting,
			String[] gradeIds, String[] gradeDays) {
		 
		Object updateHdrObj[][]=null;
	 	Object updateDtlObj[][]=null;
	  	boolean result=false;
	  	boolean result1=false;
	  	boolean flag=false;
		try {
			// TODO Auto-generated method stub
			updateHdrObj = new Object[1][3];
			updateHdrObj[0][0] = probationSetting.getProbationApp();
			if (String.valueOf(updateHdrObj[0][0]).equals("true")) {
				updateHdrObj[0][0] = "Y";
			} else {
				updateHdrObj[0][0] = "N";
			}
			updateHdrObj[0][1] = probationSetting.getEmpTypeCode();
			updateHdrObj[0][2] = probationSetting.getProbationDays();
		
			String updateHdrQuery = " UPDATE HRMS_PROBATION_HDR SET PROBATION_APPLICABLE=?,PROBATION_EMPTYPE=?,PROBATION_DAYS=?";
			
			result =getSqlModel().singleExecute(updateHdrQuery, updateHdrObj);
			
			if(result)
			{
				if(gradeIds!=null)
				{	
					updateDtlObj=new Object[gradeIds.length][2];
					for (int i = 0; i < gradeIds.length; i++) {
						updateDtlObj[i][0]=gradeIds[i];
						updateDtlObj[i][1]=gradeDays[i];
					}
					
					String deleteDtlQuery ="DELETE FROM HRMS_PROBATION_DTL";
					getSqlModel().singleExecute(deleteDtlQuery);
					
					String insertDtlQuery = " INSERT INTO HRMS_PROBATION_DTL(PROBATION_GRADE, PROBATION_GRADEDAYS)"
						+ " VALUES(?,?)";
					result1 = getSqlModel().singleExecute(insertDtlQuery, updateDtlObj);
				}
			}
			if(result & result1)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
			
		} catch (Exception e) {
			logger.error("Exception in updateProbation-------"+e);
		}
		return flag;
	}
}
