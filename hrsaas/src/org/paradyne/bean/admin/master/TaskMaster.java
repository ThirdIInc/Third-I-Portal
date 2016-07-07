package org.paradyne.bean.admin.master;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class TaskMaster extends BeanBase {

	
	private String modeLength="false";
	private String totalRecords="";
	private String typeCode;
	private String typeName;
	
	private String myPage;
	private String hiddencode;
	private String show;
	ArrayList iteratorlist;
	private String hdeleteCode;

	
	/**
	 * @return the projName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param projName the projName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the iteratorlist
	 */
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	/**
	 * @param iteratorlist the iteratorlist to set
	 */
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the proCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param proCode the proCode to se  t
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	
	

	
}
