package org.paradyne.model.mypage;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.mypage.RightTileDataBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;
import org.struts.action.mypage.RightTileDataAction;

public class RightTileDataModel extends ModelBase {

	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(RightTileDataModel.class);
	
	public Object[][] processPollInfo(Document document, RightTileDataBean bean) {
		// TODO Auto-generated method stub


		String[][] question = new String[1][2];
		String[][] option = null;
		try {
			// Setting Poll Code and Poll Question from XML
			List nodes = document
					.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION");
			question = new String[nodes.size()][3];

			for (Iterator iter = nodes.iterator(); iter.hasNext();) {
				Element node = (Element) iter.next();
				question[0][1] = node.attributeValue("id");
				question[0][2] = node.attributeValue("name");
			}// end of loop

			bean.setPollCode(question[0][1]);
			bean.setQuesName(question[0][2]);

			// Setting Options for the corresponding Poll
			List node1 = document
					.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION/OPTION");
			option = new String[node1.size()][5];
			int count = 0;
			// logger.info("Poll code----------"
			// + homepage.getPollCode());
			String query = " SELECT POLL_CODE, POLL_QUESTION FROM HRMS_POLL_HDR WHERE POLL_CODE<"
					+ bean.getPollCode() + " ORDER BY POLL_CODE DESC ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				// employeePortal.setHmPrevFlag(true);
			}// end of if
			int maxIndex = 0;
			int prevValue = 0;
			for (Iterator iter = node1.iterator(); iter.hasNext();) {

				Element node2 = (Element) iter.next();
				option[count][0] = node2.attributeValue("id");
				option[count][1] = node2.attributeValue("name");
				// logger.info("option[count][1]======>"+option[count][1]);
				option[count][2] = node2.attributeValue("value");
				// logger.info("option[count][2]======>"+option[count][2]);
				option[count][3] = node2.attributeValue("color");
				option[count][4] = "N";
				if (prevValue <= Integer.parseInt(String
						.valueOf(option[count][2]))) {
					maxIndex = count;
					prevValue = Integer.parseInt(String
							.valueOf(option[count][2]));
				}
				count++;
			}// end of loop
			option[maxIndex][4] = "Y";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return option;

	
	}

	public void savePoll(HttpServletRequest request,
			HttpServletResponse response, String path, RightTileDataBean bean) {


		String pollCode = "";
		String optionCode = "";
		try {
			pollCode = request.getParameter("pollCode");
			optionCode = request.getParameter("optionCode");
			logger.info("PollCode          :"
					+ request.getParameter("pollCode"));
			logger.info("OptionCode          :"
					+ request.getParameter("optionCode"));

			bean.setPollCode(pollCode);
			bean.setOptionValue(optionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("home.getPollCode()===" + bean.getPollCode());

		String expiryQuery = "SELECT TO_CHAR(EXPIRY_DATE,'DD-MM-YYYY') FROM HRMS_POLL_HDR WHERE TO_DATE(SYSDATE,'dd-mm-yyyy') > TO_DATE(EXPIRY_DATE,'dd-mm-yyyy') "
				+ " AND POLL_CODE= " + bean.getPollCode();
		Object expiryData[][] = getSqlModel().getSingleResult(expiryQuery);

		if (expiryData != null && expiryData.length > 0) {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().write("<message>expiry</message>");
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			String pollCheck = "SELECT * FROM HRMS_POLL_EMP WHERE POLL_EMP_ID ="
					+ bean.getUserEmpId()
					+ " AND "
					+ "POLL_CODE="
					+ bean.getPollCode() + " ";
			Object pollData[][] = getSqlModel().getSingleResult(pollCheck);

			if (!(pollData.length > 0)) {

				String pollQuery = " INSERT INTO HRMS_POLL_EMP(POLL_EMP_ID,POLL_CODE,POLL_DATE,POLL_OPTION_CODE) "

						+ " VALUES ("
						+ bean.getUserEmpId()
						+ ","
						+ bean.getPollCode()
						+ ",to_date(sysdate,'dd-mm-yyyy'),"
						+ bean.getOptionValue() + ")  ";

				getSqlModel().singleExecute(pollQuery);
				Document doc = null;
				try {
					doc = writeInXML(new XMLReaderWriter() // Read
							// from
							// XML
							// on
							// load
							.parse(new File(path)), bean);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					new XMLReaderWriter().write(doc, path); // Write into XML on
					// submit
				} catch (Exception e) {
					// TODO: handle exception
				}

				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				try {
					response.getWriter().write("<message>valid</message>");
				} catch (Exception e) {
					// TODO: handle exception
				}

			} else {

				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				try {
					response.getWriter().write("<message>invalid</message>");
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
	
		
	}

	private Document writeInXML(Document document, RightTileDataBean bean) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		List nodes = document
				.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION");
		// Loop for Question
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			Element node = (Element) iter.next();
			if (node.attributeValue("id").equals(bean.getPollCode())) {
				List node1 = document
						.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION/OPTION");

				int count = 0;
				// Loop for Option
				for (Iterator iter1 = node1.iterator(); iter1.hasNext();) {
					Element node2 = (Element) iter1.next();
					if (bean.getOptionValue().equals(
							node2.attributeValue("id"))) {
						int value = Integer.parseInt(node2
								.attributeValue("value"));
						value = value + 1;
						node2.setAttributeValue("value", String.valueOf(value));

					}// end of nested if
					count++;
				}// end of inner loop(options)
			}// end of if
			node.attributeValue("name");
		}// end of outer loop(question)
		return document;
	
	}

}
