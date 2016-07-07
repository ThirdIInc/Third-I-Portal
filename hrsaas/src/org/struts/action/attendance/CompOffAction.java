package org.struts.action.attendance;

import org.paradyne.bean.attendance.CompOff;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.attendance.CompOffModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author balajim date 06-08-2008
 */
public class CompOffAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CompOffAction.class);
	CompOff comp = null;

	/**
	 * @return String after adding the record in the iterator
	 */
	public String add() {

		Object hprojName[] = request.getParameterValues("hprojName");
		Object hDate[] = request.getParameterValues("hDate");
		Object hsTime[] = request.getParameterValues("hsTime");
		Object heTime[] = request.getParameterValues("heTime");
		
		CompOffModel model = new CompOffModel();
		model.initiate(context, session);

		boolean dupresult = false;
		dupresult = model.isduplicate(comp);
		if(!dupresult) {
			addActionMessage("Compoff already Applied for this Date.");
			model.checked(comp, hprojName, hDate, hsTime, heTime);
			model.terminate();
			return "success";

		}
		if(comp.getCheckEdit().equals("")) {
			model.add(comp, hprojName, hDate, hsTime, heTime);
		} else {
			model.edit(comp, hprojName, hDate, hsTime, heTime);
		}
		comp.setIteratorFlag("true");
		comp.setPrName("");
		comp.setPrDate("");
		comp.setStartTime("");
		comp.setEndTime("");
		comp.setCmonth("-1");
		comp.setCyear("");
		comp.setHidMon("");
		comp.setCheckEdit("");

		model.terminate();
		return "success";
	}

	public String callDtl() {
		String cmpId = comp.getCompId();
		CompOffModel model = new CompOffModel();
		model.initiate(context, session);
		model.callDtl(comp, cmpId);

		comp.setPrName("");
		comp.setPrDate("");
		comp.setStartTime("");
		comp.setEndTime("");
		model.terminate();
		return "success";
	}

	/**
	 * @return String after deleting the record
	 */
	public String delete() {

		CompOffModel model = new CompOffModel();
		model.initiate(context, session);
		boolean ckFlag = model.checkForward(comp);
		if(ckFlag) {
			boolean result = model.delete(comp);
			if(result) {
				addActionMessage(getMessage("delete"));
				//------------------------Process Manager Alert------------------------start
				ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				
				String applnID = comp.getCompId();
				String module = "Compensatory Off";
				processAlerts.removeProcessAlert(applnID, module);
				//------------------------Process Manager Alert------------------------end
			} else {
				addActionMessage(getMessage("del.error"));
			}
		} else {
			addActionMessage("You Can not Delete Approved or Rejected  Application !");
		}
		comp.setEmpId("");
		comp.setEmpName("");
		comp.setEmpToken("");
		comp.setBranchName("");
		comp.setDept("");
		comp.setDesg("");
		comp.setAppDate("");
		comp.setStatus("");
		comp.setPrName("");
		comp.setPrDate("");
		comp.setStartTime("");
		comp.setEndTime("");
		comp.setCmonth("-1");
		comp.setCyear("");
		comp.setHidMon("");
		comp.setCompId("");
		comp.setComments("");
		model.terminate();
		return "success";
	}

	/**
	 * @return String after deleting the record in the iterator
	 */
	/*
	 * method for deleting a row in the table
	 */
	public String delItem() {
		String serialNo = request.getParameter("serialNo");
		Object hprojName[] = request.getParameterValues("hprojName");
		Object hDate[] = request.getParameterValues("hDate");
		Object hsTime[] = request.getParameterValues("hsTime");
		Object heTime[] = request.getParameterValues("heTime");
		
		/*
		 * Object hmonth[] = request.getParameterValues("hmonth"); Object hyear[] = request.getParameterValues("hyear"); Object
		 * hidMon[] = request.getParameterValues("hidMon");
		 */
		CompOffModel model = new CompOffModel();
		model.initiate(context, session);
		model.delItem(comp, hprojName, hDate, hsTime, heTime, serialNo);
		comp.setCheckEdit("");
		model.terminate();
		return "success";
	}

	public String f9action() {
		try {
			String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME  ENAME, TO_CHAR(COMP_APPDATE,'DD-MM-YYYY') COMP_APPDATE, " + " DECODE(COMP_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected') STATUS, NVL(CENTER_NAME, ' ') CENTER_NAME, NVL(DEPT_NAME, ' ') DEPT_NAME, " + " NVL(RANK_NAME, ' ') RANK_NAME, EMP_ID, COMP_ID " + " FROM HRMS_COMPOFF_HDR " + " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_COMPOFF_HDR .COMP_EMPID) " + " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER) " + " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK) " + " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT) ";
			if(comp.isGeneralFlag()) {
				query += " WHERE EMP_ID = " + comp.getUserEmpId();
			}
			query += " ORDER BY TO_DATE(COMP_APPDATE, 'DD-MM-YYYY') DESC, UPPER(ENAME) ";

			String[] headers = {getMessage("employee.id"), getMessage("employee"), getMessage("app.dateate"), getMessage("status")};

			String[] headerWidth = {"20", "40", "20", "20"};

			String[] fieldNames = {"comp.empToken", "comp.empName", "comp.appDate", "status", "comp.branchName", "comp.dept", "comp.desg", "comp.empId", "comp.compId"};

			int[] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8};

			String submitFlag = "true";

			String submitToMethod = "CompOff_callDtl.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			return null;
		} // end of try catch block
	}

	public String f9Employee() {
		try {
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID  FROM HRMS_EMP_OFFC " + "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) " + "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) " + "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) " + "	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) " + "	WHERE EMP_STATUS ='S'  ORDER BY EMP_ID ";

			String[] headers = {getMessage("employee.id"), getMessage("employee")};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"comp.empToken", "comp.empName", "comp.branchName", "comp.dept", "comp.desg", "comp.empId"};

			int[] columnIndex = {0, 1, 2, 3, 4, 5};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			return null;
		}
	}

	public CompOff getComp() {
		return comp;
	}

	public Object getModel() {
		return comp;
	}

	public void prepare_local() throws Exception {
		comp = new CompOff();
		comp.setMenuCode(405);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		CompOffModel model = new CompOffModel();
		model.initiate(context, session);
		if(comp.isGeneralFlag()) {
			model.getEmployeeDetails(comp.getUserEmpId(), comp);
		}
		model.terminate();
	}

	public String report() {
		/*
		 * Generate the report for particular application
		 */
		CompOffModel model = new CompOffModel();
		model.initiate(context, session);
		model.getReport(request, response, comp);
		model.terminate();
		return null;
	}

	/**
	 * @return String after resetting all the fields in the form
	 */
	public String reset() {
		if(!comp.isGeneralFlag()) {
			comp.setEmpId("");
			comp.setEmpName("");
			comp.setEmpToken("");
			comp.setBranchName("");
			comp.setDept("");
			comp.setDesg("");
			comp.setComments("");
		}
		comp.setComments("");
		comp.setAppDate("");
		comp.setStatus("");
		comp.setPrName("");
		comp.setPrDate("");
		comp.setStartTime("");
		comp.setEndTime("");
		comp.setCmonth("-1");
		comp.setCyear("");
		comp.setHidMon("");
		comp.setCompId("");
		return "success";
	}

	/*
	 * method for saving the application and modifying application based on reporting structure
	 */
	/**
	 * @return String
	 */
	public String save() {
		Object hprojName[] = request.getParameterValues("hprojName");
		Object hDate[] = request.getParameterValues("hDate");
		Object hsTime[] = request.getParameterValues("hsTime");
		Object heTime[] = request.getParameterValues("heTime");
		
		CompOffModel model = new CompOffModel();
		model.initiate(context, session);
		Object[][] empFlow = generateEmpFlow(comp.getEmpId(), "Leave", 1);

		if(empFlow == null) { // checking employee reporting structure is there r not...!
			addActionMessage("Reporting structure not defined for the employee\n" + comp.getEmpName());
			addActionMessage("Please contact HR manager !");
		} else {
			if(comp.getCompId().equals("")) {
				boolean result = model.save(comp, hprojName, hDate, hsTime, heTime, empFlow);
				if(result) {
					addActionMessage(getMessage("save"));
					
					//------------------------Process Manager Alert------------------------start
					try {
						String applnID = comp.getCompId();
						String module = "Compensatory Off";
						String applicant = comp.getEmpId();
						String approver = String.valueOf(empFlow[0][0]);
						sendApplicationAlert(applnID, module, applicant, approver);
					} catch(Exception e) {
						logger.error(e);
					}
					//------------------------Process Manager Alert------------------------end
					
					reset();
				} else {
					addActionMessage(getMessage("save.error"));
				}
			} else {
				boolean ckFlag = model.checkForward(comp);
				if(ckFlag) {
					boolean result = model.update(comp, hprojName, hDate, hsTime, heTime, empFlow);
					if(result) {
						addActionMessage(getMessage("update"));
						reset();
					} else {
						addActionMessage(getMessage("update.error"));
					}
				} else {
					addActionMessage("Forwarded Application can't updated!");
					reset();
				}
				comp.setIteratorFlag("false");
			}
		}
		model.terminate();
		return "success";
	}
	
	public void sendApplicationAlert(String applnID, String module, String applicant, String approver) {
		try {
			String msgType = "A";
			String level = "1";
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			
			template.setEmailTemplate("COMP OFF APPL-APPLICANT TO APPROVER");
								
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			templateQuery1.setParameter(1, applicant);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, approver);
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);
			
			template.configMailAlert();
			template.sendProcessManagerAlert(approver, module, msgType, applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch(Exception e) {
			logger.error(e);
		}
	}

	public void setComp(CompOff comp) {
		this.comp = comp;
	}
}