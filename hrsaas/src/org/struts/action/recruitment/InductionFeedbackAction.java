package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.Recruitment.InductionFeedback;
import org.paradyne.model.recruitment.InductionFeedbackModel;
import org.struts.lib.ParaActionSupport;

public class InductionFeedbackAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InductionFeedbackAction.class);
	InductionFeedback indFeed;

	public InductionFeedback getIndFeed() {
		return indFeed;
	}

	public void setIndFeed(InductionFeedback indFeed) {
		this.indFeed = indFeed;
	}

	public void prepare_local() throws Exception {
		indFeed = new InductionFeedback();
		indFeed.setMenuCode(354);
	}

	public Object getModel() {
		return indFeed;
	}

	public String input() throws Exception {
		try {
			indFeed.setInductionName("");
			indFeed.setInductionFrom("");
			indFeed.setInductionTo("");
			indFeed.setInducVenue("");
			indFeed.setInducDesc("");
			indFeed.setContactPerson("");
			indFeed.setInductionName("");
			indFeed.setInductionCode("");
			indFeed.setSuggestion("");
			indFeed.setAttachFile("");
			indFeed.setDataPath("");
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		try {
			InductionFeedbackModel model = new InductionFeedbackModel();
			model.initiate(context, session);
			String[] quesCode = (String[]) request
					.getParameterValues("quesCode");
			String[] rating = (String[]) request.getParameterValues("rating");
			String[] comment = (String[]) request
					.getParameterValues("comments");
			String message = model.save(indFeed, request, quesCode, rating,
					comment);
			if (message == "1") {
				addActionMessage("Feedback submitted successfully");
			}
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return SUCCESS;
	}

	public String viewFeedback() throws Exception {
		try {
			String empCode = request.getParameter("empCode");
			String inducCode = request.getParameter("inducCode");
			InductionFeedbackModel model = new InductionFeedbackModel();
			model.initiate(context, session);
			// For adding data path BEGINS
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			
			String fileDataPath = getText("data_path") + "/upload" + poolName
					+ "/Recruitment/";
			System.out.println("data path : " + fileDataPath);
			indFeed.setDataPath(fileDataPath);
			// For adding data path ENDS
			model.showFeedbackdetails(indFeed, request, empCode, inducCode);
			if(!indFeed.getAttachFile().equals("") ) {
				indFeed.setShowAttachedFileLink(true);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFeedback";
	}

	public String getQuestions() throws Exception {
		try {
			indFeed.setInductionName(indFeed.getInductionName());
			InductionFeedbackModel model = new InductionFeedbackModel();
			model.initiate(context, session);
			model.getrecord(indFeed, request);
			model.terminate();

			// For adding data path BEGINS
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			System.out.println("getText data_path ::::>>>>>>"
					+ getText("data_path"));
			String fileDataPath = getText("data_path") + "/upload" + poolName
					+ "/Recruitment/";
			System.out.println("data path : " + fileDataPath);
			indFeed.setDataPath(fileDataPath);
			// For adding data path ENDS

			indFeed.setShowUploadButton(true);
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("exception in navigation pannel", e);
		}
		return SUCCESS;
	}

	/**
	 * this method is to display all the conducted inductions
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Search() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT INDUC_NAME,TO_CHAR(INDUC_FROMDATE,'DD-MM-YYYY'),TO_CHAR(INDUC_TODATE,'DD-MM-YYYY'), "
				+ " INDUC_DTLCODE,HRMS_REC_INDUC_PARTI.INDUC_CODE,INDUC_DESC,INDUC_VENUE,INDUC_CONTACT,EMP_FNAME||' '||EMP_LNAME "
				+ " FROM HRMS_REC_INDUC_PARTI "
				+ " INNER JOIN HRMS_REC_INDUC_HDR ON (HRMS_REC_INDUC_HDR.INDUC_CODE = HRMS_REC_INDUC_PARTI.INDUC_CODE) "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_INDUC_HDR.INDUC_CONTACT) "
				+ " WHERE INDUC_EMPID = "
				+ indFeed.getUserEmpId()
				+ " AND ( INDUC_FEED_STATUS ='N' OR INDUC_FEED_STATUS  IS NULL ) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("indnamesearch"),
				getMessage("frmdate"), getMessage("toDate") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "50", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "inductionName", "inductionFrom",
				"inductionTo", "inductionDtlCode", "inductionCode",
				"inducDesc", "inducVenue", "cntPersonId", "contactPerson" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "InductionFeedback_getQuestions.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public void viewAttachedFile() throws Exception {
		try {
			String attachFile = request.getParameter("attachFile");
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
				fileName = attachFile;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				System.out.println("@@@@@@@@@@@@@@@@@ File Name : " + fileName);
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

}
