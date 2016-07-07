/**
 * 
 */
package org.paradyne.model.employeeSurvey;


import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.employeeSurvey.EmployeeSurveySummary;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;


/**
 * @author AA0431 Lakkichand_Golegaonkar
 * @Date 20 Oct 2010
 */
public class EmployeeSurveySummaryModel extends ModelBase {

	public void generateReport(HttpServletResponse response,
			EmployeeSurveySummary empSurveySummary) {
		// TODO Auto-generated method stub
		String queryForOptions = "SELECT DISTINCT OPTION_DESC FROM HRMS_EMPSURVEY_QUESOPTION WHERE QUES_CODE"
				+ " IN(SELECT QUES_CODE FROM HRMS_EMPSURVEY_QUESBANK WHERE QUES_SURVEY_CODE="
				+ empSurveySummary.getSurveyCode();
		if (empSurveySummary.getSectionCode() != null
				&& empSurveySummary.getSectionCode().length() > 0) {
			queryForOptions += " AND QUES_SECTION_CODE="
					+ empSurveySummary.getSectionCode() + ") ";
		}
		queryForOptions += "GROUP BY OPTION_DESC  ORDER BY OPTION_DESC";

		Object[][] optionsProvided = getSqlModel().getSingleResult(
				queryForOptions);

		String optionStatusQuery = "SELECT HRMS_EMPSURVEY_QUESBANK.QUES_NAME, HRMS_EMPSURVEY_QUESOPTION.OPTION_DESC,COUNT(*),HRMS_EMPSURVEY_QUESBANK.QUES_CODE "
				+ " FROM "
				+ " HRMS_EMPSURVEY_COMMENTS "
				+ " inner JOIN HRMS_EMPSURVEY_QUESBANK on (HRMS_EMPSURVEY_QUESBANK.QUES_CODE=HRMS_EMPSURVEY_COMMENTS.QUESTION_CODE) "
				+ " INNER JOIN HRMS_EMPSURVEY_QUESOPTION ON (TO_CHAR(HRMS_EMPSURVEY_QUESOPTION.OPTION_CODE)=HRMS_EMPSURVEY_COMMENTS.QUESTION_COMMENTS) "
				+ " WHERE  QUES_TYPE='O' AND QUES_SURVEY_CODE="
				+ empSurveySummary.getSurveyCode();

		if (empSurveySummary.getSectionCode() != null
				&& empSurveySummary.getSectionCode().length() > 0) {
			optionStatusQuery += " AND QUES_SECTION_CODE="
					+ empSurveySummary.getSectionCode() ;
		}
		if(empSurveySummary.getDivisionId() != null
				&& empSurveySummary.getDivisionId().length() > 0){			
			optionStatusQuery +=" AND EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV="+empSurveySummary.getDivisionId()+")";
		}		
		if(empSurveySummary.getBranchId() != null
				&& empSurveySummary.getBranchId().length() > 0){
			optionStatusQuery +=" AND EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_CENTER="+empSurveySummary.getBranchId()+")";
		}
		
		if(empSurveySummary.getDepartmentId() != null
				&& empSurveySummary.getDepartmentId().length() > 0){
			optionStatusQuery +=" AND EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DEPT="+empSurveySummary.getDepartmentId()+")";
		}
			
		optionStatusQuery += " AND HRMS_EMPSURVEY_COMMENTS.QUESTION_COMMENTS IS NOT NULL "
				+ " GROUP BY HRMS_EMPSURVEY_QUESBANK.QUES_NAME, HRMS_EMPSURVEY_QUESOPTION.OPTION_DESC,HRMS_EMPSURVEY_QUESBANK.QUES_CODE  ORDER BY HRMS_EMPSURVEY_QUESBANK.QUES_NAME, OPTION_DESC";

		Object[][] optionsCount = getSqlModel().getSingleResult(
				optionStatusQuery);
		
		Object [][] transObj =null;
			
		try {
			transObj = Utility.transverse(optionsCount, new int[] { 0 }, 1, 2,
					"0", true, new String[] { "Question" });
			System.out.println("transObj ======"+transObj.length);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("transObj ======"+transObj.length);

		Object [][] srNoObj=new Object[transObj.length][1];
		srNoObj[0][0]="Sr. No.";
		for (int i = 0; i < srNoObj.length-1; i++) {
			srNoObj[i+1][0]=""+(i+1);
		}
		
		try {
		
			transObj = Utility.joinArrays(srNoObj, transObj, 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("+++++");
			e.printStackTrace();
			transObj=new Object[0][0];
		}
		System.out.println("____  transObj ____"+transObj.length);
		System.out.println("____  transObj[0] ____"+transObj[0].length);
		ReportDataSet rds = new ReportDataSet();
		rds.setReportType(empSurveySummary.getReportType());
		rds.setFileName("Employee Survey Summary");
		//rds.setPageSize("A3");
		//rds.setPageOrientation("portrait");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		
		
		
		Object[][] nameData = new Object[3][4];
		nameData[0][0] = "Survey :";
		nameData[0][1] = empSurveySummary.getSurveyName();
		nameData[0][2] = "";
		nameData[0][3] = "";

		nameData[1][0] = "";
		nameData[1][1] = "";
		nameData[1][2] = "";
		nameData[1][3] = "";
		
		nameData[2][0] = "";
		nameData[2][1] = "";
		nameData[2][2] = "";
		nameData[2][3] = "";
		

		if (!(empSurveySummary.getSectionName().equals(""))) {
			nameData[0][2] = "Section :";
			nameData[0][3] = empSurveySummary.getSectionName();
		}// end of if
		
		if(!empSurveySummary.getDivisionId().equals("")){
			nameData[1][0] = "Division: ";
			nameData[1][1] = empSurveySummary.getDivisionName();
		}
		
		if(!empSurveySummary.getBranchId().equals("")){
			nameData[1][2] = "Branch: ";
			nameData[1][3] = empSurveySummary.getBranchName();
		}
		
		if(!empSurveySummary.getDepartmentId().equals("")){
			nameData[2][0] = "Department: ";
			nameData[2][1] = empSurveySummary.getDepartmentName();
		}
		
		
		TableDataSet filterTable = new TableDataSet();
		filterTable.setData(nameData);
		int [] cellWidth_filterTable=new int[]{30,30,30,30};
		int [] cellAlignment_filterTable=new int[]{0,0,0,0};
		filterTable.setCellAlignment(cellAlignment_filterTable);
		filterTable.setCellWidth(cellWidth_filterTable);
		rg.addTableToDoc(filterTable);
		
		// Table for question 
		TableDataSet tdsEstab = new TableDataSet();
		tdsEstab.setData(transObj);
		int [] cellWidth=new int[transObj[0].length];
		for (int i = 0; i < cellWidth.length; i++) {
			cellWidth[i]=30;
		}
		int [] cellAlignment=new int[transObj[0].length];
		for (int i = 0; i < cellAlignment.length; i++) {
			cellAlignment[i]=0;
		}
		tdsEstab.setCellAlignment(cellAlignment);
		tdsEstab.setCellWidth(cellWidth);
		
		rg.addTableToDoc(tdsEstab);
		
		rg.process();
		rg.createReport(response);
		

	}
	public static Object[][] checkNullObjArr(Object objArr[][]) {
		try {
			for(int i = 0; i < objArr.length; i++) {
				for(int j = 0; j < objArr[0].length; j++) {
					if(String.valueOf(objArr[i][j]) == null || String.valueOf(objArr[i][j]).equals(null)
						|| String.valueOf(objArr[i][j]).equals("null"))
						objArr[i][j] = "0";
				}
			}
		} catch(Exception e) {}
		return objArr;
	}

}
