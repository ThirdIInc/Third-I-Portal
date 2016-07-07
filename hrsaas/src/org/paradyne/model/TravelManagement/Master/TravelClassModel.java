package org.paradyne.model.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Master.TravelClass;
import org.paradyne.lib.ModelBase;

public class TravelClassModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelClassModel.class);

	public void reqData(TravelClass bean, HttpServletRequest request) {
		try {

			String query = "SELECT  NVL(CLASS_NAME,''),NVL(JOURNEY_NAME,' '), CASE WHEN CLASS_STATUS='A' THEN 'Active' WHEN CLASS_STATUS='D' THEN 'Deactive' ELSE ' ' END AS STATUS,CLASS_MIN_EXPENSE,"
					+ " CLASS_ID, CLASS_LEVEL,JOURNEY_LEVEL FROM HRMS_TMS_JOURNEY_CLASS "
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) ORDER BY JOURNEY_LEVEL,CLASS_LEVEL";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 20) {
				String firstRecord = "";
				String lastRecord = "";
				int numOfPage = data.length / 20;
				int j = 19;
				for (int i = 0; i < numOfPage; i++) {
					if (i < (numOfPage - 1)) {
						firstRecord += "" + data[j + 1][4] + ",";
						lastRecord += "" + data[j][4] + ",";

					} else {
						firstRecord += "" + data[j][4];
						lastRecord += "" + data[j - 1][4] + "";
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
				TravelClass bean1 = new TravelClass();

				bean1.setTrvlClsIt(String.valueOf(data[i][0]).trim());
				bean1.setTrvlModeIt(String.valueOf(data[i][1]));
				//bean1.setStatus(String.valueOf(data[i][2]));
				bean1.setTrvlStatusIt(String.valueOf(data[i][2]));
				bean1.setExpnseAmnt(String.valueOf(data[i][3]));
				bean1.setTrvlClsItId(String.valueOf(data[i][4]));
				bean1.setItLevel(String.valueOf(data[i][5]));
				

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

	public boolean addTravelClass(TravelClass bean) {
		boolean result = false;
		if (!checkDuplicate(bean)) {
			Object[][] add = new Object[1][6];
			String query = " SELECT NVL(MAX(CLASS_ID),0)+1,NVL(MAX(CLASS_LEVEL),0)+1 FROM HRMS_TMS_JOURNEY_CLASS";
			Object[][] data = getSqlModel().getSingleResult(query);
			add[0][0] = String.valueOf(data[0][0]);
			add[0][1] = bean.getTrvlClass().toUpperCase().trim();
			add[0][2] = bean.getTrvlModeId();
			add[0][3] = bean.getStatus();
			
			add[0][4] = String.valueOf(data[0][1]);
			add[0][5]=bean.getExpnseAmnt();
			
			
			bean.setTrvlClsId(String.valueOf(data[0][0]));
			bean.setHiddencode(String.valueOf(data[0][0]));
			if (bean.getStatus().startsWith("A"))
				bean.setStatus("Active");
			else
				bean.setStatus("Deactive");
			result = getSqlModel().singleExecute(getQuery(1), add);

		}
		return result;
	}

	public boolean modTravelClass(TravelClass bean) {
		if (!checkDuplicateMod(bean)) {
			
			//UPDATE HRMS_TMS_JOURNEY_CLASS SET CLASS_NAME=?,CLASS_JOURNEY_ID=?,CLASS_STATUS=?,CLASS_MIN_EXPENSE=? WHERE CLASS_ID=?
			Object mod[][] = new Object[1][5];
			mod[0][0] = bean.getTrvlClass().toUpperCase().trim();
			mod[0][1] = bean.getTrvlModeId();
			mod[0][2] = bean.getStatus();
			mod[0][3] = bean.getExpnseAmnt();
			mod[0][4] = bean.getHiddencode();
			
			if (bean.getStatus().startsWith("A"))
				bean.setStatus("Active");
			else if(bean.getStatus().startsWith("D")){
				bean.setStatus("Deactive");
			}
			else{
				bean.setStatus("");
			}
				
			return getSqlModel().singleExecute(getQuery(2), mod);
		}
		return false;
	}

	public void getTravelClassSearch(TravelClass bean) {
		try {
			String query1 = " SELECT  NVL(CLASS_NAME,''),CLASS_JOURNEY_ID,NVL(JOURNEY_NAME,' '), CASE WHEN CLASS_STATUS='A' THEN 'Active' WHEN CLASS_STATUS='D' THEN 'Deactive' ELSE ' ' END AS STATUS,"
					+ "	 CLASS_ID,CLASS_MIN_EXPENSE FROM HRMS_TMS_JOURNEY_CLASS"
					+ "	LEFT JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
					+ "	WHERE CLASS_ID=" + bean.getTrvlClsId();
			Object[][] data = getSqlModel().getSingleResult(query1);

			if (data != null && data.length > 0) {
				bean.setTrvlClass(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setTrvlModeId(checkNull(String.valueOf(data[0][1])));
				bean.setTrvlMode(checkNull(String.valueOf(data[0][2])));
				//bean.setStatus(checkNull(String.valueOf(data[0][3])));
				String status =checkNull(String.valueOf(data[0][3]));
				if(status.equals("Active")){
					bean.setStatus("A");	
				}else if(status.equals("Deactive")){
					bean.setStatus("D");
				}else{
					bean.setStatus("");
				}
					
				//bean.setTrvlClsId(checkNull(String.valueOf(data[0][4])));
				bean.setTrvlClsItId(checkNull(String.valueOf(data[0][4])));
				bean.setHiddencode(String.valueOf(data[0][4]));
				bean.setExpnseAmnt(checkNull(String.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getTravelClassOnDoubleClick(TravelClass bean) {
		try {

			/*String query1 = " SELECT  NVL(CLASS_NAME,''),CLASS_JOURNEY_ID,NVL(JOURNEY_NAME,' '), CASE WHEN CLASS_STATUS='A' THEN 'Active' ELSE 'Deactive' END AS STATUS,"
					+ "	 CLASS_ID FROM HRMS_TMS_JOURNEY_CLASS"
					+ "	LEFT JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
					+ "	WHERE CLASS_ID=" + bean.getHiddencode();*/
			String query1 = " SELECT  NVL(CLASS_NAME,''),CLASS_JOURNEY_ID,NVL(JOURNEY_NAME,' '),   CLASS_STATUS ,"
				+ "	 CLASS_ID,CLASS_MIN_EXPENSE FROM HRMS_TMS_JOURNEY_CLASS"
				+ "	LEFT JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
				+ "	WHERE CLASS_ID=" + bean.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query1);

			if (data != null && data.length > 0) {

				bean.setTrvlClass(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setTrvlModeId(checkNull(String.valueOf(data[0][1])));
				bean.setTrvlMode(checkNull(String.valueOf(data[0][2])));
				bean.setStatus(checkNull(String.valueOf(data[0][3])));
				//bean.setTrvlStatusIt(checkNull(String.valueOf(data[0][3])));
				bean.setTrvlClsItId(checkNull(String.valueOf(data[0][4])));
				bean.setExpnseAmnt(checkNull(String.valueOf(data[0][5])));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean delChkdRec(TravelClass bean, String[] code) {
		Object[][] delObj = new Object[code.length][1];
		for (int i = 0; i < code.length; i++)
			delObj[i][0] = code[i];
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	public boolean deleteTravelClass(TravelClass bean) {
		Object del[][] = new Object[1][1];
		del[0][0] = bean.getTrvlClsId();
		return getSqlModel().singleExecute(getQuery(3), del);
	}

	public void getTravelClass(TravelClass bean) {

		// TODO Auto-generated method stub
		try {

			String query1 = " SELECT  NVL(CLASS_NAME,''),CLASS_JOURNEY_ID,NVL(JOURNEY_NAME,' '), CASE WHEN CLASS_STATUS='A' THEN 'Active' WHEN CLASS_STATUS='D' THEN 'Deactive' ELSE ' ' END AS STATUS,"
					+ "	 CLASS_ID,CLASS_MIN_EXPENSE FROM HRMS_TMS_JOURNEY_CLASS"
					+ "	LEFT JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
					+ "	WHERE CLASS_ID=" + bean.getTrvlClsId();

			Object[][] data = getSqlModel().getSingleResult(query1);

			if (data != null && data.length > 0) {
				bean.setTrvlClass(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setTrvlModeId(checkNull(String.valueOf(data[0][1])));
				bean.setTrvlMode(checkNull(String.valueOf(data[0][2])));
				bean.setStatus(checkNull(String.valueOf(data[0][3])));
				bean.setTrvlClsItId(checkNull(String.valueOf(data[0][4])));
				bean.setExpnseAmnt(checkNull(String.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String upData(TravelClass bean) {
		String code = bean.getUpId();
		String previousId = "";
		String currentId = "";
		System.out
				.println(" bean.getPreviousLevel()------travel-------------------"
						+ bean.getPreviousLevel());
		String preSql = "SELECT CLASS_ID FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_LEVEL ="
				+ bean.getPreviousLevel();
		Object[][] prevousJourData = getSqlModel().getSingleResult(preSql);
		if (prevousJourData != null && prevousJourData.length > 0) {
			previousId = "" + prevousJourData[0][0];

			logger.info("Previous id----------" + previousId);
		}

		String currentSql = "SELECT CLASS_ID FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_LEVEL ="
				+ bean.getCurrentLevel();
		Object[][] currentJourData = getSqlModel().getSingleResult(currentSql);
		if (currentJourData != null && currentJourData.length > 0) {
			currentId = "" + currentJourData[0][0];

			logger.info("Current id----------" + currentId);
		}

		
		String getQuery = "SELECT CLASS_ID,CLASS_LEVEL FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_JOURNEY_ID IN(SELECT CLASS_JOURNEY_ID FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_LEVEL ="
				+ bean.getCurrentLevel() + ")" + " ORDER BY CLASS_LEVEL ASC ";

		Object[][] data = getSqlModel().getSingleResult(getQuery);
		// =======for down
		boolean isLastTecord = false;
		boolean isFirstTecord = false;

		for (int i = 0; i < data.length; i++) {
			if (bean.getCurrentLevel().equals(String.valueOf(data[i][1]))) {
				isLastTecord = true;
			} else {
				isLastTecord = false;
			}
		}

		for (int i = 0; i < data.length; i++) {
			if (i == 0
					&& bean.getCurrentLevel()
							.equals(String.valueOf(data[i][1]))) {
				isFirstTecord = true;
			}

		}

		System.out
				.println("*********************************isLastTecord      "
						+ isLastTecord);
		System.out
				.println("*********************************isFirstTecord      "
						+ isFirstTecord);
		if (isLastTecord && bean.getUpDownFlag().equals("DOWN")) {

			return "Tis is Last level ,so can not move";

			/*
			 * for (int i = 0; i < data.length; i++) { String sql = "" ;
			 * if(i==0){ sql = "UPDATE HRMS_TMS_JOURNEY_CLASS SET CLASS_LEVEL
			 * ="+data[data.length-1][1]+"" +" WHERE CLASS_ID= "+data[i][0] ; }
			 * else{ sql = "UPDATE HRMS_TMS_JOURNEY_CLASS SET CLASS_LEVEL
			 * ="+data[i-1][1]+"" +" WHERE CLASS_ID= "+data[i][0] ; }
			 * 
			 * getSqlModel().singleExecute(sql); }
			 */

		} else if (isFirstTecord && bean.getUpDownFlag().equals("UP")) {

			return "This is First  level ,so can not move";

		}

		// ++++++++++++++++++++++shash
		else {

			String sql = "UPDATE HRMS_TMS_JOURNEY_CLASS SET CLASS_LEVEL ="
					+ bean.getPreviousLevel() + " WHERE CLASS_ID=" + currentId;
			getSqlModel().singleExecute(sql);

			String sqlUp = "UPDATE HRMS_TMS_JOURNEY_CLASS SET CLASS_LEVEL ="
					+ bean.getCurrentLevel() + " WHERE CLASS_ID =" + previousId;
			getSqlModel().singleExecute(sqlUp);

		}

		return "";
	}

	public boolean checkDuplicate(TravelClass bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TMS_JOURNEY_CLASS WHERE UPPER(CLASS_NAME) LIKE  '"
				+ bean.getTrvlClass().trim().toUpperCase() + "' AND  CLASS_JOURNEY_ID="+bean.getTrvlModeId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(TravelClass bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TMS_JOURNEY_CLASS WHERE UPPER(CLASS_NAME) LIKE '"
				+ bean.getTrvlClass().trim().toUpperCase()
				+ "' AND CLASS_ID not in(" + bean.getTrvlClsId() + ")  AND  CLASS_JOURNEY_ID="+bean.getTrvlModeId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

}
