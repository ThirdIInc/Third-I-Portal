package org.paradyne.model.admin.master;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.admin.master.GroupMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;
/**
 * @author pranali 
 * Date 24-04-07
 */
public class GroupModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
org.paradyne.bean.admin.master.GroupMaster groupMaster=null;
	
	public boolean addGroup(GroupMaster bean)
	{
		String query="SELECT UPPER(GRP_NAME) FROM HRMS_GRP WHERE (GRP_NAME='"+bean.getGroupName().trim().toUpperCase()+"' OR GRP_NAME='"+bean.getGroupName().trim().toLowerCase()+"')";
		Object [][]data=getSqlModel().getSingleResult(query);
		
		boolean flag=false;
		if(data.length>0)
		{
			 flag=false;
		}
		else{
		Object addObj[][] =new Object[1][2];
		addObj[0][0] =bean.getGroupName().trim(); 
		addObj[0][1] =bean.getGroupDesc().trim();
		
		flag =getSqlModel().singleExecute(getQuery(1),addObj);	
		}
		return flag;
	}

	public boolean modGroup(GroupMaster bean)
	{
		
		
		Object addObj[][] =new Object[1][3];
		
		
		addObj[0][0] =bean.getGroupName().trim(); 
		
		addObj[0][1] =bean.getGroupDesc().trim();
		
		addObj[0][2] =bean.getGroupID().trim();
		logger.info("group id"+bean.getGroupID());
		
		return getSqlModel().singleExecute(getQuery(2),addObj);	
		
		
	}
	
	public boolean deleteGroup(GroupMaster bean)
	{
		
		Object addObj[][] =new Object[1][1];
		addObj[0][0] =bean.getGroupID();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
		
	}
	
	public void  getGroupReport(GroupMaster groupMaster)
	{
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(4));
		
		
		ArrayList<Object> att = new ArrayList<Object>();
		
		for(int i=0; i<data.length; i++)
		{	
			GroupMaster bean1= new GroupMaster();
			
			bean1.setGroupID(checkNull(String.valueOf(data[i][0])));
			
			bean1.setGroupName(checkNull(String.valueOf(data[i][1])));
			
			bean1.setGroupDesc(checkNull(String.valueOf(data[i][2])));
			
			att.add(bean1);
			
		}
		groupMaster.setAtt(att); 
		
	}

	public void  getGroupRecord(GroupMaster groupMaster)
	{
		Object[] param=new Object[1];
		param[0]=groupMaster.getGroupID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(5),param);
		for(int i=0; i<data.length; i++)
		{				
			groupMaster.setGroupID(checkNull(String.valueOf(data[0][0])));
			groupMaster.setGroupName(checkNull(String.valueOf(data[0][1])));
			groupMaster.setGroupDesc(checkNull(String.valueOf(data[0][2])));
		}
		
	}

	
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}

	public void getReport(GroupMaster groupMaster2, HttpServletRequest request,
			HttpServletResponse response, ServletContext context) {
		// TODO Auto-generated method stub
		CrystalReport cr=new CrystalReport();
		String path="org\\paradyne\\rpt\\admin\\master\\group.rpt ";
		cr.createReport(request, response, context,session, path, "");
		
	}

	public boolean duplicate(GroupMaster groupMaster2) {
		
		
		
		return false;
	}

	public void groupData(GroupMaster groupMaster, HttpServletRequest request) {
		
		Object [][] repData = getSqlModel().getSingleResult(getQuery(6));
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = repData.length;
	int no_of_pages=Math.round(REC_TOTAL/20);
	//PageNo = Integer.parseInt(locationMaster.getMyPage());//-----------
	double row = (double)repData.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	logger.info("--------------------"+rowCount1);
	logger.info("------- groupMaster.getMyPage()-------------"+ groupMaster.getMyPage());
	ArrayList<Object> obj=new ArrayList<Object>();
	request.setAttribute("abc", rowCount1);

	//PageNo
	if(String.valueOf( groupMaster.getMyPage()).equals("null")||String.valueOf( groupMaster.getMyPage()).equals(null)||String.valueOf( groupMaster.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
			logger.info("-----------To_TOTAL----null-----"+To_TOT);
			 groupMaster.setMyPage("1");
	}
	
	
	else{
			
		  pg1=	Integer.parseInt( groupMaster.getMyPage());
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
		  GroupMaster bean1 = new GroupMaster ();
			bean1.setGroupID(checkNull(String.valueOf(repData[i][0])));
			bean1.setGroupName(checkNull(String.valueOf(repData[i][1])));
			bean1.setGroupDesc(checkNull(String.valueOf(repData[i][2])));
			
			
			
			
			
            obj.add(bean1);
	  }
	
	
		
	   groupMaster.setAtt(obj);
	
	
		
	}

	public void calforedit(GroupMaster groupMaster) {
		logger.info("Entry to edit method----------------");
		
		String query = "SELECT GROUP_ID,GROUP_NAME, NVL(GROUP_DESC,'') FROM HRMS_GROUP  " 
	         
	          +"  where GROUP_ID = "+groupMaster.getHiddencode() 
+"     ORDER BY GROUP_ID ";


Object [][]data=getSqlModel().getSingleResult(query);
groupMaster.setGroupID(String.valueOf(data[0][0]));
groupMaster.setGroupName(String.valueOf(data[0][1]));
groupMaster.setGroupDesc(checkNull(String.valueOf(data[0][2])));
logger.info("Exiting  from edit method----------------");



		
	}

	public boolean calfordelete(GroupMaster groupMaster) {
		
		Object [][] delete = new Object [1][1];
		delete [0][0] =groupMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		
	}

	public boolean deleteGroup(GroupMaster groupMaster, String[] code) {
		
		
		boolean result=false;
		boolean flag=false;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
				
				if(!code[i].equals("")){
					logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);	
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					flag	=getSqlModel().singleExecute(getQuery(3), delete);
					if(flag)
					result=true;
				}
			}
		}
		return result;
		
	}
	
}
