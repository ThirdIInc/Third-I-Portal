package org.paradyne.model.recruitment;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.TestTemplate;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class TestTemplateModel extends ModelBase{

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	int scriptPageNo = 10;
	
	/**
	 * @author AA0877
	 * @method showRecords
	 * @purpose to retrieve the test template details.
	 * @param bean
	 * @param status
	 * @return String
	 */
	public void showRecords(TestTemplate testTemplate,HttpServletRequest request) {
		logger.info("IN showRecords model");
		
		try {			
			Object[][] applicationData = getSqlModel().getSingleResult(getQuery(1));
			
			if(applicationData == null){
				testTemplate.setNoData("true");
			} //end of if
			else if(applicationData.length == 0){
				testTemplate.setNoData("true");
			} //end of else if
			testTemplate.setTotalRecords(""+applicationData.length);
				int REC_TOTAL = 0;
				int To_TOT = 20;
				int From_TOT = 0;
				int pg1=0;
				int PageNo1=1;//----------
				REC_TOTAL=applicationData.length;
				int no_of_pages=Math.round(REC_TOTAL/20);
				//PageNo = Integer.parseInt(locationMaster.getMyPage());//-----------
				double row = (double)applicationData.length/20.0;

				java.math.BigDecimal value1 = new java.math.BigDecimal(row);
			 
				int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());

				logger.info("--------------------"+rowCount1);
				logger.info("------- Template page------------"+ testTemplate.getMyPage());
				ArrayList<Object> obj=new ArrayList<Object>();
				request.setAttribute("abc", rowCount1);

				//PageNo
				if(String.valueOf( testTemplate.getMyPage()).equals("null")||String.valueOf(testTemplate.getMyPage()).equals(null)||String.valueOf( testTemplate.getMyPage()).equals(""))
				{
				PageNo1=1;
				From_TOT=0;
				To_TOT=20;

				  if(To_TOT >applicationData.length){
					  To_TOT=applicationData.length;
				  }
					logger.info("-----------To_TOTAL----null-----"+To_TOT);
					testTemplate.setMyPage("1");
				}

				else{
					logger.info("ssss"+testTemplate.getMyPage());
				  pg1=Integer.parseInt(testTemplate.getMyPage());
				  PageNo1=pg1;
				  
				  if(pg1==1){
					 From_TOT=0;
					 To_TOT=20;
				  }
				  else{
					//  From_TOTAL=To_TOTAL+1;
						 To_TOT=To_TOT*pg1;
						 From_TOT=(To_TOT-20);
				  }
				  if(To_TOT >applicationData.length){
					  To_TOT=applicationData.length;
				  }
				 
			  }
				request.setAttribute("xyz", PageNo1);
			  	logger.info("------------from total--------"+From_TOT);
			  	logger.info("----------to total----------"+To_TOT);
			  	
			  	
				ArrayList<Object> list = new ArrayList<Object>();

				
					for (int i=From_TOT;i<To_TOT; i++) {
						TestTemplate bean1 = new TestTemplate();
						logger.info("application length"+i);
						bean1.setSrNo(String.valueOf(i+1));								//serial number
						bean1.setTestName(String.valueOf(applicationData[i][0]));		//Test Name
						bean1.setTestDuration(String.valueOf(applicationData[i][1]));	//Test Duration
						bean1.setTestType(String.valueOf(applicationData[i][2]).trim());		//test type
						bean1.setTotalQuestions(String.valueOf(applicationData[i][3]));//Total Questions	
						bean1.setTestTotalMarks(String.valueOf(applicationData[i][4]));//total marks
						bean1.setPassingMark(String.valueOf(applicationData[i][5]));	//passing marks
						bean1.setTestTemplateCode(String.valueOf(applicationData[i][6]));	//passing marks
						list.add(bean1);
					}	//for loop ends
					
				//if statement ends 
				testTemplate.setList(list);
				 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @author AA0877
	 * @purpose This function call 2 functions which load all subjects and category from db.
	 * @param bean
	 * @param request
	 * @return void
	 */
	public void loadData(TestTemplate bean) {
		
		retrieveSubject(bean);
		retrieveCategory(bean);
	} //end of loadData method
	
	/**
	 * @author AA0877
	 * @purpose This function displays all the subjects from db.
	 * @param bean
	 * @return void
	 */
	public void retrieveSubject(TestTemplate bean){
		logger.info("inside retrieveSubject");
		
		HashMap<Object, Object> subjectMap = new HashMap<Object, Object>();
		
		Object [][] subjectData = getSqlModel().getSingleResult(getQuery(2));
		
		if(subjectData != null && subjectData.length != 0){
			 for (int i = 0; i < subjectData.length; i++) {
				subjectMap.put(String.valueOf(subjectData [i][0]), String.valueOf(subjectData [i][1]));
			}
			 //sort subjectMap as per the subject name
			 subjectMap = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(subjectMap, null, true);
			 
			bean.setSubjectMap(subjectMap);
			bean.setSubjectFlag("true");
		}
		else{
			bean.setSubjectFlag("false");
		}
		bean.setSubject("");
	}
	
	/**
	 * @author AA0877
	 * @purpose This function displays all Categories from db.
	 * @param bean
	 * @return void
	 */
	public void retrieveCategory(TestTemplate bean){
		logger.info("inside retrieveCategory");
		
		HashMap<Object, Object> categoryMap = new HashMap<Object, Object>();
		Object [][] categoryData = null;
		logger.info("bean.getHiddenSubject()=====>"+bean.getHiddenSubject());
		if(bean.getHiddenSubject()==null||bean.getHiddenSubject().equals("null")||bean.getHiddenSubject().equals("")){
			categoryData = getSqlModel().getSingleResult(getQuery(3));
		}
		else{
			
			String selCat = "SELECT CAT_CODE,CAT_NAME FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE IN ("+bean.getHiddenSubject()+") "		   				
				+"ORDER BY CAT_NAME";
			categoryData = getSqlModel().getSingleResult(selCat);
		}
		
		
		if(categoryData != null && categoryData.length != 0){
			 for (int i = 0; i < categoryData.length; i++) {
				 categoryMap.put(String.valueOf(categoryData [i][0]), String.valueOf(categoryData[i][1]));
			}
			 categoryMap = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(categoryMap, null, true);
			 
			bean.setCategoryMap(categoryMap);
			bean.setCategoryFlag("true");
		}
		else{
			bean.setCategoryFlag("false");
		}
		bean.setCategory("");
	}
	
	/**
	 * @author AA0877
	 * @purpose to select the questions from question bank
	 * @param testtemplate
	 * @param request
	 * @return void
	 */
	public void selectQuestion(TestTemplate testtemplate, HttpServletRequest request) {
		logger.info("in selectQuestion model");
		testtemplate.setHiddenSubject(testtemplate.getSubject());	//hidden subjectCode
		testtemplate.setHiddenCategory(testtemplate.getCategory());//hidden categoryCode
		/*
		 * select questions from question bank for the selected subject, complexicity and test type
		 */
		String query = "SELECT QUES_CODE, QUES_NAME, QUES_SUB_CODE, SUBJECT_NAME, QUES_TYPE, QUES_CAT_CODE, "
					   +"CAT_NAME, DECODE(QUES_LEVEL, 'H', 'Hard', 'M', 'Medium', 'E', 'Easy') "
					   +"FROM HRMS_REC_QUESBANK "
					   +"LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE = QUES_SUB_CODE) "
					   +"LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = QUES_CAT_CODE) "
					   +"WHERE QUES_SUB_CODE IN ("+testtemplate.getSubject()+")";
					   		
					   logger.info("template cat......."+testtemplate.getCategory());
					   if(testtemplate.getCategory()!=null && !testtemplate.getCategory().equals("")){
						   
						   query += " AND QUES_CAT_CODE IN ("+testtemplate.getCategory()+")";
					   } //end of if
					   
					   if(testtemplate.getTestType().equals("B")){
						   query += " AND QUES_TYPE IN ('O', 'S') ";
					   } //end of if
					   else{
						   query += " AND QUES_TYPE = '"+testtemplate.getTestType()+"'";
					   } //end of else
					   
					   if(!testtemplate.getCompLevel1().equals("")){
						   query += "AND QUES_LEVEL IN ("+testtemplate.getCompLevel1()+") ";
					   } //end of if
					   
					   if(!testtemplate.getAddToListQuestions().equals("")){
						   String questionCodes = testtemplate.getAddToListQuestions();
							questionCodes = questionCodes.substring(0, questionCodes.length() - 1);
						   query += " AND QUES_CODE NOT IN ("+questionCodes+") ";
					   } //end of if
					   
					   query += " ORDER BY QUES_SUB_CODE, QUES_CAT_CODE, QUES_LEVEL";
		logger.info("testtemplate.getMyPage()===="+testtemplate.getMyPage());
		Object [][] questionData = getSqlModel().getSingleResult(query);
		
		String[] pageIndex = Utility.doPaging(testtemplate.getMyPage(),questionData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="2";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		
		if(pageIndex[4].equals("1"))
			testtemplate.setMyPage("1");
		
		ArrayList<Object> quesDataList = new ArrayList<Object>();
		
		if(questionData != null && questionData.length != 0){
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
				TestTemplate bean = new TestTemplate();
				
				bean.setSelectQuesCode(checkNull(String.valueOf(questionData[i][0])));// question code
				bean.setQuesName(checkNull(String.valueOf(questionData[i][1])));// question name
				bean.setComplexicity(checkNull(String.valueOf(questionData[i][7]))); //complexicity
				
				if(!checkNull(String.valueOf(questionData[i][6])).equals("")){
					bean.setQuesSubject(checkNull(String.valueOf(questionData[i][3]))
							+" --> "+checkNull(String.valueOf(questionData[i][6]))); //sublect-category
				}
				else{
					bean.setQuesSubject(checkNull(String.valueOf(questionData[i][3]))); //subject
				}
				quesDataList.add(bean);
			}// end of for
		}
		
		testtemplate.setQuesDataList(quesDataList);
		
		String str = request.getParameter("id");
		String str1 = request.getParameter("id1");
		testtemplate.setTestRequirements(str);
		if(testtemplate.getHardCompLevel()!=null){
			if(testtemplate.getHardCompLevel().equalsIgnoreCase("'H'"))
			{
				logger.info("hard");
				testtemplate.setHardCompLevel("'H'");
				testtemplate.setHard("'H'");
				logger.info("hard.."+testtemplate.getHard());
			}
		}else{
			
			testtemplate.setHard(null);
		}
		if(testtemplate.getMediumCompLevel()!=null){
			if(testtemplate.getMediumCompLevel().equalsIgnoreCase("'M'"))
			{
				logger.info("medium");
				testtemplate.setMediumCompLevel("'M'");
				testtemplate.setMedium("'M'");
				logger.info("medium.."+testtemplate.getMedium());
			}
		}else{
			testtemplate.setMedium(null);
		}
		
		if(testtemplate.getEasyCompLevel()!=null){
			if(testtemplate.getEasyCompLevel().equalsIgnoreCase("'E'"))
			{
				logger.info("easy");
				testtemplate.setEasyCompLevel("'E'");
				testtemplate.setEasy("'E'");
				logger.info("easy.."+testtemplate.getEasy());
			}
		}else{
			testtemplate.setEasy(null);
		}
		testtemplate.setTestReqCode(str1);
	}
	
	/**
	 * @Method isExists
	 * @Purpose to check whether the template is already exists or not
	 * @param bean
	 * @return boolean
	**/
	public boolean isExists(TestTemplate bean){
		logger.info("in isExists ");
		
		boolean result = false;
		String query = "SELECT * FROM HRMS_REC_TEST_TEMPLATE WHERE UPPER(TEMPLATE_TEST_NAME) = '"
						+bean.getTestName().toUpperCase()+"'";
		
		if(!bean.getTestTemplateCode().equals("")){
			query += " AND TEMPLATE_CODE  != "+bean.getTestTemplateCode();
		}

		Object [][] testData = getSqlModel().getSingleResult(query);
		
		if(testData != null && testData.length != 0)result = true;
		
		return result;
	}
	
	/**
	 * @Method saveFirst
	 * @Purpose to save the template details in HRMS_REC_TEST_TEMPLATE table
	 * @param bean
	 * @param request
	 * @return String
	**/
	public String saveFirst(TestTemplate testTemplate, HttpServletRequest request){
		logger.info("in saveFirst model ");
		
		String flag = "";
		
		//if template already exists return duplicate message
		if(isExists(testTemplate)){
			flag = "duplicate";
		} //end of if
		else{
			boolean result = false;
			Object[][] templateData = new Object[1][28];
		
			templateData[0][0] = testTemplate.getTestName();		//TEMPLATE TEST NAME
			templateData[0][1] = testTemplate.getTestDuration();	//TEMPLATE DURATION
			templateData[0][2] = testTemplate.getTestType();		//TEMPLATE TEST TYPE
			
			if(testTemplate.getEnableScore().equals("true"))templateData[0][3] = "Y";	//enable score
			else templateData[0][3] = "N";	//enable score
			
			templateData[0][4] = testTemplate.getTestTotalMarks();//total marks
			templateData[0][5] = testTemplate.getTotalNoOfQues();	//total no of questions
			
			//logger.info("testTemplate.getEqualWeightage()===>"+testTemplate.getEqualWeightage());
			if(testTemplate.getCorrectCheck().equals("false")){
				templateData[0][6] = "Y";	//equal weightage
				templateData[0][27]=testTemplate.getMarksAllocatedForEach();//marks allocated for each
			} //end of if
			else{
				templateData[0][6] = "N";	//equal weightage
				templateData[0][27]="0";//marks allocated for each
			} //end of else
			
			if(testTemplate.getCorrectCheck().equals("true")){
				templateData[0][7] = "Y";	//marks for correct answer
				templateData[0][8] = testTemplate.getMarksForHard();	//marks for hard
				templateData[0][9] = testTemplate.getMarksForMedium(); //marks for medium
				templateData[0][10] = testTemplate.getMarksForEasy();	//marks for easy
			} //end of if
			else{
				templateData[0][7] = "N";	//marks for correct answer
				templateData[0][8] = "0";	//marks for hard
				templateData[0][9] = "0"; //marks for medium
				templateData[0][10] ="0";	//marks for easy
			} //end of else
				templateData[0][11] = "Y";	//marks for wrong answer
				templateData[0][12] = testTemplate.getWrongmarksForHard();	//wrong marks for hard
				templateData[0][13] = testTemplate.getWrongmarksForMedium(); //wrong marks for medium
				templateData[0][14] = testTemplate.getWrongmarksForEasy();	//wrong marks for easy
			
			//templateData[0][11] = testTemplate.getMarkWrong();	//marks for wrong 
			templateData[0][15] = testTemplate.getMarksForNoAns();	//marks for no answer
			templateData[0][16] = testTemplate.getPassingMark();		//passing marks
			templateData[0][17] = testTemplate.getInstructionNotes();	//instruction notes
			
			
			if(testTemplate.getQuestionFrom().equals("add")){
				testTemplate.setAddQuestion("checked");
				testTemplate.setRandomQuestion("");
				testTemplate.setAddQuestionList("true");
				/*templateData[0][18] = testTemplate.getCntEasy();	//easy count
				templateData[0][19] = testTemplate.getCntMedium();//midium count	
				templateData[0][20] = testTemplate.getCntHard();//hard count	
				templateData[0][21] = testTemplate.getTotalnoOfQus(); //total no of questions
				
				templateData[0][22] = testTemplate.getMarkEasy();	//easy count
				templateData[0][23] = testTemplate.getMarkMedium();//midium count	
				templateData[0][24] = testTemplate.getMarkHard();//hard count	
				templateData[0][25] = testTemplate.getTotalMarkss(); //total no of questions
*/			} //end of if
			else{
				testTemplate.setRandomQuestion("checked");
				testTemplate.setAddQuestion("");
				testTemplate.setAddQuestionList("false");
			} //end of else
			templateData[0][18] = testTemplate.getCntRandomEasy();	//easy count
			templateData[0][19] = testTemplate.getCntRandomMedium();//midium count	
			templateData[0][20] = testTemplate.getCntRandomHard();//hard count	
			templateData[0][21] = testTemplate.getTotalRandomnoOfQus(); //total no of questions
			
			templateData[0][22] = testTemplate.getMarkRandomEasy();	//easy count
			templateData[0][23] = testTemplate.getMarkRandomMedium();//midium count	
			templateData[0][24] = testTemplate.getMarkRandomHard();//hard count	
			templateData[0][25] = testTemplate.getTotalRandomMarkss(); //total no of questions
			
			templateData[0][26]=testTemplate.getQuestionFrom();
			
			result = getSqlModel().singleExecute(getQuery(4), templateData);
			
			if(result){
				//insertQuestionDetails method to insert the selected questions details 
				result = insertQuestionDetails(testTemplate, request);
				
				if(result)flag = "saved";
				else flag = "error";
			} //end of if
			else flag = "error";
		} //end of else
		//flag = "saved";
		return flag;
	} //end of saveFirst method
	
	/**
	 * @Method getTemplateCode
	 * @Purpose to select the max template code from HRMS_REC_TEST_TEMPLATE table
	 * @param bean
	 * @param Code
	 * @param request
	 * @return String
	**/
	public String getTemplateCode(TestTemplate testTemplate, boolean Code, HttpServletRequest request) throws Exception
	{
		logger.info("insert query value...!"+String.valueOf(Code));
		 String reqcode="";
		if(Code){
			String query = "SELECT MAX(TEMPLATE_CODE) FROM HRMS_REC_TEST_TEMPLATE";
			
			Object[][] reqsCode = getSqlModel().getSingleResult(query);	//select the max template code from database
			
			if(reqsCode != null){
				logger.info("with in if condition...!"+String.valueOf(reqsCode[0][0]));
				reqcode = String.valueOf(reqsCode[0][0]);
				testTemplate.setTestTemplateCode(String.valueOf(reqsCode[0][0]));
				logger.info("tempalate code...!"+testTemplate.getTestTemplateCode());
			}
			//viewDetails(testTemplate,reqcode);
		}
		return reqcode;
	}
	
	public void viewCartDetails(TestTemplate bean,HttpServletRequest request){
		
		String query ="SELECT SUBJECT_NAME,CAT_NAME,QUES_NAME,DECODE(QUES_LEVEL, 'H', 'Hard', 'M', 'Medium', 'E', 'Easy'),QUES_CODE FROM HRMS_REC_QUESBANK LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE = QUES_SUB_CODE) LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = QUES_CAT_CODE)"+ 
					  "WHERE QUES_CODE IN("+bean.getHiddenQuestionCode()+")";
		
		Object [][] questionData = getSqlModel().getSingleResult(query);
		 
		ArrayList<Object> quesDataList = new ArrayList<Object>();		
		if(questionData != null && questionData.length != 0){
			
								
				//logger.info("question code is "+String.valueOf(questionData[i][0]));
				for (int i = 0; i < questionData.length; i++) {
					
				TestTemplate bean1 = new TestTemplate();
				bean1.setSelectQuesCode(checkNull(String.valueOf(questionData[i][4])));// question code
				bean1.setQuesName(checkNull(String.valueOf(questionData[i][2])));// question name
				 
				bean1.setComplexicity(checkNull(String.valueOf(questionData[i][3]))); //complexicity
				
				if(!checkNull(String.valueOf(questionData[i][1])).equals("")){
					bean1.setQuesSubject(checkNull(String.valueOf(questionData[i][0]))
							+" --> "+checkNull(String.valueOf(questionData[i][1]))); //sublect-category
				 }
				else{
						bean1.setQuesSubject(checkNull(String.valueOf(questionData[i][0]))); //subject
					}
				quesDataList.add(bean1);
				}// end of for
									
			}
		bean.setQuesDataList(quesDataList);
					
	}
	/**
	 * @Method viewDetails
	 * @Purpose to display the details of the selected template
	 * @param bean
	 * @param tempcode
	 * @param request
	**/
	public void viewDetails(TestTemplate bean, String tempcode, HttpServletRequest request) throws Exception{
		logger.info("in viewDetails model ");
		try {
		if(bean.getTestType().equals("O"))bean.setTestType("Objective");	//test type Objective
		else if(bean.getTestType().equals("S"))bean.setTestType("Subjective");//test type Subjective
		else if(bean.getTestType().equals("B"))bean.setTestType("Both");//test type Both Objective or Subjective
		
		//EnableScore
		if(bean.getEnableScore().equals("true"))bean.setEnableScore("Yes");
		else bean.setEnableScore("No");
		
		if(bean.getCorrectCheck().equals("true")){
			bean.setEqualDistriRadio("");
			bean.setDefineDistriRadio("checked");
		} //end of if
		else{
			bean.setEqualDistriRadio("checked");
			bean.setDefineDistriRadio("");
		} //end of else
		
			bean.setCntRandomEasy(bean.getCntRandomEasy());
			bean.setCntRandomHard(bean.getCntRandomHard());
			bean.setCntRandomMedium(bean.getCntRandomMedium());
			bean.setTotalRandomnoOfQus(bean.getTotalRandomnoOfQus());
			
			bean.setMarkRandomEasy(bean.getMarkRandomEasy());
			bean.setMarkRandomHard(bean.getMarkRandomHard());
			bean.setMarkRandomMedium(bean.getMarkRandomMedium());
			bean.setTotalRandomMarkss(bean.getTotalRandomMarkss());
	
			bean.setQuestionFrom(bean.getQuestionFrom());	//question from 
		
		//showSubjectCategory method to show the subject and category
		showSubjectCategory(bean, bean.getSubject(), bean.getCategory());
		
		Object[][]tempType = null;
		try {
			String templateTypeQuery = "SELECT TEMPLATE_TYPE FROM HRMS_REC_TEST_TEMPLATE WHERE TEMPLATE_CODE = "
					+ bean.getTestTemplateCode() + "";
			tempType = getSqlModel().getSingleResult(templateTypeQuery);
		} catch (Exception e) {
			logger.error("exception in template type query", e);
		} //end of catch
		
		if(String.valueOf(tempType[0][0]).equals("random")){
			bean.setRandomQuestion("checked");
			bean.setAddQuestion("");
			bean.setAddQuestionList("false");
			setRandomQuestionListFromDatabase(bean);
			
		} //end of if
		else{
			bean.setAddQuestion("checked");
			bean.setRandomQuestion("");
			bean.setAddQuestionList("true");
			//setAddQuestionListFromDatabase(bean);
			setAddList(bean, request);	//set the list of the questions
		} //end of else
		
		} catch (Exception e) {
			logger.error("exception in viewDetails method", e);
		} //end of catch
	} //end of viewDetails method
	
	
	private void setRandomQuestionListFromDatabase(TestTemplate bean) {
		Object [][] randomQuesDetails = null;
		
		try {
			String query = "SELECT SUBJECT_NAME, CAT_NAME, TEMPLATE_SUBCODE, TEMPLATE_CATCODE, TEMPLATE_NOOFQUES, "
					+ " DECODE(TEMPLATE_COM_LEVEL, 'H', 'Hard', 'M', 'Medium', 'E', 'Easy') "
					+ " FROM HRMS_REC_TESTTEMPDTL "
					+ " LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE = TEMPLATE_SUBCODE) "
					+ " LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = TEMPLATE_CATCODE) "
					+ " WHERE TEMPLATE_CODE ="+ bean.getTestTemplateCode();
			randomQuesDetails = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in randomQuesDetails query",e);
		} //end of catch
		if(randomQuesDetails !=null && randomQuesDetails.length > 0){
			bean.setQuestionFrom("random");
			String subjectCode = "0";
			String categoryCode = "0";
			ArrayList<Object> questionList = new ArrayList<Object>();
			
			for (int i = 0; i < randomQuesDetails.length; i++) {
				TestTemplate bean1 = new TestTemplate();
				
				if(!checkNull(String.valueOf(randomQuesDetails[i][1])).equals("")){
					bean1.setSubjectCategory(checkNull(String.valueOf(randomQuesDetails[i][0]))
							+"-->"+checkNull(String.valueOf(randomQuesDetails[i][1]))); //sublect-category
				}
				else{
					bean1.setSubjectCategory(checkNull(String.valueOf(randomQuesDetails[i][0]))); //sublect-category
				}
				
				bean1.setRandomSubCode(checkNull(String.valueOf(randomQuesDetails[i][2])));//subject code
				bean1.setRandomCatCode(checkNull(String.valueOf(randomQuesDetails[i][3])));//category code
				bean1.setRandomComplexicity(checkNull(String.valueOf(randomQuesDetails[i][5])));//complexicity
				
				if(checkNull(String.valueOf(randomQuesDetails[i][5])).equals("Hard")){
					bean1.setComplexicityCode("H");
				}
				else if(checkNull(String.valueOf(randomQuesDetails[i][5])).equals("Medium")){
					bean1.setComplexicityCode("M");
				}
				else if(checkNull(String.valueOf(randomQuesDetails[i][5])).equals("Easy")){
					bean1.setComplexicityCode("E");
				}
				
				String query = "  SELECT COUNT(*) FROM HRMS_REC_QUESBANK "
							   +" WHERE QUES_SUB_CODE = "+checkNull(String.valueOf(randomQuesDetails[i][2]))
							   +" AND QUES_CAT_CODE="+checkNull(String.valueOf(randomQuesDetails[i][3]))+""
							   +" AND QUES_LEVEL = '"+bean1.getComplexicityCode()+"' ";
				
				System.out.println("Test Type3......"+bean.getTestType());
				
				if(bean.getTestType().equals("Both") || bean.getTestType().equals("B")){
					query += "AND QUES_TYPE IN ('O', 'S') ";
				}
				else if(bean.getTestType().equals("Objective") || bean.getTestType().equals("O")){
					query += "AND QUES_TYPE = 'O' ";
				}
				else query += "AND QUES_TYPE = 'S' ";
				
				Object [][] noOfQues = getSqlModel().getSingleResult(query);
				
				if(noOfQues != null && noOfQues.length != 0)
					bean1.setAvailableQuestion(checkNull(String.valueOf(noOfQues[0][0])));
				else	bean1.setAvailableQuestion("0");
				
				bean1.setTestQuestion(checkNull(String.valueOf(randomQuesDetails[i][4])));
				
				questionList.add(bean1);
				
				subjectCode += checkNull(String.valueOf(randomQuesDetails[i][2]))+",";
				categoryCode += checkNull(String.valueOf(randomQuesDetails[i][3]))+",";
			}
			bean.setQuestionList(questionList);
			
			subjectCode = subjectCode.substring(0, subjectCode.length()-1);
			categoryCode = categoryCode.substring(0, categoryCode.length()-1);
			
			showSubjectCategory(bean, subjectCode, categoryCode);
			bean.setNotAvailable("false");
		} //end of if
		else{
			bean.setNotAvailable("true");
			bean.setSubjectFlag("");
			bean.setCategoryFlag("");
		} //end of else
	} //end of setRandomQuestionListFromDatabase method

	public void setAddList(TestTemplate bean, HttpServletRequest request){
		logger.info("in setQuestionList model ");
		
		Object [][] addQuesDetails = getSqlModel().getSingleResult(getQuery(12), new Object[] {bean.getTestTemplateCode()});
		
		if(addQuesDetails != null && addQuesDetails.length != 0){
			String selectQues = "SELECT QUES_NAME FROM HRMS_REC_QUESBANK "
								+"WHERE QUES_CODE IN ("+checkNull(String.valueOf(addQuesDetails[0][0]))+")";
			
			Object [][] quesData = getSqlModel().getSingleResult(selectQues);
			
			if(quesData != null && quesData.length != 0){
				String question = "";
				for (int i = 0; i < quesData.length; i++) {
					question += (i + 1) + ". "+String.valueOf(quesData[i][0]) + "\n ";
				}
				bean.setQuesName(question);
				bean.setSelectQuesCode(checkNull(String.valueOf(addQuesDetails[0][0])));
			}
			bean.setQuestionFrom("add");
			bean.setAddQuestion("checked");
			bean.setRandomQuestion("");
			bean.setAddQuestionList("true");
			
			Object [][] addQueDetails = getSqlModel().getSingleResult(getQuery(15), new Object[] {bean.getTestTemplateCode()});
			if(addQueDetails != null && addQueDetails.length != 0){
				logger.info("addQueDetails===>"+addQueDetails.length);
				bean.setAddToListQuestions(String.valueOf(addQueDetails[0][0])+",");
				bean.setQuestionListFlag("false");
				String s[] = String.valueOf(addQueDetails[0][0]).split(",");
				request.setAttribute("addQuestionTotalPages", (s.length % scriptPageNo == 0)?(s.length/scriptPageNo):(s.length/scriptPageNo)+1);
				bean.setCnt(String.valueOf(s.length));
				ArrayList<Object> questionList = new ArrayList<Object>();
				
				for(int i=0;i<s.length;i++){
				TestTemplate bean1 = new TestTemplate();
				String queryAdd="SELECT QUES_SUB_CODE,QUES_CODE,QUES_TYPE,DECODE(QUES_LEVEL,'H', 'Hard', 'M', 'Medium', 'E', 'Easy'),QUES_NAME,QUES_CAT_CODE FROM HRMS_REC_QUESBANK"+
								" where (HRMS_REC_QUESBANK.QUES_CODE="+s[i]+")";
				
				
				Object [][] queslist = getSqlModel().getSingleResult(queryAdd);
				
				String subname="SELECT SUBJECT_NAME FROM HRMS_REC_SUBJECT WHERE SUBJECT_CODE IN ("+queslist[0][0]+")ORDER BY SUBJECT_NAME";
				
				Object [][] sublist = getSqlModel().getSingleResult(subname);
				
				String catname="SELECT CAT_NAME FROM HRMS_REC_CATEGORY "+
						  	" WHERE CAT_CODE IN (" +queslist[0][5]+ ") ORDER BY CAT_NAME";
				
				Object [][] catlist = getSqlModel().getSingleResult(catname);
				
				
				bean1.setSrno(i);
				//bean1.setDupQusSubject(queslist[0][0].toString());
				bean1.setDupQusSubject(checkNull(String.valueOf(sublist[0][0]))
						+"-->"+checkNull(String.valueOf(catlist[0][0]))); 
				bean1.setDupQusName(queslist[0][4].toString());
				bean1.setDupComplexicity(queslist[0][3].toString());
				bean1.setDupSelectQuCode(queslist[0][1].toString());														
				questionList.add(bean1);
				
				}
				bean.setAddlist(questionList);
			}
			
			showSubjectCategory(bean, String.valueOf(addQuesDetails[0][1]),  String.valueOf(addQuesDetails[0][2]));
			bean.setNotAvailable("false");
		} //end of else
		
	//	bean.setQuestionListFlag("true");
		
		//int count=Integer.parseInt(request.getParameter("cnt"));
		/*
		String  sc=bean.getDupQusSubject();
		String [] subcat = sc.split(",");
		
		String qname=bean.getDupQusName();
		String [] questionName = qname.split(",");
		
		String complex = bean.getDupComplexicity();
		String [] complexity=complex.split(",");
		
		String QuesCode = bean.getDupSelectQuCode();
		String [] selectedQuesCode=QuesCode.split(",");
		bean.setCnt(String.valueOf(selectedQuesCode.length));
		
		if(selectedQuesCode!=null)
		{   
			request.setAttribute("addQuestionTotalPages", (selectedQuesCode.length % 4 == 0)?(selectedQuesCode.length/4):(selectedQuesCode.length/4)+1);
			ArrayList<Object> list = new ArrayList<Object>();
			for(int i=0;i<selectedQuesCode.length;i++){
				TestTemplate bean1 = new TestTemplate();
				
				bean1.setSrno(i);
				bean1.setDupQusSubject(subcat[i]);
				bean1.setDupQusName(questionName[i]);
				bean1.setDupComplexicity(complexity[i]);
				bean1.setDupSelectQuCode(selectedQuesCode[i]);						
				list.add(bean1);
				
				
			}
			bean.setAddlist(list);
			
		}
		//else bean.setNotAvailable("true");
*/	}
	
	/**
	 * @Method delete
	 * @Purpose to delete the selected template
	 * @param testtemplate
	 * @return boolean
	**/
	public boolean delete(TestTemplate testtemplate) {
		// TODO Auto-generated method stub
		
		//delete question details from HRMS_REC_TESTTEMPDTL
		boolean result = getSqlModel().singleExecute(getQuery(8), new Object[][]{{testtemplate.getTestTemplateCode()}});
			
		//delete question details from HRMS_REC_TESTTEMP_QUES
		result = getSqlModel().singleExecute(getQuery(9), new Object[][]{{testtemplate.getTestTemplateCode()}});
			
		if(result)
			//delete question details from HRMS_REC_TEST_TEMPLATE
			result = getSqlModel().singleExecute(getQuery(10), new Object[][]{{testtemplate.getTestTemplateCode()}});

		 return result;
		
	}	
	
	/**
	 * @param request 
	 * @Method getCallForEdit
	 * @Purpose to edit the selected template
	 * @param testtemplate
	 * @return boolean
	**/
	
	public void getDetails(TestTemplate bean, HttpServletRequest request)
	{
		Object [][] templateData = getSqlModel().getSingleResult(getQuery(14), new Object [] {bean.getTestTemplateCode()});
		
		if(templateData != null && templateData.length != 0){
			bean.setTestName((checkNull(String.valueOf(templateData[0][0]))));//test name
			bean.setTestDuration((checkNull(String.valueOf(templateData[0][1]))));//test duration
			//bean.setTestType((checkNull(String.valueOf(templateData[0][2]))));
			
			if((templateData[0][2]).equals("Objective"))//test type
			{
				bean.setTestType("Objective");
			}
			else if((templateData[0][2]).equals("Subjective"))bean.setTestType("Subjective");
			else if((templateData[0][2]).equals("Both"))bean.setTestType("Both");
			
				
			if(checkNull(String.valueOf(templateData[0][3])).equals("Y"))
				bean.setEnableScore("Yes");
			else
				bean.setEnableScore("No");
				
			bean.setTestTotalMarks((checkNull(String.valueOf(templateData[0][4]))));
			bean.setTotalNoOfQues((checkNull(String.valueOf(templateData[0][5]))));
			bean.setMarksForNoAns((checkNull(String.valueOf(templateData[0][6]))));
			bean.setPassingMark((checkNull(String.valueOf(templateData[0][7]))));
			bean.setInstructionNotes((checkNull(String.valueOf(templateData[0][8]))));
			bean.setQuestionFrom((checkNull(String.valueOf(templateData[0][26]))));
			bean.setMarksAllocatedForEach(String.valueOf(templateData[0][27])); //marks allocated for each answer
			
			if(checkNull(String.valueOf(templateData[0][9])).equals("Y"))
			{
				bean.setCorrectCheck("true");
				bean.setEqualDistriRadio("");
				bean.setDefineDistriRadio("checked");
				bean.setMarksForHard((checkNull(String.valueOf(templateData[0][10]))));
				bean.setMarksForMedium((checkNull(String.valueOf(templateData[0][11]))));
				bean.setMarksForEasy((checkNull(String.valueOf(templateData[0][12]))));
			} //end of if
			else{
				bean.setCorrectCheck("false");
				bean.setEqualDistriRadio("checked");
				bean.setDefineDistriRadio("");
			} //end of else
			
			bean.setWrongmarksForHard((checkNull(String.valueOf(templateData[0][15]))));
			bean.setWrongmarksForMedium((checkNull(String.valueOf(templateData[0][16]))));
			bean.setWrongmarksForEasy((checkNull(String.valueOf(templateData[0][17]))));
				
			if(bean.getQuestionFrom().equals("add")){
				bean.setAddQuestion("checked");
				bean.setRandomQuestion("");
				bean.setAddQuestionList("true");
			} //end of if
			else{
				bean.setRandomQuestion("checked");
				bean.setAddQuestion("");
				bean.setAddQuestionList("false");
			} //end of else
			bean.setCntRandomEasy(String.valueOf(templateData[0][18]).trim());
			bean.setCntRandomMedium(String.valueOf(templateData[0][19]).trim());
			bean.setCntRandomHard(String.valueOf(templateData[0][20]).trim());
			bean.setTotalRandomnoOfQus(String.valueOf(templateData[0][21]).trim());
			
			bean.setMarkRandomEasy(String.valueOf(templateData[0][22]).trim());
			bean.setMarkRandomMedium(String.valueOf(templateData[0][23]).trim());
			bean.setMarkRandomHard(String.valueOf(templateData[0][24]).trim());
			bean.setTotalRandomMarkss(String.valueOf(templateData[0][25]).trim());
		} //end of if template data not null
		
		System.out.println("Test Type1.........."+bean.getTestType());
		
		
		String responseText = "";
		String responseText1 = "";
		String responseText2 = "";
		String result="";
		
		
		showQuestionDeatils(bean,request);
		
		String query = "SELECT CAT_CODE,CAT_NAME FROM HRMS_REC_CATEGORY WHERE CAT_CODE IN ("
			+bean.getHiddenCategory() + ")";
		
		Object[][] cat = getSqlModel().getSingleResult(query);
		
		
		HashMap<Object, Object> categoryMap = new HashMap<Object, Object>();
		for (int i = 0; i < cat.length; i++) {
		categoryMap.put(cat[i][0],cat[i][1]);
		}
		bean.setCategoryMap(categoryMap);
		
		
		String query1 ="SELECT  SUBJECT_CODE,SUBJECT_NAME FROM HRMS_REC_SUBJECT " ;
				//"WHERE SUBJECT_CODE IN ("+bean.getHiddenSubject()+")";
		
		Object[][] sub = getSqlModel().getSingleResult(query1);
		
		HashMap<Object, Object> subjectMap = new HashMap<Object, Object>();
		
		for (int i = 0; i < sub.length; i++) {
		subjectMap.put(sub[i][0],sub[i][1]);
		}
		bean.setSubjectMap(subjectMap);
		
	}
	
	public void getCallForEdit(TestTemplate bean, HttpServletRequest request){
		logger.info("in viewDetails model ");
		
		logger.info("template code "+bean.getTestTemplateCode() );
		logger.info("checkbox equal "+bean.getEqualWeightage());
		
		//test type
		if(bean.getTestType().equals("Objective"))bean.setTestType("O");
		else if(bean.getTestType().equals("Subjective"))bean.setTestType("S");
		else if(bean.getTestType().equals("Both"))bean.setTestType("B");
		
		//enable score
		if(bean.getEnableScore().equals("Yes"))bean.setEnableScore("true");
		else bean.setEnableScore("false");
		
		bean.setCntRandomEasy(bean.getCntRandomEasy().trim());
		bean.setCntRandomMedium(bean.getCntRandomMedium().trim());
		bean.setCntRandomHard(bean.getCntRandomHard().trim());
		bean.setTotalRandomnoOfQus(bean.getTotalRandomnoOfQus().trim());
		
		bean.setMarkRandomEasy(bean.getMarkRandomEasy().trim());
		bean.setMarkRandomMedium(bean.getMarkRandomMedium().trim());
		bean.setMarkRandomHard(bean.getMarkRandomHard().trim());
		bean.setTotalRandomMarkss(bean.getTotalRandomMarkss().trim());
		
		bean.setHiddenCategory(bean.getHiddenCategory());
		bean.setHiddenSubject(bean.getHiddenSubject());
		
		if(bean.getQuestionFrom().equals("random")){
			logger.info("in random ");
			setQuestionList(bean, request);	//set question list
		} //end of if
		else{
			setAddList(bean, request);	//set the list of the questions
		} //end of else
	} //end of getCallForEdit method
	
	
	
	/**
	 * @Method updateData
	 * @Purpose to update the selected template
	 * @param testtemplate
	 * @param request
	 * @return String
	**/
	public String updateData(TestTemplate testTemplate, HttpServletRequest request){
		logger.info("in update data ");
		
		String flag = "";
		
		if(isExists(testTemplate)) flag = "duplicate";
		else{
			boolean result = false;
			Object[][] templateData = new Object[1][29];
			
			templateData[0][0] = testTemplate.getTestName();		//TEMPLATE TEST NAME
			templateData[0][1] = testTemplate.getTestDuration();	//TEMPLATE DURATION
			templateData[0][2] = testTemplate.getTestType();		//TEMPLATE TEST TYPE
			
			if(testTemplate.getEnableScore().equals("true"))templateData[0][3] = "Y";	//enable score
			else templateData[0][3] = "N";	//enable score
			
			templateData[0][4] = testTemplate.getTestTotalMarks();//total marks
			templateData[0][5] = testTemplate.getTotalNoOfQues();	//total no of questions
			
			templateData[0][6] = "N";	//equal weightage
			
			if(testTemplate.getCorrectCheck().equals("true")){
				templateData[0][7] = "Y";	//marks for correct answer
				templateData[0][8]  = testTemplate.getMarksForHard();	//marks for hard
				templateData[0][9]  = testTemplate.getMarksForMedium(); //marks for medium
				templateData[0][10] = testTemplate.getMarksForEasy();	//marks for easy
			}
			else{
				templateData[0][7] = "N";	//marks for correct answer
				templateData[0][8]  = "";	//marks for hard
				templateData[0][9]  = ""; //marks for medium
				templateData[0][10] = "";	//marks for easy
			}
			
				templateData[0][11] = "Y";	//marks for wrong answer
				templateData[0][12]  = testTemplate.getWrongmarksForHard();	// wrong marks for hard
				templateData[0][13]  = testTemplate.getWrongmarksForMedium(); //wrong marks for medium
				templateData[0][14] = testTemplate.getWrongmarksForEasy();	//wrong marks for easy
			
			templateData[0][15] = testTemplate.getMarksForNoAns();	//marks for no answer
			templateData[0][16] = testTemplate.getPassingMark();		//passing marks
			templateData[0][17] = testTemplate.getInstructionNotes();	//instruction notes
			
			if(testTemplate.getQuestionFrom().equals("add")){
				testTemplate.setAddQuestion("checked");
				testTemplate.setRandomQuestion("");
				testTemplate.setAddQuestionList("true");
			} //end of if
			else{
				testTemplate.setRandomQuestion("checked");
				testTemplate.setAddQuestion("");
				testTemplate.setAddQuestionList("false");
			} //end of else
			templateData[0][18] = testTemplate.getCntRandomEasy();	//easy count
			templateData[0][19] = testTemplate.getCntRandomMedium();//midium count	
			templateData[0][20] = testTemplate.getCntRandomHard();//hard count	
			templateData[0][21] = testTemplate.getTotalRandomnoOfQus(); //total no of questions
			
			templateData[0][22] = testTemplate.getMarkRandomEasy();	//easy count
			templateData[0][23] = testTemplate.getMarkRandomMedium();//medium count	
			templateData[0][24] = testTemplate.getMarkRandomHard();//hard count	
			templateData[0][25] = testTemplate.getTotalRandomMarkss(); //total no of questions
			
			templateData[0][26] = testTemplate.getQuestionFrom();
			templateData[0][27] = testTemplate.getMarksAllocatedForEach();//marks allocated for each answer
			templateData[0][28] = testTemplate.getTestTemplateCode();//template code
			
			result = getSqlModel().singleExecute(getQuery(7), templateData);
			
			if(result){
				//delete question details from HRMS_REC_TESTTEMP_QUES
				String deleteAddedQues = "DELETE FROM HRMS_REC_TESTTEMP_QUES "
										 +"WHERE TEMPLATE_CODE = "+testTemplate.getTestTemplateCode();
				
				result = getSqlModel().singleExecute(deleteAddedQues);
				
				//delete question details from HRMS_REC_TESTTEMPDTL
				String deleteRandomQues = "DELETE FROM HRMS_REC_TESTTEMPDTL "
					 					  +"WHERE TEMPLATE_CODE = "+testTemplate.getTestTemplateCode();
				
				result = getSqlModel().singleExecute(deleteRandomQues);
				
				if(result){
					result = insertQuestionDetails(testTemplate, request);
					
					if(result)flag = "updated";
					else flag = "error";
				}
				else flag = "error";
			}
			else flag = "error";
		}
		
		return flag;
	}
	
	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result value of the data
	 * @return String
	**/
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if
		else {
			return result;
		}	//end of else
	}

	/**
	 * @Method selectRandomQuestions
	 * @Purpose to select random questions from question bank
	 * @param testtemplate
	**/
	public void selectRandomQuestions(TestTemplate bean) {
		// TODO Auto-generated method stub
		logger.info("in selectRandomQuestions model "+bean.getSubject());
		
		ArrayList<Object> questionList = new ArrayList<Object>();
		
		bean.setHiddenSubject(bean.getSubject());	//hidden subject
		bean.setHiddenCategory(bean.getCategory()); //hidden category
		
		//select question as per the selected subjects and test type
		String query =  "SELECT SUBJECT_NAME, CAT_NAME, DECODE(QUES_LEVEL, 'H', 'Hard', 'M', 'Medium', 'E', 'Easy'), COUNT(*), "
						+"QUES_SUB_CODE, QUES_CAT_CODE, QUES_LEVEL "
						+"FROM HRMS_REC_QUESBANK " 
						+"LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE = QUES_SUB_CODE) " 
						+"LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = QUES_CAT_CODE) " 
						+"WHERE QUES_SUB_CODE IN ("+bean.getSubject()+") ";
		if(bean.getTestType().equals("Both") || bean.getTestType().equals("B")){
			query += "AND QUES_TYPE IN ('O', 'S') ";
		}
		else if(bean.getTestType().equals("Objective") || bean.getTestType().equals("O")){
			query += "AND QUES_TYPE = 'O' ";
		}
		else query += "AND QUES_TYPE = 'S' ";
		
		if(bean.getCategory()!=null && !bean.getCategory().equals("")){
			   query += " AND QUES_CAT_CODE IN ("+bean.getCategory()+")";
		} //end of if
		
		query += "GROUP BY QUES_SUB_CODE, QUES_CAT_CODE, QUES_LEVEL, SUBJECT_NAME, CAT_NAME, "
				 +"DECODE(QUES_LEVEL, 'H', 'Hard', 'M', 'Medium', 'E', 'Easy') "
				 +"ORDER BY QUES_SUB_CODE, QUES_CAT_CODE, QUES_LEVEL";
		
		logger.info("subject code "+bean.getSubject());
		Object [][] getQuestions = getSqlModel().getSingleResult(query);
		
		//int cntEasy=0;
		//int cntHard=0;
		//int cntMedium=0;
		int MarkEasy = 0;
		int MarkMedium = 0;
		int MarkHard = 0;
		int totalnoOfQus = 0;
		int totalMarkss = 0;
		
		if(getQuestions != null && getQuestions.length != 0){
			for (int i = 0; i < getQuestions.length; i++) {
				TestTemplate bean1 = new TestTemplate();
				
				if(!checkNull(String.valueOf(getQuestions[i][1])).equals("")){
					logger.info("in if");
					bean1.setSubjectCategory(checkNull(String.valueOf(getQuestions[i][0]))
							+" --> "+checkNull(String.valueOf(getQuestions[i][1]))); //sublect-category
				}
				else{
					bean1.setSubjectCategory(checkNull(String.valueOf(getQuestions[i][0]))); //subject
				}
				bean1.setRandomSubCode(checkNull(String.valueOf(getQuestions[i][4])));	//random subject code
				bean1.setRandomCatCode(checkNull(String.valueOf(getQuestions[i][5])));	//random category code
				bean1.setRandomComplexicity(checkNull(String.valueOf(getQuestions[i][2])));//complexicity
				bean1.setComplexicityCode(checkNull(String.valueOf(getQuestions[i][6]).trim()));//complexicity code
				bean1.setAvailableQuestion( checkNull(String.valueOf(getQuestions[i][3])));//available no of questions
				questionList.add(bean1);
			} //end of loop
				
			bean.setQuestionList(questionList);	
			bean.setNotAvailable("false");
			} //end of if
			else{
				bean.setNotAvailable("true");
			} //end of else
			bean.setRecCount(String.valueOf(questionList.size()));
			logger.info("check Flag.**********"+bean.getCorrectCheck());
			logger.info("bean.getRadioOption()**********"+bean.getRadioOption());
			if(bean.getRadioOption().equals("Equal")){
				bean.setEqualDistriRadio("checked");
				bean.setDefineDistriRadio("");
				bean.setCorrectCheck("false");
			} //end of if
			else{
				bean.setDefineDistriRadio("checked");
				bean.setEqualDistriRadio("");
				bean.setCorrectCheck("true");
			} //end of else
	} //end of selectRandomQuestions method
	
	/**
	 * @Method setQuestionList
	 * @Purpose to set the selected questions in the list
	 * @param testtemplate
	 * @param request
	**/
	public void setQuestionList(TestTemplate bean, HttpServletRequest request){
		logger.info("in setQuestionList model ");
		
		bean.setQuestionListFlag("true");
		
		String [] subjectCategory    = request.getParameterValues("subjectCategory");	//subject-category
		String [] subjectCode        = request.getParameterValues("randomSubCode");		//subject code
		String [] categoryCode       = request.getParameterValues("randomCatCode");		//category code
		String [] noOfQuestion       = request.getParameterValues("testQuestion");		//no of questions
		String [] complexicity       = request.getParameterValues("complexicityCode");	//complexicity code
		String [] RandomComplexicity = request.getParameterValues("RandomComplexicity");//complexicity
		String [] availableQuestion  = request.getParameterValues("availableQuestion");	//available no of questions
		
		if(subjectCategory != null && subjectCategory.length != 0){
			request.setAttribute("addQuestionTotalPages", (subjectCategory.length % scriptPageNo == 0)?(subjectCategory.length/scriptPageNo):(subjectCategory.length/scriptPageNo)+1);
			ArrayList<Object> questionList = new ArrayList<Object>();
			for (int i = 0; i < subjectCategory.length; i++) {
				TestTemplate bean1 = new TestTemplate();
				bean1.setSubjectCategory(subjectCategory[i]); //subject-category
				bean1.setRandomSubCode(subjectCode[i]);			//random subject code
				bean1.setRandomCatCode(categoryCode[i]);		//random category code
				bean1.setRandomComplexicity(RandomComplexicity[i]);//complexicity
				bean1.setComplexicityCode(complexicity[i]);//complexicity code
				bean1.setAvailableQuestion(availableQuestion[i]);//available no of questions
				bean1.setTestQuestion(noOfQuestion[i]);//questions in test
				
				questionList.add(bean1);
			}
			bean.setQuestionList(questionList);
			
		}
		else bean.setNotAvailable("true");
	}
	
	/**
	 * @Method insertQuestionDetails
	 * @Purpose to insert the selected question details
	 * @param testtemplate
	 * @param request
	 * @return boolean
	**/
	public boolean insertQuestionDetails(TestTemplate bean, HttpServletRequest request){
		logger.info("in insertQuestionDetails ");
		
		boolean result = false;
		
		if(bean.getTestTemplateCode().equals("")){
			//select max template code from HRMS_REC_TEST_TEMPLATE
			String query = "SELECT MAX(TEMPLATE_CODE) FROM HRMS_REC_TEST_TEMPLATE";
			
			Object [][] tempCode = getSqlModel().getSingleResult(query);
			
			if(tempCode != null && tempCode.length != 0){
				bean.setTestTemplateCode(checkNull(String.valueOf(tempCode[0][0])));
			} //end of if
			//if questions to be added from question bank
			if(bean.getQuestionFrom().equals("add")){
				bean.setHiddenSubject(bean.getSubject());	//hidden subject
				bean.setHiddenCategory(bean.getCategory());	//hidden category code
			} //end of if
		} //end of if
		
		//if questions to be added from question bank
		if(bean.getQuestionFrom().equals("add")){
			bean.setQuestionListFlag("false");
			bean.setAddQuestion("checked");
			bean.setRandomQuestion("");
			bean.setAddQuestionList("true");
			Object[][] quesData = new Object[1][5];
			
			quesData [0][0] = bean.getTestTemplateCode();	//template code
			quesData [0][1] = bean.getDupSelectQuCode();		//question code
			quesData [0][2] = bean.getSubject();		//subject
			quesData [0][3] = bean.getCategory();				//category
			quesData [0][4] = bean.getCompLevel();			//complexicity
			
			//insert question details in HRMS_REC_TESTTEMP_QUES
			result = getSqlModel().singleExecute(getQuery(5), quesData);
		} //end of if
		else{ //if questions to be selected randomly
			bean.setQuestionListFlag("true");
			bean.setRandomQuestion("checked");
			bean.setAddQuestion("");
			bean.setAddQuestionList("false");
			if(bean.getHiddenCategory().equals(""))
					bean.setHiddenCategory(bean.getCategory());	//hidden category
			
			String [] subjectCode  = request.getParameterValues("randomSubCode");	//subject code
			String [] categoryCode = request.getParameterValues("randomCatCode");	//category code
			String [] noOfQuestion = request.getParameterValues("testQuestion");	//no of questions in test	
			String [] complexicity = request.getParameterValues("complexicityCode");	//complexcity
			
			if(subjectCode != null && subjectCode.length != 0){
				for (int i = 0; i < subjectCode.length; i++) {
					Object [][] randomQuesData = new Object [1][5];
					
					randomQuesData [0][0] = bean.getTestTemplateCode();
					randomQuesData [0][1] = subjectCode[i];
					randomQuesData [0][2] = categoryCode[i];
					
					if(noOfQuestion[i].equals(""))randomQuesData [0][3] = "0";
					else randomQuesData [0][3] = noOfQuestion[i];
					
					randomQuesData [0][4] = complexicity[i];
					
					//insert question details in HRMS_REC_TESTTEMPDTL
					result = getSqlModel().singleExecute(getQuery(6), randomQuesData);
				} //end of loop
			} //end of if
		} //end of else
		return result;
	} //end of insertQuestionDetails method

	/**
	 * @Method searchRecord
	 * @Purpose to display the details of the selected template
	 * @param bean
	 * @param request 
	**/
	public void searchRecord(TestTemplate bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Object [][] templateData = getSqlModel().getSingleResult(getQuery(11), new Object [] {bean.getTestTemplateCode()});
		
		if(templateData != null && templateData.length != 0){
			//enable score
			if(checkNull(String.valueOf(templateData[0][0])).equals("Y"))
				bean.setEnableScore("Yes");
			else
				bean.setEnableScore("No");
			
			//correct check
			if(checkNull(String.valueOf(templateData[0][2])).equals("Y")){
				bean.setCorrectCheck("true");
				bean.setDefineDistriRadio("checked");
				bean.setEqualDistriRadio("");
			} //end of if
				
			else{
				bean.setCorrectCheck("false");
				bean.setDefineDistriRadio("");
				bean.setEqualDistriRadio("checked");
			} //end of else
				
			//marks for wrong
			bean.setMarksForNoAns(checkNull(String.valueOf(templateData[0][4])));//marks for no answer
			bean.setInstructionNotes(checkNull(String.valueOf(templateData[0][5])));//instruction notes
			bean.setMarksForHard(checkNull(String.valueOf(templateData[0][6])));//marks for hard question
			bean.setMarksForMedium(checkNull(String.valueOf(templateData[0][7])));//marks for medium question
			bean.setMarksForEasy(checkNull(String.valueOf(templateData[0][8])));//marks for easy question
			
			bean.setWrongmarksForHard(checkNull(String.valueOf(templateData[0][9])));//marks for hard question
			bean.setWrongmarksForMedium(checkNull(String.valueOf(templateData[0][10])));//marks for medium question
			bean.setWrongmarksForEasy(checkNull(String.valueOf(templateData[0][11])));//marks for easy question
			bean.setQuestionFrom(checkNull(String.valueOf(templateData[0][20])));
			bean.setMarksAllocatedForEach(String.valueOf(templateData[0][21]));//marks allocated for each
			
			if(bean.getQuestionFrom().equals("add")){
				bean.setAddQuestion("checked");
				bean.setRandomQuestion("");
				bean.setAddQuestionList("true");
				bean.setCntEasy(String.valueOf(templateData[0][12]));
				bean.setCntHard(String.valueOf(templateData[0][13]));
				bean.setCntMedium(String.valueOf(templateData[0][14]));
				bean.setTotalnoOfQus(String.valueOf(templateData[0][15]));
				
				bean.setMarkEasy(String.valueOf(templateData[0][16]));			
				bean.setMarkHard(String.valueOf(templateData[0][17]));
				bean.setMarkMedium(String.valueOf(templateData[0][18]));
				bean.setTotalMarkss(String.valueOf(templateData[0][19]));
			}else{
				bean.setRandomQuestion("checked");
				bean.setAddQuestion("");
				bean.setAddQuestionList("false");
				bean.setCntRandomEasy(String.valueOf(templateData[0][12]));
				bean.setCntRandomMedium(String.valueOf(templateData[0][13]));
				bean.setCntRandomHard(String.valueOf(templateData[0][14]));
				bean.setTotalRandomnoOfQus(String.valueOf(templateData[0][15]));
				
				bean.setMarkRandomEasy(String.valueOf(templateData[0][16]));
				bean.setMarkRandomMedium(String.valueOf(templateData[0][17]));
				bean.setMarkRandomHard(String.valueOf(templateData[0][18]));
				bean.setTotalRandomMarkss(String.valueOf(templateData[0][19]));
			}
			showQuestionDeatils(bean,request);
			bean.setNotAvailable("false");
		} //end of if
		else{
			bean.setNotAvailable("true");
		} //end of else
	}
	
	/**
	 * @Method showQuestionDeatils
	 * @Purpose to display the question details
	 * @param bean
	 * @param request 
	**/
	public void showQuestionDeatils(TestTemplate bean, HttpServletRequest request){
		logger.info("in showQuestionDeatils ");
		
		System.out.println("Test Type2......"+bean.getTestType());
		//select the question details
		Object [][] addQuesDetails = getSqlModel().getSingleResult(getQuery(12), new Object[] {bean.getTestTemplateCode()});
		
		if(addQuesDetails != null && addQuesDetails.length != 0){
			String selectQues = "SELECT QUES_NAME FROM HRMS_REC_QUESBANK "
								+"WHERE QUES_CODE IN ("+checkNull(String.valueOf(addQuesDetails[0][0]))+")";
			
			Object [][] quesData = getSqlModel().getSingleResult(selectQues);
			
			if(quesData != null && quesData.length != 0){
				String question = "";
				for (int i = 0; i < quesData.length; i++) {
					question += (i + 1) + ". "+String.valueOf(quesData[i][0]) + "\n ";
				}
				bean.setQuesName(question);
				bean.setSelectQuesCode(checkNull(String.valueOf(addQuesDetails[0][0])));
			}
			bean.setQuestionFrom("add");
			
			Object [][] addQueDetails = getSqlModel().getSingleResult(getQuery(15), new Object[] {bean.getTestTemplateCode()});
			if(addQueDetails != null && addQueDetails.length != 0){
				bean.setAddToListQuestions(String.valueOf(addQueDetails[0][0])+",");
				bean.setQuestionListFlag("false");
				String s[] = String.valueOf(addQueDetails[0][0]).split(",");
				request.setAttribute("addQuestionTotalPages", (s.length % scriptPageNo == 0)?(s.length/scriptPageNo):(s.length/scriptPageNo)+1);
				bean.setCnt(String.valueOf(s.length));
				ArrayList<Object> questionList = new ArrayList<Object>();
				
				for(int i=0;i<s.length;i++){
				TestTemplate bean1 = new TestTemplate();
				String queryAdd="SELECT QUES_SUB_CODE,QUES_CODE,QUES_TYPE,DECODE(QUES_LEVEL,'H', 'Hard', 'M', 'Medium', 'E', 'Easy'),QUES_NAME,QUES_CAT_CODE FROM HRMS_REC_QUESBANK"+
								" where (HRMS_REC_QUESBANK.QUES_CODE="+s[i]+")";
				
				
				Object [][] queslist = getSqlModel().getSingleResult(queryAdd);
				
				String subname="SELECT SUBJECT_NAME FROM HRMS_REC_SUBJECT WHERE SUBJECT_CODE IN ("+queslist[0][0]+")ORDER BY SUBJECT_NAME";
				
				Object [][] sublist = getSqlModel().getSingleResult(subname);
				
				String catname="SELECT CAT_NAME FROM HRMS_REC_CATEGORY "+
						  	" WHERE CAT_CODE IN (" +queslist[0][5]+ ") ORDER BY CAT_NAME";
				
				Object [][] catlist = getSqlModel().getSingleResult(catname);
				
				
				bean1.setSrno(i);
				//bean1.setDupQusSubject(queslist[0][0].toString());
				bean1.setDupQusSubject(checkNull(String.valueOf(sublist[0][0]))
						+"-->"+checkNull(String.valueOf(catlist[0][0]))); 
				bean1.setDupQusName(queslist[0][4].toString());
				bean1.setDupComplexicity(queslist[0][3].toString());
				bean1.setDupSelectQuCode(queslist[0][1].toString());														
				questionList.add(bean1);
				
				}
				bean.setAddlist(questionList);
			}
			
			showSubjectCategory(bean, String.valueOf(addQuesDetails[0][1]),  String.valueOf(addQuesDetails[0][2]));
			bean.setNotAvailable("false");
		} //end of else
		else{
			bean.setQuestionListFlag("true");
			
			Object [][] randomQuesDetails = getSqlModel().getSingleResult(getQuery(13), new Object[] {bean.getTestTemplateCode()});
			
			if(randomQuesDetails != null && randomQuesDetails.length != 0){
				bean.setQuestionFrom("random");
				String subjectCode = "0";
				String categoryCode = "0";
				ArrayList<Object> questionList = new ArrayList<Object>();
				
				for (int i = 0; i < randomQuesDetails.length; i++) {
					TestTemplate bean1 = new TestTemplate();
					
					if(!checkNull(String.valueOf(randomQuesDetails[i][1])).equals("")){
						bean1.setSubjectCategory(checkNull(String.valueOf(randomQuesDetails[i][0]))
								+"-->"+checkNull(String.valueOf(randomQuesDetails[i][1]))); //sublect-category
					}
					else{
						bean1.setSubjectCategory(checkNull(String.valueOf(randomQuesDetails[i][0]))); //sublect-category
					}
					
					bean1.setRandomSubCode(checkNull(String.valueOf(randomQuesDetails[i][2])));//subject code
					bean1.setRandomCatCode(checkNull(String.valueOf(randomQuesDetails[i][3])));//category code
					bean1.setRandomComplexicity(checkNull(String.valueOf(randomQuesDetails[i][5])));//complexicity
					
					if(checkNull(String.valueOf(randomQuesDetails[i][5])).equals("Hard")){
						bean1.setComplexicityCode("H");
					}
					else if(checkNull(String.valueOf(randomQuesDetails[i][5])).equals("Medium")){
						bean1.setComplexicityCode("M");
					}
					else if(checkNull(String.valueOf(randomQuesDetails[i][5])).equals("Easy")){
						bean1.setComplexicityCode("E");
					}
					
					String query = "SELECT COUNT(*) FROM HRMS_REC_QUESBANK "
								   +"WHERE QUES_SUB_CODE = "+checkNull(String.valueOf(randomQuesDetails[i][2]))
								   +" AND QUES_CAT_CODE="+checkNull(String.valueOf(randomQuesDetails[i][3]))+""
								   +"AND QUES_LEVEL = '"+bean1.getComplexicityCode()+"' ";
					
					System.out.println("Test Type3......"+bean.getTestType());
					
					if(bean.getTestType().equals("Both") || bean.getTestType().equals("B")){
						query += "AND QUES_TYPE IN ('O', 'S') ";
					}
					else if(bean.getTestType().equals("Objective") || bean.getTestType().equals("O")){
						query += "AND QUES_TYPE = 'O' ";
					}
					else query += "AND QUES_TYPE = 'S' ";
					
					Object [][] noOfQues = getSqlModel().getSingleResult(query);
					
					if(noOfQues != null && noOfQues.length != 0)
						bean1.setAvailableQuestion(checkNull(String.valueOf(noOfQues[0][0])));
					else	bean1.setAvailableQuestion("0");
					
					bean1.setTestQuestion(checkNull(String.valueOf(randomQuesDetails[i][4])));
					
					questionList.add(bean1);
					
					subjectCode += checkNull(String.valueOf(randomQuesDetails[i][2]))+",";
					categoryCode += checkNull(String.valueOf(randomQuesDetails[i][3]))+",";
				}
				bean.setQuestionList(questionList);
				
				subjectCode = subjectCode.substring(0, subjectCode.length()-1);
				categoryCode = categoryCode.substring(0, categoryCode.length()-1);
				
				showSubjectCategory(bean, subjectCode, categoryCode);
				bean.setNotAvailable("false");
			} //end of if
			else{
				bean.setNotAvailable("true");
				bean.setSubjectFlag("");
				bean.setCategoryFlag("");
			} //end of else
		} //end of else
	} //end of showQuestionDeatils method
	
	/**
	 * @Method showSubjectCategory
	 * @Purpose to display the subject-category
	 * @param bean
	**/
	public void showSubjectCategory(TestTemplate bean, String subjectCode, String categoryCode){
		
		try {
			System.out.println("subjectCode............."+subjectCode);
			String quesQuery = "SELECT SUBJECT_NAME FROM HRMS_REC_SUBJECT "					   
				   				+"WHERE SUBJECT_CODE IN ("+subjectCode+") ORDER BY SUBJECT_NAME";

			Object [][] subjectData = getSqlModel().getSingleResult(quesQuery);
			
			if(subjectData != null && subjectData.length != 0){
				String subject = "";
				for (int i = 0; i < subjectData.length; i++) {
					subject += (i+1)+". "+String.valueOf(subjectData[i][0])+"\n ";
				}
				bean.setSCode(subject);
				}
			else bean.setSubjectFlag("");
		} catch (RuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
		String catQuery = "SELECT CAT_NAME FROM HRMS_REC_CATEGORY "
						  +"WHERE CAT_CODE IN (" +categoryCode+ ") ORDER BY CAT_NAME";
		
		Object[][] categoryData = getSqlModel().getSingleResult(catQuery);
		
		if (categoryData != null && categoryData.length != 0) {
			String category = "";
			for (int i = 0; i < categoryData.length; i++) {
				category += (i + 1) + ". "+String.valueOf(categoryData[i][0]) + "\n ";
			}
			bean.setCCode(category);
		}
		else bean.setCategoryFlag("");
		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}
		bean.setHiddenCategory(categoryCode);
		bean.setHiddenSubject(subjectCode);
	}

	public Object[][] getCategory(TestTemplate bean) {
		
		Object[][] categories = null;
		
		try {
			String query = "SELECT CAT_CODE,CAT_NAME FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE IN ("
					+ bean.getHidAjaxSubject() + ") ORDER BY CAT_NAME ";
			categories = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in categories query",e);
		} //end of catch
		
		return categories;
	} //end of method
	
	
	public void deleteOption(TestTemplate testTemplate, String[] subcat,
			String[] questionName, String[] complexity, String[] selectedQuesCode
			){
		try{
		ArrayList<Object> addlist1=new ArrayList<Object>();
		
	
		String ansVar="";
		String optionVar="";
		String rownum;
		rownum=testTemplate.getParaId();
		if(selectedQuesCode!=null && subcat.length>0)
		{   
	        
	         int j=0;
	      
	         
	         
			for(int i=0;i<selectedQuesCode.length;i++){				
				TestTemplate bean=new TestTemplate();				
				if((i!= Integer.parseInt(testTemplate.getParaId()))){					
					bean.setSrno(j);
					bean.setDupQusSubject(subcat[i]);
					bean.setDupQusName(questionName[i]);
					bean.setDupComplexicity(complexity[i]);
					bean.setDupSelectQuCode(selectedQuesCode[i]);	
					addlist1.add(bean);
					
					}
				j++;
			}
			testTemplate.setCnt(String.valueOf(addlist1.size()));
		}
		System.out.println("addlist......"+addlist1.size());
		
		testTemplate.setAddlist(addlist1);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
		
	}

	/**
	 * this method is called to add question through question bank...
	 * @param request 
	 * @param testTemplate
	 */
	public void addQuestionFromBank(TestTemplate bean, HttpServletRequest request) {
		String questionCodes = bean.getAddToListQuestions();
		questionCodes = questionCodes.substring(0, questionCodes.length() - 1);
		Object [][] questionData = null;
		try {
			String query = "SELECT QUES_CODE, QUES_NAME, QUES_SUB_CODE, SUBJECT_NAME, QUES_TYPE, QUES_CAT_CODE, "
					+ "CAT_NAME, DECODE(QUES_LEVEL, 'H', 'Hard', 'M', 'Medium', 'E', 'Easy') "
					+ "FROM HRMS_REC_QUESBANK "
					+ "LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE = QUES_SUB_CODE) "
					+ "LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = QUES_CAT_CODE) "
					+ "WHERE QUES_SUB_CODE IN (" + bean.getSubject() + ")";
			logger.info("template cat......." + bean.getCategory());
			if (bean.getCategory() != null && !bean.getCategory().equals("")) {

				query += " AND QUES_CAT_CODE IN (" + bean.getCategory() + ")";
			} //end of if
			if(bean.getTestType().equals("Both") || bean.getTestType().equals("B")){
				query += "AND QUES_TYPE IN ('O', 'S') ";
			}
			else if(bean.getTestType().equals("Objective") || bean.getTestType().equals("O")){
				query += "AND QUES_TYPE = 'O' ";
			}
			else query += "AND QUES_TYPE = 'S' ";
			if (!bean.getCompLevel1().equals("")) {
				query += "AND QUES_LEVEL IN (" + bean.getCompLevel1() + ") ";
			} //end of if
			query += "AND QUES_CODE IN (" + questionCodes
					+ ") ORDER BY QUES_SUB_CODE, QUES_CAT_CODE, QUES_LEVEL";
			questionData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exceptions in add question query", e);
		} //end of catch
		
		if(questionData !=null && questionData.length >0){
			
			request.setAttribute("addQuestionTotalPages", (questionData.length % scriptPageNo == 0)?(questionData.length/scriptPageNo):(questionData.length/scriptPageNo)+1);
			
			ArrayList<Object> list=new ArrayList<Object>();
			for (int i = 0; i < questionData.length; i++) {
				TestTemplate bean1=new TestTemplate();
				bean1.setSrno(i);
				bean1.setDupQusSubject(String.valueOf(questionData[i][3]));
				bean1.setDupQusName(String.valueOf(questionData[i][1]));
				bean1.setDupComplexicity(String.valueOf(questionData[i][7]));
				bean1.setDupSelectQuCode(String.valueOf(questionData[i][0]));						
				list.add(bean1);
				
			} //end of loop
			bean.setAddlist(list);
			bean.setNotAvailable("false");
			
			if(bean.getCorrectCheck().equals("false")){
				bean.setEqualDistriRadio("checked");
				bean.setDefineDistriRadio("");
				//bean.setCorrectCheck("false");
			} //end of if
			else{
				bean.setEqualDistriRadio("");
				bean.setDefineDistriRadio("checked");
			} //end of else
			
			
		} //end of if
	} //end of addQuestionFromBank method
	public void callAddList(TestTemplate bean, HttpServletRequest request)
	{
		String [] dupQusSubject =request.getParameterValues("dupQusSubject");
		String [] dupQusName =request.getParameterValues("dupQusName");
		String [] dupSelectQuCode =request.getParameterValues("dupSelectQuCode");
		String [] dupComplexicity =request.getParameterValues("dupComplexicity");
		
		ArrayList questionList = new ArrayList();
		if(dupQusSubject!=null && dupQusSubject.length>0)
		{ 
			int j=1;			
	     for(int i=0;i<dupQusSubject.length;i++)
		 {
		TestTemplate bean1= new TestTemplate();
		bean1.setSrno(j);
		//bean1.setDupQusSubject(queslist[0][0].toString());
		bean1.setDupQusSubject(checkNull(String.valueOf(dupQusSubject[i])));
				
		bean1.setDupQusName(checkNull(String.valueOf(dupQusName[i])));
		bean1.setDupComplexicity(checkNull(String.valueOf(dupComplexicity[i])));
		bean1.setDupSelectQuCode(checkNull(String.valueOf(dupSelectQuCode[i])));													
		questionList.add(bean1);
		j++;
			}
		
		}
		bean.setAddlist(questionList);
	}
} //end of class
