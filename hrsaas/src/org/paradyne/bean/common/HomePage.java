/**
 * 
 */
package org.paradyne.bean.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 * 
 */
public class HomePage extends BeanBase {

	private String myPage = "";
	private boolean pageFlag = false;

	private String bdayListFlag = "false";
	private String holdayListFlag = "false";
	private String newJoinFlag = "false";
	private String addListFlag = "false";
	private String pollCode;
	private String quesName;
	private String optionCode;
	private String hdOptionCode;
	private String optionName;
	private ArrayList optionList;
	private HashMap optionMap;
	private String optionValue;
	private String quickLink = "";
	private HashMap quickMap;
	private String prevPollCode;
	private String prevQuesName;
	private String expiry;

	private String subject = "";
	private String sender = "";
	private String Date = "";
	private String noOfMails = "";
	private String msgDesc = "";
	private String msgCode = "";

	private ArrayList mailList = null;

	private String msgFrom = "";
	private String msgTo = "";
	private String msgcc = "";
	private String msgBcc = "";
	private String msgSubject = "";
	private String msgText = "";
	private String mailSize = "";
	private boolean attChkFlag = false;
	private String msgAttach = "";
	private int totalVotes = 0;
	private String clickType = "P";
	private boolean backFlag = false;
	private boolean prevFlag = false;
	private boolean nextFlag = false;
	private boolean statFlag = false;
	private boolean hmPrevFlag = false;
	// Added for show messages link of CEO Connect
	private String ceoFlag = "";
	private String ceoPresentFlag="";
	private String ceoMsgCode = "";
	private String ceoMsgSub = "";
	private String ceoMsgDesc = "";
	private String ceoMsgDate = "";
	private String modeLength = "";
	private String totalRecords = "";
	private ArrayList<HomePage> msgList = null;
	private String msgFromName="";
	private String ceoMsgSubAbbr="";
	private String ceoMsgDateAbbr="";
	public ArrayList<HomePage> getMsgList() {
		return msgList;
	}

	public void setMsgList(ArrayList<HomePage> msgList) {
		this.msgList = msgList;
	}

	// end Added for show messages link of CEO Connect
	/**
	 * @return ceoMsgCode
	 */
	public String getCeoMsgCode() {
		return ceoMsgCode;
	}

	/**
	 * @param ceoMsgCode
	 *            the ceoMsgCode to set
	 */
	public void setCeoMsgCode(String ceoMsgCode) {
		this.ceoMsgCode = ceoMsgCode;
	}

	/**
	 * @return ceoMsgSub
	 */
	public String getCeoMsgSub() {
		return ceoMsgSub;
	}

	/**
	 * @param ceoMsgSub
	 *            the ceoMsgSub to set
	 */
	public void setCeoMsgSub(String ceoMsgSub) {
		this.ceoMsgSub = ceoMsgSub;
	}

	/**
	 * @return ceoMsgDesc
	 */
	public String getCeoMsgDesc() {
		return ceoMsgDesc;
	}

	/**
	 * @param ceoMsgDesc
	 *            the ceoMsgDesc to set
	 */
	public void setCeoMsgDesc(String ceoMsgDesc) {
		this.ceoMsgDesc = ceoMsgDesc;
	}

	/**
	 * @return ceoMsgDate
	 */
	public String getCeoMsgDate() {
		return ceoMsgDate;
	}

	/**
	 * @param ceoMsgDate
	 *            the ceoMsgDate to set
	 */
	public void setCeoMsgDate(String ceoMsgDate) {
		this.ceoMsgDate = ceoMsgDate;
	}

	/**
	 * @return backFlag
	 */
	public boolean isBackFlag() {
		return backFlag;
	}

	/**
	 * @param backFlag
	 *            the backFlag to set
	 */
	public void setBackFlag(boolean backFlag) {
		this.backFlag = backFlag;
	}

