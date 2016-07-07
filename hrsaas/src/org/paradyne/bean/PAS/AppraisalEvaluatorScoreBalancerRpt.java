package org.paradyne.bean.PAS;

import org.paradyne.lib.BeanBase;

public class AppraisalEvaluatorScoreBalancerRpt extends BeanBase {
	private String sAppId = null;
	private String sAppCode = null;
	private String sAppStartDate = null;
	private String sAppEndDate = null;
	
	public String getSAppId() {
		return sAppId;
	}

	public void setSAppId(String appId) {
		sAppId = appId;
	}

	public String getSAppCode() {
		return sAppCode;
	}

	public void setSAppCode(String appCode) {
		sAppCode = appCode;
	}

	public String getSAppStartDate() {
		return sAppStartDate;
	}

	public void setSAppStartDate(String appStartDate) {
		sAppStartDate = appStartDate;
	}

	public String getSAppEndDate() {
		return sAppEndDate;
	}

	public void setSAppEndDate(String appEndDate) {
		sAppEndDate = appEndDate;
	}
	
}
