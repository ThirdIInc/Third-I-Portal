package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.QualificationDetail;
import org.paradyne.bean.admin.srd.SalaryDetail;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

public class QualificationDetailModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(QualificationDetailModel.class);
	org.paradyne.bean.admin.srd.QualificationDetail qualDetail = null;

	AuditTrail trial = null;

	/**
	 * Method to add the qualification details of an employee.
	 * 
	 * @param bean
	 * @param request
	 * @return boolean
	 */
	public boolean addQualDtl(QualificationDetail bean,
			HttpServletRequest request) {
		Object addObj[][] = new Object[1][8];
		addObj[0][0] = bean.getEmpID();
		addObj[0][1] = bean.getQualCode();
		addObj[0][2] = bean.getInstitute();
		addObj[0][3] = bean.getUniversity();
		addObj[0][4] = bean.getYear();
		addObj[0][5] = bean.getGrade();
		addObj[0][6] = bean.getPercentage();
		addObj[0][7] = bean.getTech();

		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {

			String query1 = "SELECT MAX(QUA_ID) FROM HRMS_EMP_QUA";
			Object[][] data = getSqlModel().getSingleResult(query1);

			trial = new AuditTrail(bean.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE * */
			trial.initiate(context, session);

			trial.setParameters("HRMS_EMP_QUA", new String[] { "QUA_ID" },
					new String[] { String.valueOf(data[0][0]) }, bean
							.getEmpID());

			/** AUDIT TRAIL EXECUTION * */
			trial.executeADDTrail(request);
		}
		return result;

	}

	/** * Method to modify the qualification details of an employee.
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean modQualDtl(QualificationDetail bean,
			HttpServletRequest request) {

		boolean result = false;
		try {
			Object addObj[][] = new Object[1][9];
			addObj[0][0] = bean.getQualCode();
			addObj[0][1] = bean.getInstitute().trim();
			addObj[0][2] = bean.getUniversity().trim();
			addObj[0][3] = bean.getYear().trim();
			addObj[0][4] = bean.getGrade().trim();
			addObj[0][5] = bean.getPercentage().trim();
			addObj[0][6] = bean.getTech();
			addObj[0][7] = bean.getQualificationCode();
			addObj[0][8] = bean.getEmpID();
				
			result = getSqlModel().singleExecute(getQuery(2), addObj);
			/** AUDIT TRAIL EXECUTION * */
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return result;
	}

	/** Method to delete the qualification details of an employee.
	 * @param bean
	 * @param request
	 * @return boolean
	 */
	public boolean delQualDtl(QualificationDetail bean,
			HttpServletRequest request) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getParacode();
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE * */
		trial.initiate(context, session);
		trial.setParameters("HRMS_EMP_QUA", new String[] { "QUA_ID" },
		new String[] { bean.getParacode() }, bean.getEmpID());
		/** AUDIT TRAIL EXECUTION * */
		trial.executeDELTrail(request);
		boolean result= getSqlModel().singleExecute(getQuery(3), addObj);
		return result;
	}

	/** Method to display the qualification details of an employee below.
	 * @param qualDetail
	 */
	public void getQualifyRecord(QualificationDetail qualDetail) {
		Object addObj[] = new Object[1];
		addObj[0] = qualDetail.getEmpID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
		ArrayList<Object> qualList = new ArrayList<Object>();
		if (data!=null   && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				QualificationDetail bean1 = new QualificationDetail();
				bean1.setQualifyCode(String.valueOf(data[i][6]));
				bean1.setQualifyName(String.valueOf(data[i][8]));
				if(bean1.getQualifyName().length() > 15){
					bean1.setAbbrQualifyName(bean1.getQualifyName().substring(0, 14)+"...");
				}else{
					bean1.setAbbrQualifyName(bean1.getQualifyName());
				}
				bean1.setInstitute(String.valueOf(data[i][9]));
				if(bean1.getInstitute().length() > 15){
					bean1.setAbbrInstitute(bean1.getInstitute().substring(0, 14)+"...");
				}else{
					bean1.setAbbrInstitute(bean1.getInstitute());
				}
				bean1.setUniversity(String.valueOf(data[i][10]));
				if(bean1.getUniversity().length() > 15){
					bean1.setAbbrUniversity(bean1.getUniversity().substring(0, 14)+"...");
				}else{
					bean1.setAbbrUniversity(bean1.getUniversity());
				}
				if (String.valueOf(data[i][11]).equals("null")
						|| (data[i][11] == null) || (data[i][11].equals(""))) {
					bean1.setYear("");
				}// end of nested if
				else {
					bean1.setYear(String.valueOf(data[i][11]));
				}// end of nested else

				bean1.setGrade(String.valueOf(data[i][12]));
				if(bean1.getGrade().length() > 15){
					bean1.setAbbrGrade(bean1.getGrade().substring(0, 14)+"...");
				}else{
					bean1.setAbbrGrade(bean1.getGrade());
				}
				bean1.setPercentage(String.valueOf(data[i][13]));
				if (String.valueOf(data[i][14]).equals("null")) {
					bean1.setTech("");
				}// end of nested if
				else {
					bean1.setTech(String.valueOf(data[i][14]));
				}// end of nested else
				qualList.add(bean1);
			}// end of for loop
			qualDetail.setNoData("false");
			qualDetail.setQualList(qualList);
		}else{
			qualDetail.setNoData("true");
			qualDetail.setQualList(null);
		}
		
	}

	/** Method to set the qualification details of an employee for update
	 * 
	 * @param qualDetail
	 */
	public void getRecord(QualificationDetail qualDetail) {

		Object addObj[] = new Object[2];
		addObj[0] = qualDetail.getParacode();
		if(!qualDetail.getEmpID().equals("")){
			addObj[1] = qualDetail.getEmpID();
		}else{
			addObj[1] = qualDetail.getUserEmpId();
		}
		Object[][] data = getSqlModel().getSingleResult(getQuery(5), addObj);
		try {
			if(data!=null && data.length>0){
				if (String.valueOf(data[0][0]).equals("null") || (data[0][0] == null)
						|| (data[0][0].equals(""))) {
					qualDetail.setQualCode("");
				}// end of if
				else {
					qualDetail.setQualCode(String.valueOf(data[0][0]));
				}// end of else
				qualDetail.setQualifyName(String.valueOf(data[0][1]));
				qualDetail.setInstitute(String.valueOf(data[0][2]));
				qualDetail.setUniversity(String.valueOf(data[0][3]));
				if (String.valueOf(data[0][4]).equals("null") || (data[0][4] == null)
						|| (data[0][4].equals(""))) {
					qualDetail.setYear("");
				}// end of if
				else {
					qualDetail.setYear(String.valueOf(data[0][4]));
				}// end of else
				qualDetail.setGrade(String.valueOf(data[0][5]));
				qualDetail.setPercentage(String.valueOf(data[0][6]));
				qualDetail.setTech(String.valueOf(data[0][7]));
				qualDetail.setQualificationCode(String.valueOf(data[0][8]));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** Method to replace the null with a space.
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
  public void getImage(QualificationDetail qualDetail2) {
			
			String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+qualDetail2.getEmpID();
			
			Object[][] myPics = getSqlModel().getSingleResult(query);
			if(myPics!=null && myPics.length>0)
			{	
			qualDetail2.setUploadFileName( String.valueOf(myPics[0][0]));	
			qualDetail2.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));
			}
			
			System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+qualDetail2.getUploadFileName());
				
			}
}