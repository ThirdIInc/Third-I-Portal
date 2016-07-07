package org.paradyne.model.recruitment;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.PreOfferProcessing;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/*
 * @author ganesh
 * Date: 10/11/2010
 *  
 */
public class PreOfferProcessingModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PreOfferProcessingModel.class);

	/**
	 * Added by Pradeep on Date:17-06-2009
	 * following function is called in the action class when the candidate is selected from the pop up window.
	 * @param bean
	 */
	
	public void showOffer(PreOfferProcessing bean){
		try{
			String query="SELECT CASE WHEN OFFER_STATUS='D' THEN 'Due' WHEN OFFER_STATUS='P' THEN 'Pending' WHEN  OFFER_STATUS='A' THEN 'Approved'"
				+" WHEN OFFER_STATUS='R' THEN 'Approval Rejection'  WHEN OFFER_STATUS='I' THEN 'Issued' WHEN OFFER_STATUS='OA' THEN 'Accepted'"
				+" WHEN OFFER_STATUS='OR' THEN 'Rejected' WHEN OFFER_STATUS='C' THEN 'Canceled' END "
				+" FROM HRMS_REC_OFFER WHERE OFFER_CAND_CODE="+bean.getCandidateCode();
			Object obj[][] = getSqlModel().getSingleResult(query);
			if(obj!=null && obj.length>0){
				bean.setOfferStatus(String.valueOf(obj[0][0]));
			}else{
				bean.setOfferStatus("Not Given");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param bean 
	 * purpose:setting check list details 
	 */
	public void showCheckList(PreOfferProcessing bean) {
	
		
		try {
			if (String.valueOf(bean.getCheckListType()) != "") {
				String Query = "SELECT CHECK_CODE,CHECK_NAME FROM HRMS_CHECKLIST	WHERE CHECK_TYPE='"
						+ bean.getCheckListType() + "'	AND CHECK_STATUS='A'";

				Object obj[][] = getSqlModel().getSingleResult(Query);
				ArrayList<Object> list = new ArrayList<Object>();
				if (obj != null && obj.length > 0) {

					for (int i = 0; i < obj.length; i++) {
						PreOfferProcessing bgcheck = new PreOfferProcessing();
						bgcheck.setCheckListitemcode(String.valueOf(obj[i][0]));
						bgcheck.setCheckListName(String.valueOf(obj[i][1]));
						bgcheck.setCheckListresponce("");
						bgcheck.setCheckListComments("");
						list.add(bgcheck);
					}
					bean.setChkList(list);
					//checkListitemcode checkListName checkListresponce checkListComments
					bean.setChkLength(String.valueOf(list.size()));
					bean.setNoData("false");
				}
				else
				{
				bean.setChkLength("0");
				bean.setNoData("true");
				}
				
				bean.setChecklistTable(true);
			}else
			{
				bean.setChecklistTable(false);
			}
		
		} catch (Exception e) {
			
		}	
		
		
	}
	
	
	/**
	 * @param bean 
	 * purpose:setting  background check list details 
	 */

	public void setCheckList(PreOfferProcessing bean) {
		
		
		try {
			if (String.valueOf(bean.getCheckListType()) != "") {
				String Query = "select  HRMS_REC_PRECHECK_DTL.CHECKLIST_CODE,"
						+ " CHECK_NAME,PRE_RESPONSE,HRMS_REC_PRECHECK_DTL.PRE_COMMENTS,HRMS_REC_PRECHECK_DTL.PRE_CODE"
						+ ",decode(PRE_RESPONSE,'S ','Satisfied','N ','Not Satisfied','NA','Not Applicable'), HRMS_REC_PRECHECK_DTL.PRE_DTL_PROOF  from HRMS_REC_PRECHECK_DTL"
						+ " left JOIN HRMS_REC_PRECHECK ON (HRMS_REC_PRECHECK.PRE_CODE=HRMS_REC_PRECHECK_DTL.PRE_CODE)"
						+ " left JOIN HRMS_CHECKLIST ON (HRMS_CHECKLIST.CHECK_CODE=HRMS_REC_PRECHECK_DTL.CHECKLIST_CODE)"
						+ " where HRMS_REC_PRECHECK_DTL.PRE_CODE="
						+ bean.getBgcheckCode()
						+ " and HRMS_CHECKLIST.CHECK_TYPE='"
						+ bean.getCheckListType() + "'";

				Object obj[][] = getSqlModel().getSingleResult(Query);
				ArrayList<Object> list = new ArrayList<Object>();
				if (obj != null && obj.length > 0) {

					for (int i = 0; i < obj.length; i++) {
						PreOfferProcessing bgcheck = new PreOfferProcessing();
						bgcheck.setCheckListitemcode(String.valueOf(obj[i][0]));
						bgcheck.setCheckListName(String.valueOf(obj[i][1]));
						bgcheck.setCheckListresponce(String.valueOf(obj[i][2]));
						bgcheck.setDupcheckListresponce(String
								.valueOf(obj[i][5]));
						bgcheck.setCheckListComments(checkNull(String
								.valueOf(obj[i][3])));
						bgcheck.setUploadLocFileName(checkNull(String
								.valueOf(obj[i][6])));
						list.add(bgcheck);
					}
					bean.setChkList(list);
					//checkListitemcode checkListName checkListresponce checkListComments
					
					bean.setChkLength(String.valueOf(list.size()));
					bean.setNoData("false");
				}
				else
				{
				bean.setChkLength("0");
				bean.setNoData("true");
				}
				

			}
			bean.setChecklistTable(true);
		} catch (Exception e) {
			
		}	
		
		
	}
	
	
	
	
	
	/**
	 * @param bean
	 * @param ckcode
	 * @param response
	 * @param comments
	 * @return boolean after saving the application.
	 */
	public boolean save(PreOfferProcessing bean, Object[] ckcode, Object[] response, Object[] comments , Object[] dtlproof) {
		
		

	
		try {
			
			
			
			Object add[][] = new Object[1][6];
			add[0][0] = bean.getCandidateCode();
			add[0][1] = bean.getReqCode();
			if (bean.getBgCheckType().equals("O"))  //when background check type is Out source
				add[0][2] = "O";
			else
				add[0][2] = "I";                   //when background check type is Inhouse
			add[0][3] = bean.getCheckListType();
			add[0][4] = bean.getOverallComments();
			if (bean.getOutsourceAgencyCode() != "")
				add[0][5] = bean.getOutsourceAgencyCode();
			else
				add[0][5] = "";
			logger.info("Before firing insert query...!!" + getQuery(1));
			boolean result = getSqlModel().singleExecute(getQuery(1), add);
			logger.info("after  query...!!" + result);
			if (result) {
				logger.info("inserting child record ");

				String query = " SELECT NVL(MAX(PRE_CODE),0) FROM HRMS_REC_PRECHECK";
				Object resultCode[][] = getSqlModel().getSingleResult(query);

				if (ckcode != null) {

					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][5];

						chkDtl[0][0] = String.valueOf(resultCode[0][0]);
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][2] = String.valueOf(response[i]);
						chkDtl[0][3] = String.valueOf(comments[i]);
						chkDtl[0][4] = String.valueOf(dtlproof[i]);

						result = getSqlModel().singleExecute(getQuery(2),
								chkDtl);

					}
					logger
							.info("after inserting child records after for loop..!! ");

				}
				bean.setBgcheckCode(String.valueOf(resultCode[0][0]));

				boolean res = false;
				if (bean.getChkoffercode() != "")    // updating background verification status in offer.
				{ 	String updateStatusQuery = "update HRMS_REC_OFFER set OFFER_BG_REQ='C' where  OFFER_CODE="
							+ bean.getChkoffercode();
					res = getSqlModel().singleExecute(updateStatusQuery);
					logger.info("update query...REsult....!! " + res);
				}
				return result;
			}
			return result;
		} catch (Exception e) {
			
			return false;
		}
	}
	
	
	/**
	 * @param bean
	 * @param ckcode
	 * @param response
	 * @param comments
	 * @return boolean after modifying the application.
	 */
	public boolean update(PreOfferProcessing bean, Object[] ckcode, Object[] response, Object[] comments ,Object[] dtlproof ) {
		

		
		try {
			Object add[][] = new Object[1][7];
			add[0][0] = bean.getCandidateCode();
			add[0][1] = bean.getReqCode();
			if (bean.getBgCheckType().equals("O"))   //when background check type is Out source
				add[0][2] = "O";
			else
				add[0][2] = "I";                      //when background check type is In house
			add[0][3] = bean.getCheckListType();
			add[0][4] = bean.getOverallComments();
			if (bean.getOutsourceAgencyCode() != "")
				add[0][5] = bean.getOutsourceAgencyCode();
			else
				add[0][5] = "";                              //bean.getOutsourceAgencyCode();
			add[0][6] = bean.getBgcheckCode();
			//add[0][7] = bean.getUploadLocFileName();
			
			boolean result = getSqlModel().singleExecute(getQuery(3),add);
			if (result) {
				String delQuery = "delete from HRMS_REC_PRECHECK_DTL where PRE_CODE="
						+ bean.getBgcheckCode();
				result = getSqlModel().singleExecute(delQuery);

				logger.info("inserting child record ");

				String query = " SELECT NVL(MAX(PRE_CODE),0) FROM HRMS_REC_PRECHECK";
				Object resultCode[][] = getSqlModel().getSingleResult(query);

				if (ckcode != null) {

					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][5];
						chkDtl[0][0] = String.valueOf(bean.getBgcheckCode());
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][2] = String.valueOf(response[i]);
						chkDtl[0][3] = String.valueOf(comments[i]);
						chkDtl[0][4] = String.valueOf(dtlproof[i]);
						result = getSqlModel().singleExecute(getQuery(2),
								chkDtl);

					}
					
				}
				return result;
			}
			return result;
		} catch (Exception e) {
			
			return false;
		}
	}
	
	
	/**
	 * @param bean
	 * @param status
	 * purpose:setting background application list(pending or conducted)
	 */
	public void setBackgroundList(PreOfferProcessing bean,String status,HttpServletRequest request) {
		
		String ConductQuery="";
		if(String.valueOf(status)=="P") // when status is pending
		{ 
		   ConductQuery="select CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,REQS_NAME, "
						+" HRMS_RANK.RANK_NAME, " 
						+" CASE WHEN OFFER_STATUS='OA' THEN 'Accepted ' ELSE 'offer Status' End," +
								"OFFER_CODE,HRMS_REC_REQS_HDR.REQS_CODE,OFFER_CAND_CODE,CAND_RESUME"
						+" 	FROM  HRMS_REC_OFFER"
						+" 	INNER JOIN HRMS_REC_CAND_DATABANK  ON(HRMS_REC_OFFER.OFFER_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)" 
						+" 	INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_OFFER.OFFER_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
						+" 	INNER JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
						+" 	WHERE OFFER_STATUS='OA' AND OFFER_BG_REQ='N'";
		   
		   bean.setConductflag(false);
		   
		   
		}else	// when status is conducted
	     {
			/*ConductQuery="select CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,REQS_NAME, "
	    	              +" HRMS_RANK.RANK_NAME,CASE WHEN OFFER_STATUS='OA' THEN 'Accepted' ELSE 'offer Status' END,"
	    	              +" OFFER_CODE,HRMS_REC_REQS_HDR.REQS_CODE,OFFER_CAND_CODE,CAND_RESUME ," +
	    	              		" CASE WHEN BG_CHECK_LIST='J' THEN 'Joining ' WHEN BG_CHECK_LIST='M' THEN 'Medical' "
	    	              +" WHEN BG_CHECK_LIST='T' THEN 'Transfer' WHEN BG_CHECK_LIST='I' THEN 'Interview' "
	    	              +" WHEN BG_CHECK_LIST='B' THEN 'Background' WHEN BG_CHECK_LIST='P' THEN 'Prejoining' END" 
	    	              +"  from  HRMS_REC_OFFER "
	    	              +" inner join HRMS_REC_CAND_DATABANK  on(HRMS_REC_OFFER.OFFER_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) " 
	    	              +" inner join HRMS_REC_REQS_HDR on(HRMS_REC_OFFER.OFFER_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
	    	              +" inner join HRMS_RANK on(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
	    	              +" inner join HRMS_REC_BGCHECK on(HRMS_REC_BGCHECK.BG_CAND_CODE=HRMS_REC_OFFER.OFFER_CAND_CODE)"  
	    	            //  +" where ";
	     					+" 	WHERE OFFER_STATUS='OA' AND OFFER_BG_REQ='C'";*/
	                          
			ConductQuery=" SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME as name, "
				         +"  HRMS_REC_REQS_HDR.REQS_NAME,HRMS_RANK.RANK_NAME,"
				         +" DECODE(OFFER_STATUS, 'D ','Due','I', 'Issued', 'OA', 'Accepted', 'OR', 'Rejected', 'C ', 'Canceled'),"
				         +" HRMS_REC_OFFER.OFFER_CODE,PRE_REQS_CODE,PRE_CAND_CODE,CAND_RESUME, "
				         +" CASE WHEN PRE_CHECK_LIST='J' THEN 'Joining ' WHEN PRE_CHECK_LIST='M' THEN 'Medical' " 
				         +" WHEN PRE_CHECK_LIST='T' THEN 'Transfer' WHEN PRE_CHECK_LIST='I' THEN 'Interview'  "
				         +" WHEN PRE_CHECK_LIST='B' THEN 'Background' WHEN PRE_CHECK_LIST='P' THEN 'Prejoining' END "
				         +" FROM HRMS_REC_PRECHECK  	"
				         +" INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PRECHECK.PRE_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) "
				         +" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PRECHECK.PRE_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
				         +" left join HRMS_REC_OFFER on(HRMS_REC_OFFER.OFFER_REQS_CODE=HRMS_REC_PRECHECK.PRE_REQS_CODE and "
				         +" HRMS_REC_OFFER.OFFER_CAND_CODE=HRMS_REC_PRECHECK.PRE_CAND_CODE )"
				         +" INNER JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)" 
				         +" ORDER BY PRE_CODE ";
	     bean.setConductflag(true);
	     }
			logger.info("ConductQuery"+ConductQuery);
		try {
			Object obj[][] = getSqlModel().getSingleResult(ConductQuery);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("abc", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("xyz", Integer.parseInt(String.valueOf(pageIndex[3])));
			
		   if(pageIndex[4].equals("1"))
				bean.setMyPage("1");
		  
		
			ArrayList<Object> list = new ArrayList<Object>();
			
			
			
			if (obj != null && obj.length > 0) {

			//	for (int i = 0; i < obj.length; i++) {
				  for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){

					PreOfferProcessing bgcheck = new PreOfferProcessing();
					bgcheck.setLcandidate(String.valueOf(obj[i][0]));
					bgcheck.setLreqName(String.valueOf(obj[i][1]));
					bgcheck.setLposition(checkNull(String.valueOf(obj[i][2])));
					bgcheck.setLofferstatus(checkNull(String.valueOf(obj[i][3])));

					bgcheck.setLoffercode(checkNull(String.valueOf(obj[i][4])));
					bgcheck.setLreqcode(String.valueOf(obj[i][5]));
					bgcheck.setLcancode(String.valueOf(obj[i][6]));
					bgcheck.setResume(String.valueOf(obj[i][7]));
					

					if (String.valueOf(status) != "P")
						bgcheck.setLchecklistType(String.valueOf(obj[i][8]));

					list.add(bgcheck);
				}
				bean.setBgpendingChkList(list);
				bean.setNoData("false");

			} else {
				bean.setNoData("true");

			}
			bean.setBgpendinglistLength(String.valueOf(list.size()));
		} catch (Exception e) {
			
		}
	 }

	/**
	 * @param bgcheck
	 * purpose:setting conducted candidate details
	 */
	public void conducted(PreOfferProcessing bgcheck) {
		String Reqquery = " select REQS_CODE,REQS_NAME,HRMS_RANK.RANK_NAME,decode(REQS_STATUS,'O','Open','C','Close'),"
			+" HRMS_DIVISION.DIV_NAME,hrms_center.CENTER_NAME,HRMS_DEPT.DEPT_NAME"	
			+" from HRMS_REC_REQS_HDR "
			+" inner join HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
			+" inner join hrms_center ON (hrms_center.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
			+" inner join HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
			+" inner join HRMS_RANK on(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
			+" Where REQS_CODE="+bgcheck.getChkreqcode();
		
		String Candquery = "SELECT CAND_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME from HRMS_REC_CAND_DATABANK "
						   +" Where CAND_CODE="+bgcheck.getChkcandidatecode();
		
		String offerQuery="select DECODE(OFFER_STATUS, 'I ', 'Issued', 'OA', 'Accepted', 'OR', 'Rejected', 'C ', 'Canceled')"
							+" from hrms_rec_offer where OFFER_REQS_CODE="+bgcheck.getChkreqcode()
							 +" and OFFER_CAND_CODE="+bgcheck.getChkcandidatecode();
		
		logger.info("Reqquery Query...!!"+Reqquery);
		logger.info("Candquery Query...!!"+Candquery);
		
		Object Reqdtl[][];
		Object Candtl[][];
		try {
			Reqdtl = getSqlModel().getSingleResult(Reqquery);
			Candtl = getSqlModel().getSingleResult(Candquery);
			Object offer[][] = getSqlModel().getSingleResult(offerQuery);
			
			
			
			if(Reqdtl!=null && Reqdtl.length>0)
			{
				bgcheck.setReqCode(String.valueOf(Reqdtl[0][0]));
				bgcheck.setReqName(String.valueOf(Reqdtl[0][1]));
				bgcheck.setPosition(checkNull(String.valueOf(Reqdtl[0][2])));
				//bgcheck.setOfferStatus(String.valueOf(Reqdtl[0][3]));
				bgcheck.setDivision(String.valueOf(Reqdtl[0][4]));
				bgcheck.setBranch(String.valueOf(Reqdtl[0][5]));
				bgcheck.setDepartment(String.valueOf(Reqdtl[0][6]));
				
			}
			if(Candtl!=null && Candtl.length>0)
			{
				bgcheck.setCandidateCode(String.valueOf(Candtl[0][0]));
				bgcheck.setCandidateName(String.valueOf(Candtl[0][1]));
			}
			
			if(offer!=null && offer.length>0)
			{
			bgcheck.setOfferStatus(checkNull(String.valueOf(offer[0][0])));
			}
						
		} catch (Exception e) {
			
		}
		
		
		//return res;
		
		
		
	}

	/**
	 * @param bgcheck
	 * purpose:setting selected record details.
	 */
	public void f9searchdtl(PreOfferProcessing bgcheck) {
	
		
		try {
			String Reqquery = " select REQS_CODE,REQS_NAME,RANK_NAME,decode(REQS_STATUS,'O','Open','C','Close'),"
					+ " HRMS_DIVISION.DIV_NAME,hrms_center.CENTER_NAME,HRMS_DEPT.DEPT_NAME"
					+ " from HRMS_REC_REQS_HDR "
					+ " inner join HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " inner join hrms_center ON (hrms_center.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " inner join HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " inner join HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " Where REQS_CODE=" + bgcheck.getReqCode();
			String Candquery = "SELECT CAND_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME from HRMS_REC_CAND_DATABANK "
					+ " Where CAND_CODE=" + bgcheck.getCandidateCode();
			
			
			String offerQuery="select DECODE(OFFER_STATUS, 'I ', 'Issued', 'OA', 'Accepted', 'OR', 'Rejected', 'C ', 'Canceled')"
				+" from hrms_rec_offer where OFFER_REQS_CODE="+bgcheck.getReqCode()
				 +" and OFFER_CAND_CODE="+bgcheck.getCandidateCode();
			
			
			
			Object Reqdtl[][] = getSqlModel().getSingleResult(Reqquery);
			Object Candtl[][] = getSqlModel().getSingleResult(Candquery);
			Object offer[][] = getSqlModel().getSingleResult(offerQuery);
			if (Reqdtl != null && Reqdtl.length > 0) {
				logger.info("with in if .....!!!");
				bgcheck.setReqCode(String.valueOf(Reqdtl[0][0]));
				bgcheck.setReqName(String.valueOf(Reqdtl[0][1]));
				bgcheck.setPosition(String.valueOf(Reqdtl[0][2]));
				//bgcheck.setOfferStatus(checkNull(String.valueOf(offer[0][0])));
				bgcheck.setDivision(String.valueOf(Reqdtl[0][4]));
				bgcheck.setBranch(String.valueOf(Reqdtl[0][5]));
				bgcheck.setDepartment(String.valueOf(Reqdtl[0][6]));

			}
			if (offer != null && offer.length > 0) {
			bgcheck.setOfferStatus(checkNull(String.valueOf(offer[0][0])));
			}else
				bgcheck.setOfferStatus("");
			if (Candtl != null && Candtl.length > 0) {
				logger.info("with in Candtl if .....!!!");
				bgcheck.setCandidateCode(String.valueOf(Candtl[0][0]));
				bgcheck.setCandidateName(String.valueOf(Candtl[0][1]));
			}
			String BgQuery = "Select PRE_CHECK_TYPE ,decode(PRE_CHECK_TYPE,'I','In House','O','Out Source'),DECODE(PRE_CHECK_LIST,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Prejoining CheckList'),"
					+ "  PRE_COMMENTS,PRE_AGENCYCODE FROM HRMS_REC_PRECHECK where PRE_CODE="
					+ bgcheck.getBgcheckCode();
			logger.info("with the BgQuery .....!!!" + BgQuery);
			Object BgQuerydtl[][] = getSqlModel().getSingleResult(BgQuery);
			
			bgcheck.setBgCheckType(String.valueOf(BgQuerydtl[0][0]));
			bgcheck.setDupbgCheckType(String.valueOf(BgQuerydtl[0][1]));
			bgcheck.setDupcheckListType(String.valueOf(BgQuerydtl[0][2]));
			bgcheck.setOverallComments(checkNull(String
					.valueOf(BgQuerydtl[0][3])));
			
			logger.info("outsource Details...  .....!!!"
					+ bgcheck.getOutsourceAgencyName());
			if (String.valueOf(BgQuerydtl[0][0]).equals("O")) {
				if (String.valueOf(BgQuerydtl[0][4]) != "") {
					String outsourceQuery = "SELECT REC_CODE,REC_PARTNERNAME FROM HRMS_REC_PARTNER where REC_CODE="
							+ String.valueOf(BgQuerydtl[0][4]);

					logger.info(outsourceQuery);
					Object outsourcedtl[][] = getSqlModel().getSingleResult(
							outsourceQuery);
					if (outsourcedtl != null && outsourcedtl.length > 0) {
						bgcheck.setOutsourceAgencyCode(String
								.valueOf(outsourcedtl[0][0]));
						bgcheck.setOutsourceAgencyName(checkNull(String
								.valueOf(outsourcedtl[0][1])));
					}

				}
			} else {
				bgcheck.setOutsourceAgencyCode("");
				bgcheck.setOutsourceAgencyName("");

			}
			setCheckList(bgcheck);
		} catch (Exception e) {
			
		}
		
		
	}
public String checkNull(String result) {
		
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}			
	/**
	 * @param bgcheck
	 * @return boolean after deleting PreOfferProcessing application
	 */
	public boolean deletepreoffer(PreOfferProcessing bgcheck) {
		
		String delBg="delete from HRMS_REC_PRECHECK where PRE_CODE ="+bgcheck.getBgcheckCode();
		String delBgDtl="delete from HRMS_REC_PRECHECK_DTL where PRE_CODE	="+bgcheck.getBgcheckCode();
		
		boolean result;
		try {
			result = getSqlModel().singleExecute(delBgDtl);
			if (result) {
				result = getSqlModel().singleExecute(delBg);

			}
			return result;
		} catch (Exception e) {
			
		}
		return false;
	}		
	
    /*
     *@param bgcheck
     *purpose:setting selected PreOfferProcessing details.
     */
    public void bgCheckRecord(PreOfferProcessing bgcheck) {
    	  
    	  try {
			String bgcheckquery = " SELECT PRE_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME as name,"
					+ " HRMS_REC_REQS_HDR.REQS_NAME,PRE_REQS_CODE,PRE_CAND_CODE,PRE_CHECK_LIST,"
					+
					//	"PRE_CHECK_TYPE," +
					" HRMS_REC_PARTNER.REC_PARTNERNAME,PRE_AGENCYCODE "
					+ " 	FROM HRMS_REC_PRECHECK "
					+ " 	INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PRECHECK.PRE_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " 	INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PRECHECK.PRE_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " 	left JOIN HRMS_REC_PARTNER ON (HRMS_REC_PARTNER.REC_CODE=HRMS_REC_PRECHECK.PRE_AGENCYCODE)"
					+ " WHERE PRE_CODE=" + bgcheck.getBgcheckCode();
			Object bgRecorddtl[][] = getSqlModel()
					.getSingleResult(bgcheckquery);
			if (bgRecorddtl != null && bgRecorddtl.length > 0) {
				bgcheck.setBgcheckCode(String.valueOf(bgRecorddtl[0][0]));
				bgcheck.setCandidateName(String.valueOf(bgRecorddtl[0][1]));
				bgcheck.setReqName(String.valueOf(bgRecorddtl[0][2]));
				bgcheck.setReqCode(String.valueOf(bgRecorddtl[0][3]));
				bgcheck.setCandidateCode(String.valueOf(bgRecorddtl[0][4]));
				bgcheck.setCheckListType(String.valueOf(bgRecorddtl[0][5]));
				bgcheck.setOutsourceAgencyName(checkNull(String.valueOf(bgRecorddtl[0][6])));
				bgcheck.setOutsourceAgencyCode(checkNull(String.valueOf(bgRecorddtl[0][7])));

				f9searchdtl(bgcheck);
			}
		} catch (Exception e) {
			
		}
		
		    
		
	}		
		
		
	
    
    public void getRecords(PreOfferProcessing bean,HttpServletRequest request){
		try{
		
		String query = " SELECT NVL(INDUS_NAME,' '),NVL(INDUS_ABBRIVIATION,' '),CASE WHEN INDUS_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(INDUS_DESCRIPTION,' '),INDUS_CODE  FROM HRMS_INDUS_TYPE"
			+" ORDER BY INDUS_CODE";

		Object[][] data = getSqlModel().getSingleResult(query);
		
	  
		ArrayList<Object> obj=new ArrayList<Object>();
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("abc", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("xyz", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
	  
	  for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
                 //setting 
		//  IndusMaster  bean1 = new IndusMaster();
		
	     //obj.add(bean1);
		  
		  
	  }
	
	//  bean.setIndustryList(obj);
	  
	  
	}catch(Exception e){
		e.printStackTrace();
	}
	
	}	
    
    
		
}
	
	
	
	
	
	
	
	
	


