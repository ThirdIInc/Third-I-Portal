package org.paradyne.bean.PAS.Competency;

import org.paradyne.lib.BeanBase;

public class RoleWiseCompMisReport extends BeanBase
{
  private String role;
  private String roleId;
  private String compName;
  private String compId;
  private String desgId;
  private String compCatagory;
  private String compCatcode;
  private String report = "";

  public String getReport() { return this.report; }

  public void setReport(String report) {
    this.report = report;
  }

  public String getRole() {
    return this.role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  public String getRoleId() {
    return this.roleId;
  }
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  public String getCompName() {
    return this.compName;
  }
  public void setCompName(String compName) {
    this.compName = compName;
  }
  public String getCompId() {
    return this.compId;
  }
  public void setCompId(String compId) {
    this.compId = compId;
  }
  public String getDesgId() {
    return this.desgId;
  }
  public void setDesgId(String desgId) {
    this.desgId = desgId;
  }
  public String getCompCatagory() {
    return this.compCatagory;
  }
  public void setCompCatagory(String compCatagory) {
    this.compCatagory = compCatagory;
  }
  public String getCompCatcode() {
    return this.compCatcode;
  }
  public void setCompCatcode(String compCatcode) {
    this.compCatcode = compCatcode;
  }
}