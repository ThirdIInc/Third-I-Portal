package org.paradyne.model.payroll;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.Form1;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.lowagie.text.Font;

public class Form2Model extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.Form1Model.class);
	private NumberFormat formatter = new DecimalFormat("#0.00");
/*
	public void report1(Form1 bean, HttpServletResponse response) {

		try {

			String name = "Form 2 ";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(this,"Pdf",name,"A4");
			rg.setFName("Form 2 ");
			rg.addTextBold("(FORM 2 REVISED)",0,2,0);
			rg.addTextBold("NOMINATION AND DECLARATION FORM FOR UNEXEMPTED/EXEMPTED ESTABLISHMENT ",0,1,0,5);
			rg.addText("\n", 0, 0, 0);
			rg.addText("Declaration and Nomination Form under the Employees’ Provident Funds and Employees’  Pension Schemes", 0,1,0);
			rg.addText("\n", 0, 1, 1);
			rg.addText("Paragraphs 33 & 61(1) of the Employees Provident Funds Scheme 1952 and Paragraph 18 of the Employees’ Pension Scheme, 1995 ", 0,1,0);
			rg.addText("\n", 0, 1, 1);

			String empQuery = " SELECT UPPER(NVL(TITLE_NAME,'')||'.'||EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME), "
					+ " FML_FNAME||' '||NVL(FML_MNAME,'')||' '||FML_LNAME,TO_CHAR(EMP_DOB,'DD-MM-YYYY') "
					+ " ,DECODE(EMP_GENDER,'M','MALE','F','FEMALE','OTHER'), "
					+ " DECODE(EMP_MARITAL_STATUS,'M','Married','U','Unmarried','W','Widow','A','Widower','D','Divorce'),NVL(SAL_GPFNO,''), "
					+ " HRMS_EMP_ADDRESS.ADD_1||' '||nvl(HRMS_EMP_ADDRESS.ADD_2,'')||' '||NVL(HRMS_EMP_ADDRESS.ADD_3,'')||'\n'||NVL(HRMS_LOCATION.LOCATION_NAME,' ')||'\n' || "
					+ " HRMS_EMP_ADDRESS.ADD_STATE||'  '||HRMS_EMP_ADDRESS.ADD_CNTRY as ad1, "
					+ " a1.ADD_1||' '||nvl(a1.ADD_2,'')||' '||NVL(a1.ADD_3,'')||'\n'||NVL(l1.LOCATION_NAME,' ')||'\n' || "
					+ " a1.ADD_STATE||'  '||a1.ADD_CNTRY as ad2 "
					+ " FROM HRMS_EMP_OFFC  "
					+ " LEFT JOIN HRMS_EMP_NOMINEE ON HRMS_EMP_NOMINEE.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE  "
					+ " inner JOIN HRMS_EMP_FML ON HRMS_EMP_FML.EMP_ID = HRMS_EMP_NOMINEE.EMP_ID AND NOM_TYPE ='F' "
					+ " LEFT JOIN HRMS_EMP_PERS ON HRMS_EMP_PERS.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " left join HRMS_SALARY_MISC on HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P' "
					+ " LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE = HRMS_EMP_ADDRESS.ADD_CITY "
					+ " LEFT JOIN HRMS_EMP_ADDRESS a1 ON a1.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='L' "
					+ " LEFT JOIN HRMS_LOCATION l1 ON l1.LOCATION_CODE = a1.ADD_CITY "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID  = " + bean.getEmpId();

			String husbandOrFatherNameQuery = " SELECT DISTINCT FML_FNAME||' '||NVL(FML_MNAME,'')||' '||FML_LNAME ,FML_GENDER,RELATION_NAME, "
					+ " CASE WHEN UPPER(RELATION_NAME) LIKE 'HUSBAND' THEN RELATION_NAME WHEN  "
					+ " UPPER(RELATION_NAME) LIKE 'FATHER' THEN RELATION_NAME ELSE NULL END "
					+ " FROM HRMS_EMP_NOMINEE "
					+ " INNER JOIN HRMS_EMP_FML ON( HRMS_EMP_FML.EMP_ID = HRMS_EMP_NOMINEE.EMP_ID AND HRMS_EMP_FML.FML_ISALIVE='Y')"
					+ " INNER JOIN HRMS_RELATION ON( HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION)"
					+ " WHERE HRMS_EMP_NOMINEE.EMP_ID="
					+ bean.getEmpId()
					+ " AND NOM_TYPE='F' ORDER BY RELATION_NAME DESC ";

			Object husbandOrFatherNameObj[][] = getSqlModel().getSingleResult(
					husbandOrFatherNameQuery);

			String str = "";

			if (husbandOrFatherNameObj != null&& husbandOrFatherNameObj.length > 0) {
				for (int i = 0; i < husbandOrFatherNameObj.length; i++) {
					if (!(String.valueOf(husbandOrFatherNameObj[i][3]).equals("null") || String.valueOf(husbandOrFatherNameObj[i][3]) == null)) {
						str = checkNull(String.valueOf(husbandOrFatherNameObj[i][0]));
						break;
					}
				}
			}

			Object empData[][] = getSqlModel().getSingleResult(empQuery);

			// Update by Anantha lakshmi
			Object[][] empDetails=new Object[4][4];
			if (empData != null && empData.length > 0) {
				empDetails[0][0]="1.Name (IN BLOCK LETTERS):" ;
				empDetails[0][1]=String.valueOf(empData[0][0])+"\n Name";
				empDetails[0][2]=str+"\n Father's/Husband's Name";
				empDetails[0][3]=str+"\n SurName";
				
				empDetails[1][0]="2.Date of Birth:"; 
				empDetails[1][1]=String.valueOf(empData[0][2]);
				empDetails[1][2]="3.Account No:";
				empDetails[1][3]=String.valueOf(empData[0][5]);
				              
				empDetails[2][0]="4.*Sex(F/M):"; 
				empDetails[2][1]=String.valueOf(empData[0][3]);
				empDetails[2][2]="5.Marital Status:";
				empDetails[2][3]=String.valueOf(empData[0][4]);
				              
				empDetails[3][0]="6.Address (Permanent/Temporary):";
				empDetails[3][1]=String.valueOf(empData[0][6])+"\n"+String.valueOf(empData[0][7]);
			}
			else{
				empDetails[0][0]="1.Name (IN BLOCK LETTERS):" ;
				empDetails[0][1]="\n Name";
				empDetails[0][2]="\n Father's/Husband's Name";
				empDetails[0][3]="\n SurName";
				
				empDetails[1][0]="2.Date of Birth:"; 
				empDetails[1][1]="";
				empDetails[1][2]="3.Account No:";
				empDetails[1][3]="";
				              
				empDetails[2][0]="4.*Sex(F/M):"; 
				empDetails[2][1]="";
				empDetails[2][2]="5.Marital Status:";
				empDetails[2][3]="";
				              
				empDetails[3][0]="6.Address (Permanent/Temporary):";
				empDetails[3][1]="";
				
			}
			rg.tableBodyNoBorder(empDetails, new int[] {25,25,25,25}, new int[] {0,0,0,0});
			rg.addText("\n", 0, 1, 1);
			String nomineeQuery =" SELECT FML_FNAME||' '||NVL(FML_MNAME,'')||' '||FML_LNAME,nvl(FML_ADDRESS,' '),RELATION_NAME,ROUND((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')- TO_DATE(TO_CHAR(FML_DOB,'DD-MM-YYYY'),'DD-MM-YYYY'))/365),NOM_SHARE,NOM_SHARE "
				+"  FROM HRMS_EMP_NOMINEE  "
				+" LEFT JOIN HRMS_EMP_FML ON(HRMS_EMP_FML.FML_ID=HRMS_EMP_NOMINEE.NOM_NOMINEE)"
				+" LEFT JOIN HRMS_RELATION ON HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION "
				+" WHERE HRMS_EMP_NOMINEE.EMP_ID ="+bean.getEmpId()+" and HRMS_EMP_NOMINEE.NOM_TYPE='F'"
				+" ORDER BY NOM_ID ";
				
				String header[] = new String[7];
				int[] cellwidth2 = { 10, 20, 20, 20, 10, 15, 15 };
				int[] alignment2 = { 1, 0, 0, 0, 1, 1, 1 };

				Object nomineeObj[][] = getSqlModel().getSingleResult(nomineeQuery);
				header[0] = "Sr.No.\n\n\n\n\n\n\n\n\n\n\n\n               1";
				header[1] = "Name of the nominee/nominees\n\n\n\n\n\n\n\n\n\n\n  2";
				header[2] = "Address\n\n\n\n\n\n\n\n\n\n\n\n\n  3";
				header[3] = "Nominee’s relationship with the member\n\n\n\n\n\n\n\n\n\n\n  4";
				header[4] = "Date of Birth\n\n\n\n\n\n\n\n\n\n\n  5";
				header[5] = "Total amount or share of accumulations in Provident Fund to be paid to each nominee\n\n\n\n\n\n  6";
				header[6] = "If the Nominee is a minor, name & relationship & address of the guardian who may receive the amount during the minority of nominee\n  7";
				Object[][] tempObj = null;
				rg.addTextBold("PART -A(EPF)", 0, 1, 0);
				rg.addFormatedText("I hereby nominate the person(s)/cancel the nomination made by me previously and nominate the person(s), mentioned below to receive the"
								   	+ " amount standing to my credit in the Employees’ Provident Fund, in the event of my death: \n", 0, 0, 0, 1, 9);
				rg.addText("\n", 0, 1, 1);
				if (nomineeObj != null && nomineeObj.length > 0) {
					try {
						tempObj = new Object[nomineeObj.length][nomineeObj[0].length + 1];
						for (int w = 0; w < nomineeObj.length; w++) {
							tempObj[w][0] = w + 1;
							for (int m = 1; m <= nomineeObj[0].length; m++) {
								tempObj[w][m] = nomineeObj[w][m - 1];
							}
						}
						nomineeObj = tempObj;
					} catch (Exception e) {}
					rg.tableBody(header, nomineeObj, cellwidth2, alignment2);
				} else{
					nomineeObj = new Object[1][header.length];
					nomineeObj = intSpace(nomineeObj);
					rg.tableBody(header, nomineeObj, cellwidth2, alignment2);
					rg.addText("No nominee records available.", 0, 1, 1);
				}	
				rg.addText("\n", 0, 1, 1);
				rg.addFormatedText("1.*Certified that I have no family as defined in Para 2(g) of the Employees’ Provident Funds Scheme, 1952 and should "
								  + " I acquire a family hereafter the above nomination should be deemed as cancelled.",
								  0, 0, 0, 1, 9);
				rg.addFormatedText("2.*Certified that my father/mother is / are dependant upon me.",0, 0, 0, 1, 9);
				rg.addText("\n", 0, 1, 1);
				rg.addText("\n", 0, 1, 1);

				Object empObj2[][] = new Object[1][2];
				int[] cellwidth3 = { 50, 50 };
				int[] alignment3 = { 0, 2 };

				empObj2[0][0] = "*Strike out whichever is not applicable";
				empObj2[0][1] = "Signature or thumb impression of the subscriber";

				rg.tableBodyNoBorder(empObj2, cellwidth3, alignment3);

				rg.addText("\n", 0, 1, 1);
				rg.addTextBold("Part -(EPS) \n", 0,1,0);
				rg.addFormatedText("(Part 18)", 0, 0, 1, 1, 10);
				rg.addFormatedText("I hereby furnish below particulars of the members of my family who would be eligible to receive"
										+ " Widow/Children Pension in the event of my death. \n",0, 0, 0, 1, 9);

				String header1[] = new String[5];
				int[] cellwidth4 = { 8, 25, 25, 15, 15 };
				int[] alignment4 = { 0, 0, 0, 0, 0 };

				String nominee2 = " SELECT FML_FNAME||' '||NVL(FML_MNAME,'')||' '||FML_LNAME||' '||NVL(FML_ADDRESS,''),NVL(FML_ADDRESS,''), "
						+ " TO_CHAR(FML_DOB,'DD-MM-YYYY'),NVL(RELATION_NAME,'')  "
						+ "	FROM HRMS_EMP_FML "
						+ "	LEFT JOIN HRMS_RELATION ON HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION "
						+ "	where HRMS_EMP_FML.EMP_ID="
						+ bean.getEmpId()
						+ " and HRMS_EMP_FML.FML_RELATION in (5,6,7,8 )  ";

				Object nomineeObj1[][] = getSqlModel().getSingleResult(nominee2);
				header1[0] = "Sr.No.\n\n  1";
				header1[1] = "Name & Address of family member\n   2";
				header1[2] = "Address\n\n    3";
				header1[3] = "Date of Birth\n\n  4";
				header1[4] = "Relationship with member\n    5";

				rg.addText("\n", 0, 1, 1);

				if (nomineeObj1 != null && nomineeObj1.length > 0) {

					try {
						tempObj = new Object[nomineeObj1.length][nomineeObj1[0].length + 1];
						for (int w = 0; w < nomineeObj1.length; w++) {
							tempObj[w][0] = w + 1;
							for (int m = 1; m <= nomineeObj1[0].length; m++) {
								tempObj[w][m] = nomineeObj1[w][m - 1];
							}
						}
						nomineeObj1 = tempObj;
					} catch (Exception e) {
					}

					rg.tableBody(header1, nomineeObj1, cellwidth4, alignment4);
				} else {
					nomineeObj1 = new Object[2][2];
					nomineeObj1 = intSpace(nomineeObj1);
					rg.tableBody(header1, nomineeObj1, cellwidth4, alignment4);
					rg.addText("No  records available.", 0, 1, 1);
				}
				rg.addText("\n", 0, 1, 1);
				rg.addFormatedText("Certified that I have no family as defined in Para 2 (vii) of the Employees’ Pension Scheme, 1971 and should acquire a family hereafter I shall furnish particulars thereon in the above form.\n",0, 0, 0, 1, 9);
				rg.pageBreak();
				rg.addFormatedText("I hereby nominate the following person for receiving monthly widow pension (admissible under para16(2)(a)(i)&(ii) in the evenet of my death without leaving any eligible family members for receiving pension).\n",	0, 0, 0, 1, 9);
				rg.addText("\n", 0, 1, 1);
				String nominee3 = " SELECT FML_FNAME||' '||NVL(FML_MNAME,'')||' '||FML_LNAME||' '||NVL(FML_ADDRESS,''), "
						+ " TO_CHAR(FML_DOB,'DD-MM-YYYY'),NVL(RELATION_NAME,'')  "
						+ "	FROM HRMS_EMP_FML "
						+ "	LEFT JOIN HRMS_RELATION ON HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION "
						+ "	where HRMS_EMP_FML.EMP_ID="
						+ bean.getEmpId()
						+ " and HRMS_EMP_FML.FML_RELATION in (5,6 )  ";

				String header2[] = new String[3];
				int[] cellwidth5 = { 33, 33, 33 };
				int[] alignment5 = { 0, 0, 0 };

				Object nomineeObj2[][] = getSqlModel().getSingleResult(nominee3);
				header2[0] = "Name & Address of the Nominee";
				header2[1] = "Date of Birth";
				header2[2] = "Relationship with member";

				if (nomineeObj2 != null && nomineeObj2.length > 0) {
					rg.tableBody(header2, nomineeObj2, cellwidth5, alignment5);
				} else {
					nomineeObj2 = new Object[2][2];
					nomineeObj2 = intSpace(nomineeObj2);
					rg.tableBody(header2, nomineeObj2, cellwidth5, alignment5);
					rg.addText("No  records available.", 0, 1, 1);
				}
				int[] cellwidthForDtSign = { 25,25,25,25 };
				int[] alignmentForDtSign = { 0, 0,0,0 };

				Object empObj3[][] = new Object[2][4];
				empObj3[0][0] = "Date : \n \n";
				empObj3[1][3] = "Signature or thumb impression of the subscriber";
				rg.addText("\n", 0, 1, 1);
				rg.tableBodyNoBorder(empObj3, cellwidthForDtSign, alignmentForDtSign);
				
				rg.addTextBold("Certificate by employer", 0, 1, 5);
				if(empData.length>0 &&empData!=null){
					rg.addText("\t\t\tCertified that the above declaration and nomination has been signed/thumb impressed before me by Shri/Smt/Miss. "
										+ String.valueOf(empData[0][0])
										+ " employed in my establishment after he/she has read the entries. The entries have been read over to him/her by me and got confirmed by him/her. \n",	0,0,1);
				}	
				else{
					rg.addText("\t\t\tCertified that the above declaration and nomination has been signed/thumb impressed before me by Shri/Smt/Miss. "
							+ "____________________"
							+ " employed in my establishment after he/she has read the entries. The entries have been read over to him/her by me and got confirmed by him/her. \n",	0,0,1);
				}
				rg.addText("\n", 0, 1, 1);
				rg.addText("\n", 0, 1, 1);

				//rg.addFormatedText("Signature of the employer or other Authorised officer of the establishment \n",
					//			0, 0, 2, 1, 9);
				
				rg.tableBodyNoBorder(empObj3, cellwidthForDtSign, alignmentForDtSign);
				rg.addText("\n", 0, 1, 1);
				rg.addText("\n", 0, 1, 1);
				Object empObj4[][] = new Object[2][6];
				int[] cellwidth6 = { 10, 5, 25, 30, 5, 25 };
				int[] alignment6 = { 0, 0, 0, 0, 0, 0 };

				empObj4[0][0] = "Place";
				empObj4[0][1] = ":";
				empObj4[0][2] = "";
				empObj4[0][3] = "Designation";
				empObj4[0][4] = ":";
				empObj4[0][5] = "";

				empObj4[1][0] = "Date";
				empObj4[1][1] = ":";
				empObj4[1][2] = "";
				empObj4[1][3] = "Name & Address of the Factory/ Establishment or rubber stamp thereof";
				empObj4[1][4] = ":";
				empObj4[1][5] = "";
				
				rg.tableBodyNoBorder(empObj4, cellwidth6, alignment6);
			    rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
*/	
	
	public void report(HttpServletRequest request, HttpServletResponse response, Form1 bean, String path, String logoPath) {
		try {
			String reportType = bean.getReportType();
			String fileName = "PF2_"+ bean.getEmpToken() +"_"+bean.getFromYear()+"-"+bean.getToYear()
			+"_"+ Utility.getRandomNumber(1000);

			String title = "Form 2";

			ReportDataSet  rds = new ReportDataSet();
			String reportPathName=fileName+"."+reportType;
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setReportHeaderRequired(false);
			rds.setFileName(fileName);
			rds.setPageSize("A4");
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
				request.setAttribute("action", "Form2_input.action?path="+reportPathName);
			}					
						
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = null;
			if (reportType.equalsIgnoreCase("pdf")) {
				obj = new Object[2][2];
				try {
					// obj[0][0] = rg.getImage(logoPath);
					// //Image.getInstance(logoPath);
					Image newLogo = Image.getInstance(logoPath);
					newLogo.setScaleToFitLineWhenOverflow(false);
					newLogo.scaleAbsolute(90f, 90f);
					obj[0][0] = newLogo;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj[0][1] = "(FORM 2 REVISED)";
				obj[1][1] = "";
				subtitleName.setData(obj);
				subtitleName.setCellAlignment(new int[] { 0, 2 });
				subtitleName.setCellWidth(new int[] { 15, 85 });
				subtitleName.setBodyFontStyle(1);
				subtitleName.setBorder(false);
				subtitleName.setHeaderTable(false);
				subtitleName.setCellColSpan(new int[] { 2, 4 });
				// rg.createHeader(subtitleName);
				rg.addTableToDoc(subtitleName);
			} else {
				obj = new Object[2][1];				
				obj[0][0] = "(FORM 2 REVISED)";
				obj[1][0] = "";
				subtitleName.setData(obj);
				subtitleName.setCellAlignment(new int[] { 2 });
				subtitleName.setCellWidth(new int[] { 100 });
				subtitleName.setBodyFontStyle(1);
				subtitleName.setBorder(false);
				subtitleName.setHeaderTable(false);
				subtitleName.setCellColSpan(new int[] { 6 });
				// rg.createHeader(subtitleName);
				rg.addTableToDoc(subtitleName);
			}
			
			subtitleName = new TableDataSet();
			Object obj1[][] = new Object[1][1];
			obj1[0][0] = "NOMINATION AND DECLARATION FORM FOR UNEXEMPTED/EXEMPTED ESTABLISHMENTS";
			subtitleName.setData(obj1);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontStyle(1);
			subtitleName.setBodyFontSize(10);
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setCellColSpan(new int[] { 6 });
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);			
			
			
			TableDataSet titleData = new TableDataSet();
			titleData.setData(new Object[][] { 
					{ "Declaration and Nomination Form under the Employees Provident Funds and Employees Pension Schemes" },
					//{ "(For Unexempted Establishment Only)"},					
					{ "(Paragraph 33 and 61 (1) of the Employees Provident Fund Scheme 1952 and Paragraph 18 of the Employees Pension Scheme 1995)" }
					});
			titleData.setCellAlignment(new int[] { 1 });
			titleData.setCellWidth(new int[] { 100 });
			//titleData.setBodyFontDetails(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0));
			titleData.setBorder(false);
			titleData.setHeaderTable(false);
			titleData.setBlankRowsBelow(1);
			titleData.setCellColSpan(new int[] { 6 });
			rg.addTableToDoc(titleData);
			
			
			String empQuery = " SELECT UPPER(NVL(TITLE_NAME,'')||'.'||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,'')||' '||HRMS_EMP_OFFC.EMP_LNAME), "
				+ " HRMS_EMP_FML.FML_FNAME||' '||NVL(HRMS_EMP_FML.FML_MNAME,'')||' '||HRMS_EMP_FML.FML_LNAME, TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY') "
				+ " ,DECODE(HRMS_EMP_OFFC.EMP_GENDER,'M','MALE','F','FEMALE','OTHER'), TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),"
				+ " DECODE(HRMS_EMP_PERS.EMP_MARITAL_STATUS,'M','Married','U','Unmarried','W','Widow','A','Widower','D','Divorce'),NVL(HRMS_SALARY_MISC.SAL_GPFNO,''), "
				+ " HRMS_EMP_ADDRESS.ADD_1||' '||NVL(HRMS_EMP_ADDRESS.ADD_2,'')||' '||NVL(HRMS_EMP_ADDRESS.ADD_3,'')||' '||NVL(HRMS_LOCATION.LOCATION_NAME,' ')||' ' || "
				+ " HRMS_EMP_ADDRESS.ADD_STATE||'  '||HRMS_EMP_ADDRESS.ADD_CNTRY AS ad1, "
				+ " a1.ADD_1||' '||NVL(a1.ADD_2,'')||' '||NVL(a1.ADD_3,'')||' '||NVL(l1.LOCATION_NAME,' ')||' ' || "
				+ " a1.ADD_STATE||'  '||a1.ADD_CNTRY AS ad2 "
				+ " FROM HRMS_EMP_OFFC  "
				+ " LEFT JOIN HRMS_EMP_NOMINEE ON HRMS_EMP_NOMINEE.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE  "
				+ " INNER JOIN HRMS_EMP_FML ON HRMS_EMP_FML.EMP_ID = HRMS_EMP_NOMINEE.EMP_ID AND HRMS_EMP_NOMINEE.NOM_TYPE ='F' "
				+ " LEFT JOIN HRMS_EMP_PERS ON HRMS_EMP_PERS.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " LEFT JOIN HRMS_SALARY_MISC on HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND HRMS_EMP_ADDRESS.ADD_TYPE='P' "
				+ " LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE = HRMS_EMP_ADDRESS.ADD_CITY "
				+ " LEFT JOIN HRMS_EMP_ADDRESS a1 ON a1.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND a1.ADD_TYPE='L' "
				+ " LEFT JOIN HRMS_LOCATION l1 ON l1.LOCATION_CODE = a1.ADD_CITY "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID  = ?";// + bean.getEmpId();
			
			Object[] parameterObj = null;
			parameterObj = new Object[1];
			parameterObj[0] = bean.getEmpId();
			
			Object empData[][] = getSqlModel().getSingleResult(empQuery,parameterObj);
			
			String familyDataQuery = " SELECT DISTINCT NVL(HRMS_EMP_FML.FML_FNAME||' '||NVL(HRMS_EMP_FML.FML_MNAME,'')||' '||HRMS_EMP_FML.FML_LNAME,' '), NVL(HRMS_EMP_FML.FML_ADDRESS,' '), HRMS_RELATION.RELATION_NAME, TO_CHAR(HRMS_EMP_FML.FML_DOB,'DD-MM-YYYY'), HRMS_EMP_NOMINEE.NOM_SHARE,' '"  
				//+ " ,CASE WHEN UPPER(RELATION_NAME) LIKE 'HUSBAND' THEN RELATION_NAME WHEN   UPPER(RELATION_NAME) LIKE 'FATHER' THEN RELATION_NAME ELSE NULL END " 
				+ " FROM HRMS_EMP_NOMINEE "
				+ " INNER JOIN HRMS_EMP_FML ON( HRMS_EMP_FML.FML_ID =HRMS_EMP_NOMINEE.NOM_NOMINEE AND HRMS_EMP_FML.FML_ISALIVE='Y')"
				+ " INNER JOIN HRMS_RELATION ON( HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION)"
				+ " WHERE HRMS_EMP_NOMINEE.EMP_ID=?"
				//+ bean.getEmpId()
				+ " AND HRMS_EMP_NOMINEE.NOM_TYPE='F' ORDER BY HRMS_RELATION.RELATION_NAME DESC ";
			
			parameterObj = new Object[1];
			parameterObj[0] = bean.getEmpId();

			Object familydataObj[][] = getSqlModel().getSingleResult(familyDataQuery, parameterObj);
			
			String nomineeQuery2 = " SELECT ROWNUM, HRMS_EMP_FML.FML_FNAME||' '||NVL(HRMS_EMP_FML.FML_MNAME,'')||' '||HRMS_EMP_FML.FML_LNAME||'--'||NVL(HRMS_EMP_FML.FML_ADDRESS,''), "
				//+ " TO_CHAR(FML_DOB,'DD-MM-YYYY'),"
				+ " CASE WHEN FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_FML.FML_DOB)/12) IS NULL THEN ' ' ELSE FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_FML.FML_DOB)/12)|| ' ' ||'YEARS' END AS age,"
				+ " NVL(HRMS_RELATION.RELATION_NAME,'')  "
				+ "	FROM HRMS_EMP_FML "
				+ "	LEFT JOIN HRMS_RELATION ON HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION "
				+ "	WHERE HRMS_EMP_FML.EMP_ID=?"
				//+ bean.getEmpId()
				+ " AND HRMS_EMP_FML.FML_RELATION IN (5,6,7,8 )  ";
			
			parameterObj = new Object[1];
			parameterObj[0] = bean.getEmpId();

			Object nomineeObj2[][] = getSqlModel().getSingleResult(nomineeQuery2,parameterObj);
			
			String nomineeQuery3 = " SELECT ROWNUM, HRMS_EMP_FML.FML_FNAME||' '||NVL(HRMS_EMP_FML.FML_MNAME,'')||' '||HRMS_EMP_FML.FML_LNAME||'--'||NVL(HRMS_EMP_FML.FML_ADDRESS,''), "
				+ " TO_CHAR(HRMS_EMP_FML.FML_DOB,'DD-MM-YYYY')," 
				+ " NVL(HRMS_RELATION.RELATION_NAME,'')  "
				+ "	FROM HRMS_EMP_FML "
				+ "	LEFT JOIN HRMS_RELATION ON HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION "
				+ "	where HRMS_EMP_FML.EMP_ID=?"
				//+ bean.getEmpId()
				+ " and HRMS_EMP_FML.FML_RELATION in (5,6 )  ";
			
			parameterObj = new Object[1];
			parameterObj[0] = bean.getEmpId();
			
			Object nomineeObj3[][] = getSqlModel().getSingleResult(nomineeQuery3,parameterObj);
			
			if(empData!=null && empData.length > 0){
				Object[][] nameObj = new Object[1][1];
				nameObj[0][0] = "1. Name (IN BLOCK LETTERS) : "+checkNull(String.valueOf(empData[0][0]));				
				
				TableDataSet nameData = new TableDataSet();
				nameData.setCellAlignment(new int[] { 0 });
				nameData.setCellWidth(new int[] { 100 });
				nameData.setData(nameObj);
				nameData.setBorder(false);
				nameData.setHeaderTable(false);
				nameData.setBlankRowsBelow(1);
				nameData.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(nameData);				
				
				Object[][] nameObj1 = new Object[1][2];
				nameObj1[0][0] = "2. Date of Birth : "+ checkNull(String.valueOf(empData[0][2]));
				nameObj1[0][1] = "3. Account No. : "+ checkNull(String.valueOf(empData[0][6]));
				
				nameData = new TableDataSet();
				nameData.setCellAlignment(new int[] { 0,0 });
				nameData.setCellWidth(new int[] { 50,50 });
				nameData.setData(nameObj1);
				nameData.setBorder(false);
				nameData.setHeaderTable(false);
				nameData.setBlankRowsBelow(1);
				nameData.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(nameData);
				
				Object[][] nameObj2 = new Object[1][2];
				nameObj2[0][0] = "4. *Sex : "+ checkNull(String.valueOf(empData[0][3]));
				nameObj2[0][1] = "5. Marital Status : "+ checkNull(String.valueOf(empData[0][5]));
				
				nameData = new TableDataSet();
				nameData.setCellAlignment(new int[] { 0,0 });
				nameData.setCellWidth(new int[] { 50,50 });
				nameData.setData(nameObj2);
				nameData.setBorder(false);
				nameData.setHeaderTable(false);
				nameData.setBlankRowsBelow(1);
				nameData.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(nameData);
				
				Object[][] nameObj3 = new Object[1][1];
				nameObj3[0][0] = "6. Address Permanent / Temporary : "
						+ (checkNull(String.valueOf(empData[0][7])).trim().equals("") ? 
								String.valueOf(empData[0][8]) : String.valueOf(empData[0][7])) ;
				
				nameData = new TableDataSet();
				nameData.setCellAlignment(new int[] { 0 });
				nameData.setCellWidth(new int[] { 100 });
				nameData.setData(nameObj3);
				nameData.setBorder(false);
				nameData.setHeaderTable(false);
				nameData.setBlankRowsBelow(1);
				nameData.setCellColSpan(new int[] { 3 });
				rg.addTableToDoc(nameData);
				
				/*
				nameObj[1][0] = "2. S/o, W/o, D/o Name : "+String.valueOf(empData[0][1]);;
				nameObj[1][1] = "";
				nameObj[1][2] = "";
				nameObj[2][2] = "5. Date of Joining : "+String.valueOf(empData[0][4]);
				nameObj[3][2] = "";
				nameObj[4][0] = "8 (A) Address Permanent : "+String.valueOf(empData[0][7]);
				nameObj[4][1] = "";
				nameObj[4][2] = "";
				nameObj[5][0] = "";
				nameObj[5][1] = "";
				nameObj[5][2] = "";
				nameObj[6][0] = "   (B) Address Temporary : "+String.valueOf(empData[0][8]);
				nameObj[6][1] = "";
				nameObj[6][2] = "";
				nameObj[7][0] = "";
				nameObj[7][1] = "";
				nameObj[7][2] = "";
				
				TableDataSet nameData = new TableDataSet();
				nameData.setCellAlignment(new int[] { 0, 0, 0 });
				nameData.setCellWidth(new int[] { 35, 35, 30 });
				nameData.setData(nameObj);
				nameData.setBorder(false);
				nameData.setHeaderTable(false);
				nameData.setBlankRowsBelow(1);
				rg.addTableToDoc(nameData);
				*/
				
				
				TableDataSet partAEPFData = new TableDataSet();
				partAEPFData.setData(new Object[][] { { "" }, { "PART – A (EPF)" } });
				partAEPFData.setCellAlignment(new int[] { 1 });
				partAEPFData.setCellWidth(new int[] { 100 });
				partAEPFData.setBodyFontStyle(1);
				partAEPFData.setBorder(false);
				partAEPFData.setHeaderTable(false);
				partAEPFData.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(partAEPFData);
				
				
				Object[][] partAEPFDeclarationObj = new Object[1][1];
				partAEPFDeclarationObj[0][0] = "I hereby nominate the person(s)/cancel " +
						"the nomination made by me previously and nominate the person(s) " +
						"mentioned below to receive the amount standing to my credit in the " +
						"Employees Provident Fund, in the event of my death." ;
				
				TableDataSet partAEPFDeclaration = new TableDataSet();
				partAEPFDeclaration.setCellAlignment(new int[] { 0 });
				partAEPFDeclaration.setCellWidth(new int[] { 100 });
				partAEPFDeclaration.setData(partAEPFDeclarationObj);
				partAEPFDeclaration.setBorder(false);
				partAEPFDeclaration.setHeaderTable(false);
				partAEPFDeclaration.setBlankRowsBelow(1);
				partAEPFDeclaration.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(partAEPFDeclaration);
				
				
				
				
				String partAHeader[][] = new String[2][6];
				partAHeader[0][0] = "Name of the Nominee(s)";
				partAHeader[0][1] = "Address";
				partAHeader[0][2] = "Nominee’s relationship with the member";
				partAHeader[0][3] = "Date of Birth";
				partAHeader[0][4] = "Total amount or share of accumulations in Provident Funds to " +
						"be paid to each nominee";
				partAHeader[0][5] = "If the nominee is minor name and address of the guardian who " +
						"may receive the amount during the minority of the nominee";
				
				partAHeader[1][0] = "1";
				partAHeader[1][1] = "2";
				partAHeader[1][2] = "3";
				partAHeader[1][3] = "4";
				partAHeader[1][4] = "5";
				partAHeader[1][5] = "6";
				
				
				int[] partAwidth = { 35, 25, 20, 20, 30, 30 };
				int[] partAalignment = { 0, 0, 0, 1, 2, 1 };
				
				Object[][] partAObject = null; 
				if(familydataObj!=null && familydataObj.length>0){
					partAObject = new Object[familydataObj.length][6];					
					for (int i = 0; i < partAObject.length; i++) {
						for (int j = 0; j < partAObject[0].length; j++) {
							partAObject[i][j]=String.valueOf(familydataObj[i][j]);
							if(j==4 && !checkNull(partAObject[i][j].toString()).equals("")){
								partAObject[i][j] = formatter.format(Double.parseDouble(partAObject[i][j].toString()));
							}
						}
					}
				}else{
					partAObject = new Object[1][6];					
					partAObject[0][0] = "NA";
					partAObject[0][1] = "NA";
					partAObject[0][2] = "NA";
					partAObject[0][3] = "NA";
					partAObject[0][4] = "NA";
					partAObject[0][5] = "NA";
				}
				
				TableDataSet partADataObj = new TableDataSet();
				partADataObj.setData(partAObject);
				partADataObj.setHeaderTable(true);
				partADataObj.setHeaderBorderDetail(3);
				partADataObj.setHeaderData(partAHeader);
				partADataObj.setCellAlignment(partAalignment);
				partADataObj.setCellWidth(partAwidth);
				partADataObj.setBorder(true);
				partADataObj.setBorderDetail(3);
				//partADataObj.setHeaderBGColor(new Color(225, 225, 225));
				partADataObj.setBlankRowsBelow(1);
				rg.addTableToDoc(partADataObj);
				
				
				Object[][] instructionsObj = new Object[2][1];
				instructionsObj[0][0] = "1. \t *Certified that I have no family as defined in para 2 (g) of " +
						"the Employees Provident Fund Scheme 1952 and should I acquire a family hereafter the " +
						"above nomination should be deemed as cancelled.";

				instructionsObj[1][0] = "2. \t * Certified that my father/mother is/are dependent upon me.";
					
				TableDataSet instructionsData = new TableDataSet();
				instructionsData.setData(instructionsObj);
				instructionsData.setCellAlignment(new int[] { 0 });
				instructionsData.setCellWidth(new int[] { 100 });
				instructionsData.setBorder(false);
				instructionsData.setHeaderTable(false);
				instructionsData.setBlankRowsBelow(2);
				instructionsData.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(instructionsData);
								
				Object[][] strikeOutObj = new Object[1][2];
				strikeOutObj[0][0] = "Strike out whichever is not applicable"; 
				strikeOutObj[0][1] = "Signature/or thumb impression of the subscriber";
				TableDataSet strikeOutData = new TableDataSet();
				strikeOutData.setData( strikeOutObj );
				strikeOutData.setCellAlignment(new int[] { 0,  2 });
				strikeOutData.setCellWidth(new int[] { 50, 50 });
				strikeOutData.setBorder(false);
				strikeOutData.setHeaderTable(false);
				strikeOutData.setBlankRowsBelow(2);
				strikeOutData.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(strikeOutData);
				
				
				TableDataSet partBEPFData = new TableDataSet();
				partBEPFData.setData(new Object[][] { { "PART B – (EPS)" }, { "Para 18" } });
				partBEPFData.setCellAlignment(new int[] { 1 });
				partBEPFData.setCellWidth(new int[] { 100 });
				partBEPFData.setBodyFontStyle(1);
				partBEPFData.setBorder(false);
				partBEPFData.setHeaderTable(false);
				partBEPFData.setBlankRowsBelow(1);
				partBEPFData.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(partBEPFData);
				
				
				Object[][] furnishobj  = new Object[1][1];
				furnishobj[0][0] = "I hereby furnish below particulars of the members of my family who would " +
						"be eligible to receive Widow/Children Pension in the event of my premature death " +
						"in service.";
				
				TableDataSet furnishData = new TableDataSet();
				furnishData.setData(furnishobj);
				furnishData.setCellAlignment(new int[] { 0 });
				furnishData.setCellWidth(new int[] { 100 });
				furnishData.setBorder(false);
				furnishData.setHeaderTable(false);
				furnishData.setBlankRowsBelow(1);
				furnishData.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(furnishData);
				
				
				String partBHeader1[][] = new String[2][4];
				partBHeader1[0][0] = "Sr.No";
				partBHeader1[0][1] = "Name & Address of the Family Member";
				partBHeader1[0][2] = "Age";
				partBHeader1[0][3] = "Relationship with the member";
				partBHeader1[1][0] = "1";
				partBHeader1[1][1] = "2";
				partBHeader1[1][2] = "3";
				partBHeader1[1][3] = "4";
				
				int[] partBwidth1 = { 15, 30, 30, 25 };
				int[] partBalignment1 = { 1, 0, 1, 0};
				
				Object[][] partBObject1 = null; 
				if(nomineeObj2!=null && nomineeObj2.length>0){
					partBObject1 = new Object[nomineeObj2.length][4];
					for (int i = 0; i < partBObject1.length; i++) {
						for (int j = 0; j < partBObject1[0].length; j++) {
							partBObject1[i][j]=String.valueOf(nomineeObj2[i][j]);
						}
					}
					
					TableDataSet partBDataObj1 = new TableDataSet();
					partBDataObj1.setData(partBObject1);
					partBDataObj1.setHeaderTable(true);
					partBDataObj1.setHeaderData(partBHeader1);
					partBDataObj1.setHeaderBorderDetail(3);
					partBDataObj1.setCellAlignment(partBalignment1);
					partBDataObj1.setCellWidth(partBwidth1);
					partBDataObj1.setBorder(true);
					partBDataObj1.setBlankRowsBelow(1);
					partBDataObj1.setBorderDetail(3);
					rg.addTableToDoc(partBDataObj1);
					
				}else{
					TableDataSet partBDataObj1 = new TableDataSet();
					partBDataObj1.setData( new Object[][] {{ "No data to display"}});
					partBDataObj1.setCellAlignment(new int[] { 1 });
					partBDataObj1.setCellWidth(new int[] { 100 });
					partBDataObj1.setBorder(false);
					partBDataObj1.setBlankRowsBelow(2);
					partBDataObj1.setCellColSpan(new int[] { 6 });
					rg.addTableToDoc(partBDataObj1);
				}	
				
				Object[][] instructionsObj2 = new Object[2][1];
				instructionsObj2[0][0] = "Certified that I have no family as defined in para 2 (vii) of the " +
						"Employees’s Family Pension Scheme 1995 and should I acquire a family hereafter I " +
						"shall furnish Particulars there on in the above form.";
				
				instructionsObj2[1][0] = "I hereby nominate the following person for receiving the monthly " +
						"widow pension (admissible under para 16 2 (a) (i) & (ii) in the event of my death " +
						"without leaving any eligible family member for receiving pension.";
				
				TableDataSet instructionsData2 = new TableDataSet();
				instructionsData2.setData(instructionsObj2);
				instructionsData2.setCellAlignment(new int[] { 0 });
				instructionsData2.setCellWidth(new int[] { 100 });
				instructionsData2.setBorder(false);
				instructionsData2.setHeaderTable(false);
				instructionsData2.setBlankRowsBelow(1);
				instructionsData2.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(instructionsData2);
				
				String partBHeader2[][] = new String[2][4];
				partBHeader2[0][0] = "Sr.No";
				partBHeader2[0][1] = "Name & address of the Nominee";
				partBHeader2[0][2] = "Date of Birth";
				partBHeader2[0][3] = "Relationship with member";
				
				partBHeader2[1][0] = "1";
				partBHeader2[1][1] = "2";
				partBHeader2[1][2] = "3";
				partBHeader2[1][3] = "4";
				
				int[] partBwidth2 = { 15, 40, 20, 20};
				int[] partBalignment2 = { 1, 0, 1, 0};
				
				Object[][] partBObject2 = null; 
				if(nomineeObj3!=null && nomineeObj2.length>0){
					partBObject2 = new Object[nomineeObj3.length][4];					
					for (int i = 0; i < partBObject2.length; i++) {
						for (int j = 0; j < partBObject2[0].length; j++) {
							partBObject2[i][j]=String.valueOf(nomineeObj3[i][j]);
						}
					}
					
					TableDataSet partBDataObj2 = new TableDataSet();
					partBDataObj2.setData(partBObject2);
					partBDataObj2.setHeaderTable(true);
					partBDataObj2.setHeaderData(partBHeader2);
					partBDataObj2.setHeaderBorderDetail(3);
					partBDataObj2.setCellAlignment(partBalignment2);
					partBDataObj2.setCellWidth(partBwidth2);
					partBDataObj2.setBorder(true);
					partBDataObj2.setBorderDetail(3);
					partBDataObj2.setBlankRowsBelow(1);
					rg.addTableToDoc(partBDataObj2);
				}else{
					TableDataSet partBDataObj2 = new TableDataSet();
					partBDataObj2.setData( new Object[][] {{ "No data to display"}});
					partBDataObj2.setCellAlignment(new int[] { 1 });
					partBDataObj2.setCellWidth(new int[] { 100 });
					partBDataObj2.setBorder(false);
					partBDataObj2.setBlankRowsBelow(2);
					partBDataObj2.setCellColSpan(new int[] { 6 });
					rg.addTableToDoc(partBDataObj2);
				}
				
				
				
				TableDataSet dateData = new TableDataSet();
				dateData.setData(new Object[][] { { "Date ___________________", " " }, { " ", " " }, 
						{ " ","Signature or thumb impression of the subscriber" }});
				dateData.setCellAlignment(new int[] { 0, 2 });
				dateData.setCellWidth(new int[] { 50, 50 });
				dateData.setBorder(false);
				dateData.setHeaderTable(false);
				dateData.setBlankRowsBelow(1);
				dateData.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(dateData);
				
				Vector blackLineVector = new Vector();
				blackLineVector.add(new BaseColor(0, 0, 0));
				blackLineVector.add(Rectangle.TOP);
				rg.addLine(blackLineVector);
				
				TableDataSet certificateTitleData = new TableDataSet();
				certificateTitleData.setData(new Object[][] {{ "CERTIFICATE BY EMPLOYER" }});
				certificateTitleData.setCellAlignment(new int[] { 1 });
				certificateTitleData.setCellWidth(new int[] { 100 });
				certificateTitleData.setBodyFontStyle(1);
				certificateTitleData.setBorder(false);
				certificateTitleData.setHeaderTable(false);
				certificateTitleData.setBlankRowsAbove(1);
				certificateTitleData.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(certificateTitleData);
				
				Object[][] instructionsObj3 = new Object[1][1];
				instructionsObj3[0][0] = " \t Certified that the above declaration and nomination has been " +
						"signed / thumb impressed before me by Shri / Smt./Miss " + 
						String.valueOf(empData[0][0]) + " employed in my establishment after he/she has " +
						"read the entries / the entries have been read over to him/her by me and got confirmed " +
						"by him/her.";					
					
				TableDataSet instructionsData3 = new TableDataSet();
				instructionsData3.setData(instructionsObj3);
				instructionsData3.setCellAlignment(new int[] { 0 });
				instructionsData3.setCellWidth(new int[] { 100 });
				//instructionsData3.setBodyFontDetails(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0));
				instructionsData3.setBorder(false);
				instructionsData3.setHeaderTable(false);
				instructionsData3.setBlankRowsBelow(3);
				instructionsData3.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(instructionsData3);
				
				Object[][] dateSignData = new Object[1][2];
				dateSignData[0][0] = "Dated : _____________ ";
				dateSignData[0][1] = "Signature of the employer or other authorised officer of the establishment";
				
				TableDataSet dateSignTds = new TableDataSet();
				dateSignTds.setData(dateSignData);
				dateSignTds.setCellAlignment(new int[] { 0,2 });
				dateSignTds.setCellWidth(new int[] { 50,50 });
				dateSignTds.setBorder(false);
				dateSignTds.setHeaderTable(false);
				dateSignTds.setBlankRowsBelow(2);
				dateSignTds.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(dateSignTds);
				
				Object[][] placeDateDataObj = new Object[2][2];
				placeDateDataObj[0][0] = " ";
				placeDateDataObj[0][1] = " Place : ";
				placeDateDataObj[1][0] = " Name & address of the Factory /Establishment ";
				placeDateDataObj[1][1] = " Date : ";

				TableDataSet placeDateData = new TableDataSet();
				placeDateData.setData(placeDateDataObj);
				/*
				placeDateData.setData(new Object[][] { { "", "", "" }, 
						{ "","Signature of the employer or other \n Authorised officers of the establishment :-","________________________" }, 
						{"Place : _____________", "", "Designation : _____________"},{"", "", ""},
						{"Dated :- _____________", "Name and address of the factory \nEstablishment or rubber stamp there of :", "_________________________"}});
				*/
				placeDateData.setCellAlignment(new int[] { 0, 0 });
				placeDateData.setCellWidth(new int[] { 50, 50 });
				//placeDateData.setBodyFontDetails(Font.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0));
				placeDateData.setBorder(false);
				placeDateData.setHeaderTable(false);
				placeDateData.setCellColSpan(new int[] { 3,3 });
				rg.addTableToDoc(placeDateData);
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { {""}, { "No family details available" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				noData.setCellColSpan(new int[] { 6 });
				rg.addTableToDoc(noData);
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
	
	public Object[][] intSpace(Object[][] tempObj) {
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "         ";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempObj;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}
