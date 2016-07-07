/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.TestReschedule;
import org.paradyne.model.recruitment.TestRescheduleModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0517
 *
 */
public class TestRescheduleAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SchdInterviewListAction.class);
	TestReschedule testResch;
	
	public TestReschedule getTestResch() {
		return testResch;
	}
	public void setTestResch(TestReschedule testResch) {
		this.testResch = testResch;
	}
	
	public void prepare_local() throws Exception {
		testResch = new TestReschedule();
		testResch.setMenuCode(928);
		//schdIntList.setStatusFlag("false");
		// TODO Auto-generated method stub
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return testResch;
	}
	

	/**
	 * this method is to retrieve all Scheduled interview list whose INTERVIEW CONDUCT STATUS IS "N"
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		//scheduledInterviews();
	}
	/**
	 * added on date:17-03-2009
	 */
	public String input(){
		TestRescheduleModel model = new TestRescheduleModel();
		model.initiate(context, session);
		//schdIntList.setSearchFlag("true");
		String status = "N";
		String stat = "Scheduled Test List";
		request.setAttribute("stat", stat);
		testResch.setButtonFlag("true");
		//schdIntList.setEnableFilterValue(true);
		/*String msg=getMessage("reqs.code");
		String msg1=getMessage("cand.name");
		String msg2=getMessage("interVType");
		String msg3=getMessage("intDate");
		String msg4=getMessage("Interviewer");*/
		model.getSchdTestList(testResch,status,request);
		model.terminate();
		return "success";
	} //end of input method
	
	public String cancelTestList()throws Exception{
		logger.info("in cancelTestList");
		testResch.setButtonFlag("false");
		TestRescheduleModel model = new TestRescheduleModel();
		model.initiate(context, session);
		String status = "C";
		model.getCancelledTestList(testResch,status,request);
		model.terminate();
		return SUCCESS;
	} //end of cancelTestList method
	
	public String cancelTestSchedule()throws Exception{
		String [] reqCode = request.getParameterValues("requisitionCode");
		String[] radio = request.getParameterValues("radioButton");//THIS IS RADIO BUTTON VALUE FOR WHICH ROW IT IS CHECKED
		String[] testCod = request.getParameterValues("testCode");
		String[] tesDtlCode = request.getParameterValues("testDtlCode");
		logger.info("radioButton[0]   :"+radio[0]);
		logger.info("requisitionCode in fromTestRescheduleList===="+reqCode.length);
		String requisitionCode="";
		String testCode="";
		String testDtlCode="";
		
		if (radio == null) {
		}//end of if 
		else if (radio.length == 0) {
		}//end of else
		else{
			try {
				/**
				 * HERE FROM (radio[0] - 1 ) is done bcoz the value of radio is 1 greater from the row value.
				 */
				requisitionCode = reqCode[Integer.parseInt(radio[0]) - 1];
				testCode = testCod[Integer.parseInt(radio[0]) - 1];
				testDtlCode = tesDtlCode[Integer.parseInt(radio[0]) - 1];
				logger.info("testDtlCode from schdInterviewLIst   :"+testDtlCode);
			} catch (Exception e) {
				logger.error("exception in fromScheduleInterviewList",e);
			}
			
			TestRescheduleModel model = new TestRescheduleModel();
			model.initiate(context, session);
			String result = model.cancelSchedule(testResch,request,requisitionCode,testCode,testDtlCode);
			model.terminate();
			if(result.equals("1")){
				addActionMessage("Test schedule cancelled successfully");
			} //end of if
			String status = "N";
			String stat = "Rechedule List";
			request.setAttribute("stat", stat);
			testResch.setButtonFlag("true");
			//schdIntList.setEnableFilterValue(true);
			/*String msg=getMessage("reqs.code");
			String msg1=getMessage("cand.name");
			String msg2=getMessage("interVType");
			String msg3=getMessage("intDate");
			String msg4=getMessage("Interviewer");*/
			model.getSchdTestList(testResch,status,request);
		} //end of else
		return SUCCESS;
	} //end of cancelTestSchedule method
	
} //end of class
