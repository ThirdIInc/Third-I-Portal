package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.srd.EmployeeCheckList;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

/**
 * 
 * @modified by priyanka.kumbhar
 * @modified on 23-01-2013
 */
public class EmployeeCheckListModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeCheckListModel.class);

	AuditTrail trial;

	/**
	 * Method to display the records below.
	 * 
	 * @param employeeCheckList
	 * @param request
	 */
	public void getList(EmployeeCheckList employeeCheckList,
			HttpServletRequest request) {
		try {
			// sql query for selecting the list of saved records
			System.out.println("getEmpID()-------------------------------->>"
					+ employeeCheckList.getEmpID());
			String sqlSelect = " SELECT HRMS_CHECKLIST.CHECK_CODE , HRMS_CHECKLIST.CHECK_NAME, "
					+ " HRMS_EMP_CHECKLIST.CHECK_DESC,  DECODE(CHECK_SUBMIT,'Yes','Yes','NA','NA','Pen','Pending','NA') ,"
					+ " NVL(CHECK_DOCUMENT,' ')  FROM HRMS_CHECKLIST "
					+ " LEFT JOIN HRMS_EMP_CHECKLIST ON(HRMS_EMP_CHECKLIST.CHECK_CODE=HRMS_CHECKLIST.CHECK_CODE "
					+ " AND HRMS_EMP_CHECKLIST.EMP_ID= "
					+ employeeCheckList.getEmpID()
					+ ")"
					+ "  WHERE HRMS_CHECKLIST.CHECK_TYPE='J' and CHECK_STATUS='A' "
					+ " ORDER BY HRMS_CHECKLIST.CHECK_CODE ";

			Object[][] data = getSqlModel().getSingleResult(sqlSelect);
			ArrayList<EmployeeCheckList> chk = new ArrayList<EmployeeCheckList>();
			HashMap hmChk = new HashMap();
			if (data.length > 0) {
				employeeCheckList.setNewFlag(true);
				for (int i = 0; i < data.length; i++) {
					EmployeeCheckList bean = new EmployeeCheckList();
					bean.setChkId(checkNull(String.valueOf(data[i][0]))); // set id
					bean.setChkName(checkNull(String.valueOf(data[i][1]))); // set name
					//bean.setChkDesc(checkNull(String.valueOf(data[i][2]))); // set description
					//String Desc=checkNull(String.valueOf(data[i][2]));
					bean.setChkDesc(checkNull(String.valueOf(data[i][2])));
					//bean.setTextDescription(checkNull(String.valueOf(data[i][2])));
					String check = String.valueOf(data[i][3]);
					if (check.equals("Pending")) { // check the status of 'pending'
						bean.setChkSubmitTest("Pen");
					} else if (check.equals("Yes")) {
						bean.setChkSubmitTest("Yes");
					} else {
						bean.setChkSubmitTest("NA");
					}

					bean.setChkSubmit(checkNull(String.valueOf(data[i][3])));// set the value of submit or not

					String docFiles = (String.valueOf(data[i][4]).trim());// set the uploaded files

					if (!docFiles.equals("") && docFiles.length() > 0) { // using split function separate the uploaded files
						String[] abc = docFiles.split(",");
						String value = "";
						String newValue = "";
						for (int j = 0; j < abc.length; j++) {
							value += abc[j] + "\n"; // save the file with new line
							newValue += abc[j] + ","; // save the file with comma separated
						}

						bean.setUploadFileNameNew(newValue.trim());
						String temp = value.trim();
						if (temp == "" || temp.length() == 0) {
							bean.setDeleteImg("false"); // delete flag true if file is available else false
						} else {
							bean.setDeleteImg("true");
						}
						bean.setUploadFileName(value);
						if (bean.getUploadFileName().length() > 15) // if length of the file is greater then 15 use substring function for short length display.
						{
							bean.setAbbrUploadFileName(bean.getUploadFileName()
									.substring(0, 9)
									+ ".....");
						} else {
							bean
									.setAbbrUploadFileName(bean
											.getUploadFileName());
						}

						String[] splitDoc = temp.split("\n");
						ArrayList<EmployeeCheckList> proofList = new ArrayList<EmployeeCheckList>();

						if (splitDoc[0] != null && !splitDoc[0].equals("")) { // create an array list of the uploaded files.
							for (int j = 0; j < splitDoc.length; j++) {
								EmployeeCheckList empChkList = new EmployeeCheckList();
								empChkList
										.setUploadFileName(splitDoc[j].trim());
								proofList.add(empChkList);
							}

							bean.setUploadList(proofList); // set the array list.
						} else {
							bean.setUploadList(proofList);
						}
					}

					// String ch = String.valueOf(data[i][3]);
					chk.add(bean);
				}
				request.setAttribute("chkText", hmChk);
				employeeCheckList.setChkList(chk);// set the created list.
			} else { // else select the list name from the master check-list order by check-code

				String sql = "	SELECT CHECK_CODE , CHECK_NAME FROM HRMS_CHECKLIST "
						+ " ORDER BY CHECK_CODE ";
				Object[][] param = getSqlModel().getSingleResult(sql);//
				ArrayList<EmployeeCheckList> para = new ArrayList<EmployeeCheckList>();
				for (int i = 0; i < param.length; i++) {
					EmployeeCheckList bean1 = new EmployeeCheckList(); // create instance
					bean1.setChkId(checkNull(String.valueOf(param[i][0]))); // set check-id
					bean1.setChkName(checkNull(String.valueOf(param[i][1])));// set check-name
					hmChk.put("" + i, "false");
					para.add(bean1);
				}
				request.setAttribute("chkText", hmChk);
				employeeCheckList.setChkList(para);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to save the Check list details of an employee.
	 * 
	 * @param empID
	 * @param chkCode
	 * @param chkDesc
	 * @param chkSubmit
	 * @param uploadFileName
	 * @param employeeCheckList
	 * @return String
	 */
	public String save(String empID, String[] chkCode, String[] chkDesc,
			String[] chkSubmit, String[] uploadFileName,
			EmployeeCheckList employeeCheckList, HttpServletRequest request) {
		String str = null;

		try {

			logger.info("===USER LOGIN CODE===="
					+ employeeCheckList.getUserEmpId());
			trial = new AuditTrail(employeeCheckList.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE * */
			trial.initiate(context, session);
			trial.setParameters("HRMS_EMP_CHECKLIST",
					new String[] { "EMP_ID" }, new String[] { employeeCheckList
							.getEmpID() }, employeeCheckList.getEmpID());
			trial.beginTrail();

			String sqlDelete = "DELETE FROM HRMS_EMP_CHECKLIST WHERE EMP_ID="
					+ empID + " ";
			getSqlModel().singleExecute(sqlDelete);// execution of the delete query and the execution of insert query.
			String sqlInsert = "INSERT INTO HRMS_EMP_CHECKLIST(EMP_ID,CHECK_CODE,CHECK_DESC ,CHECK_SUBMIT,CHECK_DOCUMENT) VALUES(?,?,?,?,?)";
			Object addObj[][] = new Object[chkCode.length][5];

			employeeCheckList.setEditFlag(false);// set the edit flag to false.

			if (chkCode != null) {
				for (int i = 0; i < chkCode.length; i++) {
					System.out.println("count-->" + (i + 1));
					System.out.println("empID" + empID); // add employee id.
					addObj[i][0] = empID;

					System.out.println(" chkCode[i]--" + chkCode[i]);
					addObj[i][1] = chkCode[i]; // add check code.

					System.out.println(" chkDesc[i]--" + chkDesc[i]);
					addObj[i][2] = chkDesc[i]; // add check description.

					System.out.println(" chkSubmit[i]--" + chkSubmit[i]);
					addObj[i][3] = chkSubmit[i];// add submit or not value.

					String[] NewUploadFile = request
							.getParameterValues("uploadFil" + i);// new added files
					String[] OldUpFileNew = request
							.getParameterValues("uploadFileNameNew");// old files
					String strValue = "";
					String finalOldValue = "";

					if (OldUpFileNew != null) {
						finalOldValue = OldUpFileNew[i].trim();
					}

					if (NewUploadFile != null && NewUploadFile.length > 0) {
						String value = "";
						for (int x = 0; x < NewUploadFile.length; x++) {
							value += NewUploadFile[x] + ",";
						}

						if (finalOldValue.equals(",")) {
							finalOldValue = value;
						} else {
							finalOldValue += value;
						}

					}
					System.out.println("strValue--------->" + strValue);
					addObj[i][4] = finalOldValue;// add the previous files.

					System.out.println(" ---------------------------------");
					str = "Record saved Successfully";

				}

				getSqlModel().singleExecute(sqlInsert, addObj);

				/** AUDIT TRAIL EXECUTION * */
				trial.executeMODTrail(request);

			}

		} catch (Exception e) {
			logger.error("Exception in Save " + e);
		}
		return str;

	}

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) { // check null function
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getImage(EmployeeCheckList employeeCheckList) { // get the employee information with its profile image
		try {
			String query = "select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="
					+ employeeCheckList.getEmpID();

			Object[][] myPics = getSqlModel().getSingleResult(query);

			employeeCheckList.setProfileUploadFileName(String
					.valueOf(myPics[0][0]));// set photo
			employeeCheckList.setProfileEmpName(String.valueOf(myPics[0][1])
					+ " " + String.valueOf(myPics[0][2]) + " "
					+ String.valueOf(myPics[0][3]));// set first name, last name and middle name.

			System.out
					.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"
							+ employeeCheckList.getProfileUploadFileName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
