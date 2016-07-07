/**
 * 
 */
package org.paradyne.model.helpdesk;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.helpdesk.HelpDeskSubReqType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0623
 *
 */
public class HelpDeskSubReqTypeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskSubReqTypeModel.class);

	public String addSubRequestTypes(HelpDeskSubReqType subReqType) {
		Object[][] add = new Object[1][7];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = subReqType.getReqTypeCode();  
			add[0][1] = subReqType.getSubReqType();  
			add[0][2] = subReqType.getSlaCode();
			
			if( subReqType.getIsLinkAsset().equals("true")){
				add[0][3] = "Y"; //is manager approved
			}else{
				add[0][3] = "N";
			}
			add[0][4] = subReqType.getAssetTypeId();
			if( subReqType.getIsActive().equals("true")){
				add[0][5] = "Y"; //is active
			}else{
				add[0][5] = "N";
			}
			if (subReqType.getIsManagerApproved().equals("true")) {
				add[0][6] = "Y"; // is manager approved
			} else {
				add[0][6] = "N";
			}
			
			if (!checkDuplicate(subReqType)) {
				String query = " INSERT INTO HELPDESK_REQUEST_SUBTYPE (REQUEST_SUBTYPE_ID,REQUEST_TYPE_ID,"
					+ " REQUEST_SUBTYPE_NAME,REQUEST_SUBTYPE_SLA, IS_ASSET_REQUEST, ASSET_TYPE_CODE, IS_ACTIVE, IS_MANAGER_APPROVAL) " 
					+ " VALUES((SELECT NVL(MAX(REQUEST_SUBTYPE_ID),0)+1 FROM HELPDESK_REQUEST_SUBTYPE),?,?,?,?,?,?,?)";
				result = getSqlModel().singleExecute(query, add);
				if (result) {
					String maxQueryC = " SELECT MAX(REQUEST_SUBTYPE_ID) FROM HELPDESK_REQUEST_SUBTYPE";
					Object[][] dataC = getSqlModel().getSingleResult(maxQueryC);
					subReqType.setSubReqTypeCode(String.valueOf(dataC[0][0]));

					String maxQuery = " SELECT REQUEST_TYPE_ID FROM HELPDESK_REQUEST_SUBTYPE " +
							" WHERE REQUEST_SUBTYPE_ID="+subReqType.getSubReqTypeCode();
					Object[][] data = getSqlModel().getSingleResult(maxQuery);
					subReqType.setReqTypeCode(String.valueOf(data[0][0]));
				
					flag = "saved";
				} else {
					flag = "error";
				}
			} else {
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was raised-->"+e);
			e.printStackTrace();
		}
		return flag;
	}

	private boolean checkDuplicate(HelpDeskSubReqType subReqType) {
		boolean result = false;
		try {
			String query = "SELECT * FROM HELPDESK_REQUEST_SUBTYPE WHERE UPPER(REQUEST_SUBTYPE_NAME) LIKE '"
					+ subReqType.getSubReqType().trim().toUpperCase()
					+ "' AND REQUEST_TYPE_ID=" + subReqType.getReqTypeCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String modSubRequestTypes(HelpDeskSubReqType subReqType) {
		// to get the data for update the record
		Object[][] data = new Object[1][6];
		String editFlag = "";
		boolean result = false;
		try {
			logger.info("subReqType.getReqTypeCode()  :"+subReqType.getReqTypeCode());
			data[0][0] = subReqType.getReqTypeCode();
			data[0][1] = subReqType.getSubReqType().trim();
			data[0][2] = subReqType.getSlaCode();
			/*if( subReqType.getIsLinkAsset().equals("true")){
				data[0][3] = "Y"; //is asset
				data[0][4]=subReqType.getAssetTypeId();
			}else{
				data[0][3] = "N";
				data[0][4]="";
			}*/
			if( subReqType.getIsActive().equals("true")){
				data[0][3] = "Y"; //is active
			}else{
				data[0][3] = "N";
			}
			if (subReqType.getIsManagerApproved().equals("true")) {
				data[0][4] = "Y"; // is manager approved
			} else {
				data[0][4] = "N";
			}
			data[0][5] = subReqType.getSubReqTypeCode();
			//data[0][6] = subReqType.getHidReqTypeCode();
			if (!checkDuplicateMod(subReqType)) {
				// to get the data for modifying the record
				String query = "UPDATE HELPDESK_REQUEST_SUBTYPE SET REQUEST_TYPE_ID=?, REQUEST_SUBTYPE_NAME=?,REQUEST_SUBTYPE_SLA=?, IS_ACTIVE=?, IS_MANAGER_APPROVAL=? WHERE REQUEST_SUBTYPE_ID=? ";
				result = getSqlModel().singleExecute(query, data);
				if (result) {
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised--->");
		}
		return editFlag;
	}

	private boolean checkDuplicateMod(HelpDeskSubReqType subReqType) {
		boolean result = false;
		Object[][] data = null;
		Object[] value = new Object[1];
		try {

			value[0] = subReqType.getReqType().trim().toUpperCase();
		} catch (Exception e) {
			
		}
		try {
			String query = "SELECT * FROM HELPDESK_REQUEST_SUBTYPE WHERE UPPER(REQUEST_SUBTYPE_NAME) LIKE '"
					+ subReqType.getReqType().trim().toUpperCase()
					+ "' AND REQUEST_SUBTYPE_ID not in(" + subReqType.getReqTypeCode() + ") "
					+ " AND REQUEST_TYPE_ID="+subReqType.getReqTypeCode();
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			
		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	public void getRecords(HelpDeskSubReqType subReqType,
			HttpServletRequest request) {
		try {
			int length=0;	
			String query = " SELECT ROWNUM, HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID, NVL(HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,' '), "   
					+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID, NVL(HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_NAME,' '), "
					+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA, NVL(HELPDESK_SLA_HDR.SLA_NAME,' '),  "
					+ " NVL(HELPDESK_REQUEST_SUBTYPE.IS_MANAGER_APPROVAL,' '), "
					+ " NVL(DECODE(HELPDESK_REQUEST_SUBTYPE.IS_MANAGER_APPROVAL,'Y','Yes','N','No'),' '), "
					+ " DECODE (HELPDESK_REQUEST_SUBTYPE.IS_ACTIVE,'Y','Yes','N','No','No') "
					+ " FROM HELPDESK_REQUEST_SUBTYPE "
					+ " LEFT JOIN HELPDESK_REQUEST_TYPE  ON (HELPDESK_REQUEST_TYPE .REQUEST_TYPE_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID) "
					+ " LEFT JOIN HELPDESK_SLA_HDR ON (HELPDESK_SLA_HDR.SLA_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA) "
					+ " ORDER BY HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID, HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID";

			Object[][] data = getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				subReqType.setModeLength("true");
				subReqType.setTotalRecords(String.valueOf(data.length));  //   to  display the total number of record in  the list 
			
			String[] pageIndex = Utility.doPaging(subReqType.getMyPage(),data.length, 20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"))
				subReqType.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 	
				HelpDeskSubReqType subReqType1 = new HelpDeskSubReqType();
				
				subReqType1.setSrNoItr(String.valueOf(data[i][0]));   
				subReqType1.setReqTypeCodeItr(String.valueOf(data[i][1]));   
				subReqType1.setReqTypeItr(String.valueOf(data[i][2]).trim());
				subReqType1.setSubReqTypeCodeItr(String.valueOf(data[i][3]));   
				subReqType1.setSubReqTypeItr(String.valueOf(data[i][4]).trim());  
				subReqType1.setSlaCodeItr(String.valueOf(data[i][5]));       
				subReqType1.setSlaNameItr(String.valueOf(data[i][6]).trim());
				if (String.valueOf(data[i][7]).equals("Y")) {
					subReqType1.setIsManagerApprovedItt("Yes");
				}else{
					subReqType1.setIsManagerApprovedItt("No");
				}
				subReqType1.setIsActiveItr(String.valueOf(data[i][9]).trim());
				List.add(subReqType1);
			}
			
			subReqType.setSubReqTypeList(List);
			length=data.length;
			subReqType.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean deleteReqType(HelpDeskSubReqType subReqType) {
		// to delete the single record after clicking on saving or searching button
		String query= " DELETE FROM HELPDESK_REQUEST_SUBTYPE WHERE REQUEST_SUBTYPE_ID="
			+subReqType.getSubReqTypeCode();
		return getSqlModel().singleExecute(query);
	}

	public boolean delChkdRec(HelpDeskSubReqType subReqType, String[] subReqCode) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < subReqCode.length; i++) {
			if (!subReqCode[i].equals("")) {

				Object[][] delete = new Object[1][1];
				delete[0][0] = subReqCode[i];
				logger.info("subReqCode  in delete : "+delete[0][0]);
				String query= " DELETE FROM HELPDESK_REQUEST_SUBTYPE WHERE REQUEST_SUBTYPE_ID=?";
				result = getSqlModel().singleExecute(query, delete);
				if (!result) {
					count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public void getSubReqTypeOnDblClick(HelpDeskSubReqType subReqType, HttpServletRequest request) {
		try {

			String query = " SELECT HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID, NVL(HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,' '), " 
				+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID, NVL(HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_NAME,' '), "
				+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA, NVL(HELPDESK_SLA_HDR.SLA_NAME,' '), "
				+ " NVL(HELPDESK_REQUEST_SUBTYPE.IS_ASSET_REQUEST,' '), "
				+ " HELPDESK_REQUEST_SUBTYPE.ASSET_TYPE_CODE, NVL(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_NAME,' '), " 
				+ " HELPDESK_REQUEST_SUBTYPE.IS_ACTIVE, "
				+ " HELPDESK_REQUEST_SUBTYPE.IS_MANAGER_APPROVAL "
				+ " FROM HELPDESK_REQUEST_SUBTYPE "  
				+ " LEFT JOIN HELPDESK_REQUEST_TYPE  ON (HELPDESK_REQUEST_TYPE .REQUEST_TYPE_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID) "  
				+ " LEFT JOIN HELPDESK_SLA_HDR ON (HELPDESK_SLA_HDR.SLA_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA) "
				+ " LEFT JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE = HELPDESK_REQUEST_SUBTYPE.ASSET_TYPE_CODE) "	
				+ " WHERE HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID= "
				+subReqType.getHiddenSubReqCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				subReqType.setReqTypeCode(String.valueOf(data[0][0]));   
				subReqType.setReqType(String.valueOf(data[0][1]).trim());
				subReqType.setSubReqTypeCode(String.valueOf(data[0][2]));   
				subReqType.setSubReqType(String.valueOf(data[0][3]).trim());  
				subReqType.setSlaCode(String.valueOf(data[0][4]));       
				subReqType.setSlaName(String.valueOf(data[0][5]).trim());
			 
				if (String.valueOf(data[0][6]).equals("Y")) {
					subReqType.setIsLinkAsset("true");
					 request.setAttribute("assetStatus", "Y");
				}else{
					 request.setAttribute("assetStatus", "N");
				} 
				subReqType.setAssetTypeId(String.valueOf(data[0][7]));  	
				subReqType.setAssetType(String.valueOf(data[0][8]));  	
				if (String.valueOf(data[0][9]).equals("Y")) {
					 subReqType.setIsActive("true");
					}
				if (String.valueOf(data[0][10]).equals("Y")) {
					subReqType.setIsManagerApproved("true");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getSubRequestTypes(HelpDeskSubReqType subReqType, HttpServletRequest request) {
		try {

			String query = " SELECT HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID, NVL(HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,' '),"   
				+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID, NVL(HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_NAME,' '), "
				+ " HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA, NVL(HELPDESK_SLA_HDR.SLA_NAME,' '), "
				+ " NVL(HELPDESK_REQUEST_SUBTYPE.IS_ASSET_REQUEST,' '), "
				+ " ASSET_TYPE_CODE, NVL(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_NAME,' ')," 
				+ " HELPDESK_REQUEST_SUBTYPE.IS_ACTIVE, HELPDESK_REQUEST_SUBTYPE.IS_MANAGER_APPROVAL "  
				+ " FROM HELPDESK_REQUEST_SUBTYPE  "
				+ " LEFT JOIN HELPDESK_REQUEST_TYPE  ON (HELPDESK_REQUEST_TYPE .REQUEST_TYPE_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_TYPE_ID) "  
				+ " LEFT JOIN HELPDESK_SLA_HDR ON (HELPDESK_SLA_HDR.SLA_ID = HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_SLA) "
				+ " LEFT JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE = HELPDESK_REQUEST_SUBTYPE.ASSET_TYPE_CODE) "	
				+ " WHERE HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID= "
				+subReqType.getSubReqTypeCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				subReqType.setReqTypeCode(String.valueOf(data[0][0]));   
				subReqType.setReqType(String.valueOf(data[0][1]).trim());
				subReqType.setSubReqTypeCode(String.valueOf(data[0][2]));   
				subReqType.setSubReqType(String.valueOf(data[0][3]).trim());  
				subReqType.setSlaCode(String.valueOf(data[0][4]));       
				subReqType.setSlaName(String.valueOf(data[0][5]).trim());
			 
				 if (String.valueOf(data[0][6]).equals("Y")) {
					subReqType.setIsLinkAsset("true");
					 request.setAttribute("assetStatus", "Y");
				}else{
					 request.setAttribute("assetStatus", "N");
				} 
				subReqType.setAssetTypeId(String.valueOf(data[0][7]));  	
				subReqType.setAssetType(String.valueOf(data[0][8]));  	
				if (String.valueOf(data[0][9]).equals("Y")) {
					 subReqType.setIsActive("true");
					}
				if (String.valueOf(data[0][10]).equals("Y")) {
					subReqType.setIsManagerApproved("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
