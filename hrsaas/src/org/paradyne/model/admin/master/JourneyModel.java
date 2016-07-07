/**
 * @author Diptimayee Pandey
 * 08-08-2008
 */
package org.paradyne.model.admin.master;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.Journey;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

 
public class JourneyModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void addItem(Journey journey,String []srNo, String []jClass, int check){	
		ArrayList<Object> tableList = new ArrayList<Object>();
		logger.info("in new add");
		if(srNo!=null){
			for(int i=0;i<srNo.length;i++){
				Journey bean=new Journey();
				logger.info("_________________"+jClass[i]);
				bean.setSrNo(String.valueOf(i+1));
				bean.setJClass(jClass[i]);
				tableList.add(bean);
			}
		}if(check==1){
			logger.info("=========="+journey.getJourneyClass());
			Journey bean=new Journey();
			bean.setSrNo(String.valueOf(tableList.size()+1));
			bean.setJClass(journey.getJourneyClass());
		
		tableList.add(bean);
		
		}else if(check==0){
			tableList.remove(Integer.parseInt(journey.getSubcode())-1);
		}
		logger.info("length************"+tableList.size());
			if(tableList.size()!=0)
				journey.setTableLength("1");
			else 
				journey.setTableLength("0");
			journey.setList(tableList);
	}
	
	
	public void getDuplicate(Journey journey,String []srNo, String []jClass, int check){	
		ArrayList<Object> tableList = new ArrayList<Object>();
		
		if(srNo!=null){
			
			for(int i=0;i<srNo.length;i++){
				Journey bean= new Journey();
				bean.setSrNo(String.valueOf(i+1));
				bean.setJClass(jClass[i]);
				tableList.add(bean);
			}
			journey.setList(tableList);
		}
		
		
		

		
	}
	
	public void moditem(Journey journey, String[] srNo,
			String[] jClass,  int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		logger.info("in  moditem");
		if(srNo!=null){
			
			for(int i=0;i<srNo.length;i++){
				Journey bean= new Journey();
			    logger.info("hidden edit=="+journey.getHiddenEdit());
			    if (i == Integer.parseInt(journey.getHiddenEdit())-1) {
					logger.info("loop no$$$$$$$$$$$$$$$$$$" + i);
					bean.setSrNo(String.valueOf(i+1));
					bean.setJClass(journey.getJourneyClass());
						

				}		    
			    else{
				bean.setSrNo(String.valueOf(i+1));
				bean.setJClass(jClass[i]);
				
			    }
				tableList.add(bean);
			}
		}
		logger.info("length************"+tableList.size());
			if(tableList.size()!=0)
				journey.setTableLength("1");
			else journey.setTableLength("0");
			journey.setList(tableList);
		
	}


	
	
	public boolean delDtl(Journey journey, String[] code,
			String[] jcl) {
		try{
		ArrayList<Object> list=new ArrayList<Object>();
		
		
		if(jcl!=null)
		{   
			for(int i=0;i<jcl.length;i++){
				Journey bean=new Journey();
				if((String.valueOf(code[i]).equals(""))){
					bean.setJClass(jcl[i]);
					list.add(bean);
					
				}
				
				}
		}
		journey.setList(list);
		}
		
	
		catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
		return true;
		
	}
	
	public boolean addMode(Journey journey, String[] jClass) {
		// TODO Auto-generated method stub
		
		
 String query = " SELECT NVL(MAX(JOURNEY_ID),0)+1 FROM HRMS_JOURNEY ";
		
		Object [][]code=getSqlModel().getSingleResult(query);
		Object obj[][]=new Object[1][2];
		obj[0][0]=code[0][0];
		obj[0][1]=journey.getJourneyMode();
		
		
		boolean result=getSqlModel().singleExecute(getQuery(1), obj);
			if(result){
				journey.setHiddenMode(String.valueOf(code[0][0]));
					result=addOption(journey,jClass);
				}
		return result;
	}
	
	public boolean modMode(Journey journey,String []jClass){
		boolean result1=false;
		boolean result2=false;
		logger.info("journey.getHiddenMode()-------------"+journey.getHiddenMode());
		Object obj[][]=new Object[1][2];
		obj[0][0]=journey.getJourneyMode();
		obj[0][1]=journey.getHiddenMode();
		
		Object addObj [][]= new Object[1][2];
		boolean result=getSqlModel().singleExecute(getQuery(3),obj);
		if(result){
			String queryDel="DELETE FROM HRMS_JOURNEY_CLASS WHERE JOURNEY_CLASS_JOURNEY_ID= "+journey.getHiddenMode();
			result1=getSqlModel().singleExecute(queryDel);
			if(result1)
			{
				if(jClass !=null){
					for(int i=0;i<jClass.length;i++){
						addObj[0][0]= jClass[i];
						addObj[0][1] =journey.getHiddenMode();
						getSqlModel().singleExecute(getQuery(2),addObj);
					}
				}
			}
				//result2=addOption(journey,jClass);
				
		}
		return result;
	}
	
	public boolean addOption(Journey journey,String[] jClass){
		 String query = " SELECT NVL(MAX(JOURNEY_ID),0) FROM HRMS_JOURNEY ";
		 Object [][]code=getSqlModel().getSingleResult(query);
		boolean result=false;
		Object addObj [][]= new Object[1][2];
		if(jClass !=null){
		for(int i=0;i<jClass.length;i++){
			logger.info("-----------"+code[0][0]);
			addObj[0][0]= jClass[i];
			addObj[0][1] =code[0][0];
			logger.info("addObj[0][1]=========================="+addObj[0][1]);
			result=getSqlModel().singleExecute(getQuery(2),addObj);
		}
		}
		return result;
		}
	
	
	public void showRecord(Journey journey)
	{
		String query = "SELECT JOURNEY_CLASS_ID,JOURNEY_CLASS_NAME ,JOURNEY_CLASS_JOURNEY_ID  FROM HRMS_JOURNEY_CLASS" 
			+ " WHERE JOURNEY_CLASS_JOURNEY_ID =" + journey.getHiddenMode()
			+ " ORDER BY JOURNEY_CLASS_ID";
		 
		Object data[][] = getSqlModel().getSingleResult(query);

		  ArrayList<Object> list=new ArrayList<Object>();
		
		for(int i=0 ;i <data.length;i++)
		{
			Journey bean1=new Journey();
			bean1.setSubcode(String.valueOf(data[i][0]));
			bean1.setJClass(String.valueOf(data[i][1]));
			bean1.setSrNo(String.valueOf(i+1));
			logger.info("bean1.getSubCode=="+bean1.getSubcode());
			list.add(bean1);
		}
		journey.setList(list);
		if(list.size()==0){
			journey.setTableLength("0");
		}else journey.setTableLength("1");
	
				
	}
	
	public boolean deleteMode(Journey journey)
	{
		Object delObj[][] = new Object[1][1];
		delObj[0][0]= journey.getHiddenMode();
		boolean res=getSqlModel().singleExecute(getQuery(4), delObj);
		if(res)
			return getSqlModel().singleExecute(getQuery(5), delObj);
		else 
			return false;
	}


	public void generateReport(Journey journey, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			String reportName="Journey";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf", reportName);
			rg.addTextBold("Journey Report", 0, 1, 0);
			rg.addTextBold("\n", 0, 1, 0);
			Object Data[][]=null;
			String msg ="";
			String date="select To_char(Sysdate,'dd-mm-yyyy') from dual";
			Object[][] DATE = getSqlModel().getSingleResult(date);
			 rg.addText("Date:"+String.valueOf(DATE[0][0]),2,2,0);
			String jourQuery="SELECT JOURNEY_ID,JOURNEY_NAME FROM HRMS_JOURNEY order by JOURNEY_ID";
			Object jourData[][]= getSqlModel().getSingleResult(jourQuery);
			for (int i = 0; i < jourData.length; i++) {
				 msg="Journey Mode :  "+String.valueOf(jourData[i][1]);
				
		
			String query="SELECT ROWNUM,HRMS_JOURNEY_CLASS.JOURNEY_CLASS_NAME,JOURNEY_CLASS_JOURNEY_ID"
					+ " FROM HRMS_JOURNEY" 
					+ " LEFT JOIN HRMS_JOURNEY_CLASS ON(HRMS_JOURNEY.JOURNEY_ID=HRMS_JOURNEY_CLASS.JOURNEY_CLASS_JOURNEY_ID)"
					+" WHERE JOURNEY_CLASS_JOURNEY_ID="+jourData[i][0]
					+ " ORDER BY  JOURNEY_ID";
			 Data= getSqlModel().getSingleResult(query);
			
			 if (jourData != null && jourData.length > 0) {
					 String[] colNames={"Sr. No.","Journey Class"};
						int [] cellWidth={8,25};
						int [] alignment={0,1};
					 if (Data != null && Data.length > 0){
							rg.addText(msg, 0, 0, 0);
							rg.tableBody(colNames,Data,cellWidth,alignment);
						
					 }else{
						 rg.addText("There is no class for journey mode "+String.valueOf(jourData[i][1]),0,1,0);
					 }
				
				}else{
					rg.addText("There is no data to display.",0,0,0);
				}
			}
			/*Object[][] finalData=new Object[Data.length][3];
			String name="";
			int s=1;*/
			
			/*for(int i=0;i<Data.length;i++){
				if(name.equals(String.valueOf(Data[i][0])) ){
					finalData[i][0]="";
					finalData[i][1]="";
					
				}else{
					finalData[i][0]=s++;
					finalData[i][1]=Data[i][0];
					
				}
				finalData[i][2]=Data[i][1];	
			
				name=String.valueOf(Data[i][0]);
				
			}*/
		
				
				
			rg.createReport(response);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
