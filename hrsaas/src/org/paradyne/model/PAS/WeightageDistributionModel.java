package org.paradyne.model.PAS;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.WeightageDistribution;
import org.paradyne.lib.ModelBase;

public class WeightageDistributionModel extends ModelBase{

	
	public String chkNull(Object data){
		
		if(data!=null){
			return data.toString();
		}else{
			return "";
		}
		
	}
	
	
	/**
	 * THIS METHOD RETRIEVES ALL THE CONFIGURED/ACTIVE PHASES FOR THE CALENDAR
	 * @param bean
	 */
	public void getConfiguredPhases(WeightageDistribution bean, HttpServletRequest request){
		
		Object param []= new Object [1];
		param[0] = bean.getApprId();
		
		Object calPhases[][] = getSqlModel().getSingleResult(getQuery(1),param );
		ArrayList list = new ArrayList<WeightageDistribution>();	
		long totalWeightage = 0;
		
		if(calPhases!=null && calPhases.length>0){
			
			for(int i=0;i<calPhases.length;i++){
				
				
				WeightageDistribution bean1 = new WeightageDistribution();
				
				if(i<calPhases.length){//FOR ALL PHASES
					
					bean1.setPhase(calPhases[i][0].toString());
					bean1.setWeightage(calPhases[i][1].toString());				
					bean1.setApprId(calPhases[i][2].toString());	
					bean1.setPhaseId(calPhases[i][3].toString());
					totalWeightage+=Integer.parseInt(calPhases[i][1].toString());
					
				}
				list.add(bean1);
				
			}
			bean.setPhaseList(list);
		}
	
		request.setAttribute("totalWeightage", totalWeightage);
		
	}
	
}
