/**
 * 
 */
package org.paradyne.model.PAS;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalQuesAnalysis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author aa0554
 *
 */
public class AppraisalQuesAnalysisModel extends ModelBase {

	public void getReport(HttpServletRequest request,HttpServletResponse response,AppraisalQuesAnalysis bean,String careerLabel){
		String reportName = "Appraisal Question Analysis";
		ReportDataSet rds = new ReportDataSet();
		
		rds.setReportType(bean.getReportType());
		rds.setFileName(reportName);
		rds.setPageSize("A4");
		rds.setPageOrientation("landscape");
		System.out.println("careerLabel==="+careerLabel);
		Object [][]tiltleObj=new Object[1][1];	
		//tiltleObj [0][0]="";
		tiltleObj [0][0]=reportName;
		//tiltleObj [0][2]="";
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		TableDataSet tdsTitle = new TableDataSet();
		tdsTitle.setData(tiltleObj);
		tdsTitle.setCellAlignment(new int[] {1});
		tdsTitle.setCellWidth(new int[] {50});
		tdsTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
		rg.addTableToDoc(tdsTitle);
		
		Object [][]subTiltleObj=new Object[2][1];	
		if(!bean.getSectionId().equals("")){
			subTiltleObj=new Object[3][1];
			subTiltleObj [2][0]="Section : "+bean.getSectionName();
		}
		subTiltleObj [0][0]="Appraisal Code : "+bean.getApprCode();
		subTiltleObj [1][0]="Question : "+bean.getQuestionDesc();
		
		TableDataSet tdsSubTitle = new TableDataSet();
		tdsSubTitle.setData(subTiltleObj);
		tdsSubTitle.setCellAlignment(new int[] {0});
		tdsSubTitle.setCellWidth(new int[] { 15 });
		tdsSubTitle.setBlankRowsAbove(1);
		tdsSubTitle.setBodyFontDetails(Font.HELVETICA, 9, Font.BOLD, new Color(0,0,0));
		rg.addTableToDoc(tdsSubTitle);
		
		String sectionCommentQuery="SELECT EM.EMP_TOKEN,EM.EMP_FNAME||' '||EM.EMP_MNAME||' '||EM.EMP_LNAME AS EMPNAME,APPR_PHASE_NAME,EV.EMP_FNAME||' '||EV.EMP_MNAME||' '||EV.EMP_LNAME,"
					+" NVL(APPR_SECTION_NAME,''),"
					+" DECODE (APPR_QUES_RATING,'-1','',APPR_QUES_RATING),DECODE (APPR_QUES_COMMENTS,'-1','',APPR_QUES_COMMENTS) FROM PAS_APPR_COMMENTS"
					+" INNER JOIN HRMS_EMP_OFFC EM ON (EM.EMP_ID=APPR_EMP_ID)"
					+" INNER JOIN HRMS_EMP_OFFC EV ON (EV.EMP_ID=APPR_EVALUATOR_CODE)"
					+" INNER JOIN PAS_APPR_SECTION_HDR ON (PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID )"
					+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)"
					+" WHERE  PAS_APPR_COMMENTS.APPR_ID="+bean.getApprId();
		if(!bean.getQuestionCode().equals("")){
			sectionCommentQuery+=" AND APPR_QUESTION_CODE="+bean.getQuestionCode();
		}
		if(!bean.getSectionId().equals("")){
			sectionCommentQuery+=" AND PAS_APPR_COMMENTS.APPR_SECTION_ID="+bean.getSectionId();
		}
		sectionCommentQuery+=" ORDER BY EMPNAME ,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL ";
		Object [][]sectionCommentObj=getSqlModel().getSingleResult(sectionCommentQuery);
		
		if(bean.getSectionId().equals("")){
		String trnCommentsQuery="SELECT EM.EMP_TOKEN,EM.EMP_FNAME||' '||EM.EMP_MNAME||' '||EM.EMP_LNAME AS EMPNAME,APPR_PHASE_NAME,EV.EMP_FNAME||' '||EV.EMP_MNAME||' '||EV.EMP_LNAME,"
					+" 'Training',"
					+" 'NA',APPR_TRNRECOM_COMMENT FROM PAS_APPR_TRN_RECOMMEND_COMMENT "
					+" INNER JOIN HRMS_EMP_OFFC EM ON (EM.EMP_ID=APPR_EMP_ID)"
					+" LEFT JOIN HRMS_EMP_OFFC EV ON (EV.EMP_ID=APPR_APPRAISER_ID)"
					+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_PHASE_ID)"
					+" WHERE APPR_QUESTION_CODE="+bean.getQuestionCode()+" AND PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_ID="+bean.getApprId()+" ORDER BY EMPNAME,APPR_PHASE_ORDER ";
		Object [][]trnCommentsObj=getSqlModel().getSingleResult(trnCommentsQuery);
		
