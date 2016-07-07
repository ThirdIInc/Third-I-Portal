package org.paradyne.bean.portal;

import org.paradyne.lib.BeanBase;

public class TipsSettingBean extends BeanBase {

	private boolean contentDataFlag =false;

	/**
	 * @return the contentDataFlag
	 */
	public boolean isContentDataFlag() {
		return contentDataFlag;
	}

	/**
	 * @param contentDataFlag the contentDataFlag to set
	 */
	public void setContentDataFlag(boolean contentDataFlag) {
		this.contentDataFlag = contentDataFlag;
	}
	
}
