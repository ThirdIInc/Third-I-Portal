/**
 * 
 */
package org.struts.action.recruitment;

import javax.servlet.http.HttpSession;

import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.bean.Recruitment.OnlinePaper;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.common.LoginModel;
import org.paradyne.model.recruitment.CandidateLoginModel;
import org.paradyne.model.recruitment.OnlinePaperModel;
import org.struts.lib.DyneActionSupport;


public class CandidateTestAction extends DyneActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.DyneActionSupport#prepare_local()
	 */
	
	OnlinePaper onlinePaper ;
	//private CandidateLoginBean candidateLoginBean;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.DyneActionSupport.class);
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		onlinePaper= new OnlinePaper();
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return onlinePaper;
	}
	public String submitnew() throws Exception {
		
		String poolName ="" ;
		String result ="" ;
		try {
			 poolName = new StringEncrypter(
			 StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
			 StringEncrypter.URL_ENCRYPTION_KEY).decrypt(onlinePaper.getInfoId());
			 logger.info("login 4");
		}
		catch (Exception e) {
		// TODO: handle exception
		}
		
	logger.info("poolname--------------------"+poolName);
		
	CandidateLoginModel model = new CandidateLoginModel();
	HttpSession session=request.getSession();
	session.setAttribute("session_pool",poolName);
	model.initiate(context,session);
	
	//String flag=model.submitnewuser(onlinePaper);
	//String userFlag=model.checkUser(onlinePaper);
	
	
	   model.terminate();
	  return result;
	
	}

/*public String next()throws Exception {	
	OnlinePaperModel model = new OnlinePaperModel();
	model.initiate(context,session);
	model.checkPrevious(onlinePaper,request);
	
	String [] subjectQuestCode=request.getParameterValues("subjectQuestCode");		
	String [] questcode=request.getParameterValues("quesCode");
	String [] optionCode=request.getParameterValues("optionCode");
	String [] quesCodeChk=request.getParameterValues("quesCodeChk");
	String [] testCode=request.getParameterValues("testCode");	
	String [] SubjOpt=request.getParameterValues("SubjOpt");
	String  paperName=request.getParameter("paperName");	
	String tQues=request.getParameter("totalNoOfQues");
	
	onlinePaper.setPaperName(paperName);
	onlinePaper.setTotalNoOfQues(tQues);
	if(SubjOpt!=null&&SubjOpt.length>0)
		for (int i = 0; i < SubjOpt.length; i++) {
		System.out.println("----SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS--------"+SubjOpt[i]);
	}
	String PaperCode=onlinePaper.getPaperCode();	
	String cndlogincode=onlinePaper.getCandidateLoginCode();
	model.findCorrectRecord(onlinePaper,questcode,optionCode,quesCodeChk,cndlogincode,subjectQuestCode,testCode,SubjOpt);
	onlinePaper.setTimerFlag("start");
	model.terminate();	
	
	return "gofortest"; 
	}
public String privious()throws Exception {	
	OnlinePaperModel model = new OnlinePaperModel();
	model.initiate(context,session);
	model.checkPrevious(onlinePaper,request);
	model.terminate();	
	return "gofortest"; 
	}

public String result()throws Exception{
	
	String [] subjectQuestCode=request.getParameterValues("subjectQuestCode");		
	String [] questcode=request.getParameterValues("quesCode");
	String [] optionCode=request.getParameterValues("optionCode");
	String [] quesCodeChk=request.getParameterValues("quesCodeChk");
	String [] testCode=request.getParameterValues("testCode");	
	String cndlogincode=onlinePaper.getCandidateLoginCode();
	String [] SubjOpt=request.getParameterValues("SubjOpt");
	OnlinePaperModel model = new OnlinePaperModel();
	model.initiate(context, session);
	model.findCorrectRecord(onlinePaper,questcode,optionCode,quesCodeChk,cndlogincode,subjectQuestCode,testCode,SubjOpt);
	char res=model.result(onlinePaper,cndlogincode,request);
	String str=(String)request.getAttribute("percentage");
	
	if(res=='P'){
		
		addActionMessage("'CONGRATULATION'YOU HAVE CLEARED THE EXAM  YOUR PERCENTAGE IS :="+str+"%");
	}
	else{		
		addActionMessage("'SORRY' YOU ARE  NOT CLEARED THE EXAM YOUR PERCENTAGE IS :="+str+"%");
	}
	
	model.terminate();
	return "error"; 
 }*/

}


		
	
		
	
	



