package org.struts.action.admin.srd;

import java.util.Iterator;
import java.util.TreeMap;
import org.paradyne.bean.admin.srd.FamilyDetails;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.FamilyDetailsModel;
import com.lowagie.text.Image;

public class FamilyDetailsAction extends org.struts.lib.ParaActionSupport {
	private static final long serialVersionUID = 1L;
	
	FamilyDetails famDetail;
	String poolDir = "";
	String fileName = "";
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(FamilyDetailsAction.class);

	/**
	 * @return the famDetail
	 */
	public FamilyDetails getFamDetail() {
		return famDetail;
	}

	/**
	 * @param famDetail
	 *            the famDetail to set
	 */
	public void setFamDetail(FamilyDetails famDetail) {
		this.famDetail = famDetail;
	}

	/** (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {

		return famDetail;
	}

	public void prepare_local() throws Exception {
		famDetail = new FamilyDetails();
		famDetail.setMenuCode(46);
	}

	/**
	 * Method to save the family details of an employee. 
	 * @return String
	 * @throws Exception
	 */

	public String save() throws Exception {
		FamilyDetailsModel model = new FamilyDetailsModel();
		model.initiate(context, session);
		boolean result;
		String str = null;
		if (famDetail.getParacode().equals("")) {
			result = model.addFamDtl(famDetail,request);
			str = getMessage("save");
		}// end of if
		else {
			result = model.modFamDtl(famDetail,request);
			str = getMessage("update");
		}// end of else
		
		if (result) {
			addActionMessage(str);
			reset();
			//famDetail.setEmploymentSts("");
		}// end of if
		else {
			addActionMessage(getMessage("save.error"));
		}// end of else
		famDetail.setEditFlag(false);
		famDetail.setEditDetail(false);
		
	    FamRecord();
	    
	    model.terminate();
	    return "success";
	}

	/**
	 * Method to clear the form fields 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			famDetail.setFamilyCode("");
			famDetail.setFirstName("");
			famDetail.setMiddleName("");
			famDetail.setLastName("");
			famDetail.setRelCode("");
			famDetail.setRelName("");
			famDetail.setPhone("");
			famDetail.setProfession("");
			famDetail.setIdentification("");
			famDetail.setBirthday("");
			famDetail.setAddress("");
			famDetail.setEmail("");
			famDetail.setDependent("");
			famDetail.setOtherInfo("");
			famDetail.setUploadFileName("");
			famDetail.setMaritalStatus("U");
			famDetail.setSex("M");
			famDetail.setAlive("Y");
		}// end of try
		catch (Exception e) {
			logger.error("Exception" + e);
		} // end of catch
		 
		famDetail.setEditFlag(false);
		famDetail.setEditDetail(false);
		getProfileImage();
		return "success";
	}

	/**
	 * To set value of the fields for edit.
	 * @return String
	 * @throws Exception
	 */
	public String callForEdit() throws Exception {
		logger.info("inside edit");
		System.out.println("Family Code----------"+famDetail.getParacode());
		FamilyDetailsModel model = new FamilyDetailsModel();
		model.initiate(context, session);
		model.getRecord(famDetail);
		String familyPhoto = model.getPhoto(famDetail);
		String str = (String) session.getAttribute("session_pool");
		String img = model.getServletContext().getRealPath("//")
				+ "//pages//images//" + str + "//family//" + familyPhoto;
		famDetail.setFlag("false");
		try {
			Image image = Image.getInstance(img);
		}
		catch (Exception e) {
			familyPhoto = "NoImage.jpg";
			logger.error(e);
		}		
		request.setAttribute("familyPhoto", familyPhoto);
		famDetail.setEditFlag(true);
		famDetail.setEditDetail(true);
		FamRecord();
		getProfileImage();
		model.terminate();
		return "success";
		 
	}
	
