package org.paradyne.model.reimbursement;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Master.TravelModeListMasterBean;
import org.paradyne.bean.reimbursement.ReimbConfigurationBean;
import org.paradyne.lib.ModelBase;

public class ReimbConfigurationModel extends ModelBase {

	public void getList(ReimbConfigurationBean bean, HttpServletRequest request) {

		Object[][] selData = null;

		String selQuery = "SELECT CREDIT_NAME, DECODE(IS_MANAGER_APPROVAL,'Y','YES','N','NO'),  DECODE(IS_ADMIN_APPROVAL,'Y','YES','N','NO'),H1.EMP_FNAME|| ' ' ||H1.EMP_MNAME|| ' ' ||H1.EMP_LNAME, H2.EMP_FNAME|| ' ' ||H2.EMP_MNAME|| ' ' ||H2.EMP_LNAME,REIMB_CREDIT_HEAD FROM HRMS_REIMB_CONFIG"
				+ " LEFT JOIN  HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD)"
				+ "  LEFT JOIN HRMS_EMP_OFFC H1  ON(H1.EMP_ID=HRMS_REIMB_CONFIG.REIMB_ADMIN)"
				+ "  LEFT JOIN HRMS_EMP_OFFC  H2  ON(H2.EMP_ID=HRMS_REIMB_CONFIG.REIMB_ACCOUNTANT)"
				+ " ORDER BY REIMB_CREDIT_HEAD";
		selData = getSqlModel().getSingleResult(selQuery);
		
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean
				.getMyPage(), selData.length, 20);
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
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		if (selData == null) {

		}

		ArrayList<Object> list = new ArrayList<Object>();

