package org.paradyne.model.voucher;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.voucher.CashVoucherMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

/**
 * @author mangeshj
 * Date 01/02/2008
 * CashVoucherModel class to write the business logic to add, update, view the cash voucher application
 */

public class CashVoucherModel extends ModelBase {
	CashVoucherMaster voucher;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.paradyne.model.voucher.CashVoucherModel.class);
	NumberFormat testNumberFormat = new DecimalFormat("#0.00");

	/* method name : save
	 * purpose     : to save voucher application
	 * return type : String
	 * parameter   : CashVoucherMaster instance, Object[][] emp, Object[] vchCode, Object[] amount, Object[] remark, Object[] proof, Object [] upload
	 */
	public String save(CashVoucherMaster voucher, Object[][] emp, Object[] vchCode, Object[] amount,
						Object[] remark, Object[] proof, Object [] upload,String kiEmpCodeIt,String voucherMgrFlag,String voucherAdminFlag ,String voucherAcctFlag) {
		boolean result = false;
		String msg = "";
		Object[][] to_mailIds = new Object[1][1];	
		Object[][] from_mailIds = new Object[1][1];
		if (emp == null) {
			return "empNull";
		}
		Object[][] saveObj = new Object[1][10];
		String query = "SELECT NVL(MAX(VOUCHER_APPL_CODE),0)+1 FROM HRMS_VOUCHER_APPL";
		Object[][] resultCode = getSqlModel().getSingleResult(query);
		saveObj[0][0] = String.valueOf(resultCode[0][0]); // / max appl_code
		saveObj[0][1] = voucher.getType(); // voucher type
		saveObj[0][2] = voucher.getEmpCode(); // employee Code
		saveObj[0][3] = voucher.getVchDate(); // voucher Date
		if(voucher.getDraftFlag().equals("true")){
			saveObj[0][4] = "D";
		}else{
			saveObj[0][4] = "P";
		}		
		saveObj[0][5] = voucher.getTotalamount(); // total amount
		saveObj[0][6] =checkNull( String.valueOf(emp[0][0])); // APPROVER OF THE APPLICATION
		saveObj[0][7] = voucher.getDetails(); // Details
		if(voucherMgrFlag.equals("N")&& (voucherAdminFlag.equals("Y") || voucherAcctFlag.equals("Y")) ){
			saveObj[0][8] ="";
		}else{
		saveObj[0][8] = checkNull(String.valueOf(emp[0][3])); // alternate approver
		}
		if(kiEmpCodeIt!=null){
		saveObj[0][9] = kiEmpCodeIt;
		}else
		{
			saveObj[0][9] = "";
		}
		result = getSqlModel().singleExecute(getQuery(1), saveObj);
		if (result) {
			voucher.setVCode(String.valueOf(resultCode[0][0]));
			result = saveDetails(voucher, vchCode, amount, remark, proof, upload);	
		}
		if (result){
			msg = "saved";
			 /****************** generate the application number****************/ 
			ApplCodeTemplateModel model = new ApplCodeTemplateModel();
			model.initiate(context, session);
			String applnCode = model.generateApplicationCode(voucher.getVCode(), "Cash", voucher.getEmpCode(),voucher.getVchDate());
			logger.info("final appln code in application=="+ applnCode);
			getSqlModel().singleExecute("UPDATE HRMS_VOUCHER_APPL SET VOUCHER_NO='"+ applnCode + "' WHERE VOUCHER_APPL_CODE="
							+ voucher.getVCode());
			model.terminate();
			/****************** end generate the application number****************/
		} else {
			msg = "notsaved";
		}		
		return msg;
	}
	
	
	/* method name : saveDetails
	 * purpose     : to save voucher Details
	 * return type : boolean
	 * parameter   : CashVoucherMaster instance, Object[] vchCode, Object[] amount, Object[] remark, Object[] proof, Object [] upload
	 */
	public boolean saveDetails(CashVoucherMaster voucher, Object[] vchCode,
			Object[] amount, Object[] remark, Object[] proof, Object [] upload) {
		boolean result = false;
		if (vchCode != null) { // check whether data is present in table list
				Object[][] detailData = new Object[vchCode.length][6];
				for (int i = 0; i < vchCode.length; i++) {
					detailData[i][0] = voucher.getVCode(); // application code
					detailData[i][1] = vchCode[i]; // voucher Head code
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
	
	
	
	/* method name : addVoucher
	 * purpose     : to add voucher Details in the list
	 * return type : void
	 * parameter   : CashVoucherMaster instance, Object[] Srno,Object[] voucherHeadCode, Object[] voucherHeadName,
	 *			     Object[] amount, Object[] remark, int remove, Object[] proof, Object [] upload
	 */
	public void addVoucher(CashVoucherMaster bean, Object[] Srno,Object[] voucherHeadCode, Object[] voucherHeadName,
			Object[] amount, Object[] remark, int remove, Object[] proof, Object [] upload) {
		
		double total = 0.0;
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (Srno != null) // check whether list is empty or not
		{
			for (int i = 0; i < Srno.length; i++) {
				CashVoucherMaster bean1 = new CashVoucherMaster();
				bean1.setSrNo(String.valueOf(i + 1)); // Sr No
				bean1.setVchCode(String.valueOf(voucherHeadCode[i])); // Voucher Head Code
				bean1.setVoucherHead(String.valueOf(voucherHeadName[i])); // Voucher Head Name
				bean1.setVamt(Utility.twoDecimals(String.valueOf(amount[i]))); // Voucher amount
				bean1.setVrem(String.valueOf(remark[i])); // Voucher Remark
				bean1.setVproof(String.valueOf(proof[i]));
				bean1.setUploadFile(String.valueOf(upload[i]));
				total += Double.parseDouble(String.valueOf(amount[i]));
				bean.setTotalamount(testNumberFormat.format(total));
				tableList.add(bean1); // add into table list
			} 
		} 
		if (remove == 1) {
		
			try {
				total += Double.parseDouble(String.valueOf(bean.getVamount()));

				bean.setVamt(Utility.twoDecimals(bean.getVamount()));
				bean.setVrem(bean.getVremark());
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
				logger.info("exception in addVoucher()"+e);
				
			}
			bean.setTotalamount(testNumberFormat.format(total));
			bean.setList(tableList);
		} // end of if
		else if (remove == 0) {
			boolean result;
			Object[][] delObj=new Object[1][1];
			delObj[0][0]=bean.getVCode();
			String qry="SELECT * FROM HRMS_VCHDTL WHERE VOUCHER_APPL_CODE="+bean.getVCode();
		    Object[][] voucher_Records = getSqlModel().getSingleResult(qry);
			//Object[][] resultCode = getSqlModel().getSingleResult(query);
		    if( voucher_Records!=null){
		    	result = getSqlModel().singleExecute(getQuery(10), delObj);
		    }
			tableList.remove(Integer.parseInt(bean.getCheckEdit()) - 1);
			total = Double.parseDouble(bean.getTotalamount())- Double.parseDouble(bean.getTotalCheck());
			bean.setCheckEdit("");
			bean.setTotalamount(testNumberFormat.format(total));
		}
		else {
		} 
		if (tableList.size() != 0)
			bean.setTableLength("1");
		else
			bean.setTableLength("");
		bean.setList(tableList);

	}
	/* method name : modify
	 * purpose     : to update voucher application
	 * return type : String
	 * parameter   : CashVoucherMaster instance, Object[][] emp,Object[] vchCode, Object[] amount, 
	 * 				 Object[] remark, Object[] proof, Object [] upload
	 */
	public String modify(CashVoucherMaster bean, Object[][] emp,
			Object[] vchCode, Object[] amount, Object[] remark, Object[] proof, Object [] upload,String strKiEmpCodeIt) {
		boolean result = false;
		if (emp == null) {
			return "empNull"; // / return Error message when no approver is defined
		} 
		Object[][] modObj = new Object[1][9];
		modObj[0][0] = bean.getType(); // type of the voucher
		modObj[0][1] = bean.getUserEmpId(); // employee code
		modObj[0][2] = bean.getVchDate(); // date
		modObj[0][3] = bean.getDetails(); // details
		modObj[0][4] = bean.getTotalamount(); // total amount
		modObj[0][5] = String.valueOf(emp[0][0]); // Approver
		modObj[0][6] = String.valueOf(emp[0][3]); // alternate approver
		modObj[0][7] =strKiEmpCodeIt ; 
		modObj[0][8] =bean.getVCode() ;// application code
		/*
		 * getQuery(8) for updating HRMS_VOUCHER_APPL
		 */
		result = getSqlModel().singleExecute(getQuery(8), modObj);
		Object[][] code = new Object[1][1];
		code[0][0] = bean.getVCode();
		/*
		 * CALL THE METHOD saveDetails(voucher, vchCode, amount, remark) FOR inserting the voucher details
		 */
		if (result) {
			getSqlModel().singleExecute(getQuery(10), code); //Deleting the voucher details from the HRMS_VCHDTL
			saveDetails(bean, vchCode, amount, remark, proof, upload);
			return "modified";
		}  // end of if
		else
			return "notmodified";

	}

	/* method name : deleteVoucherPara
	 * purpose     : to delete voucher application
	 * return type : boolean
	 * parameter   : CashVoucherMaster instance
	 */
	public boolean deleteVoucherPara(CashVoucherMaster bean,HttpServletRequest request) {
		Object addObj[][] = new Object[1][1];
		boolean result=false;
		try {
			addObj[0][0] = request.getParameter("vCode");			
			result =result = getSqlModel().singleExecute(getQuery(16), addObj);
			if (result) {
				 getSqlModel().singleExecute(getQuery(10), addObj);
			}
			if (result) {
				result = getSqlModel().singleExecute(getQuery(3), addObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	public boolean cancelRecord(CashVoucherMaster bean) {
		Object addObj[][] = new Object[1][2];
		boolean result=false;
		addObj[0][0] = "C";
		addObj[0][1] = bean.getVCode();
		result = getSqlModel().singleExecute(getQuery(14), addObj);			
		return result;

	}
	/* method name : getRecord
	 * purpose     : to delete voucher application
	 * return type : boolean
	 * parameter   : CashVoucherMaster instance
	 */
	public void getRecord(CashVoucherMaster bean,String appCode,HttpServletRequest request) {
		try {
			Object[] param = new Object[1];
			
			
			param[0] =appCode;
			
			System.out.println("param[0] -----------------    "+param[0]);
			
			if (param[0] != null)
				bean.setVCode(String.valueOf(param[0]));
			bean.setType(String.valueOf(request.getParameter("type")));
			Object applData[][] = getSqlModel().getSingleResult(getQuery(12),
					param);
			/*
			 * setting the Status of the Application 1)if level is 1 & status will
			 * be set to value coming from database
			 */
			if (String.valueOf(applData[0][3]).equals("1")) {
				bean.setStatus(String.valueOf(applData[0][0]));
				bean.setHiddenStatus(String.valueOf(applData[0][0]));
			}
			/*
			 * 2)if level is higher than 1 & application is pending status will be
			 * set to "Forwarded"
			 */
			else if (!(String.valueOf(applData[0][3]).equals("1"))
					&& String.valueOf(applData[0][0]).equals("P")) {
				bean.setStatus("F");
				bean.setHiddenStatus("F");
			}
			/*
			 * 3)if level is higher than 1 & application is not pending status will
			 * be set to value coming from database
			 */
			else {
				bean.setStatus(String.valueOf(applData[0][0]));
				bean.setHiddenStatus(String.valueOf(applData[0][0]));
			} // end of else
			bean.setDetails(checkNull(String.valueOf(applData[0][1]))); // Details
			//bean.setTotalamount(checkNull(String.valueOf(applData[0][2]))); // total amount
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
			bean.setType(String.valueOf(applData[0][7]));
			if (applData[0][6] != null) {
				String vchKiEmpId = String.valueOf(applData[0][6]);
				String[] strKiEmpId = vchKiEmpId.split(",");
				Object[][] kiEmpDetails = null;
				ArrayList<CashVoucherMaster> cvList = new ArrayList<CashVoucherMaster>();
				if (strKiEmpId != null) {
					for (int i = 0; i < strKiEmpId.length; i++) {
						String qry = "SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID="
								+ strKiEmpId[i];
						kiEmpDetails = getSqlModel().getSingleResult(qry);
						if (kiEmpDetails != null && kiEmpDetails.length > 0) {
							CashVoucherMaster cvApp = new CashVoucherMaster();
							cvApp.setKeepInformedEmpCode(String
									.valueOf(kiEmpDetails[0][0]));
							cvApp.setKeepInformedEmpId(String
									.valueOf(kiEmpDetails[0][1]));
							cvApp.setKeepInformedEmpName(String
									.valueOf(kiEmpDetails[0][2]));
							cvApp.setSerialNo(String.valueOf(i));// sr no
							cvList.add(cvApp);
							bean.setKeepInformedList(cvList);
						}
					}//for close
				}

			}//if close
			// getQuery(5) for selecting the Voucher details from HRMS_VCHDTL
			Object[][] result = getSqlModel().getSingleResult(getQuery(5),
					param);
			ArrayList<Object> tableList = new ArrayList<Object>();
			// setting the details into the table list
			if (!(result.length == 0)) {
				for (int i = 0; i < result.length; i++) {
					CashVoucherMaster bean1 = new CashVoucherMaster();
					bean1.setSrNo(String.valueOf(i + 1)); // Sr no.
					bean1.setVchCode(String.valueOf(result[i][0])); // voucher HeadCode
					bean1.setVoucherHead((String.valueOf(result[i][1]))); // Voucher Head Name
					bean1.setVamt(String.valueOf(result[i][2])); // voucher amount
					bean1.setVrem(checkNull(String.valueOf(result[i][3]))); // Voucher Remark
					if (String.valueOf(result[i][4]).equalsIgnoreCase("N"))
						bean1.setVproof("No");
					else
						bean1.setVproof("Yes");
					bean1
							.setUploadFile(checkNull(String
									.valueOf(result[i][5])));
					tableList.add(bean1); // add into tableList
				}
				bean.setTableLength("1");
				bean.setList(tableList);
			}
			ArrayList list = new ArrayList();
			String query = " SELECT EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME, TO_CHAR(APPR_DATE,'DD-MM-YYYY'), "
					+ " DECODE(VOUCHER_STATUS,'B','Sent Back','A','Approved','R','Rejected'),VOUCHER_REMARK"
					+ " FROM HRMS_VOUCHER_PATH  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
					+ " WHERE  HRMS_VOUCHER_PATH.VOUCHER_CODE="
					+ appCode;
			Object[][] approvedData = getSqlModel().getSingleResult(query);
			if (approvedData != null && approvedData.length > 0) {

				bean.setPrevApproverID(checkNull(String
						.valueOf(approvedData[0][0])));
				bean.setPrevApproverName(checkNull(String
						.valueOf(approvedData[0][1])));
				bean.setPrevApproverDate(checkNull(String
						.valueOf(approvedData[0][2])));
				bean.setPrevApproverStatus(checkNull(String
						.valueOf(approvedData[0][3])));
				bean.setPrevApproverComment(checkNull(String
						.valueOf(approvedData[0][4])));
				list.add(bean);
			}
			bean.setApproverCommentList(list);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	/* method name : getReport
	 * purpose     : to show the PDF report for selected voucher application
	 * return type : void
	 * parameter   : HttpServletRequest request,HttpServletResponse response, CashVoucherMaster instance
	 */
	public void getReport(HttpServletRequest request,HttpServletResponse response, CashVoucherMaster cv) {
		String s = "\nCASH VOUCHER\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",s);
		String query = "SELECT DECODE(STATUS,'P','Pending','A','Approved','R','Rejected','C','Canceled'),APPL_LEVEL FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE="+request.getParameter("vCode");
		Object[][] status = getSqlModel().getSingleResult(query);
		String stat = String.valueOf(status[0][0]);
		if (String.valueOf(status[0][0]).equals("Pending")&& !String.valueOf(status[0][1]).equals("1")) {
			stat = "Forwarded";
		} // end of if
		String sql = "SELECT ROWNUM,VCH_NAME,VCHDTL_REMARK,VCHDTL_AMOUNT ,DECODE(PROOF_FLAG,'Y','Yes','N','No')"
			+ " FROM HRMS_VCHDTL INNER JOIN HRMS_VCH_HD ON (HRMS_VCH_HD.VCH_CODE ="
			+ " HRMS_VCHDTL.VCH_CODE) WHERE HRMS_VCHDTL.VOUCHER_APPL_CODE="
			+ request.getParameter("vCode");
			//by Ananatha
		    //+ cv.getVCode();

		/*String approver = "SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(APPR_DATE,'DD-MM-YYYY'), "
			+ " VOUCHER_REMARK abc,DECODE(VOUCHER_STATUS,'P','Pending','A','Approved','R','Rejected') FROM HRMS_VOUCHER_PATH"
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) "
			+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
			+ " WHERE VOUCHER_CODE="
			+ request.getParameter("vCode")
			//by Ananatha
			//+ cv.getVCode()
			+ " union "
			+ " SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),'',cast(''as nvarchar2(100)) a, "
			+ " DECODE(STATUS,'P','Pending','A','Approved','R','Rejected') FROM  HRMS_VOUCHER_APPL "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.APPROVED_BY= HRMS_EMP_OFFC.EMP_ID) "
			+ " INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
			+ " where STATUS='P'and VOUCHER_APPL_CODE="+ request.getParameter("vCode") ;
			//by Ananatha
			//+ cv.getVCode();
*/
		String approver ="SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' ')," +
				"TO_CHAR(APPR_DATE,'DD-MM-YYYY'),  VOUCHER_REMARK abc,DECODE(VOUCHER_STATUS,'P','Pending','A','Approved','R','Rejected') " +
				"FROM HRMS_VOUCHER_PATH INNER JOIN " +
				"HRMS_EMP_OFFC ON (HRMS_VOUCHER_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) " +
				"WHERE VOUCHER_CODE="+request.getParameter("vCode");
		Object[][] approverData = getSqlModel().getSingleResult(approver);

		Object[][] approvalTable = new Object[approverData.length][7];
		/*
		 * get the approver data in an Object to set it into the table
		 * 
		 */
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

		Object data[][] = new Object[5][4];

		/* Object down[][]=new Object[2][1]; */

		data[0][0] = "Voucher No.";
		data[0][1] = ": " + cv.getType();
		data[0][2] = "Employee Id. ";
		data[0][3] = ": " + cv.getEmpToken();

		data[1][0] = "Employee Name ";
		data[1][1] = ": " + cv.getEname();
		data[1][2] = "Department ";
		data[1][3] = ": " + cv.getDepartment();

		data[2][0] = "Designation ";
		data[2][1] = ": " + cv.getDesignation();
		data[2][2] = "Grade ";
		data[2][3] = ": " + cv.getGrade();

		data[3][0] = "Date ";
		data[3][1] = ": " + cv.getVchDate();
		data[3][2] = "Voucher Type ";
		data[3][3] = ": " + cv.getType();
		
		data[4][0] = "Status ";
		data[4][1] = ": " + stat;
		data[4][2] = "";
		data[4][3] = "";

		int[] bcellWidth = { 20, 30, 20, 30 };
		int[] bcellAlign = { 0, 0, 0, 0 };
		rg.addFormatedText(s, 6, 0, 1, 0);

		rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);


		int cellwidth[] = { 5, 34, 40, 20, 15 };
		int alignment[] = { 1, 1, 0, 2, 0 };

		Object [][]heading=new Object[1][1];
		int []cells={25};
		int []align={0};
		
		heading[0][0]="Voucher Details :";
		rg.tableBodyBold(heading, cells, align) ;
		//rg.addFormatedText("Voucher Details :", 6, 0, 0, 0);
		String colnames[] = { "Sr.No", "Voucher Head", "Particulars", "Amount",
		"Proof Attached" };
		String appCol[] = { "Sr. No", "Approver Id", "Approver Name", "Date",
				"Remarks", "Status", "Signature" };
		int appCell[] = { 5, 10, 25, 10, 30, 10, 12 };
		int apprAlign[] = { 1, 1, 0, 1, 0, 0, 0 };
		rg.tableBody(colnames, tab, cellwidth, alignment);
		Object totObj[][]=new Object[1][5];
		totObj[0][0]="";
		totObj[0][1]="";
		totObj[0][2]="Total Amount";
		totObj[0][3]=""+cv.getTotalamount();
		totObj[0][4]="";
		rg.tableBody(totObj, cellwidth, alignment);
		//rg.addFormatedText("Total Amount: " + cv.getTotalamount(), 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("Details:  " + cv.getDetails(), 0, 0, 0, 0);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		heading[0][0]="Approver Details :";
		rg.tableBodyBold(heading, cells, align) ;
		//rg.addFormatedText("Approver Details :", 6, 0, 0, 0);
		/*rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);*/

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

	/* method name : showEmp
	 * purpose     : to show the employee details on the screen
	 * return type : void
	 * parameter   : CashVoucherMaster instance
	 */
	public void showEmp(CashVoucherMaster cvoucher,HttpServletRequest request) {
		
		Object[] emp = new Object[1];
		emp[0]=cvoucher.getUserEmpId();
		Object[][] empdata = getSqlModel().getSingleResult(getQuery(9), emp);
		cvoucher.setEmpCode(String.valueOf(empdata[0][0]));
		cvoucher.setEname(String.valueOf(empdata[0][1])); // Employee Name
		cvoucher.setDesignation(String.valueOf(empdata[0][2])); // Designation
		cvoucher.setDepartment(String.valueOf(empdata[0][3])); // Dept
		cvoucher.setGrade(String.valueOf(empdata[0][4])); // Grade
		cvoucher.setEmpToken(String.valueOf(empdata[0][5])); // token no
	}
	/* method name : getEmployeeDetails
	 * purpose     : to show the employee details on the screen
	 * return type : void
	 * parameter   : String empId,CashVoucherMaster instance
	 */
	public void getEmployeeDetails(String empId, CashVoucherMaster cvoucher) {
		Object[] emp = new Object[1];
		emp[0] = empId;
		Object[][] empdata = getSqlModel().getSingleResult(getQuery(9), emp);
		cvoucher.setEmpCode(String.valueOf(empdata[0][0]));
		cvoucher.setEname(String.valueOf(empdata[0][1])); // Employee Name
		cvoucher.setDesignation(String.valueOf(empdata[0][2])); // Designation
		cvoucher.setDepartment(String.valueOf(empdata[0][3])); // Dept
		cvoucher.setGrade(String.valueOf(empdata[0][4])); // grade
		cvoucher.setEmpToken(String.valueOf(empdata[0][5])); // token no
	}

	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		/*
		 * method to check the null value
		 * 
		 */
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	/* 
	 * method name : setApplication 
	 * purpose     : set the header details on the screen to show to the approver
	 * return type : void
	 * parameter   : CashVoucherMaster instance
	 */
	public void setApplication(CashVoucherMaster bean) {
		try {
			Object[] vCode = new Object[1];
			//System.out.println("Vcode"+bean.getVCode());
			vCode[0] = bean.getVCode();
			Object[][] result = getSqlModel().getSingleResult(getQuery(13),
					vCode);
			//bean.setVCode(String.valueOf(result[0][0]));
			bean.setType(String.valueOf(result[0][1]));
			bean.setEmpCode(String.valueOf(result[0][2]));
			bean.setVchDate(String.valueOf(result[0][4]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* 
	 * method name : editVoucher 
	 * purpose     : set the voucher details in the list after editing
	 * return type : void
	 * parameter   : CashVoucherMaster instance, Object[] Srno,Object[] voucherHeadCode, 
	 * 				 Object[] voucherHeadName,Object[] amount, Object[] remark, Object[] proof, Object [] upload 
	 */
	public void editVoucher(CashVoucherMaster bean, Object[] Srno,
			Object[] voucherHeadCode, Object[] voucherHeadName,
			Object[] amount, Object[] remark, Object[] proof, Object [] upload ) {
		double total = 0.0;
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (Srno != null) // check whether list is empty or not
		{
			for (int i = 0; i < Srno.length; i++) {
				CashVoucherMaster bean1 = new CashVoucherMaster();
				if (i == Integer.parseInt(bean.getCheckEdit()) - 1) {
					logger.info("loop no$$$$$$$$$$$$$$$$$$" + i);
					bean1.setVamt(Utility.twoDecimals(bean.getVamount()));
					bean1.setVrem(bean.getVremark());
					bean1.setVchCode(bean.getVchHeadCode());
					bean1.setVoucherHead(bean.getVoucherHeadName());
					bean1.setUploadFile(bean.getUploadFileName());
					bean1.setSrNo(bean.getCheckEdit());
					if (bean.getHproof().equals("N"))
						bean1.setVproof("No");
					else
						bean1.setVproof("Yes");

				}  // end of if
				else {
					bean1.setSrNo(String.valueOf(i + 1)); // Sr No
					bean1.setVchCode(String.valueOf(voucherHeadCode[i])); // Voucher Head Code
					bean1.setVoucherHead(String.valueOf(voucherHeadName[i])); // Voucher  Head  Name
					bean1.setVamt(Utility.twoDecimals(String.valueOf(amount[i]))); // Voucher amount
					bean1.setVrem(String.valueOf(remark[i])); // Voucher Remark
					bean1.setVproof(String.valueOf(proof[i]));
					bean1.setUploadFile(String.valueOf(upload[i]));
					total += Double.parseDouble(String.valueOf(amount[i]));
					bean.setTotalamount(String.valueOf(total));
				} // end of else

				tableList.add(bean1);
				// tableList.remove(Integer.parseInt(bean.getCheckEdit())-1);
			} // end of fr loop

		} // end of if

		try {
			total += Double.parseDouble(String.valueOf(bean.getVamount()));

		} catch (NumberFormatException e) {
			logger.info("exception in editVoucher()"+e);
		}
		bean.setTotalamount(testNumberFormat.format(total));
		bean.setList(tableList);
		if (tableList.size() != 0)
			bean.setTableLength("1");
		else
			bean.setTableLength("");
		bean.setList(tableList);

	}
	/* 
	 * method name : setApprover 
	 * purpose     : set the previous approver details with comments in the list
	 * return type : void
	 * parameter   : CashVoucherMaster instance
	 */
	public void setApprover(CashVoucherMaster bean) {
		String query = "SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(APPR_DATE,'DD-MM-YYYY'),NVL(VOUCHER_REMARK,' ') "
			+ " ,DECODE(VOUCHER_STATUS,'P','Pending','R','Rejected','A','Approved')FROM HRMS_VOUCHER_PATH "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) "
			+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
			+ " WHERE VOUCHER_CODE=" + bean.getVCode() +" ORDER BY APPR_DATE";
		Object apprData[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> apprList = new ArrayList<Object>();
		logger.info("apprData is   "+apprData.length);

		try {
			if(apprData != null && apprData.length !=0){
				/*
				 * set the retrieved data in the list
				 * 
				 */
				for (int i = 0; i < apprData.length; i++) {
					CashVoucherMaster bean1 = new CashVoucherMaster();

					bean1.setApprName(String.valueOf(apprData[i][1]));
					bean1.setApprDate(String.valueOf(apprData[i][2]));
					bean1.setApprComments(String.valueOf(apprData[i][3]));
					bean1.setAppStatus(String.valueOf(apprData[i][4]));
					apprList.add(bean1);
				} // end of for loop
			} // end of if
			else bean.setCommentFlag("true");
			logger.info("comment flag is   "+bean.getCommentFlag());
			bean.setApproveList(apprList);

		} catch (Exception e) {
			logger.info("exception in setApprover()"+e);
		}
	}
	
	public Object[][] getVoucherApplication(String alertApplnID) {
		Object[][] voucherApplnObj = null;
		try {
			String voucherApplnSql = " SELECT HRMS_EMP_OFFC.EMP_ID, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, " +
			" NVL(HRMS_RANK.RANK_NAME,'') DSIGNATION, NVL(HRMS_DEPT.DEPT_NAME,'') DEPARTMENT, NVL(CADRE_NAME,'') GRADE, EMP_TOKEN, " +
			" NVL(STATUS,'P') STATUS, NVL(VCH_DETAILS,'') DETAILS, VCH_TOTALAMT, APPL_LEVEL FROM HRMS_VOUCHER_APPL " +
			" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_VOUCHER_APPL.EMP_CODE) " +
			" INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) " +
			" INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) " +
			" INNER JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_CADRE = HRMS_CADRE.CADRE_ID) " +
			" INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " +
			" INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) " +
			" WHERE VOUCHER_APPL_CODE = " + alertApplnID;
			voucherApplnObj = getSqlModel().getSingleResult(voucherApplnSql);
		} catch (Exception e) {
			logger.error(e);
		}
		return voucherApplnObj;
	}
	
	public Object[][] getVoucherDetails(String alertApplnID) {
		Object[][] voucherDetailsObj = null;
		try {
			String voucherDetailsSql = " SELECT VCH_NAME, VCHDTL_AMOUNT, NVL(VCHDTL_REMARK,''), PROOF_FLAG, NVL(VCH_UPLOADED_FILE, '') " +
			" FROM HRMS_VCHDTL " +
			" INNER JOIN HRMS_VCH_HD ON (HRMS_VCH_HD.VCH_CODE = HRMS_VCHDTL.VCH_CODE) " +
			" INNER JOIN HRMS_VOUCHER_APPL ON (HRMS_VOUCHER_APPL.VOUCHER_APPL_CODE = HRMS_VCHDTL.VOUCHER_APPL_CODE) " +
			" WHERE HRMS_VCHDTL.VOUCHER_APPL_CODE = " + alertApplnID;
			voucherDetailsObj = getSqlModel().getSingleResult(voucherDetailsSql);
		} catch (Exception e) {
			logger.error(e);
		}
		return voucherDetailsObj;
	}
	
	public void showVoucherList(CashVoucherMaster bean, HttpServletRequest request){
		try {
			logger.info("inside the showVoucherList");
			String[] srNo = request.getParameterValues("srNo");
			String[] voucherHeadCode = request.getParameterValues("vchCode");
			String[] voucherHeadName = request
					.getParameterValues("VoucherHead");
			String[] amount = request.getParameterValues("vamt");
			String[] remark = request.getParameterValues("vrem");
			String[] proof = request.getParameterValues("vproof");
			String[] upload = request.getParameterValues("uploadFile");
			ArrayList<Object> vchList = new ArrayList<Object>();
			if (srNo != null || srNo.length != 0) {
				for (int i = 0; i < srNo.length; i++) {
					CashVoucherMaster listBean = new CashVoucherMaster();
					listBean.setSrNo(checkNull(srNo[i]));
					listBean.setVamt(checkNull(amount[i]));
					listBean.setVchCode(checkNull(voucherHeadCode[i]));
					listBean.setVoucherHead(checkNull(voucherHeadName[i]));
					listBean.setVrem(checkNull(remark[i]));
					listBean.setVproof(checkNull(proof[i]));
					listBean.setUploadFile(checkNull(upload[i]));

					vchList.add(listBean);
				}

				bean.setList(vchList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Anantha lakshmi
	
	public void getAllTypeOfApplications(CashVoucherMaster cvoucher,	HttpServletRequest request, String empId) {
			
			
			try {
		 
				String query = " SELECT VOUCHER_TYPE, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(APP_DATE,'DD-MM-YYYY'), "  
				 +" VCH_TOTALAMT,DECODE(STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','C','Canceled','B','Sent Back') , EMP_CODE, VOUCHER_APPL_CODE, APPROVED_BY "  
				 +" FROM HRMS_VOUCHER_APPL  "
				 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) "  
				 +" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
				 +" WHERE  HRMS_VOUCHER_APPL.EMP_CODE=? AND STATUS=?";
				query += " ORDER BY VOUCHER_APPL_CODE DESC ";
				
				
				Object draftParam[] = null;
				draftParam = new Object[] {empId,"D" };
				Object[][] draftData = getSqlModel().getSingleResult(query,draftParam);
				ArrayList DraftList = new ArrayList();
				if (draftData != null && draftData.length > 0) {
					
					for (int i = 0; i < draftData.length; i++) {
						CashVoucherMaster cashVoucherBean = new CashVoucherMaster();
						cashVoucherBean.setType(checkNull(String.valueOf(draftData[i][0])));
						cashVoucherBean.setEname(checkNull(String.valueOf(draftData[i][1])));
						cashVoucherBean.setVchDate(checkNull(String.valueOf(draftData[i][2])));
						cashVoucherBean.setTotalamount(checkNull(String.valueOf(draftData[i][3])));
						cashVoucherBean.setStatus(checkNull(String.valueOf(draftData[i][4])));
						cashVoucherBean.setEmpCode(checkNull(String.valueOf(draftData[i][5])));
						cashVoucherBean.setVCode(checkNull(String.valueOf(draftData[i][6])));
						DraftList.add(cashVoucherBean);
					}
					cvoucher.setDraftList(DraftList);
				}else{
					cvoucher.setType("");
					cvoucher.setEname("");
					cvoucher.setVchDate("");
					cvoucher.setTotalamount("");
					cvoucher.setStatus("");
					cvoucher.setEmpCode("");
					cvoucher.setVCode("");
					cvoucher.setDraftList(DraftList);
				}
				
				
				String submitDataquery = " SELECT VOUCHER_TYPE, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(APP_DATE,'DD-MM-YYYY'), "  
					 +" VCH_TOTALAMT,DECODE(STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','C','Canceled','B','Sent Back') , EMP_CODE, VOUCHER_APPL_CODE, APPROVED_BY "  
					 +" FROM HRMS_VOUCHER_APPL  "
					 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) "  
					 +" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
					 +" WHERE  HRMS_VOUCHER_APPL.EMP_CODE=? AND STATUS=?";
				     submitDataquery += " ORDER BY VOUCHER_APPL_CODE DESC ";
				
				Object submitParam[] = null;
				//submitParam = new Object[] {empId};
				submitParam = new Object[] {empId,"P" };
				Object[][] submitData = getSqlModel().getSingleResult(submitDataquery,submitParam);
				//Object submitData[][] = getSqlModel().getSingleResult(submitDataquery,submitParam);
			 
				if (submitData != null && submitData.length > 0) {
					ArrayList SubmitList = new ArrayList();
					for (int i = 0; i < submitData.length; i++) {
						CashVoucherMaster cashVoucherBean = new CashVoucherMaster();
						cashVoucherBean.setType(checkNull(String.valueOf(submitData[i][0])));
						cashVoucherBean.setEname(checkNull(String.valueOf(submitData[i][1])));
						cashVoucherBean.setVchDate(checkNull(String.valueOf(submitData[i][2])));
						cashVoucherBean.setTotalamount(checkNull(String.valueOf(submitData[i][3])));
						cashVoucherBean.setStatus(checkNull(String.valueOf(submitData[i][4])));
						cashVoucherBean.setEmpCode(checkNull(String.valueOf(submitData[i][5])));
						cashVoucherBean.setVCode(checkNull(String.valueOf(submitData[i][6])));
						SubmitList.add(cashVoucherBean);
					}
					cvoucher.setSubmitList(SubmitList);
				}
				
				String sendBackDataquery = " SELECT VOUCHER_TYPE, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(APP_DATE,'DD-MM-YYYY'), "  
					 +" VCH_TOTALAMT,DECODE(STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','C','Canceled','B','Sent Back') , EMP_CODE, VOUCHER_APPL_CODE, APPROVED_BY "  
					 +" FROM HRMS_VOUCHER_APPL  "
					 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) "  
					 +" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
					 +" WHERE  HRMS_VOUCHER_APPL.EMP_CODE=? AND STATUS=?";
				     sendBackDataquery += " ORDER BY VOUCHER_APPL_CODE DESC ";
				
				Object sendBackParam[] = null;
				//submitParam = new Object[] {empId};
				sendBackParam = new Object[] {empId,"B" };
				Object[][] sendBackData = getSqlModel().getSingleResult(sendBackDataquery,sendBackParam);
				//Object submitData[][] = getSqlModel().getSingleResult(submitDataquery,submitParam);
			 
				if (sendBackData != null && sendBackData.length > 0) {
					ArrayList sendBackList = new ArrayList();
					for (int i = 0; i < sendBackData.length; i++) {
						CashVoucherMaster cashVoucherBean = new CashVoucherMaster();
						cashVoucherBean.setType(checkNull(String.valueOf(sendBackData[i][0])));
						cashVoucherBean.setEname(checkNull(String.valueOf(sendBackData[i][1])));
						cashVoucherBean.setVchDate(checkNull(String.valueOf(sendBackData[i][2])));
						cashVoucherBean.setTotalamount(checkNull(String.valueOf(sendBackData[i][3])));
						cashVoucherBean.setStatus(checkNull(String.valueOf(sendBackData[i][4])));
						cashVoucherBean.setEmpCode(checkNull(String.valueOf(sendBackData[i][5])));
						cashVoucherBean.setVCode(checkNull(String.valueOf(sendBackData[i][6])));
						sendBackList.add(cashVoucherBean);
					}
					cvoucher.setSendBackList(sendBackList);
				}
			
			
			} catch (Exception e) {
				logger.error("Exception in getAllTypeOfApplications------" + e);
			}
		
		}
	
public void getApprovedList(CashVoucherMaster bean,HttpServletRequest request, String empId) {
			try {

				Object approvedParam[] = null;
				approvedParam = new Object[] { empId,"A"};	
				String query = " SELECT VOUCHER_TYPE, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(APP_DATE,'DD-MM-YYYY'), "  
					 +" VCH_TOTALAMT,DECODE(STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','C','Canceled') , EMP_CODE, VOUCHER_APPL_CODE, APPROVED_BY "  
					 +" FROM HRMS_VOUCHER_APPL  "
					 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) "  
					 +" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
					 +" WHERE  HRMS_VOUCHER_APPL.EMP_CODE=? AND STATUS=?";
					query += " ORDER BY VOUCHER_APPL_CODE DESC ";
				Object[][] approvedData = getSqlModel().getSingleResult(query,approvedParam);
				
				String[] pageIndexApproved = Utility.doPaging(bean.getMyPage(),approvedData.length, 20);
				if (pageIndexApproved == null) {
					pageIndexApproved[0] = "0";
					pageIndexApproved[1] = "20";
					pageIndexApproved[2] = "1";
					pageIndexApproved[3] = "1";
					pageIndexApproved[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
				request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
				if (pageIndexApproved[4].equals("1"))
					bean.setMyPage("1");

				if(approvedData != null && approvedData.length > 0) {
						bean.setApproveLength("true");
						ArrayList approvedList = new ArrayList();
						for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
							CashVoucherMaster cashVoucherBean = new CashVoucherMaster();
							cashVoucherBean.setType(checkNull(String.valueOf(approvedData[i][0])));
							cashVoucherBean.setEname(checkNull(String.valueOf(approvedData[i][1])));
							cashVoucherBean.setVchDate(checkNull(String.valueOf(approvedData[i][2])));
							cashVoucherBean.setTotalamount(checkNull(String.valueOf(approvedData[i][3])));
							cashVoucherBean.setStatus(checkNull(String.valueOf(approvedData[i][4])));
							cashVoucherBean.setEmpCode(checkNull(String.valueOf(approvedData[i][5])));
							cashVoucherBean.setVCode(checkNull(String.valueOf(approvedData[i][6])));
							approvedList.add(cashVoucherBean);
						}
						bean.setApprovedList(approvedList);
					}
				
			} catch (Exception e) {
				logger.error("Exception in getApprovedList------" + e);
				e.printStackTrace();
			}

		}
		public void getRejectedList(CashVoucherMaster bean,HttpServletRequest request, String empId) {

			try {
				Object rejectedParam[] = null;
				rejectedParam = new Object[] {  empId,"R" };
				String query = " SELECT VOUCHER_TYPE, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(APP_DATE,'DD-MM-YYYY'), "  
					 +" VCH_TOTALAMT,DECODE(STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','C','Canceled') , EMP_CODE, VOUCHER_APPL_CODE, APPROVED_BY "  
					 +" FROM HRMS_VOUCHER_APPL  "
					 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_VOUCHER_APPL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) "  
					 +" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
					 +" WHERE  HRMS_VOUCHER_APPL.EMP_CODE=? AND STATUS=?";
					query += " ORDER BY VOUCHER_APPL_CODE DESC ";
				Object rejectedData[][] = getSqlModel().getSingleResult(query,rejectedParam);

				String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(), rejectedData.length, 20);
				if (pageIndexRejected == null) {
					pageIndexRejected[0] = "0";
					pageIndexRejected[1] = "20";
					pageIndexRejected[2] = "1";
					pageIndexRejected[3] = "1";
					pageIndexRejected[4] = "";
				}

				request.setAttribute("totalPageRejected", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
				request.setAttribute("PageNoRejected", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
				if (pageIndexRejected[4].equals("1"))
					bean.setMyPageRejected("1");

				if (rejectedData != null && rejectedData.length > 0) {
					bean.setRejectedLength("true");
					ArrayList rejectedList = new ArrayList();
					for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
						CashVoucherMaster cashVoucherRejectBean = new CashVoucherMaster();
						cashVoucherRejectBean.setType(checkNull(String.valueOf(rejectedData[i][0])));
						cashVoucherRejectBean.setEname(checkNull(String.valueOf(rejectedData[i][1])));
						cashVoucherRejectBean.setVchDate(checkNull(String.valueOf(rejectedData[i][2])));
						cashVoucherRejectBean.setTotalamount(checkNull(String.valueOf(rejectedData[i][3])));
						cashVoucherRejectBean.setStatus(checkNull(String.valueOf(rejectedData[i][4])));
						cashVoucherRejectBean.setEmpCode(checkNull(String.valueOf(rejectedData[i][5])));
						cashVoucherRejectBean.setVCode(checkNull(String.valueOf(rejectedData[i][6])));
						rejectedList.add(cashVoucherRejectBean);
					}
					bean.setRejectedList(rejectedList);
				}

			} catch (Exception e) {
				logger.error("Exception in getRejectedList------" + e);
			}

		}
	public boolean sendForApprovalRecord(CashVoucherMaster bean,String appId) {
		Object addObj[][] = new Object[1][3];
		boolean result=false;
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysDate = formater.format(date);
			addObj[0][0] = "P";
			addObj[0][1] = sysDate;
			addObj[0][2] = appId;
			result = getSqlModel().singleExecute(getQuery(14), addObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode,String[] empToken, String[] empName, CashVoucherMaster cvoucher) {

		try {
			ArrayList<CashVoucherMaster> cvList = new ArrayList<CashVoucherMaster>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					CashVoucherMaster cvApp = new CashVoucherMaster();
					cvApp.setKeepInformedEmpCode(empCode[i]);
					cvApp.setKeepInformedEmpId(empToken[i]);
					cvApp.setKeepInformedEmpName(empName[i]);
					cvApp.setSerialNo(srNo[i]);// sr no
					cvList.add(cvApp);
				}
				cvoucher.setKeepInformedList(cvList);

			}
		} catch (Exception e) {
			logger.error("Exception in displayIteratorValueForKeepInformed----------"+ e);
		}

	}
	public void setKeepInformed(String[] srNo, String[] empCode,String[] empToken,
			String[] empName, CashVoucherMaster cvoucher) {

		try {
			CashVoucherMaster cvApp = new CashVoucherMaster();
			cvApp.setKeepInformedEmpCode(cvoucher.getKiEmpCode());
			cvApp.setKeepInformedEmpId(cvoucher.getKiEmpToken());
			cvApp.setKeepInformedEmpName(cvoucher.getKiEmpName());
			ArrayList<CashVoucherMaster> cvList = displayNewValueForKeepInformed(srNo, empCode,empToken, empName, cvoucher);
			cvApp.setSerialNo(String.valueOf(cvList.size() + 1));// sr no
			cvList.add(cvApp);
			cvoucher.setKeepInformedList(cvList);
		} catch (Exception e) {
			logger.error("Exception in setKeepInformed----------" + e);
		}
	}
	private ArrayList<CashVoucherMaster> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode,String[] empToken, String[] empName,CashVoucherMaster cvoucher) {
		ArrayList<CashVoucherMaster> cvList = null;
		try {
			cvList = new ArrayList<CashVoucherMaster>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					CashVoucherMaster cvbean = new CashVoucherMaster();
					cvbean.setKeepInformedEmpCode(empCode[i]);
					cvbean.setKeepInformedEmpId(empToken[i]);
					cvbean.setKeepInformedEmpName(empName[i]);
					cvbean.setSerialNo(srNo[i]);
					cvList.add(cvbean);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueForKeepInformed----------" + e);
		}
		return cvList;
	}
	public void setApproverData(CashVoucherMaster cvoucher,Object[][] empFlow) {
		try {
			
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				System.out.println("=========IN M==========");
				for (int i = 0; i < empFlow.length; i++) {
					
					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";
						    //+ " WHERE EMP_TOKEN IN('" + empFlow[i][0] + "')";

					Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						CashVoucherMaster cvBean = new CashVoucherMaster();
						cvBean.setApproverName(String.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)+ "-Approver";
						cvBean.setSrNoIterator(srNo);
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
	public void removeKeepInformedData(String[] serialNo, String[] empCode,String[] empToken,
			String[] empName, CashVoucherMaster cvoucher,String removeEmpId,String vchCode) {
		try {
			System.out.println("CASH VOUCHER CODE"+vchCode);
			ArrayList<Object> tableList = new ArrayList<Object>();
			String qry="SELECT VOUCHER_KEEP_INFORM FROM  HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE="+vchCode;
			Object[][] keepInfEmpIdObj=getSqlModel().getSingleResult(qry); 
			if(keepInfEmpIdObj!=null){
				System.out.println("-------IF------------");
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
				String upateQry="UPDATE  HRMS_VOUCHER_APPL SET  VOUCHER_KEEP_INFORM='"+employeeId+"' WHERE VOUCHER_APPL_CODE="+cvoucher.getVCode();
				getSqlModel().singleExecute(upateQry); 
			}
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {
					CashVoucherMaster bean = new CashVoucherMaster();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setKeepInformedEmpCode(empCode[i]);
					bean.setKeepInformedEmpId(empToken[i]);
					bean.setKeepInformedEmpName(empName[i]);
					tableList.add(bean);
				}
				System.out.println("--------TABLELIST------------"+tableList.size());
				tableList.remove(Integer.parseInt(cvoucher.getCheckRemove()) - 1);
			}
			cvoucher.setKeepInformedList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformedData------" + e);
		}

	}
	public String onlineApproveRejectSendBackAppl(CashVoucherMaster cvoucher){
		String result = "";
		result = "Application has been approved ";
		return result;
	}
	public void getAppCode(CashVoucherMaster cvoucher){
		String query = "SELECT NVL(MAX(VOUCHER_APPL_CODE),0) FROM HRMS_VOUCHER_APPL";
		Object[][] resultCode = getSqlModel().getSingleResult(query);
		if(resultCode!=null)
			cvoucher.setVCode(String.valueOf(resultCode[0][0])); 
	}
	
	public void AdminMgrAccountAppr(CashVoucherMaster cvoucher){
		String query = "SELECT CONF_CASH_MGR_FLAG,CONF_CASH_ADMIN_FLAG,CONF_CASH_ACC_FLAG FROM HRMS_ADMIN_CONF";
		Object[][] resultCode = getSqlModel().getSingleResult(query);
		if(resultCode!=null && resultCode.length>0){
			cvoucher.setVoucherMgrFlag(String.valueOf(resultCode[0][0]));
			cvoucher.setVoucherAdminFlag(String.valueOf(resultCode[0][1]));
			cvoucher.setVoucherAcctFlag(String.valueOf(resultCode[0][2]));
		}else{
			cvoucher.setVoucherMgrFlag("");
			cvoucher.setVoucherAdminFlag("");
			cvoucher.setVoucherAcctFlag("");
		}
		
	}
	
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String approveRejectApplication(HttpServletRequest request,String vCode,
			String appStatus, int level, String applicantId, String isManagerAppr,String isAdminFlag,
			String isAcctFlag, String approverComments,String currentUser,String adminStatus) {
		String applicationStatus="pending";
		String status="";
		String forwardedTo = "";
		boolean result = false;
		Object[][] empFlow = null;
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		try {
			//level+=1;
			
			if (String.valueOf(appStatus).equals("A")) {
				if(isManagerAppr.equals("Y")){
					empFlow = generateEmpFlow(applicantId, "Cash", (level));
					status="A";
					if (empFlow != null && empFlow.length>0) {
						appStatus = "P";
					
					}
				}
				
				if((appStatus.equals("A"))&& (isAdminFlag.equals("Y")) &&(adminStatus.equals("N"))){
					String ownerQuery = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT "+
					"EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+applicantId+")	AND CONF_TYPE='CashAdmin'";
					empFlow = model.getSqlModel().getSingleResult(ownerQuery);
					adminStatus="Y";
					appStatus="P";
					status="A";
					if(empFlow != null && String.valueOf(empFlow[0][0]).equals(currentUser)){ 
						 ownerQuery = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT "+
						"EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+applicantId+")	AND CONF_TYPE='CashAccount'";
						empFlow = model.getSqlModel().getSingleResult(ownerQuery);
					}
					
					
				}
				if(appStatus.equals("A")&& (isAcctFlag.equals("Y"))){
					String ownerQuery = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT "+
					"EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+applicantId+")	AND CONF_TYPE='CashAccount'";
					empFlow = model.getSqlModel().getSingleResult(ownerQuery);
				}
				if (empFlow != null && empFlow.length>0) {
					forwardedTo = String.valueOf(empFlow[0][0]);
				}else{
					forwardedTo = "0";
				}
				
				String updateApprove = " UPDATE HRMS_VOUCHER_APPL SET STATUS='"+appStatus+"', VOUCHER_MGR_STATUS = '" 
					+"A"
					+ "' ,VOUCHER_ADMIN_STATUS='"+adminStatus +"', VOUCHER_PENDING_WITH= "+forwardedTo+" ,APPL_LEVEL="+(level)+" WHERE VOUCHER_APPL_CODE = "
					+vCode;
				
				result = getSqlModel().singleExecute(updateApprove);
				applicationStatus = "approved";
				//sendForwardNotificationMail(request, requestId, initiatorId, forwardedTo, isManagerAppr, deptId, reqTypeId, currentUser, level);
				
			}else if(String.valueOf(appStatus).equals("B")){
				forwardedTo = applicantId;
				status="B";
				level=1;
				String updateSendBack = " UPDATE HRMS_VOUCHER_APPL SET VOUCHER_MGR_STATUS = '" 
					+appStatus
					+ "' , VOUCHER_PENDING_WITH= "+forwardedTo+" , APPL_LEVEL="+(level)+" WHERE VOUCHER_APPL_CODE = "
					+vCode;
				
				result = getSqlModel().singleExecute(updateSendBack);
				applicationStatus = "sendback";
			}else{
				forwardedTo = applicantId;
				status="R";
				String updateReject = " UPDATE HRMS_VOUCHER_APPL SET VOUCHER_MGR_STATUS = '" 
					+appStatus
					+ "' , VOUCHER_PENDING_WITH= "+forwardedTo+", STATUS='N' WHERE VOUCHER_APPL_CODE = "
					+vCode;
				
				result = getSqlModel().singleExecute(updateReject);
				applicationStatus = "rejected";
			}
			if(result){
				saveApprovarDetails(currentUser, appStatus, vCode, approverComments);
				String maxQuery = " SELECT MAX(VOUCHER_APP_DTL_ID) FROM HRMS_VOUCHER_PATH ";
				Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
				String dtlId=String.valueOf(maxObj[0][0]);
				System.out.println("Detail ID"+dtlId);
				System.out.println("Voucher code"+vCode);
				if(appStatus.equals("A") || appStatus.equals("P")){

					sendForwardNotificationMail(request, vCode, applicantId, forwardedTo, isManagerAppr, currentUser);
					
					}
				sendForwardNotificationToApplicant(status, vCode,dtlId,applicantId,isManagerAppr,forwardedTo,currentUser);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicationStatus;
	}
	
	
	public boolean saveApprovarDetails(String currentUser,String appStatus,String vchCode,String approverComments) {
		boolean result = false;
		Object[][] dtlObj = new Object[1][4];
		dtlObj[0][0] = vchCode;// VOUCHER_ID
		dtlObj[0][1] = currentUser;// VOUCHER_ACTION_BY
		dtlObj[0][2] = approverComments;// VOUCHER_COMMENTS
		if(appStatus.equals("B") || appStatus.equals("R"))
		dtlObj[0][3] = appStatus;
		else{
			dtlObj[0][3] = "A";
		}
		

		// INSERT INTO DTL TABLE
		String insertDtl ="INSERT INTO HRMS_VOUCHER_PATH (VOUCHER_CODE,APPROVER_CODE,APPR_DATE,VOUCHER_REMARK,VOUCHER_STATUS,VOUCHER_APP_DTL_ID) " +
				"VALUES(?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?,(SELECT NVL(MAX(VOUCHER_APP_DTL_ID),0)+1 FROM HRMS_VOUCHER_PATH))";
	
		result = getSqlModel().singleExecute(insertDtl, dtlObj);

		return result;
		
		
	}
	
	public void sendForwardNotificationMail(HttpServletRequest request, String vCode, 
			String applicantId, String managerId, String isManagerAppr, String userEmpId){
	try{
				/* 2.	Request Submit Notification to Request Owner */
				
				EmailTemplateBody templateForRequestApproval = new EmailTemplateBody();
				templateForRequestApproval.initiate(context, session);
				templateForRequestApproval.setEmailTemplate("CASH VOUCHER -APPROVER1 TO APPROVER2");
				templateForRequestApproval.getTemplateQueries();
				
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				String module = "Voucher";
				processAlerts.removeProcessAlert(String.valueOf(vCode),module);
				
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryForApproval1 = templateForRequestApproval
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryForApproval1.setParameter(1, userEmpId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval2 = templateForRequestApproval
							.getTemplateQuery(2);// To EMAIL
					templateQueryForApproval2.setParameter(1, managerId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval3 = templateForRequestApproval
					.getTemplateQuery(3);
					templateQueryForApproval3.setParameter(1, vCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval4 = templateForRequestApproval
					.getTemplateQuery(4);
					templateQueryForApproval4.setParameter(1, vCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForApproval5 = templateForRequestApproval
					.getTemplateQuery(5);
					templateQueryForApproval5.setParameter(1, vCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*try {
					// And so on
					EmailTemplateQuery templateQueryForApproval6 = templateForRequestApproval
					.getTemplateQuery(6);
					templateQueryForApproval6.setParameter(1, userEmpId);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				String ownerQuery = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=(SELECT "+
				"EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+applicantId+")	AND CONF_TYPE='CashAccount'";
				Object[][] managerIdObj = getSqlModel().getSingleResult(ownerQuery);
				
				String reqManagerId = "";
				
				if(managerIdObj != null && managerIdObj.length > 0 ){
					reqManagerId = String.valueOf(managerIdObj[0][0]);
				}
				try {
					String	alertLink="";
					String	linkParam="";
					templateForRequestApproval.configMailAlert();
					if(!(reqManagerId.equals(managerId))){
							alertLink = "/voucher/VoucherApplication_retriveForApproval.action";
							linkParam = "voucherNoView="+vCode+"&status=P";
					}else{
						alertLink = "/voucher/CashVoucher_getAccountantDetails.action";
						linkParam = "voucherNoView="+vCode ;
						
					}
					templateForRequestApproval.sendProcessManagerAlert(managerId, "Voucher", "A", vCode,
							"1", linkParam, alertLink, "Pending", applicantId,
							"", "", "", userEmpId);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				
				templateForRequestApproval.sendApplicationMail();
				templateForRequestApproval.clearParameters();
				templateForRequestApproval.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendForwardNotificationToApplicant(String status, String vCode,String dtlId, String applicant, 
			String isManagerAppr ,String managerID, String userEmpId) {

		try {
			String applicantLink="";
			String applicantLinkParam="";
			if(status.equals("R") || status.equals("B")){
				try{
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Voucher";
			processAlerts.removeProcessAlert(String.valueOf(vCode),module);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			String actualStataus = "";
			EmailTemplateBody template2 = new EmailTemplateBody();
			template2.initiate(context, session);
			if(status.equals("A")){
				
			template2.setEmailTemplate("CASH VOUCHER - APPROVER TO APPLICANT");
			
			template2.getTemplateQueries();
			
			EmailTemplateQuery templateQuery11 = template2.getTemplateQuery(1); //FROM EMAIL
			templateQuery11.setParameter(1, userEmpId);
			
			EmailTemplateQuery templateQuery12 = template2.getTemplateQuery(2); //TO EMAIL
			templateQuery12.setParameter(1, applicant);
			
			EmailTemplateQuery templateQuery13 = template2.getTemplateQuery(3);
			templateQuery13.setParameter(1, vCode);
			
			EmailTemplateQuery templateQuery14 = template2.getTemplateQuery(4);
			templateQuery14.setParameter(1, userEmpId);
			
			EmailTemplateQuery templateQuery15 = template2.getTemplateQuery(5);
			templateQuery15.setParameter(1, managerID);
			
			template2.configMailAlert();
			actualStataus = "Approved";
			}else if(status.equals("B") || status.equals("R") ) {
				
				template2.setEmailTemplate("CASH VOUCHER -APPROVER REJECT");
				
				template2.getTemplateQueries();
				
				EmailTemplateQuery templateQuery1 = template2.getTemplateQuery(1); //FROM EMAIL
				//templateQuery1.setParameter(1, oldApprover);
				templateQuery1.setParameter(1, userEmpId);
				
				EmailTemplateQuery templateQuery2 = template2.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, applicant);
				
				EmailTemplateQuery templateQuery3 = template2.getTemplateQuery(3);
				templateQuery3.setParameter(1, vCode);
				try{
				EmailTemplateQuery templateQuery4 = template2.getTemplateQuery(4);
				//templateQuery4.setParameter(1, oldApprover);
				templateQuery4.setParameter(1, vCode);
				templateQuery4.setParameter(2, dtlId);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				EmailTemplateQuery templateQuery5 = template2.getTemplateQuery(5);
				templateQuery5.setParameter(1, vCode);
				
				EmailTemplateQuery templateQuery6 = template2.getTemplateQuery(6);
				templateQuery6.setParameter(1, vCode);
				
				template2.configMailAlert();
				if(status.equals("B")){
				actualStataus = "SentBack";
				 applicantLink = "/voucher/CashVoucher_retrive.action";
				 applicantLinkParam = "vCode="+vCode+"&cvStatus=B";
				template2.sendProcessManagerAlert(applicant,
						"Voucher", "A", vCode, "1", applicantLinkParam, applicantLink,
						actualStataus, applicant, "", "", "", userEmpId);
				
				 applicantLink = "/voucher/VoucherApplication_retriveForApproval.action";
				 applicantLinkParam = "voucherNoView="+vCode+"&status=B";
				template2.sendProcessManagerAlert(userEmpId,
						"Voucher", "I", vCode, "1", applicantLinkParam, applicantLinkParam,
						actualStataus, applicant, "", "", "", userEmpId);
				}else{
					actualStataus = "Rejected";
				}
				
			}else if(status.equals("P")) {
				actualStataus = "Pending";
			}
			 applicantLink = "/voucher/VoucherApplication_retriveForApproval.action";
			 applicantLinkParam = "voucherNoView="+vCode+"&status=A";
			
			
			//SendManger ALert -- BEGINS
			 if(!status.equals("B")){
				template2.sendProcessManagerAlert(userEmpId,
						"Voucher", "I", vCode, "1", applicantLinkParam, applicantLink,
						actualStataus, applicant, "", applicant, applicant,
						userEmpId);
			 }
			//SendManger ALert -- ENDS
			template2.sendApplicationMail();
			template2.clearParameters();
			template2.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getAdminStatus(CashVoucherMaster cvoucher) {
		Object[][] adminStatusObj=null;
		String adminStatus="";
		CashVoucherModel model = new CashVoucherModel();
		model.initiate(context, session);
		String ownerQuery = "SELECT  VOUCHER_ADMIN_STATUS FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE="+cvoucher.getVCode();
		adminStatusObj = model.getSqlModel().getSingleResult(ownerQuery);
		if(adminStatusObj!=null && adminStatusObj.length>0)
		 adminStatus=String.valueOf(adminStatusObj[0][0]);
		model.terminate();
		return adminStatus;
	}
	
}