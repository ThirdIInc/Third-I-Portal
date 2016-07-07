/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import org.paradyne.lib.ModelBase;

/**
 * @author Reeba_Joseph
 * 
 */
public class AnniversaryTemplateModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AnniversaryTemplateModel.class);

	/**
	 * Save the template
	 * 
	 * @param templateName
	 * @param templateData
	 * @param templateCode
	 * @return boolean
	 */
	public boolean save(String templateName, String templateData,
			String templateCode) {
		Object[][]data=new Object[1][1];
		data[0][0]=templateData;
		String hdrQuery = " INSERT INTO HRMS_ANNIVERSARY_TEMPLATE(TEMPLATE_ID,TEMPLATE_DATE,TEMPLATE_NAME,TEMPLATE_BODY) "
				+ " VALUES ((SELECT NVL(MAX(TEMPLATE_ID),0)+1 from HRMS_ANNIVERSARY_TEMPLATE) ,"
				+ "SYSDATE," + "'" + templateName + "',?)";
		if (templateCode != null && !templateCode.equals(""))//if code is not null
			hdrQuery = "UPDATE HRMS_ANNIVERSARY_TEMPLATE SET TEMPLATE_NAME = '"
					+ templateName + "',TEMPLATE_BODY = ?" 
					+ " WHERE TEMPLATE_ID = " + templateCode;

		return getSqlModel().singleExecute(hdrQuery,data);
	}//end of save method

	/**
	 * Set template data
	 * 
	 * @param templateId
	 * @return Object
	 */
	public Object[][] setTemplateDataOnSearch(String templateId) {
		String selQry = " SELECT TEMPLATE_BODY FROM HRMS_ANNIVERSARY_TEMPLATE WHERE TEMPLATE_ID = "
				+ templateId;
		Object[][] templateObj = getSqlModel().getSingleResult(selQry);
		return templateObj;
	}//end of setTemplateDataOnSearch method
	
}//end of class
