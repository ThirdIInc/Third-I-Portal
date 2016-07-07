package org.struts.action.admin.srd;

import org.paradyne.EmailTest;
import org.paradyne.bean.admin.srd.WarningLetter;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.admin.srd.OffSecActsModel;
import org.paradyne.model.admin.srd.WarningLetterModel;
import org.struts.lib.ParaActionSupport;

import com.sun.java_cup.internal.emit;

public class WarningLetterAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.action.admin.srd.WarningLetterAction.class);
	WarningLetter warningLetter;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		warningLetter = new WarningLetter();
		warningLetter.setMenuCode(913);

	}

	public WarningLetter getWarningLetter() {
		return warningLetter;
	}

	public void setWarningLetter(WarningLetter warningLetter) {
		this.warningLetter = warningLetter;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return warningLetter;
	}

	public String f9type() throws Exception {

		String query = " SELECT   EMP_TOKEN,(TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),EMP_ID  FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE EMP_STATUS='S' order by emp_id";

		String[] headers = { "Token No.", getMessage("employee")};

		String[] headerWidth = { "40", "60" };

		String[] fieldNames = { "empToken", "empName", "empId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	//SELECT  TEMPLATE_ID,TEMPLATE_NAME , DECODE(TEMPLATE_TYPE,'O','Offer Template','P','Appointment Template','A','Acceptance Template','E','Experience Template' ) FROM HRMS_LETTERTEMPLATE_HDR ORDER BY TEMPLATE_ID
	public String f9Template() throws Exception {

		String query = " SELECT TEMPLATE_NAME , TEMPLATE_ID  FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_NAME  like 'WARNING LETTER'";

		String[] headers = { getMessage("template")};

		String[] headerWidth = {"100" };

		String[] fieldNames = { "templateName", "templateCode"};

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String report() throws Exception {

		
			//model.generateReport(warningLetter, response);
			//model.generateTemplateReport(warningLetter, response);
			try {
				String tempCode = warningLetter.getTemplateCode();//template code
				String empCode = warningLetter.getEmpId();//emp code

				logger.info("template code " + tempCode);
				logger.info("emp  Code  " + empCode);

				Template template = new Template(tempCode);
				template.initiate(context, session);

				template.getTemplateQueries();
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, empCode);
				
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
			

				String comleteTemplate = template.execute(request, response,
						"WARNING_LETTER");
				logger.info("comleteTemplate....." + comleteTemplate);
			} catch (Exception e) {
				try {
					String type = "Txt";
					String title = "Warning letter";
					e.printStackTrace();
					String finaldata = "<html>" + "" + "</html>";
					org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
							type, title);

					byte[] buf = finaldata.getBytes();

					response.setContentType("application/msword");
					response.getOutputStream().write(buf);
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + "WARNING_LETTER" + ".doc\"");
					response.setHeader("cache-control", "no-cache");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			return null;
			
			
	
	}

	public String testReport() throws Exception {
		/*WarningLetterModel model =new WarningLetterModel();
		model.initiate(context, session);
		model.testReport(warningLetter,response,request);
		model.terminate();*/
		
		EmailTest text=new EmailTest();
		text.textReport(response);
		return null;
	}
	
	
	
	
	public String reset() throws Exception {
		warningLetter.setEmpId("");
		warningLetter.setEmpName("");
		warningLetter.setEmpToken("");
		warningLetter.setTemplateCode("");
		warningLetter.setTemplateName("");
		return SUCCESS;

	}

}
