package org.paradyne.model.PAS;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.PAS.TemplateDefination;
import org.paradyne.lib.ModelBase;

public class TemplateDefinationModel extends ModelBase {

	public boolean addTemplate(Object[][]dataObject,TemplateDefination bean){
		boolean result = false;
					result = getSqlModel().singleExecute(getQuery(1),dataObject);
					
					Object [][]tmpObj = new Object[1][1];
					tmpObj[0][0] = dataObject[0][0];
					
					if(result){
						
						getSqlModel().singleExecute(getQuery(4),tmpObj);
						Object [][]tempCode = getSqlModel().getSingleResult(getQuery(3));
						
						bean.setTemplateCode(""+tempCode[0][0]);
					}
					return result;
	}
	
	public boolean updateTemplate(Object[][]dataObject){
		boolean result = false;
					result = getSqlModel().singleExecute(getQuery(2),dataObject);
		return result;
	}
	public void getApprDetails(TemplateDefination bean){
		String query =" SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID "
			+"FROM PAS_APPR_CALENDAR  WHERE APPR_ID ="+bean.getApprId() +" ORDER BY APPR_ID ";
		Object[][] apprdet = getSqlModel().getSingleResult(query);
		if(apprdet!=null && apprdet.length>0){
			bean.setApprCode(String.valueOf(apprdet[0][0]));
			bean.setStartDate(String.valueOf(apprdet[0][1]));
			bean.setEndDate(String.valueOf(apprdet[0][2]));
			
			
		}
		
	}
	public void getTemplateDetails(TemplateDefination bean){
		String query =" SELECT PAS_APPR_TEMPLATE.APPR_ID,APPR_TEMPLATE_NAME, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), "+
			" TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_TEMPLATE_ID  FROM PAS_APPR_TEMPLATE  "
			+" LEFT JOIN PAS_APPR_CALENDAR ON (PAS_APPR_TEMPLATE.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) WHERE PAS_APPR_TEMPLATE.APPR_ID="+bean.getApprId()
			+"  ORDER BY PAS_APPR_TEMPLATE.APPR_ID,APPR_TEMPLATE_ID ";
		Object[][] tempDet = getSqlModel().getSingleResult(query);
		List<TemplateDefination> list=new ArrayList<TemplateDefination>();
		if(tempDet!=null && tempDet.length>0){
			for(int i=0;i<tempDet.length;i++){
				TemplateDefination bean1=new TemplateDefination();
			bean1.setApprCode(String.valueOf(tempDet[i][0]));
			bean1.setTemplateName(String.valueOf(tempDet[i][1]));
			bean1.setStartDate(String.valueOf(tempDet[i][2]));
			bean1.setEndDate(String.valueOf(tempDet[i][3]));
			bean1.setTemplateCode(String.valueOf(tempDet[i][4]));
			list.add(bean1);
			}
			bean.setTemList(list);
		}
		
	}
}
