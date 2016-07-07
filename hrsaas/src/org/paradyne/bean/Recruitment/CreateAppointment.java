package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author aa0540
 *
 */

public class CreateAppointment extends BeanBase {
		private String offerCode="";
		private String hiddenOfferCode="";
		private String buttonName = "";
		private String reqCodeOffer="";
		private String reqNameOffer="";
		private String empCodeOffer="";
		private String candCodeOffer="";
		private String canddNameOffer="";
		private String positionOffer="";
		private String hireMgrOffer="";
		private String signAuthorOfferCode="";
		private String signAuthorOffer="";
		private String dueDays="";
		private String offerDate="";
		private String offerAcceptedDate="";
		private String offerApprovedDate="";
		private String resumeOffer="";
		private String offerTemplate = "";
		private String chkSerch="";
		private boolean noData=false;
		
		ArrayList<Object> offerList = new ArrayList<Object>();
		
	 	private String offerStatus = "";
		private String offerDueFlag = "false";
		private boolean offerIssueFlag = false;
		private boolean offerAccFlag = false;
		private boolean offerRejFlag = false;
		private boolean offerCancelFlag = false;
		private boolean offerApprPendingFlag = false;
		private boolean offerApprFlag = false;
		private boolean offerApprRejFlag = false;
		private boolean noOfferDataFlag = false;
		
		private String appointmentCode="";
		private String hiddenAppointmentCode="";
		
		private String reqCodeAppointment="";
		private String reqNameAppointment="";
		private String empCodeAppointment="";
		private String canddNameAppointment="";
		private String candCodeAppointment="";
		private String positionAppointment="";
		private String hireMgrAppointment="";
		private String appointmentDate="";
		private String offeredCtc="";
		private String dueDaysAppointment="";
		private String appointmentAcceptedDate="";
		private String appointmentApprovedDate="";
		private String AppointmentOfferedCtc="";
		private String resumeAppointment="";
		private String appointTemplate = "";
		
		private String myPageOffer="";
		private String myPageApp="";
		
		private ArrayList<Object> appointmentList = new ArrayList<Object>();
		
		private String appointmentStatus = "";
		private boolean appointmentDueFlag = false;
		private boolean appointmentIssueFlag = false;
		private boolean appointmentAccFlag = false;
		private boolean appointmentRejFlag = false;
		private boolean appointmentCancelFlag = false;
		private boolean appointmentApprPendingFlag = false;
		private boolean appointmentApprFlag = false;
		private boolean appointmentApprRejFlag = false;
		private boolean noAppointmentDataFlag = false;
		
		private String show="";
		private boolean searchFlag=false;
		private boolean reqCodeFlag=false;
		private boolean candFlag=false;
		private boolean positionFlag=false;
		private boolean hiringFlag=false;
		private boolean dueDaysFlag=false;
		private boolean appFromFlag=false;
		private boolean appToFlag=false;
		private boolean accFromFlag=false;
		private boolean accToFlag=false;
		private boolean rejFromFlag=false;
		private boolean rejToFlag=false;
		private boolean canFromFlag=false;
		private boolean canToFlag=false;
		private boolean clearFlag=false;
		private String searchRequisitionCode="";
		private String searchHidRequisitionCode="";
		private String searchHidRequiredbyDate="";
		private String searchCandName="";
		private String searchCandCode="";
		private String searchPosition="";
		private String searchPositionId="";
		private String searchHiringMgr="";
		private String searchDueSinceDays="";
		private String appFrmDate="";
		private String appToDate="";
		private String appAccToDate="";
		private String appAccFrmDate="";
		private String appRejFrmDate="";
		private String appRejToDate="";
		private String appCanFrmDate="";
		private String appCanToDate="";
		private String searchHiringMgrId="";
		private boolean recordLength=false;
		private String totalRecords="";
		
		//Edited by Prashant starts
		private ArrayList keepInformedList = null;
		private String serialNo = "";
		private String keepInformedEmpName = "";
		private String keepInformedEmpId = "";
		//Edited by Prashant ends
		
