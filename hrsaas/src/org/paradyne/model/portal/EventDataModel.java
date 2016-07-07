package org.paradyne.model.portal;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.portal.EventDataBean;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.admin.srd.SendMailModel;

public class EventDataModel extends ModelBase {

	public void getGlofestData(HttpServletRequest request, String category,
			String eventCode, String year, EventDataBean bean) {
		try {
			String yearQuery = " SELECT DISTINCT EVENT_YEAR ,(EVENT_YEAR)+1 FROM HRMS_EVENT_MASTER WHERE 1=1";
			if (category != null && !category.equals("null") && !category.equals("")) {
				yearQuery += " AND EVENT_CATEGORY_CODE=" + category;
			}
			yearQuery += " AND  HRMS_EVENT_MASTER.EVENT_CODE=" + eventCode
						+ " ORDER BY EVENT_YEAR DESC ";
			Object yearObj[][] = getSqlModel().getSingleResult(yearQuery);
			if (yearObj != null && yearObj.length > 0) {
				bean.setYearFlag(true);
			}
			request.setAttribute("yearObj", yearObj);
			if (year == null || year.equals("null")) {
				if (yearObj != null && yearObj.length > 0) {
					year = String.valueOf(yearObj[0][0]);
				}
			}
			request.setAttribute("yearValue", year);
			String currentyearQuery = " SELECT EVENT_YEAR ,(EVENT_YEAR)+1 ,EVENT_NAME,EVENT_CODE FROM HRMS_EVENT_MASTER "
					+ " WHERE 1=1";
			if (category != null && !category.equals("null") &&   !category.equals("")) {
				currentyearQuery += "AND EVENT_CATEGORY_CODE=" + category;
			}
			if (!(year == null)  && !(year.equals("null"))) {
				currentyearQuery += " AND EVENT_YEAR=" + year;
			}
			currentyearQuery += " AND  HRMS_EVENT_MASTER.EVENT_CODE=" + eventCode;
			
			currentyearQuery += " ORDER BY EVENT_YEAR DESC ,EVENT_CODE DESC ";

			if (eventCode == null || eventCode.equals("null")) {
				Object currentyearQueryObj[][] = getSqlModel().getSingleResult(
						currentyearQuery);
				if (currentyearQueryObj != null
						&& currentyearQueryObj.length > 0) {
					eventCode = String.valueOf(currentyearQueryObj[0][3]);
				}
			}
			String imageQuery = "  SELECT EVENT_IMGPATH from  HRMS_EVENT_MASTER_DTL  "
					+ " INNER JOIN  HRMS_EVENT_MASTER ON ( HRMS_EVENT_MASTER.EVENT_CODE= HRMS_EVENT_MASTER_DTL.EVENT_CODE) "
					+ " WHERE EVENT_UPLOAD_CATEGORY='P' ";
			if (category != null && !category.equals("null") && !category.equals("")) {
				imageQuery += "and EVENT_CATEGORY_CODE=" + category;
			}
			imageQuery += " AND  HRMS_EVENT_MASTER.EVENT_CODE=" + eventCode;
			Object[][] imageData = getSqlModel().getSingleResult(imageQuery);
			if (imageData != null && imageData.length > 0) {
				bean.setImageDataFlag(true);
			}
			request.setAttribute("glofestImageObj", imageData);
			String query = " SELECT NVL(EVENT_NAME,''),EVENT_YEAR,TO_CHAR(EVENT_DATE,'DD-MM-YYYY'),nvl(EVENT_LOCATION,'') "
					+ " , NVL(EVENT_DESCRIPTION,'') FROM   HRMS_EVENT_MASTER "
					+ " WHERE 1=1";
			if (category != null && !category.equals("null") && !category.equals("")) {
				query += "AND EVENT_CATEGORY_CODE=" + category;
			}
			query += " AND  HRMS_EVENT_MASTER.EVENT_CODE=" + eventCode;
			Object[][] aboutEventData = getSqlModel().getSingleResult(query);
			if (aboutEventData != null && aboutEventData.length > 0) {
				bean.setEventDataFlag(true);
			}
			request.setAttribute("aboutEventData", aboutEventData);
			String eventNameQuery = " SELECT EVENT_Name FROM HRMS_EVENT_MASTER "
					+ " WHERE 1=1";
			if (category != null && !category.equals("null") && !category.equals("")) {
				eventNameQuery += "AND EVENT_CATEGORY_CODE=" + category;
			}
			eventNameQuery += " AND HRMS_EVENT_MASTER.EVENT_CODE=" + eventCode;
			Object[][] eventNameQueryObj = getSqlModel().getSingleResult(
					eventNameQuery);
			request.setAttribute("eventNameQueryObj", eventNameQueryObj);
			String videoquery = "  SELECT EVENT_IMGPATH FROM  HRMS_EVENT_MASTER_DTL  "
					+ " INNER JOIN  HRMS_EVENT_MASTER ON ( HRMS_EVENT_MASTER.EVENT_CODE= HRMS_EVENT_MASTER_DTL.EVENT_CODE) "
					+ " WHERE EVENT_UPLOAD_CATEGORY='V' ";
			if (category != null && !category.equals("null") && !category.equals("")) {
				videoquery += " and EVENT_CATEGORY_CODE=" + category;
			}
			videoquery += " AND  HRMS_EVENT_MASTER.EVENT_CODE=" + eventCode;
			Object[][] videoData = getSqlModel().getSingleResult(videoquery);

			if (videoData != null && videoData.length > 0) {
				bean.setVideoDataFlag(true);
			}
			request.setAttribute("aboutVideoData", videoData);
			String contentquery = "  SELECT EVENT_IMGNAME,EVENT_IMGPATH FROM  HRMS_EVENT_MASTER_DTL  "
					+ " INNER JOIN  HRMS_EVENT_MASTER ON ( HRMS_EVENT_MASTER.EVENT_CODE= HRMS_EVENT_MASTER_DTL.EVENT_CODE) "
					+ " WHERE EVENT_UPLOAD_CATEGORY='C' ";
			if (category != null && !category.equals("null") && !category.equals("")) {
				contentquery += " and EVENT_CATEGORY_CODE=" + category;
			}

			contentquery += " AND  HRMS_EVENT_MASTER.EVENT_CODE=" + eventCode;
			Object[][] contentData = getSqlModel()
					.getSingleResult(contentquery);
			if (contentData != null && contentData.length > 0) {
				bean.setContentDataFlag(true);
			}
			request.setAttribute("aboutContentData", contentData);
			String eventYearList = "   SELECT EVENT_CODE, EVENT_NAME FROM HRMS_EVENT_MASTER "
					+ " WHERE EVENT_YEAR=" + year;
			if (category != null && !category.equals("null") && !category.equals("")) {
				eventYearList += " AND EVENT_CATEGORY_CODE=" + category;
			}
			Object[][] eventYearListObj = getSqlModel().getSingleResult(
					eventYearList);
			request.setAttribute("eventYearListObj", eventYearListObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFeedbackDetails() {

	}

	public boolean saveFeedbackDetails(String categoryCode, String title,
			String desc, String empCode) {
		boolean result = false;
		try {			
			Object saveObj[][] = new Object[1][4];
			saveObj[0][0] = categoryCode;
			saveObj[0][1] = empCode;
			saveObj[0][2] = title;
			saveObj[0][3] = desc;
			String query = "  INSERT INTO  HRMS_EVENT_FEEDBACK (CATEGORY_CODE, EMP_CODE, FEEDBACK_TITLE, FEEDBACK_DESC, FEEDBACK_DATE) "
					+ " VALUES(?,?,?,?,SYSDATE) ";
			result = getSqlModel().singleExecute(query, saveObj);

			if (result) {
				try {
					sendFeedBackMail(desc, categoryCode, empCode, title);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getRewardData(HttpServletRequest request, EventDataBean bean, String categoryCode,
			String eventCode, String year, String awardType, String check) {
		try {
			String yearQuery = " SELECT FROM_YEAR, TO_YEAR FROM ("
								+ " SELECT DISTINCT  CASE WHEN TO_NUMBER(TO_CHAR(AWARD_DATE,'MMDD'))>=401 " 
								+ " THEN TO_NUMBER(TO_CHAR(AWARD_DATE,'YYYY')) " 
								+ " ELSE TO_NUMBER(TO_CHAR(AWARD_DATE,'YYYY'))-1 "
								+ " END  FROM_YEAR, "
								+ " CASE WHEN TO_NUMBER(TO_CHAR(AWARD_DATE,'MMDD'))>=401 "
								+ " THEN TO_NUMBER(TO_CHAR(AWARD_DATE,'YYYY'))+1" 
								+ " ELSE TO_NUMBER(TO_CHAR(AWARD_DATE,'YYYY')) "
								+ " END   TO_YEAR "
								+ " FROM HRMS_AWARD ) ORDER BY FROM_YEAR DESC";
			Object yearObj[][] = getSqlModel().getSingleResult(yearQuery);
			request.setAttribute("yearObj", yearObj);
			year=check;		
			if (year == null || year.equals("null")) {
				if (yearObj != null && yearObj.length > 0) {
					year = String.valueOf(yearObj[0][0]);
				}
			}
			request.setAttribute("yearValue", year);

			String awardTypeQuery = " SELECT AWARD_CODE,AWARD_TYPE FROM HRMS_AWARD_MASTER "
					+ " WHERE IS_ACTIVE='Y' ORDER BY AWARD_CODE ";
			Object awardTypeObj[][] = getSqlModel().getSingleResult(
					awardTypeQuery);

			request.setAttribute("awardTypeObj", awardTypeObj);			
			String typeId = "1";
			if (awardType == null || awardType.equals("null")
					|| awardType.equals("")) {
				typeId = "1";
			} else {
				typeId = awardType;
			}
			request.setAttribute("awardType", typeId);
			int nextYear = Integer.parseInt(year) + 1;
			
			int prevYear= Integer.parseInt(year);

			String awardListQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " NVL(DEPT_NAME,''),CENTER_NAME,RANK_NAME ,NVL(AWARD_PHOTO,'nophoto'), "
					+ " HRMS_AWARD.EMP_ID FROM HRMS_AWARD "
					+ " LEFT JOIN HRMS_EMP_OFFC ON ( HRMS_EMP_OFFC.EMP_ID  = HRMS_AWARD.EMP_ID AND EMP_STATUS='S') "
					+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT "
					+ " LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ " WHERE  AWARD_TYPE_ID=" + typeId
					+ " AND AWARD_ISACTIVEINDASHLET='Y'"
					+ " AND HRMS_EMP_OFFC.EMP_DIV IN(SELECT HRMS_EMP_OFFC.EMP_DIV FROM HRMS_EMP_OFFC"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID ="+ bean.getUserEmpId()+")"
					+ "   AND  AWARD_DATE>= TO_DATE('01-04-" + String.valueOf(yearObj[prevYear][0])
						+ "', 'DD-MM-YYYY') AND  AWARD_DATE<= TO_DATE('31-03-"
						+ String.valueOf(yearObj[prevYear][1]) + "', 'DD-MM-YYYY') ";

			Object awardListObj[][] = getSqlModel().getSingleResult(
					awardListQuery);
			request.setAttribute("awardListObj", awardListObj);
			String awardTypeNameQuery = " SELECT AWARD_TYPE FROM HRMS_AWARD_MASTER "
					+ " WHERE AWARD_CODE=" + typeId + " ORDER BY AWARD_CODE ";
			Object awardTypeNameObj[][] = getSqlModel().getSingleResult(
					awardTypeNameQuery);
			request.setAttribute("awardTypeNameObj", awardTypeNameObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendFeedBackMail(String feedbackDescription,
			String categoryCode, String loginEmpId, String title) {
		String result = "";
		try {
			String query = " SELECT EVENT_EMAIL,DECODE(EVENT_CODE,'1','Glofest','2','GCL','3','GTL Care','4','MY Idea','5','Connexions','6','Rewards','7','Colour Band','8','Helpdesk','9','Induction','10','Knowledge Forum') from HRMS_PORTAL_EVENT "
					+ " WHERE  EVENT_CODE=" + categoryCode;
			Object dataObj[][] = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				String[] to_Emailid = new String[1];
				to_Emailid[0] = String.valueOf(dataObj[0][0]);
				String feedbackCategory = String.valueOf(dataObj[0][1]);
				String[] sub = { " " + feedbackCategory + " Feedback -" + title };
				String[] msg = new String[1];
				String name = "";
				String empName = "";
				String empNamequery = " SELECT EMP_FNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC "
						+ " WHERE EMP_ID=" + loginEmpId;
				Object empNamequeryObj[][] = getSqlModel().getSingleResult(
						empNamequery);
				if (empNamequeryObj != null && empNamequeryObj.length > 0) {
					empName = String.valueOf(empNamequeryObj[0][0]);
				}
				String emailAddressQuery = " SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS "
						+ " WHERE ADD_TYPE='P' AND EMP_ID=" + loginEmpId;
				Object emailAddressQueryObj[][] = getSqlModel()
						.getSingleResult(emailAddressQuery);
				String fromEmailID[] = null;
				if (emailAddressQueryObj != null
						&& emailAddressQueryObj.length > 0) {
					fromEmailID = new String[1];
					fromEmailID[0] = String.valueOf(emailAddressQueryObj[0][0]);
				}
				msg[0] = getMessage(feedbackDescription, name, empName,
						feedbackCategory, title);
				MailUtility mod = new MailUtility();
				mod.initiate(context, session);
				mod.sendMail(to_Emailid, fromEmailID, sub[0], msg[0], "", "",
						true);
				mod.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getMessage(String feedBackDescription, String name,
			String empName, String feedbackCategory, String title) {
		String[] htmlText = new String[1];
		String tempMsg = "";
		htmlText[0] = "<html> "
				+ "<style>"
				+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
				+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
				+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
				+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
				+ ".style15 {color: #CC0000} "
				+ ".style16 {color: #D23A49} "
				+ "</style>"
				+ "	<body> "
				+ "<table width='60%' border='0' cellpadding='0' cellspacing='0'>"
				+ "<tr> "
				+ "<td width='66%'>"
				+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
				+ "<tr> " + "<td><p>Dear&nbsp;<b>" + ""
				+ feedbackCategory
				+ " Management Team "
				+ ""
				+ "</b>, </p><br /> "
				+ "a feedback about <strong>"
				+ feedbackCategory
				+ " </strong> has been forwarded to you.<br /> "
				+ "<br>Feedback sent by :"
				+ empName
				+ ".</p> "
				+ "<br /> Feedback Title: "
				+ title
				+ "<br /> Feedback Description: "
				+ feedBackDescription
				+ "<br /><p>Thank you for using Glodyne employee portal</p><br />"
				+ "</td> " + "</tr> " + "</table> " + "</td> " + "</tr> "

				+ "</table> " + "</body> " + "</html> ";
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		tempMsg = model.getMassMessage(htmlText[0]);
		return tempMsg;
	}
}