		String careerCommentsQuery="SELECT EM.EMP_TOKEN,EM.EMP_FNAME||' '||EM.EMP_MNAME||' '||EM.EMP_LNAME AS EMPNAME,APPR_PHASE_NAME,EV.EMP_FNAME||' '||EV.EMP_MNAME||' '||EV.EMP_LNAME,"
					+" '"+careerLabel+"',"
					+" 'NA',APPR_CAREER_COMMENT FROM PAS_APPR_CAREER_COMMENT"
					+" INNER JOIN HRMS_EMP_OFFC EM ON (EM.EMP_ID=APPR_EMP_ID)"
					+" LEFT JOIN HRMS_EMP_OFFC EV ON (EV.EMP_ID=APPR_APPRAISER_ID)"
					+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(APPR_PHASE_ID=APPR_PHASE)"
					+" WHERE APPR_QUESTION_CODE="+bean.getQuestionCode()+" AND PAS_APPR_CAREER_COMMENT.APPR_ID="+bean.getApprId()+" ORDER BY EMPNAME,APPR_PHASE_ORDER ";
		Object [][]careerCommentsObj=getSqlModel().getSingleResult(careerCommentsQuery);

		sectionCommentObj= Utility.joinArrays(sectionCommentObj, trnCommentsObj, 1, 0);
		
