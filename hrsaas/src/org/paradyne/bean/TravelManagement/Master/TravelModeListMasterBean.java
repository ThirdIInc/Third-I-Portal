package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelModeListMasterBean extends BeanBase {

	
	/** 
	 * for Travel Mode Master list
	 */
	private String ittTravelModeName="";
	private String ittAirlineBusTrain="";
	private String ittModeCode="";
	ArrayList TravelModeMasterItt=null ;
	/*
	 * for Travel Mode Master
	 * 
	 */
	private String ittSrN0="";
	private String journeyId="";
	private String travelModeName="";
	private String airlineBusTrain="";
	private String travelId="";
	private String myPage="";
	
	private String ModeId="";

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public String getIttTravelModeName() {
		return ittTravelModeName;
	}

	public void setIttTravelModeName(String ittTravelModeName) {
		this.ittTravelModeName = ittTravelModeName;
	}

	public String getIttAirlineBusTrain() {
		return ittAirlineBusTrain;
	}

	public void setIttAirlineBusTrain(String ittAirlineBusTrain) {
		this.ittAirlineBusTrain = ittAirlineBusTrain;
	}

	public String getIttModeCode() {
		return ittModeCode;
	}

	public void setIttModeCode(String ittModeCode) {
		this.ittModeCode = ittModeCode;
	}

	public ArrayList getTravelModeMasterItt() {
		return TravelModeMasterItt;
	}

	public void setTravelModeMasterItt(ArrayList travelModeMasterItt) {
		TravelModeMasterItt = travelModeMasterItt;
	}

	public String getTravelModeName() {
		return travelModeName;
	}

	public void setTravelModeName(String travelModeName) {
		this.travelModeName = travelModeName;
	}

	public String getAirlineBusTrain() {
		return airlineBusTrain;
	}

	public void setAirlineBusTrain(String airlineBusTrain) {
		this.airlineBusTrain = airlineBusTrain;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(String journeyId) {
		this.journeyId = journeyId;
	}

	public String getIttSrN0() {
		return ittSrN0;
	}

	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}

	public String getModeId() {
		return ModeId;
	}

	public void setModeId(String modeId) {
		ModeId = modeId;
	}
	
	
}
