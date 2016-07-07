package org.paradyne.model.admin.master;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.bean.admin.master.ESIMaster;
import org.paradyne.bean.admin.master.PayBillMaster;

import com.itextpdf.text.BaseColor;



public class PayBillModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	public boolean addPayBill(PayBillMaster payMaster){
		logger.info("in addPayBill()");
		Object[][] saveObj = new Object[1][1];		
		saveObj[0][0]=payMaster.getPayGrp().trim();
		return getSqlModel().singleExecute(getQuery(1),saveObj);
	}
	public boolean modPayBill(PayBillMaster payMaster){
		Object addObj [][]= new Object [1][2];		
		addObj [0][0]=payMaster.getPayGrp().trim();
		addObj[0][1]=payMaster.getPayID();		
		return getSqlModel().singleExecute(getQuery(2),addObj);
	}
	public boolean deletePayBill(PayBillMaster payMaster){
		Object addObj [][]= new Object [1][1];
		addObj [0][0]=payMaster.getPayID();
		return getSqlModel().singleExecute(getQuery(3),addObj);
	}
	public void getPayBillReport(PayBillMaster payMaster){
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> list=new ArrayList<Object>();
		for(int i=0;i<data.length;i++){
			PayBillMaster bean=new PayBillMaster();
			bean.setPayID(String.valueOf(data[i][0]));
			bean.setPayGrp(String.valueOf(data[i][1]));
			list.add(bean);
		}		
		payMaster.setPay(list);
	}
	
	public void getReport(PayBillMaster bean,HttpServletRequest request,HttpServletResponse 

			response,ServletContext context,HttpSession session){
				CrystalReport cr=new CrystalReport(); 
				String path="org\\paradyne\\rpt\\admin\\master\\paybill.rpt";
				cr.createReport(request, response, context,session, path, "");
			}
	
	
	
	
	
public void  Data(PayBillMaster  bean, HttpServletRequest request) {
		

		Object [][] repData = getSqlModel().getSingleResult(getQuery(6));
		bean.setModeLength("true");
		bean.setTotalNoOfRecords(String.valueOf(repData.length));
		
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = repData.length;
	int no_of_pages=Math.round(REC_TOTAL/20);
	//PageNo = Integer.parseInt(bean.getMyPage());//-----------
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
			logger.info("-----------To_TOTAL----null-----"+To_TOT);
			bean.setMyPage("1");
	}
	
	
	else{
			
		  pg1=	Integer.parseInt(bean.getMyPage());
		  PageNo1=pg1;
		  
		  if(pg1 ==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
	  }
	request.setAttribute("xyz", PageNo1);
	  logger.info("------------from total--------"+From_TOT);
	  logger.info("----------to total----------"+To_TOT);
	  for(int i=From_TOT;i<To_TOT;i++){
                 //setting 
		  PayBillMaster  bean1 = new PayBillMaster ();

          bean1.setPayID(checkNull(String.valueOf(repData[i][0])));
		  bean1.setPayGrp(checkNull(String.valueOf(repData[i][1])));
			
			
			
			
            obj.add(bean1);
	  }
	
	
		
	  bean.setIteratorlist(obj);
	
	}
public void calforedit(PayBillMaster bean) {
		
		
		String query = "SELECT PAYBILL_ID,PAYBILL_GROUP FROM  HRMS_PAYBILL WHERE PAYBILL_ID="+bean.getHiddencode() ;
		
			
		
		Object [][]data=getSqlModel().getSingleResult(query);
      //setter

        bean.setPayID((String.valueOf(data[0][0])));
		bean.setPayGrp((String.valueOf(data[0][1])));
		
		
		
	}
public boolean calfordelete(PayBillMaster bean) {
	
	Object [][] delete = new Object [1][1];
	delete [0][0] =  bean.getHiddencode();
	return getSqlModel().singleExecute(getQuery(3), delete);
	// TODO Auto-generated method stub

}
	

public String checkNull(String result) {
	if (result == null || result.equals("null")) {
		return "";
	} else {
		return result;
	}
}
	
public boolean deletecheckedRecords(PayBillMaster bean, String[] code) {
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
				
			}
		}
	}
	if(count!=0)
	{	result=false;
		return result;
		}
	else
		{result=true;
		return result; }
}

//Added by Nilesh D on 6th Feb 2012.
public void generateReport(PayBillMaster payMaster, HttpServletRequest request,
		HttpServletResponse	response,String reportPath)
{
	
	ReportDataSet rds = new ReportDataSet();
	
	String type = payMaster.getReport();// Pdf/Xls/Doc
	rds.setReportType(type);
	
	String fileName = "Pay Bill " +Utility.getRandomNumber(1000);
	rds.setFileName(fileName);
	rds.setReportName("Pay Bill");
	rds.setTotalColumns(2);
	rds.setShowPageNo(true);
	// Report Generator Starts here
	org.paradyne.lib.ireportV2.ReportGenerator rg=null;
	//Attachment Path Definition
	//String reportPath="";
	if(reportPath.equals("")){
	rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
	}
	else{
	rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
	request.setAttribute("reportPath", reportPath+fileName+"."+type);
	request.setAttribute("fileName", fileName + "." + type);
	request.setAttribute("action", "PayBillMaster_input.action");

	// Initial Page Action name
	}
	
	String query=" SELECT ROWNUM, PAYBILL_GROUP FROM  HRMS_PAYBILL";
	Object[][]queryData=getSqlModel().getSingleResult(query);
	//Defining Tabular Structure and data
	TableDataSet tdstable = new TableDataSet();
	
	tdstable.setHeader(new String[]{"Sr. No.","Pay Bill Group"});// defining headers
	tdstable.setData(queryData);// data object from query
	tdstable.setHeaderLines(true);
	tdstable.setBlankRowsAbove(1);
	//tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
	tdstable.setCellAlignment(new int[]{1,0});
	tdstable.setCellWidth(new int[]{10,90});
	tdstable.setBorderDetail(3);
	tdstable.setHeaderTable(false);
	rg.addTableToDoc(tdstable);
	rg.process();
	if(reportPath.equals(""))
	{
	rg.createReport(response);
	}else
	{
	/* Generates the report as attachment*/
		rg.saveReport(response);
	}
}


	
}
