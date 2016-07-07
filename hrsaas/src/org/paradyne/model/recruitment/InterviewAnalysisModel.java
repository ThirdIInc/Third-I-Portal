package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.InterviewAnalysis;
import org.paradyne.bean.Recruitment.TestInterviewReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class InterviewAnalysisModel  extends ModelBase{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	
	
	
	
/**
 *  This method  is used to display the record in view mode
 * @param intAnalysis
 * @param request
 */
public void callJspView(InterviewAnalysis intAnalysis,
			HttpServletRequest request) {
		
		// TODO Auto-generated method stub
		
		String  viewQuery="SELECT  distinct CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
						+"	EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,NVL(EVAL_ROUND_TYPE,' '),"
						+" EVAL_MAKE_OFFER,EVAL_FWD_NEXTROU,EVAL_SCORE, EVAL_PERCENTAGE,DECODE(EVAL_INT_STATUS ,'S','Selected','R','Rejected','O','On Hold'),CAND_CODE FROM HRMS_REC_CANDEVAL"
						+"	LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CANDEVAL.EVAL_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
						+"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_CANDEVAL.EVAL_INT_EMPID )"
						+"	LEFT JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_CANDEVAL.EVAL_REQS_CODE AND HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_CANDEVAL.EVAL_CAND_CODE) "
						+" inner join   HRMS_REC_CANDEVAL_DTL on(HRMS_REC_CANDEVAL_DTL.EVAL_CODE=HRMS_REC_CANDEVAL.EVAL_CODE)"
						+"	WHERE 1=1";
		if (!(intAnalysis.getCandCode().equals("") || intAnalysis.getCandCode().equals("null"))) {
			viewQuery += " AND CAND_CODE=" + intAnalysis.getCandCode();
		}
		if (!(intAnalysis.getIntrvCode().equals("") || intAnalysis.getIntrvCode().equals("null"))) {
			viewQuery += " AND EVAL_INT_EMPID=" + intAnalysis.getIntrvCode();
		}
		if (!(intAnalysis.getInterviewstatus().equals("") || intAnalysis.getInterviewstatus().equals("null"))) {
			viewQuery += " AND EVAL_INT_STATUS='"+intAnalysis.getInterviewstatus()+"'";
		}
		
		if (intAnalysis.getIntRound().equals("E")) {
		if (!(intAnalysis.getInterviewRound().equals(""))&& !intAnalysis.getInterviewRound().equals("null")) {
			viewQuery += " AND EVAL_ROUND_TYPE='"+intAnalysis.getInterviewRound()+"'";
		}
		}
		
		if (intAnalysis.getIntRound().equals("L")) {
		if (!(intAnalysis.getInterviewRound().equals(""))&& !intAnalysis.getInterviewRound().equals("null")) {
			viewQuery += " AND EVAL_ROUND_TYPE < '"+intAnalysis.getInterviewRound()+"'";
		}
		}
		
		if (intAnalysis.getIntRound().equals("G")) {
			if (!(intAnalysis.getInterviewRound().equals(""))&& ! intAnalysis.getInterviewRound().equals("null")) {
			viewQuery += " AND EVAL_ROUND_TYPE > '"+intAnalysis.getInterviewRound()+"'";
		}
		}
		
		if (intAnalysis.getIntRound().equals("LE")) {
			if (!(intAnalysis.getInterviewRound().equals(""))&& !intAnalysis.getInterviewRound().equals("null")) {
			viewQuery += " AND EVAL_ROUND_TYPE <= '"+intAnalysis.getInterviewRound()+"'";
		}
		}
		if (intAnalysis.getIntRound().equals("GE")) {
			if (!(intAnalysis.getInterviewRound().equals(""))&& !intAnalysis.getInterviewRound().equals("null")) {
			viewQuery += " AND EVAL_ROUND_TYPE >= '"+intAnalysis.getInterviewRound()+"'";
		}
		}
		
		if(!intAnalysis.getSortByInt().equals("") || ! intAnalysis.getThenBYInt().equals("") || ! intAnalysis.getSecondByInt().equals(""))
		  {
			viewQuery+=" ORDER BY ";
		  }
		  if(!intAnalysis.getSortByInt().equals(""))
		  {
			  logger.info("bean.getSortByInt()   -----------> "+intAnalysis.getSortByInt());
			  viewQuery+= getSortVal(intAnalysis.getSortByInt())+" "+getSortOrder(intAnalysis.getRadioOneInt());
			  if(!intAnalysis.getThenBYInt().equals("")|| ! intAnalysis.getSecondByInt().equals("")){ 
				  viewQuery+=" , ";
			  }
		  }
		  
		  if(!intAnalysis.getThenBYInt().equals(""))
		  {
			  viewQuery+= getSortVal(intAnalysis.getThenBYInt())+" "+ getSortOrder(intAnalysis.getRadioTwoInt()); 
			  if(!intAnalysis.getSecondByInt().equals("")) {
				  viewQuery+=" , ";
			  }
		  }
		  
		  if(!intAnalysis.getSecondByInt().equals(""))
		  {
			  viewQuery+= getSortVal(intAnalysis.getSecondByInt())+" "+ getSortOrder(intAnalysis.getRadioThreeInt());
		  }
		Object[][] data=getSqlModel().getSingleResult(viewQuery);
		
		if(data!=null && data.length>0)
		{
			intAnalysis.setModeLength("true");	
		}else
		{
			intAnalysis.setModeLength("false");	
		}
		String[] pageIndex = Utility.doPaging(intAnalysis.getMyPage(),data.length, 20);

		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage1", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("PageNo1", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			intAnalysis.setMyPage("1");
	
		ArrayList<Object> list = new ArrayList<Object>();
		if (data != null && data.length > 0) {
			intAnalysis.setCandidateLength(String.valueOf(data.length));
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				
				
		    InterviewAnalysis bean= new InterviewAnalysis ();
		    if (intAnalysis.getCandidateCheck().equals("N")) {
		    	intAnalysis.setCandidateHd("true");
				bean.setCandidateFlag("true");
			}
		    else {
				   bean.setCandidateName(String.valueOf(data[i][0]));
		    	}
		    if (intAnalysis.getInterviewerCheck().equals("N")) {
		    	intAnalysis.setIntrviewerHd("true");
				bean.setIntrviewerFlag("true");
			}
		    else {
				   bean.setInterviewer(String.valueOf(data[i][1]));
		    	}
			
		    if (intAnalysis.getIntRoundCheck().equals("N")) {
		    	intAnalysis.setIntRndTypeHd("true");
				bean.setIntRndTypeFlag("true");
			}
		    else {
				   bean.setIntRound(String.valueOf(data[i][2]));
		    	}
		   if(intAnalysis.getMakeOffferCheck().equals("N")){
			   intAnalysis.setMakeOfferHd("true");
			  bean.setMakeOfferFlag("true");
			   
		   }
		   else{
		    bean.setMakeOffer(String.valueOf(data[i][3]));
		   }
		 
		   if(intAnalysis.getNextRoundCheck().equals("N")){
			   intAnalysis.setNextRoundHd("true");
			   bean.setNextRoundFlag("true");
			   
		   }
		   else{
		    bean.setNextRound(String.valueOf(data[i][4]));
		   }
		    //bean.setScore(String.valueOf(data[i][5]));
		    
		 if (intAnalysis.getScoreCheck().equals("N")) {
	    	intAnalysis.setScoreHd("true");
			bean.setScoreFlag("true");
		}
	    else {
			   bean.setScore(String.valueOf(data[i][5]));
	    	}
		 
		 
		 if(intAnalysis.getPercentageCheck().equals("N")){
			 intAnalysis.setPercentageHd("true");
				bean.setPercentageFlag("true");
		 }
		 else{
		    bean.setPercentage(String.valueOf(data[i][6]));
		 }
		    
		   
		    if (intAnalysis.getStatusCheck().equals("N")) {
		    	intAnalysis.setStatusHd("true");
				bean.setStatusFlag("true");
			}
		    else {
				   bean.setStatus(String.valueOf(data[i][7]));
		    	}
		    
		    
		    String queryDtls=" SELECT  EVAL_RATE_CODE, EVAL_RATE_SCORE from HRMS_REC_CANDEVAL_DTL"
				+"	 INNER JOIN  HRMS_REC_CANDEVAL ON( HRMS_REC_CANDEVAL.EVAL_CODE=HRMS_REC_CANDEVAL_DTL.EVAL_CODE)"
				+" WHERE EVAL_CAND_CODE="+data[i][8] ;
			
            Object[][]evalData=getSqlModel().getSingleResult(queryDtls);
	   
		    if(intAnalysis.getQualificationCheck().equals("N")){
		    	intAnalysis.setQualificationHd("true");
				bean.setQuailificationFlag("true");
		    }else{
		     	bean.setQualification(String.valueOf(evalData[0][1]));
		    }
		    if(intAnalysis.getTechnicalCheck().equals("N")){
		    	intAnalysis.setTechnicalHd("true");
				bean.setTechnicalFlag("true");
		    }else{
		    	bean.setTechnical(String.valueOf(evalData[1][1]));
		    }
		    if(intAnalysis.getCommunicationCheck().equals("N")){
		    	intAnalysis.setCommunicationHd("true");
				bean.setCommunicationFlag("true");
		    }else{
		    	bean.setCommunication(String.valueOf(evalData[2][1]));
		    }
		    	
		    if(intAnalysis.getMangmentCheck().equals("N")){
		    	intAnalysis.setManagmentHd("true");
				bean.setManagmentFlag("true");
		    }else{
		    	bean.setManagment(String.valueOf(evalData[3][1]));	
		    }
		    if(intAnalysis.getPersonalityCheck().equals("N")){
		    	intAnalysis.setPersonalityHd("true");
				bean.setPersonalityFlag("true");
		    }else{
		    	bean.setPersonality(String.valueOf(evalData[4][1]));
		    	
		    }
		    if(intAnalysis.getLearningCheck().equals("N")){
		    	intAnalysis.setLearningHd("true");
				bean.setLearningFlag("true");
		    }else{
		    	bean.setLearning(String.valueOf(evalData[5][1])); 
		    }
		    	
		    if(intAnalysis.getRelevantCheck().equals("N")){
		    	intAnalysis.setRelevantHd("true");
				bean.setRelevantFlag("true");
		    }else{
		    	bean.setRelevant(String.valueOf(evalData[6][1]));
		    }
		    if(intAnalysis.getSuitablityCheck().equals("N")){
		    	intAnalysis.setSuitablityHd("true");
				bean.setSutablityFlag("true");
		    }else{	
		       	bean.setSuitable(String.valueOf(evalData[7][1]));
		    }
			list.add(bean);
			
			}
			intAnalysis.setInterviewList(list);
			intAnalysis.setCandidateLength(String.valueOf(data.length));
						
		}
		else{//if reqData is null
			intAnalysis.setNoData("true");//No data to display message shown
			intAnalysis.setInterviewList(null);
			intAnalysis.setCandidateLength(String.valueOf(data.length));
			}
		
	}
	
