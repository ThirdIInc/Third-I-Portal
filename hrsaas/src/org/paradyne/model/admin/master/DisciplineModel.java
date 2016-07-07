/**
 * 
 */
package org.paradyne.model.admin.master;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.DisciplineMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;

/**
 * @author Muzaffar
 * 
 */
public class DisciplineModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	org.paradyne.bean.admin.master.DisciplineMaster dispMaster = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(DisciplineMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DISCIPLINE WHERE UPPER(DISCP_NAME) LIKE '"
				+ bean.getDisciplineName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(DisciplineMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DISCIPLINE WHERE UPPER(DISCP_NAME) LIKE '"
				+ bean.getDisciplineName().trim().toUpperCase()
				+ "' AND DISCP_ID not in(" + bean.getDisciplineID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for inserting the data */
	public boolean addDiscipline(DisciplineMaster bean) {
		if (!checkDuplicate(bean)) {
			Object addObj[][] = new Object[1][3];
			addObj[0][0] = bean.getDisciplineName().trim();
			addObj[0][1] = bean.getDisciplineDesc().trim();
			addObj[0][2] = bean.getDisciplineParID().trim();

			return getSqlModel().singleExecute(getQuery(1), addObj);
		}// end of if
		else {
			return false;
		}// end of else
	}

	/* for modifing the data */
	public boolean modDiscipline(DisciplineMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object addObj[][] = new Object[1][4];
			addObj[0][0] = bean.getDisciplineName().trim();
			addObj[0][1] = bean.getDisciplineDesc().trim();
			addObj[0][2] = bean.getDisciplineParID().trim();
			addObj[0][3] = bean.getDisciplineID().trim();

			return getSqlModel().singleExecute(getQuery(2), addObj);

		}// end of if
		else {
			return false;
		}// end of else
	}

	/* for deleting the record after selecting */
	public boolean deleteDiscipline(DisciplineMaster bean) {
		Object addObj[][] = new Object[1][1];

		addObj[0][0] = bean.getDisciplineID();
		return getSqlModel().singleExecute(getQuery(3), addObj);

	}

	public void getDisciplineReport(DisciplineMaster bean) {
		Object addObj[] = new Object[1];
		ArrayList<Object> dispList = new ArrayList();

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		for (int i = 0; i < data.length; i++) {
			DisciplineMaster bean1 = new DisciplineMaster();
			logger.info("I am 2");
			bean1.setDisciplineID(String.valueOf(data[i][0]));
			bean1.setDisciplineName(String.valueOf(data[i][1]));
			bean1.setDisciplineDesc(String.valueOf(data[i][2]));
			bean1.setDisciplineParID(String.valueOf(data[i][3]));
			dispList.add(bean1);
		}// end of loop
		bean.setDispArray(dispList);

	}

	public void getReport(DisciplineMaster dispMaster2,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context) {
		// TODO Auto-generated method stub
		CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\discipline.rpt ";
		cr.createReport(request, response, context, session, path, "");

	}

	/*
	 * public void getDisciplineRecord(DisciplineMaster bean) { Object addObj[]
	 * =new Object[1];
	 * 
	 * addObj[0] =bean.getDisciplineID(); Object[][] data =
	 * getSqlModel().getSingleResult(getQuery(4),addObj);
	 * 
	 * for(int i=0; i<data.length; i++) { logger.info("I am 2");
	 * bean.setDisciplineID(String.valueOf(data[i][0]));
	 * bean.setDisciplineName(String.valueOf(data[i][1]));
	 * bean.setDisciplineDesc(String.valueOf(data[i][2]));
	 * bean.setDisciplineAbbr(String.valueOf(data[i][3]));
	 * bean.setDisciplineParID(String.valueOf(data[i][3])); } }
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/* generating the list onload */
	public void dispData(DisciplineMaster dispMaster, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1 = 0;
		int PageNo1 = 1;// ----------
		REC_TOTAL = repData.length;
		int no_of_pages = Math.round(REC_TOTAL / 20);
		// PageNo = Integer.parseInt(locationMaster.getMyPage());//-----------
		double row = (double) repData.length / 20.0;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount1 = Integer.parseInt(value1.setScale(0,
				java.math.BigDecimal.ROUND_UP).toString());

		logger.info("--------------------" + rowCount1);
		logger.info("------- dispMaster.getMyPage()-------------"
				+ dispMaster.getMyPage());
		ArrayList<Object> obj = new ArrayList<Object>();
		request.setAttribute("abc", rowCount1);

		/* Generating PageNo(20 each page) */
		if (String.valueOf(dispMaster.getMyPage()).equals("null")
				|| String.valueOf(dispMaster.getMyPage()).equals(null)
				|| String.valueOf(dispMaster.getMyPage()).equals(" ")) {
			PageNo1 = 1;
			From_TOT = 0;
			To_TOT = 20;

			if (To_TOT > repData.length) {
				To_TOT = repData.length;
			}// end of if
			logger.info("-----------To_TOTAL----null-----" + To_TOT);
			dispMaster.setMyPage("1");
		}// end of nested if

		else {

			pg1 = Integer.parseInt(dispMaster.getMyPage());
			PageNo1 = pg1;

			if (pg1 == 1) {
				From_TOT = 0;
				To_TOT = 20;
			}// end of if
			else {
				// From_TOTAL=To_TOTAL+1;
				To_TOT = To_TOT * pg1;
				From_TOT = (To_TOT - 20);
			}// end of else
			if (To_TOT > repData.length) {
				To_TOT = repData.length;
			}// end of if
		}// end of else
		request.setAttribute("xyz", PageNo1);
		logger.info("------------from total--------" + From_TOT);
		logger.info("----------to total----------" + To_TOT);
		for (int i = From_TOT; i < To_TOT; i++) {
			DisciplineMaster bean1 = new DisciplineMaster();
			bean1.setDisciplineID(checkNull(String.valueOf(repData[i][0])));
			bean1.setDisciplineName(checkNull(String.valueOf(repData[i][1])));
			bean1.setDisciplineDesc(checkNull(String.valueOf(repData[i][2])));

			obj.add(bean1);
		}// end of loop

		dispMaster.setDispArray(obj);

	}

	/* for selecting the record from list by double clicking */
	public void calforedit(DisciplineMaster dispMaster) {

		String query = "SELECT HRMS_DISCIPLINE .DISCP_ID,NVL(HRMS_DISCIPLINE .DISCP_NAME,' '),NVL(D1.DISCP_NAME,' ') ,NVL(HRMS_DISCIPLINE .DISCP_PARENT_CODE,0),NVL(HRMS_DISCIPLINE .DISCP_DESC,' ') "
				+ "  FROM HRMS_DISCIPLINE "
				+ "  LEFT JOIN HRMS_DISCIPLINE  D1 ON (D1.DISCP_ID = HRMS_DISCIPLINE .DISCP_PARENT_CODE)"
				+ "  where  HRMS_DISCIPLINE.DISCP_ID = "
				+ dispMaster.getHiddencode()
				+ "   ORDER BY HRMS_DISCIPLINE .DISCP_ID";

		Object[][] data = getSqlModel().getSingleResult(query);
		dispMaster.setDisciplineID(String.valueOf(data[0][0]));
		dispMaster.setDisciplineName(String.valueOf(data[0][1]));
		dispMaster.setDisciplineParName(String.valueOf(data[0][2]));
		dispMaster.setDisciplineParID(String.valueOf(data[0][3]));
		dispMaster.setDisciplineDesc(String.valueOf(data[0][4]));

	}

	public boolean calfordelete(DisciplineMaster dispMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = dispMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/*
	 * public boolean deleteDescipline(DisciplineMaster dispMaster, String[]
	 * code) {
	 * 
	 * 
	 * boolean result=false; boolean flag=false; if(code !=null) { for (int i =
	 * 0; i < code.length; i++) {
	 * 
	 * if(!code[i].equals("")){ logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
	 * Object [][] delete = new Object [1][1]; delete [0][0] =code[i] ; flag
	 * =getSqlModel().singleExecute(getQuery(3), delete); if(flag) result=true; } } }
	 * return result; }
	 */
	/* for deleting one or more records from list */
	public String deletecheckedRecords(DisciplineMaster dispMaster,
			String[] code) {
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

				}// end of if
			}// end of loop

		}// end of nested if
		if (count != 0) {
			result = "false";
		}// end of if
		else
			result = "true";

		return result;
	}

}
