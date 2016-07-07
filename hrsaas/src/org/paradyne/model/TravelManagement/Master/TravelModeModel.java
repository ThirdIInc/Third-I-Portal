package org.paradyne.model.TravelManagement.Master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.TravelMode;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class TravelModeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelModeModel.class);

	public void reqData(TravelMode bean, HttpServletRequest request) {
		try {

			String query = "SELECT NVL(JOURNEY_NAME,' '),CASE WHEN JOURNEY_STATUS='A' THEN 'Active' WHEN JOURNEY_STATUS='D' THEN 'Deactive' ELSE ' ' END, JOURNEY_ID,JOURNEY_LEVEL"
					+ " FROM HRMS_TMS_JOURNEY_MODE"
					+ " ORDER BY JOURNEY_LEVEL ";
			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 20) {
				String firstRecord = "";
				String lastRecord = "";
				int numOfPage = data.length / 20;
				int j = 19;
				for (int i = 0; i < numOfPage; i++) {
					if (i < (numOfPage - 1)) {
						firstRecord += "" + data[j + 1][3] + ",";
						lastRecord += "" + data[j][3] + ",";

					} else {
						firstRecord += "" + data[j][3];
						lastRecord += "" + data[j - 1][3] + "";
					}
					j = j + 20;
				}
				bean.setFirstRecordOfPage(firstRecord);
				bean.setLastRecordOfPage(lastRecord);
			}

			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = data.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) data.length / 20.0;

			java.math.BigDecimal value1 = new java.math.BigDecimal(row);

			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			ArrayList<Object> obj = new ArrayList<Object>();
			System.out.println("val of riwC" + rowCount1);
			request.setAttribute("abc", rowCount1);
			// PageNo
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals(" ")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;

				if (To_TOT > data.length) {
					To_TOT = data.length;
				}

				bean.setMyPage("1");
			} else {
				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				} else {
					// From_TOTAL=To_TOTAL+1;
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > data.length) {
					To_TOT = data.length;
				}
			}
			request.setAttribute("xyz", PageNo1);
			// request.setAttribute("record", lastRecords);

			for (int i = From_TOT; i < To_TOT; i++) {
				// setting
				TravelMode bean1 = new TravelMode();

				bean1.setTrvMode(String.valueOf(data[i][0]).trim());
				bean1.setStatus(String.valueOf(data[i][1]));
				bean1.setTrvId(String.valueOf(data[i][2]));
				bean1.setItLevel(String.valueOf(data[i][3]));
				obj.add(bean1);
			}

			bean.setTrvList(obj);

			bean.setModeLength(String.valueOf(data.length));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean addTravelMode(TravelMode bean) {
		boolean result = false;

		if (!checkDuplicate(bean)) {
			Object[][] add = new Object[1][4];
			String query = " SELECT NVL(MAX(JOURNEY_ID),0)+1,NVL(MAX(JOURNEY_LEVEL),0)+1 FROM HRMS_TMS_JOURNEY_MODE";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {

				add[0][0] = String.valueOf(data[0][0]);
				add[0][1] = bean.getTrvMode().toUpperCase().trim();
				add[0][2] = bean.getStatus();
				System.out.println("add[0][2]-----------"+add[0][2]);
				add[0][3] = String.valueOf(data[0][1]);
				bean.setTrvId(String.valueOf(data[0][0]));
				if (bean.getStatus().startsWith("A"))
					bean.setStatus("Active");
				else if(bean.getStatus().startsWith("D"))
					bean.setStatus("Deactive");
				else{
					bean.setStatus(" ");
				}
				result = getSqlModel().singleExecute(getQuery(1), add);
			}
		}

		return result;

	}

	public boolean modTravelMode(TravelMode bean) {

		if (!checkDuplicateMod(bean)) {
			Object mod[][] = new Object[1][3];
			mod[0][0] = bean.getTrvMode().toUpperCase().trim();
			mod[0][1] = bean.getStatus();
			mod[0][2] = bean.getTrvId();
			if (bean.getStatus().startsWith("A"))
				bean.setStatus("Active");
			else if (bean.getStatus().startsWith("D"))
				bean.setStatus("Deactive");
			else
				bean.setStatus("");
			return getSqlModel().singleExecute(getQuery(2), mod);
		} else
			return false;

	}

	/**
	 * following function is called when a record is selected from search window
	 * to set the records.
	 * 
	 * @param bean
	 */
	public void getTravelMode(TravelMode bean) {

		try {
			String query1 = " SELECT NVL(JOURNEY_NAME,' '),CASE WHEN JOURNEY_STATUS='A' THEN 'Active' WHEN JOURNEY_STATUS='D' THEN 'Deactive' ELSE ' ' END,NVL(JOURNEY_ID,'')  FROM HRMS_TMS_JOURNEY_MODE"
					+ " WHERE JOURNEY_ID=" + bean.getTrvId();

			Object[][] data = getSqlModel().getSingleResult(query1);

			if (data != null && data.length > 0) {

				bean.setTrvMode(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setStatus(checkNull(String.valueOf(data[0][1])));
				bean.setTrvId(checkNull(String.valueOf(data[0][2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getTravelModeSearch(TravelMode bean) {
		try {

			String query1 = " SELECT NVL(JOURNEY_NAME,' '),JOURNEY_STATUS,NVL(JOURNEY_ID,'')  FROM HRMS_TMS_JOURNEY_MODE"
					+ " WHERE JOURNEY_ID=" + bean.getTrvId();
			Object[][] data = getSqlModel().getSingleResult(query1);
			if (data != null && data.length > 0) {
				bean.setTrvMode(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setStatus(checkNull(String.valueOf(data[0][1])));
				bean.setTrvId(checkNull(String.valueOf(data[0][2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getTravelModeOnDoubleClick(TravelMode bean) {
		try {

			logger.info("---Inside getTravelModeOnDoubleClick-------"
					+ bean.getHiddencode());
			String query1 = " SELECT JOURNEY_NAME,JOURNEY_STATUS,JOURNEY_ID  FROM HRMS_TMS_JOURNEY_MODE"
					+ " WHERE JOURNEY_ID=" + bean.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query1);

			if (data != null && data.length > 0) {

				bean.setTrvMode(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setStatus(checkNull(String.valueOf(data[0][1]).trim()));
				bean.setTrvId(checkNull(String.valueOf(data[0][2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean deleteTravelMode(TravelMode bean) {
		// TODO Auto-generated method stub
		Object del[][] = new Object[1][1];
		del[0][0] = bean.getTrvId();
		return getSqlModel().singleExecute(getQuery(3), del);
	}

	public boolean delChkdRec(TravelMode bean, String[] code) {
		Object[][] delObj = new Object[code.length][1];
		for (int i = 0; i < code.length; i++)
			delObj[i][0] = code[i];
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	public void generateReport(TravelMode travel, HttpServletResponse response,
			String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "Travel Mode";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);

		String query = "SELECT  JOURNEY_NAME,NVL(JOURNEY_CLASS_NAME,' '),CASE WHEN JOURNEY_STATUS='A' THEN 'Active' WHEN JOURNEY_STATUS='D' THEN 'Deactive' ELSE 'Deactive' END FROM HRMS_TMS_JOURNEY_MODE ORDER BY JOURNEY_LEVEL ";
		Object[][] data = getSqlModel().getSingleResult(query);
		Object[][] finalData = new Object[data.length][4];

		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				finalData[i][0] = j;
				finalData[i][1] = data[i][0];
				finalData[i][2] = data[i][1];
				finalData[i][3] = data[i][2];
				j++;
			}

			// String abc[] = { "Sr No", "Travel Mode","Travel Class","Status"};
			int cellwidth[] = { 5, 20, 20, 20 };
			int alignment[] = { 0, 0, 0, 0 };
			// rg.addFormatedText("Room type", 1,0,1,0);
			rg.addTextBold("Travel Mode", 0, 1, 0);
			rg.addText("\n\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n\n", 0, 0, 0);
			rg.tableBody(label, finalData, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

	public void upData(TravelMode bean) {
		String code = bean.getUpId();
		String previousId = "";
		String currentId = "";

		String preSql = "SELECT JOURNEY_ID FROM HRMS_TMS_JOURNEY_MODE WHERE JOURNEY_LEVEL ="
				+ bean.getPreviousLevel();
		Object[][] prevousJourData = getSqlModel().getSingleResult(preSql);
		if (prevousJourData != null && prevousJourData.length > 0) {
			previousId = "" + prevousJourData[0][0];
		}

		String currentSql = "SELECT JOURNEY_ID FROM HRMS_TMS_JOURNEY_MODE WHERE JOURNEY_LEVEL ="
				+ bean.getCurrentLevel();
		Object[][] currentJourData = getSqlModel().getSingleResult(currentSql);
		if (currentJourData != null && currentJourData.length > 0) {
			currentId = "" + currentJourData[0][0];
		}

		String sql = "UPDATE HRMS_TMS_JOURNEY_MODE SET JOURNEY_LEVEL ="
				+ bean.getPreviousLevel() + " WHERE JOURNEY_ID=" + currentId;
		getSqlModel().singleExecute(sql);

		String sqlUp = "UPDATE HRMS_TMS_JOURNEY_MODE SET JOURNEY_LEVEL ="
				+ bean.getCurrentLevel() + " WHERE JOURNEY_ID =" + previousId;
		getSqlModel().singleExecute(sqlUp);

	}

	public boolean checkDuplicate(TravelMode bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TMS_JOURNEY_MODE WHERE UPPER(JOURNEY_NAME) LIKE  '"
				+ bean.getTrvMode().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(TravelMode bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TMS_JOURNEY_MODE WHERE UPPER(JOURNEY_NAME) LIKE '"
				+ bean.getTrvMode().trim().toUpperCase()
				+ "' AND JOURNEY_ID not in(" + bean.getTrvId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

}
