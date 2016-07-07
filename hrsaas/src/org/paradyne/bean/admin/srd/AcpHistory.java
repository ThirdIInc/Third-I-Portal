/**
 * 
 */
package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author riteshr
 *
 */
public class AcpHistory extends BeanBase implements Serializable {
	
	public String acpId="";
	public String empId="";
	public String empName="";
	public String tokenNo="";
	public String rank="";
	public String center="";
	public String post="";
	public String postId="";
	public String trade="";
	public String tradeId="";
	public String fromDate="";
	public String payScale="";
	public String payScaleId="";
	public String serialNo="";
	public String paracode="";
	public String dceListNo="";
	public String dceListDate="";
	
	private ArrayList acpList ;
	/**
	 * @return the acpId
	 */
	public String getAcpId() {
		return acpId;
	}
	/**
	 * @param acpId the acpId to set
	 */
	public void setAcpId(String acpId) {
		this.acpId = acpId;
	}
	/**
	 * @return the acpList
	 */
	public ArrayList getAcpList() {
		return acpList;
	}
	/**
	 * @param acpList the acpList to set
	 */
	public void setAcpList(ArrayList acpList) {
		this.acpList = acpList;
	}
	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * @param paracode the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	/**
	 * @return the payScale
	 */
	public String getPayScale() {
		return payScale;
	}
	/**
	 * @param payScale the payScale to set
	 */
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}
	/**
	 * @return the payScaleId
	 */
	public String getPayScaleId() {
		return payScaleId;
	}
	/**
	 * @param payScaleId the payScaleId to set
	 */
	public void setPayScaleId(String payScaleId) {
		this.payScaleId = payScaleId;
	}
	/**
	 * @return the post
	 */
	public String getPost() {
		return post;
	}
	/**
	 * @param post the post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}
	/**
	 * @return the postId
	 */
	public String getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}
	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * @return the trade
	 */
	public String getTrade() {
		return trade;
	}
	/**
	 * @param trade the trade to set
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}
	/**
	 * @return the tradeId
	 */
	public String getTradeId() {
		return tradeId;
	}
	/**
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * @return the tokenNo
	 */
	public String getTokenNo() {
		return tokenNo;
	}
	/**
	 * @param tokenNo the tokenNo to set
	 */
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	/**
	 * @return the dceListDate
	 */
	public String getDceListDate() {
		return dceListDate;
	}
	/**
	 * @param dceListDate the dceListDate to set
	 */
	public void setDceListDate(String dceListDate) {
		this.dceListDate = dceListDate;
	}
	/**
	 * @return the dceListNo
	 */
	public String getDceListNo() {
		return dceListNo;
	}
	/**
	 * @param dceListNo the dceListNo to set
	 */
	public void setDceListNo(String dceListNo) {
		this.dceListNo = dceListNo;
	}

}
