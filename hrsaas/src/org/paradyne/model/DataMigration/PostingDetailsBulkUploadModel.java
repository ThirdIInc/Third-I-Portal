package org.paradyne.model.DataMigration;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class PostingDetailsBulkUploadModel extends ModelBase {

	public void uploadPostDetails(HttpServletRequest request,
			HttpServletResponse response, EmpDetailsUpload empDetails) {
		try {

			System.out
					.println("in model's uploadPostDetails method############################## ");

			String filePath = empDetails.getDataPath() + ""
					+ empDetails.getUploadFileName();
			empDetails.setUploadName("PostingDetails");
			System.out.println("filePath-----------" + filePath);
			MigrateExcelData.getFile(filePath);

			HashMap<Integer, Boolean> columnInformation = MigrateExcelData
					.isColumnsMandatory();

			Object[][] empIdObj = null;
			Object[][] empIdMaster = getSqlModel().getSingleResult(
					" SELECT EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC ");
			if (empIdMaster != null && empIdMaster.length > 0)
				System.out.println("columnInformation.get(0)------"
						+ columnInformation.get(0));
			System.out.println("columnInformation.get(1)------"
					+ columnInformation.get(1));
			try {
				empIdObj = MigrateExcelData.uploadExcelData(1, empIdMaster,
						MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
			} catch (RuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Object[][] empNameObj = MigrateExcelData.uploadExcelData(2,
						null, MigrateExcelData.STRING_TYPE, columnInformation
								.get(2));
			} catch (RuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Object[][] site_Name = null;
			try {
				Object[][] sitename = getSqlModel()
						.getSingleResult(
								"SELECT  ONSITE_POSTING_ID ,ONSITE_POSTING_NAME FROM HRMS_ONSITE_POSTING");

				site_Name = MigrateExcelData.uploadExcelData(3, sitename,
						MigrateExcelData.MASTER_TYPE, columnInformation.get(3));
			} catch (RuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Object[][] fromDate = null;
			try {
				fromDate = MigrateExcelData.uploadExcelData(4, null,
						MigrateExcelData.DATE_TYPE, columnInformation.get(4));
			} catch (RuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Object[][] toDate = null;
			try {
				toDate = MigrateExcelData.uploadExcelData(5, null,
						MigrateExcelData.DATE_TYPE, columnInformation.get(5));
			} catch (RuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String post = "SELECT NVL(MAX(EMP_POSTING_ID),0) FROM HRMS_EMP_POSTING";
			Object[][] Pstdetail = getSqlModel().getSingleResult(post);
			System.out.println("String.valueOf(Pstdetail[0][0])--" + String.valueOf(Pstdetail[0][0]));
			int postId = Integer.parseInt(String.valueOf(Pstdetail[0][0]));
			System.out
					.println("postValue here get is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
							+ postId);
			if (empIdMaster != null && empIdMaster.length > 0) {
				boolean res = MigrateExcelData.isFileToBeUploaded();
				if (res) {
					String empIds = "";

					Object[][] insertExpObj = new Object[empIdObj.length][5];
					for (int i = 0; i < empIdObj.length; i++) {
						empIds += String.valueOf(empIdObj[i][0]) + ",";
						insertExpObj[i][0] = String.valueOf(++postId);

						
						System.out.println("insertExpObj[i][0]"+insertExpObj[i][0]);
						
						try {
							insertExpObj[i][1] = String.valueOf(empIdObj[i][0]);
						} catch (Exception e) {
							insertExpObj[i][1] = "";
						}

						try {
							insertExpObj[i][4] = String
									.valueOf(site_Name[i][0]).trim();

						} catch (Exception e) {
							insertExpObj[i][4] = "";
						}

						try {
							insertExpObj[i][2] = checkNull(String.valueOf(fromDate[i][0]))
									.trim();
						} catch (Exception e) {
							insertExpObj[i][2] = "";
						}

						try {
							insertExpObj[i][3] = checkNull(String.valueOf(toDate[i][0]))
									.trim();
						} catch (Exception e) {
							insertExpObj[i][3] = "";
						}
						/*
						 * System.out.println("insertExpObj[i][1] empIdObj"
						 * +String.valueOf(empIdObj[i][0]) );
						 * System.out.println("insertExpObj[i][2] site_Name"
						 * +String.valueOf(site_Name[i][0]) );
						 * System.out.println("insertExpObj[i][4] toDate"
						 * +String.valueOf(toDate[i][0]) );
						 * System.out.println("insertExpObj[i][4]fromDate"
						 * +String.valueOf(fromDate[i][0]) );
						 */
					}
					empIds = empIds.substring(0, empIds.length() - 1);

					String delQuery = "DELETE FROM HRMS_EMP_POSTING WHERE EMP_ID IN ("
							+ empIds + ") ";

					getSqlModel().singleExecute(delQuery);

					String insertquery = "INSERT INTO HRMS_EMP_POSTING (EMP_POSTING_ID,EMP_ID,EMP_POSTING_DATE_START, EMP_POSTING_DATE_END,ONSITE_POSTING_ID)"
							+ " VALUES (?,?,TO_DATE(?,'MM-DD-YYYY'),TO_DATE(?,'MM-DD-YYYY'),?)";

					boolean insertPostResult = getSqlModel().singleExecute(
							insertquery, insertExpObj);
					System.out
							.println("insertPostResult is getting here is ))))))))))))))))))))))))))))))))))))))"
									+ insertPostResult);

					if (insertPostResult)
						empDetails.setStatus("Success");
					else {
						empDetails.setStatus("Fail");
						empDetails
								.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
					}
				} else {
					empDetails.setStatus("Fail");
					empDetails
							.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
				}
			} else {
				empDetails.setStatus("Fail");
				empDetails
						.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * To set blank for null value of any field.
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

}
