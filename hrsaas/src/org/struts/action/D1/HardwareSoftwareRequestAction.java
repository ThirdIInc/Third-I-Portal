package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.D1.HardwareSoftwareRequest;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.HardwareSoftwareRequestModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.lib.ParaActionSupport;

public class HardwareSoftwareRequestAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HardwareSoftwareRequestAction.class);

	HardwareSoftwareRequest hrdsoftBean;

	public void prepare_local() throws Exception {
		hrdsoftBean = new HardwareSoftwareRequest();
		hrdsoftBean.setMenuCode(1184);
	}

	public Object getModel() {
		return hrdsoftBean;
	}

	public HardwareSoftwareRequest getHrdsoftBean() {
		return hrdsoftBean;
	}

	public void setHrdsoftBean(HardwareSoftwareRequest hrdsoftBean) {
		this.hrdsoftBean = hrdsoftBean;
	}

	public String input() {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			String userId = hrdsoftBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getPendingList(hrdsoftBean, request, userId);
			}
			hrdsoftBean.setListType("pending");
			model.terminate();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String getApprovedList() throws Exception {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			String userId = hrdsoftBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(hrdsoftBean, request, userId);
			}
			hrdsoftBean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return INPUT;
	}

	public String getCancelledList() throws Exception {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			String userId = hrdsoftBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getCancelledList(hrdsoftBean, request, userId);
			}
			hrdsoftBean.setListType("cancelled");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return INPUT;
	}

	public String getRejectedList() throws Exception {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			String userId = hrdsoftBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(hrdsoftBean, request, userId);
			}
			hrdsoftBean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String back() throws Exception {
		String listType = hrdsoftBean.getListType();
		hrdsoftBean.setHwSwID("");
		if(listType.equals("pending")) {
			input();
		} else if(listType.equals("approved")) {
			getApprovedList();
		} else {
			getRejectedList();
		}
		return INPUT;
	}

	public String addNew() {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			model.getLoginUserInformation(hrdsoftBean);
			setApproverName();
			setApproverList(hrdsoftBean.getRequesterID());
			hrdsoftBean.setApplicationStatus("");
			
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			// for getting server path where configuration files are saved.
			String dataPath = getText("data_path") + "/upload" + poolName
					+ "/HardwareSoftware/";

			hrdsoftBean.setDataPath(dataPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return SUCCESS;
	}

	public void setApproverName() {
		HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
		try {
			model.initiate(context, session);
			Object[][] empFlow = null;
			try {
				empFlow = generateEmpFlow(hrdsoftBean.getRequesterID(), "D1", 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {
				String query = " SELECT EMP_FNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC "
						+ " WHERE EMP_ID=" + String.valueOf(empFlow[0][0]);
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {

					hrdsoftBean.setApproverName(String.valueOf(data[0][0]));
					hrdsoftBean
							.setFirstApproverCode(String.valueOf(data[0][1]));
				}
			} else {
				hrdsoftBean.setFirstApproverCode("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}

	public void setApproverList(String empCode) {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			Object[][] empFlow = null;
			try {
				empFlow = model1.generateEmpFlow(empCode, "D1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {
				model.setApproverData(hrdsoftBean, empFlow);
			}
			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String draftFunction() {
		boolean result = false;
		HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
		model.initiate(context, session);

		if (hrdsoftBean.getHwSwID().equals("")) {
			result = model.draftFunction(hrdsoftBean, request);

			if (result) {
				addActionMessage(getMessage("save"));
				reset();
			} else {
				addActionMessage(getMessage("save.error"));
			}
		} else { // update search details
			result = model.updateRecords(hrdsoftBean, request);

			if (result) {
				addActionMessage(getMessage("update"));
				reset();
			} else {
				addActionMessage(getMessage("update.error"));
			}
		}
		input();
		return INPUT;
	}

	public String sendForApprovalFunction() {

		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			boolean result = model
					.sendForApprovalFunction(hrdsoftBean, request);
			String applicationId = hrdsoftBean.getHwSwID();
			String userID = hrdsoftBean.getUserEmpId();
			String status = hrdsoftBean.getStatus();
			String approverCode = "0";
			if (!hrdsoftBean.getSelectApproverCode().equals("")) {
				approverCode = hrdsoftBean.getSelectApproverCode();
			} else {
				approverCode = hrdsoftBean.getFirstApproverCode();
			}
			model.terminate();
			if (result) {
				addActionMessage("Application sent for approval.\n Tracking Number : "
						+ hrdsoftBean.getRequestTrackingNumber());
				try {
					String path=hrdsoftBean.getDataPath();
					String fileanme=hrdsoftBean.getUploadFileName();
					
					sendApplicantToApproverMail(applicationId, userID,
							approverCode, status,path,fileanme);
				} catch (Exception e) {
					logger
							.error("Exception occured in sendForApprovalFunction : "
									+ e);
				}
			} else {
				addActionMessage("Error occured sending application.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	public String reset() {
		hrdsoftBean.setHwSwID("");
		hrdsoftBean.setHardWareCheckbox("");
		hrdsoftBean.setSoftWareCheckbox("");
		hrdsoftBean.setAirCardCheckbox("");
		hrdsoftBean.setRequesterName("");
		hrdsoftBean.setRequesterPhone("");
		hrdsoftBean.setRequesterEmail("");
		hrdsoftBean.setEmpID("");
		hrdsoftBean.setEmpToken("");
		hrdsoftBean.setEmpName("");
		hrdsoftBean.setEmpDept("");
		hrdsoftBean.setEmpCity("");
		hrdsoftBean.setEmpState("");
		hrdsoftBean.setEmpZip("");
		hrdsoftBean.setEmpAddress("");
		hrdsoftBean.setEmpEmail("");
		hrdsoftBean.setEmpPhone("");
		hrdsoftBean.setUserProfile("");
		hrdsoftBean.setUserProfileRadioOptionValue("");
		hrdsoftBean.setOtherProfileExplain("");
		hrdsoftBean.setBussinessExplain("");
		hrdsoftBean.setTypeOfHardwareRequest("");
		hrdsoftBean.setHardwareItemsRequested("");
		hrdsoftBean.setSpecialHardwareRequestedExplain("");
		hrdsoftBean.setSoftwareItemsRequested("");
		hrdsoftBean.setSpecialSoftwareItemsRequested("");
		hrdsoftBean.setDescOfSoftwareRequest("");
		hrdsoftBean.setManufacturer("");
		hrdsoftBean.setCurrentModel("");
		hrdsoftBean.setSerial("");
		hrdsoftBean.setCompName("");
		hrdsoftBean.setOperatingSystem("");
		hrdsoftBean.setDoYouHaveOpenTicketRadio("");
		hrdsoftBean.setDoYouHaveOpenTicketOptionValue("");
		hrdsoftBean.setOpenTicketYES("");
		hrdsoftBean.setProjectSrYES("");
		hrdsoftBean.setZenworkRadio("");
		hrdsoftBean.setZenWorkOptionValue("");
		hrdsoftBean.setAntiVirusRadio("");
		hrdsoftBean.setAntiVirusOptionValue("");
		hrdsoftBean.setSelectApproverToken("");
		hrdsoftBean.setSelectapproverName("");
		hrdsoftBean.setSelectApproverCode("");
		hrdsoftBean.setEmpAttn("");
		hrdsoftBean.setEmpCountry("");
		addNew();
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String showApplicationFunction() {
		String hiddenStatus = request.getParameter("hiddenStatus");
		String applicationID = request.getParameter("applicationID");
		HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
		
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		// for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/upload" + poolName
				+ "/HardwareSoftware/";

		hrdsoftBean.setDataPath(dataPath);
		
		/*
		 * FOR SUPER USER
		 */
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			applicationID=applCode;
		}
		
		try {
			model.initiate(context, session);
			model.showApplicationFunction(hrdsoftBean, applicationID);
		if(!hrdsoftBean.getRequesterID().equals("")){
			setApproverName();
			setApproverList(hrdsoftBean.getRequesterID());
		}
			model.terminate();
		
		if (hiddenStatus.equals("D")) {
			getNavigationPanel(2);
			hrdsoftBean.setEnableAll("Y");
			hrdsoftBean.setApplicationStatus("D");
		} else if (hiddenStatus.equals("P")) {
			getNavigationPanel(3);
			hrdsoftBean.setEnableAll("N");
			hrdsoftBean.setApplicationStatus("P");
		} else if (hiddenStatus.equals("B")) {
			getNavigationPanel(2);
			hrdsoftBean.setEnableAll("Y");
			hrdsoftBean.setApplicationStatus("B");
		} else if (hiddenStatus.equals("A")) {
			
			System.out.println("in showApplicationFunction call status is  " + hiddenStatus);
			
			getNavigationPanel(4);
			hrdsoftBean.setEnableAll("N");
			hrdsoftBean.setApplicationStatus("A");
			hrdsoftBean.setPpoFlag(false);
		} else if (hiddenStatus.equals("X")) {
			getNavigationPanel(3);
			hrdsoftBean.setEnableAll("N");
			hrdsoftBean.setApplicationStatus("X");
		} else if (hiddenStatus.equals("C")) {
			getNavigationPanel(3);
			hrdsoftBean.setEnableAll("N");
			hrdsoftBean.setApplicationStatus("C");
		} else if (hiddenStatus.equals("R")) {
			getNavigationPanel(3);
			hrdsoftBean.setEnableAll("N");
			hrdsoftBean.setApplicationStatus("R");
		}  else if (hiddenStatus.equals("F")) {
			getNavigationPanel(3);
			hrdsoftBean.setEnableAll("N");
			hrdsoftBean.setApplicationStatus("F");
		} else {
			getNavigationPanel(3);
			hrdsoftBean.setEnableAll("N");
			hrdsoftBean.setApplicationStatus("Z");
		}
	} catch (Exception e) {
				e.printStackTrace();
			}
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			hrdsoftBean.setEnableAll("N");
		}
		model.getApproverCommentList(hrdsoftBean, applicationID);

		return SUCCESS;
	}

	public String delete() {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			boolean result = model.delRecord(hrdsoftBean);
			model.terminate();
			if (result) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("no result"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	public String f9Employee() {
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";
		if (hrdsoftBean.getUserProfileDivision() != null
				&& hrdsoftBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ hrdsoftBean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { "Employee ID", "Employee" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empToken", "empName", "empID" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "true";
		String submitToMethod = "HardwareSoftwareRequest_setEmployeeInfo.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String setEmployeeInfo() {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			model.setEmployeeInformation(hrdsoftBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setApproverName();
		setApproverList(hrdsoftBean.getRequesterID());
		getNavigationPanel(1);
		hrdsoftBean.setEnableAll("Y");
		return SUCCESS;
	}
	
	
	public String f9DepartmentNum() throws Exception {
				
		String query = "SELECT DEPT_ID, DEPT_NAME||' - '||DEPT_ABBR FROM HRMS_DEPT WHERE DEPT_PARENT_CODE IS NULL ";

        if (hrdsoftBean.getUserProfileDivision()!=null && hrdsoftBean.getUserProfileDivision().length() > 0) {
				query += " AND DEPT_DIV_CODE IN ("
						+ hrdsoftBean.getUserProfileDivision() + ")";
			}
        query += " ORDER BY DEPT_ID DESC";
		
		
		String[] headers = { "Department ID", "Department" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empDeptID", "empDept" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	public String f9Requester() {
		String str = "0";
		if (!hrdsoftBean.getFirstApproverCode().equals("")) {
			str = hrdsoftBean.getFirstApproverCode();
		} else if (!hrdsoftBean.getSelectApproverCode().equals("")) {
			str = hrdsoftBean.getSelectApproverCode();
		}

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";
		query += getprofileQuery(hrdsoftBean);
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { "Requester ID", "Requester" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "requesterToken", "requesterName",
				"requesterID" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "true";
		String submitToMethod = "HardwareSoftwareRequest_setRequesterInfo.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String setRequesterInfo() {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			model.setRequesterInformation(hrdsoftBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setApproverName();
		setApproverList(hrdsoftBean.getRequesterID());
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String f9Approver() {
		String str = "0";
		if (!hrdsoftBean.getFirstApproverCode().equals("")) {
			str = hrdsoftBean.getFirstApproverCode();
		}
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";
		if (hrdsoftBean.getUserProfileDivision() != null
				&& hrdsoftBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ hrdsoftBean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ","
				+ hrdsoftBean.getRequesterID() + "," + hrdsoftBean.getEmpID()
				+ ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { "Employee ID", "Employee" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "selectApproverToken", "selectapproverName",
				"selectApproverCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String cancel() {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			model.cancelFunction(hrdsoftBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	private void sendApplicantToApproverMail(String applicationId,
			String userID, String approverCode, String status, String path, String fileanme) {
		try {
			HardwareSoftwareRequestModel model = new HardwareSoftwareRequestModel();
			model.initiate(context, session);
			EmailTemplateBody templateApprover = new EmailTemplateBody();
			templateApprover.initiate(context, session);
			templateApprover
					.setEmailTemplate("D1-HARDWARE SOFTWARE REQUEST APPLICANT TO APPROVER");
			templateApprover.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApprover.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);

			EmailTemplateQuery templateQueryApp2 = templateApprover.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, approverCode);

			EmailTemplateQuery templateQueryApp3 = templateApprover.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp4 = templateApprover.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);

			EmailTemplateQuery templateQueryApp5 = templateApprover.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp6 = templateApprover.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
		
			
			String hwQuery ="SELECT HWSW_HW_ITEMS_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID=" +applicationId  ;
			Object data[][] = model.getSqlModel().getSingleResult(hwQuery);
			
			String[] hwData = null;
			String hw  = "";
			if(data != null && data.length > 0) {
				//hw =String.valueOf(data[0][0]);
				hwData = String.valueOf(data[0][0]).split(",");
				
				for(int i = 0; i < hwData.length; i++) {
					if(hwData[i].equals("1")){
						hw+=""+(i+1)+")Desktop Standard User Build A\n";
					}
					else if(hwData[i].equals("2")){
						hw+=""+(i+1)+")Desktop Standard User Build B\n";
					}
					else if(hwData[i].equals("3")){
						hw+=""+(i+1)+")Laptop Standard User Build A\n";
					}
					else if(hwData[i].equals("4")){
						hw+=""+(i+1)+")Laptop Standard User Build B\n";
					}
					else if(hwData[i].equals("5")){
						hw+=""+(i+1)+")Other\n";
					}
				}
			}
			
			
			EmailTemplateQuery templateQueryApp7 = templateApprover.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, hw);
			templateQueryApp7.setParameter(2, applicationId);
			
			String swQuery ="SELECT HWSW_SW_ITEMS_REQ , HWSW_SW_SPECIAL_REQ FROM HRMS_D1_HARDWARE_SOFTWARE_REQ  WHERE HWSW_REQ_ID=" +applicationId  ;
			Object swData[][] = model.getSqlModel().getSingleResult(swQuery);
			
			String[] softwareData = null;
			String sw  = "";
			String spSw  = "";
			
			if(swData != null && swData.length > 0) {
				
				softwareData = String.valueOf(swData[0][0]).split(",");
				
				for(int i = 0; i < softwareData.length; i++) {
					if(softwareData[i].equals("1")){
						sw+=""+(i+1)+")MS Office\n";
					}
					else if(softwareData[i].equals("2")){
						sw+=""+(i+1)+")MS Project\n";
					}
					else if(softwareData[i].equals("3")){
						sw+=""+(i+1)+")MS Visio\n";
					}
					else if(softwareData[i].equals("4")){
						sw+=""+(i+1)+")Open Office\n";
					}
				}
				
				
				String spSwQuery ="select SPECIAL_SOFTWARE_NAME from HRMS_D1_SPECIAL_SW_REQ where SPECIAL_SOFTWARE_ID IN ("+swData[0][1]+")";
				Object spSwData[][] = model.getSqlModel().getSingleResult(spSwQuery);
			
				
				for(int i = 0; i < spSwData.length; i++) {
					spSw+=""+(i+1)+")"+spSwData[i][0]+"\n";
				}
			}
			
			EmailTemplateQuery templateQueryApp8 = templateApprover.getTemplateQuery(8);
			templateQueryApp8.setParameter(1, sw);
			templateQueryApp8.setParameter(2, spSw);
			templateQueryApp8.setParameter(3, applicationId);
			
			templateApprover.configMailAlert();
			if(!fileanme.equals("")){
				String[]attachment=new String[1];
				attachment[0]=path+fileanme;
				templateApprover.sendApplMailWithAttachment(attachment);
			}
			else{
			templateApprover.sendApplicationMail();
			}
			templateApprover.clearParameters();
			templateApprover.terminate();
			// MAIL FROM Applicant To Approver Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void viewAttachedProof() throws Exception {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");

			/*MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
					response);*/
			

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
				} // end of if
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} // end of if
				else if (ext.equals("doc")) {
					mimeType = "msword";
				} // end of else if
				else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} // end of else if
				else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} // end of else
				else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} // end of else if
				else if (ext.equals("txt")) {
					mimeType = "txt";
				} // end of else if
				else if (ext.equals("gif")) {
					mimeType = "gif";
				} // end of else if
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/" + mimeType
							+ "");
				}

				response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while

			} catch (FileNotFoundException e) {

				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
			// return null;
			
		} catch (Exception e) {
			//logger.error("Exception in viewUploadedFile in action:" + e);
		}
}
}
