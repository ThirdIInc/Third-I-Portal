/**
 * 
 */
package org.struts.action.admin.srd;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.srd.Birthday;
import org.paradyne.model.admin.srd.BirthdayModel;
import org.paradyne.model.admin.srd.FamilyMisReportModel;

/**
 * @author AA0623
 *
 */
public class BirthdayReportAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	Birthday birth ;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		birth = new Birthday();
		birth.setMenuCode(475);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return birth;
	}
	public Birthday getBirth() {
		return birth;
	}

	public void setBirth(Birthday birth) {
		this.birth = birth;
	}

	
	public String report()throws Exception 
	{
	 /*try {
		BirthdayModel model = new BirthdayModel();
		model.initiate(context, session);
		model.getReport(response, birth);
		model.terminate();
	} catch (Exception e) {
		// TODO: handle exception
	}
	return null;    */
		
	BirthdayModel model = new BirthdayModel();
	model.initiate(context, session);
	model.getBirthDayReport(birth, request, response);
	model.terminate();
	return null;	
	}
	
	/*This function is used to generate Report 
	 * using ireportV2 Lib
	 * */
	public String birthDayReport() throws Exception {
		BirthdayModel model = new BirthdayModel();
		model.initiate(context, session);
		model.getBirthDayReport(birth, request, response);
		model.terminate();
		return null;
	}

}
