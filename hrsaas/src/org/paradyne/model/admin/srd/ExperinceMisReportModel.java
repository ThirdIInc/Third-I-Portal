/**
 * @author GTL-AA1614
 *
 */
package org.paradyne.model.admin.srd;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.srd.ExperinceMisReport;
import org.paradyne.bean.admin.srd.FamilyMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

public class ExperinceMisReportModel extends ModelBase {
	public String getReport(ExperinceMisReport rpt,	HttpServletResponse response) {		
		String reportName = "Experience MIS Report";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				rpt.getReportType(), reportName,"A4");
		rg.addTextBold(" Experience MIS Report", 0, 1, 0);
			
		//Added By Nilesh on 20th June 2011. 
		//Checkbox added on JSP  for generating different MIS Reports on checkbox if it is checked or unchecked..
 		if(!rpt.getCheckFlag().equals("Y"))
					{
			System.out.println("rpt.getCheckFlag()===== "+rpt.getCheckFlag());
			String relquery="";
			int[] attCellWidth = { 60 , 80 , 60 , 80 , 80 , 80 , 80 , 80 , 80 , 60 , 80 , 80 , 60 , 60 , 60 , 80};
			int []attAlign={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			String []attCol={"Employee","Name","Status","Emp Type","Department","Branch","Division","Designation","Employer","Job Title","Job Description","Joining Date","Relieving Date","Last Salary","Scale of Pay","Years of Experince"};
				 relquery="SELECT EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),TO_CHAR(TYPE_NAME),DEPT_NAME,(HRMS_CENTER.CENTER_NAME),DIV_NAME,HRMS_RANK.RANK_NAME,"
				+ " EXP_EMPLOYER,EXP_JOBTITLE,EXP_JOBDESC,TO_CHAR(EXP_JOINING_DATE,'DD-MM-YYYY'),TO_CHAR(EXP_RELIEVING_DATE,'DD-MM-YYYY'),"
				+ " EXP_LASTSAL,EXP_SCALE_OF_PAY,"
				+ " FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)/12)||' '||'Years'||' '|| FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)-FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)/12) *12) ||' '||'Months'" + " FROM HRMS_EMP_EXP INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_EXP.EMP_ID) "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ " LEFT JOIN HRMS_DEPT ON (dept_id = emp_dept)"
				+ " LEFT JOIN HRMS_EMP_TYPE on (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ "	LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV WHERE 1=1  ";
				if (!(rpt.getEmpid().equals(""))
						&& !(rpt.getEmpid() == null)
						&& !rpt.getEmpid().equals("null")) {
					relquery += " AND HRMS_EMP_OFFC.EMP_ID =" + rpt.getEmpid();
				}
				if (!(rpt.getCenterId().equals(""))
					&& !(rpt.getCenterId() == null)
					&& !rpt.getCenterId().equals("null")) {
					relquery += " AND HRMS_EMP_OFFC.EMP_CENTER="+ rpt.getCenterId() + " ";
				}
			    if (!(rpt.getDivCode().equals(""))
			    	&& !(rpt.getDivCode() == null)
			    	&& !rpt.getDivCode().equals("null")) {
					relquery += " AND HRMS_EMP_OFFC.EMP_DIV=" + rpt.getDivCode()+ " ";
				}
				if (!(rpt.getDeptCode().equals(""))
				    && !(rpt.getDeptCode() == null)
				    && !rpt.getDeptCode().equals("null")) {
					relquery += " AND HRMS_EMP_OFFC.EMP_DEPT="+ rpt.getDeptCode() + " ";
				}
				if (!(rpt.getDesgCode().equals(""))
					&& !(rpt.getDesgCode() == null)
					&& !rpt.getDesgCode().equals("null")) {
					relquery += " AND HRMS_EMP_OFFC.EMP_RANK="+ rpt.getDesgCode() + " ";
				}
				if (!(rpt.getStatus().equals("-1"))){
					relquery += " AND HRMS_EMP_OFFC.EMP_STATUS='"+ rpt.getStatus() + "'";
				}
				relquery+= " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
				/*relquery="SELECT EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),TO_CHAR(TYPE_NAME),DEPT_NAME,(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME," 
				  + " EXP_EMPLOYER,EXP_JOBTITLE,EXP_JOBDESC,TO_CHAR(EXP_JOINING_DATE,'DD-MM-YYYY')," 
				  + " TO_CHAR(EXP_RELIEVING_DATE,'DD-MM-YYYY')," 
				  + " EXP_LASTSAL,EXP_SCALE_OF_PAY," 
				  + " FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)/12)||' '||'Years'||' '|| FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)-FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)/12) *12) ||' '||'Months'" 
				  + " FROM HRMS_EMP_EXP INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_EXP.EMP_ID)" 
				  + " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)" 
				  + " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)" 
				  + " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)" 
				  + " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)" 
				  + " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)" 
				  + " ORDER BY HRMS_EMP_OFFC.EMP_ID";*/	
				Object emprelativesData[][] = getSqlModel().getSingleResult(relquery);
				if(!(emprelativesData==null || emprelativesData.length==0))
				{
					  rg.setFName("Experience");
					  rg.addFormatedText("\n",0, 0, 1, 0);
					  if(rpt.getReportType().equals("Xls"))
						{
							rg.tableBody(attCol, emprelativesData, attCellWidth,attAlign);
							rg.addText("\n\n",0, 0, 0);
						}
						if(rpt.getReportType().equals("Pdf"))
						{
							rg.tableBody(attCol, emprelativesData, attCellWidth,attAlign);		
						}
						if(rpt.getReportType().equals("Txt"))
						{
							rg.tableBody(attCol, emprelativesData, attCellWidth,attAlign);
						}
			    }//inner loop closed
		   //}
			
			else
			{
				rg.addFormatedText("    ",0, 0, 1, 0);
				rg.addFormatedText("No records to display ", 0, 0, 1, 0);
			}
		}
		//Query for displaying Total Experiance and Current Experiance of employee in report.
		else
		   {
			int[] attCellWidth = { 60 , 80 , 60 , 80 , 80 , 80 , 80 , 80 ,  60 , 80 };
			int []attAlign={0,0,0,0,0,0,0,0,0,0};
			String []attCol={"Employee Token","Name","Status","Emp Type","Department","Branch","Division","Designation","Current Experiance","Total Experince"};
			
			String expNewQuery = "SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),"
				+ " TO_CHAR(TYPE_NAME),DEPT_NAME,(HRMS_CENTER.CENTER_NAME),DIV_NAME,HRMS_RANK.RANK_NAME "
				+","
				+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)||' '||'Years'||' '|| FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)-FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12) ||' '||'Months' AS CURRENT_EXPERRIENCE,"
				+ "floor(((sysdate-HRMS_EMP_OFFC.EMP_REGULAR_DATE) + SUM(hrms_emp_exp.EXP_RELIEVING_DATE-hrms_emp_exp.EXP_JOINING_DATE))/365)"
				+ " ||' '||'Years'||' '|| round(mod(((sysdate-hrms_emp_offc.EMP_REGULAR_DATE) + SUM(hrms_emp_exp.EXP_RELIEVING_DATE-hrms_emp_exp.EXP_JOINING_DATE)),365) /30 ) ||' '||'Months' as TOTAL_EXPERIANCE "
				+ "  FROM HRMS_EMP_EXP"
				+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_EXP.EMP_ID) "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ "LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ "LEFT JOIN HRMS_DEPT ON (dept_id = emp_dept)"
				+ "LEFT JOIN HRMS_EMP_TYPE on (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
				+ "LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV WHERE 1=1 ";
				
				if (!(rpt.getEmpid().equals(""))
						&& !(rpt.getEmpid() == null)
						&& !rpt.getEmpid().equals("null")) {
					expNewQuery += " AND HRMS_EMP_OFFC.EMP_ID =" + rpt.getEmpid();
				}
				if (!(rpt.getCenterId().equals(""))
					&& !(rpt.getCenterId() == null)
					&& !rpt.getCenterId().equals("null")) {
					expNewQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="+ rpt.getCenterId() + " ";
				}
			    if (!(rpt.getDivCode().equals(""))
			    	&& !(rpt.getDivCode() == null)
			    	&& !rpt.getDivCode().equals("null")) {
					expNewQuery += " AND HRMS_EMP_OFFC.EMP_DIV=" + rpt.getDivCode()+ " ";
				}
				if (!(rpt.getDeptCode().equals(""))
				    && !(rpt.getDeptCode() == null)
				    && !rpt.getDeptCode().equals("null")) {
					expNewQuery += " AND HRMS_EMP_OFFC.EMP_DEPT="+ rpt.getDeptCode() + " ";
				}
				if (!(rpt.getDesgCode().equals(""))
					&& !(rpt.getDesgCode() == null)
					&& !rpt.getDesgCode().equals("null")) {
					expNewQuery += " AND HRMS_EMP_OFFC.EMP_RANK="+ rpt.getDesgCode() + " ";
				}
				if (!(rpt.getStatus().equals("-1"))){
					expNewQuery += " AND HRMS_EMP_OFFC.EMP_STATUS='"+ rpt.getStatus() + "'";
				}
			
				expNewQuery+= " group by  EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),"
				+ "  EMP_STATUS,TO_CHAR(TYPE_NAME),DEPT_NAME,(HRMS_CENTER.CENTER_NAME),DIV_NAME,HRMS_RANK.RANK_NAME,"
				+ " HRMS_EMP_OFFC.EMP_REGULAR_DATE";
			

		
		
		Object empExpData[][] = getSqlModel().getSingleResult(expNewQuery);
		
		if(!(empExpData==null || empExpData.length==0))
		{
			  rg.setFName("Experience");
			  rg.addFormatedText("\n",0, 0, 1, 0);
			  if(rpt.getReportType().equals("Xls"))
				{
					rg.tableBody(attCol, empExpData, attCellWidth,attAlign);
					rg.addText("\n\n",0, 0, 0);
				}
				if(rpt.getReportType().equals("Pdf"))
				{
					rg.tableBody(attCol, empExpData, attCellWidth,attAlign);		
				}
				if(rpt.getReportType().equals("Txt"))
				{
					rg.tableBody(attCol, empExpData, attCellWidth,attAlign);
				}
	    }//inner loop closed
   //}
	
	else
	{
		rg.addFormatedText("    ",0, 0, 1, 0);
		rg.addFormatedText("No records to display ", 0, 0, 1, 0);
	}
		   
		   }
		
		rg.createReport(response);
		return null;
	}
	
	public void getExpMISReport(ExperinceMisReport rpt, HttpServletRequest request, HttpServletResponse response, String reportPath)
	{
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type= rpt.getReport();
		rds.setReportType(type);
		String fileName = "ExperienceMISReportDetails"+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Experience MIS Report");
		rds.setTotalColumns(13);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(rpt.getUserEmpId());

		//Report generator starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg= null;
		
		//Attachment Path Definition
		if(reportPath.equals(""))
		{
			rg =  new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);		
		}
		else
		{
			rg= new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);		
			request.setAttribute("action","/srd/ExperienceMISReport_input.action");
			request.setAttribute("fileName",fileName+"."+type );
			//Initial Page Action
		}	
		
		/*Setting Filter Details */
		
		String filter ="";
		
		if(!rpt.getDivsion().equals("")){
			filter +="\nDivision: "+rpt.getDivsion();
		}
		
		if(!rpt.getDeptName().equals("")){
			filter+="\n\nDepartment: "+rpt.getDeptName();
		}
		
		if(!rpt.getCenterName().equals("")){
			filter+="\n\nBranch: "+rpt.getCenterName();
		}
		
		if(!rpt.getDesgName().equals("")){
			filter+="\n\nDesignation: " +rpt.getDesgName();
		}
		
		if(!rpt.getEmpName().equals("")){
			filter+="\n\nEmployee Name: " +rpt.getEmpName();
		}
		
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][]{{filter}});
		filterData.setCellAlignment(new int[]{0});
		filterData.setCellWidth(new int[]{100});
		filterData.setCellColSpan(new int[]{13});
		filterData.setBodyFontStyle(1);
		filterData.setCellNoWrap(new boolean[]{false});
		rg.addTableToDoc(filterData);
		
		
		if(!rpt.getCheckFlag().equals("Y")){
		
	      String filterClause="";
		  if (!rpt.getDivsion().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DIV IN("
					+ rpt.getDivCode()+")";
			System.out.println("+fmr.getDivCode() ="
					+ rpt.getDivCode());
		  }
		  if (!rpt.getDeptName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
					+ rpt.getDeptCode()+")";
			System.out.println("+fmr.getDeptId() =" + rpt.getDeptCode());
		  }
		  if (!rpt.getCenterName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
					+ rpt.getCenterId()+")";
			System.out.println("+fmr.getBranchCode() ="
					+ rpt.getCenterId());
		  }
		  if (!rpt.getDesgName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_RANK IN("
					+ rpt.getDesgCode()+")";
			System.out.println("+fmr.getDesgCode() ="
					+ rpt.getDesgCode());
		  }
		  if (!rpt.getEmpName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_ID IN(" + rpt.getEmpid()+")";
			System.out.println("+fmr.getEmpCode() =" + rpt.getEmpid());
		  }
		  if (!(rpt.getStatus().equals("-1"))){
			filterClause += " AND HRMS_EMP_OFFC.EMP_STATUS='"+ rpt.getStatus() + "'";
		  }
		  String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					   +" DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retire','N','Resigned','E','Terminated'),"
					   +" HRMS_DEPT.DEPT_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DIVISION.DIV_NAME, HRMS_RANK.RANK_NAME, HRMS_EMP_EXP.EXP_EMPLOYER,HRMS_EMP_EXP.EXP_JOBTITLE,HRMS_EMP_EXP.EXP_JOBDESC,"
					   +" TO_CHAR(HRMS_EMP_EXP.EXP_JOINING_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_EMP_EXP.EXP_RELIEVING_DATE,'DD-MM-YYYY'),FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)/12) ||' '||'Years'||' '||"
					   +" FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)-FLOOR(MONTHS_BETWEEN(EXP_RELIEVING_DATE,EXP_JOINING_DATE)/12) *12) ||' '||'Months'"
					   +" FROM HRMS_EMP_EXP INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_EXP.EMP_ID)"
					   +" LEFT JOIN HRMS_TITLE ON HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE"
					   +" LEFT JOIN HRMS_RANK ON HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID"
					   +" LEFT JOIN HRMS_CENTER ON HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID"
					   +" LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV"
					   +" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  where 1<2";
		
		  query += filterClause;
		  query +=  " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		  //query += "ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		  //Defining table Structure and Data
		  org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
		  Object [][] queryData = getSqlModel().getSingleResult(query);
		
		  if(queryData == null || queryData.length == 0){
			 TableDataSet noData = new TableDataSet();
			 Object[][] noDataObj = new Object[1][1];
			 noDataObj[0][0] = "No records available";
			 noData.setData(noDataObj);
			 noData.setCellAlignment(new int[] { 1 });
			 noData.setCellWidth(new int[] { 100 });
			 noData.setBorder(false);
			 rg.addTableToDoc(noData);
		
	      }
		  else{
			  System.out.println("In getExpMISReport()QeryData Length: "+queryData.length);
			  tdstable.setHeader(new String[]{"Emp ID"," Name"," Status","Department"," Branch"," Division"," Designation", "Employer", "JobTitle", "Job Designation", " Joining Date", " Releaving Date"," Current Experience"});
			  tdstable.setHeaderTable(true);
			  tdstable.setHeaderBorderDetail(3);	 
		      tdstable.setData(queryData);
			  //tdstable.setHeaderLines(true);
			  //tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
			  tdstable.setCellAlignment(new int[]{0,0,0,0,0,0,0,0, 0, 0, 1, 1, 2});
			  tdstable.setCellWidth(new int[] {5,8,12,5,12,9,7,10,9,5,6,7,5});
			 // tdstable.setBorder(true); 
			  tdstable.setBorderDetail(3);
			  rg.addTableToDoc(tdstable);
		  }
		  int totalEmployee = queryData.length;
		  TableDataSet totalEmp = new TableDataSet();
		  totalEmp.setData(new Object[][] { { "Total Employees : " + totalEmployee } });
		  totalEmp.setCellAlignment(new int[] { 0 });
		  totalEmp.setCellWidth(new int[] { 100 });
		  totalEmp.setBorderDetail(0);
		  totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		  totalEmp.setBodyFontStyle(1);
		  totalEmp.setBlankRowsAbove(1);
		  rg.addTableToDoc(totalEmp);
		  rg.process();
		
		  if(reportPath.equals("")){
			 rg.createReport(response);
		  }
		  else{
			 rg.saveReport(response);
          }
		}
		else{
			 String filterClause="";
			  if (!rpt.getDivsion().equals("")) {
				filterClause += " AND HRMS_EMP_OFFC.EMP_DIV IN("
						+ rpt.getDivCode()+")";
				System.out.println("+fmr.getDivCode() ="
						+ rpt.getDivCode());
			  }
			  if (!rpt.getDeptName().equals("")) {
				filterClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
						+ rpt.getDeptCode()+")";
				System.out.println("+fmr.getDeptId() =" + rpt.getDeptCode());
			  }
			  if (!rpt.getCenterName().equals("")) {
				filterClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
						+ rpt.getCenterId()+")";
				System.out.println("+fmr.getBranchCode() ="
						+ rpt.getCenterId());
			  }
			  if (!rpt.getDesgName().equals("")) {
				filterClause += " AND HRMS_EMP_OFFC.EMP_RANK IN("
						+ rpt.getDesgCode()+")";
				System.out.println("+fmr.getDesgCode() ="
						+ rpt.getDesgCode());
			  }
			  if (!rpt.getEmpName().equals("")) {
				filterClause += " AND HRMS_EMP_OFFC.EMP_ID IN(" + rpt.getEmpid()+")";
				System.out.println("+fmr.getEmpCode() =" + rpt.getEmpid());
			  }
			  if (!(rpt.getStatus().equals("-1"))){
				filterClause += " AND HRMS_EMP_OFFC.EMP_STATUS='"+ rpt.getStatus() + "'";
			  }
			  String query = "SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),"
				  			+" DEPT_NAME,(HRMS_CENTER.CENTER_NAME),DIV_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_EXP.EXP_EMPLOYER,HRMS_EMP_EXP.EXP_JOBTITLE,HRMS_EMP_EXP.EXP_JOBDESC,"
				  			+" FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)||' '||'Years'||' '|| FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)-FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12) ||' '||'Months',"
				  			+" FLOOR(((SYSDATE-HRMS_EMP_OFFC.EMP_REGULAR_DATE) + SUM(HRMS_EMP_EXP.EXP_RELIEVING_DATE-HRMS_EMP_EXP.EXP_JOINING_DATE))/365)"
				  			+" ||' '||'Years'||' '|| ROUND(MOD(((SYSDATE-HRMS_EMP_OFFC.EMP_REGULAR_DATE) + SUM(HRMS_EMP_EXP.EXP_RELIEVING_DATE-HRMS_EMP_EXP.EXP_JOINING_DATE)),365) /30 ) ||' '||'Months'"
				  			+" FROM HRMS_EMP_EXP"
				  			+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_EXP.EMP_ID)"
				  			+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				  			+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				  			+" LEFT JOIN HRMS_DEPT ON (DEPT_ID = EMP_DEPT)"
				  			+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				  			+" LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV WHERE 1<2";
			
			  query += filterClause;
			  query +=  " GROUP BY EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),"
				  		+" EMP_STATUS,DEPT_NAME,(HRMS_CENTER.CENTER_NAME),DIV_NAME,HRMS_RANK.RANK_NAME,"
				  		+" HRMS_EMP_EXP.EXP_EMPLOYER, HRMS_EMP_EXP.EXP_JOBTITLE, HRMS_EMP_EXP.EXP_JOBDESC,HRMS_EMP_OFFC.EMP_REGULAR_DATE";
			 
			  //Defining table Structure and Data
			  org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
			  Object [][] queryData = getSqlModel().getSingleResult(query);
			
			  if(queryData == null || queryData.length == 0){
				 
				 TableDataSet noData = new TableDataSet();
				    Object[][] noDataObj = new Object[1][1];
				    noDataObj[0][0] = "No records available";
				    noData.setData(noDataObj);
				    noData.setCellAlignment(new int[] { 1 });
				    noData.setCellWidth(new int[] { 100 });
				    noData.setBorder(false);
				    rg.addTableToDoc(noData);
			
		      }
			  else{
				 System.out.println("In getExpMISReport()QeryData Length: "+queryData.length);tdstable.setHeader(new String[]{"Emp Id"," Name"," Status","Department"," Branch"," Division"," Designation", "Employer", "JobTitle", "Job Designation"," Current Experience", "Total Experience"});
				 tdstable.setHeaderTable(true);
				 tdstable.setHeaderBorderDetail(3);
				 tdstable.setData(queryData);
				 //tdstable.setHeaderLines(true);
				 // tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
				 tdstable.setCellAlignment(new int[]{0,0,0,0,0,0,0,0,0,0,0,0});
				 tdstable.setCellWidth(new int[] {5,10,5,10,10,10,10,8,8,8,8,8});
				 tdstable.setBorderDetail(3);
				 //tdstable.setBorder(true);
				 rg.addTableToDoc(tdstable); 
			  }
			  
			  int totalEmployee = queryData.length;
			 TableDataSet totalEmp = new TableDataSet();
			 totalEmp.setData(new Object[][] { { "Total Employees : "+ totalEmployee } });
			 totalEmp.setCellAlignment(new int[] { 0 });
			 totalEmp.setCellWidth(new int[] { 100 });
			 totalEmp.setBorderDetail(0);
			 totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
			 totalEmp.setBodyFontStyle(1);
			 totalEmp.setBlankRowsAbove(1);
			 rg.addTableToDoc(totalEmp);
			 rg.process();
			
			  if(reportPath.equals("")){
				 rg.createReport(response);
			  }
			  else{
				 rg.saveReport(response);
	          }
		}
		
	}
	
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for
				// Apr To Dec
			} // end of loop
			empId = empId.substring(0, empId.length() - 1);
		} catch (Exception e) {
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.
		

		return empId;
	} // end of getEmpList method
}
