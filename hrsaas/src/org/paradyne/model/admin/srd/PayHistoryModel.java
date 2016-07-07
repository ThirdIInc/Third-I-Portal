package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.PayHistory;
import org.paradyne.bean.admin.srd.SkillDetails;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;


/**
 * @author Prajakta.Bhandare
 * @date 07 Jan 2013
 */
public class PayHistoryModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PayHistoryModel.class);
	
	AuditTrail trial=null;

	/**
	 * To save a new record
	 * 
	 * @param payHistory
	 * @return String
	 */
	public String savePay(PayHistory payHistory) {

		Object addObj[][] = new Object[1][5];
		addObj[0][0] = payHistory.getEmpId();
		addObj[0][1] = payHistory.getPayType().trim();
		addObj[0][2] = payHistory.getNctc();
		addObj[0][3] = payHistory.getOctc();
		addObj[0][4] = payHistory.getIncrementDate();
		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			return "Record saved successfully";
		}// end of if
		else {
			return "Record can not be added";
		}// end of else
	}

	/**
	 * To display the records for selected employee.
	 * 
	 * @param payHistory
	 */
	public void getPayDetails(PayHistory payHistory) {
		Object[] empcode = new Object[1];
		empcode[0] = payHistory.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(2), empcode);
		ArrayList list1 = new ArrayList();
		if (data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				PayHistory bean = new PayHistory();

				bean.setPayType(checkNull(String.valueOf(data[i][0])));
				bean.setNctc(checkNull(String.valueOf(data[i][1])));
				bean.setOctc(checkNull(String.valueOf(data[i][2])));
				bean.setIncrementDate(checkNull(String.valueOf(data[i][3])));
				bean.setPayId(checkNull(String.valueOf(data[i][4])));
				list1.add(bean);
			}// end of for loop
			payHistory.setPayList(list1);
			payHistory.setNoData("false");
		}// end of if
		else{
			payHistory.setNoData("true");
			payHistory.setPayList(null);
		}
	}

	/**
	 * To select the particular record which is going to be modify.
	 * 
	 * @param payHistory
	 */
	public void getOneRecord(PayHistory payHistory) {

		Object[] payId = new Object[2];
		payId[0] = payHistory.getParacode();
		payId[1] = payHistory.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), payId);
		payHistory.setEmpId(payHistory.getEmpId());
		payHistory.setPayId(payHistory.getParacode());
		payHistory.setPayType(checkNull(String.valueOf(data[0][0])));
		payHistory.setNctc(checkNull(String.valueOf(data[0][1])));
		payHistory.setOctc(checkNull(String.valueOf(data[0][2])));
		payHistory.setIncrementDate(checkNull(String.valueOf(data[0][3])));
	}

	/**
	 * To modify the particular record of an employee.
	 * 
	 * @param payHistory
	 * @return String
	 */
	public String modPay(PayHistory payHistory,HttpServletRequest request) {

		String str = "";
		try {
			Object modObj[][] = new Object[1][5];
			str = "";
			logger.info("===USER LOGIN CODE====" + payHistory.getUserEmpId());
			trial = new AuditTrail(payHistory.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session);
			trial.setParameters("HRMS_EMP_INCR", new String[] { "INCR_CODE" },
					new String[] { payHistory.getPayId() }, payHistory
							.getEmpId());
			trial.beginTrail();
			modObj[0][0] = payHistory.getPayType();
			modObj[0][1] = payHistory.getNctc();
			modObj[0][2] = payHistory.getOctc();
			modObj[0][3] = payHistory.getIncrementDate();
			modObj[0][4] = payHistory.getParacode();
			
			boolean result = getSqlModel().singleExecute(getQuery(4), modObj);
			if (result) {
				str = "Record updated Successfully";
			}// end of if
			else {
				str = "Record can not be updated ";
			}// end of else
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeMODTrail(request);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in modPay-----------"+e);
		}
		return str;
	}

	/**
	 * To delete the particular record of an employee.
	 * 
	 * @param bean
	 * @return String
	 */
	public String deleteRecord(PayHistory bean) {

		Object[][] delObj = new Object[1][1];
		delObj[0][0] = bean.getParacode();
		boolean result = getSqlModel().singleExecute(getQuery(5), delObj);
		if (result) {
			return "Record Deleted successfully";
		}// end of if
		else {
			return "Record can not be deleted";
		}// end of else

	}
	
	/** METHOD TO GET IMAGE OF EMPLOYEE
	 * @param payHistory
	 */
	public void getImage(PayHistory payHistory) {

		try {
			String query = "select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="
					+ payHistory.getEmpId();

			Object[][] myPics = getSqlModel().getSingleResult(query);
			if (myPics != null && myPics.length > 0) {
				payHistory.setUploadFileName(checkNull(String.valueOf(myPics[0][0])));
				payHistory.setProfileEmpName(checkNull(String.valueOf(myPics[0][1])) + " "
						+ checkNull(String.valueOf(myPics[0][2])) + " "
						+ checkNull(String.valueOf(myPics[0][3])));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

}
	
	/** Method to check null value
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
}
