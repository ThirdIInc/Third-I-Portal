package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.ClassRequestBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;


/**
 * @author aa1381
 *
 */
public class ClassRequestModel extends ModelBase {
	/**
	 * Set True.
	 */
	private static final String FLAG_TRUE = "true";
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
     * Method to check string is null or not.
     * 
     * @param result - Check null or not
     * @return - String after checking null
     */
	public String checkNull(final String result) 	{
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param classreqBean checking class code equals null,getting user employee id ,tracking no.
	 * @param empId - used to set employee id.
	 * @return boolean
	 * purpose - Insertion Of data into Data Base.
	 */
	public boolean insertBusinessData(final ClassRequestBean classreqBean, final String empId) {

		final Object [][] addObj = new Object[1][23];

		final String query1 = "SELECT NVL(MAX(CLASS_REQUEST_ID),0)+1 FROM  HRMS_D1_CLASS_REQUEST";
		final Object[][] classId = this.getSqlModel().getSingleResult(query1);

		addObj[0][0] = this.checkNull(String.valueOf(classId[0][0]));
		addObj[0][1] = classreqBean.getClassName().trim();
		addObj[0][2] = classreqBean.getClassDescription().trim();
		addObj[0][3] = classreqBean.getDivId().trim();
		addObj[0][4] = classreqBean.getPreRequisite().trim();
		addObj[0][5] = classreqBean.getInstructorName().trim();
		addObj[0][6] = classreqBean.getStartDate().trim();
		addObj[0][7] = classreqBean.getEndDate().trim();
		addObj[0][8] = classreqBean.getDurationofClass().trim();
		addObj[0][9] = classreqBean.getStartTime().trim();
		addObj[0][10] = classreqBean.getEndTime().trim();
		addObj[0][11] = classreqBean.getClassRoom().trim();
		addObj[0][12] = classreqBean.getMaxnumOfStudents().trim();
		addObj[0][13] = classreqBean.getLocation().trim();
		addObj[0][14] = classreqBean.getAddress().trim();
		addObj[0][15] = classreqBean.getTelephone().trim();
		addObj[0][16] = classreqBean.getContactName().trim();
		addObj[0][17] = classreqBean.getHotelInformation().trim();
		addObj[0][18] = classreqBean.getHotelFile().trim();
		addObj[0][19] = classreqBean.getComments().trim();
		addObj[0][20] = classreqBean.getStatus().trim();

		String autoCode = "";
		final String selQuery = "SELECT NVL(MAX(CLASS_REQUEST_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(CLASS_REQUEST_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CLASS_REQUEST	";
		final	Object[][] selData = this.getSqlModel().getSingleResult(selQuery);
		if ("".equals(classreqBean.getClassCode())) {
			autoCode = String.valueOf(selData[0][0]);

			classreqBean.setClassCode(autoCode);
			/**
			 * SET TRACKING NO
			 */
			final	String qq = "SELECT NVL(MAX(CLASS_REQUEST_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(CLASS_REQUEST_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CLASS_REQUEST	";
			final	Object[][] obj = this.getSqlModel().getSingleResult(qq);
			if (obj != null && obj.length > 0) {
				try {
					final 	ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					final	String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]) , "D1-CLASS" , classreqBean.getUserEmpId() , String.valueOf(obj[0][2]));
					classreqBean.setTrackingNum(this.checkNull(applnCode));
					model.terminate();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}

		addObj[0][21] = classreqBean.getTrackingNum().trim();
		addObj[0][22] = empId;
	
		classreqBean.setClassCode(String.valueOf(classId[0][0]));
		return this.getSqlModel().singleExecute(this.getQuery(1), addObj);
	}

