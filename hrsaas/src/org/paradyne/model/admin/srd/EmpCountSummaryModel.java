/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.awt.Color;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.EmpCountSummary;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author AA0554
 *
 */
public class EmpCountSummaryModel extends  ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmpCountSummaryModel.class);
	public void getReport(EmpCountSummary bean,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			String reportName = "Emp Head Count Summary";
			ReportDataSet rds = new ReportDataSet();
			
			rds.setReportType(bean.getReportType());
			rds.setFileName(reportName);
			
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);

			
			
			String tiltleStr ="";
			String horizontalMasterQuery="";
			String verticalMasterQuery="";
			Object horizontalMasterObj[][]=null;
			Object verticalMasterObj[][]=null;
			
			if(bean.getVerticalPara().equals("DP")){
				tiltleStr =" Department wise summary as on  :"+bean.getAsOnDate();
				verticalMasterQuery ="SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_NAME";
			}
			else if(bean.getVerticalPara().equals("BR")){
				tiltleStr =" Branch wise summary as on  :"+bean.getAsOnDate();
				verticalMasterQuery ="SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_NAME";
			}
			else if(bean.getVerticalPara().equals("ET")){
				tiltleStr =" Employee Type wise summary as on  :"+bean.getAsOnDate();
				verticalMasterQuery ="SELECT TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_NAME";
			}
			else if(bean.getVerticalPara().equals("CC")){
				tiltleStr =" Cost Center wise summary as on  :"+bean.getAsOnDate();
				verticalMasterQuery ="SELECT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_NAME";
			}
			
			if(bean.getHorizontalPara().equals("DP")){
				horizontalMasterQuery ="SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_NAME";
			}
			else if(bean.getHorizontalPara().equals("BR")){
				horizontalMasterQuery ="SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_NAME";
			}
			else if(bean.getHorizontalPara().equals("ET")){
				horizontalMasterQuery ="SELECT TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_NAME";
			}
			else if(bean.getHorizontalPara().equals("CC")){
				horizontalMasterQuery ="SELECT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_NAME";
			}
			
			horizontalMasterObj = getSqlModel().getSingleResult(horizontalMasterQuery);
			
			verticalMasterObj = getSqlModel().getSingleResult(verticalMasterQuery);
			
			Vector<Object[]> dataVector = new Vector<Object[]>();
			for (int i = 0; i < verticalMasterObj.length+1; i++) {
				Object tempObj[]=new Object[horizontalMasterObj.length+2];
				int horizontalTotal=0;
				if(i<verticalMasterObj.length){
				tempObj[0]=String.valueOf(verticalMasterObj[i][1]);
				for (int j = 1; j <= horizontalMasterObj.length; j++) {
					tempObj[j] =getEmpCount(bean,String.valueOf(verticalMasterObj[i][0]),String.valueOf(horizontalMasterObj[j-1][0]));
					horizontalTotal+= Integer.parseInt((String.valueOf(tempObj[j])));
				}
				}else{
					
				}
				tempObj[horizontalMasterObj.length+1] =horizontalTotal;
				dataVector.add(tempObj);
			}
			
			Object [][]tiltleObj=new Object[1][1];	
			tiltleObj [0][0]=tiltleStr;
			Object[][]finalObj = new Object[dataVector.size()][horizontalMasterObj.length+2];
			for (int i = 0; i < dataVector.size()-1; i++) {
				finalObj[i] = dataVector.get(i);
			}
			/*for (int i = 0; i < finalObj.length; i++) {
				for (int j = 0; j < finalObj[0].length; j++) {
					logger.info("finalObj ["+i+"]["+j+"]==="+finalObj[i][j]);
				}
			}*/
			
			for (int i = 1; i < finalObj[0].length; i++) {
				int verticalTotal=0;
				for (int j = 0; j < finalObj.length-1; j++) {
					verticalTotal += Integer.parseInt((String.valueOf(finalObj[j][i])));
				}
				finalObj[finalObj.length-1][i] = verticalTotal;
			}
			finalObj[finalObj.length-1][0]="Total";
			String []colNames=new String[horizontalMasterObj.length+2] ;
			int []align=new int[horizontalMasterObj.length+2] ;
			int []cellwidth=new int[horizontalMasterObj.length+2] ;
			colNames[0]="";
			colNames[colNames.length-1]="Total";
			cellwidth[0]=40;
			cellwidth[colNames.length-1]=15;
			align[0]=0;
			align[colNames.length-1]=1;
			for (int i = 1; i < colNames.length-1; i++) {
				colNames[i] = String.valueOf(horizontalMasterObj[i-1][1]);
				align[i]=1;
				cellwidth[i]=15;
			}
			
			TableDataSet tdsTitle = new TableDataSet();
			tdsTitle.setData(tiltleObj);
			tdsTitle.setCellAlignment(new int[] {0});
			tdsTitle.setCellWidth(new int[] { 30 });
			tdsTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
			rg.addTableToDoc(tdsTitle);
			
			Object [][]subTiltleObj=new Object[1][1];	
			subTiltleObj [0][0]="Division : "+bean.getDivName();
			TableDataSet tdsSubTitle = new TableDataSet();
			tdsSubTitle.setData(subTiltleObj);
			tdsSubTitle.setCellAlignment(new int[] {0});
			tdsSubTitle.setCellWidth(new int[] { 30 });
			tdsSubTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
			rg.addTableToDoc(tdsSubTitle);
			
			TableDataSet tdsFinalTable = new TableDataSet();
			tdsFinalTable.setData(finalObj);
			tdsFinalTable.setCellAlignment(align);
			tdsFinalTable.setCellWidth(cellwidth);
			tdsFinalTable.setHeader(colNames);
			tdsFinalTable.setHeaderBGColor(new Color(225,225,225));
			tdsFinalTable.setBorder(true);
			tdsFinalTable.setBlankRowsAbove(1);
			rg.addTableToDoc(tdsFinalTable);
			
			rg.process();
			rg.createReport(response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getEmpCount(EmpCountSummary bean,String verticalCode,String horizontalCode){
		String horizontalCondition ="";
		String verticalCondition ="";
		String horizontalJoin ="";
		String verticalJoin ="";
		
		String count="0";
		
		if(bean.getHorizontalPara().equals("DP")){
			horizontalCondition =" AND EMP_DEPT ="+horizontalCode;
		}
		else if(bean.getHorizontalPara().equals("BR")){
			horizontalCondition =" AND EMP_CENTER ="+horizontalCode;
		}
		else if(bean.getHorizontalPara().equals("ET")){
			horizontalCondition =" AND EMP_TYPE ="+horizontalCode;
		}
		else if(bean.getHorizontalPara().equals("CC")){
			horizontalCondition =" AND COST_CENTER_ID ="+horizontalCode;
			horizontalJoin =" LEFT JOIN HRMS_SALARY_MISC T1 ON(T1.EMP_ID=HRMS_EMP_OFFC.EMP_ID)";
		}
		
		if(bean.getVerticalPara().equals("DP")){
			verticalCondition =" AND EMP_DEPT ="+verticalCode;
		}
		else if(bean.getVerticalPara().equals("BR")){
			verticalCondition =" AND EMP_CENTER ="+verticalCode;
		}
		else if(bean.getVerticalPara().equals("ET")){
			verticalCondition =" AND EMP_TYPE ="+verticalCode;
		}
		else if(bean.getVerticalPara().equals("CC")){
			verticalCondition =" AND COST_CENTER_ID ="+verticalCode;
			verticalJoin =" LEFT JOIN HRMS_SALARY_MISC T1 ON(T1.EMP_ID=HRMS_EMP_OFFC.EMP_ID)";
		}
		
		String countQuery1=" SELECT NVL(COUNT(HRMS_EMP_OFFC.EMP_ID),0) FROM HRMS_EMP_OFFC "
						+horizontalJoin + verticalJoin
						+" WHERE EMP_REGULAR_DATE <=TO_DATE('"+bean.getAsOnDate()+"','DD-MM-YYYY') AND EMP_DIV= "+bean.getDivCode()
						+ horizontalCondition+verticalCondition;
		
		String countQuery2=" SELECT NVL(COUNT(HRMS_EMP_OFFC.EMP_ID),0) FROM HRMS_EMP_OFFC "
						+horizontalJoin + verticalJoin
						+" WHERE EMP_LEAVE_DATE <=TO_DATE('"+bean.getAsOnDate()+"','DD-MM-YYYY') AND EMP_DIV= "+bean.getDivCode()
						+ horizontalCondition+verticalCondition;
		
		Object [][]countObj1=getSqlModel().getSingleResult(countQuery1);
		Object [][]countObj2=getSqlModel().getSingleResult(countQuery2);
		
		count =String.valueOf(Integer.parseInt(String.valueOf(countObj1[0][0]))-
					Integer.parseInt(String.valueOf(countObj2[0][0])));
		
		return count;
	}
}
