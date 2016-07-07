package org.paradyne.model.TravelManagement.TravelProcess;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlMangAuthorities;
import org.paradyne.lib.ModelBase;

/**
 * @author aa0651
 * 
 */
public class TmsTrvlMangAuthoritiesModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * To save records
	 */

	public boolean saveAuth(TmsTrvlMangAuthorities bean,
			HttpServletRequest request, String overWrite, String isSave) {
		boolean result = false;
		try {
			String empCode[] = request.getParameterValues("itEmployeeCode");

			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {
					System.out.println("Value Of empCode[i]---------------"
							+ empCode[i]);
				}
			}
			Object[][] add = null;
			Object[][] addObj = null;

			boolean res = false;

			Object maxCodeObj[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(AUTH_ID),0) FROM TMS_MANG_AUTH_HDR");
			int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

			Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(AUTH_DTL_ID),0)+1 FROM TMS_MNG_AUTH_DTL");
			int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);

			add = new Object[1][14];
			add[0][0] = ++maxCode;
			if (bean.getHidAppFlag().equals("Y")) {
				add[0][1] = "Y";
			} else {
				add[0][1] = "N";
			}

			add[0][2] = checkNull(bean.getBranchCode().trim());
			add[0][3] = checkNull(bean.getMainSchdlrCode().trim());
			add[0][4] = checkNull(bean.getAltMainSchdlrCode().trim());
			add[0][5] = checkNull(bean.getDescriptionNew().trim());
			add[0][6] = checkNull(bean.getStatus());
			add[0][7] = checkNull(bean.getSchdlrApprCode().trim());
			add[0][8] = checkNull(bean.getAccntCode().trim());

			// ADDED BY REEBA
			add[0][9] = checkNull(bean.getAlterAccountantCode().trim());
			add[0][10] = checkNull(bean.getEscalationEmployeeCode().trim());
			add[0][11] = checkNull(bean.getAccountantEmailID().trim());
			if (bean.getHiddenclaimwrkflowflag().equals("N")) {
				add[0][12] = "N";
			} else {
				add[0][12] = "Y";
			}
			//add[0][12]=checkNull(bean.getClaimwrkflowflag().trim());
			if (bean.getHiddenackwrkflowflag().equals("N")) {
				add[0][13] = "N";
			} else {
				add[0][13] = "Y";
			}
			//add[0][13]=checkNull(bean.getAckwrkflowflag().trim());

			result = getSqlModel().singleExecute(getQuery(1), add);

			if (result) {
				String maxQuery = "SELECT MAX(AUTH_ID) FROM TMS_MANG_AUTH_HDR";
				Object[][] data = getSqlModel().getSingleResult(maxQuery);
				bean.setMangAuthCode(String.valueOf(data[0][0]));

				if (empCode != null && empCode.length > 0) {

					addObj = new Object[empCode.length][7];
					for (int z = 0; z < empCode.length; z++) {
						addObj[z][0] = data[0][0];
						addObj[z][1] = maxCodeDtl;
						addObj[z][2] = empCode[z];
						addObj[z][3] = "N";
						addObj[z][4] = "N";
						addObj[z][5] = "N";
						addObj[z][6] = "S";
						maxCodeDtl++;
					}
					result = getSqlModel().singleExecute(getQuery(2), addObj);
				}

				if (overWrite != null && !overWrite.equals("null")
						&& overWrite.equals("true")) {
					String dtlQuery = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID !="
							+ bean.getMangAuthCode();
					getSqlModel().singleExecute(dtlQuery);
					String hdrQuery = "DELETE FROM TMS_MANG_AUTH_HDR WHERE AUTH_ID !="
							+ bean.getMangAuthCode();
					getSqlModel().singleExecute(hdrQuery);
				} else if (overWrite != null && !overWrite.equals("null")
						&& overWrite.equals("false")) {
					String upQuery = "UPDATE TMS_MANG_AUTH_HDR SET AUTH_ALL_BRNCH='N' WHERE AUTH_ID != "
							+ String.valueOf(data[0][0]);
					getSqlModel().singleExecute(upQuery);
				}
				try {

					saveAccountantDtl(bean, request, overWrite, isSave);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveAccountantDtl(TmsTrvlMangAuthorities bean,
			HttpServletRequest request, String overWrite, String isSave) {
		boolean result = false;
		try {

			System.out.println("In save accountant dtl-------------------");
			Object[][] add = null;
			Object[][] addObj = null;
			result = false;
			boolean res = false;

			int maxCodeDtl = 0;

			Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(AUTH_DTL_ID),0)+1 FROM TMS_MNG_AUTH_DTL");
			maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);

			String[] empCode = request.getParameterValues("itEmployeeCodeAcc");

			String[] acknoledge = (String[]) request
					.getParameterValues("hiddenitAcknoledge");

			String[] advance = (String[]) request
					.getParameterValues("hiddenitAdvance");

			String[] claim = (String[]) request
					.getParameterValues("hiddenittClaim");

			String deleteQuery = " DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID="
					+ bean.getMangAuthCode() + " AND AUTH_DTL_TYPE='A' ";
			System.out.println("bean.getMangAuthCode()  "
					+ bean.getMangAuthCode());
			result = getSqlModel().singleExecute(deleteQuery);

			if (empCode != null && empCode.length > 0) {

				addObj = new Object[empCode.length][10];
				for (int z = 0; z < empCode.length; z++) {

					addObj[z][0] = bean.getMangAuthCode();
					addObj[z][1] = maxCodeDtl;
					addObj[z][2] = empCode[z];
					addObj[z][3] = "N";
					addObj[z][4] = "N";
					addObj[z][5] = "N";
					addObj[z][6] = acknoledge[z];
					addObj[z][7] = advance[z];
					addObj[z][8] = claim[z];
					addObj[z][9] = "A";
					maxCodeDtl++;
				}
				result = getSqlModel().singleExecute(getQuery(6), addObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveAuth_Old(TmsTrvlMangAuthorities bean,
			HttpServletRequest request, String overWrite) {

		Object[][] add = null;
		Object[][] addObj = null;
		boolean result = false;
		boolean res = false;
		Object maxCodeObj[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(AUTH_ID),0) FROM TMS_MANG_AUTH_HDR");
		int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

		Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(AUTH_DTL_ID),0) FROM TMS_MNG_AUTH_DTL");
		int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);

		add = new Object[1][12];
		add[0][0] = ++maxCode;
		if (bean.getHidAppFlag().equals("Y")) {
			add[0][1] = "Y";
		} else {
			add[0][1] = "N";
		}

		add[0][2] = checkNull(bean.getBranchCode().trim());
		add[0][3] = checkNull(bean.getMainSchdlrCode().trim());
		add[0][4] = checkNull(bean.getAltMainSchdlrCode().trim());
		add[0][5] = checkNull(bean.getDescriptionNew().trim());
		add[0][6] = checkNull(bean.getStatus());
		add[0][7] = checkNull(bean.getSchdlrApprCode().trim());
		add[0][8] = checkNull(bean.getAccntCode().trim());

		// ADDED BY REEBA
		add[0][9] = checkNull(bean.getAlterAccountantCode().trim());
		add[0][10] = checkNull(bean.getEscalationEmployeeCode().trim());
		add[0][11] = checkNull(bean.getAccountantEmailID().trim());

		result = getSqlModel().singleExecute(getQuery(1), add);

		if (result) {
			String maxQuery = "SELECT MAX(AUTH_ID) FROM TMS_MANG_AUTH_HDR";
			Object[][] data = getSqlModel().getSingleResult(maxQuery);
			bean.setMangAuthCode(String.valueOf(data[0][0]));
			if (overWrite != null && !overWrite.equals("null")
					&& overWrite.equals("true")) {
				String dtlQuery = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID !="
						+ bean.getMangAuthCode();
				getSqlModel().singleExecute(dtlQuery);
				String hdrQuery = "DELETE FROM TMS_MANG_AUTH_HDR WHERE AUTH_ID !="
						+ bean.getMangAuthCode();
				getSqlModel().singleExecute(hdrQuery);
			} else if (overWrite != null && !overWrite.equals("null")
					&& overWrite.equals("false")) {
				String upQuery = "UPDATE TMS_MANG_AUTH_HDR SET AUTH_ALL_BRNCH='N' WHERE AUTH_ID != "
						+ String.valueOf(data[0][0]);
				getSqlModel().singleExecute(upQuery);
			}
			String myHidden = "";
			bean.getMyHidden();
			if (bean.getMyHidden() != null && !bean.getMyHidden().equals("")) {
				myHidden += bean.getMyHidden();
				logger.info("-------myHidden---------" + myHidden);
				String s[] = myHidden.split(",");
				Object[] empCode;
				Object[] travelFlag;
				Object[] convFlags;
				Object[] lodgeFlags;
				addObj = new Object[s.length][6];
				logger.info("==========s======length=========" + s.length);
				if (s != null && s.length > 0) {
					empCode = new Object[s.length];
					travelFlag = new Object[s.length];
					convFlags = new Object[s.length];
					lodgeFlags = new Object[s.length];

					for (int j = 0; j < s.length; j++) {
						String ecodes[] = s[j].split("#");
						empCode[j] = ecodes[0];
						travelFlag[j] = ecodes[1];
						convFlags[j] = ecodes[2];
						lodgeFlags[j] = ecodes[3];
					}
					int z = 0;
					if (empCode != null) {
						for (int i = 0; i < s.length; i++) {
							addObj[z][0] = data[0][0];
							addObj[z][1] = ++maxCodeDtl;
							addObj[z][2] = empCode[i];
							addObj[z][3] = travelFlag[i];
							addObj[z][4] = convFlags[i];
							addObj[z][5] = lodgeFlags[i];
							z++;

						}
						result = getSqlModel().singleExecute(getQuery(2),
								addObj);
					}

				}
			} else if (bean.getMyHidden().equals("")) {
				int k = 0;
				String query = "SELECT AUTH_DTL_SUB_SCH_ID,"
						+ " AUTH_DTL_TRAVELFLAG, AUTH_DTL_CONVFLAG, AUTH_DTL_LODGEFLAG"
						+ " FROM TMS_MNG_AUTH_DTL"
						// + " LEFT JOIN HRMS_EMP_OFFC
						// ON(HRMS_EMP_OFFC.EMP_ID=TMS_MNG_AUTH_DTL.AUTH_DTL_SUB_SCH_ID)"
						+ " WHERE AUTH_ID=(SELECT AUTH_ID FROM TMS_MANG_AUTH_HDR WHERE AUTH_ALL_BRNCH='Y')";
				Object queryData[][] = getSqlModel().getSingleResult(query);
				Object[][] addObjDtl = new Object[queryData.length][6];
				if (queryData != null && queryData.length > 0) {
					for (int i = 0; i < queryData.length; i++) {
						addObjDtl[k][0] = bean.getMangAuthCode();
						addObjDtl[k][1] = ++maxCodeDtl;
						addObjDtl[k][2] = queryData[i][0];
						addObjDtl[k][3] = queryData[i][1];
						addObjDtl[k][4] = queryData[i][2];
						addObjDtl[k][5] = queryData[i][3];
						k++;

					}
					result = getSqlModel()
							.singleExecute(getQuery(2), addObjDtl);
				}
			}
		}
		return result;

	}

	/*
	 * To update records
	 */
	public boolean updateAuth(TmsTrvlMangAuthorities bean,
			HttpServletRequest request, String overWrite, String isSave) {
		boolean result = false;
		try {

			System.out.println("In update Logic-------------");

			String empCode[] = request.getParameterValues("itEmployeeCode");
			String authCode = bean.getMangAuthCode();
			Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(AUTH_DTL_ID),0) FROM TMS_MNG_AUTH_DTL");
			int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);
			Object[][] add = null;
			Object[][] addObj = null;
			add = new Object[1][14];
			add[0][0] = bean.getHidAppFlag();
			add[0][1] = checkNull(bean.getBranchCode().trim());
			add[0][2] = checkNull(bean.getMainSchdlrCode().trim());
			add[0][3] = checkNull(bean.getAltMainSchdlrCode().trim());
			add[0][4] = checkNull(bean.getDescriptionNew().trim());
			add[0][5] = checkNull(bean.getStatus());
			add[0][6] = checkNull(bean.getSchdlrApprCode().trim());
			add[0][7] = checkNull(bean.getAccntCode().trim());
			// ADDED BY REEBA
			add[0][8] = checkNull(bean.getAlterAccountantCode().trim());
			add[0][9] = checkNull(bean.getEscalationEmployeeCode().trim());
			add[0][10] = checkNull(bean.getAccountantEmailID().trim());
			add[0][11]=checkNull(bean.getHiddenclaimwrkflowflag().trim());			
			add[0][12]=checkNull(bean.getHiddenackwrkflowflag().trim());
			add[0][13] = checkNull(bean.getMangAuthCode().trim());
			result = getSqlModel().singleExecute(getQuery(5), add);
			if (result) {
				logger.info("-------overWrite------value----" + overWrite);
				if (overWrite != null && !overWrite.equals("null")
						&& overWrite.equals("true")) {
					String dtlQuery = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID !="
							+ bean.getMangAuthCode();
					getSqlModel().singleExecute(dtlQuery);
					String hdrQuery = "DELETE FROM TMS_MANG_AUTH_HDR WHERE AUTH_ID !="
							+ bean.getMangAuthCode();
					getSqlModel().singleExecute(hdrQuery);
				} else if (overWrite != null && !overWrite.equals("null")
						&& overWrite.equals("false")) {
					String upQuery = "UPDATE TMS_MANG_AUTH_HDR SET AUTH_ALL_BRNCH='N' WHERE AUTH_ID != "
							+ bean.getMangAuthCode();
					getSqlModel().singleExecute(upQuery);
				}

				String delSubSch = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_DTL_TYPE='S' AND AUTH_ID="
						+ authCode;
				getSqlModel().singleExecute(delSubSch);

				int z = 0;
				if (empCode != null && empCode.length > 0) {
					addObj = new Object[empCode.length][7];
					for (int i = 0; i < empCode.length; i++) {
						addObj[z][0] = bean.getMangAuthCode();
						addObj[z][1] = ++maxCodeDtl;
						addObj[z][2] = empCode[i];
						addObj[z][3] = "N";
						addObj[z][4] = "N";
						addObj[z][5] = "N";
						addObj[z][6] = "S";

						z++;

					}
					result = getSqlModel().singleExecute(getQuery(2), addObj);
				}

				try {
					saveAccountantDtl(bean, request, overWrite, isSave);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/*
	 * To update records
	 */
	public boolean updateAuth_OLD(TmsTrvlMangAuthorities bean,
			HttpServletRequest request, String overWrite) {
		boolean result = false;

		String authCode = bean.getMangAuthCode();

		Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(AUTH_DTL_ID),0) FROM TMS_MNG_AUTH_DTL");
		int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);

		Object[][] add = null;
		Object[][] addObj = null;

		add = new Object[1][12];
		add[0][0] = bean.getHidAppFlag();
		add[0][1] = checkNull(bean.getBranchCode().trim());
		add[0][2] = checkNull(bean.getMainSchdlrCode().trim());
		add[0][3] = checkNull(bean.getAltMainSchdlrCode().trim());
		add[0][4] = checkNull(bean.getDescriptionNew().trim());
		add[0][5] = checkNull(bean.getStatus());
		add[0][6] = checkNull(bean.getSchdlrApprCode().trim());
		add[0][7] = checkNull(bean.getAccntCode().trim());
		// ADDED BY REEBA
		add[0][8] = checkNull(bean.getAlterAccountantCode().trim());
		add[0][9] = checkNull(bean.getEscalationEmployeeCode().trim());
		add[0][10] = checkNull(bean.getAccountantEmailID().trim());

		add[0][11] = checkNull(bean.getMangAuthCode().trim());
		result = getSqlModel().singleExecute(getQuery(5), add);
		if (result) {
			logger.info("-------overWrite------value----" + overWrite);
			if (overWrite != null && !overWrite.equals("null")
					&& overWrite.equals("true")) {
				String dtlQuery = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID !="
						+ bean.getMangAuthCode();
				getSqlModel().singleExecute(dtlQuery);
				String hdrQuery = "DELETE FROM TMS_MANG_AUTH_HDR WHERE AUTH_ID !="
						+ bean.getMangAuthCode();
				getSqlModel().singleExecute(hdrQuery);
			} else if (overWrite != null && !overWrite.equals("null")
					&& overWrite.equals("false")) {
				String upQuery = "UPDATE TMS_MANG_AUTH_HDR SET AUTH_ALL_BRNCH='N' WHERE AUTH_ID != "
						+ bean.getMangAuthCode();
				getSqlModel().singleExecute(upQuery);
			}
			logger.info("-----sub length-------" + bean.getSubTabLength());

			String myHidden = "";
			bean.getMyHidden();

			System.out
					.println("Value Of bean.getMyHidden()                           "
							+ bean.getMyHidden());
			if (bean.getMyHidden() != null && !bean.getMyHidden().equals("")) {
				myHidden += bean.getMyHidden();
				String s[] = myHidden.split(",");
				Object[] empCode;
				Object[] travelFlag;
				Object[] convFlags;
				Object[] lodgeFlags;
				addObj = new Object[s.length][6];
				logger.info("==========s======length=========" + s.length);
				if (s != null && s.length > 0) {
					empCode = new Object[s.length];
					travelFlag = new Object[s.length];
					convFlags = new Object[s.length];
					lodgeFlags = new Object[s.length];

					for (int j = 0; j < s.length; j++) {
						String ecodes[] = s[j].split("#");
						empCode[j] = ecodes[0];
						travelFlag[j] = ecodes[1];
						convFlags[j] = ecodes[2];
						lodgeFlags[j] = ecodes[3];
					}
					int z = 0;
					if (empCode != null) {
						for (int i = 0; i < s.length; i++) {
							addObj[z][0] = bean.getMangAuthCode();
							addObj[z][1] = ++maxCodeDtl;
							addObj[z][2] = empCode[i];
							addObj[z][3] = travelFlag[i];
							addObj[z][4] = convFlags[i];
							addObj[z][5] = lodgeFlags[i];
							z++;

						}
						String delSubSch = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID="
								+ authCode;
						getSqlModel().singleExecute(delSubSch);

						result = getSqlModel().singleExecute(getQuery(2),
								addObj);
					}
				}
			} else if (bean.getMyHidden().equals("")
					&& bean.getSubTabLength().equals("0")) {
				String delSubSch = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID="
						+ authCode;
				getSqlModel().singleExecute(delSubSch);
			}

		}
		return result;

	}

	/*
	 * To view sub scheduler
	 */
	public void viewSubSchdlr(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ " DECODE (AUTH_DTL_TRAVELFLAG,'N','NO','Y','YES') ,DECODE (AUTH_DTL_LODGEFLAG,'N','NO','Y','YES'), DECODE (AUTH_DTL_CONVFLAG,'N','NO','Y','YES')"
				+ " FROM TMS_MNG_AUTH_DTL"
				+ " LEFT JOIN HRMS_EMP_OFFC  ON(HRMS_EMP_OFFC.EMP_ID=TMS_MNG_AUTH_DTL.AUTH_DTL_SUB_SCH_ID)"
				+ " WHERE AUTH_ID=" + trvlAuth.getMangAuthCode();
		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> obj = new ArrayList<Object>();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				// setting
				TmsTrvlMangAuthorities bean1 = new TmsTrvlMangAuthorities();
				bean1.setViewSrNo(String.valueOf(obj.size() + 1));
				bean1.setItViewEmpToken(String.valueOf(data[i][0]).trim());
				bean1.setItViewEmpName(String.valueOf(data[i][1]));
				bean1.setItViewTrvl(String.valueOf(data[i][2]));
				bean1.setItViewLodge(String.valueOf(data[i][3]));
				bean1.setItViewConv(String.valueOf(data[i][4]));

				obj.add(bean1);
			}
			trvlAuth.setViewMangAuthList(obj);

		}

	}

	public void getSavedAccountant(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ " AUTH_DTL_TRAVELFLAG, AUTH_DTL_LODGEFLAG, AUTH_DTL_CONVFLAG,AUTH_DTL_SUB_SCH_ID"
				+ " ,AUTH_DTL_ACKNOLEDGEFLAG, AUTH_DTL_ADVANCEFLAG, AUTH_DTL_CLAIMFLAG FROM TMS_MNG_AUTH_DTL"
				+ " LEFT JOIN HRMS_EMP_OFFC  ON(HRMS_EMP_OFFC.EMP_ID=TMS_MNG_AUTH_DTL.AUTH_DTL_SUB_SCH_ID)"
				+ " WHERE AUTH_DTL_TYPE='A' AND AUTH_ID="
				+ trvlAuth.getMangAuthCode();
		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> obj = new ArrayList<Object>();
		if (data != null && data.length > 0) {
			logger.info("<<<<<<<<Inside if>>>>>>>>");
			for (int i = 0; i < data.length; i++) {
				// setting
				TmsTrvlMangAuthorities bean1 = new TmsTrvlMangAuthorities();

				bean1.setSrNoAcc(String.valueOf(obj.size() + 1));
				bean1.setItEmployeeTokenAcc(String.valueOf(data[i][0]).trim());
				bean1.setItEmployeeAcc(String.valueOf(data[i][1]));
				bean1.setItEmployeeCodeAcc(String.valueOf(data[i][5]));

				bean1.setHiddenitAcknoledge(String.valueOf(data[i][6]));

				bean1.setHiddenitAdvance(String.valueOf(data[i][7]));

				bean1.setHiddenittClaim(String.valueOf(data[i][8]));

				bean1
						.setItAcknoledge(bean1.getHiddenitAcknoledge().equals(
								"Y") ? "checked" : "");
				bean1
						.setItAdvance(bean1.getHiddenitAdvance().equals("Y") ? "checked"
								: "");

				System.out.println("Vishwambhar_________________              "
						+ bean1.getHiddenittClaim());
				bean1
						.setIttClaim(bean1.getHiddenittClaim().equals("Y") ? "checked"
								: "");
				obj.add(bean1);
			}
			trvlAuth.setSubAccountantList(obj);
			;

		} else {
			/*
			 * trvlAuth.setSubTabLength("0"); if
			 * (!trvlAuth.getMyHidden().equals("")) { getSubList(trvlAuth); }
			 * else { trvlAuth.setSubSchdlrList(null); }
			 */
			// trvlAuth.setNoDataSch(true);
		}

	}

	/*
	 * To retrive saved sub scheduler
	 */
	public void getSavedSubSchdlr(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ " AUTH_DTL_TRAVELFLAG, AUTH_DTL_LODGEFLAG, AUTH_DTL_CONVFLAG,AUTH_DTL_SUB_SCH_ID"
				+ " FROM TMS_MNG_AUTH_DTL"
				+ " LEFT JOIN HRMS_EMP_OFFC  ON(HRMS_EMP_OFFC.EMP_ID=TMS_MNG_AUTH_DTL.AUTH_DTL_SUB_SCH_ID)"
				+ " WHERE AUTH_DTL_TYPE='S' AND AUTH_ID="
				+ trvlAuth.getMangAuthCode();
		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> obj = new ArrayList<Object>();
		if (data != null && data.length > 0) {
			logger.info("<<<<<<<<Inside if>>>>>>>>");
			for (int i = 0; i < data.length; i++) {
				// setting
				TmsTrvlMangAuthorities bean1 = new TmsTrvlMangAuthorities();
				bean1.setSrNo(String.valueOf(obj.size() + 1));
				bean1.setItEmployeeToken(String.valueOf(data[i][0]).trim());
				bean1.setItEmployee(String.valueOf(data[i][1]));
				bean1.setItEmployeeCode(String.valueOf(data[i][5]));
				/*
				 * bean1.setHidItTravel(String.valueOf(data[i][2]));
				 * bean1.setHidItLodging(String.valueOf(data[i][3]));
				 * bean1.setHidItLocalConv(String.valueOf(data[i][4])); bean1
				 * .setItTravel(bean1.getHidItTravel().equals("Y") ? "checked" :
				 * ""); bean1
				 * .setItLocalConv(bean1.getHidItLocalConv().equals("Y") ?
				 * "checked" : ""); bean1
				 * .setItLodging(bean1.getHidItLodging().equals("Y") ? "checked" :
				 * "");
				 */
				obj.add(bean1);
			}
			trvlAuth.setSubSchdlrList(obj);

		} else {
			trvlAuth.setSubTabLength("0");
			if (!trvlAuth.getMyHidden().equals("")) {
				getSubList(trvlAuth);
			} else {
				trvlAuth.setSubSchdlrList(null);

			}
			// trvlAuth.setNoDataSch(true);

		}

	}

	/*
	 * To add sub scheduler in sub schedulers list in child window
	 */
	public void addSchdlr(TmsTrvlMangAuthorities trvlAuth,
			HttpServletRequest request) {
		// logger.info("Inside Add");
		String[] srNo = request.getParameterValues("srNo");
		String[] itEmployeeCode = request.getParameterValues("itEmployeeCode");
		String[] itEmployee = request.getParameterValues("itEmployee");
		String[] itEmployeeToken = request
				.getParameterValues("itEmployeeToken");

		ArrayList<Object> list = new ArrayList<Object>();

		if (itEmployeeCode != null) {

			for (int i = 0; i < itEmployeeCode.length; i++) {
				TmsTrvlMangAuthorities bean = new TmsTrvlMangAuthorities();
				bean.setSrNo(srNo[i]);
				bean.setItEmployee(itEmployee[i]);
				bean.setItEmployeeCode(itEmployeeCode[i]);
				bean.setItEmployeeToken(itEmployeeToken[i]);
				list.add(bean);
			}

		}
		TmsTrvlMangAuthorities bean1 = new TmsTrvlMangAuthorities();
		// logger.info("=====in else============" + trvlAuth.getEmployee());
		bean1.setSrNo(String.valueOf(list.size() + 1));
		bean1.setItEmployee(trvlAuth.getEmployee());
		bean1.setItEmployeeCode(trvlAuth.getEmployeeCode());
		bean1.setItEmployeeToken(trvlAuth.getEmployeeToken());

		list.add(bean1);

		trvlAuth.setSubSchdlrList(list);

		if (trvlAuth.getSubSchdlrList().size() == 0) {
			// trvlAuth.setSubTabLength("0");
		} else {
			// trvlAuth.setSubTabLength("1");
		}
	}

	/*
	 * To delete multiple sub schedulers from Sub scheduler list in child window
	 */
	public boolean callDelSub(TmsTrvlMangAuthorities trvlAuth, String[] code,
			String[] itEmployeeCode, String[] itEmployee,
			String[] itEmployeeToken) {

		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (itEmployeeCode != null) {
				for (int i = 0; i < itEmployeeCode.length; i++) {
					TmsTrvlMangAuthorities bean = new TmsTrvlMangAuthorities();
					if ((String.valueOf(code[i]).equals(""))) {
						bean.setSrNo("" + (i + 1));
						bean.setItEmployeeCode(itEmployeeCode[i]);
						bean.setItEmployee(itEmployee[i]);
						bean.setItEmployeeToken(itEmployeeToken[i]);
						list.add(bean);
					}

				}
			}
			trvlAuth.setSubSchdlrList(list);
			if (trvlAuth.getSubSchdlrList().size() == 0) {
				// trvlAuth.setSubTabLength("0");
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return true;

	}

	/*
	 * To delete multiple sub schedulers from Sub scheduler list in child window
	 */
	public boolean callDelAccountant(TmsTrvlMangAuthorities trvlAuth,
			String[] code, String[] itEmployeeCode, String[] itEmployee,
			String[] itEmployeeToken, String[] acknoledge, String[] advance,
			String[] claim) {

		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (itEmployeeCode != null) {
				for (int i = 0; i < itEmployeeCode.length; i++) {
					TmsTrvlMangAuthorities bean = new TmsTrvlMangAuthorities();
					if ((String.valueOf(code[i]).equals(""))) {
						bean.setSrNoAcc("" + (i + 1));
						bean.setItEmployeeCodeAcc(itEmployeeCode[i]);
						bean.setItEmployeeAcc(itEmployee[i]);
						bean.setItEmployeeTokenAcc(itEmployeeToken[i]);
						bean.setHiddenitAcknoledge(acknoledge[i]);
						bean.setHiddenitAdvance(advance[i]);
						bean.setHiddenittClaim(claim[i]);
						bean
								.setItAcknoledge(bean.getItAcknoledge().equals(
										"Y") ? "checked" : "");
						bean
								.setItAdvance(bean.getItAdvance().equals("Y") ? "checked"
										: "");
						bean
								.setIttClaim(bean.getIttClaim().equals("Y") ? "checked"
										: "");

						list.add(bean);
					}

				}
			}
			trvlAuth.setSubAccountantList(list);
			if (trvlAuth.getSubAccountantList().size() == 0) {
				// trvlAuth.setSubTabLength("0");
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return true;

	}

	/*
	 * for paging
	 */
	public void getRecords(TmsTrvlMangAuthorities bean,
			HttpServletRequest request) {
		try {

			String query = "SELECT HRMS_CENTER.CENTER_NAME,"
					+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
					+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"
					+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME,"
					+ " CASE WHEN AUTH_STATUS='A' THEN 'Active' WHEN AUTH_STATUS='D' THEN 'Deactive' ELSE ' ' END,AUTH_BRNCH_ID,"
					+ " AUTH_MAIN_SCHL_ID,AUTH_SCH_APPROVAL,AUTH_ACCOUNTENT,AUTH_ALT_MAIN_SCHL_ID,AUTH_ID,AUTH_ALL_BRNCH,"
					+ " CASE WHEN AUTH_ALL_BRNCH='Y' THEN 'Yes' ELSE 'No' END,AUTH_ALT_ACCNT_ID,AUTH_POLICY_VIOLN_ESCLN_ID"
					+ " FROM TMS_MANG_AUTH_HDR"
					+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=TMS_MANG_AUTH_HDR.AUTH_BRNCH_ID)"
					+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID)"
					+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_SCH_APPROVAL)"
					+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)"
					+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME";

			Object[][] data = getSqlModel().getSingleResult(query);
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = data.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) data.length / 20.0;

			java.math.BigDecimal value1 = new java.math.BigDecimal(row);

			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			ArrayList<Object> obj = new ArrayList<Object>();
			System.out.println("val of riwC" + rowCount1);
			request.setAttribute("abc", rowCount1);

			// PageNo
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals(" ")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;

				if (To_TOT > data.length) {
					To_TOT = data.length;
				}

				bean.setMyPage("1");
			}

			else {

				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				} else {
					// From_TOTAL=To_TOTAL+1;
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > data.length) {
					To_TOT = data.length;
				}
			}
			request.setAttribute("xyz", PageNo1);

			for (int i = From_TOT; i < To_TOT; i++) {
				// setting
				TmsTrvlMangAuthorities bean1 = new TmsTrvlMangAuthorities();

				bean1.setItBranch(String.valueOf(data[i][0]).trim());
				bean1.setItSchdlr(String.valueOf(data[i][1]));
				bean1.setItSchdlrAppr(String.valueOf(data[i][2]));
				bean1.setItAccnt(String.valueOf(data[i][3]));
				bean1.setItStatus(String.valueOf(data[i][4]));
				bean1.setItBranchCode(String.valueOf(data[i][5]));
				bean1.setItSchdlrCode(String.valueOf(data[i][6]));
				bean1.setItSchdlrApprCode(String.valueOf(data[i][7]));
				bean1.setItAccntCode(String.valueOf(data[i][8]));
				bean1.setItAltSchdlrCode(String.valueOf(data[i][9]));
				bean1.setItAuthCode(String.valueOf(data[i][10]));
				bean1.setItAllBranchCode(String.valueOf(data[i][11]));
				bean1.setItAllBranch(String.valueOf(data[i][12]));

				// ADDED BY REEBA
				bean1.setItAltAccntCode(String.valueOf(data[i][13]));
				bean1.setItEscalationEmployeeCode(String.valueOf(data[i][14]));
				obj.add(bean1);
			}
			bean.setAuthoritiesList(obj);
			if (obj.size() == 0) {
				bean.setNoData(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * ] To delete multiple record
	 */
	public boolean delChkdRec(TmsTrvlMangAuthorities trvlAuth, String[] code) {

		// logger.info("Inside delChkdRec Auth Model");
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				// logger.info("=====code==========" + code.length);
				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				// logger.info("====code value==" + delete[0][0]);
				getSqlModel().singleExecute(getQuery(3), delete);
				result = getSqlModel().singleExecute(getQuery(4), delete);
				if (!result) {
					count++;
				}
			}
		}

		// logger.info("count" + count);
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	/*
	 * To delete single record
	 */
	public boolean deleteAuth(TmsTrvlMangAuthorities trvlAuth) {
		boolean result = false;
		String delDtlSch = "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID="
				+ trvlAuth.getMangAuthCode();
		result = getSqlModel().singleExecute(delDtlSch);
		if (result) {
			Object del[][] = new Object[1][1];
			del[0][0] = trvlAuth.getMangAuthCode();
			return getSqlModel().singleExecute(getQuery(4), del);
		}
		return result;
	}

	public void getMangAuthDtl(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT HRMS_CENTER.CENTER_NAME,"
				+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
				+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"
				+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME,"
				+ " e4.EMP_FNAME || ' ' || e4.EMP_MNAME || ' ' || e4.EMP_LNAME,"
				+ " CASE WHEN AUTH_STATUS='A' THEN 'Active' WHEN AUTH_STATUS='D' THEN 'Deactive' ELSE ' ' END,AUTH_BRNCH_ID,"
				+ " AUTH_MAIN_SCHL_ID,NVL(AUTH_SCH_APPROVAL,''),NVL(AUTH_ACCOUNTENT,''),NVL(AUTH_ALT_MAIN_SCHL_ID,''),AUTH_ID,"
				+ " NVL(AUTH_ALL_BRNCH,''), "
				+ " CASE WHEN AUTH_ALL_BRNCH='Y' THEN 'Yes' ELSE 'No' END,AUTH_STATUS"
				// ADDED BY REEBA
				+ " , AUTH_ALT_ACCNT_ID, e5.EMP_FNAME || ' ' || e5.EMP_MNAME || ' ' || e5.EMP_LNAME, e5.EMP_TOKEN"
				+ " , AUTH_POLICY_VIOLN_ESCLN_ID, e6.EMP_FNAME || ' ' || e6.EMP_MNAME || ' ' || e6.EMP_LNAME, e6.EMP_TOKEN,"
				+ " NVL(AUTH_ACCOUNTANT_EMAIL_ID,'') ,CLM_ACK_WORKFLOW_ENABLE,CLM_ADMIN_WORKFLOW_ENABLE"

				+ " FROM TMS_MANG_AUTH_HDR"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=TMS_MANG_AUTH_HDR.AUTH_BRNCH_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_SCH_APPROVAL)"
				+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)"
				+ " LEFT JOIN HRMS_EMP_OFFC e4 ON(e4.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_MAIN_SCHL_ID)"
				// ADDED BY REEBA
				+ " LEFT JOIN HRMS_EMP_OFFC e5 ON(e5.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_ACCNT_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e6 ON(e6.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_POLICY_VIOLN_ESCLN_ID)"

				+ " WHERE AUTH_ID=" + trvlAuth.getMangAuthCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			trvlAuth.setBranch(String.valueOf(data[0][0]));
			trvlAuth.setMainSchdlr(String.valueOf(data[0][1]));
			trvlAuth.setSchdlrAppr(String.valueOf(data[0][2]));
			trvlAuth.setAccnt(String.valueOf(data[0][3]));
			trvlAuth.setAltMainSchdlr(String.valueOf(data[0][4]));
			trvlAuth.setStatus(String.valueOf(data[0][5]));
			trvlAuth.setBranchCode(String.valueOf(data[0][6]));
			trvlAuth.setMainSchdlrCode(String.valueOf(data[0][7]));
			trvlAuth.setSchdlrApprCode(String.valueOf(data[0][8]));
			trvlAuth.setAccntCode(String.valueOf(data[0][9]));
			trvlAuth.setAltMainSchdlrCode(String.valueOf(data[0][10]));
			trvlAuth.setMangAuthCode(String.valueOf(data[0][11]));
			trvlAuth.setHidAppFlag(checkNull(String.valueOf(data[0][12])));
			trvlAuth.setHidChkFlg(String.valueOf(data[0][13]));
			trvlAuth.setAppFlag(checkNull(String.valueOf(data[0][12]).equals(
					"Y") ? "checked" : ""));

			// ADDED BY REEBA
			trvlAuth.setAlterAccountantCode(checkNull(String
					.valueOf(data[0][15])));
			trvlAuth.setAlterAccountant(checkNull(String.valueOf(data[0][16])));
			trvlAuth.setAlterAccountantToken(checkNull(String
					.valueOf(data[0][17])));
			trvlAuth.setEscalationEmployeeCode(checkNull(String
					.valueOf(data[0][18])));
			trvlAuth.setEscalationEmployee(checkNull(String
					.valueOf(data[0][19])));
			trvlAuth.setEscalationEmployeeToken(checkNull(String
					.valueOf(data[0][20])));
			trvlAuth
					.setAccountantEmailID(checkNull(String.valueOf(data[0][21])));
			trvlAuth.setHiddenclaimwrkflowflag(checkNull(String.valueOf(data[0][22])));
			trvlAuth.setClaimwrkflowflag(checkNull(String.valueOf(data[0][22]).equals(
			"Y") ? "checked" : ""));
			trvlAuth.setHiddenackwrkflowflag(checkNull(String.valueOf(data[0][23])));
			trvlAuth.setAckwrkflowflag(checkNull(String.valueOf(data[0][23]).equals(
			"Y") ? "checked" : ""));
			
		}

	}

	/*
	 * To get management authorities detail applicable to all branch
	 */
	public void getMangAuthAllBranch(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT HRMS_CENTER.CENTER_NAME,"
				+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
				+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"
				+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME,"
				+ " e4.EMP_FNAME || ' ' || e4.EMP_MNAME || ' ' || e4.EMP_LNAME,"
				+ " CASE WHEN AUTH_STATUS='A' THEN 'Active' WHEN AUTH_STATUS='D' THEN 'Deactive' ELSE ' ' END,AUTH_BRNCH_ID,"
				+ " AUTH_MAIN_SCHL_ID,NVL(AUTH_SCH_APPROVAL,''),NVL(AUTH_ACCOUNTENT,''),NVL(AUTH_ALT_MAIN_SCHL_ID,''),AUTH_ID,"
				+ " NVL(AUTH_ALL_BRNCH,''), "
				+ " CASE WHEN AUTH_ALL_BRNCH='Y' THEN 'Yes' ELSE 'No' END,AUTH_STATUS"
				// ADDED BY REEBA
				+ " , AUTH_ALT_ACCNT_ID, e5.EMP_FNAME || ' ' || e5.EMP_MNAME || ' ' || e5.EMP_LNAME, e5.EMP_TOKEN"
				+ " , AUTH_POLICY_VIOLN_ESCLN_ID, e6.EMP_FNAME || ' ' || e6.EMP_MNAME || ' ' || e6.EMP_LNAME, e6.EMP_TOKEN, AUTH_ACCOUNTANT_EMAIL_ID,CLM_ACK_WORKFLOW_ENABLE,CLM_ADMIN_WORKFLOW_ENABLE"

				+ " FROM TMS_MANG_AUTH_HDR"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=TMS_MANG_AUTH_HDR.AUTH_BRNCH_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_SCH_APPROVAL)"
				+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)"
				+ " LEFT JOIN HRMS_EMP_OFFC e4 ON(e4.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_MAIN_SCHL_ID)"
				// ADDED BY REEBA
				+ " LEFT JOIN HRMS_EMP_OFFC e5 ON(e5.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_ACCNT_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e6 ON(e6.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_POLICY_VIOLN_ESCLN_ID)"

				+ " WHERE AUTH_ID=" + trvlAuth.getMangAuthCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			// trvlAuth.setBranch(String.valueOf(data[0][0]));
			trvlAuth.setMainSchdlr(String.valueOf(data[0][1]));
			trvlAuth.setSchdlrAppr(String.valueOf(data[0][2]));
			trvlAuth.setAccnt(String.valueOf(data[0][3]));
			trvlAuth.setAltMainSchdlr(String.valueOf(data[0][4]));
			trvlAuth.setStatus(String.valueOf(data[0][5]));
			// trvlAuth.setBranchCode(String.valueOf(data[0][6]));
			trvlAuth.setMainSchdlrCode(String.valueOf(data[0][7]));
			trvlAuth.setSchdlrApprCode(String.valueOf(data[0][8]));
			trvlAuth.setAccntCode(String.valueOf(data[0][9]));
			trvlAuth.setAltMainSchdlrCode(String.valueOf(data[0][10]));
			trvlAuth.setMangAuthCode(String.valueOf(data[0][11]));
			if (trvlAuth.getTestFlag().equals("true")) {
				trvlAuth.setHidAppFlag("N");
			} else {
				trvlAuth.setHidAppFlag(checkNull(String.valueOf(data[0][12])));
			}
			trvlAuth.setHidChkFlg(String.valueOf(data[0][13]));
			trvlAuth
					.setAppFlag(trvlAuth.getHidAppFlag().equals("Y") ? "checked"
							: "");

			// ADDED BY REEBA
			trvlAuth.setAlterAccountantCode(checkNull(String
					.valueOf(data[0][15])));
			trvlAuth.setAlterAccountant(checkNull(String.valueOf(data[0][16])));
			trvlAuth.setAlterAccountantToken(checkNull(String
					.valueOf(data[0][17])));
			trvlAuth.setEscalationEmployeeCode(checkNull(String
					.valueOf(data[0][18])));
			trvlAuth.setEscalationEmployee(checkNull(String
					.valueOf(data[0][19])));
			trvlAuth.setEscalationEmployeeToken(checkNull(String
					.valueOf(data[0][20])));
			trvlAuth
					.setAccountantEmailID(checkNull(String.valueOf(data[0][21])));
			trvlAuth.setHiddenclaimwrkflowflag(checkNull(String.valueOf(data[0][22])));
			trvlAuth.setClaimwrkflowflag(checkNull(String.valueOf(data[0][22]).equals(
			"N") ? "checked" : ""));
			trvlAuth.setHiddenackwrkflowflag(checkNull(String.valueOf(data[0][23])));
			trvlAuth.setAckwrkflowflag(checkNull(String.valueOf(data[0][23]).equals(
			"N") ? "checked" : ""));

		}

	}

	public void getMangAuthDtlEdit(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT HRMS_CENTER.CENTER_NAME,"
				+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
				+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"
				+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME,"
				+ " e4.EMP_FNAME || ' ' || e4.EMP_MNAME || ' ' || e4.EMP_LNAME,"
				+ " CASE WHEN AUTH_STATUS='A' THEN 'Active' WHEN AUTH_STATUS='D' THEN 'Deactive'  ELSE ' ' END,AUTH_BRNCH_ID,"
				+ " AUTH_MAIN_SCHL_ID,NVL(AUTH_SCH_APPROVAL,''),NVL(AUTH_ACCOUNTENT,''),NVL(AUTH_ALT_MAIN_SCHL_ID,''),AUTH_ID,"
				+ " NVL(AUTH_ALL_BRNCH,''), "
				+ " CASE WHEN AUTH_ALL_BRNCH='Y' THEN 'Yes' ELSE 'No' END,AUTH_STATUS"
				// ADDED BY REEBA
				+ " , AUTH_ALT_ACCNT_ID, e5.EMP_FNAME || ' ' || e5.EMP_MNAME || ' ' || e5.EMP_LNAME, e5.EMP_TOKEN"
				+ " , AUTH_POLICY_VIOLN_ESCLN_ID, e6.EMP_FNAME || ' ' || e6.EMP_MNAME || ' ' || e6.EMP_LNAME, e6.EMP_TOKEN, AUTH_ACCOUNTANT_EMAIL_ID,CLM_ACK_WORKFLOW_ENABLE,CLM_ADMIN_WORKFLOW_ENABLE"

				+ " FROM TMS_MANG_AUTH_HDR"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=TMS_MANG_AUTH_HDR.AUTH_BRNCH_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_SCH_APPROVAL)"
				+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)"
				+ " LEFT JOIN HRMS_EMP_OFFC e4 ON(e4.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_MAIN_SCHL_ID)"
				// ADDED BY REEBA
				+ " LEFT JOIN HRMS_EMP_OFFC e5 ON(e5.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ALT_ACCNT_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC e6 ON(e6.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_POLICY_VIOLN_ESCLN_ID)"

				+ " WHERE AUTH_ID=" + trvlAuth.getMangAuthCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			trvlAuth.setBranch(String.valueOf(data[0][0]));
			trvlAuth.setMainSchdlr(String.valueOf(data[0][1]));
			trvlAuth.setSchdlrAppr(String.valueOf(data[0][2]));
			trvlAuth.setAccnt(String.valueOf(data[0][3]));
			trvlAuth.setAltMainSchdlr(String.valueOf(data[0][4]));
			trvlAuth.setStatus(String.valueOf(data[0][14]));
			trvlAuth.setBranchCode(String.valueOf(data[0][6]));
			trvlAuth.setMainSchdlrCode(String.valueOf(data[0][7]));
			trvlAuth.setSchdlrApprCode(String.valueOf(data[0][8]));
			trvlAuth.setAccntCode(String.valueOf(data[0][9]));
			trvlAuth.setAltMainSchdlrCode(String.valueOf(data[0][10]));
			trvlAuth.setMangAuthCode(String.valueOf(data[0][11]));
			trvlAuth.setHidAppFlag(checkNull(String.valueOf(data[0][12])));
			trvlAuth.setHidChkFlg(String.valueOf(data[0][13]));
			trvlAuth.setAppFlag(checkNull(String.valueOf(data[0][12]).equals(
					"Y") ? "checked" : ""));

			// ADDED BY REEBA
			trvlAuth.setAlterAccountantCode(checkNull(String
					.valueOf(data[0][15])));
			trvlAuth.setAlterAccountant(checkNull(String.valueOf(data[0][16])));
			trvlAuth.setAlterAccountantToken(checkNull(String
					.valueOf(data[0][17])));
			trvlAuth.setEscalationEmployeeCode(checkNull(String
					.valueOf(data[0][18])));
			trvlAuth.setEscalationEmployee(checkNull(String
					.valueOf(data[0][19])));
			trvlAuth.setEscalationEmployeeToken(checkNull(String
					.valueOf(data[0][20])));
			trvlAuth
					.setAccountantEmailID(checkNull(String.valueOf(data[0][21])));
			trvlAuth.setHiddenclaimwrkflowflag(checkNull(String.valueOf(data[0][22])));
			trvlAuth.setClaimwrkflowflag(checkNull(String.valueOf(data[0][22]).equals(
			"N") ? "checked" : ""));
			trvlAuth.setHiddenackwrkflowflag(checkNull(String.valueOf(data[0][23])));
			trvlAuth.setAckwrkflowflag(checkNull(String.valueOf(data[0][23]).equals(
			"N") ? "checked" : ""));
		}

	}

	public void getSubList(TmsTrvlMangAuthorities trvlAuth) {/*
	 * String
	 * myHidden =
	 * trvlAuth.getMyHidden();
	 * String[] hide =
	 * myHidden.split(",");
	 * ArrayList<Object>
	 * list = new
	 * ArrayList<Object>();
	 * for (int i =
	 * 0; i <
	 * hide.length;
	 * i++) {
	 * 
	 * String[]
	 * hideno =
	 * hide[i].split("#");
	 * TmsTrvlMangAuthorities
	 * bean = new
	 * TmsTrvlMangAuthorities();
	 * bean.setSrNo("" +
	 * (i + 1));
	 * bean.setItEmployeeCode(hideno[0]);
	 * String query = "
	 * SELECT
	 * (HRMS_EMP_OFFC.EMP_FNAME || ' '
	 * ||HRMS_EMP_OFFC.EMP_MNAME || ' ' ||
	 * HRMS_EMP_OFFC.EMP_LNAME)
	 * as
	 * name,EMP_TOKEN " + "
	 * FROM
	 * HRMS_EMP_OFFC " + "
	 * WHERE
	 * HRMS_EMP_OFFC.EMP_ID=" +
	 * hideno[0]; //
	 * Object[][]
	 * data =
	 * getSqlModel().getSingleResult(query);
	 * 
	 * Object[][]
	 * data =
	 * getSqlModel().getSingleResult(query); //
	 * logger.info("--->"+data.length);
	 * if (data !=
	 * null) {
	 * bean.setItEmployee(String.valueOf(data[0][0]));
	 * bean.setItEmployeeToken(String.valueOf(data[0][1])); }
	 * bean.setHidItTravel(hideno[1]);
	 * bean.setHidItLocalConv(hideno[2]);
	 * bean.setHidItLodging(hideno[3]);
	 * bean.setItTravel(hideno[1].equals("Y") ?
	 * "checked" :
	 * "");
	 * bean.setItLocalConv(hideno[2].equals("Y") ?
	 * "checked" :
	 * "");
	 * bean.setItLodging(hideno[3].equals("Y") ?
	 * "checked" :
	 * "");
	 * list.add(bean); }
	 * trvlAuth.setSubSchdlrList(list);
	 * if
	 * (trvlAuth.getSubSchdlrList().size() ==
	 * 0) {
	 * trvlAuth.setSubTabLength("0"); }
	 * 
	 */
	}

	/*
	 * To display OLD description
	 */
	public void getDescPrevious(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT AUTH_DESC FROM TMS_MANG_AUTH_HDR WHERE AUTH_ID="
				+ trvlAuth.getMangAuthCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		try {
			trvlAuth.setDescription(checkNull(String.valueOf(data[0][0])));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * To display description
	 */
	public void getDesc(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT AUTH_DESC FROM TMS_MANG_AUTH_HDR WHERE AUTH_ID="
				+ trvlAuth.getMangAuthCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		try {
			trvlAuth.setDescriptionNew(checkNull(String.valueOf(data[0][0])));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/*
	 * To get hidden data for edit
	 */
	public void getHidData(TmsTrvlMangAuthorities trvlAuth) {
		String str = "0", strEmp = "0";
		String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR";
		Object[][] data = getSqlModel().getSingleResult(branchQuery);
		String empQuery = "SELECT NVL(AUTH_DTL_SUB_SCH_ID,0) FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID="
				+ trvlAuth.getMangAuthCode();
		Object[][] empData = getSqlModel().getSingleResult(empQuery);

		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				str += "," + data[i][0];
			}
			trvlAuth.setItBranchCode(str);

		}

		if (empData != null && empData.length > 0) {
			for (int i = 0; i < empData.length; i++) {
				strEmp += "," + empData[i][0];
			}
			trvlAuth.setHiddenEmpId(strEmp);
		}
	}

	public void getMsg(TmsTrvlMangAuthorities trvlAuth) {

		String query = "SELECT HRMS_CENTER.CENTER_NAME,AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=TMS_MANG_AUTH_HDR.AUTH_BRNCH_ID)"
				+ " WHERE AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";
		Object[][] queryData = getSqlModel().getSingleResult(query);

		if (queryData != null && queryData.length > 0) {
			trvlAuth.setMsg(String.valueOf(queryData[0][0]));
			trvlAuth.setMsgFlag(true);
		} else {
			trvlAuth.setMsgFlag(false);
		}

	}

	public void showBranch(TmsTrvlMangAuthorities trvlAuth) {
		String msgQuery = "SELECT NVL(CENTER_NAME,' ') FROM HRMS_CENTER"
				+ " WHERE CENTER_ID NOT IN(SELECT NVL(AUTH_BRNCH_ID,0) FROM TMS_MANG_AUTH_HDR WHERE AUTH_STATUS='A')"
				+ " ORDER BY  CENTER_ID";
		Object[][] msgQueryData = getSqlModel().getSingleResult(msgQuery);
		ArrayList<Object> list = new ArrayList<Object>();
		if (msgQueryData != null && msgQueryData.length > 0) {
			for (int i = 0; i < msgQueryData.length; i++) {
				TmsTrvlMangAuthorities bean = new TmsTrvlMangAuthorities();
				bean.setDivBranch(String.valueOf(msgQueryData[i][0]));
				list.add(bean);
			}
			trvlAuth.setShowBranchFlag(true);
		}
		trvlAuth.setBranchList(list);
	}

	public void getEmpId(TmsTrvlMangAuthorities trvlAuth) {

		String query = "SELECT NVL(AUTH_MAIN_SCHL_ID,0)"
				+ " FROM TMS_MANG_AUTH_HDR";
		Object[][] QueryData = getSqlModel().getSingleResult(query);

		String query1 = "SELECT NVL(AUTH_ALT_MAIN_SCHL_ID,0)"
				+ " FROM TMS_MANG_AUTH_HDR";
		Object[][] Query1Data = getSqlModel().getSingleResult(query1);

		String query2 = "SELECT NVL(AUTH_SCH_APPROVAL,0)"
				+ " FROM TMS_MANG_AUTH_HDR";
		Object[][] Query2Data = getSqlModel().getSingleResult(query2);

		String query3 = "SELECT NVL(AUTH_ACCOUNTENT,0)"
				+ " FROM TMS_MANG_AUTH_HDR";
		Object[][] Query3Data = getSqlModel().getSingleResult(query3);

		// ADDED BY REEBA BEGINS
		String query4 = "SELECT NVL(AUTH_ALT_ACCNT_ID,0)"
				+ " FROM TMS_MANG_AUTH_HDR";
		Object[][] Query4Data = getSqlModel().getSingleResult(query4);

		String query5 = "SELECT NVL(AUTH_POLICY_VIOLN_ESCLN_ID,0)"
				+ " FROM TMS_MANG_AUTH_HDR";
		Object[][] Query5Data = getSqlModel().getSingleResult(query5);
		// ADDED BY REEBA ENDS

		String schQuery = "SELECT NVL(AUTH_DTL_SUB_SCH_ID,0) FROM TMS_MNG_AUTH_DTL";
		Object[][] schQueryData = getSqlModel().getSingleResult(schQuery);
		// UPDATED BY REEBA
		String str = "0", str4 = "0", str1 = "0", str2 = "0", str3 = "0", finalStr = "0", str5 = "0", str6 = "0";
		if (QueryData != null && QueryData.length > 0) {
			for (int i = 0; i < QueryData.length; i++) {
				if (i == 0) {
					str = "" + QueryData[i][0];
				} else {
					str += "," + QueryData[i][0];
				}
			}
			logger.info("--------model str======" + str);
		}

		if (Query1Data != null && Query1Data.length > 0) {
			for (int i = 0; i < Query1Data.length; i++) {
				if (i == 0) {
					str1 = "" + Query1Data[i][0];
				} else {
					str1 += "," + Query1Data[i][0];
				}

			}
			logger.info("--------model str1======" + str1);
		}

		if (Query2Data != null && Query2Data.length > 0) {
			for (int i = 0; i < Query2Data.length; i++) {
				if (i == 0) {
					str2 = "" + Query2Data[i][0];
				} else {
					str2 += "," + Query2Data[i][0];
				}

			}
			logger.info("--------model str2======" + str2);
		}

		if (Query3Data != null && Query3Data.length > 0) {
			for (int i = 0; i < Query3Data.length; i++) {
				if (i == 0) {
					str3 = "" + Query3Data[i][0];
				} else {
					str3 += "," + Query3Data[i][0];
				}

			}
			logger.info("--------model str3======" + str3);
		}

		if (schQueryData != null && schQueryData.length > 0) {
			for (int i = 0; i < schQueryData.length; i++) {
				if (i == 0) {
					str4 = "" + QueryData[i][0];
				} else {
					str4 += "," + schQueryData[i][0];
				}

			}
			logger.info("--------model str4======" + str4);
		}

		// ADDED BY REEBA BEGINS
		if (Query4Data != null && Query4Data.length > 0) {
			for (int i = 0; i < Query4Data.length; i++) {
				if (i == 0) {
					str5 = "" + Query4Data[i][0];
				} else {
					str5 += "," + Query4Data[i][0];
				}

			}
			logger.info("--------model str5======" + str5);
		}

		if (Query5Data != null && Query5Data.length > 0) {
			for (int i = 0; i < Query5Data.length; i++) {
				if (i == 0) {
					str6 = "" + Query5Data[i][0];
				} else {
					str6 += "," + Query5Data[i][0];
				}

			}
			logger.info("--------model str6======" + str6);
		}
		// ADDED BY REEBA ENDS

		// UPDATED BY REEBA
		finalStr = str + "," + str1 + "," + str2 + "," + str3 + "," + str4
				+ "," + str5 + "," + str6;
		logger.info("--------model finalStr======" + finalStr);
		trvlAuth.setHidTabEmpId(finalStr);

	}

	public void getAllDetail(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT AUTH_ID,AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_BRNCH_ID="
				+ trvlAuth.getBranchCode();
		Object[][] queryData = getSqlModel().getSingleResult(query);
		if (queryData != null && queryData.length > 0) {
			trvlAuth.setMangAuthCode(String.valueOf(queryData[0][0]));
			getMangAuthAllBranch(trvlAuth);
			getDesc(trvlAuth);
			viewSubSchdlr(trvlAuth);
		}// end of if
		else {
			String innerQuery = "SELECT AUTH_ID,AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";
			Object[][] innerData = getSqlModel().getSingleResult(innerQuery);
			if (innerData != null && innerData.length > 0) {
				trvlAuth.setMangAuthCode(String.valueOf(innerData[0][0]));
				trvlAuth.setTestFlag("true");
				getMangAuthAllBranch(trvlAuth);
				getDesc(trvlAuth);
				viewSubSchdlr(trvlAuth);
				// trvlAuth.setMangAuthCode("");
			}// end of inner if
			else {
				trvlAuth
						.setAppFlag(trvlAuth.getHidAppFlag().equals("Y") ? "checked"
								: "");
			}
		}// end of else
	}

	public void getBranchDtl(TmsTrvlMangAuthorities trvlAuth) {

		String branchQquery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE AUTH_BRNCH_ID="
				+ trvlAuth.getBranchCode();
		Object[][] branchQueryData = getSqlModel()
				.getSingleResult(branchQquery);

		if (branchQueryData != null && branchQueryData.length > 0) {
			trvlAuth.setTestBranchCode(String.valueOf(branchQueryData[0][0]));
		} else {
			trvlAuth.setTestBranchCode("");
		}
	}

	public void checkData(TmsTrvlMangAuthorities trvlAuth) {
		String query = "SELECT AUTH_ID FROM TMS_MANG_AUTH_HDR";
		Object[][] queryData = getSqlModel().getSingleResult(query);
		if (queryData != null && queryData.length > 0) {
			trvlAuth.setDataFlag("true");
		} else {
			trvlAuth.setDataFlag("false");
		}
	}

	/**
	 * @author REEBA JOSEPH
	 * @param trvlAuth
	 * @return boolean
	 */
	public boolean updateMainScheduler(TmsTrvlMangAuthorities trvlAuth) {

		String updateQuery = "UPDATE TMS_MANG_AUTH_HDR SET AUTH_MAIN_SCHL_ID="
				+ trvlAuth.getAltMainSchdlrCode() + ", AUTH_ALT_MAIN_SCHL_ID= "
				+ trvlAuth.getMainSchdlrCode() + " WHERE AUTH_ID="
				+ trvlAuth.getMangAuthCode();

		return getSqlModel().singleExecute(updateQuery);
	}// end of method updateMainScheduler

	/**
	 * @author REEBA JOSEPH
	 * @param trvlAuth
	 * @return boolean
	 */
	public boolean updateAccountant(TmsTrvlMangAuthorities trvlAuth) {

		String updateQuery = "UPDATE TMS_MANG_AUTH_HDR SET AUTH_ACCOUNTENT="
				+ trvlAuth.getAlterAccountantCode() + ", AUTH_ALT_ACCNT_ID= "
				+ trvlAuth.getAccntCode() + " WHERE AUTH_ID="
				+ trvlAuth.getMangAuthCode();

		return getSqlModel().singleExecute(updateQuery);
	}// end of method updateAccountant

	public void addAccountant(TmsTrvlMangAuthorities trvlAuth,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String[] srNo = request.getParameterValues("srNoAcc");
			String[] itEmployeeCode = request
					.getParameterValues("itEmployeeCodeAcc");
			String[] itEmployee = request.getParameterValues("itEmployeeAcc");
			String[] itEmployeeToken = request
					.getParameterValues("itEmployeeTokenAcc");

			String[] acknoledge = (String[]) request
					.getParameterValues("hiddenitAcknoledge");
			String[] advance = (String[]) request
					.getParameterValues("hiddenitAdvance");

			String[] claim = (String[]) request
					.getParameterValues("hiddenittClaim");

			ArrayList<Object> list = new ArrayList<Object>();

			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					TmsTrvlMangAuthorities bean = new TmsTrvlMangAuthorities();
					bean.setSrNoAcc(srNo[i]);
					bean.setItEmployeeAcc(itEmployee[i]);
					bean.setItEmployeeCodeAcc(itEmployeeCode[i]);
					bean.setItEmployeeTokenAcc(itEmployeeToken[i]);

					bean.setHiddenitAcknoledge(acknoledge[i]);
					bean.setHiddenitAdvance(advance[i]);
					bean.setHiddenittClaim(claim[i]);
					bean.setItAcknoledge(bean.getHiddenitAcknoledge().equals(
							"Y") ? "checked" : "");
					bean
							.setItAdvance(bean.getHiddenitAdvance().equals("Y") ? "checked"
									: "");
					bean
							.setIttClaim(bean.getHiddenittClaim().equals("Y") ? "checked"
									: "");

					list.add(bean);
				}

			}

			TmsTrvlMangAuthorities bean1 = new TmsTrvlMangAuthorities();

			bean1.setSrNoAcc(String.valueOf(list.size() + 1));
			bean1.setItEmployeeAcc(trvlAuth.getAccountantemployee());
			bean1.setItEmployeeCodeAcc(trvlAuth.getAccountantemployeeCode());
			bean1.setItEmployeeTokenAcc(trvlAuth.getAccountantemployeeToken());

			bean1.setHiddenitAcknoledge("N");
			bean1.setHiddenitAdvance("N");
			bean1.setHiddenittClaim("N");

			list.add(bean1);

			trvlAuth.setSubAccountantList(list);

			if (trvlAuth.getSubAccountantList().size() == 0) {

			} else {

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setItterotorSubShedulerData(TmsTrvlMangAuthorities trvlAuth,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String[] srNo = request.getParameterValues("srNo");
			String[] itEmployeeCode = request
					.getParameterValues("itEmployeeCode");
			String[] itEmployee = request.getParameterValues("itEmployee");
			String[] itEmployeeToken = request
					.getParameterValues("itEmployeeToken");

			ArrayList<Object> list = new ArrayList<Object>();

			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					TmsTrvlMangAuthorities bean = new TmsTrvlMangAuthorities();
					bean.setSrNo(srNo[i]);
					bean.setItEmployee(itEmployee[i]);
					bean.setItEmployeeCode(itEmployeeCode[i]);
					bean.setItEmployeeToken(itEmployeeToken[i]);
					list.add(bean);
				}

				trvlAuth.setSubSchdlrList(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setItterotorAccountantData(TmsTrvlMangAuthorities trvlAuth,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {

			String[] srNo = request.getParameterValues("srNoAcc");
			String[] itEmployeeCode = request
					.getParameterValues("itEmployeeCodeAcc");
			String[] itEmployee = request.getParameterValues("itEmployeeAcc");
			String[] itEmployeeToken = request
					.getParameterValues("itEmployeeTokenAcc");

			String[] acknoledge = (String[]) request
					.getParameterValues("hiddenitAcknoledge");
			String[] advance = (String[]) request
					.getParameterValues("hiddenitAdvance");
			String[] claim = (String[]) request
					.getParameterValues("hiddenittClaim");

			ArrayList<Object> list = new ArrayList<Object>();

			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					TmsTrvlMangAuthorities bean = new TmsTrvlMangAuthorities();
					bean.setSrNoAcc(srNo[i]);
					bean.setItEmployeeAcc(itEmployee[i]);
					bean.setItEmployeeCodeAcc(itEmployeeCode[i]);
					bean.setItEmployeeTokenAcc(itEmployeeToken[i]);

					bean.setHiddenitAcknoledge(acknoledge[i]);
					bean.setHiddenitAdvance(advance[i]);
					bean.setHiddenittClaim(claim[i]);
					bean.setItAcknoledge(bean.getHiddenitAcknoledge().equals(
							"Y") ? "checked" : "");
					bean
							.setItAdvance(bean.getHiddenitAdvance().equals("Y") ? "checked"
									: "");
					bean
							.setIttClaim(bean.getHiddenittClaim().equals("Y") ? "checked"
									: "");

					list.add(bean);
				}

			}
			trvlAuth.setSubAccountantList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
