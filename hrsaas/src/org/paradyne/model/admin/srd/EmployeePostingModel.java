package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.srd.EmployeePosting;
import org.paradyne.lib.ModelBase;

public class EmployeePostingModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeePostingModel.class);

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * Method to display the Site Particalar details of an employee below.
	 * 
	 * @param qualDetail
	 */
	public void getEmpPostingRecord(EmployeePosting empPosting) {
		try {
			Object addObj[] = new Object[1];
			addObj[0] = empPosting.getEmpID();
			Object[][] data = getSqlModel()
					.getSingleResult(getQuery(4), addObj);		
			ArrayList<EmployeePosting> postingList = new ArrayList<EmployeePosting>();
			if (null != data && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					EmployeePosting bean1 = new EmployeePosting();
					bean1.setEmpID(checkNull(String.valueOf(data[i][0])));
					bean1.setEmpToken(checkNull(String.valueOf(data[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(data[i][2])));
					bean1.setSiteNameItt(checkNull(String.valueOf(data[i][3])));
					bean1.setLocationNameItt(checkNull(String
							.valueOf(data[i][4])));
					bean1.setFrmDateItt(checkNull(String.valueOf(data[i][5])));
					bean1.setToDateItt(checkNull(String.valueOf(data[i][6])));
					bean1.setEmpPostingDescItt(checkNull(String
							.valueOf(data[i][7])));
					bean1.setSiteCodeItt(checkNull(String.valueOf(data[i][8])));
					bean1.setLocCodeItt(checkNull(String.valueOf(data[i][9])));
					bean1
							.setEmpPostingIdItt(checkNull(String
									.valueOf(data[i][10])));
					postingList.add(bean1);
				}// end of for loop
				empPosting.setPostingList(postingList);
				empPosting.setNoData("false");
			} else {
				empPosting.setPostingList(null);
				empPosting.setNoData("true");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * to insert the records in the list
	 * 
	 * @param empPosting
	 * @param request
	 * @return
	 */
	public boolean addPostinglDtl(EmployeePosting empPosting,
			HttpServletRequest request) {
		boolean res = false;
		Object addObj[][] = new Object[1][5];
		addObj[0][0] = empPosting.getSiteCode();
		addObj[0][1] = empPosting.getEmpID();
		addObj[0][2] = empPosting.getFrmDate();
		addObj[0][3] = empPosting.getToDate();
		addObj[0][4] = checkNull(empPosting.getEmpPostingDesc());
		String str = "INSERT INTO HRMS_EMP_POSTING (EMP_POSTING_ID,ONSITE_POSTING_ID,EMP_ID,"
				+ "EMP_POSTING_DATE_START, EMP_POSTING_DATE_END, EMP_POSTING_DESC)"
				+ " VALUES ((SELECT NVL(MAX(EMP_POSTING_ID),0) + 1 FROM HRMS_EMP_POSTING),?,? ,"
				+ "TO_DATE(?,'DD-MM-YYYY') ,TO_DATE(?,'DD-MM-YYYY'),? )";
		res = getSqlModel().singleExecute(str, addObj);

		return res;

	}

	/**
	 * to update the data from list
	 * 
	 * @param empPosting2
	 * @param request
	 * @return
	 */
	public boolean modPostingDtl(EmployeePosting empPosting,
			HttpServletRequest request) {
		boolean result = false;
		if (!checkDuplicateMod(empPosting)) {

			String udateQuery = "UPDATE HRMS_EMP_POSTING SET ONSITE_POSTING_ID="
					+ checkNull(empPosting.getSiteCode()) + "";

			if (!checkNull(empPosting.getFrmDate()).equals("")) {
				udateQuery += " , EMP_POSTING_DATE_START=TO_DATE('"
						+ empPosting.getFrmDate() + "','DD-MM-YYYY') ";
			} else {
				udateQuery += " , EMP_POSTING_DATE_START=TO_DATE('','DD-MM-YYYY') ";
			}

			if (!checkNull(empPosting.getToDate()).equals("")) {
				udateQuery += " , EMP_POSTING_DATE_END=TO_DATE('"
						+ empPosting.getToDate() + "','DD-MM-YYYY') ";
			} else {
				udateQuery += " , EMP_POSTING_DATE_END=TO_DATE('','DD-MM-YYYY') ";
			}

			udateQuery += " , EMP_POSTING_DESC='"
					+ checkNull(empPosting.getEmpPostingDesc()) + "' ";

			udateQuery += " WHERE EMP_ID=" + checkNull(empPosting.getEmpID())
					+ " AND EMP_POSTING_ID="
					+ checkNull(empPosting.getParaId());

			result = getSqlModel().singleExecute(udateQuery);
		}

		return result;
	}

	/**
	 * To set the record for edit.
	 * 
	 * @param bean
	 */
	public void getRecord(EmployeePosting empPosting) {
		try {
			Object addObj[][] = new Object[1][10];

			String query = " SELECT HRMS_EMP_POSTING.EMP_ID ,EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME) NAME ,"
					+ " ONSITE_POSTING_NAME,"
					+ " TO_CHAR(EMP_POSTING_DATE_START,'DD-MM-YYYY'),TO_CHAR(EMP_POSTING_DATE_END,'DD-MM-YYYY'), "
					+ " EMP_POSTING_DESC,EMP_POSTING_ID, LOCATION_NAME,ONSITE_POSTING_ID"
					+ " FROM HRMS_EMP_POSTING "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_POSTING.EMP_ID)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ " LEFT JOIN HRMS_ONSITE_POSTING ON( HRMS_ONSITE_POSTING.ONSITE_POSTING_ID=HRMS_EMP_POSTING.ONSITE_POSTING_ID)"
					+ " LEFT JOIN HRMS_LOCATION ON(HRMS_ONSITE_POSTING.ONSITE_POSTING_LOCATION = HRMS_LOCATION.LOCATION_CODE)"
					+ " WHERE HRMS_EMP_POSTING.EMP_POSTING_ID="+empPosting.getParaId();

			Object[][] data = getSqlModel().getSingleResult(query);

			empPosting.setEmpID(checkNull(String.valueOf(data[0][0])));
			empPosting.setEmpToken(checkNull(String.valueOf(data[0][1])));
			empPosting.setEmpName(checkNull(String.valueOf(data[0][2])));
			empPosting.setSiteName(checkNull(String.valueOf(data[0][3])));
			empPosting.setFrmDate(checkNull(String.valueOf(data[0][4])));
			empPosting.setToDate(checkNull(String.valueOf(data[0][5])));
			empPosting.setEmpPostingDesc(checkNull(String.valueOf(data[0][6])));
			empPosting.setEmpPostingId(checkNull(String.valueOf(data[0][7])));			
			empPosting.setLocationName(checkNull(String.valueOf(data[0][8])));
			empPosting.setSiteCode(checkNull(String.valueOf(data[0][9])));

		}// end of try
		catch (Exception e) {
			e.printStackTrace();
		}// end of catch
	}

	/**
	 * To delete the record
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean delPostingRecord(EmployeePosting empPosting) {
		Object addObj[] = new Object[1];
		/*addObj[0][0] = empPosting.getSiteCodeItt();
		addObj[0][1] = empPosting.getEmpID();
		addObj[0][2] = empPosting.getFromDateCode();
		addObj[0][3] = empPosting.getToDateCode();
		String query = "DELETE FROM HRMS_EMP_POSTING WHERE ONSITE_POSTING_ID = "
				+ addObj[0][0]
				+ " AND HRMS_EMP_POSTING.EMP_ID =  "
				+ addObj[0][1]
				+ " AND To_DATE(EMP_POSTING_DATE_START)=TO_DATE( '"
				+ addObj[0][2]
				+ "','DD-MM-YYYY') AND TO_DATE(EMP_POSTING_DATE_END)=TO_DATE('"
				+ addObj[0][3] + "','DD-MM-YYYY')";

		return getSqlModel().singleExecute(query);*/
		// return result;
		addObj[0]= empPosting.getParaId();
		String query= " DELETE FROM HRMS_EMP_POSTING"
					+ " WHERE HRMS_EMP_POSTING.EMP_POSTING_ID= "+String.valueOf(addObj[0]);
		return getSqlModel().singleExecute(query);

	}
	/**
	 * to check the Modified Duplicate value or data
	 * 
	 */
	public boolean checkDuplicateMod(EmployeePosting empPosting) {
		boolean result = false;
		/*
		 * String query = "SELECT ONSITE_POSTING_NAME FROM HRMS_ONSITE_POSTING" + "
		 * INNER JOIN HRMS_EMP_POSTING ON (HRMS_EMP_POSTING.ONSITE_POSTING_ID=
		 * HRMS_ONSITE_POSTING.ONSITE_POSTING_ID)" + " WHERE
		 * UPPER(ONSITE_POSTING_NAME)LIKE ' " +
		 * empPosting.getSiteName().trim().toUpperCase() + "' AND
		 * HRMS_ONSITE_POSTING.ONSITE_POSTING_ID not in (" +
		 * empPosting.getPostingId() + ")";
		 */
		/*
		 * String query = "SELECT EMP_ID, EMP_POSTING_DATE_START,
		 * EMP_POSTING_DATE_END FROM HRMS_EMP_POSTING " + " where
		 * EMP_POSTING_DATE_START not
		 * in(TO_DATE('"+empPosting.getFrmDate()+"','DD-MM-YYYY')) AND
		 * EMP_POSTING_DATE_END not
		 * in(TO_DATE('"+empPosting.getToDate()+"','DD-MM-YYYY'))AND
		 * EMP_ID="+empPosting.getEmpID()+" "; Object obj[][] =
		 * getSqlModel().getSingleResult(query); if (obj != null && obj.length >
		 * 0) { return true; }
		 */

		String query = " SELECT * FROM  HRMS_EMP_POSTING WHERE (TO_DATE('"
				+ empPosting.getFrmDate()
				+ "','DD-MM-YYYY') BETWEEN EMP_POSTING_DATE_START AND EMP_POSTING_DATE_END "
				+ " OR TO_DATE('"
				+ empPosting.getToDate()
				+ "','DD-MM-YYYY') BETWEEN EMP_POSTING_DATE_START AND EMP_POSTING_DATE_END)  AND EMP_ID="
				+ empPosting.getEmpID() + " AND ONSITE_POSTING_ID NOT IN("
				+ empPosting.getSiteCodeItt() + ")";
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj == null && obj.length == 1) {
			result = true;
		}
		return result;
	}

	public void getImage(EmployeePosting empPosting) {
		try {
			String query = " SELECT EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),"
					+ " NVL(EMP_LNAME,' ') FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empPosting.getEmpID();
			Object[][] myPics = getSqlModel().getSingleResult(query);

			empPosting.setUploadFileName(String.valueOf(myPics[0][0]));
			empPosting.setProfileEmpName(String.valueOf(myPics[0][1]) + " "
					+ String.valueOf(myPics[0][2]) + " "
					+ String.valueOf(myPics[0][3]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}