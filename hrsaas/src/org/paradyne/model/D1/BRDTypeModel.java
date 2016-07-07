package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.BRDType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1380
 *
 */
public class BRDTypeModel extends ModelBase {

	/**
	 * @param bean
	 * @param request
	 */
	public void getInitialData(final BRDType bean, final HttpServletRequest request) {
		final Object[][] regData = this.getSqlModel().getSingleResult("SELECT TYPE_ID, NVL(TYPE_NAME,'') FROM HRMS_D1_BRD_TYPE ORDER BY TYPE_ID");
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
			final ArrayList<BRDType> list = new ArrayList<BRDType>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				final BRDType innerBean = new BRDType();
				innerBean.setTypeCode(this.checkNull(String.valueOf(regData[i][0])));
				innerBean.setTypeNameItr(this.checkNull(String.valueOf(regData[i][1])));
				list.add(innerBean);
			}
			bean.setTypeList(list);
		} else {
			bean.setTypeList(null);
		}
	}
	
	
	/**
	 * purpose - For inserting records into Data Base.
	 * @param businessUnitBean - bean instance.
	 * @return true/false.
	 */
	public boolean insertData(final BRDType bean) {
		boolean result = false;
		try {
			if(!checkForDuplicateRecord(bean)) {
				result = false;
			} else {
				Object[][] maxCode = getSqlModel().getSingleResult("SELECT NVL(MAX(TYPE_ID),0)+1 FROM HRMS_D1_BRD_TYPE");
				String insertDataQuery = " INSERT INTO HRMS_D1_BRD_TYPE(TYPE_ID, TYPE_NAME) "
										+" VALUES(?,?)";
				Object[][] insertDataObj = new Object[1][2];
				insertDataObj[0][0] = checkNull(String.valueOf(maxCode[0][0]));
				insertDataObj[0][1] = bean.getTypeName().trim();
				System.out.println("bean.getTypeName() >>>"+bean.getTypeName());
				bean.setTypeCode(checkNull(String.valueOf(maxCode[0][0])));
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
	public boolean updateData(final BRDType bean) {
		boolean result = false;	
		try {
			if(!checkForDuplicateRecord(bean)) {
				result = false;
			} else {
				final Object[][] updateObj = new Object[1][2];
				updateObj[0][0] = bean.getTypeName().trim();
				updateObj[0][1] = bean.getTypeCode().trim();
				String updateQuery = " UPDATE HRMS_D1_BRD_TYPE SET TYPE_NAME=? WHERE TYPE_ID=?  ";
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
	public void editBusinessData(final BRDType bean) {
		try {
			final String query = "SELECT TYPE_ID, NVL(TYPE_NAME,'') FROM HRMS_D1_BRD_TYPE " + 
								 " WHERE TYPE_ID = " + bean.getTypeCode();
			final Object[][] data = this.getSqlModel().getSingleResult(query);
			bean.setTypeCode(checkNull(String.valueOf(data[0][0])));
			bean.setTypeName(checkNull(String.valueOf(data[0][1])));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param bean - to get code for deletion. 
	 * @return true/false.
	 */
	public boolean delete(final BRDType bean) {
		final String delQuery = "DELETE FROM HRMS_D1_BRD_TYPE WHERE TYPE_ID =" + bean.getTypeCode();
		boolean result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}
	
	
	/**
	 * @param bean .
	 * @param code - unique code for deletion.
	 * @return boolean.
	 */
	public boolean deleteCheckedRecords(final BRDType bean, final String[] code) {
		boolean result = false;
		String codeTodelete = "";
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!"".equals(code[i])) {
					codeTodelete += String.valueOf(code[i])+",";
				}
			}
			codeTodelete = codeTodelete.substring(0, codeTodelete.length()-1);
			System.out.println("codeTodelete >>"+codeTodelete);
			result = getSqlModel().singleExecute(" DELETE FROM HRMS_D1_BRD_TYPE WHERE TYPE_ID IN (" + codeTodelete + ")");
		}
		 
		return result;
	}
	
	
	/**
	 * method name : checkForDuplicateRecord.
	 * Check for duplicates Value
	 * @return boolean
	 */
	public boolean checkForDuplicateRecord(final BRDType bean) {
		boolean result = false;
		try {
			Object[][] selectData = getSqlModel().getSingleResult("SELECT NVL(TYPE_NAME,'') FROM HRMS_D1_BRD_TYPE");
			if (selectData != null && selectData.length > 0) {
				for (int i = 0; i < selectData.length; i++) {
					if(bean.getTypeName().trim().equalsIgnoreCase(String.valueOf(selectData[i][0]))) {
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
