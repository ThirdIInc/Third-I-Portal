package org.paradyne.model.vendor;

/**
 * @ author Archana Salunkhe
 * @ purpose : Provide Invoice Acceptance Functionality
 * @ Date : 19-Apr-2012 
 */
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.vendor.InvoiceAcceptance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class InvoiceAcceptanceModel extends ModelBase {

	public void getListForAcceptance(InvoiceAcceptance invoiceAccept, HttpServletRequest request) {
		try {
/*			System.out.println("invoiceAccept.getLoginPartnerCode()-------"+invoiceAccept.getLoginPartnerCode());
			String checkIsAcknowledgeQuery = "  SELECT APPROVER_CODE FROM  VENDOR_REPORTING_STR "
					+ " WHERE APPROVER_TYPE='Ack'"
					+" AND APPROVER_CODE= " + invoiceAccept.getUserEmpId();

			Object[][] chkIsAckObj = getSqlModel()
					.getSingleResult(checkIsAcknowledgeQuery);*/
			Object [] pCodeObj = new Object[2];
			pCodeObj[0]= "A";
			pCodeObj[1]= invoiceAccept.getUserEmpId();
			/*if (chkIsAckObj != null
					&& chkIsAckObj.length > 0) {*/
				Object[][] acceptObj = getSqlModel().getSingleResult(
						getQuery(1),pCodeObj);
				if (acceptObj != null && acceptObj.length > 0) {
					/*For Paging*/
					invoiceAccept.setPendingAccLength("true");
					String[] pageIndex = Utility.doPaging(invoiceAccept.getMyPage(),
							acceptObj.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}					
					request.setAttribute("totalPage", Integer.parseInt(String
							.valueOf(pageIndex[2])));
					
					request.setAttribute("PageNo", Integer.parseInt(String
							.valueOf(pageIndex[3])));
					if (pageIndex[4].equals("1"))
						invoiceAccept.setMyPage("1");
					
					ArrayList<Object> pendingAccList = new ArrayList<Object>();
					for (int i = 0; i < acceptObj.length; i++) {						
						InvoiceAcceptance bean = new InvoiceAcceptance();
						bean.setPartnerNameIA(String.valueOf(acceptObj[0][0]));
						bean.setInvoiceDateIA(String.valueOf(acceptObj[0][1]));
						bean.setPartnerCodeIA(String.valueOf(acceptObj[0][2]));
						bean.setInvoiceIDIA(String.valueOf(acceptObj[0][3]));
						bean.setInvoiceStatusIA("Pending");
						pendingAccList.add(bean);
					}
					invoiceAccept.setAccButtonFlag("true");
					invoiceAccept.setPendingList(pendingAccList);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAcceptanceDetails(InvoiceAcceptance acceptBean,
			String invoiceCode) {
		try {
			Object[] invoiceObj = new Object[1];
			invoiceObj[0] = invoiceCode;
			Object[][] acceptDetailObj = getSqlModel().getSingleResult(
					getQuery(2), invoiceObj);
			if (acceptDetailObj != null && acceptDetailObj.length > 0) {
				acceptBean.setPartnerCodeIA(String
						.valueOf(acceptDetailObj[0][0]));
				acceptBean.setInvoiceDateIA(String
						.valueOf(acceptDetailObj[0][1]));
				acceptBean.setPartnerTypeIA(String
						.valueOf(acceptDetailObj[0][2]));
				acceptBean.setInvoiceFromDateIA(String
						.valueOf(acceptDetailObj[0][3]));
				acceptBean.setInvoiceToDateIA(String
						.valueOf(acceptDetailObj[0][4]));
				acceptBean.setProjectNameIA(String
						.valueOf(acceptDetailObj[0][5]));
				acceptBean.setAchivementIA(String
						.valueOf(acceptDetailObj[0][6]));
				acceptBean.setInvoiceNumberIA(String
						.valueOf(acceptDetailObj[0][7]));
				acceptBean.setInvoiceAttachedIA(String
						.valueOf(acceptDetailObj[0][8]));
				acceptBean.setInvoiceAmountIA(String
						.valueOf(acceptDetailObj[0][9]));
				acceptBean.setPartnerCommentsIA(String
						.valueOf(acceptDetailObj[0][10]));
				acceptBean.setInvoiceLevelIA(String
						.valueOf(acceptDetailObj[0][13]));
				acceptBean.setPartnerNameIA(String
						.valueOf(acceptDetailObj[0][15]));
				if (String.valueOf(acceptDetailObj[0][11]).equals("A")) {
					acceptBean.setDisburseFlg("false");
					acceptBean.setAccButtonFlag("true");
				} else {
					acceptBean.setDisburseFlg("true");
					acceptBean.setAccButtonFlag("false");
					acceptBean.setDisburseDate(String
							.valueOf(acceptDetailObj[0][16]));
				}
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	public boolean setApprovedDetails(InvoiceAcceptance invoiceBean, String invoiceCode) {
		boolean appFlag = false;
		try {
			Object[] invoiceObj = new Object[1];
			invoiceObj[0] = invoiceCode;
			Object[][] appDataObj = getSqlModel().getSingleResult(getQuery(4),
					invoiceObj);
			ArrayList<Object> apprverList = new ArrayList<Object>();
			if (appDataObj != null && appDataObj.length != 0) {
				for (int i = 0; i < appDataObj.length; i++) {
					InvoiceAcceptance bean = new InvoiceAcceptance();
					bean.setApproverIDIA(checkNull(String
							.valueOf(appDataObj[i][0])));
					bean.setApproverNameIA(checkNull(String
							.valueOf(appDataObj[i][1])));
					bean.setApproveDateIA((String.valueOf(appDataObj[i][2])));
					bean.setApproveStatusIA((checkNull(String
							.valueOf(appDataObj[i][3]))));
					if (String.valueOf(appDataObj[i][4]).equals("null")
							|| String.valueOf(appDataObj[i][4]) == null) {
						bean.setApproverComtIA("");
					} else {
						bean.setApproverComtIA(checkNull(String
								.valueOf(appDataObj[i][4])));
					}
					apprverList.add(bean);
					appFlag = true;
				}
			}
			invoiceBean.setAprCommentListIA(apprverList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appFlag;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public boolean updateRecordForAck(InvoiceAcceptance acceptBean, String invoiceCode){
	 boolean result=false;
		try {
			String disburseDt = acceptBean.getDisburseDate();
			String invoiceStatus = "Q";
			Object[][] paramObj = new Object[1][3];
			paramObj[0][0] = invoiceStatus;
			paramObj[0][1] = disburseDt;
			//paramObj[0][2] = acceptBean.getUserEmpId();
			paramObj[0][2] = invoiceCode;
			boolean outResult = getSqlModel().singleExecute(getQuery(5),
					paramObj);
			if (outResult) {
				System.out.println("acceptBean.getUserEmpId()-----"
						+ acceptBean.getUserEmpId());
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	public boolean sendBackRecordForAck(InvoiceAcceptance acceptBean, String invoiceCode){
		boolean result = false;
		try {
			Object[] paramObj = new Object[1];
			paramObj[0] = acceptBean.getPartnerCodeIA();
			System.out.println("acceptBean.getPartnerCodeIA()----------"
					+ acceptBean.getPartnerCodeIA());
			Object[][] approverObj = getSqlModel().getSingleResult(getQuery(6),
					paramObj);
			String disburseDt = acceptBean.getDisburseDate();
			if (approverObj != null && approverObj.length > 0) {
				String query = "UPDATE VENDOR_INVOICE SET EXPECTED_DISB_DATE = TO_DATE('"
						+ disburseDt
						+ "','DD-MM-YYYY'),"
						+ " INVOICE_STATUS='B',INVOICE_APPROVER="
						+ approverObj[0][0]
						+ ", INVOICE_LEVEL='1'"
						+ " WHERE INVOICE_STATUS='A' AND INVOICE_ID="
						+ invoiceCode;
				boolean outResult = getSqlModel().singleExecute(query);
				if (outResult) {
					System.out.println("acceptBean.getUserEmpId()-----"
							+ acceptBean.getUserEmpId());
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void getAcceptList(InvoiceAcceptance acceptBean){
		
		try {
		Object [] accData = new Object[2];
		String ApplStatus=null;
		accData[0]="Q";
		accData[1]=acceptBean.getUserEmpId();
		Object acceptObj [][]= getSqlModel().getSingleResult(getQuery(1), accData);
		
		if(acceptObj != null && acceptObj.length >0){
			ArrayList <Object> acceptArrayList = new ArrayList <Object> ();
			for(int i=0; i<acceptObj.length;i++){
				InvoiceAcceptance beanAccept = new InvoiceAcceptance();
				beanAccept.setPartnerNameIA(String.valueOf(acceptObj[i][0]));
				beanAccept.setInvoiceDateIA(String.valueOf(acceptObj[i][1]));
				beanAccept.setPartnerCodeIA(String.valueOf(acceptObj[i][2]));
				beanAccept.setInvoiceIDIA(String.valueOf(acceptObj[i][3]));
				if (String.valueOf(acceptObj[i][4]).equals("Q")) {
					ApplStatus = "Accepted";
				}
				beanAccept.setInvoiceStatusIA(String.valueOf(ApplStatus));
				acceptArrayList.add(beanAccept);
			}
			acceptBean.setAcceptList(acceptArrayList);
		 } 
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
