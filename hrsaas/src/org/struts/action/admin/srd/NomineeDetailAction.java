package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.NomineeDetail;
import org.paradyne.model.admin.srd.NomineeDetailModel;

import com.lowagie.text.Image;

/**
 * @author radha
 * 
 */
public class NomineeDetailAction extends org.struts.lib.ParaActionSupport {
	private static final long serialVersionUID = 1L;
	NomineeDetail nomDetail;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(NomineeDetailAction.class);

	/**
	 * @return the nomDetail
	 */
	public NomineeDetail getNomDetail() {
		return nomDetail;
	}

	/**
	 * @param nomDetail
	 *            the nomDetail to set
	 */
	public void setNomDetail(NomineeDetail nomDetail) {
		this.nomDetail = nomDetail;
	}

	public Object getModel() {

		return nomDetail;
	}

	public void prepare_local() throws Exception {
		nomDetail = new NomineeDetail();
		nomDetail.setMenuCode(47);
	}

	/**
	 * To save records
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			System.out.println("inside action");
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			String str = null;
			String query = " SELECT NOM_TYPE,NOM_NOMINEE FROM HRMS_EMP_NOMINEE "
				+ " WHERE NOM_TYPE='"
				+ nomDetail.getNomiType()
				+ "' AND NOM_NOMINEE="
				+ nomDetail.getMemberCode()
				+ " AND EMP_ID=" + nomDetail.getEmpID();
			if (nomDetail.getParacode().equals("")) {
				Object data[][] = model.getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					addActionMessage("Duplicate Record Found");
					
				}
				else{
				str = model.addNomDtl(nomDetail, request);
				addActionMessage(str);
				
				}
			}// end of if
			else {
				query+="AND NOM_ID NOT IN("+nomDetail.getParacode()+")";
				Object data[][] = model.getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					addActionMessage("Duplicate Record Found");
					
				}else{
				str = model.modNomDtl(nomDetail, request);
				addActionMessage(str);
				}
			}// end of else
			model.getNomineeRecord(nomDetail);
			getProfileImage();
			nomDetail.setEditFlag(false);
			nomDetail.setEditDetail(false);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	/**
	 * To set the record for edit
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		try {
			NomineeDetailModel model = new NomineeDetailModel();
			System.out.println("ID.............." + nomDetail.getParacode());
			System.out.println("Old Fraction Value.............."
					+ nomDetail.getOldFractionValue());
			model.initiate(context, session);
			if (nomDetail.isDeleteFlag()) {
				nomDetail.setDeleteChk("true");
			} else {
				nomDetail.setDeleteChk("false");
			}
			model.getRecord(nomDetail);
			model.getNomineeRecord(nomDetail);
			getProfileImage();
			nomDetail.setEditFlag(true);
			nomDetail.setEditDetail(true);
			model.terminate();
			// return NomRecord();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * To clear all form fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public void reset() {

		nomDetail.setEmpID("");
		nomDetail.setEmpName("");
		nomDetail.setRankName("");
		nomDetail.setCenterName("");
		nomDetail.setEmpToken("");
		nomDetail.setNomineeCode("");
		nomDetail.setMemberCode("");
		nomDetail.setMemberName("");
		nomDetail.setNomiFraction("");
		nomDetail.setNomiDate("");
		nomDetail.setNomiType("");
		nomDetail.setNomList(null);
		nomDetail.setWitnessID("");
		nomDetail.setWitnessName("");
		nomDetail.setWitnessAddress("");
		nomDetail.setWitnessDetailsList(null);
		nomDetail.setParacode("");

	}

	public String cancel() {
		try {
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			model.getNomineeRecord(nomDetail);
			nomDetail.setEditFlag(false);
			nomDetail.setEditDetail(false);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * To delete record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			boolean result = model.delNomDtl(nomDetail, request);
			model.terminate();
			if (result) {
				addActionMessage(getMessage("delete"));
				nomDetail.setNomineeCode("");
				nomDetail.setMemberCode("");
				nomDetail.setMemberName("");
				nomDetail.setNomiFraction("");
				nomDetail.setNomiDate("");
				nomDetail.setNomiType("");
				nomDetail.setNomList(null);
				nomDetail.setParacode("");
				nomDetail.setWitnessID("");
				nomDetail.setWitnessName("");
				nomDetail.setWitnessAddress("");
			}// end of if
			else {
				addActionMessage(getMessage("del.error"));
			}// end of else
			nomDetail.setEditDetail(false);
			nomDetail.setEditFlag(false);
			NomRecord();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * To display the record below.
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String NomRecord() throws Exception {
		try {
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			model.getNomineeRecord(nomDetail);

			/*
			 * nomDetail.setNomiFraction(""); nomDetail.setNomiDate("");
			 * nomDetail.setNomiType(""); nomDetail.setNomineeCode("");
			 * nomDetail.setMemberCode(""); nomDetail.setMemberName("");
			 */