	/**
	 * @return totalVotes
	 */
	public int getTotalVotes() {
		return totalVotes;
	}

	/**
	 * @param totalVotes
	 *            the totalVotes to set
	 */
	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	/**
	 * @return msgAttach
	 */
	public String getMsgAttach() {
		return msgAttach;
	}

	/**
	 * @param msgAttach
	 *            the msgAttach to set
	 */
	public void setMsgAttach(String msgAttach) {
		this.msgAttach = msgAttach;
	}

	/**
	 * @return the bdayListFlag
	 */
	public String getBdayListFlag() {
		return bdayListFlag;
	}

	/**
	 * @param bdayListFlag
	 *            the bdayListFlag to set
	 */
	public void setBdayListFlag(String bdayListFlag) {
		this.bdayListFlag = bdayListFlag;
	}

	/**
	 * @return the holdayListFlag
	 */
	public String getHoldayListFlag() {
		return holdayListFlag;
	}

	/**
	 * @param holdayListFlag
	 *            the holdayListFlag to set
	 */
	public void setHoldayListFlag(String holdayListFlag) {
		this.holdayListFlag = holdayListFlag;
	}

	/**
	 * @return the newJoinFlag
	 */
	public String getNewJoinFlag() {
		return newJoinFlag;
	}

	/**
	 * @param newJoinFlag
	 *            the newJoinFlag to set
	 */
	public void setNewJoinFlag(String newJoinFlag) {
		this.newJoinFlag = newJoinFlag;
	}

	/**
	 * @return the addListFlag
	 */
	public String getAddListFlag() {
		return addListFlag;
	}

	/**
	 * @param addListFlag
	 *            the addListFlag to set
	 */
	public void setAddListFlag(String addListFlag) {
		this.addListFlag = addListFlag;
	}

	/**
	 * @return the pollCode
	 */
	public String getPollCode() {
		return pollCode;
	}

