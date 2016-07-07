package org.paradyne.model.vendor;

/** @ Author : Archana Salunkhe
 * @ purpose : Developed to define Vendor Reporting Structure 
 * @ Date : 03-May-2012
 */
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.vendor.VendorReportStructure;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class VendorReportStructureModel  extends ModelBase{

	/**To Show record of Reporting Structure in List */
	public void getPartnerReportList(VendorReportStructure reportBean, HttpServletRequest request){
		
		Object[][] reportListObj = getSqlModel().getSingleResult(getQuery(1));		
		if (reportListObj != null && reportListObj.length > 0) {
			reportBean.setModeLength("true");		
			reportBean.setTotalRecords(String.valueOf(reportListObj.length));

			String[] pageIndex = Utility.doPaging(reportBean.getMyPage(),
					reportListObj.length, 20);// to display the page number
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to set the total number of page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				reportBean.setMyPage("1");
			
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++){
				VendorReportStructure bean = new VendorReportStructure();
				bean.setPartnerCodeItt(String.valueOf(reportListObj[i][0]));
				bean.setPartnerNameItt(String.valueOf(reportListObj[i][1]));
				bean.setApproverNameItt(String.valueOf(reportListObj[i][2]));
				bean.setApproverTypeItt(String.valueOf(reportListObj[i][3]));
				bean.setLevel(String.valueOf(reportListObj[i][4]));
				bean.setApproverCodeItt(String.valueOf(reportListObj[i][5]));
				bean.setReportingID(String.valueOf(reportListObj[i][6]));
				list.add(bean);
			}
			reportBean.setReportStrList(list);
		}		
	}
	
	/**To edit any record in the list by double clicking on it
	 * @ return String*/
	public void callForEdit(VendorReportStructure vendorReport){
		
		Object[] paramObj = new Object[1];
		paramObj[0] = vendorReport.getReportingID();
		//paramObj[1] = vendorReport.getHiddenApproverID();
		
		Object [][] reportDataObj = getSqlModel().getSingleResult(getQuery(2),paramObj);
		if(reportDataObj != null && reportDataObj.length >0){
			
			vendorReport.setPartnerCode(String.valueOf(reportDataObj[0][0]));
			vendorReport.setPartnerName(String.valueOf(reportDataObj[0][1]));
			vendorReport.setApproverName(String.valueOf(reportDataObj[0][2]));
			vendorReport.setApproverType(String.valueOf(reportDataObj[0][3]));
			vendorReport.setLevel(String.valueOf(reportDataObj[0][4]));
			vendorReport.setApproverToken(String.valueOf(reportDataObj[0][5]));
			vendorReport.setApproverCode(String.valueOf(reportDataObj[0][6]));		
			vendorReport.setReportingID(String.valueOf(reportDataObj[0][7]));
		}
	}
	/**To Save record in database
	 * @ return boolean*/
	public boolean save(HttpServletRequest request, VendorReportStructure reportStrBean){
		boolean result=false;
		try {
			String query= "SELECT MAX(REPORTING_ID)+1 FROM VENDOR_REPORTING_STR";
			Object[][] idObj = getSqlModel().getSingleResult(query);
			/*Object [][] insertObj =new Object[1][5];
			
			insertObj[0][0]= reportStrBean.getPartnerCode();
			insertObj[0][1]= reportStrBean.getApproverCode();
			insertObj[0][2]= reportStrBean.getApproverType().trim();
			insertObj[0][3]= reportStrBean.getLevel();	
			insertObj[0][4]= String.valueOf(idObj[0][0]);
			for(int i =0; i<insertObj[0].length; i++){
				System.out.println("insertObj-----"+insertObj[0][i]);
			}
			if(insertObj != null && insertObj.length >0){				
				result = getSqlModel().singleExecute(getQuery(3),insertObj);
			}*/
		result= getSqlModel().singleExecute("INSERT INTO VENDOR_REPORTING_STR (PARTNER_CODE,APPROVER_CODE, APPROVER_TYPE ,APPROVER_LEVEL,REPORTING_ID)"+
					 "VALUES("+reportStrBean.getPartnerCode()+","
					 +reportStrBean.getApproverCode()+",'"+
					 reportStrBean.getApproverType()+"',"+
					 reportStrBean.getLevel()+","+ String.valueOf(idObj[0][0])+")");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**To update record
	 * @ return boolean*/
	public boolean update(VendorReportStructure bean){
		boolean result=false;
		try {
			Object [][] updateObj= new Object [1][5];
			updateObj[0][0]=bean.getPartnerCode();
			updateObj[0][1]= bean.getApproverCode();
			updateObj[0][2]= bean.getApproverType();
			updateObj[0][3]= bean.getLevel();
			updateObj[0][4]= bean.getReportingID();
			
			if(updateObj != null && updateObj.length >0){				
				result = getSqlModel().singleExecute(getQuery(4),updateObj);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
