package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.PayHistory;
import org.paradyne.bean.admin.srd.PromotionHistory;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

/**
 * @author Prajakta.Bhandare
 * @date 09 Jan 2013
 */
public class PromotionHistoryModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PromotionHistoryModel.class);
	
	AuditTrail trial=null;

	/**
	 * for saving the new records.	
	 * @param promotionHistory
	 * @return String
	 */
	public String savePromotion(PromotionHistory promotionHistory) {

		Object addObj[][] = new Object[1][6];
		addObj[0][0] = promotionHistory.getEmpId();
		addObj[0][1] = promotionHistory.getPostId();
		addObj[0][2] = promotionHistory.getPromotionDate();
		addObj[0][3] = promotionHistory.getDeptId();
		addObj[0][4] = promotionHistory.getCenterNo();
		addObj[0][5] = promotionHistory.getCtc();
		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			return "Record Saved Successfully";
		}//end of if 
		else {
			return "Record can not be saved";
		}//end of else
	}

	/**
	 * for display the record for selected employee.
	 * 
	 * @param promotionHistory
	 */
	public void getPromotionDetails(PromotionHistory promotionHistory) {
		Object[] empcode = new Object[1];
		empcode[0] = promotionHistory.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(2), empcode);
		Object[][] data1 = getSqlModel().getSingleResult(getQuery(7), empcode);
		ArrayList list1 = new ArrayList();
		if (data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				PromotionHistory bean = new PromotionHistory();
				bean.setPost(checkNull(String.valueOf(data[i][0])));
				bean.setPromotionDate(checkNull(String.valueOf(data[i][1])));
				bean.setPromoId(checkNull(String.valueOf(data[i][2])));
				bean.setDept(checkNull(String.valueOf(data[i][3])));
				bean.setCenterName(checkNull(String.valueOf(data[i][4])));
				bean.setCtc(checkNull(String.valueOf(data[i][5])));
				list1.add(bean);
			}//end of for loop
			promotionHistory.setPromoList(list1);
			promotionHistory.setNoData("false");
			
		}//end of if
		else{
			promotionHistory.setNoData("true");
			promotionHistory.setPromoList(null);
		}
		list1=null;
		list1= new ArrayList();
		if(data1!=null && data1.length>0){
			for (int j = 0; j < data1.length; j++) {
				PromotionHistory bean = new PromotionHistory();
				bean.setRank(checkNull(String.valueOf(data1[j][0])));
				bean.setDept(checkNull(String.valueOf(data1[j][1])));
				bean.setPromotionDate(checkNull(String.valueOf(data1[j][2])));
				bean.setEfectiveDate(checkNull(String.valueOf(data1[j][3])));
				bean.setOldCtc(checkNull(String.valueOf(data1[j][5])));
				bean.setNewCtc(checkNull(String.valueOf(data1[j][4])));
				list1.add(bean);
				promotionHistory.setPrmlength("true");
			}//end of for loop
			promotionHistory.setHistoryList(list1);
		}else{
			promotionHistory.setPrmlength("false");
			promotionHistory.setHistoryList(null);
		}
	}

	/**
	 * To modify the particular record of an employee.
	 * 
	 * @param promotionHistory
	 * @return String
	 */
	public String modPromotion(PromotionHistory promotionHistory,HttpServletRequest request) {

		String str = "";
		
		try {
			Object modObj[][] = new Object[1][7];
			
			logger.info("===USER LOGIN CODE===="
					+ promotionHistory.getUserEmpId());
			trial = new AuditTrail(promotionHistory.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session);
			trial.setParameters("HRMS_PROMO_HISTORY", new String[] {"PROMO_ID"},
					new String[] { promotionHistory.getParacode() },
					promotionHistory.getEmpId());
			trial.beginTrail();
			modObj[0][0] = promotionHistory.getPostId();
			modObj[0][1] = promotionHistory.getPromotionDate();
			modObj[0][2] = promotionHistory.getDeptId();
			modObj[0][3] = promotionHistory.getCenterNo();
			modObj[0][4] = promotionHistory.getCtc();
			modObj[0][5] = promotionHistory.getParacode();
			modObj[0][6] = promotionHistory.getEmpId();
			boolean result = getSqlModel().singleExecute(getQuery(4), modObj);
			
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeMODTrail(request);
			
			if (result) {
				str = "Record updated Successfully";
			}// end of if
			else {
				str = "Record can not be updated ";
			}// end of else
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in modPromotion---------------"+e);
		}
		return str;
	}

	/**
	 * for selecting the particular record which is going to be modify.
	 * 
	 * @param promotionHistory
	 */
	public void getOneRecord(PromotionHistory promotionHistory) {

		Object[] promoId = new Object[2];
		promoId[0] = promotionHistory.getParacode();
		promoId[1] = promotionHistory.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), promoId);
		promotionHistory.setPost(checkNull(String.valueOf(data[0][0])));
		promotionHistory.setPostId(checkNull(String.valueOf(data[0][1])));
		promotionHistory.setPromotionDate(checkNull(String.valueOf(data[0][2])));
		promotionHistory.setDept(checkNull(String.valueOf(data[0][3])));
		promotionHistory.setDeptId(checkNull(String.valueOf(data[0][4])));
		promotionHistory.setCenterName(checkNull(String.valueOf(data[0][5])));
		promotionHistory.setCenterNo(checkNull(String.valueOf(data[0][6])));
		promotionHistory.setCtc(checkNull(String.valueOf(data[0][7])));
		promotionHistory.setPromoId(promotionHistory.getParacode());
	}

	/**
	 * for deleting the particular record of an employee.
	 * 
	 * @param bean
	 * @return String
	 */
	public String deleteRecord(PromotionHistory bean) {

		Object[][] delObj = new Object[1][2];
		delObj[0][0] = bean.getParacode();
		delObj[0][1] = bean.getEmpId();
		boolean result = getSqlModel().singleExecute(getQuery(5), delObj);
		if (result) {
			return "Record Deleted successfully";
		}//end of if
		else {
			return "Record can not be deleted";
		}//end of else

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

	/** METHOD TO GET IMAGE OF EMPLOYEE
	 * @param promotionHistory
	 */
	public void getImage(PromotionHistory promotionHistory) {

		try {
			String query = "select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="
					+ promotionHistory.getEmpId();

			Object[][] myPics = getSqlModel().getSingleResult(query);
			if (myPics != null && myPics.length > 0) {
				promotionHistory.setUploadFileName(checkNull(String.valueOf(myPics[0][0])));
				promotionHistory.setProfileEmpName(checkNull(String.valueOf(myPics[0][1])) + " "
						+ checkNull(String.valueOf(myPics[0][2])) + " "
						+ checkNull(String.valueOf(myPics[0][3])));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
}
}
