/**
 * 
 */
package org.paradyne.model.D1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.TravelApplicationFormBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author aa1439
 *
 */
public class TravelApplicationFormModel extends ModelBase{
	public void getDefaultManagerName(TravelApplicationFormBean addDet) {
		Object[][] data=null;
		String reportingCode="";
		String query="";
		String query1="";
		
		if(addDet.getDeptName()!=null && !addDet.getDeptName().equals("")) {
			query = " SELECT EMP_REPORTING_OFFICER,EMP_TOKEN,TO_CHAR(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME) from HRMS_EMP_OFFC "
					 + " WHERE EMP_ID="+addDet.getEmployeeCode();
			data= getSqlModel().getSingleResult(query);
			if (data.length > 0) {
					reportingCode=String.valueOf(data[0][0]);
				}
			
			Object[][] data1=null;
			if(reportingCode!=null){
				query1 = " SELECT EMP_TOKEN, TO_CHAR(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME), EMP_ID FROM HRMS_EMP_OFFC "
					 + " WHERE EMP_ID="+reportingCode;
						data1= getSqlModel().getSingleResult(query1);
				if(data1!=null && data1.length>0){
					addDet.setFirstApproverToken(String.valueOf(data1[0][0]));
					addDet.setFirstApproverName(String.valueOf(data1[0][1]));
					addDet.setFirstApproverCode(String.valueOf(data1[0][2]));
					
					if(addDet.getApproverCode().equals(addDet.getFirstApproverCode())) {
						addDet.setApproverToken("");
						addDet.setApproverName("");
					}
				}else{
					addDet.setFirstApproverToken("");
					addDet.setFirstApproverName("");
				}
			}
		}
	}
	
