/**
 * 
 */
package org.struts.action.promotion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.paradyne.bean.promotion.PromotionMaster;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.PAS.AppraisalMisReportModel;
import org.paradyne.model.Promotion.PromotionReportModel;
import org.struts.lib.ParaActionSupport;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author AA0418
 *
 */
public class PromotionReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.action.promotion.PromotionReportAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	PromotionMaster pramotion;
	@Override
	public void prepare_local() throws Exception {
		pramotion = new PromotionMaster();
		pramotion.setMenuCode(959);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return pramotion;
	}

	public PromotionMaster getPramotion() {
		return pramotion;
	}

	public void setPramotion(PromotionMaster pramotion) {
		this.pramotion = pramotion;
	}
	
	public String getURL(){
		PromotionReportModel model = new PromotionReportModel();
		model.initiate(context, session);
		System.out.println("pramotion.isGeneralFlag() : "+pramotion.isGeneralFlag());
		
		/*if (pramotion.isGeneralFlag()) {
			try{
				getReport();
			}catch(Exception e){}
			
		}else{*/
			model.generateUrlList(request, response, pramotion);
		//}
		model.terminate();
		return SUCCESS;
	}
	
	/*public void prepare_withLoginProfileDetails() throws Exception {
		PromotionReportModel model = new PromotionReportModel();
		model.initiate(context,session);	
		if (pramotion.isGeneralFlag()) {
			model.getEmployeeDetails(pramotion.getUserEmpId(),pramotion);
		}
		model.terminate();
	
    }*/
	
	public void getReport()
	{
		String tempCode = request.getParameter("tempCode");
		String promCode=request.getParameter("promCode");
		//SELECT PROM_CODE ,PROM_TEMPLATE  FROM HRMS_PROMOTION 
		//WHERE ROWNUM>=1 AND ROWNUM<=10
		
		PromotionReportModel model = new PromotionReportModel();
		model.initiate(context, session);
		
		
		try{
			// String comlpeteTemplate .. for loop
			
			
			String sqlQuery="SELECT PROM_CODE ,PROM_TEMPLATE FROM HRMS_PROMOTION WHERE 1=1 AND PROM_TEMPLATE IS NOT NULL "+model.getFilter(pramotion);
			Object [][] resultObj = model.getSqlModel().getSingleResult(sqlQuery);
			
			String comlpeteTemplate="";
			
			if(resultObj!=null && resultObj.length>0)
			{
				comlpeteTemplate=getTemplate(String.valueOf(resultObj[0][1]), String.valueOf(resultObj[0][0]), pramotion.getLetterType());
			}
			
			
				
				//loop r
				
				if(pramotion.getLetterType().equals("doc")){
				}else{
				
					DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(comlpeteTemplate));

			
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ "Appraisal_2010-11_"+pramotion.getEmpToken()+".pdf\"");
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+pramotion.getEmpToken()+".pdf";//append Employee ID
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
				 	
		            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+pramotion.getEmpToken()+".pdf");
		            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
		            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
		            int i = 0;
		            if(client.equals("pool_glodyne")){
		            Image watermark_image = Image.getInstance(getText("data_path")+"/Appraisal/rings.jpg");
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
			}
			}catch(Exception e){
			e.printStackTrace();
			}
			model.terminate();
	}
	
	public String f9emp() {

		String repToQue = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME,TO_CHAR(EFFECT_DATE,'DD-MM-YYYY'),EMP_CODE," 
				+ " PROM_CODE, NVL(ADD_EMAIL,''),HRMS_PROMOTION.PROM_TEMPLATE,nvl(TEMPLATE_NAME,'')  FROM HRMS_PROMOTION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PROMOTION.EMP_CODE)"
				+	"inner join HRMS_LETTERTEMPLATE_HDR on (HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID=HRMS_PROMOTION.PROM_TEMPLATE)"
				+" 	LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')";
				if(pramotion.isGeneralFlag())
				{
					repToQue+="where EMP_CODE = "+pramotion.getUserEmpId();
				}
				//+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				repToQue+= " ORDER BY EFFECT_DATE DESC, UPPER(NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {  getMessage("employee.id"),getMessage("employee"),getMessage("promotion.eff.date")};

		String[] headerWidth = { "20", "60","20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken", "empName","effDate","empCode","promCode","empMailId","tempCode","tempName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2,3,4,5,6,7 };

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

		setF9Window(repToQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public void report(){
		PromotionReportModel model = new PromotionReportModel();
		model.initiate(context, session);
		model.report(pramotion, response);
		model.terminate();
		
	}
	
	
	 public String getTemplate(String tempCode,String promCode,String letterType){

			Template template = new Template(tempCode);
			template.initiate(context, session);		
			template.getTemplateQueries();
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, promCode);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				TemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				TemplateQuery templateQuery7 = template.getTemplateQuery(7);
				templateQuery7.setParameter(1, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TemplateQuery templateQuery8 = template.getTemplateQuery(8);
				templateQuery8.setParameter(8, promCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String comlpeteTemplate="";
			if(letterType.equals("doc")){
				 comlpeteTemplate=template.execute(request,response,"Appraisal_2010-11_"+pramotion.getEmpToken(),true);
				logger.info("comleteTemplate....."+comlpeteTemplate);
			}else{
				 comlpeteTemplate = template.execute(request, response,
						"APPRAISAL_2010-11",true);
				logger.info("comleteTemplate....."+comlpeteTemplate);
				 try {
						comlpeteTemplate=comlpeteTemplate.replaceAll("&nbsp;", " ");
						comlpeteTemplate=comlpeteTemplate.replaceAll("& ", "&amp; ");
						comlpeteTemplate=comlpeteTemplate.replaceAll("&rsquo;", "'");
						
						//comlpeteTemplate = "<html>" + comlpeteTemplate + "</html>";
					
					} catch (Exception e) {
						e.printStackTrace();
					}
			}

			
			
		 return comlpeteTemplate;
	 }
	
	
	
	public String getPromotionLetter(){
		
		PromotionReportModel model = new PromotionReportModel();
		model.initiate(context, session);
		try{
			// String comlpeteTemplate .. for loop
			String comlpeteTemplate="";
			
			String rangeFromValue=request.getParameter("rangefromValue");
			String rangeToValue=request.getParameter("rangetoValue");
			System.out.println("rangeFromValue : "+rangeFromValue);
			System.out.println("rangeToValue : "+rangeToValue);
			Object promTemplCode[][]=model.getTemplateCode(rangeFromValue, rangeToValue,pramotion);
			
			int j=Integer.parseInt(rangeFromValue)-1;
			if(promTemplCode!=null && promTemplCode.length>0)
			{
				for(;j<Integer.parseInt(rangeToValue);j++)
				{
					System.out.println("rangeToValue...."+j+" ....  "+String.valueOf(promTemplCode[j][0])+"  .... "+String.valueOf(promTemplCode[j][1]));
					comlpeteTemplate+=getTemplate(String.valueOf(promTemplCode[j][1]), String.valueOf(promTemplCode[j][0]), pramotion.getLetterType());
					//comlpeteTemplate+="<div style='PAGE-BREAK-BEFORE: always'><span style='DISPLAY: none'>&nbsp;</span></div>";
				}
			}
			
				//comlpeteTemplate+=getTemplate(tempCode, promCode, pramotion.getLetterType());
				
				//loop r
			
			System.out.println("comlpeteTemplate::::::"+comlpeteTemplate);
				
				if(pramotion.getLetterType().equals("doc")){

					String finaldata = "<html>" + comlpeteTemplate + "</html>";
				
					byte[] buf = finaldata.getBytes();

					response.setContentType("application/msword");
					response.setHeader("Content-Disposition", "attachment; filename=\""
							+ "Report"+Math.random()+ ".doc\"");
					response.setHeader("cache-control", "no-cache");
					response.getOutputStream().write(buf);
					
				}else{
				
					String client="";
			try {
				comlpeteTemplate = "<html>" + comlpeteTemplate + "</html>";
				DocumentBuilder builder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				Document doc = builder.parse(new StringBufferInputStream(
						comlpeteTemplate));
				ITextRenderer renderer = new ITextRenderer();
				renderer.setDocument(doc, null);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + "Letter_"
								+ Math.random() + ".pdf\"");
				client = String.valueOf(session.getAttribute("session_pool"));
				String outputFile = getText("data_path") + "/Appraisal/"
						+ client + "/Appraisal_2010-11_beforeWM_"
						+ pramotion.getEmpToken() + ".pdf";//append Employee ID
				File letter = new File(outputFile);
				if (!letter.exists()) {
					File l = new File(letter.getParent());
					boolean dir = l.mkdirs();
				}
				OutputStream os = new FileOutputStream(letter);
				renderer.layout();
				renderer.createPDF(os);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//response.setHeader("cache-control", "no-cache");
			 try {
				 	
		            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+pramotion.getEmpToken()+".pdf");
		            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
		            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, response.getOutputStream());
		            int i = 0;
		            if(client.equals("pool_glodyne")){
		            Image watermark_image = Image.getInstance(getText("data_path")+"/Appraisal/rings.jpg");
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
			}
			}catch(Exception e){
			e.printStackTrace();
			}
			model.terminate();
			return null;
	}
	public String reset(){
		pramotion.setEmpCode("");
		pramotion.setEmpToken("");
		pramotion.setEmpName("");
		pramotion.setPromCode("");
		pramotion.setStrength("");
		pramotion.setWeakness("");
		pramotion.setPromDate("");
		pramotion.setEffDate("");
		pramotion.setSignAuthCode("");
		pramotion.setSignAuthName("");
		pramotion.setSignAuthToken("");
		pramotion.setTempCode("");
		pramotion.setLetterType("pdf");
		pramotion.setEmpMailId("");
		pramotion.setTempName("");
		pramotion.setTempName("");
		//UPDATED BY REEBA BEGINS
		pramotion.setSecSignAuthCode("");
		pramotion.setSecSignAuthName("");
		pramotion.setSecSignAuthToken("");
		//UPDATED BY REEBA ENDS
		return SUCCESS;
	}
	
	public String f9actionTemplate()
	{
		String query = "  SELECT TEMPLATE_NAME,TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR"
			+" WHERE TEMPLATE_TYPE=13 ORDER BY TEMPLATE_ID DESC";
		
		String[] headers = { getMessage("templateName") };

		String[] headerWidth = {"40"};

		String[] fieldNames = {"tempName","tempCode"};

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
			+" WHERE EMP_ID !="+pramotion.getEmpCode()+" ORDER BY UPPER(NAME)";

		
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
	
	//UPDATED BY REEBA BEGINS
	public String f9SecSignAuth()
	{
		String query = "  SELECT DISTINCT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,EMP_TOKEN,EMP_ID FROM HRMS_EMP_OFFC "
			+" WHERE EMP_ID !="+pramotion.getEmpCode()+" ORDER BY EMP_ID";

		
		String[] headers = { getMessage("employee"), getMessage("employee.id") };

		String[] headerWidth = {"70","30"};

		String[] fieldNames = {"secSignAuthName","secSignAuthToken","secSignAuthCode"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String sendMail() {
		try {
			String tempCode = "";
			String promCode = "";
			String empMailId = "";
			String empId = "";
			tempCode = request.getParameter("tempCode");
			promCode=request.getParameter("promCode");
			empMailId = request.getParameter("empMailId");
			empId = request.getParameter("empCode");
			logger.info("tempCode=="+tempCode);
			logger.info("promCode=="+promCode);
			String template = "";
			template = getTemplate(tempCode, promCode, "pdf");
			template="<html>" + template + "</html>";
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new StringBufferInputStream(template));

			ITextRenderer renderer = new ITextRenderer();
			
			renderer.setDocument(doc, null);
			PdfWriter writer = renderer.getWriter();
			String client=String.valueOf(session.getAttribute("session_pool"));
			String outputFile = getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+pramotion.getEmpToken()+".pdf";//append Employee ID
			File letter=new File(outputFile);
			if(!letter.exists()){
				File l = new File(letter.getParent());
				boolean dir = l.mkdirs();
			}
			OutputStream os = new FileOutputStream(letter);
			renderer.layout();
			renderer.createPDF(os);
			
			 try {
		            PdfReader Read_PDF_To_Watermark = new PdfReader(getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_beforeWM_"+pramotion.getEmpToken()+".pdf");
		            int number_of_pages = Read_PDF_To_Watermark.getNumberOfPages();
		            PdfStamper stamp = new PdfStamper(Read_PDF_To_Watermark, new FileOutputStream(getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_"+pramotion.getEmpToken()+".pdf"));
		            int i = 0;
		            if(client.equals("pool_glodyne")){
		            Image watermark_image = Image.getInstance(getText("data_path")+"/Appraisal/rings.jpg");
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
			final MailUtility mailModel = new MailUtility();
			mailModel.initiate(context, session);
			String[] attachPath = new String[1];
			attachPath[0]=getText("data_path")+"/Appraisal/"+client+"/Appraisal_2010-11_"+pramotion.getEmpToken()+".pdf";
			EmailTemplateBody emailTemplate = new EmailTemplateBody();
			emailTemplate.initiate(context, session);
			//emailTemplate.setEmailTemplate("APPRAISAL EMAIL TEMPLATE");
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
				//templateQuery3.setParameter(1, promCode);
				templateQuery3.setParameter(1, empId);
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
				
				addActionMessage("Mail sent successfully");
				
			} catch (Exception e) {
				
			}
			mailModel.terminate();
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return INPUT;
	}
	
public String f9Division()throws Exception{
		
		try {
			String query = " SELECT HRMS_DIVISION.DIV_ID,nvl(HRMS_DIVISION.DIV_NAME,' ')	 FROM HRMS_DIVISION";
			String header = getMessage("promotion.division");
			String textAreaId = "paraFrm_div";
			String hiddenFieldId = "paraFrm_divCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}


public String f9Branch()throws Exception{
	
	try {
		String query = " SELECT HRMS_CENTER.CENTER_ID,nvl(HRMS_CENTER.CENTER_NAME,' ')	FROM HRMS_CENTER";		
		String header = getMessage("promotion.branch");
		String textAreaId = "paraFrm_branch";
		String hiddenFieldId = "paraFrm_branchCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9multiSelect";
}

public String f9Dept()throws Exception{
	
	try {
		String query = " SELECT HRMS_DEPT.DEPT_ID,nvl(HRMS_DEPT.DEPT_NAME,'')	FROM HRMS_DEPT";
		
		String header = getMessage("promotion.dept");
		String textAreaId = "paraFrm_dept";
		String hiddenFieldId = "paraFrm_deptCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9multiSelect";
}

public String f9Designation()throws Exception{
	
	try {
		String query = " SELECT  RANK_ID, NVL(RANK_NAME,' ')  FROM HRMS_RANK ORDER BY RANK_ID";
		
		String header = getMessage("promotion.desg");
		String textAreaId = "paraFrm_desg";
		String hiddenFieldId = "paraFrm_desgCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9multiSelect";
}
}
