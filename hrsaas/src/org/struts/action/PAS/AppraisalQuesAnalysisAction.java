/**
 * 
 */
package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalQuesAnalysis;
import org.paradyne.model.PAS.AppraisalQuesAnalysisModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0554
 *
 */
public class AppraisalQuesAnalysisAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	AppraisalQuesAnalysis apprQuesAnalysis;
	@Override
	public void prepare_local() throws Exception {
		apprQuesAnalysis = new AppraisalQuesAnalysis();
		apprQuesAnalysis.setMenuCode(1037);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return apprQuesAnalysis;
	}
	
public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR "
			+" ORDER BY APPR_ID";

		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.from"),getMessage("appraisal.to")};

		
		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId"};

		int[] columnIndex = { 0,1,2,3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}
	
public String f9AppQues()throws Exception{
		
		String query = " SELECT DISTINCT APPR_QUES_DESC,APPR_QUES_CATEG_NAME,APPR_QUES_CODE FROM PAS_APPR_QUESTIONNAIRE  "
					+" INNER JOIN PAS_APPR_QUESTION_CATGORY ON(PAS_APPR_QUESTION_CATGORY.APPR_QUES_CATG_CODE=PAS_APPR_QUESTIONNAIRE.APPR_QUES_CATG_CODE)" 
					+" WHERE APPR_QUES_STATUS ='A' ORDER BY APPR_QUES_CATEG_NAME,APPR_QUES_DESC";

		
		String[] headers = { getMessage("question"),getMessage("question")+"Category"};

		
		String[] headerWidth = { "75","25"};

		String[] fieldNames = { "questionDesc","questionCategory","questionCode"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}
public String f9Section()throws Exception{
		
		String query = " SELECT APPR_SECTION_NAME,APPR_SECTION_ID FROM PAS_APPR_SECTION_HDR WHERE APPR_ID="+apprQuesAnalysis.getApprId()
		+" ORDER BY APPR_SECTION_ORDER";

		String[] headers = { getMessage("section")};

		String[] headerWidth = { "100"};

		String[] fieldNames = { "sectionName","sectionId"};

		int[] columnIndex = { 0,1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}
public String getReport(){
	AppraisalQuesAnalysisModel model=new AppraisalQuesAnalysisModel();
	model.initiate(context, session);
	if(!apprQuesAnalysis.getQuestionCode().equals(""))
	model.getReport(request,response,apprQuesAnalysis,getMessage("career.progression"));
	else 
		model.getReportSectionWise(request,response,apprQuesAnalysis,getMessage("career.progression"));
	model.terminate();
	return null;
}

}
