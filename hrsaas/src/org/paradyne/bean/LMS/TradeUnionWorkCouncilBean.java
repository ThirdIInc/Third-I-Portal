package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TradeUnionWorkCouncilBean extends BeanBase {

	private String tradeUnionWorkCouncilID = "";
	private String tradeUnionWorkCouncildeleteCode ="";	
	private String  trNoAddRow="false";
	private String committeeType="";
	private String committeeName="";
	private String leaderName="";
	private String checkRemoveUpload="";
	private String dataPath="";
	private String proofSrNo="";
	private String proofName="";
	private String proofFileName="";
	private String boardMemberName = "";
	private String boardMemberType = "";
	private String agrrementExpiryDate="";
	private String agreementDetails="";
	private String agreementName="";
	private String procedureName="";
	private String procedureDetails ="";
	private String bargainingAgreement="";
	private String procedureGrievance="";
	private String show="";
	ArrayList<Object>  tradeUnionWorkCouncilItt=null ;
	
	ArrayList<Object>  boardMembersDetailsList=null ;
	
	private String boardMemberId = "";
	private String myPage="";
	private String modeLength ="";
	private String totalRecords ="";
	
	private String  bargainAggreement="false";    
	ArrayList bargainAggreementList = new ArrayList();
	ArrayList procedureGrievanceList = new ArrayList();
	private String uploadLocFileName="";
	private String uploadProcGrievanceLocFileName="";
	
	private String committeeDtlId = "";
	private String committeeAgreementGrievanceId="";
	
	private String bargAgrrOption = ""; // radiobutton
	
	private String procgrievanceOption = ""; // radiobutton
	
	private String radioValue="";
	
	public String getRadioValue() {
		return radioValue;
	}
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}
	
	public String getCommitteeDtlId() {
		return committeeDtlId;
	}
	public void setCommitteeDtlId(String committeeDtlId) {
		this.committeeDtlId = committeeDtlId;
	}
	public String getCommitteeAgreementGrievanceId() {
		return committeeAgreementGrievanceId;
	}
	public void setCommitteeAgreementGrievanceId(
			String committeeAgreementGrievanceId) {
		this.committeeAgreementGrievanceId = committeeAgreementGrievanceId;
	}
	public String getUploadLocFileName() {
		return uploadLocFileName;
	}
	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}
	public ArrayList<Object> getTradeUnionWorkCouncilItt() {
		return tradeUnionWorkCouncilItt;
	}
	public void setTradeUnionWorkCouncilItt(
			ArrayList<Object> tradeUnionWorkCouncilItt) {
		this.tradeUnionWorkCouncilItt = tradeUnionWorkCouncilItt;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getProcedureGrievance() {
		return procedureGrievance;
	}
	public void setProcedureGrievance(String procedureGrievance) {
		this.procedureGrievance = procedureGrievance;
	}
	public String getBargainingAgreement() {
		return bargainingAgreement;
	}
	public void setBargainingAgreement(String bargainingAgreement) {
		this.bargainingAgreement = bargainingAgreement;
	}
	public String getProofSrNo() {
		return proofSrNo;
	}
	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}
	public String getProofName() {
		return proofName;
	}
	public void setProofName(String proofName) {
		this.proofName = proofName;
	}
	public String getProofFileName() {
		return proofFileName;
	}
	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}
	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}
	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getTrNoAddRow() {
		return trNoAddRow;
	}
	public void setTrNoAddRow(String trNoAddRow) {
		this.trNoAddRow = trNoAddRow;
	}
	public String getCommitteeType() {
		return committeeType;
	}
	public void setCommitteeType(String committeeType) {
		this.committeeType = committeeType;
	}
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getTradeUnionWorkCouncilID() {
		return tradeUnionWorkCouncilID;
	}
	public void setTradeUnionWorkCouncilID(String tradeUnionWorkCouncilID) {
		this.tradeUnionWorkCouncilID = tradeUnionWorkCouncilID;
	}
	public String getBoardMemberName() {
		return boardMemberName;
	}
	public void setBoardMemberName(String boardMemberName) {
		this.boardMemberName = boardMemberName;
	}
	public String getBoardMemberType() {
		return boardMemberType;
	}
	public void setBoardMemberType(String boardMemberType) {
		this.boardMemberType = boardMemberType;
	}
	public String getAgrrementExpiryDate() {
		return agrrementExpiryDate;
	}
	public void setAgrrementExpiryDate(String agrrementExpiryDate) {
		this.agrrementExpiryDate = agrrementExpiryDate;
	}
	public String getAgreementDetails() {
		return agreementDetails;
	}
	public void setAgreementDetails(String agreementDetails) {
		this.agreementDetails = agreementDetails;
	}
	public String getAgreementName() {
		return agreementName;
	}
	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getProcedureDetails() {
		return procedureDetails;
	}
	public void setProcedureDetails(String procedureDetails) {
		this.procedureDetails = procedureDetails;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getTradeUnionWorkCouncildeleteCode() {
		return tradeUnionWorkCouncildeleteCode;
	}
	public void setTradeUnionWorkCouncildeleteCode(
			String tradeUnionWorkCouncildeleteCode) {
		this.tradeUnionWorkCouncildeleteCode = tradeUnionWorkCouncildeleteCode;
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
	public String getBoardMemberId() {
		return boardMemberId;
	}
	public void setBoardMemberId(String boardMemberId) {
		this.boardMemberId = boardMemberId;
	}
	public String getBargainAggreement() {
		return bargainAggreement;
	}
	public void setBargainAggreement(String bargainAggreement) {
		this.bargainAggreement = bargainAggreement;
	}
	public ArrayList getBargainAggreementList() {
		return bargainAggreementList;
	}
	public void setBargainAggreementList(ArrayList bargainAggreementList) {
		this.bargainAggreementList = bargainAggreementList;
	}
	public ArrayList<Object> getBoardMembersDetailsList() {
		return boardMembersDetailsList;
	}
	public void setBoardMembersDetailsList(ArrayList<Object> boardMembersDetailsList) {
		this.boardMembersDetailsList = boardMembersDetailsList;
	}
	public String getUploadProcGrievanceLocFileName() {
		return uploadProcGrievanceLocFileName;
	}
	public void setUploadProcGrievanceLocFileName(
			String uploadProcGrievanceLocFileName) {
		this.uploadProcGrievanceLocFileName = uploadProcGrievanceLocFileName;
	}
	public ArrayList getProcedureGrievanceList() {
		return procedureGrievanceList;
	}
	public void setProcedureGrievanceList(ArrayList procedureGrievanceList) {
		this.procedureGrievanceList = procedureGrievanceList;
	}
	public String getBargAgrrOption() {
		return bargAgrrOption;
	}
	public void setBargAgrrOption(String bargAgrrOption) {
		this.bargAgrrOption = bargAgrrOption;
	}
	public String getProcgrievanceOption() {
		return procgrievanceOption;
	}
	public void setProcgrievanceOption(String procgrievanceOption) {
		this.procgrievanceOption = procgrievanceOption;
	}
	
}
