package org.paradyne.model.admin.master;


import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.DesignationMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;

/**
 * @author pranali 
 * Date 25-04-07
 */
public class DesignationModel  extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	org.paradyne.bean.admin.master.DesignationMaster desgMaster=null;
	
	public boolean addDesignation(DesignationMaster bean)
	{
		String query="SELECT UPPER(RANK_NAME) FROM HRMS_RANK WHERE (RANK_NAME='"+bean.getDesgName().trim().toUpperCase()+"' OR RANK_NAME='"+bean.getDesgName().trim().toLowerCase()+"')";
		Object [][]data=getSqlModel().getSingleResult(query);
		
		boolean flag=false;
		if(data.length>0)
		{
			 flag=false;
		}
		else{
		Object addObj[][] =new Object[1][8];
		addObj[0][0] =bean.getDesgName().trim(); 
		addObj[0][1] =bean.getDesgParCode().trim();
		addObj[0][2] =bean.getDesgHighCode().trim();
		addObj[0][3] =bean.getDesgAbbr().trim();
		addObj[0][4] =bean.getDesgDesc().trim();
		addObj[0][5] =bean.getDesgLevel().trim();
		addObj[0][6] =bean.getRcmndAuth().trim();
		addObj[0][7] =bean.getApprAuth().trim();
		flag= getSqlModel().singleExecute(getQuery(1),addObj);	
		}
		return flag;
	}
	
	public boolean modDesignation(DesignationMaster bean)
	{
		Object addObj[][] =new Object[1][9];	
		addObj[0][0] =bean.getDesgName().trim(); 
		addObj[0][1] =bean.getDesgParCode().trim();
		addObj[0][2] =bean.getDesgHighCode().trim();
		addObj[0][3] =bean.getDesgAbbr().trim();
		addObj[0][4] =bean.getDesgDesc().trim();
		addObj[0][5] =bean.getDesgLevel().trim();
		addObj[0][6] =bean.getRcmndAuth().trim();
		addObj[0][7] =bean.getApprAuth().trim();
		addObj[0][8] =bean.getDesgID().trim();
		return getSqlModel().singleExecute(getQuery(2),addObj);		
	}
	
	public boolean deleteDesignation(DesignationMaster bean)
	{
		Object addObj[][] =new Object[1][1];
		addObj[0][0] =bean.getDesgID();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
	}

	public void  getDesignationReport(DesignationMaster desgMaster)
	{
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> att = new ArrayList<Object>();
		for(int i=0; i<data.length; i++)
		{	
			DesignationMaster bean1= new DesignationMaster();
			bean1.setDesgID(checkNull(String.valueOf(data[i][0])));
			bean1.setDesgName(checkNull(String.valueOf(data[i][1])));
			bean1.setDesgParCode(checkNull(String.valueOf(data[i][2])));
			bean1.setDesgHighCode(checkNull(String.valueOf(data[i][3])));
			bean1.setDesgAbbr(checkNull(String.valueOf(data[i][4])));
			bean1.setDesgDesc(checkNull(String.valueOf(data[i][5])));
			bean1.setDesgLevel(checkNull(String.valueOf(data[i][6])));
			bean1.setRcmndAuth(checkNull(String.valueOf(data[i][7])));
			bean1.setApprAuth(checkNull(String.valueOf(data[i][8])));
			att.add(bean1);
		}
		desgMaster.setAtt(att); 
		
	}
	
	public void getReport(DesignationMaster desgMaster,HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session){
		CrystalReport cr=new CrystalReport(); 
		String path="org\\rpt\\admin\\master\\designation.rpt";
		cr.createReport(request, response, context,session, path, "");
	}
	
	
	public void getDesignationRecord(DesignationMaster bean) {
		Object addObj[] =new Object[1];
		
		addObj[0] =bean.getDesgID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {			
			bean.setDesgID(String.valueOf(data[i][0]));
			bean.setDesgName(checkNull(String.valueOf(data[i][1])));
			bean.setDesgParCode(checkNull(String.valueOf(data[i][2])));
			bean.setDesgHighCode(checkNull(String.valueOf(data[i][3])));
			bean.setDesgAbbr(checkNull(String.valueOf(data[i][4])));
			bean.setDesgDesc(checkNull(String.valueOf(data[i][5])));
			bean.setDesgLevel(checkNull(String.valueOf(data[i][6])));			
			bean.setRcmndAuth(checkNull(String.valueOf(data[i][7])));
			bean.setApprAuth(checkNull(String.valueOf(data[i][8])));
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
}
