package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

public class Form27A extends BeanBase
{

    public Form27A()
    {
        divId = "";
        divName = "";
        finFrmYr = "";
        finToYr = "";
        quarter = "";
        signAuthId = "";
        signAuthName = "";
        signAuthDesg = "";
        empToken = "";
        reportType ="";
    }

    public String getDivId()
    {
        return divId;
    }

    public void setDivId(String divId)
    {
        this.divId = divId;
    }

    public String getDivName()
    {
        return divName;
    }

    public void setDivName(String divName)
    {
        this.divName = divName;
    }

    public String getFinFrmYr()
    {
        return finFrmYr;
    }

    public void setFinFrmYr(String finFrmYr)
    {
        this.finFrmYr = finFrmYr;
    }

    public String getFinToYr()
    {
        return finToYr;
    }

    public void setFinToYr(String finToYr)
    {
        this.finToYr = finToYr;
    }

    public String getQuarter()
    {
        return quarter;
    }

    public void setQuarter(String quarter)
    {
        this.quarter = quarter;
    }

    public String getSignAuthId()
    {
        return signAuthId;
    }

    public void setSignAuthId(String signAuthId)
    {
        this.signAuthId = signAuthId;
    }

    public String getSignAuthName()
    {
        return signAuthName;
    }

    public void setSignAuthName(String signAuthName)
    {
        this.signAuthName = signAuthName;
    }

    public String getSignAuthDesg()
    {
        return signAuthDesg;
    }

    public void setSignAuthDesg(String signAuthDesg)
    {
        this.signAuthDesg = signAuthDesg;
    }

    public String getEmpToken()
    {
        return empToken;
    }

    public void setEmpToken(String empToken)
    {
        this.empToken = empToken;
    }
    /**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
    
    private String divId;
    private String divName;
    private String finFrmYr;
    private String finToYr;
    private String quarter;
    private String signAuthId;
    private String signAuthName;
    private String signAuthDesg;
    private String empToken;
    private String reportType;
	
}