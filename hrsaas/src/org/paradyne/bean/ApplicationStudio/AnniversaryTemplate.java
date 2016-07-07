/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 * 
 */
public class AnniversaryTemplate extends BeanBase {
	private String htmlcode = "";
	private String tempCode = "";
	private String tempContent = "";
	private String field = "";
	private String TemplateId = "";
	private String tempName = "";

	/**
	 * @return the htmlcode
	 */
	public String getHtmlcode() {
		return htmlcode;
	}

	/**
	 * @param htmlcode
	 *            the htmlcode to set
	 */
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}

	/**
	 * @return the tempCode
	 */
	public String getTempCode() {
		return tempCode;
	}

	/**
	 * @param tempCode
	 *            the tempCode to set
	 */
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}

	/**
	 * @return the tempContent
	 */
	public String getTempContent() {
		return tempContent;
	}

	/**
	 * @param tempContent
	 *            the tempContent to set
	 */
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return TemplateId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}

	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}

	/**
	 * @param tempName
	 *            the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
}//end of class
