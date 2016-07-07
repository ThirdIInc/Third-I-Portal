package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.Tdsperquisites;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;

import com.ibm.icu.util.Calendar;

public class TdsperquisitesModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TdsperquisitesModel.class);

	public boolean addPerquisite(Tdsperquisites bean) {
		Object addObj[][] = new Object[1][6];
		boolean result = false;
		// logger.info("model ----------------1");
		addObj[0][0] = bean.getTdsperquisitesname().trim();
		addObj[0][1] = bean.getPerquisitesAbbr().trim();
		// logger.info("model ----------------2");
		addObj[0][2] = bean.getVariance();
		addObj[0][3] = bean.getTaxable();
	 	addObj[0][4] = bean.getCreditexempt().trim();
		addObj[0][5] = bean.getTaxCode().trim();
		
		
		result = getSqlModel().singleExecute(getQuery(1), addObj);
		// logger.info("result in addperquisite --- model "+result);
		if (result) {/*
			*//**
			 * Following code calculates the tax and updates tax process
			 *//*
			try {
				CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
				taxmodel.initiate(context, session);
				// logger.info("I m calling tax calculation method");
				Object empList[][] = getSqlModel()
						.getSingleResult(
								"SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'");
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int fromYear = Integer.parseInt(String.valueOf(cal
						.get(Calendar.YEAR)));
				int month = Integer.parseInt(String.valueOf(cal
						.get(Calendar.MONTH)));
				if (month <= 3)
					fromYear--;
				if (empList != null && empList.length > 0)
					taxmodel.calculateTax(empList, String.valueOf(fromYear),
							String.valueOf(fromYear + 1));
				taxmodel.terminate();
			} catch (Exception e) {
				logger
						.error("Exception in modPerquisites() of TdsperquisitesModel  while calling calculateTax : "
								+ e);
				e.printStackTrace();
			}
		*/}
		return result;
	}

	public boolean modPerquisite(Tdsperquisites bean) {
		Object addObj[][] = new Object[1][7];
		boolean result = false;
		addObj[0][0] = bean.getTdsperquisitesname().trim();
		addObj[0][1] = bean.getPerquisitesAbbr().trim();
		addObj[0][2] = bean.getVariance();
		addObj[0][3] = bean.getTaxable();
		addObj[0][4] = bean.getCreditexempt().trim();
		addObj[0][5] = bean.getTaxCode().trim();
		
		addObj[0][6] = bean.getTdsperquisitescode();
		result = getSqlModel().singleExecute(getQuery(2), addObj);
		if (result) {/*
			*//**
			 * Following code calculates the tax and updates tax process
			 *//*
			try {
				CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
				taxmodel.initiate(context, session);
				// logger.info("I m calling tax calculation method");
				Object empList[][] = getSqlModel()
						.getSingleResult(
								"SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'");
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int fromYear = Integer.parseInt(String.valueOf(cal
						.get(Calendar.YEAR)));
				int month = Integer.parseInt(String.valueOf(cal
						.get(Calendar.MONTH)));
				if (month <= 3)
					fromYear--;
				if (empList != null && empList.length > 0)
					taxmodel.calculateTax(empList, String.valueOf(fromYear),
							String.valueOf(fromYear + 1));
				taxmodel.terminate();
			} catch (Exception e) {
				logger
						.error("Exception in modPerquisites() of TdsperquisitesModel  while calling calculateTax : "
								+ e);
				e.printStackTrace();
			}
		*/}
		return result;

	}

	public boolean deletePerquisite(Tdsperquisites bean) {
		Object addObj[][]=null;

		try {
			addObj = new Object[1][1];
			addObj[0][0] = bean.getTdsperquisitescode();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getSqlModel().singleExecute(getQuery(3), addObj);

	}

	public void getReport(Tdsperquisites bean, HttpServletRequest request,
			HttpServletResponse response) {
		String Query = "SELECT rownum,NVL(PERQ_NAME,' '),NVL(PERQ_ABBR,' '),DECODE(PERQ_PERIOD,'Q','Quarterly','M','Monthly','A','Annually','H','Half Yearly'),decode(PERQ_TAXABLE_FLAG,'Y','YES','N','NO') FROM HRMS_PERQUISITE_HEAD ORDER BY PERQ_CODE";

		String title = "TDS PERQUISITES\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				title);

		rg.addFormatedText(title, 6, 0, 1, 0);
		String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";

		try {
			Object date[][] = getSqlModel().getSingleResult(dateQuery);

			Object result[][] = getSqlModel().getSingleResult(Query);

			rg.setFName("TDS PERQUISITES");
			rg.addFormatedText("Date :" + String.valueOf(date[0][0]), 0, 0, 2,
					0);
			rg.addText("\n\n", 1, 0, 0);
			if (result != null && result.length > 0) {
				System.out.println("in side if block...!!");
				String appCol[] = { "Sr.No", "Perquisite Name",
						"Perquisite Abbreviation", "Perquisite Period",
						"Perquisites taxable" };
				int appCell[] = { 10, 30, 20, 20, 10 };
				int apprAlign[] = { 1, 0, 0, 0, 1 };
				for (int i = 0; i < result.length; i++) {
					result[i][0] = String.valueOf(i + 1);

				}

				rg.tableBody(appCol, result, appCell, apprAlign);

			} else {
				rg.addFormatedText("No Records available", 1, 1, 1, 0);

			}
		} catch (Exception e) {
			System.out.println("catch block u r...!");
			// e.printStackTrace();
		}

		rg.createReport(response);

	}

	public void Data(Tdsperquisites bean, HttpServletRequest request) {

		Object[][] obj = getSqlModel().getSingleResult(getQuery(4));

		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));

		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");

		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				Tdsperquisites bean1 = new Tdsperquisites();

				bean1.setTdsperquisitescode(String.valueOf(obj[i][0]));
				bean1.setTdsperquisitesname(String.valueOf(obj[i][1]));
				bean1.setPerquisitesAbbr(String.valueOf(obj[i][2]));
				bean1.setVariance(checkNull((String.valueOf(obj[i][3]))));
				bean1.setTaxable(checkNull((String.valueOf(obj[i][4]))));
				list.add(bean1);
			}

			bean.setTotalRecords("" + obj.length);
		} else {
			bean.setListLength("false");
		}

		if (list.size() > 0)
			bean.setListLength("true");
		else
			bean.setListLength("false");

		bean.setIteratorlist(list);

	}

	public void calforedit(Tdsperquisites bean) {

		try {
			String query = "SELECT PERQ_CODE,NVL(PERQ_NAME,' '),NVL(PERQ_ABBR,' '),PERQ_PERIOD,PERQ_TAXABLE_FLAG,PERQ_IS_EXEMPTED, "
					+ " PERQ_EXEMPT_UNDER_SECTION,HRMS_TAX_INVESTMENT.INV_NAME FROM HRMS_PERQUISITE_HEAD "
					+ " LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_PERQUISITE_HEAD.PERQ_EXEMPT_UNDER_SECTION=HRMS_TAX_INVESTMENT.INV_CODE) "
					+ "WHERE PERQ_CODE= " + bean.getHiddencode();
			Object[][] repData = getSqlModel().getSingleResult(query);
			bean.setTdsperquisitescode(String.valueOf(repData[0][0]));
			bean
					.setTdsperquisitesname(checkNull(String
							.valueOf(repData[0][1])));
			bean.setPerquisitesAbbr(checkNull(String.valueOf(repData[0][2])));
			bean.setVariance(checkNull((String.valueOf(repData[0][3]))));
			bean.setTaxable(checkNull((String.valueOf(repData[0][4]))));
			bean.setCreditexempt(checkNull((String.valueOf(repData[0][5]))));
			bean.setTaxCode(checkNull((String.valueOf(repData[0][6]))));
			bean.setExemptSectionNo(checkNull((String.valueOf(repData[0][7]))));
		} catch (Exception e) {
			// TODO: handle exception
		}
	 
	}

	public boolean deletecheckedRecords(Tdsperquisites bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
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
			return result;
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getRecord(Tdsperquisites bean) {
		try {
			// TODO Auto-generated method stub
			String query = " SELECT PERQ_CODE,NVL(PERQ_NAME,' '),NVL(PERQ_ABBR,' '),PERQ_PERIOD,PERQ_TAXABLE_FLAG,PERQ_IS_EXEMPTED,PERQ_EXEMPT_UNDER_SECTION,HRMS_TAX_INVESTMENT.INV_NAME "
					+ " FROM HRMS_PERQUISITE_HEAD "
					+ " LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_PERQUISITE_HEAD.PERQ_EXEMPT_UNDER_SECTION=HRMS_TAX_INVESTMENT.INV_CODE) "
					+ " WHERE PERQ_CODE= " + bean.getTdsperquisitescode();
			Object[][] repData = getSqlModel().getSingleResult(query);
			bean.setTdsperquisitescode(String.valueOf(repData[0][0]));
			bean
					.setTdsperquisitesname(checkNull(String
							.valueOf(repData[0][1])));
			bean.setPerquisitesAbbr(checkNull(String.valueOf(repData[0][2])));
			bean.setVariance(checkNull((String.valueOf(repData[0][3]))));
			bean.setTaxable(checkNull((String.valueOf(repData[0][4]))));
			bean.setCreditexempt(checkNull((String.valueOf(repData[0][5]))));
			bean.setTaxCode(checkNull((String.valueOf(repData[0][6]))));
			bean.setExemptSectionNo(checkNull((String.valueOf(repData[0][7]))));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
