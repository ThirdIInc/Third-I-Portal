package org.paradyne.model.settings;

import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.D1.BusinessRequirementDocument;
import org.paradyne.bean.settings.SettingMaster;
import org.paradyne.bean.settings.TipsBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.BusinessRequirementDocumentModel;

public class TipsModel extends ModelBase {

	/**
	 * Nilesh Dhandare 18th Jan 2012.
	 * 
	 * @param tipsBean :
	 *            Used to get field data.
	 * @param status :
	 *            Checked for save.
	 * @param poolDir :
	 *            Path.
	 * @return int.
	 */
	public int saveTips(TipsBean tipsBean, String status, String poolDir)

	{
		try {
			int flagValue = 0;
			if (status.equals("save")) {
				Object[][] addLink = new Object[1][5];
				addLink[0][0] = String.valueOf(tipsBean.getTipsName());
				if (tipsBean.getUploadTs().equals("")) {
					addLink[0][1] = tipsBean.getLinkTs();
					addLink[0][3] = "L";
				} // END nested if
				else {
					addLink[0][1] = "../pages/upload/" + tipsBean.getUploadTs();
					addLink[0][3] = "D";
				}// END else
				if (tipsBean.getActiveTip().equals("true"))
					addLink[0][2] = "Y";
				else
					addLink[0][2] = "N";

				addLink[0][4] = tipsBean.getDivisionCode().trim();

				if (tipsBean.getHiddenCode_TS().equals("")) {
					getSqlModel().singleExecute(getQuery(1), addLink);
					flagValue = 1;
				} else {// y
					Object[][] hiddenCode = new Object[1][6];
					hiddenCode[0][0] = tipsBean.getTipsName();
					if (String.valueOf(addLink[0][3]).equals("D"))
						hiddenCode[0][1] = "../pages/upload/"
								+ tipsBean.getUploadTs();
					else
						hiddenCode[0][1] = tipsBean.getLinkTs();
					hiddenCode[0][2] = String.valueOf(addLink[0][3]);
					hiddenCode[0][3] = String.valueOf(addLink[0][2]);

					hiddenCode[0][4] = tipsBean.getDivisionCode().trim();

					hiddenCode[0][5] = tipsBean.getHiddenCode_TS();
					getSqlModel().singleExecute(getQuery(2), hiddenCode);
					flagValue = 2;
				}// END else y

				xml_Ts(poolDir);

			}// END if "save"

			Object[][] getLinkData = getSqlModel().getSingleResult(getQuery(3));
			System.out.println("getLinkData length here is  ============="
					+ getLinkData.length);
			ArrayList<Object> list_ts = new ArrayList<Object>();
			for (int i = 0; i < getLinkData.length; i++) {
				TipsBean beanLink_Ts = new TipsBean();
				beanLink_Ts.setLinkCode_TL(String.valueOf(getLinkData[i][0]));
				beanLink_Ts.setLinkName_TL(String.valueOf(getLinkData[i][1]));
				String link = String.valueOf(getLinkData[i][2]);
				if (String.valueOf(getLinkData[i][4]).equals("L")) {
					System.out.println("11111111111111111111111111111");
					beanLink_Ts.setLinkFile_TL(link);
				}// END if
				else
					beanLink_Ts.setLinkFile_TL(link.substring(link
							.lastIndexOf("/") + 1, link.length()));
				beanLink_Ts.setLinkActive_TL(String.valueOf(getLinkData[i][3]));

				list_ts.add(beanLink_Ts);
			}// END of loop x
			tipsBean.setList_TipsLink(list_ts);
			return flagValue;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Edit Corporate Info Settings
	 * 
	 * @param bean
	 */
	public void editTs(TipsBean bean) {
		try {
			String query = " SELECT TIP_LINKNAME, NVL(TIP_LINK,' '), TIP_STATUS , TIP_FLAG  FROM HRMS_SETTINGS_TIPS WHERE TIP_CODE = "
					+ bean.getHiddenCode_TS();
			Object resultData[][] = getSqlModel().getSingleResult(query);
			bean.setTipsName(String.valueOf(resultData[0][0]));
			bean.setActiveTip(String.valueOf(resultData[0][3]));
			String status = (String.valueOf(resultData[0][2]));
			System.out.println("status : " + status);
			if (status.equals("D")) {
				bean.setCheckFlag_TS("false");
				String link = String.valueOf(resultData[0][1]);
				int start = link.lastIndexOf("/") + 1;
				int end = link.length();
				String complete = link.substring(start, end);
				System.out.println("complete -------- " + complete);
				bean.setUploadTs(complete);
				System.out.println("UploadTs =-------------"
						+ bean.getUploadTs());

			}// END if
			if (status.equals("L")) {
				String linkName = String.valueOf(resultData[0][1]);// .substring(7,String.valueOf(resultData[0][1]).length());
				bean.setCheckFlag_TS("true");
				bean.setLinkTs(linkName);
			}// END else if
			String view = bean.getActiveTip().trim();
			if (view.equals("Y")) {
				bean.setActiveTip("true");
			} else {
				bean.setActiveTip("false");
			}

			String divisionQuery = "  SELECT NVL(TIP_DIVISION,'') FROM HRMS_SETTINGS_TIPS "
					+ "  WHERE TIP_CODE =" + bean.getHiddenCode_TS().trim();
			Object divisionObj[][] = getSqlModel().getSingleResult(
					divisionQuery);
			String str = "";
			if (divisionObj != null && divisionObj.length > 0) {
				String divNameQuery = " SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
						+ String.valueOf(divisionObj[0][0]) + ")";

				Object divNameQueryObj[][] = getSqlModel().getSingleResult(
						divNameQuery);
				if (divNameQueryObj != null && divNameQueryObj.length > 0) {
					Object[][] divisionDataObj = new Object[divNameQueryObj.length][1];
					for (int i = 0; i < divNameQueryObj.length; i++) {
						bean.setDivisionCode(String.valueOf(divisionObj[0][0]));
						divisionDataObj[i][0] = String
								.valueOf(divNameQueryObj[i][0]);
					}

					if (divisionDataObj != null && divisionDataObj.length > 0) {
						for (int j = 0; j < divisionDataObj.length; j++) {
							str += String.valueOf(j + 1) + ".";
							str += String.valueOf(divisionDataObj[j][0])
									+ "\n ";
							bean.setDivisionName(str);
						}

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deleting Corporate Settings details
	 * 
	 * @param bean
	 * @param poolDir
	 * @return boolean
	 */
	public boolean deleteTs(TipsBean bean, String poolDir) {
		try {
			Object[][] delCode = new Object[1][1];
			delCode[0][0] = bean.getHiddenCode_TS();
			boolean result = getSqlModel().singleExecute(getQuery(4), delCode);
			xml_Ts(poolDir);
			bean.setHiddenCode_TS("");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Nilesh Dhandare 18th Jan 2012. Method : xml_Ts(). Purpose : Writing XML
	 * 
	 * @param poolDir
	 * @return nothing.
	 */
	public void xml_Ts(String poolDir) {
		Object[][] xmlTipsInfoData = getSqlModel().getSingleResult(getQuery(5));
		if (!(poolDir == null || poolDir.equals("") || poolDir.equals(null)))
			poolDir = "\\" + poolDir;

		try {
			// XML write
			new XMLReaderWriter().write(buildDocument("HRHOME", "TIPS LINK",
					xmlTipsInfoData), poolDir);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * XML CREATION for HrWorK Communication
	 * 
	 * @param head
	 * @param subHead
	 * @param hrHome
	 * @return document creates XML
	 */
	public Document buildDocument(String head, String subHead, Object[][] hrHome) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(head);

		Element header;
		Element data;

		header = root.addElement("COMMUNICATION").addAttribute("name", subHead);
		for (int i = 0; i < hrHome.length; i++)
			data = header.addElement("Link").addAttribute("id",
					"" + hrHome[i][0].toString()).addAttribute("name",
					"" + hrHome[i][1].toString()).addAttribute("value",
					"" + hrHome[i][2].toString());
		return document;
	}

	
	/**
	 * Method : sendMassMail().
	 * Purpose : Mail Template to send mass mail to employee available in HRMS_EMP_ADDRESS. 
	 * @param tipsBean 
	 * 
	 */
	public void sendMassMail(TipsBean tipsBean) {
		try {
			
			TipsModel model = new TipsModel();
			model.initiate(context, session);
			String empQuery = "SELECT EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS ";
			 Object [][] data = getSqlModel().getSingleResult(empQuery);
			String code = null;
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("GLODYNE TIPS MAIL TO EMPLOYEES");
			template.getTemplateQueries();
			 
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, tipsBean.getUserEmpId());
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			templateQuery2.setParameter(1, code);

				String[] empData = null;
			
			if (data != null && data.length > 0) {
				empData = new String[data.length];
				for (int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}
			
			template.configMailAlert();
			
			if (empData != null && empData.length >0) {
				template.sendApplicationMailToKeepInfo(empData);
			}
			
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
}
