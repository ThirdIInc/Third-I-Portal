package org.struts.action.recruitment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Recruitment.OfferApproval;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.*;
/**
 * 
 * @author Pradeep Sahoo
 * Date:12-01-2009
 *
 */
public class OfferApprovalAction extends ParaActionSupport {
	OfferApproval bean;

	public OfferApproval getBean() {
		return bean;
	}

	public void setBean(OfferApproval bean) {
		this.bean = bean;
	}
	
	public Object getModel() {
		return bean;
	}

	
	public void prepare_local() throws Exception {

		bean = new OfferApproval();
		bean.setMenuCode(811);
	}
	/**
	 * following function is called when the page is loaded
	 */
	public String input() {
		try{
		
		OfferApprovalModel model=new OfferApprovalModel();
		 model.initiate(context, session);
		
		/**
		 * call getOfferRecords(bean) method of OfferApprovalModel class
		 * to retrieve all application details as per the selected status
		 */
		bean.setApptFlag("P");
		bean.setOfferFlag("P");
		bean.setSaveFlag("true");
		bean.setStatusFlag("true"); 
		bean.setAptStatusFlag("true");
		bean.setAptSaveFlag("true");
		bean.setPending("true");
		model.getOfferRecords(bean,"'S'","P",request);
		bean.setPendingFlag("true");
		model.getAppointmentRecords(bean,"'S'","P",request);
		model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return "success";
		
	}
	
	public String aptReset() throws Exception{
		OfferApprovalModel model=new OfferApprovalModel();
			model.initiate(context, session);
			bean.setAprCandCode("");
			bean.setAptId("");
			bean.setAptDays("");
			bean.setAptDays("");
			bean.setAptReqName("");
			bean.setAptPos("");
			bean.setAptReqnCode("");
			bean.setAptResume("");
			input();
		model.terminate();
		
		return SUCCESS;
		
	}
	
	
	public String reset() throws Exception{
		OfferApprovalModel model=new OfferApprovalModel();
		model.initiate(context, session);
		bean.setReqCode("");
		bean.setReqName("");
		bean.setResume("");
		bean.setCandCode("");
		bean.setCandName("");
		bean.setOfferCode("");
		bean.setOfferStatus("");
		bean.setDays("");
		bean.setList(null);
		input();
		model.terminate();
		
		
		return "success";
}
	
	/**
	 * following function is called when any button is clicked in the form.
	 * @return
	 */
	
	public String ckeckdata(){
	
		
		String status = request.getParameter("status");		//getting status which passed as a request parameter from form
	
		
		//bean.setStatus(status);		//set application status as it is in request parameter
		
		String stat ="";
		String app="";
		//bean.setMyPage("");
		if(status.equals("P")){	//if status is P
			bean.setPending("true");
			bean.setStatusFlag("true");
			bean.setSaveFlag("true");
			bean.setAptStatusFlag("true");
			bean.setAptSaveFlag("true");
			stat = "Pending List";	//set header status as Pending List
			app="'S','I','OA','OR','C'";
			bean.setOfferFlag("P");
		
		//	bean.setOpenFlag("true");
		}	//end of if statement
		else if(status.equals("R")){	//if status is R
			bean.setRejected("true");
			bean.setStatusFlag("false");
			bean.setSaveFlag("false");
			bean.setAptStatusFlag("true");
			bean.setAptSaveFlag("true");
			stat = "Rejected List";		//set header status as Rejected List
			app="'S','I','OA','OR','C'";
			bean.setOfferFlag("R");
		//	bean.setOpenFlag("false");
			
		}else if(status.equals("A")){	//if status is A
			bean.setApproved("true");
			bean.setStatusFlag("false");
			bean.setSaveFlag("false");
			bean.setAptStatusFlag("true");
			bean.setAptSaveFlag("true");
			stat = "Approved List";		//set header status as Approved List
			app="'S','I','OA','OR','C'";
			bean.setOfferFlag("A");
		//	bean.setOpenFlag("false");
			
		}	//	//end of else if statement 
		
		//set header status as a request attribute
		request.setAttribute("stat", stat);	
		OfferApprovalModel model = new 
		OfferApprovalModel();	//creating an instance of OfferApprovalModel class
		model.initiate(context, session);				 
		
		/**
		 * call getOfferRecords(bean) method of OfferApprovalModel class
		 * to retrieve all application details as per the selected status
		 */
		bean.setPendingFlag("true");
		bean.setAptStatusFlag("true");
		model.getOfferRecords(bean,app,status,request);
		model.getAppointmentRecords(bean, "'S'","P",request);
		model.terminate();	//terminate the LoanApprovalModel class
		return "success";
	}
	
	
	
	public String ckeckAptData(){
	
		
		String status = request.getParameter("status");		//getting status which passed as a request parameter from form
	
		
		//bean.setStatus(status);		//set application status as it is in request parameter
		
		String stat ="";
		String app="";
		if(status.equals("P")){	//if status is P
		
			bean.setAptStatusFlag("true");
			bean.setAptSaveFlag("true");
			bean.setSaveFlag("true");
			bean.setStatusFlag("true");
			stat = "Pending List";	//set header status as Pending List
			app="'S','I','OA','OR','C'";
			 bean.setPendingFlag("true");
    		 bean.setApptFlag("P");
    		 
		
		
		}	//end of if statement
		else if(status.equals("R")){	//if status is R
			bean.setAptStatusFlag("false");
			bean.setAptSaveFlag("false");
			bean.setSaveFlag("true");
			bean.setStatusFlag("true");
			stat = "Rejected List";		//set header status as Rejected List
			app="'S','I','OA','OR','C'";
			bean.setRejectedFlag("true");
			bean.setApptFlag("R");
			
		}else if(status.equals("A")){	//if status is A
			bean.setAptStatusFlag("false");
			bean.setAptSaveFlag("false");
			bean.setSaveFlag("true");
			bean.setStatusFlag("true");
			stat = "Approved List";		//set header status as Approved List
			app="'S','I','OA','OR','C'";
			 bean.setApprovedFlag("true");
				bean.setApptFlag("A");
			
		}//end of else if statement 
		
		//set header status as a request attribute
		request.setAttribute("appt", stat);	
		
		OfferApprovalModel model = new 
		OfferApprovalModel();	//creating an instance of OfferApprovalModel class
		model.initiate(context, session);				 
		
		/**
		 * call getOfferRecords(bean) method of OfferApprovalModel class
		 * to retrieve all application details as per the selected status
		 */
		model.getAppointmentRecords(bean,app,status,request);
		bean.setPending("true");
		model.getOfferRecords(bean,"'S'","P",request);
		model.terminate();	//terminate the OfferApprovalModel class
		return "success";
	}
	
	/**
	 * following function is called when save button is clicked in the screening approval form.
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		OfferApprovalModel model = new OfferApprovalModel();	//creating an instance of CandidateScreeningModel class
		model.initiate(context, session);	
		
		String reqnCode[]=request.getParameterValues("reqCode");//Resume Code
		String offrId[]=request.getParameterValues("offerCode");//Comments
		String[] status=request.getParameterValues("offerStatus");//Resume Status
		String[] candidateCode= request.getParameterValues("candCode");//Resume Status
	
		
		boolean result=model.updateOffer(bean, reqnCode, offrId, status, candidateCode, request);
		
		if(result){
			addActionMessage("Record saved successfully.");
			bean.setSaveFlag("true");
			bean.setAptSaveFlag("true");
			bean.setStatusFlag("true");
			bean.setAptStatusFlag("true");
			model.getOfferRecords(bean,"'S'","P",request);
			model.getAppointmentRecords(bean,"'S'","P",request);
		}
		/**
		 * following function is called to display the records whose status is 'Open'
		 */
		//model.getCandidateRecords(bean,"O",request);
		
		model.terminate();
		return "success";
		
		
	}
	
public String aptSave() throws Exception {
		
		OfferApprovalModel model = new OfferApprovalModel();	//creating an instance of CandidateScreeningModel class
		model.initiate(context, session);	
		
		String aReqnCode[]=request.getParameterValues("aptReqnCode");//Resume Code
		String apptId[]=request.getParameterValues("aptId");//Comments
		String[] apptStatus=request.getParameterValues("aptStatus");//Resume Status
		String[] apptCandidateCode= request.getParameterValues("aprCandCode");//Resume Status
	
		
		boolean result=model.updateAppointment(bean, aReqnCode,apptId, apptStatus, apptCandidateCode, request);
		
		if(result){
			addActionMessage("Record saved successfully.");
			bean.setSaveFlag("true");
			bean.setAptSaveFlag("true");
			bean.setStatusFlag("true");
			bean.setAptStatusFlag("true");
			model.getAppointmentRecords(bean,"'S'","P",request);
			model.getOfferRecords(bean,"'S'","P",request);
		}
		
	
		
		model.terminate();
		return "success";
		
		
	}
	
