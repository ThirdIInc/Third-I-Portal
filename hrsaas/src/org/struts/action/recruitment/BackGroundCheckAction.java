package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.Recruitment.backGroundCheck;
import org.paradyne.model.recruitment.backGroundCheckModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author saipavan voleti
 *  
 */

public class BackGroundCheckAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BackGroundCheckAction.class);

	backGroundCheck bgcheck;

	public void prepare_local() throws Exception {
		bgcheck = new backGroundCheck();
		bgcheck.setMenuCode(786);
	}

	public Object getModel() {
		return bgcheck;
	}

	public backGroundCheck getBgcheck() {
		return bgcheck;
	}

	public void setBgcheck(backGroundCheck bgcheck) {
		this.bgcheck = bgcheck;
	}

	 
	public String f9search() {
		try {
			String query = " SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME AS NAME,"
					+ " HRMS_REC_REQS_HDR.REQS_NAME, BG_CODE, BG_REQS_CODE, BG_CAND_CODE, BG_CHECK_LIST,"
					+ " HRMS_REC_PARTNER.REC_PARTNERNAME, BG_AGENCYCODE, HRMS_REC_OFFER.OFFER_CODE "
					+ " FROM HRMS_REC_BGCHECK "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_BGCHECK.BG_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_BGCHECK.BG_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " LEFT JOIN HRMS_REC_PARTNER ON (HRMS_REC_PARTNER.REC_CODE=HRMS_REC_BGCHECK.BG_AGENCYCODE)"
					+" LEFT JOIN HRMS_REC_OFFER ON(HRMS_REC_OFFER.OFFER_REQS_CODE=HRMS_REC_BGCHECK.BG_REQS_CODE AND  HRMS_REC_OFFER.OFFER_CAND_CODE=HRMS_REC_BGCHECK.BG_CAND_CODE )"
					+ " ORDER BY BG_CODE  ";
			String[] headers = { getMessage("cand.name"),
					getMessage("reqs.code") };
			String[] headerWidth = { "50", "40" };  
			String[] fieldNames = { "candidateName", "reqName", "bgcheckCode",
					"reqCode", "candidateCode", "checkListType",
					"outsourceAgencyName", "outsourceAgencyCode", "Chkoffercode" };

			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
			String submitFlag = "true";
			String submitToMethod = "BackGroundCheck_f9searchdtl.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		}  
	}

	public String f9searchdtl() {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			if (String.valueOf(bgcheck.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(bgcheck
						.getCheckListType()));
			}
			// For adding data path BEGINS
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String fileDataPath = getText("data_path") + "/upload" + poolName
					+ "/Recruitment/";
			bgcheck.setDataPath(fileDataPath);
			// For adding data path ENDS
			bgcheck.setChkoffercode(bgcheck.getChkoffercode());
			bgmodel.f9searchdtl(bgcheck, bgcheck.getReqCode(), bgcheck.getCandidateCode(), 
								bgcheck.getBgcheckCode(),bgcheck.getCheckListType());
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return "PageView";

	}

	/**Added by Pradeep on Date:17-06-2009 
	 * following function is called to set the offer status of the candidate when 
	 * the corresponding candidate is selected from the candidate pop up window. 
	 * @return
	 */
	public String getOffStatus() {
		try {
			backGroundCheckModel model = new backGroundCheckModel();
			model.initiate(context, session);
			model.showOffer(bgcheck);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success1";
	}

	/*
	 * Pop up window for Candidate List 
	 *  @return String f9page
	 */
	public String f9Candidate() {
		try {
			String query = "SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,CAND_CODE from HRMS_REC_CAND_DATABANK order by CAND_FIRST_NAME ";
			String[] headers = { getMessage("cand.name") };
			String[] headerWidth = { "80" };
			String[] fieldNames = { "candidateName", "candidateCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";  
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {

			return "";
		} //end of try-catch block
	}

	/*
	 * Pop up window for Requisition 
	 *  @return String f9page
	 */

	public String f9Requistion() {
		try {
			String query = " SELECT REQS_NAME,RANK_NAME,"
					+ " HRMS_DIVISION.DIV_NAME,HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_NAME,REQS_CODE"
					+ " FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					//+" WHERE REQS_APPROVAL_STATUS IN ('A','Q')"
					+ " ORDER BY REQS_CODE ASC";
			//	String[] headers = { "Requisition Code","Position","Division","Branch","Department" };
			String[] headers = { getMessage("reqs.code"),
					getMessage("position"), getMessage("division"),
					getMessage("branch"), getMessage("department") };
			String[] headerWidth = { "20", "20", "20", "20", "20" };
			String[] fieldNames = { "reqName", "position", "division",
					"branch", "department", "reqCode" };
			int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
			String submitFlag = "true";
			String submitToMethod = "BackGroundCheck_showOfferSTATUS.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} //end of try-catch block
	}
	
	
	
	public String showOfferSTATUS() {
		try {
			backGroundCheckModel model = new backGroundCheckModel();
			model.initiate(context, session);
			String query = "SELECT CASE WHEN OFFER_STATUS='D' THEN 'Due' WHEN OFFER_STATUS='P' THEN 'Pending' WHEN  OFFER_STATUS='A' THEN 'Approved'"
					+ " WHEN OFFER_STATUS='R' THEN 'Approval Rejection'  WHEN OFFER_STATUS='I' THEN 'Issued' WHEN OFFER_STATUS='OA' THEN 'Accepted'"
					+ " WHEN OFFER_STATUS='S' THEN 'Rejected' WHEN OFFER_STATUS='C' THEN 'Canceled' END "
					+ " FROM HRMS_REC_OFFER WHERE OFFER_CAND_CODE="+ bgcheck.getCandidateCode() +" and OFFER_REQS_CODE = "+bgcheck.getReqCode();
			Object obj[][] = model.getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				bgcheck.setOfferStatus(String.valueOf(obj[0][0]));
			} else {
				bgcheck.setOfferStatus("Not Given");
			}
			
			if (!String.valueOf(bgcheck.getCheckListType()).trim().equals("S")) {
				request.setAttribute("listname", String.valueOf(bgcheck.getCheckListType()));
				model.f9searchdtl(bgcheck, bgcheck.getReqCode(), bgcheck.getCandidateCode(), 
								bgcheck.getBgcheckCode(), bgcheck.getCheckListType());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getNavigationPanel(2);
		return "success1";
	}
	
	
	/*
	 * Pop up window for Out source Agency 
	 *  @return String f9page
	 */
	public String f9outsource() {
		try {
			String query = " SELECT REC_PARTNERNAME,DECODE(REC_TYPE,'C ','Consultant','AA','Advertising Agency','O ','Outsourcing Agency'),REC_CODE FROM HRMS_REC_PARTNER ORDER BY REC_CODE ";
			String[] headers = { "Outsource Agency Name", "Partner Type" };
			String[] headerWidth = { "40", "40" };
			String[] fieldNames = { "outsourceAgencyName", "partnertype",
					"outsourceAgencyCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} //end of try-catch block
	}

	/*
	 * method for setting check list details.
	 */
	public String showCheckList() {
		try {
			getNavigationPanel(2);
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			if (String.valueOf(bgcheck.getCheckListType()) != "") {

				bgcheck.setHiddenstatus(bgcheck.getCheckListType());
				request.setAttribute("listname", String.valueOf(bgcheck
						.getCheckListType()));

				bgmodel.showCheckList(bgcheck);
			}
			bgmodel.terminate();
		} catch (Exception e) {

		}
		return "success1";
	}

	/*
	 *  @ saving back ground check application details.
	 */

	public String save() {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			bgcheck.setChecklistTable(true);
			boolean res = false;
			Object[] ckcode = request.getParameterValues("checkListitemcode");
			Object[] Response = request.getParameterValues("checkListresponce");
			Object[] comments = request.getParameterValues("checkListComments");
			Object[] dtlproof = request.getParameterValues("uploadLocFileName");
			if (String.valueOf(bgcheck.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(bgcheck
						.getCheckListType()));
			}
			if (bgcheck.getBgcheckCode().equals("")) {
				res = bgmodel.save(bgcheck, ckcode, Response, comments,
						dtlproof);

				if (res) {
					addActionMessage("Record saved successfully.");
					getNavigationPanel(3);
				} else {
					addActionMessage("Record can not saved.");
					getNavigationPanel(2);
				}

			} else {
				res = bgmodel.update(bgcheck, ckcode, Response, comments,
						dtlproof);

				if (res) {
					addActionMessage("Record updated successfully.");
					getNavigationPanel(3);
				} else {
					addActionMessage("Record can not updated.");
					getNavigationPanel(2);
				}
			}
			bgmodel.f9searchdtl(bgcheck, bgcheck.getReqCode(), bgcheck.getCandidateCode(), bgcheck.getBgcheckCode(),bgcheck.getCheckListType());
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PageView";
	}

	/*
	 *  @ setting conducted back ground check application details.
	 */

	public String conducted() {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			if (bgcheck.getChkreqcode() != "")
				if (bgcheck.getChkcandidatecode() != "")
					bgmodel.conducted(bgcheck);
			
			//For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String fileDataPath = getText("data_path") + "/upload" + poolName + "/Recruitment/";
			bgcheck.setDataPath(fileDataPath);
			//For adding data path ENDS
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success1";
	}

	public String callstatus() { //retrieving application details 
		try {
			//Please don not remove below Line i.e. bgcheck.setMenuCode(786) 
			// as this method is from back button of the Requisition form i.e. employeeReqView.jsp
			bgcheck.setMenuCode(786);
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			//String status = request.getParameter("bgStatus");
			String status = request.getParameter("status");
			if (status.equals("C")) {
				bgcheck.setBgStatus("C");
				status = "C";
				bgcheck.setConduct("true");
				getNavigationPanel(1);
			} else {
				bgcheck.setBgStatus("P");
				status = "P";
				bgcheck.setConduct("false");
				getNavigationPanel(6);
			}
			request.setAttribute("stat", status);
			bgmodel.setBackgroundList(bgcheck, status, request);
			bgmodel.terminate();
		} catch (Exception e) {

		}
		return "success";
	}

	/*
	 *  @ setting pending back ground check application details.
	 */
	public String input() throws Exception {
		/*Default Method with default modeCode(1)*/
		getNavigationPanel(6);
		backGroundCheckModel bgmodel = new backGroundCheckModel();
		bgmodel.initiate(context, session);
		bgmodel.setBackgroundList(bgcheck, "P", request);
		bgmodel.terminate();
		return "success";
	}

	public void viewAttachedProof() throws Exception {
		try {
			String uploadFileName = request.getParameter("fileName");
			String dataPath = request.getParameter("dataPath");
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} else if (ext.equals("doc")) {
					mimeType = "msword";
				} else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} else if (ext.equals("txt")) {
					mimeType = "txt";
				} else if (ext.equals("gif")) {
					mimeType = "gif";
				}
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					}
				}

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename=\""
						+ fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}

			} catch (FileNotFoundException e) {

				addActionMessage("proof document not found");
			} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *  @ new back ground check application.
	 */
	public String addNew() throws Exception {
		getNavigationPanel(2);
		backGroundCheckModel bgmodel = new backGroundCheckModel();
		bgmodel.initiate(context, session);
		try {
			//For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String fileDataPath = getText("data_path") + "/upload" + poolName + "/Recruitment/";
			bgcheck.setDataPath(fileDataPath);
			//For adding data path ENDS
		} catch (Exception e) {
			e.printStackTrace();
		}
		reset();
		bgmodel.terminate();
		return "success1";
	}

	/*
	 *  @ delete back ground check application.
	 */
	public String delete() throws Exception {
		try {
			backGroundCheckModel model = new backGroundCheckModel();
			model.initiate(context, session);
			boolean result;
			result = model.deleteBgcheck(bgcheck);
			if (result) {
				getNavigationPanel(2);
				addActionMessage("Record deleted successfully !");
			} else {
				getNavigationPanel(3);
				addActionMessage("Record can't be deleted as \n it is associated with some other record");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return "success";
	}

	/*
	 *  @ editing back ground check application.
	 */

	public String edit() throws Exception {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			if (String.valueOf(bgcheck.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(bgcheck
						.getCheckListType()));
			}
			bgmodel.bgCheckRecord(bgcheck);
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success1";

	}

	public String cancelThrd() throws Exception {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			if (String.valueOf(bgcheck.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(bgcheck
						.getCheckListType()));
			}
			bgmodel.bgCheckRecord(bgcheck);
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return "PageView";

	}

	public String cancelFirst() throws Exception {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			reset();
			bgmodel.setBackgroundList(bgcheck, "P", request);
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(6);
		return "success";

	}

	public String cancelSec() throws Exception {
		
		try {
			System.out.println("u r at cancelSec  222");
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			if (String.valueOf(bgcheck.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(bgcheck
						.getCheckListType()));
			}
			bgmodel.bgCheckRecord(bgcheck);
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success1";

	}

	public String cancelFrth() throws Exception {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			bgmodel.setBackgroundList(bgcheck, "P", request);
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(6);
		return "success";

	}

	public String callPage() throws Exception {
		
		try {
			backGroundCheckModel model = new backGroundCheckModel();
			model.initiate(context, session);
			String status = bgcheck.getBgStatus();
			if (status.equals("C")) {
				bgcheck.setBgStatus("C");
				status = "C";
				bgcheck.setConduct("true");
				getNavigationPanel(1);
			} else {
				bgcheck.setBgStatus("P");
				status = "P";
				bgcheck.setConduct("false");
				getNavigationPanel(6);
			}
			request.setAttribute("stat", status);
			model.setBackgroundList(bgcheck, status, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//reset();
		
		return "success";
	}

	public void reset() {
		backGroundCheckModel bgmodel = new backGroundCheckModel();
		bgmodel.initiate(context, session);

		bgcheck.setBgcheckCode("");
		bgcheck.setCandidateName("");
		bgcheck.setCandidateCode("");
		bgcheck.setOfferStatus("");
		bgcheck.setReqCode("");
		bgcheck.setReqName("");
		bgcheck.setDivision("");
		bgcheck.setBranch("");
		bgcheck.setDepartment("");
		bgcheck.setPosition("");
		bgcheck.setDupbgCheckType("");
		bgcheck.setOutsourceAgencyName("");
		bgcheck.setOutsourceAgencyCode("");
		bgcheck.setCheckListType("");
		bgcheck.setDupcheckListType("");
		bgcheck.setCheckListCode("");
		bgcheck.setOverallComments("");
		//bgcheck.setChecklistTable("");
		bgcheck.setCheckListresponce("");
		bgcheck.setDupcheckListresponce("");
		bgcheck.setCheckListComments("");
		bgcheck.setCheckListitemcode("");
		bgcheck.setCheckListName("");
		bgcheck.setLcandidate("");
		bgcheck.setLcancode("");
		bgcheck.setLoffercode("");
		bgcheck.setLreqcode("");
		bgcheck.setLreqName("");
		bgcheck.setLposition("");
		bgcheck.setLofferstatus("");
		bgcheck.setLchecklistType("");
		bgcheck.setBgStatus("");
		bgcheck.setConduct("");
		bgcheck.setChkLength("");
		bgcheck.setBgpendinglistLength("");
		bgcheck.setBgconductlistLength("");
		bgcheck.setChkreqcode("");
		bgcheck.setChkoffercode("");
		bgcheck.setChkcandidatecode("");

		bgmodel.terminate();

	}

	/*
	 *  @ showing candidate CV.
	 */
	public void viewCV() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/resume/" + fileName;
			oStream = response.getOutputStream();

			response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {

			addActionMessage("Resume not found");
		} catch (Exception e) {

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

	/*public String addMultipleProof() {
		getNavigationPanel(2);
		try {
			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");
			String[] proofFileName = request
					.getParameterValues("proofFileName");
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			bgmodel.displayIteratorValueForUploadProof(srNo, proofName, bgcheck);
			bgmodel.setProofList(srNo, proofName, bgcheck);
			
			bgcheck.setUploadLocFileName("");
			
			setItteratorData();
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "dtl";
	}*/

	/*public void setItteratorData() {

		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] proofIt = request.getParameterValues("proofIt");
			String[] proofRequiredIt = request
					.getParameterValues("proofRequiredIt");
			String[] proofName = request.getParameterValues("proofName");
			String[] proofNameItt = request.getParameterValues("ittproofName");
			String[] itteratorProofNameForSave = request
					.getParameterValues("itteratorProofNameForSave");
			String[] policyViolationTextIt = request
					.getParameterValues("policyViolationTextIt");

			bgmodel.setItteratorValues(bgcheck, request, srNo, proofIt, proofRequiredIt, 
					 proofName, proofNameItt,
					itteratorProofNameForSave, policyViolationTextIt);
			bgmodel.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	
	
	public String backGroundCheckMailAlert() {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			//bgmodel.setSavedDtlRecord(bgcheck, request);
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "backGroundCheckMailMesageJsp";
	}
	
	public String sendMailForBackGroundCheck() {
		try {
			backGroundCheckModel bgmodel = new backGroundCheckModel();
			bgmodel.initiate(context, session);
			boolean result = bgmodel.sendBackgroundMailAlert(bgcheck);
			if (result) {
				addActionMessage("Mail successfully send to previous employer.");
			} else {
				addActionMessage("Unable to  send mail to previous employer.");
			}
			bgmodel.conducted(bgcheck);
			bgmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "backGroundCheckMailMesageJsp";
	}
	
	
	public void viewAttachedFile() throws Exception {
		try {
			String attachedFileName = request.getParameter("attachedFileName");
			String dataPath = request.getParameter("dataPath");
			System.out.println("Data Path >>>>>>>"+dataPath);
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				fileName = attachedFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				System.out.println("@@@@@@@@@@@@@@@@@ File Name : "+fileName);
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} else if (ext.equals("doc")) {
					mimeType = "msword";
				} else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} else if (ext.equals("txt")) {
					mimeType = "txt";
				} else if (ext.equals("gif")) {
					mimeType = "gif";
				}
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					}
				}

				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					 
				} else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename=\""
						+ fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}

			} catch (FileNotFoundException e) {

				addActionMessage("proof document not found");
			} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
public String showBackGroundDetails(){
	try {
		backGroundCheckModel bgmodel = new backGroundCheckModel();
		bgmodel.initiate(context, session);
		// For adding data path BEGINS
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		String fileDataPath = getText("data_path") + "/upload" + poolName
				+ "/Recruitment/";
		bgcheck.setDataPath(fileDataPath);
		// For adding data path ENDS
		String requisitionCode = request.getParameter("requisitionCode");
		String candidateCode = request.getParameter("candidateCode");
		String backgroundCheckCode = request.getParameter("backgroundCheckCode");
		String backgroundCheckListType = request.getParameter("backgroundCheckListType");
		String backgroundCheckOfferCode = request.getParameter("offerCode");
		bgcheck.setBgcheckCode(backgroundCheckCode);
		bgcheck.setChkoffercode(backgroundCheckOfferCode);
		bgmodel.f9searchdtl(bgcheck, requisitionCode, candidateCode, backgroundCheckCode, backgroundCheckListType);
		bgmodel.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	getNavigationPanel(3);
	return "PageView";
}

	
}
