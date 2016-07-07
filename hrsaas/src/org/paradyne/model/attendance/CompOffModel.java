package org.paradyne.model.attendance;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.attendance.CompOff;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.attendance.CompOffApprovalAction;

/**
 * @author balajim date 06-08-2008
 */
public class CompOffModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CompOffModel.class);
	/**
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 */
	/*
	 * method for adding the compoff details in the iterator table
	 */
	public void add(CompOff bean, Object[] hprojName, Object[] hDate, Object[] hsTime, Object[] heTime) {
		try {
			ArrayList<Object> compList = new ArrayList<Object>();

			if(hprojName != null) {
				for(int i = 0; i < hprojName.length + 1; i++) {
					CompOff bean1 = new CompOff();
					if(i < hprojName.length) {

						bean1.setHprojName(String.valueOf(hprojName[i]));
						bean1.setHDate(String.valueOf(hDate[i]));
						bean1.setHsTime(String.valueOf(hsTime[i]));
						bean1.setHeTime(String.valueOf(heTime[i]));
						compList.add(bean1);
						bean.setCompList(compList);
					} else {
						bean1.setHprojName(bean.getPrName());
						bean1.setHDate(bean.getPrDate());
						bean1.setHsTime(bean.getStartTime());
						bean1.setHeTime(bean.getEndTime());
						compList.add(bean1);
						bean.setCompList(compList);

					} // end of else statement
				} // end of for loop
			} else {
				CompOff bean1 = new CompOff();

				bean1.setHprojName(bean.getPrName());
				bean1.setHDate(bean.getPrDate());
				bean1.setHsTime(bean.getStartTime());
				bean1.setHeTime(bean.getEndTime());
				compList.add(bean1);
				bean.setCompList(compList);
			}
		} catch(Exception e) {
			logger.error(e);
		}// end of try catch block
	}

	/**
	 * @param bean
	 * @param cmpId
	 */
	public void callDtl(CompOff bean, String cmpId) {
		try {
			String sql = " SELECT COMP_STATUS, NVL(COMP_COMMENTS,' ') FROM HRMS_COMPOFF_HDR WHERE COMP_ID = " + cmpId;
			Object[][] result = getSqlModel().getSingleResult(sql);
			if(result.length > 0) {
				bean.setStatus(String.valueOf(result[0][0]));
				bean.setComments(String.valueOf(result[0][1]).trim());
			}
		} catch(Exception e) {
			logger.error(e);
		} // end of try catch block...!

		ArrayList<Object> compList = new ArrayList<Object>();
		String query = " SELECT COMPDTL_PROJECT, TO_CHAR(COMPDTL_PROJECTDATE, 'DD-MM-YYYY'), COMPDTL_STARTTIME, COMPDTL_ENDTIME " +
		" FROM HRMS_COMPOFF_DTL " + " WHERE COMPDTL_COMPID = " + cmpId;
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data.length > 0) {
			for(int i = 0; i < data.length; i++) {
				CompOff bean1 = new CompOff();
				bean1.setHprojName(String.valueOf(data[i][0]));
				bean1.setHDate(String.valueOf(data[i][1]));
				bean1.setHsTime(String.valueOf(data[i][2]));
				bean1.setHeTime(String.valueOf(data[i][3]));
				compList.add(bean1);
			}
			bean.setCompList(compList);
			bean.setIteratorFlag("true");
		}
	}

	public void checked(CompOff bean, Object[] hprojName, Object[] hDate, Object[] hsTime, Object[] heTime) {
		try {
			ArrayList<Object> compList = new ArrayList<Object>();

			if(hprojName != null) {
				for(int i = 0; i < hprojName.length + 1; i++) {
					CompOff bean1 = new CompOff();
					if(i < hprojName.length) {
						bean1.setHprojName(String.valueOf(hprojName[i]));
						bean1.setHDate(String.valueOf(hDate[i]));
						bean1.setHsTime(String.valueOf(hsTime[i]));
						bean1.setHeTime(String.valueOf(heTime[i]));
						compList.add(bean1);
						bean.setCompList(compList);
					}
				} // end of for loop
			}
		} catch(Exception e) {
			logger.error(e);
		} // end of try catch block
	}

	public boolean checkForward(CompOff bean) {
		String sql = " SELECT COMP_STATUS FROM HRMS_COMPOFF_HDR WHERE  COMP_STATUS = 'P' AND COMP_LEVEL = 1 AND COMP_ID =" + bean.getCompId();
		Object[][] result = getSqlModel().getSingleResult(sql);
		if(result.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param bean
	 * @return boolean after deleting record
	 */
	public boolean delete(CompOff bean) {
		boolean result = false;
		boolean data = false;

		String empquery = "Select COMP_EMPID,COMP_APPROVER FROM HRMS_COMPOFF_HDR WHERE COMP_ID = " + bean.getCompId();

		Object obj[][] = getSqlModel().getSingleResult(empquery);
		String sql = " DELETE FROM HRMS_COMPOFF_DTL WHERE COMPDTL_COMPID = " + bean.getCompId();
		data = getSqlModel().singleExecute(sql);

		String query = " DELETE FROM HRMS_COMPOFF_HDR WHERE COMP_ID = " + bean.getCompId();
		result = getSqlModel().singleExecute(query);
		if(data == true & result == true) {
			try {
				Object[][] to_mailIds = new Object[1][1];
				Object[][] from_mailIds = new Object[1][1];

				to_mailIds[0][0] = String.valueOf(obj[0][1]);
				from_mailIds[0][0] = String.valueOf(obj[0][0]);

				MailUtility mail = new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds, "CompOff", "", "D");
				mail.terminate();
			} catch(Exception e) {
				logger.error(e);
			}
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
	 * @param serialNo
	 */
	/*
	 * method for deleting record
	 */
	public void delItem(CompOff bean, Object[] hprojName, Object[] hDate, Object[] hsTime, Object[] heTime, String serialNo) {
		try {
			ArrayList<Object> compList = new ArrayList<Object>();
			for(int i = 0; i < hprojName.length + 1; i++) {
				CompOff bean1 = new CompOff();
				if(i < hprojName.length) {
					bean1.setHprojName(String.valueOf(hprojName[i]));
					bean1.setHDate(String.valueOf(hDate[i]));
					bean1.setHsTime(String.valueOf(hsTime[i]));
					bean1.setHeTime(String.valueOf(heTime[i]));
					compList.add(bean1);
				}
			}
			compList.remove(Integer.parseInt(serialNo));
			bean.setCompList(compList);
		} catch(Exception e) {
			logger.error(e);
		} // end of try catch block
	}

	/**
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 */
	/*
	 * function for editing the record
	 */
	public void edit(CompOff bean, Object[] hprojName, Object[] hDate, Object[] hsTime, Object[] heTime) {
		try {
			ArrayList<Object> compList = new ArrayList<Object>();

			if(hprojName != null) {
				for(int i = 0; i < hprojName.length; i++) {
					CompOff bean1 = new CompOff();

					if(i == Integer.parseInt(bean.getCheckEdit())) {
						bean1.setHprojName(bean.getPrName());
						bean1.setHDate(bean.getPrDate());
						bean1.setHsTime(bean.getStartTime());
						bean1.setHeTime(bean.getEndTime());
						compList.add(bean1);
					} else {
						bean1.setHprojName(String.valueOf(hprojName[i]));
						bean1.setHDate(String.valueOf(hDate[i]));
						bean1.setHsTime(String.valueOf(hsTime[i]));
						bean1.setHeTime(String.valueOf(heTime[i]));
						compList.add(bean1);
					}
					bean.setCompList(compList);
				}
			}

		} catch(Exception e) {
			logger.error(e);
		}
		// end of try catch block
	}

	/**
	 * @param empId
	 * @param bean
	 */
	/*
	 * method for setting General employee details...!
	 */
	public void getEmployeeDetails(String empId, CompOff bean) {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, CENTER_NAME, DEPT_NAME, RANK_NAME, " +
		" EMP_ID FROM HRMS_EMP_OFFC " +
		" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) " +
		" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) " +
		" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) " + 
		" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) " +
		" WHERE EMP_ID = " + empId;
		Object[][] empdata = getSqlModel().getSingleResult(query);
		bean.setEmpToken(String.valueOf(empdata[0][0]));
		bean.setEmpName(String.valueOf(empdata[0][1]));
		bean.setBranchName(String.valueOf(empdata[0][2]));
		bean.setDept(String.valueOf(empdata[0][3]));
		bean.setDesg(String.valueOf(empdata[0][4]));
		bean.setEmpId(empId);
	}

	/**
	 * @param request
	 * @param response
	 * @param bean
	 */
	/*
	 * Generating report
	 */
	public void getReport(HttpServletRequest request, HttpServletResponse response, CompOff bean) {
		String sqlQuery = " SELECT DECODE(COMP_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected'), NVL(COMP_COMMENTS,' ') " +
		" FROM HRMS_COMPOFF_HDR WHERE COMP_ID = " + bean.getCompId();
		Object[][] statusRes = getSqlModel().getSingleResult(sqlQuery);

		String s = "\n Compensatory Off REPORT\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf", s);

		String query = " SELECT ROWNUM, COMPDTL_PROJECT, TO_CHAR(COMPDTL_PROJECTDATE, 'DD-MM-YYYY'), COMPDTL_STARTTIME, " +
		" COMPDTL_ENDTIME FROM HRMS_COMPOFF_DTL WHERE COMPDTL_COMPID = " + bean.getCompId();
		Object[][] result = getSqlModel().getSingleResult(query);

		// query for getting all the records without any conditions

		String approver = " SELECT EMP_TOKEN, (HRMS_TITLE.TITLE_NAME || ' ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME ||' ') AS EMP_NAME,TO_CHAR(APPR_DATE,'DD-MM-YYYY'), " +
		" COMPOFF_REMARK AS REMARK, DECODE(COMPOFF_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected') FROM HRMS_COMPOFF_PATH  " +
		" INNER JOIN HRMS_EMP_OFFC ON(HRMS_COMPOFF_PATH.APPROVER_CODE = HRMS_EMP_OFFC.EMP_ID) " +
		" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) WHERE COMPID = " + bean.getCompId() +
		" UNION " +
		" SELECT EMP_TOKEN, (HRMS_TITLE.TITLE_NAME || ' ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME || ' '), ''," +
		" CAST('' AS NVARCHAR2(100)) a , DECODE(COMP_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected') FROM HRMS_COMPOFF_HDR  " +
		" INNER JOIN HRMS_EMP_OFFC ON(HRMS_COMPOFF_HDR.COMP_APPROVER = HRMS_EMP_OFFC.EMP_ID) " +
		" INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " +
		" WHERE COMP_STATUS = 'P' AND COMP_ID = " + bean.getCompId();
		Object[][] approverData = getSqlModel().getSingleResult(approver);
		
		int j = 1;
		Object[][] approvalTable = new Object[approverData.length][8];
		for(int i = 0; i < approverData.length; i++) {
			approvalTable[i][0] = String.valueOf(j++);
			approvalTable[i][1] = checkNull(String.valueOf(approverData[i][0]));
			approvalTable[i][2] = checkNull(String.valueOf(approverData[i][1]));
			approvalTable[i][3] = checkNull(String.valueOf(approverData[i][2]));
			approvalTable[i][4] = checkNull(String.valueOf(approverData[i][3]));
			approvalTable[i][5] = checkNull(String.valueOf(approverData[i][4]));
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

		int[] bcellWidth = {20, 30, 20, 30};
		int[] bcellAlign = {0, 0, 0, 0};
		rg.addFormatedText(s, 6, 0, 1, 0);

		rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);

		rg.addFormatedText("Compensatory Off Details : \n ", 6, 0, 0, 0);
		String colnames[] = {"Sr.No", "Project Name ", "Date", "Start Time", "End Time"};
		int cellwidth[] = {10, 40, 15, 15, 15};
		int alignment[] = {1, 0, 0, 0, 0};
		String appCol[] = {"Sr. No", "Approver Id", "Approver Name", "Date", "Remarks", "Status", "Signature"};
		int appCell[] = {5, 10, 25, 10, 30, 10, 12};
		int apprAlign[] = {1, 1, 0, 1, 0, 0, 0};
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
	}

	public boolean isduplicate(CompOff comp) {
		if((!(String.valueOf(comp.getEmpId()).equals("")) && (!String.valueOf(comp.getEmpId()).equals("")))) {
			String Query = " SELECT COMP_EMPID, COMP_STATUS, HRMS_COMPOFF_DTL.COMPDTL_PROJECTDATE FROM HRMS_COMPOFF_HDR " +
			" LEFT JOIN HRMS_COMPOFF_DTL ON(HRMS_COMPOFF_HDR.COMP_ID = HRMS_COMPOFF_DTL.COMPDTL_COMPID) " +
			" WHERE COMP_EMPID = " + comp.getEmpId() + " AND HRMS_COMPOFF_DTL.COMPDTL_PROJECTDATE = To_date('" + comp.getPrDate() + "', 'dd-mm-yyyy') " +
			" ORDER BY HRMS_COMPOFF_DTL.COMPDTL_PROJECTDATE ";
			Object result[][] = getSqlModel().getSingleResult(Query);
			
			if(result.length > 0) {
				if(result.length == 1) {
					if(String.valueOf(result[0][1]).equals("R")) {
						return true;
					} else
						return false;
				} else
					for(int i = 0; i < result.length; i++) {

						if(!String.valueOf(result[i][1]).equals("R")) {
							return false;
						}
					}
			} else {
				return true;
			}
			return false;
		}
		return true;
	}

	/**
	 * @param bean
	 * @param hprojName
	 * @param hDate
	 * @param hsTime
	 * @param heTime
	 * @param empFlow
	 * @return boolean after inserting a record
	 */
	public boolean save(CompOff bean, Object[] hprojName, Object[] hDate, Object[] hsTime, Object[] heTime, Object[][] empFlow) {
		boolean data = false;
		
		String compIDSql = " SELECT NVL(MAX(COMP_ID), 0) + 1 FROM HRMS_COMPOFF_HDR ";
		Object[][] compIDObj = getSqlModel().getSingleResult(compIDSql);
		bean.setCompId(String.valueOf(compIDObj[0][0]));
		
		Object[][] addObj = new Object[1][6];
		addObj[0][0] = bean.getCompId();
		addObj[0][1] = bean.getEmpId();
		addObj[0][2] = bean.getAppDate();
		addObj[0][3] = String.valueOf(empFlow[0][0]);
		addObj[0][4] = bean.getComments();
		addObj[0][5] = String.valueOf(empFlow[0][3]);
		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);

		for(int i = 0; i < hprojName.length; i++) {
			Object addItem[][] = new Object[1][5];
			addItem[0][0] = String.valueOf(hprojName[i]);
			addItem[0][1] = String.valueOf(hDate[i]);
			addItem[0][2] = String.valueOf(hsTime[i]);
			addItem[0][3] = String.valueOf(heTime[i]);
			addItem[0][4] = bean.getEmpId();
			data = getSqlModel().singleExecute(getQuery(2), addItem);
		}
		
		if(data == true & result == true) {
			/*try {
				Object[][] to_mailIds = new Object[1][1];
				Object[][] from_mailIds = new Object[1][1];
				
				to_mailIds[0][0] = String.valueOf(empFlow[0][0]);
				from_mailIds[0][0] = bean.getEmpId();

				MailUtility mail = new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds, "CompOff", "", "P");

				mail.terminate();
			} catch(Exception e) {
				logger.error(e);
			}*/
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
	 * @return boolean after updating a record
	 */
	public boolean update(CompOff bean, Object[] hprojName, Object[] hDate, Object[] hsTime, Object[] heTime, Object[][] empFlow) {
		try {
			boolean flag = false;
			boolean result = false;
			Object[][] addObj = new Object[1][6];
			addObj[0][0] = bean.getEmpId();
			addObj[0][1] = bean.getAppDate();
			addObj[0][2] = String.valueOf(empFlow[0][0]);
			addObj[0][3] = bean.getComments();

			addObj[0][4] = String.valueOf(empFlow[0][3]);
			addObj[0][5] = bean.getCompId();
			boolean data = getSqlModel().singleExecute(getQuery(3), addObj);

			if(hprojName != null) {
				String query = " DELETE FROM  HRMS_COMPOFF_DTL WHERE COMPDTL_COMPID = " + bean.getCompId();
				flag = getSqlModel().singleExecute(query);
			}

			if(flag) {
				for(int i = 0; i < hprojName.length; i++) {
					Object addItem[][] = new Object[1][6];
					addItem[0][0] = bean.getCompId();
					addItem[0][1] = String.valueOf(hprojName[i]);
					addItem[0][2] = String.valueOf(hDate[i]);
					addItem[0][3] = String.valueOf(hsTime[i]);
					addItem[0][4] = String.valueOf(heTime[i]);
					addItem[0][5] = bean.getEmpId();
					result = getSqlModel().singleExecute(getQuery(4), addItem);
				}
				if(data == true & flag == true & result == true) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			logger.error(e);
			return false;
		} // end of try catch block
		return true;
	}
}