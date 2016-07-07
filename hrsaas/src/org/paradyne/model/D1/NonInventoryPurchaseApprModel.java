package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.NonInventoryPurchaseBean;
import org.paradyne.bean.D1.WorkersCompInjury;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.leave.RegularizationModel;

public class NonInventoryPurchaseApprModel extends ModelBase {
	public void input(NonInventoryPurchaseBean bean, HttpServletRequest request) {

		String query = "SELECT TRACKING_NO, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(NON_INVNTY_APPL_DATE,'DD-MM-YYYY') "
				+ "	,HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID,NON_PURCHASE_ID FROM HRMS_D1_NON_INVENTORY_PURCHASE "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID) ";
		/*
		 * if (bean.isGeneralFlag()) { query += " WHERE
		 * HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID=" +
		 * bean.getUserEmpId(); } else {
		 *  }
		 */

		query += " WHERE 1=1 ";

		String pending = query;
		String logistic = query;
		if (bean.getFlag().equals("")) {
			pending = pending + " AND NON_INVNTY_STATUS IN('P') ";
			logistic = logistic + " AND NON_INVNTY_STATUS IN('L' ) ";
		} else if (bean.getFlag().equals("AA")) {
			pending = pending + " AND NON_INVNTY_STATUS='A'  ";
			logistic = logistic + " AND NON_INVNTY_STATUS='A'  ";
		} else if (bean.getFlag().equals("RR")) {
			pending = query + " AND NON_INVNTY_STATUS='R'  ";
			logistic = logistic + " AND NON_INVNTY_STATUS='R'  ";
		}

		String finalQuery = pending + " AND (CHANGE_MANG_CODE="
				+ bean.getUserEmpId() + " OR APPROVER_CODE="
				+ bean.getUserEmpId() + ") ";
		bean.setListDraft(null);
		Object[][] objApprove = getSqlModel().getSingleResult(
				finalQuery + "   ORDER BY NON_PURCHASE_ID DESC");
		boolean check = false;
		if (objApprove != null && objApprove.length > 0) {
			check = true;
		}

		/*
		 * if(checkReporting(bean)){ logistic = logistic + " ORDER BY
		 * HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC "; objApprove =
		 * getSqlModel().getSingleResult(logistic); check=true; }
		 */

