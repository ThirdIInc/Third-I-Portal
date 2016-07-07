package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.AwardDetails;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

/**
 * @modified by priyanka. kumbhar
 * 
 */

public class AwardDetailsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AwardDetailsModel.class);
	org.paradyne.bean.admin.srd.AwardDetails awdMaster = null;

	AuditTrail trial = null;

	/**
	 * To save the record
	 * 
	 * @param bean
	 * @return String
	 */
	public String addAwdDtl(AwardDetails bean) {
		Object addObj[][] = new Object[1][10];

		addObj[0][0] = bean.getEmpId().trim();
		addObj[0][1] = bean.getAwdId().trim();
		addObj[0][2] = bean.getAwdDesc().trim();		
		addObj[0][3] = bean.getAwdDt().trim();
		addObj[0][4] = bean.getAuth().trim();
		addObj[0][5] = bean.getAuthDt().trim();
		addObj[0][6] = bean.getSlNo();
		addObj[0][7] = bean.getUploadMyFileNameTxt();
		addObj[0][8] = bean.getUploadImageName().trim();
		if (bean.getActiveInDashlet().trim().equals("true")) {
			addObj[0][9] = "Y";
		} else {
			addObj[0][9] = "N";
		}

		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			return "Record saved Successfully";

		}// end of if
		else {

			return "Record cannot be added.";
		}// end of else
	}

	/**
	 * To modify the record
	 * 
	 * @param bean
	 * @return String
	 */
	public String modAwdDtl(AwardDetails bean, HttpServletRequest request) {
		String str = "";
		try {
			Object modObj[][] = new Object[1][11];

			logger.info("===USER LOGIN CODE====" + bean.getUserEmpId());
			trial = new AuditTrail(bean.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE * */
			trial.initiate(context, session);
			trial.setParameters("HRMS_AWARD", new String[] { "AWARD_ID" },
					new String[] { bean.getAwdCode() }, bean.getEmpId());
			trial.beginTrail();
			modObj[0][0] = bean.getAwdId().trim();
			modObj[0][1] = bean.getAwdDesc();
			modObj[0][2] = bean.getAwdDt().trim();
			modObj[0][3] = bean.getAuth().trim();
			modObj[0][4] = bean.getAuthDt().trim();
			modObj[0][5] = bean.getEmpId().trim();
			modObj[0][6] = bean.getSlNo();			
			modObj[0][7] = bean.getUploadMyFileNameTxt();
			modObj[0][8] = bean.getUploadImageName().trim();
			if (bean.getActiveInDashlet().trim().equals("true")) {
				modObj[0][9] = "Y";
			} else {
				modObj[0][9] = "N";
			}
			modObj[0][10] = bean.getParaId();
			boolean result = getSqlModel().singleExecute(getQuery(2), modObj);
			/** AUDIT TRAIL EXECUTION * */
			//trial.executeMODTrail(request);
			if (result) {
				str = "Record updated successfully";
			}// end of if
			else {

				str = "Record cannot be updated.";
			}// end of else
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in modAwdDtl-----------" + e);
		}
		return str;
	}

	/**
	 * To delete the record
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean deleteAward(AwardDetails bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getParaId();
		return getSqlModel().singleExecute(getQuery(6), addObj);
	}

	/**
	 * for selecting the particular record which is going to be modify.
	 * 
	 * @param awdMaster
	 * @return Object
	 */
	public AwardDetails getRecord(AwardDetails awdMaster) {
		try {
			Object addObj[] = new Object[2];
			addObj[0] = awdMaster.getEmpId();
			addObj[1]= awdMaster.getParaId();
			Object[][] data = getSqlModel()
					.getSingleResult(getQuery(3), addObj);
			for (int i = 0; i < data.length; i++) {
				awdMaster.setAwdCode(checkNull(String.valueOf(data[0][0])));
				awdMaster.setEmpId(checkNull(String.valueOf(data[0][1])));
				awdMaster
						.setEmployeeToken(checkNull(String.valueOf(data[0][2])));
				awdMaster.setEmpName(checkNull(String.valueOf(data[0][3])));
				awdMaster.setSlNo(checkNull(String.valueOf(data[0][4])));
				awdMaster.setAwdId(checkNull(String.valueOf(data[0][5])));
				awdMaster.setAwardType(checkNull(String.valueOf(data[0][6])));
				awdMaster.setAwdDesc(checkNull(String.valueOf(data[0][7])));
				awdMaster.setAwdDt(checkNull(String.valueOf(data[0][8])));
				awdMaster.setAuth(checkNull(String.valueOf(data[0][9])));
				awdMaster.setAuthDt(checkNull(String.valueOf(data[0][10])));
				awdMaster.setUploadMyFileNameTxt(checkNull(String
						.valueOf(data[0][11])));
				awdMaster.setUploadImageName(checkNull(String
						.valueOf(data[0][12])));
				awdMaster.setActiveInDashlet(checkNull(String
						.valueOf(data[0][13])));
				awdMaster.setActiveInDashlet(awdMaster.getActiveInDashlet()
						.equals("Y") ? "true" : "");

			}// end of for loop
		} catch (Exception e) {
			e.printStackTrace();
		}
		return awdMaster;
	}

	/**
	 * To set the record below after search
	 * 
	 * @param bean1
	 */
	public void getAwardRecord(AwardDetails bean1) {
		Object[] param = new Object[1];
		param[0] = bean1.getEmpId();	
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), param);
		ArrayList<Object> awdList = new ArrayList<Object>();
		if (data.length > 0 && data!=null) {
		for (int i = 0; i < data.length; i++) {
			AwardDetails bean2 = new AwardDetails();
			bean2.setAwdCode(checkNull(String.valueOf(data[i][0])));
			bean2.setEmpId(checkNull(String.valueOf(data[i][1])));
			bean2.setEmployeeToken(checkNull(String.valueOf(data[i][2])));
			bean2.setEmpName(checkNull(String.valueOf(data[i][3])));
			bean2.setSlNo(checkNull(String.valueOf(data[i][4])));
			bean2.setAwdId(checkNull(String.valueOf(data[i][5])));
			bean2.setAwardType(checkNull(String.valueOf(data[i][6])));
			bean2.setAwdDesc(checkNull(String.valueOf(data[i][7])));
			bean2.setAwdDt(checkNull(String.valueOf(data[i][8])));
			bean2.setAuth(checkNull(String.valueOf(data[i][9])));
			bean2.setAuthDt(checkNull(String.valueOf(data[i][10])));
			bean2.setUploadMyFileName(checkNull(String.valueOf(data[i][11])));			
			bean2.setUploadImageNameItt(checkNull(String.valueOf(data[i][12])));
			bean2.setIsActiveItt(checkNull(String.valueOf(data[i][13])));
			awdList.add(bean2);
		}// end of for loop
		bean1.setAwardList(awdList);
		bean1.setNoData("false");
		}else{
			bean1.setAwardList(null);
			bean1.setNoData("true");
		}

	}

	/**
	 * To generate report.
	 * 
	 * @param bean
	 */
	public void getAwardReport(AwardDetails bean) {

		Object[] param = new Object[1];
		param[0] = bean.getEmpId();
		AwardDetails bean2 = new AwardDetails();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), param);
		bean2.setEmpId(checkNull(String.valueOf(data[0][1])));
		bean2.setEmployeeToken(checkNull(String.valueOf(data[0][2])));
		bean2.setEmpName(checkNull(String.valueOf(data[0][3])));
		bean2.setEmpCent(checkNull(String.valueOf(data[0][4])));
		bean2.setEmpRank(checkNull(String.valueOf(data[0][5])));
		ArrayList<Object> awdList = new ArrayList<Object>();
		for (int i = 0; i < data.length; i++) {
			AwardDetails bean1 = new AwardDetails();
			bean1.setAwardType(checkNull(String.valueOf(data[i][6])));
			bean1.setAwdDesc(checkNull(String.valueOf(data[i][7])));
			bean1.setAwdDt(checkNull(String.valueOf(data[i][8])));
			bean1.setAuth(checkNull(String.valueOf(data[i][9])));
			bean1.setAuthDt(checkNull(String.valueOf(data[i][10])));
			awdList.add(bean1);
		}// end of for loop
		bean.setAwardList(awdList);

	}

	/**
	 * Method to replace the null with a space.
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

	/**
	 * for display the record for selected employee.
	 * @param empId
	 * @param bean
	 */

	public void getLoginAward(String empId, AwardDetails bean) {
		Object[] param = new Object[1];
		param[0] = bean.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(7), param);

		ArrayList<Object> awdList = new ArrayList<Object>();
		if (data.length > 0 && data!=null) {
		for (int i = 0; i < data.length; i++) {
			AwardDetails bean1 = new AwardDetails();
			bean1.setAwdCode(checkNull(String.valueOf(data[i][0])));
			bean1.setAwardType(checkNull(String.valueOf(data[i][6])));
			bean1.setAwdDt(checkNull(String.valueOf(data[i][8])));
			bean1.setAwdDesc(checkNull(String.valueOf(data[i][7])));
			bean1.setAuth(checkNull(String.valueOf(data[i][9])));
			bean1.setAuthDt(checkNull(String.valueOf(data[i][10])));
			bean1.setSlNo(checkNull(String.valueOf(data[i][11])));
			awdList.add(bean1);

		}// end of for loop
		bean.setAwardList(awdList);
		bean.setNoData("false");
		}else{
			bean.setAwardList(null);
			bean.setNoData("true");
		}
	}
	public void getImage(AwardDetails awdDetails) {
			 try {
				  String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+awdDetails.getEmpId();
					
					Object[][] myPics = getSqlModel().getSingleResult(query);
						
					awdDetails.setPhotoUploadFileName( String.valueOf(myPics[0][0]));	
					awdDetails.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));	
					
					System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+awdDetails.getPhotoUploadFileName());
					
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
