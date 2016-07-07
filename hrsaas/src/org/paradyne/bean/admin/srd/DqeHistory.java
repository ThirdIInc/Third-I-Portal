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
public class DqeHistory extends BeanBase implements Serializable {
	
	public String dqeId="";
	public String empId="";
	public String empName="";
	public String tokenNo="";
	public String rank="";
	public String center="";
	public String post="";
	public String postId="";
	public String trade="";
	public String tradeId="";
	public String date="";
	public String dcList="";
	public String dcListId="";
	public String serialNo="";
	public String paracode="";
	private ArrayList dqeList ;
	public String dcListDate="";
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the dcList
	 */
	public String getDcList() {
		return dcList;
	}
	/**
	 * @param dcList the dcList to set
	 */
	public void setDcList(String dcList) {
		this.dcList = dcList;
	}
	/**
	 * @return the dcListId
	 */
	public String getDcListId() {
		return dcListId;
	}
	/**
	 * @param dcListId the dcListId to set
	 */
	public void setDcListId(String dcListId) {
		this.dcListId = dcListId;
	}
	
	/**
	 * @return the dqeId
	 */
	public String getDqeId() {
		return dqeId;
	}
	/**
	 * @param dqeId the dqeId to set
	 */
	public void setDqeId(String dqeId) {
		this.dqeId = dqeId;
	}
	/**
	 * @return the dqeList
	 */
	public ArrayList getDqeList() {
		return dqeList;
	}
	/**
	 * @param dqeList the dqeList to set
	 */
	public void setDqeList(ArrayList dqeList) {
		this.dqeList = dqeList;
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
	 * @return the dcListDate
	 */
	public String getDcListDate() {
		return dcListDate;
	}
	/**
	 * @param dcListDate the dcListDate to set
	 */
	public void setDcListDate(String dcListDate) {
		this.dcListDate = dcListDate;
	}

}
