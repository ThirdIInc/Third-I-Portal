package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.BRDClassification;
import org.paradyne.bean.D1.BRDType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1380
 *
 */
public class BRDClassificationModel extends ModelBase {

	/**
	 * @param bean
	 * @param request
	 */
	public void getInitialData(final BRDClassification bean, final HttpServletRequest request) {
		final Object[][] regData = this.getSqlModel().getSingleResult("SELECT CLASSIFICATION_ID, NVL(CLASSIFICATION_NAME,'') FROM HRMS_D1_BRD_CLASSIFICATION ORDER BY CLASSIFICATION_ID");
		if (regData != null && regData.length > 0) {
			bean.setModeLength("true");
			bean.setTotalNoOfRecords(String.valueOf(regData.length));
			final String[] pageIndex = Utility.doPaging(bean.getMyPage(), regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] =  "1";
			}
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if ("1".equals(pageIndex[4])) {
				bean.setMyPage("1");
			}
			final ArrayList<BRDClassification> list = new ArrayList<BRDClassification>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				final BRDClassification innerBean = new BRDClassification();
				innerBean.setClassificationCode(this.checkNull(String.valueOf(regData[i][0])));
				innerBean.setClassificationNameItr(this.checkNull(String.valueOf(regData[i][1])));
				list.add(innerBean);
			}
			bean.setClassificationList(list);
		} else {
			bean.setClassificationList(null);
		}
	}
	
	
	/**
	 * purpose - For inserting records into Data Base.
	 * @param businessUnitBean - bean instance.
	 * @return true/false.
	 */
	public boolean insertData(final BRDClassification bean) {
		boolean result = false;
		try {
			if(!checkForDuplicateRecord(bean)) {
				result = false;
			} else {
				Object[][] maxCode = getSqlModel().getSingleResult("SELECT NVL(MAX(CLASSIFICATION_ID),0)+1 FROM HRMS_D1_BRD_CLASSIFICATION");
				String insertDataQuery = " INSERT INTO HRMS_D1_BRD_CLASSIFICATION(CLASSIFICATION_ID, CLASSIFICATION_NAME) "
										+" VALUES(?,?)";
				Object[][] insertDataObj = new Object[1][2];
				insertDataObj[0][0] = checkNull(String.valueOf(maxCode[0][0]));
				insertDataObj[0][1] = bean.getClassificationName().trim();
				bean.setClassificationCode(checkNull(String.valueOf(maxCode[0][0])));
				result = getSqlModel().singleExecute(insertDataQuery, insertDataObj);
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * purpose - Modifying record.
	 * @param bean - get appln data.
	 * @return true/false..
	 */
	public boolean updateData(final BRDClassification bean) {
		boolean result = false;	
		try {
			if(!checkForDuplicateRecord(bean)) {
				result = false;
			} else {
				final Object[][] updateObj = new Object[1][2];
				updateObj[0][0] = bean.getClassificationName().trim();
				updateObj[0][1] = bean.getClassificationCode().trim();
				String updateQuery = " UPDATE HRMS_D1_BRD_CLASSIFICATION SET CLASSIFICATION_NAME=? WHERE CLASSIFICATION_ID=?  ";
				result =  this.getSqlModel().singleExecute(updateQuery, updateObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * purpose - Edit respective record.
	 * @param businessUnitBean bean instance.
	 */
	public void editBusinessData(final BRDClassification bean) {
		try {
			final String query = "SELECT CLASSIFICATION_ID, NVL(CLASSIFICATION_NAME,'') FROM HRMS_D1_BRD_CLASSIFICATION " + 
								 " WHERE CLASSIFICATION_ID = " + bean.getClassificationCode();
			final Object[][] data = this.getSqlModel().getSingleResult(query);
			bean.setClassificationCode(checkNull(String.valueOf(data[0][0])));
			bean.setClassificationName(checkNull(String.valueOf(data[0][1])));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param bean - to get code for deletion. 
	 * @return true/false.
	 */
	public boolean delete(final BRDClassification bean) {
		final String delQuery = "DELETE FROM HRMS_D1_BRD_CLASSIFICATION WHERE CLASSIFICATION_ID =" + bean.getClassificationCode();
		boolean result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}
	
	
	/**
	 * @param bean .
	 * @param code - unique code for deletion.
	 * @return boolean.
	 */
	public boolean deleteCheckedRecords(final BRDClassification bean, final String[] code) {
		boolean result = false;
		String codeTodelete = "";
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!"".equals(code[i])) {
					codeTodelete += String.valueOf(code[i])+",";
				}
			}
			codeTodelete = codeTodelete.substring(0, codeTodelete.length()-1);
			result = getSqlModel().singleExecute(" DELETE FROM HRMS_D1_BRD_CLASSIFICATION WHERE CLASSIFICATION_ID IN (" + codeTodelete + ")");
		}
		 
		return result;
	}
	
	
	/**
	 * method name : checkForDuplicateRecord.
	 * Check for duplicates Value
	 * @return boolean
	 */
	public boolean checkForDuplicateRecord(final BRDClassification bean) {
		boolean result = false;
		try {
			Object[][] selectData = getSqlModel().getSingleResult("SELECT NVL(CLASSIFICATION_NAME,'') FROM HRMS_D1_BRD_CLASSIFICATION");
			if (selectData != null && selectData.length > 0) {
				for (int i = 0; i < selectData.length; i++) {
					if(bean.getClassificationName().trim().equalsIgnoreCase(String.valueOf(selectData[i][0]))) {
						result = false;
						break;
					} else {
						result = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
	
}
