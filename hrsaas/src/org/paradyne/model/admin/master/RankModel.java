package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.RankMaster;
import org.paradyne.bean.payroll.MonthlyEDSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 String reportPath="";
 if(reportPath.equals("")){
 rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
 }
 * @author Prasad
 */

/**
 *  to define the business logic for designation/rank 
 */
/**
 * @author tinshuk.banafar
 *
 */
public class RankModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.admin.master.RankMasterAction.class);

	/**
	 * following function is called to add a new record
	 * 
	 * @param bean
	 * @return
	 */
	public String addDesignation(RankMaster bean) {

		Object[][] add = new Object[1][5];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = bean.getDesignationName().trim(); // designation name 
			add[0][1] = bean.getDesignationAbbr().trim(); // designation abbreviation
			add[0][2] = bean.getDesignationDesc(); // designation description
			if (bean.getIsActive().equals("true")) {
				add[0][3] = "Y"; //is active
			} else {
				add[0][3] = "N";
			}
			add[0][4] = bean.getSalaryRange(); //Salary Range

			if (!checkDuplicate(bean)) {
				// to get the data   from designation/rank  
				result = getSqlModel().singleExecute(getQuery(1), add);
				if (result) {

					String query = " SELECT MAX(HRMS_RANK.RANK_ID) FROM HRMS_RANK";
					// to get the  designation/rank id  from table
					Object[][] data = getSqlModel().getSingleResult(query);

					String query1 = " SELECT HRMS_RANK.RANK_NAME, NVL(HRMS_RANK.RANK_ABBR,' '), DECODE(HRMS_RANK.IS_ACTIVE,'Y','Yes','N','No'), HRMS_RANK.RANK_DESC, HRMS_RANK.RANK_ID, NVL(HRMS_RANK.SALARY_RANGE,' ') FROM "
							+ " HRMS_RANK WHERE HRMS_RANK.RANK_ID="
							+ String.valueOf(data[0][0]);

					//logger.info("query1 in addDesignation---->" + query1);

					Object[][] Data = getSqlModel().getSingleResult(query1);

					bean.setDesignationName(checkNull(String
							.valueOf(Data[0][0]))); // designation name
					bean.setDesignationAbbr(checkNull(String
							.valueOf(Data[0][1]))); // designation Abbreviation
					bean.setDesignationDesc(checkNull(String
							.valueOf(Data[0][3]))); // designation description
					bean.setPageStatus(checkNull(String.valueOf(Data[0][2])));
					bean.setDesignationCode(checkNull(String
							.valueOf(Data[0][4]))); // designation code
					bean.setSalaryRange(String.valueOf(Data[0][5]));
					flag = "saved";
				} else {
					flag = "error";
				}
			} else {
				//  to check the status 
				String query = "SELECT DECODE(IS_ACTIVE,'Y','Yes','N','No') "
						+ " FROM HRMS_RANK where  IS_ACTIVE='"
						+ bean.getIsActive() + "'";

				Object[][] data = getSqlModel().getSingleResult(query);
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised-->");
		}
		return flag;
	}

	/**
	 * following function is called to update the record.
	 * 
	 * @param bean
	 * @return
	 */

	public String modDesignation(RankMaster bean) {
		// to get the data for  update the record
		Object[][] data = new Object[1][6];
		String editFlag = "";
		boolean result = false;
		try {
			data[0][0] = bean.getDesignationName(); // designation name
			data[0][1] = bean.getDesignationAbbr(); // designation Abbreviation
			data[0][2] = bean.getDesignationDesc(); // designation description
			if (bean.getIsActive().equals("true")) {
				data[0][3] = "Y"; //is active
			} else {
				data[0][3] = "N";
			}
			data[0][4] = bean.getSalaryRange(); //salary range
			data[0][5] = bean.getDesignationCode(); // designation code
			if (!checkDuplicateMod(bean)) {
				// to get the data  for modifying  the record
				String query = "  UPDATE HRMS_RANK SET RANK_NAME=?,RANK_ABBR=? ,RANK_DESC=?,IS_ACTIVE=?, SALARY_RANGE=? WHERE RANK_ID=?   ";
				result = getSqlModel().singleExecute(query, data);
				if (result) {
					String query1 = "SELECT RANK_NAME,RANK_ABBR,RANK_DESC,DECODE(IS_ACTIVE,'Y','Yes','N','No'),RANK_ID, NVL(SALARY_RANGE,' ') FROM HRMS_RANK where RANK_ID ="
							+ bean.getDesignationCode();

					Object[][] data1 = getSqlModel().getSingleResult(query1);
					bean.setDesignationName(checkNull(String.valueOf(
							data1[0][0]).trim())); // designation name
					bean.setDesignationAbbr(checkNull(String.valueOf(
							data1[0][1]).trim())); // designation Abbreviation
					bean.setDesignationDesc(checkNull(String.valueOf(
							data1[0][2]).trim())); // designation description
					bean.setPageStatus(checkNull(String.valueOf(data1[0][3]))); // designation status
					bean.setDesignationCode(checkNull(String.valueOf(
							data1[0][4]).trim())); // designation code
					bean.setSalaryRange(String.valueOf(data1[0][5]));
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised--->");
			e.printStackTrace();
		}
		return editFlag;
	}

	/**
	 * following function is called to delete a record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteDesignation(RankMaster bean) {
		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching button
		del[0][0] = bean.getDesignationCode();
		return getSqlModel().singleExecute(getQuery(3), del);
	}

	/**
	 *  to check the null value
	 * @param result
	 * @return
	 */

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * following function is called when a record is selected from search window
	 * to set the records.
	 * 
	 * @param bean
	 */

	public void getDesignation(RankMaster bean, String designationCode) {
		try {

			String query = " SELECT RANK_NAME,RANK_ABBR,IS_ACTIVE,RANK_DESC,RANK_ID, NVL(SALARY_RANGE,' ') FROM HRMS_RANK  "
					+ " WHERE RANK_ID=" + designationCode;

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setDesignationName(checkNull(String.valueOf(data[0][0])
						.trim())); // designation name
				bean.setDesignationAbbr(checkNull(String.valueOf(data[0][1])
						.trim())); //designation abbreviation
				if (String.valueOf(data[0][2]).equals("Y")) {
					bean.setIsActive("true");
				}
				bean.setDesignationDesc(checkNull(String.valueOf(data[0][3]))); // designation description
				bean.setDesignationCode(checkNull(String.valueOf(data[0][4]))); // designation code
				bean.setSalaryRange(checkNull(String.valueOf(data[0][5])));//salary range
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * To get the records onload 
	 * @param bean
	 * @param request
	 */
	public void getRecords(RankMaster bean, HttpServletRequest request) {
		try {
			int length = 0;
			String query = " SELECT NVL(RANK_NAME,' '),NVL(RANK_ABBR,' '), DECODE(HRMS_RANK.IS_ACTIVE,'Y','Yes','A','Yes','N','No','D','No','No'), NVL(RANK_DESC,' '),RANK_ID, NVL(SALARY_RANGE, ' ') FROM HRMS_RANK "
					+ " ORDER BY RANK_ID ";

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setModeLength("true");
				bean.setTotalRecords(String.valueOf(data.length)); //   to  display the total number of record in  the list 

				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						data.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "1";

				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));

				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));

				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					RankMaster bean1 = new RankMaster();

					bean1.setDesignationName(String.valueOf(data[i][0]).trim()); // designation name
					bean1.setDesignationAbbr(String.valueOf(data[i][1]).trim()); // designation abbreviation
					bean1.setIsActiveItt(String.valueOf(data[i][2])); // designation status
					bean1.setDesignationCodeItt(String.valueOf(data[i][4])); // designation code
					bean1.setSalaryRangeItt(String.valueOf(data[i][5]));
					List.add(bean1);
				}
				bean.setDesignationList(List);
				length = data.length;
				bean.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to delete checked records whatever you want to delete
	 * 
	 * @param bean
	 * @param code
	 * @return
	 */
	public boolean delChkdRec(RankMaster bean, String[] code) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {

				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				result = getSqlModel().singleExecute(getQuery(3), delete);
				if (!result) {
					count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	/**
	 * for checking duplicate entry of records during Insertion
	 * 
	 * @param bean
	 * @return
	 */
	public boolean checkDuplicate(RankMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_RANK WHERE UPPER(RANK_NAME) LIKE '"
				+ bean.getDesignationName().trim().toUpperCase() + "'";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * For checking duplicate entry of records during modification
	 * 
	 * @param bean
	 * @return
	 */
	public boolean checkDuplicateMod(RankMaster bean) {
		boolean result = false;
		Object[][] data = null;
		String jdName = bean.getDesignationName().trim().toUpperCase();
		Object[] value = new Object[1];
		try {

			value[0] = bean.getDesignationName().trim().toUpperCase();
		} catch (Exception e) {

		}
		try {
			String query = "SELECT * FROM HRMS_RANK WHERE UPPER(RANK_NAME) LIKE '"
					+ bean.getDesignationName().trim().toUpperCase()
					+ "' AND RANK_ID not in(" + bean.getDesignationCode() + ")";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {

		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * Following function is called to get the report of the Pdf format for list of Designation Records
	 * @param rankMaster
	 * @param response
	 * @param label
	 */
	public void generateReport(RankMaster rankMaster,
			HttpServletResponse response, String[] label) {
		org.paradyne.lib.report.ReportGenerator rg;
		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String reportName = "Designation Master";
			rg = new ReportGenerator("Pdf", reportName);
			rg.setFName("Designation Master.Pdf");
			String queryDes = " SELECT RANK_NAME,RANK_ABBR,DECODE(IS_ACTIVE,'Y','Yes','N','No','No') FROM HRMS_RANK ORDER BY RANK_ID";

			Object[][] desData = getSqlModel().getSingleResult(queryDes);

			Object[][] dataObj = new Object[desData.length][4];

			if (desData != null && desData.length > 0) {
				for (int i = 0; i < desData.length; i++) {
					dataObj[i][0] = i + 1;
					dataObj[i][1] = desData[i][0];
					dataObj[i][2] = desData[i][1];
					dataObj[i][3] = desData[i][2];
				}
				int cellwidth[] = { 5, 20, 20, 20 };
				int alignment[] = { 1, 0, 0, 1 };
				rg.addTextBold("Designation Master", 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addTextBold("Date :" + toDay, 0, 2, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(label, dataObj, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Added by Tinshuk Banafar....Begin....

	public void getRankReport(RankMaster rankMast, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {
        
		
		ReportDataSet rds = new ReportDataSet();
		String type = rankMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Designation Master Details " + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Designation Master Details ");
		rds.setShowPageNo(true);
		
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setTotalColumns(6);
		
		rds.setGeneratedByText(rankMast.getUserEmpId());
		rds.setUserEmpId(rankMast.getUserEmpId());
		
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		 
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,context, request);
		}
		else{
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "RankMaster_input.action");
			// Initial Page Action name
			}

		TableDataSet tdstable = new TableDataSet();
		String query = " SELECT rownum,RANK_NAME,RANK_ABBR,RANK_DESC,DECODE(IS_ACTIVE,'Y','Yes','N','No'),SALARY_RANGE FROM HRMS_RANK ";
		
		query+=" order by rownum,RANK_NAME ";
		
		tdstable.setHeader(new String[] { "SrNo  ", "Designation Name",
				"Designation Abbr.", "Designation Desc.","Is Active","Salary Range" });// defining headers
		
		Object[][] queryData = getSqlModel().getSingleResult(query);
		//Defining Tabular Structure and data
		
		tdstable.setCellAlignment(new int[]{0,0,0,0,0,2});
		tdstable.setCellWidth(new int[]{12,25,20,20,8,15});
		
		
		tdstable.setCellColSpan(new int[] { 6 });
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setData(queryData);// data object from query
		tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		tdstable.setBorderDetail(3);
		
		rg.addTableToDoc(tdstable);
		
		int totalRecords=queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Records : "
				+ totalRecords } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);

		rg.process();
		
		//new end
		if(reportPath.equals("")){
		rg.createReport(response);
		}
		else{
		/* Generates the report as attachment*/
		rg.saveReport(response);
		}
	}
	
	//Added by Tinshuk Banafar.....End.....
}
