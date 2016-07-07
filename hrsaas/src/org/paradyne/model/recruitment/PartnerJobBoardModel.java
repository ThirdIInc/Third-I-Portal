package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.PartnerJobBoard;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0417
 * Date : 4 July
 */
public class PartnerJobBoardModel extends ModelBase {
	
	public void callPartnerJobBorad(PartnerJobBoard bean,HttpServletRequest request)
	{
		try {
			String status = request.getParameter("status");
			if (status != null && status.equals("C")) {
				bean.setIsClose("true");
			}
			String sql = " SELECT REQS_NAME,HRMS_RANK.RANK_NAME,NVL(CENTER_NAME,' '), (NVL(HRMS_REC_VACPUB_CONSDTL.PUB_ASG_VAC,0) - NVL(HRMS_REC_VACPUB_CONSDTL.PUB_CLOSE_VAC,0)), "
					+ " HRMS_REC_VACPUB_CONSDTL.PUB_ASG_VAC, HRMS_REC_REQS_HDR.REQS_CODE ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,REQS_HIRING_MANAGER	FROM HRMS_REC_VACPUB_CONSDTL "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE=HRMS_REC_VACPUB_CONSDTL.PUB_CODE ) "
					+ " LEFT JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE ) "
					+ " LEFT JOIN HRMS_REC_VACPUB_RECDTL ON(HRMS_REC_VACPUB_RECDTL.PUB_CODE =HRMS_REC_VACPUB_HDR.PUB_CODE )  "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_REC_REQS_HDR.REQS_BRANCH) "
					+ "  LEFT JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
					+ " WHERE PUB_CONS_ID ="
					+ bean.getUserEmpId()
					+ " AND PUB_VAC_STATUS !='C'";
			Object[][] data = getSqlModel().getSingleResult(sql);
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
			if (data != null && data.length > 0) {
				ArrayList<PartnerJobBoard> jobList = new ArrayList<PartnerJobBoard>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					PartnerJobBoard bean1 = new PartnerJobBoard();
					bean1.setItReqName(String.valueOf(data[i][0]));
					bean1.setItPosition(String.valueOf(data[i][1]));
					bean1.setItLocation(checkNull(String.valueOf(data[i][2])));
					bean1.setItNoOfOpenVac(String.valueOf(data[i][3]));
					bean1.setItTotalNoPostVac(String.valueOf(data[i][4])); // its hard coded do after
					bean1.setItReqCode(String.valueOf(data[i][5]));
					bean1.setItHiringManager(String.valueOf(data[i][6]));
					bean1.setItHiringManagerId(String.valueOf(data[i][7]));
					jobList.add(bean1);
				}
				bean.setPartnerJobList(jobList);
			} else {
				bean.setNoData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} 
	
	/**
	 * @purpose : To retrive and Requisition header information
	 */
	public void searchHdrRec(PartnerJobBoard manPowerReqs,
			HttpServletRequest request) {
		try {
			String query = "SELECT NVL(REQS_NAME,' '),REQS_POSITION,NVL(RANK_NAME,' '),DECODE(REQS_STATUS,'O','Open','C','Close'),"
					+ " REQS_DIVISION,NVL(DIV_NAME,' '),REQS_BRANCH,NVL(CENTER_NAME,' '),REQS_DEPT,NVL(DEPT_NAME,' '),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
					+ " REQS_HIRING_MANAGER,OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME,REQS_RECTYPE_INT,"
					+ " REQS_APPLIED_BY,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,NVL(REQS_JOBDESC_NAME,' '),"
					+ " NVL(REQS_JOB_DESCRIPTION,' '),NVL(REQS_ROLE_RESPON,' '),NVL(REQS_SPECIAL_REQ,' '),NVL(REQS_PERSONEL_REQ,' '),REQS_VACAN_COMPEN,"
					+ " REQS_VACAN_EXPMIN,REQS_VACAN_EXPMAX,REQS_VACAN_TYPE,REQS_VACAN_CONTRACT,"
					+ " REQS_VACAN_CONTYPE,REQS_APPR_CODE,REQS_LEVEL,REQS_RECTYPE_EXT,NVL(TO_CHAR(REQS_DATE,'DD-MM-YYYY'),' '),REQS_REPLACE_EMP,"
					+ " JOB_DESC_MIN_RECRUIT_DAYS, JOB_DESC_CODE FROM HRMS_REC_REQS_HDR "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY "
					+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION "
					+ " LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION "
					+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT "
					+ " LEFT JOIN HRMS_JOB_DESCRIPTION ON HRMS_JOB_DESCRIPTION.JOB_DESC_CODE = HRMS_REC_REQS_HDR.REQS_JOB_DESC_CODE "
					+ " LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH "
					+ " WHERE REQS_CODE=" + manPowerReqs.getReqCode();
			Object[][] reqHdrDet = getSqlModel().getSingleResult(query);
			if (reqHdrDet != null && reqHdrDet.length > 0) {
				manPowerReqs.setReqName(String.valueOf(reqHdrDet[0][0]));
				manPowerReqs
						.setReqPositionCode(String.valueOf(reqHdrDet[0][1]));
				manPowerReqs
						.setReqPositionName(String.valueOf(reqHdrDet[0][2]));
				manPowerReqs.setReqStatus(String.valueOf(reqHdrDet[0][3]));
				manPowerReqs.setReqDivCode(String.valueOf(reqHdrDet[0][4]));
				manPowerReqs.setReqDiv(String.valueOf(reqHdrDet[0][5]));
				if (String.valueOf(reqHdrDet[0][6]).equals("null")) {
					manPowerReqs.setReqBrnCode("");
					manPowerReqs.setReqBrn("");
				} else {
					manPowerReqs.setReqBrnCode(String.valueOf(reqHdrDet[0][6]));
					manPowerReqs.setReqBrn(String.valueOf(reqHdrDet[0][7]));
				}
				if (String.valueOf(reqHdrDet[0][8]).equals("null")) {
					manPowerReqs.setReqDeptCode("");
					manPowerReqs.setReqDept("");
				} else {
					manPowerReqs
							.setReqDeptCode(String.valueOf(reqHdrDet[0][8]));
					manPowerReqs.setReqDept(String.valueOf(reqHdrDet[0][9]));
				}

				if (String.valueOf(reqHdrDet[0][10]).equals("")
						|| String.valueOf(reqHdrDet[0][10]).equals("null")) {
					manPowerReqs.setReqApprStatus("");
				} else {
					manPowerReqs.setReqApprStatus(String.valueOf(reqHdrDet[0][10]));
					manPowerReqs.setHiddenApproveStatus(String.valueOf(reqHdrDet[0][10]));
				}
				manPowerReqs.setHiringcode(String.valueOf(reqHdrDet[0][11]));
				manPowerReqs.setHiringManager(String.valueOf(reqHdrDet[0][12]));
				if (String.valueOf(reqHdrDet[0][13]).equals("Y")) {
					manPowerReqs.setInternal("true");
				} else {
					manPowerReqs.setInternal("false");
				}
				request.setAttribute("int", manPowerReqs.getInternal());
				manPowerReqs.setAppliedBy(String.valueOf(reqHdrDet[0][14]));

				if (String.valueOf(reqHdrDet[0][16]).equals("null")
						|| String.valueOf(reqHdrDet[0][16]).equals("")) {
					manPowerReqs.setJdDescName(String.valueOf(""));
				} else {
					manPowerReqs
							.setJdDescName(String.valueOf(reqHdrDet[0][16]));
				}

				if (String.valueOf(reqHdrDet[0][17]).equals("null")
						|| String.valueOf(reqHdrDet[0][17]).equals("")) {
					manPowerReqs.setJdDesc(String.valueOf(""));
				} else {
					manPowerReqs.setJdDesc(String.valueOf(reqHdrDet[0][17]));
				}

				if (String.valueOf(reqHdrDet[0][18]).equals("null")
						|| String.valueOf(reqHdrDet[0][18]).equals("")) {
					manPowerReqs.setJdRoleDesc(String.valueOf(""));
				} else {
					manPowerReqs
							.setJdRoleDesc(String.valueOf(reqHdrDet[0][18]));
				}

				if (String.valueOf(reqHdrDet[0][19]).equals("null")
						|| String.valueOf(reqHdrDet[0][19]).equals("")) {
					manPowerReqs.setSpecialReq(String.valueOf(""));
				} else {
					manPowerReqs
							.setSpecialReq(String.valueOf(reqHdrDet[0][19]));
				}

				if (String.valueOf(reqHdrDet[0][20]).equals("null")
						|| String.valueOf(reqHdrDet[0][20]).equals("")) {
					manPowerReqs.setPersoanlReq(String.valueOf(""));
				} else {
					manPowerReqs.setPersoanlReq(String
							.valueOf(reqHdrDet[0][20]));
				}

				// manPowerReqs.setJdDesc(String.valueOf(reqHdrDet[0][17]));
				// manPowerReqs.setJdRoleDesc(String.valueOf(reqHdrDet[0][18]));
				// manPowerReqs.setSpecialReq(String.valueOf(reqHdrDet[0][19]));
				// manPowerReqs.setPersoanlReq(String.valueOf(reqHdrDet[0][20]));
				if (String.valueOf(reqHdrDet[0][21]).equals("null")) {
					manPowerReqs.setReqCompensation("");
				} else {
					manPowerReqs.setReqCompensation(String.valueOf(reqHdrDet[0][21]));
				}
					
				if (String.valueOf(reqHdrDet[0][22]).equals("null")) {
					manPowerReqs.setMinExp("");
				} else {
					manPowerReqs.setMinExp(String.valueOf(reqHdrDet[0][22]));
				}
					
				if (String.valueOf(reqHdrDet[0][23]).equals("null")) {
					manPowerReqs.setMaxExp("");
				} else {
					manPowerReqs.setMaxExp(String.valueOf(reqHdrDet[0][23]));
				}
					
				if (String.valueOf(reqHdrDet[0][24]).equals("null")) {
					manPowerReqs.setVacType("");
				} else {
					if (manPowerReqs.getFlagView().equals("true")) {
						if (String.valueOf(reqHdrDet[0][24]).equals("N")) {
							manPowerReqs.setNewPostComment(checkNull(String.valueOf(reqHdrDet[0][31])));
							manPowerReqs.setVacType(String.valueOf("New Post"));
						} else if (String.valueOf(reqHdrDet[0][24]).equals("R")) {
							manPowerReqs.setVacType(String.valueOf("Replacement"));
							manPowerReqs.setReplaceEmpId(String.valueOf(reqHdrDet[0][31]));
							getRemoveList(manPowerReqs);
						} else {
							manPowerReqs.setVacType(String.valueOf(""));
						}

					} else {
						manPowerReqs.setVacType(String
								.valueOf(reqHdrDet[0][24]));
						if (String.valueOf(reqHdrDet[0][24]).equals("N")) {
							manPowerReqs.setNewPostComment(checkNull(String.valueOf(reqHdrDet[0][31])));
						} else if (String.valueOf(reqHdrDet[0][24]).equals("R")) {
							manPowerReqs.setReplaceEmpId(String.valueOf(reqHdrDet[0][31]));
							getRemoveList(manPowerReqs);
						}
					}
				}

				if (String.valueOf(reqHdrDet[0][25]).equals("null")) {
					manPowerReqs.setReqConType("");
				} else {
					if (manPowerReqs.getFlagView().equals("true")) {
						if (String.valueOf(reqHdrDet[0][25]).equals("R")) {
							manPowerReqs.setReqConType(String.valueOf("Regular"));
						} else if (String.valueOf(reqHdrDet[0][25]).equals("O")) {
							manPowerReqs.setReqConType(String.valueOf("On Contract"));
						} else {
							manPowerReqs.setReqConType(String.valueOf(""));
						}
					} else {
						manPowerReqs.setReqConType(String.valueOf(reqHdrDet[0][25]));
					}
				}

				if (String.valueOf(reqHdrDet[0][26]).equals("null")) {
					manPowerReqs.setReqPartFull("");
				} else {
					if (manPowerReqs.getFlagView().equals("true")) {
						if (String.valueOf(reqHdrDet[0][26]).equals("P")) {
							manPowerReqs.setReqPartFull(String.valueOf("Part Time"));
						} else if (String.valueOf(reqHdrDet[0][26]).equals("F")) {
							manPowerReqs.setReqPartFull(String.valueOf("Full Time"));
						} else {
							manPowerReqs.setReqPartFull(String.valueOf(""));
						}
					} else {
						manPowerReqs.setReqPartFull(String.valueOf(reqHdrDet[0][26]));
					}
				}
				if (String.valueOf(reqHdrDet[0][29]).equals("Y")) {
					manPowerReqs.setExternal("true");
				} else {
					manPowerReqs.setExternal("false");
				}
				request.setAttribute("ext", manPowerReqs.getExternal());
				manPowerReqs.setReqDt(String.valueOf(reqHdrDet[0][30]));

				if (String.valueOf(reqHdrDet[0][32]).equals("null")) {
					manPowerReqs.setMinReuiredDays("");
				} else {
					manPowerReqs.setMinReuiredDays(String.valueOf(reqHdrDet[0][32]));
				}

				if (String.valueOf(reqHdrDet[0][33]).equals("null")) {
					manPowerReqs.setJdCode("");
				} else {
					manPowerReqs.setJdCode(String.valueOf(reqHdrDet[0][33]));
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @purpose : To retrive and Requisition header information
	 */
	public void searchVacDtl(PartnerJobBoard manPowerReqs) {
		try {
			String query = "SELECT VACAN_DTL_CODE,NVL(VACAN_NUMBERS,0),NVL(TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),' ') FROM HRMS_REC_REQS_VACDTL"
					+ " WHERE REQS_CODE=" + manPowerReqs.getReqCode();
			Object[][] vacDets = getSqlModel().getSingleResult(query);
			if (vacDets != null) {
				ArrayList<PartnerJobBoard> list = new ArrayList<PartnerJobBoard>();
				for (int i = 0; i < vacDets.length; i++) {
					PartnerJobBoard loopBean = new PartnerJobBoard();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setVacDetCode(String.valueOf(String
							.valueOf(vacDets[i][0])));
					loopBean.setNoOfVac(String.valueOf(
							String.valueOf(vacDets[i][1])).trim());
					loopBean.setVacDate(String.valueOf(
							String.valueOf(vacDets[i][2])).trim());
					list.add(loopBean);
				}
				manPowerReqs.setVacList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * following function is called to display the approver name,date and
	 * remarks when a record gets approved
	 * 
	 * @param bean
	 */
	public void getApprovalDtls(PartnerJobBoard bean) {
		try {
			String query = "  SELECT DISTINCT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(TO_CHAR(PATH_APPR_DATE,'DD-MM-YYYY'),' '),"
					+ " NVL(HRMS_REC_REQS_PATH.PATH_REMARK,' '),DECODE(PATH_STATUS,'A','Approved','R','Rejected','H','On Hold'),PATH_APPR_DATE"
					+ " FROM HRMS_REC_REQS_PATH "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_REQS_PATH.PATH_REQ_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_REQS_PATH.PATH_APPROVER_CODE)"
					+ " WHERE REQS_CODE=" + bean.getReqCode() + " ORDER BY PATH_APPR_DATE ";
			Object[][] data = getSqlModel().getSingleResult(query);
			ArrayList<PartnerJobBoard> list = new ArrayList<PartnerJobBoard>();
			if (data != null && data.length > 0) {
				bean.setApprvFlag("true");
				for (int i = 0; i < data.length; i++) {
					PartnerJobBoard bean1 = new PartnerJobBoard();
					// bean1.setSrNo(String.valueOf(data[i][0]));//Sr No
					bean1.setApprvName(String.valueOf(data[i][0]));// Approver Name
					bean1.setApprvDate(String.valueOf(data[i][1]));// Approved Date
					bean1.setApprvStat(String.valueOf(data[i][3]));// Approver Status
					bean1.setApprvRem(String.valueOf(data[i][2]));// Approver Remark
					list.add(bean1);
				}
				bean.setApprvList(list);
			} else {
				bean.setApprvFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] checkStatus(PartnerJobBoard bean, String code) {
		Object[][] data = null;
		try {
			String query = "SELECT DECODE(REQS_STATUS,'O','Open','C','Close'),REQS_NAME,REQS_APPROVAL_STATUS FROM HRMS_REC_REQS_HDR WHERE REQS_CODE="
					+ code;
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void getRemoveList(PartnerJobBoard manPowerReqs) {
		Object data[][] = null;
		String empName = "";
		try {
			String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID IN (" + manPowerReqs.getReplaceEmpId() + ")";
			data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					empName += String.valueOf(data[i][0]) + "-"
							+ String.valueOf(data[i][1]) + ",";
				} 
				empName = empName.substring(0, empName.length() - 1);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		manPowerReqs.setReplaceEmpName(empName);
	} 

}
