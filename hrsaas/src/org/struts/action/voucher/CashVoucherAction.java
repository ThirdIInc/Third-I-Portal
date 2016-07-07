package org.struts.action.voucher;

import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.paradyne.bean.voucher.CashVoucherMaster;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.model.voucher.CashVoucherModel;
import org.struts.lib.ParaActionSupport;

public class CashVoucherAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CashVoucherAction.class);
	CashVoucherMaster cvoucher;
	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
	
	public void setCvoucher(CashVoucherMaster cvoucher) {
		this.cvoucher = cvoucher;
	}
	
	public CashVoucherMaster getCvoucher() {
		return cvoucher;
	}

	public Object getModel() {
		return cvoucher;
	}

	public void prepare_local() throws Exception {
		this.cvoucher = new CashVoucherMaster();
		cvoucher.setMenuCode(372);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		model.AdminMgrAccountAppr(cvoucher);
		if (cvoucher.isGeneralFlag()) {
			model.getEmployeeDetails(cvoucher.getUserEmpId(), cvoucher);
		}
		model.terminate();
	}
	public String input() {
		try {
			getNavigationPanel(1);
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(cvoucher, request,cvoucher.getUserEmpId());  // getting all types of application
			cvoucher.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in input--------" + e);
		}
		return SUCCESS;
	}
	
	public String addItem() {
		getNavigationPanel(2);
		Object[] srNo = request.getParameterValues("srNo");
		Object[] voucherHeadCode = request.getParameterValues("vchCode");
		Object[] voucherHeadName = request.getParameterValues("VoucherHead");
		Object[] amount = request.getParameterValues("vamt");
		Object[] remark = request.getParameterValues("vrem");
		Object[] proof  = request.getParameterValues("vproof");
		Object[] upload = request.getParameterValues("uploadFile");
		
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		if (cvoucher.getCheckEdit().equals("")) {
			model.addVoucher(cvoucher, srNo, voucherHeadCode, voucherHeadName, amount, remark, 1, proof, upload);
		} else {
			model.editVoucher(cvoucher, srNo, voucherHeadCode, voucherHeadName, amount, remark, proof, upload);
			
		}
		clear();
		//Set Approver Employee
		setApproverList();
		//Get keep Inform Employees 
		cvoucher.setKiEmpName("");
		cvoucher.setKiEmpCode("");
		cvoucher.setKiEmpToken("");
		String[] serialNo = request.getParameterValues("serialNo"); // serial
		String[] empToken = request.getParameterValues("keepInformedEmpId");// keep
		String[] empName = request.getParameterValues("keepInformedEmpName");// keep informed
		String[] empCode = request.getParameterValues("keepInformedEmpCode");// keep
		model.displayIteratorValueForKeepInformed(serialNo, empCode,empToken,empName, cvoucher);
		cvoucher.setVoucherDetailsFlag("true");
		//DVoucher Details
		setStatus();
		model.terminate();
		return "addCashVoucherApp";
	}

	public void clear() {
		/*
		 * Clear the fields used for filling the Voucher details i.e Voucher Head,amount,remark 
		 */
		cvoucher.setVchHeadCode("");
		cvoucher.setVoucherHeadName("");
		cvoucher.setVamount("");
		cvoucher.setVremark("");
		cvoucher.setCheckEdit("");
		cvoucher.setHproof("");
		cvoucher.setUploadFileName("");
	}

	public String delete() throws Exception {
		try {
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			//boolean result = model.deleteVoucherPara(cvoucher);
			boolean result = model.cancelRecord(cvoucher);
			if (result) {
				System.out.println("RESULT==" + result);
				addActionMessage("Record canceled successfully.");
				model.getAllTypeOfApplications(cvoucher, request, cvoucher
						.getUserEmpId()); // getting all types of application
				cvoucher.setListType("pending");
				//------------------------Process Manager Alert------------------------start
			
				//------------------------Process Manager Alert------------------------end
				reset();
			} else {
				addActionMessage("Record can't be canceled.");
			}
			getNavigationPanel(1);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public void deleteProcessManagerAlert(String moduleName,
			String applicationCode) {
		try {
			LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ applicationCode
					+ " AND UPPER(ALERT_MODULE) LIKE '"
					+ moduleName.trim().toUpperCase() + "'";
			model.getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String f9action() throws Exception {
		String query = " SELECT VOUCHER_TYPE, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(APP_DATE,'DD-MM-YYYY'), " +
		" VCH_TOTALAMT, CASE WHEN STATUS = 'P' AND APPL_LEVEL != 1 THEN 'Forwarded' WHEN STATUS = 'P' THEN 'Pending' " +
		" WHEN STATUS = 'A' THEN 'Approved' WHEN STATUS = 'R' THEN 'Rejected' WHEN STATUS = 'C' THEN 'Canceled' ELSE '' END, EMP_CODE, VOUCHER_APPL_CODE, APPROVED_BY " +
		" FROM HRMS_VOUCHER_APPL " +
		" INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) " +
		" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)";
		if (cvoucher.isGeneralFlag()) {
			query += " WHERE EMP_CODE = " + cvoucher.getUserEmpId();
		}
		query += " ORDER BY APP_DATE DESC ";
		
		String[] headers = { getMessage("vouchertype"), getMessage("employee"), getMessage("date"), getMessage("Total.Amount"),getMessage("status") };

		String[] headerWidth = { "20", "30", "15", "15","20" };

		String[] fieldNames = { "type", "ename", "vchDate", "totalamount", "hiddenStatus",	"empCode", "vCode","approverCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7};

		String submitFlag = "true";

		String submitToMethod = "CashVoucher_retrive.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionEname() throws Exception {
		/*
		 * Select the Employee F9
		 */
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, NVL(RANK_NAME, ' '), " +
		" NVL(DEPT_NAME, ' '), NVL(CADRE_NAME, ' '), EMP_ID FROM HRMS_EMP_OFFC " +
		" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) " +
		" LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) " +
		" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " +
		" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) " +
		" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " +
		" WHERE EMP_CENTER = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = " + cvoucher.getUserEmpId() + " ) " + 
		" AND EMP_STATUS = 'S' ORDER BY EMP_ID ";

		String[] headers = {getMessage("employee.id"), getMessage("employee"), getMessage("designation"), getMessage("department"), getMessage("grade") };

		String[] headerWidth = {"10", "30", "20", "20", "20"};

		String[] fieldNames = {"empToken", "ename", "designation", "department", "grade", "empCode"};

		int[] columnIndex = {0, 1, 2, 3, 4, 5};

		String submitFlag = "true";

		String submitToMethod = "CashVoucher_setEmpDetails.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionhd() throws Exception {
		/*
		 * Select the Voucher Head from Voucher Head master F9
		 */

		String query = " SELECT VCH_CODE, VCH_NAME FROM HRMS_VCH_HD ORDER BY VCH_CODE ";

		String[] headers = {"Voucher Code", "Voucher Name"};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"vchHeadCode", "VoucherHeadName"};

		int[] columnIndex = {0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	

	public String remove() {
		Object[] srNo = request.getParameterValues("srNo");
		Object[] voucherHeadCode = request.getParameterValues("vchCode");
		Object[] voucherHeadName = request.getParameterValues("VoucherHead");
		Object[] amount = request.getParameterValues("vamt");
		Object[] remark = request.getParameterValues("vrem");
		Object[] proof = request.getParameterValues("vproof");
		Object[] upload = request.getParameterValues("uploadFile");
		cvoucher.setVoucherDetailsFlag("true");
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		model.addVoucher(cvoucher, srNo, voucherHeadCode, voucherHeadName, amount, remark, 0, proof, upload);
		model.terminate();
		getNavigationPanel(7);
		return "addCashVoucherApp";
	}
	
	

	public String report() {
		/*
		 * Generate the report for particular application
		 */
		System.out.println("STATUS==="+cvoucher.getStatus());
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		model.getReport(request, response, cvoucher);
		model.terminate();
		return null;
	}

	public String reset() {
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		cvoucher.setVCode("");
		cvoucher.setType("");
		
		if (!cvoucher.isGeneralFlag()) { // check weather the employee is General or Admin
			cvoucher.setEname("");
			cvoucher.setDepartment("");
			cvoucher.setDesignation("");
			cvoucher.setGrade("");
			cvoucher.setEmpToken("");
		}
		cvoucher.setVrem("");
		cvoucher.setVoucherHead("");
		cvoucher.setVamt("");
		cvoucher.setStatus("");
		cvoucher.setTotalamount("");
		cvoucher.setDetails("");
		cvoucher.setVchCode("");
		cvoucher.setChkfield("");
		cvoucher.setVamount("");
		cvoucher.setVchHeadCode("");
		cvoucher.setVoucherHeadName("");
		cvoucher.setVremark("");
		cvoucher.setHproof("");
		cvoucher.setCheckEdit("");
		cvoucher.setTableLength("");
		cvoucher.setUploadFileName("");
		cvoucher.setVoucherDetailsFlag("true");
		model.terminate();
		getNavigationPanel(2);
		return "addCashVoucherApp";
		//return SUCCESS;
	}

	public String retrive() { //Retrive data on f9 action
		try {
		 	String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			cvoucher.setSource(source);
			CashVoucherModel cashModel = new CashVoucherModel();
			cashModel.initiate(context, session);
			cashModel.showEmp(cvoucher, request);
			
			String appCode = request.getParameter("vCode");
			System.out.println("appCode ----------------------------------------- "+appCode);
			cashModel.getRecord(cvoucher, appCode,request);
			setApproverList();
			//clear();
			cvoucher.setViewAppFlag("true");
			String status = request.getParameter("cvStatus");			
			System.out.println("+status              "+status);
			if (status.equals("D")) {
				getNavigationPanel(7);
				cvoucher.setEditFlag1("false");
				cvoucher.setVoucherDetailsFlag("true");
			} else if (status.equals("P")) {
				getNavigationPanel(6);
				cvoucher.setEditFlag1("true");
				cvoucher.setApprovalFlag("false");
				cvoucher.setVoucherDetailsFlag("false");
				cvoucher.setShowFlag("false");
			} else if (status.equals("A")) {
				cvoucher.setVoucherDetailsFlag("false");
				cvoucher.setEditFlag1("true");
				cvoucher.setPrevAppCommentListFlag("true");
				getNavigationPanel(6);
				cvoucher.setShowFlag("false");
			} else if (status.equals("R")) {
				cvoucher.setVoucherDetailsFlag("false");
				cvoucher.setEditFlag1("true");
				cvoucher.setPrevAppCommentListFlag("true");
				getNavigationPanel(6);
				cvoucher.setShowFlag("false");
			} else if (status.equals("B")) {
				getNavigationPanel(7);
				cvoucher.setPrevAppCommentListFlag("true");
				cvoucher.setVoucherDetailsFlag("true");
				cvoucher.setEditFlag1("false");
			}
			cashModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//getNavigationPanel(4);
		return "addCashVoucherApp";
	}

	public String retriveApplication() {
		try {
			CashVoucherModel cashModel = new CashVoucherModel();
			cashModel.initiate(context, session);
			
			String alertApplnID = request.getParameter("alertApplnID");
			
			Object[][] voucherApplnObj = cashModel.getVoucherApplication(alertApplnID);
			
			cvoucher.setEmpCode(String.valueOf(voucherApplnObj[0][0]));
			cvoucher.setEname(String.valueOf(voucherApplnObj[0][1]));
			cvoucher.setDesignation(cashModel.checkNull(String.valueOf(voucherApplnObj[0][2])));
			cvoucher.setDepartment(cashModel.checkNull(String.valueOf(voucherApplnObj[0][3])));
			cvoucher.setGrade(cashModel.checkNull(String.valueOf(voucherApplnObj[0][4])));
			cvoucher.setEmpToken(String.valueOf(voucherApplnObj[0][5]));
			
			if (String.valueOf(voucherApplnObj[0][9]).equals("1")) {
				cvoucher.setStatus(String.valueOf(voucherApplnObj[0][6]));
				cvoucher.setHiddenStatus(String.valueOf(voucherApplnObj[0][6]));
			} else if (!(String.valueOf(voucherApplnObj[0][9]).equals("1")) && String.valueOf(voucherApplnObj[0][6]).equals("P")) {
				cvoucher.setStatus("F");
				cvoucher.setHiddenStatus("F");
			} else {
				cvoucher.setStatus(String.valueOf(voucherApplnObj[0][6]));
				cvoucher.setHiddenStatus(String.valueOf(voucherApplnObj[0][6]));
			}
			
			cvoucher.setDetails(cashModel.checkNull(String.valueOf(voucherApplnObj[0][7])));
			
			double total = Double.parseDouble(String.valueOf(voucherApplnObj[0][8]));
			cvoucher.setTotalamount(testNumberFormat.format(total).replace(",",""));
			cvoucher.setLevel(String.valueOf(voucherApplnObj[0][9]));
			Object[][] voucherDetailsObj = cashModel.getVoucherDetails(alertApplnID);
			
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (!(voucherDetailsObj.length == 0)) {
				for (int i = 0; i < voucherDetailsObj.length; i++) {
					CashVoucherMaster bean1 = new CashVoucherMaster();

					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setVchCode(alertApplnID);
					bean1.setVoucherHead((String.valueOf(voucherDetailsObj[i][0])));
					bean1.setVamt(String.valueOf(voucherDetailsObj[i][1]));
					bean1.setVrem(cashModel.checkNull(String.valueOf(voucherDetailsObj[i][2])));
					
					if (String.valueOf(voucherDetailsObj[i][3]).equalsIgnoreCase("N")) {
						bean1.setVproof("No");
					} else {
						bean1.setVproof("Yes");
					}
					bean1.setUploadFile(cashModel.checkNull(String.valueOf(voucherDetailsObj[i][4])));
					
					tableList.add(bean1);
				}

				cvoucher.setTableLength("1");
				cvoucher.setList(tableList);
			}			
			cashModel.terminate();
			return "success";			
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String retriveForApproval() {
		try {
			String source= request.getParameter("src");
			cvoucher.setSource(source);
			CashVoucherModel cashModel = new CashVoucherModel();
			cashModel.initiate(context, session);
			String voucherCode = request.getParameter("voucherNoView");
			cvoucher.setVCode(voucherCode);
			cashModel.setApplication(cvoucher);
			//updated By Anantha
			cashModel.getRecord(cvoucher, cvoucher.getVCode(), request);
			cashModel.showEmp(cvoucher, request);
			cashModel.setApprover(cvoucher);
			
			cvoucher.setIsApprove("true");
			
			clear();
			cashModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(9);
		return "addCashVoucherApp";
	}

	public String save() {
		try {
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			String flag = "";
			String status = request.getParameter("checkStatus");
			String[] proofName = request.getParameterValues("proofName");
			Object[][] empFlow = null;
			String module = "Voucher";
			Object[] srNo = request.getParameterValues("srNo");
			Object[] voucherHeadCode = request.getParameterValues("vchCode");
			Object[] voucherHeadName = request
					.getParameterValues("VoucherHead");
			Object[] amount = request.getParameterValues("vamt");
			Object[] remark = request.getParameterValues("vrem");
			Object[] proof = request.getParameterValues("vproof");
			Object[] upload = request.getParameterValues("uploadFile");
			String[] kiEmpCodeIt = request
					.getParameterValues("keepInformedEmpCode");
			String strKiEmpCodeIt = "";
			if (kiEmpCodeIt != null) {
				for (int cnt = 0; cnt < kiEmpCodeIt.length; cnt++) {
					if (cnt == 0) {
						strKiEmpCodeIt = String.valueOf(kiEmpCodeIt[cnt]);
					} else {
						strKiEmpCodeIt = strKiEmpCodeIt + ","
								+ String.valueOf(kiEmpCodeIt[cnt]);
					}
				}
			}
			if (cvoucher.getVoucherMgrFlag().equals("Y")) {//helpdesk.getIsManagerApproval()
				empFlow = generateEmpFlow(cvoucher.getEmpCode(), "Cash", 1);
			} else if(cvoucher.getVoucherAdminFlag().equals("Y")){
				String ownerQuery = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT "+
						"EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+cvoucher.getEmpCode()+")	AND CONF_TYPE='CashAdmin'";
				empFlow = model.getSqlModel().getSingleResult(ownerQuery);
			}else if(cvoucher.getVoucherAcctFlag().equals("Y")){
				String ownerQuery =  "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT "+
				"EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+cvoucher.getEmpCode()+")	AND CONF_TYPE='CashAccount'";
			empFlow = model.getSqlModel().getSingleResult(ownerQuery);
			}
			if (empFlow != null && empFlow.length > 0) {
				if (cvoucher.getVCode().equals("")) {
						try{
							// IF CODE IS BLANK, SAVE RECORD
							flag = model.save(cvoucher, empFlow, voucherHeadCode,
									amount, remark, proof, upload, strKiEmpCodeIt,cvoucher.getVoucherMgrFlag(),cvoucher.getVoucherAdminFlag(),cvoucher.getVoucherAcctFlag());
							if (flag.equals("saved")) { 
								
								if (request.getParameter("checkStatus").equals("D")) {
									//addActionMessage(getMessage("save"));
									sendProcessManagerAlertDraft();
									addActionMessage("Your application has been Draft successfully.");
								} else{
									
										String str = (empFlow != null && empFlow.length > 0) ? String
												.valueOf(empFlow[0][0])
											: "0";
										/*	
										 * Remove Process manager alert entry from
										 * mypage
										 *//*
										 */								
										  try { MypageProcessManagerAlertsModel
										  processAlerts = new
										  MypageProcessManagerAlertsModel();
										  processAlerts.initiate(context, session);
										  processAlerts.removeProcessAlert(cvoucher.getVCode(), module);
										  processAlerts.terminate(); } 
										  catch (Exception  e) {
										  e.printStackTrace(); 
										  }
										try {
												
											sendApplicationAlert(cvoucher.getVCode(),cvoucher.getEmpCode(), 
													str, empFlow,kiEmpCodeIt,cvoucher.getVoucherMgrFlag(),
													cvoucher.getVoucherAdminFlag(), cvoucher.getVoucherAcctFlag(), module);
												addActionMessage("Request sent for approval successfully.\nRequest ID:-"
														+ cvoucher.getVoucherNo().trim());
											} catch (Exception e) {
												e.printStackTrace();
											}
		
								}
										addActionMessage("Your request has been saved successfully.");
						} else if (flag.equals("notsaved")) {
										addActionMessage("Your application has not saved successfully.");
										model.addVoucher(cvoucher, srNo, voucherHeadCode,
												voucherHeadName, amount, remark, 2, proof, upload);
						}
									
	
					} catch (Exception e) {
						e.printStackTrace();
							logger.error("Exception in save");
					}

				}else{
						flag = model.modify(cvoucher, empFlow, voucherHeadCode,
								amount, remark, proof, upload, strKiEmpCodeIt);
						if (flag.equals("modified")) {
							//addActionMessage(getMessage("update"));
							String str = (empFlow != null && empFlow.length > 0) ? String
									.valueOf(empFlow[0][0])
								: "0";
							sendApplicationAlert(cvoucher.getVCode(),cvoucher.getEmpCode(), 
									str, empFlow,kiEmpCodeIt,cvoucher.getVoucherMgrFlag(),
									cvoucher.getVoucherAdminFlag(), cvoucher.getVoucherAcctFlag(), module);
							addActionMessage("Your application has been sent for approval successfully.");
						} else if (flag.equals("notmodified")) {
							addActionMessage("Your application has not sent for approval successfully.");
							model.addVoucher(cvoucher, srNo, voucherHeadCode,
									voucherHeadName, amount, remark, 2, proof, upload);
						}
					
				}
			} else {
				addActionMessage("Reporting structure not defined for the employee\n"
						+ cvoucher.getEmpName()	+ "\nPlease contact HR manager !");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (cvoucher.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (cvoucher.getSource().equals("myservices")) {
			return "serviceJsp";
		}
		else{
		return input();
		}

	}

	
	
	/**
	 * Saves the voucher applications
	 * @return : Forward mapping parameter
	 */
	/*public String save() {
		try {
			
			 * Retrieve the values from the voucher head list for saving into the HRMS_VCHDTL table
			 
			Object[] srNo = request.getParameterValues("srNo");
			Object[] voucherHeadCode = request.getParameterValues("vchCode");
			Object[] voucherHeadName = request
					.getParameterValues("VoucherHead");
			Object[] amount = request.getParameterValues("vamt");
			Object[] remark = request.getParameterValues("vrem");
			Object[] proof = request.getParameterValues("vproof");
			Object[] upload = request.getParameterValues("uploadFile");
			String[] kiEmpCodeIt = request
					.getParameterValues("keepInformedEmpCode");
			String strKiEmpCodeIt = "";
			if (kiEmpCodeIt != null) {
				for (int cnt = 0; cnt < kiEmpCodeIt.length; cnt++) {
					if (cnt == 0) {
						strKiEmpCodeIt = String.valueOf(kiEmpCodeIt[cnt]);
					} else {
						strKiEmpCodeIt = strKiEmpCodeIt + ","
								+ String.valueOf(kiEmpCodeIt[cnt]);
					}
				}
			}
			CashVoucherModel cashModel = new CashVoucherModel();
			cashModel.initiate(context, session);
			String flag = "";
			System.out.println("========STATUS====="
					+ request.getParameter("checkStatus"));
			Object[][] empFlow = generateEmpFlow(cvoucher.getEmpCode(), "Cash",
					1);
			if (cvoucher.getVCode().equals("")) {
				flag = cashModel.save(cvoucher, empFlow, voucherHeadCode,
						amount, remark, proof, upload, strKiEmpCodeIt);
				if (flag.equals("saved")) {
					if (request.getParameter("checkStatus").equals("D")) {
						//addActionMessage(getMessage("save"));
						sendProcessManagerAlertDraft();
						addActionMessage("Your application has been Draft successfully.");
					} else {
						addActionMessage("Your application has been send for approval successfully.");
					}

				} else if (flag.equals("notsaved")) {
					addActionMessage("Your application has not saved successfully.");
					cashModel.addVoucher(cvoucher, srNo, voucherHeadCode,
							voucherHeadName, amount, remark, 2, proof, upload);
				}
			} else {
				flag = cashModel.modify(cvoucher, empFlow, voucherHeadCode,
						amount, remark, proof, upload, strKiEmpCodeIt);
				if (flag.equals("modified")) {
					//addActionMessage(getMessage("update"));
					addActionMessage("Your application has been sent for approval successfully.");
				} else if (flag.equals("notmodified")) {
					addActionMessage("Your application has not sent for approval successfully.");
					cashModel.addVoucher(cvoucher, srNo, voucherHeadCode,
							voucherHeadName, amount, remark, 2, proof, upload);
				}
			}
			
		*//** Give the message if approver is not defined for employee*//*
			 
			if (flag.equals("empNull")) {
				addActionMessage("Reporting structure not defined for the employee\n"
						+ cvoucher.getEname());
				addActionMessage("Please contact HR manager !");
				cashModel.addVoucher(cvoucher, srNo, voucherHeadCode,
						voucherHeadName, amount, remark, 2, proof, upload);
			}
			cashModel.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cvoucher.getSource().equals("mymessages")) {
			return "mymessages";
		} 
		else if (cvoucher.getSource().equals("myservices"))
		{
			return "serviceJsp";
		}
		else{
		return input();
		}
	}
*/
	
	public void sendApplicationAlert(String applnID, String applicant,String  approver, Object[][] empFlow,
			String[] keepInfo,String voucherMgrFlag,String voucherAdminFlag,String voucherAcctFlag,String module ) {
		try {
			
			/**
			 * Remove Process manager alert entry from mypage
			 */
			try {
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				processAlerts.removeProcessAlert(applnID, "Voucher");
				processAlerts.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String msgType = "A";
			String level = "1";
			String alertlink ="";
			String linkParam="";
			EmailTemplateBody template = new EmailTemplateBody();
			
			
			template.initiate(context, session);
			
			template.setEmailTemplate("CASH VOUCHER -APPLICANT TO APPROVER");			
			template.getTemplateQueries();
			
			try {
				System.out.println("APPLICANT-1="+applicant);
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
				templateQuery1.setParameter(1, applicant);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				System.out.println("APPROVER-2="+approver);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, approver);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				System.out.println("APPLNID-3="+applnID);
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("APPLICANT-4="+applicant);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicant);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("APPLICANT-4="+applnID);
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			
			template.configMailAlert();
			String keepInformId = "";

			if (keepInfo != null) {

				for (int i = 0; i < keepInfo.length; i++) {
					if (i == keepInfo.length - 1) {
						keepInformId += String.valueOf(keepInfo[i]);
					} else {
						keepInformId += String.valueOf(keepInfo[i]) + ",";

					}
				}

			}
			
			if(voucherMgrFlag.equals("Y") || voucherAdminFlag.equals("Y")){		
			alertlink = "/voucher/VoucherApplication_retriveForApproval.action";
			linkParam = "voucherNoView="+applnID+"&status=P";
			}else{
				alertlink = "/voucher/CashVoucher_getAccountantDetails.action";
				linkParam = "voucherNoView="+applnID ;
			}
			try {
				template.sendProcessManagerAlert(approver, module, msgType,
						applnID, level, linkParam, alertlink, "Pending",
						applicant, "", "", "",applicant);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/*try {
				if (!String.valueOf(empFlow[0][3]).equals("0")) {
					template.sendApplicationMailToAlternateApprover(String.valueOf(empFlow[0][3]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			//	Add approval link -pass parameters to the link
			
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			String applicationType = "CashVoucherApp";
						
			link_param[0] = applicationType + "#" + "A" + "#" + level + "#"
			+ applicant + "#" + voucherMgrFlag + "#" 
			+ "#" + voucherAcctFlag + "#" + applnID + "#" + "...#"
			+ voucherAdminFlag + "#" + approver;
			link_param[1] = applicationType + "#" + "R" + "#" + level + "#"
			+ applicant + "#"  + "#" + voucherAdminFlag
			+ "#" + voucherAcctFlag + "#" + applnID + "#" + "...#"
			+ voucherAdminFlag + "#" + approver;
			link_param[2] = applicationType + "#" + "B" + "#" + level + "#"
			+ applicant + "#" + voucherMgrFlag + "#" + "#" + voucherAcctFlag + "#" 
			+ applnID + "#" + "...#"+ voucherAdminFlag + "#" + approver;

			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			if (keepInfo != null) {
				template.sendApplicationMailToKeepInfo(keepInfo);
			}
			
			template.addOnlineLink(request, link_param, link_label);	
			
			
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			
						
			/* MAIL TEMPLATES TO APPLICANT DETAILS*/
			EmailTemplateBody template1 = new EmailTemplateBody();
			template1.initiate(context, session);
			
			template1.setEmailTemplate("CASH VOUCHER - APPLICATION DETAILS TO APPLICANT");			
			template1.getTemplateQueries();
			
			try {
				System.out.println("APPLICANT-1="+applicant);
				EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); //FROM EMAIL
				templateQuery11.setParameter(1, applicant);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				System.out.println("APPROVER-2="+approver);
				EmailTemplateQuery templateQuery22 = template1.getTemplateQuery(2); //TO EMAIL
				templateQuery22.setParameter(1, approver);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				System.out.println("APPLNID-3="+applnID);
				EmailTemplateQuery templateQuery33 = template1.getTemplateQuery(3);
				templateQuery33.setParameter(1, applnID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("APPLICANT-4="+applicant);
				EmailTemplateQuery templateQuery44 = template1.getTemplateQuery(4);
				templateQuery44.setParameter(1, applnID);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			template1.configMailAlert();
			// create process alerts
			alertlink = "/voucher/CashVoucher_retrive.action";
			linkParam = "vCode="+applnID+"&cvStatus=P";
						
			template1.sendProcessManagerAlert("", module, "I",
					applnID, level, linkParam, alertlink, "Pending",
					applicant, "", keepInformId, applicant,applicant);
			
			template1.sendApplicationMail();
			template1.clearParameters();
			template1.terminate();
			
			
		} catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	
	public String showVoucherList(){
		System.out.println("------------SHOWVOUCHERLIST---------");
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		model.showVoucherList(cvoucher,request);
		model.terminate();
		return SUCCESS;
	}
	// updated By Anantha Lakshmi
	

	
	//Approved List
	public String getApprovedList() throws Exception {
		getNavigationPanel(1);
		try {
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			model.getApprovedList(cvoucher, request, cvoucher.getUserEmpId());
			cvoucher.setListType("approved");
			cvoucher.setPrevAppCommentListFlag("true");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return SUCCESS;
	}
	//Rejected App
	
	public String getRejectedList() throws Exception {
		getNavigationPanel(1);
		try {
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			model.getRejectedList(cvoucher, request, cvoucher.getUserEmpId());
			cvoucher.setListType("rejected");
			cvoucher.setPrevAppCommentListFlag("true");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}
		return SUCCESS;
	}
	public String addNew() {
		
		String source = request.getParameter("src");
		String status = request.getParameter("status");
		System.out.println("source--------------" + source);
		cvoucher.setSource(source);
		
		
		System.out.println("------addNew---------");
		getNavigationPanel(2);
		try {
		    reset();
		    CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			model.showEmp(cvoucher,request);
			cvoucher.setSendAppBtnFlag("true");
			cvoucher.setDeleteBtnFlag("true");
			cvoucher.setSearchResetFlag("true");
			if(status != null && ! status.equals("")){
				if(status.equals("B")){
					cvoucher.setStatus("Send Back");
					cvoucher.setHiddenStatus("B");
				}else{
					cvoucher.setStatus("DRAFT");
					cvoucher.setHiddenStatus("D");
				}
			}
			cvoucher.setPrevAppCommentFlag("false");
			setApproverList();
			cvoucher.setVoucherDetailsFlag("true");
			
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in addNew--------" + e);
		}
		return "addCashVoucherApp";
	}
	public String back() {
		try {
			getNavigationPanel(1);
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			System.out.println("========STATUS========="+cvoucher.getHiddenStatus());
			String status=cvoucher.getHiddenStatus();
			if(status.equals("A")){
				model.getApprovedList(cvoucher, request, cvoucher.getUserEmpId());
				cvoucher.setListType("approved");
			}
			if(status.equals("R")){
				model.getRejectedList(cvoucher, request, cvoucher.getUserEmpId());
				cvoucher.setListType("rejected");
			}
		    if(status.equals("P")||status.equals("B")){
		    	model.getAllTypeOfApplications(cvoucher, request,cvoucher.getUserEmpId());
		    	cvoucher.setListType("pending");
		    }
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in input--------" + e);
		}
		return SUCCESS;
	}
	public String delete1() {
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		boolean result = model.deleteVoucherPara(cvoucher,request);
		if(result){
			addActionMessage("Record deleted successfully.");
		}else
			addActionMessage("Record can't deleted successfully.");
		
		input();
		return SUCCESS;
	}
	/*public String sendForApproval() {
		boolean result;
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		Object[][] empFlow = generateEmpFlow(cvoucher.getUserEmpId(), "Cash", 1);
		String applnID = request.getParameter("vCode");
		String module = "Voucher";
		String applicant = request.getParameter("empCode");//cvoucher.getEmpCode();
		String approver = String.valueOf(empFlow[0][0]);
		String applnDate = cvoucher.getVchDate();
		String keepInfo[] = request.getParameterValues("keepInformedEmpCode");
		// Draft list is send for approval
		if(cvoucher.getViewAppFlag().equals("true")){
			save();
			result= model.sendForApprovalRecord(cvoucher,applnID);
			input();
			if(result){
				//------------------------Process Manager Alert------------------------start
				try {		
					sendApplicationAlert(applnID, module, applicant, empFlow, applnDate,keepInfo);
				} catch(Exception e) {
					logger.error(e);
				}
				//------------------------Process Manager Alert------------------------end
			}
			
			
			
		}//Direct send for approval
		else{
			System.out.println("=======SENDING APPLICATION=====ELSE============");
			save();
			model.getAppCode(cvoucher);
			String applnID1 = cvoucher.getVCode();
			if(applnID1.equals("0")){
				applnID1=String.valueOf("1");
			}else{
				applnID=applnID1;
			}
			//------------------------Process Manager Alert------------------------start
			try {
				sendApplicationAlert(applnID, module, applicant, empFlow, applnDate,keepInfo);
			} catch(Exception e) {
				logger.error(e);
			}
			//------------------------Process Manager Alert------------------------end	
		}  
		model.terminate();
		return SUCCESS;
	}*/
	public String f9KeepInformedEmployee() {
		System.out.println();
		String[] eId = request.getParameterValues("keepInformedEmpCode");
		String str = cvoucher.getEmpCode();
		if (eId != null) {
			for (int i = 0; i < eId.length; i++) {
				str =str+ "," + eId[i];
			}
		}
		System.out.println("ANOTHER APPROVERS"+str);
		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "	FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S' ";
			if (cvoucher.getUserProfileDivision() != null && cvoucher.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN ("+ cvoucher.getUserProfileDivision() + ")";
			query += " AND EMP_ID NOT IN(" + str + ") ";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

			String[] headers = { getMessage("kiEmpToken"),getMessage("kiEmpName") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "kiEmpToken", "kiEmpName","kiEmpCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		} // end of try-catch block
	} 
	public String addKeepInformedEmpList() {
		try {
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			
			if (cvoucher.getHiddenStatus().equals("B")) {
				getNavigationPanel(7);
			} else {
				getNavigationPanel(2);
			}
			setApproverList();
			String[] serialNo = request.getParameterValues("serialNo"); // serial
			String[] empCode = request.getParameterValues("keepInformedEmpCode");// keep
			String[] empToken = request.getParameterValues("keepInformedEmpId");// keep
			String[] empName = request.getParameterValues("keepInformedEmpName");// keep informed
			
			model.displayIteratorValueForKeepInformed(serialNo, empCode,empToken,empName, cvoucher);
			model.setKeepInformed(serialNo, empCode,empToken, empName, cvoucher);
			cvoucher.setKiEmpName("");
			cvoucher.setVoucherDetailsFlag("true");
			//Get Iterator values of Voucher Details
		
		    cvoucher.setVchHeadCode("");
			cvoucher.setVoucherHeadName("");
			cvoucher.setVamount("");
			cvoucher.setUploadFileName("");
			cvoucher.setVremark("");
			cvoucher.setHproof("");
			Object[] srNo = request.getParameterValues("srNo");
			Object[] voucherHeadCode = request.getParameterValues("vchCode");
			Object[] voucherHeadName = request.getParameterValues("VoucherHead");
			Object[] amount = request.getParameterValues("vamt");
			Object[] remark = request.getParameterValues("vrem");
			Object[] proof  = request.getParameterValues("vproof");
			Object[] upload = request.getParameterValues("uploadFile");
			model.addVoucher(cvoucher, srNo, voucherHeadCode, voucherHeadName, amount, remark, 1, proof, upload);	
			model.terminate();
			
			setStatus();
		} catch (Exception e) {
		}
		return "addCashVoucherApp";
	}
	public void setApproverList() {
		try {
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			String empCode=cvoucher.getEmpCode();
			System.out.println("-------EMPCODE------"+empCode);
			Object[][] empFlow = model1.generateEmpFlow(empCode, "Cash");
			model.setApproverData(cvoucher, empFlow);
			model1.terminate();
			model.terminate();
			
		} catch (Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}
	
	private void setStatus()
	{
		try {
			cvoucher.setStatus(cvoucher.getHiddenStatus().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String removeKeepInformed() {
		try {
			if (cvoucher.getHiddenStatus().equals("B")) {
				getNavigationPanel(7);
			} else {
				getNavigationPanel(2);
			}
			
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpCode");
			String[] empToken = request.getParameterValues("keepInformedEmpId");
			String[] empName = request.getParameterValues("keepInformedEmpName");
			String vchCode=request.getParameter("vCode");
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			String removeEmpId=request.getParameter("kiInfrEmployeeCode");
			cvoucher.setVoucherDetailsFlag("true");
			//model.getRecord(cvoucher,request);
			showVoucherList();
			setApproverList();
			model.removeKeepInformedData(serialNo, empCode,empToken, empName,cvoucher,removeEmpId,vchCode);
			cvoucher.setCheckRemove("");
			setStatus();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformed--------" + e);
		}
		return "addCashVoucherApp";

	}
	public String setEmpDetails(){
		
		String status = request.getParameter("hiddenStatus");
		System.out.println("--------STATUS-------"+status);
		if (status.equals("D")) {
			getNavigationPanel(7);
			cvoucher.setViewAppFlag("true");
			cvoucher.setVoucherDetailsFlag("true");
		}
		System.out.println(" USER EMP CODE---"+cvoucher.getUserEmpId());
		System.out.println("---V CODE-----"+cvoucher.getVCode());
		System.out.println("-------cv Ststus--"+request.getParameter("cvStatus"));
		setApproverList();
		cvoucher.setType("");
		cvoucher.setKeepInformedEmpCode("");
		cvoucher.setKeepInformedEmpId("");
		cvoucher.setKeepInformedEmpName("");
		cvoucher.setKiEmpName("");
		cvoucher.setKiEmpToken("");
		cvoucher.setKiEmpCode("");
		cvoucher.setSerialNo("");

		cvoucher.setSrNo(""); // Sr No
		cvoucher.setVchCode(""); // Voucher Head Code
		cvoucher.setVoucherHead(""); // Voucher  Head  Name
		cvoucher.setVamt(""); // Voucher amount
		cvoucher.setVrem(""); // Voucher Remark
		cvoucher.setVproof("");
		cvoucher.setUploadFile("");
		cvoucher.setTotalamount("");
		cvoucher.setVoucherHeadName("");
		cvoucher.setVchHeadCode("");
		cvoucher.setVamount("");
		cvoucher.setHproof("");
		cvoucher.setVremark("");
		cvoucher.setUploadFileName("");
		return "addCashVoucherApp";
		
	}
	public String deleteVoucherDetails()
	{
		try {
			getNavigationPanel(1);
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			boolean result = model.deleteVoucherPara(cvoucher, request);
			model.getAllTypeOfApplications(cvoucher, request, cvoucher
					.getUserEmpId());
			if (result) {
				deleteProcessManagerAlert("Voucher", cvoucher.getVCode());
				addActionMessage("Record deleted successfully.");
			}
			cvoucher.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cvoucher.getSource().equals("mymessages")) {
			return "mymessages";
		} 
		else if (cvoucher.getSource().equals("myservices"))
		{
			return "serviceJsp";
		}
		else{
		return SUCCESS;
		}
		
	}
	
	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "\\Alerts\\alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = cvoucher.getEmpCode();
			String module = "Voucher";
			String applnID = cvoucher.getVCode();
			String level = "1";
			String link = "/voucher/CashVoucher_retrive.action";
			String linkParam = "vCode=" + applnID + "&cvStatus=D&empCode="+applicantID;
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", cvoucher
					.getEname().trim());
			message = message.replace("<#APPL_TYPE#>", "Voucher");
			template.sendProcessManagerAlertDraft(applicantID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					applicantID,applicantID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String approveRejSendBackRequestApplication() {
		try {
			setLevelForApplication();
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			String status=cvoucher.getHiddenStatus();
			int level=cvoucher.getAppLevel();
			String applicantId=cvoucher.getEmpCode();
			String vCode=cvoucher.getVCode();
			String isManagerAppr=cvoucher.getVoucherMgrFlag(); 
			String isAdminFlag=cvoucher.getVoucherAdminFlag(); 
			String isAcctFlag=cvoucher.getVoucherAcctFlag(); 
						
			String approverComments=cvoucher.getVchRemark();
			String currentUser=cvoucher.getUserEmpId();
			String adminStatus=model.getAdminStatus(cvoucher);
			String applicationStatus = model.approveRejectApplication( request,vCode,status,level,applicantId,
					isManagerAppr,isAdminFlag,isAcctFlag,approverComments,currentUser,adminStatus);
			
			if (applicationStatus.equals("approved")) {
				addActionMessage("Request approved.");
				
			} else if (applicationStatus.equals("sendback")) {
				addActionMessage("Request sent back.");
			} else {
				
				addActionMessage("Request rejected.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cvoucher.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (cvoucher.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return INPUT;
		}
	
	}
	
	public void setLevelForApplication() {
		try {
			CashVoucherModel model = new CashVoucherModel();
			model.initiate(context, session);
			String query = " SELECT APPL_LEVEL FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE="
					+ cvoucher.getVCode();
			Object levelObj[][] = model.getSqlModel().getSingleResult(query);
			if (levelObj != null && levelObj.length > 0) {
				cvoucher.setAppLevel(Integer.parseInt(String.valueOf(levelObj[0][0])));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String getAccountantDetails() throws Exception {
		cvoucher.setListType("pending");
		return "accountantList";
	}
	
}
