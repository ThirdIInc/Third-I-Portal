/**
 * 
 */
package org.paradyne.model.PAS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalConfigReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 * @author Pankaj_Jain
 * 
 */
public class AppraisalConfigReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppraisalConfigReportModel.class);

	/**
	 * Generates group wise report based on appraisal and its template. It
	 * generates the report for each group on each page.
	 * 
	 * @param request
	 * @param response
	 * @param apprCode
	 * @param tempCode
	 */
	public void generateConfigReport(HttpServletRequest request,
			HttpServletResponse response, String apprCode, String tempCode,
			String tempName, String groupCode) {
		String s = "\n Appraisal Configuration Report";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
		//rg.setFName("Appraisal Configuration Report");
		rg.addFormatedText(s, 6, 0, 1, 0);
		rg.addText("Date: " + dateData[0][0], 0, 2, 0);

		try {
			Object[][] apprInfoObj = new Object[1][3];
			rg.addFormatedText("\n", 6, 0, 0, 0);
			apprInfoObj[0][0] = "Appraisal Code: - "
					+ request.getParameter("apprName");
			apprInfoObj[0][1] = "Appraisal Start Date: - "
					+ request.getParameter("startDate");
			apprInfoObj[0][2] = "Appraisal End Date: - "
					+ request.getParameter("endDate");
			int cellWidth[] = { 40, 30, 30 };
			int alignment[] = { 1, 1, 1 };
			rg.tableBodyNoBorder(apprInfoObj, cellWidth, alignment);
		} catch (Exception e) {
		}
		rg.addTableHeader("\nTemplate Definition: - ", 6, 0, 0, true);
		rg.addFormatedText("Template Name : " + tempName, 0, 0, 0, 0);

		String instrQry = " SELECT APPR_INSTRUCTIONS FROM PAS_APPR_TEMPLATE WHERE APPR_ID = "
				+ apprCode + " AND APPR_TEMPLATE_ID = " + tempCode;
		Object[][] instrObj = getSqlModel().getSingleResult(instrQry);

		String ratingQry = " SELECT DISTINCT APPR_RATING_VALU,APPR_RATING_DESC FROM PAS_APPR_QUESTION_RATING_DTL "
				+ " WHERE PAS_APPR_QUESTION_RATING_DTL.APPR_ID = "
				+ apprCode
				+ " ORDER BY APPR_RATING_VALU	";
		Object[][] ratingObj = getSqlModel().getSingleResult(ratingQry);

		if (instrObj == null || instrObj.length < 1) {
			rg.addText("There is no data avialable. So cannot generate the preview.",2, 0, 0);
			rg.createReport(response);
			return;
		}

		rg.addTableHeader("\nAppraisal Instruction : - ", 6, 0, 0,true);
		String ratingScale = "";
		for (int i = 0; i < ratingObj.length; i++)
			ratingScale += ratingObj[i][0] + "-" + ratingObj[i][1] + ",";
		if(!ratingScale.equals(""))
			ratingScale = ratingScale.substring(0, ratingScale.length() - 1);
		rg.addFormatedText("Rating Scale : " + ratingScale, 0, 0, 0, 0);
		rg.addFormatedText("Instruction : "
				+ Utility.checkNull(String.valueOf(instrObj[0][0])), 0, 0, 0, 0);
		rg.addFormatedText("\n", 6, 0, 0, 0);

		String secDtlQry = " SELECT APPR_SECTION_NAME,PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT,APPR_SECTION_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID  FROM PAS_APPR_SECTION_DTL "
				+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_SECTION_DTL.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
				+ " INNER JOIN PAS_APPR_SECTION_HDR ON (PAS_APPR_SECTION_DTL.APPR_SECTION_ID = PAS_APPR_SECTION_HDR.APPR_SECTION_ID) "
				+ " WHERE PAS_APPR_PHASE_CONFIG.APPR_ID = "
				+ apprCode
				+ " AND APPR_PHASE_STATUS = 'A' "
				+ " AND APPR_TEMPLATE_ID = "
				+ tempCode
				+ " ORDER BY APPR_SECTION_ORDER,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
		Object[][] secDtlObj = getSqlModel().getSingleResult(secDtlQry);

		String grpQuery = " SELECT APPR_EMP_GRP_NAME,APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR  WHERE APPR_ID = "
				+ apprCode + " AND APPR_TEMPLATE_ID = " + tempCode;

		if (!groupCode.equals(""))
			grpQuery += " AND APPR_EMP_GRP_ID = " + groupCode;
		grpQuery += " ORDER BY  APPR_EMP_GRP_ID ";
		Object[][] grpObj = getSqlModel().getSingleResult(grpQuery);

		String secStr = "";
		String phaseStr = "";
		int secCount = 0;
		int phaseCount = 0;
		for (int j = 0; j < secDtlObj.length; j++) {
			if (secStr.indexOf(String.valueOf(secDtlObj[j][5])) < 0) {
				secStr += String.valueOf(secDtlObj[j][5]);
				secCount++;
			}
			if (phaseStr.indexOf(String.valueOf(secDtlObj[j][6])) < 0) {
				phaseStr += String.valueOf(secDtlObj[j][6]);
				phaseCount++;
			}
		}

		Object[][] dataObj = new Object[secCount * 3][phaseCount + 2];
		int k = 0, z = 0, c = 0;
		for (int i = 0; i < secCount * 3; i++) {
			c = k;
			if (i % 3 == 0) {
				dataObj[i][0] = "";
				dataObj[i][1] = "Visibility";
				z = 2;
			} else if (i % 3 == 1) {
				dataObj[i][0] = secDtlObj[k][0];
				dataObj[i][1] = "Rating";
				z = 3;
			} else if (i % 3 == 2) {
				dataObj[i][0] = "";
				dataObj[i][1] = "Comments";
				z = 4;
			}
			for (int j = 0; j < phaseCount; j++)
				dataObj[i][2 + j] = secDtlObj[k++][z];

			if (i % 3 == 2)
				k = c + phaseCount;
			else
				k = c;

		}

		int[] bcellWidth = new int[phaseCount + 2];
		int[] bcellAlign = new int[phaseCount + 2];
		String columns[] = new String[phaseCount + 2];
		columns[0] = "Section Name";
		columns[1] = "Parameter";
		for (int i = 2; i < phaseCount + 2; i++)
			columns[i] = String.valueOf(secDtlObj[i - 2][1]);
		bcellWidth[0] = 25;
		bcellWidth[1] = 20;
		bcellAlign[0] = 0;
		bcellAlign[1] = 0;
		for (int i = 2; i < bcellAlign.length; i++) {
			bcellWidth[i] = 55 / phaseCount;
			bcellAlign[i] = 1;
		}
		rg.addTableHeader("Section Mapping : - ", 1, 0, 0,true);
		rg.addFormatedText("\n", 6, 0, 0, 0);
		rg.tableBody(columns, dataObj, bcellWidth, bcellAlign);
		String grpStr = "";
		rg.addFormatedText("\n", 6, 0, 0, 0);
		rg.addTableHeader("Appraisal Mapping : - ", 1, 0, 0,true);
		if (grpObj != null && grpObj.length > 0) {
			for (int i = 0; i < grpObj.length; i++)
				grpStr += grpObj[i][0] + ",";
			grpStr = grpStr.substring(0, grpStr.length() - 1);
		}
		if (!grpStr.equals("")) {
			rg.addFormatedText("Group Name : " + grpStr, 0, 0, 0, 0);
			rg.addFormatedText(
							"Note : To view the list of employees for the group(s) refer annexure.",
							0, 0, 0, 0);
		} else
			rg.addFormatedText("No Group is defined for this appraisal calendar", 0,
					0, 0, 0);

		String trnDtlsQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
				+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
				+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
				+ apprCode
				+ " AND APPR_TEMPLATE_ID = "
				+ tempCode
				+ " AND APPR_SECTION_TYPE = 'T' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
		Object trnDtlsObj[][] = getSqlModel().getSingleResult(trnDtlsQry);
		Object[][] trnDataObj = null;
		if (trnDtlsObj != null && trnDtlsObj.length > 0) {
			trnDataObj = new Object[3][trnDtlsObj.length + 2];
			trnDataObj[0][0] = "";
			trnDataObj[1][0] = "";
			trnDataObj[2][0] = "";
			trnDataObj[0][1] = "";
			trnDataObj[1][1] = "Visibility";
			trnDataObj[2][1] = "Comments";
			for (int i = 0; i < trnDtlsObj.length; i++)
				trnDataObj[0][i + 2] = String.valueOf(trnDtlsObj[i][0]);

			for (k = 1; k < 3; k++)
				for (int i = 0; i < trnDtlsObj.length; i++)
					trnDataObj[k][i + 2] = trnDtlsObj[i][k];

			columns = new String[trnDtlsObj.length + 1];
			columns[0] = "";

			bcellWidth = new int[trnDtlsObj.length + 2];
			bcellAlign = new int[trnDtlsObj.length + 2];
			bcellWidth[0] = 20;
			bcellAlign[0] = 0;
			bcellWidth[1] = 20;
			bcellAlign[1] = 0;

			for (int i = 0; i < trnDtlsObj.length; i++) {
				bcellWidth[i + 2] = 60 / trnDtlsObj.length;
				bcellAlign[i + 2] = 1;
			}

		}

		trnDtlsQry = " SELECT APPR_QUES_DESC FROM PAS_APPR_TRN_RECOMMEND "
				+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_TRN_RECOMMEND.APPR_QUESTION_CODE) "
				+ " WHERE APPR_QUES_STATUS = 'A' AND APPR_ID = " + apprCode
				+ " AND APPR_TEMPLATE_ID = " + tempCode;
		trnDtlsObj = getSqlModel().getSingleResult(trnDtlsQry);

		rg.addFormatedText("\n", 6, 0, 0, 0);
		rg.addTableHeader("Training Details : - ", 6, 0, 0, true);
		rg.addFormatedText("\t\t\tTraining Recommendation Applicability : ",
				0, 0, 0, 0);
		if (trnDataObj != null && trnDataObj.length > 0)
			rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
		else
			rg
					.addFormatedText(
							"\t\t\t\t Training Recomendation applicability not defined for appraisal calendar",
							0, 0, 0, 0);

		rg.addFormatedText("\t\t\tTraining Question List : ", 0, 0, 0, 0);

		if (trnDtlsObj != null)
			for (int i = 0; i < trnDtlsObj.length; i++) {
				rg.addFormatedText("\t\t\t\t " + (i + 1) + ". "
						+ trnDtlsObj[i][0], 0, 0, 0, 0);
			}
		else
			rg
					.addFormatedText(
							"\t\t\t\t Training question list not defined for appraisal calendar",
							0, 0, 0, 0);
		rg.addFormatedText("\n ", 6, 0, 0, 0);

		String awardQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
				+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
				+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
				+ apprCode
				+ " AND APPR_TEMPLATE_ID = "
				+ tempCode
				+ " AND APPR_SECTION_TYPE = 'A' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
		Object awardObj[][] = getSqlModel().getSingleResult(awardQry);

		rg.addTableHeader("Awards & Recognition : - ", 6, 0, 0, true);
		rg.addFormatedText("\t\t\tAward Nomination Applicability : ", 0, 0,
				0, 0);
		if (awardObj != null && awardObj.length > 0) {
			for (k = 1; k < 3; k++)
				for (int i = 0; i < awardObj.length; i++)
					trnDataObj[k][i + 2] = awardObj[i][k];
			rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
		} else
			rg.addFormatedText(
							"\t\t\t\t Award nomination applicabilty not defined in appraisal calendar",
							0, 0, 0, 0);

		rg.addFormatedText("\t\t\tAward Nomination : ", 0, 0, 0, 0);
		String awardNom = " SELECT APPR_AWARD_FLAG,APPR_REASON_FLAG FROM PAS_APPR_AWD_NOMINATE "
				+ " WHERE APPR_ID = "
				+ apprCode
				+ " AND APPR_TEMPLATE_ID = "
				+ tempCode;
		Object[][] awardNomObj = getSqlModel().getSingleResult(awardNom);

		Object[][] awardStatus = new Object[2][3];
		awardStatus[0][0] = "";
		awardStatus[1][0] = "";
		awardStatus[0][1] = "Nominate an Award";
		awardStatus[0][2] = "Reason for Nomination";
		if (awardNomObj != null && awardNomObj.length > 0) {
			awardStatus[1][1] = awardNomObj[0][0];
			awardStatus[1][2] = awardNomObj[0][1];
			int[] cellWidth = { 40, 30, 30 };
			int[] cellAlign = { 0, 1, 1 };
			rg.tableBodyNoBorder(awardStatus, cellWidth, cellAlign);
		} else
			rg
					.addFormatedText(
							"\t\t\t\t Award nomination not defined in the appraisal calendar",
							0, 0, 0, 0);

		rg.addTableHeader("Disciplinary Action : - ", 6, 0, 0, true);
		rg.addFormatedText("\t\t\t\tDisciplinary Action Applicability: ", 0,
				0, 0, 0);

		String dispQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
				+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
				+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
				+ apprCode
				+ " AND APPR_TEMPLATE_ID = "
				+ tempCode
				+ " AND APPR_SECTION_TYPE = 'D' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
		Object dispObj[][] = getSqlModel().getSingleResult(dispQry);

		if (dispObj != null) {
			for (k = 1; k < 3; k++)
				for (int i = 0; i < dispObj.length; i++)
					trnDataObj[k][i + 2] = dispObj[i][k];
			if (trnDataObj != null && trnDataObj.length > 0)
				rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
			else
				rg
						.addFormatedText(
								"\t\t\t\t Disciplinary action not defined in the apprisal calendar",
								0, 0, 0, 0);
		}

		rg.addTableHeader("Career Progression Details : - ", 6, 0, 0, true);
		rg.addFormatedText("\t\t\tCareer Progression Applicability: ", 0, 0,
				0, 0);

		String carrerQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
				+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
				+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
				+ apprCode
				+ " AND APPR_TEMPLATE_ID = "
				+ tempCode
				+ " AND APPR_SECTION_TYPE = 'C' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
		;
		Object carrerObj[][] = getSqlModel().getSingleResult(carrerQry);
		if (carrerObj != null) {
			for (k = 1; k < 3; k++)
				for (int i = 0; i < carrerObj.length; i++)
					trnDataObj[k][i + 2] = carrerObj[i][k];
			if (trnDataObj != null && trnDataObj.length > 0)
				rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
			else
				rg
						.addFormatedText(
								"\t\t\t\t Carrear progression appicability not defined",
								0, 0, 0, 0);
		}

		rg.addFormatedText("\t\t\tCareer Progression Question List: ", 0, 0,
				0, 0);
		String carrerQsQry = " SELECT APPR_QUES_DESC FROM PAS_APPR_CAREER  "
				+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_CAREER .APPR_QUESTION_CODE) "
				+ " WHERE APPR_QUES_STATUS = 'A' AND APPR_ID = " + apprCode
				+ " AND APPR_TEMPLATE_ID = " + tempCode;
		trnDtlsObj = getSqlModel().getSingleResult(carrerQsQry);

		if (trnDtlsObj != null)
			for (int i = 0; i < trnDtlsObj.length; i++)
				rg.addFormatedText("\t\t\t\t " + (i + 1) + ". "
						+ trnDtlsObj[i][0], 0, 0, 0, 0);
		else
			rg
					.addFormatedText(
							"\t\t\t\t No question list defined in the appraisal calendar for carrer progression",
							0, 0, 0, 0);
		
		rg.pageBreak();
		rg.addTableHeader("Annexure: - ", 6, 0, 0, true);
		String cols[] = {"Sr No","Employee Name","Division","Branch","Department","Designation"}; 
		int []cellWidth={10,20,15,20,20,15};
		int []cellAlign={1,0,0,0,0,0};
		for (int i = 0; i < grpObj.length; i++) {
			rg.addFormatedText(" Group : " + grpObj[i][0], 0, 0, 0, 0);
			rg.addFormatedText("\n", 6, 0, 0, 0);
			String empQry =  " SELECT ROWNUM,EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME,DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME "
				           + " FROM PAS_APPR_EMP_GRP_DTL "  
				           + " INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
				           + " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  "
				           + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "  
				           + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
				           + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				           + " WHERE PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = "+grpObj[i][1]	   
				           + " ORDER BY UPPER(EMPNAME) ";
		   Object[][] empObj = getSqlModel().getSingleResult(empQry);
		   if(empObj != null && empObj.length > 0)
		   {
			   for (int j = 0; j < empObj.length; j++) 
				   empObj[j][0]= j+1;
			   rg.tableBody(cols,empObj, cellWidth, cellAlign);
		   }
		   else
			   rg.addFormatedText("\n No employee has been assigned to this group.", 6, 0, 0, 0);
		   rg.addFormatedText("\n", 6, 0, 0, 0);
		}
		rg.createReport(response);
	}
	public void getReport(AppraisalConfigReport bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {
		
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
			String reportType = bean.getReport();
			String title = "Appraisal Configuration Report";
			ReportDataSet rds = new ReportDataSet();
			String fileName = " Appraisal Configuration Report_"+ Utility.getRandomNumber(1000);
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
				request.setAttribute("action", "/pas/AppraisalConfigReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
			//rg.setFName("Appraisal Configuration Report");
			
			Object[][] dataTitle=new Object[1][1];
			dataTitle[0][0] = "Date: "+String.valueOf(dateData[0][0]);
			TableDataSet dataTable = new TableDataSet();
			dataTable.setData(dataTitle);
			//creditHeaderData.setData(creditheadtitle);
			dataTable.setCellAlignment(new int[]{2});
			dataTable.setCellWidth(new int[]{100});
			dataTable.setBodyFontStyle(0);  
		//	EmployeeData.setCellColSpan(new int[]{5});
			//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			dataTable.setBorderDetail(0);
			dataTable.setBlankRowsBelow(1);
			//creditHeaderData.setBlankRowsBelow(1);
			rg.addTableToDoc(dataTable);

			try {
				Object[][] apprInfoObj = new Object[1][3];
				//rg.addFormatedText("\n", 6, 0, 0, 0);
				apprInfoObj[0][0] = "Appraisal Code: - "
						+ request.getParameter("apprName");
				apprInfoObj[0][1] = "Appraisal Start Date: - "
						+ request.getParameter("startDate");
				apprInfoObj[0][2] = "Appraisal End Date: - "
						+ request.getParameter("endDate");
				int cellWidth[] = { 40, 30, 30 };
				int alignment[] = { 1, 1, 1 };
				//rg.tableBodyNoBorder(apprInfoObj, cellWidth, alignment);
				
				TableDataSet filterData1 = new TableDataSet();
				filterData1.setData(apprInfoObj);
				filterData1.setCellAlignment(alignment);
				filterData1.setBodyFontStyle(0);
				filterData1.setCellWidth(cellWidth);
				filterData1.setCellColSpan(new int[]{8,2,3});
				filterData1.setBlankRowsBelow(1);
				filterData1.setCellNoWrap(new boolean[]{true,false,false});
				filterData1.setBorder(false);
				//filterData.setBlankRowsBelow(1);
				rg.addTableToDoc(filterData1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object[][] AssessTitle=new Object[1][1];
				AssessTitle[0][0]="Template Definition: -";
				TableDataSet AssHeaderData = new TableDataSet();
				AssHeaderData.setData(new Object[][] { { "Template Definition: -" } });
				AssHeaderData.setCellAlignment(new int[]{0});
				AssHeaderData.setCellWidth(new int[]{100});
				AssHeaderData.setBodyFontStyle(1);  
				AssHeaderData.setBorderDetail(2);
				AssHeaderData.setBlankRowsBelow(1);
				rg.addTableToDoc(AssHeaderData);
				
		
				Object[][] tempTitle=new Object[1][1];
				 tempTitle[0][0] = "Template Name : "+bean.getTempName();
				TableDataSet tempData = new TableDataSet();
				tempData.setData(tempTitle);
				tempData.setCellAlignment(new int[]{0});
				tempData.setCellWidth(new int[]{100});
				tempData.setBodyFontStyle(0);  
				tempData.setBorderDetail(0);
				tempData.setBlankRowsBelow(1);
				rg.addTableToDoc(tempData);
				
			
			String instrQry = " SELECT APPR_INSTRUCTIONS FROM PAS_APPR_TEMPLATE WHERE APPR_ID = "
					+ bean.getApprCode() + " AND APPR_TEMPLATE_ID = " + bean.getTempCode();
			Object[][] instrObj = getSqlModel().getSingleResult(instrQry);

			String ratingQry = " SELECT DISTINCT APPR_RATING_VALU,APPR_RATING_DESC FROM PAS_APPR_QUESTION_RATING_DTL "
					+ " WHERE PAS_APPR_QUESTION_RATING_DTL.APPR_ID = "
					+ bean.getApprCode()
					+ " ORDER BY APPR_RATING_VALU	";
			Object[][] ratingObj = getSqlModel().getSingleResult(ratingQry);

			if (instrObj == null || instrObj.length < 1) {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "There is no data avialable. So cannot generate the preview.";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
				rg.process();
				rg.createReport(response);
				return;
			}

			Object[][] appraTitle=new Object[1][1];
			appraTitle[0][0] = "Appraisal Instruction : -";
			TableDataSet apprData = new TableDataSet();
			apprData.setData(appraTitle);
			apprData.setCellAlignment(new int[]{0});
			apprData.setCellWidth(new int[]{100});
			apprData.setBodyFontStyle(1);  
			apprData.setBorderDetail(2);
			apprData.setBlankRowsBelow(1);
			rg.addTableToDoc(apprData);
			
			
			String ratingScale = "";
			for (int i = 0; i < ratingObj.length; i++)
				ratingScale += ratingObj[i][0] + "-" + ratingObj[i][1] + ",";
			if(!ratingScale.equals(""))
				ratingScale = ratingScale.substring(0, ratingScale.length() - 1);
			String ratingScaleObj="\n Rating Scale :"+ratingScale;
			ratingScaleObj+="\n\n Instruction : "+Utility.checkNull(String.valueOf(instrObj[0][0]));
			
			TableDataSet ratinScaleTab = new TableDataSet();
			ratinScaleTab.setData(new  Object[][]{{ratingScaleObj}});
			ratinScaleTab.setCellAlignment(new int[]{0});
			ratinScaleTab.setBodyFontStyle(0);
			ratinScaleTab.setCellWidth(new int[]{100});
			ratinScaleTab.setBlankRowsBelow(1);
			ratinScaleTab.setBorder(false);
			rg.addTableToDoc(ratinScaleTab);
			
			
			String secDtlQry = " SELECT APPR_SECTION_NAME,PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT,APPR_SECTION_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID  FROM PAS_APPR_SECTION_DTL "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_SECTION_DTL.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
					+ " INNER JOIN PAS_APPR_SECTION_HDR ON (PAS_APPR_SECTION_DTL.APPR_SECTION_ID = PAS_APPR_SECTION_HDR.APPR_SECTION_ID) "
					+ " WHERE PAS_APPR_PHASE_CONFIG.APPR_ID = "
					+ bean.getApprCode()
					+ " AND APPR_PHASE_STATUS = 'A' "
					+ " AND APPR_TEMPLATE_ID = "
					+ bean.getTempCode()
					+ " ORDER BY APPR_SECTION_ORDER,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
			Object[][] secDtlObj = getSqlModel().getSingleResult(secDtlQry);

			String grpQuery = " SELECT APPR_EMP_GRP_NAME,APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR  WHERE APPR_ID = "
					+ bean.getApprCode() + " AND APPR_TEMPLATE_ID = " + bean.getTempCode();

			if (!bean.getGroupCode().equals(""))
				grpQuery += " AND APPR_EMP_GRP_ID = " + bean.getGroupCode();
			grpQuery += " ORDER BY  APPR_EMP_GRP_ID ";
			Object[][] grpObj = getSqlModel().getSingleResult(grpQuery);

			String secStr = "";
			String phaseStr = "";
			int secCount = 0;
			int phaseCount = 0;
			for (int j = 0; j < secDtlObj.length; j++) {
				if (secStr.indexOf(String.valueOf(secDtlObj[j][5])) < 0) {
					secStr += String.valueOf(secDtlObj[j][5]);
					secCount++;
				}
				if (phaseStr.indexOf(String.valueOf(secDtlObj[j][6])) < 0) {
					phaseStr += String.valueOf(secDtlObj[j][6]);
					phaseCount++;
				}
			}

			Object[][] dataObj = new Object[secCount * 3][phaseCount + 2];
			int k = 0, z = 0, c = 0;
			for (int i = 0; i < secCount * 3; i++) {
				c = k;
				if (i % 3 == 0) {
					dataObj[i][0] = "";
					dataObj[i][1] = "Visibility";
					z = 2;
				} else if (i % 3 == 1) {
					dataObj[i][0] = secDtlObj[k][0];
					dataObj[i][1] = "Rating";
					z = 3;
				} else if (i % 3 == 2) {
					dataObj[i][0] = "";
					dataObj[i][1] = "Comments";
					z = 4;
				}
				for (int j = 0; j < phaseCount; j++)
					dataObj[i][2 + j] = secDtlObj[k++][z];

				if (i % 3 == 2)
					k = c + phaseCount;
				else
					k = c;

			}

			int[] bcellWidth = new int[phaseCount + 2];
			int[] bcellAlign = new int[phaseCount + 2];
			String columns[] = new String[phaseCount + 2];
			columns[0] = "Section Name";
			columns[1] = "Parameter";
			for (int i = 2; i < phaseCount + 2; i++)
				columns[i] = String.valueOf(secDtlObj[i - 2][1]);
			bcellWidth[0] = 25;
			bcellWidth[1] = 20;
			bcellAlign[0] = 0;
			bcellAlign[1] = 0;
			for (int i = 2; i < bcellAlign.length; i++) {
				bcellWidth[i] = 55 / phaseCount;
				bcellAlign[i] = 1;
			}
						
			Object[][] secTitle=new Object[1][1];
			secTitle[0][0] = "Section Mapping:-";
			TableDataSet secTab = new TableDataSet();
			secTab.setData(secTitle);
			secTab.setCellAlignment(new int[]{0});
			secTab.setCellWidth(new int[]{100});
			secTab.setBodyFontStyle(1);  
			secTab.setBorderDetail(2);
			//secTab.setBlankRowsBelow(1);
			rg.addTableToDoc(secTab);
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(columns);
			tdstable.setData(dataObj);// data object from query
			tdstable.setHeaderLines(true);
			tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
			tdstable.setCellAlignment(bcellAlign);
			tdstable.setCellWidth(bcellWidth); 
			tdstable.setHeaderBorderDetail(3);	
			tdstable.setBorderDetail(3);
			tdstable.setBorder(true); 
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeaderTable(true);   
			tdstable.setBlankRowsBelow(1);
			rg.addTableToDoc(tdstable);
			
						
			String grpStr = "";
									
			Object[][] appMapTitle=new Object[1][1];
			appMapTitle[0][0] = "Appraisal Mapping : -";
			TableDataSet appMapTab = new TableDataSet();
			appMapTab.setData(appMapTitle);
			appMapTab.setCellAlignment(new int[]{0});
			appMapTab.setCellWidth(new int[]{100});
			appMapTab.setBodyFontStyle(1);  
			appMapTab.setBorderDetail(0);
			//appMapTab.setBlankRowsBelow(1);
			rg.addTableToDoc(appMapTab);
			
		
			if (grpObj != null && grpObj.length > 0) {
				for (int i = 0; i < grpObj.length; i++)
					grpStr += grpObj[i][0] + ",";
				grpStr = grpStr.substring(0, grpStr.length() - 1);
			}
			if (!grpStr.equals("")) {
				String groupTitleObj=" Group Name: "+grpStr;
				groupTitleObj+="\n Note : To view the list of employees for the group(s) refer annexure.";
								
				TableDataSet groupDet = new TableDataSet();
				groupDet.setData(new Object[][]{{groupTitleObj}});
				groupDet.setCellAlignment(new int[]{0});
				groupDet.setCellWidth(new int[]{100});
				groupDet.setBodyFontStyle(0);  
				groupDet.setBorderDetail(0);
				groupDet.setBlankRowsBelow(1);
				rg.addTableToDoc(groupDet);
			
			} else{
			TableDataSet noData1 = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No Group is defined for this appraisal calendar";
			
			noData1.setData(noDataObj);
			noData1.setCellAlignment(new int[] { 1 });
			noData1.setCellWidth(new int[] { 100 });
			noData1.setBorder(false);
			rg.addTableToDoc(noData1);
			}
			String trnDtlsQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
					+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
					+ bean.getApprCode()
					+ " AND APPR_TEMPLATE_ID = "
					+ bean.getTempCode()
					+ " AND APPR_SECTION_TYPE = 'T' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
			Object trnDtlsObj[][] = getSqlModel().getSingleResult(trnDtlsQry);
			Object[][] trnDataObj = null;
			if (trnDtlsObj != null && trnDtlsObj.length > 0) {
				trnDataObj = new Object[3][trnDtlsObj.length + 2];
				trnDataObj[0][0] = "";
				trnDataObj[1][0] = "";
				trnDataObj[2][0] = "";
				trnDataObj[0][1] = "";
				trnDataObj[1][1] = "Visibility";
				trnDataObj[2][1] = "Comments";
				for (int i = 0; i < trnDtlsObj.length; i++)
					trnDataObj[0][i + 2] = String.valueOf(trnDtlsObj[i][0]);

				for (k = 1; k < 3; k++)
					for (int i = 0; i < trnDtlsObj.length; i++)
						trnDataObj[k][i + 2] = trnDtlsObj[i][k];

				columns = new String[trnDtlsObj.length + 1];
				columns[0] = "";

				bcellWidth = new int[trnDtlsObj.length + 2];
				bcellAlign = new int[trnDtlsObj.length + 2];
				bcellWidth[0] = 20;
				bcellAlign[0] = 0;
				bcellWidth[1] = 20;
				bcellAlign[1] = 0;

				for (int i = 0; i < trnDtlsObj.length; i++) {
					bcellWidth[i + 2] = 60 / trnDtlsObj.length;
					bcellAlign[i + 2] = 1;
				}

			}

			trnDtlsQry = " SELECT APPR_QUES_DESC FROM PAS_APPR_TRN_RECOMMEND "
					+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_TRN_RECOMMEND.APPR_QUESTION_CODE) "
					+ " WHERE APPR_QUES_STATUS = 'A' AND APPR_ID = " + bean.getApprCode()
					+ " AND APPR_TEMPLATE_ID = " + bean.getTempCode();
			trnDtlsObj = getSqlModel().getSingleResult(trnDtlsQry);

			//rg.addFormatedText("\n", 6, 0, 0, 0);
			//rg.addTableHeader("Training Details : - ", 6, 0, 0, true);
			//rg.addFormatedText("\t\t\tTraining Recommendation Applicability : ",
				//	0, 0, 0, 0);
			
			String TrainingObj="Training Details : - ";
										
			TableDataSet Training = new TableDataSet();
			Training.setData(new Object[][]{{TrainingObj}});
			Training.setCellAlignment(new int[]{0});
			Training.setCellWidth(new int[]{100});
			Training.setBodyFontStyle(1);  
			Training.setBorderDetail(0);
			Training.setBlankRowsBelow(1);
			rg.addTableToDoc(Training);
			
			String TrainingDetObj="Training Recommendation Applicability : ";
			
			TableDataSet TrainingDet = new TableDataSet();
			TrainingDet.setData(new Object[][]{{TrainingDetObj}});
			TrainingDet.setCellAlignment(new int[]{0});
			TrainingDet.setCellWidth(new int[]{100});
			TrainingDet.setBodyFontStyle(0);  
			TrainingDet.setBorderDetail(0);
			TrainingDet.setBlankRowsBelow(1);
			rg.addTableToDoc(TrainingDet);
			if (trnDataObj != null && trnDataObj.length > 0){
			//	rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
			
			TableDataSet empList = new TableDataSet();
			empList.setData(trnDataObj);// data object from query
			empList.setHeaderLines(true);
			empList.setCellAlignment(bcellAlign);
			empList.setCellWidth(bcellWidth); 
			empList.setBorder(false); 
			empList.setBlankRowsAbove(1);
			empList.setHeaderTable(false);   
			empList.setBlankRowsBelow(1);
			rg.addTableToDoc(empList);
			}
			else{
			
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "\t\t\t\t Training Recomendation applicability not defined for appraisal calendar";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
			String trnQuestionList="Training Question List :  ";
			TableDataSet trnQuestion = new TableDataSet();
			trnQuestion.setData(new Object[][]{{trnQuestionList}});
			trnQuestion.setCellAlignment(new int[]{0});
			trnQuestion.setCellWidth(new int[]{100});
			trnQuestion.setBodyFontStyle(0);  
			trnQuestion.setBorderDetail(0);
			trnQuestion.setBlankRowsBelow(1);
			rg.addTableToDoc(trnQuestion);
			if (trnDtlsObj != null){
				for (int i = 0; i < trnDtlsObj.length; i++) {
					//rg.addFormatedText("\t\t\t\t " + (i + 1) + ". "
						//	+ trnDtlsObj[i][0], 0, 0, 0, 0);
					String questionDet = "\t\t\t\t"+(i+1)+"."+trnDtlsObj[i][0];
					TableDataSet questionList = new TableDataSet();
					questionList.setData(new Object[][]{{questionDet}});
					questionList.setCellAlignment(new int[]{0});
					questionList.setCellWidth(new int[]{100});
					questionList.setBodyFontStyle(0);  
					questionList.setBorderDetail(0);
					questionList.setBlankRowsBelow(1);
					rg.addTableToDoc(questionList);
				}
			}
			else{
				
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "\t\t\t\t Training question list not defined for appraisal calendar";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}	
			
			String awardQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
					+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
					+ bean.getApprCode()
					+ " AND APPR_TEMPLATE_ID = "
					+ bean.getTempCode()
					+ " AND APPR_SECTION_TYPE = 'A' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
			Object awardObj[][] = getSqlModel().getSingleResult(awardQry);

			String awardDetObj="\n Awards & Recognition : -";
			TableDataSet awardTab = new TableDataSet();
			awardTab.setData(new Object[][]{{awardDetObj}});
			awardTab.setCellAlignment(new int[]{0});
			awardTab.setCellWidth(new int[]{100});
			awardTab.setBodyFontStyle(1);  
			awardTab.setBorderDetail(0);
			awardTab.setBlankRowsBelow(1);
			rg.addTableToDoc(awardTab);
			
			String awardNomDetObj="\t\t\tAward Nomination Applicability :";
			
			TableDataSet awardNomTab = new TableDataSet();
			awardNomTab.setData(new Object[][]{{awardNomDetObj}});
			awardNomTab.setCellAlignment(new int[]{0});
			awardNomTab.setCellWidth(new int[]{100});
			awardNomTab.setBodyFontStyle(0);  
			awardNomTab.setBorderDetail(0);
			awardNomTab.setBlankRowsBelow(1);
			rg.addTableToDoc(awardNomTab);
			//rg.addFormatedText("\t\t\tAward Nomination Applicability : ", 0, 0,
					//0, 0);
			if (awardObj != null && awardObj.length > 0) {
				for (k = 1; k < 3; k++)
					for (int i = 0; i < awardObj.length; i++)
						trnDataObj[k][i + 2] = awardObj[i][k];
				//rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
				
				TableDataSet empList = new TableDataSet();
				empList.setData(trnDataObj);// data object from query
				empList.setHeaderLines(false);
				empList.setCellAlignment(bcellAlign);
				empList.setCellWidth(bcellWidth); 
				empList.setBorder(false); 
				empList.setHeaderTable(false);   
				empList.setBlankRowsBelow(1);
				rg.addTableToDoc(empList);
				
			} else
			{
			String awardNoDataObj ="\t\t\t\t Award nomination applicabilty not defined in appraisal calendar";
			TableDataSet awardNoDataTab = new TableDataSet();
			awardNoDataTab.setData(new Object[][]{{awardNoDataObj}});
			awardNoDataTab.setCellAlignment(new int[]{0});
			awardNoDataTab.setCellWidth(new int[]{100});
			awardNoDataTab.setBodyFontStyle(0);  
			awardNoDataTab.setBorderDetail(0);
			awardNoDataTab.setBlankRowsBelow(1);
			rg.addTableToDoc(awardNoDataTab);
			}
			
			//rg.addFormatedText("\t\t\tAward Nomination : ", 0, 0, 0, 0);
			String awardNomDet="\t\t\tAward Nomination :";
			TableDataSet awardNomDataTab = new TableDataSet();
			awardNomDataTab.setData(new Object[][]{{awardNomDet}});
			awardNomDataTab.setCellAlignment(new int[]{0});
			awardNomDataTab.setCellWidth(new int[]{100});
			awardNomDataTab.setBodyFontStyle(0);  
			awardNomDataTab.setBorderDetail(0);
			awardNomDataTab.setBlankRowsBelow(1);
			rg.addTableToDoc(awardNomDataTab);
			
			String awardNom = " SELECT APPR_AWARD_FLAG,APPR_REASON_FLAG FROM PAS_APPR_AWD_NOMINATE "
					+ " WHERE APPR_ID = "
					+ bean.getApprCode()
					+ " AND APPR_TEMPLATE_ID = "
					+ bean.getTempCode();
			Object[][] awardNomObj = getSqlModel().getSingleResult(awardNom);

			Object[][] awardStatus = new Object[2][3];
			awardStatus[0][0] = "";
			awardStatus[1][0] = "";
			awardStatus[0][1] = "Nominate an Award";
			awardStatus[0][2] = "Reason for Nomination";
			if (awardNomObj != null && awardNomObj.length > 0) {
				awardStatus[1][1] = awardNomObj[0][0];
				awardStatus[1][2] = awardNomObj[0][1];
				int[] cellWidth = { 40, 30, 30 };
				int[] cellAlign = { 0, 1, 1 };
				//rg.tableBodyNoBorder(awardStatus, cellWidth, cellAlign);
				TableDataSet empList = new TableDataSet();
				empList.setData(awardStatus);// data object from query
				empList.setHeaderLines(false);
				empList.setCellAlignment(cellAlign);
				empList.setCellWidth(cellWidth); 
				empList.setBorder(false); 
				empList.setHeaderTable(false);   
				empList.setBlankRowsBelow(1);
				rg.addTableToDoc(empList);
			} else{
				
				String awardNoDataObj ="\t\t\t\t Award nomination not defined in the appraisal calendar";
				TableDataSet awardNoDataTab = new TableDataSet();
				awardNoDataTab.setData(new Object[][]{{awardNoDataObj}});
				awardNoDataTab.setCellAlignment(new int[]{0});
				awardNoDataTab.setCellWidth(new int[]{100});
				awardNoDataTab.setBodyFontStyle(0);  
				awardNoDataTab.setBorderDetail(0);
				awardNoDataTab.setBlankRowsBelow(1);
				rg.addTableToDoc(awardNoDataTab);
				
			}
			//rg.addTableHeader("Disciplinary Action : - ", 6, 0, 0, true);
			String disObj ="Disciplinary Action : - ";
			TableDataSet awardNoDataTab = new TableDataSet();
			awardNoDataTab.setData(new Object[][]{{disObj}});
			awardNoDataTab.setCellAlignment(new int[]{0});
			awardNoDataTab.setCellWidth(new int[]{100});
			awardNoDataTab.setBorderDetail(2);
			awardNoDataTab.setBodyFontStyle(1);  
			awardNoDataTab.setBorderDetail(0);
			awardNoDataTab.setBlankRowsBelow(1);
			rg.addTableToDoc(awardNoDataTab);
			
		//	rg.addFormatedText("\t\t\t\tDisciplinary Action Applicability: ", 0,
			//		0, 0, 0);
			
			String disDetObj = "\t\t\t\tDisciplinary Action Applicability: ";
			TableDataSet disDetObjTab = new TableDataSet();
			disDetObjTab.setData(new Object[][]{{disDetObj}});
			disDetObjTab.setCellAlignment(new int[]{0});
			disDetObjTab.setCellWidth(new int[]{100});
			disDetObjTab.setBodyFontStyle(0);  
			disDetObjTab.setBorderDetail(0);
			disDetObjTab.setBlankRowsBelow(1);
			rg.addTableToDoc(disDetObjTab);

			String dispQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
					+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
					+ bean.getApprCode()
					+ " AND APPR_TEMPLATE_ID = "
					+ bean.getTempCode()
					+ " AND APPR_SECTION_TYPE = 'D' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
			Object dispObj[][] = getSqlModel().getSingleResult(dispQry);

			if (dispObj != null) {
				for (k = 1; k < 3; k++)
					for (int i = 0; i < dispObj.length; i++)
						trnDataObj[k][i + 2] = dispObj[i][k];
				if (trnDataObj != null && trnDataObj.length > 0){
				//	rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
				TableDataSet empList = new TableDataSet();
				empList.setData(trnDataObj);// data object from query
				empList.setHeaderLines(false);
				empList.setCellAlignment(bcellAlign);
				empList.setCellWidth(bcellWidth); 
				empList.setBorder(false); 
				empList.setHeaderTable(false);   
				empList.setBlankRowsBelow(1);
				rg.addTableToDoc(empList);
				}	
				else{
					
					String strDisObj ="\t\t\t\t Disciplinary action not defined in the apprisal calendar";
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(new Object[][]{{strDisObj}});
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}	
			}

			//rg.addTableHeader("Career Progression Details : - ", 6, 0, 0, true);
			String carProgDet ="Career Progression Details : -";
			TableDataSet carPragTab = new TableDataSet();
			carPragTab.setData(new Object[][]{{carProgDet}});
			carPragTab.setCellAlignment(new int[]{0});
			carPragTab.setCellWidth(new int[]{100});
			carPragTab.setBodyFontStyle(1);  
			carPragTab.setBorderDetail(0);
			carPragTab.setBlankRowsBelow(1);
			rg.addTableToDoc(carPragTab);
			
			//rg.addFormatedText("\t\t\tCareer Progression Applicability: ", 0, 0,
			//		0, 0);
			String carProgApp ="\t\t\tCareer Progression Applicability: ";
			TableDataSet carProgTab = new TableDataSet();
			carProgTab.setData(new Object[][]{{carProgDet}});
			carProgTab.setCellAlignment(new int[]{0});
			carProgTab.setCellWidth(new int[]{100});
			carProgTab.setBodyFontStyle(0);  
			carProgTab.setBorderDetail(0);
			carProgTab.setBlankRowsBelow(1);
			rg.addTableToDoc(carProgTab);
			String carrerQry = " SELECT APPR_PHASE_NAME, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER FROM PAS_APPR_COMMON_SECTION "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE) "
					+ " WHERE PAS_APPR_COMMON_SECTION.APPR_ID = "
					+ bean.getApprCode()
					+ " AND APPR_TEMPLATE_ID = "
					+ bean.getTempCode()
					+ " AND APPR_SECTION_TYPE = 'C' ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";
			;
			Object carrerObj[][] = getSqlModel().getSingleResult(carrerQry);
			if (carrerObj != null) {
				for (k = 1; k < 3; k++)
					for (int i = 0; i < carrerObj.length; i++)
						trnDataObj[k][i + 2] = carrerObj[i][k];
				if (trnDataObj != null && trnDataObj.length > 0){
					//rg.tableBodyNoBorder(trnDataObj, bcellWidth, bcellAlign);
					TableDataSet empList = new TableDataSet();
					empList.setData(trnDataObj);// data object from query
					empList.setHeaderLines(false);
					empList.setCellAlignment(bcellAlign);
					empList.setCellWidth(bcellWidth); 
					empList.setBorder(false); 
					empList.setHeaderTable(false);   
					empList.setBlankRowsBelow(1);
					rg.addTableToDoc(empList);
				}
				else{
					
					String strDisObj ="\t\t\t\t Carrear progression appicability not defined";
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(new Object[][]{{strDisObj}});
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}	
			}

			
			String strCarNoObj ="\t\t\tCareer Progression Question List:";
			TableDataSet carNodata = new TableDataSet();
			carNodata.setData(new Object[][]{{strCarNoObj}});
			carNodata.setCellAlignment(new int[]{0});
			carNodata.setCellWidth(new int[]{100});
			carNodata.setBodyFontStyle(0);  
			carNodata.setBorderDetail(0);
			carNodata.setBlankRowsBelow(1);
			rg.addTableToDoc(carNodata);
			
			String carrerQsQry = " SELECT APPR_QUES_DESC FROM PAS_APPR_CAREER  "
					+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_CAREER .APPR_QUESTION_CODE) "
					+ " WHERE APPR_QUES_STATUS = 'A' AND APPR_ID = " + bean.getApprCode()
					+ " AND APPR_TEMPLATE_ID = " + bean.getTempCode();
			trnDtlsObj = getSqlModel().getSingleResult(carrerQsQry);

			if (trnDtlsObj != null){
				for (int i = 0; i < trnDtlsObj.length; i++){
					//rg.addFormatedText("\t\t\t\t " + (i + 1) + ". "
						//	+ trnDtlsObj[i][0], 0, 0, 0, 0);
			String strCarProgData ="\t\t\t\t"+(i + 1) + ". "+ trnDtlsObj[i][0];
			TableDataSet strCarProgDataTab = new TableDataSet();
			strCarProgDataTab.setData(new Object[][]{{strCarProgData}});
			strCarProgDataTab.setCellAlignment(new int[]{0});
			strCarProgDataTab.setCellWidth(new int[]{100});
			strCarProgDataTab.setBodyFontStyle(0);  
			strCarProgDataTab.setBorderDetail(0);
			strCarProgDataTab.setBlankRowsBelow(1);
			rg.addTableToDoc(strCarProgDataTab);
				}
			}	else{
				
				String strNoQuest ="\t\t\t\t No question list defined in the appraisal calendar for carrer progression";
				TableDataSet strNoQuestObj = new TableDataSet();
				carNodata.setData(new Object[][]{{strNoQuest}});
				carNodata.setCellAlignment(new int[]{0});
				carNodata.setCellWidth(new int[]{100});
				carNodata.setBodyFontStyle(0);  
				carNodata.setBorderDetail(0);
				carNodata.setBlankRowsBelow(1);
				rg.addTableToDoc(carNodata);
			}
			//rg.pageBreak();
			rg.addProperty(1);
			
			//rg.addTableHeader("Annexure: - ", 6, 0, 0, true);
			String AnnexHdr = "Annexure: -";
			TableDataSet strNoQuestObj = new TableDataSet();
			strNoQuestObj.setData(new Object[][]{{AnnexHdr}});
			strNoQuestObj.setCellAlignment(new int[]{0});
			strNoQuestObj.setCellWidth(new int[]{100});
			strNoQuestObj.setBodyFontStyle(1);  
			strNoQuestObj.setBorderDetail(0);
			strNoQuestObj.setBlankRowsBelow(1);
			rg.addTableToDoc(strNoQuestObj);
			
			String cols[] = {"Sr No","Employee Name","Division","Branch","Department","Designation"}; 
			int []cellWidth={10,20,15,20,20,15};
			int []cellAlign={1,0,0,0,0,0};
			for (int i = 0; i < grpObj.length; i++) {
				Object[][] groupTitle=new Object[1][1];
				groupTitle[0][0] = "Group : "+String.valueOf(grpObj[i][0]);
				TableDataSet groupDet = new TableDataSet();
				groupDet.setData(groupTitle);
				groupDet.setCellAlignment(new int[]{0});
				groupDet.setCellWidth(new int[]{100});
				groupDet.setBodyFontStyle(1);  
				groupDet.setBorderDetail(0);
				groupDet.setBlankRowsBelow(1);
				rg.addTableToDoc(groupDet);
				
				String empQry =  " SELECT ROWNUM,EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME,DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME "
					           + " FROM PAS_APPR_EMP_GRP_DTL "  
					           + " INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
					           + " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  "
					           + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "  
					           + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
					           + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					           + " WHERE PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = "+grpObj[i][1]	   
					           + " ORDER BY UPPER(EMPNAME) ";
			   Object[][] empObj = getSqlModel().getSingleResult(empQry);
			   if(empObj != null && empObj.length > 0)
			   {
				   for (int j = 0; j < empObj.length; j++) 
					   empObj[j][0]= j+1;
				   //rg.tableBody(cols,empObj, cellWidth, cellAlign);
				   TableDataSet empList = new TableDataSet();
					empList.setHeader(cols);
					empList.setData(empObj);// data object from query
					empList.setHeaderLines(true);
					empList.setHeaderBorderColor(new BaseColor(0,255,0));
					empList.setCellAlignment(cellAlign);
					empList.setCellWidth(cellWidth); 
					empList.setHeaderBorderDetail(3);	
					empList.setBorderDetail(3);
					empList.setBorder(true); 
					empList.setBlankRowsAbove(1);
					empList.setHeaderTable(true);   
					empList.setBlankRowsBelow(1);
					rg.addTableToDoc(empList);
			   }
			   else{
				 //  rg.addFormatedText("\n No employee has been assigned to this group.", 6, 0, 0, 0);
			  // rg.addFormatedText("\n", 6, 0, 0, 0);
				String strNoQuest ="\n No employee has been assigned to this group.";
				TableDataSet noEmpData = new TableDataSet();
				noEmpData.setData(new Object[][]{{strNoQuest}});
				noEmpData.setCellAlignment(new int[]{0});
				noEmpData.setCellWidth(new int[]{100});
				noEmpData.setBodyFontStyle(0);  
				noEmpData.setBorderDetail(0);
				noEmpData.setBlankRowsBelow(1);
				rg.addTableToDoc(noEmpData);
			}
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