	public String add() throws Exception {
		try {
			FamilyDetailsModel model = null;
			model = new FamilyDetailsModel();
			model.initiate(context, session);
			famDetail.setFlag("true");
			famDetail.setEditFlag(true);
			famDetail.setEditDetail(true);
			famDetail.setParacode("");
			String familyPhoto = "NoImage.jpg";			
			FamRecord();
			request.setAttribute("familyPhoto", familyPhoto);
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * Method to delete the family details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		FamilyDetailsModel model = new FamilyDetailsModel();
		model.initiate(context, session);
		boolean result = model.delFamDtl(famDetail,request);
		if (result) {
			addActionMessage(getMessage("delete"));
			reset();
		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
		}// end of else		
		String[][] memPhts = model.getFamilyRecord(famDetail, session);
		request.setAttribute("memPhts", memPhts);
		//model.getEmp(famDetail);
		model.terminate();
		famDetail.setEditFlag(false);
		famDetail.setEditDetail(false);
	
		getProfileImage();
		return "success";
	}

	/**
	 * To set the records below after search.
	 * @return String
	 * @throws Exception
	 */
	public String FamRecord() throws Exception {
		try {
			FamilyDetailsModel model = new FamilyDetailsModel();
			model.initiate(context, session);
			try {
				getProfileImage();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String[][] memPhts = model.getFamilyRecord(famDetail, session);
			request.setAttribute("memPhts", memPhts);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}

	/**
	 * To generate report.
	 * @return String
	 * @throws Exception
	 */
	public String famReport() throws Exception {
		FamilyDetailsModel model = new FamilyDetailsModel();
		model.initiate(context, session);
		model.getFamilyReportRecord(famDetail);
		model.terminate();
		return "famReport";
	}

	/**
	 * Method to get the family details of an employee while form loading.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		FamilyDetailsModel model = null;
		try {
			System.out.println("famDetail.getUserEmpId()------------"+famDetail.getUserEmpId());
			model = new FamilyDetailsModel();
			model.initiate(context, session);
			famDetail.setFlag("true");
			if (famDetail.isGeneralFlag()) {
				famDetail.setIsNotGeneralUser("false");		
				famDetail.setEmpID(famDetail.getUserEmpId());
			}
			else {
				famDetail.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute("srdEmpCode");
				if(offcEmp==null){
					offcEmp =famDetail.getUserEmpId();
				}
				famDetail.setEmpID(offcEmp);
			}				
			FamRecord();
		} 
		catch (Exception e) {
			logger.error(e);
		}
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map1 = dmu.getGenderXml("gender");
		TreeMap map2 = dmu.getGenderXml("marriage");
		Iterator it =map1.keySet().iterator(); 
		famDetail.setAssetmap(map1);
		famDetail.setMarriagemap(map2);
		famDetail.setMaritalStatus("U");
		famDetail.setEditFlag(false);
		dmu.terminate();
		//getProfileImage();
		model.terminate();
	}

	public String input() throws Exception{
		FamilyDetailsModel model = null;
		try {
			System.out.println("famDetail.getUserEmpId()------------"+famDetail.getUserEmpId());
			model = new FamilyDetailsModel();
			model.initiate(context, session);
			famDetail.setFlag("true");
			if (famDetail.isGeneralFlag()) {
				famDetail.setIsNotGeneralUser("false");			
			}
			else {
				famDetail.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute("srdEmpCode");
				if(offcEmp==null){
					offcEmp= famDetail.getUserEmpId();
				}
				famDetail.setEmpID(offcEmp);
			}	
			FamRecord();
		} 
		catch (Exception e) {
			logger.error(e);
		}
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map1 = dmu.getGenderXml("gender");
		TreeMap map2 = dmu.getGenderXml("marriage");
		Iterator it =map1.keySet().iterator();	 
		famDetail.setAssetmap(map1);
		famDetail.setMarriagemap(map2);
		famDetail.setEditFlag(false);
		famDetail.setEditDetail(false);
		getProfileImage();
		dmu.terminate();
		model.terminate();
        return SUCCESS;
	}
	/**
	 * To select an employee
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		String query = " SELECT EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	TO_CHAR(CENTER_NAME),HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";
		query += getprofileQuery(famDetail);
		query += " ORDER BY EMP_ID  ";
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "20", "30", "30", "20" };
		String[] fieldNames = { "famDetail.empToken", "famDetail.empName",
				"famDetail.centerName", "famDetail.rankName", "empID" };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitFlag = "true";
		String submitToMethod = "FamilyDetails_FamRecord.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Method to select the relation
	 * @return String
	 * @throws Exception
	 */
	public String f9relaction() throws Exception {
		String query = " SELECT RELATION_CODE,RELATION_NAME FROM HRMS_RELATION ORDER BY RELATION_CODE ";
		String[] headers = { getMessage("relation.code"),
				getMessage("relation") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "relCode", "relName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		famDetail.setMasterMenuCode(38);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	public void getProfileImage(){
		try {
			FamilyDetailsModel model = new FamilyDetailsModel();
			model.initiate(context, session);
			model.getImage(famDetail);
			 String photoNew = famDetail.getPhotoUploadFileName();
			String strNew = (String) session.getAttribute("session_pool");
			famDetail.setFlag("false");
			String imgNew = model.getServletContext().getRealPath("//")+ "//pages//images//" + strNew + "//employee//" + photoNew;
			try {
				Image image1 = Image.getInstance(imgNew);
			}// end of try
			catch (Exception e) {
				photoNew = "NoImage.jpg";
			}
			request.setAttribute("profilePhoto", photoNew);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String cancel(){
		try {
			FamilyDetailsModel model = new FamilyDetailsModel();
			model.initiate(context, session);
			FamRecord();
			getProfileImage();
			model.terminate();
			famDetail.setEditDetail(false);
			famDetail.setEditFlag(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
