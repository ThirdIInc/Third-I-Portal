/**
 * 
 */
package org.paradyne.model.voucher;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.pf.PFSlipDetailReport;
import org.paradyne.bean.voucher.VoucherMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;


/**
 * @author mangeshj
 * Date 13/03/2008
 *VoucherMisReportModel class to write the business logic to view the Report
 * regarding the voucher application
 */
public class VoucherMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/* method name : getReport
	 * purpose     : to show the Voucher MIS Report in PDF format
	 * return type : void
	 * parameter   : HttpServletRequest request,
			HttpServletResponse response,VoucherMisReport instance
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response,VoucherMisReport bean) {

		
		String status=bean.getStatus();
		if(status.equals("P"))
			status="Pending";
		else if(status.equals("A"))
			status="Approved";
		else if(status.equals("R"))
		status="Rejected";
		else if(status.equals("C"))
			status="Cancelled";
		
		Object [][]heading=new Object[1][1];

		int []cells={25};
		int []align={0};
		String query = "SELECT ROWNUM ,NVL(VOUCHER_NO,' '),(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(APP_DATE,'DD-MM-YYYY'), "
				+ " VCH_NAME, to_char(NVL(VCHDTL_AMOUNT,0),9999999999990.99),DECODE(STATUS,'D','Draft','W','Withdraw','P','Pending','B','Send Back','A','Approved','R','Rejected','F','Forward'), NVL(VCH_DETAILS,' ')  FROM HRMS_VOUCHER_APPL"
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) "
				+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) " 
				+ " INNER JOIN HRMS_VCHDTL ON (HRMS_VCHDTL.VOUCHER_APPL_CODE=HRMS_VOUCHER_APPL.VOUCHER_APPL_CODE)"
				+ " INNER JOIN HRMS_VCH_HD ON(HRMS_VCH_HD.VCH_CODE=HRMS_VCHDTL.VCH_CODE)"
				+ " WHERE 1=1 ";
		String filterSubQuery=" ";
		
			String reportHeading="\nCASH VOUCHER MIS REPORT\n\n";
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(bean.getRepType(),reportHeading,"A4");

			Object conditions[][]=null;
			Object filters[][]=new Object [3][4];
			
			/*
			 * adding the conditions in the query according to the filters selected
			 * & also adding the filters to the Object to view in the report
			 * 
			 */
			boolean filterFlag = false;
			
			if (!bean.isGeneralFlag()) {
				if(!bean.getDivCode().equals("")){
					filters [0][0] = "Division Name : "+bean.getDivision();
					filters [0][1] = "";
					filterSubQuery += " AND EMP_DIV ="+bean.getDivCode();
					filterFlag = true;
				}
				if(!bean.getBranchCode().equals("")){
					if(!bean.getDivCode().equals("")){
						filters [0][2] = "Branch Name : "+bean.getBranch();
						filters [0][3] = "";
					}else{
						filters [0][0] = "Branch Name : "+bean.getBranch();
						filters [0][1] = "";
					}
					filterSubQuery += " AND EMP_CENTER ="+bean.getBranchCode();
					filterFlag = true;
				}
				if(!bean.getDeptCode().equals("")){
					filters [1][0] = "Department Name : "+bean.getDepartment();
					filters [1][1] = "";
					filterSubQuery += " AND EMP_DEPT ="+bean.getDeptCode();
					filterFlag = true;
				}
				if(!bean.getDesgCode().equals("")){
					if(!bean.getDeptCode().equals("")){
						filters [1][2] = "Designation Name : "+bean.getDesignation();
						filters [1][3] = "";
					}else{
						filters [1][0] = "Branch Name : "+bean.getDesignation();
						filters [1][1] = "";
					}
					filterSubQuery += " AND EMP_RANK ="+bean.getDesgCode();
					filterFlag = true;
				}
			}
			
			if(!bean.getVchHeadCode().equals("")){
				filters [2][0] = "Voucher Head : "+bean.getVchHeadName();
				filters [2][1] = "";
				filterSubQuery += " AND HRMS_VCHDTL.VCH_CODE ="+bean.getVchHeadCode();
				filterFlag = true;
			}
			
			try{
			
		if(!bean.getEmpCode().equals("")&& bean.getFrmDate().equals("") && bean.getStatus().equals("")&& bean.getToDate().equals(""))
		{
				conditions=new Object[1][4];
			conditions[0][0]="Employee Name : "+ bean.getEname();
			filterSubQuery+= " AND EMP_CODE=" + bean.getEmpCode();
			
		} // end of if
		else if(!bean.getFrmDate().equals("") && bean.getEmpCode().equals("")&& bean.getStatus().equals("") )
		{
			conditions=new Object[1][4];
			conditions[0][0]="From Date : "+bean.getFrmDate();
			conditions[0][1]="";
			
			if(!bean.getToDate().equals("")){
				conditions[0][2]="To Date : "+bean.getToDate();
				conditions[0][3]="";
				filterSubQuery+= " AND APP_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
						+ "','DD-MM-YYYY')AND TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY')";
			}else{
				filterSubQuery+= " AND APP_DATE >= TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')";
			}
			
			
		} // end of else if
		else if(!bean.getFrmDate().equals("") && ! bean.getEmpCode().equals("") && bean.getStatus().equals(""))
		{
			conditions=new Object[2][4];
			conditions[0][0]="Employee Name : "+bean.getEname();
			conditions[0][1]="";
			conditions[1][0]="From Date : "+bean.getFrmDate();
			conditions[1][1]="";
			
						
			if(!bean.getToDate().equals("")){
				conditions[1][2]="To Date : "+bean.getToDate();
				conditions[1][3]="";
				
				filterSubQuery+=" AND EMP_CODE=" + bean.getEmpCode()+" AND APP_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY')";
			}
			
			else {
				filterSubQuery+=" AND EMP_CODE=" + bean.getEmpCode()+" AND APP_DATE >= TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')";
			}
			
			
		} // end of else if
		else if(!bean.getStatus().equals("") && bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals(""))
		{
			conditions=new Object[1][4];
			conditions[0][0]="Status : "+status;
			
			filterSubQuery+=" AND STATUS='"+bean.getStatus()+"'";
			
		} // end of else if
		else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals(""))
		{
			conditions=new Object[1][4];
			conditions[0][0]="Employee Name : "+bean.getEname();
			conditions[0][1]="";
			conditions[0][2]="Status : "+status;
			conditions[0][3]="";
			filterSubQuery+= " AND EMP_CODE=" + bean.getEmpCode()+" AND STATUS='"+bean.getStatus()+"'";
			
		} // end of else if
		else if(!bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals(""))
		{
			conditions=new Object[2][4];
			conditions[0][0]="Status : "+status;
			conditions[0][1]="";
			conditions[1][0]="From Date : "+bean.getFrmDate();
			conditions[1][1]="";
					
			if(!bean.getToDate().equals("")){
				conditions[1][2]="To Date : "+bean.getToDate();
				conditions[1][3]="";
				filterSubQuery+=" AND APP_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
						+ "','DD-MM-YYYY')AND TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY') AND STATUS='"+bean.getStatus()+"'";
			}
			else {
				filterSubQuery+=" AND APP_DATE >= TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND STATUS='"+bean.getStatus()+"'";

			}
			
		} // end of else if
		else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals(""))
		{
			conditions=new Object[2][4];
			conditions[0][0]="Employee Name : "+bean.getEname();
			conditions[0][1]="";
			conditions[0][2]="Status : "+status;
			
			conditions[1][0]="From Date : "+bean.getFrmDate();
			conditions[1][1]="";
						
			if(!bean.getToDate().equals("")){
				conditions[1][2]="To Date : "+bean.getToDate();
				conditions[1][3]="";
				filterSubQuery+=" AND EMP_CODE=" + bean.getEmpCode()+" AND APP_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY')AND STATUS='"+bean.getStatus()+"'";
			}
			else {
				filterSubQuery+=" AND EMP_CODE=" + bean.getEmpCode()+" AND APP_DATE >= TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND STATUS='"+bean.getStatus()+"'";
			}
			
		} // end of else if
		else if(!bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && bean.getStatus().equals(""))
		{
			conditions=new Object[1][4];
			conditions[0][0]="Employee Name : "+bean.getEname();
			conditions[0][1]="";
			conditions[0][2]="Up to Date : "+bean.getToDate();
			conditions[0][3]="";
			filterSubQuery+=" AND EMP_CODE=" + bean.getEmpCode()+" AND APP_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;
			
		} // end of else if
		else if(bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && ! bean.getStatus().equals(""))
		{
			conditions=new Object[1][4];
			conditions[0][0]="Status : "+status;
			conditions[0][1]="";
			conditions[0][2]="Up to Date : "+bean.getToDate();
			conditions[0][3]="";
			filterSubQuery+=" AND STATUS='"+bean.getStatus()+"' AND APP_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;
			
		} // end of else if
		else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals(""))
		{
			conditions=new Object[2][4];
			conditions[0][0]="Employee Name : "+bean.getEname();
			conditions[0][1]="";
			conditions[0][2]="Status : "+status;
			conditions[0][3]="";
			conditions[1][0]="Up to Date : "+bean.getToDate();
			filterSubQuery+= " AND EMP_CODE=" + bean.getEmpCode()+" AND STATUS='"+bean.getStatus()+"' AND APP_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;
			
		} // end of else if
		else if(bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals(""))
		{
			conditions=new Object[1][4];
			conditions[0][0]="Up to Date : "+bean.getToDate();
			conditions[0][1]="";
			conditions[0][2]="";
			conditions[0][3]="";
			
			filterSubQuery+= " AND APP_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;
			
		}
		
			int [] bcellWidth={45,5,45,5};
			int [] bcellAlign={0,0,0,0};
			query += filterSubQuery+" ORDER BY APP_DATE DESC, HRMS_VOUCHER_APPL.VOUCHER_APPL_CODE,VCH_NAME ";
			Object tableData[][]=getSqlModel().getSingleResult(query);
			
			/*
			 * adding the filters in the report
			 * 
			 */
			if(!bean.getRepType().equals("Xls")){									// PDF report
				rg.addFormatedText(reportHeading, 6, 0, 1, 0);
				rg.addText("Date : "+bean.getToday(), 0, 2, 0);
				if(!bean.getRepType().equals("Txt")){
				rg.addFormatedText("", 0, 0, 1, 0);
				rg.addFormatedText("", 0, 0, 1, 0);
				}
			if(conditions != null ){
				rg.tableBodyNoBorder(conditions,bcellWidth,bcellAlign);
			}
			else if(filterFlag != true ){
				filters [0][0] = "Criteria  : All Records";
				filters [0][1] = "";
				
			}
			
			rg.tableBodyNoBorder(filters,bcellWidth,bcellAlign);
			
			heading[0][0]="Voucher Details :";
			rg.tableBodyBold(heading, cells, align) ;
			
			}else{																	// Xls Report
				rg.addText(reportHeading, 0, 1, 0);
				rg.addText("", 0,  1, 0);
				rg.addText("Date: "+bean.getToday(), 0, 2, 0);
				
				if(!bean.getEmpCode().equals("")){
					rg.addText("Employee Name : "+bean.getEname(), 0, 1, 0);
				}
				if(!bean.getDivCode().equals("")){
					rg.addText("Division Name : "+bean.getDivision(), 0, 1, 0);
				}
				if(!bean.getBranchCode().equals("")){
					rg.addText("Branch Name : "+bean.getBranch(), 0, 1, 0);
				}
				if(!bean.getDeptCode().equals("")){
					rg.addText("Department Name : "+bean.getDepartment(), 0, 1, 0);
				}
				if(!bean.getDesgCode().equals("")){
					rg.addText("Designation Name : "+bean.getDesignation(), 0, 1, 0);
				}
				if(!bean.getFrmDate().equals("")){
					rg.addText("From Date : "+bean.getFrmDate(), 0, 1, 0);
				}
				if(!bean.getToDate().equals("")){
					rg.addText("To Date : "+bean.getToDate(), 0, 1, 0);
				}
				if(!bean.getVchHeadCode().equals("")){
					rg.addText("Voucher Head : "+bean.getVchHeadName(), 0, 1, 0);
				}
				if(!bean.getStatus().equals("")){
					rg.addText("Status : "+status, 0, 1, 0);
				}
				if(conditions == null && filterFlag != true){
					rg.addText("Criteria  : All Records", 0, 1, 0);
				}
			}
			/*
			 * give message if no matching record found
			 * 
			 */
			if(tableData!=null && tableData.length==0){
				rg.addText("", 1, 1, 3);
				rg.addText("No records to display", 1, 1, 3);
				
			} // end of if
			else{
			logger.info("inside else length="+tableData.length);
			int cellwidth[]={5,15,25,15,18,15,12,30};
			int alignment[]={1,1,0,1,0,2,0,0};
			double totAmount =0.00;
			rg.addText("\n", 0, 1, 0);
			String colnames[]={"Sr. No.","Voucher No.","Employee Name","Date","Voucher Head","Amount","Status","Details"};
			for(int i=0;i<tableData.length;i++){
				tableData[i][0]=String.valueOf(i+1);
				totAmount+= Double.parseDouble(String.valueOf(tableData[i][5]));
			}
			Object totalObj[][] =new Object[1][8];
			totalObj=Utility.checkNullObjArr(totalObj);
			totalObj [0][3]="Total Amount";
			totalObj [0][5]=Utility.twoDecimals(new BigDecimal(totAmount).toPlainString());
			//totalObj [0][5] = (new BigDecimal(totAmount).toPlainString());    
			
			
			tableData = Utility.joinArrays(tableData, totalObj, 1, 0);
			/*
			 * show the matching data in the tabular format
			 * 
			 */
			rg.tableBodyBold(colnames,tableData, cellwidth, alignment,12);
			if(bean.getEmpCode().equals("")){
			rg=getDeptStatistic(rg,bean,filterSubQuery);
			}
			
			} // end of else 
			}catch (Exception e) {
				logger.info("Exception occured is"+e);
				e.printStackTrace();
			}
			
			rg.addFormatedText("", 1, 0, 2, 3);
			
			rg.createReport(response);
	}
	public ReportGenerator getDeptStatistic(ReportGenerator rg, VoucherMisReport bean,String filterSubQuery){
		
		//Object [][]vchHead =getSqlModel().getSingleResult("SELECT VCH_CODE,VCH_NAME FROM HRMS_VCH_HD ORDER BY UPPER(VCH_NAME)");
		
		//Object [][]deptName =getSqlModel().getSingleResult("SELECT DEPT_CODE,DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME)");
		Object [][]vchHead =null;
		Object [][]deptName=null;
		if(!bean.getVchHeadCode().equals("")){
			vchHead =new Object[1][2];
			vchHead[0][0]=bean.getVchHeadCode();
			vchHead[0][1]=bean.getVchHeadName();
		}else{
		vchHead =getSqlModel().getSingleResult("SELECT DISTINCT HRMS_VCHDTL.VCH_CODE,VCH_NAME FROM HRMS_VCHDTL" 
								+" LEFT JOIN HRMS_VCH_HD ON(HRMS_VCH_HD.VCH_CODE=HRMS_VCHDTL.VCH_CODE) ORDER BY UPPER(VCH_NAME)");
		}
		if(!bean.getDeptCode().equals("")){
			deptName =new Object[1][2];
			deptName[0][0]=bean.getDeptCode();
			deptName[0][1]=bean.getDepartment();
		}else{
		deptName =getSqlModel().getSingleResult("SELECT DISTINCT DEPT_ID,DEPT_NAME FROM HRMS_VOUCHER_APPL"
								+" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=EMP_CODE)"
								+" INNER JOIN HRMS_DEPT ON(EMP_DEPT=DEPT_ID) ORDER BY UPPER(DEPT_NAME)");
		}
		String dataQuery ="SELECT TO_CHAR(NVL(SUM(VCHDTL_AMOUNT),0),9999999999990.99) FROM HRMS_VCH_HD "
					+" LEFT JOIN HRMS_VCHDTL ON(HRMS_VCHDTL.VCH_CODE=HRMS_VCH_HD.VCH_CODE)"
					+" LEFT JOIN HRMS_VOUCHER_APPL ON(HRMS_VOUCHER_APPL.VOUCHER_APPL_CODE=HRMS_VCHDTL.VOUCHER_APPL_CODE)"
					+" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=EMP_CODE)"
					+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=EMP_DEPT)";
		Object [][] deptStaticData =new Object[vchHead.length+1][deptName.length+3];
		Object tempAmtData[][]=null;
		
		
		for (int i = 0; i < deptStaticData.length; i++) {
			if(i !=deptStaticData.length-1){
				double vchHeadTot =0.0;
			for (int j = 0; j < deptStaticData[0].length; j++) {
				
				if(j==0){
					deptStaticData[i][j] = i+1;
				}else if(j==1 && i !=deptStaticData.length-1){
					deptStaticData[i][j] = String.valueOf(vchHead[i][1]);
				}else if(j==deptStaticData[0].length-1){
					deptStaticData[i][j] = Utility.twoDecimals(vchHeadTot);
				}
				else{
					tempAmtData =getSqlModel().getSingleResult(dataQuery +" WHERE HRMS_VCH_HD.VCH_CODE="+String.valueOf(vchHead[i][0])+" AND DEPT_ID="+String.valueOf(deptName[j-2][0])+filterSubQuery);
					if(tempAmtData !=null || tempAmtData.length>0){
						deptStaticData[i][j] = String.valueOf(tempAmtData[0][0]);
						vchHeadTot += Double.parseDouble(String.valueOf(deptStaticData[i][j]));
					}
				}
			}
			}else{
				deptStaticData[i][1] ="Total";
			}
		}
			double totalAmt =0.00;
			for (int j = 2; j < deptStaticData[0].length-1; j++) {
				double deptTot =0.0;
				for (int i = 0; i < deptStaticData.length; i++) {
					if(i !=deptStaticData.length-1){
						deptTot +=Double.parseDouble(String.valueOf(deptStaticData[i][j]));
					}else {
						deptStaticData[i][j] =Utility.twoDecimals(deptTot);
						totalAmt +=deptTot;
					}
				}
			}
			deptStaticData[deptStaticData.length-1][deptStaticData[0].length-1]=Utility.twoDecimals(totalAmt);
		
		/*for (int i = 0; i < vchHead.length; i++) {
			for (int j = 0; j < deptStaticData[0].length; j++) {
				
				logger.info("deptStaticData["+i+"]["+j+"]=="+deptStaticData[i][j]);
			}
			
		}*/
			int deptNameLength =deptName.length;
		deptStaticData[deptStaticData.length-1][0]="";
		
		String colNames []=new String[deptNameLength+3] ;
		int cellwidth[]=new int[deptNameLength+3];
		int alignment[]=new int[deptNameLength+3];
		
		int colNameLength =colNames.length;
		colNames[1]="Voucher Head";
		cellwidth[1]=25;
		alignment[1]=0;
		
		colNames[0]="Sr.No.";
		cellwidth[0]=5;
		alignment[0]=1;
		
		colNames[colNameLength-1]="Total";
		cellwidth[colNameLength-1]=25;
		alignment[colNameLength-1]=2;
		
		for (int i = 2; i < colNameLength-1; i++) {
			colNames[i]=String.valueOf(deptName[i-2][1]);
			cellwidth[i]=20;
			alignment[i]=2;
		}
		
		rg.pageBreak();
		rg.addText("\nDepartment Statistics :", 0, 0, 0);
		rg.addText("\n", 0, 1, 0);
		rg.tableBodyBold(colNames, deptStaticData, cellwidth, alignment, 12);
		return rg;
	}
	
	
	public void getLoginUserInfo(VoucherMisReport bean, HttpServletRequest request) {
		try {
			
			String sQuery = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMPLOYEE_NAME FROM HRMS_EMP_OFFC WHERE EMP_ID = " + bean.getEmpCode();
			
			Object[][] outputData = getSqlModel().getSingleResult(sQuery);
			
			if (outputData.length > 0)
			{
				bean.setEmpToken(String.valueOf(outputData[0][0]));		/* Employee Code */
				bean.setEname(String.valueOf(outputData[0][1]));		/* Employee Name */
			}
			
		} catch (Exception e) {
			logger.error("Error in PFSlipDetailReportModel.getLoginUserInfo() methos : " + e.getMessage());
		}
	}
}
