package org.paradyne.model.common;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.common.AdminHome;
import org.paradyne.bean.common.LeaveHome;
import org.paradyne.lib.ModelBase;

/*
 * author:Pradeep K S
 * Date:29-01-2008
 */

public class AdminHomeModel extends ModelBase {

	public void getConf(AdminHome adHm) {

		try {
			String query = " SELECT TO_CHAR(CONF_DATE,'DD-MM-YYYY'),CONF_FROM_TIME,CONF_TO_TIME,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,VENUE_NAME "
				+"  ,DECODE(CONF_STATUS,'P','Pending','A','Approved') "
					+ " FROM HRMS_CONF_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CONF_HDR.CONF_BOOKEDBY)  "
					+ " INNER JOIN HRMS_CONF_VENUE ON(HRMS_CONF_VENUE.VENUE_CODE=HRMS_CONF_HDR.CONF_ROOMNO) "
					+ " WHERE TO_DATE(CONF_DATE,'DD-MM-YYYY') >=TO_DATE(SYSDATE,'DD-MM-YYYY')  AND CONF_STATUS IN ('P','A') ";
			if (adHm.getUserProfileDivision() != null
					&& adHm.getUserProfileDivision().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV="
						+ adHm.getUserProfileDivision() + ")";
			}
			query +=" ORDER BY CONF_APPLN_DATE";
			Object[][] values = getSqlModel().getSingleResult(query);
			ArrayList<AdminHome> conList = new ArrayList<AdminHome>();
			for (int i = 0; i < values.length; i++) {

				AdminHome bean = new AdminHome();
				bean.setConfDate(String.valueOf(values[i][0]));
				bean.setConfTime(String.valueOf(values[i][1]));
				bean.setConfToTime(String.valueOf(values[i][2]));
				bean.setConfBy(String.valueOf(values[i][3]));
				bean.setConfPlace(String.valueOf(values[i][4]));
				bean.setConfStatus(String.valueOf(values[i][5]));

				conList.add(bean);
			}
			adHm.setConfList(conList);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void voucher(AdminHome adHm) {

		String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(APP_DATE,'DD-MM-YYYY'),VCH_TOTALAMT FROM HRMS_VOUCHER_APPL "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_VOUCHER_APPL.EMP_CODE)"
				+ " WHERE APP_DATE  >=(SELECT SYSDATE FROM DUAL) ";

		Object[][] values = getSqlModel().getSingleResult(query);
		ArrayList<AdminHome> vList = new ArrayList<AdminHome>();
		for (int i = 0; i < values.length; i++) {

			AdminHome bean = new AdminHome();
			bean.setName(String.valueOf(values[i][0]));
			bean.setVchDate(String.valueOf(values[i][1]));
			bean.setAmt(String.valueOf(values[i][2]));

			vList.add(bean);
		}

		adHm.setVchList(vList);

	}

	public void getAsset(AdminHome bean) {

		String query = " SELECT ASSET_CATEGORY_TYPE FROM HRMS_ASSET_CATEGORY ";
		Object[][] values = getSqlModel().getSingleResult(query);

		TreeMap tm = new TreeMap();
		for (int i = 0; i < values.length; i++) {

			tm.put(i, String.valueOf(values[i][0]));
			bean.setAssetList(tm);

		}

	}

	public String[] getAssetRecord(AdminHome adHm) {
		String[] str = new String[2];
		TreeMap map = new TreeMap();
		String sql = " SELECT  ASSET_CATEGORY_CODE, ASSET_CATEGORY_NAME    FROM  HRMS_ASSET_CATEGORY order by  ASSET_CATEGORY_CODE ";

		Object[][] data = getSqlModel().getSingleResult(sql);
		str[0] = String.valueOf(data[0][0]);
		for (int j = 0; j < data.length; j++) {

			map.put(String.valueOf(data[j][0]), String.valueOf(data[j][1]));

		}// end inner for

		adHm.setAssetmap(map);
		TreeMap map1 = new TreeMap();
		String sqlQuery = "  Select  ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME   from HRMS_ASSET_SUBTYPE "
				+ " where  ASSET_CATEGORY_CODE=	" + String.valueOf(data[0][0]);

		Object[][] data1 = getSqlModel().getSingleResult(sqlQuery);
		str[1] = String.valueOf(data1[0][0]);
		for (int j = 0; j < data1.length; j++) {

			map1.put(String.valueOf(data1[j][0]), String.valueOf(data1[j][1]));

		}// end inner for

		adHm.setAssetmap1(map1);
		return str;
	}

	public String[] getChart(String categorycode, String subtypecode)
			throws Exception {

		System.out
				.println("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");

		String query = " select ASSET_INVENTORY_TYPE from hrms_asset_subtype "
				+ "  where asset_category_code =" + categorycode
				+ " and asset_subtype_code = " + subtypecode;
		Object[][] data = getSqlModel().getSingleResult(query);
		String[] chartValue = new String[2];
		// IF reult comes 'I''
		if (data != null && data.length > 0
				&& !String.valueOf(data[0][0]).equals("null")) {
			if (String.valueOf(data[0][0]).equals("I")) {
				String query1 = "select  nvl(sum(ASSET_AVAILABLE),0) from HRMS_ASSET_MASTER_DTL "
						+ " where ASSET_MASTER_CODE in "
						+ " (select ASSET_MASTER_CODE from hrms_asset_master where asset_category_code = "
						+ categorycode
						+ " and asset_subtype_code = "
						+ subtypecode + ") and " + " ASSET_AVAILABLE  = 1";
				Object[][] data1 = getSqlModel().getSingleResult(query1);
				String query2 = " select count (*) from HRMS_ASSET_MASTER_DTL "
						+ " where  ASSET_MASTER_CODE in "
						+ " (select ASSET_MASTER_CODE from hrms_asset_master where asset_category_code = "
						+ categorycode + " and asset_subtype_code = "
						+ subtypecode + ") " + " and ASSET_AVAILABLE  = 0 ";
				Object[][] data2 = getSqlModel().getSingleResult(query2);

				chartValue[0] = String.valueOf(data1[0][0]);
				chartValue[1] = String.valueOf(data2[0][0]);
			}
		}

		// ------------------------

		// --- IF reult comes 'S''
		if (data != null && data.length > 0
				&& !String.valueOf(data[0][0]).equals("null")) {
			if (String.valueOf(data[0][0]).equals("S")) {
				String query3 = " select  nvl(sum(ASSET_AVAILABLE),0),nvl(sum(ASSET_QUANTITY),0)-nvl(sum(ASSET_AVAILABLE),0) from HRMS_ASSET_MASTER_DTL "
						+ " where ASSET_MASTER_CODE in  "
						+ " (select ASSET_MASTER_CODE from hrms_asset_master where asset_category_code ="
						+ categorycode
						+ " and asset_subtype_code = "
						+ subtypecode + ") ";
				Object[][] data3 = getSqlModel().getSingleResult(query3);
				chartValue[0] = String.valueOf(data3[0][0]);
				chartValue[1] = String.valueOf(data3[0][1]);

			}
		}

		return chartValue;

	}
	
	
//Added by priyanka
	
	public void getAdminMenuList(HttpServletRequest request,
			AdminHome bean, String menuType) {
		try {

			if (menuType == null) {
				menuType = "MN";
			}
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String groupQuery = " select DISTINCT MENU_GROUP,MENU_GROUP_ORDER, MENU_IMAGE from HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and HRMS_PROFILE_DTL.PROFILE_CODE IN("
					+ bean.getMultipleProfileCode()
					+ "))"
					+ " WHERE MENU_TYPE='"
					+ menuType
					+ "' AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER ";
			Object groupData[][] = getSqlModel().getSingleResult(groupQuery);

			String query = " SELECT DISTINCT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP,MENU_GROUP_ORDER,MENU_PLACEINMENU FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ "	OR PROFILE_GENERAL_FLAG ='Y'))"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN("+bean.getMultipleProfileCode()+") and MENU_TYPE='" + menuType + "' "
					+ " AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER,MENU_PLACEINMENU";
			Object data[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("serviceMenulst", data);
			request.setAttribute("groupData", groupData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Ended by priyanka
	

}