/*
 *  this is used to select the the field name for sorting 
 */
	public String getSortVal(String Status)
	{  String sql="";
	
	if(Status.equals("C")){
			sql=" CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME  ";
		} 
		
		 if(Status.equals("I")){
			sql=" EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME  ";
		} 
		
		return sql;
		 
	}
	/*
	 *  this is used to select the the ordering  for sorting 
	 */
	public String getSortOrder(String Status)
	{ 
	   String sql=""; 
	   if(Status.equals("A")) {		   
		   sql="Asc";
	   } 
	   if(Status.equals("D")) {		   
		   sql="Desc";
	   }
	   return sql ;
	}
	
	public void doPaging(InterviewAnalysis bean, int candidateLength,
			Object[][] data, HttpServletRequest request) {
		try {
			/*
			 * totalRec -: No. of records per page fromTotRec -: Starting No. of
			 * record to show on a current page toTotRec -: Last No. of record
			 * to show on a current page pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			 */

			/*
			 * String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM
			 * HRMS_SALARY_CONF "; Object[][] pagingObj =
			 * getSqlModel().getSingleResult(pagingSql);
			 */
			int totalRec = 1;// Integer.parseInt(String.valueOf(pagingObj[0][0]));
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int totalPage = 0;

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
					(double) candidateLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			if (String.valueOf(bean.getHdPage()).equals("null")
					|| String.valueOf(bean.getHdPage()).equals(null)
					|| String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if (toTotRec > candidateLength) {
					toTotRec = candidateLength;
				}
				bean.setHdPage("1");
			} else {
				pageNo = Integer.parseInt(bean.getHdPage());

				if (pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if (toTotRec > candidateLength) {
					toTotRec = candidateLength;
				}

			}

			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method is used to insert the value in all filter option
	 * @param intAnalysis
	 * @return
	 */
	public boolean saveRepSettings(InterviewAnalysis intAnalysis) {
				
		// TODO Auto-generated method stub
		Object[][] filtOpt = new Object[1][7];
		Object[][] sortOpt = new Object[1][7];
		Object[][] ColDef = new Object[1][6];
		
		

		/**
		 * following array is called to insert the filter details record into
		 * the HRMS_REC_INTANAREP_FILTERS table.
		 */
		
		
		filtOpt[0][0] = intAnalysis.getCandCode();
		filtOpt[0][1] = intAnalysis.getIntrvCode();
		filtOpt[0][2] = intAnalysis.getInterviewstatus();
		filtOpt[0][3] = intAnalysis.getInterviewRound();
		filtOpt[0][4] = intAnalysis.getSeltype();
		filtOpt[0][5] = intAnalysis.getSettingName();
		filtOpt[0][6] = intAnalysis.getUserEmpId();
		
		String InstQuery = "INSERT INTO HRMS_REC_INTANAREP_FILTERS(INTANAREP_CODE,INTANAREP_CAND_CODE, INTANAREP_INTVIEWER_CODE, INTANAREP_INTSTATUS, INTANAREP_INTRNDS, INTANAREP_REPOPTION, INTANAREP_NAME, INTANAREP_USEREMPID)"
				+ " VALUES((SELECT NVL(MAX(INTANAREP_CODE),0)+1 FROM HRMS_REC_INTANAREP_FILTERS),?,?,?,?,?,?,?)";

		boolean result = getSqlModel().singleExecute(InstQuery, filtOpt);
		if (result) {
			String query = "SELECT MAX(INTANAREP_CODE) FROM HRMS_REC_INTANAREP_FILTERS";
			Object[][] data = getSqlModel().getSingleResult(query);
				
			
			/**
			 * following array is called to insert Test the sorting details
			 * record into the HRMS_REC_INTANALYSISREP_SORT
			 */

			sortOpt[0][0] = String.valueOf(data[0][0]);
			sortOpt[0][1] = intAnalysis.getSortByInt();
			sortOpt[0][2] = intAnalysis.getRadioOneInt();
			sortOpt[0][3] = intAnalysis.getThenBYInt();
			sortOpt[0][4] = intAnalysis.getRadioTwoInt();
			sortOpt[0][5] = intAnalysis.getSecondByInt();
			sortOpt[0][6] = intAnalysis.getRadioThreeInt();
			String sortQurey = "INSERT INTO HRMS_REC_INTANALYSISREP_SORT(INTANALYSISREP_CODE, SORT_BY, SORT_BY_ORDER, SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1, SORT_THENBY1_ORDER) "
					+ " VALUES(?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(sortQurey, sortOpt);
			
			
			/**
			 * following array is called to insert the column definition details
			 * record into the HRMS_REC_SCHDREP_COLDEF
			 */

			ColDef[0][0] = String.valueOf(data[0][0]);
			ColDef[0][1] = intAnalysis.getCandidateCheck();// Intrvcandidate();
			ColDef[0][2] = intAnalysis.getInterviewerCheck();// IntRoundType();
			ColDef[0][3] = intAnalysis. getIntRoundCheck();// IntervewDate();
			ColDef[0][4] = intAnalysis.getScoreCheck();// IntervewTime();
			ColDef[0][5] = intAnalysis.getStatusCheck();// InterviewVenue();
			

			String coldefQuery = "INSERT INTO HRMS_REC_INTANALYSISREP_COLDEF(INTANALYSISREP_CODE, COL_CANDNAME, COL_INTVIEWER, COL_INTROUND, COL_TOTALSCORE, COL_STATUS)"
					+ "VALUES(?,?,?,?,?,?)";
			getSqlModel().singleExecute(coldefQuery, ColDef);
		
		}
		return result;
	}
/**
 *  this method is used to update the all filter Option
 * @param intAnalysis
 * @return
 */
	public boolean updateSettings(InterviewAnalysis intAnalysis) {
		// TODO Auto-generated method stub
		
		boolean result = false;

		try {
			Object[][] filtOpt = new Object[1][8];
			Object[][] sortOpt = new Object[1][7];
			Object[][] ColDef = new Object[1][6];
			
			
			filtOpt[0][0] = intAnalysis.getCandCode();
			filtOpt[0][1] = intAnalysis.getIntrvCode();
			filtOpt[0][2] = intAnalysis.getInterviewstatus();
			filtOpt[0][3] = intAnalysis.getInterviewRound();
			filtOpt[0][4] = intAnalysis.getSeltype();
			filtOpt[0][5] = intAnalysis.getSettingName();
			filtOpt[0][6] = intAnalysis.getUserEmpId();
			filtOpt[0][7] = intAnalysis.getSettingCode();
			
			
			String UpQuery = "UPDATE HRMS_REC_INTANAREP_FILTERS SET INTANAREP_CAND_CODE="+filtOpt[0][0]+", INTANAREP_INTVIEWER_CODE="+filtOpt[0][1]+", INTANAREP_INTSTATUS='"+filtOpt[0][2]+"', INTANAREP_INTRNDS="+filtOpt[0][3]+", INTANAREP_REPOPTION='"+filtOpt[0][4]+"', INTANAREP_NAME='"+filtOpt[0][5]+"', INTANAREP_USEREMPID="+filtOpt[0][6]+" WHERE INTANAREP_CODE="+filtOpt[0][7]+"";
				
			
			
			result=getSqlModel().singleExecute(UpQuery);
			
				     if (result) {
							/**
							 * following array is called to update the test sorting details
							 * record into the HRMS_REC_INTANALYSISREP_SORT atable.
							 */
							// sortOpt[0][0]=String.valueOf(data[0][0]);
							sortOpt[0][0] = intAnalysis.getSortByInt();
							if (intAnalysis.getRadioOneInt().equals("A")) {
								sortOpt[0][1] = String.valueOf("A");
							} else if (intAnalysis.getRadioOneInt().equals("D")) {
								sortOpt[0][1] = String.valueOf("D");
							}
							sortOpt[0][2] = intAnalysis.getThenBYInt();
							if (intAnalysis.getRadioTwoInt().equals("A")) {
								sortOpt[0][3] = String.valueOf("A");
							} else if (intAnalysis.getRadioTwoInt().equals("D")) {
								sortOpt[0][3] = String.valueOf("D");
							}

							sortOpt[0][4] = intAnalysis.getSecondByInt();
							if (intAnalysis.getRadioThreeInt().equals("A")) {
								sortOpt[0][5] = String.valueOf("A");
							} else if (intAnalysis.getRadioThreeInt().equals("D")) {
								sortOpt[0][5] = String.valueOf("D");
							}
							sortOpt[0][6] = intAnalysis.getSettingCode();
					       String UpdaetSort="UPDATE HRMS_REC_INTANALYSISREP_SORT SET  SORT_BY=?, SORT_BY_ORDER=?, SORT_THENBY=?, SORT_THENBY_ORDER=?, SORT_THENBY1=?, SORT_THENBY1_ORDER=?  WHERE INTANALYSISREP_CODE=?";
					      	getSqlModel().singleExecute(UpdaetSort, sortOpt);
		
					      	

							/**
							 * following array is called to UPDATE the column definition details
							 * record into the HRMS_REC_INTANALYSISREP_COLDEF
							 */

							
							ColDef[0][0] = intAnalysis.getCandidateCheck();// Intrvcandidate();
							ColDef[0][1] = intAnalysis.getInterviewerCheck();// IntRoundType();
							ColDef[0][2] = intAnalysis. getIntRoundCheck();// IntervewDate();
							ColDef[0][3] = intAnalysis.getScoreCheck();// IntervewTime();
							ColDef[0][4] = intAnalysis.getStatusCheck();// InterviewVenue();
							ColDef[0][5] = intAnalysis.getSettingCode();
					

							String coldefQuery = "UPDATE HRMS_REC_INTANALYSISREP_COLDEF SET  COL_CANDNAME=?, COL_INTVIEWER=?, COL_INTROUND=?, COL_TOTALSCORE=?, COL_STATUS=? WHERE INTANALYSISREP_CODE=?";
									
							getSqlModel().singleExecute(coldefQuery, ColDef);
				     
				     }
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}
/**
 *  this   method display the selected record record in filter option
 * @param intAnalysis
 */
	public void getFilterOptions(InterviewAnalysis intAnalysis) {
		// TODO Auto-generated method stub
		Object[] code = new Object[1];
		code[0] = intAnalysis.getSettingCode();
		String query="SELECT CAND_CODE,CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
			         +" INTANAREP_INTSTATUS,NVL(INTANAREP_INTRNDS,''),DECODE(INTANAREP_REPOPTION,'Pdf','Pdf','Xls','Xls','Txt','Txt'),INTANAREP_NAME"
			         +"	 from HRMS_REC_INTANAREP_FILTERS"
			         +"	 LEFT join HRMS_REC_CAND_DATABANK on(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_INTANAREP_FILTERS.INTANAREP_CAND_CODE)"
			         +"	 LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_INTANAREP_FILTERS.INTANAREP_INTVIEWER_CODE )"
			         +"	 WHERE INTANAREP_CODE="+intAnalysis.getSettingCode();
		
		Object[][] filters = getSqlModel().getSingleResult(query);
		
		
		if (filters != null && filters.length > 0) {
			
			intAnalysis.setCandCode(String.valueOf(filters[0][0]));
			intAnalysis.setCandName(String.valueOf(filters[0][1]));
			intAnalysis.setIntrvCode(String.valueOf(filters[0][2]));
			intAnalysis.setInterviewerName(String.valueOf(filters[0][3]));
			intAnalysis.setInterviewstatus(String.valueOf(filters[0][4]));
		
			intAnalysis.setInterviewRound(String.valueOf(filters[0][5]));
			intAnalysis.setSeltype(String.valueOf(filters[0][6]));
			intAnalysis.setSettingName(String.valueOf(filters[0][7]));
	}

	
	
}
	/**
	 *  this   method display the selected record record in Sorting option
	 * @param intAnalysis
	 */
	public void getSortingDet(InterviewAnalysis intAnalysis) {
		// TODO Auto-generated method stub
		
		Object[] code = new Object[1];
		code[0] = intAnalysis.getSettingCode();
		String sortquery=" SELECT SORT_BY, SORT_BY_ORDER, SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1, SORT_THENBY1_ORDER FROM HRMS_REC_INTANALYSISREP_SORT"
				 +" WHERE INTANALYSISREP_CODE="+intAnalysis.getSettingCode();
				Object[][] filters = getSqlModel().getSingleResult(sortquery);
		
		if (filters != null && filters.length > 0) {
			
			intAnalysis.setSortByInt(String.valueOf(filters[0][0]));
			if(String.valueOf(filters[0][1]).trim().equals("A")){
				intAnalysis.setSortRadioInt("A");
				intAnalysis.setRadioOneInt("A");
			}
			else if(String.valueOf(filters[0][1]).equals("D")){
				intAnalysis.setSortRadioInt("D");
				intAnalysis.setRadioOneInt("D");
			}
			
			intAnalysis.setThenBYInt(String.valueOf(filters[0][2]));
			if(String.valueOf(filters[0][3]).trim().equals("A")){
				intAnalysis.setThenRadioInt("A");
				intAnalysis.setRadioTwoInt("A");
			}
			else if(String.valueOf(filters[0][3]).trim().equals("D")){
				intAnalysis.setThenRadioInt("D");
				intAnalysis.setRadioTwoInt("D");
			}
			intAnalysis.setSecondByInt(String.valueOf(filters[0][4]));
			if(String.valueOf(filters[0][5]).trim().equals("A")){
				intAnalysis.setThanRadioInt("A");
				intAnalysis.setRadioThreeInt("A");
			}
			else if(String.valueOf(filters[0][5]).trim().equals("D")){
				intAnalysis.setThanRadioInt("D");
				intAnalysis.setRadioThreeInt("D");
			}
			intAnalysis.setButtonFlag(true);
			
	}

}
	/**
	 *  this   method disply the selected record record in Column defination 
	 * @param intAnalysis
	 */
	public void getColumnDef(InterviewAnalysis intAnalysis,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Object[] code = new Object[1];
		code[0] = intAnalysis.getSettingCode();
		Object[][] checkBox=new Object[1][5];
		String colDefQuery="SELECT  COL_CANDNAME, COL_INTVIEWER, COL_INTROUND, COL_TOTALSCORE, COL_STATUS FROM HRMS_REC_INTANALYSISREP_COLDEF  WHERE INTANALYSISREP_CODE="+intAnalysis.getSettingCode();
		Object[][] data = getSqlModel().getSingleResult(colDefQuery);
		if(data!=null && data.length>0){//Candidate Name
			if(String.valueOf(data[0][0]).equals("Y")){
				checkBox[0][0]=String.valueOf("true");
			}else{
				checkBox[0][0]=String.valueOf("false");
			}
			intAnalysis.setCandidateCheck(String.valueOf(data[0][0]));
			if(String.valueOf(data[0][1]).equals("Y")){
				checkBox[0][1]=String.valueOf("true");
			}else{
				checkBox[0][1]=String.valueOf("false");
			}
			intAnalysis.setInterviewerCheck(String.valueOf(data[0][1]));
			if(String.valueOf(data[0][2]).equals("Y")){
				checkBox[0][2]=String.valueOf("true");
			}else{
				checkBox[0][2]=String.valueOf("false");
			}
			intAnalysis.setIntRoundCheck(String.valueOf(data[0][2]));
			if(String.valueOf(data[0][3]).equals("Y")){
				checkBox[0][3]=String.valueOf("true");
			}else{
				checkBox[0][3]=String.valueOf("false");
			}
			intAnalysis.setScoreCheck(String.valueOf(data[0][3]));
			if(String.valueOf(data[0][4]).equals("Y")){
				checkBox[0][4]=String.valueOf("true");
			}else{
				checkBox[0][4]=String.valueOf("false");
			}
			intAnalysis.setStatusCheck(String.valueOf(data[0][4]));
			
			intAnalysis.setMyChkFlag(true);
			
			
			
		}
	}
	
	
	
	
	

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	
	
	/**
	 *  this   method is used for generating the report
	 * @param intAnalysis
	 */
	public void getGenerateReport(InterviewAnalysis bean,
			HttpServletResponse response, HttpServletRequest request,
			String[] colnames) {
		// TODO Auto-generated method stub
		
		
		String s="\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  INTERVIEW ANALYSIS REPORT  \n\n";
		String reportType="";
		
		if(bean.getSeltype().equals("Pdf")){
			reportType="Pdf";
		}
		if(bean.getSeltype().equals("Xls")){
			reportType="Xls";
		}
		if(bean.getSeltype().equals("Txt")){
			reportType="Txt";
		}
		
		
	org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(reportType,s,"A4");
	
				int testcounter = 0;
				String query = "SELECT  ";

				if (bean.getCandidateCheck().equals("Y")) {
					logger.info("In if");
					query += "  DISTINCT  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME";

				} else {
					query += "''";
				}
				if (bean.getInterviewerCheck().equals("Y")) {
					
					query += " ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME";

				} else {
					query += ",''";
				}
		    if (bean.getIntRoundCheck().equals("Y")) {
					
					query += " ,EVAL_ROUND_TYPE";

				} else {
					query += ",''";
				}
		    
		    if (bean.getMakeOffferCheck().equals("Y")) {
				
				query += " , EVAL_MAKE_OFFER";

			} else {
				query += ",''";
			}
 if (bean.getNextRoundCheck().equals("Y")) {
				
				query += " , EVAL_FWD_NEXTROU";

			} else {
				query += ",''";
			}
		  
		    if (bean.getScoreCheck().equals("Y")) {
				
				query += " ,EVAL_SCORE";

			} else {
				query += ",''";
			}
		 if (bean.getStatusCheck().equals("Y")) {
				
				query += " ,DECODE(EVAL_INT_STATUS ,'S','Selected','R','Rejected','O','On Hold')";

			} else {
				query += ",''";
			}
			
			 query+=" ,CAND_CODE from HRMS_REC_CANDEVAL	"
					+"	inner join HRMS_REC_CAND_DATABANK on(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_CANDEVAL.EVAL_CAND_CODE)"
					+"	left JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_CANDEVAL.EVAL_INT_EMPID )"
					+" inner join   HRMS_REC_CANDEVAL_DTL on(HRMS_REC_CANDEVAL_DTL.EVAL_CODE=HRMS_REC_CANDEVAL.EVAL_CODE)"
					//+"	inner join HRMS_REC_INTANAREP_FILTERS on(HRMS_REC_INTANAREP_FILTERS.INTANAREP_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)";
					//+"	WHERE INTANAREP_CODE="+bean.getSettingCode();
			          +"	WHERE 1=1 ";
			//  this is for  the  Filter Option  
			 if (!(bean.getCandCode().equals("") || bean.getCandCode().equals("null"))) {
					query += " AND CAND_CODE=" + bean.getCandCode();
				}
			 if (!(bean.getIntrvCode().equals("") || bean.getIntrvCode().equals("null"))) {
					query += " AND HRMS_REC_CANDEVAL.EVAL_INT_EMPID=" + bean.getIntrvCode();
				}
			 if (!(bean.getInterviewstatus().equals("") || bean.getInterviewstatus().equals("null"))) {
					query += " AND EVAL_INT_STATUS=" + bean.getInterviewstatus();
				}
			 if (!(bean.getInterviewRound().equals("") || bean.getInterviewRound().equals("null"))) {
					query += " AND EVAL_ROUND_TYPE=" + bean.getInterviewRound();
				}
			 
			 
			 // this is for Sorting Option first
			 if(bean.getSortByInt().equals("C") ||bean.getSortByInt().equals("I")||bean.getThenBYInt().equals("I") ||bean.getThenBYInt().equals("C")||bean.getSecondByInt().equals("C") ||bean.getSecondByInt().equals("I") )
				{
					query += " ORDER BY";
				}
			 
			 if (bean.getSortByInt().equals("C")) {
					if (bean.getRadioOneInt().equals("A")) {
						query += " CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ASC ";

					} else if (bean.getRadioOneInt().equals("D")) {
						query += "  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME DESC ";

					}
					if (bean.getSecondByInt().equals("I")||bean.getThenBYInt().equals("C")||bean.getSecondByInt().equals("C")||bean.getThenBYInt().equals("I")) {
						query += " , ";
					}
				}
			 if (bean.getSortByInt().equals("I")) {
					if (bean.getRadioOneInt().equals("A")) {
						query += "  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ASC ";

					} else if (bean.getRadioOneInt().equals("D")) {
						query += "  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME DESC ";

					}
					if (bean.getSecondByInt().equals("I")||bean.getThenBYInt().equals("C")||bean.getSecondByInt().equals("C")||bean.getThenBYInt().equals("I")) {
						query += " , ";
					}
				}
			 
			 // this is for Sorting Option Second
			 if (bean.getThenBYInt().equals("C")) {
					if (bean.getRadioTwoInt().equals("A")) {
						query += "  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ASC ";

					} else if (bean.getRadioTwoInt().equals("D")) {
						query += "  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME DESC ";

					}
					if (bean.getSecondByInt().equals("I")||bean.getSecondByInt().equals("C")) {
						query += " , ";
					}
				}
			 if (bean.getThenBYInt().equals("I")) {
					if (bean.getRadioTwoInt().equals("A")) {
						query += "  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ASC ";

					} else if (bean.getRadioTwoInt().equals("D")) {
						query += "  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME DESC ";

					}
					if (bean.getSecondByInt().equals("I")||bean.getSecondByInt().equals("C")) {
						query += " , ";
					}
				}
			 // this is for Sorting Option Third
			 if (bean.getSecondByInt().equals("C")) {
					if (bean.getRadioThreeInt().equals("A")) {
						query += "  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ASC ";

					} else if (bean.getRadioThreeInt().equals("D")) {
						query += "  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME DESC ";

					}
				}
			 if (bean.getSecondByInt().equals("I")) {
					if (bean.getRadioThreeInt().equals("A")) {
						query += "  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ASC ";

					} else if (bean.getRadioThreeInt().equals("D")) {
						query += "  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME DESC ";

					}
				}
			 
			 Object[][] data = getSqlModel().getSingleResult(query);
			 
			
				if (data != null && data.length > 0) {
				//	for (int i = 0; i < data.length; i++) {
						
						int counter = getInterviewCount(bean);
					
						Object[][] intrvData = new Object[data.length][counter + 1];
						String[] col = new String[counter + 1];
						int[] cellIntWidth = new int[counter + 1];
						int[] intAlign = new int[counter + 1];
						int cntr = 0;
						int mm = 0;
						col[mm] = colnames[0];
						cellIntWidth[mm] = 5;
						intAlign[mm] = 1;
						mm++;
						if (bean.getCandidateCheck().equals("Y")) {
							col[mm] = colnames[1];
							cellIntWidth[mm] = 15;
							intAlign[mm] = 0;
							mm++;
						}
						if (bean.getInterviewerCheck().equals("Y")) {
							col[mm] = colnames[2];
							cellIntWidth[mm] = 15;
							intAlign[mm] = 0;
							mm++;
						}
						if (bean.getIntRoundCheck().equals("Y")) {
							col[mm] = colnames[3];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
						
						if (bean.getQualificationCheck().equals("Y")) {
							col[mm] = colnames[4];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}	
						if (bean.getTechnicalCheck().equals("Y")) {
							col[mm] = colnames[5];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
						if (bean.getCommunicationCheck().equals("Y")) {
							col[mm] = colnames[6];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
						if (bean.getMangmentCheck().equals("Y")) {
							col[mm] = colnames[7];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
						
						if (bean.getPersonalityCheck().equals("Y")) {
							col[mm] = colnames[8];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
						if (bean.getLearningCheck().equals("Y")) {
							col[mm] = colnames[9];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
						if (bean.getRelevantCheck().equals("Y")) {
							col[mm] = colnames[10];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
				if (bean.getSuitablityCheck().equals("Y")) {
							col[mm] = colnames[11];
							cellIntWidth[mm] = 10;
							intAlign[mm] = 0;
							mm++;
						}
				if (bean.getMakeOffferCheck().equals("Y")) {
					col[mm] = colnames[12];
					cellIntWidth[mm] = 10;
					intAlign[mm] = 0;
					mm++;
				}
				if (bean.getNextRoundCheck().equals("Y")) {
					col[mm] = colnames[13];
					cellIntWidth[mm] = 10;
					intAlign[mm] = 0;
					mm++;
				}	
				if (bean.getScoreCheck().equals("Y")) {
					col[mm] = colnames[14];
					cellIntWidth[mm] = 10;
					intAlign[mm] = 0;
					mm++;
				}
				
				if (bean.getPercentageCheck().equals("Y")) {
					col[mm] = colnames[15];
					cellIntWidth[mm] = 10;
					intAlign[mm] = 0;
					mm++;
				}
				if (bean.getStatusCheck().equals("Y")) {
					col[mm] = colnames[16];
					cellIntWidth[mm] = 10;
					intAlign[mm] = 0;
					mm++;
				}
				mm = 0;
					int n=0; 	
						for (int j = 0; j < data.length; j++) {

							intrvData[j][cntr] =++n ;//data[j][0];
							cntr++;
							if (bean.getCandidateCheck().equals("Y")) {
								intrvData[j][cntr] = data[j][0];// Candidate Name
								cntr++;
							}
							if (bean.getInterviewerCheck().equals("Y")) {
								intrvData[j][cntr] = data[j][1];// Interviewer
							
								cntr++;
							}
							if (bean.getIntRoundCheck().equals("Y")) {
								intrvData[j][cntr] = data[j][2];// Interview round
								
								cntr++;
							}
							
							
					String queryDtls=" SELECT  EVAL_RATE_CODE, EVAL_RATE_SCORE from HRMS_REC_CANDEVAL_DTL"
								+"	 INNER JOIN  HRMS_REC_CANDEVAL ON( HRMS_REC_CANDEVAL.EVAL_CODE=HRMS_REC_CANDEVAL_DTL.EVAL_CODE)"
								+" WHERE EVAL_CAND_CODE="+data[j][7] ;
							
				       Object[][]evalData=getSqlModel().getSingleResult(queryDtls);
  
				       Object[][] evalDatas = new Object[evalData.length][counter + 1];	   
						   
				            
				            if(bean.getQualificationCheck().equals("Y")){
						    intrvData[j][cntr]=evalData[0][1];// Qualification
				            	cntr++;
						     
						  }
						    if(bean.getTechnicalCheck().equals("Y")){
						    
						    	intrvData[j][cntr]=evalData[1][1];// technical 
				            	cntr++;
						    	
						   }
						    if(bean.getCommunicationCheck().equals("Y")){
						      	intrvData[j][cntr]=evalData[2][1];// communication
				            	cntr++;
						    }
						    	
						    if(bean.getMangmentCheck().equals("Y")){
						    	intrvData[j][cntr]=evalData[3][1];// managment
				            	cntr++;
						    }
						    if(bean.getPersonalityCheck().equals("Y")){
						   
						    	intrvData[j][cntr]=evalData[4][1];// personality
				            	cntr++;
						    }
						    if(bean.getLearningCheck().equals("Y")){
						    
						    intrvData[j][cntr]=evalData[5][1];// Learning
			            	cntr++;
						    }
						    	
						    if(bean.getRelevantCheck().equals("Y")){
						    
						    	intrvData[j][cntr]=evalData[6][1];// relevant 
				            	cntr++;
						    }
						    if(bean.getSuitablityCheck().equals("Y")){
						    	
						    	intrvData[j][cntr]=evalData[7][1];//suitablity
				            	cntr++;
						    }
						    
						   
						    if (bean.getMakeOffferCheck().equals("Y")) {
								intrvData[j][cntr] = data[j][3];// Make Offer Letter
								
								cntr++;
							}
							if (bean.getNextRoundCheck().equals("Y")) {
								intrvData[j][cntr] = data[j][4];// Next Round
								
								cntr++;
							}
							if (bean.getScoreCheck().equals("Y")) {
								intrvData[j][cntr] = data[j][5];// Total Score
								cntr++;
							}
							if(bean.getPercentageCheck().equals("Y")){
						    	intrvData[j][cntr]=evalData[9][1];// percentage
				            	cntr++;
							 }
							if (bean.getStatusCheck().equals("Y")) {
								intrvData[j][cntr] = data[j][6];// Status
								
								cntr++;
							}
							cntr=0;
						}
						if (data != null && data.length > 0) {
							rg.addText("\n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t INTERVIEW ANALYSIS REPORT  \n\n",0,0,0);
							rg.tableBody(col, intrvData, cellIntWidth,intAlign);

						}
						if(bean.getSeltype().equals("Txt")){
						
							rg.addText("\n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t INTERVIEW ANALYSIS REPORT  \n\n",0,0,0);
							rg.tableBody(col, intrvData, cellIntWidth,intAlign);
							
						}	
						}	
				else {
					rg.addText("No records found.", 0, 1, 0);
				}
		rg.createReport(response);			
	}
	
	/** this method display the record  for selected check box.
	 * 
	 * @param bean
	 * @return
	 */
	public int getInterviewCount(InterviewAnalysis bean) {
		int counter = 0;
		if (bean.getCandidateCheck().equals("Y"))
			counter++;
		if (bean.getInterviewerCheck().equals("Y"))
			counter++;
		if (bean.getIntRoundCheck().equals("Y"))
			counter++;
		if (bean.getScoreCheck().equals("Y"))
			counter++;
		if (bean.getStatusCheck().equals("Y"))
			counter++;
		if(bean.getQualificationCheck().equals("Y"))
			counter++;
		if(bean.getTechnicalCheck().equals("Y"))
			counter++;
		if(bean.getCommunicationCheck().equals("Y"))
			counter++;
		if(bean.getMangmentCheck().equals("Y"))
			counter++;
		if(bean.getPersonalityCheck().equals("Y"))
			counter++;
		if(bean.getLearningCheck().equals("Y"))
			counter++;
		if(bean.getRelevantCheck().equals("Y"))
			counter++;
		if(bean.getSuitablityCheck().equals("Y"))
			counter++;
		if(bean.getMakeOffferCheck().equals("Y"))
			counter++;
		if(bean.getNextRoundCheck().equals("Y"))
			counter++;
		if(bean.getPercentageCheck().equals("Y"))
			counter++;
		return counter;
	}

	
}