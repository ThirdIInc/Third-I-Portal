package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.EmployeePosting;
import org.paradyne.model.admin.srd.EmployeePostingModel;
import org.struts.lib.ParaActionSupport;
import com.lowagie.text.Image;

public class EmployeePostingAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	EmployeePosting empPosting;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeePostingAction.class);

	@Override
	/**
	 * overridden method for prepare_local() of ParaActionSupport
	 */
	public void prepare_local() throws Exception {
		logger.info("prepar ============");
		empPosting = new EmployeePosting();
		empPosting.setMenuCode(945);
	}

	/**
	 * On Load
	 */

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			System.out.println("prepare login site code........"
					+ empPosting.getSiteCode());
			EmployeePostingModel model = new EmployeePostingModel();
			model.initiate(context, session);
			if (empPosting.isGeneralFlag()) {// start of if
				empPosting.setIsNotGeneralUser("false");
				empPosting.setEmpID(empPosting.getUserEmpId());
				model.getEmpPostingRecord(empPosting);

			} else {
				empPosting.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute(
						"srdEmpCode");
				if (offcEmp != null && offcEmp != "") {
					empPosting.setEmpID(offcEmp);
				} else {
					empPosting.setEmpID(empPosting.getUserEmpId());
				}
				model.getEmpPostingRecord(empPosting);
			}
			// PostingRecord();
			empPosting.setEditFlag(false);
			empPosting.setEditDetail(false);
			model.getEmpPostingRecord(empPosting);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return empPosting;
	}

	public EmployeePosting getEmpPosting() {
		return empPosting;
	}

	public void setEmpPosting(EmployeePosting empPosting) {
		this.empPosting = empPosting;
	}

	/**
	 * To save records
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() {

		try {
			EmployeePostingModel model = new EmployeePostingModel();
			model.initiate(context, session);
			boolean res;
			String str = null;
			if (empPosting.getParaId().equals("")) {
				res = model.addPostinglDtl(empPosting, request);
				str = getMessage("save");
			}// end of if
			else {
				res = model.modPostingDtl(empPosting, request);
				str = getMessage("update");
			}// end of else
			empPosting.setEditFlag(false);
			empPosting.setEditDetail(false);
			if (res) {
				addActionMessage(str);
				reset();
			} else {
				addActionMessage(getMessage("save.error"));
			}
			model.getEmpPostingRecord(empPosting);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * To set the record for edit.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		EmployeePostingModel model = new EmployeePostingModel();
		model.initiate(context, session);
		model.getRecord(empPosting);
		model.getEmpPostingRecord(empPosting);
		empPosting.setEditDetail(true);
		empPosting.setEditFlag(true);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/**
	 * To delete the record.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		EmployeePostingModel model = new EmployeePostingModel();
		model.initiate(context, session);
		boolean result = model.delPostingRecord(empPosting);
		model.getEmpPostingRecord(empPosting);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		}// end of if
		else {
			addActionMessage("site  can not be deleted");
		}// end of else
		model.getEmpPostingRecord(empPosting);
		reset();
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	public void reset() throws Exception {
		try {
			empPosting.setParaId("");
			empPosting.setEmpPostingId("");
			empPosting.setSiteCode("");
			empPosting.setLocationCode("");
			empPosting.setSiteName("");
			empPosting.setLocationName("");
			empPosting.setFrmDate("");
			empPosting.setToDate("");
			empPosting.setEmpPostingDesc("");
			empPosting.setSiteCodeItt("");
			empPosting.setEmpPostingDescItt("");
			empPosting.setLocationNameItt("");
			empPosting.setSiteCodeItt("");
			empPosting.setSiteNameItt("");
			empPosting.setFrmDateItt("");
			empPosting.setToDateItt("");
		}// end of try block
		catch (Exception e) {
			logger.error("Exception" + e);
		}// end of catch block
	}

	/**
	 * To select employee
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN,  "
				+ " NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),	"
				+ " HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";

		query += getprofileQuery(empPosting);
		query += " ORDER BY EMP_ID  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken", "empName", "empID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

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
		String submitToMethod = "EmployeePosting_PostingRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * To select employee
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9site() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT  ONSITE.ONSITE_POSTING_NAME,LOCATION_NAME ,ONSITE.ONSITE_POSTING_ID" 
                       + " FROM HRMS_ONSITE_POSTING ONSITE"
                       + " INNER JOIN HRMS_LOCATION ON(ONSITE.ONSITE_POSTING_LOCATION=HRMS_LOCATION.LOCATION_CODE)"
                       + " WHERE  ONSITE.IS_ACTIVE='Y' AND ONSITE.ONSITE_POSTING_TYPE='C'"
                       + " ORDER BY  ONSITE.ONSITE_POSTING_NAME";

		System.out.println("-----------------");

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("site"), getMessage("site.location") };

		String[] headerWidth = { "15", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "siteName", "locationName", "siteCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

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
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String addNew() {
		EmployeePostingModel model = new EmployeePostingModel();
		model.initiate(context, session);
		model.getEmpPostingRecord(empPosting);
		empPosting.setEditFlag(true);
		empPosting.setEditDetail(true);
		getProfileImage();
		model.terminate();
		return "success";
	}	

	public String editEmp() {
		EmployeePostingModel model = new EmployeePostingModel();
		model.initiate(context, session);
		try {
			model.getEmpPostingRecord(empPosting);
			empPosting.setEditFlag(true);
			empPosting.setEditDetail(true);		
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public String cancelFunc() throws Exception {
		try {
			EmployeePostingModel model = new EmployeePostingModel();
			model.initiate(context, session);
			reset();
			model.getEmpPostingRecord(empPosting);
			empPosting.setEditFlag(false);
			empPosting.setEditDetail(false);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public void getProfileImage() {
		EmployeePostingModel model = new EmployeePostingModel();
		model.initiate(context, session);
		model.getImage(empPosting);
		String photo = empPosting.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		empPosting.setFlag("false");
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

	public String PostingRecord() throws Exception {
		EmployeePostingModel model = new EmployeePostingModel();
		model.initiate(context, session);
		model.getEmpPostingRecord(empPosting);
		model.terminate();
		// empPosting.setLstFlag(true);
		empPosting.setSiteCode("");
		empPosting.setLocationName("");
		empPosting.setSiteName("");
		empPosting.setFrmDate("");
		empPosting.setFromDateCode("");
		empPosting.setToDate("");
		empPosting.setToDateCode("");
		empPosting.setEmpPostingDesc("");
		getProfileImage();
		return "success";
	}

}
