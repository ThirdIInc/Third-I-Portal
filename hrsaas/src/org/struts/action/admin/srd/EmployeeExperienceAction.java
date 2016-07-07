package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.EmployeeExperience;
import org.paradyne.model.admin.srd.EmployeeExperienceModel;
import org.struts.lib.ParaActionSupport;
import com.lowagie.text.Image;

/**
 * @author Prajakta.Bhandare
 * @date 23 Jan 2013
 */
public class EmployeeExperienceAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	EmployeeExperience empExp;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeExperienceAction.class);

	/**
	 * @return empExp
	 */
	public EmployeeExperience getEmpExp() {
		return empExp;
	}

	/**
	 * @param empExp
	 * the empExp to set
	 */
	public void setEmpExp(EmployeeExperience empExp) {
		this.empExp = empExp;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return empExp;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		empExp = new EmployeeExperience();
		empExp.setMenuCode(114);
	}

	
	
	
	
	public String input() throws Exception {
		//updated by Priyanka Kumbhar
		EmployeeExperienceModel model = new EmployeeExperienceModel();
		model.initiate(context, session);
		String strEmp ="";
		try {
			
			strEmp = (String) request.getSession().getAttribute(
					"srdEmpCode");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(strEmp!=null && !strEmp.equals("")){
			empExp.setEmpId(strEmp);
		}
		model.getExpRecord(empExp);
		empExp.setEditFlag(false);
		getProfileImage();
		return SUCCESS;
	}
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			if (empExp.isGeneralFlag()) {//if general user
				empExp.setIsNotGeneralUser("false");
				empExp.setEmpId(empExp.getUserEmpId());
			} //end if general user
			else {
				empExp.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute(
						"srdEmpCode");
				if (offcEmp == null || offcEmp.equals("")) {//if emp id null
					offcEmp = empExp.getUserEmpId();
				}//end if emp id null
				empExp.setEmpId(offcEmp);
			}//end of else
			model.getExpRecord(empExp);
			empExp.setEditFlag(false);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**  Method to add Record
	 * @return String
	 * @throws Exception
	 */
	public String add() throws Exception {
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			model.getExpRecord(empExp);
			//model.getProjectRecords(empExp, request);
			empExp.setEditFlag(true);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			String str = "";
			if (empExp.getParaExpId().equals("")
					|| empExp.getParaExpId() == null) {//if exp id null
				str = model.addExp(empExp);
			}//end if exp id null
			else {
				str = model.updateExp(empExp);
			}//end of else
			reset();
			model.getExpRecord(empExp);
			model.terminate();
			addActionMessage(getText(str));
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		empExp.setEditFlag(false);
		getProfileImage();
		return SUCCESS;
	}

	public String saveProject() throws Exception {
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			String str = "";
			if (empExp.getParaProjId().equals("")
					|| empExp.getParaProjId() == null) {//if project code null
				str = model.addProject(empExp);
			} //end if project code null 
			else {
				str = model.updateProject(empExp);
			}//end of else
			model.getProjectRecords(empExp, request);
			empExp.setParaProjId("");
			model.terminate();
			addActionMessage(getText(str));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		empExp.setEditFlag(false);
		getProfileImage();
		return "projectDtl";
	}

	/**
	 * To clear all the form fields
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			empExp.setEmployer("");
			empExp.setEmpExpList(null);
			empExp.setEmpSal("");
			empExp.setCtc("");
			empExp.setDesignation("");
			empExp.setExpId("");
			empExp.setJoiningDate("");
			empExp.setJobDesc("");
			empExp.setNoData("");
			empExp.setNoProjData("");
			empExp.setParaExpId("");
			empExp.setParaProjId("");
			empExp.setRelDate("");
			empExp.setSrNo("");
			empExp.setTeamSize("");
			empExp.setProjCode("");
			empExp.setProjectCode("");
			empExp.setProjectDesc("");
			empExp.setProjectDuration("");
			empExp.setProjectExpId("");
			empExp.setProjectItt("");
			empExp.setProjectList(null);
			empExp.setProjectName("");
			empExp.setProjectRole("");
			empExp.setProjExpId("");
			empExp.setProjName("");
			empExp.setProjectNameItt("");
			empExp.setProjectRoleItt("");
			empExp.setProjectDurationItt("");
			empExp.setProjectDescItt("");
			empExp.setTeamSizeItt("");
			empExp.setProjNameList(null);
		} catch (Exception e) {
			logger.error("Exception" + e);
		}
		return "success";
	}
	
	/** Method to display employee's work experience record
	 * @return String
	 * @throws Exception
	 */
	public String expRecord() throws Exception {
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			model.getExpRecord(empExp);
			model.terminate();
			getProfileImage();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * To set the record for edit.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		EmployeeExperienceModel model = new EmployeeExperienceModel();
		model.initiate(context, session);
		model.getOneExpRecord(empExp);
		model.getExpRecord(empExp);
		model.terminate();
		empExp.setEditFlag(true);
		getProfileImage();
		return SUCCESS;
	}
	/**
	 *  To delete the project record in list
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception { 
		EmployeeExperienceModel model = new EmployeeExperienceModel();
		model.initiate(context, session);
		boolean result = model.delExpRecord(empExp);
		reset();
		model.getExpRecord(empExp);
		model.terminate();
		if(result){//if result true
			addActionMessage(getText("delMessage", ""));	
		}//end if result true
		else{
			addActionMessage("Record cannot be deleted");
		}//end of else
		empExp.setEditFlag(false);
		getProfileImage();
		return SUCCESS;
	}

	/**Method to delete the project record.
	 * @return String
	 * @throws Exception
	 */
	public String deleteProject() throws Exception {
		EmployeeExperienceModel model = new EmployeeExperienceModel();
		model.initiate(context, session);
		boolean result = model.delProjRecord(empExp);
		if (result) {//if result
			addActionMessage(getText("delMessage", ""));
		}// end of if result
		else {
			addActionMessage("project record can not be deleted");
		}// end of else
		model.getProjectRecords(empExp, request);
		empExp.setParaProjId("");
		model.terminate();
		empExp.setEditFlag(false);
		getProfileImage();
		return "projectDtl";
	}
	
	/** Method to edit Project record
	 * @return String
	 * @throws Exception
	 */
	public String editProject() throws Exception{
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			model.getOneProjRecord(empExp);
			model.getProjectRecords(empExp, request);
			model.terminate();
			getProfileImage();
			empExp.setEditFlag(true);
			return "projectDtl";
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** Method to add project record
	 * @return String
	 * @throws Exception
	 */
	public String addProject() throws Exception{
		
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			empExp.setEditFlag(true);
			model.getProjectRecords(empExp, request);
			model.terminate();
			getProfileImage();
			return "projectDtl";
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** To select employee
	 * @return String
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,EMP_ID, "
				+ " NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')	FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)";
		query += getprofileQuery(empExp);
		query += " ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "15", "30", "27", "28" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "employeeToken", "empName", "empCent",
				"empRank", "empId", "empName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "EmployeeExperience_expRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	

	/** Method to cancel current record
	 * @return String
	 * @throws Exception
	 */
	public String cancelFunc() throws Exception {
		try {
			EmployeeExperienceModel model = new EmployeeExperienceModel();
			model.initiate(context, session);
			reset();
			model.getExpRecord(empExp);
			empExp.setEditFlag(false);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**Method to set profile name and profile image
	 * 
	 */
	public void getProfileImage() {
		EmployeeExperienceModel model = new EmployeeExperienceModel();
		model.initiate(context, session);
		model.getImage(empExp);
		String photo = empExp.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		empExp.setFlag("false");
		String img = model.getServletContext().getRealPath("//")
				+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
	}
}