	/**
	 * @param pollCode
	 *            the pollCode to set
	 */
	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}

	/**
	 * @return the quesName
	 */
	public String getQuesName() {
		return quesName;
	}

	/**
	 * @param quesName
	 *            the quesName to set
	 */
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}

	/**
	 * @return the optionCode
	 */
	public String getOptionCode() {
		return optionCode;
	}

	/**
	 * @param optionCode
	 *            the optionCode to set
	 */
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	/**
	 * @return the optionName
	 */
	public String getOptionName() {
		return optionName;
	}

	/**
	 * @param optionName
	 *            the optionName to set
	 */
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	/**
	 * @return the optionList
	 */
	public ArrayList getOptionList() {
		return optionList;
	}

	/**
	 * @param optionList
	 *            the optionList to set
	 */
	public void setOptionList(ArrayList optionList) {
		this.optionList = optionList;
	}

	/**
	 * @return the hdOptionCode
	 */
	public String getHdOptionCode() {
		return hdOptionCode;
	}

	/**
	 * @param hdOptionCode
	 *            the hdOptionCode to set
	 */
	public void setHdOptionCode(String hdOptionCode) {
		this.hdOptionCode = hdOptionCode;
	}

	/**
	 * @return the optionMap
	 */
	public HashMap getOptionMap() {
		return optionMap;
	}

	/**
	 * @param optionMap
	 *            the optionMap to set
	 */
	public void setOptionMap(HashMap optionMap) {
		this.optionMap = optionMap;
	}

	/**
	 * @return the optionValue
	 */
	public String getOptionValue() {
		return optionValue;
	}

	/**
	 * @param optionValue
	 *            the optionValue to set
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	/**
	 * @return the quickLink
	 */
	public String getQuickLink() {
		return quickLink;
	}

	/**
	 * @param quickLink
	 *            the quickLink to set
	 */
	public void setQuickLink(String quickLink) {
		this.quickLink = quickLink;
	}

	/**
	 * @return the quickMap
	 */
	public HashMap getQuickMap() {
		return quickMap;
	}

	/**
	 * @param quickMap
	 *            the quickMap to set
	 */
	public void setQuickMap(HashMap quickMap) {
		this.quickMap = quickMap;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public ArrayList getMailList() {
		return mailList;
	}

	public void setMailList(ArrayList mailList) {
		this.mailList = mailList;
	}

	public String getNoOfMails() {
		return noOfMails;
	}

	public void setNoOfMails(String noOfMails) {
		this.noOfMails = noOfMails;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}

	public String getMsgcc() {
		return msgcc;
	}

	public void setMsgcc(String msgcc) {
		this.msgcc = msgcc;
	}

	public String getMsgBcc() {
		return msgBcc;
	}

	public void setMsgBcc(String msgBcc) {
		this.msgBcc = msgBcc;
	}

	public String getMsgSubject() {
		return msgSubject;
	}

	public void setMsgSubject(String msgSubject) {
		this.msgSubject = msgSubject;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public String getMailSize() {
		return mailSize;
	}

	public void setMailSize(String mailSize) {
		this.mailSize = mailSize;
	}

	public boolean isAttChkFlag() {
		return attChkFlag;
	}

	public void setAttChkFlag(boolean attChkFlag) {
		this.attChkFlag = attChkFlag;
	}

	public String getPrevPollCode() {
		return prevPollCode;
	}

	public void setPrevPollCode(String prevPollCode) {
		this.prevPollCode = prevPollCode;
	}

	public String getPrevQuesName() {
		return prevQuesName;
	}

	public void setPrevQuesName(String prevQuesName) {
		this.prevQuesName = prevQuesName;
	}

	public String getClickType() {
		return clickType;
	}

	public void setClickType(String clickType) {
		this.clickType = clickType;
	}

	public boolean isPrevFlag() {
		return prevFlag;
	}

	public void setPrevFlag(boolean prevFlag) {
		this.prevFlag = prevFlag;
	}

	public boolean isNextFlag() {
		return nextFlag;
	}

	public void setNextFlag(boolean nextFlag) {
		this.nextFlag = nextFlag;
	}

	public boolean isStatFlag() {
		return statFlag;
	}

	public void setStatFlag(boolean statFlag) {
		this.statFlag = statFlag;
	}

	public boolean isHmPrevFlag() {
		return hmPrevFlag;
	}

	public void setHmPrevFlag(boolean hmPrevFlag) {
		this.hmPrevFlag = hmPrevFlag;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public boolean isPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}

	/**
	 * @return ceoFlag
	 */
	public String getCeoFlag() {
		return ceoFlag;
	}

	/**
	 * @param ceoFlag
	 *            the ceoFlag to set
	 */
	public void setCeoFlag(String ceoFlag) {
		this.ceoFlag = ceoFlag;
	}

	/**
	 * @return modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}

	/**
	 * @param modeLength
	 *            the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getMsgFromName() {
		return msgFromName;
	}

	public void setMsgFromName(String msgFromName) {
		this.msgFromName = msgFromName;
	}

	public String getCeoMsgSubAbbr() {
		return ceoMsgSubAbbr;
	}

	public void setCeoMsgSubAbbr(String ceoMsgSubAbbr) {
		this.ceoMsgSubAbbr = ceoMsgSubAbbr;
	}

	public String getCeoMsgDateAbbr() {
		return ceoMsgDateAbbr;
	}

	public void setCeoMsgDateAbbr(String ceoMsgDateAbbr) {
		this.ceoMsgDateAbbr = ceoMsgDateAbbr;
	}

	/**
	 * @return the ceoPresentFlag
	 */
	public String getCeoPresentFlag() {
		return ceoPresentFlag;
	}

	/**
	 * @param ceoPresentFlag the ceoPresentFlag to set
	 */
	public void setCeoPresentFlag(String ceoPresentFlag) {
		this.ceoPresentFlag = ceoPresentFlag;
	}
}
