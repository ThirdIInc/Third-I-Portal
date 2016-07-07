/**
 * @author Ananthalakshmi
 * 
 */
package org.paradyne.model.Asset;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.AssestCountReport;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class AssetCountReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	public AssetCountReportModel() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * THIS METHOD IS USED OFR GENERATING REPORT
	 * @param leaveAppMis
	 * @param response
	 * @return String
	 */
	public String getReport(AssestCountReport assetCntRpt,HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String [] headerNames={"Asset Category Name" , "Asset SubType Name" , "Lost" , "Damage" , "Used" , "Assigned" , "Total"};
			int[] cellWidth = { 15 , 15 , 15 , 15 , 15 , 15 , 10 };
			int[] alignment = { 0 , 0 , 0 , 0 , 1 , 0 , 0};
			String sqlQuery = " SELECT ASSET_CATEGORY_NAME ,"
					+ " ASSET_SUBTYPE_NAME ,ASSET_ASSISGN_FLAG,COUNT(ASSET_ASSISGN_FLAG) FROM  HRMS_ASSET_MASTER_DTL "
					+ " INNER JOIN HRMS_ASSET_MASTER ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE) "
					+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE )"
					+ " INNER JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE) "
					+ " WHERE 1=1 "
					+ " GROUP BY ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME ,ASSET_SUBTYPE_CODE, "
					+ " ASSET_SUBTYPE_NAME ,ASSET_ASSISGN_FLAG,ASSET_SUBTYPE_CODE ";

			Object[][] dataObj = getSqlModel().getSingleResult(sqlQuery);
			for(int i=0;i<dataObj.length;i++)
			{
				System.out.println("VALUE IS-------------------"+dataObj[i][0]);
				System.out.println("VALUE IS-------------------"+dataObj[i][1]);
				System.out.println("VALUE IS-------------------"+dataObj[i][2]);
				System.out.println("VALUE IS-------------------"+dataObj[i][3]);
				
				
			}
			if (dataObj != null && dataObj.length > 0) {
				Object [][] transObj =null;
				try {
					transObj = Utility.transverse(dataObj, new int[] { 0 }, 1, 2,"0", true, new String[] { "vishu" });
					System.out.println("transObj ======"+transObj.length);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println("transObj ======"+transObj.length);
				System.out.println("transObj =ffffff====="+transObj[0].length);
				
				for (int i = 0; i < transObj.length; i++) {
					for (int j = 0; j < transObj[0].length; j++) {
						System.out.println("transObj[i][j]-------------"+transObj[i][j]);
					}
				}
			}

		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception------------------" + e);
			e.printStackTrace();

		}// end of catch
		return "true";
	}
}// end of class
