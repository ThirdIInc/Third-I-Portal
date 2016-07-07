package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.OnsitePosting;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * 
 * @author Dilip Kumar
 * Date:20/09/09
 */
public class OnsitePostingModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * this method is used to inserting the record
	 * 
	 * @param onsiteposting
	 * @return
	 */
	public boolean saveRecord(OnsitePosting onsiteposting) {
		boolean result = false;
		if (!checkDuplicate(onsiteposting)) {

			String query = "SELECT NVL(MAX(ONSITE_POSTING_ID),0)+1 FROM  HRMS_ONSITE_POSTING";
			Object[][] rel = getSqlModel().getSingleResult(query);
			onsiteposting.setOnSiteID(String.valueOf(rel[0][0]));

			Object[][] addObj = new Object[1][11];
			addObj[0][0] = checkNull(onsiteposting.getSiteType());
			addObj[0][1] = onsiteposting.getSiteCode().trim();
			addObj[0][2] = onsiteposting.getSiteAbbreviation().trim();
			addObj[0][3] = onsiteposting.getSiteName().trim();
			addObj[0][4] = checkNull(onsiteposting.getLoccationId());
			addObj[0][5] = checkNull(onsiteposting.getSiteAddress1());
			addObj[0][6] = checkNull(onsiteposting.getSiteAddress2());
			addObj[0][7] = checkNull(onsiteposting.getSiteAddress3());
			addObj[0][8] = checkNull(onsiteposting.getPhone());
			addObj[0][9] = onsiteposting.getEmailId();
			addObj[0][10] = onsiteposting.getIsActive();
		
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			return result;

		}// end of if
		else {
			return false;
		}// end of else
	}

	/**
	 *  To modify the records
	 * @param onsiteposting
	 * @return
	 */
	public boolean updateRecord(OnsitePosting onsiteposting) {
		// TODO Auto-generated method stub
		if (!checkDuplicateMod(onsiteposting)) {
			Object[][] addObj = new Object[1][12];

			addObj[0][0] = onsiteposting.getSiteType();
			addObj[0][1] = onsiteposting.getSiteCode().trim();
			addObj[0][2] = onsiteposting.getSiteAbbreviation().trim();
			addObj[0][3] = onsiteposting.getSiteName().trim();
			addObj[0][4] = onsiteposting.getLoccationId();
			addObj[0][5] = onsiteposting.getSiteAddress1();
			addObj[0][6] = onsiteposting.getSiteAddress2();
			addObj[0][7] = onsiteposting.getSiteAddress3();
			addObj[0][8] = onsiteposting.getPhone();
			addObj[0][9] = onsiteposting.getEmailId();
			addObj[0][10] = onsiteposting.getIsActive();
			addObj[0][11] = onsiteposting.getOnSiteID();
		return getSqlModel().singleExecute(getQuery(2), addObj);

		}// end of if
		else {
			return false;
		}

	}
	/**
	 *  to check the duplicate data
	 * @param bean
	 * @return
	 */

	public boolean checkDuplicate(OnsitePosting bean) {
		boolean result = false;
		String siteCode = bean.getSiteCode().trim().toUpperCase();
		String siteName = bean.getSiteName().trim().toUpperCase();
		String siteAbbr = bean.getSiteAbbreviation().trim().toUpperCase();
		
		String query = "SELECT * FROM HRMS_ONSITE_POSTING WHERE UPPER(ONSITE_POSTING_CODE) LIKE '" + siteCode + "' " +
		" OR UPPER(ONSITE_POSTING_ABBR) LIKE '"+siteAbbr+"' OR UPPER(ONSITE_POSTING_NAME) LIKE '"+siteName+"' ";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/**
	 * for checking duplicate entry of record during modification
	 */
	public boolean checkDuplicateMod(OnsitePosting bean) {
		boolean result = false;
		
		String siteCode = bean.getSiteCode().trim().toUpperCase();
		String siteName = bean.getSiteName().trim().toUpperCase();
		String siteAbbr = bean.getSiteAbbreviation().trim().toUpperCase();
		String onSiteId = bean.getOnSiteID();
		
		String query = "SELECT * FROM HRMS_DIVISION WHERE UPPER(ONSITE_POSTING_CODE) LIKE '" + siteCode + "' " +
		" OR UPPER(ONSITE_POSTING_ABBR) LIKE '"+siteAbbr+"' OR UPPER(ONSITE_POSTING_NAME) LIKE '"+siteName+"' " +
		" AND ONSITE_POSTING_ID NOT IN(" +onSiteId+ ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* generating the list onload */
	public void iteratorListData(OnsitePosting onsiteposting,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		Object[][] listData = getSqlModel().getSingleResult(getQuery(4));
		ArrayList<Object> List = new ArrayList<Object>();
		
		if (listData != null && listData.length > 0) {
			onsiteposting.setPostingLength("true");
			onsiteposting.setTotalRecords(String.valueOf(listData.length));

			String[] pageIndex = Utility.doPaging(onsiteposting.getMyPage(), listData.length, 20);
			if (pageIndex == null || pageIndex.length < 1) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			if (pageIndex[4].equals("1"))
				onsiteposting.setMyPage("1");
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				OnsitePosting bean1 = new OnsitePosting();
				bean1.setOnSiteID(checkNull(String.valueOf(listData[i][0])));
				bean1.setSiteType(checkNull(String.valueOf(listData[i][1])));
				bean1.setSiteCode(checkNull(String.valueOf(listData[i][2])));
				bean1.setSiteAbbreviation(checkNull(String.valueOf(listData[i][3])));
				bean1.setSiteName(checkNull(String.valueOf(listData[i][4])));
				bean1.setIsActive(checkNull(String.valueOf(listData[i][5])));

				List.add(bean1);
			}// end of loop
			onsiteposting.setPostingList(List);
		} else{
			onsiteposting.setPostingLength("false");
			onsiteposting.setTotalRecords("0");
			onsiteposting.setPostingList(List);
		}
	}

	/**
	 * this is used to check null value
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * this method is used to delete the records
	 * 
	 * @param onsiteposting
	 * @return
	 */
	public boolean deletePosting(OnsitePosting onsiteposting) {
		// TODO Auto-generated method stub
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = onsiteposting.getOnSiteID();
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/**
	 *  to delet the multiple records in the list
	 * @param onsiteposting
	 * @param code
	 * @return
	 */
	public boolean deleteSiteType(OnsitePosting onsiteposting, String[] code) {
		// TODO Auto-generated method stub

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}
					// result=true;
				}// end of nested if
			}// end of loop
		}// end of if
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}
	/**
	 * to modify the records in double click option
	 * @param onsiteposting
	 */

	public void calforedit(OnsitePosting onsiteposting) {
		// TODO Auto-generated method stub

		String query = "SELECT  nvl(ONSITE_POSTING_TYPE,''), nvl(ONSITE_POSTING_CODE,''),"
				+ " nvl(ONSITE_POSTING_ABBR,''),nvl( ONSITE_POSTING_NAME,''),nvl( ONSITE_POSTING_ADDRESS_1,''), "
				+ " nvl(ONSITE_POSTING_ADDRESS_2,''),nvl(ONSITE_POSTING_ADDRESS_3,''),nvl(ONSITE_POSTING_LOCATION,''),"
				+ " nvl(ONSITE_POSTING_PHONE,''),nvl(ONSITE_POSTING_EMAIL,''),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),nvl(ONSITE_POSTING_ID,'') FROM HRMS_ONSITE_POSTING"
				+ " WHERE  ONSITE_POSTING_ID = " + onsiteposting.getOnSiteID()
				+ "  ORDER BY ONSITE_POSTING_ID  ";

		Object[][] data = getSqlModel().getSingleResult(query);

		onsiteposting.setSiteType(checkNull(String.valueOf(data[0][0])));
		onsiteposting.setSiteCode(checkNull(String.valueOf(data[0][1])));
		onsiteposting.setSiteAbbreviation(checkNull(String.valueOf(data[0][2])));
		onsiteposting.setSiteName(checkNull(String.valueOf(data[0][3])));
		onsiteposting.setSiteLocation(checkNull(String.valueOf(data[0][4])));
		onsiteposting.setSiteAddress1(checkNull(String.valueOf(data[0][5])));
		onsiteposting.setSiteAddress2(checkNull(String.valueOf(data[0][6])));
		onsiteposting.setSiteAddress3(checkNull(String.valueOf(data[0][7])));
		onsiteposting.setPhone(checkNull(String.valueOf(data[0][8])));
		onsiteposting.setEmailId(checkNull(String.valueOf(data[0][9])));
		onsiteposting.setIsActive(checkNull(String.valueOf(data[0][10])));
		onsiteposting.setOnSiteID(checkNull(String.valueOf(data[0][11])));

	}

	public boolean sitedata(OnsitePosting onsiteposting) {
		// TODO Auto-generated method stub

		try {
			Object[] para = new Object[1];
			para[0] = onsiteposting.getOnSiteID();

			String query = "SELECT  nvl(ONSITE_POSTING_TYPE,''),nvl(ONSITE_POSTING_CODE,''),"
					+ " nvl(ONSITE_POSTING_ABBR,''),nvl(ONSITE_POSTING_NAME,''),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),nvl(LOCATION_NAME,''),nvl(ONSITE_POSTING_ADDRESS_1,''), "
					+ " nvl(ONSITE_POSTING_ADDRESS_2,''),nvl(ONSITE_POSTING_ADDRESS_3,''), "
					+ " nvl(ONSITE_POSTING_PHONE,''),nvl( ONSITE_POSTING_EMAIL,'') FROM HRMS_ONSITE_POSTING"
					+ "  INNER JOIN HRMS_LOCATION on(HRMS_LOCATION.LOCATION_CODE=HRMS_ONSITE_POSTING.ONSITE_POSTING_LOCATION) "
					+ " WHERE  ONSITE_POSTING_ID =" + para[0] + "";

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				onsiteposting.setSiteType(checkNull(String.valueOf(data[0][0])));
				onsiteposting.setSiteCode(checkNull(String.valueOf(data[0][1])));
				onsiteposting.setSiteAbbreviation(checkNull(String.valueOf(data[0][2])));
				onsiteposting.setSiteName(checkNull(String.valueOf(data[0][3])));
				onsiteposting.setIsActive(checkNull(checkNull(String.valueOf(data[0][4]))));
				onsiteposting.setSiteLocation(checkNull(String.valueOf(data[0][5])));
				onsiteposting.setSiteAddress1(checkNull(String.valueOf(data[0][6])));
				onsiteposting.setSiteAddress2(checkNull(String.valueOf(data[0][7])));
				onsiteposting.setSiteAddress3(checkNull(String.valueOf(data[0][8])));
				onsiteposting.setPhone(checkNull(String.valueOf(data[0][9])));
				onsiteposting.setEmailId(checkNull(String.valueOf(data[0][10])));

			   }
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
/*
 *  to generate the report
 */
	public void generateReport(OnsitePosting onsiteposting,
			HttpServletResponse response, String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nOnsite Posting Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Onsite Posting Master.Pdf");
		String queryDes = "SELECT DECODE(ONSITE_POSTING_TYPE,'C','Client','V','Vendor','P',"
				+ " 'Partner'),ONSITE_POSTING_CODE,ONSITE_POSTING_ABBR,ONSITE_POSTING_NAME, DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') "
				+ " FROM HRMS_ONSITE_POSTING ORDER BY ONSITE_POSTING_CODE";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][6];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
				Data[i][5] = data[i][4];
				j++;
			}
			int cellwidth[] = { 10, 20, 20, 20, 20, 10 };
			int alignment[] = { 1, 0, 0, 0, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

}
