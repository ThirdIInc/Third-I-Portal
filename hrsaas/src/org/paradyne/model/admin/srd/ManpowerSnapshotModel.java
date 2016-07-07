/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.awt.Color;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.ManpowerSnapshot;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author AA0554
 *
 */
public class ManpowerSnapshotModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ManpowerSnapshotModel.class);
	
	public void getReport(ManpowerSnapshot bean,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			String reportName = "Manpwer Snapshot";
			ReportDataSet rds = new ReportDataSet();
			
			rds.setReportType(bean.getReportType());
			rds.setFileName(reportName);
			
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);

			Object [][]tiltleObj=new Object[1][1];	
			tiltleObj [0][0]="MANPOWER SNAPSHOT ";
			
			String subTiltleStr ="";
			String groupByTitle ="";
			String conditionSubQuery =" AND EMP_DIV="+bean.getDivCode();
			String joinSubQuery ="";
			String groupBySubQuery=" GROUP BY EMP_DEPT,DEPT_NAME ORDER BY DEPT_NAME ";
			
			if(bean.getSnapShotFor().equals("DP")){
				subTiltleStr ="Department :"+bean.getDeptName();
				conditionSubQuery +=" AND EMP_DEPT="+bean.getDeptCode();
			}
			else if(bean.getSnapShotFor().equals("BR")){
				subTiltleStr ="Branch :"+bean.getBranchName();
				conditionSubQuery +=" AND EMP_CENTER="+bean.getBranchCode();
			}
			else if(bean.getSnapShotFor().equals("ET")){
				subTiltleStr ="Employee Type :"+bean.getETypeName();
				conditionSubQuery +=" AND EMP_TYPE="+bean.getETypeCode();
			}
			else if(bean.getSnapShotFor().equals("CC")){
				subTiltleStr =" Cost Center :"+bean.getCostCenterName();
				conditionSubQuery +=" AND COST_CENTER_ID="+bean.getCostCenterCode();
				joinSubQuery =" INNER JOIN HRMS_SALARY_MISC T1 ON(T1.EMP_ID=HRMS_EMP_OFFC.EMP_ID)";
			}else{
				subTiltleStr="All Records";
			}
			
			String asOnFrmDateQuery1 ="SELECT EMP_DEPT,DEPT_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
				+" LEFT JOIN HRMS_DEPT ON(DEPT_ID=EMP_DEPT)"
				+ joinSubQuery
				+" WHERE EMP_REGULAR_DATE <TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
				
			String asOnFrmDateQuery2 ="SELECT EMP_DEPT,DEPT_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
				+" LEFT JOIN HRMS_DEPT ON(DEPT_ID=EMP_DEPT)"
				+ joinSubQuery
				+" WHERE EMP_LEAVE_DATE <TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ";
				
			
			String additionCountQuery ="SELECT EMP_DEPT,DEPT_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
				+" LEFT JOIN HRMS_DEPT ON(DEPT_ID=EMP_DEPT)"
				+ joinSubQuery
				+" WHERE EMP_REGULAR_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
				
			
			String exitCountQuery ="SELECT EMP_DEPT,DEPT_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
				+" LEFT JOIN HRMS_DEPT ON(DEPT_ID=EMP_DEPT)"
				+ joinSubQuery
				+" WHERE EMP_LEAVE_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') ";
				
			
			String masterQuery=" SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_NAME ";
			
			if(bean.getSnapShotGroupBy().equals("DP")){
				groupByTitle = "Department";
				asOnFrmDateQuery1+=conditionSubQuery + groupBySubQuery;
				asOnFrmDateQuery2+=conditionSubQuery + groupBySubQuery;
				additionCountQuery+=conditionSubQuery + groupBySubQuery;
				exitCountQuery+=conditionSubQuery + groupBySubQuery;
			}
			else if(bean.getSnapShotGroupBy().equals("BR")){
				groupByTitle ="Branch";
				asOnFrmDateQuery1 =asOnFrmDateQuery1.replaceAll("DEPT", "CENTER");
				asOnFrmDateQuery2 =asOnFrmDateQuery2.replaceAll("DEPT", "CENTER");
				additionCountQuery =additionCountQuery.replaceAll("DEPT", "CENTER");
				exitCountQuery =exitCountQuery.replaceAll("DEPT", "CENTER");
				groupBySubQuery =groupBySubQuery.replaceAll("DEPT", "CENTER");
				
				asOnFrmDateQuery1+=conditionSubQuery + groupBySubQuery;
				asOnFrmDateQuery2+=conditionSubQuery + groupBySubQuery;
				additionCountQuery+=conditionSubQuery + groupBySubQuery;
				exitCountQuery+=conditionSubQuery + groupBySubQuery;
				masterQuery = masterQuery.replaceAll("DEPT", "CENTER");
			}
			else if(bean.getSnapShotGroupBy().equals("ET")){
				groupByTitle ="Employee Type";
				
				asOnFrmDateQuery1 =asOnFrmDateQuery1.replaceAll("DEPT", "TYPE").replaceAll("HRMS_TYPE", "HRMS_EMP_TYPE");
				asOnFrmDateQuery2 =asOnFrmDateQuery2.replaceAll("DEPT", "TYPE").replaceAll("HRMS_TYPE", "HRMS_EMP_TYPE");
				additionCountQuery =additionCountQuery.replaceAll("DEPT", "TYPE").replaceAll("HRMS_TYPE", "HRMS_EMP_TYPE");
				exitCountQuery =exitCountQuery.replaceAll("DEPT", "TYPE").replaceAll("HRMS_TYPE", "HRMS_EMP_TYPE");
				groupBySubQuery =groupBySubQuery.replaceAll("DEPT", "TYPE").replaceAll("HRMS_TYPE", "HRMS_EMP_TYPE");
				
				asOnFrmDateQuery1+=conditionSubQuery + groupBySubQuery;
				asOnFrmDateQuery2+=conditionSubQuery + groupBySubQuery;
				additionCountQuery+=conditionSubQuery + groupBySubQuery;
				exitCountQuery+=conditionSubQuery + groupBySubQuery;
				
				masterQuery =masterQuery.replaceAll("DEPT", "TYPE").replaceAll("HRMS_TYPE", "HRMS_EMP_TYPE");;
			}
			else if(bean.getSnapShotGroupBy().equals("CC")){
				groupByTitle ="Cost Center";
				 asOnFrmDateQuery1 ="SELECT HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID =HRMS_EMP_OFFC.EMP_ID)"
					+" INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+ joinSubQuery
					+" WHERE EMP_REGULAR_DATE <TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') "
					+ conditionSubQuery
					+" GROUP BY HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME ORDER BY COST_CENTER_NAME ";
				 asOnFrmDateQuery2 ="SELECT HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID =HRMS_EMP_OFFC.EMP_ID)"
					+" INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+ joinSubQuery
					+" WHERE EMP_LEAVE_DATE <TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') "
					+ conditionSubQuery
					+" GROUP BY HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME ORDER BY COST_CENTER_NAME ";
				
				 additionCountQuery ="SELECT HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID =HRMS_EMP_OFFC.EMP_ID)"
					+" INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+ joinSubQuery
					+" WHERE EMP_REGULAR_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') "
					+ conditionSubQuery
					+" GROUP BY HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME ORDER BY COST_CENTER_NAME ";
				
				 exitCountQuery ="SELECT HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME,COUNT(HRMS_EMP_OFFC.EMP_ID) FROM HRMS_EMP_OFFC "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID =HRMS_EMP_OFFC.EMP_ID)"
					+" INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+ joinSubQuery
					+" WHERE EMP_LEAVE_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') "
					+ conditionSubQuery
					+" GROUP BY HRMS_COST_CENTER.COST_CENTER_ID,COST_CENTER_NAME ORDER BY COST_CENTER_NAME ";
				 masterQuery = "SELECT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_NAME";
			}
			
			Object asOnFrmDateObj1[][]=getSqlModel().getSingleResult(asOnFrmDateQuery1);
			Object asOnFrmDateObj2[][]=getSqlModel().getSingleResult(asOnFrmDateQuery2);
			Object additionCountObj[][]=getSqlModel().getSingleResult(additionCountQuery);
			Object exitCountObj[][]=getSqlModel().getSingleResult(exitCountQuery);
			Object masterObj[][]=getSqlModel().getSingleResult(masterQuery);
			
			asOnFrmDateObj1 =compareObject(masterObj, asOnFrmDateObj1);
			asOnFrmDateObj2 =compareObject(masterObj, asOnFrmDateObj2);
			additionCountObj =compareObject(masterObj, additionCountObj);
			exitCountObj =compareObject(masterObj, exitCountObj);
			
			int totalAsOnFrmDate=0;
			int totalAdditionCount=0;
			int totalExitCount=0;
			int totalAsOnToDate=0;
			
			Object finalObj[][]=new Object[masterObj.length+1][5];
			/*finalObj[0][0] =groupByTitle;
			finalObj[0][1] ="As on "+bean.getFromDate();
			finalObj[0][2] ="Additions";
			finalObj[0][3] ="Exits";
			finalObj[0][4] ="As on "+bean.getToDate();*/
			String []colName={groupByTitle,"As on "+bean.getFromDate(),"Additions","Exits","As on "+bean.getToDate()};
			for (int i = 0; i < finalObj.length-1; i++) {
				finalObj[i][0] =asOnFrmDateObj1[i][1];				// Group by name
				
				finalObj[i][1] =Integer.parseInt(String.valueOf(asOnFrmDateObj1[i][2]))-	
				Integer.parseInt(String.valueOf(asOnFrmDateObj2[i][2]));				// as on from date
				totalAsOnFrmDate+= Integer.parseInt(String.valueOf(finalObj[i][1]));
				
				finalObj[i][2] =Integer.parseInt(String.valueOf(additionCountObj[i][2]));			// addition during
				totalAdditionCount+= Integer.parseInt(String.valueOf(finalObj[i][2]));
				
				finalObj[i][3] =Integer.parseInt(String.valueOf(exitCountObj[i][2]));				// exit during
				totalExitCount+= Integer.parseInt(String.valueOf(finalObj[i][3]));
				
				finalObj[i][4] =Integer.parseInt(String.valueOf(finalObj[i][1]))+ Integer.parseInt(String.valueOf(finalObj[i][2]))
									- Integer.parseInt(String.valueOf(finalObj[i][3]));			// as on to date
				totalAsOnToDate+= Integer.parseInt(String.valueOf(finalObj[i][4]));
				
			}
			
			finalObj[finalObj.length-1][0] ="Total";
			finalObj[finalObj.length-1][1] =totalAsOnFrmDate;
			finalObj[finalObj.length-1][2] =totalAdditionCount;
			finalObj[finalObj.length-1][3] =totalExitCount;
			finalObj[finalObj.length-1][4] =totalAsOnToDate;
			
			TableDataSet tdsTitle = new TableDataSet();
			tdsTitle.setData(tiltleObj);
			tdsTitle.setCellAlignment(new int[] {1});
			tdsTitle.setCellWidth(new int[] { 100 });
			tdsTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
			rg.addTableToDoc(tdsTitle);
			
			
				Object [][]subTiltleObj=new Object[2][1];	
				subTiltleObj [0][0]="Division :"+bean.getDivName();
				subTiltleObj [1][0]=subTiltleStr;
				TableDataSet tdsSubTitle = new TableDataSet();
				tdsSubTitle.setData(subTiltleObj);
				tdsSubTitle.setCellAlignment(new int[] {0});
				tdsSubTitle.setCellWidth(new int[] { 30 });
				tdsSubTitle.setBodyFontDetails(Font.HELVETICA, 9, Font.BOLD, new Color(0,0,0));
				rg.addTableToDoc(tdsSubTitle);
			
			
			Object summaryTitleObj[][]=new Object [1][1];
			summaryTitleObj[0][0]="Summary";
			
			Object summaryObj[][]=new Object [4][2];
			summaryObj[0][0]="Employees on payroll as on "+bean.getFromDate()+" :";
			summaryObj[0][1]=totalAsOnFrmDate;
			summaryObj[1][0]="Additions during "+bean.getFromDate()+" and "+bean.getToDate()+" :";
			summaryObj[1][1]=totalAdditionCount;
			summaryObj[2][0]="Exits during "+bean.getFromDate()+" and "+bean.getToDate()+" :";
			summaryObj[2][1]=totalExitCount;
			summaryObj[3][0]="Employees on payroll as on "+bean.getToDate()+" :";
			summaryObj[3][1]=totalAsOnToDate;
			
			TableDataSet tdsSummaryTitle = new TableDataSet();
			tdsSummaryTitle.setData(summaryTitleObj);
			tdsSummaryTitle.setCellAlignment(new int[] {0,});
			tdsSummaryTitle.setCellWidth(new int[] { 30 });
			tdsSummaryTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
			rg.addTableToDoc(tdsSummaryTitle);
			
			TableDataSet tdsSummary= new TableDataSet();
			tdsSummary.setData(summaryObj);
			tdsSummary.setBorder(true);
			tdsSummary.setCellAlignment(new int[] {0,0});
			tdsSummary.setCellWidth(new int[] { 30,20 });
			rg.addTableToDoc(tdsSummary);
			
			//Object [][]dataObject=getSqlModel().getSingleResult("");
			
			TableDataSet tdsFinalTable = new TableDataSet();
			tdsFinalTable.setData(finalObj);
			tdsFinalTable.setCellAlignment(new int[]{0,1,1,1,1});
			tdsFinalTable.setCellWidth(new int[]{40,15,15,15,15});
			tdsFinalTable.setHeader(colName);
			tdsFinalTable.setHeaderBGColor(new Color(225,225,225));
			tdsFinalTable.setBorder(true);
			tdsFinalTable.setBlankRowsAbove(1);
			rg.addTableToDoc(tdsFinalTable);
			rg.process();
			rg.createReport(response);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public Object [][] compareObject(Object [][]masterObj,Object[][]dataObj){
		Object returnObj [][]= new Object[masterObj.length][3];
		int j = 0;
		try{
		for (int i = 0; i < returnObj.length; i++) {
			if(j<dataObj.length && String.valueOf(masterObj[i][0]).equals(String.valueOf(dataObj[j][0]))){
					returnObj[i] = dataObj[j];
					j++;
				
			}else{
				returnObj[i][0] = masterObj[i][0];
				returnObj[i][1] = masterObj[i][1];
				returnObj[i][2] = 0;
				
			}
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			for (int i = 0; i < returnObj.length; i++) {
			returnObj[i][0] = masterObj[i][0];
			returnObj[i][1] = masterObj[i][1];
			returnObj[i][2] = 0;
			}
			
		}
		return returnObj;
	}
	

}
