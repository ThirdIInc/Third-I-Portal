package org.paradyne.model.TravelManagement.Master;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.HotelType;
import org.paradyne.bean.TravelManagement.Master.LocationType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.lib.ParaActionSupport;


	public class LocationTypeModel  extends ModelBase {
								static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
								(org.struts.action.admin.master.RankMasterAction.class);
						
						/**
						* following function is called to add a new record
						* @param bean
						* @return
						*/
						public boolean addLocationType(LocationType bean) {
						
						 if(!checkDuplicate(bean)){
							Object[][] add=new Object[1][3];
						try{
						
						add[0][0]=bean.getLocationName().trim();
						logger.info("add[0][0]--->"+add[0][0]);
						add[0][1]=bean.getDescription();
						logger.info("add[0][0]--->"+add[0][1]);
						add[0][2]=bean.getStatus();
						logger.info("add[0][0]--->"+add[0][2]);
												
						}catch(Exception e){
						e.printStackTrace();
						}
						String Query1="INSERT INTO HRMS_TMS_LOCATION_TYPE(LOCATION_TYPE_ID,LOCATION_TYPE_NAME,LOCATION_TYPE_DESC,LOCATION_TYPE_STATUS) " 
						+" VALUES((SELECT NVL(MAX(LOCATION_TYPE_ID),0)+1 FROM HRMS_TMS_LOCATION_TYPE ),'"+add[0][0]+"','"+add[0][1]+"','"+add[0][2]+"')";
						System.out.println("____________*********{{{{{{{_______}}}}}}"+Query1);
						boolean result=getSqlModel().singleExecute(Query1);
						
						logger.info("Resumt in AddLocationType Method--->"+result);
						
						if(result){
						
						String query = " SELECT MAX(LOCATION_TYPE_ID) FROM HRMS_TMS_LOCATION_TYPE" ;
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						
						
						String query1 = " SELECT LOCATION_TYPE_NAME	,CASE WHEN LOCATION_TYPE_STATUS	='A' THEN 'Active' WHEN LOCATION_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,LOCATION_TYPE_DESC,LOCATION_TYPE_ID FROM "
						+" HRMS_TMS_LOCATION_TYPE WHERE LOCATION_TYPE_ID="+String.valueOf(data[0][0]);
						
						logger.info("query1 in addLoacation---->"+query1);
						
						Object[][] Data = getSqlModel().getSingleResult(query1);
						
									bean.setLocationName(checkNull(String.valueOf(Data[0][0])));
									bean.setDescription(checkNull(String.valueOf(Data[0][2])));
									bean.setStatus(checkNull(String.valueOf(Data[0][1])));
									bean.setLocationCode(checkNull(String.valueOf(Data[0][3])));
							  }
						   return result;
					    }
						else{
							return false;
						}}
						
						/**
						* following function is called to update the record.
						* @param bean
						* @return
						*/
						
						
						public boolean modLocationType(LocationType bean) {
						// TODO Auto-generated method stub
						if(!checkDuplicateMod(bean)){
							
							Object [][] data=new Object[1][4];
						try{
						
						data[0][0]=bean.getLocationName();
						data[0][1]=bean.getDescription();
						data[0][2]=bean.getStatus();
						data[0][3]=bean.getLocationCode();
						}
						catch(Exception e){
							
							e.printStackTrace();
						}
						String query="UPDATE HRMS_TMS_LOCATION_TYPE SET LOCATION_TYPE_NAME=?,LOCATION_TYPE_DESC=?,LOCATION_TYPE_STATUS=? where LOCATION_TYPE_ID=?";
						
						boolean flag=getSqlModel().singleExecute(query, data);
						String query1 = "SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_DESC,CASE WHEN LOCATION_TYPE_STATUS='A' THEN 'Active' WHEN LOCATION_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,LOCATION_TYPE_ID FROM HRMS_TMS_LOCATION_TYPE where LOCATION_TYPE_ID ="+bean.getLocationCode()+"";
						
						Object [][]data1=getSqlModel().getSingleResult(query1);
						bean.setLocationName(checkNull(String.valueOf(data1[0][0]).trim()));
						
						bean.setDescription(checkNull(String.valueOf(data1[0][1]).trim()));
						bean.setStatus(checkNull(String.valueOf(data1[0][2]).trim()));
						bean.setLocationCode(checkNull(String.valueOf(data1[0][3]).trim()));
						
						return flag;
						}
						else {
							 return false;
						}
						}
						
						public boolean checkDuplicate(LocationType bean) {
							boolean result = false;
							String query = "SELECT * FROM  HRMS_TMS_LOCATION_TYPE WHERE UPPER(LOCATION_TYPE_NAME) LIKE '"
									+ bean.getLocationName().trim().toUpperCase() + "'";
							Object[][] data = getSqlModel().getSingleResult(query);
							if (data != null && data.length > 0) {
								result = true;
							}// end of if
							return result;
						}
						
						public boolean checkDuplicateMod(LocationType bean) {
							boolean result = false;
							String query = "SELECT * FROM  HRMS_TMS_LOCATION_TYPE WHERE UPPER(LOCATION_TYPE_NAME) LIKE '"
									+ bean.getLocationName().trim().toUpperCase()
									+ "' AND LOCATION_TYPE_ID not  in(" + bean.getLocationCode() + ")";
							Object[][] data = getSqlModel().getSingleResult(query);
							if (data != null && data.length > 0) {
								result = true;
							}// end of if
							return result;

						}
						
						
						
						
						
						public void getReport(LocationType bean, HttpServletRequest request,
								HttpServletResponse response, ServletContext context,String[]label) {
							// TODO Auto-generated method stub
							/*CrystalReport cr = new CrystalReport();
							String path = "org/paradyne/rpt/admin/master/Travel.rpt ";
							cr.createReport(request, response, context, session, path, "");*/
							
							 String comp = "Travel Management System";
						        String s = "Location Type List";
						        ReportGenerator rg = new ReportGenerator("Pdf", s);
						        String query ="SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_DESC,DECODE(LOCATION_TYPE_STATUS,'A','Active','D','Deactive'),TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_TMS_LOCATION_TYPE  order by LOCATION_TYPE_NAME" ;
						        Object result[][] = getSqlModel().getSingleResult(query);
						        if(result.length > 0)
						        {
						            try
						            {
						                rg.addFormatedText(comp, 0, 0, 1, 0);
						                rg.addFormatedText("", 0, 0, 1, 0);
						                rg.addFormatedText("\t\t\t\t\t\t\t\t\t\t\t\t"+s+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Date:"+ String.valueOf(result[0][3]), 0, 0, 1, 0);
						               // rg.addFormatedText("Date:"+String.valueOf(result[0][3])+"\t\t\t\t\t\t\t\t" +s, 0, 0, 1, 0);
						               rg.addFormatedText("", 0, 0, 1, 0);
						                rg.addFormatedText("", 0, 0, 1, 0);
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
						            int bcellAlign[] = {0, 0, 0,0};
						           // String appCol[] = {  "Sr.No", "Location Type  Name", "Description", "Status" };
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
						public boolean deleteLocationType(LocationType bean) {
						Object del[][] = new Object[1][1];
						
						del[0][0] = bean.getLocationCode();
						
						logger.info("Desig Code del[0][0]--->"+del[0][0]);
						String Query="DELETE FROM HRMS_TMS_LOCATION_TYPE WHERE LOCATION_TYPE_ID=?";
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
						
						public void getLocationType(LocationType bean){
					
							/*String query1 = " SELECT MAX(LOCATION_TYPE_ID) FROM HRMS_TMS_LOCATION_TYPE" ;
							
							Object[][] data1 = getSqlModel().getSingleResult(query1);*/
						logger.info("bean.getLocationCode--->"+bean.getLocationCode());	
					
						String query = "SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_DESC,LOCATION_TYPE_STATUS,LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE"
						                   +" WHERE LOCATION_TYPE_ID="+bean.getLocationCode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setLocationName(checkNull(String.valueOf(data[0][0]).trim()));
						
						bean.setDescription(checkNull(String.valueOf(data[0][1])));
						bean.setStatus(checkNull(String.valueOf(data[0][2])));
						bean.setLocationCode(checkNull(String.valueOf(data[0][3])));
						}
						
						
						}	 
						public void getLocationTypeRec(LocationType bean){
							
							/*String query1 = " SELECT MAX(LOCATION_TYPE_ID) FROM HRMS_TMS_LOCATION_TYPE" ;
							
							Object[][] data1 = getSqlModel().getSingleResult(query1);*/
						logger.info("bean.getLocationCode--->"+bean.getLocationCode());	
					
						String query = "SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_DESC,DECODE(LOCATION_TYPE_STATUS,'A','Active','D','Deactive'),LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE"
						                   +" WHERE LOCATION_TYPE_ID="+bean.getLocationCode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setLocationName(checkNull(String.valueOf(data[0][0]).trim()));
						
						bean.setDescription(checkNull(String.valueOf(data[0][1])));
						bean.setStatus(checkNull(String.valueOf(data[0][2])));
						bean.setLocationCode(checkNull(String.valueOf(data[0][3])));
						}
						
						
						}	
						public void getLocationTypeEdt(LocationType bean){
							
							/*String query1 = " SELECT MAX(LOCATION_TYPE_ID) FROM HRMS_TMS_LOCATION_TYPE" ;
							
							Object[][] data1 = getSqlModel().getSingleResult(query1);*/
						logger.info("bean.getLocationCode--->"+bean.getLocationCode());	
					
						String query = "SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_DESC,DECODE(LOCATION_TYPE_STATUS,'A','Active','D','Deactive'),LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE"
						                   +" WHERE LOCATION_TYPE_ID="+bean.getLocationCode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setLocationName(checkNull(String.valueOf(data[0][0]).trim()));
						
						bean.setDescription(checkNull(String.valueOf(data[0][1])));
						bean.setStatus(checkNull(String.valueOf(data[0][2])));
						bean.setLocationCode(checkNull(String.valueOf(data[0][3])));
						}
						
						
						}	
						
						
						public void getLocationTypeOnDoubleClick(LocationType bean){
						try{
						
						String query = " SELECT NVL(LOCATION_TYPE_NAME,' '),DECODE(LOCATION_TYPE_STATUS,'A','Active','D','Deactive'),NVL(LOCATION_TYPE_DESC,''),LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE"
						+" WHERE LOCATION_TYPE_ID="+bean.getHiddencode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setLocationName(String.valueOf(data[0][0]).trim());
						
						bean.setStatus(String.valueOf(data[0][1]));
						bean.setDescription(String.valueOf(data[0][2]).trim());
						bean.setLocationCode(String.valueOf(data[0][3]));
						}
						}catch(Exception e){
						e.printStackTrace();
						}
						
						}	 
						public void getRecords(LocationType bean,HttpServletRequest request){
						try{
						
						String query = " SELECT NVL(UPPER(LOCATION_TYPE_NAME),' '),CASE WHEN LOCATION_TYPE_STATUS='A' THEN 'Active' WHEN LOCATION_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,NVL(LOCATION_TYPE_DESC,''),LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE"
						+" ORDER BY UPPER(LOCATION_TYPE_NAME)";
						
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
							LocationType  bean1 = new LocationType();
						
						bean1.setLocationName(String.valueOf(data[i][0]).trim());
						
						bean1.setStatus(String.valueOf(data[i][1]));
						bean1.setLocationCode(String.valueOf(data[i][3]));
						obj.add(bean1);
						}
						
						
						
						bean.setLocationList(obj);
						}catch(Exception e){
						e.printStackTrace();
						}
						
						}	
						
						public boolean delChkdRec(LocationType bean,String[] code){
						
						
						int count=0;
						boolean result=false;
						for(int i=0;i<code.length;i++){
						if(!code[i].equals("")){
						
						Object [][] delete = new Object [1][1];
						delete [0][0] =code[i] ;
						String Query=" DELETE FROM HRMS_TMS_LOCATION_TYPE WHERE LOCATION_TYPE_ID=?";
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








