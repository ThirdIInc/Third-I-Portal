package org.struts.action.PAS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.paradyne.bean.PAS.ApprFormStart;
import org.paradyne.model.PAS.ApprFormStartModel;
import org.struts.lib.ParaActionSupport;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

@SuppressWarnings("deprecation")
public class ApprFormStartAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ApprFormStartAction.class);
	
	ApprFormStart apprFormStart;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

		apprFormStart = new ApprFormStart();
		apprFormStart.setMenuCode(759);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormStart;
	}
		
	public String input(){
		String forwardStatus =""+(String)request.getAttribute("forwardStatus");
		System.out.println("-------|forwardstatus|---------"+forwardStatus);
		try {
			if(forwardStatus.equals("true")){
				addActionMessage(getMessage("appraisal.form.head")+" forwarded successfully");
			}else if(forwardStatus.equals("false")){
				addActionMessage("Error while forwarding the "+getMessage("appraisal.form.head"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("######### EMP ID ##############"+apprFormStart.getUserEmpId());
		getNavigationPanel(8);
		ApprFormStartModel model = new ApprFormStartModel();
		model.initiate(context, session);
		model.getAppraisalDueList(apprFormStart);
		model.getAppraisalFinalizedList(apprFormStart);
		model.terminate();
		return SUCCESS;
	}

	public String searchAppraisal() {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		/*String query = " SELECT  DISTINCT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_COMMENTS.APPR_ID, "
				+ " TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY')  FROM PAS_APPR_COMMENTS "
				+ " INNER JOIN PAS_APPR_CALENDAR  ON (PAS_APPR_COMMENTS.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) "
				+ " WHERE APPR_EMP_ID = "+apprFormStart.getUserEmpId()
				+ " ORDER BY PAS_APPR_COMMENTS.APPR_ID ";*/

		String query = " SELECT  DISTINCT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_TRACKING.APPR_ID, " 
				+" TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY')  FROM PAS_APPR_TRACKING " 
				+" INNER JOIN PAS_APPR_CALENDAR  ON (PAS_APPR_TRACKING.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) " 
				+" WHERE EMP_ID = "+apprFormStart.getUserEmpId()
				+" ORDER BY PAS_APPR_TRACKING.APPR_ID ";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { "Appraisal Code", "Start Date", "End Date" };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "apprCode", "apprStartDate", "apprEndDate", "apprId", "apprValidTillDate" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ApprFormGeneralInfo_getApprStartDetails.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	@SuppressWarnings("deprecation")
	public void previewLetter() {
		try {
			String template = "";
			String empId = request.getParameter("empId");
			System.out.println("--=-===-=-=-=-=-"+empId);
			String promCode = request.getParameter("promCode");
			String apprCode = request.getParameter("apprCode");
			String templateCode = request.getParameter("templateCode");
			String empName = request.getParameter("empName");
			System.out.println("--=-===-=-=-=-=-"+empName);
			
			
			ApprFormStartModel model = new ApprFormStartModel();
			model.initiate(context, session);
			template = model.getTemplate(request, response,templateCode,promCode,empId,empName);
			model.terminate();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(template));

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ empName+".pdf\"");
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"\\Appraisal\\"+client+"\\Appraisal_2010-11_beforeWM_"+empId+".pdf";//append Employee ID
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
				 	
		            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"\\Appraisal\\"+client+"\\Appraisal_2010-11_beforeWM_"+empId+".pdf");
		            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
		            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
		            int i = 0;
		            Image watermark_image = Image.getInstance(getText("data_path")+"\\Appraisal\\rings.jpg");
		            watermark_image.setAbsolutePosition(100, 100);
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
	}
	
	
}
