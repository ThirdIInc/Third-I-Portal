package org.struts.action.probation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.paradyne.bean.probation.ProbationLetters;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.struts.lib.ParaActionSupport;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.probation.ProbationConfirmationModel;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class ProbationLettersAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ProbationLettersAction.class);

	ProbationLetters probLetters;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		probLetters=new ProbationLetters();
		probLetters.setMenuCode(1005);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return probLetters;
	}

	public ProbationLetters getProbLetters() {
		return probLetters;
	}

	public void setProbLetters(ProbationLetters probLetters) {
		this.probLetters = probLetters;
	}

	/**
	 * METHOD TO SELECT CONFIRMATION LETTER TEMPLATE
	 * @return
	 * @throws Exception
	 */
	public String f9actionConfirmation() throws Exception {
		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_LETTERTEMPLATE_HDR"
			+" WHERE TEMPLATE_TYPE=TO_CHAR((SELECT LETTERTYPE_ID FROM HRMS_LETTERTYPE WHERE LETTERTYPE LIKE 'Confirmation Letter')) "
			+" ORDER BY TEMPLATE_ID";
		String[] headers = { "Template Code", "Template Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "confTempCode", "confTempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method
	/**
	 * METHOD TO SELECT EXTENSION LETTER TEMPLATE
	 * @return
	 * @throws Exception
	 */
	public String f9actionExtension() throws Exception {
		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_LETTERTEMPLATE_HDR"
						+" WHERE TEMPLATE_TYPE=0 "
						+" ORDER BY TEMPLATE_ID";

		String[] headers = { "Template Code", "Template Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "extTempCode", "extTempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method
	/**
	 * METHOD TO SELECT TERMINATION LETTER TEMPLATE
	 * @return
	 * @throws Exception
	 */
	public String f9actionTermination() throws Exception {
		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_LETTERTEMPLATE_HDR"
						+" WHERE TEMPLATE_TYPE=0 "
						+" ORDER BY TEMPLATE_ID";

		String[] headers = { "Template Code", "Template Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "termTempCode", "termTempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method
	
	/**
	 * METHOD TO SELECT SIGNING AUTHORITY
	 * @throws Exception
	 */
	
	public String f9signAutho() throws Exception {
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_ID " 
						+"	FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ORDER BY EMP_ID";

		String[] headers = { "Employee Token", "Employee Name"};
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "signAuthToken", "signAuthName" ,"signAuthCode"};
		int[] columnIndex = { 0, 1,2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method
	/**
	 * METHOD TO SELECT EMPLOYEE TO GENERATE LETTER
	 * @return
	 * @throws Exception
	 */
	public String f9actionEmp() throws Exception {
		String query = " SELECT distinct EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_ID  ,PROBATION_CODE "
						+" 	FROM HRMS_PROBATION_CONFIRM "
						+"	INNER JOIN HRMS_EMP_OFFC  ON( HRMS_PROBATION_CONFIRM.PROBATION_EMPID=HRMS_EMP_OFFC .EMP_ID) "
						+"	WHERE EMP_STATUS='S' ORDER BY EMP_ID";

		String[] headers = { "Employee Token", "Employee Name"};
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empToken", "empName" ,"empCode","probationCode"};
		int[] columnIndex = { 0, 1,2,3 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method
	
	

	/**
	 * METHOD TO GENERATE CONFIRMATION LETTER
	 */
	public String generateConfirmationLetter() {
		try {
			
			String tempCode=probLetters.getConfTempCode();
			logger.info("");
			Template template = new Template(tempCode);
			template.initiate(context, session);

			template.getTemplateQueries();
			
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, probLetters.getProbationCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, probLetters.getSignAuthCode());
				System.out.println("probationConfirm.getAuthoCode()  ::  "
						+ probLetters.getSignAuthCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			String comleteTemplate = template.execute(request, response,
					"Confirmation Letter");
			logger.info("comleteTemplate....." + comleteTemplate);
		} catch (Exception e) {
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
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return null;
	}
	public String generateConfirmationLetterPdf()
	{
		System.out.println("========generateConfirmationLetter    Pdf===========");
		String comleteTemplate = "";
		String finaldata = "";
		try {
			
			String tempCode=probLetters.getConfTempCode();
			logger.info("");
			Template template = new Template(tempCode);
			template.initiate(context, session);

			template.getTemplateQueries();
			
			
			
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, probLetters.getProbationCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, probLetters.getSignAuthCode());
				System.out.println("probationConfirm.getAuthoCode()  ::  "
						+ probLetters.getSignAuthCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			
		    comleteTemplate = template.execute(request, response,"SETTLEMENT_LETTER",true);
			logger.info("comleteTemplate....." + comleteTemplate);
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
			response.setHeader("Content-Disposition", "attachment; filename=\""+"ProbationLetter"+probLetters.getEmpName()+".pdf\"");
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/ProbationLetter/"+client+"/ProbationLetter_"+probLetters.getEmpName()+".pdf";//append Employee ID
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
	            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/ProbationLetter/"+client+"/ProbationLetter_"+probLetters.getEmpName()+".pdf");
	            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
	            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
	            int i = 0;
	            if(client.equals("pool_glodyne")){
		            Image watermark_image = Image.getInstance(getText("data_path")+"/ProbationLetter/rings.jpg");
		            watermark_image.setAbsolutePosition(80, 300);
		            PdfContentByte add_watermark;            
		            while (i < number_of_pages) {
		              i++;
		              add_watermark = stamp.getUnderContent(i);
		              add_watermark.addImage(watermark_image);
		            }
	            }
	            stamp.close();
		 }catch (Exception i1) {
            i1.printStackTrace();
		 }
			
			logger.info("comleteTemplate....." + comleteTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	public String sendmailConfTemp() {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String 	probationCode=request.getParameter("probationCode");
		try {
			String inFileName1 = getText("data_path") + "/datafiles/"
					+ poolName + "/ConfirmaionLetter/ConfirmaionLetter_"
					+ probLetters.getEmpName() + ".doc";
			String tempCode = probLetters.getConfTempCode();
			Template template = new Template(tempCode);
			template.initiate(context, session);
			template.getTemplateQueries();
			
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, probationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, probLetters.getSignAuthCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			String comleteTemplate = template.executeWriteFile(request,response, "CONFIRMATION_LETTER");
			logger.info("comleteTemplate....." + comleteTemplate);

			byte[] buf = comleteTemplate.getBytes();
			// PrintWriter output = null;
			String inFileName = getText("data_path") + "/datafiles/"
					+ poolName + "/ConfirmaionLetter/ConfirmaionLetter_"
					+ probLetters.getEmpName() + ".doc";
			File file1 = new File(getText("data_path") + "/datafiles/"
					+ poolName + "/ConfirmaionLetter");
			if (!file1.exists()) {
				file1.mkdirs();
			}
			OutputStream out = new FileOutputStream(inFileName);
			out.write(buf);
			out.close();

						
			//Pdf Email 
			
			EmailTemplateBody mailtemplatePDF = new EmailTemplateBody();
			mailtemplatePDF.initiate(context, session);
			mailtemplatePDF.setEmailTemplate("PROBATION - HR TO CANDIDATE");
			mailtemplatePDF.getTemplateQueries();

			try {
				EmailTemplateQuery etemplateQuery1 = mailtemplatePDF
						.getTemplateQuery(1); // FROM
				etemplateQuery1.setParameter(1, probLetters.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery etemplateQuery2 = mailtemplatePDF
						.getTemplateQuery(2); // TO
				etemplateQuery2.setParameter(1, probLetters.getEmpCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery etemplateQuery3 = mailtemplatePDF
						.getTemplateQuery(3);
				etemplateQuery3.setParameter(1, probLetters.getEmpCode());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery etemplateQuery4 = mailtemplatePDF
						.getTemplateQuery(4);
				etemplateQuery4.setParameter(1, probLetters.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			String attachPathPDF[] = new String[1];
			attachPathPDF[0] = getText("data_path") + "/ProbationLetter/" + poolName+ "/ProbationLetter_"+ probLetters.getEmpName() + ".pdf";
			mailtemplatePDF.configMailAlert();
			try {
				mailtemplatePDF.sendApplMailWithAttachment(attachPathPDF);
				addActionMessage("Mail has been sent successfully to the candidate");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return INPUT;
	}

}