		private String dataPath = "";
		private String joiningDateIterator = "";
		private String hiddenRequisitionCode = "";
		
		public String getJoiningDateIterator() {
			return joiningDateIterator;
		}
		public void setJoiningDateIterator(String joiningDateIterator) {
			this.joiningDateIterator = joiningDateIterator;
		}
		public String getDataPath() {
			return dataPath;
		}
		public void setDataPath(String dataPath) {
			this.dataPath = dataPath;
		}
		/**
		 * @return the offerCode
		 */
		public String getOfferCode() {
			return offerCode;
		}
		/**
		 * @param offerCode the offerCode to set
		 */
		public void setOfferCode(String offerCode) {
			this.offerCode = offerCode;
		}
		/**
		 * @return the reqCodeOffer
		 */
		public String getReqCodeOffer() {
			return reqCodeOffer;
		}
		/**
		 * @param reqCodeOffer the reqCodeOffer to set
		 */
		public void setReqCodeOffer(String reqCodeOffer) {
			this.reqCodeOffer = reqCodeOffer;
		}
		/**
		 * @return the reqNameOffer
		 */
		public String getReqNameOffer() {
			return reqNameOffer;
		}
		/**
		 * @param reqNameOffer the reqNameOffer to set
		 */
		public void setReqNameOffer(String reqNameOffer) {
			this.reqNameOffer = reqNameOffer;
		}
		/**
		 * @return the empCodeOffer
		 */
		public String getEmpCodeOffer() {
			return empCodeOffer;
		}
		/**
		 * @param empCodeOffer the empCodeOffer to set
		 */
		public void setEmpCodeOffer(String empCodeOffer) {
			this.empCodeOffer = empCodeOffer;
		}
		/**
		 * @return the candCodeOffer
		 */
		public String getCandCodeOffer() {
			return candCodeOffer;
		}
		/**
		 * @param candCodeOffer the candCodeOffer to set
		 */
		public void setCandCodeOffer(String candCodeOffer) {
			this.candCodeOffer = candCodeOffer;
		}
		/**
		 * @return the canddNameOffer
		 */
		public String getCanddNameOffer() {
			return canddNameOffer;
		}
		/**
		 * @param canddNameOffer the canddNameOffer to set
		 */
		public void setCanddNameOffer(String canddNameOffer) {
			this.canddNameOffer = canddNameOffer;
		}
		/**
		 * @return the positionOffer
		 */
		public String getPositionOffer() {
			return positionOffer;
		}
		/**
		 * @param positionOffer the positionOffer to set
		 */
		public void setPositionOffer(String positionOffer) {
			this.positionOffer = positionOffer;
		}
		/**
		 * @return the hireMgrOffer
		 */
		public String getHireMgrOffer() {
			return hireMgrOffer;
		}
		/**
		 * @param hireMgrOffer the hireMgrOffer to set
		 */
		public void setHireMgrOffer(String hireMgrOffer) {
			this.hireMgrOffer = hireMgrOffer;
		}
		/**
		 * @return the dueDays
		 */
		public String getDueDays() {
			return dueDays;
		}
		/**
		 * @param dueDays the dueDays to set
		 */
		public void setDueDays(String dueDays) {
			this.dueDays = dueDays;
		}
		/**
		 * @return the offerList
		 */
		public ArrayList<Object> getOfferList() {
			return offerList;
		}
		/**
		 * @param offerList the offerList to set
		 */
		public void setOfferList(ArrayList<Object> offerList) {
			this.offerList = offerList;
		}
		/**
		 * @return the offerStatus
		 */
		public String getOfferStatus() {
			return offerStatus;
		}
		/**
		 * @param offerStatus the offerStatus to set
		 */
		public void setOfferStatus(String offerStatus) {
			this.offerStatus = offerStatus;
		}
		
