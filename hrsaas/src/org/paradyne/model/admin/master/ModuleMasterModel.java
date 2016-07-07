package org.paradyne.model.admin.master;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.master.ModuleMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author saipavan voleti
 * Dt:13/8/2009
 */
public class ModuleMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModuleMasterModel.class); 
	
	
	/**
	 * @param bean
	 * @return boolean after adding new record.
	 */
	public boolean addrecord(ModuleMaster bean) {
		Object addObj[][]=new Object[1][4];
		
        addObj[0][0]=bean.getModuleName();       
        addObj[0][1]=bean.getDescription();
        addObj[0][2]=bean.getHidAuthflag();
        addObj[0][3]=bean.getModuleType().trim();
        
		return getSqlModel().singleExecute(getQuery(1),addObj);  
	}
	
	/**
	 * @param bean
	 * @return boolean after modifying the existing record.
	 */

	public boolean modrecord(ModuleMaster bean) {
		logger.info("prasad testing..modrecordmodrecord.!!");
		Object addObj[][] =new Object[1][5];
            
		    addObj[0][0]=bean.getModuleName();	       
	        addObj[0][1]=bean.getDescription();
	      	addObj[0][2]=bean.getHidAuthflag();
	        addObj[0][3]=bean.getModuleType().trim();
	        addObj[0][4]=bean.getModuleCode();
        
		return getSqlModel().singleExecute(getQuery(2),addObj);  // update query
		
	}
	
	/**
	 * @param bean
	 * @return boolean after deleting record.
	 */
	public boolean deleterecord(ModuleMaster bean) {
		Object addObj[][] =new Object[1][1];

         addObj[0][0] =bean.getModuleCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	  // delete query

	}

	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/* method name : Data 
	 * purpose     : to show the records heads in the list
	 * return type : void
	 * parameter   : Master instance, HttpServletRequest request
	 */

	public void  Data(ModuleMaster  bean, HttpServletRequest request) {

		Object [][] obj = getSqlModel().getSingleResult(getQuery(5));
		
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
		logger.info("my page...!"+bean.getMyPage());
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("abc", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("xyz", Integer.parseInt(String.valueOf(pageIndex[3])));
		
	   if(pageIndex[4].equals("1"))
		   bean.setMyPage("1");
	  			
		
		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {

		//	for (int i = 0; i < obj.length; i++) {
			 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
		
			ModuleMaster  bean1 = new ModuleMaster ();
			bean1.setModuleCode(checkNull(String.valueOf(obj[i][0])));
			bean1.setModuleName(checkNull(String.valueOf(obj[i][1])));
			//bean1.setDescription(checkNull(String.valueOf(obj[i][2])));
			bean1.setModuleType(checkNull(String.valueOf(obj[i][2])));
			//	bean.setHidAuthflag(checkNull(String.valueOf(data[0][3])));
			//bean1.setStatus(String.valueOf(obj[i][2]));
			

			list.add(bean1);
		} // end of for loop



		bean.setIteratorlist(list);

	}
	}
	/* method name : callForEdit 
	 * purpose     : to show the record head details after edit
	 * return type : void
	 * parameter   : Master instance, HttpServletRequest request
	 */

	public void callForEdit(ModuleMaster bean) {


		String query="SELECT MODULE_CODE, MODULE_NAME, MODULE_DESC,MODULE_AUTHFLAG,MODULE_TYPE FROM HRMS_MODULE WHERE MODULE_CODE="+bean.getHiddencode() ;

		System.out.println("Query....!!!"+query);
		Object [][]data=getSqlModel().getSingleResult(query);
	    bean.setModuleCode(String.valueOf(data[0][0]));
		 bean.setModuleName(String.valueOf(data[0][1]));
		bean.setDescription(checkNull(String.valueOf(data[0][2])));
		bean.setHidAuthflag(checkNull(String.valueOf(data[0][3])));
		bean.setModuleType(checkNull(String.valueOf(data[0][4])));
		// bean.setStatus(String.valueOf(data[0][4]));
		


	}
	/* method name : deleteCheckedRecords 
	 * purpose     : to delete the multiple checked records.
	 * return type : boolean
	 * parameter   : bean instance, String[] code
	 */
	public boolean deleteCheckedRecords(ModuleMaster bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {

				if(!code[i].equals("")){

					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(3), delete);  // delete the checked voucher heads
					if(!result)
						count++;

				} // end of if
			} // end of for loop
		} // end of if
		if(count!=0)
		{	
		result=false;
		return result;
		} // end of if
		else
		{
		result=true;
		return result;
		} // end of else
	}

	public void details(ModuleMaster bean) {
		// TODO Auto-generated method stub
		String query="SELECT MODULE_DESC,MODULE_AUTHFLAG,MODULE_TYPE FROM HRMS_MODULE WHERE MODULE_CODE="+bean.getModuleCode();
		System.out.println("Query....!!"+query);
		Object data[][]= getSqlModel().getSingleResult(query);
		
		bean.setDescription(checkNull(String.valueOf(data[0][0])));
		bean.setHidAuthflag(checkNull(String.valueOf(data[0][1])));
		bean.setModuleType(checkNull(String.valueOf(data[0][2])));
		//Specbean.setStatus(String.valueOf(data[0][1]));
		
	}

	public void setModulenames(ModuleMaster bean) {
		// TODO Auto-generated method stub
		TreeMap map = new TreeMap();
		String query="SELECT MODULE_CODE, MODULE_NAME FROM HRMS_MODULE order by MODULE_CODE";
		Object [][] obj = getSqlModel().getSingleResult(query);
		if(obj!=null && obj.length>0)
		{
			for (int j = 0; j < obj.length; j++) {
				map.put(String.valueOf(obj[j][0]), String.valueOf(obj[j][1]));
			}
			
		}
		map.put(String.valueOf("0"), String.valueOf("Select Module"));
		bean.setMap(map);
		
	}

}




