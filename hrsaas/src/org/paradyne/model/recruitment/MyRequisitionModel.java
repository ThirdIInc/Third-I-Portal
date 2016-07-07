/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;

import org.paradyne.bean.Recruitment.MyRequisition;
import org.paradyne.bean.Recruitment.VacancyManagement;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import javax.servlet.http.HttpServletRequest;

/**
 * @author aa0540
 *
 */
public class MyRequisitionModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyRequisitionModel.class);
	/**
	 * this method is to retrieve requisition data which is open and whose status is approved.
	 * @param msg7 
	 * @param msg6 
	 * @param msg5 
	 * @param msg4 
	 * @param msg3 
	 * @param msg2 
	 * @param msg1 
	 * @param msg 
	 * @param vacancyMgmt
	 * @param reStatus
	 * @param approvalStatus
	 */
	public void getRecord(MyRequisition bean, String status,String msg, String msg1, String msg2, String msg3, String msg4, String msg5, String msg6, String msg7, HttpServletRequest request) {
		logger.info("in getRecords ");
		int count=0;
		 String concatStr="";
		bean.setMyStatus(status);
		ArrayList<Object> tableList = new ArrayList<Object>();
		Object[][] reqData = null;
		
		
		
		try {
			String query = getQuery(1);
			System.out.println("1-----------"+bean.getApplyFilterFlag());
			if(bean.getApplyFilterFlag().equals("true")){
			if(!(bean.getRequisitionId().equals("")|| bean.getRequisitionId().equals("null"))) {
				query += " AND  PUB_REQS_CODE ="+bean.getRequisitionId();
				concatStr+=msg+" :"+bean.getRequisitionName()+",";
				/*bean.setRequstionFlag(true);
				bean.setClearFlag(true);*/
			}
		
			if (!(bean.getDivId().equals("") || bean.getDivId().equals("null"))) {
				query += " AND DIV_Id ="+ bean.getDivId();
				concatStr+=msg1+" :"+bean.getDivName()+",";
				
			}
			if (!(bean.getBranchId().equals("") || bean.getBranchId().equals("null"))) {
				query += " AND CENTER_ID =" + bean.getBranchId();
				concatStr+=msg2+" :"+bean.getBranchName()+",";
				
			}
			if (!bean.getDeptId().equals("")) {
				query += " AND DEPT_ID =" + bean.getDeptId();
				concatStr+=msg3+" :"+bean.getDeptName()+",";
				
			}
			if (!(bean.getHrManagerId().equals("")|| bean.getHrManagerId().equals("null"))) {
				query = query + " AND EMP_ID ="+ bean.getHrManagerId();
				concatStr+=msg4+" :"+bean.getManagerName()+",";
				
			}
			if (!(bean.getPositionId().equals("") || bean.getPositionId().equals("null"))) {
				query = query + " AND RANK_Id=" + bean.getPositionId();
				concatStr+=msg5+" :"+bean.getPositionName()+",";
				
			}
						
			if(!(bean.getPubfDate().trim().equals("") || bean.getPubfDate().trim().equals("null"))){
				query= query+" AND PUB_DATE >=TO_DATE("+"'"+bean.getPubfDate()+"'"+",'DD-MM-YYYY')";
				concatStr+=msg6+" :"+bean.getPubfDate()+",";
			}
			if(!(bean.getPubtDate().trim().equals("") || bean.getPubtDate().trim().equals("null"))){
				query= query+" AND PUB_DATE <=TO_DATE("+"'"+bean.getPubtDate()+"'"+",'DD-MM-YYYY')";
				concatStr+=msg7+" :"+bean.getPubtDate()+",";
				
			}
			}
			else{
				
				bean.setApplyFilterFlag("false");
			}
			query = query + " 	ORDER BY REQS_DATE DESC";
			System.out.println("2---"+bean.getUserEmpId());
			System.out.println("3---"+status);
			
			reqData = getSqlModel().getSingleResult(query, new Object []{bean.getUserEmpId(), status});
			
			if(reqData!=null && reqData.length>0)
			{
				bean.setModeLength("true");	
			}//end of if 
			else
			{
				bean.setModeLength("false");	
			}//end of else
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),reqData.length, 20);	
			
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"))
				bean.setMyPage("1");
						
			if (reqData.length > 0 && reqData!=null) {//this if to check if query contains data or not
				for (int i =Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) {//loop for req query
					 MyRequisition bean1 = new MyRequisition();
					 
					 bean1.setReqCode(String.valueOf(reqData[i][1]));//req code
					 bean1.setReqName(String.valueOf(reqData[i][2]));//req name
					 bean1.setPosition(String.valueOf(reqData[i][3]));//position
					 bean1.setAppliedBy(String.valueOf(reqData[i][4]));//applied by
					 bean1.setHiringMgr(String.valueOf(reqData[i][5]));//hiring manager
					 bean1.setPublishDate(String.valueOf(reqData[i][6]));//publish date
					 bean1.setNoOfVacancies(String.valueOf(reqData[i][7]));//no of vacancies
					 bean1.setRequiredDate(checkNull(String.valueOf(reqData[i][8])));//required date
					
					 tableList.add(bean1);//data added in list
				}
				bean.setList(tableList);	//table list have been set
				bean.setTotalRecords(String.valueOf(reqData.length));
				
			}
			else{//if reqData is null
				bean.setNoData("true");//No data to display message shown
				bean.setList(null);
				bean.setTotalRecords(String.valueOf(reqData.length));
				
				
			}
			
		}//end of try block
		catch (Exception e) {
			logger.error("exception in getRecord method",e);
		}//end of catch block
		try{ 
			  String [] dispArr = concatStr.split(",");
			  request.setAttribute("dispArr", dispArr);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}//end of getRecord method

	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result value of the data
	 * @return String
	**/
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if
		else {
			return result;
		}	//end of else
	}
}// end of main method
