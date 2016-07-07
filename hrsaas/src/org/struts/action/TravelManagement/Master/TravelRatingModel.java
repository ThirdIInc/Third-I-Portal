package org.struts.action.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Master.TravelRatingBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class TravelRatingModel extends ModelBase {

	public void intData(TravelRatingBean trBean, HttpServletRequest request) {
		
		String ratingQuery =  getQuery(2);
		Object[][] repData = getSqlModel().getSingleResult(ratingQuery);
		
		if (repData != null && repData.length > 0) {
			trBean.setModeLength("true");

			trBean.setTotalNoOfRecords(String.valueOf(repData.length));

			String[] pageIndex = Utility.doPaging(trBean.getMyPage(),
					repData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1")){
				trBean.setMyPage("1");
			}
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				TravelRatingBean bean = new TravelRatingBean();
				bean.setRatingId(checkNull(String.valueOf(repData[i][0])));
				bean.setRatingParameter(checkNull(String.valueOf(repData[i][1])));
				bean.setRatingType(checkNull(String.valueOf(repData[i][2])));
				

				List.add(bean);
			}// end of loop
System.out.println("in main page---"+List.size());
			trBean.setRatingdataList(List);
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	public boolean addData(TravelRatingBean trBean) {

		boolean result = false;
		Object addObj[][] = new Object[1][2];

		addObj[0][0] = trBean.getRatingParameter();
		addObj[0][1] = trBean.getRatingType();
		

		result = getSqlModel().singleExecute(getQuery(1), addObj);

		if(result)
		{
			String autoCodeQuery = " SELECT NVL(MAX(RATING_ID),0) FROM  TMS_RATING_PARAM  ";
			
			Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
			System.out.println("Rating addData  hidden value ---"+data);
			
			if(data!=null && data.length >0)
			{
				trBean.setHiddencode(String.valueOf(data[0][0]));
				System.out.println("Rating data hidden value ---"+trBean.getHiddencode());
			}
		}
		return result;
	}
	
	
	public boolean update(TravelRatingBean trBean) {
			Object addObj[][] = new Object[1][3];
			
			addObj[0][0] = trBean.getRatingParameter().trim();
			addObj[0][1] = trBean.getRatingType().trim();
			addObj[0][2] = trBean.getHiddencode().trim();
			return getSqlModel().singleExecute(getQuery(3), addObj);
	}

	public void calforedit(TravelRatingBean trBean) {

		try {
			String query = " SELECT RATING_NAME, RATING_TYPE FROM TMS_RATING_PARAM WHERE RATING_ID= "
					+ trBean.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);
			trBean.setRatingParameter(String.valueOf(data[0][0]));
			trBean.setRatingType(String.valueOf(data[0][1]));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean deleteCheckedRecords(TravelRatingBean trBean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(4), delete);
					if (!result)
						count++;

				}// end of if
			}// end of for loop
		}// end of if
		if (count != 0) {
			result = false;
			return result;
		}// end of if
		else {
			result = true;
			return result;
		}// end of else		
	}

	public boolean delRecord(TravelRatingBean trBean) {
		Object del[][] = new Object[1][1];
		
		del[0][0] = trBean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(4), del);
	}

}
