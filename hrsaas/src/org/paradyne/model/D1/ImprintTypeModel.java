package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ImprintTypeBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381
 *
 */
public class ImprintTypeModel extends ModelBase {
	/**
	 * Set 1.
	 */
	private static final String DOUBLE_QUOTE_ONE = "1";
	
	
	/**
	 * purpose - Generating the records list on on load.
	 * @param imprintBean - get & set MyPage value.
	 * @param request - used to set paging attributes.
	 */
	public void initialData(final ImprintTypeBean imprintBean,	final HttpServletRequest request) {

		final Object[][] regData = this.getSqlModel().getSingleResult(this.getQuery(3));

		if (regData != null && regData.length > 0) {
			imprintBean.setModeLength("true");

			imprintBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final String[] pageIndex = Utility.doPaging(imprintBean.getMyPage(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = ImprintTypeModel.DOUBLE_QUOTE_ONE;
				pageIndex[3] = ImprintTypeModel.DOUBLE_QUOTE_ONE;

			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (ImprintTypeModel.DOUBLE_QUOTE_ONE.equals(pageIndex[4])) {
				imprintBean.setMyPage(ImprintTypeModel.DOUBLE_QUOTE_ONE);
			}
			final List<ImprintTypeBean> list = new ArrayList<ImprintTypeBean>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {

				final ImprintTypeBean bean = new ImprintTypeBean();
				bean.setImprintTypeCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setImprintName(this.checkNull(String.valueOf(regData[i][1])));
				list.add(bean);
			}
			imprintBean.setImprintTypeList(list);
		}  	else {
			imprintBean.setImprintTypeList(null);
		}
	}

	/**
	 * @param result - checks that data contains null value or not.
	 * @return String.
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
	 * @param imprintBean - used to get form data.
	 * @return true/false.
	 */
	public boolean insertImprintData(final ImprintTypeBean imprintBean) {
		
		if (!this.checkDuplicateAdd(imprintBean)) {
		
			final Object [][] addObj = new Object[1][3];
	
			final String query1 = "SELECT NVL(MAX(IMPRINT_ID),0)+1 FROM  HRMS_D1_IMPRINT_TYPE";
			final Object[][] impId = this.getSqlModel().getSingleResult(query1);
		
			addObj[0][0] = this.checkNull(String.valueOf(impId[0][0]));
			addObj[0][1] = imprintBean.getImprintCode().trim();
			addObj[0][2] = imprintBean.getImprintName().trim();
		
		/**
		 * Data Displaying Purpose.
		 */
		/*for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[i].length; j++) {
				System.out.println("insertObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}*/

			imprintBean.setImprintTypeCode(String.valueOf(impId[0][0]));
		
			return this.getSqlModel().singleExecute(this.getQuery(1), addObj);
		} else {
			
			return false;
		}		
	}

	/**
	 * purpose - for checking duplicate entry of record during insertion.
	 * @param bean - used to get imprint code to check for duplicate data.
	 * @return true/false.
	 */
	public boolean checkDuplicateAdd(final ImprintTypeBean bean) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_D1_IMPRINT_TYPE WHERE UPPER(IMPRINT_CODE) LIKE '" + bean.getImprintCode().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	
	/**
	 * purpose - Modifying the record.
	 * @param bean - used to get form data.
	 * @return true/false.
	 */
	public boolean updateImprintData(final ImprintTypeBean bean) {
		if (!this.checkDuplicateUpdate(bean)) {
		
			final Object [][] updateObj = new Object[1][3];

			updateObj[0][0] = bean.getImprintCode().trim();
			updateObj[0][1] = bean.getImprintName().trim();
			updateObj[0][2] = bean.getImprintTypeCode().trim();
			return this.getSqlModel().singleExecute(this.getQuery(2), updateObj);
		} 	else {
			
			return false;
		}		
	}
	
	
	/**
	 * purpose - for checking duplicate entry of record during updation.
	 * @param bean - bean instance.
	 * @return boolean.
	 */
	public boolean checkDuplicateUpdate(final ImprintTypeBean bean) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_D1_IMPRINT_TYPE WHERE UPPER(IMPRINT_CODE) LIKE '" + bean.getImprintCode().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	} 

	/**
	 * purpose - for selecting the data from list and setting those data in respective  fields.
	 * @param imprintBean - used to set form data.
	 */
	public void editSoftwareReqData(final ImprintTypeBean imprintBean) {

		try {
			
			final String query = " SELECT IMPRINT_CODE,IMPRINT_NAME FROM HRMS_D1_IMPRINT_TYPE WHERE  IMPRINT_ID= " + imprintBean.getImprintTypeCode();
			final Object[][] data = this.getSqlModel().getSingleResult(query);
			imprintBean.setImprintCode(String.valueOf(data[0][0]));
			imprintBean.setImprintName(String.valueOf(data[0][1]));
			
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param bean .
	 * @param code - multiple codes for deletion.
	 * @return true/false.
	 */
	public boolean deleteCheckedRecords(final ImprintTypeBean  bean, final String[] code) {
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
	 * purpose - delete sing record.
	 * @param imprintBean - used to get imprint code for delete single record.
	 * @return true/false.
	 */
	public boolean delete(final ImprintTypeBean imprintBean) {
		boolean result = false;
		final String deleteId = imprintBean.getImprintTypeCode();
		final String delQuery = "DELETE FROM HRMS_D1_IMPRINT_TYPE WHERE IMPRINT_ID=" + deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

}
