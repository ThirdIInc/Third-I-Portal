package org.paradyne.model.PAS;

import org.paradyne.bean.PAS.ApprFormInstuction;
import org.paradyne.lib.ModelBase;


public class ApprFormInstructionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormInstructionModel.class);
	
	public void getInstructions(ApprFormInstuction bean){
		
		try{
			Object [] appraisalCode = new Object[2];
			
			appraisalCode [0] = bean.getApprId();
			appraisalCode [1] = bean.getTemplateCode();
			
			Object [][] instrData = getSqlModel().getSingleResult(getQuery(1), appraisalCode);
		
			//String rating = getRating(bean.getApprId());
			String rating ="";
			
			logger.info("instrData.length"+instrData.length);
			if (instrData != null && instrData.length != 0) {
				//rating = rating +"\n\n"+checkNull(String.valueOf(instrData[0][2]));
				rating = checkNull(String.valueOf(instrData[0][2]));
				//bean.setApprInstr(checkNull(String.valueOf(instrData[0][2])));
								
			}
			
			bean.setApprInstr(checkNull(rating));
			
			logger.info("rating"+rating);
			
		}catch(Exception e){
			e.printStackTrace();
		}
				
	}
	public boolean getInstrApplicable(ApprFormInstuction bean){
		
		boolean result = false;
				
		Object [] appraisalCode = new Object[2];
		
		appraisalCode [0] = bean.getApprId();
		appraisalCode [1] = bean.getTemplateCode();
		
		Object [][] instrData = getSqlModel().getSingleResult(getQuery(1),appraisalCode);
	
		logger.info("instrData.length"+instrData.length);
		if (instrData != null && instrData.length != 0) {
		
			if(String.valueOf(instrData[0][1]).equals("Y"))
				result= true;
								
		}
		
		return result;
	}
	public String getRating(String apprCode){
		 
		String rating =null;
		
		Object [][] ratingData = getSqlModel().getSingleResult(getQuery(2),new Object[]{apprCode});
		
		for(int i =0;i<ratingData.length;i++){
			if(i == 0)
				rating = "\nRating : "+String.valueOf(ratingData[i][0])+" - "+String.valueOf(ratingData[i][1]);
			else
				rating = rating +", "+String.valueOf(ratingData[i][0])+" - "+String.valueOf(ratingData[i][1]);
		}
		logger.info("rating value--"+rating);
		return rating;
		
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
