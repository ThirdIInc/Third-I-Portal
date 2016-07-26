package org.paradyne.model.PAS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.ApprFormSection;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.struts.action.extraWorkBenefits.ExtraWorkBenifitCalculationAction;

import com.lowagie.text.Font;

public class ApprFormSectionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ApprFormSectionModel.class);

	public boolean checkSectionsEnabeled(String apprId, String tmpId,String phaseId){
		
		String sql1="SELECT APPR_TRN_FLAG,APPR_AWARD_FLAG, APPR_DISCIPLINARY_FLAG, APPR_CAREER_FLAG, APPR_TRN_FLAG"
			+" FROM PAS_APPR_TEMPLATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
		
		String sql2="SELECT APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,APPR_SECTION_TYPE FROM PAS_APPR_COMMON_SECTION" 
			+" WHERE APPR_ID = ? AND APPR_TEMPLATE_ID= ? AND APPR_PHASE = ? AND APPR_SECTION_TYPE  IN('T','A','D','C')";

		Object param1[] = new Object[2];
		param1[0] = apprId;
		param1[1] = tmpId;
		
		Object param2[] = new Object[3];
		param2[0] = apprId; 
		param2[1] = tmpId;
		param2[2] = phaseId;
		
		Object data1[][] = getSqlModel().getSingleResult(sql1,param1);//AWARD/DISCIPLINARY/CAREER APPLICABILITY
		Object data2[][] = getSqlModel().getSingleResult(sql2,param2);//PHASE WISE AWARD/DISCIPLINARY/CAREER APPLICABILITY
		
		//1. CHECK OVERALL APPLICABILITY OF training/award/disciplinary/career
		if(data1!=null && data1.length>0){
			if(data1[0][0].equals("N") &&
					data1[0][1].equals("N") &&
					data1[0][2].equals("N") &&
					data1[0][3].equals("N")){//If no other sections like training/award/disciplinary/career are enabled 
				return false;
			}			
		}
		
		//2. CHECK PHASE WISE APPLICABILITY OF training/award/disciplinary/career
		int disableCount=0;
		if(data2!=null && data2.length>0){ // add by deepak
			for(int j=0;j<data2.length;j++){
				if(data2[j][0].equals("N")){					
					disableCount++;//4
				}
			}
		}
		
		
		//If all sections (training/award/discilpinary/career are disabled for this phase)
		//then provide finish button in this screen itself
		if(disableCount==4){
				return false;
			}
		return true;
	}
	
	// get the visible section list for selected appraisal and phase
	// and set section code of first section
	public void getSectionList(ApprFormSection bean) {
		try {
			Object[] paramObj = new Object[3];
			paramObj[0] = bean.getApprId();
			paramObj[1] = bean.getTemplateCode();
			paramObj[2] = bean.getPhaseCode();

			String sectionList = "";
			Object[][] sectionData = getSqlModel().getSingleResult(getQuery(1),paramObj);
			if (sectionData != null && sectionData.length > 0) {
				for (int i = 0; i < sectionData.length; i++) {
					if (i == 0)
						sectionList = String.valueOf(sectionData[i][0]);
					else
						sectionList = sectionList + ","+ String.valueOf(sectionData[i][0]);
				}
				bean.setSectionList(sectionList);
				bean.setSectionCode(checkNull(String.valueOf(sectionData[0][0])));
			}else
				bean.setSectionList(null);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	// get the section details like question, answer type , weightage and set these as per 
	// the applicability of comment and rating
	public boolean getSectionDetails(ApprFormSection bean) {
		try {
			Object[] paramObj = new Object[4];

			paramObj[0] = bean.getApprId();
			paramObj[1] = bean.getTemplateCode();
			paramObj[2] = bean.getPhaseCode();
			paramObj[3] = bean.getSectionCode();

			// get the group from appraisal mapping in which this employee belongs 
			Object [][] groupObj = getSqlModel().getSingleResult(getQuery(26),new Object[]{bean.getEmpId(),bean.getApprId()});

			// get section name, visibility, rating applicability , comment applicability and set it 
			Object[][] sectionData = getSqlModel().getSingleResult(getQuery(2),paramObj);

			if (sectionData != null && sectionData.length != 0) {

				bean.setSectionName(checkNull(String.valueOf(sectionData[0][1])));
				bean.setVisibilityFlag(checkNull(String.valueOf(sectionData[0][2])));
				// set the rating and comment applicability flags
				if (String.valueOf(sectionData[0][3]).equals("Y")){
					bean.setRatingFlag("true");
				}else
					bean.setRatingFlag("false");

				if (String.valueOf(sectionData[0][4]).equals("Y"))
					bean.setCommentFlag("true");
				else
					bean.setCommentFlag("false");
			}
			
			Object sectionObj [] = new Object[5];
			sectionObj[0] = bean.getApprId();
			sectionObj[1] = bean.getTemplateCode();
			sectionObj[2] = bean.getPhaseCode();
			sectionObj[3] = bean.getSectionCode();
			sectionObj[4] = ""+groupObj[0][0];
			String phaseOrderQuery = "select appr_phase_order from pas_appr_phase_config where phase_id = "+bean.getPhaseCode()+" ";
			Object PhaseOrder = getSqlModel().getSingleResult(phaseOrderQuery);
			System.out.println("phase order-------------------"+PhaseOrder);
			// get the questions for the selected group and for this section and this phase
			Object quesData[][] = getSqlModel().getSingleResult(getQuery(3),sectionObj);
			
			String query="SELECT DISTINCT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT,"
				+"		NVL(APPR_QUES_COMMENTS,' '),APPR_PHASE_ORDER,APPR_QUESTION_ORDER, APPR_EVALUATOR_LEVEL,APPR_QUESTION_CODE,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+"		FROM PAS_APPR_COMMENTS "
				+"		INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_COMMENTS.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID)"
				+"		INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_COMMENTS.APPR_QUESTION_CODE" 
				+"		and  PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+bean.getSectionCode()+" and APPR_EMP_GRP_ID = "+groupObj[0][0]+" )"
				+" 		inner join hrms_emp_offc on (PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE = hrms_emp_offc.EMP_ID)"
				+"		WHERE  PAS_APPR_COMMENTS.APPR_ID= "+bean.getApprId()+" AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID = "+bean.getTemplateCode()+"  "
				+"		and APPR_EVALUATOR_CODE  != "+bean.getUserEmpId()+" and PAS_APPR_COMMENTS.APPR_EMP_ID = "+bean.getEmpId()+"  "
				+"      and PAS_APPR_COMMENTS.APPR_SECTION_ID = "+bean.getSectionCode()+" "
				+"      and PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER  < "+PhaseOrder+" "
				+"		ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,PAS_APPR_QUES_MAPPING.APPR_QUESTION_ORDER ";
						
			Object[][]subObj=getSqlModel().getSingleResult(query);
			
			if(bean.getPhaseForwardFlag().equals("false")){
				String str="";
				if(quesData!=null &&quesData.length>0){
					for (int i = 0; i < quesData.length; i++) {
						str+=quesData[i][0]+",";
					}
					System.out.println("---------String str= "+str);
					str=str.substring(0, str.length()-1);
					System.out.println("-------11--String str= "+str);
				}else{
				}
				
				System.out.println("");
				
				String queryNV="SELECT distinct APPR_QUESTION_CODE,APPR_QUES_DESC,PAS_APPR_QUES_MAPPING.APPR_QUESTION_ORDER FROM PAS_APPR_COMMENTS " 
					+"	INNER JOIN  PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE " 
					+"	AND PAS_APPR_COMMENTS.APPR_SECTION_ID = "+bean.getSectionCode()+" AND APPR_EMP_ID = "+bean.getEmpId()+")  "
					+"	INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE AND "
					+"	PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+bean.getSectionCode()+" AND APPR_EMP_GRP_ID = "+groupObj[0][0]+" )   "
					+"	WHERE  PAS_APPR_COMMENTS.APPR_ID= "+bean.getApprId()+" AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID= "+bean.getTemplateCode()+"  "
					+"	 ";				
				if(!str.equals("")){
					queryNV+=" AND APPR_QUESTION_CODE NOT IN ("+str+")";
				}
			
				queryNV+=" ORDER BY PAS_APPR_QUES_MAPPING.APPR_QUESTION_ORDER";
				
				Object quesDataNV[][] = getSqlModel().getSingleResult(queryNV);
				
				ArrayList<Object> listNV = new ArrayList<Object>();
				bean.setQuestionListNV(null);
				bean.setFlagNV("false");
				if(quesDataNV !=null && quesDataNV.length>0){
					bean.setFlagNV("true");
					for (int i = 0; i < quesDataNV.length; i++) {
						System.out.println("quesDataNV[i][1]  : "+quesDataNV[i][1]);
						ApprFormSection bean1 = new ApprFormSection();
						bean1.setQuesDescNV(String.valueOf(quesDataNV[i][1]));
						ArrayList<Object> sublistNA = new ArrayList<Object>();
						if(subObj !=null && subObj.length>0){
							for (int j = 0; j < subObj.length; j++) {
								if(String.valueOf(subObj[j][8]).equals(String.valueOf(quesDataNV[i][0]))){
									ApprFormSection subbean1 = new ApprFormSection();
									subbean1.setPrvPhaseNameNV(checkNull(String.valueOf(subObj[j][0])));
									subbean1.setPrvPhaseApprNameNV(checkNull(String.valueOf(subObj[j][9])));
									
									if(String.valueOf(subObj[j][2]).equals("-1")){
										subbean1.setPrvPhaseRatingNV("NA");
										subbean1.setPrvPhaseActualNV("NA");
										subbean1.setPrvPhaseWeightageNV("NA");
									}else{
										subbean1.setPrvPhaseRatingNV(checkNull(String.valueOf(subObj[j][2])));
										subbean1.setPrvPhaseActualNV(checkNull(String.valueOf(subObj[j][3])));
										subbean1.setPrvPhaseWeightageNV(checkNull(String.valueOf(subObj[j][1])));	
									}
									if(String.valueOf(subObj[j][4]).equals("-1"))
										subbean1.setPrvPhaseCommentsNV("NA");
									else
										subbean1.setPrvPhaseCommentsNV(checkNull(String.valueOf(subObj[j][4])));						
									sublistNA.add(subbean1);
								}
							}									
							bean1.setSubQuestionListNV(sublistNA);					
						}
						listNV.add(bean1);
					}
				}
				bean.setQuestionListNV(listNV);
			}	
			// set the question list for this section 
			ArrayList<Object> list = new ArrayList<Object>();
			bean.setQuestionList(list);
			if (quesData != null && quesData.length != 0) {
				bean.setParametersAvailable(true);
				for (int i = 0; i < quesData.length; i++) {

					ApprFormSection bean1 = new ApprFormSection();

					bean1.setQuestionCode(checkNull(String.valueOf(quesData[i][0])));
					bean1.setQuestionDesc(checkNull(String.valueOf(quesData[i][1])));
					bean1.setQuesWeight(checkNull(String.valueOf(quesData[i][3])));
					bean1.setQuesMandatory(checkNull(String.valueOf(quesData[i][5])));
					bean1.setQuesLimit(checkNull(String.valueOf(quesData[i][6])));
					bean1.setQuesRating("");
					bean1.setQuesComment("");
					int count=0;
					bean1.setFlag("false;");
						ArrayList<Object> sublist = new ArrayList<Object>();
							if(subObj !=null && subObj.length>0){
								for (int j = 0; j < subObj.length; j++) {
									if(String.valueOf(subObj[j][8]).equals(String.valueOf(quesData[i][0]))){
											count++;
										ApprFormSection subbean1 = new ApprFormSection();
										subbean1.setPrvPhaseName(checkNull(String.valueOf(subObj[j][0])));
										subbean1.setPrvPhaseApprName(checkNull(String.valueOf(subObj[j][9])));
										if(String.valueOf(subObj[j][2]).equals("-1")){
											subbean1.setPrvPhaseRating("NA");
											subbean1.setPrvPhaseActual("NA");
											subbean1.setPrvPhaseWeightage("NA");
										}else{
											subbean1.setPrvPhaseRating(checkNull(String.valueOf(subObj[j][2])));
											subbean1.setPrvPhaseActual(checkNull(String.valueOf(subObj[j][3])));
											subbean1.setPrvPhaseWeightage(checkNull(String.valueOf(subObj[j][1])));
										}
										if(String.valueOf(subObj[j][4]).equals("-1"))
											subbean1.setPrvPhaseComments("NA");
										else
											subbean1.setPrvPhaseComments(checkNull(String.valueOf(subObj[j][4])));
										sublist.add(subbean1);
									}
								}
								if(count>0){
									bean1.setFlag("true");
								}
								bean1.setSubQuestionList(sublist);
							}
						list.add(bean1);
					}
				bean.setQuestionList(list);
			}else{
				bean.setParametersAvailable(false);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return true;
	}
	//check whether next section exists or not if yes set that section code from section list 
	//to current section and also manage the PreviousExist and NextExist flags
	public boolean  getNextSection(ApprFormSection bean) {
		try {
			String current = bean.getSectionCode();
			String sectionList = bean.getSectionList();

			//logger.info("current section code" + current);

			String[] section = sectionList.split(",");
			//logger.info("sectionlength" + section.length);
			if (section != null) {
				for (int j = 0; j < section.length; j++) {
					if (current.equals(section[j])) {
						if (j < section.length - 1) {
							bean.setSectionCode(section[j + 1]);
							bean.setPreviousExist("true");
							getSectionDetails(bean);
						}
					}
					if (bean.getSectionCode().equals(
							section[section.length - 1])) {
						//logger.info("I m in  Last Page " + j);
						bean.setNextExist("false");
					}

				}
			}
			//logger.info("after next section code" + bean.getSectionCode());
			
		} catch (Exception e) {
			//e.printStackTrace();
			
		}
		return true;
	}
	//check whether previous section exists or not if yes set that section code from section list 
	//to current section and also manage the PreviousExist and NextExist flags
	public void getPreviousSection(ApprFormSection bean) {
		try {
			String current = bean.getSectionCode();
			String sectionList = bean.getSectionList();

			String[] section = sectionList.split(",");
			//logger.info("sectionlength" + section.length);
			if (section != null) {
				for (int j = 0; j < section.length; j++) {
					if (current.equals(section[j])) {
						if (j > 0) {
							bean.setSectionCode(section[j - 1]);
							bean.setNextExist("true");
						}
					}
					if (bean.getSectionCode().equals(section[0])) {
						//logger.info("I m in  first Page " + j);
						bean.setPreviousExist("false");
					}

				}
			}

			//logger.info("after next section code" + bean.getSectionCode());
			getSectionDetails(bean);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	// check whether employee had already filled data for this section and this phase and set the 
	//  empDataExist flag
	public boolean empSectionDataExists(ApprFormSection bean){
		try{
		boolean result = false;
		
		Object[] sectionObj = new Object[6];

		sectionObj[0] = bean.getApprId();
		sectionObj[1] = bean.getTemplateCode();
		sectionObj[2] = bean.getPhaseCode();
		sectionObj[3] = bean.getSectionCode();
		sectionObj[4] = bean.getEmpId();
		sectionObj[5] = bean.getUserEmpId();

		//logger.info("sectionObj[0]"+sectionObj[0]);
		//logger.info("sectionObj[1]"+sectionObj[1]);
		//logger.info("sectionObj[2]"+sectionObj[2]);
		//logger.info("sectionObj[3]"+sectionObj[3]);
		//logger.info("sectionObj[4]"+sectionObj[4]);
		//logger.info("sectionObj[4]"+sectionObj[5]);
		
		Object[][] dataObj = getSqlModel().getSingleResult(getQuery(6),
				sectionObj);
		
		//logger.info("asdasd"+dataObj.length);
		if (dataObj != null && dataObj.length != 0) {
				bean.setEmpDataExist("true");
				result = true;
		}else{
			bean.setEmpDataExist("false");
			
		}
		
		//logger.info("emp record exists-------------"+bean.getEmpDataExist());
		return result;
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
		
	}
	// get the rating combo values and set to combo as per the order
	public void setRating(ApprFormSection bean){
		HashMap mp = new HashMap();
		String maxWt ="0",rating = null; 
		try{
			Object [][] ratingTypeObj = getSqlModel().getSingleResult(getQuery(29),new Object[]{bean.getApprId()});
			if (ratingTypeObj != null && ratingTypeObj.length > 0) {
				
				bean.setRatingType(String.valueOf(ratingTypeObj[0][1]));
				bean.setFractionRating(String.valueOf(ratingTypeObj[0][0]));
			}
			String quer = " SELECT APPR_RATING_VALU,APPR_RATING_DESC FROM PAS_APPR_QUESTION_RATING_DTL  WHERE APPR_ID= "+bean.getApprId()+" ORDER BY APPR_RATING_VALU ";
			Object[][] iterator = getSqlModel().getSingleResult(quer);
			if (iterator != null && iterator.length != 0) {
			
			for (int i = 0; i < iterator.length; i++) {
				mp.put((i+1), String.valueOf(iterator[i][0]));
				maxWt = String.valueOf(iterator[iterator.length - 1][0]);
				
				if(i == 0)
					rating = String.valueOf(iterator[i][0])+" - "+String.valueOf(iterator[i][1]);
				else
					rating = rating +"<br>"+String.valueOf(iterator[i][0])+" - "+String.valueOf(iterator[i][1]);

			}
		
			//logger.info("rating combo size"+mp.size());
			}
			
		}catch(Exception e){
			////e.printStackTrace();
			mp.put(0, 0);
		}
		mp = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mp, null, true);
		bean.setMaxWeightage(maxWt);
		bean.setHashmap(mp);
		bean.setRatingNote(rating);
	}
	// retrieve employee details for this section and this phase
	public void empSectionData(ApprFormSection bean) {
		try {
			boolean result = false;
			Object[] paramObj = new Object[4];
			paramObj[0] = bean.getApprId();
			paramObj[1] = bean.getTemplateCode();
			paramObj[2] = bean.getPhaseCode();
			paramObj[3] = bean.getSectionCode();
				
			setRating(bean);
		
			// get the group from appraisal mapping in which this employee belongs 
			Object [][] groupObj = getSqlModel().getSingleResult(getQuery(26),new Object[]{bean.getEmpId(),bean.getApprId()});

			// get section name, visibility, rating applicability , comment applicability and set it 
			Object[][] sectionData = getSqlModel().getSingleResult(getQuery(2), paramObj);

			if (sectionData != null && sectionData.length != 0) {

				bean.setSectionName(checkNull(String.valueOf(sectionData[0][1])));
				bean.setVisibilityFlag(checkNull(String.valueOf(sectionData[0][2])));

				if (String.valueOf(sectionData[0][3]).equals("Y"))
					bean.setRatingFlag("true");
				else
					bean.setRatingFlag("false");

				if (String.valueOf(sectionData[0][4]).equals("Y"))
					bean.setCommentFlag("true");
				else
					bean.setCommentFlag("false");
			}
			
			Object[] sectionObj = new Object[9];
			sectionObj[0] = bean.getEmpId();
			sectionObj[1] = bean.getSectionCode();
			sectionObj[2] = bean.getSectionCode();
			sectionObj[3] = bean.getPhaseCode();
			sectionObj[4] = ""+groupObj[0][0];
			sectionObj[5] = bean.getApprId();
			sectionObj[6] = bean.getTemplateCode();
			sectionObj[7] = bean.getUserEmpId();
			sectionObj[8] = bean.getPhaseCode();

			Object[][] dataObj = getSqlModel().getSingleResult(getQuery(5),	sectionObj);

			// set the question list for this section with the rating and comments for questions given by employee
			ArrayList<Object> list = new ArrayList<Object>();
			bean.setQuestionList(list);
			
			String query="SELECT DISTINCT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT,"
					+"		NVL(APPR_QUES_COMMENTS,' '),APPR_PHASE_ORDER,APPR_QUESTION_ORDER, APPR_EVALUATOR_LEVEL,APPR_QUESTION_CODE,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME"
					+"		FROM PAS_APPR_COMMENTS "
					+"		INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_COMMENTS.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID)"
					+"		INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_COMMENTS.APPR_QUESTION_CODE" 
					+"		and  PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+bean.getSectionCode()+" and APPR_EMP_GRP_ID = "+groupObj[0][0]+" )"
					+" 		inner join hrms_emp_offc on (PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE = hrms_emp_offc.EMP_ID)"
					+"		WHERE  PAS_APPR_COMMENTS.APPR_ID= "+bean.getApprId()+" AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID = "+bean.getTemplateCode()+"  "
					+"		and APPR_EVALUATOR_CODE  != "+bean.getUserEmpId()+"  and  PAS_APPR_COMMENTS.APPR_EMP_ID = "+bean.getEmpId()+"  "
					+"      and PAS_APPR_COMMENTS.APPR_SECTION_ID = "+bean.getSectionCode()+" "
					+"		ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,PAS_APPR_QUES_MAPPING.APPR_QUESTION_ORDER ";
							
			Object[][]subObj=getSqlModel().getSingleResult(query);
			if(bean.getPhaseForwardFlag().equals("false")){		
				String str="";
				if(dataObj!=null &&dataObj.length>0){
					for (int i = 0; i < dataObj.length; i++) {
						str+=dataObj[i][0]+",";
					}
					str=str.substring(0, str.length()-1);
				}else{

				}
				String queryNV="SELECT distinct APPR_QUESTION_CODE,APPR_QUES_DESC FROM PAS_APPR_COMMENTS " 
						+"	INNER JOIN  PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE " 
						+"	AND PAS_APPR_COMMENTS.APPR_SECTION_ID = "+bean.getSectionCode()+" AND APPR_EMP_ID = "+bean.getEmpId()+")  "
						+"	INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE AND "
						+"	PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+bean.getSectionCode()+" AND APPR_EMP_GRP_ID = "+groupObj[0][0]+" )   "
						+"	WHERE  PAS_APPR_COMMENTS.APPR_ID= "+bean.getApprId()+" AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID= "+bean.getTemplateCode()+"  "
						+"	 ";				
				if(!str.equals("")){
					queryNV+=" AND APPR_QUESTION_CODE NOT IN ("+str+")";
				}
				Object quesDataNV[][] = getSqlModel().getSingleResult(queryNV);
				ArrayList<Object> listNV = new ArrayList<Object>();
				bean.setFlagNV("false");
				if(quesDataNV !=null && quesDataNV.length>0){
					bean.setFlagNV("true");
					for (int i = 0; i < quesDataNV.length; i++) {
						ApprFormSection bean1 = new ApprFormSection();
						bean1.setQuesDescNV(String.valueOf(quesDataNV[i][1]));
						listNV.add(bean1);				
				
						ArrayList<Object> sublistNA = new ArrayList<Object>();
							if(subObj !=null && subObj.length>0){
								for (int j = 0; j < subObj.length; j++) {
									if(String.valueOf(subObj[j][8]).equals(String.valueOf(quesDataNV[i][0]))){
										ApprFormSection subbean1 = new ApprFormSection();
										subbean1.setPrvPhaseNameNV(checkNull(String.valueOf(subObj[j][0])));
										subbean1.setPrvPhaseApprNameNV(checkNull(String.valueOf(subObj[j][9])));
										
										if(String.valueOf(subObj[j][2]).equals("-1")){
											subbean1.setPrvPhaseRatingNV("NA");
											subbean1.setPrvPhaseWeightageNV("NA");
											subbean1.setPrvPhaseActualNV("NA");
										}else{
											subbean1.setPrvPhaseRatingNV(checkNull(String.valueOf(subObj[j][2])));
											subbean1.setPrvPhaseWeightageNV(checkNull(String.valueOf(subObj[j][1])));
											subbean1.setPrvPhaseActualNV(checkNull(String.valueOf(subObj[j][3])));
										}
										if(String.valueOf(subObj[j][4]).equals("-1"))
											subbean1.setPrvPhaseCommentsNV("NA");
										else
											subbean1.setPrvPhaseCommentsNV(checkNull(String.valueOf(subObj[j][4])));						
										sublistNA.add(subbean1);
									}
								}									
							bean1.setSubQuestionListNV(sublistNA);					
							}
						}
					bean.setQuestionListNV(listNV);			
				}	
			}
			if(dataObj != null && dataObj.length > 0){
				bean.setParametersAvailable(true);
				for (int i = 0; i < dataObj.length; i++) {
					
					ApprFormSection bean1 = new ApprFormSection();
					bean1.setQuestionCode(checkNull(String.valueOf(dataObj[i][0])));
					bean1.setQuestionDesc(checkNull(String.valueOf(dataObj[i][1])));
					bean1.setQuesWeight(checkNull(String.valueOf(dataObj[i][3])));
					bean1.setQuesMandatory(checkNull(String.valueOf(dataObj[i][4])));
					bean1.setQuesLimit(checkNull(String.valueOf(dataObj[i][5])));
					bean1.setQuesRating(checkNull(String.valueOf(dataObj[i][6])));
					bean1.setQuesComment(checkNull(String.valueOf(dataObj[i][7])));
	
					ArrayList<Object> sublist = new ArrayList<Object>();
					int count=0;
					bean1.setFlag("false");
					if(subObj !=null && subObj.length>0){
						for (int j = 0; j < subObj.length; j++) {
							if(String.valueOf(subObj[j][8]).equals(String.valueOf(dataObj[i][0]))){
								count++;
								ApprFormSection subbean1 = new ApprFormSection();
								subbean1.setPrvPhaseName(checkNull(String.valueOf(subObj[j][0])));
								subbean1.setPrvPhaseApprName(checkNull(String.valueOf(subObj[j][9])));
								
								if(String.valueOf(subObj[j][2]).equals("-1")){
									subbean1.setPrvPhaseRating("NA");
									subbean1.setPrvPhaseWeightage("NA");
									subbean1.setPrvPhaseActual("NA");
								}else{
									subbean1.setPrvPhaseWeightage(checkNull(String.valueOf(subObj[j][1])));
									subbean1.setPrvPhaseRating(checkNull(String.valueOf(subObj[j][2])));
									subbean1.setPrvPhaseActual(checkNull(String.valueOf(subObj[j][3])));
								}
								if(String.valueOf(subObj[j][4]).equals("-1"))
									subbean1.setPrvPhaseComments("NA");
								else
									subbean1.setPrvPhaseComments(checkNull(String.valueOf(subObj[j][4])));
								
								sublist.add(subbean1);
							}
						}	
						if(count>0){
							bean1.setFlag("true");
						}
						bean1.setSubQuestionList(sublist);
					}
				list.add(bean1);
			}
		}else{
			bean.setParametersAvailable(false);
		}
		bean.setEmpDataExist("true");
		bean.setQuestionList(list);
		} catch (Exception e) {
		}
	}
	// save the section data 
	public boolean saveSection(String apprCode, String templateCode,
			String sectionCode, String phaseCode, String empCode,
			Object[] quesCode, Object[] rating, Object[] comment, Object[] weightage,String maxWt,
			String []ratingFlag,String []commentFlag,String appraiserCode) {
		//logger.info("insert data");
		//logger.info("insert data ratingFlag----------"+ratingFlag[0]);
		//logger.info("insert data commentFlag---------"+commentFlag[0]);
		String level = "1";
		Object levelObj[][] = getSqlModel().getSingleResult(getQuery(19), new Object[]{apprCode,appraiserCode,phaseCode,empCode});
		
		if (levelObj != null && levelObj.length != 0) 
			level = String.valueOf(levelObj[0][0]);
		

		try {
			boolean result = false;
			
			if (quesCode != null) {

				Object[][] insertObject = new Object[quesCode.length][12];

				for (int i = 0; i < quesCode.length; i++) {

					
					insertObject[i][0] = apprCode;
					insertObject[i][1] = templateCode;
					insertObject[i][2] = sectionCode;
					insertObject[i][3] = empCode;
					insertObject[i][4] = quesCode[i];
					if(commentFlag[0].equals("true"))
						insertObject[i][5] = checkNull(""+comment[i]);
					else
						insertObject[i][5] = "-1";	// by default comment will be blank
					
					if(ratingFlag[0].equals("true"))
						insertObject[i][6] = checkNull(String.valueOf(rating[i]));
					else
						insertObject[i][6] = "-1";	// by default will be 0
					
					insertObject[i][7] = phaseCode;
					
					if(ratingFlag[0].equals("false"))
						insertObject[i][8] = "0";
					else
						insertObject[i][8] = checkNull(String.valueOf(weightage[i]));
					
					
					//logger.info("i======== "+i+" wetghtage--- "+insertObject[i][8]);
					
					// gets the percent weighate of each question
					// i.e  3(rating given) out of  5(max rating)
					// then how much out of 10(question weightage)
					
					/*  for glodyne appraisal replace this for rating
					 * insertObject[i][9] = getPercent(""+weightage[i],""+insertObject[i][6],""+100 );
					 */
					// String quesRating = getQuesRating(""+weightage[i],""+insertObject[i][6],maxWt,quesFormuls);	
					if(ratingFlag[0].equals("true"))
						insertObject[i][9] = getPercent(""+weightage[i],""+insertObject[i][6],maxWt );
					else
						insertObject[i][9] = "0";
					
					insertObject[i][10] = appraiserCode;
					insertObject[i][11] = level;
					

				}
				//logger.info("insertObject.length" + insertObject.length);

				result = getSqlModel().singleExecute(getQuery(4), insertObject);

			}
			return result;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	// update the section data
	public boolean updateSection(String apprCode, String templateCode,
			String sectionCode, String phaseCode, String empCode,
			Object[] quesCode, Object[] rating, Object[] comment,Object[] weightage,String maxWt,
			String []ratingFlag,String []commentFlag,String appraiserCode) {
		//logger.info("update data");
		//logger.info("update data ratingFlag----------"+ratingFlag[0]);
		//logger.info("update data commentFlag---------"+commentFlag[0]);
		//logger.info("update data maxWt---------"+maxWt);
		
		try{
			String level = "1";
			
			Object levelObj[][] = getSqlModel().getSingleResult(getQuery(19), new Object[]{apprCode,appraiserCode,phaseCode,empCode});
			
			if (levelObj != null && levelObj.length != 0) 
				level = String.valueOf(levelObj[0][0]);
			
			
			
		boolean result = false;
		
		/**
		 * Added delete query for section questions delete\
		 * Lakkichand
		 */
		Object [][] delObjNew = new Object[quesCode.length][8];
		/*Object [][] delObj = new Object[1][6];
		delObj[0][0] = apprCode;
		delObj[0][1] = templateCode;
		delObj[0][2] = phaseCode;
		delObj[0][3] = sectionCode;
		delObj[0][4] = empCode;
		delObj[0][5] = level;*/
		
		String delQuery=" DELETE FROM PAS_APPR_COMMENTS  WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?  AND APPR_SECTION_ID =? AND APPR_EMP_ID=? AND " +
				" APPR_QUESTION_CODE=? AND APPR_PHASE_ID=? AND APPR_EVALUATOR_CODE=? AND APPR_EVALUATOR_LEVEL=? ";
		
		Object[][] insertObject = null;
		if (quesCode != null) {

			insertObject = new Object[quesCode.length][12];

			for (int i = 0; i < quesCode.length; i++) {

				insertObject[i][0] = apprCode;
				insertObject[i][1] = templateCode;
				insertObject[i][2] = sectionCode;
				insertObject[i][3] = empCode;
				insertObject[i][4] = String.valueOf(quesCode[i]);
				delObjNew[i][0] = apprCode;
				delObjNew[i][1] = templateCode;
				delObjNew[i][2] = sectionCode;
				delObjNew[i][3] = empCode;
				delObjNew[i][4] = String.valueOf(quesCode[i]);
				
				if(commentFlag[0].equals("true"))
					insertObject[i][5] = checkNull(String.valueOf(comment[i]));
				else
					insertObject[i][5] = "-1";
				
				//logger.info("insertObject[i][4]quesssssss"+String.valueOf(quesCode[i]));
				
				if(ratingFlag[0].equals("true"))
					insertObject[i][6] = checkNull(String.valueOf(rating[i]));
				else
					insertObject[i][6] = "-1";
				
					insertObject[i][7] = phaseCode;
					delObjNew[i][5] =phaseCode;
					if(ratingFlag[0].equals("false"))
						insertObject[i][8] = "0";
					else
						insertObject[i][8] = weightage[i];
					
				// gets the percent weighate of each question
				// i.e  3(rating given) out of  5(max rating)
				// then how much out of 10(question weightage)
					
				/*  for glodyne appraisal replace this for rating
				 * insertObject[i][9] = getPercent(""+weightage[i],""+insertObject[i][6],""+100 );
				 */
				//String quesRating = getQuesRating(""+weightage[i],""+insertObject[i][6],maxWt,quesFormuls);	
					if(ratingFlag[0].equals("true"))
						insertObject[i][9] = getPercent(""+weightage[i],""+insertObject[i][6],maxWt );
					else
						insertObject[i][9] = "0";
					
				insertObject[i][10] = appraiserCode;
				insertObject[i][11] = level;
				delObjNew[i][6] = appraiserCode;
				delObjNew[i][7] = level;
			}
				
			///logger.info("in updateeeeeee insertObject.length" + insertObject.length);
		}
		
		//logger.info("level---------------"+level);
		
		//delete and insert the section details
		// result = getSqlModel().singleExecute(getQuery(7), delObj) ;
		 result = getSqlModel().singleExecute(delQuery, delObjNew) ;
		 
		 if(result){
			 
			 getSqlModel().singleExecute(getQuery(4), insertObject) ;
		 }
		 
		
		/*Object sqlQuery [] = new Object[2];
		sqlQuery [0] = getQuery(7);
		sqlQuery [1] = getQuery(4);
		
		java.util.Vector dataVector = new Vector();
		
		dataVector.add(delObj);
		dataVector.add(insertObject);
		
		result = getSqlModel().multiExecute(sqlQuery, dataVector);*/
				
		
		return result;
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}
	
	// gets the percent weighate of each question
	// i.e  3(rating given) out of  5(max rating)
	// then how much out of 10(question weightage)
	public String getPercent(String weightage, String rating, String outOfRating){
		String percent = "0";
		try{
		//	logger.info("weightage-----"+weightage);
		//	logger.info("rating-----"+rating);
		//	logger.info("outOfRating-----"+outOfRating);
			
			if (rating == null || rating.equals("null"))
				rating="0";
			if (weightage == null || weightage.equals("null"))
				weightage="0";
			if (outOfRating == null || outOfRating.equals("null"))
				outOfRating="0";
				
			
			if(!rating.equals("0") && !weightage.equals("0") && !outOfRating.equals("0"))
				percent = ""+(((Double.parseDouble(weightage) *  Double.parseDouble(rating)))/Double.parseDouble(outOfRating));
			else
				percent ="0.0";
			
			percent = Utility.twoDecimals(percent);
			
			//logger.info("percent"+percent);
			return percent;
		}catch(Exception e){
			//e.printStackTrace();
			return "0";
		}
	}
	// get previous phase details for this section and this employee
	public void getPreviousPhaseData(String apprCode, String templateCode,
			String sectionCode,String phaseCode,String empCode, HttpServletRequest request,String appraiserCode){
		try{
		//	logger.info("apprCode-----"+apprCode);
		//	logger.info("templateCode-----"+templateCode);
		//	logger.info("sectionCode-----"+sectionCode);
		//	logger.info("phaseCode-----"+phaseCode);
		//	logger.info("empCode-----"+empCode);
			String phases = null;
			/*Object [][] phaseData = getSqlModel().getSingleResult(getQuery(9), new Object[]{apprCode,phaseCode});
						
			for(int i=0;i<phaseData.length;i++){
				if (i == 0)
					phases = String.valueOf(phaseData[i][1]);
				else
					phases = phases + ","
							+ String.valueOf(phaseData[i][1]);
			}*/
			
			Object [][] groupObj = getSqlModel().getSingleResult(getQuery(26),new Object[]{empCode,apprCode});

			
			String queryNew =" SELECT DISTINCT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME, APPR_QUES_DESC,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT,NVL(APPR_QUES_COMMENTS,' '),APPR_PHASE_ORDER,APPR_QUESTION_ORDER," 
								+" APPR_EVALUATOR_LEVEL FROM PAS_APPR_COMMENTS "  
								+" INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_COMMENTS.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID ) " 
								+" INNER JOIN  PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE "
								+" and PAS_APPR_COMMENTS.APPR_SECTION_ID = "+sectionCode+" AND APPR_EMP_ID = "+empCode+" ) " 
								+" INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE and "
								+" PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+sectionCode+" and APPR_EMP_GRP_ID = "+groupObj[0][0]+" ) "  
								+" WHERE  PAS_APPR_COMMENTS.APPR_ID= "+apprCode+" AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID= "+templateCode+"  and APPR_EVALUATOR_CODE  != "+appraiserCode
								+" ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,PAS_APPR_QUES_MAPPING.APPR_QUESTION_ORDER ";
			
			String query = " SELECT DISTINCT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME, APPR_QUES_DESC,APPR_QUES_RATING,NVL(APPR_QUES_COMMENTS,''),APPR_PHASE_ORDER,APPR_QUESTION_ORDER "
						+" FROM PAS_APPR_COMMENTS "  
						+" INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_COMMENTS.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
						+" INNER JOIN  PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE ) " 
						+" INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE) " 
						+" WHERE  PAS_APPR_COMMENTS.APPR_ID= "+apprCode+" AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID= "+templateCode+" AND   "
						+" PAS_APPR_COMMENTS.APPR_SECTION_ID= "+sectionCode+" AND APPR_EMP_ID = "+empCode+" APPR_EVALUATOR_CODE " 
						+" ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_QUESTION_ORDER ";
			
			//AND PAS_APPR_COMMENTS.APPR_PHASE_ID IN ("+phases+") "
			Object[][] dataObj = getSqlModel().getSingleResult(queryNew);
			
			//request.setAttribute("previousphaseName", phaseData);
			request.setAttribute("previousPhaseData", dataObj);
			
					
			
		}catch(Exception e){
			//e.printStackTrace();
		}
		
	}
	
	/*
	 * method name : checkNull purpose : check the string is null or not return
	 * type : String parameter : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}
	//to display the rating note
	/*public String getRating(String apprCode){
		 
		String rating =null;
		
		Object [][] ratingData = getSqlModel().getSingleResult(getQuery(27),new Object[]{apprCode});
		
		for(int i =0;i<ratingData.length;i++){
			if(i == 0)
				rating = String.valueOf(ratingData[i][0])+" - "+String.valueOf(ratingData[i][1]);
			else
				rating = rating +", "+String.valueOf(ratingData[i][0])+" - "+String.valueOf(ratingData[i][1]);
		}
		logger.info("rating value--"+rating);
		
		return checkNull(rating);
		
	}*/
	
	public boolean checkSelf(String apprId,String phaseId){
		
		String sql="SELECT APPR_IS_SELFPHASE	 FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+apprId+" AND APPR_PHASE_ID="+phaseId;
		Object data[][]= getSqlModel().getSingleResult(sql);
		if(data!=null && data.length>0){
			if(data[0][0].equals("Y")){
				return true;
			}
		}
		return false;
		
	}
	
	
	//--------------------------for forward appraisal 
	
	public boolean forwardAppraisal(HttpServletRequest request, String apprCode,String templateCode,String sectionCode,String phaseCode,String empCode,
			String appraiserCode,String apprPeriod){
		boolean result = false;
		boolean result1 = false;
		
		try{
			Object [][]nextPhaseObj = null;
			Object [][] apprGrpObj =null;
			Object [][]currentApprObj = null;
			Object [][]nextApprObj = null;
			Object [][]finalApprObj = new Object[1][3];
			boolean flag = false;
			boolean next = false;
			String level="1";
			Object[][] to_mailIds =new Object[1][1];	
			Object[][] from_mailIds =new Object[1][1];	
			
			//logger.info("apprCode-----------"+apprCode);
			//logger.info("templateCode-----------"+templateCode);
			//logger.info("phaseCode-----------"+phaseCode);
			//logger.info("empCode-----------"+empCode);
			///logger.info("appraiserCode-----------"+appraiserCode);
			
			Object [][]updateObj = new Object[1][5];
			updateObj[0][0]=apprCode;
			updateObj[0][1]=templateCode;
			updateObj[0][2]=empCode;
			updateObj[0][3]=phaseCode;
			updateObj[0][4]=appraiserCode;
			
			Object [][]forwardObj = new Object[1][13];
			forwardObj[0][0]=apprCode;
			forwardObj[0][1]=templateCode;
			forwardObj[0][2]=empCode;   
						
			// get the group from appraiser in which this employee belongs 
			apprGrpObj =getSqlModel().getSingleResult(getQuery(12), new Object[]{empCode,apprCode});
			
			//logger.info("apprGrpObj-----------"+apprGrpObj[0][0]);
			
			// get the appraisers for current phase and that selected group, appraisal
			currentApprObj = getSqlModel().getSingleResult(getQuery(13), new Object[]{String.valueOf(apprGrpObj[0][0]),
				apprCode,phaseCode});
				
			//logger.info("currentApprObj.length-----------"+currentApprObj.length);
			//logger.info("currentApprObj-----------"+currentApprObj[0][0]);
			
			if (currentApprObj != null && currentApprObj.length > 1 ) {// if there is next phase and approver for this appraisal
				
				//logger.info("currentApprObj.length>>>>>>>>>>1");
				for(int i =0;i<currentApprObj.length;i++){
					
						if(appraiserCode.equals(String.valueOf(currentApprObj[i][0]))){
														
								if(i<currentApprObj.length-1){
									// more than one appraiser are there for this phase
									// get the weight age sum and rating sum
									Object quesObj[][] = getPhaseTotal(apprCode,templateCode,empCode,phaseCode,appraiserCode);
									// get the phase rating percentage as per configuration in appraisal phase configuration
									Object phaseObj [][] = getPhaseScore(apprCode,templateCode,empCode,phaseCode,appraiserCode);
									
										forwardObj[0][3] =phaseCode;
										forwardObj[0][4] =String.valueOf(currentApprObj[i+1][0]);
										forwardObj[0][5] =phaseCode;
										forwardObj[0][6] = String.valueOf(Math.round(Double.parseDouble(""+quesObj[0][0])));
										forwardObj[0][7] = String.valueOf(quesObj[0][1]);
										forwardObj[0][8] = String.valueOf(phaseObj[0][0]);
										forwardObj[0][9] = String.valueOf(phaseObj[0][1]);
										forwardObj[0][10] = String.valueOf(phaseObj[0][2]);
										forwardObj[0][11] = appraiserCode;
										forwardObj[0][12] = String.valueOf(currentApprObj[i][1]);
										
										// update tracking table with phase forward flag = Y
										// written seperate query because one phase can have multiple approvers
										result1 =getSqlModel().singleExecute(getQuery(15), updateObj);
										// insert phase percentage in the  tracking table
										result = getSqlModel().singleExecute(getQuery(14),forwardObj);	
										
										if(result){
											
											try {
												to_mailIds[0][0]=String.valueOf(currentApprObj[i+1][0]);
												from_mailIds[0][0]=empCode;

												/*MailUtility mail=new MailUtility();
												mail.initiate(context, session);
												logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
												logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
												mail.sendMail(to_mailIds, from_mailIds,"Appraisal",apprPeriod, "P");
												mail.terminate();*/
												
												sendAppraisalMailManagerToManager( request, empCode, String.valueOf(to_mailIds[0][0]), apprCode, phaseCode,appraiserCode,phaseCode);
												
												//sendAppraisalMailApplicantToManager( request, empCode, String.valueOf(to_mailIds[0][0]), apprCode, String.valueOf(nextPhaseObj[0][0]));
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
										
										
								}else{
									// this phase is having only one appraiser
									flag = true;
									level=String.valueOf(currentApprObj[i][1]);
									break;
							}
						}
				}	
				
				//logger.info("currentApprObj.length..........."+currentApprObj.length);
				//logger.info("currentApprObj.flag..........."+flag);
			}
			// this will be true when there is no next approver for that phase
			if(flag == true || currentApprObj.length == 1){
				
				logger.info("next phase=========1");
				// get next phase order
				Object [][]phaseOrderObj = getSqlModel().getSingleResult(getQuery(10), new Object[]{phaseCode,apprCode,apprCode});
				logger.info("phaseOrderObj.========="+phaseOrderObj.length);
				
				if (phaseOrderObj != null && phaseOrderObj.length != 0) {
						// get the phase code of that order				
					nextPhaseObj = getSqlModel().getSingleResult(getQuery(11),new Object[]
					                 {String.valueOf(phaseOrderObj[0][0]),apprCode});
				
					logger.info("nextPhaseObj.========="+nextPhaseObj.length);
					if (nextPhaseObj != null && nextPhaseObj.length != 0){
						
						//get the next phase appraisers for that group and phase
						nextApprObj = getSqlModel().getSingleResult(getQuery(13),new Object[]{String.valueOf(apprGrpObj[0][0]),
						apprCode,String.valueOf(nextPhaseObj[0][0])});
						
						
						if (nextApprObj != null && nextApprObj.length != 0){
							logger.info("nextApprObj.========="+nextApprObj.length);
							
							// get the weight age sum and rating sum
							Object quesObj[][] = getPhaseTotal(apprCode,templateCode,empCode,phaseCode,appraiserCode);
							// get the phase rating percentage as per configuration in appraisal phase configuration
							Object phaseObj [][] = getPhaseScore(apprCode,templateCode,empCode,phaseCode,appraiserCode);
								
								forwardObj[0][3] =phaseCode;
								forwardObj[0][4] =String.valueOf(nextApprObj[0][0]);
								forwardObj[0][5] =String.valueOf(nextPhaseObj[0][0]);
								forwardObj[0][6] = String.valueOf(Math.round(Double.parseDouble(""+quesObj[0][0])));
								forwardObj[0][7] = String.valueOf(quesObj[0][1]);
								forwardObj[0][8] = String.valueOf(phaseObj[0][0]);
								forwardObj[0][9] = String.valueOf(phaseObj[0][1]);
								forwardObj[0][10] = String.valueOf(phaseObj[0][2]);
								forwardObj[0][11] = appraiserCode;
								forwardObj[0][12] = String.valueOf(nextApprObj[0][1]);
								
								// update tracking table with entry for next appraiser and phase
								result1 =getSqlModel().singleExecute(getQuery(15), updateObj);
								// insert phase percentage in the  tracking table
								result = getSqlModel().singleExecute(getQuery(14),forwardObj);	
								
								String phaseOver = " UPDATE PAS_APPR_TRACKING SET PHASE_ISCOMPLETE = 'Y' "
													+" WHERE APPR_ID = "+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode+" AND PHASE_ID = "+phaseCode;
								
								getSqlModel().singleExecute(phaseOver);
								
								if(result){
									
									try {
										to_mailIds[0][0]=String.valueOf(nextApprObj[0][0]);
										from_mailIds[0][0]=empCode;
										/*
										MailUtility mail=new MailUtility();
										mail.initiate(context, session);
										logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
										logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
										mail.sendMail(to_mailIds, from_mailIds,"Appraisal",apprPeriod, "P");

										mail.terminate();*/
										if(String.valueOf(nextApprObj[0][1]).equals("2"))
										{
											sendAppraisalMailApplicantToManager( request,empCode, String.valueOf(to_mailIds[0][0]), apprCode, String.valueOf(nextPhaseObj[0][0]));
											sentAppraisalMailSystemToApplicant(empCode,String.valueOf(to_mailIds[0][0]),apprCode,String.valueOf(nextPhaseObj[0][0]));
		
										}
										//else if(String.valueOf(nextApprObj[0][1]).equals("3"))
										else
										{
											System.out.println("inside this case :::::::::::: ******** :: phaseCode ::  "+phaseCode+" nextPhaseObj[0][0] : "+nextPhaseObj[0][0]);
											String phaseFarward = " UPDATE PAS_APPR_TRACKING SET NEXT_PHASE_FORWARD = 'Y' "
												+" WHERE APPR_ID = "+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode+" AND PHASE_ID = "+phaseCode;
							
											getSqlModel().singleExecute(phaseFarward);
											sendAppraisalMailManagerToManager( request, empCode, String.valueOf(to_mailIds[0][0]), apprCode, String.valueOf(nextPhaseObj[0][0]),appraiserCode,phaseCode);
										}
																			
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
						}else{
							next = true;
							logger.info("approver not defined for this phaseeeeeee");
						}
					}	
				}else{
					next = true;
					logger.info("no next phaseeeeeeeeeeeeeeeeeeee");
				}
				
			}
			// this flag will be true when there is no next phase and  next approver for this phase.
			if(next == true){
				
				logger.info("final appraisal forwardddddd");
				
				// get the weight age sum and rating sum
				Object quesObj[][] = getPhaseTotal(apprCode,templateCode,empCode,phaseCode,appraiserCode);
				// get the phase rating percentage as per configuration in appraisal phase configuration
				Object phaseObj [][] = getPhaseScore(apprCode,templateCode,empCode,phaseCode,appraiserCode);
				
								
				forwardObj[0][3] =phaseCode;
				forwardObj[0][4] ="";
				forwardObj[0][5] ="";
				forwardObj[0][6] = String.valueOf(Math.round(Double.parseDouble(""+quesObj[0][0])));;
				forwardObj[0][7] = String.valueOf(quesObj[0][1]);
				forwardObj[0][8] = String.valueOf(phaseObj[0][0]);
				forwardObj[0][9] = String.valueOf(phaseObj[0][1]);
				forwardObj[0][10] = String.valueOf(phaseObj[0][2]);
				forwardObj[0][11] = appraiserCode;
				forwardObj[0][12] = level;
				
				String sqlQuery="SELECT * FROM PAS_APPR_TRACKING WHERE APPR_ID = "+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode+" AND PHASE_ID = "+phaseCode+"  AND PHASE_APPRAISER_LEVEL ="+level;
				Object[][] data = getSqlModel().getSingleResult(sqlQuery);
				if(data!=null && data.length>0)
				{
						String updateQuery="UPDATE PAS_APPR_TRACKING SET PHASE_QUES_TOTAL_RATING = "+Math.round(Double.parseDouble(String.valueOf(quesObj[0][0])))+" , PHASE_QUES_TOTAL_WT = NVL("+quesObj[0][1]+",0) ,PHASE_PERCENTAGE = NVL("+phaseObj[0][0]+",0) ,PHASE_WEIGHTAGE= NVL("+phaseObj[0][1]+",0), PHASE_FINAL_SCORE =  NVL("+phaseObj[0][2]+",0),PHASE_INSERTED_EMPLOYEE = "+appraiserCode+" WHERE APPR_ID = "+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode+" AND PHASE_ID = "+phaseCode+"  AND PHASE_APPRAISER_LEVEL ="+level;
						result = getSqlModel().singleExecute(updateQuery);
					
				}else
				{
					// insert phase percentage in the  tracking table
					result = getSqlModel().singleExecute(getQuery(14),forwardObj);
				}
				
				
				// get final score with their value and description. 
				Object finalScoreObj[][] = getFinalScore(apprCode,templateCode,empCode,phaseCode);
				/*
				 * replace this for calculating the appraisal rating as per the  glodyne rule
				 * Object finalScoreObj[][] = getGlodyeScore(apprCode,templateCode,empCode,phaseCode);
				 */
				
				
				finalApprObj[0][0]= apprCode;
				finalApprObj[0][1]= templateCode;
				finalApprObj[0][2]= empCode;   
				// update tracking table for the last time with phase forward flag =Y and APPRAISAL_STATUS =Y  
				result1 =getSqlModel().singleExecute(getQuery(16), finalApprObj);
				
				Object [][] finalObj = new Object[1][7];
				finalObj[0][0] = apprCode;
				finalObj[0][1] = templateCode;
				finalObj[0][2] = empCode;
				finalObj[0][3] = String.valueOf(finalScoreObj[0][0]);
				finalObj[0][4] = String.valueOf(finalScoreObj[0][0]);
				finalObj[0][5] = String.valueOf(finalScoreObj[0][1]);
				finalObj[0][6] = String.valueOf(finalScoreObj[0][2]);
				
				String sqlScore="SELECT * FROM PAS_APPR_SCORE WHERE APPR_ID = "+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode;
				Object[][] dataObj = getSqlModel().getSingleResult(sqlScore);
				if(dataObj!=null && dataObj.length>0)
				{
					String updateScoreQuery="UPDATE PAS_APPR_SCORE SET APPR_SCORE = "+finalScoreObj[0][0]+" , APPR_FINAL_SCORE="+finalScoreObj[0][0]+" , APPR_FINAL_SCORE_VALUE = '"+finalScoreObj[0][1]+"' , APPR_FINAL_SCORE_DESC = '"+finalScoreObj[0][2]+"' WHERE APPR_ID = "+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode;
					boolean res = getSqlModel().singleExecute(updateScoreQuery);
				}
				else
				{
					// insert into table appraisal score
					boolean res = getSqlModel().singleExecute(getQuery(24), finalObj);
				}
				
				
				ratingCalculation(apprCode, templateCode, empCode);
				
				if(result1){
					try {
						to_mailIds[0][0]=empCode;
						from_mailIds[0][0]=appraiserCode;
						
						request.setAttribute("previewFlag", "true");

						//MailUtility mail=new MailUtility();
						//mail.initiate(context, session);
						//logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
						//logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
						
						//mail.sendMail(to_mailIds, from_mailIds,"Appraisal",apprPeriod, "A");
					/*
					 * 		sentAppraisalMailManagerToApplicant(empCode,appraiserCode,apprCode);
					 * 
					 */	
						//mail.terminate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				//logger.info("final appraisal forwardddddd"+result1);
				//logger.info("final appraisal final result"+res);
				
			}
				//logger.info("forwardObj--- "+forwardObj.length);
				//logger.info("updateObj--- "+updateObj.length);
				
					
			///logger.info("result--- "+result);
			//logger.info("result1--- "+result1);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	// return weightage sum and rating sum for that phase 
	public Object[][] getPhaseTotal(String apprCode,String templateCode,String empCode,String phaseCode,String appraiserCode){
		
			// default level will be 1
			String level = "1";
		
			// this is required when one phase having the multiple approvers.
			// retrieve the level of the appraiser for this appraisal, employee and this phase
			Object levelObj[][] = getSqlModel().getSingleResult(getQuery(19), new Object[]{apprCode,appraiserCode,phaseCode,empCode});
		
			// set the level of appraiser for this phase
			if (levelObj != null && levelObj.length != 0) 
					level = String.valueOf(levelObj[0][0]);
		
			// retrieve sum of question weight age and sum of percent rating weight age 
			
			
			Object[][]totalObj = getSqlModel().getSingleResult(getQuery(17),new Object[]{apprCode,templateCode,empCode,phaseCode,level});
			
										
			return totalObj;
		
	}
	
	public  Object[][] getPhaseScore(String apprCode,String templateCode,String empCode,String phaseCode,String appraiserCode){
		
		// return weight age sum and rating sum for that phase
		// ANS quesWt = 150
		// ANS ratWt =	75
		Object quesObj[][] = getPhaseTotal(apprCode,templateCode,empCode,phaseCode,appraiserCode);
		
		Object scoreObj[][] = new Object[1][3];
		
		// get whether phase wise weight age is ON or default 100 weight age for each phase
		Object phaseObj[][] = getSqlModel().getSingleResult(getQuery(18), new Object[]{apprCode,phaseCode});
		
		//if phase wise rating is ON get the weight age for that phase 
		// ANS 20 out of 100
		if(String.valueOf(phaseObj[0][0]).equals("Y")){
			// calculate rating out of 100.............
			// 75(percent rating) out of 150(question weight age)
			// how much out of 100(percentage)
			// ANS- 50
			scoreObj[0][0] =getPercent("100",""+Math.round(Double.parseDouble(""+quesObj[0][0])),""+quesObj[0][1]);
			//weight age of that phase in phase configuration suppose 20
			scoreObj[0][1] =phaseObj[0][1];
			// get the rating out of weight age of that phase in phase configuration
			// 50 out of 100
			// how much out of 20
			// ANS- 10
			scoreObj[0][2] =getPercent(""+phaseObj[0][1],""+scoreObj[0][0],"100");   
			
		}else{
			// phase wise rating is OFF so each phase is having default weight age
			scoreObj[0][0] =getPercent("100",""+Math.round(Double.parseDouble(""+quesObj[0][0])),""+quesObj[0][1]);
			scoreObj[0][1] =phaseObj[0][1];
			scoreObj[0][2] =scoreObj[0][0];   
			
		}
				
		return scoreObj;
		
	}
		
	public Object[][] getFinalScore(String apprCode,String templateCode,String empCode,String phaseCode){
		
		// check whether phase wise rating is ON or OFF
		Object phaseObj[][] = getSqlModel().getSingleResult(getQuery(18), new Object[]{apprCode,phaseCode});
		
		String finalScore = "0";
		
		// select MIN,MAX or AVG where more than one appraisers for one phase
		Object [][] toleranceObj = getSqlModel().getSingleResult(getQuery(20), new Object[]{apprCode});
		// phase wise rating is ON
		if(String.valueOf(phaseObj[0][0]).equals("Y")){
				
			
			if(toleranceObj[0][0].equals("1")){
				//MINIMUM
				// query will return sum of ratings 
				// MIN score where two appraiser scores for one phase
				Object minObj [][] = getSqlModel().getSingleResult(getQuery(21), new Object[]{apprCode,empCode,templateCode});
				
				finalScore = String.valueOf(minObj[0][0]);
				
				
			}
			if(toleranceObj[0][0].equals("2")){
				//MAXIMUM
				// query will return sum of ratings 
				//  MAX score where two appraiser scores for one phase
				Object maxObj [][] = getSqlModel().getSingleResult(getQuery(22), new Object[]{apprCode,empCode,templateCode});
				
				finalScore = String.valueOf(maxObj[0][0]);
			}
			if(toleranceObj[0][0].equals("3")){
				//AVERAGE
				// query will return sum of ratings
				//  AVG score where two appraiser scores for one phase
				Object avgObj [][] = getSqlModel().getSingleResult(getQuery(23), new Object[]{apprCode,empCode,templateCode});
				
				finalScore = String.valueOf(avgObj[0][0]);
	
			}
					
			
		}else{
			
					
			if(toleranceObj[0][0].equals("1")){
				//MINIMUM
				// query will return sum of ratings 
				// MIN score where two appraiser scores for one phase
				Object minObj [][] = getSqlModel().getSingleResult(getQuery(21), new Object[]{apprCode,empCode,templateCode});
				// calculate percent score for all phases
				// 350 out of 400
				// how much out of 100
				finalScore = getPercent("100",""+minObj[0][0],""+minObj[0][1]);
				
				
			}
			if(toleranceObj[0][0].equals("2")){
				//MAXIMUM
				// query will return sum of ratings 
				//  MAX score where two appraiser scores for one phase
				
				Object maxObj [][] = getSqlModel().getSingleResult(getQuery(22), new Object[]{apprCode,empCode,templateCode});
				// calculate percent score for all phases
				// 350 out of 400
				// how much out of 100
				finalScore = getPercent("100",""+maxObj[0][0],""+maxObj[0][1]);
			}
			if(toleranceObj[0][0].equals("3")){
				// AVERAGE
				// query will return sum of ratings
				//  AVG score where two appraiser scores for one phase
				Object avgObj [][] = getSqlModel().getSingleResult(getQuery(23), new Object[]{apprCode,empCode,templateCode});
				// calculate percent score for all phases
				// 350 out of 400
				// how much out of 100
				finalScore = getPercent("100",""+avgObj[0][0],""+avgObj[0][1]);
	
			}
					
		}
		
		
		logger.info("finalScore---------------"+finalScore);
		// retrieve score descriptions 
		Object [][] scoreObj =  getSqlModel().getSingleResult(getQuery(25), new Object[]{apprCode});
		
		String value= String.valueOf(Math.round(Double.parseDouble(finalScore)));
		
		Object [][]finalObj = new Object[1][3];
		if (scoreObj != null && scoreObj.length != 0) {
		for(int i=0;i<scoreObj.length;i++){
					
					finalObj[0][0] = value;
				
					if(Double.parseDouble(finalScore) >= Double.parseDouble(String.valueOf(scoreObj[i][0])))
					{	// get score value and description for final score						
						finalObj[0][1]=String.valueOf(scoreObj[i][1]);
						finalObj[0][2]=String.valueOf(scoreObj[i][2]);
						
						break;
					}
						
			}
		}
		//logger.info("score--------------"+finalObj[0][0]);
		///logger.info("score value----------"+finalObj[0][1]);
		//logger.info("scoredesc-----------"+finalObj[0][2]);
		return finalObj;
	}
	
	public Object [][] getGlodyeScore(String apprCode,String templateCode,String empCode,String phaseCode){
		
		Object [][]finalObj = new Object[1][3];
		String finalScore = "";
		int score=0;
		int weightage=0;
		String phaseQuery = " SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = "+apprCode+" AND APPR_PHASE_STATUS='A'  AND APPR_PHASE_WEIGHT_AGE != 0 ";
		
		Object phaseCodeObj [][] = getSqlModel().getSingleResult(phaseQuery);
		
		if (phaseCodeObj != null && phaseCodeObj.length != 0) {
			for(int i=0;i<phaseCodeObj.length;i++){
				
				String scoreQuery = " SELECT ROUND(NVL(SUM(APPR_QUES_PERCENT_WT),0)),ROUND(NVL(SUM(APPR_QUES_WEIGHTAGE),0)) "
									+" FROM PAS_APPR_COMMENTS " 
									+" WHERE APPR_ID = "+apprCode+" AND APPR_EMP_ID = "+empCode+" AND APPR_PHASE_ID = "+phaseCodeObj[i][0] +" AND APPR_EVALUATOR_LEVEL = "
									+" (SELECT MAX(APPR_EVALUATOR_LEVEL) FROM PAS_APPR_COMMENTS "
									+" WHERE APPR_ID = "+apprCode+" AND APPR_EMP_ID = "+empCode+" AND APPR_PHASE_ID = "+phaseCodeObj[i][0]+")";
				
				
				Object [][] scoreObj = getSqlModel().getSingleResult(scoreQuery);
				
				score = score + Integer.parseInt(String.valueOf(scoreObj[0][0]));
				weightage = weightage + Integer.parseInt(String.valueOf(scoreObj[0][1]));
			}
			
		}	
			//logger.info("getGlodyeScore wieghtage-----------"+weightage);
			//logger.info("getGlodyeScore score-----------"+score);
			
			finalScore = getPercent("100",""+score,""+weightage);
			
			
			//logger.info("getGlodyeScore finalScore-----------"+finalScore);
			
			Object [][] scoreObj =  getSqlModel().getSingleResult(getQuery(25), new Object[]{apprCode});
			
			String value= String.valueOf(Math.round(Double.parseDouble(finalScore)));
			
			
			if (scoreObj != null && scoreObj.length != 0) {
			for(int i=0;i<scoreObj.length;i++){
						
						finalObj[0][0] = value;
					
						if(Double.parseDouble(finalScore) >= Double.parseDouble(String.valueOf(scoreObj[i][0])))
						{	// get score value and description for final score						
							finalObj[0][1]=String.valueOf(scoreObj[i][1]);
							finalObj[0][2]=String.valueOf(scoreObj[i][2]);
							
							break;
						}
							
				}
			}
			//logger.info("getGlodyeScore score--------------"+finalObj[0][0]);
			//logger.info("getGlodyeScore score value----------"+finalObj[0][1]);
			//logger.info("getGlodyeScore scoredesc-----------"+finalObj[0][2]);
			
		
		return finalObj;
	}

	public String getApprover(String phaseCode, String empCode, String apprCode) {
		String approver = "";
		try {
			String query = " SELECT NEXT_APPRAISER FROM PAS_APPR_TRACKING WHERE APPR_ID="+apprCode+" AND EMP_ID="+empCode+
			" AND PHASE_ID="+phaseCode+" AND NEXT_PHASE_FORWARD='N' ";
			Object[][] data = getSqlModel().getSingleResult(query);
			
			if(data != null && data.length > 0) {
				approver = String.valueOf(data[0][0]);
			}
		} catch(Exception e) {
			logger.error(e);
		}
		return approver;
	}
	
	public void ratingCalculation(String apprCode,String templateCode,String empCode){
			
						
			String query =" SELECT APPR_QUES_FORMULS,APPR_SCORE_FORMULA,APPR_PREV_RATING_FORMULA FROM PAS_RATING WHERE APPR_ID ="+apprCode+" AND APPR_TEMPALTE_CODE ="+templateCode;
			//select the question formula and score formula from rating defination.
			Object [][] rateObj = getSqlModel().getSingleResult(query);
			
			if(rateObj == null && rateObj.length == 0)
				rateObj = new Object[][]{{0,0}};
			
			if(rateObj != null && rateObj.length > 0){
				
				String quesFormula = String.valueOf(rateObj[0][0]);
				String scoreFormula = String.valueOf(rateObj[0][1]);
				String prevScoreFormula=String.valueOf(rateObj[0][2]);
				System.out.println("prevScoreFormula :::::: "+prevScoreFormula);
				
				String rateQuery ="SELECT APPR_RATING_TYPE,APPR_MAX_RATING FROM PAS_APPR_QUESTION_RATING_HDR WHERE APPR_ID = "+apprCode;
				//select ratin gtype and phase max weightage from phase config
				Object[][] obj = getSqlModel().getSingleResult(rateQuery);
				
				String ratingType = String.valueOf(obj[0][0]);
				String MaxWeightage = String.valueOf(obj[0][1]);
				
				String phaseRatequery =" SELECT APPR_PHASE_SCORE_FORMULA,PHASE_ID FROM PAS_RATING_PHASE WHERE APPR_ID ="+apprCode+" AND APPR_TEMPLATE_CODE = "+templateCode;
				//select phase formula from phase rating formula
				Object [][] phaseRateObj = getSqlModel().getSingleResult(phaseRatequery);
				
				String phaseQuery =" SELECT APPR_PHASE_ID,APPR_PHASEWISE_RATING,APPR_PHASE_WEIGHT_AGE FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID ="+apprCode+" AND APPR_PHASE_STATUS ='A' ORDER BY APPR_PHASE_ORDER "; 
				//select phase name and their weightage
				Object [][] phaseObj = getSqlModel().getSingleResult(phaseQuery);
				HashMap<String,String> phaseMap = new HashMap<String,String>();	
				HashMap<String,String> prevApprMap = new HashMap<String,String>();
				double scoreTotal=0;
				if (phaseObj != null && phaseObj.length > 0 &&
						phaseRateObj != null && phaseRateObj.length > 0 ) {
					
					String MAX_RATING="0";
					String PHASE_WEIGHTAGE="0";
					String P_TOTAL_QUES="0";
					String phaseFormula="";
					if(ratingType.equals("perc"))
						MAX_RATING="100";
					else
						MAX_RATING=MaxWeightage;
					
					String appraiserGrpQuery=" SELECT PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER_GRP_DTL " 
						+" LEFT JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID ) "
						+" WHERE APPR_APPRAISEE_ID = "+empCode+" AND APPR_ID = "+apprCode ;
					
					// get the group from appraiser in which this employee belongs 
					Object [][] apprGrpObj =getSqlModel().getSingleResult(appraiserGrpQuery);
					
					for(int i=0;i<phaseObj.length;i++){
						
						for (int j = 0; j < phaseRateObj.length; j++) {
							
							if(String.valueOf(phaseObj[i][0]).equals(String.valueOf(phaseRateObj[j][1]))){
								phaseFormula=String.valueOf(phaseRateObj[j][0]);
								break;
							}
						}
						PHASE_WEIGHTAGE =String.valueOf(phaseObj[i][2]);
						String phaseTotal="0";
						double phaseApprTotal=0;
						double phaseQuesWtTotal =0;
						String appraiserQuery = " SELECT APPR_APPRAISER_CODE,APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER "
							+" WHERE APPR_APPRAISER_GRP_ID = "+String.valueOf(apprGrpObj[0][0])+" AND APPR_ID = "+apprCode+" AND APPR_PHASE_ID = "+String.valueOf(phaseObj[i][0]) 
							+" ORDER BY  APPR_APPRAISER_LEVEL ";
							
						// get the appraisers for current phase and that selected group, appraisal
						Object [][] appraiserObj = getSqlModel().getSingleResult(appraiserQuery);
						
						if (appraiserObj != null && appraiserObj.length > 0 ) {
							
							HashMap<String,Double> phaseAppraiserMap = new HashMap<String,Double>();
							for(int j=0;j<appraiserObj.length;j++){
								phaseQuesWtTotal =0;
								if(String.valueOf(appraiserObj[0][0]).equals("")||String.valueOf(appraiserObj[0][0]).equals("null"))
									appraiserObj[0][0]=empCode;
								
									String quesQuery = " SELECT NVL(APPR_QUES_RATING,'0'),NVL(APPR_QUES_WEIGHTAGE,'0'),APPR_QUESTION_CODE,APPR_SECTION_ID FROM PAS_APPR_COMMENTS " 
												+" WHERE APPR_ID = "+apprCode+" AND APPR_TEMPLATE_ID = "+templateCode  
												+" AND APPR_EMP_ID = "+empCode+"  AND APPR_PHASE_ID = "+String.valueOf(phaseObj[i][0])+" AND APPR_EVALUATOR_CODE = "+String.valueOf(appraiserObj[j][0]);//+" AND APPR_QUES_WEIGHTAGE != 0";
									
									Object [][] quesObj = getSqlModel().getSingleResult(quesQuery);
									double quesTotal = 0;
									if (quesObj != null && quesObj.length > 0 ) {
										
										P_TOTAL_QUES = String.valueOf(quesObj.length); 
										Object [][] quesUpdate = new Object[quesObj.length][8];
										for (int k = 0; k < quesObj.length; k++) {
											
											try {
												String quesExp = quesFormula.replace("Q_RATING", String.valueOf(quesObj[k][0])).replace("Q_WEIGHTAGE", String.valueOf(quesObj[k][1]))
																.replace("Q_MAX_RATING", MAX_RATING);
												
												quesTotal += Utility.expressionEvaluate(quesExp);
												
												phaseQuesWtTotal += Double.parseDouble(String.valueOf(quesObj[k][1]));
												
												quesUpdate [k][0]=String.valueOf(Utility.expressionEvaluate(quesExp));//quesRating calculated
												quesUpdate [k][1]=apprCode;
												quesUpdate [k][2]=templateCode;
												quesUpdate [k][3]=empCode;
												quesUpdate [k][4]=String.valueOf(phaseObj[i][0]);
												quesUpdate [k][5]=String.valueOf(quesObj[k][2]);//quesCode
												quesUpdate [k][6]=String.valueOf(quesObj[k][3]);//sectionCOde
												quesUpdate [k][7]=String.valueOf(appraiserObj[j][0]);//evaluatorCode
												
												
											} catch (Exception e) {
												logger.info("erroe in cacluating the question total"+e);
											} 
										}
										String quesUpdateQuery = " UPDATE PAS_APPR_COMMENTS SET APPR_QUES_PERCENT_WT = ?  "
												+" WHERE APPR_ID = ? and APPR_TEMPLATE_ID = ? and APPR_EMP_ID = ?  and APPR_PHASE_ID = ? and APPR_QUESTION_CODE = ? and APPR_SECTION_ID = ? and APPR_EVALUATOR_CODE = ?"; 
											
										boolean flag = getSqlModel().singleExecute(quesUpdateQuery,quesUpdate);
									}
									
									String phaseExp = phaseFormula.replace("P_RATING_SUM", String.valueOf(quesTotal)).replace("P_WEIGHTAGE", PHASE_WEIGHTAGE)
										.replace("P_TOTAL_QUES", P_TOTAL_QUES).replace("P_QUES_WEIGHTAGE_SUM", String.valueOf(phaseQuesWtTotal));
	
									String secQuery=" SELECT APPR_SECTION_ID FROM PAS_APPR_SECTION_HDR WHERE APPR_ID= "+apprCode+" AND APPR_TEMPLATE_ID = "+templateCode+" ORDER BY APPR_SECTION_ORDER ";
									Object[][] secObj= getSqlModel().getSingleResult(secQuery);
									Object [][] tempObj= null;
									if(secObj != null && secObj.length > 0 ){
										
										tempObj= new Object[secObj.length][4];
										
										String sectionQuery =" SELECT APPR_SECTION_ID,COUNT(APPR_EMP_ID),NVL(SUM(APPR_QUES_PERCENT_WT),0),NVL(SUM(APPR_QUES_WEIGHTAGE),0) FROM PAS_APPR_COMMENTS "
											+" WHERE APPR_ID ="+apprCode+" AND APPR_TEMPLATE_ID ="+templateCode+" AND APPR_EMP_ID ="+empCode+" AND APPR_PHASE_ID = "+String.valueOf(phaseObj[i][0])
											+" AND APPR_EVALUATOR_CODE = "+String.valueOf(appraiserObj[j][0])+" GROUP BY APPR_SECTION_ID ORDER BY APPR_SECTION_ID ";//AND APPR_QUES_WEIGHTAGE != 0
										
										Object [][] sectionObj = getSqlModel().getSingleResult(sectionQuery);
									
										if(sectionObj != null && sectionObj.length > 0 ){
											
											for (int k = 0; k < secObj.length; k++) {
												
												for (int l = 0; l < sectionObj.length; l++) {
													
													if(String.valueOf(secObj[k][0]).equals(String.valueOf(sectionObj[l][0]))){
														
														tempObj[k][0] = String.valueOf(sectionObj[l][0]);
														tempObj[k][1] = String.valueOf(sectionObj[l][1]);
														tempObj[k][2] = String.valueOf(sectionObj[l][2]);
														tempObj[k][3] = String.valueOf(sectionObj[l][3]);
														
														break;
													}else{
														
														tempObj[k][0] = String.valueOf(secObj[k][0]);
														tempObj[k][1] = String.valueOf("0");
														tempObj[k][2] = String.valueOf("0");
														tempObj[k][3] = String.valueOf("0");
													}
													
												}
																							
											}
										}else{
											for (int k = 0; k < secObj.length; k++) {
												
												tempObj[k][0] = String.valueOf(secObj[k][0]);
												tempObj[k][1] = String.valueOf("0");
												tempObj[k][2] = String.valueOf("0");
												tempObj[k][3] = String.valueOf("0");
											}
										}
									}
									
									if(tempObj != null && tempObj.length > 0 ){
										
										for (int k = 0; k < tempObj.length; k++) {
										
											phaseExp = phaseExp.replace("S_RATING_SUM"+k,String.valueOf(tempObj[k][2]))
														.replace("S_QUES_WEIGHTAGE_SUM"+k,String.valueOf(tempObj[k][3]))
														.replace("S_TOTAL_QUES"+k,String.valueOf(tempObj[k][1]));
										}
									}
									
									Object [][] phaseUpdate = new Object[1][6];
									phaseApprTotal = Utility.expressionEvaluate(phaseExp);
									
									logger.info("phase expression----- P"+i+"M"+j+" -- "+phaseExp +" ------- "+phaseApprTotal);
									
										phaseUpdate [0][0]=String.valueOf(Utility.twoDecimals(phaseApprTotal));
										phaseUpdate [0][1]=apprCode;
										phaseUpdate [0][2]=templateCode;
										phaseUpdate [0][3]=empCode;
										phaseUpdate [0][4]=String.valueOf(phaseObj[i][0]);
										phaseUpdate [0][5]=String.valueOf(appraiserObj[j][0]);
										
										phaseAppraiserMap.put("P"+i+"M"+j,phaseApprTotal);
										
										String phaseUpdateQuery = " UPDATE PAS_APPR_TRACKING SET PHASE_FINAL_SCORE = ?  "
														+" WHERE APPR_ID = ? and TEMPLATE_CODE = ? and EMP_ID = ?  and PHASE_ID = ? and PHASE_INSERTED_EMPLOYEE = ?"; 

										boolean flag = getSqlModel().singleExecute(phaseUpdateQuery,phaseUpdate);
								}
								
							phaseTotal = String.valueOf(phaseAppraiserMap.get("P"+i+"M0")); 
							
							if(phaseAppraiserMap.size() > 1){
								
								String typeQuery = " SELECT APPR_RATING_TOLERANCE from PAS_APPR_QUESTION_RATING_HDR where APPR_ID = "+apprCode;
								// select MIN,MAX or AVG where more than one appraisers for one phase
								Object [][] toleranceObj = getSqlModel().getSingleResult(typeQuery);
								
								if(toleranceObj[0][0].equals("4")){
									//LAST RATING
									phaseTotal = String.valueOf(phaseAppraiserMap.get("P"+i+"M"+(phaseAppraiserMap.size() - 1)));
								}
								if(toleranceObj[0][0].equals("5")){
									//FIRST RATING
									phaseTotal = String.valueOf(phaseAppraiserMap.get("P"+i+"M0"));
								}
								
								Collection<Double> col = phaseAppraiserMap.values();
								ArrayList<Double> array = new ArrayList(col);
								Collections.sort(array);
								
								if(toleranceObj[0][0].equals("1")){
									//MINIMUM
									phaseTotal = String.valueOf(array.get(0));
								}
								if(toleranceObj[0][0].equals("2")){
									//MAXIMUM
									phaseTotal = String.valueOf(array.get(array.size()-1));
								}
								
								if(toleranceObj[0][0].equals("3")){
									//AVERAGE
									double total=0;
									for(int p=0;p<array.size();p++){
										total += Double.parseDouble(String.valueOf(array.get(p)));
									}
									phaseTotal = String.valueOf(total/array.size());
									//phaseQuesWtTotal = phaseQuesWtTotal/array.size();
								}
								
							}
														
							phaseMap.put("PHASE"+i,String.valueOf(Utility.twoDecimals(phaseTotal)));
						}
						
					}
				}else{
					if (phaseObj != null && phaseObj.length > 0 ){
						for (int j = 0; j < phaseObj.length; j++) {
							phaseMap.put("PHASE"+j,"0");
						}
					}
				}
				String prevApprQuery="SELECT 'APPR_'||PAS_APPR_SCORE.APPR_ID||'#',PAS_APPR_SCORE.APPR_FINAL_SCORE,PAS_APPR_SCORE.APPR_ID FROM PAS_APPR_SCORE "+ 									
									"WHERE PAS_APPR_SCORE.EMP_ID= "+empCode;
				System.out.println(" The Previous Apprisal Query ::::::::::::: "+prevApprQuery);
				Object [][] prevApprScoreObj = getSqlModel().getSingleResult(prevApprQuery);
				if(prevApprScoreObj!=null && prevApprScoreObj.length>0)
				{
					for(int k=0;k<prevApprScoreObj.length;k++)
					{
						prevApprMap.put(String.valueOf(prevApprScoreObj[k][0]),String.valueOf(prevApprScoreObj[k][1]));
					}
				}
				
				if(phaseMap.size() > 0){
					
					for(int i=0;i<phaseObj.length;i++){
						logger.info("phase "+i+" rating =="+phaseMap.get("PHASE"+i));
						scoreFormula = scoreFormula.replace("PHASE"+i+"_RATING",phaseMap.get("PHASE"+i));
					}
					
					/*System.out.println(" scoreFormula : :::::: before :::::: "+scoreFormula);
					
					if(prevApprMap.size() > 0)
					{
						System.out.println("prevApprMap :::  " +prevApprMap);
						for(int k=0;k<prevApprScoreObj.length;k++)
						{
							logger.info("APPR_"+prevApprScoreObj[k][2]+" rating =="+prevApprMap.get("APPR_"+prevApprScoreObj[k][2]));
							scoreFormula = scoreFormula.replace("APPR_"+prevApprScoreObj[k][2],prevApprMap.get("APPR_"+prevApprScoreObj[k][2]));
						}
						
					}*/
					
					System.out.println(" scoreFormula : :::::: after :::::: "+scoreFormula);
					
					scoreTotal = Utility.expressionEvaluate(scoreFormula);
					logger.info("ratingCalculation---------scoreTotal-------------"+scoreTotal);
					//Object [][] finalObj = getScoreDescription(apprCode, String.valueOf(scoreTotal));
					
					
					
					//Goal Competency Calculation starts here
					
					// slect calander goal_weightageflag..
					String apprGoalWeightageFlag="";
					String sqlGoalWeightFlag="SELECT nvl(APPR_GOAL_WEIGHTAGE_FLAG,'F') FROM PAS_APPR_CALENDAR WHERE APPR_ID="+apprCode;
					Object [][]goalWeightFlagObj = getSqlModel().getSingleResult(sqlGoalWeightFlag);
					if(goalWeightFlagObj!=null && goalWeightFlagObj.length>0)
					{
						apprGoalWeightageFlag=checkNull(String.valueOf(goalWeightFlagObj[0][0]));
					}
					System.out.println("apprGoalWeightageFlag :: "+apprGoalWeightageFlag);	
					
					// select goal weightages
/*					
SELECT  (NVL(FINAL_RATING,0)*APPR_MAP_WEIGHTAGE)/100,APPR_MAP_WEIGHTAGE FROM PAS_APPR_GOAL_COMP_MAP 
LEFT JOIN  HRMS_GOAL_EMP_HDR   ON (GOAL_ID=APPR_MAP_CODE  AND  EMP_ID=3)
WHERE APPR_CODE=20 AND APPR_MAP_TYPE='G'
*/
					String goalWeightQuery="SELECT  (NVL(FINAL_RATING,0)*NVL(APPR_MAP_WEIGHTAGE,0))/100,NVL(APPR_MAP_WEIGHTAGE,0) FROM PAS_APPR_GOAL_COMP_MAP "+ 
											"INNER JOIN  HRMS_GOAL_EMP_HDR   ON (HRMS_GOAL_EMP_HDR.GOAL_ID=APPR_MAP_CODE  AND  EMP_ID="+empCode+") "+
											"INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) "+
											"WHERE APPR_CODE="+apprCode+" AND APPR_MAP_TYPE='G' ORDER BY GOAL_FROM_DATE";
					Object [][]goalWeightObj = getSqlModel().getSingleResult(goalWeightQuery);
					double goal_com_weigtage=0;
					double goal_score=0;
					if(goalWeightObj!=null && goalWeightObj.length>0){
						for(int i=0;i<goalWeightObj.length;i++)
						{
							if(apprGoalWeightageFlag.equals("F")){
								if(i==0)
								{
									goal_score=Double.parseDouble(String.valueOf(goalWeightObj[i][0]));
									goal_com_weigtage=Double.parseDouble(String.valueOf(goalWeightObj[i][1]));
									break;
								}
							}
							else if(apprGoalWeightageFlag.equals("L")){
								if(i==goalWeightObj.length-1)
								{
									goal_score=Double.parseDouble(String.valueOf(goalWeightObj[i][0]));
									goal_com_weigtage=Double.parseDouble(String.valueOf(goalWeightObj[i][1]));
									break;
								}
							}
						}
						if(apprGoalWeightageFlag.equals("A")){
							String sqlAverageGoalWeight="SELECT  SUM(NVL(FINAL_RATING,0))/count(*)*(SUM(NVL(APPR_MAP_WEIGHTAGE,0))/count(*))/100, "+
														"SUM(NVL(FINAL_RATING,0))/count(*),SUM(NVL(APPR_MAP_WEIGHTAGE,0))/count(*) "+
														"FROM PAS_APPR_GOAL_COMP_MAP "+ 
														"INNER JOIN  HRMS_GOAL_EMP_HDR   ON (GOAL_ID=APPR_MAP_CODE  AND  EMP_ID="+empCode+") "+
														"WHERE APPR_CODE="+apprCode+" AND APPR_MAP_TYPE='G'";
								Object [][]avgGoalWeightObj = getSqlModel().getSingleResult(sqlAverageGoalWeight);
								if(avgGoalWeightObj!=null && avgGoalWeightObj.length>0){
									
									goal_score=Double.parseDouble(String.valueOf(avgGoalWeightObj[0][0]));
									goal_com_weigtage=Double.parseDouble(String.valueOf(avgGoalWeightObj[0][2]));
								}
							
						}
						
					}
					
					String sqlComptency="SELECT  NVL(FINAL_SCORE,0)*NVL(APPR_MAP_WEIGHTAGE,0)/100,NVL(APPR_MAP_WEIGHTAGE,0) FROM PAS_APPR_GOAL_COMP_MAP "+ 
										"left JOIN HRMS_COMP_EMP ON COMP_ID=APPR_MAP_CODE AND COMP_EMP_ID="+empCode+" "+
										"WHERE APPR_CODE="+apprCode+" AND APPR_MAP_TYPE='C' ";	
					Object [][]compWeightObj = getSqlModel().getSingleResult(sqlComptency);
					double comp_com_weigtage=0;
					double comp_score=0;
					if(compWeightObj!=null && compWeightObj.length>0){
						comp_score=Double.parseDouble(String.valueOf(compWeightObj[0][0]));
						comp_com_weigtage=Double.parseDouble(String.valueOf(compWeightObj[0][1]));
					}
					Object [][] finalObj =null;
					double apprisal_weightage=100-(goal_com_weigtage+comp_com_weigtage);
					finalObj = getScoreDescription(apprCode, String.valueOf(scoreTotal));
					String Appr_individual_Score=String.valueOf(finalObj[0][0]);
					double appr_score=Double.parseDouble(String.valueOf(finalObj[0][0]))*apprisal_weightage/100;
					Object scoreInsertObj [][] = new Object[1][13];
					finalObj = getScoreDescription(apprCode, String.valueOf(Utility.twoDecimals(appr_score+goal_score+comp_score)));
					//scoreInsertObj[0][0] = String.valueOf(finalObj[0][0]);
					scoreInsertObj[0][0] = String.valueOf(Utility.twoDecimals(appr_score+goal_score+comp_score));
					//scoreInsertObj[0][1] = String.valueOf(finalObj[0][0]);
					scoreInsertObj[0][1] = String.valueOf(Utility.twoDecimals(appr_score+goal_score+comp_score));
					scoreInsertObj[0][2] = String.valueOf(finalObj[0][1]);
					scoreInsertObj[0][3] = String.valueOf(finalObj[0][2]);
					scoreInsertObj[0][4] = String.valueOf(goal_score);
					scoreInsertObj[0][5] = String.valueOf(comp_score);
					scoreInsertObj[0][6] = String.valueOf(goal_com_weigtage);
					scoreInsertObj[0][7] = String.valueOf(comp_com_weigtage);
					scoreInsertObj[0][8] = String.valueOf(apprisal_weightage);
					scoreInsertObj[0][9] = Appr_individual_Score;
					scoreInsertObj[0][10] = apprCode;
					scoreInsertObj[0][11] = templateCode;
					scoreInsertObj[0][12] = empCode;
					
					
					
					String scoreUpdateQuery = " UPDATE PAS_APPR_SCORE SET APPR_SCORE = ? , APPR_FINAL_SCORE =? , APPR_FINAL_SCORE_VALUE =? , APPR_FINAL_SCORE_DESC = ?, APPR_SCORE_ADJUST = '0',APPR_ADJUST_FACTOR = '+' ,GOAL_SCORE=?, COMP_SCORE=?,APPR_GOAL_WEIGHTAGE=?,APPR_COMP_WEIGHTAGE=?,APPR_WEIGHTAGE=?,APPR_INDIVIDUAL_SCORE=?  "
						+"  where APPR_ID = ? and TEMPLATE_CODE = ? and EMP_ID = ? "; 

					boolean flag = getSqlModel().singleExecute(scoreUpdateQuery,scoreInsertObj);

					
					// insert into table appraisal score
					//boolean res = getSqlModel().singleExecute(getQuery(24), finalObj);
					
					System.out.println(" prevScoreFormula : :::::: before :::::: "+prevScoreFormula);
					
					if(prevScoreFormula!=null && (!prevScoreFormula.equals("null"))&& prevScoreFormula.length()>0)
					{
						String prevApprCodeval[]=prevScoreFormula.split("APPR_");
						if(prevApprCodeval!=null && prevApprCodeval.length>0)
						{
							System.out.println("First Val :: "+prevApprCodeval[0]);
							
							if(prevApprCodeval[1]!=null && prevApprCodeval[1].length()>0)
							{
								System.out.println("Second Val :: previous Appriasal Code :::  "+prevApprCodeval[1]);
								String prevAppraiscalCode="0";
								String prevApprCode[]=prevApprCodeval[1].split("#");
								if(prevApprCode!=null && prevApprCode.length>0)
								{
									prevAppraiscalCode=prevApprCode[0];
								}
								System.out.println("prevAppraiscalCode ::::::"+prevAppraiscalCode);
								String prevApprSql="SELECT * FROM PAS_APPR_SCORE WHERE APPR_ID = "+prevAppraiscalCode+" AND EMP_ID = "+empCode;
								Object [][]PrevApprEmpObj = getSqlModel().getSingleResult(prevApprSql);
								/*System.out.println("PrevApprEmpObj :: "+PrevApprEmpObj);
								System.out.println("PrevApprEmpObj.length :: "+PrevApprEmpObj.length);
							*/	if(PrevApprEmpObj!=null && PrevApprEmpObj.length>0)
								{
									prevScoreFormula=prevScoreFormula.replace("CURRENT_SCORE", String.valueOf(Utility.twoDecimals(appr_score+goal_score+comp_score)));
									//System.out.println("prevApprMap :::  " +prevApprMap+" prevApprMap.size() :: "+prevApprMap.size());
									
									if(prevApprMap.size() > 0)
									{
										//System.out.println("prevApprMap :::  " +prevApprMap);
										for(int k=0;k<prevApprScoreObj.length;k++)
										{
											logger.info("APPR_"+prevApprScoreObj[k][2]+"# rating =="+prevApprMap.get("APPR_"+prevApprScoreObj[k][2]+"#"));
											prevScoreFormula = prevScoreFormula.replace("APPR_"+prevApprScoreObj[k][2]+"#",prevApprMap.get("APPR_"+prevApprScoreObj[k][2]+"#"));
										}
										
										System.out.println(" prevScoreFormula : :::::: after :::::: "+prevScoreFormula);
										scoreTotal = Utility.expressionEvaluate(prevScoreFormula);
										logger.info("ratingCalculation-----::: prevScoreFormula :: ----scoreTotal-------------"+scoreTotal);
										
										
										
										Object finalscoreInsertObj [][] = new Object[1][7];
										finalObj = getScoreDescription(apprCode, String.valueOf(Utility.twoDecimals(scoreTotal)));
										//scoreInsertObj[0][0] = String.valueOf(finalObj[0][0]);
										finalscoreInsertObj[0][0] = String.valueOf(Utility.twoDecimals(scoreTotal));
										//scoreInsertObj[0][1] = String.valueOf(finalObj[0][0]);
										finalscoreInsertObj[0][1] = String.valueOf(Utility.twoDecimals(scoreTotal));
										finalscoreInsertObj[0][2] = String.valueOf(finalObj[0][1]);
										finalscoreInsertObj[0][3] = String.valueOf(finalObj[0][2]);					
										finalscoreInsertObj[0][4] = apprCode;
										finalscoreInsertObj[0][5] = templateCode;
										finalscoreInsertObj[0][6] = empCode;
										
										
										
										String  prevscoreUpdateQuery = " UPDATE PAS_APPR_SCORE SET APPR_SCORE = ? , APPR_FINAL_SCORE =? , APPR_FINAL_SCORE_VALUE =? , APPR_FINAL_SCORE_DESC = ?   "
											+"  where APPR_ID = ? and TEMPLATE_CODE = ? and EMP_ID = ? "; 

										 flag = getSqlModel().singleExecute(prevscoreUpdateQuery,finalscoreInsertObj);
									
								}
							}
							
						}
						
							
						}
						
					}
					
					
					
				}
				
			}
			
			
	}
	public Object[][] getScoreDescription(String apprCode,String finalScore){
		Object [][]finalObj = new Object[1][3];
		try{
			Object [][] scoreObj =  getSqlModel().getSingleResult(getQuery(25), new Object[]{apprCode});
			
			String value= String.valueOf(Math.round(Double.parseDouble(finalScore)));
			
			if (scoreObj != null && scoreObj.length != 0) {
			for(int i=0;i<scoreObj.length;i++){
						
						finalObj[0][0] = Utility.twoDecimals(String.valueOf(finalScore));
					
						if(Double.parseDouble(String.valueOf(finalObj[0][0])) >= Double.parseDouble(String.valueOf(scoreObj[i][0])))
						{	// get score value and description for final score						
							finalObj[0][1]=String.valueOf(scoreObj[i][1]);
							finalObj[0][2]=String.valueOf(scoreObj[i][2]);
							
							break;
						}
				}
			}
			logger.info("getScoreDescription score--------------"+finalObj[0][0]);
			logger.info("getScoreDescription score value----------"+finalObj[0][1]);
			logger.info("getScoreDescription scoredesc-----------"+finalObj[0][2]);
			
		}catch(Exception e){
			logger.error("error in getScoreDescription --------------"+e);
		}
		return  finalObj;
	}
	
	public void getMonthlyRating(ApprFormSection bean){
		String sectionQuery = "select PULL_MONTHLYRATING_DATA from PAS_APPR_SECTION_HDR where PULL_MONTHLYRATING_DATA ='Y' and appr_section_id = "+bean.getSectionCode();
		Object[][] sectionData = getSqlModel().getSingleResult(sectionQuery);
		if(sectionData != null && sectionData.length > 0){
			String ratingQuery = " select RATING_EMP_ID,"
				+" CASE WHEN RATING_MONTH > 2 and RATING_MONTH < 12  THEN TO_CHAR(TO_DATE(RATING_MONTH+1,'MM'),'MON')||'-'||RATING_YEAR "
				+"      WHEN RATING_MONTH = 12 THEN TO_CHAR(TO_DATE(1,'MM'),'MON')||'-'||(RATING_YEAR+1) "
				+"      WHEN RATING_MONTH < 4   THEN TO_CHAR(TO_DATE(RATING_MONTH+1,'MM'),'MON')||'-'||(RATING_YEAR)"
				+" END, "
				+" RATING FROM HRMS_EMP_MONTHLY_RATING "
				+" WHERE  RATING_EMP_ID =  "+bean.getEmpId()
				+" AND RATING > 0 "
				+" AND (( RATING_MONTH >= 3 AND RATING_YEAR = TO_CHAR(TO_DATE('"+bean.getApprStartDate()+"','dd-mm-YYYY'),'YYYY')) "
				+" OR ( RATING_MONTH <= 2 AND RATING_YEAR = (TO_CHAR(TO_DATE('"+bean.getApprStartDate()+"','dd-mm-YYYY'),'YYYY')+1) )) "
				+" ORDER BY TO_DATE(RATING_MONTH||'-'||RATING_YEAR,'MM-YYYY')";
			Object[][] data = getSqlModel().getSingleResult(ratingQuery);
			if(data != null && data.length > 0){
				ArrayList<Object> list = new ArrayList<Object>();
				bean.setShowMonthRating(true);
				double rating = 0;
				double avgRating=0;
				double rating70=0;
				for (int i = 0; i < data.length; i++) {
					ApprFormSection bean1 = new ApprFormSection();
					bean1.setRatingMonthString(String.valueOf(data[i][1]));
					bean1.setMonthRating(Utility.twoDecimals(String.valueOf(data[i][2])));
					list.add(bean1);
					rating = rating + Double.parseDouble(String.valueOf(data[i][2]));
				}
				bean.setMonthRatingList(list);
				avgRating = Math.ceil(rating/data.length);
				bean.setAvgMonthRating(String.valueOf(avgRating));
				rating70 = Math.ceil(avgRating*70/100);
				bean.setRating70(String.valueOf(rating70));
			}else{
				bean.setShowMonthRating(false);
				bean.setAvgMonthRating("0");
				bean.setRating70(String.valueOf("0"));
			}
		}else{
			bean.setShowMonthRating(false);
		}
	}
	
	public void sentAppraisalMailSystemToApplicant(String applicantId,String approverId,String appraisalId, String phaseCode)
	{
		logger.info("############# Applicant Id ##################"+applicantId);
		logger.info("############# Approver ID ##################"+approverId);
		
		ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
		processAlerts.initiate(context, session);

		//processAlerts.removeProcessAlertAppraisal(appraisalId, applicantId,phaseCode);
		
		EmailTemplateBody templateAppraisalSubmission = new EmailTemplateBody();
		templateAppraisalSubmission.initiate(context, session);
		templateAppraisalSubmission.setEmailTemplate("Mail to employee regarding appraisal application submission");
		templateAppraisalSubmission.getTemplateQueries();
		
		/*try {
			// get the query as per number
			EmailTemplateQuery templateQueryForApproval1 = templateAppraisalSubmission
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQueryForApproval1.setParameter(1, approverId);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval2 = templateAppraisalSubmission
					.getTemplateQuery(2);// To EMAIL
			templateQueryForApproval2.setParameter(1, applicantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval3 = templateAppraisalSubmission
			.getTemplateQuery(3);// To EMAIL
			templateQueryForApproval3.setParameter(1, approverId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval4 = templateAppraisalSubmission
			.getTemplateQuery(4);// To EMAIL
			templateQueryForApproval4.setParameter(1, applicantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		templateAppraisalSubmission.configMailAlert();
		
		String alertLink = "/pas/ApprFormGeneralInfo_getApprStartDetails.action";
		String linkParam ="apprId="+appraisalId+
							"&empId="+applicantId;
		/*String linkParam = "apprId="+appraisalId+
			"&empId="+empCode+"&phaseCode="+phaseCode;*/
		//String approverId = toMailId;
		String level="0";
		try {
			templateAppraisalSubmission.sendProcessManagerAlert("", "Appraisal", "I",
					appraisalId, level, linkParam, alertLink,
					"Pending",applicantId , "",
					"", applicantId, applicantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// call for sending the email
		templateAppraisalSubmission.sendApplicationMail();

		// clear the template
		templateAppraisalSubmission.clearParameters();

		// terminate template
		templateAppraisalSubmission.terminate();
	}
	
	public void sentAppraisalMailManagerToApplicant(String applicantId,String approverId,String appraisalId, String phaseCode)
	{
		logger.info("############# Applicant Id ##################"+applicantId);
		logger.info("############# Approver ID ##################"+approverId);
		
		ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
		processAlerts.initiate(context, session);

		processAlerts.removeProcessAlertAppraisal(appraisalId, applicantId,phaseCode);
		
		EmailTemplateBody templateAppraisalSubmission = new EmailTemplateBody();
		templateAppraisalSubmission.initiate(context, session);
		templateAppraisalSubmission.setEmailTemplate("Appraisal Manager To Applicant");
		templateAppraisalSubmission.getTemplateQueries();
		
		try {
			// get the query as per number
			EmailTemplateQuery templateQueryForApproval1 = templateAppraisalSubmission
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQueryForApproval1.setParameter(1, approverId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval2 = templateAppraisalSubmission
					.getTemplateQuery(2);// To EMAIL
			templateQueryForApproval2.setParameter(1, applicantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval3 = templateAppraisalSubmission
			.getTemplateQuery(3);// To EMAIL
			templateQueryForApproval3.setParameter(1, approverId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval4 = templateAppraisalSubmission
			.getTemplateQuery(4);// To EMAIL
			templateQueryForApproval4.setParameter(1, applicantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		templateAppraisalSubmission.configMailAlert();
		
		String alertLink = "/pas/ApprFormGeneralInfo_getApprStartDetails.action";
		String linkParam ="apprId="+appraisalId+
							"&empId="+applicantId;
		/*String linkParam = "apprId="+appraisalId+
			"&empId="+empCode+"&phaseCode="+phaseCode;*/
		//String approverId = toMailId;
		String level="0";
		try {
			templateAppraisalSubmission.sendProcessManagerAlert("", "Appraisal", "I",
					appraisalId, level, linkParam, alertLink,
					"Approved",applicantId , "",
					"", applicantId, approverId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// call for sending the email
		templateAppraisalSubmission.sendApplicationMail();

		// clear the template
		templateAppraisalSubmission.clearParameters();

		// terminate template
		templateAppraisalSubmission.terminate();
	}

	public void sendAppraisalMailApplicantToManager(HttpServletRequest request, String empCode, String toMailId, String appraisalId, String phaseCode){
		
		logger.info("############# Applicant Id ##################"+empCode);
		logger.info("############# To Mail ID ##################"+toMailId);
		
		ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
		processAlerts.initiate(context, session);

		processAlerts.removeProcessAlertAppraisal(appraisalId, empCode,phaseCode);
		
		EmailTemplateBody templateAppraisalSubmission = new EmailTemplateBody();
		templateAppraisalSubmission.initiate(context, session);
		templateAppraisalSubmission.setEmailTemplate("Appraisal Applicant to Manager");
		templateAppraisalSubmission.getTemplateQueries();
		
		try {
			// get the query as per number
			EmailTemplateQuery templateQueryForApproval1 = templateAppraisalSubmission
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQueryForApproval1.setParameter(1, empCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval2 = templateAppraisalSubmission
					.getTemplateQuery(2);// To EMAIL
			templateQueryForApproval2.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval3 = templateAppraisalSubmission
			.getTemplateQuery(3);// To EMAIL
			templateQueryForApproval3.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval4 = templateAppraisalSubmission
			.getTemplateQuery(4);// To EMAIL
			templateQueryForApproval4.setParameter(1, empCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] link_param = new String[3];
		String[] link_label = new String[3];
		String applicationType = "Appraisal";
		String link = "/pas/ApprFormGeneralInfo_retrieveAppraisalDetails.action?apprId="+appraisalId+
							"$empId="+empCode+"$phaseCode="+phaseCode;
		link_param[0] = applicationType + "#" + toMailId + "#applicationDtls#"+ link; 
		
		link_label[0] = "here to Process";

		// configure the actual contents of the template
		templateAppraisalSubmission.configMailAlert();
		
		String alertLink = "/pas/ApprFormGeneralInfo_retrieveAppraisalDetails.action";
		String linkParam = "apprId="+appraisalId+
			"&empId="+empCode+"&phaseCode="+phaseCode;
		String approverId = toMailId;
		String level="0";
		try {
			templateAppraisalSubmission.sendProcessManagerAlert(approverId, "Appraisal", "A",
					appraisalId, level, linkParam, alertLink,
					"Pending",empCode , "",
					"", approverId, empCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		templateAppraisalSubmission.addOnlineLink(request, link_param, link_label);

		// call for sending the email
		templateAppraisalSubmission.sendApplicationMail();

		// clear the template
		templateAppraisalSubmission.clearParameters();

		// terminate template
		templateAppraisalSubmission.terminate();
	}

public void sendAppraisalMailManagerToManager(HttpServletRequest request, String empCode, String toMailId, String appraisalId, String phaseCode, String appraiserCode, String currentPhase){
		
		logger.info("############# Applicant Id ##################"+empCode);
		logger.info("############# To Mail ID ##################"+toMailId);
		
		ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
		processAlerts.initiate(context, session);

		processAlerts.removeProcessAlertAppraisal(appraisalId, empCode,currentPhase);
		
		EmailTemplateBody templateAppraisalSubmission = new EmailTemplateBody();
		templateAppraisalSubmission.initiate(context, session);
		templateAppraisalSubmission.setEmailTemplate("Appraisal Manager To Manager");
		templateAppraisalSubmission.getTemplateQueries();
		
		try {
			// get the query as per number
			EmailTemplateQuery templateQueryForApproval1 = templateAppraisalSubmission
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQueryForApproval1.setParameter(1, appraiserCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval2 = templateAppraisalSubmission
					.getTemplateQuery(2);// To EMAIL
			templateQueryForApproval2.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval3 = templateAppraisalSubmission
			.getTemplateQuery(3);// Current Approver
			templateQueryForApproval3.setParameter(1, appraiserCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval4 = templateAppraisalSubmission
			.getTemplateQuery(4);// Next Approver
			templateQueryForApproval4.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQueryForApproval5 = templateAppraisalSubmission
			.getTemplateQuery(5);// employee name
			templateQueryForApproval5.setParameter(1, empCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] link_param = new String[3];
		String[] link_label = new String[3];
		String applicationType = "Appraisal";
		String link = "/pas/ApprFormGeneralInfo_retrieveAppraisalDetails.action?apprId="+appraisalId+
							"$empId="+empCode+"$phaseCode="+phaseCode;
		link_param[0] = applicationType + "#" + toMailId + "#applicationDtls#"+ link; 
		
		link_label[0] = "here to Process";

		// configure the actual contents of the template
		templateAppraisalSubmission.configMailAlert();
		
		String alertLink = "/pas/ApprFormGeneralInfo_retrieveAppraisalDetails.action";
		String linkParam = "apprId="+appraisalId+
			"&empId="+empCode+"&phaseCode="+phaseCode;
		String approverId = toMailId;
		String level="0";
		try {
			templateAppraisalSubmission.sendProcessManagerAlert(approverId, "Appraisal", "A",
					appraisalId, level, linkParam, alertLink,
					"Pending",empCode , "",
					"", approverId, appraiserCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		templateAppraisalSubmission.addOnlineLink(request, link_param, link_label);

		// call for sending the email
		templateAppraisalSubmission.sendApplicationMail();

		// clear the template
		templateAppraisalSubmission.clearParameters();

		// terminate template
		templateAppraisalSubmission.terminate();
	}
	public void previewAppraisal(ApprFormSection apprFormSection) {
		
		try {
			String sqlQuery = "SELECT ROWNUM, Q_DESC,WT,RATING,PERCENT,COMMENTS,EMPID,PHASEID,PHASE_NAME,REPNAME,EVAL_LEVEL,SECID,SEC_NAME FROM (  SELECT  DISTINCT APPR_QUES_DESC AS Q_DESC, APPR_QUES_WEIGHTAGE  AS WT, APPR_QUES_RATING AS RATING,APPR_QUES_PERCENT_WT AS PERCENT, APPR_QUES_COMMENTS AS COMMENTS,   APPR_EMP_ID AS EMPID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID AS PHASEID, APPR_PHASE_NAME AS PHASE_NAME,(REPT.EMP_FNAME||' '||REPT.EMP_MNAME|| ' ' || REPT.EMP_LNAME) AS REPNAME, APPR_EVALUATOR_LEVEL AS EVAL_LEVEL,  APPR_SECTION_ID AS SECID, APPR_SECTION_NAME AS SEC_NAME,APPR_PHASE_ORDER, APPR_SECTION_ORDER,APPR_QUESTION_ORDER  FROM PAS_APPR_COMMENTS  INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_COMMENTS.APPR_ID=PAS_APPR_QUES_MAPPING.APPR_ID  AND PAS_APPR_COMMENTS.APPR_PHASE_ID=PAS_APPR_QUES_MAPPING.APPR_PHASE  AND PAS_APPR_COMMENTS.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID  AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID=PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID  AND PAS_APPR_COMMENTS.APPR_QUESTION_CODE=PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID )  INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)  INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID )  INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE)  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID  INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE WHERE PAS_APPR_COMMENTS.APPR_ID="+apprFormSection.getApprId()+" "+  
							   "AND HRMS_EMP_OFFC.EMP_ID="+apprFormSection.getEmpId()+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER,APPR_QUESTION_ORDER)";
			
			Object[][] data = getSqlModel().getSingleResult(sqlQuery);
			ArrayList listObj=new ArrayList();
			if(data!=null && data.length>0)
			{
				for(int i=0;i<data.length;i++)
				{
					ApprFormSection bean=new ApprFormSection();
					bean.setIttrPhaseName(checkNull(String.valueOf(data[i][8])));
					bean.setIttrSectionName(checkNull(String.valueOf(data[i][12])));
					bean.setIttrQuesDesc(checkNull(String.valueOf(data[i][1])));
					bean.setIttrQuesWt(checkNull(String.valueOf(data[i][2])));
					bean.setIttrQuesRating(checkNull(String.valueOf(data[i][3])));
					bean.setIttrActual(checkNull(String.valueOf(data[i][4])));
					bean.setIttrComment(checkNull(String.valueOf(data[i][5])));
					listObj.add(bean);
				}
			}
			apprFormSection.setApprRatingList(listObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPreviewTraningDtl(ApprFormSection apprFormSection) {
		try {
			String sqlQuery = "SELECT ROWNUM,APPR_TRN_DESC,TO_CHAR(APPR_TRN_FROM,'dd-mm-yyyy'),TO_CHAR(APPR_TRN_TO,'dd-mm-yyyy'), "+
								"APPR_TRN_DURATION,APPR_TRN_SPONCER,APPR_EMP_ID  FROM PAS_APPR_TRN_ATTENDED  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRN_ATTENDED.APPR_EMP_ID  WHERE APPR_ID = "+apprFormSection.getApprId()+"  and HRMS_EMP_OFFC.EMP_Id="+apprFormSection.getEmpId()+" ORDER BY APPR_EMP_ID ";
			
			Object[][] data = getSqlModel().getSingleResult(sqlQuery);
			ArrayList listObj=new ArrayList();
			if(data!=null && data.length>0)
			{
				for(int i=0;i<data.length;i++)
				{
					ApprFormSection bean=new ApprFormSection();
					bean.setIttrTrnAttndDesc(checkNull(String.valueOf(data[i][1])));
					bean.setIttrTrnAttndFrom(checkNull(String.valueOf(data[i][2])));
					bean.setIttrTrnAttndTo(checkNull(String.valueOf(data[i][3])));
					bean.setIttrTrnAttndDuration(checkNull(String.valueOf(data[i][4])));
					bean.setIttrTrnAttndSponser(checkNull(String.valueOf(data[i][5])));					
					listObj.add(bean);
				}
			}
			apprFormSection.setAppTrnAttndList(listObj);
			
			String sqlTrnApprQuery="SELECT ROWNUM,APPR_PHASE_NAME,APPR_TRN_DESC,APPR_TRNATTEN_COMMENT,PAS_APPR_TRN_ATTENDED_COMMENT.APPR_EMP_ID  FROM PAS_APPR_TRN_ATTENDED_COMMENT  INNER JOIN PAS_APPR_TRN_ATTENDED ON PAS_APPR_TRN_ATTENDED.APPR_TRN_CODE = PAS_APPR_TRN_ATTENDED_COMMENT.APPR_TRN_CODE  INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_TRN_ATTENDED_COMMENT.APPR_PHASE_ID  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRN_ATTENDED_COMMENT.APPR_EMP_ID  WHERE PAS_APPR_TRN_ATTENDED_COMMENT.APPR_ID = "+apprFormSection.getApprId()+"  AND HRMS_EMP_OFFC.EMP_ID="+apprFormSection.getEmpId()+" ORDER BY PAS_APPR_TRN_ATTENDED_COMMENT.APPR_EMP_ID,APPR_PHASE_ORDER";
			Object[][] dataTrnDtl = getSqlModel().getSingleResult(sqlTrnApprQuery);
			ArrayList listTrnObj=new ArrayList();
			if(dataTrnDtl!=null && dataTrnDtl.length>0)
			{
				for(int i=0;i<dataTrnDtl.length;i++)
				{
					ApprFormSection bean1=new ApprFormSection();
					bean1.setIttrTrnPhase(checkNull(String.valueOf(dataTrnDtl[i][1])));
					bean1.setIttrTrnAttendDesc(checkNull(String.valueOf(dataTrnDtl[i][2])));
					bean1.setIttrTrnAttndComment(checkNull(String.valueOf(dataTrnDtl[i][3])));
								
					listTrnObj.add(bean1);
				}
			}
			apprFormSection.setApprTrnAttndCommList(listTrnObj);
			
			String sqlTrnApprRecomnd="SELECT ROWNUM,APPR_PHASE_NAME,APPR_QUES_DESC,APPR_TRNRECOM_COMMENT,APPR_EMP_ID,PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_PHASE_ID  FROM PAS_APPR_TRN_RECOMMEND_COMMENT  INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_QUESTION_CODE)  INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID= PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_PHASE_ID)  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_EMP_ID  WHERE APPR_ID = "+apprFormSection.getApprId()+" "+ 
									 "AND HRMS_EMP_OFFC.EMP_ID="+apprFormSection.getEmpId()+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER ";
			Object[][] dataTrnRecomnd = getSqlModel().getSingleResult(sqlTrnApprRecomnd);
			ArrayList listTrnRecomndObj=new ArrayList();
			
			if(dataTrnRecomnd!=null && dataTrnRecomnd.length>0)
			{
				for(int i=0;i<dataTrnRecomnd.length;i++)
				{
					ApprFormSection bean2=new ApprFormSection();
					bean2.setIttrTrnRecomndPhase(checkNull(String.valueOf(dataTrnRecomnd[i][1])));
					bean2.setIttrTrnRecomndQusDesc(checkNull(String.valueOf(dataTrnRecomnd[i][2])));
					bean2.setIttrTrnRecomndComment(checkNull(String.valueOf(dataTrnRecomnd[i][3])));
								
					listTrnRecomndObj.add(bean2);
				}
			}
			apprFormSection.setApprTrnRecomndList(listTrnRecomndObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void setPreviewAwardDtl(ApprFormSection apprFormSection) {
		
		String sqlAwardAchived="SELECT ROWNUM,APPR_AWARD_DESC,TO_CHAR(APPR_AWARD_DATE,'dd-mm-yyyy'),APPR_ISSUING_AUTHORITY,APPR_EMP_ID   FROM PAS_APPR_AWARD_ACHIEVED  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_AWARD_ACHIEVED.APPR_EMP_ID  WHERE APPR_ID = "+apprFormSection.getApprId()+"  AND HRMS_EMP_OFFC.EMP_ID="+apprFormSection.getEmpId()+" ORDER BY APPR_EMP_ID";
		Object[][] dataAwardAchived = getSqlModel().getSingleResult(sqlAwardAchived);
		ArrayList listObj=new ArrayList();
		if(dataAwardAchived!=null && dataAwardAchived.length>0)
		{
			for(int i=0;i<dataAwardAchived.length;i++)
			{
				ApprFormSection bean=new ApprFormSection();
				bean.setIttrAwdAchvdDesc(checkNull(String.valueOf(dataAwardAchived[i][1])));
				bean.setIttrAwdAchvDate(checkNull(String.valueOf(dataAwardAchived[i][2])));
				bean.setIttrAwdAchvIssueAuth(checkNull(String.valueOf(dataAwardAchived[i][3])));				
				listObj.add(bean);
			}
		}
		apprFormSection.setApprAwdAchvList(listObj);
		String sqlAwdAchvdComment="SELECT ROWNUM,APPR_PHASE_NAME,APPR_AWARD_DESC,APPR_AWARD_COMMENT,PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID,PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_PHASE_ID  FROM PAS_APPR_AWD_ACHIEVED_COMMENT  INNER JOIN PAS_APPR_AWARD_ACHIEVED ON(PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE=PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE )  INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID= PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_PHASE_ID)  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID  WHERE PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID = "+apprFormSection.getApprId()+"  AND HRMS_EMP_OFFC.EMP_ID="+apprFormSection.getEmpId()+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER ";
		Object[][] dataAwardAchivedComm = getSqlModel().getSingleResult(sqlAwdAchvdComment);
		ArrayList listAwdAChvdCommObj=new ArrayList();
		if(dataAwardAchivedComm!=null && dataAwardAchivedComm.length>0)
		{
			for(int i=0;i<dataAwardAchivedComm.length;i++)
			{
				ApprFormSection bean1=new ApprFormSection();
				bean1.setIttrAwdAchvdCommPhase(checkNull(String.valueOf(dataAwardAchivedComm[i][1])));
				bean1.setIttrAwdAchvdCommDesc(checkNull(String.valueOf(dataAwardAchivedComm[i][2])));
				bean1.setIttrAwdAchvdComment(checkNull(String.valueOf(dataAwardAchivedComm[i][3])));				
				listAwdAChvdCommObj.add(bean1);
			}
		}
		apprFormSection.setApprAwdAchvdCommList(listAwdAChvdCommObj);
		
		String sqlAwdNomine="SELECT ROWNUM,APPR_PHASE_NAME,APPR_AWARD,APPR_AWARD_REASON,APPR_EMP_ID FROM PAS_APPR_AWD_NOMINATE_COMMENT  INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_AWD_NOMINATE_COMMENT.APPR_PHASE_ID  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_AWD_NOMINATE_COMMENT.APPR_EMP_ID  WHERE PAS_APPR_AWD_NOMINATE_COMMENT.APPR_ID = "+apprFormSection.getApprId()+"  AND HRMS_EMP_OFFC.EMP_ID="+apprFormSection.getEmpId()+" ORDER BY PAS_APPR_AWD_NOMINATE_COMMENT.APPR_EMP_ID,APPR_PHASE_ORDER  ";
		Object[][] dataAwdNomine = getSqlModel().getSingleResult(sqlAwdNomine);
		ArrayList listAwdNomineObj=new ArrayList();
		if(dataAwdNomine!=null && dataAwdNomine.length>0)
		{
			for(int i=0;i<dataAwdNomine.length;i++)
			{
				ApprFormSection bean2=new ApprFormSection();
				bean2.setIttrAwdNominPhase(checkNull(String.valueOf(dataAwdNomine[i][1])));
				bean2.setIttrAwdNominAwardName(checkNull(String.valueOf(dataAwdNomine[i][2])));
				bean2.setIttrAwdNominAwardReason(checkNull(String.valueOf(dataAwdNomine[i][3])));				
				listAwdNomineObj.add(bean2);
			}
		}
		apprFormSection.setApprAwdNomnList(listAwdNomineObj);
		
	}

	public void setPreviewDisciplinaryDtl(ApprFormSection apprFormSection) {
		try {
			String sqlDispActQuery = "SELECT ROWNUM,APPR_DISCP_ACTION,TO_CHAR(APPR_DISCP_DATE,'dd-mm-yyyy'),APPR_DISCP_AUTHORITY ,APPR_EMP_ID  FROM PAS_APPR_DISCIPLINARY  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_DISCIPLINARY.APPR_EMP_ID  WHERE APPR_ID = "+apprFormSection.getApprId()+"  AND HRMS_EMP_OFFC.EMP_ID="+apprFormSection.getEmpId()+" ORDER BY APPR_EMP_ID";
			
			Object[][] dataDiscAct = getSqlModel().getSingleResult(sqlDispActQuery);
			ArrayList listObj=new ArrayList();
			if(dataDiscAct!=null && dataDiscAct.length>0)
			{
				for(int i=0;i<dataDiscAct.length;i++)
				{
					ApprFormSection bean=new ApprFormSection();
					bean.setIttrDispAction(checkNull(String.valueOf(dataDiscAct[i][1])));
					bean.setIttrDispActDate(checkNull(String.valueOf(dataDiscAct[i][2])));
					bean.setIttrDispActIsssueAuth(checkNull(String.valueOf(dataDiscAct[i][3])));
					
					listObj.add(bean);
				}
			}
			apprFormSection.setApprDispActList(listObj);
			
			String sqlDispActCommQuery = "SELECT ROWNUM,APPR_PHASE_NAME,APPR_DISCP_ACTION, APPR_DISCP_COMMENTS,PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID,APPR_PHASE  FROM PAS_APPR_DISCIPLINARY_COMMENT  INNER JOIN PAS_APPR_DISCIPLINARY ON (PAS_APPR_DISCIPLINARY.APPR_DISCP_ID=PAS_APPR_DISCIPLINARY_COMMENT.APPR_DISCP_ID )  INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID= PAS_APPR_DISCIPLINARY_COMMENT.APPR_PHASE )  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID  WHERE PAS_APPR_DISCIPLINARY_COMMENT.APPR_ID = "+apprFormSection.getApprId()+"  and HRMS_EMP_OFFC.EMP_Id="+apprFormSection.getEmpId()+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER ";
			
			Object[][] dataDiscActComm = getSqlModel().getSingleResult(sqlDispActCommQuery);
			ArrayList listDispCommObj=new ArrayList();
			if(dataDiscActComm!=null && dataDiscActComm.length>0)
			{
				for(int i=0;i<dataDiscActComm.length;i++)
				{
					ApprFormSection bean1=new ApprFormSection();
					bean1.setIttrDispActPhase(checkNull(String.valueOf(dataDiscActComm[i][1])));
					bean1.setIttrDispActCommDesc(checkNull(String.valueOf(dataDiscActComm[i][2])));
					bean1.setIttrDispActComment(checkNull(String.valueOf(dataDiscActComm[i][3])));
					
					listDispCommObj.add(bean1);
				}
			}
			apprFormSection.setApprDispActCommentList(listDispCommObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public boolean isGoalFinalise( String apprCode,String empCode)
	{
		boolean flag=false;
		String goalQueryHDR = "SELECT GOAL_ID,GOAL_NAME FROM HRMS_GOAL_CONFIG WHERE APPRAISAL_CODE = "
			+apprCode + "  ORDER BY GOAL_ID ";
		Object[][] goalHdrData = getSqlModel().getSingleResult(goalQueryHDR);
		Object[][]goalObj=null;
		if(goalHdrData !=null && goalHdrData.length>0)
		{
			for (int p = 0; p < goalHdrData.length; p++) {
				
				String qoalQuery="SELECT NVL(GOAL_RATING_WEIGHTAGE,0) AS GOAL_WT, NVL(GOAL_SELF_WEIGHTAGE,0) AS SELF_WGT, NVL(GOAL_MANAGER_WEIGHTAGE,0) AS MGR_WT, MULTIPLE_RATING_OPTION AS AVG_RT, NVL(APPRAISAL_RATING_WEIGHTAGE,0) AS APPR_WGT  ,NVL(GOAL_REVIEW_SELF_RATING,0), NVL(GOAL_REVIEW_MGR_RATING,0), NVL(GOAL_REVIEW_FINAL_RATING,0),HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_CONFIG.GOAL_ID, "+
				"HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,TO_CHAR(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(GOAL_ASSESSMENT_WEIGTAGE,0) "+	
				 "FROM HRMS_GOAL_CONFIG  	INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_HDR.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID )	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "+ 
				"LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID="+goalHdrData[p][0]+" ) "+ 	
				"WHERE HRMS_GOAL_CONFIG.APPRAISAL_CODE="+apprCode+"   AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_EMP_OFFC.EMP_ID="+empCode+" AND HRMS_GOAL_CONFIG.GOAL_ID = "+goalHdrData[p][0];
			//double FINAL_WGT=0.0;
			goalObj = getSqlModel().getSingleResult(qoalQuery);
			String goalHdrCode="0";
			String goalId="0";
			if(goalObj!=null && goalObj.length>0){


				for (int m = 0; m < goalObj.length; m++) {
					goalHdrCode=String.valueOf(goalObj[m][8]);
					goalId=String.valueOf(goalObj[m][9]);
					
							
							String empSelfGoalQuery="SELECT SELF_GOAL_DTL_ID,NVL(GOAL_CATEGORY,' ')AS GOAL_CATEGORY,GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')AS ACHIEVEMENT_DATE ,NVL(GOAL_COMMENTS,' ') AS  KRA,NVL(GOAL_WEIGHTAGE,0)AS WEIGHTAGE,SELF_GOAL_RATING AS RATING,NVL(SELF_GOAL_COMMENT,'') AS COMMENTS , NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,IS_GOAL_ACHIEVED FROM HRMS_GOAL_SELF_ASSESSMENT_DTL		 INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID ="+goalObj[m][10]+" )  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";
							Object[][]empSelfObj=getSqlModel().getSingleResult(empSelfGoalQuery);
							if(empSelfObj !=null && empSelfObj.length>0){
								
								
							
								String mgrApprQuery="SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID)" 
									+"	WHERE EMP_GOAL_ASSESSMENT_ID ="+goalObj[m][10]+"	";
							Object[][]mgrObj=getSqlModel().getSingleResult(mgrApprQuery);
							if(mgrObj !=null && mgrObj.length>0){
								
							}else
							{
								flag=true;
								break;
							}
							
							
							}
							else
							{
								flag=true;
								break;
							}
						//}
					//}
					
				}
				
			
				
			}else
			{
				flag=true;
				break;
			}
				
			}
		}
		return flag;
	}
	
	public boolean isCompFinalise( String apprCode,String empCode)
	{
		boolean flag=false;
		String compHedingSql="SELECT APPR_MAP_CODE,NVL(COMP_NAME,'') FROM PAS_APPR_GOAL_COMP_MAP "+
		"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=PAS_APPR_GOAL_COMP_MAP.APPR_MAP_CODE) "+
		 "WHERE APPR_CODE = "+apprCode+" AND APPR_MAP_TYPE='C'";
		Object[][]compHedingObj=getSqlModel().getSingleResult(compHedingSql);
		String compId="0";
		if(compHedingObj!=null && compHedingObj.length > 0)
		{
			compId=checkNull(String.valueOf(compHedingObj[0][0]));
			
			
			String compAssessmentIdSql="SELECT  HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID,TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(COMP_ASSESSMENT_WEIGTAGE,0) FROM HRMS_COMP_EMP_ASSESSMENT_HDR "+ 
										"LEFT JOIN HRMS_COMP_ASSESSMENT_CONFIG ON(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID) "+ 
										"WHERE  EMP_ID = "+empCode+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId;
			Object[][]compAssessmentIdObj=getSqlModel().getSingleResult(compAssessmentIdSql);
			if(compAssessmentIdObj!=null && compAssessmentIdObj.length > 0){
				
				
				for(int n=0;n<compAssessmentIdObj.length;n++)
				{
					
					
					
					String compEmpAssessmentSql="SELECT  DISTINCT  NVL(HRMS_COMPETENCY_HDR.COMPETENCY_TITLE,' '), NVL(COMP_RATING,0),HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL,NVL(COMPETENCY_WEIGHTAGE,0),HRMS_COMPETENCY_ROLE.COMPETENCY_CODE "+ 
												"FROM HRMS_COMP_SELF_ASSESMENT "+ 
												"INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+ 
												"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID =HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID "+ 
												"INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMP_SELF_ASSESMENT.COMP_ID = HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND HRMS_EMP_OFFC.EMP_RANK=HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE) "+
												"INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) "+ 
												"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID) "+
												"WHERE  COMP_EMP_ID = "+empCode+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compAssessmentIdObj[n][0]+"  ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";
					Object[][]compSelfRatingObj=getSqlModel().getSingleResult(compEmpAssessmentSql);
					Object[][]compEmpRatObj=null;
					if(compSelfRatingObj!=null && compSelfRatingObj.length > 0)
					{
						
					}else
					{
						flag=true;
						break;
					}
					
					String mgrCompSql="SELECT  DISTINCT  NVL(HRMS_COMPETENCY_HDR.COMPETENCY_TITLE,' '), NVL(COMP_RATING,0),HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL,NVL(COMPETENCY_WEIGHTAGE,0),HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,e2.EMP_FNAME||' '||e2.EMP_LNAME "+ 
										"FROM HRMS_COMP_EMP_ASSESSMENT_DTL "+ 
										"INNER JOIN HRMS_EMP_OFFC e1 ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID=e1.EMP_ID) "+ 
										"inner join hrms_emp_offc e2 on (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID=e2.EMP_ID) "+
										"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID =HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID "+ 
										"INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND e1.EMP_RANK=HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE) "+
										"INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) "+ 
										"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID) "+
										"WHERE  COMP_EMP_ID = "+empCode+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compAssessmentIdObj[n][0]+" ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";
					
					Object[][]compMgrRatingObj=getSqlModel().getSingleResult(mgrCompSql);
					Object[][]compMgrRatObj=null;
					if(compMgrRatingObj!=null && compMgrRatingObj.length > 0)
					{
						
						
					}
					else
					{
						flag=true;
						break;
					}
					
				}
				
			}else
			{
				flag=true;
				
			}
		}
		
		return flag;
	}

	public boolean updateAppraisal(HttpServletRequest request, String apprCode,
			String templateCode, String empCode, String appraiserCode, String phaseCode) {
			boolean result=false;
		
			try {
				String sqlQuery = " UPDATE PAS_APPR_TRACKING SET NEXT_PHASE_FORWARD = 'Y', PHASE_FORWARD='Y' ,APPRAISAL_STATUS ='Y' ,PHASE_ISCOMPLETE = 'Y' "
						+ " WHERE APPR_ID ="+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode;
				result =getSqlModel().singleExecute(sqlQuery);
				if(result)
				{
					sentAppraisalMailManagerToApplicant(empCode,appraiserCode,apprCode,phaseCode);
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public boolean updateAppraisalCancel(HttpServletRequest request,String apprCode,
			String templateCode, String empCode, String appraiserCode, String phaseCode){
		 boolean result=false;
		 try{
			 String sqlQuery="UPDATE PAS_APPR_TRACKING    " +
			 					" set NEXT_PHASE_FORWARD = 'N' ," +
			 					" PHASE_ISCOMPLETE = 'N',       " +
			 					" PHASE_FORWARD='N'" +
			 					" where  APPR_ID ="+apprCode+   
			 					" AND TEMPLATE_CODE = "+templateCode+
			 					" AND EMP_ID = "+empCode +
			 					" and  phase_id="+phaseCode;
			 System.out.println("Sql Query - "+sqlQuery);
			 result=getSqlModel().singleExecute(sqlQuery);
			 
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return result;
	}
	
}