		String apprRejectQuery = "  SELECT distinct TRACKING_NO, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(NON_INVNTY_APPL_DATE,'DD-MM-YYYY') 	,HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID,HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID FROM HRMS_D1_NON_INVENTORY_PURCHASE "
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID)"
				+ "	 INNER JOIN HRMS_D1_NON_INVENTORY_PATH  ON(HRMS_D1_NON_INVENTORY_PATH.NON_INVENTORY_ID=HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID AND NON_INVENTORY_APPROVER="
				+ bean.getUserEmpId() + ") " + "	WHERE 1=1  ";

		if (bean.getFlag().equals("AA")) {
			apprRejectQuery += "  AND NON_INVNTY_STATUS IN('A','L') ORDER BY HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC ";
			objApprove = getSqlModel().getSingleResult(apprRejectQuery);
			check = true;
		} else if (bean.getFlag().equals("RR")) {
			apprRejectQuery += "  AND NON_INVNTY_STATUS IN('R') ORDER BY HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC ";
			objApprove = getSqlModel().getSingleResult(apprRejectQuery);
			check = true;
		}

		if (checkReporting(bean)) {
			logistic = finalQuery + " UNION " + logistic;
			logistic += "  ORDER BY NON_PURCHASE_ID DESC  ";
			objApprove = getSqlModel().getSingleResult(logistic);
			check = true;
		}

		String[] pageIndexAppr = setData(bean, request, objApprove,
				"totalPage", "pageNo", bean.getMyPage(), "");

		if (objApprove != null && objApprove.length > 0 && check) {
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
					.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
				NonInventoryPurchaseBean bean1 = new NonInventoryPurchaseBean();
				bean1.setIttEmployeeToken(checkNull(String
						.valueOf(objApprove[i][0])));
				bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objApprove[i][2]));
				// bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
				bean1.setIttnonInventoryCode(String.valueOf(objApprove[i][4]));
				list.add(bean1);
				if (bean.getFlag().equals("")) {
					bean1.setButtonName("View/Approve Application");
				} else {
					bean1.setButtonName("View Application");
				}
			}
			bean.setListDraft(list);
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

	/**
	 * 
	 */

	public String[] setData(NonInventoryPurchaseBean bean,
			HttpServletRequest request, Object[][] data, String totalPage,
			String pageNo, String page, String type) {
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(page,
				data.length, 20);
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
		request.setAttribute(totalPage, Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute(pageNo, Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1")) {
			bean.setMyPage("1");
			if (type.equals("1")) {
				bean.setMyPage1("1");
			}
			if (type.equals("2")) {
				bean.setMyPage2("1");
			}
		}
		return pageIndex;
	}

	/**
	 * METHOD FOR APPROVED
	 * 
	 * @param nonInentCode
	 * @param request
	 * @return
	 */

	public boolean approve(HttpServletRequest request, String nonInentCode,
			String approverCode, String Comments, String status,
			String ppoFile, String ppoNo, String[] files) {
		boolean flag = false;
		/**
		 * CHECK DEFOULT MANAGER
		 */
		String finalApprove = status;
		String chkDefoult = " SELECT CHANGE_MANG_CODE,NON_INVNTY_STATUS,NON_INVNTY_EMP_ID FROM HRMS_D1_NON_INVENTORY_PURCHASE WHERE NON_PURCHASE_ID="
				+ nonInentCode + " ";
		Object[][] chkObj = getSqlModel().getSingleResult(chkDefoult);
		/*
		 * if(chkObj !=null && chkObj.length>0 &&
		 * String.valueOf(chkObj[0][0]).equals("A")||String.valueOf(chkObj[0][0]).equals("Z")||String.valueOf(chkObj[0][0]).equals("R")||String.valueOf(chkObj[0][0]).equals("B")){
		 * return "Application has already been processed."; }
		 */

		Object[][] logisticAppr = getLogisticApprover();
		if (status.equals("A") && logisticAppr != null
				&& logisticAppr.length > 0) {
			if (chkObj != null && chkObj.length > 0
					&& String.valueOf(chkObj[0][1]).equals("P")) {
				/*
				 * SEND DIRECT TO LOGISTIC
				 */
				status = "L";
			} else if (chkObj != null && chkObj.length > 0
					&& String.valueOf(chkObj[0][1]).equals("L")) {
				/*
				 * APPROVED FROM LOGISTIC
				 */
				status = "A";
			}
		}
		Object[][] upData = new Object[1][2];
		upData[0][0] = ppoFile;
		upData[0][1] = ppoNo;
		String updLogistic = " UPDATE HRMS_D1_NON_INVENTORY_PURCHASE  SET NON_INVNTY_STATUS='"
				+ status
				+ "',NON_INVNTY_PPO=?,NON_INVNTY_PPO_NO=? WHERE 	NON_PURCHASE_ID="
				+ nonInentCode;
		flag = getSqlModel().singleExecute(updLogistic, upData);

		if (flag) {
			// INSERT INTO PATH
			String qqq = "INSERT INTO HRMS_D1_NON_INVENTORY_PATH(NON_INVENTORY_ID,NON_INVENTORY_APPROVER,NON_INVENTORY_COMMENTS,NON_INVENTORY_DATE,NON_INVENTORY_PATH_ID,NON_INVENTORY_STATUS) "
					+ "	VALUES(?,?,?,SYSDATE,(SELECT NVL(MAX(NON_INVENTORY_PATH_ID),0)+1 FROM HRMS_D1_NON_INVENTORY_PATH),?)";
			Object[][] obj = new Object[1][4];
			obj[0][0] = nonInentCode;
			obj[0][1] = approverCode;
			obj[0][2] = Comments;
			obj[0][3] = status;
			flag = getSqlModel().singleExecute(qqq, obj);
			/*
			 * check logistic approver present or not
			 */
			Object[][] groupEmail = getLogisticEmailID();
			if (finalApprove.equals("A") && logisticAppr != null
					&& logisticAppr.length > 0 && chkObj != null
					&& chkObj.length > 0
					&& String.valueOf(chkObj[0][1]).equals("P")) {
				try {

					System.out.println("manager to logistics");
					sendMailMethodGroup(
							"Non Inventory purchase applicationl from  first approver to Logistic approver",
							approverCode, String.valueOf(logisticAppr[0][0]),
							nonInentCode, null, null, request, groupEmail,
							false, files);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					sendMailMethod(
							"Non Inventory purchase applicationl to employee for first approval",
							approverCode, String.valueOf(chkObj[0][2]),
							nonInentCode, null, null, request, null, true,
							files);
				} catch (Exception e) {
					// TODO: handle exception
				}
				//
			} else if (chkObj != null && chkObj.length > 0
					&& String.valueOf(chkObj[0][1]).equals("L")) {
				try {
					sendMailMethod(
							"Non Inventory purchase applicationl to employee form logistic final approval",
							approverCode, String.valueOf(chkObj[0][2]),
							nonInentCode, null, null, request, null, true,
							files);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					/**
					 * MAIL TO GROUP EMAIL
					 */
					sendMailMethodGroup(
							"Non Inventory purchase applicationl to employee form logistic final approval",
							approverCode, String.valueOf(chkObj[0][2]),
							nonInentCode, null, null, request, groupEmail,
							false, files);
				} catch (Exception e) {
					// TODO: handle exception
				}

			} else if (finalApprove.equals("R") || finalApprove.equals("B")) {
				try {
					sendMailMethod(
							"Non Inventory purchase applicationl to employee form logistic final approval",
							approverCode, String.valueOf(chkObj[0][2]),
							nonInentCode, null, null, request, null, true,
							files);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}

		return flag;
	}

	public boolean checkReporting(NonInventoryPurchaseBean bean) {
		boolean rr = false;
		String query = "SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('L')  AND APP_SETTINGS_EMP_ID= "
				+ bean.getUserEmpId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			rr = true;
		} else {
			rr = false;
		}

		return rr;
	}

	public String sendMailMethod(String tempName, String fromEmp,
			String approveCode, String applicationCode

			, String[] link_param, String[] link_label,
			HttpServletRequest request, Object[][] getLogisticEmailID,
			boolean isSendMail, String[] files) throws Exception {
		try {
			Object[][] eventData = null;
			Object[][] templateData = null;
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);

			String templateQuery = "SELECT TEMPLATE_NAME "
					+ " FROM HRMS_EMAILTEMPLATE_HDR  "
					+ " WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"
					+ tempName + "'";
			templateData = model.getSqlModel().getSingleResult(templateQuery);
			// if(templateData !=null && templateData.length>0){
			String templateName = tempName.trim();
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);

			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
																				// EMAIL
			if (!fromEmp.equals("")) {
				templateQuery1.setParameter(1, fromEmp);
			}

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
																				// EMAIL
			templateQuery2.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, fromEmp);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery10 = template.getTemplateQuery(10);
			templateQuery10.setParameter(1, applicationCode);

			template.configMailAlert();
			if (link_param != null && link_param.length > 0) {
				template.addOnlineLink(request, link_param, link_label);
			}

			/*
			 * SEND MAIL TO GROUP EMAILS
			 */
			if (getLogisticEmailID != null && getLogisticEmailID.length > 0) {
				template.sendApplicationMailToGroups(getLogisticEmailID);
			}
			if (isSendMail) {
				if(files !=null && files.length>0){
					template.sendApplMailWithAttachment(files);
				}
				else{
				template.sendApplicationMail();
				}
			}
			template.clearParameters();
			template.terminate();
			// }

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}

	public String sendMailMethodGroup(String tempName, String fromEmp,
			String approveCode, String applicationCode

			, String[] link_param, String[] link_label,
			HttpServletRequest request, Object[][] getLogisticEmailID,
			boolean isSendMail, String[] files) throws Exception {
		try {
			Object[][] eventData = null;
			Object[][] templateData = null;
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);

			String templateQuery = "SELECT TEMPLATE_NAME "
					+ " FROM HRMS_EMAILTEMPLATE_HDR  "
					+ " WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"
					+ tempName + "'";
			templateData = model.getSqlModel().getSingleResult(templateQuery);
			// if(templateData !=null && templateData.length>0){
			String templateName = tempName.trim();
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);

			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
																				// EMAIL
			if (!fromEmp.equals("")) {
				templateQuery1.setParameter(1, fromEmp);
			}

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
																				// EMAIL
			templateQuery2.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, fromEmp);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery10 = template.getTemplateQuery(10);
			templateQuery10.setParameter(1, applicationCode);

			template.configMailAlert();
			if (link_param != null && link_param.length > 0) {
				template.addOnlineLink(request, link_param, link_label);
			}

			/*
			 * SEND MAIL TO GROUP EMAILS
			 */
			System.out.println("Inside Group mail*****1 : " + isSendMail);
			if (getLogisticEmailID != null && getLogisticEmailID.length > 0) {
				System.out.println("Inside Group mail*****22");
				template.sendApplicationMailToGroups(getLogisticEmailID,files);
			}
			if (isSendMail) {
				if(files !=null && files.length>0){
					template.sendApplMailWithAttachment(files);
				}
				else{
					template.sendApplicationMail();	
				}
				
			}
			template.clearParameters();
			template.terminate();
			// }

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}

	public Object[][] getLogisticApprover() {
		Object[][] data = null;
		String query = "SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('L') AND APP_SETTINGS_EMP_ID IS NOT NULL";
		data = getSqlModel().getSingleResult(query);
		return data;
	}

	public Object[][] getLogisticEmailID() {
		Object[][] data = null;
		String query = "SELECT APP_EMAIL_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('L') AND  APP_EMAIL_ID IS NOT NULL ";
		data = getSqlModel().getSingleResult(query);
		return data;
	}

}
