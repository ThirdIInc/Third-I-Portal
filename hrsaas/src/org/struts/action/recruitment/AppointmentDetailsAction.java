package org.struts.action.recruitment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.paradyne.bean.Recruitment.AppointmentDetails;
import org.paradyne.model.recruitment.AppointmentDetailsModel;
import org.paradyne.model.recruitment.OfferDetailsModel;
import org.struts.lib.ParaActionSupport;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
/**
 * @author aa0540
 *
 */
public class AppointmentDetailsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppointmentDetailsAction.class);
	
	AppointmentDetails appointmentDetails = null;
	
	public void prepare_local() throws Exception {
		appointmentDetails = new AppointmentDetails();
		//appointmentDetails.setMenuCode(792);
		appointmentDetails.setMenuCode(779);
		// TODO Auto-generated method stub

	}

	public Object getModel() {
		return appointmentDetails;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		AppointmentDetailsModel model = new AppointmentDetailsModel();
		model.initiate(context, session);
		model.setSysDate(appointmentDetails);
		//For adding data path BEGINS
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		String fileDataPath = getText("data_path") + "/upload" + poolName + "/Recruitment/";
		System.out.println("data path : "+fileDataPath);
		appointmentDetails.setDataPath(fileDataPath);
		//For adding data path ENDS
		model.terminate();
	}
	
	public String joiningChecklist(){
		try {
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);
			model.joiningChecklist(appointmentDetails, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "joiningCheckList";
	}
	
	public String fromCreateOffer(){
		try {
			int count = 0;
			String buttonName = request.getParameter("buttonName");
			String status = (String) request.getAttribute("status");
			String[] isRadioCheck = request.getParameterValues("radioAppoint");
			String[] requisitionCode = request
					.getParameterValues("reqCodeAppointment");
			String[] appointmentCode = request
					.getParameterValues("appointmentCode");
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);
			if (!buttonName.equals("")) {
				if (isRadioCheck != null && isRadioCheck.length != 0) {
					for (int i = 0; i < isRadioCheck.length; i++) {
						if (isRadioCheck[i].equals("Y")) {
							appointmentDetails
									.setRequisitionCode(requisitionCode[i]);
							appointmentDetails
									.setAppointmentCode(appointmentCode[i]);
							model.getAppointmentDetails(appointmentDetails,
									status);
							appointmentDetails.setAddcandFlag(true);
							count++;
							break;
						}
					}
				}
			} else {
				appointmentDetails.setAppointmentCode("");
				appointmentDetails.setAddcandFlag(false);
			}
			if (count == 0) {
				appointmentDetails.setAppointmentCode("");
				appointmentDetails.setAddcandFlag(false);
				appointmentDetails.setAppointmentStatus("");
			}
			model.getKeepInformedSavedRecord(appointmentDetails);
			//For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String fileDataPath = getText("data_path") + "/upload" + poolName + "/Recruitment/";
			System.out.println("data path : "+fileDataPath);
			appointmentDetails.setDataPath(fileDataPath);
			//For adding data path ENDS
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String detailsOffer() throws Exception{
		try {
			String reqCode=request.getParameter("reqCode");
			AppointmentDetailsModel model=new AppointmentDetailsModel();
			model.initiate(context, session);
			//For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String fileDataPath = getText("data_path") + "/upload" + poolName + "/Recruitment/";
			System.out.println("data path : "+fileDataPath);
			appointmentDetails.setDataPath(fileDataPath);
			//For adding data path ENDS
			model.getRequsitionDetails(appointmentDetails,reqCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String viewAppointmentDetails(){
		try {
			String reqsCode = request.getParameter("reqsCode");
			String offerCode = request.getParameter("appointmentCode");
			String editFlag = request.getParameter("doubleClickEditFlag");//offer code
			if (editFlag.equals("true")) {
				appointmentDetails.setDoubleClickViewModeFlag("true");
			}
			appointmentDetails.setRequisitionCode(reqsCode);
			appointmentDetails.setAppointmentCode(offerCode);
			appointmentDetails.setViewAppointmentFlag(true);
			appointmentDetails.setAddcandFlag(true);
			
			
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);
			model.getAppointmentDetails(appointmentDetails, "N");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewAppointmentDetails";
	}
	
	public String save(){
		try {
			String[] attachPath = null;
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			attachPath = new String[1];
			attachPath[0] = getText("data_path") + "/OfferLetter/"
			+ poolName + "/OfferLetter/OfferLetter_"
			+ appointmentDetails.getCandidateName() + ".pdf";
			deleteFile(String.valueOf(attachPath[0]));
			
			appointmentDetails.setViewAppointmentFlag(true);
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			String appointmentStatus = request.getParameter("appointmentStatus");
			//String hiddenPublishCode = request.getParameter("hiddenPublishCode");
			String hiddenRequisitionCode = request.getParameter("requisitionCode");
			String result = model.save(appointmentDetails, serialNo, empCode, empName, appointmentStatus, hiddenRequisitionCode);
			//String result = model.save(appointmentDetails, serialNo, empCode, empName, appointmentStatus, hiddenPublishCode, hiddenRequisitionCode);
			if (result.equals("saved")) {
				model.getKeepInformedSavedRecord(appointmentDetails);
				addActionMessage("Record saved successfully!");
				//if offer status is send for approval then mail goes to signing authority 
				if(appointmentDetails.getAppointmentStatus().equals("Sent For Approval")) {
					sendMailToSigningAuthority();
				}
				return "viewAppointmentDetails";
			} else if (result.equals("notSaved")) {
				addActionMessage("Record can not be saved!");
			} else if (result.equals("updated")) {
				model.getKeepInformedSavedRecord(appointmentDetails);
				addActionMessage("Record updated successfully!");
				//if offer status is send for approval then mail goes to signing authority 
				if(appointmentDetails.getAppointmentStatus().equals("Sent For Approval")) {
					sendMailToSigningAuthority();
				}
				return "viewAppointmentDetails";
			} else if (result.equals("notUpdated")) {
				addActionMessage("Record can not be updated!");
			}else if(result.equals("alreadyProcessed")) {
				addActionMessage("All the vacancies for this requisition are already processed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public void deleteFile(String reportPath){
		System.out.println("Delete File"+reportPath);
		File file = new File(reportPath);
	if(file.exists()){
		file.delete();
	}
	}
	
	private void sendMailToSigningAuthority() {
		try {
			String candidateCode = request.getParameter("candidateCode");
			String signingAuthorityCode = request.getParameter("authorityCode");
			String appointmentCode = request.getParameter("appointmentCode");
			String requisitionCode = request.getParameter("requisitionCode");
			
			Template letterTemplate = new Template(this.appointmentDetails.getTemplateCode());
			letterTemplate.initiate(context, session);
			String finaldata = "";
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String completeTemplate = letterTemplate.executeWriteFile(request,
					response, "APPOINTMENT_LETTER");
			try {
				completeTemplate = completeTemplate.replaceAll("&nbsp;", "");
				completeTemplate = completeTemplate.replaceAll("& ", "&amp; ");
				finaldata = "<html>" + completeTemplate + "</html>";

			} catch (final Exception e) {
				e.printStackTrace();
			}
			try {
				DocumentBuilder builder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				Document doc = builder.parse(new StringBufferInputStream(
						finaldata));
				ITextRenderer renderer = new ITextRenderer();
				renderer.setDocument(doc, null);
				PdfWriter writer = renderer.getWriter();
				String outputFile = getText("data_path") + "/AppointmentLetter/"
						+ poolName + "/AppointmentLetter/AppointmentLetter_"
						+ appointmentDetails.getCandidateName() + ".pdf";//append Employee ID
				File letter = new File(outputFile);
				if (!letter.exists()) {
					File l = new File(letter.getParent());
					boolean dir = l.mkdirs();
				}
				OutputStream os = new FileOutputStream(letter);
				renderer.layout();
				renderer.createPDF(os);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("RMS-APPOINTMENT DETAILS MAIL TO SIGNING AUTHORITY");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			templateQuery1.setParameter(1, appointmentDetails.getUserEmpId());

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			templateQuery2.setParameter(1, signingAuthorityCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, appointmentDetails.getUserEmpId());
			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, signingAuthorityCode);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, requisitionCode);
			
			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, appointmentCode);
			
			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, appointmentCode);
			
			String[] attachPath = null;
			attachPath = new String[1];
				attachPath[0] = getText("data_path") + "/AppointmentLetter/"
						+ poolName + "/AppointmentLetter/AppointmentLetter_"
						+ appointmentDetails.getCandidateName() + ".pdf";
			
			template.configMailAlert();
			
			// Add approval link -pass parameters to the link
			String[] link_parameter = new String[1];
			String[] link_labels = new String[1];
			String applicationType = "AppointmentLetterSigningAuthorityApproval";
			link_parameter[0] = applicationType + "#" + signingAuthorityCode + "#applicationDtls#";

			String link = "/recruit/OfferApproval_input.action?";
			// link= PPEncrypter.encrypt(link);
			System.out.println("applicationDtls  ..." + link);
			link_parameter[0] += link;
			link_labels[0] = "Click Here To Process";
			template.addOnlineLink(request, link_parameter, link_labels);
			template.sendApplMailWithAttachment(attachPath);
			//template.sendApplicationMail();
			template.clearParameters();
			template.terminate();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String backFromView(){
		try {
			if (appointmentDetails.getProbation().trim().equals("Yes")) {
				appointmentDetails.setProbation("true");
			} else {
				appointmentDetails.setProbation("false");
			}
			if (appointmentDetails.getBackgroundCheck().equals("Yes")) {
				appointmentDetails.setBackgroundCheck("true");
			} else {
				appointmentDetails.setBackgroundCheck("false");
			}
			if (appointmentDetails.getAppointmentStatus().equals("Due")) {
				appointmentDetails.setAppointmentStatus("D");
			} else if (appointmentDetails.getAppointmentStatus().equals(
					"Issued")) {
				appointmentDetails.setAppointmentStatus("I");
			} else if (appointmentDetails.getAppointmentStatus().equals(
					"Sent For Approval")) {
				appointmentDetails.setAppointmentStatus("S");
			} else if (appointmentDetails.getAppointmentStatus().equals(
					"Accepted")) {
				appointmentDetails.setAppointmentStatus("OA");
			} else if (appointmentDetails.getAppointmentStatus().equals(
					"Rejected")) {
				appointmentDetails.setAppointmentStatus("OR");
			} else if (appointmentDetails.getAppointmentStatus().equals(
					"Canceled")) {
				appointmentDetails.setAppointmentStatus("C");
			}
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);
			model.getKeepInformedSavedRecord(appointmentDetails);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String toPostResume(){
		try {
			Object[] formValues = new Object[47];
			formValues[0] = "appointment";
			formValues[1] = appointmentDetails.getRequisitionCode();
			formValues[2] = appointmentDetails.getRequisitionName();
			formValues[3] = appointmentDetails.getPosition();
			formValues[4] = appointmentDetails.getHiringManager();
			formValues[5] = appointmentDetails.getDivision();
			formValues[6] = appointmentDetails.getBranch();
			formValues[7] = appointmentDetails.getDepartment();
			formValues[8] = appointmentDetails.getJoiningDate();
			formValues[9] = appointmentDetails.getAppointmentDate();
			formValues[10] = appointmentDetails.getCurrentCtc();
			formValues[11] = appointmentDetails.getNegotiatedCtc();
			formValues[12] = appointmentDetails.getJobCode();
			formValues[13] = appointmentDetails.getJobDescription();
			formValues[14] = appointmentDetails.getRolesResponsibility();
			formValues[15] = appointmentDetails.getEmpType();
			formValues[16] = appointmentDetails.getEmpTypeCode();
			formValues[17] = appointmentDetails.getHiringMgr();
			formValues[18] = appointmentDetails.getHiringMgrCode();
			formValues[19] = appointmentDetails.getExpJoiningDate();
			formValues[20] = appointmentDetails.getReportingTo();
			formValues[21] = appointmentDetails.getReportingToCode();
			formValues[22] = appointmentDetails.getTestReqCode();
			formValues[23] = appointmentDetails.getTestRequirements();
			formValues[24] = appointmentDetails.getGrade();
			formValues[25] = appointmentDetails.getGradeCode();
			formValues[26] = appointmentDetails.getTemplate();
			formValues[27] = appointmentDetails.getTemplateCode();
			formValues[28] = appointmentDetails.getProbation();
			formValues[29] = appointmentDetails.getSigningAuthority();
			formValues[30] = appointmentDetails.getAuthorityCode();
			formValues[31] = appointmentDetails.getBackgroundCheck();
			formValues[32] = appointmentDetails.getDesignation();
			formValues[33] = appointmentDetails.getAppointmentStatus();
			formValues[34] = appointmentDetails.getOfferedCtc();
			formValues[35] = appointmentDetails.getRemarks();
			formValues[36] = appointmentDetails.getCandConstraints();
			formValues[37] = appointmentDetails.getCandidateCode();
			formValues[38] = appointmentDetails.getCandidateName();
			formValues[39] = appointmentDetails.getMonths();
			formValues[40] = appointmentDetails.getPositionCode();//probation months
			formValues[41] = appointmentDetails.getDivisionCode();//probation months
			formValues[42] = appointmentDetails.getDeptCode();//probation months
			formValues[43] = appointmentDetails.getBranchCode();//probation months
			formValues[44] = appointmentDetails.getRecruiterId();//probation months
			formValues[45] = appointmentDetails.getRecruiterName();//probation months
			formValues[46] = appointmentDetails.getCandidateEmailID(); 
			request.setAttribute("formValues", formValues);
			request.setAttribute("formListValues", new Object[0][0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "postResume";
	}
	
	public String returnFromPostResume(){
		try {
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);
			String candCode = (String) request.getAttribute("candCode");
			Object[] formValues = (Object[]) request.getAttribute("formValues");//HEADER OBJECT
			model.returnFromPostResume(appointmentDetails, candCode,
							formValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String reset(){
		try {
			appointmentDetails.setAppointmentCode("");
			appointmentDetails.setRequisitionCode("");
			appointmentDetails.setRequisitionName("");
			appointmentDetails.setPosition("");
			appointmentDetails.setHiringManager("");
			appointmentDetails.setDivision("");
			appointmentDetails.setBranch("");
			appointmentDetails.setDepartment("");
			appointmentDetails.setCandidateCode("");
			appointmentDetails.setCandidateName("");
			appointmentDetails.setJoiningDate("");
			appointmentDetails.setCurrentCtc("");
			appointmentDetails.setNegotiatedCtc("");
			appointmentDetails.setJobDescription("");
			appointmentDetails.setRolesResponsibility("");
			appointmentDetails.setEmpTypeCode("");
			appointmentDetails.setEmpType("");
			appointmentDetails.setHiringMgr("");
			appointmentDetails.setHiringMgrCode("");
			appointmentDetails.setExpJoiningDate("");
			appointmentDetails.setReportingTo("");
			appointmentDetails.setReportingToCode("");
			appointmentDetails.setTestRequirements("");
			appointmentDetails.setGrade("");
			appointmentDetails.setGradeCode("");
			appointmentDetails.setTemplate("");
			appointmentDetails.setTemplateCode("");
			appointmentDetails.setProbation("false");
			appointmentDetails.setMonths("");
			appointmentDetails.setSigningAuthority("");
			appointmentDetails.setAuthorityCode("");
			appointmentDetails.setBackgroundCheck("false");
			appointmentDetails.setDesignation("");
			appointmentDetails.setAppointmentStatus("");
			appointmentDetails.setOfferedCtc("");
			appointmentDetails.setRemarks("");
			appointmentDetails.setCandConstraints("");
			appointmentDetails.setCandidateEmailID("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
public String f9RequisitionAction(){
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_REC_REQS_HDR.REQS_NAME, HRMS_RANK.RANK_NAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, "
						+" TO_CHAR(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY'), HRMS_REC_REQS_HDR.REQS_CODE, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+" HRMS_RANK.RANK_ID, HRMS_DIVISION.DIV_ID, HRMS_CENTER.CENTER_ID, HRMS_DEPT.DEPT_ID " 
						+" FROM HRMS_REC_REQS_HDR "  
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
						+" INNER  JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "   
						+" INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
						+" INNER JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) "
						+" INNER JOIN HRMS_DIVISION ON (HRMS_REC_REQS_HDR.REQS_DIVISION = HRMS_DIVISION.DIV_ID) "
						+" INNER JOIN HRMS_CENTER ON (HRMS_REC_REQS_HDR.REQS_BRANCH = HRMS_CENTER.CENTER_ID) "
						+" INNER JOIN HRMS_DEPT ON (HRMS_REC_REQS_HDR.REQS_DEPT = HRMS_DEPT.DEPT_ID) "
						+" WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN ('A','Q') AND HRMS_REC_REQS_HDR.REQS_STATUS = 'O' AND HRMS_REC_REQS_VACDTL.VACAN_STATUS='P' " 
					    +" ORDER BY HRMS_REC_REQS_HDR.REQS_DATE DESC";
			
			/*"SELECT HRMS_REC_REQS_HDR.REQS_NAME, HRMS_RANK.RANK_NAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, "
			+" TO_CHAR(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY'), HRMS_REC_REQS_HDR.REQS_CODE, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
			+" HRMS_RANK.RANK_ID, HRMS_DIVISION.DIV_ID, HRMS_CENTER.CENTER_ID, HRMS_DEPT.DEPT_ID " 
			+" FROM HRMS_REC_REQS_HDR "  
			+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)   "
			+" INNER  JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE)   "
			+" INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "  
			+" INNER JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID)   "
			+" INNER JOIN HRMS_DIVISION ON (HRMS_REC_REQS_HDR.REQS_DIVISION = HRMS_DIVISION.DIV_ID) "  
			+" INNER JOIN HRMS_CENTER ON (HRMS_REC_REQS_HDR.REQS_BRANCH = HRMS_CENTER.CENTER_ID) "  
			+" INNER JOIN HRMS_DEPT ON (HRMS_REC_REQS_HDR.REQS_DEPT = HRMS_DEPT.DEPT_ID) "
			+" LEFT JOIN HRMS_REC_VACANCIES_STATS ON (HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE  AND  HRMS_REC_VACANCIES_STATS.VAC_PUBLISH_CODE=HRMS_REC_VACPUB_HDR.PUB_CODE) "
			+" WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN ('A','Q') AND HRMS_REC_REQS_HDR.REQS_STATUS = 'O' AND HRMS_REC_REQS_VACDTL.VACAN_STATUS='P' "  
			+" AND HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN='N' " 
			+" GROUP BY HRMS_REC_REQS_HDR.REQS_NAME, HRMS_RANK.RANK_NAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,  HRMS_REC_REQS_HDR.REQS_CODE, HRMS_EMP_OFFC.EMP_FNAME, HRMS_EMP_OFFC.EMP_MNAME, HRMS_EMP_OFFC.EMP_LNAME , HRMS_RANK.RANK_ID, HRMS_DIVISION.DIV_ID, HRMS_CENTER.CENTER_ID, HRMS_DEPT.DEPT_ID, HRMS_REC_VACPUB_HDR.PUB_CODE";
			*/
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("reqs.code"), getMessage("position"), getMessage("division"), 
								getMessage("branch"), getMessage("department"), "Required By Date"};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String[] headerWidth = { "15", "20", "15", "15", "15", "20" };
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"requisitionName", "position", "division", "branch", "department", "requiredByDate", "requisitionCode",
									"hiringManager","positionCode","divisionCode","branchCode","deptCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8,9,10,11};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AppointmentDetails_setRequisition.action";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9CandidateAction(){
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		/*String query = "SELECT CAND_CODE, CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME "
					   +"FROM HRMS_REC_CAND_DATABANK "
					   +"WHERE CAND_CODE NOT IN (SELECT RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
					   +"UNION SELECT EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL "
					   +"UNION SELECT OFFER_CAND_CODE FROM HRMS_REC_OFFER) "
					   +"ORDER BY UPPER(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME)";*/
		
		String query = "SELECT  HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME, HRMS_REC_CAND_DATABANK.CAND_CODE, HRMS_REC_CAND_DATABANK.CAND_EMAIL_ID "
					   +" FROM HRMS_REC_CAND_DATABANK "
					   +" WHERE HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN (SELECT DISTINCT HRMS_REC_RESUME_BANK.RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
					   +" WHERE HRMS_REC_RESUME_BANK.RESUME_REQS_CODE = "+appointmentDetails.getRequisitionCode()+" "
					   +" UNION SELECT DISTINCT HRMS_REC_CANDEVAL.EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL "
					   +" WHERE HRMS_REC_CANDEVAL.EVAL_REQS_CODE = "+appointmentDetails.getRequisitionCode()+" "
					   +" UNION SELECT DISTINCT HRMS_REC_OFFER.OFFER_CAND_CODE FROM HRMS_REC_OFFER "
					   +" WHERE HRMS_REC_OFFER.OFFER_REQS_CODE = "+appointmentDetails.getRequisitionCode()+" "
					   +" UNION SELECT DISTINCT HRMS_REC_APPOINT.APPOINT_CAND_CODE FROM HRMS_REC_APPOINT "
					   +" WHERE HRMS_REC_APPOINT.APPOINT_REQS_CODE = "+appointmentDetails.getRequisitionCode()+")" 
					   +" AND HRMS_REC_CAND_DATABANK.CAND_CONVERT_FLAG = 'N' "
					   +" AND HRMS_REC_CAND_DATABANK.CAND_SHORT_STATUS NOT IN ('E')"
					   +" ORDER BY UPPER(HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME)";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("candidate.name")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"candidateName", "candidateCode", "candidateEmailID"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9JobDescAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_JOB_DESCRIPTION.JOB_DESC_NAME, HRMS_JOB_DESCRIPTION.JOB_DESC_ROLE_RESP, HRMS_JOB_DESCRIPTION.JOB_DESC_CODE "
						+"FROM HRMS_JOB_DESCRIPTION "
						+"ORDER BY HRMS_JOB_DESCRIPTION.JOB_DESC_EFFC_DATE DESC";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("job.desc"), getMessage("roles.res")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"20", "40"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"jobDescription", "rolesResponsibility", "jobCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9EmpTypeAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_EMP_TYPE.TYPE_NAME, HRMS_EMP_TYPE.TYPE_ID FROM HRMS_EMP_TYPE ORDER BY HRMS_EMP_TYPE.TYPE_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("employee.type")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"empType", "empTypeCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9HireMgrAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
					   +"FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' "
					   +"ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("hiring.mgr")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"hiringMgr", "hiringMgrCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9ReportingAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
					   +"FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' "
					   +"ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("reporting.to")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"reportingTo", "reportingToCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
public String f9ReportingAdminAction(){
		
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
					   +"FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' "
					   +"ORDER BY HRMS_EMP_OFFC.EMP_ID";
		String [] headers = {getMessage("reporting.to.admin")};
		
		String [] headerWidth = {"100"};
		
		String [] fieldNames = {"reportingToAdmin", "reportingToAdminCode"};
		
		int [] columnIndex = {0, 1};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	public String f9SigningAuthAction(){
	
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, RANK_NAME, EMP_ID "
					   +"FROM HRMS_EMP_OFFC "
					   +"INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) WHERE EMP_STATUS = 'S' "
					   +"ORDER BY EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("signing.authority"), getMessage("designation")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"50", "50"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"signingAuthority", "designation", "authorityCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9GradeAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT DISTINCT CADRE_NAME, CADRE_ID FROM  HRMS_CADRE ORDER BY CADRE_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("grade")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"grade", "gradeCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	
	public String previewAppointmentPDF(){
		String comleteTemplate = "";
		String finaldata = "";
		String tempCode = request.getParameter("templateCode");
		String candCode = request.getParameter("candidateCode");
		String reqsCode = request.getParameter("requisitionCode");
		Template template = new Template(tempCode);
		template.initiate(context, session);		
		template.getTemplateQueries();
		OfferDetailsModel model = new OfferDetailsModel();
		model.initiate(context, session);
		try{
		try {
			logger.info("QUERY 1 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, reqsCode);
			templateQuery1.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			logger.info("QUERY 2 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, reqsCode);
			templateQuery2.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			logger.info("QUERY 3 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, reqsCode);
			templateQuery3.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			logger.info("QUERY 4 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, reqsCode);
			templateQuery4.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
		  logger.info("QUERY 5 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery5 = template.getTemplateQuery(5);
			logger.info("offered candidate code" + candCode);
			templateQuery5.setParameter(1, reqsCode);
			templateQuery5.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
		logger.info("QUERY 6 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery6 = template.getTemplateQuery(6);
			logger.info("offered candidate code" + candCode);
			templateQuery6.setParameter(1, reqsCode);
			templateQuery6.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery7 = template.getTemplateQuery(7);
			logger.info("offered candidate code" + candCode);
			templateQuery7.setParameter(1, reqsCode);
			templateQuery7.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery8 = template.getTemplateQuery(8);
			logger.info("offered candidate code" + candCode);
			templateQuery8.setParameter(1, reqsCode);
			templateQuery8.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery9 = template.getTemplateQuery(9);
			logger.info("offered candidate code" + candCode);
			String takeHomeAmt = model.getTotalAmt(candCode,reqsCode,"takeHome",true);
			templateQuery9.setParameter(1, takeHomeAmt);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery10 = template.getTemplateQuery(10);
			logger.info("offered candidate code" + candCode);
			String ctcPerMonth = model.getTotalAmt(candCode,reqsCode,"ctcPerMonth",true);
			templateQuery10.setParameter(1, ctcPerMonth);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery11 = template.getTemplateQuery(11);
			logger.info("offered candidate code" + candCode);
			String ctcPerYear = model.getTotalAmt(candCode,reqsCode,"ctcPerYear",true);
			templateQuery11.setParameter(1, ctcPerYear);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		comleteTemplate=template.execute(request,response,"APPOINTMENT_LETTER",true);
		logger.info("comleteTemplate....."+comleteTemplate);
		comleteTemplate = comleteTemplate.replaceAll("&nbsp;", "");
		comleteTemplate = comleteTemplate.replaceAll("& ", "&amp; ");
		comleteTemplate = comleteTemplate.replaceAll("&rsquo;", "'");
		comleteTemplate = comleteTemplate.replaceAll("&ndash;", "-");
		finaldata = "<html>" + comleteTemplate + "</html>";
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(new StringBufferInputStream(finaldata));
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(doc, null);

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+ "APPOINTMENT_LETTER_"+appointmentDetails.getCandidateName()+".pdf\"");
		String client=String.valueOf(session.getAttribute("session_pool"));
		String outputFile = getText("data_path")+"/AppointmentLetter/"+client+"/AppointmentLetter_"+appointmentDetails.getCandidateName()+".pdf";//append Employee ID
		File letter=new File(outputFile);
		if(!letter.exists()){
			File l = new File(letter.getParent());
			boolean dir = l.mkdirs();
		}
		OutputStream os = new FileOutputStream(letter);
		renderer.layout();
		renderer.createPDF(os);
		//response.setHeader("cache-control", "no-cache");
		try {
	 	
		 PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/AppointmentLetter/"+client+"/AppointmentLetter_"+appointmentDetails.getCandidateName()+".pdf");
        int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
        PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
        int i = 0;
        if(client.equals("pool_glodyne")){
        Image watermark_image = Image.getInstance(getText("data_path")+"/APPOINTMENT_LETTER/rings.jpg");
        watermark_image.setAbsolutePosition(80, 300);
        PdfContentByte add_watermark;            
        while (i < number_of_pages) {
          i++;
          add_watermark = stamp.getUnderContent(i);
          add_watermark.addImage(watermark_image);
        }
        }
        stamp.close();
    }
    catch (Exception i1) {
        i1.printStackTrace();
    }
		
		model.terminate();
		}catch(Exception e){
			System.out.println("Exception OCCUR >>>>>>>>>>");
			e.printStackTrace();
		}	
		return null;
	
	}
	public String previewappointment(){
		String tempCode = request.getParameter("templateCode");
		String candCode = request.getParameter("candidateCode");
		String reqsCode = request.getParameter("requisitionCode");
		Template template = new Template(tempCode);
		template.initiate(context, session);		
		template.getTemplateQueries();
		OfferDetailsModel model = new OfferDetailsModel();
		model.initiate(context, session);
		try{
		try {
			logger.info("QUERY 1 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, reqsCode);
			templateQuery1.setParameter(2, candCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			logger.info("QUERY 2 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, reqsCode);
			templateQuery2.setParameter(2, candCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			logger.info("QUERY 3 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, reqsCode);
			templateQuery3.setParameter(2, candCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			logger.info("QUERY 4 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, reqsCode);
			templateQuery4.setParameter(2, candCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
		  logger.info("QUERY 5 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery5 = template.getTemplateQuery(5);
			logger.info("offered candidate code" + candCode);
			templateQuery5.setParameter(1, reqsCode);
			templateQuery5.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
		logger.info("QUERY 6 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
			TemplateQuery templateQuery6 = template.getTemplateQuery(6);
			logger.info("offered candidate code" + candCode);
			templateQuery6.setParameter(1, reqsCode);
			templateQuery6.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery7 = template.getTemplateQuery(7);
			logger.info("offered candidate code" + candCode);
			templateQuery7.setParameter(1, reqsCode);
			templateQuery7.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery8 = template.getTemplateQuery(8);
			logger.info("offered candidate code" + candCode);
			templateQuery8.setParameter(1, reqsCode);
			templateQuery8.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery9 = template.getTemplateQuery(9);
			logger.info("offered candidate code" + candCode);
			String takeHomeAmt = model.getTotalAmt(candCode,reqsCode,"takeHome",true);
			templateQuery9.setParameter(1, takeHomeAmt);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery10 = template.getTemplateQuery(10);
			logger.info("offered candidate code" + candCode);
			String ctcPerMonth = model.getTotalAmt(candCode,reqsCode,"ctcPerMonth",true);
			templateQuery10.setParameter(1, ctcPerMonth);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery11 = template.getTemplateQuery(11);
			logger.info("offered candidate code" + candCode);
			String ctcPerYear = model.getTotalAmt(candCode,reqsCode,"ctcPerYear",true);
			templateQuery11.setParameter(1, ctcPerYear);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String comleteTemplate=template.execute(request,response,"APPOINTMENT_LETTER");
		logger.info("comleteTemplate....."+comleteTemplate);
		model.terminate();
		}catch(Exception e){
			try{
			String type = "Txt";
			String title = "Template letter";
			e.printStackTrace();
			String finaldata = "<html>" + "" + "</html>";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					type, title);

			byte[] buf = finaldata.getBytes();

			response.setContentType("application/msword");
			response.getOutputStream().write(buf);
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ "APPOINTMENT_LETTER" + ".doc\"");
			response.setHeader("cache-control", "no-cache");
			}catch(Exception e1){e1.printStackTrace();}
		
		}	
		return null;
	}


	
	
	public String emailappointment() {
		try {
			String currentAppointStaus = request.getParameter("appointmentStatus");
			if(currentAppointStaus.equals("Issued")) {
				appointmentDetails.setAppointmentStatus("I");
			}
		
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String tempCode = request.getParameter("templateCode");// Template Code
			String candCode = request.getParameter("candidateCode");// Candidate Code
			String reqsCode = request.getParameter("requisitionCode");// Requisition Code
			String appointmentCode = request.getParameter("appointmentCode"); // Appointment code
			String[] keepInfo = null;
			keepInfo = request.getParameterValues("keepInformedEmpId");
			//String publishCode = request.getParameter("hiddenPublishCode");
			String requisitionCode = request.getParameter("hiddenRequisitionCode");
			logger.info("tempCode >>" + tempCode);
			logger.info("candidate code >>" + candCode);
			logger.info("reqs code >>" + reqsCode);
			logger.info("appointmentCode >>" + appointmentCode);
			//logger.info("publishCode >>" + publishCode);
			logger.info("requisitionCode >>" + requisitionCode);

			Template template = new Template(tempCode);
			template.initiate(context, session);		
			template.getTemplateQueries();
			
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);

			try {
				logger.info("QUERY 1 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, reqsCode);
				templateQuery1.setParameter(2, candCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				logger.info("QUERY 2 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, reqsCode);
				templateQuery2.setParameter(2, candCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				logger.info("QUERY 3 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, reqsCode);
				templateQuery3.setParameter(2, candCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				logger.info("QUERY 4 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, reqsCode);
				templateQuery4.setParameter(2, candCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				  logger.info("QUERY 5 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
					TemplateQuery templateQuery5 = template.getTemplateQuery(5);
					logger.info("offered candidate code" + candCode);
					templateQuery5.setParameter(1, reqsCode);
					templateQuery5.setParameter(2, candCode);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
				logger.info("QUERY 6 : Requisition code :>>"+reqsCode+" :  cand code :>>"+candCode );
					TemplateQuery templateQuery6 = template.getTemplateQuery(6);
					logger.info("offered candidate code" + candCode);
					templateQuery6.setParameter(1, reqsCode);
					templateQuery6.setParameter(2, candCode);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					TemplateQuery templateQuery7 = template.getTemplateQuery(7);
					logger.info("offered candidate code" + candCode);
					templateQuery7.setParameter(1, reqsCode);
					templateQuery7.setParameter(2, candCode);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					TemplateQuery templateQuery8 = template.getTemplateQuery(8);
					logger.info("offered candidate code" + candCode);
					templateQuery8.setParameter(1, reqsCode);
					templateQuery8.setParameter(2, candCode);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					TemplateQuery templateQuery9 = template.getTemplateQuery(9);
					logger.info("offered candidate code" + candCode);
					String takeHomeAmt = model.getTotalAmt(candCode,reqsCode,"takeHome",true);
					templateQuery9.setParameter(1, takeHomeAmt);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					TemplateQuery templateQuery10 = template.getTemplateQuery(10);
					logger.info("offered candidate code" + candCode);
					String ctcPerMonth = model.getTotalAmt(candCode,reqsCode,"ctcPerMonth",true);
					templateQuery10.setParameter(1, ctcPerMonth);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					TemplateQuery templateQuery11 = template.getTemplateQuery(11);
					logger.info("offered candidate code" + candCode);
					String ctcPerYear = model.getTotalAmt(candCode,reqsCode,"ctcPerYear",true);
					templateQuery11.setParameter(1, ctcPerYear);
				} catch (Exception e) {
					// TODO: handle exception
				}
			
			String finaldata="";
			String completeTemplate = template.executeWriteFile(request, response, "APPOINTMENT_LETTER");
			try {
				completeTemplate=completeTemplate.replaceAll("&nbsp;", "");
				completeTemplate=completeTemplate.replaceAll("& ", "&amp; ");
				completeTemplate=completeTemplate.replaceAll("&rsquo;", "'");
				completeTemplate=completeTemplate.replaceAll("&ndash;", "-");
				
				finaldata = "<html>" + completeTemplate + "</html>";
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(
					finaldata));

			ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f,20);

			renderer.setDocument(doc, null);
		
						
			String outputFile = getText("data_path")+"/AppointmentLetter/"+poolName+"/AppointmentLetter/AppointmentLetter_"
						+ appointmentDetails.getCandidateName()+".pdf";//append Employee ID
			File letter=new File(outputFile);
			if(!letter.exists()){
				File l = new File(letter.getParent());
				boolean dir = l.mkdirs();
			}
			OutputStream os = new FileOutputStream(letter);
			renderer.layout();
			renderer.createPDF(os);
			
			
			EmailTemplateBody emailTemplate = new EmailTemplateBody();
			emailTemplate.initiate(context, session);
			emailTemplate.setEmailTemplate("Candidate Appointment Letter Email");
			emailTemplate.getTemplateQueries();
			EmailTemplateQuery emailTemplateQuery1 = emailTemplate.getTemplateQuery(1);
			emailTemplateQuery1.setParameter(1, appointmentDetails.getUserEmpId());

			EmailTemplateQuery emailTemplateQuery2 = emailTemplate.getTemplateQuery(2);
			emailTemplateQuery2.setParameter(1, candCode);

			EmailTemplateQuery emailTemplateQuery3 = emailTemplate.getTemplateQuery(3);
			emailTemplateQuery3.setParameter(1, appointmentDetails.getUserEmpId());
			emailTemplateQuery3.setParameter(2, appointmentDetails.getUserEmpId());
			emailTemplateQuery3.setParameter(3, appointmentCode);

			// For sending OfferLetter and Annexure attachment BEGINS
			String path = appointmentDetails.getDataPath();
			String annexureFileName = appointmentDetails.getAnnextureFileName();
			System.out.println("path : " + path);
			System.out.println("annexureFileName : " + annexureFileName);
			String[] attachPath = null;
			if (!annexureFileName.equals("")) {
				attachPath = new String[2];
				attachPath[0] = getText("data_path") + "/AppointmentLetter/"
						+ poolName + "/AppointmentLetter/AppointmentLetter_"
						+ appointmentDetails.getCandidateName() + ".pdf";

				attachPath[1] = path + annexureFileName;
			} else {
				attachPath = new String[1];
				attachPath[0] = getText("data_path") + "/AppointmentLetter/"
						+ poolName + "/AppointmentLetter/AppointmentLetter_"
						+ appointmentDetails.getCandidateName() + ".pdf";
			}

			// For sending OfferLetter and Annexure attachment ENDS
			emailTemplate.configMailAlert();
			
			if (keepInfo != null && keepInfo.length > 0) {
				emailTemplate.sendApplicationMailToKeepInfo(keepInfo);
			}
			// Offer Approved : Status --> OA
			// Offer Rejected : Status --> S
			String[] link_parameter = new String[2];
			String[] link_labels = new String[2];
			String applicationType = "AppointmentLetterDetails";
			//link_parameter[0] = applicationType + "#" + candCode + "#" + appointmentCode + "#" + "OA" + "#" + publishCode + "#" + requisitionCode + "#" + "...";
			//link_parameter[1] = applicationType + "#" + candCode + "#" + appointmentCode + "#" + "S" + "#" + publishCode + "#" + requisitionCode + "#" + "...";
			
			link_parameter[0] = applicationType + "#" + candCode + "#" + appointmentCode + "#" + "OA" + "#" + reqsCode + "#" + "...";
			link_parameter[1] = applicationType + "#" + candCode + "#" + appointmentCode + "#" + "S" + "#" + reqsCode + "#" + "...";
			
			System.out.println("############ applicationType >>"+applicationType);
			System.out.println("############ candCode >>"+candCode);
			System.out.println("############ appointmentCode >>"+appointmentCode);
			System.out.println("############ reqsCode >>"+reqsCode);
			 
			
			link_labels[0] = "Accept Appointment";
			link_labels[1] = "Reject Appointment";
			emailTemplate.addOnlineLink(request, link_parameter, link_labels);
			emailTemplate.sendApplMailWithAttachment(attachPath);
			addActionMessage("Mail has been sent successfully to the candidate");
			emailTemplate.clearParameters();
			emailTemplate.terminate();

			model.getKeepInformedSavedRecord(appointmentDetails);
			model.terminate();
		} catch (Exception e) {
			addActionMessage("Exception occured while sending application.");
			e.printStackTrace();
		}
		return "success";
	}
	
	public String f9AppointmentTemplate()
	{
		String query = "  SELECT  TEMPLATE_NAME,TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR"
			//+" WHERE TEMPLATE_TYPE=TO_CHAR((SELECT LETTERTYPE_ID FROM HRMS_LETTERTYPE WHERE LETTERTYPE LIKE '%APPOINTMENT%')) "
			+" WHERE TEMPLATE_TYPE=2"
			+" ORDER BY TEMPLATE_ID";

		String[] headers = { "Template Name" };

		String[] headerWidth = {"40"};

		String[] fieldNames = {"template","templateCode"};

		int[] columnIndex = { 0,1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9AppointmentEmailTemplate()
	{
		String query = "  SELECT TEMPLATE_NAME,TEMPLATE_ID FROM HRMS_EMAILTEMPLATE_HDR ORDER BY TEMPLATE_ID";

		String[] headers = {"Template Name" };

		String[] headerWidth = {"40"};

		String[] fieldNames = {"emailtemplate","emailtemplateCode"};

		int[] columnIndex = { 0,1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String searchRec() throws Exception
	{		
		AppointmentDetailsModel model = new AppointmentDetailsModel();
		model.initiate(context, session);
		model.viewDetails(appointmentDetails,appointmentDetails.getTemplateCode());	
		model.terminate();
		return "viewFirst";
	}
	
	/**
	 * following function is called when  position seacrh window is clicked.
	 * @return
	 * @throws Exception
	 */
	public String f9actionDesg() throws Exception {
		String query = " SELECT  NVL(RANK_NAME,' ') ,RANK_ID FROM HRMS_RANK WHERE RANK_STATUS='A' ORDER BY UPPER(RANK_NAME) ";		
		String[] headers={getMessage("position")};
		String[] headerWidth={ "50"};
		String[] fieldNames={"position","positionCode"};
		int[] columnIndex={0,1};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	
	public String f9actionDiv() throws Exception {//-f9action for division
		String query = " SELECT  DIV_NAME , DIV_id FROM HRMS_DIVISION ";	
		
		if(appointmentDetails.getUserProfileDivision() !=null && appointmentDetails.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+appointmentDetails.getUserProfileDivision() +")"; 
			query+= " ORDER BY  UPPER(DIV_NAME) ";
			
		String[] headers={getMessage("division")};
		String[] headerWidth={"50"};
		String[] fieldNames={"division","divisionCode"};
		int[] columnIndex={0,1};
		String submitFlag = "false";
		String submitToMethod="";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	
	/**
	 * following function is called when the branch search window button is clicked
	 * @return
	 * @throws Exception
	 */	
		public String f9actionBrn() throws Exception {
			String query = " SELECT  NVL(CENTER_NAME,' ') ,CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";		
			
			String[] headers={getMessage("branch")};
			String[] headerWidth={"50"};
			String[] fieldNames={"branch","branchCode"};
			int[] columnIndex={0,1};
			String submitFlag = "false";
			String submitToMethod="";
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			return "f9page";
		}
		
		/**
		 * following function is called when the department search window button is clicked
		 * @return
		 * @throws Exception
		 */	
		public String f9actionDept() throws Exception {//--------f9action for division
			String query = " SELECT   DEPT_NAME , DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";		
			String[] headers={getMessage("department")};
			String[] headerWidth={"50"};
			String[] fieldNames={"department","deptCode"};
			int[] columnIndex={0,1};
			String submitFlag = "false";
			String submitToMethod="";
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			return "f9page";
		}
		
		public String f9Recruiter()
		{
			AppointmentDetailsModel model = new AppointmentDetailsModel();
			model.initiate(context, session);
			Object[][]chkIfPublishData = null;
			try {
				String checkIfPublished = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR "
						+ "WHERE PUB_REQS_CODE = "
						+ appointmentDetails.getRequisitionCode()
						+ " AND PUB_STATUS = 'P'";
				chkIfPublishData = model.getSqlModel().getSingleResult(checkIfPublished);
			} catch (Exception e) {
				logger.error("exception in chkIfPublishData", e);
			} //end of catch
			model.terminate();
			String query = "";
			
			if(chkIfPublishData != null && chkIfPublishData.length > 0){
				query = "SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '), "
						+" PUB_REC_EMPID FROM HRMS_REC_VACPUB_RECDTL "
						+" INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID) "
						+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  "
						+" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK   "
						+" WHERE PUB_REQS_CODE = "+appointmentDetails.getRequisitionCode()+" AND PUB_STATUS = 'P'";
			} //end of if
			else{
				query = "  SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
					+ "	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  "
					+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE" +
					  " where EMP_STATUS = 'S'"
				    + " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			} //end of else

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Id", "Employee Name", "Branch",
				"Designation" };

		String[] headerWidth = { "20", "30", "30", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"recruiterToken","recruiterName","hBranch","hDesg","recruiterId"};
				

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
		}

		
		public String setRequisition(){
			AppointmentDetailsModel model;
			try {
				String query = "SELECT REQS_NAME,REQS_JOBDESC_NAME,REQS_ROLE_RESPON "
						+ "FROM HRMS_REC_REQS_HDR " + "WHERE REQS_CODE="
						+ appointmentDetails.getRequisitionCode();
				model = new AppointmentDetailsModel();
				model.initiate(context, session);
				Object data[][] = model.getSqlModel().getSingleResult(query);
				appointmentDetails.setJobDescription(String.valueOf(data[0][1]));
				appointmentDetails.setRolesResponsibility(String.valueOf(data[0][2]));
				appointmentDetails.setRequiredByDate(appointmentDetails.getRequiredByDate());
				model.terminate();
				
			} catch (Exception e) {
				logger.error("Exception in Requisition in action:" + e);
				return null;
			}
			
			return SUCCESS;
		}

		
		public String addKeepInformedEmpList() {
			try {
				String[] serialNo = request.getParameterValues("serialNo"); // serial
				// no.
				String[] empCode = request.getParameterValues("keepInformedEmpId");// keep
				// informed
				// empid
				String[] empName = request
						.getParameterValues("keepInformedEmpName");// keep informed
				// employee name
				
				AppointmentDetailsModel model = new AppointmentDetailsModel();
				model.initiate(context, session);
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, appointmentDetails);
				
				model.setKeepInformed(serialNo, empCode, empName, appointmentDetails);
				
				appointmentDetails.setEmployeeId("");
				appointmentDetails.setEmployeeToken("");
				appointmentDetails.setEmployeeName("");
				
				model.terminate();
			} catch (Exception e) {
				logger.error("Exception in addKeepInformedEmpList-----" + e);
			}
			return SUCCESS;
		}// end of addKeepInformedEmpList

		public String f9KeepInformedEmployee() {
			String[] eId = request.getParameterValues("keepInformedEmpId");
			String str = "0";
			if (eId != null) {
				for (int i = 0; i < eId.length; i++) {
					str += "," + eId[i];
				}
			}
			// logger.info("value of str-----------------" + str);

			try {
				String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
						+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
						+ "	FROM HRMS_EMP_OFFC "
						+ " WHERE EMP_STATUS='S' ";
				//added by Prashant
						if(appointmentDetails.getUserProfileDivision() !=null && appointmentDetails.getUserProfileDivision().length()>0)
						query += "AND EMP_DIV IN (" +appointmentDetails.getUserProfileDivision() +")";
						query += "AND EMP_ID NOT IN(" + str + ")";


				String[] headers = { getMessage("employee.id"),
						getMessage("employee") };

				String[] headerWidth = { "20", "80" };

				String[] fieldNames = { "employeeToken", "employeeName",
						"employeeId" };

				int[] columnIndex = { 0, 1, 2 };

				String submitFlag = "false";

				String submitToMethod = "";

				setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
						submitFlag, submitToMethod);

				return "f9page";
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "";
			} // end of try-catch block
		} // end of f9Branch
		
		
		public String removeKeepInformed() {
			try {
				String[] serialNo = request.getParameterValues("serialNo");
				String[] empCode = request.getParameterValues("keepInformedEmpId");
				String[] empName = request
						.getParameterValues("keepInformedEmpName");
				AppointmentDetailsModel model = new AppointmentDetailsModel();
				model.initiate(context, session);
				model.removeKeepInformedData(serialNo, empCode, empName,
						appointmentDetails);
				model.terminate();
			} catch (Exception e) {
				logger.error("Exception in removeKeepInformed--------" + e);
			}
			return SUCCESS;
		}
		
		public void viewAttachedAnnexure() throws Exception {
			try {
				String uploadFileName = request.getParameter("annextureFileName");
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

					// for getting server path where configuration files are saved.
					String path = dataPath + fileName;
					oStream = response.getOutputStream();
					if (ext.equals("pdf")) {
						// response.setHeader("Content-type",
						// "application/"+mimeType+"");
					} // end of if
					else {
						response.setHeader("Content-type", "application/" + mimeType + "");
					}
					response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");

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