		if (selData != null && selData.length > 0) {
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
			.parseInt(String.valueOf(pageIndex[1])); i++) {

				ReimbConfigurationBean subBean = new ReimbConfigurationBean();

				subBean.setIttReimbHead(String.valueOf(selData[i][0]));
				subBean.setIttReimbManagerApproval(String
						.valueOf(selData[i][1]));
				subBean.setIttReimbAdminApproval(String.valueOf(selData[i][2]));
				subBean.setIttReimbAdmin(String.valueOf(selData[i][3]));
				subBean.setIttReimbAccountant(String.valueOf(selData[i][4]));
				subBean.setIttReimbCode(String.valueOf(selData[i][5]));
				subBean.setIttSrN0(String.valueOf(i + 1));
				list.add(subBean);
			}

		}
		bean.setReimbConfigurationItt(list);

	}

	public boolean save(ReimbConfigurationBean bean) {
		boolean result = false;

		String IdHead = bean.getIdHead();
		String reimbManagerApproval = bean.getReimbManagerApproval();
		String reimbAdminApproval = bean.getReimbAdminApproval();
		String EmpIdAdmin = bean.getEmpIdAdmin();
		String EmpIdAccountant = bean.getEmpIdAccountant();
		
		String paymentMod="";
		if(bean.getPaymentMode().equals("true")){
			
			 paymentMod = "Y";
		}else{
			 paymentMod = "N";
		}
		
		String paymentModCash ="";
		if(bean.getPaymentModeCash().equals("true")){
		
		 paymentModCash ="Y";
		}else{
			 paymentModCash ="N";
			
		}
		String paymentModCheque ="";
		if(bean.getPaymentModeCheque().equals("true")){
		
			paymentModCheque ="Y";
		}else{
			paymentModCheque ="N";
			
		}
		String paymentModSalary ="";
		
		if(bean.getPaymentModeSalary().equals("true")){
		
			paymentModSalary ="Y";
		}else{
			paymentModSalary ="N";
			
		}

		
	
		
		String transferAccount = bean.getTransferAccount();
		String reimbursementPeriod = bean.getReimbursementPeriod();
		String carrierForward = bean.getCarrierForward();
		String carrierPercentage=bean.getCarrierPercentage();
		String advanceAllowed = bean.getAdvanceAllowed();
		String startmonth=bean.getStartmonth();
		String endmonth=bean.getEndmonth();
		String yearFrom=bean.getYearFrom();
		String yearTo=bean.getYearTo();
		String isAccountant=bean.getReimbAccountantReqd();
		String carryFwd=bean.getCarryFwdPeriod();
		String advPeriod=bean.getAdvAllowedPeriod();

		Object[][] insData = new Object[1][21];
		insData[0][0] = IdHead;
		insData[0][1] = reimbManagerApproval;
		insData[0][2] = reimbAdminApproval;
		insData[0][3] = EmpIdAdmin;
		insData[0][4] = EmpIdAccountant;
		insData[0][5] = paymentMod;
		insData[0][6] = paymentModCash;
		insData[0][7] = paymentModCheque;
		insData[0][8] = paymentModSalary;
		insData[0][9] = transferAccount;
		insData[0][10] = reimbursementPeriod;
		insData[0][11] = carrierForward;
		insData[0][12] = carrierPercentage;
		insData[0][13] = advanceAllowed;
		insData[0][14] = yearFrom;
		insData[0][15] = startmonth;
		insData[0][16] = yearTo;
		insData[0][17] = endmonth;
		insData[0][18] = isAccountant;
		insData[0][19] = carryFwd;
		insData[0][20] = advPeriod;

		String insQuery = "INSERT INTO HRMS_REIMB_CONFIG (REIMB_CREDIT_HEAD, IS_MANAGER_APPROVAL, IS_ADMIN_APPROVAL, REIMB_ADMIN, REIMB_ACCOUNTANT, REIMB_PAYMODE_TRANSFER, REIMB_PAYMODE_CASH, REIMB_PAYMODE_CHEQUE, REIMB_PAYMODE_SALARY, REIMB_TRANSFER_ACCOUNT, REIMB_PERIOD, REIMB_CARRIED_FORWARD, REIMB_CARRIED_PERCENTAGE, IS_ADVANCE_ALLOWED,FINANCIAL_YEAR_FROM, FINANCIAL_FROM_MONTH, FINANCIAL_YEAR_TO, FINANCIAL_TO_MONTH, IS_ACCOUNTANT_APPROVAL, REIMB_CARRIED_PERIOD, REIMB_ADVANCE_PERIOD) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		result = getSqlModel().singleExecute(insQuery, insData);

		return result;
	}

	public boolean update(ReimbConfigurationBean bean) {
		boolean result;

		String IdHead = bean.getIdHead();
		String reimbManagerApproval = bean.getReimbManagerApproval();
		String reimbAdminApproval = bean.getReimbAdminApproval();
		String EmpIdAdmin = bean.getEmpIdAdmin();
		String EmpIdAccountant = bean.getEmpIdAccountant();
		
		String paymentMod="";
		if(bean.getPaymentMode().equals("true")){
			
			 paymentMod = "Y";
		}else{
			 paymentMod = "N";
		}
		
		String paymentModCash ="";
		if(bean.getPaymentModeCash().equals("true")){
		
		 paymentModCash ="Y";
		}else{
			 paymentModCash ="N";
			
		}
		String paymentModCheque ="";
		if(bean.getPaymentModeCheque().equals("true")){
		
			paymentModCheque ="Y";
		}else{
			paymentModCheque ="N";
			
		}
		String paymentModSalary ="";
		
		if(bean.getPaymentModeSalary().equals("true")){
		
			paymentModSalary ="Y";
		}else{
			paymentModSalary ="N";
			
		}
		String transferAccount = bean.getTransferAccount();
		String reimbursementPeriod = bean.getReimbursementPeriod();
		String carrierForward = bean.getCarrierForward();
		String carrierPercentage=bean.getCarrierPercentage();
		String advanceAllowed = bean.getAdvanceAllowed();
		String startmonth=bean.getStartmonth();
		String endmonth=bean.getEndmonth();
		String yearFrom=bean.getYearFrom();
		String yearTo=bean.getYearTo();
		String isAccountant=bean.getReimbAccountantReqd();
		String carryFwd=bean.getCarryFwdPeriod();
		String advPeriod=bean.getAdvAllowedPeriod();
		
		Object[][] upData = new Object[1][21];

		upData[0][0] = reimbManagerApproval;

		upData[0][1] = reimbAdminApproval;
		upData[0][2] = EmpIdAdmin;

		upData[0][3] = EmpIdAccountant;
		upData[0][4] = paymentMod;
		upData[0][5] = paymentModCash;
		upData[0][6] = paymentModCheque;
		upData[0][7] = paymentModSalary;

		upData[0][8] = transferAccount;
		upData[0][9] = reimbursementPeriod;

		upData[0][10] = carrierForward;
		upData[0][11] = carrierPercentage;
		upData[0][12] = advanceAllowed;
		
		upData[0][13] = yearFrom;
		upData[0][14] = startmonth;
		upData[0][15] = yearTo;
		upData[0][16] = endmonth;
		upData[0][17] = isAccountant;
		upData[0][18] = carryFwd;
		upData[0][19] = advPeriod;
		upData[0][20] = IdHead;
System.out.println(".................."+yearFrom);
		String upQuery = "UPDATE HRMS_REIMB_CONFIG SET  IS_MANAGER_APPROVAL=?, IS_ADMIN_APPROVAL=?, REIMB_ADMIN=?, REIMB_ACCOUNTANT=?,REIMB_PAYMODE_TRANSFER=?,REIMB_PAYMODE_CASH=?, REIMB_PAYMODE_CHEQUE=?, REIMB_PAYMODE_SALARY=?, REIMB_TRANSFER_ACCOUNT=?, REIMB_PERIOD=?, REIMB_CARRIED_FORWARD=?, REIMB_CARRIED_PERCENTAGE=?, IS_ADVANCE_ALLOWED=?,FINANCIAL_YEAR_FROM=?, FINANCIAL_FROM_MONTH=?, FINANCIAL_YEAR_TO=?, FINANCIAL_TO_MONTH=? , IS_ACCOUNTANT_APPROVAL=?, REIMB_CARRIED_PERIOD=?, REIMB_ADVANCE_PERIOD=? WHERE  REIMB_CREDIT_HEAD=? ";
				

		result = getSqlModel().singleExecute(upQuery, upData);

		return result;
	}

	public void dblClickItt(ReimbConfigurationBean bean) {
		
		getDetails(bean);
	}

	public void getDetails(ReimbConfigurationBean bean) {
		Object[][] selData = null;
		
		String IdHead = bean.getIdHead();
		
		System.out.println("...................................."+IdHead);
		
		
		String selQuery = "SELECT CREDIT_NAME, IS_MANAGER_APPROVAL, IS_ADMIN_APPROVAL,H1.EMP_FNAME|| ' ' ||H1.EMP_MNAME|| ' ' ||H1.EMP_LNAME, H2.EMP_FNAME|| ' ' ||H2.EMP_MNAME|| ' ' ||H2.EMP_LNAME, REIMB_PAYMODE_TRANSFER,REIMB_PAYMODE_CASH, REIMB_PAYMODE_CHEQUE, REIMB_PAYMODE_SALARY, REIMB_TRANSFER_ACCOUNT, REIMB_PERIOD, REIMB_CARRIED_FORWARD,REIMB_CARRIED_PERCENTAGE, IS_ADVANCE_ALLOWED,REIMB_ADMIN,H1.EMP_TOKEN, REIMB_ACCOUNTANT,H2.EMP_TOKEN,FINANCIAL_YEAR_FROM, FINANCIAL_FROM_MONTH, FINANCIAL_YEAR_TO, FINANCIAL_TO_MONTH, IS_ACCOUNTANT_APPROVAL, REIMB_CARRIED_PERIOD, REIMB_ADVANCE_PERIOD FROM HRMS_REIMB_CONFIG"
			+ " LEFT JOIN  HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD)"
			+ "  LEFT JOIN HRMS_EMP_OFFC H1  ON(H1.EMP_ID=HRMS_REIMB_CONFIG.REIMB_ADMIN)"
			+ "  LEFT JOIN HRMS_EMP_OFFC  H2  ON(H2.EMP_ID=HRMS_REIMB_CONFIG.REIMB_ACCOUNTANT) WHERE REIMB_CREDIT_HEAD="+IdHead;
		
	selData = getSqlModel().getSingleResult(selQuery);
		
	bean.setReimbHead(checkNull(String.valueOf(selData[0][0])));
		bean.setReimbManagerApproval(checkNull(String.valueOf(selData[0][1])));
		bean.setReimbAdminApproval(checkNull(String.valueOf(selData[0][2])));
		bean.setReimbAdmin(checkNull(String.valueOf(selData[0][3])));
		bean.setReimbAccountant(checkNull(String.valueOf(selData[0][4])));
		
		if( checkNull(String.valueOf(selData[0][5])).equals("Y")){
			
		bean.setPaymentMode("true");
		}
		else{
			bean.setPaymentMode("false");
		}
		if( checkNull(String.valueOf(selData[0][6])).equals("Y")){
			bean.setPaymentModeCash("true");
		}else{
			bean.setPaymentModeCash("false");
		}
		
		if( checkNull(String.valueOf(selData[0][7])).equals("Y")){
		bean.setPaymentModeCheque("true");
		}else{
			bean.setPaymentModeCheque("false");
		}
		if( checkNull(String.valueOf(selData[0][8])).equals("Y")){
		bean.setPaymentModeSalary("true");
		}else{
			bean.setPaymentModeSalary("false");
		}
		
		bean.setTransferAccount(checkNull(String.valueOf(selData[0][9])));
		bean.setReimbursementPeriod(checkNull(String.valueOf(selData[0][10])));
		bean.setCarrierForward(checkNull(String.valueOf(selData[0][11])));
		bean.setCarrierPercentage(checkNull(String.valueOf(selData[0][12])));
		bean.setAdvanceAllowed(checkNull(String.valueOf(selData[0][13])));
		bean.setEmpIdAdmin(checkNull(String.valueOf(selData[0][14])));
		bean.setEmpTokenAdmin(checkNull(String.valueOf(selData[0][15])));
		bean.setEmpIdAccountant(checkNull(String.valueOf(selData[0][16])));
		bean.setEmpTokenAccountant(checkNull(String.valueOf(selData[0][17])));
		bean.setYearFrom(checkNull(String.valueOf(selData[0][18])));
		bean.setStartmonth(checkNull(String.valueOf(selData[0][19])));
		bean.setYearTo(checkNull(String.valueOf(selData[0][20])));
		bean.setEndmonth(checkNull(String.valueOf(selData[0][21])));
		bean.setReimbAccountantReqd(checkNull(String.valueOf(selData[0][22])));
		bean.setCarryFwdPeriod(checkNull(String.valueOf(selData[0][23])));
		bean.setAdvAllowedPeriod(checkNull(String.valueOf(selData[0][24])));
	}

	public boolean delete(ReimbConfigurationBean bean,
			HttpServletRequest request) {
		boolean result = false;

		String IdHead = bean.getIdHead();


		String delQuery = "DELETE FROM HRMS_REIMB_CONFIG WHERE REIMB_CREDIT_HEAD="+IdHead;
		result = getSqlModel().singleExecute(delQuery);
		getList(bean, request);

		return result;
	}

	public boolean check(ReimbConfigurationBean bean) {
		boolean result = false;
		String IdHead = bean.getIdHead();
		String selQuery = "SELECT REIMB_CREDIT_HEAD	FROM HRMS_REIMB_CONFIG where REIMB_CREDIT_HEAD="
				+ IdHead;

		Object[][] data = getSqlModel().getSingleResult(selQuery);
		if (data.length > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean deleteCheck(ReimbConfigurationBean bean, String[] code,
			String[] reimbCode, HttpServletRequest request) {
		boolean flag = false;
		
		for (int j = 0; j < code.length; j++) {
			if (code[j].equals("Y")) {

				String delQuery = "DELETE FROM HRMS_REIMB_CONFIG WHERE REIMB_CREDIT_HEAD="
						+ reimbCode[j] + "";
				flag = getSqlModel().singleExecute(delQuery);

			}

		}
		getList(bean, request);

		return flag;
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	
	

}
