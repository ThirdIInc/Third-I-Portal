package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.ProfReferences;
import org.paradyne.model.admin.srd.ProfReferencesModel;
import org.paradyne.model.admin.srd.QualificationDetailModel;
import org.struts.lib.ParaActionSupport;

import com.lowagie.text.Image;

public class ProfReferencesAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	ProfReferences profbean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProfReferencesAction.class);

	/** (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		profbean = new ProfReferences();
		profbean.setMenuCode(964);
	}

	/** (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return profbean;
	}

	/**
	 * @return the profbean
	 */
	public ProfReferences getProfbean() {
		return profbean;
	}

	/**
	 * @param profbean
	 *            the profbean to set
	 */
	public void setProfbean(ProfReferences profbean) {
		this.profbean = profbean;
	}

	/** (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			ProfReferencesModel model = new ProfReferencesModel();
			System.out.println("prof Employee ID------------"+profbean.getEmpId());
			model.initiate(context, session);
			if (profbean.isGeneralFlag()) {				
				profbean.setIsNotGeneralUser("false");
				profbean.setEmpId(profbean.getUserEmpId());
			}// end of if
			else {
				profbean.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute(
						"srdEmpCode");
				if (offcEmp == null || offcEmp.equals("")) {
					offcEmp = profbean.getUserEmpId();
				}
				profbean.setEmpId(offcEmp);
			}// end of else
			System.out.println("prof Employee ID------------"+profbean.getEmpId());
			model.profReferencesRecord(profbean, profbean.getEmpId());
			System.out.println("prof Employee ID------------"+profbean.getEmpId());
			profbean.setEditFlag("false");
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Method Name: save()
	 * Used to save Professional References Detail in Database
	 * @return String 
	 */
	public String save() {
		ProfReferencesModel model = new ProfReferencesModel();
		model.initiate(context, session);
		String result = "";
		System.out.println("Employee ID------------"+profbean.getEmpId());
		if (profbean.getNewFlag().equals("")) {
			logger.info("new flag is null...!!+before add method.");
			result = model.add(profbean);
		} else {
			if (profbean.getParaId().equals("")) {
				result = model.add(profbean);
			} else {
				result = model.mod(profbean);
			}
		}
		addActionMessage(getText(result));
		profbean.setEditFlag("false");
		profbean.setEditDetail("false");
		reset();
		model.profReferencesRecord(profbean, profbean.getEmpId());
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/** Method Name :f9data()
	 * Used to select Professional References Details of Employee
	 * @return String
	 */
	public String f9data() {
		ProfReferencesModel model = new ProfReferencesModel();
		model.initiate(context, session);
		System.out.println("Employee ID------------"+profbean.getEmpId());
		try {
			if (profbean.getEmpId() != null || !profbean.getEmpId().equals(""))
				model.profReferencesRecord(profbean, profbean.getEmpId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		getProfileImage();
		model.terminate();
		return SUCCESS;

	}

	/** Method Name : edit()
	 * Used to edit Records
	 * @return String
	 */
	public String edit() {
		ProfReferencesModel model = new ProfReferencesModel();
		model.initiate(context, session);
		try {
			
			model.callProfRecord(profbean);
			model.profReferencesRecord(profbean, profbean.getEmpId());
			profbean.setEditDetail("true");
			profbean.setEditFlag("true");
			profbean.setNewFlag("false");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getProfileImage();
		model.terminate();
		return SUCCESS;

	}
     public String add()
     {
    	 ProfReferencesModel model = new ProfReferencesModel();
 		model.initiate(context, session);
 		try {
 			
 			model.profReferencesRecord(profbean, profbean.getEmpId());
 			profbean.setEditDetail("true");
 			profbean.setEditFlag("true");
 			profbean.setNewFlag("true");
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		getProfileImage();
 		model.terminate();
 		return SUCCESS;
     }
	/** Method Name : delete()
	 * Used to delete Records
	 * @return String 
	 */
	public String delete() {
		ProfReferencesModel model = new ProfReferencesModel();
		model.initiate(context, session);
		boolean result = model.deleteProfRecord(profbean);
		model.profReferencesRecord(profbean, profbean.getEmpId());
		if (result) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
		}
		reset();
		profbean.setEditDetail("false");
		profbean.setEditDetail("false");
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/** Method Name : f9action()
	 * Used to select Employee From List
	 * @return String 
	 * @throws Exception
	 */
	public String f9action() throws Exception {

		String query = "SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),NVL(CENTER_NAME,' '),HRMS_RANK.RANK_NAME,EMP_ID, "
				+ " NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";
		query += getprofileQuery(profbean);
		query += " ORDER BY EMP_ID  ";
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };
		String[] headerWidth = { "20", "30", "30", "30" };
		String[] fieldNames = { "employeeToken", "empName", "empbranch",
				"empdesignation", "empId", "empName" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
		String submitFlag = "true";
		String submitToMethod = "ProfReferences_f9data.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**Method Name : reset()
	 * Used to reset Fields
	 */
	public void reset() {
		profbean.setAddress1("");
		profbean.setAddress2("");
		profbean.setAddress3("");
		profbean.setPhoneNo("");
		profbean.setMobileNo("");
		profbean.setProfFname("");
		profbean.setProfMname("");
		profbean.setProfLname("");
		profbean.setEmailId("");
		profbean.setParaId("");
		profbean.setOccupation("");
		profbean.setExtension("");
		profbean.setOtherInfo("");
		profbean.setCompanyName("");
		profbean.setCountryName("");
		profbean.setCityName("");
		profbean.setStateName("");
		profbean.setPinCode("");
		profbean.setFaxNo("");
		
	}

	/**Method Name : f9cityaction()
	 * Used to select City From List
	 * @return String
	 * @throws Exception
	 */
	public String f9cityaction() throws Exception {
		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE=2 ORDER BY LOCATION_CODE ";
		String[] headers = { "City Code", "City Name" };
		String[] headerWidth = { "20", "20" };
		String[] fieldNames = { "cityCode", "cityName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**Method Name : getProfileImage()
	 * Used to show ProfileImage
	 */
	public void getProfileImage() {
		ProfReferencesModel model = new ProfReferencesModel();
		model.initiate(context, session);
		model.getImage(profbean);
		String photo = profbean.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		profbean.setFlag("false");
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

	/** Method Name : cancel()
	 * Used to go back 
	 * @return String
	 */
	public String cancel() {
		ProfReferencesModel model = new ProfReferencesModel();
		model.initiate(context, session);
		String empID = profbean.getEmpId();
		model.profReferencesRecord(profbean, empID);
		reset();
		getProfileImage();
		profbean.setEditDetail("false");
		profbean.setEditFlag("false");
		model.terminate();
		return "success";
	}

}