		sectionCommentObj= Utility.joinArrays(sectionCommentObj, careerCommentsObj, 1, 0);
		}
		
		String colName[]={"Employee Id","Employee Name","Appraisal Phase","Appraiser Name","Section Name","Question Rating","Question Comments"};
		int cellwidth[]={15, 25, 15, 25, 15, 10, 35};
		int cellalign[]={0, 0, 0, 0, 0, 1, 0};
		if(sectionCommentObj.length>0 && sectionCommentObj !=null ){
		TableDataSet tdsFinalTable = new TableDataSet();
		tdsFinalTable.setData(sectionCommentObj);
		tdsFinalTable.setCellAlignment(cellalign);
		tdsFinalTable.setCellWidth(cellwidth);
		tdsFinalTable.setHeader(colName);
		tdsFinalTable.setHeaderBGColor(new Color(225,225,225));
		tdsFinalTable.setBorder(true);
		tdsFinalTable.setBlankRowsAbove(1);
		rg.addTableToDoc(tdsFinalTable);
		}
		else{
			Object noDataObj[][]=new Object[1][1];
			noDataObj[0][0]="No data to display.";
			TableDataSet noDataTable = new TableDataSet();
			noDataTable.setData(noDataObj);
			noDataTable.setCellAlignment(new int[]{1});
			noDataTable.setCellWidth(new int[]{25});
			noDataTable.setBorder(false);
			noDataTable.setBlankRowsAbove(1);
			rg.addTableToDoc(noDataTable);
		}
		rg.process();
		rg.createReport(response);
	}
	
	public void getReportSectionWise(HttpServletRequest request,HttpServletResponse response,AppraisalQuesAnalysis bean,String careerLabel){
		String reportName = "Appraisal Question Analysis";
		ReportDataSet rds = new ReportDataSet();
		
		rds.setReportType(bean.getReportType());
		rds.setFileName(reportName);
		rds.setPageSize("A4");
		rds.setPageOrientation("landscape");
		System.out.println("careerLabel==="+careerLabel);
		Object [][]tiltleObj=new Object[1][1];	
		//tiltleObj [0][0]="";
		tiltleObj [0][0]=reportName;
		//tiltleObj [0][2]="";
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		TableDataSet tdsTitle = new TableDataSet();
		tdsTitle.setData(tiltleObj);
		tdsTitle.setCellAlignment(new int[] {1});
		tdsTitle.setCellWidth(new int[] {50});
		tdsTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
		rg.addTableToDoc(tdsTitle);
		
		String quesQuery="SELECT DISTINCT APPR_QUESTION_CODE, APPR_QUES_DESC FROM PAS_APPR_COMMENTS " 
				+" INNER JOIN PAS_APPR_QUESTIONNAIRE ON (APPR_QUES_CODE=APPR_QUESTION_CODE)"
				+" WHERE APPR_ID="+bean.getApprId()+" AND APPR_SECTION_ID="+bean.getSectionId();
		Object [][]quesObj=getSqlModel().getSingleResult(quesQuery);
		if(quesObj!=null && quesObj.length>0){
			Object [][]subTiltleObj=new Object[2][1];	
			subTiltleObj [0][0]="Appraisal Code : "+bean.getApprCode();
			subTiltleObj [1][0]="Section : "+bean.getSectionName();
			
			TableDataSet tdsSubTitle = new TableDataSet();
			tdsSubTitle.setData(subTiltleObj);
			tdsSubTitle.setCellAlignment(new int[] {0});
			tdsSubTitle.setCellWidth(new int[] { 15 });
			tdsSubTitle.setBlankRowsAbove(1);
			tdsSubTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD, new Color(0,0,0));
			rg.addTableToDoc(tdsSubTitle);
			for (int i = 0; i < quesObj.length; i++) {
				
				
				
				String sectionCommentQuery="SELECT EM.EMP_TOKEN,EM.EMP_FNAME||' '||EM.EMP_MNAME||' '||EM.EMP_LNAME AS EMPNAME,APPR_PHASE_NAME,EV.EMP_FNAME||' '||EV.EMP_MNAME||' '||EV.EMP_LNAME,"
							//+" NVL(APPR_SECTION_NAME,''),"
							+" DECODE (APPR_QUES_RATING,'-1','',APPR_QUES_RATING),DECODE (APPR_QUES_COMMENTS,'-1','',APPR_QUES_COMMENTS) FROM PAS_APPR_COMMENTS"
							+" INNER JOIN HRMS_EMP_OFFC EM ON (EM.EMP_ID=APPR_EMP_ID)"
							+" INNER JOIN HRMS_EMP_OFFC EV ON (EV.EMP_ID=APPR_EVALUATOR_CODE)"
							//+" INNER JOIN PAS_APPR_SECTION_HDR ON (PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID )"
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)"
							+" WHERE  PAS_APPR_COMMENTS.APPR_ID="+bean.getApprId()+ "AND APPR_QUESTION_CODE="+String.valueOf(quesObj[i][0])
							+" AND PAS_APPR_COMMENTS.APPR_SECTION_ID="+bean.getSectionId()
							+" ORDER BY EMPNAME ,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL";
				
				Object [][]sectionCommentObj=getSqlModel().getSingleResult(sectionCommentQuery);
				
							
				String colName[]={"Employee Id","Employee Name","Appraisal Phase","Appraiser Name","Question Rating","Question Comments"};
				int cellwidth[]={15, 25, 15, 25, 10, 35};
				int cellalign[]={0, 0, 0, 0, 1, 0};
				if(sectionCommentObj.length>0 && sectionCommentObj !=null ){
				
					Object [][]quesTiltleObj=new Object[1][1];	
					quesTiltleObj [0][0]="Question : "+String.valueOf(quesObj[i][1]);
					
				TableDataSet tdsQuesTitle = new TableDataSet();
				tdsQuesTitle.setData(quesTiltleObj);
				tdsQuesTitle.setCellAlignment(new int[] {0});
				tdsQuesTitle.setCellWidth(new int[] { 15 });
				tdsQuesTitle.setBlankRowsAbove(1);
				tdsQuesTitle.setBodyFontDetails(Font.HELVETICA, 9, Font.BOLD, new Color(0,0,0));
				rg.addTableToDoc(tdsQuesTitle);
				
				
				TableDataSet tdsFinalTable = new TableDataSet();
				tdsFinalTable.setData(sectionCommentObj);
				tdsFinalTable.setCellAlignment(cellalign);
				tdsFinalTable.setCellWidth(cellwidth);
				tdsFinalTable.setHeader(colName);
				tdsFinalTable.setHeaderBGColor(new Color(225,225,225));
				tdsFinalTable.setBorder(true);
				tdsFinalTable.setBlankRowsAbove(1);
				rg.addTableToDoc(tdsFinalTable);
				
				}
				else{
					
				}
			}
		}else{
			Object noDataObj[][]=new Object[1][1];
			noDataObj[0][0]="No data to display.";
			TableDataSet noDataTable = new TableDataSet();
			noDataTable.setData(noDataObj);
			noDataTable.setCellAlignment(new int[]{1});
			noDataTable.setCellWidth(new int[]{25});
			noDataTable.setBorder(false);
			noDataTable.setBlankRowsAbove(1);
			rg.addTableToDoc(noDataTable);
		}
		
		rg.process();
		rg.createReport(response);
	}
}
