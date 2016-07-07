/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 * @date 25 APR 2007
 */
public class DebitHeadMaster extends BeanBase {
	
	private String debitCode;
	private String debitName;
	private String debitAbbr;
	private String authority="";
	private String autorDate="";
	private ArrayList debitList = null;
	
	public DebitHeadMaster(){
		
	}
	/**
	 * @param debitCode
	 * @param debitName
	 * @param debitAbbr
	 * @param authority
	 * @param autorDate
	 * @param debitList
	 */
	public DebitHeadMaster(String debitCode, String debitName, String debitAbbr, String authority, String autorDate, ArrayList cityList) {
		super();
		this.debitCode = debitCode;
		this.debitName = debitName;
		this.debitAbbr = debitAbbr;
		this.authority = authority;
		this.autorDate = autorDate;
		this.debitList = cityList;
	}
	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @return the autorDate
	 */
	public String getAutorDate() {
		return autorDate;
	}
	
	/**
	 * @return the debitList
	 */
	public ArrayList getDebitList() {
		return debitList;
	}
	/**
	 * @param debitList the debitList to set
	 */
	public void setDebitList(ArrayList debitList) {
		this.debitList = debitList;
	}
	/**
	 * @return the debitAbbr
	 */
	public String getDebitAbbr() {
		return debitAbbr;
	}
	/**
	 * @return the debitCode
	 */
	public String getDebitCode() {
		return debitCode;
	}
	/**
	 * @return the debitName
	 */
	public String getDebitName() {
		return debitName;
	}
	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	/**
	 * @param autorDate the autorDate to set
	 */
	public void setAutorDate(String autorDate) {
		this.autorDate = autorDate;
	}
	
	/**
	 * @param debitAbbr the debitAbbr to set
	 */
	public void setDebitAbbr(String debitAbbr) {
		this.debitAbbr = debitAbbr;
	}
	/**
	 * @param debitCode the debitCode to set
	 */
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	/**
	 * @param debitName the debitName to set
	 */
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
}
