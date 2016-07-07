package org.paradyne.model.recruitment;

import java.util.ArrayList;

import org.paradyne.bean.Recruitment.UploadDocuments;
import org.paradyne.lib.ModelBase;

public class UploadDocumentsModel extends ModelBase {

	public void showCheckList(UploadDocuments bean) {
		try {
			String Query = "SELECT CHECK_CODE,CHECK_NAME FROM HRMS_CHECKLIST	WHERE CHECK_TYPE='B'	AND CHECK_STATUS='A' ORDER BY CHECK_CODE";

			Object obj[][] = getSqlModel().getSingleResult(Query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					UploadDocuments innerBean = new UploadDocuments();
					innerBean.setCheckListitemcode(checkNull(String
							.valueOf(obj[i][0])));
					innerBean.setCheckListName(checkNull(String
							.valueOf(obj[i][1])));
					list.add(innerBean);
				}
				bean.setChkList(list);
				bean.setChkLength(String.valueOf(list.size()));
				bean.setNoData("false");
			} else {
				bean.setChkLength("0");
				bean.setNoData("true");
			}
			// bean.setChecklistTable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean save(Object[] ckcode, Object[] dtlproof, UploadDocuments bean) {

		boolean result = false;

		try {
			
			String maxCandCode = "  select CAND_DATABANK_CODE from  HRMS_REC_CAND_LOGIN where CAND_CODE="
				+ bean.getCandidateUserEmpId();
			Object[][]maxCandDatabankCode = getSqlModel().getSingleResult(maxCandCode);

		
			Object saveObj[][] = new Object[1][3];
			saveObj[0][0] =String.valueOf(maxCandDatabankCode[0][0]);
			saveObj[0][1] = bean.getRequisitionCode();
			saveObj[0][2] = "B";
			String insertHdrQuery = "  INSERT INTO HRMS_REC_BGCHECK (BG_CODE,BG_CAND_CODE,BG_REQS_CODE,BG_CHECK_LIST)  "
					+ " VALUES ((SELECT NVL(MAX(BG_CODE),0)+1 FROM HRMS_REC_BGCHECK),?,?,? ) ";
			result = getSqlModel().singleExecute(insertHdrQuery, saveObj);
			if (result) {
				String insertDtlQuery = " INSERT INTO HRMS_REC_BGCHECK_DTL (BG_DTL_CODE,BG_CODE,CHECKLIST_CODE,BG_DTL_PROOF) VALUES ((SELECT NVL(MAX(BG_DTL_CODE),0)+1 FROM HRMS_REC_BGCHECK_DTL),?,?,?)";

				String query = " SELECT NVL(MAX(BG_CODE),0) FROM HRMS_REC_BGCHECK";
				Object resultCode[][] = getSqlModel().getSingleResult(query);

				if (ckcode != null && ckcode.length > 0) {
					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][3];
						chkDtl[0][0] = String.valueOf(resultCode[0][0]);
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][2] = String.valueOf(dtlproof[i]);
						result = getSqlModel().singleExecute(insertDtlQuery,
								chkDtl);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	public void setSaveDocs(UploadDocuments bean) {
		try {
			String query = " SELECT  HRMS_REC_BGCHECK_DTL.CHECKLIST_CODE, CHECK_NAME,HRMS_REC_BGCHECK_DTL.BG_CODE, "
					+ " HRMS_REC_BGCHECK_DTL.BG_DTL_PROOF  FROM HRMS_REC_BGCHECK_DTL LEFT JOIN HRMS_REC_BGCHECK ON (HRMS_REC_BGCHECK.BG_CODE=HRMS_REC_BGCHECK_DTL.BG_CODE) LEFT JOIN HRMS_CHECKLIST ON (HRMS_CHECKLIST.CHECK_CODE=HRMS_REC_BGCHECK_DTL.CHECKLIST_CODE) WHERE HRMS_REC_BGCHECK_DTL.BG_CODE="+bean.getHiddenCode()+" AND HRMS_CHECKLIST.CHECK_TYPE='B'   ORDER BY CHECKLIST_CODE ";
			Object obj[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					UploadDocuments innerBean = new UploadDocuments();
					innerBean.setCheckListitemcode(checkNull(String
							.valueOf(obj[i][0])));
					innerBean.setCheckListName(checkNull(String
							.valueOf(obj[i][1])));
					innerBean.setUploadLocFileName(checkNull(String
							.valueOf(obj[i][3])));

					bean.setHiddenCode(checkNull(String.valueOf(obj[i][2])));
					list.add(innerBean);
				}
				bean.setChkList(list);
				bean.setChkLength(String.valueOf(list.size()));
				bean.setNoData("false");

			} else {
				bean.setChkLength("0");
				bean.setNoData("true");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	public boolean update(Object[] ckcode, Object[] dtlproof,
			UploadDocuments bean) {
		boolean result = false;

		try {
			String query = " DELETE FROM HRMS_REC_BGCHECK_DTL WHERE BG_CODE="
					+ bean.getHiddenCode();
			result = getSqlModel().singleExecute(query);
			if (result) {

				String insertDtlQuery = " INSERT INTO HRMS_REC_BGCHECK_DTL (BG_DTL_CODE,BG_CODE,CHECKLIST_CODE,BG_DTL_PROOF) VALUES ((SELECT NVL(MAX(BG_DTL_CODE),0)+1 FROM HRMS_REC_BGCHECK_DTL),?,?,?)";

				String hdrquery = " SELECT NVL(MAX(BG_CODE),0) FROM HRMS_REC_BGCHECK";
				Object resultCode[][] = getSqlModel().getSingleResult(hdrquery);

				if (ckcode != null && ckcode.length > 0) {
					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][3];
						chkDtl[0][0] = String.valueOf(resultCode[0][0]);
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][2] = String.valueOf(dtlproof[i]);
						result = getSqlModel().singleExecute(insertDtlQuery,
								chkDtl);
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
