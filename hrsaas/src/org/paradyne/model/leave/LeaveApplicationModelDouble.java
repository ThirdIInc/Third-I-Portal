/**
 * 
 */
package org.paradyne.model.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import org.paradyne.bean.leave.LeaveApplication;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Sunil
 * @date 07 April 07
 **/
public class LeaveApplicationModelDouble extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public int  calculate(String startDate , String endDate,LeaveApplication leaveApplication)	{
		
		int total = 0;
		int totalHol = 0;
		int secSaterdaySunday = 0;
		int weekenHol = 0;
		Object[][] holidayObj =null;
		
		String select="SELECT TO_DATE('"+endDate+"','DD-MM-YYYY')-TO_DATE('"+startDate+"','DD-MM-YYYY') from  DUAL";
		Object[][] dateDiff=getSqlModel().getSingleResult(select);
		String leaveType = leaveApplication.getLevCode();
		
		String holidayFlag =" SELECT  HRMS_LEAVE_POLICY.LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_CODE"
							+" FROM HRMS_LEAVE_POLICY "
							+" WHERE  EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID ="+leaveApplication.getEmpCode()+" ) "
							+"  AND (LEAVE_HOLIDAY_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="+leaveApplication.getLevCode()+")";
		Object[][] holidayCheckObj=getSqlModel().getSingleResult(holidayFlag);
		
		String weekFlag =" SELECT  HRMS_LEAVE_POLICY.LEAVE_WEEKOFF_INCLUDE_FLAG,LEAVE_CODE"
			+" FROM HRMS_LEAVE_POLICY "
			+" WHERE  EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID ="+leaveApplication.getEmpCode()+" ) "
			+"  AND (LEAVE_WEEKOFF_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="+leaveApplication.getLevCode()+")";
		Object[][] weeklyCheckObj=getSqlModel().getSingleResult(weekFlag);
		
