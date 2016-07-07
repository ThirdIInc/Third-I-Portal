/**
 * 
 */
package org.paradyne.bean.helpdesk;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0623
 *
 */
public class HelpDeskStatusCatg extends BeanBase {
	private String statusCategCode="";
	private String statusCateg="";
	private String statusAbbrev="";
	private String statusOrder="";
	private String isStatusLast="";
	
	private String statusCategCodeItr="";
	private String statusCategItr="";
	private String statusAbbrevItr="";
	private String modeLength="false";
	private String totalRecords="";
	private ArrayList StatusCategList=null;
	private String myPage;
	private String show;
	
	private String hiddencode;
	private String editHidcode = "";
	
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
	 * @return the editHidcode
	 */
	public String getEditHidcode() {
		return editHidcode;
	}
	/**
	 * @param editHidcode the editHidcode to set
	 */
	public void setEditHidcode(String editHidcode) {
		this.editHidcode = editHidcode;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the statusCategList
	 */
	public ArrayList getStatusCategList() {
		return StatusCategList;
	}
	/**
	 * @param statusCategList the statusCategList to set
	 */
	public void setStatusCategList(ArrayList statusCategList) {
		StatusCategList = statusCategList;
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
	 * @return the statusCategCodeItr
	 */
	public String getStatusCategCodeItr() {
		return statusCategCodeItr;
	}
	/**
	 * @param statusCategCodeItr the statusCategCodeItr to set
	 */
	public void setStatusCategCodeItr(String statusCategCodeItr) {
		this.statusCategCodeItr = statusCategCodeItr;
	}
	/**
	 * @return the statusCategItr
	 */
	public String getStatusCategItr() {
		return statusCategItr;
	}
	/**
	 * @param statusCategItr the statusCategItr to set
	 */
	public void setStatusCategItr(String statusCategItr) {
		this.statusCategItr = statusCategItr;
	}
	/**
	 * @return the statusAbbrevItr
	 */
	public String getStatusAbbrevItr() {
		return statusAbbrevItr;
	}
	/**
	 * @param statusAbbrevItr the statusAbbrevItr to set
	 */
	public void setStatusAbbrevItr(String statusAbbrevItr) {
		this.statusAbbrevItr = statusAbbrevItr;
	}
	/**
	 * @return the statusCategCode
	 */
	public String getStatusCategCode() {
		return statusCategCode;
	}
	/**
	 * @param statusCategCode the statusCategCode to set
	 */
	public void setStatusCategCode(String statusCategCode) {
		this.statusCategCode = statusCategCode;
	}
	/**
	 * @return the statusCateg
	 */
	public String getStatusCateg() {
		return statusCateg;
	}
	/**
	 * @param statusCateg the statusCateg to set
	 */
	public void setStatusCateg(String statusCateg) {
		this.statusCateg = statusCateg;
	}
	/**
	 * @return the statusAbbrev
	 */
	public String getStatusAbbrev() {
		return statusAbbrev;
	}
	/**
	 * @param statusAbbrev the statusAbbrev to set
	 */
	public void setStatusAbbrev(String statusAbbrev) {
		this.statusAbbrev = statusAbbrev;
	}
	/**
	 * @return the statusOrder
	 */
	public String getStatusOrder() {
		return statusOrder;
	}
	/**
	 * @param statusOrder the statusOrder to set
	 */
	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}
	/**
	 * @return the isStatusLast
	 */
	public String getIsStatusLast() {
		return isStatusLast;
	}
	/**
	 * @param isStatusLast the isStatusLast to set
	 */
	public void setIsStatusLast(String isStatusLast) {
		this.isStatusLast = isStatusLast;
	}
	

}
