package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.EthinicityBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381.
 *
 */
public class EthinicityModel extends ModelBase {
	/**
	 * Set 1.
	 */
	private static final  String DOUBLE_QUOTE_ONE = "1";
	

	/**
	 * purpose - Generating the EthicList on onload.
	 * @param ethinicity - used to get & set MyPage value.
	 * @param request - used to set total page & page no attributes.
	 */
	public void initialData(final EthinicityBean ethinicity, final HttpServletRequest request) {

		final Object[][] regData = this.getSqlModel().getSingleResult(this.getQuery(3));

		if (regData != null && regData.length > 0) {
			ethinicity.setModeLength("true");

			ethinicity.setTotalNoOfRecords(String.valueOf(regData.length));

			final String[] pageIndex = Utility.doPaging(ethinicity.getMyPage(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = EthinicityModel.DOUBLE_QUOTE_ONE;
				pageIndex[3] = EthinicityModel.DOUBLE_QUOTE_ONE;
			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (EthinicityModel.DOUBLE_QUOTE_ONE.equals(pageIndex[4])) {
				ethinicity.setMyPage(EthinicityModel.DOUBLE_QUOTE_ONE);
			}
			final List<EthinicityBean> list = new ArrayList<EthinicityBean>();

			ethinicity.setEthicListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {

				final EthinicityBean bean = new EthinicityBean();
				bean.setEthicCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setEthinicity(this.checkNull(String.valueOf(regData[i][1])));
				list.add(bean);
			}
			
			ethinicity.setEthicList(list);
		}   else {

			ethinicity.setEthicList(null);

		}
	}
	
	/**
	 * purpose - Check Null Functionality.
	 * @param result Contains string data to be checked.
	 * @return String String after checking null
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} 	else {
			return result;
		}
	}
	
	/**
	 * purpose - For inserting record into DB.
	 * @param ethinicity - used to get form data.
	 * @return true/false.
	 */
	public boolean insert(final EthinicityBean ethinicity) {
		if (!this.checkDuplicateAdd(ethinicity)) {

			final Object [][] addObj = new Object[1][2];
			final String query1 = "SELECT NVL(MAX(ETHIC_CODE),0)+1 FROM  HRMS_D1_ETHIC";
			final Object[][] softId = this.getSqlModel().getSingleResult(query1);

			addObj[0][0] = this.checkNull(String.valueOf(softId[0][0]));
			addObj[0][1] = ethinicity.getEthinicity().trim();

			ethinicity.setEthicCode(String.valueOf(softId[0][0]));
			return this.getSqlModel().singleExecute(this.getQuery(1), addObj);
		}     else {

			return false;
		}

	}

	/**
	 * purpose - Checking duplicate record during insertion.
	 * @param bean - get Ethinicity.
	 * @return true/false.
	 */
	public boolean checkDuplicateAdd(final EthinicityBean bean) {
		boolean result = false;
		final String query = "SELECT ETHINICITY FROM HRMS_D1_ETHIC WHERE UPPER(ETHINICITY) LIKE '" + bean.getEthinicity().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}


	/**
	 * purpose - Modifing the record.
	 * @param bean - used to get for updation.
	 * @return true/false.
	 */
	public boolean update(final EthinicityBean bean) {
		if (!this.checkDuplicateUpdate(bean)) {

			final Object [][]  updateObj = new Object[1][2];

			updateObj[0][0] = bean.getEthinicity().trim();
			updateObj[0][1] = bean.getEthicCode().trim();
			return this.getSqlModel().singleExecute(this.getQuery(2), updateObj);
		}    else {
			return false;
		}
	}

	/**
	 * purpose - for checking duplicate entry of record during updation.
	 * @param bean - to get Ethinicity for duplication check.
	 * @return true/false.
	 */

	public boolean checkDuplicateUpdate(final EthinicityBean bean) {
		boolean result = false;
		final String query = "SELECT ETHINICITY FROM HRMS_D1_ETHIC WHERE UPPER(ETHINICITY) LIKE '" + bean.getEthinicity().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * purpose - Edit respective record.
	 * @param ethinicity - used to get EthicCode to edit.
	 */
	public void editBusinessData(final EthinicityBean ethinicity) {
		try {
			
			final String query = " SELECT ETHINICITY FROM HRMS_D1_ETHIC  WHERE ETHIC_CODE= " + ethinicity.getEthicCode();

			final Object[][] data = this.getSqlModel().getSingleResult(query);

			ethinicity.setEthinicity(String.valueOf(data[0][0]));

		}  catch (final Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * @param bean .
	 * @param code - unique code for deletion of multiple records.
	 * @return true/false.
	 */
	public boolean deleteCheckedRecords(final EthinicityBean bean, final String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!"".equals(code[i])) {

					final Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = this.getSqlModel().singleExecute(this.getQuery(4), delete);
					if (!result) 	{
						count++;
					}
				}
			}
		}
		if (count != 0) {
			result = false;
			return result;
		}  	else {
			result = true;
			return result;
		}
	}
	/**
	 * @param ethinicity - used to get EthicCode for deletion of record.
	 * @return true/false.
	 */
	public boolean delete(final EthinicityBean ethinicity) {
		boolean result = false;
		
		final String deleteId = ethinicity.getEthicCode();

		final String delQuery = "DELETE FROM HRMS_D1_ETHIC WHERE ETHIC_CODE=" + deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

}
