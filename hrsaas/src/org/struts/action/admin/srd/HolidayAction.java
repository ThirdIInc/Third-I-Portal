/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.Holiday;
import org.paradyne.model.admin.srd.HolidayModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author balajim
 *
 */
public class HolidayAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
 
	
	Holiday hday ;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		hday= new Holiday();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return hday;
	}

	public Holiday getHday() {
		return hday;
	}

	public void setHday(Holiday hday) {
		this.hday = hday;
	}
	
	 public String report()throws Exception 
	{
		/*HolidayModel model = new HolidayModel();
	 model.initiate(context,session);			
	 model.getReport(hday,response);
	 model.terminate();	
	 return null;*/
	HolidayModel model = new HolidayModel();
	model.initiate(context, session);
	model.getholidayListReport(hday,request,response);
	model.terminate();
	return null;
	} 
	
	/*public String holidayList()throws Exception 
	{
			HolidayModel model = new HolidayModel();
			model.initiate(context,session);			
			model.getholidayListReport(hday,response);
			model.terminate();	
			return null;    
	}
*/
	/*This function is used to generate Report 
	 * using ireportV2 Lib
	 * */
	/*public String holidayReport() throws Exception {
		HolidayModel model = new HolidayModel();
		model.initiate(context, session);
		model.getHolidayReport(hday, request, response);
		model.terminate();
		return null;
	}*/
}
