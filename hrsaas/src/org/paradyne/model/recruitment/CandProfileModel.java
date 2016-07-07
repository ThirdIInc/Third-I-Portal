package org.paradyne.model.recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.CandProfile;
import org.paradyne.lib.ModelBase;

/**
 * 
 * @author Pradeep Kumar Sahoo Date:24-06-2009
 * 
 */
public class CandProfileModel extends ModelBase {
	public HttpServletRequest request;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandProfileModel.class);

	/**
	 * following function is called to insert the records in the
	 * HRMS_REC_CDOL_DATABANK
	 * 
	 * @param bean
	 * @return
	 */
	public boolean saveFirst(CandProfile bean) {
		boolean result = false;

		try {
			String query = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] dt = getSqlModel().getSingleResult(query);
			Object[][] addObj = new Object[1][25];
			addObj[0][0] = bean.getFirstName();
			addObj[0][1] = bean.getMiddleName();
			addObj[0][2] = bean.getLastName();
			addObj[0][3] = bean.getDob();
			addObj[0][4] = bean.getGender();
			addObj[0][5] = bean.getMaritalStatus();
			addObj[0][6] = bean.getMinExp();
			addObj[0][7] = bean.getMaxExp();
			addObj[0][8] = bean.getCurrentCtc();
			addObj[0][9] = bean.getExpectedCtc();
			addObj[0][10] = bean.getResPhone();
			addObj[0][11] = bean.getMobile();
			addObj[0][12] = bean.getOffPhone();
			addObj[0][13] = bean.getEmail();
			addObj[0][14] = bean.getWheYouEmp();
			addObj[0][15] = bean.getRelocate();
			addObj[0][16] = bean.getDoYou();
			addObj[0][17] = bean.getRefEmpName();
			addObj[0][18] = String.valueOf("D");
			addObj[0][19] = bean.getUploadFileName();
			addObj[0][20] = bean.getUploadPhoto();
			addObj[0][21] = bean.getOtherInfo();
			addObj[0][22] = String.valueOf(dt[0][0]);
			addObj[0][23] = bean.getPassport();
			addObj[0][24] = bean.getPanNo();
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			
			/*String updateQuery = "UPDATE HRMS_REC_CAND_LOGIN SET CAND_FIRSTNAME = '"+bean.getFirstName()+"', CAND_LASTNAME = '"+bean.getLastName()+"' WHERE CAND_CODE = "+bean.getCandidateUserEmpId();
			getSqlModel().singleExecute(updateQuery);*/
			if (result) {
				String maxQuery = "SELECT MAX(CAND_CODE) FROM HRMS_REC_CDOL_DATABANK";
				Object[][] candCode = getSqlModel().getSingleResult(maxQuery);
				if (candCode != null)
					bean.setCandCode(String.valueOf(candCode[0][0]));

				Object[][] tempAddr = new Object[1][7];
				tempAddr[0][0] = bean.getCandCode();
				tempAddr[0][1] = bean.getCityCode();
				tempAddr[0][2] = bean.getStateCode();
				tempAddr[0][3] = bean.getCountryCode();
				tempAddr[0][4] = bean.getPincode();
				tempAddr[0][5] = bean.getAddress();
				tempAddr[0][6] = String.valueOf("C");

				Object[][] permAddr = new Object[1][7];
				permAddr[0][0] = bean.getCandCode();
				permAddr[0][1] = bean.getCityCode1();
				permAddr[0][2] = bean.getStateCode1();
				permAddr[0][3] = bean.getCountryCode1();
				permAddr[0][4] = bean.getPincode1();
				permAddr[0][5] = bean.getAddress1();
				permAddr[0][6] = String.valueOf("P");

				result = getSqlModel().singleExecute(getQuery(7), tempAddr);
				result = getSqlModel().singleExecute(getQuery(8), permAddr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/*
	 * Method to replace the null with a space. @param result @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}
		else {
			return result;
		}
	}

	/**
	 * following function is called to update the first page
	 * 
	 * @param bean
	 * @return
	 */

	public boolean updateFirst(CandProfile bean) {
		boolean result = false;
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysDate = formater.format(date);
			String deleteQuery = "DELETE FROM HRMS_REC_CDOL_ADDRESSDTL WHERE CAND_CODE="
					+ bean.getCandCode();
			getSqlModel().singleExecute(deleteQuery);
			Object[][] addObj = new Object[1][25];
			addObj[0][0] = bean.getFirstName();
			addObj[0][1] = bean.getMiddleName();
			addObj[0][2] = bean.getLastName();
			addObj[0][3] = bean.getDob();
			addObj[0][4] = bean.getGender();
			addObj[0][5] = bean.getMaritalStatus();
			addObj[0][6] = bean.getMinExp();
			addObj[0][7] = bean.getMaxExp();
			if (bean.getCurrentCtc().equals("null")
					|| bean.getCurrentCtc().equals("")) {
				addObj[0][8] = String.valueOf("");
			} else {
				addObj[0][8] = bean.getCurrentCtc();
			}
			if (bean.getExpectedCtc().equals("null")
					|| bean.getExpectedCtc().equals("")) {
				addObj[0][9] = String.valueOf("");
			} else {
				addObj[0][9] = bean.getExpectedCtc();
			}
			addObj[0][10] = bean.getResPhone();
			addObj[0][11] = bean.getMobile();
			addObj[0][12] = bean.getOffPhone();
			addObj[0][13] = bean.getEmail();
			addObj[0][14] = bean.getWheYouEmp();
			addObj[0][15] = bean.getRelocate();
			addObj[0][16] = bean.getDoYou();
			if (bean.getRadioEmp().equals("Y")) {
				bean.setRadioFlag("true");
			} else {
				bean.setRadioFlag("false");
			}
			addObj[0][17] = String.valueOf("");
			addObj[0][18] = sysDate;
			addObj[0][19] = bean.getUploadFileName();
			addObj[0][20] = bean.getUploadPhoto();
			addObj[0][21] = bean.getOtherInfo();
			addObj[0][22] = bean.getPassport();
			addObj[0][23] = bean.getPanNo();
			addObj[0][24] = bean.getCandCode();
			Object[][] tempAddr = new Object[1][7];
			tempAddr[0][0] = bean.getCandCode();
			tempAddr[0][1] = bean.getCityCode();
			tempAddr[0][2] = bean.getStateCode();
			tempAddr[0][3] = bean.getCountryCode();
			tempAddr[0][4] = bean.getPincode();
			tempAddr[0][5] = bean.getAddress();
			tempAddr[0][6] = String.valueOf("C");
			Object[][] permAddr = new Object[1][7];
			permAddr[0][0] = bean.getCandCode();
			permAddr[0][1] = bean.getCityCode1();
			permAddr[0][2] = bean.getStateCode1();
			permAddr[0][3] = bean.getCountryCode1();
			permAddr[0][4] = bean.getPincode1();
			permAddr[0][5] = bean.getAddress1();
			permAddr[0][6] = String.valueOf("P");
			getSqlModel().singleExecute(getQuery(7), tempAddr);
			getSqlModel().singleExecute(getQuery(8), permAddr);
			result = getSqlModel().singleExecute(getQuery(9), addObj);
			
			/*String updateQuery = "UPDATE HRMS_REC_CAND_LOGIN SET CAND_FIRSTNAME = '"+bean.getFirstName()+"', CAND_LASTNAME = '"+bean.getLastName()+"' WHERE CAND_CODE = "+bean.getCandidateUserEmpId();
			getSqlModel().singleExecute(updateQuery);*/
			if (!(bean.getRefEmpName().equals("")
					|| bean.getRefEmpName().equals("null") || bean
					.getRefEmpName().equals(" "))) {

				Object[][] updateRef = new Object[1][2];
				updateRef[0][0] = bean.getRefEmpName();
				updateRef[0][1] = bean.getCandCode();
				result = getSqlModel().singleExecute(getQuery(10), updateRef);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to save the reference details in table
	 * HRMS_REC_CDOL_REFDTL
	 * 
	 * @param bean
	 * @param candCode
	 */
	public boolean saveRefDet(CandProfile bean, String candCode,
			HttpServletRequest request) {
		boolean result = false;
		String delRefQuery = "DELETE FROM HRMS_REC_CDOL_REFDTL WHERE CAND_CODE=" + candCode;
		getSqlModel().singleExecute(delRefQuery);
		try {
			String[] refName = request.getParameterValues("refName");
			String[] profession = request.getParameterValues("profession");
			String[] refContact = request.getParameterValues("contactDet");
			String[] refComments = request.getParameterValues("refComment");
			if (refName != null) {
				if (refName.length > 0) {
					Object[][] refObj = new Object[refName.length][5];
					for (int i = 0; i < refName.length; i++) {
						if (!(refName[i].trim().equals("null")
								|| refName[i].trim().equals("") || refName[i]
								.trim().equals(" "))) {
							refObj[i][0] = candCode;
							refObj[i][1] = refName[i];
							refObj[i][2] = profession[i];
							refObj[i][3] = refContact[i];
							refObj[i][4] = refComments[i];
						}

					}
					result = getSqlModel().singleExecute(getQuery(2), refObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in saveRefDet method ", e);
		}
		return result;
	}

	/**
	 * following function is called to save the qualification details
	 * 
	 * @param candCode
	 * @param request
	 */
	public boolean saveQual(String candCode, HttpServletRequest request) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HRMS_REC_CDOL_QUADTL WHERE CAND_CODE="
					+ candCode;
			getSqlModel().singleExecute(deleteQuery);
			String[] estbName = request.getParameterValues("estbName");
			String[] yearofPass = request.getParameterValues("yearofPass");
			if (estbName != null) {
				int j = 1;
				if (estbName.length > 0) {

					Object[][] qualObj = new Object[estbName.length][6];
					for (int i = 0; i < estbName.length; i++) {
						String code = (String) request.getParameter("qualId"
								+ j);
						if (!(code.equals("") || code.equals("null") || code
								.equals(" "))) {
							qualObj[i][0] = candCode;
							qualObj[i][1] = code;// (String)request.getParameter("qualId"+j);
							qualObj[i][2] = (String) request
									.getParameter("splId" + j);
							qualObj[i][3] = estbName[i];
							qualObj[i][4] = yearofPass[i];
							qualObj[i][5] = "";
						}
						j++;
					}
					result = getSqlModel().singleExecute(getQuery(3), qualObj);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to save the exp details in the list.
	 * 
	 * @param candCode
	 * @param request
	 */

	public boolean saveExp(String candCode, HttpServletRequest request) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HRMS_REC_CDOL_EXPDTL WHERE CAND_CODE="
					+ candCode;
			getSqlModel().singleExecute(deleteQuery);
			String[] emprName = request.getParameterValues("emprName");
			String[] lastJobPro = request.getParameterValues("lastJobPro");
			String[] joinDate = request.getParameterValues("joinDate");
			String[] relvDate = request.getParameterValues("relvDate");
			String[] specAch = request.getParameterValues("specAch");
			String[] ctcExp = request.getParameterValues("ctcExp");
			String[] jobExp = null;

			if (emprName != null) {

				int j = 1;
				jobExp = new String[emprName.length];
				if (emprName.length > 0) {
					Object[][] expObj = new Object[emprName.length][9];
					for (int i = 0; i < emprName.length; i++) {
						if (!(emprName[i].trim().equals("")
								|| emprName[i].trim().equals("null") || emprName[i]
								.trim().equals(" "))) {
							jobExp[i] = (String) request.getParameter("jdExp"
									+ j);
							expObj[i][0] = candCode;
							expObj[i][1] = emprName[i];
							expObj[i][2] = lastJobPro[i];
							expObj[i][3] = joinDate[i];
							expObj[i][4] = relvDate[i];
							expObj[i][5] = jobExp[i];// (String)request.getParameter("jdExp");
							expObj[i][6] = (String) request
									.getParameter("indsId" + j);
							expObj[i][7] = specAch[i];
							expObj[i][8] = ctcExp[i];
						}
						j++;

					}
					result = getSqlModel().singleExecute(getQuery(4), expObj);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to save the skill details in the list.
	 * 
	 * @param candCode
	 * @param request
	 */
	public boolean saveSkill(String candCode, HttpServletRequest request) {
		boolean result = false;
		try {
			String[] skillExp = request.getParameterValues("skillExp");
			String deleteQuery = "DELETE FROM HRMS_REC_CDOL_SKILLDTL WHERE CAND_CODE="
					+ candCode;
			getSqlModel().singleExecute(deleteQuery);
			if (skillExp != null) {

				int j = 1;
				if (skillExp.length > 0) {
					Object[][] skillObj = new Object[skillExp.length][4];
					for (int i = 0; i < skillObj.length; i++) {
						String code = (String) request.getParameter("skillCode"
								+ j);
						if (!(code.equals("") || code.equals("null") || code
								.equals(" "))) {
							skillObj[i][0] = candCode;
							skillObj[i][1] = "S";
							skillObj[i][2] = code;
							skillObj[i][3] = skillExp[i];
						}
						j++;
					}

					result = getSqlModel().singleExecute(getQuery(5), skillObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * following function is called to save the functional/domain details from
	 * the list domain/functional
	 */
	public boolean saveFunc(String candCode, HttpServletRequest request) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HRMS_REC_CDOL_FUNCDTL WHERE CAND_CODE="
					+ candCode;
			getSqlModel().singleExecute(deleteQuery);
			String[] funcExp = request.getParameterValues("funcExp");
			if (funcExp != null) {
				int j = 1;
				if (funcExp.length > 0) {

					Object[][] funcObj = new Object[funcExp.length][4];
					for (int i = 0; i < funcObj.length; i++) {
						String code = (String) request.getParameter("funcCode"
								+ j);
						if (!(code.equals("") || code.equals("null") || code
								.equals(" "))) {
							funcObj[i][0] = candCode;
							funcObj[i][1] = "F";
							funcObj[i][2] = (String) request
									.getParameter("funcCode" + j);
							funcObj[i][3] = funcExp[i];
						}
						logger.info("function code in action" + funcObj[i][2]);
						j++;
					}

					result = getSqlModel().singleExecute(getQuery(6), funcObj);// saveDomainOrFunction(candCode,funcObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to display the Candidate details,address
	 * details etc.
	 * 
	 * @param candDB
	 * @param str
	 * @param request
	 */
	public void searchCandDet(CandProfile candProf, HttpServletRequest request) {
		try {
			if (!(candProf.getCandCode().equals("") || candProf.getCandCode()
					.equals("null"))) {
				String tempAddrsQuery = " SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' '),HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_STATE,HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD,' '),HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION "
						+ " LEFT JOIN HRMS_REC_CDOL_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_CITY)"
						+ " LEFT JOIN HRMS_LOCATION state on(state.LOCATION_CODE=HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_STATE)"
						+ " LEFT JOIN HRMS_LOCATION country on(country.LOCATION_CODE=HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_COUNTRY)"
						+ " where HRMS_REC_CDOL_ADDRESSDTL.CAND_CODE="
						+ candProf.getCandCode()
						+ " and HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_TYPE='C' ";
				Object[][] candCurrAdd = getSqlModel().getSingleResult(
						tempAddrsQuery);

				if (candCurrAdd != null && candCurrAdd.length > 0) {
					candProf.setCity(checkNull(String
							.valueOf(candCurrAdd[0][0])));
					candProf.setState(checkNull(String
							.valueOf(candCurrAdd[0][1])));
					candProf.setCountry(checkNull(String
							.valueOf(candCurrAdd[0][2])));
					candProf.setCityCode(checkNull(String
							.valueOf(candCurrAdd[0][3])));
					candProf.setStateCode(checkNull(String
							.valueOf(candCurrAdd[0][4])));
					candProf.setCountryCode(checkNull(String
							.valueOf(candCurrAdd[0][5])));
					if (String.valueOf(candCurrAdd[0][6]).equals("")
							|| String.valueOf(candCurrAdd[0][6]).equals("null")) {
						candProf.setAddress("");
					} else {
						candProf.setAddress(String.valueOf(candCurrAdd[0][6])
								.trim());
					}
					if (String.valueOf(candCurrAdd[0][7]).equals("null")
							|| String.valueOf(candCurrAdd[0][7]).equals("")
							|| String.valueOf(candCurrAdd[0][7]).equals(" ")) {
						candProf.setPincode("");
					} else {
						candProf.setPincode(String.valueOf(candCurrAdd[0][7]));
					}

				}

				String permAddrsQuery = " SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' '),HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_STATE,HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD,' '),HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION "
						+ " LEFT JOIN HRMS_REC_CDOL_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_CITY)"
						+ " LEFT JOIN HRMS_LOCATION state on(state.LOCATION_CODE=HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_STATE)"
						+ " LEFT JOIN HRMS_LOCATION country on(country.LOCATION_CODE=HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_COUNTRY)"
						+ " where HRMS_REC_CDOL_ADDRESSDTL.CAND_CODE="
						+ candProf.getCandCode()
						+ " and HRMS_REC_CDOL_ADDRESSDTL.CAND_ADD_TYPE='P' ";
				Object[][] permAdd = getSqlModel().getSingleResult(
						permAddrsQuery);
				if (permAdd != null && permAdd.length > 0) {
					candProf.setCity1(checkNull(String.valueOf(permAdd[0][0])));
					candProf
							.setState1(checkNull(String.valueOf(permAdd[0][1])));
					candProf.setCountry1(checkNull(String
							.valueOf(permAdd[0][2])));
					candProf.setCityCode1(checkNull(String
							.valueOf(permAdd[0][3])));
					candProf.setStateCode1(checkNull(String
							.valueOf(permAdd[0][4])));
					candProf.setCountryCode1(checkNull(String
							.valueOf(permAdd[0][5])));
					if (String.valueOf(permAdd[0][6]).equals("")
							|| String.valueOf(permAdd[0][6]).equals("null")) {
						candProf.setAddress1("");
					} else {
						candProf.setAddress1(String.valueOf(permAdd[0][6])
								.trim());
					}
					candProf.setAddress1(String.valueOf(permAdd[0][6]).trim());
					if (String.valueOf(permAdd[0][7]).equals("null")
							|| String.valueOf(permAdd[0][7]).equals("")
							|| String.valueOf(permAdd[0][7]).equals(" ")) {
						candProf.setPincode1("");
					} else {
						candProf.setPincode1(String.valueOf(permAdd[0][7]));
					}
				}
				String query = "SELECT  NVL(CAND_FIRST_NAME,' '), NVL(CAND_MID_NAME,' '), NVL(CAND_LAST_NAME,' '),TO_CHAR(CAND_DOB,'DD-MM-YYYY'), CAND_GENDER, "
						+ "CAND_MART_STATUS, CAND_OFF_PHONE, CAND_MOB_PHONE, CAND_RES_PHONE, CAND_EMAIL_ID, "
						+ "CAND_EMPLOYED, CAND_RELOCATION, CAND_EXP_YEAR, CAND_EXP_MONTH, CAND_DOYOUKNOW, "
						+ "NVL(CAND_REF_EMPID,' '),DECODE(CAND_GENDER,'M','Male','F','Female','O','Other'),DECODE(CAND_MART_STATUS,'S','Single','M','Married','D','Divorced'),"
						+ " NVL(CAND_RESUME,' '),NVL(CAND_PHOTO,' '),' ',' ',NVL(CAND_REF_EMPID,' '),CAND_CURR_CTC,CAND_EXPC_CTC,"
						+ " CAND_OTHER_INFO,NVL(CAND_PASSPORT_NO,' '),NVL(CAND_PAN_NO,' ') FROM HRMS_REC_CDOL_DATABANK "
						+
						// "LEFT JOIN HRMS_EMP_OFFC
						// ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_CAND_DATABANK.CAND_REF_EMPID)"+
						" WHERE CAND_CODE=" + candProf.getCandCode();
				String exp = "";
				Object[][] candDet = getSqlModel().getSingleResult(query);
				if (candDet != null && candDet.length > 0) {

					String yr = String.valueOf(candDet[0][12]);
					String mn = String.valueOf(candDet[0][13]);

					double mon;
					double year;
					if (yr.equals("null") || yr.equals("")) {
						year = 0;
					} else {
						year = Double.parseDouble(yr);
					}
					if (mn.equals("null") || mn.equals("")) {
						mon = 0;
					} else {
						mon = Double.parseDouble(mn);
					}

					if (mon != 0 && year != 0) {
						if (year > 1 && mon > 1) {

							exp += year + " " + "Years " + mon + " " + "Months";
						} else if (!(year > 1 && mon > 1)) {

							exp += year + " " + " Year " + mon + " " + "Month";
						} else if (year > 1 && (!(mon > 1))) {

							exp += year + " " + " Years " + mon + " " + "Month";
						} else if ((!(year > 1)) && mon > 1) {

							exp += year + " " + " Year " + mon + " " + "Months";
						}
					} else {

						if (year > 1) {

							exp += year + " " + "Years ";
						} else if (!(year > 1)) {

							exp += year + " " + " Year ";
						}

					}

					candProf.setExpView(exp);

					if (String.valueOf(candDet[0][0]).equals("null"))
						candProf.setFirstName("");
					else
						candProf.setFirstName(String.valueOf(candDet[0][0]));

					if (String.valueOf(candDet[0][1]).equals("null"))
						candProf.setMiddleName("");
					else
						candProf.setMiddleName(String.valueOf(candDet[0][1]));

					if (String.valueOf(candDet[0][2]).equals("null"))
						candProf.setLastName("");
					else
						candProf.setLastName(String.valueOf(candDet[0][2]));

					candProf.setDob(checkNull(String.valueOf(candDet[0][3])));
					candProf
							.setGender(checkNull(String.valueOf(candDet[0][4])));
					candProf.setMaritalStatus(checkNull(String
							.valueOf(candDet[0][5])));
					if (String.valueOf(candDet[0][6]).equals("null")) {
						candProf.setOffPhone("");
					} else {
						candProf.setOffPhone(String.valueOf(candDet[0][6]));
					}
					if (String.valueOf(candDet[0][7]).equals("null")) {
						candProf.setMobile("");
					} else {
						candProf.setMobile(String.valueOf(candDet[0][7]));
					}
					if (String.valueOf(candDet[0][8]).equals("null"))
						candProf.setResPhone("");
					else
						candProf.setResPhone(String.valueOf(candDet[0][8]));
					if (String.valueOf(candDet[0][9]).equals("null"))
						candProf.setEmail("");
					else
						candProf.setEmail(String.valueOf(candDet[0][9]));
					if (String.valueOf(candDet[0][10]).equals("null"))
						candProf.setWheYouEmp("");
					else
						candProf.setWheYouEmp(String.valueOf(candDet[0][10]));

					if (String.valueOf(candDet[0][11]).equals("null"))
						candProf.setRelocate("");
					else
						candProf.setRelocate(String.valueOf(candDet[0][11]));
					if (String.valueOf(candDet[0][12]).equals("null"))
						candProf.setMinExp("");
					else
						candProf.setMinExp(String.valueOf(candDet[0][12]));
					if (String.valueOf(candDet[0][13]).equals("null"))
						candProf.setMaxExp("");
					else
						candProf.setMaxExp(String.valueOf(candDet[0][13]));

					if (String.valueOf(candDet[0][14]).equals("null"))
						candProf.setDoYou("");
					else
						candProf.setDoYou(String.valueOf(candDet[0][14]));

					if (String.valueOf(candDet[0][15]).equals("null"))
						candProf.setRefEmpId("");
					else
						candProf.setRefEmpId(String.valueOf(candDet[0][15]));

					candProf.setGenderView(checkNull(String
							.valueOf(candDet[0][16])));
					candProf.setMarriageView(checkNull(String
							.valueOf(candDet[0][17])));
					candProf.setUploadFileName(checkNull(String
							.valueOf(candDet[0][18])));
					candProf.setUploadPhoto(checkNull(String
							.valueOf(candDet[0][19])));
					candProf.setRefEmpTok(checkNull(String.valueOf(
							candDet[0][20]).trim()));
					candProf.setRefEmpName(checkNull(String.valueOf(
							candDet[0][22]).trim()));
					// candProf.setRefEmpId(String.valueOf(candDet[0][22]).trim());
					if (String.valueOf(candDet[0][23]).trim().equals("null")
							|| String.valueOf(candDet[0][23]).trim().equals("")) {
						candProf.setCurrentCtc("");
					} else {
						candProf.setCurrentCtc(String.valueOf(candDet[0][23])
								.trim());
					}

					if (String.valueOf(candDet[0][24]).trim().equals("null")
							|| String.valueOf(candDet[0][24]).trim().equals(
									"null")) {
						candProf.setExpectedCtc("");
					} else {
						candProf.setExpectedCtc(String.valueOf(candDet[0][24])
								.trim());
					}

					if (String.valueOf(candDet[0][25]).trim().equals("")
							|| String.valueOf(candDet[0][25]).trim().equals(
									"null")) {
						candProf.setOtherInfo("");
					} else {
						candProf.setOtherInfo(String.valueOf(candDet[0][25])
								.trim());
					}

					if (String.valueOf(candDet[0][26]).trim().equals("")
							|| String.valueOf(candDet[0][26]).trim().equals(
									"null")) {
						candProf.setPassport("");
					} else {
						candProf.setPassport(String.valueOf(candDet[0][26])
								.trim());
					}

					if (String.valueOf(candDet[0][27]).trim().equals("")
							|| String.valueOf(candDet[0][27]).trim().equals(
									"null")) {
						candProf.setPanNo("");
					} else {
						candProf
								.setPanNo(String.valueOf(candDet[0][27]).trim());
					}

					if (String.valueOf(candDet[0][14]).equals("Y")) {// do u
																		// know
																		// anybody
						candProf.setRadioFlag("true");
					} else {
						candProf.setRadioFlag("false");
					}

					if (String.valueOf(candDet[0][10]).equals("Y")) {// Whether
																		// u
																		// employed
																		// earlier
																		// in
																		// the
																		// group
						candProf.setRadioFlag1("true");
					} else {
						candProf.setRadioFlag1("false");
					}

					if (String.valueOf(candDet[0][11]).equals("Y")) {// Relocate
						candProf.setRadioFlag2("true");
					} else {
						candProf.setRadioFlag2("false");
					}

					String photo = candProf.getUploadPhoto();
					request.setAttribute("photo", photo);
				} else {
					request.setAttribute("photo", "");
				}
			}
		} catch (RuntimeException e) {
			logger.info("Exception in searchCandDet method", e);
		}
	}

	/**
	 * following function is called to display the reference details
	 * 
	 * @param candDB
	 */
	public void searchRefDtl(CandProfile candProf) {
		try {
			String query = "SELECT CAND_DTL_CODE, NVL(CAND_REFNAME,' '), NVL(CAND_PROFES,' '), NVL(CAND_CONTACT,' '), "
					+ " NVL(CAND_COMMENTS,' ') FROM HRMS_REC_CDOL_REFDTL "
					+ " WHERE CAND_CODE=" + candProf.getCandCode();
			Object[][] candRefDets = getSqlModel().getSingleResult(query);
			if (candRefDets != null && candRefDets.length > 0) {
				candProf.setRefFlag("true");
				ArrayList list = new ArrayList();
				for (int i = 0; i < candRefDets.length; i++) {
					CandProfile loopBean = new CandProfile();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setRefDtlCode(checkNull(String.valueOf(String
							.valueOf(candRefDets[i][0]))));
					loopBean.setRefName(checkNull(String.valueOf(String
							.valueOf(candRefDets[i][1]))));
					loopBean.setProfession(checkNull(String.valueOf(String
							.valueOf(candRefDets[i][2]))));
					loopBean.setContactDet(checkNull(String.valueOf(String
							.valueOf(candRefDets[i][3]))));
					loopBean.setRefComment(checkNull(String.valueOf(String
							.valueOf(candRefDets[i][4]))));
					list.add(loopBean);
				}// end of if statement

				candProf.setRefList(list);
			} else {
				candProf.setRefFlag("false");
			}
		} catch (RuntimeException e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * following function is called to remove records from Qualification List
	 * 
	 * @param candProf
	 * @param name
	 * @param code
	 * @param lvl
	 * @param spN
	 * @param spCode
	 * @param del
	 * @param year
	 * @param est
	 * @param detCode
	 * @param request
	 */
	public void delQual(CandProfile candProf, String[] name, String[] code,
			String[] lvl, String[] spN, String[] spCode, String[] del,
			String[] year, String[] est, String[] detCode,
			HttpServletRequest request) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();

			int counter = 0;
			for (int i = 0; i < name.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			Object qualObj[][] = new Object[counter][5];
			if (est != null) {
				int m = 0;

				for (int i = 0; i < est.length; i++) {
					CandProfile bean = new CandProfile();
					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setYearofPass(year[i]);
						bean.setEstbName(est[i]);
						bean.setQualDetCode(detCode[i]);
						qualObj[m][0] = name[i];
						qualObj[m][1] = lvl[i];
						qualObj[m][2] = spN[i];
						qualObj[m][3] = code[i];
						qualObj[m][4] = spCode[i];

						tableList.add(bean);
						m++;

					}

				}

			}
			candProf.setQualList(tableList);
			request.setAttribute("qualObj", qualObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to remove records from exp history when
	 * delete button is clicked.
	 * 
	 * @param candProf
	 * @param employer
	 * @param profession
	 * @param joiningDate
	 * @param relieving
	 * @param splAch
	 * @param ctc
	 * @param detCode
	 * @param indsType
	 * @param indCode
	 * @param jobDescr
	 * @param del
	 * @param request
	 */
	public void delExp(CandProfile candProf, String[] employer,
			String[] profession, String[] joiningDate, String[] relieving,
			String[] splAch, String[] ctc, String[] detCode, String[] indsType,
			String[] indCode, String[] jobDescr, String[] del,
			HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < employer.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}

			Object expObj[][] = new Object[counter][3];
			if (employer != null) {
				int m = 0;

				for (int i = 0; i < employer.length; i++) {
					CandProfile bean = new CandProfile();

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setEmprName(employer[i]);
						bean.setLastJobPro(profession[i]);
						bean.setJoinDate(joiningDate[i]);
						bean.setRelvDate(relieving[i]);
						bean.setSpecAch(splAch[i]);
						bean.setCtcExp(ctc[i]);
						bean.setExpDtlId(detCode[i]);
						expObj[m][0] = jobDescr[i];
						expObj[m][1] = indsType[i];
						expObj[m][2] = indCode[i];
						tableList.add(bean);

						m++;
					}

				}

			}
			candProf.setExpList(tableList);
			request.setAttribute("expObj", expObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called when delete button in the Domain/Functional
	 * list is clicked to remove records from the list.
	 * 
	 * @param candProf
	 * @param exp
	 * @param detCode
	 * @param funCode
	 * @param name
	 * @param del
	 * @param request
	 */
	public void delFunc(CandProfile candProf, String[] exp, String[] detCode,
			String[] funCode, String[] name, String[] del,
			HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < exp.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}

			Object funcObj[][] = new Object[counter][2];
			if (exp != null) {
				int m = 0;

				for (int i = 0; i < exp.length; i++) {
					CandProfile bean = new CandProfile();

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setFuncExp(exp[i]);
						bean.setFuncDtlId(detCode[i]);
						funcObj[m][0] = name[i];
						funcObj[m][1] = funCode[i];
						tableList.add(bean);
						m++;
					}

				}

			}
			candProf.setFuncList(tableList);
			request.setAttribute("funcObj", funcObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called when delete button is clicked in skill list
	 * to remove records from the list.
	 * 
	 * @param candProf
	 * @param exp
	 * @param detCode
	 * @param skillCode
	 * @param name
	 * @param del
	 * @param request
	 */
	public void delSkill(CandProfile candProf, String[] exp, String[] detCode,
			String[] skillCode, String[] name, String[] del,
			HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < exp.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			logger.info("val of counter" + counter);
			Object skillObj[][] = new Object[counter][2];
			if (exp != null) {
				int m = 0;

				for (int i = 0; i < exp.length; i++) {
					CandProfile bean = new CandProfile();

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setSkillExp(exp[i]);
						bean.setSkillDtlId(detCode[i]);
						skillObj[m][0] = name[i];
						skillObj[m][1] = skillCode[i];
						tableList.add(bean);
						m++;
					}

				}

			}
			candProf.setSkillList(tableList);
			request.setAttribute("skillObj", skillObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to delete the records from Reference List
	 * when Delete button is clicked.
	 * 
	 * @param candProf
	 * @param ref
	 * @param profession
	 * @param contact
	 * @param comment
	 * @param code
	 * @param del
	 * @param request
	 */
	public void delRef(CandProfile candProf, String[] ref, String[] profession,
			String[] contact, String[] comment, String[] code, String[] del,
			HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < ref.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			if (ref != null) {
				int m = 0;
				for (int i = 0; i < ref.length; i++) {
					CandProfile bean = new CandProfile();
					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setRefName(ref[i]);
						bean.setProfession(profession[i]);
						bean.setContactDet(contact[i]);
						bean.setRefComment(comment[i]);
						bean.setRefDtlCode(code[i]);
						tableList.add(bean);

						m++;
					}
				}
			}
			candProf.setRefList(tableList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to display by default one row in the exp
	 * history
	 * 
	 * @param bean
	 * @param request
	 */
	public void addRowExp(CandProfile bean, HttpServletRequest request) {
		try {
			ArrayList list = new ArrayList();
			Object expObj[][] = new Object[1][3];
			CandProfile bean1 = new CandProfile();
			bean1.setSrNo(String.valueOf(1));
			bean1.setExpDtlId("");
			bean1.setEmprName("");
			bean1.setLastJobPro("");
			bean1.setJoinDate("");
			bean1.setRelvDate("");
			expObj[0][0] = "";
			bean1.setSpecAch("");
			expObj[0][1] = "";
			expObj[0][2] = "";
			bean1.setIndsId("");
			bean1.setCtcExp("");
			list.add(bean1);
			bean.setExpList(list);
			request.setAttribute("expObj", expObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method

	/**
	 * following function is called to display one row by default in
	 * Qualification details
	 * 
	 * @param bean
	 * @param request
	 */
	public void addRowQual(CandProfile bean, HttpServletRequest request) {
		try {
			ArrayList list = new ArrayList();
			Object qualObj[][] = new Object[1][5];
			CandProfile bean1 = new CandProfile();
			bean1.setSrNo(String.valueOf(1));
			bean1.setQualDtlId("");
			qualObj[0][0] = "";
			bean1.setSpelDtlId("");
			qualObj[0][1] = "";
			qualObj[0][2] = "";
			qualObj[0][3] = "";
			qualObj[0][4] = "";
			bean1.setEstbName("");
			bean1.setYearofPass("");
			list.add(bean1);
			bean.setQualList(list);
			request.setAttribute("qualObj", qualObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method

	/**
	 * following function is called to display one row by default in Skill
	 * details
	 * 
	 * @param bean
	 * @param request
	 */
	public void addRowSkill(CandProfile bean, HttpServletRequest request) {
		try {
			ArrayList list = new ArrayList();
			Object skillObj[][] = new Object[1][2];
			CandProfile bean1 = new CandProfile();
			bean1.setSrNo(String.valueOf(1));
			bean1.setSkillDtlId("");
			skillObj[0][0] = "";
			skillObj[0][1] = "";
			bean1.setSkillCode("");
			bean1.setSkillExp("");
			list.add(bean1);
			bean.setSkillList(list);
			request.setAttribute("skillObj", skillObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method

	/**
	 * following function is called to display one row by default in domain
	 * details
	 * 
	 * @param bean
	 * @param request
	 */
	public void addRowFunc(CandProfile bean, HttpServletRequest request) {
		try {
			ArrayList list = new ArrayList();
			Object funcObj[][] = new Object[1][2];
			CandProfile bean1 = new CandProfile();
			bean1.setSrNo(String.valueOf(1));
			bean1.setFuncDtlId("");
			funcObj[0][0] = "";
			funcObj[0][1] = "";
			bean1.setFuncCode("");
			bean1.setFuncExp("");
			list.add(bean1);
			bean.setFuncList(list);
			request.setAttribute("funcObj", funcObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method

	/**
	 * following function is called to retrieve the records in Qualification
	 * List
	 * 
	 * @param candProf
	 * @param request
	 */
	public void retCandQual(CandProfile candProf, HttpServletRequest request) {
		try {
			String query = "SELECT  CAND_DTL_CODE, CAND_QUA_CODE,NVL(QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
					+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
					+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,CAND_SPEC_CODE,NVL(SPEC_ABBR,' '), NVL(CAND_ESTB_NAME,' '), "
					+ " CAND_YEAR_PASS, CAND_PERC_MARKS FROM HRMS_REC_CDOL_QUADTL "
					+ " LEFT JOIN HRMS_QUA ON  HRMS_QUA.QUA_ID = HRMS_REC_CDOL_QUADTL.CAND_QUA_CODE"
					+ " LEFT JOIN HRMS_SPECIALIZATION ON HRMS_SPECIALIZATION.SPEC_ID = HRMS_REC_CDOL_QUADTL.CAND_SPEC_CODE "
					+ " WHERE CAND_CODE=" + candProf.getCandCode();

			Object[][] candQualDets = getSqlModel().getSingleResult(query);
			if (candQualDets != null && candQualDets.length > 0) {
				candProf.setQualiFlag("true");
				Object[][] qualObj = new Object[candQualDets.length][5];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candQualDets.length; i++) {
					CandProfile loopBean = new CandProfile();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setQualDetCode(String.valueOf(String
							.valueOf(candQualDets[i][1])));
					qualObj[i][0] = String.valueOf(String
							.valueOf(candQualDets[i][2]));// Qua Name
					qualObj[i][1] = String.valueOf(String
							.valueOf(candQualDets[i][3]));// Qua Level
					qualObj[i][2] = String.valueOf(String
							.valueOf(candQualDets[i][5]));// Specialization
															// name
					qualObj[i][3] = String.valueOf(String
							.valueOf(candQualDets[i][1]));// Qualification Id
					qualObj[i][4] = String.valueOf(String
							.valueOf(candQualDets[i][4]));// Specialization
															// Code
					loopBean.setEstbName(String.valueOf(String
							.valueOf(candQualDets[i][6])));
					if (String.valueOf(candQualDets[i][7]).equals("null")
							|| String.valueOf(candQualDets[i][7]).equals("")) {
						loopBean.setYearofPass("");
					} else {
						loopBean.setYearofPass(String.valueOf(String
								.valueOf(candQualDets[i][7])));
					}
					loopBean.setPercMarks(String.valueOf(String
							.valueOf(candQualDets[i][8])));
					list.add(loopBean);
				}// end of if statement
				request.setAttribute("qualObj", qualObj);
				candProf.setQualList(list);
			} else {
				candProf.setQualiFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * @purpose : To retrive skill details in the skill list
	 */
	public void retCandSkill(CandProfile candProf, HttpServletRequest request) {
		try {
			String query = "SELECT CAND_DTL_CODE ,CAND_SKILL_CODE,NVL(SKILL_NAME,' '), CAND_EXP FROM HRMS_REC_CDOL_SKILLDTL "
					+ " LEFT JOIN HRMS_SKILL ON  HRMS_SKILL.SKILL_ID = HRMS_REC_CDOL_SKILLDTL.CAND_SKILL_CODE"
					+

					" WHERE CAND_CODE=" + candProf.getCandCode();

			Object[][] candSkillDets = getSqlModel().getSingleResult(query);
			if (candSkillDets != null && candSkillDets.length > 0) {
				candProf.setSkillFlag("true");
				Object[][] skillObj = new Object[candSkillDets.length][2];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candSkillDets.length; i++) {
					CandProfile loopBean = new CandProfile();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setSkillDtlId(checkNull(String.valueOf(String
							.valueOf(candSkillDets[i][0]))));
					// loopBean.setSkillCode(String.valueOf(String.valueOf(candSkillDets[i][1])));
					skillObj[i][0] = checkNull((String.valueOf(String.valueOf(
							candSkillDets[i][2]).trim())));
					skillObj[i][1] = checkNull(String.valueOf(String
							.valueOf(candSkillDets[i][1])));
					if (String.valueOf(candSkillDets[i][3]).equals("null")
							|| String.valueOf(candSkillDets[i][3]).equals("")) {
						loopBean.setSkillExp("");
					} else {
						loopBean.setSkillExp(String.valueOf(String.valueOf(
								candSkillDets[i][3]).trim()));
					}

					list.add(loopBean);
				}// end of if statement
				request.setAttribute("skillObj", skillObj);
				candProf.setSkillList(list);
			} else {
				candProf.setSkillFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * @purpose : To display the records in the domain/functional list
	 */
	public void retCandFunc(CandProfile candProf, HttpServletRequest request) {
		try {
			String query = "SELECT CAND_DTL_CODE ,CAND_FUNC_CODE,NVL(FUNC_DOMAIN_NAME,' '), CAND_EXP FROM HRMS_REC_CDOL_FUNCDTL "
					+ " LEFT JOIN HRMS_FUNC_DOMAIN_MASTER ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE  = HRMS_REC_CDOL_FUNCDTL.CAND_FUNC_CODE"
					+ " WHERE CAND_CODE=" + candProf.getCandCode();

			Object[][] candFuncDets = getSqlModel().getSingleResult(query);
			if (candFuncDets != null && candFuncDets.length > 0) {
				candProf.setDomFlag("true");
				Object[][] funcObj = new Object[candFuncDets.length][2];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candFuncDets.length; i++) {
					CandProfile loopBean = new CandProfile();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setFuncDtlId(String.valueOf(String
							.valueOf(candFuncDets[i][0])));
					// loopBean.setFuncCode(String.valueOf(String.valueOf(candFuncDets[i][1])));
					funcObj[i][0] = (String.valueOf(String.valueOf(
							candFuncDets[i][2]).trim()));
					funcObj[i][1] = (String.valueOf(String
							.valueOf(candFuncDets[i][1])));
					if (String.valueOf(candFuncDets[i][3]).equals("")
							|| String.valueOf(candFuncDets[i][3])
									.equals("null")) {
						loopBean.setFuncExp("");
					} else {
						loopBean.setFuncExp(String.valueOf(String.valueOf(
								candFuncDets[i][3]).trim()));
					}

					list.add(loopBean);
				}// end of if statement
				request.setAttribute("funcObj", funcObj);
				candProf.setFuncList(list);
			} else {
				candProf.setDomFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * @purpose : To display the records in the exp history
	 */
	public void retCandExp(CandProfile candProf, HttpServletRequest request) {
		try {
			String query = "SELECT  CAND_DTL_CODE,NVL(CAND_EMPLOYER_NAME,' '),NVL(CAND_EMP_PORF,' '), NVL(TO_CHAR(CAND_RELV_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(CAND_JOIN_DATE,'DD-MM-YYYY'),' '), NVL(CAND_JOB_DESC,' '), "
					+ " ' ',CAND_INDUS_TYPE,NVL(INDUS_ABBRIVIATION,' '),NVL(CAND_SPEC_ACHV,' '), CAND_CTC "
					+ "  FROM HRMS_REC_CDOL_EXPDTL "
					+

					" LEFT JOIN HRMS_INDUS_TYPE ON HRMS_REC_CDOL_EXPDTL.CAND_INDUS_TYPE = HRMS_INDUS_TYPE.INDUS_CODE "
					+

					" WHERE CAND_CODE=" + candProf.getCandCode();
			Object[][] candExpDets = getSqlModel().getSingleResult(query);

			if (candExpDets != null && candExpDets.length > 0) {
				candProf.setExpFlag("true");
				Object[][] expObj = new Object[candExpDets.length][3];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candExpDets.length; i++) {
					CandProfile loopBean = new CandProfile();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setExpDtlId(checkNull(String.valueOf(String
							.valueOf(candExpDets[i][0]))));
					loopBean.setEmprName(checkNull(String.valueOf(
							String.valueOf(candExpDets[i][1])).trim()));
					loopBean.setLastJobPro(checkNull(String.valueOf(
							String.valueOf(candExpDets[i][2])).trim()));
					loopBean.setRelvDate(checkNull(String.valueOf(
							String.valueOf(candExpDets[i][3])).trim()));
					loopBean.setJoinDate(checkNull(String.valueOf(
							String.valueOf(candExpDets[i][4])).trim()));
					expObj[i][0] = String.valueOf(checkNull(String
							.valueOf(candExpDets[i][5])));//Candidate Job Description
					loopBean.setIndsId(checkNull(String.valueOf(String
							.valueOf(candExpDets[i][7]))));
					expObj[i][1] = String.valueOf(String
							.valueOf(candExpDets[i][8]));//Industry Type
					expObj[i][2] = String.valueOf(String
							.valueOf(candExpDets[i][7]));//Industry Id
					loopBean.setSpecAch(String.valueOf(String
							.valueOf(candExpDets[i][9])));
					if (String.valueOf(candExpDets[i][10]).equals("null")
							|| String.valueOf(candExpDets[i][10]).equals("")) {
						loopBean.setCtcExp("");
					} else {
						loopBean.setCtcExp(String.valueOf(String
								.valueOf(candExpDets[i][10])));
					}
					list.add(loopBean);
				}//end of if statement
				request.setAttribute("expObj", expObj);
				candProf.setExpList(list);
			} else {
				candProf.setExpFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

}
