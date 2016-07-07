package org.paradyne.model.LMS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.ColonyMasterBean;
import org.paradyne.bean.TravelManagement.Master.ProjectMasterBean;
import org.paradyne.bean.admin.master.RoundTypeMasterBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class ColonyMasterModel extends ModelBase {
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	

	public void getList(ColonyMasterBean bean, HttpServletRequest request) {
		Object[][] selData = null;

		try {
			String selQuery = "SELECT  COLONY_NAME, COLONY_ADDRESS,COLONY_ID FROM LMS_COLONY  ORDER BY COLONY_ID ";
			selData = getSqlModel().getSingleResult(selQuery);

			// System.out.println("=========== After Query ===========");
			selData = getSqlModel().getSingleResult(selQuery);
		} catch (Exception e) {

		}
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean
				.getMyPage(), selData.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		if (selData == null) {

		}

		ArrayList<Object> list = new ArrayList<Object>();
		if (selData != null && selData.length > 0) {

			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				ColonyMasterBean subBean = new ColonyMasterBean();
				
				subBean.setIttColonyName(checkNull(String.valueOf(selData[i][0])));
				subBean.setIttColonyAddress(checkNull(String
						.valueOf(selData[i][1])));

				subBean.setIttSrN0(String.valueOf(i + 1));
				subBean.setIttColonyId(String.valueOf(selData[i][2]));

				list.add(subBean);
			}
		}
		bean.setColonyMasterItt(list);
	}

	public boolean deleteCheck(ColonyMasterBean bean, String[] code,
			String[] colId, HttpServletRequest request) {
		boolean flag =false;
		
		//System.out.println(".......................................");
		for (int j = 0; j < code.length; j++) {	
			if(code[j].equals("Y")){
		
			String delQuery="DELETE FROM LMS_COLONY WHERE COLONY_ID="+colId[j]+"";
			flag = getSqlModel().singleExecute(delQuery);
			
			}
		
			
		
		}
		getList(bean,request);
		
		return flag;

	}

	public boolean save(ColonyMasterBean bean) {

		boolean result = false;

		

		String cName = bean.getColonyName();
		String cAddress = bean.getColonyAddress();

		String selQuery = " SELECT * FROM LMS_COLONY WHERE UPPER(COLONY_NAME) LIKE '"
				+ bean.getColonyName().trim().toUpperCase() + "'";
		Object[][] selData = getSqlModel().getSingleResult(selQuery);
		if (selData.length == 0) {

			Object insObj[][] = new Object[1][2];

			// insObj[0][0]= cId;
			
			insObj[0][0] = cName;
			insObj[0][1] = cAddress;

			String insQuery = "INSERT INTO LMS_COLONY ( COLONY_ID, COLONY_NAME, COLONY_ADDRESS) VALUES((SELECT NVL(MAX(COLONY_ID),0)+1 FROM LMS_COLONY) ,?,?)";
			result = getSqlModel().singleExecute(insQuery, insObj);

			String Query = "SELECT MAX(COLONY_ID) FROM LMS_COLONY";
			Object[][] data = getSqlModel().getSingleResult(Query);
			bean.setColonyId(String.valueOf(data[0][0]));
		}
		return result;

	}

	public boolean update(ColonyMasterBean bean) {
		boolean result = false;
		try {

			String cNo = bean.getColonyNo();
			String cName = bean.getColonyName();
			String cAddress = bean.getColonyAddress();
			String cId = bean.getColonyId();

			Object upObj[][] = new Object[1][3];
			upObj[0][0] = cName; 
			upObj[0][1] =cAddress;
			upObj[0][2] = cId;
			

			String upQuery = "UPDATE LMS_COLONY SET   COLONY_NAME=?, COLONY_ADDRESS=? WHERE COLONY_ID=?";

			result = getSqlModel().singleExecute(upQuery, upObj);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean delete(ColonyMasterBean bean, HttpServletRequest request) {
		boolean result = false;

		String cId = bean.getColonyId();

		String delQuery = "DELETE FROM LMS_COLONY WHERE COLONY_ID=" + cId;
		result = getSqlModel().singleExecute(delQuery);
		getList(bean, request);
		return result;
	}

	public void getDetails(ColonyMasterBean bean) {
		try {
			String colId = bean.getColonyId();
			String selQuery = "SELECT  COLONY_ID,COLONY_NAME, COLONY_ADDRESS FROM LMS_COLONY WHERE COLONY_ID= "+colId;
			Object[][] data = getSqlModel().getSingleResult(selQuery);
			bean.setColonyId(String.valueOf(data[0][0]));
			bean.setColonyName(String.valueOf(data[0][1]));
			bean.setColonyAddress(String.valueOf(data[0][2]));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
	public void dblClickItt(ColonyMasterBean bean) {
		getDetails(bean);

	}

}
