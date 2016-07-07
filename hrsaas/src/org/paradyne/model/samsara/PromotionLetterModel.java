/**
 * 
 */
package org.paradyne.model.samsara;

import java.awt.Color;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.samsara.PromotionLetter;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author Lakkichand_Golegaonkar
 * @date 22nd August 2010
 */
public class PromotionLetterModel extends ModelBase {

	public void generateReport(HttpServletRequest request,
			HttpServletResponse response, PromotionLetter promotionLetter) {
		// TODO Auto-generated method stub

	

		String query = " SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME,'') AS TITLE,HRMS_EMP_OFFC.EMP_FNAME AS FNAME,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) "
				+ " AS EMP_NAME , NVL(HRMS_CENTER.CENTER_NAME,'') AS EMP_BRANCH ,  "
				+ " EMP_LNAME as SURNAME , NVL(TRIM(TO_CHAR(EFFECT_DATE,'Month'))||' '||TO_CHAR(EFFECT_DATE,'DD')||', '||TO_CHAR(EFFECT_DATE,'YYYY'),' ') "
				+ " AS  EFFECT_DATE , TRIM(TO_CHAR(PROM_DATE,'Month'))||' '||TO_CHAR(SYSDATE,'DD')||', '||TO_CHAR(SYSDATE,'YYYY') AS SYS_DATE , RANK_NAME AS DESIGNATION,TRIM(TO_CHAR(SYSDATE,'Month'))||' '||TO_CHAR(PROM_DATE,'DD')||', '||TO_CHAR(PROM_DATE,'YYYY') AS PROM_DATE,ROUND(NVL(HRMS_PROMOTION.OLD_CTC,0)) AS OLD_CTC ,NVL(PROM_GROSSAMT,0) AS NEW_CTC "
				+ " ,TO_CHAR(NVL(OLD_CTC,0 ),'99,99,999'),DECODE(DESG_CODE,PRO_DESG,0,1) FROM HRMS_PROMOTION LEFT JOIN HRMS_EMP_OFFC ON (EMP_ID=EMP_CODE)  "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ "	LEFT JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER) LEFT JOIN HRMS_RANK ON(RANK_ID=PRO_DESG) "
				+ "  WHERE PRO_DIV="+promotionLetter.getDivCode()+" AND EFFECT_DATE=TO_DATE('"
				+ promotionLetter.getEffDate() + "','DD-MM-YYYY')";

				if (!promotionLetter.getEmpCode().equals("")) {
					query += " AND HRMS_EMP_OFFC.EMP_ID="
							+ promotionLetter.getEmpCode();
				}
			

		Object[][] empObj = getSqlModel().getSingleResult(query);

	

		if (empObj != null && empObj.length > 0) {
			/*
			 * Object[][] empIds=new Object[empObj.length][1]; for (int i = 0; i <
			 * empObj.length; i++) { empIds[i][0]=empObj[i][0]; }
			 */
			String salCodeQuery = "  SELECT HRMS_PROMOTION.EMP_CODE,CREDIT_NAME,ROUND(NVL(NEW_AMT,0)) AS NEW_AMT_MONTHLY , CREDIT_PERIODICITY AS PERIODICITY,(CASE WHEN CREDIT_PERIODICITY='M' THEN "
					+ " (ROUND(NVL(NEW_AMT,0)) * 12) WHEN CREDIT_PERIODICITY='A' THEN ROUND(NVL(NEW_AMT,0)) WHEN  "
					+ " CREDIT_PERIODICITY='Q' THEN ROUND(NVL(NEW_AMT,0))*4 WHEN CREDIT_PERIODICITY='H'  "
					+ " THEN ROUND(NVL(NEW_AMT,0))*2 END) AS NEW_AMT_ANNUALY,TO_CHAR(HRMS_PROMOTION.EFFECT_DATE,'DD-MM-YYYY') AS EFF_DATE "
					+ " , TO_CHAR(ROUND(NVL(NEW_AMT,0)),'99,99,999')" +
							",TO_CHAR((CASE WHEN CREDIT_PERIODICITY='M' THEN "
					+ " (ROUND(NVL(NEW_AMT,0)) * 12) WHEN CREDIT_PERIODICITY='A' THEN ROUND(NVL(NEW_AMT,0)) WHEN  "
					+ " CREDIT_PERIODICITY='Q' THEN ROUND(NVL(NEW_AMT,0))*4 WHEN CREDIT_PERIODICITY='H'  "
					+ " THEN ROUND(NVL(NEW_AMT,0))*2 END),'99,99,999')" +
							" FROM HRMS_PROMOTION_SALARY  "
					+ " INNER JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE =HRMS_PROMOTION_SALARY.PROM_CODE) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=SAL_CODE) "
					+ "  WHERE  PRO_DIV="+promotionLetter.getDivCode()+" AND NVL(NEW_AMT,0)>0  "
					+ " AND HRMS_PROMOTION.EFFECT_DATE=TO_DATE('"
					+ promotionLetter.getEffDate() + "','DD-MM-YYYY') ";
			if (!promotionLetter.getEmpCode().equals("")) {
				salCodeQuery += " AND HRMS_PROMOTION.EMP_CODE="
						+ promotionLetter.getEmpCode();
			}
			salCodeQuery += " ORDER BY HRMS_PROMOTION.EMP_CODE,HRMS_CREDIT_HEAD.CREDIT_PRIORITY";

			HashMap empMap = getSqlModel().getSingleResultMap(salCodeQuery, 0,
					2);
			
			String queryTotal = "SELECT HRMS_PROMOTION.EMP_CODE , TO_CHAR(SUM((CASE WHEN CREDIT_PERIODICITY='M' THEN "
				+ " (NEW_AMT * 12) WHEN CREDIT_PERIODICITY='A' THEN NEW_AMT WHEN  "
				+ " CREDIT_PERIODICITY='Q' THEN NEW_AMT*4 WHEN CREDIT_PERIODICITY='H' "
				+ "  THEN NEW_AMT*2 END)),'99,99,999') AS NEW_AMT_ANNUALY,TO_CHAR(SUM(ROUND(NVL(NEW_AMT,0))),'99,99,999') "
				+ " , TO_CHAR(SUM((CASE WHEN CREDIT_PERIODICITY='M' THEN "
				+ " (NEW_AMT * 12) WHEN CREDIT_PERIODICITY='A' THEN NEW_AMT WHEN  "
				+ " CREDIT_PERIODICITY='Q' THEN NEW_AMT*4 WHEN CREDIT_PERIODICITY='H' "
				+ "  THEN NEW_AMT*2 END))-ROUND(NVL(HRMS_PROMOTION.OLD_CTC,0)),'99,99,999') AS INC_CTC"
				+ " FROM HRMS_PROMOTION_SALARY  "
				+ " INNER JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE =HRMS_PROMOTION_SALARY.PROM_CODE AND NVL(NEW_AMT,0)>0)"
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=SAL_CODE)"
				+ "  WHERE  PRO_DIV="+promotionLetter.getDivCode()+" AND HRMS_PROMOTION.EFFECT_DATE=TO_DATE('"
				+ promotionLetter.getEffDate() + "','DD-MM-YYYY')";
				if (!promotionLetter.getEmpCode().equals("")) {
					queryTotal += " AND HRMS_PROMOTION.EMP_CODE="
							+ promotionLetter.getEmpCode();
				}

		queryTotal += "   GROUP BY EMP_CODE	,HRMS_PROMOTION.OLD_CTC";
		HashMap empTotMap = getSqlModel().getSingleResultMap(queryTotal, 0,2);

			String title = "Promotion Letter";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Promotion Letter");
			rds.setReportName("Promotion Letter");
			rds.setReportType("Txt");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			for (int i = 0; i < empObj.length; i++) {

				String headerText = "\n\n\n\n\n"
						+ " "
						+ String.valueOf(empObj[i][10])// Prom date
						+ "\n\n" + String.valueOf(empObj[i][2])
						+ " "
						+ String.valueOf(empObj[i][4])// Mr.
						// Fname
						+ "\n"
						+ String.valueOf(empObj[i][5])// branch
						+ "\n\n\n" + "Dear " + String.valueOf(empObj[i][2]) + " "
						+ String.valueOf(empObj[i][6]) + ",";// Mr.
				// Surname
				Object[][] firstData = new Object[10][1];
				firstData[0][0]="";
				firstData[1][0]="";
				firstData[2][0]="";
				firstData[3][0]="";
				firstData[4][0]=String.valueOf(empObj[i][10]);
				firstData[5][0]="";
				firstData[6][0]= String.valueOf(empObj[i][2])
				+ " "
				+ String.valueOf(empObj[i][4]);
				
				firstData[7][0]=String.valueOf(empObj[i][5]);
				firstData[8][0]="";
				firstData[9][0]="Dear " + String.valueOf(empObj[i][2]) + " "
				+ String.valueOf(empObj[i][6]) + ",";
				
				
				TableDataSet subtitleName = new TableDataSet();
				subtitleName.setData(firstData);
				subtitleName.setCellAlignment(new int[] { 0 });
				subtitleName.setCellWidth(new int[] { 100 });
				subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				subtitleName.setBorder(false);

				rg.addTableToDoc(subtitleName);

				String startText = "Management is pleased to advise you that in appreciation of your diligence and contribution,"
						+ " you have been "; 
						if(String.valueOf(empObj[i][14]).equals("1")){
							startText+="promoted as </w:t></w:r><w:r><w:rPr><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='20'/></w:rPr><w:b/></w:rPr><w:t xml:space=\"preserve\">"+ String.valueOf(empObj[i][9])+"</w:t></w:r><w:r><w:t xml:space=\"preserve\"> and";
						}						
						startText+= " granted an increment in your Salary, effective " 
						//"<w:t><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='1'/> <w:b/></w:rPr>"
						+"</w:t></w:r><w:r><w:rPr><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='20'/></w:rPr><w:b/></w:rPr><w:t xml:space=\"preserve\">"	
						+String.valueOf(empObj[i][7])
						+"</w:t></w:r><w:r><w:t xml:space=\"preserve\">";
						startText+= " as under.";
						startText+="</w:t></w:r><w:r><w:rPr><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='20'/></w:rPr><w:b/></w:rPr><w:t xml:space=\"preserve\">"	
						+"  "+"</w:t></w:r><w:r><w:t xml:space=\"preserve\">";
						//</w:t>" + "";
						//"</w:t> "
						//" </w:t></w:r><w:r><w:rPr><w:b/></w:rPr><w:t xml:space=\"preserve\">"
					//+String.valueOf(empObj[i][7])+"</w:t></w:r><w:r><w:t>"
				//"<w:t><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='1'/> </w:rPr> 
						
				Object[][] secondData = new Object[][] { { startText } };
				TableDataSet startTextTable = new TableDataSet();
				startTextTable.setData(secondData);
				startTextTable.setCellAlignment(new int[] { 0 });
				startTextTable.setCellWidth(new int[] { 100 });
				startTextTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));
				startTextTable.setBorder(false);
				rg.addTableToDoc(startTextTable);