			getProfileImage();

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * On Load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			if (nomDetail.isGeneralFlag()) {
				nomDetail.setIsNotGeneralUser("false");
				nomDetail.setEmpID(nomDetail.getUserEmpId());
			} else {
				nomDetail.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute(
						"srdEmpCode");
				if (offcEmp == null || offcEmp.equals("")) {
					offcEmp = nomDetail.getUserEmpId();
				}
				nomDetail.setEmpID(offcEmp);
			}
			model.getNomineeRecord(nomDetail);
			nomDetail.setEditFlag(false);
			nomDetail.setEditDetail(false);
			if (nomDetail.isDeleteFlag()) {
				nomDetail.setDeleteChk("true");
			} else {
				nomDetail.setDeleteChk("false");
			}
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To select the employee
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
				+ "	NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";

		query += getprofileQuery(nomDetail);
		query += " ORDER BY EMP_ID  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "15", "30", "30", "25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "nomDetail.empToken", "empName",
				"nomDetail.centerName", "nomDetail.rankName",
				"nomDetail.empID", "empName" };

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
		String submitToMethod = "NomineeDetail_NomRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * To select member
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9memaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		logger.info("Emp ID : " + nomDetail.getEmpID());
		String query = "SELECT FML_ID,FML_FNAME ||' '||FML_MNAME || ' '||FML_LNAME FROM HRMS_EMP_FML WHERE FML_ISALIVE='Y' AND EMP_ID= '"
				+ String.valueOf(nomDetail.getEmpID()) + "'";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("member.code"),
				getMessage("menbername") };

		String[] headerWidth = { "10", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "nomDetail.memberCode", "nomDetail.memberName" };

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
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * For deleting witness
	 * 
	 * @author MANISH
	 * @return
	 * @throws Exception
	 */
	public String deleteWitness() throws Exception {
		try {
			String witnessid = request.getParameter("witnessid");
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			boolean result = model.delWitness(nomDetail, request, witnessid);

			model.terminate();
			if (result) {
				addActionMessage(getMessage("delete"));
				nomDetail.setWitnessID("");
				nomDetail.setWitnessName("");
				nomDetail.setWitnessAddress("");
			} else {
				addActionMessage(getMessage("del.error"));
			}
			nomDetail.setEditFlag(true);
			nomDetail.setEditDetail(true);
			model.getRecord(nomDetail);
			NomRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";

	}

	/**
	 * For nominee report
	 * 
	 * @author MANISH
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {

		try {
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			model.getReport(nomDetail, request, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String nomineeDtls() {
		try {
			NomineeDetailModel model = new NomineeDetailModel();
			model.initiate(context, session);
			nomDetail.setParacode("");
			model.getNomineeRecord(nomDetail);
			getProfileImage();
			model.terminate();
			nomDetail.setEditFlag(true);
			nomDetail.setEditDetail(true);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "success";
	}

	// Added by Tinshuk Banafar Begins
	public void getProfileImage() {
		NomineeDetailModel model = new NomineeDetailModel();
		model.initiate(context, session);
		model.getImage(nomDetail);
		String photo = nomDetail.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		nomDetail.setFlag("false");
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
	// Added by Tinshuk Banafar Ends
}