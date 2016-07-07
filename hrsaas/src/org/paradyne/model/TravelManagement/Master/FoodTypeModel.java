/**
 * 
 */
package org.paradyne.model.TravelManagement.Master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.FoodType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author aa0651
 * 
 */
public class FoodTypeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void getFoodType(FoodType bean) {
		// TODO Auto-generated method stub
		try {

			logger.info("bean.getDesignationCode--->" + bean.getTypeId());

			String query = " SELECT FOOD_TYPE_NAME,NVL(FOOD_TYPE_DESC,''),CASE WHEN FOOD_TYPE_STATUS='A' THEN 'Active' WHEN FOOD_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,FOOD_TYPE_ID  FROM HRMS_TMS_FOOD_TYPE  "
					+ " WHERE FOOD_TYPE_ID=" + bean.getTypeId();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setTypeName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setDescription(checkNull(String.valueOf(data[0][1])));
				bean.setStatus(checkNull(String.valueOf(data[0][2])));
				bean.setTypeId(checkNull(String.valueOf(data[0][3])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getFoodTypeSearch(FoodType bean) {
		// TODO Auto-generated method stub
		try {

			logger.info("bean.getDesignationCode--->" + bean.getTypeId());

			String query = " SELECT FOOD_TYPE_NAME,NVL(FOOD_TYPE_DESC,''), FOOD_TYPE_STATUS,FOOD_TYPE_ID  FROM HRMS_TMS_FOOD_TYPE  "
					+ " WHERE FOOD_TYPE_ID=" + bean.getTypeId();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setTypeName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setDescription(checkNull(String.valueOf(data[0][1])));
				bean.setStatus(checkNull(String.valueOf(data[0][2])));
				bean.setTypeId(checkNull(String.valueOf(data[0][3])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean addFoodType(FoodType bean) {
		// TODO Auto-generated method stub
		if (!checkDuplicate(bean)) {
			Object[][] add = new Object[1][3];
			try {
				add[0][0] = bean.getTypeName().trim();
				add[0][1] = bean.getDescription();
				add[0][2] = bean.getStatus();
			} catch (Exception e) {
				e.printStackTrace();
			}
			boolean result = getSqlModel().singleExecute(getQuery(1), add);

			if (result) {

				String query = " SELECT MAX(FOOD_TYPE_ID) FROM HRMS_TMS_FOOD_TYPE";

				Object[][] data = getSqlModel().getSingleResult(query);

				System.out.println("data[0][0]---->" + data[0][0]);

				String query1 = " SELECT FOOD_TYPE_NAME	,CASE WHEN FOOD_TYPE_STATUS	='A' THEN 'Active' WHEN FOOD_TYPE_STATUS='D' THEN 'Deactive'  ELSE ' ' END,NVL(FOOD_TYPE_DESC,''),FOOD_TYPE_ID FROM "
						+ " HRMS_TMS_FOOD_TYPE WHERE FOOD_TYPE_ID="
						+ String.valueOf(data[0][0]);

				logger.info("query1 in addDesignation---->" + query1);

				Object[][] Data = getSqlModel().getSingleResult(query1);

				bean.setTypeName(checkNull(String.valueOf(Data[0][0])).trim());
				bean.setDescription(checkNull(String.valueOf(Data[0][2]))
						.trim());
				bean.setStatus(checkNull(String.valueOf(Data[0][1])).trim());
				bean.setTypeId(checkNull(String.valueOf(Data[0][3])).trim());

			}
			return result;
		} else {
			return false;
		}
	}

	public boolean modFoodType(FoodType bean) {
		// TODO Auto-generated method stub
		if (!checkDuplicateMod(bean)) {

			Object mod[][] = new Object[1][4];

			mod[0][0] = bean.getTypeName().trim();
			mod[0][1] = bean.getDescription();
			mod[0][2] = bean.getStatus();
			mod[0][3] = bean.getTypeId();

			boolean res = getSqlModel().singleExecute(getQuery(2), mod);

			if (res) {
				String query1 = " SELECT FOOD_TYPE_NAME,NVL(FOOD_TYPE_DESC,''),CASE WHEN FOOD_TYPE_STATUS='A' THEN 'Active' WHEN FOOD_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,FOOD_TYPE_ID  FROM HRMS_TMS_FOOD_TYPE"
						+ " WHERE FOOD_TYPE_ID=" + bean.getTypeId();

				Object[][] Data = getSqlModel().getSingleResult(query1);

				bean.setTypeName(checkNull(String.valueOf(Data[0][0])).trim());
				logger.info("data[0][0]--->" + Data[0][0]);
				bean.setDescription(checkNull(String.valueOf(Data[0][1]))
						.trim());
				bean.setStatus(checkNull(String.valueOf(Data[0][2])).trim());
				bean.setTypeId(checkNull(String.valueOf(Data[0][3])).trim());
			}
			return res;
		} else {
			return false;
		}
	}

	public void reqData(FoodType bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {

			String query = " SELECT NVL(UPPER(FOOD_TYPE_NAME),' '),CASE WHEN FOOD_TYPE_STATUS='A' THEN 'Active' WHEN FOOD_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,NVL(FOOD_TYPE_DESC,''),FOOD_TYPE_ID  FROM HRMS_TMS_FOOD_TYPE"
					+ " ORDER BY UPPER(FOOD_TYPE_NAME),FOOD_TYPE_STATUS";

			Object[][] data = getSqlModel().getSingleResult(query);
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

			for (int i = From_TOT; i < To_TOT; i++) {
				// setting
				FoodType bean1 = new FoodType();

				bean1.setTypeName(String.valueOf(data[i][0]).trim());
				bean1.setStatus(String.valueOf(data[i][1]));
				bean1.setTypeId(String.valueOf(data[i][3]));
				obj.add(bean1);
			}

			bean.setTypeList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean deleteFoodType(FoodType bean) {
		// TODO Auto-generated method stub
		// System.out.println("SINGLE DELETE>>>>>>>>>");

		// Food type data from TMS_APP_TOUR_DTL tables of TMS
		Object tourFoodData[][] = getSqlModel().getSingleResult(getQuery(4));
		boolean exists = false;
		xx: if (tourFoodData != null && tourFoodData.length > 0) {
			for (int i = 0; i < tourFoodData.length; i++) {
				String foodSplit[] = String.valueOf(tourFoodData[i][0]).split(
						",");
				if (foodSplit != null && foodSplit.length > 0) {
					for (int j = 0; j < foodSplit.length; j++) {
						if (foodSplit[j].equals(bean.getTypeId())) {
							exists = true;
							break xx;
						}
					}
				}// if ends
			}// for ends
		}// if ends

		if (!exists) {
			Object del[][] = new Object[1][1];
			del[0][0] = bean.getTypeId();
			return getSqlModel().singleExecute(getQuery(3), del);
		}
		return false;
	}

	public void getFoodTypeOnDoubleClick(FoodType bean) {
		// TODO Auto-generated method stub
		try {

			String query = " SELECT NVL(FOOD_TYPE_NAME,' '),FOOD_TYPE_STATUS,NVL(FOOD_TYPE_DESC,''),FOOD_TYPE_ID  FROM HRMS_TMS_FOOD_TYPE"
					+ " WHERE FOOD_TYPE_ID=" + bean.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setTypeName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setStatus(checkNull(String.valueOf(data[0][1])));
				bean
						.setDescription(checkNull(String.valueOf(data[0][2])
								.trim()));
				bean.setTypeId(checkNull(String.valueOf(data[0][3])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean delChkdRec(FoodType food, String[] code) {
		// TODO Auto-generated method stub

		System.out.println("MULTI DELETE>>>>>>>>>" + code.length);
		int count = 0;
		boolean result = false;

		int deleteCount = 0;

		for (int l = 0; l < code.length; l++) {
			if (!code[l].equals("")) {
				deleteCount++;// NO OF FOOD TYPE RECORDS SELECTED
			}
		}

		for (int i = 0; i < code.length; i++) {

			// Food type data from TMS_APP_TOUR_DTL tables of TMS
			Object tourFoodData[][] = getSqlModel()
					.getSingleResult(getQuery(4));
			boolean exists = false;

			xx: if (tourFoodData != null && tourFoodData.length > 0) {
				for (int j = 0; j < tourFoodData.length; j++) {
					String foodSplit[] = String.valueOf(tourFoodData[j][0])
							.split(",");
					if (foodSplit != null && foodSplit.length > 0) {
						for (int k = 0; k < foodSplit.length; k++) {
							if (!code[i].equals("")
									&& foodSplit[k].equals(code[i])) {
								exists = true;
								break xx;
							}
						}
					} // if ends
				} // for ends
			} // if ends

			if (!exists && !code[i].equals("")) {

				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				result = getSqlModel().singleExecute(getQuery(3), delete);
				/*
				 * if(!result){ count++; }
				 */
				count++;
			}

		} // for ends

		if (count < deleteCount) {
			// result=false;
			return false;
		} else {
			// result=true;
			return true;
		}
	}

	public void generateReport(FoodType food, HttpServletResponse response,
			String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "Food Type";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);

		String query = "SELECT  FOOD_TYPE_NAME,NVL(FOOD_TYPE_DESC,''),CASE WHEN FOOD_TYPE_STATUS='A' THEN 'Active'  WHEN FOOD_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END FROM HRMS_TMS_FOOD_TYPE ORDER BY FOOD_TYPE_NAME,FOOD_TYPE_DESC,FOOD_TYPE_STATUS ";
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

			// String abc[] = { "Sr No", "Food Type
			// Name","Description","Status"};
			int cellwidth[] = { 5, 20, 20, 20 };
			int alignment[] = { 0, 0, 0, 0 };
			rg.addTextBold("Food Type", 0, 1, 0);
			rg.addText("\n\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n\n", 0, 0, 0);
			rg.tableBody(label, finalData, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

	public boolean checkDuplicate(FoodType bean) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_TMS_FOOD_TYPE WHERE UPPER(FOOD_TYPE_NAME) LIKE '"
				+ bean.getTypeName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}

	public boolean checkDuplicateMod(FoodType bean) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_TMS_FOOD_TYPE WHERE UPPER(FOOD_TYPE_NAME) LIKE '"
				+ bean.getTypeName().trim().toUpperCase()
				+ "' AND FOOD_TYPE_ID not in(" + bean.getTypeId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getDesc(FoodType food) {
		// TODO Auto-generated method stub
		String query = "SELECT NVL(FOOD_TYPE_DESC,'') FROM HRMS_TMS_FOOD_TYPE ORDER BY  FOOD_TYPE_ID";
		Object[][] data = getSqlModel().getSingleResult(query);
		food.setDescription(String.valueOf(data[0][0]));

	}

}
