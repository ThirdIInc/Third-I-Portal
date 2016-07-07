package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.VisaDetailsBean;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.AwardDetailsModel;
import org.paradyne.model.admin.srd.EmployeePostingModel;
import org.paradyne.model.admin.srd.VisaDetailsModel;
import org.struts.lib.ParaActionSupport;

import com.lowagie.text.Image;

public class VisaDetailsAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	VisaDetailsBean visaBean;

	public void prepare_local() throws Exception {
		visaBean = new VisaDetailsBean();
		visaBean.setMenuCode(2229);

	}

	public Object getModel() {
		return visaBean;
	}

	public VisaDetailsBean getVisaBean() {
		return visaBean;
	}

	public void setVisaBean(VisaDetailsBean visaBean) {
		this.visaBean = visaBean;
	}

	/** Method : input Purpose : for getting current user related information. 
	 * @return String*/
	public String input() throws Exception {
		VisaDetailsModel model = new VisaDetailsModel();
		model.initiate(context, session);
		if (visaBean.isGeneralFlag()) {
			visaBean.setIsNotGeneralUser("false");
			visaBean.setEmpId(visaBean.getUserEmpId());
		} else {
			visaBean.setIsNotGeneralUser("true");
			String str = (String) request.getSession().getAttribute(
					"srdEmpCode");
			if(str!=null && str!=""){
			visaBean.setEmpId(str);
			}
			else{
				visaBean.setEmpId(visaBean.getUserEmpId());
			}			
		}
		model.getVisaRecord(visaBean);
		model.getPassportRecord(visaBean);
		getProfileImage();
		model.terminate();
		return "success";
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		VisaDetailsModel model = new VisaDetailsModel();
		model.initiate(context, session);
		if (visaBean.isGeneralFlag()) {
			visaBean.setIsNotGeneralUser("false");
			visaBean.setEmpId(visaBean.getUserEmpId());
		} else {
			visaBean.setIsNotGeneralUser("true");
			String str = (String) request.getSession().getAttribute(
					"srdEmpCode");
			if(str!=null && str!=""){
			visaBean.setEmpId(str);
			}
			else{
				visaBean.setEmpId(visaBean.getUserEmpId());
			}			
		}
		//model.getVisaRecord(visaBean);
		//model.getPassportRecord(visaBean);
		getProfileImage();
		model.terminate();
	}

	public String resetVisa() {
		/*Visa*/
		visaBean.setVisaNumber("");
		visaBean.setCountry("");
		visaBean.setVisaType("");
		visaBean.setVisaEntry("");
		visaBean.setIssuePlace("");
		visaBean.setIssueAuthority("");
		visaBean.setIssueDate("");
		visaBean.setValidUpto("");
		visaBean.setAddress("");
		visaBean.setVisaCode("");
		visaBean.setParaId("");
		return SUCCESS;
	}
	public String resetPassport()
	{
		// Passport
		visaBean.setPassportExpDate("");
		visaBean.setPassport("");
		visaBean.setPassportName("");
		visaBean.setFatherName("");
		visaBean.setMotherName("");
		visaBean.setPassportIssueFrom("");
		visaBean.setPassportDateOfIssue("");
		visaBean.setPassportUidNum("");
		visaBean.setHiddenDelete("");
		visaBean.setHiddenEdit("");

		return SUCCESS;
	}


	public String deleteVisaDetails() {

		VisaDetailsModel model = new VisaDetailsModel();
		model.initiate(context, session);
		visaBean.setDisplayFlag("true");
        boolean result=model.deleteVisaRecord(visaBean);
		if(result)
		{
			addActionMessage(getText("delMessage","Record Deleted Successfully"));
			visaBean.setVisaNumber("");
			visaBean.setCountry("");
			visaBean.setVisaType("");
			visaBean.setVisaEntry("");
			visaBean.setIssuePlace("");
			visaBean.setIssueAuthority("");
			visaBean.setIssueDate("");
			visaBean.setValidUpto("");
			visaBean.setAddress("");
		}
		
		//visaBean.setHiddenDelete("");
		visaBean.setParaId("");
		model.getVisaRecord(visaBean);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/** Purpose - Used to add record into list. 
	 * @return String.
	 * @throws Exception - Exception.*/

	public String addItem() {
		VisaDetailsModel model = new VisaDetailsModel();
		 model.initiate(context, session);
		 resetVisa();
		
		 boolean finalResult=false;
		 visaBean.getPassport();
		 model.getVisaRecord(visaBean);
		 model.getPassportRecord(visaBean);
		finalResult =model.getpassportNumber(visaBean);
		System.out.println("*******************************" +finalResult);
          if(finalResult)
          {			
			
			visaBean.setDisplayFlag("true");
			visaBean.setAddFlag(true);
			visaBean.setEditFlag(false);
			
		
          }
          else
          {
        	  visaBean.setEditFlag(true);
        	  visaBean.setAddFlag(false);
        	  addActionMessage("Please Click Save Button");
          }
          getProfileImage();
		  model.terminate();
          return "success";
	}

	/** Method to save the Passport & Visa details of an Employee. 
	 * @return String
	 * @throws Exception*/
	public String savePassport() throws Exception {
		VisaDetailsModel model=new VisaDetailsModel();
		model.initiate(context, session);
		boolean result=false;
		boolean persResult=model.isPersonal(visaBean);
		if (persResult) {
			result = model.updatePassportDetail(visaBean);
			if(result){
				addActionMessage("Record Updated Successfully");
			}
		}
		else{
			result=model.insertPassPortDetail(visaBean);
			if(result){
				addActionMessage("Record Saved Successfully");
			}
		}
        visaBean.setEditFlag(false);
        model.getPassportRecord(visaBean);
        model.getVisaRecord(visaBean);
        getProfileImage();
		model.terminate();		
		return SUCCESS;
	}
/**
 * 
 * @return
 * @throws Exception
 */
	
	public String saveVisa() throws Exception
	{ 
		VisaDetailsModel model=new VisaDetailsModel();
		model.initiate(context, session);
		String str = "";
		if (visaBean.getParaId().equals("")) {
			str = model.addVisaDetail(visaBean);
		}// end of if
		else {
			str = model.modifyVisaDetail(visaBean);
			System.out.println("Record Updated Successfully");
			
		}// end of else
		addActionMessage(getText(str));
        visaBean.setEditFlag(false);
        model.getPassportRecord(visaBean);
        model.getVisaRecord(visaBean);
       // visaBean.setVisaCode("");
        getProfileImage();
		model.terminate();
		
		return SUCCESS;
		
	}
	
	
	
	/**Method name : cancel. purpose back to previous page 
	 * @return String.
	 * @throws Exception -Exception.*/
	public String cancelFunc() throws Exception {
		try{
			VisaDetailsModel model=new VisaDetailsModel();
			model.initiate(context, session);
			model.getPassportRecord(visaBean);
			model.getVisaRecord(visaBean);
		    getProfileImage();
		    visaBean.setEditFlag(false);
		    model.terminate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	/** THIS METHOD IS USED FOR SELECTING EMPLOYEE 
	 * @return String
	 * @throws Exception*/

	/** Method to select the employee. 
	 * @return String
	 * @throws Exception*/
	public String f9action() throws Exception {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE
			 * DESIRED OUTPUT
			 */
			String query = "SELECT EMP_TOKEN,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),EMP_ID"
					+ " FROM HRMS_EMP_OFFC "					
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";
			if (visaBean.getUserProfileDivision() != null
					&& visaBean.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ visaBean.getUserProfileDivision() + ")";
			} else {
				query += " WHERE 1=1 ";
			}
			query += " AND EMP_STATUS='S'";
			query += "ORDER BY EMP_ID";

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP
			 * WINDOW. *
			 */
			String[] headers = { "Employee Id", getMessage("employee.head")};

			String[] headerWidth = { "20", "30"};

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED
			 * AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS
			 * CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST
			 * BE SAME AS THE LENGTH OF FIELDNAMES
			 */
			String[] fieldNames = { "empToken", "empName", "empId" };
			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT
			 * ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE
			 * SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE
			 * {1,3}
			 * 
			 * NOTE: COLUMN NUMBERS STARTS WITH 0
			 * 
			 */
			int[] columnIndex = { 0, 1, 2};
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
			String submitToMethod = "VisaDetails_showRecord.action";
			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** Method to set Address Particulars after edit or delete. 
	 * @return String
	 * @throws Exception*/

	public String showRecord() throws Exception {
		VisaDetailsModel model = new VisaDetailsModel();
		model.initiate(context, session);
		//model.getEmpRecord(visaBean, request);
		model.getPassportRecord(visaBean);
		model.getVisaRecord(visaBean);
		//visaBean.setDisplayFlag("true");
		//model.showList(visaBean);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	public void getProfileImage() {
		VisaDetailsModel model = new VisaDetailsModel();
		model.initiate(context, session);
		model.getImage(visaBean);
		String photo = visaBean.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
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

	public String editPassport() throws Exception {
		try {
			VisaDetailsModel model = new VisaDetailsModel();

			model.initiate(context, session);
			model.getPassportRecord(visaBean);
			model.getVisaRecord(visaBean);
			visaBean.setEditFlag(true);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return SUCCESS;
	}
	public String editVisa() throws Exception
	{
		try
		{
			VisaDetailsModel model = new VisaDetailsModel();
			model.initiate(context, session);
			model.getVisaSingleRecord(visaBean);
			model.getVisaRecord(visaBean);
			model.getPassportRecord(visaBean);
			visaBean.setAddFlag(true);
			getProfileImage();
			model.terminate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
