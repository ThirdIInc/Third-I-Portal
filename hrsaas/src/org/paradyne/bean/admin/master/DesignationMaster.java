package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * @author pranali 
 * Date 25-04-07
 */
public class DesignationMaster extends BeanBase implements Serializable{
	
	String desgID="";
	String desgName="";
	String desgParCode="";
	String desgHighCode="";
	String desgAbbr="";
	String desgDesc="";
	String desgLevel="";
	String rcmndAuth="";
	String apprAuth="";
	ArrayList att=null;
	
	public DesignationMaster()
	{
		
	}

	public DesignationMaster(String desgID, String desgName, String desgParCode, String desgHighCode, String desgAbbr, String desgDesc, String desgLevel, String rcmndAuth, String apprAuth, ArrayList att) {
		super();
		this.desgID = desgID;
		this.desgName = desgName;
		this.desgParCode = desgParCode;
		this.desgHighCode = desgHighCode;
		this.desgAbbr = desgAbbr;
		this.desgDesc = desgDesc;
		this.desgLevel = desgLevel;
		this.rcmndAuth = rcmndAuth;
		this.apprAuth = apprAuth;
		this.att = att;
	}

	public String getApprAuth() {
		return apprAuth;
	}

	public void setApprAuth(String apprAuth) {
		this.apprAuth = apprAuth;
	}

	public ArrayList getAtt() {
		return att;
	}

	public void setAtt(ArrayList att) {
		this.att = att;
	}

	public String getDesgAbbr() {
		return desgAbbr;
	}

	public void setDesgAbbr(String desgAbbr) {
		this.desgAbbr = desgAbbr;
	}

	public String getDesgDesc() {
		return desgDesc;
	}

	public void setDesgDesc(String desgDesc) {
		this.desgDesc = desgDesc;
	}

	public String getDesgHighCode() {
		return desgHighCode;
	}

	public void setDesgHighCode(String desgHighCode) {
		this.desgHighCode = desgHighCode;
	}

	public String getDesgID() {
		return desgID;
	}

	public void setDesgID(String desgID) {
		this.desgID = desgID;
	}

	public String getDesgLevel() {
		return desgLevel;
	}

	public void setDesgLevel(String desgLevel) {
		this.desgLevel = desgLevel;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getDesgParCode() {
		return desgParCode;
	}

	public void setDesgParCode(String desgParCode) {
		this.desgParCode = desgParCode;
	}

	public String getRcmndAuth() {
		return rcmndAuth;
	}

	public void setRcmndAuth(String rcmndAuth) {
		this.rcmndAuth = rcmndAuth;
	}
	
}
