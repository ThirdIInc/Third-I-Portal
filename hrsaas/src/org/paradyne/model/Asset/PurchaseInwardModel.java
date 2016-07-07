/**
 * 
 */
package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.PurchaseInward;
import org.paradyne.bean.Asset.PurchaseOrderApproval;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;

/**
 * @author Mangesh Jadhav 22-08-2008 PurchaseInwardModel class to write business
 *         logic to show the purchase orders for the Purchase inward
 */
public class PurchaseInwardModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.model.Asset.PurchaseInwardModel.class);

	/*
	 * method name : showList purpose : show the approved purchase Orders which
	 * comes under the Employee who is logged in. return type : void parameter :
	 * PurchaseInward instance
	 */
	public void showList(PurchaseInward bean, String orderStatus,
			HttpServletRequest request) {

		try {
			String status = "PE";
			String label = "Pending List";
			
			System.out.println("orderStatus                       "+orderStatus);
			if (orderStatus.equals("C")) {
				status = "CL";
				bean.setPendingFlag("false");
				label = "Cancelled List";
			} // end of if
			else if (orderStatus.equals("I")) {
				status = "PS";
				bean.setPendingFlag("false");
				label = "Inward List";
			} // end of if
			else {
				status = "PE";
				bean.setPendingFlag("true");
				label = "Pending List";
			}// end of else
			/*
			 * get the employee codes which are coming under warehouse for which
			 * the employee who is logged in is responsible
			 * 
			 */
			String empIdQuery = "SELECT EMP_ID FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_WAREHOUSE_BRANCH ON (HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE)"
					+ " WHERE WAREHOUSE_RESPONSIBLE_PERSON="
					+ bean.getUserEmpId();
			try {
				String query = "SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
						+ " HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS "
						+ " FROM HRMS_ASSET_PURCHASE_HDR "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
						+ " WHERE  PURCHASE_STATUS='A' AND INWARD_STATUS='"
						+ status
						+ "' AND PURCHASE_EMP_CODE IN("
						+ empIdQuery
						+ ")";
				logger.info("query for inward ==" + query);
				ArrayList<Object> appList = new ArrayList<Object>();
				Object[][] result = getSqlModel().getSingleResult(query);
				if (result != null && result.length != 0) {
					/*
					 * setting the retrieved data in the list
					 * 
					 */
					for (int i = 0; i < result.length; i++) {
						PurchaseOrderApproval approval = new PurchaseOrderApproval();
						approval.setPurchaseCode(String.valueOf(result[i][0]));
						logger.info("purchaseCode of approval"
								+ approval.getPurchaseCode());
						approval.setEmpID(String.valueOf(result[i][3]));
						approval.setEmpToken(String.valueOf(result[i][1]));
						approval.setEmpName(String.valueOf(result[i][2]));
						approval.setOrderDate(String.valueOf(result[i][4]));
						approval.setCheckStatus(String.valueOf(result[i][6]));

						logger.info("size of list==" + appList.size());

						bean.setNoData("false");
						appList.add(approval);

					} // end of for loop

					bean.setListLength(String.valueOf(appList.size()));
				} // end of if
				else {
					bean.setNoData("true");
					bean.setListLength("0");
				} // end of else

				bean.setRecordList(appList);
			} catch (Exception e) {
				logger.error("Exception in setting the list==" + e);
				e.printStackTrace();
			}
			request.setAttribute("stat", label);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * method name : cancelOrder purpose : cancels the purchase order. return
	 * type : boolean parameter : PurchaseInward instance
	 */
	public boolean cancelOrder(PurchaseInward bean) {
		boolean result = false;
		try {
			/*
			 * Query to update the Order status to cancel
			 * 
			 */
			String cancelQuery = "UPDATE HRMS_ASSET_PURCHASE_HDR SET INWARD_STATUS = 'CL' WHERE PURCHASE_CODE ="
					+ bean.getHiddenPurchaseCode();
			result = getSqlModel().singleExecute(cancelQuery);
			if (result) {
				/*
				 * try { Object[][] to_mailIds =new Object[1][1]; Object[][]
				 * from_mailIds =new Object[1][1];
				 * to_mailIds[0][0]=bean.getEmpCodeHidden();
				 * from_mailIds[0][0]=bean.getUserEmpId();
				 * 
				 * MailUtility mail=new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				 * mail.sendMail(to_mailIds, from_mailIds,"Purchase", "", "C");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in sendmail()=="+e); }
				 */

				// ------------------------Process Manager
				// Alert------------------------start
				ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
				processAlerts.initiate(context, session);

				String applnID = bean.getHiddenPurchaseCode();
				String module = "Purchase Inward";
				processAlerts.removeProcessAlert(applnID, module);
				// ------------------------Process Manager
				// Alert------------------------end
			} // end of if
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