		/**
		 * CHECK HOLIDAY WILL INCLUDE IN LEAVE
		 */
		if(holidayCheckObj.length > 0){
			String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YY') FROM HRMS_HOLIDAY"
							+" WHERE HOLI_DATE >=TO_DATE('"+startDate+"','DD-MM-YYYY') AND HOLI_DATE <=TO_DATE('"+endDate+"','DD-MM-YYYY')";
			 holidayObj=getSqlModel().getSingleResult(holStr);
			 totalHol = holidayObj.length ;
		}
		/**
		 * CHECK WEEKEND WILL INCLUDE IN LEAVE
		 */
		if(weeklyCheckObj .length >0){
			
			/**
			 * CHECK WHETHER HOLIDAY FALLS IN  WEEKEND  
			 */
			if (holidayObj !=null) {
				weekenHol = new Utility().removeSecSatSunFromHolidayList(holidayObj);
				totalHol =weekenHol;
			}
			if(!(new Utility().checkDate(startDate, endDate) == 1 || new Utility().checkDate(startDate, endDate) == 9999 )){
				secSaterdaySunday = new Utility().countNumberOfSecSaturdaysAndSundays(new Utility().getCalanderDate(startDate),new Utility().getCalanderDate(endDate));
			}
			
		}
			total =(Integer.parseInt(String.valueOf(dateDiff[0][0]))+1)-(totalHol + secSaterdaySunday);		
		/*}else{
			total =Integer.parseInt(String.valueOf(dateDiff[0][0]))+1 ;
		}*/
		return total ;
		
	}
	
	/** METHOD FOR ADD RECORD BY APPLICANT FOR ADD OR UPDATE LEAVE TYPE     
	 * 
	 * @param leaveApplication              					*************** By Sunil*******************
	 * @param leaveFromDtl
	 * @param leaveToDtl
	 * @param levClosingBalance
	 * @param levOpeningBalance
	 * @return
	 */
	public String addLeaveType(LeaveApplication leaveApplication) {
		logger.info("addLeaveApplication");
		String levCode = leaveApplication.getLeaveCode();
		Object[][] bean = new Object[1][15];
		boolean result = false;
			
		/**
		 * 	ADDING APPLICATION IF NOT ADDED
		 */
	/*	
		String message =validForAddLeave(leaveApplication);
		if(!(message.equals(""))){
			return message;
		}
		*/
		if(levCode.equals("") || levCode == null){
			/**
			 * Check valid date for leave 
			 */
			String message =validForAddLeave(leaveApplication);
			if(!(message.equals(""))){
				return message;
			}
			bean[0][0] = leaveApplication.getEmpCode();
			bean[0][1] = leaveApplication.getLeaveTotalDays();
			bean[0][2] = leaveApplication.getComments();
			bean[0][3] = leaveApplication.getContactDetails();
		//	bean[0][4] = leaveApplication.getStatus();
			bean[0][4] = leaveApplication.getReliever();
	
			bean[0][5] = leaveApplication.getPrefix();
			bean[0][6] = leaveApplication.getSuffix();
			bean[0][7] = leaveApplication.getMedicalCert();
			bean[0][8] = leaveApplication.getForwardId();
			
			bean[0][9] = leaveApplication.getToDraw();
			bean[0][10] = leaveApplication.getRationRW();
			bean[0][11] = leaveApplication.getWefDate();
			bean[0][12] = leaveApplication.getLetterNo();
			bean[0][13] = leaveApplication.getLetterDate();
			bean[0][14] = leaveApplication.getRationCHQ();
			result = getSqlModel().singleExecute(getQuery(1), bean);
			
			// INSERT INTO HRMS_LEAVE_PATH
			if(result){
			Object[][] appObj = new Object[2][1];
			appObj[0][0] = leaveApplication.getForwardId();
			appObj[1][0] = leaveApplication.getApproverId();
			result = getSqlModel().singleExecute(getQuery(20), appObj);		
			}
		}
		
		/**
		 *  RETRIEVE PRIVIOUS ADDED LEVAE APPLICATION CODE 
		 */
		Object levCodeObj [][] = getSqlModel().getSingleResult(getQuery(15));
		/**
		 * FOR ADDING RECORD IN HRMS_LEAVE_DTL 
		 */
		if(!(leaveApplication.getStatus().trim().equals("Recommended")||leaveApplication.getStatus().trim().equals("Approved") 
						|| leaveApplication.getStatus().trim().equals("Rejected") || leaveApplication.getStatus().trim().equals("Canceled"))){
			if (result ||!(levCode.equals("") || levCode == null)) {      								//  check whether
					Object[][] beanLeave = new Object[1][6];
					
					beanLeave[0][0] = leaveApplication.getEmpCode();
					beanLeave[0][1] = leaveApplication.getLevCode();
					beanLeave[0][2] = leaveApplication.getLeaveTotalDays();
					beanLeave[0][3] = leaveApplication.getLeaveFromDtl();
					beanLeave[0][4] = leaveApplication.getLeaveToDtl();
					beanLeave[0][5] = leaveApplication.getRemark();
					if(leaveApplication.getLeaveId().equals("") || leaveApplication.getLeaveId() == null  ){  //check for add or update
						/**
						 * Check valid date for leave 
						 */
						String message =validForAddLeave(leaveApplication);
						if(!(message.equals(""))){
							return message;
						}
						
						if(levCode.equals("") || levCode == null){												// For adding as new application
							result = getSqlModel().singleExecute(getQuery(10), beanLeave);                    
							leaveApplication.setLeaveCode(String.valueOf(levCodeObj[0][0]).toString());
							leaveApplication.setStatus(String.valueOf("Being Applied"));
						}else{																					// For adding as selected application
							Object[][] addLeave = new Object[1][7];
							addLeave[0][0] = levCode;
							addLeave[0][1] = leaveApplication.getEmpCode();
							addLeave[0][2] = leaveApplication.getLevCode();
							addLeave[0][3] = leaveApplication.getLeaveTotalDays();
							addLeave[0][4] = leaveApplication.getLeaveFromDtl();
							addLeave[0][5] = leaveApplication.getLeaveToDtl();
							addLeave[0][6] = leaveApplication.getRemark();
							result = getSqlModel().singleExecute(getQuery(25), addLeave);
						}
					}else{
						if(leaveApplication.isUpdateFlag()){                //test for updateUser
							/**
							 * Check valid date for leave 
							 */
							String message =validForAddLeave(leaveApplication);
							if(!(message.equals(""))){
								return message;
							}
							if(!(leaveApplication.getLevCode().equals(leaveApplication.getHdlevType()))){ //check for change leave type
								String updateBlnc = " UPDATE HRMS_LEAVE_BALANCE SET "
													+" LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + (SELECT LEAVE_DAYS FROM HRMS_LEAVE_DTL WHERE LEAVE_DTL_ID =? ) "
													+" WHERE EMP_ID =? AND LEAVE_CODE =?" ;
								Object[][] updateObj = new Object[1][3];
								updateObj[0][0] = leaveApplication.getLeaveId();
								updateObj[0][1] = leaveApplication.getEmpCode();
								updateObj[0][2] = leaveApplication.getHdlevType();
								result = getSqlModel().singleExecute(updateBlnc, updateObj);
							}
						Object[][] updateObj = new Object[1][7];
						updateObj[0][0] = leaveApplication.getEmpCode();
						updateObj[0][1] = leaveApplication.getLevCode();
						updateObj[0][2] = leaveApplication.getLeaveTotalDays();
						updateObj[0][3] = leaveApplication.getLeaveFromDtl();
						updateObj[0][4] = leaveApplication.getLeaveToDtl();
						updateObj[0][5] = leaveApplication.getRemark();
						updateObj[0][6] = leaveApplication.getLeaveId();
						result = getSqlModel().singleExecute(getQuery(19), updateObj);					// For Update
						
						}else{
							return "Permission denied to update record";
						}
					}
				
			}
		
		/**
		 * FOR UPDATING RECORD IN HRMS_LEAVE_BALANCE
		 */
		if (result || !(levCode.equals("") || levCode == null)) {
			Object[][] LeaveBal = new Object[1][3];				
				LeaveBal[0][0] = leaveApplication.getLevClosingBalance();
				LeaveBal[0][1] = leaveApplication.getLevCode();
				LeaveBal[0][2] = leaveApplication.getEmpCode();
				result = getSqlModel().singleExecute(getQuery(14), LeaveBal);                   // Update Leave Balance
		}
		leaveApplication.setParacode("");
		leaveApplication.setHiddenDate("");
		leaveApplication.setLeaveId("");
		leaveApplication.setLevCode("");
		leaveApplication.setLevClosingBalance("");
		leaveApplication.setLeaveFromDtl("");
		leaveApplication.setLeaveTotalDays("");
		leaveApplication.setLevType("");
		leaveApplication.setLeaveToDtl("");
		leaveApplication.setLevOpeningBalance("");
		leaveApplication.setRemark("");
		
		
		return "Application saved successfully";
		}else{
			if(leaveApplication.getStatus().trim().equals("Recomended"))
				return "Application in process so you can't update !" ;
			else if(leaveApplication.getStatus().trim().equals("Approved"))
				return "Application is Approved so you can't update ! " ;
			else 
				return "Application rejected so you can't update !" ;
		}	
	}
	
	public String addLeaveByAdmin(LeaveApplication leaveApplication) {
		logger.info("addLeaveApplication");
		String levCode = leaveApplication.getLeaveCode();
		Object[][] bean = new Object[1][14];
		boolean result = false;
			if(leaveApplication.getLeaveId().equals("") || leaveApplication.getLeaveId() == null  ){  //check for add or update
							// For adding leave on application																					// For adding as selected application
					Object[][] addLeave = new Object[1][7];
					addLeave[0][0] = levCode;
					addLeave[0][1] = leaveApplication.getEmpCode();
					addLeave[0][2] = leaveApplication.getLevCode();
					addLeave[0][3] = leaveApplication.getLeaveTotalDays();
					addLeave[0][4] = leaveApplication.getLeaveFromDtl();
					addLeave[0][5] = leaveApplication.getLeaveToDtl();
					addLeave[0][6] = leaveApplication.getRemark();
					result = getSqlModel().singleExecute(getQuery(25), addLeave);
			}
			else{
				if(!(leaveApplication.getLevCode().equals(leaveApplication.getHdlevType()))){ //check for change leave type
					String updateBlnc = " UPDATE HRMS_LEAVE_BALANCE SET "
										+" LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + (SELECT LEAVE_DAYS FROM HRMS_LEAVE_DTL WHERE LEAVE_DTL_ID =? ) "
										+" WHERE EMP_ID =? AND LEAVE_CODE =?" ;
					Object[][] updateObj = new Object[1][3];
					updateObj[0][0] = leaveApplication.getLeaveId();
					updateObj[0][1] = leaveApplication.getEmpCode();
					updateObj[0][2] = leaveApplication.getHdlevType();
					result = getSqlModel().singleExecute(updateBlnc, updateObj);
				}
				Object[][] updateObj = new Object[1][7];
				updateObj[0][0] = leaveApplication.getEmpCode();
				updateObj[0][1] = leaveApplication.getLevCode();
				updateObj[0][2] = leaveApplication.getLeaveTotalDays();
				updateObj[0][3] = leaveApplication.getLeaveFromDtl();
				updateObj[0][4] = leaveApplication.getLeaveToDtl();
				updateObj[0][5] = leaveApplication.getRemark();
				updateObj[0][6] = leaveApplication.getLeaveId();
				result = getSqlModel().singleExecute(getQuery(19), updateObj);					// For Update
			}
		
		/**
		 * FOR UPDATING RECORD IN HRMS_LEAVE_BALANCE
		 */
		if (result) {
			Object[][] LeaveBal = new Object[1][3];				
				LeaveBal[0][0] = leaveApplication.getLevClosingBalance();
				LeaveBal[0][1] = leaveApplication.getLevCode();
				LeaveBal[0][2] = leaveApplication.getEmpCode();
				result = getSqlModel().singleExecute(getQuery(14), LeaveBal);                   // Update Leave Balance
		}
		if(result){
			Object [][] objDtl = new Object[1][2];
			objDtl[0][0] ="P";
			objDtl[0][1] =leaveApplication.getLeaveCode();
			String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = ? "
								+" WHERE LEAVE_APPLICATION_CODE= ? ";
				getSqlModel().singleExecute(updateDtl, objDtl);	
		}
		if(result){
			Object[][] updateHdr = new Object[1][3];
			logger.info("comments*******************"+leaveApplication.getAssesedApproved());
			updateHdr[0][0] = leaveApplication.getForwardId();
			updateHdr[0][1] = "P";
			updateHdr[0][2] = leaveApplication.getLeaveCode();
			result =getSqlModel().singleExecute(getQuery(22), updateHdr);
			leaveApplication.setParacode("");
		}
		leaveApplication.setStatus("Pending");
		leaveApplication.setHiddenDate("");
		leaveApplication.setLeaveId("");
		leaveApplication.setLevCode("");
		leaveApplication.setLevClosingBalance("");
		leaveApplication.setLeaveFromDtl("");
		leaveApplication.setLeaveTotalDays("");
		leaveApplication.setLevType("");
		leaveApplication.setLeaveToDtl("");
		leaveApplication.setLevOpeningBalance("");
		leaveApplication.setRemark("");

		return "Application update successfully";
		
	}
	
	/**
	 * METHOD CALLS WHEN USER APPLY FOR LEAVE        ***************By Sunil*******************
	 * @param leaveApplication
	 */
	public void getLeaveDtl(LeaveApplication leaveApplication){
		ArrayList<Object> att = new ArrayList<Object>();
		Object levObj [] = new Object[1];
		levObj[0]= leaveApplication.getEmpCode();
		Object[][] values = getSqlModel().getSingleResult(getQuery(16), levObj);
		for(int i = 0 ;i < values.length ; i++){
			LeaveApplication levApp = new LeaveApplication();
			levApp.setSlevCode(String.valueOf(values[i][0]));
			levApp.setSlevType(String.valueOf(values[i][1]));
			levApp.setSleaveFromDtl(String.valueOf(values[i][2]));
			levApp.setSleaveToDtl(String.valueOf(values[i][3]));
			levApp.setSlevClosingBalance(String.valueOf(values[i][4]));
			levApp.setLeaveId(String.valueOf(values[i][5]));
			att.add(levApp);
		}
		leaveApplication.setAtt(att);
		
	}
	/**
	 * METHOD CALLS WHEN USER SELECT APPLICATION CODE         ***************By Sunil*******************
	 * @param leaveApplication
	 */
	public void getLeaveDtlHistory(LeaveApplication leaveApplication){
		ArrayList<Object> att = new ArrayList<Object>();
		try{
		Object levObj [] = new Object[2];
		levObj[0]= leaveApplication.getEmpCode();
		levObj[1]= leaveApplication.getLeaveCode();
		logger.info("LeaveApplication----------"+leaveApplication.getEmpCode());
		Object[][] values = getSqlModel().getSingleResult(getQuery(18), levObj);
		for(int i = 0 ;i < values.length ; i++){
			LeaveApplication levApp = new LeaveApplication();
			levApp.setSlevCode(String.valueOf(values[i][0]));
			levApp.setSlevType(String.valueOf(values[i][1]));
			levApp.setSleaveFromDtl(String.valueOf(values[i][2]));
			levApp.setSleaveToDtl(String.valueOf(values[i][3]));
			levApp.setSlevClosingBalance(String.valueOf(values[i][4]));
			Object remObj = values[i][5];
			if (remObj != null) 
				levApp.setRemark(String.valueOf(values[i][5]));
			else
				levApp.setRemark("");
			levApp.setLeaveId(String.valueOf(values[i][6]));
			att.add(levApp);
		}
		leaveApplication.setAtt(att);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**					 											***************By Sunil*******************
	 * METHOD FOR SELECT PERTICULAR RECORD FOR EDITE                                           
	 * @param leaveApplication
	 */
	public void getLeaveEditDtl(LeaveApplication leaveApplication){
		Object [] editObj = new Object[2];
		editObj[0] = leaveApplication.getHiddenDate();
		editObj[1] = leaveApplication.getEmpCode();
		logger.info("Leave dtl id-----------------"+leaveApplication.getHiddenDate());
		Object[][] result = getSqlModel().getSingleResult(getQuery(17), editObj);
		if(result.length != 0){
			leaveApplication.setLevCode(String.valueOf(result[0][0]));
			leaveApplication.setHdlevType(String.valueOf(result[0][0]));
			leaveApplication.setLevType(String.valueOf(result[0][1]));
			leaveApplication.setLeaveFromDtl(String.valueOf(result[0][2]));
			leaveApplication.setLeaveToDtl(String.valueOf(result[0][3]));
			leaveApplication.setLevOpeningBalance(String.valueOf((Integer.parseInt(String.valueOf(result[0][4]))+Integer.parseInt(String.valueOf(result[0][5])))));
			leaveApplication.setLevClosingBalance(String.valueOf(result[0][4]));
			leaveApplication.setLeaveTotalDays(String.valueOf(result[0][5]));
			Object remarObj = result[0][6];
			if(remarObj != null)
				leaveApplication.setRemark(String.valueOf(result[0][6]));
			else
				leaveApplication.setRemark("");
			leaveApplication.setLeaveId(String.valueOf(result[0][7]));
		}
	}
	public boolean removeLeaves(LeaveApplication leaveApplication){
		String updateBlnc =" UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "+
					" WHERE EMP_ID =? AND LEAVE_CODE =?";
			Object[][] updateObj = new Object[1][3];
			updateObj[0][0] = leaveApplication.getRemLevDay();
			updateObj[0][1] = leaveApplication.getEmpCode();
			updateObj[0][2] = leaveApplication.getRemlevCode();
		boolean	result = getSqlModel().singleExecute(updateBlnc, updateObj);
		if(result){
			String delQuery ="DELETE FROM HRMS_LEAVE_DTL WHERE LEAVE_DTL_ID="+leaveApplication.getRemlevDtl();
			result=getSqlModel().singleExecute(delQuery);		
		}
		leaveApplication.setParacode("");
		leaveApplication.setHiddenDate("");
		leaveApplication.setLeaveId("");
		leaveApplication.setLevCode("");
		leaveApplication.setLevClosingBalance("");
		leaveApplication.setLeaveFromDtl("");
		leaveApplication.setLeaveTotalDays("");
		leaveApplication.setLevType("");
		leaveApplication.setLeaveToDtl("");
		leaveApplication.setLevOpeningBalance("");
		leaveApplication.setRemark("");
		leaveApplication.setRemLevDay("");
		leaveApplication.setRemlevCode("");
		return result;
	}
	public boolean forwardApplication(LeaveApplication leaveApplication){
		Object[][] forwObject = new Object[1][2];
		forwObject[0][0] = leaveApplication.getForwardId();
		forwObject[0][1] = leaveApplication.getLeaveCode();
		boolean result =getSqlModel().singleExecute(getQuery(28), forwObject);
		if(result){
			String delete = "DELETE FROM HRMS_LEAVE_PATH WHERE LEAVE_APPL_CODE ='"+leaveApplication.getLeaveCode()+"' ";
			result = getSqlModel().singleExecute(delete);
			if(result){
				Object[][] leavPath = new Object[2][2];	
				
				leavPath[0][0] = leaveApplication.getLeaveCode();
				leavPath[0][1] = leaveApplication.getForwardId();
				
				leavPath[1][0] = leaveApplication.getLeaveCode();
				leavPath[1][1] = leaveApplication.getApproverId();
				result = getSqlModel().singleExecute(getQuery(26), leavPath);
			}
		}
		return result ;
	}
	/**
	 * 
	 * @param leaveApplication
	 * @return boolean METHOD FOR SAVE RECORD BY APPROVER          ***************By Sunil*******************
	 */
	public boolean saveByApprover(LeaveApplication leaveApplication) {

		Object[][] byApprover = new Object[1][4];
		
		boolean result=false;
		
		byApprover[0][0] = leaveApplication.getAssesedComment();
		byApprover[0][1] = leaveApplication.getAssesedApproved();
		byApprover[0][2] = leaveApplication.getLeaveCode();
		byApprover[0][3] = leaveApplication.getUserEmpId();

		result = getSqlModel().singleExecute(getQuery(12), byApprover);
		
		Object[][] updateHdr = new Object[1][3];
		logger.info("comments*******************"+leaveApplication.getAssesedApproved());
		updateHdr[0][0] = leaveApplication.getApproverId();
		updateHdr[0][1] = leaveApplication.getAssesedApproved();
		updateHdr[0][2] = leaveApplication.getLeaveCode();
		
		result =getSqlModel().singleExecute(getQuery(22), updateHdr);
		
		if(leaveApplication.getAssesedApproved().equals("R") || leaveApplication.getAssesedApproved().equals("N")){
		String balQuery = "SELECT LEAVE_DAYS,EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "+
							"WHERE LEAVE_APPLICATION_CODE='"+leaveApplication.getLeaveCode()+"' ";
		Object[][] balObj = getSqlModel().getSingleResult(balQuery);
		if(balObj.length >0){
			balQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "+
					" WHERE EMP_ID =? AND LEAVE_CODE =?";
			result =getSqlModel().singleExecute(balQuery, balObj);
			logger.info("rejected---------------------" +result);
		}
		}else if(leaveApplication.getAssesedApproved().equals("A")){
			Object [][] objDtl = new Object[1][2];
			objDtl[0][0] ="A";
			objDtl[0][1] =leaveApplication.getLeaveCode();
			String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = ? "
								+" WHERE LEAVE_APPLICATION_CODE= ? ";
				getSqlModel().singleExecute(updateDtl, objDtl);	
		}
		return result;
	}
	/**
	 * METHOD FOR SELECT RECORD FOR LEAVE APPLICATION			***************By Sunil*******************
	 * @param leaveApplication
	 * @return
	 */
	public boolean getLeaveApplication(LeaveApplication leaveApplication) {
		try {

			Object[] bean = new Object[1];

			bean[0] = leaveApplication.getLeaveCode();
			logger.info("leave Code -------"+ leaveApplication.getLeaveCode());
			Object[][] values = getSqlModel()
					.getSingleResult(getQuery(5), bean);

			leaveApplication.setLeaveCode(String
					.valueOf(values[0][0]));
			leaveApplication.setEmpCode(String
					.valueOf(values[0][1]));
			leaveApplication.setEmpName(String.valueOf(values[0][2]));
			leaveApplication.setDepartment(String.valueOf(values[0][3]));
			leaveApplication.setCenter(String.valueOf(values[0][4]));
			leaveApplication.setApplicationDate(String.valueOf(values[0][5]));
			leaveApplication.setLeaveFromDate(String.valueOf(values[0][6]));
			leaveApplication.setLeaveToDate(String.valueOf(values[0][7]));
		//	leaveApplication.setLeaveTotalDays(String.valueOf(values[0][8]));
			Object comm = values[0][9];
			if(comm != null)
				leaveApplication.setComments(String.valueOf(values[0][9]));
			else
				leaveApplication.setComments("");
			Object cont = values[0][10];
			if(cont != null)
				leaveApplication.setContactDetails(String.valueOf(values[0][10]));
			else
				leaveApplication.setContactDetails("");
			leaveApplication.setStatus(String.valueOf(values[0][11]));
			leaveApplication.setReliever(String.valueOf(values[0][12]));
			leaveApplication.setRelieverName(String.valueOf(values[0][13]));
			Object prif = values[0][14];
			if(prif != null)
				leaveApplication.setPrefix(String.valueOf(values[0][14]));
			else
				leaveApplication.setPrefix("");
			Object suff = values[0][15];
			if(suff != null)
				leaveApplication.setSuffix(String.valueOf(values[0][15]));
			else
				leaveApplication.setSuffix("");
			Object medCert = values[0][16];
			if(medCert != null)
				leaveApplication.setMedicalCert(String.valueOf(values[0][16]));
			else
				leaveApplication.setMedicalCert("");
			logger.info("PREFIX: " + String.valueOf(values[0][15]));
			//leaveApplication.setForwardId(String.valueOf(values[0][17]));
			//leaveApplication.setForwardName(String.valueOf(values[0][18]));
			

			leaveApplication.setToDraw(String.valueOf(values[0][19]));
			leaveApplication.setRationRW(String.valueOf(values[0][20]));
			Object weDate = values[0][21];
			if(weDate != null)
				leaveApplication.setWefDate(String.valueOf(values[0][21]));
			else
				leaveApplication.setWefDate("");
			Object lettNo = values[0][22];
			if(lettNo != null)
				leaveApplication.setLetterNo(String.valueOf(values[0][22]));
			else
				leaveApplication.setLetterNo("");
			Object lettDate = values[0][23];
			if(lettDate != null)
				leaveApplication.setLetterDate(String.valueOf(values[0][23]));
			else
				leaveApplication.setLetterDate("");
			
			leaveApplication.setRationCHQ(String.valueOf(values[0][24]));
			leaveApplication.setTokenNo(String.valueOf(values[0][25]));
			leaveApplication.setIsOfficer(String.valueOf(values[0][26]));
			
			Object[][] pathValues = getSqlModel()
			.getSingleResult(getQuery(21), bean);
			
			leaveApplication.setApproverId(String.valueOf(pathValues[0][0]));
			leaveApplication.setApproverName(String.valueOf(pathValues[0][1]));
			leaveApplication.setForwardId(String.valueOf(pathValues[1][0]));
			leaveApplication.setForwardName(String.valueOf(pathValues[1][1]));
			return true;
			
		} catch (Exception e) {

			logger.info(e);

			return false;
		}
	}

	/**
	 * METHOD FOR SHOW RECORD BY APPROVER                  ***************By Sunil*******************
	 * 
	 * @param leaveApplication
	 * @param appcode
	 * @return
	 */
	public boolean showLeaveApplication(LeaveApplication leaveApplication,
			String appcode) {
		try {


			Object[] bean = new Object[1];

			bean[0] = appcode;
			logger.info("leave Code -------"+ appcode);
			Object[][] values = getSqlModel()
					.getSingleResult(getQuery(5), bean);

			leaveApplication.setLeaveCode(String
					.valueOf(values[0][0]));
			leaveApplication.setEmpCode(String
					.valueOf(values[0][1]));
			leaveApplication.setEmpName(String.valueOf(values[0][2]));
			leaveApplication.setDepartment(String.valueOf(values[0][3]));
			leaveApplication.setCenter(String.valueOf(values[0][4]));
			leaveApplication.setApplicationDate(String.valueOf(values[0][5]));
			leaveApplication.setLeaveFromDate(String.valueOf(values[0][6]));
			leaveApplication.setLeaveToDate(String.valueOf(values[0][7]));
		//	leaveApplication.setLeaveTotalDays(String.valueOf(values[0][8]));
			Object comm = values[0][9];
			if(comm != null)
				leaveApplication.setComments(String.valueOf(values[0][9]));
			else
				leaveApplication.setComments("");
			Object cont = values[0][10];
			if(cont != null)
				leaveApplication.setContactDetails(String.valueOf(values[0][10]));
			else
				leaveApplication.setContactDetails("");
			leaveApplication.setStatus(String.valueOf(values[0][11]));
			leaveApplication.setReliever(String.valueOf(values[0][12]));
			leaveApplication.setRelieverName(String.valueOf(values[0][13]));
			Object prif = values[0][14];
			if(prif != null)
				leaveApplication.setPrefix(String.valueOf(values[0][14]));
			else
				leaveApplication.setPrefix("");
			Object suff = values[0][15];
			if(suff != null)
				leaveApplication.setSuffix(String.valueOf(values[0][15]));
			else
				leaveApplication.setSuffix("");
			Object medCert = values[0][16];
			if(medCert != null)
				leaveApplication.setMedicalCert(String.valueOf(values[0][16]));
			else
				leaveApplication.setMedicalCert("");
			logger.info("PREFIX: " + String.valueOf(values[0][15]));
			//leaveApplication.setForwardId(String.valueOf(values[0][17]));
			//leaveApplication.setForwardName(String.valueOf(values[0][18]));
			

			leaveApplication.setToDraw(String.valueOf(values[0][19]));
			leaveApplication.setRationRW(String.valueOf(values[0][20]));
			Object weDate = values[0][21];
			if(weDate != null)
				leaveApplication.setWefDate(String.valueOf(values[0][21]));
			else
				leaveApplication.setWefDate("");
			Object lettNo = values[0][22];
			if(lettNo != null)
				leaveApplication.setLetterNo(String.valueOf(values[0][22]));
			else
				leaveApplication.setLetterNo("");
			Object lettDate = values[0][23];
			if(lettDate != null)
				leaveApplication.setLetterDate(String.valueOf(values[0][23]));
			else
				leaveApplication.setLetterDate("");
			
			leaveApplication.setRationCHQ(String.valueOf(values[0][24]));
			leaveApplication.setTokenNo(String.valueOf(values[0][25]));
			leaveApplication.setIsOfficer(String.valueOf(values[0][26]));
			
			Object[][] pathValues = getSqlModel()
			.getSingleResult(getQuery(21), bean);
			
			leaveApplication.setApproverId(String.valueOf(pathValues[0][0]));
			leaveApplication.setApproverName(String.valueOf(pathValues[0][1]));
			leaveApplication.setForwardId(String.valueOf(pathValues[1][0]));
			leaveApplication.setForwardName(String.valueOf(pathValues[1][1]));
			HashMap statMap = new HashMap ();
			if(String.valueOf(pathValues[0][0]).equals(leaveApplication.getUserEmpId())){
				statMap.put("R", "Reject");
				statMap.put("A", "Approve");
				leaveApplication.setStatMap(statMap);
			}else if(String.valueOf(pathValues[1][0]).equals(leaveApplication.getUserEmpId())){
				statMap.put("R", "Reject");
				statMap.put("C", "Recommend");
				leaveApplication.setStatMap(statMap);
			}
			else{
				leaveApplication.setIsAdmin("false");
				logger.info("-----------------------------------------------");
			}
			leaveApplication.setIsForApprove("true");
			return true;

		} catch (Exception e) {

			logger.info(e);

			return false;
		}
	}

	public boolean deleteLeaveApplication(LeaveApplication leaveApplication) {

		Object[][] bean = new Object[1][1];
		bean[0][0] = leaveApplication.getLeaveCode();

		boolean result = getSqlModel().singleExecute(getQuery(4), bean);

		return result;
	}

	public boolean getRelieverName(LeaveApplication leaveApplication) {

		try {
			Object[] bean = new Object[1];

			bean[0] = leaveApplication.getReliever();

			logger.info("Reliver ID: " + bean[0]);

			Object[][] result = getSqlModel()
					.getSingleResult(getQuery(6), bean);

			leaveApplication.setRelieverName(String.valueOf(result[0][0]));

			logger.info("Reliver Name: " + result[0][0]);

			return true;

		} catch (Exception e) {
			logger.info("LeaveApplicationModel getRelieverName" + e);
			return false;
		}

	}
	/**
	 * METHODS FOR UPDATE LEAVEAPPLICATION              ***************Modified- Sunil*******************
	 * @param leaveApplication
	 * @return
	 */
	public boolean updateLeaveApplication(LeaveApplication leaveApplication) {
		
		boolean result = false;
		if(leaveApplication.getLeaveCode().equals("") || leaveApplication.getLeaveCode()== null){
			return false ;
		}
		if(!(leaveApplication.getStatus().trim().equals("Recomended")||leaveApplication.getStatus().trim().equals("Approved") || leaveApplication.getStatus().trim().equals("Rejected") || leaveApplication.getStatus().trim().equals("Cancel") )){
			Object[][] bean = new Object[1][19];
	
			bean[0][0] = leaveApplication.getEmpCode();
			bean[0][1] = leaveApplication.getApplicationDate();
			bean[0][2] = leaveApplication.getLeaveFromDate();
			bean[0][3] = leaveApplication.getLeaveToDate();
			bean[0][4] = leaveApplication.getLeaveTotalDays();
			bean[0][5] = leaveApplication.getComments();
			bean[0][6] = leaveApplication.getContactDetails();
		//	bean[0][7] = leaveApplication.getStatus();
			if(leaveApplication.getReliever().equals("") || leaveApplication.getReliever().equals("null") || leaveApplication.getReliever() == null)
				bean[0][7] = "";
			else
				bean[0][7] = leaveApplication.getReliever();
			bean[0][8] = leaveApplication.getPrefix();
			bean[0][9] = leaveApplication.getSuffix();
			bean[0][10] = leaveApplication.getMedicalCert();
			bean[0][11] = leaveApplication.getForwardId();
			
			bean[0][12] = leaveApplication.getToDraw();
			bean[0][13] = leaveApplication.getRationRW();
			bean[0][14] = leaveApplication.getWefDate();
			bean[0][15] = leaveApplication.getLetterNo();
			bean[0][16] = leaveApplication.getLetterDate();
			bean[0][17] = leaveApplication.getRationCHQ();
			bean[0][18] = leaveApplication.getLeaveCode();
			
			
			result = getSqlModel().singleExecute(getQuery(2), bean);
			if(result){
				String delete = "DELETE FROM HRMS_LEAVE_PATH WHERE LEAVE_APPL_CODE ='"+leaveApplication.getLeaveCode()+"' ";
				result = getSqlModel().singleExecute(delete);
				if(result){
					Object[][] leavPath = new Object[2][2];	
					
					leavPath[0][0] = leaveApplication.getLeaveCode();
					leavPath[0][1] = leaveApplication.getForwardId();
					
					leavPath[1][0] = leaveApplication.getLeaveCode();
					leavPath[1][1] = leaveApplication.getApproverId();
					result = getSqlModel().singleExecute(getQuery(26), leavPath);
				}
			}
		}
		return result;

	}
	
		/*****************************NOT IN USED*****************************/
	
	public void getLeaveEditDtl(LeaveApplication leaveApplication,
			HttpSession session) {
		logger.info("getLeaveEditDtl:");

		ArrayList<LeaveApplication> att = new ArrayList<LeaveApplication>();
		int size = 0;
		try {

			if ((ArrayList) session.getAttribute("att") != null) {

				att = (ArrayList) session.getAttribute("att");
				logger.info("qwe********" + att.size());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("qwe****" + att.size());

		LeaveApplication levApp = new LeaveApplication();

		LeaveApplication editBean = (LeaveApplication) att.get(Integer
				.parseInt(leaveApplication.getParacode()));
		logger.info("Paracode()):"
				+ Integer.parseInt(leaveApplication.getParacode()));
		leaveApplication.setSlevCode(editBean.getLevCode());
		leaveApplication.setSlevType(editBean.getLevType());
		logger.info("leaveApplication.getLevType():"
				+ leaveApplication.getLevType());
		leaveApplication.setSlevOpeningBalance(editBean.getLevOpeningBalance());
		leaveApplication.setSleaveFromDtl(editBean.getLeaveFromDtl());
		leaveApplication.setSleaveToDtl(editBean.getLeaveToDtl());
		leaveApplication.setSlevClosingBalance(editBean.getLevClosingBalance());

		att.remove(Integer.parseInt(leaveApplication.getParacode()));
		try {
			session.setAttribute("att", att);
		} catch (Exception e) {
			e.printStackTrace();
		}
		leaveApplication.setAtt(att);

	}
	public void getTableDetails(LeaveApplication leaveApplication) {

		logger.info("GET TABLE DETAILS:");

		String code="SELECT MAX(LEAVE_APPL_CODE) FROM HRMS_LEAVE_HDR";
		Object[][] lcode = getSqlModel().getSingleResult(code);
		
		Object[] bean = new Object[1];
			bean[0] = lcode[0][0];

		logger.info("LEAVE CODE ITERAOTN :" + bean[0]);

		Object[][] result = getSqlModel().getSingleResult(getQuery(13), bean);
		ArrayList<Object> att = new ArrayList<Object>();

		if (null != result && result.length > 0) {
			for (int i = 0; i < result.length; i++) {
				LeaveApplication levApp = new LeaveApplication();
				levApp.setLeaveCode(String.valueOf(result[i][0]));
				levApp.setSlevCode(String.valueOf(result[i][1]));
				levApp.setSlevType(String.valueOf(result[i][2]));
				levApp.setSlevOpeningBalance(String.valueOf(result[i][3]));
				levApp.setSleaveFromDtl(String.valueOf(result[i][4]));
				levApp.setSleaveToDtl(String.valueOf(result[i][5]));
				levApp.setSlevClosingBalance(String.valueOf(result[i][6]));
				levApp.setRemark(String.valueOf(result[i][7]));
				levApp.setLeaveId(String.valueOf(result[i][8]));
				
				att.add(levApp);
			}

			logger.info("TABLE DATA SIZE:" + att.size());
			leaveApplication.setAtt(att);
		}
	}

	public void getTableHistoryDetails(LeaveApplication leaveApplication) {

		logger.info("GET TABLE DETAILS:");

		Object[] bean = new Object[1];
		ArrayList list = new ArrayList();
		bean[0] = leaveApplication.getLeaveCode();

		logger.info("LEAVE CODE ITERAOTN " + bean[0]);

		Object[][] result = getSqlModel().getSingleResult(getQuery(11), bean);

		for (int i = 0; i < result.length; i++) {
			LeaveApplication levApp = new LeaveApplication();
			levApp.setLevType(String.valueOf(result[i][0]));
			levApp.setLevOpeningBalance(String.valueOf(result[i][1]));
			levApp.setLevClosingBalance(String.valueOf(result[i][2]));
			levApp.setLeaveFromDtl(String.valueOf(result[i][3]));
			levApp.setLeaveToDtl(String.valueOf(result[i][4]));

			list.add(levApp);
		}
		leaveApplication.setAtt(list);
	}

	public void getLeaveReport(LeaveApplication leaveApplication) {
		logger.info("inside getLeaveReport");
		Object addObj[] = new Object[1];
		addObj[0] = leaveApplication.getLeaveCode();
		logger.info("addObj[0]: " + addObj[0]);
		Object[][] data = getSqlModel().getSingleResult(getQuery(27), addObj); //replace query 8 to 27
		int totalLeav = 0;
		logger.info("data: " + data.length);

		ArrayList<Object> att = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			LeaveApplication bean1 = new LeaveApplication();

			bean1.setLevType(String.valueOf(data[i][1]));
			/*bean1.setLevOpeningBalance(String.valueOf(data[i][2]));
			bean1.setLevClosingBalance(String.valueOf(data[i][3]));*/
			bean1.setLeaveFromDtl(String.valueOf(data[i][2]));
			bean1.setLeaveToDtl(String.valueOf(data[i][3]));
			bean1.setLevTotal(String.valueOf(data[i][4]));
			totalLeav+= Integer.parseInt(String.valueOf(data[i][4]));
			att.add(bean1);
		}
		leaveApplication.setAtt(att);
		leaveApplication.setLeaveTotalDays(String.valueOf(totalLeav));
	//	getLeaveComment(leaveApplication);
		getRecomended_Approved(leaveApplication);
	}

	public void getLeaveComment(LeaveApplication leaveApplication) {
		logger.info("inside getLeaveComment");
		try{
		Object addObj[] = new Object[1];
		addObj[0] = leaveApplication.getLeaveCode();
		logger.info("addObj[0]: " + addObj[0]);
		Object[][] data1 = getSqlModel().getSingleResult(getQuery(9), addObj);

		logger.info("data1: " + data1.length);

		ArrayList<Object> leaveStatus = new ArrayList<Object>();

		for (int i = 0; i < data1.length; i++) {
			LeaveApplication bean1 = new LeaveApplication();

			bean1.setAssesedName(String.valueOf(data1[i][1]));
			bean1.setAssesedApproved(String.valueOf(data1[i][2]));
			bean1.setAssesedComment(String.valueOf(data1[i][3]));
			bean1.setAssesedDate(String.valueOf(data1[i][4]));
			
			leaveStatus.add(bean1);
		}
		leaveApplication.setLeaveStatus(leaveStatus);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void getAddressDetails(LeaveApplication leaveApplication) {
		String addrStr = " SELECT ADD_1 ,ADD_2,ADD_3,HRMS_CITY.CITY_NAME,ADD_STATE,ADD_CNTRY, "
						+" ADD_PH1,ADD_PH2,ADD_MOBILE " 
						+" FROM HRMS_EMP_ADDRESS "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+" LEFT JOIN HRMS_CITY ON (HRMS_EMP_ADDRESS.ADD_CITY = HRMS_CITY.CITY_ID)"
						+" WHERE HRMS_EMP_ADDRESS.EMP_ID ='"+leaveApplication.getEmpCode()+"' AND ADD_TYPE = '"+leaveApplication.getContactDetails()+"'";
		Object[][] values = getSqlModel().getSingleResult(addrStr);
		String address = "";
		if (values.length >0) {
			for (int i = 0; i < values[0].length; i++) {
				Object obj = values[0][i];
				if(obj != null){
					address+=values[0][i]+"\n";
				}
				
			}
			
		}
		leaveApplication.setContactDetails(address);
		
	}
	public void  getEmployeeDetails(String empId, LeaveApplication leaveApplication)
	{
	Object[] beanObj = new Object[1];
	beanObj[0] =empId ;
	String query =" SELECT HRMS_EMP_OFFC.EMP_ID, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_ID||' - '||HRMS_CENTER.CENTER_NAME,HRMS_EMP_OFFC.EMP_TOKEN,EMP_TYPE"
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " 
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE   "
				+"  WHERE HRMS_EMP_OFFC.EMP_ID =? ";
	Object[][] values = getSqlModel().getSingleResult(query, beanObj);
	logger.info("addApplication:-------------------"+values.length);
	logger.info("addApplication:-------------------"+String.valueOf(beanObj[0]));
	if (leaveApplication.isGeneralFlag()) {
	leaveApplication.setEmpCode(String.valueOf(values[0][0]));
	leaveApplication.setEmpName(String.valueOf(values[0][1]));
	leaveApplication.setDepartment(String.valueOf(values[0][2]));
	leaveApplication.setCenter(String.valueOf(values[0][3]));
	leaveApplication.setTokenNo(String.valueOf(values[0][4]));
	leaveApplication.setIsOfficer(String.valueOf(values[0][5]));
	Date date = new Date();
	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	String sysDate = formater.format(date);
	leaveApplication.setApplicationDate(sysDate);	
	}
	leaveApplication.setIsOfficer(String.valueOf(values[0][5]));
	}
	public String getEmpType(LeaveApplication leaveApplication){
		String empString ="SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE  EMP_ID = '"+leaveApplication.getEmpCode()+"' ";
		Object[][] empValues = getSqlModel().getSingleResult(empString);
		if (String.valueOf(empValues[0][0]).equals("4") || String.valueOf(empValues[0][0]).equals("7")) {
			leaveApplication.setIsOfficer("true");
		}
		return "";
	}
	public void getRecomended_Approved(LeaveApplication leaveApplication){
		Object[] appObj = new Object[1];
		appObj[0] = leaveApplication.getLeaveCode();
		Object[][] values = getSqlModel().getSingleResult(getQuery(24), appObj);
		
		if (values.length >0) {
			leaveApplication.setAssesedName(String.valueOf(values[0][0]));
			leaveApplication.setAssesedComment(String.valueOf(values[0][1]));
			leaveApplication.setAssesedDate(String.valueOf(values[0][2]));
			leaveApplication.setApproverName(String.valueOf(values[1][0]));
			leaveApplication.setApproverId(String.valueOf(values[1][1]));
			leaveApplication.setForwardName(String.valueOf(values[1][2]));
		}
		
	}
	
	public String validForAddLeave(LeaveApplication bean){

		String message;
		
		try { 
			/**
			 * IF USER IS UPDATING LEAVE, IT WILL CHECK 
			 */
			if (!(bean.getLeaveId().equals("") || bean.getLeaveId() == null )) { 
				String testDate = " SELECT TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
						+ " FROM HRMS_LEAVE_DTL "
						+ " WHERE (LEAVE_TO_DATE >= TO_DATE('"
						+ bean.getLeaveFromDtl()
						+ "','DD-MM-YYYY') "
						+ " AND LEAVE_FROM_DATE <= TO_DATE('"
						+ bean.getLeaveToDtl()
						+ "','DD-MM-YYYY') ) "
						+ " AND EMP_ID =" + bean.getEmpCode()+" AND LEAVE_DTL_ID !="+bean.getLeaveId();
				Object[][] valid = getSqlModel().getSingleResult(testDate);
				message = "";
				if (valid != null && valid.length > 0) {
					message = "You can not apply leave between "
							+ bean.getLeaveFromDtl() + " and "
							+ bean.getLeaveToDtl();
					return message;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			/**
			 * IF USER IS ADDING NEW LEAVE, IT WILL CHECK 
			 */
			
			if ((bean.getLeaveId().equals("") || bean.getLeaveId() == null )) { 
				String testDate = " SELECT TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
						+ " FROM HRMS_LEAVE_DTL "
						+ " WHERE (LEAVE_TO_DATE >= TO_DATE('"
						+ bean.getLeaveFromDtl()
						+ "','DD-MM-YYYY') "
						+ " AND LEAVE_FROM_DATE <= TO_DATE('"
						+ bean.getLeaveToDtl()
						+ "','DD-MM-YYYY') ) "
						+ " AND EMP_ID =" + bean.getEmpCode();
				Object[][] valid = getSqlModel().getSingleResult(testDate);
				message = "";
				if (valid != null && valid.length > 0) {
					message = "You can not apply leave between "
							+ bean.getLeaveFromDtl() + " and "
							+ bean.getLeaveToDtl();
					return message;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			/**
			 * IF USER IS ADDING NEW LEAVE, IT WILL CHECK CONSECUTIVE LEAVE ACOORDING TO LEAVE POLICY 
			 */
			String consicutiveDate = " SELECT HRMS_LEAVE_DTL.LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
					+ " FROM HRMS_LEAVE_DTL "
					+ " INNER JOIN HRMS_LEAVE_POLICY ON (HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE_POLICY.LEAVE_CODE "
					+ " AND HRMS_LEAVE_POLICY.LEAVE_CONSECUTIVE_FLAG ='Y' )"
					+ " WHERE EMP_ID = "
					+ bean.getEmpCode()
					+ " AND EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ bean.getEmpCode()
					+ ")"
					+ " AND LEAVE_TO_DATE+1 = TO_DATE('"+bean.getLeaveFromDtl()+"','DD-MM-YYYY') ";
				//	+ " AND LEAVE_DTL_ID !="+bean.getLeaveId();
			Object[][] hasData = getSqlModel().getSingleResult(consicutiveDate);
			if (hasData != null && hasData.length > 0) {
				message = "You can not apply leave from "
						+ bean.getLeaveFromDtl();
				return message;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "" ;
	}
	
}