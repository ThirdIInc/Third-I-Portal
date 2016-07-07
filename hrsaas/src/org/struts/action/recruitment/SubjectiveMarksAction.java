/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.SubjectiveMarks;
import org.paradyne.model.recruitment.OnlinePaperModel;
import org.paradyne.model.recruitment.SubjectiveMarksModel;
import org.struts.lib.ParaActionSupport;

import freemarker.log.Logger;

public class SubjectiveMarksAction extends ParaActionSupport {

	
	SubjectiveMarks subMarks=null;
	/**
	 * @return the subMarks
	 */
	public SubjectiveMarks getSubMarks() {
		return subMarks;
	}

	/**
	 * @param subMarks the subMarks to set
	 */
	public void setSubMarks(SubjectiveMarks subMarks) {
		this.subMarks = subMarks;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		subMarks=new SubjectiveMarks();
		

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return subMarks;
	}
	
	public String reset()
	{		
		subMarks.setTestCode("");
		subMarks.setQuesCode("");
		subMarks.setSubjectQuestCode("");	
		subMarks.setPaperCode("");
		subMarks.setPaperName("");
		subMarks.setFromDate("");
		subMarks.setToDate("");
	    subMarks.setCandidateName("");
		subMarks.setQuesDescItt("");
		subMarks.setSubjOpt("");
		subMarks.setMarks("");
		subMarks.setMaxMarks("");
				
	return SUCCESS;
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		
		SubjectiveMarksModel model=new SubjectiveMarksModel();
		model.initiate(context, session);
		model.getPendingList(subMarks);
		model.terminate();
		//return "success";
	}
	public String save() throws Exception{
		String []testCode=request.getParameterValues("testCode");
		String []subjectQuestCode=request.getParameterValues("subjectQuestCode");
		String []quesCode=request.getParameterValues("quesCode");
		String []maxMarks=request.getParameterValues("maxMarks");
		String []marks=request.getParameterValues("marks");
				
	    SubjectiveMarksModel subModel= new SubjectiveMarksModel();
		
	    subModel.initiate(context,session);
	   
		subModel.save(subMarks,testCode,subjectQuestCode,quesCode,maxMarks,marks);
		
		addActionMessage("Record saved Successfully");
	  	reset();
		subModel.terminate();
		return SUCCESS;
		
	}
	
	public String f9Paperaction()throws Exception {
		//System.out.println("12535----------------------");
		String query = "SELECT PAPER_CODE,PAPER_NAME FROM HRMS_PAPER_HDR";
		String []headers ={"Paper Code","Paper Name"};
		String []headerwidth={"20","80"};
		String []fieldNames={"subMarks.paperCode","subMarks.paperName"};
		int []columnIndex={0,1};
		String submitFlage ="false";
		String submitToMethod = " ";
	
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		return "f9page";
	}

}
