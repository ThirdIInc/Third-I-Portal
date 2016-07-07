package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class IndusMaster extends BeanBase
  implements Serializable
{
  private String industryCode = "";
  private String industryName = "";
  private String industryDesc = "";
  private String industryAbbr = "";
  private String industryStatus = "";
  private String hdomainCode = "";

  private ArrayList industryList = null;
  private String confChk = "";
  private String hdeleteCode = "";
  private String myPage = "";
  private String show = "";
  private String selectname = "";
  private String hiddencode = "";

  private String flagShow = "false";
  private String editFlag = "false";
  private String findViewFlag = "false";
  private String pageStatus = "";
  private String totalRecords = "";
  private boolean industryView = false;
  private String checkAll = "";

  public String getIndustryCode()
  {
    return this.industryCode;
  }

  public String getIndustryName()
  {
    return this.industryName;
  }

  public String getIndustryDesc()
  {
    return this.industryDesc;
  }

  public String getIndustryAbbr()
  {
    return this.industryAbbr;
  }

  public String getIndustryStatus()
  {
    return this.industryStatus;
  }

  public String getHdomainCode()
  {
    return this.hdomainCode;
  }

  public ArrayList getIndustryList()
  {
    return this.industryList;
  }

  public String getConfChk()
  {
    return this.confChk;
  }

  public String getHdeleteCode()
  {
    return this.hdeleteCode;
  }

  public String getMyPage()
  {
    return this.myPage;
  }

  public String getShow()
  {
    return this.show;
  }

  public String getSelectname()
  {
    return this.selectname;
  }

  public String getHiddencode()
  {
    return this.hiddencode;
  }

  public String getEditFlag()
  {
    return this.editFlag;
  }

  public void setIndustryCode(String industryCode)
  {
    this.industryCode = industryCode;
  }

  public void setIndustryName(String industryName)
  {
    this.industryName = industryName;
  }

  public void setIndustryDesc(String industryDesc)
  {
    this.industryDesc = industryDesc;
  }

  public void setIndustryAbbr(String industryAbbr)
  {
    this.industryAbbr = industryAbbr;
  }

  public void setIndustryStatus(String industryStatus)
  {
    this.industryStatus = industryStatus;
  }

  public void setHdomainCode(String hdomainCode)
  {
    this.hdomainCode = hdomainCode;
  }

  public void setIndustryList(ArrayList industryList)
  {
    this.industryList = industryList;
  }

  public void setConfChk(String confChk)
  {
    this.confChk = confChk;
  }

  public void setHdeleteCode(String hdeleteCode)
  {
    this.hdeleteCode = hdeleteCode;
  }

  public void setMyPage(String myPage)
  {
    this.myPage = myPage;
  }

  public void setShow(String show)
  {
    this.show = show;
  }

  public void setSelectname(String selectname)
  {
    this.selectname = selectname;
  }

  public void setHiddencode(String hiddencode)
  {
    this.hiddencode = hiddencode;
  }

  public void setEditFlag(String editFlag)
  {
    this.editFlag = editFlag;
  }
  public String getPageStatus() {
    return this.pageStatus;
  }
  public void setPageStatus(String pageStatus) {
    this.pageStatus = pageStatus;
  }
  public String getTotalRecords() {
    return this.totalRecords;
  }
  public void setTotalRecords(String totalRecords) {
    this.totalRecords = totalRecords;
  }
  public boolean isIndustryView() {
    return this.industryView;
  }
  public void setIndustryView(boolean industryView) {
    this.industryView = industryView;
  }
  public String getCheckAll() {
    return this.checkAll;
  }
  public void setCheckAll(String checkAll) {
    this.checkAll = checkAll;
  }
  public String getFindViewFlag() {
    return this.findViewFlag;
  }
  public void setFindViewFlag(String findViewFlag) {
    this.findViewFlag = findViewFlag;
  }
  public String getFlagShow() {
    return this.flagShow;
  }
  public void setFlagShow(String flagShow) {
    this.flagShow = flagShow;
  }
}