	public void getEmployeeDetails(String empId,TravelApplicationFormBean bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;
			
			
			
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " 
				//+" HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_RANK.RANK_NAME,EMP_ID, EMP_REPORTING_OFFICER "
				+" HRMS_DEPT.DEPT_NAME,HRMS_RANK.RANK_NAME,EMP_ID, EMP_REPORTING_OFFICER "
				+" FROM HRMS_EMP_OFFC "
				//+" Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+" Left join HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+" Left join HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) " 
				+" WHERE HRMS_EMP_OFFC.EMP_ID ="+ empId;

			Object[][] empData = getSqlModel().getSingleResult(query);
			bean.setEmployeeToken(checkNull(String.valueOf(empData[0][0])));
			bean.setEmployeeName(checkNull(String.valueOf(empData[0][1])));
			bean.setDeptName(checkNull(String.valueOf(empData[0][2])));
			bean.setDesignation(checkNull(String.valueOf(empData[0][3])));
			bean.setEmployeeCode(checkNull(String.valueOf(empData[0][4])));// bean.setDeptName(String.valueOf(empData[0][3]));
			bean.setFirstApproverCode(checkNull(String.valueOf(empData[0][5])));
			getManagerName(bean);
			
			
			String dateQuery="SELECT to_char(sysdate,'dd-mm-yyyy HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setCompletedDate(String.valueOf(dateObj[0][0]));
			}
			bean.setCompletedByCode(bean.getEmployeeCode());
			bean.setCompletedBy(bean.getEmployeeName());
			
		} catch (Exception e) {
		}
		
	}
	
	
	
	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
		if(currentUserObj != null && currentUserObj.length > 0) { return true; }
		return false;
	}
	
	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public void getManagerName(TravelApplicationFormBean bean) {
		Object[][] data=null;
		String query="";
		if(bean.getFirstApproverCode()!=null && !bean.getFirstApproverCode().equals("")) {
			query = " SELECT EMP_TOKEN,to_char(EMP_FNAME|| ' '||EMP_MNAME|| ' '||EMP_LNAME),EMP_ID from HRMS_EMP_OFFC "
					 + " where EMP_ID="+bean.getFirstApproverCode();
			data= getSqlModel().getSingleResult(query);
			if (data.length > 0) {
				bean.setFirstApproverToken(checkNull(String.valueOf(data[0][0])));
				bean.setFirstApproverName(checkNull(String.valueOf(data[0][1])));
				bean.setFirstApproverCode(checkNull(String.valueOf(data[0][2])));
				}
		}else{
			bean.setFirstApproverToken("");
			bean.setFirstApproverName("");
			bean.setFirstApproverCode("");
		}
	}
	
	public boolean save(TravelApplicationFormBean bean) {
		boolean result = false;
		try {
			String approverCode="";
			Object insertObj[][] = new Object[1][39];

			insertObj[0][0] = bean.getEmployeeCode().trim();
			insertObj[0][1] = bean.getDateOfAtr().trim();
			insertObj[0][2] = bean.getFromDate().trim();
			insertObj[0][3] = bean.getToDate().trim();
			insertObj[0][4] = bean.getEmpTravel().trim();
			
			if(bean.getCustomer().equals("true") && bean.getCustomer()!=null){
				insertObj[0][5] = "Y";
			}else{
				insertObj[0][5]="N";
			}
			
			if(bean.getManagmentTraining().equals("true") && bean.getManagmentTraining()!=null){
				insertObj[0][6] = "Y";
			}else{
				insertObj[0][6]="N";
			}
			
			if(bean.getOther().equals("true") && bean.getOther()!=null){
				insertObj[0][7] = "Y";
			}else{
				insertObj[0][7]="N";
			}
			if(bean.getTraining().equals("true") && bean.getTraining()!=null){
				insertObj[0][8] = "Y";
			}else{
				insertObj[0][8]="N";
			}
			if(bean.getAcquisition().equals("true") && bean.getAcquisition()!=null){
				insertObj[0][9] = "Y";
			}else{
				insertObj[0][9]="N";
			}
			
			insertObj[0][10] = bean.getComments().trim();
			
			if(bean.getAir().equals("true") && bean.getAir()!=null){
				insertObj[0][11] = "Y";
			}else{
				insertObj[0][11]="N";
			}
			if(bean.getCar().equals("true") && bean.getCar()!=null){
				insertObj[0][12] = "Y";
			}else{
				insertObj[0][12]="N";
			}
			if(bean.getHotel().equals("true") && bean.getHotel()!=null){
				insertObj[0][13] = "Y";
			}else{
				insertObj[0][13]="N";
			}
			
			insertObj[0][14]=bean.getRadioValue().trim();
			//insertObj[0][14]="Y";
			if(bean.getConnections().equals("true") && bean.getConnections()!=null){
				insertObj[0][15] = "Y";
			}else{
				insertObj[0][15]="N";
			}
			
			if(bean.getAtr().equals("true") && bean.getAtr()!=null){
				insertObj[0][16] = "Y";
			}else{
				insertObj[0][16]="N";
			}
			
			if(bean.getTimes().equals("true") && bean.getTimes()!=null){
				insertObj[0][17] = "Y";
			}else{
				insertObj[0][17]="N";
			}
			if(bean.getNotBooked().equals("true") && bean.getNotBooked()!=null){
				insertObj[0][18] = "Y";
			}else{
				insertObj[0][18]="N";
			}
			
			if(bean.getCarrierPreference().equals("true") && bean.getCarrierPreference()!=null){
				insertObj[0][19] = "Y";
			}else{
				insertObj[0][19]="N";
			}
			if(bean.getOtherChk().equals("true") && bean.getOtherChk()!=null){
				insertObj[0][20] = "Y";
			}else{
				insertObj[0][20]="N";
			}
			
			if(bean.getNonRefundable().equals("true") && bean.getNonRefundable()!=null){
				insertObj[0][21] = "Y";
			}else{
				insertObj[0][21]="N";
			}
			insertObj[0][22]=bean.getAirExp().trim();
			insertObj[0][23] =bean.getCarExp().trim();
			insertObj[0][24]=bean.getHotelExp().trim();
			insertObj[0][25] =bean.getMealExp().trim();
			insertObj[0][26] =bean.getOtherExp().trim();
			insertObj[0][27] =bean.getTotalAirExp().trim();
			insertObj[0][28] = bean.getTotalCarExp().trim();
			insertObj[0][29]=bean.getTotalHotelExp().trim();
			insertObj[0][30] =bean.getTotalMealExp().trim();
			insertObj[0][31] =bean.getTotalOtherExp().trim();		
					
			if(!bean.getApproverCode().equals("") && bean.getApproverCode()!=null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			
			insertObj[0][32] = approverCode;
			insertObj[0][33] = "D";
			
			
			//Tracking Number
			String qq="SELECT NVL(MAX(TRAVEL_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(TRAVEL_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_TRAVEL_REQUEST 	";
			Object[][]obj=getSqlModel().getSingleResult(qq);
			if(obj !=null && obj.length>0){
				//bean.setAuthorizedToken(checkNull(String.valueOf(obj[0][0])));
			}
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			String token ="";
			token = tempModel.generateApplicationCode(String.valueOf(obj[0][1]), "D1-AUTHTRVL", bean.getEmployeeCode(), String.valueOf(obj[0][2]));
			bean.setAuthorizedToken(token);
			//End Tracking Number
			
			
			insertObj[0][34] = bean.getAuthorizedToken().trim();
			insertObj[0][35] = bean.getCompletedByCode().trim();
			insertObj[0][36] = bean.getDestination().trim();
			insertObj[0][37] = bean.getGeneralComments().trim();
			insertObj[0][38] = bean.getDeptName().trim();

			String insertQuery = "INSERT INTO HRMS_D1_TRAVEL_REQUEST(TRAVEL_ID, TRAVEL_EMP_ID, TRAVEL_ATR_DATE, TRAVEL_FROM_DATE, " 
			 +" TRAVEL_TO_DATE, TRAVEL_EMPLOYEE_TRAVELING, TRAVEL_JUSTIFICATION_CUST, TRAVEL_JUSTIFICATION_MGM," 
			 +" TRAVEL_JUSTIFICATION_OTHER, TRAVEL_JUSTIFICATION_TRAINING, TRAVEL_JUSTIFICATION_ACQ, TRAVEL_JUSTIFICATION_COMMENTS, " 
			 +" TRAVEL_DIRECT_AIR, TRAVEL_DIRECT_CAR, TRAVEL_DIRECT_HOTEL, TRAVEL_DIRECT_LOWEST_COST, TRAVEL_POLICY_CONN, TRAVEL_POLICY_ATR, " 
			 +" TRAVEL_POLICY_TIMES, TRAVEL_POLICY_NON_BOOKED, TRAVEL_POLICY_CARRIER, TRAVEL_POLICY_OTHER, TRAVEL_POLICY_NON_REFUND," 
			 +" TRAVEL_EXPENSE_AIR, TRAVEL_EXPENSE_CAR, TRAVEL_EXPENSE_HOTEL, TRAVEL_EXPENSE_MEAL, TRAVEL_EXPENSE_OTHER, TRAVEL_EXPENSE_AIR_TOTAL, " 
			 +" TRAVEL_EXPENSE_CAR_TOTAL, TRAVEL_EXPENSE_HOTEL_TOTAL, TRAVEL_EXPENSE_MEAL_TOTAL, TRAVEL_EXPENSE_OTHER_TOTAL,  "
			 +" TRAVEL_APPROVER_CODE,TRAVEL_APPROVER_STATUS, TRAVEL_APPLICATION_DATE,TRAVEL_FILE_HEADER_NAME,TRAVEL_INITIATOR,TRAVEL_DESTINATION, TRAVEL_COMMENTS,TRAVEL_DEPT_NAME) "
			 +" VALUES((SELECT NVL(MAX(TRAVEL_ID),0)+1 FROM HRMS_D1_TRAVEL_REQUEST),?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?) ";
			
			result = getSqlModel().singleExecute(insertQuery, insertObj);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public boolean update(TravelApplicationFormBean bean) {
		boolean result = false;
		try {
			
			String approverCode="";
			Object updateObj[][] = new Object[1][40];

			updateObj[0][0] = bean.getEmployeeCode().trim();
			updateObj[0][1] = bean.getDateOfAtr().trim();
			updateObj[0][2] = bean.getFromDate().trim();
			updateObj[0][3] = bean.getToDate().trim();
			updateObj[0][4] = bean.getEmpTravel().trim();
			
			if(bean.getCustomer().equals("true") && bean.getCustomer()!=null){
				updateObj[0][5] = "Y";
			}else{
				updateObj[0][5]="N";
			}
			
			if(bean.getManagmentTraining().equals("true") && bean.getManagmentTraining()!=null){
				updateObj[0][6] = "Y";
			}else{
				updateObj[0][6]="N";
			}
			
			if(bean.getOther().equals("true") && bean.getOther()!=null){
				updateObj[0][7] = "Y";
			}else{
				updateObj[0][7]="N";
			}
			if(bean.getTraining().equals("true") && bean.getTraining()!=null){
				updateObj[0][8] = "Y";
			}else{
				updateObj[0][8]="N";
			}
			if(bean.getAcquisition().equals("true") && bean.getAcquisition()!=null){
				updateObj[0][9] = "Y";
			}else{
				updateObj[0][9]="N";
			}
			
			updateObj[0][10] = bean.getComments();
			
			if(bean.getAir().equals("true") && bean.getAir()!=null){
				updateObj[0][11] = "Y";
			}else{
				updateObj[0][11]="N";
			}
			if(bean.getCar().equals("true") && bean.getCar()!=null){
				updateObj[0][12] = "Y";
			}else{
				updateObj[0][12]="N";
			}
			if(bean.getHotel().equals("true") && bean.getHotel()!=null){
				updateObj[0][13] = "Y";
			}else{
				updateObj[0][13]="N";
			}
			
			updateObj[0][14]=bean.getRadioValue().trim();
			//updateObj[0][14]="Y";
			if(bean.getConnections().equals("true") && bean.getConnections()!=null){
				updateObj[0][15] = "Y";
			}else{
				updateObj[0][15]="N";
			}
			
			if(bean.getAtr().equals("true") && bean.getAtr()!=null){
				updateObj[0][16] = "Y";
			}else{
				updateObj[0][16]="N";
			}
			
			if(bean.getTimes().equals("true") && bean.getTimes()!=null){
				updateObj[0][17] = "Y";
			}else{
				updateObj[0][17]="N";
			}
			if(bean.getNotBooked().equals("true") && bean.getNotBooked()!=null){
				updateObj[0][18] = "Y";
			}else{
				updateObj[0][18]="N";
			}
			
			if(bean.getCarrierPreference().equals("true") && bean.getCarrierPreference()!=null){
				updateObj[0][19] = "Y";
			}else{
				updateObj[0][19]="N";
			}
			if(bean.getOtherChk().equals("true") && bean.getOtherChk()!=null){
				updateObj[0][20] = "Y";
			}else{
				updateObj[0][20]="N";
			}
			
			if(bean.getNonRefundable().equals("true") && bean.getNonRefundable()!=null){
				updateObj[0][21] = "Y";
			}else{
				updateObj[0][21]="N";
			}
			updateObj[0][22]=bean.getAirExp().trim();
			updateObj[0][23] =bean.getCarExp().trim();
			updateObj[0][24]=bean.getHotelExp().trim();
			updateObj[0][25] =bean.getMealExp().trim();
			updateObj[0][26] =bean.getOtherExp().trim();
			updateObj[0][27] =bean.getTotalAirExp().trim();
			updateObj[0][28] = bean.getTotalCarExp().trim();
			updateObj[0][29]=bean.getTotalHotelExp().trim();
			updateObj[0][30] =bean.getTotalMealExp().trim();
			updateObj[0][31] =bean.getTotalOtherExp().trim();		
					
			if(!bean.getApproverCode().equals("") && bean.getApproverCode()!=null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			
			updateObj[0][32] = approverCode;
			updateObj[0][33] = "D";
			updateObj[0][34]=bean.getAuthorizedToken().trim();
			updateObj[0][35]=bean.getCompletedByCode().trim();
			
			updateObj[0][36] = bean.getDestination().trim();
			updateObj[0][37] = bean.getGeneralComments().trim();
			updateObj[0][38] = bean.getDeptName().trim();
			
			updateObj[0][39]=bean.getTravelID();
			
			String updateQuery = "UPDATE HRMS_D1_TRAVEL_REQUEST SET TRAVEL_EMP_ID=?, TRAVEL_ATR_DATE=to_date(?,'DD-MM-YYYY'), TRAVEL_FROM_DATE=to_date(?,'DD-MM-YYYY'), " 
				 +" TRAVEL_TO_DATE=to_date(?,'DD-MM-YYYY'), TRAVEL_EMPLOYEE_TRAVELING=?, TRAVEL_JUSTIFICATION_CUST=?, TRAVEL_JUSTIFICATION_MGM=?," 
				 +" TRAVEL_JUSTIFICATION_OTHER=?, TRAVEL_JUSTIFICATION_TRAINING=?, TRAVEL_JUSTIFICATION_ACQ=?, TRAVEL_JUSTIFICATION_COMMENTS=?, " 
				 +" TRAVEL_DIRECT_AIR=?, TRAVEL_DIRECT_CAR=?, TRAVEL_DIRECT_HOTEL=?, TRAVEL_DIRECT_LOWEST_COST=?, TRAVEL_POLICY_CONN=?, TRAVEL_POLICY_ATR=?, " 
				 +" TRAVEL_POLICY_TIMES=?, TRAVEL_POLICY_NON_BOOKED=?, TRAVEL_POLICY_CARRIER=?, TRAVEL_POLICY_OTHER=?, TRAVEL_POLICY_NON_REFUND=?," 
				 +" TRAVEL_EXPENSE_AIR=?, TRAVEL_EXPENSE_CAR=?, TRAVEL_EXPENSE_HOTEL=?, TRAVEL_EXPENSE_MEAL=?, TRAVEL_EXPENSE_OTHER=?, TRAVEL_EXPENSE_AIR_TOTAL=?, " 
				 +" TRAVEL_EXPENSE_CAR_TOTAL=?, TRAVEL_EXPENSE_HOTEL_TOTAL=?, TRAVEL_EXPENSE_MEAL_TOTAL=?, TRAVEL_EXPENSE_OTHER_TOTAL=?,  "
				 +" TRAVEL_APPROVER_CODE=?,TRAVEL_APPROVER_STATUS=?,TRAVEL_FILE_HEADER_NAME=?,TRAVEL_INITIATOR =? ,TRAVEL_DESTINATION =? , TRAVEL_COMMENTS =? , TRAVEL_DEPT_NAME = ? "
				 + " WHERE TRAVEL_ID =? ";
			
			result = getSqlModel().singleExecute(updateQuery, updateObj);

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//Send for Approval
	public boolean sendForApproval(TravelApplicationFormBean bean) {
		boolean result = false;
		try {
			String approverCode="";
			Object insertObj[][] = new Object[1][39];

			insertObj[0][0] = bean.getEmployeeCode().trim();
			insertObj[0][1] = bean.getDateOfAtr().trim();
			insertObj[0][2] = bean.getFromDate().trim();
			insertObj[0][3] = bean.getToDate().trim();
			insertObj[0][4] = bean.getEmpTravel().trim();
			
			if(bean.getCustomer().equals("true") && bean.getCustomer()!=null){
				insertObj[0][5] = "Y";
			}else{
				insertObj[0][5]="N";
			}
			
			if(bean.getManagmentTraining().equals("true") && bean.getManagmentTraining()!=null){
				insertObj[0][6] = "Y";
			}else{
				insertObj[0][6]="N";
			}
			
			if(bean.getOther().equals("true") && bean.getOther()!=null){
				insertObj[0][7] = "Y";
			}else{
				insertObj[0][7]="N";
			}
			if(bean.getTraining().equals("true") && bean.getTraining()!=null){
				insertObj[0][8] = "Y";
			}else{
				insertObj[0][8]="N";
			}
			if(bean.getAcquisition().equals("true") && bean.getAcquisition()!=null){
				insertObj[0][9] = "Y";
			}else{
				insertObj[0][9]="N";
			}
			
			insertObj[0][10] = bean.getComments().trim();
			
			if(bean.getAir().equals("true") && bean.getAir()!=null){
				insertObj[0][11] = "Y";
			}else{
				insertObj[0][11]="N";
			}
			if(bean.getCar().equals("true") && bean.getCar()!=null){
				insertObj[0][12] = "Y";
			}else{
				insertObj[0][12]="N";
			}
			if(bean.getHotel().equals("true") && bean.getHotel()!=null){
				insertObj[0][13] = "Y";
			}else{
				insertObj[0][13]="N";
			}
			
			insertObj[0][14]=bean.getRadioValue().trim();
			//insertObj[0][14]="Y";
			if(bean.getConnections().equals("true") && bean.getConnections()!=null){
				insertObj[0][15] = "Y";
			}else{
				insertObj[0][15]="N";
			}
			
			if(bean.getAtr().equals("true") && bean.getAtr()!=null){
				insertObj[0][16] = "Y";
			}else{
				insertObj[0][16]="N";
			}
			
			if(bean.getTimes().equals("true") && bean.getTimes()!=null){
				insertObj[0][17] = "Y";
			}else{
				insertObj[0][17]="N";
			}
			if(bean.getNotBooked().equals("true") && bean.getNotBooked()!=null){
				insertObj[0][18] = "Y";
			}else{
				insertObj[0][18]="N";
			}
			
			if(bean.getCarrierPreference().equals("true") && bean.getCarrierPreference()!=null){
				insertObj[0][19] = "Y";
			}else{
				insertObj[0][19]="N";
			}
			if(bean.getOtherChk().equals("true") && bean.getOtherChk()!=null){
				insertObj[0][20] = "Y";
			}else{
				insertObj[0][20]="N";
			}
			
			if(bean.getNonRefundable().equals("true") && bean.getNonRefundable()!=null){
				insertObj[0][21] = "Y";
			}else{
				insertObj[0][21]="N";
			}
			insertObj[0][22]=bean.getAirExp().trim();
			insertObj[0][23] =bean.getCarExp().trim();
			insertObj[0][24]=bean.getHotelExp().trim();
			insertObj[0][25] =bean.getMealExp().trim();
			insertObj[0][26] =bean.getOtherExp().trim();
			insertObj[0][27] =bean.getTotalAirExp().trim();
			insertObj[0][28] = bean.getTotalCarExp().trim();
			insertObj[0][29]=bean.getTotalHotelExp().trim();
			insertObj[0][30] =bean.getTotalMealExp().trim();
			insertObj[0][31] =bean.getTotalOtherExp().trim();		
					
			if(!bean.getApproverCode().equals("") && bean.getApproverCode()!=null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			
			insertObj[0][32] = approverCode;
			insertObj[0][33] = "P";
			//Tracking Number
			String qq="SELECT NVL(MAX(TRAVEL_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(TRAVEL_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_TRAVEL_REQUEST 	";
			Object[][]obj=getSqlModel().getSingleResult(qq);
			if(obj !=null && obj.length>0){
				//bean.setAuthorizedToken(checkNull(String.valueOf(obj[0][0])));
			}
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			String token ="";
			token = tempModel.generateApplicationCode(String.valueOf(obj[0][1]), "D1-AUTHTRVL", bean.getEmployeeCode(), String.valueOf(obj[0][2]));
			bean.setAuthorizedToken(token);
			//End Tracking Number
			
			insertObj[0][34]=bean.getAuthorizedToken().trim();
			insertObj[0][35]=bean.getCompletedByCode().trim();
			//added ganesg start
			insertObj[0][36]=bean.getDestination().trim();
			insertObj[0][37]=bean.getGeneralComments().trim();
			insertObj[0][38]=bean.getDeptName().trim();
			//added ganesg end
			String insertQuery = "INSERT INTO HRMS_D1_TRAVEL_REQUEST(TRAVEL_ID, TRAVEL_EMP_ID, TRAVEL_ATR_DATE, TRAVEL_FROM_DATE, " 
			 +" TRAVEL_TO_DATE, TRAVEL_EMPLOYEE_TRAVELING, TRAVEL_JUSTIFICATION_CUST, TRAVEL_JUSTIFICATION_MGM," 
			 +" TRAVEL_JUSTIFICATION_OTHER, TRAVEL_JUSTIFICATION_TRAINING, TRAVEL_JUSTIFICATION_ACQ, TRAVEL_JUSTIFICATION_COMMENTS, " 
			 +" TRAVEL_DIRECT_AIR, TRAVEL_DIRECT_CAR, TRAVEL_DIRECT_HOTEL, TRAVEL_DIRECT_LOWEST_COST, TRAVEL_POLICY_CONN, TRAVEL_POLICY_ATR, " 
			 +" TRAVEL_POLICY_TIMES, TRAVEL_POLICY_NON_BOOKED, TRAVEL_POLICY_CARRIER, TRAVEL_POLICY_OTHER, TRAVEL_POLICY_NON_REFUND," 
			 +" TRAVEL_EXPENSE_AIR, TRAVEL_EXPENSE_CAR, TRAVEL_EXPENSE_HOTEL, TRAVEL_EXPENSE_MEAL, TRAVEL_EXPENSE_OTHER, TRAVEL_EXPENSE_AIR_TOTAL, " 
			 +" TRAVEL_EXPENSE_CAR_TOTAL, TRAVEL_EXPENSE_HOTEL_TOTAL, TRAVEL_EXPENSE_MEAL_TOTAL, TRAVEL_EXPENSE_OTHER_TOTAL,  "
			 +" TRAVEL_APPROVER_CODE,TRAVEL_APPROVER_STATUS, TRAVEL_APPLICATION_DATE,TRAVEL_FILE_HEADER_NAME,TRAVEL_INITIATOR,TRAVEL_DESTINATION, TRAVEL_COMMENTS,TRAVEL_DEPT_NAME ) "
			 +" VALUES((SELECT NVL(MAX(TRAVEL_ID),0)+1 FROM HRMS_D1_TRAVEL_REQUEST),?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?) ";
			
			result = getSqlModel().singleExecute(insertQuery, insertObj);
			
			
			String selectId="Select max(TRAVEL_ID) from HRMS_D1_TRAVEL_REQUEST ";
			Object[][] data = getSqlModel().getSingleResult(selectId);
			if(data!=null && data.length >0){
				bean.setTravelID(String.valueOf(data[0][0]));
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public boolean updateSendForApproval(TravelApplicationFormBean bean) {
		boolean result = false;
		try {
			
			String approverCode="";
			Object updateObj[][] = new Object[1][40];

			updateObj[0][0] = bean.getEmployeeCode().trim();
			updateObj[0][1] = bean.getDateOfAtr().trim();
			updateObj[0][2] = bean.getFromDate().trim();
			updateObj[0][3] = bean.getToDate().trim();
			updateObj[0][4] = bean.getEmpTravel().trim();
			
			if(bean.getCustomer().equals("true") && bean.getCustomer()!=null){
				updateObj[0][5] = "Y";
			}else{
				updateObj[0][5]="N";
			}
			
			if(bean.getManagmentTraining().equals("true") && bean.getManagmentTraining()!=null){
				updateObj[0][6] = "Y";
			}else{
				updateObj[0][6]="N";
			}
			
			if(bean.getOther().equals("true") && bean.getOther()!=null){
				updateObj[0][7] = "Y";
			}else{
				updateObj[0][7]="N";
			}
			if(bean.getTraining().equals("true") && bean.getTraining()!=null){
				updateObj[0][8] = "Y";
			}else{
				updateObj[0][8]="N";
			}
			if(bean.getAcquisition().equals("true") && bean.getAcquisition()!=null){
				updateObj[0][9] = "Y";
			}else{
				updateObj[0][9]="N";
			}
			
			updateObj[0][10] = bean.getComments();
			
			if(bean.getAir().equals("true") && bean.getAir()!=null){
				updateObj[0][11] = "Y";
			}else{
				updateObj[0][11]="N";
			}
			if(bean.getCar().equals("true") && bean.getCar()!=null){
				updateObj[0][12] = "Y";
			}else{
				updateObj[0][12]="N";
			}
			if(bean.getHotel().equals("true") && bean.getHotel()!=null){
				updateObj[0][13] = "Y";
			}else{
				updateObj[0][13]="N";
			}
			
			updateObj[0][14]=bean.getRadioValue().trim();
			//updateObj[0][14]="Y";
			if(bean.getConnections().equals("true") && bean.getConnections()!=null){
				updateObj[0][15] = "Y";
			}else{
				updateObj[0][15]="N";
			}
			
			if(bean.getAtr().equals("true") && bean.getAtr()!=null){
				updateObj[0][16] = "Y";
			}else{
				updateObj[0][16]="N";
			}
			
			if(bean.getTimes().equals("true") && bean.getTimes()!=null){
				updateObj[0][17] = "Y";
			}else{
				updateObj[0][17]="N";
			}
			if(bean.getNotBooked().equals("true") && bean.getNotBooked()!=null){
				updateObj[0][18] = "Y";
			}else{
				updateObj[0][18]="N";
			}
			
			if(bean.getCarrierPreference().equals("true") && bean.getCarrierPreference()!=null){
				updateObj[0][19] = "Y";
			}else{
				updateObj[0][19]="N";
			}
			if(bean.getOtherChk().equals("true") && bean.getOtherChk()!=null){
				updateObj[0][20] = "Y";
			}else{
				updateObj[0][20]="N";
			}
			
			if(bean.getNonRefundable().equals("true") && bean.getNonRefundable()!=null){
				updateObj[0][21] = "Y";
			}else{
				updateObj[0][21]="N";
			}
			updateObj[0][22]=bean.getAirExp().trim();
			updateObj[0][23] =bean.getCarExp().trim();
			updateObj[0][24]=bean.getHotelExp().trim();
			updateObj[0][25] =bean.getMealExp().trim();
			updateObj[0][26] =bean.getOtherExp().trim();
			updateObj[0][27] =bean.getTotalAirExp().trim();
			updateObj[0][28] = bean.getTotalCarExp().trim();
			updateObj[0][29]=bean.getTotalHotelExp().trim();
			updateObj[0][30] =bean.getTotalMealExp().trim();
			updateObj[0][31] =bean.getTotalOtherExp().trim();		
					
			if(!bean.getApproverCode().equals("") && bean.getApproverCode()!=null) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			
			updateObj[0][32] = approverCode;
			updateObj[0][33] = "P";
			updateObj[0][34]=bean.getAuthorizedToken().trim();
			updateObj[0][35]=bean.getCompletedByCode().trim();
			//added ganesh start
			updateObj[0][36]=bean.getDestination().trim();
			updateObj[0][37]=bean.getGeneralComments().trim();
			updateObj[0][38]=bean.getDeptName().trim();
			//added ganesh end
			updateObj[0][39]=bean.getTravelID();
			
			String updateQuery = "UPDATE HRMS_D1_TRAVEL_REQUEST SET TRAVEL_EMP_ID=?, TRAVEL_ATR_DATE=to_date(?,'DD-MM-YYYY'), TRAVEL_FROM_DATE=to_date(?,'DD-MM-YYYY'), " 
				 +" TRAVEL_TO_DATE=to_date(?,'DD-MM-YYYY'), TRAVEL_EMPLOYEE_TRAVELING=?, TRAVEL_JUSTIFICATION_CUST=?, TRAVEL_JUSTIFICATION_MGM=?," 
				 +" TRAVEL_JUSTIFICATION_OTHER=?, TRAVEL_JUSTIFICATION_TRAINING=?, TRAVEL_JUSTIFICATION_ACQ=?, TRAVEL_JUSTIFICATION_COMMENTS=?, " 
				 +" TRAVEL_DIRECT_AIR=?, TRAVEL_DIRECT_CAR=?, TRAVEL_DIRECT_HOTEL=?, TRAVEL_DIRECT_LOWEST_COST=?, TRAVEL_POLICY_CONN=?, TRAVEL_POLICY_ATR=?, " 
				 +" TRAVEL_POLICY_TIMES=?, TRAVEL_POLICY_NON_BOOKED=?, TRAVEL_POLICY_CARRIER=?, TRAVEL_POLICY_OTHER=?, TRAVEL_POLICY_NON_REFUND=?," 
				 +" TRAVEL_EXPENSE_AIR=?, TRAVEL_EXPENSE_CAR=?, TRAVEL_EXPENSE_HOTEL=?, TRAVEL_EXPENSE_MEAL=?, TRAVEL_EXPENSE_OTHER=?, TRAVEL_EXPENSE_AIR_TOTAL=?, " 
				 +" TRAVEL_EXPENSE_CAR_TOTAL=?, TRAVEL_EXPENSE_HOTEL_TOTAL=?, TRAVEL_EXPENSE_MEAL_TOTAL=?, TRAVEL_EXPENSE_OTHER_TOTAL=?,  "
				 +" TRAVEL_APPROVER_CODE=?,TRAVEL_APPROVER_STATUS=? ,TRAVEL_FILE_HEADER_NAME=? ,TRAVEL_INITIATOR =? , TRAVEL_DESTINATION =? , TRAVEL_COMMENTS =? ,TRAVEL_DEPT_NAME = ? "
				 + " WHERE TRAVEL_ID =? ";
			
			result = getSqlModel().singleExecute(updateQuery, updateObj);

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//List
	public void getPendingList(TravelApplicationFormBean bean, HttpServletRequest request, String userId) {
		try {
			Object[][] draftListData = null;
			ArrayList draftList = new ArrayList();

			Object[][] inProcessListData = null;
			
			Object[][] sentBackListData = null;
			ArrayList sentBackVoucherList = new ArrayList();

			// For drafted application Begins
			String selQuery = "SELECT TRAVEL_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'), TRAVEL_APPROVER_STATUS, " + 
							  " TRAVEL_ID,TRAVEL_FILE_HEADER_NAME, HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') " +				
							  " FROM HRMS_D1_TRAVEL_REQUEST " +
							  " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) " +
							  " WHERE TRAVEL_APPROVER_STATUS  = 'D' AND TRAVEL_INITIATOR = " + userId + " " + "ORDER BY TRAVEL_APPLICATION_DATE DESC ";
			  

			draftListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), draftListData.length, 20);
			if(pageIndexDrafted == null) {				
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if(pageIndexDrafted[4].equals("1")) bean.setMyPage("1");

			if(draftListData != null && draftListData.length > 0) {
			
				
				for(int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					TravelApplicationFormBean beanItt = new TravelApplicationFormBean();
					beanItt.setEmpToken(checkNull(String.valueOf(draftListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(draftListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(draftListData[i][4])));
					beanItt.setTravelID(checkNull(String.valueOf(draftListData[i][5])));
					beanItt.setAuthorizedToken(checkNull(String.valueOf(draftListData[i][6])));
					beanItt.setDestinationItr(checkNull(String.valueOf(draftListData[i][7])));
					beanItt.setFromTravelDateItr(checkNull(String.valueOf(draftListData[i][8])));
					
					draftList.add(beanItt);
				}
				bean.setDraftListLength(true);
				bean.setDraftList(draftList);
			}
			// For drafted application Ends

			// For in-Process application Begins
			String pendingQuery = "SELECT TRAVEL_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'), TRAVEL_APPROVER_STATUS " 
				+ " ,TRAVEL_ID,TRAVEL_FILE_HEADER_NAME, HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY')  FROM HRMS_D1_TRAVEL_REQUEST "
				+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) "
				+ " WHERE TRAVEL_APPROVER_STATUS  IN( 'P','F','S','X') AND TRAVEL_INITIATOR = " + userId + " " + "ORDER BY TRAVEL_APPLICATION_DATE DESC ";

			inProcessListData = getSqlModel().getSingleResult(pendingQuery);
			String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(), inProcessListData.length, 20);
			if(pageIndexDrafted == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if(pageIndexInProcess[4].equals("1")) bean.setMyPageInProcess("1");

			if(inProcessListData != null && inProcessListData.length > 0) {
				ArrayList<TravelApplicationFormBean> inProcessList = new ArrayList();
				
				for(int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					TravelApplicationFormBean beanItt1 = new TravelApplicationFormBean();
					beanItt1.setEmpToken(checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt1.setEmpName(checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt1.setApplicationDate(checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt1.setApplnStatus(checkNull(String.valueOf(inProcessListData[i][4])));
					beanItt1.setTravelID(checkNull(String.valueOf(inProcessListData[i][5])));
					beanItt1.setAuthorizedToken(checkNull(String.valueOf(inProcessListData[i][6])));
					beanItt1.setDestinationItr(checkNull(String.valueOf(inProcessListData[i][7])));
					beanItt1.setFromTravelDateItr(checkNull(String.valueOf(inProcessListData[i][8])));
					inProcessList.add(beanItt1);
				}
				//bean.setInProcessList(inProcessList);
				bean.setInProcessListLength(true);
				bean.setApplicationList(inProcessList);
			}
			
			// For in-Process application Ends

			// Sent-Back application Begins

			String sentBackQuery = "SELECT TRAVEL_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'), TRAVEL_APPROVER_STATUS " 
				+ " ,TRAVEL_ID,TRAVEL_FILE_HEADER_NAME, HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') FROM HRMS_D1_TRAVEL_REQUEST "
				+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) "
				+ " WHERE TRAVEL_APPROVER_STATUS  = 'B' AND TRAVEL_INITIATOR = " + userId + " " + "ORDER BY TRAVEL_APPLICATION_DATE DESC ";
			
			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);

			String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(), sentBackListData.length, 20);
			if(pageIndexSentBack == null) {
				pageIndexSentBack[0] = "0";
				pageIndexSentBack[1] = "20";
				pageIndexSentBack[2] = "1";
				pageIndexSentBack[3] = "1";
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String.valueOf(pageIndexSentBack[3])));
			if(pageIndexSentBack[4].equals("1")) bean.setMyPageSentBack("1");

			if(sentBackListData != null && sentBackListData.length > 0) {
				
				for(int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
					TravelApplicationFormBean beanItt = new TravelApplicationFormBean();
					beanItt.setEmpToken(checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(sentBackListData[i][4])));
					beanItt.setTravelID(checkNull(String.valueOf(sentBackListData[i][5])));
					beanItt.setAuthorizedToken(checkNull(String.valueOf(sentBackListData[i][6])));
					beanItt.setDestinationItr(checkNull(String.valueOf(sentBackListData[i][7])));
					beanItt.setFromTravelDateItr(checkNull(String.valueOf(sentBackListData[i][8])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSendBackLength(true);
				bean.setSendBackList(sentBackVoucherList);
			}
			// Sent-Back application Ends

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	//End List
	
	
public Object[][] getApproverCommentList(String travelId) {
		
		String apprCommentListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, NVL(TRAVEL_COMMENTS,''), "
			+ " TO_CHAR(TRAVEL_DATE, 'DD-MM-YYYY') AS TRAVEL_DATE, "
			+ " DECODE(TRAVEL_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') " 
			+ " AS STATUS "
			+ " FROM HRMS_D1_TRAVEL_DATA_PATH PATH   "
			+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.TRAVEL_APPROVER) "
			+ " WHERE TRAVEL_ID = " + travelId + " ORDER BY TRAVEL_ID DESC ";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}


public void view(TravelApplicationFormBean bean, HttpServletRequest request, String cdromID) {
	try {
		String query = " SELECT TRAVEL_ID, TRAVEL_EMP_ID, to_char(TRAVEL_ATR_DATE,'dd-mm-yyyy'),  to_char(TRAVEL_FROM_DATE,'dd-mm-yyyy'),  to_char(TRAVEL_TO_DATE,'dd-mm-yyyy'), "
			+" TRAVEL_EMPLOYEE_TRAVELING, TRAVEL_JUSTIFICATION_CUST, TRAVEL_JUSTIFICATION_MGM,  "
			+" TRAVEL_JUSTIFICATION_OTHER, TRAVEL_JUSTIFICATION_TRAINING, TRAVEL_JUSTIFICATION_ACQ,  "
			+" TRAVEL_JUSTIFICATION_COMMENTS, TRAVEL_DIRECT_AIR, TRAVEL_DIRECT_CAR, TRAVEL_DIRECT_HOTEL,  "
			+" TRAVEL_DIRECT_LOWEST_COST, TRAVEL_POLICY_CONN, TRAVEL_POLICY_ATR, TRAVEL_POLICY_TIMES,  "
			+" TRAVEL_POLICY_NON_BOOKED, TRAVEL_POLICY_CARRIER, TRAVEL_POLICY_OTHER, TRAVEL_POLICY_NON_REFUND,  "
			+" TRAVEL_EXPENSE_AIR, TRAVEL_EXPENSE_CAR, TRAVEL_EXPENSE_HOTEL, TRAVEL_EXPENSE_MEAL, TRAVEL_EXPENSE_OTHER,  "
			+" TRAVEL_EXPENSE_AIR_TOTAL, TRAVEL_EXPENSE_CAR_TOTAL, TRAVEL_EXPENSE_HOTEL_TOTAL, TRAVEL_EXPENSE_MEAL_TOTAL,  "
			+" TRAVEL_EXPENSE_OTHER_TOTAL, TRAVEL_APPROVER_CODE,OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,  TRAVEL_APPROVER_COMMENTS, TRAVEL_APPROVER_STATUS,  "
			+" to_char(TRAVEL_APPLICATION_DATE,'dd-mm-yyyy HH24:MI'), TRAVEL_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN , "
			+" To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME), "
			//+" HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_RANK.RANK_NAME,hrms_emp_offc.EMP_ID,TRAVEL_TP_EMAIL,TRAVEL_FILE_HEADER_NAME ,TRAVEL_INITIATOR  "
			+" TRAVEL_DEPT_NAME,HRMS_RANK.RANK_NAME,hrms_emp_offc.EMP_ID,TRAVEL_TP_EMAIL,TRAVEL_FILE_HEADER_NAME ,TRAVEL_INITIATOR,TRAVEL_DESTINATION, TRAVEL_COMMENTS  "
			+" FROM HRMS_D1_TRAVEL_REQUEST "
			+" inner join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID)  "
			//+" Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) "
			+" Left join HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
			+" Left join HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
			+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPROVER_CODE)  " 
			+ " where  TRAVEL_ID="+cdromID;

		Object[][] data = getSqlModel().getSingleResult(query);

		try {
			bean.setTravelID(checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeCode(checkNull(String.valueOf(data[0][1])));
			bean.setDateOfAtr(checkNull(String.valueOf(data[0][2])));
			bean.setFromDate(checkNull(String.valueOf(data[0][3])));
			bean.setToDate(checkNull(String.valueOf(data[0][4])));
			bean.setEmpTravel(checkNull(String.valueOf(data[0][5])));
			
			if(String.valueOf(data[0][6]).equals("Y")){
				bean.setCustomer("true");
			}else{
				bean.setCustomer("false");
			}
			if(String.valueOf(data[0][7]).equals("Y")){
				bean.setManagmentTraining("true");
			}else{
				bean.setManagmentTraining("false");
			}
			if(String.valueOf(data[0][8]).equals("Y")){
				bean.setOther("true");
			}else{
				bean.setOther("false");
			}
			if(String.valueOf(data[0][9]).equals("Y")){
				bean.setTraining("true");
			}else{
				bean.setTraining("false");
			}
			if(String.valueOf(data[0][10]).equals("Y")){
				bean.setAcquisition("true");
			}else{
				bean.setAcquisition("false");
			}
			bean.setComments(checkNull(String.valueOf(data[0][11])));
			if(String.valueOf(data[0][12]).equals("Y")){
				bean.setAir("true");
			}else{
				bean.setAir("false");
			}
			
			if(String.valueOf(data[0][13]).equals("Y")){
				bean.setCar("true");
			}else{
				bean.setCar("false");
			}
			if(String.valueOf(data[0][14]).equals("Y")){
				bean.setHotel("true");
			}else{
				bean.setHotel("false");
			}
			
			if(String.valueOf(data[0][15]).equals("Y")){
				bean.setRadioValue(checkNull(String.valueOf(data[0][15])));
				request.setAttribute("radioValueYesNo", String.valueOf(data[0][15]));
			}else if(String.valueOf(data[0][15]).equals("N")){
				bean.setRadioValue(checkNull(String.valueOf(data[0][15])));
				request.setAttribute("radioValueYesNo", String.valueOf(data[0][15]));
			}
			
			
			if(String.valueOf(data[0][16]).equals("Y")){
				bean.setConnections("true");
			}else{
				bean.setConnections("false");
			}
			if(String.valueOf(data[0][17]).equals("Y")){
				bean.setAtr("true");
			}else{
				bean.setAtr("false");
			}
			
			if(String.valueOf(data[0][18]).equals("Y")){
				bean.setTimes("true");
			}else{
				bean.setTimes("false");
			}
			if(String.valueOf(data[0][19]).equals("Y")){
				bean.setNotBooked("true");
			}else{
				bean.setNotBooked("false");
			}
			
			if(String.valueOf(data[0][20]).equals("Y")){
				bean.setCarrierPreference("true");
			}else{
				bean.setCarrierPreference("false");
			}
			if(String.valueOf(data[0][21]).equals("Y")){
				bean.setOtherChk("true");
			}else{
				bean.setOtherChk("false");
			}
			if(String.valueOf(data[0][22]).equals("Y")){
				bean.setNonRefundable("true");
			}else{
				bean.setNonRefundable("false");
			}
			if(String.valueOf(data[0][23])!=null && !String.valueOf(data[0][23]).equals("null")){
				bean.setAirExp(checkNull(String.valueOf(data[0][23])));
			}else{
				bean.setAirExp("0");
			}
			
			if(String.valueOf(data[0][24])!=null && !String.valueOf(data[0][24]).equals("null")){
				bean.setCarExp(checkNull(String.valueOf(data[0][24])));
			}else{
				bean.setCarExp("0");
			}
			
			if(String.valueOf(data[0][25])!=null && !String.valueOf(data[0][25]).equals("null")){
				bean.setHotelExp(checkNull(String.valueOf(data[0][25])));
			}else{
				bean.setHotelExp("0");
			}
			
			if(String.valueOf(data[0][26])!=null && !String.valueOf(data[0][26]).equals("null")){
				bean.setMealExp(checkNull(String.valueOf(data[0][26])));
			}else{
				bean.setMealExp("0");
			}
			if(String.valueOf(data[0][27])!=null && !String.valueOf(data[0][27]).equals("null")){
				bean.setOtherExp(checkNull(String.valueOf(data[0][27])));	
			}else{
				bean.setOtherExp("0");
			}
				
			if(String.valueOf(data[0][28])!=null && !String.valueOf(data[0][28]).equals("null")){
				bean.setTotalAirExp(checkNull(String.valueOf(data[0][28])));
			}else{
				bean.setTotalAirExp("0");
			}
			
			if(String.valueOf(data[0][29])!=null && !String.valueOf(data[0][29]).equals("null")){
				bean.setTotalCarExp(checkNull(String.valueOf(data[0][29])));
			}else{
				bean.setTotalCarExp("0");
			}
			
			if(String.valueOf(data[0][30])!=null && !String.valueOf(data[0][30]).equals("null")){
				bean.setTotalHotelExp(checkNull(String.valueOf(data[0][30])));
			}else{
				bean.setTotalHotelExp("0");
			}
			
			if(String.valueOf(data[0][31])!=null && !String.valueOf(data[0][31]).equals("null")){
				bean.setTotalMealExp(checkNull(String.valueOf(data[0][31])));
			}else{
				bean.setTotalMealExp("0");
			}
			
			if(String.valueOf(data[0][32])!=null && !String.valueOf(data[0][32]).equals("null")){
				bean.setTotalOtherExp(checkNull(String.valueOf(data[0][32])));
			}else{
				bean.setTotalOtherExp("0");
			}
			
			double value=0.00;
			if(!String.valueOf(data[0][28]).equals("null") && String.valueOf(data[0][28])!=null 
			&& !String.valueOf(data[0][29]).equals("null")	&& String.valueOf(data[0][29])!=null 
			&& !String.valueOf(data[0][30]).equals("null")	&&  String.valueOf(data[0][30])!=null 
			&& !String.valueOf(data[0][31]).equals("null")	&& String.valueOf(data[0][31])!=null
			&& !String.valueOf(data[0][32]).equals("null")	&& String.valueOf(data[0][32])!=null 
			){
				value=Double.parseDouble(String.valueOf(data[0][28]))+Double.parseDouble(String.valueOf(data[0][29]))
				+Double.parseDouble(String.valueOf(data[0][30]))+
				Double.parseDouble(String.valueOf(data[0][31]))+Double.parseDouble(String.valueOf(data[0][32]));
				String value1="";
				value1=String.valueOf(value);
				bean.setTotalValue(value1);
			}else{
				bean.setTotalValue("0.00");
			}
			bean.setApproverCode(checkNull(String.valueOf(data[0][33])));
			bean.setApproverToken(checkNull(String.valueOf(data[0][34])));
			bean.setApproverName(checkNull(String.valueOf(data[0][35])));
			
			/*Object[][] empFlow = null;
			try {
				final ReportingModel reporting = new ReportingModel();
				reporting.initiate(context, session);
				empFlow = reporting.generateEmpFlow(bean.getEmployeeCode(),	"D1", 1);
				reporting.terminate();
			} catch (final Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {

				final String setApprover = String.valueOf(empFlow[0][0]);
				// Approver Section Begins
				if (!this.checkNull(String.valueOf(data[0][33])).equals(setApprover)) {
					bean.setApproverCode(this.checkNull(String.valueOf(data[0][33])));
					bean.setApproverToken(this.checkNull(String.valueOf(data[0][34])));
					bean.setApproverName(this.checkNull(String.valueOf(data[0][35])));
				}

				// Approver Section Ends
			} else {

				bean.setApproverCode(this.checkNull(String.valueOf(data[0][33])));
				bean.setApproverToken(this.checkNull(String.valueOf(data[0][34])));
				bean.setApproverName(this.checkNull(String.valueOf(data[0][35])));
			}
			*/
			
			
			
			/* Object[][] data1 = null;
			 if(String.valueOf(data[0][33])!=null && !String.valueOf(data[0][33]).equals("")){
				 String queryApprover=" select HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) "
					 +" from hrms_emp_offc where EMP_ID= "+String.valueOf(data[0][33]);
				 data1 = getSqlModel().getSingleResult(queryApprover);
				 bean.setApproverToken(checkNull(String.valueOf(data1[0][0])));
				 bean.setApproverName(checkNull(String.valueOf(data1[0][1])));
			 }*/
			 
			//bean.setApprover(checkNull(String.valueOf(data[0][33])));
			 bean.setApplnStatus(checkNull(String.valueOf(data[0][37])));	
			 bean.setLevel(checkNull(String.valueOf(data[0][39])));
			 bean.setEmployeeToken(checkNull(String.valueOf(data[0][40])));
			 bean.setEmployeeName(checkNull(String.valueOf(data[0][41])));
			 bean.setDeptName(checkNull(String.valueOf(data[0][42])));
			 bean.setDesignation(checkNull(String.valueOf(data[0][43])));
			 bean.setEmailWorldTravel(checkNull(String.valueOf(data[0][45])));
			 
			 bean.setAuthorizedToken(checkNull(String.valueOf(data[0][46])));
			 
			 bean.setCompletedDate(checkNull(String.valueOf(data[0][38])));
			 bean.setCompletedByCode(checkNull(String.valueOf(data[0][47])));
			 Object[][] completedByObj = null;
			 if(String.valueOf(data[0][47])!=null && !String.valueOf(data[0][47]).equals("")){
				 String completedByQuery=" select HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) "
					 +" from hrms_emp_offc where EMP_ID= "+String.valueOf(data[0][47]);
				 completedByObj = getSqlModel().getSingleResult(completedByQuery);
				 //bean.setApproverToken(checkNull(String.valueOf(completedByObj[0][0])));
				 bean.setCompletedBy(checkNull(String.valueOf(completedByObj[0][1])));
			 }
			 
			 bean.setDestination(checkNull(String.valueOf(data[0][48])));
			 bean.setGeneralComments(checkNull(String.valueOf(data[0][49])));
			
			 
		} catch(Exception e) {
			e.printStackTrace();
		}

	} catch(Exception e) {
		e.printStackTrace();
	}
}


public boolean delete(TravelApplicationFormBean bean, HttpServletRequest request) {
	boolean result = false;

	String code = bean.getTravelID();

	String delQuery = "DELETE FROM HRMS_D1_TRAVEL_REQUEST WHERE TRAVEL_ID="+code;
	result = getSqlModel().singleExecute(delQuery);
	return result;
}

//Approved List
public void getApprovedList(TravelApplicationFormBean bean, HttpServletRequest request, String userId) {
	try {
		Object[][] approvedListData = null;
		ArrayList approvedList = new ArrayList();

		Object[][] approvedCancellationListData = null;
		ArrayList approvedCancellationList = new ArrayList();

		// Approved application Begins
	
		String selQuery = "SELECT TRAVEL_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'), TRAVEL_APPROVER_STATUS " 
			+ " ,TRAVEL_ID,TRAVEL_FILE_HEADER_NAME, HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') FROM HRMS_D1_TRAVEL_REQUEST "
			+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) "
			+ " WHERE TRAVEL_APPROVER_STATUS  = 'A' AND TRAVEL_INITIATOR = " + userId + " " + "ORDER BY TRAVEL_APPLICATION_DATE DESC ";
		
		approvedListData = getSqlModel().getSingleResult(selQuery);

		String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
		if(pageIndexApproved == null) {
			pageIndexApproved[0] = "0";
			pageIndexApproved[1] = "20";
			pageIndexApproved[2] = "1";
			pageIndexApproved[3] = "1";
			pageIndexApproved[4] = "";
		}

		request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
		request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
		if(pageIndexApproved[4].equals("1")) bean.setMyPageApproved("1");

		if(approvedListData != null && approvedListData.length > 0) {
			
			for(int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
				TravelApplicationFormBean beanItt = new TravelApplicationFormBean();
				beanItt.setEmpToken(checkNull(String.valueOf(approvedListData[i][1])));
				beanItt.setEmpName(checkNull(String.valueOf(approvedListData[i][2])));
				beanItt.setApplicationDate(checkNull(String.valueOf(approvedListData[i][3])));
				beanItt.setApplnStatus(checkNull(String.valueOf(approvedListData[i][4])));
				beanItt.setTravelID(checkNull(String.valueOf(approvedListData[i][5])));
				beanItt.setAuthorizedToken(checkNull(String.valueOf(approvedListData[i][6])));
				beanItt.setDestinationItr(checkNull(String.valueOf(approvedListData[i][7])));
				beanItt.setFromTravelDateItr(checkNull(String.valueOf(approvedListData[i][8])));
				approvedList.add(beanItt);
				
			}
			bean.setApprovedListLength(true);
			bean.setApprovedList(approvedList);
		}
		// Approved application Ends

		// Approved cancellation application Begins
		String approvedcancellationQuery = "SELECT TRAVEL_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'), TRAVEL_APPROVER_STATUS " 
			+ " ,TRAVEL_ID,TRAVEL_FILE_HEADER_NAME, HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') FROM HRMS_D1_TRAVEL_REQUEST "
			+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) "
			+ " WHERE TRAVEL_APPROVER_STATUS  = 'X' AND TRAVEL_INITIATOR = " + userId + " " + "ORDER BY TRAVEL_APPLICATION_DATE DESC ";

		approvedCancellationListData = getSqlModel().getSingleResult(approvedcancellationQuery);

		String[] pageIndexApprovedCancel = Utility.doPaging(bean.getMyPageApprovedCancel(), approvedCancellationListData.length, 20);
		if(pageIndexApprovedCancel == null) {
			pageIndexApprovedCancel[0] = "0";
			pageIndexApprovedCancel[1] = "20";
			pageIndexApprovedCancel[2] = "1";
			pageIndexApprovedCancel[3] = "1";
			pageIndexApprovedCancel[4] = "";
		}

		request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
		request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
		if(pageIndexApprovedCancel[4].equals("1")) bean.setMyPageApprovedCancel("1");

		if(approvedCancellationListData != null && approvedCancellationListData.length > 0) {
			for(int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer.parseInt(pageIndexApprovedCancel[1]); i++) {
				TravelApplicationFormBean beanItt = new TravelApplicationFormBean();
				beanItt.setEmpToken(checkNull(String.valueOf(approvedCancellationListData[i][1])));
				beanItt.setEmpName(checkNull(String.valueOf(approvedCancellationListData[i][2])));
				beanItt.setApplicationDate(checkNull(String.valueOf(approvedCancellationListData[i][3])));
				beanItt.setApplnStatus(checkNull(String.valueOf(approvedCancellationListData[i][4])));
				beanItt.setTravelID(checkNull(String.valueOf(approvedCancellationListData[i][5])));
				beanItt.setAuthorizedToken(checkNull(String.valueOf(approvedCancellationListData[i][6])));
				beanItt.setDestinationItr(checkNull(String.valueOf(approvedCancellationListData[i][7])));
				beanItt.setFromTravelDateItr(checkNull(String.valueOf(approvedCancellationListData[i][8])));
				approvedCancellationList.add(beanItt);
			}
			bean.setApprovedCancelListLength(true);
			bean.setApprovedCancelList(approvedCancellationList);
		}
		// Approved cancellation application Ends

	} catch(Exception e) {
		e.printStackTrace();
	}

}
//Reject List
public void getRejectedList(TravelApplicationFormBean bean, HttpServletRequest request, String userId) {

	Object[][] rejectedListData = null;
	ArrayList rejectedList = new ArrayList();

	Object[][] rejectedCancellationListData = null;
	ArrayList rejectedCancellationList = new ArrayList();

	// Rejected application Begins
	String rejectQuery = "SELECT TRAVEL_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'), TRAVEL_APPROVER_STATUS " 
		+ " ,TRAVEL_ID,TRAVEL_FILE_HEADER_NAME, HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') FROM HRMS_D1_TRAVEL_REQUEST "
		+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) "
		+ " WHERE TRAVEL_APPROVER_STATUS  = 'R' AND TRAVEL_INITIATOR = " + userId + " " + "ORDER BY TRAVEL_APPLICATION_DATE DESC ";

	rejectedListData = getSqlModel().getSingleResult(rejectQuery);

	String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(), rejectedListData.length, 20);
	if(pageIndexRejected == null) {
		pageIndexRejected[0] = "0";
		pageIndexRejected[1] = "20";
		pageIndexRejected[2] = "1";
		pageIndexRejected[3] = "1";
		pageIndexRejected[4] = "";
	}

	request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
	request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
	if(pageIndexRejected[4].equals("1")) bean.setMyPageRejected("1");

	if(rejectedListData != null && rejectedListData.length > 0) {
		
		for(int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
			TravelApplicationFormBean beanItt = new TravelApplicationFormBean();
			beanItt.setEmpToken(checkNull(String.valueOf(rejectedListData[i][1])));
			beanItt.setEmpName(checkNull(String.valueOf(rejectedListData[i][2])));
			beanItt.setApplicationDate(checkNull(String.valueOf(rejectedListData[i][3])));
			beanItt.setApplnStatus(checkNull(String.valueOf(rejectedListData[i][4])));
			beanItt.setTravelID(checkNull(String.valueOf(rejectedListData[i][5])));
			beanItt.setAuthorizedToken(checkNull(String.valueOf(rejectedListData[i][6])));
			beanItt.setDestinationItr(checkNull(String.valueOf(rejectedListData[i][7])));
			beanItt.setFromTravelDateItr(checkNull(String.valueOf(rejectedListData[i][8])));
			rejectedList.add(beanItt);
		}
		bean.setRejectListLength(true);
		bean.setRejectList(rejectedList);
	}
	// Rejected application Ends

	// Rejected cancellation application Begins

	String rejectcancellationQuery = "SELECT TRAVEL_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'), TRAVEL_APPROVER_STATUS " 
		+ " ,TRAVEL_ID,TRAVEL_FILE_HEADER_NAME, HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') FROM HRMS_D1_TRAVEL_REQUEST "
		+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID) "
		+ " WHERE TRAVEL_APPROVER_STATUS  = 'Z' AND TRAVEL_INITIATOR = " + userId + " " + "ORDER BY TRAVEL_APPLICATION_DATE DESC ";

	rejectedCancellationListData = getSqlModel().getSingleResult(rejectcancellationQuery);

	String[] pageIndexRejectedCancellation = Utility.doPaging(bean.getMyPageCancelRejected(), rejectedCancellationListData.length, 20);
	if(pageIndexRejectedCancellation == null) {
		pageIndexRejectedCancellation[0] = "0";
		pageIndexRejectedCancellation[1] = "20";
		pageIndexRejectedCancellation[2] = "1";
		pageIndexRejectedCancellation[3] = "1";
		pageIndexRejectedCancellation[4] = "";
	}

	request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[2])));
	request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[3])));
	if(pageIndexRejectedCancellation[4].equals("1")) bean.setMyPageCancelRejected("1");

	if(rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {
	
		for(int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer.parseInt(pageIndexRejectedCancellation[1]); i++) {
			TravelApplicationFormBean beanItt = new TravelApplicationFormBean();
			beanItt.setEmpToken(checkNull(String.valueOf(rejectedCancellationListData[i][1])));
			beanItt.setEmpName(checkNull(String.valueOf(rejectedCancellationListData[i][2])));
			beanItt.setApplicationDate(checkNull(String.valueOf(rejectedCancellationListData[i][3])));
			beanItt.setApplnStatus(checkNull(String.valueOf(rejectedCancellationListData[i][4])));
			beanItt.setTravelID(checkNull(String.valueOf(rejectedCancellationListData[i][5])));
			beanItt.setAuthorizedToken(checkNull(String.valueOf(rejectedCancellationListData[i][6])));
			beanItt.setDestinationItr(checkNull(String.valueOf(rejectedCancellationListData[i][7])));
			beanItt.setFromTravelDateItr(checkNull(String.valueOf(rejectedCancellationListData[i][8])));
			rejectedCancellationList.add(beanItt);
		}
		bean.setRejectCancelListLength(true);
		bean.setRejectCancelList(rejectedCancellationList);
	}
	// Rejected cancellation application Ends
}
//Start for Approval
public Object[][] getTravelDetails(String travelId) {
	String getDetailsSql = " SELECT TRAVEL_APPROVER_COMMENTS, TRAVEL_APPROVER_CODE, TRAVEL_APPROVER_STATUS FROM HRMS_D1_TRAVEL_REQUEST "
	 +" WHERE TRAVEL_ID="+travelId;
	return getSqlModel().getSingleResult(getDetailsSql);
}

public boolean isUserAHRApprover(String userId) {
	String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H' AND APP_SETTINGS_EMP_ID = " + userId;
	Object[][] hrApproverObj = getSqlModel().getSingleResult(hrApproverSql);

	if(hrApproverObj != null && hrApproverObj.length > 0) { return true; }

	return false;
}
//End for Approval

public String onlineApproveRejectSendBack(HttpServletRequest request,
		String applicationCode, String status,
		String remarks, String approverId ,String userId,String employeeCode) {
	String result = "";
	String res = "";
	String query = " SELECT TRAVEL_APPROVER_STATUS FROM HRMS_D1_TRAVEL_REQUEST WHERE TRAVEL_ID="
			+ applicationCode;

	Object approverIdObj[][] = getSqlModel().getSingleResult(query);

	if (approverIdObj != null && approverIdObj.length > 0) {
		if (String.valueOf(approverIdObj[0][0]).equals("F")) {
			res = approveRejectSendBackLevApp(request, applicationCode, status, remarks, approverId,userId,employeeCode);
			if (res.equals("approved")){
				result = "Travel application has been approved.";
			}else if (res.equals("rejected")){
				result = "Travel application has been rejected.";
			}else if (res.equals("sendback")){
				result = "Travel application has been sent back to applicant.";
			}else{
				result = "Error Occured.";
			}
			//Mail to All
			sendApprovalMailToInititor(applicationCode, userId, employeeCode, status);
			sendApprovalMailToManager(applicationCode, userId, employeeCode, status);
			sendApprovalMailToEmployee(applicationCode, userId, employeeCode, status);
		} else {
			result = "Travel Application has already been processed.";
		}
	}
	//sendApprovalMailToInititor(applicationCode, userId, employeeCode, status);
	//sendApprovalMailToManager(applicationCode, userId, employeeCode, status);
	return result;

}

public String approveRejectSendBackLevApp(HttpServletRequest request, 
		String applicationCode, String status, String remarks, String approverId,String userId,String employeeCode){
	String result="";
	String updateFromTP="update HRMS_D1_TRAVEL_REQUEST set TRAVEL_APPROVER_STATUS='"+status 
	+"',TRAVEL_TP_EMAIL='"+ approverId
	+"',TRAVEL_APPROVER_COMMENTS='"+ remarks
	+"' WHERE TRAVEL_ID="+applicationCode;
	
	boolean resultATR=getSqlModel().singleExecute(updateFromTP);
	
	insertApproverComments(applicationCode, "0", remarks,
			status);
	if(resultATR){
		if(status.equals("A")){
			result="approved";
		}else if(status.equals("R")){
			result="rejected";
		}else if(status.equals("B")){
			result="sendback";
		}
	}
	return result;
}


private void insertApproverComments(String cdromId, String userId,
		String approverComments, String statusToUpdate) {
	String insertSql = " INSERT INTO HRMS_D1_TRAVEL_DATA_PATH (TRAVEL_PATH_ID,TRAVEL_ID, TRAVEL_APPROVER, TRAVEL_COMMENTS,TRAVEL_STATUS,TRAVEL_DATE) "
			+ " VALUES ((SELECT NVL(MAX(TRAVEL_PATH_ID), 0) + 1 FROM HRMS_D1_TRAVEL_DATA_PATH), ?, ?, ?, ?,sysdate) ";

	Object[][] insertObj = new Object[1][4];
	insertObj[0][0] = cdromId;
	insertObj[0][1] = userId;
	insertObj[0][2] = approverComments;
	insertObj[0][3] = statusToUpdate;

	getSqlModel().singleExecute(insertSql, insertObj);
}


private void sendApprovalMailToInititor(String travelId, String userId, String employeeCode, String status) {
	try {
		// MAIL FROM APPROVER TO APPLICANT
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			TravelApplicationFormModel model=new TravelApplicationFormModel();
			model.initiate(context, session);
			String finInititor="Select Trim(TRAVEL_INITIATOR) from  HRMS_D1_TRAVEL_REQUEST where TRAVEL_ID="+travelId;
			//Object finInititorObj[][]=model.getSqlModel().getSingleResult(finInititor);
			Object[][] finInititorObj =model.getSqlModel().getSingleResult(finInititor);
			
		
			String finDeptQuery=" SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('R')   AND APP_EMAIL_ID IS NOT NULL";
			Object[][] finDeptQueryObj =model.getSqlModel().getSingleResult(finDeptQuery);
			
			String inititorId="";
			if(finInititorObj!=null && finInititorObj.length>0){
				inititorId=String.valueOf(finInititorObj[0][0]).trim();
			}
			template.setEmailTemplate("D1-AUTHORIZED TRAVEL STATUS FROM WORLD TRAVEL");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1,inititorId.trim());
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, travelId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, travelId);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, travelId);
			
			//Keep inform
			//String[] empData = null;
			/*String[] empData = new String[finDeptQueryObj.length];
			if (finDeptQueryObj != null && finDeptQueryObj.length > 0) {
				empData = new String[finDeptQueryObj.length];
				for (int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(finDeptQueryObj[i][0]);
				}
			}*/
			
			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
						
			if(finDeptQueryObj !=null && finDeptQueryObj.length>0){
				template.sendApplicationMailToGroups(finDeptQueryObj);
			}		
			
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		//End Inititor
	} catch(Exception e) {
		e.printStackTrace();
	}
}

//Manager
private void sendApprovalMailToManager(String travelId, String userId, String employeeCode, String status) {
	try {
		// MAIL FROM APPROVER TO APPLICANT
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			TravelApplicationFormModel model=new TravelApplicationFormModel();
			model.initiate(context, session);
			String finManager="select TRAVEL_APPROVER  from HRMS_D1_TRAVEL_DATA_PATH where TRAVEL_APPROVER <>0 and TRAVEL_ID="+travelId;
			//Object finInititorObj[][]=model.getSqlModel().getSingleResult(finInititor);
			Object[][] finManagerObj =model.getSqlModel().getSingleResult(finManager);
			
			String managerId="";
			if(finManagerObj!=null && finManagerObj.length>0){
				managerId=String.valueOf(finManagerObj[0][0]).trim();
			}
			template.setEmailTemplate("D1-AUTHORIZED TRAVEL STATUS FROM WORLD TRAVEL");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1,managerId.trim());
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, travelId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, travelId);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, travelId);
			
			//Keep inform
			//String[] empData = null;
			String[] empData = new String[finManagerObj.length];
			if (finManagerObj != null && finManagerObj.length > 1) {
				empData = new String[finManagerObj.length];
				for (int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(finManagerObj[i][0]);
				}
			}
			
			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMailToKeepInfo(empData);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		//End Inititor
	
	} catch(Exception e) {
		//logger.error(e);
	}

}
//To employee
private void sendApprovalMailToEmployee(String travelId, String userId, String employeeCode, String status) {

	try {
		
			
		try {
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			TravelApplicationFormModel model=new TravelApplicationFormModel();
			model.initiate(context, session);
			String finEmployee="select TRAVEL_EMP_ID  from HRMS_D1_TRAVEL_REQUEST where TRAVEL_ID="+travelId;
			Object[][] finEmployeeObj =model.getSqlModel().getSingleResult(finEmployee);
			
			String employeeId="";
			if(finEmployeeObj!=null && finEmployeeObj.length>0){
				employeeId=String.valueOf(finEmployeeObj[0][0]).trim();
			}
			template.setEmailTemplate("D1-AUTHORIZED TRAVEL STATUS FROM WORLD TRAVEL");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1,employeeId.trim());
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, travelId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, travelId);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, travelId);
			
			template.configMailAlert();
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		//End Employee
	
	} catch(Exception e) {
		//logger.error(e);
	}

}
}
