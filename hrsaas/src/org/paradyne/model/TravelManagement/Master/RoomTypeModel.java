package org.paradyne.model.TravelManagement.Master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.RoomType;
import org.paradyne.bean.TravelManagement.Master.TravelClass;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author aa0651
 * 
 */
public class RoomTypeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void generateReport(RoomType rmType, HttpServletResponse response,
			String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "Room Type";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		// String sysQuery="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		// Object [][]sysData=getSqlModel().getSingleResult(sysQuery);
		// rg.addText("Date: "+sysData[0][0]+"", 0, 2, 0);

		String query = "SELECT  HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME,ROOM_TYPE_NAME,NVL(ROOM_TYPE_DESC,''),CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' WHEN ROOM_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END "
				+ "   FROM HRMS_TMS_ROOM_TYPE  "
		        + "  LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE) "
		        + " ORDER BY ROOM_LEVEL ";
		Object[][] data = getSqlModel().getSingleResult(query);
		Object[][] finalData = new Object[data.length][5];

		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				finalData[i][0] = j;
				finalData[i][1] = data[i][0];
				finalData[i][2] = data[i][1];
				finalData[i][3] = data[i][2];
				finalData[i][4] = data[i][3];
				j++;
			}

			// String abc[] = { "Sr No", "Room Type
			// Name","Description","Status"};
			int cellwidth[] = { 5, 20,20, 20, 20 };
			int alignment[] = { 0,0, 0, 0, 0 };
			// rg.addFormatedText("Room type", 1,0,1,0);
			rg.addTextBold("Room type", 0, 1, 0);
			rg.addText("\n\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n\n", 0, 0, 0);
			rg.tableBody(label, finalData, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

	/**
	 * following function is called when a record is selected from search window
	 * to set the records.
	 * 
	 * @param bean
	 */
	public void getRoomType(RoomType bean) {
		// TODO Auto-generated method stub
		try {

			logger.info("bean.getDesignationCode--->" + bean.getTypeId());

			String query = " SELECT ROOM_TYPE_NAME,NVL(ROOM_TYPE_DESC,''),CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' WHEN ROOM_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,ROOM_TYPE_ID"
					+ ",HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME,ROOM_HOTEL_TYPE"
					+ " FROM HRMS_TMS_ROOM_TYPE  "
					+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
					+ " WHERE ROOM_TYPE_ID=" + bean.getTypeId();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setTypeName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setDesciption(checkNull(String.valueOf(data[0][1])));
				bean.setStatus(checkNull(String.valueOf(data[0][2])));
				bean.setTypeId(checkNull(String.valueOf(data[0][3])));
				bean.setHotelName(checkNull(String.valueOf(data[0][4])));
				bean.setHotelId(checkNull(String.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getRoomTypeSearch(RoomType bean) {
		// TODO Auto-generated method stub
		try {

			logger.info("bean.getDesignationCode--->" + bean.getTypeId());

			String query = " SELECT ROOM_TYPE_NAME,NVL(ROOM_TYPE_DESC,''), ROOM_TYPE_STATUS,ROOM_TYPE_ID"
					+ ",HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME,ROOM_HOTEL_TYPE"
					+ " FROM HRMS_TMS_ROOM_TYPE  "
					+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
					+ " WHERE ROOM_TYPE_ID=" + bean.getTypeId();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setTypeName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setDesciption(checkNull(String.valueOf(data[0][1])));
				bean.setStatus(checkNull(String.valueOf(data[0][2])));
				bean.setTypeId(checkNull(String.valueOf(data[0][3])));
				bean.setHotelName(checkNull(String.valueOf(data[0][4])));
				bean.setHotelId(checkNull(String.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean checkDuplicate(RoomType bean) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_TMS_ROOM_TYPE WHERE UPPER(ROOM_TYPE_NAME) LIKE '"
				+ bean.getTypeName().trim().toUpperCase() + "'AND  ROOM_HOTEL_TYPE="+bean.getHotelId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}

	
	
	
	
	
	public boolean checkDuplicateMod(RoomType bean) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_TMS_ROOM_TYPE WHERE UPPER(ROOM_TYPE_NAME) LIKE '"
				+ bean.getTypeName().trim().toUpperCase()
				+ "' AND ROOM_TYPE_ID not in(" + bean.getTypeId() + ")AND  ROOM_HOTEL_TYPE="+bean.getHotelId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	public boolean addRoomType(RoomType bean) {
		// TODO Auto-generated method stub
		if (!checkDuplicate(bean)) {
			Object[][] add = new Object[1][4];
			try {
				add[0][0] = bean.getTypeName().trim();
				add[0][1] = bean.getDesciption();
				add[0][2] = bean.getStatus();
				add[0][3] = bean.getHotelId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			boolean result = getSqlModel().singleExecute(getQuery(1), add);

			if (result) {

				String query = " SELECT MAX(ROOM_TYPE_ID) FROM HRMS_TMS_ROOM_TYPE";

				Object[][] data = getSqlModel().getSingleResult(query);

				System.out.println("data[0][0]---->" + data[0][0]);

				String query1 = " SELECT ROOM_TYPE_NAME	,CASE WHEN ROOM_TYPE_STATUS	='A' THEN 'Active' WHEN ROOM_TYPE_STATUS='D' THEN 'Deactive' ELSE '' END,NVL(ROOM_TYPE_DESC,''),"
						+ " ROOM_TYPE_ID,HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME,ROOM_HOTEL_TYPE FROM "
						+ " HRMS_TMS_ROOM_TYPE"
						+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
						+ " WHERE ROOM_TYPE_ID=" + String.valueOf(data[0][0]);

				logger.info("query1 in addDesignation---->" + query1);

				Object[][] Data = getSqlModel().getSingleResult(query1);

				bean.setTypeName(checkNull(String.valueOf(Data[0][0])).trim());
				bean
						.setDesciption(checkNull(String.valueOf(Data[0][2]))
								.trim());
				bean.setStatus(checkNull(String.valueOf(Data[0][1])).trim());
				bean.setTypeId(checkNull(String.valueOf(Data[0][3])).trim());
				bean.setHotelName(checkNull(String.valueOf(Data[0][4])).trim());
				bean.setHotelId(checkNull(String.valueOf(Data[0][5])).trim());

			}
			return result;
		} else {
			return false;
		}

	}

	/**
	 * following function is called to update the record.
	 * 
	 * @param bean
	 * @return
	 */

	public boolean modRoomType(RoomType bean) {
		if (!checkDuplicateMod(bean)) {

			Object mod[][] = new Object[1][5];

			mod[0][0] = bean.getTypeName().trim();
			mod[0][1] = bean.getDesciption();
			mod[0][2] = bean.getStatus();
			mod[0][3] = bean.getHotelId();
			mod[0][4] = bean.getTypeId();

			boolean res = getSqlModel().singleExecute(getQuery(2), mod);

			if (res) {
				String query1 = " SELECT ROOM_TYPE_NAME,NVL(ROOM_TYPE_DESC,''),CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' WHEN ROOM_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,ROOM_TYPE_ID "
						+ " ,HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME,ROOM_HOTEL_TYPE FROM HRMS_TMS_ROOM_TYPE"
						+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
						+ " WHERE ROOM_TYPE_ID=" + bean.getTypeId();

				Object[][] Data = getSqlModel().getSingleResult(query1);

				bean.setTypeName(checkNull(String.valueOf(Data[0][0])).trim());
				logger.info("data[0][0]--->" + Data[0][0]);
				bean
						.setDesciption(checkNull(String.valueOf(Data[0][1]))
								.trim());
				bean.setStatus(checkNull(String.valueOf(Data[0][2])).trim());
				bean.setTypeId(checkNull(String.valueOf(Data[0][3])).trim());
				bean.setHotelName(checkNull(String.valueOf(Data[0][4])).trim());
				bean.setHotelId(checkNull(String.valueOf(Data[0][5])).trim());
			}
			return res;
		} else {
			return false;
		}

	}

	/**
	 * following function is called to delete a record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteRoomType(RoomType bean) {
		Object del[][] = new Object[1][1];
		del[0][0] = bean.getTypeId();
		return getSqlModel().singleExecute(getQuery(3), del);
	}

	public void getRoomTypeOnDoubleClick(RoomType bean) {
		try {

			String query = " SELECT NVL(ROOM_TYPE_NAME,' '),ROOM_TYPE_STATUS,NVL(ROOM_TYPE_DESC,''),ROOM_TYPE_ID "
					+ " ,HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME,ROOM_HOTEL_TYPE"
					+ " FROM HRMS_TMS_ROOM_TYPE"
					+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
					+ " WHERE ROOM_TYPE_ID=" + bean.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setTypeName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setStatus(checkNull(String.valueOf(data[0][1])));
				bean
						.setDesciption(checkNull(String.valueOf(data[0][2])
								.trim()));
				bean.setTypeId(checkNull(String.valueOf(data[0][3])));
				bean.setHotelName(checkNull(String.valueOf(data[0][4])));
				bean.setHotelId(checkNull(String.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean delChkdRec(RoomType bean, String[] code) {

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

	/*
	 * public void reqData(RoomType bean,HttpServletRequest request){ try{
	 * 
	 * String query = " SELECT NVL(ROOM_TYPE_NAME,' '),CASE WHEN
	 * ROOM_TYPE_STATUS='A' THEN 'Active' ELSE 'Deactive'
	 * END,NVL(ROOM_TYPE_DESC,' '),ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE" +"
	 * ORDER BY ROOM_LEVEL";
	 * 
	 * Object[][] data = getSqlModel().getSingleResult(query); int REC_TOTAL =
	 * 0; int To_TOT = 20; int From_TOT = 0; int pg1=0; int
	 * PageNo1=1;//---------- REC_TOTAL = data.length; int
	 * no_of_pages=Math.round(REC_TOTAL/20); double row =
	 * (double)data.length/20.0;
	 * 
	 * java.math.BigDecimal value1 = new java.math.BigDecimal(row);
	 * 
	 * int rowCount1
	 * =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
	 * 
	 * 
	 * ArrayList<Object> obj=new ArrayList<Object>(); System.out.println("val
	 * of riwC"+rowCount1); request.setAttribute("abc", rowCount1);
	 * 
	 * //PageNo
	 * if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals("
	 * ")) { PageNo1=1; From_TOT=0; To_TOT=20;
	 * 
	 * if(To_TOT >data.length){ To_TOT=data.length; }
	 * 
	 * bean.setMyPage("1"); }
	 * 
	 * 
	 * else{
	 * 
	 * pg1= Integer.parseInt(bean.getMyPage()); PageNo1=pg1;
	 * 
	 * if(pg1 ==1){ From_TOT=0; To_TOT=20; } else{ // From_TOTAL=To_TOTAL+1;
	 * To_TOT=To_TOT*pg1; From_TOT=(To_TOT-20); } if(To_TOT >data.length){
	 * To_TOT=data.length; } } request.setAttribute("xyz", PageNo1);
	 * 
	 * for(int i=From_TOT;i<To_TOT;i++){ //setting RoomType bean1 = new
	 * RoomType();
	 * 
	 * bean1.setTypeName(String.valueOf(data[i][0]).trim());
	 * bean1.setStatus(String.valueOf(data[i][1]));
	 * bean1.setTypeId(String.valueOf(data[i][3])); obj.add(bean1); }
	 * 
	 * 
	 * 
	 * bean.setTypeList(obj); bean.setModeLength(String.valueOf(data.length));
	 * }catch(Exception e){ e.printStackTrace(); }
	 *  }
	 */

	public void reqData(RoomType bean, HttpServletRequest request) {
		try {
			String query = " SELECT NVL(UPPER(ROOM_TYPE_NAME),' '),CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' WHEN ROOM_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,NVL(ROOM_TYPE_DESC,''),ROOM_TYPE_ID,ROOM_LEVEL,"
					+ " NVL(ROOM_HOTEL_TYPE,''),NVL(UPPER(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME),' ')"
					+ " FROM HRMS_TMS_ROOM_TYPE"
					+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
					+ " ORDER BY HOTEL_LEVEL,ROOM_LEVEL";

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 20) {
				String firstRecord = "";
				String lastRecord = "";
				int numOfPage = data.length / 20;//2
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
				System.out.println("firstRecord=================== "
						+ firstRecord);
				System.out.println("numOfPage=================== " + numOfPage);
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
			}

			else {

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
				RoomType bean1 = new RoomType();
				bean1.setTypeName(String.valueOf(data[i][0]).trim());
				bean1.setStatus(String.valueOf(data[i][1]));
				bean1.setTypeId(String.valueOf(data[i][3]));
				bean1.setItLevel(String.valueOf(data[i][4]));
				bean1.setItHotelId(checkNull(String.valueOf(data[i][5])));
				bean1.setItHotelName(checkNull(String.valueOf(data[i][6])));
				obj.add(bean1);
			}

			bean.setTypeList(obj);

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

	public void getDesc(RoomType rmType) {
		// TODO Auto-generated method stub
		String query = "SELECT NVL(ROOM_TYPE_DESC,'') FROM HRMS_TMS_ROOM_TYPE ORDER BY ROOM_TYPE_ID";
		Object[][] data = getSqlModel().getSingleResult(query);
		rmType.setDesciption(String.valueOf(data[0][0]));

	}

	public String upData(RoomType bean) {

		String code = bean.getUpId();
		String previousId = "";
		String currentId = "";
		System.out.println(" bean.getPreviousLevel()-------------------------"
				+ bean.getPreviousLevel());

		String preSql = "SELECT ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_LEVEL ="
				+ bean.getPreviousLevel();
		Object[][] prevousJourData = getSqlModel().getSingleResult(preSql);
		if (prevousJourData != null && prevousJourData.length > 0) {
			previousId = "" + prevousJourData[0][0];
		}

		String currentSql = "SELECT ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_LEVEL ="
				+ bean.getCurrentLevel();
		Object[][] currentJourData = getSqlModel().getSingleResult(currentSql);
		if (currentJourData != null && currentJourData.length > 0) {
			currentId = "" + currentJourData[0][0];
		}
		/*
		 * added by debjani
		 */

		String getQuery = "SELECT ROOM_TYPE_ID,ROOM_LEVEL FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_HOTEL_TYPE IN(SELECT ROOM_HOTEL_TYPE FROM HRMS_TMS_ROOM_TYPE  WHERE ROOM_LEVEL="
				+ bean.getCurrentLevel() + ")" + " ORDER BY ROOM_LEVEL ASC ";

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

			return "This is Last level ,so can not move";

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

			String sql = "UPDATE HRMS_TMS_ROOM_TYPE SET ROOM_LEVEL ="
					+ bean.getPreviousLevel() + " WHERE ROOM_TYPE_ID="
					+ currentId;
			getSqlModel().singleExecute(sql);

			String sqlUp = "UPDATE HRMS_TMS_ROOM_TYPE SET ROOM_LEVEL ="
					+ bean.getCurrentLevel() + " WHERE  ROOM_TYPE_ID ="
					+ previousId;
			getSqlModel().singleExecute(sqlUp);

		}

		return "";

		/*
		 * 
		 */

		/*
		 * by debjani String sql ="UPDATE HRMS_TMS_ROOM_TYPE SET ROOM_LEVEL
		 * ="+bean.getPreviousLevel()+" WHERE ROOM_TYPE_ID="+currentId;
		 * getSqlModel().singleExecute(sql);
		 * 
		 * String sqlUp ="UPDATE HRMS_TMS_ROOM_TYPE SET ROOM_LEVEL
		 * ="+bean.getCurrentLevel()+" WHERE ROOM_TYPE_ID ="+previousId;
		 * getSqlModel().singleExecute(sqlUp);
		 */

		/*
		 * String code = bean.getUpId(); System.out.println("code=============
		 * "+code); String idSql ="SELECT ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE
		 * WHERE ROOM_LEVEL ="+code; Object[][] jourData=
		 * getSqlModel().getSingleResult(idSql);
		 * 
		 * int upId= 0; if(bean.getStatusUp().equals("true")) { upId=
		 * (Integer.parseInt(code)-1); }
		 * 
		 * if(bean.getStatusDown().equals("true")) { upId=
		 * (Integer.parseInt(code)+1); } String sql ="UPDATE HRMS_TMS_ROOM_TYPE
		 * SET ROOM_LEVEL ="+upId+" WHERE ROOM_LEVEL="+code;
		 * getSqlModel().singleExecute(sql);
		 * 
		 * String sqlUp ="UPDATE HRMS_TMS_ROOM_TYPE SET ROOM_LEVEL ="+code+"
		 * WHERE ROOM_LEVEL="+upId+" AND ROOM_TYPE_ID !="+jourData[0][0];
		 * getSqlModel().singleExecute(sqlUp);
		 */

	}

	/*
	 * public boolean addType(RoomType bean) { // TODO Auto-generated method
	 * stub Object addObj[][] = new Object[1][3]; addObj[0][0] =
	 * bean.getTypeName().trim(); addObj[0][1] = bean.getDesciption();
	 * addObj[0][2] = bean.getStatus(); return
	 * getSqlModel().singleExecute(getQuery(1), addObj);
	 *  }
	 * 
	 * public boolean modType(RoomType bean) { // TODO Auto-generated method
	 * stub Object addObj[][] = new Object[1][4]; addObj[0][0] =
	 * bean.getTypeName().trim(); addObj[0][1] = bean.getDesciption();
	 * addObj[0][2] = bean.getStatus(); addObj[0][3] = bean.getTypeId(); return
	 * getSqlModel().singleExecute(getQuery(2), addObj);
	 *  }
	 */

	/* generating the list onload */

	/*
	 * public void reqData(RoomType rmType, HttpServletRequest request) { String
	 * Query=" SELECT
	 * ROOM_TYPE_ID,SUBSTR(ROOM_TYPE_NAME,0,40),DECODE(ROOM_TYPE_STATUS,'A','Active','D','Deactive')
	 * FROM HRMS_TMS_ROOM_TYPE ORDER BY ROOM_TYPE_ID"; Object[][] repData =
	 * getSqlModel().getSingleResult(Query); int REC_TOTAL = 0; int To_TOT = 20;
	 * int From_TOT = 0; int pg1 = 0; int PageNo1 = 1;//---------- REC_TOTAL =
	 * repData.length; int no_of_pages = Math.round(REC_TOTAL / 20); //PageNo =
	 * Integer.parseInt(locationMaster.getMyPage());//----------- double row =
	 * (double) repData.length / 20.0;
	 * 
	 * java.math.BigDecimal value1 = new java.math.BigDecimal(row);
	 * 
	 * int rowCount1 = Integer.parseInt(value1.setScale(0,
	 * java.math.BigDecimal.ROUND_UP).toString());
	 * 
	 * 
	 * ArrayList<Object> obj = new ArrayList<Object>();
	 * request.setAttribute("abc", rowCount1);
	 * 
	 * Generating PageNo(20 each page) if
	 * (String.valueOf(rmType.getMyPage()).equals("null") ||
	 * String.valueOf(rmType.getMyPage()).equals(null) ||
	 * String.valueOf(rmType.getMyPage()).equals(" ")) { PageNo1 = 1; From_TOT =
	 * 0; To_TOT = 20;
	 * 
	 * if (To_TOT > repData.length) { To_TOT = repData.length; }//end of if
	 * logger.info("-----------To_TOTAL----null-----" + To_TOT);
	 * rmType.setMyPage("1"); }//end of nested if
	 * 
	 * else {
	 * 
	 * pg1 = Integer.parseInt(rmType.getMyPage()); PageNo1 = pg1;
	 * 
	 * if (pg1 == 1) { From_TOT = 0; To_TOT = 20; }//end of if else { //
	 * From_TOTAL=To_TOTAL+1; To_TOT = To_TOT * pg1; From_TOT = (To_TOT - 20);
	 * }//end of else if (To_TOT > repData.length) { To_TOT = repData.length;
	 * }//end of if }//end of else request.setAttribute("xyz", PageNo1);
	 * logger.info("------------from total--------" + From_TOT);
	 * logger.info("----------to total----------" + To_TOT); for (int i =
	 * From_TOT; i < To_TOT; i++) { RoomType bean1 = new RoomType();
	 * bean1.setTypeId(String.valueOf(repData[i][0]));
	 * bean1.setTypeName(String.valueOf(repData[i][1]));
	 * 
	 * bean1.setStatus(String.valueOf(repData[i][2])); obj.add(bean1); }//end of
	 * loop
	 * 
	 * rmType.setTypeList(obj);
	 *  }
	 */

	/* for selecting data from list */

	/* for deleting the record after selecting from window or list */

	/*
	 * public boolean deleteType(RoomType bean) { Object addObj[][] = new
	 * Object[1][1];
	 * 
	 * addObj[0][0] = bean.getTypeId(); String query=" DELETE FROM
	 * HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_ID=?"; return
	 * getSqlModel().singleExecute(query, addObj);
	 *  } public void getTypeRecord(RoomType rmType) { Object addObj[] = new
	 * Object[1];
	 * 
	 * addObj[0] = rmType.getTypeId(); String query1=" SELECT
	 * ROOM_TYPE_ID,nvl(ROOM_TYPE_NAME,'') FROM HRMS_TMS_ROOM_TYPE WHERE
	 * ROOM_TYPE_ID=?"; Object[][] data = getSqlModel().getSingleResult(query1,
	 * addObj);
	 * 
	 * for (int i = 0; i < data.length; i++) { logger.info("I am 2");
	 * rmType.setTypeId(String.valueOf(data[i][0]));
	 * rmType.setTypeName(String.valueOf(data[i][1])); if
	 * (String.valueOf(data[i][2]).equals("null")) rmType.setTrvHighCode("");
	 * else rmType.setTrvHighCode(String.valueOf(data[i][2]));
	 * 
	 * }//end of loop
	 *  }
	 */

	/* for deleting one or more records from list */

	/*
	 * public boolean deleteType(RoomType rmType , String[] code) { boolean
	 * result = false; boolean flag = false; int cnt = 0; if (code != null) {
	 * for (int i = 0; i < code.length; i++) { if (!code[i].equals("")) { logger
	 * .info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->" +
	 * code[i]); Object[][] delete = new Object[1][1]; delete[0][0] = code[i];
	 * String query=" DELETE FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_ID=?"; flag =
	 * getSqlModel().singleExecute(query, delete); if (!flag) { cnt++; }//end of
	 * if //result=true; }//end of if }//end of loop }//end of nested if if (cnt >
	 * 0) { result = false; }//end of if else result = true; return result;
	 *  } public void calforedit(RoomType rmType) { logger.info(" enter to call
	 * for edit method");
	 * 
	 * String query = "SELECT
	 * ROOM_TYPE_ID,ROOM_TYPE_NAME,ROOM_TYPE_DESC,ROOM_TYPE_STATUS FROM
	 * HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_ID='"+rmType.getHiddencode()+"' "
	 *  +" ORDER BY ROOM_TYPE_ID ";
	 * 
	 * Object[][] data = getSqlModel().getSingleResult(query);
	 * rmType.setTypeId(String.valueOf(data[0][0]));
	 * rmType.setTypeName(String.valueOf(data[0][1]));
	 * rmType.setDesciption(checkNull(String.valueOf(data[0][2])));
	 * rmType.setStatus(String.valueOf(data[0][3]));
	 *  }
	 */

}
