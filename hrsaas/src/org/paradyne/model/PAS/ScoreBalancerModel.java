package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import org.paradyne.bean.PAS.ScoreBalancer;
import org.paradyne.lib.ModelBase;

public class ScoreBalancerModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ScoreBalancerModel.class);
	
	
	public String getAppraiser(String empCode, Object [][]appraiserData){
		String  apprName="";
	if(appraiserData != null && appraiserData.length !=0){
			for (int i = 0; i < appraiserData.length; i++) {
			if(empCode.equals(String.valueOf(appraiserData[i][0]))){
					if(i==0)
						apprName = String.valueOf(appraiserData[i][1]);
					else if(i != appraiserData.length){
						apprName = apprName+","+String.valueOf(appraiserData[i][1]);
					}
				}
						
			}
		}
		/* Remove the commas from first and last character */
		if ((apprName != null) && (apprName.length() > 0)) {
			
			apprName = apprName.trim();
			
			/* check in first character are comma then remove */
			if(apprName.substring(0,1).equals(","))
				apprName = apprName.substring(1,apprName.length());
			
			/* check in last character are comma then remove */
			if(apprName.substring(apprName.length()-1,apprName.length()).equals(","))
				apprName = apprName.substring(0,apprName.length()-1);
		}
		return apprName;
	}
	
	public void getEmpList(ScoreBalancer bean, HttpServletRequest request){
		
		try{
			/*String appraiserQuery= "  SELECT PAS_APPR_TRACKING.EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME) FROM PAS_APPR_TRACKING "
						+" LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_TRACKING.NEXT_APPRAISER = HRMS_EMP_OFFC.EMP_ID) " 
						+" WHERE APPR_ID =  "+bean.getApprId();*/
						
			String appraiserQuery= "select APPR_APPRAISEE_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME) FROM PAS_APPR_APPRAISER_GRP_DTL "
						+" LEFT JOIN PAS_APPR_APPRAISER ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID and APPR_ID = "+bean.getApprId()+" ) "
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE) WHERE APPR_ID = "+bean.getApprId()
						+" ORDER BY APPR_APPRAISEE_ID,APPR_PHASE_ID,APPR_APPRAISER_LEVEL ";
				
			Object [][] appraiser = getSqlModel().getSingleResult(appraiserQuery);
	
			String empQuery = "  SELECT PAS_APPR_SCORE.EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME) AS EMPNAME, NVL(HRMS_DEPT.DEPT_NAME,'  ') AS DEPTNAME, "
						+" APPR_SCORE,APPR_ADJUST_FACTOR,APPR_SCORE_ADJUST,APPR_FINAL_SCORE,TEMPLATE_CODE,DECODE(APPR_SCORE_FINALIZE,'Y','true','false') "
						+" FROM PAS_APPR_SCORE " 
						+" LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_SCORE.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
						+" WHERE APPR_ID = "+bean.getApprId();
			
			if(!bean.getDivCode().equals(""))
				empQuery += " AND HRMS_EMP_OFFC.EMP_DIV = "+bean.getDivCode();
			
			if(!bean.getBranchCode().equals(""))
				empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+bean.getBranchCode();
			
			if(!bean.getDeptCode().equals(""))
				empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+bean.getDeptCode();
			
			if(!bean.getApprEmpCode().equals(""))
				empQuery += " AND PAS_APPR_SCORE.EMP_ID in (SELECT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL "
						   +" INNER JOIN PAS_APPR_APPRAISER ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID and APPR_ID = "+bean.getApprId()+" )"
						   +" WHERE APPR_APPRAISER_CODE IS NOT NULL and APPR_APPRAISER_CODE = "+bean.getApprEmpCode()+" )";
			
			empQuery += " ORDER BY  DEPTNAME, EMPNAME";
			
			
			Object [][] empData = getSqlModel().getSingleResult(empQuery);
						
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = empData.length;
			if(empData==null || empData.length==0){
				bean.setNoData("true");
			}else{
				bean.setNoData("false");
			}
			int no_of_pages = Math.round(REC_TOTAL / 20);
			// PageNo = Integer.parseInt(bean.getMyPage());//-----------
			double row = (double) empData.length / 20.0;

			java.math.BigDecimal value1 = new java.math.BigDecimal(row);

			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			request.setAttribute("abc", rowCount1);

			// PageNo
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals("")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;

				if (To_TOT > empData.length) {
					To_TOT = empData.length;
				}
				logger.info("-----------To_TOTAL----null-----" + To_TOT);
				bean.setMyPage("1");
			}

			else {

				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				} else {
					// From_TOTAL=To_TOTAL+1;
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > empData.length) {
					To_TOT = empData.length;
				}
			}
			request.setAttribute("xyz", PageNo1);
			logger.info("------------from total--------" + From_TOT);
			logger.info("----------to total----------" + To_TOT);
			logger.info("empData-----------"+empData.length);
			ArrayList<Object> list=new ArrayList<Object>();
			if(empData != null && empData.length !=0){
				for (int i = From_TOT; i < To_TOT; i++) {
					ScoreBalancer bean1 = new ScoreBalancer();
					
					bean1.setEmpId(checkNull(String.valueOf(empData[i][0])));
					bean1.setEmpName(checkNull(String.valueOf(empData[i][1])));
					bean1.setDeptName(checkNull(String.valueOf(empData[i][2])));
					bean1.setAppraiserName(getAppraiser(String.valueOf(empData[i][0]),appraiser));
					bean1.setApprScore(checkNull(String.valueOf(empData[i][3])));
					bean1.setOprand(checkNull(String.valueOf(empData[i][4])));
					bean1.setAdjustFactor(checkNull(String.valueOf(empData[i][5])));
					bean1.setApprFinalScore(checkNull(String.valueOf(empData[i][6])));
					bean1.setTemplateCode(checkNull(String.valueOf(empData[i][7])));
					bean1.setFinalizeStatus(checkNull(String.valueOf(empData[i][8])));
					list.add(bean1);

				}
			} // end of if
			
			bean.setEmpList(list);
			}catch (Exception e) {
			//e.printStackTrace();
		}
		try{
			
			String ratingQuery=" SELECT APPR_RATING_TYPE,APPR_SCORE_TO FROM PAS_APPR_QUESTION_RATING_HDR "
						+" LEFT JOIN PAS_APPR_OVERALL_RATING  ON PAS_APPR_OVERALL_RATING.APPR_ID = PAS_APPR_QUESTION_RATING_HDR.APPR_ID "
						+" WHERE PAS_APPR_QUESTION_RATING_HDR.APPR_ID ="+bean.getApprId()
						+" ORDER BY APPR_SCORE_TO DESC ";
			Object [][] rateObj = getSqlModel().getSingleResult(ratingQuery);
			if(rateObj != null && rateObj.length > 0){
				bean.setRatingType(String.valueOf(rateObj[0][0]));
				bean.setMaxRating(String.valueOf(rateObj[0][1]));
			}
		}catch(Exception e){
			
		}
		
	}
	public boolean save(Object []empCode,Object [] operand,Object [] adjust,Object [] finalScore,String apprCode,Object []templateCode){
		boolean result = false;
		
		Object updateObj[][] = new Object[empCode.length][8];
		
		Object [][] scoreObj =  getSqlModel().getSingleResult(getQuery(4), new Object[]{apprCode});
		logger.info("scoreObj---------------"+scoreObj.length);
		for(int i =0;i<empCode.length;i++){
			
			Object scoreDesc[][] =  getScoreDesc(scoreObj, ""+finalScore[i]);
			
			updateObj[i][0] = adjust[i];
			updateObj[i][1] = operand[i];
			updateObj[i][2] = finalScore[i];
			updateObj[i][3] = String.valueOf(scoreDesc[0][0]);
			updateObj[i][4] = String.valueOf(scoreDesc[0][1]);
			updateObj[i][5] = empCode[i];
			updateObj[i][6] = apprCode;
			updateObj[i][7] = templateCode[i];
		}	
		try{
			result = getSqlModel().singleExecute(getQuery(3), updateObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Object[][] getScoreDesc(Object scoreObj[][], String score){
			
		Object[][] scoreDesc = new Object[1][2];
		if (scoreObj != null && scoreObj.length != 0) {
		for(int i=0;i<scoreObj.length;i++){
				if(Double.parseDouble(score) >= Double.parseDouble(String.valueOf(scoreObj[i][0])))
					{							
						scoreDesc[0][0]=String.valueOf(scoreObj[i][1]);
						scoreDesc[0][1]=String.valueOf(scoreObj[i][2]);
						
						break;
					}
				}
		}
		
		return scoreDesc;
	}
	
	
public void getEmpList1(ScoreBalancer bean,HttpServletRequest request){
		
		try{
			Object [][] appraiser = getSqlModel().getSingleResult(getQuery(2),new Object[]{bean.getApprId()});
	
			Object [][] repData = getSqlModel().getSingleResult(getQuery(1),new Object[]{bean.getApprId()});

			
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = repData.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) repData.length / 20.0;
			java.math.BigDecimal value1 = new java.math.BigDecimal(row);
			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());
			ArrayList<Object> obj = new ArrayList<Object>();
			request.setAttribute("abc", rowCount1);
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals("")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;
				if (To_TOT > repData.length) {
					To_TOT = repData.length;
				}//end of if
				bean.setMyPage("1");
			}//end of if

			else {
				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				}//end of if
				else {
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}//end of else
				if (To_TOT > repData.length) {
					To_TOT = repData.length;
				}//end of if
			}//end of else
			request.setAttribute("xyz", PageNo1);
			
						
			logger.info("empData-----------"+repData.length);
			ArrayList<Object> list=new ArrayList<Object>();
			if(repData != null && repData.length !=0){
				for (int i = 0; i < repData.length; i++) {
					ScoreBalancer bean1 = new ScoreBalancer();
					
					bean1.setEmpId(checkNull(String.valueOf(repData[i][0])));
					bean1.setEmpName(checkNull(String.valueOf(repData[i][1])));
					bean1.setDeptName(checkNull(String.valueOf(repData[i][2])));
					System.out.println("EMP>>>"+repData[i][0]);
					bean1.setAppraiserName(getAppraiser(String.valueOf(repData[i][0]),appraiser));
					bean1.setApprScore(checkNull(String.valueOf(repData[i][3])));
					bean1.setOprand(checkNull(String.valueOf(repData[i][4])));
					bean1.setAdjustFactor(checkNull(String.valueOf(repData[i][5])));
					bean1.setApprFinalScore(checkNull(String.valueOf(repData[i][6])));
					
					list.add(bean1);

				}
			} // end of if
			
			bean.setEmpList(list);
			logger.info("setEmpList-----------"+list.size());
			}catch (Exception e) {
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

	public void finalizeEmpScore(Object[][] updateObj) {
		
		Object [][] scoreObj =  getSqlModel().getSingleResult(getQuery(4), new Object[]{""+updateObj[0][7]});
		
		
		for (int j = 0; j < updateObj.length; j++) {
			
			Object scoreDesc[][] =  getScoreDesc(scoreObj, ""+updateObj[j][3]);
			updateObj[j][4] = scoreDesc[0][0];
			updateObj[j][5] = scoreDesc[0][1];
			
		}
		
		String updateScrQry = " UPDATE PAS_APPR_SCORE SET APPR_SCORE=?,APPR_SCORE_ADJUST=?,APPR_ADJUST_FACTOR = ?, APPR_FINAL_SCORE = ?" 
				+ ", APPR_FINAL_SCORE_VALUE=?, APPR_FINAL_SCORE_DESC = ? ,APPR_SCORE_FINALIZE='Y' "
						    + " WHERE EMP_ID = ? AND APPR_ID = ?";
		
		getSqlModel().singleExecute(updateScrQry,updateObj);
	}
	public String getEmployeeScore(String apprcode,String empCode){
		String message="";
		String query =" SELECT EMP_ID,APPR_SCORE,NVL(APPR_ADJUST_FACTOR,'+'),NVL(APPR_SCORE_ADJUST,0),APPR_FINAL_SCORE " 
					+" FROM PAS_APPR_SCORE WHERE APPR_ID= "+apprcode+" AND EMP_ID ="+empCode;
		Object[][] result = getSqlModel().getSingleResult(query);
		if(result != null && result.length > 0){
			for (int i = 0; i < result[0].length; i++) {
				message += String.valueOf(result[0][i])+"#"; 
			}
		}
		return message;
	}
	
}
