package org.paradyne.model.TravelManagement.TravelProcess;

import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlProcessMngr;
import org.paradyne.lib.ModelBase;

/**
 * @author krishna date: 16-JULY-2009
 * 
 */
public class TmsTrvlProcessMngrModel extends ModelBase {

	public void showSetting(TmsTrvlProcessMngr processManager) {
		String Query = " SELECT PMGR_REQ_RD, PMGR_REQ_APPR_RD,PMGR_SCHL_RD,PMGR_SCHL_APPR_RD,PMGR_APPR_MAIN_SCHL,"
				+ " PMGR_TRVL_CONF_RD,PMGR_TRVL_CLAIM_APP_RD,PMGR_TRVL_CLAIM_APPRD_RD, PMGR_SCND_LVL_APPRD_RD, "
				+ " PMGR_TRVL_POLICY_EFFDT FROM TMS_PROCESS_MGR";
		Object[][] data = getSqlModel().getSingleResult(Query);

		if (data != null && data.length > 0) {

			if (data[0][0].equals("Y")) {
				processManager.setReqFlag("checked");

			} else {
				processManager.setReqFlag("false");
			}

			if (data[0][1].equals("Y")) {
				processManager.setReqAppFlag("checked");
			} else {
				processManager.setReqAppFlag("false");
			}

			if (data[0][2].equals("Y")) {
				processManager.setSchdFlag("checked");
			} else {
				processManager.setSchdFlag("false");
			}

			if (data[0][3].equals("Y")) {
				processManager.setSchdAppFlag("checked");
			} else {
				processManager.setSchdAppFlag("false");
			}

			if (data[0][4].equals("Y")) {
				processManager.setApprMainSch("checked");
			} else {
				processManager.setApprMainSch("false");
			}

			if (data[0][5].equals("Y")) {
				processManager.setConfFlag("checked");
			} else {
				processManager.setConfFlag("false");
			}

			if (data[0][6].equals("Y")) {
				processManager.setClaimFlag("checked");
			} else {
				processManager.setClaimFlag("false");
			}

			if (data[0][7].equals("Y")) {
				processManager.setClaimAppFlag("checked");
			} else {
				processManager.setClaimAppFlag("false");
			}

			if (data[0][8].equals("Y")) {
				processManager.setLevelFlag("checked");
			} else {
				processManager.setLevelFlag("false");
			}

			processManager.setEffectDate(String.valueOf(data[0][9]));

		} else {
			processManager.setReqFlag("false");
			processManager.setReqAppFlag("false");
			processManager.setSchdFlag("false");
			processManager.setSchdAppFlag("false");
			processManager.setApprMainSch("false");
			processManager.setConfFlag("false");
			processManager.setClaimFlag("false");
			processManager.setClaimAppFlag("false");
			processManager.setLevelFlag("false");
			processManager.setFromDate("false");
		}

	}

	public boolean saveApplication(TmsTrvlProcessMngr processManager) {

		Object[][] obj = new Object[1][10];

		if (processManager.getHidreqFlag().equals("Y")) {
			obj[0][0] = "Y";
		} else
			obj[0][0] = "N";

		if (processManager.getHidreqAppFlag().equals("Y")) {
			obj[0][1] = "Y";
		} else
			obj[0][1] = "N";

		if (processManager.getHidschdFlag().equals("Y")) {
			obj[0][2] = "Y";
		} else
			obj[0][2] = "N";
		if (processManager.getHidschdAppFlag().equals("Y")) {
			obj[0][3] = "Y";
		} else
			obj[0][3] = "N";

		if (processManager.getHidapprMainSch().equals("Y")) {
			obj[0][4] = "Y";
		} else
			obj[0][4] = "N";

		if (processManager.getHidconfFlag().equals("Y")) {
			obj[0][5] = "Y";
		} else
			obj[0][5] = "N";

		if (processManager.getHidclaimFlag().equals("Y")) {
			obj[0][6] = "Y";
		} else
			obj[0][6] = "N";

		if (processManager.getHidclaimAppFlag().equals("Y")) {
			obj[0][7] = "Y";
		} else
			obj[0][7] = "N";

		if (processManager.getHidlevelFlag().equals("Y")) {
			obj[0][8] = "Y";
		} else
			obj[0][8] = "N";

		obj[0][9] = processManager.getEffectDate();
		
		
		 String query ="DELETE FROM TMS_PROCESS_MGR";
		 
		 getSqlModel().singleExecute(query);
		

		String Query = "INSERT INTO TMS_PROCESS_MGR  (PMGR_REQ_RD, PMGR_REQ_APPR_RD,PMGR_SCHL_RD,PMGR_SCHL_APPR_RD,PMGR_APPR_MAIN_SCHL,"
				+ " PMGR_TRVL_CONF_RD,PMGR_TRVL_CLAIM_APP_RD,PMGR_TRVL_CLAIM_APPRD_RD, PMGR_SCND_LVL_APPRD_RD, "
				+ " PMGR_TRVL_POLICY_EFFDT )"
				+ " VALUES(?,? ,? ,? ,? ,? ,? ,?,?,?)";
		return getSqlModel().singleExecute(Query, obj);

	}

}
