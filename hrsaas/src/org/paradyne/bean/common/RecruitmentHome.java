package org.paradyne.bean.common;
import java.util.*;

import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep S
 * Date:31-01-2008
 */
public class RecruitmentHome extends BeanBase {
	String total;
	
	private String requisitionName;
	private String requisitionCode;
	private String vacanDtlCode;
	private String positionId;
	private String position;
	private String requiredDate;
	private String noOfVacancies;
	//Interview Schedule
	private String reqsName="";
	private String reqsCode="";
	private String interviewerEmpId="";
	private String interviewCode="";
	private String positionName="";
	private String intDtae="";
	private String intTime="";
	private String candCode="";
	private String candName="";
	private String interviewerName="";
	//Interviewer's Schedule
	private String intvwrReqCode="";
	private String intvwCode="";
	private String intrvwrPos="";
	private String intrvwrDt="";
	private String intvwrTime="";
	private String intrvwrCandName="";
	private String intrvwrCandCode="";
	private String intrvwrReqName="";
	

	private ArrayList<Object> interviewList=new ArrayList<Object>();
	private ArrayList <Object> list= new ArrayList<Object>();
	private ArrayList <Object>  intrvwrList=new ArrayList<Object>(); 
	private ArrayList <Object>  referalList=new ArrayList<Object>(); 
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRequisitionName() {
		return requisitionName;
	}

	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}

	public String getRequisitionCode() {
		return requisitionCode;
	}

	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}

	public String getVacanDtlCode() {
		return vacanDtlCode;
	}

	public void setVacanDtlCode(String vacanDtlCode) {
		this.vacanDtlCode = vacanDtlCode;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getNoOfVacancies() {
		return noOfVacancies;
	}

	public void setNoOfVacancies(String noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}

	public ArrayList getList() {
		return list;
	}

	

	public String getReqsName() {
		return reqsName;
	}

	public void setReqsName(String reqsName) {
		this.reqsName = reqsName;
	}

	public String getReqsCode() {
		return reqsCode;
	}

	public void setReqsCode(String reqsCode) {
		this.reqsCode = reqsCode;
	}

	public String getInterviewerEmpId() {
		return interviewerEmpId;
	}

	public void setInterviewerEmpId(String interviewerEmpId) {
		this.interviewerEmpId = interviewerEmpId;
	}

	public String getInterviewCode() {
		return interviewCode;
	}

	public void setInterviewCode(String interviewCode) {
		this.interviewCode = interviewCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getIntDtae() {
		return intDtae;
	}

	public void setIntDtae(String intDtae) {
		this.intDtae = intDtae;
	}

	public String getIntTime() {
		return intTime;
	}

	public void setIntTime(String intTime) {
		this.intTime = intTime;
	}

	public String getCandCode() {
		return candCode;
	}

	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}

	public ArrayList getInterviewList() {
		return interviewList;
	}



	public String getCandName() {
		return candName;
	}

	public void setCandName(String candName) {
		this.candName = candName;
	}

	public void setInterviewList(ArrayList<Object> interviewList) {
		this.interviewList = interviewList;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getIntvwrReqCode() {
		return intvwrReqCode;
	}

	public void setIntvwrReqCode(String intvwrReqCode) {
		this.intvwrReqCode = intvwrReqCode;
	}

	public String getIntvwCode() {
		return intvwCode;
	}

	public void setIntvwCode(String intvwCode) {
		this.intvwCode = intvwCode;
	}

	public String getIntrvwrPos() {
		return intrvwrPos;
	}

	public void setIntrvwrPos(String intrvwrPos) {
		this.intrvwrPos = intrvwrPos;
	}

	public String getIntrvwrDt() {
		return intrvwrDt;
	}

	public void setIntrvwrDt(String intrvwrDt) {
		this.intrvwrDt = intrvwrDt;
	}

	public String getIntvwrTime() {
		return intvwrTime;
	}

	public void setIntvwrTime(String intvwrTime) {
		this.intvwrTime = intvwrTime;
	}

	public String getIntrvwrCandName() {
		return intrvwrCandName;
	}

	public void setIntrvwrCandName(String intrvwrCandName) {
		this.intrvwrCandName = intrvwrCandName;
	}

	public String getIntrvwrCandCode() {
		return intrvwrCandCode;
	}

	public void setIntrvwrCandCode(String intrvwrCandCode) {
		this.intrvwrCandCode = intrvwrCandCode;
	}

	public String getIntrvwrReqName() {
		return intrvwrReqName;
	}

	public void setIntrvwrReqName(String intrvwrReqName) {
		this.intrvwrReqName = intrvwrReqName;
	}

	public ArrayList<Object> getIntrvwrList() {
		return intrvwrList;
	}

	public void setIntrvwrList(ArrayList<Object> intrvwrList) {
		this.intrvwrList = intrvwrList;
	}

	public ArrayList<Object> getReferalList() {
		return referalList;
	}

	public void setReferalList(ArrayList<Object> referalList) {
		this.referalList = referalList;
	}
	
	
	
	

}
