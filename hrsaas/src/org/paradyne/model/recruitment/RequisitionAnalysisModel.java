package org.paradyne.model.recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.RequisitionAnalysis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class RequisitionAnalysisModel extends ModelBase{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger
	       .getLogger(org.paradyne.model.recruitment.RequisitionAnalysisModel.class);
	public void getReport(HttpServletRequest request,
			HttpServletResponse response,RequisitionAnalysis bean,String[] colnames,String reportType) {
		try{
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		int length;
		String query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME, DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME, TO_CHAR(REQS_DATE,'DD-MM-YYYY'),"+
		" sum(VACAN_NUMBERS) AS  VACAN_NUMBERS,REQS_VACAN_COMPEN,"+
		" (REQS_VACAN_COMPEN*VACAN_NUMBERS) AS AVG_CTC FROM HRMS_REC_REQS_HDR " +
		"INNER JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) " +
		" INNER JOIN HRMS_DIVISION ON (HRMS_REC_REQS_HDR.REQS_DIVISION = HRMS_DIVISION.DIV_ID)" +
		"  INNER JOIN HRMS_CENTER ON (HRMS_REC_REQS_HDR.REQS_BRANCH = HRMS_CENTER.CENTER_ID)" +
		" INNER JOIN HRMS_DEPT ON (HRMS_REC_REQS_HDR.REQS_DEPT = HRMS_DEPT.DEPT_ID)" +
		" LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_REQS_VACDTL.REQS_CODE) WHERE REQS_APPROVAL_STATUS IN ('A','Q') "; 


		if(!bean.getRequisitionCode().equals("")){
		query+=" AND HRMS_REC_REQS_HDR.REQS_CODE= "+bean.getRequisitionHiddenCode();
		}
		if(!bean.getDivison().equals("")){
		query+=" AND DIV_ID= "+bean.getDivisonCode();
		}
		if(!bean.getBranch().equals("")){
		query+="AND CENTER_ID= "+bean.getBranchCode();
		}
		if(!bean.getDepartment().equals("")){
		query+=" AND DEPT_ID= "+bean.getDeptCode();
		}
		if(!bean.getDesignation().equals("")){
		query+=" AND RANK_ID= "+bean.getDesignationCode();
		}
		if(!bean.getReqDate().equals("")){
		query+=" AND TO_CHAR(REQS_DATE,'DD-MM-YYYY')='"+bean.getReqDate()+"'";
		}
		/*if(!bean.getNoOfPersons().equals("")){
		query+=" AND VACAN_NUMBERS= "+bean.getNoOfPersons();
		}
		if(!bean.getTotalCTC().equals("")){
		query+=" AND  REQS_VACAN_COMPEN="+bean.getTotalCTC();
		}*/
		
		query += " group by HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME, DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME, "+
				 " TO_CHAR(REQS_DATE,'DD-MM-YYYY'),REQS_VACAN_COMPEN, (REQS_VACAN_COMPEN*VACAN_NUMBERS) ORDER BY UPPER(REQS_NAME)";

		Object [][]reqData=getSqlModel().getSingleResult(query);
		Object Data[][]=new Object[reqData.length][10];
		String reportName="\n  REQUISITION ANALYSIS REPORT  \n\n";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(reportType, "REQUISITION ANALYSIS REPORT");
		try{
			logger.info("Length of Total Records  ----------------->   "+reqData.length);
		}catch (Exception e) {
			logger.info("Exception was rised when i am getting length of total records---------->");
		}
		if(reportType.equals("Pdf")){
			if(reqData!=null && reqData.length==0){
				rg.addTextBold("Date :"+toDay, 0, 2, 0);
				rg.addText("\n",0,0,0);
				rg.addText("No Records to Display",  0, 1, 0);
			} else{
				rg.setFName("Requisition analysis report");
				length=reqData.length;
				int j=0;
				int cellwidth[]={15,50,35,35,40,40,50,25,25,25};
				int alignment[]={1,0,0,0,0,0,1,2,2,2};
				rg.addFormatedText("Requisition analysis report", 6, 0, 1, 0);
				rg.addTextBold("Date :"+toDay, 0, 2, 0);
				for (int i = 0; i < reqData.length; i++) {
					Data[i][0]=i+1;
					if(reqData[i][0] !=null)
						Data[i][1]=reqData[i][1];
					else
						Data[i][1]="";
					
					if(reqData[i][1] !=null)						
						Data[i][2]=reqData[i][2];
					else
						Data[i][2]="";
					
					if(reqData[i][2] !=null)
						Data[i][3]=reqData[i][3];
					else
						Data[i][3]="";
					
					if(reqData[i][3] !=null)
						Data[i][4]=reqData[i][4];
					else
						Data[i][4]="";
					
					if(reqData[i][4] !=null)
						Data[i][5]=reqData[i][5];
					else
						Data[i][5]="";
					
					if(reqData[i][5] !=null)
						Data[i][6]=reqData[i][6];
					else
						Data[i][6]="";
					
					
					if(reqData[i][6] !=null)
						Data[i][7]=reqData[i][7];
					else
						Data[i][7]="";
					
					if(reqData[i][7] !=null)
						Data[i][8]=reqData[i][8];
					else
						Data[i][8]="";
					
					if(reqData[i][8] !=null)
						Data[i][9]=reqData[i][9];
					else
						Data[i][9]="";
				}//for loop ends
				rg.addText("\n",0,0,0);
				rg.tableBody(colnames, Data, cellwidth, alignment);
				rg.addText("\n",0,0,0);
				if (reqData.length != 0) {
					length = reqData.length;
					rg.addTextBold("Total Records : " + length, 0, 2, 0);
				}// end of if
				else {
					rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
				}
			}//else block ends
		}
		if(reportType.equals("Txt")){
			rg.setFName("Requisition analysis report.doc");
			rg.addTextBold("Requisition analysis report", 0, 1, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			int cellwidth[]={15,50,35,35,40,40,50,25,25,25};
			int alignment[]={1,0,0,0,0,0,1,2,2,2};
			for (int i = 0; i < reqData.length; i++) {
				Data[i][0]=i+1;
				if(reqData[i][0] !=null)
					Data[i][1]=reqData[i][1];
				else
					Data[i][1]="";
				
				if(reqData[i][1] !=null)						
					Data[i][2]=reqData[i][2];
				else
					Data[i][2]="";
				
				if(reqData[i][2] !=null)
					Data[i][3]=reqData[i][3];
				else
					Data[i][3]="";
				
				if(reqData[i][3] !=null)
					Data[i][4]=reqData[i][4];
				else
					Data[i][4]="";
				
				if(reqData[i][4] !=null)
					Data[i][5]=reqData[i][5];
				else
					Data[i][5]="";
				
				if(reqData[i][5] !=null)
					Data[i][6]=reqData[i][6];
				else
					Data[i][6]="";
				
				
				if(reqData[i][6] !=null)
					Data[i][7]=reqData[i][7];
				else
					Data[i][7]="";
				
				if(reqData[i][7] !=null)
					Data[i][8]=reqData[i][8];
				else
					Data[i][8]="";
				
				if(reqData[i][8] !=null)
					Data[i][9]=reqData[i][9];
				else
					Data[i][9]="";
			}//for loop ends
			rg.tableBody(colnames, Data, cellwidth, alignment);
			rg.addText("\n", 0, 0, 0);
			if (reqData.length != 0) {
				length = reqData.length;
				rg.addTextBold("Total Records : " + length, 0, 2, 0);
			}// end of if
			else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}
		}
		if(reportType.equals("Xls")){
			rg.setFName("Requisition analysis report");
			int cellwidth[]={15,50,35,35,40,40,50,25,25,25};
			int alignment[]={1,0,0,0,0,0,1,2,2,2};
			rg.addFormatedText("", 6, 0, 1, 0);
			if (reportType.equalsIgnoreCase("Xls")) {
				rg.addText("", 0, 6, 0);
				rg.addText("Requisition analysis report", 0, 6, 0);
				rg.addText("", 0, 6, 0);
				rg.addText("", 0, 6, 0);
			}
			for (int i = 0; i < reqData.length; i++) {
				Data[i][0]=i+1;
				if(reqData[i][0] !=null)
					Data[i][1]=reqData[i][1];
				else
					Data[i][1]="";
				
				if(reqData[i][1] !=null)						
					Data[i][2]=reqData[i][2];
				else
					Data[i][2]="";
				
				if(reqData[i][2] !=null)
					Data[i][3]=reqData[i][3];
				else
					Data[i][3]="";
				
				if(reqData[i][3] !=null)
					Data[i][4]=reqData[i][4];
				else
					Data[i][4]="";
				
				if(reqData[i][4] !=null)
					Data[i][5]=reqData[i][5];
				else
					Data[i][5]="";
				
				if(reqData[i][5] !=null)
					Data[i][6]=reqData[i][6];
				else
					Data[i][6]="";
				
				
				if(reqData[i][6] !=null)
					Data[i][7]=reqData[i][7];
				else
					Data[i][7]="";
				
				if(reqData[i][7] !=null)
					Data[i][8]=reqData[i][8];
				else
					Data[i][8]="";
				
				if(reqData[i][8] !=null)
					Data[i][9]=reqData[i][9];
				else
					Data[i][9]="";
			}//for loop ends
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(colnames, Data, cellwidth, alignment);
			length = reqData.length;
			if (reqData.length != 0) {
				length = reqData.length;
				rg.addTextBold("Total Records : " + length, 0, 2, 0);
			}// end of if
			else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}
		}
		rg.createReport(response);
		}catch (Exception e) {
			logger.error("Exception was rised in model when i am getting report -------------> "); 
		}
	}
	public void callJspView(RequisitionAnalysis bean,
			HttpServletRequest request) {
		try {
			
			String query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME, DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME, TO_CHAR(REQS_DATE,'DD-MM-YYYY'),"+
						" sum(VACAN_NUMBERS) AS  VACAN_NUMBERS,REQS_VACAN_COMPEN,"+
						" (REQS_VACAN_COMPEN*VACAN_NUMBERS) AS AVG_CTC FROM HRMS_REC_REQS_HDR " +
						"INNER JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) " +
						" INNER JOIN HRMS_DIVISION ON (HRMS_REC_REQS_HDR.REQS_DIVISION = HRMS_DIVISION.DIV_ID)" +
						"  INNER JOIN HRMS_CENTER ON (HRMS_REC_REQS_HDR.REQS_BRANCH = HRMS_CENTER.CENTER_ID)" +
						" INNER JOIN HRMS_DEPT ON (HRMS_REC_REQS_HDR.REQS_DEPT = HRMS_DEPT.DEPT_ID)" +
						" LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_REQS_VACDTL.REQS_CODE) WHERE REQS_APPROVAL_STATUS IN ('A','Q') "; 
			
		
			if(!bean.getRequisitionCode().equals("")){
				query+=" AND HRMS_REC_REQS_HDR.REQS_CODE= "+bean.getRequisitionHiddenCode();
			}
			if(!bean.getDivison().equals("")){
				query+=" AND DIV_ID= "+bean.getDivisonCode();
			}
			if(!bean.getBranch().equals("")){
				query+="AND CENTER_ID= "+bean.getBranchCode();
			}
			if(!bean.getDepartment().equals("")){
				query+=" AND DEPT_ID= "+bean.getDeptCode();
			}
			if(!bean.getDesignation().equals("")){
				query+=" AND RANK_ID= "+bean.getDesignationCode();
			}
			if(!bean.getReqDate().equals("")){
				query+=" AND TO_CHAR(REQS_DATE,'DD-MM-YYYY')='"+bean.getReqDate()+"'";
			}
			/*if(!bean.getNoOfPersons().equals("")){
				query+=" AND VACAN_NUMBERS= "+bean.getNoOfPersons();
			}
			if(!bean.getTotalCTC().equals("")){
				query+=" AND  REQS_VACAN_COMPEN="+bean.getTotalCTC();
			}*/
			
			query += " group by HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME, DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME, "+
			 		 " TO_CHAR(REQS_DATE,'DD-MM-YYYY'),REQS_VACAN_COMPEN, (REQS_VACAN_COMPEN*VACAN_NUMBERS) ORDER BY UPPER(REQS_NAME)";
			
			Object [][]reqData=getSqlModel().getSingleResult(query);
			
			ArrayList<Object> obj = new ArrayList<Object>();
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
			reqData.length, 20);
			
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
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			bean.setReqLength(String.valueOf(reqData.length));
			if(reqData!=null && reqData.length>0){	
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
				
					RequisitionAnalysis bean1 = new RequisitionAnalysis();
					bean1.setRequisitionCode(checkNull(String.valueOf(reqData[i][1]).trim()));
					bean1.setDivison(checkNull(String.valueOf(reqData[i][2]).trim()));
					bean1.setBranch(checkNull(String.valueOf(reqData[i][3]).trim()));
					bean1.setDepartment(checkNull(String.valueOf(reqData[i][4]).trim()));
					bean1.setDesignation(checkNull(String.valueOf(reqData[i][5]).trim()));
					bean1.setReqDate(checkNull(String.valueOf(reqData[i][6]).trim()));
					bean1.setNoOfPersons(checkNull(String.valueOf(reqData[i][7]).trim()));
					bean1.setAvgCTC(checkNull(String.valueOf(reqData[i][8]).trim()));
					bean1.setTotalCTC(checkNull(String.valueOf(reqData[i][9]).trim()));
					obj.add(bean1);
				}
				bean.setRequisitionList(obj);
			}
			else
				bean.setNoData("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public String checkNull(String val){
		if(val==null || val.equals("null")){
			return "";
		}else{
			return val;
		}
	}
	
}
