package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381
 *
 */
public class BusinessUnitModel extends ModelBase {
	 /**
     * Report Type PDF.
     */
	public static final String PAGE_1 = "1"; 
	/**
	 * Set Quotes.
	 */
	public static final String QUOTES = "'";
	
	/**
	 * purpose - Generating the list on On load.
	 * @param businessUnitBean - getting & setting my page & List.  
	 * @param request - used to set total page & page no attribute.
	 */
	public void initialData(final BusinessUnitBean businessUnitBean,	final HttpServletRequest request) {
		final Object[][] regData = this.getSqlModel().getSingleResult(this.getQuery(3));
		if (regData != null && regData.length > 0) {
			businessUnitBean.setModeLength("true");
			businessUnitBean.setTotalNoOfRecords(String.valueOf(regData.length));
			final String[] pageIndex = Utility.doPaging(businessUnitBean.getMyPage(),
					regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = BusinessUnitModel.PAGE_1;
				pageIndex[3] = BusinessUnitModel.PAGE_1;
			}
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (BusinessUnitModel.PAGE_1.equals(pageIndex[4])) {
				businessUnitBean.setMyPage(BusinessUnitModel.PAGE_1);
			}
			final List<BusinessUnitBean> list = new ArrayList<BusinessUnitBean>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				final BusinessUnitBean bean = new BusinessUnitBean();
				bean.setBusinessCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setUnitName(this.checkNull(String.valueOf(regData[i][1])));		
				list.add(bean);
			}		
			businessUnitBean.setBusinessUnitList(list);
		} else {
			businessUnitBean.setBusinessUnitList(null);
		}
	}

	//Check Null Function//	

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

	
	/**
	 * purpose - For inserting records into Data Base.
	 * @param businessUnitBean - bean instance.
	 * @return true/false.
	 */
	public boolean insertBusinessData(final BusinessUnitBean businessUnitBean) {
		if (!this.checkDuplicateAdd(businessUnitBean)) {
			final Object [][] addObj  = new Object[1][4];
			final String query1 = "SELECT NVL(MAX(BUSS_UNIT_ID),0)+1 FROM  HRMS_D1_BUSSINESS_UNIT";
			final Object[][] bussUnitId = this.getSqlModel().getSingleResult(query1);
			addObj[0][0] = this.checkNull(String.valueOf(bussUnitId[0][0]));
			addObj[0][1] = businessUnitBean.getUnitCode().trim();
			addObj[0][2] = businessUnitBean.getUnitName().trim();
			addObj[0][3] = businessUnitBean.getEmpID().trim();
			businessUnitBean.setBusinessCode(String.valueOf(bussUnitId[0][0]));
			return this.getSqlModel().singleExecute(this.getQuery(1), addObj); 
		}	else {

			return false;
		}
	}


	/**
	 * purpose - Checking duplicate record during insertion.
	 * @param bean - get unit code.
	 * @return true/false.
	 */
	public boolean checkDuplicateAdd(final BusinessUnitBean bean) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_D1_BUSSINESS_UNIT WHERE UPPER(BUSS_UNIT_CODE) LIKE '" + bean.getUnitCode().trim().toUpperCase() + BusinessUnitModel.QUOTES;
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	
	/**
	 * purpose - Modifying record.
	 * @param bean - get application data.
	 * @return true/false..
	 */
	public boolean updateBusinessData(final BusinessUnitBean bean) {
		if (!this.checkDuplicateUpdate(bean)) {
			final Object [][] updateObj = new Object[1][4];
			updateObj[0][0] = bean.getUnitCode().trim();
			updateObj[0][1] = bean.getUnitName().trim();
			updateObj[0][2] = bean.getEmpID().trim();
			updateObj[0][3] = bean.getBusinessCode().trim();			
			return this.getSqlModel().singleExecute(this.getQuery(2), updateObj);	
		}  	else {
			return false;
		}
	}

	/**
	 * purpose - for checking duplicate entry of record during updation.
	 * @param bean - to get unit code for duplication check.
	 * @return true/false.
	 */
	public boolean checkDuplicateUpdate(final BusinessUnitBean bean) {
		boolean result = false;
		final String query = "SELECT BUSS_UNIT_CODE FROM HRMS_D1_BUSSINESS_UNIT"
							+ " WHERE UPPER(BUSS_UNIT_CODE) LIKE '" + bean.getUnitCode().trim().toUpperCase()+"'"
							+ " AND BUSS_UNIT_ID NOT IN  ("+ bean.getBusinessCode()+")";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	
	/**
	 * purpose - Edit respective record.
	 * @param businessUnitBean bean instance.
	 */
	public void editBusinessData(final BusinessUnitBean businessUnitBean) {
		try {			
			final String query = " SELECT BUSS_UNIT_CODE,BUSS_UNIT_NAME,EMP_TOKEN,"
								+ " EMP_FNAME||' '||EMP_MNAME ||' '||EMP_LNAME, EMP_ID  FROM HRMS_D1_BUSSINESS_UNIT "  
								+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSSINESS_UNIT.BUSS_EMP_ID)"
								+ " WHERE BUSS_UNIT_ID= " + businessUnitBean.getBusinessCode();
			final Object[][] data = this.getSqlModel().getSingleResult(query);
			businessUnitBean.setUnitCode(String.valueOf(data[0][0]));
			businessUnitBean.setUnitName(String.valueOf(data[0][1]));
			businessUnitBean.setEmpToken(checkNull(String.valueOf(data[0][2])));
			businessUnitBean.setEmpName(checkNull(String.valueOf(data[0][3])));			
			businessUnitBean.setEmpID(checkNull(String.valueOf(data[0][4])));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean .
	 * @param code - unique code for deletion.
	 * @return boolean.
	 */
	public boolean deleteCheckedRecords(final BusinessUnitBean bean, final String[] code) {
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
		}  else {
			result = true;
			return result;
		}
	}

	/**
	 * @param bean - to get code for deletion. 
	 * @return true/false.
	 */
	public boolean delete(final BusinessUnitBean bean) {
		boolean result = false;				
		final String deleteId = bean.getBusinessCode();
		final String delQuery = "DELETE FROM HRMS_D1_BUSSINESS_UNIT WHERE BUSS_UNIT_ID =" + deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}
}
