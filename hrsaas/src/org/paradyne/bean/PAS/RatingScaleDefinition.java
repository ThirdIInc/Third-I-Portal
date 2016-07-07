package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class RatingScaleDefinition extends BeanBase {
	
	private ArrayList lstAppraisal = null;
	private boolean flglstAppraisal = false;
	
	private String sAppId = null;
	private String sAppCode = null;
	private String sAppStartDate;
	private String sAppEndDate;
	private String paraId;
	private String isStarted;
	
	/* Question Rating Parameter */
	private String iSrNo = "0";
	private String sAppRatingId = null;
	private String sAppMinRating = null;
	private String sAppMaxRating = null;
	private String sAllowFraction = null;
	private String sAllowFractionFlg = null;
	private String sRatingType=null;
	private ArrayList lstQuestionRatingList = null;
	private boolean flgQuestionRatingList = false;
	
	private String addFlg = null;
	
	/* Question Rating  List */
	private String sDtlId = null;
	private String sAppQuestionRatingName = null;
	private String sAppQuestionRatingValue = null;
	
	private String sAppScoreFlg = null;
	private String iflg;
	
	/* Overall Score Description */
	private String iSrNoOverall = "0";
	private String sAppScoreId = null;
	private String sAppOverAllScoreFrom = null;
	private String sAppOverAllScoreTo = null;
	private String sAppOverAllScoreValue = null;
	private String sAppOverAllScoreDesc = null;
	private String sAppOverAllBellCurve = null;
	private ArrayList lstOverAllScoreList = null;
	private boolean flgOverAllScoreList = false;
	private String sTotalBullCurve = null;
	
	/* Navigation and Mode Parameter */
	private String sMode = null;
	private String myPage;
	private String hiddencode;
	private String readFlag="true";
	
	
	public String getSAppId() {
		return sAppId;
	}
	public void setSAppId(String appId) {
		sAppId = appId;
	}
	public String getSAppRatingId() {
		return sAppRatingId;
	}
	public void setSAppRatingId(String appRatingId) {
		sAppRatingId = appRatingId;
	}
	public String getSAppMinRating() {
		return sAppMinRating;
	}
	public void setSAppMinRating(String appMinRating) {
		sAppMinRating = appMinRating;
	}
	public String getSAppMaxRating() {
		return sAppMaxRating;
	}
	public void setSAppMaxRating(String appMaxRating) {
		sAppMaxRating = appMaxRating;
	}
	public String getSAllowFraction() {
		return sAllowFraction;
	}
	public void setSAllowFraction(String allowFraction) {
		sAllowFraction = allowFraction;
	}
	public String getSAppQuestionRatingName() {
		return sAppQuestionRatingName;
	}
	public void setSAppQuestionRatingName(String appQuestionRatingName) {
		sAppQuestionRatingName = appQuestionRatingName;
	}
	public String getSAppQuestionRatingValue() {
		return sAppQuestionRatingValue;
	}
	public void setSAppQuestionRatingValue(String appQuestionRatingValue) {
		sAppQuestionRatingValue = appQuestionRatingValue;
	}
	public String getSAppOverAllScoreFrom() {
		return sAppOverAllScoreFrom;
	}
	public void setSAppOverAllScoreFrom(String appOverAllScoreFrom) {
		sAppOverAllScoreFrom = appOverAllScoreFrom;
	}
	public String getSAppOverAllScoreTo() {
		return sAppOverAllScoreTo;
	}
	public void setSAppOverAllScoreTo(String appOverAllScoreTo) {
		sAppOverAllScoreTo = appOverAllScoreTo;
	}
	public String getSAppOverAllScoreValue() {
		return sAppOverAllScoreValue;
	}
	public void setSAppOverAllScoreValue(String appOverAllScoreValue) {
		sAppOverAllScoreValue = appOverAllScoreValue;
	}
	public String getSAppOverAllScoreDesc() {
		return sAppOverAllScoreDesc;
	}
	public void setSAppOverAllScoreDesc(String appOverAllScoreDesc) {
		sAppOverAllScoreDesc = appOverAllScoreDesc;
	}
	public String getSAppOverAllBellCurve() {
		return sAppOverAllBellCurve;
	}
	public void setSAppOverAllBellCurve(String appOverAllBellCurve) {
		sAppOverAllBellCurve = appOverAllBellCurve;
	}
	public String getSMode() {
		return sMode;
	}
	public void setSMode(String mode) {
		sMode = mode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	public ArrayList getLstAppraisal() {
		return lstAppraisal;
	}
	public void setLstAppraisal(ArrayList lstAppraisal) {
		this.lstAppraisal = lstAppraisal;
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
	public String getSAppCode() {
		return sAppCode;
	}
	public void setSAppCode(String appCode) {
		sAppCode = appCode;
	}
	public ArrayList getLstQuestionRatingList() {
		return lstQuestionRatingList;
	}
	public void setLstQuestionRatingList(ArrayList lstQuestionRatingList) {
		this.lstQuestionRatingList = lstQuestionRatingList;
	}
	public String getISrNo() {
		return iSrNo;
	}
	public void setISrNo(String srNo) {
		iSrNo = srNo;
	}
	public String getAddFlg() {
		return addFlg;
	}
	public void setAddFlg(String addFlg) {
		this.addFlg = addFlg;
	}
	public ArrayList getLstOverAllScoreList() {
		return lstOverAllScoreList;
	}
	public void setLstOverAllScoreList(ArrayList lstOverAllScoreList) {
		this.lstOverAllScoreList = lstOverAllScoreList;
	}
	public String getISrNoOverall() {
		return iSrNoOverall;
	}
	public void setISrNoOverall(String srNoOverall) {
		iSrNoOverall = srNoOverall;
	}
	public String getSAppScoreFlg() {
		return sAppScoreFlg;
	}
	public void setSAppScoreFlg(String appScoreFlg) {
		sAppScoreFlg = appScoreFlg;
	}
	public String getIflg() {
		return iflg;
	}
	public void setIflg(String iflg) {
		this.iflg = iflg;
	}
	public String getSAppScoreId() {
		return sAppScoreId;
	}
	public void setSAppScoreId(String appScoreId) {
		sAppScoreId = appScoreId;
	}
	public String getSDtlId() {
		return sDtlId;
	}
	public void setSDtlId(String dtlId) {
		sDtlId = dtlId;
	}
	public String getSAllowFractionFlg() {
		return sAllowFractionFlg;
	}
	public void setSAllowFractionFlg(String allowFractionFlg) {
		sAllowFractionFlg = allowFractionFlg;
	}
	public String getSTotalBullCurve() {
		return sTotalBullCurve;
	}
	public void setSTotalBullCurve(String totalBullCurve) {
		sTotalBullCurve = totalBullCurve;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public boolean isFlgQuestionRatingList() {
		return flgQuestionRatingList;
	}
	public void setFlgQuestionRatingList(boolean flgQuestionRatingList) {
		this.flgQuestionRatingList = flgQuestionRatingList;
	}
	public boolean isFlgOverAllScoreList() {
		return flgOverAllScoreList;
	}
	public void setFlgOverAllScoreList(boolean flgOverAllScoreList) {
		this.flgOverAllScoreList = flgOverAllScoreList;
	}
	public String getIsStarted() {
		return isStarted;
	}
	public void setIsStarted(String isStarted) {
		this.isStarted = isStarted;
	}
	public boolean isFlglstAppraisal() {
		return flglstAppraisal;
	}
	public void setFlglstAppraisal(boolean flglstAppraisal) {
		this.flglstAppraisal = flglstAppraisal;
	}
	public String getSRatingType() {
		return sRatingType;
	}
	public void setSRatingType(String ratingType) {
		sRatingType = ratingType;
	}
}
