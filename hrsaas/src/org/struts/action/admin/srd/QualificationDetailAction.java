package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.QualificationDetail;
import org.paradyne.model.admin.srd.ProfReferencesModel;
import org.paradyne.model.admin.srd.QualificationDetailModel;
import com.lowagie.text.Image;

public class QualificationDetailAction extends org.struts.lib.ParaActionSupport {
	private static final long serialVersionUID = 1L;
	QualificationDetail qualDetail;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(QualificationDetailAction.class);

	public Object getModel() {

		return qualDetail;
	}

	/**
	 * overridden method for prepare_local() of ParaActionSupport
	 */

	public void prepare_local() throws Exception {
		qualDetail = new QualificationDetail();
		qualDetail.setMenuCode(45);
	}

	/**
	 * @return the qualDetail
	 */
	public QualificationDetail getQualDetail() {
		return qualDetail;
	}

	/**
	 * @param qualDetail
	 *            the qualDetail to set
	 */
	public void setQualDetail(QualificationDetail qualDetail) {
		this.qualDetail = qualDetail;
	}

	/**
	 * Method to get the employee details while form loading.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			QualificationDetailModel model = new QualificationDetailModel();
			model.initiate(context, session);			
			if (qualDetail.isGeneralFlag()) {
				qualDetail.setIsNotGeneralUser("false");
				qualDetail.setEmpID(qualDetail.getUserEmpId());
			}
			else {
				qualDetail.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute("srdEmpCode");
				if(offcEmp == null || offcEmp.equals("")){
					qualDetail.setEmpID(qualDetail.getUserEmpId());					
				}else
					qualDetail.setEmpID(offcEmp);
			}
			QualiRecord();
			qualDetail.setEditFlag(false);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String input() throws Exception {
		QualificationDetailModel model = null;
		try {
			model = new QualificationDetailModel();
			model.initiate(context, session);
			qualDetail.setFlag("true");
			if (qualDetail.isGeneralFlag()) {
				qualDetail.setEmpID(qualDetail.getUserEmpId());
			} else {
				String offcEmp = (String) request.getSession().getAttribute(
						"srdEmpCode");
				if (offcEmp == null || offcEmp.equals("")) {
					qualDetail.setEmpID(qualDetail.getUserEmpId());
				}else
					qualDetail.setEmpID(offcEmp);
			}
			getProfileImage();
		} catch (Exception e) {
			logger.error(e);
		}
		return SUCCESS;
	}
	// Input function ends
	
	/** To save records
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		QualificationDetailModel model = new QualificationDetailModel();
		model.initiate(context, session);
		boolean result;
		String str = null;
		if (qualDetail.getQualificationCode().equals("")) {
			result = model.addQualDtl(qualDetail,request);
			str = getMessage("save");
		}// end of if
		else {
			result = model.modQualDtl(qualDetail,request);
			str = getMessage("update");
		}// end of else
		
		qualDetail.setEditFlag(false);
		qualDetail.setEditDetail(false);
		if (result) {
			addActionMessage(str);
			qualDetail.setQualifyCode("");
			qualDetail.setQualificationCode("");
			qualDetail.setQualCode("");
			qualDetail.setQualifyName("");
			qualDetail.setInstitute("");
			qualDetail.setUniversity("");
			qualDetail.setYear("");
			qualDetail.setGrade("");
			qualDetail.setPercentage("");
			qualDetail.setTech("");
		} else {
			addActionMessage(getMessage("save.error"));
		}
		QualiRecord();
		model.terminate();
		
		return "success";
	}
	/*** To delete records
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		QualificationDetailModel model = new QualificationDetailModel();
		model.initiate(context, session);
		boolean result = model.delQualDtl(qualDetail,request);
		if (result) {
			addActionMessage(getText("delMessage", ""));
			qualDetail.setQualificationCode("");
			qualDetail.setQualifyCode("");
			qualDetail.setQualCode("");
			qualDetail.setQualifyName("");
			qualDetail.setInstitute("");
			qualDetail.setUniversity("");
			qualDetail.setYear("");
			qualDetail.setGrade("");
			qualDetail.setPercentage("");
			qualDetail.setTech("");
		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		QualiRecord();
		qualDetail.setEditDetail(false);
		qualDetail.setEditFlag(false);
		model.terminate();
		return "success";
	}
   /**
	 * To select qualification
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9qualaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT QUA_ID,QUA_NAME FROM HRMS_QUA ORDER BY QUA_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { 
		getMessage("qualification.code"),
		getMessage("qualname") 
		};
		String[] headerWidth = { "20", "80" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "qualDetail.qualCode", "qualDetail.qualifyName" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };
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
		qualDetail.setMasterMenuCode(25);
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
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	TO_CHAR(CENTER_NAME),HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID, "
				+ " NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";

		query += getprofileQuery(qualDetail);
		query += " ORDER BY EMP_ID  ";
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
		String[] fieldNames = { "qualDetail.empToken", "employeeName",
				"qualDetail.centerName", "qualDetail.rankName",
				"empID", "employeeName" };
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
		String submitToMethod = "QualificationDetail_QualiRecord.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	/**
	 * To display records below
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String QualiRecord() throws Exception {
		QualificationDetailModel model = new QualificationDetailModel();
		model.initiate(context, session);
		String employeeId=qualDetail.getEmpID();
		model.getQualifyRecord(qualDetail);		
		getProfileImage();
		model.terminate();
		return "success";
	}

	public String editEmp() throws Exception {
		logger.info("inside edit");
		QualificationDetailModel model = new QualificationDetailModel();
		model.initiate(context, session);
		QualiRecord();
		model.terminate();	
		qualDetail.setEditDetail(true);
		qualDetail.setEditFlag(true);
		return "success";
	}
	public String edit() {
		QualificationDetailModel model = new QualificationDetailModel();
		model.initiate(context, session);
		try {
			QualiRecord();
			model.getRecord(qualDetail);
			model.terminate();	
			qualDetail.setEditFlag(true);
		qualDetail.setEditDetail(true);
			
		} catch (Exception e) {
			
		}
		
		getProfileImage();
		model.terminate();
		return SUCCESS;
		
	}
	public String cancelFunc() throws Exception {
	try {
			QualificationDetailModel model = new QualificationDetailModel();
			model.initiate(context, session);			
			QualiRecord();
			qualDetail.setEditFlag(false);
			qualDetail.setEditDetail(false);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	public void getProfileImage(){
		QualificationDetailModel model = new QualificationDetailModel();
		model.initiate(context, session);
		 model.getImage(qualDetail);
		 String photo = qualDetail.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		qualDetail.setFlag("false");
		String img = model.getServletContext().getRealPath("//")+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
		
	}

}