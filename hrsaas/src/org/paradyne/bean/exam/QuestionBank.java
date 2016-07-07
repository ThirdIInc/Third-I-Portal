package org.paradyne.bean.exam;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:20-01-2009
 *
 */
public class QuestionBank extends BeanBase {
	private String dataPath = "";
	private String uploadFileName = "";
	private boolean viewUploadDocFlag = false;
	private String allowToUploadAnswer = "";
	private String flagShow="false";
	private String limFlag="false";
	private String optionFlag="false";
	private String optionId;
	private String paraId;
	private String hoption;
	private String hiddencode;
	private String cancelFlag="false";
	private String selectname;
	private String subject="";
	private String ansOptions;
	private String subjectCode;
	private String categoryCode;
	private String category;
	private String qsnWtg;
	private String compLevel;
	private String qsnStatus;
	private String hflag;
	private String flagView="false";
	private String chkOpt;
	private String question="";
	private String questionAbbr="";
	private String limit;
	private String mark="";
	private String upload="";
	private String uploadImage="";
	private String subjective="";
	private String optionCode="";
	private String option="";
	private String optionItt="";
	private String flag="";
	private String level="";
	private String srNo="";
	private String quesDesc="";
	private String quesType="";
	private String quesCode="";
	private String optionName="";
	private ArrayList<Object> optionList;
	private ArrayList<Object> list;
	private ArrayList<Object> questionList;
	private String tableLength="";
	private String hiddenEdit="";
	private String hiddensub="";
	private String questionItt="";
	private String hiddenView="";
	private String selectquesCode="";
	private String checkFlag_cs="false";
	private String markItt="";
	private String quesCodeItt="";
	private String quesTypeItt="";
	private String quesLevelItt="";
	private String quesDescItt="";
	private String optionTextarea="";
	private String questionView="false";
	private String quesChoiceLen =""; // To show limited Length of Choice
	
	private String flagItt;
	private String hiddenCode;
	private String questionCode;
	
	private String hiddenCode_option="";
	
	private String myPage;
	
	private String show;
	private String concatFlg="";
	
