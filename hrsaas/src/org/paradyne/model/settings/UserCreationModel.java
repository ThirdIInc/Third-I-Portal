package org.paradyne.model.settings;

import org.paradyne.bean.settings.UserCreation;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.admin.srd.SendMailModel;
import java.util.Random;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author reebaj
 * 
 */

public class UserCreationModel extends ModelBase {
	Random random = new Random();
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	UserCreation userCreation = null;

	public UserCreationModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Random generation of password
	 * 
	 * @param length
	 * @return String password
	 */
	public String getRandomPassword(int length) {
		StringBuffer password = new StringBuffer();
		// logger.info("=======");
		char[] smallChars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };
		char[] capChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] numbers = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' };
		char[] spec_char = new char[] { '!', '@', '#', '$', '%', '^', '&', '*',
				'`', '~', '(', ')', '-', '_' };

		int count = 0;
		int at_least_1_cap = 0;

		String query = " SELECT PWD_INCLUDE_ALPHA, PWD_INCLUDE_NUM, PWD_INCLUDE_SPCHAR, PWD_INCLUDE_UPCASE "
				+ " FROM HRMS_SETTINGS_PWD ";
		Object[][] pass = getSqlModel().getSingleResult(query);
		for (int i = 0; i < length; i++) {
			if (count >= length) {
				break;
			}// end of if

			/**
			 * if alphabets(small letters.... append and count++;
			 */
			if (String.valueOf(pass[0][0]).trim().equals("Y")) {
				// logger.info("=============small letter=============");
				password.append(smallChars[random.nextInt(smallChars.length)]);
				count++;
			}// end of if
			if (count >= length) {
				break;
			}// end of if

			/**
			 * if numbers.... append and count++;
			 */
			if (String.valueOf(pass[0][1]).trim().equals("Y")) {
				// logger.info("=============number=============");
				password.append(numbers[random.nextInt(numbers.length)]);
				count++;
			}// end of if
			if (count >= length) {
				break;
			}// end of if

			/**
			 * if spec_char.... append and count++;
			 */
			if (String.valueOf(pass[0][2]).trim().equals("Y")) {
				// logger.info("=============special character=============");
				password.append(spec_char[random.nextInt(spec_char.length)]);
				count++;
			}// end of if

			if (count >= length) {
				break;
			}// end of if

			/**
			 * if at_least one capital.... if at_least_1_cap==0 append and
			 * at_least_1_cap++;
			 */
			if (String.valueOf(pass[0][3]).trim().equals("Y")) {
				if (at_least_1_cap == 0) {
					// logger.info("=============capital letter=============");
					password.append(capChars[random.nextInt(capChars.length)]);
					at_least_1_cap++;
					count++;
				}// end of nested if
			}// end of if
			/**
			 * if count==length; break;
			 */
			if (count >= length) {
				break;
			}// end of if
			
			if (String.valueOf(pass[0][0]).trim().equals("N") && String.valueOf(pass[0][1]).trim().equals("N") && String.valueOf(pass[0][2]).trim().equals("N") && String.valueOf(pass[0][3]).trim().equals("N") ) {
				password.append(smallChars[random.nextInt(smallChars.length)]);
				count++;
			}// end of if
			/**
			 * if count==length; break;
			 */
			if (count >= length) {
				break;
			}// end of if

		}// end of FOR loop
		return password.toString();
	}

	/**
	 * Random generation of password (if none of the options are selected i.e
	 * "Y")
	 * 
	 * @param length
	 * @return String password
	 */
	public String getAltRandomPassword(int length) {
		StringBuffer password = new StringBuffer();
		// logger.info("=======");
		char[] smallChars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };

		for (int i = 0; i < length; i++) {
			password.append(smallChars[random.nextInt(smallChars.length)]);
		}// end of loop
		return password.toString();
	}

	/**
	 * Random generation of password if only numbers required
	 * 
	 * @param j
	 * @return String password
	 */
	public Object getRandomNumbers(int j) {
		// TODO Auto-generated method stub
		StringBuffer password = new StringBuffer();
		char[] numbers = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' };
		for (int i = 0; i < j; i++) {
			password.append(numbers[random.nextInt(numbers.length)]);
		}// end of loop
		return password.toString();
	}

	/**
	 * Generating users
	 * 
	 * @param userCreation
	 * @param request
	 * @return result
	 */
	public boolean submit(UserCreation userCreation, HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			String passQuery = "SELECT NVL(PWD_MIN_LENGTH,0) FROM HRMS_SETTINGS_PWD";
			Object[][] passObj = getSqlModel().getSingleResult(passQuery);
 
			// CHECKING PASSWORD LENGTH
			int passWord = 8;
			try {
				if (passObj!=null && passObj.length > 0) {
					if(!String.valueOf(passObj[0][0]).equals("0"))
					passWord = Integer.parseInt(String.valueOf(passObj[0][0]));
				}// end of if
				else {
					passWord = 8;
				}// end of else
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("Exception in catch 1- Submit - model");
				passWord = 8;
				e.printStackTrace();
				// passWord = 8;
			}
			String randompassword = "";

			String query = "SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') NAME "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_LOGIN ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE HRMS_LOGIN.EMP_ID IS NULL AND HRMS_EMP_OFFC.EMP_STATUS='S' ";
/*Added By Nilesh*/
			if (userCreation.getUserProfileDivision() != null
					&& !userCreation.getUserProfileDivision().equals("")) {
				query += " AND EMP_DIV IN ("
						+ userCreation.getUserProfileDivision() + ")";

			}
			query += " ORDER BY  EMP_DIV";

			Object[][] id = getSqlModel().getSingleResult(query);

			logger.info("idlength.777777777777777.." + id.length);

			/* SELECTING USERNAME COMBINATION - DROPDOWN BOX 1 */
			String str = " SELECT ";
			if (userCreation.getName1().equals("1")) {
				str += "LOWER(EMP_FNAME)";
			} else if (userCreation.getName1().equals("2")) {
				str += "LOWER(NVL(EMP_MNAME,''))";
			} else if (userCreation.getName1().equals("3")) {
				str += "LOWER(NVL(EMP_LNAME,''))";
			} else if (userCreation.getName1().equals("4")) {
				str += "LOWER(SUBSTR(EMP_FNAME,1,1))";
			} else if (userCreation.getName1().equals("5")) {
				str += "LOWER(NVL(SUBSTR(EMP_MNAME,1,1),''))";
			} else if (userCreation.getName1().equals("6")) {
				str += "LOWER(NVL(SUBSTR(EMP_LNAME,1,1),''))";
			}

			/* SELECTING USERNAME SEPERATOR - DROPDOWN BOX 2 */
			String seperator = "";
			if (userCreation.getSeperator().equals("-1")) {
				logger.info("seperrrrrrrrrrr--------rrrrrrrrrrrrrrrr"
						+ userCreation.getSeperator());
				seperator = "";
			} else if (userCreation.getSeperator().equals("1")) {
				logger.info("seperrrrrrrrrrrrrrrrrrrrrrrrrrrrr"
						+ userCreation.getSeperator());
				seperator = ".";
			} else if (userCreation.getSeperator().equals("2")) {
				seperator = "_";
			} else if (userCreation.getSeperator().equals("3")) {
				seperator = "-";
			}

			/* SELECTING USERNAME COMBINATION - DROPDOWN BOX 3 */
			String str1 = " SELECT ";
			if (userCreation.getName2().equals("1")) {
				str1 += "LOWER(EMP_FNAME)";
			} else if (userCreation.getName2().equals("2")) {
				str1 += "LOWER(NVL(EMP_MNAME,''))";
			} else if (userCreation.getName2().equals("3")) {
				str1 += "LOWER(NVL(EMP_LNAME,''))";
			} else if (userCreation.getName2().equals("4")) {
				str1 += "LOWER(SUBSTR(EMP_FNAME,1,1))";
			} else if (userCreation.getName2().equals("5")) {
				str1 += "LOWER(NVL(SUBSTR(EMP_MNAME,1,1),''))";
			} else if (userCreation.getName2().equals("6")) {
				str1 += "LOWER(NVL(SUBSTR(EMP_LNAME,1,1),''))";
			}

			String digits = "";

			String dupstr, dupstr1;
			dupstr = str;
			dupstr1 = str1;

			for (int i = 0; i < id.length; i++) {
				Object[][] finalObj = new Object[1][3];
				str = dupstr + " FROM HRMS_EMP_OFFC WHERE EMP_ID= "
						+ String.valueOf(id[i][0]);
				Object data[][] = getSqlModel().getSingleResult(str);

				str1 = dupstr1 + " FROM HRMS_EMP_OFFC WHERE EMP_ID= "
						+ String.valueOf(id[i][0]);
				Object data1[][] = getSqlModel().getSingleResult(str1);

				logger.info("reeeee---" + String.valueOf(id[i][0]));

				finalObj[0][0] = String.valueOf(id[i][0]);// emp_id

				logger.info("first   string----" + String.valueOf(data[0][0]));
				logger
						.info("second   string----"
								+ String.valueOf(data1[0][0]));
				String ss = "";
				if (data1[0][0] != null && data1.length > 0) {
					ss = (checkNull(String.valueOf(data[0][0])) + seperator
							+ checkNull(String.valueOf(data1[0][0])) + digits);// concatenating
					logger.info("concatenated string.............." + ss);
					finalObj[0][1] = String.valueOf(ss);// username

				}// end of if
				else {
					ss = (checkNull(String.valueOf(data[0][0])) + digits);
					logger.info("concatenated string.............." + ss);
					finalObj[0][1] = String.valueOf(ss);// username

				}// end of else

				/* GENERATING PASSWORD RANDOMLY */
				randompassword = getRandomPassword(passWord);
				logger.info("password ******-----" + randompassword);
				if (randompassword.equals("")) {// if password settings are not
					// set
					randompassword = getAltRandomPassword(passWord);
					logger.info("password-----" + randompassword);
				}// end of if

				finalObj[0][2] = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.encrypt(randompassword); // encrypted password

				String insertQuery = "INSERT INTO HRMS_LOGIN(LOGIN_CODE,EMP_ID,PROFILE_CODE,LOGIN_NAME,LOGIN_PASSWORD,LOCK_FLAG,LOGIN_CREATE_DATE,LOGIN_PWD_CHANGE_DATE,EMP_ACTIVE,LOGIN_PROFILE) "
						+ " VALUES((SELECT NVL(MAX(LOGIN_CODE),0)+1 FROM HRMS_LOGIN),?,2,?,?,'N',SYSDATE,SYSDATE,'Y',2) ";

				logger.info("insert 1");
				result = getSqlModel().singleExecute(insertQuery, finalObj);

				/* ********** TO HANDLE UNIQUE USERNAME CONSTRAINT ********** */

				/** ******* INSERT FAILS-1 ********* */
				if (!result) {
					// TODO: handle exception
					/**
					 * use initial of first/middle/last name if username exists
					 * and concatenate with username
					 */
					logger.info("catch-----------------------------------e");

					String cQuery = " SELECT ";

					if (userCreation.getName1().equals("1")
							&& userCreation.getName2().equals("1")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_MNAME,1,1),'')) ";
					}

					else if (userCreation.getName1().equals("1")
							&& userCreation.getName2().equals("3")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_MNAME,1,1),'')) ";
					} else if (userCreation.getName1().equals("1")
							&& userCreation.getName2().equals("2")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_LNAME,1,1),'')) ";
					} else if (userCreation.getName1().equals("2")
							&& userCreation.getName2().equals("1")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_LNAME,1,1),'')) ";
					} else if (userCreation.getName1().equals("2")
							&& userCreation.getName2().equals("2")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_FNAME,1,1),'')) ";
					} else if (userCreation.getName1().equals("2")
							&& userCreation.getName2().equals("3")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_FNAME,1,1),'')) ";
					} else if (userCreation.getName1().equals("3")
							&& userCreation.getName2().equals("1")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_MNAME,1,1),'')) ";
					} else if (userCreation.getName1().equals("3")
							&& userCreation.getName2().equals("2")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_FNAME,1,1),'')) ";
					} else if (userCreation.getName1().equals("3")
							&& userCreation.getName2().equals("3")) {
						cQuery += " LOWER(NVL(SUBSTR(EMP_FNAME,1,1),'')) ";
					}

					cQuery += "FROM HRMS_EMP_OFFC WHERE EMP_ID= "
							+ String.valueOf(id[i][0]);
					Object cData[][] = getSqlModel().getSingleResult(cQuery);
					logger.info("cData.lenth=======" + cData.length);

					// SETTING USERNAME

					if (cData[0][0] != null && cData.length > 0) {

						if (!String.valueOf(finalObj[0][1]).trim().equals("")) {

							if (data1[0][0] != null && data1.length > 0) {
								ss = (checkNull(String.valueOf(data[0][0]))
										+ seperator
										+ String.valueOf(cData[0][0])
										+ seperator
										+ checkNull(String.valueOf(data1[0][0])) + digits);// concatenating
								finalObj[0][1] = String.valueOf(ss);// username

							}// end of 2nd nested if
							else {
								ss = (checkNull(String.valueOf(data[0][0]))
										+ seperator
										+ String.valueOf(cData[0][0]) + digits);
								finalObj[0][1] = String.valueOf(ss);// username

							}// end of else

							logger.info("NEW FIRST NAME-------"
									+ String.valueOf(ss));
						}// end of 1st nested if

					}// end of IF-2

					logger.info("insert 2");
					result = getSqlModel().singleExecute(insertQuery, finalObj);

					/** ******* INSERT FAILS-2 ********* */
					if (!result) {

						/**
						 * use first/middle/last name if username exists and
						 * concatenate with username
						 */
						logger
								.info("catch-----------------------------------e1");

						String dQuery = " SELECT";

						if (userCreation.getName1().equals("1")
								&& userCreation.getName2().equals("1")) {
							dQuery += "  LOWER(EMP_MNAME) ";
						}

						else if (userCreation.getName1().equals("1")
								&& userCreation.getName2().equals("3")) {
							dQuery += "  LOWER(EMP_MNAME) ";
						} else if (userCreation.getName1().equals("1")
								&& userCreation.getName2().equals("2")) {
							dQuery += "  LOWER(EMP_LNAME) ";
						} else if (userCreation.getName1().equals("2")
								&& userCreation.getName2().equals("1")) {
							dQuery += "  LOWER(EMP_LNAME) ";
						} else if (userCreation.getName1().equals("2")
								&& userCreation.getName2().equals("2")) {
							dQuery += "  LOWER(EMP_FNAME) ";
						} else if (userCreation.getName1().equals("2")
								&& userCreation.getName2().equals("3")) {
							dQuery += "  LOWER(EMP_FNAME) ";
						} else if (userCreation.getName1().equals("3")
								&& userCreation.getName2().equals("1")) {
							dQuery += "  LOWER(EMP_MNAME) ";
						} else if (userCreation.getName1().equals("3")
								&& userCreation.getName2().equals("2")) {
							dQuery += "  LOWER(EMP_FNAME) ";
						} else if (userCreation.getName1().equals("3")
								&& userCreation.getName2().equals("3")) {
							dQuery += "  LOWER(EMP_FNAME) ";
						}
						dQuery += "FROM HRMS_EMP_OFFC WHERE EMP_ID= "
								+ String.valueOf(id[i][0]);

						Object dData[][] = getSqlModel()
								.getSingleResult(dQuery);
						if (dData[0][0] != null && dData.length > 0) {
							if (!String.valueOf(finalObj[0][1]).trim().equals(
									"")) {

								if (data1[0][0] != null && data1.length > 0) {
									ss = (checkNull(String.valueOf(data[0][0]))
											+ seperator
											+ String.valueOf(dData[0][0])
											+ seperator
											+ checkNull(String
													.valueOf(data1[0][0])) + digits);// concatenating
									finalObj[0][1] = String.valueOf(ss);// username

								} // end of nested if-2
								else {
									ss = (checkNull(String.valueOf(data[0][0]))
											+ seperator
											+ String.valueOf(dData[0][0]) + digits);
									finalObj[0][1] = String.valueOf(ss);// username
								}// end of else
								System.out
										.println("****************NEW FIRST NAME-------"
												+ String.valueOf(ss));
							}// end of nested if-1

						} // end of IF-4
						result = getSqlModel().singleExecute(insertQuery,
								finalObj);

						/** ******* INSERT FAILS-3 ********* */
						if (!result) {
							/**
							 * if exists, add 4 random numbers concatenate with
							 * username
							 */
							logger.info("LAST ELSE" + String.valueOf(ss));
							finalObj[0][1] = String.valueOf(ss)
									+ getRandomNumbers(3);
							result = getSqlModel().singleExecute(insertQuery,
									finalObj);
						}
					}// end of IF-3

				}// end of IF -1
			}// end of FOR loop
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Send mail to the employees according to the filters or conditions
	 * selected Content - user name & password, HrWorK URL
	 * 
	 * @param userCreation
	 * @param request
	 * @return boolean
	 */
	public boolean sendMail(UserCreation userCreation,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String query = " SELECT HRMS_LOGIN.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') NAME,"
				+ " HRMS_EMP_ADDRESS.ADD_EMAIL, LOGIN_NAME, LOGIN_PASSWORD "
				+ " FROM HRMS_LOGIN "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LOGIN.EMP_ID) "
				+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " INNER JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " WHERE  ADD_TYPE='P' AND HRMS_EMP_OFFC.EMP_STATUS='S' AND ADD_EMAIL IS NOT NULL ";

		/* ****** CHECKING FILTERS ***** */
		if (userCreation.getSendDate().equals("1")) {
			query += " AND 1=1";
		} else if (userCreation.getSendDate().equals("2")) {
			query += " AND TO_CHAR(LOGIN_CREATE_DATE,'DD-MM-YYYY') =(SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL) ";
		} else if (userCreation.getSendDate().equals("3")) {
			query += " AND TO_CHAR(LOGIN_CREATE_DATE,'MM') =(SELECT TO_CHAR(SYSDATE,'MM') FROM DUAL) ";
		} else if (userCreation.getSendDate().equals("4")) {
			query += " AND TO_CHAR(LOGIN_CREATE_DATE,'MM') =(SELECT TO_CHAR(SYSDATE,'MM')-1 FROM DUAL) ";
		}// end of nested if

		if (!(String.valueOf(userCreation.getBranchCode()).equals("null") || String
				.valueOf(userCreation.getBranchCode()).equals(""))) {
			query += " and	EMP_CENTER=" + userCreation.getBranchCode() + " ";
		} 
		//Added By Nilesh
		if (userCreation.getUserProfileDivision() != null
				&& !userCreation.getUserProfileDivision().equals("")) {
			query += " AND EMP_DIV IN ("
					+ userCreation.getUserProfileDivision() + ")";
		} 
		if (!(String.valueOf(userCreation.getDivisionCode()).equals("null") || String
				.valueOf(userCreation.getDivisionCode()).equals(""))) {
			query += " and	EMP_DIV=" + userCreation.getDivisionCode() + " ";
		} 
		if (!(String.valueOf(userCreation.getDeptCode()).equals("null") || String
				.valueOf(userCreation.getDeptCode()).equals(""))) {
			query += " and	EMP_DEPT=" + userCreation.getDeptCode() + " ";
		} 
		if (!(String.valueOf(userCreation.getDesgCode()).equals("null") || String
				.valueOf(userCreation.getDesgCode()).equals(""))) {
			query += "	and EMP_RANK=" + userCreation.getDesgCode() + " ";
		} 
		if (!(String.valueOf(userCreation.getEmployeeCode()).equals(
				"null") || String.valueOf(userCreation.getEmployeeCode())
				.equals(""))) {
			query += "	AND HRMS_EMP_OFFC.EMP_ID="
					+ userCreation.getEmployeeCode() + " ";
		}// end of nested if

		query += " ORDER BY  EMP_DIV";

		Object[][] mailObj = getSqlModel().getSingleResult(query);

		String[] to_Emailid = new String[mailObj.length];

		MailUtility mod = new MailUtility();
		mod.initiate(context, session);
		String[] flag = null;

		// Retrieving sender email id
		flag = mod.getDefaultMailIds();

		if (flag.equals("null") && !(flag.length > 0)) {
			return false;
		}// end of if

		String[] htmlText = new String[mailObj.length];
		String[] subject = new String[mailObj.length];
		String[] mesg = new String[mailObj.length];
		if (mailObj.length > 0) {
			for (int z = 0; z < mailObj.length; z++) {
				to_Emailid[z] = String.valueOf(mailObj[z][2]);
				logger.info("To Email Ids --------" + to_Emailid[z]);

				/* RETRIEVING URL FOR HRWORK */
				String poolName = (String) session.getAttribute("session_pool");
				/*
				 * String[] company = poolName.split("_"); String URL =
				 * "http://" + request.getServerName() + ":" +
				 * request.getServerPort() + "/hrwork/" + "client/" +
				 * company[1];
				 */

				ResourceBundle boundle = ResourceBundle
						.getBundle("org.paradyne.lib.ConnectionModel");
				String clientName = "www";
				try {
					clientName = boundle.getString(poolName);
				} catch (Exception e) {
					e.printStackTrace();
					clientName = "www";
				}
				String URL = "http://" + clientName + ".com";
				subject[z] = "PeoplePower Account Information";

				/*
				 * ************************* EMAIL CONTENT(MESSAGE)
				 * ***************************
				 */
				try {
					htmlText[z] = "<html> "
							+ "<style>"
							+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
							+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
							+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
							+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
							+ ".style15 {color: #CC0000} "
							+ ".style16 {color: #D23A49} "
							+ "</style>"
							+ "	<body> "
							+ "<table width='60%' border='0' cellpadding='0' cellspacing='0'>"

							+ "<tr> "
							+ "<td width='66%'>"
							+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
							+ "<tr> " + "<td><p>Dear&nbsp;<b>"
							+ mailObj[z][1]
							+ "</b>, </p><br /> "
							+ "<p>Welcome to <u>HRMS Tool</u>.  "
							+ ""
							+ "Kindly find your account information below.</p> "
							+ ""
							+ "Username:  "
							+ mailObj[z][3]
							+ ""
							+ "<p>Password:  "
							+ new StringEncrypter(
									StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
									.decrypt(String.valueOf(mailObj[z][4]))
							+ "</p> "
							+ "<br /><p>HRMS Link :<a href='"
							+ URL
							+ "'>"
							+ URL
							+ "</a></p>"
							+ "<br /><p>For any assistance, please contact the System Administrator.</p><br />"
							+ "<p>Best Regards,</p>"
							+ "<p>Human Resource Team</p>"
							+ "</td> "
							+ "</tr> " + "</table> " + "</td> " + "</tr> "

							+ "</table> " + "</body> " + "</html> ";
					logger.info("html/text--------------->>" + htmlText);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error("EXception caught in Model - Sending Email");
				}
				SendMailModel sendmodel = new SendMailModel();
				sendmodel.initiate(context, session);
				mesg[z] = sendmodel.getMassMessage(htmlText[z]);

				String finalObj[] = new String[1];

				finalObj[0] = String.valueOf(to_Emailid[z]);

				mod.sendMail(finalObj, mod.getDefaultMailIds(), subject[z],
						mesg[z], "", userCreation.getEmployeeCode(), false);

			}// end of loop

			mod.terminate();
			return true;
		}// end of if
		return false;
	}

	/**
	 * Creating username as email id of employee
	 * 
	 * @param userCreation
	 * @param request
	 * @return boolean result inserts username and password
	 */
	public boolean submitEmail(UserCreation userCreation,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean result = false;
		logger.info("model-----------");
		try {
			String query = "SELECT DISTINCT(EMP_ID),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') NAME, "
					+ " HRMS_EMP_ADDRESS.ADD_EMAIL "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ " LEFT JOIN HRMS_LOGIN ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ " where  ADD_TYPE='P' and ADD_EMAIL IS NOT NULL AND HRMS_LOGIN.EMP_ID IS NULL AND HRMS_EMP_OFFC.EMP_STATUS='S' ";
/*Added By Nilesh */
			if (userCreation.getUserProfileDivision() != null
					&& !userCreation.getUserProfileDivision().equals("")) {
				query += " AND EMP_DIV IN ("
						+ userCreation.getUserProfileDivision() + ")";

			}
			Object[][] id = getSqlModel().getSingleResult(query);
			logger.info("length..." + id.length);
			String[] to_Emailid = new String[id.length];

			// checking minimum length required for password
			String passQuery = "SELECT NVL(PWD_MIN_LENGTH ,0) FROM HRMS_SETTINGS_PWD";
			Object[][] passObj = getSqlModel().getSingleResult(passQuery);
			int passWord = 8;
			try {
				if (passObj!=null && passObj.length > 0 ) {
					if(Integer.parseInt(String.valueOf(passObj[0][0]))==0)
					{
						passWord = 8;
					}
					else{
						passWord = Integer.parseInt(String.valueOf(passObj[0][0]));
					}
					
				}// end of if
				else {
					passWord = 8;
				}// end of else
			} catch (Exception e) {
				// TODO: handle exception
				logger
						.error("Exception caught in checking password - Submit Email model");
				e.printStackTrace();
			}
			String randompassword = "";

			Object[][] mailObj = new Object[id.length][4];
			for (int i = 0; i < id.length; i++) {
				Object[][] finalObj = new Object[1][3];
				finalObj[0][0] = String.valueOf(id[i][0]);// emp_id
				logger.info("id--------" + String.valueOf(id[i][0]));

				finalObj[0][1] = String.valueOf(id[i][2]);// username
				logger.info("username--------" + String.valueOf(id[i][2]));

				// GENERATING PASSWORD
				randompassword = getRandomPassword(passWord);
				logger.info("password ******-----" + randompassword);
				if (randompassword.equals("")) {
					randompassword = getAltRandomPassword(passWord);
					logger.info("password-----" + randompassword);
				}// end of if

				finalObj[0][2] = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.encrypt(randompassword); // encrypted password

				mailObj[i][0] = String.valueOf(id[i][1]);// emp_name for mail
				mailObj[i][1] = String.valueOf(id[i][2]);// Email id for
				// employee
				mailObj[i][2] = String.valueOf(id[i][2]);// username for
				// email
				mailObj[i][3] = randompassword;// unencrypted password for
				// email
				String insertQuery = "INSERT INTO HRMS_LOGIN(LOGIN_CODE,EMP_ID,PROFILE_CODE,LOGIN_NAME,LOGIN_PASSWORD,LOGIN_CREATE_DATE,LOGIN_PWD_CHANGE_DATE,EMP_ACTIVE,LOGIN_PROFILE) "
						+ " VALUES((SELECT NVL(MAX(LOGIN_CODE),0)+1 FROM HRMS_LOGIN),?,2,?,?,SYSDATE,SYSDATE,'Y',2) ";
				result = getSqlModel().singleExecute(insertQuery, finalObj);

			}// end of loop

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception caught in Submit Email model");
			e.printStackTrace();
		}
		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

}
