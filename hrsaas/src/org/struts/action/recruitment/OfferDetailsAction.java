package org.struts.action.recruitment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.bean.Recruitment.OfferDetails;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.OfferDetailsModel;
import org.struts.lib.ParaActionSupport;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author aa0540
 * 
 */
public class OfferDetailsAction extends ParaActionSupport {
	
	/** logger. *  */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OfferDetailsAction.class);

	/** offerDetails. *  */
	private OfferDetails offerDetails;
	/** manPowerReqs. *  */
	private EmployeeRequisition manPowerReqs;

	/**	
	 * Method : prepare_local.
	 * Purpose : For setting Menu Code 
	 * @throws Exception : Exception 
	 */
	public void prepare_local() throws Exception {
		this.offerDetails = new OfferDetails();
		this.manPowerReqs = new EmployeeRequisition();
		// offerDetails.setMenuCode(781);
		this.offerDetails.setMenuCode(779);
	}

	/**	
	 * Method : getModel().
	 * Purpose : For getting model instance
	 * @return Object
	 */
	public Object getModel() {
		return this.offerDetails;
	}

	/**
	 * @return the offerDetails
	 */
	public OfferDetails getOfferDetails() {
		return this.offerDetails;
	}

	/**
	 * @param offerDetails
	 *            the offerDetails to set
	 */
	public void setOfferDetails(OfferDetails offerDetails) {
		this.offerDetails = offerDetails;
	}

	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is called once you click on Create Offer link.
	 * & every time you submit any request
	 * @throws Exception : Exception
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			final OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.setSysDate(this.offerDetails);
			// For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPathFrmCreateOffer = this.getText("data_path") + "/upload" + poolName + "/Recruitment/";
			this.offerDetails.setDataPath(fileDataPathFrmCreateOffer);
			// For adding data path ENDS
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method fromCreateOffer
	 * @purpose action called from the create offer form
	 * @return String
	 */
	public String fromCreateOffer() {
		try {
			int count = 0;
			// button name to set flags accordingly
			final String buttonName = request.getParameter("buttonName"); 
			// status
			final String status = (String) request.getAttribute("status");
			// radio button values
			final String[] isRadioCheck = request.getParameterValues("radioOffer");
			// requisition code
			final String[] requisitionCode = request.getParameterValues("reqCodeOffer");
			// offer code
			final String[] offerCode = request.getParameterValues("offerCode");
			final OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			// check for selected radio button
			if (!buttonName.equals("")) {
				if (isRadioCheck != null && isRadioCheck.length != 0) {
					for (int i = 0; i < isRadioCheck.length; i++) {
						if (isRadioCheck[i].equals("Y")) {
							// set reqs code of the selected record
							this.offerDetails.setRequisitionCode(requisitionCode[i]);
							// set offer code of the selected record
							this.offerDetails.setOfferCode(offerCode[i]);
							// retrieve all the offer details of the selected record
							model.getOfferDetails(this.offerDetails, status);
							// add cand flag as true
							this.offerDetails.setAddcandFlag(true);
							count++;
							break;
						}
					}
					// offerDetails.setAddcandFlag(true);
				}
			} else {
				this.offerDetails.setAddcandFlag(false);
			}
				
			if (count == 0) {
				this.offerDetails.setAddcandFlag(false);
				this.offerDetails.setOfferCode("");
				this.offerDetails.setOfferStatus("");
			}
			model.getKeepInformedSavedRecord(this.offerDetails);
			model.terminate();
			// For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPathFrmCreateOffer = this.getText("data_path") + "/upload" + poolName + "/Recruitment/";
			this.offerDetails.setDataPath(fileDataPathFrmCreateOffer);
			// For adding data path ENDS
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @method viewOfferDetails
	 * @purpose to display the offer details in view mode
	 * @return String
	 */
	public String viewOfferDetails() {
		try {
			// For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPathViewOfferDetails = getText("data_path") + "/upload" + poolName + "/Recruitment/";
			offerDetails.setDataPath(fileDataPathViewOfferDetails);
			// For adding data path ENDS
			
			final String reqsCode = request.getParameter("reqsCode");
			final String offerCode = request.getParameter("offerCode");
			final String editFlag = request.getParameter("doubleClickEditFlag");
			if (editFlag.equals("true")) {
				this.offerDetails.setDoubleClickViewModeFlag("true");
			}
			this.offerDetails.setRequisitionCode(reqsCode);
			this.offerDetails.setOfferCode(offerCode);
			this.offerDetails.setViewOfferFlag(true);
			this.offerDetails.setAddcandFlag(true);
			final OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.getOfferDetails(offerDetails, "N");
			model.getKeepInformedSavedRecord(this.offerDetails);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "viewOfferDetails";
	}

	/**
	 * @method joiningChecklist
	 * @purpose to retrieve all the joining formalities
	 * @return String
	 */
	public String joiningChecklist() {
		try {
			final OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.joiningChecklist(this.offerDetails, request);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "joiningCheckList";
	}

	/**
	 * @method save
	 * @purpose to save or update the offer details
	 * @return String
	 */
	public String save() {
		offerDetails.setViewOfferFlag(true);// view offer flag
		String[] attachPath = null;
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		attachPath = new String[1];
		attachPath[0] = getText("data_path") + "/OfferLetter/"
		+ poolName + "/OfferLetter/OfferLetter_"
		+ offerDetails.getCandidateName() + ".pdf";
		deleteFile(String.valueOf(attachPath[0]));
		try {
			final OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			final String[] serialNo = request.getParameterValues("serialNo");
			final String[] empCode = request.getParameterValues("keepInformedEmpId");
			final String[] empName = request.getParameterValues("keepInformedEmpName");
			final String offerStatus = request.getParameter("offerStatus");
			//String hiddenPublishCode = request.getParameter("hiddenPublishCode");
			final String hiddenRequisitionCode = request.getParameter("requisitionCode");
			//String result = model.save(offerDetails, serialNo, empCode, empName, offerStatus, hiddenPublishCode, hiddenRequisitionCode);
			final String result = model.save(this.offerDetails, serialNo, empCode,
					empName, offerStatus, hiddenRequisitionCode);
			this.offerDetails.setHiddenRequisitionCode(this.offerDetails.getRequisitionCode());

			if (result.equals("saved")) {
				model.getKeepInformedSavedRecord(this.offerDetails);
				this.addActionMessage("Record saved successfully!");
				this.offerDetails.setAddcandFlag(true);
				//if offer status is send for approval then mail goes to signing authority 
				if ("Send For Approval".equals(this.offerDetails.getOfferStatus())) {
					this.sendMailToSigningAuthority();
					
					
				}
				
				return "viewOfferDetails";
			} else if ("notSaved".equals(result)) {
				this.addActionMessage("Record can not be saved!");
			} else if ("updated".equals(result)) {
				model.getKeepInformedSavedRecord(this.offerDetails);
				if ("Send For Approval".equals(this.offerDetails.getOfferStatus())) {
					this.sendMailToSigningAuthority();
					
					
				}
				this.addActionMessage("Record updated successfully!");
				return "viewOfferDetails";
			} else if ("notUpdated".equals(result)) {
				this.addActionMessage("Record can not be updated!");
			} else if ("alreadyProcessed".equals(result)) {
				this.addActionMessage("All the vacancies for this requisition are already processed.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
				
		return "success";
	}

	private void sendMailToSigningAuthority() {
		String[] attachPath = null;
		try {
			final String candidateCode = request.getParameter("candidateCode");
			final String signingAuthorityCode = request.getParameter("authorityCode");
			final String offerCode = request.getParameter("offerCode");
			final String requisitionCode = request.getParameter("requisitionCode");

			Template letterTemplate = new Template(this.offerDetails.getTemplateCode());
			letterTemplate.initiate(context, session);
			
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);

			letterTemplate.getTemplateQueries();
			try {
				TemplateQuery templateQuery1 = letterTemplate.getTemplateQuery(1);
				logger.info("offered candidate code" + candidateCode);
				templateQuery1.setParameter(1, candidateCode);
				templateQuery1.setParameter(2, requisitionCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = letterTemplate.getTemplateQuery(2);
				logger.info("offered candidate code" + candidateCode);
				templateQuery2.setParameter(1, candidateCode);
				templateQuery2.setParameter(2, requisitionCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery3 = letterTemplate.getTemplateQuery(3);
				logger.info("offered candidate code" + candidateCode);
				templateQuery3.setParameter(1, candidateCode);
				templateQuery3.setParameter(2, requisitionCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery4 = letterTemplate.getTemplateQuery(4);
				logger.info("offered candidate code" + candidateCode);
				templateQuery4.setParameter(1, candidateCode);
				templateQuery4.setParameter(2, requisitionCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery5 = letterTemplate.getTemplateQuery(5);
				logger.info("offered candidate code" + candidateCode);
				templateQuery5.setParameter(1, candidateCode);
				templateQuery5.setParameter(2, requisitionCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery6 = letterTemplate.getTemplateQuery(6);
				logger.info("offered candidate code" + candidateCode);
				templateQuery6.setParameter(1, candidateCode);
				templateQuery6.setParameter(2, requisitionCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery7 = letterTemplate.getTemplateQuery(7);
				logger.info("offered candidate code" + candidateCode);
				String takeHomeAmt = model.getTotalAmt(candidateCode, requisitionCode,
						"takeHome", true);
				templateQuery7.setParameter(1, takeHomeAmt);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery8 = letterTemplate.getTemplateQuery(8);
				logger.info("offered candidate code" + candidateCode);
				String ctcPerMonth = model.getTotalAmt(candidateCode, requisitionCode,
						"ctcPerMonth", true);
				templateQuery8.setParameter(1, ctcPerMonth);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery9 = letterTemplate.getTemplateQuery(9);
				logger.info("offered candidate code" + candidateCode);
				String ctcPerYear = model.getTotalAmt(candidateCode, requisitionCode,
						"ctcPerYear", true);
				templateQuery9.setParameter(1, ctcPerYear);
				templateQuery9.setParameter(2, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.7)));
				templateQuery9.setParameter(3, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.24)));
				templateQuery9.setParameter(4, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.06)));
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery10 = letterTemplate.getTemplateQuery(10);
				logger.info("offered candidate code" + candidateCode);
				String ctcPerYear = model.getTotalAmt(candidateCode, requisitionCode,
						"ctcPerYear", false);
				templateQuery10.setParameter(1, ctcPerYear);

			} catch (final Exception e) {
				// TODO: handle exception
			}
			
			
			//comleteTemplate = template.execute(request, response, "OFFER_LETTER", true);
			
			
			
			
			
			
			String finaldata = "";
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
		//	String completeTemplate = letterTemplate.executeWriteFile(request,
			//response, "OFFER_LETTER");
			String completeTemplate =letterTemplate.execute(request, response, "OFFER_LETTER", true);
			try {
				completeTemplate = completeTemplate.replaceAll("&nbsp;", "");
				completeTemplate = completeTemplate.replaceAll("& ", "&amp; ");
				finaldata = "<html>" + completeTemplate + "</html>";

			} catch (final Exception e) {
				e.printStackTrace();
			}
			try {
				System.out.println("FinalData-------------"+finaldata);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				Document doc = builder.parse(new StringBufferInputStream(
						finaldata));
				ITextRenderer renderer = new ITextRenderer();
				renderer.setDocument(doc, null);
				PdfWriter writer = renderer.getWriter();
				String outputFile = getText("data_path") + "/OfferLetter/"
						+ poolName + "/OfferLetter/OfferLetter_"
						+ offerDetails.getCandidateName() + ".pdf";//append Employee ID
				File letter = new File(outputFile);
				if (!letter.exists()) {
					File l = new File(letter.getParent());
					boolean dir = l.mkdirs();
				}
				OutputStream os = new FileOutputStream(letter);
				System.out.println("OS------>"+os);
				System.out.println("OS------>"+getText("data_path"));
				System.out.println("OS------>"+poolName);
				renderer.layout();
				renderer.createPDF(os);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			
			
			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("RMS-OFFER DETAILS MAIL TO SIGNING AUTHORITY");
			template.getTemplateQueries();

			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			templateQuery1.setParameter(1, this.offerDetails.getUserEmpId());

			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			templateQuery2.setParameter(1, signingAuthorityCode);

			final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, this.offerDetails.getUserEmpId());

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, signingAuthorityCode);

			final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, requisitionCode);

			final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, offerCode);

			final EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, offerCode);
			
				
			attachPath = new String[1];
				attachPath[0] = getText("data_path") + "/OfferLetter/"
						+ poolName + "/OfferLetter/OfferLetter_"
						+ offerDetails.getCandidateName() + ".pdf";
			
			

			template.configMailAlert();

			// Add approval link -pass parameters to the link
			final String[] link_parameter = new String[1];
			final String[] link_labels = new String[1];
			final String applicationType = "OffetLetterSigningAuthorityApproval";
			link_parameter[0] = applicationType + "#" + signingAuthorityCode
					+ "#applicationDtls#";

			final String link = "/recruit/OfferApproval_input.action?";
			// link= PPEncrypter.encrypt(link);
			System.out.println("applicationDtls  ..." + link);
			link_parameter[0] += link;
			link_labels[0] = "Click Here To Process";
			template.addOnlineLink(request, link_parameter, link_labels);
			System.out.println("attachPath >>>>>>>>>"+String.valueOf(attachPath));
			template.sendApplMailWithAttachment(attachPath);
			//template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method backFromView
	 * @purpose to display offer details in editable form
	 * @return String
	 */
	public String backFromView() {
		try {
			// probation period checkbox
			if ("Yes".equals(this.offerDetails.getProbation().trim())) {
				this.offerDetails.setProbation("true");
			} else {
				this.offerDetails.setProbation("false");
			}
			// background check period checkbox
			if ("Yes".equals(this.offerDetails.getBackgroundCheck())) {
				this.offerDetails.setBackgroundCheck("true");
			} else {
				this.offerDetails.setBackgroundCheck("false");
			}
			// offer status
			if ("Due".equals(this.offerDetails.getOfferStatus())) {
				this.offerDetails.setOfferStatus("D");
			} else if ("Issued".equals(this.offerDetails.getOfferStatus())) {
				this.offerDetails.setOfferStatus("I");
			} else if ("Send For Approval".equals(this.offerDetails.getOfferStatus())) {
				this.offerDetails.setOfferStatus("S");
			} else if ("Accepted".equals(this.offerDetails.getOfferStatus())) {
				this.offerDetails.setOfferStatus("OA");
			} else if ("Rejected".equals(this.offerDetails.getOfferStatus())) {
				this.offerDetails.setOfferStatus("S");
			} else if ("Canceled".equals(this.offerDetails.getOfferStatus())) {
				this.offerDetails.setOfferStatus("C");
			}

			final OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.getKeepInformedSavedRecord(this.offerDetails);
			// For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPathBackFromView = getText("data_path") + "/upload" + poolName + "/Recruitment/";
			System.out.println("data path : " + fileDataPathBackFromView);
			this.offerDetails.setDataPath(fileDataPathBackFromView);
			// For adding data path ENDS
			model.terminate();
			this.offerDetails.setHiddenRequisitionCode(this.offerDetails.getRequisitionCode());
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @method toPostResume
	 * @purpose to navigate to the post resume to add new candidate
	 * @return String
	 */
	public String toPostResume() {
		try {
			final Object[] formValues = new Object[47];
			// form name
			formValues[0] = "offer";
			// reqs code
			formValues[1] = this.offerDetails.getRequisitionCode();
			// reqs name
			formValues[2] = this.offerDetails.getRequisitionName();
			// position
			formValues[3] = this.offerDetails.getPosition();
			// hiring manager
			formValues[4] = this.offerDetails.getHiringManager();
			// division
			formValues[5] = this.offerDetails.getDivision();
			// branch
			formValues[6] = this.offerDetails.getBranch();
			// department
			formValues[7] = this.offerDetails.getDepartment();
			// joining date
			formValues[8] = this.offerDetails.getJoiningDate();
			// offer date
			formValues[9] = this.offerDetails.getOfferDate();
			// current CTC
			formValues[10] = this.offerDetails.getCurrentCtc();
			// negotiated CTC
			formValues[11] = this.offerDetails.getNegotiatedCtc();
			// job desc code
			formValues[12] = this.offerDetails.getJobCode();
			// job desc
			formValues[13] = this.offerDetails.getJobDescription();
			// roles n responsibities
			formValues[14] = this.offerDetails.getRolesResponsibility();
			// emp type
			formValues[15] = this.offerDetails.getEmpType();
			// emp type code
			formValues[16] = this.offerDetails.getEmpTypeCode();
			// hring manager
			formValues[17] = this.offerDetails.getHiringMgr();
			// hiring manager code
			formValues[18] = this.offerDetails.getHiringMgrCode();
			// expected joining date
			formValues[19] = this.offerDetails.getExpJoiningDate();
			// reproting to
			formValues[20] = this.offerDetails.getReportingTo();
			// reproting to code
			formValues[21] = this.offerDetails.getReportingToCode();
			// requirement code
			formValues[22] = this.offerDetails.getTestReqCode();
			// requirement
			formValues[23] = this.offerDetails.getTestRequirements();
			// grade
			formValues[24] = this.offerDetails.getGrade();
			// grade code
			formValues[25] = this.offerDetails.getGradeCode();
			// template
			formValues[26] = this.offerDetails.getTemplate();
			// template code
			formValues[27] = this.offerDetails.getTemplateCode();
			// probation period
			formValues[28] = this.offerDetails.getProbation();
			// signing authority
			formValues[29] = this.offerDetails.getSigningAuthority();
			// signing authority code
			formValues[30] = this.offerDetails.getAuthorityCode();
			// background check
			formValues[31] = this.offerDetails.getBackgroundCheck();
			// designation
			formValues[32] = this.offerDetails.getDesignation();
			// offer status
			formValues[33] = this.offerDetails.getOfferStatus();
			// offered CTC
			formValues[34] = this.offerDetails.getOfferedCtc();
			// comments
			formValues[35] = this.offerDetails.getRemarks();
			// cand constraints
			formValues[36] = this.offerDetails.getCandConstraints();
			// cand code
			formValues[37] = this.offerDetails.getCandidateCode();
			// cand name
			formValues[38] = this.offerDetails.getCandidateName();
			// probation months
			formValues[39] = this.offerDetails.getMonths();
			formValues[40] = this.offerDetails.getPositionCode(); 
			formValues[41] = this.offerDetails.getDivisionCode(); 
			formValues[42] = this.offerDetails.getDeptCode(); 
			formValues[43] = this.offerDetails.getBranchCode(); 
			formValues[44] = this.offerDetails.getRecruiterId(); 
			formValues[45] = this.offerDetails.getRecruiterName(); 
			formValues[46] = this.offerDetails.getCandidateEmailID();
			request.setAttribute("formValues", formValues);
			request.setAttribute("formListValues", new Object[0][0]);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "postResume";
	}

	/**
	 * @method returnFromPostResume
	 * @purpose return from the post resume form
	 * @return String
	 */
	public String returnFromPostResume() {
		try {
			final OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			final String candCode = (String) request.getAttribute("candCode");
			// HEADER OBJECT
			final Object[] formValues = (Object[]) request.getAttribute("formValues");
			model.returnFromPostResume(this.offerDetails, candCode, formValues);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @method setRequisition
	 * @date 30/08/2010
	 * @purpose to set Job Description
	 * @return String
	 */

	public String setRequisition() {
		OfferDetailsModel model;
		try {
			final String query = "SELECT HRMS_REC_REQS_HDR.REQS_NAME, HRMS_REC_REQS_HDR.REQS_JOBDESC_NAME, HRMS_REC_REQS_HDR.REQS_ROLE_RESPON " + 
					 			 "FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE=" + this.offerDetails.getRequisitionCode();
			model = new OfferDetailsModel();
			model.initiate(context, session);
			final Object[][] data = model.getSqlModel().getSingleResult(query);
			this.offerDetails.setJobDescription(String.valueOf(data[0][1]));
			this.offerDetails.setRolesResponsibility(String.valueOf(data[0][2]));
			this.offerDetails.setRequiredByDate(this.offerDetails.getRequiredByDate());
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * @method reset
	 * @purpose to clear all the form fields
	 * @return String
	 */
	public String reset() {
		try {
			this.offerDetails.setOfferCode("");
			this.offerDetails.setRequisitionCode("");
			this.offerDetails.setRequisitionName("");
			this.offerDetails.setPosition("");
			this.offerDetails.setDivision("");
			this.offerDetails.setBranch("");
			this.offerDetails.setDepartment("");
			this.offerDetails.setCandidateCode("");
			this.offerDetails.setCandidateName("");
			this.offerDetails.setJoiningDate("");
			// this.offerDetails.setOfferDate("");
			this.offerDetails.setCurrentCtc("");
			this.offerDetails.setNegotiatedCtc("");
			this.offerDetails.setJobDescription("");
			this.offerDetails.setRolesResponsibility("");
			this.offerDetails.setEmpTypeCode("");
			this.offerDetails.setEmpType("");
			this.offerDetails.setHiringMgr("");
			this.offerDetails.setHiringMgrCode("");
			this.offerDetails.setExpJoiningDate("");
			this.offerDetails.setReportingTo("");
			this.offerDetails.setReportingToCode("");
			this.offerDetails.setTestRequirements("");
			this.offerDetails.setGrade("");
			this.offerDetails.setGradeCode("");
			this.offerDetails.setTemplate("");
			this.offerDetails.setTemplateCode("");
			this.offerDetails.setProbation("false");
			this.offerDetails.setMonths("");
			this.offerDetails.setSigningAuthority("");
			this.offerDetails.setAuthorityCode("");
			this.offerDetails.setBackgroundCheck("false");
			this.offerDetails.setDesignation("");
			this.offerDetails.setOfferStatus("");
			this.offerDetails.setOfferedCtc("");
			this.offerDetails.setRemarks("");
			this.offerDetails.setCandConstraints("");
			this.offerDetails.setCandidateEmailID("");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @method f9RequisitionAction
	 * @purpose to select reqs code
	 * @return String
	 */
	public String f9RequisitionAction() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//Only Published vacancies get displayed into List
		final String query = "SELECT HRMS_REC_REQS_HDR.REQS_NAME, HRMS_RANK.RANK_NAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, TO_CHAR(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY') , " + 
							 " HRMS_REC_REQS_HDR.REQS_CODE, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , HRMS_RANK.RANK_ID, HRMS_DIVISION.DIV_ID, HRMS_CENTER.CENTER_ID, HRMS_DEPT.DEPT_ID " + 
							 " FROM HRMS_REC_REQS_HDR   " + 
							 " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)  " + 
							 " INNER  JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) " + 
							 " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) " + 
							 " INNER JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID)  " + 
							 " INNER JOIN HRMS_DIVISION ON (HRMS_REC_REQS_HDR.REQS_DIVISION = HRMS_DIVISION.DIV_ID) " + 
							 " INNER JOIN HRMS_CENTER ON (HRMS_REC_REQS_HDR.REQS_BRANCH = HRMS_CENTER.CENTER_ID)  " + 
							 " INNER JOIN HRMS_DEPT ON (HRMS_REC_REQS_HDR.REQS_DEPT = HRMS_DEPT.DEPT_ID)  " + 
							 " WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN ('A','Q') AND HRMS_REC_REQS_HDR.REQS_STATUS = 'O' AND HRMS_REC_REQS_VACDTL.VACAN_STATUS='P' ORDER BY HRMS_REC_REQS_HDR.REQS_DATE DESC ";

		/*"SELECT HRMS_REC_REQS_HDR.REQS_NAME, HRMS_RANK.RANK_NAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, TO_CHAR(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY') , "  
		+" HRMS_REC_REQS_HDR.REQS_CODE, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , HRMS_RANK.RANK_ID, HRMS_DIVISION.DIV_ID, HRMS_CENTER.CENTER_ID, HRMS_DEPT.DEPT_ID "//, HRMS_REC_VACPUB_HDR.PUB_CODE  "
		+" FROM HRMS_REC_REQS_HDR   "
		+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "   
		+" INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "  
		+" INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "  
		+" INNER JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID)   "
		+" INNER JOIN HRMS_DIVISION ON (HRMS_REC_REQS_HDR.REQS_DIVISION = HRMS_DIVISION.DIV_ID) "  
		+" INNER JOIN HRMS_CENTER ON (HRMS_REC_REQS_HDR.REQS_BRANCH = HRMS_CENTER.CENTER_ID)   "
		+" INNER JOIN HRMS_DEPT ON (HRMS_REC_REQS_HDR.REQS_DEPT = HRMS_DEPT.DEPT_ID)  "
		+" LEFT JOIN HRMS_REC_VACANCIES_STATS ON (HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE " 
		+" AND HRMS_REC_VACANCIES_STATS.VAC_PUBLISH_CODE=HRMS_REC_VACPUB_HDR.PUB_CODE) "
		+" WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN ('A','Q') AND HRMS_REC_REQS_HDR.REQS_STATUS = 'O' AND HRMS_REC_REQS_VACDTL.VACAN_STATUS='P' AND HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN='N' "
		+" GROUP BY HRMS_REC_REQS_HDR.REQS_NAME, HRMS_RANK.RANK_NAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,   "
		+" HRMS_REC_REQS_HDR.REQS_CODE, HRMS_EMP_OFFC.EMP_FNAME,HRMS_EMP_OFFC.EMP_MNAME,HRMS_EMP_OFFC.EMP_LNAME , HRMS_RANK.RANK_ID, HRMS_DIVISION.DIV_ID, HRMS_CENTER.CENTER_ID, HRMS_DEPT.DEPT_ID "; //, HRMS_REC_VACPUB_HDR.PUB_CODE";
		 */
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = { this.getMessage("reqs.code"), this.getMessage("position"),
							 this.getMessage("division"), this.getMessage("branch"), this.getMessage("department"), "Required By Date" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		final String[] headerWidth = { "15", "20", "15", "15", "15", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		final String[] fieldNames = { "requisitionName", "position", "division",
				"branch", "department", "requiredByDate", "requisitionCode",
				"hiringManager", "positionCode", "divisionCode", "branchCode",
				"deptCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "OfferDetails_setRequisition.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9CandidateAction
	 * @purpose to select cand details
	 * @return String
	 */
	public String f9CandidateAction() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = "SELECT CAND_CODE, CAND_FIRST_NAME||'
		 * '||CAND_MID_NAME||' '||CAND_LAST_NAME " +"FROM HRMS_REC_CAND_DATABANK "
		 * +"WHERE CAND_CODE NOT IN (SELECT RESUME_CAND_CODE FROM
		 * HRMS_REC_RESUME_BANK " // +"UNION SELECT EVAL_CAND_CODE FROM
		 * HRMS_REC_CANDEVAL " +"UNION SELECT OFFER_CAND_CODE FROM
		 * HRMS_REC_OFFER) " +"ORDER BY UPPER(CAND_FIRST_NAME||'
		 * '||CAND_MID_NAME||' '||CAND_LAST_NAME)";
		 */

		String query = "SELECT HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME, HRMS_REC_CAND_DATABANK.CAND_CODE, HRMS_REC_CAND_DATABANK.CAND_EMAIL_ID "
				+ "FROM HRMS_REC_CAND_DATABANK "
				+ "WHERE HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN (SELECT DISTINCT HRMS_REC_RESUME_BANK.RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
				+ "WHERE HRMS_REC_RESUME_BANK.RESUME_REQS_CODE = "
				+ offerDetails.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT HRMS_REC_CANDEVAL.EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL "
				+ "WHERE HRMS_REC_CANDEVAL.EVAL_REQS_CODE = "
				+ offerDetails.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT HRMS_REC_OFFER.OFFER_CAND_CODE FROM HRMS_REC_OFFER "
				+ "WHERE HRMS_REC_OFFER.OFFER_REQS_CODE = "
				+ offerDetails.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT HRMS_REC_APPOINT.APPOINT_CAND_CODE FROM HRMS_REC_APPOINT "
				+ "WHERE HRMS_REC_APPOINT.APPOINT_REQS_CODE = "
				+ offerDetails.getRequisitionCode()
				+ ") "
				+ " AND HRMS_REC_CAND_DATABANK.CAND_CONVERT_FLAG = 'N' "
				+ " AND HRMS_REC_CAND_DATABANK.CAND_SHORT_STATUS NOT IN ('E')"
				+ "ORDER BY UPPER(HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME)";

		/*
		 * String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||'
		 * '||CAND_LAST_NAME,CAND_CODE FROM " +" HRMS_REC_CAND_DATABANK ORDER BY
		 * CAND_CODE desc ";
		 */

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { this.getMessage("cand.name") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "candidateName", "candidateCode",
				"candidateEmailID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

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
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9JobDescAction
	 * @purpose to select job desc details
	 * @return String
	 */
	public String f9JobDescAction() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HRMS_JOB_DESCRIPTION.JOB_DESC_NAME, HRMS_JOB_DESCRIPTION.JOB_DESC_ROLE_RESP, HRMS_JOB_DESCRIPTION.JOB_DESC_CODE "
				+ "FROM HRMS_JOB_DESCRIPTION "
				+ "ORDER BY HRMS_JOB_DESCRIPTION.JOB_DESC_EFFC_DATE DESC";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { this.getMessage("job.desc"), this.getMessage("roles.res") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "jobDescription", "rolesResponsibility",
				"jobCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9EmpTypeAction
	 * @purpose to select emp type
	 * @return String
	 */
	public String f9EmpTypeAction() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HRMS_EMP_TYPE.TYPE_NAME, HRMS_EMP_TYPE.TYPE_ID FROM HRMS_EMP_TYPE ORDER BY HRMS_EMP_TYPE.TYPE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { this.getMessage("employee.type") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "empType", "empTypeCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9HireMgrAction
	 * @purpose to select hiring manager
	 * @return String
	 */
	public String f9HireMgrAction() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
				+ "FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' "
				+ "ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { this.getMessage("hiring.mgr") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "hiringMgr", "hiringMgrCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9ReportingAction
	 * @purpose to select reporting to person
	 * @return String
	 */
	public String f9ReportingAction() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
				+ "FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' "
				+ "ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { this.getMessage("reporting.to") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "reportingTo", "reportingToCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9SigningAuthAction
	 * @purpose to select signing authority
	 * @return String
	 */
	public String f9SigningAuthAction() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_RANK.RANK_NAME, HRMS_EMP_OFFC.EMP_ID "
				+ "FROM HRMS_EMP_OFFC "
				+ "INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' "
				+ "ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { this.getMessage("signing.authority"),
				this.getMessage("designation") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "signingAuthority", "designation",
				"authorityCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @method f9GradeAction
	 * @purpose to select grade
	 * @return String
	 */
	public String f9GradeAction() {
		offerDetails.setGradeflag("0");
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT DISTINCT HRMS_CADRE.CADRE_NAME, HRMS_CADRE.CADRE_ID FROM  HRMS_CADRE ORDER BY HRMS_CADRE.CADRE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { this.getMessage("grade") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "grade", "gradeCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String previewOfferPDF() {
		String comleteTemplate = "";
		String finaldata = "";
		try {
			String tempCode = request.getParameter("templateCode");// tempalte code
			String candCode = request.getParameter("candidateCode");// cand code
			String reqsCode = request.getParameter("requisitionCode");// reqs  code
			logger.info("tamplate code " + tempCode);
			logger.info("candidate code " + candCode);
			logger.info("reqs code " + reqsCode);

			Template template = new Template(tempCode);
			template.initiate(context, session);

			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);

			template.getTemplateQueries();
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				logger.info("offered candidate code" + candCode);
				templateQuery1.setParameter(1, candCode);
				templateQuery1.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				logger.info("offered candidate code" + candCode);
				templateQuery2.setParameter(1, candCode);
				templateQuery2.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				logger.info("offered candidate code" + candCode);
				templateQuery3.setParameter(1, candCode);
				templateQuery3.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				logger.info("offered candidate code" + candCode);
				templateQuery4.setParameter(1, candCode);
				templateQuery4.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery5 = template.getTemplateQuery(5);
				logger.info("offered candidate code" + candCode);
				templateQuery5.setParameter(1, candCode);
				templateQuery5.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery6 = template.getTemplateQuery(6);
				logger.info("offered candidate code" + candCode);
				templateQuery6.setParameter(1, candCode);
				templateQuery6.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery7 = template.getTemplateQuery(7);
				logger.info("offered candidate code" + candCode);
				String takeHomeAmt = model.getTotalAmt(candCode, reqsCode,
						"takeHome", true);
				templateQuery7.setParameter(1, takeHomeAmt);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery8 = template.getTemplateQuery(8);
				logger.info("offered candidate code" + candCode);
				String ctcPerMonth = model.getTotalAmt(candCode, reqsCode,
						"ctcPerMonth", true);
				templateQuery8.setParameter(1, ctcPerMonth);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery9 = template.getTemplateQuery(9);
				logger.info("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode, reqsCode,
						"ctcPerYear", true);
				templateQuery9.setParameter(1, ctcPerYear);
				templateQuery9.setParameter(2, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.7)));
				templateQuery9.setParameter(3, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.24)));
				templateQuery9.setParameter(4, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.06)));
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery10 = template.getTemplateQuery(10);
				logger.info("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode, reqsCode,
						"ctcPerYear", false);
				templateQuery10.setParameter(1, ctcPerYear);

			} catch (final Exception e) {
				// TODO: handle exception
			}
			comleteTemplate = template.execute(request, response, "OFFER_LETTER", true);
			logger.info("comleteTemplate....." + comleteTemplate);
			comleteTemplate = comleteTemplate.replaceAll("&nbsp;", "");
			comleteTemplate = comleteTemplate.replaceAll("& ", "&amp; ");
			comleteTemplate = comleteTemplate.replaceAll("&rsquo;", "'");
			comleteTemplate = comleteTemplate.replaceAll("&ndash;", "-");
			finaldata = "<html>" + comleteTemplate + "</html>";
			/*String poolName = String.valueOf(session.getAttribute("session_pool"));
			
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + "OFFER_LETTER" + ".pdf\"");
			String outputFile = getText("data_path")+"/OfferLetter/"+poolName+"/OfferLetter/OfferLetter_"+ offerDetails.getCandidateName()+".pdf";//append Employee ID
			System.out.println("Outut File >>>>"+outputFile);*/


			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(finaldata));
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
	
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""+ "OFFER_LETTER_"+offerDetails.getCandidateName()+".pdf\"");
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/OfferLetter/"+client+"/OfferLetter_"+offerDetails.getCandidateName()+".pdf";//append Employee ID
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
		 	
            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/OfferLetter/"+client+"/OfferLetter_"+offerDetails.getCandidateName()+".pdf");
            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
            int i = 0;
            if(client.equals("pool_glodyne")){
            Image watermark_image = Image.getInstance(getText("data_path")+"/OfferLetter/rings.jpg");
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
        catch (final Exception i1) {
            i1.printStackTrace();
        }
	
			
			model.terminate();
		} catch (final Exception e) {
			System.out.println("EXCEPTION OCCUR");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @method previewoffer
	 * @purpose to prieview offer letter details
	 * @return String
	 */
	public String previewoffer() {
		try {
			String tempCode = request.getParameter("templateCode");// tempalte code
			String candCode = request.getParameter("candidateCode");// cand code
			String reqsCode = request.getParameter("requisitionCode");// reqs  code

			logger.info("tamplate code " + tempCode);
			logger.info("candidate code " + candCode);
			logger.info("reqs code " + reqsCode);

			Template template = new Template(tempCode);
			template.initiate(context, session);

			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);

			template.getTemplateQueries();
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				logger.info("offered candidate code" + candCode);
				templateQuery1.setParameter(1, candCode);
				templateQuery1.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				logger.info("offered candidate code" + candCode);
				templateQuery2.setParameter(1, candCode);
				templateQuery2.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				logger.info("offered candidate code" + candCode);
				templateQuery3.setParameter(1, candCode);
				templateQuery3.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				logger.info("offered candidate code" + candCode);
				templateQuery4.setParameter(1, candCode);
				templateQuery4.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery5 = template.getTemplateQuery(5);
				logger.info("offered candidate code" + candCode);
				templateQuery5.setParameter(1, candCode);
				templateQuery5.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery6 = template.getTemplateQuery(6);
				logger.info("offered candidate code" + candCode);
				templateQuery6.setParameter(1, candCode);
				templateQuery6.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery7 = template.getTemplateQuery(7);
				logger.info("offered candidate code" + candCode);
				String takeHomeAmt = model.getTotalAmt(candCode, reqsCode,
						"takeHome", true);
				templateQuery7.setParameter(1, takeHomeAmt);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery8 = template.getTemplateQuery(8);
				logger.info("offered candidate code" + candCode);
				String ctcPerMonth = model.getTotalAmt(candCode, reqsCode,
						"ctcPerMonth", true);
				templateQuery8.setParameter(1, ctcPerMonth);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery9 = template.getTemplateQuery(9);
				logger.info("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode, reqsCode,
						"ctcPerYear", true);
				templateQuery9.setParameter(1, ctcPerYear);
				templateQuery9.setParameter(2, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.7)));
				templateQuery9.setParameter(3, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.24)));
				templateQuery9.setParameter(4, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.06)));
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery10 = template.getTemplateQuery(10);
				logger.info("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode, reqsCode,
						"ctcPerYear", false);
				templateQuery10.setParameter(1, ctcPerYear);

			} catch (final Exception e) {
				// TODO: handle exception
			}
			String comleteTemplate = template.execute(request, response,
					"OFFER_LETTER");
			logger.info("comleteTemplate....." + comleteTemplate);

			model.terminate();
		} catch (final Exception e) {
			try {
				String type = "Txt";
				String title = "Template letter";
				e.printStackTrace();
				String finaldata = "<html>" + "" + "</html>";
				org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
						type, title);

				byte[] buf = finaldata.getBytes();

				response.setContentType("application/msword");
				response.getOutputStream().write(buf);
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + "OFFER_LETTER" + ".doc\"");
				response.setHeader("cache-control", "no-cache");
			} catch (final Exception e1) {
				e1.printStackTrace();
			}

		}

		return null;
	}

	public String emailoffer() {
		try {
			addActionMessage("Mail has been sent successfully to the candidate");
			String currentOfferStatus = request.getParameter("offerStatus");
			if (currentOfferStatus.equals("Issued")) {
				offerDetails.setOfferStatus("I");
			}
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String tempCode = request.getParameter("templateCode"); // Offer Letter template
			String candCode = request.getParameter("candidateCode"); // Candidate Code
			String reqsCode = request.getParameter("requisitionCode"); // Requisition Code
			String offerCode = request.getParameter("offerCode"); //Offer Code
			//String publishCode = request.getParameter("hiddenPublishCode");
			String requisitionCode = request
					.getParameter("hiddenRequisitionCode");
			String[] keepInfo = null;
			keepInfo = request.getParameterValues("keepInformedEmpId");

			logger.info("tempCode >>" + tempCode);
			logger.info("candidate code >>" + candCode);
			logger.info("reqs code >>" + reqsCode);
			logger.info("Offer Code >>" + offerCode);
			//logger.info("publishCode >>" + publishCode);
			logger.info("requisitionCode >>" + requisitionCode);

			Template letterTemplate = new Template(tempCode);
			letterTemplate.initiate(context, session);

			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);

			letterTemplate.getTemplateQueries();
			try {
				TemplateQuery templateQuery1 = letterTemplate
						.getTemplateQuery(1);
				templateQuery1.setParameter(1, candCode);
				templateQuery1.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = letterTemplate
						.getTemplateQuery(2);
				templateQuery2.setParameter(1, candCode);
				templateQuery2.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery3 = letterTemplate
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, candCode);
				templateQuery3.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery4 = letterTemplate
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, candCode);
				templateQuery4.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery5 = letterTemplate
						.getTemplateQuery(5);
				logger.info("offered candidate code" + candCode);
				templateQuery5.setParameter(1, candCode);
				templateQuery5.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery6 = letterTemplate
						.getTemplateQuery(6);
				logger.info("offered candidate code" + candCode);
				templateQuery6.setParameter(1, candCode);
				templateQuery6.setParameter(2, reqsCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery7 = letterTemplate
						.getTemplateQuery(7);
				logger.info("offered candidate code" + candCode);
				String takeHomeAmt = model.getTotalAmt(candCode, reqsCode,
						"takeHome", true);
				templateQuery7.setParameter(1, takeHomeAmt);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery8 = letterTemplate
						.getTemplateQuery(8);
				logger.info("offered candidate code" + candCode);
				String ctcPerMonth = model.getTotalAmt(candCode, reqsCode,
						"ctcPerMonth", true);
				templateQuery8.setParameter(1, ctcPerMonth);
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery9 = letterTemplate
						.getTemplateQuery(9);
				logger.info("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode, reqsCode,
						"ctcPerYear", true);
				templateQuery9.setParameter(1, ctcPerYear);
				templateQuery9.setParameter(2, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.7)));
				templateQuery9.setParameter(3, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.24)));
				templateQuery9.setParameter(4, String.valueOf(Math.round(Double
						.parseDouble(ctcPerYear) * 0.06)));
			} catch (final Exception e) {
				// TODO: handle exception
			}

			try {
				TemplateQuery templateQuery10 = letterTemplate
						.getTemplateQuery(10);
				logger.info("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode, reqsCode,
						"ctcPerYear", false);
				templateQuery10.setParameter(1, ctcPerYear);

			} catch (final Exception e) {
				// TODO: handle exception
			}

			String finaldata = "";
			String completeTemplate = letterTemplate.executeWriteFile(request,
					response, "OFFER_LETTER");
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
				String outputFile = getText("data_path") + "/OfferLetter/"
						+ poolName + "/OfferLetter/OfferLetter_"
						+ offerDetails.getCandidateName() + ".pdf";//append Employee ID
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
			template.setEmailTemplate("Candidate Offer Letter Email");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			templateQuery1.setParameter(1, offerDetails.getUserEmpId());

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			templateQuery2.setParameter(1, candCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, offerDetails.getUserEmpId());
			templateQuery3.setParameter(2, offerDetails.getUserEmpId());
			templateQuery3.setParameter(3, offerCode);

			// For sending OfferLetter and Annexure attachment BEGINS
			String path = offerDetails.getDataPath();
			String annexureFileName = offerDetails.getAnnextureFileName();
			System.out.println("path : " + path);
			System.out.println("annexureFileName : " + annexureFileName);
			String[] attachPath = null;
			if (!annexureFileName.equals("")) {
				attachPath = new String[2];
				attachPath[0] = getText("data_path") + "/OfferLetter/"
						+ poolName + "/OfferLetter/OfferLetter_"
						+ offerDetails.getCandidateName() + ".pdf";

				attachPath[1] = path + annexureFileName;
			} else {
				attachPath = new String[1];
				attachPath[0] = getText("data_path") + "/OfferLetter/"
						+ poolName + "/OfferLetter/OfferLetter_"
						+ offerDetails.getCandidateName() + ".pdf";
			}

			// For sending OfferLetter and Annexure attachment ENDS
			template.configMailAlert();
			if (keepInfo != null && keepInfo.length > 0) {
				/*for (int i = 0; i < keepInfo.length; i++) {
					System.out.println("Keep Inform : "+keepInfo[i]);
				}*/
				template.sendApplicationMailToKeepInfo(keepInfo);
			}

			//Offer Approved : Status --> OA
			//Offer Rejected : Status --> OR
			String[] link_parameter = new String[2];
			String[] link_labels = new String[2];
			String applicationType = "OfferLetterDetails";

			//link_parameter[0] = applicationType + "#" +  candCode + "#" + offerCode + "#" + "OA" + "#" + publishCode + "#" + requisitionCode + "#" + "...";
			//link_parameter[1] = applicationType + "#" +  candCode + "#" + offerCode + "#" + "S" + "#" + publishCode + "#" + requisitionCode + "#" + "...";

			link_parameter[0] = applicationType + "#" + candCode + "#"
					+ offerCode + "#" + "OA" + "#" + requisitionCode + "#"
					+ "...";
			link_parameter[1] = applicationType + "#" + candCode + "#"
					+ offerCode + "#" + "S" + "#" + requisitionCode + "#"
					+ "...";

			link_labels[0] = "Accept Offer";
			link_labels[1] = "Reject Offer";
			template.addOnlineLink(request, link_parameter, link_labels);
			template.sendApplMailWithAttachment(attachPath);
			//template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

			model.initiate(context, session);
			model.getKeepInformedSavedRecord(offerDetails);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String f9OfferTemplate() {
		String query = "  SELECT HRMS_LETTERTEMPLATE_HDR.TEMPLATE_NAME, HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR "
				// +" WHERE TEMPLATE_TYPE=TO_CHAR((SELECT LETTERTYPE_ID FROM
				// HRMS_LETTERTYPE WHERE LETTERTYPE LIKE '%OFFER%')) "
				+ " WHERE HRMS_LETTERTEMPLATE_HDR.TEMPLATE_TYPE=1 "
				+ " ORDER BY HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID";

		String[] headers = { "Template Name" };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "template", "templateCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9ReportingAdminAction() {

		String query = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
				+ "FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' "
				+ "ORDER BY HRMS_EMP_OFFC.EMP_ID";
		String[] headers = { this.getMessage("reporting.to.admin") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "reportingToAdmin", "reportingToAdminCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9OfferEmailTemplate() {
		String query = "  SELECT HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME, HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID FROM HRMS_EMAILTEMPLATE_HDR ORDER BY HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID";

		String[] headers = { "Template Name" };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "emailtemplate", "emailtemplateCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String searchRec() throws Exception {
		OfferDetailsModel model = new OfferDetailsModel();
		model.initiate(context, session);
		model.viewDetails(offerDetails, offerDetails.getTemplateCode());
		model.terminate();
		return "viewFirst";
	}

	public String defineSalaryStructure() throws Exception {
		try {
			offerDetails.setOfferedCtc(request.getParameter("offer"));
			offerDetails.setRequisitionCode(request.getParameter("reqCode"));
			offerDetails.setCandidateCode(request.getParameter("candCode"));
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.viewSalaryStructure(offerDetails);
			model.viewSalaryStructureDebit(offerDetails);
			model.terminate();
			getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "defineSalStructure";
	}

	public String f9salgradeAction() throws Exception {

		String query = " SELECT HRMS_SALGRADE_HDR.SALGRADE_CODE, NVL(HRMS_SALGRADE_HDR.SALGRADE_TYPE ,' ') FROM HRMS_SALGRADE_HDR ORDER BY HRMS_SALGRADE_HDR.SALGRADE_CODE ";

		String[] headers = { "Salary Grade Code", "Salary Grade Name" };
		String[] headersWidth = { "20", " 40" };
		String[] fieldName = { "salgrdId", "salgrdName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = ""; //"OfferDetails_gradeDetail.action";
		this.setF9Window(query, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String gradeDetail() {
		try {
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			String gradeCode = offerDetails.getSalgrdId();
			//String gradeCode = request.getParameter("gradeCode");
			System.out.println("gradeCode >>>>>>>>>>>" + gradeCode);
			offerDetails.setGradeflag("2");
			model.showGrade(offerDetails, gradeCode, request);
			offerDetails.setGrdFlag("true");
			getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "defineSalStructure";
	}

	public String saveSalaryStruc() throws Exception {
		try {
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.saveSalaryStruc(offerDetails, request);
			model.saveSalaryStrucDbt(offerDetails, request);
			addActionMessage("Record saved successfully");
			model.terminate();
			getNavigationPanel(2);
			offerDetails.setViewFlagStruc("false");
			offerDetails.setEnableAll("N");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "defineSalStructure";
	}

	public String editSalaryStruc() throws Exception {
		try {
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.editSalaryStruc(offerDetails, request);
			model.editSalaryStrucDbt(offerDetails, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "defineSalStructure";
	}

	public String f9frmaction() throws Exception {
		offerDetails.setCalflag("0");
		// request.setAttribute("ss","0");
		String sql = " SELECT NVL(HRMS_FORMULABUILDER_HDR.FORMULA_NAME,' '), HRMS_FORMULABUILDER_HDR.FORMULA_ID FROM HRMS_FORMULABUILDER_HDR ORDER BY HRMS_FORMULABUILDER_HDR.FORMULA_ID ";
		String[] headers = { "Formula Name" };
		String[] headersWidth = { "80" };
		String[] fieldName = { "frmName", "frmId" };
		String submitFlag = "false";
		int[] columnIndex = { 0, 1 };
		String submitToMethod = "";
		this.setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String calculate() {
		try {
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			Object[] sCode = request.getParameterValues("salCode");
			Object[] sHead = request.getParameterValues("salHead");
			// Object[] scurAmt = request.getParameterValues("curAmt");
			Object[] snewAmt = request.getParameterValues("newAmt");
			Object[] salPer = request.getParameterValues("salPerdiocity");
			model.showFormula(sCode, sHead, snewAmt, salPer, offerDetails);
			offerDetails.setCalflag("1");
			// String ss=null;
			request.setAttribute("ss", "1");
			// promotion.setSelectFlag("false");
			model.terminate();
			getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "defineSalStructure";
	}

	/**
	 * Created by Guru on 17/06/2009
	 * 
	 * @purpose To get all the fields to offer details form whatever filled by
	 *          user in manpower requisition
	 * @return String
	 * @throws Exception
	 */
	public String detailsOffer() throws Exception {
		try {
			String reqCode = request.getParameter("reqCode");
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			// For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPathFrmCreateOffer = this.getText("data_path") + "/upload" + poolName + "/Recruitment/";
			this.offerDetails.setDataPath(fileDataPathFrmCreateOffer);
			// For adding data path ENDS
			model.getRequsitionDetails(offerDetails, reqCode);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * following function is called when position seacrh window is clicked.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionDesg() throws Exception {
		String query = " SELECT NVL(HRMS_RANK.RANK_NAME,' '), HRMS_RANK.RANK_ID FROM HRMS_RANK WHERE HRMS_RANK.RANK_STATUS='A' ORDER BY UPPER(HRMS_RANK.RANK_NAME) ";
		String[] headers = { this.getMessage("desghead") };
		String[] headerWidth = { "50" };
		String[] fieldNames = { "position", "positionCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9actionDiv() throws Exception {// -f9action for division
		String query = " SELECT HRMS_DIVISION.DIV_NAME, HRMS_DIVISION.DIV_ID FROM HRMS_DIVISION";
		if (offerDetails.getUserProfileDivision() != null
				&& offerDetails.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ offerDetails.getUserProfileDivision() + ")";
		query += " ORDER BY  UPPER(DIV_NAME) ";
		String[] headers = { this.getMessage("division") };
		String[] headerWidth = { "50" };
		String[] fieldNames = { "division", "divisionCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * following function is called when the branch search window button is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionBrn() throws Exception {
		String query = " SELECT  NVL(HRMS_CENTER.CENTER_NAME,' '), HRMS_CENTER.CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(HRMS_CENTER.CENTER_NAME) ";
		String[] headers = { this.getMessage("branch") };
		String[] headerWidth = { "50" };
		String[] fieldNames = { "branch", "branchCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * following function is called when the department search window button is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionDept() throws Exception {// --------f9action for
		// division
		String query = " SELECT HRMS_DEPT.DEPT_NAME, HRMS_DEPT.DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(HRMS_DEPT.DEPT_NAME) ";
		String[] headers = { this.getMessage("department") };
		String[] headerWidth = { "50" };
		String[] fieldNames = { "department", "deptCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9Recruiter() {
		OfferDetailsModel model = new OfferDetailsModel();
		model.initiate(context, session);
		Object[][] chkIfPublishData = null;
		try {
			String checkIfPublished = "SELECT HRMS_REC_VACPUB_HDR.PUB_CODE FROM HRMS_REC_VACPUB_HDR "
					+ "WHERE HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = "
					+ offerDetails.getRequisitionCode()
					+ " AND HRMS_REC_VACPUB_HDR.PUB_STATUS = 'P'";
			chkIfPublishData = model.getSqlModel().getSingleResult(
					checkIfPublished);
		} catch (final Exception e) {
			logger.error("exception in chkIfPublishData", e);
		}
		model.terminate();
		String query = "";

		if (chkIfPublishData != null && chkIfPublishData.length > 0) {
			query = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(HRMS_CENTER.CENTER_NAME,' '),NVL(HRMS_RANK.RANK_NAME,' '), "
					+ " HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID FROM HRMS_REC_VACPUB_RECDTL "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  "
					+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK   "
					+ " WHERE HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = "
					+ offerDetails.getRequisitionCode()
					+ " AND HRMS_REC_VACPUB_HDR.PUB_STATUS = 'P'";
		} // end of if
		else {
			query = "  SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
					+ "	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  "
					+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE"
					+ " where EMP_STATUS = 'S'"
					+ " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		} // end of else

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

		String[] fieldNames = { "recruiterToken", "recruiterName", "hBranch",
				"hDesg", "recruiterId" };

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

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

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
					+ "	FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS='S' ";
			if (offerDetails.getUserProfileDivision() != null
					&& offerDetails.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN ("
						+ offerDetails.getUserProfileDivision() + ")";
			query += " AND EMP_ID NOT IN(" + str + ") ";

			String[] headers = { this.getMessage("employee.id"),
					this.getMessage("employee") };

			String[] headerWidth = { "20", "80" };

			String[] fieldNames = { "employeeToken", "employeeName",
					"employeeId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (final Exception e) {
			this.logger.error(e.getMessage());
			return "";
		} // end of try-catch block
	} // end of f9Branch

	public String addKeepInformedEmpList() {
		try {
			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("keepInformedEmpId");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpName");// keep
			// informed
			// employee name

			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, offerDetails);

			model.setKeepInformed(serialNo, empCode, empName, offerDetails);

			offerDetails.setEmployeeId("");
			offerDetails.setEmployeeToken("");
			offerDetails.setEmployeeName("");

			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in addKeepInformedEmpList-----" + e);
		}
		return SUCCESS;
	}// end of addKeepInformedEmpList

	public String removeKeepInformed() {
		try {
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			OfferDetailsModel model = new OfferDetailsModel();
			model.initiate(context, session);
			model.removeKeepInformedData(serialNo, empCode, empName,
					offerDetails);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
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
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFile(String reportPath){
		System.out.println("Delete File"+reportPath);
		File file = new File(reportPath);
	if(file.exists()){
		file.delete();
	}
}
}
