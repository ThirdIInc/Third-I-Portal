package org.paradyne.model.TravelManagement.Master;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.HotelType;
import org.paradyne.bean.TravelManagement.Master.TravelMode;
import org.paradyne.bean.TravelManagement.Master.TravelPurpose;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.lib.ParaActionSupport;


	public class HotelTypeModel  extends ModelBase {
							static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
							(org.struts.action.admin.master.RankMasterAction.class);
					
					/**
					* following function is called to add a new record
					* @param bean
					* @return
					*/
					public boolean addHotelType(HotelType bean) {
					
					if(!checkDuplicate(bean)){
						Object[][] add=new Object[1][3];
					try{
					
					add[0][0]=bean.getHotelName().trim();
					logger.info("add[0][0]- Name -->"+add[0][0]);
					add[0][1]=bean.getDescription();
					logger.info("add[0][1]-- Desc->"+add[0][1]);
					add[0][2]=bean.getStatus();
					logger.info("add[0][3]-Status-->"+add[0][2]);
					
					}catch(Exception e){
					e.printStackTrace();
					}
					String Query1="INSERT INTO HRMS_TMS_HOTEL_TYPE(HOTEL_TYPE_ID,HOTEL_TYPE_NAME,HOTEL_TYPE_DESC,HOTEL_TYPE_STATUS,HOTEL_LEVEL) " 
					+" VALUES((SELECT NVL(MAX(HOTEL_TYPE_ID),0)+1 FROM HRMS_TMS_HOTEL_TYPE ),'"+add[0][0]+"','"+add[0][1]+"','"+add[0][2]+"',(SELECT NVL(MAX(HOTEL_LEVEL),0)+1 FROM HRMS_TMS_HOTEL_TYPE))";
					System.out.println("____________*********{{{{{{{_______"+Query1);
					boolean result=getSqlModel().singleExecute(Query1);
					
					logger.info("Resumt in AddHotelType Method--->"+result);
					
					if(result){
					
					String query = " SELECT MAX(HOTEL_TYPE_ID) FROM HRMS_TMS_HOTEL_TYPE" ;
					
					Object[][] data = getSqlModel().getSingleResult(query);
					
					System.out.println("data[0][0]---->"+data[0][0]);
					
					String query1 = " SELECT HOTEL_TYPE_NAME	,CASE WHEN HOTEL_TYPE_STATUS	='A' THEN 'Active'  WHEN HOTEL_TYPE_STATUS	='D' THEN 'Deactive' ELSE ' ' END,HOTEL_TYPE_DESC,HOTEL_TYPE_ID FROM "
					+" HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_ID="+String.valueOf(data[0][0]) ;
					
					logger.info("query1 in addDesignation---->"+query1);
					
					Object[][] Data = getSqlModel().getSingleResult(query1);
					
					bean.setHotelName(checkNull(String.valueOf(Data[0][0])));
					bean.setDescription(checkNull(String.valueOf(Data[0][2])));
					bean.setStatus(checkNull(String.valueOf(Data[0][1])));
					System.out.println("fffff--------------"+bean.getStatus());
					bean.setHotelCode(checkNull(String.valueOf(Data[0][3])));
					
					}
					return result;
					}else{
						return false;
					}}
					
					/**
					* following function is called to update the record.
					* @param bean
					* @return
					*/
					
					
					public boolean modHotelType(HotelType bean) {
					// TODO Auto-generated method stub
					
						if(!checkDuplicateMod(bean)){
						Object [][] data=new Object[1][4];
					try{
					
					data[0][0]=bean.getHotelName();
					data[0][1]=bean.getDescription();
					
					data[0][2]=bean.getStatus();
					data[0][3]=bean.getHotelCode();
					}
					catch(Exception e){
						
						e.printStackTrace();
					}
					String query="UPDATE HRMS_TMS_HOTEL_TYPE SET HOTEL_TYPE_NAME=?,HOTEL_TYPE_DESC=?,HOTEL_TYPE_STATUS=? where HOTEL_TYPE_ID=?";
					
					boolean flag=getSqlModel().singleExecute(query, data);
					String query1 = "SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_DESC,CASE WHEN HOTEL_TYPE_STATUS='A' THEN 'Active' WHEN HOTEL_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE where HOTEL_TYPE_ID ="+bean.getHotelCode()+"";
					
					Object [][]data1=getSqlModel().getSingleResult(query1);
					bean.setHotelName(checkNull(String.valueOf(data1[0][0]).trim()));
					
					bean.setDescription(checkNull(String.valueOf(data1[0][1]).trim()));
					
					bean.setStatus(checkNull(String.valueOf(data1[0][2]).trim()));
					bean.setHotelCode(checkNull(String.valueOf(data1[0][3]).trim()));
					
					return flag;
					}
					else {
						return false;
					}
	}
					public boolean checkDuplicate(HotelType bean) {
						boolean result = false;
						String query = "SELECT * FROM  HRMS_TMS_HOTEL_TYPE WHERE UPPER(HOTEL_TYPE_NAME) LIKE '"
								+ bean.getHotelName().trim().toUpperCase() + "'";
						Object[][] data = getSqlModel().getSingleResult(query);
						if (data != null && data.length > 0) {
							result = true;
						}// end of if
						return result;
					}
					
					public boolean checkDuplicateMod(HotelType bean) {
						boolean result = false;
						String query = "SELECT * FROM  HRMS_TMS_HOTEL_TYPE WHERE UPPER(HOTEL_TYPE_NAME) LIKE '"
								+ bean.getHotelName().trim().toUpperCase()
								+ "' AND HOTEL_TYPE_ID  not in(" + bean.getHotelCode() + ")";
						Object[][] data = getSqlModel().getSingleResult(query);
						if (data != null && data.length > 0) {
							result = true;
						}// end of if
						return result;

					}
					
					
					
					
					
					public void getReport(HotelType bean, HttpServletRequest request,
							HttpServletResponse response, ServletContext context,String []label) {
						// TODO Auto-generated method stub
						/*CrystalReport cr = new CrystalReport();
						String path = "org/paradyne/rpt/admin/master/Travel.rpt ";
						cr.createReport(request, response, context, session, path, "");*/
						
						 String comp = "Travel Management System";
					        String s = "Hotel Type List";
					        ReportGenerator rg = new ReportGenerator("Pdf", s);
					        String query ="SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_DESC,DECODE(HOTEL_TYPE_STATUS,'A','Active','D','Deactive'),TO_CHAR(SYSDATE,'DD-MM-YYYY' )FROM HRMS_TMS_HOTEL_TYPE order by HOTEL_LEVEL " ;
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
					
					            int bcellWidth[] = {7, 15, 20, 10  };
					            int bcellAlign[] = {0, 0, 0,0};
					           // String appCol[] = {  "Sr.No", "Hotel Type  Name", "Description", "Status"};
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
					public boolean deleteHotelType(HotelType bean) {
						boolean flag=false;
					Object del[][] = new Object[1][1];
					
					del[0][0] = bean.getHotelCode();
					
					logger.info("Desig Code del[0][0]--->"+del[0][0]);
					String Query="DELETE FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_ID=?";
					 flag=getSqlModel().singleExecute(Query,del);
					if(flag==true)
					{
						System.out.println("in if ----------");
						return flag;
					}
					else
					return flag;
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
					
					public void getHotelType(HotelType bean){
					try{
					
					logger.info("bean.getDesignationCode--->"+bean.getHotelCode());	
					
					String query = " SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_DESC,HOTEL_TYPE_STATUS,HOTEL_TYPE_ID  FROM HRMS_TMS_HOTEL_TYPE"
					                   +" WHERE HOTEL_TYPE_ID="+bean.getHotelCode();
					
					Object[][] data = getSqlModel().getSingleResult(query);
					
					if(data!=null && data.length>0){
					
					bean.setHotelName(String.valueOf(data[0][0]).trim());
					
					bean.setDescription(checkNull(String.valueOf(data[0][1])));
					
					bean.setStatus(checkNull(String.valueOf(data[0][2])));
					bean.setHotelCode(checkNull(String.valueOf(data[0][3])));
					}
					}catch(Exception e){
					e.printStackTrace();
					}
					
					}	 
					public void getHotelTypeEdt(HotelType bean){
						try{
						
						logger.info("bean.getDesignationCode--->"+bean.getHotelCode());	
						
						String query = " SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_DESC,DECODE(HOTEL_TYPE_STATUS,'A','Active','D','Deactive'),HOTEL_TYPE_ID  FROM HRMS_TMS_HOTEL_TYPE"
						                   +" WHERE HOTEL_TYPE_ID="+bean.getHotelCode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setHotelName(String.valueOf(data[0][0]).trim());
						
						bean.setDescription(checkNull(String.valueOf(data[0][1])));
						
						bean.setStatus(checkNull(String.valueOf(data[0][2])));
						bean.setHotelCode(checkNull(String.valueOf(data[0][3])));
						}
						}catch(Exception e){
						e.printStackTrace();
						}
						
						}	 
					
					public void getHotelTypeRec(HotelType bean){
						try{
						
						logger.info("bean.getDesignationCode--->"+bean.getHotelCode());	
						
						String query = " SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_DESC,DECODE(HOTEL_TYPE_STATUS,'A','Active','D','Deactive'),HOTEL_TYPE_ID  FROM HRMS_TMS_HOTEL_TYPE"
						                   +" WHERE HOTEL_TYPE_ID="+bean.getHotelCode();
						
						Object[][] data = getSqlModel().getSingleResult(query);
						
						if(data!=null && data.length>0){
						
						bean.setHotelName(String.valueOf(data[0][0]).trim());
						
						bean.setDescription(checkNull(String.valueOf(data[0][1])));
						
						bean.setStatus(checkNull(String.valueOf(data[0][2])));
						bean.setHotelCode(checkNull(String.valueOf(data[0][3])));
						}
						}catch(Exception e){
						e.printStackTrace();
						}
						
						}	 
						
					
					public void getHotelTypeOnDoubleClick(HotelType bean){
					try{
					
					String query = " SELECT NVL(HOTEL_TYPE_NAME,' '),HOTEL_TYPE_STATUS,NVL(HOTEL_TYPE_DESC,''),HOTEL_TYPE_ID  FROM HRMS_TMS_HOTEL_TYPE"
					+" WHERE HOTEL_TYPE_ID="+bean.getHiddencode();
					
					Object[][] data = getSqlModel().getSingleResult(query);
					
					if(data!=null && data.length>0){
					
					bean.setHotelName(String.valueOf(data[0][0]).trim());
					
					bean.setStatus(String.valueOf(data[0][1]));
					
					bean.setDescription(String.valueOf(data[0][2]).trim());
					bean.setHotelCode(String.valueOf(data[0][3]));
					}
					}catch(Exception e){
					e.printStackTrace();
					}
					
					}	 
					/*public void getRecords(HotelType bean,HttpServletRequest request){
					try{
					
					String query = " SELECT NVL(HOTEL_TYPE_NAME,' '),CASE WHEN HOTEL_TYPE_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(HOTEL_TYPE_DESC,' '),HOTEL_TYPE_ID  FROM HRMS_TMS_HOTEL_TYPE"
					+" ORDER BY HOTEL_LEVEL";
					
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
						HotelType  bean1 = new HotelType();
					
					bean1.setHotelName(String.valueOf(data[i][0]).trim());
					
					bean1.setStatus(String.valueOf(data[i][1]));
					
					bean1.setHotelCode(String.valueOf(data[i][3]));
					obj.add(bean1);
					}
					
					
					
					bean.setHotelList(obj);
					bean.setModeLength(String.valueOf(data.length));

					}catch(Exception e){
					e.printStackTrace();
					}
					
					}	*/
					
					
					
					public void getRecords(HotelType bean,HttpServletRequest request){
						try{
							String query = " SELECT NVL(UPPER(HOTEL_TYPE_NAME),' '),CASE WHEN HOTEL_TYPE_STATUS='A' THEN 'Active' WHEN HOTEL_TYPE_STATUS='D' THEN 'Deactive' ELSE ' ' END,NVL(HOTEL_TYPE_DESC,''),HOTEL_TYPE_ID,HOTEL_LEVEL  FROM HRMS_TMS_HOTEL_TYPE"
								+" ORDER BY HOTEL_LEVEL";
						
						Object[][] data = getSqlModel().getSingleResult(query);
						if(data!=null && data.length>20)
						{   
							String firstRecord ="";
							String lastRecord ="";
							int numOfPage = data.length/20;
							int j=19;
							for (int i = 0; i < numOfPage; i++) {
								if(i<(numOfPage))
								{
								firstRecord+=""+data[j+1][4]+",";
								lastRecord+=""+data[j][4]+",";
								
								}else
								{
									firstRecord+=""+data[j][4];
									lastRecord+=""+data[j-1][4]+"";
								}
								j=j+20;
							}
							System.out.println("firstRecord=================== "+firstRecord);
							System.out.println("numOfPage=================== "+numOfPage); 
							System.out.println("lastRecord=================== "+lastRecord); 
							bean.setFirstRecordOfPage(firstRecord);
							bean.setLastRecordOfPage(lastRecord);
						}
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
					//request.setAttribute("record", lastRecords);
					  
					  for(int i=From_TOT;i<To_TOT;i++){
				                 //setting 
						  HotelType  bean1 = new HotelType();
							
							bean1.setHotelName(String.valueOf(data[i][0]).trim());							
							bean1.setStatus(String.valueOf(data[i][1]));							
							bean1.setHotelCode(String.valueOf(data[i][3]));					
							bean1.setItLevel(String.valueOf(data[i][4])); 
							obj.add(bean1);
					  }
					
					
						
					  bean.setHotelList(obj);
					  
					  bean.setModeLength(String.valueOf(data.length));
					 
					}catch(Exception e){
						e.printStackTrace();
					}
					
					}	
					
					
					
					
					
					public boolean delChkdRec(HotelType bean,String[] code){
					
					
					int count=0;
					boolean result=false;
					for(int i=0;i<code.length;i++){
					if(!code[i].equals("")){
					
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					String Query=" DELETE FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_ID=?";
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


					public void upData(HotelType bean)
					{
						
						
						String code = bean.getUpId();
						 String previousId ="";
						 String currentId = "";
						 
						 String preSql ="SELECT HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_LEVEL ="+ bean.getPreviousLevel();
						 Object[][] prevousJourData= getSqlModel().getSingleResult(preSql);
						 if(prevousJourData != null && prevousJourData.length>0)
						 {
							 previousId  =""+prevousJourData[0][0];
						 } 
						 
						 String currentSql ="SELECT HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_LEVEL ="+bean.getCurrentLevel();
						 Object[][] currentJourData= getSqlModel().getSingleResult(currentSql);
						 if(currentJourData != null && currentJourData.length>0)
						 {
							 currentId  =""+currentJourData[0][0];
						 }
						 
						 String sql ="UPDATE HRMS_TMS_HOTEL_TYPE SET HOTEL_LEVEL ="+bean.getPreviousLevel()+" WHERE HOTEL_TYPE_ID="+currentId;
							getSqlModel().singleExecute(sql);
							
							String sqlUp ="UPDATE HRMS_TMS_HOTEL_TYPE SET HOTEL_LEVEL ="+bean.getCurrentLevel()+" WHERE HOTEL_TYPE_ID ="+previousId;
							getSqlModel().singleExecute(sqlUp);
						/*String code = bean.getUpId();
						System.out.println("code============= "+code);
						 String idSql ="SELECT HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_LEVEL ="+code;
						 Object[][] hotelData= getSqlModel().getSingleResult(idSql);
						 
						 int  upId= 0;
						  if(bean.getStatusUp().equals("true"))
						  {
						   upId= (Integer.parseInt(code)-1);
						  }
						  
						  if(bean.getStatusDown().equals("true"))
						  {
						   upId= (Integer.parseInt(code)+1);
						  } 
						String sql ="UPDATE HRMS_TMS_HOTEL_TYPE SET HOTEL_LEVEL ="+upId+" WHERE HOTEL_LEVEL="+code;
						getSqlModel().singleExecute(sql);
						
						String sqlUp ="UPDATE HRMS_TMS_HOTEL_TYPE SET HOTEL_LEVEL ="+code+" WHERE HOTEL_LEVEL="+upId+" AND HOTEL_TYPE_ID !="+hotelData[0][0];
						getSqlModel().singleExecute(sqlUp);
						*/
					}

					
					
					
					
		}






