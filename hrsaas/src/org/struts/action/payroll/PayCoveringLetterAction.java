package org.struts.action.payroll;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.PayCoveringLetter;
import org.paradyne.model.payroll.PayCoveringLetterModel;
/*
 * author:Pradeep
 * Date:20.03.2008
 */



public class PayCoveringLetterAction extends ParaActionSupport {
	PayCoveringLetter pcl;
static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {
		pcl=new PayCoveringLetter();	 
		pcl.setMenuCode(547);
	}

	public PayCoveringLetter getPcl() {
		return pcl;
	}

	public void setPcl(PayCoveringLetter pcl) {
		this.pcl = pcl;
	}
	
	public Object getModel() {
		return this.pcl;
	}
	
	public void report() {
		PayCoveringLetterModel model=new PayCoveringLetterModel();
		
		model.initiate(context, session);
		try{
		model.getReport(pcl,response);
		}catch(Exception e) {
			
			logger.info("Pradeep"+e);
			e.printStackTrace();
		}
		model.terminate();
		
		
	}
	
	
public String f9div() throws Exception {
		
		String sql = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ORDER BY DIV_ID ";
		
		String[] headers = {"Division Code","Division Name"};
		String[] headersWidth = {"20"," 40"};
		
		String[] fieldName = {"divId","divName"};
		String submitFlag = "false";
		
		int[] columnIndex = {0,1};
		String submitToMethod ="PayCoveringLetter_.action" ;//"EmpCredit_empDetail.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	

}
