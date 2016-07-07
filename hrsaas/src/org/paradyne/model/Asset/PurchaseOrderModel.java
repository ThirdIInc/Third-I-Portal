/**
 * 
 */
package org.paradyne.model.Asset;

import java.awt.Color;
import java.text.DecimalFormat;
import com.lowagie.text.Font;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.AssetEmployee;
import org.paradyne.bean.Asset.PurchaseOrder;
import org.paradyne.bean.Asset.RateList;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.voucher.CashVoucherMaster;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.common.ApplCodeTemplateModel;

import com.lowagie.text.Element;

/**
 * @author mangeshj Date 19/08/2008 PurchaseOrderModel class to write the
 *         business logic to Add , Update, View the purchase orders
 */
public class PurchaseOrderModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.model.Asset.PurchaseOrderModel.class);

	// NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
	NumberFormat formatter = new DecimalFormat("#0.00");

	/*
	 * method name : setGeneralEmpData purpose : to display the employee data if
	 * employee logged in is general employee return type : void parameter :
	 * PurchaseOrder instance
	 */
	public void setGeneralEmpData(PurchaseOrder bean) {
		String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME FROM HRMS_EMP_OFFC"
				+ " WHERE EMP_ID =" + bean.getUserEmpId();
		Object empData[][] = getSqlModel().getSingleResult(query);
		if (empData != null && empData.length != 0) {
			bean.setEmpCode(bean.getUserEmpId());
			bean.setEmpToken(String.valueOf(empData[0][0]));
			bean.setEmpName(String.valueOf(empData[0][1]));
		} // end of if
	}

	/*
	 * method name : addItem purpose : to add the purchase details in the Order
	 * list return type : void parameter : PurchaseOrder instance,String
	 * []itemCode,String [] itemName,String []price,String [] unit,String []
	 * quantity,String [] total, String [] cityName
	 */

	public void addItem(PurchaseOrder bean, String[] itemCode,
			String[] itemName, String[] price, String[] unit,
			String[] quantity, String[] total, String[] cityName) {
		ArrayList<Object> tableList = new ArrayList<Object>();

		double totalAmt = 0.0;
		if (itemCode != null && itemCode.length != 0) {
			/*
			 * set the Orders in the list if any before adding new Item
			 * 
			 */
			for (int i = 0; i < itemCode.length; i++) {
				PurchaseOrder bean1 = new PurchaseOrder();
				/*
				 * bean1.setVendorCodeIterator(vendorCode[i]);
				 * bean1.setVendorNameIterator(vendorName[i]);
				 */
				bean1.setItemCodeIterator(itemCode[i]);
				bean1.setItemIterator(itemName[i]);
				bean1.setPriceIterator(Utility.twoDecimals(price[i]));
				bean1.setUnitIterator(unit[i]);
				bean1.setQuantityIterator(Utility.twoDecimals(quantity[i]));
				bean1.setTotalIterator(Utility.twoDecimals(total[i]));
				bean1.setCityIterator(cityName[i]);
				totalAmt += Double.parseDouble(Utility.twoDecimals(String
						.valueOf(total[i])));
				tableList.add(bean1);
			} // end of for loop

		} // end of if
		/*
		 * add new Order in the list
		 */
		try {
			/*
			 * bean.setVendorCodeIterator(bean.getVendorCode());
			 * bean.setVendorNameIterator(bean.getVendorName());
			 */
			bean.setItemCodeIterator(bean.getSubTypeCode());
			bean.setItemIterator(bean.getSubTypeName());
			bean.setPriceIterator(Utility.twoDecimals(bean.getPrice()));
			bean.setUnitIterator(bean.getUnit());
			bean.setQuantityIterator(Utility.twoDecimals(bean.getQuantity()));

			bean.setTotalIterator(formatter.format(Double.parseDouble(Utility
					.twoDecimals(bean.getPrice()))
					* Double.parseDouble(Utility
							.twoDecimals(bean.getQuantity()))));
			totalAmt += Double.parseDouble(Utility.twoDecimals(bean
					.getTotalPrice()));

			bean.setCityIterator(bean.getCityName());

			tableList.add(bean);
		} catch (Exception e) {
			logger.info("exception in addItem()==" + e);
		}
		bean.setTotalAmount(formatter.format(Math.round(totalAmt)));
		bean.setOrderList(tableList);

	}

	/*
	 * method name : editItem purpose : to add the purchase details in the Order
	 * list after editing return type : void parameter : PurchaseOrder
	 * instance,String []itemCode,String [] itemName,String []price,String []
	 * unit,String [] quantity,String [] total, String [] cityName
	 */
	public void editItem(PurchaseOrder bean, String[] itemCode,
			String[] itemName, String[] price, String[] unit,
			String[] quantity, String[] total, String[] cityName) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		double totalAmt = 0.0;
		if (itemCode != null) {
			/*
			 * set the Orders in the list before adding Item
			 * 
			 */
			for (int i = 0; i < itemCode.length; i++) {
				PurchaseOrder bean1 = new PurchaseOrder();
				if (i == Integer.parseInt(bean.getParaId()) - 1) {
					/*
					 * bean.setVendorCodeIterator(bean.getVendorCode());
					 * bean.setVendorNameIterator(bean.getVendorName());
					 */
					bean.setItemCodeIterator(bean.getSubTypeCode());
					bean.setItemIterator(bean.getSubTypeName());
					bean.setPriceIterator(Utility.twoDecimals(bean.getPrice()));
					bean.setUnitIterator(bean.getUnit());
					bean.setQuantityIterator(Utility.twoDecimals(bean
							.getQuantity()));
					bean.setTotalIterator(formatter
							.format((Double.parseDouble(Utility
									.twoDecimals(bean.getPrice())) * Double
									.parseDouble(Utility.twoDecimals(bean
											.getQuantity())))));
					bean.setCityIterator(bean.getCityName());
					totalAmt += Double.parseDouble(Utility.twoDecimals(bean
							.getTotalPrice()));
					tableList.add(bean);
				} // end of if
				else {
					/*
					 * bean1.setVendorCodeIterator(vendorCode[i]);
					 * bean1.setVendorNameIterator(vendorName[i]);
					 */
					bean1.setItemCodeIterator(itemCode[i]);
					bean1.setItemIterator(itemName[i]);
					bean1.setPriceIterator(Utility.twoDecimals(price[i]));
					bean1.setUnitIterator(unit[i]);
					bean1.setQuantityIterator(Utility.twoDecimals(quantity[i]));
					bean1.setTotalIterator(Utility.twoDecimals(total[i]));
					bean1.setCityIterator(cityName[i]);
					totalAmt += Double.parseDouble(Utility.twoDecimals(String
							.valueOf(total[i])));
					tableList.add(bean1);
				} // end of else
			} // end of for loop
		} // end of if

		bean.setTotalAmount(formatter.format(Math.round(totalAmt)));
		bean.setOrderList(tableList);
	}

	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode, String[] empName, PurchaseOrder bean) {

		try {
			ArrayList<PurchaseOrder> leaveList = new ArrayList<PurchaseOrder>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					PurchaseOrder purchObj = new PurchaseOrder();
					purchObj.setKeepInformedEmpId(empCode[i]);
					purchObj.setKeepInformedEmpName(empName[i]);
					purchObj.setSerialNo(srNo[i]);// sr no
					leaveList.add(purchObj);
				}
				bean.setKeepInformedList(leaveList);

			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForKeepInformed----------"
							+ e);
		}

	}

	public void setKeepInformed(String[] srNo, String[] empCode,
			String[] empName, PurchaseOrder bean) {

		try {
			PurchaseOrder purchOrd = new PurchaseOrder();
			purchOrd.setKeepInformedEmpId(bean.getEmployeeId());
			purchOrd.setKeepInformedEmpName(bean.getEmployeeName());
			ArrayList<PurchaseOrder> leaveList = displayNewValueForKeepInformed(
					srNo, empCode, empName, bean);
			purchOrd.setSerialNo(String.valueOf(leaveList.size() + 1));// sr no
			leaveList.add(purchOrd);
			bean.setKeepInformedList(leaveList);
		} catch (Exception e) {
			logger.error("Exception in setKeepInformed----------" + e);
		}

	}

	private ArrayList<PurchaseOrder> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode, String[] empName,
			PurchaseOrder bean) {
		// TODO Auto-generated method stub

		ArrayList<PurchaseOrder> purchaseList = null;
		try {
			purchaseList = new ArrayList<PurchaseOrder>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					PurchaseOrder purchOrder = new PurchaseOrder();
					purchOrder.setKeepInformedEmpId(empCode[i]);
					purchOrder.setKeepInformedEmpName(empName[i]);
					purchOrder.setSerialNo(srNo[i]);
					purchaseList.add(purchOrder);

				}

			}
		} catch (Exception e) {
			logger
					.error("Exception in displayNewValueForKeepInformed----------"
							+ e);
		}
		return purchaseList;
	}

	/*
	 * method name : removeItem purpose : to remove the purchase details from
	 * the Order list return type : void parameter : PurchaseOrder
	 * instance,String []itemCode,String [] itemName,String []price,String []
	 * unit,String [] quantity,String [] total, String [] cityName
	 */
	public void removeItem(PurchaseOrder bean, String[] itemCode,
			String[] itemName, String[] price, String[] unit,
			String[] quantity, String[] total, String[] cityName) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		double totalAmt = 0.0;
		if (itemCode != null) {
			for (int i = 0; i < itemCode.length; i++) {
				PurchaseOrder bean1 = new PurchaseOrder();
				if (i == Integer.parseInt(bean.getParaId()) - 1) {

				} // end of if
				else {
					/*
					 * bean1.setVendorCodeIterator(vendorCode[i]);
					 * bean1.setVendorNameIterator(vendorName[i]);
					 */
					bean1.setItemCodeIterator(itemCode[i]);
					bean1.setItemIterator(itemName[i]);
					bean1.setPriceIterator(Utility.twoDecimals(price[i]));
					bean1.setUnitIterator(unit[i]);
					bean1.setQuantityIterator(Utility.twoDecimals(quantity[i]));
					bean1.setTotalIterator(Utility.twoDecimals(total[i]));
					bean1.setCityIterator(cityName[i]);
					totalAmt += Double.parseDouble(Utility.twoDecimals(String
							.valueOf(total[i])));
					tableList.add(bean1);
				} // end of else
			} // end of for loop
		} // end of if

		bean.setTotalAmount(formatter.format(Math.round(totalAmt)));
		bean.setOrderList(tableList);
	}

	/*
	 * method name : showList purpose : to show the purchase details in the
	 * Order list return type : void parameter : PurchaseOrder instance,String
	 * []itemCode,String [] itemName,String []price,String [] unit,String []
	 * quantity,String [] total, String [] cityName
	 */
	public void showList(PurchaseOrder bean, String[] itemCode,
			String[] itemName, String[] price, String[] unit,
			String[] quantity, String[] total, String[] cityName) {
		ArrayList<Object> tableList = new ArrayList<Object>();

		double totalAmt = 0.0;
		if (itemCode != null) {
			/*
			 * set the orders in the list
			 */
			for (int i = 0; i < itemCode.length; i++) {
				PurchaseOrder bean1 = new PurchaseOrder();
				/*
				 * bean1.setVendorCodeIterator(vendorCode[i]);
				 * bean1.setVendorNameIterator(vendorName[i]);
				 */
				bean1.setItemCodeIterator(itemCode[i]);
				bean1.setItemIterator(itemName[i]);
				bean1.setPriceIterator(Utility.twoDecimals(price[i]));
				bean1.setUnitIterator(unit[i]);
				bean1.setQuantityIterator(Utility.twoDecimals(quantity[i]));
				bean1.setTotalIterator(Utility.twoDecimals(total[i]));
				bean1.setCityIterator(cityName[i]);
				tableList.add(bean1);
				totalAmt += Double.parseDouble(Utility.twoDecimals(String
						.valueOf(total[i])));
			} // end of for loop
		} // end of if
		bean.setTotalAmount(formatter.format(Math.round(totalAmt)));
		bean.setOrderList(tableList);
	}

	/*
	 * method name : save purpose : to save the purchase order return type :
	 * boolean parameter : PurchaseOrder instance, String []itemCode,String
	 * []price,String [] quantity,String [] total, Object[][] approver
	 */
	public boolean save(PurchaseOrder bean, String[] itemCode, String[] price,
			String[] quantity, String[] total, Object[][] approver,
			HttpServletRequest request) {
		boolean result = false;
		Object insertHdrData[][] = new Object[1][27];
		insertHdrData[0][0] = bean.getEmpCode();
		insertHdrData[0][1] = bean.getOrderDate();
		insertHdrData[0][2] = String.valueOf(approver[0][0]); // approver code
		insertHdrData[0][3] = bean.getComments(); // comments if any
		insertHdrData[0][4] = bean.getHiddenStatus();
		insertHdrData[0][5] = String.valueOf(approver[0][3]); // alternate
		// approver code
		insertHdrData[0][6] = bean.getVendorCode(); // vendor code
		insertHdrData[0][7] = bean.getPoName();
		insertHdrData[0][8] = bean.getRequiredBy();
		insertHdrData[0][9] = bean.getShippingAddress();
		insertHdrData[0][10] = bean.getBillingAddress();
		insertHdrData[0][11] = bean.getCostCenterId();
		insertHdrData[0][12] = bean.getSubcostCenterId();
		insertHdrData[0][13] = bean.getRemarks();
		insertHdrData[0][14] = bean.getTerms();
		insertHdrData[0][15] = bean.getTotalAmount();
		insertHdrData[0][16] = bean.getDiscount();
		insertHdrData[0][17] = bean.getCaldiscount();
		insertHdrData[0][18] = bean.getTotalnetAmount();
		insertHdrData[0][19] = bean.getShippingCost();
		insertHdrData[0][20] = bean.getSalesTax();
		insertHdrData[0][21] = bean.getSalesTaxAmount();
		insertHdrData[0][22] = bean.getAdditionalTaxRate();
		insertHdrData[0][23] = bean.getAdditionalTaxAmount();
		insertHdrData[0][24] = bean.getPriceAdjust();
		insertHdrData[0][25] = bean.getTotalallAmount();

		insertHdrData[0][26] = bean.getOperationType();

		result = getSqlModel().singleExecute(getQuery(1), insertHdrData); // query
		// for
		// inserting
		// into
		// HRMS_ASSET_PURCHASE_HDR

		if (result) {
			logger.info("after save in HDR before add in DTL");
			Object[][] purchaseCode = getSqlModel().getSingleResult(
					"SELECT MAX(PURCHASE_CODE) FROM HRMS_ASSET_PURCHASE_HDR");
			bean.setPurchaseCode(String.valueOf(purchaseCode[0][0]));
			Object[][] insertDtlData = new Object[itemCode.length][5];
			/*
			 * add the order details into HRMS_ASSET_PURCHASE_DTL
			 * 
			 */
			for (int i = 0; i < itemCode.length; i++) {
				insertDtlData[i][0] = bean.getPurchaseCode();
				insertDtlData[i][1] = itemCode[i];
				insertDtlData[i][2] = quantity[i];
				insertDtlData[i][3] = price[i];
				insertDtlData[i][4] = String.valueOf(i + 1);

			} // end of for loop

			result = getSqlModel().singleExecute(getQuery(2), insertDtlData);

			logger.info("after save in  DTL");
			/*
			 * send the mail to approver
			 * 
			 */
			/*
			 * if(result){ try { Object[][] to_mailIds =new Object[1][1];
			 * Object[][] from_mailIds =new Object[1][1];
			 * to_mailIds[0][0]=String.valueOf(approver[0][0]);
			 * from_mailIds[0][0]=bean.getEmpCode();
			 * 
			 * MailUtility mail=new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds,"Purchase", "", "P");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger.info("exception in
			 * sendmail()=="+e); } } // end of if
			 */
			if (result) {
				/**
				 * **************** generate the application
				 * number***************
				 */

				ApplCodeTemplateModel model = new ApplCodeTemplateModel();
				model.initiate(context, session);
				String applnCode = model.generateApplicationCode(bean
						.getPurchaseCode(), "Purchase", bean.getEmpCode(), bean
						.getOrderDate());

				logger.info("final appln code in application==" + applnCode);
				getSqlModel().singleExecute(
						"UPDATE HRMS_ASSET_PURCHASE_HDR SET PURCHASE_ORDER_NO='"
								+ applnCode + "' WHERE PURCHASE_CODE="
								+ bean.getPurchaseCode());
				bean.setReferenceId(applnCode);

				updateKeepInfo(request, bean);

				model.terminate();
				/**
				 * **************** end generate the application
				 * number***************
				 */
			}
		} // end of if
		return result;
	}

	/*
	 * method name : update purpose : to update the purchase order return type :
	 * boolean parameter : PurchaseOrder instance, String []itemCode,String
	 * []price,String [] quantity,String [] total, Object[][] approver
	 */
	public boolean update(PurchaseOrder bean, String[] itemCode,
			String[] price, String[] quantity, String[] total,
			Object[][] approver, HttpServletRequest request) {
		boolean result = false;
		Object updateHdrData[][] = new Object[1][28];
		updateHdrData[0][0] = bean.getEmpCode();
		updateHdrData[0][1] = bean.getOrderDate();
		updateHdrData[0][2] = String.valueOf(approver[0][0]);
		updateHdrData[0][3] = bean.getComments();
		updateHdrData[0][4] = String.valueOf(approver[0][3]);
		updateHdrData[0][5] = bean.getVendorCode();
		updateHdrData[0][6] = bean.getHiddenStatus();
		updateHdrData[0][7] = bean.getPoName();
		updateHdrData[0][8] = bean.getRequiredBy();
		updateHdrData[0][9] = bean.getShippingAddress();
		updateHdrData[0][10] = bean.getBillingAddress();
		updateHdrData[0][11] = bean.getCostCenterId();
		updateHdrData[0][12] = bean.getSubcostCenterId();
		updateHdrData[0][13] = bean.getRemarks();
		updateHdrData[0][14] = bean.getTerms();
		updateHdrData[0][15] = bean.getTotalAmount();
		updateHdrData[0][16] = bean.getDiscount();
		updateHdrData[0][17] = bean.getCaldiscount();
		updateHdrData[0][18] = bean.getTotalnetAmount();
		updateHdrData[0][19] = bean.getShippingCost();
		updateHdrData[0][20] = bean.getSalesTax();
		updateHdrData[0][21] = bean.getSalesTaxAmount();
		updateHdrData[0][22] = bean.getAdditionalTaxRate();
		updateHdrData[0][23] = bean.getAdditionalTaxAmount();
		updateHdrData[0][24] = bean.getPriceAdjust();
		updateHdrData[0][25] = bean.getTotalallAmount();
		updateHdrData[0][26] = bean.getOperationType();
		updateHdrData[0][27] = bean.getPurchaseCode();
		result = getSqlModel().singleExecute(getQuery(3), updateHdrData); // update
		// data
		// from
		// HRMS_ASSET_PURCHASE_HDR
		updateKeepInfo(request, bean);
		if (result) {
			getSqlModel().singleExecute(
					"DELETE FROM HRMS_ASSET_PURCHASE_DTL WHERE PURCHASE_CODE="
							+ bean.getPurchaseCode());

			Object[][] insertDtlData = new Object[itemCode.length][5];
			/*
			 * add the order details into HRMS_ASSET_PURCHASE_DTL
			 * 
			 */
			for (int i = 0; i < itemCode.length; i++) {
				insertDtlData[i][0] = bean.getPurchaseCode();
				insertDtlData[i][1] = itemCode[i];
				insertDtlData[i][2] = quantity[i];
				insertDtlData[i][3] = price[i];
				insertDtlData[i][4] = String.valueOf(i + 1);

			} // end of for loop
			result = getSqlModel().singleExecute(getQuery(2), insertDtlData);
			updateKeepInfo(request, bean);

		} // end of if
		return result;
	}

	public boolean saveByApprover(PurchaseOrder bean, String[] itemCode,
			String[] price, String[] quantity, String[] total) {
		boolean result = false;
		Object[][] to_mailIds = new Object[1][1];
		Object[][] from_mailIds = new Object[1][1];
		Object updateHdrData[][] = new Object[1][2];
		updateHdrData[0][0] = bean.getVendorCode();
		updateHdrData[0][1] = bean.getPurchaseCode();
		result = getSqlModel().singleExecute(getQuery(7), updateHdrData);
		if (result) {
			getSqlModel().singleExecute(
					"DELETE FROM HRMS_ASSET_PURCHASE_DTL WHERE PURCHASE_CODE="
							+ bean.getPurchaseCode());

			Object[][] insertDtlData = new Object[itemCode.length][5];
			for (int i = 0; i < itemCode.length; i++) {
				insertDtlData[i][0] = bean.getPurchaseCode();
				insertDtlData[i][1] = itemCode[i];
				insertDtlData[i][2] = quantity[i];
				insertDtlData[i][3] = price[i];
				insertDtlData[i][4] = String.valueOf(i + 1);

			} // end of for loop

			result = getSqlModel().singleExecute(getQuery(2), insertDtlData);

			MailUtility mail = new MailUtility();
			mail.initiate(context, session);

			/*
			 * send mails to all approvers of the application
			 * 
			 */

			Object[][] approverList = getApproverList(bean);
			try {
				from_mailIds[0][0] = bean.getUserEmpId();

				for (int i = 0; i < approverList.length; i++) {
					to_mailIds[0][0] = approverList[i][0];
					logger
							.info("to_mailIds approvers[0][0]"
									+ to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds, "Purchase", bean
							.getEmpName(), "AE");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * send mails to applicant of the application
			 * 
			 */

			try {
				from_mailIds[0][0] = bean.getUserEmpId();
				to_mailIds[0][0] = bean.getEmpCode();
				logger.info("to_mailIds applicant[0][0]" + to_mailIds[0][0]);
				logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
				mail.sendMail(to_mailIds, from_mailIds, "Purchase", bean
						.getEmpName(), "EE");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mail.terminate();
		}
		return result;
	}

	/*
	 * method name : showRecords purpose : to set the purchase order details on
	 * the screen of the selected purchase order return type : void parameter :
	 * PurchaseOrder instance
	 */

	public boolean showRecords(PurchaseOrder bean) {
		boolean isKeepInformedLogin =false;
		try {
			Object[] purchaseCode = new Object[1];
			purchaseCode[0] = bean.getPurchaseCode();
			Object[][] data = getSqlModel().getSingleResult(getQuery(4),
					purchaseCode);
			ArrayList<Object> tableList = new ArrayList<Object>();
			Object headerData[][] = getSqlModel().getSingleResult(getQuery(5),
					purchaseCode);
			bean.setComments(checkNull(String.valueOf(headerData[0][0])));
			bean.setHiddenStatus(String.valueOf(headerData[0][1]));
			bean.setStatus(bean.getHiddenStatus());
			bean.setApproverCode(String.valueOf(headerData[0][2]));
			bean
					.setPurchaseOrderNo(checkNull(String
							.valueOf(headerData[0][3])));
			bean.setVendorCode(checkNull(String.valueOf(headerData[0][4])));
			bean.setVendorAddress(checkNull(String.valueOf(headerData[0][5])));
			bean.setVendorContact(checkNull(String.valueOf(headerData[0][6])));
			bean.setCostCenterId(checkNull(String.valueOf(headerData[0][7])));
			bean.setCostcenter(checkNull(String.valueOf(headerData[0][8])));
			bean
					.setSubcostCenterId(checkNull(String
							.valueOf(headerData[0][9])));
			bean.setSubcostcenter(checkNull(String.valueOf(headerData[0][10])));
			bean
					.setShippingAddress(checkNull(String
							.valueOf(headerData[0][11])));
			bean
					.setBillingAddress(checkNull(String
							.valueOf(headerData[0][12])));
			bean.setRemarks(checkNull(String.valueOf(headerData[0][13])));
			bean.setTerms(checkNull(String.valueOf(headerData[0][14])));
			bean
					.setPurchaseOrderNo(checkNull(String
							.valueOf(headerData[0][15])));
			bean.setPoName(checkNull(String.valueOf(headerData[0][16])));
			bean.setRequiredBy(checkNull(String.valueOf(headerData[0][17])));
			bean.setTotalAmount(checkNull(String.valueOf(headerData[0][18])));
			bean.setDiscount(checkNull(String.valueOf(headerData[0][19])));
			bean.setCaldiscount(checkNull(String.valueOf(headerData[0][20])));
			bean
					.setTotalnetAmount(checkNull(String
							.valueOf(headerData[0][21])));
			bean.setShippingCost(checkNull(String.valueOf(headerData[0][22])));
			bean.setSalesTax(String.valueOf(headerData[0][23]));
			bean.setSalesTaxAmount(String.valueOf(headerData[0][24]));
			bean.setAdditionalTaxRate(String.valueOf(headerData[0][25]));
			bean.setAdditionalTaxAmount(String.valueOf(headerData[0][26]));
			bean.setPriceAdjust(String.valueOf(headerData[0][27]));
			bean.setTotalallAmount(String.valueOf(headerData[0][28]));
			bean.setOperationType(String.valueOf(headerData[0][29]));
			logger
					.info("ApproverCode in showRecord=="
							+ bean.getApproverCode());
			logger.info("data length==" + data.length);
			double totalAmt = 0.0;
			if (data != null) {
				/*
				 * set the retrieved order details in the list
				 * 
				 */
				for (int i = 0; i < data.length; i++) {
					PurchaseOrder bean1 = new PurchaseOrder();
					/*
					 * bean1.setVendorCodeIterator(String.valueOf(data[i][0]));
					 * bean1.setVendorNameIterator(String.valueOf(data[i][1]));
					 */
					bean1.setItemCodeIterator(checkNull(String
							.valueOf(data[i][0])));
					bean1
							.setItemIterator(checkNull(String
									.valueOf(data[i][1])));
					bean1
							.setPriceIterator(checkNull(String
									.valueOf(data[i][2])));
					bean1
							.setUnitIterator(checkNull(String
									.valueOf(data[i][3])));
					bean1.setQuantityIterator(checkNull(String
							.valueOf(data[i][4])));
					bean1.setTotalIterator(formatter.format((Double
							.parseDouble(String.valueOf(data[i][2])) * Double
							.parseDouble(String.valueOf(data[i][4])))));
					totalAmt += Double.parseDouble(bean1.getTotalIterator());
					tableList.add(bean1);
				} // end of for loop
				logger.info("inside showrecord if");
			} // end of if
			bean.setTotalAmount(formatter.format(Math.round(totalAmt)));
			bean.setOrderList(tableList);
			String keepInfoempId = "0";
			String sqlKeepinfo = "SELECT PURCHASE_KEEP_INFORM FROM HRMS_ASSET_PURCHASE_HDR WHERE PURCHASE_CODE  = "
					+ bean.getPurchaseCode();
			Object keepInfoId[][] = getSqlModel().getSingleResult(sqlKeepinfo);
			if (keepInfoId != null && keepInfoId.length > 0) {
				keepInfoempId = String.valueOf(keepInfoId[0][0]);
			}
			String keepInfodata = "SELECT "
					+ "HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "FROM HRMS_EMP_OFFC " + "WHERE EMP_ID IN("
					+ keepInfoempId + ")";
			Object keepInfo[][] = getSqlModel().getSingleResult(keepInfodata);
			ArrayList keepInfolst = new ArrayList();
			if (keepInfo != null && keepInfo.length > 0) {

				for (int i = 0; i < keepInfo.length; i++) {
					PurchaseOrder asstbean = new PurchaseOrder();
					asstbean.setKeepInformedEmpName(String
							.valueOf(keepInfo[i][0]));
					asstbean.setKeepInformedEmpId(String
							.valueOf(keepInfo[i][1]));
					
					if(asstbean.getKeepInformedEmpId().equals(bean.getUserEmpId()))
					{
						isKeepInformedLogin =true;
					}

					keepInfolst.add(asstbean);
				}
			}
			bean.setKeepInformedList(keepInfolst);
			// setVendorDetails(bean);
		} catch (Exception e) {
			// TODO: handle exception
		}
			return isKeepInformedLogin;
	}

	/*
	 * method name : showRecordsForInward purpose : to set the purchase order
	 * details on the screen of the selected purchase order for Inward return
	 * type : void parameter : PurchaseOrder instance
	 */

	public void showRecordsForInward(PurchaseOrder bean) {
		try {
			Object[] purchaseCode = new Object[1];
			Object[] purchaseStatus = new Object[2];
			purchaseStatus[0] = bean.getPurchaseCode();
			purchaseStatus[1] = "PE";
			Object[][] pendingData = getSqlModel().getSingleResult(getQuery(6),
					purchaseStatus);
			purchaseStatus[0] = bean.getPurchaseCode();
			purchaseStatus[1] = "CL";
			Object[][] cancelData = getSqlModel().getSingleResult(getQuery(6),
					purchaseStatus);
			ArrayList<Object> tableList = new ArrayList<Object>();
			purchaseCode[0] = bean.getPurchaseCode();
			Object headerData[][] = getSqlModel().getSingleResult(getQuery(5),
					purchaseCode);
			bean.setComments(checkNull(String.valueOf(headerData[0][0])));
			bean.setHiddenStatus(String.valueOf(headerData[0][1]));
			bean.setStatus(bean.getHiddenStatus());
			double totalAmt = 0.0;
			if (pendingData != null && pendingData.length != 0) {
				/*
				 * set the retrieved order details in the list
				 * 
				 */
				for (int i = 0; i < pendingData.length; i++) {
					PurchaseOrder bean1 = new PurchaseOrder();
					/*
					 * bean1.setVendorCodeIterator(String.valueOf(data[i][0]));
					 * bean1.setVendorNameIterator(String.valueOf(data[i][1]));
					 */
					bean1
							.setItemCodeIterator(String
									.valueOf(pendingData[i][0]));
					bean1.setItemIterator(String.valueOf(pendingData[i][1]));
					bean1.setPriceIterator(String.valueOf(pendingData[i][2]));
					bean1.setUnitIterator(String.valueOf(pendingData[i][3]));
					bean1
							.setQuantityIterator(String
									.valueOf(pendingData[i][4]));
					bean1.setPurchaseDtlCode(String.valueOf(pendingData[i][5]));
					bean1.setTotalIterator(formatter
							.format((Double.parseDouble(String
									.valueOf(pendingData[i][2])) * Double
									.parseDouble(String
											.valueOf(pendingData[i][4])))));
					totalAmt += Double.parseDouble(bean1.getTotalIterator());
					tableList.add(bean1);
				} // end of for loop
				bean.setNoData("false");
			} // end of if
			else {
				bean.setNoData("true");
			} // end of else
			ArrayList<Object> tableListCanel = new ArrayList<Object>();
			if (cancelData != null && cancelData.length != 0) {
				/*
				 * set the retrieved order details in the list
				 * 
				 */
				for (int i = 0; i < cancelData.length; i++) {
					PurchaseOrder bean1 = new PurchaseOrder();
					/*
					 * bean1.setVendorCodeIterator(String.valueOf(data[i][0]));
					 * bean1.setVendorNameIterator(String.valueOf(data[i][1]));
					 */
					bean1.setItemCodeIterator(String.valueOf(cancelData[i][0]));
					bean1.setItemIterator(String.valueOf(cancelData[i][1]));
					bean1.setPriceIterator(String.valueOf(cancelData[i][2]));
					bean1.setUnitIterator(String.valueOf(cancelData[i][3]));
					bean1.setQuantityIterator(String.valueOf(cancelData[i][4]));
					bean1.setPurchaseDtlCode(String.valueOf(cancelData[i][5]));
					bean1.setTotalIterator(formatter
							.format((Double.parseDouble(String
									.valueOf(cancelData[i][2])) * Double
									.parseDouble(String
											.valueOf(cancelData[i][4])))));
					tableListCanel.add(bean1);
				} // end of for loop
				bean.setNoDataForCancel("false");
			} // end of if
			else {
				bean.setNoDataForCancel("true");
			} // end of else
			bean.setTotalAmount(formatter.format(Math.round(totalAmt)));
			bean.setOrderList(tableList);
			bean.setCancelList(tableListCanel);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * method name : showPrice purpose : to set the price of the asset after
	 * selecting for particular vendor return type : void parameter :
	 * PurchaseOrder instance
	 */
	public void showPrice(PurchaseOrder bean) {
		String priceQuery = "SELECT RATELIST_PRICE FROM HRMS_ASSET_RATELIST "
				+ " INNER JOIN HRMS_ASSET_RATELIST_DTL ON (HRMS_ASSET_RATELIST_DTL.RATELIST_CODE=HRMS_ASSET_RATELIST.RATELIST_CODE) "
				+ " WHERE RATELIST_VENDOR=" + bean.getVendorCode()
				+ " AND RATELIST_ITEM_CODE=" + bean.getSubTypeCode();
		Object price[][] = getSqlModel().getSingleResult(priceQuery);
		logger.info("price.length=====" + price.length);
		if (price != null && price.length != 0) {
			bean.setPrice(String.valueOf(price[0][0]));
		} // end of if
		else
			bean.setPrice("");
	} // end of else

	/*
	 * method name : delete purpose : to delete the purchase order & all its
	 * related details return type : boolean parameter : PurchaseOrder instance
	 */
	public boolean delete(PurchaseOrder bean) {
		boolean result = false;

		result = getSqlModel().singleExecute(
				"DELETE FROM HRMS_ASSET_PURCHASE_DTL WHERE PURCHASE_CODE="
						+ bean.getPurchaseCode());

		if (result) {

			result = getSqlModel().singleExecute(
					"DELETE FROM HRMS_ASSET_PURCHASE_HDR WHERE PURCHASE_CODE="
							+ bean.getPurchaseCode());

		} // end of if

		/*
		 * if(result){ if(result){ try { Object[][] to_mailIds =new
		 * Object[1][1]; Object[][] from_mailIds =new Object[1][1];
		 * to_mailIds[0][0]=bean.getApproverCode();
		 * from_mailIds[0][0]=bean.getEmpCode();
		 * 
		 * MailUtility mail=new MailUtility(); mail.initiate(context, session);
		 * logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
		 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
		 * mail.sendMail(to_mailIds, from_mailIds,"Purchase", "", "D");
		 * 
		 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); logger.info("exception in
		 * sendmail()=="+e); } } }
		 */
		return result;

	}

	public boolean cancelAppl(PurchaseOrder bean) {
		boolean result = false;
		Object[][] cancelObj = new Object[1][2];
		cancelObj[0][0] = "C";
		cancelObj[0][1] = bean.getPurchaseCode();

		result = getSqlModel().singleExecute(getQuery(8), cancelObj);

		/*
		 * if(result){ if(result){ try { Object[][] to_mailIds =new
		 * Object[1][1]; Object[][] from_mailIds =new Object[1][1];
		 * to_mailIds[0][0]=bean.getApproverCode();
		 * from_mailIds[0][0]=bean.getEmpCode();
		 * 
		 * MailUtility mail=new MailUtility(); mail.initiate(context, session);
		 * logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
		 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
		 * mail.sendMail(to_mailIds, from_mailIds,"Purchase", "", "D");
		 * 
		 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); logger.info("exception in
		 * sendmail()=="+e); } } }
		 */
		return result;

	}

	/*
	 * method name : showEmpData purpose : to show Employee Data of the selected
	 * purchase order return type : void parameter : PurchaseOrder instance
	 */
	public void showEmpData(PurchaseOrder bean) {

		/**
		 * Added by deepak VENDOR_TYPE='S' in query
		 */

		try {
			String query = " SELECT PURCHASE_EMP_CODE,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'), "
					+ " PURCHASE_VENDOR_CODE,VENDOR_NAME,LOCATION_NAME FROM HRMS_ASSET_PURCHASE_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
					+ " INNER JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_PURCHASE_HDR.PURCHASE_VENDOR_CODE ) "
					+ " INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY) "
					+ " WHERE PURCHASE_CODE= " + bean.getPurchaseCode();
			Object[][] empData = getSqlModel().getSingleResult(query);
			bean.setEmpCode(checkNull(String.valueOf(empData[0][0])));
			bean.setEmpToken(checkNull(String.valueOf(empData[0][1])));
			bean.setEmpName(checkNull(String.valueOf(empData[0][2])));
			bean.setOrderDate(checkNull(String.valueOf(empData[0][3])));
			bean.setVendorCode(checkNull(String.valueOf(empData[0][4])));
			bean.setVendorName(checkNull(String.valueOf(empData[0][5])));
			bean.setCityName(checkNull(String.valueOf(empData[0][6])));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * method name : showApproverComments purpose : to show Approver comments of
	 * the selected purchase order return type : void parameter : PurchaseOrder
	 * instance
	 */
	public void showApproverComments(PurchaseOrder bean) {
		String queryAppr = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,TO_CHAR(PURCHASE_APPR_DATE,'DD-MM-YYYY'),NVL(PURCHASE_APPL_COMMENTS,''),"
				+ " DECODE(PURCHASE_PATH_STATUS,'P','Pending','A','Approved','R','Rejected','B','SendBack') FROM HRMS_ASSET_PURCHASE_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_PATH.PURCHASE_APPROVER)"
				+ " WHERE PURCHASE_CODE="
				+ bean.getPurchaseCode()
				+ " ORDER BY PURCHASE_PATH_ID ";
		Object[][] approvarData = getSqlModel().getSingleResult(queryAppr);
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (approvarData != null && approvarData.length != 0) {
			for (int j = 0; j < approvarData.length; j++) {
				/*
				 * show the approver comments if any in the List
				 * 
				 */
				PurchaseOrder bean1 = new PurchaseOrder();
				bean1.setApproverName(checkNull(String
						.valueOf(approvarData[j][0])));
				bean1.setApprovedDate(checkNull(String
						.valueOf(approvarData[j][1])));
				bean1.setApproverComment(checkNull(String
						.valueOf(approvarData[j][2])));
				bean1.setApproveStatus(checkNull(String
						.valueOf(approvarData[j][3])));
				tableList.add(bean1);
			} // end of for loop
			bean.setCommentFlag("true");
		} // end of if
		else
			bean.setCommentFlag("false");

		bean.setApprList(tableList);
	}

	/*
	 * method name : getReport purpose : to show the Report in PDF format of the
	 * selected purchase order return type : void parameter : HttpServletRequest
	 * request,HttpServletResponse response,PurchaseOrder instance
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, PurchaseOrder bean) {

		/*
		 * retrieve the company data
		 * 
		 * 
		 * String companyQuery="SELECT COMPANY_NAME,
		 * NVL(COMPANY_ADDRESS,'-'),LOCATION_NAME, NVL(COMPANY_PIN,''),
		 * NVL(COMPANY_TELPHONE,'') FROM HRMS_COMPANY " +" INNER JOIN
		 * HRMS_LOCATION ON
		 * (HRMS_LOCATION.LOCATION_CODE=HRMS_COMPANY.COMPANY_CITYID)"; Object
		 * companyData[][]=getSqlModel().getSingleResult(companyQuery);
		 */

		/*
		 * retrieve the branch data
		 */
		String branchQuery = "SELECT CENTER_NAME, NVL(CENTER_ADDRESS1||' '||CENTER_ADDRESS2||' '||CENTER_ADDRESS3,'-'),LOCATION_NAME, NVL(CENTER_PINCODE,''), NVL(CENTER_TELEPHONE,'') FROM HRMS_CENTER"
				+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_CENTER.CENTER_LOCATION)"
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
				+ " WHERE EMP_ID=" + bean.getEmpCode();
		Object branchData[][] = getSqlModel().getSingleResult(branchQuery);
		/*
		 * retrieve the vendor data
		 * 
		 */

		/**
		 * Added by deepak VENDOR_TYPE='S' in query
		 */

		String vendorQuery = " SELECT VENDOR_NAME,VENDOR_ADDRESS,LOCATION_NAME FROM HRMS_VENDOR"
				+ " INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY)"
				+ " WHERE VENDOR_CODE="
				+ bean.getVendorCode()
				+ " AND  VENDOR_TYPE='S'";

		Object vendorData[][] = getSqlModel().getSingleResult(vendorQuery);

		Object year[][] = getSqlModel().getSingleResult(
				"SELECT TO_CHAR(SYSDATE,'YY') FROM DUAL");

		/*
		 * retrieve the branch
		 * 
		 */
		/*
		 * String branchQuery ="SELECT CENTER_NAME FROM HRMS_ASSET_PURCHASE_HDR " +"
		 * INNER JOIN HRMS_EMP_OFFC ON
		 * (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE)" +"
		 * INNER JOIN HRMS_CENTER ON
		 * (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)" +" WHERE
		 * PURCHASE_CODE="+bean.getPurchaseCode(); Object
		 * branch[][]=getSqlModel().getSingleResult(branchQuery);
		 */

		String purchaseNo = "";
		if (Integer.parseInt(bean.getPurchaseCode()) < 10) {
			purchaseNo = "0" + bean.getPurchaseCode();
		} // end of if
		else
			purchaseNo = bean.getPurchaseCode();

		/*
		 * Generate the Purchase Order no.
		 * 
		 */
		/*
		 * String orderString [] = String.valueOf(companyData[0][0]).split(" ");
		 * String abbrString = ""; for (int i = 0; i < orderString.length; i++) {
		 * abbrString +=""+orderString[i].charAt(0); }
		 */
		String orderNo = bean.getPurchaseOrderNo();
		/*
		 * Report Heading (Company name)
		 * 
		 */
		String s = "" + String.valueOf(branchData[0][0]) + "\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				"Purchase Order -" + s);
		Object[][] heading = new Object[1][1];
		int[] cells = { 100 };
		int[] allign = { 0 };
		Object[][] totalObj = new Object[1][6];

		double grandTotal = 0.0;
		/*
		 * to retrieve the approver details
		 * 
		 */
		/*
		 * String approver = "SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||'
		 * '|| EMP_LNAME ||' '),TO_CHAR(PURCHASE_APPR_DATE,'DD-MM-YYYY'), " +"
		 * PURCHASE_APPL_COMMENTS
		 * abc,DECODE(PURCHASE_PATH_STATUS,'P','Pending','A','Approved','R','Rejected')
		 * FROM HRMS_ASSET_PURCHASE_PATH " +" INNER JOIN HRMS_EMP_OFFC ON
		 * (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_PATH.PURCHASE_APPROVER) " +"
		 * LEFT JOIN HRMS_TITLE
		 * ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) " +" WHERE
		 * PURCHASE_CODE="+bean.getPurchaseCode() +" UNION " +" SELECT
		 * EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||'
		 * '),'',cast(''as nvarchar2(100)) a," +"
		 * DECODE(PURCHASE_STATUS,'P','Pending','A','Approved','R','Rejected')
		 * FROM HRMS_ASSET_PURCHASE_HDR " +" INNER JOIN HRMS_EMP_OFFC ON
		 * (HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_PURCHASE_HDR.PURCHASE_APPROVER_ID) " +"
		 * INNER JOIN HRMS_TITLE
		 * ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) " +" WHERE
		 * PURCHASE_STATUS='P'AND PURCHASE_CODE="+bean.getPurchaseCode();
		 * 
		 * Object[][] approverData = getSqlModel().getSingleResult(approver);
		 * 
		 * Object[][] approvalTable = new Object[approverData.length][6]; for
		 * (int i = 0; i < approverData.length; i++) { approvalTable[i][0] =
		 * String.valueOf(i + 1); approvalTable[i][1] =
		 * checkNull(String.valueOf(approverData[i][0])); approvalTable[i][2] =
		 * checkNull(String.valueOf(approverData[i][1])); approvalTable[i][3] =
		 * checkNull(String.valueOf(approverData[i][2])); approvalTable[i][4] =
		 * checkNull(String.valueOf(approverData[i][3])); approvalTable[i][5] =
		 * checkNull(String.valueOf(approverData[i][4])); }
		 */// end of for loop
		Object[] purchaseCode = new Object[1];
		purchaseCode[0] = bean.getPurchaseCode();
		Object tab[][] = getSqlModel().getSingleResult(getQuery(4),
				purchaseCode);

		Object[][] tableData = new Object[tab.length][6];
		/*
		 * set the purchase order details in the table format
		 * 
		 */
		for (int i = 0; i < tableData.length; i++) {
			tableData[i][0] = String.valueOf(i + 1);
			tableData[i][1] = checkNull(String.valueOf(tab[i][1]));
			tableData[i][2] = checkNull(String.valueOf(tab[i][3]));
			tableData[i][3] = checkNull(String.valueOf(tab[i][4]));
			tableData[i][4] = String.valueOf(formatter.format(Double
					.parseDouble(String.valueOf(tab[i][2]))));
			tableData[i][5] = String.valueOf(formatter.format(Double
					.parseDouble(String.valueOf(tab[i][2]))
					* Double.parseDouble(String.valueOf(tab[i][4]))));
			grandTotal += Double.parseDouble(String.valueOf(tableData[i][5]));
		} // end of for loop

		Object data1[][] = new Object[1][2];
		Object approvedObj[][] = new Object[3][1];
		Object message[][] = new Object[2][1];

		/* Object down[][]=new Object[2][1]; */

		int[] bcellWidthF = { 65, 35 };
		int[] bcellAlignF = { 0, 0 };
		rg.addFormatedText(s, 2, 0, 1, 0);
		rg.addFormatedTextForPurchase(""
				+ checkNull(String.valueOf(branchData[0][1])) + ","
				+ checkNull(String.valueOf(branchData[0][2])) + "-"
				+ checkNull(String.valueOf(branchData[0][3])), 1, 0, 1, 0);
		rg.addFormatedTextForPurchase("Tel :# "
				+ checkNull(String.valueOf(branchData[0][4])), 1, 0, 1, 0);
		rg.addFormatedText("Purchase Order", 6, 0, 1, 0);
		// rg.addText("Date: "+bean.getToday(), 0, 2, 0);
		rg.addText("", 0, 2, 0);
		int cellwidth[] = { 10, 25, 10, 10, 20, 15 };
		int alignment[] = { 1, 0, 1, 2, 2, 2 };

		// rg.tableBody(data, bcellWidth, bcellAlign);
		String message1 = "TO,";
		String message2 = "M/s. " + String.valueOf(vendorData[0][0]);
		String message3 = "" + String.valueOf(vendorData[0][1]);
		String message4 = "" + String.valueOf(vendorData[0][2]);

		data1[0][0] = message1 + "\n" + message2 + "\n" + message3 + "\n"
				+ message4 + "\n";
		data1[0][1] = " No          : " + orderNo + "\n Date       : "
				+ bean.getOrderDate() + "\n Location : "
				+ String.valueOf(branchData[0][0]);
		rg.tableBody(data1, bcellWidthF, bcellAlignF);

		message[0][0] = " Dear Sir,";
		message[1][0] = " We are pleased to place an order for the supply of following items :-";
		int cellwidth1[] = { 100 };
		int alignment1[] = { 0 };

		/*
		 * rg.addText(message1, 0, 0, 0); rg.addTextBold(message2, 0,
		 * Element.ALIGN_JUSTIFIED, 0); rg.addText(message3, 0, 0, 0);
		 * rg.addText(message4, 0, 0, 0); rg.addText("\n", 0, 0, 0);
		 */

		rg.addText("\n", 0, 2, 0);
		rg.tableBody(message, cellwidth1, alignment1);

		heading[0][0] = "Order List :";
		// rg.tableBodyBold(heading, cells, align) ;
		rg.addFormatedText("", 1, 0, 2, 3);
		String colnames[] = { "Sr.No", "Asset", "Unit", "Qty", "Rate", "Amount" };

		rg.tableBody(colnames, tableData, cellwidth, alignment);
		totalObj[0][0] = "";
		totalObj[0][1] = "GRAND TOTAL";
		totalObj[0][2] = "";
		totalObj[0][3] = "";
		totalObj[0][4] = "";
		totalObj[0][5] = String.valueOf(formatter
				.format(Math.round(grandTotal)));
		rg.tableBody(totalObj, cellwidth, alignment);
		rg.addFormatedText("\n", 1, 0, 2, 3);
		/*
		 * rg.addFormatedText("\n", 1, 0, 2, 3); rg.addFormatedText("\n", 1, 0,
		 * 2, 3); rg.addFormatedText("\n", 1, 0, 2, 3);
		 */
		// rg.addText("for "+s, 0, 0, 50);
		/*
		 * rg.addTextBold("for "+s, 0, Element.ALIGN_RIGHT, 10);
		 * rg.addFormatedText("\n", 1, 0, 2, 3); rg.addFormatedText("\n", 1, 0,
		 * 2, 3); //rg.addText("Authorised Signatory",0, 0, 50);
		 * rg.addTextBold("Authorised Signatory", 0, Element.ALIGN_RIGHT, 0);
		 */
		heading[0][0] = "Comments :";
		rg.tableBodyBold(heading, cells, allign);
		rg.addText(bean.getComments(), 0, 0, 0);
		rg.addFormatedText("\n", 1, 0, 2, 3);
		rg.addFormatedText("\n", 1, 0, 2, 3);
		rg.addFormatedText("\n", 1, 0, 2, 3);
		String approvedByQuery = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME FROM HRMS_ASSET_PURCHASE_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_PATH.PURCHASE_APPROVER)"
				+ " WHERE PURCHASE_CODE="
				+ bean.getPurchaseCode()
				+ "  ORDER BY PURCHASE_PATH_ID DESC";
		Object approvObject[][] = getSqlModel()
				.getSingleResult(approvedByQuery);

		String preparedBy = "PREPARED BY : " + bean.getEmpName();
		String verifiedBy = "VERIFIED BY :";
		String approvedBy = "APPROVED BY :";

		try {

			if (bean.getHiddenStatus().equals("A") && approvObject.length > 1) {
				verifiedBy += " " + approvObject[1][0];
				approvedBy += " " + approvObject[0][0];
			} else if (bean.getHiddenStatus().equals("A")) {
				verifiedBy += " " + approvObject[0][0];
				approvedBy += " " + approvObject[0][0];
			}
			if (bean.getHiddenStatus().equals("R")
					|| bean.getHiddenStatus().equals("F")) {
				verifiedBy += " " + approvObject[0][0];
				approvedBy += "";
			}
		} catch (Exception e) {
			logger.error("Exception==" + e);
			e.printStackTrace();
		}

		approvedObj[0][0] = preparedBy;
		approvedObj[1][0] = verifiedBy;
		approvedObj[2][0] = approvedBy;
		rg.tableBody(approvedObj, cells, allign);

		rg.createReport(response);

	}

	/*
	 * method name : checkNull purpose : check the null value return type :
	 * String parameter : String result
	 */
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/*
	 * method name : cancelItem purpose : Cancel the particular Item from the
	 * purchase order return type : boolean parameter : PurchaseOrder instance
	 */
	public boolean cancelItem(PurchaseOrder bean) {
		boolean result = false;
		String query = " UPDATE HRMS_ASSET_PURCHASE_DTL SET PURCHASE_STATUS='CL' WHERE PURCHASE_CODE ="
				+ bean.getPurchaseCode()
				+ " AND PURCHASE_DTL_CODE ="
				+ bean.getHiddenDtlCode();
		result = getSqlModel().singleExecute(query);

		return result;
	}

	public Object[][] getApproverList(PurchaseOrder bean) {
		String approverQuery = "SELECT PURCHASE_APPROVER FROM HRMS_ASSET_PURCHASE_PATH WHERE PURCHASE_PATH_STATUS ='A' AND PURCHASE_CODE="
				+ bean.getPurchaseCode();
		Object[][] approverList = getSqlModel().getSingleResult(approverQuery);

		return approverList;
	}

	public void showList(PurchaseOrder bean, HttpServletRequest request) {
		String sql = "SELECT PURCHASE_CODE,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'), "
				+ "CASE WHEN PURCHASE_STATUS='P' AND PURCHASE_LEVEL!=1   THEN 'Forwarded' WHEN PURCHASE_STATUS='P' THEN 'Pending' WHEN PURCHASE_STATUS='A' THEN 'Approved' WHEN PURCHASE_STATUS='R' THEN 'Rejected'  WHEN PURCHASE_STATUS='C' THEN 'Canceled' ELSE '' end, "
				+ " PURCHASE_EMP_CODE,PURCHASE_VENDOR_CODE,VENDOR_NAME,LOCATION_NAME "
				+ "FROM HRMS_ASSET_PURCHASE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
				+ "INNER JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_PURCHASE_HDR.PURCHASE_VENDOR_CODE and VENDOR_TYPE='S' ) "
				+ "left JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY)  ORDER BY PURCHASE_CODE DESC  ";

		Object[][] obj = getSqlModel().getSingleResult(sql);

		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
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

		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");

		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {

			// for (int i = 0; i < obj.length; i++) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				PurchaseOrder bean1 = new PurchaseOrder();

				bean1.setIttrpurchasecode(checkNull(String.valueOf(obj[i][0])));
				bean1
						.setIttrpurchaseempid(checkNull(String
								.valueOf(obj[i][1])));
				bean1.setIttrpurchaseempname(checkNull(String
						.valueOf(obj[i][2])));
				bean1.setIttrpurchaseorderdate(checkNull(String
						.valueOf(obj[i][3])));
				bean1
						.setIttrpurchasestatus(checkNull(String
								.valueOf(obj[i][4])));
				bean1.setIttrpurchaseempcode(checkNull(String
						.valueOf(obj[i][5])));
				bean1.setIttrpurchasevendorcode(checkNull(String
						.valueOf(obj[i][6])));
				bean1.setIttrpurchasevendorname(checkNull(String
						.valueOf(obj[i][7])));
				bean1.setIttrpurchaselocation(checkNull(String
						.valueOf(obj[i][8])));

				list.add(bean1);
			}

			bean.setTotalRecords("" + obj.length);
		} else {

			bean.setRecordsLength("false");

		}
		// bean.setEmpList(list);
		bean.setTotalRecords(String.valueOf(list.size()));
		if (list.size() > 0)
			bean.setRecordsLength("true");
		else
			bean.setRecordsLength("false");

		bean.setIteratorlist(list);

	}

	/*
	 * method name : deletecheckedRecords purpose : to delete the checked
	 * records return type : boolean parameter : bean,code
	 */

	public boolean deletecheckedRecords(PurchaseOrder bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					String sqlquery = " DELETE FROM HRMS_ASSET_PURCHASE_DTL  WHERE PURCHASE_CODE="
							+ code[i];
					result = getSqlModel().singleExecute(sqlquery);
					if (result) {
						String delratelst = "DELETE FROM HRMS_ASSET_PURCHASE_HDR A WHERE A.PURCHASE_CODE = "
								+ code[i];
						result = getSqlModel().singleExecute(delratelst);
					}
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

	public void getAllTypeOfApplications(PurchaseOrder bean,
			HttpServletRequest request, String userEmpId) {
		String draftquery = "SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS "
				+ " ,PURCHASE_ORDER_NO FROM HRMS_ASSET_PURCHASE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
				+ " WHERE PURCHASE_STATUS='D' AND (PURCHASE_EMP_CODE="
				+ userEmpId + " ) ";

		String submittedQuery = "SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS "
				+ " ,PURCHASE_ORDER_NO FROM HRMS_ASSET_PURCHASE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
				+ " WHERE PURCHASE_STATUS='P' AND (PURCHASE_EMP_CODE="
				+ userEmpId + " ) ";

		String returnedQuery = "SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS "
				+ " ,PURCHASE_ORDER_NO FROM HRMS_ASSET_PURCHASE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
				+ " WHERE PURCHASE_STATUS='B' AND (PURCHASE_EMP_CODE="
				+ userEmpId + " ) ";

		Object[][] draftObj = getSqlModel().getSingleResult(draftquery);
		Object[][] submitObj = getSqlModel().getSingleResult(submittedQuery);
		Object[][] returnObj = getSqlModel().getSingleResult(returnedQuery);

		if (draftObj != null && draftObj.length > 0) {
			ArrayList draftList = new ArrayList();
			int c1 = 0;
			for (int i = 0; i < draftObj.length; i++) {
				PurchaseOrder bean1 = new PurchaseOrder();
				bean1.setCode(checkNull(String.valueOf(draftObj[i][0])));
				bean1.setEmpToken(checkNull(String.valueOf(draftObj[i][1])));
				bean1.setEmpName(checkNull(String.valueOf(draftObj[i][2])));
				bean1.setOrderDate(checkNull(String.valueOf(draftObj[i][4])));
				bean1.setStatus(checkNull(String.valueOf(draftObj[i][6])));
				bean1
						.setPurchaseNoItt(checkNull(String
								.valueOf(draftObj[i][7])));
				draftList.add(bean1);
				++c1;
			}// End of for
			logger.info("i = " + c1);
			logger.info("draftList.length " + draftList.size());
			bean.setDraftList(draftList);
		}// End of if
		// Submitted Data

		if (submitObj != null && submitObj.length > 0) {
			ArrayList submittedList = new ArrayList();

			for (int i = 0; i < submitObj.length; i++) {
				PurchaseOrder bean1 = new PurchaseOrder();
				bean1.setCode(checkNull(String.valueOf(submitObj[i][0])));
				bean1.setEmpToken(checkNull(String.valueOf(submitObj[i][1])));
				bean1.setEmpName(checkNull(String.valueOf(submitObj[i][2])));
				bean1.setOrderDate(checkNull(String.valueOf(submitObj[i][4])));
				bean1.setStatus(checkNull(String.valueOf(submitObj[i][6])));
				bean1.setPurchaseNoItt(checkNull(String
						.valueOf(submitObj[i][7])));
				submittedList.add(bean1);

			}// End of for

			logger.info("submittedList.length " + submittedList.size());
			bean.setSubmittedList(submittedList);
		}// End of if

		if (returnObj != null && returnObj.length > 0) {
			ArrayList returnedList = new ArrayList();

			for (int i = 0; i < returnObj.length; i++) {
				PurchaseOrder bean1 = new PurchaseOrder();
				bean1.setCode(checkNull(String.valueOf(returnObj[i][0])));
				bean1.setEmpToken(checkNull(String.valueOf(returnObj[i][1])));
				bean1.setEmpName(checkNull(String.valueOf(returnObj[i][2])));
				bean1.setOrderDate(checkNull(String.valueOf(returnObj[i][4])));
				bean1.setStatus(checkNull(String.valueOf(returnObj[i][6])));
				bean1.setPurchaseNoItt(checkNull(String
						.valueOf(returnObj[i][7])));
				returnedList.add(bean1);

			}// End of for

			logger.info("returnedList.length " + returnedList.size());
			bean.setReturnedList(returnedList);
		}// End of if

	}

	public void getApprovedList(PurchaseOrder bean, HttpServletRequest request,
			String userEmpId) {
		// TODO Auto-generated method stub

		try {
			String approvedQuery = "SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
					+ " HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS,PURCHASE_ORDER_NO "
					+ " FROM HRMS_ASSET_PURCHASE_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
					+ " WHERE PURCHASE_STATUS='A' AND PURCHASE_EMP_CODE="
					+ userEmpId;
			Object[][] approvedObj = getSqlModel().getSingleResult(
					approvedQuery);
			String pageIndex[] = Utility.doPaging(bean.getMyPage(),
					approvedObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}// End of if
			request.setAttribute("totalPage1", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo1", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			logger.info("approved Data length " + approvedObj.length);
			if (approvedObj != null && approvedObj.length > 0) {
				ArrayList approvedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					PurchaseOrder bean1 = new PurchaseOrder();
					bean1.setCode(checkNull(String.valueOf(approvedObj[i][0])));
					bean1.setEmpToken(checkNull(String
							.valueOf(approvedObj[i][1])));
					bean1.setEmpName(checkNull(String
							.valueOf(approvedObj[i][2])));
					bean1.setOrderDate(checkNull(String
							.valueOf(approvedObj[i][4])));
					bean1
							.setStatus(checkNull(String
									.valueOf(approvedObj[i][6])));
					bean1.setPurchaseNoItt(checkNull(String
							.valueOf(approvedObj[i][7])));
					approvedList.add(bean1);
				}// End of for
				bean.setApprovedList(approvedList);
				logger.info("list length : " + approvedList.size());
				bean.setTotalRecords(String.valueOf(approvedObj.length));
				bean.setListLengthApproved("true");
			}// End of if
			else
				bean.setListLengthApproved("false");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getRejectedList(PurchaseOrder bean, HttpServletRequest request,
			String userEmpId) {
		// TODO Auto-generated method stub
		try {
			String rejectedQuery = " SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '|| "
					+ " HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS ,PURCHASE_ORDER_NO"
					+ " FROM HRMS_ASSET_PURCHASE_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
					+ " WHERE PURCHASE_STATUS='R' AND (PURCHASE_EMP_CODE="
					+ userEmpId + " ) ";
			Object[][] rejectedObj = getSqlModel().getSingleResult(
					rejectedQuery);

			String[] pageIndexRejected = Utility.doPaging(bean
					.getMyPageRejected(), rejectedObj.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}

			request.setAttribute("totalPageRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("PageNoRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				bean.setMyPageRejected("1");

			if (rejectedObj != null && rejectedObj.length > 0) {
				ArrayList rejectedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					PurchaseOrder bean1 = new PurchaseOrder();
					bean1.setCode(checkNull(String.valueOf(rejectedObj[i][0])));
					bean1.setEmpToken(checkNull(String
							.valueOf(rejectedObj[i][1])));
					bean1.setEmpName(checkNull(String
							.valueOf(rejectedObj[i][2])));
					bean1.setOrderDate(checkNull(String
							.valueOf(rejectedObj[i][4])));
					bean1
							.setStatus(checkNull(String
									.valueOf(rejectedObj[i][6])));
					bean1.setPurchaseNoItt(checkNull(String
							.valueOf(rejectedObj[i][7])));
					rejectedList.add(bean1);
				}// End of for
				bean.setRejectedList(rejectedList);
				bean.setTotalRecords(String.valueOf(rejectedObj.length));
				bean.setListLengthRejected("true");
			}// End of if
			else
				bean.setListLengthRejected("false");
		} catch (Exception e) {
			logger.info("Exception in rejected application  model " + e);
		}
	}

	public void getAssignedList(PurchaseOrder bean, HttpServletRequest request,
			String userEmpId) {
		// TODO Auto-generated method stub
		try {
			String assignedQuery = "SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
					+ " HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS "
					+ " FROM HRMS_ASSET_PURCHASE_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
					+ " WHERE PURCHASE_STATUS='S' AND (PURCHASE_EMP_CODE="
					+ userEmpId + " ) ";
			Object[][] assignedObj = getSqlModel().getSingleResult(
					assignedQuery);
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					assignedObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}// End of if
			request.setAttribute("totalPage3", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo3", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				;
			bean.setMyPage("1");
			if (assignedObj != null && assignedObj.length > 0) {
				ArrayList assignedList = new ArrayList();
				for (int i = 0; i < assignedObj.length; i++) {
					PurchaseOrder bean1 = new PurchaseOrder();
					bean1.setCode(checkNull(String.valueOf(assignedObj[i][0])));
					bean1.setEmpToken(checkNull(String
							.valueOf(assignedObj[i][1])));
					bean1.setEmpName(checkNull(String
							.valueOf(assignedObj[i][2])));
					bean1.setOrderDate(checkNull(String
							.valueOf(assignedObj[i][4])));
					bean1
							.setStatus(checkNull(String
									.valueOf(assignedObj[i][6])));
					assignedList.add(bean1);
				}// End of for
				bean.setAssignedList(assignedList);
				bean.setTotalRecords(String.valueOf(assignedObj.length));
				bean.setListLengthAssigned("true");
			}// End of if
			else
				bean.setListLengthAssigned("false");
		} catch (Exception e) {
			logger.info("Exception in assigned list  model " + e);
		}
	}

	public boolean withdrawApplication(PurchaseOrder bean) {
		boolean result = false;
		try {
			String sqlquery = "SELECT PURCHASE_CODE,PURCHASE_STATUS FROM HRMS_ASSET_PURCHASE_HDR  WHERE PURCHASE_STATUS = 'P' AND PURCHASE_LEVEL = '1' AND PURCHASE_CODE = "
					+ bean.getPurchaseCode();
			Object[][] obj = getSqlModel().getSingleResult(sqlquery);
			if (obj.length > 0) {
				String query = " UPDATE HRMS_ASSET_PURCHASE_HDR SET PURCHASE_STATUS = 'W' , PURCHASE_LEVEL = '1' WHERE PURCHASE_CODE =  "
						+ bean.getPurchaseCode();
				result = getSqlModel().singleExecute(query);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	public boolean setApproverData(PurchaseOrder bean, Object[][] empFlow) {

	 boolean flag =false;

		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][2];
				for (int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,  "
							+ "  EMP_ID FROM HRMS_EMP_OFFC "
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
					approverDataObj[i][1] = String.valueOf(temObj[0][1]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						PurchaseOrder purchaseBean = new PurchaseOrder();
						purchaseBean.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						purchaseBean.setSrNoIterator(srNo);
						
						if(String.valueOf(approverDataObj[i][1]).equals(bean.getUserEmpId()))
						{
							flag =true;
						}
						arrList.add(purchaseBean);
					}
					bean.setApproverList(arrList);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in setApproverData------" + e);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	public void getEmployeeDetails(PurchaseOrder purchaseOrder) {
		// TODO Auto-generated method stub

		String query = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, CENTER_NAME, DEPT_NAME, RANK_NAME, "
				+ " EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
				+ " WHERE EMP_ID = " + purchaseOrder.getEmpCode();
		Object[][] obj = getSqlModel().getSingleResult(query);

		if (obj != null && obj.length > 0) {
			purchaseOrder.setEmpToken(checkNull(String.valueOf(obj[0][0])));
			purchaseOrder.setEmpName(checkNull(String.valueOf(obj[0][1])));

			/*
			 * purchaseOrder.setBranch(checkNull(String.valueOf(obj[0][2])));
			 * purchaseOrder.setDept(checkNull(String.valueOf(obj[0][3])));
			 * purchaseOrder.setDesig(checkNull(String.valueOf(obj[0][4])));
			 */
		}// End of if
	}// End of function

	public void getComponyLogo(HttpServletRequest request) {

		String imageName = "";
		String companyAddress = "";
		try {
			String query = "SELECT COMPANY_LOGO ,COMPANY_ADDRESS FROM  HRMS_COMPANY";
			Object[][] logoquery = getSqlModel().getSingleResult(query);
			if (logoquery != null && logoquery.length > 0) {
				imageName = String.valueOf(logoquery[0][0]);
				companyAddress = String.valueOf(logoquery[0][1]);
			}
			if (!imageName.equals("")) {
				request.setAttribute("logo", imageName);
			}
			if (!companyAddress.equals("")) {
				request.setAttribute("companyAddress", companyAddress);
			}
		} catch (Exception e) {
			imageName = "";
			companyAddress = "";
		}

	}

	public void setAssetList(HttpServletRequest request, PurchaseOrder bean) {
		String itemIterator[] = request.getParameterValues("itemIterator");
		String itemCodeIterator[] = request
				.getParameterValues("itemCodeIterator");
		String priceIterator[] = request.getParameterValues("priceIterator");
		String quantityIterator[] = request
				.getParameterValues("quantityIterator");
		String unitIterator[] = request.getParameterValues("unitIterator");
		String totalIterator[] = request.getParameterValues("totalIterator");

		ArrayList<PurchaseOrder> assetList = new ArrayList<PurchaseOrder>();
		if (itemIterator != null && itemIterator.length > 0) {
			for (int i = 0; i < itemIterator.length; i++) {
				PurchaseOrder poObj = new PurchaseOrder();
				poObj.setItemIterator(itemIterator[i]);
				poObj.setItemCodeIterator(itemCodeIterator[i]);
				poObj.setPriceIterator(priceIterator[i]);
				poObj.setQuantityIterator(quantityIterator[i]);
				poObj.setUnitIterator(unitIterator[i]);
				poObj.setTotalIterator(totalIterator[i]);
				assetList.add(poObj);

			}
		}
		bean.setOrderList(assetList);
	}

	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empName, PurchaseOrder purchaseOrder) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					PurchaseOrder bean = new PurchaseOrder();

					bean.setKeepInformedEmpId(empCode[i]);
					bean.setKeepInformedEmpName(empName[i]);
					tableList.add(bean);

				}
				tableList.remove(Integer.parseInt(purchaseOrder
						.getCheckRemove()) - 1);

			}

			purchaseOrder.setKeepInformedList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformedData------" + e);
		}

	}

	public void updateKeepInfo(HttpServletRequest request, PurchaseOrder bean) {
		try {
			String str = "";
			String keepInformedEmpId[] = request
					.getParameterValues("keepInformedEmpId");
			if (keepInformedEmpId != null && keepInformedEmpId.length > 0) {
				for (int i = 0; i < keepInformedEmpId.length; i++) {
					if (i == 0) {
						str += keepInformedEmpId[i];
					} else {
						str += "," + keepInformedEmpId[i];
					}

				}

				String query = " update  HRMS_ASSET_PURCHASE_HDR "
						+ " set PURCHASE_KEEP_INFORM='" + str + "'"
						+ "where PURCHASE_CODE=" + bean.getPurchaseCode();

				getSqlModel().singleExecute(query);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setVendorDetails(PurchaseOrder bean) {
		String sqlQuery = "SELECT VENDOR_ADDRESS,VENDOR_CONTACT_NO FROM HRMS_ASSET_PURCHASE_HDR  "
				+ "LEFT JOIN HRMS_VENDOR ON (HRMS_ASSET_PURCHASE_HDR.PURCHASE_VENDOR_CODE=HRMS_VENDOR.VENDOR_CODE) "
				+ "WHERE HRMS_ASSET_PURCHASE_HDR.PURCHASE_VENDOR_CODE = "
				+ bean.getVendorCode();
		Object[][] data = getSqlModel().getSingleResult(sqlQuery);
		if (data != null && data.length > 0) {
			bean.setVendorAddress(String.valueOf(data[0][0]));
			bean.setVendorContact(String.valueOf(data[0][1]));
		}
	}

	public void setKeepInformList(HttpServletRequest request, PurchaseOrder bean) {

		String keepInformedEmpId[] = request
				.getParameterValues("keepInformedEmpId");
		String keepInformedEmpName[] = request
				.getParameterValues("keepInformedEmpName");
		ArrayList<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
		if (keepInformedEmpId != null && keepInformedEmpId.length > 0) {
			for (int i = 0; i < keepInformedEmpId.length; i++) {
				PurchaseOrder poObj = new PurchaseOrder();
				poObj.setKeepInformedEmpId(keepInformedEmpId[i]);
				poObj.setKeepInformedEmpName(keepInformedEmpName[i]);
				poList.add(poObj);

			}
		}
		bean.setKeepInformedList(poList);
	}

	public void genReport(HttpServletRequest request,
			HttpServletResponse response, PurchaseOrder bean, String logoPath,
			Object[][] empFlow,Object[][] empFirstApprover) {
		try {
			final ReportDataSet rds = new ReportDataSet();
			final String reportType = "Pdf";
			rds.setReportType(reportType);
			final String strFileName = "Purchase Order";
			rds.setFileName(strFileName);
			rds.setReportName(strFileName);
			rds.setPageOrientation("portrait");
			rds.setPageSize("A4");
			final org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			String strQuery = " SELECT PURCHASE_ORDER_NO , PURCHASE_ORDER_NAME , TO_CHAR(PURCHASE_REQUIRED_DATE,'DD-MM-YYYY'),"
					+ " DECODE(PURCHASE_STATUS,'P','PENDING','A','APPROVED','B','SENT BACK','R','REJECTED')"
					+ " ,VENDOR_NAME,VENDOR_ADDRESS,VENDOR_CONTACT_NO,NVL(PURCHASE_SHIPPING_ADDRESS,''), NVL(PURCHASE_BILLING_ADDRESS,'') "
					+ " ,NVL(COST_CENTER_NAME,''),NVL(SUB_COST_CENTER_NAME,'') "
					+ " ,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),EMP_FNAME||' '||EMP_LNAME "
					+ " ,NVL(PURCHASE_REMARKS,''), NVL(PURCHASE_TERMS,''), "
					+ " nvl(PURCHASE_SUBTOTAL,0), nvl(PURCHASE_DISCOUNT,0), nvl(PURCHASE_DISCOUNT_AMT,0),"
					+ " nvl(PURCHASE_AFTER_DISCOUNT,0), nvl(PURCHASE_SHIPPING_COST,0),nvl( PURCHASE_SALE_TAX_PERCENTAGE,0),nvl(PURCHASE_SALE_TAX_AMOUNTE,0), "
					+ " nvl(PURCHASE_ADDITIONAL_TAX_RATE,0), nvl(PURCHASE_ADDITIONAL_TAX_AMT,0), DECODE(PURCHASE_ADJUSTMENT_SIGN,'S','-','')||''||nvl(PURCHASE_PRICE_ADJUSTMENT,0), nvl(PURCHASE_FINAL_AMOUNT,0), PURCHASE_ADJUSTMENT_SIGN "
					+ " FROM HRMS_ASSET_PURCHASE_HDR "
					+ " LEFT JOIN HRMS_VENDOR ON (HRMS_ASSET_PURCHASE_HDR.PURCHASE_VENDOR_CODE=HRMS_VENDOR.VENDOR_CODE) "
					+ " LEFT JOIN HRMS_COST_CENTER ON (HRMS_ASSET_PURCHASE_HDR.PURCHASE_COST_CENTER=HRMS_COST_CENTER.COST_CENTER_ID) "
					+ " LEFT JOIN HRMS_SUB_COST_CENTER ON (HRMS_ASSET_PURCHASE_HDR.PURCHASE_COST_CENTER=HRMS_SUB_COST_CENTER.COST_CENTER_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
					+ " WHERE PURCHASE_CODE=" + bean.getPurchaseCode();

			Object purchaseOrderQueryObj[][] = getSqlModel().getSingleResult(
					strQuery);
			String query = "SELECT COMPANY_LOGO,COMPANY_ADDRESS FROM  HRMS_COMPANY";
			Object[][] addressQueryObj = getSqlModel().getSingleResult(query);

			String divNameQuery = " SELECT DIV_NAME FROM HRMS_DIVISION "
					+ " WHERE DIV_ID=(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ bean.getEmpCode().trim() + ")";
			Object divNameQueryObj[][] = getSqlModel().getSingleResult(
					divNameQuery);
			String divName = "";
			if (divNameQueryObj != null && divNameQueryObj.length > 0) {
				divName = String.valueOf(divNameQueryObj[0][0]);
			}

			final TableDataSet headerName = new TableDataSet();
			final Object[][] headerObj = new Object[2][3];
			headerObj[0][0] = rg.getImage(logoPath);
			headerObj[0][1] = divName;
			headerObj[1][0] = "\nAddress :\t" + addressQueryObj[0][1];
			// +"\nPhone Num:\t"+"020 -----------"
			// +"\nFax:\t"+"020-------------"
			// +"\nWeb Site\t:"+"www.glodyne.com";

			if (purchaseOrderQueryObj != null
					&& purchaseOrderQueryObj.length > 0) {
				headerObj[1][2] = "\n Order No :\t\t"
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][0]))
						+ "\n PO Name :\t\t"
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][1]))
						+ "\n Required By :\t\t"
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][2]))
						+ "\n PO Status :\t\t"
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][3]));
				headerName.setData(headerObj);
				headerName.setCellAlignment(new int[] { 0, 0, 0 });
				headerName.setCellWidth(new int[] { 45, 25, 30 });
				headerName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName.setBorder(false);
				headerName.setHeaderTable(true);
				headerName.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName);
				final TableDataSet headerName1 = new TableDataSet();
				final Object[][] headerObj1 = new Object[1][3];
				headerObj1[0][0] = "VENDOR DETAILS";
				headerObj1[0][1] = "SHIPPING ADDRESS";
				headerObj1[0][2] = "BILLING ADDRESS";
				headerName1.setData(headerObj1);
				headerName1.setCellAlignment(new int[] { 1, 1, 1 });
				headerName1.setCellWidth(new int[] { 30, 35, 35 });
				headerName1.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName1.setBorder(true);
				headerName1.setHeaderTable(true);
				// headerName1.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName1);
				final TableDataSet headerName2 = new TableDataSet();
				final Object[][] headerObj2 = new Object[1][3];
				headerObj2[0][0] = " Name :\t\t"
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][4]))
						+ "\n Address :\t\t"
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][5]))
						+ "\n Contact No. :\t\t"
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][6]));
				headerObj2[0][1] = "Address :\t\t "
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][7]));
				headerObj2[0][2] = "Address:\t\t "
						+ checkNull(String.valueOf(purchaseOrderQueryObj[0][8]));
				headerName2.setData(headerObj2);
				headerName2.setCellAlignment(new int[] { 0, 0, 0 });
				headerName2.setCellWidth(new int[] { 30, 35, 35 });
				headerName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName2.setBorder(true);
				headerName2.setHeaderTable(true);
				// headerName2.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName2);
				final TableDataSet headerName3 = new TableDataSet();
				final Object[][] headerObj3 = new Object[1][1];
				headerObj3[0][0] = "Order Details ";
				String dtlQuery = "  SELECT  DISTINCT ASSET_SUBTYPE_NAME,nvl(PURCHASE_ASSET_PRICE,0), "
						+ " nvl(PURCHASE_ASSET_QUANTITY,0),UNIT_NAME  "
						+ " ,nvl(PURCHASE_ASSET_PRICE*PURCHASE_ASSET_QUANTITY,0) "
						+ " FROM HRMS_ASSET_PURCHASE_DTL "
						+ " INNER JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_PURCHASE_DTL.PURCHASE_ASSET_CODE) "
						+ " INNER JOIN HRMS_UNIT_MASTER ON (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)  "
						+ " WHERE HRMS_ASSET_PURCHASE_DTL.PURCHASE_CODE="
						+ bean.getPurchaseCode();
				Object dtlQueryObj[][] = getSqlModel()
						.getSingleResult(dtlQuery);
				headerName3.setData(headerObj3);
				headerName3.setCellAlignment(new int[] { 0 });
				headerName3.setCellWidth(new int[] { 100 });
				headerName3.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName3.setBorder(false);
				headerName3.setHeaderTable(true);
				// headerName3.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName3);
				final TableDataSet headerName4 = new TableDataSet();
				Object[][] finalData = null;
				if (dtlQueryObj != null && dtlQueryObj.length > 0) {
					finalData = new Object[dtlQueryObj.length + 1][6];
					for (int i = 0; i < finalData.length; i++) {

						if (i == 0) {
							finalData[i][0] = "Sr.No";
							finalData[i][1] = "Asset";
							finalData[i][2] = "Price";
							finalData[i][3] = "Quantity";
							finalData[i][4] = "Unit";
							finalData[i][5] = "Total Price";

						} else {
							finalData[i][0] = String.valueOf(i);
							finalData[i][1] = checkNull(String
									.valueOf(dtlQueryObj[i - 1][0]));
							finalData[i][2] = checkNull(String
									.valueOf(dtlQueryObj[i - 1][1]));
							finalData[i][3] = checkNull(String
									.valueOf(dtlQueryObj[i - 1][2]));
							finalData[i][4] = checkNull(String
									.valueOf(dtlQueryObj[i - 1][3]));
							finalData[i][5] = checkNull(String
									.valueOf(dtlQueryObj[i - 1][4]));

						}

					}
				}

				headerName4.setData(finalData);
				headerName4.setCellAlignment(new int[] { 1, 1, 1, 1, 1, 1 });
				headerName4.setCellWidth(new int[] { 10, 25, 15, 20, 15, 20 });
				headerName4.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName4.setBorder(true);
				headerName4.setHeaderTable(true);
				// headerName4.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName4);
				final TableDataSet headerName5 = new TableDataSet();
				final Object[][] headerObj5 = new Object[8][2];
				headerObj5[0][0] = "Sub Total\t:";
				headerObj5[1][0] = "- Discount("
						+ Utility.twoDecimals(checkNull(String
								.valueOf(purchaseOrderQueryObj[0][16])))
						+ " %)\t:";
				headerObj5[2][0] = "Total(Net)\t:";
				headerObj5[3][0] = "+ Shipping Cost\t:";
				headerObj5[4][0] = "+ Sales Tax Rate("
						+ Utility.twoDecimals(checkNull(String
								.valueOf(purchaseOrderQueryObj[0][20])))
						+ " %)\t:";
				headerObj5[5][0] = "+ Addisional Tax Rate ("
						+ Utility.twoDecimals(checkNull(String
								.valueOf(purchaseOrderQueryObj[0][22])))
						+ " %)\t:";
				headerObj5[6][0] = "+/- Price Adjustment\t:";
				headerObj5[7][0] = "TOTAL\t:";
				headerObj5[0][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][15])));
				headerObj5[1][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][17])));
				headerObj5[2][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][18])));
				headerObj5[3][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][19])));
				headerObj5[4][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][21])));
				headerObj5[5][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][23])));
				headerObj5[6][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][24])));
				headerObj5[7][1] = Utility.twoDecimals(checkNull(String
						.valueOf(purchaseOrderQueryObj[0][25])));
				headerName5.setData(headerObj5);
				headerName5.setCellAlignment(new int[] { 2, 2 });
				headerName5.setCellWidth(new int[] { 90, 10 });
				headerName5.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName5.setBorder(false);
				headerName5.setHeaderTable(false);
				// headerName5.setBlankRowsBelow(1);
				// HashMap<String ,Object> map =
				// rg.joinTableDataSet(headerName5, headerName10, true, 70);
				rg.addTableToDoc(headerName5);

				final TableDataSet headerName6 = new TableDataSet();
				headerName6.setData(new Object[][] { { " " },
						{ "General Information" } });
				headerName6.setCellAlignment(new int[] { 0 });
				headerName6.setCellWidth(new int[] { 100 });
				headerName6.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName6.setBorder(false);
				headerName6.setHeaderTable(false);
				// headerName6.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName6);
				final TableDataSet headerName7 = new TableDataSet();
				final Object[][] headerObj7 = new Object[2][4];
				headerObj7[0][0] = "Purchase Order Placed By ";
				headerObj7[0][1] = checkNull(String
						.valueOf(purchaseOrderQueryObj[0][12]));
				headerObj7[0][2] = "Created Date";
				headerObj7[0][3] = checkNull(String
						.valueOf(purchaseOrderQueryObj[0][11]));
				headerObj7[1][0] = "Cost Center";
				headerObj7[1][1] = checkNull(String
						.valueOf(purchaseOrderQueryObj[0][9]));
				headerObj7[1][2] = "Sub Cost Center";
				headerObj7[1][3] = checkNull(String
						.valueOf(purchaseOrderQueryObj[0][10]));

				headerName7.setData(headerObj7);
				headerName7.setCellAlignment(new int[] { 0, 0, 0, 0 });
				headerName7.setCellWidth(new int[] { 25, 25, 25, 25 });
				headerName7.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName7.setBorder(true);
				headerName7.setHeaderTable(true);
				headerName7.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName7);
				// rg.PAGE_BREAK=2;
				final TableDataSet headerName8 = new TableDataSet();
				final Object[][] headerObj8 = new Object[purchaseOrderQueryObj.length + 1][2];
				for (int cnt = 0; cnt < headerObj8.length; cnt++) {
					if (cnt == 0) {
						headerObj8[cnt][0] = "Remarks";
						headerObj8[cnt][1] = "Terms";
					} else {
						headerObj8[cnt][0] = checkNull(String
								.valueOf(purchaseOrderQueryObj[0][13]));
						headerObj8[cnt][1] = checkNull(String
								.valueOf(purchaseOrderQueryObj[0][14]));
					}
				}
				headerName8.setData(headerObj8);
				headerName8.setCellAlignment(new int[] { 0, 0 });
				headerName8.setCellWidth(new int[] { 50, 50 });
				headerName8.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName8.setBorder(true);
				headerName8.setHeaderTable(true);
				headerName8.setBlankRowsBelow(3);
				rg.addTableToDoc(headerName8);

				/*String approvedByQuery = " SELECT EMP_FNAME||' '||EMP_LNAME NAME FROM HRMS_ASSET_PURCHASE_HDR "
						+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_CODE) WHERE PURCHASE_CODE="
						+ bean.getPurchaseCode() + "  ";*/
				//Object[][] empFlow1 = generateEmpFlow(String.valueOf(bean.getEmpCode()), "Purchase");
				String approvedByQuery="";
				Object approvObject[][]=null;
				if(empFirstApprover!=null && empFirstApprover.length >0)					
				approvedByQuery="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME FROM  HRMS_EMP_OFFC where emp_id="+empFirstApprover[0][0];
				
				approvObject = getSqlModel().getSingleResult(
						approvedByQuery);

				if (approvObject != null && approvObject.length > 0) {

					Object[][] verifiedByQueryObj = null;
					if (empFlow != null && empFlow.length > 0) {
						String verifiedByQuery = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME FROM  HRMS_EMP_OFFC where emp_id="
								+ String.valueOf(empFlow[0][0]);
						verifiedByQueryObj = getSqlModel().getSingleResult(
								verifiedByQuery);
					}

					if (verifiedByQueryObj != null
							&& verifiedByQueryObj.length > 0) {
						TableDataSet newData = new TableDataSet();
						newData.setData(new Object[][] {
								{ "____________________",
										"____________________",
										"_____________________" },
								{ "Applicant signature", "Approver signature",
										"Verified by signature" } });
						newData.setCellAlignment(new int[] { 1, 1, 1 });
						newData.setCellWidth(new int[] { 33, 34, 33 });
						newData.setBodyFontDetails(Font.HELVETICA, 10,
								Font.NORMAL, new Color(0, 0, 0));
						newData.setBorder(false);
						newData.setHeaderTable(false);
						rg.addTableToDoc(newData);

						TableDataSet newData1 = new TableDataSet();
						newData1
								.setData(new Object[][] {
										{
												"(" + bean.getEmpName().trim()
														+ ")",
												"("
														+ String
																.valueOf(approvObject[0][0])
														+ ")",
												"("
														+ String
																.valueOf(verifiedByQueryObj[0][0])
														+ ")" },
										{ " ", " ", " " } });
						newData1.setCellAlignment(new int[] { 1, 1, 1 });
						newData1.setCellWidth(new int[] { 33, 34, 33 });
						newData1.setBodyFontDetails(Font.HELVETICA, 10,
								Font.BOLD, new Color(0, 0, 0));
						newData1.setBorder(false);
						newData1.setHeaderTable(false);
						newData1.setBlankRowsBelow(1);
						rg.addTableToDoc(newData1);
					} else {
						TableDataSet newData = new TableDataSet();
						newData
								.setData(new Object[][] {
										{ "____________________",
												"____________________" },
										{ "Applicant signature",
												"Approver signature" } });
						newData.setCellAlignment(new int[] { 1, 1 });
						newData.setCellWidth(new int[] { 50, 50 });
						newData.setBodyFontDetails(Font.HELVETICA, 10,
								Font.NORMAL, new Color(0, 0, 0));
						newData.setBorder(false);
						newData.setHeaderTable(false);
						rg.addTableToDoc(newData);

						TableDataSet newData1 = new TableDataSet();
						newData1
								.setData(new Object[][] {
										{
												"(" + bean.getEmpName().trim()
														+ ")",
												"("
														+ String
																.valueOf(approvObject[0][0])
														+ ")" }, { " ", " " } });
						newData1.setCellAlignment(new int[] { 1, 1 });
						newData1.setCellWidth(new int[] { 50, 50 });
						newData1.setBodyFontDetails(Font.HELVETICA, 10,
								Font.BOLD, new Color(0, 0, 0));
						newData1.setBorder(false);
						newData1.setHeaderTable(false);
						newData1.setBlankRowsBelow(1);
						rg.addTableToDoc(newData1);
					}

				}

				rg.process();

			}

			rg.createReport(response);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
