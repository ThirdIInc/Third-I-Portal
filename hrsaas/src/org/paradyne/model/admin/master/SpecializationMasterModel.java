package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.admin.master.SpecializationMaster;

/**
 * @author Prasad
 */

public class SpecializationMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.admin.master.SpecializationMasterAction.class);

	/**
	 * following function is called to add a new record
	 * 
	 * @param bean
	 * @return
	 */

	public String addSpecialization(SpecializationMaster bean) {
		Object[][] add = new Object[1][4];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = bean.getSpecializationName().trim();
			add[0][1] = bean.getSpecializationAbbr().trim();
			add[0][2] = bean.getSpecializationDesc();
			add[0][3] = bean.getSpecializationStatus();
			if (!checkDuplicate(bean)) {
				result = getSqlModel().singleExecute(getQuery(1), add);
				if (result) {
					String query = " SELECT MAX(SPEC_ID) FROM HRMS_SPECIALIZATION";
					Object[][] data = getSqlModel().getSingleResult(query);
					String query1 = " SELECT SPEC_NAME,SPEC_ABBR,DECODE(SPEC_STATUS,'A','Active','D','Deactive'),SPEC_DESC,SPEC_ID FROM "
							+ " HRMS_SPECIALIZATION WHERE SPEC_ID="
							+ String.valueOf(data[0][0]);
					Object[][] Data = getSqlModel().getSingleResult(query1);
					bean.setSpecializationName(checkNull(String
							.valueOf(Data[0][0])));
					bean.setSpecializationAbbr(checkNull(String
							.valueOf(Data[0][1])));
					bean.setSpecializationDesc(checkNull(String
							.valueOf(Data[0][3])));
					bean
							.setSpecStatsView(checkNull(String
									.valueOf(Data[0][2])));
					bean.setSpecializationCode(checkNull(String
							.valueOf(Data[0][4])));
					flag = "saved";
				} else {
					flag = "error";
				}
			} else {
				String query = "SELECT DECODE(SPEC_STATUS,'A','Active','D','Deactive') "
						+ " FROM HRMS_SPECIALIZATION where  SPEC_STATUS='"
						+ bean.getSpecializationStatus() + "'";
				Object[][] data = getSqlModel().getSingleResult(query);
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised----->");
		}
		return flag;
	}

	/**
	 * following function is called to update the record.
	 * 
	 * @param bean
	 * @return
	 */

	public String modSpecialization(SpecializationMaster bean) {
		String editFlag = "";
		boolean result = false;
		Object mod[][] = new Object[1][5];
		try {
			mod[0][0] = bean.getSpecializationName().trim();
			mod[0][1] = bean.getSpecializationAbbr().trim();
			mod[0][2] = bean.getSpecializationDesc();
			mod[0][3] = bean.getSpecializationStatus();
			mod[0][4] = bean.getSpecializationCode();
			if (!checkDuplicateMod(bean)) {
				result = getSqlModel().singleExecute(getQuery(2), mod);
				if (result) {
					String query1 = " SELECT SPEC_NAME,SPEC_ABBR,SPEC_DESC,DECODE(SPEC_STATUS,'A','Active','D','Deactive'),SPEC_ID  FROM HRMS_SPECIALIZATION"
							+ " WHERE SPEC_ID=" + bean.getSpecializationCode();
					Object[][] Data = getSqlModel().getSingleResult(query1);
					bean.setSpecializationName(checkNull(String
							.valueOf(Data[0][0])));
					bean.setSpecializationAbbr(checkNull(String
							.valueOf(Data[0][1])));
					bean.setSpecializationDesc(checkNull(String
							.valueOf(Data[0][2])));
					bean
							.setSpecStatsView(checkNull(String
									.valueOf(Data[0][3])));
					bean.setSpecializationCode(checkNull(String
							.valueOf(Data[0][4])));
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised--->");
		}
		return editFlag;
	}

	/**
	 * following function is called to delete a record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteSpecialization(SpecializationMaster bean) {
		Object del[][] = new Object[1][1];
		del[0][0] = bean.getSpecializationCode();
		return getSqlModel().singleExecute(getQuery(3), del);
	}

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

	public void getSpecialization(SpecializationMaster bean) {
		try {
			String query = " SELECT SPEC_NAME,SPEC_ABBR,SPEC_STATUS,SPEC_DESC,SPEC_ID,DECODE(SPEC_STATUS,'A','Active','D','Deactive')  FROM HRMS_SPECIALIZATION  "
					+ " WHERE SPEC_ID=" + bean.getSpecializationCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setSpecializationName(checkNull(String.valueOf(data[0][0])
						.trim()));
				bean.setSpecializationAbbr(checkNull(String.valueOf(data[0][1])
						.trim()));
				bean.setSpecializationStatus(checkNull(String
						.valueOf(data[0][2])));
				bean
						.setSpecializationDesc(checkNull(String
								.valueOf(data[0][3])));
				bean
						.setSpecializationCode(checkNull(String
								.valueOf(data[0][4])));
				bean.setSpecStatsView(checkNull(String.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSpecializationRec(SpecializationMaster bean) {
		try {
			String query = " SELECT SPEC_NAME,SPEC_ABBR,DECODE(SPEC_STATUS,'A','Active','D','Deactive'),SPEC_DESC,SPEC_ID,SPEC_STATUS  FROM HRMS_SPECIALIZATION  "
					+ " WHERE SPEC_ID=" + bean.getSpecializationCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setSpecializationName(checkNull(String.valueOf(data[0][0])
						.trim()));
				bean.setSpecializationAbbr(checkNull(String.valueOf(data[0][1])
						.trim()));
				bean.setSpecStatsView(checkNull(String.valueOf(data[0][2])));
				bean
						.setSpecializationDesc(checkNull(String
								.valueOf(data[0][3])));
				bean
						.setSpecializationCode(checkNull(String
								.valueOf(data[0][4])));
				bean.setSpecializationStatus(checkNull(String
						.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSpecializationOnDoubleClick(SpecializationMaster bean) {
		try {
			String query = " SELECT NVL(SPEC_NAME,' '),NVL(SPEC_ABBR,' '),DECODE(SPEC_STATUS,'A','Active','D','Deactive'),NVL(SPEC_DESC,' '),SPEC_ID,SPEC_STATUS  FROM HRMS_SPECIALIZATION"
					+ " WHERE SPEC_ID=" + bean.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				bean.setSpecializationName(checkNull(String.valueOf(data[0][0])
						.trim()));
				bean.setSpecializationAbbr(checkNull(String.valueOf(data[0][1])
						.trim()));
				bean.setSpecStatsView(checkNull(String
						.valueOf(data[0][2])));
				bean.setSpecializationDesc(checkNull(String.valueOf(data[0][3])
						.trim()));
				bean
						.setSpecializationCode(checkNull(String
								.valueOf(data[0][4])));
				bean.setSpecializationStatus(checkNull(String
						.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To show the records belonging to the specialization
	 * 
	 * @param bean
	 * @param request
	 */
	public void getRecords(SpecializationMaster bean, HttpServletRequest request) {
		try {
			int length=0;	
			String query = " SELECT SPEC_NAME,SPEC_ABBR,DECODE(SPEC_STATUS,'A','Active','D','Deactive'),SPEC_DESC,SPEC_ID  FROM HRMS_SPECIALIZATION"
					+ " ORDER BY SPEC_ID";
			Object[][] data = getSqlModel().getSingleResult(query);
			ArrayList<Object> obj = new ArrayList<Object>();
			/*
			 * int REC_TOTAL = 0; int To_TOT = 20; int From_TOT = 0; int pg1=0;
			 * int PageNo1=1;//---------- REC_TOTAL = data.length; int
			 * no_of_pages=Math.round(REC_TOTAL/20); double row =
			 * (double)data.length/20.0; java.math.BigDecimal value1 = new
			 * java.math.BigDecimal(row); int rowCount1
			 * =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
			 * ArrayList<Object> obj=new ArrayList<Object>();
			 * System.out.println("val of riwC"+rowCount1);
			 * request.setAttribute("abc", rowCount1); //PageNo
			 * if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals("
			 * ")) { PageNo1=1; From_TOT=0; To_TOT=20;
			 * 
			 * if(To_TOT >data.length){ To_TOT=data.length; }
			 * 
			 * bean.setMyPage("1"); } else{
			 * 
			 * pg1= Integer.parseInt(bean.getMyPage()); PageNo1=pg1;
			 * 
			 * if(pg1 ==1){ From_TOT=0; To_TOT=20; } else{ //
			 * From_TOTAL=To_TOTAL+1; To_TOT=To_TOT*pg1; From_TOT=(To_TOT-20); }
			 * if(To_TOT >data.length){ To_TOT=data.length; } }
			 * request.setAttribute("xyz", PageNo1);
			 */
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					data.length, 20);
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

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				// setting
				SpecializationMaster bean1 = new SpecializationMaster();
				bean1.setSpecializationName(String.valueOf(data[i][0]).trim());
				bean1.setSpecializationAbbr(String.valueOf(data[i][1]).trim());
				bean1.setSpecializationStatus(String.valueOf(data[i][2]));
				bean1.setSpecializationCode(String.valueOf(data[i][4]));
				obj.add(bean1);
			}
			bean.setSpecializationList(obj);
			length=data.length;
			bean.setTotalRecords(String.valueOf(length));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To delete the checked records
	 * 
	 * @param bean
	 * @param code
	 * @return boolean
	 */
	public boolean delChkdRec(SpecializationMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					//logger.info("code is"+code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					String query = "DELETE FROM HRMS_SPECIALIZATION WHERE SPEC_ID=? ";
					result =getSqlModel().singleExecute(query, delete);
					if (!result)
						count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return true;
		}
	}

	/**
	 * for checking duplicate entry of records during insertion
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicate(SpecializationMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_SPECIALIZATION WHERE UPPER(SPEC_NAME) LIKE '"
				+ bean.getSpecializationName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * for checking duplicate entry of records during modification
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicateMod(SpecializationMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_SPECIALIZATION WHERE UPPER(SPEC_NAME) LIKE '"
				+ bean.getSpecializationName().trim().toUpperCase()
				+ "' AND SPEC_ID not in(" + bean.getSpecializationCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * Following function is called to get the report of the Pdf format for list
	 * of Specialization Records
	 * 
	 * @param bean
	 * @param response
	 * @param label
	 */
	public void generateReport(SpecializationMaster specMaster,
			HttpServletResponse response, String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "Specialization Master";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Specialization Master.Pdf");
		String queryDes = "SELECT SPEC_NAME,SPEC_ABBR,DECODE(SPEC_STATUS,'A','Active','D','Deactive') "
				+ "FROM HRMS_SPECIALIZATION ORDER BY SPEC_ID";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				j++;
			}
			int cellwidth[] = { 5, 20, 20, 20 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addTextBold("Specialization Master", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}
}