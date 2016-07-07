package org.paradyne.model.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.admin.master.LeaveMaster;
import org.paradyne.bean.leave.LeaveReason;

public class LeaveReasonModel extends  ModelBase{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);

	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
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
	
	/* method name : Data 
	 * purpose     : to show the assets type in the list
	 * return type : void
	 * parameter   : LeaveReason instance, HttpServletRequest request
	 */
	public void Data(LeaveReason bean, HttpServletRequest request) {
		Object [][] obj = getSqlModel().getSingleResult(getQuery(5));
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
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
			   bean.setMyPage("1");
		  			
		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {

			 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
				LeaveReason  bean1 = new LeaveReason ();

				bean1.setReaId(checkNull(String.valueOf(obj[i][0])));
				bean1.setReaName(checkNull(String.valueOf(obj[i][1])));
				bean1.setIsActive(checkNull(String.valueOf(obj[i][2])));

				list.add(bean1);
			}
			
			bean.setTotalRecords(""+obj.length);
		}
		else{				
			bean.setNoData("true");
		}			
			
	    if(list.size()>0)
	    	bean.setNoData("false");
	    else
	    	bean.setNoData("true");
	    bean.setListIterator(list);
	}

	public String save1(LeaveReason bean) {
		
		String id = bean.getReaId().trim();
		boolean result = false;
		String msg = "";
		if(id.equals("")) {
			Object[][] obj = new Object[1][2];
			obj[0][0] = bean.getReaName();
			obj[0][1] = bean.getIsActive();
			result = getSqlModel().singleExecute(getQuery(1),obj);
		}
		else {
			Object[][] obj = new Object[1][3];
			obj[0][0] = bean.getReaName();
			obj[0][1] = bean.getIsActive();
			obj[0][2] = bean.getReaId();
			result = getSqlModel().singleExecute(getQuery(2),obj);
		}
		if(result)
			return "Record Saved Successfully.";
		else
			return "Duplicate Record Found.";
		
	}
	
	
		public boolean save(LeaveReason bean) {
			boolean result = false;
	 	try {
			
			if(!checkDuplicate(bean))
			{
				Object[][] obj = new Object[1][2];
				obj[0][0] = bean.getReaName();
				obj[0][1]=bean.getIsActive();
				result = getSqlModel().singleExecute(getQuery(1), obj);
				
				String selectQuery = " SELECT NVL(MAX(REASON_CODE),0) FROM HRMS_LEAVE_REASON ";
				Object reasonCodeObj[][] = getSqlModel().getSingleResult(
						selectQuery);
				
				if (result)
				{
					bean.setReaId(String.valueOf(reasonCodeObj[0][0]));
					result = true ; 
				}
				 
			}
			else
				
				result = false ;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result ;
	}
		
		
		public boolean update(LeaveReason bean) {
			boolean result = false;
	 	try {
			
			if(!checkDuplicateMod(bean))
			{
				Object[][] obj = new Object[1][3];
				obj[0][0] = bean.getReaName();
				obj[0][1] = bean.getIsActive();
				obj[0][2] = bean.getReaId();
			  result = getSqlModel().singleExecute(getQuery(2), obj);
				if (result)
					result=true ;
				else
					result = false ;
				 
			}
			else
				
				result = false ;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result ;
	}
		
		
		public boolean checkDuplicateMod(LeaveReason bean){
			boolean result=false;
			try {
				String query = " SELECT * FROM HRMS_LEAVE_REASON WHERE UPPER(REASON_NAME) LIKE '"
						+ bean.getReaName().trim().toUpperCase()
						+ "' AND REASON_CODE not in(" + bean.getReaId() + ")";
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					result = true;
				}//end of if
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;

		}
		
		public boolean checkDuplicate(LeaveReason bean){
			boolean result=false;
			try {
				String query = " SELECT * FROM HRMS_LEAVE_REASON WHERE UPPER(REASON_NAME) LIKE '"
						+ bean.getReaName().trim().toUpperCase() + "'";
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					result = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;

		}
 		 
	public void getRecord(LeaveReason bean) {
		
		try {
			String[] obj = new String[1];
			obj[0] = bean.getReaId();
			Object[][] data = getSqlModel().getSingleResult(getQuery(4), obj);
			bean.setReaName(checkNull(String.valueOf(data[0][0])));
			bean.setIsActive(checkNull(String.valueOf(data[0][1])));
		} catch (Exception e) {
			logger.error("Exception in getRecord -- "+e);
		}
	}

	public boolean delete(LeaveReason bean) {
		Object delObj[][]=new Object[1][1];
		delObj[0][0]=bean.getReaId().trim();
		return getSqlModel().singleExecute(getQuery(3),delObj);
	}

	public boolean deleteCheckedRecords(LeaveReason bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {

				if(!code[i].equals("")){

					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(3), delete);
					if(!result)
						count++;

				}// end of if
			}// end of for loop
		}// end of if
		if(count!=0)
		{
		result=false;
		return result;
		}// end of if
		else
		{
		result=true;
		return result; 
		}// end of else		
	}

	public void getReport(LeaveReason reaMast, HttpServletRequest request,
			HttpServletResponse response, ServletContext context, String[] label) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nLeave Reason\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName,"");
		rg.setFName("Leave Reason.Pdf");
						
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		Object[][] Data = new Object[data.length][3];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][1];
				Data[i][2] = data[i][2];
				
				j++;
			}
			int cellwidth[] = { 20, 60 , 20 };
			int alignment[] = { 1, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	} 
	
}
