/**
 * 
 */
package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.bean.Recruitment.OnlineTest;
import org.paradyne.model.recruitment.CandidateLoginModel;
import org.paradyne.model.recruitment.OnlineTestModel;
import org.struts.lib.DyneActionSupport;

/**
 * @author varunk
 *
 */
public class OnlineTestAction extends DyneActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(OnlineTestAction.class);
		OnlineTest onlineTest;
		
	public void prepare_local() throws Exception {
		onlineTest = new OnlineTest();
		onlineTest.setMenuCode(348);
	}
	
	public Object getModel() {
		return onlineTest;
	}

	public OnlineTest getOnlineTest() {
		return onlineTest;
	}

	public void setOnlineTest(OnlineTest onlineTest) {
		this.onlineTest = onlineTest;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = "/" + poolName;
		}
		final String fileDataPath = this.getText("data_path") + "/upload" + poolName + "/OnlineTestDocs/";
		this.onlineTest.setDataPath(fileDataPath);
	}
	
	public String input() {
		OnlineTestModel model = new OnlineTestModel();
		model.initiate(context, session);
		model.getScheduledTestList(request, onlineTest);
		model.terminate();
		return "input";
	}
	
	public String startTestValidation() {
		String resultToReturn = "toTemplateDtl";
		String testTime = request.getParameter("testTime");
		String testDate = request.getParameter("testDate");
		String testTemplate = request.getParameter("testTemplate");
		String requisitionCode = request.getParameter("requisitionCode"); 
		String templateCode = request.getParameter("templateCode"); 
		String testCode = request.getParameter("testCode"); 
		
		if (testCode != null && testCode.length() > 0) {
			onlineTest.setOnlineTestCode(testCode);
		}
			
		if (templateCode != null && templateCode.length() > 0) {
			onlineTest.setTemplateCode(templateCode);
		}
		
		if(requisitionCode != null && requisitionCode.length() > 0) {
			onlineTest.setReqCode(requisitionCode);
		}
		OnlineTestModel model = new OnlineTestModel();
		model.initiate(context, session);
		String result = model.getTestTemplateDetails(request, onlineTest, testTime, testDate, testTemplate);
		if(result.equals("testTimeExpire")){
			addActionMessage("Your test time has been expired");
			onlineTest.setTestTemplateCode("");
			onlineTest.setReqCode("");
			resultToReturn = "input";
		} else if(result.equals("testDateExpire")){
			addActionMessage("Your test date has been expired");
			onlineTest.setTestTemplateCode("");
			onlineTest.setReqCode("");
			resultToReturn = "input";
		} else if(result.equals("waitTillScheduledDateTime")){
			addActionMessage("Your test is scheduled on " + testDate + " at " + testTime + ". Please wait. ");
			resultToReturn = "input";
		} 
		
		if (resultToReturn.equals("input")) {
			input();
		} 
		model.terminate();
		return resultToReturn;
	}
	
	 
	/**
	 * this method is called when the test stars....like on the Continue button and each 
	 * and every time on the Next Button
	 * @return
	 * @throws Exception
	 */
	public String startTest()throws Exception{
		String time = request.getParameter("onlineTime");
		if(time!=null){
			onlineTest.setText(time);
		}
		String answerCode = request.getParameter("optionCode");//this is answers given by the candidate
		OnlineTestModel model = new OnlineTestModel();
		model.initiate(context, session);
		boolean result = model.submittedTestStatus(onlineTest); 
		if (result) {
			addActionMessage("Your test has been already submitted.");
		} else {
			model.startTest(onlineTest,request);
			model.terminate();
			onlineTest.setTimerFlag("start");//this is used in the java script to start the time..
		}
		//onlineTest.setSubjectAns("");
		return "toStartTest";
	}  
	
	
	/**
	 * this method is called to view the previous question
	 * @return
	 * @throws Exception
	 */
	public String previous()throws Exception{
		String sequence1 = onlineTest.getSequenceCode();
		OnlineTestModel model = new OnlineTestModel();
		model.initiate(context, session);
		boolean result = model.submittedTestStatus(onlineTest); 
		if (result) {
			addActionMessage("Your test has been already submitted.");
		} else {
			model.getPrevious(onlineTest,request);
			/**
			 * this if condition is checked if in case SEQUENCE VALUE is greater than 1 than the Previous Button
			 * should be visible. So the PreviousButton flag is set true...
			 */
			if(!(request.getParameter("sequenceCode")==null ||request.getParameter("sequenceCode").equals("")
					||request.getParameter("sequenceCode").equals("null"))){
				int sequence = Integer.parseInt(onlineTest.getSequenceCode());
				if(sequence >1){
					onlineTest.setPreviousButton("true");
				}  
			}  
		}
		
		model.terminate();
		
		
		return "toStartTest";
	} //end of method
	
	public String submit()throws Exception{
		OnlineTestModel model = new OnlineTestModel();
		model.initiate(context, session);
		boolean result = model.submittedTestStatus(onlineTest); 
		if (result) {
			addActionMessage("Your test has been already submitted.");
			onlineTest.setResultFlag("true");
		} else {
			model.submit(onlineTest,request);
			if(onlineTest.getTestType().equals("O")){
				if(onlineTest.getOnlineScore().equals("Y")){
					onlineTest.setChkOnlineScoreFlag("true");
					model.getTestResult(onlineTest,request);
					if(onlineTest.getResult().equals("Pass")){
						onlineTest.setResultFlag("true");
					} else{
						onlineTest.setResultFlag("false");
					} 
				} else {
					onlineTest.setResultFlag("true");
					onlineTest.setChkOnlineScoreFlag("false");
					model.getTestResult(onlineTest,request);
				}  
			} else {
				onlineTest.setResultFlag("true");
				onlineTest.setChkOnlineScoreFlag("false");
				model.getTestResult(onlineTest,request);
			} 
		}
		model.terminate();		
		return "onlineTestSubmit";
	}
	
	public String cancel()throws Exception{
		input(); 
		return SUCCESS;
	}  
	
	public String backMenu()throws Exception{
		try {
			OnlineTestModel model = new OnlineTestModel();
			model.initiate(context, session);
			model.createMenu(request);
			model.terminate();
			CandidateLoginModel loginmodel = new CandidateLoginModel();
			loginmodel.initiate(context, session);
			loginmodel.createMainMenu(request);
			Object[][] logo = loginmodel.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			getCandName(onlineTest, request);
			loginmodel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return "backToMainMenu";
	}  
	
	public void getCandName(OnlineTest onlineTest,
			HttpServletRequest request) {
		try {
			OnlineTestModel model = new OnlineTestModel();
			model.initiate(context, session);
			String candQuery = "SELECT CAND_FIRSTNAME||' '||CAND_LASTNAME FROM HRMS_REC_CAND_LOGIN WHERE CAND_CODE='"
								+ onlineTest.getCandidateUserEmpId() + "'";
			Object[][] data = model.getSqlModel().getSingleResult(candQuery);
			request.setAttribute("CandName", String.valueOf(data[0][0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 * Method : openAttachedFile().
	 * Purpose : Method to open attached file.
	 * @throws IOException - Exception.
	 */
	public void openUploadedFile() throws IOException {
		String addedFile = request.getParameter("fileName");
		final String[] extension = addedFile.replace(".","#").split("#");
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.onlineTest.getDataPath();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = "pdf";
			final String applnDoc = "doc";
			final String applnXls = "xls";
			final String applnXlsx = "xlsx";
			final String applnJpg = "jpg";
			final String applnTxt = "gif";
			final String applnGif = "txt";

			final String mimeTypeAcrobat = "acrobat";
			final String mimeTypeMSWord = "msword";
			final String mimeTypeMSExcel = "msexcel";
			final String mimeTypeJpg = "jpg";
			final String mimeTypeTxt = "gif";
			final String mimeTypeGif = "txt";

			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}

			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename=\"" 	+ addedFile + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}

			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}
}
