/**
 * 
 */
package org.struts.action.settlement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.paradyne.bean.settlement.SettlementLetters;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.struts.lib.ParaActionSupport;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * @author aa0554
 *
 */
public class SettlmentLettersAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SettlmentLettersAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	SettlementLetters settLetters;
	
	@Override
	public void prepare_local() throws Exception {
		settLetters = new SettlementLetters();
		settLetters.setMenuCode(1003);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return settLetters;
	}
	
	public String f9actionEmp()
	{
		String query = "  SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,RESIGN_EMP,NVL(SETTL_CODE,0),"
					+" NVL(HRMS_EXIT_HDR.RESIGN_CODE,0) AS EXIT_CODE,HRMS_RESIGN.RESIGN_CODE FROM HRMS_RESIGN "
					+" LEFT JOIN HRMS_EMP_OFFC ON(RESIGN_EMP=EMP_ID)"
					+" LEFT JOIN HRMS_SETTL_HDR ON(SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)"
					+" LEFT JOIN HRMS_EXIT_HDR ON(HRMS_RESIGN.RESIGN_CODE=RESIGN_CODE)";
		
		/*String query = "   SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,SETTL_ECODE FROM HRMS_SETTL_HDR "
			+" LEFT JOIN HRMS_EMP_OFFC ON(SETTL_ECODE=EMP_ID) WHERE SETTL_LOCKFLAG='Y' ORDER BY UPPER(NAME)";*/

		String[] headers = { getMessage("employee.id"),getMessage("employee") };

		String[] headerWidth = {"40","60"};

		String[] fieldNames = {"empToken","empName","empCode","settleCode","exitCode","resignCode"};

		int[] columnIndex = { 0,1,2,3,4,5 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String getTemplateType(){
		String tepmlateType="";
		String type=settLetters.getLetterType();
		if(type.equals("resAcc")){
			tepmlateType="10";
		}else if(type.equals("exp")){
			tepmlateType="4";
		}else if(type.equals("relieve")){
			tepmlateType="6";
		}else if(type.equals("terminate")){
			tepmlateType="8";
		}else if(type.equals("exitInter")){
			tepmlateType="14";
		}else if(type.equals("settle")){
			tepmlateType="9";
		}else if(type.equals("deptClear")){
			tepmlateType="15";
		}
		
		return tepmlateType;
	}
	public String[] getTempateFields(){
		String[] tempateFields=new String[2];
		
			tempateFields[0]=settLetters.getLetterType()+"TempName";
			tempateFields[1]=settLetters.getLetterType()+"TempCode";
		
		return tempateFields;
	}
	public String previewLetter(){
		String empCode = request.getParameter("empCode");
		String resignCode = request.getParameter("resignCode");
		String signAuthCode = request.getParameter("signAuthCode");
		//ganesh start
		String signAuthSecCode = request.getParameter("signAuthSecCode");
		System.out.println("signAuthSecCode = "+ signAuthSecCode);
		//ganesh end
		if(settLetters.getLetterType().equals("exp")){
			getExperienceLetter(empCode,signAuthCode);
		}
		else if(settLetters.getLetterType().equals("expPdf")){
			getExperienceLetterPdf(empCode,signAuthCode);
		}else if(settLetters.getLetterType().equals("relieve")){
			getRelieveLetter(empCode,signAuthCode);
		}else if(settLetters.getLetterType().equals("relievePdf")){
			getRelieveLetterPdf(empCode,signAuthCode);
		}else if(settLetters.getLetterType().equals("resAcc")){
			getAcceptanceLetter(resignCode,signAuthCode);
		}else if(settLetters.getLetterType().equals("terminate")){
			
		}else if(settLetters.getLetterType().equals("exitInter")){
			
		}
		
		return null;
	}
	public void getRelieveLetter(String empCode,String signAuthCode){
		String tempCode = request.getParameter("relieveTempCode");
		
		try{
			Template template = new Template(tempCode);
			template.initiate(context, session);		
			template.getTemplateQueries();
			
			TemplateQuery templateQuery1=template.getTemplateQuery(1);
			
			templateQuery1.setParameter(1, empCode);
			System.out.println("empCode = " + empCode);
			
			TemplateQuery templateQuery2=template.getTemplateQuery(2);
			
			templateQuery2.setParameter(1, signAuthCode);
			
			//ganesh start
			
			String signAuthSecCode = request.getParameter("signAuthSecCode");
			System.out.println("signAuthSecCode = "+ signAuthSecCode);
			
			if(!signAuthSecCode.equals("")){
			
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, signAuthSecCode);	
			}
			
			//ganesh end
						
			String completeTemplate=template.execute(request,response,"RELIEVING_LETTER");
			//logger.info("comleteTemplate....."+comleteTemplate);
			
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
						+ "RELIEVING_LETTER" + ".doc\"");
				response.setHeader("cache-control", "no-cache");
				}catch(Exception e1){e1.printStackTrace();}
			
			}	
	}
	
	//Anantha lakshmi
	
	
	public void getRelieveLetterPdf(String empCode,String signAuthCode){
		String tempCode = request.getParameter("relieveTempCode");
		String finaldata="",completeTemplate="";
		try{
			Template template = new Template(tempCode);
			template.initiate(context, session);		
			template.getTemplateQueries();
			
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, empCode);
				System.out.println("empCode = " + empCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, signAuthCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			String signAuthSecCode = request.getParameter("signAuthSecCode");
			System.out.println("signAuthSecCode = "+ signAuthSecCode);
			
			try {
				if (!signAuthSecCode.equals("")) {

					TemplateQuery templateQuery3 = template.getTemplateQuery(3);
					templateQuery3.setParameter(1, signAuthSecCode);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			completeTemplate=template.execute(request,response,"RELIEVING_LETTER",true);
			logger.info("comleteTemplate....." + completeTemplate);
			completeTemplate = completeTemplate.replaceAll("&nbsp;", "");
			completeTemplate = completeTemplate.replaceAll("& ", "&amp; ");
			completeTemplate = completeTemplate.replaceAll("&rsquo;", "'");
			completeTemplate = completeTemplate.replaceAll("&ndash;", "-");
			finaldata  = "<html>" + completeTemplate + "</html>";
		    
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(finaldata));
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
	
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""+ "RelieveLetter"+settLetters.getEmpName()+".pdf\"");
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/RelieveLetter/"+client+"/RelieveLetter_"+settLetters.getEmpName()+".pdf";//append Employee ID
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
	            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/RelieveLetter/"+client+"/RelieveLetter_"+settLetters.getEmpName()+".pdf");
	            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
	            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
	            int i = 0;
	            if(client.equals("pool_glodyne")){
		            Image watermark_image = Image.getInstance(getText("data_path")+"/RelieveLetter/rings.jpg");
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
		}catch(Exception e){
			/*	try{
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
						+ "RELIEVING_LETTER" + ".doc\"");
				response.setHeader("cache-control", "no-cache");
				}catch(Exception e1){e1.printStackTrace();}            */
			   e.printStackTrace();
			}	
	}
	
	
	
	public String getExperienceLetter(String empCode,String signAuthCode){
		String tempCode = request.getParameter("expTempCode");
		
		try{
			Template template = new Template(tempCode);
			template.initiate(context, session);		
			template.getTemplateQueries();
			
			TemplateQuery templateQuery1=template.getTemplateQuery(1);
			
			templateQuery1.setParameter(1, empCode);
			
			TemplateQuery templateQuery2=template.getTemplateQuery(2);
			
			templateQuery2.setParameter(1, signAuthCode);
			
			//ganesh start
						
			String signAuthSecCode = request.getParameter("signAuthSecCode");
			System.out.println("signAuthSecCode = "+ signAuthSecCode);
			
			//TemplateQuery templateQuery3 = template.getTemplateQuery(3);
			
			
			if(!signAuthSecCode.equals("")){
			
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, signAuthSecCode);	
			}
			
			//ganesh end
			
			String comlpeteTemplate=template.execute(request,response,"EXPERIENCE_LETTER");
			//logger.info("comleteTemplate....."+comleteTemplate);
			
			}catch(Exception e){
				try{
				e.printStackTrace();
				String finaldata = "<html>" + "" + "</html>";
				byte[] buf = finaldata.getBytes();

				response.setContentType("application/msword");
				response.getOutputStream().write(buf);
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ "EXPERIENCE_LETTER" + ".doc\"");
				response.setHeader("cache-control", "no-cache");
				}catch(Exception e1){e1.printStackTrace();}
			
			}	
			return null;
	}
	
	
	public String getExperienceLetterPdf(String empCode,String signAuthCode){
		String tempCode = request.getParameter("expTempCode");
		String finaldata="",comlpeteTemplate="";
		try{
			Template template = new Template(tempCode);
			template.initiate(context, session);		
			template.getTemplateQueries();
			
			TemplateQuery templateQuery1=template.getTemplateQuery(1);
			
			templateQuery1.setParameter(1, empCode);
			
			TemplateQuery templateQuery2=template.getTemplateQuery(2);
			
			templateQuery2.setParameter(1, signAuthCode);
			
			//ganesh start
						
			String signAuthSecCode = request.getParameter("signAuthSecCode");
			System.out.println("signAuthSecCode = "+ signAuthSecCode);
			
			//TemplateQuery templateQuery3 = template.getTemplateQuery(3);
			
			
			if(!signAuthSecCode.equals("")){
			
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, signAuthSecCode);	
			}
			
			//ganesh end
			System.out.println("-------getExperienceLetter Pdf----11---");
			 comlpeteTemplate=template.execute(request,response,"EXPERIENCE_LETTER",true);
			//logger.info("comleteTemplate....."+comleteTemplate);
			comlpeteTemplate = comlpeteTemplate.replaceAll("&nbsp;", "");
			comlpeteTemplate = comlpeteTemplate.replaceAll("& ", "&amp; ");
			comlpeteTemplate = comlpeteTemplate.replaceAll("&rsquo;", "'");
			comlpeteTemplate = comlpeteTemplate.replaceAll("&ndash;", "-");
			finaldata  = "<html>" + comlpeteTemplate + "</html>";
		    
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(finaldata));
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
	
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""+ "ExperinceLetter"+settLetters.getEmpName()+".pdf\"");
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/ExperinceLetter/"+client+"/ExperinceLetter_"+settLetters.getEmpName()+".pdf";//append Employee ID
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
	            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/ExperinceLetter/"+client+"/ExperinceLetter_"+settLetters.getEmpName()+".pdf");
	            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
	            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
	            int i = 0;
	            if(client.equals("pool_glodyne")){
		            Image watermark_image = Image.getInstance(getText("data_path")+"/ExperinceLetter/rings.jpg");
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
			}catch(Exception e){
				/*try{
				e.printStackTrace();
				String finaldata = "<html>" + "" + "</html>";
				byte[] buf = finaldata.getBytes();

				response.setContentType("application/msword");
				response.getOutputStream().write(buf);
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ "EXPERIENCE_LETTER" + ".doc\"");
				response.setHeader("cache-control", "no-cache");
				}catch(Exception e1){e1.printStackTrace();}*/
				e.printStackTrace();
			}	
			return null;
	}
	
	
	
	public void getAcceptanceLetter(String resignCode,String signAuthCode){
		String tempCode = request.getParameter("resAccTempCode");
		
		try{
			Template template = new Template(tempCode);
			template.initiate(context, session);		
			template.getTemplateQueries();
			
			TemplateQuery templateQuery1=template.getTemplateQuery(1);
			
			templateQuery1.setParameter(1, resignCode);
			
			TemplateQuery templateQuery2=template.getTemplateQuery(2);
			
			templateQuery2.setParameter(1, signAuthCode);
			
			//ganesh start
			
			String signAuthSecCode = request.getParameter("signAuthSecCode");
			System.out.println("signAuthSecCode = "+ signAuthSecCode);
			
			if(!signAuthSecCode.equals("")){
			
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, signAuthSecCode);	
			}
			
			//ganesh end
						
			String comlpeteTemplate=template.execute(request,response,"ACCEPTANCE_LETTER");
			//logger.info("comleteTemplate....."+comleteTemplate);
			
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
						+ "ACCEPTANCE_LETTER" + ".doc\"");
				response.setHeader("cache-control", "no-cache");
				}catch(Exception e1){e1.printStackTrace();}
			
			}	
	}
	
	public String f9actionTemplate()
	{
		String tepmlateType=getTemplateType();
		String query = "  SELECT  TEMPLATE_NAME,TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR"
			+" WHERE TEMPLATE_TYPE="+tepmlateType
			+" ORDER BY TEMPLATE_ID";
		
		String[] headers = { "Template Name" };

		String[] headerWidth = {"40"};

		String[] fieldNames = getTempateFields();
		for (int i = 0; i < fieldNames.length; i++) {
			System.out.println("-----fieldNames--------"+fieldNames[i]);
		}

		int[] columnIndex = { 0,1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9actionSignAuth()
	{
		String query = "  SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,EMP_ID FROM HRMS_EMP_OFFC "
			+" WHERE EMP_ID !="+settLetters.getEmpCode()+" ORDER BY UPPER(NAME)";

		
		String[] headers = { getMessage("employee.id"),getMessage("employee") };

		String[] headerWidth = {"40","60"};

		String[] fieldNames = {"signAuthToken","signAuthName","signAuthCode"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/* 
	 * f9actionSignAuthSec() for showing Second Signing Authority list
	 * date : 13 jan 2011
	 */
	
	public String f9actionSignAuthSec()
	{
		String query = "  SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,EMP_ID FROM HRMS_EMP_OFFC "
			+" WHERE EMP_ID !="+settLetters.getEmpCode()+" ORDER BY UPPER(NAME)";

		
		String[] headers = { getMessage("employee.id"),getMessage("employee") };

		String[] headerWidth = {"40","60"};

		String[] fieldNames = {"signAuthSecToken","signAuthSecName","signAuthSecCode"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