	/**
	 * @param  classreqBean - to get application data.
	 * @return boolean
	 * purpose - Updating Data inside data base.
	 */
	public boolean updateBusinessData(final ClassRequestBean classreqBean) {

		final 	Object [][]  updateObj = new Object[1][21];

		updateObj[0][0] = classreqBean.getClassName().trim();
		updateObj[0][1] = classreqBean.getClassDescription().trim();
		updateObj[0][2] = classreqBean.getDivId().trim();
		updateObj[0][3] = classreqBean.getPreRequisite().trim();
		updateObj[0][4] = classreqBean.getInstructorName().trim();
		updateObj[0][5] = classreqBean.getStartDate().trim();
		updateObj[0][6] = classreqBean.getEndDate().trim();
		updateObj[0][7] = classreqBean.getDurationofClass().trim();
		updateObj[0][8] = classreqBean.getStartTime().trim();
		updateObj[0][9] = classreqBean.getEndTime().trim();
		updateObj[0][10] = classreqBean.getClassRoom().trim();
		updateObj[0][11] = classreqBean.getMaxnumOfStudents().trim();
		updateObj[0][12] = classreqBean.getLocation().trim();
		updateObj[0][13] = classreqBean.getAddress().trim();
		updateObj[0][14] = classreqBean.getTelephone().trim();
		updateObj[0][15] = classreqBean.getContactName().trim();
		updateObj[0][16] = classreqBean.getHotelInformation().trim();
		updateObj[0][17] = classreqBean.getHotelFile().trim();
		updateObj[0][18] = classreqBean.getComments().trim();
		updateObj[0][19] = classreqBean.getStatus().trim();
		updateObj[0][20] = classreqBean.getClassCode().trim();
		
	
		return this.getSqlModel().singleExecute(this.getQuery(2), updateObj);
	}

