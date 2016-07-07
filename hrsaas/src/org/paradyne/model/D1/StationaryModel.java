package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.StationaryBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381
 *
 */
public class StationaryModel extends ModelBase {
	/**
	 * Set Page as 1.
	 */
	private static final String PAGE_NO_1 = "1";
	/**
	 * Set Quotes.
	 */
	private static final String QUOTES = "'";
	
	
	
	
	
	/**
	 * purpose - displaying list.
	 * @param stationarybean - used to get & set mypage also set StationaryList. 
	 * @param request - set total page & page no attribute.
	 */
	public void initialData(final StationaryBean stationarybean, final HttpServletRequest request) {

		final Object[][] regData = this.getSqlModel().getSingleResult(this.getQuery(3));

		if (regData != null && regData.length > 0) {
			stationarybean.setModeLength("true");

			stationarybean.setTotalNoOfRecords(String.valueOf(regData.length));

			final String[] pageIndex = Utility.doPaging(stationarybean.getMyPage(),
					regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = StationaryModel.PAGE_NO_1;
				pageIndex[3] = StationaryModel.PAGE_NO_1;

			}
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (StationaryModel.PAGE_NO_1.equals(pageIndex[4])) {
				stationarybean.setMyPage(StationaryModel.PAGE_NO_1);
			}
			final List<StationaryBean> list = new ArrayList<StationaryBean>();
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {

				final StationaryBean bean = new StationaryBean();
				bean.setStationaryhiddenCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setStationaryName(this.checkNull(String.valueOf(regData[i][1])));
				list.add(bean);
			}
			
			stationarybean.setStationaryList(list);
		} 	else {

			stationarybean.setStationaryList(null);

		}
	}

	/**
	 * purpose - Checking Null Values. 
	 * @param result - contains data of respective field.
	 * @return String.
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}
	
	/**
	 * purpose - inserting values into DB.
	 * @param stationarybean - used to get form data.
	 * @return true/ false.
	 */
	public boolean insertImprintData(final StationaryBean stationarybean) {
		
		if (!this.checkDuplicateAdd(stationarybean)) {
			final Object [][] addObj = new Object[1][3];

			final String query1 = "SELECT NVL(MAX(STATIONARY_ID),0)+1 FROM  HRMS_D1_STATIONARY";
			final Object[][] stationaryId = this.getSqlModel().getSingleResult(query1);
		
		
			addObj[0][0] = this.checkNull(String.valueOf(stationaryId[0][0]));
			addObj[0][1] = stationarybean.getStationaryCode().trim();
			addObj[0][2] = stationarybean.getStationaryName().trim();
		
		/*for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[i].length; j++) {
				System.out.println("insertObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}*/

			stationarybean.setStationaryhiddenCode(String.valueOf(stationaryId[0][0]));
		
			return this.getSqlModel().singleExecute(this.getQuery(1), addObj);
		} 	else {
			return false;	
		}	
	}

	/**
	 * purpose - checking duplicate value during insertion.
	 * @param bean - used to get stationary code during insertion.
	 * @return true / false, entered data.
	 */
	public boolean checkDuplicateAdd(final StationaryBean bean) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_D1_STATIONARY WHERE UPPER(STATIONARY_CODE) LIKE '" 	+ bean.getStationaryCode().trim().toUpperCase() + StationaryModel.QUOTES;
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}		

	/**
	 * purpose - Modifying the record.
	 * @param bean - get form data. 
	 * @return true / false , updated data.
	 */
	public boolean updateImprintData(final StationaryBean bean) {
		if (!this.checkDuplicateUpdate(bean)) {
		
			final Object[][] updateObj = new Object[1][3];

			updateObj[0][0] = bean.getStationaryCode().trim();
			updateObj[0][1] = bean.getStationaryName().trim();
			updateObj[0][2] = bean.getStationaryhiddenCode().trim();
			return this.getSqlModel().singleExecute(this.getQuery(2), updateObj);
		}   else {
			return false;	
		}	
	
	}

/**
 * purpose - for checking duplicate entry of record during updation.
 * @param bean - used to get stationary code during updation.
 * @return true/false.
 */
	public boolean checkDuplicateUpdate(final StationaryBean bean) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_D1_STATIONARY WHERE UPPER(STATIONARY_CODE) LIKE '" + bean.getStationaryCode().trim().toUpperCase() + StationaryModel.QUOTES;
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}	

	/**
	 * purpose - Edit respective fields.
	 * @param stationarybean - set form data.
	 */
	public void editSoftwareReqData(final StationaryBean stationarybean) {

		try {
			
			final String query = " SELECT STATIONARY_CODE,STATIONARY_NAME FROM HRMS_D1_STATIONARY WHERE  STATIONARY_ID= " + stationarybean.getStationaryhiddenCode();
			final Object[][] data = this.getSqlModel().getSingleResult(query);
			stationarybean.setStationaryCode(String.valueOf(data[0][0]));
			stationarybean.setStationaryName(String.valueOf(data[0][1]));
			
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * purpose - Delete multiple records.
	 * @param bean . 
	 * @param code - Contains codes for deletion.
	 * @return true/false.
	 */
	public boolean deleteCheckedRecords(final StationaryBean  bean, final String[] code) {
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
	 * purpose - delete single record.
	 * @param stationarybean - used to get code for deletion. 
	 * @return boolean.
	 */
	public boolean delete(final StationaryBean stationarybean) {
		boolean result = false;
		
		final String deleteId = stationarybean.getStationaryhiddenCode();

		final String delQuery = "DELETE FROM HRMS_D1_STATIONARY WHERE STATIONARY_ID=" + deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}
	

}
