package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Recruitment.OpenVacReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class OpenVacReportModel extends ModelBase {

	public void getReport(HttpServletRequest request,
			HttpServletResponse response, OpenVacReport bean, String[] colnames) {

		try {
		 
			String s = "\n  Open Vacancy Analysis  \n\n";
			String reportType = "";
			if (bean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					reportType, s, "A4");
			if (bean.getReportType().equals("X")) {
				Object[][] title = new Object[2][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = "Open Vacancies Analysis";

				title[1][0] = "";
				title[1][1] = "";
				title[1][2] = "";

				int[] cols = { 20, 20, 30 };
				int[] align = { 0, 0, 1 };
				rg.tableBodyNoCellBorder(title, cols, align, 1);

			}
			Object tab[][] = getSqlModel().getSingleResult(callSqlQuery(bean));
			int length;
			String[] actualCol = new String[Integer.parseInt(bean
					.getCheckedCount())];
			int cellwidth[] = new int[Integer.parseInt(bean.getCheckedCount())];
			int alignment[] = new int[Integer.parseInt(bean.getCheckedCount())];
			int k = 1;
			actualCol[0] = colnames[0];
			cellwidth[0] = 15;
			alignment[0] = 1;
			if (bean.getReqCodeChk().equals("on")) {
				actualCol[k] = colnames[1];
				cellwidth[k] = 30;
				alignment[k] = 0;
				k++;
			}
			if (bean.getPostionChk().equals("on")) {
				actualCol[k] = colnames[2];
				cellwidth[k] = 45;
				alignment[k] = 0;
				k++;
			}
			if (bean.getDateChk().equals("on")) {
				actualCol[k] = colnames[3];
				cellwidth[k] = 45;
				alignment[k] = 1;
				k++;
			}
			if (bean.getHiringMangerChk().equals("on")) {
				actualCol[k] = colnames[4];
				cellwidth[k] = 35;
				alignment[k] = 0;
				k++;
			}
			if (bean.getRecruiterChk().equals("on")) {
				actualCol[k] = colnames[5];
				cellwidth[k] = 45;
				alignment[k] = 0;
				k++;
			}
			if (bean.getRequiredDateChk().equals("on")) {
				actualCol[k] = colnames[6];
				cellwidth[k] = 20;
				alignment[k] = 1;
				k++;
			}
			if (bean.getTotalVacChk().equals("on")) {
				actualCol[k] = colnames[7];
				cellwidth[k] = 25;
				alignment[k] = 2;
				k++;
			}
			if (bean.getVacAssignChk().equals("on")) {
				actualCol[k] = colnames[8];
				cellwidth[k] = 25;
				alignment[k] = 2;
				k++;
			}
			if (bean.getOpenVacChk().equals("on")) {
				actualCol[k] = colnames[9];
				cellwidth[k] = 20;
				alignment[k] = 2;
				k++;
			}
			if (bean.getClosedvacChk().equals("on")) {
				actualCol[k] = colnames[10];
				cellwidth[k] = 20;
				alignment[k] = 2;
				k++;
			}
			if (bean.getClosedDateChk().equals("on")) {
				actualCol[k] = colnames[11];
				cellwidth[k] = 20;
				alignment[k] = 2;
				k++;
			}
			if (bean.getOverDueDayChk().equals("on")) {
				actualCol[k] = colnames[12];
				cellwidth[k] = 20;
				alignment[k] = 2;
				k++;
			}
			if (bean.getApprovarChk().equals("on")) {
				actualCol[k] = colnames[13];
				cellwidth[k] = 20;
				alignment[k] = 2;
				k++;
			}
			length = tab.length;
			Object totalObj[][] = new Object[length][Integer.parseInt(bean
					.getCheckedCount())];
			rg.addFormatedText(s, 6, 0, 1, 0);
			if (bean.getReportType().equalsIgnoreCase("Xls")) {
				rg.addText("", 0, 6, 0);
				rg.addText(s, 0, 6, 0);
				rg.addText("", 0, 6, 0);
				rg.addText("", 0, 6, 0);
			}
			if (tab != null && tab.length == 0) {

				rg.addText("No Records to Display", 0, 1, 0);

			} else {
				int m = 1;
				int cnt = 1;
				for (int i = 0; i < tab.length; i++) {

					totalObj[i][0] = cnt;
					cnt++;
					if (bean.getReqCodeChk().equals("on")) {
						if (tab[i][0] != null) {
							totalObj[i][m] = checkNull("" + tab[i][0]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}

					if (bean.getPostionChk().equals("on")) {
						if (tab[i][1] != null) {
							totalObj[i][m] = checkNull("" + tab[i][1]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}
					if (bean.getDateChk().equals("on")) {
						if (tab[i][2] != null) {
							totalObj[i][m] = checkNull("" + tab[i][2]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}

					if (bean.getHiringMangerChk().equals("on")) {
						if (tab[i][3] != null) {
							totalObj[i][m] = checkNull("" + tab[i][3]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}

					if (bean.getRecruiterChk().equals("on")) {
						if (tab[i][4] != null) {
							totalObj[i][m] = checkNull("" + tab[i][4]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}
					if (bean.getRequiredDateChk().equals("on")) {
						if (tab[i][5] != null) {
							totalObj[i][m] = checkNull("" + tab[i][5]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}

					if (bean.getTotalVacChk().equals("on")) {
						if (tab[i][6] != null) {
							totalObj[i][m] = checkNull("" + tab[i][6]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}

					if (bean.getVacAssignChk().equals("on")) {
						if (tab[i][7] != null) {
							totalObj[i][m] = checkNull("" + tab[i][7]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}
					if (bean.getOpenVacChk().equals("on")) {
						if (tab[i][8] != null) {
							totalObj[i][m] = checkNull("" + tab[i][8]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}

					if (bean.getClosedvacChk().equals("on")) {
						if (tab[i][9] != null) {
							totalObj[i][m] = checkNull("" + tab[i][9]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}
					if (bean.getClosedDateChk().equals("on")) {
						if (tab[i][10] != null) {
							totalObj[i][m] = checkNull("" + tab[i][10]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}
					if (bean.getOverDueDayChk().equals("on")) {
						if (tab[i][11] != null) {
							totalObj[i][m] = checkNull("" + tab[i][11]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}
					if (bean.getApprovarChk().equals("on")) {
						if (tab[i][12] != null) {
							totalObj[i][m] = checkNull("" + tab[i][12]);
						} else {
							totalObj[i][m] = "";
						}
						m++;
					}
					m = 1;
				}
				if (!bean.getExportAll().equals("on")) {

					String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							totalObj.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					Object[][] pageObj = new Object[numOfRec][Integer
							.parseInt(bean.getCheckedCount())];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < Integer.parseInt(bean
								.getCheckedCount()); j++) {
							pageObj[z][j] = totalObj[i][j];
							pageObj[z][0] = String.valueOf(srNo);
						}
						z++;
						srNo++;
					}

					if (k == 1) {
						rg.addText("No Records to Display", 0, 1, 0);
					} else {
						rg.tableBody(actualCol, pageObj, cellwidth, alignment);
					}

					if (bean.getReportType().equalsIgnoreCase("Xls")) {
						if (k == 1) {
							rg.addText("No Records to Display", 0, 1, 0);
						} else {
							rg.tableBodyNoBorder(pageObj, cellwidth, alignment);
						}
					}

				} else {
					if (k == 1) {
						rg.addText("No Records to Display", 0, 1, 0);
					} else {
						rg.tableBody(actualCol, totalObj, cellwidth, alignment);
					}

					if (bean.getReportType().equalsIgnoreCase("Xls")) {
						if (k == 1) {
							rg.addText("No Records to Display", 0, 1, 0);
						} else {
							rg
									.tableBodyNoBorder(totalObj, cellwidth,
											alignment);
						}
					}
				}
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 
	public void displayReq(OpenVacReport bean) {
		/*String sql =" SELECT REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' "
					+" ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE FROM HRMS_REC_REQS_HDR  "
					+" INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) " 
		            + " INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) " 
			        +" LEFT JOIN HRMS_REC_VACPUB_RECDTL ON( HRMS_REC_VACPUB_RECDTL.PUB_DTL_CODE =HRMS_REC_VACPUB_HDR.PUB_CODE) " 
				    +" WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND HRMS_REC_VACPUB_HDR.PUB_STATUS='P' ";
		System.out.println("bean.getHiringManagerId()======== "+bean.getHiringManagerId());*/

		try {
			String sql = "SELECT DISTINCT REQS_NAME AS REQCODE,RANK_NAME  AS REQPOSITION ,TO_CHAR(REQS_DATE,'DD-MM-YYYY') AS REQDATE ,"
					+ " DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE "
					+ " FROM HRMS_REC_VACPUB_RECDTL INNER JOIN  HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"
					+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID)"
					+ " INNER JOIN HRMS_EMP_OFFC A ON(A.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+ " INNER JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " WHERE REQS_APPROVAL_STATUS IN ('A','Q')";
			if (!bean.getHiringManagerId().equals("")) {
				sql += " AND REQS_HIRING_MANAGER=" + bean.getHiringManagerId();
			}
			if (!bean.getRecruiterCode().equals("")) {
				sql += " AND PUB_REC_EMPID =" + bean.getRecruiterCode();
			}
			if (!bean.getPositionId().equals("")) {
				sql += " AND REQS_POSITION =" + bean.getPositionId();
			}
			if (!bean.getDateFilter().equals("1")
					&& bean.getReqCode().equals("")) {
				if (bean.getDateFilter().equals("O")) {
					sql += " AND REQS_DATE = TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("OB")) {
					sql += " AND REQS_DATE <= TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("OA")) {
					sql += " AND REQS_DATE >= TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("B")) {
					sql += " AND REQS_DATE < TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("A")) {
					sql += " AND REQS_DATE > TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("F")) {
					sql += " AND REQS_DATE >= TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY') ";
					if (!(bean.getToDate().equals("") || bean.getToDate()
							.equals("dd-mm-yyyy"))) {
						sql += " AND REQS_DATE <= TO_DATE('" + bean.getToDate()
								+ "','DD-MM-YYYY') ";
					}
				}
			}
			Object[][] data = getSqlModel().getSingleResult(sql);
			if (data != null && data.length > 0) {
				bean.setDataLength("" + data.length);
				ArrayList<OpenVacReport> list = new ArrayList<OpenVacReport>();
				for (int i = 0; i < data.length; i++) {
					OpenVacReport bean1 = new OpenVacReport();

					bean1.setItReqName(callStringBreak("" + data[i][0], 21));
					bean1.setItPosition("" + data[i][1]);
					bean1.setItReqDate("" + data[i][2]);
					bean1.setItStatus("" + data[i][3]);
					bean1.setItReqCode("" + data[i][4]);
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
					list.add(bean1);
				}
				bean.setDispList(list);
			} else {

				bean.setNoDataReq("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callViewScreen(OpenVacReport bean, HttpServletRequest request) {
		
		try {
			if (bean.getReqCodeChk().equals("on")) {
				bean.setReqCodeChkFlag("true");
			}
			if (bean.getPostionChk().equals("on")) {
				bean.setPostionChkFlag("true");
			}
			if (bean.getDateChk().equals("on")) {
				bean.setDateChkFlag("true");
			}
			if (bean.getHiringMangerChk().equals("on")) {
				bean.setHiringMangerChkFlag("true");
			}
			if (bean.getTotalVacChk().equals("on")) {
				bean.setTotalVacChkFlag("true");
			}
			if (bean.getRecruiterChk().equals("on")) {
				bean.setRecruiterChkFlag("true");
			}
			if (bean.getRequiredDateChk().equals("on")) {
				bean.setRequiredDateChkFlag("true");
			}
			if (bean.getVacAssignChk().equals("on")) {
				bean.setVacAssignChkFlag("true");
			}
			if (bean.getOpenVacChk().equals("on")) {
				bean.setOpenVacChkFlag("true");
			}
			if (bean.getClosedvacChk().equals("on")) {
				bean.setClosedvacChkFlag("true");
			}
			if (bean.getClosedDateChk().equals("on")) {
				bean.setClosedDateChkFlag("true");
			}
			if (bean.getOverDueDayChk().equals("on")) {
				bean.setOverDueDayChkFlag("true");
			}
			if (bean.getApprovarChk().equals("on")) {
				bean.setApprovarChkFlag("true");
			}
			Object[][] data = getSqlModel().getSingleResult(callSqlQuery(bean));
			if (data != null && data.length > 0) {
				bean.setDataLength(String.valueOf(data.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						data.length, 20);
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

				ArrayList<OpenVacReport> openViewList = new ArrayList<OpenVacReport>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					OpenVacReport bean1 = new OpenVacReport();
					bean1.setIvReqCode(callStringBreak(String
							.valueOf(data[i][0]), 13));
					bean1.setIvPostion(String.valueOf(data[i][1]));
					bean1.setIvReqDate(String.valueOf(data[i][2]));
					bean1.setIvHiringManager(checkNull(String
							.valueOf(data[i][3])));
					bean1.setIvRecruiterName(checkNull(String
							.valueOf(data[i][4])));
					bean1.setIvRequiredDate(checkNull(String
							.valueOf(data[i][5])));
					bean1.setIvTotalVac(checkNull(String.valueOf(data[i][6])));
					bean1.setIvAssgnVac(checkNull(String.valueOf(data[i][7])));
					bean1.setIvOpenVac(checkNull(String.valueOf(data[i][8])));
					bean1.setIvCloseVac(checkNull(String.valueOf(data[i][9])));
					bean1
							.setIvClosedDate(checkNull(String
									.valueOf(data[i][10])));
					bean1
							.setIvOverDueDay(checkNull(String
									.valueOf(data[i][11])));
					bean1.setIvApprovarName(checkNull(String
							.valueOf(data[i][12])));
					if (bean.getReqCodeChk().equals("on")) {
						bean1.setReqCodeChkFlag("true");
					}

					if (bean.getPostionChk().equals("on")) {
						bean1.setPostionChkFlag("true");
					}

					if (bean.getDateChk().equals("on")) {
						bean1.setDateChkFlag("true");
					}

					if (bean.getHiringMangerChk().equals("on")) {
						bean1.setHiringMangerChkFlag("true");
					}
					if (bean.getTotalVacChk().equals("on")) {
						bean1.setTotalVacChkFlag("true");
					}
					if (bean.getRecruiterChk().equals("on")) {
						bean1.setRecruiterChkFlag("true");
					}
					if (bean.getRequiredDateChk().equals("on")) {
						bean1.setRequiredDateChkFlag("true");
					}
					if (bean.getVacAssignChk().equals("on")) {
						bean1.setVacAssignChkFlag("true");
					}
					if (bean.getOpenVacChk().equals("on")) {
						bean1.setOpenVacChkFlag("true");
					}
					if (bean.getClosedvacChk().equals("on")) {
						bean1.setClosedvacChkFlag("true");
					}
					if (bean.getClosedDateChk().equals("on")) {
						bean1.setClosedDateChkFlag("true");
					}

					if (bean.getOverDueDayChk().equals("on")) {
						bean1.setOverDueDayChkFlag("true");
					}
					if (bean.getApprovarChk().equals("on")) {
						bean1.setApprovarChkFlag("true");
					}

					openViewList.add(bean1);
				}
				bean.setOpenViewList(openViewList);
			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				bean.setNoData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String callSqlQuery(OpenVacReport bean) {
		
		/*String query="SELECT REQS_NAME AS REQCODE,RANK_NAME  AS REQPOSITION ,TO_CHAR(REQS_DATE,'DD-MM-YYYY') AS REQDATE ,"+
		 " A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME AS HIRINGMANAGER,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS RECRUITERNAME,"+
		 " TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY') AS REQUIRED_BY_DATE,VACAN_NUMBERS AS TOTALVAC,PUB_ASG_VAC AS ASSIGNED_VAC,(nvl(PUB_ASG_VAC,0) - nvl(PUB_CLOSE_VACAN,0)) AS OPENVAC,"+
		 " nvl(PUB_CLOSE_VACAN,0) AS CLOSED_VAC ,TO_CHAR(PUB_VAC_CLOSEDATE,'DD-MM-YYYY') , "+
		 " CASE WHEN PUB_VAC_CLOSEDATE IS NULL THEN TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') ELSE TO_DATE(PUB_VAC_CLOSEDATE,'DD-MM-YYYY') -TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') END AS OVER_DUE_DAY, "+ 
		 " B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME AS APPROVARNAME"+
		 " FROM HRMS_REC_VACPUB_RECDTL INNER JOIN  HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"+  
		 " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"+ 
		 " INNER JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE)"+  
		 " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID)"+  
		 " INNER JOIN HRMS_EMP_OFFC A ON(A.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"+ 
		 "INNER JOIN HRMS_EMP_OFFC B ON(B.EMP_ID=HRMS_REC_REQS_HDR.REQS_APPR_CODE )"+ 
		 " INNER JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"+
		 " WHERE HRMS_REC_VACPUB_HDR.PUB_STATUS='P'";*/

		/*String query="SELECT REQS_NAME AS REQCODE,RANK_NAME  AS REQPOSITION ,TO_CHAR(REQS_DATE,'DD-MM-YYYY') AS REQDATE ,"+
		 " A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME AS HIRINGMANAGER,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS RECRUITERNAME,"+
		 " TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY') AS REQUIRED_BY_DATE,VACAN_NUMBERS AS TOTALVAC,PUB_ASG_VAC AS ASSIGNED_VAC,(nvl(PUB_ASG_VAC,0) - nvl(PUB_CLOSE_VACAN,0)) AS OPENVAC,"+
		 " nvl(PUB_CLOSE_VACAN,0) AS CLOSED_VAC ,TO_CHAR(REQS_CLOSE_DATE,'dd-mm-yyyy') , "+
		 " CASE WHEN PUB_VAC_CLOSEDATE IS NULL THEN TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') ELSE TO_DATE(PUB_VAC_CLOSEDATE,'DD-MM-YYYY') -TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') END AS OVER_DUE_DAY, "+ 
		 " B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME AS APPROVARNAME"+
		 " FROM HRMS_REC_VACPUB_RECDTL INNER JOIN  HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"+  
		 " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"+ 
		 " INNER JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE)"+  
		 " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID)"+  
		 " INNER JOIN HRMS_EMP_OFFC A ON(A.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"+ 
		 "INNER JOIN HRMS_EMP_OFFC B ON(B.EMP_ID=HRMS_REC_REQS_HDR.REQS_APPR_CODE )"+ 
		 " INNER JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"+
		 " WHERE HRMS_REC_REQS_HDR.REQS_STATUS = 'C' ";*/

		String query = "";
		 
		try {
			query = "SELECT REQS_NAME AS REQCODE,RANK_NAME  AS REQPOSITION ,TO_CHAR(REQS_DATE,'DD-MM-YYYY') AS REQDATE ,"
					+ " A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME AS HIRINGMANAGER,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS RECRUITERNAME,"
					+ " TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY') AS REQUIRED_BY_DATE,VACAN_NUMBERS AS TOTALVAC,PUB_ASG_VAC AS ASSIGNED_VAC, "
					//+" (nvl(VACAN_NUMBERS,0) - nvl(PUB_ASG_VAC,0)) AS OPENVAC,"
					+" (NVL(PUB_ASG_VAC,0) - NVL(PUB_CLOSE_VACAN,0)) AS OPENVAC,"
					+ " NVL(PUB_CLOSE_VACAN,0) AS CLOSED_VAC , TO_CHAR(PUB_VAC_CLOSEDATE,'dd-mm-yyyy') , " //TO_CHAR(REQS_CLOSE_DATE,'dd-mm-yyyy') , "
					+ " CASE WHEN PUB_VAC_CLOSEDATE IS NULL THEN TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') ELSE TO_DATE(PUB_VAC_CLOSEDATE,'DD-MM-YYYY') -TO_DATE(VACAN_REQ_DATE,'DD-MM-YYYY') END AS OVER_DUE_DAY, "
					+ " B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME AS APPROVARNAME"
					+ " FROM HRMS_REC_VACPUB_RECDTL INNER JOIN  HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"
					+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"
					+ " INNER JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID)"
					+ " INNER JOIN HRMS_EMP_OFFC A ON(A.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+ " INNER JOIN HRMS_EMP_OFFC B ON(B.EMP_ID=HRMS_REC_REQS_HDR.REQS_APPR_CODE )"
					+ " INNER JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " WHERE HRMS_REC_REQS_HDR.REQS_STATUS IN ('O','C') ";
			if (!bean.getReqCode().equals("")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
						+ bean.getReqCode();
			}
			if (!bean.getHiringManagerId().equals("")) {
				query += " AND REQS_HIRING_MANAGER="
						+ bean.getHiringManagerId();
			}
			if (!bean.getRecruiterCode().equals("")) {
				query += " AND PUB_REC_EMPID =" + bean.getRecruiterCode();
			}
			if (!bean.getPositionId().equals("")) {
				query += " AND REQS_POSITION =" + bean.getPositionId();
			}
			if (!bean.getDateFilter().equals("1")
					&& bean.getReqCode().equals("")) {
				if (bean.getDateFilter().equals("O")) {
					query += " AND REQS_DATE = TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("OB")) {
					query += " AND REQS_DATE <= TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("OA")) {
					query += " AND REQS_DATE >= TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("B")) {
					query += " AND REQS_DATE < TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("A")) {
					query += " AND REQS_DATE > TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getDateFilter().equals("F")) {
					query += " AND REQS_DATE >= TO_DATE('" + bean.getFrmDate()
							+ "','DD-MM-YYYY')";
					if (!(bean.getToDate().equals("") || bean.getToDate()
							.equals("dd-mm-yyyy"))) {
						query += " AND REQS_DATE <= TO_DATE('"
								+ bean.getToDate() + "','DD-MM-YYYY') ";
					}
				}
			}
			if (!bean.getTotalVacAdvCom().equals("1")) {
				query += " AND NVL(VACAN_NUMBERS,0) "
						+ getAdvanceFilter(bean.getTotalVacAdvCom()) + " "
						+ bean.getTotalVacAdvTxt();
			}
			if (!bean.getAsgnVacAdvCom().equals("1")) {
				query += " AND NVL(PUB_ASG_VAC,0) "
						+ getAdvanceFilter(bean.getAsgnVacAdvCom()) + " "
						+ bean.getAsgnVacAdvTxt();
			}
			if (!bean.getOpenVacAdvCom().equals("1")) {
				query += " AND (NVL(PUB_ASG_VAC,0) - NVL(PUB_CLOSE_VACAN,0)) "
						+ getAdvanceFilter(bean.getOpenVacAdvCom()) + " "
						+ bean.getOpenVacAdvTxt();
			}
			if (!bean.getCloseVacAdvCom().equals("1")) {
				query += " AND NVL(PUB_CLOSE_VACAN,0) "
						+ getAdvanceFilter(bean.getCloseVacAdvCom()) + " "
						+ bean.getCloseVacAdvTxt();
			}
			if (!bean.getRequirdeByDateCom().trim().equals("1")) {
				if (bean.getRequirdeByDateCom().trim().equals("O")) {
					query += " AND VACAN_REQ_DATE = TO_DATE('"
							+ bean.getRequirdeByFromDateTxt()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getRequirdeByDateCom().trim().equals("OB")) {
					query += " AND VACAN_REQ_DATE <= TO_DATE('"
							+ bean.getRequirdeByFromDateTxt()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getRequirdeByDateCom().trim().equals("OA")) {
					query += " AND VACAN_REQ_DATE >= TO_DATE('"
							+ bean.getRequirdeByFromDateTxt()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getRequirdeByDateCom().trim().equals("B")) {
					query += " AND VACAN_REQ_DATE < TO_DATE('"
							+ bean.getRequirdeByFromDateTxt()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getRequirdeByDateCom().trim().equals("A")) {
					query += " AND VACAN_REQ_DATE > TO_DATE('"
							+ bean.getRequirdeByFromDateTxt()
							+ "','DD-MM-YYYY')";
				}

				if (bean.getRequirdeByDateCom().trim().equals("F")) {
					query += " AND VACAN_REQ_DATE >= TO_DATE('"
							+ bean.getRequirdeByFromDateTxt()
							+ "','DD-MM-YYYY') ";

					if (!(bean.getRequirdeByToDateTxt().equals("") || bean
							.getRequirdeByToDateTxt().equals("dd-mm-yyyy"))) {
						query += "AND REQS_DATE <= TO_DATE('"
								+ bean.getRequirdeByToDateTxt()
								+ "','DD-MM-YYYY') ";
					}
				}
			}
			if (!bean.getSelectedReq().equals("")
					&& !bean.getSelectedReq().equals("null")) {
				query += " AND HRMS_REC_REQS_HDR.REQS_CODE IN ("
						+ bean.getSelectedReq() + ")";
			}
			if (!bean.getSortBy().equals("1") || !bean.getThenBy1().equals("1")
					|| !bean.getThenBy2().equals("1")) {
				query += " ORDER BY ";
			}
			if (!bean.getSortBy().equals("1")) {
				query += getSortVal(bean.getSortBy()) + " "
						+ getSortOrder(bean.getSortByOrder());
				if (!bean.getThenBy1().equals("1")
						|| !bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}
			if (!bean.getThenBy1().equals("1")) {
				query += getSortVal(bean.getThenBy1()) + " "
						+ getSortOrder(bean.getThenByOrder1());
				if (!bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}
			if (!bean.getThenBy2().equals("1")) {
				query += getSortVal(bean.getThenBy2()) + " "
						+ getSortOrder(bean.getThenByOrder2());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	public String getSortVal(String Status) {
		String sql = "";
		try {
			if (Status.equals("R")) {
				sql = " UPPER(REQS_NAME) ";
			}
			if (Status.equals("P")) {
				sql = "UPPER(RANK_NAME)  ";
			}
			if (Status.equals("D")) {
				sql = " REQS_DATE";
			}
			if (Status.equals("H")) {
				sql = "UPPER(A.EMP_FNAME||' '||A.EMP_MNAME||' '||A.EMP_LNAME ) ";
			}
			if (Status.equals("T")) {
				sql = " VACAN_NUMBERS ";
			}
			if (Status.equals("N")) {
				sql = " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ";
			}
			if (Status.equals("B")) {
				sql = " VACAN_REQ_DATE ";
			}
			if (Status.equals("V")) {
				sql = " PUB_ASG_VAC ";
			}
			if (Status.equals("O")) {
				sql = " OPENVAC ";
			}
			if (Status.equals("C")) {
				sql = " PUB_CLOSE_VACAN ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	public String getSortOrder(String Status) {
		String sql = "";
		try {
			if (Status.equals("A")) {
				sql = "Asc";
			}
			if (Status.equals("D")) {
				sql = "Desc";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	public String getAdvanceFilter(String Status) {
		String sql = "";
		try {
			if (Status.equals("IE")) {
				sql = "=";
			}
			if (Status.equals("LE")) {
				sql = "<=";
			}
			if (Status.equals("GE")) {
				sql = ">=";
			}
			if (Status.equals("LT")) {
				sql = "<";
			}
			if (Status.equals("GT")) {
				sql = ">";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	public void callBack(OpenVacReport bean) {
		try {
			if (bean.getReqCodeChk().equals("on")) {
				bean.setReqCodeChk("checked");
			}
			if (bean.getPostionChk().equals("on")) {
				bean.setPostionChk("checked");
			}
			if (bean.getDateChk().equals("on")) {
				bean.setDateChk("checked");
			}
			if (bean.getHiringMangerChk().equals("on")) {
				bean.setHiringMangerChk("checked");
			}
			if (bean.getTotalVacChk().equals("on")) {
				bean.setTotalVacChk("checked");
			}
			if (bean.getRecruiterChk().equals("on")) {
				bean.setRecruiterChk("checked");
			}
			if (bean.getRequiredDateChk().equals("on")) {
				bean.setRequiredDateChk("checked");
			}
			if (bean.getVacAssignChk().equals("on")) {
				bean.setVacAssignChk("checked");
			}
			if (bean.getOpenVacChk().equals("on")) {
				bean.setOpenVacChk("checked");
			}
			if (bean.getClosedvacChk().equals("on")) {
				bean.setClosedvacChk("checked");
			}
			if (bean.getClosedDateChk().equals("on")) {
				bean.setClosedDateChk("checked");
			}
			if (bean.getOverDueDayChk().equals("on")) {
				bean.setOverDueDayChk("checked");
			}
			if (bean.getApprovarChk().equals("on")) {
				bean.setApprovarChk("checked");
			}
			if (bean.getSortByOrder().equals("A")) {
				bean.setSortByAsc("checked");
			} else {
				bean.setSortByDsc("checked");
			}
			if (bean.getThenByOrder1().equals("A")) {
				bean.setThenByOrder1Asc("checked");
			} else {
				bean.setThenByOrder1Dsc("checked");
			}
			if (bean.getThenByOrder2().equals("A")) {
				bean.setThenByOrder2Asc("checked");
			} else {
				bean.setThenByOrder2Dsc("checked");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void input(OpenVacReport bean) {
		bean.setReqCodeChk("checked");
		bean.setPostionChk("checked");
		bean.setDateChk("checked");
		bean.setHiringMangerChk("checked");
		bean.setTotalVacChk("checked");
		bean.setRecruiterChk("checked");
		bean.setRequiredDateChk("checked");
		bean.setVacAssignChk("checked");
		bean.setOpenVacChk("checked");
		bean.setClosedvacChk("checked");
		bean.setClosedDateChk("checked");
		bean.setOverDueDayChk("checked");
		bean.setApprovarChk("checked");
	}

	public void callPreviousRecord(OpenVacReport bean) {
		String sql = " SELECT VACREP_CODE, VACREP_NAME FROM HRMS_REC_VACREP_FILTERS WHERE VACREP_USEREMPID ="
				+ bean.getUserEmpId() + " ORDER BY UPPER(VACREP_NAME) ";
		Object[][] data = getSqlModel().getSingleResult(sql);
		if (data != null && data.length > 0) {
			LinkedHashMap map = new LinkedHashMap();
			for (int i = 0; i < data.length; i++) {
				map.put(data[i][0], data[i][1]);
			}
			bean.setMap(map);
		}

	}

	public boolean callSave(OpenVacReport bean) {
		if (saveFiletr(bean) && saveSort(bean) && saveColumnDef(bean)
				&& saveAdvance(bean) && saveSelectedReq(bean)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean saveFiletr(OpenVacReport bean) {
		Object[][] filterObj = null;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			filterObj = new Object[1][12];
		} else {
			filterObj = new Object[1][13];
		}
		filterObj[0][0] = bean.getReqCode().equals("") ? null : bean
				.getReqCode();
		filterObj[0][1] = bean.getRecruiterCode().equals("") ? null : bean
				.getRecruiterCode();
		filterObj[0][2] = bean.getPositionId().equals("") ? null : bean
				.getPositionId();
		if (!bean.getDateFilter().equals("1") && bean.getReqCode().equals("")) {
			if (bean.getDateFilter().equals("O")) {
				filterObj[0][3] = "O";
			}

			if (bean.getDateFilter().equals("B")) {
				filterObj[0][3] = "B";
			}

			if (bean.getDateFilter().equals("A")) {
				filterObj[0][3] = "A";
			}

			if (bean.getDateFilter().equals("OB")) {
				filterObj[0][3] = "OB";
			}

			if (bean.getDateFilter().equals("OA")) {
				filterObj[0][3] = "OA";
			}

			if (bean.getDateFilter().equals("F")) {
				filterObj[0][3] = "F";
			}
		} else {
			filterObj[0][3] = "1";
		}
		filterObj[0][4] = bean.getDateFilter().equals("1") ? null : bean
				.getFrmDate().equals("") ? null : bean.getFrmDate();
		filterObj[0][5] = bean.getReqStatus().equals("") ? "V" : bean
				.getReqStatus();
		filterObj[0][6] = bean.getReportType().equals("1") ? "1" : bean
				.getReportType();
		filterObj[0][7] = bean.getSettingName();
		filterObj[0][8] = (!bean.getDateFilter().equals("F")) ? null : bean
				.getToDate().equals("") ? null : bean.getToDate();
		filterObj[0][9] = bean.getUserEmpId();
		filterObj[0][10] = bean.getHiringManagerId().equals("") ? null : bean
				.getHiringManagerId();
		filterObj[0][11] = bean.getRadioStatus();
		boolean flag = false;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			flag = getSqlModel().singleExecute(getQuery(1), filterObj);
		} else {
			filterObj[0][12] = bean.getSearchSetting();
			flag = getSqlModel().singleExecute(getQuery(6), filterObj);
		}
		System.out.println("flag======filetr========= " + flag);
		return flag;
	}

	public boolean saveSort(OpenVacReport bean) {
		Object[][] sortObj = null;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			sortObj = new Object[1][6];
		} else {
			sortObj = new Object[1][7];
		}
		sortObj[0][0] = bean.getSortBy();
		sortObj[0][1] = bean.getSortByOrder();
		sortObj[0][2] = bean.getThenBy1();
		sortObj[0][3] = bean.getThenByOrder1();
		sortObj[0][4] = bean.getThenBy2();
		sortObj[0][5] = bean.getThenByOrder2();
		;
		boolean flag = false;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			flag = getSqlModel().singleExecute(getQuery(2), sortObj);
		} else {
			sortObj[0][6] = bean.getSearchSetting();
			flag = getSqlModel().singleExecute(getQuery(7), sortObj);
		}
		System.out.println("flag======sort========= " + flag);
		return flag;
	}

	public boolean saveColumnDef(OpenVacReport bean) {
		Object[][] colObj = null;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			colObj = new Object[1][13];
		} else {
			colObj = new Object[1][14];
		}
		colObj[0][0] = bean.getReqCodeChk().equals("on") ? "Y" : "N";
		colObj[0][1] = bean.getPostionChk().equals("on") ? "Y" : "N";
		colObj[0][2] = bean.getDateChk().equals("on") ? "Y" : "N";
		colObj[0][3] = bean.getHiringMangerChk().equals("on") ? "Y" : "N";
		colObj[0][4] = bean.getTotalVacChk().equals("on") ? "Y" : "N";
		colObj[0][5] = bean.getRecruiterChk().equals("on") ? "Y" : "N";
		colObj[0][6] = bean.getRequiredDateChk().equals("on") ? "Y" : "N";
		colObj[0][7] = bean.getVacAssignChk().equals("on") ? "Y" : "N";
		colObj[0][8] = bean.getOpenVacChk().equals("on") ? "Y" : "N";
		colObj[0][9] = bean.getClosedvacChk().equals("on") ? "Y" : "N";
		colObj[0][10] = bean.getClosedDateChk().equals("on") ? "Y" : "N";
		colObj[0][11] = bean.getOverDueDayChk().equals("on") ? "Y" : "N";
		colObj[0][12] = bean.getApprovarChk().equals("on") ? "Y" : "N";
		boolean flag = false;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			flag = getSqlModel().singleExecute(getQuery(3), colObj);
		} else {
			colObj[0][13] = bean.getSearchSetting();
			flag = getSqlModel().singleExecute(getQuery(8), colObj);
		}
		System.out.println("flag======column========= " + flag);
		return flag;
	}

	public boolean saveAdvance(OpenVacReport bean) {
		Object[][] colAdv = null;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			colAdv = new Object[1][11];
		} else {
			colAdv = new Object[1][12];
		}
		colAdv[0][0] = bean.getTotalVacAdvCom();
		colAdv[0][1] = bean.getTotalVacAdvTxt().equals("") ? null : bean
				.getTotalVacAdvTxt();
		colAdv[0][2] = bean.getAsgnVacAdvCom();
		colAdv[0][3] = bean.getAsgnVacAdvTxt().equals("") ? null : bean
				.getAsgnVacAdvTxt();
		colAdv[0][4] = bean.getOpenVacAdvCom();
		colAdv[0][5] = bean.getOpenVacAdvTxt().equals("") ? null : bean
				.getOpenVacAdvTxt();
		colAdv[0][6] = bean.getCloseVacAdvCom();
		colAdv[0][7] = bean.getCloseVacAdvTxt().equals("") ? null : bean
				.getCloseVacAdvTxt();
		colAdv[0][8] = bean.getRequirdeByDateCom().trim();
		colAdv[0][9] = bean.getRequirdeByDateCom().equals("1") ? null : bean
				.getRequirdeByFromDateTxt().equals("") ? null : bean
				.getRequirdeByFromDateTxt();
		colAdv[0][10] = (!bean.getRequirdeByDateCom().equals("F")) ? null
				: bean.getRequirdeByToDateTxt().equals("") ? null : bean
						.getRequirdeByToDateTxt();

		boolean flag = false;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			flag = getSqlModel().singleExecute(getQuery(4), colAdv);
		} else {
			colAdv[0][11] = bean.getSearchSetting();
			flag = getSqlModel().singleExecute(getQuery(9), colAdv);
		}
		System.out.println("flag======advance========= " + flag);
		return flag;
	}

	public boolean saveSelectedReq(OpenVacReport bean) {
		Object[][] reqObj = null;
		boolean flag = false;
		if (bean.getSearchSetting().equals("B")
				|| (!bean.getSettingName().trim().equals(
						bean.getSettingNameDup().trim()))) {
			reqObj = new Object[1][1];
			reqObj[0][0] = bean.getSelectedReq();
			flag = getSqlModel().singleExecute(getQuery(5), reqObj);
		} else {
			reqObj = new Object[1][2];
			reqObj[0][0] = bean.getSelectedReq();
			reqObj[0][1] = bean.getSearchSetting();
			flag = getSqlModel().singleExecute(getQuery(10), reqObj);
		}

		return flag;
	}

	public boolean callUpdate(OpenVacReport bean) {
		if (saveFiletr(bean) && saveSort(bean) && saveColumnDef(bean)
				&& saveAdvance(bean) && saveSelectedReq(bean)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * set the data of filter
	 * @param bean
	 */
	public void callFilterSavedData(OpenVacReport bean) {
		String sqlFilter = " SELECT REQS_NAME,VACREP_REQ_CODE,NVL(E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME,' ') as recName , "
				+ "VACREP_RECRUITER_CODE, NVL(RANK_NAME,' '), VACREP_POSITION_CODE, VACREP_DATEOPTION, TO_CHAR(VACREP_REQDATE_FROM,'DD-MM-YYYY'), "
				+ " VACREP_REQSTATUS, VACREP_REPOPTION, VACREP_NAME, TO_CHAR(VACREP_REQDATE_TO ,'DD-MM-YYYY'), "
				+ " (E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME) as hiringName,  VACREP_HIRINGMGR_CODE,VACREP_RADIO_OPTION FROM HRMS_REC_VACREP_FILTERS "
				+ " LEFT JOIN HRMS_REC_REQS_HDR ON (VACREP_REQ_CODE = REQS_CODE) "
				+ " LEFT JOIN HRMS_EMP_OFFC E1 ON (VACREP_RECRUITER_CODE = E1.EMP_ID ) "
				+ " LEFT JOIN HRMS_RANK  ON (VACREP_POSITION_CODE = RANK_ID ) "
				+ " LEFT JOIN HRMS_EMP_OFFC E2 ON (VACREP_HIRINGMGR_CODE = E2.EMP_ID ) "
				+ " WHERE VACREP_CODE =" + bean.getSearchSetting();
		Object[][] data = getSqlModel().getSingleResult(sqlFilter);

		if (data != null && data.length > 0) {
			bean.setReqname(checkNull(String.valueOf(data[0][0])));
			bean.setReqCode(checkNull(String.valueOf(data[0][1])));
			bean.setRecruiterName(checkNull(String.valueOf(data[0][2])));
			bean.setRecruiterCode(checkNull(String.valueOf(data[0][3])));
			bean.setPosition(checkNull(String.valueOf(data[0][4])));
			bean.setPositionId(checkNull(String.valueOf(data[0][5])));
			bean.setDateFilter(checkNull(String.valueOf(data[0][6])).trim());
			bean.setFrmDate(checkNull(String.valueOf(data[0][7])));
			if (String.valueOf(data[0][8]).equals("V")) {
				bean.setHidReportView("checked");
				bean.setHidReportRadio("");
			} else {
				bean.setHidReportRadio("checked");
				bean.setHidReportView("");
			}
			bean.setReportType(String.valueOf(data[0][9]));
			bean.setSettingName(checkNull(String.valueOf(data[0][10])));
			bean.setSettingNameDup(checkNull(String.valueOf(data[0][10])));
			bean.setToDate(checkNull(String.valueOf(data[0][11])));
			bean.setHiringManagerName(checkNull(String.valueOf(data[0][12])));
			bean.setHiringManagerId(checkNull(String.valueOf(data[0][13])));
			if (String.valueOf(data[0][14]).equals("C")) {
				bean.setRadioReq("checked");
			}
			if (String.valueOf(data[0][14]).equals("N")) {
				bean.setRadioRecr("checked");
			}
			if (String.valueOf(data[0][14]).equals("M")) {
				bean.setRadioHirMng("checked");
			}
			if (String.valueOf(data[0][14]).equals("P")) {
				bean.setRadioPosition("checked");
			}

		}
	}

	/**
	 * set the data of sort 
	 * @param bean
	 */
	public void callSortSavedData(OpenVacReport bean) {
		String sqlSort = " SELECT SORT_BY, SORT_BY_ORDER, SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1, "
				+ " SORT_THENBY1_ORDER FROM HRMS_REC_VACREP_SORT WHERE VACREP_CODE ="
				+ bean.getSearchSetting();
		Object[][] sortData = getSqlModel().getSingleResult(sqlSort);
		if (sortData != null && sortData.length > 0) {
			bean.setSortBy(String.valueOf(sortData[0][0]));
			if (String.valueOf(sortData[0][1]).equals("A")) {
				bean.setSortByAsc("checked");
			} else {
				bean.setSortByDsc("checked");
			}

			bean.setThenBy1(String.valueOf(sortData[0][2]));
			if (String.valueOf(sortData[0][3]).equals("A")) {
				bean.setThenByOrder1Asc("checked");
			} else {
				bean.setThenByOrder1Dsc("checked");
			}

			bean.setThenBy2(String.valueOf(sortData[0][4]));
			if (String.valueOf(sortData[0][5]).equals("A")) {
				bean.setThenByOrder2Asc("checked");
			} else {
				bean.setThenByOrder2Dsc("checked");
			}
		}
	}

	/**
	 * set the data of column 
	 * @param bean
	 */

	public void callColumnSavedData(OpenVacReport bean) {
		String sqlColumn = "SELECT COL_REQNAME, COL_POSITION,COL_REQDATE, COL_HIRINGMAN, COL_TOTALNOVAC, COL_RECNAME, "
				+ " COL_REQBYDATE, COL_ASSIGNVAC,COL_OPENVAC, COL_CLOSEVAC,COL_CLOSED_DATE, COL_OVERDUE_DAY,COL_APPRVRNAME FROM HRMS_REC_VACREP_COLDEF WHERE VACREP_CODE ="
				+ bean.getSearchSetting();
		Object[][] data = getSqlModel().getSingleResult(sqlColumn);

		if (data != null && data.length > 0) {

			if (String.valueOf(data[0][0]).equals("Y")) {
				bean.setReqCodeChk("checked");
			}
			if (String.valueOf(data[0][1]).equals("Y")) {
				bean.setPostionChk("checked");
			}
			if (String.valueOf(data[0][2]).equals("Y")) {
				bean.setDateChk("checked");
			}
			if (String.valueOf(data[0][3]).equals("Y")) {
				bean.setHiringMangerChk("checked");
			}
			if (String.valueOf(data[0][4]).equals("Y")) {
				bean.setTotalVacChk("checked");
			}
			if (String.valueOf(data[0][5]).equals("Y")) {
				bean.setRecruiterChk("checked");
			}
			if (String.valueOf(data[0][6]).equals("Y")) {
				bean.setRequiredDateChk("checked");
			}
			if (String.valueOf(data[0][7]).equals("Y")) {
				bean.setVacAssignChk("checked");
			}
			if (String.valueOf(data[0][8]).equals("Y")) {
				bean.setOpenVacChk("checked");
			}
			if (String.valueOf(data[0][9]).equals("Y")) {
				bean.setClosedvacChk("checked");
			}

			if (String.valueOf(data[0][10]).equals("Y")) {
				bean.setClosedDateChk("checked");
			}

			if (String.valueOf(data[0][11]).equals("Y")) {
				bean.setOverDueDayChk("checked");
			}
			if (String.valueOf(data[0][12]).equals("Y")) {
				bean.setApprovarChk("checked");
			}
		}
	}

	/**
	 * set the data of advance filter 
	 * @param bean
	 */

	public void callAdvanceSavedData(OpenVacReport bean) {
		String sqlAdvance = "  SELECT ADV_TOTALNOVAC_OPTION, ADV_TOTALNOVAC, ADV_ASSIGNVAC_OPTION, ADV_ASSIGNVAC, ADV_OPENVAC_OPTION, "
				+ " ADV_OPENVAC, ADV_CLOSEVAC_OPTION, ADV_CLOSEVAC, ADV_REQBYDATE_OPTION,  TO_CHAR(ADV_REQBYDATE_FROM,'DD-MM-YYYY'),"
				+ " TO_CHAR(ADV_REQBYDATE_TO,'DD-MM-YYYY') FROM HRMS_REC_VACREP_ADV WHERE VACREP_CODE ="
				+ bean.getSearchSetting();
		Object[][] data = getSqlModel().getSingleResult(sqlAdvance);

		if (data != null && data.length > 0) {
			bean.setTotalVacAdvCom(String.valueOf(data[0][0]));
			bean.setTotalVacAdvTxt(checkNull(String.valueOf(data[0][1])));
			bean.setAsgnVacAdvCom(String.valueOf(data[0][2]));
			bean.setAsgnVacAdvTxt(checkNull(String.valueOf(data[0][3])));
			bean.setOpenVacAdvCom(String.valueOf(data[0][4]));
			bean.setOpenVacAdvTxt(checkNull(String.valueOf(data[0][5])));
			bean.setCloseVacAdvCom(String.valueOf(data[0][6]));
			bean.setCloseVacAdvTxt(checkNull(String.valueOf(data[0][7])));
			System.out.println("Required date-------->>>>>"
					+ String.valueOf(data[0][8]) + "---");
			bean.setRequirdeByDateCom(String.valueOf(data[0][8]).trim());
			bean
					.setRequirdeByFromDateTxt(checkNull(String
							.valueOf(data[0][9])));
			bean.setRequirdeByToDateTxt(checkNull(String.valueOf(data[0][10])));
		}

	}

	/**
	 * set the data of reqution textarea 
	 * @param bean
	 */

	public void callSavedReqData(OpenVacReport bean) {
		String sqlAdvance = " SELECT VACREP_REQS_CODE FROM HRMS_REC_VACREP_REQS WHERE VACREP_CODE ="
				+ bean.getSearchSetting();
		Object[][] data = getSqlModel().getSingleResult(sqlAdvance);

		if (data != null && data.length > 0) {
			bean.setSelectedReq(String.valueOf(data[0][0]));
			String reqSql = "SELECT  REQS_NAME FROM HRMS_REC_REQS_HDR WHERE REQS_CODE IN("
					+ String.valueOf(data[0][0]) + ")";
			Object[][] reqData = getSqlModel().getSingleResult(reqSql);
			if (reqData != null && reqData.length > 0) {
				String reqCode = "";
				for (int i = 0; i < reqData.length; i++) {
					if (i < reqData.length - 1) {
						reqCode += String.valueOf(reqData[i][0]) + ",";
					} else {
						reqCode += String.valueOf(reqData[i][0]);
					}
				}
				bean.setSelectedReqName(reqCode);
				bean.setHidSelectedReqName(reqCode);

			}
		}
	}

	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} // end of checkNull

	public Object[][] checkDuplicate(OpenVacReport bean) {
		String sql = " SELECT  VACREP_NAME ,VACREP_CODE FROM HRMS_REC_VACREP_FILTERS WHERE UPPER(VACREP_NAME) ='"
				+ bean.getSettingName().toUpperCase()
				+ "' AND VACREP_USEREMPID=" + bean.getUserEmpId();
		System.out.println("sql-------->" + sql);
		return getSqlModel().getSingleResult(sql);

	}

	public static String callStringBreak(String strVal, int breakLen) {
		String strBreak = "";
		int numOfBreak = 0;
		if (strVal != null && strVal.length() > 0 && strVal.length() > breakLen) {
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
		return strBreak;
	}

}
