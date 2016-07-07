/**
 * 
 */
package org.paradyne.model.vendor;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelClaim.TmsClmDisbursement;
import org.paradyne.bean.vendor.InvoiceDisbursement;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0491
 * 
 */
public class InvoiceDisbursementModel extends ModelBase {

	public void callStatus(InvoiceDisbursement bean, String status,
			HttpServletRequest request) {

		try {
			String query = " SELECT PARTNER_NAME,VENDOR_INVOICE.PARTNER_CODE,TO_CHAR(VENDOR_INVOICE.INVOICE_DATE, 'DD-MM-YYYY'),"
					+ "   invoice_amount, decode(INVOICE_STATUS,'Q','Acknowledged and pending for disbursement'),VENDOR_INVOICE.INVOICE_ID,VENDOR_INVOICE.INVOICE_STATUS"
					+ " FROM VENDOR_INVOICE"
					+ " INNER JOIN VENDOR_INFO ON  VENDOR_INFO.PARTNER_LOGIN_CODE = VENDOR_INVOICE.PARTNER_CODE"
					+ " WHERE 1=1";
			System.out.println("status in model========  " + status);
			// query += " AND VENDOR_INVOICE.INVOICE_STATUS IN('" + status +
			// "')";

			Object[][] listObj = getSqlModel().getSingleResult(query);

			String[] pageIndex = org.paradyne.lib.Utility.doPaging(bean
					.getMyPage(), listObj.length, 20);

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
			request.setAttribute("row", Integer.parseInt(String
					.valueOf(pageIndex[0])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			if (listObj != null && listObj.length > 0) {

				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					InvoiceDisbursement innerbean = new InvoiceDisbursement();
					innerbean.setVendorNameItt(String.valueOf(listObj[i][0]));
					innerbean.setVendorCodeItt(String.valueOf(listObj[i][1]));
					innerbean.setInvoiceDateItt(String.valueOf(listObj[i][2]));
					innerbean.setDisburseAmountItt(String
							.valueOf(listObj[i][3]));
					innerbean.setStatusItt(String.valueOf(listObj[i][4]));
					pendingList.add(innerbean);
				}
				bean.setVendorDisbrList(pendingList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public TreeMap<String, String> setPayModes() {
		TreeMap<String, String> map = null;

		try {

			map = new TreeMap<String, String>();
			map.put("CA", "Cash");
			map.put("TR", "Transfer");
			map.put("CH", "Cheque");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public void view(InvoiceDisbursement bean) {
		try {
			String query = "  SELECT PARTNER_NAME,VENDOR_INVOICE.PARTNER_CODE,TO_CHAR(VENDOR_INVOICE.INVOICE_DATE, 'DD-MM-YYYY'), "
					+ "  PARTNER_TYPE, to_char(INVOICE_FROM_DATE,'dd-mm-yyyy'),to_char(INVOICE_TO_DATE,'dd-mm-yyyy'), INVOICE_PROJECT,INVOICE_AMOUNT,VENDOR_INVOICE.INVOICE_ID,VENDOR_INVOICE.INVOICE_STATUS "
					+ " FROM VENDOR_INVOICE "
					+ " INNER JOIN VENDOR_INFO ON VENDOR_INFO.PARTNER_LOGIN_CODE = VENDOR_INVOICE.PARTNER_CODE "
					+ " WHERE VENDOR_INVOICE.INVOICE_ID="
					+ bean.getHiddenInvoiceCode();
			Object dataObj[][] = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				bean.setPartnerName(checkNull(String.valueOf(dataObj[0][0])));
				bean.setPartnerCode(checkNull(String.valueOf(dataObj[0][1])));
				bean.setPartnerDate(checkNull(String.valueOf(dataObj[0][2])));
				bean.setPartnerType(checkNull(String.valueOf(dataObj[0][3])));
				bean.setPartnerInvoiceFrmDate(checkNull(String
						.valueOf(dataObj[0][4])));
				bean.setPartnerInvoiceToDate(checkNull(String
						.valueOf(dataObj[0][5])));
				bean
						.setPartnerProject(checkNull(String
								.valueOf(dataObj[0][6])));

				bean.setTotalInvoiceDisbrAmt(checkNull(String
						.valueOf(dataObj[0][7])));

				/*
				 * bean .setDisburseAmount(checkNull(String
				 * .valueOf(dataObj[0][7])));
				 */

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	public void addPayDtls(InvoiceDisbursement bean, HttpServletRequest request) {

		System.out.println("in paydetails--------------");
		ArrayList<Object> list = new ArrayList<Object>();
		String payMode[] = request.getParameterValues("disbBalPayMode");// Pay
		// Mode
		String paymentDate[] = request.getParameterValues("disbBalDate");// Payment
		// Date
		String bankCode[] = request.getParameterValues("bankId");// Bank Id
		String[] account = request.getParameterValues("accountNo");// Account
		// No.
		String[] chqDate = request.getParameterValues("chqDate");// Cheque
		// Date
		String[] chqNo = request.getParameterValues("chqNo");// Cheque No.
		String[] disAmt = request.getParameterValues("disbBalAmt");// Amount
		String[] comment = request.getParameterValues("disbBalCmt"); // Comments
		String disBrsId[] = request.getParameterValues("disbBalId");
		String[] bankName = request.getParameterValues("bankName");// Bank name

		if (payMode != null && payMode.length > 0) {
			for (int i = 0; i < payMode.length; i++) {

				InvoiceDisbursement bean1 = new InvoiceDisbursement();
				if (disBrsId[i].equals("") || disBrsId[i].equals("null")
						|| disBrsId[i].equals(" ")) {
					bean1.setEditButton("false");
					bean1.setDisburseFlag("false");
				} else {
					bean1.setEditButton("false");
					bean1.setDisburseFlag("false");
				}
				bean1.setDisbBalId(checkNull(disBrsId[i]));
				bean1.setDisbBalDate(checkNull(paymentDate[i]));
				bean1.setDisbBalAmt(checkNull(disAmt[i]));
				bean1.setDisbBalCmt(checkNull(comment[i]));
				if (payMode[i].equals("Cheque")) {
					bean1.setDisbBalPayMode("Cheque");
					bean1.setChqDate(chqDate[i]);
					bean1.setChqNo(chqNo[i]);
					bean1.setPaymentDet(chqNo[i] + "--" + chqDate[i]);
				} else if (payMode[i].equals("Transfer")) {
					bean1.setDisbBalPayMode("Transfer");
					bean1.setBankId(bankCode[i]);
					bean1.setBankName(bankName[i]);
					bean1.setAccountNo(account[i]);
					bean1.setPaymentDet(account[i] + "--" + bankName[i]);
				}// ADDED BY REEBA
				else {
					bean1.setDisbBalPayMode("Cash");
				}

				list.add(bean1);
			}

		}
		TmsClmDisbursement bean2 = new TmsClmDisbursement();

		bean2.setDisbBalDate(bean.getDisburseDate());
		if (bean.getDisburseMode().equals("TR")) {
			bean2.setDisbBalPayMode("Transfer");
			bean2.setAccountNo(bean.getDisburseAccount());
			bean2.setBankName(bean.getDisburseBankName());
			bean2.setBankId(bean.getBankCode());
			bean2.setPaymentDet(bean.getDisburseAccount() + "--"
					+ bean.getDisburseBankName());

		} else if (bean.getDisburseMode().equals("CH")) {
			bean2.setDisbBalPayMode("Cheque");
			bean2.setChqNo(bean.getDisburseChqNo());
			bean2.setChqDate(bean.getDisburseChqDate());
			bean2.setPaymentDet(bean.getDisburseChqNo() + "--"
					+ bean.getDisburseChqDate());
		}// ADDED BY REEBA
		else {
			bean2.setDisbBalPayMode("Cash");
		}
		bean2.setDisbBalAmt(checkNull(bean.getDisburseAmount()));
		bean2.setDisbBalCmt(checkNull(bean.getDisbursementComment()));
		list.add(bean2);
		view(bean);
		/*
		 * setEmpDtls(bean, request); setApprvlCommentDis(bean, request);
		 */
		bean.setPaymentList(list);
		
		double disbursedAmt =0;
		  disbursedAmt =Double.parseDouble(bean.getTotalInvoiceDisbrAmt());
		 
		if(disbursedAmt>0)
		{
		double differenceAmt = disbursedAmt - Double.parseDouble(bean.getTotalAmount());
		bean.setBalanceAmount(String.valueOf(differenceAmt));
		}

	}

	public void removePayDtls(InvoiceDisbursement bean,
			HttpServletRequest request) {

		try {
			String payMode[] = request.getParameterValues("disbBalPayMode");
			String paymentDate[] = request.getParameterValues("disbBalDate");
			String bankCode[] = request.getParameterValues("bankId");
			String[] account = request.getParameterValues("accountNo");
			String[] chqDate = request.getParameterValues("chqDate");
			String[] chqNo = request.getParameterValues("chqNo");
			String[] disAmt = request.getParameterValues("disbBalAmt");
			String[] comment = request.getParameterValues("disbBalCmt");
			String disBrsId[] = request.getParameterValues("disbBalId");
			String[] bankName = request.getParameterValues("bankName");
			String[] payFlag = request.getParameterValues("payFlag");
			// ADDED BY REEBA
			double disbursedAmt =0;
			  disbursedAmt =Double.parseDouble(bean.getTotalInvoiceDisbrAmt());
			double amount = 0;
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = 0; i < payMode.length; i++) {
				if (payFlag[i].equals("N")) {
					InvoiceDisbursement bean1 = new InvoiceDisbursement();
					if (disBrsId[i].equals("") || disBrsId[i].equals("null")
							|| disBrsId[i].equals(" "))
						bean1.setEditButton("false");
					else
						bean1.setEditButton("true");

					bean1.setDisbBalId(disBrsId[i]);
					bean1.setDisbBalDate(checkNull(paymentDate[i]));
					bean1.setDisbBalAmt(checkNull(disAmt[i]));
					amount += Double.parseDouble(disAmt[i]);
					bean1.setDisbBalCmt(checkNull(comment[i]));
					if (payMode[i].equals("Cheque")) {
						bean1.setDisbBalPayMode("Cheque");
						bean1.setChqDate(chqDate[i]);
						bean1.setChqNo(chqNo[i]);
						bean1.setPaymentDet(chqNo[i] + "--" + chqDate[i]);
					} else if (payMode[i].equals("Transfer")) {
						bean1.setDisbBalPayMode("Transfer");
						bean1.setBankId(bankCode[i]);
						bean1.setBankName(bankName[i]);
						bean1.setAccountNo(account[i]);
						bean1.setPaymentDet(account[i] + "--" + bankName[i]);
					} else {
						bean1.setDisbBalPayMode("Cash");

					}
					list.add(bean1);
				}

			}// End of for loop
			System.out.println("disbursedAmt   "+disbursedAmt);
			System.out.println("amount   "+amount);
			
			
			if(disbursedAmt>0)
			{
			double differenceAmt = disbursedAmt - amount;
			bean.setBalanceAmount(String.valueOf(differenceAmt));
			bean.setTotalAmount(String.valueOf(amount));
			}
			bean.setPaymentList(list);
			
			view(bean);
			/*
			 * setEmpDtls(bean, request); setApprvlCommentDis(bean, request);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getApproverDtl(InvoiceDisbursement bean) {
		try {
			String query = "  SELECT  HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ " ,TO_CHAR(INVOICE_APPROVE_DATE,'DD-MM-YYYY'),DECODE(INVOICE_STATUS,'A','Approved','B','Sent Back') "
					+ " ,NVL(INVOICE_APPR_COMMENTS,'') "
					+ " FROM VENDOR_INVOICE_PATH "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = VENDOR_INVOICE_PATH.INVOICE_APPROVED_BY)";
			Object approverDataObj[][] = getSqlModel().getSingleResult(query);
			ArrayList list = new ArrayList();
			if (approverDataObj != null && approverDataObj.length > 0) {
				for (int i = 0; i < approverDataObj.length; i++) {
					InvoiceDisbursement innerbean = new InvoiceDisbursement();
					innerbean.setPrevApproverID(checkNull(String
							.valueOf(approverDataObj[i][0])));
					innerbean.setPrevApproverName(checkNull(String
							.valueOf(approverDataObj[i][1])));
					innerbean.setPrevApproverDate(checkNull(String
							.valueOf(approverDataObj[i][2])));
					innerbean.setPrevApproverStatus(checkNull(String
							.valueOf(approverDataObj[i][3])));
					innerbean.setPrevApproverComment(checkNull(String
							.valueOf(approverDataObj[i][4])));
					list.add(innerbean);
				}
			}
			bean.setApproverCommentList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

 
	public boolean chkDisburseIdforSave(InvoiceDisbursement bean) {
		boolean result = false;
		String query = "SELECT VENDOR_DISB_CLM_ID   FROM VENDOR_DISBURSEMENT WHERE VENDOR_DISB_CLM_ID=1";
				//+ bean.getTmsClmAppId();
		Object[][] Data = getSqlModel().getSingleResult(query);
		if (Data != null && Data.length > 0) {
			result = true;
		}

		return result;
	}

	public boolean updateData(InvoiceDisbursement bean,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveData(InvoiceDisbursement bean, HttpServletRequest request) {

		boolean result = false;
		String payMode[] = request.getParameterValues("disbBalPayMode");
		String paymentDate[] = request.getParameterValues("disbBalDate");
		String bankName[] = request.getParameterValues("bankName");
		String[] account = request.getParameterValues("accountNo");
		String[] chqDate = request.getParameterValues("chqDate");
		String[] chqNo = request.getParameterValues("chqNo");
		String[] disAmt = request.getParameterValues("disbBalAmt");
		String[] comment = request.getParameterValues("disbBalCmt");
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");
		 
		
		String query ="  insert into VENDOR_DISBURSEMENT (VENDOR_DISB_ID, VENDOR_DISB_CLM_ID, VENDOR_DISB_BALANCE_AMT, VENDOR_DISB_STATUS, VENDOR_DISB_DATE)"
				+" values((SELECT NVL(MAX(VENDOR_DISB_ID),0)+1 FROM VENDOR_DISBURSEMENT),1,"+bean.getTotalAmount()+",'T',Sysdate)";
	 
		result = getSqlModel().singleExecute(query);
		if (result) {
			String query1 = "SELECT MAX(VENDOR_DISB_ID) FROM VENDOR_DISBURSEMENT";
			Object[][] Data = getSqlModel().getSingleResult(query1);
			bean.setDisburseCode(String.valueOf(Data[0][0]));
 
			
			String insertQuery = "  INSERT INTO VENDOR_DISB_BAL(VENDOR_DISB_BAL_ID, VENDOR_DISB_BAL_DISBID, VENDOR_DISB_TOT_BAL, VENDOR_DISB_PAID_BAL, VENDOR_DISB_PEND_BAL, VENDOR_DISB_PAYDATE, VENDOR_DISB_PAYMODE, VENDOR_DISB_CHEQUE_NO, VENDOR_DISB_CHEQUE_DATE, VENDOR_DISB_BANK_NAME, VENDOR_DISB_TRNSFR_ACCNO, VENDOR_DISB_CMTS, VENDOR_DISB_MONTH, VENDOR_DISB_YEAR) VALUES((SELECT NVL(MAX(VENDOR_DISB_BAL_ID),0)1 FROM TMS_VENDOR_DISB_BAL),? "
				+" 	,?,TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?) ";
			

			if (payMode != null && payMode.length > 0) {
				for (int i = 0; i < payMode.length; i++) {
					Object[][] add = new Object[1][11];
					add[0][0] = bean.getDisburseCode();
					add[0][1] = checkNull(String.valueOf(disAmt[i]));
					add[0][2] = checkNull(String.valueOf(paymentDate[i]));
					if (payMode[i].equals("Transfer"))
						add[0][3] = String.valueOf("TR");
					else if (payMode[i].equals("Cash"))
						add[0][3] = String.valueOf("CA");
					else
						add[0][3] = String.valueOf("CH");
					add[0][4] = checkNull(String.valueOf(chqNo[i]));
					add[0][5] = checkNull(String.valueOf(chqDate[i]));
					add[0][6] = checkNull(String.valueOf(bankName[i]));
					add[0][7] = checkNull(String.valueOf(account[i]));
					add[0][8] = checkNull(String.valueOf(comment[i]));
					add[0][9] = checkNull(String.valueOf(month[i]));
					add[0][10] = checkNull(String.valueOf(year[i]));
					result = getSqlModel().singleExecute(insertQuery, add);
				}
			}

			 

		}

		return result;
	
	}

}
