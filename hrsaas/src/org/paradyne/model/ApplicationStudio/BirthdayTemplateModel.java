package org.paradyne.model.ApplicationStudio;

import org.paradyne.lib.ModelBase;

public class BirthdayTemplateModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LetterTemplateModel.class);

	public boolean save(String templateName, String templateData, String templateCode) {
		Object data[][]=new Object[1][1];
		data[0][0]=templateData;
			String hdrQuery = " INSERT INTO HRMS_BIRTHDAYTEMPLATE_HDR(TEMPLATE_ID,TEMPLATE_DATE,TEMPLATE_NAME,TEMPLATE_BODY) "
					+ " VALUES ((SELECT NVL(MAX(TEMPLATE_ID),0)+1 from HRMS_BIRTHDAYTEMPLATE_HDR) ,"
					+ "SYSDATE,"
					+ "'"
					+ templateName
					+ "',?)";
			if(templateCode != null && !templateCode.equals(""))
				hdrQuery="UPDATE HRMS_BIRTHDAYTEMPLATE_HDR SET TEMPLATE_NAME = '"+templateName+"',TEMPLATE_BODY = ? WHERE TEMPLATE_ID = "+templateCode;
			
			return getSqlModel().singleExecute(hdrQuery,data);
	}
	
	public Object[][] setTemplateDataOnSearch(String templateId)
	{
		String selQry = " SELECT TEMPLATE_BODY FROM HRMS_BIRTHDAYTEMPLATE_HDR WHERE TEMPLATE_ID = "+templateId;
		Object[][] templateObj = getSqlModel().getSingleResult(selQry);
		return templateObj;
	}
}
	
