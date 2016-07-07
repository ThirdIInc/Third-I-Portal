package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.paradyne.bean.D1.ClassRequestApproveBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ClassRequestApproveModel;
import org.paradyne.model.D1.ClassRequestModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class ClassRequestApproveAction extends ParaActionSupport {
	/**
	 * Set input.
	 */
	private static final String RETURN_TYPE_INPUT = "input";
	/**
	 * Set Pending.
	 */
	private static final String STATUS_PENDING = "pending";
	/**
	 * Set Extension Type as JPG.
	 */
	private static final String EXT_TYPE_JPG = "jpg";
	/**
	 * Set #.
	 */
	private static final String SPEL_CHAR_HASH = "#";
	/**
	 * Set approved.
	 */
	private static final String STATUS_APPROVED =	"approved";
	/**
	 * Set AllEnable to N.
	 */
	private static final String SETENABLE_ALL_N = "N";
	/**
	 *  Set Extension Type as GIF.
	 */
	private static final String EXT_TYPE_GIF = "gif";
	/**
	 *  Set Extension Type as TXT.
	 */
	private static final String EXT_TYPE_TXT = "txt";

	/**
	 * Object of ClassRequestApproveBean.
	 */
	private ClassRequestApproveBean classreqApp;

	/** (non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 * @throws Exception - menu code not found.
	 */
	public void prepare_local() throws Exception {
		this.classreqApp = new ClassRequestApproveBean();
		this.classreqApp.setMenuCode(2021);
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return Object.
	 */
	public Object getModel() {

		return this.classreqApp;
	}

	/**
	 * @return the classreqApp
	 */
	public ClassRequestApproveBean getClassreqApp() {
		return this.classreqApp;
	}
/**
 * 
 * @param classreqApp the classreqApp to set
 */
	public void setClassreqApp(final ClassRequestApproveBean classreqApp) {
		this.classreqApp = classreqApp;
	}

	/**(non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 * @throws Exception if pool name blank.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		/*File data path is set here by this way*/
		
		String poolName = String.valueOf(session.getAttribute("session_pool"));

		if (!"".equals(poolName) || poolName == null) {
			poolName = "/" + poolName;
		}

	        final String dataPath = this.getText("data_path") + "/upload" + poolName + "/ClassRequest/";
		this.classreqApp.setDataPath(dataPath);
	
	}

	/**purpose - back to front page.
	 * @return INPUT.
	 */
	public String backToList() {
		this.input();
		return INPUT;
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * @return INPUT.
	 */
	public String input() {
		try {
		        final ClassRequestApproveModel model = new ClassRequestApproveModel();

			model.initiate(context, session);
		        final	String userId = this.classreqApp.getUserEmpId();
	                final	boolean isITDeptApprover = model.isUserAHRApprover(userId);
			if (isITDeptApprover) {
				model.getPendingList(this.classreqApp, request);
			} 
			this.classreqApp.setListType(ClassRequestApproveAction.STATUS_PENDING);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * purpose - paging implementation purpose.
	 * @return input.
	 */
	public String callPage() {

		return ClassRequestApproveAction.RETURN_TYPE_INPUT;
	}

	/**
	 * purpose- to return back to list.
	 * @return input.
	 */
	public String cancel() {
		this.input();
		return ClassRequestApproveAction.RETURN_TYPE_INPUT;
	}

/**
 * purpose - To Approve Application & the status will also get change.
 * @return this.input()
 */
	public String approveApplication() {	
	
		final String applicationId = this.classreqApp.getClassReqAppCode();
		
		final String userId = this.classreqApp.getUserEmpId();
	
		System.out.println("userId in approveApplication is = " + userId);
		
		final	String status = this.classreqApp.getApplnStatus();
			
		try {
			final ClassRequestApproveModel model = new ClassRequestApproveModel();
			model.initiate(context, session);
		        final boolean result = model.approveApplicationFunction(this.classreqApp, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application approved successfully.");
				this.sendApprovalToApplicantApplicantMail(applicationId, userId); 
			} else {
				this.addActionMessage("Error occured.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}
	
/**
 * purpose - send Approval to Applicant mail.
 * @param applicationId - Application Unique Id.
 * @param userID - Current User Id.
 */
        private void sendApprovalToApplicantApplicantMail(final String applicationId, final String userID) {
        	try {
        		final String loginUserId =  this.classreqApp.getCompletedByID(); 

        		System.out.println("loginUserId here in sendApprovalToApplicantApplicantMail is = " + loginUserId);
		/** MAIL FROM Approver  To Applicant**/
        		
        		final	ClassRequestModel model = new ClassRequestModel();
        		model.initiate(context, session);

        		final	String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS APPROVER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPROVER_NAME " + " FROM HRMS_D1_APPROVAL_SETTINGS " + " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)" + " WHERE APP_SETTINGS_TYPE = 'E'  ORDER BY APP_SETTINGS_EMP_ID ";
        		final	Object [][] data = model.getSqlModel().getSingleResult(query);
        		final	EmailTemplateBody templateApp = new EmailTemplateBody();
        		templateApp.initiate(context, session);
        		templateApp.setEmailTemplate("D1-CLASS REQUEST APPROVER TO APPLICANT");
        		
        		/**
        		 * FROM EMAIL
        		 */ 
        		templateApp.getTemplateQueries();
        		final	EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); 
		
        		System.out.println("userID in mail application is ========" + userID);
        		/**
        		 *TO EMAIL**/
        		templateQueryApp1.setParameter(1, userID);
        		final	EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); 
		
        		System.out.println("loginUserId in mail application is ========" + loginUserId);
        		
        		/**
        		 *TABLE DATA**/
        		templateQueryApp2.setParameter(1, loginUserId);
        		final 	EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
        		System.out.println("applicationId here is =========" + applicationId);
		
        		templateQueryApp3.setParameter(1, applicationId);
		
        		 /**
        		  * *OTHERS.**/
        		final	EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
        		templateQueryApp4.setParameter(1, String.valueOf(data[0][0]));
		
        		final String loginId = this.classreqApp.getCompletedByID();
		
        		/**
        		 * APPLICANT NAME.*
        		 **/
        		final	EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
        		templateQueryApp5.setParameter(1, loginId);
		
        		/**
        		 * location.
        		 **/			
        		final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
        		templateQueryApp6.setParameter(1, applicationId);


        		/**
        		 *hotel info.
        		 * */
        		final EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);  
        		templateQueryApp7.setParameter(1, applicationId);

        		/**
        		 * approverInfo
        		 **/
        		final  EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
        		templateQueryApp8.setParameter(1, applicationId);



        		String[] empData = null;

        		if (data != null && data.length > 1) {
        			empData = new String[data.length];
        			for (int i = 1; i < empData.length; i++) {
        				empData[i] = String.valueOf(data[i][0]);
        			}
        		}

        		templateApp.configMailAlert();
        		final	String[] attachedFile = new String[1];
        		attachedFile[0] = this.classreqApp.getDataPath() + this.classreqApp.getHotelFile();
        		System.out.println("attachedFile[0]========" + attachedFile[0]);
		
        		templateApp.sendApplMailWithAttachment(attachedFile);
		
        		templateApp.sendApplicationMail();
        		templateApp.clearParameters();
        		templateApp.terminate();
        		model.terminate();
        		/*** MAIL FROM Applicant To Approver End**/
        	} catch (final Exception e) {
        		e.printStackTrace();
        	}
        }	

    /**
	 * purpose - Populate Approver Comments.
	 * @param apprCommentListObj -set list values.
	 */
	private void populateApproverComments(final Object[][] apprCommentListObj) {
		final	List<ClassRequestApproveBean> approverCommentList = new ArrayList<ClassRequestApproveBean>(apprCommentListObj.length);

		for (int i = 0; i < apprCommentListObj.length; i++) {
			final	ClassRequestApproveBean appBean = new ClassRequestApproveBean();
			appBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
			appBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
			appBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
			appBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
			approverCommentList.add(appBean);
		}
		this.classreqApp.setApproverCommentList(approverCommentList);
	}

	/**
	 * purpose - For application send back.
	 * @return this.input()
	 */
	public String sendBackApplication() {
		try {
			final	String applicationId = this.classreqApp.getClassReqAppCode();
			final	String approverComments = this.classreqApp.getApproverComments();
			final	String userId = this.classreqApp.getUserEmpId();
			final	ClassRequestApproveModel model = new ClassRequestApproveModel();

			model.initiate(context, session);
			model.sendBack(applicationId, approverComments, userId);

			this.sendApprovalToApplicantApplicantMail(applicationId, userId);
			model.terminate();
			this.addActionMessage("Application Send Back successfully.");
		
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	
	/**
	 * purpose - Reject Application.
	 * @return this.input()
	 */
	public String rejectApplication()	{
		
		final	String applicationId = this.classreqApp.getClassReqAppCode();
		final	String userID = this.classreqApp.getUserEmpId();
		final	String status = this.classreqApp.getApplnStatus();
		try {
			
			final	ClassRequestApproveModel model = new ClassRequestApproveModel();
			model.initiate(context, session);
			final	boolean result = model.rejectApplicationFunction(this.classreqApp, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application rejected.");
				this.sendApprovalToApplicantApplicantMail(applicationId, userID); 
			} else {
				this.addActionMessage("Error occured.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * purpose - getting rejected list.
	 * @return INPUT
	 * @throws Exception if userId get blank or null.
	 */
	public String getRejectedList() throws Exception {
		try {
			final	ClassRequestApproveModel model = new ClassRequestApproveModel();

			model.initiate(context, session);
			final	String userId = this.classreqApp.getUserEmpId();
			final	boolean isITDeptApprover = model.isUserAHRApprover(userId);
			if (isITDeptApprover) {
				model.getRejectedList(this.classreqApp, request);
			} 
			this.classreqApp.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * purpose -  Method for file upload functionality.
	 * @throws IOException if particular path or file not present at given path. then it throws this exception.
	 */
	public void openAttachedFile() throws IOException {
     
		System.out.println("here in openAttachedFile function--" + this.classreqApp.getHotelFile());
		
		final	String addedFile = this.classreqApp.getHotelFile();
		final	String[] extension = addedFile.replace(".", ClassRequestApproveAction.SPEL_CHAR_HASH).split(ClassRequestApproveAction.SPEL_CHAR_HASH);
		final	String ext = extension[extension.length - 1];
		String mimeType = "";
		final	String dataPath = this.classreqApp.getDataPath();
		final	String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = "pdf";
			final String applnDoc = "doc";
			final String applnXls = "xls";
			final String applnXlsx = "xlsx";
			final String applnJpg = ClassRequestApproveAction.EXT_TYPE_JPG;
			final String applnTxt = ClassRequestApproveAction.EXT_TYPE_TXT;
			final String applnGif = ClassRequestApproveAction.EXT_TYPE_GIF;
              
			final String mimeTypeAcrobat = "acrobat";
			final String mimeTypeMSWord = "msword";
			final String mimeTypeMSExcel = "msexcel";
			final String mimeTypeJpg = ClassRequestApproveAction.EXT_TYPE_JPG;
			final String mimeTypeTxt = ClassRequestApproveAction.EXT_TYPE_TXT;
			final String mimeTypeGif = ClassRequestApproveAction.EXT_TYPE_GIF;
              
			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}


			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename= \"" + addedFile + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}

			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}
	
	/*To set Approver Name on click edit application button*/
	
	/**
	 * purpose - Set Approver Name.
	 */
	public void setApproverName() {
		final	ClassRequestApproveModel model = new ClassRequestApproveModel();
		try {
			model.initiate(context, session);
			final	Object[][] empFlow = this.generateEmpFlow(this.classreqApp.getUserEmpId(), "D1", 1);
			if (empFlow != null && empFlow.length > 0) {
				final	String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id, EMP_TOKEN from hrms_emp_offc " + " where emp_id=" + String.valueOf(empFlow[0][0]);
				final	Object [][]  data = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
				
					this.classreqApp.setApproverName(String.valueOf(data[0][0]));
					this.classreqApp.setApproverCode(String.valueOf(data[0][1]));
					this.classreqApp.setAppToken(String.valueOf(data[0][2]));
				}
			} /*else {

			}*/
		} catch (final Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}
	
	
	/**
	 * @return INPUT.
	 * @throws Exception if userId get blank or null.
	 */
	public String getApprovedList() throws Exception {
		try {
			final	ClassRequestApproveModel model = new ClassRequestApproveModel();

			model.initiate(context, session);

			final	String userId = this.classreqApp.getUserEmpId();
			final	boolean isITDeptApprover = model.isUserAHRApprover(userId);
			if (isITDeptApprover) {
				model.getApprovedList(this.classreqApp, request);
			} 
			this.classreqApp.setListType(ClassRequestApproveAction.STATUS_APPROVED);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}


	/*public String getPendingList() {
		final ClassRequestApproveModel model = new ClassRequestApproveModel();

		model.initiate(context, session);

		String userId = this.classreqApp.getUserEmpId();
		String pageForPendingApp = this.classreqApp.getPageForPendingApp();
		String pageForPendingCancelApp = this.classreqApp.getPageForPendingCancelApp();
		boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if (isUserAHRApprover) {
			*//**
			 * Forwarded application list
			 *//*
			List forwardedAppList = model.getForwardedApplicationList(pageForPendingApp, request);
			if (forwardedAppList != null && !forwardedAppList.isEmpty()) {
				this.classreqApp.setPagingForPendingApp(true);
			} else {
				this.classreqApp.setPagingForPendingApp(false);
			}
			this.classreqApp.setPendingAppList(forwardedAppList);

			*//**
			 * Pending cancelled application list
			 *//*
			List pendingCancelAppList = model.getPendingCancellationApplicationList(pageForPendingCancelApp, request);
			if (pendingCancelAppList != null && !pendingCancelAppList.isEmpty()) {
				this.classreqApp.setPagingForPendingCancelApp(true);
			} else {
				this.classreqApp.setPagingForPendingCancelApp(false);
			}
			this.classreqApp.setPendingCancelAppList(pendingCancelAppList);
		} else {
			List pendingAppList = model.getPendingApplicationList(userId, pageForPendingApp, request);
			if (pendingAppList != null && !pendingAppList.isEmpty()) {
				this.classreqApp.setPagingForPendingApp(true);
			} else {
				this.classreqApp.setPagingForPendingApp(false);
			}
			this.classreqApp.setPendingAppList(pendingAppList);

			*//**
			 * Pending cancelled application list
			 *//*
			List pendingCancelAppList = model
					.getPendingCancellationApplicationList(userId,
							pageForPendingCancelApp, request);
			if (pendingCancelAppList != null && !pendingCancelAppList.isEmpty()) {
				this.classreqApp.setPagingForPendingCancelApp(true);
			} else {
				this.classreqApp.setPagingForPendingCancelApp(false);
			}
			this.classreqApp.setPendingCancelAppList(pendingCancelAppList);
		}

		this.classreqApp.setListType(ClassRequestApproveAction.STATUS_PENDING);

		model.terminate();
		return SUCCESS;
	}*/

	/**
	 * purpose - Function is for setting values after view application button clicked on list JSP.
	 * @return success.
	 */
	public String classApplicationFunction() {
		final	String typeOfList = this.classreqApp.getListType();
		System.out.println("typeOfList==" + typeOfList);
		this.classreqApp.setListTypeDetailPage(typeOfList);

		try {
			final	String applicationID = request.getParameter("classAppCode");
			System.out.println("applicationID = " + applicationID);
			final	ClassRequestApproveModel model = new ClassRequestApproveModel();
			model.initiate(context, session);
	
			//model.getcurrentUserAndSysDate(classreqApp);
			this.setApproverName();
			
			model.classApplicationFunction(this.classreqApp, applicationID, typeOfList);
		        final	Object[][] apprCommentListObj = model.getApproverCommentList(applicationID);
			this.populateApproverComments(apprCommentListObj);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		if (ClassRequestApproveAction.STATUS_PENDING.equals(typeOfList)) {
			this.classreqApp.setAppFlag(true);
			this.classreqApp.setEnableAll(ClassRequestApproveAction.SETENABLE_ALL_N);
			this.getNavigationPanel(1);
		} else if (ClassRequestApproveAction.STATUS_APPROVED.equals(typeOfList)) {
			this.classreqApp.setEnableAll(ClassRequestApproveAction.SETENABLE_ALL_N);
			this.getNavigationPanel(2);
		} else {
			this.classreqApp.setEnableAll(ClassRequestApproveAction.SETENABLE_ALL_N);
			this.getNavigationPanel(2);
		}

		return "success";
	}
}