	public void viewCV()throws Exception{
		OutputStream oStream = null;response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			fileName= request.getParameter("fileName");
			if(fileName==null){
				if(fileName.length()<=0){
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName+ "/resume/"+fileName;
			oStream = response.getOutputStream();
			
			response.setHeader("Content-type", "application/msword"); 
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1)
			{
				oStream.write(iChar);
			}
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		
			addActionMessage("Resume not found");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		
			if(fsStream!=null){
			fsStream.close();  
			}
			if(oStream!=null){
			oStream.flush();
			oStream.close();
			}
		}
		
		
		//return null;
	}

	/**@method previewoffer 
	 * @purpose to prieview offer letter details
	 * @return String
	 */
	public String previewoffer() {
		try {
			
			String tempCode = request.getParameter("templateCode");//tempalte code
			
			String reqsCode=request.getParameter("reqCode");//Resume Code
			String candCode= request.getParameter("candCode");//Resume Status
			
	
			System.out.println("tamplate code " + tempCode);
			System.out.println("candidate code " + candCode);
			System.out.println("reqs code " + reqsCode);

			Template template = new Template(tempCode);
			template.initiate(context, session);
			
			OfferApprovalModel model = new OfferApprovalModel();
			model.initiate(context, session);

			template.getTemplateQueries();
			try {
				TemplateQuery templateQuery1 = template.getTemplateQuery(1);
				System.out.println("offered candidate code" + candCode);
				templateQuery1.setParameter(1, candCode);
				templateQuery1.setParameter(2, reqsCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(2);
				System.out.println("offered candidate code" + candCode);
				templateQuery2.setParameter(1, candCode);
				templateQuery2.setParameter(2, reqsCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery3 = template.getTemplateQuery(3);
				System.out.println("offered candidate code" + candCode);
				templateQuery3.setParameter(1, candCode);
				templateQuery3.setParameter(2, reqsCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery4 = template.getTemplateQuery(4);
				System.out.println("offered candidate code" + candCode);
				templateQuery4.setParameter(1, candCode);
				templateQuery4.setParameter(2, reqsCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery5 = template.getTemplateQuery(5);
				System.out.println("offered candidate code" + candCode);
				templateQuery5.setParameter(1, candCode);
				templateQuery5.setParameter(2, reqsCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery6 = template.getTemplateQuery(6);
				System.out.println("offered candidate code" + candCode);
				templateQuery6.setParameter(1, candCode);
				templateQuery6.setParameter(2, reqsCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				TemplateQuery templateQuery7 = template.getTemplateQuery(7);
				System.out.println("offered candidate code" + candCode);
				String takeHomeAmt = model.getTotalAmt(candCode,reqsCode,"takeHome",true);
				templateQuery7.setParameter(1, takeHomeAmt);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery8 = template.getTemplateQuery(8);
				System.out.println("offered candidate code" + candCode);
				String ctcPerMonth = model.getTotalAmt(candCode,reqsCode,"ctcPerMonth",true);
				templateQuery8.setParameter(1, ctcPerMonth);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				TemplateQuery templateQuery9 = template.getTemplateQuery(9);
				System.out.println("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode,reqsCode,"ctcPerYear",true);
				templateQuery9.setParameter(1, ctcPerYear);
				templateQuery9.setParameter(2, String.valueOf(Math.round(Double.parseDouble(ctcPerYear)*0.7)));
				templateQuery9.setParameter(3, String.valueOf(Math.round(Double.parseDouble(ctcPerYear)*0.24)));
				templateQuery9.setParameter(4, String.valueOf(Math.round(Double.parseDouble(ctcPerYear)*0.06)));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				TemplateQuery templateQuery10 = template.getTemplateQuery(10);
				System.out.println("offered candidate code" + candCode);
				String ctcPerYear = model.getTotalAmt(candCode,reqsCode,"ctcPerYear",false);
				templateQuery10.setParameter(1, ctcPerYear);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			String comleteTemplate = template.execute(request, response,
					"OFFER_LETTER");
			System.out.println("comleteTemplate....." + comleteTemplate);
			
			model.terminate();
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
	
	public String previewappointment(){
		String tempCode = request.getParameter("templateCode");//tempalte code
		
		String reqsCode=request.getParameter("reqCode");//Resume Code
		String candCode= request.getParameter("candCode");//Resume Status
		
		System.out.println("tamplate code "+tempCode);
		System.out.println("candidate code "+candCode);
		System.out.println("reqs code "+reqsCode);
		
		Template template = new Template(tempCode);
		template.initiate(context, session);		
		template.getTemplateQueries();
		OfferApprovalModel model = new OfferApprovalModel();
		model.initiate(context, session);
		try{
		try {
			TemplateQuery templateQuery1 = template.getTemplateQuery(1);
			System.out.println("appointment candidate code" + candCode);
			System.out.println("appointment requi code" + reqsCode);
			templateQuery1.setParameter(1, reqsCode);
			templateQuery1.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery2 = template.getTemplateQuery(2);
			System.out.println("appointment candidate code" + candCode);
			System.out.println("appointment requi code" + reqsCode);
			templateQuery2.setParameter(1, reqsCode);
			templateQuery2.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery3 = template.getTemplateQuery(3);
			System.out.println("appointment candidate code" + candCode);
			System.out.println("appointment requi code" + reqsCode);
			templateQuery3.setParameter(1, reqsCode);
			templateQuery3.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery4 = template.getTemplateQuery(4);
			System.out.println("appointment candidate code" + candCode);
			System.out.println("appointment requi code" + reqsCode);
			templateQuery4.setParameter(1, reqsCode);
			templateQuery4.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery5 = template.getTemplateQuery(5);
			System.out.println("offered candidate code" + candCode);
			templateQuery5.setParameter(1, reqsCode);
			templateQuery5.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery6 = template.getTemplateQuery(6);
			System.out.println("offered candidate code" + candCode);
			templateQuery6.setParameter(1, reqsCode);
			templateQuery6.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery7 = template.getTemplateQuery(7);
			System.out.println("offered candidate code" + candCode);
			templateQuery7.setParameter(1, reqsCode);
			templateQuery7.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			TemplateQuery templateQuery8 = template.getTemplateQuery(8);
			System.out.println("offered candidate code" + candCode);
			templateQuery8.setParameter(1, reqsCode);
			templateQuery8.setParameter(2, candCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery9 = template.getTemplateQuery(9);
			System.out.println("offered candidate code" + candCode);
			String takeHomeAmt = model.getTotalAmt(candCode,reqsCode,"takeHome",true);
			templateQuery9.setParameter(1, takeHomeAmt);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery10 = template.getTemplateQuery(10);
			System.out.println("offered candidate code" + candCode);
			String ctcPerMonth = model.getTotalAmt(candCode,reqsCode,"ctcPerMonth",true);
			templateQuery10.setParameter(1, ctcPerMonth);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			TemplateQuery templateQuery11 = template.getTemplateQuery(11);
			System.out.println("offered candidate code" + candCode);
			String ctcPerYear = model.getTotalAmt(candCode,reqsCode,"ctcPerYear",true);
			templateQuery11.setParameter(1, ctcPerYear);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String comleteTemplate=template.execute(request,response,"APPOINTMENT_LETTER");
		System.out.println("comleteTemplate....."+comleteTemplate);
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

	
}
