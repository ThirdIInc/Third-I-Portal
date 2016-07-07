package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.ConfigureMailAlerts;
import org.paradyne.lib.ModelBase;



public class ConfigureMailAlertsModel extends ModelBase {	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ConfigureMailAlertsModel.class);
/*
	public boolean saveSetting(ConfigureMailAlerts bean,HttpServletRequest request) {
		Object confDtl[][] = null;
		try {

			String query = " UPDATE HRMS_MAIL_EVENTS SET EVENT_TRIGGER_OPTION=? where MAIL_EVENT_CODE=? ";

			Object hidOption[] = request.getParameterValues("hidConf");
			Object eventCode[] = request.getParameterValues("eventCode");

			if (eventCode.length > 0) {
				logger.info("eventCode-->length..!" + eventCode.length);
				confDtl = new Object[eventCode.length][2];

				for (int j = 0; j < hidOption.length; j++) {
					confDtl[j][0] = hidOption[j];    // A OR E OR V
					confDtl[j][1] = eventCode[j];
					// logger.info("i value..!"+j+" "+hidOption[j]);
					// confDtl[i][1]=String.valueOf(i+1);
				}
				

				return getSqlModel().singleExecute(query, confDtl);
			}

		} catch (Exception e) {
			logger.info("configuration Data is not available in database...!"+ e);
			return false;
		}

		return true;
	}

	
	
	
	public void getSettingDtl(ConfigureMailAlerts bean) {

		String query = "SELECT  EVENT_TRIGGER_OPTION,HRMS_MAIL_EVENTS.EVENT_NAME,MAIL_EVENT_CODE FROM HRMS_MAIL_EVENTS" 
		+"  inner join HRMS_MAIL_EVENTS on(HRMS_MAIL_EVENTS.EVENT_CODE=HRMS_MAIL_EVENTS.MAIL_EVENT_CODE)"
	    +" ORDER BY MAIL_EVENT_CODE asc";
		try {
			Object confDtl[][] = getSqlModel().getSingleResult(query);
			ArrayList list = new ArrayList();
			if (confDtl != null && confDtl.length > 0) {
				for (int i = 0; i < confDtl.length; i++) {

					ConfigureMailAlerts locbean = new ConfigureMailAlerts();
					locbean.setEventName(checkNull(String.valueOf(confDtl[i][1])));
					locbean.setHidConf(checkNull(String.valueOf(confDtl[i][0])));
					locbean.setEventCode(checkNull(String.valueOf(confDtl[i][2])));
					list.add(locbean);
				}
				bean.setEventList(list);
				System.out.println("list sie...!" + list.size());
			}

		} catch (Exception e) {
			logger.info("configuration Data is not available in database...!"+ e);
		}
	}


*/
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	
	
