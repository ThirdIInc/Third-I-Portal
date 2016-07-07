package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.SoftwareRequestBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381
 *
 */
public class SoftwareRequestModel extends ModelBase {
	/**
	 * Set Page Index as 1.
	 */
	private static final String PAGE_NO = "1";
	
	
	/**
	 * purpose - Generating the list on On load.
	 * @param softwarereqBean - getting & setting paging value.
	 * @param request - set total page & page no attributes.
	 */
	public void initialData(final SoftwareRequestBean softwarereqBean, final HttpServletRequest request) {

		final Object[][] regData = this.getSqlModel().getSingleResult(this.getQuery(3));

		if (regData != null && regData.length > 0) {
			softwarereqBean.setModeLength("true");

			softwarereqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final String[] pageIndex = Utility.doPaging(softwarereqBean.getMyPage(),
					regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = SoftwareRequestModel.PAGE_NO;
				pageIndex[3] = SoftwareRequestModel.PAGE_NO;

			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (SoftwareRequestModel.PAGE_NO.equals(pageIndex[4])) {
				softwarereqBean.setMyPage(SoftwareRequestModel.PAGE_NO);
			}
			
			final List<SoftwareRequestBean> lst = new ArrayList<SoftwareRequestBean>();
	
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {

				final SoftwareRequestBean bean = new SoftwareRequestBean();
				bean.setSoftwareReqCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setSoftwareName(this.checkNull(String.valueOf(regData[i][1])));

				lst.add(bean);
			}
			
			softwarereqBean.setSoftwareReqList(lst);
		} 	else {

			softwarereqBean.setSoftwareReqList(null);

		}
	}

	/**
	 * purpose - Checking Null values.
	 * @param result -check null result.
	 * @return String.
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		}  else {
			return result;
		}
	}
	
	/**
	 * purpose -For inserting values into DB.
	 * @param softwarereqBean - used to get form data. 
	 * @return true/false.
	 */
	public boolean insertSoftReqData(final SoftwareRequestBean softwarereqBean) {

		if (!this.checkDuplicateAdd(softwarereqBean)) {

			final Object [][] addObj = new Object[1][3];

			final String query1 = "SELECT NVL(MAX(SPECIAL_SOFTWARE_ID),0)+1 FROM  HRMS_D1_SPECIAL_SW_REQ";
			final 	Object[][] softId = this.getSqlModel().getSingleResult(query1);

			addObj[0][0] = this.checkNull(String.valueOf(softId[0][0]));
			addObj[0][1] = softwarereqBean.getSoftwareCode().trim();
			addObj[0][2] = softwarereqBean.getSoftwareName().trim();

			/**
			 * dta display purpose.
			 */
			/*for (int i = 0; i < addObj.length; i++) {
				for (int j = 0; j < addObj[i].length; j++) {
					System.out.println("insertObj[" + i + "][" + j + "]  " + addObj[i][j]);
				}
			}*/

			softwarereqBean.setSoftwareReqCode(String.valueOf(softId[0][0]));

			return this.getSqlModel().singleExecute(this.getQuery(1), addObj);
		}   else {
			return false;
		}
	}

	/* for checking duplicate entry of record during insertion*/

	/**
	 * purpose - check duplicate record durring insertion.
	 * @param bean - used to get software code for duplication. 
	 * @return true/false.
	 */
	public boolean checkDuplicateAdd(final SoftwareRequestBean bean) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_D1_SPECIAL_SW_REQ WHERE UPPER(SPECIAL_SOFTWARE_CODE) LIKE '" + bean.getSoftwareCode().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**  */
	/**
	 * purpose -  Modifying the record.
	 * @param bean - used to get form data to update 
	 * @return true/false.
	 */
	public boolean updateSoftReqData(final SoftwareRequestBean bean) {
		if (!this.checkDuplicateUpdate(bean)) {
			final Object [][] updateObj = new Object[1][3];

			updateObj[0][0] = bean.getSoftwareCode().trim();
			updateObj[0][1] = bean.getSoftwareName().trim();
			updateObj[0][2] = bean.getSoftwareReqCode().trim();
			return this.getSqlModel().singleExecute(this.getQuery(2), updateObj);
		} else {
			return false;
		}
	}

	
	/**
	 * purpose - checking duplicate entry of record during updation.
	 * @param bean -used to get software code for duplication. 
	 * @return true/false.
	 */
	public boolean checkDuplicateUpdate(final SoftwareRequestBean bean) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_D1_SPECIAL_SW_REQ WHERE UPPER(SPECIAL_SOFTWARE_CODE) LIKE '" + bean.getSoftwareCode().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * purpose - To set the data from list and setting those data in respective.
	 * @param softwarereqBean - used to set form data into respective fields.
	 */
	public void editSoftwareReqData(final SoftwareRequestBean softwarereqBean) {
		try {
			
			final String query = " SELECT SPECIAL_SOFTWARE_CODE,SPECIAL_SOFTWARE_NAME FROM HRMS_D1_SPECIAL_SW_REQ WHERE  SPECIAL_SOFTWARE_ID= " + softwarereqBean.getSoftwareReqCode();
			final Object[][] data = this.getSqlModel().getSingleResult(query);
			softwarereqBean.setSoftwareCode(String.valueOf(data[0][0]));
			softwarereqBean.setSoftwareName(String.valueOf(data[0][1]));
		}   catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean .
	 * @param code - multiple code for deletion.
	 * @return true/false.
	 */
	public boolean deleteCheckedRecords(final SoftwareRequestBean bean, final String[] code) {
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
		}  	else {
			result = true;
			return result;
		}
	}

	/**
	 * purpose - delete pertcular record only.
	 * @param softwarereqBean - used to get software code to delete single record.
	 * @return true/false.
	 */
	public boolean delete(final SoftwareRequestBean softwarereqBean) {
		boolean result = false;
		
		final String deleteId = softwarereqBean.getSoftwareReqCode();

		final String delQuery = "DELETE FROM HRMS_D1_SPECIAL_SW_REQ WHERE SPECIAL_SOFTWARE_ID=" + deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

}
