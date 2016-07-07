/**
 * @author saipavan voleti
 * 25-08-2008
 *
 */
package org.paradyne.bean.settings;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class Suggestion extends BeanBase {
	
	private String source ="";
	private String sub="";
	private String hiddenCode_sg="";
	private String suggestionCode="";
	private String suggestionName="";
	private String suggestionDate="";
	private String suggestionSub="";
	private String suggestion;
	
	private ArrayList<Object> list_suggestion;
	
	//added by sai new suggestion form 
	private String suggestionFlag;
	private String suggcode; 
	private String empName;
	private String ecode; 
	private String empAppr; 
	private String suggDate;
	private String sugg;
	private String suggimprove;
	private String suggimple;
	private String hrconclusion;
	private String isApprove;
	//private String empAppr;
	private String eToken;
	
	private String apprName;
	private String apprDate;
	private String apprComments;
	private String approvalStatus;
	
	private Boolean listFlag=false;
	
	ArrayList approveList;
	
	
	
	 
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getHiddenCode_sg() {
		return hiddenCode_sg;
	}
	public void setHiddenCode_sg(String hiddenCode_sg) {
		this.hiddenCode_sg = hiddenCode_sg;
	}
	public String getSuggestionCode() {
		return suggestionCode;
	}
	public void setSuggestionCode(String suggestionCode) {
		this.suggestionCode = suggestionCode;
	}
	public String getSuggestionName() {
		return suggestionName;
	}
	public void setSuggestionName(String suggestionName) {
		this.suggestionName = suggestionName;
	}
	public ArrayList<Object> getList_suggestion() {
		return list_suggestion;
	}
	public void setList_suggestion(ArrayList<Object> list_suggestion) {
		this.list_suggestion = list_suggestion;
	}
	public String getSuggestionDate() {
		return suggestionDate;
	}
	public void setSuggestionDate(String suggestionDate) {
		this.suggestionDate = suggestionDate;
	}
	public String getSuggestionSub() {
		return suggestionSub;
	}
	public void setSuggestionSub(String suggestionSub) {
		this.suggestionSub = suggestionSub;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getSuggestionFlag() {
		return suggestionFlag;
	}
	public void setSuggestionFlag(String suggestionFlag) {
		this.suggestionFlag = suggestionFlag;
	}
	public String getSuggcode() {
		return suggcode;
	}
	public void setSuggcode(String suggcode) {
		this.suggcode = suggcode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getEmpAppr() {
		return empAppr;
	}
	public void setEmpAppr(String empAppr) {
		this.empAppr = empAppr;
	}
	public String getSuggDate() {
		return suggDate;
	}
	public void setSuggDate(String suggDate) {
		this.suggDate = suggDate;
	}
	public String getSugg() {
		return sugg;
	}
	public void setSugg(String sugg) {
		this.sugg = sugg;
	}
	
	public String getSuggimple() {
		return suggimple;
	}
	public void setSuggimple(String suggimple) {
		this.suggimple = suggimple;
	}
	public String getHrconclusion() {
		return hrconclusion;
	}
	public void setHrconclusion(String hrconclusion) {
		this.hrconclusion = hrconclusion;
	}
	public String getSuggimprove() {
		return suggimprove;
	}
	public void setSuggimprove(String suggimprove) {
		this.suggimprove = suggimprove;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public String getApprName() {
		return apprName;
	}
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public ArrayList getApproveList() {
		return approveList;
	}
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getEToken() {
		return eToken;
	}
	public void setEToken(String token) {
		eToken = token;
	}
	public Boolean getListFlag() {
		return listFlag;
	}
	public void setListFlag(Boolean listFlag) {
		this.listFlag = listFlag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

}
