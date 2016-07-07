package org.struts.action.PAS;
import org.paradyne.bean.PAS.DepartmentRating;
import org.paradyne.model.PAS.DepartmentRatingModel;
import org.struts.lib.ParaActionSupport;

public class DepartmentRatingAction extends ParaActionSupport {

	DepartmentRating deptRating;
	
	public void prepare_local() throws Exception {
		deptRating = new DepartmentRating();
		deptRating.setMenuCode(2051);
	}
	
	public Object getModel() {
		return deptRating;
	}

	public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,"
			+" CASE WHEN TO_DATE(SYSDATE,'DD-MM-YYYY') < TO_DATE(APPR_CAL_VALID_DATE,'DD-MM-YYYY') THEN 'N' " 
			+" WHEN TO_DATE(SYSDATE,'DD-MM-YYYY') > TO_DATE(APPR_CAL_VALID_DATE,'DD-MM-YYYY') THEN 'Y' END ,"
			+" TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY') FROM PAS_APPR_CALENDAR "
			+" ORDER BY APPR_ID";
		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.from"),getMessage("appraisal.to")};

		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId","lockFlag","closureDate"};

		int[] columnIndex = { 0,1,2,3,4,5 };

		String submitFlag = "true";

		String submitToMethod = "DepartmentRating_getDeptScoreData.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		
		return "f9page";
	}
	public String getDeptScoreData(){
		DepartmentRatingModel model=new DepartmentRatingModel();
		String appId=request.getParameter("apprId");
		model.initiate(context, session);
		model.getDeptScoreData(deptRating,appId);
		model.terminate();
		return SUCCESS;
	}
	public String saveDeptScoreData(){
		DepartmentRatingModel model=new DepartmentRatingModel();
		boolean result=false;
		String str="";
		String deptId[]=request.getParameterValues("deptId");
		String deptScore[]=request.getParameterValues("deptScore");
		String modDeptScore[]=request.getParameterValues("modDeptScore");
		model.initiate(context, session);
		result=model.saveScoreData(deptRating,deptId,deptScore,modDeptScore);
		if(result){
			str="Record Save Successfully";
		}
		model.terminate();
		getDeptScoreData();
		addActionMessage(str);
		return SUCCESS;
	}
	public String caliculateDeptRating(){
		DepartmentRatingModel model=new DepartmentRatingModel();
		boolean result=false;
		String str="";
		String appId=request.getParameter("apprId");
		model.initiate(context, session);
		result=model.calDeptRating(deptRating,appId);
		if(result){
			str="Department Rating Caliculated Successfully";
		}else{
			model.getDeptScoreData(deptRating, appId);
		}
		model.terminate();
		addActionMessage(str);
		return SUCCESS;
		
	}
}
