package org.paradyne.model.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.bean.PAS.AppraisalMonitoring;
import org.paradyne.lib.ModelBase;

public class AppraisalMonitoringModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppraisalMonitoringModel.class);
	
	public void getPhaseDetails(AppraisalMonitoring bean){
		ArrayList<Object> phaseList = new ArrayList<Object>();
		try{
			
			String grpQuery =" SELECT PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER_GRP_HDR  "
					+" INNER JOIN PAS_APPR_APPRAISER_GRP_DTL ON PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID "
					+" WHERE PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = ? and APPR_APPRAISEE_ID = ? ";
	
			Object [][] resultObj = getSqlModel().getSingleResult(grpQuery,new Object[]{bean.getApprId(),bean.getEmpCode()});
			
			bean.setDisplayFlag("true");
			if(resultObj != null && resultObj.length > 0){
				
				bean.setAppraiseeGroup(String.valueOf(resultObj[0][0]));
				
				String appraiserQuery =" SELECT PAS_APPR_APPRAISER.APPR_PHASE_ID,APPR_PHASE_NAME,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS NAME,APPR_APPRAISER_LEVEL,NVL(PHASE_FORWARD,'N') FROM PAS_APPR_APPRAISER "
					+" INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_APPRAISER.APPR_PHASE_ID "
					+" LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE "
					+" LEFT JOIN PAS_APPR_TRACKING ON PAS_APPR_TRACKING.PHASE_ID = PAS_APPR_APPRAISER.APPR_PHASE_ID  "
					+" AND PAS_APPR_TRACKING.APPR_ID = PAS_APPR_APPRAISER.APPR_ID  "
					+" AND (PAS_APPR_TRACKING.PHASE_INSERTED_EMPLOYEE = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE OR PAS_APPR_APPRAISER.APPR_APPRAISER_CODE is null) "
					+" AND APPR_APPRAISER_GRP_ID = ? "
					+" WHERE PAS_APPR_TRACKING.EMP_ID = ? AND APPR_APPRAISER_GRP_ID = ? "
					+" ORDER BY APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";
				
				Object [][] dataObj = getSqlModel().getSingleResult(appraiserQuery,new Object[]{String.valueOf(resultObj[0][0]),bean.getEmpCode(),String.valueOf(resultObj[0][0])});
				
				if(dataObj != null && dataObj.length > 0){
					
					for (int i = 0; i < dataObj.length; i++) {
						
						AppraisalMonitoring tempBean = new AppraisalMonitoring();
						
						tempBean.setPhaseCode(String.valueOf(dataObj[i][0]));
						tempBean.setPhaseName(String.valueOf(dataObj[i][1]));
						if(i == 0)
							tempBean.setAppraiserName(bean.getEmpName());
						else
							tempBean.setAppraiserName(String.valueOf(dataObj[i][2]));
						tempBean.setAppraiserLevel(String.valueOf(dataObj[i][3]));
						if(String.valueOf(dataObj[i][4]).equals("Y")){
							tempBean.setPhaseCompleted("Completed");
						}else
							tempBean.setPhaseCompleted("NotCompleted");
						
						phaseList.add(tempBean);
					}
				}else{
					bean.setNoData("true");
				}	
				bean.setPhaseList(phaseList);
			}else{
				bean.setNoData("true");
			}
		}catch(Exception e){
			logger.info("exception in getPhaseDetails -- "+e);
		}
	}
	public boolean sendBack(AppraisalMonitoring bean){
		boolean result2 = false;
			try{
				String scoreDelete = "DELETE FROM PAS_APPR_SCORE WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID = "+bean.getEmpCode();
				
				boolean res = getSqlModel().singleExecute(scoreDelete);	
				
				String deleteQuery =" DELETE FROM PAS_APPR_TRACKING WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID ="+bean.getEmpCode()
							+" AND PHASE_ID ="+bean.getSbPhaseCode()+" AND PHASE_APPRAISER_LEVEL >= "+bean.getSbAppraiserLevel();
				
				boolean result = getSqlModel().singleExecute(deleteQuery);

				String deleteQuery1 = " DELETE FROM PAS_APPR_TRACKING WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID ="+bean.getEmpCode()
							+" AND PHASE_ID IN( SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ORDER > "+bean.getSbPhaseOrder()+" AND APPR_PHASE_STATUS ='A' AND PAS_APPR_PHASE_CONFIG.APPR_ID = "+bean.getApprId()+")";
				
				boolean result1 = getSqlModel().singleExecute(deleteQuery1);
								
				if(Integer.parseInt(bean.getSbAppraiserLevel()) > 1){
					
					String updateQuery = " UPDATE PAS_APPR_TRACKING SET APPRAISAL_STATUS ='N', NEXT_PHASE_FORWARD = 'N' WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID ="+bean.getEmpCode()
					+" AND PHASE_ID = "+bean.getSbPhaseCode()+" AND PHASE_APPRAISER_LEVEL = "+(Integer.parseInt(bean.getSbAppraiserLevel())-1);
		
					 result2 = getSqlModel().singleExecute(updateQuery);
					
				}else{
					
					String updateQuery2 = " SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ORDER = "+(Integer.parseInt(bean.getSbPhaseOrder())-1)+" AND APPR_PHASE_STATUS ='A' AND PAS_APPR_PHASE_CONFIG.APPR_ID = "+bean.getApprId();
					
					Object[][] obj = getSqlModel().getSingleResult(updateQuery2);
					
					if(obj != null && obj.length > 0){
						
						String updateQuery1 = " UPDATE PAS_APPR_TRACKING SET APPRAISAL_STATUS ='N', NEXT_PHASE_FORWARD = 'N' WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID ="+bean.getEmpCode()
									+" AND PHASE_ID = "+String.valueOf(obj[0][0])
									+" AND PHASE_APPRAISER_LEVEL = ( SELECT MAX(PHASE_APPRAISER_LEVEL) FROM PAS_APPR_TRACKING WHERE APPR_ID = "+bean.getApprId()
									+" AND EMP_ID = "+bean.getEmpCode()+" AND PHASE_ID = "+String.valueOf(obj[0][0])+" ) ";
						
						 result2 = getSqlModel().singleExecute(updateQuery1);
					}else
						result2 = true;
				}

			}catch(Exception e){
				logger.info("exception in sendBack -- "+e);
			}
		return result2;
	}
	
	public boolean changeGroup(AppraisalMonitoring bean){
		boolean result1= false;
		try{
			boolean test = deleteAppraiserDetails(bean.getApprId(),bean.getEmpCode());
			if(test){
				
				if(bean.getAppraiseeGroup().equals("")){
					
					String insertQuery = " INSERT INTO PAS_APPR_APPRAISER_GRP_DTL (APPR_APPRAISER_GRP_ID,APPR_APPRAISEE_ID )VALUES ( ?,? )";
					result1 = getSqlModel().singleExecute(insertQuery, new Object [][]{{bean.getGroupId(),bean.getEmpCode()}});
					
				}else{
					
					String deleteQuery =" DELETE FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID = "+bean.getAppraiseeGroup()
					+" AND APPR_APPRAISEE_ID = "+bean.getEmpCode();
			
					boolean result = getSqlModel().singleExecute(deleteQuery);
			
					if(result){
						String insertQuery = " INSERT INTO PAS_APPR_APPRAISER_GRP_DTL (APPR_APPRAISER_GRP_ID,APPR_APPRAISEE_ID )VALUES ( ?,? )";
						result1 = getSqlModel().singleExecute(insertQuery, new Object [][]{{bean.getGroupId(),bean.getEmpCode()}});
					}
				}
				
				String appraiserQuery =" SELECT APPR_APPRAISER_CODE FROM PAS_APPR_APPRAISER  WHERE APPR_APPRAISER_GRP_ID = "+bean.getGroupId()
										+" AND APPR_APPRAISER_CODE IS NOT NULL AND APPR_APPRAISER_LEVEL = 1 AND APPR_PHASE_ID = "
										+" ( SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ORDER = "
										+" (SELECT APPR_PHASE_ORDER +1 FROM PAS_APPR_PHASE_CONFIG WHERE APPR_IS_SELFPHASE = 'Y' AND APPR_ID = "+bean.getApprId()+" AND APPR_PHASE_STATUS = 'A') "
										+" AND APPR_ID = "+bean.getApprId()+" AND APPR_PHASE_STATUS = 'A')";
				
				Object [][] resultObj = getSqlModel().getSingleResult(appraiserQuery);
				if(resultObj != null && resultObj.length > 0){
					
					String updateQuery1 = " UPDATE PAS_APPR_TRACKING SET APPRAISAL_STATUS ='N', NEXT_PHASE_FORWARD = 'N',NEXT_APPRAISER = "+String.valueOf(resultObj[0][0])
						+" WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID ="+bean.getEmpCode();

					boolean result = getSqlModel().singleExecute(updateQuery1);
				}
								
			}
		}catch(Exception e){
			logger.info("exception in changeGroup -- "+e);
		}
		return result1;
	}
	
	public boolean deleteAppraiserDetails(String apprId,String empCode){
		boolean res8=false;
		try{
			
			String scoreDelete = "DELETE FROM PAS_APPR_SCORE WHERE APPR_ID = "+apprId+" AND EMP_ID = "+empCode;
			boolean res = getSqlModel().singleExecute(scoreDelete);
			
			String trackDelete = " DELETE FROM PAS_APPR_TRACKING WHERE APPR_ID = "+apprId+" AND EMP_ID = "+empCode+" AND PHASE_INSERTED_EMPLOYEE != "+empCode;
			boolean res0 = getSqlModel().singleExecute(trackDelete);
			
			String careerDelete = " DELETE FROM PAS_EMP_GENERAL_COMMENTS WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode+" AND APPR_APPRAISER_CODE != "+empCode;
			boolean res1 = getSqlModel().singleExecute(careerDelete);
			
			String careerDelete1 = " DELETE FROM PAS_APPR_CAREER_COMMENT WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode+" AND APPR_APPRAISER_ID != "+empCode;
			boolean res2 = getSqlModel().singleExecute(careerDelete1);
			
			String discpDelete = " DELETE FROM PAS_APPR_DISCIPLINARY_COMMENT WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode;
			boolean res3 = getSqlModel().singleExecute(discpDelete);
			
			String awdDelete = " DELETE FROM PAS_APPR_AWD_ACHIEVED_COMMENT WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode;
			boolean res4 = getSqlModel().singleExecute(awdDelete);
			
			String awdDelete1 = " DELETE FROM PAS_APPR_AWD_NOMINATE_COMMENT WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode+" AND APPR_APPRAISER_ID != "+empCode;
			boolean res5 = getSqlModel().singleExecute(awdDelete1);
			
			String trnDelete = " DELETE FROM PAS_APPR_TRN_ATTENDED_COMMENT WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode;
			boolean res6 = getSqlModel().singleExecute(trnDelete);
			
			String trnDelete1 = " DELETE FROM PAS_APPR_TRN_RECOMMEND_COMMENT WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode;
			boolean res7 = getSqlModel().singleExecute(trnDelete1);
			
			String cmtDelete1 = " DELETE FROM PAS_APPR_COMMENTS WHERE APPR_ID = "+apprId+" AND APPR_EMP_ID = "+empCode+" AND APPR_EVALUATOR_CODE != "+empCode;
			 res8 = getSqlModel().singleExecute(cmtDelete1);
			
		}catch(Exception e){
			logger.info("exception in deleteEmpDetails -- "+e);
		}
		return res8;
	}
	
	public boolean addEmployee(AppraisalMonitoring bean){
		boolean result2= false;
		try{
			String eligibleQuery = " INSERT INTO PAS_APPR_ELIGIBLE_EMP (APPR_ID,APPR_EMP_ID,APPR_EMP_STATUS,APPR_SYS_DATE,APPR_CRT_TYPE ) "
						+" VALUES ("+bean.getApprId()+","+bean.getAddEmpId()+",'A',SYSDATE,'O')";
			
			boolean result = getSqlModel().singleExecute(eligibleQuery);
			
			if(result){
				String apprGrpQuery = "INSERT INTO PAS_APPR_APPRAISER_GRP_DTL (APPR_APPRAISER_GRP_ID,APPR_APPRAISEE_ID )"
						+" VALUES ( "+bean.getAddAppraiserGroupCode()+","+bean.getAddEmpId()+")";
				
				boolean result1 = getSqlModel().singleExecute(apprGrpQuery);
				
				String templateQuery = "INSERT INTO PAS_APPR_EMP_GRP_DTL (APPR_EMP_GRP_ID,APPR_EMP_GRP_EMPID )"
					+" VALUES ( "+bean.getQuesGroupCode()+","+bean.getAddEmpId()+")";
			
				result2 = getSqlModel().singleExecute(templateQuery);
			}
			
		}catch(Exception e){
			logger.info("exception in addEmployee -- "+e);
		}
		return result2;
	}
	
	public boolean removeEmployee(AppraisalMonitoring bean){
		boolean result3 = false;
		try{			
			try{
				
				String scoreDelete = "DELETE FROM PAS_APPR_SCORE WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID = "+bean.getRemoveEmpId();
				boolean res = getSqlModel().singleExecute(scoreDelete);
				
				String trackDelete = " DELETE FROM PAS_APPR_TRACKING WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID = "+bean.getRemoveEmpId();
				boolean res0 = getSqlModel().singleExecute(trackDelete);
				
				String careerDelete = " DELETE FROM PAS_EMP_GENERAL_COMMENTS WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res1 = getSqlModel().singleExecute(careerDelete);
				
				String careerDelete1 = " DELETE FROM PAS_APPR_CAREER_COMMENT WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res2 = getSqlModel().singleExecute(careerDelete1);
				
				String discpDelete = " DELETE FROM PAS_APPR_DISCIPLINARY_COMMENT WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res3 = getSqlModel().singleExecute(discpDelete);
				
				String discpDelete0 = " DELETE FROM PAS_APPR_DISCIPLINARY WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res30 = getSqlModel().singleExecute(discpDelete0);
				
				String awdDelete = " DELETE FROM PAS_APPR_AWD_ACHIEVED_COMMENT WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res4 = getSqlModel().singleExecute(awdDelete);
				
				String awdDelete0 = " DELETE FROM PAS_APPR_AWARD_ACHIEVED WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res40 = getSqlModel().singleExecute(awdDelete0);
				
				String awdDelete1 = " DELETE FROM PAS_APPR_AWD_NOMINATE_COMMENT WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res5 = getSqlModel().singleExecute(awdDelete1);
				
				String trnDelete = " DELETE FROM PAS_APPR_TRN_ATTENDED_COMMENT WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res6 = getSqlModel().singleExecute(trnDelete);
				
				String trnDelete0 = " DELETE FROM PAS_APPR_TRN_ATTENDED WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res60 = getSqlModel().singleExecute(trnDelete0);
				
				String trnDelete1 = " DELETE FROM PAS_APPR_TRN_RECOMMEND_COMMENT WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res7 = getSqlModel().singleExecute(trnDelete1);
				
				String cmtDelete1 = " DELETE FROM PAS_APPR_COMMENTS WHERE APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getRemoveEmpId();
				boolean res8 = getSqlModel().singleExecute(cmtDelete1);
				
			}catch(Exception e){
				logger.info("exception in deleteEmpDetails -- "+e);
			}
			
				String templateDelete = " DELETE FROM PAS_APPR_EMP_GRP_DTL WHERE APPR_EMP_GRP_EMPID = "+bean.getRemoveEmpId()
							+" AND APPR_EMP_GRP_ID IN (SELECT APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR WHERE APPR_ID = "+bean.getApprId()+")";
				
				boolean result1 = getSqlModel().singleExecute(templateDelete);
				
				String appraiserDelete = " DELETE FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISEE_ID = "+bean.getRemoveEmpId()
							+" AND APPR_APPRAISER_GRP_ID IN (SELECT APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID = "+bean.getApprId()+")";
				
				boolean result2 = getSqlModel().singleExecute(appraiserDelete);
				
				String eligibleDelete = " DELETE FROM PAS_APPR_ELIGIBLE_EMP WHERE APPR_EMP_ID = "+bean.getRemoveEmpId()
							+" AND APPR_ID = "+bean.getApprId();
	
				result3 = getSqlModel().singleExecute(eligibleDelete);
				
			
		}catch(Exception e){
			logger.info("exception in removeEmployee -- "+e);
		}
		return result3;
	}
}
