package org.paradyne.model.admin.master;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.master.RoundTypeMasterBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class RoundTypeMasterModel extends ModelBase {

	public void getList(RoundTypeMasterBean bean, HttpServletRequest request) {

		String selQuery = "SELECT  ROUND_TYPE,ROUND_CODE FROM HRMS_REC_ROUND_TYPE  ORDER BY ROUND_CODE ";
		Object[][] selData = getSqlModel().getSingleResult(selQuery);
		
		if (selData != null && selData.length > 0) {
			bean.setRecordsAvailable(true);
			bean.setTotalRecords(String.valueOf(selData.length));
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					selData.length, 20);

			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));

			if (pageIndex[4].equals("1")) {
				bean.setMyPage("1");
			}
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				RoundTypeMasterBean subBean = new RoundTypeMasterBean();
				subBean.setIttRoundTypeCode(String.valueOf(selData[i][1]));
				subBean.setIttSrN0(String.valueOf(i + 1));
				subBean.setIttRoundType(String.valueOf(selData[i][0]));

				list.add(subBean);
			}
			bean.setRoundTypeMasterItt(list);
		}
	}

	public boolean deleteCheck(RoundTypeMasterBean bean, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		String delQuery = "";
		if(code != null) {
			for(int i = 0; i < code.length; i++) {

				if(!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					delQuery = "DELETE FROM HRMS_REC_ROUND_TYPE WHERE ROUND_CODE=?";
					flag = getSqlModel().singleExecute(delQuery, delete);
					if(!flag) {
						cnt++;
					}// end of if
					// result=true;
				}// end of if
			}// end of loop
		}// end of nested if
		if(cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}

	public void getDetails(RoundTypeMasterBean bean) {

		//String roundCode = bean.getHiddencode();
		String roundCode =bean.getRoundCode(); 

		String selQuery = "SELECT ROUND_TYPE,ROUND_CODE FROM HRMS_REC_ROUND_TYPE WHERE ROUND_CODE= "
				+ roundCode;

		Object[][] data = getSqlModel().getSingleResult(selQuery);

		bean.setRoundCode(String.valueOf(data[0][1]));
		bean.setRoundType(String.valueOf(data[0][0]));

	}

	public boolean save(RoundTypeMasterBean bean) {
		boolean result = false;

		String roundType = bean.getRoundType();
		String selQuery = " SELECT * FROM HRMS_REC_ROUND_TYPE WHERE UPPER(ROUND_TYPE) LIKE '"
				+ bean.getRoundType().trim().toUpperCase() + "'";
		Object[][] selData = getSqlModel().getSingleResult(selQuery);
		if (selData.length == 0) {

			Object[][] insObj = new Object[1][1];
			insObj[0][0] = bean.getRoundType();

			String insQuery = "INSERT INTO HRMS_REC_ROUND_TYPE (ROUND_CODE, ROUND_TYPE) VALUES((SELECT NVL(MAX(ROUND_CODE),0)+1 FROM  HRMS_REC_ROUND_TYPE) ,?)";
			result = getSqlModel().singleExecute(insQuery, insObj);

			String Query = "SELECT MAX(ROUND_CODE) FROM  HRMS_REC_ROUND_TYPE";
			Object[][] data = getSqlModel().getSingleResult(Query);
			bean.setRoundCode(String.valueOf(data[0][0]));
		}
		return result;
	}

	public boolean update(RoundTypeMasterBean bean) {
		boolean result = false;
		String roundCode = bean.getRoundCode();
		String roundType = bean.getRoundType();

		Object upObj[][] = new Object[1][1];

		upObj[0][0] = roundType;

		System.out.println(".............." + roundCode);
		System.out.println(".............." + roundType);

		String upQuery = "UPDATE HRMS_REC_ROUND_TYPE SET  ROUND_TYPE=? WHERE ROUND_CODE= "
				+ roundCode;
		result = getSqlModel().singleExecute(upQuery, upObj);

		return result;
	}

	public void dblClickItt(RoundTypeMasterBean bean) {
		String roundCode = bean.getHiddencode();
		//String roundCode =bean.getRoundCode(); 

		String selQuery = "SELECT ROUND_TYPE,ROUND_CODE FROM HRMS_REC_ROUND_TYPE WHERE ROUND_CODE= "
				+ roundCode;

		Object[][] data = getSqlModel().getSingleResult(selQuery);

		bean.setRoundCode(String.valueOf(data[0][1]));
		bean.setRoundType(String.valueOf(data[0][0]));


	}

	public boolean delete(RoundTypeMasterBean bean, HttpServletRequest request) {

		boolean result = false;

		String roundCode = bean.getRoundCode();

		String delQuery = "DELETE FROM HRMS_REC_ROUND_TYPE WHERE ROUND_CODE="
				+ roundCode;
		result = getSqlModel().singleExecute(delQuery);
		getList(bean, request);
		return result;

	}

}
