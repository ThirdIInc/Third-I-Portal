 package org.paradyne.model.TravelManagement.TravelProcess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.common.ReportingModel;
import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class TravelApplicationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelApplicationModel.class);

	public void getSysDate(TravelApplication bean) {
		String sysDateSql = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object data[][] = getSqlModel().getSingleResult(sysDateSql);

		if (data != null && data.length > 0) {
			bean.setAppDate(String.valueOf(data[0][0]));// Set sys_date as
			// application date
		}
	}
	
	

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals(" ")
				|| result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	public void getEmployeeDtls(TravelApplication bean) {

		Object empData[][] = getSqlModel().getSingleResult(getQuery(58),
				new Object[] { bean.getUserEmpId() });
		System.out.println("########## USER ID ############"
				+ bean.getUserEmpId());

		bean.setInitId(checkNull(bean.getUserEmpId()));
		bean.setInitToken(String.valueOf(empData[0][0]));
		bean.setInitName(String.valueOf(empData[0][1]));
		bean.setInitGradeId(String.valueOf(empData[0][2]));
		bean.setInitiatorGradeId(String.valueOf(empData[0][12]));
		bean.setAge(String.valueOf(empData[0][6]));
		bean.setContact(String.valueOf(empData[0][13]));
		bean.setAdvAmount(String.valueOf(empData[0][14]));
		bean.setInitiatorDateOfBirth(String.valueOf(empData[0][15]));
		bean.setJoiningDate(String.valueOf(empData[0][16]));
	}

	// INFORMATION//////////////////////////////////////////////////////////

	public void getApproverComments(TravelApplication bean) {

		// Approver comments in case application is sent back/approved/rejected
		if (bean.getAppStatus().equals("B") || bean.getAppStatus().equals("A")
				|| bean.getAppStatus().equals("R")) {
			Object data[][] = getSqlModel().getSingleResult(getQuery(53),
					new Object[] { bean.getAppId(), bean.getAppCode() });
			if (data != null && data.length > 0) {
				ArrayList<TravelApplication> list = new ArrayList<TravelApplication>();
				for (int i = 0; i < data.length; i++) {
					TravelApplication bean1 = new TravelApplication();
					bean1.setApprComment(String.valueOf(data[i][0]));

					list.add(bean1);
				}
				bean.setCommentList(list);
			}
		}
	}

	public String getTravelPolicy(TravelApplication bean, String gradeId) {// String
		// gradeId
		// =
		// request.getParameter("gradeId");

		// 1. Retrieve process manager details
		Object processObj[][] = getSqlModel().getSingleResult(getQuery(51));
		String effctPolicyDate = "";
		if (processObj != null && processObj.length > 0) {
			if (String.valueOf(processObj[0][7]).equals("0")) {// From date
				effctPolicyDate = bean.getStartDate();
			} else if (String.valueOf(processObj[0][7]).equals("1")) {// To
				// date
				effctPolicyDate = bean.getEndDate();
			} else if (String.valueOf(processObj[0][7]).equals("2")) {// Application
				// date
				effctPolicyDate = bean.getAppDate();
			} else {// Travel Policy Effective Date not saved in TMS_PROCESS_MGR
				effctPolicyDate = "(SELECT MAX(POLICY_EFFECTIVE_DATE) FROM TMS_TRAVEL_POLICY)";
			}
		}
		// 2. get travel policy for the grade of the employee
		// selected.
		logger.info("gradeId============" + gradeId);
		Object data[][] = getSqlModel().getSingleResult(getQuery(46),
				new Object[] { gradeId });// ,effctPolicyDate,
		// bean.getAppDate()
		logger.info("data length============" + data.length);
		if (data != null && data.length > 0) {
			try {
				bean.setPolicy(String.valueOf(data[0][3]));
				bean.setEffctDate(String.valueOf(data[0][4]));
				bean.setPolicyStatus(String.valueOf(data[0][5]));
				bean.setPolicyAdvanceAllowed(String.valueOf(data[0][6]));
				bean.setMaxDaysTravelClaim(String.valueOf(data[0][7]));
				if (String.valueOf(data[0][8]).equals("Y")) {
					// bean.setPayModeForAdvanceClaimCheque("true");
					bean.setPayModeForAdvanceClaimCheque("Cheque,");
				}
				if (String.valueOf(data[0][9]).equals("Y")) {
					bean.setPayModeForAdvanceClaimCash(" Cash,");
				}
				if (String.valueOf(data[0][10]).equals("Y")) {
					bean.setPayModeForAdvanceClaimTransfer(" Transfer,");
				}
				if (String.valueOf(data[0][11]).equals("Y")) {
					bean.setPayModeForAdvanceClaimInSalary(" Salary");
				}
				bean.setMinNumberOfAdvanceDaysToApplyForTravel(String
						.valueOf(data[0][12]));
				if (String.valueOf(data[0][13]).equals("Y")) {
					bean.setTravelTypeSelf(" Self,");
				}
				if (String.valueOf(data[0][14]).equals("Y")) {
					bean.setTravelTypeTeam(" Team,");
				}
				if (String.valueOf(data[0][15]).equals("Y")) {
					bean.setTravelTypeGuest(" Guest");
				}
				
				bean.setTravelPolicyCurrency(String.valueOf(data[0][16])); 
				 
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 3. get all expenses, travel, lodging details
			Object allExpData[][] = null;
			try {
				allExpData = getSqlModel().getSingleResult(getQuery(50),
						new Object[] { data[0][0] });
				if (allExpData != null && allExpData.length > 0) {
					bean.setExpCategory(String.valueOf(allExpData[0][2]));
					bean.setLodgExpCategory(String.valueOf(allExpData[0][3]));
					bean.setPolicyGud(checkNull(String
							.valueOf(allExpData[0][0])));
					bean.setSttlmntDays(String.valueOf(allExpData[0][1]));
				}
			} catch (Exception e) {
				logger.error("Error in allExpData===" + e);
				// e.printStackTrace();
			}

			Object expData[][] = null;
			try {
				// 4. get expense category list
				expData = getSqlModel().getSingleResult(getQuery(47),
						new Object[] { data[0][0] });
				long totalAmount = 0;
				if (expData != null && expData.length > 0) {

					ArrayList<TravelApplication> list = new ArrayList<TravelApplication>();
					for (int i = 0; i < expData.length; i++) {
						TravelApplication bean1 = new TravelApplication();

						bean1.setExpCategory(String.valueOf(expData[i][0]));
						bean1.setUnit(String.valueOf(expData[i][1]));
						bean1.setAmountWithBill(String.valueOf(expData[i][2]));
						bean1.setAmountWithOutBill(String
								.valueOf(expData[i][3]));
						// bean1.setMaxEntAmount(String.valueOf(expData[i][2]));
						bean1.setNoLimit(String.valueOf(expData[i][4]));
						bean1.setProofReq(String.valueOf(expData[i][5]));
						bean1.setCityGrade(String.valueOf(expData[i][6]));
						list.add(bean1);
					}
					bean.setTotalAmount(String.valueOf(totalAmount));
					bean.setExpList(list);
				}
			} catch (Exception e) {
				logger.error("Error in expData===" + e);
				// e.printStackTrace();
			}
			String status;
			Object travelModeData[][] = null;
			try {
				// 5. get travel mode list
				travelModeData = getSqlModel().getSingleResult(getQuery(48),
						new Object[] { data[0][0] });
				status = "";
				if (travelModeData != null && travelModeData.length > 0) {
					ArrayList<TravelApplication> list = new ArrayList<TravelApplication>();
					String modeStatus = "";
					for (int i = 0; i < travelModeData.length; i++) {

						// String.valueOf(allExpData[0][4]) //travel
						// String.valueOf(allExpData[0][5]) //lodging

						if (allExpData != null && allExpData.length > 0) {

							// logger.info("MASTER_CLASS_ID>>>"+travelModeData[i][0]);
							// logger.info("POLICY_CLASS_ID>>>"+allExpData[0][4]);

							// if(modeStatus.equals("Applicable") ||
							// String.valueOf(travelModeData[i][0]).equals(String.valueOf(allExpData[0][4]))){
							if (modeStatus.equals("Applicable")
									|| String.valueOf(travelModeData[i][0]).equals(
													String.valueOf(allExpData[0][4]))) {
								TravelApplication bean1 = new TravelApplication();
								bean1.setClassId(String
										.valueOf(travelModeData[i][0]));
								bean1.setJourneyId(String
										.valueOf(travelModeData[i][1]));
								bean1.setJourneyName(String
										.valueOf(travelModeData[i][2]));
								bean1.setClassName(String
										.valueOf(travelModeData[i][3]));

								bean1.setStatus("Applicable");
								modeStatus = "Applicable";
								list.add(bean1);

							}/*
							 * else{ bean1.setStatus("Not Applicable"); }
							 */

						} else {
							// bean1.setStatus("");
						}

						/*
						 * if(status.equals("Applicable")){
						 * bean1.setStatus("Applicable"); }else
						 * if(status.equals("")){ bean1.setStatus("Applicable");
						 * }else{ bean1.setStatus(""); }
						 */
					}
					bean.setTrvlModeList(list);
				}
			} catch (Exception e) {
				logger.error("Error in travelModeData===" + e);
				// e.printStackTrace();
			}
			// 6. get hotel type list

			Object hotelTypeData[][] = null;
			try {
				hotelTypeData = getSqlModel().getSingleResult(getQuery(49),
						new Object[] { data[0][0] });
				status = "";
				if (hotelTypeData != null && hotelTypeData.length > 0) {

					ArrayList<TravelApplication> list = new ArrayList<TravelApplication>();
					String modeStatus = "";
					for (int i = 0; i < hotelTypeData.length; i++) {

						if (allExpData != null && allExpData.length > 0) {
							// logger.info("MASTER_ROOM_ID>>>"+hotelTypeData[i][0]);
							// logger.info("POLICY_ROOM_ID>>>"+allExpData[0][5]);

							// if(modeStatus.equals("Applicable") ||
							// String.valueOf(hotelTypeData[i][0]).equals(String.valueOf(allExpData[0][5]))){
							if (modeStatus.equals("Applicable")
									|| String
											.valueOf(hotelTypeData[i][0])
											.equals(
													String
															.valueOf(allExpData[0][5]))) {

								TravelApplication bean1 = new TravelApplication();

								bean1.setRoomId(String
										.valueOf(hotelTypeData[i][0]));
								bean1.setHotelId(String
										.valueOf(hotelTypeData[i][1]));
								bean1.setHotel(String
										.valueOf(hotelTypeData[i][2]));
								bean1.setRoom(String
										.valueOf(hotelTypeData[i][3]));

								bean1.setStatus("Applicable");
								modeStatus = "Applicable";
								list.add(bean1);

							} else {
								// bean1.setStatus("Not Applicable");
							}

						} else {
							// bean1.setStatus("");
						}

						/*
						 * if(status.equals("") &&
						 * String.valueOf(hotelTypeData[i][0]).equals(String.valueOf(allExpData[0][5]))){
						 * status="Applicable"; bean1.setStatus("Applicable"); }
						 * 
						 * if(status.equals("Applicable")){
						 * bean1.setStatus("Applicable"); }else{
						 * bean1.setStatus("Not Applicable"); }
						 */
					}
					bean.setHotelTypeList(list);
				}
			} catch (Exception e) {
				logger.error("Error in hotelTypeData===" + e);
				// e.printStackTrace();
			}

		} else {
			return "policyNotDefined";
		}
		return "";
	}

	public Object[][] getReportingStructure(String empId, String deptId,
			String branchId, String desgId) {

		// 1. check employee's reporting structure
		Object empRptObj[][] = getSqlModel().getSingleResult(getQuery(2),
				new Object[] { empId });
		if (empRptObj != null && empRptObj.length > 0) {
			return empRptObj;
		}
		// 2. check department reporting structure
		Object dptRptObj[][] = getSqlModel().getSingleResult(getQuery(3),
				new Object[] { deptId });
		if (dptRptObj != null && dptRptObj.length > 0) {
			return dptRptObj;
		}
		// 3. check branch reporting structure
		Object branchRptObj[][] = getSqlModel().getSingleResult(getQuery(4),
				new Object[] { branchId });
		if (branchRptObj != null && branchRptObj.length > 0) {
			return branchRptObj;
		}
		// 4. check designation reporting structure
		Object desgRptObj[][] = getSqlModel().getSingleResult(getQuery(5),
				new Object[] { desgId });
		if (desgRptObj != null && desgRptObj.length > 0) {
			return desgRptObj;
		}
		return null;
	}

	/**
	 * @param empId
	 * @return This method is used to obtain reporting structure of the
	 *         initiator. Used for guest applicants
	 */
	public Object[][] getInitiatorReportingStructure(String empId) {

		// 1. check employee's reporting structure
		Object guestRptObj[][] = getSqlModel().getSingleResult(getQuery(2),
				new Object[] { empId });
		if (guestRptObj != null && guestRptObj.length > 0) {
			return guestRptObj;
		}
		// IF initiators reporting structure not defined.
		Object initiatorObj[][] = getSqlModel().getSingleResult(getQuery(9),
				new Object[] { empId });

		// 2. check department reporting structure
		Object dptRptObj[][] = getSqlModel().getSingleResult(getQuery(3),
				new Object[] { initiatorObj[0][0] });
		if (dptRptObj != null && dptRptObj.length > 0) {
			return dptRptObj;
		}
		// 3. check branch reporting structure
		Object branchRptObj[][] = getSqlModel().getSingleResult(getQuery(4),
				new Object[] { initiatorObj[0][1] });
		if (branchRptObj != null && branchRptObj.length > 0) {
			return branchRptObj;
		}
		// 4. check designation reporting structure
		Object desgRptObj[][] = getSqlModel().getSingleResult(getQuery(5),
				new Object[] { initiatorObj[0][2] });
		if (desgRptObj != null && desgRptObj.length > 0) {
			return desgRptObj;
		}
		return null;
	}

	public String isPolicyViolatedForEmployee(TravelApplication bean,
			String gradeId, String empName) {
		logger.info(":::isPolicyViolated called...");
		String effctPolicyDate = "";
		String policyViolation = "";

		// 1. Retrieve process manager details
		// Object processObj[][] = getSqlModel().getSingleResult(getQuery(51));
		// 2. Get policy details of journey/lodging of the grade of the
		// applicant
		logger.info(" bean.getAppDate()...." + bean.getAppDate());

		Object policyObj[][] = null;
		try {
			if (!checkNull(gradeId).equals("")) {
				policyObj = getSqlModel().getSingleResult(getQuery(52),
						new Object[] { gradeId, bean.getStartDate() });// ,effctPolicyDate
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (policyObj == null || policyObj.length == 0) {
				policyViolation += "PND";
			}
		}

		if (policyObj != null && policyObj.length > 0) {
			logger.info("bean.getHiddenApplicationCode() >>"
					+ bean.getHiddenApplicationCode());

			/* Journey violation chk */
			Object[] param = null;
			String jourSql = getQuery(10);
			String accomSql = getQuery(11);
			param = new Object[1];
			param[0] = bean.getHiddenApplicationCode();

			jourSql += "  ORDER BY JOURNEY_CODE ";
			accomSql += "   ORDER BY LODGE_CODE ";
			// 2. Gather existing journey details
			Object jourData[][] = getSqlModel().getSingleResult(jourSql, param);
			// jourData[0][3] - mode-classid
			// jourData[0][4] - mode-class name

			// 3. Gather existing Accomodation details
			Object accomData[][] = getSqlModel().getSingleResult(accomSql,
					param);
			// accomData[0][3] - room id from hrms_tms_room_type

			// logger.info("bean.getHAppFor()>>>>"+bean.getHAppFor());

			// if(bean.getHAppFor().equals("S")){//Self
			logger.info("[POLICY]journey level>>>>>" + policyObj[0][2]);
			logger.info("[POLICY]lodging level>>>>>" + policyObj[0][3]);
			// //Journey Details
			if (jourData != null && jourData.length > 0 && policyObj != null
					&& policyObj.length > 0) {
				for (int j = 0; j < jourData.length; j++) {
					// logger.info(""+policyObj[0][2]+"="+jourData[j][7]);
					// FOR DIFFERENT LEVEL OF JOURNEY COMPARISONS
					logger.info("String.valueOf(jourData[j][8])>>>>>"
							+ String.valueOf(jourData[j][8]));
					logger.info("String.valueOf(jourData[0][4])>>>>>"
							+ String.valueOf(policyObj[0][4]));
					if ((Integer.parseInt(String.valueOf(jourData[j][8])) > Integer
							.parseInt(String.valueOf(policyObj[0][4]))) 
					 /* If level of journey in policy and tour are same or not*/
					) {
						// NOTHING REQUIRED

					} else if ((Integer
							.parseInt(String.valueOf(jourData[j][8])) == Integer
							.parseInt(String.valueOf(policyObj[0][4])))
					/* If level of jorney in policy and tour are same or not */
					) {
						logger.info("String.valueOf(jourData[j][7])>>>>>"
								+ String.valueOf(jourData[j][7]));
						logger.info("String.valueOf(jourData[0][2])>>>>>"
								+ String.valueOf(policyObj[0][2]));

						if (Integer.parseInt(String.valueOf(jourData[j][7])) < Integer
								.parseInt(String.valueOf(policyObj[0][2]))) {
							// for first journey record
							if (j == 0) {
								policyViolation += "\n Journey For " + empName
										+ " - ";
							}
							policyViolation += "\t\t\t\t" + jourData[j][1]
									+ " to " + jourData[j][2] + "\n Chosen is "
									+ jourData[j][4] + " allowed is "
									+ policyObj[0][6] + " and below \n";
						}
					} else { // FOR COMPARISONS AT CLASS_LEVEL WITHIN SAME
						// JOURNEY_TYPE
						// 1. jour_levl in app < jour_level in policy
						// 2. class_level in app < class_level in policy
						if (j == 0) {
							policyViolation += "\n Journey For " + empName
									+ " - ";
						}
						policyViolation += "\t\t\t\t" + jourData[j][1] + " to "
								+ jourData[j][2] + "\n Chosen is "
								+ jourData[j][4] + " allowed is "
								+ policyObj[0][6] + " and below \n";
					}

				}
			}

			/* Accomodation violation chk */
			if (accomData != null && accomData.length > 0 && policyObj != null
					&& policyObj.length > 0) {
				for (int k = 0; k < accomData.length; k++) {
					logger.info("" + policyObj[0][3] + "=" + accomData[k][12]);

					logger.info("policyObj[0][5]  " + policyObj[0][5]
							+ "==accomData[k][13]==" + accomData[k][13]);

					if (Integer.parseInt(String.valueOf(accomData[k][13])) > Integer
							.parseInt(String.valueOf(policyObj[0][5])) // If
					) {

						// NOTHING REQUIRED

					} else if ((Integer.parseInt(String
							.valueOf(accomData[k][13])) == Integer
							.parseInt(String.valueOf(policyObj[0][5]))) // If
					/* level of jorney in policy and tour are same or not*/
					) {
						logger.info("policyObj[0][5]  " + policyObj[0][3]
								+ "==accomData[k][13]==" + accomData[k][12]);

						if (Integer.parseInt(String.valueOf(accomData[k][12])) < Integer
								.parseInt(String.valueOf(policyObj[0][3]))) {
							// for first journey record
							if (k == 0) {
								policyViolation += "\n Accomodation For "
										+ empName + " - ";
							}
							// for all accom records
							policyViolation += "\n Chosen is "
									+ accomData[k][2] + "-" + accomData[k][4]
									+ " allowed is " + policyObj[0][7];
						}

					} else {

						if (k == 0) {
							policyViolation += "\n Accomodation For " + empName
									+ " - ";
						}
						// for all accom records
						policyViolation += "\n Chosen is " + accomData[k][2]
								+ "-" + accomData[k][4] + " allowed is "
								+ policyObj[0][7];
					}

				}
			}

			// }
		} else {
			policyViolation = "Policy Not Defined";// PMND
		}
		logger.info("Inside:::isPolicyViolated:::ends");
		logger.info("policyViolation>>>>>" + policyViolation);
		return policyViolation;

	}

	public void addInformList(TravelApplication travelBean,
			String[] keepInformCode, String[] keepInform, String chk) {
		travelBean.setInformList(null);
		ArrayList arrList = new ArrayList();
		int count = 0;
		try {
			count = Integer.parseInt(travelBean.getKeepHidden());
		} catch (Exception e) {
			e.printStackTrace();
		}
		travelBean.setKeepHidden("");
		if (keepInform != null && keepInform.length > 0) {
			if (chk.equals("remove")) {
				for (int i = 0; i < keepInform.length; i++) {
					TravelApplication leaveBean = new TravelApplication();
					if (i != (count)) {
						leaveBean.setKeepInformCode(keepInformCode[i]);
						leaveBean.setKeepInform(keepInform[i]);
						arrList.add(leaveBean);
					}
				}
			} else {
				for (int i = 0; i < keepInform.length; i++) {
					TravelApplication leaveBean = new TravelApplication();
					leaveBean.setKeepInformCode(keepInformCode[i]);
					leaveBean.setKeepInform(keepInform[i]);
					arrList.add(leaveBean);
				}
			}
		}

		if (chk.equals("add")) {
			TravelApplication leaveBean = new TravelApplication();
			leaveBean.setKeepInformCode(travelBean.getInformCode());
			leaveBean.setKeepInform(travelBean.getInformName());
			arrList.add(leaveBean);
		}
		travelBean.setInformList(arrList);
	}

	/**
	 * SET SELF/GUEST/OTHER EMP LIST
	 */

	public void setKeepInform(TravelApplication bean, String applCode) {
		String query = "SELECT NVL(APPL_KEEP_INFO,'0') FROM  TMS_APPLICATION WHERE APPL_ID="
				+ applCode;
		Object[][] hdrData = getSqlModel().getSingleResult(query);
		bean.setInformList(null);
		if (hdrData != null && hdrData.length > 0) {
			String info = String.valueOf(hdrData[0][0]);
			if (!info.equals("0")) {
				String emp = "SELECT EMP_ID,EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID IN("
						+ info + ")";
				Object[][] empInfoData = getSqlModel().getSingleResult(emp);
				ArrayList <Object> arrList = new ArrayList <Object>();
				if (empInfoData != null && empInfoData.length > 0) {
					for (int i = 0; i < empInfoData.length; i++) {
						TravelApplication leaveBean = new TravelApplication();
						leaveBean.setKeepInformCode(String
								.valueOf(empInfoData[i][0]));
						leaveBean.setKeepInform(String
								.valueOf(empInfoData[i][1]));
						arrList.add(leaveBean);
					}
					bean.setInformList(arrList);
				}
			}
		}
	}

	// ADDED BY PRASHANT BEGINS

	public void getAllApplications(TravelApplication travelApplicationBean,
			HttpServletRequest request, String empId, String status) {

		ArrayList<Object> travelApplicationList;

		try {

			String allApplicationStatusQuery = " SELECT APPL_INITIATOR,NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITIATOR, TOUR_TRAVEL_REQ_NAME, TO_CHAR(APPL_DATE,'DD-MM-YYYY'), TRIM(APPL_STATUS), APPL_ID, DECODE(TRIM(APPL_STATUS),'P','Pending for approval','C','Booking Completed','A','Approved','N','Draft','B','Sent Back','R','Rejected','F','Revoked')"
					+ " ,APPL_TRAVEL_ID,'_'||APPL_STATUS FROM TMS_APPLICATION "
					+ " INNER JOIN HRMS_EMP_OFFC ON (TMS_APPLICATION.APPL_INITIATOR = HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE 1=1 ";

			if (status.equals("processed")) {
				allApplicationStatusQuery += " AND TRIM(APPL_STATUS) IN ('A','R','C','F') AND APPL_INITIATOR ="
						+ empId;
				travelApplicationBean.setListType("processed");
			} else if (status.equals("under_process")) {
				allApplicationStatusQuery += " AND TRIM(APPL_STATUS) IN ('N','P','B') AND APPL_INITIATOR ="
						+ empId;
				travelApplicationBean.setListType("under_process");
			}

			allApplicationStatusQuery += "  ORDER BY APPL_ID ";

			Object applicationDataObj[][] = getSqlModel().getSingleResult(
					allApplicationStatusQuery);
			String[] pageIndex = Utility.doPaging(travelApplicationBean
					.getMyPage(), applicationDataObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1")) {
				travelApplicationBean.setMyPage("1");
			}
			logger.info("Integer.parseInt(pageIndex[0])=="
					+ Integer.parseInt(pageIndex[0]));

			logger.info("Integer.parseInt(pageIndex[1])=="
					+ Integer.parseInt(pageIndex[1]));
			travelApplicationList = new ArrayList<Object>();
			// for (int i = 0; i < applicationDataObj.length; i++)
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				TravelApplication travelBean = new TravelApplication();

				travelBean.setInitiatorIdItt(String
						.valueOf(applicationDataObj[i][1]));// Initiator Id
				travelBean.setInitiatorNameItt(String
						.valueOf(applicationDataObj[i][1]));// Initiator name
				travelBean.setTravelRequestNameItt(Utility.checkNull(String
						.valueOf(applicationDataObj[i][2])));// Travel
				// request
				// name
				travelBean.setTravleDateItt(String
						.valueOf(applicationDataObj[i][3]));// Travel date
				travelBean.setTravleApplicationStatusItt(String
						.valueOf(applicationDataObj[i][4]));// Travel
				// application
				// status
				travelBean.setTravelApplicationIdItt(Utility.checkNull(String
						.valueOf(applicationDataObj[i][5])));// Application
				// Id
				travelBean.setTravleApplicationStatusNameItt(String
						.valueOf(applicationDataObj[i][6]));// Decoded status
				// name

				travelBean.setTravelNameItt(String
						.valueOf(applicationDataObj[i][7]));

				travelBean.setTravleApplicationStatusItt(String
						.valueOf(applicationDataObj[i][8]));

				travelApplicationList.add(travelBean);

			}
			logger.info("travelApplicationList.size()=="
					+ travelApplicationList.size());
			travelApplicationBean
					.setTravelApplicationIteratorlist(travelApplicationList);
			logger.info("travelApplicationBean.size()=="
					+ travelApplicationBean.getTravelApplicationIteratorlist()
							.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public void addTravellerEmployee(TravelApplication trvlApp,
			String[] empToken, String[] empName, String[] empAge,
			String[] empContact) {
		ArrayList<Object> travellerEmployeeList;

		System.out.println("############# EMP ID #################"
				+ String.valueOf(trvlApp.getEmpTokenNo()));
		System.out.println("############# EMP NAME #################"
				+ String.valueOf(trvlApp.getEmployeeName()));
		System.out.println("############# AGE #################"
				+ String.valueOf(trvlApp.getEmployeeAge()));
		System.out.println("############# CONTACT #################"
				+ String.valueOf(trvlApp.getEmployeeContact()));

		try {
			travellerEmployeeList = new ArrayList<Object>();
			TravelApplication localBean = new TravelApplication();
			localBean.setEmpId(trvlApp.getEmpTokenNo());
			localBean.setEmpName(trvlApp.getEmployeeName());
			localBean.setAge(trvlApp.getEmployeeAge());
			localBean.setContact(trvlApp.getEmployeeContact());

			travellerEmployeeList.add(localBean);
			trvlApp.setEmpList(travellerEmployeeList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean saveApplication(TravelApplication trvlApp,
			HttpServletRequest request, String[] empListEmployeeId,
			String[] empListEmployeeType, String[] empListEmployeeName,
			String[] empListEmployeeAge, String[] empListEmployeeContact,
			String[] empListAdvAmount, String[] frmPlace, String[] toPlace,
			String[] jourModeId, String[] jourMode, String[] jourDate,
			String[] jourTime, String[] lodgTypeId, String[] roomTypeId,
			String[] cityId, String[] city, String[] prfrdLoc,
			String[] frmDate, String[] frmTime, String[] toDate,
			String[] toTime, String[] locCity, String[] trvlDtls,
			String[] medium, String[] locFrmDate, String[] locFromTime,
			String[] locToDate, String[] locToTime, String status,
			Object[][] empFlow, String[] currencyEmpList) {

		logger.info("######## IN SAVE MODEL METHOD #################");
		boolean result = false;

		try {

			// INSERT RECORD CODE BEGINS
			String keepInformToCode[] = request
					.getParameterValues("keepInformToCode");
			String keepInformToEmpCode = "";
			if (keepInformToCode != null && keepInformToCode.length > 0) {
				for (int i = 0; i < keepInformToCode.length; i++) {
					if (keepInformToCode.length - 1 == i) {
						keepInformToEmpCode += keepInformToCode[i];
					} else {
						keepInformToEmpCode += keepInformToCode[i] + ",";
					}

				}
			}

			String appCodeQuery = "SELECT NVL(MAX(APPL_ID),0) FROM TMS_APPLICATION";
			Object appCode[][] = getSqlModel().getSingleResult(appCodeQuery);

			String applicationCode = String.valueOf(appCode[0][0]);

			String applicationIdQuery = "SELECT NVL(MAX(APPL_ID),0)+1 FROM TMS_APPLICATION";
			Object trvlApplId[][] = getSqlModel().getSingleResult(
					applicationIdQuery);

			String applicationId = String.valueOf(trvlApplId[0][0]);

			trvlApp.setHiddenApplicationCode(applicationId);

			/*
			 * SAVING TRAVEL DETAILS TRAVEL START DATE, END DATE, REQUEST NAME &
			 * TRAVEL PURPOSE
			 */

			Object insertApplicationData[][] = new Object[1][23];

			insertApplicationData[0][0] = applicationId;// application Id
			insertApplicationData[0][1] = trvlApp.getHAppFor();// applicant
			// type
			insertApplicationData[0][2] = trvlApp.getAppDate();// application
			// date
			insertApplicationData[0][3] = trvlApp.getInitId();// initiator Id
			insertApplicationData[0][4] = "";// accompany with
			insertApplicationData[0][5] = trvlApp.getApplComm();// applicant
			// comments
			insertApplicationData[0][6] = keepInformToEmpCode; // keep inform
			// to
			insertApplicationData[0][7] = trvlApp.getStartDate(); // travel
			// start
			// date
			insertApplicationData[0][8] = trvlApp.getEndDate();// travel end
			// date
			insertApplicationData[0][9] = trvlApp.getTrvlReqName();// travel
			// request
			// name
			insertApplicationData[0][10] = trvlApp.getPurposeId();// travel
			// purpose
			// Id
			insertApplicationData[0][11] = trvlApp.getTrvlTypeId();// travel
			// type Id
			insertApplicationData[0][12] = trvlApp.getCustomerId();// customer
			// Id
			insertApplicationData[0][13] = trvlApp.getProjectId();// project
			// Id
			insertApplicationData[0][14] = trvlApp.getOtherCustomerName();// other
			// customer
			// name
			insertApplicationData[0][15] = trvlApp.getOtherProject();// other
			// project
			// name
			insertApplicationData[0][16] = trvlApp.getJourneyRadio();// journey
			// radio
			// btn
			// val
			insertApplicationData[0][17] = trvlApp.getAccomodationRadio();// accomodation
			// radio
			// btn
			// val
			insertApplicationData[0][18] = trvlApp.getLocalConvRadio();// local
			// conv
			// radio
			// btn
			// val
			insertApplicationData[0][19] = status;

			insertApplicationData[0][20] = empFlow[0][0];

			insertApplicationData[0][21] = empFlow[0][3];

			insertApplicationData[0][22] = "TRAVEL_" + applicationId;

			result = getSqlModel().singleExecute(getQuery(92),
					insertApplicationData);

			if (result) {

				/* FETCHING MAX APPLICATION ID FROM THE TMS_APPLICATION TABLE */

				Object appObj[][] = getSqlModel().getSingleResult(getQuery(6));
				trvlApp.setAppId(String.valueOf(appObj[0][0]));

				if (empListEmployeeId != null && empListEmployeeId.length > 0) {

					System.out.println("empListEmployeeId.length----------"
							+ empListEmployeeId.length);

					for (int i = 0; i < empListEmployeeId.length; i++) {

						if (empListEmployeeType[i].equals("S")
								|| empListEmployeeType[i].equals("O")) {
							// INSERTING EMPLOYEE DETAILS TO TMS_APP_EMPDTL
							Object insertEmp[][] = new Object[1][11];
							insertEmp[0][0] = String.valueOf(applicationId);
							insertEmp[0][1] = empListEmployeeId[i];
							insertEmp[0][2] = empListEmployeeAge[i];
							insertEmp[0][3] = empListEmployeeContact[i];
							insertEmp[0][4] = empListAdvAmount[i];
							insertEmp[0][5] = empFlow[0][0];// main approver
							insertEmp[0][6] = empFlow[0][3];
							// alt approver

							// 5. Insert data in TMS_APP_EMPDTL table
							insertEmp[0][7] = status;
							insertEmp[0][8] = String.valueOf(applicationId);
							insertEmp[0][9] = empListAdvAmount[i];
							insertEmp[0][10] = currencyEmpList[i];
							result = getSqlModel().singleExecute(getQuery(90),
									insertEmp);

						} else {
							// INSERTING GUEST DETAILS TO TMS_APP_GUEST_DTL

							Object insertEmp[][] = new Object[1][9];
							insertEmp[0][0] = String.valueOf(applicationId);
							insertEmp[0][1] = empListEmployeeName[i];
							insertEmp[0][2] = empListEmployeeAge[i];
							insertEmp[0][3] = empListEmployeeContact[i];
							insertEmp[0][4] = empListAdvAmount[i];
							insertEmp[0][5] = empFlow[0][0]; // main approver
							insertEmp[0][6] = empFlow[0][3]; // alt approver

							insertEmp[0][7] = status;
							insertEmp[0][8] = String.valueOf(applicationId);

							// 5. Insert data in TMS_APP_EMPDTL table
							result = getSqlModel().singleExecute(getQuery(91),
									insertEmp);

						}// end of else for adding guest
					}// end of for loop

				}
				/*
				 * ########## INSERTING JOURNEY DETAILS, ACCOMODATION & LOCAL
				 * CONVEYANCE DETAILS #################
				 */

				// Journey detail
				if (frmPlace != null && frmPlace.length > 0) {
					for (int i = 0; i < frmPlace.length; i++) {
						Object journeyDataObj[][] = new Object[1][7];
						journeyDataObj[0][0] = applicationId;// App Id
						journeyDataObj[0][1] = frmPlace[i];
						journeyDataObj[0][2] = toPlace[i];
						journeyDataObj[0][3] = jourModeId[i];
						journeyDataObj[0][4] = jourDate[i];
						journeyDataObj[0][5] = jourTime[i];
						journeyDataObj[0][6] = "";
						result = getSqlModel().singleExecute(getQuery(21),
								journeyDataObj);
					}
				}// Journey detail ends

				// accomodation details

				System.out.println("########### Accom radio ###############"
						+ trvlApp.getAccomodationRadio());

				if (trvlApp.getAccomodationRadio().equals("C")) {
					if (lodgTypeId != null && lodgTypeId.length > 0) {
						for (int i = 0; i < lodgTypeId.length; i++) {
							Object accomodationDataObj[][] = new Object[1][10];
							accomodationDataObj[0][0] = applicationId;
							accomodationDataObj[0][1] = lodgTypeId[i];// Hotel
							// type
							accomodationDataObj[0][2] = roomTypeId[i];// Room
							// type
							accomodationDataObj[0][3] = city[i];
							accomodationDataObj[0][4] = prfrdLoc[i];
							accomodationDataObj[0][5] = frmDate[i];
							accomodationDataObj[0][6] = toDate[i];
							accomodationDataObj[0][7] = frmTime[i];
							accomodationDataObj[0][8] = toTime[i];
							accomodationDataObj[0][9] = "";

							result = getSqlModel().singleExecute(getQuery(22),
									accomodationDataObj);
						}
					}// accomodation details ends

				}

				/* SAVING LOCAL CONVEYANCE DETAILS */
				System.out
						.println("############ local conv radio ##############"
								+ trvlApp.getLocalConvRadio());
				if (trvlApp.getLocalConvRadio().equals("C")) {
					if (locCity != null && locCity.length > 0) {
						for (int i = 0; i < locCity.length; i++) {
							Object localConveyanceDataObj[][] = new Object[1][9];
							localConveyanceDataObj[0][0] = applicationId;
							localConveyanceDataObj[0][1] = locCity[i];// Hotel
							// type
							localConveyanceDataObj[0][2] = trvlDtls[i];// Room
							// type
							localConveyanceDataObj[0][3] = medium[i];
							localConveyanceDataObj[0][4] = locFrmDate[i];
							localConveyanceDataObj[0][5] = locToDate[i];
							localConveyanceDataObj[0][6] = locFromTime[i];
							localConveyanceDataObj[0][7] = locToTime[i];
							localConveyanceDataObj[0][8] = "";

							result = getSqlModel().singleExecute(getQuery(23),
									localConveyanceDataObj);
						}
					}
				}

				/* Update approximate budget of tour */

				TravelProcessModel processModel = new TravelProcessModel();
				processModel.initiate(context, session);
				double approximateAmount = 0.0d;

				try {
					approximateAmount = processModel
							.getApproximateBudget(applicationId);

					String budgetQuery = " UPDATE  TMS_APPLICATION SET APPL_APPROXIMATE_BUDGET = "
							+ approximateAmount
							+ " WHERE APPL_ID="
							+ applicationId;
					result = getSqlModel().singleExecute(budgetQuery);

				} catch (Exception e) {
					e.printStackTrace();
				}
				processModel.terminate();
			}
			System.out.println("status------------"+status);
			if (trvlApp.getDraftOrSend().equals("P")) {
				result =isApplicationUnderProcess(applicationId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] sendApplicationForApproval(TravelApplication trvlApp,
			HttpServletRequest request) {

		boolean result = false;
		Object apprData[][] = null;

		try {
			// result = saveApplication(trvlApp, request);

			String empListEmployeeType[] = request
					.getParameterValues("employeeTypeFromList");

			String empListEmployeeId[] = request
					.getParameterValues("employeeTravellerIdFromList");

			logger.info("########## APP ID ###########" + trvlApp.getAppId());
			logger.info("########## TRAVEL ID ###########"
					+ trvlApp.getTrvlId());
			logger.info("########## INITIATOR ID ###########"
					+ trvlApp.getInitId());
			logger.info("########## EMPLOYEE TYPE ###########"
					+ trvlApp.getHAppFor());

			Object appObj[][] = getSqlModel().getSingleResult(getQuery(6));// App
			// ID

			if (result) { /* UPDATE EMPLOYEE STATUS & SEND FOR APPROVAL */

				if (appObj != null && appObj.length > 0) {

					String applCodeQuery = " SELECT APPL_CODE FROM TMS_APP_EMPDTL WHERE APPL_ID="
							+ String.valueOf(appObj[0][0])
							+ " UNION ALL "
							+ " SELECT APPL_CODE FROM TMS_APP_GUEST_DTL WHERE APPL_ID="
							+ String.valueOf(appObj[0][0]);
					Object applicationCode[][] = getSqlModel().getSingleResult(
							applCodeQuery);

					/* UPDATING APPLICATION STATUS IN TMS_APPLICATION */

					getSqlModel().singleExecute(getQuery(89), applicationCode);

					if (empListEmployeeType != null
							&& empListEmployeeType.length > 0) {

						System.out
								.println("############ empListEmployeeType.length ##############"
										+ empListEmployeeType.length);
						System.out
								.println("############ applicationCode.length ##############"
										+ applicationCode.length);

						String empApproverId[][] = null;

						if (empListEmployeeId != null
								&& empListEmployeeId.length > 0) {
							for (int k = 0; k < empListEmployeeId.length; k++) {
								logger
										.info("############ APPLICATION CODE ##############"
												+ applicationCode[k][0]);

								if (empListEmployeeType[k].equals("S")
										|| empListEmployeeType[k].equals("O")) {
									logger
											.info("############ Application ID in if loop##############"
													+ trvlApp.getAppId());
									logger
											.info("############ EMPLOYEE FLAG in if loop##############"
													+ empListEmployeeType[k]);
									Object updateEmployee[][] = new Object[1][3];
									updateEmployee[0][0] = "P";
									updateEmployee[0][1] = trvlApp.getAppId();
									updateEmployee[0][2] = String
											.valueOf(applicationCode[k][0]);
									result = getSqlModel().singleExecute(
											getQuery(38), updateEmployee);

									// 2. Get empId, deptId, branchId, desgId of
									// applicant
									Object empDtls[][] = getSqlModel()
											.getSingleResult(
													getQuery(34)
															+ " AND APPL_CODE = "
															+ String
																	.valueOf(applicationCode[k][0]),
													new Object[] { trvlApp
															.getAppId() });
									String initiatorId = trvlApp.getInitId();
									String gradeId = String
											.valueOf(empDtls[0][9]);
									String deptId = String
											.valueOf(empDtls[0][10]);
									String branchId = String
											.valueOf(empDtls[0][11]);
									String desgId = String
											.valueOf(empDtls[0][12]);

									// 3. Find approver of the Initiator
									// Object apprData[][] =
									// getReportingStructure(
									// empId, deptId,
									// branchId, desgId);
									ReportingModel model = new ReportingModel();
									model.initiate(context, session);
									apprData = model.generateEmpFlow(
											initiatorId, "TYD", 1);// getReportingStructure(bean.getAppEmpId(),
									model.terminate();

									// 4. Update TMS_APP_EMPDTL table
									Object param[][] = new Object[1][4];

									if (apprData != null && apprData.length > 0) {
										param[0][0] = apprData[0][0];// main_appr
										param[0][1] = apprData[0][3];// alt_appr
									} else {
										param[0][0] = "";// main_appr
										param[0][1] = "";// alt_appr
									}
									param[0][2] = trvlApp.getAppId();
									param[0][3] = String
											.valueOf(applicationCode[k][0]);
									boolean updateResult = getSqlModel()
											.singleExecute(getQuery(55), param);

									// INSERTING DATA IN APPROVAL TABLE
									Object insertParam[][] = new Object[1][4];
									insertParam[0][0] = trvlApp.getAppId();
									insertParam[0][1] = String
											.valueOf(applicationCode[k][0]);

									if (apprData != null && apprData.length > 0) {
										insertParam[0][2] = apprData[0][0];
									} else {
										insertParam[0][2] = "";
									}
									insertParam[0][3] = "1";

									result = getSqlModel().singleExecute(
											getQuery(42), insertParam);

								} else {/* GUEST UPDATES */
									logger
											.info("############ Application ID in else loop##############"
													+ trvlApp.getAppId());
									logger
											.info("############ EMPLOYEE FLAG in else loop##############"
													+ empListEmployeeType[k]);

									Object updateGuest[][] = new Object[1][3];
									updateGuest[0][0] = "P";
									updateGuest[0][1] = trvlApp.getAppId();
									updateGuest[0][2] = String
											.valueOf(applicationCode[k][0]);
									result = getSqlModel().singleExecute(
											getQuery(39), updateGuest);

									// 2. Get dtls of the initiator
									Object initDtls[][] = getSqlModel()
											.getSingleResult(
													getQuery(56),
													new Object[] { trvlApp
															.getAppId() });

									logger
											.info("################### initDtls.length ##############"
													+ initDtls.length);

									if (initDtls != null && initDtls.length > 0) {

										String initiatorId = trvlApp
												.getInitId();
										String gradeId = String
												.valueOf(initDtls[0][1]);
										String deptId = String
												.valueOf(initDtls[0][2]);
										String branchId = String
												.valueOf(initDtls[0][3]);
										String desgId = String
												.valueOf(initDtls[0][4]);

										// 3. Find approver of the Initiator

										ReportingModel model = new ReportingModel();
										model.initiate(context, session);
										apprData = model.generateEmpFlow(
												initiatorId, "TYD", 1);// getReportingStructure(bean.getAppEmpId(),
										model.terminate();

										System.out.println("MAIN APPROVER ::"
												+ apprData[0][0]);

										// 4. Update TMS_APP_GUEST_DTL table
										Object param[][] = new Object[1][4];
										if (apprData != null
												&& apprData.length > 0) {
											param[0][0] = apprData[0][0];// main_appr
											param[0][1] = apprData[0][3];// alt_appr
										} else {
											param[0][0] = "";// main_appr
											param[0][1] = "";// alt_appr
										}
										param[0][2] = trvlApp.getAppId();
										param[0][3] = String
												.valueOf(applicationCode[k][0]);
										boolean updateResult = getSqlModel()
												.singleExecute(getQuery(64),
														param);

										// insert in approval table
										Object insertParam[][] = new Object[1][4];
										insertParam[0][0] = trvlApp.getAppId();
										insertParam[0][1] = String
												.valueOf(applicationCode[k][0]);
										insertParam[0][2] = apprData[0][0];// main_appr
										insertParam[0][3] = "1";
										result = getSqlModel().singleExecute(
												getQuery(42), insertParam);
									}

								}

							}
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getApplicationDetailsByApplicationId(trvlApp, request);
		return apprData;
	}

	public boolean getApplicationDetailsByApplicationId(TravelApplication trvlApp,
			HttpServletRequest request) {
		boolean flag =false;
		try {
			logger.info("############## APPLICATION ID ##################"
					+ trvlApp.getHiddenApplicationCode());
			String employeeInformationQuery = " SELECT NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,'')AS APPLICANT,NVL(APPL_EMP_AGE,0) AS AGE , "
					+ " NVL(APPL_EMP_CONTACT,''),CASE WHEN nvl(APPL_APPROVED_ADVANCE_AMOUNT,0) = 0 THEN nvl(APPL_EMP_ADVANCE_AMT,0) ELSE APPL_APPROVED_ADVANCE_AMOUNT END AS ADVANCE_AMOUNT, APPL_EMP_CODE,'S',EMP_CADRE, NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'),' ') AS DOB "
					+ " , NVL(APPL_APPROVED_ADVANCE_AMOUNT,0), TO_CHAR(NVL(CADRE_NAME,'')), TO_CHAR(NVL(APPL_CURRENCY,''))"
					+ " FROM TMS_APP_EMPDTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(TMS_APP_EMPDTL.APPL_EMP_CODE = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P') "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID =HRMS_EMP_OFFC.EMP_CADRE)"
					+ " WHERE APPL_ID= "
					+ trvlApp.getHiddenApplicationCode()
					+ " UNION ALL  "
					+ " SELECT NVL(GUEST_NAME,'') AS APPLICANT, NVL(GUEST_AGE,0) AS AGE, "
					+ " NVL(GUEST_CONTACT,''),NVL(GUEST_ADVANCE_AMT,0),0 ,'G',0, '',0, '', ''"
					+ " FROM TMS_APP_GUEST_DTL WHERE APPL_ID= "
					+ trvlApp.getHiddenApplicationCode();
			String employeeDetailsQuery = " SELECT TRIM(TRIM(NVL(EMP_FNAME,' '))||' '|| TRIM(NVL(EMP_MNAME,' '))||' '||TRIM(NVL(EMP_LNAME,' '))) AS INITIATOR_NAME "
					+ " ,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPLICATIONDATE , DECODE(TRIM(APPL_STATUS),'P','Pending for approval','C','Booking Completed','A','Approved','N','Draft','B','Sent Back','F','Revoked') AS STATUS , "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRAVELSTARTDATE, TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS  TRAVELENDDATE "
					+ " ,TOUR_TRAVEL_REQ_NAME AS REQNAME , NVL(PURPOSE_NAME,''),TOUR_TRAVEL_PURPOSE,NVL(PROJECT_NAME,''),TOUR_PROJECT "
					+ " ,NVL(TOUR_OTHER_PROJECT	,''),TOUR_CUSTOMER,NVL(TRAVEL_CUST_NAME,''),NVL(TOUR_OTHER_CUSTOMER,''),TOUR_TRAVEL_TYPE,NVL(LOCATION_TYPE_NAME,''),NVL(APPL_APPLCANT_COMMENTS,''), "
					+ " APPL_MANAGE_JOURNEY,APPL_MANAGE_ACCOMODATION, APPL_MANAGE_LOCAL_CONV  "
					+ " ,NVL(EMP_TOKEN,' '),EMP_ID,APPL_APPLCANT_COMMENTS ,APPL_VIOLATION_REASON, APPL_ISPOLICYVIOLATED, APPL_VIOLATION_DETAILS, EMP_CADRE, TO_CHAR(NVL(CADRE_NAME,'')) FROM TMS_APPLICATION "
					+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
					+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID =TMS_APPLICATION.TOUR_TRAVEL_PURPOSE) "
					+ " LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID =TMS_APPLICATION.TOUR_PROJECT ) "
					+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER) "
					+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = TMS_APPLICATION.TOUR_TRAVEL_TYPE) "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID =HRMS_EMP_OFFC.EMP_CADRE)"
					+ " WHERE APPL_ID= " + trvlApp.getHiddenApplicationCode();
			String journeyDetailsQuery = " SELECT  JOURNEY_FROM, JOURNEY_TO, NVL(JOURNEY_NAME||'-'||CLASS_NAME,' '), TO_CHAR(JOURNEY_DATE,'DD-MM-YYYY'), JOURNEY_TIME, JOURNEY_MODECLASS,JOURNEY_CODE "
					+ " FROM TMS_APP_JOURNEY_DTL "
					+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID=TMS_APP_JOURNEY_DTL.JOURNEY_MODECLASS)"
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
					+ " WHERE APPL_ID= " + trvlApp.getHiddenApplicationCode();
			String lodgingDetailsQuery = " SELECT  LODGE_CODE, HOTEL_TYPE_NAME,ROOM_TYPE_NAME, LODGE_CITY, LODGE_PRE_LOCATION, TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY'), TO_CHAR(LODGE_TODATE,'DD-MM-YYYY') , LODGE_FROMTIME, LODGE_TOTIME "
					+ " ,LODGE_HOTELTYPE, LODGE_ROOMTYPE "
					+ " FROM TMS_APP_LODGE_DTL "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_HOTELTYPE ) "
					+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON (HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_ROOMTYPE ) "
					+ " WHERE APPL_ID= " + trvlApp.getHiddenApplicationCode();
			String localConveyanceDetailsQuery = " SELECT CONV_CODE, CONV_CITY, CONV_TRAVELDTL, CONV_MEDIUM, TO_CHAR(CONV_FROMDATE,'DD-MM-YYYY'), TO_CHAR(CONV_TODATE,'DD-MM-YYYY'), CONV_FROMTIME, CONV_TOTIME "
					+ " FROM TMS_APP_CONV_DTL WHERE APPL_ID= "
					+ trvlApp.getHiddenApplicationCode();

			Object employeeDetailsDataObj[][] = getSqlModel().getSingleResult(
					employeeDetailsQuery);
			Object employeeInformationDataObj[][] = getSqlModel()
					.getSingleResult(employeeInformationQuery);
			Object journeyDataObj[][] = getSqlModel().getSingleResult(
					journeyDetailsQuery);
			Object lodgingDetailsDataObj[][] = getSqlModel().getSingleResult(
					lodgingDetailsQuery);
			Object localConveyanceDetailsDataObj[][] = getSqlModel()
					.getSingleResult(localConveyanceDetailsQuery);
			if (employeeInformationDataObj != null
					&& employeeInformationDataObj.length > 0) {

				ArrayList <Object> empTravellerList = new ArrayList <Object>();

				for (int i = 0; i < employeeInformationDataObj.length; i++) {
					TravelApplication travellerBean = new TravelApplication();

					travellerBean.setEmployeeNameFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][0])));// emp
					// name
					travellerBean.setEmployeeAgeFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][1])));// emp
					// age
					travellerBean.setEmployeeContactFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][2])));// emp
					// contact
					travellerBean.setEmployeeAdvanceFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][3])));// advance
					// amount
					travellerBean
							.setEmployeeTravellerIdFromList(checkNull(String
									.valueOf(employeeInformationDataObj[i][4])));// traveller
					// id
					travellerBean.setEmployeeTypeFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][5])));// traveller
					// type

					travellerBean.setTravellerGradeId(checkNull(String
							.valueOf(employeeInformationDataObj[i][6])));
					travellerBean
							.setEmployeeDateOfBirthFromList(checkNull(String
									.valueOf(employeeInformationDataObj[i][7])));// DOB

					travellerBean.setApprovedAdvanceAmount(checkNull(String
							.valueOf(employeeInformationDataObj[i][8])));
					travellerBean.setEmployeeBandFromList(String
							.valueOf(employeeInformationDataObj[i][9]));
					travellerBean.setCurrencyEmployeeAdvance(String.valueOf(employeeInformationDataObj[i][10]));

					empTravellerList.add(travellerBean);
				}
				trvlApp.setTravellerList(empTravellerList);
			}
			if (employeeDetailsDataObj != null
					&& employeeDetailsDataObj.length > 0) {

				trvlApp.setInitName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][0])));// initiator
				// name
				trvlApp.setAppDate(checkNull(String
						.valueOf(employeeDetailsDataObj[0][1])));// application
				// date
				trvlApp.setAppStatus(checkNull(String
						.valueOf(employeeDetailsDataObj[0][2])));// application
				// status
				trvlApp.setStartDate(checkNull(String
						.valueOf(employeeDetailsDataObj[0][3])));// travel
				// start
				// date
				trvlApp.setEndDate(checkNull(String
						.valueOf(employeeDetailsDataObj[0][4])));// travel
				// end
				// date
				trvlApp.setTrvlReqName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][5])));// travel
				// request
				// name
				trvlApp.setPurpose(checkNull(String
						.valueOf(employeeDetailsDataObj[0][6])));// travel
				// purpose
				// name
				trvlApp.setPurposeId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][7])));// travel
				// purpose
				// id
				trvlApp.setProject(checkNull(String
						.valueOf(employeeDetailsDataObj[0][8])));// travel
				// project
				// name
				trvlApp.setProjectId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][9])));// tour
				// project
				trvlApp.setOtherProject(checkNull(String
						.valueOf(employeeDetailsDataObj[0][10])));// tour
				// other
				// project
				trvlApp.setCustomerId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][11])));// tour
				// customer
				// id
				trvlApp.setCustomerName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][12])));// tour
				// customer
				// name
				trvlApp.setOtherCustomerName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][13])));// tour
				// other
				// project
				trvlApp.setTrvlTypeId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][14])));// travel
				// type
				// id
				trvlApp.setTrvlType(checkNull(String
						.valueOf(employeeDetailsDataObj[0][15])));// travel
				// type
				// name
				trvlApp.setApplicantComments(checkNull(String
						.valueOf(employeeDetailsDataObj[0][16])));// travel
				// type
				// id
				trvlApp.setJourneyRadio(String
						.valueOf(employeeDetailsDataObj[0][17]));// journey
				// radio
				trvlApp.setAccomodationRadio(String
						.valueOf(employeeDetailsDataObj[0][18]));// accomodation
				// radio
				trvlApp.setLocalConvRadio(String
						.valueOf(employeeDetailsDataObj[0][19]));// local
				// conv
				// radio

				trvlApp.setInitToken(checkNull(String
						.valueOf(employeeDetailsDataObj[0][20])));

				trvlApp.setInitId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][21])));

				trvlApp.setApplComm(checkNull(String
						.valueOf(employeeDetailsDataObj[0][22])));
				trvlApp.setViolationReason(checkNull(String
						.valueOf(employeeDetailsDataObj[0][23])));// violation
				// reason
				trvlApp.setViolationStatus(checkNull(String
						.valueOf(employeeDetailsDataObj[0][24])));// violation
				// status Y
				// or N
				trvlApp.setPolicyViolationMsg(checkNull(String
						.valueOf(employeeDetailsDataObj[0][25])));// violation
				// msg
				trvlApp.setInitiatorGradeId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][26])));// initiator
				// grade

				trvlApp.setEmpBand(checkNull(String
						.valueOf(employeeDetailsDataObj[0][27])));// initiator_grade_Name
			}
			if (journeyDataObj != null && journeyDataObj.length > 0) {

				ArrayList <Object>empJourneyList = new ArrayList <Object>();

				for (int i = 0; i < journeyDataObj.length; i++) {

					TravelApplication journeyBean = new TravelApplication();

					journeyBean.setJourneyFromPlace(checkNull(String
							.valueOf(journeyDataObj[i][0])));// journey from
					journeyBean.setJourneyToPlace(checkNull(String
							.valueOf(journeyDataObj[i][1])));// journey to

					journeyBean.setJourneyMode(checkNull(String
							.valueOf(journeyDataObj[i][2])));// journey
					// date

					journeyBean.setJourneyDate(checkNull(String
							.valueOf(journeyDataObj[i][3])));// journey
					// date
					journeyBean.setJourneyTime(checkNull(String
							.valueOf(journeyDataObj[i][4])));// journey
					// time
					journeyBean.setJourneyModeId(checkNull(String
							.valueOf(journeyDataObj[i][5])));// journeyModeId

					journeyBean.setJourCode(checkNull(String
							.valueOf(journeyDataObj[i][6])));// journey
					// code
					empJourneyList.add(journeyBean);
				}
				trvlApp.setJourneyList(empJourneyList);
			}
			if (lodgingDetailsDataObj != null
					&& lodgingDetailsDataObj.length > 0) {

				ArrayList <Object>empAccomodationList = new ArrayList<Object>();
				for (int i = 0; i < lodgingDetailsDataObj.length; i++) {

					TravelApplication accomodationBean = new TravelApplication();

					accomodationBean.setAccomodationId(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][0])));// accomodation
					// Id
					accomodationBean.setAccomodationHotelType(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][1])));// hotel
					// type
					// name
					accomodationBean.setAccomodationRoomType(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][2])));// room type
					// name
					accomodationBean.setAccomodationCity(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][3])));// city
					accomodationBean
							.setAccomodationPrefLocation(checkNull(String
									.valueOf(lodgingDetailsDataObj[i][4])));// preferred
					// loc
					accomodationBean.setAccomodationFromDate(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][5])));// from date
					accomodationBean.setAccomodationToDate(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][6])));// to date
					accomodationBean.setAccomodationFromTime(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][7])));// from time
					accomodationBean.setAccomodationToTime(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][8])));// to time
					accomodationBean
							.setAccomodationHotelTypeId(checkNull(String
									.valueOf(lodgingDetailsDataObj[i][9])));// hotel
					// type id
					accomodationBean.setAccomodationRoomTypeId(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][10])));// room
					// type
					// id
					empAccomodationList.add(accomodationBean);

				}
				trvlApp.setAccomodationList(empAccomodationList);
			}
			if (localConveyanceDetailsDataObj != null
					&& localConveyanceDetailsDataObj.length > 0) {

				ArrayList <Object> empLocalConveyanceList = new ArrayList <Object>();

				for (int i = 0; i < localConveyanceDetailsDataObj.length; i++) {

					TravelApplication localConveyanceBean = new TravelApplication();
					localConveyanceBean.setLocalConveyanceCode(checkNull(String
							.valueOf(localConveyanceDetailsDataObj[i][0])));// conveyance
					// code
					localConveyanceBean.setLocalConveyanceCity(checkNull(String
							.valueOf(localConveyanceDetailsDataObj[i][1])));// conveyance
					// city
					localConveyanceBean
							.setLocalConveyanceTravelDetail(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][2])));// travel
					// detail
					localConveyanceBean
							.setLocalConveyanceTravelMedium(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][3])));// travel
					// medium
					localConveyanceBean
							.setLocalConveyanceFromDate(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][4])));// conveyance
					// from
					// date
					localConveyanceBean
							.setLocalConveyanceToDate(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][5])));// conveyance
					// to
					// date
					localConveyanceBean
							.setLocalConveyanceFromTime(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][6])));// conveyance
					// from
					// time
					localConveyanceBean
							.setLocalConveyanceToTime(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][7])));// conveyance
					// to
					// time
					empLocalConveyanceList.add(localConveyanceBean);

				}
				trvlApp.setLocalConveyanceList(empLocalConveyanceList);
			}
			trvlApp.setCounterVal("2");
			flag =getKeepInformedSavedRecord(trvlApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ;
	}

	public boolean updateApplication(TravelApplication trvlApp,
			HttpServletRequest request, String[] empListEmployeeId,
			String[] empListEmployeeType, String[] empListEmployeeName,
			String[] empListEmployeeAge, String[] empListEmployeeContact,
			String[] empListAdvAmount, String[] frmPlace, String[] toPlace,
			String[] jourModeId, String[] jourMode, String[] jourDate,
			String[] jourTime, String[] lodgTypeId, String[] roomTypeId,
			String[] cityId, String[] city, String[] prfrdLoc,
			String[] frmDate, String[] frmTime, String[] toDate,
			String[] toTime, String[] locCity, String[] trvlDtls,
			String[] medium, String[] locFrmDate, String[] locFromTime,
			String[] locToDate, String[] locToTime, String status,
			Object[][] empFlow, String[] currencyEmpList) {
		boolean result = false;

		try {
			
			

			String keepInformToCode[] = request
					.getParameterValues("keepInformToCode");
			String keepInformToEmpCode = "";
			if (keepInformToCode != null && keepInformToCode.length > 0) {
				for (int i = 0; i < keepInformToCode.length; i++) {
					if (keepInformToCode.length - 1 == i) {
						keepInformToEmpCode += keepInformToCode[i];
					} else {
						keepInformToEmpCode += keepInformToCode[i] + ",";
					}

				}
			}

			String updateQuery = " UPDATE TMS_APPLICATION SET  TOUR_TRAVEL_STARTDT=TO_DATE(?,'DD-MM-YYYY') "
					+ "  ,TOUR_TRAVEL_ENDDT=TO_DATE(?,'DD-MM-YYYY'),TOUR_TRAVEL_REQ_NAME=?, "
					+ " TOUR_TRAVEL_PURPOSE=?,TOUR_TRAVEL_TYPE=?,TOUR_CUSTOMER=?,TOUR_PROJECT=? "
					+ " ,TOUR_OTHER_CUSTOMER=?,TOUR_OTHER_PROJECT=?, "
					+ " APPL_MANAGE_JOURNEY=?,APPL_MANAGE_ACCOMODATION=?,APPL_MANAGE_LOCAL_CONV=? ,APPL_STATUS=?"
					+ " ,APPL_KEEP_INFO=? ,APPL_MAIN_APPROVER=?, APPL_ALTER_APPROVER=? ,APPL_APPLCANT_COMMENTS=? ,APPL_TRAVEL_ID=?  "
					+ "  WHERE APPL_ID=? ";

			Object updateObj[][] = new Object[1][19];

			updateObj[0][0] = trvlApp.getStartDate();
			updateObj[0][1] = trvlApp.getEndDate();
			updateObj[0][2] = trvlApp.getTrvlReqName();
			updateObj[0][3] = trvlApp.getPurposeId();
			updateObj[0][4] = trvlApp.getTrvlTypeId();
			updateObj[0][5] = trvlApp.getCustomerId();
			updateObj[0][6] = trvlApp.getProjectId();
			updateObj[0][7] = trvlApp.getOtherCustomerName();
			updateObj[0][8] = trvlApp.getOtherProject();
			updateObj[0][9] = trvlApp.getJourneyRadio();
			updateObj[0][10] = trvlApp.getAccomodationRadio();
			updateObj[0][11] = trvlApp.getLocalConvRadio();
			System.out.println("status   status status "+status);
			updateObj[0][12] = status;
			updateObj[0][13] = keepInformToEmpCode;
			updateObj[0][14] = empFlow[0][0];
			updateObj[0][15] = empFlow[0][3];
			updateObj[0][16] = trvlApp.getApplComm();
			updateObj[0][17] = "TRAVEL_" + trvlApp.getHiddenApplicationCode();
			updateObj[0][18] = trvlApp.getHiddenApplicationCode().trim();

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			if (result) {
				String deleteEmployeeDetailsQuery = "DELETE FROM TMS_APP_EMPDTL WHERE APPL_ID="
						+ trvlApp.getHiddenApplicationCode();
				result = getSqlModel()
						.singleExecute(deleteEmployeeDetailsQuery);

				String deleteGuestDetailsQuery = "DELETE FROM TMS_APP_GUEST_DTL WHERE APPL_ID="
						+ trvlApp.getHiddenApplicationCode();
				result = getSqlModel().singleExecute(deleteGuestDetailsQuery);

				if (empListEmployeeId != null && !empListEmployeeId.equals("")
						&& empListEmployeeId.length > 0) {

					System.out.println("empListEmployeeId.length----------"
							+ empListEmployeeId.length);

					for (int i = 0; i < empListEmployeeId.length; i++) {

						if (empListEmployeeType[i].equals("S")
								|| empListEmployeeType[i].equals("O")) {

							// GET REPORTING STRUCTURE FOR SELF & OTHER EMPLOYEE

							// INSERTING EMPLOYEE DETAILS TO TMS_APP_EMPDTL
							Object insertEmp[][] = new Object[1][11];

							insertEmp[0][0] = trvlApp
									.getHiddenApplicationCode();
							insertEmp[0][1] = empListEmployeeId[i];
							insertEmp[0][2] = empListEmployeeAge[i];
							insertEmp[0][3] = empListEmployeeContact[i];
							insertEmp[0][4] = empListAdvAmount[i];
							insertEmp[0][5] = empFlow[0][0];// main approver
							insertEmp[0][6] = empFlow[0][3];// alt approver

							// 5. Insert data in TMS_APP_EMPDTL table
							insertEmp[0][7] = status;
							insertEmp[0][8] = trvlApp
									.getHiddenApplicationCode();
							insertEmp[0][9] = empListAdvAmount[i];
							insertEmp[0][10] = currencyEmpList[i];
							result = getSqlModel().singleExecute(getQuery(90),
									insertEmp);

						} else {
							// INSERTING GUEST DETAILS TO TMS_APP_GUEST_DTL

							Object insertEmp[][] = new Object[1][9];
							insertEmp[0][0] = trvlApp
									.getHiddenApplicationCode();
							insertEmp[0][1] = empListEmployeeName[i];
							insertEmp[0][2] = empListEmployeeAge[i];
							insertEmp[0][3] = empListEmployeeContact[i];
							insertEmp[0][4] = empListAdvAmount[i];
							insertEmp[0][5] = empFlow[0][0];// main approver
							insertEmp[0][6] = empFlow[0][3];// alt approver

							insertEmp[0][7] = status;

							insertEmp[0][8] = trvlApp
									.getHiddenApplicationCode();

							// 5. Insert data in TMS_APP_EMPDTL table
							result = getSqlModel().singleExecute(getQuery(91),
									insertEmp);

						}// end of else for adding guest
					}// end of for loop

				}

				String deleteJourneyDetailsQuery = "DELETE FROM TMS_APP_JOURNEY_DTL WHERE APPL_ID="
						+ trvlApp.getHiddenApplicationCode();
				result = getSqlModel().singleExecute(deleteJourneyDetailsQuery);

				// Journey details

				// Journey detail
				if (frmPlace != null && frmPlace.length > 0) {
					for (int i = 0; i < frmPlace.length; i++) {
						Object journeyDataObj[][] = new Object[1][7];
						journeyDataObj[0][0] = trvlApp
								.getHiddenApplicationCode();// App Id
						journeyDataObj[0][1] = frmPlace[i];
						journeyDataObj[0][2] = toPlace[i];
						journeyDataObj[0][3] = jourModeId[i];
						journeyDataObj[0][4] = jourDate[i];
						journeyDataObj[0][5] = jourTime[i];
						journeyDataObj[0][6] = "";

						result = getSqlModel().singleExecute(getQuery(21),
								journeyDataObj);
					}
				}// Journey detail ends

				String deleteAccomodationDetailsQuery = "DELETE FROM TMS_APP_LODGE_DTL WHERE APPL_ID="
						+ trvlApp.getHiddenApplicationCode();
				result = getSqlModel().singleExecute(
						deleteAccomodationDetailsQuery);
				if (trvlApp.getAccomodationRadio().equals("C")) {
					// accomodation details
					if (lodgTypeId != null && !lodgTypeId.equals("")
							&& lodgTypeId.length > 0) {
						for (int i = 0; i < lodgTypeId.length; i++) {
							Object accomodationDataObj[][] = new Object[1][10];
							accomodationDataObj[0][0] = trvlApp
									.getHiddenApplicationCode();
							accomodationDataObj[0][1] = lodgTypeId[i];// Hotel
							// type
							accomodationDataObj[0][2] = roomTypeId[i];// Room
							// type
							accomodationDataObj[0][3] = city[i];
							accomodationDataObj[0][4] = prfrdLoc[i];
							accomodationDataObj[0][5] = frmDate[i];
							accomodationDataObj[0][6] = toDate[i];
							accomodationDataObj[0][7] = frmTime[i];
							accomodationDataObj[0][8] = toTime[i];
							accomodationDataObj[0][9] = "";

							result = getSqlModel().singleExecute(getQuery(22),
									accomodationDataObj);
						}
					}// accomodation details ends
				}

				String deleteLocalConveyanceDetailsQuery = "DELETE FROM TMS_APP_CONV_DTL WHERE APPL_ID="
						+ trvlApp.getHiddenApplicationCode();
				result = getSqlModel().singleExecute(
						deleteLocalConveyanceDetailsQuery);

				/* SAVING LOCAL CONVEYANCE DETAILS */
				if (trvlApp.getLocalConvRadio().equals("C")) {
					if (locCity != null && !locCity.equals("")
							&& locCity.length > 0) {
						for (int i = 0; i < locCity.length; i++) {
							Object localConveyanceDataObj[][] = new Object[1][9];
							localConveyanceDataObj[0][0] = trvlApp
									.getHiddenApplicationCode();
							localConveyanceDataObj[0][1] = locCity[i];// Hotel
							// type
							localConveyanceDataObj[0][2] = trvlDtls[i];// Room
							// type
							localConveyanceDataObj[0][3] = medium[i];
							localConveyanceDataObj[0][4] = locFrmDate[i];
							localConveyanceDataObj[0][5] = locToDate[i];
							localConveyanceDataObj[0][6] = locFromTime[i];
							localConveyanceDataObj[0][7] = locToTime[i];
							localConveyanceDataObj[0][8] = "";

							result = getSqlModel().singleExecute(getQuery(23),
									localConveyanceDataObj);
						}
					}

				}

			}
			/* Update approximate budget of tour */

			TravelProcessModel processModel = new TravelProcessModel();
			processModel.initiate(context, session);
			double approximateAmount = 0.0d;

			try {
				approximateAmount = processModel.getApproximateBudget(trvlApp
						.getHiddenApplicationCode());

				String budgetQuery = " UPDATE  TMS_APPLICATION SET APPL_APPROXIMATE_BUDGET = "
						+ approximateAmount
						+ " WHERE APPL_ID="
						+ trvlApp.getHiddenApplicationCode();
				result = getSqlModel().singleExecute(budgetQuery);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("status-------#######################################################################-----"+status);
			if (trvlApp.getDraftOrSend().equals("P")) {
				result =isApplicationUnderProcess(trvlApp.getHiddenApplicationCode().trim());
			}
			processModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getKeepInformedSavedRecord(TravelApplication trvlApp) {
		boolean flag =false;
		try {

			String selectQuery = " SELECT NVL(APPL_KEEP_INFO,'') FROM TMS_APPLICATION WHERE APPL_ID="
					+ trvlApp.getHiddenApplicationCode();

			Object selectDataObj[][] = getSqlModel().getSingleResult(
					selectQuery);
			String str = "";
			String query = "";
			if (selectDataObj != null && selectDataObj.length > 0) {
				str = String.valueOf(selectDataObj[0][0]);

				if (str.length() > 0) {
					query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
							+ " FROM HRMS_EMP_OFFC "
							+ "  WHERE  EMP_ID IN("
							+ str + ")";
				}
				Object result[][] = getSqlModel().getSingleResult(query);

				ArrayList <Object>list = new ArrayList<Object>();
				if (result != null && result.length > 0) {

					for (int i = 0; i < result.length; i++) {
						TravelApplication bean = new TravelApplication();
						bean.setKeepInformToCode(String.valueOf(result[i][1]));
						
						if(bean.getKeepInformToCode().equals(trvlApp.getUserEmpId()))
						{
							flag=true;
						}
						bean.setKeepInformToName(String.valueOf(result[i][0]));
						list.add(bean);
					}
					trvlApp.setKeepInformedList(list);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getKeepInformedSavedRecord----------"
					+ e);
		}
		return flag ;
	}

	/*
	 * public String sendForApprovalWithViolation(TravelApplication trvlApp,
	 * HttpServletRequest request){
	 * 
	 * try { logger.info("########## HIDDEN APP ID
	 * #############"+trvlApp.getHiddenApplicationCode());
	 * logger.info("########## VIOLATION REASON
	 * #############"+trvlApp.getViolationReason()); String
	 * updateAppicationStatusQuery = "UPDATE TMS_APPLICATION SET
	 * APPL_STATUS='P', APPL_VIOLATION_REASON= " +trvlApp.getViolationReason() + "
	 * WHERE APPL_ID= " +trvlApp.getHiddenApplicationCode(); String
	 * updateEmpDetailStatusQuery = "UPDATE TMS_APP_EMPDTL SET
	 * APPL_EMP_TRAVEL_APPLSTATUS='P'WHERE APPL_ID= "
	 * +trvlApp.getHiddenApplicationCode(); String updateGuestDetailStatusQuery =
	 * "UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='P' WHERE APPL_ID="
	 * +trvlApp.getHiddenApplicationCode();
	 * 
	 * getSqlModel().singleExecute(updateAppicationStatusQuery); } catch
	 * (Exception e) { e.printStackTrace();} return null; }
	 */

	public boolean deleteApplication(TravelApplication bean, String appCode) {

		boolean result = false;

		logger.info("@@@@@@@@@@ APPLICATION ID @@@@@@@@@@@@@@@" + appCode);
		try {

			String deleteEmployeeDetailsQuery = "DELETE FROM TMS_APP_EMPDTL WHERE APPL_ID="
					+ appCode;
			result = getSqlModel().singleExecute(deleteEmployeeDetailsQuery);
			if (result) {
				String deleteGuestDetailsQuery = "DELETE FROM TMS_APP_GUEST_DTL WHERE APPL_ID="
						+ appCode;
				result = getSqlModel().singleExecute(deleteGuestDetailsQuery);
			}
			if (result) {
				String deleteJourneyDetailsQuery = "DELETE FROM TMS_APP_JOURNEY_DTL WHERE APPL_ID="
						+ appCode;
				result = getSqlModel().singleExecute(deleteJourneyDetailsQuery);
			}
			if (result) {
				String deleteAccomodationDetailsQuery = "DELETE FROM TMS_APP_LODGE_DTL WHERE APPL_ID="
						+ appCode;
				result = getSqlModel().singleExecute(
						deleteAccomodationDetailsQuery);
			}
			if (result) {
				String deleteLocalConveyanceDetailsQuery = "DELETE FROM TMS_APP_CONV_DTL WHERE APPL_ID="
						+ appCode;
				result = getSqlModel().singleExecute(
						deleteLocalConveyanceDetailsQuery);
			}
			
			if(result){
				String deleteAppApprovalDtlQuery ="DELETE FROM TMS_APP_APPROVAL_DTL WHERE APPL_ID="
						+ appCode;
				result = getSqlModel().singleExecute(deleteAppApprovalDtlQuery);
			}
			if (result) {
				String deleteApplicationQuery = "DELETE FROM TMS_APPLICATION WHERE APPL_ID="
						+ appCode;
				result = getSqlModel().singleExecute(deleteApplicationQuery);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String isAdvanceAllowedForEmployee(String gradeId, String empName) {
		String advanceComment = "";
		try {
			String policyQuery = " SELECT TMS_TRAVEL_POLICY.POLICY_ID,POLICY_GRAD_ID,POLICY_ISADVANCE "
					+ " FROM TMS_TRAVEL_POLICY "
					+ " INNER JOIN  TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID) "
					+ " WHERE POLICY_GRAD_ID="
					+ gradeId
					+ " AND  POLICY_STATUS='A'";
			Object[][] advanceObj = getSqlModel().getSingleResult(policyQuery);
			if (String.valueOf(advanceObj[0][2]).equals("N")) {
				advanceComment = "Advance not allowed for " + empName + ".\n";
			} else {
				advanceComment = "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return advanceComment;
	}

	public boolean setApproverComments(TravelApplication travelbean) {

		boolean result = false;
		try {
			String approverCommentQuery = " SELECT EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' ')	,to_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') "
					+ "	,DECODE(TRIM(APPR_STATUS),'P','Pending for approval','C','Booking Completed','A','Approved','N','Draft','B','Sent Back','F','Revoked','R','Rejected') AS STATUS "
					+ "	,nvl(APPR_DTL_COMMENTS,'') "
					+ "	from TMS_APP_APPROVAL_DTL "
					+ "	 INNER JOIN HRMS_EMP_OFFC ON (TMS_APP_APPROVAL_DTL.APPR_APPROVER_ID= HRMS_EMP_OFFC.EMP_ID) "
					+ "	  WHERE APPL_ID="
					+ travelbean.getHiddenApplicationCode().trim();

			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList <Object> arrList = new ArrayList <Object>();
				for (int i = 0; i < approverCommentObj.length; i++) {
					TravelApplication bean = new TravelApplication();
					bean.setPrevApproverID(checkNull(String
							.valueOf(approverCommentObj[i][0])));
					bean.setPrevApproverName(checkNull(String
							.valueOf(approverCommentObj[i][1])));
					bean.setPrevApproverDate(checkNull(String
							.valueOf(approverCommentObj[i][2])));
					bean.setPrevApproverStatus(checkNull(String
							.valueOf(approverCommentObj[i][3])));
					bean.setPrevApproverComment(checkNull(String
							.valueOf(approverCommentObj[i][4])));
					/*
					 * String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					 * leaveBean.setAppSrNo(srNo);
					 */
					arrList.add(bean);

					result = true;
				}
				travelbean.setApproverCommentList(arrList);
				travelbean.setApproverListFlag(true);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public void generateReport(TravelApplication trvlApp,
			HttpServletResponse response) {

		ReportDataSet rds = new ReportDataSet();
		rds.setReportType("pdf");
		rds.setFileName("Travel Application Report ");
		rds.setReportName("Travel Application Report ");
		rds.setPageOrientation("landscape");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
				rds);

		String title = "Travel Application Report ";
		String subTitle = "";
		String divisionName = "", divisionAddress = "";

		String query = " SELECT EMP_TOKEN,TRIM(TRIM(NVL(EMP_FNAME,' '))||' '|| TRIM(NVL(EMP_MNAME,' '))||' '||TRIM(NVL(EMP_LNAME,' '))) "
				+" AS INITIATOR_NAME  ,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPLICATIONDATE ,"
				+" DECODE(TRIM(APPL_STATUS),'A','APPROVED','N','DRAFT','P','PENDING','R','REJECTED','C','BOOKING COMPLETED','F','REVOKED')AS STATUS ,"
				+" TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRAVELSTARTDATE, TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS  TRAVELENDDATE, "
				+" TOUR_TRAVEL_REQ_NAME AS REQNAME , NVL(PURPOSE_NAME,''),NVL(PROJECT_NAME,'') , NVL(TOUR_OTHER_PROJECT	,''), "
				+" NVL(TRAVEL_CUST_NAME,''),NVL(TOUR_OTHER_CUSTOMER,''),NVL(LOCATION_TYPE_NAME,'') , APPL_INITIATOR ,"
				+" ADMIN_COMMENTS ,NVL(cadre_name,'') FROM TMS_APPLICATION"
				+" INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
				+" INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID =TMS_APPLICATION.TOUR_TRAVEL_PURPOSE)  "
				+" LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID =TMS_APPLICATION.TOUR_PROJECT )  "
				+" LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER)  "
				+" INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID =TMS_APPLICATION.TOUR_TRAVEL_TYPE) "
				+" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+" WHERE APPL_ID=" + trvlApp.getHiddenApplicationCode();

		Object[][] reportObj = getSqlModel().getSingleResult(query);

		String divDetailsQury = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
				+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,'')  FROM HRMS_DIVISION "
				+ " where DIV_ID=(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ " " + String.valueOf(reportObj[0][13]) + ")";
		Object divisionDtl[][] = getSqlModel().getSingleResult(divDetailsQury);

		if (divisionDtl != null && divisionDtl.length > 0) {
			divisionName = String.valueOf(divisionDtl[0][0]);
			divisionAddress = String.valueOf(checkNull("" + divisionDtl[0][2]))
					+ "\n" + String.valueOf(checkNull("" + divisionDtl[0][3]))
					+ String.valueOf(checkNull("" + divisionDtl[0][4]));
		}
		Object[][] nameObj = new Object[1][1];
		boolean isLogo = false;
		try {
			String logoQuery = "select COMPANY_CODE,COMPANY_LOGO from HRMS_COMPANY";
			Object logoObj[][] = getSqlModel().getSingleResult(logoQuery);
			if (logoObj != null && logoObj.length > 0) {
				String filename = "";
				if (!String.valueOf(logoObj[0][1]).equals("")) {
					String clientUser = (String) session
							.getAttribute("session_pool");
					filename = String.valueOf(logoObj[0][1]);
					String filePath = context.getRealPath("/") + "pages/Logo/"
							+ session.getAttribute("session_pool") + "/"
							+ filename;
					System.out.println("-------------------  " + filePath);
					nameObj[0][0] = Image.getInstance(filePath);
					isLogo = true;
				} else
					nameObj[0][0] = "";
			} else {
				nameObj[0][0] = "";
			}
		} catch (Exception eee) {
			nameObj[0][0] = " ";
		}

		TableDataSet logoData = new TableDataSet();
		logoData.setData(nameObj);
		logoData.setCellAlignment(new int[] { 0 });
		logoData.setCellWidth(new int[] { 100 });
		logoData.setBorder(false);
		logoData.setBlankRowsBelow(1);
		// logoData.setHeaderTable(true);

		Object[][] titleObj = new Object[1][1];
		titleObj[0][0] = "" + divisionName;

		TableDataSet titleName = new TableDataSet();
		titleName.setData(titleObj);
		titleName.setCellAlignment(new int[] { 1 });
		titleName.setCellWidth(new int[] { 100 });
		titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD, new Color(
				0, 0, 0));
		titleName.setBorder(false);

		Object[][] subtitleObj = new Object[1][1];
		subtitleObj[0][0] = "" + divisionAddress;

		TableDataSet subtitleName = new TableDataSet();
		subtitleName.setData(subtitleObj);
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
				new Color(0, 0, 0));
		subtitleName.setBorder(false);

		Object[][] subtitleObj2 = new Object[1][1];
		subtitleObj2[0][0] = "" + title + " " + subTitle;

		TableDataSet subtitleName2 = new TableDataSet();
		subtitleName2.setData(subtitleObj2);
		subtitleName2.setCellAlignment(new int[] { 1 });
		subtitleName2.setCellWidth(new int[] { 100 });
		subtitleName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
				new Color(0, 0, 0));
		subtitleName2.setBorder(false);
		subtitleName2.setBlankRowsBelow(1);

		HashMap<String, Object> mapOne = rg.joinTableDataSet(titleName,
				subtitleName, false, 0);
		HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,
				subtitleName2, false, 0);
		HashMap<String, Object> mapThree = null;
		if (isLogo)
			mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 30);
		else
			mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 5);
		// rg.addTableToDoc(mapThree);
		rg.createHeader(mapThree);

		String selQuery = "SELECT DECODE(APPL_MANAGE_JOURNEY,'S','Self Managed','C','Company Managed'),DECODE(APPL_MANAGE_ACCOMODATION,'S','Self Managed','C','Company Managed'),DECODE(APPL_MANAGE_LOCAL_CONV,'S','Self Managed','C','Company Managed','F','Revoked') FROM TMS_APPLICATION WHERE APPL_ID="
				+ trvlApp.getHiddenApplicationCode();
		Object[][] empDetails = getSqlModel().getSingleResult(selQuery);

		Object data[][] = new Object[7][4];

		data[0][0] = "Initiator Name";
		data[0][1] = String.valueOf(reportObj[0][0]) + " - "
				+ String.valueOf(reportObj[0][1]);
		data[0][2] = "Initiator Grade";
		data[0][3] = String.valueOf(reportObj[0][15]);

		data[1][0] = "Application Date ";
		data[1][1] = String.valueOf(reportObj[0][2]);
		data[1][2] = "	Application Status ";
		data[1][3] = String.valueOf(reportObj[0][3]);

		data[2][0] = "Travel start date  ";
		data[2][1] = String.valueOf(reportObj[0][4]);
		data[2][2] = "	Travel end date ";
		data[2][3] = String.valueOf(reportObj[0][5]);

		data[3][0] = "Travel Request Name ";
		data[3][1] = String.valueOf(reportObj[0][6]);
		data[3][2] = "Travel Purpose ";
		data[3][3] = String.valueOf(reportObj[0][7]);

		data[4][0] = "Project ";
		data[4][1] = String.valueOf(reportObj[0][8]);
		data[4][2] = "	If other project, please specify ";
		data[4][3] = String.valueOf(reportObj[0][9]);

		data[5][0] = "Customer ";
		data[5][1] = String.valueOf(reportObj[0][10]);
		data[5][2] = "	If other customer, please specify ";
		data[5][3] = String.valueOf(reportObj[0][11]);

		data[6][0] = "Travel Type ";
		data[6][1] = String.valueOf(reportObj[0][12]);

		int[] cellwidth = { 25, 25, 25, 25 };
		int[] alignment = { 0, 0, 0, 0 };

		TableDataSet tdstable = new TableDataSet();
		tdstable.setData(data);
		tdstable.setCellAlignment(alignment);
		tdstable.setCellWidth(cellwidth);
		tdstable.setBorder(true);
		rg.addTableToDoc(tdstable);

		// FOR EMPLOYEE INFORMATION

		String selQueryEmployee = "SELECT EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||EMP_LNAME, APPL_EMP_AGE, APPL_EMP_CONTACT,"
				+ " CASE WHEN APPL_APPROVED_ADVANCE_AMOUNT = 0  THEN NVL(APPL_EMP_ADVANCE_AMT,0) ELSE NVL(APPL_APPROVED_ADVANCE_AMOUNT,0) END AS ADVANCE_AMOUNT, TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'),TO_CHAR(cadre_name), TO_CHAR(APPL_CURRENCY) "
				+ " FROM  TMS_APP_EMPDTL"
				+ " INNER JOIN HRMS_EMP_OFFC ON(TMS_APP_EMPDTL.APPL_EMP_CODE = HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+ " WHERE APPL_ID="
				+ trvlApp.getHiddenApplicationCode()
				+ ""
				+ " UNION"
				+ " SELECT GUEST_NAME, GUEST_AGE, GUEST_CONTACT, GUEST_ADVANCE_AMT , '' , '' , '' "
				+ " FROM TMS_APP_GUEST_DTL"
				+ " WHERE APPL_ID= "
				+ trvlApp.getHiddenApplicationCode();

		Object[][] empDetailsEmployee = getSqlModel().getSingleResult(
				selQueryEmployee);

		TableDataSet headerTableEmployee = new TableDataSet();
		headerTableEmployee
				.setData(new Object[][] { { "EMPLOYEE INFORMATION" } });
		headerTableEmployee.setCellAlignment(new int[] { 0 });
		headerTableEmployee.setCellWidth(new int[] { 100 });
		headerTableEmployee.setBlankRowsAbove(1);
		headerTableEmployee.setBorder(false);
		rg.addTableToDoc(headerTableEmployee);

		String[] headerEmployee = new String[7];
		headerEmployee[0] = "SR.NO";
		headerEmployee[1] = "EMPLOYEE/GUEST NAME";
		headerEmployee[2] = "AGE";
		headerEmployee[3] = "GRADE";
		headerEmployee[4] = "CONTACT NUMBER";
		headerEmployee[5] = "DATE OF BIRTH";
		headerEmployee[6] = "ADVANCE AMOUNT";
		
		double amtTotal=0;
		String currency="";

		if (empDetailsEmployee != null && empDetailsEmployee.length > 0) {
			Object[][] obj = new Object[empDetailsEmployee.length][7];

			for (int i = 0; i < obj.length; i++) {
				obj[i][0] = i + 1;
				obj[i][1] = empDetailsEmployee[i][0];
				obj[i][2] = empDetailsEmployee[i][1];
				obj[i][3] = empDetailsEmployee[i][5];

				obj[i][4] = empDetailsEmployee[i][2];

				obj[i][5] = empDetailsEmployee[i][4];
				obj[i][6] = String.valueOf(empDetailsEmployee[i][3]).concat(String.valueOf(" "+checkNull(String.valueOf(empDetailsEmployee[i][6]))));
				amtTotal+=Double.parseDouble(String.valueOf(empDetailsEmployee[i][3])); 
				currency=checkNull(String.valueOf(empDetailsEmployee[i][6]));
			}

			Object[][] totAmt = new Object[1][7];
			totAmt[0][0]="";
			totAmt[0][1]="";
			totAmt[0][2]="";
			totAmt[0][3]="";
			totAmt[0][4]="";
			totAmt[0][5]="";
			totAmt[0][6]= "Total : "+String.valueOf(amtTotal).concat(" "+currency);
			
			TableDataSet finalTable = new TableDataSet();
			Object[][] finalObject= Utility.joinArrays(obj, totAmt,1,0);
			
			finalTable.setHeader(headerEmployee);
			finalTable.setCellAlignment(new int[] { 1, 1, 1, 1, 1, 2, 1 });
			finalTable.setCellWidth(new int[] { 10, 20, 20, 20, 20, 20, 20 });
			finalTable.setHeaderFontDetails(Font.HELVETICA, 8, Font.BOLD,new Color(0, 0, 0));
			finalTable.setHeaderBGColor(new Color(200, 200, 200));
			finalTable.setBorder(true);
			finalTable.setHeaderTable(false);
			
			finalTable.setData(finalObject);
			rg.addTableToDoc(finalTable);
			
		} else {
			TableDataSet noDataEmployee = new TableDataSet();
			noDataEmployee.setData(new Object[][] { { "No data to display" } });
			noDataEmployee.setCellAlignment(new int[] { 1 });
			noDataEmployee.setCellWidth(new int[] { 100 });
			rg.addTableToDoc(noDataEmployee);
		}

		// FOR JOURNEY DETAILS
		String selQueryJourney = "SELECT  JOURNEY_FROM, JOURNEY_TO, NVL(JOURNEY_NAME||'-'||CLASS_NAME,' '), TO_CHAR(JOURNEY_DATE,'DD-MM-YYYY'), JOURNEY_TIME"
				+ " FROM TMS_APP_JOURNEY_DTL"
				+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID=TMS_APP_JOURNEY_DTL.JOURNEY_MODECLASS) "
				+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) "
				+ " WHERE APPL_ID= "
				+ trvlApp.getHiddenApplicationCode();

		Object[][] empDetailsJourney = getSqlModel().getSingleResult(
				selQueryJourney);

		TableDataSet headerTableJourney = new TableDataSet();
		headerTableJourney.setData(new Object[][] { { "JOURNEY DETAILS :"
				+ empDetails[0][0] } });
		headerTableJourney.setCellAlignment(new int[] { 0 });
		headerTableJourney.setCellWidth(new int[] { 100 });
		headerTableJourney.setBlankRowsAbove(1);
		headerTableJourney.setBorder(false);
		rg.addTableToDoc(headerTableJourney);

		String[] headerJourney = new String[5];
		headerJourney[0] = "FROM PLACE";
		headerJourney[1] = "TO PLACE";
		headerJourney[2] = "JOURNEY MODE-CLASS";
		headerJourney[3] = "JOURNEY DATE";
		headerJourney[4] = "TIME";

		if (empDetailsJourney != null && empDetailsJourney.length > 0) {
			TableDataSet journey = new TableDataSet();
			journey.setHeader(headerJourney);
			journey.setData(empDetailsJourney);
			journey.setCellAlignment(new int[] { 1, 1, 1, 1, 1 });
			journey.setCellWidth(new int[] { 10, 20, 20, 20, 20 });
			journey.setHeaderFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));
			journey.setHeaderBGColor(new Color(200, 200, 200));
			journey.setBorder(true);
			journey.setHeaderTable(false);
			rg.addTableToDoc(journey);
		} else {
			TableDataSet noDataJourney = new TableDataSet();
			noDataJourney.setData(new Object[][] { { "No data to display" } });
			noDataJourney.setCellAlignment(new int[] { 1 });
			noDataJourney.setCellWidth(new int[] { 100 });
			rg.addTableToDoc(noDataJourney);
		}

		// Lodging Details
		String selQueryLodging = "SELECT   HOTEL_TYPE_NAME,ROOM_TYPE_NAME, LODGE_CITY, LODGE_PRE_LOCATION, TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY'), LODGE_FROMTIME, TO_CHAR(LODGE_TODATE,'DD-MM-YYYY') , LODGE_TOTIME "
				+ " FROM TMS_APP_LODGE_DTL "
				+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_HOTELTYPE )"
				+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON (HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_ROOMTYPE ) "
				+ " WHERE APPL_ID= " + trvlApp.getHiddenApplicationCode();

		Object[][] empDetailsLodging = getSqlModel().getSingleResult(
				selQueryLodging);

		TableDataSet headerTableLodging = new TableDataSet();
		headerTableLodging.setData(new Object[][] { { "LODGING DETAILS :"
				+ empDetails[0][1] } });
		headerTableLodging.setCellAlignment(new int[] { 0 });
		headerTableLodging.setCellWidth(new int[] { 100 });
		headerTableLodging.setBlankRowsAbove(1);
		headerTableLodging.setBorder(false);
		rg.addTableToDoc(headerTableLodging);

		String[] headerLodging = new String[8];
		headerLodging[0] = "HOTEL TYPE ";
		headerLodging[1] = "ROOM TYPE";
		headerLodging[2] = "CITY ";
		headerLodging[3] = "PREFFERED LOCATION";
		headerLodging[4] = "FROM DATE ";
		headerLodging[5] = "FROM TIME(HH24:MI)";
		headerLodging[6] = "TO DATE ";
		headerLodging[7] = "TO TIME(HH24:MI)";

		if (empDetailsLodging != null && empDetailsLodging.length > 0) {
			TableDataSet Lodging = new TableDataSet();
			Lodging.setHeader(headerLodging);
			Lodging.setData(empDetailsLodging);
			Lodging.setCellAlignment(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 });
			Lodging.setCellWidth(new int[] { 10, 20, 20, 20, 20, 15, 15, 15 });
			Lodging.setHeaderFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));
			Lodging.setHeaderBGColor(new Color(200, 200, 200));
			Lodging.setBorder(true);
			Lodging.setHeaderTable(false);
			rg.addTableToDoc(Lodging);
		}
		// Local Conveyance Details
		String selQueryLocal = "SELECT  CONV_CITY, CONV_TRAVELDTL, CONV_MEDIUM, TO_CHAR(CONV_FROMDATE,'DD-MM-YYYY'), CONV_FROMTIME, TO_CHAR(CONV_TODATE,'DD-MM-YYYY'), CONV_TOTIME "
				+ " FROM TMS_APP_CONV_DTL WHERE APPL_ID="
				+ trvlApp.getHiddenApplicationCode();

		Object[][] empDetailsLocal = getSqlModel().getSingleResult(
				selQueryLocal);

		TableDataSet headerTableLocal = new TableDataSet();
		headerTableLocal
				.setData(new Object[][] { { "LOCAL CONVEYANCE DETAILS: "
						+ empDetails[0][2] } });
		headerTableLocal.setCellAlignment(new int[] { 0 });
		headerTableLocal.setCellWidth(new int[] { 100 });
		headerTableLocal.setBlankRowsAbove(1);
		headerTableLocal.setBorder(false);
		rg.addTableToDoc(headerTableLocal);

		String[] headerLocal = new String[7];
		headerLocal[0] = "CITY ";
		headerLocal[1] = "TRAVEL DETAILS ";
		headerLocal[2] = "MEDIUM";
		headerLocal[3] = "FROM DATE ";
		headerLocal[4] = "FROM TIME";
		headerLocal[5] = "TO DATE ";
		headerLocal[6] = "TO TIME";

		if (empDetailsLocal != null && empDetailsLocal.length > 0) {
			TableDataSet Lodging = new TableDataSet();
			Lodging.setHeader(headerLocal);
			Lodging.setData(empDetailsLocal);
			Lodging.setCellAlignment(new int[] { 1, 1, 1, 1, 1, 1, 1 });
			Lodging.setCellWidth(new int[] { 10, 20, 20, 20, 20, 15, 15 });
			Lodging.setHeaderFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));
			Lodging.setHeaderBGColor(new Color(200, 200, 200));
			Lodging.setBorder(true);
			Lodging.setHeaderTable(false);
			rg.addTableToDoc(Lodging);

		}

		String queryComments = "SELECT NVL(APPL_APPLCANT_COMMENTS,' ') FROM TMS_APPLICATION WHERE APPL_ID="
				+ trvlApp.getHiddenApplicationCode();
		Object[][] Comments = getSqlModel().getSingleResult(queryComments);

		TableDataSet header = new TableDataSet();
		header.setData(new Object[][] { { "APPLICANT COMMENTS :"
				+ Utility.checkNull(String.valueOf(Comments[0][0])) } });
		header.setCellAlignment(new int[] { 0 });
		header.setCellWidth(new int[] { 100 });
		header.setBlankRowsAbove(1);
		header.setBlankRowsBelow(1);
		header.setBorder(false);
		rg.addTableToDoc(header);

		TableDataSet headerTableApprover = new TableDataSet();
		headerTableApprover
				.setData(new Object[][] { { "APPROVER DETAILS :" } });
		headerTableApprover.setCellAlignment(new int[] { 0 });
		headerTableApprover.setCellWidth(new int[] { 100 });
		headerTableApprover.setBlankRowsAbove(1);
		headerTableApprover.setBorder(false);
		rg.addTableToDoc(headerTableApprover);

		String[] headerApprover = new String[6];
		headerApprover[0] = "SR NO.";
		headerApprover[1] = "APPROVER ID";
		headerApprover[2] = "APPROVER NAME";
		headerApprover[3] = "DATE";
		headerApprover[4] = "STATUS";
		headerApprover[5] = "COMMENTS";

		String approverQuery = "SELECT rownum as srno,EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' ')	,to_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') 	,DECODE(TRIM(APPR_STATUS),'P','Pending for approval','C','Booking Completed','A','Approved','N','Draft','B','Sent Back','F','Revoked','R','Rejected') AS STATUS 	,nvl(APPR_DTL_COMMENTS,'') 	from TMS_APP_APPROVAL_DTL 	 INNER JOIN HRMS_EMP_OFFC ON (TMS_APP_APPROVAL_DTL.APPR_APPROVER_ID= HRMS_EMP_OFFC.EMP_ID) 	  WHERE APPL_ID="
				+ trvlApp.getHiddenApplicationCode();
		Object[][] empApproverDetails = getSqlModel().getSingleResult(
				approverQuery);

		if (empApproverDetails != null && empApproverDetails.length > 0) {
			TableDataSet approver = new TableDataSet();
			approver.setHeader(headerApprover);
			approver.setData(empApproverDetails);
			approver.setCellAlignment(new int[] { 1, 1, 1, 1, 1, 1 });
			approver.setCellWidth(new int[] { 5, 10, 20, 15, 20, 20 });
			approver.setHeaderFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));
			approver.setHeaderBGColor(new Color(200, 200, 200));
			approver.setBorder(true);
			approver.setHeaderTable(false);
			rg.addTableToDoc(approver);
		} else {
			TableDataSet noDataJourney = new TableDataSet();
			noDataJourney.setData(new Object[][] { { "No data to display" } });
			noDataJourney.setCellAlignment(new int[] { 1 });
			noDataJourney.setCellWidth(new int[] { 100 });
			rg.addTableToDoc(noDataJourney);
		}

		rg.process();
		rg.createReport(response);

	}

	public boolean revoke(TravelApplication trvlApp) {
		boolean result = false;

		try {
			String updateQuery = " UPDATE TMS_APPLICATION SET APPL_STATUS='F',REVOKE_DATE=TO_DATE(SYSDATE) WHERE APPL_ID="
					+ trvlApp.getHiddenApplicationCode().trim();
			result = getSqlModel().singleExecute(updateQuery);
			String sqlQuery = "	SELECT APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID="
					+ trvlApp.getHiddenApplicationCode().trim();
			Object statausobj[][] = getSqlModel().getSingleResult(sqlQuery);
			if (statausobj != null && statausobj.length > 0) {

				if (result) {
					if (!(String.valueOf(statausobj[0][0]).equals("P"))
							|| !(String.valueOf(statausobj[0][0]).equals("N"))) {
						updateAttendanceData(trvlApp.getHiddenApplicationCode()
								.trim());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean updateAttendanceData(String travelAppNo) {
		boolean result = false;
		try {
			String sqlQuery = "	SELECT TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') ,TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),APPL_INITIATOR AS EMP_ID "
					+ " FROM  TMS_APPLICATION "
					+ " WHERE APPL_ID="
					+ travelAppNo;
			Object[][] selectobj = getSqlModel().getSingleResult(sqlQuery);
			String[][] splitObj = null;
			if (selectobj != null && selectobj.length > 0) {
				String shiftQuery = " select EMP_SHIFT from hrms_emp_offc where emp_id="
						+ selectobj[0][2];
				Object[][] shiftObj = getSqlModel().getSingleResult(shiftQuery);

				String dateQuery = "  SELECT TO_DATE('"
						+ String.valueOf(selectobj[0][1])
						+ "','DD-MM-YYYY')-TO_DATE('"
						+ String.valueOf(selectobj[0][0])
						+ "','DD-MM-YYYY') FROM DUAL ";

				Object dateQueryObj[][] = getSqlModel().getSingleResult(
						dateQuery);
				int looplength = 0;
				if (dateQueryObj != null && dateQueryObj.length > 0) {
					looplength = Integer.parseInt(String
							.valueOf(dateQueryObj[0][0]));
				}

				splitObj = DateUtility.splitDatesObj(String
						.valueOf(selectobj[0][0]), looplength + 1);

				if (splitObj != null && splitObj.length > 0) {

					for (int i = 0; i < splitObj.length; i++) {

						String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
								+ splitObj[i][0].substring(6, 10)
								+ " where ATT_DATE=to_date('"
								+ splitObj[i][0]
								+ "','dd-mm-yyyy') and ATT_EMP_ID='"
								+ selectobj[0][2] + " ' ";

						Object[][] selectObj = getSqlModel().getSingleResult(
								selectQry);

						if (selectObj != null && selectObj.length > 0) {
							String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ splitObj[i][0].substring(6, 10)
									+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='AB'"
									+ " WHERE ATT_DATE=to_date ( '"
									+ splitObj[i][0]
									+ "','dd-mm-yyyy')"
									+ " and ATT_EMP_ID=" + selectobj[0][2];

							getSqlModel().singleExecute(
									myCardTimeActualDataUpdate);
						}

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String isDatePolicyViolatedForEmployee(TravelApplication bean,
			String gradeId, String empName) {
		/* Application date violation chk */
		String datePolicyViolation = "";
		Object chkDateObj[][] = null;
		Object minDaysDataObj[][] = null;

		String getMinDayQuery = " SELECT NVL(MIN_ADVANCE_DAYS_FOR_TRAVEL,0) FROM TMS_TRAVEL_POLICY "
				+ " INNER JOIN TMS_POLICY_MAP_DTL ON(TMS_POLICY_MAP_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID) "
				+ " INNER JOIN TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_POLICY_MAP_DTL.POLICY_ID AND POLICY_GRAD_ID=?)"
				+ " WHERE  POLICY_STATUS='A' AND TO_DATE(?,'DD-MM-YYYY')>=POLICY_EFFECTIVE_DATE ";

		minDaysDataObj = getSqlModel().getSingleResult(getMinDayQuery,
				new Object[] { gradeId, bean.getAppDate() });

		String chkminDateQuery = "SELECT TO_DATE('" + bean.getStartDate()
				+ "','DD-MM-YYYY')-TO_DATE('" + bean.getAppDate()
				+ "','DD-MM-YYYY')FROM DUAL";
		chkDateObj = getSqlModel().getSingleResult(chkminDateQuery);

		String minDaysData = (minDaysDataObj != null && minDaysDataObj.length > 0) ? String
				.valueOf(minDaysDataObj[0][0])
				: "0";
		if (Integer.parseInt(minDaysData) != 0) {
			if (Integer.parseInt(String.valueOf(chkDateObj[0][0])) <= Integer
					.parseInt(String.valueOf(minDaysDataObj[0][0]))) {

				/*
				 * datePolicyViolation = "Application Date Deviation For " +
				 * empName + " - "; datePolicyViolation += "\n Start date
				 * selected is " + bean.getStartDate() + " Application date must
				 * be " + String.valueOf(minDaysDataObj[0][0]) + " days prior to
				 * travel start date. \n";
				 */

				datePolicyViolation = "Please apply minimum "
						+ String.valueOf(minDaysDataObj[0][0])
						+ " days in advance for travel application."
						+ "\nThis will help us to serve you better. ";
			}
		}
		return datePolicyViolation;
	}

	public boolean isApplicationUnderProcess(String travelAppNo) {
		boolean result = false;
		System.out.println("inside isApplicationUnderProcess--travelAppNo-"
				+ travelAppNo);
		try {
			String sqlQuery = "	SELECT TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') ,TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),APPL_INITIATOR AS EMP_ID "
					+ " FROM  TMS_APPLICATION "
					+ " WHERE APPL_ID="
					+ travelAppNo;

			Object[][] selectobj = getSqlModel().getSingleResult(sqlQuery);

			String[][] splitObj = null;

			if (selectobj != null && selectobj.length > 0) {
				String shiftQuery = " select EMP_SHIFT from hrms_emp_offc where emp_id="
						+ selectobj[0][2];
				Object[][] shiftObj = getSqlModel().getSingleResult(shiftQuery);

				String dateQuery = "  SELECT TO_DATE('"
						+ String.valueOf(selectobj[0][1])
						+ "','DD-MM-YYYY')-TO_DATE('"
						+ String.valueOf(selectobj[0][0])
						+ "','DD-MM-YYYY') FROM DUAL ";

				Object dateQueryObj[][] = getSqlModel().getSingleResult(
						dateQuery);
				int looplength = 0;
				if (dateQueryObj != null && dateQueryObj.length > 0) {
					looplength = Integer.parseInt(String
							.valueOf(dateQueryObj[0][0]));
				}

				splitObj = DateUtility.splitDatesObj(String
						.valueOf(selectobj[0][0]), looplength + 1);

				if (splitObj != null && splitObj.length > 0) {

					for (int i = 0; i < splitObj.length; i++) {

						String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
								+ splitObj[i][0].substring(6, 10)
								+ " where ATT_DATE=to_date('"
								+ splitObj[i][0]
								+ "','dd-mm-yyyy') and ATT_EMP_ID='"
								+ selectobj[0][2] + " ' ";

						Object[][] selectObj = getSqlModel().getSingleResult(
								selectQry);

						if (selectObj != null && selectObj.length > 0) {
							String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ splitObj[i][0].substring(6, 10)
									+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,IS_APPL_PROCESS='Y'"
									+ " WHERE ATT_DATE=to_date ( '"
									+ splitObj[i][0]
									+ "','dd-mm-yyyy')"
									+ " and ATT_EMP_ID=" + selectobj[0][2];

							result =getSqlModel().singleExecute(
									myCardTimeActualDataUpdate);
						} else {
							String actualDataInsertInAtt = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
									+ splitObj[i][0].substring(6, 10)
									+ "(ATT_DATE, ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_REG_STATUS_ONE, IS_APPL_PROCESS,ATT_SHIFT_ID) "
									+ "VALUES(to_date('"
									+ splitObj[i][0]
									+ "','dd-mm-yyyy'),"
									+ selectobj[0][2]
									+ ",'AB','AB','AB','Y',"
									+ shiftObj[0][0]
									+ ")";

							result =getSqlModel().singleExecute(actualDataInsertInAtt);
						}
						 
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
