package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.paradyne.bean.D1.CDRomApprovalBean;
import org.paradyne.model.D1.CDRomApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1439/aa1381
 *
 */
public class CDRomApprovalAction extends ParaActionSupport {
	
	/**
	 * Set special type as dot.
	 */
	private static final String SPEL_CHAR_DOT = ".";
	/**
	 * Set special type as #.
	 */
	private static final String SPEL_CHAR_HASH = "#";
	/**
	 * Set file type as pdf.
	 */
	private static final String FILE_TYPE_PDF = "pdf";
	/**
	 * Set file type as xls.
	 */
	private static final String FILE_TYPE_XLS = "xls";
	/**
	 * Set file type as txt.
	 */
	private static final String FILE_TYPE_TXT = "txt";
	/**
	 * Set file type as xlsx.
	 */
	private static final String FILE_TYPE_XLSX = "xlsx";
	/**
	 * Set file type as jpg.
	 */
	private static final String FILE_TYPE_JPG = "jpg";
	/**
	 * Set file type as doc.
	 */
	private static final String FILE_TYPE_DOC = "doc";
	/**
	 * Set file type as gif.
	 */
	private static final String FILE_TYPE_GIF = "gif";
	/**
	 * Set mime type as acrobat.
	 */
	private static final String MIME_TYPE_ACROBAT = "acrobat";
	/**
	 * Set mime type as msword.
	 */
	private static final String MIME_TYPE_MS = "msword";
	/**
	 * Set mime type as msexcel.
	 */
	private static final String MIME_TYPE_MSXL = "msexcel";
	/**
	 * Set Data path.
	 */
	private static final String DATA_PATH = "data_path";
	/**
	 * Set String indouble qoutes.
	 */
	private static final String UPLOAD = "/upload";
	
	/**
	 * Logger.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApprovalAction.class);
	
	/**
	 * Object of CDRomApprovalBean.
	 */
	private CDRomApprovalBean bean;

