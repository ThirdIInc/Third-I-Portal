package org.paradyne.model.recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.CTCProjection;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class CTCProjectionModel extends ModelBase{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger
    .getLogger(org.paradyne.model.recruitment.CTCProjectionModel.class);
	public void getReport(HttpServletRequest request,HttpServletResponse response,
			              CTCProjection bean,String[] colnames,String reportType) {
		try{
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String query="SELECT DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,COUNT(EMP_ID),SUM(VACAN_NUMBERS), "+
		  "NVL(REQS_VACAN_COMPEN,0) EXCTC,HRMS_REC_REQS_HDR.REQS_CODE "+
		  "FROM HRMS_REC_REQS_HDR  "+
		  "INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT) "+
		  "INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION) "+
		  "INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "+
		  "INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION) "+
		  "INNER JOIN HRMS_REC_REQS_VACDTL VACDTL ON (VACDTL.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "+ 
		  "LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_CENTER=HRMS_REC_REQS_HDR.REQS_BRANCH "+
		  "AND OFFC1.EMP_RANK=HRMS_REC_REQS_HDR.REQS_POSITION "+
		  "AND OFFC1.EMP_DEPT=HRMS_REC_REQS_HDR.REQS_DEPT "+
		  "AND OFFC1.EMP_DIV=HRMS_REC_REQS_HDR.REQS_DIVISION) "+  
		  "WHERE VACAN_STATUS='P' ";
		if(!bean.getDesignationCode().equals("")){
		query += "AND HRMS_REC_REQS_HDR.REQS_POSITION = " +bean.getDesignationCode();
		}     
		if(!bean.getDivisonCode().equals("")){
		query += "AND HRMS_REC_REQS_HDR.REQS_DIVISION = " +bean.getDivisonCode();
		}
		if(!bean.getDeptCode().equals("")){
		query += "AND HRMS_REC_REQS_HDR.REQS_DEPT = " +bean.getDeptCode();
		}     
		if(!bean.getBranchCode().equals("")){
		query += "AND HRMS_REC_REQS_HDR.REQS_BRANCH = "+bean.getBranchCode();
		}
		query +="GROUP BY ROWNUM,HRMS_REC_REQS_HDR.REQS_CODE, REQS_POSITION, REQS_DIVISION, "+ 
		    "REQS_BRANCH, REQS_DEPT,REQS_VACAN_COMPEN,DEPT_NAME,DIV_NAME,CENTER_NAME,RANK_NAME, "+
		    "VACAN_STATUS ORDER BY ROWNUM ";
		String existCTCQuery = "SELECT EMP_DIV , EMP_CENTER,EMP_DEPT,EMP_RANK ,COUNT( HRMS_EMP_OFFC.EMP_ID), "
			+ " SUM(CREDIT_AMT),CREDIT_PERIODICITY,  "
			+ " CASE WHEN CREDIT_PERIODICITY = 'M' THEN (SUM(CREDIT_AMT)*12) "
			+ " WHEN CREDIT_PERIODICITY = 'Q' THEN (SUM(CREDIT_AMT)*4) "
			+ " WHEN CREDIT_PERIODICITY = 'H' THEN (SUM(CREDIT_AMT)*2) "
			+ " WHEN CREDIT_PERIODICITY = 'A' THEN (SUM(CREDIT_AMT)) "
			+ " END AS AMOUNT "
			+ " FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_EMP_CREDIT ON HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE) "
			+ " GROUP BY EMP_DIV , EMP_CENTER,EMP_DEPT,EMP_RANK,CREDIT_PERIODICITY ";
		String reportName="\n  CTC Projection Report  \n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(reportType, reportName);
		Object [][]reqData=getSqlModel().getSingleResult(query);
		Object [][]existCTCData=getSqlModel().getSingleResult(existCTCQuery);
		Object Data[][]=new Object[reqData.length][11];
		int length=0;
		if(reportType.equals("Pdf")){
			if(reqData!=null && reqData.length==0){
				rg.addTextBold("Date :"+toDay, 0, 2, 0);
				rg.addText("\n",0,0,0);
				rg.addText("No Records to Display",  0, 1, 0);
			} else{
				length=reqData.length;
				int j=0;
				int cellwidth[]={6,10,10,10,10,12,16,16};
				int alignment[]={1,0,0,1,0,2,2,2};
				rg.addFormatedText(reportName, 6, 0, 1, 0);
				rg.addTextBold("Date :"+toDay, 0, 2, 0);
				for (int i = 0; i < reqData.length; i++) {
					Data[i][0]=i+1;
					if(reqData[i][0] != null)
						Data[i][1]=reqData[i][0];//DIV
					else
						Data[i][1]="";
					
					if(reqData[i][1] != null)						
						Data[i][2]=reqData[i][1];//CENTER
					else
						Data[i][2]="";
					
					if(reqData[i][2] != null)
						Data[i][3]=reqData[i][2];//DEPT
					else
						Data[i][3]="";
					
					if(reqData[i][3] != null)
						Data[i][4]=reqData[i][3];//RANK
					else
						Data[i][4]="";
					double totalCTC = (Double.parseDouble(checkValue(String
							.valueOf(existCTCData[i][7]))) + Double
							.parseDouble(checkValue(String
									.valueOf(reqData[i][5]))));
					if(reqData[i][4] != null)
						Data[i][5]=Utility
						.twoDecimals(Double
								.parseDouble(checkNull(String
										.valueOf(totalCTC))));//Total CTC 
					else
						Data[i][5]="";
					
					if(reqData[i][5] != null)
						Data[i][6]="";//Projected % Hike
					else
						Data[i][6]="";
					
					if(reqData[i][6] != null)
						Data[i][7]="";//Projected CTC
					else
						Data[i][7]="";
					
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
			rg.setFName("CTC Projection Report.doc");
			rg.addTextBold("CTC Projection Report", 0, 1, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			int cellwidth[]={6,10,10,10,10,12,16,16};
			int alignment[]={1,0,0,1,0,2,2,2};
			for (int i = 0; i < reqData.length; i++) {
				Data[i][0]=i+1;
				if(reqData[i][0] != null)
					Data[i][1]=reqData[i][0];//DIV
				else
					Data[i][1]="";
				
				if(reqData[i][1] != null)						
					Data[i][2]=reqData[i][1];//CENTER
				else
					Data[i][2]="";
				
				if(reqData[i][2] != null)
					Data[i][3]=reqData[i][2];//DEPT
				else
					Data[i][3]="";
				
				if(reqData[i][3] != null)
					Data[i][4]=reqData[i][3];//RANK
				else
					Data[i][4]="";
				
				double totalCTC = (Double.parseDouble(checkValue(String
						.valueOf(existCTCData[i][7]))) + Double
						.parseDouble(checkValue(String
								.valueOf(reqData[i][5]))));
				if(reqData[i][4] != null)
					Data[i][5]=Utility
					.twoDecimals(Double
							.parseDouble(checkNull(String
									.valueOf(totalCTC))));//Total CTC 
				else
					Data[i][5]="";
				
				if(reqData[i][5] != null)
					Data[i][6]="";//Projected % Hike
				else
					Data[i][6]="";
				
				if(reqData[i][6] != null)
					Data[i][7]="";//Projected CTC
				else
					Data[i][7]="";
				
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
			rg.setFName("CTC Projection Report");
			int cellwidth[]={15,50,35,35,40,40,50,25,25,25};
			int alignment[]={1,0,0,0,0,0,1,2,2,2};
			rg.addFormatedText("", 6, 0, 1, 0);
			if (reportType.equalsIgnoreCase("Xls")) {
				rg.addText("", 0, 6, 0);
				rg.addText("CTC Projection Report", 0, 6, 0);
				rg.addText("", 0, 6, 0);
				rg.addText("", 0, 6, 0);
			}
			for (int i = 0; i < reqData.length; i++) {
				Data[i][0]=i+1;
				if(reqData[i][0] != null)
					Data[i][1]=reqData[i][0];//DIV
				else
					Data[i][1]="";
				
				if(reqData[i][1] != null)						
					Data[i][2]=reqData[i][1];//CENTER
				else
					Data[i][2]="";
				
				if(reqData[i][2] != null)
					Data[i][3]=reqData[i][2];//DEPT
				else
					Data[i][3]="";
				
				if(reqData[i][3] != null)
					Data[i][4]=reqData[i][3];//RANK
				else
					Data[i][4]="";
				
				double totalCTC = (Double.parseDouble(checkValue(String
						.valueOf(existCTCData[i][7]))) + Double
						.parseDouble(checkValue(String
								.valueOf(reqData[i][5]))));
				if(reqData[i][4] != null)
					Data[i][5]=Utility
					.twoDecimals(Double
							.parseDouble(checkNull(String
									.valueOf(totalCTC))));//Total CTC 
				else
					Data[i][5]="";
				
				if(reqData[i][5] != null)
					Data[i][6]="";//Projected % Hike
				else
					Data[i][6]="";
				
				if(reqData[i][6] != null)
					Data[i][7]="";//Projected CTC
				else
					Data[i][7]="";
				
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
	public void callJspView(CTCProjection bean,
			HttpServletRequest request) {
		String query="SELECT DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,COUNT(EMP_ID),SUM(VACAN_NUMBERS), "+
					  "NVL(REQS_VACAN_COMPEN,0) EXCTC,HRMS_REC_REQS_HDR.REQS_CODE "+
					  "FROM HRMS_REC_REQS_HDR  "+
					  "INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT) "+
					  "INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION) "+
					  "INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "+
					  "INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION) "+
					  "INNER JOIN HRMS_REC_REQS_VACDTL VACDTL ON (VACDTL.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "+ 
					  "LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_CENTER=HRMS_REC_REQS_HDR.REQS_BRANCH "+
					  "AND OFFC1.EMP_RANK=HRMS_REC_REQS_HDR.REQS_POSITION "+
					  "AND OFFC1.EMP_DEPT=HRMS_REC_REQS_HDR.REQS_DEPT "+
					  "AND OFFC1.EMP_DIV=HRMS_REC_REQS_HDR.REQS_DIVISION) "+  
					  "WHERE VACAN_STATUS='P' ";
		if(!bean.getDesignationCode().equals("")){
			  query += "AND HRMS_REC_REQS_HDR.REQS_POSITION = " +bean.getDesignationCode();
		}     
		if(!bean.getDivisonCode().equals("")){
			  query += "AND HRMS_REC_REQS_HDR.REQS_DIVISION = " +bean.getDivisonCode();
		}
		if(!bean.getDeptCode().equals("")){
			  query += "AND HRMS_REC_REQS_HDR.REQS_DEPT = " +bean.getDeptCode();
		}     
		if(!bean.getBranchCode().equals("")){
			  query += "AND HRMS_REC_REQS_HDR.REQS_BRANCH = "+bean.getBranchCode();
		}
		query +="GROUP BY ROWNUM,HRMS_REC_REQS_HDR.REQS_CODE, REQS_POSITION, REQS_DIVISION, "+ 
		  	    "REQS_BRANCH, REQS_DEPT,REQS_VACAN_COMPEN,DEPT_NAME,DIV_NAME,CENTER_NAME,RANK_NAME, "+
		  	    "VACAN_STATUS ORDER BY ROWNUM ";
		
		String existCTCQuery = "SELECT EMP_DIV , EMP_CENTER,EMP_DEPT,EMP_RANK ,COUNT( HRMS_EMP_OFFC.EMP_ID), "
			+ " SUM(CREDIT_AMT),CREDIT_PERIODICITY,  "
			+ " CASE WHEN CREDIT_PERIODICITY = 'M' THEN (SUM(CREDIT_AMT)*12) "
			+ " WHEN CREDIT_PERIODICITY = 'Q' THEN (SUM(CREDIT_AMT)*4) "
			+ " WHEN CREDIT_PERIODICITY = 'H' THEN (SUM(CREDIT_AMT)*2) "
			+ " WHEN CREDIT_PERIODICITY = 'A' THEN (SUM(CREDIT_AMT)) "
			+ " END AS AMOUNT "
			+ " FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_EMP_CREDIT ON HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE) "
			+ " GROUP BY EMP_DIV , EMP_CENTER,EMP_DEPT,EMP_RANK,CREDIT_PERIODICITY ";
		
		Object [][]reqData=getSqlModel().getSingleResult(query);
		
		Object [][]existCTCData=getSqlModel().getSingleResult(existCTCQuery);
		
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
				CTCProjection bean1 = new CTCProjection();
				bean1.setDivison(checkNull(String.valueOf(reqData[i][0]).trim()));//Division  1
				bean1.setBranch(checkNull(String.valueOf(reqData[i][1]).trim()));//Branch  2
				bean1.setDepartment(checkNull(String.valueOf(reqData[i][2]).trim()));//Department  3
				bean1.setDesignation(checkNull(String.valueOf(reqData[i][3]).trim()));//Designation  4
				double totalCTC = 0.0d;
				totalCTC = (Double.parseDouble(checkValue(String
						.valueOf(existCTCData[i][7]))) + Double
						.parseDouble(checkValue(String.valueOf(reqData[i][5]))));
				bean1.setTotalCtc(checkNull(Utility.twoDecimals(String.valueOf(totalCTC))));//Total CTC  5
				bean1.setProjectHikes("");//Projected Hikes 6
				bean1.setProjectedCtc("");//Projected CTC
				bean1.setRequisitionHidCode(checkNull(String.valueOf(reqData[i][7]).trim()));
				obj.add(bean1);
			}
			bean.setRequisitionList(obj);
		}else{
			bean.setNoData("true");
		}
	}
	public String checkNull(String val){
		if(val==null || val.equals("null")){
			return "";
		}else{
			return val;
		}
	}
	public String checkValue(String val) {
		if (val == null || val.equals("null")) {
			return "0";
		} else {
			return val;
		}
	}
}
