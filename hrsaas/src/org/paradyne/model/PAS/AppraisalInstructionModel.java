package org.paradyne.model.PAS;

import java.util.ArrayList;

import org.paradyne.bean.PAS.AppraisalInstruction;

import org.paradyne.lib.ModelBase;
import org.struts.action.PAS.ApprFormGeneralInfoAction;

public class AppraisalInstructionModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraisalInstructionModel.class);
	
	public void getRatingdetails(AppraisalInstruction bean){
		try{
			Object [] apprCode = new Object[1];
			apprCode [0] = String.valueOf(bean.getApprId());
		
			Object[][] rateObj = getSqlModel().getSingleResult(getQuery(4),apprCode);
			ArrayList<Object> list=new ArrayList<Object>();
			if(rateObj != null && rateObj.length > 0){
				
				if(String.valueOf(rateObj[0][0]).equals("perc")){
					
					bean.setRatingType("perc");
				}else{
					
					logger.info("bean.getApprId()-----------"+bean.getApprId());
					Object [][] ratingData = getSqlModel().getSingleResult(getQuery(1),apprCode);
					bean.setRatingType("scale");
					if(ratingData != null && ratingData.length !=0){
						for (int i = 0; i < ratingData.length; i++) {
							AppraisalInstruction bean1 = new AppraisalInstruction();
							
							bean1.setRatingValue(checkNull(String.valueOf(ratingData[i][0])));
							bean1.setRatingDesc(checkNull(String.valueOf(ratingData[i][1])));
							
							list.add(bean1);

						}
					} // end of if
				}
			}
			
			bean.setRatingList(list);
			logger.info("loist sizeeeeeee----------"+list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	public boolean saveInstructioons(Object[][] updateInstrObj){
		
		boolean result = false;
		try{
		result = getSqlModel().singleExecute(getQuery(2), updateInstrObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void getInstructions(AppraisalInstruction bean){
		try{
		Object []codeObj = new Object[2];
		
			codeObj[0]= bean.getApprId();
			codeObj[1]= bean.getTemplateCode();
		
		Object [][]resultData = getSqlModel().getSingleResult(getQuery(3), codeObj);
		if(resultData != null && resultData.length !=0){
		System.out.println("String.valueOf(resultData[0][0]).trim()"+String.valueOf(resultData[0][0]).trim());
		if(String.valueOf(resultData[0][0]).trim().equals("Y"))
			bean.setInstrApplicable("true");
		else
			bean.setInstrApplicable("false");
		
		bean.setInstruction(checkNull(String.valueOf(resultData[0][1])));
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* 
	 * method name : checkNull
	 * purpose     : check the string is null or not
	 * return type : String
	 * parameter   : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  // end of if
		else {
			return result;
		} // end of else
	}
}
