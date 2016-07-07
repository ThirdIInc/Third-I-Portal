/**
 * 
 */
package org.paradyne.model.extraWorkBenefits;

import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.extraWorkBenefits.ExtraWorkApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;

/**
 * @author Reeba_Joseph
 * 
 */
public class ExtraWorkApplicationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExtraWorkApplicationModel.class);

	public void getAllTypeOfApplications(ExtraWorkApplication application,
			HttpServletRequest request, String empId) {
		Object submitParam[] = null;
		Object returnParam[] = null;

		try {
			// Pending object
			submitParam = new Object[] { "P", empId };
			// Return object
			returnParam = new Object[] { "B", empId };

			// Draft Query
			String draftQuery = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " TO_CHAR(HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_DATE,'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_HDR.EMP_ID, "
					+ " HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_CODE,HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_STATUS "
					+ " FROM HRMS_EXTRAWORK_APPL_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HRMS_EXTRAWORK_APPL_HDR.EMP_ID) "
					+ " WHERE HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_STATUS IN ('D','W') AND HRMS_EXTRAWORK_APPL_HDR.EMP_ID= "
					+ empId + " ORDER BY EXTRAWORK_APPL_CODE DESC";
			Object draftData[][] = getSqlModel().getSingleResult(draftQuery);

			// pending query
			Object submitData[][] = getSqlModel().getSingleResult(getQuery(1),
					submitParam);
			// reurn query
			Object returnData[][] = getSqlModel().getSingleResult(getQuery(1),
					returnParam);

			// SETS DRAFT LIST
			if (draftData != null && draftData.length > 0) {
				ArrayList<Object> DraftList = new ArrayList<Object>();
				for (int i = 0; i < draftData.length; i++) {
					ExtraWorkApplication extWrkBeanDraft = new ExtraWorkApplication();
					extWrkBeanDraft.setExtEmpToken(String
							.valueOf(draftData[i][0]));
					extWrkBeanDraft.setExtEmpName(String
							.valueOf(draftData[i][1]));
					extWrkBeanDraft.setExtAppDate(String
							.valueOf(draftData[i][2]));
					extWrkBeanDraft.setDraftEmpId(String
							.valueOf(draftData[i][3]));
					extWrkBeanDraft.setDraftExtWorkAppNo(String
							.valueOf(draftData[i][4]));
					extWrkBeanDraft.setExtAppStatus(String
							.valueOf(draftData[i][5]));
					DraftList.add(extWrkBeanDraft);
				}
				application.setDraftList(DraftList);
			}// END IF DRAFT LIST

			// PENDING LIST
			if (submitData != null && submitData.length > 0) {
				ArrayList<Object> submitList = new ArrayList<Object>();
				for (int i = 0; i < submitData.length; i++) {
					ExtraWorkApplication extWrkSubmitBean = new ExtraWorkApplication();
					extWrkSubmitBean.setExtEmpToken(String
							.valueOf(submitData[i][0]));
					extWrkSubmitBean.setExtEmpName(String
							.valueOf(submitData[i][1]));
					extWrkSubmitBean.setExtAppDate(String
							.valueOf(submitData[i][2]));
					extWrkSubmitBean.setSubmitEmpId(String
							.valueOf(submitData[i][3]));
					extWrkSubmitBean.setSubmitExtWorkAppNo(String
							.valueOf(submitData[i][4]));
					extWrkSubmitBean.setExtAppStatus(String
							.valueOf(submitData[i][5]));
					submitList.add(extWrkSubmitBean);
				}
				application.setSubmitList(submitList);
			}// END IF PENDING LIST

			// RETURN LIST
			if (returnData != null && returnData.length > 0) {
				ArrayList<Object> returnList = new ArrayList<Object>();
				for (int i = 0; i < returnData.length; i++) {
					ExtraWorkApplication extWrkReturnBean = new ExtraWorkApplication();
					extWrkReturnBean.setExtEmpToken(String
							.valueOf(returnData[i][0]));
					extWrkReturnBean.setExtEmpName(String
							.valueOf(returnData[i][1]));
					extWrkReturnBean.setExtAppDate(String
							.valueOf(returnData[i][2]));
					extWrkReturnBean.setReturnEmpId(String
							.valueOf(returnData[i][3]));
					extWrkReturnBean.setReturnExtWorkAppNo(String
							.valueOf(returnData[i][4]));
					extWrkReturnBean.setExtAppStatus(String
							.valueOf(returnData[i][5]));
					returnList.add(extWrkReturnBean);
				}
				application.setReturnList(returnList);
			}// END IF RETURN LIST
		} catch (Exception e) {
			logger.error("Exception in getAllTypeOfApplications: " + e);
		}// END TRY CATCH BLOCK

	}

	/**
	 * method for setting General employee details...!
	 * 
	 * @param empId
	 * @param bean
	 */
	public ExtraWorkApplication getEmployeeDetails(String empId,
			ExtraWorkApplication bean) {
		try {
			Object[] beanObj = new Object[1];
			beanObj[0] = empId;
			Object[][] empdata = getSqlModel().getSingleResult(getQuery(2),
					beanObj);
			bean.setEmpToken(String.valueOf(empdata[0][0]));
			bean.setEmpName(String.valueOf(empdata[0][1]));
			bean.setBranchName(String.valueOf(empdata[0][2]));
			bean.setDept(String.valueOf(empdata[0][3]));
			bean.setDesg(String.valueOf(empdata[0][4]));
			bean.setEmpId(empId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	/*
	 * public boolean isduplicate(ExtraWorkApplication bean) {
	 * if(!(String.valueOf(bean.getEmpId()).equals(""))) { String query =
	 * "SELECT EXTRAWORK_DATE,EMP_ID FROM HRMS_EXTRAWORK_APPL_DTL " + " WHERE
	 * EXTRAWORK_APPL_CODE NOT IN ("+bean.getCompId()+") AND
	 * EMP_ID="+bean.getEmpId(); Object result[][] =
	 * getSqlModel().getSingleResult(query); } }
	 */

	/**
	 * CHECKS DUPLICATE ENTRY ON EXTRA WORK DATE FOR EMPLOYEE
	 */
	public boolean isduplicate(ExtraWorkApplication comp) {

		try {
			Object result[][] = null;
			if (!(String.valueOf(comp.getEmpId()).equals(""))) {
				if (comp.getCheckEdit().equals("")) {

					String Query = " SELECT HRMS_EXTRAWORK_APPL_HDR.EMP_ID, HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_STATUS, HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_DATE FROM HRMS_EXTRAWORK_APPL_HDR "
							+ " LEFT JOIN HRMS_EXTRAWORK_APPL_DTL ON(HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_CODE = HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_CODE) "
							+ " WHERE HRMS_EXTRAWORK_APPL_HDR.EMP_ID = "
							+ comp.getEmpId()
							+ " AND HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_DATE = To_date('"
							+ comp.getPrDate()
							+ "', 'dd-mm-yyyy') "
							+" AND HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_STATUS  NOT IN('W')"
							+ " ORDER BY HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_DATE ";
					result = getSqlModel().getSingleResult(Query);

					if (result.length > 0) {
						if (result.length == 1) {
							if (String.valueOf(result[0][1]).equals("R")
									|| String.valueOf(result[0][1]).equals("B")) {
								// REJECTED OR SENT BACK
								return true;
							} else
								return false;
						} else
							for (int i = 0; i < result.length; i++) {
								if (!String.valueOf(result[i][1]).equals("R")
										|| !String.valueOf(result[i][1])
												.equals("B")) {
									return false;
								}// END IF R OR B
							}// END FOR
					} else {
						return true;
					}
					return false;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public boolean isduplicateCheckForIterator(ExtraWorkApplication comp,
			Object[] date) {
		try {
			if (comp.getCheckEdit().equals("")) {
				if (date != null && date.length > 0) {
					for (int i = 0; i < date.length; i++) {
						if (String.valueOf(date[i]).trim().equals(
								comp.getPrDate().trim())) {
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			logger
					.error("Exception in isduplicateCheckForIterator--------------"
							+ e);
		}
		return true;
	}

	/**
	 * SETS VALUES OF LIST
	 * 
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param hsType
	 */
	public void checked(ExtraWorkApplication bean, Object[] hprojName,
			Object[] hDate, Object[] hsTime, Object[] heTime, Object[] hsType) {
		try {
			ArrayList<Object> compList = new ArrayList<Object>();
			String type = "";
			String day = "";
			if (hprojName != null) {
				for (int i = 0; i < hprojName.length + 1; i++) {
					ExtraWorkApplication bean1 = new ExtraWorkApplication();
					if (i < hprojName.length) {
						if (hsType[i].equals("F"))
							type = "Full Day";
						else if (hsType[i].equals("H"))
							type = "Half Day";
						else if (hsType[i].equals("O"))
							type = "Partial Day";
						else if (hsType[i].equals("Full Day"))
							type = "Full Day";
						else if (hsType[i].equals("Half Day"))
							type = "Half Day";
						else if (hsType[i].equals("Partial Day"))
							type = "Partial Day";
						bean1.setHprojName(String.valueOf(hprojName[i]));
						bean1.setHsType(type);
						bean1.setHDate(String.valueOf(hDate[i]));
						bean1.setHsTime(String.valueOf(hsTime[i]));
						bean1.setHeTime(String.valueOf(heTime[i]));
						day = checkIfHoliday(String.valueOf(hDate[i]), bean);
						if (day.equals("")) {
							day = getDayFromDate(String.valueOf(hDate[i]));
						}// END IF DAY
						bean1.setHDay(day);
						compList.add(bean1);
						bean.setCompList(compList);
					}// END IF LENGTH
				} // end of for loop
			}// END IF LIST NOT EMPTY
		} catch (Exception e) {
			logger.error(e);
		} // end of try catch block
	}

	/**
	 * method for adding the extra work details in the iterator table
	 * 
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param hsType
	 */
	public void add(ExtraWorkApplication bean, Object[] hprojName,
			Object[] hDate, Object[] hsTime, Object[] heTime, Object[] hsType) {
		try {

			System.out.println("in add method------------------------");
			ArrayList<Object> compList = new ArrayList<Object>();
			String type = "";
			String day = "";
			if (hprojName != null) {
				for (int i = 0; i < hprojName.length + 1; i++) {
					ExtraWorkApplication bean1 = new ExtraWorkApplication();
					if (i < hprojName.length) {
						if (hsType[i].equals("F")
								|| hsType[i].equals("Full Day"))
							type = "Full Day";
						else if (hsType[i].equals("H")
								|| hsType[i].equals("Half Day"))
							type = "Half Day";
						else
							type = "Partial Day";

						bean1.setHprojName(String.valueOf(hprojName[i]));
						bean1.setHsType(type);
						bean1.setHDate(String.valueOf(hDate[i]));
						bean1.setHsTime(String.valueOf(hsTime[i]));
						bean1.setHeTime(String.valueOf(heTime[i]));
						day = checkIfHoliday(String.valueOf(hDate[i]), bean);
						if (day.equals("")) {
							day = getDayFromDate(String.valueOf(hDate[i]));
						}// END IF DAY
						bean1.setHDay(day);
						compList.add(bean1);
						bean.setCompList(compList);
					}// END IF LENGTH
					else {
						if (bean.getType().equals("F"))
							type = "Full Day";
						else if (bean.getType().equals("H"))
							type = "Half Day";
						else
							type = "Partial Day";
						bean1.setHprojName(bean.getPrName());
						bean1.setHsType(type);
						bean1.setHDate(bean.getPrDate());
						bean1.setHsTime(bean.getStartTime());
						bean1.setHeTime(bean.getEndTime());
						day = checkIfHoliday(bean.getPrDate(), bean);
						if (day.equals("")) {
							day = getDayFromDate(bean.getPrDate());
						}// END IF
						bean1.setHDay(day);
						compList.add(bean1);
						bean.setCompList(compList);

					} // end of else statement
				} // end of for loop
			}// END IF LIST PRESENT
			else {

				System.out
						.println("in add method------------------------in else loop-------------");
				ExtraWorkApplication bean1 = new ExtraWorkApplication();
				if (bean.getType().equals("F"))
					type = "Full Day";
				else if (bean.getType().equals("H"))
					type = "Half Day";
				else
					type = "Partial Day";
				bean1.setHprojName(bean.getPrName());
				bean1.setHsType(type);
				bean1.setHDate(bean.getPrDate());
				bean1.setHsTime(bean.getStartTime());
				bean1.setHeTime(bean.getEndTime());
				day = checkIfHoliday(bean.getPrDate(), bean);
				if (day.equals("")) {
					day = getDayFromDate(bean.getPrDate());
				}
				bean1.setHDay(day);
				compList.add(bean1);
				bean.setCompList(compList);
			}// END ELSE (LIST NOT PRESENT)
		} catch (Exception e) {
			logger.error(e);
		}// end of try catch block
	}

	/**
	 * function for editing the record
	 * 
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param hsType
	 */
	public void edit(ExtraWorkApplication bean, Object[] hprojName,
			Object[] hDate, Object[] hsTime, Object[] heTime, Object[] hsType) {
		try {
			ArrayList<Object> compList = new ArrayList<Object>();
			String type = "";
			String day = "";
			if (hprojName != null) {
				for (int i = 0; i < hprojName.length; i++) {
					ExtraWorkApplication bean1 = new ExtraWorkApplication();

					if (i == Integer.parseInt(bean.getCheckEdit())) {
						if (bean.getType().equals("F"))
							type = "Full Day";
						else if (bean.getType().equals("H"))
							type = "Half Day";
						else
							type = "Partial Day";
						bean1.setHprojName(bean.getPrName());
						bean1.setHsType(type);
						bean1.setHDate(bean.getPrDate());
						bean1.setHsTime(bean.getStartTime());
						bean1.setHeTime(bean.getEndTime());
						day = checkIfHoliday(bean.getPrDate(), bean);
						if (day.equals("")) {
							day = getDayFromDate(bean.getPrDate());
						}// END IF DAY IS BLANK
						bean1.setHDay(day);
						compList.add(bean1);
					}// IF VALUE SAME AS CHECKEDIT
					else {
						if (hsType[i].equals("F")
								|| hsType[i].equals("Full Day"))
							type = "Full Day";
						else if (hsType[i].equals("H")
								|| hsType[i].equals("Half Day"))
							type = "Half Day";
						else
							type = "Partial Day";
						bean1.setHprojName(String.valueOf(hprojName[i]));
						bean1.setHsType(type);
						bean1.setHDate(String.valueOf(hDate[i]));
						bean1.setHsTime(String.valueOf(hsTime[i]));
						bean1.setHeTime(String.valueOf(heTime[i]));
						day = checkIfHoliday(String.valueOf(hDate[i]), bean);
						if (day.equals("")) {
							day = getDayFromDate(String.valueOf(hDate[i]));
						}// END IF DAY
						bean1.setHDay(day);
						compList.add(bean1);
					}// IF VALUE NOT SAME
					bean.setCompList(compList);
				}// END OF LOOP
			}// IF LIST

		} catch (Exception e) {
			logger.error(e);
		}
		// end of try catch block
	}

	/**
	 * method for deleting record
	 * 
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param serialNo
	 * @param hsType
	 */
	public void delItem(ExtraWorkApplication bean, Object[] hprojName,
			Object[] hDate, Object[] hsTime, Object[] heTime, String serialNo,
			Object[] hsType) {
		try {
			ArrayList<Object> compList = new ArrayList<Object>();
			String type = "";
			String day = "";
			for (int i = 0; i < hprojName.length + 1; i++) {
				ExtraWorkApplication bean1 = new ExtraWorkApplication();
				if (i < hprojName.length) {
					if (hsType[i].equals("F"))
						type = "Full Day";
					else if (hsType[i].equals("H"))
						type = "Half Day";
					else
						type = "Partial Day";
					bean1.setHprojName(String.valueOf(hprojName[i]));
					bean1.setHsType(type);
					bean1.setHDate(String.valueOf(hDate[i]));
					bean1.setHsTime(String.valueOf(hsTime[i]));
					bean1.setHeTime(String.valueOf(heTime[i]));
					day = checkIfHoliday(String.valueOf(hDate[i]), bean);
					if (day.equals("")) {
						day = getDayFromDate(String.valueOf(hDate[i]));
					}// END IF DAY IS BLANK
					bean1.setHDay(day);
					compList.add(bean1);
				}// END IF LENGTH
			}// END OF LOOP
			compList.remove(Integer.parseInt(serialNo));
			bean.setCompList(compList);
		} catch (Exception e) {
			logger.error(e);
		} // end of try catch block
	}

	/**
	 * SET APPROVER DATA
	 * 
	 * @param bean
	 * @param empFlow
	 */
	public void setApproverData(ExtraWorkApplication bean, Object[][] empFlow) {
		try {
			if (empFlow != null && empFlow.length > 0) {
				// IF REPORTING STRUCTURE DEFINED
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {
					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";
					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}// END OF LOOP
				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList<Object> arrList = new ArrayList<Object>();
					for (int i = 0; i < approverDataObj.length; i++) {
						ExtraWorkApplication extBean = new ExtraWorkApplication();
						extBean.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						extBean.setSrNoIterator(srNo);
						arrList.add(extBean);
					}// END LOOP
					bean.setApproverList(arrList);
				}// END IF QUERYOBJ NOT NULL

			}// END IF EMFLOW NOT NULL
		} catch (Exception e) {
			logger.error("Exception in setApproverData method : " + e);
		}// END TRY CATCH BLOCK
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	/**
	 * Retrieve keep informed iterator values
	 * 
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param bean
	 */
	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode, String[] empName, ExtraWorkApplication bean) {
		try {
			ArrayList<ExtraWorkApplication> keepInformedList = new ArrayList<ExtraWorkApplication>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					ExtraWorkApplication extApp = new ExtraWorkApplication();
					extApp.setKeepInformedEmpId(empCode[i]);
					extApp.setKeepInformedEmpName(empName[i]);
					extApp.setKeepInformedSerialNo(srNo[i]);// sr no
					keepInformedList.add(extApp);
				}// END LOOP
				bean.setKeepInformedList(keepInformedList);
			}// END IF LIST NOT NULL
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForKeepInformed method"
							+ e);
		}// END TRY CATCH BLOCK
	}

	/**
	 * SETS KEEP INFORMED TO LIST
	 * 
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param bean
	 */
	public void setKeepInformed(String[] srNo, String[] empCode,
			String[] empName, ExtraWorkApplication bean) {

		try {
			ExtraWorkApplication extApp = new ExtraWorkApplication();
			extApp.setKeepInformedEmpId(bean.getEmployeeId());
			extApp.setKeepInformedEmpName(bean.getEmployeeName());
			ArrayList<ExtraWorkApplication> extWrkList = displayNewValueForKeepInformed(
					srNo, empCode, empName, bean);
			extApp.setKeepInformedSerialNo(String
					.valueOf(extWrkList.size() + 1));// sr no
			extWrkList.add(extApp);
			bean.setKeepInformedList(extWrkList);
		} catch (Exception e) {
			logger.error("Exception in setKeepInformed method : " + e);
		}// END TRY CATCH BLOCK

	}

	/**
	 * ADd new values to existing keep informed to list
	 * 
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param bean
	 * @return ArrayList
	 */
	private ArrayList<ExtraWorkApplication> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode, String[] empName,
			ExtraWorkApplication bean) {
		ArrayList<ExtraWorkApplication> extWrkList = null;
		try {
			extWrkList = new ArrayList<ExtraWorkApplication>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					ExtraWorkApplication extApp = new ExtraWorkApplication();
					extApp.setKeepInformedEmpId(empCode[i]);
					extApp.setKeepInformedEmpName(empName[i]);
					extApp.setKeepInformedSerialNo(srNo[i]);
					extWrkList.add(extApp);
				}// END LOOP
			}// END IF LIST NOT NULL
		} catch (Exception e) {
			logger.error("Exception in displayNewValueForKeepInformed method :"
					+ e);
		}// END TRY CATCH BLOCK
		return extWrkList;
	}

	/**
	 * SETs LIST OF APPROVER COMMENTS
	 * 
	 * @param srNo
	 * @param approverId
	 * @param approverName
	 * @param approvedDate
	 * @param approvedComments
	 * @param prevApproverStatus
	 * @param bean
	 */
	public void setApproverCommentList(String[] srNo, String[] approverId,
			String[] approverName, String[] approvedDate,
			String[] approvedComments, String[] prevApproverStatus,
			ExtraWorkApplication bean) {
		try {
			if (approverName != null && approverName.length > 0) {
				ArrayList<Object> arrList = new ArrayList<Object>();
				for (int i = 0; i < srNo.length; i++) {
					ExtraWorkApplication extBean = new ExtraWorkApplication();
					extBean.setPrevApproverID(approverId[i]);
					extBean.setPrevApproverName(approverName[i]);
					extBean.setPrevApproverDate(approvedDate[i]);
					extBean.setPrevApproverComment(approvedComments[i]);
					extBean.setPrevApproverStatus(prevApproverStatus[i]);
					arrList.add(extBean);
				}// END LOOP
				bean.setApproverCommentList(arrList);
			}// END IF LIST NOT NULL
		} catch (Exception e) {
			logger.error("Exception in setApproverCommentList method :" + e);
		}// END TRY CATCH BLOCK
	}

	/**
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param empFlow
	 * @param hsType
	 * @param status
	 * @param day
	 * @return boolean after inserting a record
	 */
	public boolean save(ExtraWorkApplication bean, Object[] hprojName,
			Object[] hDate, Object[] hsTime, Object[] heTime,
			Object[][] empFlow, Object[] hsType, String status,
			String[] empCode, Object[] day) {

		boolean data = false;
		// SAVE HEADER DETAILS
		boolean result = false;
		try {
			String compIDSql = " SELECT NVL(MAX(EXTRAWORK_APPL_CODE), 0) + 1 FROM HRMS_EXTRAWORK_APPL_HDR ";
			Object[][] compIDObj = getSqlModel().getSingleResult(compIDSql);
			bean.setCompId(String.valueOf(compIDObj[0][0]));
			Object[][] addObj = new Object[1][7];
			addObj[0][0] = String.valueOf(compIDObj[0][0]);
			addObj[0][1] = bean.getEmpId();
			addObj[0][2] = bean.getAppDate();
			// APPROVER ID
			addObj[0][3] = String.valueOf(empFlow[0][0]);
			addObj[0][4] = bean.getComments();
			// ALTERNATE APPROVER ID
			addObj[0][5] = String.valueOf(empFlow[0][3]);
			addObj[0][6] = status;
			result = getSqlModel().singleExecute(getQuery(3), addObj);
			String type = "";
			if (result) {
				// SAVE KEEP INFORMED LIST VALUES
				saveKeepInformedList(empCode, bean);
				String maxQuery = " SELECT MAX(EXTRAWORK_APPL_CODE) FROM HRMS_EXTRAWORK_APPL_HDR";
				Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
				for (int i = 0; i < hprojName.length; i++) {
					if (hsType[i].equals("Full Day"))
						type = "F";
					else if (hsType[i].equals("Half Day"))
						type = "H";
					else if (hsType[i].equals("Partial Day"))
						type = "O";
					Object addItem[][] = new Object[1][9];
					addItem[0][0] = String.valueOf(maxObj[0][0]);
					addItem[0][1] = String.valueOf(hprojName[i]);
					addItem[0][2] = String.valueOf(hDate[i]);
					addItem[0][3] = String.valueOf(hsTime[i]);
					addItem[0][4] = String.valueOf(heTime[i]);
					addItem[0][5] = type;
					addItem[0][6] = bean.getEmpId();
					addItem[0][7] = status;
					addItem[0][8] = String.valueOf(day[i]);
					// SAVE DETAILS IF HEADER SAVED
					data = getSqlModel().singleExecute(getQuery(4), addItem);

				}// END LOOP

				if (data) {
					if (hDate != null && hDate.length > 0 && status.equals("P")) {
						data = insertExtraworkIntoAttendance(hDate, bean
								.getEmpId(), String.valueOf(maxObj[0][0]));
					}
				}
			}// END IF HEADER SAVED
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (data == true & result == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean insertExtraworkIntoAttendance(Object[] date,
			String empCode, String applicationCode) {
		boolean result = false;

		if (date != null && date.length > 0) {
			for (int i = 0; i < date.length; i++) {
				String actualDataUpdateInAtt = "UPDATE HRMS_DAILY_ATTENDANCE_"
						+ String.valueOf(date[i]).substring(6, 10)
						+ " SET IS_APPL_PROCESS='Y' WHERE ATT_DATE=TO_DATE('"
						+ date[i] + "','DD-MM-YYYY') AND ATT_EMP_ID=" + empCode;

				result = getSqlModel().singleExecute(actualDataUpdateInAtt);
			}

		}

		return result;
	}

	/**
	 * METHOD TO SAVE KEEP INFORMED TO LIST
	 * 
	 * @param empCode
	 * @param bean
	 */
	public void saveKeepInformedList(String empCode[], ExtraWorkApplication bean) {

		try {
			// SAVE KEEP INFORMED TO EMPCODES AS COMMA SEPARATED VALUES.
			String empId = "";
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					if (i < empCode.length - 1) {
						empId += empCode[i] + ",";
					} else {
						empId = empId + empCode[i];
					}// END IF ELSE BLOCK
				}// END LOOP
			}// END IF EMPCODE NOT NULL
			String updateQuery = "  UPDATE "
					+ " HRMS_EXTRAWORK_APPL_HDR SET EXTRAWORK_KEEP_INFORMED=?  WHERE EXTRAWORK_APPL_CODE=? AND EMP_ID=? ";
			Object updateObj[][] = new Object[1][3];
			updateObj[0][0] = empId;
			updateObj[0][1] = bean.getCompId();
			updateObj[0][2] = bean.getEmpId();
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Exception in saveKeepInformedList method : " + e);
		}

	}

	/**
	 * REMOVE DATA FROM KEEP INFORMED TO LIST
	 * 
	 * @param serialNo
	 * @param empCode
	 * @param empName
	 * @param bean
	 */
	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empName, ExtraWorkApplication bean) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					ExtraWorkApplication bean1 = new ExtraWorkApplication();
					bean1.setKeepInformedSerialNo(String.valueOf(i + 1));
					bean1.setKeepInformedEmpId(empCode[i]);
					bean1.setKeepInformedEmpName(empName[i]);
					tableList.add(bean1);

				}// END LOOP
				tableList.remove(Integer.parseInt(bean.getCheckRemove()) - 1);

			}// END IF LIST NOT NULL
			bean.setKeepInformedList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformedData method : " + e);
		}// END TRY CATCH BLOCK

	}

	/**
	 * METHOD TO CHECK IF APPLIACTION IS FORWARDED
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkForward(ExtraWorkApplication bean) {
		String sql = " SELECT EXTRAWORK_APPL_STATUS FROM HRMS_EXTRAWORK_APPL_HDR "
				+ " WHERE EXTRAWORK_APPL_STATUS = 'P' "
				+ " AND EXTRAWORK_APPL_LEVEL = 1 AND EXTRAWORK_APPL_CODE ="
				+ bean.getCompId();
		Object[][] result = getSqlModel().getSingleResult(sql);
		if (result.length > 0) {
			return true;
		} else {
			return false;
		}// END IF ELSE BLOCK
	}

	/**
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param empFlow
	 * @param hsType
	 * @param status
	 * @param day
	 * @return boolean after updating a record
	 */
	public boolean update(ExtraWorkApplication bean, Object[] hprojName,
			Object[] hDate, Object[] hsTime, Object[] heTime,
			Object[][] empFlow, Object[] hsType, String status,
			String[] empCode, Object[] day) {
		try {
			boolean flag = false;
			boolean result = false;
			Object[][] addObj = new Object[1][7];
			addObj[0][0] = bean.getEmpId();
			addObj[0][1] = bean.getAppDate();
			addObj[0][2] = String.valueOf(empFlow[0][0]);
			addObj[0][3] = bean.getComments();
			addObj[0][4] = String.valueOf(empFlow[0][3]);
			addObj[0][5] = status;
			addObj[0][6] = bean.getCompId();
			// UPDATES HEADER TABLE
			boolean data = getSqlModel().singleExecute(getQuery(5), addObj);
			String type = "";
			if (data) {
				// SAVE KEEP INFORMED LIST
				saveKeepInformedList(empCode, bean);
				if (hprojName != null) {
					// DELETE FROM DETAIL TABLE
					String query = " DELETE FROM  HRMS_EXTRAWORK_APPL_DTL WHERE EXTRAWORK_APPL_CODE = "
							+ bean.getCompId();
					flag = getSqlModel().singleExecute(query);
				}
				if (flag) {
					// IF DELETE INSERT INTO DETAIL TABLE
					for (int i = 0; i < hprojName.length; i++) {
						if (hsType[i].equals("Full Day"))
							type = "F";
						else if (hsType[i].equals("Half Day"))
							type = "H";
						else if (hsType[i].equals("Partial Day"))
							type = "O";
						Object addItem[][] = new Object[1][9];
						addItem[0][0] = bean.getCompId();
						addItem[0][1] = String.valueOf(hprojName[i]);
						addItem[0][2] = String.valueOf(hDate[i]);
						addItem[0][3] = String.valueOf(hsTime[i]);
						addItem[0][4] = String.valueOf(heTime[i]);
						addItem[0][5] = type;
						addItem[0][6] = bean.getEmpId();
						addItem[0][7] = status;
						addItem[0][8] = String.valueOf(day[i]);
						result = getSqlModel().singleExecute(getQuery(6),
								addItem);
					}// END LOOP
				}// END IF FLAG
			}
			if (data == true & flag == true & result == true) {
				return true;
			} else {
				return false;
			}// END IF ELSE BLOCK
		} catch (Exception e) {
			logger.error(e);
			return false;
		} // end of try catch block
	}

	/**
	 * METHOD TO SET SAVE LIST OF EXTRA WORK DETAILS
	 * 
	 * @param bean
	 * @param cmpId
	 */
	public void setSavedExtraWorkList(ExtraWorkApplication bean, String cmpId) {
		try {
			String sql = " SELECT EXTRAWORK_APPL_STATUS, NVL(EXTRAWORK_APPL_COMMENTS,' ') FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE = "
					+ cmpId;
			Object[][] result = getSqlModel().getSingleResult(sql);
			if (result.length > 0) {
				bean.setStatus(String.valueOf(result[0][0]));
				bean.setComments(String.valueOf(result[0][1]).trim());
			}// END IF QUERY OBJECT
		} catch (Exception e) {
			logger.error(e);
		} // end of try catch block...!
		ArrayList<Object> extList = new ArrayList<Object>();
		String query = " SELECT EXTRAWORK_PURPOSE, TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'), "
				+ " EXTRAWORK_STARTTIME, EXTRAWORK_ENDTIME, DECODE(EXTRAWORK_DAY_TYPE,'F','Full Day','H','Half Day','O','Partial Day') "
				+ " , EXTRAWORK_DAY"
				+ " FROM HRMS_EXTRAWORK_APPL_DTL "
				+ " WHERE EXTRAWORK_APPL_CODE = " + cmpId;
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				ExtraWorkApplication bean1 = new ExtraWorkApplication();
				bean1.setHprojName(String.valueOf(data[i][0]));
				bean1.setHDate(String.valueOf(data[i][1]));
				bean1.setHsTime(String.valueOf(data[i][2]));
				bean1.setHeTime(String.valueOf(data[i][3]));
				bean1.setHsType(String.valueOf(data[i][4]));
				bean1.setHDay(String.valueOf(data[i][5]));
				extList.add(bean1);
				// bean.setIsExtApp("false");
			}// END FOR
			bean.setCompList(extList);
			bean.setIteratorFlag("true");
		}// END IF DATA OBJ PRESENT
	}

	/**
	 * THIS METHOD IS USED FOR APPROVAL
	 * 
	 * @param bean
	 */
	public void getRecord(ExtraWorkApplication bean) {
		try {
			Object[] param = new Object[1];
			param[0] = bean.getCompId();
			// getquery(7) for selecting the status ,details,level
			Object applData[][] = getSqlModel().getSingleResult(getQuery(7),
					param);
			// 1) setting the status of the application 1)if level is 1 & status
			// will be set to value coming from database
			if (String.valueOf(applData[0][1]).equals("1")) {

				bean.setStatus(String.valueOf(applData[0][0]));
				bean.setHiddenStatus(String.valueOf(applData[0][0]));
			}// end of if
			// 2)if level is higher than 1 & application is pending status will
			// be set to "forwarded"

			else if (!(String.valueOf(applData[0][1]).equals("1"))
					&& String.valueOf(applData[0][0]).equals("P")) {
				bean.setStatus("F");
				bean.setHiddenStatus("F");
			}// end of else if

			// 3)if level is higher than 1 & application is not pending status
			// will be set to value coming from database
			else {
				bean.setStatus(String.valueOf(applData[0][0]));
				bean.setHiddenStatus(String.valueOf(applData[0][0]));
			}// end of else
			bean.setLevel(String.valueOf(applData[0][1]));
		} catch (Exception e) {
			logger.error("Exception in getRecord: " + e);
		}

	}// end of getRecord

	/**
	 * METHOS TO SET SAVED KEEP INFORMED TO RECORDS
	 * 
	 * @param bean
	 */
	public boolean getKeepInformedSavedRecord(ExtraWorkApplication bean) {
		boolean flag =false ;
		try {
			String selectQuery = " SELECT EXTRAWORK_KEEP_INFORMED FROM "
					+ " HRMS_EXTRAWORK_APPL_HDR "
					+ " WHERE EXTRAWORK_APPL_CODE=" + bean.getCompId()
					+ " AND EMP_ID=" + bean.getEmpId();
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
				}// END IF
				Object result[][] = getSqlModel().getSingleResult(query);
				ArrayList<ExtraWorkApplication> keepInformedList = new ArrayList<ExtraWorkApplication>();
				if (result != null) {
					for (int i = 0; i < result.length; i++) {
						ExtraWorkApplication extBean = new ExtraWorkApplication();
						extBean.setKeepInformedEmpId(String
								.valueOf(result[i][1]));
						
						if(extBean.getKeepInformedEmpId().equals(bean.getUserEmpId()))
						{
							flag=true;
						}
						extBean.setKeepInformedEmpName(String
								.valueOf(result[i][0]));
						extBean.setKeepInformedSerialNo(String.valueOf(i + 1));// sr
						// no
						keepInformedList.add(extBean);
					}// END FOR LOOP
					bean.setKeepInformedList(keepInformedList);
				}// END IF RESULT NOT NULL
			}// END IF SELECTEDDATAOBJ NOT NULL
		} catch (Exception e) {
			logger.error("Exception in getKeepInformedSavedRecord method : "
					+ e);
		}// END OF TRY CATCH BLOCK
		
		return flag; 
	}

	/**
	 * METHOD TO SET APPROVER COMMENTS
	 * 
	 * @param bean
	 */
	public void setApproverComments(ExtraWorkApplication bean) {

		try {
			String approverCommentQuery = " SELECT EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),TO_CHAR(EXTRAWORK_APPROVE_DATE,'DD-MM-YYYY'), "
					+ " DECODE(EXTRAWORK_APPROVER_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected','F','Forwarded'),EXTRAWORK_APPROVER_REMARK  "
					+ " FROM HRMS_EXTRAWORK_APPL_PATH "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPROVER= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE  HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPL_CODE="
					+ bean.getCompId()
					+ " ORDER BY HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPL_CODE DESC ";
			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList<Object> arrList = new ArrayList<Object>();
				for (int i = 0; i < approverCommentObj.length; i++) {
					ExtraWorkApplication apprBean = new ExtraWorkApplication();
					apprBean.setPrevApproverID(checkNull(String
							.valueOf(approverCommentObj[i][0])));
					apprBean.setPrevApproverName(checkNull(String
							.valueOf(approverCommentObj[i][1])));
					apprBean.setPrevApproverDate(checkNull(String
							.valueOf(approverCommentObj[i][2])));
					apprBean.setPrevApproverStatus(checkNull(String
							.valueOf(approverCommentObj[i][3])));
					apprBean.setPrevApproverComment(checkNull(String
							.valueOf(approverCommentObj[i][4])));
					arrList.add(apprBean);
				}// END FOR LOOP
				bean.setApproverCommentList(arrList);
			}// END IF APPROVERCOMMENTOBJ NOT NULL
		} catch (Exception e) {
			logger.error("Exception in setApproverComments method : " + e);
		}// END TRY CATCH BLOCK
	}

	/**
	 * METHOD TO RETRIEVE SEND BACK COMMENTS
	 * 
	 * @param bean
	 */
	public void getSendBackComments(ExtraWorkApplication bean) {
		try {
			String sendBackCommentQuery = " SELECT EXTRAWORK_APPROVER_REMARK FROM HRMS_EXTRAWORK_APPL_PATH "
					+ "	WHERE EXTRAWORK_APPL_CODE="
					+ bean.getCompId()
					+ "AND EXTRAWORK_APPROVER_STATUS='"
					+ bean.getHiddenStatus() + "'";
			Object sendBackCommentObj[][] = getSqlModel().getSingleResult(
					sendBackCommentQuery);
			if (sendBackCommentObj != null && sendBackCommentObj.length > 0) {
				bean.setApproverComments(checkNull(String
						.valueOf(sendBackCommentObj[0][0])));
			}// END IF
		} catch (Exception e) {
			logger.error("Exception in getSendBackComments method : " + e);
		}// END TRY CATCH BLOCK

	}

	/**
	 * @param bean
	 * @return boolean after deleting record
	 */
	public boolean delete(ExtraWorkApplication bean) {
		boolean result = false;
		boolean data = false;

		String sql = " DELETE FROM HRMS_EXTRAWORK_APPL_DTL WHERE EXTRAWORK_APPL_CODE = "
				+ bean.getCompId();
		data = getSqlModel().singleExecute(sql);

		String query = " DELETE FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE = "
				+ bean.getCompId();
		result = getSqlModel().singleExecute(query);
		if (data == true & result == true) {
			return true;
		} else {
			return false;
		}// END IF ELSE BLOCK
	}
	
	
	

	public boolean withdrawApplication(ExtraWorkApplication bean) {
		boolean result = false;
		try {
			String updateHdr = " UPDATE HRMS_EXTRAWORK_APPL_HDR SET EXTRAWORK_APPL_STATUS ='W' WHERE EXTRAWORK_APPL_CODE ="
					+ bean.getCompId();
			result = getSqlModel().singleExecute(updateHdr);
			if (result) {
				String updateDtl = " UPDATE HRMS_EXTRAWORK_APPL_DTL SET EXTRAWORK_APPL_STATUS = 'W'  "
						+ " WHERE EXTRAWORK_APPL_CODE=" + bean.getCompId();
				result = getSqlModel().singleExecute(updateDtl);
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in cancelPendingApplication : " + e);
		}// END TRY CATCH BLOCK
		return result;
	}// end of withdrawApplication

	/**
	 * METHOD TO RETRIEVE APPROVED LIST DETAILS
	 * 
	 * @param bean
	 * @param request
	 * @param empId
	 */
	public void getApprovedList(ExtraWorkApplication bean,
			HttpServletRequest request, String empId) {
		try {
			Object approvedParam[] = null;
			approvedParam = new Object[] { "A", empId };
			Object approvedData[][] = getSqlModel().getSingleResult(
					getQuery(1), approvedParam);

			String[] pageIndexApproved = Utility.doPaging(bean.getMyPage(),
					approvedData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}// END IF

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				bean.setMyPage("1");

			if (approvedData != null && approvedData.length > 0) {
				bean.setApproveLength("true");
				ArrayList<Object> approvedList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					ExtraWorkApplication approvedBean = new ExtraWorkApplication();
					approvedBean.setExtEmpToken(String
							.valueOf(approvedData[i][0]));
					approvedBean.setExtEmpName(String
							.valueOf(approvedData[i][1]));
					approvedBean.setExtAppDate(String
							.valueOf(approvedData[i][2]));
					approvedBean.setApprovedEmpId(String
							.valueOf(approvedData[i][3]));
					approvedBean.setApprovedExtWorkAppNo(String
							.valueOf(approvedData[i][4]));
					approvedBean.setExtAppStatus(String
							.valueOf(approvedData[i][5]));
					approvedList.add(approvedBean);
				}// END FOR LOOP
				bean.setApprovedList(approvedList);
			}// END IF DATA NOT NULL
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
			e.printStackTrace();
		}// END TRY CATCH BLOCK

	}

	/**
	 * METHOD TO SET LIST OF REJECTED APPLIATIONS
	 * 
	 * @param bean
	 * @param request
	 * @param empId
	 */
	public void getRejectedList(ExtraWorkApplication bean,
			HttpServletRequest request, String empId) {

		try {
			Object rejectedParam[] = null;
			rejectedParam = new Object[] { "R", empId };

			Object rejectedData[][] = getSqlModel().getSingleResult(
					getQuery(1), rejectedParam);

			String[] pageIndexRejected = Utility.doPaging(bean
					.getMyPageRejected(), rejectedData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}// END IF

			request.setAttribute("totalPageRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("PageNoRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				bean.setMyPageRejected("1");

			if (rejectedData != null && rejectedData.length > 0) {

				bean.setRejectedLength("true");
				ArrayList<Object> rejectedList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {

					ExtraWorkApplication rejectedBean = new ExtraWorkApplication();
					rejectedBean.setExtEmpToken(String
							.valueOf(rejectedData[i][0]));
					rejectedBean.setExtEmpName(String
							.valueOf(rejectedData[i][1]));
					rejectedBean.setExtAppDate(String
							.valueOf(rejectedData[i][2]));
					rejectedBean.setRejectedEmpId(String
							.valueOf(rejectedData[i][3]));
					rejectedBean.setRejectedExtWorkAppNo(String
							.valueOf(rejectedData[i][4]));
					rejectedBean.setExtAppStatus(String
							.valueOf(rejectedData[i][5]));
					rejectedList.add(rejectedBean);

				}// END LOOP
				bean.setRejectedList(rejectedList);
			}// END IF DATA NOT NULL

		} catch (Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}// END TRY CATCH BLOCK

	}

	/**
	 * THIS METHOD IS USED FOR CHECKING BRANCH WISE HOLIDAY FLAG
	 * 
	 * @param bean
	 */
	public void checkBranchWiseHoliday(ExtraWorkApplication bean) {
		try {
			String fiterquery = " SELECT DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false') BRN_HDAY_FLAG "
					+ " FROM HRMS_ATTENDANCE_CONF ";
			Object fitObj[][] = getSqlModel().getSingleResult(fiterquery);
			if (fitObj != null && fitObj.length > 0) {
				bean.setBrnHDayFlag(String.valueOf(fitObj[0][0]));
			}
		} catch (Exception e) {
			logger.error("Exception in checkBranchWiseHoliday method:  " + e);
		}
	}// end of checkBranchWiseHoliday

	/**
	 * THIS METHOD IS USED FOR CHECKING APPROVAL WORKFLOW FLAG
	 * 
	 * @param bean
	 */
	public void checkApprovalWorkFlow(ExtraWorkApplication bean) {
		try {
			// CHECK IF APPROVAL WORKFLOW IS DEFINED
			String workFlowQuery = "SELECT EXTRAWORK_FLAG FROM HRMS_SALARY_CONF";
			Object[][] workFlowObj = getSqlModel().getSingleResult(
					workFlowQuery);
			if (workFlowObj != null && workFlowObj.length > 0) {
				bean.setWorkFlowFlag(String.valueOf(workFlowObj[0][0]));
			}
		} catch (Exception e) {
			logger.error("Exception in checkApprovalWorkFlow method : " + e);
		}
	}// end of checkApprovalWorkFlow

	/**
	 * METHOD TO CHECK IF EXTRAWORK DATE FALLS ON HOLIDAY OR WEEKLY OFF
	 * 
	 * @param applDate
	 * @param application
	 * @return String
	 */
	public String checkIfHoliday(String applDate,
			ExtraWorkApplication application) {
		try {
			String extraWorkDay = "";
			Object[][] holidayObj = null;

			if (application.getBrnHDayFlag().equals("true")) {
				// CHECK IF APPL DATE IS HOLIDAY BRANCH WISE
				String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY'),HRMS_HOLIDAY.HOLI_TYPE FROM HRMS_HOLIDAY_BRANCH "
						+ " INNER JOIN HRMS_HOLIDAY ON(HRMS_HOLIDAY.HOLI_DATE =HRMS_HOLIDAY_BRANCH.HOLI_DATE )"
						+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ application.getEmpId()
						+ ")"
						+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE =TO_DATE('"
						+ applDate + "','DD-MM-YYYY') ";
				holidayObj = getSqlModel().getSingleResult(holStr);
				if (holidayObj != null && holidayObj.length > 0) {
					if (String.valueOf(holidayObj[0][1]).equals("H")) {
						extraWorkDay = "HLD";
					} else if (String.valueOf(holidayObj[0][1]).equals("N")) {
						extraWorkDay = "NHD";
					} else {
						extraWorkDay = "";
					}
				}

			} // end of if
			else {
				// IF NOT IN BRANCH WISE HOLIDAY, CHECK IN HOLIDAY
				String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),HOLI_TYPE FROM HRMS_HOLIDAY"
						+ " WHERE HOLI_DATE =TO_DATE('"
						+ applDate
						+ "','DD-MM-YYYY')";

				holidayObj = getSqlModel().getSingleResult(holStr);
				if (holidayObj != null && holidayObj.length > 0) {
					if (String.valueOf(holidayObj[0][1]).equals("H")) {
						extraWorkDay = "HLD";
					} else if (String.valueOf(holidayObj[0][1]).equals("N")) {
						extraWorkDay = "NHD";
					} else {
						extraWorkDay = "";
					}

				}

			} // end of else
			if (holidayObj == null || holidayObj.length == 0) {
				// FIND WEEK NO OF APPL DATE
				String weekNo = String.valueOf(getWeekOfMonth(applDate));
				// CHECK IF DAY IS WEEKLY OFF FROM SHIFT
				String weeklyOffDays = "";
				// FIND THE SHIFT OF EMPLOYEE
				String shiftQuery = " SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID = "
						+ application.getEmpId();
				Object[][] shiftObj = getSqlModel().getSingleResult(shiftQuery);
				// PASS WEEK NO AND FIND WEEKLY OFF DAYS FOR THAT EMPLOYEE
				String queryWeeklyOff = "SELECT  NVL(SHIFT_WEEK_" + weekNo
						+ ",0) " + " FROM HRMS_SHIFT WHERE SHIFT_ID ="
						+ shiftObj[0][0];
				Object weeklyOffObj[][] = getSqlModel().getSingleResult(
						queryWeeklyOff);
				if (weeklyOffObj != null && weeklyOffObj.length > 0) {// Z
					// WEEKLY OFF DAYS AS COMMA SEPARATED VALUES
					weeklyOffDays = String.valueOf(weeklyOffObj[0][0]);
					if (!weeklyOffDays.equals("")) {// Y
						// FIND APPLICATION DATE FALLS IN WHICH DAY OF WEEK
						String dayOfWeek = String
								.valueOf(getIntDayOfWeek(applDate));
						// SPLIT COMMA SEPARATED WEEKLY OFF VALUES
						String splitWeeklyOffDays[] = weeklyOffDays.split(",");
						for (int i = 0; i < splitWeeklyOffDays.length; i++) {// X
							// IF WEEKLY OFF DAY = DAY OF APPLICATION DATE
							if (splitWeeklyOffDays[i].equals(dayOfWeek)) {
								extraWorkDay = getDay(Integer
										.parseInt(dayOfWeek));
								break;
							} else
								extraWorkDay = "";
						}// END LOOP X
					}// END IF Y
				}// END IF Z
			}// END IF HOLIDAYOBJ NULL OR ZERO
			return extraWorkDay;
		} catch (Exception e) {
			logger.error("Exception in checkIfHoliday : " + e);
			e.printStackTrace();
			return "";
		}// END TRY CATCH BLOCK

	}// end of checkIfHoliday

	public String checkShiftHours(ExtraWorkApplication application,
			String shiftWorkingHours) {

		String result = "";
		try {
			int check = 0;
			String[] startTime = application.getStartTime().split(":");
			String[] endTime = application.getEndTime().split(":");
			String[] diffHours = new String[2];
			diffHours[0] = String.valueOf(Integer.parseInt(endTime[0])
					- Integer.parseInt(startTime[0]));
			if (Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]) >= 0) {
				diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
						- Integer.parseInt(startTime[1]));
			} else {
				diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
						+ Integer.parseInt(startTime[1]));
				diffHours[0] = String
						.valueOf(Integer.parseInt(diffHours[0]) - 1);
			}
			String timeDifference = diffHours[0] + ":" + diffHours[1];

			long startTimeCheck = getMinute(shiftWorkingHours);
			long endTimeCheck = getMinute(timeDifference);
			if (application.getType().equals("F") && endTimeCheck < 0) {
				check = 1;
			} else if (application.getType().equals("H")
					&& (endTimeCheck / 2) < 0) {
				check = 1;
			}

			String calculateTimeDiffQuery = "  SELECT TRIM(FLOOR(((TO_DATE('"
					+ application.getPrDate() + "-" + application.getEndTime()
					+ "','dd-mm-yyyy-HH24:MI')+" + check + " -TO_DATE('"
					+ application.getPrDate() + "-"
					+ application.getStartTime()
					+ "','dd-mm-yyyy-HH24:MI'))*24*60*60)/3600)"
					+ "  || ':' || " + "  FLOOR((((TO_DATE('"
					+ application.getPrDate() + "-" + application.getEndTime()
					+ "','dd-mm-yyyy-HH24:MI')-TO_DATE('"
					+ application.getPrDate() + "-"
					+ application.getStartTime()
					+ "','dd-mm-yyyy-HH24:MI'))*24*60*60) - "
					+ "   FLOOR(((TO_DATE('" + application.getPrDate() + "-"
					+ application.getEndTime()
					+ "','dd-mm-yyyy-HH24:MI')-TO_DATE('"
					+ application.getPrDate() + "-"
					+ application.getStartTime()
					+ "','dd-mm-yyyy-HH24:MI'))*24*60*60)/3600)*3600)/60)"
					+ " )TIME_DIFFERENCE FROM DUAL ";

			System.out.println("calculateTimeDiffQuery-----------"
					+ calculateTimeDiffQuery);

			Object timeDiffObj[][] = getSqlModel().getSingleResult(
					calculateTimeDiffQuery);

			long startEndTimeDiff = 0;
			long actualShiftHrs = 0;

			if (timeDiffObj != null && timeDiffObj.length > 0) {
				startEndTimeDiff = getMinute(String.valueOf(timeDiffObj[0][0]));
				actualShiftHrs = getMinute(shiftWorkingHours);
				if (application.getType().equals("F")) {
					if (!(startEndTimeDiff >= actualShiftHrs)) {
						result = "fullDayViolation";
					}
				} else if (application.getType().equals("H")) {
					if (!((startEndTimeDiff) >= (actualShiftHrs / 2))) {
						result = "halfDayViolation";
					}
				} else {
					
					/*if (!((startEndTimeDiff) >= (actualShiftHrs / 2))) {
						result += "partialForExtraDayPayViolation";
					}*/
					result = "";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result;
		}
		return result;
	}

	public String checkShiftHours_old(ExtraWorkApplication application,
			String shiftWorkingHours) {
		try {
			String[] startTime = application.getStartTime().split(":");
			String[] endTime = application.getEndTime().split(":");
			String[] diffHours = new String[2];
			diffHours[0] = String.valueOf(Integer.parseInt(endTime[0])
					- Integer.parseInt(startTime[0]));
			if (Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]) >= 0) {
				diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
						- Integer.parseInt(startTime[1]));
			} else {
				diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
						+ Integer.parseInt(startTime[1]));
				diffHours[0] = String
						.valueOf(Integer.parseInt(diffHours[0]) - 1);
			}
			String timeDifference = diffHours[0] + ":" + diffHours[1];

			long startTimeCheck = getMinute(shiftWorkingHours);
			long endTimeCheck = getMinute(timeDifference);
			long diffHoursCheck = endTimeCheck - startTimeCheck;
			if (application.getType().equals("F") && diffHoursCheck < 0) {
				return "fullDayViolation";
			} else if (application.getType().equals("H")
					&& (endTimeCheck - (startTimeCheck / 2)) < 0) {
				return "halfDayViolation";
			} else
				return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public int getMinute(String result) {
		int min = 0;
		try {

			if (result == null || result.equals("null") || result.equals("")) {
				return min;
			}// end of if
			else {
				if (result.contains(":")) {
					String[] chk = result.split(":");
					try {
						min = Integer.parseInt(chk[0]) * 60;
						min = min + Integer.parseInt(chk[1]);
					} catch (Exception e) {
					}
				}
			}// end of else
		} catch (Exception e) {
			e.printStackTrace();

		}

		return min;
	}

	/**
	 * Get day of week number
	 * 
	 * @param day
	 * @return String
	 */
	public String getDay(int day) {
		switch (day) {
		case 1:
			return "SUN";
		case 2:
			return "MON";
		case 3:
			return "TUE";
		case 4:
			return "WED";
		case 5:
			return "THU";
		case 6:
			return "FRI";
		case 7:
			return "SAT";
		}
		return "";
	}

	/**
	 * Get which day of week, date falls in ?
	 * 
	 * @param date
	 * @return Integer value
	 */
	public int getIntDayOfWeek(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(Utility.getDate(date));
		int day = c.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	/**
	 * Get which week of month date falls in
	 * 
	 * @param date
	 * @return Integer value
	 */
	public int getWeekOfMonth(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(Utility.getDate(date));
		return c.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * Get which day, date falls in
	 * 
	 * @param date
	 * @return String
	 */
	public String getDayFromDate(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(Utility.getDate(date));
		int day = c.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case 1:
			return "SUN";
		case 2:
			return "MON";
		case 3:
			return "TUE";
		case 4:
			return "WED";
		case 5:
			return "THU";
		case 6:
			return "FRI";
		case 7:
			return "SAT";
		}
		return "";
	}

	/**
	 * Generating report
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, ExtraWorkApplication bean) {
		try {
			String sqlQuery = " SELECT DECODE(EXTRAWORK_APPL_STATUS,'D','Draft','B','Sent Back','P','Pending','R','Rejected','A','Approved','F','Forwarded','W','Withdrawn'), NVL(EXTRAWORK_APPL_COMMENTS,' ') "
					+ " FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE = "
					+ bean.getCompId();
			Object[][] statusRes = getSqlModel().getSingleResult(sqlQuery);
			String s = "\n Extra Work Benefits Report\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", s);
			// EXTRA WORK BENEFIT DETAILS
			String query = " SELECT ROWNUM, TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'), DECODE(EXTRAWORK_DAY_TYPE,'F','Full Day','H','Half Day','O','Partial Day'), "
					+ " EXTRAWORK_STARTTIME, EXTRAWORK_ENDTIME, EXTRAWORK_PURPOSE FROM HRMS_EXTRAWORK_APPL_DTL WHERE EXTRAWORK_APPL_CODE = "
					+ bean.getCompId();
			Object[][] result = getSqlModel().getSingleResult(query);

			// APPROVER DETAILS
			String approver = " SELECT EMP_TOKEN, (HRMS_TITLE.TITLE_NAME || ' ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ||' ') AS EMP_NAME,TO_CHAR(EXTRAWORK_APPROVE_DATE,'DD-MM-YYYY'), "
					+ " EXTRAWORK_APPROVER_REMARK AS REMARK, DECODE(EXTRAWORK_APPROVER_STATUS, 'D','Draft','B','Sent Back','P','Pending','R','Rejected','A','Approved','F','Forwarded','W','Withdrawn') FROM HRMS_EXTRAWORK_APPL_PATH  "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPROVER = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) WHERE EXTRAWORK_APPL_CODE = "
					+ bean.getCompId()
					+ " UNION "
					+ " SELECT EMP_TOKEN, (HRMS_TITLE.TITLE_NAME || ' ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME || ' '), '',"
					+ " CAST('' AS NVARCHAR2(100)) a , DECODE(EXTRAWORK_APPL_STATUS, 'D','Draft','B','Sent Back','P','Pending','R','Rejected','A','Approved','F','Forwarded','W','Withdrawn') FROM HRMS_EXTRAWORK_APPL_HDR  "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_APPROVER = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					+ " WHERE EXTRAWORK_APPL_STATUS = 'P' AND EXTRAWORK_APPL_CODE = "
					+ bean.getCompId();
			Object[][] approverData = getSqlModel().getSingleResult(approver);
			int j = 1;
			Object[][] approvalTable = new Object[approverData.length][8];
			for (int i = 0; i < approverData.length; i++) {
				approvalTable[i][0] = String.valueOf(j++);
				approvalTable[i][1] = checkNull(String
						.valueOf(approverData[i][0]));
				approvalTable[i][2] = checkNull(String
						.valueOf(approverData[i][1]));
				approvalTable[i][3] = checkNull(String
						.valueOf(approverData[i][2]));
				approvalTable[i][4] = checkNull(String
						.valueOf(approverData[i][3]));
				approvalTable[i][5] = checkNull(String
						.valueOf(approverData[i][4]));
				approvalTable[i][6] = "";
			}
			Object data[][] = new Object[4][4];
			data[0][0] = "Employee Id. :";
			data[0][1] = bean.getEmpToken();
			data[0][2] = "Employee Name :";
			data[0][3] = bean.getEmpName();
			data[1][0] = "Branch :";
			data[1][1] = bean.getBranchName();
			data[1][2] = "Department :";
			data[1][3] = bean.getDept();
			data[2][0] = "Designation :";
			data[2][1] = bean.getDesg();
			data[2][2] = "Application Date :";
			data[2][3] = bean.getAppDate();
			data[3][0] = "Status :";
			data[3][1] = String.valueOf(statusRes[0][0]);
			data[3][2] = "Comments :";
			data[3][3] = String.valueOf(statusRes[0][1]);
			int[] bcellWidth = { 20, 30, 20, 30 };
			int[] bcellAlign = { 0, 0, 0, 0 };
			rg.addFormatedText(s, 6, 0, 1, 0);
			rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
			rg.addFormatedText("Extra Work Details : \n ", 6, 0, 0, 0);
			String colnames[] = { "Sr.No", "Extra Work Date",
					"Extra Work Type", "Start Time", "End Time", "Purpose " };
			int cellwidth[] = { 10, 15, 15, 15, 15, 40 };
			int alignment[] = { 1, 0, 0, 0, 0, 0 };
			String appCol[] = { "Sr. No", "Approver Id", "Approver Name",
					"Date", "Remarks", "Status", "Signature" };
			int appCell[] = { 5, 10, 25, 10, 30, 10, 12 };
			int apprAlign[] = { 1, 1, 0, 1, 0, 0, 0 };
			rg.tableBody(colnames, result, cellwidth, alignment);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("Approver Details :", 6, 0, 0, 0);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.tableBody(appCol, approvalTable, appCell, apprAlign);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("__________________", 1, 0, 0, 3);
			rg.addFormatedText("", 1, 0, 0, 3);
			rg.addFormatedText("Employee Signature", 1, 0, 0, 3);
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}// END TRY CATCH BLOCK
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	// METHODS FOR APPROVAL
	/**
	 * THIS METHOD IS USED FOR DISPLAYING EMPLOYEE INFORMARTION
	 * 
	 * @param leaveApp
	 * @return String
	 */
	public String showEmp(ExtraWorkApplication bean) {
		try {
			Object[] emp = new Object[1];
			emp[0] = bean.getEmpId();
			logger.info("Employee Id .......... " + emp[0]);
			Object[][] empdata = getSqlModel()
					.getSingleResult(getQuery(8), emp);
			bean.setEmpToken(String.valueOf(empdata[0][0]));// employee token
			bean.setEmpName(String.valueOf(empdata[0][1]));// employee name
			bean.setBranchName(String.valueOf(empdata[0][2]));// branch
			logger.info("Branch .......... " + empdata[0][2]);
			bean.setDept(String.valueOf(empdata[0][3]));// department
			logger.info("Department .......... " + empdata[0][3]);
			bean.setDesg(String.valueOf(empdata[0][4]));// designation
			logger.info("Designation .......... " + empdata[0][4]);
			bean.setEmpId(String.valueOf(empdata[0][5]));// employee id
		} catch (Exception e) {
			logger.error("Exception in showEmp--------- " + e);
		}// END TRY CATCH BLOCK
		return "success";

	}// end of showEmp

	/**
	 * THIS METHOD IS USED FOR APPROVER DETAILS
	 * 
	 * @param bean
	 */
	public boolean setApproverDetails(ExtraWorkApplication bean) {
		boolean isRecordInPath = false;
		try {
			String approverQuery = "  SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '), "
					+ " TO_CHAR(HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPROVE_DATE,'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPROVER_REMARK "
					+ " FROM HRMS_EXTRAWORK_APPL_PATH  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPROVER= HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
					+ " WHERE HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPL_CODE ="
					+ bean.getCompId()
					+ " ORDER BY HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPROVE_DATE ";
			Object[][] approverDataObj = getSqlModel().getSingleResult(
					approverQuery);
			ArrayList<Object> apprList = new ArrayList<Object>();
			if (approverDataObj != null && approverDataObj.length != 0) {// y
				isRecordInPath = true;
				for (int i = 0; i < approverDataObj.length; i++) {// x

					ExtraWorkApplication extBean = new ExtraWorkApplication();
					extBean.setApprName(checkNull(String
							.valueOf(approverDataObj[i][1])));
					extBean.setApprDate(checkNull(String
							.valueOf(approverDataObj[i][2])));
					if (String.valueOf(approverDataObj[i][3]).equals("null")
							|| String.valueOf(approverDataObj[i][3]) == null) {
						extBean.setApprComments("");
					}// end of if
					else {

						extBean.setApprComments(checkNull(String
								.valueOf(approverDataObj[i][3])));
					}// end of else
					apprList.add(extBean);
				}// end of for loop x

			}// end of if y
			bean.setApproveList(apprList);
		} catch (Exception e) {
			logger.error("Exception in setApproverDetails :  " + e);
		}// END TRY CATCH BLOCK
		return isRecordInPath;
	}// end of setApproverDetails

	// INTEGRATION OF EMAIL TEMPLATES
	public void generateMailTemplate(ExtraWorkApplication bean,
			HttpServletRequest request, Object[][] empFlow) {

		try {
			//for removing draft entry
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(String.valueOf(bean.getCompId()),
					"ExtraWork");

			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' '), MODULE_NAME FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " LEFT JOIN HRMS_MODULE ON (HRMS_MODULE.MODULE_CODE = HRMS_MAIL_EVENTS.EVENT_MODULE_CODE) "
					+ " WHERE EVENT_CODE=74";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);
			String templateCode = "";
			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("N")) {
					logger.info("Event Option Flage is False.");
					return;
				}

				if (tempData[0][1] != null
						&& !String.valueOf(tempData[0][1]).equals("")) {
					templateCode = String.valueOf(tempData[0][1]);
				} else {
					logger.info("Template is not Defined." + templateCode);
					return;
				}

			} else {
				logger.info("Event Template is not Defined.");
				return;
			}
			String query = "SELECT EMP_ID, EXTRAWORK_APPL_APPROVER, EXTRAWORK_APPL_CODE FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE="
					+ bean.getCompId();
			Object queryObj[][] = getSqlModel().getSingleResult(query);

			String msgType = "A";
			String applicantID = String.valueOf(queryObj[0][0]);
			String approverID = String.valueOf(empFlow[0][0]);
			String module = String.valueOf(tempData[0][4]);
			String applnID = String.valueOf(queryObj[0][2]);
			String level = "1";

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(String.valueOf(tempData[0][3]));
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, applicantID);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, approverID);

			// Subject + Body
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, approverID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);
			templateQuery5.setParameter(2, applicantID);

			// Add approval link -pass parameters to the link
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			String applicationType = "ExtraWorkAppl";

			link_param[0] = applicationType + "#" + applicantID + "#" + applnID
					+ "#" + "A" + "#" + "..." + "#" + approverID + "#" + level;
			link_param[1] = applicationType + "#" + applicantID + "#" + applnID
					+ "#" + "R" + "#" + "..." + "#" + approverID + "#" + level;
			link_param[2] = applicationType + "#" + applicantID + "#" + applnID
					+ "#" + "B" + "#" + "..." + "#" + approverID + "#" + level;
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			template.configMailAlert();

			String[] keep = request.getParameterValues("keepInformedEmpId");

			 
		 

			String alertLink = "/extraWorkBenefits/ExtraWorkApplication_retrieveForApproval.action";
			String linkParam = "extWrkApplicationNo=" + bean.getCompId() + "&"
					+ "applicationStatus=P";
			String approverId = approverID;
			try {
				template.sendProcessManagerAlert(approverId, "ExtraWork", "A",
						bean.getCompId(), level, linkParam, alertLink,
						"Pending", applicantID, String.valueOf(empFlow[0][3]),
						"", "", applicantID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 

			// create process alerts
			//template.sendProcessManagerAlert(approverID, module, msgType,applnID, level);

			if (keep != null) {
				template.sendApplicationMailToKeepInfo(keep);
			}

			template.addOnlineLink(request, link_param, link_label);

			// send mail
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

			generateMailTemplateForAutoResponse(bean, empFlow,request);
		} catch (Exception e) {
			logger.error("generateMailTemplate" + e);
		}// END TRY CATCH BLOCK

	}

	public void generateMailTemplateForAutoResponse(ExtraWorkApplication bean,
			Object[][] empFlow,HttpServletRequest request) {
		try {

			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' '), MODULE_NAME FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " LEFT JOIN HRMS_MODULE ON (HRMS_MODULE.MODULE_CODE = HRMS_MAIL_EVENTS.EVENT_MODULE_CODE) "
					+ " WHERE EVENT_CODE=75";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);
			String templateCode = "";
			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("N")) {
					logger.info("Event Option Flage is False.");
					return;
				}

				if (tempData[0][1] != null
						&& !String.valueOf(tempData[0][1]).equals("")) {
					templateCode = String.valueOf(tempData[0][1]);
				} else {
					logger.info("Template is not Defined." + templateCode);
					return;
				}

			} else {
				logger.info("Event Template is not Defined.");
				return;
			}// END IF ELSE

			String query = "SELECT EMP_ID, EXTRAWORK_APPL_APPROVER, EXTRAWORK_APPL_CODE FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE="
					+ bean.getCompId();
			Object queryObj[][] = getSqlModel().getSingleResult(query);

			String applicantID = String.valueOf(queryObj[0][0]);
			String approverID = String.valueOf(empFlow[0][0]);
			String module = String.valueOf(tempData[0][4]);
			String applnID = String.valueOf(queryObj[0][2]);
			String level = "1";

			/*
			 * Mail to employee regarding EXTRA WORK application submission
			 */

			EmailTemplateBody template_applicant = new EmailTemplateBody();
			template_applicant.initiate(context, session);

			template_applicant.setEmailTemplate(String.valueOf(tempData[0][3]));
			template_applicant.getTemplateQueries();

			EmailTemplateQuery templateQueryApp1 = template_applicant
					.getTemplateQuery(1); // FROM EMAIL
			EmailTemplateQuery templateQueryApp2 = template_applicant
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, applicantID);

			// Subject + Body
			EmailTemplateQuery templateQueryApp3 = template_applicant
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applnID);

			EmailTemplateQuery templateQueryApp4 = template_applicant
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, approverID);

			EmailTemplateQuery templateQueryApp5 = template_applicant
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applnID);
			templateQueryApp5.setParameter(2, applicantID);
			template_applicant.configMailAlert();

			// create process alerts
			/*template_applicant.sendProcessManagerAlert(applicantID, module,
					"I", applnID, level);*/
			// send mail
			
			
			String[] keep = request.getParameterValues("keepInformedEmpId");

			String alertCCId = "0";

			if (keep != null) {
				for (int i = 0; i < keep.length; i++) {
					if (i == keep.length) {
						alertCCId += keep[i];
					} else {
						alertCCId += "," + keep[i];
					}
				}
			}
			
			try {
		 
			String link = "/extraWorkBenefits/ExtraWorkApplication_retrieveDetails.action";
			
			String linkParam ="extWrkApplCode=" + applnID + "&extWrkStatus=P";
				template_applicant
						.sendProcessManagerAlert("", "ExtraWork", "I", applnID,
								level, linkParam, link, "Pending", applicantID,
								"", alertCCId, applicantID, applicantID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			template_applicant.sendApplicationMail();
			template_applicant.clearParameters();
			template_applicant.terminate();

		} catch (Exception e) {
			logger.error("Error in generateMailTemplateForAutoResponse : " + e);
		}// END TRY CATCH BLOCK

	}

	/**
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param empFlow
	 * @param hsType
	 * @param status
	 * @param day
	 * @return boolean after inserting a record
	 */
	public boolean saveForNoApprover(ExtraWorkApplication bean,
			Object[] hprojName, Object[] hDate, Object[] hsTime,
			Object[] heTime, Object[] hsType, String[] empCode, Object[] day,
			String status) {

		boolean data = false;
		String compIDSql = " SELECT NVL(MAX(EXTRAWORK_APPL_CODE), 0) + 1 FROM HRMS_EXTRAWORK_APPL_HDR ";
		Object[][] compIDObj = getSqlModel().getSingleResult(compIDSql);
		bean.setCompId(String.valueOf(compIDObj[0][0]));
		Object[][] addObj = new Object[1][7];
		addObj[0][0] = String.valueOf(compIDObj[0][0]);
		addObj[0][1] = bean.getEmpId();
		addObj[0][2] = bean.getAppDate();
		// APPROVER ID
		addObj[0][3] = bean.getEmpId();
		addObj[0][4] = bean.getComments();
		// ALTERNATE APPROVER ID
		addObj[0][5] = "";
		logger.info("Status....................." + status);
		if (status.equals("D"))
			addObj[0][6] = "D";
		else
			addObj[0][6] = "A";
		// SAVE HEADER DETAILS
		boolean result = getSqlModel().singleExecute(getQuery(3), addObj);
		String type = "";
		if (result) {
			// SAVE KEEP INFORMED LIST VALUES
			saveKeepInformedList(empCode, bean);
			String maxQuery = " SELECT MAX(EXTRAWORK_APPL_CODE) FROM HRMS_EXTRAWORK_APPL_HDR";
			Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
			for (int i = 0; i < hprojName.length; i++) {
				if (hsType[i].equals("Full Day"))
					type = "F";
				else if (hsType[i].equals("Half Day"))
					type = "H";
				else if (hsType[i].equals("Partial Day"))
					type = "O";
				Object addItem[][] = new Object[1][9];
				addItem[0][0] = String.valueOf(maxObj[0][0]);
				addItem[0][1] = String.valueOf(hprojName[i]);
				addItem[0][2] = String.valueOf(hDate[i]);
				addItem[0][3] = String.valueOf(hsTime[i]);
				addItem[0][4] = String.valueOf(heTime[i]);
				addItem[0][5] = type;
				addItem[0][6] = bean.getEmpId();
				if (status.equals("D"))
					addItem[0][7] = "D";
				else
					addItem[0][7] = "A";
				addItem[0][8] = String.valueOf(day[i]);
				// SAVE DETAILS IF HEADER SAVED
				data = getSqlModel().singleExecute(getQuery(4), addItem);
			}// END LOOP
		}// END IF HEADER SAVED
		if (data == true & result == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param empFlow
	 * @param hsType
	 * @param status
	 * @param day
	 * @return boolean after updating a record
	 */
	public boolean updateForNoApprover(ExtraWorkApplication bean,
			Object[] hprojName, Object[] hDate, Object[] hsTime,
			Object[] heTime, Object[] hsType, String[] empCode, Object[] day,
			String status) {
		try {
			boolean flag = false;
			boolean result = false;
			Object[][] addObj = new Object[1][7];
			addObj[0][0] = bean.getEmpId();
			addObj[0][1] = bean.getAppDate();
			addObj[0][2] = bean.getEmpId();
			addObj[0][3] = bean.getComments();
			addObj[0][4] = "";
			if (status.equals("D"))
				addObj[0][5] = "D";
			else
				addObj[0][5] = "A";
			addObj[0][6] = bean.getCompId();
			// UPDATES HEADER TABLE
			boolean data = getSqlModel().singleExecute(getQuery(5), addObj);
			String type = "";
			if (data) {
				// SAVE KEEP INFORMED LIST
				saveKeepInformedList(empCode, bean);
				if (hprojName != null) {
					// DELETE FROM DETAIL TABLE
					String query = " DELETE FROM  HRMS_EXTRAWORK_APPL_DTL WHERE EXTRAWORK_APPL_CODE = "
							+ bean.getCompId();
					flag = getSqlModel().singleExecute(query);
				}
				if (flag) {
					// IF DELETE INSERT INTO DETAIL TABLE
					for (int i = 0; i < hprojName.length; i++) {
						if (hsType[i].equals("Full Day"))
							type = "F";
						else if (hsType[i].equals("Half Day"))
							type = "H";
						else if (hsType[i].equals("Partial Day"))
							type = "O";
						Object addItem[][] = new Object[1][9];
						addItem[0][0] = bean.getCompId();
						addItem[0][1] = String.valueOf(hprojName[i]);
						addItem[0][2] = String.valueOf(hDate[i]);
						addItem[0][3] = String.valueOf(hsTime[i]);
						addItem[0][4] = String.valueOf(heTime[i]);
						addItem[0][5] = type;
						addItem[0][6] = bean.getEmpId();
						if (status.equals("D"))
							addItem[0][7] = "D";
						else
							addItem[0][7] = "A";
						addItem[0][8] = String.valueOf(day[i]);
						result = getSqlModel().singleExecute(getQuery(6),
								addItem);
					}// END LOOP
				}// END IF FLAG
			}
			if (data == true & flag == true & result == true) {
				return true;
			} else {
				return false;
			}// END IF ELSE BLOCK
		} catch (Exception e) {
			logger.error(e);
			return false;
		} // end of try catch block
	}

	public String checkShiftTimings(ExtraWorkApplication application,
			String valueOf, Object[][] shiftObj, boolean isExceptionalShift) {
		// TODO Auto-generated method stub

		String result = "";
		String shiftStartTmEndTmQuery = "";
		try {

			if (isExceptionalShift) {
				String dayOfWeek = String.valueOf(getIntDayOfWeek(application
						.getPrDate()));
				shiftStartTmEndTmQuery = "TO_CHAR(SFT_DTL_START_TIME, 'HH24:MI') AS SFT_DTL_START_TIME,TO_CHAR(SFT_DTL_END_TIME, 'HH24:MI') AS SFT_DTL_END_TIME"
						+ " WHERE SHIFT_ID="
						+ shiftObj[0][0]
						+ " AND SFT_DTL_EXCEPTIONAL_DAY="
						+ Integer.parseInt(dayOfWeek);
			} else {

				shiftStartTmEndTmQuery = " SELECT TO_CHAR(SFT_START_TIME, 'HH24:MI'), TO_CHAR(SFT_END_TIME, 'HH24:MI')"
						+ " FROM HRMS_SHIFT WHERE SHIFT_ID=" + shiftObj[0][0];

			}

			Object shiftHrsObj[][] = getSqlModel().getSingleResult(
					shiftStartTmEndTmQuery);

			String shiftStartTime = String.valueOf(shiftHrsObj[0][0]).replace(
					":", "");
			;
			String shiftEndTime = String.valueOf(shiftHrsObj[0][1]).replace(
					":", "");
			;
			long shiftStart_Time = Long.parseLong(shiftStartTime);
			long shiftEnd_Time = Long.parseLong(shiftEndTime);

			System.out.println("shiftStart_Time------------------"
					+ shiftStart_Time);
			System.out.println("shiftEnd_Time----------------------"
					+ shiftEnd_Time);

			String startTime = application.getStartTime().replace(":", "");
			;
			String endTime = application.getEndTime().replace(":", "");
			;
			long start_Time = Long.parseLong(startTime);

			long end_Time = Long.parseLong(endTime);

			if (start_Time > shiftStart_Time) {
				end_Time = Long.parseLong(endTime) + 2400;
			}

			System.out.println("start_Time------------------" + start_Time);
			System.out.println("end_Time----------------------" + end_Time);

			/*
			 * shiftStart_Time------------------900
			 * shiftEnd_Time----------------------1800
			 * start_Time------------------800
			 * end_Time----------------------2100
			 */

			/*
			 * if((start_Time < shiftStart_Time ||start_Time > shiftEnd_Time)&&
			 * (end_Time <shiftStart_Time ||end_Time > shiftEnd_Time)) {
			 * System.out.println("In if loop------------------"); }
			 */
			/*
			 * if((start_Time < shiftStart_Time || start_Time > shiftEnd_Time)&&
			 * (end_Time <shiftStart_Time ||end_Time > shiftEnd_Time)) {
			 * System.out.println("In if loop------------------"); }
			 */

			/*
			 * shiftStart_Time------------------930
			 * shiftEnd_Time----------------------1830
			 * start_Time------------------1900
			 * end_Time----------------------700
			 */

			if ((start_Time <= shiftStart_Time && end_Time <= shiftStart_Time)
					|| (start_Time >= shiftEnd_Time && end_Time >= shiftEnd_Time)) {
				System.out
						.println("In if vishwambhar logic's loop loop------------------");
			}
			/*
			 * else if((end_Time <= shiftStart_Time && start_Time
			 * <=shiftStart_Time)||(end_Time >= shiftEnd_Time && start_Time >=
			 * shiftEnd_Time)) { System.out.println("In if vishwambhar logic's
			 * loop loop-------erwrre-----------"); }
			 */
			else {
				System.out.println("In else loop------------------");
				result = "You can not apply between your shift timings.";
				return result;

			}

		} catch (Exception e) {
			result = "";
		}

		return result;
	}

	
}
