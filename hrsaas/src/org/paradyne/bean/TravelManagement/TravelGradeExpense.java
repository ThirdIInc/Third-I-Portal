/**
 * Balaji Mane
 * Date 17-10-2008
 *  This class serves as the bean of TravelGradeWiseExpense
 */
package org.paradyne.bean.TravelManagement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelGradeExpense extends BeanBase {
	private String  journeyName="";
	private String journeyId="";
	private String className="";
	private String classId="";
	private String gradeName="";
	private String gradeCode="";
	private String travelOtherCost="";
	private String hotelOtherCost="";
	private String hotelPerDayCost="";
	private String hidCheck="";
	private String trGradeId="";	
	private String grCheck=""; 
	private String pocketCost="";	
	private String foodCost="";
	
	ArrayList jourExp =null;
	public String getGrCheck() {
		return grCheck;
	}
	public void setGrCheck(String grCheck) {
		this.grCheck = grCheck;
	}
	public ArrayList getJourExp() {
		return jourExp;
	}
	public void setJourExp(ArrayList jourExp) {
		this.jourExp = jourExp;
	}
	public String getJourneyName() {
		return journeyName;
	}
	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}
	public String getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(String journeyId) {
		this.journeyId = journeyId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
  
	public String getTravelOtherCost() {
		return travelOtherCost;
	}
	public void setTravelOtherCost(String travelOtherCost) {
		this.travelOtherCost = travelOtherCost;
	}
	public String getHotelOtherCost() {
		return hotelOtherCost;
	}
	public void setHotelOtherCost(String hotelOtherCost) {
		this.hotelOtherCost = hotelOtherCost;
	}
	public String getHotelPerDayCost() {
		return hotelPerDayCost;
	}
	public void setHotelPerDayCost(String hotelPerDayCost) {
		this.hotelPerDayCost = hotelPerDayCost;
	}
	public String getHidCheck() {
		return hidCheck;
	}
	public void setHidCheck(String hidCheck) {
		this.hidCheck = hidCheck;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getTrGradeId() {
		return trGradeId;
	}
	public void setTrGradeId(String trGradeId) {
		this.trGradeId = trGradeId;
	}
	public String getPocketCost() {
		return pocketCost;
	}
	public void setPocketCost(String pocketCost) {
		this.pocketCost = pocketCost;
	}
	public String getFoodCost() {
		return foodCost;
	}
	public void setFoodCost(String foodCost) {
		this.foodCost = foodCost;
	}

}
