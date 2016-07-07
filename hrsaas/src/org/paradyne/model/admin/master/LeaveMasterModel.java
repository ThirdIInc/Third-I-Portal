package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.LeaveMaster;
import org.paradyne.bean.admin.master.RankMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 * 
 * @author Pradeep Ku. Sahoo
 *
 */
/**
 * to define the business logic for leave
 */
public class LeaveMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	LeaveMaster lm = null;

	/* for checking duplicate entry of records during insertion. */
	public boolean checkDuplicate(LeaveMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_LEAVE WHERE UPPER(LEAVE_NAME) LIKE '"
				+ bean.getLeaveName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for checking duplicate entry of records during modification. */
	public boolean checkDuplicateMod(LeaveMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_LEAVE WHERE UPPER(LEAVE_NAME) LIKE '"
				+ bean.getLeaveName().trim().toUpperCase()
				+ "' AND LEAVE_ID not in(" + bean.getLeaveCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for inserting records. */
	public boolean addLeave(LeaveMaster bean) {
		if (!checkDuplicate(bean)) {
			String query = "SELECT NVL(MAX(LEAVE_ID),0)+1 FROM  HRMS_LEAVE";
			Object[][] leave = getSqlModel().getSingleResult(query);
			bean.setLeaveCode(String.valueOf(leave[0][0]));
			// logger.info("add rail model");
			Object[][] addParam = new Object[1][4];
			addParam[0][0] = bean.getLeaveName().trim();
			addParam[0][1] = bean.getLeaveAbbr().trim();
			addParam[0][2] = bean.getIsActive();
			addParam[0][3] = bean.getIsHalfPayApplicable();
			return getSqlModel().singleExecute(getQuery(1), addParam);

		}// end of if
		return false;
	}

	/* for modifying records. */
	public boolean modLeave(LeaveMaster bean) {
		if (!checkDuplicateMod(bean)) {
			// logger.info("add rail model");
			Object[][] modParam = new Object[1][5];
			modParam[0][0] = bean.getLeaveName().trim();
			modParam[0][1] = bean.getLeaveAbbr().trim();
			modParam[0][2] = bean.getIsActive();
			modParam[0][3] = bean.getIsHalfPayApplicable();
			modParam[0][4] = bean.getLeaveCode().trim();
			return getSqlModel().singleExecute(getQuery(2), modParam);

		}// end of if
		else {
			return false;

		}// end of else
	}

	/* for deleting single record. */
	public boolean deleteLeave(LeaveMaster bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = bean.getLeaveCode();
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/**
	 * to check null value
	 * 
	 * @param result
	 * @return
	 */

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getLeaveRecord(LeaveMaster bean) {
		Object paraObj[] = new Object[1];
		paraObj[0] = bean.getLeaveCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), paraObj);

		// for(int i=0; i<data.length; i++) {
		bean.setLeaveCode(checkNull(String.valueOf(data[0][0])));
		bean.setLeaveName(checkNull(String.valueOf(data[0][1])));
		bean.setLeaveAbbr(checkNull(String.valueOf(data[0][2])));
		bean.setIsActive(checkNull(String.valueOf(data[0][3])));
		// logger.info("Value of aabbr"+ data[0][2]);
		bean.setIsHalfPayApplicable(checkNull(String.valueOf(data[0][4])));
		// }
	}

	/**
	 * to generate the leave report
	 * 
	 * @param bean
	 * @param request
	 * @param response
	 * @param context
	 * @param label
	 */
	public void getLeaveReport(LeaveMaster bean, HttpServletRequest request,
			HttpServletResponse response, ServletContext context, String[] label) {

		/*
		 * Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		 * 
		 * ArrayList<Object> leavList = new ArrayList<Object>();
		 * 
		 * for(int i=0; i<data.length; i++) { LeaveMaster bean1= new
		 * LeaveMaster();
		 * bean1.setLeaveCode(checkNull(String.valueOf(data[i][0])));
		 * logger.info("Value of code"+String.valueOf(data[i][0]));
		 * bean1.setLeaveName(checkNull(String.valueOf(data[i][1])));
		 * bean1.setLeaveAbbr(checkNull(String.valueOf(data[i][2])));
		 * 
		 * 
		 * leavList.add(bean1); }//end of loop bean.setLeaveList(leavList); }
		 */
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nLeave  Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Leave  Master.Pdf");
		String queryDes = "  SELECT LEAVE_NAME ,NVL(LEAVE_ABBR,''), "
			+" DECODE(IS_HALF_DAY,'Y','YES','N','NO','NO'),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LEAVE ORDER BY upper(LEAVE_NAME)";

		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][5];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
				j++;
			}
			int cellwidth[] = { 15, 30, 30, 20, 20 };
			int alignment[] = { 1, 0, 0, 0 , 0};
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

	/* for displaying records in onload. */
	public void leaveData(LeaveMaster lm, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(5));
		if (repData != null && repData.length > 0) {
			lm.setModeLength("true");
			lm.setTotalRecords(String.valueOf(repData.length));

			String[] pageIndex = Utility.doPaging(lm.getMyPage(),
					repData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				lm.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				LeaveMaster bean1 = new LeaveMaster();
				bean1.setLeaveCode(checkNull(String.valueOf(repData[i][0])));
				bean1.setLeaveName(checkNull(String.valueOf(repData[i][1])));
				bean1.setLeaveAbbr(checkNull(String.valueOf(repData[i][2])));
				bean1.setIsActive(checkNull(String.valueOf(repData[i][3])));

				List.add(bean1);
			}// end of loop

			lm.setLeaveList(List);
		}
	}

	/* for selecting records from the list to update the data. */
	public void calforedit(LeaveMaster lm) {

		try {
			String query = " SELECT LEAVE_ID,LEAVE_NAME ,NVL(LEAVE_ABBR,''),IS_ACTIVE ,IS_HALF_DAY FROM HRMS_LEAVE   where LEAVE_ID= "
					+ lm.getHiddencode() + "   ORDER BY LEAVE_ID";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				lm.setLeaveCode(String.valueOf(data[0][0]));
				lm.setLeaveName(checkNull(String.valueOf(data[0][1])));
				lm.setLeaveAbbr(checkNull(String.valueOf(data[0][2])));
				lm.setIsActive(checkNull(String.valueOf(data[0][3])));
				lm
						.setIsHalfPayApplicable(checkNull(String
								.valueOf(data[0][4])));
			}

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	/**
	 * to delete the single record after clicking on save or search button
	 * 
	 * @param lm
	 * @return
	 */
	public boolean calfordelete(LeaveMaster lm) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = lm.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/*
	 * public boolean deleteLeave(LeaveMaster lm, String[] code) { boolean
	 * result=false; boolean flag=false; if(code !=null) { for (int i = 0; i <
	 * code.length; i++) {
	 * 
	 * if(!code[i].equals("")){ logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
	 * Object [][] delete = new Object [1][1]; delete [0][0] =code[i] ; flag
	 * =getSqlModel().singleExecute(getQuery(3), delete); if(flag) result=true; } } }
	 * return result; }
	 */

	/* for deleting multiple records in list to check the checkboxes. */
	public String deletecheckedRecords(LeaveMaster lm, String[] code) {
		String result = "";
		int count = 0;

		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					boolean res;
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					res = getSqlModel().singleExecute(getQuery(3), delete);
					if (!res)
						count++;
				}
			}
		}
		if (count != 0) {
			result = "false";
		} else
			result = "true";
		return result;
	}
	
	//Added by Tinshuk Banafar...Begin....

	public void getRankReport(LeaveMaster leaveMast, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {
       
			
		ReportDataSet rds = new ReportDataSet();
		String type = leaveMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Leave Master Details " + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Leave Master Details ");
		rds.setShowPageNo(true);
		
		rds.setGeneratedByText(leaveMast.getUserEmpId());
		rds.setUserEmpId(leaveMast.getUserEmpId());
		rds.setPageOrientation("landscape");
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		 
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,context, request);
		}
		else{
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "LeaveMaster_input.action");
			// Initial Page Action name
			}


		String query = " SELECT LEAVE_ID,LEAVE_NAME,LEAVE_ABBR,DECODE(IS_ACTIVE,'Y','Yes','N','No'),DECODE(IS_HALF_DAY,'Y','Yes','N','No') FROM HRMS_LEAVE ORDER BY LEAVE_ID";
		Object[][] queryData = getSqlModel().getSingleResult(query);
		//Defining Tabular Structure and data
		TableDataSet tdstable = new TableDataSet();
		tdstable.setHeader(new String[] { "Leave Id  ", "Leave Name",
				"Leave Abbreviation.", "Is Active" ,"Is Half Day"});// defining headers
		
		tdstable.setData(queryData);// data object from query
		tdstable.setHeaderLines(true);
		tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		tdstable.setCellAlignment(new int[]{0,0,0,0,0});
		tdstable.setCellWidth(new int[]{20,25,20,15,20});
		tdstable.setBorderDetail(0);
		tdstable.setBorder(true);
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(2);
		rg.addTableToDoc(tdstable);
		rg.process();
		
		if(reportPath.equals("")){
		rg.createReport(response);
		}
		else{
		/* Generates the report as attachment*/
		rg.saveReport(response);
		}
		
		
	}
	//Added by Tinshuk Banafar..End
}
