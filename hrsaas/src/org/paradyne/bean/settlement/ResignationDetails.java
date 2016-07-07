package org.paradyne.bean.settlement;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class ResignationDetails extends BeanBase
{
  private String regCode;
  private String empCode;
  private String empName;
  private String branch;
  private String department;
  private String designation;
  private String grade;
  private String dateofjoin;
  private String noticePeriod;
  private String resignDate;
  private String acceptDate;
  private String sepdate;
  private String reason;
  private String remark;
  private String status;
  private String employeeToken;
  private String withDrawn;
  private String today = "";
  private String noticeperiodselect = "";
  private String apprToken = "";
  private String apprName = "";
  private String apprCode = "";
  private String noticePeriodActual = "";
  private String waveOffPeriod = "";
  private String accByToken = "";
  private String acceptedBy = "";
  private String accByCode = "";

  private String lockFlag = "";
  private boolean templateFlag = false;

  private String listFlag = "false";
  private String myPage;
  private String noData = "false";
  private String modeLength = "false";
  private String totalRecords = "";
  private ArrayList tableList = null;
  private String empTokenItt = "";
  private String empNameItt = "";
  private String empCodeItt = "";
  private String resignDateItt = "";
  private String resignCodeItt = "";
  private String hiddencode = "";
  private String descCnt2 = "";
  private String hrComments = "";

  private String resignEmployeeToken = "";
  private String resignEmployeeName = "";
  private String resignationDate = "";
  private ArrayList resignList = null;
  private String cadreName = "";

  public String getCadreName() {
    return this.cadreName;
  }

  public void setCadreName(String cadreName) {
    this.cadreName = cadreName;
  }

  public String getResignEmployeeToken() {
    return this.resignEmployeeToken;
  }

  public void setResignEmployeeToken(String resignEmployeeToken) {
    this.resignEmployeeToken = resignEmployeeToken;
  }

  public String getResignEmployeeName() {
    return this.resignEmployeeName;
  }

  public void setResignEmployeeName(String resignEmployeeName) {
    this.resignEmployeeName = resignEmployeeName;
  }

  public String getResignationDate() {
    return this.resignationDate;
  }

  public void setResignationDate(String resignationDate) {
    this.resignationDate = resignationDate;
  }

  public ArrayList getResignList() {
    return this.resignList;
  }

  public void setResignList(ArrayList resignList) {
    this.resignList = resignList;
  }

  public String getHiddencode()
  {
    return this.hiddencode;
  }

  public void setHiddencode(String hiddencode)
  {
    this.hiddencode = hiddencode;
  }

  public String getResignCodeItt()
  {
    return this.resignCodeItt;
  }

  public void setResignCodeItt(String resignCodeItt)
  {
    this.resignCodeItt = resignCodeItt;
  }

  public String getEmpTokenItt()
  {
    return this.empTokenItt;
  }

  public void setEmpTokenItt(String empTokenItt)
  {
    this.empTokenItt = empTokenItt;
  }

  public String getEmpNameItt()
  {
    return this.empNameItt;
  }

  public void setEmpNameItt(String empNameItt)
  {
    this.empNameItt = empNameItt;
  }

  public String getEmpCodeItt()
  {
    return this.empCodeItt;
  }

  public void setEmpCodeItt(String empCodeItt)
  {
    this.empCodeItt = empCodeItt;
  }

  public String getResignDateItt()
  {
    return this.resignDateItt;
  }

  public void setResignDateItt(String resignDateItt)
  {
    this.resignDateItt = resignDateItt;
  }

  public String getLockFlag() {
    return this.lockFlag;
  }

  public void setLockFlag(String lockFlag) {
    this.lockFlag = lockFlag;
  }

  public String getNoticeperiodselect() {
    return this.noticeperiodselect;
  }

  public void setNoticeperiodselect(String noticeperiodselect) {
    this.noticeperiodselect = noticeperiodselect;
  }

  public String getToday() {
    return this.today;
  }

  public void setToday(String today) {
    this.today = today;
  }

  public String getEmployeeToken() {
    return this.employeeToken;
  }

  public void setEmployeeToken(String employeeToken) {
    this.employeeToken = employeeToken;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getEmpCode() {
    return this.empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getEmpName() {
    return this.empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getBranch() {
    return this.branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getDesignation() {
    return this.designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getGrade() {
    return this.grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getDateofjoin() {
    return this.dateofjoin;
  }

  public void setDateofjoin(String dateofjoin) {
    this.dateofjoin = dateofjoin;
  }

  public String getNoticePeriod() {
    return this.noticePeriod;
  }

  public void setNoticePeriod(String noticePeriod) {
    this.noticePeriod = noticePeriod;
  }

  public String getResignDate() {
    return this.resignDate;
  }

  public void setResignDate(String resignDate) {
    this.resignDate = resignDate;
  }

  public String getAcceptDate() {
    return this.acceptDate;
  }

  public void setAcceptDate(String acceptDate) {
    this.acceptDate = acceptDate;
  }

  public String getSepdate() {
    return this.sepdate;
  }

  public void setSepdate(String sepdate) {
    this.sepdate = sepdate;
  }

  public String getReason() {
    return this.reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getRegCode() {
    return this.regCode;
  }

  public void setRegCode(String regCode) {
    this.regCode = regCode;
  }

  public String getWithDrawn() {
    return this.withDrawn;
  }

  public void setWithDrawn(String withDrawn) {
    this.withDrawn = withDrawn;
  }

  public String getApprToken()
  {
    return this.apprToken;
  }

  public void setApprToken(String apprToken)
  {
    this.apprToken = apprToken;
  }

  public String getApprName()
  {
    return this.apprName;
  }

  public void setApprName(String apprName)
  {
    this.apprName = apprName;
  }

  public String getApprCode()
  {
    return this.apprCode;
  }

  public void setApprCode(String apprCode)
  {
    this.apprCode = apprCode;
  }

  public String getNoticePeriodActual()
  {
    return this.noticePeriodActual;
  }

  public void setNoticePeriodActual(String noticePeriodActual)
  {
    this.noticePeriodActual = noticePeriodActual;
  }

  public String getWaveOffPeriod()
  {
    return this.waveOffPeriod;
  }

  public void setWaveOffPeriod(String waveOffPeriod)
  {
    this.waveOffPeriod = waveOffPeriod;
  }

  public boolean isTemplateFlag()
  {
    return this.templateFlag;
  }

  public void setTemplateFlag(boolean templateFlag)
  {
    this.templateFlag = templateFlag;
  }

  public String getAccByToken()
  {
    return this.accByToken;
  }

  public void setAccByToken(String accByToken)
  {
    this.accByToken = accByToken;
  }

  public String getAcceptedBy()
  {
    return this.acceptedBy;
  }

  public void setAcceptedBy(String acceptedBy)
  {
    this.acceptedBy = acceptedBy;
  }

  public String getAccByCode()
  {
    return this.accByCode;
  }

  public void setAccByCode(String accByCode)
  {
    this.accByCode = accByCode;
  }

  public String getMyPage()
  {
    return this.myPage;
  }

  public void setMyPage(String myPage)
  {
    this.myPage = myPage;
  }

  public String getNoData()
  {
    return this.noData;
  }

  public void setNoData(String noData)
  {
    this.noData = noData;
  }

  public String getModeLength()
  {
    return this.modeLength;
  }

  public void setModeLength(String modeLength)
  {
    this.modeLength = modeLength;
  }

  public String getTotalRecords()
  {
    return this.totalRecords;
  }

  public void setTotalRecords(String totalRecords)
  {
    this.totalRecords = totalRecords;
  }

  public ArrayList getTableList()
  {
    return this.tableList;
  }

  public void setTableList(ArrayList tableList)
  {
    this.tableList = tableList;
  }

  public String getListFlag()
  {
    return this.listFlag;
  }

  public void setListFlag(String listFlag)
  {
    this.listFlag = listFlag;
  }

  public String getDescCnt2() {
    return this.descCnt2;
  }

  public void setDescCnt2(String descCnt2) {
    this.descCnt2 = descCnt2;
  }

  public String getHrComments() {
    return this.hrComments;
  }

  public void setHrComments(String hrComments) {
    this.hrComments = hrComments;
  }
}