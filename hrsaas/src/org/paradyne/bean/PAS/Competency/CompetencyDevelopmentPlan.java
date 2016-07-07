package org.paradyne.bean.PAS.Competency;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class CompetencyDevelopmentPlan extends BeanBase
{
  private String modeLength = "false";
  private String totalRecords = "";
  private String compDevCode;
  private String compDevName;
  private String compDevDesc;
  ArrayList titleList;
  private String myPage;
  private String show;
  private String hiddencode;
  private String hdeleteCode;

  public final String getModeLength()
  {
    return this.modeLength;
  }

  public final void setModeLength(String modeLength)
  {
    this.modeLength = modeLength;
  }

  public final String getTotalRecords()
  {
    return this.totalRecords;
  }

  public final void setTotalRecords(String totalRecords)
  {
    this.totalRecords = totalRecords;
  }

  public final String getCompDevCode()
  {
    return this.compDevCode;
  }

  public final void setCompDevCode(String compDevCode)
  {
    this.compDevCode = compDevCode;
  }

  public final String getCompDevName()
  {
    return this.compDevName;
  }

  public final void setCompDevName(String compDevName)
  {
    this.compDevName = compDevName;
  }

  public final String getCompDevDesc()
  {
    return this.compDevDesc;
  }

  public final void setCompDevDesc(String compDevDesc)
  {
    this.compDevDesc = compDevDesc;
  }

  public final ArrayList getTitleList()
  {
    return this.titleList;
  }

  public final void setTitleList(ArrayList titleList)
  {
    this.titleList = titleList;
  }

  public final String getMyPage()
  {
    return this.myPage;
  }

  public final void setMyPage(String myPage)
  {
    this.myPage = myPage;
  }

  public final String getShow()
  {
    return this.show;
  }

  public final void setShow(String show)
  {
    this.show = show;
  }

  public final String getHiddencode()
  {
    return this.hiddencode;
  }

  public final void setHiddencode(String hiddencode)
  {
    this.hiddencode = hiddencode;
  }

  public final String getHdeleteCode()
  {
    return this.hdeleteCode;
  }

  public final void setHdeleteCode(String hdeleteCode)
  {
    this.hdeleteCode = hdeleteCode;
  }
}