package org.paradyne.model.voucher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.voucher.VoucherMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;


/**
 * @author mangeshj
 * Date 21/012/2007
 * VoucherMasterModel class to write the business logic to add, update, view the voucher heads
 */

public class VoucherMasterModel extends ModelBase{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	VoucherMaster voucher;
	
	/* method name : addVoucher
	 * purpose     : to add the voucher head
	 * return type : String
	 * parameter   : VoucherMaster instance
	 */
	public String addVoucher(VoucherMaster bean) {
		Object addObj[][]=new Object[1][1];
		addObj[0][0]=bean.getVoucherHead();
		String query="SELECT NVL(MAX(VCH_CODE),0)+1 FROM HRMS_VCH_HD";
		
		Object [][] repData = getSqlModel().getSingleResult(query);
		boolean result= getSqlModel().singleExecute(getQuery(1),addObj);   // insert into HRMS_VCH_HD

		if(result)
		{
			if(repData!=null&& repData.length>0)
				bean.setVoucherCode(String.valueOf(repData[0][0]));
			
			return "Record saved Successfully."; 

		}// end of if
		else
			return "Duplicate record found. ";

	}
	/* method name : modVoucher
	 * purpose     : to update the voucher head
	 * return type : String
	 * parameter   : VoucherMaster instance
	 */
	public String modVoucher(VoucherMaster bean) {
		Object addObj[][] =new Object[1][2];

		/*Object head[][]=new Object[1][1];
		head [0][0]=bean.getVoucherHead();
		 */
		addObj[0][0]=bean.getVoucherHead();
		addObj[0][1] =bean.getVoucherCode();
		boolean result= getSqlModel().singleExecute(getQuery(2),addObj);  // update query
		if(result)
		{
			return "Record updated Successfully. ";
		}// end of if
		else
			return "Duplicate record found. ";		
	}
	/* method name : deleteVoucher
	 * purpose     : to delete the voucher head
	 * return type : boolean
	 * parameter   : VoucherMaster instance
	 */
	public boolean deleteVoucher(VoucherMaster bean) {
		Object addObj[][] =new Object[1][1];

		addObj[0][0] =bean.getVoucherCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	  // delete query

	}

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
	/* method name : getVoucherRecord
	 * purpose     : to set the voucher head details on the screen
	 * return type : void
	 * parameter   : VoucherMaster instance
	 */
	public void  getVoucherRecord(VoucherMaster bean) {
		Object addObj[] =new Object[1];

		addObj[0] =bean.getVoucherCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);

		for(int i=0; i<data.length; i++) {
			logger.info("I am get record");
			bean.setVoucherCode(String.valueOf(data[i][0]));
			bean.setVoucherHead(String.valueOf(data[i][1]));
		} // end of for loop

	}
	/* method name : getReport
	 * purpose     : to get the voucher head report
	 * return type : void
	 * parameter   : VoucherMaster instance
	 */
	public void getReport(VoucherMasterModel model, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session) {

		CrystalReport cr=new CrystalReport(); 
		String path="org\\paradyne\\rpt\\admin\\master\\voucherHead.rpt";
		cr.createReport(request, response, context,session, path, "");


	}
	
	public void getReport(VoucherMaster bean, HttpServletRequest request,
			HttpServletResponse response,String[]label) {		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nVoucher Head\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Voucher Head");
		String queryDes = "SELECT  rownum,VCH_NAME FROM HRMS_VCH_HD ORDER  BY VCH_CODE";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][2];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				j++;
			}
			int cellwidth[] = { 15,50};
			int alignment[] = { 1, 0};
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	/* method name : Data 
	 * purpose     : to show the voucher heads in the list
	 * return type : void
	 * parameter   : VoucherMaster instance, HttpServletRequest request
	 */

	public void  Data(VoucherMaster  bean, HttpServletRequest request) {


	//	Object [][] repData = getSqlModel().getSingleResult(getQuery(6));
		Object [][] obj = getSqlModel().getSingleResult(getQuery(6));
		
		
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

			//	for (int i = 0; i < obj.length; i++) {
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){

					   VoucherMaster  bean1 = new VoucherMaster ();
						bean1.setVoucherCode(checkNull(String.valueOf(obj[i][0])));
						bean1.setVoucherHead(checkNull(String.valueOf(obj[i][1])));	

					list.add(bean1);
				}
				
				 bean.setTotalRecords(""+obj.length);
			}
			else{
				bean.setNoData("true");
				bean.setListLength("false");
				
			}
			//bean.setEmpList(list);
			bean.setTotalRecords(String.valueOf(list.size()));
		    if(list.size()>0)
		    	bean.setListLength("true");
		    else
		    	bean.setListLength("false");
		
		    bean.setRecordList(list);
		
		
	
		/*
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1=0;
		int PageNo1=1;//----------
		REC_TOTAL = repData.length;
		int no_of_pages=Math.round(REC_TOTAL/20);
		
		 * show 20 records per page
		  
		double row = (double)repData.length/20.0;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());


		ArrayList<Object> obj=new ArrayList<Object>();
		request.setAttribute("abc", rowCount1);

		//PageNo
		if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(" "))
		{
			PageNo1=1;
			From_TOT=0;
			To_TOT=20;

			if(To_TOT >repData.length){
				To_TOT=repData.length;
			}
			bean.setMyPage("1");
		} // end of if


		else{

			pg1=	Integer.parseInt(bean.getMyPage());
			PageNo1=pg1;

			if(pg1 ==1){
				From_TOT=0;
				To_TOT=20;
			} // end of if
			else{
				//  From_TOTAL=To_TOTAL+1;
				To_TOT=To_TOT*pg1;
				From_TOT=(To_TOT-20);
			} // end of else
			if(To_TOT >repData.length){
				To_TOT=repData.length;
			} // end of if
		} // end of else
		request.setAttribute("xyz", PageNo1);
		for(int i=From_TOT;i<To_TOT;i++){
			
			VoucherMaster  bean1 = new VoucherMaster ();
			bean1.setVoucherCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setVoucherHead(checkNull(String.valueOf(repData[i][1])));

			obj.add(bean1);
		} // end of for loop
*/


		

	}
	/* method name : callForEdit 
	 * purpose     : to show the voucher head details after edit
	 * return type : void
	 * parameter   : VoucherMaster instance, HttpServletRequest request
	 */

	public void callForEdit(VoucherMaster bean) {


		String query = "SELECT  VCH_CODE, VCH_NAME  FROM HRMS_VCH_HD WHERE  VCH_CODE= "+bean.getHiddencode() ;

		Object [][]data=getSqlModel().getSingleResult(query);
		//setter

		bean.setVoucherCode(String.valueOf(data[0][0]));
		bean.setVoucherHead(String.valueOf(data[0][1]));
		//	bean.set(String.valueOf(data[0][2]));


	}
	/* method name : deleteCheckedRecords 
	 * purpose     : to delete the multiple voucher heads checked
	 * return type : boolean
	 * parameter   : VoucherMaster instance, String[] code
	 */
	public boolean deleteCheckedRecords(VoucherMaster bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {

				if(!code[i].equals("")){

					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(3), delete);  // delete the checked voucher heads
					if(!result)
						count++;

				} // end of if
			} // end of for loop
		} // end of if
		if(count!=0)
		{	
		result=false;
		return result;
		} // end of if
		else
		{
		result=true;
		return result;
		} // end of else
	}

}




