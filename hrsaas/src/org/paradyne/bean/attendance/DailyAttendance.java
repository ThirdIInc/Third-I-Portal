/**
 * Bhushan
 * Feb 29, 2008
**/

package org.paradyne.bean.attendance;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class DailyAttendance extends BeanBase
{
	private String payBillNo = "";
	private String payBillName = "";
	private String typeCode = "";
	private String typeName = "";
	private String brnCode = "";
	private String brnName = "";
	private String deptCode = "";
	private String deptName = "";
	private String divCode = "";
	private String divName = "";
	private String brnFlag;
	private String deptFlag;
	private String payBillFlag;
	private String typeFlag;
	private String divFlag;
	private String fromDate;
	private String toDate;
	private ArrayList<Object> attdnList;
	private String eId;
	private String eToken;
	private String eName;
	private String eDate;
	private String eSId;
	private String eShift;
	private String eInTime;
	private String eOutTime;
	private String eWHrs;
	private String eExHrs;
	private String eLtHrs;
	private String eErHrs;
	private boolean eDlLate = false;
	private boolean eHfDay = false;
	private boolean eWOff = false;
	private boolean eHlDay = false;
	private boolean eAbsDay = false;
	private boolean flag = false;
	private String hdPage;
	private String fromTotRec;
	private String toTotRec;
	private String eStatus1;
	private String eStatus2;
	private String eFilNo;
	private String eFilName;
	private String eFilToken;
	private String srchStatus1;
	private String srchStatus2;
	private String bulkStatus1;
	private String bulkStatus2;
	private boolean showExtraHoursFlag;
	
	public boolean isShowExtraHoursFlag() {
		return showExtraHoursFlag;
	}
	public void setShowExtraHoursFlag(boolean showExtraHoursFlag) {
		this.showExtraHoursFlag = showExtraHoursFlag;
	}
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBrnCode() {
		return brnCode;
	}
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getBrnFlag() {
		return brnFlag;
	}
	public void setBrnFlag(String brnFlag) {
		this.brnFlag = brnFlag;
	}
	public String getDeptFlag() {
		return deptFlag;
	}
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}
	public String getPayBillFlag() {
		return payBillFlag;
	}
	public void setPayBillFlag(String payBillFlag) {
		this.payBillFlag = payBillFlag;
	}
	public String getTypeFlag() {
		return typeFlag;
	}
	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}
	public String getDivFlag() {
		return divFlag;
	}
	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public ArrayList<Object> getAttdnList() {
		return attdnList;
	}
	public void setAttdnList(ArrayList<Object> attdnList) {
		this.attdnList = attdnList;
	}
	public String getEId() {
		return eId;
	}
	public void setEId(String id) {
		eId = id;
	}
	public String getEToken() {
		return eToken;
	}
	public void setEToken(String token) {
		eToken = token;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String name) {
		eName = name;
	}
	public String getEDate() {
		return eDate;
	}
	public void setEDate(String date) {
		eDate = date;
	}
	public String getESId() {
		return eSId;
	}
	public void setESId(String id) {
		eSId = id;
	}
	public String getEShift() {
		return eShift;
	}
	public void setEShift(String shift) {
		eShift = shift;
	}
	public String getEInTime() {
		return eInTime;
	}
	public void setEInTime(String inTime) {
		eInTime = inTime;
	}
	public String getEOutTime() {
		return eOutTime;
	}
	public void setEOutTime(String outTime) {
		eOutTime = outTime;
	}
	public String getEWHrs() {
		return eWHrs;
	}
	public void setEWHrs(String hrs) {
		eWHrs = hrs;
	}
	public String getEExHrs() {
		return eExHrs;
	}
	public void setEExHrs(String exHrs) {
		eExHrs = exHrs;
	}
	public String getELtHrs() {
		return eLtHrs;
	}
	public void setELtHrs(String ltHrs) {
		eLtHrs = ltHrs;
	}
	public String getEErHrs() {
		return eErHrs;
	}
	public void setEErHrs(String erHrs) {
		eErHrs = erHrs;
	}
	public boolean isEDlLate() {
		return eDlLate;
	}
	public void setEDlLate(boolean dlLate) {
		eDlLate = dlLate;
	}
	public boolean isEHfDay() {
		return eHfDay;
	}
	public void setEHfDay(boolean hfDay) {
		eHfDay = hfDay;
	}
	public boolean isEWOff() {
		return eWOff;
	}
	public void setEWOff(boolean off) {
		eWOff = off;
	}
	public boolean isEHlDay() {
		return eHlDay;
	}
	public void setEHlDay(boolean hlDay) {
		eHlDay = hlDay;
	}
	public boolean isEAbsDay() {
		return eAbsDay;
	}
	public void setEAbsDay(boolean absDay) {
		eAbsDay = absDay;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	public String getEStatus1() {
		return eStatus1;
	}
	public void setEStatus1(String status1) {
		eStatus1 = status1;
	}
	public String getEStatus2() {
		return eStatus2;
	}
	public void setEStatus2(String status2) {
		eStatus2 = status2;
	}
	public String getEFilNo() {
		return eFilNo;
	}
	public void setEFilNo(String filNo) {
		eFilNo = filNo;
	}
	public String getEFilName() {
		return eFilName;
	}
	public void setEFilName(String filName) {
		eFilName = filName;
	}
	public String getEFilToken() {
		return eFilToken;
	}
	public void setEFilToken(String filToken) {
		eFilToken = filToken;
	}
	public String getSrchStatus1() {
		return srchStatus1;
	}
	public void setSrchStatus1(String srchStatus1) {
		this.srchStatus1 = srchStatus1;
	}
	public String getSrchStatus2() {
		return srchStatus2;
	}
	public void setSrchStatus2(String srchStatus2) {
		this.srchStatus2 = srchStatus2;
	}
	public String getBulkStatus1() {
		return bulkStatus1;
	}
	public void setBulkStatus1(String bulkStatus1) {
		this.bulkStatus1 = bulkStatus1;
	}
	public String getBulkStatus2() {
		return bulkStatus2;
	}
	public void setBulkStatus2(String bulkStatus2) {
		this.bulkStatus2 = bulkStatus2;
	}
}