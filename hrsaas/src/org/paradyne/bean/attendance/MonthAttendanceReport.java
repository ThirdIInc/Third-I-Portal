package org.paradyne.bean.attendance;

import org.paradyne.lib.BeanBase;

/**
 * @author Bhushan
 * @date Jul 11, 2008
**/

public class MonthAttendanceReport extends BeanBase
{
	private String brnFlag;
	private String deptFlag;
	private String payBillFlag;
	private String typeFlag;
	private String divFlag;
	private String month;
	private String year;
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
	private String attdnType = "";
	private String reportType = "";
	
	public String getBrnFlag()
	{
		return brnFlag;
	}
	
	public void setBrnFlag(String brnFlag)
	{
		this.brnFlag = brnFlag;
	}
	
	public String getDeptFlag()
	{
		return deptFlag;
	}
	
	public void setDeptFlag(String deptFlag)
	{
		this.deptFlag = deptFlag;
	}
	
	public String getPayBillFlag()
	{
		return payBillFlag;
	}
	
	public void setPayBillFlag(String payBillFlag)
	{
		this.payBillFlag = payBillFlag;
	}
	
	public String getTypeFlag()
	{
		return typeFlag;
	}
	
	public void setTypeFlag(String typeFlag)
	{
		this.typeFlag = typeFlag;
	}
	
	public String getDivFlag()
	{
		return divFlag;
	}
	
	public void setDivFlag(String divFlag)
	{
		this.divFlag = divFlag;
	}
	
	public String getMonth()
	{
		return month;
	}
	
	public void setMonth(String month)
	{
		this.month = month;
	}
	
	public String getYear()
	{
		return year;
	}
	
	public void setYear(String year)
	{
		this.year = year;
	}
	
	public String getPayBillNo()
	{
		return payBillNo;
	}
	
	public void setPayBillNo(String payBillNo)
	{
		this.payBillNo = payBillNo;
	}
	
	public String getPayBillName()
	{
		return payBillName;
	}
	
	public void setPayBillName(String payBillName)
	{
		this.payBillName = payBillName;
	}
	
	public String getTypeCode()
	{
		return typeCode;
	}
	
	public void setTypeCode(String typeCode)
	{
		this.typeCode = typeCode;
	}
	
	public String getTypeName()
	{
		return typeName;
	}
	
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}
	
	public String getBrnCode()
	{
		return brnCode;
	}
	
	public void setBrnCode(String brnCode)
	{
		this.brnCode = brnCode;
	}
	
	public String getBrnName()
	{
		return brnName;
	}
	
	public void setBrnName(String brnName)
	{
		this.brnName = brnName;
	}
	
	public String getDeptCode()
	{
		return deptCode;
	}
	
	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}
	
	public String getDeptName()
	{
		return deptName;
	}
	
	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}
	
	public String getDivCode()
	{
		return divCode;
	}
	
	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}
	
	public String getDivName()
	{
		return divName;
	}
	
	public void setDivName(String divName)
	{
		this.divName = divName;
	}
	
	public String getAttdnType()
	{
		return attdnType;
	}

	public void setAttdnType(String attdnType)
	{
		this.attdnType = attdnType;
	}

	public String getReportType()
	{
		return reportType;
	}
	
	public void setReportType(String reportType)
	{
		this.reportType = reportType;
	}	
}