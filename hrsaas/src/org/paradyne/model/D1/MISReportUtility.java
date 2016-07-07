/**
 * 
 */
package org.paradyne.model.D1;

import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.extraWorkBenefits.LateReglMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0476
 *
 */
public class MISReportUtility extends ModelBase {

	
	
	
	/**
	 * METHOD TO GENERATE REPORT
	 */
	public void getReport(String reportType,String reportTitle,String hiddenColumns,
			String reqStatus,String myPage,String exportAll,String query,int count,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try{
			//String reportType = "";
			if (reportType.equals("P")) {
				reportType = "Pdf";
			}
			if (reportType.equals("X")) {
				reportType = "Xls";
			}
			if (reportType.equals("T")) {
				reportType = "Txt";
			}
			System.out.println("reportType--------------->" + reportType + "<-------");
			System.out.println();
			String reportName = "";
			if (!reportTitle.equals(""))
				reportName = reportTitle;
			else
				reportName = "MIS Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName,"");
			rg.addText("\n", 0, 0, 0);
			
			System.out.println("report columns -------- "+hiddenColumns);
			String multiSelectValues = hiddenColumns;
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			//####int count = 0;
			//Creating Query with Count (count appended after #)
			
			//#### String queryWithCount = selectQuery(labelNames,count,splitComma,request);
			
			//#### String splitQuery[] = queryWithCount.split("#");
			//####String query = splitQuery[0];
			//####count = Integer.parseInt(splitQuery[1]);
			//String labels = splitQuery[2];

			System.out.println("counter after select query==========" + count);
			//System.out.println("labels after select query==========" + labels);
			
			// QUERY APPENDED WITH CONDITIONS
			//####query += conditionQuery(bean, labelNames); 
			
			System.out.println("\nquery -- ----------------------->\n"+query);
			Object finalObj[][] = null;
			finalObj = getSqlModel().getSingleResult(query);	
			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			String[] str_colNames = new String[count + 1];
			str_colNames[0] = "Employee Code";
			int str_colNames_array = 0;
			int[] cellWidth = new int[count + 1];
			cellWidth[0] = 10;
			int cellWidth_array = 0;
			int[] cellAlign = new int[count + 1];
			cellAlign[0] = 0;
			int cellAlign_array = 0;
			
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					System.out.println("Key....." + splitDash[0]);
					System.out.println("Value....." + splitDash[1]);
					
					for (int j = 0; j < labelNames.length; j++) {
						if(splitDash[1].trim().equals(labelNames[j].trim())){	
						str_colNames[++str_colNames_array] = labelNames[j];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
						break;
						}
					}
										
				}//End of for
			}//End of if
			
			//System.out.println("Export all values   : " + bean.getExportAll());
			System.out.println("counter for exporting==========" + reqStatus);
			
			if(finalObj != null && finalObj.length > 0){
				if(reqStatus.trim().equals("R"))
					exportAll="on";			
				if (exportAll.equals("on")) {
					if (reportType.equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, finalObj, cellWidth,
								cellAlign);
					} else if (reportType.equals("X")) {
						//rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, finalObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, finalObj, cellWidth,
								cellAlign);
					}
				}else{
					String[] pageIndex = Utility.doPaging(myPage,
							finalObj.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					int columnLength = count + 1;
					System.out.println("columnLength   : " + columnLength);
					Object[][] pageObj = new Object[numOfRec][columnLength];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < columnLength; j++) {
							pageObj[z][j] = finalObj[i][j];
						}
						z++;
						srNo++;
					}
					if (reportType.equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else if (reportType.equals("X")) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					}
				}
			}else{
				rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			}
			rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
		/**
		 * METHOD TO VIEW ON SCREEN
		 */
	
	public Object[][] callViewScreen(String query,String hiddenColumns,String myPage,
			int count,
			HttpServletRequest request, String[] labelNames) {
		// TODO Auto-generated method stub
		Object finalObj[][] = null;
		try{
			//Report Columns
			System.out.println("report columns -------- "+hiddenColumns);
			String multiSelectValues = hiddenColumns;
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			
			//Creating Query with Count (count appended after #)
			//String queryWithCount = selectQuery(misreport,labelNames,count,splitComma,request);
			
			//String splitQuery[] = queryWithCount.split("#");
			//String labels = splitQuery[2];

			System.out.println("counter after select query==========" + count);
			//System.out.println("labels after select query==========" + labels);
			
			// QUERY APPENDED WITH CONDITIONS
			
			
			System.out.println("\nquery -- ----------------------->\n"+query);
			
			finalObj = getSqlModel().getSingleResult(query);
			System.out.println("finalObj.length - "+finalObj.length);
			if(finalObj != null && finalObj.length > 0){
				String[] pageIndex = Utility.doPaging(myPage,
						finalObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1")){
					//misreport.setMyPage("1");
				}
				
				int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
				int columnLength = finalObj[0].length;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = finalObj[i][j];
					}
					z++;
				}
				
				request.setAttribute("finalObj", pageObj);				
				
			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));				
			}
		}catch(Exception e){
			
		}
		return  finalObj;
	}

	
}
