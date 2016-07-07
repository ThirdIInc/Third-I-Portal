package org.paradyne.model.recruitment;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import org.paradyne.bean.Recruitment.*;
import org.paradyne.lib.ModelBase;

public class RecruitmentSettingModel extends ModelBase {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	
	    public boolean addSetting(RecruitmentSetting recSet) {
	       	Object[][] add=new Object[1][4];
	         	add[0][0]=recSet.getReqNo();
	         	add[0][1]=recSet.getReqWrkFlw();
	    	    add[0][2]=recSet.getReqPlnFlw();
	    	    add[0][3]=recSet.getSchFlw();
	    	    boolean result=getSqlModel().singleExecute(getQuery(1),add);
	    	    
	    	    return result;
		    }
	    
	    public boolean modSetting(RecruitmentSetting recSet){
	    		Object[][] mod=new Object[1][5];
	    		mod[0][0]=recSet.getReqNo();
	    		mod[0][1]=recSet.getReqWrkFlw();
	    		mod[0][2]=recSet.getReqPlnFlw();
	    		mod[0][3]=recSet.getSchFlw();
	    		mod[0][4]=recSet.getRecuisitonCode();
	    		
	    		boolean result=getSqlModel().singleExecute(getQuery(2),mod);
	        		
	          return result;
	    
	   
	    } 
	    
	    
	    public boolean delSetting(RecruitmentSetting recSet){
    		Object[][] del=new Object[1][1];
    		del[0][0]=recSet.getRecuisitonCode();
    		
    		
    		
    		boolean result=getSqlModel().singleExecute(getQuery(3),del);
        		
          return result;
    
   
    } 
	    
	    
	    public void getRecords(RecruitmentSetting recSet,HttpServletRequest request) {
	    	
	    	Object[] param=new Object[1];
	    	param[0]=recSet.getRecuisitonCode();
	    	   	
	    	
	    	Object [][] data=getSqlModel().getSingleResult(getQuery(4),param);
	    	recSet.setRecuisitonCode(String.valueOf(data[0][0]));
	    	recSet.setReqNo(String.valueOf(data[0][1]));
	    	recSet.setReqWrkFlw(String.valueOf(data[0][2]));
	    	recSet.setReqPlnFlw(String.valueOf(data[0][3]));
	    	recSet.setSchFlw(String.valueOf(data[0][4]));
	    	
	    	
	    	request.setAttribute("data",String.valueOf(data[0][2]));
	    	request.setAttribute("data1", String.valueOf(data[0][3]));
	    	request.setAttribute("data2", String.valueOf(data[0][4]));
	    	
	   	
	    	
	    	
	    }
	    
	    
	    
}


