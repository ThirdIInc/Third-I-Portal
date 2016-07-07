package org.paradyne.model.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.InterviewAssessMaster;
import org.paradyne.bean.TravelManagement.Master.CityGrade;
import org.paradyne.bean.TravelManagement.Master.HotelMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class CityGradeModel extends ModelBase {

/**For inserting record into DB */
	public boolean addData(CityGrade bean) {

		boolean result = false;
		Object addObj[][] = new Object[1][2];

		addObj[0][0] = bean.getGradeName();
		addObj[0][1] = bean.getGradeCities();
		

		result = getSqlModel().singleExecute(getQuery(1), addObj);

		if(result)
		{
			String autoCodeQuery = " SELECT NVL(MAX(GRADE_ID),0) FROM  HRMS_CITY_GRADE  ";
			
			Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
			System.out.println("City  addData  hidden value ---"+data);
			
			if(data!=null && data.length >0)
			{
				bean.setHiddencode(String.valueOf(data[0][0]));
				System.out.println("City  data hidden value ---"+bean.getHiddencode());
			}
		}

		return result;

	}// end of if
	
	
/** for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(CityGrade bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CITY_GRADE WHERE UPPER(CITY_GRADE_ID) LIKE '"
				+ bean.getGradeName().trim().toUpperCase()
				+ "' AND REC_ASSESS_CODE not in("
				+ bean.getGradeId()
				+ ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	
	
/** for modifing the record */
	public boolean update(CityGrade bean) {
		if (!checkDuplicateMod(bean)) {
			Object addObj[][] = new Object[1][3];
			
			addObj[0][0] = bean.getGradeName().trim();
			addObj[0][1] = bean.getGradeCities().trim();
			addObj[0][2] = bean.getHiddencode().trim();
			return getSqlModel().singleExecute(getQuery(3), addObj);
		}// end of if
		else
			return false;
	}
	
	
/** Generating the list Onload */
	public void intData(CityGrade cityGrade, HttpServletRequest request) {

		Object[][] repData = getSqlModel().getSingleResult(getQuery(2));

		if (repData != null && repData.length > 0) {
			cityGrade.setModeLength("true");

			cityGrade.setTotalNoOfRecords(String.valueOf(repData.length));

			String[] pageIndex = Utility.doPaging(cityGrade.getMyPage(),
					repData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				cityGrade.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				CityGrade bean = new CityGrade();
				bean.setGradeId(checkNull(String.valueOf(repData[i][0])));
				bean.setGradeName(checkNull(String.valueOf(repData[i][1])));
				bean.setGradeCities(checkNull(String.valueOf(repData[i][2])));
				

				List.add(bean);
			}// end of loop
System.out.println("in main page---"+List.size());
			cityGrade.setGradeList(List);
		}

		else {

			cityGrade.setGradeList(null);

		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
/**
 * following function is called to delete a record
 * 
 */
	public boolean delRecord(CityGrade cityGrade) {
		Object del[][] = new Object[1][1];
		
		del[0][0] = cityGrade.getHiddencode();
		return getSqlModel().singleExecute(getQuery(4), del);
	}

		
/**
 * for selecting the data from list and setting those data in respective
 * fields
 */
	public void calforedit(CityGrade cityGrade) {

		try {
			System.out.println("cityGrade.getHiddencode() in edit method nilesh---"+cityGrade.getHiddencode());
			String query = " SELECT GRADE_NAME,GRADE_CITIES FROM HRMS_CITY_GRADE WHERE  GRADE_ID= "
					+ cityGrade.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);
			cityGrade.setGradeName(String.valueOf(data[0][0]));
			cityGrade.setGradeCities(String.valueOf(data[0][1]));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
     public boolean deleteCheckedRecords(CityGrade bean,
			String[] code) {
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

				}// end of if
			}// end of for loop
		}// end of if
		if (count != 0) {
			result = false;
			return result;
		}// end of if
		else {
			result = true;
			return result;
		}// end of else		
	}
	
	
	
	
}