				Object firstobjFinal[][] = new Object[4][1];
				firstobjFinal = Utility.checkNullObjArr(firstobjFinal);
				TableDataSet tableData1 = new TableDataSet();
				tableData1.setData(firstobjFinal);
				tableData1.setCellAlignment(new int[] { 0 });
				tableData1.setCellWidth(new int[] { 30 });
				tableData1.setBorder(false);
				tableData1.setBodyCellpadding(1.5f);

				Object secondobjFinal[][] = new Object[3][2];
				secondobjFinal = Utility.checkNullObjArr(secondobjFinal);

				secondobjFinal[0][0] = "Current Cost To Company";
				secondobjFinal[0][1] = String.valueOf(empObj[i][13]);

				secondobjFinal[1][0] = "Revised Cost To Company w.e.f. "
						+ "</w:t></w:r><w:r><w:rPr><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='20'/></w:rPr><w:b/></w:rPr><w:t xml:space=\"preserve\">"+String.valueOf(empObj[i][7])+"</w:t></w:r><w:r><w:t>";
				secondobjFinal[1][1] = ((Object[][])empTotMap.get(String
						.valueOf(empObj[i][0])))[0][1];

				secondobjFinal[2][0] = "Increase in Cost To Company";
				secondobjFinal[2][1] = ((Object[][])empTotMap.get(String
						.valueOf(empObj[i][0])))[0][3];;
				TableDataSet tableData2 = new TableDataSet();
				tableData2.setData(secondobjFinal);
				tableData2.setHeader(new String[] { "", "Per Annum" });
				tableData2.setHeaderFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				tableData2.setCellAlignment(new int[] { 0, 2 });
				tableData2.setCellWidth(new int[] { 75, 25 });
				tableData2.setBorder(true);
				tableData2.setBodyCellpadding(1.5f);
				tableData2.setTablewidth(75);
				tableData2.setBodyFontDetails(Font.HELVETICA, 10, Font.NORMAL,
						new Color(0, 0, 0));