		/**
		 * @return the offerDueFlag
		 */
		public String getOfferDueFlag() {
			return offerDueFlag;
		}
		/**
		 * @param offerDueFlag the offerDueFlag to set
		 */
		public void setOfferDueFlag(String offerDueFlag) {
			this.offerDueFlag = offerDueFlag;
		}
		/**
		 * @return the offerIssueFlag
		 */
		public boolean isOfferIssueFlag() {
			return offerIssueFlag;
		}
		/**
		 * @param offerIssueFlag the offerIssueFlag to set
		 */
		public void setOfferIssueFlag(boolean offerIssueFlag) {
			this.offerIssueFlag = offerIssueFlag;
		}
		/**
		 * @return the offerAccFlag
		 */
		public boolean isOfferAccFlag() {
			return offerAccFlag;
		}
		/**
		 * @param offerAccFlag the offerAccFlag to set
		 */
		public void setOfferAccFlag(boolean offerAccFlag) {
			this.offerAccFlag = offerAccFlag;
		}
		/**
		 * @return the offerRejFlag
		 */
		public boolean isOfferRejFlag() {
			return offerRejFlag;
		}
		/**
		 * @param offerRejFlag the offerRejFlag to set
		 */
		public void setOfferRejFlag(boolean offerRejFlag) {
			this.offerRejFlag = offerRejFlag;
		}
		/**
		 * @return the offerApprFlag
		 */
		public boolean isOfferApprFlag() {
			return offerApprFlag;
		}
		/**
		 * @param offerApprFlag the offerApprFlag to set
		 */
		public void setOfferApprFlag(boolean offerApprFlag) {
			this.offerApprFlag = offerApprFlag;
		}
		/**
		 * @return the offerApprRejFlag
		 */
		public boolean isOfferApprRejFlag() {
			return offerApprRejFlag;
		}
		/**
		 * @param offerApprRejFlag the offerApprRejFlag to set
		 */
		public void setOfferApprRejFlag(boolean offerApprRejFlag) {
			this.offerApprRejFlag = offerApprRejFlag;
		}
		/**
		 * @return the noOfferDataFlag
		 */
		public boolean isNoOfferDataFlag() {
			return noOfferDataFlag;
		}
		/**
		 * @param noOfferDataFlag the noOfferDataFlag to set
		 */
		public void setNoOfferDataFlag(boolean noOfferDataFlag) {
			this.noOfferDataFlag = noOfferDataFlag;
		}
		/**
		 * @return the reqCodeAppointment
		 */
		public String getReqCodeAppointment() {
			return reqCodeAppointment;
		}
		/**
		 * @param reqCodeAppointment the reqCodeAppointment to set
		 */
		public void setReqCodeAppointment(String reqCodeAppointment) {
			this.reqCodeAppointment = reqCodeAppointment;
		}
		/**
		 * @return the reqNameAppointment
		 */
		public String getReqNameAppointment() {
			return reqNameAppointment;
		}
		/**
		 * @param reqNameAppointment the reqNameAppointment to set
		 */
		public void setReqNameAppointment(String reqNameAppointment) {
			this.reqNameAppointment = reqNameAppointment;
		}
		/**
		 * @return the empCodeAppointment
		 */
		public String getEmpCodeAppointment() {
			return empCodeAppointment;
		}
		/**
		 * @param empCodeAppointment the empCodeAppointment to set
		 */
		public void setEmpCodeAppointment(String empCodeAppointment) {
			this.empCodeAppointment = empCodeAppointment;
		}
		/**
		 * @return the canddNameAppointment
		 */
		public String getCanddNameAppointment() {
			return canddNameAppointment;
		}
		/**
		 * @param canddNameAppointment the canddNameAppointment to set
		 */
		public void setCanddNameAppointment(String canddNameAppointment) {
			this.canddNameAppointment = canddNameAppointment;
		}
		/**
		 * @return the candCodeAppointment
		 */
		public String getCandCodeAppointment() {
			return candCodeAppointment;
		}
		/**
		 * @param candCodeAppointment the candCodeAppointment to set
		 */
		public void setCandCodeAppointment(String candCodeAppointment) {
			this.candCodeAppointment = candCodeAppointment;
		}
		/**
		 * @return the positionAppointment
		 */
		public String getPositionAppointment() {
			return positionAppointment;
		}
		/**
		 * @param positionAppointment the positionAppointment to set
		 */
		public void setPositionAppointment(String positionAppointment) {
			this.positionAppointment = positionAppointment;
		}
		/**
		 * @return the hireMgrAppointment
		 */
		public String getHireMgrAppointment() {
			return hireMgrAppointment;
		}
		/**
		 * @param hireMgrAppointment the hireMgrAppointment to set
		 */
		public void setHireMgrAppointment(String hireMgrAppointment) {
			this.hireMgrAppointment = hireMgrAppointment;
		}
		/**
		 * @return the appointmentDate
		 */
		public String getAppointmentDate() {
			return appointmentDate;
		}
		/**
		 * @param appointmentDate the appointmentDate to set
		 */
		public void setAppointmentDate(String appointmentDate) {
			this.appointmentDate = appointmentDate;
		}
		/**
		 * @return the offeredCtc
		 */
		public String getOfferedCtc() {
			return offeredCtc;
		}
		/**
		 * @param offeredCtc the offeredCtc to set
		 */
		public void setOfferedCtc(String offeredCtc) {
			this.offeredCtc = offeredCtc;
		}
		/**
		 * @return the appointmentList
		 */
		public ArrayList<Object> getAppointmentList() {
			return appointmentList;
		}
		/**
		 * @param appointmentList the appointmentList to set
		 */
		public void setAppointmentList(ArrayList<Object> appointmentList) {
			this.appointmentList = appointmentList;
		}
		/**
		 * @return the appointmentStatus
		 */
		public String getAppointmentStatus() {
			return appointmentStatus;
		}
		/**
		 * @param appointmentStatus the appointmentStatus to set
		 */
		public void setAppointmentStatus(String appointmentStatus) {
			this.appointmentStatus = appointmentStatus;
		}
		/**
		 * @return the appointmentDueFlag
		 */
		public boolean isAppointmentDueFlag() {
			return appointmentDueFlag;
		}
		/**
		 * @param appointmentDueFlag the appointmentDueFlag to set
		 */
		public void setAppointmentDueFlag(boolean appointmentDueFlag) {
			this.appointmentDueFlag = appointmentDueFlag;
		}
		/**
		 * @return the appointmentIssueFlag
		 */
		public boolean isAppointmentIssueFlag() {
			return appointmentIssueFlag;
		}
		/**
		 * @param appointmentIssueFlag the appointmentIssueFlag to set
		 */
		public void setAppointmentIssueFlag(boolean appointmentIssueFlag) {
			this.appointmentIssueFlag = appointmentIssueFlag;
		}
		/**
		 * @return the appointmentAccFlag
		 */
		public boolean isAppointmentAccFlag() {
			return appointmentAccFlag;
		}
		/**
		 * @param appointmentAccFlag the appointmentAccFlag to set
		 */
		public void setAppointmentAccFlag(boolean appointmentAccFlag) {
			this.appointmentAccFlag = appointmentAccFlag;
		}
		/**
		 * @return the appointmentRejFlag
		 */
		public boolean isAppointmentRejFlag() {
			return appointmentRejFlag;
		}
		/**
		 * @param appointmentRejFlag the appointmentRejFlag to set
		 */
		public void setAppointmentRejFlag(boolean appointmentRejFlag) {
			this.appointmentRejFlag = appointmentRejFlag;
		}
		/**
		 * @return the appointmentApprFlag
		 */
		public boolean isAppointmentApprFlag() {
			return appointmentApprFlag;
		}
		/**
		 * @param appointmentApprFlag the appointmentApprFlag to set
		 */
		public void setAppointmentApprFlag(boolean appointmentApprFlag) {
			this.appointmentApprFlag = appointmentApprFlag;
		}
		/**
		 * @return the appointmentApprRejFlag
		 */
		public boolean isAppointmentApprRejFlag() {
			return appointmentApprRejFlag;
		}
		/**
		 * @param appointmentApprRejFlag the appointmentApprRejFlag to set
		 */
		public void setAppointmentApprRejFlag(boolean appointmentApprRejFlag) {
			this.appointmentApprRejFlag = appointmentApprRejFlag;
		}
		/**
		 * @return the noAppointmentDataFlag
		 */
		public boolean isNoAppointmentDataFlag() {
			return noAppointmentDataFlag;
		}
		/**
		 * @param noAppointmentDataFlag the noAppointmentDataFlag to set
		 */
		public void setNoAppointmentDataFlag(boolean noAppointmentDataFlag) {
			this.noAppointmentDataFlag = noAppointmentDataFlag;
		}
		/**
		 * @return the offerCancelFlag
		 */
		public boolean isOfferCancelFlag() {
			return offerCancelFlag;
		}
		/**
		 * @param offerCancelFlag the offerCancelFlag to set
		 */
		public void setOfferCancelFlag(boolean offerCancelFlag) {
			this.offerCancelFlag = offerCancelFlag;
		}
		/**
		 * @return the appointmentCancelFlag
		 */
		public boolean isAppointmentCancelFlag() {
			return appointmentCancelFlag;
		}
		/**
		 * @param appointmentCancelFlag the appointmentCancelFlag to set
		 */
		public void setAppointmentCancelFlag(boolean appointmentCancelFlag) {
			this.appointmentCancelFlag = appointmentCancelFlag;
		}
		/**
		 * @return the offerApprPendingFlag
		 */
		public boolean isOfferApprPendingFlag() {
			return offerApprPendingFlag;
		}
		/**
		 * @param offerApprPendingFlag the offerApprPendingFlag to set
		 */
		public void setOfferApprPendingFlag(boolean offerApprPendingFlag) {
			this.offerApprPendingFlag = offerApprPendingFlag;
		}
		/**
		 * @return the appointmentApprPendingFlag
		 */
		public boolean isAppointmentApprPendingFlag() {
			return appointmentApprPendingFlag;
		}
		/**
		 * @param appointmentApprPendingFlag the appointmentApprPendingFlag to set
		 */
		public void setAppointmentApprPendingFlag(boolean appointmentApprPendingFlag) {
			this.appointmentApprPendingFlag = appointmentApprPendingFlag;
		}
		/**
		 * @return the offerDate
		 */
		public String getOfferDate() {
			return offerDate;
		}
		/**
		 * @param offerDate the offerDate to set
		 */
		public void setOfferDate(String offerDate) {
			this.offerDate = offerDate;
		}
		/**
		 * @return the offerAcceptedDate
		 */
		public String getOfferAcceptedDate() {
			return offerAcceptedDate;
		}
		/**
		 * @param offerAcceptedDate the offerAcceptedDate to set
		 */
		public void setOfferAcceptedDate(String offerAcceptedDate) {
			this.offerAcceptedDate = offerAcceptedDate;
		}
		/**
		 * @return the offerApprovedDate
		 */
		public String getOfferApprovedDate() {
			return offerApprovedDate;
		}
		/**
		 * @param offerApprovedDate the offerApprovedDate to set
		 */
		public void setOfferApprovedDate(String offerApprovedDate) {
			this.offerApprovedDate = offerApprovedDate;
		}
		/**
		 * @return the hiddenOfferCode
		 */
		public String getHiddenOfferCode() {
			return hiddenOfferCode;
		}
		/**
		 * @param hiddenOfferCode the hiddenOfferCode to set
		 */
		public void setHiddenOfferCode(String hiddenOfferCode) {
			this.hiddenOfferCode = hiddenOfferCode;
		}
		/**
		 * @return the appointmentCode
		 */
		public String getAppointmentCode() {
			return appointmentCode;
		}
		/**
		 * @param appointmentCode the appointmentCode to set
		 */
		public void setAppointmentCode(String appointmentCode) {
			this.appointmentCode = appointmentCode;
		}
		/**
		 * @return the hiddenAppointmentCode
		 */
		public String getHiddenAppointmentCode() {
			return hiddenAppointmentCode;
		}
		/**
		 * @param hiddenAppointmentCode the hiddenAppointmentCode to set
		 */
		public void setHiddenAppointmentCode(String hiddenAppointmentCode) {
			this.hiddenAppointmentCode = hiddenAppointmentCode;
		}
		/**
		 * @return the dueDaysAppointment
		 */
		public String getDueDaysAppointment() {
			return dueDaysAppointment;
		}
		/**
		 * @param dueDaysAppointment the dueDaysAppointment to set
		 */
		public void setDueDaysAppointment(String dueDaysAppointment) {
			this.dueDaysAppointment = dueDaysAppointment;
		}
		/**
		 * @return the appointmentAcceptedDate
		 */
		public String getAppointmentAcceptedDate() {
			return appointmentAcceptedDate;
		}
		/**
		 * @param appointmentAcceptedDate the appointmentAcceptedDate to set
		 */
		public void setAppointmentAcceptedDate(String appointmentAcceptedDate) {
			this.appointmentAcceptedDate = appointmentAcceptedDate;
		}
		/**
		 * @return the appointmentApprovedDate
		 */
		public String getAppointmentApprovedDate() {
			return appointmentApprovedDate;
		}
		/**
		 * @param appointmentApprovedDate the appointmentApprovedDate to set
		 */
		public void setAppointmentApprovedDate(String appointmentApprovedDate) {
			this.appointmentApprovedDate = appointmentApprovedDate;
		}
		/**
		 * @return the appointmentOfferedCtc
		 */
		public String getAppointmentOfferedCtc() {
			return AppointmentOfferedCtc;
		}
		/**
		 * @param appointmentOfferedCtc the appointmentOfferedCtc to set
		 */
		public void setAppointmentOfferedCtc(String appointmentOfferedCtc) {
			AppointmentOfferedCtc = appointmentOfferedCtc;
		}
		public String getButtonName() {
			return buttonName;
		}
		public void setButtonName(String buttonName) {
			this.buttonName = buttonName;
		}
		/**
		 * @return the resumeOffer
		 */
		public String getResumeOffer() {
			return resumeOffer;
		}
		/**
		 * @param resumeOffer the resumeOffer to set
		 */
		public void setResumeOffer(String resumeOffer) {
			this.resumeOffer = resumeOffer;
		}
		/**
		 * @return the resumeAppointment
		 */
		public String getResumeAppointment() {
			return resumeAppointment;
		}
		/**
		 * @param resumeAppointment the resumeAppointment to set
		 */
		public void setResumeAppointment(String resumeAppointment) {
			this.resumeAppointment = resumeAppointment;
		}
		public String getMyPageOffer() {
			return myPageOffer;
		}
		public void setMyPageOffer(String myPageOffer) {
			this.myPageOffer = myPageOffer;
		}
		public String getMyPageApp() {
			return myPageApp;
		}
		public void setMyPageApp(String myPageApp) {
			this.myPageApp = myPageApp;
		}
		/**
		 * @return the offerTemplate
		 */
		public String getOfferTemplate() {
			return offerTemplate;
		}
		/**
		 * @param offerTemplate the offerTemplate to set
		 */
		public void setOfferTemplate(String offerTemplate) {
			this.offerTemplate = offerTemplate;
		}
		/**
		 * @return the appointTemplate
		 */
		public String getAppointTemplate() {
			return appointTemplate;
		}
		/**
		 * @param appointTemplate the appointTemplate to set
		 */
		public void setAppointTemplate(String appointTemplate) {
			this.appointTemplate = appointTemplate;
		}
		public String getShow() {
			return show;
		}
		public void setShow(String show) {
			this.show = show;
		}
		public boolean isSearchFlag() {
			return searchFlag;
		}
		public void setSearchFlag(boolean searchFlag) {
			this.searchFlag = searchFlag;
		}
		public boolean isReqCodeFlag() {
			return reqCodeFlag;
		}
		public void setReqCodeFlag(boolean reqCodeFlag) {
			this.reqCodeFlag = reqCodeFlag;
		}
		public boolean isCandFlag() {
			return candFlag;
		}
		public void setCandFlag(boolean candFlag) {
			this.candFlag = candFlag;
		}
		public boolean isPositionFlag() {
			return positionFlag;
		}
		public void setPositionFlag(boolean positionFlag) {
			this.positionFlag = positionFlag;
		}
		public boolean isHiringFlag() {
			return hiringFlag;
		}
		public void setHiringFlag(boolean hiringFlag) {
			this.hiringFlag = hiringFlag;
		}
		public boolean isDueDaysFlag() {
			return dueDaysFlag;
		}
		public void setDueDaysFlag(boolean dueDaysFlag) {
			this.dueDaysFlag = dueDaysFlag;
		}
		public boolean isAppFromFlag() {
			return appFromFlag;
		}
		public void setAppFromFlag(boolean appFromFlag) {
			this.appFromFlag = appFromFlag;
		}
		public boolean isAppToFlag() {
			return appToFlag;
		}
		public void setAppToFlag(boolean appToFlag) {
			this.appToFlag = appToFlag;
		}
		public boolean isAccFromFlag() {
			return accFromFlag;
		}
		public void setAccFromFlag(boolean accFromFlag) {
			this.accFromFlag = accFromFlag;
		}
		public boolean isAccToFlag() {
			return accToFlag;
		}
		public void setAccToFlag(boolean accToFlag) {
			this.accToFlag = accToFlag;
		}
		public boolean isRejFromFlag() {
			return rejFromFlag;
		}
		public void setRejFromFlag(boolean rejFromFlag) {
			this.rejFromFlag = rejFromFlag;
		}
		public boolean isRejToFlag() {
			return rejToFlag;
		}
		public void setRejToFlag(boolean rejToFlag) {
			this.rejToFlag = rejToFlag;
		}
		public boolean isCanFromFlag() {
			return canFromFlag;
		}
		public void setCanFromFlag(boolean canFromFlag) {
			this.canFromFlag = canFromFlag;
		}
		public boolean isCanToFlag() {
			return canToFlag;
		}
		public void setCanToFlag(boolean canToFlag) {
			this.canToFlag = canToFlag;
		}
		public boolean isClearFlag() {
			return clearFlag;
		}
		public void setClearFlag(boolean clearFlag) {
			this.clearFlag = clearFlag;
		}
		public String getSearchRequisitionCode() {
			return searchRequisitionCode;
		}
		public void setSearchRequisitionCode(String searchRequisitionCode) {
			this.searchRequisitionCode = searchRequisitionCode;
		}
		public String getSearchHidRequisitionCode() {
			return searchHidRequisitionCode;
		}
		public void setSearchHidRequisitionCode(String searchHidRequisitionCode) {
			this.searchHidRequisitionCode = searchHidRequisitionCode;
		}
		public String getSearchCandName() {
			return searchCandName;
		}
		public void setSearchCandName(String searchCandName) {
			this.searchCandName = searchCandName;
		}
		public String getSearchCandCode() {
			return searchCandCode;
		}
		public void setSearchCandCode(String searchCandCode) {
			this.searchCandCode = searchCandCode;
		}
		public String getSearchPosition() {
			return searchPosition;
		}
		public void setSearchPosition(String searchPosition) {
			this.searchPosition = searchPosition;
		}
		public String getSearchPositionId() {
			return searchPositionId;
		}
		public void setSearchPositionId(String searchPositionId) {
			this.searchPositionId = searchPositionId;
		}
		public String getSearchHiringMgr() {
			return searchHiringMgr;
		}
		public void setSearchHiringMgr(String searchHiringMgr) {
			this.searchHiringMgr = searchHiringMgr;
		}
		public String getSearchDueSinceDays() {
			return searchDueSinceDays;
		}
		public void setSearchDueSinceDays(String searchDueSinceDays) {
			this.searchDueSinceDays = searchDueSinceDays;
		}
		public String getAppFrmDate() {
			return appFrmDate;
		}
		public void setAppFrmDate(String appFrmDate) {
			this.appFrmDate = appFrmDate;
		}
		public String getAppToDate() {
			return appToDate;
		}
		public void setAppToDate(String appToDate) {
			this.appToDate = appToDate;
		}
		public String getAppAccToDate() {
			return appAccToDate;
		}
		public void setAppAccToDate(String appAccToDate) {
			this.appAccToDate = appAccToDate;
		}
		public String getAppAccFrmDate() {
			return appAccFrmDate;
		}
		public void setAppAccFrmDate(String appAccFrmDate) {
			this.appAccFrmDate = appAccFrmDate;
		}
		public String getAppRejFrmDate() {
			return appRejFrmDate;
		}
		public void setAppRejFrmDate(String appRejFrmDate) {
			this.appRejFrmDate = appRejFrmDate;
		}
		public String getAppRejToDate() {
			return appRejToDate;
		}
		public void setAppRejToDate(String appRejToDate) {
			this.appRejToDate = appRejToDate;
		}
		public String getAppCanFrmDate() {
			return appCanFrmDate;
		}
		public void setAppCanFrmDate(String appCanFrmDate) {
			this.appCanFrmDate = appCanFrmDate;
		}
		public String getAppCanToDate() {
			return appCanToDate;
		}
		public void setAppCanToDate(String appCanToDate) {
			this.appCanToDate = appCanToDate;
		}
		public String getSearchHiringMgrId() {
			return searchHiringMgrId;
		}
		public void setSearchHiringMgrId(String searchHiringMgrId) {
			this.searchHiringMgrId = searchHiringMgrId;
		}
		public boolean isRecordLength() {
			return recordLength;
		}
		public void setRecordLength(boolean recordLength) {
			this.recordLength = recordLength;
		}
		public String getChkSerch() {
			return chkSerch;
		}
		public void setChkSerch(String chkSerch) {
			this.chkSerch = chkSerch;
		}
		public String getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}
		public String getSignAuthorOfferCode() {
			return signAuthorOfferCode;
		}
		public void setSignAuthorOfferCode(String signAuthorOfferCode) {
			this.signAuthorOfferCode = signAuthorOfferCode;
		}
		public String getSignAuthorOffer() {
			return signAuthorOffer;
		}
		public void setSignAuthorOffer(String signAuthorOffer) {
			this.signAuthorOffer = signAuthorOffer;
		}
		public boolean isNoData() {
			return noData;
		}
		public void setNoData(boolean noData) {
			this.noData = noData;
		}
		public String getSearchHidRequiredbyDate() {
			return searchHidRequiredbyDate;
		}
		public void setSearchHidRequiredbyDate(String searchHidRequiredbyDate) {
			this.searchHidRequiredbyDate = searchHidRequiredbyDate;
		}
		public ArrayList getKeepInformedList() {
			return keepInformedList;
		}
		public void setKeepInformedList(ArrayList keepInformedList) {
			this.keepInformedList = keepInformedList;
		}
		public String getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
		}
		public String getKeepInformedEmpName() {
			return keepInformedEmpName;
		}
		public void setKeepInformedEmpName(String keepInformedEmpName) {
			this.keepInformedEmpName = keepInformedEmpName;
		}
		public String getKeepInformedEmpId() {
			return keepInformedEmpId;
		}
		public void setKeepInformedEmpId(String keepInformedEmpId) {
			this.keepInformedEmpId = keepInformedEmpId;
		}
		 
		public String getHiddenRequisitionCode() {
			return hiddenRequisitionCode;
		}
		public void setHiddenRequisitionCode(String hiddenRequisitionCode) {
			this.hiddenRequisitionCode = hiddenRequisitionCode;
		}
}
