package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.paradyne.bean.admin.srd.FamilyDetails;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;
import com.lowagie.text.Image;

public class FamilyDetailsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(FamilyDetailsModel.class);
	org.paradyne.bean.admin.srd.FamilyDetails famDetail = null;

	
	AuditTrail trial=null;
	
	/**
	 * To add family details of the employee.
	 * 
	 * @param bean
	 * @param request 
	 * @return boolean
	 */
	public boolean addFamDtl(FamilyDetails bean, HttpServletRequest request) {
		Object addObj[][] = new Object[1][18];
		addObj[0][0] = bean.getEmpID();
		addObj[0][1] = bean.getFirstName();
		addObj[0][2] = bean.getMiddleName();
		addObj[0][3] = bean.getLastName();
		addObj[0][4] = bean.getRelCode();
		addObj[0][5] = bean.getMaritalStatus();
		addObj[0][6] = bean.getSex();
		addObj[0][7] = bean.getPhone();
		addObj[0][8] = bean.getProfession();
		addObj[0][9] = bean.getIdentification();
		addObj[0][10] = bean.getEmail();
		addObj[0][11] = bean.getBirthday();
		addObj[0][12] = bean.getAddress();
		addObj[0][13] = bean.getAlive();
		addObj[0][14] = bean.getDependent();
		String img = bean.getUploadFileName();
		addObj[0][15] = img;
		addObj[0][16] = bean.getOtherInfo();
		addObj[0][17]=bean.getEmploymentSts();
		boolean result=getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			String query1 = "SELECT MAX(FML_ID) FROM HRMS_EMP_FML";
			Object[][] data = getSqlModel().getSingleResult(query1);
			trial = new AuditTrail(bean.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session); 
			trial.setParameters("HRMS_EMP_FML", new String[] { "FML_ID" },
			new String[] { String.valueOf(data[0][0]) }, bean.getEmpID());
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeADDTrail(request);
		}
		return result;
	}
	/**
	 * To modify the family details of the employee.
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean modFamDtl(FamilyDetails bean,HttpServletRequest request) {
		Object addObj[][] = new Object[1][18];
		try {
			logger.info("===USER LOGIN CODE====" + bean.getUserEmpId());
			trial = new AuditTrail(bean.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session);
			trial.setParameters("HRMS_EMP_FML", new String[] { "FML_ID" },new String[] { bean.getFamilyCode() }, bean.getEmpID());
			trial.beginTrail();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in modOffDtl-------------------"+e);
		}
		addObj[0][0] = bean.getFirstName().trim();
		addObj[0][1] = bean.getMiddleName().trim();
		addObj[0][2] = bean.getLastName().trim();
		addObj[0][3] = bean.getRelCode();
		addObj[0][4] = bean.getMaritalStatus();
		addObj[0][5] = bean.getSex();
		addObj[0][6] = bean.getPhone().trim();
		addObj[0][7] = bean.getProfession().trim();
		addObj[0][8] = bean.getIdentification().trim();
		addObj[0][9] = bean.getEmail().trim();
		addObj[0][10] = bean.getBirthday().trim();
		addObj[0][11] = bean.getAddress();
		addObj[0][12] = bean.getAlive();
		addObj[0][13] = bean.getDependent();
		addObj[0][14] = bean.getUploadFileName();
		addObj[0][15] = bean.getOtherInfo();
		addObj[0][17] = bean.getParacode();
		addObj[0][16]=bean.getEmploymentSts();
		boolean result= getSqlModel().singleExecute(getQuery(4), addObj);
		/**	AUDIT TRAIL	EXECUTION **/	 	
 		//trial.executeMODTrail(request);
  		return result;
	}
	/**
	 * To delete the family details of the employee.
	 * 
	 * @param bean
	 * @param request 
	 * @return boolean
	 */
	public boolean delFamDtl(FamilyDetails bean, HttpServletRequest request) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getParacode();
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE	**/
		trial.initiate(context, session);
		trial.setParameters("HRMS_EMP_FML", new String[] { "FML_ID" },
		new String[] {bean.getParacode() }, bean.getEmpID());
		/**	AUDIT TRAIL	EXECUTION **/
		trial.executeDELTrail(request);
		return getSqlModel().singleExecute(getQuery(5), addObj);
	}

	/**
	 * To set the family details of the employee to show below after search..
	 * 
	 * @param famDetail
	 * @param session
	 * @return String
	 */
	public String[][] getFamilyRecord(FamilyDetails famDetail,
			HttpSession session) {
		String[][] memPhoto = null;
		Object addObj[] = new Object[1];
		addObj[0] = famDetail.getEmpID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(2), addObj);
		ArrayList<Object> famList = new ArrayList<Object>();
		if (null != data && data.length > 0) {
			
			famDetail.setNoData("false");
			memPhoto = new String[1][data.length];
			for (int i = 0; i < data.length; i++) {
				FamilyDetails bean1 = new FamilyDetails();
				memPhoto[0][i] = String.valueOf(data[i][8]);
				String str = (String) session.getAttribute("session_pool");
				String img = getServletContext().getRealPath("//")
						+ "\\pages\\images\\" + str + "\\family\\"
						+ String.valueOf(data[i][8]);
				try {

					Image image = Image.getInstance(img);
				}// end of try
				catch (Exception e) {
					memPhoto[0][i] = "NoImage.jpg";
					logger.error(e);
				}// end of catch

				bean1.setFamilyCode(String.valueOf(data[i][0]));
				bean1.setFirstName(String.valueOf(data[i][1]));
				bean1.setRelName(String.valueOf(data[i][3]));
				bean1.setSex(checkNull(String.valueOf(data[i][4])));
				/*if (String.valueOf(data[i][5]).equals("null")) {
					bean1.setMaritalStatus("");
				}// end of nested if
				else*/
				bean1.setMaritalStatus(checkNull(String.valueOf(data[i][5])));
				bean1.setAlive(String.valueOf(data[i][6]));
				bean1.setDependent(String.valueOf(data[i][7]));
				
				bean1.setPhone(String.valueOf(data[i][10]));
				bean1.setProfession(String.valueOf(data[i][11]));
				if(bean1.getProfession().length() > 25){
					bean1.setAbbrProfession(bean1.getProfession().substring(0, 24)+"...");
				}else{
					bean1.setAbbrProfession(bean1.getProfession());
				}
				bean1.setEmail(String.valueOf(data[i][12]));
				if(bean1.getEmail().length() >25){
					bean1.setAbbrEmail(bean1.getEmail().substring(0, 24)+"...");
				}else{
					bean1.setAbbrEmail(bean1.getEmail());
				}
				bean1.setIdentification(String.valueOf(data[i][13]));
				if(bean1.getIdentification().length() >25){
					bean1.setAbbrIdentification(bean1.getIdentification().substring(0, 24)+"...");
				}else{
					bean1.setAbbrIdentification(bean1.getIdentification());
				}
				bean1.setBirthday(String.valueOf(data[i][14]));
				bean1.setAddress(String.valueOf(data[i][15]));
				if(bean1.getAddress().length() >25){
					bean1.setAbbrAddress(bean1.getAddress().substring(0, 24)+"...");
				}else{
					bean1.setAbbrAddress(bean1.getAddress());
				}
				bean1.setOtherInfo(String.valueOf(data[i][16]));
				if(bean1.getOtherInfo().length() >25){
					bean1.setAbbrOtherInfo(bean1.getOtherInfo().substring(0, 24)+"...");
				}else{
					bean1.setAbbrOtherInfo(bean1.getOtherInfo());
				}

				bean1.setSpouse_photo(String.valueOf(memPhoto[0][i]));

				famList.add(bean1);
			}// end of for loop
			famDetail.setFamList(famList);
		}else{
			famDetail.setNoData("true");
			famDetail.setFamList(null);
		}
		if (data.length > 0) {

			return memPhoto;

		}// end of if
		else {

			return null;
		}// end of else
	}

	/**
	 * To set the value of the field for edit
	 * 
	 * @param famDetail
	 */
	public void getRecord(FamilyDetails famDetail) {

		Object addObj[] = new Object[2];
		addObj[0]=famDetail.getParacode().trim();
		addObj[1] = famDetail.getEmpID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
		famDetail.setFirstName(String.valueOf(data[0][0]).trim());
		famDetail.setMiddleName(String.valueOf(data[0][1]).trim());
		famDetail.setLastName(String.valueOf(data[0][2]).trim());
		if (String.valueOf(data[0][3]).equals("null") || (data[0][3] == null)
				|| (data[0][3].equals(""))) {
			famDetail.setRelCode("");
		}// end of if
		else {
			famDetail.setRelCode(String.valueOf(data[0][3]));
		}// end of else
		famDetail.setRelName(String.valueOf(data[0][4]).trim());
		famDetail.setMaritalStatus(String.valueOf(data[0][5]));
		famDetail.setSex(String.valueOf(data[0][6]));
		if (String.valueOf(data[0][7]).equals("null") || (data[0][7] == null)
				|| (data[0][7].equals(""))) {
			famDetail.setPhone("");
		}// end of if
		else {
			famDetail.setPhone(String.valueOf(data[0][7]).trim());
		}// end of else
		famDetail.setProfession(String.valueOf(data[0][8]).trim());
		famDetail.setIdentification(String.valueOf(data[0][9]).trim());
		famDetail.setEmail(String.valueOf(data[0][10]).trim());

		if (String.valueOf(data[0][11]).equals("null") || (data[0][11] == null)
				|| (data[0][11].equals(""))) {
			famDetail.setBirthday("");
		}// end of if
		else {
			famDetail.setBirthday(String.valueOf(data[0][11]).trim());
		}// end of else
		famDetail.setAddress(String.valueOf(data[0][12]).trim());
		famDetail.setParacode(String.valueOf(data[0][13]).trim());
		famDetail.setFamilyCode(String.valueOf(data[0][13]).trim());
		famDetail.setAlive(String.valueOf(data[0][14]).trim());
		famDetail.setDependent(String.valueOf(data[0][15]).trim());
		famDetail.setOtherInfo(String.valueOf(data[0][17]).trim());
		famDetail.setUploadFileName(String.valueOf(data[0][16]).trim());
		famDetail.setEmploymentSts(String.valueOf(data[0][18]).trim());
	}

	/**
	 * To get photo
	 * 
	 * @param famDetail
	 * @return String
	 */
	public String getPhoto(FamilyDetails famDetail) {
		String SQL = "  SELECT NVL(FML_SPOUSE_PHOTO,' ') from HRMS_EMP_FML inner join hrms_emp_offc on(hrms_emp_offc.emp_id=hrms_emp_fml.emp_id)"
				+ " WHERE  FML_ID="
				+ famDetail.getParacode()
				+ " AND HRMS_EMP_OFFC.EMP_ID =" + famDetail.getEmpID();
		Object[][] data = getSqlModel().getSingleResult(SQL);
		return String.valueOf(data[0][0]);

	}
	/**
	 * To generate the report of family details of the employee.
	 * 
	 * @param famDetail
	 */
	public void getFamilyReportRecord(FamilyDetails famDetail) {
		Object addObj[] = new Object[1];
		addObj[0] = famDetail.getEmpID();
		String query = "  SELECT RANK_NAME from HRMS_RANK "
				+ " left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
				+ " WHERE EMP_ID = " + famDetail.getEmpID();
		Object[][] res = getSqlModel().getSingleResult(query);
		famDetail.setDesign(String.valueOf(res[0][0]));
		Object[][] data = getSqlModel().getSingleResult(getQuery(6), addObj);
		ArrayList<Object> famList = new ArrayList<Object>();
		if (null != data && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				FamilyDetails bean1 = new FamilyDetails();

				bean1.setFamilyCode(String.valueOf(i + 1));
				bean1.setFirstName(String.valueOf(data[i][0]));
				bean1.setRelName(String.valueOf(data[i][2]));
				if (String.valueOf(data[i][3]).equals("null")
						|| (data[i][3] == null) || (data[i][3].equals(""))) {
					bean1.setBirthday("");
				}// end of if
				else {
					bean1.setBirthday(String.valueOf(data[i][3]));
				}// end of else

				famList.add(bean1);
			}// end of for loop
			famDetail.setFamList(famList);
		}// end of if
	}

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

public void getImage(FamilyDetails famDetail) {
		
		try {
			if(famDetail.getEmpID()==null || famDetail.getEmpID().equals(""))
			{
				famDetail.setEmpID(famDetail.getUserEmpId());
			}
			String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+famDetail.getEmpID();
			
			Object[][] myPics = getSqlModel().getSingleResult(query);
				
			famDetail.setPhotoUploadFileName(String.valueOf(myPics[0][0]));	
			famDetail.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));
			
			System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+famDetail.getPhotoUploadFileName());
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
