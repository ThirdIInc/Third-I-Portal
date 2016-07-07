package org.paradyne.bean.settlement;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class ResignationApproval extends BeanBase
{
  private String listType = "";
  private String empCode = "";
  private String level = "";
  private String pendingStatus = "";
  private String empName = "";
  private String date = "";
  private String resignAppNo = "";
  private String tokenNo = "";
  private String appEmpId = "";
  private String appResignAppNo = "";
  private String appEmpToken = "";
  private String appResignAppDate = "";
  private String appStatus = "";
  private String appEmpName = "";
  private String appLevel = "";
  private String rejResignAppNo = "";
  private String rejEmpId = "";
  private String rejLevel = "";
  private String rejEmpToken = "";
  private String rejEmpName = "";
  private String rejResignAppDate = "";
  private String rejStatus = "";
  private ArrayList pendingList = null;
  private ArrayList appList = null;
  private ArrayList rejList = null;

  public String getListType()
  {
    return this.listType;
  }

  public void setListType(String listType) {
    this.listType = listType;
  }

  public String getEmpCode() {
    return this.empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getPendingStatus() {
    return this.pendingStatus;
  }

  public void setPendingStatus(String pendingStatus) {
    this.pendingStatus = pendingStatus;
  }

  public String getEmpName() {
    return this.empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getResignAppNo() {
    return this.resignAppNo;
  }

  public void setResignAppNo(String resignAppNo) {
    this.resignAppNo = resignAppNo;
  }

  public ArrayList getPendingList() {
    return this.pendingList;
  }

  public void setPendingList(ArrayList pendingList) {
    this.pendingList = pendingList;
  }

  public String getTokenNo() {
    return this.tokenNo;
  }

  public void setTokenNo(String tokenNo) {
    this.tokenNo = tokenNo;
  }

  public String getAppEmpId() {
    return this.appEmpId;
  }

  public void setAppEmpId(String appEmpId) {
    this.appEmpId = appEmpId;
  }

  public String getAppResignAppNo() {
    return this.appResignAppNo;
  }

  public void setAppResignAppNo(String appResignAppNo) {
    this.appResignAppNo = appResignAppNo;
  }

  public String getAppEmpToken() {
    return this.appEmpToken;
  }

  public void setAppEmpToken(String appEmpToken) {
    this.appEmpToken = appEmpToken;
  }

  public String getAppResignAppDate() {
    return this.appResignAppDate;
  }

  public void setAppResignAppDate(String appResignAppDate) {
    this.appResignAppDate = appResignAppDate;
  }

  public String getAppStatus() {
    return this.appStatus;
  }

  public void setAppStatus(String appStatus) {
    this.appStatus = appStatus;
  }

  public ArrayList getAppList() {
    return this.appList;
  }

  public void setAppList(ArrayList appList) {
    this.appList = appList;
  }

  public String getAppEmpName() {
    return this.appEmpName;
  }

  public void setAppEmpName(String appEmpName) {
    this.appEmpName = appEmpName;
  }

  public String getAppLevel() {
    return this.appLevel;
  }

  public void setAppLevel(String appLevel) {
    this.appLevel = appLevel;
  }

  public String getRejResignAppNo() {
    return this.rejResignAppNo;
  }

  public void setRejResignAppNo(String rejResignAppNo) {
    this.rejResignAppNo = rejResignAppNo;
  }

  public String getRejEmpId() {
    return this.rejEmpId;
  }

  public void setRejEmpId(String rejEmpId) {
    this.rejEmpId = rejEmpId;
  }

  public String getRejLevel() {
    return this.rejLevel;
  }

  public void setRejLevel(String rejLevel) {
    this.rejLevel = rejLevel;
  }

  public String getRejEmpToken() {
    return this.rejEmpToken;
  }

  public void setRejEmpToken(String rejEmpToken) {
    this.rejEmpToken = rejEmpToken;
  }

  public String getRejEmpName() {
    return this.rejEmpName;
  }

  public void setRejEmpName(String rejEmpName) {
    this.rejEmpName = rejEmpName;
  }

  public String getRejResignAppDate() {
    return this.rejResignAppDate;
  }

  public void setRejResignAppDate(String rejResignAppDate) {
    this.rejResignAppDate = rejResignAppDate;
  }

  public String getRejStatus() {
    return this.rejStatus;
  }

  public void setRejStatus(String rejStatus) {
    this.rejStatus = rejStatus;
  }

  public ArrayList getRejList() {
    return this.rejList;
  }

  public void setRejList(ArrayList rejList) {
    this.rejList = rejList;
  }
}