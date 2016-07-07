package org.paradyne.model.PAS;

import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.PAS.AppraisalSummaryReport;
import org.paradyne.bean.PAS.Competency.MyTeamCompetencyReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 * @author AA0554
 *
 */
public class AppraisalSummaryReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraisalSummaryReportModel.class);

	public void getAppraisalDtls(AppraisalSummaryReport bean){
		
		String sql="SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),"
				  +"TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR 	WHERE APPR_ID="+bean.getApprId();
		Object data[][]=getSqlModel().getSingleResult(sql);
		
		if(data!=null && data.length>0){
			bean.setApprFrom(String.valueOf(data[0][1]));
			bean.setApprTo(String.valueOf(data[0][2]));
		}
		
	}
	
	public void getReport1(AppraisalSummaryReport bean,HttpServletRequest request, HttpServletResponse response){
		
		ReportGenerator rg = new ReportGenerator("Pdf","Appraisal Summary Report","A4");
		rg.addFormatedText("Appraisal Summary Report", 6, 0, 1, 0);
		
		Object data[][] = new Object[3][2];
		data[0][0] = "Appraisal Code : ";
		data[0][1] = ""+bean.getApprCode();
		data[1][0] = "Appraisal Start Date : ";
		data[1][1] = ""+bean.getApprFrom();
		data[2][0] = "Appraisal End Date : ";
		data[2][1] = ""+bean.getApprTo();
		int width[] = {3,30};
		int align[] = {0,0};
		
		rg.tableBodyNoBorder(data, width, align);
		Object phases[][]=getCalendarPhases(bean);
				
		String[]colNames = new String[10+phases.length];		
		int[]cellwidth=new int[10+phases.length];//{10,10,10,10,10,10,10,10};
		int[]allignment=new int[10+phases.length];//{0,0,0,0,0,0,2,0};
		
		colNames[0] = "Sr.No";
		colNames[1] = "Emp No";
		colNames[2] =  "Emp Name";
		colNames[3] =  "Department";
		colNames[4] =  "Branch";
		colNames[5] =  "Designation";
		
		cellwidth[0] = 5;
		cellwidth[1] = 10;
		cellwidth[2] = 25;
		cellwidth[3] = 25;
		cellwidth[4] = 25;
		cellwidth[5] = 25;
		
		allignment[0] = 0;
		allignment[1] = 0;
		allignment[2] = 0;
		allignment[3] = 0;
		allignment[4] = 0;
		allignment[5] = 0;
		
		int index=6;
		for(int i=0;i<phases.length;i++){
			if(i>0){//For phases other than self
				String sections = getPhaseSection(String.valueOf(phases[i][2]),bean.getApprId(),bean.getTemplateId());
				colNames[index] = String.valueOf(phases[i][0])+"\n"+sections;
				cellwidth[index] = 10;
				allignment[index] = 1;
				index++;
			}if(i==0){
				colNames[index] = String.valueOf(phases[i][0]);
				cellwidth[index] = 10;
				allignment[index] = 1;
				index++;
			}
		}
		cellwidth[colNames.length-4] = 8;
		cellwidth[colNames.length-3] = 10;
		cellwidth[colNames.length-2] = 10;
		cellwidth[colNames.length-1] = 10;
		
		allignment[colNames.length-4] = 2;
		allignment[colNames.length-3] = 2;
		allignment[colNames.length-2] = 2;
		allignment[colNames.length-1] = 0;
		
		colNames[colNames.length-4] =  "Actual Score";
		colNames[colNames.length-3] =  "Adjusted Score";
		colNames[colNames.length-2] =  "Final Score";
		colNames[colNames.length-1] =  "Final Rating";
				
		//2.Employees along with name/division/dept/branch/designation/phases/score/final rating
		String whereClause=" ";
		
		String empSql=" SELECT PAS_APPR_SCORE.EMP_ID,EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DEPT.DEPT_NAME,' '), "
		+ " NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_RANK.RANK_NAME,' '), ";
		if(phases!=null && phases.length>0){
			for(int i=0;i<phases.length;i++){
				empSql+="'',";
			}			
		}
		empSql+=" APPR_SCORE,APPR_ADJUST_FACTOR ||''|| NVL(APPR_SCORE_ADJUST,0),NVL(APPR_FINAL_SCORE,0),APPR_FINAL_SCORE_VALUE FROM PAS_APPR_SCORE "
		+" INNER JOIN PAS_APPR_TEMPLATE ON(PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID=PAS_APPR_SCORE.TEMPLATE_CODE)"
		+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID ) "
		+" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
		+" LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
		+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
		+" LEFT JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_SCORE.APPR_ID)"
		+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID	= HRMS_EMP_OFFC.EMP_DIV)"		
		+" WHERE APPR_ID = "+bean.getApprId() +" AND TEMPLATE_CODE="+bean.getTemplateId();
		
		if(!bean.getDivision().equals("")){//If division selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_DIV="+bean.getDivId();
		}if(!bean.getBranch().equals("")){//If branch selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
		}if(!bean.getDept().equals("")){//If department selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptId();
		}if(!bean.getDesg().equals("")){//If designation selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_RANK="+bean.getDesgId();
		}
		empSql+=whereClause+" ORDER BY ROWNUM";
		Object empData[][] = getSqlModel().getSingleResult(empSql);
		Object finalData[][] = null; 
		if(empData!=null && empData.length>0){
			
			for(int j=0;j<empData.length;j++){	
				index=6;
				String empTmplSql="SELECT DISTINCT PAS_APPR_EMP_GRP_HDR.APPR_TEMPLATE_ID,APPR_INSTRUCTION_FLAG FROM PAS_APPR_EMP_GRP_DTL "
					+" LEFT JOIN PAS_APPR_EMP_GRP_HDR ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID AND PAS_APPR_EMP_GRP_HDR.APPR_ID ="+bean.getApprId()+") "
					+" LEFT JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID) "
					+" LEFT JOIN PAS_APPR_TEMPLATE ON (PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID) "
					+" WHERE APPR_EMP_GRP_EMPID = "+empData[j][0]+" AND PAS_APPR_EMP_GRP_HDR.APPR_ID ="+bean.getApprId();
				
				for(int i=0;i<phases.length;i++){
						if(i==0){//Self Phase
							empData[j][index++] = "NA";
						}else{//Other Phase	
								empData[j][index++] =  getOtherPhaseSectionalScore(bean.getApprId(),String.valueOf(empData[j][0]),String.valueOf(phases[i][2]),bean.getTemplateId());
						}
				}
				empData[j][0] = (j+1);
			}
			rg.tableBody(colNames, empData, cellwidth,allignment);
		}else{
			rg.addFormatedText("No records available for the selected calendar", 0, 0, 0, 0);
		}
		rg.createReport(response);
	}
	
	public String getOtherPhaseSectionalScore(String apprId,String empId,String phaseId,String templateId){
		String sql="SELECT '',APPR_SECTION_ID FROM PAS_APPR_SECTION_DTL "
			  +" WHERE APPR_ID="+apprId+" AND APPR_TEMPLATE_ID="+templateId+" AND APPR_PHASE_ID="+phaseId+"  ORDER BY APPR_SECTION_ID ";//AND APPR_SECTION_VISIBILITY='Y'
		Object secData[][] = getSqlModel().getSingleResult(sql);
		String PHASE_SCORE = "";
		if(secData!=null && secData.length>0){
			for(int j=0;j<secData.length;j++){
				Object [][] sectionScoreObject = null;
				try{	
						String sectionScoreQuery= " SELECT SUM(APPR_QUES_WEIGHTAGE),NVL(ROUND(SUM(APPR_QUES_PERCENT_WT)),'0') FROM PAS_APPR_COMMENTS " 
								+" WHERE APPR_ID = "+apprId+" AND APPR_SECTION_ID = "+secData[j][1]+" AND APPR_EMP_ID = "+empId+" AND APPR_PHASE_ID = "+phaseId+" AND "
								+" APPR_EVALUATOR_LEVEL = (SELECT MAX(APPR_EVALUATOR_LEVEL) FROM PAS_APPR_COMMENTS  "
								+"	WHERE APPR_ID ="+apprId+" AND APPR_SECTION_ID="+secData[j][1]+" AND APPR_EMP_ID="+empId+" AND APPR_PHASE_ID = "+phaseId+")";
						sectionScoreObject = getSqlModel().getSingleResult(sectionScoreQuery);
				}catch(Exception e){
					e.printStackTrace();
				}
					if(j!=0){	//OTHER SECTIONS						
						if(sectionScoreObject!=null){
							PHASE_SCORE+="+"+sectionScoreObject [0][1];
						}
					}
					else{//FIRST SECTION
						if(sectionScoreObject!=null){
							PHASE_SCORE+="("+sectionScoreObject [0][1];
						}
					}
			}
			PHASE_SCORE+=")";
		}
		return PHASE_SCORE;
	}
	
	public String getPhaseSection(String phaseId,String apprId,String templateId){
		
		String sql="SELECT  PAS_APPR_SECTION_HDR.APPR_SECTION_ID ,APPR_SECTION_NAME,APPR_SECTION_VISIBILITY FROM PAS_APPR_SECTION_HDR" 
				  +" INNER JOIN PAS_APPR_SECTION_DTL ON(PAS_APPR_SECTION_DTL.APPR_ID=PAS_APPR_SECTION_HDR.APPR_ID AND"
				  +" PAS_APPR_SECTION_DTL.APPR_TEMPLATE_ID=PAS_APPR_SECTION_HDr.APPR_TEMPLATE_ID AND"
				  +" PAS_APPR_SECTION_DTL.APPR_SECTION_ID = PAS_APPR_SECTION_HDR.APPR_SECTION_ID)"
				  +" WHERE PAS_APPR_SECTION_HDR.APPR_ID="+apprId 
				  +" AND APPR_PHASE_ID="+phaseId 
				  +" AND PAS_APPR_SECTION_HDR.APPR_TEMPLATE_ID="+templateId
				  +" AND APPR_SECTION_VISIBILITY='Y'"
				  +" ORDER BY APPR_SECTION_ORDER";
		
		Object secData[][] = getSqlModel().getSingleResult(sql);
		String secDtls="";
		if(secData!=null && secData.length>0){
			for(int i=0;i<secData.length;i++){
				if(i==0){//First section
					secDtls+="("+String .valueOf(secData[i][1]);
					
				}else{
					secDtls+="+"+String .valueOf(secData[i][1]);
				}
				if(i==secData.length-1){//Last section
					secDtls+=")";
				}
			}
		}
		return secDtls;
	}
	
	public Object [][] getCalendarPhases(AppraisalSummaryReport bean){
		String sql="SELECT APPR_PHASE_NAME,APPR_PHASEWISE_RATING,APPR_PHASE_ID	 FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+bean.getApprId()+" AND APPR_PHASE_STATUS='A' ORDER BY APPR_PHASE_ORDER	";
		Object data[][] = getSqlModel().getSingleResult(sql);
		return data;
	}
	
    public void getReport(AppraisalSummaryReport bean,HttpServletRequest request, HttpServletResponse response){
		
		ReportGenerator rg = new ReportGenerator("Pdf","Appraisal Summary Report","A4");
		rg.addFormatedText("Appraisal Summary Report", 6, 0, 1, 0);
		
		Object data[][] = new Object[3][2];
		data[0][0] = "Appraisal Code : ";
		data[0][1] = ""+bean.getApprCode();
		data[1][0] = "Appraisal Start Date : ";
		data[1][1] = ""+bean.getApprFrom();
		data[2][0] = "Appraisal End Date : ";
		data[2][1] = ""+bean.getApprTo();
		int width[] = {3,30};
		int align[] = {0,0};
		
		rg.tableBodyNoBorder(data, width, align);
		Object phases[][]=getCalendarPhases(bean);
				
		String[]colNames = new String[10+phases.length];		
		int[]cellwidth=new int[10+phases.length];//{10,10,10,10,10,10,10,10};
		int[]allignment=new int[10+phases.length];//{0,0,0,0,0,0,2,0};
		
		colNames[0] = "Sr.No";
		colNames[1] = "Emp No";
		colNames[2] =  "Emp Name";
		colNames[3] =  "Department";
		colNames[4] =  "Branch";
		colNames[5] =  "Designation";
		
		cellwidth[0] = 5;
		cellwidth[1] = 10;
		cellwidth[2] = 20;
		cellwidth[3] = 20;
		cellwidth[4] = 20;
		cellwidth[5] = 20;
		
		allignment[0] = 0;
		allignment[1] = 0;
		allignment[2] = 0;
		allignment[3] = 0;
		allignment[4] = 0;
		allignment[5] = 0;
		
		int index=6;
		for(int i=0;i<phases.length;i++){
				colNames[index] = String.valueOf(phases[i][0]);
				cellwidth[index] = 10;
				allignment[index] = 1;
				index++;
		}
		cellwidth[colNames.length-4] = 10;
		cellwidth[colNames.length-3] = 10;
		cellwidth[colNames.length-2] = 10;
		cellwidth[colNames.length-1] = 10;
		
		allignment[colNames.length-4] = 1;
		allignment[colNames.length-3] = 1;
		allignment[colNames.length-2] = 1;
		allignment[colNames.length-1] = 0;
		
		colNames[colNames.length-4] =  "Actual Score";
		colNames[colNames.length-3] =  "Adjustment Factor";
		colNames[colNames.length-2] =  "Final Score";
		colNames[colNames.length-1] =  "Score Value";
				
		//2.Employees along with name/division/dept/branch/designation/phases/score/final rating
		String whereClause=" ";
		
		String empSql=" SELECT PAS_APPR_SCORE.EMP_ID,EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DEPT.DEPT_NAME,' '), "
				+ " NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_RANK.RANK_NAME,' '), ";
		
		if(phases!=null && phases.length>0){
			for(int i=0;i<phases.length;i++){
				empSql+="'',";
			}			
		}
		
		empSql+=" APPR_SCORE,APPR_ADJUST_FACTOR ||''|| NVL(APPR_SCORE_ADJUST,0),NVL(APPR_FINAL_SCORE,0),APPR_FINAL_SCORE_VALUE,PAS_APPR_SCORE.EMP_ID FROM PAS_APPR_SCORE "
				+" INNER JOIN PAS_APPR_TEMPLATE ON(PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID=PAS_APPR_SCORE.TEMPLATE_CODE)"
				+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID ) "
				+" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
				+" LEFT JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_SCORE.APPR_ID)"
				+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID	= HRMS_EMP_OFFC.EMP_DIV)"		
				+" WHERE APPR_ID = "+bean.getApprId() +" AND TEMPLATE_CODE="+bean.getTemplateId();
		
		if(!bean.getDivision().equals("")){//If division selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_DIV="+bean.getDivId();
		}if(!bean.getBranch().equals("")){//If branch selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
		}if(!bean.getDept().equals("")){//If department selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptId();
		}if(!bean.getDesg().equals("")){//If designation selected
			whereClause+=" AND HRMS_EMP_OFFC.EMP_RANK="+bean.getDesgId();
		}
		empSql+=whereClause+" ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) ";
		
		String phaseScore = " SELECT PHASE_FINAL_SCORE,PHASE_ID,PAS_APPR_TRACKING.EMP_ID " 
				+" FROM  PAS_APPR_TRACKING " 
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRACKING.EMP_ID ) "
				+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_TRACKING.PHASE_ID)"
				+" WHERE PAS_APPR_TRACKING.APPR_ID = "+bean.getApprId() +" AND TEMPLATE_CODE = "+bean.getTemplateId()
				+whereClause
				+" ORDER BY EMP_ID,APPR_PHASE_ORDER,PHASE_APPRAISER_LEVEL ";
		HashMap<String,Object[][]> phaseMap  = getSqlModel().getSingleResultMap(phaseScore, 2,2);
		
		Object empData[][] = getSqlModel().getSingleResult(empSql);
		if(empData!=null && empData.length>0){
			for(int j=0;j<empData.length;j++){	
				index=6;
				Object [][] phaseObj = phaseMap.get(String.valueOf(empData[j][empData[0].length - 1]));
				if(phaseObj != null && phaseObj.length > 0){
					for(int i=0;i<phases.length;i++){
						String score="";
						boolean res= false;
						for(int k=0;k<phaseObj.length;k++){
							if(String.valueOf(phases[i][2]).equals(String.valueOf(phaseObj[k][1]))){
								if(res){
									score += ","+String.valueOf(phaseObj[k][0]);
								}else{
									score += String.valueOf(phaseObj[k][0]);
									res= true;
								}
							}
						}
						empData[j][index++] =score;
					}
				}
				empData[j][0] = (j+1);
			}
			rg.tableBody(colNames, empData, cellwidth,allignment);
		}else{
			rg.addFormatedText("No records available for the selected calendar", 0, 0, 0, 0);
		}
		rg.createReport(response);
	}
    
	public void getReport(AppraisalSummaryReport bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {
		
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
			String reportType = bean.getReport();
			String title = "Appraisal Summary Report";
			ReportDataSet rds = new ReportDataSet();
			String fileName = " Appraisal Summary Report_"+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(title);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportType(reportType);
			rds.setTotalColumns(8);
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{
				//logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "/pas/AppraisalSummaryReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			String filters = "";
			Object phases[][]=getCalendarPhases(bean);
			
			filters +="Appraisal Code :" +bean.getApprCode();
			filters +="\n Appraisal Start Date : "+bean.getApprFrom();
			filters +="\n Appraisal End Date : "+bean.getApprTo();
			if(!bean.getDivision().equals("")){//If division selected
				filters +="\n Division : "+bean.getDivision();
			}if(!bean.getBranch().equals("")){//If branch selected
				filters +="\n Branch  : "+bean.getBranch();
			}if(!bean.getDept().equals("")){//If department selected
				filters +="\n Department : "+bean.getDept();
			}if(!bean.getDesg().equals("")){//If designation selected
				filters +="\n Designation : "+bean.getDesg();
			}
			int width[] = {3,30};
			int align[] = {0,0};
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setBodyFontStyle(0);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{10+phases.length});
			filterData.setCellNoWrap(new boolean[]{true});
			filterData.setBlankRowsBelow(1);
			filterData.setBorder(false);
			rg.addTableToDoc(filterData);
			
			
					
			String[]colNames = new String[10+phases.length];		
			int[]cellwidth=new int[10+phases.length];//{10,10,10,10,10,10,10,10};
			int[]allignment=new int[10+phases.length];//{0,0,0,0,0,0,2,0};
			
			colNames[0] = "Sr.No";
			colNames[1] = "Emp No";
			colNames[2] =  "Emp Name";
			colNames[3] =  "Department";
			colNames[4] =  "Branch";
			colNames[5] =  "Designation";
			
			cellwidth[0] = 5;
			cellwidth[1] = 10;
			cellwidth[2] = 20;
			cellwidth[3] = 20;
			cellwidth[4] = 20;
			cellwidth[5] = 20;
			
			allignment[0] = 0;
			allignment[1] = 0;
			allignment[2] = 0;
			allignment[3] = 0;
			allignment[4] = 0;
			allignment[5] = 0;
			
			int index=6;
			for(int i=0;i<phases.length;i++){
					colNames[index] = String.valueOf(phases[i][0]);
					cellwidth[index] = 10;
					allignment[index] = 1;
					index++;
			}
			cellwidth[colNames.length-4] = 10;
			cellwidth[colNames.length-3] = 10;
			cellwidth[colNames.length-2] = 10;
			cellwidth[colNames.length-1] = 10;
			
			allignment[colNames.length-4] = 1;
			allignment[colNames.length-3] = 1;
			allignment[colNames.length-2] = 1;
			allignment[colNames.length-1] = 0;
			
			colNames[colNames.length-4] =  "Actual Score";
			colNames[colNames.length-3] =  "Adjustment Factor";
			colNames[colNames.length-2] =  "Final Score";
			colNames[colNames.length-1] =  "Score Value";
					
			//2.Employees along with name/division/dept/branch/designation/phases/score/final rating
			String whereClause=" ";
			
			String empSql=" SELECT PAS_APPR_SCORE.EMP_ID,EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DEPT.DEPT_NAME,' '), "
					+ " NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_RANK.RANK_NAME,' '), ";
			
			if(phases!=null && phases.length>0){
				for(int i=0;i<phases.length;i++){
					empSql+="'',";
				}			
			}
			
			empSql+=" APPR_SCORE,APPR_ADJUST_FACTOR ||''|| NVL(APPR_SCORE_ADJUST,0),NVL(APPR_FINAL_SCORE,0),APPR_FINAL_SCORE_VALUE,PAS_APPR_SCORE.EMP_ID FROM PAS_APPR_SCORE "
					+" INNER JOIN PAS_APPR_TEMPLATE ON(PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID=PAS_APPR_SCORE.TEMPLATE_CODE)"
					+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID ) "
					+" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+" LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
					+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+" LEFT JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_SCORE.APPR_ID)"
					+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID	= HRMS_EMP_OFFC.EMP_DIV)"		
					+" WHERE APPR_ID = "+bean.getApprId() +" AND TEMPLATE_CODE="+bean.getTemplateId();
			
			if(!bean.getDivision().equals("")){//If division selected
				whereClause+=" AND HRMS_EMP_OFFC.EMP_DIV="+bean.getDivId();
			}if(!bean.getBranch().equals("")){//If branch selected
				whereClause+=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}if(!bean.getDept().equals("")){//If department selected
				whereClause+=" AND HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptId();
			}if(!bean.getDesg().equals("")){//If designation selected
				whereClause+=" AND HRMS_EMP_OFFC.EMP_RANK="+bean.getDesgId();
			}
			empSql+=whereClause+" ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) ";
			
			String phaseScore = " SELECT PHASE_FINAL_SCORE,PHASE_ID,PAS_APPR_TRACKING.EMP_ID " 
					+" FROM  PAS_APPR_TRACKING " 
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRACKING.EMP_ID ) "
					+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_TRACKING.PHASE_ID)"
					+" WHERE PAS_APPR_TRACKING.APPR_ID = "+bean.getApprId() +" AND TEMPLATE_CODE = "+bean.getTemplateId()
					+whereClause
					+" ORDER BY EMP_ID,APPR_PHASE_ORDER,PHASE_APPRAISER_LEVEL ";
			HashMap<String,Object[][]> phaseMap  = getSqlModel().getSingleResultMap(phaseScore, 2,2);
			
			Object empData[][] = getSqlModel().getSingleResult(empSql);
			if(empData!=null && empData.length>0){
				for(int j=0;j<empData.length;j++){	
					index=6;
					Object [][] phaseObj = phaseMap.get(String.valueOf(empData[j][empData[0].length - 1]));
					if(phaseObj != null && phaseObj.length > 0){
						for(int i=0;i<phases.length;i++){
							String score="";
							boolean res= false;
							for(int k=0;k<phaseObj.length;k++){
								if(String.valueOf(phases[i][2]).equals(String.valueOf(phaseObj[k][1]))){
									if(res){
										score += ","+String.valueOf(phaseObj[k][0]);
									}else{
										score += String.valueOf(phaseObj[k][0]);
										res= true;
									}
								}
							}
							empData[j][index++] =score;
						}
					}
					empData[j][0] = (j+1);
				}
			}	
			
			
			if(empData!=null && empData.length>0){
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(colNames);
				tdstable.setData(empData);// data object from query
				tdstable.setHeaderLines(true);
				tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
				tdstable.setCellAlignment(allignment);
				tdstable.setCellWidth(cellwidth); 
				tdstable.setHeaderBorderDetail(3);	
				tdstable.setBorderDetail(3);
				tdstable.setBorder(true); 
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeaderTable(true);   
				tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				}else{
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";
					
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
							Font.BOLD, new BaseColor(0, 0, 0));*/
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}
				
				
				rg.process();
				if(reportPath.equals("")){
				rg.createReport(response);
				}
				else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
	

