package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.OnlinePaper;
import org.paradyne.model.recruitment.OnlinePaperModel;
import org.struts.lib.ParaActionSupport;

public class OnlinePaperPriviewAction extends ParaActionSupport{
	OnlinePaper onlinePaper;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public OnlinePaper getOnlinePaper() 
	{
		return onlinePaper;
	
	}

	public void setOnlinePaper(OnlinePaper OnlinePaper) {
		this.onlinePaper = onlinePaper;
	}
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		 onlinePaper = new OnlinePaper();
		 }

	public Object getModel() {
		// TODO Auto-generated method stub
		return onlinePaper;
	}
	
	public String priview()throws Exception {	
		OnlinePaperModel model = new OnlinePaperModel();
		model.initiate(context,session);
		String PaperCode=onlinePaper.getPaperCode();
		
		System.out.println("____________________________________");
		model.showRecord1( onlinePaper,request,PaperCode,"");
		
		return SUCCESS; 
		}

	
	public String next()throws Exception {	
		OnlinePaperModel model = new OnlinePaperModel();
		model.initiate(context,session);
				
		String [] questcode=request.getParameterValues("quesCode");
		String [] optionCode=request.getParameterValues("optionCode");
		String [] quesCodeChk=request.getParameterValues("quesCodeChk");
		if(questcode!=null){
		for (int i = 0; i < questcode.length; i++) {
			System.out.println("________code________"+questcode[i]);
		}
		}
		if(optionCode!=null){
			for (int i = 0; i < optionCode.length; i++) {
				System.out.println("________optionCode_______"+optionCode[i]);
			}
		
		}
		
		if(quesCodeChk!=null){
			for (int i = 0; i < quesCodeChk.length; i++) {
				System.out.println("quesCodeChk_____"+quesCodeChk[i]);
			}
		}	
		String [] subjectQuestCode=request.getParameterValues("subjectQuestCode");	
		String PaperCode=onlinePaper.getPaperCode();	
		String cndloCode=onlinePaper.getCandidateLoginCode();	
		model.showRecord1(onlinePaper,request,PaperCode,cndloCode);
		String [] testCode=request.getParameterValues("testCode");
		String [] SubOpt=request.getParameterValues("SubOpt");
		model.findCorrectRecord(onlinePaper,questcode,optionCode,quesCodeChk,cndloCode,subjectQuestCode,testCode,SubOpt);
		onlinePaper.setTimerFlag("start");
		model.terminate();	
		System.out.println("___________________________");
		return "priview"; 
		}
	public String privious()throws Exception {	
		OnlinePaperModel model = new OnlinePaperModel();
		model.initiate(context,session);
				
		String [] questcode=request.getParameterValues("quesCode");
		String [] optionCode=request.getParameterValues("optionCode");
		String [] quesCodeChk=request.getParameterValues("quesCodeChk");
		if(questcode!=null){
		for (int i = 0; i < questcode.length; i++) {
			System.out.println("________code________"+questcode[i]);
		}
		}
		if(optionCode!=null){
			for (int i = 0; i < optionCode.length; i++) {
				System.out.println("________optionCode_______"+optionCode[i]);
			}
		}
		if(quesCodeChk!=null){
			for (int i = 0; i < quesCodeChk.length; i++) {
				System.out.println("quesCodeChk_____"+quesCodeChk[i]);
			}
		}	
		String PaperCode=onlinePaper.getPaperCode();
		String [] subjectQuestCode=request.getParameterValues("subjectQuestCode");	
		String cndloCode=onlinePaper.getCandidateLoginCode();	
		String [] testCode=request.getParameterValues("testCode");	
		model.showRecord1(onlinePaper,request,PaperCode,cndloCode);
		String [] SubOpt=request.getParameterValues("SubOpt");
		model.findCorrectRecord(onlinePaper,questcode,optionCode,quesCodeChk,cndloCode,subjectQuestCode,testCode,SubOpt);
		onlinePaper.setTimerFlag("start");
		model.terminate();	
		//System.out.println("___________________________");
		return "priview"; 
		}
	
	
	
	
	
	
}