public boolean saveCentraliseSetting(ConfigureMailAlerts bean, HttpServletRequest request) {
		
		String query=" UPDATE HRMS_MAIL_EVENTS SET EVENT_OPTION_FLAG=?,EVENT_TEMPLATE_CODE=?,EVENT_TRIGGER_OPTION=? where EVENT_CODE=? ";
		Object confDtl[][] = null;
		try {
			Object hidOption[] = request.getParameterValues("hidConf");
			Object eventCode[] = request.getParameterValues("eventCode");
			Object hidRadioOption[] = request.getParameterValues("hidRadioConf");
			Object templateCode[]=null;
			
			
			

			if (eventCode.length > 0) {
				templateCode=new Object[eventCode.length];
				for (int i = 0; i < eventCode.length; i++) {
					 templateCode[i] = request.getParameter("templateCode"+i);
				}
				
				
				logger.info("eventCode-->length..!" + templateCode);
				logger.info("eventCode-->length..!" + eventCode.length);
				confDtl = new Object[eventCode.length][4];

				for (int j = 0; j < hidOption.length; j++) {
					confDtl[j][0] = hidOption[j];    // Y OR N
					
					 if(!templateCode[j].equals(""))
						 confDtl[j][1] =String.valueOf(templateCode[j]);
					 else
						 confDtl[j][1] =String.valueOf("");
					 
					 confDtl[j][2] = hidRadioOption[j];
					 
					 confDtl[j][3] = eventCode[j];
					 
					logger.info("i value..!"+j+" "+hidOption[j]+"   :"+String.valueOf(confDtl[j][1]) );
					// confDtl[i][1]=String.valueOf(i+1);
				}
				

				return getSqlModel().singleExecute(query,confDtl);
			}

		} catch (Exception e) {
			logger.info("configuration Data is not available in database...!"+e);
			return false;
		}

		return true;
		
		
	}
	
	
	public void  getCentraliseSettingDtl(ConfigureMailAlerts bean) {
		//String query = "SELECT  EVENT_OPTION_FLAG,MAIL_EVENT_NAME,MAIL_EVENT_CODE FROM HRMS_MAIL_EVENTS ORDER BY MAIL_EVENT_CODE asc";
		
		
		String query = "SELECT  EVENT_CODE,EVENT_NAME,EVENT_OPTION_FLAG,EVENT_TEMPLATE_CODE, "
			+"  HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME,EVENT_TRIGGER_OPTION  FROM HRMS_MAIL_EVENTS "
			+"  left join HRMS_EMAILTEMPLATE_HDR on (HRMS_MAIL_EVENTS.EVENT_TEMPLATE_CODE=HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID)"
				//+" where EVENT_MODULE_CODE="+bean.getModuleName()+" "
			+" where EVENT_MODULE_CODE="+bean.getDupModulecode()+" "
		+" ORDER BY EVENT_CODE asc	";
		
		try {
			Object confDtl[][] = getSqlModel().getSingleResult(query);
			ArrayList list = new ArrayList();
			if (confDtl != null && confDtl.length > 0) {
				
				for (int i = 0; i < confDtl.length; i++) {

					ConfigureMailAlerts locbean = new ConfigureMailAlerts();
					locbean.setEventCode(checkNull(String.valueOf(confDtl[i][0])));
					
					locbean.setEventName(checkNull(String.valueOf(confDtl[i][1])));
					locbean.setHidConf(checkNull(String.valueOf(confDtl[i][2])));   // for Y OR N
					locbean.setTemplateCode(checkNull(String.valueOf(confDtl[i][3])));
					locbean.setTemplateName(checkNull(String.valueOf(confDtl[i][4])));
					locbean.setHidRadioConf(checkNull(String.valueOf(confDtl[i][5])));     // FOR A OR V OR E
					
					logger.info(""+checkNull(String.valueOf(confDtl[i][3]))+ " name :: "+checkNull(String.valueOf(confDtl[i][4])));
					list.add(locbean);
				}
				bean.setEventList(list);
				
				bean.setNoofRecordsFlag(true);
				System.out.println("getCentraliseSettingDtl list size...!" + list.size());
			}else
			{
				bean.setNoofRecordsFlag(false);
				bean.setEventList(null);
			}

		} catch (Exception e) {
			logger.info("configuration Data is not available in database...!"+ e);
		}
		
		
		
	}
	/*
	public void setModulenames(ConfigureMailAlerts bean) {
		// TODO Auto-generated method stub
		TreeMap map = new TreeMap();
		String query="SELECT MODULE_CODE, MODULE_NAME FROM HRMS_MODULE order by MODULE_CODE";
		Object [][] obj = getSqlModel().getSingleResult(query);
		if(obj!=null && obj.length>0)
		{
			for (int j = 0; j < obj.length; j++) {
				map.put(String.valueOf(obj[j][0]), String.valueOf(obj[j][1]));
			}
			
		}
		map.put(String.valueOf("0"), String.valueOf("Select Module Name"));
		bean.setMap(map);
		
	}
	*/
	
	
	
}
