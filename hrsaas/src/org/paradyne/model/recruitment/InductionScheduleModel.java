package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.InductionSchedule;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author varunK
 * 
 */
public class InductionScheduleModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InductionScheduleModel.class);

	public void getDueList(InductionSchedule indSchedule,
			HttpServletRequest request) {
		Object[][] dueData = null;
		ArrayList<Object> tableList = new ArrayList<Object>();
			try {
				String query = "SELECT EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_DIV,DIV_NAME,EMP_CENTER, "
						+ " CENTER_NAME,EMP_DEPT,DEPT_NAME,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') "
						+ " FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+ " WHERE EMP_STATUS = 'S' AND EMP_IND_STATUS = 'N' ";
				if (indSchedule.getUserProfileDivision() != null
						&& !indSchedule.getUserProfileDivision().equals("")) {
					query += " AND EMP_DIV IN ("
							+ indSchedule.getUserProfileDivision() + ")";
				}
				query += " ORDER BY  EMP_DIV";
				dueData = getSqlModel().getSingleResult(query);
				String[] pageIndex = Utility.doPaging(indSchedule.getMyPage(),
						dueData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1")) {
					indSchedule.setMyPage("1");
				}
				if (dueData != null && dueData.length > 0) {
					indSchedule.setTotalRecords("" + dueData.length);
					indSchedule.setModeLength("true");
					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						InductionSchedule bean = new InductionSchedule();
						bean.setEmployeeCode(String.valueOf(dueData[i][0]));
						bean.setEmployeeName(String.valueOf(dueData[i][1]));
						bean.setDivisionCode(String.valueOf(dueData[i][2]));
						bean.setDivisionName(checkNull(String
								.valueOf(dueData[i][3])));
						bean.setBranchCode(String.valueOf(dueData[i][4]));
						bean.setBranchName(checkNull(String
								.valueOf(dueData[i][5])));
						bean.setDeptCode(String.valueOf(dueData[i][6]));
						bean.setDeptName(checkNull(String
								.valueOf(dueData[i][7])));
						bean.setDateOfJoining(checkNull(String
								.valueOf(dueData[i][8])));
						tableList.add(bean);
					}
					indSchedule.setEmployeeList(tableList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} 
		else {
			return result;
		}
	}

	public void addActivity(InductionSchedule indSchedule,
			HttpServletRequest request) {
		try {
			ArrayList<Object> actList = new ArrayList<Object>();
			String[] actListLen = request.getParameterValues("actListDate");
			String[] actStartTime = request.getParameterValues("actListStartTime");
			String[] actEndTime = request.getParameterValues("actListEndTime");
			String[] actDetails = request.getParameterValues("actListDetails");
			String[] actOwner = request.getParameterValues("actListOwner");
			String[] actOwnerType = request.getParameterValues("ownerType");
			String[] designation = request.getParameterValues("designation");
			String[] actVenueDetails = request.getParameterValues("actVenueDetails");
			String[] actListOwnerIDIerator = request.getParameterValues("actListOwnerIDIerator");
			if (actListLen != null) {
				for (int i = 0; i < actListLen.length; i++) {
					InductionSchedule bean = new InductionSchedule();
					bean.setActListDate(actListLen[i]);
					bean.setActListStartTime(actStartTime[i]);
					bean.setActListEndTime(actEndTime[i]);
					bean.setActListDetails(actDetails[i]);
					bean.setActListOwner(actOwner[i]);
					bean.setOwnerType(actOwnerType[i]);
					bean.setDesignation(designation[i]);
					bean.setActVenueDetails(actVenueDetails[i]);
					bean.setActListOwnerIDIerator(actListOwnerIDIerator[i]);
					actList.add(bean);
				}
			}
			InductionSchedule bean = new InductionSchedule();
			bean.setActListDate(indSchedule.getActDate());
			bean.setActListStartTime(indSchedule.getActStartTime());
			bean.setActListEndTime(indSchedule.getActEndTime());
			bean.setActListDetails(indSchedule.getActDetails());
			bean.setActVenueDetails(indSchedule.getActVenue());
			bean.setOwnerType(indSchedule.getOwnerIntOrExt());
			if (indSchedule.getOwnerIntOrExt().equals("internal")) {
				bean.setActListOwner(indSchedule.getOwnerNameInt());
				bean.setDesignation(indSchedule.getDesignationInt());
				bean.setActListOwnerIDIerator(indSchedule.getOwnerIdInt());
			} else {
				bean.setActListOwner(indSchedule.getOwnerNameExt());
				bean.setDesignation(indSchedule.getDesignationExt());
				bean.setActListOwnerIDIerator("");
			}
			if (indSchedule.getRowId().equals("")
					|| indSchedule.getRowId().equals("null")
					|| indSchedule.getRowId().equals(null)) {
				actList.add(bean);
			} else {
				actList.remove(Integer.parseInt(indSchedule.getRowId()) - 1);
				actList.add(Integer.parseInt(indSchedule.getRowId()) - 1, bean);
			}
			indSchedule.setActivityList(actList);
			getEmployeeList(indSchedule, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getEmployeeList(InductionSchedule indSchedule,
			HttpServletRequest request) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String[] empName = request.getParameterValues("empName");
			String[] empCode = request.getParameterValues("empCode");
			String[] divName = request.getParameterValues("divName");
			String[] divCode = request.getParameterValues("divCode");
			String[] brName = request.getParameterValues("brName");
			String[] brCode = request.getParameterValues("brCode");
			String[] deptListName = request.getParameterValues("deptListName");
			String[] deptListCode = request.getParameterValues("deptListCode");
			String[] dtOfJoinList = request.getParameterValues("dtOfJoinList");
			String[] feedbackStatus = request.getParameterValues("feedbackStatus");
			String[] givenFeedbackStatus = request.getParameterValues("givenFeedbackStatus");
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					InductionSchedule bean = new InductionSchedule();
					bean.setEmpCode(empCode[i]);
					bean.setEmpName(empName[i]);
					bean.setDivCode(divCode[i]);
					bean.setDivName(checkNull(divName[i]));
					bean.setBrCode(brCode[i]);
					bean.setBrName(checkNull(brName[i]));
					bean.setDeptListCode(deptListCode[i]);
					bean.setDeptListName(checkNull(deptListName[i]));
					bean.setDtOfJoinList(checkNull(dtOfJoinList[i]));
					bean.setFeedbackStatus(checkNull(feedbackStatus[i]));
					bean.setGivenFeedbackStatus(checkNull(givenFeedbackStatus[i]));
					tableList.add(bean);
				}
				indSchedule.setParticipantList(tableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteActivity(InductionSchedule indSchedule,
			HttpServletRequest request) {
		boolean result = false;
		try {
			ArrayList<Object> actList = new ArrayList<Object>();
			String[] actListLen = request.getParameterValues("actListDate");
			String[] actStartTime = request
					.getParameterValues("actListStartTime");
			String[] actEndTime = request.getParameterValues("actListEndTime");
			String[] actDetails = request.getParameterValues("actListDetails");
			String[] actOwner = request.getParameterValues("actListOwner");
			String[] actOwnerType = request.getParameterValues("ownerType");
			String[] designation = request.getParameterValues("designation");
			String[] actVenueDetails = request
					.getParameterValues("actVenueDetails");
			String[] actListOwnerIDIerator = request.getParameterValues("actListOwnerIDIerator");
			if (actListLen != null) {
				for (int i = 0; i < actListLen.length; i++) {
					InductionSchedule bean = new InductionSchedule();
					bean.setActListDate(actListLen[i]);
					bean.setActListStartTime(actStartTime[i]);
					bean.setActListEndTime(actEndTime[i]);
					bean.setActListDetails(actDetails[i]);
					bean.setActListOwner(actOwner[i]);
					bean.setOwnerType(actOwnerType[i]);
					bean.setDesignation(designation[i]);
					bean.setActVenueDetails(actVenueDetails[i]);
					bean.setActListOwnerIDIerator(actListOwnerIDIerator[i]);
					actList.add(bean);
					result = true;
				}
				actList.remove(Integer.parseInt(indSchedule.getRowId()) - 1);
				indSchedule.setActivityList(actList);
			}
			getEmployeeList(indSchedule, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteEmployee(InductionSchedule indSchedule,
			HttpServletRequest request) {
		boolean result = false;
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String[] empName = request.getParameterValues("empName");
			String[] empCode = request.getParameterValues("empCode");
			String[] divName = request.getParameterValues("divName");
			String[] divCode = request.getParameterValues("divCode");
			String[] brName = request.getParameterValues("brName");
			String[] brCode = request.getParameterValues("brCode");
			String[] deptListName = request.getParameterValues("deptListName");
			String[] deptListCode = request.getParameterValues("deptListCode");
			String[] dtOfJoinList = request.getParameterValues("dtOfJoinList");
			String[] checkBox = request.getParameterValues("checkBoxEmpList");
			String[] feedbackStatus = request.getParameterValues("feedbackStatus");
			String[] givenFeedbackStatus = request.getParameterValues("givenFeedbackStatus");
			
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					InductionSchedule bean = new InductionSchedule();
					if (!(String.valueOf(checkBox[i]).equals("Y"))) {
						bean.setEmpCode(empCode[i]);
						bean.setEmpName(empName[i]);
						bean.setDivCode(divCode[i]);
						bean.setDivName(divName[i]);
						bean.setBrCode(brCode[i]);
						bean.setBrName(brName[i]);
						bean.setDeptListCode(deptListCode[i]);
						bean.setDeptListName(deptListName[i]);
						bean.setDtOfJoinList(checkNull(dtOfJoinList[i]));
						
						bean.setFeedbackStatus(checkNull(feedbackStatus[i]));
						bean.setGivenFeedbackStatus(checkNull(givenFeedbackStatus[i]));
						
						tableList.add(bean);
					} else {
						if (!(indSchedule.getInductionCode().equals("")
								|| indSchedule.getInductionCode()
										.equals("null") || indSchedule
								.getInductionCode().equals(null))) {

							/*
							 * String delEmpDtl = " DELETE FROM
							 * HRMS_REC_INDUC_PARTI WHERE INDUC_CODE =
							 * "+indSchedule.getInductionCode()+" " + " AND
							 * INDUC_EMPID="+empCode[i]+""; boolean resultDtl =
							 * getSqlModel().singleExecute(delEmpDtl);
							 * 
							 * String updOffc = "UPDATE HRMS_EMP_OFFC SET
							 * EMP_IND_STATUS = 'N' WHERE EMP_ID =
							 * "+empCode[i]+""; boolean result =
							 * getSqlModel().singleExecute(updOffc);
							 */

							String insertTemp = "INSERT INTO HRMS_REC_INDUC_TEMP (INDUC_CODE,INDUC_EMPID) "
									+ " VALUES (" + indSchedule.getInductionCode() + "," + empCode[i] + ")";
							boolean resultTemp = getSqlModel().singleExecute(insertTemp);
						}
					}
				}
				indSchedule.setParticipantList(tableList);
				result = true;
			}
			getActivityList(indSchedule, request);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public void getActivityList(InductionSchedule indSchedule,
			HttpServletRequest request) {
		try {
			ArrayList<Object> actList = new ArrayList<Object>();
			String[] actListLen = request.getParameterValues("actListDate");
			String[] actStartTime = request.getParameterValues("actListStartTime");
			String[] actEndTime = request.getParameterValues("actListEndTime");
			String[] actDetails = request.getParameterValues("actListDetails");
			String[] actOwner = request.getParameterValues("actListOwner");
			String[] actOwnerType = request.getParameterValues("ownerType");
			String[] designation = request.getParameterValues("designation");
			String[] actVenueDetails = request.getParameterValues("actVenueDetails");
			String[] actListOwnerIDIerator = request.getParameterValues("actListOwnerIDIerator");

			if (actListLen != null) {
					for (int i = 0; i < actListLen.length; i++) {
						InductionSchedule bean = new InductionSchedule();
						bean.setActListDate(checkNull(actListLen[i]));
						bean.setActListStartTime(checkNull(actStartTime[i]));
						bean.setActListEndTime(checkNull(actEndTime[i]));
						bean.setActListDetails(checkNull(actDetails[i]));
						bean.setActListOwner(checkNull(actOwner[i]));
						bean.setOwnerType(checkNull(actOwnerType[i]));
						bean.setDesignation(checkNull(designation[i]));
						bean.setActVenueDetails(checkNull(actVenueDetails[i]));
						bean.setActListOwnerIDIerator(actListOwnerIDIerator[i]);
						actList.add(bean);
					}
				indSchedule.setActivityList(actList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String saveRecord(InductionSchedule indSchedule,
			HttpServletRequest request) {
		Object[][] maxInducHdr = null;
		boolean resultDtl = false;
		String result = "";
		String dupresult = "";
		try {
			if (!(indSchedule.getInductionCode().equals("")
					|| indSchedule.getInductionCode().equals("null") || indSchedule
					.getInductionCode().equals(null))) {

				Object[][] dataTemp = null;
				String tempData = "SELECT * FROM HRMS_REC_INDUC_TEMP WHERE INDUC_CODE ="
						+ indSchedule.getInductionCode() + "";
				dataTemp = getSqlModel().getSingleResult(tempData);
				for (int i = 0; i < dataTemp.length; i++) {
					String updOffc = "UPDATE HRMS_EMP_OFFC SET EMP_IND_STATUS = 'N' WHERE EMP_ID = "
							+ dataTemp[i][1] + "";
					boolean resultOffc = getSqlModel().singleExecute(updOffc);
				}

				String deleteParti = "DELETE FROM HRMS_REC_INDUC_PARTI WHERE INDUC_CODE ="
						+ indSchedule.getInductionCode() + "";
				boolean resultDelParti = getSqlModel().singleExecute(
						deleteParti);

				String deleteDtl = "DELETE FROM HRMS_REC_INDUC_DTL WHERE INDUC_CODE ="
						+ indSchedule.getInductionCode() + "";
				boolean resultDelDtl = getSqlModel().singleExecute(deleteDtl);

				String deleteHdr = "DELETE FROM HRMS_REC_INDUC_HDR WHERE INDUC_CODE ="
						+ indSchedule.getInductionCode() + "";
				boolean resultDelHdr = getSqlModel().singleExecute(deleteHdr);

				String deleteTemp = "DELETE FROM HRMS_REC_INDUC_TEMP WHERE INDUC_CODE ="
						+ indSchedule.getInductionCode() + " ";
				// boolean resulltTemp =
				// getSqlModel().singleExecute(deleteTemp);
				dupresult = "2";
			}
			if(indSchedule.getInductionCode().equals("")) {
				String query = "SELECT NVL(MAX(INDUC_CODE),0)+1 FROM HRMS_REC_INDUC_HDR";
				maxInducHdr = getSqlModel().getSingleResult(query);
				if(maxInducHdr!=null && maxInducHdr.length >0) {
					indSchedule.setInductionCode(String.valueOf(maxInducHdr[0][0]));
				}
				
			} else {
				indSchedule.setInductionCode(indSchedule.getInductionCode());
			}
			String insertHdr = "INSERT INTO HRMS_REC_INDUC_HDR (INDUC_CODE,INDUC_NAME,INDUC_DESC,INDUC_FROMDATE,INDUC_TODATE,INDUC_VENUE,INDUC_CONTACT) "
					+ " VALUES("
					+ indSchedule.getInductionCode().trim()
					+ ",'"
					+ indSchedule.getInductionName()
					+ "','"
					+ indSchedule.getInducDesc()
					+ "',TO_DATE('"
					+ indSchedule.getInductionFrom()
					+ "','DD-MM-YYYY'),TO_DATE('"
					+ indSchedule.getInductionTo()
					+ "','DD-MM-YYYY'), "
					+ " '"
					+ indSchedule.getInducVenue()
					+ "',"
					+ indSchedule.getCntPersonId() + ")";
			boolean resultHdr = getSqlModel().singleExecute(insertHdr);
			if (resultHdr) {

				String[] actListLen = request.getParameterValues("actListDate");
				String[] actStartTime = request
						.getParameterValues("actListStartTime");
				String[] actEndTime = request
						.getParameterValues("actListEndTime");
				String[] actDetails = request
						.getParameterValues("actListDetails");
				String[] actOwner = request.getParameterValues("actListOwner");
				String[] actOwnerType = request.getParameterValues("ownerType");
				String[] designation = request
						.getParameterValues("designation");
				String[] feedbackStatus = request
						.getParameterValues("feedbackStatus");
				String[] actVenueDetails = request
						.getParameterValues("actVenueDetails");
				String[] actListOwnerIDIerator = request.getParameterValues("actListOwnerIDIerator");
				if (actListLen != null && actListLen.length > 0) {
					for (int i = 0; i < actListLen.length; i++) {
						String owner = "";
						Object[][] maxDtlCode = null;
						if (actOwnerType[i].equals("internal")) {
							owner = "I";
						} else {
							owner = "E";
						}
						
					 
						String insertDtl = "INSERT INTO HRMS_REC_INDUC_DTL (INDUC_DTLCODE,INDUC_CODE,INDUC_ACTDTL,INDUC_ACTDATE,INDUC_ACTSTART_TIME,INDUC_ACTEND_TIME, "
								+ " INDUC_ACTOWNER_TYPE,INDUC_ACT_OWNER,INDUC_VENUE, INDUC_ACTOWNERID) "
								+ " VALUES("
								+ indSchedule.getInductionCode().trim()
								+ ","
								+ indSchedule.getInductionCode().trim()
								+ ",'"
								+ actDetails[i]
								+ "',TO_DATE('"
								+ actListLen[i]
								+ "','DD-MM-YYYY'),'"
								+ actStartTime[i]
								+ "','"
								+ actEndTime[i]
								+ "', "
								+ " '"
								+ owner
								+ "','"
								+ actOwner[i]
								+ "','"
								+ actVenueDetails[i]
								+ "','"
								+ actListOwnerIDIerator[i]                  
								+ "')";
						resultDtl = getSqlModel().singleExecute(insertDtl);
					}
				}

				String[] empCode = request.getParameterValues("empCode");
				if (empCode != null && empCode.length > 0) {
					boolean resultParti = false;
					for (int i = 0; i < empCode.length; i++) {
						Object[][] maxCodeParti = null;
						String maxPartiQuery = "SELECT NVL(MAX(INDUC_DTLCODE),0)+1 FROM HRMS_REC_INDUC_PARTI";
						maxCodeParti = getSqlModel().getSingleResult(
								maxPartiQuery);
						if (maxCodeParti != null && maxCodeParti.length > 0) {
							String insertPartiDtl = "INSERT INTO HRMS_REC_INDUC_PARTI (INDUC_DTLCODE,INDUC_CODE,INDUC_EMPID,INDUC_FEED_STATUS) "
									+ " VALUES("
									+ maxCodeParti[0][0]
									+ ","
									+ indSchedule.getInductionCode().trim()
									+ ","
									+ empCode[i]
									+ ",'" + feedbackStatus[i] + "')";
							resultParti = getSqlModel().singleExecute(
									insertPartiDtl);

							if (resultParti) {
								String updateOffc = "UPDATE HRMS_EMP_OFFC SET EMP_IND_STATUS = 'Y' WHERE EMP_ID = "
										+ empCode[i] + "";
								boolean resultOffc = getSqlModel()
										.singleExecute(updateOffc);
							}
						}
					}
				}
				result = "1";
			}
			getActivityList(indSchedule, request);
			getEmployeeList(indSchedule, request);
			if (dupresult != null && dupresult.equals("2"))
				result = "2";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void dispFromEdit(InductionSchedule indSchedule,
			String inductionCode) {
		try {
			Object[][] dataHdr = null;
				String queryHdr = "SELECT INDUC_CODE,INDUC_NAME,NVL(INDUC_DESC,''),TO_CHAR(INDUC_FROMDATE,'DD-MM-YYYY'),TO_CHAR(INDUC_TODATE,'DD-MM-YYYY'), "
						+ " INDUC_VENUE,INDUC_CONTACT,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
						+ " FROM HRMS_REC_INDUC_HDR "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_INDUC_HDR.INDUC_CONTACT) "
						+ " WHERE INDUC_CODE=" + inductionCode;
				dataHdr = getSqlModel().getSingleResult(queryHdr);
		if(dataHdr != null && dataHdr.length > 0 ) {
				String deleteTemp = "DELETE FROM HRMS_REC_INDUC_TEMP WHERE INDUC_CODE ="
						+ dataHdr[0][0] + " ";
				// boolean resulltTemp = getSqlModel().singleExecute(deleteTemp);
				indSchedule.setInductionCode(checkNull(String
						.valueOf(dataHdr[0][0])));
				indSchedule.setInductionName(checkNull(String
						.valueOf(dataHdr[0][1])));
				indSchedule.setInducDesc(checkNull(String
						.valueOf(dataHdr[0][2])));
				indSchedule.setInductionFrom(checkNull(String
						.valueOf(dataHdr[0][3])));
				indSchedule.setInductionTo(checkNull(String
						.valueOf(dataHdr[0][4])));
				indSchedule.setInducVenue(checkNull(String
						.valueOf(dataHdr[0][5])));
				indSchedule.setCntPersonId(String.valueOf(dataHdr[0][6]));
				indSchedule.setContactPerson(checkNull(String
						.valueOf(dataHdr[0][7])));
			}
			Object[][] dataDtl = null;
				String dtlQuery = "SELECT INDUC_DTLCODE,INDUC_CODE,INDUC_ACTDTL,TO_CHAR(INDUC_ACTDATE,'DD-MM-YYYY'),INDUC_ACTSTART_TIME,INDUC_ACTEND_TIME, "
						+ " INDUC_ACTOWNER_TYPE,INDUC_ACT_OWNER,INDUC_VENUE, INDUC_ACTOWNERID "
						+ " FROM HRMS_REC_INDUC_DTL WHERE INDUC_CODE =" + inductionCode 
						+" ORDER BY TO_DATE(INDUC_ACTDATE,'DD-MM-YYYY') ASC , " 
						+" TO_DATE(INDUC_ACTSTART_TIME,'HH24:MI') ASC ";
				dataDtl = getSqlModel().getSingleResult(dtlQuery);
			if (dataDtl != null && dataDtl.length > 0) {
				ArrayList<Object> actList = new ArrayList<Object>();
					for (int i = 0; i < dataDtl.length; i++) {
						InductionSchedule bean = new InductionSchedule();
						bean.setActListDate(checkNull(String
								.valueOf(dataDtl[i][3])));
						bean.setActListStartTime(checkNull(String
								.valueOf(dataDtl[i][4])));
						bean.setActListEndTime(checkNull(String
								.valueOf(dataDtl[i][5])));
						bean.setActListDetails(checkNull(String
								.valueOf(dataDtl[i][2])));
						bean.setActListOwner(checkNull(String
								.valueOf(dataDtl[i][7])));
						if (String.valueOf(dataDtl[i][6]).equals("I")) {
							bean.setOwnerType("internal");
						} else {
							bean.setOwnerType("external");
						}
						bean.setDesignation("");
						bean.setActVenueDetails(checkNull(String
								.valueOf(dataDtl[i][8])));
						bean.setActListOwnerIDIerator(checkNull(String
								.valueOf(dataDtl[i][9])));
						actList.add(bean);
					}
				indSchedule.setActivityList(actList);
			}
			Object[][] dataParti = null;
				String dtlPartiQuery = "SELECT INDUC_DTLCODE,INDUC_CODE,INDUC_EMPID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_DIV,DIV_NAME,EMP_CENTER, "
						+ " CENTER_NAME,EMP_DEPT,DEPT_NAME,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),NVL(INDUC_FEED_STATUS,'N') "
						+ " FROM HRMS_REC_INDUC_PARTI "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_INDUC_PARTI.INDUC_EMPID) "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
						+ " WHERE INDUC_CODE ="+inductionCode 
						+" ORDER BY INDUC_DTLCODE ASC";
				dataParti = getSqlModel().getSingleResult(dtlPartiQuery);
			if(dataParti !=null && dataParti.length > 0) {
				ArrayList<Object> employeeList = new ArrayList<Object>();
					for (int i = 0; i < dataParti.length; i++) {
						InductionSchedule bean = new InductionSchedule();
						bean.setEmpCode(String.valueOf(dataParti[i][2]));
						bean.setEmpName(String.valueOf(dataParti[i][3]));
						bean.setDivCode(String.valueOf(dataParti[i][4]));
						bean.setDivName(checkNull(String
								.valueOf(dataParti[i][5])));
						bean.setBrCode(String.valueOf(dataParti[i][6]));
						bean.setBrName(checkNull(String
								.valueOf(dataParti[i][7])));
						bean.setDeptListCode(String.valueOf(dataParti[i][8]));
						bean.setDeptListName(checkNull(String
								.valueOf(dataParti[i][9])));
						bean.setDtOfJoinList(checkNull(String
								.valueOf(dataParti[i][10])));
						bean.setFeedbackStatus(String.valueOf(dataParti[i][11]));
						if(String.valueOf(dataParti[i][11]).equals("N")){
							bean.setGivenFeedbackStatus("Feedback Not Given");
						} else {
							bean.setGivenFeedbackStatus("Feedback Given");
						}
						employeeList.add(bean);
					}
				indSchedule.setParticipantList(employeeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewEmployee(InductionSchedule indSchedule,
			HttpServletRequest request) {
		try {
			String result = "";
			Object[][] newEmployee = null;
			String query = " SELECT EMP_DIV,DIV_NAME,EMP_CENTER,CENTER_NAME,EMP_DEPT,DEPT_NAME,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), "
					+ " NVL(HRMS_REC_INDUC_PARTI.INDUC_FEED_STATUS,'N')  "
					+ "  FROM HRMS_EMP_OFFC "
					+ "  LEFT JOIn HRMS_REC_INDUC_PARTI on(HRMS_REC_INDUC_PARTI.INDUC_EMPID=HRMS_EMP_OFFC.EMP_ID) "
					+ "   LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  "
					+ "   LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)  "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
					+ " WHERE EMP_ID = " + indSchedule.getEmpNewId() + "";
			newEmployee = getSqlModel().getSingleResult(query);
			ArrayList<Object> tableList = new ArrayList<Object>();
			String[] empName = request.getParameterValues("empName");
			String[] empCode = request.getParameterValues("empCode");
			String[] divName = request.getParameterValues("divName");
			String[] divCode = request.getParameterValues("divCode");
			String[] brName = request.getParameterValues("brName");
			String[] brCode = request.getParameterValues("brCode");
			String[] deptListName = request.getParameterValues("deptListName");
			String[] deptListCode = request.getParameterValues("deptListCode");
			String[] dtOfJoinList = request.getParameterValues("dtOfJoinList");
			String[] feedbackstatus = request
					.getParameterValues("feedbackStatus");
			String[] givenFeedbackStatus = request
					.getParameterValues("givenFeedbackStatus");
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {
					InductionSchedule bean = new InductionSchedule();
					bean.setEmpCode(empCode[i]);
					bean.setEmpName(empName[i]);
					bean.setDivCode(divCode[i]);
					bean.setDivName(divName[i]);
					bean.setBrCode(brCode[i]);
					bean.setBrName(brName[i]);
					bean.setDeptListCode(deptListCode[i]);
					bean.setDeptListName(deptListName[i]);
					bean.setDtOfJoinList(checkNull(dtOfJoinList[i]));
					bean.setFeedbackStatus(feedbackstatus[i]);
					bean.setGivenFeedbackStatus(givenFeedbackStatus[i]);
					tableList.add(bean);
				}
			}
			if (newEmployee != null && newEmployee.length > 0) {
				InductionSchedule bean = new InductionSchedule();
				bean.setEmpCode(indSchedule.getEmpNewId());
				bean.setEmpName(indSchedule.getEmpNewName());
				bean.setDivCode(String.valueOf(newEmployee[0][0]));
				bean.setDivName(String.valueOf(newEmployee[0][1]));
				bean.setBrCode(String.valueOf(newEmployee[0][2]));
				bean.setBrName(String.valueOf(newEmployee[0][3]));
				bean.setDeptListCode(String.valueOf(newEmployee[0][4]));
				bean.setDeptListName(String.valueOf(newEmployee[0][5]));
				bean.setDtOfJoinList(checkNull(String
						.valueOf(newEmployee[0][6])));
				bean.setFeedbackStatus(String.valueOf(newEmployee[0][7]));
				if (checkNull(String.valueOf(newEmployee[0][7])).equals("N")) {
					bean.setGivenFeedbackStatus("Feedback Not Given");
				} else {
					bean.setGivenFeedbackStatus("Feedback Given");
				}
				tableList.add(bean);
			}
			indSchedule.setParticipantList(tableList);
			getActivityList(indSchedule, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void getScheduledInduction(InductionSchedule indSchedule,
			HttpServletRequest request) {
		try {
			Object[][] data = null;
			ArrayList<Object> tableList = new ArrayList<Object>();
			String query = "SELECT INDUC_CODE,INDUC_NAME,TO_CHAR(INDUC_FROMDATE,'DD-MM-YYYY'),TO_CHAR(INDUC_TODATE,'DD-MM-YYYY') FROM HRMS_REC_INDUC_HDR ORDER BY INDUC_FROMDATE DESC ";
			data = getSqlModel().getSingleResult(query);
			String[] pageIndex = Utility.doPaging(indSchedule.getMyPage(),
					data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				indSchedule.setMyPage("1");
			if (data != null && data.length > 0) {
				indSchedule.setTotalRecords("" + data.length);
				indSchedule.setModeLength("true");
				for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
						.parseInt(String.valueOf(pageIndex[1])); i++) {
					InductionSchedule bean = new InductionSchedule();
					bean.setInductionCode(String.valueOf(data[i][0]));
					bean.setInducListName(String.valueOf(data[i][1]));
					bean.setInducFrmDt(String.valueOf(data[i][2]));
					bean.setInducToDt(String.valueOf(data[i][3]));
					tableList.add(bean);
				}
				indSchedule.setInductionList(tableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getContactDetails(InductionSchedule bean) {
		try {
			String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,LOCATION_NAME,ADD_EMAIL,ADD_MOBILE "
					+ " FROM HRMS_EMP_OFFC LEFT JOIN HRMS_EMP_ADDRESS ON HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID"
					+ " LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID="
					+ bean.getCntPersonId()
					+ " AND ADD_TYPE='P'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setContactPerson(String.valueOf(data[0][0]) + "\n"
						+ checkNull(String.valueOf(data[0][1])) + "\n"
						+ checkNull(String.valueOf(data[0][2])) + "\n"
						+ checkNull(String.valueOf(data[0][3])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
