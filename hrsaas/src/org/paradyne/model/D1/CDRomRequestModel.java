package org.paradyne.model.D1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CDRomRequestBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;

/**
 * @author aa1439/aa1381.
 * 
 */
public class CDRomRequestModel extends ModelBase {
	
	/**
	 * Set 0.
	 */
	private static final String PAGE_ZERO = "0";
	/**
	 * Set 20.
	 */
	private static final String PAGE_TWENTY = "20";
	/**
	 * Set 1.
	 */
	private static final String PAGE_ONE = "1";
	/**
	 * Set Date.
	 */
	private static final String DATE = "dd-MM-yyyy";
	
	
	
	/**
	 * Logger.
	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeModel.class);

	/**
	 * @param cdromID - Used to get CD_ROM ID.
	 * @return Object.
	 */
	public Object[][] getApproverCommentList(final String cdromID) {

		final String apprCommentListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CDROM_COMMENTS, " + " TO_CHAR(CDROM_DATE, 'DD-MM-YYYY') AS CDROM_DATE, " + " DECODE(CDROM_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') " + " AS STATUS " + " FROM HRMS_D1_CDROM_DATA_PATH PATH " + " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.CDROM_APPROVER) " + " WHERE CDROM_ID = " + cdromID + " ORDER BY CDROM_ID DESC ";
		return this.getSqlModel().getSingleResult(apprCommentListSql);
	}

	/**
	 * @param userId - Used to get user emp id.
	 * @return true/false.
	 */
	public boolean isCurrentUser(final String userId) {
		final String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
		final Object[][] currentUserObj = this.getSqlModel().getSingleResult(currentUserSql);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * List Functionality-  For drafted application.
	 */ 
	
	
	/**
	 * @param bean - used to get page value & set list.
	 * @param request - Used to set total page & page no for draft list,used to set totalInProcessPage & ProcessPageNo for pending list, totalSentBackPage & sentBackPageNo for send back list .
	 * @param userId - used to get initiator.
	 */
	public void getPendingList(final CDRomRequestBean bean, final HttpServletRequest request, final String userId) {
		try {
			System.out.println("inside getPendingList");
			Object[][] draftListData = null;
			final List<CDRomRequestBean> draftList = new ArrayList<CDRomRequestBean>();

			Object[][] inProcessListData = null;

			Object[][] sentBackListData = null;
			final List<CDRomRequestBean> sentBackVoucherList = new ArrayList<CDRomRequestBean>();

			final String selQuery = " select CDROM_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(CDROM_APP_DATE,'DD-MM-YYYY'), CDROM_APPROVER_STATUS " + " ,CDROM_ID,CDROM_FILE_HEADER_NAME from HRMS_D1_CDROM_REQUEST " + "  inner join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" + " where CDROM_APPROVER_STATUS  = 'D' AND CDROM_INITIATOR = " + userId + " " + "ORDER BY CDROM_APP_DATE DESC ";
			draftListData = this.getSqlModel().getSingleResult(selQuery);

			final String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = CDRomRequestModel.PAGE_ZERO;
				pageIndexDrafted[1] = CDRomRequestModel.PAGE_TWENTY;
				pageIndexDrafted[2] = CDRomRequestModel.PAGE_ONE;
				pageIndexDrafted[3] = CDRomRequestModel.PAGE_ONE;
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if (CDRomRequestModel.PAGE_ONE.equals(pageIndexDrafted[4])) {
				bean.setMyPage(CDRomRequestModel.PAGE_ONE);
			}

			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftListLength(true);
				
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					final CDRomRequestBean beanItt = new CDRomRequestBean();
					beanItt.setAuthorizedToken(this.checkNull(String.valueOf(draftListData[i][6])));
					beanItt.setCdRomID(this.checkNull(String.valueOf(draftListData[i][5])));
					beanItt.setEmpToken(this.checkNull(String.valueOf(draftListData[i][1])));
					beanItt.setEmpName(this.checkNull(String.valueOf(draftListData[i][2])));
					beanItt.setApplicationDate(this.checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setApplnStatus(this.checkNull(String.valueOf(draftListData[i][4])));
					draftList.add(beanItt);
				}
				bean.setDraftList(draftList);
				
				System.out.println("inside getPendingList---"	+ bean.getDraftList().size());
			}
		
			/**
			 *  List Functionality - For in-Process application Begins.
			 */
		
			final String pendingQuery = " select CDROM_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(CDROM_APP_DATE,'DD-MM-YYYY'), CDROM_APPROVER_STATUS " + " ,CDROM_ID,CDROM_FILE_HEADER_NAME from HRMS_D1_CDROM_REQUEST " + "  inner join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" + "  where CDROM_APPROVER_STATUS IN('P','F') AND CDROM_INITIATOR = " + userId + " " + "ORDER BY CDROM_APP_DATE DESC ";

			inProcessListData = this.getSqlModel().getSingleResult(pendingQuery);
			final String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = CDRomRequestModel.PAGE_ZERO;
				pageIndexInProcess[1] = CDRomRequestModel.PAGE_TWENTY;
				pageIndexInProcess[2] = CDRomRequestModel.PAGE_ONE;
				pageIndexInProcess[3] = CDRomRequestModel.PAGE_ONE;
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if (CDRomRequestModel.PAGE_ONE.equals(pageIndexInProcess[4])) {
				bean.setMyPageInProcess(CDRomRequestModel.PAGE_ONE);
			}

			if (inProcessListData != null && inProcessListData.length > 0) {
				final List<CDRomRequestBean> inProcessList = new ArrayList<CDRomRequestBean>();

				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					
					System.out.println("in for Loop");
					
					final CDRomRequestBean beanItt1 = new CDRomRequestBean();
					beanItt1.setAuthorizedToken(this.checkNull(String.valueOf(inProcessListData[i][6])));
					beanItt1.setCdRomID(this.checkNull(String.valueOf(inProcessListData[i][5])));
					beanItt1.setEmpToken(this.checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt1.setEmpName(this.checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt1.setApplicationDate(this.checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt1.setApplnStatus(this.checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessList.add(beanItt1);
				}
				
				bean.setInProcessListLength(true);
				bean.setApplicationList(inProcessList);
				
			}

			/**
			 * Functionality - Sent-Back application Begins.
			 */ 

			final String sentBackQuery = " select CDROM_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(CDROM_APP_DATE,'DD-MM-YYYY'), CDROM_APPROVER_STATUS " + " ,CDROM_ID,CDROM_FILE_HEADER_NAME  from HRMS_D1_CDROM_REQUEST " + "  inner join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" + "  where CDROM_APPROVER_STATUS  = 'B' AND CDROM_INITIATOR = " + userId + " " + "ORDER BY CDROM_APP_DATE DESC ";

			sentBackListData = this.getSqlModel().getSingleResult(sentBackQuery);

			final String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = CDRomRequestModel.PAGE_ZERO;
				pageIndexSentBack[1] = CDRomRequestModel.PAGE_TWENTY;
				pageIndexSentBack[2] = CDRomRequestModel.PAGE_ONE;
				pageIndexSentBack[3] = CDRomRequestModel.PAGE_ONE;
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String.valueOf(pageIndexSentBack[3])));
			if (CDRomRequestModel.PAGE_ONE.equals(pageIndexSentBack[4])) {
				bean.setMyPageSentBack(CDRomRequestModel.PAGE_ONE);
			}
			if (sentBackListData != null && sentBackListData.length > 0) {

				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
				
					final CDRomRequestBean beanItt = new CDRomRequestBean();
					
					beanItt.setAuthorizedToken(this.checkNull(String.valueOf(sentBackListData[i][6])));
					beanItt.setCdRomID(this.checkNull(String.valueOf(sentBackListData[i][5])));
					beanItt.setEmpToken(this.checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setEmpName(this.checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setApplicationDate(this.checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setApplnStatus(this.checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSendBackLength(true);
				bean.setSendBackList(sentBackVoucherList);
				System.out.println("in send back list here---" + bean.getSendBackList().size());
			}
			

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * @param result - used to check whether the data is null or not.
	 * @return String.
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param empId - Used to set employee id.
	 * @param bean - Used to set employee details.
	 */
	public void getEmployeeDetails(final String empId, final CDRomRequestBean bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;

			final String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " + " HRMS_D1_DEPARTMENT.DEPT_NUMBER , " + " HRMS_LOCATION.LOCATION_NAME,HRMS_EMP_ADDRESS.ADD_PH1 ,HRMS_EMP_OFFC.EMP_ID,EMP_REPORTING_OFFICER " + " FROM HRMS_EMP_OFFC " + " Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) " + " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " + " LEFT JOIN HRMS_LOCATION ON(HRMS_EMP_ADDRESS.ADD_CITY=HRMS_LOCATION.LOCATION_CODE)" + " WHERE HRMS_EMP_OFFC.EMP_ID =" + empId;

			final Object[][] empData = this.getSqlModel().getSingleResult(query);
			bean.setEmployeeToken(this.checkNull(String.valueOf(empData[0][0])));
			bean.setEmployeeName(this.checkNull(String.valueOf(empData[0][1])));
			bean.setDeptNo(this.checkNull(String.valueOf(empData[0][2])));
			bean.setOfficeLocation(this.checkNull(String.valueOf(empData[0][3])));
			bean.setPhNo(this.checkNull(String.valueOf(empData[0][4])));
			bean.setEmployeeCode(this.checkNull(String.valueOf(empData[0][5])));
			bean.setFirstApproverCode(this.checkNull(String.valueOf(empData[0][6])));
			this.getManagerName(bean);

			/**
			 *  Set Completed on and completed by
			 */
	
			final String dateQuery = "SELECT to_char(sysdate,'dd-mm-yyyy HH24:MI') from dual ";
			final Object[][] dateObj = this.getSqlModel().getSingleResult(dateQuery);
			if (dateObj != null && dateObj.length > 0) {
				bean.setCompletedDate(String.valueOf(dateObj[0][0]));
			}
			bean.setCompletedByCode(bean.getEmployeeCode());
			bean.setCompletedBy(bean.getEmployeeName());
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param bean - 
	 */
	public void getManagerName(final CDRomRequestBean bean) {
		Object[][] data = null;
		String query = "";
		if (bean.getFirstApproverCode() != null && !"".equals(bean.getFirstApproverCode())) {
			query = " SELECT EMP_TOKEN,to_char(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME),EMP_ID from HRMS_EMP_OFFC " + " where EMP_ID=" + bean.getFirstApproverCode();
			data = this.getSqlModel().getSingleResult(query);
			if (data.length > 0) {
				bean.setFirstApproverToken(this.checkNull(String.valueOf(data[0][0])));
				bean.setFirstApproverName(this.checkNull(String.valueOf(data[0][1])));
				bean.setFirstApproverCode(this.checkNull(String.valueOf(data[0][2])));
			}
		} else {
			bean.setFirstApproverToken("");
			bean.setFirstApproverName("");
			bean.setFirstApproverCode("");
		}
	}

	/**
	 * @param bean - Used to get employee code.
	 */
	public void getEmployeeDetails(final CDRomRequestBean bean) {

		final String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " + " HRMS_D1_DEPARTMENT.DEPT_NUMBER , " + " hrms_dept.DEPT_NAME ,HRMS_LOCATION.LOCATION_NAME,HRMS_EMP_ADDRESS.ADD_PH1 ,HRMS_EMP_OFFC.EMP_ID" + " FROM HRMS_EMP_OFFC " + " Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) " + " LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " + " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " 	+ " LEFT JOIN HRMS_LOCATION ON(HRMS_EMP_ADDRESS.ADD_CITY=HRMS_LOCATION.LOCATION_CODE) " + " WHERE HRMS_EMP_OFFC.EMP_ID =" + bean.getEmployeeCode();

		final Object[][] empData = this.getSqlModel().getSingleResult(query);
		bean.setEmployeeToken(this.checkNull(String.valueOf(empData[0][0])));
		bean.setEmployeeName(this.checkNull(String.valueOf(empData[0][1])));
		bean.setDeptNo(this.checkNull(String.valueOf(empData[0][2])));
		bean.setDeptName(this.checkNull(String.valueOf(empData[0][3])));
		bean.setOfficeLocation(this.checkNull(String.valueOf(empData[0][4])));
		bean.setPhNo(this.checkNull(String.valueOf(empData[0][5])));
		bean.setEmployeeCode(this.checkNull(String.valueOf(empData[0][6])));

		for (int i = 0; i < 3; i++) {
			System.out.println(" getEmployeeDetails Details::::::" + empData[0][i]);
		}

	}

	/**
	 * @param bean - Used to get CD Rom Id hidden field on JSP.
	 * @return true/false.
	 */
	public boolean delete(final CDRomRequestBean bean) {
		boolean result = false;

		final String code = bean.getCdRomID();

		final String delQuery = "DELETE FROM HRMS_D1_CDROM_REQUEST WHERE CDROM_ID=" + code;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

	/**
	 * @param bean - Used to get Application Data.
	 * @param request - Used to get request when application send for approval.
	 * @return true/false.
	 */
	public boolean save(final CDRomRequestBean bean, final HttpServletRequest request) {
		boolean result = false;
		try {
			
			System.out.println("Status : " + bean.getApplnStatus());
			String approverCode = "";
			final Object [][] insertObj = new Object[1][23];

			insertObj[0][0] = bean.getEmployeeCode();
			insertObj[0][1] = bean.getDeptId();
			insertObj[0][2] = bean.getOfficeLocation();
			insertObj[0][3] = bean.getPhNo();

			// Request Details
			insertObj[0][4] = bean.getRequestName();
			insertObj[0][5] = bean.getNoMaster();
			insertObj[0][6] = bean.getNoCopy();
			insertObj[0][7] = bean.getPakName();
			insertObj[0][8] = bean.getAttachementRequestDesc();
			insertObj[0][9] = bean.getSourceName();
			insertObj[0][10] = bean.getRequestDetailFile();

			// Delivery Information
			insertObj[0][11] = bean.getDeliveryDate();
			insertObj[0][12] = bean.getDeliveryVia();

			// Addressing Information
			insertObj[0][13] = bean.getSourceAddress();
			insertObj[0][14] = bean.getAddressDocument();
			insertObj[0][15] = bean.getAddressInfoFile1();
			insertObj[0][16] = bean.getProof();
			insertObj[0][17] = bean.getAdditionalInfoDoc();
			insertObj[0][18] = bean.getAddressInfoFile2();

			if (!"".equals(bean.getApproverCode()) && bean.getApproverCode() != null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			insertObj[0][19] = approverCode;
			// insertObj[0][19] = "D";
			insertObj[0][20] = bean.getApplnStatus();

			// Tracking Number
			final String qq = "SELECT NVL(MAX(CDROM_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(CDROM_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CDROM_REQUEST 	";
			final Object[][] obj = this.getSqlModel().getSingleResult(qq);
			if (obj != null && obj.length > 0) {
				// bean.setAuthorizedToken(checkNull(String.valueOf(obj[0][0])));
			}
			final Date date = new Date();
			final SimpleDateFormat formater = new SimpleDateFormat(CDRomRequestModel.DATE);
			final String sysDate = formater.format(date);
			final ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			String token = "";
			token = tempModel.generateApplicationCode(String.valueOf(obj[0][1]), "D1-CDROM", bean.getEmployeeCode(), String.valueOf(obj[0][2]));
			
			bean.setAuthorizedToken(token);
			insertObj[0][21] = bean.getCompletedByCode().trim();
			insertObj[0][22] = bean.getAuthorizedToken().trim();

			final String insertQuery = "INSERT INTO HRMS_D1_CDROM_REQUEST " + " (CDROM_ID, CDROM_EMP_ID,CDROM_DEPT,CDROM_LOCATION,CDROM_PHONE_NO,CDROM_REQUEST_TYPE, CDROM_NO_OF_MASTER, CDROM_NO_OF_COPY_NEEDED, " + " CDROM_PAKKAGING_TYPE_NEEDED, CDROM_REQUEST_DOCUMENT_DESC, CDROM_REQUEST_SOURCE, " 	+ " CDROM_REQUSET_ATTACH_FILE, CDROM_DELIVERY_DATE, CDROM_DELIVERY_VIA, CDROM_ADDRESSING_SOURCE, " + " CDROM_ADDRESSING_DOCUMENT_DESC, CDROM_ADDRESSING_DOCUMENT_FILE, CDROM_ADDRESSING_PROOF, " + " CDROM_ADDRESSING_ADD_DESC, CDROM_ADDRESSING_ADD_FILE, CDROM_APPROVER_CODE, CDROM_APPROVER_STATUS, " + " CDROM_APP_DATE,CDROM_INITIATOR, CDROM_FILE_HEADER_NAME ) " + " VALUES((SELECT NVL(MAX(CDROM_ID),0)+1 FROM HRMS_D1_CDROM_REQUEST),?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,sysdate,?,?) ";

			result = this.getSqlModel().singleExecute(insertQuery, insertObj);
			
			// added nilesh
			if (result) {
				final String autoCodeQuery = " SELECT NVL(MAX(CDROM_ID),0) FROM HRMS_D1_CDROM_REQUEST ";
				final Object[][] data = this.getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setCdRomID(String.valueOf(data[0][0]));
				}
			}
			// added nilesh
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param bean - Used to get Application Data.
	 * @param request -  Used to get request when application send for approval.
	 * @return true/false.
	 */
	public boolean update(final CDRomRequestBean bean, final HttpServletRequest request) {
		boolean result = false;
		try {
			
			String approverCode = "";
			final Object [][] updateObj = new Object[1][24];

			updateObj[0][0] = bean.getEmployeeCode();
			updateObj[0][1] = bean.getDeptId();
			updateObj[0][2] = bean.getOfficeLocation();
			updateObj[0][3] = bean.getPhNo();

			// Request Details
			updateObj[0][4] = bean.getRequestName();
			updateObj[0][5] = bean.getNoMaster();
			updateObj[0][6] = bean.getNoCopy();
			updateObj[0][7] = bean.getPakName();
			updateObj[0][8] = bean.getAttachementRequestDesc();
			updateObj[0][9] = bean.getSourceName();
			updateObj[0][10] = bean.getRequestDetailFile();

			// Delivery Information
			updateObj[0][11] = bean.getDeliveryDate();
			updateObj[0][12] = bean.getDeliveryVia();

			// Addressing Information
			updateObj[0][13] = bean.getSourceAddress();
			updateObj[0][14] = bean.getAddressDocument();
			updateObj[0][15] = bean.getAddressInfoFile1();
			updateObj[0][16] = bean.getProof();
			updateObj[0][17] = bean.getAdditionalInfoDoc();
			updateObj[0][18] = bean.getAddressInfoFile2();

			if (!"".equals(bean.getApproverCode()) && bean.getApproverCode() != null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			updateObj[0][19] = approverCode;
			// updateObj[0][19] = "D";
			updateObj[0][20] = bean.getApplnStatus();
			// updateObj[0][17] = bean.getStatus();

			updateObj[0][21] = bean.getCompletedByCode().trim();
			updateObj[0][22] = bean.getAuthorizedToken().trim();
			updateObj[0][23] = bean.getCdRomID();

			final String updateQuery = "UPDATE HRMS_D1_CDROM_REQUEST SET " + "  CDROM_EMP_ID=?,CDROM_DEPT=?,CDROM_LOCATION=?,CDROM_PHONE_NO=?,CDROM_REQUEST_TYPE=?, CDROM_NO_OF_MASTER=?, CDROM_NO_OF_COPY_NEEDED=?, " + " CDROM_PAKKAGING_TYPE_NEEDED=?, CDROM_REQUEST_DOCUMENT_DESC=?, CDROM_REQUEST_SOURCE=?, " + " CDROM_REQUSET_ATTACH_FILE=?, CDROM_DELIVERY_DATE=to_date(?,'DD-MM-YYYY'), CDROM_DELIVERY_VIA=?, CDROM_ADDRESSING_SOURCE=?, " + " CDROM_ADDRESSING_DOCUMENT_DESC=?, CDROM_ADDRESSING_DOCUMENT_FILE=?, CDROM_ADDRESSING_PROOF=?, " + " CDROM_ADDRESSING_ADD_DESC=?, CDROM_ADDRESSING_ADD_FILE=?, CDROM_APPROVER_CODE=?, CDROM_APPROVER_STATUS=?,CDROM_INITIATOR=?, CDROM_FILE_HEADER_NAME=? " + " WHERE CDROM_ID =?";

			result = this.getSqlModel().singleExecute(updateQuery, updateObj);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param bean - Used to get appln data.
	 * @return true/false.
	 */
	public boolean sendForApproval(final CDRomRequestBean bean) {
		boolean result = false;
		try {
			System.out.println("IN sendForApproval ");
			String approverCode = "";
			final Object [][] insertObj = new Object[1][22];

			insertObj[0][0] = bean.getEmployeeCode();
			insertObj[0][1] = bean.getOfficeLocation();
			insertObj[0][2] = bean.getPhNo();

			// Request Details
			insertObj[0][3] = bean.getRequestName();
			insertObj[0][4] = bean.getNoMaster();
			insertObj[0][5] = bean.getNoCopy();
			insertObj[0][6] = bean.getPakName();
			insertObj[0][7] = bean.getAttachementRequestDesc();
			insertObj[0][8] = bean.getSourceName();
			insertObj[0][9] = bean.getUploadFileName();

			// Delivery Information
			insertObj[0][10] = bean.getDeliveryDate();
			insertObj[0][11] = bean.getDeliveryVia();

			// Addressing Information
			insertObj[0][12] = bean.getSourceAddress();
			insertObj[0][13] = bean.getAddressDocument();
			insertObj[0][14] = bean.getUploadFileAddress();
			insertObj[0][15] = bean.getProof();
			insertObj[0][16] = bean.getAdditionalInfoDoc();
			insertObj[0][17] = bean.getUploadFileAdditionalInfoDoc();

			if (!"".equals(bean.getApproverCode()) && bean.getApproverCode() != null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			insertObj[0][18] = approverCode;
			// insertObj[0][19] = "P";
			insertObj[0][19] = bean.getApplnStatus();

			// Tracking Number
			final String qq = "SELECT NVL(MAX(CDROM_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(CDROM_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CDROM_REQUEST 	";
			final Object[][] obj = this.getSqlModel().getSingleResult(qq);
			if (obj != null && obj.length > 0) {
				// bean.setAuthorizedToken(checkNull(String.valueOf(obj[0][0])));
			}
			final Date date = new Date();
			final SimpleDateFormat formater = new SimpleDateFormat(CDRomRequestModel.DATE);
			final String sysDate = formater.format(date);
			final ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			String token = "";
			token = tempModel.generateApplicationCode(
					String.valueOf(obj[0][1]), "D1-CDROM", bean.getEmployeeCode(), String.valueOf(obj[0][2]));
			System.out.println("token---" + token);
			bean.setAuthorizedToken(token);
			// End Tracking Number

			insertObj[0][20] = bean.getCompletedByCode().trim();
			insertObj[0][21] = bean.getAuthorizedToken().trim();

			final String insertQuery = "INSERT INTO HRMS_D1_CDROM_REQUEST " + " (CDROM_ID, CDROM_EMP_ID,CDROM_LOCATION, CDROM_PHONE_NO,CDROM_REQUEST_TYPE, CDROM_NO_OF_MASTER, CDROM_NO_OF_COPY_NEEDED, " + " CDROM_PAKKAGING_TYPE_NEEDED, CDROM_REQUEST_DOCUMENT_DESC, CDROM_REQUEST_SOURCE, " + " CDROM_REQUSET_ATTACH_FILE, CDROM_DELIVERY_DATE, CDROM_DELIVERY_VIA, CDROM_ADDRESSING_SOURCE, " + " CDROM_ADDRESSING_DOCUMENT_DESC, CDROM_ADDRESSING_DOCUMENT_FILE, CDROM_ADDRESSING_PROOF, " + " CDROM_ADDRESSING_ADD_DESC, CDROM_ADDRESSING_ADD_FILE, CDROM_APPROVER_CODE, CDROM_APPROVER_STATUS, " + " CDROM_APP_DATE,CDROM_INITIATOR, CDROM_FILE_HEADER_NAME) " + " VALUES((SELECT NVL(MAX(CDROM_ID),0)+1 FROM HRMS_D1_CDROM_REQUEST),?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,sysdate,?,?) ";

			result = this.getSqlModel().singleExecute(insertQuery, insertObj);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param bean - Used to get Applicationn Data.
	 * @return true/false.
	 */
	public boolean updateSendForApproval(final CDRomRequestBean bean) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			String approverCode = "";
			final Object [][] updateObj = new Object[1][23];

			updateObj[0][0] = bean.getEmployeeCode();
			updateObj[0][1] = bean.getOfficeLocation();
			updateObj[0][2] = bean.getPhNo();

			// Request Details
			updateObj[0][3] = bean.getRequestName();
			updateObj[0][4] = bean.getNoMaster();
			updateObj[0][5] = bean.getNoCopy();
			updateObj[0][6] = bean.getPakName();
			updateObj[0][7] = bean.getAttachementRequestDesc();
			updateObj[0][8] = bean.getSourceName();
			updateObj[0][9] = bean.getUploadFileName();

			// Delivery Information
			updateObj[0][10] = bean.getDeliveryDate();
			updateObj[0][11] = bean.getDeliveryVia();

			// Addressing Information
			updateObj[0][12] = bean.getSourceAddress();
			updateObj[0][13] = bean.getAddressDocument();
			updateObj[0][14] = bean.getUploadFileAddress();
			updateObj[0][15] = bean.getProof();
			updateObj[0][16] = bean.getAdditionalInfoDoc();
			updateObj[0][17] = bean.getUploadFileAdditionalInfoDoc();

			if (!"".equals(bean.getApproverCode()) && bean.getApproverCode() != null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			updateObj[0][18] = approverCode;
			// updateObj[0][17] = "D";
			updateObj[0][19] = "P";
			updateObj[0][20] = bean.getCompletedByCode().trim();
			updateObj[0][21] = bean.getAuthorizedToken().trim();
			updateObj[0][22] = bean.getCdRomID();

			final String updateQuery = "UPDATE HRMS_D1_CDROM_REQUEST SET " + "  CDROM_EMP_ID=?,CDROM_LOCATION=?,CDROM_PHONE_NO=?,CDROM_REQUEST_TYPE=?, CDROM_NO_OF_MASTER=?, CDROM_NO_OF_COPY_NEEDED=?, " + " CDROM_PAKKAGING_TYPE_NEEDED=?, CDROM_REQUEST_DOCUMENT_DESC=?, CDROM_REQUEST_SOURCE=?, " + " CDROM_REQUSET_ATTACH_FILE=?, CDROM_DELIVERY_DATE=to_date(?,'DD-MM-YYYY'), CDROM_DELIVERY_VIA=?, CDROM_ADDRESSING_SOURCE=?, " + " CDROM_ADDRESSING_DOCUMENT_DESC=?, CDROM_ADDRESSING_DOCUMENT_FILE=?, CDROM_ADDRESSING_PROOF=?, " + " CDROM_ADDRESSING_ADD_DESC=?, CDROM_ADDRESSING_ADD_FILE=?, CDROM_APPROVER_CODE=?, CDROM_APPROVER_STATUS=? ,CDROM_INITIATOR=?, CDROM_FILE_HEADER_NAME=? " + " WHERE CDROM_ID =?";

			result = this.getSqlModel().singleExecute(updateQuery, updateObj);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param srNo - used for displaying purpose of for loop. 
	 * @param proofName - used to set uploaded file name.
	 * @param bean - used to set list.
	 */
	public void displayIteratorValueForUploadProof(final String[] srNo, final String[] proofName, final CDRomRequestBean bean) {
		try {
			final List<CDRomRequestBean> proofList = new ArrayList<CDRomRequestBean>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					final CDRomRequestBean bean1 = new CDRomRequestBean();
					bean1.setUploadFileName(proofName[i]);
					bean1.setUploadFileSrNo(srNo[i]);
					proofList.add(bean);
				}
				bean.setUploadFileList(proofList);
				System.out.println("here file List---" + bean.getUploadFileList().size());
			}
		} catch (final Exception e) {
			this.logger.error("Exception in displayIteratorValueForUploadProof------" + e);
		}

	}

	/**
	 * @param bean - set Application Data.
	 * @param request - Used to set Attributes addressSource & proofSource.
	 * @param cdromID - Used to get CD Rom ID.
	 */
	public void view(final CDRomRequestBean bean, final HttpServletRequest request, final String cdromID) {
		try {
			final String query = " Select CDROM_ID, CDROM_EMP_ID,CDROM_REQUEST_TYPE, CDROM_NO_OF_MASTER, CDROM_NO_OF_COPY_NEEDED, " + " CDROM_PAKKAGING_TYPE_NEEDED, CDROM_REQUEST_DOCUMENT_DESC, CDROM_REQUEST_SOURCE,  " + " CDROM_REQUSET_ATTACH_FILE, to_char(CDROM_DELIVERY_DATE,'dd-mm-yyyy'), CDROM_DELIVERY_VIA, CDROM_ADDRESSING_SOURCE,  " + " CDROM_ADDRESSING_DOCUMENT_DESC, CDROM_ADDRESSING_DOCUMENT_FILE, CDROM_ADDRESSING_PROOF,  " + " CDROM_ADDRESSING_ADD_DESC, CDROM_ADDRESSING_ADD_FILE, CDROM_APPROVER_CODE, CDROM_APPROVER_STATUS,  " + " CDROM_APPROVER_COMMENTS,CDROM_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME), " + " CDROM_DEPT,HRMS_DEPT.DEPT_NAME ,CDROM_LOCATION,CDROM_PHONE_NO,CDROM_LEVEL ," + " CDROM_INITIATOR, CDROM_FILE_HEADER_NAME ,to_char(CDROM_APP_DATE,'dd-mm-yyyy HH24:MI'), " + "emp.EMP_TOKEN ,To_char(emp.EMP_FNAME||' '||emp.EMP_MNAME||' '||emp.EMP_LNAME)" + " from HRMS_D1_CDROM_REQUEST " + " inner join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID) " + " LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_D1_CDROM_REQUEST.CDROM_DEPT)  " + " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID) " + " Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) " + "left join hrms_emp_offc emp on(emp.EMP_ID=HRMS_D1_CDROM_REQUEST.CDROM_APPROVER_CODE)" + " where  CDROM_ID=" + cdromID  + " " + "ORDER BY CDROM_FILE_HEADER_NAME DESC ";       

			final Object[][] data = this.getSqlModel().getSingleResult(query);

			try {
				bean.setCdRomID(this.checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeCode(this.checkNull(String.valueOf(data[0][1])));
				// Employee Name Setting here
				Object[][] empObj = null;
				if (String.valueOf(data[0][1]) != null && !String.valueOf("").equals(data[0][1])) {
					final String queryEmployee = " select HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) " + " from hrms_emp_offc where EMP_ID= " + String.valueOf(data[0][1]);
					empObj = this.getSqlModel().getSingleResult(queryEmployee);
					bean.setEmployeeToken(this.checkNull(String.valueOf(empObj[0][0])));
					bean.setEmployeeName(this.checkNull(String.valueOf(empObj[0][1])));
				}
				// End Employee Name Setting here
				// Request Details
				bean.setRequestName(this.checkNull(String.valueOf(data[0][2])));
				bean.setNoMaster(this.checkNull(String.valueOf(data[0][3])));
				bean.setNoCopy(this.checkNull(String.valueOf(data[0][4])));
				bean.setPakName(this.checkNull(String.valueOf(data[0][5])));
				bean.setAttachementRequestDesc(this.checkNull(String.valueOf(data[0][6])));
				bean.setSourceName(this.checkNull(String.valueOf(data[0][7])));
				bean.setRequestDetailFile(this.checkNull(String.valueOf(data[0][8])));
				
				// Delivery Information
				bean.setDeliveryDate(this.checkNull(String.valueOf(data[0][9])));
				bean.setDeliveryVia(this.checkNull(String.valueOf(data[0][10])));

				// Addressing Information
				bean.setSourceAddress(this.checkNull(String.valueOf(data[0][11])));
				bean.setAddressDocument(this.checkNull(String.valueOf(data[0][12])));
				request.setAttribute("addressSource", bean.getSourceAddress());
				bean.setAddressInfoFile1(this.checkNull(String.valueOf(data[0][13])));
				
				bean.setProof(this.checkNull(String.valueOf(data[0][14])));
				request.setAttribute("proofSource", bean.getProof());
				bean.setAdditionalInfoDoc(this.checkNull(String.valueOf(data[0][15])));
				bean.setAddressInfoFile2(this.checkNull(String.valueOf(data[0][16])));

				Object[][] empFlow = null;
				try {
					final ReportingModel reporting = new ReportingModel();
					reporting.initiate(context, session);
					empFlow = reporting.generateEmpFlow(bean.getEmployeeCode(),	"D1", 1);
					reporting.terminate();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				if (empFlow != null && empFlow.length > 0) {

					final String setApprover = String.valueOf(empFlow[0][0]);
					// Approver Section Begins
					if (!this.checkNull(String.valueOf(data[0][17])).equals(setApprover)) {
						bean.setApproverCode(this.checkNull(String.valueOf(data[0][17])));
						bean.setApproverToken(this.checkNull(String.valueOf(data[0][31])));
						bean.setApproverName(this.checkNull(String.valueOf(data[0][32])));
						
					}

					// Approver Section Ends
				} else {

					bean.setApproverCode(this.checkNull(String.valueOf(data[0][17])));
					bean.setApproverToken(this.checkNull(String.valueOf(data[0][31])));
					bean.setApproverName(this.checkNull(String.valueOf(data[0][32])));
					
				}

				bean.setApplnStatus(this.checkNull(String.valueOf(data[0][18])));
				
				bean.setApproverComments(this.checkNull(String.valueOf(data[0][19])));
				bean.setLevel(this.checkNull(String.valueOf(data[0][20])));

				bean.setDeptId(this.checkNull(String.valueOf(data[0][23])));
				bean.setDeptName(this.checkNull(String.valueOf(data[0][24])));
				bean.setOfficeLocation(this.checkNull(String.valueOf(data[0][25])));
				bean.setPhNo(this.checkNull(String.valueOf(data[0][26])));
				bean.setCompletedByCode(this.checkNull(String.valueOf(data[0][28])));
				bean.setAuthorizedToken(this.checkNull(String.valueOf(data[0][29])));
				bean.setCompletedDate(this.checkNull(String.valueOf(data[0][30])));

				Object[][] completedByObj = null;
				if (String.valueOf(data[0][28]) != null 	&& !String.valueOf("").equals(data[0][28])) {
					final String completedByQuery = " select HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) " + " from hrms_emp_offc where EMP_ID= " + String.valueOf(data[0][28]);
					completedByObj = this.getSqlModel().getSingleResult(completedByQuery);
					
					bean.setCompletedBy(this.checkNull(String.valueOf(completedByObj[0][1])));
				}

			

			} catch (final Exception e) {
				e.printStackTrace();
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean - Used to get page related fields values and set list.
	 * @param request - Used to set Attributes totalApprovedPage & approvedPageNo.
	 * @param userId - Used to get Initiator.
	 */ 
	public void getApprovedList(final CDRomRequestBean bean, final HttpServletRequest request, final String userId) {
		try {
			Object[][] approvedListData = null;
			final List<CDRomRequestBean> approvedList = new ArrayList<CDRomRequestBean>();

			Object[][] approvedCancellationListData = null;
			final List<CDRomRequestBean> approvedCancellationList = new ArrayList<CDRomRequestBean>();

			// Approved application Begins

			final String selQuery = " select CDROM_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(CDROM_APP_DATE,'DD-MM-YYYY'), CDROM_APPROVER_STATUS " 	+ " ,CDROM_ID,CDROM_FILE_HEADER_NAME from HRMS_D1_CDROM_REQUEST " + "  inner join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" + "  where CDROM_APPROVER_STATUS  = 'A' AND CDROM_INITIATOR = " + userId + " " + "ORDER BY CDROM_APP_DATE DESC ";

			approvedListData = this.getSqlModel().getSingleResult(selQuery);

			final String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = CDRomRequestModel.PAGE_ZERO;
				pageIndexApproved[1] = CDRomRequestModel.PAGE_TWENTY;
				pageIndexApproved[2] = CDRomRequestModel.PAGE_ONE;
				pageIndexApproved[3] = CDRomRequestModel.PAGE_ONE;
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if (CDRomRequestModel.PAGE_ONE.equals(pageIndexApproved[4])) {
				bean.setMyPageApproved(CDRomRequestModel.PAGE_ONE);
			}

			if (approvedListData != null && approvedListData.length > 0) {

				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final CDRomRequestBean beanItt = new CDRomRequestBean();
					beanItt.setAuthorizedToken(this.checkNull(String.valueOf(approvedListData[i][6])));
					beanItt.setCdRomID(this.checkNull(String.valueOf(approvedListData[i][5])));
					beanItt.setEmpToken(this.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setEmpName(this.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setApplicationDate(this.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setApplnStatus(this.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);

				}
				bean.setApprovedListLength(true);
				bean.setApprovedList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins

			final String approvedcancellationQuery = " select CDROM_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(CDROM_APP_DATE,'DD-MM-YYYY'), CDROM_APPROVER_STATUS " 	+ " ,CDROM_ID from HRMS_D1_CDROM_REQUEST " 	+ "  inner join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" + "  where CDROM_APPROVER_STATUS  = 'X' AND CDROM_EMP_ID = " + userId + " " + "ORDER BY CDROM_APP_DATE DESC ";

			approvedCancellationListData = this.getSqlModel().getSingleResult(approvedcancellationQuery);

			final String[] pageIndexApprovedCancel = Utility.doPaging(bean.getMyPageApprovedCancel(), approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = CDRomRequestModel.PAGE_ZERO;
				pageIndexApprovedCancel[1] = CDRomRequestModel.PAGE_TWENTY;
				pageIndexApprovedCancel[2] = CDRomRequestModel.PAGE_ONE;
				pageIndexApprovedCancel[3] = CDRomRequestModel.PAGE_ONE;
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (CDRomRequestModel.PAGE_ONE.equals(pageIndexApprovedCancel[4])) {
				bean.setMyPageApprovedCancel(CDRomRequestModel.PAGE_ONE);
			}

			if (approvedCancellationListData != null && approvedCancellationListData.length > 0) {

				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer.parseInt(pageIndexApprovedCancel[1]); i++) {
					final CDRomRequestBean beanItt = new CDRomRequestBean();
					beanItt.setCdRomID(this.checkNull(String.valueOf(approvedListData[i][5])));
					beanItt.setEmpToken(this.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setEmpName(this.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setApplicationDate(this.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setApplnStatus(this.checkNull(String.valueOf(approvedListData[i][4])));
					approvedCancellationList.add(beanItt);
				}
				bean.setApprovedCancelListLength(true);
				bean.setApprovedCancelList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param bean - Used to get page related fields values and set list.
	 * @param request - Used to set Attribute   totalRejected & PagerejectedPageNo - for rejected list & totalRejectedCancellationPage & rejectedCancellationPageNo - for Rejected Cancel List.
	 * @param userId - Used to get Initiator.
	 */
	public void getRejectedList(final CDRomRequestBean bean, final HttpServletRequest request, final String userId) {

		Object[][] rejectedListData = null;
		final List<CDRomRequestBean> rejectedList = new ArrayList<CDRomRequestBean>();

		Object[][] rejectedCancellationListData = null;
		final List<CDRomRequestBean> rejectedCancellationList = new ArrayList<CDRomRequestBean>();

		// Rejected application Begins

		final String rejectQuery = " select CDROM_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(CDROM_APP_DATE,'DD-MM-YYYY'), CDROM_APPROVER_STATUS " 	+ " ,CDROM_ID,CDROM_FILE_HEADER_NAME from HRMS_D1_CDROM_REQUEST " + "  inner join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" + "  where CDROM_APPROVER_STATUS  = 'R' AND CDROM_INITIATOR = " + userId + " " + "ORDER BY CDROM_APP_DATE DESC ";

		rejectedListData = this.getSqlModel().getSingleResult(rejectQuery);

		final String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(), rejectedListData.length, 20);
		if (pageIndexRejected == null) {
			pageIndexRejected[0] = CDRomRequestModel.PAGE_ZERO;
			pageIndexRejected[1] = CDRomRequestModel.PAGE_TWENTY;
			pageIndexRejected[2] = CDRomRequestModel.PAGE_ONE;
			pageIndexRejected[3] = CDRomRequestModel.PAGE_ONE;
			pageIndexRejected[4] = "";
		}

		request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
		request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
		if (CDRomRequestModel.PAGE_ONE.equals(pageIndexRejected[4])) {
			bean.setMyPageRejected(CDRomRequestModel.PAGE_ONE);
		}

		if (rejectedListData != null && rejectedListData.length > 0) {

			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
				final CDRomRequestBean beanItt = new CDRomRequestBean();
				beanItt.setAuthorizedToken(this.checkNull(String.valueOf(rejectedListData[i][6])));
				beanItt.setCdRomID(this.checkNull(String.valueOf(rejectedListData[i][5])));
				beanItt.setEmpToken(this.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setEmpName(this.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setApplicationDate(this.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setApplnStatus(this.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			bean.setRejectListLength(true);
			bean.setRejectList(rejectedList);
		}
		// Rejected application Ends

		// Rejected cancellation application Begins

		final String rejectcancellationQuery = " select CDROM_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(CDROM_APP_DATE,'DD-MM-YYYY'), CDROM_APPROVER_STATUS " + " ,CDROM_ID,CDROM_FILE_HEADER_NAME from HRMS_D1_CDROM_REQUEST " + "  inner join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CDROM_REQUEST.CDROM_EMP_ID)" + "  where CDROM_APPROVER_STATUS  = 'Z' AND CDROM_EMP_ID = " + userId + " " + "ORDER BY CDROM_APP_DATE DESC ";

		rejectedCancellationListData = this.getSqlModel().getSingleResult(rejectcancellationQuery);

		final String[] pageIndexRejectedCancellation = Utility.doPaging(bean.getMyPageCancelRejected(), rejectedCancellationListData.length, 20);
		if (pageIndexRejectedCancellation == null) {
			pageIndexRejectedCancellation[0] = CDRomRequestModel.PAGE_ZERO;
			pageIndexRejectedCancellation[1] = CDRomRequestModel.PAGE_TWENTY;
			pageIndexRejectedCancellation[2] = CDRomRequestModel.PAGE_ONE;
			pageIndexRejectedCancellation[3] = CDRomRequestModel.PAGE_ONE;
			pageIndexRejectedCancellation[4] = "";
		}

		request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[2])));
		request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[3])));
		if (CDRomRequestModel.PAGE_ONE.equals(pageIndexRejectedCancellation[4])) {
			bean.setMyPageCancelRejected(CDRomRequestModel.PAGE_ONE);
		}
		if (rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {

			for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer.parseInt(pageIndexRejectedCancellation[1]); i++) {
				final CDRomRequestBean beanItt = new CDRomRequestBean();
				beanItt.setAuthorizedToken(this.checkNull(String.valueOf(rejectedListData[i][6])));
				beanItt.setCdRomID(this.checkNull(String.valueOf(rejectedListData[i][5])));
				beanItt.setEmpToken(this.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setEmpName(this.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setApplicationDate(this.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setApplnStatus(this.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedCancellationList.add(beanItt);
			}
			bean.setRejectCancelListLength(true);
			bean.setRejectCancelList(rejectedCancellationList);
		}
		// Rejected cancellation application Ends
	}

	/**
	 * @param bean - Used to get CDROM ID. 
	 * @param request - used  to send it to save method.
	 * @return true/false.
	 */
	public boolean sendForApprovalFunction(final CDRomRequestBean bean, final HttpServletRequest request) {
		
		boolean result = false;

		if ("".equals(bean.getCdRomID())) {
			result = this.save(bean, request);
		} else {
			result = this.update(bean, request);
			try {
				final String changeStatusQuery = "UPDATE HRMS_D1_CDROM_REQUEST SET CDROM_APPROVER_STATUS = 'P' WHERE CDROM_ID = " + bean.getCdRomID();
				result = this.getSqlModel().singleExecute(changeStatusQuery);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("bean.getCdRomID() in model is ====" + bean.getCdRomID());
		return result;

	}

}