	private String hdeleteCode;
	private String editFlag="false";
	private boolean ansFlgView=false;
	private String concatEx="";
	private String optionString="";
	private String flagString="";
	private String totRec="";
	private String chkAll="";
	private String noData="false";
	private String pageNoField="";
	private String totalPage="";
	private String fromFlag="";
	private String from="";
	
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getTotRec() {
		return totRec;
	}
	public void setTotRec(String totRec) {
		this.totRec = totRec;
	}
	public String getChkAll() {
		return chkAll;
	}
	public void setChkAll(String chkAll) {
		this.chkAll = chkAll;
	}
	public String getOptionString() {
		return optionString;
	}
	public void setOptionString(String optionString) {
		this.optionString = optionString;
	}
	public String getFlagString() {
		return flagString;
	}
	public void setFlagString(String flagString) {
		this.flagString = flagString;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getHiddenCode_option() {
		return hiddenCode_option;
	}
	public void setHiddenCode_option(String hiddenCode_option) {
		this.hiddenCode_option = hiddenCode_option;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSubjective() {
		return subjective;
	}
	public void setSubjective(String subjective) {
		this.subjective = subjective;
	}
	public String getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getOptionItt() {
		return optionItt;
	}
	public void setOptionItt(String optionItt) {
		this.optionItt = optionItt;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getQuesDesc() {
		return quesDesc;
	}
	public void setQuesDesc(String quesDesc) {
		this.quesDesc = quesDesc;
	}
	public String getQuesType() {
		return quesType;
	}
	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}
	public String getQuesCode() {
		return quesCode;
	}
	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
	}	
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getFlagItt() {
		return flagItt;
	}
	public void setFlagItt(String flagItt) {
		this.flagItt = flagItt;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getHiddensub() {
		return hiddensub;
	}
	public void setHiddensub(String hiddensub) {
		this.hiddensub = hiddensub;
	}
	public String getQuestionItt() {
		return questionItt;
	}
	public void setQuestionItt(String questionItt) {
		this.questionItt = questionItt;
	}
	public String getHiddenView() {
		return hiddenView;
	}
	public void setHiddenView(String hiddenView) {
		this.hiddenView = hiddenView;
	}
	public String getSelectquesCode() {
		return selectquesCode;
	}
	public void setSelectquesCode(String selectquesCode) {
		this.selectquesCode = selectquesCode;
	}
	public String getQuesCodeItt() {
		return quesCodeItt;
	}
	public void setQuesCodeItt(String quesCodeItt) {
		this.quesCodeItt = quesCodeItt;
	}
	public String getQuesTypeItt() {
		return quesTypeItt;
	}
	public void setQuesTypeItt(String quesTypeItt) {
		this.quesTypeItt = quesTypeItt;
	}
	public String getQuesLevelItt() {
		return quesLevelItt;
	}
	public void setQuesLevelItt(String quesLevelItt) {
		this.quesLevelItt = quesLevelItt;
	}
	public String getQuesDescItt() {
		return quesDescItt;
	}
	public void setQuesDescItt(String quesDescItt) {
		this.quesDescItt = quesDescItt;
	}
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public String getCheckFlag_cs() {
		return checkFlag_cs;
	}
	public void setCheckFlag_cs(String checkFlag_cs) {
		this.checkFlag_cs = checkFlag_cs;
	}
	
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getUploadImage() {
		return uploadImage;
	}
	public void setUploadImage(String uploadImage) {
		this.uploadImage = uploadImage;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getMarkItt() {
		return markItt;
	}
	public void setMarkItt(String markItt) {
		this.markItt = markItt;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQsnWtg() {
		return qsnWtg;
	}
	public void setQsnWtg(String qsnWtg) {
		this.qsnWtg = qsnWtg;
	}
	public String getCompLevel() {
		return compLevel;
	}
	public void setCompLevel(String compLevel) {
		this.compLevel = compLevel;
	}
	public String getQsnStatus() {
		return qsnStatus;
	}
	public void setQsnStatus(String qsnStatus) {
		this.qsnStatus = qsnStatus;
	}
	public String getHflag() {
		return hflag;
	}
	public void setHflag(String hflag) {
		this.hflag = hflag;
	}
	public String getChkOpt() {
		return chkOpt;
	}
	public void setChkOpt(String chkOpt) {
		this.chkOpt = chkOpt;
	}
	public String getAnsOptions() {
		return ansOptions;
	}
	public void setAnsOptions(String ansOptions) {
		this.ansOptions = ansOptions;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getFlagView() {
		return flagView;
	}
	public void setFlagView(String flagView) {
		this.flagView = flagView;
	}
	public String getHoption() {
		return hoption;
	}
	public void setHoption(String hoption) {
		this.hoption = hoption;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getOptionFlag() {
		return optionFlag;
	}
	public void setOptionFlag(String optionFlag) {
		this.optionFlag = optionFlag;
	}
	public String getLimFlag() {
		return limFlag;
	}
	public void setLimFlag(String limFlag) {
		this.limFlag = limFlag;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public boolean isAnsFlgView() {
		return ansFlgView;
	}
	public void setAnsFlgView(boolean ansFlgView) {
		this.ansFlgView = ansFlgView;
	}
	public String getConcatEx() {
		return concatEx;
	}
	public void setConcatEx(String concatEx) {
		this.concatEx = concatEx;
	}
	public String getConcatFlg() {
		return concatFlg;
	}
	public void setConcatFlg(String concatFlg) {
		this.concatFlg = concatFlg;
	}
	public String getOptionTextarea() {
		return optionTextarea;
	}
	public void setOptionTextarea(String optionTextarea) {
		this.optionTextarea = optionTextarea;
	}
	public String getQuestionView() {
		return questionView;
	}
	public void setQuestionView(String questionView) {
		this.questionView = questionView;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getFlagShow() {
		return flagShow;
	}
	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return this.uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the viewUploadDocFlag
	 */
	public boolean isViewUploadDocFlag() {
		return this.viewUploadDocFlag;
	}
	/**
	 * @param viewUploadDocFlag the viewUploadDocFlag to set
	 */
	public void setViewUploadDocFlag(boolean viewUploadDocFlag) {
		this.viewUploadDocFlag = viewUploadDocFlag;
	}
	/**
	 * @return the allowToUploadAnswer
	 */
	public String getAllowToUploadAnswer() {
		return this.allowToUploadAnswer;
	}
	/**
	 * @param allowToUploadAnswer the allowToUploadAnswer to set
	 */
	public void setAllowToUploadAnswer(String allowToUploadAnswer) {
		this.allowToUploadAnswer = allowToUploadAnswer;
	}
	/**
	 * @return the fromFlag
	 */
	public String getFromFlag() {
		return fromFlag;
	}
	/**
	 * @param fromFlag the fromFlag to set
	 */
	public void setFromFlag(String fromFlag) {
		this.fromFlag = fromFlag;
	}
	/**
	 * @return the questionAbbr
	 */
	public String getQuestionAbbr() {
		return questionAbbr;
	}
	/**
	 * @param questionAbbr the questionAbbr to set
	 */
	public void setQuestionAbbr(String questionAbbr) {
		this.questionAbbr = questionAbbr;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getQuesChoiceLen() {
		return quesChoiceLen;
	}
	public void setQuesChoiceLen(String quesChoiceLen) {
		this.quesChoiceLen = quesChoiceLen;
	}
	public ArrayList<Object> getOptionList() {
		return optionList;
	}
	public void setOptionList(ArrayList<Object> optionList) {
		this.optionList = optionList;
	}
	public ArrayList<Object> getList() {
		return list;
	}
	public void setList(ArrayList<Object> list) {
		this.list = list;
	}
	public ArrayList<Object> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(ArrayList<Object> questionList) {
		this.questionList = questionList;
	}
	

	
}
