package org.paradyne.model.admin.master;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.admin.master.ProfTaxMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;

import com.ibm.icu.util.Calendar;

/*
 * @Modified by v.saipavan
 * 
 * 
 */

public class ProfTaxModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProfTaxModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	ProfTaxMaster taxSlab = null;

	public boolean addTaxSlab(ProfTaxMaster bean) {
		Object addObj[][] = new Object[1][3];

		logger.info("In add ********2:" + String.valueOf(bean.getPtCode()));
		addObj[0][0] = bean.getEffDate();
		addObj[0][1] = bean.getPtLocCode();
		addObj[0][2] = bean.getPtDebitCode();

		return getSqlModel().singleExecute(getQuery(1), addObj);

	}

	public String delTaxSlabRecord(ProfTaxMaster bean, String poolDir) {


		Object addObj[][] = new Object[1][1];
		boolean result;
		addObj[0][0] = bean.getPtCode();
		logger.info("ptcode=========" + addObj[0][0]);
		result = getSqlModel().singleExecute(getQuery(6), addObj);
		if (result) {

			result = getSqlModel().singleExecute(getQuery(4), addObj);
			xml_profTax(poolDir);
		}
		if (result)
			return "deleted";
		else
			return "not deleted";

	}

	public boolean updateEmpInv(ProfTaxMaster bean) {
		Object addObj[][] = new Object[1][3];

		addObj[0][0] = bean.getParaID();
		addObj[0][1] = bean.getFrmAmount();
		addObj[0][2] = bean.getToAmount();
		addObj[0][3] = bean.getFixedAmt();

		return getSqlModel().singleExecute(getQuery(2), addObj);

	}

	public void getRecord(ProfTaxMaster bean) {

		Object[] addObj = new Object[1];

		addObj[0] = bean.getPtCode();
		// addObj[2]=bean.getPtLocCode();
		// addObj[3]=bean.getPtDebitCode();

		logger.info("In add ********1:" + addObj[0]);
		/*
		 * if(addObj[0].equals("")) { return false; } else{ String que="SELECT
		 * PTAX_FROMAMT,PTAX_UPTOAMT,PTAX_FIXEDAMT,PTAX_VARAMT,DECODE(PTAX_VARMTH,'0','
		 * ','1','January','2','February','3','March','4','April'," +
		 * "'5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'),PTAX_DTLCODE
		 * FROM HRMS_PTAX_DTL WHERE PTAX_CODE="+addObj[0]+" ORDER BY
		 * PTAX_FROMAMT ";
		 */
		String que = "SELECT PTAX_FROMAMT,PTAX_UPTOAMT,PTAX_FIXEDAMT,NVL(PTAX_VARAMT,' '),NVL(PTAX_VARMTH,' ')"
				+ ",PTAX_DTLCODE,"
				+ "NVL(DECODE(PTAX_VARMTH,'0',' ','1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'),' ') "
				+ " FROM HRMS_PTAX_DTL WHERE PTAX_CODE="
				+ addObj[0]
				+ " ORDER BY PTAX_FROMAMT ";
		Object[][] data = getSqlModel().getSingleResult(que);

		logger.info("In add ********2:" + data.length);
		ArrayList<Object> taxList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			ProfTaxMaster bean1 = new ProfTaxMaster();

			// logger.info("********2-Inc Code==:"+String.valueOf(data[i][5]));

			// logger.info("length========="+data.length);

			bean1.setSrNo(String.valueOf(i + 1));
			if (String.valueOf(data[i][0]).equals("")
					|| String.valueOf(data[i][0]).equals("null"))
				bean1.setFamt("");
			else
				bean1.setFamt(String.valueOf(data[i][0]));
			if (String.valueOf(data[i][1]).equals("")
					|| String.valueOf(data[i][1]).equals("null"))
				bean1.setTamt("");
			else
				bean1.setTamt(String.valueOf(data[i][1]));

			if (String.valueOf(data[i][2]).equals("")
					|| String.valueOf(data[i][3]).equals("null"))
				bean1.setFdamt("");
			else
				bean1.setFdamt(String.valueOf(data[i][2]));

			/*
			 * if(String.valueOf(data[i][3]).equals("") ||
			 * String.valueOf(data[i][3]).equals("null")) bean1.setVamt(" ");
			 * else
			 */
			bean1.setVamt(String.valueOf(data[i][3]));

			if (String.valueOf(data[i][4]).equals("")
					|| String.valueOf(data[i][4]).equals("null"))
				bean1.setVmth(" ");
			else
				bean1.setVmth(String.valueOf(data[i][6]));
			bean1.setPtDtlCode(String.valueOf(data[i][5]));
			bean1.setDupVarmonth(String.valueOf(data[i][4]));

			// logger.info("********2-To Amount==:"+String.valueOf(data[i][2]));

			// logger.info("********2-Frm
			// Amount==:"+String.valueOf(data[i][1]));

			taxList.add(bean1);
			// logger.info("********3:"+taxList.listIterator());

		}
		// bean.setTableLength("0");
		bean.setTaxList(taxList);
		bean.setTableLength(String.valueOf(taxList.size()));
		// }

	}

	public void getEditRecord(ProfTaxMaster bean) {

		Object[] addObj = new Object[1];
		addObj[0] = bean.getParaID();

		logger.info("para iddddd===" + addObj[0]);

		Object[][] data = getSqlModel().getSingleResult(getQuery(5), addObj);

		bean.setPtDtlCode(String.valueOf(data[0][0]));
		bean.setFrmAmount(String.valueOf(data[0][1]));
		bean.setToAmount(String.valueOf(data[0][2]));
		bean.setFixedAmt(String.valueOf(data[0][3]));
		bean.setVarAmt(String.valueOf(data[0][4]));
		bean.setVmth(String.valueOf(data[0][5]));

	}

	public boolean updateTaxSlab(ProfTaxMaster bean) {
		Object addObj[][] = new Object[1][6];

		addObj[0][0] = bean.getFrmAmount();
		addObj[0][1] = bean.getToAmount();
		addObj[0][2] = bean.getFixedAmt();
		addObj[0][3] = bean.getVarAmt();
		addObj[0][4] = bean.getVarMonth();
		addObj[0][5] = bean.getParaID();

		return getSqlModel().singleExecute(getQuery(4), addObj);

	}

	public boolean addTaxDtl(ProfTaxMaster ptMaster, String[] ptCode) {
		Object addObj[][] = new Object[1][5];

		addObj[0][0] = ptMaster.getFrmAmount();
		logger.info("frm amt=====" + addObj[0][0]);
		addObj[0][1] = ptMaster.getToAmount();
		addObj[0][2] = ptMaster.getFixedAmt();
		addObj[0][3] = ptMaster.getVarAmt();
		addObj[0][4] = ptMaster.getVarMonth();

		String Que = " SELECT max(PTAX_CODE) FROM HRMS_PTAX_HDR";
		Object[][] ptaxCode = getSqlModel().getSingleResult(Que);
		String dt2 = "INSERT INTO HRMS_PTAX_DTL (PTAX_CODE,PTAX_DTLCODE,PTAX_FROMAMT,PTAX_UPTOAMT,PTAX_FIXEDAMT,PTAX_VARAMT,PTAX_VARMTH) "
				+ " VALUES ("
				+ ptaxCode[0][0]
				+ ","
				+ "(SELECT NVL(max(PTAX_DTLCODE),0)+1 from HRMS_PTAX_DTL)"
				+ ","
				+ addObj[0][0]
				+ ","
				+ addObj[0][1]
				+ ","
				+ addObj[0][2]
				+ "," + addObj[0][3] + "," + addObj[0][4] + ")";
		getSqlModel().singleExecute(dt2);

		return false;
	}

	public void addTax(ProfTaxMaster ptMaster, Object[] srNo,
			Object[] frmAmount, Object[] toAmount, Object[] fixedAmt,
			Object[] varAmt, Object[] varMonth, int remove) {

		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) // check whether list is empty or not
		{
			for (int i = 0; i < srNo.length; i++) {
				ProfTaxMaster ptMaster1 = new ProfTaxMaster();
				ptMaster1.setSrNo(String.valueOf(i + 1));
				ptMaster1.setFamt(String.valueOf(frmAmount[i]));
				ptMaster1.setTamt(String.valueOf(toAmount[i]));
				ptMaster1.setFdamt(String.valueOf(fixedAmt[i]));
				ptMaster1.setVamt(String.valueOf(varAmt[i]));

				ptMaster1.setVmth(String.valueOf(varMonth[i]));
				tableList.add(ptMaster1);
			}

		}
		if (remove == 1) {
			ptMaster.setPtDtlCode(ptMaster.getTaxDtlCode());
			ptMaster.setFamt(ptMaster.getFrmAmount());
			ptMaster.setTamt(ptMaster.getToAmount());
			ptMaster.setFdamt(ptMaster.getFixedAmt());
			ptMaster.setVamt(ptMaster.getVarAmt());
			ptMaster.setVmth(ptMaster.getVarMonth());
			ptMaster.setSrNo(String.valueOf(tableList.size() + 1));
			tableList.add(ptMaster);
			ptMaster.setTaxList(tableList);

		} else if (remove == 0) {
			// if adding after edit call

			// removing particular voucher head from tablelist

			logger.info("chkkkkkkkkkkkk==" + ptMaster.getChkEdit());
			// tableList.remove(Integer.parseInt(ptMaster.getChkEdit())-1);

		}

		else {
		}
		logger.info("tablelist===" + tableList.size());

		ptMaster.setTableLength(String.valueOf(tableList.size()));

		ptMaster.setTaxList(tableList);
	}

	public void addTax1(ProfTaxMaster ptMaster, Object[] srNo,
			Object[] frmAmount, Object[] toAmount, Object[] fixedAmt,
			Object[] varAmt, Object[] varMonth, Object[] hiddenvarMonth,
			int remove) {

		/*
		 * for (int i = 0; i < hiddenvarMonth.length; i++) {
		 * System.out.println("values"+checkNull(String.valueOf(hiddenvarMonth[0])));
		 *  }
		 */

		String chk = ptMaster.getChkEdit();
		/*
		 * int fromamout,toamount;
		 * fromamout=Integer.parseInt(ptMaster.getFrmAmount());
		 * toamount=Integer.parseInt(ptMaster.getToAmount());
		 */
		logger
				.info("adding the edited record 1 means adding new 2 means modifying existing"
						+ remove);
		logger.info("checkedit valueeeeeeeeeee" + ptMaster.getChkEdit());

		int j = 0;
		if (remove == 2) {
			logger.info("you r at remove 2 if condition");

			j = Integer.parseInt(chk) - 1;
		}
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) // check whether list is empty or not
		{
			if (remove == 1) {
				for (int i = 0; i < srNo.length; i++) {
					ProfTaxMaster ptMaster1 = new ProfTaxMaster();
					ptMaster1.setSrNo(String.valueOf(i + 1));
					ptMaster1.setFamt(String.valueOf(frmAmount[i]));
					ptMaster1.setTamt(String.valueOf(toAmount[i]));
					ptMaster1.setFdamt(String.valueOf(fixedAmt[i]));
					ptMaster1.setVamt(checkNull(String.valueOf(varAmt[i])));
					ptMaster1.setVmth(checkNull(String.valueOf(varMonth[i])));
					ptMaster1.setDupVarmonth(checkNull(String
							.valueOf(hiddenvarMonth[i])));
					tableList.add(ptMaster1);
				}
			}
			if (remove == 2) {
				for (int i = 0; i < srNo.length; i++) {
					if (i != j) {
						ProfTaxMaster ptMaster1 = new ProfTaxMaster();
						ptMaster1.setSrNo(String.valueOf(i + 1));
						ptMaster1.setFamt(String.valueOf(frmAmount[i]));
						ptMaster1.setTamt(String.valueOf(toAmount[i]));
						ptMaster1.setFdamt(String.valueOf(fixedAmt[i]));
						ptMaster1.setVamt(checkNull(String.valueOf(varAmt[i])));
						ptMaster1
								.setVmth(checkNull(String.valueOf(varMonth[i])));
						ptMaster1.setDupVarmonth(checkNull(String
								.valueOf(hiddenvarMonth[i])));
						tableList.add(ptMaster1);
					} else {
						ProfTaxMaster ptMaster1 = new ProfTaxMaster();
						ptMaster1.setSrNo(String.valueOf(i + 1));
						ptMaster1.setFamt(ptMaster.getFrmAmount());
						ptMaster1.setTamt(ptMaster.getToAmount());
						ptMaster1.setFdamt(ptMaster.getFixedAmt());
						if (ptMaster.getVarAmt().equals(""))
							ptMaster1.setVamt("");
						else
							ptMaster1.setVamt(ptMaster.getVarAmt());
						/*
						 * if(ptMaster.getVarMonth().equals(""))
						 * ptMaster1.setVmth("0"); else
						 */
						if (!ptMaster.getVarMonth().equals("")) {
							ptMaster1
									.setVmth(monthname(Integer
											.parseInt(checkNull2(ptMaster
													.getVarMonth()))));
							ptMaster1.setDupVarmonth(ptMaster.getVarMonth());
						} else {
							ptMaster1.setVmth("");
							ptMaster1.setDupVarmonth("");

						}
						tableList.add(ptMaster1);
					}
				}

			}

		}
		if (remove == 1) {
			ptMaster.setPtDtlCode(ptMaster.getTaxDtlCode());
			ptMaster.setFamt(ptMaster.getFrmAmount());
			ptMaster.setTamt(ptMaster.getToAmount());
			ptMaster.setFdamt(ptMaster.getFixedAmt());
			// ptMaster.setVamt(ptMaster.getVarAmt());
			if (!ptMaster.getVarAmt().equals(""))
				ptMaster.setVamt(ptMaster.getVarAmt());
			else
				ptMaster.setVamt(" ");
			// ptMaster.setVmth(ptMaster.getVarMonth());
			if (!ptMaster.getVarMonth().equals("")) {
				ptMaster.setVmth(monthname(Integer.parseInt(checkNull2(ptMaster
						.getVarMonth()))));
				ptMaster.setDupVarmonth(ptMaster.getVarMonth());
			} else {
				ptMaster.setVmth("");
				ptMaster.setDupVarmonth("");

			}
			ptMaster.setSrNo(String.valueOf(tableList.size() + 1));
			tableList.add(ptMaster);
			ptMaster.setTaxList(tableList);

		} else if (remove == 0) {
			// if adding after edit call

			// removing particular voucher head from tablelist

			// logger.info("chkkkkkkkkkkkk=="+ptMaster.getChkEdit());
			// tableList.remove(Integer.parseInt(ptMaster.getChkEdit())-1);

		}

		else {
		}
		// logger.info("tablelist==="+tableList.size());

		ptMaster.setTableLength(String.valueOf(tableList.size()));

		ptMaster.setTaxList(tableList);

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	// checknull2 return 0 value when null is there.
	public String checkNull2(String result) {
		System.out.println("we r at checknull2...!!");
		if (result == null || result.equals("null") || result.equals(" ")) {
			System.out.println("we r at checknull2...!!");
			System.out.println("intergeer test...!!" + Integer.parseInt("0"));
			return "0";
		} else {
			return result;
		}
	}

	public String monthname(int a) {

		switch (a) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return " ";
		}

	}

	public String monthcase(String a) {

		if (String.valueOf(a).equalsIgnoreCase("January")) {
			return "1";
		}
		if (String.valueOf(a).equalsIgnoreCase("February")) {
			return "2";
		}
		if (String.valueOf(a).equalsIgnoreCase("March")) {
			return "3";
		}
		if (String.valueOf(a).equalsIgnoreCase("April")) {
			return "4";
		}
		if (String.valueOf(a).equalsIgnoreCase("May")) {
			return "5";
		}

		if (String.valueOf(a).equalsIgnoreCase("June")) {
			return "6";
		}
		if (String.valueOf(a).equalsIgnoreCase("July")) {
			return "7";
		}
		if (String.valueOf(a).equalsIgnoreCase("August")) {
			return "8";
		}
		if (String.valueOf(a).equalsIgnoreCase("September")) {
			return "9";
		}
		if (String.valueOf(a).equalsIgnoreCase("October")) {
			return "10";
		}
		if (String.valueOf(a).equalsIgnoreCase("November")) {
			return "11";
		}
		if (String.valueOf(a).equalsIgnoreCase("December")) {
			return "12";
		} else {
			return " ";
		}

	}

	public String save(ProfTaxMaster ptMaster, Object[] srNo,
			Object[] frmAmount, Object[] toAmount, Object[] fixedAmt,
			Object[] varAmt, Object[] varMonth, String path) {
		boolean result = false;
		logger.info("IN modelllll");
		Object[][] saveObj = new Object[1][4];

		String query = " SELECT NVL(MAX(PTAX_CODE),0)+1 FROM HRMS_PTAX_HDR";
		Object resultCode[][] = getSqlModel().getSingleResult(query);
		saveObj[0][0] = String.valueOf(resultCode[0][0]);
		saveObj[0][1] = ptMaster.getEffDate();
		saveObj[0][2] = ptMaster.getPtDebitCode();
		saveObj[0][3] = ptMaster.getPtLocCode();

		result = getSqlModel().singleExecute(getQuery(1), saveObj);
		ptMaster.setPtCode(String.valueOf(resultCode[0][0]));

		if (result) {
			saveDetails(ptMaster, srNo, frmAmount, toAmount, fixedAmt, varAmt,
					varMonth);
			xml_profTax(path);
		}
		if (result) {
			/**
			 * Following code calculates the tax and updates tax process
			 */
			try {
				CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
				taxmodel.initiate(context, session);
				logger.info("I m calling tax calculation method");
				String empQry = " SELECT EMP_ID FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ " WHERE CENTER_LOCATION IN (SELECT LOCATION_CODE FROM HRMS_LOCATION "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_LOCATION = HRMS_LOCATION.LOCATION_CODE) "
						+ " WHERE HRMS_LOCATION.LOCATION_PARENT_CODE = "
						+ ptMaster.getPtLocCode() + ") AND EMP_STATUS = 'S'";
				Object[][] empList = getSqlModel().getSingleResult(empQry);
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int fromYear = Integer.parseInt(String.valueOf(cal
						.get(Calendar.YEAR)));
				int month = Integer.parseInt(String.valueOf(cal
						.get(Calendar.MONTH)));
				if (month <= 2)
					fromYear--;
				if (empList != null && empList.length > 0)
					taxmodel.calculateTax(empList, String.valueOf(fromYear),
							String.valueOf(fromYear + 1));
				taxmodel.terminate();
			} catch (Exception e) {
				logger
						.error("Exception in save() profTax Model while calling calculateTax : "
								+ e);
				e.printStackTrace();
			}
			return "saved";
		} else
			return "notsaved";

	}

	private boolean saveDetails(ProfTaxMaster ptMaster, Object[] srNo,
			Object[] frmAmount, Object[] toAmount, Object[] fixedAmt,
			Object[] varAmt, Object[] varMonth) {

		boolean result = false;
		Object[][] detailData = new Object[1][6];
		if (srNo != null) { // check whether data is present in table list
			for (int i = 0; i < srNo.length; i++) {
				detailData[0][0] = ptMaster.getPtCode();
				logger.info("detaillllllll===" + detailData[0][0]);
				logger.info("varMonth...!!" + String.valueOf(varMonth[i]));
				logger.info("frmAmount...!!" + String.valueOf(frmAmount[i]));
				logger.info("toAmount...!!" + String.valueOf(toAmount[i]));
				logger.info("fixedAmt...!!" + String.valueOf(fixedAmt[i]));
				logger.info("varAmt...!!" + String.valueOf(varAmt[i]));

				detailData[0][1] = frmAmount[i];
				detailData[0][2] = toAmount[i];
				detailData[0][3] = fixedAmt[i];
				// detailData[0][4]=varAmt[i];

				if (!String.valueOf(varAmt[i]).equals(""))
					detailData[0][4] = String.valueOf(varAmt[i]);
				else
					detailData[0][4] = "";
				// detailData[0][5]=varMonth[i];
				if (!String.valueOf(varMonth[i]).equals(""))
					detailData[0][5] = String.valueOf(varMonth[i]);
				else
					detailData[0][5] = " ";
				System.out.println("Before firing query...!!");

				result = getSqlModel().singleExecute(getQuery(2), detailData);
			}
		}
		return result;
	}

	public String modify(ProfTaxMaster ptMaster, Object[] srNo,
			Object[] frmAmount, Object[] toAmount, Object[] fixedAmt,
			Object[] varAmt, Object[] varMonth, String path) {
		logger.info("inside modifying  ");
		boolean result = false;

		Object[][] modObj = new Object[1][4];

		modObj[0][0] = ptMaster.getEffDate();
		modObj[0][2] = ptMaster.getPtDebitCode();
		logger.info("dbbbbbbbbbbb==" + modObj[0][2]);
		modObj[0][1] = ptMaster.getPtLocCode();
		logger.info("lcccccccccc==" + modObj[0][1]);
		modObj[0][3] = ptMaster.getPtCode();
		logger.info("ptcccccccccccccc==" + modObj[0][3]);

		result = getSqlModel().singleExecute(getQuery(3), modObj);
		Object[][] code = new Object[1][1];
		code[0][0] = ptMaster.getPtCode();

		getSqlModel().singleExecute(getQuery(4), code);

		if (result) {
			saveDetails(ptMaster, srNo, frmAmount, toAmount, fixedAmt, varAmt,
					varMonth);
			xml_profTax(path);
			/**
			 * Following code calculates the tax and updates tax process
			 */
			try {
				CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
				taxmodel.initiate(context, session);
				logger.info("I m calling tax calculation method");
				String empQry = " SELECT EMP_ID FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ " WHERE CENTER_LOCATION IN (SELECT LOCATION_CODE FROM HRMS_LOCATION "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_LOCATION = HRMS_LOCATION.LOCATION_CODE) "
						+ " WHERE HRMS_LOCATION.LOCATION_PARENT_CODE = "
						+ ptMaster.getPtLocCode() + ") AND EMP_STATUS = 'S'";
				Object[][] empList = getSqlModel().getSingleResult(empQry);
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int fromYear = Integer.parseInt(String.valueOf(cal
						.get(Calendar.YEAR)));
				int month = Integer.parseInt(String.valueOf(cal
						.get(Calendar.MONTH)));
				if (month <= 2)
					fromYear--;
				if (empList != null && empList.length > 0)
					taxmodel.calculateTax(empList, String.valueOf(fromYear),
							String.valueOf(fromYear + 1));
				taxmodel.terminate();
			} catch (Exception e) {
				logger
						.error("Exception in modify() while calling calculateTax : "
								+ e);
				e.printStackTrace();
			}
			return "modified";
		} else
			return "notmodified";

	}

	public boolean delDtlRecord(ProfTaxMaster ptMaster, Object[] ptDtl,
			Object[] srNo, int remove, String poolDir) {
		Object addObj[][] = new Object[1][1];
		boolean result = false;
		addObj[0][0] = String.valueOf(ptDtl[0]);
		/*
		 * getQuery(10) for Deleting the voucher details from the HRMS_VCHDTL
		 * table
		 * 
		 */
		if (!addObj[0][0].equals("")) {
			logger.info("dtllllllll==" + addObj[0][0]);
			result = getSqlModel().singleExecute(getQuery(7), addObj);
			if (result) {
				xml_profTax(poolDir);
			}
		} else {
			/*
			 * ArrayList<Object> tableList = new ArrayList<Object>();
			 * if(srNo!=null) // check whether list is empty or not { Object
			 * addObj1[][] = new Object[1][1]; addObj1[0][0] =
			 * String.valueOf(srNo[0]);
			 * 
			 * for(int i=0;i<srNo.length;i++){ result =
			 * getSqlModel().singleExecute(getQuery(7), addObj1); } }
			 * if(remove==0) {
			 * logger.info("chkkkkkkkkkkkk=="+ptMaster.getChkEdit());
			 * tableList.remove(Integer.parseInt(ptMaster.getChkEdit())-1); }
			 */

			return false;
		}
		return result;
	}

	public void getReport(ProfTaxMaster bean, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session, String path) {
		// TODO Auto-generated method stub
		/*CrystalReport cr = new CrystalReport();
		String path = "org/paradyne/rpt/admin/master/professionalTax.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		
		try {
			String reportType = bean.getReportType();
			if(reportType.equals("") || reportType == null || reportType.equals("null")){
				reportType = "pdf";
			}	
			
			String title = "PTax Parameters";

			ReportDataSet rds = new ReportDataSet();
			String fileName = "PTAX_Parameter";
			String reportPathName = fileName + "." + reportType;
			rds.setFileName(fileName);
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportHeaderRequired(true);
			// rds.setPageOrientation("landscape");
			// rds.setPageSize("TABLOID");
			rds.setTotalColumns(8);
			ReportGenerator rg = null;

			if (path.equals("")) {
				rg = new ReportGenerator(rds, session, context, request);
				new ReportGenerator(rds, session, context);
			} else {
				logger.info("################# ATTACHMENT PATH #############"
						+ path + fileName + "." + reportType);
				rg = new ReportGenerator(rds, path, session, context, request);
				request.setAttribute("reportPath", path + fileName + "."
						+ reportType);
				request.setAttribute("fileName", fileName + "." + reportType);
				request.setAttribute("action", "ProfTaxMaster_input.action");
			}

			String query = "SELECT HRMS_PTAX_HDR.PTAX_CODE, TO_CHAR(HRMS_PTAX_HDR.PTAX_DATE,'DD-MM-YYYY'),"
					+ " NVL(HRMS_LOCATION.LOCATION_NAME,' '), HRMS_DEBIT_HEAD.DEBIT_NAME "
					+ " FROM HRMS_PTAX_HDR"
					+ " LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_PTAX_HDR.PTAX_LOCATION)"
					+ " LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_PTAX_HDR.PTAX_DEBIT_CODE)"
					+ " ORDER BY PTAX_CODE";
			Object[][] ptaxCodeObj = getSqlModel().getSingleResult(query);

			TableDataSet tdsObj = null;
			if (ptaxCodeObj != null && ptaxCodeObj.length > 0) {

				for (int i = 0; i < ptaxCodeObj.length; i++) {
					Object[][] state = new Object[1][1];
					state[0][0] = "State/Location : " + ptaxCodeObj[i][2];

					tdsObj = new TableDataSet();
					tdsObj.setData(state);
					tdsObj.setHeaderTable(false);
					tdsObj.setCellAlignment(new int[] { 0 });
					tdsObj.setCellWidth(new int[] { 100 });
					tdsObj.setCellColSpan(new int[] { 5 });
					tdsObj.setBodyFontStyle(1);
					rg.addTableToDoc(tdsObj);

					String[] header = new String[2];
					header[0] = "Effective Date";
					header[1] = "Professional Tax Debit Code";
					int[] headerAlign = { 0, 0 };
					int[] headerWidth = { 50, 50 };
					int[] headerColSpan = { 3, 2 };

					Object[][] data = new Object[1][2];
					data[0][0] = ptaxCodeObj[i][1];
					data[0][1] = ptaxCodeObj[i][3];

					tdsObj = new TableDataSet();
					tdsObj.setData(data);
					tdsObj.setHeader(header);
					tdsObj.setHeaderBorderDetail(3);
					/*
					 * tdsObj.setHeaderColSpan(new int[] {1,4});
					 * tdsObj.setCellColSpan(new int[] {1,4});
					 */
					tdsObj.setCellAlignment(headerAlign);
					tdsObj.setCellWidth(headerWidth);
					tdsObj.setBorder(true);
					tdsObj.setBorderDetail(3);
					tdsObj.setBlankRowsBelow(1);
					rg.addTableToDoc(tdsObj);

					Object[][] slabTitle = new Object[1][1];
					slabTitle[0][0] = "Slabs";

					tdsObj = new TableDataSet();
					tdsObj.setData(slabTitle);
					tdsObj.setHeaderTable(false);
					tdsObj.setCellAlignment(new int[] { 0 });
					tdsObj.setCellWidth(new int[] { 100 });
					tdsObj.setCellColSpan(new int[] { 5 });
					tdsObj.setBodyFontStyle(1);
					rg.addTableToDoc(tdsObj);

					String[] header1 = new String[5];
					header1[0] = "From Amount";
					header1[1] = "To Amount";
					header1[2] = "Fixed Amount";
					header1[3] = "Variable Amount";
					header1[4] = "Variable Month";

					int[] headerAlign1 = { 2, 2, 2, 2, 0 };
					int[] headerWidth1 = { 20, 20, 20, 20, 20 };
					int[] headerColSpan1 = { 1, 1, 1, 1, 1 };

					String slabQuery = "SELECT NVL(PTAX_FROMAMT,'0'),NVL(PTAX_UPTOAMT,'0'),NVL(PTAX_FIXEDAMT,'0'),"
							+ " NVL(PTAX_VARAMT,'0'), NVL(DECODE(PTAX_VARMTH,'0',' ','1','January','2','February',"
							+ " '3','March','4','April','5','May','6','June','7','July','8','August',"
							+ " '9','September','10','October','11','November','12','December'),' ') "
							+ " FROM HRMS_PTAX_DTL WHERE PTAX_CODE="
							+ ptaxCodeObj[i][0] + " ORDER BY PTAX_FROMAMT ";
					Object[][] slabObj = getSqlModel().getSingleResult(
							slabQuery);

					Object[][] slabData = null;
					if (slabObj != null && slabObj.length > 0) {
						slabData = new Object[slabObj.length][5];
						for (int j = 0; j < slabObj.length; j++) {
							if (checkNull(slabObj[j][0].toString()).trim()
									.equals("")) {
								slabData[j][0] = "";
							} else {
								slabData[j][0] = formatter.format(Double
										.parseDouble(slabObj[j][0].toString()));
							}
							if (checkNull(slabObj[j][1].toString()).trim()
									.equals("")) {
								slabData[j][1] = "";
							} else {
								slabData[j][1] = formatter.format(Double
										.parseDouble(slabObj[j][1].toString()));
							}
							if (checkNull(slabObj[j][2].toString()).trim()
									.equals("")) {
								slabData[j][2] = "";
							} else {
								slabData[j][2] = formatter.format(Double
										.parseDouble(slabObj[j][2].toString()));
							}
							if (checkNull(slabObj[j][3].toString()).trim()
									.equals("")) {
								slabData[j][3] = "";
							} else {
								slabData[j][3] = formatter.format(Double
										.parseDouble(slabObj[j][3].toString()));
							}

							if (checkNull(slabObj[j][4].toString()).trim()
									.equals("")) {
								slabData[j][4] = "";
							} else {
								slabData[j][4] = slabObj[j][4];
							}
						}

						tdsObj = new TableDataSet();
						tdsObj.setData(slabData);
						tdsObj.setHeader(header1);
						tdsObj.setHeaderBorderDetail(3);
						tdsObj.setCellColSpan(headerColSpan1);
						tdsObj.setCellAlignment(headerAlign1);
						tdsObj.setCellWidth(headerWidth1);
						tdsObj.setBorder(true);
						tdsObj.setBorderDetail(3);
						tdsObj.setBlankRowsBelow(1);
						rg.addTableToDoc(tdsObj);
					}
				}

				Object[][] slabTitle = new Object[1][1];
				slabTitle[0][0] = "Total No of States/Location : " + ptaxCodeObj.length;

				tdsObj = new TableDataSet();
				tdsObj.setData(slabTitle);
				tdsObj.setHeaderTable(false);
				tdsObj.setCellAlignment(new int[] { 0 });
				tdsObj.setCellWidth(new int[] { 100 });
				tdsObj.setCellColSpan(new int[] { 5 });
				tdsObj.setBodyFontStyle(1);
				rg.addTableToDoc(tdsObj);
			} else {
				Object[][] slabTitle = new Object[1][1];
				slabTitle[0][0] = "No records avaliable for selected criteria";

				tdsObj = new TableDataSet();
				tdsObj.setData(slabTitle);
				tdsObj.setHeaderTable(false);
				tdsObj.setCellAlignment(new int[] { 0 });
				tdsObj.setCellWidth(new int[] { 100 });
				tdsObj.setCellColSpan(new int[] { 5 });
				tdsObj.setBodyFontStyle(1);
				rg.addTableToDoc(tdsObj);
			}

			rg.process();
			if (path.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void xml_profTax(String path) {

		if (!(path == null || path.equals("") || path.equals(null)))
			path = "/" + path;

		try {
			new XMLReaderWriter().write(buildProftax("PAYROLL",
					"PROFESSIONAL TAX"), path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Document buildProftax(String head, String subhead) {
		String query1 = " SELECT HRMS_PTAX_DTL.PTAX_DTLCODE, HRMS_PTAX_HDR.PTAX_LOCATION, HRMS_PTAX_HDR.PTAX_DEBIT_CODE, "
				+ " PTAX_FROMAMT, PTAX_UPTOAMT, PTAX_FIXEDAMT, NVL(PTAX_VARMTH,''),  PTAX_VARAMT ,TO_CHAR(PTAX_DATE,'DD-MM-YYYY')"
				+ " FROM HRMS_PTAX_HDR "
				+ " LEFT JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_DTL.PTAX_CODE = HRMS_PTAX_HDR.PTAX_CODE) "
				+ " ORDER BY PTAX_DATE DESC ";
		Object data[][] = getSqlModel().getSingleResult(query1);
		Document document = DocumentHelper.createDocument();
		logger.info("data.length" + data.length);
		// Element header;
		Element element;
		Element root = document.addElement(head);
		if (data != null && data.length > 0) {
			// header = root.addElement("PTAX").addAttribute("name", subhead);
			for (int i = 0; i < data.length; i++) {
				element = root.addElement("PTAX").addAttribute("code",
						String.valueOf(data[i][0])).addAttribute("location",
						String.valueOf(data[i][1])).addAttribute("debitCode",
						String.valueOf(data[i][2])).addAttribute("frmAmt",
						String.valueOf(data[i][3])).addAttribute("toAmt",
						String.valueOf(data[i][4])).addAttribute("fxdAmt",
						String.valueOf(data[i][5])).addAttribute("varMnth",
						String.valueOf(data[i][6])).addAttribute("varAmt",
						String.valueOf(data[i][7])).addAttribute(
						"effectiveDate", String.valueOf(data[i][8]));
			}
		} else {
			logger.info("NO PRF TAX");
			// header = root.addElement("PTAX").addAttribute("name", subhead);
			element = root.addElement("PTAX").addAttribute("code",
					String.valueOf("code")).addAttribute("location",
					String.valueOf("loc")).addAttribute("debitCode",
					String.valueOf("deb")).addAttribute("frmAmt",
					String.valueOf("frmamt")).addAttribute("toAmt",
					String.valueOf("toamt")).addAttribute("fxdAmt",
					String.valueOf("fxamt")).addAttribute("varMnth",
					String.valueOf("varmnth")).addAttribute("varAmt",
					String.valueOf("varamt")).addAttribute("effectiveDate",
					String.valueOf("effectivedate"));

		}
		return document;
	}

	public void removedtl(ProfTaxMaster ptMaster, Object[] srNo,
			Object[] frmAmount, Object[] toAmount, Object[] fixedAmt,
			Object[] varAmt, Object[] varMonth, Object[] hiddenvarMonth,
			String rid) {
		// TODO Auto-generated method stub

		int j = Integer.parseInt(rid);
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) // check whether list is empty or not
		{
			System.out.println("REmoved value" + j);
			System.out.println("Size of proftax:::" + srNo.length);
			for (int i = 0; i < srNo.length; i++) {
				if (i != j) {

					ProfTaxMaster ptMaster1 = new ProfTaxMaster();
					ptMaster1.setSrNo(String.valueOf(i + 1));
					ptMaster1.setFamt(String.valueOf(frmAmount[i]));
					ptMaster1.setTamt(String.valueOf(toAmount[i]));
					ptMaster1.setFdamt(String.valueOf(fixedAmt[i]));
					ptMaster1.setVamt(checkNull(String.valueOf(varAmt[i])));
					ptMaster1.setVmth(checkNull(String.valueOf(varMonth[i])));
					ptMaster1.setDupVarmonth(checkNull(String
							.valueOf(hiddenvarMonth[i])));
					tableList.add(ptMaster1);
				}
			}

		}
		ptMaster.setTableLength(String.valueOf(tableList.size()));
		ptMaster.setTaxList(tableList);

	}

	/**
	 * @author REEBA
	 * @param ptMaster
	 * @param request
	 */
	public void displayList(ProfTaxMaster ptMaster, HttpServletRequest request) {
		String listQuery = "SELECT PTAX_CODE,TO_CHAR(PTAX_DATE,'DD-MM-YYYY'),NVL(LOCATION_NAME,' '),"
			+ " NVL(PTAX_LOCATION,'0') FROM HRMS_PTAX_HDR"
			+ " LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_PTAX_HDR.PTAX_LOCATION)"
			+ " LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_PTAX_HDR.PTAX_DEBIT_CODE) ORDER BY PTAX_CODE ";
		Object[][] listObj = getSqlModel().getSingleResult(listQuery);
		
		String[] pageIndex = Utility.doPaging(ptMaster.getMyPage(), listObj.length, 20);
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
			ptMaster.setMyPage("1");

		ArrayList<Object> list = new ArrayList<Object>();
		if (listObj != null && listObj.length > 0) {

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				ProfTaxMaster bean = new ProfTaxMaster();
				bean.setPtCodeItr(String.valueOf(listObj[i][0]));
				bean.setEffectiveDateItr(checkNull(String.valueOf(listObj[i][1])));
				bean.setLocationItr(checkNull(String.valueOf(listObj[i][2])));
				bean.setLocationCodeItr(String.valueOf(listObj[i][3]));
				list.add(bean);
			}

			ptMaster.setTotalRecords("" + listObj.length);
		} else {
			ptMaster.setListLength("false");
		}

		if (list.size() > 0)
			ptMaster.setListLength("true");
		else
			ptMaster.setListLength("false");
		ptMaster.setIteratorlist(list);

	}

	/**
	 * @author REEBA
	 * @param ptMaster
	 * @param code
	 * @param poolDir
	 * @return
	 */
	public boolean deletecheckedRecords(ProfTaxMaster ptMaster, String[] code,
			String poolDir) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					
					result = getSqlModel().singleExecute(getQuery(6), delete);
					if (result) {
						result = getSqlModel().singleExecute(getQuery(4), delete);
					}
					if (!result)
						count++;

				}
			}
		}
		if (result) {
			xml_profTax(poolDir);
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	/**
	 * @author REEBA
	 * @param ptMaster
	 */
	public void callforedit(ProfTaxMaster ptMaster) {
		String query = "SELECT PTAX_CODE,TO_CHAR(PTAX_DATE,'DD-MM-YYYY'),NVL(LOCATION_NAME,' '),NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '),"
			+ " NVL(PTAX_LOCATION,'0'),NVL(PTAX_DEBIT_CODE,'0') FROM HRMS_PTAX_HDR"
			+ " LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_PTAX_HDR.PTAX_LOCATION)"
			+ " LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_PTAX_HDR.PTAX_DEBIT_CODE) WHERE PTAX_CODE ="+ptMaster.getHiddencode();
		Object[][] dataObj = getSqlModel().getSingleResult(query);
		if(dataObj!=null && dataObj.length>0){
			ptMaster.setPtCode(String.valueOf(dataObj[0][0]));
			ptMaster.setEffDate(String.valueOf(dataObj[0][1]));
			ptMaster.setLocName(String.valueOf(dataObj[0][2]));
			ptMaster.setPtDebitName(String.valueOf(dataObj[0][3]));
			ptMaster.setPtLocCode(String.valueOf(dataObj[0][4]));
			ptMaster.setPtDebitCode(String.valueOf(dataObj[0][5]));
		}
		
	}

}
