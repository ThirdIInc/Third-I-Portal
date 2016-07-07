package org.paradyne.model.voucher;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.voucher.CashVoucherMaster;
import org.paradyne.bean.voucher.VoucherApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

public class VoucherApplicationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VoucherApplicationModel.class);
	NumberFormat testNumberFormat = new DecimalFormat("#0.00");

	public void getAllPendingApplications(VoucherApplication bean,
			HttpServletRequest request) {
		try {
			String sQuery = " SELECT B.EMP_TOKEN, B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME EMP_NAME, "
					+ " TO_CHAR( A.APP_DATE,'DD-MM-YYYY') APP_DATE, A.STATUS, A.VOUCHER_APPL_CODE, A.VOUCHER_NO "
					+ " FROM HRMS_VOUCHER_APPL A "
					+ " INNER JOIN HRMS_EMP_OFFC B ON (A.EMP_CODE = B.EMP_ID) "
					+ " WHERE A.EMP_CODE = " + bean.getUserEmpId();
			sQuery += " ORDER BY VOUCHER_APPL_CODE DESC ";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);
			if ((objData != null) && (objData.length > 0)) {
				ArrayList <VoucherApplication> tmpLstDraft = new ArrayList<VoucherApplication>();
				ArrayList <VoucherApplication>tmpLstPending = new ArrayList<VoucherApplication>();
				ArrayList <VoucherApplication>tmpLstReturn = new ArrayList<VoucherApplication>();
				int counter1 = 0;
				int counter2 = 0;
				int counter3 = 0;
				VoucherApplication bean1 = null;

				for (int i = 0; i < objData.length; i++) {
					if ((String.valueOf(objData[i][3]).equals("D"))
							|| (String.valueOf(objData[i][3]).equals("W"))) {
						bean1 = new VoucherApplication();
						bean1.setSSrNo(String.valueOf(++counter1));
						bean1.setEmpId(checkNull(String
								.valueOf(objData[i][0])));
						bean1.setEname(checkNull(String
										.valueOf(objData[i][1])));
						bean1.setVchDate(checkNull(String
								.valueOf(objData[i][2])));
						bean1.setStatus(checkNull(String
										.valueOf(objData[i][3])));
						bean1.setVCode(checkNull(String
										.valueOf(objData[i][4])));
						bean1.setVoucherNo(checkNull(String
								.valueOf(objData[i][5])));
						tmpLstDraft.add(bean1);
					} else if (String.valueOf(objData[i][3]).equals("P")) {
						bean1 = new VoucherApplication();
						bean1.setSSrNo(String.valueOf(++counter2));
						bean1.setEmpId(checkNull(String
								.valueOf(objData[i][0])));
						bean1.setEname(checkNull(String
										.valueOf(objData[i][1])));
						bean1.setVchDate(checkNull(String
								.valueOf(objData[i][2])));
						bean1.setStatus(checkNull(String
										.valueOf(objData[i][3])));
						bean1.setVCode(checkNull(String
										.valueOf(objData[i][4])));
						bean1.setVoucherNo(checkNull(String
								.valueOf(objData[i][5])));
						tmpLstPending.add(bean1);

					} else if (String.valueOf(objData[i][3]).equals("B")) {
						bean1 = new VoucherApplication();
						bean1.setSSrNo(String.valueOf(++counter3));
						bean1.setEmpId(checkNull(String
								.valueOf(objData[i][0])));
						bean1.setEname(checkNull(String
										.valueOf(objData[i][1])));
						bean1.setVchDate(checkNull(String
								.valueOf(objData[i][2])));
						bean1.setStatus(checkNull(String
										.valueOf(objData[i][3])));
						bean1.setVCode(checkNull(String
										.valueOf(objData[i][4])));
						tmpLstReturn.add(bean1);
					}
				}
				bean.setLstDraft(tmpLstDraft);
				bean.setLstPending(tmpLstPending);
				bean.setLstReturn(tmpLstReturn);
			}
		} catch (Exception e) {
			logger.error("Error in VoucherApplicationModel.getAllPendingApplications() method Model : "
							+ e.getMessage());
		}
	}

	public void getApprovedApplications(VoucherApplication bean,
			HttpServletRequest request) {
		try {
			String sQuery = " SELECT B.EMP_TOKEN, B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME EMP_NAME, "
					+ " TO_CHAR( A.APP_DATE,'DD-MM-YYYY') APP_DATE, A.STATUS, A.VOUCHER_APPL_CODE  "
					+ " FROM HRMS_VOUCHER_APPL A "
					+ " INNER JOIN HRMS_EMP_OFFC B ON (A.EMP_CODE = B.EMP_ID) WHERE A.EMP_CODE= "
					+ bean.getUserEmpId() + " AND A.STATUS = 'A'";
			sQuery += " ORDER BY A.VOUCHER_APPL_CODE DESC ";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);
			int counter1 = 0;
			ArrayList<VoucherApplication> tmpLstApproved = new ArrayList<VoucherApplication>();
			VoucherApplication bean1 = null;

			if ((objData != null) && (objData.length > 0)) {
				for (int i = 0; i < objData.length; i++) {
					bean1 = new VoucherApplication();
					bean1.setSSrNo(String.valueOf(++counter1));
					bean1.setEmpId(checkNull(String.valueOf(objData[i][0])));
					bean1.setEname(checkNull(String.valueOf(objData[i][1])));
					bean1.setVchDate(checkNull(String.valueOf(objData[i][2])));
					bean1.setStatus(checkNull(String.valueOf(objData[i][3])));
					bean1.setVCode(checkNull(String.valueOf(objData[i][4])));
					tmpLstApproved.add(bean1);
				}
				bean.setLstApproved(tmpLstApproved);
			}
		} catch (Exception e) {
			logger.error("Error in VoucherApplicationModel.getApprovedApplications() method Model : "
							+ e.getMessage());
		}
	}

	public void getRejectedApplications(VoucherApplication bean,
			HttpServletRequest request) {
		try {
			String sQuery = " SELECT B.EMP_TOKEN, B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME EMP_NAME, "
					+ " TO_CHAR( A.APP_DATE,'DD-MM-YYYY') APP_DATE, A.STATUS, A.VOUCHER_APPL_CODE "
					+ " FROM HRMS_VOUCHER_APPL A "
					+ " INNER JOIN HRMS_EMP_OFFC B ON (A.EMP_CODE = B.EMP_ID) WHERE A.EMP_CODE= "
					+ bean.getUserEmpId() + " AND A.STATUS = 'R'";
			sQuery += " ORDER BY A.VOUCHER_APPL_CODE DESC ";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);
			int counter1 = 0;
			ArrayList<VoucherApplication> tmpLstRejected = new ArrayList<VoucherApplication>();
			VoucherApplication bean1 = null;
			if ((objData != null) && (objData.length > 0)) {
				for (int i = 0; i < objData.length; i++) {
					bean1 = new VoucherApplication();
					bean1.setSSrNo(String.valueOf(++counter1));
					bean1.setEmpId(checkNull(String.valueOf(objData[i][0])));
					bean1.setEname(checkNull(String.valueOf(objData[i][1])));
					bean1.setVchDate(checkNull(String.valueOf(objData[i][2])));
					bean1.setStatus(checkNull(String.valueOf(objData[i][3])));
					bean1.setVCode(checkNull(String.valueOf(objData[i][4])));
					tmpLstRejected.add(bean1);
				}
				bean.setLstRejected(tmpLstRejected);
			}
		} catch (Exception e) {
			logger
					.error("Error in VoucherApplicationModel.getRejectedApplications() method Model : "
							+ e.getMessage());
		}
	}

	/*
	 * Method Name : addVoucher purpose : to add voucher Details in the list.
	 */
	public void addVoucher(VoucherApplication bean, HttpServletRequest request,
			int remove) {
		Object[] srNo = request.getParameterValues("srNo");
		Object[] voucherHeadCode = request.getParameterValues("vchCode");
		Object[] voucherHeadName = request.getParameterValues("VoucherHead");
		Object[] amount = request.getParameterValues("vamt");
		Object[] remark = request.getParameterValues("vrem");
		Object[] proof = request.getParameterValues("vproof");
		Object[] upload = request.getParameterValues("uploadFile");

		double total = 0.0;
		ArrayList<VoucherApplication> tableList = new ArrayList<VoucherApplication>();
		if (srNo != null){
			for (int i = 0; i < srNo.length; i++) {
				VoucherApplication bean1 = new VoucherApplication();
				bean1.setSrNo(String.valueOf(i + 1)); // Sr No
				bean1.setVchCode(String.valueOf(voucherHeadCode[i])); 
				bean1.setVoucherHead(String.valueOf(voucherHeadName[i])); 
				bean1.setVamt(Utility.twoDecimals(Double.parseDouble(String
						.valueOf(amount[i])))); // Voucher Amount
				bean1
						.setVrem(removeNewLineCharacter(String
								.valueOf(remark[i]))); // Voucher Remark
				bean1.setVproof(String.valueOf(proof[i]));
				bean1.setUploadFile(String.valueOf(upload[i]));
				total += Double.parseDouble(String.valueOf(amount[i]));
				bean.setTotalamount(testNumberFormat.format(total));
				bean1.setVoucherDetailsFlag("true");
				tableList.add(bean1);
			} 
		} 
		if (remove == 1) {
			/*whether new voucher Head is adding or editing the old one*/
			try {
				total += Double.parseDouble(String.valueOf(bean.getVamount()));

				bean.setVamt(Utility.twoDecimals(Double.parseDouble(bean
						.getVamount())));
				logger.info("bean.getVamount()==" + bean.getVamount());
				logger.info("Utility.twoDecimals(bean.getVamount())=="
						+ Utility.twoDecimals(bean.getVamount()));
				bean.setVrem(removeNewLineCharacter(bean.getVremark()));
				bean.setVchCode(bean.getVchHeadCode());
				bean.setVoucherHead(bean.getVoucherHeadName());
				if (bean.getHproof().equals("N"))
					bean.setVproof("No");
				else
					bean.setVproof("Yes");
				bean.setSrNo(String.valueOf(tableList.size() + 1));
				bean.setUploadFile(bean.getUploadFileName());
				tableList.add(bean);
			} catch (NumberFormatException e) {
				logger.info("exception in addVoucher()" + e);
			}
			// bean.setTotalamount(String.valueOf(total));
			bean.setTotalamount(testNumberFormat.format(total));
			bean.setList(tableList);
		} // end of if
		else if (remove == 0) {
			/* removing particular voucher head from tablelist */
			tableList.remove(Integer.parseInt(bean.getCheckEdit()) - 1);

			/* updating the total amount after removing row */

			total = Double.parseDouble(bean.getTotalamount())
					- Double.parseDouble(bean.getTotalCheck());
			bean.setCheckEdit("");
			// bean.setTotalamount(String.valueOf(total));
			bean.setTotalamount(testNumberFormat.format(total));
		} 
		else {
		}		
		logger.info("tablelist===" + tableList.size() + "& total amount=="
				+ bean.getTotalamount());
		if (tableList.size() != 0)
			bean.setTableLength("1");
		else
			bean.setTableLength("");
		bean.setList(tableList);
	}

	private String removeNewLineCharacter(String sTmpStr) {
		String sResult = null;
		try {
			sResult = sTmpStr.replaceAll("\r\n", " ");
		} catch (Exception e) {
			sResult = "";
		}
		return sResult;
	}

	public void editVoucher(VoucherApplication bean, HttpServletRequest request) {
		/* add the Voucher Details in the Table list */
		Object[] srNo = request.getParameterValues("srNo");
		Object[] voucherHeadCode = request.getParameterValues("vchCode");
		Object[] voucherHeadName = request.getParameterValues("VoucherHead");
		Object[] amount = request.getParameterValues("vamt");
		Object[] remark = request.getParameterValues("vrem");
		Object[] proof = request.getParameterValues("vproof");
		Object[] upload = request.getParameterValues("uploadFile");
		double total = 0.0;
		ArrayList<VoucherApplication> tableList = new ArrayList<VoucherApplication>();
		if (srNo != null){
			/* add the previous list on loading the form */
			VoucherApplication bean1 = null;
			for (int i = 0; i < srNo.length; i++) {
				bean1 = new VoucherApplication();
				if (i == Integer.parseInt(bean.getCheckEdit()) - 1) {
					logger.info("loop no$$$$$$$$$$$$$$$$$$" + i);
					bean1.setVamt(Utility.twoDecimals(bean.getVamount()));
					bean1.setVrem(removeNewLineCharacter(bean.getVremark()));
					bean1.setVchCode(bean.getVchHeadCode());
					bean1.setVoucherHead(bean.getVoucherHeadName());
					bean1.setUploadFile(bean.getUploadFileName());
					bean1.setSrNo(bean.getCheckEdit());
					if (bean.getHproof().equals("N"))
						bean1.setVproof("No");
					else
						bean1.setVproof("Yes");
					total += Double.parseDouble(String.valueOf(bean
							.getVamount()));
				} else {
					bean1.setSrNo(String.valueOf(i + 1)); // Sr No
					bean1.setVchCode(String.valueOf(voucherHeadCode[i])); 
					bean1.setVoucherHead(String.valueOf(voucherHeadName[i])); 
					bean1.setVamt(Utility
							.twoDecimals(String.valueOf(amount[i]))); 
					bean1.setVrem(removeNewLineCharacter(String
							.valueOf(remark[i]))); 
					bean1.setVproof(String.valueOf(proof[i]));
					bean1.setUploadFile(String.valueOf(upload[i]));

					total += Double.parseDouble(String.valueOf(amount[i]));
					bean.setTotalamount(String.valueOf(total));					
					// bean.setList(tableList);
				}
				bean1.setVoucherDetailsFlag("true");
				tableList.add(bean1);
				// tableList.remove(Integer.parseInt(bean.getCheckEdit())-1);
			}
		} // end of if

		try {
			// total += Double.parseDouble(String.valueOf(bean.getVamount()));
			bean.setTotalamount(testNumberFormat.format(total));
			bean.setList(tableList);

		} catch (NumberFormatException e) {
			logger.info("exception in editVoucher()" + e);
		}
		/*bean.setTotalamount(String.valueOf(total));
		 bean.setTotalamount(testNumberFormat.format(tot));
		 bean.setTotalamount(testNumberFormat.format(total));
		 bean.setList(tableList);*/

		logger.info("tablelist===" + tableList.size());
		if (tableList.size() != 0)
			bean.setTableLength("1");
		else
			bean.setTableLength("");
		bean.setList(tableList);

	}

	public String save(HttpServletRequest request, VoucherApplication bean,
			Object[][] emp, Object[] srNo, Object[] vHeadCode, Object[] amount,
			Object[] remark, Object[] proof, Object[] upload,
			String[] keepInformList) {
		boolean result = false;
		String msg = "";
		if ((emp == null) && !(bean.getHiddenStatus().equalsIgnoreCase("D"))) {
			return "empNull";
		}
		Object[][] saveObj = new Object[1][10];
		String query = "SELECT NVL(MAX(VOUCHER_APPL_CODE),0)+1 FROM HRMS_VOUCHER_APPL";
		Object[][] resultCode = getSqlModel().getSingleResult(query);

		saveObj[0][0] = String.valueOf(resultCode[0][0]); // max appl_code
		saveObj[0][1] = bean.getVoucherType(); // voucher type
		saveObj[0][2] = bean.getEmpId(); // employee Id (Selected Employee)
		saveObj[0][3] = bean.getVchDate(); // voucher Date
		saveObj[0][4] = bean.getHiddenStatus(); // Status
		saveObj[0][5] = bean.getTotalamount(); // total amount
		if (emp == null) {
			saveObj[0][6] = ""; // APPROVER OF THE APPLICATION
		} else {
			saveObj[0][6] = String.valueOf(emp[0][0]); // APPROVER 														
		}
		saveObj[0][7] = bean.getDetails(); // Details

		if (emp == null) {
			saveObj[0][8] = "";
		} else {
			saveObj[0][8] = String.valueOf(emp[0][3]); // alternate approver
		}
		saveObj[0][9] = bean.getUserEmpId();
		// Draft By
		result = getSqlModel().singleExecute(getQuery(1), saveObj);
		if (result) {
			bean.setVCode(String.valueOf(resultCode[0][0]));
			saveKeepInformedList(keepInformList,bean);
			result = saveDetails(bean, vHeadCode, amount, remark, proof, upload);
		}
		if (result) {
			msg = "saved";
			ApplCodeTemplateModel model = new ApplCodeTemplateModel();
			model.initiate(context, session);
			String applnCode = model.generateApplicationCode(bean.getVCode(),
					"Cash", bean.getEmpId(), bean.getVchDate());

			logger.info("final appln code in application==" + applnCode);
			getSqlModel().singleExecute(
					"UPDATE HRMS_VOUCHER_APPL SET VOUCHER_NO='" + applnCode
							+ "' WHERE VOUCHER_APPL_CODE=" + bean.getVCode());
			model.terminate();
		} else {
			msg = "notsaved";
		}
		return msg;
	}

	public boolean saveDetails(VoucherApplication voucher, Object[] vHeadCode,
			Object[] amount, Object[] remark, Object[] proof, Object[] upload) {

		boolean result = false;
		Object[][] detailData = new Object[vHeadCode.length][6];
		if (vHeadCode != null) { // check whether data is present in table
									// list
			for (int i = 0; i < vHeadCode.length; i++) {
				detailData[i][0] = voucher.getVCode(); // application code
				logger.info("detailData[" + i + "][0] =" + detailData[i][0]);
				detailData[i][1] = vHeadCode[i]; // voucher Head code
				detailData[i][2] = amount[i]; // voucher amount
				detailData[i][3] = remark[i]; // voucher remark
				if (String.valueOf(proof[i]).equals("No"))
					detailData[i][4] = "N";
				else
					detailData[i][4] = "Y";
				detailData[i][5] = upload[i]; // uploaded file
			} // end of for loop
			result = getSqlModel().singleExecute(getQuery(2), detailData);
		} // end of if
		return result;
	}

	public String modify(HttpServletRequest request, VoucherApplication bean,
			Object[][] emp, Object[] srNo, Object[] vHeadCode, Object[] amount,
			Object[] remark, Object[] proof, Object[] upload, String[] keepInformList) {

		boolean result = false;
		if ((emp == null) && !(bean.getHiddenStatus().equalsIgnoreCase("D"))) {
			return "empNull"; // Reporting Structure is Not defined
		} // end of if
		Object[][] modObj = new Object[1][9];
		modObj[0][0] = bean.getVoucherType(); // type of the voucher
		modObj[0][1] = bean.getEmpId(); // employee code
		modObj[0][2] = bean.getVchDate(); // date
		modObj[0][3] = bean.getHiddenStatus(); // status
		modObj[0][4] = bean.getDetails(); // details
		modObj[0][5] = bean.getTotalamount(); // total amount
		if (emp == null) {
			modObj[0][6] = "";
		} else {
			modObj[0][6] = String.valueOf(emp[0][0]); // Approver
		}
		if (emp == null) {
			modObj[0][7] = "";
		} else {
			modObj[0][7] = String.valueOf(emp[0][3]); // alternate approver
		}
		modObj[0][8] = bean.getVCode(); // application code
		result = getSqlModel().singleExecute(getQuery(8), modObj);
		Object[][] code = new Object[1][1];
		code[0][0] = bean.getVCode();
		if (result) {
			saveKeepInformedList(keepInformList, bean);
			getSqlModel().singleExecute(getQuery(10), code); // Delete details from the HRMS_VCHDTL
			saveDetails(bean, vHeadCode, amount, remark, proof, upload);
			return "modified";
		}
		else
			return "notmodified";
	}
	
	public void getLoginEmployeeDetails(VoucherApplication bean,
			HttpServletRequest request) {
		try {
			String sQuery = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, "
					+ " NVL(RANK_NAME, ' '), "
					+ " NVL(DEPT_NAME, ' '), NVL(CADRE_NAME, ' '), EMP_ID FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) "
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
					+ " WHERE EMP_ID = " + bean.getUserEmpId();
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if ((objData != null) && (objData.length > 0)) {
				bean.setEmpToken(checkNull(String.valueOf(objData[0][0])));
				bean.setEname(checkNull(String.valueOf(objData[0][1])));
				bean.setDesignation(checkNull(String.valueOf(objData[0][2])));
				bean.setDepartment(checkNull(String.valueOf(objData[0][3])));
				bean.setGrade(checkNull(String.valueOf(objData[0][4])));
				bean.setEmpId(checkNull(String.valueOf(objData[0][5])));
			}
		} catch (Exception e) {
			logger
					.error("Error in VoucherApplicationModel.getLoginEmployeeDetails() method Model : "
							+ e.getMessage());
		}
	}

	public void getReport(HttpServletRequest request,
			HttpServletResponse response, VoucherApplication bean) {
		String s = "\nCASH VOUCHER\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String query = "SELECT DECODE(STATUS,'D','Draft','W','Withdraw','P','Pending','B','Send Back','A','Approved','R','Rejected','F','Forward'), APPL_LEVEL FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE="
				+ bean.getVCode();
		Object[][] status = getSqlModel().getSingleResult(query);
		String stat = String.valueOf(status[0][0]);
		if (String.valueOf(status[0][0]).equals("Pending")
				&& !String.valueOf(status[0][1]).equals("1")) {
			stat = "Forwarded";
		} // end of if
		String sql = "SELECT ROWNUM,VCH_NAME,VCHDTL_REMARK,VCHDTL_AMOUNT ,DECODE(PROOF_FLAG,'Y','Yes','N','No')"
				+ " FROM HRMS_VCHDTL INNER JOIN HRMS_VCH_HD ON (HRMS_VCH_HD.VCH_CODE ="
				+ " HRMS_VCHDTL.VCH_CODE) WHERE HRMS_VCHDTL.VOUCHER_APPL_CODE="
				+ bean.getVCode();
		String approver = "SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(APPR_DATE,'DD-MM-YYYY'), "
				+ " VOUCHER_REMARK abc,DECODE(VOUCHER_STATUS,'D','Draft','W','Withdraw','P','Pending','B','Send Back','A','Approved','R','Rejected','F','Forward') FROM HRMS_VOUCHER_PATH"
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
				+ " WHERE VOUCHER_CODE="
				+ bean.getVCode()
				+ " union "
				+ " SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),'',cast(''as nvarchar2(100)) a, "
				+ " DECODE(STATUS,'D','Draft','W','Withdraw','P','Pending','B','Send Back','A','Approved','R','Rejected','F','Forward') FROM  HRMS_VOUCHER_APPL "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.APPROVED_BY= HRMS_EMP_OFFC.EMP_ID) "
				+ " INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
				+ " where STATUS='P'and VOUCHER_APPL_CODE=" + bean.getVCode();

		Object[][] approverData = getSqlModel().getSingleResult(approver);

		Object[][] approvalTable = new Object[approverData.length][7];
		/* get the approver data in an Object to set it into the table*/
		for (int i = 0; i < approverData.length; i++) {
			approvalTable[i][0] = String.valueOf(i + 1);
			approvalTable[i][1] = checkNull(String.valueOf(approverData[i][0]));
			approvalTable[i][2] = checkNull(String.valueOf(approverData[i][1]));
			approvalTable[i][3] = checkNull(String.valueOf(approverData[i][2]));
			approvalTable[i][4] = checkNull(String.valueOf(approverData[i][3]));
			approvalTable[i][5] = checkNull(String.valueOf(approverData[i][4]));
			approvalTable[i][6] = "";
		} // end of for loop
		Object tab[][] = getSqlModel().getSingleResult(sql);
		Object data[][] = new Object[4][4];
		/* Object down[][]=new Object[2][1]; */

		data[0][0] = "Voucher No.";
		data[0][1] = ": " + bean.getVCode();
		data[0][2] = "Employee Id. ";
		data[0][3] = ": " + bean.getEmpToken();

		data[1][0] = "Employee Name ";
		data[1][1] = ": " + bean.getEname();
		data[1][2] = "Department ";
		data[1][3] = ": " + bean.getDepartment();

		data[2][0] = "Designation ";
		data[2][1] = ": " + bean.getDesignation();
		data[2][2] = "Grade ";
		data[2][3] = ": " + bean.getGrade();

		data[3][0] = "Date ";
		data[3][1] = ": " + bean.getVchDate();
		data[3][2] = "Voucher Type ";
		data[3][3] = ": " + bean.getVoucherType();

		data[3][0] = "Status ";
		data[3][1] = ": " + stat;
		data[3][2] = "";
		data[3][3] = "";

		int[] bcellWidth = { 20, 30, 20, 30 };
		int[] bcellAlign = { 0, 0, 0, 0 };
		rg.addFormatedText(s, 6, 0, 1, 0);

		rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);

		int cellwidth[] = { 5, 34, 40, 20, 15 };
		int alignment[] = { 1, 1, 0, 2, 0 };

		Object[][] heading = new Object[1][1];
		int[] cells = { 25 };
		int[] align = { 0 };

		heading[0][0] = "Voucher Details :";
		rg.tableBodyBold(heading, cells, align);
		// rg.addFormatedText("Voucher Details :", 6, 0, 0, 0);
		String colnames[] = { "Sr.No", "Voucher Head", "Particulars", "Amount",
				"Proof Attached" };
		String appCol[] = { "Sr. No", "Approver Id", "Approver Name", "Date",
				"Remarks", "Status", "Signature" };
		int appCell[] = { 5, 10, 25, 10, 30, 10, 12 };
		int apprAlign[] = { 1, 1, 0, 1, 0, 0, 0 };
		rg.tableBody(colnames, tab, cellwidth, alignment);
		Object totObj[][] = new Object[1][5];
		totObj[0][0] = "";
		totObj[0][1] = "";
		totObj[0][2] = "Total Amount";
		totObj[0][3] = "" + bean.getTotalamount();
		totObj[0][4] = "";
		rg.tableBody(totObj, cellwidth, alignment);
		// rg.addFormatedText("Total Amount: " + bean.getTotalamount(), 1, 0, 2,
		// 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("Details:  " + bean.getDetails(), 0, 0, 0, 0);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		heading[0][0] = "Approver Details :";
		rg.tableBodyBold(heading, cells, align);
		// rg.addFormatedText("Approver Details :", 6, 0, 0, 0);
		/*
		 * rg.addFormatedText("", 1, 0, 2, 3); rg.addFormatedText("", 1, 0, 2,
		 * 3); rg.addFormatedText("", 1, 0, 2, 3); rg.addFormatedText("", 1, 0,
		 * 2, 3);
		 */
		rg.tableBody(appCol, approvalTable, appCell, apprAlign);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("__________________", 1, 0, 0, 3);
		rg.addFormatedText("", 1, 0, 0, 3);
		rg.addFormatedText("Employee Signature", 1, 0, 0, 3);

		rg.createReport(response);
	}

	public boolean deleteVoucherApplication(VoucherApplication bean) {
		boolean result = false;
		try {

			if (!(bean.getVCode().equals(""))) {

				/* Details Table data */
				String sQueryDtl = " DELETE FROM HRMS_VCHDTL WHERE VOUCHER_APPL_CODE = "
						+ bean.getVCode();
				result = getSqlModel().singleExecute(sQueryDtl);

				/* Main Table data */
				String sQuery = " DELETE FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE = "
						+ bean.getVCode();
				result = getSqlModel().singleExecute(sQuery);
			} else {
				result = false;
			}
		} catch (Exception e) {
			logger
					.error("Error in VoucherApplicationModel.deleteVoucherApplication() method Model : "
							+ e.getMessage());
			return false;
		}
		return result;
	}

	public boolean withdrawApplication(VoucherApplication bean) {
		boolean result = false;
		try {
			String query = " SELECT STATUS, APPL_LEVEL FROM HRMS_VOUCHER_APPL "
					+ " WHERE APPL_LEVEL=1 AND STATUS = 'P' AND VOUCHER_APPL_CODE = "
					+ bean.getVCode();

			Object obj[][] = getSqlModel().getSingleResult(query);

			if (obj.length > 0) {
				String updateQuery = "UPDATE HRMS_VOUCHER_APPL SET STATUS ='W' "
						+ " WHERE VOUCHER_APPL_CODE = " + bean.getVCode();
				result = getSqlModel().singleExecute(updateQuery);
			} else
				result = false;
		} catch (Exception e) {
			logger
					.error("Error in VoucherApplicationModel.withdrawApplication() method Model : "
							+ e.getMessage());
			result = false;
		}
		return result;
	}

	public void setApprLevelOnceAgain(VoucherApplication bean, Object[][] emp) {
		try {
			String sQuery = " UPDATE HRMS_VOUCHER_APPL SET APPROVED_BY = "
					+ String.valueOf(emp[0][0]) + "," + " APPL_LEVEL = "
					+ String.valueOf(emp[0][2]) + ","
					+ " ALTERNATE_APPROVER = " + String.valueOf(emp[0][3])
					+ " WHERE VOUCHER_APPL_CODE = " + bean.getVCode();

			getSqlModel().singleExecute(sQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setApplication(VoucherApplication bean) {
		Object[] vCode = new Object[1];
		vCode[0] = bean.getVCode();
		Object[][] result = getSqlModel().getSingleResult(getQuery(13), vCode);

		bean.setVoucherType(String.valueOf(result[0][1]));
		bean.setEmpId(String.valueOf(result[0][2]));
		bean.setEname(String.valueOf(result[0][3]));
		bean.setVchDate(String.valueOf(result[0][4]));
		// bean.setStatus(String.valueOf(result[0][5]));
	}
	/** Method name : getRecord purpose : to delete voucher application return
	 * @param bean
	 */
	public void getRecord(VoucherApplication bean) {
		Object[] param = new Object[1];	
		param[0] = bean.getVCode();
		logger.info("voucher code-------" + param[0]);
		Object applData[][] = getSqlModel()
				.getSingleResult(getQuery(12), param);
		if (String.valueOf(applData[0][3]).equals("1")) {
			// bean.setStatus(String.valueOf(applData[0][0]));
			// bean.setHiddenStatus(String.valueOf(applData[0][0]));
		} // end of if
		else if (!(String.valueOf(applData[0][3]).equals("1"))
				&& String.valueOf(applData[0][0]).equals("P")) {
			// bean.setStatus("F");
			// bean.setHiddenStatus("F");
		}
		else {
			bean.setStatus(String.valueOf(applData[0][0]));
			// bean.setHiddenStatus(String.valueOf(applData[0][0]));
		}
		bean.setDetails(checkNull(String.valueOf(applData[0][1]))); // Details
		double tot;
		if (applData[0][2] == null) {
			tot = Double.parseDouble(String.valueOf(0));
		} else {
			tot = Double.parseDouble(String.valueOf(applData[0][2]));
		}
		bean.setTotalamount(testNumberFormat.format(tot).replace(",", ""));
		bean.setLevel(String.valueOf(applData[0][3]));
		bean.setVoucherNo(checkNull(String.valueOf(applData[0][4])));
		bean.setVchDate(checkNull(String.valueOf(applData[0][5])));
		bean.setVoucherType(checkNull(String.valueOf(applData[0][6])));
		
		Object[][] result = getSqlModel().getSingleResult(getQuery(5), param);
		ArrayList<VoucherApplication> tableList = new ArrayList<VoucherApplication>();
		if (!(result.length == 0)) {
			for (int i = 0; i < result.length; i++) {
				VoucherApplication bean1 = new VoucherApplication();

				bean1.setSrNo(String.valueOf(i + 1)); // Sr no.
				bean1.setVchCode(String.valueOf(result[i][0]));// Code
				bean1.setVoucherHead((String.valueOf(result[i][1])));
				bean1.setVamt(String.valueOf(result[i][2])); 
				bean1.setVrem(checkNull(String.valueOf(result[i][3]))); 
				if (String.valueOf(result[i][4]).equalsIgnoreCase("N"))
					bean1.setVproof("No");
				else
					bean1.setVproof("Yes");
				bean1.setUploadFile(checkNull(String.valueOf(result[i][5])));
				bean1.setVoucherDetailsFlag(bean.getVoucherDetailsFlag());
				bean1.setEditFlag1(bean.getEditFlag1());
				System.out.println("bean1.getVoucherDetailsFlag()--"+bean1.getVoucherDetailsFlag());
				tableList.add(bean1);
			}
			bean.setTableLength("1");
			bean.setList(tableList);
		}
	}
	/**
	 * Method name : showEmp purpose : to show the employee details on the
	 * @param cvoucher
	 */
	public void showEmp(VoucherApplication cvoucher) {
		Object[] emp = new Object[1];
		emp[0] = cvoucher.getEmpId();
		Object[][] empdata = getSqlModel().getSingleResult(getQuery(9), emp);
		cvoucher.setEmpId(String.valueOf(empdata[0][0]));
		cvoucher.setEname(String.valueOf(empdata[0][1])); // Employee Name
		cvoucher.setDesignation(String.valueOf(empdata[0][2])); // Designation
		cvoucher.setDepartment(String.valueOf(empdata[0][3])); // Dept
		cvoucher.setGrade(String.valueOf(empdata[0][4])); // Grade
		cvoucher.setEmpToken(String.valueOf(empdata[0][5]));
	}

	/** Method Name : setApprover purpose : set the previous approver details
	 * @param bean
	 * @return
	 */
	public boolean setApprover(VoucherApplication bean) {
		boolean result= false;
		String query = "SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(APPR_DATE,'DD-MM-YYYY'),NVL(VOUCHER_REMARK,' '), "
				+ " DECODE(VOUCHER_STATUS,'D','Draft','P','Pending','B','Send Back','A','Approved','R','Rejected','F','Forward','W','Withdraw')"
				+ " FROM HRMS_VOUCHER_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
				+ " WHERE VOUCHER_CODE="
				+ bean.getVCode()
				+ " ORDER BY APPR_DATE";
		Object apprData[][] = getSqlModel().getSingleResult(query);
		ArrayList<VoucherApplication> apprList = new ArrayList<VoucherApplication>();
		logger.info("apprData is   " + apprData.length);
		try {
			if (apprData != null && apprData.length != 0) {
				result= true;
				for (int i = 0; i < apprData.length; i++) {
					VoucherApplication bean1 = new VoucherApplication();
					bean1.setAppSrNo(String.valueOf(i + 1));
					bean1.setPrevApproverID((String.valueOf(apprData[i][0])));
					bean1.setPrevApproverName(String.valueOf(apprData[i][1]));
					bean1.setPrevApproverDate(String.valueOf(apprData[i][2]));
					bean1.setPrevApproverComment(String.valueOf(apprData[i][3]));
					bean1.setPrevApproverStatus(String.valueOf(apprData[i][4]));
					apprList.add(bean1);
				} 
			} 
			bean.setApproverCommentList(apprList);
		} catch (Exception e) {
			logger.info("exception in setApprover()" + e);
		}
		return result;
	}

	public String checkWithdrawIsPossible(VoucherApplication bean) {
		String bResult = "N";
		try {
			String sQuery = " SELECT APPL_LEVEL FROM HRMS_VOUCHER_APPL WHERE APPL_LEVEL > 1 "
					+ " AND STATUS = 'P' AND VOUCHER_APPL_CODE = "
					+ bean.getVCode();

			Object apprData[][] = getSqlModel().getSingleResult(sQuery);
			if (apprData != null && apprData.length != 0) {
				bResult = "Y";
			} else {
				bResult = "N";
			}

		} catch (Exception e) {
			logger.info("exception in setApprover()" + e);
		}
		return bResult;
	}

	public static String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public void setMainApproverData(VoucherApplication cvoucher,
			Object[][] empFlow) {
		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {
					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";
					// + " WHERE EMP_TOKEN IN('" + empFlow[i][0] + "')";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList <VoucherApplication> arrList = new ArrayList <VoucherApplication>();
					for (int i = 0; i < approverDataObj.length; i++) {
						VoucherApplication cvBean = new VoucherApplication();
						cvBean.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						cvBean.setSrNoIt(srNo);
						arrList.add(cvBean);
					}
					cvoucher.setApproverList(arrList);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}
	}

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

	/*public void keepInfEmployee(VoucherApplication bean) {
		String qry = " SELECT NVL(STATUS,'P'), NVL(VCH_DETAILS,''),VCH_TOTALAMT ,APPL_LEVEL,NVL(VOUCHER_NO,''),NVL(TO_CHAR(APP_DATE,'DD-MM-YYYY'),''),VOUCHER_KEEP_INFORM  FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE="
				+ bean.getVCode();
		Object[][] applData = getSqlModel().getSingleResult(qry);
		if (applData[0][6] != null) {
			String vchKiEmpId = String.valueOf(applData[0][6]);
			String[] strKiEmpId = vchKiEmpId.split(",");
			Object[][] kiEmpDetails = null;
			ArrayList<VoucherApplication> cvList = new ArrayList<VoucherApplication>();
			if (strKiEmpId != null) {
				for (int i = 0; i < strKiEmpId.length; i++) {
					String qryEmpDetails = "SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID="
							+ strKiEmpId[i];
					kiEmpDetails = getSqlModel().getSingleResult(qryEmpDetails);
					if (kiEmpDetails != null) {
						VoucherApplication cvApp = new VoucherApplication();
						cvApp.setKeepInformedEmpCodeIt(String
								.valueOf(kiEmpDetails[0][0]));
						cvApp.setKeepInformedEmpIdIt(String
								.valueOf(kiEmpDetails[0][1]));
						cvApp.setKeepInformedEmpNameIt(String
								.valueOf(kiEmpDetails[0][2]));
						cvApp.setSerialNoIt(String.valueOf(i));// sr no
						cvList.add(cvApp);
						bean.setKeepInformedList(cvList);
					}
				}// for close
			}
			System.out.println("---------SIZE----------" + cvList.size());
		}// if close

	}
*/
	public void loadVchReamrk(VoucherApplication cvoucher) {
		try {
			String pathQuery = " SELECT NVL(VOUCHER_REMARK,' ') FROM HRMS_VOUCHER_PATH "
					+ " WHERE APPROVER_CODE="
					+ cvoucher.getUserEmpId()
					+ " AND VOUCHER_CODE= ?";
			Object[] paramObj = new Object[1];
			paramObj[0] = cvoucher.getVCode();
			Object[][] data = getSqlModel()
					.getSingleResult(pathQuery, paramObj);
			if (data == null || data.length == 0 || data.equals(null)) {
				cvoucher.setVchRemark("");
			} else {

			}
			String status = cvoucher.getStatus();
			if (status.equals("P")) {
				cvoucher.setApprflag("false");
			}
			if (status.equals("A")) {
				cvoucher.setApprflag("true");
			}
			if (status.equals("R")) {
				cvoucher.setApprflag("true");
			}
			if (status.equals("F")) {
				cvoucher.setApprflag("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkFlag(VoucherApplication bean) {
		String query = "SELECT HRMS_ADMIN_CONF.CONF_CASH_MGR_FLAG, "
				+ " HRMS_ADMIN_CONF.CONF_CASH_ADMIN_FLAG, "
				+ " HRMS_ADMIN_CONF.CONF_CASH_ACC_FLAG FROM HRMS_ADMIN_CONF";
		Object[][] outputObj = getSqlModel().getSingleResult(query);
		if (outputObj != null && outputObj.length > 0) {
			bean.setVoucherMgrFlg(String.valueOf(outputObj[0][0]));
			bean.setVoucherAdminFlg(String.valueOf(outputObj[0][1]));
			bean.setVoucherAccFlg(String.valueOf(outputObj[0][2]));
		} else {
			bean.setVoucherMgrFlg("");
			bean.setVoucherAdminFlg("");
			bean.setVoucherAccFlg("");
		}

	}

	public void sendProcessManagerAlert(HttpServletRequest request, String appCode, String applicant,
			String approver, Object[][] empFlow, Object[] keepInformList,
			String voucherMgrFlg, String voucherAdminFlg, String voucherAccFlg,
			String string) {
		try {
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(appCode, "Voucher");
			processAlerts.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String level = "1";
		String link  ="";
		String linkParam="";

		EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);
		template.setEmailTemplate("VOUCHER - APPLICANT TO APPROVER");			
		template.getTemplateQueries();
		
		try {			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			templateQuery1.setParameter(1, applicant);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, approver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, appCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicant);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, appCode);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		try {
			template.configMailAlert();

		} catch (Exception e) {
			e.printStackTrace();
		}		
		if(voucherMgrFlg.equals("Y") || voucherAdminFlg.equals("Y")){		
			link = "/voucher/VoucherApplication_retriveForApproval.action";
			linkParam = "voucherCode="+appCode+"&status=P";
			}else{				
			}
		try {
			template.sendProcessManagerAlert(approver, "Voucher", "A",
					appCode, level, linkParam, link, "Pending",
					applicant, "", "", "",applicant);		
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmailTemplateBody templateApp = new EmailTemplateBody ();
		templateApp.initiate(context, session);
		
		templateApp.setEmailTemplate("VOUCHER - EMAIL TO APPLICANT");
		templateApp.getTemplateQueries();
		
		EmailTemplateQuery templateQuery1 =  templateApp.getTemplateQuery(1);
		
		
		EmailTemplateQuery templateQuery2 = templateApp.getTemplateQuery(2);
		templateQuery2.setParameter(1, applicant);
		
		EmailTemplateQuery templateQuery3 = templateApp.getTemplateQuery(3);
		templateQuery3.setParameter(1, appCode);
		
		EmailTemplateQuery templateQuery4 = templateApp.getTemplateQuery(4);
		templateQuery4.setParameter(1, appCode);
		
		try {
			templateApp.configMailAlert();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String [] keep =request.getParameterValues("keepInformedEmpCode");
		String keepInformTo = "";
		if (keepInformList != null) {
			for (int i = 0; i < keepInformList.length; i++) {
				if (i == keepInformList.length - 1) {
					keepInformTo += String.valueOf(keepInformList[i]);
				} else {
					keepInformTo += String.valueOf(keepInformList[i]) + ",";
				}
			}
		}			
		link = "/voucher/VoucherApplication_callforedit.action";
		linkParam = "voucherCode=" + appCode + "&voucherStatus=P";

		try {
			templateApp.sendProcessManagerAlert("", "Voucher", "I", appCode,
					level, linkParam, link, "Pending", applicant, "",
					keepInformTo, applicant, applicant);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(keep != null ){
				templateApp.sendApplicationMailToKeepInfo(keep);
			}
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void setKeepInformTo(VoucherApplication bean, String[] serialNo,
			String[] empID, String[] empToken, String[] empName) {
		try {
			VoucherApplication voucherBean = new VoucherApplication();
			voucherBean.setKeepInformedEmpCode(bean.getKiEmpCode());
			voucherBean.setKeepInformedEmpId(bean.getKiEmpToken());
			voucherBean.setKeepInformedEmpName(bean.getKiEmpName());
			ArrayList <VoucherApplication> voucherKeepList = new ArrayList<VoucherApplication>();
			voucherKeepList= displayNewValueForKeepInformed(serialNo, empID,empToken, empName, bean);
			voucherBean.setSerialNo(String.valueOf(voucherKeepList.size() + 1));
			voucherBean.setVoucherDetailsFlag("true");
			voucherKeepList.add(voucherBean);
			bean.setKeepInformedList(voucherKeepList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private ArrayList<VoucherApplication> displayNewValueForKeepInformed(
			String[] serialNo, String[] empID, String[] empToken,
			String[] empName, VoucherApplication bean) {
		ArrayList <VoucherApplication> keepList = new ArrayList<VoucherApplication>();
		try {			
			if(serialNo != null){
				for(int i=0;i<serialNo.length;i++){
					VoucherApplication vBean = new VoucherApplication();
					vBean.setKeepInformedEmpCode(empID[i]);
					vBean.setKeepInformedEmpId(empToken[i]);
					vBean.setKeepInformedEmpName(empName[i]);
					vBean.setVoucherDetailsFlag("true");
					keepList.add(vBean);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keepList;
	}

	public void displayIteratorOfKeep(VoucherApplication bean,
			String[] serialNo, String[] empID, String[] empToken,
			String[] empName) {
		ArrayList<VoucherApplication> kList = new ArrayList <VoucherApplication>();
		try {
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					VoucherApplication vBean = new VoucherApplication();
					vBean.setKeepInformedEmpCode(empID[i]);
					vBean.setKeepInformedEmpId(empToken[i]);
					vBean.setKeepInformedEmpName(empName[i]);
					vBean.setSerialNo(serialNo[i]);
					vBean.setVoucherDetailsFlag("true");
					kList.add(vBean);
				}
				bean.setKeepInformedList(kList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean getKeepInformedRecord(VoucherApplication bean) {
		boolean keepResult= false;
		try {
			String keepQuery = " SELECT VOUCHER_KEEP_INFORM FROM HRMS_VOUCHER_APPL "
					+ " WHERE VOUCHER_APPL_CODE="
					+ bean.getVCode()
					+ " AND EMP_CODE=" + bean.getEmpId();
			Object[][] objKeep = getSqlModel().getSingleResult(keepQuery);
			String empId = "";
			if (objKeep != null && objKeep.length > 0) {
				empId = String.valueOf(objKeep[0][0]);
				if (empId.length() > 0) {
					String keepNameDetail = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
							+ " EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC WHERE  EMP_ID IN("
							+ empId + ")";
					Object[][] outParam = getSqlModel().getSingleResult(
							keepNameDetail);
					ArrayList<VoucherApplication> keepList = new ArrayList<VoucherApplication>();
					if (outParam != null) {
						for (int i = 0; i < outParam.length; i++) {
							VoucherApplication vBean = new VoucherApplication();
							vBean.setKeepInformedEmpCode(String
									.valueOf(outParam[i][1]));
							if(vBean.getKeepInformedEmpCode().equals(bean.getUserEmpId()))
							{
								keepResult=true;
							}
							vBean.setKeepInformedEmpId(String
									.valueOf(outParam[i][2]));
							vBean.setKeepInformedEmpName(String
									.valueOf(outParam[i][0]));
							vBean.setVoucherDetailsFlag(bean.getVoucherDetailsFlag());
							keepList.add(vBean);
						}
						bean.setKeepInformedList(keepList);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return keepResult;
	}

	public boolean deleteVoucherDetails(VoucherApplication bean,
			HttpServletRequest request) {
		Object addObj[][] = new Object[1][1];
		boolean result=false;
		try {
			addObj[0][0] = request.getParameter("vCode");			
			result = getSqlModel().singleExecute(getQuery(10), addObj);
			if (result) {
				 getSqlModel().singleExecute(getQuery(3), addObj);
			}
			if (result) {
				result = getSqlModel().singleExecute(getQuery(15), addObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void saveKeepInformedList(String empCode[], VoucherApplication bean) {

		try {
			// SAVE KEEP INFORMED TO EMPCODES AS COMMA SEPARATED VALUES.
			String empId = "";
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					if (i < empCode.length - 1) {
						empId += empCode[i] + ",";
					} else {
						empId = empId + empCode[i];
					}
				}
			}
			String updateQuery = "  UPDATE HRMS_VOUCHER_APPL SET "
					+ "  VOUCHER_KEEP_INFORM=?  WHERE VOUCHER_APPL_CODE=? AND EMP_CODE=? ";
			Object updateObj[][] = new Object[1][3];
			updateObj[0][0] = empId;
			updateObj[0][1] = bean.getVCode();
			updateObj[0][2] = bean.getEmpId();
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Exception in saveKeepInformedList method : " + e);
		}

	}
	
	public void checked(HttpServletRequest request, VoucherApplication bean) {
		try {
			Object[] srNo = request.getParameterValues("srNo");
			Object[] voucherHeadCode = request.getParameterValues("vchCode");
			Object[] voucherHeadName = request.getParameterValues("VoucherHead");
			Object[] amount = request.getParameterValues("vamt");
			Object[] remark = request.getParameterValues("vrem");
			Object[] proof = request.getParameterValues("vproof");
			Object[] upload = request.getParameterValues("uploadFile");
			double total = 0.0;			
			ArrayList<VoucherApplication> tableList = new ArrayList<VoucherApplication>();
			if (srNo != null){
				for (int i = 0; i < srNo.length; i++) {
					VoucherApplication bean1 = new VoucherApplication();
					bean1.setSrNo(String.valueOf(i + 1)); // Sr No					
					bean1.setVchCode(String.valueOf(voucherHeadCode[i])); 
					bean1.setVoucherHead(String.valueOf(voucherHeadName[i])); 
					bean1.setVamt(Utility.twoDecimals(Double.parseDouble(String
							.valueOf(amount[i])))); // Voucher Amount
					bean1
							.setVrem(removeNewLineCharacter(String
									.valueOf(remark[i]))); // Voucher Remark
					bean1.setVproof(String.valueOf(proof[i]));
					bean1.setUploadFile(String.valueOf(upload[i]));
					total += Double.parseDouble(String.valueOf(amount[i]));
					bean.setTotalamount(testNumberFormat.format(total));
					bean1.setVoucherDetailsFlag("true");
					tableList.add(bean1);
				} 
				bean.setList(tableList);
			} 
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empToken, String[] empName, VoucherApplication bean,
			String removeEmpId, String vchCode) {
		try{
			ArrayList<VoucherApplication> tableList = new ArrayList<VoucherApplication>();
			String qry="SELECT VOUCHER_KEEP_INFORM FROM  HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE="+vchCode;
			Object[][] keepInfEmpIdObj=getSqlModel().getSingleResult(qry); 
			if(keepInfEmpIdObj!=null){
				String strKeepInfEmpId=String.valueOf(keepInfEmpIdObj[0][0]);
				String[] splitEmpId= strKeepInfEmpId.split(",");
				String employeeId="";
				for(int i=0;i<splitEmpId.length;i++){
					if(!splitEmpId[i].equals(removeEmpId)){
						if(employeeId.equals("")){
							employeeId=String.valueOf(splitEmpId[i]);
						}else{
							employeeId=employeeId+","+String.valueOf(splitEmpId[i]);
						}
					}
				}
				String upateQry="UPDATE  HRMS_VOUCHER_APPL SET  VOUCHER_KEEP_INFORM=' "+employeeId+"' WHERE VOUCHER_APPL_CODE= "+bean.getVCode();
				getSqlModel().singleExecute(upateQry); 
			}
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {
					VoucherApplication bean1 = new VoucherApplication();
					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setKeepInformedEmpCode(empCode[i]);
					bean1.setKeepInformedEmpId(empToken[i]);
					bean1.setKeepInformedEmpName(empName[i]);
					bean1.setVoucherDetailsFlag(bean.getVoucherDetailsFlag());
					tableList.add(bean1);
				}				
				tableList.remove(Integer.parseInt(bean.getCheckRemove()) - 1);
			}
			bean.setKeepInformedList(tableList);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}

