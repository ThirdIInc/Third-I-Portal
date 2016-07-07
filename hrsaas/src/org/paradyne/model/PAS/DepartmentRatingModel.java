/*
 * Author Anantha lakshmi
 */
package org.paradyne.model.PAS;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.omg.CORBA.Request;
import org.paradyne.bean.PAS.DepartmentRating;
import org.paradyne.lib.ModelBase;

public class DepartmentRatingModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DepartmentRatingModel.class);
	/*The Following function  get the department rating data from  table PAS_APPR_DEPT_RATING
	 * @param bean, Appraisal Id
	 */
	public void getDeptScoreData(DepartmentRating bean,String appId){
		ArrayList<Object> phaseList = new ArrayList<Object>();
		try{
			String grpQuery =" SELECT HRMS_DEPT.DEPT_NAME,DEPT_RATING,DEPT_MOD_RATING,PAS_APPR_DEPT_RATING.DEPT_ID FROM PAS_APPR_DEPT_RATING"
					+" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = PAS_APPR_DEPT_RATING.DEPT_ID"
					+" WHERE APPR_ID="+appId;
	
			Object [][] resultObj = getSqlModel().getSingleResult(grpQuery);
			ArrayList<Object> deptScoreDataList=new ArrayList<Object>();
			
			if(resultObj != null && resultObj.length > 0){
				
				String orgScoreQuery =" SELECT AVG(DEPT_MOD_RATING) FROM PAS_APPR_DEPT_RATING "+" WHERE APPR_ID="+appId;
				Object [][] dataObj = getSqlModel().getSingleResult(orgScoreQuery);
				if(dataObj != null && dataObj.length > 0){
					bean.setOrganizedScore(String.valueOf(dataObj[0][0]));
				}
				
				for (int i = 0; i < resultObj.length; i++) {	
						DepartmentRating tempBean = new DepartmentRating();
						tempBean.setDeptName(String.valueOf(resultObj[i][0]));
						tempBean.setDeptScore(String.valueOf(resultObj[i][1]));
						tempBean.setModDeptScore(String.valueOf(resultObj[i][2]));
						tempBean.setDeptId(String.valueOf(resultObj[i][3]));
						deptScoreDataList.add(tempBean);
				}
				bean.setDeptScoreList(deptScoreDataList);
			}else{
				calDeptRating(bean, appId);
				getOrganzatioScore(bean, appId);
			}
		}catch(Exception e){
			logger.info("exception in getPhaseDetails -- "+e);
		}
	}
	/*The following function  saved the department rating data 
	 * @param bean, Appraisal Id
	 */
	public boolean  saveScoreData(DepartmentRating bean,String deptId[],String deptScore[],String modDeptScore[]){
		Boolean result=false;
		Object[][] param=null;
		String appId=bean.getApprId();
		if(deptId!=null && deptId.length >0){
			param=new Object[deptId.length][4];
			for(int i=0;i<deptId.length;i++){
				if(modDeptScore[i].equals("") || modDeptScore[i].equals("null")){
					param[i][0]=String.valueOf("0");//Amount
				}else{
					param[i][0]=modDeptScore[i]; //modDeptScore
				}
				param[i][1]=deptScore[i];
				param[i][2]=deptId[i];
				param[i][3]=appId;
			}//End of for loop
		}//End if
		String delQuery= " DELETE FROM PAS_APPR_DEPT_RATING WHERE APPR_ID="+appId;
		result=getSqlModel().singleExecute(delQuery);
		if(result){
			result=getSqlModel().singleExecute(getQuery(1),param);
			String delOrganizedScoreUpdateQuery= "UPDATE PAS_APPR_CALENDAR  SET APPR_CAL_ORG_SCORE="+bean.getOrganizedScore()+" WHERE APPR_ID="+appId;
			result=getSqlModel().singleExecute(delOrganizedScoreUpdateQuery);
		}
		return result;
	}
	/*The Following function the caliculated Department Rating  Score 
	 * @param bean, Appraisal Id
	 */
	public boolean calDeptRating(DepartmentRating bean,String appId){
		ArrayList<Object> phaseList = new ArrayList<Object>();
		boolean result=false;
		try{
			String grpQuery ="select dept_name,avg(MOD_SCORE),dept_id from PAS_APPR_MGMNT_PANEL_REVIEW"
				+" inner join hrms_emp_offc on (PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
							+" inner join HRMS_DEPT on (dept_id=emp_dept) "
							+" WHERE APPR_ID=" + appId + "  group by dept_name,dept_id";
	
			Object [][] resultObj = getSqlModel().getSingleResult(grpQuery);
			ArrayList<Object> caldeptScoreList=new ArrayList<Object>();
			
			if(resultObj != null && resultObj.length > 0){
				for (int i = 0; i < resultObj.length; i++) {			
						DepartmentRating tempBean = new DepartmentRating();
						tempBean.setDeptName(String.valueOf(resultObj[i][0]));
						tempBean.setDeptScore(String.valueOf(resultObj[i][1]));
						tempBean.setModDeptScore(String.valueOf(resultObj[i][1]));
						tempBean.setDeptId(String.valueOf(resultObj[i][2]));
						caldeptScoreList.add(tempBean);
				}
				bean.setDeptScoreList(caldeptScoreList);
				 result=true;
			}else
			{
				 result=false;
			}
		}catch(Exception e){
			logger.info("exception in getPhaseDetails -- "+e);
		}
		return result;
	}
	/*The Following function the caliculated Average organzation score 
	 * @param bean, Appraisal Id
	 */
	public void getOrganzatioScore(DepartmentRating bean,String appId){
		String orgScoreQuery =" SELECT AVG(MOD_SCORE) FROM PAS_APPR_MGMNT_PANEL_REVIEW "	
            +" WHERE APPR_ID="+appId;
		Object [][] dataObj = getSqlModel().getSingleResult(orgScoreQuery);
		if(dataObj != null && dataObj.length > 0){
			bean.setOrganizedScore(String.valueOf(dataObj[0][0]));
		}
	}
}
