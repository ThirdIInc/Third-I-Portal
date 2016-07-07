package org.paradyne.model.employeeSurvey;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.employeeSurvey.EmployeeSurveyReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class EmployeeSurveyReportModel extends ModelBase {

	public void generateReport(HttpServletResponse response,
			EmployeeSurveyReport empSurveyReport) {
		// TODO Auto-generated method stub
		try {

			String title = "\n Employee Survey Report\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					empSurveyReport.getReportType(), title);
			rg.addFormatedText(title, 6, 0, 1, 0);

			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String todaydate = formater.format(date);

			rg.addText("Date: " + todaydate, 0, 2, 0);

			String questionHeaderQuery = " SELECT QUES_NAME,QUES_CODE FROM HRMS_EMPSURVEY_QUESBANK WHERE QUES_SURVEY_CODE="
					+ empSurveyReport.getSurveyCode().trim();

			if (empSurveyReport.getSectionCode() != null
					&& empSurveyReport.getSectionCode().length() > 0) {
				questionHeaderQuery += " AND QUES_SECTION_CODE="
						+ empSurveyReport.getSectionCode() + " ";
			}// end of if

			questionHeaderQuery += " ORDER BY QUES_NAME ";
//........................................
			Object headerObject[][] = getSqlModel().getSingleResult(
					questionHeaderQuery);

			String empQuery = " SELECT DISTINCT HRMS_EMPSURVEY_COMMENTS.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| "
					+ " HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMPSURVEY_COMMENTS "
					+ "    INNER JOIN HRMS_EMP_OFFC ON( HRMS_EMP_OFFC.EMP_ID=HRMS_EMPSURVEY_COMMENTS.EMP_ID) "
					+ " WHERE HRMS_EMPSURVEY_COMMENTS.SURVEY_CODE="
					+ empSurveyReport.getSurveyCode().trim();

			if (empSurveyReport.getEmployeeCode() != null
					&& empSurveyReport.getEmployeeCode().length() > 0) {
				empQuery += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID="
						+ empSurveyReport.getEmployeeCode() + " ";
			}// end of if

			if (empSurveyReport.getSectionCode() != null
					&& empSurveyReport.getSectionCode().length() > 0) {
				empQuery += " AND HRMS_EMPSURVEY_COMMENTS.SECTION_CODE="
						+ empSurveyReport.getSectionCode() + " ";
			}// end of if
			if (empSurveyReport.getDivisionId() != null
					&& empSurveyReport.getDivisionId().length() > 0) {
				empQuery += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV="
						+ empSurveyReport.getDivisionId() + ")";
			}
			if (empSurveyReport.getBranchId() != null
					&& empSurveyReport.getBranchId().length() > 0) {
				empQuery += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_CENTER="
						+ empSurveyReport.getBranchId() + ")";
			}

			if (empSurveyReport.getDepartmentId() != null
					&& empSurveyReport.getDepartmentId().length() > 0) {
				empQuery += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DEPT="
						+ empSurveyReport.getDepartmentId() + ")";
			}
			
			Object empQueryObject[][] = getSqlModel().getSingleResult(empQuery);

			String sql = "";
			Object obj[][] = null;

			if (headerObject != null && headerObject.length > 0) {
				obj = new Object[empQueryObject.length][headerObject.length + 3];

			}

			if (empQueryObject != null && empQueryObject.length > 0) {
				for (int i = 0; i < empQueryObject.length; i++) {

					sql = " SELECT QUESTION_CODE,NVL(QUESTION_COMMENTS,' '),NVL(QUESTION_TYPE,'') "
							+ " FROM HRMS_EMPSURVEY_COMMENTS "
							+ " INNER JOIN HRMS_EMP_OFFC ON( HRMS_EMP_OFFC.EMP_ID=HRMS_EMPSURVEY_COMMENTS.EMP_ID) "
							+ " WHERE 1=1 And HRMS_EMPSURVEY_COMMENTS.emp_id="
							+ String.valueOf(empQueryObject[i][0]) + " ";

					if (empSurveyReport.getSurveyCode() != null
							&& empSurveyReport.getSurveyCode().length() > 0) {
						sql += " AND HRMS_EMPSURVEY_COMMENTS.SURVEY_CODE="
								+ empSurveyReport.getSurveyCode() + " ";
					}// end of if

					if (empSurveyReport.getSectionCode() != null
							&& empSurveyReport.getSectionCode().length() > 0) {
						sql += " AND HRMS_EMPSURVEY_COMMENTS.SECTION_CODE="
								+ empSurveyReport.getSectionCode() + " ";
					}// end of if

					if (empSurveyReport.getEmployeeCode() != null
							&& empSurveyReport.getEmployeeCode().length() > 0) {
						sql += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID="
								+ empSurveyReport.getEmployeeCode() + " ";
					}// end of if
					if (empSurveyReport.getDivisionId() != null
							&& empSurveyReport.getDivisionId().length() > 0) {
						sql += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV="
								+ empSurveyReport.getDivisionId() + ")";
					}
					if (empSurveyReport.getBranchId() != null
							&& empSurveyReport.getBranchId().length() > 0) {
						sql += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_CENTER="
								+ empSurveyReport.getBranchId() + ")";
					}

					if (empSurveyReport.getDepartmentId() != null
							&& empSurveyReport.getDepartmentId().length() > 0) {
						sql += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DEPT="
								+ empSurveyReport.getDepartmentId() + ")";
					}

					Object data[][] = getSqlModel().getSingleResult(sql);

					if (obj != null && obj.length > 0) {
						obj[i][0] = String.valueOf(i + 1);
						obj[i][1] = String.valueOf(empQueryObject[i][1]);
						obj[i][2] = String.valueOf(empQueryObject[i][2]);

					}

					String str = "";

					if (headerObject != null && headerObject.length > 0) {
						for (int k = 0; k < headerObject.length; k++) {
							obj[i][3 + k] = "";
							for (int j = 0; j < data.length; j++) {

								if (String.valueOf(headerObject[k][1]).equals(
										String.valueOf(data[j][0]))) {

									if (String.valueOf(data[j][2]).equals("O")) {
										str = getCommaSeperatedAnswer(String
												.valueOf(data[j][1]));
									} else {
										str = String.valueOf(data[j][1]);
									}
									obj[i][3 + k] = str;

								}
							}

						}
					}

				}

			}

			if (obj != null && obj.length > 0) {

				Object[][] nameData = new Object[3][4];
				nameData[0][0] = "Survey :";
				nameData[0][1] = empSurveyReport.getSurveyName();
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

								if (!(empSurveyReport.getSectionName().equals(""))) {
					nameData[0][2] = "Section :";
					nameData[0][3] = empSurveyReport.getSectionName();
				}// end of if
				
				if(!empSurveyReport.getDivisionId().equals("")){
					nameData[1][0] = "Division: ";
					nameData[1][1] = empSurveyReport.getDivisionName();
				}
				
				if(!empSurveyReport.getBranchId().equals("")){
					nameData[1][2] = "Branch: ";
					nameData[1][3] = empSurveyReport.getBranchName();
				}
				
				if(!empSurveyReport.getDepartmentId().equals("")){
					nameData[2][0] = "Department: ";
					nameData[2][1] = empSurveyReport.getDepartmentName();
				}
				
				if (!empSurveyReport.getEmployeeName().equals("")) {
					nameData[2][2] = "Employee Name:";

					System.out.println("nameData[1][1] is >>>>>>>>>>>>>>"
							+ nameData[1][1]);

					nameData[2][3] = empSurveyReport.getEmployeeName();
					System.out.println("nameData[1][1] is >>>>>>>>>>>>>>"
							+ nameData[1][1]);

				}// end of if

				int[] width_1 = { 15, 25, 15, 25 };
				int[] align_1 = { 0, 0, 0, 0 };
				int totalCol = 0;
				String[] colNames = null;
				int[] cellWidth = null;
				int[] alignment = null;
				int counter = 0;
				if (headerObject != null && headerObject.length > 0) {
					totalCol = 3 + headerObject.length;

					colNames = new String[totalCol];
					cellWidth = new int[totalCol];
					alignment = new int[totalCol];

					colNames[counter] = "Sr. No.";
					cellWidth[counter] = 5;
					alignment[counter] = 0;
					counter++;
					colNames[counter] = "Employee Id";
					cellWidth[counter] = 10;
					alignment[counter] = 0;
					counter++;
					colNames[counter] = "Name";
					cellWidth[counter] = 15;
					alignment[counter] = 0;
					counter++;
				}

				if (headerObject != null && headerObject.length > 0) {
					for (int i = 0; i < headerObject.length; i++) {
						colNames[counter] = String.valueOf(headerObject[i][0]);
						cellWidth[counter] = 10;
						alignment[counter] = 1;
						counter++;
					}
				}

				if (obj != null && obj.length > 0) {
					if (empSurveyReport.getReportType().equals("Xls")) {
						String[] name_xls = { "", "Employee Survey Report", "",
								"" };
						rg.xlsTableBody(name_xls, nameData, width_1, align_1);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(colNames, obj, cellWidth, alignment);
					} else if (empSurveyReport.getReportType().equals("Pdf")) {
						rg.addText("\n", 0, 0, 0);
						rg.tableBodyNoBorder(nameData, width_1, align_1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(colNames, obj, cellWidth, alignment);
					} else if (empSurveyReport.getReportType().equals("Txt")) {
						rg.tableBodyNoBorder(nameData, width_1, align_1);
						rg.tableBody(colNames, obj, cellWidth, alignment);
					}
				} else {
					rg.addTextBold("There is no data to display.", 0, 1, 0);
				}// end of else

			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}

			rg.createReport(response);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getValue(Object[][] obj) {
		String str = "";
		try {
			for (int i = 0; i < obj.length; i++) {
				for (int j = 0; j < obj[0].length; j++) {
					if (!obj[i][j].equals("")) {
						str += String.valueOf(obj[i][j]) + ",";
					}
				}

			}// end of loop

			str = str.substring(0, str.length() - 1);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return str;

	}

	String getCommaSeperatedAnswer(String result) {
		String str = "";

		try {
			String query = " SELECT OPTION_DESC FROM HRMS_EMPSURVEY_QUESOPTION "
					+ " WHERE OPTION_CODE IN(" + result + ")";
			Object dataObj[][] = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				str = getValue(dataObj);
			}

			System.out.println("str----------------" + str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}

	public void createEmployeeSurveyReport(HttpServletResponse response,
			EmployeeSurveyReport empSurveyReport) {
		// TODO Auto-generated method stub

		try {

			//String str = "";

			String title = "\n Employee Survey Report\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					empSurveyReport.getReportType(), title);
			rg.addFormatedText(title, 6, 0, 1, 0);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String todaydate = formater.format(date);
			rg.addText("Date: " + todaydate, 0, 2, 0);
           
			
		/*	String query=" SELECT QUES_NAME,NVL(QUESTION_COMMENTS,' '),QUES_CODE,NVL(QUESTION_TYPE,'') "
					+ "  FROM HRMS_EMPSURVEY_QUESBANK "
					+ " LEFT JOIN HRMS_EMPSURVEY_COMMENTS  ON( HRMS_EMPSURVEY_QUESBANK.QUES_CODE=HRMS_EMPSURVEY_COMMENTS.QUESTION_CODE) ";
			*/
			
			/* String query = " SELECT QUES_NAME,NVL(QUESTION_COMMENTS,' '),QUESTION_CODE,NVL(QUESTION_TYPE,'') "
					+ " FROM HRMS_EMPSURVEY_COMMENTS "
					+ " INNER JOIN HRMS_EMP_OFFC ON( HRMS_EMP_OFFC.EMP_ID=HRMS_EMPSURVEY_COMMENTS.EMP_ID) "
					+ " LEFT JOIN HRMS_EMPSURVEY_QUESBANK ON( HRMS_EMPSURVEY_QUESBANK.QUES_CODE=HRMS_EMPSURVEY_COMMENTS.QUESTION_CODE) "
					+ " WHERE 1=1 ";*/

			String qq = " SELECT QUES_CODE, NVL(QUES_NAME,' '), QUES_TYPE, NVL(SECTION_NAME,' '), QUES_SECTION_CODE "
					+ "   FROM HRMS_EMPSURVEY_QUESBANK  "
					+ "   LEFT JOIN HRMS_EMPSURVEY_MASTERDTL ON (HRMS_EMPSURVEY_MASTERDTL.SECTION_CODE = HRMS_EMPSURVEY_QUESBANK.QUES_SECTION_CODE)"
					+ "   WHERE 1=1 "; 

			if (empSurveyReport.getSurveyCode() != null
					&& empSurveyReport.getSurveyCode().length() > 0) {
				qq += " AND QUES_SURVEY_CODE="
						+ empSurveyReport.getSurveyCode() + " ";
			}// end of if

			qq += " ORDER BY QUES_SECTION_CODE,QUES_CODE ";

			String dtl = " SELECT QUESTION_COMMENTS, QUESTION_TYPE, QUESTION_CODE, SURVEY_FINALIZE FROM HRMS_EMPSURVEY_COMMENTS "
					+ " WHERE 1=1 ";

			//...............if (empSurveyReport.getSurveyCode() != null
					//&& empSurveyReport.getSurveyCode().length() > 0) {
				//dtl += " AND HRMS_EMPSURVEY_COMMENTS.SURVEY_CODE="
					//	+ empSurveyReport.getSurveyCode() + " ";
			//}// end of if

			if (empSurveyReport.getSectionCode() != null
					&& empSurveyReport.getSectionCode().length() > 0) {
				dtl += " AND HRMS_EMPSURVEY_COMMENTS.SECTION_CODE="
						+ empSurveyReport.getSectionCode() + " ";
			}// end of if

			if (empSurveyReport.getEmployeeCode() != null
					&& empSurveyReport.getEmployeeCode().length() > 0) {
				dtl += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID="
						+ empSurveyReport.getEmployeeCode() + " ";
			}// end of if
			if (empSurveyReport.getDivisionId() != null
					&& empSurveyReport.getDivisionId().length() > 0) {
				dtl += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV="
						+ empSurveyReport.getDivisionId() + ")";
			}
			if (empSurveyReport.getBranchId() != null
					&& empSurveyReport.getBranchId().length() > 0) {
				dtl += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_CENTER="
						+ empSurveyReport.getBranchId() + ")";
			}

			if (empSurveyReport.getDepartmentId() != null
					&& empSurveyReport.getDepartmentId().length() > 0) {
				dtl += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID IN ( SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DEPT="
						+ empSurveyReport.getDepartmentId() + ")";
			}

			dtl += " ORDER BY SECTION_CODE,QUESTION_CODE ";

			Object[][] dtlData = getSqlModel().getSingleResult(dtl);
			Object[][] finalData = null;
			Object[][] questData = getSqlModel().getSingleResult(qq);

			if (questData != null && questData.length > 0) {
				finalData = new Object[questData.length][3];
				int count = 0;
				String str1 = "";
				for (int i = 0; i < questData.length; i++) {
					finalData[i][0] = i + 1;
					finalData[i][1] = questData[i][1];
					finalData[i][2]="";
					for (int j = 0; j < dtlData.length; j++) {
						if (String.valueOf(questData[i][0]).equals(
								String.valueOf(dtlData[j][2]))) {
							System.out.println(" equal-----------");
							//finalData[i][0] = count + 1;
							//finalData[i][1] = questData[i][1];
							
							if (String.valueOf(dtlData[j][1]).equals("O")) {
								str1 = getCommaSeperatedAnswer(String
										.valueOf(dtlData[j][0]));
							} else {
								str1 = String.valueOf(dtlData[j][0]);
							}

							finalData[i][2] = str1;
							count++;
							break;
						}
						else{
							System.out.println("not equal");
							//finalData[count][0] = count + 1;
							//finalData[count][1] = questData[i][1];
							//finalData[count][2]="";
							//count++;
							//break;
						}
					}

				}

			}

			/*if (empSurveyReport.getSurveyCode() != null
					&& empSurveyReport.getSurveyCode().length() > 0) {
				query += " AND HRMS_EMPSURVEY_COMMENTS.SURVEY_CODE="
						+ empSurveyReport.getSurveyCode() + " ";
			}// end of if

			if (empSurveyReport.getSectionCode() != null
					&& empSurveyReport.getSectionCode().length() > 0) {
				query += " AND HRMS_EMPSURVEY_COMMENTS.SECTION_CODE="
						+ empSurveyReport.getSectionCode() + " ";
			}// end of if

			if (empSurveyReport.getEmployeeCode() != null
					&& empSurveyReport.getEmployeeCode().length() > 0) {
				query += " AND HRMS_EMPSURVEY_COMMENTS.EMP_ID="
						+ empSurveyReport.getEmployeeCode() + " ";
			}// end of if

			query += " ORDER BY SECTION_CODE,QUESTION_CODE ";

			Object data[][] = getSqlModel().getSingleResult(query);

			Object obj[][] = new Object[data.length][3];*/

			/*if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					obj[i][0] = String.valueOf(i + 1);
					obj[i][1] = String.valueOf(data[i][0]);

					if (String.valueOf(data[i][3]).equals("O")) {
						str = getCommaSeperatedAnswer(String
								.valueOf(data[i][1]));
					} else {
						str = String.valueOf(data[i][1]);
					}

					obj[i][2] = str;
				}

			}*/

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };

			int[] cellWidth = { 20, 50, 30 };
			int[] alignment = { 0, 0, 0 };

			String colNames[] = { "SR.No.", "Survey Question", "Survey Answer" };

			String divisionNameAddQuery = " SELECT NVL(DIV_NAME,' '),NVL(DIV_ADDRESS1||' '||DIV_ADDRESS2||' '||DIV_ADDRESS3,' ') "
					+ " FROM  HRMS_DIVISION "
					+ " WHERE DIV_ID=(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empSurveyReport.getEmployeeCode().trim() + ")";

			Object divisionObj[][] = getSqlModel().getSingleResult(
					divisionNameAddQuery);

			Object[][] nameData = new Object[3][4];
			nameData[0][0] = "Survey :";
			nameData[0][1] = "";// name
			nameData[0][2] = "Section :";
			nameData[0][3] = "";
			nameData[1][0] = "Employee Name :";
			nameData[1][1] = "";
			
			if(divisionObj!=null && divisionObj.length >0)
			{
				nameData[1][2] = "Division Name :";
				nameData[1][3] = String.valueOf(divisionObj[0][0]);
				
				nameData[2][0] = "Division Address :";
				nameData[2][1] = String.valueOf(divisionObj[0][1]);
				
				nameData[2][2] = "";
				nameData[2][3] = "";
				
			}
			if (!(empSurveyReport.getSurveyName().equals(""))) {
				nameData[0][1] = empSurveyReport.getSurveyName();
			}// end of if
			if (!(empSurveyReport.getSectionName().equals(""))) {
				//nameData[0][2] = "Section :";
				nameData[0][3] = empSurveyReport.getSectionName();
			}// end of if
			if (!(empSurveyReport.getEmployeeName().equals(""))) {
				//nameData[1][0] = "Employee :";

				System.out.println("nameData[1][1] is >>>>>>>>>>>>>>"
						+ nameData[1][1]);

				nameData[1][1] = empSurveyReport.getEmployeeName();
				System.out.println("nameData[1][1] is >>>>>>>>>>>>>>"
						+ nameData[1][1]);

			}// end of if

			if (finalData != null && finalData.length > 0) {
				if (empSurveyReport.getReportType().equals("Xls")) {
					String[] name_xls = { "", "Employee Survey Report", "", "" };
					rg.xlsTableBody(name_xls, nameData, width_1, align_1);
					rg.addText("\n", 0, 0, 0);
					rg.xlsTableBody(colNames, finalData, cellWidth, alignment);
				} else if (empSurveyReport.getReportType().equals("Pdf")) {
					rg.addText("\n", 0, 0, 0);
					rg.tableBodyNoBorder(nameData, width_1, align_1);
					rg.addText("\n", 0, 0, 0);
					rg.tableBody(colNames, finalData, cellWidth, alignment);
				} else if (empSurveyReport.getReportType().equals("Txt")) {
					rg.tableBodyNoBorder(nameData, width_1, align_1);
					rg.tableBody(colNames, finalData, cellWidth, alignment);
				}
			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}

			rg.createReport(response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
