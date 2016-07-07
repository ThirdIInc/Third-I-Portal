package org.paradyne.bean.eGov.reports;
import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.D1.NewHireRehire;
import org.paradyne.lib.BeanBase;
/**
 * 
 * @author pradeep kumar
 * @modified by Reeba 
 *
 */
public class LeaveCreditConfigeGovBean extends BeanBase{
	private String leaveConfigId="";
	private String  noData="false";
	private String modeLength="false";
	private String totalRecords="";
	
	private String leaveId="";
	private String leaveName="";
	
	private String debitCode = "";
	private String debitName = "";
	private String debitAbbr = "";
	private String myPage = "";
	
	private List<LeaveCreditConfigeGovBean> leaveConfigList;
	
	private List<LeaveCreditConfigeGovBean> debitHeadList;
	
	private List<LeaveCreditConfigeGovBean> debitHeadListLength;
	private List<LeaveCreditConfigeGovBean> saveList;
	
	private String hLeaveCode="";
	ArrayList arr=null;
	private String frmId="";
	private String hiddenfrmId="";
	private String hiddenCode="";
	private String hdeleteCode;
	
        /**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
		public String getHiddenfrmId() {
                return hiddenfrmId;
        }
        public void setHiddenfrmId(String hiddenfrmId) {
                this.hiddenfrmId = hiddenfrmId;
        }
        public String getFrmId() {
                return frmId;
        }
        public void setFrmId(String frmId) {
                this.frmId = frmId;
        }
        public ArrayList getArr() {
                return arr;
        }
        public void setArr(ArrayList arr) {
                this.arr = arr;
        }
        public String getHLeaveCode() {
                return hLeaveCode;
        }
        public void setHLeaveCode(String leaveCode) {
                hLeaveCode = leaveCode;
        }
        public String getMyPage() {
                return myPage;
        }
        public void setMyPage(String myPage) {
                this.myPage = myPage;
        }
        public String getDebitCode() {
                return debitCode;
        }
        public void setDebitCode(String debitCode) {
                this.debitCode = debitCode;
        }
        public String getDebitName() {
                return debitName;
        }
        public void setDebitName(String debitName) {
                this.debitName = debitName;
        }
        public String getDebitAbbr() {
                return debitAbbr;
        }
        public void setDebitAbbr(String debitAbbr) {
                this.debitAbbr = debitAbbr;
        }
        public String getLeaveConfigId() {
                return leaveConfigId;
        }
        public void setLeaveConfigId(String leaveConfigId) {
                this.leaveConfigId = leaveConfigId;
        }
        public String getNoData() {
                return noData;
        }
        public void setNoData(String noData) {
                this.noData = noData;
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
        public String getLeaveId() {
                return leaveId;
        }
        public void setLeaveId(String leaveId) {
                this.leaveId = leaveId;
        }
        
        public List<LeaveCreditConfigeGovBean> getDebitHeadList() {
                return debitHeadList;
        }
        public void setDebitHeadList(List<LeaveCreditConfigeGovBean> debitHeadList) {
                this.debitHeadList = debitHeadList;
        }
        public List<LeaveCreditConfigeGovBean> getDebitHeadListLength() {
                return debitHeadListLength;
        }
        public void setDebitHeadListLength(
                        List<LeaveCreditConfigeGovBean> debitHeadListLength) {
                this.debitHeadListLength = debitHeadListLength;
        }
        public String getLeaveName() {
                return leaveName;
        }
        public void setLeaveName(String leaveName) {
                this.leaveName = leaveName;
        }
        public List<LeaveCreditConfigeGovBean> getLeaveConfigList() {
                return leaveConfigList;
        }
        public void setLeaveConfigList(List<LeaveCreditConfigeGovBean> leaveConfigList) {
                this.leaveConfigList = leaveConfigList;
        }
        public List<LeaveCreditConfigeGovBean> getSaveList() {
                return saveList;
        }
        public void setSaveList(List<LeaveCreditConfigeGovBean> saveList) {
                this.saveList = saveList;
        }
        public String getHiddenCode() {
                return hiddenCode;
        }
        public void setHiddenCode(String hiddenCode) {
                this.hiddenCode = hiddenCode;
        }
        
       
	
	
	
	
	
	
	
	
	
	
		
}
