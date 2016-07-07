package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.paradyne.bean.Recruitment.CandidateStatusReport;
import org.paradyne.bean.Recruitment.ManpwrReqsnAnalysis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

import javax.servlet.http.*;

public class CandidateStatusModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandidateStatusModel.class);

	/**
	 * following function is called to check the duplicate entry of the 
	 * setting name by the corresponding user
	 * @param bean
	 * @return
	 */

	public Object[][] chkUser(CandidateStatusReport bean) {
		String query = "SELECT APPREP_NAME,APPREP_CODE FROM HRMS_REC_APPREP_FILTERS WHERE UPPER(APPREP_NAME) = '"
				+ bean.getSaveSetting().toUpperCase()
				+ "'"
				+ " AND APPREP_USEREMPID =(" + bean.getUserEmpId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		return data;

	}

	/**
	 * following function is called to display the setting names saved by the corresponding user
	 * @param bean
	 */
	public void callSavedLIst(CandidateStatusReport bean) {
		String quer = "SELECT APPREP_CODE,APPREP_NAME FROM HRMS_REC_APPREP_FILTERS WHERE APPREP_USEREMPID="
				+ bean.getUserEmpId();
		Object[][] iterator = getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		for (int i = 0; i < iterator.length; i++) {
			mp.put(String.valueOf(iterator[i][0]), String
					.valueOf(iterator[i][1]));

		}
		bean.setHashMap(mp);
	}

	/**
	 * following function is called to sort the records depending on the Sorting value.
	 * @param status
	 * @return
	 */
	public String getSortingValue(String status) {
		String sql = "";

		if (status.equals("RN")) {
			sql = " NVL(REQS_NAME,' ') ";
		}

		if (status.equals("P ")) {
			sql = " NVL(RANK_NAME,' ') ";
		}

		if (status.equals("CN")) {
			sql = " CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME  ";
		}

		return sql;

	}

	/**
	 * following function is called for sort the records in Ascending order or in Descending
	 * @param Status
	 * @return
	 */
	public String getSortOrder(String Status) {
		String sql = "";
		if (Status.equals("A")) {
			sql = " ASC ";
		}
		if (Status.equals("D")) {
			sql = " DESC ";
		}
		return sql;
	}

	/**
	 * following function is called to return the no. of selected 
	 * columns in the Column definition 
	 * @param bean
	 * @return
	 */
	public int getNoOfCol(CandidateStatusReport bean) {
		int counter = 0;
		if (bean.getChkEmail().equals("Y")) {

			counter++;

		}

		if (bean.getChkCandScrnStat().equals("Y")) {

			counter++;
		}
		 
		 
		if (bean.getChkOffStat().equals("Y")) {

			counter++;
		}

		if (bean.getChkAppntStat().equals("Y")) {

			counter++;
		}

		if (bean.getChkConEmp().equals("Y")) {
			counter++;
		}

		if (bean.getChkMobileNo().equals("Y")) {

			counter++;
		}
		if (bean.getChkLastComp().equals("Y")) {

			counter++;
		}
		if (bean.getChkcurrCtc().equals("Y")) {

			counter++;
		}
		if (bean.getChkCurrLoc().equals("Y")) {

			counter++;
		}
		if (bean.getChkExp().equals("Y")) {

			counter++;
		}
		return counter;

	}

	/**
	 * following function is called to display the no. of interviews attended by
	 * the candidate.
	 * @return
	 */
	public int getIntrvRnd(String reqsCode, String candCode) {
		int kk = 0;
		try {

			String query = "select count(*) from HRMS_REC_CANDEVAL where  EVAL_REQS_CODE="
					+ reqsCode
					+ " and EVAL_CAND_CODE="
					+ candCode
					+ " group by EVAL_CAND_CODE ";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				if (!(String.valueOf(data[0][0]).equals("null") || String
						.valueOf(data[0][0]).equals("")))
					kk = Integer.parseInt(String.valueOf(data[0][0]));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kk;

	}

	/**
	 * following function is called to return the candidate details.
	 * And this function is called in the getReportForAll.
	 * @param reqCode
	 * @param bean
	 * @return
	 */

	public Object[][] getCandidateDetails(String reqCode,
			CandidateStatusReport bean) throws Exception {

		/*String query="SELECT DISTINCT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ," 
					 +" CAND_EMAIL_ID,DECODE(RESUME_STATUS,'R','Rejected','T','Short List For Test','I','Short List For Interview','B','Short List For Test or Interview'),DECODE(TEST_RESULT,'P','Pass','F','Fail','','Nil') ," 
					 +" DECODE(OFFER_STATUS,'D','Due','S','Send For Approval','I','Issued','OA','Accepted','OR','Rejected','C','Canceled','','N/A'), " 
					 +" DECODE(APPOINT_STATUS,'D ','Due','S ','Send For Approval','I ','Issued','OA','Accepted','OR','Rejected','C ','Canceled',' ','DDD','','N/A') , " 
					 +" DECODE(HRMS_REC_APPOINT.APPOINT_CONVERT_EMP,'Y','Yes','N','No','','No','No'), " 
					 +" HRMS_REC_CAND_DATABANK.CAND_CODE,HRMS_REC_REQS_HDR.REQS_CODE " 
					 +" FROM HRMS_REC_RESUME_BANK " 
					 +" LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE =HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) " 
					 +" LEFT JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE) " 
					 +" LEFT JOIN  HRMS_REC_SCHTEST_DTL ON(HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE " 
					 +" AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) " 
					 + "LEFT JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) " 
					 +" LEFT JOIN HRMS_REC_TESTRESULT ON(HRMS_REC_TESTRESULT.TEST_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE " 
					 +" AND HRMS_REC_TESTRESULT.TEST_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) " 
					 +" LEFT JOIN HRMS_REC_OFFER ON(HRMS_REC_OFFER.OFFER_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE " 
					 +" AND HRMS_REC_OFFER.OFFER_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) " 
					 +" LEFT JOIN HRMS_REC_APPOINT ON(HRMS_REC_APPOINT.APPOINT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE  AND " 
					 +" HRMS_REC_APPOINT.APPOINT_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					 +" WHERE 1=1 ";
		 */

		/*String query = "SELECT DISTINCT REQS_NAME,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ,"
				+ " CAND_EMAIL_ID,CAND_MOB_PHONE,DECODE(RESUME_STATUS,'R','Rejected','T','Short List For Test','I','Short List For Interview','B','Short List For Test or Interview'),DECODE(TEST_RESULT,'P','Pass','F','Fail','','Nil') ,"
				+ " DECODE(OFFER_STATUS,'D','Due','S','Send For Approval','I','Issued','OA','Accepted','OR','Rejected','C','Canceled','','N/A'), "
				+ " DECODE(APPOINT_STATUS,'D ','Due','S ','Send For Approval','I ','Issued','OA','Accepted','OR','Rejected','C ','Canceled',' ','DDD','','N/A') , "
				+ " DECODE(HRMS_REC_APPOINT.APPOINT_CONVERT_EMP,'Y','Yes','N','No','','No','No'), "
				+ " HRMS_REC_CAND_DATABANK.CAND_CODE,HRMS_REC_REQS_HDR.REQS_CODE "
				+ " ,NVL(RANK_NAME,' ') FROM HRMS_REC_CAND_DATABANK "
				+ " LEFT JOIN HRMS_REC_RESUME_BANK ON (HRMS_REC_RESUME_BANK.RESUME_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE ) "
				+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_CAND_DATABANK.CAND_POSTRESUME_REQUISITIONID) "
				+ " LEFT JOIN  HRMS_REC_SCHTEST_DTL ON(HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE "
				+ " AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) "
				+ "LEFT JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
				+ " LEFT JOIN HRMS_REC_TESTRESULT ON(HRMS_REC_TESTRESULT.TEST_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
				+ " AND HRMS_REC_TESTRESULT.TEST_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
				+ " LEFT JOIN HRMS_REC_OFFER ON(HRMS_REC_OFFER.OFFER_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
				+ " AND HRMS_REC_OFFER.OFFER_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
				+ " LEFT JOIN HRMS_REC_APPOINT ON(HRMS_REC_APPOINT.APPOINT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE  AND "
				+ " HRMS_REC_APPOINT.APPOINT_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
				+ " WHERE 1=1 ";*/

		String query = " select distinct REQS_NAME ,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME name "
				+ " ,CAND_EMAIL_ID,CAND_MOB_PHONE "
				+ " ,DECODE(RESUME_STATUS,'R','Rejected','T','Short List For Test','I','Short List For Interview','B','Short List For Test or Interview') resume_status "
				+ ", DECODE(OFFER_STATUS,'D','Due','S','Send For Approval','I','Issued','OA','Accepted','OR','Rejected','C','Canceled','','N/A') "
				+ ",DECODE(APPOINT_STATUS,'D ','Due','S ','Send For Approval','I ','Issued','OA','Accepted','OR','Rejected','C ','Canceled',' ','DDD','','N/A') as appoint_status "
				+" ,DECODE(HRMS_REC_APPOINT.APPOINT_CONVERT_EMP,'Y','Yes','N','No','','No','No') "
				+ ",POST_CAND_CODE,POST_REQS_CODE,NVL(RANK_NAME,' '),nvl(HRMS_REC_CAND_DATABANK.CAND_COMPANY,' '), "
				+ " HRMS_REC_CAND_DATABANK.CAND_CURR_CTC,nvl(HRMS_REC_CAND_DATABANK.CAND_LOCATION,' '), " 
				+ " HRMS_REC_CAND_DATABANK.CAND_EXP_YEAR  ||'Years  '||  "   
				+ " HRMS_REC_CAND_DATABANK.CAND_EXP_MONTH ||' Months' "
				+ " from  HRMS_REC_CAND_POSTING "
				+ " INNER JOIN  HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE =  HRMS_REC_CAND_POSTING.POST_REQS_CODE)  "
				+ " LEFT JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_CAND_POSTING.POST_CAND_CODE ) "
				+ " LEFT JOIN HRMS_REC_RESUME_BANK ON (HRMS_REC_RESUME_BANK.RESUME_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE ) "
				+ " LEFT JOIN HRMS_REC_APPOINT ON(HRMS_REC_APPOINT.APPOINT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE  AND  HRMS_REC_APPOINT.APPOINT_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)  "
				+ "LEFT JOIN HRMS_REC_OFFER ON(HRMS_REC_OFFER.OFFER_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE  AND HRMS_REC_OFFER.OFFER_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
				+ "LEFT JOIN  HRMS_REC_SCHTEST_DTL ON(HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE  AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) "
				+ "LEFT JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)  "
				 +"  WHERE 1=1";

		if (!(bean.getReqCode() == null) && !bean.getReqCode().equals("")) {
			query += " AND HRMS_REC_CAND_POSTING.POST_REQS_CODE ='"
					+ bean.getReqCode().trim() + "'";
		}

		if (!(bean.getCandidateCode() == null)
				&& !bean.getCandidateCode().equals("")) {
			query += " AND HRMS_REC_CAND_POSTING.POST_CAND_CODE='"
					+ bean.getCandidateCode().trim() + "'";
		}

		if (!(bean.getRankId() == null) && !bean.getRankId().equals("")) {
			query += " AND HRMS_RANK.RANK_ID ='" + bean.getRankId().trim()
					+ "'";
		}

		if (!(bean.getAdvScrn().equals(""))) {
			query += " AND RESUME_STATUS=" + "'" + bean.getAdvScrn().trim()
					+ "'";
		}

		if (!(bean.getAdvOffStat().equals("") || bean.getAdvOffStat().equals(
				"null"))) {
			query += " AND OFFER_STATUS=" + "'" + bean.getAdvOffStat().trim()
					+ "'";
		}

		if (!(bean.getAdvAppntStat().equals("") || bean.getAdvAppntStat()
				.equals("null"))) {
			query += " AND APPOINT_STATUS=" + "'"
					+ bean.getAdvAppntStat().trim() + "'";
		}
		
		
		if (!(bean.getReqsStatus().equals(""))) {
			query += " AND REQS_STATUS='" + bean.getReqsStatus() + "'";
		}

		
		if (bean.getReqsDateCombo().trim().equals("O")) {
			// search those cands whose resume positioning date is equal to
			// fromDate
			query += " AND CAND_POSTING_DATE = TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("B")) {
			// search those cands whose resume positioning date before fromDate
			query += " AND CAND_POSTING_DATE < TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("A")) {
			// search those cands whose resume positioning date after fromDate
			query += " AND CAND_POSTING_DATE > TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("OB")) {
			// search those cands whose resume positioning date on or before
			// fromDate
			query += " AND CAND_POSTING_DATE <= TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("OA")) {
			// search those cands whose resume positioning date on or after
			// fromDate
			query += " AND CAND_POSTING_DATE >= TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("F")) {
			// search those cands whose resume positioning date between fromDate
			// and toDate
			query += " AND (CAND_POSTING_DATE BETWEEN TO_DATE('"
					+ bean.getFrmDate() + "', 'DD-MM-YYYY') ";
			if (!bean.getToDate().trim().equals("")) { // bean.getToDate()!=null &&
				query += "AND TO_DATE('" + bean.getToDate()
						+ "', 'DD-MM-YYYY')) ";
			} else {
				query += "AND TO_DATE(to_char(sysdate,'dd-mm-yyyy'),'DD-MM-YYYY')) ";
			}
		}
	/*	hhhhhhhhhhh
		
		if (bean.getReqsDateCombo().equals("O ")) {
			if (!(bean.getFrmDate() == null)
					&& !bean.getFrmDate().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE(" + "'"
						+ bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
			}
		}

		if (bean.getReqsDateCombo().equals("B ")) {
			if (!(bean.getFrmDate() == null)
					&& !bean.getFrmDate().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("
						+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
			}
		}

		if (bean.getReqsDateCombo().equals("A ")) {
			if (!(bean.getFrmDate() == null)
					&& !bean.getFrmDate().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("
						+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
			}
		}

		if (bean.getReqsDateCombo().equals("OB")) {
			if (!(bean.getFrmDate() == null)
					&& !bean.getFrmDate().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
						+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
			}
		}

		if (bean.getReqsDateCombo().equals("OA")) {
			if (!(bean.getFrmDate() == null)
					&& !bean.getFrmDate().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
						+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
			}
		}*/

		/*if (bean.getReqsDateCombo().equals("F ")) {
			if (!(bean.getFrmDate() == null)
					&& !bean.getFrmDate().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
						+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
			}

			if (!(bean.getToDate() == null) && !bean.getToDate().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
						+ "'" + bean.getToDate() + "'" + ",'DD-MM-YYYY')";
			}

		}*/


		if (!bean.getSortBy().trim().equals("1")
				|| !bean.getFirstBy().trim().equals("1")
				|| !bean.getSecondBy().trim().equals("1")) {
			query += " ORDER BY ";
		}

		if (!bean.getSortBy().trim().equals("1")) {

			query += getSortingValue(bean.getSortBy());//

			if (bean.getAsc1().trim().equals("A")) {
				query += " " + getSortOrder("A");
			} else {
				query += " " + getSortOrder("D");
			}

			if (!bean.getFirstBy().trim().equals("1")
					|| !bean.getSecondBy().trim().equals("1")) {
				query += " , ";
			}

		}

		if (!bean.getFirstBy().trim().equals("1")) {
			query += getSortingValue(bean.getFirstBy());// +" "+getSortOrder(bean.getThenByOrder1()); 
			if (bean.getAsce1().trim().equals("A")) {
				query += " " + getSortOrder("A");
			} else {
				query += " " + getSortOrder("D");
			}

			if (!bean.getSecondBy().trim().equals("1")) {
					query += " , ";
			}

		}

		if (!bean.getSecondBy().trim().equals("1")) {
			query += getSortingValue(bean.getSecondBy());//+" "+getSortOrder(bean.getThenByOrder2());
			if (bean.getAscc1().trim().equals("A")) {
				query += " " + getSortOrder("A");
			} else {
				query += " " + getSortOrder("D");
			}

		}

		Object[][] candidate = getSqlModel().getSingleResult(query);
		int counter = getNoOfCol(bean);
		
		System.out.println("Counter---->"+counter);
		Object[][] candData = new Object[candidate.length][counter + 3];
		Object[][] Data = null;
		if (candidate != null && candidate.length > 0) {

			if (bean.getExportAllData().equals("Y")) {
				int cnt = 0;
				int m = 1;
				for (int i = 0; i < candidate.length; i++) {

					candData[i][cnt] = m++;//Serial No.
					cnt++;
					candData[i][cnt] = candidate[i][0];//Requisition Name
					cnt++;

					candData[i][cnt] = candidate[i][1];//Candidate Name
					cnt++;

					if (bean.getChkEmail().equals("Y")) {
						candData[i][cnt] = candidate[i][2];//Email id
						cnt++;
					}

					if (bean.getChkMobileNo().equals("Y")) {
						candData[i][cnt] = candidate[i][3];//Mobile No
						cnt++;
					}

					if (bean.getChkCandScrnStat().equals("Y")) {
						candData[i][cnt] = candidate[i][4];//Screening Status
						cnt++;
					}
					 
				 
					if (bean.getChkOffStat().equals("Y")) {
						candData[i][cnt] = candidate[i][5];//offer status
						cnt++;
					}

					if (bean.getChkAppntStat().equals("Y")) {
						candData[i][cnt] = candidate[i][6];
						cnt++;
					}

					if (bean.getChkConEmp().equals("Y")) {
						candData[i][cnt] = candidate[i][7];
						cnt++;
					}
					
					if (bean.getChkLastComp().equals("Y")) {
						candData[i][cnt] = candidate[i][11];
						cnt++;
					}
					
					if (bean.getChkcurrCtc().equals("Y")) {
						candData[i][cnt] = candidate[i][12];
						cnt++;
					}
					
					if (bean.getChkCurrLoc().equals("Y")) {
						candData[i][cnt] = candidate[i][13];
						cnt++;
					}
					
					
					if (bean.getChkExp().equals("Y")) {
						candData[i][cnt] = candidate[i][14];
						cnt++;
					}

					cnt = 0;

				}//End of for loop		  
			}//End of If cond for export all data
			else {
				String[] pageIndex = Utility.doPaging(bean.getMyCandPage(),
						candidate.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				int numOfRec = Integer.parseInt(pageIndex[1])
						- Integer.parseInt(pageIndex[0]);
				Data = new Object[numOfRec][counter + 2];
				int cntr = 0;
				int k = 0;
				int j = 1;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					Data[k][cntr] = j++;//Serial No.
					cntr++;
					Data[k][cntr] = candidate[k][0];//Candidate Name
					cntr++;

					if (bean.getChkEmail().equals("Y")) {
						Data[k][cntr] = candidate[k][1];//Email id
						cntr++;
					}

					if (bean.getChkCandScrnStat().equals("Y")) {
						Data[k][cntr] = candidate[k][2];//Screening Status
						cntr++;
					}
					/* 
					if (bean.getChkIntRnd().equals("Y")) {
						Data[k][cntr] = getIntrvRnd(reqCode, String
								.valueOf(candidate[k][7]));
						cntr++;
					}*/
					if (bean.getChkOffStat().equals("Y")) {
						Data[k][cntr] = candidate[k][4];
						cntr++;
					}

					if (bean.getChkAppntStat().equals("Y")) {
						Data[k][cntr] = candidate[k][5];
						cntr++;
					}

					if (bean.getChkConEmp().equals("Y")) {
						Data[k][cntr] = candidate[k][6];
					}
					
					
					
					if (bean.getChkLastComp().equals("Y")) {
						Data[k][cntr] = candidate[k][10];
					}
					if (bean.getChkcurrCtc().equals("Y")) {
						Data[k][cntr] = candidate[k][11];
					}
					
					if (bean.getChkCurrLoc().equals("Y")) {
						Data[k][cntr] = candidate[k][12];
					}
					
					if (bean.getChkExp().equals("Y")) {
						Data[k][cntr] = candidate[k][13];
					}
					k++;
					cntr = 0;

				}//End of for loop

			}//End of else condition

		}
		if (bean.getExportAllData().equals("Y"))
			return candData;
		else
			return Data;
	}

	/**Added by Pradeep on Date:16-07-2009
	 * following function is called to generate the report when Generate Report button is clicked .
	 * This report will display all the requisition header names and the corresponding employee details
	 * regarding their interview details,offer details etc.
	 *  * @param bean
	 */
	public void getReportForAll(CandidateStatusReport bean,
			HttpServletResponse response) {
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean
				.getFlag(), "Candidate Status Report","A4");

		try {
			int counter = getNoOfCol(bean);
			String[] headers = new String[counter + 3];
			int count = 0;
			int cellwidth[] = new int[counter + 3];
			int[] alignment = new int[counter + 3];

			headers[count] = "Sr.No";
			alignment[count] = 1;
			cellwidth[count] = 6;
			count++;

			alignment[count] = 0;
			headers[count] = "Requisition Name";
			cellwidth[count] = 15;
			count++;

			alignment[count] = 0;
			headers[count] = "Candidate Name";
			cellwidth[count] = 15;
			count++;

			if (bean.getChkEmail().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 15;
				headers[count] = "Email Id";
				count++;

			}

			System.out.println("bean.getChkMobileNo()  "
					+ bean.getChkMobileNo());

			if (bean.getChkMobileNo().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 15;
				headers[count] = "Mobile No";
				count++;

			}

			logger.info("Offer Status" + bean.getOfferstatus());

			if (bean.getChkCandScrnStat().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 11;
				headers[count] = "Candidate Screening Status";
				count++;

			}
			/*if (bean.getChkTestRes().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 8;
				headers[count] = "Test Result";
				count++;

			}*/

		/*	if (bean.getChkIntRnd().equals("Y")) {
				alignment[count] = 2;
				cellwidth[count] = 10;
				headers[count] = "Interview Round";
				count++;

			}*/

			if (bean.getChkOffStat().equals("Y")) {

				alignment[count] = 0;
				cellwidth[count] = 8;
				headers[count] = "Offer Status";
				count++;
			}

			if (bean.getChkAppntStat().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 13;
				headers[count] = "Appointment Status";
				count++;
			}

			if (bean.getChkConEmp().equals("Y")) {
				alignment[count] = 1;
				cellwidth[count] = 12;
				headers[count] = "Convert to Employee";
				count++;
			}
			
			
			if (bean.getChkLastComp().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 12;
				headers[count] = "Last Company Name";
				count++;
			}
			
			if (bean.getChkcurrCtc().equals("Y")) {
				alignment[count] = 2;
				cellwidth[count] = 12;
				headers[count] = "Current CTC (In Lacs)";
				count++;
			}
			
			if (bean.getChkCurrLoc().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 12;
				headers[count] = "Current Location";
				count++;
			}
			
			
			if (bean.getChkExp().equals("Y")) {
				alignment[count] = 0;
				cellwidth[count] = 12;
				headers[count] = "Experience";
				count++;
			}
			
			count = 0;
			if (bean.getFlag().equals("Xls")) {
				rg.addText("Candidate Status Report :", 0, 1, 0);
				rg.setFName("Candidate Status Report.xls");
			} else if (bean.getFlag().equals("Pdf")) {
				rg.setFName("Candidate Status Report");
				rg.addFormatedText("Candidate Status Report :", 6, 0, 1, 1);
				rg.addText("\n\n", 0, 0, 0);
			} else {
				rg.setFName("Candidate Status Report.doc");
				rg.addText("Candidate Status Report :", 0, 1, 0);

			}

			Object[][] candidateData = getCandidateDetails(null, bean);

			if (candidateData != null && candidateData.length > 0) {
				//rg.addText("Candidate List :",0,0,0);
				rg.tableBody(headers, candidateData, cellwidth, alignment);
				if (bean.getFlag().equals("Pdf")
						|| bean.getFlag().equals("Xls"))
					rg.addText("\n\n\n", 0, 0, 0);
			}//End of candidate if condition
			else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
				if (bean.getFlag().equals("Pdf")
						|| bean.getFlag().equals("Xls"))
					rg.addText("\n\n\n", 0, 0, 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rg.createReport(response);

	}

	/**
	 * following function is called the requisition details when Select Requisition button is clicked.
	 * @param bean
	 */
	public void displayReq(CandidateStatusReport bean) {

		try {
			String query = " SELECT REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' "
					+ " ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE FROM HRMS_REC_REQS_HDR  "
					+ " INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
					+ " WHERE  REQS_APPROVAL_STATUS NOT IN('B','P','H','R') AND 1=1  ";

			if (!bean.getRankId().equals("")) {
				query += " AND REQS_POSITION =" + bean.getRankId();
			}
			if (bean.getReqsDateCombo().equals("O ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE(" + "'"
							+ bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("B ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("A ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("OB")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("OA")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("F ")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

				if (!(bean.getToDate() == null) && !bean.getToDate().equals("")) {
					query += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getToDate() + "'" + ",'DD-MM-YYYY')";
				}

			}

			if (!(bean.getReqsStatus().equals(""))) {
				query += " AND REQS_STATUS='" + bean.getReqsStatus() + "'";
			}

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setNoDataReq("false");
				bean.setDataLength("" + data.length);
				ArrayList<Object> list = new ArrayList<Object>();
				for (int i = 0; i < data.length; i++) {
					CandidateStatusReport bean1 = new CandidateStatusReport();
					bean1.setItReqName("" + data[i][0]);
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
								bean1.setSelectedReqFlag("checked");
								break;
							}
						}
					}
				}
				bean.setDispList(list);
			} else {
				bean.setNoDataReq("true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to insert the 
	 * @param bean
	 * @return
	 */
	public boolean saveRepSettings(CandidateStatusReport bean) {
		Object[][] filtOpt = new Object[1][10];
		Object[][] sortOpt = new Object[1][7];
		Object[][] sortColDef = new Object[1][12];
		Object[][] advFilt = new Object[1][6];
		Object[][] reqsData = new Object[1][2];

		/**
		 * following array is called to insert the filter details record into the 
		 * HRMS_REC_APPREP_FILTERS table.
		 */
		filtOpt[0][0] = bean.getReqCode();
		filtOpt[0][1] = bean.getCandidateCode();
		filtOpt[0][2] = bean.getRankId();
		filtOpt[0][3] = bean.getReqsDateCombo();
		String frmDate = (bean.getFrmDate().equals("") || bean.getFrmDate()
				.equals("dd-mm-yyyy")) ? "" : bean.getFrmDate();
		String toDate = (bean.getToDate().equals("") || bean.getToDate()
				.equals("dd-mm-yyyy")) ? "" : bean.getToDate();

		filtOpt[0][4] = frmDate;
		filtOpt[0][5] = toDate;
		filtOpt[0][6] = bean.getReqsStatus();
		filtOpt[0][7] = bean.getSaveSetting();
		if (bean.getFlag().equals("") || bean.getFlag().equals("null")) {
			filtOpt[0][8] = String.valueOf("");
		} else {
			filtOpt[0][8] = bean.getFlag().substring(0, 1);
		}

		filtOpt[0][9] = bean.getUserEmpId();
		//filtOpt[0][10]=bean.getFlag().substring(0,1);

		for (int i = 0; i < filtOpt.length; i++) {
			for (int j = 0; j < filtOpt[0].length; j++) {
				System.out.println("filtOpt[i][j]  " + filtOpt[i][j]);
			}
		}

		boolean result = getSqlModel().singleExecute(getQuery(1), filtOpt);
		if (result) {
			String query = "SELECT MAX(APPREP_CODE) FROM HRMS_REC_APPREP_FILTERS";
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setAppRepCode(String.valueOf(data[0][0]));

			/**
			 * following array is called to insert the sorting details record into the 
			 * HRMS_REC_APPREP_SORT
			 */

			sortOpt[0][0] = String.valueOf(data[0][0]);
			sortOpt[0][1] = bean.getSortBy();
			if (bean.getAsc1().equals("A")) {
				sortOpt[0][2] = bean.getAsc1();
			} else {
				sortOpt[0][2] = bean.getDesc1();
			}
			sortOpt[0][3] = bean.getFirstBy();
			if (bean.getAsce1().equals("A")) {
				sortOpt[0][4] = bean.getAsce1();
			} else {
				sortOpt[0][4] = bean.getDesce1();
			}
			sortOpt[0][5] = bean.getSecondBy();
			if (bean.getAscc1().equals("A")) {
				sortOpt[0][6] = bean.getAscc1();
			} else {
				sortOpt[0][6] = bean.getDescc1();
			}

			getSqlModel().singleExecute(getQuery(2), sortOpt);

			/**
			 * following array is called to insert the column definition details record into the 
			 * HRMS_REC_APPREP_SORT
			 */

			sortColDef[0][0] = String.valueOf(data[0][0]);
			sortColDef[0][1] = bean.getChkCand();
			sortColDef[0][2] = bean.getChkEmail();
			sortColDef[0][3] = bean.getChkSearchStat();
			sortColDef[0][4] = bean.getChkCandScrnStat();
			sortColDef[0][5] = "";
			sortColDef[0][6] = bean.getChkIntStat();
			sortColDef[0][7] = "";
			sortColDef[0][8] = bean.getChkMkOffStat();
			sortColDef[0][9] = bean.getChkOffStat();
			sortColDef[0][10] = bean.getChkAppntStat();
			sortColDef[0][11] = bean.getChkConEmp();
			getSqlModel().singleExecute(getQuery(3), sortColDef);

			/**
			 * following array is called to insert the advance filter details record into the 
			 * HRMS_REC_APPREP_ADV
			 */
			advFilt[0][0] = String.valueOf(data[0][0]);
			advFilt[0][1] = bean.getAdvScrn();
			advFilt[0][2] = bean.getAdvIntRnd();
			advFilt[0][3] = bean.getIntRndVal();
			advFilt[0][4] = bean.getAdvAppntStat();
			advFilt[0][5] = bean.getAdvOffStat();
			getSqlModel().singleExecute(getQuery(4), advFilt);

			/**
			 * following array is called to insert the selected requisition details record into the 
			 * HRMS_REC_APPREP_REQS
			 */
			if (bean.getSelectedReq().length() > 0
					&& bean.getSelectedReq() != null) {
				String[] str = bean.getSelectedReq().split(",");
				for (int i = 0; i < str.length; i++) {
					reqsData[0][0] = str[i];
					reqsData[0][1] = String.valueOf(data[0][0]);
					getSqlModel().singleExecute(getQuery(5), reqsData);
				}
			}

		}

		return result;

	}

	/**
	 * following function is called to update the filter options,column definitions,advance filters and 
	 * sorting details
	 * @param bean
	 * @return
	 */
	public boolean updateSettings(CandidateStatusReport bean) {
		boolean result = false;

		try {
			Object[][] filtOpt = new Object[1][10];
			Object[][] sortOpt = new Object[1][7];
			Object[][] sortColDef = new Object[1][12];
			Object[][] advFilt = new Object[1][6];
			Object[][] reqsData = new Object[1][2];
			Object[][] del = new Object[1][1];
			del[0][0] = bean.getAppRepCode();

			/**
			 * following array is called to update the filter details record into the 
			 * HRMS_REC_APPREP_FILTERS table.
			 */
			filtOpt[0][0] = bean.getReqCode();
			filtOpt[0][1] = bean.getCandidateCode();
			filtOpt[0][2] = bean.getRankId();
			filtOpt[0][3] = bean.getReqsDateCombo();
			filtOpt[0][4] = (bean.getFrmDate().equals("") || bean.getFrmDate()
					.equals("dd-mm-yyyy")) ? "" : bean.getFrmDate();
			filtOpt[0][5] = (bean.getToDate().equals("") || bean.getToDate()
					.equals("dd-mm-yyyy")) ? "" : bean.getToDate();
			filtOpt[0][6] = bean.getReqsStatus();
			//	filtOpt[0][7]=bean.getFlag();

			if (bean.getFlag().equals("") || bean.getFlag().equals("null")) {
				filtOpt[0][7] = String.valueOf("");
			} else {
				filtOpt[0][7] = bean.getFlag().substring(0, 1);
			}
			filtOpt[0][8] = bean.getUserEmpId();
			filtOpt[0][9] = bean.getAppRepCode();

			result = getSqlModel().singleExecute(getQuery(6), filtOpt);
			if (result) {
				/**
				 * following array is called to update the sorting details record into the 
				 * HRMS_REC_APPREP_Sort table.
				 */
				sortOpt[0][0] = bean.getSortBy();
				logger.info("firsat radio ascending" + bean.getAsc1());

				if (bean.getAsc1().equals("A")) {
					sortOpt[0][1] = String.valueOf("A");

				} else if (bean.getDesc1().equals("D")) {
					sortOpt[0][1] = String.valueOf("D");
				}

				sortOpt[0][2] = bean.getFirstBy();

				if (bean.getAsce1().equals("A")) {
					sortOpt[0][3] = bean.getAsce1();
				} else if (bean.getDesce1().equals("D")) {
					sortOpt[0][3] = bean.getDesce1();
				}
				//sortOpt[0][3]=bean.getAsce1();
				sortOpt[0][4] = bean.getSecondBy();

				if (bean.getAscc1().equals("A")) {
					sortOpt[0][5] = bean.getAscc1();
				} else if (bean.getDescc1().equals("D")) {
					sortOpt[0][5] = bean.getDescc1();
				}
				//sortOpt[0][5]=bean.getAscc1();
				sortOpt[0][6] = bean.getAppRepCode();
				getSqlModel().singleExecute(getQuery(7), sortOpt);

				/**
				 * following array is called to update the column definition details record into the 
				 * HRMS_REC_APPREP_COLDEF
				 */

				sortColDef[0][0] = bean.getChkCand();
				sortColDef[0][1] = bean.getChkEmail();
				sortColDef[0][2] = bean.getChkSearchStat();
				sortColDef[0][3] = bean.getChkCandScrnStat();
				sortColDef[0][4] ="";
				sortColDef[0][5] = bean.getChkIntStat();
				sortColDef[0][6] = "";
				sortColDef[0][7] = bean.getChkMkOffStat();
				sortColDef[0][8] = bean.getChkOffStat();
				sortColDef[0][9] = bean.getChkAppntStat();
				sortColDef[0][10] = bean.getChkConEmp();
				sortColDef[0][11] = bean.getAppRepCode();
				getSqlModel().singleExecute(getQuery(8), sortColDef);

				/**
				 * following array is called to UPDATE the advance filter details record into the 
				 * HRMS_REC_APPREP_ADV
				 */
				advFilt[0][0] = bean.getAdvScrn();
				advFilt[0][1] = bean.getAdvIntRnd();
				advFilt[0][2] = bean.getIntRndVal();
				advFilt[0][3] = bean.getAdvAppntStat();
				advFilt[0][4] = bean.getAdvOffStat();
				advFilt[0][5] = bean.getAppRepCode();
				getSqlModel().singleExecute(getQuery(9), advFilt);

				String query = "DELETE FROM HRMS_REC_APPREP_REQS WHERE APPREP_CODE=? ";
				getSqlModel().singleExecute(query, del);

				/**
				 * following array is called to insert the selected requisition details record into the 
				 * HRMS_REC_APPREP_REQS
				 */
				if (bean.getSelectedReq().length() > 0
						&& bean.getSelectedReq() != null) {
					String[] str = bean.getSelectedReq().split(",");
					for (int i = 0; i < str.length; i++) {
						reqsData[0][0] = str[i];
						reqsData[0][1] = bean.getAppRepCode();
						getSqlModel().singleExecute(getQuery(5), reqsData);
					}
				}

			}//End of if condition
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * following function is called to display the filter option details 
	 * when the search setting drop down is selected. 
	 * @param bean
	 */
	public void getFilterOptions(CandidateStatusReport bean) {
		Object[] code = new Object[1];
		code[0] = bean.getAppRepCode();

		Object[][] filters = getSqlModel().getSingleResult(getQuery(10), code);

		if (filters != null && filters.length > 0) {

			bean.setReqCode(checkNull(String.valueOf(filters[0][0])));
			bean.setReqname(checkNull(String.valueOf(filters[0][1])));
			bean.setCandidateCode(checkNull(String.valueOf(filters[0][2])));
			bean.setCandidateName(checkNull(String.valueOf(filters[0][3])));
			bean.setRankId(checkNull(String.valueOf(filters[0][4])));
			bean.setRankName(checkNull(String.valueOf(filters[0][5])));
			bean.setReqsDateCombo(checkNull(String.valueOf(filters[0][6])));
			bean.setFrmDate(checkNull(String.valueOf(filters[0][7])));
			bean.setToDate(checkNull(String.valueOf(filters[0][8])));
			bean.setReqsStatus(checkNull(String.valueOf(filters[0][9])));

			if (!(String.valueOf(filters[0][10]).equals("") || String.valueOf(
					filters[0][10]).equals("null"))) {
				bean.setFlag(checkNull(String.valueOf(filters[0][10])));
				bean.setReportFlag("true");
			} else {
				bean.setReportFlag("false");
			}
			bean.setSaveSetting(checkNull(String.valueOf(filters[0][11])));

		}
	}

	/**
	 * following function is called to display the requisition details when 
	 * the search setting drop down is selected. 
	 * @param bean
	 */
	public void getReqsnDetails(CandidateStatusReport bean) {
		Object[] code = new Object[1];
		code[0] = bean.getAppRepCode();
		Object[][] filters = getSqlModel().getSingleResult(getQuery(11), code);
		String str = "";
		String str1 = "";
		if (filters != null && filters.length > 0) {
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
		}

	}

	/**
	 * following function is called to display the sorting option details
	 *  when the search setting drop down is selected. 
	 * @param bean
	 */
	public void getSortingDet(CandidateStatusReport bean) {

		Object[] code = new Object[1];
		code[0] = bean.getAppRepCode();
		bean.setRadioFlag("true");
		Object[][] sorting = getSqlModel().getSingleResult(getQuery(12), code);
		if (sorting != null && sorting.length > 0) {

			bean.setSortBy(checkNull(String.valueOf(sorting[0][0])));
			if (String.valueOf(sorting[0][1]).equals("A")) {
				bean.setRadio1("true");
				bean.setAsc1("A");
			} else if (String.valueOf(sorting[0][1]).equals("D")) {
				bean.setRadioFlag1("true");
				bean.setDesc1("D");
			}

			bean.setFirstBy(checkNull(String.valueOf(sorting[0][2])));
			if (String.valueOf(sorting[0][3]).equals("A")) {
				bean.setRadio2("true");
				bean.setAsce1("A");
			} else {
				bean.setRadioFlag2("true");
				bean.setDesce1("D");
			}

			bean.setSecondBy(checkNull(String.valueOf(sorting[0][4])));
			if (String.valueOf(sorting[0][5]).equals("A")) {
				bean.setRadio3("true");
				bean.setAscc1("A");
			} else {
				bean.setRadioFlag3("true");
				bean.setDescc1("D");
			}
		}
	}

	/**
	 * following function is called to display the Column definition details
	 * @param bean
	 * @param request
	 */
	public void getColumnDef(CandidateStatusReport bean,
			HttpServletRequest request) {
		try {

			Object[] code = new Object[1];
			Object[][] checkBox = new Object[1][12];
			code[0] = bean.getAppRepCode();
			Object[][] columnDef = getSqlModel().getSingleResult(getQuery(13),
					code);

			if (columnDef != null && columnDef.length > 0) {//Candidate Name
				bean.setPageFlag("true");
				if (String.valueOf(columnDef[0][0]).equals("Y")) {
					checkBox[0][0] = String.valueOf("true");
				} else {
					checkBox[0][0] = String.valueOf("false");
				}
				bean.setChkCand(String.valueOf(columnDef[0][0]));
				if (String.valueOf(columnDef[0][1]).equals("Y")) {//Email Id
					checkBox[0][1] = String.valueOf("true");
				} else {
					checkBox[0][1] = String.valueOf("false");
				}
				bean.setChkEmail(String.valueOf(columnDef[0][1]));

				if (String.valueOf(columnDef[0][2]).equals("Y")) {//Candidate Search Status
					checkBox[0][2] = String.valueOf("true");
				} else {
					checkBox[0][2] = String.valueOf("false");
				}
				bean.setChkSearchStat(String.valueOf(columnDef[0][2]));
				if (String.valueOf(columnDef[0][3]).equals("Y")) {//Candidate Screening Status
					checkBox[0][3] = String.valueOf("true");
				} else {
					checkBox[0][3] = String.valueOf("false");
				}
				bean.setChkCandScrnStat(String.valueOf(columnDef[0][3]));
				if (String.valueOf(columnDef[0][4]).equals("Y")) {//Test Result
					checkBox[0][4] = String.valueOf("true");
				} else {
					checkBox[0][4] = String.valueOf("false");
				}

			 

				if (String.valueOf(columnDef[0][5]).equals("Y")) {//Interview Status
					checkBox[0][5] = String.valueOf("true");
				} else {
					checkBox[0][5] = String.valueOf("false");
				}

				bean.setChkIntStat(String.valueOf(columnDef[0][5]));

				if (String.valueOf(columnDef[0][6]).equals("Y")) {//Interview Round
					checkBox[0][6] = String.valueOf("true");
				} else {
					checkBox[0][6] = String.valueOf("false");
				}
				/*bean.setChkIntRnd(String.valueOf(columnDef[0][6]));*/
				if (String.valueOf(columnDef[0][7]).equals("Y")) {//Make Offer Status
					checkBox[0][7] = String.valueOf("true");
				} else {
					checkBox[0][7] = String.valueOf("false");
				}
				bean.setChkOffStat(String.valueOf(columnDef[0][7]));

				if (String.valueOf(columnDef[0][8]).equals("Y")) {//Offer Status
					checkBox[0][8] = String.valueOf("true");
				} else {
					checkBox[0][8] = String.valueOf("false");
				}
				bean.setChkOffStat(String.valueOf(columnDef[0][8]));
				if (String.valueOf(columnDef[0][9]).equals("Y")) {//Appointment Status
					checkBox[0][9] = String.valueOf("true");
				} else {
					checkBox[0][9] = String.valueOf("false");
				}
				bean.setChkAppntStat(String.valueOf(columnDef[0][9]));
				if (String.valueOf(columnDef[0][10]).equals("Y")) {//Convert to Employee
					checkBox[0][10] = String.valueOf("true");
				} else {
					checkBox[0][10] = String.valueOf("false");
				}
				bean.setChkConEmp(String.valueOf(columnDef[0][10]));

				if (String.valueOf(columnDef[0][11]).equals("Y")) {//Convert to Employee
					checkBox[0][11] = String.valueOf("true");
				} else {
					checkBox[0][11] = String.valueOf("false");
				}
				bean.setChkMobileNo(String.valueOf(columnDef[0][11]));

				request.setAttribute("checkBox", checkBox);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to display the advance filter details 
	 * @param bean
	 */
	public void getAdvanceDet(CandidateStatusReport bean) {
		Object[] code = new Object[1];
		code[0] = bean.getAppRepCode();
		Object[][] advanceDet = getSqlModel().getSingleResult(getQuery(14),
				code);

		if (advanceDet != null && advanceDet.length > 0) {
			bean.setAdvScrn(checkNull(String.valueOf(advanceDet[0][0])));
			bean.setAdvIntRnd(checkNull(String.valueOf(advanceDet[0][1])));
			bean.setIntRndVal(checkNull(String.valueOf(advanceDet[0][2])));
			bean.setAdvOffStat(checkNull(String.valueOf(advanceDet[0][3])));
			bean.setAdvAppntStat(checkNull(String.valueOf(advanceDet[0][4])));

		}

	}

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * following function is called to display the summary details in Jsp mode.
	 * @param reqCode
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Object[][] getSummaryInJsp(String reqCode, CandidateStatusReport bean)
			throws Exception {
		int cntr = 0;
		String query = " SELECT HRMS_REC_REQS_HDR.REQS_CODE,HRMS_REC_REQS_HDR.REQS_NAME";
		int counter = 0;
		if (bean.getChkCandScrnStat().equals("Y")) {
			counter++;
			query += " , (SELECT COUNT(RESUME_STATUS) FROM HRMS_REC_RESUME_BANK WHERE RESUME_STATUS='T' AND RESUME_REQS_CODE IN("
					+ reqCode + "))";
			query += " ,(SELECT COUNT(RESUME_STATUS) FROM HRMS_REC_RESUME_BANK WHERE RESUME_STATUS='I' AND RESUME_REQS_CODE IN("
					+ reqCode + "))";
			query += " ,(SELECT COUNT(RESUME_STATUS) FROM HRMS_REC_RESUME_BANK WHERE RESUME_STATUS='B' AND RESUME_REQS_CODE IN("
					+ reqCode + "))";
		} else {
			query += ",'','','' ";
		}

		if (bean.getChkIntStat().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(EVAL_INT_STATUS) FROM HRMS_REC_CANDEVAL WHERE EVAL_INT_STATUS='S' and EVAL_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(EVAL_INT_STATUS) FROM HRMS_REC_CANDEVAL WHERE EVAL_INT_STATUS='R' and EVAL_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(EVAL_INT_STATUS) FROM HRMS_REC_CANDEVAL WHERE EVAL_INT_STATUS='O' and EVAL_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'','',''";

		}

		if (bean.getChkMkOffStat().equals("Y")) {
			counter++;
			query += ", (SELECT COUNT(EVAL_MAKE_OFFER) FROM HRMS_REC_CANDEVAL WHERE EVAL_MAKE_OFFER='Y' AND EVAL_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(EVAL_MAKE_OFFER) FROM HRMS_REC_CANDEVAL WHERE EVAL_MAKE_OFFER='N' AND EVAL_REQS_CODE IN("
					+ reqCode + "))";
		} else {
			query += ",'',''";

		}

		if (bean.getChkOffStat().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='D' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='I' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='OA' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='OR' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='C' and OFFER_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'','','','',''";

		}

		if (bean.getChkAppntStat().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='D' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='I' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='OA' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='OR' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='C' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'','','','',''";

		}

		if (bean.getChkConEmp().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(APPOINT_CONVERT_EMP) FROM HRMS_REC_APPOINT WHERE APPOINT_CONVERT_EMP='Y'  and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ", (SELECT COUNT(APPOINT_CONVERT_EMP) FROM HRMS_REC_APPOINT WHERE APPOINT_CONVERT_EMP='N' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'',''";
		}

		query += "  FROM HRMS_REC_RESUME_BANK ";
		query += "  left join HRMS_REC_REQS_HDR on (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)";
		query += " WHERE HRMS_REC_REQS_HDR.REQS_CODE in(" + reqCode + ")";
		query += " GROUP BY  HRMS_REC_REQS_HDR.REQS_CODE,HRMS_REC_REQS_HDR.REQS_NAME";
		Object[][] summaryData = null;
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {

			summaryData = new Object[data.length][counter + 2];
			for (int i = 0; i < data.length; i++) {
				int cnt = 0;
				summaryData[i][cnt] = cntr++;
				cnt++;
				summaryData[i][cnt] = String.valueOf(data[i][1]);//Reqsn Name
				cnt++;
				if (bean.getChkCandScrnStat().equals("Y")) {
					summaryData[i][cnt] = String.valueOf(data[i][2])
							+ "                                        "
							+ String.valueOf(data[i][3])
							+ "                              "
							+ String.valueOf(data[i][4]);//Short list for test//Short list for Interview;//Both
					cnt++;

				}

				if (bean.getChkIntStat().equals("Y")) {
					summaryData[i][cnt] = String.valueOf(data[i][5])
							+ "               " + String.valueOf(data[i][6])
							+ "                 	" + String.valueOf(data[i][7]);//Interview Status for Selected //Rejected//On Hold
					cnt++;
				}

				if (bean.getChkMkOffStat().equals("Y")) {
					summaryData[i][cnt] = String.valueOf(data[i][8])
							+ "       " + String.valueOf(data[i][9]);//Make offer status Yes //No
					cnt++;

				}

				if (bean.getChkOffStat().equals("Y")) {
					summaryData[i][cnt] = String.valueOf(data[i][10])
							+ "       " + String.valueOf(data[i][11])
							+ "             " + String.valueOf(data[i][12])
							+ "              " + String.valueOf(data[i][13])
							+ "              " + String.valueOf(data[i][14]);//Offer Due/Issued/Accepted/Rejected/Canceled
					cnt++;

				}

				if (bean.getChkAppntStat().equals("Y")) {
					summaryData[i][cnt] = String.valueOf(data[i][15])
							+ "         " + String.valueOf(data[i][16])
							+ "             " + String.valueOf(data[i][17])
							+ "            " + String.valueOf(data[i][18])
							+ "            " + String.valueOf(data[i][19]);//Appointment Due
					cnt++;

				}

				if (bean.getChkConEmp().equals("Y")) {
					summaryData[i][cnt] = String.valueOf(data[i][20])
							+ "      " + String.valueOf(data[i][21]);//Convert to Employee

				}
				cnt = 0;
			}

		}

		return summaryData;

	}

	/**
	 * following function is called to generate the summary report data
	 * @param reqCode
	 * @param bean
	 * @return
	 */
	public Object[][] getSummaryReport(String reqCode,
			CandidateStatusReport bean) throws Exception {

		int cntr = 0;
		String query = " SELECT HRMS_REC_REQS_HDR.REQS_CODE,HRMS_REC_REQS_HDR.REQS_NAME";
		int counter = 0;
		if (bean.getChkCandScrnStat().equals("Y")) {
			counter++;
			query += " , (SELECT COUNT(RESUME_STATUS) FROM HRMS_REC_RESUME_BANK WHERE RESUME_STATUS='T' AND RESUME_REQS_CODE IN("
					+ reqCode + "))";
			query += " ,(SELECT COUNT(RESUME_STATUS) FROM HRMS_REC_RESUME_BANK WHERE RESUME_STATUS='I' AND RESUME_REQS_CODE IN("
					+ reqCode + "))";
			query += " ,(SELECT COUNT(RESUME_STATUS) FROM HRMS_REC_RESUME_BANK WHERE RESUME_STATUS='B' AND RESUME_REQS_CODE IN("
					+ reqCode + "))";
		} else {
			query += ",'','','' ";
		}

		if (bean.getChkIntStat().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(EVAL_INT_STATUS) FROM HRMS_REC_CANDEVAL WHERE EVAL_INT_STATUS='S' and EVAL_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(EVAL_INT_STATUS) FROM HRMS_REC_CANDEVAL WHERE EVAL_INT_STATUS='R' and EVAL_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(EVAL_INT_STATUS) FROM HRMS_REC_CANDEVAL WHERE EVAL_INT_STATUS='O' and EVAL_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'','',''";

		}

		if (bean.getChkMkOffStat().equals("Y")) {
			counter++;
			query += ", (SELECT COUNT(EVAL_MAKE_OFFER) FROM HRMS_REC_CANDEVAL WHERE EVAL_MAKE_OFFER='Y' AND EVAL_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(EVAL_MAKE_OFFER) FROM HRMS_REC_CANDEVAL WHERE EVAL_MAKE_OFFER='N' AND EVAL_REQS_CODE IN("
					+ reqCode + "))";
		} else {
			query += ",'',''";

		}

		if (bean.getChkOffStat().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='D' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='I' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='OA' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='OR' and OFFER_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_STATUS='C' and OFFER_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'','','','',''";

		}

		if (bean.getChkAppntStat().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='D' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='I' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='OA' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='OR' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ",(SELECT COUNT(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_STATUS='C' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'','','','',''";

		}

		if (bean.getChkConEmp().equals("Y")) {
			counter++;
			query += ",(SELECT COUNT(APPOINT_CONVERT_EMP) FROM HRMS_REC_APPOINT WHERE APPOINT_CONVERT_EMP='Y'  and APPOINT_REQS_CODE IN("
					+ reqCode + "))";
			query += ", (SELECT COUNT(APPOINT_CONVERT_EMP) FROM HRMS_REC_APPOINT WHERE APPOINT_CONVERT_EMP='N' and APPOINT_REQS_CODE IN("
					+ reqCode + "))";

		} else {
			query += ",'',''";
		}

		query += "  FROM HRMS_REC_RESUME_BANK ";
		query += "  left join HRMS_REC_REQS_HDR on (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)";
		query += " WHERE HRMS_REC_REQS_HDR.REQS_CODE in(" + reqCode + ")";
		query += " GROUP BY  HRMS_REC_REQS_HDR.REQS_CODE,HRMS_REC_REQS_HDR.REQS_NAME";
		Object[][] summaryData = null;
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			if (bean.getFlag().equals("Pdf")) {
				summaryData = new Object[data.length][counter + 2];
				for (int i = 0; i < data.length; i++) {
					int cnt = 0;
					summaryData[i][cnt] = cntr++;
					cnt++;
					summaryData[i][cnt] = String.valueOf(data[i][1]);//Reqsn Name
					cnt++;
					if (bean.getChkCandScrnStat().equals("Y")) {
						summaryData[i][cnt] = "\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][2])
								+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][3])
								+ "\t\t\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][4]);//Short list for test//Short list for Interview;//Both
						cnt++;

					}

					if (bean.getChkIntStat().equals("Y")) {
						summaryData[i][cnt] = "\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][5])
								+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][6])
								+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][7]);//Interview Status for Selected //Rejected//On Hold
						cnt++;
					}

					if (bean.getChkMkOffStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][8])
								+ "					" + String.valueOf(data[i][9]);//Make offer status Yes //No
						cnt++;

					}

					if (bean.getChkOffStat().equals("Y")) {
						summaryData[i][cnt] = "\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][10]) + "											"
								+ String.valueOf(data[i][11]) + "											"
								+ String.valueOf(data[i][12])
								+ "													   \t\t\t"
								+ String.valueOf(data[i][13])
								+ " 																				"
								+ String.valueOf(data[i][14]);//Offer Due/Issued/Accepted/Rejected/Canceled
						cnt++;

					}

					if (bean.getChkAppntStat().equals("Y")) {
						summaryData[i][cnt] = "\t\t\t\t\t\t"
								+ String.valueOf(data[i][15])
								+ "\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][16])
								+ "\t\t\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][17])
								+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][18])
								+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
								+ String.valueOf(data[i][19]);//Appointment Due
						cnt++;

					}

					if (bean.getChkConEmp().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][20])
								+ "			  " + String.valueOf(data[i][21]);//Convert to Employee

					}
					cnt = 0;
				}
			} else if (bean.getFlag().equals("Txt")
					|| bean.getFlag().equals("")) {

				summaryData = new Object[data.length][counter + 2];
				for (int i = 0; i < data.length; i++) {
					int cnt = 0;
					summaryData[i][cnt] = cntr++;
					cnt++;
					summaryData[i][cnt] = String.valueOf(data[i][1]);//Reqsn Name
					cnt++;
					if (bean.getChkCandScrnStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][2]) + "\t"
								+ String.valueOf(data[i][3]) + "\t"
								+ String.valueOf(data[i][4]);//Short list for test//Short list for Interview;//Both
						cnt++;

					}

					if (bean.getChkIntStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][5])
								+ "\t " + String.valueOf(data[i][6]) + "\t "
								+ String.valueOf(data[i][7]);//Interview Status for Selected //Rejected//On Hold
						cnt++;
					}

					if (bean.getChkMkOffStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][8]) + "\t"
								+ String.valueOf(data[i][9]);//Make offer status Yes //No
						cnt++;

					}

					if (bean.getChkOffStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][10])
								+ "\t" + String.valueOf(data[i][11]) + "\t"
								+ String.valueOf(data[i][12]) + "\t"
								+ String.valueOf(data[i][13]) + "\t"
								+ String.valueOf(data[i][14]);//Offer Due/Issued/Accepted/Rejected/Canceled
						cnt++;

					}

					if (bean.getChkAppntStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][15])
								+ "\t" + String.valueOf(data[i][16]) + "\t"
								+ String.valueOf(data[i][17]) + "\t"
								+ String.valueOf(data[i][18]) + "\t"
								+ String.valueOf(data[i][19]);//Appointment Due
						cnt++;

					}

					if (bean.getChkConEmp().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][20])
								+ "\t" + String.valueOf(data[i][21]);//Convert to Employee

					}
					cnt = 0;
				}

			} else {

				summaryData = new Object[data.length][counter + 2];
				for (int i = 0; i < data.length; i++) {
					int cnt = 0;
					summaryData[i][cnt] = cntr++;
					cnt++;
					summaryData[i][cnt] = String.valueOf(data[i][1]);//Reqsn Name
					cnt++;
					if (bean.getChkCandScrnStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][2])
								+ "          " + String.valueOf(data[i][3])
								+ "            	" + String.valueOf(data[i][4]);//Short list for test//Short list for Interview;//Both
						cnt++;

					}

					if (bean.getChkIntStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][5])
								+ "               "
								+ String.valueOf(data[i][6])
								+ "                 	"
								+ String.valueOf(data[i][7]);//Interview Status for Selected //Rejected//On Hold
						cnt++;
					}

					if (bean.getChkMkOffStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][8])
								+ "       " + String.valueOf(data[i][9]);//Make offer status Yes //No
						cnt++;

					}

					if (bean.getChkOffStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][10])
								+ "       " + String.valueOf(data[i][11])
								+ "             " + String.valueOf(data[i][12])
								+ "              "
								+ String.valueOf(data[i][13])
								+ "              "
								+ String.valueOf(data[i][14]);//Offer Due/Issued/Accepted/Rejected/Canceled
						cnt++;

					}

					if (bean.getChkAppntStat().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][15])
								+ "         " + String.valueOf(data[i][16])
								+ "             " + String.valueOf(data[i][17])
								+ "            " + String.valueOf(data[i][18])
								+ "            " + String.valueOf(data[i][19]);//Appointment Due
						cnt++;

					}

					if (bean.getChkConEmp().equals("Y")) {
						summaryData[i][cnt] = String.valueOf(data[i][20])
								+ "      " + String.valueOf(data[i][21]);//Convert to Employee

					}
					cnt = 0;
				}

			}

		}
		return summaryData;

	}

	/**
	 * following function is called to display the summary details of all the requisitions when the 
	 * View Summary button is clicked.
	 * @param bean
	 */
	public void displaySummaryDetails(CandidateStatusReport bean,
			HttpServletRequest request) {
		ArrayList<Object[][]> list = new ArrayList<Object[][]>();

		try {
			String query1 = " SELECT distinct NVL(REQS_NAME,' '),"
					+ "	NVL(TO_CHAR(REQS_DATE,'DD-MM-YYYY'),' ') as RequDate,"
					+ " NVL(RANK_NAME,' ') as position,	DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition') as approvalstatus,"
					+ " NVL(DIV_NAME,' ') as division,DECODE(REQS_STATUS,'O','Open','C','Close','R','Rejected') as reqstatus,REQS_CODE"
					+ " FROM  HRMS_REC_RESUME_BANK"
					+ " left join HRMS_REC_CAND_DATABANK on (HRMS_REC_CAND_DATABANK.CAND_CODE =HRMS_REC_RESUME_BANK.RESUME_CAND_CODE)"
					+ " inner join HRMS_REC_REQS_HDR on (HRMS_REC_RESUME_BANK.RESUME_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " where 1=1 AND HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS='A'";

			String reqCode = request.getParameter("reqCode");

			if (!(reqCode == null || reqCode.equals("null") || reqCode
					.equals(""))) {
				query1 += " AND HRMS_REC_REQS_HDR.REQS_CODE  IN(" + reqCode
						+ ")";
			}

			/*	if(bean.getSelectedReq()!=null  && bean.getSelectedReq().length()>0){
					query1+= " AND HRMS_REC_REQS_HDR.REQS_CODE  in(='"+ bean.getReqCode()+"'";
				}*/

			if (!(bean.getRankId() == null) && !bean.getRankId().equals("")) {
				query1 += " AND HRMS_RANK.RANK_ID ='" + bean.getRankId() + "'";
			}

			if (!(bean.getCandidateCode() == null)
					&& !bean.getCandidateCode().equals("")) {
				query1 += " AND HRMS_REC_RESUME_BANK.RESUME_CAND_CODE='"
						+ bean.getCandidateCode() + "'";
			}

			if (bean.getReqsDateCombo().equals("O")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("B")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("A")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("OB")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("OA")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("F")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

				if (!(bean.getToDate() == null) && !bean.getToDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

			}

			if (!(bean.getReqsStatus().equals("") || bean.getReqsStatus()
					.equals("null"))) {
				query1 += " AND REQS_STATUS=" + "'" + bean.getReqsStatus()
						+ "'";
			}

			//First Sort

			if (bean.getSortBy().equals("RN") || bean.getSortBy().equals("")) {
				if (bean.getAsc1().equals("Y")) {
					query1 += " ORDER BY NVL(REQS_NAME,' ') ASC";
				} else {
					query1 += " order by NVL(REQS_NAME,' ') desc";
				}

			} else if (bean.getSortBy().equals("P")) {
				if (bean.getAsc1().equals("Y")) {
					query1 += " ORDER BY position ASC";
				} else {
					query1 += " order by position desc";
				}

			}

			//Second Sort

			if (bean.getSortBy().equals("RN")) {
				if (bean.getAsce1().equals("Y")) {
					query1 += " , NVL(REQS_NAME,' ') ASC";
				} else {
					query1 += " , NVL(REQS_NAME,' ') desc";
				}

			} else if (bean.getFirstBy().equals("P")) {
				if (bean.getAsce1().equals("Y")) {
					query1 += " , position ASC";
				} else {
					query1 += " , position desc";
				}

			}

			//Third Sort

			if (bean.getSecondBy().equals("RN")) {
				if (bean.getAscc1().equals("Y")) {
					query1 += " , NVL(REQS_NAME,' ') ASC";
				} else {
					query1 += " , NVL(REQS_NAME,' ') desc";
				}

			} else if (bean.getSortBy().equals("P")) {
				if (bean.getAscc1().equals("Y")) {
					query1 += " , position ASC";
				} else {
					query1 += " , position desc";
				}

			}

			int columnLength = 0;
			Object[][] reqsns = getSqlModel().getSingleResult(query1);

			if (reqsns != null && reqsns.length > 0) {
				for (int j = 0; j < reqsns.length; j++) {

					Object[][] summary = getSummaryInJsp(String
							.valueOf(reqsns[j][6]), bean);

					if (summary != null && summary.length > 0) {
						columnLength = summary[0].length;
						Object[][] obj = new Object[summary.length][summary[0].length];
						for (int k = 0; k < obj.length; k++) {
							for (int l = 1; l < summary[0].length; l++) {

								obj[k][l] = summary[k][l];
							}
						}
						list.add(obj);
					}
				}//End of for loop

				if (list.size() != 0) {
					ArrayList<Object> sumList = new ArrayList<Object>();
					Object[][] sum = new Object[list.size()][columnLength];
					for (int i = 0; i < list.size(); i++) {
						Object[][] summary = list.get(i);

						for (int j = 0; j < summary.length; j++) {
							sum[i] = summary[j];
						}
					}
					for (int k = 0; k < sum.length; k++) {
						CandidateStatusReport bean1 = new CandidateStatusReport();
						for (int m = 0; m < sum[0].length; m++) {
							int n = 0;
							bean1.setSn(String.valueOf(sum[k][n]));
							n++;
							bean1.setViewReqsn(String.valueOf(sum[k][n]));
							n++;
							if (bean.getChkCandScrnStat().equals("Y")) {
								bean.setCandScrnHdrFlag("true");
								bean1.setCandScrnItrtrFlag("true");
								bean1.setViewScreening(String
										.valueOf(sum[k][n]));
								n++;
							} else {
								bean.setCandScrnHdrFlag("false");
								bean1.setCandScrnItrtrFlag("false");
							}

							if (bean.getChkIntStat().equals("Y")) {
								bean.setIntvHdrFlag("true");
								bean1.setIntvItrtFlag("true");
								bean1.setViewInterview(String
										.valueOf(sum[k][n]));
								n++;
							} else {
								bean.setIntvHdrFlag("false");
								bean1.setIntvItrtFlag("false");
							}

							if (bean.getChkMkOffStat().equals("Y")) {
								bean.setMkOffHdrFlag("true");
								bean1.setMkOffItrtrFlag("true");
								bean1.setViewMakeOffer(String
										.valueOf(sum[k][n]));
								n++;
							} else {
								bean.setMkOffHdrFlag("false");
								bean1.setMkOffItrtrFlag("false");
							}

							if (bean.getChkOffStat().equals("Y")) {
								bean.setOffrHdrFlag("true");
								bean1.setOffrItrtrFlag("true");
								bean1.setViewOffer(String.valueOf(sum[k][n]));
								n++;
							} else {
								bean.setOffrHdrFlag("false");
								bean1.setOffrItrtrFlag("false");
							}

							if (bean.getChkAppntStat().equals("Y")) {
								bean.setAppntmtHdrFlag("true");
								bean1.setAppntmtItrtrFlag("true");
								bean1.setViewAppntmt(String.valueOf(sum[k][n]));
								n++;
							} else {
								bean.setAppntmtHdrFlag("false");
								bean1.setAppntmtItrtrFlag("false");
							}
							if (bean.getChkConEmp().equals("Y")) {
								bean.setConEmpHdrFlag("true");
								bean1.setConEMpItrtrFlag("true");
								bean1.setConEmp(String.valueOf(sum[k][n]));
							} else {
								bean.setConEmpHdrFlag("false");
								bean1.setConEMpItrtrFlag("false");
							}
							n = 0;

						}
						sumList.add(bean1);
					}
					bean.setSummaryList(sumList);
				}
			}//End of reqsns if condition

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to display the requisition details page wise.
	 */
	public void displayReqsInJsp(CandidateStatusReport bean,
			HttpServletRequest request) {
		try {

			String query1 = " SELECT distinct NVL(REQS_NAME,' '),"
					+ "	NVL(TO_CHAR(REQS_DATE,'DD-MM-YYYY'),' ') ,"
					+ " NVL(RANK_NAME,' '),	DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition') ,"
					+ " NVL(DIV_NAME,' ') ,	DECODE(REQS_STATUS,'O','Open','C','Close','N','Canceled'),REQS_CODE"
					+ " FROM  HRMS_REC_REQS_HDR "
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " where 1=1 AND HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN('A','Q')";

			if (!(bean.getReqCode() == null) && !bean.getReqCode().equals("")) {
				query1 += " AND HRMS_REC_REQS_HDR.REQS_CODE ='"
						+ bean.getReqCode() + "'";
			}

			if (!(bean.getRankId() == null) && !bean.getRankId().equals("")) {
				query1 += " AND HRMS_RANK.RANK_ID ='" + bean.getRankId() + "'";
			}

			if (!bean.getSelectedReq().equals("")
					&& bean.getSelectedReq() != null) {
				query1 += " AND HRMS_REC_REQS_HDR.REQS_CODE IN("
						+ bean.getSelectedReq() + ")";
			}

			if (bean.getReqsDateCombo().equals("O")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("B")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("A")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("OB")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("OA")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}
			}

			if (bean.getReqsDateCombo().equals("F")) {
				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

				if (!(bean.getToDate() == null) && !bean.getToDate().equals("")) {
					query1 += " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("
							+ "'" + bean.getFrmDate() + "'" + ",'DD-MM-YYYY')";
				}

			}

			if (!(bean.getReqsStatus().equals("") || bean.getReqsStatus()
					.equals("null"))) {
				query1 += " AND REQS_STATUS=" + "'" + bean.getReqsStatus()
						+ "'";
			}

			if (!bean.getSortBy().equals("1") || !bean.getFirstBy().equals("1")
					|| !bean.getSecondBy().equals("1")) {
				if (!bean.getSortBy().equals("CN")
						&& !bean.getFirstBy().equals("CN")
						&& !bean.getSecondBy().equals("CN"))
					query1 += " ORDER BY ";
			}

			if (!bean.getSortBy().equals("1")) {

				if (!(bean.getSortBy().equals("CN"))) {

					query1 += getSortingValue(bean.getSortBy());

					if (bean.getAsc1().equals("A")) {
						query1 += " " + getSortOrder("A");
					} else {
						query1 += " " + getSortOrder("D");
					}

					if (!bean.getFirstBy().equals("1")
							|| !bean.getSecondBy().equals("1")) {
						if (!bean.getFirstBy().equals("CN")
								|| !bean.getSecondBy().equals("CN"))
							query1 += ",";
					}
				}
			}

			if (!bean.getFirstBy().equals("1")) {

				if (!bean.getFirstBy().equals("CN")) {
					query1 += getSortingValue(bean.getFirstBy());// +" "+getSortOrder(bean.getThenByOrder1()); 
					if (bean.getAsce1().equals("A")) {
						query1 += "" + getSortOrder("A");
						;
					} else {
						query1 += "" + getSortOrder("D");
						;
					}

					if (!bean.getSecondBy().equals("1")) {
						if (!bean.getSecondBy().equals("CN"))
							query1 += ",";
					}
				}
			}

			if (!bean.getSecondBy().equals("1")) {
				if (!bean.getSecondBy().equals("CN")) {
					query1 += getSortingValue(bean.getSecondBy());//+" "+getSortOrder(bean.getThenByOrder2());
					if (bean.getAscc1().equals("A")) {
						query1 += "" + getSortOrder("A");
						;
					} else {
						query1 += "" + getSortOrder("D");
						;
					}
				}
			}

			Object[][] data = getSqlModel().getSingleResult(query1);
			String[] pageIndex = Utility.doPaging(bean.getMyReqPage(),
					data.length, 1);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "1";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyReqPage("1");

			if (data != null && data.length > 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					bean.setViewReqsName(String.valueOf(data[i][0]));// Requisition Name
					bean.setViewReqsDate(String.valueOf(data[i][1]));// Requisition Date
					bean.setPosition(String.valueOf(data[i][2]));//Position
					bean.setStatus(String.valueOf(data[i][5]));// Requisition status
					bean.setApprvalStatus(String.valueOf(data[i][3]));// Requisition Approval Status
					bean.setDiv(String.valueOf(data[i][4]));// Division
					bean.setViewReqsCode(String.valueOf(data[i][6]));// Requisition Code

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to display the candidate names that will be displayed in the jsp mode for  
	 * the corresponding requisition.  
	 */
	public void displayCandidates(CandidateStatusReport bean,
			HttpServletRequest request) {
		try {
			String query = "SELECT DISTINCT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ,"
					+ " CAND_EMAIL_ID,DECODE(RESUME_STATUS,'R','Rejected','T','Short List For Test','I','Short List For Interview','B','Short List For Test or Interview'),DECODE(TEST_RESULT,'P','Pass','F','Fail','','Nil') ,"
					+ " DECODE(OFFER_STATUS,'D','Due','S','Send For Approval','I','Issued','OA','Accepted','OR','Rejected','C','Canceled','','N/A'), "
					+ " DECODE(APPOINT_STATUS,'D ','Due','S ','Send For Approval','I ','Issued','OA','Accepted','OR','Rejected','C ','Canceled',' ','DDD','','N/A') , "
					+ " DECODE(HRMS_REC_APPOINT.APPOINT_CONVERT_EMP,'Y','Yes','N','No','','No','No'), "
					+ " HRMS_REC_CAND_DATABANK.CAND_CODE,HRMS_REC_REQS_HDR.REQS_CODE "
					+ " FROM HRMS_REC_RESUME_BANK "
					+ " LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE =HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) "
					+ " LEFT JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE) "
					+ " LEFT JOIN  HRMS_REC_SCHTEST_DTL ON(HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE "
					+ " AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) "
					+ "LEFT JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
					+ " LEFT JOIN HRMS_REC_TESTRESULT ON(HRMS_REC_TESTRESULT.TEST_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
					+ " AND HRMS_REC_TESTRESULT.TEST_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " LEFT JOIN HRMS_REC_OFFER ON(HRMS_REC_OFFER.OFFER_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
					+ " AND HRMS_REC_OFFER.OFFER_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " LEFT JOIN HRMS_REC_APPOINT ON(HRMS_REC_APPOINT.APPOINT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE  AND "
					+ " HRMS_REC_APPOINT.APPOINT_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " WHERE 1=1 ";

			if (!(bean.getReqCode().equals("") || bean.getReqCode().equals(
					"null"))) {
				query += "AND HRMS_REC_REQS_HDR.REQS_CODE=" + bean.getReqCode();
			}

			if (!(bean.getAdvScrn().equals(""))) {
				query += " AND RESUME_STATUS=" + "'" + bean.getAdvScrn() + "'";
			}

			if (!(bean.getAdvOffStat().equals("") || bean.getAdvOffStat()
					.equals("null"))) {
				query += " AND OFFER_STATUS=" + "'" + bean.getAdvOffStat()
						+ "'";
			}

			if (!(bean.getAdvAppntStat().equals("") || bean.getAdvAppntStat()
					.equals("null"))) {
				query += " AND APPOINT_STATUS=" + "'" + bean.getAdvAppntStat()
						+ "'";
			}

			if (bean.getSortBy().equals("CN") || bean.getFirstBy().equals("CN")
					|| bean.getSecondBy().equals("CN")) {
				query += " ORDER BY ";
				if (bean.getAsc1().equals("A") || bean.getAsce1().equals("A")
						|| bean.getAscc1().equals("A")) {
					query += " CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ASC";
				} else {
					query += " CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME DESC";
				}
			}

			ArrayList<Object> list = new ArrayList<Object>();
			Object[][] candidate = getSqlModel().getSingleResult(query);
			if (candidate != null && candidate.length > 0) {

				String[] pageIndex = Utility.doPaging(bean.getMyCandPage(),
						candidate.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "1";
					pageIndex[2] = "20";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage1", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo1", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyCandPage("1");
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					CandidateStatusReport bean1 = new CandidateStatusReport();
					bean1.setCand(checkNull(String.valueOf(candidate[i][0])));

					if (bean.getChkEmail().equals("Y")) {
						bean1.setEmail(checkNull(String
								.valueOf(candidate[i][1])));
						bean1.setItrEmailFlag("true");
						bean.setItrHdrEmail("true");
					} else {
						bean1.setItrEmailFlag("false");
						bean.setItrHdrEmail("false");

					}

					if (bean.getChkCandScrnStat().equals("Y")) {
						bean1.setScrnStat(checkNull(String
								.valueOf(candidate[i][2])));
						bean1.setItrSrnFlag("true");
						bean.setItrHdrSrn("true");
					} else {
						bean1.setItrSrnFlag("false");
						bean.setItrHdrSrn("false");

					}

				/*	if (bean.getChkTestRes().equals("Y")) {
						bean1.setTestRes(checkNull(String
								.valueOf(candidate[i][3])));
						bean.setItrHdrTest("true");
						bean1.setItrTestFlag("true");
					} else {
						bean.setItrHdrTest("false");
						bean1.setItrTestFlag("false");

					}*/
					/*if (bean.getChkIntRnd().equals("Y")) {
						bean1.setItrInrvRndFlag("true");
						bean.setItrHdrInrvRnd("true");
						int intr = getIntrvRnd(bean.getViewReqsCode(), String
								.valueOf(candidate[i][7]));
						bean1.setIntvround(checkNull(String.valueOf(intr)));
					} else {
						bean1.setItrInrvRndFlag("false");
						bean.setItrHdrInrvRnd("false");
					}*/
					if (bean.getChkOffStat().equals("Y")) {
						bean1.setItrOffStatFlag("true");
						bean.setItrHdrOffStat("true");
						bean1.setOfferstatus(checkNull(String
								.valueOf(candidate[i][4])));
					} else {
						bean1.setItrOffStatFlag("false");
						bean.setItrHdrOffStat("false");
					}
					if (bean.getChkAppntStat().equals("Y")) {
						bean1.setItrAppntStatFlag("true");
						bean.setItrHdrAppntStat("true");
						bean1.setAppntStat(checkNull(String
								.valueOf(candidate[i][5])));

					} else {
						bean1.setItrAppntStatFlag("false");
						bean.setItrHdrAppntStat("false");
					}

					if (bean.getChkConEmp().equals("Y")) {
						bean1.setItrConFlag("true");
						bean.setItrHdrCon("true");
						bean1.setConvert(checkNull(String
								.valueOf(candidate[i][6])));
					} else {
						bean1.setItrConFlag("false");
						bean.setItrHdrCon("false");
					}
					list.add(bean1);

				}

				bean.setCandList(list);
				bean.setNoData("true");

			} else {
				bean.setNoData("false");
				if (bean.getChkConEmp().equals("Y"))
					bean.setItrHdrCon("true");
				if (bean.getChkAppntStat().equals("Y"))
					bean.setItrHdrAppntStat("true");
				if (bean.getChkOffStat().equals("Y"))
					bean.setItrHdrOffStat("true");
				/*if (bean.getChkIntRnd().equals("Y"))
					bean.setItrHdrInrvRnd("true");*/
				/*if (bean.getChkTestRes().equals("Y"))
					bean.setItrHdrTest("true");*/
				if (bean.getChkCandScrnStat().equals("Y"))
					bean.setItrHdrSrn("true");
				if (bean.getChkEmail().equals("Y"))
					bean.setItrHdrEmail("true");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getCandStausReport(CandidateStatusReport bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		
		
		ReportDataSet rds = new ReportDataSet();
		String type = bean.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Candidate Status Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Candidate Status Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(bean.getUserEmpId());
		rds.setUserEmpId(bean.getUserEmpId());
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("portrait");
		rds.setTotalColumns(13);
		
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "CandidateStatusReport_input.action");
			// Initial Page Action name
		}
		
		
		
		String query = " SELECT * FROM (SELECT DISTINCT HRMS_REC_REQS_HDR.REQS_NAME,"
			        + "  HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME ||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME Name  ,"
			        + "	 HRMS_REC_CAND_DATABANK.CAND_EMAIL_ID,HRMS_REC_CAND_DATABANK.CAND_MOB_PHONE  ,"
					+ "	 DECODE(HRMS_REC_RESUME_BANK.RESUME_STATUS,'R','Rejected','T','Short List For Test', "
					+ "	 'I','Short List For Interview','B','Short List For Test or Interview') Resume_Status ,"
					+ "	  DECODE(HRMS_REC_OFFER.OFFER_STATUS,'D ','Due','S','Send For Approval','I ','Issued','OA'," 
					+ "  'Accepted','OR','Rejected','C ','Canceled','','N/A') , DECODE(HRMS_REC_APPOINT.APPOINT_STATUS,'D ', "
					+ "  'Due','S ','Send For Approval','I ','Issued','OA','Accepted','OR','Rejected','C ','Canceled',' ','DDD', "
					+ "  '','N/A') as Appoint_Status  , DECODE(HRMS_REC_APPOINT.APPOINT_CONVERT_EMP,'Y','Yes','N','No','','No','No') , "
					+ "   NVL(HRMS_REC_CAND_DATABANK.CAND_COMPANY,' '),  HRMS_REC_CAND_DATABANK.CAND_CURR_CTC,NVL(HRMS_REC_CAND_DATABANK.CAND_LOCATION,' '),"
					+ "   HRMS_REC_CAND_DATABANK.CAND_EXP_YEAR  ||'Years  '||   HRMS_REC_CAND_DATABANK.CAND_EXP_MONTH ||' Months', "
					+ "	  NVL(HRMS_QUA.QUA_NAME,'     '),  "
					+ "	  DECODE(CAND_REF_TYPE, 'A', 'All', 'E', 'Employee Referral','C','Job Consultant', 'O','On Line Application','D','Direct'),"
					+ "	  recruitName.EMP_FNAME||' '||recruitName.EMP_MNAME||' '||recruitName.EMP_LNAME as REC_NAME  ,"
					+ "	  refBy.EMP_FNAME||' '||refBy.EMP_MNAME||' '||refBy.EMP_LNAME as REF_BY, TO_CHAR(HRMS_REC_REQS_HDR.REQS_DATE,'DD-MM-YYYY'),"
					
					+ "	  MAX(case when HRMS_QUA.QUA_LEVEL='DI' THEN 1 when HRMS_QUA.QUA_LEVEL='UG' THEN 2  "
					+ "	  when HRMS_QUA.QUA_LEVEL='GR' THEN 3 "
					+ "	  when HRMS_QUA.QUA_LEVEL='PG' THEN 4 "
					+ "	  when HRMS_QUA.QUA_LEVEL='PH' THEN 5 "
					+ "	  when  HRMS_QUA.QUA_LEVEL='DO' THEN 5 ELSE 0 end)  as Q_LEVEL,"
					+ "	  HRMS_REC_REQS_HDR.REQS_NAME||HRMS_REC_CAND_DATABANK.CAND_CODE"
					+ "	  FROM  HRMS_REC_CAND_POSTING  "
					+ "	  INNER JOIN  HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE =  HRMS_REC_CAND_POSTING.POST_REQS_CODE)  " 
					+ "	  LEFT JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_CAND_POSTING.POST_CAND_CODE ) "
					+ "	  LEFT JOIN HRMS_REC_RESUME_BANK ON (HRMS_REC_RESUME_BANK.RESUME_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE ) "
					+ "	  LEFT JOIN HRMS_REC_APPOINT ON(HRMS_REC_APPOINT.APPOINT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE  AND  HRMS_REC_APPOINT.APPOINT_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)  "
					+ "	  LEFT JOIN HRMS_REC_OFFER ON(HRMS_REC_OFFER.OFFER_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE  AND HRMS_REC_OFFER.OFFER_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ "	  LEFT JOIN HRMS_REC_SCHTEST_DTL ON(HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE  AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) "
					+ "	  LEFT JOIN HRMS_REC_CD_QUADTL QUADTL ON(HRMS_REC_CAND_DATABANK.CAND_CODE=QUADTL.CAND_CODE)  "
					+ "	  LEFT join HRMS_QUA on (HRMS_QUA.QUA_ID = QUADTL.CAND_QUA_CODE) "
					+ "	  LEFT JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)"
					
					+ "   LEFT JOIN  HRMS_REC_VACPUB_HDR on (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE )"
					+ "   LEFT JOIN  HRMS_REC_VACPUB_RECDTL on (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE )"
					+ "   LEFT JOIN  HRMS_EMP_OFFC recruitName on (HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID = recruitName.EMP_ID )"
					+ "   LEFT JOIN  HRMS_EMP_OFFC refBy ON (refBy.EMP_ID = HRMS_REC_CAND_DATABANK.CAND_POSTRESUME_EMPID)"
					+ "	  WHERE 1=1 ";
		
		if (!(bean.getReqCode() == null) && !bean.getReqCode().equals("")) {
			query += " AND HRMS_REC_CAND_POSTING.POST_REQS_CODE ='"
					+ bean.getReqCode().trim() + "'";
		}

		if (!(bean.getCandidateCode() == null)
				&& !bean.getCandidateCode().equals("")) {
			query += " AND HRMS_REC_CAND_POSTING.POST_CAND_CODE='"
					+ bean.getCandidateCode().trim() + "'";
		}

		if (!(bean.getRankId() == null) && !bean.getRankId().equals("")) {
			query += " AND HRMS_RANK.RANK_ID ='" + bean.getRankId().trim()
					+ "'";
		}

		if (!(bean.getAdvScrn().equals(""))) {
			query += " AND RESUME_STATUS=" + "'" + bean.getAdvScrn().trim()
					+ "'";
		}

		if (!(bean.getAdvOffStat().equals("") || bean.getAdvOffStat().equals(
				"null"))) {
			query += " AND OFFER_STATUS=" + "'" + bean.getAdvOffStat().trim()
					+ "'";
		}

		if (!(bean.getAdvAppntStat().equals("") || bean.getAdvAppntStat()
				.equals("null"))) {
			query += " AND APPOINT_STATUS=" + "'"
					+ bean.getAdvAppntStat().trim() + "'";
		}
		
		if (!(bean.getReqsStatus().equals(""))) {
			query += " AND REQS_STATUS='" + bean.getReqsStatus() + "'";
		}

		
		if (bean.getReqsDateCombo().trim().equals("O")) {
			query += " AND CAND_POSTING_DATE = TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("B")) {
			query += " AND CAND_POSTING_DATE < TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("A")) {
			query += " AND CAND_POSTING_DATE > TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("OB")) {
			
			query += " AND CAND_POSTING_DATE <= TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("OA")) {
			query += " AND CAND_POSTING_DATE >= TO_DATE('" + bean.getFrmDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getReqsDateCombo().trim().equals("F")) {
			query += " AND (CAND_POSTING_DATE BETWEEN TO_DATE('"
					+ bean.getFrmDate() + "', 'DD-MM-YYYY') ";
			if (!bean.getToDate().trim().equals("")) { 
				query += "AND TO_DATE('" + bean.getToDate()
						+ "', 'DD-MM-YYYY')) ";
			} else {
				query += "AND TO_DATE(to_char(sysdate,'dd-mm-yyyy'),'DD-MM-YYYY')) ";
			}
		}
		
		
		query+=" GROUP BY REQS_NAME,REQS_NAME||HRMS_REC_CAND_DATABANK.CAND_CODE, "
			+ " HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME ||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME ,  "
			+ "  HRMS_REC_CAND_DATABANK.CAND_EMAIL_ID,HRMS_REC_CAND_DATABANK.CAND_MOB_PHONE, "
			+ " HRMS_REC_RESUME_BANK.RESUME_STATUS,HRMS_REC_APPOINT.APPOINT_STATUS,"
			+ " HRMS_REC_OFFER.OFFER_STATUS, HRMS_REC_APPOINT.APPOINT_CONVERT_EMP, "
			+ " HRMS_REC_CAND_POSTING.POST_CAND_CODE,HRMS_REC_CAND_POSTING.POST_REQS_CODE," 
			+ " HRMS_RANK.RANK_NAME, HRMS_REC_CAND_DATABANK.CAND_COMPANY," 		
			+ " HRMS_REC_CAND_DATABANK.CAND_CURR_CTC,HRMS_REC_CAND_DATABANK.CAND_LOCATION, " 
			+ " CAND_EXP_YEAR,CAND_EXP_MONTH,QUA_NAME,REQS_NAME||HRMS_REC_CAND_DATABANK.CAND_CODE,"
			+ " CAND_REF_TYPE,recruitName.EMP_FNAME||' '||recruitName.EMP_MNAME||' '||recruitName.EMP_LNAME,"
			+ " refBy.EMP_FNAME||' '||refBy.EMP_MNAME||' '||refBy.EMP_LNAME, HRMS_REC_REQS_HDR.REQS_DATE)" 
			+ " ORDER BY REQS_NAME,Name,Q_LEVEL " ;
		 
		
		String[] header = { "Requisition Name", "Candidate Name", "Email Id",
				"Mobile No", "Candidate Screening Status", "Offer Status", "Appointment Status",
				"Convert to Employee", "Last Company Name", "Current CTC (In Lacs)",
				"Current Location", "Experience","Qualification (Highest)"
				,"Source","Recruiter name","referred by","requisition date"
				};
		
		Object[][] candidateData = getSqlModel().getSingleResult(query);
		Object[][] finalObj = null;

		if (!(candidateData == null || candidateData.length == 0)) {
			HashMap<String, Object[]> map = new HashMap<String, Object[]>();

				for (int i = 0; i < candidateData.length; i++) {
					map.put(String.valueOf(candidateData[i][18]),
							(Object[]) candidateData[i]);
				}
				finalObj = new Object[map.size()][candidateData[0].length];
				Iterator itKeyList = null;
				Object key = null;
				Set keySet = map.keySet();
				itKeyList = keySet.iterator();
				int countIncr = 0;
				System.out.println("map.size()  " + map.size());
				while (itKeyList.hasNext()) {
					key = itKeyList.next();
					System.out.println(" key  " + key);
					Object[] value = (Object[]) map.get(key);
					for (int i = 0; i < value.length; i++) {
						finalObj[countIncr][i] = value[i];
					}
					countIncr++;

				}
		}
		
		TableDataSet tdstable = new TableDataSet();

		
		if (finalObj == null || finalObj.length == 0) {
			System.out.println("Within If--->No records available");
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "\nNo records available\n\n";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });

			noData.setBorder(false);
			rg.addTableToDoc(noData);
		   }else{
			tdstable.setHeader(header);
			tdstable.setHeaderTable(true);
			tdstable.setData(finalObj);
			tdstable.setHeaderBorderDetail(3);
			int[] cellAlign = {  0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0,0,0,0,0,0 };
			tdstable.setCellAlignment(cellAlign);
			int[] cellWidth = {  10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
			tdstable.setCellWidth(cellWidth);
			tdstable.setBorderDetail(3);
			rg.addTableToDoc(tdstable);
			
		
			int totalRecords=finalObj.length;
			TableDataSet totalCandidate = new TableDataSet();
			totalCandidate.setData(new Object[][] { { "Total Records : "
					+ totalRecords } });
			totalCandidate.setCellAlignment(new int[] { 0 });
			totalCandidate.setCellWidth(new int[] { 100 });
			totalCandidate.setBorderDetail(0);
			totalCandidate.setBodyBGColor(new BaseColor(200, 200, 200));
			totalCandidate.setBodyFontStyle(1);
			totalCandidate.setBlankRowsAbove(1);
			rg.addTableToDoc(totalCandidate);
		}
		
		rg.process();

		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			/* Generates the report as attachment */
			rg.saveReport(response);
		}
	
	}

}//End of class

