/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.NewJoinee;
import org.paradyne.model.admin.srd.NewJoineeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author balajim
 *
 */
public class NewJoineeAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
 
	
	NewJoinee newjoin;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		newjoin = new NewJoinee();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return newjoin;
	}

	public NewJoinee getNewjoin() {
		return newjoin;
	}

	public void setNewjoin(NewJoinee newjoin) {
		this.newjoin = newjoin;
	}
	
	public String report()throws Exception 
	{
	/* NewJoineeModel model = new NewJoineeModel();
	 model.initiate(context,session);			
	 model.getReport(newjoin,response);
	 model.terminate();	
	 return null;  */
	NewJoineeModel model = new NewJoineeModel();
	model.initiate(context, session);
	model.getNewJoinReport(newjoin, request, response);
	model.terminate();
	return null;
	}

	/*This function is used to generate Report 
	 * using ireportV2 Lib
	 * */
	public String newJoinReport() throws Exception {
		NewJoineeModel model = new NewJoineeModel();
		model.initiate(context, session);
		model.getNewJoinReport(newjoin, request, response);
		model.terminate();
		return null;
	}

}
