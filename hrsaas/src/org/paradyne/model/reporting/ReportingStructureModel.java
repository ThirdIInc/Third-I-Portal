package org.paradyne.model.reporting;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
 
import org.paradyne.bean.reporting.ReportingStructure;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class ReportingStructureModel extends ModelBase {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public Object[][] getDBValueForreportingStructure() {
		Object reportingStrForObj[][] = null;
		try {

			String selectQuery = " SELECT REPORTING_APPL_TYPE_ABBREV,REPORTING_APPL_TYPE_NAME,REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE "
					+ " ORDER BY REPORTING_APPL_TYPE_CODE ";
			reportingStrForObj = getSqlModel().getSingleResult(selectQuery);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return reportingStrForObj;
	}

	public void getListData(ReportingStructure reportingStr) {
		// TODO Auto-generated method stub
		try {
			Object dataObj[][] = getDBValueForreportingStructure();
			ArrayList list = new ArrayList();
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					ReportingStructure innerbean = new ReportingStructure();
					innerbean.setSrNoItt(String.valueOf(i+1));
					innerbean.setModuleAbbrItt(Utility.checkNull(String
							.valueOf(dataObj[i][0])));
					innerbean.setModuleNameItt(Utility.checkNull(String
							.valueOf(dataObj[i][1])));
					list.add(innerbean);
				}
			}
			reportingStr.setModuleList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSavedData(ReportingStructure reportingStr, String division) {
		try {
			String query = "  SELECT MODULE_ABBRIVATION,HRMS_REPORTING_APPL_TYPE.REPORTING_APPL_TYPE_NAME, REPORTING_TYPE, REPORTING_LEVEL ,REPORTING_STRUCTUREHDR.REPORTING_DIV,DIV_NAME,REPORTING_STRUCTUREHDR.REPORTING_SAME_AS,R1.REPORTING_APPL_TYPE_NAME FROM REPORTING_STRUCTUREHDR " 
				+ "	INNER JOIN  HRMS_REPORTING_APPL_TYPE ON( REPORTING_STRUCTUREHDR.MODULE_ABBRIVATION =HRMS_REPORTING_APPL_TYPE.REPORTING_APPL_TYPE_ABBREV )"

				+ "LEFT JOIN  HRMS_REPORTING_APPL_TYPE R1 ON( R1.REPORTING_APPL_TYPE_ABBREV =REPORTING_STRUCTUREHDR .REPORTING_SAME_AS ) "
 
				+ "	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =REPORTING_STRUCTUREHDR.REPORTING_DIV)" 
				+ " WHERE REPORTING_DIV=" + division;
				 

			Object[][] dataObj = getSqlModel().getSingleResult(query);
			ArrayList list = new ArrayList();
			
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					ReportingStructure innerbean = new ReportingStructure();
					
					innerbean.setSrNoItt(String.valueOf(i+1));
					innerbean.setModuleAbbrItt(Utility.checkNull(String
							.valueOf(dataObj[i][0])));
					innerbean.setModuleNameItt(Utility.checkNull(String
							.valueOf(dataObj[i][1])));

					innerbean.setReportingType(Utility.checkNull(String
							.valueOf(dataObj[i][2])));

					innerbean.setReportingLevel(Utility.checkNull(String
							.valueOf(dataObj[i][3])));

					reportingStr.setDivCode(Utility.checkNull(String
							.valueOf(dataObj[i][4])));

					reportingStr.setDivName(Utility.checkNull(String
							.valueOf(dataObj[i][5])));
					
					innerbean.setSelectExistModuleAbbr(Utility.checkNull(String
							.valueOf(dataObj[i][6])));
					
					innerbean.setSelectExistModule(Utility.checkNull(String
							.valueOf(dataObj[i][7])));

					list.add(innerbean);
				}
				reportingStr.setModuleList(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean save(ReportingStructure reportingStr,
			String[] moduleAbbrevation, String[] reportingType,
			String[] reportingLevel,String[]reportingSameAs) {
		boolean result = false;

		try {
			if (moduleAbbrevation != null && moduleAbbrevation.length > 0) {

				String delQuery = " delete from  REPORTING_STRUCTUREHDR where REPORTING_DIV="
						+ reportingStr.getDivCode();

				getSqlModel().singleExecute(delQuery);

				Object saveObj[][] = new Object[moduleAbbrevation.length][5];

				String query = "insert into REPORTING_STRUCTUREHDR(MODULE_ABBRIVATION, REPORTING_TYPE, REPORTING_LEVEL,REPORTING_DIV,REPORTING_SAME_AS) values (?,?,?,?,?)";

				for (int i = 0; i < saveObj.length; i++) {
					saveObj[i][0] = moduleAbbrevation[i];
					saveObj[i][1] = reportingType[i];
					saveObj[i][2] = reportingLevel[i];
					saveObj[i][3] = reportingStr.getDivCode();
					saveObj[i][4] =reportingSameAs[i];
				}

				for (int i = 0; i < saveObj.length; i++) {
					for (int j = 0; j < saveObj[0].length; j++) {
						System.out.println("saveObj[i][j]  " + saveObj[i][j]);
					}
				}
				result = getSqlModel().singleExecute(query, saveObj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean isRecordAvailable(ReportingStructure reportingStr,
			String division) {
		boolean isRecordPresent = false;
		try {
			String query = " select count(*) from REPORTING_STRUCTUREHDR  where REPORTING_DIV="
					+ division;
			Object[][] selObj = getSqlModel().getSingleResult(query);
			if (selObj != null && selObj.length > 0) {
				if (!String.valueOf(selObj[0][0]).equals("0")) {
					isRecordPresent = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRecordPresent;

	}

	public void setData(String moduleAbbrStr, String moduleNameStr,
			ReportingStructure reportingStr, String division) {

		try {
			reportingStr.setModuleAbbrSelect(moduleAbbrStr);
			reportingStr.setModuleNameSelect(moduleNameStr);
			reportingStr.setGroupDivision(division);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean addGroup(ReportingStructure reportingStr) {
		boolean result = false;
		try {
			Object saveObj[][] = new Object[1][3];

			saveObj[0][0] = reportingStr.getModuleAbbrSelect();
			saveObj[0][1] = reportingStr.getGroupName().trim();

			saveObj[0][2] = reportingStr.getGroupDivision().trim();

			System.out.println("saveObj[0][0] " + saveObj[0][0]);

			System.out.println("saveObj[0][1] " + saveObj[0][1]);

			if (!checkDuplicate(reportingStr)) {

				String insertQuery = " insert into REPORTING_GROUP(GROUP_ID,MODULE_ABBR, GROUP_NAME, GROUP_CREATIONDATE,GROUP_DIV) "
						+ " values((SELECT NVL(MAX(GROUP_ID),0)+1 FROM REPORTING_GROUP),?,?,sysdate,?)";
				result = getSqlModel().singleExecute(insertQuery, saveObj);
			} else {
				result = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean checkDuplicate(ReportingStructure reportingStr) {
		boolean result = false;
		try {
			String query = " SELECT GROUP_NAME FROM REPORTING_GROUP WHERE UPPER(GROUP_NAME)='"
					+ reportingStr.getGroupName().trim().toUpperCase() + "' and MODULE_ABBR='"+reportingStr.getModuleAbbrSelect().trim()+"' AND GROUP_DIV="+reportingStr.getGroupDivision();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("........");
			e.printStackTrace();
		}
		return result;

	}

	public void getSavedGroupData(ReportingStructure reportingStr,String moduleAbbr,String division,String moduleName) {

		try {

			System.out.println("moduleName  getSavedGroupData  "+moduleName);
			
			System.out.println("moduleAbbr  getSavedGroupData  "+moduleAbbr);
			
			
			String selectQuery = " select GROUP_ID,GROUP_NAME, to_CHAR(GROUP_CREATIONDATE,'DD-MM-YYYY'),REPORTING_APPL_TYPE_NAME ,MODULE_ABBR ,GROUP_DIV from  REPORTING_GROUP inner join HRMS_REPORTING_APPL_TYPE on(HRMS_REPORTING_APPL_TYPE.REPORTING_APPL_TYPE_ABBREV = REPORTING_GROUP.MODULE_ABBR) where MODULE_ABBR='"
					+ moduleAbbr 
					+ "' AND GROUP_DIV ="+division +" order by GROUP_ID ";
			
			Object groupDataObj[][] = getSqlModel()
					.getSingleResult(selectQuery);
			ArrayList<Object> list = new ArrayList<Object>();
			if (groupDataObj != null && groupDataObj.length > 0) {
				for (int i = 0; i < groupDataObj.length; i++) {
					reportingStr.setShowGroup("true");
					ReportingStructure innerbean = new ReportingStructure();
					innerbean.setGroupIdItt(checkNull(String
							.valueOf(groupDataObj[i][0])));
					innerbean.setGroupNameItt(checkNull(String
							.valueOf(groupDataObj[i][1])));
					innerbean.setGroupCreateDate(checkNull(String
							.valueOf(groupDataObj[i][2])));
					
					reportingStr.setModuleNameSelect(moduleName);
					
					reportingStr.setModuleAbbrSelect(moduleAbbr);

				/*	reportingStr.setModuleNameSelect(checkNull(String
							.valueOf(groupDataObj[i][3])));

					reportingStr.setModuleAbbrSelect(checkNull(String
							.valueOf(groupDataObj[i][4])));*/

					reportingStr.setGroupDivision(checkNull(String
							.valueOf(groupDataObj[i][5])));

					list.add(innerbean);

				}
			}
			reportingStr.setGroupList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void setDataForApprover(String groupCode, String groupName,
			String division, ReportingStructure reportingStr,
			String moduleAbbrSelectStr, String moduleNameSelectStr) {
		try {
			reportingStr.setModuleNameApprover(moduleNameSelectStr);
			reportingStr.setModuleAbbrApprover(moduleAbbrSelectStr);

			reportingStr.setGroupNameApprover(groupName);
			reportingStr.setGroupCodeApprover(groupCode);

			reportingStr.setDivCodeApprover(division);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean saveManager(ReportingStructure reportingStr) {

		boolean result = false;
		try {

			String delteQuery = " delete from REPORTING_MANAGER where DIV_ID="
					+ reportingStr.getDivCodeApprover() + " and MODULE_ABBR='"
					+ reportingStr.getModuleAbbrApprover()
					+ "' and GROUP_CODE=" + reportingStr.getGroupCodeApprover();
			getSqlModel().singleExecute(delteQuery);

			Object saveObj[][] = new Object[1][9];
			saveObj[0][0] = reportingStr.getDivCodeApprover();
			saveObj[0][1] = reportingStr.getModuleAbbrApprover();
			saveObj[0][2] = reportingStr.getGroupCodeApprover();
			saveObj[0][3] = reportingStr.getManager1EmpCode();
			saveObj[0][4] = reportingStr.getManager1EmpCodeAlter();
			saveObj[0][5] = reportingStr.getManager2EmpCode();
			saveObj[0][6] = reportingStr.getManager2EmpCodeAlter();
			saveObj[0][7] = reportingStr.getManager3EmpCode();
			saveObj[0][8] = reportingStr.getManager3EmpCodeAlter();
			String saveQuery = " INSERT INTO REPORTING_MANAGER(DIV_ID, MODULE_ABBR, GROUP_CODE, REPORTING_MANAGER1, REPORTING_ALTERAPPR1, REPORTING_MANAGER2, REPORTING_ALTERAPPR2, REPORTING_MANAGER3, REPORTING_ALTERAPPR3)"
					+ " VALUES(?,?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(saveQuery, saveObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	public void getManagerData(ReportingStructure reportingStr,
			String division, String moduleAbbrivation, String groupCode) {
		try {
			String query = " select DIV_ID, MODULE_ABBR, GROUP_CODE, GROUP_NAME,REPORTING_MANAGER1, "
					+ " manager1.emp_token,manager1.EMP_FNAME||' '||manager1.EMP_MNAME||' '||manager1.EMP_LNAME "
					+ "  ,REPORTING_ALTERAPPR1,alter1.emp_token,alter1.EMP_FNAME||' '||alter1.EMP_MNAME||' '||alter1.EMP_LNAME "
					+ " , REPORTING_MANAGER2,manager2.emp_token,manager2.EMP_FNAME||' '||manager2.EMP_MNAME||' '||manager2.EMP_LNAME "
					+ "  ,REPORTING_ALTERAPPR2, alter2.emp_token,alter2.EMP_FNAME||' '||alter2.EMP_MNAME||' '||alter2.EMP_LNAME "
					+ "  ,REPORTING_MANAGER3,  manager3.emp_token,manager3.EMP_FNAME||' '||manager3.EMP_MNAME||' '||manager3.EMP_LNAME "
					+ " , REPORTING_ALTERAPPR3 ,alter3.emp_token,alter3.EMP_FNAME||' '||alter3.EMP_MNAME||' '||alter3.EMP_LNAME "
					+ " ,REPORTING_APPL_TYPE_NAME from REPORTING_MANAGER "
					+ "  inner join hrms_emp_offc manager1 on(manager1.emp_id=REPORTING_MANAGER.REPORTING_MANAGER1) "
					+ "  left join hrms_emp_offc alter1 on(alter1.emp_id=REPORTING_MANAGER.REPORTING_ALTERAPPR1) "
					+ "  left join hrms_emp_offc manager2 on(manager2.emp_id=REPORTING_MANAGER.REPORTING_MANAGER2) "
					+ "  left join hrms_emp_offc alter2 on(alter2.emp_id=REPORTING_MANAGER.REPORTING_ALTERAPPR2) "
					+ "  left join hrms_emp_offc manager3 on(manager3.emp_id=REPORTING_MANAGER.REPORTING_MANAGER3) "
					+ "  left join hrms_emp_offc alter3 on(alter3.emp_id=REPORTING_MANAGER.REPORTING_ALTERAPPR3) "
					+ "  inner join REPORTING_GROUP on(REPORTING_GROUP.GROUP_ID =REPORTING_MANAGER.GROUP_CODE) "
					+"  inner join HRMS_REPORTING_APPL_TYPE on(HRMS_REPORTING_APPL_TYPE.REPORTING_APPL_TYPE_ABBREV=REPORTING_MANAGER.MODULE_ABBR)"
					+ " where DIV_ID="
					+ division
					+ " and MODULE_ABBR='"
					+ moduleAbbrivation + "' AND GROUP_CODE=" + groupCode;

			Object[][] managerDataobj = getSqlModel().getSingleResult(query);
			if (managerDataobj != null && managerDataobj.length > 0) {
				reportingStr.setDivCodeApprover(checkNull(String
						.valueOf(managerDataobj[0][0])));
				reportingStr.setModuleAbbrApprover(checkNull(String
						.valueOf(managerDataobj[0][1])));
				reportingStr.setGroupCodeApprover(checkNull(String
						.valueOf(managerDataobj[0][2])));
				reportingStr.setGroupNameApprover(checkNull(String
						.valueOf(managerDataobj[0][3])));

				reportingStr.setManager1EmpCode(checkNull(String
						.valueOf(managerDataobj[0][4])));
				reportingStr.setManager1EmpToken(checkNull(String
						.valueOf(managerDataobj[0][5])));
				reportingStr.setManager1EmpName(checkNull(String
						.valueOf(managerDataobj[0][6])));

				reportingStr.setManager1EmpCodeAlter(checkNull(String
						.valueOf(managerDataobj[0][7])));
				reportingStr.setManager1EmpTokenAlter(checkNull(String
						.valueOf(managerDataobj[0][8])));
				reportingStr.setManager1EmpNameAlter(checkNull(String
						.valueOf(managerDataobj[0][9])));

				reportingStr.setManager2EmpCode(checkNull(String
						.valueOf(managerDataobj[0][10])));
				reportingStr.setManager2EmpToken(checkNull(String
						.valueOf(managerDataobj[0][11])));
				reportingStr.setManager2EmpName(checkNull(String
						.valueOf(managerDataobj[0][12])));

				reportingStr.setManager2EmpCodeAlter(checkNull(String
						.valueOf(managerDataobj[0][13])));
				reportingStr.setManager2EmpTokenAlter(checkNull(String
						.valueOf(managerDataobj[0][14])));
				reportingStr.setManager2EmpNameAlter(checkNull(String
						.valueOf(managerDataobj[0][15])));

				reportingStr.setManager3EmpCode(checkNull(String
						.valueOf(managerDataobj[0][16])));
				reportingStr.setManager3EmpToken(checkNull(String
						.valueOf(managerDataobj[0][17])));
				reportingStr.setManager3EmpName(checkNull(String
						.valueOf(managerDataobj[0][18])));

				reportingStr.setManager3EmpCodeAlter(checkNull(String
						.valueOf(managerDataobj[0][19])));
				reportingStr.setManager3EmpTokenAlter(checkNull(String
						.valueOf(managerDataobj[0][20])));
				reportingStr.setManager3EmpNameAlter(checkNull(String
						.valueOf(managerDataobj[0][21])));
				reportingStr.setModuleNameApprover(checkNull(String
						.valueOf(managerDataobj[0][22])));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean removeModule(String moduleAbbrevation, String division) {
		boolean result = false;

		try {
			String deleteQuery = " delete from REPORTING_STRUCTUREHDR where "
					+ " MODULE_ABBRIVATION='" + moduleAbbrevation
					+ "' and REPORTING_DIV=" + division;
			result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	public void viewEmployee(ReportingStructure reportingStr) {
		try {
			String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ " ,CENTER_NAME,DEPT_NAME,RANK_NAME ,cadre_name,HRMS_EMP_OFFC.emp_ID"
					+ " FROM HRMS_EMP_OFFC "
					+ "INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)   "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK) "
					+ " left join hrms_cadre on (hrms_cadre.CADRE_ID =hrms_emp_offc.EMP_CADRE)"
					+ "  WHERE 1=1 ";
			if (!reportingStr.getDivisionCode().equals("")) {
				query += " AND EMP_DIV IN(" + reportingStr.getDivisionCode()
						+ ") ";
			}
			if (!reportingStr.getBranchCode().equals("")) {
				query += " AND EMP_CENTER IN(" + reportingStr.getBranchCode()
						+ ") ";
			}
			if (!reportingStr.getDeptCode().equals("")) {
				query += " AND EMP_DEPT IN(" + reportingStr.getDeptCode()
						+ ") ";
			}
			if (!reportingStr.getDesgCode().equals("")) {
				query += " AND EMP_RANK IN(" + reportingStr.getDesgCode()
						+ ")";
			}
			if (!reportingStr.getEmpTypeCode().equals("")) {
				query += " AND EMP_TYPE IN (" + reportingStr.getEmpTypeCode()
						+ ")";
			}
			if (!reportingStr.getReportingtoCode().equals("")) {
				query += " AND EMP_REPORTING_OFFICER="
						+ reportingStr.getReportingtoCode() + " ";
			}

			if (!reportingStr.getGradeCode().equals("")) {
				query += " AND EMP_cadre IN (" + reportingStr.getGradeCode()
						+ ")";
			}

			query += " and  HRMS_EMP_OFFC.emp_ID NOT IN (select REPORTING_EMP_ID from REPORTING_STRUCTUREDTL "
					+ " where REPORTING_DIV="
					+ reportingStr.getDivisionCode()
					+ " and  REPORTING_GROUP_ID="
					+ reportingStr.getTeamGroupCode() + ") ";

			Object[][] empdataobj = getSqlModel().getSingleResult(query);

			ArrayList list = new ArrayList();

			if (empdataobj != null && empdataobj.length > 0) {

				reportingStr.setTableLength("true");
				for (int i = 0; i < empdataobj.length; i++) {
					ReportingStructure innerbean = new ReportingStructure();

					innerbean.setIttrPendEmpToken(checkNull(String
							.valueOf(empdataobj[i][0])));
					innerbean.setIttrPendEmpName(checkNull(String
							.valueOf(empdataobj[i][1])));
					innerbean.setIttrPendEmpBranch(checkNull(String
							.valueOf(empdataobj[i][2])));
					innerbean.setIttrPendEmpDepartment(checkNull(String
							.valueOf(empdataobj[i][3])));

					innerbean.setIttrPendEmpDesgination(checkNull(String
							.valueOf(empdataobj[i][4])));

					innerbean.setIttrPendEmpReptTo(reportingStr
							.getReportingto());

					innerbean.setIttrPendEmpId(checkNull(String
							.valueOf(empdataobj[i][6])));

					list.add(innerbean);

				}
			} else {
				reportingStr.setTableLength("false");
			}
			reportingStr.setPendEmployeeList(list);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setTeamDetails(String groupCode, String groupName,
			String moduleAbbrStr, String moduleNameStr, String division,
			ReportingStructure reportingStr) {
		try {
			reportingStr.setEmpGroupName(groupName);
			reportingStr.setEmpGroupCode(groupCode);
			reportingStr.setEmpModuleName(moduleNameStr);
			reportingStr.setEmpModuleAbbr(moduleAbbrStr);
			reportingStr.setEmpDivCode(division);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean addEmployeeToList(HttpServletRequest request,
			ReportingStructure reportingStr) {
		boolean result = false;
		try {
			String splitId[] = reportingStr.getParaId().split(",");
			if (splitId != null && splitId.length > 0) {
				Object addEmployeeObj[][] = new Object[splitId.length][3];
				for (int j = 0; j < splitId.length; j++) {
					System.out.println("div code Id :: "
							+ reportingStr.getTeamDivCode());
					System.out.println("Group Id :: "
							+ reportingStr.getTeamGroupCode());
					System.out.println("Employee ::splitId[" + j + "] :: "
							+ splitId[j]);
					addEmployeeObj[j][0] = checkNull(reportingStr
							.getTeamDivCode());
					addEmployeeObj[j][1] = checkNull(reportingStr
							.getTeamGroupCode());
					addEmployeeObj[j][2] = checkNull(splitId[j]);
				}

				String insertQuery = " INSERT INTO REPORTING_STRUCTUREDTL(REPORTING_DIV, REPORTING_GROUP_ID, REPORTING_EMP_ID) VALUES(?,?,?) ";

				result = getSqlModel().singleExecute(insertQuery,
						addEmployeeObj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void addEmployee(ReportingStructure reportingStr, String groupName,
			String groupCode, String moduleNameStr, String moduleAbbrStr,
			String division) {
		try {
			reportingStr.setTeamGroupName(groupName);
			reportingStr.setTeamGroupCode(groupCode);
			reportingStr.setTeamModuleName(moduleNameStr);
			reportingStr.setTeamModuleAbbr(moduleAbbrStr);
			reportingStr.setTeamDivCode(division);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setEmployeeData(ReportingStructure reportingStr) {
		try {

			reportingStr.setEmpGroupName(reportingStr.getTeamGroupName());
			reportingStr.setEmpGroupCode(reportingStr.getTeamGroupCode());
			reportingStr.setEmpDivCode(reportingStr.getTeamDivCode());
			reportingStr.setEmpModuleAbbr(reportingStr.getTeamModuleAbbr());
			reportingStr.setEmpModuleName(reportingStr.getTeamModuleName());
			setEmployeeListData(reportingStr,reportingStr.getTeamDivCode(),reportingStr.getTeamGroupCode());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setEmployeeListData(ReportingStructure reportingStr,String divCode,String groupCode) {
		try {
			String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME  ,CENTER_NAME,DEPT_NAME,RANK_NAME ,cadre_name, "
					+ "  E2.EMP_FNAME|| ' ' || E2.EMP_LNAME AS REPORTING_TO "
					+ "  ,HRMS_EMP_OFFC.emp_ID "
					+ "  FROM REPORTING_STRUCTUREDTL "
					+ "    inner join hrms_emp_offc on(hrms_emp_offc .emp_id =REPORTING_STRUCTUREDTL.REPORTING_EMP_ID) "
					+ "    LEFT JOIN HRMS_EMP_OFFC E2 ON (hrms_emp_offc.EMP_REPORTING_OFFICER=E2.EMP_ID)  "
					+ "  INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
					+ "  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)    "
					+ "   INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK)   "
					+ "   left join hrms_cadre on (hrms_cadre.CADRE_ID =hrms_emp_offc.EMP_CADRE)  "
					+ "  WHERE 1=1 "
					+ " and   REPORTING_DIV="+divCode+" and  REPORTING_GROUP_ID="+groupCode;
			 
			Object[][] empDataObj = getSqlModel().getSingleResult(query);
			ArrayList<Object> confEmployeeList = new ArrayList<Object>();
			if (empDataObj != null && empDataObj.length > 0) {
				for (int i = 0; i < empDataObj.length; i++) {
					ReportingStructure inerBean = new ReportingStructure();
					inerBean.setIttrConfEmpToken(checkNull(String
							.valueOf(empDataObj[i][0])));
					inerBean.setIttrConfEmpName(checkNull(String
							.valueOf(empDataObj[i][1])));
					inerBean.setIttrConfEmpBranch(checkNull(String
							.valueOf(empDataObj[i][2])));
					inerBean.setIttrConfEmpDepartment(checkNull(String
							.valueOf(empDataObj[i][3])));
					inerBean.setIttrConfEmpDesgination(checkNull(String
							.valueOf(empDataObj[i][4])));

					inerBean.setIttrConfEmpReptTo(checkNull(String
							.valueOf(empDataObj[i][6])));

					inerBean.setIttrConfEmpId(checkNull(String
							.valueOf(empDataObj[i][7])));
					confEmployeeList.add(inerBean);

				}
				reportingStr.setConfEmployeeList(confEmployeeList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean deleteEmployee(ReportingStructure reportingStr) {
		// TODO Auto-generated method stub
		boolean result =false;
	
		try {
			String empFinal[] = reportingStr.getParaIdEmp().split(",");
			if (empFinal != null && empFinal.length > 0) {
				String sqlQuery = "delete from REPORTING_STRUCTUREDTL where  "
						+ " REPORTING_DIV=? and  REPORTING_GROUP_ID =? and REPORTING_EMP_ID =?";
				Object deleteObj[][] = new Object[empFinal.length][3];
				for (int i = 0; i < deleteObj.length; i++) {
					deleteObj[i][0] = reportingStr.getEmpDivCode();
					deleteObj[i][1] = reportingStr.getEmpGroupCode();
					deleteObj[i][2] = empFinal[i];
				}
				result = getSqlModel().singleExecute(sqlQuery, deleteObj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	 
	}

	public boolean deleteGroup(ReportingStructure reportingStr,String deleteGroupCodeStr) {
		// TODO Auto-generated method stub
		boolean result = false;
		boolean result1 = false;
		boolean result2 = false;
		boolean finalResult = false;

		try {
			String deleteDtl = "delete from REPORTING_STRUCTUREDTL where REPORTING_GROUP_ID ="+deleteGroupCodeStr+" and REPORTING_DIV="+reportingStr.getGroupDivision()+" ";
			result = getSqlModel().singleExecute(deleteDtl);
			String deleteManager = "delete from REPORTING_MANAGER where GROUP_CODE="+deleteGroupCodeStr+" and DIV_ID="+reportingStr.getGroupDivision()+" ";
			result1 = getSqlModel().singleExecute(deleteManager);
			String deleteGroup = "delete from  REPORTING_GROUP where GROUP_ID="+deleteGroupCodeStr+" and GROUP_DIV="+reportingStr.getGroupDivision()+"  ";
			result2 = getSqlModel().singleExecute(deleteGroup);
			if (result & result1 & result2) {
				finalResult = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return finalResult;
	}
	
	public void setDropDownSaveData(HttpServletRequest request)
	{
		String selQuery = " select REPORTING_TYPE from REPORTING_STRUCTUREHDR  where REPORTING_DIV =2";
		Object dataObj =getSqlModel().getSingleResult(selQuery);
		
		request.setAttribute("dataObj", dataObj);
		
	}

	 

	public boolean moveToGroup(ReportingStructure reportingStr,String empId,String moveGroup,HttpServletRequest request)
	{
		boolean result = false;
		try {
			
			System.out.println("empId moveToGroup "+empId);
			
			System.out.println("moveGroup moveToGroup "+moveGroup);
			
			
			String checked []=request.getParameterValues("moveChk");
			String selectedEmp ="";
			 
			String empFinal []= reportingStr.getParaIdEmp().split(",");
			

			String insertQuery = " INSERT INTO REPORTING_STRUCTUREDTL(REPORTING_DIV, REPORTING_GROUP_ID, REPORTING_EMP_ID) VALUES(?,?,?) ";

		 
		 
			Object insertObj [][]= new Object[empFinal.length][3];
			for (int i = 0; i < insertObj.length; i++) {
				insertObj [i][0] = reportingStr.getEmpDivCode();
				insertObj [i][1] = moveGroup;
				insertObj [i][2] = empFinal[i];
			}
			
			result = getSqlModel().singleExecute(insertQuery , insertObj);
			 
			Object dltObj [][]= new Object[empFinal.length][3];
			for (int i = 0; i < dltObj.length; i++) {
				//dltObj [i][0] = bean.getSelectGroupId();
				dltObj [i][0] = reportingStr.getEmpDivCode();
				dltObj [i][1] = reportingStr.getEmpGroupCode();
				dltObj [i][2] = empFinal[i];
			}
			if(result){
				result=getSqlModel().singleExecute(" delete from  REPORTING_STRUCTUREDTL where REPORTING_DIV=? and REPORTING_GROUP_ID=?   and REPORTING_EMP_ID=? ",dltObj);
			}  
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}