package org.paradyne.model.recruitment;

/**
 * Author:Pradeep Kumar Sahoo
 * Date:31-07-2009
 */
import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import javax.servlet.http.*;
import org.paradyne.bean.Recruitment.ManpwrReqsnAnalysis;

public class ManpowerRequisitionAnalysisModel extends ModelBase {

	public int getCounter(ManpwrReqsnAnalysis bean) {
		int counter;

		counter = 0;
		if (bean.getHidReqsnDate().equals("Y"))
			counter = counter + 1;
		if (bean.getHidPosition().equals("Y"))
			counter = counter + 1;
		if (bean.getHidReqsStatus().equals("Y"))
			counter = counter + 1;
		if (bean.getHidApprvStatus().equals("Y"))
			counter = counter + 1;
		if (bean.getHidNoOfVac().equals("Y"))
			counter = counter + 1;
		if (bean.getHidReqByDate().equals("Y"))
			counter = counter + 1;
		if (bean.getHidOverDue().equals("Y"))
			counter = counter + 1;
		if (bean.getHidTotalCtc().equals("Y"))
			counter = counter + 1;
		if (bean.getHidHiringMngr().equals("Y"))
			counter = counter + 1;
		if (bean.getHidApprvrName().equals("Y"))
			counter = counter + 1;
		if (bean.getHidRecruitName().equals("Y"))
			counter = counter + 1;
		if (bean.getHidClosedDate().equals("Y"))
			counter = counter + 1;

		return counter;
	}

