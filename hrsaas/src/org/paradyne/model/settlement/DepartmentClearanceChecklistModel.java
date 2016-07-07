package org.paradyne.model.settlement;

import java.util.ArrayList;

import org.paradyne.bean.ApplicationStudio.LetterTemplate;
import org.paradyne.bean.admin.master.CostCenterMaster;
import org.paradyne.bean.settlement.DepartmentClearanceChecklist;
import org.paradyne.lib.ModelBase;

public class DepartmentClearanceChecklistModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DepartmentClearanceChecklistModel.class);

	public void addItem(DepartmentClearanceChecklist deptClearanceCheck,
			String[] srNo, String[] checkListItt, int check) {

		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			logger.info("in new add");
			if (checkListItt != null && checkListItt.length > 0) {

				for (int i = 0; i < checkListItt.length; i++) {
					DepartmentClearanceChecklist bean = new DepartmentClearanceChecklist();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setCheckListItt(checkListItt[i]);
					tableList.add(bean);
					logger.info("checkListItt[i]" + checkListItt[i]);

				}
			}
			if (check == 1) {
				deptClearanceCheck
						.setSrNo(String.valueOf(tableList.size() + 1));
				deptClearanceCheck.setCheckListItt(deptClearanceCheck
						.getCheckList());

				logger.info("deptClearanceCheck.getCheckList()  "
						+ deptClearanceCheck.getCheckList());

				tableList.add(deptClearanceCheck);
			} else if (check == 0) {
				tableList.remove(Integer.parseInt(deptClearanceCheck
						.getCheckEdit()) - 1);
			}

			deptClearanceCheck.setList(tableList);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void moditem(DepartmentClearanceChecklist deptClearanceCheck,
			String[] srNo, String[] checkListItt, int check) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					DepartmentClearanceChecklist bean = new DepartmentClearanceChecklist();
					if (i == Integer
							.parseInt(deptClearanceCheck.getCheckEdit()) - 1) {
						bean.setSrNo(String.valueOf(i + 1));
						bean.setCheckListItt(deptClearanceCheck.getCheckList());
					} else {
						bean.setSrNo(String.valueOf(i + 1));
						bean.setCheckListItt(checkListItt[i]);

					}

					tableList.add(bean);
				}

				deptClearanceCheck.setList(tableList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean deleteCheckList(
			DepartmentClearanceChecklist deptClearanceCheck, String[] srNo,
			String[] checkListItt) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {

			if (srNo != null) {
				int count = 0;
				for (int i = 0; i < srNo.length; i++) {
					DepartmentClearanceChecklist bean = new DepartmentClearanceChecklist();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setCheckListItt(checkListItt[i]);

					tableList.add(bean);
					count++;
					if ((i == Integer.parseInt(deptClearanceCheck
							.getCheckEdit()) - 1)) {
						count--;
					}
				}

				tableList.remove(Integer.parseInt(deptClearanceCheck
						.getCheckEdit()) - 1);
			}

			deptClearanceCheck.setList(tableList);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return true;
	}

	public boolean save(DepartmentClearanceChecklist deptClearanceCheck,
			String[] srNo, String[] checkListItt) {

		boolean result = false;
		try {

			if (!checkDuplicate(deptClearanceCheck)) {
				Object insertHdrObj[][] = new Object[1][2];

				insertHdrObj[0][0] = deptClearanceCheck.getDeptCode();
				insertHdrObj[0][1] = deptClearanceCheck.getEmpCode();

				String insertHdrQuery = " INSERT INTO HRMS_DEPT_CLEARCHKLIST_HDR(DEPT_CODE, EMP_CODE) "
						+ " VALUES(?,?) ";

				boolean res = getSqlModel().singleExecute(insertHdrQuery,
						insertHdrObj);

				if (res) {
					String selectQuery = " SELECT NVL(MAX(CHKLISTID),0)  FROM HRMS_DEPT_CLEARCHKLIST_HDR ";

					Object data[][] = getSqlModel()
							.getSingleResult(selectQuery);

					String insertDtlQuery = " INSERT INTO HRMS_DEPT_CLEARCHKLIST_DTL(DEPT_CODE, CHECKLIST_CODE, CHECKLIST_NAME) "
							+ "  VALUES(?,?,?)";

					if (srNo != null) {

						Object insertDtlObj[][] = new Object[srNo.length][3];
						for (int i = 0; i < srNo.length; i++) {
							insertDtlObj[i][0] = deptClearanceCheck
									.getDeptCode();
							insertDtlObj[i][1] = srNo[i];
							insertDtlObj[i][2] = checkListItt[i];
						}

						result = getSqlModel().singleExecute(insertDtlQuery,
								insertDtlObj);
					}

				} else {
					result = false;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public boolean update(DepartmentClearanceChecklist deptClearanceCheck,
			String[] srNo, String[] checkListItt) {

		boolean result = false;

		try {

			if (!(checkDuplicateMod(deptClearanceCheck))) {
				Object updateHdrObject[][] = new Object[1][3];

				String updateHdrQuery = " UPDATE  HRMS_DEPT_CLEARCHKLIST_HDR SET DEPT_CODE=?, EMP_CODE=? "
						+ " WHERE DEPT_CODE=? ";

				updateHdrObject[0][0] = deptClearanceCheck.getDeptCode();
				updateHdrObject[0][1] = deptClearanceCheck.getEmpCode();
				updateHdrObject[0][2] = deptClearanceCheck.getCheckListId();

				result = getSqlModel().singleExecute(updateHdrQuery,
						updateHdrObject);

				if (result) {
					String delQuery = "  DELETE FROM HRMS_DEPT_CLEARCHKLIST_DTL WHERE DEPT_CODE="
							+ deptClearanceCheck.getCheckListId();

					result = getSqlModel().singleExecute(delQuery);

					if (result) {

						String insertDtlQuery = " INSERT INTO HRMS_DEPT_CLEARCHKLIST_DTL(DEPT_CODE, CHECKLIST_CODE, CHECKLIST_NAME) "
								+ "  VALUES(?,?,?)";

						if (srNo != null) {

							Object insertDtlObj[][] = new Object[srNo.length][3];
							for (int i = 0; i < srNo.length; i++) {
								insertDtlObj[i][0] = deptClearanceCheck
										.getDeptCode();
								insertDtlObj[i][1] = srNo[i];
								insertDtlObj[i][2] = checkListItt[i];
							}

							result = getSqlModel().singleExecute(
									insertDtlQuery, insertDtlObj);
						}

					}
				}

			} else {
				result = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void setChecklistItteratorValues(
			DepartmentClearanceChecklist deptClearanceCheck, String[] srNo,
			String[] checkListItt) {
		try {
			ArrayList tableList = new ArrayList();

			String selectQuery = "  SELECT CHECKLIST_NAME FROM HRMS_DEPT_CLEARCHKLIST_DTL WHERE DEPT_CODE="
					+ deptClearanceCheck.getCheckListId();

			Object data[][] = getSqlModel().getSingleResult(selectQuery);

			if (data != null && data.length > 0) {

				for (int i = 0; i < data.length; i++) {
					DepartmentClearanceChecklist bean = new DepartmentClearanceChecklist();

					bean.setSrNo(String.valueOf(i + 1));
					bean.setCheckListItt(checkNull(String.valueOf(data[i][0])));

					tableList.add(bean);
				}

				deptClearanceCheck.setList(tableList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}

	/**
	 * method to check the duplicate entry of record during insertion
	 * 
	 * @param letterTemplate
	 * @return boolean
	 */
	public boolean checkDuplicate(
			DepartmentClearanceChecklist deptClearanceCheck) {
		boolean result = false;
		try {
			String query = " SELECT * FROM  HRMS_DEPT_CLEARCHKLIST_HDR WHERE DEPT_CODE="
					+ deptClearanceCheck.getDeptCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;

	}

	public boolean checkDuplicateMod(
			DepartmentClearanceChecklist deptClearanceCheck) {
		boolean result = false;
		try {
			String query = "  SELECT * FROM HRMS_DEPT_CLEARCHKLIST_HDR WHERE DEPT_CODE="
					+ deptClearanceCheck.getDeptCode()
					+ " AND DEPT_CODE NOT IN("
					+ deptClearanceCheck.getDeptCode() + ")";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;

	}

}
