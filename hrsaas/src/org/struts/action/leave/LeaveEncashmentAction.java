package org.struts.action.leave;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.paradyne.bean.leave.LeaveEncashment;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeaveEncashmentModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author VISHWAMBHAR DESHPANDE
 */
public class LeaveEncashmentAction extends ParaActionSupport {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	private LeaveEncashment leaveEn;

	public LeaveEncashment getLeaveEn() {
		return this.leaveEn;
	}

	public void setLeaveEn(LeaveEncashment leaveEn) {
		this.leaveEn = leaveEn;
	}

	public Object getModel() {

		return this.leaveEn;
	}

	public void prepare_local() throws Exception {

		this.leaveEn = new LeaveEncashment();
		this.leaveEn.setMenuCode(298);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		
	}
	
	public String input() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			if (this.leaveEn.isGeneralFlag()) {
				this.leaveEn = model.empDetails(this.leaveEn.getUserEmpId(), this.leaveEn);
				this.leaveEn.setImageFlag("false");
				final String policyCode = model.getLeavePolicyCode(this.leaveEn.getEmpId());
				if (policyCode == null && policyCode.equals("")) {
					this.addActionMessage("Policy is not define for employee" + this.leaveEn.getEmpName());
				} else {
					final String[][] values = model.getLeaveRecord(this.leaveEn,
							policyCode);
					if (values != null && values.length > 0) {
						this.leaveEn.setIsFlag("true");
						this.leaveEn.setIsResult("true");
						this.leaveEn.setIsTotal("true");
						request.setAttribute("values", values);
						this.leaveEn.setStatusFlag("");
						this.leaveEn.setTotalAmt("0");
					} // end of nested if
					this.setApproverList(this.leaveEn.getEmpId());
					System.out.println("leaveEn.getEmpId()===="+leaveEn.getEmpId());
				}
			} // end of if
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception in prepare_withLoginProfileDetails---------" + e);

		}
		return INPUT;
	}
	
	public void setApproverList(String empCode) {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			final ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			final Object[][] empFlow = model1.generateEmpFlow(empCode, "LeaveEncash");
			model.setApproverData(this.leaveEn, empFlow);
			model1.terminate();
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}


	/**
	 * THIS METHOD IS USED FOR DISPLAYING LEAVE TYPE AND LEAVE BALANCE
	 * 
	 * @return String
	 */
	public String LeaveTypeRecord() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			this.leaveEn.setEnComment("");
			this.leaveEn.setTotalAmt("0");
			final String policyCode = model.getLeavePolicyCode(this.leaveEn.getEmpId());
			this.logger.info("policyCode------encash application-------------" + policyCode);
			if (policyCode.equals("") || policyCode.equals("null")) {
				this.addActionMessage("Policy is not define for employee- " + this.leaveEn.getEmpName());
			} else {
				final String[][] values = model.getLeaveRecord(this.leaveEn, policyCode);
				if (values.length > 0) {
					this.leaveEn.setIsFlag("true");
					this.leaveEn.setIsResult("true");
					this.leaveEn.setIsTotal("true");
					request.setAttribute("values", values);
					this.leaveEn.setStatusFlag("");
				} // end of if
				this.leaveEn.setStatusFlag("");
				this.leaveEn.setEnCode("");
			}
			
			this.setApproverList(this.leaveEn.getEmpId());
			this.leaveEn.setInformName("");
			model.terminate();

		} catch (final Exception e) {
			this.logger.error("Exception in LeaveTypeRecord-------------" + e);
		}
		return INPUT;
	} // end of LeaveTypeRecord

	public String reset() {

		try {
			
			this.leaveEn.setEmpId("");
			this.leaveEn.setEmpToken("");
			this.leaveEn.setEmpName("");
			this.leaveEn.setCenterName("");
			this.leaveEn.setCenterID("");
			this.leaveEn.setDesgID("");
			this.leaveEn.setDesgName("");
			this.leaveEn.setRankName("");
			this.leaveEn.setRankID("");
			this.leaveEn.setLeaveName("");
			this.leaveEn.setLeaveCode("");
			this.leaveEn.setLeaveId("");
			this.leaveEn.setClBal("");
			this.leaveEn.setEnBal("");
			this.leaveEn.setEncashDate("");
			this.leaveEn.setEnComment("");
			this.leaveEn.setTotalAmt("");
			this.leaveEn.setTotal("");
			this.leaveEn.setEnCode("");
			this.leaveEn.setEncashLock("");
			this.leaveEn.setApprovalFlag("true");
			this.leaveEn.setImageFlag("true");
			this.leaveEn.setMaxEncashAmt("");
			this.leaveEn.setStatusFlag("");
			this.leaveEn.setApproveStatusFlag("");
			this.leaveEn.setIsFlag("false");
			this.leaveEn.setOnhold("");
			this.leaveEn.setIsResult("false");
			this.leaveEn.setIsTotal("false");
			this.leaveEn.setHiddenStatus("");
			this.leaveEn.setStatus("PD");			
			this.leaveEn.setLevel("");			
			this.leaveEn.setAmtPerDay("");
			this.leaveEn.setShowflag("false");
			this.leaveEn.setApprName("");			
			this.leaveEn.setApprComments("");
			this.leaveEn.setApprDate("");			
			this.leaveEn.setApproverDetails("false");
			this.leaveEn.setEId("");
			this.leaveEn.setLeaveType("");
			this.leaveEn.setLevCloseBalance("");
			this.leaveEn.setLeaveOnhold("");
			this.leaveEn.setLeaveEncashed("");			
			this.leaveEn.setEncashAmount("");
			this.leaveEn.setEncashStatus("");
			this.leaveEn.setEncashSelect("");			
			this.leaveEn.setHiddenEncashDays("");
			this.leaveEn.setDept("");
			this.leaveEn.setAppcode("");
			this.leaveEn.setApproverName("");
			this.leaveEn.setSrNoIterator("");
			this.leaveEn.setInformToken(""); 
			this.leaveEn.setInformName(""); 
			this.leaveEn.setInformCode(""); 
			this.leaveEn.setKeepInformToCode("");
			this.leaveEn.setKeepInformToName("");
			
			if (!this.leaveEn.isGeneralFlag()) {
				this.leaveEn.setEmpToken("");
				this.leaveEn.setEmpName("");
				this.leaveEn.setCenterName("");
				this.leaveEn.setRankName("");
				this.leaveEn.setEncashDate("");
				this.leaveEn.setEmpId("");
			} // end of if
			this.leaveEn.setTotalAmt("0");
			
			this.prepare_withLoginProfileDetails();
			
			
		} catch (final Exception e) {
			this.logger.error("Exception in reset-------------" + e);
		}
		return INPUT;
	} // end of reset

	/**
	 * THIS METHOD IS USED FOR SAVE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {

		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			String[] encashable = null;
			String[] balanceleave = null;
			String[] singleamount = null;
			final String[] encash1 = (String[]) request.getParameterValues("total"); // amount
			final String[] leaveid = (String[]) request.getParameterValues("leaveId"); // leave code
			final String[] onhold = (String[]) request.getParameterValues("onhold"); // on hold balance
			final String[] oldEncashDays = (String[]) request.getParameterValues("hiddenEncashDays"); // on hold balance
			final String[] keepInformToCode = (String[]) request.getParameterValues("keepInformToCode"); // keep inform to code
			System.out.println("keepInformToCode:" + keepInformToCode);
			final String apprStatus = request.getParameter("status"); // apprStatus
			System.out.println("apprStatus:" + apprStatus);
			final String policyCode = model.getLeavePolicyCode(this.leaveEn.getEmpId());
			if (leaveid != null && leaveid.length > 0) {
				encashable = new String[leaveid.length];
				balanceleave = new String[leaveid.length];
				singleamount = new String[leaveid.length];
			} // end of if
			if (leaveid != null && leaveid.length > 0) {
				for (int i = 0; i < leaveid.length; i++) {
					final String encash = request.getParameter("enBal" + i);
					encashable[i] = encash;
					final String clbalance = request.getParameter("leaveEn.clBal" + i);
					balanceleave[i] = clbalance;
					final String amount1 = request.getParameter("total" + i);
					singleamount[i] = amount1;
				} // end of for
			}

			// CODE ADDED BY REEBA
			boolean check = false;
			try {
				check = this.checkEncashmentSettings(leaveid, policyCode, encashable);
			} catch (final Exception e) {
				this.logger.error("Exception in calling checkEncashmentSettings method :  ", e);
			}
			this.logger.info("Check    :" + check);
			if (check) {
				final String[][] values = model.getLeaveRecord(this.leaveEn, policyCode);
				if (values != null && values.length > 0) {
					this.leaveEn.setIsFlag("true");
					this.leaveEn.setIsResult("true");
					this.leaveEn.setIsTotal("true");
					request.setAttribute("values", values);
					this.leaveEn.setStatusFlag("");
					this.leaveEn.setTotalAmt("0");
					this.leaveEn.setMaxEncashAmt("true");
				} // end of nested if
				
				return SUCCESS;
			}
			
			
			// CODE ADDED BY REEBA ENDS
			if (!check) {
				boolean result = false;
				if (this.leaveEn.getEnCode().equals("") || this.leaveEn.getEnCode().equals("null")) {

					if (policyCode.equals("") || policyCode.equals("null")) {
						this.addActionMessage("Policy is not define for employee- " + this.leaveEn.getEmpName());
					} else {
						final String[][] values1 = model.getLeaveRecord(this.leaveEn, policyCode);

						if (values1.length == 0) {
							this.addActionMessage("No leaves to be encashed ");
						} else {
							result = model.addEncashHdr(this.leaveEn, leaveid, balanceleave, encashable, encash1, onhold, keepInformToCode);
						}
						if (result) {
							final String[][] values = model.getLevRecord(this.leaveEn);
							if (values.length > 0) {
								this.leaveEn.setIsFlag("true");
								this.leaveEn.setIsResult("true");
								this.leaveEn.setIsTotal("true");
								request.setAttribute("values", values);
								this.leaveEn.setStatusFlag("");
								// this.leaveEn.setTotalAmt("0");
							} // end of nested if
							this.addActionMessage(this.getMessage("save"));
							this.leaveEn.setShowflag("true");
							
							this.sendProcessManagerAlertDraft();
						} // end of nested if
					}
				} else {
					result = model.modEncashHdr(this.leaveEn, leaveid, balanceleave,
							encashable, encash1, onhold, oldEncashDays, keepInformToCode,apprStatus);
					if (result) {
						final String[][] values = model.getLevRecord(this.leaveEn);
						if (values.length > 0) {
							this.leaveEn.setIsFlag("true");
							this.leaveEn.setIsResult("true");
							this.leaveEn.setIsTotal("true");
							request.setAttribute("values", values);
							this.leaveEn.setStatusFlag("");
							// this.leaveEn.setTotalAmt("0");
						} // end of nested if
						this.addActionMessage(this.getMessage("update"));
						this.leaveEn.setShowflag("true");
						
						this.sendProcessManagerAlertDraft();
						// reset();
					} // end of if
				} // end of else
			}
			model.terminate();
			this.ViewLeaveRecord();
			
		} // end of try
		catch (final Exception e) {

			this.logger.error("Exception in save----------------" + e);
		} // end of catch
		return "mymessages";
	} // end of save

	/**
	 * METHOD ADDED BY REEBA
	 * TO CHECK IF LEAVES TO BE ENCASHED EXCEEDS ENCASH LIMIT
	 * THAT IS SET IN POLICY
	 * @param policyCode
	 * @param leaveid
	 * @param encashable
	 * 
	 */
	public boolean checkEncashmentSettings(final String[] leaveid, final String policyCode,
			final String[] encashable) {
		this.logger.info("::     In checkEncashmentSettings     ::");
		final LeaveEncashmentModel model = new LeaveEncashmentModel();
		model.initiate(context, session);
		boolean check = false;
		final int encashMonth = Integer.parseInt(this.leaveEn.getEncashDate().substring(3,
				5));
		final int encashYear = Integer.parseInt(this.leaveEn.getEncashDate().substring(6,
				10));
		try {
			for (int i = 0; i < leaveid.length; i++) {
				double totEncashDays = 0.0d;
				double totEncashable = 0.0d;
				double totalEncash = 0.0d;
				this.logger.info("Encashable  : " + encashable[i]);
				if (!encashable[i].equals("0")) {
				this.logger.info("Leave code  : " + leaveid[i]);
				totEncashable = Double.parseDouble(encashable[i]);
				this.logger.info("totEncashable     :" + totEncashable);
				final String checkQuery = " SELECT NVL(LEAVE_ENC_MAXLIMIT,0), LEAVE_ENC_MAXLIMIT_DURATION,LEAVE_MGTYEAR_MONTH_START,LEAVE_MGTYEAR_MONTH_END " + 
						" , LEAVE_NAME FROM HRMS_LEAVE_POLICY_DTL " + 
						" LEFT JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE) " + 
						" LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE)  " + 
						" WHERE HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = " + policyCode + 
						" AND LEAVE_CODE = " + leaveid[i];
				final Object[][] checkObj = model.getSqlModel().getSingleResult(
						checkQuery);
				final int yearStartMonth = Integer.parseInt(String.valueOf(checkObj[0][2]));

				final int[] monthArray = this.getMonthArray(encashMonth, String.valueOf(checkObj[0][1]), yearStartMonth);
				this.logger.info("monthArray.length   : " + monthArray.length);
				for (int j = 0; j < monthArray.length; j++) {
					//this.logger.info("monthArray   : " + monthArray[j]);
				}
				if (monthArray.length > 0) {
					final String chkPrevEncQuery = " SELECT HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE, NVL(LEAVE_ENCASHED,0), TO_CHAR(ENCASH_DATE,'DD-MM-YYYY') "
							+ 
							" FROM HRMS_LEAVE_ENCASH_HDR " + 
							" LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON (HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE = HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) " + 
							" WHERE EMP_ID = " + this.leaveEn.getEmpId() + 
							" AND LEAVE_CODE = " + leaveid[i] + 
							" AND ENCASH_STATUS IN ('A','P') " + 
							" AND TO_CHAR(ENCASH_DATE,'MM') IN (" + getStringWithComma(monthArray) + ") AND TO_CHAR(ENCASH_DATE,'YYYY') ='"+encashYear+"'";
					Object[][] chkPrevEncObj = model.getSqlModel().getSingleResult(chkPrevEncQuery);
					if (chkPrevEncObj != null && chkPrevEncObj.length > 0) {
						for (int j = 0; j < chkPrevEncObj.length; j++) {
							totEncashDays += Double.parseDouble(String.valueOf(chkPrevEncObj[j][1]));
						}
					}

					this.logger.info("totEncashDays     :" + totEncashDays);
					totalEncash = totEncashable + totEncashDays;
					this.logger.info("totalEncash     :" + totalEncash);

					if (checkObj != null && checkObj.length > 0) {
						final double msgChk = Double.parseDouble(String.valueOf(checkObj[0][0]))
								- totEncashDays;
						this.logger.info("msgChk     :" + msgChk);
						this.logger.info("checkObj[0][0]     :" + String.valueOf(checkObj[0][0]));
						//if(msgChk<0.0)
						if (totalEncash > Double.parseDouble(String.valueOf(checkObj[0][0]))
								&& msgChk > 0) {
							this.logger.info("1     :" );
							this.addActionMessage("You cannot encash more than " + msgChk + " " + 
									String.valueOf(checkObj[0][4]));
							check = true;
							return check;
						} else if (totalEncash > Double.parseDouble(String.valueOf(checkObj[0][0]))
								&& msgChk <= 0.0d) {
							this.logger.info("2     :" );
							String msg = "You have already applied " + String.valueOf(checkObj[0][4]) + " for this ";
							if (String.valueOf(checkObj[0][1]).equals("Mo")) {
									msg += "month";
							} else if (String.valueOf(checkObj[0][1]).equals("Qu")) {
									msg += "quarter";
							} else if (String.valueOf(checkObj[0][1]).equals("Hy")) {
									msg += "half-year";
							} else if (String.valueOf(checkObj[0][1]).equals("Ye")) {
									msg += "year";
							}
							this.addActionMessage(msg);
							check = true;
							return check;
						} else if (msgChk < 0) {
							this.logger.info("3     :");
							String msg = "You have already applied " + String.valueOf(checkObj[0][4]) + " for this ";
							if (String.valueOf(checkObj[0][1]).equals("Mo")) {
									msg += "month";
								} else if (String.valueOf(checkObj[0][1]).equals("Qu")) {
									msg += "quarter";
								} else if (String.valueOf(checkObj[0][1]).equals("Hy")) {
									msg += "half-year";
								} else if (String.valueOf(checkObj[0][1]).equals("Ye")) {
									msg += "year";
								}
							this.addActionMessage(msg);
							check = true;
							return check;
						} else {
							this.logger.info("4     :");
							check = false;
						}
					}
				}
			}
			} // END OF MAIN FOR
		} catch (final Exception e) {
			this.logger.error("Exception in checkEncashmentSettings :  ", e);
			check = false;
		}

		model.terminate();
		return check;
	}

	/**
	 * THIS METHOD IS USED FOR CALCULATING LEAVE ENCASHMENT AMOUNT ACCORDING TO
	 * LEAVE ENCASHMENT FORMULA DEFINED IN LEAVE POLICY
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calculat() throws Exception {

		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			final String[] leaveid = (String[]) request.getParameterValues("leaveId");
			final String[] encashable = new String[leaveid.length];
			final String[] balanceleave = new String[leaveid.length];
			for (int i = 0; i < leaveid.length; i++) {
				String encash = request.getParameter("enBal" + i);
				if (encash.trim().equals("")) {
					encash = String.valueOf("0");
				} // end of if
				encashable[i] = encash;
				final String clbalance = request.getParameter("leaveEn.clBal" + i);
				balanceleave[i] = clbalance;
			} // end of for
			try {
				if (encashable.length != 0) {
					final String[][] result = model.calculatEncash(this.leaveEn,
							encashable, balanceleave, context, request);
					request.setAttribute("values", result);
				} // end of if
			} catch (final Exception e) {
				// TODO: handle exception
				this.logger.error("Exception in calculat1----------------" + e);
			} // end of catch
			this.leaveEn.setIsFlag("true");
			this.leaveEn.setIsResult("true");
			this.leaveEn.setIsTotal("true");
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in calculat2----------------" + e);
		}
		return "success";
	} // end of calculat

	/**
	 * THIS METHOD IS USED FOR FORWARDING LEAVE ENCASHMENT APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String forwardToApprover() throws Exception {

		try {
			save();
			if(this.leaveEn.getMaxEncashAmt().equals("true")){
				this.logger.error("Leaves more than Max Encashment Limit");
				return SUCCESS;
			}else{
					
				final LeaveEncashmentModel model = new LeaveEncashmentModel();
				model.initiate(context, session);
				final String[] encash1 = (String[]) request.getParameterValues("total"); // amount
				final String[] leaveid = (String[]) request.getParameterValues("leaveId"); // leave code
				final String[] onhold = (String[]) request.getParameterValues("onhold"); // on hold balance
				String[] encashable = new String[leaveid.length];
				String[] balanceleave = new String[leaveid.length];
				String[] singleamount = new String[leaveid.length];
				for (int i = 0; i < leaveid.length; i++) {
					final String encash = request.getParameter("enBal" + i);
					encashable[i] = encash;
					final String clbalance = request.getParameter("leaveEn.clBal" + i);
					balanceleave[i] = clbalance;
					final String amount1 = request.getParameter("total" + i);
					singleamount[i] = amount1;
				} // end of for
				
				// code for save details first
				final String[] oldEncashDays = (String[]) request.getParameterValues("hiddenEncashDays"); // on hold balance
				final String[] keepInformToCode = (String[]) request.getParameterValues("keepInformToCode"); // keep inform to code
				final String apprStatus = request.getParameter("status"); // apprStatus
				String policyCode = model.getLeavePolicyCode(this.leaveEn.getEmpId());
				if (leaveid != null && leaveid.length > 0) {
					encashable = new String[leaveid.length];
					balanceleave = new String[leaveid.length];
					singleamount = new String[leaveid.length];
				} // end of if
				if (leaveid != null && leaveid.length > 0) {
					for (int i = 0; i < leaveid.length; i++) {
						final String encash = request.getParameter("enBal" + i);
						encashable[i] = encash;
						final String clbalance = request.getParameter("leaveEn.clBal" + i);
						balanceleave[i] = clbalance;
						final String amount1 = request.getParameter("total" + i);
						singleamount[i] = amount1;
					} // end of for
				}
	
				// CODE ADDED BY REEBA
				boolean check = false;
				try {
					check = this.checkEncashmentSettings(leaveid, policyCode, encashable);
				} catch (final Exception e) {
					this.logger.error("Exception in calling checkEncashmentSettings method :  ", e);
				}
				this.logger.info("Check    :" + check);
				if (check) {
					final String[][] values = model.getLeaveRecord(this.leaveEn, policyCode);
					if (values != null && values.length > 0) {
						this.leaveEn.setIsFlag("true");
						this.leaveEn.setIsResult("true");
						this.leaveEn.setIsTotal("true");
						request.setAttribute("values", values);
						this.leaveEn.setStatusFlag("");
						this.leaveEn.setTotalAmt("0");
					} // end of nested if
					return SUCCESS;
				}
				
	
				if (!check) {
					boolean result = false;
					
						result = model.modEncashHdr(this.leaveEn, leaveid, balanceleave,
								encashable, encash1, onhold, oldEncashDays, keepInformToCode,apprStatus);
						if (result) {
							final String[][] values = model.getLevRecord(this.leaveEn);
							if (values.length > 0) {
								this.leaveEn.setIsFlag("true");
								this.leaveEn.setIsResult("true");
								this.leaveEn.setIsTotal("true");
								request.setAttribute("values", values);
								this.leaveEn.setStatusFlag("");
								// this.leaveEn.setTotalAmt("0");
							} // end of nested if
							// this.addActionMessage(this.getMessage("update"));
							this.leaveEn.setShowflag("true");
							// reset();
						} // end of if
				}
				//save code ends
				
				final Object[][] empFlow = this.generateEmpFlow(this.leaveEn.getEmpId(), "LeaveEncash", 1);
				if (empFlow != null && empFlow.length > 0) {
					final boolean result = model.forwardToApprover(this.leaveEn, empFlow,
							leaveid, balanceleave, encashable, encash1, onhold);
					if (result) {
	
						// -----Process Manager Alert/Email templates-------start
						try {
							
							final MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
							processAlerts.initiate(context, session);
							processAlerts.removeProcessAlert(this.leaveEn.getEnCode(), "LeaveEncash");
							processAlerts.terminate();
							
							
							final String msgType = "A";
							final String applicantID = this.leaveEn.getEmpId();
							final String approverID = String.valueOf(empFlow[0][0]);
							final String applnDate = this.leaveEn.getEncashDate();
							final String module = "LeaveEncash";
							final String applnID = this.leaveEn.getEnCode();
							final String level = "1";
	
							
							// Mail From System to Applicant
							EmailTemplateBody templateApplicant = new EmailTemplateBody();
							templateApplicant.initiate(context, session);
	
							templateApplicant
									.setEmailTemplate("LEAVE ENC-DETAILS TO APPLICANT");
	
							templateApplicant.getTemplateQueries();
	
							EmailTemplateQuery templateApplicantQuery1 = templateApplicant
									.getTemplateQuery(1); // FROM EMAIL
							// templateApplicantQuery1.setParameter(1, applicant);
	
							final EmailTemplateQuery templateApplicantQuery2 = templateApplicant.getTemplateQuery(2); // TO EMAIL
							templateApplicantQuery2.setParameter(1, applicantID);
	
							// Subject + Body
							final EmailTemplateQuery templateApplicantQuery3 = templateApplicant.getTemplateQuery(3);
							templateApplicantQuery3.setParameter(1, applnDate);
							templateApplicantQuery3.setParameter(2, applicantID);
	
							final EmailTemplateQuery templateApplicantQuery4 = templateApplicant.getTemplateQuery(4);
							templateApplicantQuery4.setParameter(1, approverID);
							
							final EmailTemplateQuery templateApplicantQuery5 = templateApplicant.getTemplateQuery(5);
							templateApplicantQuery5.setParameter(1, applnID);
							
							final EmailTemplateQuery templateApplicantQuery6 = templateApplicant.getTemplateQuery(6);
							templateApplicantQuery6.setParameter(1, applicantID);
							templateApplicantQuery6.setParameter(2, applnID);
	
							templateApplicant.configMailAlert();
							// templateApplicant.sendProcessManagerAlert(approver, module,
							// msgType, applnID, level);
	
							templateApplicant.sendApplicationMail();
							templateApplicant.clearParameters();
							templateApplicant.terminate();
	
							// Mail From System to Applicant End
							
							
							
							
							final EmailTemplateBody template = new EmailTemplateBody();
							template.initiate(context, session);
	
							template.setEmailTemplate("LEAVE ENC-APPLICANT TO APPROVER");
	
							template.getTemplateQueries();
	
							final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
							templateQuery1.setParameter(1, applicantID);
	
							final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
							templateQuery2.setParameter(1, approverID);
	
							// Subject + Body
							final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
							templateQuery3.setParameter(1, applnDate);
							templateQuery3.setParameter(2, applicantID);
	
							final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
							templateQuery4.setParameter(1, approverID);
							
							final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
							templateQuery5.setParameter(1, applnID);
							
							final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
							templateQuery6.setParameter(1, applicantID);
							templateQuery6.setParameter(2, applnID);
	
							template.configMailAlert();
							//template.sendProcessManagerAlert(approverID, module,msgType, applnID, level); // create process alerts
							
							String link = "";
							String linkParam = "";
	
							link = "/leaves/LeaveEncashment_retriveForApproval.action";
							linkParam = "leaveEncashAppNo=" + applnID + "&encashEmpId=" + applicantID;
							try {
								template.sendProcessManagerAlert(approverID, module, msgType,
										applnID, level, linkParam, link, "Pending",
										applicantID, "", "", "", applicantID);
							} catch (final Exception e) {
								e.printStackTrace();
							}
							
							String keepInformCode = "";
							if (keepInformToCode != null && keepInformToCode.length > 0) {
								for (int i = 0; i < keepInformToCode.length; i++) {
									keepInformCode = keepInformCode + keepInformToCode[i] + ",";
								}
							}
							
							this.sendProcessManagerAlertForSendforApproval(this.leaveEn.getEmpName(), approverID, applnID, applicantID, keepInformCode);
							
							String[] keep = request.getParameterValues("keepInformToCode");
							if (keep != null) {
								template.sendApplicationMailToKeepInfo(keep);
							}
							
							
							template.sendApplicationMail(); // send mail
							template.clearParameters();
							template.terminate();
						} catch (final Exception e) {
	
							this.logger.error("Exception in forwardToApprover1----------------" + e);
						}
						// -----Process Manager Alert/Email templates-------end
	
						this.addActionMessage("Leave encashment forwaded successfully");
						if (this.leaveEn.isGeneralFlag()) {
							this.leaveEn = model.empDetails(this.leaveEn.getUserEmpId(), this.leaveEn);
							this.leaveEn.setImageFlag("false");
	
							policyCode = model.getLeavePolicyCode(this.leaveEn.getEmpId());
	
							final String[][] values = model.getLeaveRecord(this.leaveEn, policyCode);
							if (values.length > 0) {
								this.leaveEn.setIsFlag("true");
								this.leaveEn.setIsResult("true");
								this.leaveEn.setIsTotal("true");
								request.setAttribute("values", values);
								this.leaveEn.setStatusFlag("");
								this.leaveEn.setTotalAmt("0");
							} // end of nested if
	
						} // end of if
						///this.reset();
						this.leaveEn.setTotalAmt("0");
					} // end of if
				} else {
					this.addActionMessage("Reporting Structure Not Defined for the Employee\n" + this.leaveEn.getEmpName());
				} // end of else
				model.terminate();
			}
		} catch (final Exception e) {
			this.logger.error("Exception in forwardToApprover2----------------" + e);
		}
		return "mymessages";
	} // end of forwardToApprover

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 */
	public String report() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			model.getReport(request, response, this.leaveEn);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in report----------------" + e);
		}
		return null;

	} // end of report

	
	/**
	 * THIS METHOD IS USED FOR SELECTING CREDIT CODE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9credits() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		final String query = " SELECT  CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD " + 
				" WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "	 + 
				" ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = {"Code", "CREDIT NAME"};

		final String[] headerWidth = {"40", "60"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"creditCode", "creditType"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	} // 
	
	
	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + 
				"  CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID,TO_CHAR(SYSDATE,'DD-MM-YYYY'),HRMS_DEPT.DEPT_NAME, NVL(EMP_GENDER,'B')" + 
				" FROM HRMS_EMP_OFFC "	+ 
				" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " + 
				" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " + 
				" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) ";

		//query += this.getprofileQuery(this.leaveEn);
		if (leaveEn.getUserProfileDivision()!=null && leaveEn.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ leaveEn.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
		query += "  AND EMP_STATUS='S'";

		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = {this.getMessage("employee.id"), this.getMessage("employee"),
				this.getMessage("branch"), this.getMessage("designation") };

		final String[] headerWidth = {"20", "30", "30", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"leaveEn.empToken", "leaveEn.empName",
				"leaveEn.centerName", "leaveEn.rankName", "leaveEn.empId",
				"leaveEn.encashDate", "leaveEn.dept", "leaveEn.gender" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		final int[] columnIndex = {0, 1, 2, 3, 4, 5, 6,7};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		final String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "LeaveEncashment_LeaveTypeRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	} // end of f9empaction

	/**
	 * THIS METHOD IS USED FOR SELECTING SAVED RECORD
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9viewaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + 
				" CENTER_NAME,HRMS_RANK.RANK_NAME,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'D','DRAFT','P','PENDING','A','APPROVED','R','REJECTED')," + 
				" HRMS_EMP_OFFC.EMP_ID,TO_CHAR(ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE, " + 
				" HRMS_LEAVE_ENCASH_HDR.ENCASH_LOCK ,NVL(APPROVED_BY,'0'),HRMS_DEPT.DEPT_NAME " + 
				" FROM HRMS_EMP_OFFC  " + 
				" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ) " + 
				" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER )" + 
				" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT )" + 
				" INNER JOIN HRMS_LEAVE_ENCASH_HDR ON(HRMS_LEAVE_ENCASH_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)";

		query += this.getprofileQuery(this.leaveEn);

		query += " ORDER BY HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE DESC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = {this.getMessage("employee.id"), this.getMessage("employee"),
				this.getMessage("branch"), this.getMessage("designation"),
				this.getMessage("stat") };

		final String[] headerWidth = {"15", "25", "20", "20", "20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"leaveEn.empToken", "leaveEn.empName",
				"leaveEn.centerName", "leaveEn.rankName", "status",
				"leaveEn.empId", "leaveEn.encashDate", "leaveEn.enCode",
				"leaveEn.encashLock", "leaveEn.statusFlag", "leaveEn.dept"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		final int[] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		final String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "LeaveEncashment_ViewLeaveRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	} // end of f9viewaction

	/**
	 * THIS METHOD IS USED FOR DISPLAYING LEAVE TYPE AND LEAVE BALANCE
	 * 
	 * @return String
	 */
	public String ViewLeaveRecord() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			final String values1[][] = model.getViewLeaveRecord(this.leaveEn);
			request.setAttribute("values", values1);
			model.getViewlevRecord(this.leaveEn);
			model.getRecord(this.leaveEn);
			this.setApproverList(this.leaveEn.getEmpId());
			
			model.setApproverDetailsRecord(this.leaveEn);
			this.leaveEn.setApproverDetails("true");
			
			
			this.leaveEn.setIsFlag("true");
			this.leaveEn.setIsTotal("true");
			if (this.leaveEn.getStatus().equals("D")) {
				this.leaveEn.setStatusFlag("");
				this.leaveEn.setStatus("PD");
			}
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in ViewLeaveRecord--------" + e);
		}
		return INPUT;
	} // end of ViewLeaveRecord

	/**
	 * THIS METHOD IS USED FOR LEAVE ENCASHMENT APPROVAL
	 * 
	 * @return String
	 */

	public String retriveForApproval() {

		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			final String encashAppNo = request.getParameter("leaveEncashAppNo"); // encashment application
			final String lempCode = request.getParameter("encashEmpId"); // employee id
			this.leaveEn.setEnCode(encashAppNo);
			this.leaveEn.setEmpId(lempCode);
			model.showEmp(this.leaveEn);
			model.getViewlevRecord(this.leaveEn);
			final String values1[][] = model.getEncashDetails(this.leaveEn);
			request.setAttribute("values", values1);
			model.getRecord(this.leaveEn);
			model.setApproverDetailsRecord(this.leaveEn);
			this.leaveEn.setApproverDetails("true");
			this.leaveEn.setIsFlag("true");
			this.leaveEn.setIsResult("true");
			this.leaveEn.setIsTotal("true");
			this.leaveEn.setApprovalFlag("false");
			this.leaveEn.setImageFlag("false");
			
			//set approvers name list start
			final ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			final Object[][] empFlow = model1.generateEmpFlow(this.leaveEn.getEmpId(), "LeaveEncash");
			model.setApproverData(this.leaveEn, empFlow);
			model1.terminate();
			//set approvers name list end
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in retriveForApproval--------" + e);
		}
		return "success";
	} // end of retriveForApproval

	/**
	 * THIS METHOD IS USED FOR LEAVE ENCASHMENT HISTORY
	 * 
	 * @return String
	 */

	public String retriveForHistory() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			final String employeeId = request.getParameter("employeeNo");
			this.leaveEn.setEId(employeeId);
			this.leaveEn.setEmpId(employeeId);
			model.getEncashHistory(this.leaveEn);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in retriveForHistory--------" + e);
		}
		return "successJspPage";
	} // end of retriveForHistory

	/**
	 * THIS METHOD IS USED FOR LEAVWE ENCASHMENT APPROVAL GO BUTTON
	 * 
	 * @return String
	 */
	public String go() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			this.leaveEn.setEmpId(this.leaveEn.getEId());
			model.getEncashHistory(this.leaveEn);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in go--------" + e);
		}
		return "successJspPage";
	} // end of go

	public int[] getMonthArray(final int encashMonth, final String period,
			int yearStartMonth) {

		final ArrayList<int[]> obj = new ArrayList<int[]>();

		if (yearStartMonth == 1) {
			yearStartMonth = 12;
		} else {
			yearStartMonth = yearStartMonth - 1;
		}
		if (period.equals("Mo")) {
			return new int[]{encashMonth};
		} else if (period.equals("Qu")) {
			int counter = 1;
			for (int i = 0; i < 4; i++) {
				int[] quarterArray = new int[] {
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++) };
				obj.add(quarterArray);
			}

		} else if (period.equals("Hy")) {
			int counter = 1;
			for (int i = 0; i < 1; i++) {
				int[] quarterArray = new int[] {
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++) };
				obj.add(quarterArray);
			}
			
		} else if (period.equals("Ye")) {
			int counter = 1;
		
			final int[] quarterArray = new int[] {
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++) 
			};
			obj.add(quarterArray);
			
			
		}

		for (int k = 0; k < obj.size(); k++) {
			final int[] name = (int[]) obj.get(k);
			for (int j = 0; j < name.length; j++) {
				if (encashMonth == name[j]) {
					return name;
				}
			}

		}
		return new int[0];
	}

	public int getExactMonth(final int mon) {
		if (mon > 12) {
			return mon - 12;
		}
		return mon;
	}
	public String getStringWithComma(final int[] str) {
		String strg = "";
		try {
			for (int i = 0; i < str.length; i++) {
				strg += String.valueOf(str[i]);
				strg += ",";
			}
			strg = strg.substring(0, strg.length() - 1);
			
		} catch (final Exception e) {
			this.logger.error(e);
		} //end of try-catch block
		return strg;
	}
	
	/**
	 * CODED BY SHASHIKANT
	 */
	public String f9informTo() throws Exception {

		try {
			final String[] empCode = request.getParameterValues("keepInformToCode");
			String code = this.leaveEn.getEmpId();
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) { // loop x
					code += "," + empCode[i];
				} // end loop x
			} // end if

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME " + 
				"	,EMP_ID " + " FROM HRMS_EMP_OFFC    ";
			// query += getprofileQuery(trvlApp);
			query += " WHERE 1=1 AND EMP_STATUS='S'";
			if (this.leaveEn.getUserProfileDivision() != null && 
					this.leaveEn.getUserProfileDivision().length() > 0) {
				query += "AND EMP_DIV IN (" + this.leaveEn.getUserProfileDivision()	+ ")";
			}
			if (code != null && !code.equals("")) {
				query += " AND EMP_ID NOT IN(" + code + ")";
			}
			query += "ORDER BY EMP_ID ";
			final String[] headers = {"Employee Id", "Employee Name"};
			final String[] headerWidth = {"20", "80"};
			final String[] fieldNames = {"informToken", "informName", "informCode"};
			final int[] columnIndex = {0, 1, 2};
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(this.getText("data_path") + "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			final String msgType = "A";
			final String applicantID = this.leaveEn.getEmpId();
			final String module = "LeaveEncash";
			final String applnID = this.leaveEn.getEnCode();
			final String level = "1";
			final String link = "/leaves/LeaveEncashment_editLeaveRecord.action";
			final String linkParam = "leaveEncashAppNo=" + applnID;
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", this.leaveEn.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "LeaveEncash");
			template.sendProcessManagerAlertDraft(applicantID,//this.leaveEn.getUserEmpId(),
					module, "A", applnID, level, linkParam, link, message,
					"Draft", applicantID, applicantID);//this.leaveEn.getUserEmpId());
			template.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public String editLeaveRecord() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			
			final String encashAppNo = request.getParameter("leaveEncashAppNo"); // encashment application
			final String lempCode = request.getParameter("encashEmpId"); // employee id
			this.leaveEn.setEnCode(encashAppNo);
			
			
			String query = " SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + 
				" CENTER_NAME,HRMS_RANK.RANK_NAME,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'D','DRAFT','P','PENDING','A','APPROVED','R','REJECTED','B','SENDBACK')," + 
				" HRMS_EMP_OFFC.EMP_ID,TO_CHAR(ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE, " + 
				" HRMS_LEAVE_ENCASH_HDR.ENCASH_LOCK ,NVL(APPROVED_BY,'0'),HRMS_DEPT.DEPT_NAME " + 
				" FROM HRMS_EMP_OFFC  " + 
				" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ) " + 
				" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER )" + 
				" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT )" + 
				" INNER JOIN HRMS_LEAVE_ENCASH_HDR ON(HRMS_LEAVE_ENCASH_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)";
			//query += this.getprofileQuery(this.leaveEn);
			query += " WHERE HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE =" + encashAppNo;
		
			final Object[][] chkLeaveRecoredObj = model.getSqlModel().getSingleResult(query);
			if (chkLeaveRecoredObj != null && chkLeaveRecoredObj.length > 0) {
				this.leaveEn.setEmpToken(String.valueOf(chkLeaveRecoredObj[0][0]));
				this.leaveEn.setEmpName(String.valueOf(chkLeaveRecoredObj[0][1]));
				this.leaveEn.setCenterName(String.valueOf(chkLeaveRecoredObj[0][2]));
				this.leaveEn.setRankName(String.valueOf(chkLeaveRecoredObj[0][3]));
				this.leaveEn.setStatus(String.valueOf(chkLeaveRecoredObj[0][4]));
				this.leaveEn.setEmpId(String.valueOf(chkLeaveRecoredObj[0][5]));
				this.leaveEn.setEncashDate(String.valueOf(chkLeaveRecoredObj[0][6]));
				this.leaveEn.setEnCode(String.valueOf(chkLeaveRecoredObj[0][7]));
				this.leaveEn.setEncashLock(String.valueOf(chkLeaveRecoredObj[0][8]));
				this.leaveEn.setApproverCode(String.valueOf(chkLeaveRecoredObj[0][9]));
				this.leaveEn.setDept(String.valueOf(chkLeaveRecoredObj[0][10]));
				model.setApproverDetailsRecord(this.leaveEn);
				if(leaveEn.getApproveList()!=null && leaveEn.getApproveList().size() > 0){
					this.leaveEn.setApproverDetails("true");
				}
				this.ViewLeaveRecord();
			}			
		} catch (final Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String editManagerLeaveRecord() {
		try {
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			
			final String encashAppNo = request.getParameter("leaveEncashAppNo"); // encashment application
			final String lempCode = request.getParameter("encashEmpId"); // employee id
			this.leaveEn.setEnCode(encashAppNo);
			
			
			String query = " SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + 
				" CENTER_NAME,HRMS_RANK.RANK_NAME,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'D','DRAFT','P','PENDING','A','APPROVED','R','REJECTED')," + 
				" HRMS_EMP_OFFC.EMP_ID,TO_CHAR(ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE, " + 
				" HRMS_LEAVE_ENCASH_HDR.ENCASH_LOCK ,NVL(APPROVED_BY,'0'),HRMS_DEPT.DEPT_NAME " + 
				" FROM HRMS_EMP_OFFC  " + 
				" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ) " + 
				" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER )" + 
				" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT )" + 
				" INNER JOIN HRMS_LEAVE_ENCASH_HDR ON(HRMS_LEAVE_ENCASH_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)";
			//query += this.getprofileQuery(this.leaveEn);
			query += " WHERE HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE =" + encashAppNo;
		
			final Object[][] chkLeaveRecoredObj = model.getSqlModel().getSingleResult(query);
			if (chkLeaveRecoredObj != null && chkLeaveRecoredObj.length > 0) {
				this.leaveEn.setEmpToken(String.valueOf(chkLeaveRecoredObj[0][0]));
				this.leaveEn.setEmpName(String.valueOf(chkLeaveRecoredObj[0][1]));
				this.leaveEn.setCenterName(String.valueOf(chkLeaveRecoredObj[0][2]));
				this.leaveEn.setRankName(String.valueOf(chkLeaveRecoredObj[0][3]));
				this.leaveEn.setStatus(String.valueOf(chkLeaveRecoredObj[0][4]));
				this.leaveEn.setEmpId(String.valueOf(chkLeaveRecoredObj[0][5]));
				this.leaveEn.setEncashDate(String.valueOf(chkLeaveRecoredObj[0][6]));
				this.leaveEn.setEnCode(String.valueOf(chkLeaveRecoredObj[0][7]));
				this.leaveEn.setEncashLock(String.valueOf(chkLeaveRecoredObj[0][8]));
				this.leaveEn.setApproverCode(String.valueOf(chkLeaveRecoredObj[0][9]));
				this.leaveEn.setDept(String.valueOf(chkLeaveRecoredObj[0][10]));
				model.setApproverDetailsRecord(this.leaveEn);
				if(leaveEn.getApproveList()!=null && leaveEn.getApproveList().size() > 0){
					this.leaveEn.setApproverDetails("true");
				}
				this.ViewLeaveRecord();
			}			
		} catch (final Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	
	
	
	public void sendProcessManagerAlertForSendforApproval(final String empName,
			final String approverCode, final String applicationCode, final String empCode,
			final String keepInformedId) {
		try {
		 
			final LeaveEncashmentModel model = new LeaveEncashmentModel();
			model.initiate(context, session);
			
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(this.getText("data_path") + "/Alerts/alertLinks.properties");			
			alertProp = new Properties();
			alertProp.load(alertFin);
			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			//String msgType = "A";
			String query = "SELECT (EMP_FNAME || ' ' || EMP_MNAME || ' '|| EMP_LNAME) AS APPROVER_NAME "
				+ "FROM HRMS_EMP_OFFC LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " 
				+ " WHERE EMP_ID = " + approverCode;
			final Object[][] approverNameObj = model.getSqlModel().getSingleResult(query);
			final String approverName = approverNameObj[0][0].toString();
			final String applicantID = empCode;
			final String module = "LeaveEncash";
			final String applnID = applicationCode;
			final String level = "1";
		 
			final String link = "/leaves/LeaveEncashment_editLeaveRecord.action";
			final String linkParam = "leaveEncashAppNo=" + applnID;
			
			String message = alertProp.getProperty("applSubmissionalertMessage");
			message = message.replace("<#MODULE#>", "LeaveEncash");
			message = message.replace("<#EMPLOYEE#>", empName);
			message = message.replace("<#APPROVER_NAME#>", approverName);
			
			template.sendProcessManagerAlertWithdraw(applicantID, module, "I",
					applnID, level, linkParam, link, message, "Pending",
					applicantID, applicantID, keepInformedId, "");
		 
			
			template.terminate();
		 
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
