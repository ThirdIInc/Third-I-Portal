/**
 * 
 */
package org.paradyne.model.settings;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.settings.GratuityConfiguration;
import org.paradyne.lib.ModelBase;

/**
 * @author aa0623
 *
 */
public class GratuityConfigurationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(GratuityConfigurationModel.class);
	
	public void addItem(GratuityConfiguration gratuityConfiguration, String[] srNo,
			String[] divCode, String[] divName, String[] gradeCode,
			String[] gradeName, String[] deptCode, String[] deptName,
			String[] brnCode, String[] brnName, String[] desgCode,
			String[] desgName,String[] eTypeCode, String[] eTypeName, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				GratuityConfiguration bean = new GratuityConfiguration();
				bean.setSrNo(String.valueOf(i + 1));

				bean.setDivisionCodeItr(divCode[i]);
				if (!(divName[i].equals("null") || divName[i].equals("")))
					bean.setDivisionNameItr(divName[i]);
				else
					bean.setDivisionNameItr("-");
				bean.setGradeCodeItr(gradeCode[i]);
				if (!(gradeName[i].equals("null") || gradeName[i].equals("")))
					bean.setGradeNameItr(gradeName[i]);
				else
					bean.setGradeNameItr("-");
				bean.setDeptCodeItr(deptCode[i]);
				if (!(deptName[i].equals("null") || deptName[i].equals("")))
					bean.setDeptNameItr(deptName[i]);
				else
					bean.setDeptNameItr("-");
				bean.setBranchCodeItr(brnCode[i]);
				if (!(brnName[i].equals("null") || brnName[i].equals("")))
					bean.setBranchNameItr(brnName[i]);
				else
					bean.setBranchNameItr("-");
				bean.setDesgCodeItr(desgCode[i]);
				if (!(desgName[i].equals("null") || desgName[i].equals("")))
					bean.setDesgNameItr(desgName[i]);
				else
					bean.setDesgNameItr("-");
				bean.setEmpTypeCodeItr(eTypeCode[i]);
				if (!(eTypeName[i].equals("null") || eTypeName[i].equals("")))
					bean.setEmpTypeNameItr(eTypeName[i]);
				else
					bean.setEmpTypeNameItr("-");

				tableList.add(bean);
			}
		}
		if (check == 1) {
			gratuityConfiguration.setSrNo(String.valueOf(tableList.size() + 1));

			gratuityConfiguration.setDivisionCodeItr(gratuityConfiguration
					.getDivisionCode());
			if (!(gratuityConfiguration.getDivisionName().equals("null") || gratuityConfiguration
					.getDivisionName().equals("")))
				gratuityConfiguration.setDivisionNameItr(gratuityConfiguration
						.getDivisionName());
			else
				gratuityConfiguration.setDivisionNameItr("-");
			gratuityConfiguration.setGradeCodeItr(gratuityConfiguration.getGradeCode());
			if (!(gratuityConfiguration.getGradeName().equals("null") || gratuityConfiguration
					.getGradeName().equals("")))
				gratuityConfiguration.setGradeNameItr(gratuityConfiguration
						.getGradeName());
			else
				gratuityConfiguration.setGradeNameItr("-");
			gratuityConfiguration.setDeptCodeItr(gratuityConfiguration.getDeptCode());
			if (!(gratuityConfiguration.getDeptName().equals("null") || gratuityConfiguration
					.getDeptName().equals("")))
				gratuityConfiguration.setDeptNameItr(gratuityConfiguration.getDeptName());
			else
				gratuityConfiguration.setDeptNameItr("-");
			gratuityConfiguration.setBranchCodeItr(gratuityConfiguration.getBranchCode());
			if (!(gratuityConfiguration.getBranchName().equals("null") || gratuityConfiguration
					.getBranchName().equals("")))
				gratuityConfiguration.setBranchNameItr(gratuityConfiguration
						.getBranchName());
			else
				gratuityConfiguration.setBranchNameItr("-");
			gratuityConfiguration.setDesgCodeItr(gratuityConfiguration.getDesgCode());
			if (!(gratuityConfiguration.getDesgName().equals("null") || gratuityConfiguration
					.getDesgName().equals("")))
				gratuityConfiguration.setDesgNameItr(gratuityConfiguration.getDesgName());
			else
				gratuityConfiguration.setDesgNameItr("-");
			gratuityConfiguration.setEmpTypeCodeItr(gratuityConfiguration.getEmpTypeCode());
			if (!(gratuityConfiguration.getEmpTypeName().equals("null") || gratuityConfiguration
					.getEmpTypeName().equals("")))
				gratuityConfiguration.setEmpTypeNameItr(gratuityConfiguration.getEmpTypeName());
			else
				gratuityConfiguration.setEmpTypeNameItr("-");
			tableList.add(gratuityConfiguration);

		} else if (check == 0) {
			tableList
					.remove(Integer.parseInt(gratuityConfiguration.getSubcode()) - 1);
		}
		if (tableList.size() != 0)
			gratuityConfiguration.setListLength("1");
		else
			gratuityConfiguration.setListLength("0");
		gratuityConfiguration.setFilterTableList(tableList);
	}

	public void moditem(GratuityConfiguration gratuityConfiguration, String[] srNo,
			String[] divCode, String[] divName, String[] gradeCode,
			String[] gradeName, String[] deptCode, String[] deptName,
			String[] brnCode, String[] brnName, String[] desgCode,
			String[] desgName,String[] eTypeCode, String[] eTypeName, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {

				if (i == Integer.parseInt(gratuityConfiguration.getHiddenEdit()) - 1) {
					gratuityConfiguration.setSrNo(String.valueOf(i + 1));
					gratuityConfiguration.setDivisionCodeItr(gratuityConfiguration
							.getDivisionCode());
					if (!(gratuityConfiguration.getDivisionName().equals("null") || gratuityConfiguration
							.getDivisionName().equals(""))) {
						gratuityConfiguration.setDivisionNameItr(gratuityConfiguration
								.getDivisionName());
					} else {
						gratuityConfiguration.setDivisionNameItr("-");
					}
					gratuityConfiguration.setGradeCodeItr(gratuityConfiguration
							.getGradeCode());
					if (!(gratuityConfiguration.getGradeName().equals("null") || gratuityConfiguration
							.getGradeName().equals("")))
						gratuityConfiguration.setGradeNameItr(gratuityConfiguration
								.getGradeName());
					else
						gratuityConfiguration.setGradeNameItr("-");
					gratuityConfiguration.setDeptCodeItr(gratuityConfiguration
							.getDeptCode());
					if (!(gratuityConfiguration.getDeptName().equals("null") || gratuityConfiguration
							.getDeptName().equals("")))
						gratuityConfiguration.setDeptNameItr(gratuityConfiguration
								.getDeptName());
					else
						gratuityConfiguration.setDeptNameItr("-");
					gratuityConfiguration.setBranchCodeItr(gratuityConfiguration
							.getBranchCode());
					if (!(gratuityConfiguration.getBranchName().equals("null") || gratuityConfiguration
							.getBranchName().equals("")))
						gratuityConfiguration.setBranchNameItr(gratuityConfiguration
								.getBranchName());
					else
						gratuityConfiguration.setBranchNameItr("-");
					gratuityConfiguration.setDesgCodeItr(gratuityConfiguration
							.getDesgCode());
					if (!(gratuityConfiguration.getDesgName().equals("null") || gratuityConfiguration
							.getDesgName().equals("")))
						gratuityConfiguration.setDesgNameItr(gratuityConfiguration
								.getDesgName());
					else
						gratuityConfiguration.setDesgNameItr("-");
					gratuityConfiguration.setEmpTypeCodeItr(gratuityConfiguration
							.getEmpTypeCode());
					if (!(gratuityConfiguration.getEmpTypeName().equals("null") || gratuityConfiguration
							.getEmpTypeName().equals("")))
						gratuityConfiguration.setEmpTypeNameItr(gratuityConfiguration.getEmpTypeName());
					else
						gratuityConfiguration.setEmpTypeNameItr("-");

					tableList.add(gratuityConfiguration);

				} else {
					GratuityConfiguration bean = new GratuityConfiguration();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setDivisionCodeItr(divCode[i]);
					if (!(divName[i].equals("null") || divName[i].equals("")))
						bean.setDivisionNameItr(divName[i]);
					else
						bean.setDivisionNameItr("-");
					bean.setGradeCodeItr(gradeCode[i]);
					if (!(gradeName[i].equals("null") || gradeName[i].equals("")))
						bean.setGradeNameItr(gradeName[i]);
					else
						bean.setGradeNameItr("-");
					bean.setDeptCodeItr(deptCode[i]);
					if (!(deptName[i].equals("null") || deptName[i].equals("")))
						bean.setDeptNameItr(deptName[i]);
					else
						bean.setDeptNameItr("-");
					bean.setBranchCodeItr(brnCode[i]);
					if (!(brnName[i].equals("null") || brnName[i].equals("")))
						bean.setBranchNameItr(brnName[i]);
					else
						bean.setBranchNameItr("-");
					bean.setDesgCodeItr(desgCode[i]);
					if (!(desgName[i].equals("null") || desgName[i].equals("")))
						bean.setDesgNameItr(desgName[i]);
					else
						bean.setDesgNameItr("-");
					bean.setEmpTypeCodeItr(eTypeCode[i]);
					if (!(eTypeName[i].equals("null") || eTypeName[i].equals("")))
						bean.setEmpTypeNameItr(eTypeName[i]);
					else
						bean.setEmpTypeNameItr("-");

					tableList.add(bean);
				}

			}
		}
		if (tableList.size() != 0)
			gratuityConfiguration.setListLength("1");
		else
			gratuityConfiguration.setListLength("0");
		gratuityConfiguration.setFilterTableList(tableList);

	}

	public boolean checkDuplicate(GratuityConfiguration gratuityConfiguration,
			String[] srNo, String[] divCode, String[] divName,
			String[] gradeCode, String[] gradeName, String[] deptCode,
			String[] deptName, String[] brnCode, String[] brnName,
			String[] desgCode, String[] desgName,String[] eTypeCode, String[] eTypeName, int check) {
		boolean result = false;
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				/*if (!gratuityConfiguration.getHiddenEdit().equals("")
						&& !gratuityConfiguration.getHiddenEdit().equals(srNo[i])) {*/
					if (gratuityConfiguration.getDivisionCode().equals(divCode[i])
						&& gratuityConfiguration.getGradeCode().equals(gradeCode[i])
						&& gratuityConfiguration.getDeptCode().equals(deptCode[i])
						&& gratuityConfiguration.getBranchCode().equals(brnCode[i])
						&& gratuityConfiguration.getDesgCode().equals(desgCode[i])
						&& gratuityConfiguration.getEmpTypeCode().equals(eTypeCode[i])
						&& !gratuityConfiguration.getHiddenEdit().equals(srNo[i])) {
						result = true;
					} else
						result = false;
				/*} else
					result = false;*/
			}
		}

		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				GratuityConfiguration bean = new GratuityConfiguration();
				bean.setSrNo(String.valueOf(i + 1));

				bean.setDivisionCodeItr(divCode[i]);
				if (!(divName[i].equals("null") || divName[i].equals("")))
					bean.setDivisionNameItr(divName[i]);
				else
					bean.setDivisionNameItr("-");
				bean.setGradeCodeItr(gradeCode[i]);
				if (!(gradeName[i].equals("null") || gradeName[i].equals("")))
					bean.setGradeNameItr(gradeName[i]);
				else
					bean.setGradeNameItr("-");
				bean.setDeptCodeItr(deptCode[i]);
				if (!(deptName[i].equals("null") || deptName[i].equals("")))
					bean.setDeptNameItr(deptName[i]);
				else
					bean.setDeptNameItr("-");
				bean.setBranchCodeItr(brnCode[i]);
				if (!(brnName[i].equals("null") || brnName[i].equals("")))
					bean.setBranchNameItr(brnName[i]);
				else
					bean.setBranchNameItr("-");
				bean.setDesgCodeItr(desgCode[i]);
				if (!(desgName[i].equals("null") || desgName[i].equals("")))
					bean.setDesgNameItr(desgName[i]);
				else
					bean.setDesgNameItr("-");
				if (!(eTypeName[i].equals("null") || eTypeName[i].equals("")))
					bean.setEmpTypeNameItr(eTypeName[i]);
				else
					bean.setEmpTypeNameItr("-");
				tableList.add(bean);
			}
			if (tableList.size() != 0)
				gratuityConfiguration.setListLength("1");
			else
				gratuityConfiguration.setListLength("0");
			gratuityConfiguration.setFilterTableList(tableList);
		}

		return result;
	}// end checkDuplicate method
	
	public void creditCombination(GratuityConfiguration bean) {
		ArrayList<Object> creditDataList = new ArrayList<Object>();
		Object[][] selectedCreditCode = null;
		Object[][] creditData = null;
		String type = bean.getCreditCodeHid(); // type to which we want adjust
		// credit type i.e. late marks or
		// half day

		logger.info("type    : " + type);
		if (!type.equals("")) {
			String query = "SELECT GRATUITY_AVGSAL_CREDIT_COMP ";

			query += "FROM HRMS_GRATUITY_CONFIG_HDR ";
			// execute query and store result in selectedCreditCode object
			// array
			selectedCreditCode = getSqlModel().getSingleResult(query);
		}

		String[] creditCodeSplit = null;

		/**
		 * if selectedCreditCode array is not null or length of selectedCreditCode is not 0
		 * or element at 0 index in selectedCreditCode array is not null or blank
		 */
		if (selectedCreditCode != null && selectedCreditCode.length != 0
				&& !String.valueOf(selectedCreditCode[0][0]).equals("null")
				&& !String.valueOf(selectedCreditCode[0][0]).equals("")) {

			creditCodeSplit = selectedCreditCode[0][0].toString().split(","); //split selectedCreditCode

			/**
			 * query to select the already saved credit types in saved order
			 */
			String creditDataQuery = "SELECT DISTINCT CREDIT_CODE, CREDIT_NAME, CREDIT_ABBR FROM HRMS_CREDIT_HEAD "
					+ "WHERE CREDIT_CODE IN (" + selectedCreditCode[0][0] + ") AND CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'";

			Object[][] matchingCreditData = getSqlModel().getSingleResult(
					creditDataQuery);

			/**
			 * iterate over matchingCreditData array and store the data in GratuityConfiguration instance
			 * and then add this instance to the list
			 */
			if (matchingCreditData != null && matchingCreditData.length != 0) {
				for (int i = 0; i < creditCodeSplit.length; i++) {
					logger.info("creditCodeSplit[i] : " + creditCodeSplit[i]);
					for (int j = 0; j < matchingCreditData.length; j++) {
						logger.info("matchingCreditData[j][0] : "
								+ matchingCreditData[j][0]);
						if (creditCodeSplit[i].equals(String
								.valueOf(matchingCreditData[j][0]))) {
							GratuityConfiguration bean1 = new GratuityConfiguration();
							bean1.setCreditCode(String
									.valueOf(matchingCreditData[j][0])); // credit
							// code
							bean1.setCreditName(String
									.valueOf(matchingCreditData[j][1]).trim()); // credit
							// name
							bean1.setCreditAbbr(String
									.valueOf(matchingCreditData[j][2]).trim()); // credit
							bean1.setCheck("true"); // abbreviation
							creditDataList.add(bean1);
							break;
						} 
					}
				}
			}

			/**
			 * query to select the rest of credit types 
			 */
			creditDataQuery = "SELECT DISTINCT CREDIT_CODE, CREDIT_NAME, CREDIT_ABBR FROM HRMS_CREDIT_HEAD "
					+ "WHERE CREDIT_CODE NOT IN ("
					+ selectedCreditCode[0][0]
					+ ") AND CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' " + "ORDER BY  CREDIT_CODE";

			creditData = getSqlModel().getSingleResult(creditDataQuery);
		} else { //if no link defined already then select all records from HRMS_LEAVE
			String query = "SELECT DISTINCT CREDIT_CODE, CREDIT_NAME, CREDIT_ABBR FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "	
				+ "ORDER BY  CREDIT_CODE";
			creditData = getSqlModel().getSingleResult(query);
		}

		/**
		 * iterate over creditData array and store the data in AttendanceSettings instance
		 * and then add this instance to the list
		 */
		if (creditData != null && creditData.length != 0) {
			for (int i = 0; i < creditData.length; i++) {
				try {
					GratuityConfiguration bean1 = new GratuityConfiguration();
					bean1.setCreditCode(String.valueOf(creditData[i][0])); //credit code
					bean1.setCreditName(String.valueOf(creditData[i][1]).trim()); //credit name
					bean1.setCreditAbbr(String.valueOf(creditData[i][2]).trim()); //credit abbreviation
					bean1.setCheck("false");
					creditDataList.add(bean1);
				} catch (Exception e) {
					logger.error("error in credit combination " + e);
				} //end of try-catch block
			} //end of for loop

			bean.setCreditTypeFlag("true"); //set creditTypeFlag to true
			bean.setCreditDataList(creditDataList); //set creditDataList
		} //end of if statement
		else {
			bean.setCreditTypeFlag("true");
			bean.setCreditDataList(creditDataList);
		}

	}

	public boolean saveSettings(GratuityConfiguration gratuityConfiguration) {
		boolean result = false;
		String deleteQuery = "DELETE FROM HRMS_GRATUITY_CONFIG_HDR";
		result = getSqlModel().singleExecute(deleteQuery);
		if(result){
			Object saveObj[][] = new Object[1][6];
			logger.info("getGratuityFlag...."+gratuityConfiguration.getGratuityFlag());
			if(gratuityConfiguration.getGratuityFlag().equals("true"))
				saveObj[0][0] = "Y";
			else
				saveObj[0][0] = "N";
			saveObj[0][1] = gratuityConfiguration.getMinimumTenure().trim();
			saveObj[0][2] = gratuityConfiguration.getCreditsCombineCode().trim();
			logger.info("round off()..."+gratuityConfiguration.getRoundOffHidden());
			logger.info("sal option..."+gratuityConfiguration.getSalOptionHidden());
			saveObj[0][3] = gratuityConfiguration.getRoundOffHidden();
			saveObj[0][4] = gratuityConfiguration.getFormula().trim();
			logger.info("formula...."+saveObj[0][4]);
			saveObj[0][5] = gratuityConfiguration.getSalOptionHidden();
			String insertQuery = "INSERT INTO HRMS_GRATUITY_CONFIG_HDR (GRATUITY_APPLICABLE, GRATUITY_MIN_SERV_TENURE, GRATUITY_AVGSAL_CREDIT_COMP, GRATUITY_SERV_YRS_ROUNDEDTO, GRATUITY_FORMULA_FACTOR,GRATUITY_SAL_OPTION)"
				+ " VALUES (?, ?, ?, ?, ?,?)";
			result = getSqlModel().singleExecute(insertQuery, saveObj);
		}
		return result;
	}
	
	public String saveFilterDtl(GratuityConfiguration gratuityConfiguration,
			HttpServletRequest request) {
		String result = "";
		String[] srNo = request.getParameterValues("srNo");
		String[] divCode = request.getParameterValues("divisionCodeItr");
		String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
		String[] deptCode = request.getParameterValues("deptCodeItr");
		String[] brnCode = request.getParameterValues("branchCodeItr");
		String[] desgCode = request.getParameterValues("desgCodeItr");
		String[] gradeCode = request.getParameterValues("gradeCodeItr");

		String delQuery = "DELETE FROM HRMS_GRATUITY_CONFIG_DTL ";
		boolean delObj = getSqlModel().singleExecute(delQuery);
		
		if (delObj) {
			String query = "";
			Object assignObj[][] = new Object[1][7];
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					String codeQuery = "SELECT NVL(MAX(GRATUITY_DTL_CODE),0)+1 FROM HRMS_GRATUITY_CONFIG_DTL";
					Object[][] resultCode = getSqlModel().getSingleResult(codeQuery);
					logger.info("divCode["+i+"]===="+divCode[i]);
					logger.info("eTypeCode["+i+"]===="+eTypeCode[i]);
					logger.info("deptCode["+i+"]===="+deptCode[i]);
					logger.info("brnCode["+i+"]===="+brnCode[i]);
					logger.info("desgCode["+i+"]===="+desgCode[i]);
					logger.info("gradeCode["+i+"]===="+gradeCode[i]);
					//logger.info("resultCode["+i+"]===="+resultCode[i]);
					assignObj[0][0] = checkNull(divCode[i]);
					assignObj[0][1] = checkNull(eTypeCode[i]);
					assignObj[0][2] = checkNull(deptCode[i]);
					assignObj[0][3] = checkNull(brnCode[i]);
					assignObj[0][4] = checkNull(desgCode[i]);
					assignObj[0][5] = checkNull(gradeCode[i]);
					assignObj[0][6] = checkNull(String.valueOf(resultCode[0][0]));
					query = " INSERT INTO HRMS_GRATUITY_CONFIG_DTL (EMP_DIVISION,EMP_TYPE,EMP_DEPT,EMP_CENTER"
						+ " ,EMP_RANK,EMP_GRADE,GRATUITY_DTL_CODE) VALUES (?,?,?,?,?,?,?) ";
					boolean flag = getSqlModel().singleExecute(query, assignObj);
					if(flag){
						result = "setting saved";
					}else
						result = "setting not saved";
				}
			} else {
				result = "setting not saved";
			}
		}
		return result;

	}
	
	/**
	 * Replaces null with blank
	 * 
	 * @param result
	 * @return blank value or original value
	 */
	public String checkNull(String result) {
		try {
			if (result.equals("null")) {
				return "";
			} else {
				return result;
			}
		} catch (Exception e) {
			return "";
		}
	}

	public boolean checkSavedDuplicate(
			GratuityConfiguration gratuityConfiguration, String[] srNo,
			String[] divCode, String[] typeCode, String[] deptCode,
			String[] brnCode, String[] desgCode, String[] gradeCode) {
		// TODO Auto-generated method stub
		return false;
	}

	public void getHeaderRecords(GratuityConfiguration gratuityConfiguration) {
		String hdrQuery = "SELECT GRATUITY_APPLICABLE, GRATUITY_MIN_SERV_TENURE, GRATUITY_AVGSAL_CREDIT_COMP,"
			+ " GRATUITY_SERV_YRS_ROUNDEDTO, GRATUITY_FORMULA_FACTOR, NVL(GRATUITY_SAL_OPTION,'L')"
			+ " FROM HRMS_GRATUITY_CONFIG_HDR";
		Object hdrObj[][] = getSqlModel().getSingleResult(hdrQuery);
		if(hdrObj!=null && hdrObj.length>0){
			if(String.valueOf(hdrObj[0][0]).equals("Y"))
				gratuityConfiguration.setGratuityFlag("true");
			else
				gratuityConfiguration.setGratuityFlag("false");
			gratuityConfiguration.setMinimumTenure(String.valueOf(hdrObj[0][1]));
			gratuityConfiguration.setCreditsCombineCode(checkNull(
					String.valueOf(hdrObj[0][2])).trim());
			gratuityConfiguration.setCreditsCombine(getCreditAbbr(checkNull(
					String.valueOf(hdrObj[0][2])).trim()));
			gratuityConfiguration.setRoundOffHidden(String.valueOf(hdrObj[0][3]));
			
			gratuityConfiguration.setFormula(String.valueOf(hdrObj[0][4]));
			gratuityConfiguration.setSalOptionHidden(String.valueOf(hdrObj[0][5]));
		
				
		}
		
	}
	
	/**
	 * method name : getCreditAbbr purpose : to retrieve the credit abbreviation
	 * for a given credit code return type : String parameter : String creditCode
	 */
	public String getCreditAbbr(String creditCode) {
		String[] creditCodeSplit = creditCode.split(",");
		String creditAbbr = "";

		if (!creditCode.equals("")) {
			for (int i = 0; i < creditCodeSplit.length; i++) {
				logger.info("creditCodeSplit[i]  :" + creditCodeSplit[i]);
				String query = "SELECT CREDIT_ABBR FROM HRMS_CREDIT_HEAD WHERE CREDIT_CODE = "
						+ creditCodeSplit[i];
				Object[][] creditAbbrData = getSqlModel()
						.getSingleResult(query);
				if (creditAbbrData != null && creditAbbrData.length != 0) {
					if (creditAbbr.equals("")) {
						logger.info("in if  : ");
						creditAbbr = String.valueOf(creditAbbrData[0][0])
								.trim();
					} // end of if
					else {
						logger.info("in else  : ");
						creditAbbr += ","
								+ String.valueOf(creditAbbrData[0][0]).trim();
					} // end of else
					logger.info("creditAbbr  : " + creditAbbr);
				} // end of if
			} // end of for loop
		} // end of if

		return creditAbbr;
	}

	public void getFiltersList(GratuityConfiguration gratuityConfiguration,
			HttpServletRequest request) {
		String filterQuery = " SELECT DISTINCT HRMS_DIVISION.DIV_ID, HRMS_DIVISION.DIV_NAME, HRMS_EMP_TYPE.TYPE_ID, HRMS_EMP_TYPE.TYPE_NAME, "
				+ " HRMS_DEPT.DEPT_ID, HRMS_DEPT.DEPT_NAME, HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_ID, "
				+ " HRMS_RANK.RANK_NAME, HRMS_CADRE.CADRE_ID, HRMS_CADRE.CADRE_NAME, GRATUITY_DTL_CODE "
				+ " FROM HRMS_GRATUITY_CONFIG_DTL "
				+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_GRATUITY_CONFIG_DTL.EMP_TYPE) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_GRATUITY_CONFIG_DTL.EMP_DEPT) "
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_GRATUITY_CONFIG_DTL.EMP_CENTER) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_GRATUITY_CONFIG_DTL.EMP_RANK) "
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_GRATUITY_CONFIG_DTL.EMP_DIVISION) "
				+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_GRATUITY_CONFIG_DTL.EMP_GRADE)";
				/*+ " WHERE "// EXTRAWORK_SETTING_CODE =
				// "+workingBenefits.getBenefitSettingID()
				+ " EXTRAWORK_BENEFIT_CODE = "
				+ workingBenefits.getBenefitsID();*/
		Object[][] filterObj = getSqlModel().getSingleResult(filterQuery);

		ArrayList<Object> tableList = new ArrayList<Object>();
		if (filterObj != null && filterObj.length > 0) {
			for (int i = 0; i < filterObj.length; i++) {// loop x
				GratuityConfiguration bean = new GratuityConfiguration();
				bean.setDivisionCodeItr(String.valueOf(filterObj[i][0]));
				if (!(String.valueOf(filterObj[i][1]).equals("null") || String
						.valueOf(filterObj[i][1]).equals("")))
					bean.setDivisionNameItr(String.valueOf(filterObj[i][1]));
				else
					bean.setDivisionNameItr("-");
				bean.setEmpTypeCodeItr(String.valueOf(filterObj[i][2]));
				if (!(String.valueOf(filterObj[i][3]).equals("null") || String
						.valueOf(filterObj[i][3]).equals("")))
					bean.setEmpTypeNameItr(String.valueOf(filterObj[i][3]));
				else
					bean.setEmpTypeNameItr("-");
				bean.setDeptCodeItr(String.valueOf(filterObj[i][4]));
				if (!(String.valueOf(filterObj[i][5]).equals("null") || String
						.valueOf(filterObj[i][5]).equals("")))
					bean.setDeptNameItr(String.valueOf(filterObj[i][5]));
				else
					bean.setDeptNameItr("-");
				bean.setBranchCodeItr(String.valueOf(filterObj[i][6]));
				if (!(String.valueOf(filterObj[i][7]).equals("null") || String
						.valueOf(filterObj[i][7]).equals("")))
					bean.setBranchNameItr(String.valueOf(filterObj[i][7]));
				else
					bean.setBranchNameItr("-");
				bean.setDesgCodeItr(String.valueOf(filterObj[i][8]));
				if (!(String.valueOf(filterObj[i][9]).equals("null") || String
						.valueOf(filterObj[i][9]).equals("")))
					bean.setDesgNameItr(String.valueOf(filterObj[i][9]));
				else
					bean.setDesgNameItr("-");
				bean.setGradeCodeItr(String.valueOf(filterObj[i][10]));
				if (!(String.valueOf(filterObj[i][11]).equals("null") || String
						.valueOf(filterObj[i][11]).equals("")))
					bean.setGradeNameItr(String.valueOf(filterObj[i][11]));
				else
					bean.setGradeNameItr("-");
				bean.setGratuityCode(String.valueOf(filterObj[i][12]));

				tableList.add(bean);
			} // end of for loop x
		}
		gratuityConfiguration.setFilterTableList(tableList);
		if (tableList.size() != 0) {
			gratuityConfiguration.setListLength("1");
		}// //end of if
		else {
			gratuityConfiguration.setListLength("0");
		}// end of else
	}
	public void deleteItem(GratuityConfiguration bean ,HttpServletRequest request){
		
		String[] divCode = request.getParameterValues("divisionCodeItr");
		String[] divName = request.getParameterValues("divisionNameItr");
		String[] gradeCode = request.getParameterValues("gradeCodeItr");
		String[] gradeName = request.getParameterValues("gradeNameItr");
		String[] deptCode = request.getParameterValues("deptCodeItr");
		String[] deptName = request.getParameterValues("deptNameItr");
		String[] brnCode = request.getParameterValues("branchCodeItr");
		String[] brnName = request.getParameterValues("branchNameItr");
		String[] desgCode = request.getParameterValues("desgCodeItr");
		String[] desgName = request.getParameterValues("desgNameItr");
		String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
		String[] eTypeName = request.getParameterValues("empTypeNameItr");
		String[] srNo = request.getParameterValues("srNo");

		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				
				if (i == Integer.parseInt(bean.getHiddenEdit()) - 1) {
					
				} else {
					GratuityConfiguration beanList = new GratuityConfiguration();
					beanList.setSrNo(String.valueOf(i + 1));
					beanList.setDivisionCodeItr(divCode[i]);
					if (!(divName[i].equals("null") || divName[i].equals("")))
						beanList.setDivisionNameItr(divName[i]);
					else
						beanList.setDivisionNameItr("-");
					beanList.setGradeCodeItr(gradeCode[i]);
					if (!(gradeName[i].equals("null") || gradeName[i].equals("")))
						beanList.setGradeNameItr(gradeName[i]);
					else
						beanList.setGradeNameItr("-");
					beanList.setDeptCodeItr(deptCode[i]);
					if (!(deptName[i].equals("null") || deptName[i].equals("")))
						beanList.setDeptNameItr(deptName[i]);
					else
						beanList.setDeptNameItr("-");
					beanList.setBranchCodeItr(brnCode[i]);
					if (!(brnName[i].equals("null") || brnName[i].equals("")))
						beanList.setBranchNameItr(brnName[i]);
					else
						beanList.setBranchNameItr("-");
					beanList.setDesgCodeItr(desgCode[i]);
					if (!(desgName[i].equals("null") || desgName[i].equals("")))
						beanList.setDesgNameItr(desgName[i]);
					else
						beanList.setDesgNameItr("-");
					beanList.setEmpTypeCodeItr(eTypeCode[i]);
					if (!(eTypeName[i].equals("null") || eTypeName[i].equals("")))
						beanList.setEmpTypeNameItr(eTypeName[i]);
					else
						beanList.setEmpTypeNameItr("-");

					tableList.add(beanList);
				}

			}
		}
		if (tableList.size() != 0)
			bean.setListLength("1");
		else
			bean.setListLength("0");
		bean.setFilterTableList(tableList);

	
		
	}
}
