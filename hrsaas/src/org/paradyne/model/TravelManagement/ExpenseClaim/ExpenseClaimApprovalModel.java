/**
 * 
 */
package org.paradyne.model.TravelManagement.ExpenseClaim;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.paradyne.bean.Asset.AssetApproval;
import org.paradyne.bean.TravelManagement.ExpenseClaim.ExpenseClaimApp;
import org.paradyne.bean.TravelManagement.ExpenseClaim.ExpenseClaimApproval;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0554
 *
 */
public class ExpenseClaimApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/* method name : getRecords 
	 * purpose     : to display the applications in tabular format according to the selected status
	 * return type : void
	 * parameter   : AssetApproval instance, String status
	 */
	public void getRecords(ExpenseClaimApproval bean, String status, HttpServletRequest request){

		Object empObject[] = new Object[3];
		empObject[0]	   =   status; 
		empObject[1]	   =	bean.getUserEmpId();
		empObject[2]	   =	bean.getUserEmpId();
		logger.info("status================"+status);
		logger.info("status======bean.getUserEmpId()=========="+bean.getUserEmpId());
		logger.info("status========bean.getUserEmpId()========"+bean.getUserEmpId());
		
		Object [][]result=null;
		
		result=getSqlModel().getSingleResult(getQuery(1),empObject);
		
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1 = 0;
		int PageNo1 = 1;// ----------
		REC_TOTAL = result.length;
		int no_of_pages = Math.round(REC_TOTAL / 20);
		// PageNo = Integer.parseInt(bean.getMyPage());//-----------
		double row = (double) result.length / 20.0;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount1 = Integer.parseInt(value1.setScale(0,
				java.math.BigDecimal.ROUND_UP).toString());

		ArrayList<Object> list = new ArrayList<Object>();
		request.setAttribute("abc", rowCount1);

		// PageNo
		if (String.valueOf(bean.getMyPage()).equals("null")
				|| String.valueOf(bean.getMyPage()).equals(null)
				|| String.valueOf(bean.getMyPage()).equals(" ")) {
			PageNo1 = 1;
			From_TOT = 0;
			To_TOT = 20;

			if (To_TOT > result.length) {
				To_TOT = result.length;
			}
			logger.info("-----------To_TOTAL----null-----" + To_TOT);
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
			if (To_TOT > result.length) {
				To_TOT = result.length;
			}
		}
		request.setAttribute("xyz", PageNo1);
		logger.info("------------from total--------" + From_TOT);
		logger.info("----------to total----------" + To_TOT);
		
		
		
		String pathQuery="SELECT NVL(EXP_APP_PATH_APPROVER_REMARK,'') FROM HRMS_TMS_EXP_APP_PATH WHERE EXP_APP_PATH_APPROVER_CODE="+bean.getUserEmpId()
		+" AND EXP_APP_PATH_ID= ?";
		ArrayList<Object> appList = new ArrayList<Object>();
		try{
			logger.info("result.length+++++"+result.length);
		}
		catch(Exception e){
			
		}
		bean.setStatusFld(status);
		if(result!=null && result.length!=0){
			/*
			 * set the application data in the list
			 * 
			 */
			for (int i = From_TOT; i < To_TOT; i++) {
				ExpenseClaimApproval approval = new ExpenseClaimApproval();
				approval.setClaimAppId(checkNull(String.valueOf(result[i][0])));
				approval.setEmpToken(checkNull(String.valueOf(result[i][1])));
				approval.setEmpName(checkNull(String.valueOf(result[i][2])));
				approval.setLevel(checkNull(String.valueOf(result[i][3])));
				approval.setAppDate(checkNull(String.valueOf(result[i][4])));
				approval.setEmpId(checkNull(String.valueOf(result[i][5])));
				approval.setReqName(checkNull(String.valueOf(result[i][6])));
				
				if(status.equals("A"))
					approval.setStatusNew("Approved");
				if(status.equals("R"))
					approval.setStatusNew("Rejected");

				logger.info("size of list=="+appList.size());
				Object [][] data=getSqlModel().getSingleResult(pathQuery,new Object []{String.valueOf((result[i][0]))});
				if(data==null || data.length==0 || data.equals(null) ){
					logger.info("inside if data");
					approval.setRemark("");
				}  // end of if
				else {
					logger.info("inside elst data");
					approval.setRemark(checkNull(String.valueOf(data[0][0])));
				}  // end of else

				if(!bean.getStatusFld().equals("P")){
					approval.setApprflag("true");
					logger.info("inside ifffffffff   "+approval.getApprflag());
				}  // end of if
				else{
					approval.setApprflag("false");
					logger.info("inside elseeeeeee   "+approval.getApprflag());
				}  // end of else
				bean.setNoData("false");
				appList.add(approval);

			}	  // end of for loop

			bean.setListLength(String.valueOf(appList.size()));
		}  // end of if
		else{
			bean.setNoData("true");
			bean.setListLength("0");
		}  // end of else
		

		logger.info("setListLength=="+bean.getListLength()+"appList.size())=="+appList.size());
		bean.setClaimList(appList);
		logger.info("setApprflag=="+bean.getApprflag());
	}
	
	/* method name : checkNull 
	 * purpose     : to check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/* method name : changeApplStatus 
	 * purpose     : to change the application status according to the selected by the approve
	 * 					i.e. Approved or Rejected
	 * return type : booleane
	 */
	public boolean changeApplStatus(ExpenseClaimApproval bean, Object [][]empFlow, String cliamId,String empCode, HttpServletRequest request){
		boolean result=false;

		Object[][] to_mailIds =new Object[1][1];	
		Object[][] from_mailIds =new Object[1][1];	

		if(empFlow!=null ){
			/*
			 * update the approver code in the application to the next approver code
			 * 
			 */
			if(empFlow.length!=0){
				Object [][]updateApprover = new Object[1][4];
				updateApprover[0][0]	  = empFlow[0][2];
				updateApprover[0][1]	  = empFlow[0][0];
				updateApprover[0][2]	  = empFlow[0][3];
				updateApprover[0][3]	  = cliamId;

				logger.info("level  "+updateApprover[0][0]+" app "+updateApprover[0][1]+" cliamId  "+updateApprover[0][2]);
				result	= getSqlModel().singleExecute(getQuery(2), updateApprover);
				/*
				 * send the mail to the next approver
				 * 
				 */
				/*try {

					to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
					from_mailIds[0][0]=empCode;

					MailUtility mail=new MailUtility();
					mail.initiate(context, session);
					logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "P");

					mail.terminate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("exception in send mail to next approver()"+e);
				}*/
			}  // end of if
			else{
				Object[][]statusChanged = new Object[1][2];
				statusChanged[0][0]     = "A";
				statusChanged[0][1]		= cliamId;

				result= getSqlModel().singleExecute(getQuery(3), statusChanged);
				
				/*try {

					to_mailIds[0][0]=empCode;
					from_mailIds[0][0]=bean.getUserEmpId();

					MailUtility mail=new MailUtility();
					mail.initiate(context, session);
					logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "A");

					mail.terminate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("exception in changeApplStatus()=="+e);
				}*/

			}  // end of else
		}  // end of if
		/*
		 * change the application status to 'Approved'
		 * 
		 */
		else{
			Object[][]statusChanged = new Object[1][2];
			statusChanged[0][0]     = "A";
			statusChanged[0][1]		= cliamId;

			result= getSqlModel().singleExecute(getQuery(3), statusChanged);
		
			/*try {

				to_mailIds[0][0]=empCode;
				from_mailIds[0][0]=bean.getUserEmpId();

				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
				logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "A");

				mail.terminate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("exception in changeApplStatus()=="+e);
			}*/
			
		}  // end of if
		getRecords(bean,bean.getStatusFld(),request);
		return result;
	}
	
	/* method name : forward 
	 * purpose     : to insert the approver details in HRMS_TMS_EXP_APP_PATH table and
	 * 					forward the application to the next approver
	 * return type : boolean
	 * parameter   : ExpenseClaimApproval instance, String status, String claimId, String empCode, String comments
	 */
	public boolean forward(ExpenseClaimApproval bean, String status, String claimId, String empCode,String comments, HttpServletRequest request) {
		Object [][] changeStatus = new Object[1][4];
		boolean result = false;
		//bean.setRemark();
		changeStatus[0][0] = claimId;
		changeStatus[0][1] = bean.getUserEmpId();
		changeStatus[0][2] = comments;
		changeStatus[0][3] = status;
		
		Object[][] to_mailIds =new Object[1][1];	
		Object[][] from_mailIds =new Object[1][1];	




		if(status.equals("A")){
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);           // Insert the approver details with its comments in HRMS_TMS_EXP_APP_PATH table
		}  // end of if
		else if(String.valueOf(status).equals("R")){
			Object[][]reject = new Object[1][2];
			reject[0][0]	 = String.valueOf(status);
			reject[0][1]	 = String.valueOf(claimId);

			result = getSqlModel().singleExecute(getQuery(3), reject);   // change the application status to 'Rejected'
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);   // Insert the approver details with its comments in HRMS_TMS_EXP_APP_PATH table


		}  // end of else if
		
		/*if (status.equals("R")) {
			try {

				to_mailIds[0][0] = empCode;
				from_mailIds[0][0] = bean.getUserEmpId();

				MailUtility mail = new MailUtility();
				mail.initiate(context, session);
				logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
				logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
				mail.sendMail(to_mailIds, from_mailIds, "Asset", "", status);

				mail.terminate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		getRecords(bean,bean.getStatusFld(),request);
		return result;
	}
	
	public boolean approveEmp(ExpenseClaimApproval exClaimApp,Object empFlow[][]) {
		// TODO Auto-generated method stub
		boolean result =false;
			String updateApplStatus ="UPDATE HRMS_TMS_EXP_APP SET EXP_APP_STATUS = 'A' WHERE EXP_APP_ID = "+exClaimApp.getHiddenClaimId();
			
		if(empFlow != null && empFlow.length !=0){		
			// check whether next approver is available or not
			Object [][]updateApprover = new Object[1][4]; 
			updateApprover[0][0]	  = empFlow[0][2];
			updateApprover[0][1]	  = empFlow[0][0];
			updateApprover[0][2]	  = empFlow[0][3];
			updateApprover[0][3]	  = exClaimApp.getHiddenClaimId();
			
			String updateApproverQuery ="UPDATE HRMS_TMS_EXP_APP SET EXP_APP_LEVEL = ?, EXP_APP_APPROVER = ? ,EXP_APP_ALTER_APPROVER=? WHERE EXP_APP_ID = ? ";
		
			result = getSqlModel().singleExecute(updateApproverQuery,updateApprover);				// change approver & increment the level 
			}else{
				result = getSqlModel().singleExecute(updateApplStatus);				// update the status to 'Approved'
			}
			if(result){
				Object [][] pathObject = new Object [1][4];
				pathObject [0][0] = exClaimApp.getHiddenClaimId();
				pathObject [0][1] = exClaimApp.getUserEmpId();
				pathObject [0][2] = exClaimApp.getApproverComments();
				pathObject [0][3] = "A";
				
				result = getSqlModel().singleExecute(getQuery(4),pathObject);
			}
		
		return result;
	}
	
	public boolean rejectEmp(ExpenseClaimApproval exClaimAppr) {
		boolean result =false;
		
		
		String updateApplStatus ="UPDATE HRMS_TMS_EXP_APP SET EXP_APP_STATUS = 'R' WHERE EXP_APP_ID = "+exClaimAppr.getHiddenClaimId();
			
				result = getSqlModel().singleExecute(updateApplStatus);				// update the status to 'Rejected'
			
			if(result){
				Object [][] pathObject = new Object [1][4];
				pathObject [0][0] = exClaimAppr.getHiddenClaimId();
				pathObject [0][1] = exClaimAppr.getUserEmpId();
				pathObject [0][2] = exClaimAppr.getApproverComments();
				pathObject [0][3] = "R";
				
				result = getSqlModel().singleExecute(getQuery(4),pathObject);
			}
		
		return result;
	}
}
