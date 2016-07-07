/**
 * 
 */
package org.paradyne.model.portal;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.paradyne.bean.portal.MyFavourites;
import org.paradyne.bean.portal.MyNotes;
import org.paradyne.lib.ModelBase;

/**
 * @author aa1439
 *
 */
public class MyFavouritesModel  extends ModelBase{
	
	
	
	public void catData(MyFavourites bean){
		try {
			LinkedHashMap map = new LinkedHashMap();
			Object[][] dataObj = null;
			String sqlQuery = " SELECT DISTINCT FAV_LINK_CATEGORY FROM HRMS_FAVOURITES WHERE EMP_ID="
					+ bean.getUserEmpId()
					+ " AND FAV_LINK_CATEGORY IS NOT NULL "
					+ "ORDER BY FAV_LINK_CATEGORY";
			dataObj = getSqlModel().getSingleResult(sqlQuery);
			if (dataObj != null && dataObj.length > 0) {
				for (int j = 0; j < dataObj.length; j++) {
					map.put(String.valueOf(dataObj[j][0]), String
							.valueOf(dataObj[j][0]));
					
				}
				map.put("Other", "Other");
			} else {
				map.put("Other", "Other");
			}
			if (map != null && map.size() > 0) {
				bean.setTmap(map);
				bean.setChkFlag(true);
			} else {
				bean.setTmap(null);
				bean.setChkFlag(false);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean addMyFavourites(MyFavourites bean, String note) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			Object[][] saveObj = new Object[1][4];
			saveObj[0][0] = bean.getUserEmpId().trim();
			saveObj[0][1] = bean.getLinkName().trim();
			saveObj[0][2] = bean.getLinkUrl().trim();
			if( bean.getCategoryOther()!=null && !bean.getCategoryOther().equals("")){
				saveObj[0][3] = bean.getCategoryOther().trim();
			}else{
				saveObj[0][3] = bean.getCategory().trim();
			}
			String query = " INSERT INTO HRMS_FAVOURITES (FAV_ID ,EMP_ID,FAV_LINK_NAME, FAV_LINK_URL, FAV_LINK_CATEGORY, FAV_CREATED_DATE)VALUES((SELECT NVL(MAX(FAV_ID),0)+1 FROM HRMS_FAVOURITES),?,?,?,?,sysdate)";
			result = getSqlModel().singleExecute(query, saveObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	public Object[][] setData(MyFavourites bean) {
		Object[][] dataObj = null;
		try {
			String query = " SELECT EMP_ID, FAV_ID, FAV_LINK_NAME, FAV_LINK_URL, FAV_LINK_CATEGORY," 
				+" to_char(FAV_CREATED_DATE,'DD-MM-YYYY HH:MI:SS'), to_char(FAV_MODIFIED_DATE,'DD-MM-YYYY HH:MI:SS') "
				+" FROM HRMS_FAVOURITES "
				+" WHERE EMP_ID="+ bean.getUserEmpId() 
				+" GROUP BY EMP_ID, FAV_ID, FAV_LINK_NAME, FAV_LINK_URL, FAV_LINK_CATEGORY,"
				+" to_char(FAV_CREATED_DATE,'DD-MM-YYYY HH:MI:SS'), to_char(FAV_MODIFIED_DATE,'DD-MM-YYYY HH:MI:SS') "
				+" ORDER BY FAV_ID  DESC";
			dataObj = getSqlModel().getSingleResult(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataObj;
	}
	
	
	public boolean updateMyFavouritesData(MyFavourites bean,String favId) {
		boolean result = false;
		try {
			Object updateObj[][] = new Object[1][4];
			updateObj[0][0] = bean.getLinkName().trim();
			updateObj[0][1] = bean.getLinkUrl().trim();
			if( bean.getCategoryOther()!=null && !bean.getCategoryOther().equals("")){
				updateObj[0][2] = bean.getCategoryOther().trim();
			}else{
				updateObj[0][2] = bean.getCategory().trim();
			}
			updateObj[0][3] = favId;
			String updateQUery = "  update HRMS_FAVOURITES set FAV_LINK_NAME=?, FAV_LINK_URL=?, FAV_LINK_CATEGORY=?, FAV_MODIFIED_DATE=sysdate "
					+ " where FAV_ID=? ";
			result = getSqlModel().singleExecute(updateQUery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	 public boolean deleteRecord(MyFavourites bean) {
			boolean result = false;
			try {
				String delQuery = "  delete from HRMS_FAVOURITES  where FAV_ID="
						+ bean.getHiddenEditCode().trim();
				result = getSqlModel().singleExecute(delQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		} 

}
