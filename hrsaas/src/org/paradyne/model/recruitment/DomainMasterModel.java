package org.paradyne.model.recruitment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.Recruitment.DomainMaster;

public class DomainMasterModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.paradyne.model.recruitment.DomainMasterModel.class); 
	
	
	/**
	 * following function is called to add a new record
	 * @param bean
	 * @return
	 */
	public String addDom(DomainMaster bean) {
		Object[][] add=new Object[1][4];
		String flag="";
		boolean result=false;
		
		add[0][0]=bean.getDomainName().trim();
		add[0][1]=bean.getDomAbbr().trim();
		add[0][2]=bean.getDomDesc().replaceAll("\n", "");
		logger.info("bean.getDomDesc() --------->  "+bean.getDomDesc());
		add[0][3]=bean.getDomainStatus();
		
		
		if(!checkDuplicate(bean))
		{
			result=getSqlModel().singleExecute(getQuery(1),add);
			logger.info("result in addDom method of outer if------->"+result);
			if(result){

			String query = " SELECT MAX(FUNC_DOMAIN_CODE) FROM HRMS_FUNC_DOMAIN_MASTER" ;
			Object[][] data = getSqlModel().getSingleResult(query);
			String query1 = " SELECT NVL(FUNC_DOMAIN_NAME,' '),NVL(FUNC_DOMAIN_ABBR,' '),DECODE(FUNC_DOMAIN_STATUS,'A','Active','D','Deactive'),NVL(FUNC_DOMAIN_DESC,' '),FUNC_DOMAIN_CODE  FROM HRMS_FUNC_DOMAIN_MASTER"
				+" WHERE FUNC_DOMAIN_CODE="+String.valueOf(data[0][0]);
			Object[][] Data = getSqlModel().getSingleResult(query1);
			bean.setDomainName(checkNull(String.valueOf(Data[0][0])));
			bean.setDomAbbr(checkNull(String.valueOf(Data[0][1])));
			bean.setDomDesc(checkNull(String.valueOf(Data[0][3])));
			bean.setViewStatus(checkNull(String.valueOf(Data[0][2])));
			bean.setDomainCode(checkNull(String.valueOf(Data[0][4])));
				flag="saved";
			}
			else
			{
				flag="error";
			}
		}
		else
		{
			String query = "SELECT DECODE(FUNC_DOMAIN_STATUS,'A','Active','D','Deactive')" 
                +" FROM HRMS_FUNC_DOMAIN_MASTER where FUNC_DOMAIN_STATUS='"+bean.getDomainStatus()+"'"; 
			Object[][] data = getSqlModel().getSingleResult(query);
			flag = "duplicate";
		}
		return flag;
	}
	
	/**
	 * following function is called to update the record.
	 * @param bean
	 * @return
	 */
	
	public String modDom(DomainMaster bean) {
	
			Object mod[][] = new Object[1][5];
			String editFlag="";
			boolean result=false;
			mod[0][0]=bean.getDomainName().trim();
			mod[0][1]=bean.getDomAbbr().trim();
			mod[0][2]=bean.getDomDesc().replaceAll("\n", "");
			mod[0][3]=bean.getDomainStatus();
			mod[0][4]=bean.getDomainCode();
					
			if(!checkDuplicateMod(bean))
			{
				result= getSqlModel().singleExecute(getQuery(2), mod);
				if(result){
					String query1 = " SELECT FUNC_DOMAIN_NAME,FUNC_DOMAIN_ABBR,DECODE(FUNC_DOMAIN_STATUS,'A','Active','D','Deactive'),FUNC_DOMAIN_DESC,FUNC_DOMAIN_CODE  FROM HRMS_FUNC_DOMAIN_MASTER"
									+" WHERE FUNC_DOMAIN_CODE="+bean.getDomainCode();
					logger.info("query1 in modification is--->"+query1);
					Object[][] Data = getSqlModel().getSingleResult(query1);
					bean.setDomainName(String.valueOf(Data[0][0]).trim());
					bean.setDomAbbr(checkNull(String.valueOf(Data[0][1])));
					bean.setViewStatus(checkNull(String.valueOf(Data[0][2])));
					bean.setDomDesc(checkNull(String.valueOf(Data[0][3])));
					bean.setDomainCode(checkNull(String.valueOf(Data[0][4])));
					editFlag="modified";
				}
				else
				{
					editFlag = "error";
				}
			}
			else
			{
				editFlag = "duplicate";
			}
			return editFlag;
	}	
	
	
	
	/**
	 * following function is called to delete a record
	 * @param bean
	 * @return
	 */
	public boolean deleteDom(DomainMaster bean) {
		Object del[][] = new Object[1][1];
		del[0][0] = bean.getDomainCode();
		return getSqlModel().singleExecute(getQuery(3), del);
	}
	
	
	/**
	 * following function is called when  a record is selected from search window to set the records.
	 * @param bean
	 */
	public void getDom(DomainMaster bean){
		try{
		
		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),NVL(FUNC_DOMAIN_ABBR,' '),FUNC_DOMAIN_STATUS,NVL(FUNC_DOMAIN_DESC,' '),FUNC_DOMAIN_CODE,DECODE(FUNC_DOMAIN_STATUS,'A','Active','D','Deactive')  FROM HRMS_FUNC_DOMAIN_MASTER"
			+" WHERE FUNC_DOMAIN_CODE="+bean.getDomainCode();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setDomainName(checkNull(String.valueOf(data[0][0]).trim()));
			bean.setDomAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			bean.setDomainStatus(checkNull(String.valueOf(data[0][2])));
			bean.setDomDesc(checkNull(String.valueOf(data[0][3])));
			bean.setDomainCode(checkNull(String.valueOf(data[0][4])));
			bean.setViewStatus(checkNull(String.valueOf(data[0][5])));
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 
	
/*	public void getDomRec(DomainMaster bean){
		try{
		
		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),NVL(FUNC_DOMAIN_ABBR,' '),DECODE(FUNC_DOMAIN_STATUS,'A','Active','D','Deactive'),NVL(FUNC_DOMAIN_DESC,' '),FUNC_DOMAIN_CODE  FROM HRMS_FUNC_DOMAIN_MASTER"
			+" WHERE FUNC_DOMAIN_CODE="+bean.getDomainCode();
		
		System.out.println("Query in GetdomRec--->"+query);
		
	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setDomainName(checkNull(String.valueOf(data[0][0]).trim()));
			logger.info("Domain Name--->");
			bean.setDomAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			logger.info("Domain Abbr--->");
			bean.setDomainStatus(checkNull(String.valueOf(data[0][2])));
			logger.info("Domain Status--->");
			bean.setDomDesc(checkNull(String.valueOf(data[0][3])));
			logger.info("Domain Desc--->");
			bean.setDomainCode(checkNull(String.valueOf(data[0][4])));
			logger.info("Domain Code--->");
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	*/ 
	public void getDomRecSch(DomainMaster bean){
		try{
		
		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),NVL(FUNC_DOMAIN_ABBR,' '),FUNC_DOMAIN_STATUS,NVL(FUNC_DOMAIN_DESC,' '),FUNC_DOMAIN_CODE  FROM HRMS_FUNC_DOMAIN_MASTER"
			+" WHERE FUNC_DOMAIN_CODE="+bean.getDomainCode();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setDomainName(checkNull(String.valueOf(data[0][0]).trim()));
			bean.setDomAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			bean.setDomainStatus(String.valueOf(data[0][2]));
			bean.setDomDesc(checkNull(String.valueOf(data[0][3])));
			bean.setDomainCode(checkNull(String.valueOf(data[0][4])));
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 

	
	
	
	public void getDomOnDoubleClick(DomainMaster bean){
		try{
		
		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),NVL(FUNC_DOMAIN_ABBR,' '),CASE WHEN FUNC_DOMAIN_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(FUNC_DOMAIN_DESC,' '),FUNC_DOMAIN_CODE  FROM HRMS_FUNC_DOMAIN_MASTER"
			+" WHERE FUNC_DOMAIN_CODE="+bean.getHiddencode();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setDomainName(checkNull(String.valueOf(data[0][0]).trim()));
			bean.setDomAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			bean.setViewStatus(checkNull(String.valueOf(data[0][2])));
			bean.setDomDesc(checkNull(String.valueOf(data[0][3]).trim()));
			bean.setDomainCode(checkNull(String.valueOf(data[0][4])));
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	/**
	 * following function is called to display the records page wise.Ths function will display 20 records per page.
	 * @param bean
	 * @param request
	 */
	public void getRecords(DomainMaster bean,HttpServletRequest request){
		try{
		
		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),NVL(FUNC_DOMAIN_ABBR,' '),CASE WHEN FUNC_DOMAIN_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(FUNC_DOMAIN_DESC,' '),FUNC_DOMAIN_CODE  FROM HRMS_FUNC_DOMAIN_MASTER"
			+" ORDER BY FUNC_DOMAIN_CODE";

		Object[][] data = getSqlModel().getSingleResult(query);
						/*int REC_TOTAL = 0;
						int To_TOT = 20;
						int From_TOT = 0;
						int pg1=0;
						int PageNo1=1;//----------
						REC_TOTAL = data.length;
						int no_of_pages=Math.round(REC_TOTAL/20);
						double row = (double)data.length/20.0;
				   
				      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
				     
				      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
				    
					
					
					System.out.println("val of riwC"+rowCount1);
					request.setAttribute("abc", rowCount1);
				
					//PageNo
					if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(" "))
					{
						PageNo1=1;
						From_TOT=0;
						  To_TOT=20;
				
						  if(To_TOT >data.length){
							  To_TOT=data.length;
						  }
							
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
						  if(To_TOT >data.length){
							  To_TOT=data.length;
						  }
					  }
					request.setAttribute("xyz", PageNo1);
	
	*
	*/
		ArrayList<Object> obj=new ArrayList<Object>();
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 20);	
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
		
		
  if(data!=null && data.length>0){		
	  
	  for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
                 //setting 
		  DomainMaster  bean1 = new DomainMaster();
			
		bean1.setDomainName(String.valueOf(data[i][0]).trim());
		bean1.setDomAbbr(String.valueOf(data[i][1]).trim());
		//bean1.setDomDesc(String.valueOf(data[i][2]));
		bean1.setDomainStatus(String.valueOf(data[i][2]));
		bean1.setDomainCode(String.valueOf(data[i][4]));
		obj.add(bean1);
	  }
	  
	  bean.setTotRecord(String.valueOf(Integer.parseInt(String.valueOf(data.length))));
	  bean.setDomainList(obj);
  }	
	
		
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	}	
	
	public boolean delChkdRec(DomainMaster bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
				
				if(!code[i].equals("")){
					
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					
					String query="DELETE FROM HRMS_FUNC_DOMAIN_MASTER WHERE FUNC_DOMAIN_CODE=? ";
					result=getSqlModel().singleExecute(query,delete);

					if(!result)
					count++;
				}
			}
		}

		if(count!=0)
		{
			result=false;
			return result;
		}
		else
		{
			result=true;
			return true; 
		}
	}
	/* for checking duplicate entry of records during insertion. */
	public boolean checkDuplicate(DomainMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_FUNC_DOMAIN_MASTER WHERE UPPER(FUNC_DOMAIN_NAME) LIKE '"
				+ bean.getDomainName().trim().toUpperCase()+"'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		logger.info("result in checkDuplicate method of Model------->"+result);
		return result;

	}

	/* for checking duplicate entry of records during modification. */
	public boolean checkDuplicateMod(DomainMaster bean) {
		boolean result = false;
		
		String query = "SELECT * FROM HRMS_FUNC_DOMAIN_MASTER WHERE UPPER(FUNC_DOMAIN_NAME) LIKE '"
				+ bean.getDomainName().trim().toUpperCase()
				+ "' AND FUNC_DOMAIN_CODE not in(" + bean.getDomainCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		
		logger.info("data length--->"+data.length);
		if (data != null && data.length > 0) {
			result = true;
		}
		logger.info("result in checkDuplicateMod method of Model------->"+result);
		return result;

	}

	/**
	 *  Following function is called to get the report of the Pdf format for list of Domain Records
	 */
	 public void generateReport(DomainMaster domainMaster, HttpServletResponse response,String[]label) {
			// TODO Auto-generated method stub
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String reportName="Domain Master";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf", reportName);
			rg.setFName("Domain Master.Pdf");
			String queryDes="SELECT FUNC_DOMAIN_NAME,FUNC_DOMAIN_ABBR,DECODE(FUNC_DOMAIN_STATUS,'A','Active','D','Deactive') " +
							"FROM HRMS_FUNC_DOMAIN_MASTER ORDER BY FUNC_DOMAIN_CODE";
			Object [][]data=getSqlModel().getSingleResult(queryDes);
			Object [][] Data=new Object[data.length][4];
			if (data != null && data.length > 0) {
				int j=1;
				for(int i=0;i<data.length;i++){
					Data[i][0]=j;
					Data[i][1]=data[i][0];
					Data[i][2]=data[i][1];
					Data[i][3]=data[i][2];
					j++;
				}
				int cellwidth[] = {5, 20,20,20 };
				int alignment[] = { 1, 0 , 0, 0};
				rg.addTextBold("Domain Master", 0, 1, 0);
				rg.addText("\n",0,0,0);
				rg.addTextBold("Date :"+toDay, 0, 2, 0);
				rg.addText("\n",0,0,0);
				rg.tableBody(label, Data, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}

			rg.createReport(response);
			
		}
	
}