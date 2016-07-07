package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

public class LetterTypeMaster extends BeanBase{

	private String letterTypeId="";
	private String letterTypeName="";
	public String getLetterTypeId() {
		return letterTypeId;
	}
	public void setLetterTypeId(String letterTypeId) {
		this.letterTypeId = letterTypeId;
	}
	public String getLetterTypeName() {
		return letterTypeName;
	}
	public void setLetterTypeName(String letterTypeName) {
		this.letterTypeName = letterTypeName;
	}
}
