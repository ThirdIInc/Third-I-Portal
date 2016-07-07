package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.admin.master.*; /*
 * author:Pradeep K. Sahoo
 * Date:17.07.2007
 */
import org.paradyne.bean.admin.srd.Holiday;

public class HolidayMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/* for inserting record*/
	public boolean addHoliday(HolidayMaster bean) {
		//logger.info("addFamDtl");
		Object addObj[][] = new Object[1][4];

		addObj[0][0] = bean.getHoliDate().trim();
		addObj[0][1] = bean.getDesc().trim();
		addObj[0][2] = bean.getHolidayType().trim();
		addObj[0][3] = bean.getIsActive();

		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		//if(result) {

		//	return "Record saved Successfully ";
		//} else {

		//	return "Record cannot be added";
		//	}
		return result;

	}

	public boolean checkDate(HolidayMaster bean) {
		boolean flag;
		Object addObj[] = new Object[1];
		addObj[0] = bean.getHoliDate();
		//logger.info("Value of date" + addObj[0]);
		Object[][] data = getSqlModel().getSingleResult(getQuery(2), addObj);
		if (data.length == 0) {
			flag = false;
		}//end of if
		else {
			flag = true;
		}//end of else
		return flag;
	}

	/* for modifing the record*/
	public boolean modHoliday(HolidayMaster bean) {
		//logger.info("addFamDtl");
		Object modObj[][] = new Object[1][5];
		modObj[0][0] = bean.getDesc().trim();
		modObj[0][1] = bean.getHoliDate();
		modObj[0][2] = bean.getHolidayType().trim();
		modObj[0][3] = bean.getIsActive();
		modObj[0][4] = bean.getHidedate();

		boolean result = getSqlModel().singleExecute(getQuery(3), modObj);

		return result;

	}

	/* for deleting record after selecting*/
	public boolean deleteHoliday(HolidayMaster bean) {
		Object delObj[][] = new Object[1][1];

		delObj[0][0] = bean.getHoliDate();
		//logger.info("Value of code in delete" + delObj[0][0]);
		return getSqlModel().singleExecute(getQuery(4), delObj);
	}

	public void getReport(HolidayMaster bean) {

		String query = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),NVL(HOLI_DESC,' '),decode(HOLI_TYPE,'H','Holiday','N','National Holiday') FROM HRMS_HOLIDAY ORDER BY HOLI_DATE ";
		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> holiList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			HolidayMaster bean1 = new HolidayMaster();
			bean1.setHoliDate(checkNull(String.valueOf(data[i][0])));
			bean1.setDesc(checkNull(String.valueOf(data[i][1])));
			bean1.setHolidayType(checkNull(String.valueOf(data[i][1])));
			holiList.add(bean1);

		}//end of loop
		bean.setHoliList(holiList);

	}

	public void getReport1(HolidayMaster bean, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session,String[]label ) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\holiday.rpt";
		cr.createReport(request, response, context, session, path, "");
	*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nHoliday Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Holiday  Master.Pdf");
		String queryDes = "SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),HOLI_DESC,decode(HOLI_TYPE,'H','Holiday','N','National Holiday'),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_HOLIDAY order by HOLI_DATE";
						
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
			int cellwidth[] = { 15, 25, 40,20, 20 };
			int alignment[] = { 1, 1, 0,0,0};
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
	public boolean getReport(Holiday bean, HttpServletResponse response) {

		String name = "Holiday Report";
		String type = "Pdf";
		String title = "Holiday Report";

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);
		//rg.rotatePage();
		rg = getHeader(rg, bean);
		rg.createReport(response);
		return true;

	}

	public ReportGenerator getHeader(ReportGenerator rg, Holiday bean) {

		String header = "Holiday Report \n\n\n\n";
		rg.addFormatedText(header, 2, 0, 1, 0);

		String query = "  SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),HOLI_DESC ,decode(HOLI_TYPE,'H','Holiday','N','National Holiday') FROM HRMS_HOLIDAY  "
				+ " 	WHERE TO_CHAR(HOLI_DATE,'YYYY') =TO_CHAR(SYSDATE,'YYYY') "
				+ "	ORDER BY TO_DATE(HOLI_DATE,'DD-MM-YYYY') ";
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data.length > 0) {
			try {
				Object[][] result = new Object[data.length][3];
				int cnt = 1;
				for (int i = 0; i < data.length; i++) {
					result[i][0] = cnt;
					result[i][1] = String.valueOf(data[i][0]);
					result[i][2] = String.valueOf(data[i][1]);
					cnt++;
				}

				String[] colNames = { "S No", "Holiday Date",
						"Holiday Description" ,"Holiday Type"};

				int[] cellWidth = { 10, 15, 50,25 };
				int[] cellTxt = { 60, 60, 60 ,60};
				int[] cellAll = { 0, 1, 0,0 };

				rg.tableBody(colNames, result, cellWidth, cellAll);

			} catch (Exception e) {
				logger.error(" Exception in getHeader :" + e);
			}
		}
		return rg;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}

	/* generating the list on load */
	public void Data(HolidayMaster bean, HttpServletRequest request) {

		Object[][] repData = getSqlModel().getSingleResult(getQuery(5));
		
		if(repData!=null && repData.length>0){
			bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(repData.length));
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), repData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
			HolidayMaster bean1 = new HolidayMaster();

			bean1.setHidedate(checkNull(String.valueOf(repData[i][0])));
			bean1.setDesc(checkNull(String.valueOf(repData[i][1])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][2])));

			List.add(bean1);
		}//end of loop

		bean.setIteratorlist(List);
		}
	}

	/* for selecting the data from list by double click and setting those data in respective fields*/

	public void calforedit(HolidayMaster bean) {

		String query = "SELECT TO_CHAR(HOLI_DATE,'dd-mm-yyyy'),nvl(HOLI_DESC,''),HOLI_TYPE,IS_ACTIVE FROM HRMS_HOLIDAY WHERE HOLI_DATE= TO_DATE('"
				+ bean.getHiddencode() + "','dd-mm-yyyy')";

		Object[][] data = getSqlModel().getSingleResult(query);
		//setter

		bean.setHidedate(String.valueOf(data[0][0]));
		bean.setHoliDate(String.valueOf(data[0][0]));

		bean.setDesc(checkNull(String.valueOf(data[0][1])));
		bean.setHolidayType(checkNull(String.valueOf(data[0][2])));
		bean.setIsActive(checkNull(String.valueOf(data[0][3])));
	}

	/*public boolean calfordelete(HolidayMaster bean) {
	
	 Object [][] delete = new Object [1][1];
	 delete [0][0] =  bean.getHiddencode();
	 return getSqlModel().singleExecute(getQuery(3), delete);
	 // TODO Auto-generated method stub

	 }*/

	/* for deleting the one or more records  from list */
	public boolean deletecheckedRecords(HolidayMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(4), delete);
					if (!result)
						count++;

				}//end of if
			}//end of loop
		}//end of if
		if (count != 0) {
			result = false;
			return result;
		}//end of if
		else {
			result = true;
			return result;
		}//end of else
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		HolidayMasterModel.logger = logger;
	}

}
