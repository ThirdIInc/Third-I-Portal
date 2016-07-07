package org.paradyne.bean.vendor;
/** @ Author : Archana Salunkhe
 * @ purpose : Developed to define Vendor Reporting Structure 
 * @ Date : 03-May-2012
 */
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class VendorReportStructure extends BeanBase {
	private String partnerCode= "";
	private String approverType ="";
	private String level="";
	private String approverName="";
	private String approverCode="";
	private String partnerName="";
	private String myPage="";
	private String modeLength="";
	private ArrayList reportStrList;
	private String totalRecords="";
	private String hiddenPartnerID="";
	private String hiddenApproverID="";
	private String approverToken="";
	private String reportingID="";
	
	/*For List page*/
	private String partnerCodeItt= "";
	private String approverTypeItt ="";
	private String approverNameItt="";
	private String approverCodeItt="";
	private String partnerNameItt="";
	
	
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getApproverType() {
		return approverType;
	}
	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public ArrayList getReportStrList() {
		return reportStrList;
	}
	public void setReportStrList(ArrayList reportStrList) {
		this.reportStrList = reportStrList;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getHiddenApproverID() {
		return hiddenApproverID;
	}
	public void setHiddenApproverID(String hiddenApproverID) {
		this.hiddenApproverID = hiddenApproverID;
	}
	public String getHiddenPartnerID() {
		return hiddenPartnerID;
	}
	public void setHiddenPartnerID(String hiddenPartnerID) {
		this.hiddenPartnerID = hiddenPartnerID;
	}
	public String getApproverToken() {
		return approverToken;
	}
	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}
	public String getReportingID() {
		return reportingID;
	}
	public void setReportingID(String reportingID) {
		this.reportingID = reportingID;
	}
	public String getPartnerCodeItt() {
		return partnerCodeItt;
	}
	public void setPartnerCodeItt(String partnerCodeItt) {
		this.partnerCodeItt = partnerCodeItt;
	}
	public String getApproverTypeItt() {
		return approverTypeItt;
	}
	public void setApproverTypeItt(String approverTypeItt) {
		this.approverTypeItt = approverTypeItt;
	}
	public String getApproverNameItt() {
		return approverNameItt;
	}
	public void setApproverNameItt(String approverNameItt) {
		this.approverNameItt = approverNameItt;
	}
	public String getApproverCodeItt() {
		return approverCodeItt;
	}
	public void setApproverCodeItt(String approverCodeItt) {
		this.approverCodeItt = approverCodeItt;
	}
	public String getPartnerNameItt() {
		return partnerNameItt;
	}
	public void setPartnerNameItt(String partnerNameItt) {
		this.partnerNameItt = partnerNameItt;
	}
}