	public static String callStringBreak(String strVal, int breakLen) {
		String strBreak = "";
		try {
			int numOfBreak = 0;
			if (strVal != null && strVal.length() > 0
					&& strVal.length() > breakLen) {
				numOfBreak = strVal.length() / breakLen;
				int j = 0;
				for (int i = breakLen; i < strVal.length();) {
					strBreak += strVal.substring(j, breakLen + j) + " ";
					j = breakLen + j;
					i = i + breakLen;
					strBreak.trim();
				}
				if (j < strVal.length()) {
					strBreak += strVal.substring(j, strVal.length());
					strBreak.trim();
				}
			} else {
				strBreak = strVal;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strBreak;
	}

	public void callSavedLIst(ManpwrReqsnAnalysis bean) {
		try {
			String quer = "SELECT MPRREP_CODE,MPRREP_USER_NAME FROM HRMS_REC_MPRREP_FILTERS WHERE MPRREP_USER_ID="
					+ bean.getUserEmpId();
			Object[][] iterator = getSqlModel().getSingleResult(quer);
			HashMap mp = new HashMap();
			for (int i = 0; i < iterator.length; i++) {
				mp.put(String.valueOf(iterator[i][0]), String
						.valueOf(iterator[i][1]));
			}
			bean.setHashMap(mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is used to generate the report in Pdf,Xls and Doc
	 * format.
	 * 
	 * @param bean
	 * @param response
	 * @param string3
	 * @param string2
	 * @param string
	 */
	public void getManpwrAnalysisReport(ManpwrReqsnAnalysis bean,
			HttpServletResponse response, String sr, String reqsCode,
			String reqsDate, String position, String apprvStatus,
			String reqsStatus, String totCtc, String noOfVac, String reqdDate,
			String closedDate, String overDue, String string, String string2,
			String string3) {
		try {

			String code = "";

			if (!(bean.getScrnReqCode().equals(",")
					|| bean.getScrnReqCode().equals(" ")
					|| bean.getScrnReqCode().equals("") || bean
					.getScrnReqCode().equals("null"))) {
				String requisition[] = bean.getScrnReqCode().split(",");

				if (requisition != null && requisition.length > 0) {

					for (int i = 0; i < requisition.length; i++) {

						if (i == requisition.length - 1) {
							code += requisition[i];
						} else {
							code += requisition[i] + ",";
						}
					}
				}
			}

			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					bean.getRepType(), "Manpower Requisition Analysis", "");
			/*
			 * "SELECT
			 * HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),RANK_NAME," +"
			 * DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On
			 * Hold','B','New Requisition','Q','Quick Requisition')," +" CASE
			 * WHEN REQS_STATUS='O' THEN 'Open' WHEN REQS_STATUS='C' THEN
			 * 'Closed' ELSE 'Rejected' END," +"
			 * REQS_VACAN_COMPEN,SUM(VACAN_NUMBERS),TO_CHAR(MAX(VACAN_REQ_DATE),'DD-MM-YYYY'),TO_CHAR(REQS_CLOSE_DATE,'DD-MM-YYYY'), " +"
			 * CASE WHEN REQS_CLOSE_DATE IS NULL THEN
			 * TO_DATE(SYSDATE,'DD-MM-YYYY')
			 * -TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY') ELSE" +"
			 * TO_DATE(REQS_CLOSE_DATE,'DD-MM-YYYY')
			 * -TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY') END FROM
			 * HRMS_REC_REQS_HDR " +" INNER JOIN HRMS_RANK
			 * ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)" +" INNER
			 * JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.REQS_CODE=
			 * HRMS_REC_REQS_HDR.REQS_CODE)" +" WHERE 1=1 AND
			 * REQS_APPROVAL_STATUS NOT IN('B','P','H','R')" ;
			 */

			String query = " SELECT HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,TO_CHAR(HRMS_REC_REQS_HDR.REQS_DATE,'DD-MM-YYYY'),"
					+ " RANK_NAME,"
					+ " DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
					+ " CASE WHEN REQS_STATUS='O' THEN 'Open' WHEN  REQS_STATUS='C' THEN 'Closed' ELSE 'Rejected' END,"
					+ " REQS_VACAN_COMPEN,SUM(VACAN_NUMBERS),TO_CHAR(MAX(VACAN_REQ_DATE),'DD-MM-YYYY'),TO_CHAR(REQS_CLOSE_DATE,'DD-MM-YYYY'),"
					+ " CASE WHEN REQS_CLOSE_DATE IS NULL THEN TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY') ELSE TO_DATE(REQS_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY')  END,"
					+ " A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME AS HIRINGMANAGER,"
					+ " B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME AS APPROVARNAME,"
					+ " C.EMP_FNAME||' '||C.EMP_MNAME||' '||C.EMP_LNAME AS RECRUITERNAME"
					+ " FROM HRMS_REC_REQS_HDR"
					+ " INNER JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.REQS_CODE= HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"
					+ " INNER JOIN  HRMS_REC_VACPUB_RECDTL ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC A ON(A.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+ " INNER JOIN HRMS_EMP_OFFC B ON(B.EMP_ID=HRMS_REC_REQS_HDR.REQS_APPR_CODE )"
					+ " INNER JOIN HRMS_EMP_OFFC C ON(C.EMP_ID=HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID )"
					+ " INNER JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)"
					+ " WHERE 1=1"
					+ " AND REQS_APPROVAL_STATUS NOT IN('B','P','H','R')";

			if (!(bean.getReqsnCode().equals("") || bean.getReqsnCode().equals(
					"null"))) {
				query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
						+ bean.getReqsnCode();
			}
			if (!bean.getPositionId().equals("")) {
				query += " AND REQS_POSITION =" + bean.getPositionId();
			}

			if (!bean.getReqsnStatus().equals("1")) {

				query += " AND REQS_STATUS='" + bean.getReqsnStatus() + "'";

			}

			if (!(bean.getSelectedReq().equals("null") || bean.getSelectedReq()
					.equals(""))) {

				query += " AND HRMS_REC_REQS_HDR.REQS_CODE IN("
						+ bean.getSelectedReq() + ")";

			}
			if (bean.getReqsnDate().equals("O ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE(" + "'"
							+ bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("B ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("A ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("OB")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("OA")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("F ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

				if (!(bean.getTDate() == null) && !bean.getTDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getTDate() + "'" + ",'DD-MM-YYYY')";
				}

			}

			if (bean.getAdvOverDueOpt().equals("IE")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') ="
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("LT")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') <"
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("GT")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') >"
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("LE")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') <="
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("GE")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') >="
						+ bean.getAdvOverDueVal();
			}

			if (!bean.getAdvApprvStat().equals("1")) {
				query += " AND REQS_APPROVAL_STATUS='" + bean.getAdvApprvStat()
						+ "'";
			}

			query += "GROUP BY"
					+ " HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,REQS_DATE,RANK_NAME,A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME,"
					+ " B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME ,C.EMP_FNAME||' '||C.EMP_MNAME||' '||C.EMP_LNAME,REQS_APPROVAL_STATUS, REQS_STATUS,"
					+ " REQS_VACAN_COMPEN,REQS_CLOSE_DATE";
			// +" ORDER BY reqs_date DESC" ;
			if (bean.getAdvVacOpt().equals("IE")) {
				query += " HAVING SUM(VACAN_NUMBERS) =" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("LT")) {
				query += " HAVING SUM(VACAN_NUMBERS) <" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("GT")) {
				query += " HAVING SUM(VACAN_NUMBERS) >" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("LE")) {
				query += " HAVING SUM(VACAN_NUMBERS) <=" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("GE")) {
				query += " HAVING SUM(VACAN_NUMBERS) >=" + bean.getAdvVacVal();
			}
			query += " order by ";
			if (!(bean.getFirstSort().equals("1"))) {
				if (bean.getFirstSort().equals("RN")) {
					if (bean.getHidFirstAsc().equals("A")) {
						query += "REQS_NAME ASC";
					} else {
						query += "REQS_NAME DESC";
					}
				} else if (bean.getFirstSort().equals("PO")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidPosition().equals("Y")) {
							query += "RANK_NAME ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidPosition().equals("Y")) {
							query += "RANK_NAME DESC";
						} else {
							query += "''";
						}

					}

				} else if (bean.getFirstSort().equals("RS")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidReqsStatus().equals("Y")) {
							query += "REQS_STATUS ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidPosition().equals("Y")) {
							query += "REQS_STATUS DESC";
						} else {
							query += "''";
						}

					}

				} else if (bean.getFirstSort().equals("AS")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidApprvStatus().equals("Y")) {
							query += "REQS_APPROVAL_STATUS ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidApprvStatus().equals("Y")) {
							query += "REQS_APPROVAL_STATUS DESC";
						} else {
							query += "''";
						}

					}

				} else if (bean.getFirstSort().equals("RD")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidReqsnDate().equals("Y")) {
							query += "REQS_DATE ASC";
						} else {
							query += "''";
						}
					} else {

						if (bean.getHidReqsnDate().equals("Y")) {
							query += "REQS_DATE DESC";
						} else {
							query += "''";
						}
					}
				} else if (bean.getFirstSort().equals("NV")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidNoOfVac().equals("Y")) {
							query += " SUM(VACAN_NUMBERS) ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidNoOfVac().equals("Y")) {
							query += "SUM(VACAN_NUMBERS) DESC";
						} else {
							query += "''";
						}

					}
				} else if (bean.getFirstSort().equals("RB")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidReqByDate().equals("Y")) {
							query += " TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidReqByDate().equals("Y")) {
							query += "TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
						} else {
							query += "''";
						}

					}
				}
			}

			if (!(bean.getSecSortBy().equals("1"))) {

				if (bean.getSecSortBy().equals("RN")) {
					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							query += ",REQS_NAME ASC";
						} else {
							query += ",REQS_NAME DESC";
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							query += "REQS_NAME ASC";
						} else {
							query += "REQS_NAME DESC";
						}
					}
				} else if (bean.getSecSortBy().equals("RD")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {
								query += ",REQS_DATE ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {
								query += "REQS_DATE ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidReqsnDate().equals("Y")) {
								query += "REQS_DATE DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("PO")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {
								query += ",RANK_NAME ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {
								query += "RANK_NAME ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidPosition().equals("Y")) {
								query += "RANK_NAME DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("RS")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {
								query += ",REQS_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {
								query += "REQS_STATUS ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidReqsStatus().equals("Y")) {
								query += "REQS_STATUS DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("AS")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {
								query += ",REQS_APPROVAL_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {
								query += "REQS_APPROVAL_STATUS ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidApprvStatus().equals("Y")) {
								query += "REQS_APPROVAL_STATUS DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("NV")) {
					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += "SUM(VACAN_NUMBERS) ASC";
							} else {
								query += "''";
							}

						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += "SUM(VACAN_NUMBERS) DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("RB")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += ",''";
							}
						} else {

							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",'' ";
							}
							// query+=",VACAN_REQ_DATE DESC";
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {

							if (bean.getHidReqByDate().equals("Y")) {
								query += "TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidReqByDate().equals("Y")) {
								query += "TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",''";
							}

						}
					}
				}
			}

			if (!(bean.getThirdSort().equals("1"))) {
				logger.info("in third sorting" + bean.getThirdSort());
				if (bean.getThirdSort().equals("RN")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {

						if (bean.getHidThirdAsc().equals("A")) {
							query += ",REQS_NAME ASC";
						} else {
							query += ",REQS_NAME DESC";
						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							query += "REQS_NAME ASC";
						} else {
							query += "REQS_NAME DESC";
						}
					}
				} else if (bean.getThirdSort().equals("RD")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("PO")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("RS")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("AS")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("NV")) {

					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) DESC";
							} else {
								query += ",''";
							}

						}
					} else {

						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("RB")) {

					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",''";
							}
						}
					}
				}
			}

			if (bean.getFirstSort().equals("1")
					&& bean.getSecSortBy().equals("1")

					&& bean.getThirdSort().equals("1")) {

				if (bean.getHidReqsnDate().equals("Y"))
					query += "reqs_date desc";
				else
					query += "''";
			}

			int counter = getCounter(bean);
			Object[][] reqsns = getSqlModel().getSingleResult(query);
			String col[] = new String[counter + 2];// {"Sr. No","Requisition
													// Code","Requsition
													// Date","Position","No.
													// Of\nVacancies","Required
													// by\nDate","Overdue\nDays","Vacancy
													// Status\nOpen Close"};
			int[] cellWidth = new int[counter + 2];// {8,12,10,10,10,12,10,20};
			int[] alignment = new int[counter + 2];// {1,0,1,0,1,1,1,0};
			if (reqsns != null && reqsns.length > 0) {

				logger.info("val of counter   " + counter);

				int c = 0;
				col[c] = sr;
				cellWidth[c] = 6;
				alignment[c] = 1;
				c++;
				if (bean.getHidReqsn().equals("Y")) {
					col[c] = reqsCode;
					cellWidth[c] = 12;
					alignment[c] = 0;
					c++;

				}

				if (bean.getHidReqsnDate().equals("Y")) {
					col[c] = reqsDate;
					cellWidth[c] = 10;
					alignment[c] = 1;
					c++;
				}

				if (bean.getHidPosition().equals("Y")) {
					col[c] = position;
					cellWidth[c] = 10;
					alignment[c] = 0;
					c++;
				}

				if (bean.getHidApprvStatus().equals("Y")) {
					col[c] = apprvStatus;
					cellWidth[c] = 12;
					alignment[c] = 0;
					c++;

				}

				if (bean.getHidReqsStatus().equals("Y")) {
					col[c] = reqsStatus;
					cellWidth[c] = 12;
					alignment[c] = 0;
					c++;
				}

				if (bean.getHidTotalCtc().equals("Y")) {
					col[c] = totCtc;
					cellWidth[c] = 12;
					alignment[c] = 0;
					c++;
				}

				if (bean.getHidNoOfVac().equals("Y")) {
					col[c] = noOfVac;
					cellWidth[c] = 10;
					alignment[c] = 2;
					c++;
				}

				if (bean.getHidReqByDate().equals("Y")) {
					col[c] = reqdDate;
					cellWidth[c] = 12;
					alignment[c] = 1;
					c++;
				}

				if (bean.getHidClosedDate().equals("Y")) {
					col[c] = closedDate;
					cellWidth[c] = 12;
					alignment[c] = 1;
					c++;
				}

				if (bean.getHidOverDue().equals("Y")) {
					col[c] = overDue;
					cellWidth[c] = 10;
					alignment[c] = 2;
					c++;
				}

				System.out
						.println("................" + bean.getHidHiringMngr());
				System.out
						.println("................" + bean.getHidApprvrName());
				System.out.println("................"
						+ bean.getHidRecruitName());
				if (bean.getHidHiringMngr().equals("Y")) {
					col[c] = string;
					cellWidth[c] = 10;
					alignment[c] = 2;
					c++;
				}
				if (bean.getHidApprvrName().equals("Y")) {
					col[c] = string2;
					cellWidth[c] = 10;
					alignment[c] = 2;
					c++;
				}
				if (bean.getHidRecruitName().equals("Y")) {
					col[c] = string3;
					cellWidth[c] = 10;
					alignment[c] = 2;
					c++;
				}

				if (bean.getExportAllData().equals("N")) {

					String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							reqsns.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					Object[][] data = new Object[numOfRec][counter + 2];
					int j = 0;
					int n = 0;
					int cntr = 0;
					int k = 0;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {

						data[k][cntr] = ++j;// Serial No.
						cntr++;
						data[k][cntr] = String.valueOf(reqsns[i][1]);
						cntr++;
						if (bean.getHidReqsnDate().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][2]);// Requisition
																			// date
							cntr++;
						}

						if (bean.getHidPosition().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][3]);// Position
							cntr++;
						}

						if (bean.getHidApprvStatus().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][4]);// Requisition
																			// Approval
																			// Status
							cntr++;
						}

						if (bean.getHidReqsStatus().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][5]);// Requisition
																			// Status
							cntr++;
						}

						if (bean.getHidTotalCtc().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][6]));// Total Ctc
							cntr++;
						}

						if (bean.getHidNoOfVac().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][7]));// Vacancy Numbers
							cntr++;
						}

						if (bean.getHidReqByDate().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][8]));// Required by Date
							cntr++;
						}

						if (bean.getHidClosedDate().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][9]));// Closed date
							cntr++;

						}

						if (bean.getHidOverDue().equals("Y")) {
							// if(String.valueOf(reqsns[i][9]).equals("") ||
							// String.valueOf(reqsns[i][9]).equals("null"))
							// data[k][cntr]=checkNull(String.valueOf(reqsns[i][10]));//Overdue
							// Days
							// else
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][10]));// Overdue Days

						}
						if (bean.getHidHiringMngr().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][11]));// Closed date
							cntr++;

						}
						if (bean.getHidApprvrName().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][12]));// Closed date
							cntr++;

						}
						if (bean.getHidRecruitName().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][13]));// Closed date
							cntr++;

						}

						cntr = 0;
						k++;

					}

					if (bean.getRepType().equals("Pdf")) {
						rg.setFName("Manpower Requisition Analysis");
						rg.addFormatedText("Manpower Requisition Analysis", 6,
								0, 1, 1);
						rg.addText("\n", 0, 0, 0);
					} else if (bean.getRepType().equals("Xls")) {
						rg.setFName("Manpower Requisition Analysis.xls");
						rg.addText("Manpower Requisition Analysis", 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
					} else {
						rg.setFName("Manpower Requisition Analysis.doc");
						rg.addText("Manpower Requisition Analysis", 0, 1, 0);
					}

					rg.tableBody(col, data, cellWidth, alignment);

				} else {
					Object[][] data = new Object[reqsns.length][counter + 2];

					int j = 0;
					int k = 0;
					int cntr = 0;
					int kk = 0;
					for (int i = 0; i < reqsns.length; i++) {
						data[k][cntr] = ++j;// Serial No.
						cntr++;
						data[k][cntr] = String.valueOf(reqsns[i][1]);
						cntr++;
						if (bean.getHidReqsnDate().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][2]);// Requisition
																			// date
							cntr++;
						}

						if (bean.getHidPosition().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][3]);// Position
							cntr++;
						}

						if (bean.getHidApprvStatus().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][4]);// Requisition
																			// Approval
																			// Status
							cntr++;
						}

						if (bean.getHidReqsStatus().equals("Y")) {
							data[k][cntr] = String.valueOf(reqsns[i][5]);// Requisition
																			// Status
							cntr++;
						}

						if (bean.getHidTotalCtc().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][6]));// Total Ctc
							cntr++;
						}

						if (bean.getHidNoOfVac().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][7]));// Vacancy Numbers
							cntr++;
						}

						if (bean.getHidReqByDate().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][8]));// Required by Date
							cntr++;
						}

						if (bean.getHidClosedDate().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][9]));// Closed date
							logger.info("closed date>>>>" + data[k][cntr]);
							cntr++;

						}

						logger.info("overdue days>>>>" + reqsns[i][10]);
						if (bean.getHidOverDue().equals("Y")) {

							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][10]));// Overdue Days
							cntr++;
						}
						if (bean.getHidHiringMngr().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][11]));// hiring
																// manager
							cntr++;
						}
						if (bean.getHidApprvrName().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][12]));// approver name
							cntr++;
						}
						if (bean.getHidRecruitName().equals("Y")) {
							data[k][cntr] = checkNull(String
									.valueOf(reqsns[i][13]));// recruiter
																// name
							cntr++;
						}

						cntr = 0;
						k++;
					}// End of for loop

					if (bean.getRepType().equals("Pdf")) {
						rg.setFName("Manpower Requisition Analysis");
						rg.addFormatedText("Manpower Requisition Analysis", 6,
								0, 1, 1);
						rg.addText("\n", 0, 0, 0);
					} else if (bean.getRepType().equals("Xls")) {
						rg.setFName("Manpower Requisition Analysis.xls");
						rg.addText("Manpower Requisition Analysis", 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
					} else {
						rg.setFName("Manpower Requisition Analysis.doc");
						rg.addText("Manpower Requisition Analysis", 0, 1, 0);
					}

					rg.tableBody(col, data, cellWidth, alignment);

				}

			}// End of if condition
			else {
				rg.addText("No records found for the selected criteria.", 0, 1,
						0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to find the number of closed vacancies for
	 * the corresponding vacancy detail code
	 * 
	 * @param vacDetCode
	 * @return
	 * @throws Exception
	 */
	public int getVacancyDetails(String vacDetCode) throws Exception {
		int vac = 0;
		try {
			if (!(vacDetCode.equals("null") || vacDetCode.equals(""))) {
				String pubQuery = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR WHERE PUB_VACAN_DTLCODE="
						+ vacDetCode;// 4084
				Object[][] pubData = getSqlModel().getSingleResult(pubQuery);
				if (pubData != null && pubData.length > 0) {
					String vacQuery = "SELECT SUM(PUB_CLOSE_VACAN) FROM HRMS_REC_VACPUB_RECDTL WHERE PUB_CODE ="
							+ String.valueOf(pubData[0][0]);
					Object[][] vacData = getSqlModel()
							.getSingleResult(vacQuery);
					if (vacData != null && vacData.length > 0) {
						if (!(String.valueOf(vacData[0][0]).equals("null") || String
								.valueOf(vacData[0][0]).equals("")))
							vac = Integer.parseInt(String
									.valueOf(vacData[0][0]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vac;

	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ManpowerRequisitionAnalysisModel.class);

	/**
	 * following function is used to display the requisition details along with
	 * the check boxes.
	 * 
	 * @param bean
	 */
	public void displayReq(ManpwrReqsnAnalysis bean) {

		try {
			String query = " SELECT REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' "
					+ " ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE FROM HRMS_REC_REQS_HDR  "
					+ " INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
					+ " WHERE  REQS_APPROVAL_STATUS NOT IN('B','P','H','R') AND 1=1  ";

			if (!bean.getPositionId().equals("")) {
				query += " AND REQS_POSITION =" + bean.getPositionId();
			}
			if (bean.getReqsnDate().equals("O ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE(" + "'"
							+ bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("B ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("A ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("OB")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("OA")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("F ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

				if (!(bean.getTDate() == null) && !bean.getTDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getTDate() + "'" + ",'DD-MM-YYYY')";
				}

			}

			if (!(bean.getReqsnStatus().equals("1"))) {
				query += " AND REQS_STATUS='" + bean.getReqsnStatus() + "'";
			}

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setShowReqsnFlag("true");
				bean.setNoDataReq("false");
				bean.setDataLength("" + data.length);
				int counter = 0;
				ArrayList<Object> list = new ArrayList<Object>();
				for (int i = 0; i < data.length; i++) {
					counter++;
					ManpwrReqsnAnalysis bean1 = new ManpwrReqsnAnalysis();
					bean1.setItReqName(callStringBreak("" + data[i][0], 15));
					bean1.setItPosition("" + data[i][1]);
					bean1.setItReqDate("" + data[i][2]);
					bean1.setItStatus("" + data[i][3]);
					bean1.setItReqCode("" + data[i][4]);
					list.add(bean1);
					if (bean.getEditReqFlag().equals("true")) {
						String[] selectedreqId = bean.getSelectedReq().split(
								",");
						for (int j = 0; j < selectedreqId.length; j++) {
							if (selectedreqId[j].equals("" + data[i][4])) {
								logger
										.info("getSelectedReqFlag --------------->  "
												+ bean1.getSelectedReqFlag());
								bean1.setSelectedReqFlag("checked");
								break;
							}
						}
					}
				}
				bean.setDispList(list);
				bean.setDataLen(String.valueOf(counter));
			} else {
				bean.setNoDataReq("true");
				bean.setShowReqsnFlag("false");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Method to replace the null with a space. @param result @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * following function is used to insert the save settings into the database
	 * 
	 * @param bean
	 * @return
	 */
	public boolean saveSettings(ManpwrReqsnAnalysis bean) {
		boolean result = false;
		try {
			Object[][] filtOpt = new Object[1][9];
			Object[][] sortOpt = new Object[1][7];
			Object[][] colDef = new Object[1][13];
			Object[][] advFilt = new Object[1][6];
			Object[][] reqsData = new Object[1][2];
			/**
			 * following code is used to insert the filter option details
			 */
			filtOpt[0][0] = bean.getReqsnCode();
			filtOpt[0][1] = bean.getPositionId();
			filtOpt[0][2] = bean.getReqsnDate();
			filtOpt[0][3] = bean.getFrmDate();
			filtOpt[0][4] = bean.getTDate();
			if (bean.getRepType().equals("Pdf")) {
				filtOpt[0][5] = String.valueOf("P");// bean.getRepType();
			} else if (bean.getRepType().equals("Txt")) {
				filtOpt[0][5] = String.valueOf("T");
			} else if (bean.getRepType().equals("Xls")) {
				filtOpt[0][5] = String.valueOf("X");
			} else {
				filtOpt[0][5] = String.valueOf("");
			}
			filtOpt[0][6] = bean.getSettingName();
			filtOpt[0][7] = bean.getReqsnStatus();
			filtOpt[0][8] = bean.getUserEmpId();
			result = getSqlModel().singleExecute(getQuery(1), filtOpt);
			if (result) {

				String query = "SELECT MAX(MPRREP_CODE) FROM HRMS_REC_MPRREP_FILTERS";
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setMraRepCode(String.valueOf(data[0][0]));
				}
			}
			/**
			 * following code is used to insert the sorting details
			 */
			sortOpt[0][0] = bean.getMraRepCode();
			sortOpt[0][1] = bean.getFirstSort();
			if (bean.getHidFirstAsc().equals("A")) {
				sortOpt[0][2] = bean.getHidFirstAsc();
			} else {
				sortOpt[0][2] = String.valueOf("D");
			}
			sortOpt[0][3] = bean.getSecSortBy();
			if (bean.getHidSecAsc().equals("A")) {
				sortOpt[0][4] = bean.getHidSecAsc();
			} else {
				sortOpt[0][4] = String.valueOf("D");
			}
			sortOpt[0][5] = bean.getThirdSort();
			if (bean.getHidThirdAsc().equals("A")) {
				sortOpt[0][6] = bean.getHidThirdAsc();
			} else {
				sortOpt[0][6] = String.valueOf("D");
			}
			getSqlModel().singleExecute(getQuery(2), sortOpt);
			/**
			 * FOLLOWING CODE IS USED TO INSERT THE COLUMN DEFINITIONS
			 */
			colDef[0][0] = bean.getMraRepCode();
			colDef[0][1] = bean.getHidReqsnDate();
			colDef[0][2] = bean.getHidNoOfVac();
			colDef[0][3] = bean.getHidClosedDate();// Closed Date
			colDef[0][4] = bean.getHidReqByDate();
			colDef[0][5] = bean.getHidOverDue();
			colDef[0][6] = bean.getHidTotalCtc();// Total Ctc
			colDef[0][7] = bean.getHidPosition();
			colDef[0][8] = bean.getHidReqsStatus();
			colDef[0][9] = bean.getHidApprvStatus();
			colDef[0][10] = bean.getHidHiringMngr();
			colDef[0][11] = bean.getHidApprvrName();
			colDef[0][12] = bean.getHidRecruitName();
			getSqlModel().singleExecute(getQuery(3), colDef);
			/*
			 * following code is used to insert the advance filter details
			 */
			advFilt[0][0] = bean.getMraRepCode();
			advFilt[0][1] = bean.getAdvVacOpt();
			advFilt[0][2] = bean.getAdvVacVal();
			advFilt[0][3] = bean.getAdvOverDueOpt();
			advFilt[0][4] = bean.getAdvOverDueVal();
			advFilt[0][5] = bean.getAdvApprvStat();
			getSqlModel().singleExecute(getQuery(4), advFilt);
			/**
			 * FOLLOWING code is used to insert the selected requisition details
			 */
			if (bean.getSelectedReq().length() > 0
					&& bean.getSelectedReq() != null) {
				String[] str = bean.getSelectedReq().split(",");
				for (int i = 0; i < str.length; i++) {
					reqsData[0][0] = str[i];
					reqsData[0][1] = bean.getMraRepCode();
					getSqlModel().singleExecute(getQuery(5), reqsData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to update the settings
	 * 
	 * @param bean
	 * @return
	 */
	public boolean updateSettings(ManpwrReqsnAnalysis bean) {
		boolean result = false;
		try {
			Object[][] filtOpt = new Object[1][8];
			Object[][] sortOpt = new Object[1][7];
			Object[][] colDef = new Object[1][13];
			Object[][] advFilt = new Object[1][6];
			Object[][] reqsData = new Object[1][2];
			Object[][] del = new Object[1][1];
			del[0][0] = bean.getMraRepCode();
			/**
			 * following code is used to UPDATE the filter option details
			 */
			filtOpt[0][0] = bean.getReqsnCode();
			filtOpt[0][1] = bean.getPositionId();
			filtOpt[0][2] = bean.getReqsnDate();
			filtOpt[0][3] = bean.getFrmDate();
			filtOpt[0][4] = bean.getTDate();
			if (bean.getRepType().equals("Pdf")) {
				filtOpt[0][5] = String.valueOf("P");// bean.getRepType();
			} else if (bean.getRepType().equals("Txt")) {
				filtOpt[0][5] = String.valueOf("T");
			} else if (bean.getRepType().equals("Xls")) {
				filtOpt[0][5] = String.valueOf("X");
			} else {
				filtOpt[0][5] = String.valueOf("");
			}
			filtOpt[0][6] = bean.getReqsnStatus();
			filtOpt[0][7] = bean.getMraRepCode();
			result = getSqlModel().singleExecute(getQuery(6), filtOpt);
			/**
			 * following code is used to insert the sorting details
			 */
			sortOpt[0][0] = bean.getFirstSort();
			if (bean.getHidFirstAsc().equals("A")) {
				sortOpt[0][1] = bean.getHidFirstAsc();
			} else {
				sortOpt[0][1] = String.valueOf("D");
			}
			sortOpt[0][2] = bean.getSecSortBy();
			if (bean.getHidSecAsc().equals("A")) {
				sortOpt[0][3] = bean.getHidSecAsc();
			} else {
				sortOpt[0][3] = String.valueOf("D");
			}
			sortOpt[0][4] = bean.getThirdSort();
			if (bean.getHidThirdAsc().equals("A")) {
				sortOpt[0][5] = bean.getHidThirdAsc();
			} else {
				sortOpt[0][5] = String.valueOf("D");
			}
			sortOpt[0][6] = bean.getMraRepCode();
			getSqlModel().singleExecute(getQuery(7), sortOpt);
			/**
			 * FOLLOWING CODE IS USED TO UPDATE THE COLUMN DEFINITIONS
			 */
			colDef[0][0] = bean.getHidReqsnDate();
			colDef[0][1] = bean.getHidNoOfVac();
			colDef[0][2] = bean.getHidClosedDate();// Closed Date
			colDef[0][3] = bean.getHidReqByDate();
			colDef[0][4] = bean.getHidOverDue();
			colDef[0][5] = bean.getHidTotalCtc();// Total Ctc
			colDef[0][6] = bean.getHidPosition();
			colDef[0][7] = bean.getHidReqsStatus();
			colDef[0][8] = bean.getHidApprvStatus();
			colDef[0][9] = bean.getHidHiringMngr();
			colDef[0][10] = bean.getHidApprvrName();
			colDef[0][11] = bean.getHidRecruitName();
			colDef[0][12] = bean.getMraRepCode();
			getSqlModel().singleExecute(getQuery(8), colDef);
			/*
			 * following code is used to update the advance filter details
			 */advFilt[0][0] = bean.getAdvVacOpt();
			advFilt[0][1] = bean.getAdvVacVal();
			advFilt[0][2] = bean.getAdvOverDueOpt();
			advFilt[0][3] = bean.getAdvOverDueVal();
			advFilt[0][4] = bean.getAdvApprvStat();
			advFilt[0][5] = bean.getMraRepCode();
			getSqlModel().singleExecute(getQuery(9), advFilt);
			/**
			 * FOLLOWING code is used to insert the selected requisition details
			 */
			String query = "DELETE FROM HRMS_REC_MPRREP_REQS WHERE MPRREP_CODE=? ";
			getSqlModel().singleExecute(query, del);
			if (bean.getSelectedReq().length() > 0
					&& bean.getSelectedReq() != null) {
				String[] str = bean.getSelectedReq().split(",");
				for (int i = 0; i < str.length; i++) {
					reqsData[0][0] = str[i];
					reqsData[0][1] = bean.getMraRepCode();
					getSqlModel().singleExecute(getQuery(5), reqsData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called to display the setting names saved for the
	 * user. When the user makes login at that following function is called.
	 * 
	 * @param bean
	 */
	public void getUserSetting(ManpwrReqsnAnalysis bean) {
		try {
			String quer = "SELECT MPRREP_CODE,MPRREP_USER_NAME FROM HRMS_REC_MPRREP_FILTERS WHERE MPRREP_CODE="
					+ bean.getMraRepCode();
			Object[][] iteratorObj = getSqlModel().getSingleResult(quer);
			if (iteratorObj != null && iteratorObj.length > 0) {
				bean.setSearchSetting(String.valueOf(iteratorObj[0][1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to display the filter option details when
	 * the value in the search setting is changed
	 * 
	 * @param bean
	 */
	public void getFilterDetails(ManpwrReqsnAnalysis bean) {
		try {
			String filterQuery = "SELECT REQS_NAME,RANK_NAME,MPRREP_DATE_OPT,TO_CHAR(MPRREP_FRM_DATE,'DD-MM-YYYY'),TO_CHAR(MPRREP_TO_DATE,'DD-MM-YYYY')"
					+ ",CASE WHEN MPRREP_REP_OPT='P' THEN 'Pdf' WHEN MPRREP_REP_OPT='T' THEN 'Txt' WHEN MPRREP_REP_OPT='X' THEN 'Xls' END ,HRMS_REC_REQS_HDR.REQS_CODE ,"
					+ " RANK_ID,MPRREP_USER_NAME,MPRREP_STATUS FROM HRMS_REC_MPRREP_FILTERS LEFT JOIN HRMS_REC_REQS_HDR ON"
					+ " (HRMS_REC_MPRREP_FILTERS.MPRREP_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_REC_MPRREP_FILTERS.MPRREP_POSITION_CODE=HRMS_RANK.RANK_ID)"
					+ " WHERE MPRREP_CODE=" + bean.getMraRepCode();
			Object[][] data = getSqlModel().getSingleResult(filterQuery);
			if (data != null && data.length > 0) {
				bean.setReqsnName(checkNull(String.valueOf(data[0][0])));
				bean.setPositionName(checkNull(String.valueOf(data[0][1])));
				bean.setReqsnDate(checkNull(String.valueOf(data[0][2])));
				bean.setFrmDate(checkNull(String.valueOf(data[0][3])));
				bean.setTDate(checkNull(String.valueOf(data[0][4])));
				if (!(String.valueOf(data[0][5]).equals("") || String.valueOf(
						data[0][5]).equals("null"))) {
					bean.setReportFlag("true");
				} else {
					bean.setReportFlag("false");
				}
				bean.setRepType(checkNull(String.valueOf(data[0][5])));
				bean.setReqsnCode(checkNull(String.valueOf(data[0][6])));
				if (String.valueOf(data[0][6]).equals("")
						|| String.valueOf(data[0][6]).equals("null")
						|| String.valueOf(data[0][6]).equals(" ")) {
					bean.setReqsnFlg("false");
				} else {
					bean.setReqsnFlg("true");
				}
				if (String.valueOf(data[0][7]).equals("")
						|| String.valueOf(data[0][7]).equals("null")
						|| String.valueOf(data[0][7]).equals(" ")) {
					bean.setPositionFlg("false");
				} else {
					bean.setPositionFlg("true");
				}
				bean.setPositionId(checkNull(String.valueOf(data[0][7])));
				bean.setSettingName(checkNull(String.valueOf(data[0][8])));
				bean.setReqsnStatus(checkNull(String.valueOf(data[0][9])));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * following method is called to display the sorting details when the search
	 * setting value is selected from the combo box.
	 * 
	 * @param bean
	 */
	public void getSortingDetails(ManpwrReqsnAnalysis bean) {
		try {
			String sortingQuery = "SELECT SORT_BY,SORT_BY_ORDER,SORT_THENBY,SORT_THENBY_ORDER,SORT_THENBY1,SORT_THENBY_OREDR1 "
					+ "  FROM HRMS_REC_MPRREP_SORT WHERE MPRREP_CODE="
					+ bean.getMraRepCode();

			Object[][] data = getSqlModel().getSingleResult(sortingQuery);
			if (data != null && data.length > 0) {
				bean.setRadioFlag("true");
				bean.setFirstSort(checkNull(String.valueOf(data[0][0])));
				if (String.valueOf(data[0][1]).equals("A")) {
					bean.setHidFirstAsc("A");
					bean.setRadio1("true");
					bean.setFirstRadio("false");
				} else {
					bean.setHidFirstDesc("D");
					bean.setRadioFlag1("true");
					bean.setFirstRadio("true");
				}
				bean.setSecSortBy(checkNull(String.valueOf(data[0][2])));
				if (String.valueOf(data[0][3]).equals("A")) {
					bean.setHidSecAsc("A");
					bean.setRadio2("true");
					bean.setSecondRadio("false");
				} else {
					bean.setHidSecDesc("D");
					bean.setRadioFlag2("true");
					bean.setSecondRadio("true");
				}

				bean.setThirdSort(checkNull(String.valueOf(data[0][4])));
				if (String.valueOf(data[0][5]).equals("A")) {
					bean.setHidThirdAsc("A");
					bean.setRadio3("true");
					bean.setThirdRadio("false");
				} else {
					bean.setHidThirdDesc("D");
					bean.setRadioFlag3("true");
					bean.setThirdRadio("true");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called display the advance filter details from the
	 * database when the saved Settings name is selected from the drop down
	 * list.
	 * 
	 * @param bean
	 */
	public void getAdvFilter(ManpwrReqsnAnalysis bean) {
		try {
			String advanceQuery = "SELECT ADV_VAC_OPT,ADV_VAC_VAL,ADV_OVER_OPT,ADV_OVER_VAL,ADV_REQS_APPRVL_STAT "
					+ "  FROM HRMS_REC_MPRREP_ADV WHERE MPRREP_CODE="
					+ bean.getMraRepCode();

			Object[][] data = getSqlModel().getSingleResult(advanceQuery);
			if (data != null && data.length > 0) {
				bean.setAdvVacOpt(checkNull(String.valueOf(data[0][0])));
				bean.setAdvVacVal(checkNull(String.valueOf(data[0][1])));
				bean.setAdvOverDueOpt(checkNull(String.valueOf(data[0][2])));
				bean.setAdvOverDueVal(checkNull(String.valueOf(data[0][3])));
				bean.setAdvApprvStat(checkNull(String.valueOf(data[0][4])));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to display the requisition details when the
	 * search setting drop down is selected.
	 * 
	 * @param bean
	 */
	public void getReqsnDetails(ManpwrReqsnAnalysis bean) {
		try {
			Object[] code = new Object[1];
			code[0] = bean.getMraRepCode();
			Object[][] filters = getSqlModel().getSingleResult(getQuery(10),
					code);
			String str = "";
			String str1 = "";
			if (filters != null && filters.length > 0) {
				bean.setShowReqsnFlag("true");
				for (int i = 0; i < filters.length; i++) {
					if (i == filters.length - 1) {
						str += checkNull(String.valueOf(filters[i][0]));
						str1 += checkNull(String.valueOf(filters[i][1]));
					} else {
						str += checkNull(String.valueOf(filters[i][0])) + ",";
						str1 += checkNull(String.valueOf(filters[i][1])) + ",";
					}
				}
				bean.setSelectedReq(str);
				bean.setSelectedReqName(str1);
				bean.setHidSelectedReqName(str1);
			} else {
				bean.setShowReqsnFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to display the column definition details
	 * when the search setting value is selected from the drop down list.
	 * 
	 * @param bean
	 * @param request
	 */
	public void getColumnDef(ManpwrReqsnAnalysis bean,
			HttpServletRequest request) {
		try {
			String colmnQuery = "SELECT COL_REQS_DATE,COL_VACANCIES,COL_REQBY_DATE,COL_OVERDUE,COL_VAC_STATUS,COL_OPENVAC_STATUS,COL_POSITION,COL_REQS_STAT,COL_APPRV_STAT, "
					+ " COL_HIRING_MANAGER, COL_APPROVER_NAME, COL_RECRUITER_NAME FROM "
					+ " HRMS_REC_MPRREP_COLDEF WHERE MPRREP_CODE="
					+ bean.getMraRepCode();

			Object[][] column = getSqlModel().getSingleResult(colmnQuery);
			if (column != null && column.length > 0) {
				bean.setCheckFlag("true");
				Object[][] checkBox = new Object[1][12];
				if (String.valueOf(column[0][0]).equals("Y")) {
					checkBox[0][0] = String.valueOf("true");
					bean
							.setHidReqsnDate(checkNull(String
									.valueOf(column[0][0])));
					bean.setDateChk("true");
				} else {
					checkBox[0][0] = String.valueOf("false");
					bean.setHidReqsnDate(String.valueOf("N"));
					bean.setDateChk("false");
				}

				if (String.valueOf(column[0][1]).equals("Y")) {
					checkBox[0][1] = String.valueOf("true");
					bean.setHidNoOfVac(checkNull(String.valueOf(column[0][1])));
					bean.setVacChk("true");
				} else {
					checkBox[0][1] = String.valueOf("false");
					bean.setHidNoOfVac(checkNull(String.valueOf(column[0][1])));
					bean.setVacChk("false");
				}

				if (String.valueOf(column[0][2]).equals("Y")) {
					checkBox[0][2] = String.valueOf("true");
					bean
							.setHidReqByDate(checkNull(String
									.valueOf(column[0][2])));
					bean.setReqChk("true");
				} else {
					checkBox[0][2] = String.valueOf("false");
					bean.setHidReqByDate("N");
					bean.setReqChk("false");
				}

				if (String.valueOf(column[0][3]).equals("Y")) {
					checkBox[0][3] = String.valueOf("true");
					bean.setHidOverDue(checkNull(String.valueOf(column[0][3])));
					bean.setOverChk("true");
				} else {
					checkBox[0][3] = String.valueOf("false");
					bean.setHidOverDue("N");
					bean.setOverChk("false");
				}

				if (String.valueOf(column[0][4]).equals("Y")) {
					checkBox[0][4] = String.valueOf("true");
					bean.setHidClosedDate(checkNull(String
							.valueOf(column[0][4])));
					bean.setClosedDateChk("true");

				} else {
					checkBox[0][4] = String.valueOf("false");
					bean.setHidClosedDate("N");
					bean.setClosedDateChk("false");
				}

				if (String.valueOf(column[0][5]).equals("Y")) {
					checkBox[0][5] = String.valueOf("true");
					bean
							.setHidTotalCtc(checkNull(String
									.valueOf(column[0][5])));
					bean.setTotalCtcChk("true");
				} else {
					checkBox[0][5] = String.valueOf("false");
					bean.setHidTotalCtc("N");
					bean.setTotalCtcChk("false");
				}

				if (String.valueOf(column[0][6]).equals("Y")) {
					checkBox[0][6] = String.valueOf("true");
					bean
							.setHidPosition(checkNull(String
									.valueOf(column[0][6])));
					bean.setPositionChk("true");
				} else {
					checkBox[0][6] = String.valueOf("false");
					bean.setHidPosition("N");
					bean.setPositionChk("false");
				}

				if (String.valueOf(column[0][7]).equals("Y")) {
					checkBox[0][7] = String.valueOf("true");
					bean.setHidReqsStatus(checkNull(String
							.valueOf(column[0][7])));
					bean.setStatusChk("true");
				} else {
					checkBox[0][7] = String.valueOf("false");
					bean.setHidReqsStatus("N");
					bean.setStatusChk("false");
				}

				if (String.valueOf(column[0][8]).equals("Y")) {
					checkBox[0][8] = String.valueOf("true");
					bean.setHidApprvStatus(checkNull(String
							.valueOf(column[0][8])));
					bean.setApprvChk("true");
				} else {
					checkBox[0][8] = String.valueOf("false");
					bean.setHidApprvStatus("N");
					bean.setApprvChk("false");
				}

				if (String.valueOf(column[0][9]).equals("Y")) {
					checkBox[0][9] = String.valueOf("true");
					bean.setHidHiringMngr(checkNull(String
							.valueOf(column[0][9])));
					bean.setHiringMngrChk("true");
				} else {
					checkBox[0][9] = String.valueOf("false");
					bean.setHidHiringMngr("N");
					bean.setHiringMngrChk("false");
				}

				if (String.valueOf(column[0][10]).equals("Y")) {
					checkBox[0][10] = String.valueOf("true");
					bean.setHidApprvrName(checkNull(String
							.valueOf(column[0][10])));
					bean.setApprvrNameChk("true");
				} else {
					checkBox[0][10] = String.valueOf("false");
					bean.setHidApprvrName("N");
					bean.setApprvrNameChk("false");
				}

				if (String.valueOf(column[0][11]).equals("Y")) {
					checkBox[0][11] = String.valueOf("true");
					bean.setHidRecruitName(checkNull(String
							.valueOf(column[0][11])));
					bean.setRecruitNameChk("true");
				} else {
					checkBox[0][11] = String.valueOf("false");
					bean.setHidRecruitName("N");
					bean.setRecruitNameChk("false");
				}

				request.setAttribute("checkBox", checkBox);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to display the requisition details page
	 * wise. When the View On Screen radio button is checked and Generate Report
	 * button is clicked system will call this following function.
	 * 
	 * @param bean
	 * @param request
	 */

	public void getDisplayInJsp(ManpwrReqsnAnalysis bean,
			HttpServletRequest request) {

		try {

			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					bean.getRepType(), "Manpower Requisition Analysis ");
			/*
			 * String query="SELECT
			 * HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),RANK_NAME," +"
			 * DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On
			 * Hold','B','New Requisition','Q','Quick Requisition')," +" CASE
			 * WHEN REQS_STATUS='O' THEN 'Open' WHEN REQS_STATUS='C' THEN
			 * 'Closed' ELSE 'Rejected' END," +"
			 * REQS_VACAN_COMPEN,SUM(VACAN_NUMBERS),TO_CHAR(MAX(VACAN_REQ_DATE),'DD-MM-YYYY'),TO_CHAR(REQS_CLOSE_DATE,'DD-MM-YYYY'), " // +"
			 * TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY'),TO_DATE(REQS_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')
			 * FROM HRMS_REC_REQS_HDR " +" CASE WHEN REQS_CLOSE_DATE IS NULL
			 * THEN TO_DATE(SYSDATE,'DD-MM-YYYY')
			 * -TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY') ELSE" +"
			 * TO_DATE(REQS_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY')
			 * END FROM HRMS_REC_REQS_HDR " +" INNER JOIN HRMS_RANK
			 * ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)" +" INNER
			 * JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.REQS_CODE=
			 * HRMS_REC_REQS_HDR.REQS_CODE)" +" WHERE 1=1 AND
			 * REQS_APPROVAL_STATUS NOT IN('B','P','H','R')" ;
			 */

			String query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,TO_CHAR(HRMS_REC_REQS_HDR.REQS_DATE,'DD-MM-YYYY'),"
					+ " RANK_NAME,"
					+ " DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
					+ " CASE WHEN REQS_STATUS='O' THEN 'Open' WHEN  REQS_STATUS='C' THEN 'Closed' ELSE 'Rejected' END,"
					+ " REQS_VACAN_COMPEN,SUM(VACAN_NUMBERS),TO_CHAR(MAX(VACAN_REQ_DATE),'DD-MM-YYYY'),TO_CHAR(REQS_CLOSE_DATE,'DD-MM-YYYY'),"
					+ " CASE WHEN REQS_CLOSE_DATE IS NULL THEN TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY') ELSE TO_DATE(REQS_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(MAX(VACAN_REQ_DATE),'DD-MM-YYYY')  END,"
					+ " A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME AS HIRINGMANAGER,"
					+ " B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME AS APPROVARNAME,"
					+ " C.EMP_FNAME||' '||C.EMP_MNAME||' '||C.EMP_LNAME AS RECRUITERNAME"
					+ " FROM HRMS_REC_REQS_HDR"
					+ " INNER JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.REQS_CODE= HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"
					+ " INNER JOIN  HRMS_REC_VACPUB_RECDTL ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC A ON(A.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+ " INNER JOIN HRMS_EMP_OFFC B ON(B.EMP_ID=HRMS_REC_REQS_HDR.REQS_APPR_CODE )"
					+ " INNER JOIN HRMS_EMP_OFFC C ON(C.EMP_ID=HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID )"
					+ " INNER JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)"
					+ " WHERE 1=1"
					+ " AND REQS_APPROVAL_STATUS NOT IN('B','P','H','R')";

			if (!(bean.getReqsnCode().equals("") || bean.getReqsnCode().equals(
					"null"))) {
				query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
						+ bean.getReqsnCode();
			}
			if (!bean.getPositionId().equals("")) {
				query += " AND REQS_POSITION =" + bean.getPositionId();
			}

			if (!bean.getReqsnStatus().equals("1")) {

				query += " AND REQS_STATUS='" + bean.getReqsnStatus() + "'";

			}

			if (!(bean.getSelectedReq().equals("null") || bean.getSelectedReq()
					.equals(""))) {

				query += " AND HRMS_REC_REQS_HDR.REQS_CODE IN("
						+ bean.getSelectedReq() + ")";

			}
			if (bean.getReqsnDate().equals("O ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE(" + "'"
							+ bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("B ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("A ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("OB")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("OA")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsnDate().equals("F ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

				if (!(bean.getTDate() == null) && !bean.getTDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getTDate() + "'" + ",'DD-MM-YYYY')";
				}

			}

			if (bean.getAdvOverDueOpt().equals("IE")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') ="
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("LT")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') <"
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("GT")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') >"
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("LE")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') <="
						+ bean.getAdvOverDueVal();
			}

			if (bean.getAdvOverDueOpt().equals("GE")) {
				query += " AND TO_DATE(VACAN_CLOSE_DATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') >="
						+ bean.getAdvOverDueVal();
			}

			if (!bean.getAdvApprvStat().equals("1")) {
				query += " AND REQS_APPROVAL_STATUS='" + bean.getAdvApprvStat()
						+ "'";
			}

			/*
			 * query+="GROUP BY
			 * HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,REQS_DATE,RANK_NAME,REQS_APPROVAL_STATUS," +"
			 * REQS_STATUS, REQS_VACAN_COMPEN,REQS_CLOSE_DATE ";
			 */

			query += "GROUP BY"
					+ " HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,REQS_DATE,RANK_NAME,A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME,"
					+ " B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME ,C.EMP_FNAME||' '||C.EMP_MNAME||' '||C.EMP_LNAME,REQS_APPROVAL_STATUS, REQS_STATUS,"
					+ " REQS_VACAN_COMPEN,REQS_CLOSE_DATE";

			if (bean.getAdvVacOpt().equals("IE")) {
				query += " HAVING SUM(VACAN_NUMBERS) =" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("LT")) {
				query += " HAVING SUM(VACAN_NUMBERS) <" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("GT")) {
				query += " HAVING SUM(VACAN_NUMBERS) >" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("LE")) {
				query += " HAVING SUM(VACAN_NUMBERS) <=" + bean.getAdvVacVal();
			}

			if (bean.getAdvVacOpt().equals("GE")) {
				query += " HAVING SUM(VACAN_NUMBERS) >=" + bean.getAdvVacVal();
			}

			query += " order by ";
			if (!(bean.getFirstSort().equals("1"))) {
				if (bean.getFirstSort().equals("RN")) {
					if (bean.getHidFirstAsc().equals("A")) {
						query += "REQS_NAME ASC";
					} else {
						query += "REQS_NAME DESC";
					}
				} else if (bean.getFirstSort().equals("PO")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidPosition().equals("Y")) {
							query += "RANK_NAME ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidPosition().equals("Y")) {
							query += "RANK_NAME DESC";
						} else {
							query += "''";
						}

					}

				} else if (bean.getFirstSort().equals("RS")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidReqsStatus().equals("Y")) {
							query += "REQS_STATUS ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidPosition().equals("Y")) {
							query += "REQS_STATUS DESC";
						} else {
							query += "''";
						}

					}

				} else if (bean.getFirstSort().equals("AS")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidApprvStatus().equals("Y")) {
							query += "REQS_APPROVAL_STATUS ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidApprvStatus().equals("Y")) {
							query += "REQS_APPROVAL_STATUS DESC";
						} else {
							query += "''";
						}

					}

				} else if (bean.getFirstSort().equals("RD")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidReqsnDate().equals("Y")) {
							query += "REQS_DATE ASC";
						} else {
							query += "''";
						}
					} else {

						if (bean.getHidReqsnDate().equals("Y")) {
							query += "REQS_DATE DESC";
						} else {
							query += "''";
						}
					}
				} else if (bean.getFirstSort().equals("NV")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidNoOfVac().equals("Y")) {
							query += " SUM(VACAN_NUMBERS) ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidNoOfVac().equals("Y")) {
							query += "SUM(VACAN_NUMBERS) DESC";
						} else {
							query += "''";
						}

					}
				} else if (bean.getFirstSort().equals("RB")) {
					if (bean.getHidFirstAsc().equals("A")) {
						if (bean.getHidReqByDate().equals("Y")) {
							query += " TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
						} else {
							query += "''";
						}
					} else {
						if (bean.getHidReqByDate().equals("Y")) {
							query += "TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
						} else {
							query += "''";
						}

					}
				}
			}

			if (!(bean.getSecSortBy().equals("1"))) {

				if (bean.getSecSortBy().equals("RN")) {
					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							query += ",REQS_NAME ASC";
						} else {
							query += ",REQS_NAME DESC";
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							query += "REQS_NAME ASC";
						} else {
							query += "REQS_NAME DESC";
						}
					}
				} else if (bean.getSecSortBy().equals("RD")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {
								query += ",REQS_DATE ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {
								query += "REQS_DATE ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidReqsnDate().equals("Y")) {
								query += "REQS_DATE DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("PO")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {
								query += ",RANK_NAME ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {
								query += "RANK_NAME ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidPosition().equals("Y")) {
								query += "RANK_NAME DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("RS")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {
								query += ",REQS_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {
								query += "REQS_STATUS ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidReqsStatus().equals("Y")) {
								query += "REQS_STATUS DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("AS")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {
								query += ",REQS_APPROVAL_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {
								query += "REQS_APPROVAL_STATUS ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidApprvStatus().equals("Y")) {
								query += "REQS_APPROVAL_STATUS DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("NV")) {
					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += "SUM(VACAN_NUMBERS) ASC";
							} else {
								query += "''";
							}

						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += "SUM(VACAN_NUMBERS) DESC";
							} else {
								query += "''";
							}

						}
					}
				} else if (bean.getSecSortBy().equals("RB")) {

					if (!(bean.getFirstSort().equals("1"))) {
						if (bean.getHidSecAsc().equals("A")) {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += ",''";
							}
						} else {

							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",'' ";
							}
							// query+=",VACAN_REQ_DATE DESC";
						}
					} else {
						if (bean.getHidSecAsc().equals("A")) {

							if (bean.getHidReqByDate().equals("Y")) {
								query += "TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += "''";
							}
						} else {

							if (bean.getHidReqByDate().equals("Y")) {
								query += "TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",''";
							}

						}
					}
				}
			}

			if (!(bean.getThirdSort().equals("1"))) {
				logger.info("in third sorting" + bean.getThirdSort());
				if (bean.getThirdSort().equals("RN")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {

						if (bean.getHidThirdAsc().equals("A")) {
							query += ",REQS_NAME ASC";
						} else {
							query += ",REQS_NAME DESC";
						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							query += "REQS_NAME ASC";
						} else {
							query += "REQS_NAME DESC";
						}
					}
				} else if (bean.getThirdSort().equals("RD")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsnDate().equals("Y")) {

								query += ",REQS_DATE DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("PO")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidPosition().equals("Y")) {

								query += ",RANK_NAME DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("RS")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqsStatus().equals("Y")) {

								query += ",REQS_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("AS")) {
					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS DESC";
							} else {
								query += ",''";
							}

						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidApprvStatus().equals("Y")) {

								query += ",REQS_APPROVAL_STATUS DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("NV")) {

					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) DESC";
							} else {
								query += ",''";
							}

						}
					} else {

						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidNoOfVac().equals("Y")) {
								query += ",SUM(VACAN_NUMBERS) DESC";
							} else {
								query += ",''";
							}
						}
					}
				} else if (bean.getThirdSort().equals("RB")) {

					if ((!bean.getFirstSort().equals("1"))
							|| (!bean.getSecSortBy().equals("1"))) {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",''";
							}
						}
					} else {
						if (bean.getHidThirdAsc().equals("A")) {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') ASC";
							} else {
								query += ",''";
							}
						} else {
							if (bean.getHidReqByDate().equals("Y")) {
								query += ",TO_CHAR(MAX(TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY')),'DD-MM-YYYY') DESC";
							} else {
								query += ",''";
							}
						}
					}
				}
			}

			if (bean.getFirstSort().equals("1")
					&& bean.getSecSortBy().equals("1")

					&& bean.getThirdSort().equals("1")) {

				if (bean.getHidReqsnDate().equals("Y"))
					query += "reqs_date desc";
				else
					query += "''";
			}

			Object[][] reqsns = getSqlModel().getSingleResult(query);

			if (reqsns != null && reqsns.length > 0) {
				bean.setTotRecord(String.valueOf(reqsns.length));
				bean.setNoData("true");
				ArrayList<Object> obj = new ArrayList<Object>();
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						reqsns.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					ManpwrReqsnAnalysis bean1 = new ManpwrReqsnAnalysis();
					int v = 0, n = 0;
					bean1.setItReqCode(String.valueOf(reqsns[i][0]));
					bean1.setItReqName(String.valueOf(reqsns[i][1]));
					if (bean.getHidReqsnDate().equals("Y")) {
						bean.setHdrReqFlg("true");
						bean1.setItrReqFlag("true");
						bean1.setItReqDate(String.valueOf(reqsns[i][2]));
					} else {
						bean.setHdrReqFlg("false");
						bean1.setItrReqFlag("false");
					}

					if (bean.getHidPosition().equals("Y")) {
						bean.setHdrPosFlag("true");
						bean1.setItrPosFlag("true");
						bean1.setItPosition(String.valueOf(reqsns[i][3]));
					} else {
						bean.setHdrPosFlag("false");
						bean1.setItrPosFlag("false");
					}

					if (bean.getHidApprvStatus().equals("Y")) {
						bean.setHdrApprvStatFlag("true");
						bean1.setItrApprvStatFlag("true");
						bean1.setIrtApprvStatus(String.valueOf(reqsns[i][4]));
					} else {
						bean.setHdrApprvStatFlag("false");
						bean1.setItrApprvStatFlag("false");
					}

					if (bean.getHidReqsStatus().equals("Y")) {
						bean.setHdrReqsStatFlag("true");
						bean1.setItrReqsStatFlag("true");
						bean1.setItrReqsStatus(String.valueOf(reqsns[i][5]));
					} else {
						bean.setHdrReqsStatFlag("false");
						bean1.setItrReqsStatFlag("false");
					}

					if (bean.getHidTotalCtc().equals("Y")) {
						bean.setHdrTotalCtcFlag("true");
						bean1.setItrTotalCtcFlag("true");
						bean1.setTotalCtc(checkNull(String
								.valueOf(reqsns[i][6])));
					} else {
						bean.setHdrTotalCtcFlag("false");
						bean1.setItrTotalCtcFlag("false");
					}

					if (bean.getHidNoOfVac().equals("Y")) {
						bean.setHdrNoOfVacFlg("true");
						bean1.setItrNoOfVacFlg("true");
						bean1.setItNoOfVac(String.valueOf(reqsns[i][7]));
					} else {
						bean.setHdrNoOfVacFlg("false");
						bean1.setItrNoOfVacFlg("false");
					}

					if (bean.getHidReqByDate().equals("Y")) {
						bean.setHdrReqByDt("true");
						bean1.setItrReqByFlgDt("true");
						bean1.setItReqByDate(checkNull(String
								.valueOf(reqsns[i][8])));
					} else {
						bean.setHdrReqByDt("false");
						bean1.setItrReqByFlgDt("false");
					}

					if (bean.getHidClosedDate().equals("Y")) {
						bean.setHdrClosedDateFlag("true");
						bean1.setItrClosedDateFlag("true");
						bean1.setClosedDate(checkNull(String
								.valueOf(reqsns[i][9])));
					} else {
						bean.setHdrClosedDateFlag("false");
						bean1.setItrClosedDateFlag("false");
					}

					if (bean.getHidOverDue().equals("Y")) {
						bean.setHdrOvrFlg("true");
						bean1.setItrOvrcFlg("true");
						// if(String.valueOf(reqsns[i][9]).equals("") ||
						// String.valueOf(reqsns[i][9]).equals("null"))
						bean1.setItOvrDue(checkNull(String
								.valueOf(reqsns[i][10])));
						// else
						// bean1.setItOvrDue(checkNull(String.valueOf(reqsns[i][11])));

					} else {
						bean.setHdrOvrFlg("false");
						bean1.setItrOvrcFlg("false");
					}
					if (bean.getHidHiringMngr().equals("Y")) {
						bean.setHdrHrngMngrFlag("true");
						bean1.setItrHrngMngrFlag("true");
						bean1.setItHrngMngr(checkNull(String
								.valueOf(reqsns[i][11])));
					} else {
						bean.setHdrHrngMngrFlag("false");
						bean1.setItrHrngMngrFlag("false");
					}
					if (bean.getHidApprvrName().equals("Y")) {
						bean.setHdrApprvrNameFlag("true");
						bean1.setItrApprvrNameFlag("true");
						bean1.setItApprvrName(checkNull(String
								.valueOf(reqsns[i][12])));
					} else {
						bean.setHdrApprvrNameFlag("false");
						bean1.setItrApprvrNameFlag("false");
					}
					if (bean.getHidRecruitName().equals("Y")) {
						bean.setHdrRecruitNameFlag("true");
						bean1.setItrRecruitNameFlag("true");
						bean1.setItRecruitName(checkNull(String
								.valueOf(reqsns[i][13])));
					} else {
						bean.setHdrRecruitNameFlag("false");
						bean1.setItrRecruitNameFlag("false");
					}

					obj.add(bean1);
				}// End of for loop
				bean.setList(obj);

			}// End of if condition
			else {
				bean.setNoData("false");
				if (bean.getHidVacStatus().equals("Y"))
					bean.setHdrVacStatFlg("true");

				if (bean.getHidOpenVac().equals("Y"))
					bean.setHdrOpenVac("true");

				if (bean.getHidOverDue().equals("Y"))
					bean.setHdrOvrFlg("true");

				if (bean.getHidReqByDate().equals("Y"))
					bean.setHdrReqByDt("true");

				if (bean.getHidNoOfVac().equals("Y"))
					bean.setHdrNoOfVacFlg("true");

				if (bean.getHidApprvStatus().equals("Y"))
					bean.setHdrApprvStatFlag("true");

				if (bean.getHidReqsStatus().equals("Y"))
					bean.setHdrReqsStatFlag("true");

				if (bean.getHidPosition().equals("Y"))
					bean.setHdrPosFlag("true");

				if (bean.getHidReqsnDate().equals("Y"))
					bean.setHdrReqFlg("true");

				if (bean.getHidTotalCtc().equals("Y"))
					bean.setHdrTotalCtcFlag("true");

				if (bean.getHidClosedDate().equals("Y"))
					bean.setHdrClosedDateFlag("true");

				if (bean.getHidHiringMngr().equals("Y"))
					bean.setHdrHrngMngrFlag("true");

				if (bean.getHidApprvrName().equals("Y"))
					bean.setHdrApprvrNameFlag("true");

				if (bean.getHidRecruitName().equals("Y"))
					bean.setHdrRecruitNameFlag("true");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to check the duplicate entry of the setting
	 * name
	 * 
	 * @param bean
	 * @return
	 */

	public Object[][] chkUser(ManpwrReqsnAnalysis bean) {
		String query = "SELECT MPRREP_USER_NAME,MPRREP_CODE FROM HRMS_REC_MPRREP_FILTERS WHERE UPPER(MPRREP_USER_NAME) = '"
				+ bean.getSettingName().toUpperCase()
				+ "'"
				+ " AND MPRREP_USER_ID =(" + bean.getUserEmpId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		return data;

	}

	public Object[][] chkUserInUpdate(ManpwrReqsnAnalysis bean) {
		String query = "SELECT MPRREP_USER_NAME FROM HRMS_REC_MPRREP_FILTERS "
				+ "WHERE UPPER(MPRREP_USER_NAME)='"
				+ bean.getSettingName().toUpperCase() + "'"
				+ " AND MPRREP_CODE !=(" + bean.getMraRepCode() + ")";

		Object[][] data = getSqlModel().getSingleResult(query);
		return data;

	}

}