	/**
	 * @param classreqBean bean - to get class code.
	 * @param status - To get Application Status.
	 * @return - result.
	 * purpose - canceled the particular application and status will updated.
	 */
	public boolean cancelFunction(final ClassRequestBean classreqBean, final String status) {
		boolean result = false;
		try {
			final	String changeStatusQuery = "UPDATE HRMS_D1_CLASS_REQUEST SET STATUS = '" + status + "'" + " WHERE CLASS_REQUEST_ID = " + classreqBean.getClassCode();
			result = this.getSqlModel().singleExecute(changeStatusQuery);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param classreqBean - to get clas code.
	 * @param empId - to nset empid.
	 * @return - result
	 * purpose - send application to approver and their application status will be updated.
	 */
	public boolean sendForApprovalFunction(final ClassRequestBean classreqBean, final String empId) {
		boolean result = false;
		if ("".equals(classreqBean.getClassCode())) {
			result = this.insertBusinessData(classreqBean , empId);
		} else {
			result = this.updateBusinessData(classreqBean);
		}
		return result;
	}

	/*** List Functionality Starts*/

	/**
	 * @param classreqBean - checking modelength is true or not, set total no of records ,getting & setting  my page value & set drafted list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get initiator
	 * purpose - To Display Drafted Applications by this query. 
	 */
	public void initialData(final ClassRequestBean classreqBean, final HttpServletRequest request, final String userId) {

		Object[][] regData = null;

		final	String draftQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE CLASS_INITIATOR = " + userId + " AND STATUS = 'D' ORDER BY TRACKING_NUMBER DESC ";
		regData = this.getSqlModel().getSingleResult(draftQuery);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength("FLAG_TRUE");

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final	String[] draftedPageIndex = Utility.doPaging(classreqBean.getMyPage(),
					regData.length, 20);
			if (draftedPageIndex == null) {
				draftedPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				draftedPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				draftedPageIndex[2] = ClassRequestModel.PAGE_ONE;
				draftedPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(draftedPageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(draftedPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(draftedPageIndex[4])) {
				classreqBean.setMyPage(ClassRequestModel.PAGE_ONE);
			}
			final	List<ClassRequestBean> draftedList = new ArrayList<ClassRequestBean>();
			classreqBean.setDraftVoucherListLength(true);
			for (int i = Integer.parseInt(draftedPageIndex[0]); i < Integer.parseInt(draftedPageIndex[1]); i++) {

				final	ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));

				draftedList.add(bean);
			}  

			classreqBean.setDraftList(draftedList);
		} 	else {
			classreqBean.setDraftList(null);
		}
	}

	/**
	 * @param classreqBean - checking mode length is true or not, set total no of records ,getting & setting  my page value & set pending list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get initiator
	 * purpose - To display Pending Application by this query.
	 */
	public void pendingData(final ClassRequestBean classreqBean, final HttpServletRequest request, final String userId) {

		Object[][] regData = null;

		final	String pendingList = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE CLASS_INITIATOR = "	+ userId + " AND STATUS='P' ORDER BY TRACKING_NUMBER DESC ";

		regData = this.getSqlModel().getSingleResult(pendingList);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength(ClassRequestModel.FLAG_TRUE);

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final	String[] pendingPageIndex = Utility.doPaging(classreqBean.getMyPageApproved(), regData.length, 20);
			if (pendingPageIndex == null) {
				pendingPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				pendingPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				pendingPageIndex[2] = ClassRequestModel.PAGE_ONE;
				pendingPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPageApproved", Integer.parseInt(String.valueOf(pendingPageIndex[2])));
			request.setAttribute("pageNoApproved", Integer.parseInt(String.valueOf(pendingPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(pendingPageIndex[4])) {
				classreqBean.setMyPageApproved(ClassRequestModel.PAGE_ONE);
			}
				
			final	List<ClassRequestBean> inprocessList = new ArrayList<ClassRequestBean>();
			classreqBean.setPedingListListLength(true);

			for (int i = Integer.parseInt(pendingPageIndex[0]); i < Integer.parseInt(pendingPageIndex[1]); i++) {

				final	ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));

				inprocessList.add(bean);
			}
			   // end of loop
			classreqBean.setPedingList(inprocessList); 
		} 	else {

			classreqBean.setPedingList(null);
		}
	}

	/**
	 * @param classreqBean - checking mode length is true or not, set total no of records ,getting & setting  my page value & set send back list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get Initiator.
	 * purpose - To display Sent Back Applications by this query.
	 */
	public void sendBack(final ClassRequestBean classreqBean, final HttpServletRequest request, final String userId) {

		Object[][] regData = null;

		final	String sendBackQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE  CLASS_INITIATOR = " + userId + "AND STATUS='B'  ORDER BY TRACKING_NUMBER DESC ";
		regData = this.getSqlModel().getSingleResult(sendBackQuery);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength(ClassRequestModel.FLAG_TRUE);

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final	String[] sendBackPageIndex = Utility.doPaging(classreqBean.getMyPageReturn(), regData.length, 20);
			if (sendBackPageIndex == null) {
				sendBackPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				sendBackPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				sendBackPageIndex[2] = ClassRequestModel.PAGE_ONE;
				sendBackPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPageReturn", Integer.parseInt(String.valueOf(sendBackPageIndex[2])));
			request.setAttribute("pageNoReturn", Integer.parseInt(String.valueOf(sendBackPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(sendBackPageIndex[4])) {
				classreqBean.setMyPageReturn(ClassRequestModel.PAGE_ONE);
			}
			final	List<ClassRequestBean> sendbackList = new ArrayList<ClassRequestBean>();
			classreqBean.setReturnListLength(true);

			for (int i = Integer.parseInt(sendBackPageIndex[0]); i < Integer.parseInt(sendBackPageIndex[1]); i++) {

				final	ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));

				sendbackList.add(bean);
			}           // end of loop

			classreqBean.setReturnList(sendbackList);
		}   else {
			classreqBean.setReturnList(null);
		}
	}

	/**
	 * @param classreqBean - checking mode length is true or not, set total no of records ,getting & setting  my page value & set approved  list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get initiator
	 * purpose - Display Approved Application by this query.
	 */
	public void approvedData(final ClassRequestBean classreqBean, 	final HttpServletRequest request, final String userId) {
		Object[][] regData = null;

		final String approveQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE CLASS_INITIATOR = " + userId + "AND STATUS='A' ORDER BY TRACKING_NUMBER DESC ";

		regData = this.getSqlModel().getSingleResult(approveQuery);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength(ClassRequestModel.FLAG_TRUE);

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final	String[] approvedPageIndex = Utility.doPaging(classreqBean.getMyPageApprovedList(), regData.length, 20);
			if (approvedPageIndex == null) {
				approvedPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				approvedPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				approvedPageIndex[2] = ClassRequestModel.PAGE_ONE;
				approvedPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPageApprovedList", Integer.parseInt(String.valueOf(approvedPageIndex[2])));
			request.setAttribute("pageNoApprovedList", Integer.parseInt(String.valueOf(approvedPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(approvedPageIndex[4])) {
				classreqBean.setMyPageApprovedList(ClassRequestModel.PAGE_ONE);
			}
			final	List<ClassRequestBean> approvedList = new ArrayList<ClassRequestBean>();
			classreqBean.setApprovedListLength(true);

			for (int i = Integer.parseInt(approvedPageIndex[0]); i < Integer.parseInt(approvedPageIndex[1]); i++) {

				final ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));

				approvedList.add(bean);
			}       // end of loop

			classreqBean.setApprovedList(approvedList);
		} else {
			classreqBean.setApprovedList(null);
		}
	}

	/**
	 * @param classreqBean - checking mode length is true or not, set total no of records ,getting & setting  my page value & set approve cancel list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get initiator
	 * purpose - Display Approved Cancel Application by this query.
	 */
	public void approvedCancelData(final ClassRequestBean classreqBean, final HttpServletRequest request, final String userId) {
		Object[][] regData = null;

		final	String approvedCancelDataQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE CLASS_INITIATOR = " + userId + "AND STATUS='X' ORDER BY TRACKING_NUMBER DESC ";

		regData = this.getSqlModel().getSingleResult(approvedCancelDataQuery);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength(ClassRequestModel.FLAG_TRUE);

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final	String[] approvedCnacelPageIndex = Utility.doPaging(classreqBean.getMyPageApprovedCancelList(), regData.length, 20);
			if (approvedCnacelPageIndex == null) {
				approvedCnacelPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				approvedCnacelPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				approvedCnacelPageIndex[2] = ClassRequestModel.PAGE_ONE;
				approvedCnacelPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPageApprovedCancelList", Integer.parseInt(String.valueOf(approvedCnacelPageIndex[2])));
			request.setAttribute("pageNoApprovedCancelList", Integer.parseInt(String.valueOf(approvedCnacelPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(approvedCnacelPageIndex[4])) {
				classreqBean.setMyPageApprovedCancelList(ClassRequestModel.PAGE_ONE);
			}
			final List<ClassRequestBean> approveCancelList = new ArrayList<ClassRequestBean>();

			classreqBean.setApprovedCancelListLength(true);
			for (int i = Integer.parseInt(approvedCnacelPageIndex[0]); i < Integer.parseInt(approvedCnacelPageIndex[1]); i++) {

				final	ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));
				approveCancelList.add(bean);
			}    // end of loop

			classreqBean.setApprovedCancelList(approveCancelList);
		} else {
			classreqBean.setApprovedCancelList(null);
		}     
	}

	/**
	 * @param classreqBean - checking mode length is true or not, set total no of records ,getting & setting  my page value & set scancel list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get initiator
	 * purpose - Display Canceled Application by this query.
	 */
	public void cancel(final ClassRequestBean classreqBean, final HttpServletRequest request, final String userId) {

		Object[][] regData = null;

		final 	String cancelQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE CLASS_INITIATOR = " + userId + " AND STATUS='C' ORDER BY TRACKING_NUMBER DESC ";

		regData = this.getSqlModel().getSingleResult(cancelQuery);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength(ClassRequestModel.FLAG_TRUE);

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final String[] cancelPageIndex = Utility.doPaging(classreqBean.getMyPageCancel(), regData.length, 20);
			if (cancelPageIndex == null) {
				cancelPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				cancelPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				cancelPageIndex[2] = ClassRequestModel.PAGE_ONE;
				cancelPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPageCancel", Integer.parseInt(String.valueOf(cancelPageIndex[2])));
			request.setAttribute("pageNoCancel", Integer.parseInt(String.valueOf(cancelPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(cancelPageIndex[4])) {
				classreqBean.setMyPageCancel(ClassRequestModel.PAGE_ONE);
			}
			final List<ClassRequestBean> cancelList = new ArrayList<ClassRequestBean>();

			classreqBean.setCancelListLength(true);
			for (int i = Integer.parseInt(cancelPageIndex[0]); i < Integer.parseInt(cancelPageIndex[1]); i++) {

				final	ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));

				cancelList.add(bean);
			}       // end of loop

			classreqBean.setCancelList(cancelList);
		}  else {
			classreqBean.setCancelList(null);
		}        
	}

	/**
	 * @param classreqBean - checking mode length is true or not, set total no of records ,getting & setting  my page value & set rejected list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get initiator.
	 * purpose - Display Rejected Application by this query.
	 */
	public void rejectedData(final ClassRequestBean classreqBean, final HttpServletRequest request, final String userId) {

		Object[][] regData = null;
		
		final String rejectQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE CLASS_INITIATOR = " 	+ userId + " AND STATUS='R' ORDER BY TRACKING_NUMBER DESC ";

		regData = this.getSqlModel().getSingleResult(rejectQuery);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength(ClassRequestModel.FLAG_TRUE);

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final	String[] rejectedPageIndex = Utility.doPaging(classreqBean.getMyPageReject(), regData.length, 20);
			if (rejectedPageIndex == null) {
				rejectedPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				rejectedPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				rejectedPageIndex[2] = ClassRequestModel.PAGE_ONE;
				rejectedPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPageReject", Integer.parseInt(String.valueOf(rejectedPageIndex[2])));
			request.setAttribute("pageNoReject", Integer.parseInt(String.valueOf(rejectedPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(rejectedPageIndex[4])) {
				classreqBean.setMyPageReject(ClassRequestModel.PAGE_ONE);
			}	
			final List<ClassRequestBean> rejectedList = new ArrayList<ClassRequestBean>();

			classreqBean.setRejectedListLength(true);
			for (int i = Integer.parseInt(rejectedPageIndex[0]); i < Integer.parseInt(rejectedPageIndex[1]); i++) {

				final ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));

				rejectedList.add(bean);
			}    // end of loop

			classreqBean.setRejectedList(rejectedList);
		}  	else {
			classreqBean.setRejectedList(null);
		}    
	}

	/**
	* @param classreqBean - checking mode length is true or not, set total no of records ,getting & setting  my page value & set approved rejected list.
	 * @param request - used to set total page attribute & page no attribute.
	 * @param userId - used to get initiator.
	 * purpose - Display Approved Rejected Application by this query.
	 */
	public void approvedRejectedData(final ClassRequestBean classreqBean, final HttpServletRequest request, final String userId) {

		Object[][] regData = null;

		final String approvedRejectedDataQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE CLASS_INITIATOR = " + userId + " AND STATUS='Z' ORDER BY TRACKING_NUMBER DESC ";
		regData = this.getSqlModel().getSingleResult(approvedRejectedDataQuery);

		if (regData != null && regData.length > 0) {
			classreqBean.setModeLength(ClassRequestModel.FLAG_TRUE);

			classreqBean.setTotalNoOfRecords(String.valueOf(regData.length));

			final	String[] approvedRejectedPageIndex = Utility.doPaging(classreqBean.getMyPageRejectCancel(), regData.length, 20);
			if (approvedRejectedPageIndex == null) {
				approvedRejectedPageIndex[0] = ClassRequestModel.PAGE_ZERO;
				approvedRejectedPageIndex[1] = ClassRequestModel.PAGE_TWENTY;
				approvedRejectedPageIndex[2] = ClassRequestModel.PAGE_ONE;
				approvedRejectedPageIndex[3] = ClassRequestModel.PAGE_ONE;

			}

			request.setAttribute("totalPageRejectCancel", Integer.parseInt(String.valueOf(approvedRejectedPageIndex[2])));
			request.setAttribute("pageNoRejectCancel", Integer.parseInt(String.valueOf(approvedRejectedPageIndex[3])));
			if (ClassRequestModel.PAGE_ONE.equals(approvedRejectedPageIndex[4])) {
				classreqBean.setMyPageRejectCancel(ClassRequestModel.PAGE_ONE);
			}
			final List<ClassRequestBean> approvedRejectedList = new ArrayList<ClassRequestBean>();

			classreqBean.setApprovedRejectListLength(true);
			for (int i = Integer.parseInt(approvedRejectedPageIndex[0]); i < Integer.parseInt(approvedRejectedPageIndex[1]); i++) {

				final ClassRequestBean bean = new ClassRequestBean();
				bean.setClassCode(this.checkNull(String.valueOf(regData[i][0])));
				bean.setTrackingNum(this.checkNull(String.valueOf(regData[i][1])));
				bean.setClassName(this.checkNull(String.valueOf(regData[i][2])));
				bean.setClassDescription(this.checkNull(String.valueOf(regData[i][3])));
				bean.setClassAppDate(this.checkNull(String.valueOf(regData[i][4])));

				approvedRejectedList.add(bean);
			}     // end of loop

			classreqBean.setApprovedRejectList(approvedRejectedList);
		}  else {
			classreqBean.setApprovedRejectList(null);
		}      
	}

	/**
	 * @param classreqBean -setting application data.
	 * @param classId - setting CLASS_REQUEST_ID column in DB.
	 * purpose - Edit Data using this query by setting respective fields.
	 */
	public void editBusinessData(final ClassRequestBean classreqBean, final String classId) {
		this.getApproverCommentList(classreqBean, classId);
		this.getcurrentUserAndSysDate(classreqBean);
		try {

			final	String query = "SELECT CLASS_NAME, CLASS_DESCRIPTION,DIVISION_ID, HRMS_DIVISION.DIV_NAME, PRE_REQUISITE,INSTRUCTOR_NAME, to_char(REQUEST_START_DATE,'dd-mm-yyyy'), to_char(REQUEST_END_DATE,'dd-mm-yyyy')," + " DURATION_OF_CLASS, REQUEST_START_TIME,REQUEST_END_TIME,CLASSROOM, MAX_OF_STUDENTS,LOCATION,ADDRESS," + " TELEPHONE, CONTACT_NAME, HOTEL_INFORMATION,HOTEL_ATTACHMENT,COMMENTS,TRACKING_NUMBER " + " FROM HRMS_D1_CLASS_REQUEST " + " LEFT JOIN   HRMS_DIVISION on(  HRMS_DIVISION.DIV_ID =HRMS_D1_CLASS_REQUEST.DIVISION_ID)" + " WHERE CLASS_REQUEST_ID=" + classId;

			final	Object[][] data = this.getSqlModel().getSingleResult(query);

			classreqBean.setClassName(this.checkNull(String.valueOf(data[0][0])));
			classreqBean.setClassDescription(this.checkNull(String.valueOf(data[0][1])));
			classreqBean.setDivId(this.checkNull(String.valueOf(data[0][2])));
			classreqBean.setClassDivision(this.checkNull(String.valueOf(data[0][3])));
			classreqBean.setPreRequisite(this.checkNull(String.valueOf(data[0][4])));
			classreqBean.setInstructorName(this.checkNull(String.valueOf(data[0][5])));
			classreqBean.setStartDate(this.checkNull(String.valueOf(data[0][6])));
			classreqBean.setEndDate(this.checkNull(String.valueOf(data[0][7])));
			classreqBean.setDurationofClass(this.checkNull(String.valueOf(data[0][8])));
			classreqBean.setStartTime(this.checkNull(String.valueOf(data[0][9])));
			classreqBean.setEndTime(this.checkNull(String.valueOf(data[0][10])));
			classreqBean.setClassRoom(this.checkNull(String.valueOf(data[0][11])));
			classreqBean.setMaxnumOfStudents(this.checkNull(String.valueOf(data[0][12])));
			classreqBean.setLocation(this.checkNull(String.valueOf(data[0][13])));
			classreqBean.setAddress(this.checkNull(String.valueOf(data[0][14])));
			classreqBean.setTelephone(this.checkNull(String.valueOf(data[0][15])));
			classreqBean.setContactName(this.checkNull(String.valueOf(data[0][16])));
			classreqBean.setHotelInformation(this.checkNull(String.valueOf(data[0][17])));
			classreqBean.setHotelFile(this.checkNull(String.valueOf(data[0][18])));
			classreqBean.setComments(this.checkNull(String.valueOf(data[0][19])));
			classreqBean.setTrackingNum(this.checkNull(String.valueOf(data[0][20])));

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param classreqBean - to get class code.
	 * @return - result
	 * purpose - Delete particular application. 
	 */
	public boolean delete(final ClassRequestBean classreqBean) {
		boolean result = false;
		final String deleteId = classreqBean.getClassCode();

		final String delQuery = "DELETE FROM HRMS_D1_CLASS_REQUEST WHERE CLASS_REQUEST_ID=" + deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

	/**
	 * @param bean - setting application data.
	 * @param classId -setting class_req_id column in DB & also pass to get ApproverCommentsList.
	 * purpose - Display Records after clicked on View Application Button on List JSP.
	 */
	public void view(final ClassRequestBean bean, final String classId) {
		this.getApproverCommentList(bean, classId);
		this.getcurrentUserAndSysDate(bean);
		final 	String viewQuery = " SELECT  CLASS_REQUEST_ID, CLASS_NAME, CLASS_DESCRIPTION, DIVISION_ID, HRMS_DIVISION.DIV_NAME, PRE_REQUISITE, INSTRUCTOR_NAME," + " to_char(REQUEST_START_DATE,'dd-mm-yyyy'),to_char( REQUEST_END_DATE,'dd-mm-yyyy'), DURATION_OF_CLASS, " + " REQUEST_START_TIME,REQUEST_END_TIME, CLASSROOM," + " MAX_OF_STUDENTS,LOCATION,ADDRESS,TELEPHONE, CONTACT_NAME, " 	+ "  HOTEL_INFORMATION,HOTEL_ATTACHMENT,COMMENTS,STATUS,TRACKING_NUMBER " + " FROM HRMS_D1_CLASS_REQUEST  " + "  LEFT JOIN   HRMS_DIVISION on(  HRMS_DIVISION.DIV_ID =HRMS_D1_CLASS_REQUEST.DIVISION_ID) " + " WHERE CLASS_REQUEST_ID=" + classId;

		final	Object[][] data = this.getSqlModel().getSingleResult(viewQuery);

		bean.setClassCode(this.checkNull(String.valueOf(data[0][0])));
		bean.setClassName(this.checkNull(String.valueOf(data[0][1])));
		bean.setClassDescription(this.checkNull(String.valueOf(data[0][2])));
		bean.setDivId(this.checkNull(String.valueOf(data[0][3])));
		bean.setClassDivision(this.checkNull(String.valueOf(data[0][4])));
		bean.setPreRequisite(this.checkNull(String.valueOf(data[0][5])));
		bean.setInstructorName(this.checkNull(String.valueOf(data[0][6])));
		bean.setStartDate(this.checkNull(String.valueOf(data[0][7])));
		bean.setEndDate(this.checkNull(String.valueOf(data[0][8])));
		bean.setDurationofClass(this.checkNull(String.valueOf(data[0][9])));
		bean.setStartTime(this.checkNull(String.valueOf(data[0][10])));
		bean.setEndTime(this.checkNull(String.valueOf(data[0][11])));
		bean.setClassRoom(this.checkNull(String.valueOf(data[0][12])));
		bean.setMaxnumOfStudents(this.checkNull(String.valueOf(data[0][13])));
		bean.setLocation(this.checkNull(String.valueOf(data[0][14])));
		bean.setAddress(this.checkNull(String.valueOf(data[0][15])));
		bean.setTelephone(this.checkNull(String.valueOf(data[0][16])));
		bean.setContactName(this.checkNull(String.valueOf(data[0][17])));
		bean.setHotelInformation(this.checkNull(String.valueOf(data[0][18])));
		bean.setHotelFile(this.checkNull(String.valueOf(data[0][19])));
		bean.setComments(this.checkNull(String.valueOf(data[0][20])));
		bean.setApplnStatus(this.checkNull(String.valueOf(data[0][21])));
		bean.setTrackingNum(this.checkNull(String.valueOf(data[0][22])));

	}

	/**
	 * @param bean - setting approver list.
	 * @param classId - setting class_req_id column in DB.
	 * purpose - Getting Approver Comments.
	 */

	public void getApproverCommentList(final ClassRequestBean bean, final String classId) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CLASS_REQ_COMMENTS, " + " TO_CHAR(CLASS_REQ_DATE, 'DD-MM-YYYY') AS CLASS_REQ_DATE, " + " DECODE(CLASS_REQ_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS " + " FROM HRMS_D1_CLASS_REQ_DATA_PATH " + " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CLASS_REQ_DATA_PATH.CLASS_REQ_APPROVER) " + " WHERE CLASS_REQUEST_ID = " + classId + " ORDER BY CLASS_REQUEST_ID DESC ";

		final	Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
	
		final	List<ClassRequestBean> approverList = new ArrayList<ClassRequestBean>(apprCommentListObj.length);
		
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final	ClassRequestBean bean1 = new ClassRequestBean();
				bean1.setApprName(String.valueOf(apprCommentListObj[i][0]));
				bean1.setApprComments(String.valueOf(apprCommentListObj[i][1]));
				bean1.setApprDate(String.valueOf(apprCommentListObj[i][2]));
				bean1.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
				approverList.add(bean1);
			}
			bean.setApproverCommentList(approverList);
		}
	}

	/**
	 * @param classreqBean - getting User employee id & setting initiator name and initiate date.
	 * purpose - To display current login user name and system date on application JSP this shows that
	 * Who logged in and Current Date on which the application filled by user.
	 */
	public void getcurrentUserAndSysDate(final ClassRequestBean classreqBean) {
		try {
			final String currentUserQuery = "SELECT EMP_ID,EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_LNAME, TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI') " + " FROM HRMS_EMP_OFFC WHERE EMP_ID=" + classreqBean.getUserEmpId();
			
			final	Object[][] currentUserObj = this.getSqlModel().getSingleResult(currentUserQuery);
			
			if (currentUserObj != null && currentUserObj.length > 0) {
				classreqBean.setCompletedByID(this.checkNull(String.valueOf(currentUserObj[0][0])));
				classreqBean.setCompletedBy(this.checkNull(String.valueOf(currentUserObj[0][1])));
				classreqBean.setCompletedDate(this.checkNull(String.valueOf(currentUserObj[0][2])));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
