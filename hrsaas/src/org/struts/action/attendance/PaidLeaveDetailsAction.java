package org.struts.action.attendance;

import org.paradyne.bean.attendance.PaidLeaveDetails;
import org.paradyne.lib.Utility;
import org.paradyne.model.attendance.PaidLeaveDetailsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Vishwambhar
 * @date 26-04-2008
**/

/**
 * This class view and modifies the leave details
**/

public class PaidLeaveDetailsAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	PaidLeaveDetails paidLeave;

	public void prepare_local() throws Exception {
		paidLeave = new PaidLeaveDetails();
	}

	public PaidLeaveDetails getPaidLeave() {
		return paidLeave;
	}

	public void setPaidLeave(PaidLeaveDetails paidLeave) {
		this.paidLeave = paidLeave;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return paidLeave;
	}

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE DETAILS
	 * 
	 * @return String
	 */
	public String retriveDetails() {

		try {
			
			PaidLeaveDetailsModel model = new PaidLeaveDetailsModel();
			model.initiate(context, session);
			String empCode = request.getParameter("eId");// employee code
		
			String attendanceCode = request.getParameter("attdnCode");// attendance
			// code
			String paidLevs = request.getParameter("paidLevs");// paid leaves
			String totAbsLD = request.getParameter("totAbsLD");
			String fDate = request.getParameter("fDate");
			String tDate = request.getParameter("tDate");
			String attConn = request.getParameter("attConn");
			
			String year = paidLeave.getYear();// year
			String month = paidLeave.getMonth();// month
			paidLeave.setMonthInt(month);// setting month
			paidLeave.setAttCode(attendanceCode);// setting attendance code
			paidLeave.setPaidLevs(paidLevs);// setting paid leaves
			paidLeave.setTotAbsLD(totAbsLD);
			paidLeave.setFDate(fDate);
			paidLeave.setTDate(tDate);
			paidLeave.setAttConnFlagLD(attConn);
			model.getRecord(paidLeave, paidLeave.getHiddenEmpid(),
					paidLeave.getAttCode(),paidLeave.getMonth(),paidLeave.getYear());
		
			model.getLateAndHalfDay(paidLeave, paidLeave.getHiddenEmpid(),
					paidLeave.getAttCode(),paidLeave.getMonth(),paidLeave.getYear());

			model.getUnpaidRecord(paidLeave, paidLeave.getHiddenEmpid(),
					paidLeave.getAttCode(),paidLeave.getMonthInt(),paidLeave.getYear());
			
			model.getEmpName(paidLeave, paidLeave.getHiddenEmpid());
			
			paidLeave.setMonth(Utility.month(Integer.parseInt(paidLeave
					.getMonth())));
			
			paidLeave.setYear(paidLeave.getYear());
			
			paidLeave.setMonthInt(paidLeave.getMonthInt());
			
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger
					.error("Exception in retriveDetails--------------------------"
							+ e);
		}
		return SUCCESS;
	}// end of retriveDetails

	/**
	 * THIS METHOD IS USED FOR SAVING RECORD
	 * 
	 * @return String
	 */
	public String save() {
		
		try {
			PaidLeaveDetailsModel model = new PaidLeaveDetailsModel();
			model.initiate(context, session);
			String[] leavId = request.getParameterValues("leaveCode");// leave code
			// code
			String[] sysAdjustedLeave = request.getParameterValues("sysAdjustLeave");// System Adjusted Leaves
		
			String[] lateMarks = request.getParameterValues("adjustLateMark");// Late Marks
		 
			String[] halfDays = request.getParameterValues("adjustHalfDays");// Half Days
			 
			String[] manualAdjustedLeave = request.getParameterValues("manualAdjustLeave");// Manually Adjusted Leaves
		 
			String[] balance = request.getParameterValues("balance");//  Current Leave Balance
			
			String manualAdjustUnpaid = request.getParameter("manualAdjustUnpaid");// Manually Adjusted Unpaid Leaves 
			
			String sysAdjustUnpaid = request.getParameter("sysAdjustUnpaid");//System Adjusted Unpaid Leaves
			
			String[] originalBal = request.getParameterValues("originalBal");//Original Leave Balance
			
			
			
			boolean flag = model.save(paidLeave, paidLeave.getAttCode(),
					paidLeave.getHiddenEmpid(), leavId, sysAdjustedLeave, lateMarks,
					halfDays,manualAdjustedLeave,balance,manualAdjustUnpaid, sysAdjustUnpaid,originalBal);
			
			model.getRecord(paidLeave, paidLeave.getHiddenEmpid(),
					paidLeave.getAttCode(),paidLeave.getMonthInt(),paidLeave.getYear());
			
			model.getEmpName(paidLeave, paidLeave.getHiddenEmpid());
			
			paidLeave.setMonth(paidLeave.getMonth());// month
			
			paidLeave.setYear(paidLeave.getYear());// year
			if (flag) {

				addActionMessage(getMessage("save"));
			}// end of if
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in save--------------------------" + e);
		}
		return SUCCESS;

	}// end of save

	/**
	 * THIS METHOD IS USED FOR GO
	 * 
	 * @return String
	 */
	public String recalculate() {
		

		try {
			PaidLeaveDetailsModel model = new PaidLeaveDetailsModel();
			model.initiate(context, session);
			String lateMarks = request.getParameter("lateMarks1");// late
			// marks
			String halfDay = request.getParameter("halfDay1");// half day
		
			model.adjustLatemarkAndHalfday(paidLeave, paidLeave
					.getHiddenEmpid(), paidLeave.getAttCode(), lateMarks,
					halfDay, request);

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
		}
		return SUCCESS;
	}// end of go

}// end of class
