package org.paradyne.model.TravelManagement.Master;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.TravelManagement.Master.TravelGrade;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

	public class TravelGradeModel extends ModelBase {
				static org.apache.log4j.Logger logger = org.apache.log4j.Logger
						.getLogger(ModelBase.class);
				org.paradyne.bean.TravelManagement.Master.TravelGrade bean = null;
				
				public boolean addTravelGrade(TravelGrade bean) {
				if(!checkDuplicate(bean)){
				Object[][] add=new Object[1][3];
				try{
				
				add[0][0]=bean.getGradeName().trim();
				logger.info("add[0][0]--->"+add[0][0]);
				add[0][1]=bean.getDescription();
				logger.info("add[0][0]--->"+add[0][1]);
				add[0][2]=bean.getStatus();
				logger.info("add[0][0]--->"+add[0][2]);
										
				}catch(Exception e){
				e.printStackTrace();
				}
				String Query1="INSERT INTO HRMS_TMS_TRAVEL_GRADE(TRAVEL_GRADE_ID,TRAVEL_GRADE_NAME,TRAVEL_GRADE_DESC,TRAVEL_GRADE_STATUS) " 
				+" VALUES((SELECT NVL(MAX(TRAVEL_GRADE_ID),0)+1 FROM HRMS_TMS_TRAVEL_GRADE ),'"+add[0][0]+"','"+add[0][1]+"','"+add[0][2]+"')";
				System.out.println("____________*********{{{{{{{_______"+Query1);
				boolean result=getSqlModel().singleExecute(Query1);
				
				logger.info("Resumt in AddTarvelGrade Method--->"+result);
				
				if(result){
				
				String query = " SELECT MAX(TRAVEL_GRADE_ID) FROM HRMS_TMS_TRAVEL_GRADE " ;
				
				Object[][] data = getSqlModel().getSingleResult(query);
				
				System.out.println("data[0][0]---->"+data[0][0]);
				
				String query1 = " SELECT TRAVEL_GRADE_NAME	,CASE WHEN TRAVEL_GRADE_STATUS	='A' THEN 'Active' ELSE 'Deactive' END,TRAVEL_GRADE_DESC,TRAVEL_GRADE_ID FROM "
				+" HRMS_TMS_TRAVEL_GRADE WHERE TRAVEL_GRADE_ID="+String.valueOf(data[0][0]);
				
				logger.info("query1 in addDesignation---->"+query1);
				
				Object[][] Data = getSqlModel().getSingleResult(query1);
				
				bean.setGradeName(checkNull(String.valueOf(Data[0][0])));
				bean.setDescription(checkNull(String.valueOf(Data[0][2])));
				bean.setStatus(checkNull(String.valueOf(Data[0][1])));
				bean.setGradeCode(checkNull(String.valueOf(Data[0][3])));
				
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
				
				
				public boolean modTravelGrade(TravelGrade bean) {
				// TODO Auto-generated method stub
				
			  if(!checkDuplicateMod(bean)){
				Object [][] data=new Object[1][4];
				try{
				
				data[0][0]=bean.getGradeName();
				data[0][1]=bean.getDescription();
				data[0][2]=bean.getStatus();
				data[0][3]=bean.getGradeCode();
				}
				catch(Exception e){
					
					e.printStackTrace();
				}
				String query="UPDATE HRMS_TMS_TRAVEL_GRADE SET TRAVEL_GRADE_NAME=?,TRAVEL_GRADE_DESC=?,TRAVEL_GRADE_STATUS=? where TRAVEL_GRADE_ID=?";
				
				boolean flag=getSqlModel().singleExecute(query, data);
				String query1 = "SELECT TRAVEL_GRADE_NAME,TRAVEL_GRADE_DESC,CASE WHEN TRAVEL_GRADE_STATUS='A' THEN 'Active' ELSE 'Deactive' END,TRAVEL_GRADE_ID FROM HRMS_TMS_TRAVEL_GRADE where TRAVEL_GRADE_ID ="+bean.getGradeCode()+"";
				
				Object [][]data1=getSqlModel().getSingleResult(query1);
				bean.setGradeName(checkNull(String.valueOf(data1[0][0]).trim()));
				
				bean.setDescription(checkNull(String.valueOf(data1[0][1]).trim()));
				bean.setStatus(checkNull(String.valueOf(data1[0][2]).trim()));
				bean.setGradeCode(checkNull(String.valueOf(data1[0][3]).trim()));
				
				return flag;
				}
				else {
				   return false;	 
			      }
			 }
				
				
				public boolean checkDuplicate(TravelGrade bean) {
					boolean result = false;
					String query = "SELECT * FROM  HRMS_TMS_TRAVEL_GRADE WHERE UPPER(TRAVEL_GRADE_NAME) LIKE '"
							+ bean.getGradeName().trim().toUpperCase() + "'";
					Object[][] data = getSqlModel().getSingleResult(query);
					if (data != null && data.length > 0) {
						result = true;
					}// end of if
					return result;
				}
				
				public boolean checkDuplicateMod(TravelGrade bean) {
					boolean result = false;
					String query = "SELECT * FROM  HRMS_TMS_TRAVEL_GRADE WHERE UPPER(TRAVEL_GRADE_NAME) LIKE '"
							+ bean.getGradeName().trim().toUpperCase()
							+ "' AND TRAVEL_GRADE_ID  not in(" + bean.getGradeCode() + ")";
					Object[][] data = getSqlModel().getSingleResult(query);
					if (data != null && data.length > 0) {
						result = true;
					}// end of if
					return result;

				}

				public void getReport(TravelGrade bean, HttpServletRequest request,
						HttpServletResponse response, ServletContext context,String [] label) {
					// TODO Auto-generated method stub
					/*CrystalReport cr = new CrystalReport();
					String path = "org/paradyne/rpt/admin/master/Travel.rpt ";
					cr.createReport(request, response, context, session, path, "");*/
					
					 String comp = "Travel Management System";
				        String s = "Travel Grade List";
				        ReportGenerator rg = new ReportGenerator("Pdf", s);
				        String query ="SELECT TRAVEL_GRADE_NAME,TRAVEL_GRADE_DESC,DECODE(TRAVEL_GRADE_STATUS,'A','Active','D','Deactive'),TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_TMS_TRAVEL_GRADE order by TRAVEL_GRADE_NAME " ;
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
				            //String appCol[] = {  "Sr.No", "Travel Grade  Name", "Description", "Status" };
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
				public boolean deleteTravelGrade(TravelGrade bean) {
				Object del[][] = new Object[1][1];
				
				del[0][0] = bean.getGradeCode();
				
				logger.info("Desig Code del[0][0]--->"+del[0][0]);
				String Query="DELETE FROM HRMS_TMS_TRAVEL_GRADE WHERE TRAVEL_GRADE_ID=?";
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
				
				public void getTravelGrade(TravelGrade bean){
				try{
				
				logger.info("bean.getDesignationCode--->"+bean.getGradeCode());	
				
				String query = " SELECT TRAVEL_GRADE_NAME,TRAVEL_GRADE_DESC,TRAVEL_GRADE_STATUS,TRAVEL_GRADE_ID  FROM HRMS_TMS_TRAVEL_GRADE"
				                   +" WHERE TRAVEL_GRADE_ID="+bean.getGradeCode();
				
				Object[][] data = getSqlModel().getSingleResult(query);
				
				if(data!=null && data.length>0){
				
				bean.setGradeName(checkNull(String.valueOf(data[0][0]).trim()));
				
				bean.setDescription(checkNull(String.valueOf(data[0][1])));
				bean.setStatus(checkNull(String.valueOf(data[0][2])));
				bean.setGradeCode(checkNull(String.valueOf(data[0][3])));
				}
				}catch(Exception e){
				e.printStackTrace();
				}
				
				}	
				public void getTravelGradeEdt(TravelGrade bean){
					try{
					
					logger.info("bean.getDesignationCode--->"+bean.getGradeCode());	
					
					String query = " SELECT TRAVEL_GRADE_NAME,TRAVEL_GRADE_DESC,DECODE(TRAVEL_GRADE_STATUS,'A','Active','D','Deactive'),TRAVEL_GRADE_ID  FROM HRMS_TMS_TRAVEL_GRADE"
					                   +" WHERE TRAVEL_GRADE_ID="+bean.getGradeCode();
					
					Object[][] data = getSqlModel().getSingleResult(query);
					
					if(data!=null && data.length>0){
					
					bean.setGradeName(checkNull(String.valueOf(data[0][0]).trim()));
					
					bean.setDescription(checkNull(String.valueOf(data[0][1])));
					bean.setStatus(checkNull(String.valueOf(data[0][2])));
					bean.setGradeCode(checkNull(String.valueOf(data[0][3])));
					}
					}catch(Exception e){
					e.printStackTrace();
					}
					
					}
				public void getTravelGradeRec(TravelGrade bean){
					try{
					
					logger.info("bean.getDesignationCode--->"+bean.getGradeCode());	
					
					String query = " SELECT TRAVEL_GRADE_NAME,TRAVEL_GRADE_DESC,DECODE(TRAVEL_GRADE_STATUS,'A','Active','D','Deactive'),TRAVEL_GRADE_ID  FROM HRMS_TMS_TRAVEL_GRADE"
					                   +" WHERE TRAVEL_GRADE_ID="+bean.getGradeCode();
					
					Object[][] data = getSqlModel().getSingleResult(query);
					
					if(data!=null && data.length>0){
					
					bean.setGradeName(checkNull(String.valueOf(data[0][0]).trim()));
					
					bean.setDescription(checkNull(String.valueOf(data[0][1])));
					bean.setStatus(checkNull(String.valueOf(data[0][2])));
					bean.setGradeCode(checkNull(String.valueOf(data[0][3])));
					}
					}catch(Exception e){
					e.printStackTrace();
					}
					
					}
				
				
				public void getTravelGradeOnDoubleClick(TravelGrade bean){
				try{
				
				String query = " SELECT NVL(TRAVEL_GRADE_NAME,' '),TRAVEL_GRADE_STATUS,NVL(TRAVEL_GRADE_DESC,' '),TRAVEL_GRADE_ID  FROM HRMS_TMS_TRAVEL_GRADE"
				+" WHERE TRAVEL_GRADE_ID="+bean.getHiddencode();
				
				Object[][] data = getSqlModel().getSingleResult(query);
				
				if(data!=null && data.length>0){
				
				bean.setGradeName(String.valueOf(data[0][0]).trim());
				
				bean.setStatus(String.valueOf(data[0][1]));
				bean.setDescription(String.valueOf(data[0][2]).trim());
				bean.setGradeCode(String.valueOf(data[0][3]));
				}
				}catch(Exception e){
				e.printStackTrace();
				}
				
				}	 
				public void getRecords(TravelGrade bean,HttpServletRequest request){
				try{
				
				String query = " SELECT NVL(TRAVEL_GRADE_NAME,' '),CASE WHEN TRAVEL_GRADE_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(TRAVEL_GRADE_DESC,' '),TRAVEL_GRADE_ID  FROM HRMS_TMS_TRAVEL_GRADE "
				+" ORDER BY TRAVEL_GRADE_ID";
				
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
					TravelGrade  bean1 = new TravelGrade();
				
				bean1.setGradeName(String.valueOf(data[i][0]).trim());
				
				bean1.setStatus(String.valueOf(data[i][1]));
				bean1.setGradeCode(String.valueOf(data[i][3]));
				obj.add(bean1);
				}
				
				
				
				bean.setGradeList(obj);
				}catch(Exception e){
				e.printStackTrace();
				}
				
				}	
				
				public boolean delChkdRec(TravelGrade bean,String[] code){
				
				
				int count=0;
				boolean result=false;
				for(int i=0;i<code.length;i++){
				if(!code[i].equals("")){
				
				Object [][] delete = new Object [1][1];
				delete [0][0] =code[i] ;
				String Query=" DELETE FROM HRMS_TMS_TRAVEL_GRADE WHERE TRAVEL_GRADE_ID=?";
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