	/**
	 * @return the bean
	 */
	public CDRomApprovalBean getBean() {
		return this.bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(final CDRomApprovalBean bean) {
		this.bean = bean;
	}

	/**
	 * @return the logger
	 */
	public  org.apache.log4j.Logger getLogger() {
		return this.logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public  void setLogger(final org.apache.log4j.Logger logger) {
		CDRomApprovalAction.logger = logger;
	}
	
	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() {
		this.bean = new CDRomApprovalBean();
		this.bean.setMenuCode(1182);
	}

	/**
	 * (non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 * purpose - Executes first & Used to set Data Path for file attachments.
	 * Purpose -Used to Set Path.
	 */
	public void prepare_withLoginProfileDetails()  {
		
		/**
		 * Set Path & for getting server path where configuration files are saved.
		 */
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = "/" + poolName;
		}
		/**
		 *  for getting server path where configuration files are saved.
		 */
		final String dataPath = this.getText(CDRomApprovalAction.DATA_PATH) + CDRomApprovalAction.UPLOAD + poolName + " /CDROM/";

		final String dataPath1 = this.getText(CDRomApprovalAction.DATA_PATH) + CDRomApprovalAction.UPLOAD + poolName + " /CDROMAddressing/";
		
		final String dataPath2 = this.getText(CDRomApprovalAction.DATA_PATH) + CDRomApprovalAction.UPLOAD + poolName + " /CDROMAdditional/";
		
		
		
		
		this.bean.setDataPath(dataPath);
		this.bean.setDataPathAddressing(dataPath1);
		this.bean.setDataPathAdditional(dataPath2);
	}
	
	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return Object.
	 */
	public Object getModel() {
		return this.bean;
		
	}
	
	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * purpose - Used to get Pending List. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String input() throws Exception {
		try {
			this.getPendingList();
			
			return "input";
		} catch (final Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Purpose - Used for Pagination.
	 * @return String.
	 */
	public String callPage() {
		return "success";
	}

	
	
	/**
	 * Purpose - Get Forwarded List ,Pending List,Pending canceled application list.
	 * @return String.
	 */
	public String getPendingList() {
		final CDRomApprovalModel model = new CDRomApprovalModel();
		model.initiate(context, session);

		final String userId = this.bean.getUserEmpId();
	
		final String pageForPendingApp = this.bean.getPageForPendingApp();
		final String pageForPendingCancelApp = this.bean.getPageForPendingCancelApp();
		final boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if (isUserAHRApprover) {
			
			System.out.println("in isUserAHRApprover hello");
			/**
			 * Forwarded application list
			 */
			final List<CDRomApprovalBean> forwardedAppList = model.getForwardedApplicationList(this.bean, pageForPendingApp, request);
			if (forwardedAppList != null && !forwardedAppList.isEmpty()) {
				this.bean.setPagingForPendingApp(true);
			} else {
				this.bean.setPagingForPendingApp(false);
			}
			this.bean.setPendingAppList(forwardedAppList);

			/**
			 * Pending cancelled application list
			 */
			final  List<CDRomApprovalBean> pendingCancelAppList = model.getPendingCancellationApplicationList(pageForPendingCancelApp, request);
			if (pendingCancelAppList != null && !pendingCancelAppList.isEmpty()) {
				this.bean.setPagingForPendingCancelApp(true);
			} else {
				this.bean.setPagingForPendingCancelApp(false);
			}
			this.bean.setPendingCancelAppList(pendingCancelAppList);
		} else {
			
			final List<CDRomApprovalBean> pendingAppList = model.getPendingApplicationList(userId, pageForPendingApp, request);
			if (pendingAppList != null && !pendingAppList.isEmpty()) {
				this.bean.setPagingForPendingApp(true);
			} else {
				this.bean.setPagingForPendingApp(false);
			}
			this.bean.setPendingAppList(pendingAppList);

			/**
			 * Pending cancelled application list
			 */
			final List<CDRomApprovalBean> pendingCancelAppList = model.getPendingCancellationApplicationList(userId, pageForPendingCancelApp, request);
			if (pendingCancelAppList != null && !pendingCancelAppList.isEmpty()) {
				this.bean.setPagingForPendingCancelApp(true);
			} else {
				this.bean.setPagingForPendingCancelApp(false);
			}
			this.bean.setPendingCancelAppList(pendingCancelAppList);
		}

		this.bean.setListType("pending");

		model.terminate();
		return SUCCESS;
	}
	
	
	/**
	 * purpose - Used to display Approved Application List.
	 * @return String.
	 */
	public String getApprovedList() {
		final CDRomApprovalModel model = new CDRomApprovalModel();
		model.initiate(context, session);

		final String userId = this.bean.getUserEmpId();
		final String pageForApprovedApp = this.bean.getPageForApprovedApp();
		final boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if (isUserAHRApprover) {
			final List<CDRomApprovalBean> approvedAppList = model.getApprovedApplicationList(pageForApprovedApp, request);
			if (approvedAppList != null && !approvedAppList.isEmpty()) {
				this.bean.setPagingForApprovedApp(true);
			} else {
				this.bean.setPagingForApprovedApp(false);
			}
			this.bean.setApprovedAppList(approvedAppList);
		} else {
			final List<CDRomApprovalBean> approvedAppList = model.getApprovedApplicationList(userId, pageForApprovedApp, request);
			if (approvedAppList != null && !approvedAppList.isEmpty()) {
				this.bean.setPagingForApprovedApp(true);
			} else {
				this.bean.setPagingForApprovedApp(false);
			}
			this.bean.setApprovedAppList(approvedAppList);
		}

		this.bean.setListType("approved");

		model.terminate();
		return SUCCESS;
	}
	

	/**
	 * purpose - Used to display Rejected Application List.
	 * @return String.
	 */
	public String getRejectedList() {
		final CDRomApprovalModel model = new CDRomApprovalModel();
		model.initiate(context, session);

		final String userId = this.bean.getUserEmpId();
		final String pageForRejectedApp = this.bean.getPageForRejectedApp();
		final boolean isUserAHRApprover = model.isUserAHRApprover(userId);

		if (isUserAHRApprover) {
			final List<CDRomApprovalBean> rejectedAppList = model.getRejectedApplicationList(pageForRejectedApp, request);
			if (rejectedAppList != null && !rejectedAppList.isEmpty()) {
				this.bean.setPagingForRejectedApp(true);
			} else {
				this.bean.setPagingForRejectedApp(false);
			}
			this.bean.setRejectedAppList(rejectedAppList);
		} else {
			final List<CDRomApprovalBean> rejectedAppList = model.getRejectedApplicationList(userId, pageForRejectedApp, request);
			if (rejectedAppList != null && !rejectedAppList.isEmpty()) {
				this.bean.setPagingForRejectedApp(true);
			} else {
				this.bean.setPagingForRejectedApp(false);
			}
			this.bean.setRejectedAppList(rejectedAppList);
		}

		this.bean.setListType("rejected");

		model.terminate();
		return SUCCESS;
	}

	
	/**
	 * @throws IOException - IOException ---added by nilesh.
	 */
	public void viewAttachedProof() throws IOException {
		final String addedFile = this.bean.getRequestDetailFile();
		final String[] extension = addedFile.replace(CDRomApprovalAction.SPEL_CHAR_DOT, CDRomApprovalAction.SPEL_CHAR_HASH).split(CDRomApprovalAction.SPEL_CHAR_HASH);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.bean.getDataPath();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = CDRomApprovalAction.FILE_TYPE_PDF;
			final String applnDoc = CDRomApprovalAction.FILE_TYPE_DOC;
			final String applnXls = CDRomApprovalAction.FILE_TYPE_XLS;
			final String applnXlsx = CDRomApprovalAction.FILE_TYPE_XLSX;
			final String applnJpg = CDRomApprovalAction.FILE_TYPE_JPG;
			final String applnTxt = CDRomApprovalAction.FILE_TYPE_TXT;
			final String applnGif = CDRomApprovalAction.FILE_TYPE_GIF;
              
			final String mimeTypeAcrobat = CDRomApprovalAction.MIME_TYPE_ACROBAT;
			final String mimeTypeMSWord = CDRomApprovalAction.MIME_TYPE_MS;
			final String mimeTypeMSExcel = CDRomApprovalAction.MIME_TYPE_MSXL;
			final String mimeTypeJpg = CDRomApprovalAction.FILE_TYPE_JPG;
			final String mimeTypeTxt = CDRomApprovalAction.FILE_TYPE_TXT;
			final String mimeTypeGif = CDRomApprovalAction.FILE_TYPE_GIF;
              
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
	
	/**
	 * @throws IOException -IOException.
	 */
	public void viewAttachedProofAddressing() throws IOException {

		final String addedFile = this.bean.getAddressInfoFile1();
		final String[] extension = addedFile.replace(CDRomApprovalAction.SPEL_CHAR_DOT, CDRomApprovalAction.SPEL_CHAR_HASH).split(CDRomApprovalAction.SPEL_CHAR_HASH);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.bean.getDataPathAddressing();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = CDRomApprovalAction.FILE_TYPE_PDF;
			final String applnDoc = CDRomApprovalAction.FILE_TYPE_DOC;
			final String applnXls = CDRomApprovalAction.FILE_TYPE_XLS;
			final String applnXlsx = CDRomApprovalAction.FILE_TYPE_XLSX;
			final String applnJpg = CDRomApprovalAction.FILE_TYPE_JPG;
			final String applnTxt = CDRomApprovalAction.FILE_TYPE_TXT;
			final String applnGif = CDRomApprovalAction.FILE_TYPE_GIF;
              
			final String mimeTypeAcrobat = CDRomApprovalAction.MIME_TYPE_ACROBAT;
			final String mimeTypeMSWord = CDRomApprovalAction.MIME_TYPE_MS;
			final String mimeTypeMSExcel = CDRomApprovalAction.MIME_TYPE_MSXL;
			final String mimeTypeJpg = CDRomApprovalAction.FILE_TYPE_JPG;
			final String mimeTypeTxt = CDRomApprovalAction.FILE_TYPE_TXT;
			final String mimeTypeGif = CDRomApprovalAction.FILE_TYPE_GIF;
              
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
	
	/**
	 * @throws IOException - IOException.
	 */
	public void viewAttachedProofAdditional() throws IOException {
		final String addedFile = this.bean.getAddressInfoFile2();
		final String[] extension = addedFile.replace(CDRomApprovalAction.SPEL_CHAR_DOT, CDRomApprovalAction.SPEL_CHAR_HASH).split(CDRomApprovalAction.SPEL_CHAR_HASH);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.bean.getDataPathAdditional();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = CDRomApprovalAction.FILE_TYPE_PDF;
			final String applnDoc = CDRomApprovalAction.FILE_TYPE_DOC;
			final String applnXls = CDRomApprovalAction.FILE_TYPE_XLS;
			final String applnXlsx = CDRomApprovalAction.FILE_TYPE_XLSX;
			final String applnJpg = CDRomApprovalAction.FILE_TYPE_JPG;
			final String applnTxt = CDRomApprovalAction.FILE_TYPE_TXT;
			final String applnGif = CDRomApprovalAction.FILE_TYPE_GIF;
              
			final String mimeTypeAcrobat = CDRomApprovalAction.MIME_TYPE_ACROBAT;
			final String mimeTypeMSWord = CDRomApprovalAction.MIME_TYPE_MS;
			final String mimeTypeMSExcel = CDRomApprovalAction.MIME_TYPE_MSXL;
			final String mimeTypeJpg = CDRomApprovalAction.FILE_TYPE_JPG;
			final String mimeTypeTxt = CDRomApprovalAction.FILE_TYPE_TXT;
			final String mimeTypeGif = CDRomApprovalAction.FILE_TYPE_GIF;
              
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

}
