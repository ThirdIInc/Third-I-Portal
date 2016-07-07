package org.paradyne.bean.mypage;

import org.paradyne.lib.BeanBase;

public class RightTileDataBean extends BeanBase {

	private String pollCode ="";
	private String quesName ="";
	private String optionValue ="";
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public String getPollCode() {
		return pollCode;
	}
	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}
	public String getQuesName() {
		return quesName;
	}
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
}
