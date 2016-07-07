package org.paradyne.model.TravelManagement.Master;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.HotelType;
import org.paradyne.bean.TravelManagement.Master.TravelGrade;
import org.paradyne.bean.TravelManagement.Master.TravelPurpose;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

	public class TravelPurposeModel  extends ModelBase {
								static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
								(org.struts.action.admin.master.RankMasterAction.class);
						
						/**
						* following function is called to add a new record
						* @param bean
						* @return
						*/
						public boolean addTravelPurpose(TravelPurpose bean) {
						if(!checkDuplicate(bean)){
						Object[][] add=new Object[1][3];
						try{
						
						add[0][0]=bean.getPurposeName().trim();
						logger.info("add[0][0]--->"+add[0][0]);
						add[0][1]=bean.getDescription();
						logger.info("add[0][0]--->"+add[0][1]);
						add[0][2]=bean.getStatus();
						logger.info("add[0][0]--->"+add[0][2]);
												
						}catch(Exception e){
						e.printStackTrace();
						}
					String Query1="INSERT INTO HRMS_TMS_PURPOSE(PURPOSE_ID,PURPOSE_NAME,PURPOSE_DESC,PURPOSE_STATUS) " 
						+" VALUES((SELECT NVL(MAX(PURPOSE_ID),0)+1 FROM HRMS_TMS_PURPOSE ),'"+add[0][0]+"','"+add[0][1]+"','"+add[0][2]+"')";
						System.out.println("____________*********{{{{{{{_______"+Query1);
						boolean result=getSqlModel().singleExecute(Query1);
						
						logger.info("Resumt in AddTravelPurpose Method--->"+result);
						
						if(result){
						
						String query = " SELECT MAX(PURPOSE_ID) FROM HRMS_TMS_PURPOSE" ;
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						System.out.println("data[0][0]---->"+data[0][0]);
						
						String query1 = " SELECT PURPOSE_NAME	,CASE WHEN PURPOSE_STATUS	='A' THEN 'Active' WHEN PURPOSE_STATUS	='D' THEN 'Deactive' ELSE ' ' END,PURPOSE_DESC,PURPOSE_ID FROM "
						+" HRMS_TMS_PURPOSE WHERE PURPOSE_ID="+String.valueOf(data[0][0]);
						
						logger.info("query1 in addDesignation---->"+query1);
						
						Object[][] Data = getSqlModel().getSingleResult(query1);
						
						bean.setPurposeName(checkNull(String.valueOf(Data[0][0])));
						bean.setDescription(checkNull(String.valueOf(Data[0][2])));
						bean.setStatus(checkNull(String.valueOf(Data[0][1])));
						bean.setPurposeCode(checkNull(String.valueOf(Data[0][3])));
						
						}
						return result;
						}
						else{
						  return false;	
						}
						}
						
						/**
						* following function is called to update the record.
						* @param bean
						* @return
						*/
						
						
						public boolean modTravelPurpose(TravelPurpose bean) {
						// TODO Auto-generated method stub
						if(!checkDuplicateMod(bean)){
							Object [][] data=new Object[1][4];
						try{
						
						data[0][0]=bean.getPurposeName();
						data[0][1]=bean.getDescription();
						data[0][2]=bean.getStatus();
						data[0][3]=bean.getPurposeCode();
						}
						catch(Exception e){
							
							e.printStackTrace();
						}
						String query="UPDATE HRMS_TMS_PURPOSE SET PURPOSE_NAME=?,PURPOSE_DESC=?,PURPOSE_STATUS=? where PURPOSE_ID=?";
						
						boolean flag=getSqlModel().singleExecute(query, data);
						String query1 = "SELECT PURPOSE_NAME,PURPOSE_DESC,DECODE(PURPOSE_STATUS,'A','Active','D','Deactive'),PURPOSE_ID FROM HRMS_TMS_PURPOSE where PURPOSE_ID ="+bean.getPurposeCode()+"";
						
						Object [][]data1=getSqlModel().getSingleResult(query1);
						bean.setPurposeName(checkNull(String.valueOf(data1[0][0]).trim()));
						
						bean.setDescription(checkNull(String.valueOf(data1[0][1]).trim()));
						bean.setStatus(checkNull(String.valueOf(data1[0][2]).trim()));
						bean.setPurposeCode(checkNull(String.valueOf(data1[0][3]).trim()));
						
						return flag;
						}
						else {
							return false;
							}
						}
						
						public boolean checkDuplicate(TravelPurpose bean) {
							boolean result = false;
							String query = "SELECT * FROM  HRMS_TMS_PURPOSE WHERE UPPER(PURPOSE_NAME) LIKE '"
									+ bean.getPurposeName().trim().toUpperCase() + "'";
							Object[][] data = getSqlModel().getSingleResult(query);
							if (data != null && data.length > 0) {
								result = true;
							}// end of if
							return result;
						}
						
						public boolean checkDuplicateMod(TravelPurpose bean) {
							boolean result = false;
							String query = "SELECT * FROM  HRMS_TMS_PURPOSE WHERE UPPER(PURPOSE_NAME) LIKE '"
									+ bean.getPurposeName().trim().toUpperCase()
									+ "' AND PURPOSE_ID  not in(" + bean.getPurposeCode() + ")";
							Object[][] data = getSqlModel().getSingleResult(query);
							if (data != null && data.length > 0) {
								result = true;
							}// end of if
							return result;

						}
						
						public void getReport(TravelPurpose bean, HttpServletRequest request,
								HttpServletResponse response, ServletContext context,String []label) {
							// TODO Auto-generated method stub
							/*CrystalReport cr = new CrystalReport();
							String path = "org/paradyne/rpt/admin/master/Travel.rpt ";
							cr.createReport(request, response, context, session, path, "");*/
							
							 String comp = "Travel Management System";
						        String s = "Travel Purpose List ";
						        ReportGenerator rg = new ReportGenerator("Pdf", s);
						         
						        String query ="SELECT PURPOSE_NAME,PURPOSE_DESC,DECODE(PURPOSE_STATUS,'A','Active','D','Deactive'),To_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_TMS_PURPOSE  order by PURPOSE_NAME " ;
						        Object result[][] = getSqlModel().getSingleResult(query);
						        if(result.length > 0)
						        {
						            try
						            {
						                rg.addFormatedText(comp, 0, 0, 1, 0);
						                rg.addFormatedText("", 0, 0, 1, 0);
						                rg.addFormatedText("\t\t\t\t\t\t\t\t\t\t\t\t"+s+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Date:"+ String.valueOf(result[0][3]),0, 0, 1, 0);
						                rg.addFormatedText("", 0, 0, 1, 0);
						                
						            }
						            catch(Exception e)
						            {
						                System.out.println("Exception");
						            }
						            Object tabledata[][] = new Object[result.length][5];
						            for(int i = 0; i < result.length; i++)
						            {
						                tabledata[i][0] = String.valueOf(i + 1);
						                tabledata[i][1] = checkNull(String.valueOf(result[i][0]));
						                tabledata[i][2] = checkNull(String.valueOf(result[i][1]));
						                tabledata[i][3] = checkNull(String.valueOf(result[i][2]));
						                
						            }

						            int bcellWidth[] = {7, 15, 20, 10   };
						            int bcellAlign[] = {0, 0, 0,0 };
						           // String appCol[] = {  "Sr.No", "Travel Purpose  Name", "Description", "Status" };
						            rg.tableBody(label, tabledata, bcellWidth, bcellAlign);
						            try
						            {
						                rg.addFormatedText("", 0, 0, 1, 0);
						                rg.addText((new StringBuilder("Total no of Records:")).append(result.length).toString(), 0, 0, 0);
						            }
						            catch(Exception e)
						            {
						                System.out.println("Exception");
						            }
						            rg.addText("End  of Report", 0, 2, 0);
						        } else
						        {
						            rg.addText("No data found", 0, 1, 0);
						        }
						        rg.createReport(response);
						    }


						
						/**
						* following function is called to delete a record
						* @param bean
						* @return
						*/
						public boolean deleteTravelPurpose(TravelPurpose bean) {
						Object del[][] = new Object[1][1];
						
						del[0][0] = bean.getPurposeCode();
						
						logger.info("Desig Code del[0][0]--->"+del[0][0]);
						String Query="DELETE FROM HRMS_TMS_PURPOSE WHERE PURPOSE_ID=?";
						return getSqlModel().singleExecute(Query,del);
						}
						
						
						public String checkNull(String result) {
						if (result == null || result.equals("null")) {
						return "";
						}//end of if
						else {
						return result;
						}//end of else
						}
						
						
						
						
						/**
						* following function is called when  a record is selected from search window to set the records.
						* @param bean
						*/
						
						public void getTravelPurpose(TravelPurpose bean){
						try{
						
						logger.info("bean.getDesignationCode--->"+bean.getPurposeCode());	
						
						String query = " SELECT PURPOSE_NAME,PURPOSE_DESC,PURPOSE_STATUS,PURPOSE_ID  FROM HRMS_TMS_PURPOSE"
						                   +" WHERE PURPOSE_ID="+bean.getPurposeCode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setPurposeName(checkNull(String.valueOf(data[0][0]).trim()));
						
						bean.setDescription(checkNull(String.valueOf(data[0][1])));
						bean.setStatus(checkNull(String.valueOf(data[0][2])));
						bean.setPurposeCode(checkNull(String.valueOf(data[0][3])));
						}
						}catch(Exception e){
						e.printStackTrace();
						}
						
						}	 
						
						public void getTravelPurposeEdt(TravelPurpose bean){
							try{
							
							logger.info("bean.getDesignationCode--->"+bean.getPurposeCode());	
							
							String query = " SELECT PURPOSE_NAME,PURPOSE_DESC,DECODE(PURPOSE_STATUS,'A','Active','D','Deactive'),PURPOSE_ID  FROM HRMS_TMS_PURPOSE"
							                   +" WHERE PURPOSE_ID="+bean.getPurposeCode();
							
							Object[][] data = getSqlModel().getSingleResult(query);
							
							if(data!=null && data.length>0){
							
							bean.setPurposeName(checkNull(String.valueOf(data[0][0]).trim()));
							
							bean.setDescription(checkNull(String.valueOf(data[0][1])));
							bean.setStatus(checkNull(String.valueOf(data[0][2])));
							bean.setPurposeCode(checkNull(String.valueOf(data[0][3])));
							}
							}catch(Exception e){
							e.printStackTrace();
							}
							
							}	
						
						
						
						public void getTravelPurposeRec(TravelPurpose bean){
							try{
							
							logger.info("bean.getDesignationCode--->"+bean.getPurposeCode());	
							
							String query = " SELECT PURPOSE_NAME,PURPOSE_DESC,DECODE(PURPOSE_STATUS,'A','Active','D','Deactive'),PURPOSE_ID  FROM HRMS_TMS_PURPOSE"
							                   +" WHERE PURPOSE_ID="+bean.getPurposeCode();
							
							Object[][] data = getSqlModel().getSingleResult(query);
							
							if(data!=null && data.length>0){
							
							bean.setPurposeName(checkNull(String.valueOf(data[0][0]).trim()));
							
							bean.setDescription(checkNull(String.valueOf(data[0][1])));
							bean.setStatus(checkNull(String.valueOf(data[0][2])));
							bean.setPurposeCode(checkNull(String.valueOf(data[0][3])));
							}
							}catch(Exception e){
							e.printStackTrace();
							}
							
							}	 
						
						public void getTravelPurposeOnDoubleClick(TravelPurpose bean){
						try{
						
						String query = " SELECT NVL(PURPOSE_NAME,' '),PURPOSE_STATUS,PURPOSE_DESC,PURPOSE_ID  FROM HRMS_TMS_PURPOSE"
						+" WHERE PURPOSE_ID="+bean.getHiddencode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setPurposeName(String.valueOf(data[0][0]).trim());
						
						bean.setStatus(String.valueOf(data[0][1]));
						bean.setDescription(String.valueOf(data[0][2]).trim());
						bean.setPurposeCode(String.valueOf(data[0][3]));
						}
						}catch(Exception e){
						e.printStackTrace();
						}
						}	 
						
						public void getRecords(TravelPurpose bean,HttpServletRequest request){
						try{
						
						String query = " SELECT NVL(UPPER(PURPOSE_NAME),' '),CASE WHEN PURPOSE_STATUS='A' THEN 'Active' WHEN PURPOSE_STATUS='D' THEN 'Deactive' ELSE ' ' END,PURPOSE_DESC,PURPOSE_ID  FROM HRMS_TMS_PURPOSE"
						+" ORDER BY UPPER(PURPOSE_NAME)";
						
						Object[][] data = getSqlModel().getSingleResult(query);
						int REC_TOTAL = 0;
						int To_TOT = 20;
						int From_TOT = 0;
						int pg1=0;
						int PageNo1=1;//----------
						REC_TOTAL = data.length;
						int no_of_pages=Math.round(REC_TOTAL/20);
						double row = (double)data.length/20.0;
						
						java.math.BigDecimal value1 = new java.math.BigDecimal(row);
						
						int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
						
						
						ArrayList<Object> obj=new ArrayList<Object>();
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
						
						for(int i=From_TOT;i<To_TOT;i++){
						//setting 
                    TravelPurpose  bean1 = new TravelPurpose();
						
						bean1.setPurposeName(String.valueOf(data[i][0]).trim());
					
						bean1.setStatus(String.valueOf(data[i][1]));
						bean1.setPurposeCode(String.valueOf(data[i][3]));
						obj.add(bean1);
						}
						
						
						
						bean.setPurposeList(obj);
						}catch(Exception e){
						e.printStackTrace();
						}
						
						}	
					/*	public  void getDesc(TravelPurpose bean){
							TravelPurposeModel model=new TravelPurposeModel();
							String Query="SELECT NVL(PURPOSE_DESC,'') FROM HRMS_TMS_PURPOSE  ORDER BY PURPOSE_ID";
							Object[][]obj=getSqlModel().getSingleResult(Query);
							bean.setDescription(String.valueOf(obj[0][0]));
						}
						*/
						public boolean delChkdRec(TravelPurpose bean,String[] code){
						
						
						int count=0;
						boolean result=false;
						for(int i=0;i<code.length;i++){
						if(!code[i].equals("")){
						
						Object [][] delete = new Object [1][1];
						delete [0][0] =code[i] ;
						String Query=" DELETE FROM HRMS_TMS_PURPOSE WHERE PURPOSE_ID=?";
						result=getSqlModel().singleExecute(Query, delete);
						if(!result){
						count++;
						}
						}
						}
						
						
						if(count!=0){
						result=false;
						return result;
						}else {
						result=true;
						return result;
						}
		}

	}



