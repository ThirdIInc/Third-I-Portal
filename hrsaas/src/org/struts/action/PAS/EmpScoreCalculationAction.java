package org.struts.action.PAS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.paradyne.bean.ApplicationStudio.LetterTemplate;
import org.paradyne.bean.PAS.EmpScoreCalculation;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.PAS.EmpScoreCalculationModel;
import org.struts.lib.ParaActionSupport;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;


@SuppressWarnings("deprecation")
public class EmpScoreCalculationAction extends ParaActionSupport {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 8291568618895735060L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmpScoreCalculationAction.class);
	EmpScoreCalculation ctcCalBean = null;
	LetterTemplate ltemplate = null;

	public void prepare_local() throws Exception {
		ctcCalBean = new EmpScoreCalculation();
		ctcCalBean.setMenuCode(2054);
	}

	public Object getModel() {
		return ctcCalBean;
	}

	public String input() {
		ctcCalBean.setListType("");
		return INPUT;
	}

	public String getEmployeeListByAppraisalID() {
		try {
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
			ctcCalBean.setListType("pending");
			model
					.getPendingList(ctcCalBean, request, ctcCalBean
							.isSearchFlag());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ctcCalBean.setListType("pending");
		return INPUT;
	}
	/*public void saveSingleEmployeeScore(HttpServletRequest request,String empCode){
		EmpScoreCalculationModel model = new EmpScoreCalculationModel();
		String[] empId = request.getParameterValues("employeeIdItt");
		String[] percentIncrement = request
				.getParameterValues("percentIncrementItt");
		String[] criticalIncrement = request
				.getParameterValues("criticalIncrementItt");
		String[] moderatedCtc = request.getParameterValues("modCtcItt");
		String[] newCtc = request.getParameterValues("newCtcItt");
		String[] oldCtc = request.getParameterValues("oldCtcItt");
		String[] templateCode = request.getParameterValues("templateCode");
		model.initiate(context, session);
		if (empId != null && empId.length > 0) {
			for (int i = 0; i < empId.length; i++) {
				if(empCode.equals(empId[i])){
					model.updateModerateCtc(ctcCalBean, new String[]{empId[i]},
							new String[]{criticalIncrement[i]}, new String[]{moderatedCtc[i]}, new String[]{percentIncrement[i]},
							new String[]{newCtc[i]},new String[]{oldCtc[i]}, new String[]{templateCode[i]});
				}
			}
			
		}
		model.terminate();
	}*/
	public String saveModerateScore() {
		boolean result = false;
		try {

			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			//synchronized(context){
			String[] empId = request.getParameterValues("employeeIdItt");
			String[] percentIncrement = request
					.getParameterValues("percentIncrementItt");
			String[] criticalIncrement = request
					.getParameterValues("criticalIncrementItt");
			String[] moderatedCtc = request.getParameterValues("modCtcItt");
			String[] newCtc = request.getParameterValues("newCtcItt");
			String[] oldCtc = request.getParameterValues("oldCtcItt");
			String[] templateCode = request.getParameterValues("templateCode");
			String[] isPromoted = request.getParameterValues("promotionFlag");
			String[] formulaCode = request.getParameterValues("formulaCode");
			String[] revisedGrade = request.getParameterValues("empNewGradeIdItt");
			String[] finalScore = request.getParameterValues("employeeScoreItt");
			
			model.initiate(context, session);
			if (empId != null && empId.length > 0) {
				result = model.updateModerateCtc(ctcCalBean, empId,
						criticalIncrement, moderatedCtc, percentIncrement,
						newCtc, oldCtc, templateCode,isPromoted,formulaCode,revisedGrade,finalScore);
			}
			//}
			if (result) {
				addActionMessage("Record saved successfully");
			} else {
				addActionMessage("Record can not be saved");
			}
			ctcCalBean.setListType("pending");
			model
					.getPendingList(ctcCalBean, request, ctcCalBean
							.isSearchFlag());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	public String saveModerateScoreOnProcess() {
		boolean result = false;
		try {
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			//synchronized(context){
			
			String[] empId = request.getParameterValues("employeeIdItt");
			String[] percentIncrement = request
					.getParameterValues("percentIncrementItt");
			String[] criticalIncrement = request
					.getParameterValues("criticalIncrementItt");
			String[] moderatedCtc = request.getParameterValues("modCtcItt");
			String[] newCtc = request.getParameterValues("newCtcItt");
			String[] oldCtc = request.getParameterValues("oldCtcItt");
			String[] templateCode = request.getParameterValues("templateCode");
			String[] isPromoted = request.getParameterValues("promotionFlag");
			String[] formulaCode = request.getParameterValues("formulaCode");
			String[] revisedGrade = request.getParameterValues("empNewGradeIdItt");
			String[] finalScore = request.getParameterValues("employeeScoreItt");
			
			model.initiate(context, session);
			if (empId != null && empId.length > 0) {
				result = model.updateModerateCtc(ctcCalBean, empId,
						criticalIncrement, moderatedCtc, percentIncrement,
						newCtc, oldCtc, templateCode,isPromoted,formulaCode,revisedGrade,finalScore);
			}
			/*if (result) {
				addActionMessage("Record saved successfully");
			} else {
				addActionMessage("Record can not be saved");
			}*/
			//}
			ctcCalBean.setListType("pending");
			model
					.getPendingList(ctcCalBean, request, ctcCalBean
							.isSearchFlag());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	public String f9emp() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_ID,EMP_FNAME||' '||EMP_LNAME FROM PAS_APPR_MGMNT_PANEL_REVIEW "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID)"
					+" WHERE APPR_ID ="+ctcCalBean.getAppraisalId()
					+ " ORDER BY EMP_ID";

			String[] headers = { getMessage("empId"), getMessage("empName") };
			String[] headerWidth = { "30", "50" };
			String[] fieldNames = { "empId", "empName" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String f9appraisal() {
		try {
			String query = "SELECT APPR_CAL_CODE, APPR_ID,TO_CHAR(APPR_PROMOTION_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_PROM_EFFECTIVE_DATE,'DD-MM-YYYY') FROM PAS_APPR_CALENDAR ORDER BY APPR_ID";

			String[] headers = { getMessage("appraisal.code") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "appraisalCode", "appraisalId",
					"promotionDate", "promoEffDate" };

			int[] columnIndex = { 0, 1, 2, 3 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String f9groupHead() {
		try {
			String query = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME||' '||  NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) GROUP_HEAD, HRMS_EMP_OFFC.EMP_ID "
					+ " FROM PAS_APPR_APPRAISER_GRP_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER_GRP_HDR.APPR_GROUP_HEAD_ID) "
					+ " WHERE APPR_ID =" + ctcCalBean.getAppraisalId();

			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "20", "70" };
			String[] fieldNames = { "groupHeadToken", "groupHeadName",
					"groupHeadId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String f9manager() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME||' '|| NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) MANAGER , APPR_APPRAISER_CODE "
					+ " FROM PAS_APPR_APPRAISER "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE) "
					+ " INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID) "
					+ " WHERE APPR_ID = "
					+ ctcCalBean.getAppraisalId()+" AND APPR_GROUP_HEAD_ID="+ctcCalBean.getGroupHeadId();
					//+ " AND APPR_PHASE_ID=" + ctcCalBean.getPhaseCode();

			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "20", "70" };
			String[] fieldNames = { "managerToken", "managerName", "managerId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/*
	 * public String getPhaseDetails() { EmpScoreCalculationModel model = new
	 * EmpScoreCalculationModel(); model.initiate(context, session);
	 * LinkedHashMap<String, String> phaseMap =
	 * model.setPhaseDetails(ctcCalBean); ctcCalBean.setPhaseMap(phaseMap);
	 * return INPUT; }
	 */
	public String f9department() {
		try {
			String query = " SELECT NVL(DEPT_NAME,' '), DEPT_ID  FROM HRMS_DEPT ORDER BY DEPT_ID";
					//+ " WHERE IS_ACTIVE = 'Y'";

			String[] headers = { getMessage("department") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "departmentName", "departmentId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String f9branch() {
		try {
			String query = " SELECT NVL(CENTER_NAME,' '), CENTER_ID FROM HRMS_CENTER "
					+ " WHERE IS_ACTIVE = 'Y'";

			String[] headers = { getMessage("branch") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "branchName", "branchId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String f9formula() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT FORMULA_NAME,FORMULA_ID FROM HRMS_FORMULABUILDER_HDR WHERE FORMULA_ISACTIVE='Y'"
				+ " ORDER BY FORMULA_ID ";
		String[] headers = { getMessage("formula") };

		String[] headerWidth = { "100" };

		String id = request.getParameter("fieldName");
		//logger.info("id>>>>" + id);
		String[] fieldNames = { "formulaName" + id, "formulaCode" + id };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9empGrade() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CADRE_NAME,CADRE_ID FROM HRMS_CADRE WHERE CADRE_IS_ACTIVE='Y'"
				+ " ORDER BY CADRE_ID ASC ";
		String[] headers = { getMessage("emp.PropGrade") };

		String[] headerWidth = { "100" };

		String id = request.getParameter("fieldName");
		//logger.info("id>>>>" + id);
		String[] fieldNames = { "empNewGradeItt" + id, "empNewGradeIdItt" + id };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9template() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		
		
		String query = " SELECT TEMPLATE_NAME,TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_TYPE= 13"
				+ " ORDER BY TEMPLATE_ID DESC ";
		String[] headers = { getMessage("template.name") };

		String[] headerWidth = { "100" };

		String id = request.getParameter("fieldName");
		//logger.info("id>>>>" + id);
		String[] fieldNames = { "templateName" + id, "templateCode" + id };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}


	public String genReport() {
		try {
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
			model.genReport(ctcCalBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String caliculateCtc() {
		try {
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
			model.getIncrSlab();
			ctcCalBean.setListType("pending");
			model.calculateCtc(ctcCalBean, request, ctcCalBean.isSearchFlag());

			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String processPromotion() {
		try {
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
		//	logger.info("inside process");
			//synchronized(context) {
			String empCode = request.getParameter("empCode");
			String formulaCode = request.getParameter("formulaCode");
			String currentCTC = request.getParameter("currentCTC");
			String empRating = request.getParameter("empRating");
			String finalCTC = request.getParameter("finalCTC");
			String promoFlag = request.getParameter("promoFlag");
			String newGrade = request.getParameter("newGrade");
			
			  /*logger.info("empCode=="+empCode);
			  logger.info("formulaCode=="+formulaCode);
			  logger.info("currentCTC=="+currentCTC);
			  logger.info("empRating=="+empRating);
			  logger.info("finalCTC=="+finalCTC);
			  logger.info("prmodate=="+ctcCalBean.getPromotionDate());
			  logger.info("prmodate=="+ctcCalBean.getPromoEffDate());*/
			 
			// formulaCode="2";
			String[] paramArray = new String[9];
			paramArray[0] = empCode;
			paramArray[1] = formulaCode;
			paramArray[2] = currentCTC;
			paramArray[3] = empRating;
			paramArray[4] = finalCTC;
			paramArray[5] = ctcCalBean.getPromotionDate();
			paramArray[6] = ctcCalBean.getPromoEffDate();
			paramArray[7] = promoFlag;
			paramArray[8] = newGrade;
			String result=model.processPromotion(paramArray, ctcCalBean);
			if(result.equals("sameEff")){
				addActionMessage("Duplicate promotion record found.");
			}else if(result.equals("locked")){
				addActionMessage("Promotion is already locked.");
			} else if(result.equals("saved")){
				addActionMessage("Record saved successfully.");
			} else if(result.equals("notSaved")){
				addActionMessage("Record can not be saved.");
			} 
		//	}
			ctcCalBean.setListType("pending");
			model
					.getPendingList(ctcCalBean, request, ctcCalBean
							.isSearchFlag());
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveModerateScoreOnProcess();
	}
	public String reProcessPromotion(){
		try {
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
			//logger.info("inside process");
			//synchronized(context) {
			String empCode = request.getParameter("empCode");
			String formulaCode = request.getParameter("formulaCode");
			String currentCTC = request.getParameter("currentCTC");
			String empRating = request.getParameter("empRating");
			String finalCTC = request.getParameter("finalCTC");
			String promoFlag = request.getParameter("promoFlag");
			String newGrade = request.getParameter("newGrade");
			String promCode = request.getParameter("promCode");
			
			 /* logger.info("empCode=="+empCode);
			  logger.info("formulaCode=="+formulaCode);
			  logger.info("currentCTC=="+currentCTC);
			  logger.info("empRating=="+empRating);
			  logger.info("finalCTC=="+finalCTC);
			  logger.info("prmodate=="+ctcCalBean.getPromotionDate());
			  logger.info("prmodate=="+ctcCalBean.getPromoEffDate());*/
			 
			// formulaCode="2";
			String[] paramArray = new String[10];
			paramArray[0] = empCode;
			paramArray[1] = formulaCode;
			paramArray[2] = currentCTC;
			paramArray[3] = empRating;
			paramArray[4] = finalCTC;
			paramArray[5] = ctcCalBean.getPromotionDate();
			paramArray[6] = ctcCalBean.getPromoEffDate();
			paramArray[7] = promoFlag;
			paramArray[8] = newGrade;
			paramArray[9] = promCode;
			String result=model.reProcessPromotion(paramArray, ctcCalBean);
			/*if(result.equals("saved")){
				addActionMessage("Record Processed successfully.");
			}*/
			if(result.equals("sameEff")){
				addActionMessage("Duplicate promotion record found.");
			}else if(result.equals("locked")){
				addActionMessage("Promotion is already locked.");
			} else if(result.equals("saved")){
				addActionMessage("Record saved successfully.");
			} else if(result.equals("notSaved")){
				addActionMessage("Record can not be saved.");
			} 
		//	}
			ctcCalBean.setListType("pending");
			model
					.getPendingList(ctcCalBean, request, ctcCalBean
							.isSearchFlag());
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveModerateScoreOnProcess();
	}

	public String sendMail() {
		try {
			String tempCode = "";
			String promCode = "";
			String empMailId = "";
			String empId = "";
			String empToken = "";
			String empName = "";
			synchronized(context) {
			tempCode = request.getParameter("templateId");
			promCode = request.getParameter("promCode");
			empMailId = request.getParameter("empMailId");
			empId = request.getParameter("empCode");
			empToken = request.getParameter("empToken");
			empName = request.getParameter("empName");
			logger.info("tempCode=="+tempCode);
			logger.info("promCode=="+promCode);
			String template = "";
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
			template = model.getTemplate(request, response, tempCode, promCode, empId, empName);
			model.terminate();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(template));

			ITextRenderer renderer = new ITextRenderer();
			
			renderer.setDocument(doc, null);
			PdfWriter writer = renderer.getWriter();
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+empToken+".pdf";//append Employee ID
			File letter=new File(outputFile);
			if(!letter.exists()){
				File l = new File(letter.getParent());
				boolean dir = l.mkdirs();
			}
			OutputStream os = new FileOutputStream(letter);
			renderer.layout();
			renderer.createPDF(os);
			
			 try {
		            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"\\Appraisal\\"+client+"\\Appraisal_2010-11_beforeWM_"+empToken+".pdf");
		            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
		            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, new FileOutputStream(getText("data_path")+"\\Appraisal\\"+client+"\\Appraisal_2010-11_"+empToken+".pdf"));
		            int i = 0;
		            Image watermark_image = Image.getInstance(getText("data_path")+"\\Appraisal\\rings.jpg");
		            watermark_image.setAbsolutePosition(80, 300);
		            PdfContentByte add_watermark;            
		            while (i < number_of_pages) {
		              i++;
		              add_watermark = stamp.getUnderContent(i);
		              add_watermark.addImage(watermark_image);
		            }
		            stamp.close();
		        }
		        catch (Exception i1) {
		            i1.printStackTrace();
		        }
			final MailUtility mailModel = new MailUtility();
			mailModel.initiate(context, session);
			String[] attachPath = new String[1];
			attachPath[0]=getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_"+empToken+".pdf";
			
			EmailTemplateBody emailTemplate = new EmailTemplateBody();
			emailTemplate.initiate(context, session);
			emailTemplate.setEmailTemplate("Promotion Letter Mail To Employee");
			emailTemplate.getTemplateQueries();

			try {
				EmailTemplateQuery templateQuery1 = emailTemplate
						.getTemplateQuery(1); // FROM EMAIL
				
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery2 = emailTemplate
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, empId);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery3 = emailTemplate
						.getTemplateQuery(3);
				// templateQuery3.setParameter(1, applnDate);
				templateQuery3.setParameter(1, promCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
			emailTemplate.configMailAlert();
			String bccArray[]=null;
			try {
				String bccmailId = getMessage("bcc.mailId");
				bccArray=bccmailId.split(";");
			} catch (Exception e) {
				// TODO: handle exception
			}
			emailTemplate.sendMailToCCEmailIdsWithAttachment(new String []{empMailId},attachPath,false,null,null,null,null,bccArray);
			
				/*mailModel.sendMail(new String[] {empMailId}, new String[] {"peoplepower.sysmail1@glodyne.com" },
						"Appraisal_2010-11", "Appraisal_2010-11",
						attachPath, empId, false);*/
				boolean result=model.updateMailStatus(ctcCalBean,empId);
				if(result)
				addActionMessage("Mail sent successfully");
				ctcCalBean.setListType("pending");
				model
						.getPendingList(ctcCalBean, request, ctcCalBean
								.isSearchFlag());
			} catch (Exception e) {
				
			}
			mailModel.terminate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return INPUT;
	}

	public String previewLetter() {
		try {
			String tempCode = "";
			String promCode = "";
			String template = "";
			String empToken="";
			
			try {
				tempCode = request.getParameter("templateId");
				promCode = request.getParameter("promCode");
				empToken = request.getParameter("empToken");
				logger.info("tempCode  : "+tempCode);
				logger.info("promCode  : "+promCode);
				logger.info("empToken  : "+empToken);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
			template = model.getTemplate(request, response,tempCode,promCode, empToken, empToken);
			model.terminate();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(template));

			
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ "Appraisal_2010-11_"+empToken+".pdf\"");
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+empToken+".pdf";//append Employee ID
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
				 	
		            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+empToken+".pdf");
		            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
		            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
		            int i = 0;
		            Image watermark_image = Image.getInstance(getText("data_path")+"/Appraisal/rings.jpg");
		            watermark_image.setAbsolutePosition(80, 300);
		            PdfContentByte add_watermark;            
		            while (i < number_of_pages) {
		              i++;
		              add_watermark = stamp.getUnderContent(i);
		              add_watermark.addImage(watermark_image);
		            }
		            stamp.close();
		        }
		        catch (Exception i1) {
		            i1.printStackTrace();
		        }
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String publishFinalizedAppraisals(){
		boolean result = false;
		try {
			EmpScoreCalculationModel model = new EmpScoreCalculationModel();
			model.initiate(context, session);
			result = model.publishAppraisals(ctcCalBean);
			if(result){
				addActionMessage("Appraisals published successfully");
			}else{
				addActionMessage("Appraisals could not be published");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getEmployeeListByAppraisalID();
	}
}
