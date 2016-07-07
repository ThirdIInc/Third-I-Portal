/**
 * 
 */
package org.struts.action.Asset;

import org.paradyne.bean.Asset.AssetEmployeeMis;
import org.paradyne.model.Asset.AssetEmployeeMisModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 * 
 */
public class AssetEmployeeMisAction extends ParaActionSupport {

	AssetEmployeeMis assetMis;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		this.assetMis = new AssetEmployeeMis();
		assetMis.setMenuCode(546);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return assetMis;
	}

	public AssetEmployeeMis getAssetMis() {
		return assetMis;
	}

	public void setAssetMis(AssetEmployeeMis assetMis) {
		this.assetMis = assetMis;
	}

	public void report() {
		AssetEmployeeMisModel model = new AssetEmployeeMisModel();
		model.initiate(context, session);
		model.getReport(request, response, assetMis);
		model.terminate();
	}

	public String f9actionEname() throws Exception {

		/*
		 * Select the Employee F9
		 * 
		 * 
		 */

		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+ " EMP_ID FROM HRMS_EMP_OFFC"
				+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)";
				
				query += getprofileQuery(assetMis);
	 
		query +=" AND EMP_STATUS ='S' ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"),
				getMessage("employee") };

		String[] headerWidth = { "10", "90" };

		String[] fieldNames = { "empToken", "ename", "empCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String reset() {
		assetMis.setEmpCode("");
		assetMis.setEname("");
		assetMis.setFrmDate("");
		assetMis.setToDate("");
		assetMis.setEmpToken("");
		assetMis.setStatus("");
		return SUCCESS;
	}

}
