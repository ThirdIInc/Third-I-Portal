package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author pranali 
 * Date 23-04-07
 */
public class TradeMaster extends BeanBase implements Serializable
{
	
	private String modeLength="false";
	private String totalRecords="";
	String tradeCode="";
	String tradeName="";
	String tradeAbbr="";
	String tradeDesc="";
	String tradeParentCode="";
	String tradeParentName="";
	private String myPage;
	private String show;
	private String  hiddencode;
	ArrayList att=null;  
	
	private String hdeleteCode;
	private String report="";


	public TradeMaster()
	{
	}
	
	public TradeMaster(String tradeCode,String tradeName,String tradeDesc,String tradeParentCode)
	{
		this.tradeCode=tradeCode;
		this.tradeName=tradeName;
		this.tradeDesc=tradeDesc;
		this.tradeParentCode=tradeParentCode;
		
	}
	
	public String getTradeCode() 
	{
		return tradeCode;
	}
	
	public void setTradeCode(String tradeCode)
	{
		this.tradeCode=tradeCode;
	}
	
	public String getTradeName() 
	{
		return tradeName;
	}
	
	public void setTradeName(String tradeName)
	{
		this.tradeName=tradeName;
	}
	
	public String getTradeDesc() 
	{
		return tradeDesc;
	}
	
	public void setTradeDesc(String tradeDesc)
	{
		this.tradeDesc=tradeDesc;
	}
	public String getTradeParentCode() 
	{
		return tradeParentCode;
	}
	
	public void setTradeParentCode(String tradeParentCode)
	{
		this.tradeParentCode=tradeParentCode;
	}
	public void setAtt(ArrayList att)
	{
		
		this.att	=	att;
	}
	
	public ArrayList getAtt()
	{
		
		return att;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getTradeParentName() {
		return tradeParentName;
	}

	public void setTradeParentName(String tradeParentName) {
		this.tradeParentName = tradeParentName;
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

	public String getTradeAbbr() {
		return tradeAbbr;
	}

	public void setTradeAbbr(String tradeAbbr) {
		this.tradeAbbr = tradeAbbr;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
	
	
}