				HashMap<String, Object> mapFirst = rg.joinTableDataSet(
						tableData1, tableData2, true, 20);
				HashMap<String, Object> mapSecond = rg.joinTableDataSet(
						mapFirst, tableData1, true, 80);
				
				rg.addTableToDoc(tableData2);

				String middleText = "Your revised salary structure w.e.f "
						+ "</w:t></w:r><w:r><w:rPr><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='20'/></w:rPr><w:b/></w:rPr><w:t xml:space=\"preserve\">"+String.valueOf(empObj[i][7]) +"</w:t></w:r><w:r><w:t>";
				middleText+= " will be:";
				middleText+="</w:t></w:r><w:r><w:rPr><w:rPr><w:sz w:val='20'/><w:sz-cs w:val='20'/></w:rPr><w:b/></w:rPr><w:t xml:space=\"preserve\">"	
					+"  "+"</w:t></w:r><w:r><w:t xml:space=\"preserve\">";

				Object[][] thirdData = new Object[][] { { middleText } };
				TableDataSet middleTextTable = new TableDataSet();
				middleTextTable.setData(thirdData);
				middleTextTable.setCellAlignment(new int[] { 0 });
				middleTextTable.setCellWidth(new int[] { 100 });
				middleTextTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));
				startTextTable.setBorder(false);
				rg.addTableToDoc(middleTextTable);

				/**
				 * SALARY TABLE
				 */
				

				try {
					Object[][] salaryObj = (Object[][]) empMap.get(String
							.valueOf(empObj[i][0]));
					if (salaryObj != null && salaryObj.length > 0) {

						Object creditObj[][] = new Object[salaryObj.length + 1][3];
						creditObj = Utility.checkNullObjArr(creditObj);
						double totalMonthly = 0;
						double totalAnnualy = 0;
						for (int j = 0; j < salaryObj.length; j++) {
							creditObj[j][0] = salaryObj[j][1];
							creditObj[j][1] = salaryObj[j][6];
							creditObj[j][2] = salaryObj[j][7];
							totalMonthly += Double.parseDouble(String
									.valueOf(salaryObj[j][2]));
							totalAnnualy += Double.parseDouble(String
									.valueOf(salaryObj[j][4]));

						}
						creditObj[salaryObj.length][0] = "Total Cost to Company";
						creditObj[salaryObj.length][1] =((Object[][])empTotMap.get(String
								.valueOf(empObj[i][0])))[0][2];
						creditObj[salaryObj.length][2] = ((Object[][])empTotMap.get(String
								.valueOf(empObj[i][0])))[0][1];

						TableDataSet creditData = new TableDataSet();
						creditData.setData(creditObj);
						creditData.setHeader(new String[] { "", "Per Month",
								"Per Annum" });
						creditData.setHeaderFontDetails(Font.HELVETICA, 10,
								Font.BOLD, new Color(0, 0, 0));
						creditData.setCellAlignment(new int[] { 0, 2, 2 });
						creditData.setCellWidth(new int[] { 40, 35, 30 });
						creditData.setBorder(true);
						creditData.setBodyCellpadding(1.5f);
						creditData.setTablewidth(75);
						creditData.setBodyFontDetails(Font.HELVETICA, 10, Font.NORMAL,
								new Color(0, 0, 0));
						HashMap<String, Object> mapCredit = rg
								.joinTableDataSet(tableData1, creditData, true,
										20);
						HashMap<String, Object> mapCreditFinal = rg
								.joinTableDataSet(mapCredit, tableData1, true,
										80);
						rg.addTableToDoc(creditData);
						
						
					}

					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("Exception-  in try catch ----||||  ");
				}
				

				Object[][] bottomData = new Object[11][1] ;
				bottomData[0][0]="The other terms and conditions of your employment remain unchanged.";
				bottomData[1][0]="";
				bottomData[2][0]="Your increment arrears will be paid alongwith salary of July 2011.";
				bottomData[3][0]="";
				bottomData[4][0]="We trust that you will keep up the good work and your association with us will continue to be a happy and mutually rewarding one.";
				bottomData[5][0]="";
				bottomData[6][0]="";
				bottomData[7][0]="Yours truly,";
				bottomData[8][0]="";
				bottomData[9][0]="";
				bottomData[10][0]="";
				TableDataSet bottomTextTable = new TableDataSet();
				bottomTextTable.setData(bottomData);
				bottomTextTable.setCellAlignment(new int[] { 0 });
				bottomTextTable.setCellWidth(new int[] { 100 });
				bottomTextTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));
				startTextTable.setBorder(false);
				rg.addTableToDoc(bottomTextTable);

				Object signatoryObj[][] = new Object[2][2];
				signatoryObj = Utility.checkNullObjArr(signatoryObj);

				signatoryObj[0][0] = "Capt. G. K. Sarkari  ";

				signatoryObj[1][0] = "Managing Director   ";

				signatoryObj[0][1] = "K. B. Balmurali";
				signatoryObj[1][1] = "President - Human Resources\t  ";

				TableDataSet signatoryData = new TableDataSet();
				signatoryData.setData(signatoryObj);
				signatoryData.setCellAlignment(new int[] { 0, 0 });
				signatoryData.setCellWidth(new int[] { 65, 35 });
				signatoryData.setBorder(false);
				signatoryData.setBodyCellpadding(1.5f);
				signatoryData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				rg.addTableToDoc(signatoryData);
				rg.addProperty(rg.PAGE_BREAK);
			}

			rg.process();
			rg.createReport(response);

		}

	}

	public String getValue(Object[][] obj) {
		String str = "";
		try {
			for (int i = 0; i < obj.length; i++) {
				str += String.valueOf(obj[i][0]) + ",";
			}// end of loop

			str = str.substring(0, str.length() - 1);
		} catch (RuntimeException e) {
			// logger.error("Exception in getValue metjod : " + e);
		}

		return str;

	}

}
