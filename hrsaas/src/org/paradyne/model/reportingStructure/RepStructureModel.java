package org.paradyne.model.reportingStructure;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.common.LoginBean;
import org.paradyne.bean.reportingStructure.RepStructure;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class RepStructureModel extends ModelBase {

	/**
	 * @author KRISHNA
	 * 
	 * 13-08-2008
	 */

	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RepStructureModel.class);
	// static ArrayList and Hash Map for dealing the Reporting Headings in the
	// Reporting Structure Report
	static ArrayList<String> sameType = new ArrayList<String>();
	static HashMap<String, String> sameTypeName = new HashMap<String, String>();
	static String returnType = new String();
	LoginBean loginBean = new LoginBean();

	/**
	 * method to generate the Report for the Reporting structure
	 * 
	 * @param bean
	 * @param response
	 * @return boolean
	 */
	public boolean report(RepStructure bean, HttpServletResponse response) {
		String name = "Reporting Structure Report";
		String type = "Pdf";
		String title = "Reporting Structure Report";

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);
		rg = getHeader(rg, bean);
		rg.createReport(response);
		return true;

	}

	/**
	 * method to generate the Header for the Reporting Structure
	 * 
	 * @param rg
	 * @param bean
	 * @return instance of ReportGenerator
	 */

	public ReportGenerator getHeader(ReportGenerator rg, RepStructure bean) {
		int empId = Integer.parseInt(bean.getEmpId());
		int headerCode = 0, j = 0;
		ArrayList<String> chkLabel = new ArrayList<String>();
		String repTYpeQuery = "SELECT DISTINCT REPORTINGHDR_TYPE FROM HRMS_REPORTING_STRUCTUREHDR";
		Object[][] repTypeData = getSqlModel().getSingleResult(repTYpeQuery);
		if (repTypeData.length > 0 && repTypeData != null) {
			int hdrArray[] = new int[repTypeData.length];

			try {
				for (int i = 0; i < repTypeData.length; i++) {
					// call method commonChk by sending repType			

					if (bean.getDefaultFlag().equals("false")) {
						headerCode = commonCheck(String
								.valueOf(repTypeData[i][0]), bean);
					}

					else {
						break;
					}

					if (headerCode > 0) {
						// add header code to array
						hdrArray[j] = headerCode;
						logger.info("hrd array" + hdrArray[j]);
						j++;

					}

				}
			} catch (Exception e) {
				logger.error(e);

			}

			// displaying header codes
			for (int d = 0; d < hdrArray.length; d++) {
				logger.info("hearers " + hdrArray[d]);
			}

			String query1 = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] today = getSqlModel().getSingleResult(query1);
			String date = "Date : " + (String) today[0][0];

			if (hdrArray[0] > 0) {
				rg.addText(date, 0, 2, 0);
				for (int x = 0; x < hdrArray.length; x++) {

					if (hdrArray[x] > 0) {
						// rg.addFormatedText(header, 6, 0, 1, 0);
						int[] rowcellwidth = { 100 };
						int[] rowalignmnet = { 0 };

						Object[][] setHdr = new Object[4][1];
						String Query = " SELECT REPORTINGHDR_CODE,NVL(DEPT_NAME,' ') ,NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)NAME ,"
								+ "REPORTINGHDR_TYPE  FROM HRMS_REPORTING_STRUCTUREHDR "
								+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DEPT_ID) "
								+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_BRN_ID) "
								+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DESG_ID)"
								+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_EMP_ID)where REPORTINGHDR_CODE='"
								+ hdrArray[x]
								+ "'  ORDER BY REPORTINGHDR_CODE ";

						Object Data[][] = getSqlModel().getSingleResult(Query);
						String type = "";
						// ==================for same As case
						if (Data.length > 0 && Data != null) {
							type = String.valueOf(Data[0][5]);
							logger
									.info("*****************TYPE**********************="
											+ type);
						}

						/*
						 * if (chkLabel.contains(type)) { continue; }
						 */

						String dispLabel = "", tempLabel, tempLabel2[], strTemp, strTemp2 = "";

						if (sameTypeName.get(type) == null) {
							dispLabel = decodeStructure(type);

						} else {

							tempLabel = type + "," + sameTypeName.get(type);
							logger.info("Label==============" + tempLabel);
							tempLabel2 = tempLabel.split(",");

							for (int i = 0; i < tempLabel2.length; i++) {
								strTemp = decodeStructure(tempLabel2[i]);
								strTemp2 += strTemp + ",";
								String sameLabel = "The reporting hierarchy for ";

								dispLabel = strTemp2.substring(0, (strTemp2
										.length() - 1));
								logger.info("label 2=============" + dispLabel);
								dispLabel = sameLabel + dispLabel + " is same.";
							}
						}

						logger.info("krissnaa");

						rg.addText("\n", 0, 0, 0);
						logger.info("length of data" + Data.length);
						logger.info("===================I value" + x);
						logger.info("kriss");
						String colNames2[] = { "Approver ID", "Approver Name",
								"Alternate Approver ID",
								"Alternate Approver Name", "Level" };
						int[] cellwidth2 = { 15, 30, 15, 30, 15 };

						setHdr[0][0] = String.valueOf(" Department Name :")
								+ "     " + String.valueOf(Data[0][1]);
						setHdr[1][0] = String.valueOf(" Branch Name :")
								+ "         " + String.valueOf(Data[0][2]);

						setHdr[2][0] = String.valueOf(" Designation Name :")
								+ "     " + String.valueOf(Data[0][3]);

						setHdr[3][0] = String.valueOf(" Employee Name :")
								+ "     " + String.valueOf(Data[0][4]);

						// String recordNum = i + 1 + getOrdinalFor(i + 1) +
						// "-Record";

						if (bean.getDefaultFlag().equals("true")) {
							String defaultQuery = "SELECT DISTINCT(REPORTINGHDR_TYPE)FROM HRMS_REPORTING_STRUCTUREHDR where REPORTINGHDR_ISDEFAULT='Y'";

							Object defaultQueryData[][] = getSqlModel()
									.getSingleResult(defaultQuery);

							// for displaying label in default case
							if (defaultQueryData.length > 0) {

								logger
										.info(" DEFAULT REP TYPE==============="
												+ String
														.valueOf(defaultQueryData[0][0]));
								String defLabel = decodeStructure(String
										.valueOf(defaultQueryData[0][0]));
								String def = "The default reporting hierarchy for all the application types is ";
								rg.addFormatedText(def + "" + defLabel + ".",
										6, 0, 1, 0);

							}

							/*if (defaultQueryData.length > 0) {
								String temp, defStr = "", defaultLabel = "";
								for (int i = 0; i < defaultQueryData.length; i++) {

									temp = decodeStructure(String
											.valueOf(defaultQueryData[i][0]));
									defStr += temp + ",";

									defaultLabel = defStr;

								}

								rg.addFormatedText(defaultLabel.substring(0,
										(defaultLabel.length() - 1)), 6, 0, 1,
										0);
							}
							 */
							else {

								String header = " Reporting hierarchy not defined  for "
										+ bean.getEmpName() + ".";
								rg.addFormatedText(header, 1, 0, 1, 0);
								sameType.clear();
								sameTypeName.clear();
								return rg;
							}

						} else {

							rg.addFormatedText(dispLabel, 6, 0, 1, 0);
						}

						rg.addText("\n", 0, 0, 0);
						// rg.tableBody(setHdr, rowcellwidth, rowalignmnet);
						rg.addText("\n", 0, 0, 0);
						/*
						 * String hdrDtl = " SELECT NVL(EMP_TOKEN,'
						 * '),(HRMS_EMP_OFFC.EMP_FNAME||'
						 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||
						 * HRMS_EMP_OFFC.EMP_LNAME)NAME ,NVL(RANK_NAME,' ') " + "
						 * from HRMS_REPORTING_STRUCTUREDTL " + " LEFT JOIN
						 * HRMS_EMP_OFFC
						 * ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REPORTING_STRUCTUREDTL.REPORTINGDTL_EMP_ID) " + "
						 * LEFT JOIN HRMS_RANK
						 * ON(HRMS_RANK.RANK_ID=HRMS_REPORTING_STRUCTUREDTL.REPORTINGDTL_DESG_ID)
						 * WHERE REPORTINGHDR_CODE=" + hdrArray[x] + "ORDER BY
						 * EMP_TOKEN";
						 */

						String hdrDtl = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)NAME ,"
								+ "HRMS_EMP_OFFC1.EMP_TOKEN,NVL((HRMS_EMP_OFFC1.EMP_FNAME||' '||HRMS_EMP_OFFC1.EMP_MNAME|| ' ' || HRMS_EMP_OFFC1.EMP_LNAME),' ')NAME1,"
								+ "REPORTINGDTL_CODE  FROM HRMS_REPORTING_STRUCTUREDTL "
								+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REPORTING_STRUCTUREDTL.REPORTINGDTL_EMP_ID) "
								+ " LEFT JOIN HRMS_EMP_OFFC HRMS_EMP_OFFC1 ON(HRMS_EMP_OFFC1.EMP_ID=HRMS_REPORTING_STRUCTUREDTL.REPORTINGDTL_ALTER_EMP_ID)"
								+ " WHERE REPORTINGHDR_CODE = "
								+ hdrArray[x]
								+ " ORDER BY REPORTINGDTL_CODE ";

						Object hdrDtlData[][] = getSqlModel().getSingleResult(
								hdrDtl);
						logger.info("HEADER LENGTH----------"
								+ hdrDtlData.length);
						Object levelData[][] = new Object[hdrDtlData.length][5];

						for (int k = 0; k < hdrDtlData.length; k++) {
							levelData[k][0] = String.valueOf(hdrDtlData[k][0]);
							levelData[k][1] = String.valueOf(hdrDtlData[k][1]);
							levelData[k][2] = String.valueOf(hdrDtlData[k][2]);
							levelData[k][3] = String.valueOf(hdrDtlData[k][3]);
							levelData[k][4] = (k + 1)
									+ ""
									+ getOrdinalFor(Integer.parseInt(String
											.valueOf(hdrDtlData[k][4])))
									+ "-Approver";

						}

						rg.tableBody(colNames2, levelData, cellwidth2);

						rg.addText("\n", 0, 0, 0);

						sameType.clear();

					}
				}
			} else {
				String header2 = " Reporting hierarchy not defined  for "
						+ bean.getEmpName() + ".";
				rg.addFormatedText(header2, 1, 0, 1, 0);
				sameType.clear();
				sameTypeName.clear();
				return rg;
			}
			sameType.clear();
			sameTypeName.clear();
			logger.info("MAP VALUES------------------------" + sameTypeName);
		}

		else {
			String header1 = " Reporting hierarchy not defined  for "
					+ bean.getEmpName() + ".";
			rg.addFormatedText(header1, 1, 0, 1, 0);
		}
		return rg;

	}

	/**
	 * method for setting bean properties
	 * 
	 * @param bean
	 */
	public void setProperties(RepStructure bean) {

		logger.info("i am in setProperties of model");
		String setQuery = "SELECT EMP_DEPT,EMP_CENTER,EMP_RANK FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ bean.getEmpId();
		Object desgCheck[][] = getSqlModel().getSingleResult(setQuery);
		bean.setDeptId(String.valueOf(desgCheck[0][0]));
		bean.setBrnchId(String.valueOf(desgCheck[0][1]));
		bean.setDesgnId(String.valueOf(desgCheck[0][2]));

	}

	/**
	 * method for setting empId and Employee Name in case of general user
	 * 
	 * @param bean
	 * @param loginCode
	 */

	public void setGeneral(RepStructure bean, int loginCode) {
		String setQuery = "SELECT  (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME),EMP_ID,EMP_DEPT,EMP_CENTER,EMP_RANK FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
				+ " LEFT JOIN HRMS_LOGIN ON(HRMS_EMP_OFFC.EMP_ID= HRMS_LOGIN.EMP_ID) WHERE LOGIN_CODE ="
				+ loginCode;
		Object setGeneral[][] = getSqlModel().getSingleResult(setQuery);

		bean.setEmpName(String.valueOf(setGeneral[0][0]));
		bean.setEmpId(String.valueOf(setGeneral[0][1]));
		bean.setDeptId(String.valueOf(setGeneral[0][2]));
		bean.setBrnchId(String.valueOf(setGeneral[0][3]));
		bean.setDesgnId(String.valueOf(setGeneral[0][4]));
	}

	/**
	 * method for checking department,branch,designation combination
	 * 
	 * @param reportType
	 * @param bean
	 * @return int
	 */

	public int checkDBG(String reportType, RepStructure bean) {
		logger.info("I am in checkDBG");
		int deptId = 0, brnchId = 0, desgId = 0;
		deptId = Integer.parseInt(bean.getDeptId());
		brnchId = Integer.parseInt(bean.getBrnchId());
		desgId = Integer.parseInt(bean.getDesgnId());

		// first check for department
		String depChkQuery = "SELECT REPORTINGHDR_DEPT_ID FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_DEPT_ID="
				+ deptId + " AND REPORTINGHDR_TYPE='" + reportType + "'";

		Object depCheck[][] = getSqlModel().getSingleResult(depChkQuery);

		if (depCheck.length > 0) {
			// deptId is there
			deptId = Integer.parseInt(String.valueOf(depCheck[0][0]));

			// check for branchId
			String brnchChkQuery = "SELECT REPORTINGHDR_BRN_ID FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_DEPT_ID="
					+ deptId
					+ " AND REPORTINGHDR_TYPE='"
					+ reportType
					+ "' AND REPORTINGHDR_BRN_ID=" + brnchId;
			Object brnchCheck[][] = getSqlModel()
					.getSingleResult(brnchChkQuery);
			if (brnchCheck.length > 0) {
				// branch Id is there
				brnchId = Integer.parseInt(String.valueOf(brnchCheck[0][0]));
				// check for designation

				String desgchkQuery = "SELECT REPORTINGHDR_DESG_ID FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_DEPT_ID="
						+ deptId
						+ "  AND REPORTINGHDR_BRN_ID="
						+ brnchId
						+ " AND  REPORTINGHDR_TYPE='"
						+ reportType
						+ "' and REPORTINGHDR_DESG_ID=" + desgId;
				Object desgCheck[][] = getSqlModel().getSingleResult(
						desgchkQuery);

				if (desgCheck.length > 0) {
					// desgId is there

					desgId = Integer.parseInt(String.valueOf(desgCheck[0][0]));
				} else {

					// designation Id is not there
					desgId = 0;
				}

			} else {
				// branch Id is not there
				brnchId = 0;

				// check for designation

				String desgchkQuery = "SELECT REPORTINGHDR_DESG_ID FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_DEPT_ID="
						+ deptId
						+ " AND  REPORTINGHDR_TYPE='"
						+ reportType
						+ "' and REPORTINGHDR_DESG_ID=" + desgId;
				Object desgCheck[][] = getSqlModel().getSingleResult(
						desgchkQuery);

				if (desgCheck.length > 0) {
					// desgId is there

					desgId = Integer.parseInt(String.valueOf(desgCheck[0][0]));
				} else {

					// designation Id is not there
					desgId = 0;
				}

			}

		}

		else {
			deptId = 0;
			// deptId is not there
			// check for branch Id
			String brnchChkQuery = "SELECT REPORTINGHDR_BRN_ID FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_TYPE='"
					+ reportType + "' AND REPORTINGHDR_BRN_ID=" + brnchId;
			Object brnchCheck[][] = getSqlModel()
					.getSingleResult(brnchChkQuery);
			if (brnchCheck.length > 0) {
				// branch Id is there
				brnchId = Integer.parseInt(String.valueOf(brnchCheck[0][0]));
				// check for designation Id depends on branch Id and Report Type
				String desgchkQuery = "SELECT REPORTINGHDR_DESG_ID FROM HRMS_REPORTING_STRUCTUREHDR WHERE  REPORTINGHDR_BRN_ID="
						+ brnchId
						+ " AND  REPORTINGHDR_TYPE='"
						+ reportType
						+ "' and REPORTINGHDR_DESG_ID=" + desgId;
				Object desgCheck[][] = getSqlModel().getSingleResult(
						desgchkQuery);

				if (desgCheck.length > 0) {
					// designation I d is there
					desgId = Integer.parseInt(String.valueOf(desgCheck[0][0]));
				} else {
					// designation Id is not there
					desgId = 0;

				}
			} else {

				// branch Id is not there
				brnchId = 0;

				// then check for designation Id depends on Reporting Type
				String desgchkQuery = "SELECT REPORTINGHDR_DESG_ID FROM HRMS_REPORTING_STRUCTUREHDR WHERE   REPORTINGHDR_TYPE='"
						+ reportType + "' and REPORTINGHDR_DESG_ID=" + desgId;
				Object desgCheck[][] = getSqlModel().getSingleResult(
						desgchkQuery);
				if (desgCheck.length > 0) {
					// designation Id is there
					desgId = Integer.parseInt(String.valueOf(desgCheck[0][0]));
				} else {
					// designation Id is not there
					desgId = 0;
				}

			}

		}

		logger.info("deptId  ==  " + deptId + "====Branch Id ==  " + brnchId
				+ "===Designation ==  " + desgId);

		if (deptId == 0 && brnchId == 0 && desgId == 0) {
			return 0;
		} else {
			String hdrQuery = "SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_DEPT_ID="
					+ deptId
					+ " AND REPORTINGHDR_BRN_ID="
					+ brnchId
					+ " AND REPORTINGHDR_DESG_ID="
					+ desgId
					+ " AND REPORTINGHDR_TYPE='" + reportType + "'";
			Object ddrData[][] = getSqlModel().getSingleResult(hdrQuery);

			if (ddrData.length > 0) {
				logger.info("Header Code"
						+ Integer.parseInt(String.valueOf(ddrData[0][0])));
				return Integer.parseInt(String.valueOf(ddrData[0][0]));
			} else {
				return 0;
			}

		}
	}

	/**
	 * method to check employee id+ Reporting type combination
	 * 
	 * @param repType
	 * @param bean
	 * @return boolean
	 */

	public boolean checkEmpIdRepType(String repType, RepStructure bean)

	{
		String empIdQuery = "SELECT  REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_TYPE='"
				+ repType + "' AND REPORTINGHDR_EMP_ID=" + bean.getEmpId();
		Object empIdData[][] = getSqlModel().getSingleResult(empIdQuery);

		if (empIdData.length > 0) {
			bean.setHeaderCode(String.valueOf(empIdData[0][0]));
			return true;
		} else {
			return false;
		}

	}

	/**
	 * method to check the Reporting Type with different cases like Same as
	 * ,Default.
	 * 
	 * @param reportType
	 * @param bean
	 * @return integer
	 */

	public int commonCheck(String reportType, RepStructure bean) {
		// empId+Type Case
		boolean empIdPType, empIdPType1;
		int headerCode = 0;
		// for default case
		String defQuery = "SELECT REPORTINGHDR_CODE,REPORTINGHDR_TYPE FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_ISDEFAULT='Y'";
		Object defQueryData[][] = getSqlModel().getSingleResult(defQuery);

		if (defQueryData.length > 0 && defQueryData != null) {
			// default is there
			for (int z = 0; z < defQueryData.length; z++) {

				empIdPType1 = checkEmpIdRepType(String
						.valueOf(defQueryData[z][1]), bean);
				if (empIdPType1) {
					// empId plus Reporting Type combination is there [Header code]
					headerCode = Integer.parseInt(bean.getHeaderCode());
					break;
					//	return headerCode;

				} else {
					// empId plus Reporting Type combination is not there
					// so check for DBG case

					headerCode = checkDBG(String.valueOf(defQueryData[z][1]),
							bean);

					logger.info("after calling checkDBG" + headerCode);

					if (headerCode > 0) {
						break;
					}

					if (headerCode == 0) {
						return 0;
					}

				}
				//return headerCode;

			}

			//headerCode = Integer.parseInt(String.valueOf(defQueryData[0][0]));

			bean.setDefaultFlag("true");
			return headerCode;

		}

		else {
			logger.info("Report TYpe ======================kriss============="
					+ reportType);
			// logger.info("in bean same As"+bean.getSameType());
			// check for same As case
			if (sameType.contains(reportType)) {
				return 0;
			} else {
				// call same as method

				logger.info("sending report to sameAs method========"
						+ reportType);
				reportType = checkSameAS2(reportType, bean);
				logger.info("got report from sameAs method========"
						+ reportType);

				if (sameType.contains(reportType)) {
					return 0;
				} else {
					sameType.add(reportType);
				}

			}

			// check for empId+TYpe case
			empIdPType = checkEmpIdRepType(reportType, bean);
			if (empIdPType) {
				// empId plus Reporting Type combination is there [Header code]
				headerCode = Integer.parseInt(bean.getHeaderCode());
				return headerCode;

			} else {
				// empId plus Reporting Type combination is not there
				// so check for DBG case

				headerCode = checkDBG(reportType, bean);

				logger.info("after calling checkDBG" + headerCode);
				if (headerCode == 0) {
					return 0;
				}

			}
			return headerCode;

		}

	}

	/**
	 * method to check the Report Type with Same as Case
	 * 
	 * @param reportType
	 * @param bean
	 * @return String
	 */
	public String checkSameAS2(String reportType, RepStructure bean) {
		String temp1 = reportType;
		returnType = reportType;
		String key, value;
		ArrayList<String> keyValue = new ArrayList<String>();
		// String temp3;
		String query = "select REPORTINGHDR_SAME_AS from HRMS_REPORTING_STRUCTUREHDR where REPORTINGHDR_TYPE = '"
				+ reportType + "'";

		Object sameAsObj[][] = getSqlModel().getSingleResult(query);

		if (sameAsObj != null && sameAsObj.length > 0
				&& !String.valueOf(sameAsObj[0][0]).equals("null")) {
			reportType = String.valueOf(sameAsObj[0][0]);
			returnType = reportType;
			String temp2, temp3;

			logger.info("map values" + sameTypeName);
			// ---------------------------------------------

			// check the size of the map if size is zero add the element

			if (sameTypeName.size() > 0) {
				// elements r there in map
				temp2 = (String) sameTypeName.get(temp1);
				logger.info("Temp2" + temp2);
				// check temp2 if it is null check for value of the map

				if (temp2 == null) {
					// key is not there for report type so check the map value
					// for report type

					temp3 = (String) sameTypeName.get(reportType);

					if (temp3 == null) {
						sameTypeName.put(reportType, temp1);
					} else {
						String sameTemp = (String) sameTypeName.get(reportType);
						String checkValue[] = sameTemp.split(",");
						boolean check = checkValue(checkValue, temp1);

						if (check) {
							sameTypeName.put(reportType, sameTemp);
						} else {
							// value is not there in combination
							logger.info("sameTemp=============" + sameTemp);
							sameTemp += "," + temp1;
							sameTypeName.put(reportType, sameTemp);
						}

					}

				} else {
					// key is there for report type
					String sameTemp = (String) sameTypeName.get(temp1);

					String checkValue[] = sameTemp.split(",");
					boolean check = checkValue(checkValue, temp1);

					if (check) {
						sameTypeName.put(reportType, sameTemp);
					} else {
						// value is not there in combination
						logger.info("sameTemp=============" + sameTemp);
						sameTemp += "," + temp1;
						sameTypeName.put(reportType, sameTemp);
						sameTypeName.remove(temp1);
					}

				}

			} else {
				// elements r not there in map so add that first element
				logger.info("else case");
				// temp1 += "";
				logger.info("report" + reportType);
				sameTypeName.put(reportType, temp1);

			}

			logger.info("map values" + sameTypeName);
			checkSameAS2(reportType, bean);
			/*
			 * logger.info("returning reporting type by sameAs
			 * method"+reportType);
			 * 
			 * return reportType;
			 */

		} else {
			logger
					.info("returning repoting type by sameAs method"
							+ returnType);
			return returnType;
		}
		logger.info("returning repoting type by sameAs method" + returnType);
		return returnType;
	}

	/**
	 * method to check the Map whether it contains the passed value or not.
	 * 
	 * @param checkValue
	 * @param temp1
	 * @return boolean
	 */
	public boolean checkValue(String[] checkValue, String temp1) {

		for (int i = 0; i < checkValue.length; i++) {
			if (checkValue[i].equals(temp1)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * method to decode the Reporting Structures to display in the Report
	 * 
	 * @param reportingType
	 * @return String
	 */
	public String decodeStructure(String reportingType) {
		if (reportingType.equals("Leave")) {
			logger.info("reporting type is Leave");
			reportingType = "Leave";
		} else if (reportingType.equals("Requi")) {
			logger.info("reporting type is Requisition");
			reportingType = "Requisition";
		} else if (reportingType.equals("Cash")) {
			logger.info("reporting type is Cash");
			reportingType = "Cash Voucher";
		} else if (reportingType.equals("Train")) {
			logger.info("reporting type is Training");
			reportingType = "Training";
		} else if (reportingType.equals("Tran")) {
			logger.info("reporting type is Transfer");
			reportingType = "Transfer";
		} else if (reportingType.equals("Sugg")) {
			logger.info("reporting type is Suggestion");
			reportingType = "Suggestion";
		} else if (reportingType.equals("Help")) {
			logger.info("reporting type is Help Desk");
			reportingType = "Help Desk";
		} else if (reportingType.equals("LTC")) {
			logger.info("reporting type is LTC Advance");
			reportingType = "LTC Advance";
		} else if (reportingType.equals("Festi")) {
			logger.info("reporting type is Festival Advance");
			reportingType = "Festival Advance";
		} else if (reportingType.equals("Other")) {
			logger.info("reporting type is Other Advance");
			reportingType = "Other Advance";
		} else if (reportingType.equals("Deput")) {
			logger.info("reporting type is Deputation");
			reportingType = "Deputation";
		} else if (reportingType.equals("HBA")) {
			logger.info("reporting type is HBA");
			reportingType = "HBA";
		} else if (reportingType.equals("GPF")) {
			logger.info("reporting type is GPF");
			reportingType = "GPF";
		} else if (reportingType.equals("CTF")) {
			logger.info("reporting type is Children Tution Fee");
			reportingType = "Children Tution Fee";
		} else if (reportingType.equals("Medi")) {
			logger.info("reporting type is Medical Advance");
			reportingType = "Medical Advance";
		} else if (reportingType.equals("TYD")) {
			logger.info("reporting type is Travel");
			reportingType = "Travel";
		} else if (reportingType.equals("Appra")) {
			logger.info("reporting type is Appraisal");
			reportingType = "Appraisal";
		} else if (reportingType.equals("Confere")) {
			logger.info("reporting type is Conference");
			reportingType = "Conference";
		} else if (reportingType.equals("Loan")) {
			logger.info("reporting type is Loan");
			reportingType = "Loan";
		} else if (reportingType.equals("Asset")) {
			logger.info("reporting type is Asset");
			reportingType = "Asset";
		}
		return reportingType;
	}

	/**
	 * method to add the Ordinal for the numbers.
	 * 
	 * @param value
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;
		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		}
		int tenRemainder = value % 10;
		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	public void setEmployeeDetails(String userEmpId, RepStructure bean) {

		logger.info("i am in setGeneral of model");
		logger.info("Login Name=========" + loginBean.getLoginName());
		String setQuery = "SELECT  (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME),EMP_ID,EMP_DEPT,EMP_CENTER,EMP_RANK,EMP_TOKEN FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
				+ " LEFT JOIN HRMS_LOGIN ON(HRMS_EMP_OFFC.EMP_ID= HRMS_LOGIN.EMP_ID) WHERE LOGIN_CODE ="
				+ bean.getUserLoginCode();
		Object setGeneral[][] = getSqlModel().getSingleResult(setQuery);
		bean.setEmpName(String.valueOf(setGeneral[0][0]));
		bean.setEmpId(String.valueOf(setGeneral[0][1]));
		bean.setDeptId(String.valueOf(setGeneral[0][2]));
		bean.setBrnchId(String.valueOf(setGeneral[0][3]));
		bean.setDesgnId(String.valueOf(setGeneral[0][4]));
		bean.setEmpTokenNo(String.valueOf(setGeneral[0][5]));

	}

}
