package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ITApplicationBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381
 *
 */
public class ITApplicationModel extends ModelBase {
	/**
	 * Set "1" value.
	 */
	private static final  String SINGLE_QUOTE_1 =  "1";
	
	
	/**
	 * purpose - used to display application list on on load . 
	 * @param itBean - used to get & set paging fields & list.
	 * @param request - used to set totalPage & pageNo.
	 */
	public void initialData(final ITApplicationBean itBean, final HttpServletRequest request) {

		final Object[][] refData = this.getSqlModel().getSingleResult(this.getQuery(3));

		if (refData != null && refData.length > 0) {
			itBean.setModeLength("true");

			itBean.setTotalNoOfRecords(String.valueOf(refData.length));

			final String[] pageIndex = Utility.doPaging(itBean.getMyPage(), refData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = ITApplicationModel.SINGLE_QUOTE_1;
				pageIndex[3] = ITApplicationModel.SINGLE_QUOTE_1;

			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (ITApplicationModel.SINGLE_QUOTE_1.equals(pageIndex[4])) {
				itBean.setMyPage(ITApplicationModel.SINGLE_QUOTE_1);
			}
			final List<ITApplicationBean> list = new ArrayList<ITApplicationBean>();

			itBean.setItListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {

				final ITApplicationBean bean = new ITApplicationBean();
				bean.setItCode(this.checkNull(String.valueOf(refData[i][0])));
				bean.setApplicationName(this.checkNull(String.valueOf(refData[i][1])));
				bean.setApplicationActive(this.checkNull(String.valueOf(refData[i][2])));
				list.add(bean);
			}
			
			itBean.setItList(list);
		} 	else {

			itBean.setItList(null);

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
		} else {
			return result;
		}
	}

	/* For inserting record into DB */

	/**
	 * purpose - used to insert application data in DB. 
	 * @param itBean - used to get application data.
	 * @return true/false.
	 */
	public boolean insert(final ITApplicationBean itBean) {
		
		final Object [][] addObj = new Object[1][4];
		final String query1 = "SELECT NVL(MAX(APPLN_ID),0)+1 FROM  HRMS_D1_IT_SEC_APPLICATIONS";
		final Object[][] itValue = this.getSqlModel().getSingleResult(query1);

		addObj[0][0] = this.checkNull(String.valueOf(itValue[0][0]));
		addObj[0][1] = itBean.getApplicationName().trim();
		addObj[0][2] = itBean.getApplicationSection().trim();
		addObj[0][3] = itBean.getApplicationActive().trim();

		itBean.setItCode(String.valueOf(itValue[0][0]));
		return this.getSqlModel().singleExecute(this.getQuery(1), addObj);
	}

	/* Modifing the record */

	/**
	 * purpose - used to update record.
	 * @param bean - used to get application data for updation.
	 * @return true/false.
	 */
	public boolean update(final ITApplicationBean bean) {
		if (!this.checkDuplicateUpdate(bean)) {
			final Object [][] updateObj = new Object[1][4];

			updateObj[0][0] = bean.getApplicationName().trim();
			updateObj[0][1] = bean.getApplicationSection().trim();
			updateObj[0][2] = bean.getApplicationActive().trim();
			updateObj[0][3] = bean.getItCode().trim();
			return this.getSqlModel().singleExecute(this.getQuery(2), updateObj);
		}  else {
		
			return false;
		}

	}
	
	

	/**
	 * purpose - used to check duplicate Application Name.
	 * @param bean - used to get application name.
	 * @return true/false.
	 */
	public boolean checkDuplicateName(final ITApplicationBean bean) {
		boolean result = false;
		final String query = "SELECT APPLN_NAME FROM HRMS_D1_IT_SEC_APPLICATIONS WHERE UPPER(APPLN_NAME) LIKE '" + bean.getApplicationName().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	
	/**
	 * purpose - used to check duplicate ApplicationSection.
	 * @param bean - used to get section name.
	 * @return true/false.
	 */
	public boolean checkDuplicateSec(final ITApplicationBean bean) {
		boolean result = false;
		final String query = "SELECT APPLN_SECTION FROM HRMS_D1_IT_SEC_APPLICATIONS WHERE UPPER(APPLN_SECTION) LIKE '" + bean.getApplicationSection().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	

	/**
	 * purpose - use for  checking duplicate entry of during updation.
	 * @param bean - used to  get application name.
	 * @return true/false.
	 */
	public boolean checkDuplicateUpdate(final ITApplicationBean bean) {
		boolean result = false;
		final String query = "SELECT APPLN_NAME FROM HRMS_D1_IT_SEC_APPLICATIONS WHERE UPPER(APPLN_NAME) LIKE '" + bean.getApplicationName().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	
	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */

	/**
	 * purpose - Edit application data.
	 * @param itBean - used to get  application id.
	 */
	public void editBusinessData(final ITApplicationBean itBean) {

		try {
			String query = " SELECT APPLN_NAME,NVL(APPLN_SECTION,''), APPLN_ACTIVE FROM HRMS_D1_IT_SEC_APPLICATIONS  WHERE APPLN_ID= " + itBean.getItCode();
			query += "order by APPLN_NAME";
			
			
			final Object[][] data = this.getSqlModel().getSingleResult(query);

			itBean.setApplicationName(String.valueOf(data[0][0]));
			itBean.setApplicationSection(String.valueOf(data[0][1]));
			itBean.setApplicationActive(String.valueOf(data[0][2]));

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param code - code contains multiple code to delete records.
	 * @return true/false.
	 */
	public boolean deleteCheckedRecords(final String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!"".equals(code[i])) {

					final Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = this.getSqlModel().singleExecute(this.getQuery(4), delete);
					if (!result) {
						count++;
					}
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


	/**
	 * purpose - delete single record.
	 * @param itBean - used to get hidden unique hidden code. 
	 * @return true/false.
	 */
	public boolean delete(final ITApplicationBean itBean) {
		boolean result = false;
		
		final String deleteId = itBean.getItCode();

		final String delQuery = "DELETE FROM HRMS_D1_IT_SEC_APPLICATIONS WHERE APPLN_ID=" + deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

}
