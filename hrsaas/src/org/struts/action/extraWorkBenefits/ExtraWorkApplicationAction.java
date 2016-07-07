/**
 * 
 */
package org.struts.action.extraWorkBenefits;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.paradyne.bean.extraWorkBenefits.ExtraWorkApplication;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.extraWorkBenefits.ExtraWorkApplicationModel;
import org.paradyne.model.extraWorkBenefits.ExtraWorkApprovalModel;
import org.struts.lib.ParaActionSupport;
import org.paradyne.model.extraWorkBenefits.ExtraWorkBenifitCalculationModel;
/**
 * @author Reeba_Joseph
 * 
 */
public class ExtraWorkApplicationAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.extraWorkBenefits.ExtraWorkApplicationAction.class);

	ExtraWorkApplication application = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		application = new ExtraWorkApplication();
		application.setMenuCode(405);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return application;
	}

	/**
	 * @return the application
	 */
	public ExtraWorkApplication getApplication() {
		return application;
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(ExtraWorkApplication application) {
		this.application = application;
	}

	/**
	 * Called on load to set fields for general user
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			// SETTING APPLICATION DATE
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			application.setAppDate(sysDate);
			if (application.isGeneralFlag()) {
				application.setEmpId(application.getUserEmpId());
				application = model.getEmployeeDetails(application
						.getUserEmpId(), application);
			}// end of if
			else {
				model.getEmployeeDetails(application.getUserEmpId(),
						application);
			}// end else
			// CHECKING BRANCH WISE HOLIDAY SETTING
			model.checkBranchWiseHoliday(application);
			model.checkApprovalWorkFlow(application);
			// SETTING APPROVER LIST
			setApproverList(application.getUserEmpId());
			model.terminate();
		} catch (Exception e) {
			logger
					.error("Exception in prepare_withLoginProfileDetails---------------"
							+ e);
		}// END TRY-CATCH BLOCK

	}

	@Override
	/**
	 * SETS ALL PENDING APPLICATIONS LIST
	 */
	public String input() throws Exception {
		try {
			reset();
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			// CALLS LIST OF PENDING APPLICATIONS
			model.getAllTypeOfApplications(application, request, application
					.getUserEmpId());
			application.setListType("pending");
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK

		if (application.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (application.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (application.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return INPUT;
		}

	}

	/**
	 * CALLED ON ADD NEW APPLICATION
	 * 
	 * @return String
	 */
	public String addNew() {
		try {
            System.out.println("Hi----------------------");
			String source = request.getParameter("src");
			String timeCardDate = request.getParameter("timeCardDate");
			System.out.println("timeCardDate  " + timeCardDate);
			if (timeCardDate != null) {
				application.setPrDate(timeCardDate);
			}
			application.setSource(source);
			System.out.println("source  " + source);

			application.setHiddenStatus("D");
		} catch (Exception e) {
			logger.error("Exception in addNew method : " + e);
		}// END TRY-CATCH BLOCK
		getNavigationPanel(2);
		// RETURNS APPLICATION JSP
		return SUCCESS;
	}

	/**
	 * CALLED ON BACK BUTTON
	 * 
	 * @return String
	 */
	public String back() {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			// SETS LIST OF ALL PENDING APPLICATIONS
			model.getAllTypeOfApplications(application, request, application
					.getUserEmpId());
			application.setListType("pending");
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			logger.error("Exception in back method : " + e);
		}// END TRY-CATCH BLOCK
		// RETURNS APPLICATIONS LIST JSP
		return INPUT;
	}

	/**
	 * Search Employees
	 * 
	 * @return String(f9 page)
	 */
	public String f9Employee() {
		try {
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
					+ "	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID  FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
					+ "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
					+ "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
					+ "	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) ";

			query += getprofileQuery(application);

			query += " AND EMP_STATUS='S' ";

			query += "	ORDER BY EMP_ID";

			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };

			String[] headerWidth = { "20", "80" };

			String[] fieldNames = { "empToken",
					"empName", "branchName",
					"dept", "desg", "empId" };

			int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

			String submitFlag = "true";

			String submitToMethod = "ExtraWorkApplication_setApplicationDate.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}// END TRY-CATCH BLOCK
	}

	/**
	 * THIS METHOD IS USED FOR SETTING APPLICATION DATE
	 * 
	 * @return String
	 */

	public String setApplicationDate() {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			application.setAppDate(sysDate);
			// SETS APPROVER LIST
			setApproverList(application.getEmpId());
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Exception in setApplicationDate" + e);
		}// END TRY-CATCH BLOCK
		return SUCCESS;
	}// end of setApplicationDate

	/**
	 * CALLED TO SET APPROVER LIST OF SELECTED EMPLOYEE
	 * 
	 * @param empCode
	 */
	public void setApproverList(String empCode) {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			// FINDS APPROVERS FROM REPORTING STRUCTURE
			ReportingModel rptModel = new ReportingModel();
			rptModel.initiate(context, session);
			Object[][] empFlow = rptModel.generateEmpFlow(empCode,
					"ExtraDayBenefit");
			model.setApproverData(application, empFlow);
			rptModel.terminate();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}// END TRY-CATCH BLOCK
	}

	/**
	 * @return String after adding the record in the iterator
	 */
	public String add() {
		try {
			boolean isExceptionalShift = false;
			Object hprojName[] = request.getParameterValues("hprojName");
			Object hsType[] = request.getParameterValues("hsType");
			Object hDate[] = request.getParameterValues("hDate");
			Object hsTime[] = request.getParameterValues("hsTime");
			Object heTime[] = request.getParameterValues("heTime");
			String[] serialNo = request
					.getParameterValues("keepInformedSerialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			boolean dupresult = false;
			// CHECKS FOR DUPLICATE RECORD (ON DATE VALIDATION)
			dupresult = model.isduplicate(application);
			if (!dupresult) {

				addActionMessage("You have already Applied for extra work benefits on this Date.");
				// SETS REMAINING LIST
				model.checked(application, hprojName, hDate, hsTime, heTime,
						hsType);
				// SETS ITERATOR VALUES
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, application);
				// SETS APPROVER LIST
				setApproverList(application.getEmpId());
				model.terminate();
				if (application.getHiddenStatus().equals("B")) {
					// SETS APPROVER COMMENTS LIST
					setApproverCommentList();
					application.setPrevAppCommentListFlag("true");
					application.setApprCommentsCheck("false");
					getNavigationPanel(5);
				} else
					getNavigationPanel(2);
				return SUCCESS;

			}// END IF NOT DUPRESULT

			// check duplicate with iterator
			dupresult = model.isduplicateCheckForIterator(application, hDate);
			if (!dupresult) {

				addActionMessage("You have already Applied for extra work benefits on this Date.");
				// SETS REMAINING LIST
				model.checked(application, hprojName, hDate, hsTime, heTime,
						hsType);
				// SETS ITERATOR VALUES
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, application);
				// SETS APPROVER LIST
				setApproverList(application.getEmpId());
				model.terminate();
				if (application.getHiddenStatus().equals("B")) {
					// SETS APPROVER COMMENTS LIST
					setApproverCommentList();
					application.setPrevAppCommentListFlag("true");
					application.setApprCommentsCheck("false");
					getNavigationPanel(5);
				} else
					getNavigationPanel(2);
				return SUCCESS;

			}// END IF NOT DUPRESULT

			ExtraWorkBenifitCalculationModel model1 = new ExtraWorkBenifitCalculationModel();

			model1.initiate(context, session);

			String day = model.checkIfHoliday(application.getPrDate(),
					application);
			String actualDay = "";

			if (day.equals("HLD") || day.equals("NHD")) {
				actualDay = day;
			} else {
				String dayOfWeek = String.valueOf(model
						.getIntDayOfWeek(application.getPrDate()));

				actualDay = model.getDay(Integer.parseInt(dayOfWeek));
			}

			System.out.println("value of actualDay------------" + actualDay);
			if (!actualDay.equals("")) {

				System.out.println("value of actualDay:::::::::::::::::::::::"
						+ actualDay);
				Object[][] tempObj = model1.getBenifit(application.getEmpId(),
						actualDay, application.getType());

				if (tempObj != null && tempObj.length > 0) {

					System.out
							.println("String.valueOf(tempObj[0][9]):::::::::::::::::"
									+ String.valueOf(tempObj[0][9]));

					if (String.valueOf(tempObj[0][9]).equals("Y")) {
						Object attendanceDataObj[][] = calculateDailyAttendance(
								application.getEmpId(), application.getPrDate());

						if (attendanceDataObj != null
								&& attendanceDataObj.length > 0) {
							System.out
									.println("String.valueOf(attendanceDataObj[0][0])  "
											+ String
													.valueOf(attendanceDataObj[0][0]));

							if (!String.valueOf(attendanceDataObj[0][0])
									.equals("00:00")) {
								boolean result = checkDailyTimingsWithAppliedTimings(
										attendanceDataObj, application);

								if (!result) {
									addActionMessage("You should apply with your login start time and end time.");

									model.checked(application, hprojName,
											hDate, hsTime, heTime, hsType);

									model.displayIteratorValueForKeepInformed(
											serialNo, empCode, empName,
											application);
									setApproverList(application.getEmpId());
									// setextraWorkList();
									if (application.getHiddenStatus().equals(
											"B")) {
										setApproverCommentList();
										application
												.setPrevAppCommentListFlag("true");
										application
												.setApprCommentsCheck("false");
										getNavigationPanel(5);
									} else
										getNavigationPanel(2);
									return SUCCESS;

								} else {

									System.out
											.println("in else loop first-------------");
									if (application.getCheckEdit().equals("")) {

										System.out
												.println("in else loop model.add-------------");

										model.add(application, hprojName,
												hDate, hsTime, heTime, hsType);
									} // END IF ADD
									else {
										model.edit(application, hprojName,
												hDate, hsTime, heTime, hsType);
									}// END ELSE EDIT
									application.setIteratorFlag("true");
									application.setPrName("");
									application.setPrDate("");
									application.setStartTime("");
									application.setEndTime("");
									application.setCmonth("-1");
									application.setCyear("");
									application.setHidMon("");
									application.setCheckEdit("");
									application.setType("");

									model.displayIteratorValueForKeepInformed(
											serialNo, empCode, empName,
											application);
									setApproverList(application.getEmpId());
									// setextraWorkList();
									if (application.getHiddenStatus().equals(
											"B")) {
										setApproverCommentList();
										application
												.setPrevAppCommentListFlag("true");
										application
												.setApprCommentsCheck("false");
										getNavigationPanel(5);
									} else
										getNavigationPanel(2);
									model.terminate();
									return SUCCESS;
								}

							}

							else {
								addActionMessage("Daily attendance record is not available in the system.");

								model.checked(application, hprojName, hDate,
										hsTime, heTime, hsType);
								model
										.displayIteratorValueForKeepInformed(
												serialNo, empCode, empName,
												application);
								setApproverList(application.getEmpId());
								// setextraWorkList();
								if (application.getHiddenStatus().equals("B")) {
									setApproverCommentList();
									application
											.setPrevAppCommentListFlag("true");
									application.setApprCommentsCheck("false");
									getNavigationPanel(5);
								} else
									getNavigationPanel(2);

								return SUCCESS;
							}

						}

					}
				}

			}
			/**
			 * CALCULATES TIME DIFFERENCE
			 */
			// FIND THE SHIFT OF EMPLOYEE
			String shiftQuery = " SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ application.getEmpId();
			Object[][] shiftObj = model.getSqlModel().getSingleResult(
					shiftQuery);
			// FIND SHIFT WORKING HOURS OF THAT SHIFT
			String queryShiftHrs = "";

			// Changes done for to check exceptional shift on 27-12-2011
			if (isExceptionalShift()) {
				String dayOfWeek = String.valueOf(model
						.getIntDayOfWeek(application.getPrDate()));
				queryShiftHrs = "SELECT  TO_CHAR(SFT_DTL_WORK_HRS,'HH24:MI') FROM HRMS_SHIFT_DTL "
						+ " WHERE SHIFT_ID="
						+ shiftObj[0][0]
						+ " AND SFT_DTL_EXCEPTIONAL_DAY="
						+ Integer.parseInt(dayOfWeek);
				isExceptionalShift = true;
			} else {

				queryShiftHrs = "SELECT  TO_CHAR(SFT_WORK_HRS,'HH24:MI') FROM HRMS_SHIFT  "
						+ " WHERE HRMS_SHIFT.SHIFT_ID=" + shiftObj[0][0];
			}

			Object shiftHrsObj[][] = model.getSqlModel().getSingleResult(
					queryShiftHrs);

			// check fullday ,halfday working hrs satisfied when applied.

			String compShiftHrs = model.checkShiftHours(application, String
					.valueOf(shiftHrsObj[0][0]));
			if (compShiftHrs.equals("fullDayViolation")) {
				addActionMessage("Time duration of "
						+ String.valueOf(shiftHrsObj[0][0])
						+ " hours should be satisfied to apply for Full Day extra work benefits");
				application.setIteratorFlag("true");
				model.checked(application, hprojName, hDate, hsTime, heTime,
						hsType);
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, application);
				setApproverList(application.getEmpId());
				model.terminate();
				if (application.getHiddenStatus().equals("B")) {
					setApproverCommentList();
					application.setPrevAppCommentListFlag("true");
					application.setApprCommentsCheck("false");
					getNavigationPanel(5);
				} else
					getNavigationPanel(2);
				return SUCCESS;
			} else if (compShiftHrs.equals("halfDayViolation")) {
				// CALCULATES HALF OF SHIFT WORKING HOURS
				long workingHoursInMinutes = model.getMinute(String
						.valueOf(shiftHrsObj[0][0]));
				long halfOfworkingHours = workingHoursInMinutes / 2;
				long hours = halfOfworkingHours / 60;
				long minutes = halfOfworkingHours % 60;

				String halfDayHours = hours + ":" + minutes;

				addActionMessage("Time duration of "
						+ halfDayHours
						+ " hours should be satisfied to apply for Half Day extra work benefits");
				application.setIteratorFlag("true");
				model.checked(application, hprojName, hDate, hsTime, heTime,
						hsType);
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, application);
				setApproverList(application.getEmpId());
				model.terminate();
				if (application.getHiddenStatus().equals("B")) {
					setApproverCommentList();
					application.setPrevAppCommentListFlag("true");
					application.setApprCommentsCheck("false");
					getNavigationPanel(5);
				} else
					getNavigationPanel(2);
				return SUCCESS;
			} else {
				//new code begins
				/*if (compShiftHrs.equals("partialForExtraDayPayViolation")){
					
									
					Object[][] tempObj = model1.getBenifitIfPartial(application.getEmpId(),
							actualDay, application.getType());
															
					if (tempObj != null && tempObj.length > 0){
						
						long workingHoursInMinutes = model.getMinute(String
								.valueOf(shiftHrsObj[0][0]));
						long halfOfworkingHours = workingHoursInMinutes / 2;
						long hours = halfOfworkingHours / 60;
						long minutes = halfOfworkingHours % 60;

						String halfDayHours = hours + ":" + minutes;
			             addActionMessage("Time duration "+halfDayHours+" hours should be satisfied to apply for Partial Day extra work benefits "+
					        " \nAs the benifit type is Extra-Day Leave.");
			            
			             model.checked(application, hprojName, hDate, hsTime, heTime,
									hsType);
							model.displayIteratorValueForKeepInformed(serialNo, empCode,
									empName, application);
							setApproverList(application.getEmpId());
							model.terminate();
							getNavigationPanel(2);
							return SUCCESS;			
					}
					
					
				}	*/
					//new code ends
				String checkIfHoliday = "";
				// CHECK IF DATE IS ON HOLIDAY OR WEEKLY OFF
				checkIfHoliday = model.checkIfHoliday(application.getPrDate(),
						application);
				logger.info("checkIfHoliday  : " + checkIfHoliday);
				if (checkIfHoliday.equals("")
						&& !application.getType().equals("O")) {
					// ADD OR EDIT IF DATE IS ON HOLIDAY OR WEEKLY OFF OR IF
					// PARTIAL DAY
					// TYPE

					String str = model.checkShiftTimings(application, String
							.valueOf(shiftHrsObj[0][0]), shiftObj,
							isExceptionalShift);
					if (!str.equals("")) {
						addActionMessage(str);
						model.checked(application, hprojName, hDate, hsTime,
								heTime, hsType);
						model.displayIteratorValueForKeepInformed(serialNo,
								empCode, empName, application);
						setApproverList(application.getEmpId());
						// setextraWorkList();
						if (application.getHiddenStatus().equals("B")) {
							setApproverCommentList();
							application.setPrevAppCommentListFlag("true");
							application.setApprCommentsCheck("false");
							getNavigationPanel(5);
						} else
							getNavigationPanel(2);
					} else {
						if (application.getCheckEdit().equals("")) {
							model.add(application, hprojName, hDate, hsTime,
									heTime, hsType);
						} // END IF ADD
						else {
							model.edit(application, hprojName, hDate, hsTime,
									heTime, hsType);
						}// END ELSE EDIT
						application.setIteratorFlag("true");
						application.setPrName("");
						application.setPrDate("");
						application.setStartTime("");
						application.setEndTime("");
						application.setCmonth("-1");
						application.setCyear("");
						application.setHidMon("");
						application.setCheckEdit("");
						application.setType("");

						model.displayIteratorValueForKeepInformed(serialNo,
								empCode, empName, application);
						setApproverList(application.getEmpId());
						// setextraWorkList();
						if (application.getHiddenStatus().equals("B")) {
							setApproverCommentList();
							application.setPrevAppCommentListFlag("true");
							application.setApprCommentsCheck("false");
							getNavigationPanel(5);
						} else
							getNavigationPanel(2);
						model.terminate();
						return SUCCESS;
					}

				} // END IF-ELSE BLOCK
				else {
					// ADD OR EDIT IF DATE IS ON HOLIDAY OR WEEKLY OFF OR IF
					// PARTIAL DAY
					// TYPE
					if (application.getCheckEdit().equals("")) {
						model.add(application, hprojName, hDate, hsTime,
								heTime, hsType);
					} // END IF ADD
					else {
						model.edit(application, hprojName, hDate, hsTime,
								heTime, hsType);
					}// END ELSE EDIT
					application.setIteratorFlag("true");
					application.setPrName("");
					application.setPrDate("");
					application.setStartTime("");
					application.setEndTime("");
					application.setCmonth("-1");
					application.setCyear("");
					application.setHidMon("");
					application.setCheckEdit("");
					application.setType("");

					model.displayIteratorValueForKeepInformed(serialNo,
							empCode, empName, application);
					setApproverList(application.getEmpId());
					// setextraWorkList();
					if (application.getHiddenStatus().equals("B")) {
						setApproverCommentList();
						application.setPrevAppCommentListFlag("true");
						application.setApprCommentsCheck("false");
						getNavigationPanel(5);
					} else
						getNavigationPanel(2);
					model.terminate();
					return SUCCESS;

				}
			}
		} catch (Exception e) {
			logger.error("Exception in add-----------------------------" + e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * method for deleting a row in the list
	 * 
	 * @return String after deleting the record in the iterator
	 */
	public String delItem() {
		try {
			String serialNo = request.getParameter("serialNo");
			Object hprojName[] = request.getParameterValues("hprojName");
			Object hsType[] = request.getParameterValues("hsType");
			Object hDate[] = request.getParameterValues("hDate");
			Object hsTime[] = request.getParameterValues("hsTime");
			Object heTime[] = request.getParameterValues("heTime");
			String[] serialNo1 = request
					.getParameterValues("keepInformedSerialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.delItem(application, hprojName, hDate, hsTime, heTime,
					serialNo, hsType);
			application.setCheckEdit("");
			model.displayIteratorValueForKeepInformed(serialNo1, empCode,
					empName, application);
			// SETS APPROVER LIST
			setApproverList(application.getEmpId());

			model.terminate();
			if (application.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				application.setPrevAppCommentListFlag("true");
				application.setApprCommentsCheck("false");
				getNavigationPanel(5);
			} else
				getNavigationPanel(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 * Called to select employees in Keep informed to list
	 * 
	 * @return String (f9 page)
	 */
	public String f9KeepInformedEmployee() {
		// TO REMOVE AN ALREADY SELECTED EMPLOYEE FROM SEARCH WINDOW
		String[] eId = request.getParameterValues("keepInformedEmpId");
		String str = "0";
		if (eId != null) {
			for (int i = 0; i < eId.length; i++) {
				str += "," + eId[i];
			}// END FOR LOOP
		}// END IF
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC ";

		query += " WHERE EMP_STATUS='S'";
		if (application.getUserProfileDivision() != null
				&& application.getUserProfileDivision().length() > 0)
			query += "AND EMP_DIV IN (" + application.getUserProfileDivision()
					+ ")";
		query += "	AND EMP_ID NOT IN(" + str
				+ ") ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "employeeToken", "employeeName", "employeeId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	} // end of f9Branch

	/**
	 * ADD EMPLOYEES INTO KEEP INFORMED LIST
	 * 
	 * @return String
	 */
	public String addKeepInformedEmpList() {
		try {
			String[] serialNo = request
					.getParameterValues("keepInformedSerialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, application);
			model.setKeepInformed(serialNo, empCode, empName, application);
			// SET LIST OF EXTRA WORK
			setextraWorkList();
			// SET APPROVER LIST
			setApproverList(application.getEmpId());
			application.setEmployeeId("");
			application.setEmployeeName("");
			application.setEmployeeToken("");
			if (application.getHiddenStatus().equals("B")) {
				// SET APPROVER COMMENTS LIST
				setApproverCommentList();
				application.setPrevAppCommentListFlag("true");
			}// END IF
			model.terminate();
			if (application.getHiddenStatus().equals("B")) {
				getNavigationPanel(5);
			} else {
				getNavigationPanel(2);
			}// END IF-ELSE BLOCK
		} catch (Exception e) {
			logger.error("Exception in addKeepInformedEmpList method :" + e);
		}
		return SUCCESS;
	}// end of addKeepInformedEmpList

	/**
	 * Remove an employee from KEep informed to list
	 * 
	 * @return String
	 */
	public String removeKeepInformed() {
		try {
			if (application.getHiddenStatus().equals("B")) {
				getNavigationPanel(5);
			} else {
				getNavigationPanel(2);
			}// END IF ELSE BLOCK
			String[] serialNo = request
					.getParameterValues("keepInformedSerialNo");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			String[] empName = request
					.getParameterValues("keepInformedEmpName");
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.removeKeepInformedData(serialNo, empCode, empName,
					application);
			// SET EXTRA WORK LIST
			setextraWorkList();
			// SET APPROVER LIST
			setApproverList(application.getEmpId());
			if (application.getHiddenStatus().equals("B")) {
				// SET APPROVER COMMENTS LIST
				setApproverCommentList();
				application.setPrevAppCommentListFlag("true");
			}
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformed method: " + e);
		}
		return SUCCESS;

	}

	/**
	 * SET KEEP INFORMED LIST
	 */
	public void setKeepInformedList() {
		ExtraWorkApplicationModel model = null;
		try {
			String[] serialNo = request
					.getParameterValues("keepInformedSerialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("keepInformedEmpId");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpName");// keep informed
			// employee name
			model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, application);
		} catch (Exception e) {
			logger.error("Exception in setKeepInformedList method: " + e);
		}// END TRY CATCH BLOCK
		model.terminate();
	}

	/**
	 * SETS EXTRA WORK BENEFITS LIST
	 * 
	 * @return String
	 */
	public String setextraWorkList() {
		Object hprojName[] = request.getParameterValues("hprojName");
		Object hsType[] = request.getParameterValues("hsType");
		Object hDate[] = request.getParameterValues("hDate");
		Object hsTime[] = request.getParameterValues("hsTime");
		Object heTime[] = request.getParameterValues("heTime");
		ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
		model.initiate(context, session);
		model.checked(application, hprojName, hDate, hsTime, heTime, hsType);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * SETS SAVED EXTRA WORK BENEFITS LIST
	 */
	public void setSavedExtraWorkList() {
		try {
			String cmpId = application.getCompId();
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.setSavedExtraWorkList(application, cmpId);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setSavedExtraWorkList------" + e);
			e.printStackTrace();
		}// END TRY CATCH BLOCK
	}

	/**
	 * SET APPROVER COMMENTS
	 * 
	 * @return String
	 */
	public String setApproverCommentList() {
		try {
			String[] srNo = request.getParameterValues("appSrNo"); // serial
			// no.
			String[] approverId = request.getParameterValues("prevApproverID");
			String[] approverName = request
					.getParameterValues("prevApproverName");
			String[] approvedDate = request
					.getParameterValues("prevApproverDate");
			String[] approvedComments = request
					.getParameterValues("prevApproverComment");
			String[] prevApproverStatus = request
					.getParameterValues("prevApproverStatus");
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.setApproverCommentList(srNo, approverId, approverName,
					approvedDate, approvedComments, prevApproverStatus,
					application);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverCommentList--------" + e);
		}// END TRY CATCH BLOCK
		return SUCCESS;
	}

	/**
	 * method for saving the application and modifying application based on
	 * reporting structure
	 * 
	 * @return String
	 */
	public String save() {
		try {
			String status = request.getParameter("checkStatus");
			String[] empCode = request.getParameterValues("keepInformedEmpId");
			Object hprojName[] = request.getParameterValues("hprojName");
			Object hsType[] = request.getParameterValues("hsType");
			Object hDate[] = request.getParameterValues("hDate");
			Object hsTime[] = request.getParameterValues("hsTime");
			Object heTime[] = request.getParameterValues("heTime");
			Object hDay[] = request.getParameterValues("hDay");
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			if (application.getWorkFlowFlag().equals("Y")) {

				Object[][] empFlow = generateEmpFlow(application.getEmpId(),
						"ExtraDayBenefit", 1);

				// CHECKS IF REPORTING STRUCTURE IS DEFINED FOR EMPLOYEE
				if (empFlow == null) {
					addActionMessage("Reporting structure not defined for the employee\n"
							+ application.getEmpName());
					addActionMessage("Please contact HR manager !");
					getNavigationPanel(1);
					try {
						// RETURNS TO LIST
						prepare_withLoginProfileDetails();
						return input();
					} catch (Exception e) {
						logger.error("Exception in save");
					}
				} // END IF EMPFLOW NULL
				else {
					if (application.getCompId().equals("")) {
						// IF CODE IS BLANK, SAVE RECORD
						boolean result = model.save(application, hprojName,
								hDate, hsTime, heTime, empFlow, hsType, status,
								empCode, hDay);
						if (result) {
							if (status.equals("P")) {
								getNavigationPanel(1);
								try {
									// CALLS EMAIL TEMPLATE IF IN PENDING STATUS
									model.generateMailTemplate(application,
											request, empFlow);
								} catch (Exception e) {
									e.printStackTrace();
								}// END TRY-CATCH BLOCK
								try {
									addActionMessage("Your application has been sent for approval successfully.");
									// RETURNS TO LIST IF MAIL SENT
									prepare_withLoginProfileDetails();
									input();
									return input();
								} catch (Exception e) {
									logger.error("Exception in save");
								}
							} else if (status.equals("D")) {
								addActionMessage(getMessage("save"));
								setSavedExtraWorkList();
								setKeepInformedList();
								setApproverList(application.getEmpId());
								getNavigationPanel(3);
							}

						} // END IF RESULT
						else {
							if (status.equals("P")) {
								addActionMessage("Your application could not be sent for approval.");
							} else if (status.equals("D")) {
								addActionMessage(getMessage("save.error"));
							}
							getNavigationPanel(2);
							reset();
						}// END ELSE (IF NOT RESULT)
					}// END IF SAVE
					else {
						// IF CODE IS NOT BLANK
						boolean ckFlag = model.checkForward(application);
						// CHECK IF APPLICATION HAS ALREADY BEEN FORWARDED FOR
						// APPROVAL
						if (!ckFlag) {
							// IF NOT FORWARDED UPDATE
							boolean result = model.update(application,
									hprojName, hDate, hsTime, heTime, empFlow,
									hsType, status, empCode, hDay);
							if (result) {
								if (status.equals("P")) {
									getNavigationPanel(1);
									try {
										// CALLS EMAIL TEMPLATE IF PENDING
										model.generateMailTemplate(application,
												request, empFlow);
									} catch (Exception e) {
										e.printStackTrace();
									}// END TRY CATCH BLOCK
									try {
										// RETURNS TO LIST
										addActionMessage("Your application has been sent for approval successfully.");
										prepare_withLoginProfileDetails();
										input();
										return input();
									} catch (Exception e) {
										logger.error("Exception in update");
									}// END TRY CATCH BLOCK
								}// END IF STATUS = P
								else if (status.equals("D")) {
									addActionMessage(getMessage("update"));
									setSavedExtraWorkList();
									setKeepInformedList();
									setApproverList(application.getEmpId());
									
								
									getNavigationPanel(3);
								}// END IF STATUS = D
							}// IF UPDATED
							else {
								if (status.equals("P")) {
									addActionMessage("Your application could not be sent for approval.");
								} else if (status.equals("D")) {
									addActionMessage(getMessage("update.error"));
								}// END NESTED IF-ELSE BLOCK
								getNavigationPanel(2);
								reset();
							}// END ELSE (NOT UPDATED)
						} // END IF NOT FORWARDED
						else {
							addActionMessage("Forwarded Application can't updated!");
							getNavigationPanel(2);
							reset();
						}// END ELSE (IF FORWARDED)
					}// END ELSE (IF CODE NOT BLANK)

					/*
					 * status = D implies Draft(save) record status = P implies
					 * Send for approval and Save
					 */

				} // END IF EMPFLOW NOT NULL
				if (application.getHiddenStatus().equals("B")) {
					// SET APPROVER COMMENTS IF SENT BACK APPLICATION
					setApproverCommentList();
					application.setPrevAppCommentListFlag("true");
				}
			} else {
				// IF APPROVAL WORKFLOW IS NOT DEFINED
				if (application.getCompId().equals("")) {
					// IF CODE IS BLANK, SAVE RECORD
					boolean result = model.saveForNoApprover(application,
							hprojName, hDate, hsTime, heTime, hsType, empCode,
							hDay, status);
					if (result) {
						addActionMessage(getMessage("save"));
						setSavedExtraWorkList();
						setKeepInformedList();
						setApproverList(application.getEmpId());
						getNavigationPanel(3);
					} else {
						addActionMessage(getMessage("save.error"));
						getNavigationPanel(2);
						reset();
					}
				} else {
					boolean result = model.updateForNoApprover(application,
							hprojName, hDate, hsTime, heTime, hsType, empCode,
							hDay, status);
					if (result) {
						addActionMessage(getMessage("update"));
						setSavedExtraWorkList();
						setKeepInformedList();
						setApproverList(application.getEmpId());
						getNavigationPanel(3);
					} else {
						addActionMessage(getMessage("update.error"));
						getNavigationPanel(2);
						reset();
					}
				}
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (application.getHiddenStatus().equals("D"))
		{
			sendProcessManagerAlertDraft();
		}
		
		if (application.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (application.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (application.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return SUCCESS;
		}

	}

	/**
	 * Retrieve saved application details on View from list
	 * 
	 * @return String
	 */
	public String retrieveDetails() {
		String status = "";
		boolean flag =false;	
		String isManager="";
		String isAlternateApprover="";
		boolean changeBtnPanel =false;		
		String source = request.getParameter("src");
		boolean flgFwd = true;
	    application.setFlagForward(flgFwd);
		application.setSource(source);

		ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
		try {
			model.initiate(context, session);			
			String extAppCode = request.getParameter("extWrkApplCode");
			String query = " select EXTRAWORK_APPL_APPROVER,EXTRAWORK_APPL_ALT_APPROVER "
				+" from HRMS_EXTRAWORK_APPL_HDR "
				+" where EXTRAWORK_APPL_CODE="+	extAppCode ;
			
			Object data[][] =model.getSqlModel().getSingleResult(query);
			
			if(data!=null && data.length>0){
				isManager=String.valueOf(data[0][0]);
				isAlternateApprover =String.valueOf(data[0][1]);
				
				if(isManager.equals(application.getUserEmpId()) || isAlternateApprover.equals(application.getUserEmpId()))
				{
					changeBtnPanel=true;
				}
			}
			status = request.getParameter("extWrkStatus");
			application.setCompId(extAppCode);
		
			String empCode = " SELECT EMP_ID FROM HRMS_EXTRAWORK_APPL_HDR "
				+ " WHERE EXTRAWORK_APPL_CODE=" + application.getCompId();
		Object empCodeObj[][] = model.getSqlModel()
				.getSingleResult(empCode);
		if (empCodeObj != null && empCodeObj.length > 0) {
			application.setEmpId(String.valueOf(empCodeObj[0][0]));
		}
			model.getRecord(application);
			model.showEmp(application);
			setSavedExtraWorkList();
			flag=model.getKeepInformedSavedRecord(application);
			boolean isRecord = model.setApproverDetails(application);	
			setApproverList(application.getEmpId());
			
			if (status.equals("B")) {
				application.setApprCommentsCheck("false");
				getApproverComments();
			}// END IF B
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in retriveDetails : " + e);
		}// END TRY CATCH BLOCK
		if (status.equals("D") || status.equals("W")) {
			if(changeBtnPanel){  
				flgFwd = false;
				application.setFlagForward(flgFwd);
				getNavigationPanel(6);
				application.setIsExtTypeApp("false");
				application.setApprovalFlag("false");
				application.setApprCommentsCheck("false");
			}
			if(status.equals("D")){
				getNavigationPanel(7);
			}
			else{
				flgFwd = false;
				application.setFlagForward(flgFwd);
				getNavigationPanel(6);
			}
		}// END IF D or W
		if (status.equals("P")) {
			application.setIsExtTypeApp("false");
			application.setApprovalFlag("false");
			if(flag){	
				flgFwd = false;			
				application.setFlagForward(flgFwd);
				getNavigationPanel(6);
			}
			else {
				flgFwd = false;			
				application.setFlagForward(flgFwd);
				getNavigationPanel(4);
			}
		}// END IF P
		if (status.equals("B")) {
			application.setPrevAppCommentFlag("true");
			application.setPrevAppCommentListFlag("true");
			application.setApprCommentsCheck("false");
			model.setApproverComments(application);
			model.getSendBackComments(application);
			if(flag){
				flgFwd = false;
				application.setFlagForward(flgFwd);
				getNavigationPanel(6);
			}
			else{
				getNavigationPanel(5);
			}	
		}// END IF B
		if (status.equals("A")) {
			logger.info("workflow flag : " + application.getWorkFlowFlag());
			application.setIsExtTypeApp("false");
			application.setApprovalFlag("false");
			application.setApprCommentsCheck("false");
			if (application.getWorkFlowFlag().equals("Y")) {
				application.setPrevAppCommentListFlag("true");
				model.setApproverComments(application);
			}
			flgFwd = false;
			application.setFlagForward(flgFwd);
			getNavigationPanel(6);
			if(flag){
				getNavigationPanel(6);
			}
			
		}// END IF A
		if (status.equals("R")) {
			flgFwd = false;
			application.setPrevAppCommentListFlag("true");
			application.setApprovalFlag("false");
			model.setApproverComments(application);
			application.setIsExtTypeApp("false");
			application.setApprCommentsCheck("false");
			application.setFlagForward(flgFwd);
			getNavigationPanel(6);
		}// END IF R		
		
		if(status.equals("F")){
			getNavigationPanel(6);
			application.setIsApprove("true");
			//model.getApplicationDetailsForApproval(confBooking, request,applicationCode);
			application.setApprCommentsCheck("true");
			application.setEnableAll("N");
		}
		return SUCCESS;		
	}

	/**
	 * RETRIEVING APPROVER COMMENTS
	 * 
	 * @return String
	 */
	public String getApproverComments() {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.setApproverComments(application);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApproverComments : " + e);
		}// END TRY CATCH BLOCK
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR DELETING APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			boolean result = model.delete(application);
			if (result) {
				deleteProcessManagerAlert("ExtraWork",  application.getCompId());
				addActionMessage(getMessage("delete"));
				reset();
			} // end of if
			else {
				addActionMessage(getMessage("del.error"));
			}// end of else
			// GETTING RECORD OF ALL TYPES OF PENDING APPLICATION
			model.getAllTypeOfApplications(application, request, application
					.getUserEmpId());
			application.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			addActionMessage(getMessage("del.error"));
		}// END TRY CATCH BLOCK
		getNavigationPanel(1);
		
		if (application.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (application.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (application.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return INPUT;
		}
		
	}// end of delete

	/**
	 * THIS METHOD IS USED FOR WITHDRAWING APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String withdrawApplication() throws Exception {
		ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
		try {
			model.initiate(context, session);
			if (application.getHiddenStatus().equals("A")
					|| application.getHiddenStatus().equals("P")) {
				boolean result = model.withdrawApplication(application);
				if (result) {
					sendProcessManagerAlertForWithdraw();
					addActionMessage("Application has been withdrawn successfully");

				}// end of if
				else {
					addActionMessage("Application could not be withdrawn ");
				}// end of else
			}// END IF A OR P
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApplicationDate :" + e);
		}// END TRY CATCH BLOCK
		// SET PENDING APPLICATIONS LIST
		model.getAllTypeOfApplications(application, request, application
				.getUserEmpId());
		application.setListType("pending");
		getNavigationPanel(1);
		
		if (application.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (application.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (application.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return INPUT;
		}
	}// end of withdrawApplication
	
	
	/*This method is used to send withdraw email to Applicant Approver and Keep Informed to*/
	public void sendProcessManagerAlertForWithdraw() {
		try {
			
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(String.valueOf(application.getCompId()),
					"ExtraWork");
			processAlerts.terminate();
			
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			
			String query ="SELECT EXTRAWORK_APPL_APPROVER,EXTRAWORK_KEEP_INFORMED FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE= "+ application.getCompId();		
			Object[][]selectObj =model.getSqlModel().getSingleResult(query);
			
			String managerId="";			
			String keepInformedId="";
			if(selectObj!=null && selectObj.length>0){
				managerId=String.valueOf(selectObj[0][0]);
				keepInformedId=String.valueOf(selectObj[0][1]);
			}
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");			
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = application.getEmpId();
			String module = "ExtraWork";
			String applnID = application.getCompId();
			String level = "1";
			String link = "/extraWorkBenefits/ExtraWorkApplication_retrieveDetails.action";	
			String linkParam ="extWrkApplCode=" + applnID + "&extWrkStatus=W";		
			String message = alertProp.getProperty("withdrawAlertMessage");
			message = message.replace("<#EMP_NAME#>", application.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "ExtraWork");
			template.sendProcessManagerAlertWithdraw(applicantID, module, "I",
					applnID, level, linkParam, link, message, "Withdraw",
					applicantID, applicantID,keepInformedId,managerId);
			template.terminate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * CALLS LIST OF APPROVED APPLICATIONS
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.getApprovedList(application, request, application
					.getUserEmpId());
			application.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList : " + e);
		}
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * CALLS LIST OF REJECTED APPLICATIONS
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getRejectedList() throws Exception {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			model.getRejectedList(application, request, application
					.getUserEmpId());
			application.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getRejectedList : " + e);
		}// END TRY CATCH BLOCK
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * Generate the report for particular application
	 * 
	 * @return String
	 */
	public String report() {
		ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
		model.initiate(context, session);
		model.getReport(request, response, application);
		model.terminate();
		return null;
	}

	/**
	 * @return String after resetting all the fields in the form
	 */
	public String reset() {
		application.setComments("");
		application.setAppDate("");
		application.setStatus("");
		application.setPrName("");
		application.setPrDate("");
		application.setStartTime("");
		application.setEndTime("");
		application.setCmonth("-1");
		application.setCyear("");
		application.setHidMon("");
		application.setCompId("");

		application.setHprojName("");
		application.setHsType("");
		application.setHsTime("");
		application.setHeTime("");
		application.setHmonth("");
		application.setHyear("");
		application.setHDate("");
		application.setSerialNo("");
		application.setIteratorFlag("false");

		application.setEmployeeName("");
		application.setEmployeeId("");
		application.setEmployeeToken("");
		application.setKeepInformedList(null);
		application.setKeepInformedSerialNo("");
		application.setKeepInformedEmpName("");
		application.setKeepInformedEmpId("");
		application.setCheckRemove("");
		application.setIsExtTypeApp("true");

		ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
		model.initiate(context, session);
		String query = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY')FROM DUAL ";
		Object obj[][] = model.getSqlModel().getSingleResult(query);
		application.setAppDate(String.valueOf(obj[0][0]));
		model.terminate();

		getNavigationPanel(2);

		return SUCCESS;
	}

	// METHODS FOR APPROVAL
	/**
	 * THIS METHOD IS CALLED ON APPROVAL FORM APPROVE BUTTON
	 * 
	 * @return String
	 */

	public String retrieveForApproval() {
		try {
			
			
			String source = request.getParameter("src");

			//String source =(String) request.getAttribute("src");

			System.out.println("source--------------" + source);
			application.setSource(source);
			
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			// APPLICATION CODE
			String extAppCode = request.getParameter("extWrkApplicationNo");
			// STATUS
			String applicationStatus = request
					.getParameter("applicationStatus");
			// SETS APPLICATION CODE
			application.setCompId(extAppCode);
			// SETS EMPLOYEE CODE
			String empCode = " SELECT EMP_ID FROM HRMS_EXTRAWORK_APPL_HDR "
					+ " WHERE EXTRAWORK_APPL_CODE=" + application.getCompId();
			Object empCodeObj[][] = model.getSqlModel()
					.getSingleResult(empCode);
			if (empCodeObj != null && empCodeObj.length > 0) {
				application.setEmpId(String.valueOf(empCodeObj[0][0]));
			}

			String can_Edit = "Y";
			if (can_Edit.equals("Y")) {
				application.setApprovalFlag("false");
				application.setIsExtTypeApp("false");
			}// end of if
			else {
				application.setApprovalFlag("false");
				application.setIsExtTypeApp("false");
			}// end of else

			// FOR SETTING LEVEL AND STATUS
			model.getRecord(application);
			// GETTING EMPLOYEE INFO
			model.showEmp(application);
			application.setIsApprove("true");
			// APPLICANT NAME F9 WINDOW FLAG
			application.setIsExtApp("false");
			// GETTING SAVED EXTRA WORK DETAILS
			model.setSavedExtraWorkList(application, application.getCompId());
			// APPROVER COMMENTS
			boolean isRecord = model.setApproverDetails(application);
			// KEEP INFORMED TO RECORD
			model.getKeepInformedSavedRecord(application);
			// SETTING APPROVER LIST
			setApproverList(application.getEmpId());
			// GETTING APPROVER COMMENTS
			getApproverComments();
			application.setApproverDetails("true");
			if (applicationStatus.equals("P")) {
				application.setAppRejSendBackFlag("true");
				if (isRecord)
					application.setPrevAppCommentListFlag("true");
				else
					application.setPrevAppCommentListFlag("false");
				application.setPrevAppCommentFlag("true");
				application.setApprCommentsCheck("true");
				getNavigationPanel(8);
			}// END IF P
			else if (applicationStatus.equals("A")) {
				application.setPrevAppCommentListFlag("true");
				getNavigationPanel(9);
			}// END IF A
			else if (applicationStatus.equals("R")) {
				application.setPrevAppCommentListFlag("true");
				getNavigationPanel(9);
			}// END IF R
			else if (applicationStatus.equals("C")) {
				application.setPrevAppCommentListFlag("false");
				application.setPrevAppCommentFlag("true");
			}// END IF C

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in retriveForApproval : " + e);
			e.printStackTrace();
		}
		return SUCCESS;
	}// end of retrieveForApproval

	/**
	 * CALLED ON APPROVE/REJECT/SEND BACK BUTTONS ON APPROVAL FORM
	 * 
	 * @return
	 */
	public String approveRejSendBackApp() {

		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);

			String can_Edit = "Y";
			if (can_Edit.equals("Y")) {
				application.setApprovalFlag("false");

			}// end of if
			else {
				application.setApprovalFlag("false");
			}// end of else

			// FOR SETTING LEVEL AND STATUS
			model.getRecord(application);
			// GETTING EMPLOYEE INFO
			model.showEmp(application);
			application.setIsApprove("true");
			// APPLICANT NAME F9 WINDOW FLAG
			application.setIsExtApp("false");
			// GETTING SAVED EXTRA WORK DETAILS
			model.setSavedExtraWorkList(application, application.getCompId());
			// APPROVER DETAILS
			model.setApproverDetails(application);
			// KEEP INFORMED TO RECORD
			model.getKeepInformedSavedRecord(application);
			// SETTING APPROVER LIST
			setApproverList(application.getEmpId());
			application.setApproverDetails("true");

			ExtraWorkApprovalModel approvalmodel = new ExtraWorkApprovalModel();
			approvalmodel.initiate(context, session);
			// CALLS METHOD ON APPROVAL MODEL
			String appStatus = approvalmodel.approveRejectSendBackApp(request,
					application.getEmpId(), application.getCompId(),
					application.getCheckApproveRejectStatus(), application
							.getApproverComments(), application.getUserEmpId(),
					application.getLevel());
			if (appStatus.equals("rejected")) {
				addActionMessage("Application rejected successfully");
			} else if (appStatus.equals("sendback")) {
				addActionMessage("Application sent back successfully");
			} else {
				addActionMessage("Application approved successfully");
			}

			approvalmodel.terminate();

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (application.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "approvalJsp";
		}
	}// end of approveRejSendBackApp

	/**
	 * CALLED ON BACK TO LIST
	 * 
	 * @return String
	 */
	public String backToApprovalList() {
		return "approvalJsp";
	}

	private Object[][] calculateDailyAttendance(String empId,
			String extraWorkDate) {
		// TODO Auto-generated method stub

		Object empWorkingHrsQueryObj[][] = null;
		try {
			String year = extraWorkDate.substring(6, 10);
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			String empWorkingHrsQuery = " SELECT NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI'), '00:00')as frmtime,NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'), '00:00')as totime, "
					+ " NVL(TO_CHAR(ATT_WORKING_HRS, 'HH24:MI'), '00:00') as WORKING_HRS "
					+ " FROM  HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " "
					+ "	 WHERE  ATT_EMP_ID="
					+ empId
					+ " "
					+ "	 AND ATT_DATE=TO_DATE('"
					+ extraWorkDate
					+ "','dd-mm-yyyy') ";
			empWorkingHrsQueryObj = model.getSqlModel().getSingleResult(
					empWorkingHrsQuery);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return empWorkingHrsQueryObj;
	}

	private boolean checkDailyTimingsWithAppliedTimings(
			Object[][] attendanceDataObj, ExtraWorkApplication applicationBean) {

		boolean result = false;
		try {

			String loginStartTime = String.valueOf(attendanceDataObj[0][0])
					.replace(":", "");
			;
			String loginEndTime = String.valueOf(attendanceDataObj[0][1])
					.replace(":", "");
			;
			long loginStart_Time = Long.parseLong(loginStartTime);
			long loginEnd_Time = Long.parseLong(loginEndTime);

			System.out.println("loginStart_Time------------------"
					+ loginStart_Time);
			System.out.println("loginEnd_Time----------------------"
					+ loginEnd_Time);

			String startTime = application.getStartTime().replace(":", "");
			;
			String endTime = application.getEndTime().replace(":", "");
			;
			long start_Time = Long.parseLong(startTime);

			long end_Time = Long.parseLong(endTime);

			System.out.println("start_Time------------------" + start_Time);
			System.out.println("end_Time----------------------" + end_Time);

			/*
			 * if(start_Time > loginStart_Time) { end_Time =
			 * Long.parseLong(endTime)+2400; }
			 */
			System.out.println("start_Time------------------" + start_Time);
			System.out.println("end_Time----------------------" + end_Time);

			System.out
					.println("in if loop aaaaaaaaaaaa"
							+ (start_Time >= loginStart_Time && start_Time < loginEnd_Time));

			System.out
					.println("in if loop bbbbbbbbbbbbbbbbb"
							+ (end_Time > loginStart_Time && end_Time <= loginEnd_Time));

			/*
			 * if(( start_Time>= loginStart_Time & start_Time <
			 * loginEnd_Time)|(end_Time> loginStart_Time & end_Time<=loginEnd_Time )) {
			 * System.out.println("in if loop "); } else { result = "You can't
			 * be applied"; }
			 */
			boolean a = false;
			boolean b = false;
			boolean c = false;
			boolean d = false;

			if (start_Time >= loginStart_Time) {
				a = true;
			}
			if (start_Time < loginEnd_Time) {
				b = true;
			}
			if (end_Time > loginStart_Time) {
				c = true;
			}
			if (end_Time <= loginEnd_Time) {
				d = true;
			}

			if (a & b & c & d) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	// Start Function to check Exceptional shift on 27-12-2010
	private boolean isExceptionalShift() {
		boolean result = false;
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);

			String dayOfWeek = String.valueOf(model.getIntDayOfWeek(application
					.getPrDate()));

			String query = "  SELECT * FROM HRMS_SHIFT_DTL "
					+ " WHERE SHIFT_ID=(SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ application.getEmpId() + ") "
					+ " AND SFT_DTL_EXCEPTIONAL_DAY="
					+ Integer.parseInt(dayOfWeek);

			Object data[][] = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	// End Function to check Exceptional shift on 27-12-2010
	
	

	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
		System.out.println("here inside sendProcessManagerAlertDraft----");
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			
			
			
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = application.getEmpId();
			String module = "ExtraWork";
			String applnID = application.getCompId();
			String level = "1";
			String link = "/extraWorkBenefits/ExtraWorkApplication_retrieveDetails.action";
			
			String linkParam ="extWrkApplCode=" + applnID + "&extWrkStatus=D";
			
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", application.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "ExtraWork");
			template.sendProcessManagerAlertDraft(applicantID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					applicantID, applicantID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProcessManagerAlert(String moduleName,
			String applicationCode) {
		try {
			ExtraWorkApplicationModel model = new ExtraWorkApplicationModel();
			model.initiate(context, session);
			String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ applicationCode
					+ " AND UPPER(ALERT_MODULE) LIKE '"
					+ moduleName.trim().toUpperCase() + "'";
			model.getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
