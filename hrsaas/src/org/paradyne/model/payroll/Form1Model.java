package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.Form1;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.Image;
import com.lowagie.text.Element;

public class Form1Model extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.Form1Model.class);
	private NumberFormat formatter = new DecimalFormat("#0.00");

	public void report(HttpServletRequest request, HttpServletResponse response, Form1 bean, String path) {

		try {
			String reportType = bean.getReportType();
			String title = "Form F";
			
			ReportDataSet  rds = new ReportDataSet();
			String fileName = "Gratuity_F_"+ bean.getEmpToken() +"_"+bean.getFromYear()+"-"+bean.getToYear()
				+"_"+ Utility.getRandomNumber(1000);;
			String reportPathName=fileName+"."+reportType;
			rds.setFileName(fileName);
			rds.setReportName(title);
			rds.setReportType(reportType);
			//rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportHeaderRequired(false);
			//rds.setPageSize("A3");
			rds.setTotalColumns(8);
			ReportGenerator rg = null;
			
			if(path.equals("")){
				rg = new ReportGenerator(rds,session,context, request);
				new ReportGenerator(rds,session,context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+path+fileName+"."+reportType);
				rg = new ReportGenerator(rds,path,session,context, request);
				request.setAttribute("reportPath", path+fileName+"."+reportType);
				request.setAttribute("fileName", fileName + "."	+ reportType);
				request.setAttribute("action", "Form1_input.action?path="+reportPathName);
			}		
			
			Object tilteObj1[][] = null;
			TableDataSet subtitleName = null;
			subtitleName = new TableDataSet();
			tilteObj1 = new Object[1][1];
			tilteObj1[0][0] = "FORM 'F'";
			subtitleName.setData(tilteObj1);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontStyle(1);
			subtitleName.setBodyFontSize(13);
			subtitleName.setBorder(false);
			subtitleName.setBlankRowsBelow(1);
			subtitleName.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(subtitleName);
			
			Object tilteObj2[][] = new Object[1][1];
			subtitleName = new TableDataSet();
			tilteObj2[0][0] = "[See Sub rule (1) of 6]";
			subtitleName.setData(tilteObj2);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			//subtitleName.setBodyFontStyle(1);
			subtitleName.setBorder(false);
			subtitleName.setBlankRowsBelow(1);
			subtitleName.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(subtitleName);
			
			Object tilteObj3[][] = new Object[1][1];
			subtitleName = new TableDataSet();
			tilteObj3[0][0] = "NOMINATION";
			subtitleName.setData(tilteObj3);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontStyle(1);
			subtitleName.setBorder(false);
			subtitleName.setBlankRowsBelow(1);
			subtitleName.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(subtitleName);
			
			String EmpSql = " SELECT HRMS_DIVISION.DIV_NAME,"
				+ " NVL(HRMS_DIVISION.DIV_ADDRESS1,'') ||' '|| NVL(HRMS_DIVISION.DIV_ADDRESS2,'') ||' '|| NVL(HRMS_DIVISION.DIV_ADDRESS3,'') " 
				+ " FROM HRMS_DIVISION " 
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_DIV = HRMS_DIVISION.DIV_ID) "				
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = ?";// + bean.getEmpId();
			
			Object[] parameterObj = null;
			parameterObj = new Object[1];
			parameterObj[0] = bean.getEmpId();  
			
			Object[][] divData = getSqlModel().getSingleResult(EmpSql,parameterObj);
			
			Object objTo[][] = new Object[3][1];
			TableDataSet tableDataSet = new TableDataSet();
			objTo[0][0] = "To,";
			objTo[1][0] = " " + checkNull(String.valueOf(divData[0][0]));
			objTo[2][0] = " " + checkNull(String.valueOf(divData[0][1]));
			
			tableDataSet.setData(objTo);
			tableDataSet.setCellAlignment(new int[] { 0 });
			tableDataSet.setCellWidth(new int[] { 100 });
			tableDataSet.setBorder(false);
			tableDataSet.setBlankRowsBelow(1);
			rg.addTableToDoc(tableDataSet);
			
			String empQuery = " SELECT NVL(HRMS_TITLE.TITLE_NAME,'')||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,'')||' '||HRMS_EMP_OFFC.EMP_LNAME," 
					+ " DECODE(HRMS_EMP_OFFC.EMP_GENDER,'M','MALE','F','FEMALE','OTHER'),NVL(HRMS_RELIGION.RELIGION_NAME,' '), "
					+ " DECODE(HRMS_EMP_PERS.EMP_MARITAL_STATUS,'M','Married','U','Unmarried','W','Widow','A','Widower','D','Divorce')," 
					+ " HRMS_DEPT.DEPT_NAME ||' - '|| HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY')," 
					+ " HRMS_EMP_ADDRESS.ADD_1||' '||NVL(HRMS_EMP_ADDRESS.ADD_2,'')||' '||NVL(HRMS_EMP_ADDRESS.ADD_3,''),NVL(HRMS_LOCATION.LOCATION_NAME,' ')," 
					+ " NVL(HRMS_EMP_ADDRESS.ADD_STATE,' ') "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_EMP_PERS ON HRMS_EMP_PERS.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ " LEFT JOIN HRMS_RELIGION ON HRMS_RELIGION.RELIGION_ID =  HRMS_EMP_PERS.EMP_RELIGION "
					+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT "
					+ " LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND HRMS_EMP_ADDRESS.ADD_TYPE='P' "
					+ " LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE = HRMS_EMP_ADDRESS.ADD_CITY "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID  = ?";// + bean.getEmpId();
			
			parameterObj = new Object[1];
			parameterObj[0] = bean.getEmpId();;  
			
			Object empData[][] = getSqlModel().getSingleResult(empQuery,parameterObj);

			if (empData != null && empData.length > 0) {

				String message1 = "1. I, " + String.valueOf(empData[0][0])
						+ " whose particulars are given in the statement below, hereby nominate the person(s)"
						+ " mentioned below to receive the gratuity payable after my death as also the gratuity"
						+ " standing to my credit in the event of my death before that amount has become"
						+ " payable, or having become payable has not been paid and direct that the said"
						+ " amount of gratuity shall be paid in proportion indicated against the name(s)"
						+ " of the nominee(s).";

				String message2 = "2. I hereby certify that the person(s) mentioned is/are a member(s)"
						+ " of my family within the meaning of clause (h) of Section 2 of the Payment of"
						+ " Gratuity Act, 1972.";

				String message3 = "3. I hereby declare that I have no family within the meaning of clause"
						+ " (h) of Section 2 of the said Act. of the Payment of Gratuity Act,1972.";

				String message4 = "4. (a) My father/mother/parents is/are not dependent on me. "
						+ " \n   (b) My husband's father/mother/parents is/are not dependent on my husband.";

				String message5 = "5. I have excluded my husband from my family by a notice dated the"
						+ " to the controlling authority in terms of the proviso to clause (h) of Section 2"
						+ " of the said Act.";

				String message6 = "6. Nomination made herein invalidates my previous nomination.";

				//rg.addFormatedText(message1, 0, 0, 0, 0, 9);
				Object msg1[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				msg1[0][0] = message1;
				tableDataSet.setData(msg1);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);
				
				//rg.addFormatedText(message2, 0, 0, 0, 0, 9);
				Object msg2[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				msg2[0][0] = message2;
				tableDataSet.setData(msg2);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);
				
				
				//rg.addFormatedText(message3, 0, 0, 0, 0, 9);
				Object msg3[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				msg3[0][0] = message3;
				tableDataSet.setData(msg3);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);
				
				
				//rg.addFormatedText(message4, 0, 0, 0, 0, 9);
				Object msg4[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				msg4[0][0] = message4;
				tableDataSet.setData(msg4);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);
				
				//rg.addFormatedText(message5, 0, 0, 0, 0, 9);
				Object msg5[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				msg5[0][0] = message5;
				tableDataSet.setData(msg5);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);

				//rg.addFormatedText(message6, 0, 0, 0, 0, 9);
				Object msg6[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				msg6[0][0] = message6;
				tableDataSet.setData(msg6);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);


				//rg.addFormatedText("NOMINEE(S)", 0, 0, 1, 1, 13);
				Object nomineeTitle[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				nomineeTitle[0][0] = "Nominee(s)";
				tableDataSet.setData(nomineeTitle);
				tableDataSet.setCellAlignment(new int[] { 1 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBodyFontStyle(1);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);				
				
				String header[][] = new String[2][5];
				int[] cellwidth = { 10,25, 25, 15, 25 };
				int[] alignment = { 1, 0, 0, 1, 2 };

				/*String nomineeQuery = " SELECT FML_FNAME||' '||NVL(FML_MNAME,'')||' '||FML_LNAME ||'--'||nvl(FML_ADDRESS,' '),RELATION_NAME,ROUND((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(FML_DOB,'DD-MM-YYYY'),'DD-MM-YYYY'))/365),NOM_SHARE "
						+ " FROM HRMS_EMP_NOMINEE "
						+ " INNER JOIN HRMS_EMP_FML ON HRMS_EMP_FML.EMP_ID = HRMS_EMP_NOMINEE.EMP_ID "
						+ " LEFT JOIN HRMS_RELATION ON HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION "
						+ " WHERE HRMS_EMP_NOMINEE.EMP_ID ="
						+ bean.getEmpId()
						+ " AND NOM_TYPE ='G'";*/
				
				
				String nomineeQuery =" SELECT " 
					+" HRMS_EMP_FML.FML_FNAME||' '||NVL(HRMS_EMP_FML.FML_MNAME,'')||' '||HRMS_EMP_FML.FML_LNAME ||' -- '||NVL(HRMS_EMP_FML.FML_ADDRESS,' ')"
					+"  ,HRMS_RELATION.RELATION_NAME,ROUND((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(HRMS_EMP_FML.FML_DOB,'DD-MM-YYYY'),'DD-MM-YYYY'))/365),HRMS_EMP_NOMINEE.NOM_SHARE "
					+"  FROM HRMS_EMP_NOMINEE  "
					+" LEFT JOIN HRMS_EMP_FML ON(HRMS_EMP_FML.FML_ID=HRMS_EMP_NOMINEE.NOM_NOMINEE)"
					+" LEFT JOIN HRMS_RELATION ON HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION "
					+" WHERE HRMS_EMP_NOMINEE.EMP_ID =? AND HRMS_EMP_NOMINEE.NOM_TYPE='G'"
					+" ORDER BY HRMS_EMP_NOMINEE.NOM_ID ";
			
				parameterObj = new Object[1];
				parameterObj[0] = bean.getEmpId();;  
				
				Object nomineeObj[][] = getSqlModel().getSingleResult(nomineeQuery, parameterObj);
				header[0][0] = "Sr.No.";
				header[0][1] = "Name in full with full address of nominee(s)";
				header[0][2] = "Relationship with the employee";
				header[0][3] = "Age of nominee";
				header[0][4] = "Proportion by which the gratuity will be shared";
				
				header[1][0] = "[1]";
				header[1][1] = "[2]";
				header[1][2] = "[3]";
				header[1][3] = "[4]";
				header[1][4] = "[5]";
				
				Object[][] tempObj = null;
				
				if (nomineeObj != null && nomineeObj.length > 0){					
					try {
						tempObj = new Object[nomineeObj.length][nomineeObj[0].length + 1];
						for (int w = 0; w < nomineeObj.length; w++) {
							tempObj[w][0] = w + 1;
							for (int m = 1; m <= nomineeObj[0].length; m++) {
								tempObj[w][m] = nomineeObj[w][m-1];
								if(m==4 && !checkNull(tempObj[w][m].toString()).equals("")){
									tempObj[w][m] = formatter.format(Double.parseDouble(tempObj[w][m].toString()));
								}
							}
						}
						nomineeObj = tempObj;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//rg.tableBody(header, nomineeObj, cellwidth, alignment);
					
					tableDataSet = new TableDataSet();
					tableDataSet.setData(nomineeObj);
					tableDataSet.setHeaderData(header);
					tableDataSet.setHeaderTable(true);
					tableDataSet.setHeaderBorderDetail(3);
					tableDataSet.setCellAlignment(alignment);
					tableDataSet.setCellWidth(cellwidth);
					tableDataSet.setBorder(true);
					tableDataSet.setBorderDetail(3);
					tableDataSet.setBlankRowsBelow(1);
					rg.addTableToDoc(tableDataSet);				
					
				} else {
					//rg.addText("No nominee records available.", 0, 1, 1);
					Object noNominee[][] = new Object[1][1];
					tableDataSet = new TableDataSet();
					noNominee[0][0] = "No nominee records available.";
					tableDataSet.setData(noNominee);
					tableDataSet.setCellAlignment(new int[] { 1 });
					tableDataSet.setCellWidth(new int[] { 100 });
					tableDataSet.setBorder(false);
					tableDataSet.setBlankRowsBelow(1);
					tableDataSet.setCellColSpan(new int[] { 5 });
					rg.addTableToDoc(tableDataSet);
				}

				rg.addProperty(rg.PAGE_BREAK);
				
				Object statement[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				statement[0][0] = "Statement";
				tableDataSet.setData(statement);
				tableDataSet.setCellAlignment(new int[] { 1 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBodyFontStyle(1);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);
				
				
				Object empObj[][] = new Object[8][3];

				empObj[0][0] = "1. Name of the employee in full";
				empObj[0][1] = " : ";
				empObj[0][2] = String.valueOf(empData[0][0]).trim();

				empObj[1][0] = "2. Sex";
				empObj[1][1] = " : ";
				empObj[1][2] = String.valueOf(empData[0][1]);

				empObj[2][0] = "3. Religion";
				empObj[2][1] = " : ";
				empObj[2][2] = String.valueOf(empData[0][2]);

				empObj[3][0] = "4. Whether unmarried/married/widow/widower";
				empObj[3][1] = " : ";
				empObj[3][2] = String.valueOf(empData[0][3]);

				empObj[4][0] = "5. Department/Branch/Section where employed";
				empObj[4][1] = " : ";
				empObj[4][2] = String.valueOf(empData[0][4]);

				empObj[5][0] = "6. Post held with Ticket No. or Serial No., if any";
				empObj[5][1] = " : ";
				empObj[5][2] = String.valueOf(empData[0][5]);

				empObj[6][0] = "7. Date of appointment";
				empObj[6][1] = " : ";
				empObj[6][2] = String.valueOf(empData[0][6]);

				empObj[7][0] = "8. Permanent Address";
				empObj[7][1] = " : ";
				empObj[7][2] = String.valueOf(empData[0][7]);

				int[] cellwidth1 = { 40, 5, 55 };
				int[] alignment1 = { 0, 0, 0 };

				//rg.tableBodyNoBorder(empObj, cellwidth1, alignment1);
				tableDataSet = new TableDataSet();
				tableDataSet.setData(empObj);
				tableDataSet.setCellAlignment(alignment1);
				tableDataSet.setCellWidth(cellwidth1);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				rg.addTableToDoc(tableDataSet);	

				Object empObj1[][] = new Object[3][6];

				empObj1[0][0] = "Village ";
				empObj1[0][1] = ":";
				empObj1[0][2] = "Thana ";
				empObj1[0][3] = ":";
				empObj1[0][4] = "Subdivision ";
				empObj1[0][5] = ":";
				
				empObj1[1][0] = "Post Office ";
				empObj1[1][1] = ":";
				empObj1[1][2] = "District ";
				empObj1[1][3] = ":" + String.valueOf(empData[0][8]);
				empObj1[1][4] = "State ";
				empObj1[1][5] = ":" + String.valueOf(empData[0][9]);

				int[] cellwidth2 = { 10, 15, 10, 15, 10, 15 };
				int[] alignment2 = { 0, 0, 0, 0, 0, 0 };

				//rg.tableBodyNoBorder(empObj1, cellwidth2, alignment2);
				tableDataSet = new TableDataSet();
				tableDataSet.setData(empObj1);
				tableDataSet.setCellAlignment(alignment2);
				tableDataSet.setCellWidth(cellwidth2);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(2);
				rg.addTableToDoc(tableDataSet);	

				Object empObj2[][] = new Object[2][4];

				empObj2[0][0] = "Place ";
				empObj2[0][1] = ":";
				empObj2[0][2] = "";
				empObj2[0][3] = "";

				empObj2[1][0] = "Date ";
				empObj2[1][1] = ":";
				empObj2[1][2] = "";
				empObj2[1][3] = "Signature/Thumb impression of the employee ";

				int[] cellwidth3 = { 15, 5, 25, 45 };
				int[] alignment3 = { 0, 0, 0, 1 };

				//rg.tableBodyNoBorder(empObj2, cellwidth3, alignment3);
				tableDataSet = new TableDataSet();
				tableDataSet.setData(empObj2);
				tableDataSet.setCellAlignment(alignment3);
				tableDataSet.setCellWidth(cellwidth3);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				rg.addTableToDoc(tableDataSet);	
				
				//rg.addFormatedText("\nDeclaration by witnesses", 0, 0, 1,1, 12);
				Object witnessTitle[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				witnessTitle[0][0] = "Declaration by witnesses";
				tableDataSet.setData(witnessTitle);
				tableDataSet.setCellAlignment(new int[] { 1 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBodyFontStyle(1);
				tableDataSet.setBlankRowsAbove(1);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);			
				
				//rg.addFormatedText("Nomination signed/thumb impressed before me.\n", 0, 0,0, 0, 9);
				Object witnessTitle1[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				witnessTitle1[0][0] = "Nomination signed/thumb impressed before me.";
				tableDataSet.setData(witnessTitle1);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBodyFontSize(10);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);		
				
				Object empObj3[][] = new Object[3][2];

				empObj3[0][0] = "Names in full and full addresses of witnesses";
				empObj3[0][1] = "Signature of witnesses";

				empObj3[1][0] = "1";
				empObj3[1][1] = "1";

				empObj3[2][0] = "2";
				empObj3[2][1] = "2";

				int[] cellwidth4 = { 50, 50 };
				int[] alignment4 = { 0, 0 };

				//rg.tableBodyNoBorder(empObj3, cellwidth4, alignment4);
				tableDataSet = new TableDataSet();
				tableDataSet.setData(empObj3);
				tableDataSet.setCellAlignment(alignment4);
				tableDataSet.setCellWidth(cellwidth4);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(tableDataSet);	

				Object empObj4[][] = new Object[2][3];

				empObj4[0][0] = "Place";
				empObj4[0][1] = ":";
				empObj4[0][2] = "";

				empObj4[1][0] = "Date";
				empObj4[1][1] = ":";
				empObj4[0][2] = "";

				int[] cellwidth5 = { 15, 5, 80 };
				int[] alignment5 = { 0, 0, 0 };

				//rg.tableBodyNoBorder(empObj4, cellwidth5, alignment5);
				tableDataSet = new TableDataSet();
				tableDataSet.setData(empObj4);
				tableDataSet.setCellAlignment(alignment5);
				tableDataSet.setCellWidth(cellwidth5);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				rg.addTableToDoc(tableDataSet);	

				//rg.addFormatedText("\nCertificate by employer", 0, 0, 1, 1, 12);
				Object certificationTitle[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				certificationTitle[0][0] = "Certificate by employer";
				tableDataSet.setData(certificationTitle);
				tableDataSet.setCellAlignment(new int[] { 1 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBodyFontStyle(1);
				tableDataSet.setBlankRowsAbove(1);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);	
				
				
				/*rg.addFormatedText(
								"Certified that the particulars of the above nomination have been verified and recorded in his establishments.",
								0, 0, 0, 0, 9);
				*/
				Object certificationTitle1[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				certificationTitle1[0][0] = "Certified that the particulars of the above nomination have been verified and recorded in his establishments.";
				tableDataSet.setData(certificationTitle1);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);	
				
				//rg.addFormatedText("Employer's Reference No.,if any\n", 0, 0,0, 0, 9);
				Object certificationTitle2[][] = new Object[1][2];
				tableDataSet = new TableDataSet();
				certificationTitle2[0][0] = "Employer's Reference No.,if any";
				certificationTitle2[0][1] = "Signature of the employer/officer authorised Designation";
				tableDataSet.setData(certificationTitle2);
				tableDataSet.setCellAlignment(new int[] { 0,0 });
				tableDataSet.setCellWidth(new int[] { 50,50 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(2);
				tableDataSet.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(tableDataSet);	
				
				Object empObj5[][] = new Object[1][4];
				empObj5[0][0] = "Date";
				empObj5[0][1] = ":";
				empObj5[0][2] = "";
				empObj5[0][3] = "Name and address of the establishment or rubber stamp thereof.";

				//rg.tableBodyNoBorder(empObj5, cellwidth3, alignment3);
				tableDataSet = new TableDataSet();
				tableDataSet.setData(empObj5);
				tableDataSet.setCellAlignment(alignment3);
				tableDataSet.setCellWidth(cellwidth3);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				rg.addTableToDoc(tableDataSet);	

				//rg.addFormatedText("\nAcknowledgement by employee", 0, 0, 1, 1,	12);
				Object acknowledgementTitle[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				acknowledgementTitle[0][0] = "Acknowledgement by the Employee";
				tableDataSet.setData(acknowledgementTitle);
				tableDataSet.setCellAlignment(new int[] { 1 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBodyFontStyle(1);
				tableDataSet.setBlankRowsAbove(1);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);	
				
				/*rg.addFormatedText(
								"Received the duplicte copy of the nomination in Form F filled by me and duly  certified by employer.",
								0, 0, 0, 0, 9);
				*/
				Object acknowledgementTitle1[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				acknowledgementTitle1[0][0] = "Received the duplicate copy of nomination in Form 'F' filed by me and duly certified by the employer.";
				tableDataSet.setData(acknowledgementTitle1);
				tableDataSet.setCellAlignment(new int[] { 0 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);	
				
				//rg.addFormatedText("Employer's Reference No.,if any", 0, 0, 0,0, 9);
				
				
				Object empObj6[][] = new Object[1][4];
				empObj6[0][0] = "Date";
				empObj6[0][1] = ":";
				empObj6[0][2] = "";
				empObj6[0][3] = "Signature of the Employee";
				//rg.tableBodyNoBorder(empObj6, cellwidth5, alignment5);
				tableDataSet = new TableDataSet();
				tableDataSet.setData(empObj6);
				tableDataSet.setCellAlignment(alignment3);
				tableDataSet.setCellWidth(cellwidth3);
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(1);
				rg.addTableToDoc(tableDataSet);	
				
			} else {
				//rg.addText("No records avaliable for selected criteria", 0, 1, 1);
				Object noRecord[][] = new Object[1][1];
				tableDataSet = new TableDataSet();
				noRecord[0][0] = "No records avaliable for selected criteria";
				tableDataSet.setData(noRecord);
				tableDataSet.setCellAlignment(new int[] { 1 });
				tableDataSet.setCellWidth(new int[] { 100 });
				tableDataSet.setBorder(false);
				tableDataSet.setBlankRowsBelow(2);
				tableDataSet.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(tableDataSet);	
			}
			
			rg.process();
			if(path.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

}
