package org.paradyne.model.payroll.incometax;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.TaxSlab;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import com.ibm.icu.util.Calendar;

public class TaxSlabModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TaxSlabModel.class);
	TaxSlab taxSlab = null;

	/**
	 * This method is used to add the slabs.
	 * 
	 * @param bean
	 * @return
	 */
	public boolean addTaxSlab(TaxSlab bean, HttpServletRequest request) {
		boolean result = false;
		try {

			if (!checkFromYearExit(bean)) {

				Object addObj[][] = new Object[1][6];

				Object taxSlabForMenIDObj[][] = getSqlModel().getSingleResult(
						"SELECT NVL(MAX(HRMS_TAX_SLAB.TAX_SLAB_ID),0) FROM HRMS_TAX_SLAB");
				int taxSlabForMenIncrementedID = Integer.parseInt(""
						+ taxSlabForMenIDObj[0][0]);

				// Records save for dynamic rows : MEN Start
				String[] frmAmonut = request.getParameterValues("frmAmount");
				String[] toAmount = request.getParameterValues("toAmount");
				String[] taxPercentage = request
						.getParameterValues("taxPercentage");

				int k = 0;

				Object detailQueryObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT "
										+ "	HRMS_TAX_SLAB.TAX_FROM_YEAR,HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB"
										+ " WHERE HRMS_TAX_SLAB.TAX_EMP_TYPE='M' AND HRMS_TAX_SLAB.TAX_FROM_YEAR = "
										+ bean.getFromYear());
				Object detailObj[][] = new Object[detailQueryObj.length][1];
				int z = 0;

				if (detailQueryObj != null && detailQueryObj.length > 0) {
					for (int t = 0; t < detailQueryObj.length; t++) {
						detailObj[z][0] = detailQueryObj[0][0];
						System.out.println("ID [" + t + "]" + detailObj[z][0]);
						z++;
					}
					String deleteWitnessQuery = "DELETE FROM HRMS_TAX_SLAB WHERE HRMS_TAX_SLAB.TAX_EMP_TYPE='M' AND HRMS_TAX_SLAB.TAX_FROM_YEAR=?";
					getSqlModel().singleExecute(deleteWitnessQuery, detailObj);
				}
				// Delete and insert Begins

				if (frmAmonut != null) {
					Object modParam[][] = new Object[frmAmonut.length][4];
					for (int i = 0; i < frmAmonut.length; i++) {
						 modParam[k][0] = ++taxSlabForMenIncrementedID;
						modParam[k][1] = frmAmonut[i];
						modParam[k][2] = checkNull(String.valueOf(toAmount[i]));
						modParam[k][3] = taxPercentage[i];

						k++;
					}
					
					for (int i = 0; i < 4; i++) {
						System.out.println("Board Member Details::::::"
								+ modParam[0][i]);
					}
					
					String insertQuery = "INSERT INTO HRMS_TAX_SLAB"
							+ "(TAX_SLAB_ID,TAX_FROM_YEAR,TAX_TO_YEAR,TAX_FROM_AMOUNT, TAX_TO_AMT, TAX_PERCENT, TAX_EMP_TYPE)"
							+ " VALUES(?,"
							+ bean.getFromYear() + "," + bean.getToYear()
							+ ",?,?,?,'M')";

					result = getSqlModel().singleExecute(insertQuery, modParam);

				}
				// Records save for dynamic rows : MEN End

				// Records save for dynamic rows : WOMEN Start

				Object taxSlabForWoMenIDObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(HRMS_TAX_SLAB.TAX_SLAB_ID),0) FROM HRMS_TAX_SLAB");
				int taxSlabForWoMenIncrementedID = Integer.parseInt(""
						+ taxSlabForWoMenIDObj[0][0]);

				String[] frmAmonutWomen = request
						.getParameterValues("frmAmountWomen");
				String[] toAmountWomen = request
						.getParameterValues("toAmountWomen");
				String[] taxPercentageWomen = request
						.getParameterValues("taxPercentageWomen");

				int l = 0;

				Object detailQueryWomenObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT "
										+ "	HRMS_TAX_SLAB.TAX_FROM_YEAR,HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB"
										+ " WHERE HRMS_TAX_SLAB.TAX_EMP_TYPE='F' AND HRMS_TAX_SLAB.TAX_FROM_YEAR = "
										+ bean.getFromYear());
				Object detailWomenObj[][] = new Object[detailQueryWomenObj.length][1];
				int y = 0;

				if (detailQueryWomenObj != null
						&& detailQueryWomenObj.length > 0) {
					for (int t = 0; t < detailQueryWomenObj.length; t++) {
						detailWomenObj[y][0] = detailQueryWomenObj[0][0];
						System.out.println("ID [" + t + "]"
								+ detailWomenObj[y][0]);
						y++;
					}
					String deleteWitnessQuery = "DELETE FROM HRMS_TAX_SLAB WHERE TAX_EMP_TYPE='F' AND TAX_FROM_YEAR=?";
					getSqlModel().singleExecute(deleteWitnessQuery,
							detailWomenObj);
				}
				// Delete and insert Begins

				if (frmAmonutWomen != null) {
					Object modParam[][] = new Object[frmAmonutWomen.length][4];
					for (int i = 0; i < frmAmonutWomen.length; i++) {
					 modParam[l][0] = ++taxSlabForWoMenIncrementedID;
						modParam[l][1] = frmAmonutWomen[i];
						modParam[l][2] = checkNull(String
								.valueOf(toAmountWomen[i]));
						modParam[l][3] = taxPercentageWomen[i];

						l++;
					}

					String insertQuery = "INSERT INTO HRMS_TAX_SLAB"
							+ "(TAX_SLAB_ID,TAX_FROM_YEAR,TAX_TO_YEAR,TAX_FROM_AMOUNT, TAX_TO_AMT, TAX_PERCENT, TAX_EMP_TYPE)"
							+ " VALUES(?,"
							+ bean.getFromYear() + "," + bean.getToYear()
							+ ",?,?,?,'F')";

					result = getSqlModel().singleExecute(insertQuery, modParam);

				}
				// Records save for dynamic rows : WOMEN End

				// Records save for dynamic rows : SENIOR Start

				Object taxSlabForSeniorIDObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(HRMS_TAX_SLAB.TAX_SLAB_ID),0) FROM HRMS_TAX_SLAB");
				int taxSlabForSeniorIncrementedID = Integer.parseInt(""
						+ taxSlabForSeniorIDObj[0][0]);

				String[] frmAmonutSenior = request
						.getParameterValues("frmAmountSenior");
				String[] toAmountSenior = request
						.getParameterValues("toAmountSenior");
				String[] taxPercentageSenior = request
						.getParameterValues("taxPercentageSenior");

				int n = 0;

				Object detailQuerySeniorObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT "
										+ "	HRMS_TAX_SLAB.TAX_FROM_YEAR,HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB"
										+ " WHERE HRMS_TAX_SLAB.TAX_EMP_TYPE='S' AND HRMS_TAX_SLAB.TAX_FROM_YEAR = "
										+ bean.getFromYear());
				Object detailSeniorObj[][] = new Object[detailQuerySeniorObj.length][1];
				int x = 0;

				if (detailQuerySeniorObj != null
						&& detailQuerySeniorObj.length > 0) {
					for (int t = 0; t < detailQuerySeniorObj.length; t++) {
						detailSeniorObj[x][0] = detailQuerySeniorObj[0][0];
						System.out.println("ID [" + t + "]"
								+ detailSeniorObj[x][0]);
						x++;
					}
					String deleteWitnessQuery = "DELETE FROM HRMS_TAX_SLAB WHERE TAX_EMP_TYPE='S' AND TAX_FROM_YEAR=?";
					getSqlModel().singleExecute(deleteWitnessQuery,
							detailSeniorObj);
				}
				// Delete and insert Begins

				if (frmAmonutSenior != null) {
					Object modParam[][] = new Object[frmAmonutSenior.length][4];
					for (int i = 0; i < frmAmonutSenior.length; i++) {
						modParam[n][0] = ++taxSlabForSeniorIncrementedID;
						modParam[n][1] = frmAmonutSenior[i];
						modParam[n][2] = checkNull(String
								.valueOf(toAmountSenior[i]));
						modParam[n][3] = taxPercentageSenior[i];

						n++;
					}

					String insertSeniorQuery = "INSERT INTO HRMS_TAX_SLAB"
							+ "(TAX_SLAB_ID,TAX_FROM_YEAR,TAX_TO_YEAR,TAX_FROM_AMOUNT, TAX_TO_AMT, TAX_PERCENT, TAX_EMP_TYPE)"
							+ " VALUES(?,"
							+ bean.getFromYear() + "," + bean.getToYear()
							+ ",?,?,?,'S')";

					result = getSqlModel().singleExecute(insertSeniorQuery,
							modParam);

				}
				// Records save for dynamic rows : SENIOR End

				/*
				 * if (result) {
				 *//**
					 * Following code calculates the tax and updates tax process
					 */
				/*
				 * try { CommonTaxCalculationModel taxmodel = new
				 * CommonTaxCalculationModel(); taxmodel.initiate(context,
				 * session); logger.info("I m calling tax calculation method");
				 * Object empList[][] = getSqlModel() .getSingleResult( "SELECT
				 * EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'"); Calendar
				 * cal = Calendar.getInstance(); cal.setTime(new Date()); int
				 * fromYear = Integer.parseInt(String.valueOf(cal
				 * .get(Calendar.YEAR))); int month =
				 * Integer.parseInt(String.valueOf(cal .get(Calendar.MONTH)));
				 * if (month <= 2) fromYear--; if (empList != null &&
				 * empList.length > 0) taxmodel.calculateTax(empList,
				 * String.valueOf(fromYear), String.valueOf(fromYear + 1));
				 * taxmodel.terminate(); } catch (Exception e) { logger
				 * .error("Exception in addTaxSlab() of TaxSlabModel while
				 * calling calculateTax : " + e); e.printStackTrace(); } }
				 */

			}// end of if
			else {
				return false;
			}// end of else

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}// end of addTaxSlab method

	/**
	 * This method is used to delete the slabs
	 * 
	 * @param bean
	 * @return
	 */
	public boolean delTaxSlabRecord(TaxSlab bean) {

		Object addObj[][] = new Object[1][1];
		/**
		 * the respective records slab Id is passed in the object.
		 */
		addObj[0][0] = bean.getParaID();
		/**
		 * the sql method is called to execute the delete query
		 */
		return getSqlModel().singleExecute(getQuery(3), addObj);

	}// end of delTaxSlabRecord method

	/**
	 * This method is used to update the slab record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean updateEmpInv(TaxSlab bean) {
		Object addObj[][] = new Object[1][3];

		addObj[0][0] = bean.getParaID();
		addObj[0][1] = bean.getFrmAmount();
		addObj[0][2] = bean.getToAmount();
		addObj[0][3] = bean.getTaxPercentage();

		return getSqlModel().singleExecute(getQuery(2), addObj);
	}// end of update method

	/**
	 * this method is used to retrieve the previously saved record.
	 * 
	 * @param bean
	 * @return
	 */
	public boolean getRecord_old(TaxSlab bean, boolean isEdit) {

		Object[][] addObj = new Object[1][3];

		if (isEdit) {
			if (bean.getType().equals("Women")) {
				addObj[0][0] = 'F';
			} else {
				addObj[0][0] = bean.getType().charAt(0);
			}

		} else {
			addObj[0][0] = bean.getType();
		}

		addObj[0][1] = bean.getFromYear();
		addObj[0][2] = bean.getToYear();

		bean.setType(String.valueOf(addObj[0][0]));

		String query = " select TAX_SLAB_ID,TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT from HRMS_TAX_SLAB where TAX_EMP_TYPE='"
				+ addObj[0][0]
				+ "' and TAX_FROM_YEAR="
				+ addObj[0][1]
				+ " and TAX_TO_YEAR="
				+ addObj[0][2]
				+ " order by TAX_FROM_AMOUNT ";

		Object[][] data = getSqlModel().getSingleResult(query);

		ArrayList<Object> taxSlabList1 = new ArrayList<Object>();
		if (data.length < 1) {
			return false;
		}// end of if
		for (int i = 0; i < data.length; i++) {
			System.out.println("with in for loop...!");

			bean.setChkEdit("true");
			TaxSlab bean1 = new TaxSlab();
			bean1.setTaxId(String.valueOf(data[i][0]));
			bean1.setFrmAmount(String.valueOf(data[i][1]));
			bean1.setToAmount(String.valueOf(data[i][2]));
			bean1.setTaxPercentage(String.valueOf(data[i][3]));
			taxSlabList1.add(bean1);
		}// end of loop
		/**
		 * the records are set in the taxSlabList to display.
		 */
		// System.out.println("before setting List............!!!");
		bean.setTaxSlabList(taxSlabList1);
		// System.out.println("after setting List............!!!");
		return true;
	}// end of getRecord method

	/**
	 * this method is used to retrieve the previously saved record.
	 * 
	 * @param bean
	 * @return
	 */
	public boolean getRecord(TaxSlab bean, boolean isEdit) {

		Object[][] addObj = new Object[1][3];

	
	/*	if (isEdit) {
			if (bean.getType().equals("Women")) {
				addObj[0][0] = 'F';
			} else {
				addObj[0][0] = bean.getType().charAt(0);
			}
			if (bean.getType().equals("Men")) {
				addObj[0][0] = 'M';
			} else {
				addObj[0][0] = bean.getType().charAt(0);
			}
			if (bean.getType().equals("Senior")) {
				addObj[0][0] = 'S';
			} else {
				addObj[0][0] = bean.getType().charAt(0);
			}

		} else {
			addObj[0][0] = bean.getType();
			
			
		}
			*/
		

		addObj[0][1] = bean.getFromYear();
		addObj[0][2] = bean.getToYear();

		//bean.setType(String.valueOf(addObj[0][0]));

		String query = " select HRMS_TAX_SLAB.TAX_SLAB_ID,HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT,HRMS_TAX_SLAB.TAX_PERCENT from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='M' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ bean.getFromYear()
				+ " and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ bean.getToYear()
				+ " order by HRMS_TAX_SLAB.TAX_FROM_AMOUNT ";

		Object[][] data = getSqlModel().getSingleResult(query);

		ArrayList<Object> taxSlabList1 = new ArrayList<Object>();
		if (data.length < 1) {
			return false;
		}// end of if
		for (int i = 0; i < data.length; i++) {
			System.out.println("with in for loop...!");

			bean.setChkEdit("true");
			TaxSlab bean1 = new TaxSlab();
			bean1.setTaxId(String.valueOf(data[i][0]));
			bean1.setTaxSlabManListId(String.valueOf(data[i][0]));
			bean1.setFrmAmount(checkNull(String.valueOf(data[i][1])));
			bean1.setToAmount(checkNull(String.valueOf(data[i][2])));
			bean1.setTaxPercentage(checkNull(String.valueOf(data[i][3])));
			taxSlabList1.add(bean1);
		}// end of loop
		/**
		 * the records are set in the taxSlabList to display.
		 */
		// System.out.println("before setting List............!!!");
		bean.setTaxSlabList(taxSlabList1);
		// System.out.println("after setting List............!!!");
		// For WOmen
		String queryWomen = " select HRMS_TAX_SLAB.TAX_SLAB_ID,HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT,HRMS_TAX_SLAB.TAX_PERCENT from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='F' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ bean.getFromYear()
				+ " and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ bean.getToYear()
				+ " order by HRMS_TAX_SLAB.TAX_FROM_AMOUNT ";

		Object[][] dataWomen = getSqlModel().getSingleResult(queryWomen);

		ArrayList<Object> taxSlabListWomen = new ArrayList<Object>();
		if (dataWomen.length < 1) {
			return false;
		}// end of if
		for (int i = 0; i < dataWomen.length; i++) {
			System.out.println("with in for loop...!");

			bean.setChkEdit("true");
			TaxSlab bean1 = new TaxSlab();
			bean1.setTaxSlabWomenListId(String.valueOf(dataWomen[i][0]));
			bean1.setFrmAmountWomen(checkNull(String.valueOf(dataWomen[i][1])));
			bean1.setToAmountWomen(checkNull(String.valueOf(dataWomen[i][2])));
			bean1.setTaxPercentageWomen(checkNull(String.valueOf(dataWomen[i][3])));
			taxSlabListWomen.add(bean1);
		}// end of loop
		/**
		 * the records are set in the taxSlabList to display.
		 */
		// System.out.println("before setting List............!!!");
		bean.setTaxSlabWomenList(taxSlabListWomen);

		// For Senior
		String querySenior = " select HRMS_TAX_SLAB.TAX_SLAB_ID,HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT,HRMS_TAX_SLAB.TAX_PERCENT from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='S' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ bean.getFromYear()
				+ " and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ bean.getToYear()
				+ " order by HRMS_TAX_SLAB.TAX_FROM_AMOUNT ";

		Object[][] dataSenior = getSqlModel().getSingleResult(querySenior);

		ArrayList<Object> taxSlabListSenior = new ArrayList<Object>();
		if (dataSenior.length < 1) {
			return false;
		}// end of if
		for (int i = 0; i < dataSenior.length; i++) {
			System.out.println("with in for loop...!");

			bean.setChkEdit("true");
			TaxSlab bean1 = new TaxSlab();
			bean1.setTaxSlabSeniorListId(String.valueOf(dataSenior[i][0]));
			bean1.setFrmAmountSenior(checkNull(String.valueOf(dataSenior[i][1])));
			bean1.setToAmountSenior(checkNull(String.valueOf(dataSenior[i][2])));
			bean1.setTaxPercentageSenior(checkNull(String.valueOf(dataSenior[i][3])));
			taxSlabListSenior.add(bean1);
		}// end of loop
		/**
		 * the records are set in the taxSlabList to display.
		 */
		// System.out.println("before setting List............!!!");
		bean.setTaxSlabSeniorList(taxSlabListSenior);
		return true;
	}// end of getRecord method

	/**
	 * This method is used to edit the records
	 * 
	 * @param taxSlab
	 */
	public void getEditRecord(TaxSlab taxSlab) {

		Object[] addObj = new Object[1];
		addObj[0] = taxSlab.getParaID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(5), addObj);

		taxSlab.setTaxId(String.valueOf(data[0][0]));
		taxSlab.setFrmAmount(String.valueOf(data[0][1]));
		taxSlab.setToAmount(String.valueOf(data[0][2]));
		taxSlab.setTaxPercentage(String.valueOf(data[0][3]));
	}// end of getEditRecord method

	public boolean updateTaxSlab(TaxSlab bean, HttpServletRequest request) {
		boolean result = false;
		try {

			Object addObj[][] = new Object[1][6];

			Object taxSlabForMenIDObj[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(HRMS_TAX_SLAB.TAX_SLAB_ID),0)+1 FROM HRMS_TAX_SLAB");
			int taxSlabForMenIncrementedID = Integer.parseInt(""
					+ taxSlabForMenIDObj[0][0]);

			// Records save for dynamic rows : MEN Start
			String[] frmAmonut = request.getParameterValues("frmAmount");
			String[] toAmount = request.getParameterValues("toAmount");
			String[] taxPercentage = request
					.getParameterValues("taxPercentage");

			int k = 0;

			Object detailQueryObj[][] = getSqlModel().getSingleResult(
					"SELECT " + "	HRMS_TAX_SLAB.TAX_FROM_YEAR,HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB"
							+ " WHERE HRMS_TAX_SLAB.TAX_EMP_TYPE='M' AND HRMS_TAX_SLAB.TAX_FROM_YEAR = "
							+ bean.getFromYear());
			Object detailObj[][] = new Object[detailQueryObj.length][1];
			int z = 0;

			if (detailQueryObj != null && detailQueryObj.length > 0) {
				for (int t = 0; t < detailQueryObj.length; t++) {
					detailObj[z][0] = detailQueryObj[0][0];
					System.out.println("ID [" + t + "]" + detailObj[z][0]);
					z++;
				}
				String deleteWitnessQuery = "DELETE FROM HRMS_TAX_SLAB WHERE TAX_EMP_TYPE='M' AND TAX_FROM_YEAR=?";
				getSqlModel().singleExecute(deleteWitnessQuery, detailObj);
			}
			// Delete and insert Begins

			if (frmAmonut != null) {
				Object modParam[][] = new Object[frmAmonut.length][4];
				for (int i = 0; i < frmAmonut.length; i++) {
					modParam[k][0] = ++taxSlabForMenIncrementedID;
					modParam[k][1] = frmAmonut[i];
					modParam[k][2] = checkNull(String.valueOf(toAmount[i]));
					modParam[k][3] = taxPercentage[i];

					k++;
				}

				String insertQuery = "INSERT INTO HRMS_TAX_SLAB"
						+ "(TAX_SLAB_ID,TAX_FROM_YEAR,TAX_TO_YEAR,TAX_FROM_AMOUNT, TAX_TO_AMT, TAX_PERCENT, TAX_EMP_TYPE)"
						+ " VALUES(?,"
						+ bean.getFromYear() + "," + bean.getToYear()
						+ ",?,?,?,'M')";

				result = getSqlModel().singleExecute(insertQuery, modParam);

			}
			// Records save for dynamic rows : MEN End

			// Records save for dynamic rows : WOMEN Start

			Object taxSlabForWoMenIDObj[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(TAX_SLAB_ID),0)+1 FROM HRMS_TAX_SLAB");
			int taxSlabForWoMenIncrementedID = Integer.parseInt(""
					+ taxSlabForWoMenIDObj[0][0]);

			String[] frmAmonutWomen = request
					.getParameterValues("frmAmountWomen");
			String[] toAmountWomen = request
					.getParameterValues("toAmountWomen");
			String[] taxPercentageWomen = request
					.getParameterValues("taxPercentageWomen");

			int l = 0;

			Object detailQueryWomenObj[][] = getSqlModel().getSingleResult(
					"SELECT " + "	HRMS_TAX_SLAB.TAX_FROM_YEAR,HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB"
							+ " WHERE HRMS_TAX_SLAB.TAX_EMP_TYPE='F' AND HRMS_TAX_SLAB.TAX_FROM_YEAR = "
							+ bean.getFromYear());
			Object detailWomenObj[][] = new Object[detailQueryWomenObj.length][1];
			int y = 0;

			if (detailQueryWomenObj != null && detailQueryWomenObj.length > 0) {
				for (int t = 0; t < detailQueryWomenObj.length; t++) {
					detailWomenObj[y][0] = detailQueryWomenObj[0][0];
					System.out.println("ID [" + t + "]" + detailWomenObj[y][0]);
					y++;
				}
				String deleteWitnessQuery = "DELETE FROM HRMS_TAX_SLAB WHERE TAX_EMP_TYPE='F' AND TAX_FROM_YEAR=?";
				getSqlModel().singleExecute(deleteWitnessQuery, detailWomenObj);
			}
			// Delete and insert Begins

			if (frmAmonutWomen != null) {
				Object modParam[][] = new Object[frmAmonutWomen.length][4];
				for (int i = 0; i < frmAmonutWomen.length; i++) {
					 modParam[l][0] = ++taxSlabForWoMenIncrementedID;
					modParam[l][1] = frmAmonutWomen[i];
					modParam[l][2] = checkNull(String.valueOf(toAmountWomen[i]));
					modParam[l][3] = taxPercentageWomen[i];

					l++;
				}

				String insertQuery = "INSERT INTO HRMS_TAX_SLAB"
						+ "(TAX_SLAB_ID,TAX_FROM_YEAR,TAX_TO_YEAR,TAX_FROM_AMOUNT, TAX_TO_AMT, TAX_PERCENT, TAX_EMP_TYPE)"
						+ " VALUES(?,"
						+ bean.getFromYear() + "," + bean.getToYear()
						+ ",?,?,?,'F')";

				result = getSqlModel().singleExecute(insertQuery, modParam);

			}
			// Records save for dynamic rows : WOMEN End

			// Records save for dynamic rows : SENIOR Start

			Object taxSlabForSeniorIDObj[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(HRMS_TAX_SLAB.TAX_SLAB_ID),0)+1 FROM HRMS_TAX_SLAB");
			int taxSlabForSeniorIncrementedID = Integer.parseInt(""
					+ taxSlabForSeniorIDObj[0][0]);

			String[] frmAmonutSenior = request
					.getParameterValues("frmAmountSenior");
			String[] toAmountSenior = request
					.getParameterValues("toAmountSenior");
			String[] taxPercentageSenior = request
					.getParameterValues("taxPercentageSenior");

			int n = 0;

			Object detailQuerySeniorObj[][] = getSqlModel().getSingleResult(
					"SELECT " + "	HRMS_TAX_SLAB.TAX_FROM_YEAR,HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB"
							+ " WHERE HRMS_TAX_SLAB.TAX_EMP_TYPE='S' AND HRMS_TAX_SLAB.TAX_FROM_YEAR = "
							+ bean.getFromYear());
			Object detailSeniorObj[][] = new Object[detailQuerySeniorObj.length][1];
			int x = 0;

			if (detailQuerySeniorObj != null && detailQuerySeniorObj.length > 0) {
				for (int t = 0; t < detailQuerySeniorObj.length; t++) {
					detailSeniorObj[x][0] = detailQuerySeniorObj[0][0];
					System.out
							.println("ID [" + t + "]" + detailSeniorObj[x][0]);
					x++;
				}
				String deleteWitnessQuery = "DELETE FROM HRMS_TAX_SLAB WHERE TAX_EMP_TYPE='S' AND TAX_FROM_YEAR=?";
				getSqlModel()
						.singleExecute(deleteWitnessQuery, detailSeniorObj);
			}
			// Delete and insert Begins

			if (frmAmonutSenior != null) {
				Object modParam[][] = new Object[frmAmonutSenior.length][4];
				for (int i = 0; i < frmAmonutSenior.length; i++) {
					 modParam[n][0] = ++taxSlabForSeniorIncrementedID;
					modParam[n][1] = frmAmonutSenior[i];
					modParam[n][2] = checkNull(String
							.valueOf(toAmountSenior[i]));
					modParam[n][3] = taxPercentageSenior[i];

					n++;
				}

				String insertSeniorQuery = "INSERT INTO HRMS_TAX_SLAB"
						+ "(TAX_SLAB_ID,TAX_FROM_YEAR,TAX_TO_YEAR,TAX_FROM_AMOUNT, TAX_TO_AMT, TAX_PERCENT, TAX_EMP_TYPE)"
						+ " VALUES(?,"
						+ bean.getFromYear() + "," + bean.getToYear()
						+ ",?,?,?,'S')";

				result = getSqlModel().singleExecute(insertSeniorQuery,
						modParam);

			}
			// Records save for dynamic rows : SENIOR End

			/*
			 * if (result) {
			 *//**
				 * Following code calculates the tax and updates tax process
				 */
			/*
			 * try { CommonTaxCalculationModel taxmodel = new
			 * CommonTaxCalculationModel(); taxmodel.initiate(context, session);
			 * logger.info("I m calling tax calculation method"); Object
			 * empList[][] = getSqlModel() .getSingleResult( "SELECT EMP_ID FROM
			 * HRMS_EMP_OFFC WHERE EMP_STATUS='S'"); Calendar cal =
			 * Calendar.getInstance(); cal.setTime(new Date()); int fromYear =
			 * Integer.parseInt(String.valueOf(cal .get(Calendar.YEAR))); int
			 * month = Integer.parseInt(String.valueOf(cal
			 * .get(Calendar.MONTH))); if (month <= 2) fromYear--; if (empList !=
			 * null && empList.length > 0) taxmodel.calculateTax(empList,
			 * String.valueOf(fromYear), String.valueOf(fromYear + 1));
			 * taxmodel.terminate(); } catch (Exception e) { logger
			 * .error("Exception in addTaxSlab() of TaxSlabModel while calling
			 * calculateTax : " + e); e.printStackTrace(); } }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}// end of updateTaxSlab method

	/**
	 * this method is used to display the report in the PDF format. It displays
	 * the list of slabs
	 * 
	 * @param taxSlab
	 * @param response
	 * @return
	 */
	public boolean getReport(TaxSlab taxSlab, HttpServletResponse response) {
		boolean flag = false;

		String reportType = "Pdf";

		String reportName = "TDS Slab Report";

		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, reportName);
		rg.addText("TDS Slab Report For The Year " + taxSlab.getFromYear()
				+ "-" + taxSlab.getToYear() + "", 1, 1, 0);
		rg.addText("", 0, 0, 0);

		/**
		 * this query is for retrieving the records of the Slab Type=Men
		 */
		String query = "select '',HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT,HRMS_TAX_SLAB.TAX_PERCENT  "
				+ "from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='M' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ taxSlab.getFromYear() + " and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ taxSlab.getToYear() + " order by HRMS_TAX_SLAB.tax_percent";

		Object data[][] = getSqlModel().getSingleResult(query);

		String[] colNames = { "Sr.No ", "From Amount", "To Amount",
				"Tax Percentage" };

		int[] cellWidth = { 10, 30, 30, 30 };

		int[] alignment = { 0, 2, 2, 2  };

		String rownum = "select rownum from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='M' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ taxSlab.getFromYear()
				+ " "
				+ "and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ taxSlab.getToYear() + " order by rownum desc";
		Object rownumLen[][] = getSqlModel().getSingleResult(rownum);

		if (data.length > 0) {
			for (int k = 0; k < rownumLen.length; k++) {
				data[k][0] = k + 1;

			}// end of loop
			rg.addText("TDS Slab For Men", 1, 0, 0);
			rg.tableBody(colNames, data, cellWidth, alignment);
			rg.addText("", 0, 0, 0);
			flag = true;
		}// end of if

		/**
		 * this query is for retrieving the records of the Slab Type=Women
		 */
		String query1 = "select '',HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT,HRMS_TAX_SLAB.TAX_PERCENT  "
				+ "from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='F' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ taxSlab.getFromYear() + " and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ taxSlab.getToYear() + " order by HRMS_TAX_SLAB.tax_percent";

		Object dataF[][] = getSqlModel().getSingleResult(query1);

		String[] colNamesF = { "Sr.No ", "From Amount", "To Amount",
				"Tax Percentage" };

		int[] cellWidthF = { 10, 30, 30, 30 };

		int[] alignmentF = { 0, 2, 2, 2 };

		String length = "select rownum from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='F' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ taxSlab.getFromYear()
				+ " "
				+ "and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ taxSlab.getToYear() + " order by rownum desc";
		Object len[][] = getSqlModel().getSingleResult(length);

		if (dataF.length > 0) {
			for (int k = 0; k < len.length; k++) {
				dataF[k][0] = k + 1;

			}// end of loop
			rg.addText("TDS Slab For Women", 1, 0, 0);
			rg.tableBody(colNamesF, dataF, cellWidthF, alignmentF);
			rg.addText("", 0, 0, 0);
			flag = true;
		}// end of if

		/**
		 * this query is for retrieving the records of the Slab Type=Senior
		 */
		String query2 = "select '',HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT,HRMS_TAX_SLAB.TAX_PERCENT  "
				+ "from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='S' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ taxSlab.getFromYear() + " and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ taxSlab.getToYear() + " order by HRMS_TAX_SLAB.tax_percent";

		Object dataS[][] = getSqlModel().getSingleResult(query2);

		String[] colNamesS = { "Sr.No ", "From Amount", "To Amount",
				"Tax Percentage" };

		int[] cellWidthS = { 10, 30, 30, 30 };

		int[] alignmentS = { 0, 2, 2, 2 };

		String srNo = "select rownum from HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_EMP_TYPE='S' and HRMS_TAX_SLAB.TAX_FROM_YEAR="
				+ taxSlab.getFromYear()
				+ " "
				+ "and HRMS_TAX_SLAB.TAX_TO_YEAR="
				+ taxSlab.getToYear() + " order by rownum desc";
		Object serialNo[][] = getSqlModel().getSingleResult(srNo);

		if (dataS.length > 0) {
			for (int k = 0; k < serialNo.length; k++) {
				dataS[k][0] = k + 1;

			}// end of loop
			rg.addText("TDS Slab For Sr.Citizen", 1, 0, 0);
			rg.tableBody(colNamesS, dataS, cellWidthS, alignmentS);
			flag = true;
		}// end of if
		rg.createReport(response);

		return flag;
	}// end of report method

	/**
	 * This method is used to check the Range of the slabs. Whether the slabs to
	 * be inserted are in the range or not. The same slab range cannot be
	 * inserted Or the slabs for the respective type cannot be inserted multiple
	 * in that respective year.
	 * 
	 * @param taxSlab
	 * @return
	 */
	public boolean chkRange(TaxSlab taxSlab) {
		Object[][] data = null;
		if (taxSlab.getTaxId() == null || taxSlab.getTaxId().equals("")
				|| taxSlab.getTaxId().equals("null")
				|| taxSlab.getTaxId().equals(null)) {
			/**
			 * This query is used to check whether the records for that Emp Type
			 * and for that year is present or not.
			 */
			String query = "SELECT HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT FROM HRMS_TAX_SLAB  where HRMS_TAX_SLAB.TAX_FROM_YEAR ="
					+ taxSlab.getFromYear()
					+ " and "
					+ " HRMS_TAX_SLAB.TAX_TO_YEAR= "
					+ taxSlab.getToYear()
					+ " and HRMS_TAX_SLAB.TAX_EMP_TYPE='"
					+ taxSlab.getType() + "' order by  HRMS_TAX_SLAB.TAX_FROM_AMOUNT";
			data = getSqlModel().getSingleResult(query);

		}// end of if
		else {
			String query = "SELECT HRMS_TAX_SLAB.TAX_FROM_AMOUNT,HRMS_TAX_SLAB.TAX_TO_AMT FROM HRMS_TAX_SLAB where  HRMS_TAX_SLAB.TAX_SLAB_ID not in ("
					+ taxSlab.getTaxId()
					+ ") "
					+ " and HRMS_TAX_SLAB.TAX_FROM_YEAR ="
					+ taxSlab.getFromYear()
					+ " and  HRMS_TAX_SLAB.TAX_TO_YEAR="
					+ taxSlab.getToYear()
					+ " and HRMS_TAX_SLAB.TAX_EMP_TYPE='"
					+ taxSlab.getType() + "' order by  HRMS_TAX_SLAB.TAX_FROM_AMOUNT";
			data = getSqlModel().getSingleResult(query);
		}// end of else
		for (int i = 0; i < data.length; i++) {
			if (Double.parseDouble(String.valueOf(taxSlab.getFrmAmount())) >= Double
					.parseDouble(String.valueOf(data[i][0]))
					&& Double.parseDouble(String
							.valueOf(taxSlab.getFrmAmount())) <= Double
							.parseDouble(String.valueOf(data[i][1]))) {
				return false;
			}// end of if
			else if (Double.parseDouble(String.valueOf(taxSlab.getToAmount())) >= Double
					.parseDouble(String.valueOf(data[i][0]))) {
				if (Double.parseDouble(String.valueOf(taxSlab.getToAmount())) >= Double
						.parseDouble(String.valueOf(data[i][0]))
						&& Double.parseDouble(String.valueOf(taxSlab
								.getToAmount())) <= Double.parseDouble(String
								.valueOf(data[i][1]))) {
					return false;
				}// end of if
			}// end of else if
		}// end of loop
		return true;
	}// end of chkRange method.

	public void getTaxSlabDataList(TaxSlab bean, HttpServletRequest request) {

		try {
			// String query = " SELECT DISTINCT TAX_FROM_YEAR, TAX_TO_YEAR,
			// DECODE(TAX_EMP_TYPE,'M','Men','F','Women','S','Senior'),
			// TAX_EMP_TYPE FROM HRMS_TAX_SLAB ORDER BY TAX_FROM_YEAR DESC";

			String query = " SELECT DISTINCT HRMS_TAX_SLAB.TAX_FROM_YEAR, HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB ORDER BY HRMS_TAX_SLAB.TAX_FROM_YEAR DESC";

			Object[][] objTaxSlabData = getSqlModel().getSingleResult(query);
			
			///////////////
			
			if (objTaxSlabData != null && objTaxSlabData.length > 0) {
				bean.setModeLength("true");
				bean.setTotalRecords(String.valueOf(objTaxSlabData.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						objTaxSlabData.length, 20);
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
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					TaxSlab empTaxSlab = new TaxSlab();
					empTaxSlab.setTaxFromYearItt(String
							.valueOf(objTaxSlabData[i][0]));// from year
					empTaxSlab
							.setTaxToYearItt(String.valueOf(objTaxSlabData[i][1]));// to
					// year
					/*
					 * empTaxSlab.setTaxEmpTypeItt(String
					 * .valueOf(objTaxSlabData[i][2]));// emp type as Women or Men
					 * or Senior empTaxSlab.setTaxEmpGenderItt(String
					 * .valueOf(objTaxSlabData[i][3]));// emp type as M or F or S
					 */List.add(empTaxSlab);
				}// end of loop
				bean.setIteratorlist(List);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean deleteCheckedRecords(TaxSlab taxSlab, String[] fromYearCode,
			String[] toYearCode) {

		logger
				.info("################ IN DELETE CHECKED RECORDS ######################");

		boolean result = false;
		String deleteQuery;
		try {
			if (fromYearCode != null && !fromYearCode.equals("")
					&& fromYearCode.length > 0) {
				for (int i = 0; i < fromYearCode.length; i++) {
					deleteQuery = " DELETE FROM HRMS_TAX_SLAB WHERE "
							+ " TAX_FROM_YEAR= " + fromYearCode[i]
							+ " AND TAX_TO_YEAR= " + toYearCode[i];
						//	+ " AND TAX_EMP_TYPE IN ('M','F','S') ";

					result = getSqlModel().singleExecute(deleteQuery);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean checkFromYearExit(TaxSlab taxSlab) {
		System.out.println("taxSlab.getFromYear()===" + taxSlab.getFromYear());
		String check = "SELECt * FROm HRMS_TAX_SLAB where HRMS_TAX_SLAB.TAX_FROM_YEAR ='"
				+ taxSlab.getFromYear() + "' ";
		Object[][] obj = getSqlModel().getSingleResult(check);
		if (obj != null && obj.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * to check null value
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean deleteSelectedRecords(TaxSlab taxSlab, String fromYearCode,
			String toYearCode) {
		logger
		.info("################ IN DELETE CHECKED RECORDS ######################");

	boolean result = false;
	String deleteQuery;
	try {
		if (fromYearCode != null && !fromYearCode.equals("")
				&& fromYearCode.length() > 0) {
			for (int i = 0; i < fromYearCode.length(); i++) {
				deleteQuery = " DELETE FROM HRMS_TAX_SLAB WHERE "
						+ " TAX_FROM_YEAR= " + fromYearCode
						+ " AND TAX_TO_YEAR= " + toYearCode;
					//	+ " AND TAX_EMP_TYPE IN ('M','F','S') ";
	
				result = getSqlModel().singleExecute(deleteQuery);
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return result;
	}

}
