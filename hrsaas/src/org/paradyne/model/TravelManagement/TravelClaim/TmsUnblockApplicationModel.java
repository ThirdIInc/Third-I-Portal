package org.paradyne.model.TravelManagement.TravelClaim;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelClaim.TmsUnblockApplication;
import org.paradyne.lib.ModelBase;

public class TmsUnblockApplicationModel extends ModelBase
{
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(TmsUnblockApplicationModel.class);

	public void getBlockedApplications(TmsUnblockApplication unblockbean,
			HttpServletRequest request, String empId) {
		// TODO Auto-generated method stub
		try {
			String query = "";

			String str = "0";
			Object[][] branchData = null;
			String allBrnchQuery = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
					+ unblockbean.getUserEmpId()
					+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);

			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_MAIN_SCHL_ID="
							+ unblockbean.getUserEmpId() + "AND AUTH_STATUS='A'";
					branchData = getSqlModel().getSingleResult(branchQuery);
					if (branchData != null && branchData.length > 0) {
						for (int i = 0; i < branchData.length; i++) {
							if (i == 0) {
								str = "" + branchData[i][0];
							} else {
								str += "," + branchData[i][0];
							}

						}// end of for
					}

				}// end of if
				else if (allBrnch[0][0].equals("Y")) {
					str = "  1=1";
				}// end of else if
			}
			
			
			if (allBrnch != null && allBrnch.length > 0)
			{	
			
		
			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					query += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + str
							+ ")";
				} else if (allBrnch[0][0].equals("Y")) {
					query += " AND" + str;
				}
			}
			
			Object blckTrvlData[][] = getSqlModel().getSingleResult(getQuery(1)+query);
			
			System.out.println("empId "+empId );
			
			ArrayList<TmsUnblockApplication> blckTrvlList = new ArrayList<TmsUnblockApplication>();
			if (blckTrvlData != null && blckTrvlData.length > 0) {
				for (int i = 0; i < blckTrvlData.length; i++) {

					TmsUnblockApplication bean1 = new TmsUnblockApplication();
					bean1.setAppId(checkNull(String.valueOf(blckTrvlData[i][0])));
					bean1.setAppCode(checkNull(String.valueOf(blckTrvlData[i][1])));
					bean1.setAppFor(checkNull(String.valueOf(blckTrvlData[i][11])));
					bean1.setEmpName(checkNull(String.valueOf(blckTrvlData[i][5])));
					bean1.setTrvlReqName(checkNull(String.valueOf(blckTrvlData[i][8])));
					bean1.setAppEndDate(checkNull(String.valueOf(blckTrvlData[i][7])));  // travel date
					bean1.setInitName(checkNull(String.valueOf(blckTrvlData[i][4])));
					bean1.setEmpId(checkNull(String.valueOf(blckTrvlData[i][9])));
					bean1.setInitId(checkNull(String.valueOf(blckTrvlData[i][3])));
					bean1.setTrvlId(checkNull(String.valueOf(blckTrvlData[i][2])));
					bean1.setClaimDueDays(checkNull(String.valueOf(blckTrvlData[i][10])));
					bean1.setApplnStatus(checkNull(String.valueOf(blckTrvlData[i][12])));	
				//	bean1.setMaximumClaimDueDays(checkNull(String.valueOf(blckTrvlData[i][13])));
					
					blckTrvlList.add(bean1);
				}
				
			}
			unblockbean.setBlockedAppList(blckTrvlList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Exception ocurred : "+e);
		}
		
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals(" ")
				|| result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}
}

