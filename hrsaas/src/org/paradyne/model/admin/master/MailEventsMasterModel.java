package org.paradyne.model.admin.master;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.master.MailEventsMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


/**
 * @author saipavan voleti
 * Dt:13/8/2009
 */
public class MailEventsMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MailEventsMasterModel.class); 
	
	
	/**
	 * @param bean
	 * @return boolean after adding new record.
	 */
	public boolean addrecord(MailEventsMaster bean) {
		Object addObj[][]=new Object[1][3];
		 logger.info("-->dupModulecode  "+bean.getDupModulecode());
		 logger.info("-->modulename  "+bean.getModuleName());
        addObj[0][0]=bean.getMailEventName();       
        addObj[0][1]=bean.getMailEventdesc();
        addObj[0][2]=bean.getDupModulecode();
        	//bean.getModuleCode();
        
		
			return getSqlModel().singleExecute(getQuery(1),addObj);  
		/*
			boolean flag=
		if(flag)
			return getSqlModel().singleExecute(getQuery(5)); 
		else 
			return false;
			*/
		
		
	}
	
	/**
	 * @param bean
	 * @return boolean after modifying the existing record.
	 */

	public boolean modrecord(MailEventsMaster bean) {
	
		Object addObj[][] =new Object[1][4];
            logger.info("-->dupModulecode  "+bean.getDupModulecode());
            logger.info("-->modulename  "+bean.getModuleName());
		    addObj[0][0]=bean.getMailEventName();	       
	        addObj[0][1]=bean.getMailEventdesc();
	        addObj[0][2]=bean.getDupModulecode();
	        	//bean.getModuleCode();
	       	addObj[0][3]=bean.getMailEventcode();
	     // addObj[0][3]=bean.getStatus();
        
		return getSqlModel().singleExecute(getQuery(2),addObj);  // update query
		
	}
	
	/**
	 * @param bean
	 * @return boolean after deleting record.
	 */
	public boolean deleterecord(MailEventsMaster bean) {
		String dtlquery="select EVENT_CODE, EVENT_OPTION_FLAG, NVL(EVENT_TEMPLATE_CODE,0) from HRMS_MAIL_EVENTS  where EVENT_CODE="+bean.getMailEventcode();
		//String query="delete from HRMS_REC_MAIL_CONF  where MAIL_EVENT_CODE="+bean.getMailEventcode();
		
		Object recordDtl[][]=getSqlModel().getSingleResult(dtlquery);
		Object addObj[][] =new Object[1][1];
        addObj[0][0] =bean.getMailEventcode();
		if(recordDtl!=null && recordDtl.length>0)
		{
			if(String.valueOf(recordDtl[0][1]).equalsIgnoreCase("Y") || !String.valueOf(recordDtl[0][2]).equals("0"))
			{
				return false;
			}	
			else{
				logger.info("u r in else..!");
			}
		}	
		return getSqlModel().singleExecute(getQuery(3),addObj);	  // delete query
		/*
		boolean flag=
		if(flag)
			return getSqlModel().singleExecute(query);
		else
              return false;*/
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

	public void  Data(MailEventsMaster  bean, HttpServletRequest request) {

		Object [][] obj = getSqlModel().getSingleResult(getQuery(4));
		
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
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
		
			MailEventsMaster  bean1 = new MailEventsMaster ();
			bean1.setMailEventcode(checkNull(String.valueOf(obj[i][0])));
			bean1.setMailEventName(checkNull(String.valueOf(obj[i][1])));
		//	bean1.setMailEventdesc(checkNull(String.valueOf(obj[i][2])));
			bean1.setModuleName(checkNull(String.valueOf(obj[i][2])));
			
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

	public void callForEdit(MailEventsMaster bean) {


		String query="SELECT EVENT_CODE,EVENT_NAME, EVENT_DESC,EVENT_MODULE_CODE,HRMS_MODULE.MODULE_NAME FROM HRMS_MAIL_EVENTS " +
		        " inner join HRMS_MODULE on (HRMS_MODULE.MODULE_CODE=HRMS_MAIL_EVENTS.EVENT_MODULE_CODE)"+
				" WHERE EVENT_CODE="+bean.getHiddencode() ;

		System.out.println("Query....!!!"+query);
		Object [][]data=getSqlModel().getSingleResult(query);
	    bean.setMailEventcode(String.valueOf(data[0][0]));
		 bean.setMailEventName(String.valueOf(data[0][1]));
		bean.setMailEventdesc(checkNull(String.valueOf(data[0][2])));
    	bean.setModuleName(checkNull(String.valueOf(data[0][4])));
    	bean.setDupModulecode(checkNull(String.valueOf(data[0][3])));
		
		// bean.setStatus(String.valueOf(data[0][4]));
		


	}
	/* method name : deleteCheckedRecords 
	 * purpose     : to delete the multiple checked records.
	 * return type : boolean
	 * parameter   : bean instance, String[] code
	 */
	public boolean deleteCheckedRecords(MailEventsMaster bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {

				if(!code[i].equals("")){

					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					String dtlquery="select EVENT_CODE, EVENT_OPTION_FLAG, NVL(EVENT_TEMPLATE_CODE,0) from HRMS_MAIL_EVENTS  where EVENT_CODE="+code[i];
					Object recordDtl[][]=getSqlModel().getSingleResult(dtlquery);
					result=false;
					if(recordDtl!=null && recordDtl.length>0)
					{
						if(String.valueOf(recordDtl[0][1]).equalsIgnoreCase("Y") || !String.valueOf(recordDtl[0][2]).equals("0"))
						{
							// return false;
						}else
						{
							logger.info("u r in else..!"+code[i] );
							result=getSqlModel().singleExecute(getQuery(3), delete); 
						}
					}	
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

	public void details(MailEventsMaster bean) {
		// TODO Auto-generated method stub
		String query="SELECT EVENT_DESC,EVENT_MODULE_CODE,HRMS_MODULE.MODULE_NAME FROM HRMS_MAIL_EVENTS " +
		     " inner join HRMS_MODULE on (HRMS_MODULE.MODULE_CODE=HRMS_MAIL_EVENTS.EVENT_MODULE_CODE)"+
				"  WHERE EVENT_CODE="+bean.getMailEventcode();
		System.out.println("Query....!!"+query);
		Object data[][]= getSqlModel().getSingleResult(query);
		
		bean.setMailEventdesc(checkNull(String.valueOf(data[0][0])));
	    bean.setModuleName(checkNull(String.valueOf(data[0][2])));
		bean.setDupModulecode(checkNull(String.valueOf(data[0][1])));
		//Specbean.setStatus(String.valueOf(data[0][1]));
		
	}

	public void setModulenames(MailEventsMaster bean) {
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
		map.put(String.valueOf("0"), String.valueOf("Select Module Name"));
		bean.setMap(map);
		
	}

	
		
	
	
	
}








