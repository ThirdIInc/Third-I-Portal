package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.PostApplied;
import org.paradyne.bean.admin.master.AwardMaster;

import org.paradyne.lib.ModelBase;

public class PostAppliedModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	public void postData(PostApplied post, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(5));
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1 = 0;
		int PageNo1 = 1;// ----------
		REC_TOTAL = repData.length;
		int no_of_pages = Math.round(REC_TOTAL / 20);
		// PageNo = Integer.parseInt(locationMaster.getMyPage());//-----------
		double row = (double) repData.length / 20.0;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount1 = Integer.parseInt(value1.setScale(0,
				java.math.BigDecimal.ROUND_UP).toString());

		System.out.println("--------------------" + rowCount1);
		System.out.println("------- am.getMyPage()-------------"
				+ post.getMyPage());
		ArrayList<Object> obj = new ArrayList<Object>();
		request.setAttribute("abc", rowCount1);

		// PageNo
		if (String.valueOf(post.getMyPage()).equals("null")
				|| String.valueOf(post.getMyPage()).equals(null)
				|| String.valueOf(post.getMyPage()).equals(" ")) {
			PageNo1 = 1;
			From_TOT = 0;
			To_TOT = 20;

			if (To_TOT > repData.length) {
				To_TOT = repData.length;
			}
			System.out.println("-----------To_TOTAL----null-----" + To_TOT);
			post.setMyPage("1");
		}

		else {

			pg1 = Integer.parseInt(post.getMyPage());
			PageNo1 = pg1;

			if (pg1 == 1) {
				From_TOT = 0;
				To_TOT = 20;
			} else {
				// From_TOTAL=To_TOTAL+1;
				To_TOT = To_TOT * pg1;
				From_TOT = (To_TOT - 20);
			}
			if (To_TOT > repData.length) {
				To_TOT = repData.length;
			}
		}
		request.setAttribute("xyz", PageNo1);
		System.out.println("------------from total--------" + From_TOT);
		System.out.println("----------to total----------" + To_TOT);
		for (int i = From_TOT; i < To_TOT; i++) {
			PostApplied bean1 = new PostApplied();
			bean1.setPostCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setPostName(checkNull(String.valueOf(repData[i][1])));

			obj.add(bean1);
		}

		post.setPostList(obj);

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void calforedit(PostApplied bean) {
		String query = " SELECT POST_CODE,POST_NAME FROM HRMS_POST_APP  "

		+ "  WHERE  POST_CODE = " + bean.getHiddencode()
				+ "   ORDER BY POST_CODE ";

		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setPostCode(String.valueOf(data[0][0]));
		bean.setPostName(String.valueOf(data[0][1]));

	}

	public boolean addData(PostApplied bean) {
		String query = "SELECT UPPER(POST_NAME) FROM HRMS_POST_APP WHERE (POST_NAME='"
				+ bean.getPostName().trim().toUpperCase()
				+ "' OR POST_NAME='"
				+ bean.getPostName().trim().toLowerCase() + "')";
		Object[][] data = getSqlModel().getSingleResult(query);

		boolean flag = false;
		if (data.length > 0) {
			flag = false;
		} else {
			Object addObj[][] = new Object[1][1];
			addObj[0][0] = bean.getPostName();

			flag = getSqlModel().singleExecute(getQuery(1), addObj);
		}
		return flag;
	}

	public boolean deleteData(PostApplied bean) {
		Object delobj[][] = new Object[1][1];
		delobj[0][0] = bean.getPostCode();
		return getSqlModel().singleExecute(getQuery(2), delobj);
	}

	public boolean modPost(PostApplied bean) {
		Object addObj[][] = new Object[1][2];
		System.out.println("In Modify method");

		addObj[0][0] = bean.getPostName();
		addObj[0][1] = bean.getPostCode();
		/*
		 * addObj [0][1]=bean.getCityClass(); addObj [0][2]=bean.getCityAbbr();
		 * addObj [0][3]=bean.getCityParID(); addObj
		 * [0][4]=bean.getCityLevCode(); addObj [0][5]=bean.getCityID();
		 */
		return getSqlModel().singleExecute(getQuery(4), addObj);

	}

	public boolean deletePost(PostApplied bean, String[] code) {
		boolean result = false;
		boolean flag = false;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					logger
							.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"
									+ code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(2), delete);
					if (flag)
						result = true;
				}
			}
		}
		return result;
	}

}
