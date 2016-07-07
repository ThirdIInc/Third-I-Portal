/**
 * @author lakkichand
 * @date 25 Apr 2007
 */

package org.paradyne.model.admin.master;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.paradyne.bean.admin.master.EmpTypeMaster;
import org.paradyne.bean.admin.master.RankMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

public class EmpTypeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmpTypeModel.class);
	EmpTypeMaster typMaster = null;

	/* for checking duplicate entry of record during insertion */
	public boolean addType(EmpTypeMaster bean) {
		if(!checkDuplicate(bean)) {
			String typeCodeSql = " SELECT NVL(MAX(TYPE_ID),0) + 1 FROM HRMS_EMP_TYPE ";
			Object[][] typeCodeObj = getSqlModel().getSingleResult(typeCodeSql);
			
			bean.setTypeID(String.valueOf(typeCodeObj[0][0]));
			
			Object addObj[][] = new Object[1][7];
			addObj[0][0] = bean.getTypeID().trim();
			addObj[0][1] = bean.getTypeName().trim();
			addObj[0][2] = bean.getTypeAbbr().trim();
			addObj[0][3] = bean.getEsiZone().trim();
			addObj[0][4] = bean.getPtZone().trim();
			addObj[0][5] = bean.getPfZone().trim();
			addObj[0][6] = bean.getIsActive().trim();// is active field(y/n).
			return getSqlModel().singleExecute(getQuery(1), addObj);
		} else {
			return false;
		}
	}

	/* for checking duplicate entry of record during modification */
	public boolean calfordelete(EmpTypeMaster typMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = typMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/* for selecting the record from list */
	public void calforedit(EmpTypeMaster typMaster) {
		String query = "  SELECT  TYPE_ID,TYPE_NAME,IS_ACTIVE FROM HRMS_EMP_TYPE WHERE TYPE_ID = " + typMaster.getHiddencode() + " ORDER BY TYPE_ID";

		Object[][] data = getSqlModel().getSingleResult(query);
		typMaster.setTypeID(String.valueOf(data[0][0]));
		typMaster.setTypeName(String.valueOf(data[0][1]));
		typMaster.setIsActive(String.valueOf(data[0][2]));
		// TODO Auto-generated method stub

	}

	/* for modifing the data */
	public boolean checkDuplicate(EmpTypeMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_EMP_TYPE WHERE UPPER(TYPE_NAME) LIKE '" + bean.getTypeName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	public boolean checkDuplicateMod(EmpTypeMaster bean) {
		boolean result = false;
		String query =
			"SELECT * FROM HRMS_EMP_TYPE WHERE UPPER(TYPE_NAME) LIKE '" + bean.getTypeName().trim().toUpperCase() + "' AND TYPE_ID not in("
				+ bean.getTypeID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public boolean checkType(EmpTypeMaster bean) {
		try {
			Object[][] asstName = getSqlModel().getSingleResult(getQuery(6));
			for(int i = 0; i < asstName.length; i++) {
				if(asstName[i][0].toString().trim().equalsIgnoreCase(bean.getTypeName().trim())) {
					return false;
				}// end of if
			}// end of loop
			return true;
		} catch(Exception e) {

			logger.error("error in checktype" + e);
			return false;
		}
	}

	/* for deleting one or more records from list */
	public boolean deleteEmptype(EmpTypeMaster typMaster, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if(code != null) {
			for(int i = 0; i < code.length; i++) {

				if(!code[i].equals("")) {
					//logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->" + code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if(!flag) {
						cnt++;
					}// end of if
					// result=true;
				}// end of if
			}// end of loop
		}// end of nested if
		if(cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}

	/* for deleting the record after selecting */
	public boolean deleteType(EmpTypeMaster bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getTypeID();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}

	/* generating the list onload */
	public void employeeSearch(EmpTypeMaster typMaster, HttpServletRequest request) {
		Object data[][] = getSqlModel().getSingleResult(getQuery(5));
		
		if(data != null && data.length > 0) {
			typMaster.setTotalRecords(data.length);
			typMaster.setRecordsAvailable(true);
			
			String[] pageIndex = Utility.doPaging(typMaster.getMyPage(), data.length, 20);	
			
			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			if(pageIndex[4].equals("1")) {
				typMaster.setMyPage("1");
			}
			
			ArrayList<Object> typList = new ArrayList<Object>();
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) { 
				EmpTypeMaster bean1 = new EmpTypeMaster();
				bean1.setTypeID(checkNull(String.valueOf(data[i][0])));
				bean1.setTypeName(checkNull(String.valueOf(data[i][1])));
				bean1.setTypeAbbr(checkNull(String.valueOf(data[i][2])));
				bean1.setIsActive(checkNull(String.valueOf(data[i][3])));
				
				typList.add(bean1);
			}
			typMaster.setTypeList(typList);
		}
	}

	public void getReport(EmpTypeMaster typMaster, HttpServletRequest request, HttpServletResponse response, ServletContext context,
		HttpSession session,String [] label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org/paradyne/rpt/admin/master/EmployeeType.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		
		String reportName = "Employee Type Master";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Employee Type  Master.Pdf");
		String queryDes = "SELECT  TYPE_NAME, TYPE_ABBR, IS_ACTIVE FROM HRMS_EMP_TYPE ORDER BY upper(TYPE_NAME) ";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][3];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];	
				j++;
			}
			int cellwidth[] = { 10, 40, 40, 40 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addTextBold("Employee Type Master", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

	public void getTypeRecord(EmpTypeMaster bean) {
		Object addObj[] = new Object[1];
		addObj[0] = bean.getTypeID();

		Object data[][] = getSqlModel().getSingleResult(getQuery(4), addObj);

		for(int i = 0; i < data.length; i++) {
			bean.setTypeID(checkNull(String.valueOf(data[i][0])));
			bean.setTypeName(checkNull(String.valueOf(data[i][1])));
			bean.setTypeAbbr(checkNull(String.valueOf(data[i][2])));
			bean.setEsiZone(checkNull(String.valueOf(data[i][3])));
			bean.setPtZone(checkNull(String.valueOf(data[i][4])));
			bean.setPfZone(checkNull(String.valueOf(data[i][5])));
			bean.setIsActive(checkNull(String.valueOf(data[i][6])));

		}// end of loop

	}

	public boolean modType(EmpTypeMaster bean) {

		if(!checkDuplicateMod(bean)) {
			Object addObj[][] = new Object[1][7];

			addObj[0][0] = bean.getTypeName().trim();
			addObj[0][1] = bean.getTypeAbbr().trim();
			addObj[0][2] = bean.getEsiZone().trim();
			addObj[0][3] = bean.getPtZone().trim();
			addObj[0][4] = bean.getPfZone().trim();
			addObj[0][5] = bean.getIsActive().trim();
			addObj[0][6] = bean.getTypeID().trim();
			
			
			return getSqlModel().singleExecute(getQuery(2), addObj);
		}// end of if

		else
			return false;

	}
	
	//Added by Tinshuk Banafar Begin
	
	public void getEmpTypeReport(EmpTypeMaster empMast, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = empMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Employee Type Master Details " + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Employee Type Master Details ");
		rds.setShowPageNo(true);
		
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setTotalColumns(5);
		
		rds.setGeneratedByText(empMast.getUserEmpId());
		rds.setUserEmpId(empMast.getUserEmpId());
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		}
		else{
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "EmpTypeMaster_input.action");
			// Initial Page Action name
			}

		String query = " SELECT TYPE_ID,TYPE_NAME,TYPE_ABBR,DECODE(IS_ACTIVE,'Y','Yes','N','No'),LMS_EMP_TYPE FROM HRMS_EMP_TYPE ORDER BY TYPE_ID";
		Object[][] queryData = getSqlModel().getSingleResult(query);
		//Defining Tabular Structure and data
		TableDataSet tdstable = new TableDataSet();
		tdstable.setHeader(new String[] { "EmpType ID", "EmpType Name",	"EmpType Abbreviation ","Is Active", "LMS EmpType" });// defining headers
		
		tdstable.setCellAlignment(new int[]{0,0,0,0,0});
		tdstable.setCellWidth(new int[]{10,25,25,20,20});
		
		// data object from query
		tdstable.setCellColSpan(new int[] { 5 });
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setData(queryData);
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
		
		if(reportPath.equals("")){
		rg.createReport(response);
		}
		else{
		/* Generates the report as attachment*/
		rg.saveReport(response);
		}
			
	}
	//Added by Tinshuk Banafar End
}