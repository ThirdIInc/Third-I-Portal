/**
 * 
 */
package org.struts.action.exam;
import org.paradyne.bean.exam.CandidatesTestResult;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.exam.CandidatesTestResultModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author diptip
 *
 */
public class CandidatesTestResultAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);

	/**
	 * 
	 */
	CandidatesTestResult ctres;
	public CandidatesTestResult getCtres() {
		return ctres;
	}

	public void setCtres(CandidatesTestResult ctres) {
		this.ctres = ctres;
	}

	public CandidatesTestResultAction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
	 ctres=new CandidatesTestResult();

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return ctres;
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		
		CandidatesTestResultModel model=new CandidatesTestResultModel();
			model.initiate(context,session);
			//model.testData(ctres,request);
			model.terminate();
	}
	public String report()
	{
		CandidatesTestResultModel model=new CandidatesTestResultModel();
		model.initiate(context,session);
		logger.info("++++++++++++++");
		model.getReport(ctres,response);
		model.terminate();
		
		return null;
		
	}
	public String search()
	{
		CandidatesTestResultModel model=new CandidatesTestResultModel();
		model.initiate(context,session);
		logger.info("++++++++++++++");
		model.testData(ctres,request);
		model.terminate();
		
		return SUCCESS;
		
	}
	public String reset(){
		ctres.setPaperCode("");
		ctres.setPaperName("");
		ctres.setFromDate("");
		ctres.setToDate("");
		ctres.setUcutoff("");
		ctres.setLcutoff("");
	return SUCCESS;
	}
	public String f9Paper() throws Exception {
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT PAPER_CODE,PAPER_NAME FROM HRMS_PAPER_HDR ORDER BY PAPER_CODE  ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Paper Code", "Paper Name"};
		String[] headerWidth={"20", "20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"ctres.paperCode","ctres.paperName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod=" ";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	

}
