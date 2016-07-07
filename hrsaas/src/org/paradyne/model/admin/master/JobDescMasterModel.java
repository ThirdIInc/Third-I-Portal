package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.JobDescriptionMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Pradeep Kumar Sahoo Date:03-01-2009
 * 
 */
public class JobDescMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(JobDescMasterModel.class);

	/**
	 * following function is called when a record is double clicked from the
	 * list
	 * 
	 * @param bean
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getJdRec(JobDescriptionMaster bean) {
		try {
			String query = "SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(JOB_DESC_NAME,' '),JOB_DESC_STATUS, NVL(JOB_DESC_DESC,' '),NVL(JOB_DESC_ROLE_RESP,' '),JOB_DESC_CODE FROM HRMS_JOB_DESCRIPTION WHERE JOB_DESC_CODE="
					+ bean.getHiddenCode();
			Object[][] repData = getSqlModel().getSingleResult(query);
			bean.setJdEffDate(checkNull(String.valueOf(repData[0][0]).trim()));
			bean.setJdDescName(checkNull(String.valueOf(repData[0][1]).trim()));
			bean.setJdDesc(checkNull(String.valueOf(repData[0][3]).trim()));
			bean.setJdRoleDesc(checkNull(String.valueOf(repData[0][4]).trim()));
			bean.setStatus(checkNull(String.valueOf(repData[0][2])));
			bean.setJdCode(checkNull(String.valueOf(repData[0][5])));
		} catch (Exception e) {
			logger.error("Exception Was Rised------->");
		}
	}

	/**
	 * following method is called when a record is selected from the search
	 * window
	 * 
	 * @param bean
	 */
	public void getRecord(JobDescriptionMaster bean) {
		try {
			String query = "SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(JOB_DESC_NAME,' '),JOB_DESC_STATUS, NVL(JOB_DESC_DESC,' '),NVL(JOB_DESC_ROLE_RESP,' '),JOB_DESC_CODE,DECODE(JOB_DESC_STATUS,'A','Active','D','Deactive'),JOB_DESC_MIN_RECRUIT_DAYS, HRMS_CADRE.CADRE_NAME,HRMS_CADRE.CADRE_ID "
					+ "FROM HRMS_JOB_DESCRIPTION  "
					+ "LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_JOB_DESCRIPTION.JOB_DESC_GRADE) "
					+ "WHERE JOB_DESC_CODE=" + bean.getJdCode();
			Object[][] repData = getSqlModel().getSingleResult(query);
			bean.setJdEffDate(checkNull(String.valueOf(repData[0][0]).trim()));
			bean.setJdDescName(checkNull(String.valueOf(repData[0][1]).trim()));

			bean.setStatus(checkNull(String.valueOf(repData[0][2])));
			bean.setJdDesc(checkNull(String.valueOf(repData[0][3]).trim()));
			bean.setJdRoleDesc(checkNull(String.valueOf(repData[0][4]).trim()));

			bean.setJdCode(checkNull(String.valueOf(repData[0][5])));
			bean.setPageStatus(checkNull(String.valueOf(repData[0][6])));
			bean.setRecruitmentDays(checkNull(String.valueOf(repData[0][7])));
			bean.setGradeName(checkNull(String.valueOf(repData[0][8])));
			bean.setGradeId(checkNull(String.valueOf(repData[0][9])));
		} catch (Exception e) {
			logger.error("Exception Was Rised------->");
		}
	}

	/**
	 * following function is called when edit button is clicked.
	 * 
	 * @param bean
	 */
	public void calOnEdit(JobDescriptionMaster bean) {
		try {
			String query = "SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(JOB_DESC_NAME,' '),JOB_DESC_STATUS, NVL(JOB_DESC_DESC,' '),NVL(JOB_DESC_ROLE_RESP,' '),JOB_DESC_CODE,DECODE(JOB_DESC_STATUS,'A','Active','D','Deactive'),JOB_DESC_MIN_RECRUIT_DAYS,HRMS_CADRE.CADRE_NAME,HRMS_CADRE.CADRE_ID FROM HRMS_JOB_DESCRIPTION "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_JOB_DESCRIPTION.JOB_DESC_GRADE)"
					+ " WHERE JOB_DESC_CODE=" + bean.getJdCode();

			Object[][] repData = getSqlModel().getSingleResult(query);
			System.out.println("date-->" + bean.getJdEffDate());
			System.out.println("name-->" + bean.getJdDescName());
			System.out.println("status-->" + bean.getStatus());
			System.out.println("desc-->" + bean.getJdDesc());
			System.out.println("roles-->" + bean.getJdRoleDesc());
			System.out.println("code-->" + bean.getJdCode());
			if (repData != null && repData.length > 0) {
				bean.setJdEffDate(checkNull(String.valueOf(repData[0][0])
						.trim()));
				bean.setJdDescName(checkNull(String.valueOf(repData[0][1])
						.trim()));
				bean.setJdDesc(checkNull(String.valueOf(repData[0][3]).trim()));
				bean.setJdRoleDesc(checkNull(String.valueOf(repData[0][4])
						.trim()));
				bean.setStatus(checkNull(String.valueOf(repData[0][2])));
				bean.setJdCode(checkNull(String.valueOf(repData[0][5])));
				bean.setPageStatus(checkNull(String.valueOf(repData[0][6])));
				bean
						.setRecruitmentDays(checkNull(String
								.valueOf(repData[0][7])));
				bean.setGradeName(checkNull(String.valueOf(repData[0][8])));
				bean.setGradeId(checkNull(String.valueOf(repData[0][9])));
			}
		} catch (Exception e) {
			logger.error("Exception Was Rised----->");
		}
	}

	/**
	 * following function is called when delete button is clicked for deleting
	 * the check box selected records
	 * 
	 * @param bean
	 * @param code
	 * @return
	 */

	public boolean delChkdRec(JobDescriptionMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					String query = "DELETE FROM HRMS_JOB_DESCRIPTION WHERE JOB_DESC_CODE=? ";
					result = getSqlModel().singleExecute(query, delete);

					if (!result)
						count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return true;
		}
	}

	/**
	 * following function is called when Save button is clicked for adding a new
	 * record.
	 * 
	 * @param jdMaster
	 * @return
	 */
	public String saveData(JobDescriptionMaster jdMaster) {
		Object[][] Data = new Object[1][7];
		String flag = "";
		boolean result = false;
		try {
			Data[0][0] = jdMaster.getJdDescName();
			Data[0][1] = jdMaster.getJdDesc();
			Data[0][2] = jdMaster.getJdRoleDesc();
			Data[0][3] = jdMaster.getStatus();
			Data[0][4] = jdMaster.getJdEffDate();
			Data[0][5] = jdMaster.getRecruitmentDays();
			Data[0][6] = jdMaster.getGradeId();

			if (!checkDuplicate(jdMaster)) {
				logger.info("inside check duplicate------------>");
				result = getSqlModel().singleExecute(getQuery(1), Data);
				logger.info("result in checkDuplicate of First if--------->"
						+ result);
				if (result) {
					String query = "SELECT MAX(JOB_DESC_CODE) FROM HRMS_JOB_DESCRIPTION";
					Object data[][] = getSqlModel().getSingleResult(query);

					String query1 = "SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(JOB_DESC_NAME,' '),DECODE(JOB_DESC_STATUS,'A','Active','D','Deactive'), NVL(JOB_DESC_DESC,' '),"
							+ " NVL(JOB_DESC_ROLE_RESP,' '),JOB_DESC_CODE,JOB_DESC_MIN_RECRUIT_DAYS, NVL(CADRE_NAME,' ')"
							+ " FROM HRMS_JOB_DESCRIPTION"
							+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_JOB_DESCRIPTION.JOB_DESC_GRADE)"
							+ " WHERE JOB_DESC_CODE="
							+ String.valueOf(data[0][0]);

					Object[][] repData = getSqlModel().getSingleResult(query1);
					jdMaster.setJdEffDate(checkNull(String.valueOf(
							repData[0][0]).trim()));
					jdMaster.setJdDescName(checkNull(String.valueOf(
							repData[0][1]).trim()));
					jdMaster.setJdDesc(checkNull(String.valueOf(repData[0][3])
							.trim()));
					jdMaster.setJdRoleDesc(checkNull(String.valueOf(
							repData[0][4]).trim()));
					jdMaster
							.setJdCode(checkNull(String.valueOf(repData[0][5])));
					jdMaster.setPageStatus(checkNull(String
							.valueOf(repData[0][2])));
					jdMaster.setRecruitmentDays(checkNull(String
							.valueOf(repData[0][6])));
					jdMaster.setGradeName(checkNull(String
							.valueOf(repData[0][7])));

					flag = "saved";
				} else {
					flag = "error";
				}
			} else {
				String query = "SELECT DECODE(JOB_DESC_STATUS,'A','Active','D','Deactive') "
						+ " FROM HRMS_JOB_DESCRIPTION where  JOB_DESC_STATUS='"
						+ jdMaster.getStatus() + "'";

				Object[][] data = getSqlModel().getSingleResult(query);
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised--->");
		}
		return flag;
	}

	/**
	 * following function is called to display all the records when the link is
	 * clicked
	 * 
	 * @param bean
	 * @param request
	 */

	public void Data(JobDescriptionMaster bean, HttpServletRequest request) {

		try {
			int length = 0;
			String query = "SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(JOB_DESC_NAME,' '),CASE WHEN JOB_DESC_STATUS='A' THEN 'Active' ELSE 'Deactive' END, NVL(JOB_DESC_DESC,' '),NVL(JOB_DESC_ROLE_RESP,' '),JOB_DESC_CODE FROM HRMS_JOB_DESCRIPTION  ORDER BY JOB_DESC_CODE ";

			Object[][] repData = getSqlModel().getSingleResult(query);
			ArrayList<Object> obj = new ArrayList<Object>();
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					repData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("abc", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("xyz", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				// setting
				JobDescriptionMaster bean1 = new JobDescriptionMaster();
				bean1.setJdEffDate(checkNull(String.valueOf(repData[i][0])));
				bean1.setJdDescName(checkNull(String.valueOf(repData[i][1])));
				bean1.setJdDesc(checkNull(String.valueOf(repData[i][3])));
				bean1.setJdRoleDesc(checkNull(String.valueOf(repData[i][4])));
				bean1.setStatus(checkNull(String.valueOf(repData[i][2])));
				bean1.setJdCode(checkNull(String.valueOf(repData[i][5])));
				obj.add(bean1);
			}
			bean.setJdList(obj);
			length = repData.length;
			bean.setTotalRecords(String.valueOf(length));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to modify the record.
	 * 
	 * @param jdMaster
	 * @return
	 */
	public String upDataData(JobDescriptionMaster jdMaster) {
		// ----------------------UPDATE main data----------------

		String editFlag = "";
		boolean result = false;

		try {

			String query = " UPDATE HRMS_JOB_DESCRIPTION SET  JOB_DESC_EFFC_DATE=TO_DATE('"
					+ jdMaster.getJdEffDate()
					+ "','DD-MM-YYYY'), JOB_DESC_NAME='"
					+ jdMaster.getJdDescName()
					+ "', JOB_DESC_ROLE_RESP='"
					+ jdMaster.getJdRoleDesc()
					+ "', JOB_DESC_STATUS='"
					+ jdMaster.getStatus() + "' ";

			if (!jdMaster.getRecruitmentDays().equals("")) {
				query += " , JOB_DESC_DESC='" + jdMaster.getJdDesc() + "'";
			}

			if (!jdMaster.getRecruitmentDays().equals("")) {
				query += " , JOB_DESC_MIN_RECRUIT_DAYS="
						+ jdMaster.getRecruitmentDays();
			}

			if (!jdMaster.getGradeId().equals("")) {
				query += " , JOB_DESC_GRADE=" + jdMaster.getGradeId();
			}

			query += " WHERE JOB_DESC_CODE =" + jdMaster.getJdCode();

			if (!(checkDuplicateMod(jdMaster))) {
				logger.info("inside updateData() method---------------->");
				result = getSqlModel().singleExecute(query);
				logger.info("result in modJobDescription -----------&&&&&>"
						+ result);
				if (result) {
					logger.info("result in modJobDescription -----------&&&&&>"
							+ result);
					String query1 = "SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(JOB_DESC_NAME,' '),CASE WHEN JOB_DESC_STATUS='A' THEN 'Active' ELSE 'Deactive' END, NVL(JOB_DESC_DESC,' '),NVL(JOB_DESC_ROLE_RESP,' '),JOB_DESC_CODE FROM HRMS_JOB_DESCRIPTION WHERE JOB_DESC_CODE="
							+ jdMaster.getJdCode();

					Object[][] repData = getSqlModel().getSingleResult(query1);
					jdMaster.setJdEffDate(checkNull(String
							.valueOf(repData[0][0])));
					jdMaster.setJdDescName(checkNull(String
							.valueOf(repData[0][1])));
					jdMaster
							.setJdDesc(checkNull(String.valueOf(repData[0][3])));
					jdMaster.setJdRoleDesc(checkNull(String
							.valueOf(repData[0][4])));
					jdMaster
							.setStatus(checkNull(String.valueOf(repData[0][2])));
					jdMaster
							.setJdCode(checkNull(String.valueOf(repData[0][5])));
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised--->");
		}
		return editFlag;
	}

	/**
	 * folloiwng function is called to delete a record when delete button is
	 * clicked..
	 * 
	 * @param jdMaster
	 * @return
	 */

	public boolean deletedata(JobDescriptionMaster jdMaster) {
		Object del[][] = new Object[1][1];
		del[0][0] = jdMaster.getJdCode();
		logger.info("Desig Code del[0][0]--->" + del[0][0]);
		return getSqlModel().singleExecute(getQuery(3), del);
	}

	/* for checking duplicate entry of records during insertion. */
	public boolean checkDuplicate(JobDescriptionMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_JOB_DESCRIPTION WHERE UPPER(JOB_DESC_NAME) = '"
				+ bean.getJdDescName().trim().toUpperCase()
				+ "'AND JOB_DESC_EFFC_DATE = TO_DATE('"
				+ bean.getJdEffDate()
				+ "','DD-MM-YYYY')";
		logger.info("query of checkDuplicate in model ----------->" + query);
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		logger
				.info("result in checkDuplicate method of Model------->"
						+ result);
		return result;

	}

	/* for checking duplicate entry of records during modification. */
	public boolean checkDuplicateMod(JobDescriptionMaster bean) {
		boolean result = false;
		Object[][] data = null;
		String jdName = bean.getJdDescName().trim().toUpperCase();
		Object[] value = new Object[1];
		try {
			value[0] = bean.getJdDescName().trim().toUpperCase();
		} catch (Exception e) {
			logger.error("esception in object value in duplicate check method",
					e);
		}
		try {
			String query = "SELECT * FROM HRMS_JOB_DESCRIPTION WHERE UPPER(JOB_DESC_NAME) = ? "
					+ " AND JOB_DESC_CODE not in("
					+ bean.getJdCode()
					+ ") "
					+ "AND JOB_DESC_EFFC_DATE = TO_DATE('"
					+ bean.getJdEffDate() + "','DD-MM-YYYY')";
			data = getSqlModel().getSingleResult(query, value);
		} catch (Exception e) {
			logger.error("exception in duplicate query", e);
		}
		if (data != null && data.length > 0) {
			result = true;
		}
		logger.info("result in checkDuplicateMod of Model------->" + result);
		return result;

	}

	/**
	 * Following function is called to get the report of the Pdf format for list
	 * of Job Description Records
	 */
	public void generateReport(JobDescriptionMaster jdMaster,
			HttpServletResponse response, String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "Job Description Master";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Job Description Master.Pdf");
		String queryDes = " SELECT TO_CHAR(JOB_DESC_EFFC_DATE,'MM-DD-YYYY'),JOB_DESC_NAME,DECODE(JOB_DESC_STATUS,'A','Active','D','Deactive') "
				+ " FROM HRMS_JOB_DESCRIPTION ORDER BY JOB_DESC_CODE";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				j++;
			}
			int cellwidth[] = { 5, 20, 20, 20 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addTextBold("Job Description Master", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}
}
