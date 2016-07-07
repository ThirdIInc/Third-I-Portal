/* @author: Bhushan Dasare   @date: June 5, 2009 */

package org.paradyne.model.attendance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.LeavePolicyData;

/**
 * To define a business logic for processing the monthly attendance
 */

public class MonthAttendanceProcessModel extends org.paradyne.lib.ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthAttendanceProcessModel.class);
	
	// Collect the total number of paid leaves, unpaid leaves, unpaid late marks and unpaid half days
	private double numOfPaidLeaves = 0.0, numOfUnPaidLeaves = 0.0,numOfRecoveryDays = 0.0; private double numOfPaidLateMarks = 0.0, numOfPaidHalfDays = 0.0;
	private double numOfUnPaidLateMarks = 0.0, numOfUnPaidHalfDays = 0.0; private int numOfPaidLateMarkMins = 0, numOfUnPaidLateMarkMins = 0;
	private int numOfAbsents = 0, extraHolidays = 0, extraWeeklyOffs = 0;
	private double numOfUnauthAdjLeaves = 0.0, numOfUnauthUnAdjLeaves = 0.0, workingHours = 0.0;
	private boolean leaveUnauthFlag = false;
	
	/**
	 * Global 2D objects to store data by firing the queries
	 */
	private Object[][] attendanceSettings = null, uploadAttendance = null, holidaysFromDailyAttendance = null, holidaysFromMaster = null;
	private Object[][] dailyAttendanceNotExist = null, weeklyOffsFromDailyAttendance = null, lateMarksHalfDays = null, leavePolicies = null;
	private Object[][] leavesAndBalances = null, leavePenalties = null, leaveDays = null, unauthorisedLeaves = null;
	private ArrayList<String> holidays = new ArrayList<String>();
	
	/**
	 * Adjust half days against the closing leave balances
	 * @param leaveDetails: Contains leave data after adjusting late marks
	 * @param numOfHalfDays: Total no. of half days
	 * @param leaveIdsForHalfDay: Leave Ids specified in Attendance Setting to adjust half days against the closing balances
	 * @return Object[][] as leave data after adjusting half days
	 */
	private Object[][] adjustHalfDaysOnDaysBasis(Object[][] leaveDetails, int numOfHalfDays, String[] hdLeaveIds) {
		Object[][] adjustHalfDays = null;
		try {
			// Converts half days into days
			double halfDays = numOfHalfDays / 2.0;
			
			// Initialize adjustHalfDays with leaveDetails, so it is containing previous leave details
			adjustHalfDays = leaveDetails;
			
			/**
			 * If half days are zero, then don't adjust half days into balances, whole half days get converted into unpaid leaves
			 */
			if(halfDays > 0) {
				if(hdLeaveIds != null && hdLeaveIds.length > 0) {
					// get leaves Ids from attendance settings to adjust half days
					for(int i = 0; i < hdLeaveIds.length; i++) {
						// leave Id from shift
						String leaveIdFromShift = String.valueOf(hdLeaveIds[i]);
						
						if(leaveDetails != null && leaveDetails.length > 0) {
							// get leave details after adjusting late marks
							for(int j = 0; j < leaveDetails.length; j++) {
								// get leave details
								String leaveIdFromDetails = String.valueOf(leaveDetails[j][1]);
								double openingBalance = Double.parseDouble(String.valueOf(leaveDetails[j][2]));
								double paidLeaveDays = Double.parseDouble(String.valueOf(leaveDetails[j][3]));
								double lateMarksAdjusted = Double.parseDouble(String.valueOf(leaveDetails[j][4]));
								double halfDaysAdjusted = Double.parseDouble(String.valueOf(leaveDetails[j][5]));
								double closingBalance = Double.parseDouble(String.valueOf(leaveDetails[j][6]));
								
								// if leave id from leave details matches with leave ids from shift, then only adjust 
								// the half days
								if(leaveIdFromShift.equals(leaveIdFromDetails)) {
									/**
									 * If halfDays <= closingBalance, then adjust all half days and deduct closing balance by half 
									 * days else consume entire balance and deduct half days by balance
									 */
									if(halfDays <= closingBalance) {
										halfDaysAdjusted += halfDays;
										closingBalance -= halfDays;
										halfDays = 0;
									} else {
										// round the closing balance, e.g. 1.74 = 1.5, 1.47 = 1.0, 1.56 = 1.5
										double roundedBalance = getNumberInRound(closingBalance);
										
										halfDays -= roundedBalance;
										halfDaysAdjusted += roundedBalance;
										closingBalance -= roundedBalance;
									}
									
									// add adjusted half days into total paid leaves
									numOfPaidHalfDays += halfDaysAdjusted;
								}
								// if leave is from shift is 0 (unpaid), then add remaining or all half days into total unpaid 
								// leaves
								else if(leaveIdFromShift.equals("0")) {
									// add remaining or all half days into total unpaid leaves
									numOfUnPaidHalfDays += halfDays;
									setNumOfUnPaidHalfDays(numOfUnPaidHalfDays);
									
									break;
								}
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								adjustHalfDays[j][0] = String.valueOf(leaveDetails[j][0]); // empId
								adjustHalfDays[j][1] = leaveIdFromDetails;
								adjustHalfDays[j][2] = openingBalance;
								adjustHalfDays[j][3] = paidLeaveDays;
								adjustHalfDays[j][4] = lateMarksAdjusted;
								adjustHalfDays[j][5] = halfDaysAdjusted;
								adjustHalfDays[j][6] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in adjustHalfDays: " + e);
		}
		return adjustHalfDays;
	}
	
	private Object[][] adjustHalfDaysOnHoursBasis(Object[][] leaveMinsDetails, int numOfHalfDays, String[] hdLeaveIds) {
		Object[][] adjustHalfDaysMins = null;
		try {
			// Converts half days into days
			double halfDays = numOfHalfDays / 2.0;
			// Initialize adjustHalfDays with leaveDetails, so it is containing previous leave details
			adjustHalfDaysMins = leaveMinsDetails;
			/**
			 * If half days are zero, then don't adjust half days into balances, whole half days get converted into unpaid leaves
			 */
			if(halfDays > 0) {
				if(hdLeaveIds != null && hdLeaveIds.length > 0) {
					// get leaves Ids from attendance settings to adjust half days
					for(int i = 0; i < hdLeaveIds.length; i++) {
						// leave Id from shift
						String leaveIdFromShift = String.valueOf(hdLeaveIds[i]);
						
						if(leaveMinsDetails != null && leaveMinsDetails.length > 0) {
							// get leave details after adjusting late marks
							for(int j = 0; j < leaveMinsDetails.length; j++) {
								// get leave details
								String leaveIdFromDetails = String.valueOf(leaveMinsDetails[j][1]);
								int openingBalanceMins = Integer.parseInt(String.valueOf(leaveMinsDetails[j][2]));
								double paidLeaveDays = Double.parseDouble(String.valueOf(leaveMinsDetails[j][3]));
								int lateMarkMinsAdjusted = Integer.parseInt(String.valueOf(leaveMinsDetails[j][4]));
								double halfDaysAdjusted = Double.parseDouble(String.valueOf(leaveMinsDetails[j][5]));
								int closingBalanceMins = Integer.parseInt(String.valueOf(leaveMinsDetails[j][6]));
								
								// convert closing balance minutes into days
								double closingBalDays = Math.round(getDays(closingBalanceMins) * Math.pow(10, 2)) / Math.pow(10, 2);
																								
								// if leave id from leave details matches with leave ids from shift, then only adjust 
								// the half days
								if(leaveIdFromShift.equals(leaveIdFromDetails)) {
									/**
									 * If halfDays <= closingBalance, then adjust all half days and deduct closing balance by half 
									 * days else consume entire balance and deduct half days by balance
									 */
									if(halfDays <= closingBalDays) {
										halfDaysAdjusted += halfDays;
										closingBalDays -= halfDays;
										halfDays = 0;
									} else {
										double roundedClosingBalDays = getNumberInRound(closingBalDays);
										
										halfDays -= roundedClosingBalDays;
										halfDaysAdjusted += roundedClosingBalDays;
										closingBalDays -= roundedClosingBalDays;
									}
									
									// add adjusted half days into total paid leaves
									numOfPaidHalfDays += halfDaysAdjusted;
								}
								
								// if leave is from shift is 0 (unpaid), then add remaining or all half days into total unpaid 
								// leaves
								else if(leaveIdFromShift.equals("0")) {
									// add remaining or all half days into total unpaid leaves
									numOfUnPaidHalfDays += halfDays;
									setNumOfUnPaidHalfDays(numOfUnPaidHalfDays);
									
									break;
								}
								// convert closing balance days into minutes
								closingBalanceMins = getMinutes(closingBalDays);
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								adjustHalfDaysMins[j][0] = String.valueOf(leaveMinsDetails[j][0]); // empId
								adjustHalfDaysMins[j][1] = leaveIdFromDetails;
								adjustHalfDaysMins[j][2] = openingBalanceMins;
								adjustHalfDaysMins[j][3] = paidLeaveDays;
								adjustHalfDaysMins[j][4] = lateMarkMinsAdjusted;
								adjustHalfDaysMins[j][5] = halfDaysAdjusted;
								adjustHalfDaysMins[j][6] = closingBalanceMins;
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in adjustHalfDaysOnHoursBasis:" + e);
		}
		return adjustHalfDaysMins;
	}
	
	/**
	 * To adjust late marks and half days against the  closing balances, get the configuration data from attendance settings.
	 * If data doesn't exists, then convert late marks and half days into unpaid leaves
	 * @param leaveDetails: Contains leave data
	 * @param numOfLateMarks: Total no. of late marks
	 * @param numOfHalfDays: Total no. of half days
	 * @return Object[][] as leave data after adjusting late marks and half days
	 */
	public Object[][] adjustLateMarksHalfDaysOnDaysBasis(Object[][] leaveDetails, int numOfLateMarks, int numOfHalfDays, 
		int lmCount, double lmAdjustLeaveDays, String lmAdjustLeaveTypes, String hdDeductLeaveTypes) {
		Object[][] adjustedLateMarksHalfDays = null;
		try {
			adjustedLateMarksHalfDays = leaveDetails;
			
			String[] lmLeaveIds = null, hdLeaveIds = null;
			
			// Get leave codes to adjust late marks
			if(!(lmAdjustLeaveTypes.equals("") || lmAdjustLeaveTypes.equals("null") || lmAdjustLeaveTypes.equals(null) || 
					lmAdjustLeaveTypes == null)) {
				lmLeaveIds = lmAdjustLeaveTypes.split(",");
			}
			
			// Get leave codes to adjust half days
			if(!(hdDeductLeaveTypes.equals("") || hdDeductLeaveTypes.equals("null") || hdDeductLeaveTypes.equals(null) || 
					hdDeductLeaveTypes == null)) {
				hdLeaveIds = hdDeductLeaveTypes.split(",");
			}
						
			// Adjust late marks against leaves
			if(numOfLateMarks > 0 && lmCount > 0 && lmLeaveIds != null && lmLeaveIds.length > 0) {
				adjustedLateMarksHalfDays = adjustLateMarksOnDaysBasis(leaveDetails, numOfLateMarks, lmCount, lmLeaveIds, 
					lmAdjustLeaveDays);
			}
			
			// Adjust half days against leaves
			if(numOfHalfDays > 0 && hdLeaveIds != null && hdLeaveIds.length > 0) {
				adjustedLateMarksHalfDays = adjustHalfDaysOnDaysBasis(adjustedLateMarksHalfDays, numOfHalfDays, hdLeaveIds);
			}
		} catch(Exception e) {
			logger.error("Exception in adjustLateMarksHalfDays:" + e);
		}		
		return adjustedLateMarksHalfDays;
	}
	
	public Object[][] adjustLateMarksHalfDaysOnHoursBasis(Object[][] leaveMinsDetails, int numOfLateMarksMins, int numOfHalfDays, 
		String lmDeductNonRegLeaveTypes, String hdDeductLeaveTypes) {
		Object[][] adjustedLateMarksHalfDaysMins = null;
		try {
			adjustedLateMarksHalfDaysMins = leaveMinsDetails;
			
			String[] lmLeaveIds = null, hdLeaveIds = null;
			
			// Get leave codes to adjust late marks
			if(!(lmDeductNonRegLeaveTypes.equals("") || lmDeductNonRegLeaveTypes.equals("null") || 
					lmDeductNonRegLeaveTypes.equals(null) || lmDeductNonRegLeaveTypes == null)) {
				lmLeaveIds = lmDeductNonRegLeaveTypes.split(",");
			}
			
			// Get leave codes to adjust half days
			if(!(hdDeductLeaveTypes.equals("") || hdDeductLeaveTypes.equals("null") || hdDeductLeaveTypes.equals(null) || 
					hdDeductLeaveTypes == null)) {
				hdLeaveIds = hdDeductLeaveTypes.split(",");
			}
			
			// Adjust late marks against leaves
			if(numOfLateMarksMins > 0 && lmLeaveIds != null && lmLeaveIds.length > 0) {
				adjustedLateMarksHalfDaysMins = adjustLateMarksOnHoursBasis(leaveMinsDetails, numOfLateMarksMins, lmLeaveIds);
			}
			
			// Adjust half days against leaves
			if(numOfHalfDays > 0 && hdLeaveIds != null && hdLeaveIds.length > 0) {
				adjustedLateMarksHalfDaysMins = adjustHalfDaysOnHoursBasis(adjustedLateMarksHalfDaysMins, numOfHalfDays, hdLeaveIds);
			}
		} catch(Exception e) {
			logger.error("Exception in adjustLateMarksHalfDaysOnHoursBasis:" + e);
		}
		return adjustedLateMarksHalfDaysMins;
	}

	/**
	 * Adjust late marks against the closing leave balances
	 * @param leaveDetails: Contains leave data
	 * @param numOfLateMarks: Total no. of late marks
	 * @param numOfLateMarksForLeave: No. of late marks per day
	 * @param leaveIdsForLateMark: Leave Ids specified in Attendance Setting to adjust late marks against the closing balances
	 * @return Object[][] as leave data after adjusting late marks
	 */
	private Object[][] adjustLateMarksOnDaysBasis(Object[][] leaveDetails, int numOfLateMarks, int lmCount, String[] lmLeaveIds, 
		double lmAdjustLeaveDays) {
		Object[][] adjustLateMarks = null;
		try {
			// Converts late marks into days
			double lateMarks = (int) ((int)(numOfLateMarks / lmCount)) * lmAdjustLeaveDays;
			
			// Initialize adjustLateMarks with leaveDetails, so it is containing previous leave details
			adjustLateMarks = leaveDetails;
			
			/**
			 * If late marks are zero, then don't adjust late marks into balances, whole late marks get converted into 
			 * unpaid leaves
			 */
			if(lateMarks > 0) {
				// get leaves Ids from shift to adjust late marks
				if(lmLeaveIds != null && lmLeaveIds.length > 0) {
					for(int i = 0; i < lmLeaveIds.length; i++) {
						// leave Id from shift
						String leaveIdFromShift = String.valueOf(lmLeaveIds[i]);
						
						if(leaveDetails != null && leaveDetails.length > 0) {
							for(int j = 0; j < leaveDetails.length; j++) {
								// get leave details
								String leaveIdFromDetails = String.valueOf(leaveDetails[j][1]);
								double openingBalance = Double.parseDouble(String.valueOf(leaveDetails[j][2]));
								double paidLeaveDays = Double.parseDouble(String.valueOf(leaveDetails[j][3]));
								double lateMarksAdjusted = Double.parseDouble(String.valueOf(leaveDetails[j][4]));
								double halfDaysAdjusted = Double.parseDouble(String.valueOf(leaveDetails[j][5]));
								double closingBalance = Double.parseDouble(String.valueOf(leaveDetails[j][6]));
								
								/**
								 * If leave id from leave details matches with leave ids from shift, then only adjust the late marks
								 */
								if(leaveIdFromShift.equals(leaveIdFromDetails)) {
									/**
									 * If lateMarks <= closingBalance, then adjust all late marks and deduct closing balance by 
									 * late marks, else consume entire balance and deduct late marks by balance
									 */
									if(lateMarks <= closingBalance) {
										lateMarksAdjusted += lateMarks;
										closingBalance -= lateMarks;
										lateMarks = 0;
									} else {
										// round the closing balance, e.g. 1.74 = 1.5, 1.47 = 1.0, 1.56 = 1.5
										double roundedBalance = getNumberInRound(closingBalance);
										
										lateMarks -= roundedBalance;
										lateMarksAdjusted += roundedBalance;
										closingBalance -= roundedBalance;
									}
									
									// add adjusted late marks into total paid leaves
									numOfPaidLateMarks += lateMarksAdjusted;
								}
								
								// if leave is from shift is 0 (unpaid), then add remaining or all late marks into total unpaid 
								// leaves
								else if(leaveIdFromShift.equals("0")) {
									// add remaining or all late marks into total unpaid leaves
									numOfUnPaidLateMarks += lateMarks;
									setNumOfUnPaidLateMarks(numOfUnPaidLateMarks);
									
									break;
								}
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								adjustLateMarks[j][0] = String.valueOf(leaveDetails[j][0]); // empId
								adjustLateMarks[j][1] = leaveIdFromDetails;
								adjustLateMarks[j][2] = openingBalance;
								adjustLateMarks[j][3] = paidLeaveDays;
								adjustLateMarks[j][4] = lateMarksAdjusted;
								adjustLateMarks[j][5] = halfDaysAdjusted;
								adjustLateMarks[j][6] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in adjustLateMarks:" + e);
		}
		return adjustLateMarks;
	}
	
	private Object[][] adjustLateMarksOnHoursBasis(Object[][] leaveMinsDetails, int numOfLateMarksMins, String[] lmLeaveIds) {
		Object[][] adjustLateMarksMins = null;
		try {
			// Initialize adjustLateMarksMins with leaveDetails, so it is containing previous leave details
			adjustLateMarksMins = leaveMinsDetails;
			
			/**
			 * If late marks are zero, then don't adjust late marks into balances, whole late marks get converted into unpaid leaves
			 */
			if(numOfLateMarksMins > 0) {
				// get leaves Ids from shift to adjust late marks
				if(lmLeaveIds != null && lmLeaveIds.length > 0) {
					for(int i = 0; i < lmLeaveIds.length; i++) {
						// leave Id from shift
						String leaveIdFromShift = String.valueOf(lmLeaveIds[i]);
						
						if(leaveMinsDetails != null && leaveMinsDetails.length > 0) {
							for(int j = 0; j < leaveMinsDetails.length; j++) {
								// get leave details
								String leaveIdFromDetails = String.valueOf(leaveMinsDetails[j][1]);
								int openingBalanceMins = Integer.parseInt(String.valueOf(leaveMinsDetails[j][2]));
								double paidLeaveDays = Double.parseDouble(String.valueOf(leaveMinsDetails[j][3]));
								int lateMarkMinsAdjusted = Integer.parseInt(String.valueOf(leaveMinsDetails[j][4]));
								int halfDaysAdjusted = Integer.parseInt(String.valueOf(leaveMinsDetails[j][5]));
								int closingBalanceMins = Integer.parseInt(String.valueOf(leaveMinsDetails[j][6]));
								
								/**
								 * If leave id from leave details matches with leave ids from shift, then only adjust the late marks
								 */
								if(leaveIdFromShift.equals(leaveIdFromDetails)) {
									/**
									 * If lateMarks <= closingBalance, then adjust all late marks and deduct closing balance by 
									 * late marks, else consume entire balance and deduct late marks by balance
									 */
									if(numOfLateMarksMins <= closingBalanceMins) {
										lateMarkMinsAdjusted += numOfLateMarksMins;
										closingBalanceMins -= numOfLateMarksMins;
										numOfLateMarksMins = 0;
									} else {
										numOfLateMarksMins -= closingBalanceMins;
										lateMarkMinsAdjusted += closingBalanceMins;
										closingBalanceMins = 0;
									}
									
									// add adjusted late marks into total paid leaves
									numOfPaidLateMarkMins += lateMarkMinsAdjusted;
								}
								
								// if leave is from shift is 0 (unpaid), then add remaining or all late marks into total unpaid 
								// leaves
								else if(leaveIdFromShift.equals("0")) {
									// add remaining or all late marks into total unpaid leaves
									numOfUnPaidLateMarkMins += numOfLateMarksMins;
									setNumOfUnPaidLateMarkMins(numOfUnPaidLateMarkMins);
									
									break;
								}
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								adjustLateMarksMins[j][0] = String.valueOf(leaveMinsDetails[j][0]); // empId
								adjustLateMarksMins[j][1] = leaveIdFromDetails;
								adjustLateMarksMins[j][2] = openingBalanceMins;
								adjustLateMarksMins[j][3] = paidLeaveDays;
								adjustLateMarksMins[j][4] = lateMarkMinsAdjusted;
								adjustLateMarksMins[j][5] = halfDaysAdjusted;
								adjustLateMarksMins[j][6] = closingBalanceMins;
							}
						}
					}
				}
			}		
		} catch(Exception e) {
			logger.error("Exception in adjustLateMarksOnHoursBasis:" + e);
		}
		return adjustLateMarksMins;
	}
	
	private Object[][] adjustUnauthorisedLeavesOnDaysBasis(Object[][] leaveDetails, String empId, double leaveUnauthDays, 
		double leaveUnauthAbsents, String[] leaveUnauthLeaveTypes, double unauthAbsents) {
		Object[][] attendanceDetails = null;
		try {
			attendanceDetails = leaveDetails;
			
			if(unauthAbsents > 0) {
				if(leaveUnauthLeaveTypes != null && leaveUnauthLeaveTypes.length > 0) {
					// get leaves Ids from attendance settings to adjust half days
					for(int i = 0; i < leaveUnauthLeaveTypes.length; i++) {
						// leave Id from shift
						String leaveIdFromPolicy = String.valueOf(leaveUnauthLeaveTypes[i]);
						
						if(leaveDetails != null && leaveDetails.length > 0) {
							// get leave details after adjusting late marks
							for(int j = 0; j < leaveDetails.length; j++) {
								// get leave details
								String leaveIdFromDetails = String.valueOf(attendanceDetails[j][1]);
								double openingBalance = Double.parseDouble(String.valueOf(attendanceDetails[j][2]));
								double paidLeaveDays = Double.parseDouble(String.valueOf(attendanceDetails[j][3]));
								double lateMarksAdjusted = Double.parseDouble(String.valueOf(attendanceDetails[j][4]));
								double halfDaysAdjusted = Double.parseDouble(String.valueOf(attendanceDetails[j][5]));
								double closingBalance = Double.parseDouble(String.valueOf(attendanceDetails[j][6]));
								double unauthLeavesAdjusted = Double.parseDouble(String.valueOf(attendanceDetails[j][7]));
								
								// if leave id from leave details matches with leave ids from shift, then only adjust 
								// the half days
								if(leaveIdFromPolicy.equals(leaveIdFromDetails)) {
									/**
									 * If halfDays <= closingBalance, then adjust all half days and deduct closing balance by half 
									 * days else consume entire balance and deduct half days by balance
									 */
									if(unauthAbsents <= closingBalance) {
										unauthLeavesAdjusted += unauthAbsents;
										closingBalance -= unauthAbsents;
										unauthAbsents = 0;
									} else {
										// round the closing balance, e.g. 1.74 = 1.5, 1.47 = 1.0, 1.56 = 1.5
										double roundedBalance = getNumberInRound(closingBalance);
										
										unauthAbsents -= roundedBalance;
										unauthLeavesAdjusted += roundedBalance;
										closingBalance -= roundedBalance;
									}
									
									// add adjusted half days into total paid leaves
									numOfUnauthAdjLeaves += unauthLeavesAdjusted;									
								}
								
								// if leave is from shift is 0 (unpaid), then add remaining or all half days into total unpaid 
								// leaves
								else if(leaveIdFromPolicy.equals("0")) {
									// add remaining or all half days into total unpaid leaves
									numOfUnauthUnAdjLeaves += unauthAbsents;
									
									break;
								}
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								attendanceDetails[j][0] = String.valueOf(leaveDetails[j][0]); // empId
								attendanceDetails[j][1] = leaveIdFromDetails;
								attendanceDetails[j][2] = openingBalance;
								attendanceDetails[j][3] = paidLeaveDays;
								attendanceDetails[j][4] = lateMarksAdjusted;
								attendanceDetails[j][5] = halfDaysAdjusted;
								attendanceDetails[j][6] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
								attendanceDetails[j][7] = unauthLeavesAdjusted;
							}
						}
					}
				}			
			}
		} catch(Exception e) {
			logger.error("Exception in adjustUnauthorisedLeavesOnDaysBasis:" + e);
		}
		return attendanceDetails;
	}
	
	private Object[][] adjustUnauthorisedLeavesOnHoursBasis(Object[][] leaveDetails, String empId, double leaveUnauthDays, 
		double leaveUnauthAbsents, String[] leaveUnauthLeaveTypes, double unauthAbsents) {
		Object[][] attendanceDetails = null;
		try {
			attendanceDetails = leaveDetails;
			
			if(unauthAbsents > 0) {
				if(leaveUnauthLeaveTypes != null && leaveUnauthLeaveTypes.length > 0) {
					// get leaves Ids from attendance settings to adjust half days
					for(int i = 0; i < leaveUnauthLeaveTypes.length; i++) {
						// leave Id from policy
						String leaveIdFromPolicy = String.valueOf(leaveUnauthLeaveTypes[i]);
						
						if(leaveDetails != null && leaveDetails.length > 0) {
							// get leave details after adjusting late marks
							for(int j = 0; j < attendanceDetails.length; j++) {
								// get leave details
								String leaveIdFromDetails = String.valueOf(attendanceDetails[j][1]);
								int openingBalanceMins = Integer.parseInt(String.valueOf(attendanceDetails[j][2]));
								double paidLeaveDays = Double.parseDouble(String.valueOf(attendanceDetails[j][3]));
								int lateMarkMinsAdjusted = Integer.parseInt(String.valueOf(attendanceDetails[j][4]));
								double halfDaysAdjusted = Double.parseDouble(String.valueOf(attendanceDetails[j][5]));
								int closingBalanceMins = Integer.parseInt(String.valueOf(attendanceDetails[j][6]));
								double unauthLeavesAdjusted = Double.parseDouble(String.valueOf(attendanceDetails[j][7]));
								
								// convert closing balance minutes into days
								double closingBalDays = Math.round(getDays(closingBalanceMins) * Math.pow(10, 2)) / Math.pow(10, 2);
								
								// if leave id from leave details matches with leave ids from shift, then only adjust 
								// the half days
								if(leaveIdFromPolicy.equals(leaveIdFromDetails)) {
									/**
									 * If halfDays <= closingBalance, then adjust all half days and deduct closing balance by half 
									 * days else consume entire balance and deduct half days by balance
									 */
									if(unauthAbsents <= closingBalDays) {
										unauthLeavesAdjusted += unauthAbsents;
										closingBalDays -= unauthAbsents;
										unauthAbsents = 0;
									} else {
										// round the closing balance, e.g. 1.74 = 1.5, 1.47 = 1.0, 1.56 = 1.5
										double roundedBalance = getNumberInRound(closingBalDays);
										
										unauthAbsents -= roundedBalance;
										unauthLeavesAdjusted += roundedBalance;
										closingBalDays -= roundedBalance;
									}
									
									// add adjusted half days into total paid leaves
									numOfUnauthAdjLeaves += unauthLeavesAdjusted;
								}
								
								// if leave is from shift is 0 (unpaid), then add remaining or all half days into total unpaid 
								// leaves
								else if(leaveIdFromPolicy.equals("0")) {
									// add remaining or all half days into total unpaid leaves
									numOfUnauthUnAdjLeaves += unauthAbsents;
									
									break;
								}
								
								// convert closing balance days into minutes
								closingBalanceMins = getMinutes(closingBalDays);
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								attendanceDetails[j][0] = String.valueOf(leaveDetails[j][0]); // empId
								attendanceDetails[j][1] = leaveIdFromDetails;
								attendanceDetails[j][2] = openingBalanceMins;
								attendanceDetails[j][3] = paidLeaveDays;
								attendanceDetails[j][4] = lateMarkMinsAdjusted;
								attendanceDetails[j][5] = halfDaysAdjusted;
								attendanceDetails[j][6] = closingBalanceMins;
								attendanceDetails[j][7] = unauthLeavesAdjusted;
							}
						}
					}
				}			
			}
		} catch(Exception e) {
			logger.error("Exception in adjustUnauthorisedLeavesOnHoursBasis:" + e);
		}
		return attendanceDetails;
	}
	
	/**
	 * Adjust the paid leaves from uploaded leaves
	 * @param empId: Employee id whose paid leave details are to be calculated
	 * @param leaveDetails: Leave details of paid leaves
	 * @param numOfUploadPaidLeaves: No. of paid leaves from uploaded leaves
	 * @return Object[][] as adjusted uploaded leaves
	 */
	private Object[][] adjustUploadedLeaves(String empId, Object[][] leaveDetails, double numOfUploadPaidLeaves) {
		Object[][] uploadedLeaves = null;
		try {
			// Get leaves Ids from attendance settings to adjust uploaded paid leaves
			String[] leaveIdsForUploadAttendnace = String.valueOf(attendanceSettings[0][7]).split(",");
			
			uploadedLeaves = leaveDetails;
			
			/**
			 * If records exist, then only get paid leaves details
			 */
			if(uploadedLeaves != null && uploadedLeaves.length > 0) {
				for(int i = 0; i < leaveIdsForUploadAttendnace.length; i++) {
					String leaveIdFromSettings = String.valueOf(leaveIdsForUploadAttendnace[i]); // leave Id from attendance settings
					
					for(int j = 0; j < uploadedLeaves.length; j++) {
						// get leave details
						String leaveIdFromDetails = String.valueOf(uploadedLeaves[j][1]);
						double openingBalance = Double.parseDouble(String.valueOf(uploadedLeaves[j][2]));
						double adjustUploadPaidLeaves = Double.parseDouble(String.valueOf(uploadedLeaves[j][3]));
						double lateMarksAdjusted = Double.parseDouble(String.valueOf(uploadedLeaves[j][4]));
						double halfDaysAdjusted = Double.parseDouble(String.valueOf(uploadedLeaves[j][5]));
						double closingBalance = Double.parseDouble(String.valueOf(uploadedLeaves[j][6]));
						
						// uploaded leave code matches with leave code in paid leave balances
						if(leaveIdFromSettings.equals(leaveIdFromDetails)) {
							/**
							 * If numOfUploadPaidLeaves <= closingBalance, then adjust all paid leaves and deduct closing balance by paid leaves
							 * else consume entire balance and deduct paid leaves by balance
							 */
							if(numOfUploadPaidLeaves <= closingBalance) {
								adjustUploadPaidLeaves += numOfUploadPaidLeaves;
								numOfPaidLeaves += numOfUploadPaidLeaves; // add adjusted paid leaves into total paid leaves
								closingBalance -= numOfUploadPaidLeaves;
								numOfUploadPaidLeaves = 0;
							} else {
								// round the closing balance, e.g. 1.74 = 1.5, 1.47 = 1.0, 1.56 = 1.5
								double roundedBalance = getNumberInRound(closingBalance);
								
								numOfUploadPaidLeaves -= roundedBalance;
								adjustUploadPaidLeaves += roundedBalance;
								numOfPaidLeaves += roundedBalance; // add adjusted paid leaves into total paid leaves
								closingBalance -= roundedBalance;
							}
						}
						
						// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
						uploadedLeaves[j][0] = empId;
						uploadedLeaves[j][1] = leaveIdFromDetails;
						uploadedLeaves[j][2] = openingBalance;
						uploadedLeaves[j][3] = adjustUploadPaidLeaves;
						uploadedLeaves[j][4] = lateMarksAdjusted;
						uploadedLeaves[j][5] = halfDaysAdjusted;
						uploadedLeaves[j][6] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
					}
				}
			}
			
			// add remaining or all paid leaves into total unpaid leaves
			numOfUnPaidLeaves += numOfUploadPaidLeaves;
		} catch(Exception e) {
			logger.error("Exception in adjustUploadedLeaves:" + e);
		}
		return uploadedLeaves;
	}
	
	/**
	 * Load the attendance settings, load attendance details like employee details, holidays, weekly offs, late marks, half days 
	 * etc from database. Create an object to save the attendance and attendance details
	 * @param month: Month for which attendance to be processed
	 * @param year: Year for which attendance to be processed
	 * @param listOfFilters: Contains list of filters selected from a page
	 * @param divisionId: Division Id to initialise object used to get information about leave policies
	 * @return String as a message whether attendance is processed properly or not
	 */
	public String attendanceProcess(String month, String year, String divisionId, String[] listOfFilters) {
		String message = "";
		try {
			// check whether table is created or not the year selected
			boolean isTableExists = isTableExists(year);
			
			if(isTableExists) {
				// Check whether attendance is alrady processed for the filters selected
				boolean isAttendanceProcessed = isAttendanceProcessed(month, year, listOfFilters);
				
				if(!isAttendanceProcessed) {
					loadAttendanceSettings(); // Load attendance settings
					
					boolean leaveConnFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][0]));
					boolean branchHoliDayFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][1]));
					boolean dailyAttnConnFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][2]));
					boolean uploadAttnConnFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][6]));
					String defaultAttnDays = String.valueOf(attendanceSettings[0][8]);
					
					// If month is in between 1 to 9, then append 0 before the month
					month = Integer.parseInt(month) < 10 ? "0" + month : month;
					
					// Calculate fromDate and toDate
					String salaryStartDate = String.valueOf(attendanceSettings[0][3]);
					String salaryStartMonth = String.valueOf(attendanceSettings[0][4]);
					String fromDate = setFromDate(dailyAttnConnFlag, month, year, salaryStartDate, salaryStartMonth);
					String toDate = setToDate(dailyAttnConnFlag, month, year, salaryStartDate, salaryStartMonth);
					
					// Get List of employees
					Object[][] employees = loadEmployeeList(toDate, listOfFilters);
					
					Object[][] calculatedAttendnace = null;
					
					if(employees != null && employees.length > 0) {
						// Concatenate employee Ids, used those Ids in SQL queries to get records of only those employees
						String concatEmployeeIds = "";
						if(employees != null && employees.length > 0) {
							for(int i = 0; i < employees.length; i++) {
								if(i == (employees.length - 1)) {
									concatEmployeeIds += String.valueOf(employees[i][0]);
								} else {
									concatEmployeeIds += String.valueOf(employees[i][0]) + ",";
								}
							}
						}
												
						// Set leave policy information
						LeavePolicyData leavePolicyData = new LeavePolicyData(divisionId);
						leavePolicyData.initiate(context, session);
						leavePolicyData.setLeavePolicyObject();
						
						// Load attendance details
						loadAttendanceDetails(concatEmployeeIds, leavePolicyData, branchHoliDayFlag, dailyAttnConnFlag, uploadAttnConnFlag, fromDate, 
							toDate, month, year, salaryStartMonth,defaultAttnDays);
						
						// Calculate the attendance for each employee
						calculatedAttendnace = getCalculatedAttendance(employees, fromDate, toDate, month, year, branchHoliDayFlag, leaveConnFlag, 
							dailyAttnConnFlag, uploadAttnConnFlag, leavePolicyData);
					}
					
					// Save attendance and its details
					if(calculatedAttendnace != null && calculatedAttendnace.length > 0) {
						message = setAttendanceToSave(month, year, listOfFilters, calculatedAttendnace);
					} else {
						message = "Attendance cannot be processed!";
					}
				} else {
					message = "Attendance is already processed!";
				}
			} else {
				message = "Attendance cannot be processed!";
			}
			
			// Reset the global variables
			resetToNull();
		} catch(Exception e) {
			logger.error("Exception in attendanceProcess:" + e);
			message = "Attendance cannot be processed!";
		}
		return message;
	}

	/**
	 * Calculate Attendance Days by deducting weekly offs, holidays, paid leave and unpaid leaves from totalAttendanceDays
	 * @param totalAttendanceDays: Days between fromDate and toDate
	 * @param numOfWeeklyOffs: Total no. of weekly offs
	 * @param numOfHolidays: Total no. of holidays
	 * @return double as total no. of attendance days
	 */
	private double calAttendanceDaysOnDaysBasis(double totalAttendanceDays, double numOfWeeklyOffs, double numOfHolidays, double totalPaidLeaves, 
			double totalUnpaidLeaves) {
		double numOfAttendanceDays = 0.0;
		try {
			numOfAttendanceDays = totalAttendanceDays - (numOfWeeklyOffs + numOfHolidays + totalPaidLeaves + totalUnpaidLeaves);
			
			if(numOfAttendanceDays < 0) {
				numOfAttendanceDays = 0;
			}
		} catch(Exception e) {
			logger.error("Exception in calAttendanceDays:" + e);
		}
		return numOfAttendanceDays;
	}
	
	private int calAttendanceDaysOnHoursBasis(int totalAttendanceMins, int numOfWeeklyOffsMins, int numOfHolidaysMins, int totalPaidLeavesMins, 
		int totalUnpaidLeavesMins) {
		int numOfAttendanceMins = 0;
		try {
			numOfAttendanceMins = totalAttendanceMins - (numOfWeeklyOffsMins + numOfHolidaysMins + totalPaidLeavesMins + totalUnpaidLeavesMins);
			
			if(numOfAttendanceMins < 0) {
				numOfAttendanceMins = 0;
			}
		} catch(Exception e) {
			logger.error("Exception in calAttendanceDays:" + e);
		}
		return numOfAttendanceMins;
	}
	
	private Object[][] calculateAttendanceOnDaysBasis(String empId, String empBranch, String leavePolicyId, String empJoinDate, 
		boolean branchHoliDayFlag, boolean leaveConnFlag, boolean dailyConnected, boolean uploadAttnConnFlag, String fromDate, String newFromDate, 
		String toDate, String month, String year, boolean isAutoPresentLateMark, boolean isAutoPresentHalfDay, boolean isAutoPresentAbsent, 
		boolean isLMEnabled, int lmCount, double lmAdjustLeaveDays, String lmAdjustLeaveTypes, boolean isHDEnabled, String hdDeductLeaveTypes, 
		boolean isWOFixedEnabled, String woDaysForWeek1, String woDaysForWeek2, String woDaysForWeek3, String woDaysForWeek4, String woDaysForWeek5, 
		String woDaysForWeek6) {
		Object[][] attendanceOnDaysBasis = new Object[1][22];
		try {
			attendanceOnDaysBasis[0][0] = empId;
			/**
			 * Calculate days between fromDate and toDate
			 */
			int totalAttendanceDays = calTotalAttendanceDays(month, year, empJoinDate);
			
			/**
			 * Calculate holidays
			 */
			int numOfHolidays = calHolidays(branchHoliDayFlag, dailyConnected, fromDate, toDate, empId, empBranch, month, year, empJoinDate);
			attendanceOnDaysBasis[0][1] = String.valueOf(numOfHolidays);
			
			/**
			 * Calculate weekly offs
			 */
			int numOfWeeklyOffs = calWeeklyOff(dailyConnected, fromDate, toDate, empId, isWOFixedEnabled, branchHoliDayFlag, empBranch, 
				woDaysForWeek1, woDaysForWeek2, woDaysForWeek3, woDaysForWeek4, woDaysForWeek5, woDaysForWeek6, month, year, empJoinDate);
			attendanceOnDaysBasis[0][2] = String.valueOf(numOfWeeklyOffs);
			
			
			/**
			 * Calculate late marks in days
			 */
			int numOfLateMarks = calLateMarksOnDaysBasis(dailyConnected, uploadAttnConnFlag, empId, isLMEnabled, isAutoPresentLateMark, 
				isAutoPresentAbsent, newFromDate, toDate);
			attendanceOnDaysBasis[0][3] = String.valueOf(numOfLateMarks);
			
			// Calculate late marks in hours
			attendanceOnDaysBasis[0][4] = "00:00";
			
			
			/**
			 * Calculate half days
			 */
			int numOfHalfDays = calHalfDays(dailyConnected, uploadAttnConnFlag, empId, isHDEnabled, isAutoPresentHalfDay, isAutoPresentAbsent, 
				newFromDate, toDate);
			attendanceOnDaysBasis[0][5] = String.valueOf(numOfHalfDays);
			
			
			/**
			 * Calculate leave details
			 */
			Object[][] attendanceDetailsOnDaysBasis = calPaidLeavesOnDaysBasis(leaveConnFlag, uploadAttnConnFlag, empId, leavePolicyId, 
				numOfLateMarks, numOfHalfDays, lmCount, lmAdjustLeaveDays, lmAdjustLeaveTypes, hdDeductLeaveTypes);
			
			
			/**
			 * Calculate total paid leaves
			 */
			double totalPaidLeaves = numOfPaidLeaves + numOfPaidLateMarks + numOfPaidHalfDays;
			attendanceOnDaysBasis[0][6] = String.valueOf(totalPaidLeaves);
						
			// Calculate paid leaves in hours
			attendanceOnDaysBasis[0][7] = "00:00";
			
			
			/**
			 * Calculate penalty adjusted days
			 */
			double penaltyAdjustedDays = calPenaltyAdjustedDays(empId, leaveConnFlag);
			attendanceOnDaysBasis[0][8] = String.valueOf(penaltyAdjustedDays);
			
			/**
			 * Calculate absents from daily attendance
			 */
			getUnauthorisedAbsents(empId, dailyConnected);
			
			
			/**
			 * Calculate total unpaid leaves
			 */
			// Calculate total unpaid leaves, containing unpaid leaves from leave application + uploaded unpaid leaves + unpaid late marks 
			// + unpaid half days
			calUnPaidLeavesOnDaysBasis(isAutoPresentAbsent, leaveConnFlag, uploadAttnConnFlag, empId, leavePolicyId);
			
			double totalUnpaidLeaves = numOfUnPaidLeaves + numOfUnPaidLateMarks + numOfUnPaidHalfDays;
			
			attendanceOnDaysBasis[0][9] = String.valueOf(totalUnpaidLeaves);
						
			// Calculate unpaid leaves in hours
			attendanceOnDaysBasis[0][10] = "00:00";
			
			
			/**
			 * Calculate penalty unadjusted days
			 */
			double penaltyUnAdjustedDays = calPenaltyUnAdjustedDays(empId, leaveConnFlag);
			attendanceOnDaysBasis[0][11] = String.valueOf(penaltyUnAdjustedDays);
			
			
			/**
			 * Calculate system unpaid leaves
			 */
			// Calculate system adjusted unpaid leaves, containing unpaid leaves either from leave application or from uploaded leaves
			attendanceOnDaysBasis[0][12] = String.valueOf(numOfUnPaidLeaves);
			
			
			/**
			 * Calculate unauthorised adjusted days
			 */
			attendanceDetailsOnDaysBasis = calUnauthorisedLeaves(attendanceDetailsOnDaysBasis, empId, leavePolicyId, dailyConnected, false);
			attendanceOnDaysBasis[0][15] = numOfUnauthAdjLeaves;
			
			attendanceOnDaysBasis[0][16] = numOfUnauthUnAdjLeaves;
			
			
			/**
			 * Calculate attendance days
			 */
			// Calculate attendance days
			double numOfAttendanceDays=0;
			double numOfSalaryDays =0;
			
				numOfAttendanceDays = calAttendanceDaysOnDaysBasis(totalAttendanceDays, numOfWeeklyOffs, numOfHolidays, totalPaidLeaves, 
						totalUnpaidLeaves);
				
				
		
			attendanceOnDaysBasis[0][13] = String.valueOf(numOfAttendanceDays);
			
			// Calculate attendance hours
			attendanceOnDaysBasis[0][14] = "00:00";
			
			
			/**
			 * Calculate salary days
			 */
			// Calculate salary days
			numOfSalaryDays = calSalaryDaysOnDaysBasis(totalAttendanceDays, totalUnpaidLeaves, penaltyUnAdjustedDays);
			attendanceOnDaysBasis[0][17] = String.valueOf(numOfSalaryDays);
			// Calculate salary hours
			attendanceOnDaysBasis[0][18] = "00:00";
			
			
			/**
			 * Add attendanceMinsDetails object
			 */
			// Add attendanceDetails object
			attendanceOnDaysBasis[0][19] = getRevisedAttendanceDetails(attendanceDetailsOnDaysBasis, false);
			
			
			/**
			 * Convert total days between fromDate and toDate into minutes
			 */
			attendanceOnDaysBasis[0][20] = totalAttendanceDays;
			
			/**
			 * recovery days
			 */
			
			attendanceOnDaysBasis[0][21] = getUploadedRecoveryDays(empId);
		} catch(Exception e) {
			logger.error("Exception in calculateAttendanceOnDaysBasis:" + e);
		}
		return attendanceOnDaysBasis;
	}

	private Object[][] calculateAttendanceOnHoursBasis(String empId, String empBranch, String leavePolicyId, String empJoinDate, 
		boolean branchHoliDayFlag, boolean leaveConnFlag, boolean dailyConnected, String fromDate, String newFromDate, String toDate, String month, 
		String year, boolean isAutoPresentLateMark, boolean isAutoPresentHalfDay, boolean isAutoPresentAbsent, boolean isLMEnabled, 
		String lmDeductNonRegLeaveTypes, boolean isHDEnabled, String hdDeductLeaveTypes, boolean isWOFixedEnabled, String woDaysForWeek1, 
		String woDaysForWeek2, String woDaysForWeek3, String woDaysForWeek4, String woDaysForWeek5, String woDaysForWeek6) {
		Object[][] attendanceOnHoursBasis = new Object[1][22];
		try {
			attendanceOnHoursBasis[0][0] = empId;
			
			
			/**
			 * Calculate days between fromDate and toDate
			 */
			// Calculate total days between fromDate and toDate
			int totalAttendanceDays = calTotalAttendanceDays(month, year, empJoinDate);
			
			// convert total days between fromDate and toDate into minutes
			int totalAttendanceMins = getMinutes(totalAttendanceDays);
			
			
			/**
			 * Calculate holidays
			 */
			double numOfHolidays = calHolidays(branchHoliDayFlag, dailyConnected, fromDate, toDate, empId, empBranch, month, year, empJoinDate);
			int numOfHolidaysMins = getMinutes(numOfHolidays);
			attendanceOnHoursBasis[0][1] = String.valueOf(numOfHolidays);
			
			
			/**
			 * Calculate weekly offs
			 */
			double numOfWeeklyOffs = calWeeklyOff(dailyConnected, fromDate, toDate, empId, isWOFixedEnabled, branchHoliDayFlag, empBranch, 
				woDaysForWeek1, woDaysForWeek2, woDaysForWeek3, woDaysForWeek4, woDaysForWeek5, woDaysForWeek6, month, year, empJoinDate);
			int numOfWeeklyOffsMins = getMinutes(numOfWeeklyOffs);
			attendanceOnHoursBasis[0][2] = String.valueOf(numOfWeeklyOffs);
			
			
			/**
			 * Calculate late marks in minutes
			 */
			// Calculate total late marks 
			int numOfLateMarksMins = calLateMarksOnHoursBasis(empId, isLMEnabled, isAutoPresentLateMark, isAutoPresentAbsent, newFromDate, toDate);
			String[] lateMarksDaysAndMins = getDaysAndRemainingMins(numOfLateMarksMins).split(",");
			
			// calculate late marks in days
			attendanceOnHoursBasis[0][3] = lateMarksDaysAndMins[0];
			
			// Calculate remaining late marks in hours
			attendanceOnHoursBasis[0][4] = getHours(Integer.parseInt(lateMarksDaysAndMins[1]));
			
			
			/**
			 * Calculate half days
			 */
			int numOfHalfDays = calHalfDays(true, false, empId, isHDEnabled, isAutoPresentHalfDay, isAutoPresentAbsent, newFromDate, toDate);
			attendanceOnHoursBasis[0][5] = String.valueOf(numOfHalfDays);
			
			
			/**
			 * Calculate leave details
			 */
			Object[][] attendanceDetailsOnHoursBasis = calPaidLeavesOnHoursBasis(leaveConnFlag, empId, leavePolicyId, numOfLateMarksMins, 
				numOfHalfDays, lmDeductNonRegLeaveTypes, hdDeductLeaveTypes);
			
			// get paid leaves and half days in minutes
			int numOfPaidLeavesMins = getMinutes(numOfPaidLeaves);
			int numOfPaidHalfDaysMins = getMinutes(numOfPaidHalfDays);
			
			
			/**
			 * Calculate total paid leaves
			 */
			int totalPaidLeavesMins = numOfPaidLeavesMins + numOfPaidLateMarkMins + numOfPaidHalfDaysMins;
			//double totalPaidLeaves = getNumberInRound(getDays(totalPaidLeavesMins));
			String[] totalPaidLevDaysAndMins = getDaysAndRemainingMins(totalPaidLeavesMins).split(",");
			
			// calculate total paid leaves in days
			attendanceOnHoursBasis[0][6] = totalPaidLevDaysAndMins[0];
			
			// Calculate remaining paid leaves in hours
			attendanceOnHoursBasis[0][7] = getHours(Integer.parseInt(totalPaidLevDaysAndMins[1]));
			
			
			/**
			 * Calculate penalty adjusted days
			 */
			double penaltyAdjustedDays = calPenaltyAdjustedDays(empId, leaveConnFlag);
			attendanceOnHoursBasis[0][8] = String.valueOf(penaltyAdjustedDays);
			
			/**
			 * Calculate absents from daily attendance
			 */
			getUnauthorisedAbsents(empId, dailyConnected);
			
			
			/**
			 * Calculate total unpaid leaves
			 */
			// Calculate total unpaid leaves, containing unpaid leaves from leave application + uploaded unpaid leaves + unpaid late marks 
			// + unpaid half days
			calUnPaidLeavesOnHoursBasis(isAutoPresentAbsent, leaveConnFlag, empId, leavePolicyId);
			
			// get unpaid leaves and unpaid half days in minutes
			int numOfUnPaidLeavesMins = getMinutes(numOfUnPaidLeaves);
			int numOfUnPaidHalfDaysMins = getMinutes(numOfUnPaidHalfDays);
			
			// calculate total unpaid leaves in minutes
			int totalUnpaidLeavesMins = numOfUnPaidLeavesMins + numOfUnPaidLateMarkMins + numOfUnPaidHalfDaysMins;
			//double totalUnpaidLeaves = getNumberInRound(getDays(totalUnpaidLeavesMins));
			String[] totalUnPaidLevDaysAndMins = getDaysAndRemainingMins(totalUnpaidLeavesMins).split(",");
			
			// calculate unpaid leaves in days
			attendanceOnHoursBasis[0][9] = totalUnPaidLevDaysAndMins[0];
			
			// Calculate unpaid leaves in hours
			attendanceOnHoursBasis[0][10] = getHours(Integer.parseInt(totalUnPaidLevDaysAndMins[1]));
			
			
			/**
			 * Calculate penalty unadjusted days
			 */
			// Calculate total penalty unadjusted days
			double penaltyUnAdjustedDays = calPenaltyUnAdjustedDays(empId, leaveConnFlag);
			attendanceOnHoursBasis[0][11] = String.valueOf(penaltyUnAdjustedDays);
			
			// calculate penalty unadjusted days in minutes
			int penaltyUnAdjustedMins = getMinutes(penaltyUnAdjustedDays);
			
			
			/**
			 * Calculate system unpaid leaves
			 */
			// Calculate system adjusted unpaid leaves, containing unpaid leaves either from leave application or from uploaded leaves
			attendanceOnHoursBasis[0][12] = String.valueOf(numOfUnPaidLeaves);
			
			
			/**
			 * Calculate unauthorised adjusted days
			 */
			attendanceDetailsOnHoursBasis = calUnauthorisedLeaves(attendanceDetailsOnHoursBasis, empId, leavePolicyId, dailyConnected, true);
			attendanceOnHoursBasis[0][15] = numOfUnauthAdjLeaves;
			
			attendanceOnHoursBasis[0][16] = numOfUnauthUnAdjLeaves;
			int numOfUnauthUnAdjLeavesMins = getMinutes(numOfUnauthUnAdjLeaves);
			
			
			/**
			 * Calculate attendance days and hours
			 */
			// Calculate total attendance minutes
			int numOfAttendanceMins = calAttendanceDaysOnHoursBasis(totalAttendanceMins, numOfWeeklyOffsMins, numOfHolidaysMins, totalPaidLeavesMins, 
				totalUnpaidLeavesMins);
			String[] attendanceDaysAndMins = getDaysAndRemainingMins(numOfAttendanceMins).split(",");
			
			// calculate attendance days
			attendanceOnHoursBasis[0][13] = attendanceDaysAndMins[0];
			
			// Calculate attendance hours
			attendanceOnHoursBasis[0][14] = getHours(Integer.parseInt(attendanceDaysAndMins[1]));
			
			
			/**
			 * Calculate salary days and hours
			 */
			// Calculate total salary minutes
			int numOfSalaryMins = calSalaryDaysOnHoursBasis(totalAttendanceMins, totalUnpaidLeavesMins, penaltyUnAdjustedMins, 
				numOfUnauthUnAdjLeavesMins);
			String[] salaryDaysAndMins = getDaysAndRemainingMins(numOfSalaryMins).split(",");
			
			// Calculate salary days
			attendanceOnHoursBasis[0][17] = salaryDaysAndMins[0];
			
			// Calculate salary hours
			attendanceOnHoursBasis[0][18] = getHours(Integer.parseInt(salaryDaysAndMins[1]));
			
			
			/**
			 * Add attendanceMinsDetails object
			 */
			attendanceOnHoursBasis[0][19] = getRevisedAttendanceDetails(attendanceDetailsOnHoursBasis, true);
			
			
			/**
			 * Convert total days between fromDate and toDate into minutes
			 */
			attendanceOnHoursBasis[0][20] = totalAttendanceMins;
			
			attendanceOnHoursBasis[0][21] = getUploadedRecoveryDays(empId);
		} catch(Exception e) {
			logger.error("Exception in calculateAttendanceOnHoursBasis:" + e);
		}
		return attendanceOnHoursBasis;
	}
	
	/**
	 * Calculate half days for an employee
	 * @param uploadAttnConnFlag: Whether upload monthly attendance work flow is enabled or not
	 * @param dailyConnected: Daily attendance work flow is enabled or not
	 * @param empId: Employee id whose half days are calculated
	 * @return int as number of half days
	 */
	private int calHalfDays(boolean dailyConnected, boolean uploadAttnConnFlag, String empId, boolean isHDEnabled, boolean isAutoPresentHalfDay, 
		boolean isAutoPresentAbsent, String fromDate, String toDate) {
		int numOfHalfDay = 0;
		try {
			if(isHDEnabled && !isAutoPresentHalfDay && !isAutoPresentAbsent) {
				// Get half days from uploaded attendance for an employee
				if(uploadAttnConnFlag) { // Upload monthly attendance workflow is enabled
					if(uploadAttendance != null && uploadAttendance.length > 0) {
						for(int i = 0; i < uploadAttendance.length; i++) {
							if(empId.equals(String.valueOf(uploadAttendance[i][0]))) {
								numOfHalfDay = Integer.parseInt(String.valueOf(uploadAttendance[i][2]));
								break;
							}
						}
					}
				}
				
				else if(dailyConnected) { // Daily attendance work flow is enabled
					// Get half days from daily attendance for an employee
					if(lateMarksHalfDays != null && lateMarksHalfDays.length > 0) {
						// Create calendar object for fromDate and toDate
						Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
						Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
						
						for(int i = 0; i < lateMarksHalfDays.length; i++) {
							if(empId.equals(String.valueOf(lateMarksHalfDays[i][0]))) {
								Calendar attDateCalendar = new Utility().getCalanderDate(String.valueOf(lateMarksHalfDays[i][1]));
								if((attDateCalendar.equals(fromDateCalendar) || attDateCalendar.after(fromDateCalendar)) && 
										(attDateCalendar.equals(toDateCalendar) || attDateCalendar.before(toDateCalendar))) {
									numOfHalfDay += Integer.parseInt(String.valueOf(lateMarksHalfDays[i][3]));
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in calHalfDays:" + e);
		}
		return numOfHalfDay;
	}
	
	private void getHolidays(boolean dailyConnected, boolean branchHoliDayFlag, String empId, String empBranch) {
		try {
			/**
			 * Get holidays from master
			 */
			ArrayList<String> masterHolidays = new ArrayList<String>();
			if(holidaysFromMaster != null && holidaysFromMaster.length > 0) {
				if(branchHoliDayFlag) {
					for(int i = 0; i < holidaysFromMaster.length; i++) {
						if(empBranch.equals(String.valueOf(holidaysFromMaster[i][0]))) {
							masterHolidays.add(String.valueOf(holidaysFromMaster[i][1]));
						}
					}
				} else {
					for(int i = 0; i < holidaysFromMaster.length; i++) {
						masterHolidays.add(String.valueOf(holidaysFromMaster[i][1]));
					}
				}
			}
			
			/**
			 * Get holidays from daily attendance
			 */
			ArrayList<String> attendanceHolidays = new ArrayList<String>();
			if(dailyConnected) {
				if(holidaysFromDailyAttendance != null && holidaysFromDailyAttendance.length > 0) {
					for(int i = 0; i < holidaysFromDailyAttendance.length; i++) {
						String empIdFromAttendance = String.valueOf(holidaysFromDailyAttendance[i][0]);
						
						if(empIdFromAttendance.equals(empId)) {
							attendanceHolidays.add(String.valueOf(holidaysFromDailyAttendance[i][1]));
							break;
						}
					}
				}
			}
			
			/**
			 * Copy holidays into one object
			 */
			if(dailyConnected) {
				ArrayList<String> totalAttendanceHolidays = new ArrayList<String>();
				
				// copy holidays from daily attendance
				totalAttendanceHolidays = attendanceHolidays;
				
				if(masterHolidays != null && masterHolidays.size() > 0) {
					for(int i = 0; i < masterHolidays.size(); i++) {
						String holidayMaster = masterHolidays.get(i);
						
						if(attendanceHolidays != null && attendanceHolidays.size() > 0) {
							boolean isHolidayExists = false;
							
							for(int j = 0; j < attendanceHolidays.size(); j++) {
								String holidayAttendance = attendanceHolidays.get(j);
								
								if(holidayMaster.equals(holidayAttendance)) {
									isHolidayExists = true;
									break;
								}
							}
							
							// if holiday not exists in daily attendance, then add holiday from shift into total holidays. 
							// Also increment extra holidays
							if(!isHolidayExists) {
								totalAttendanceHolidays.add(holidayMaster);
								extraHolidays++;
							}
						}
					}
				}
				
				/**
				 * Copy total attendance holidays into 
				 */
				if(totalAttendanceHolidays != null && totalAttendanceHolidays.size() > 0) {
					holidays = totalAttendanceHolidays;
				}
			} else {
				if(masterHolidays != null && masterHolidays.size() > 0) {
					holidays = masterHolidays;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getHolidays:" + e);
		}
	}
	
	/**
	 * Calculate holidays for an employee
	 * @param branchHoliDayFlag: Branch wise holiday work flow is enabled or not
	 * @param dailyAttnConnFlag: Daily attendance work flow is enabled or not
	 * @param fromDate: From which date holidays are calculated
	 * @param toDate: To which date holidays are calculated
	 * @param empId: Employee id whose holidays are calculated
	 * @param empBranch: Branch id whose holidays are calculated
	 * @return int as number of holidays
	 */
	private int calHolidays(boolean branchHoliDayFlag, boolean dailyConnected, String fromDate, String toDate, String empId, String empBranch, 
		String month, String year, String empJoinDate) {
		int numOfholidays = 0;
		try {
			String strMonth = Integer.parseInt(month) < 10 ? "0" + month : month;
			fromDate = "01-" + strMonth + "-" + year;
			fromDate = getFromDate(fromDate, empJoinDate);
			toDate = getMonthDays(strMonth, year) + "-" + strMonth + "-" + year;
			
			 // Create calendar object for fromDate and toDate
			Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
			Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
			
			getHolidays(dailyConnected, branchHoliDayFlag, empId, empBranch);
			
			if(holidays != null && holidays.size() > 0) {
				/**
				 * Get holidays either from daily attendance, branch wise holidays or holiday master. 
				 * If holiday is in between fromDate and toDate, then increment holidays by one
				 */
				for(int i = 0; i < holidays.size(); i++) {
					String holiday = holidays.get(i);
					
					//If holiday is in between fromDate and toDate, then increase number of holidays by one
					if(!holiday.equals("")) {
						// Create calendar object for holiday for comparison
						Calendar holidayCalendar = new Utility().getCalanderDate(holiday);
						if((holidayCalendar.equals(fromDateCalendar) || holidayCalendar.after(fromDateCalendar)) &&
						(holidayCalendar.equals(toDateCalendar) || holidayCalendar.before(toDateCalendar))) {
							numOfholidays++;
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in calHolidays:" + e);
		}
		return numOfholidays;
	}
	
	/**
	 * Calculate late marks for an employee
	 * @param uploadAttnConnFlag: Whether upload monthly attendance work flow is enabled or not
	 * @param dailyAttnConnFlag: Whether daily attendance work flow is enabled or not
	 * @param empId: Employee id whose late marks are to be calculated
	 * @return int as number of late marks
	 */
	private int calLateMarksOnDaysBasis(boolean dailyConnected, boolean uploadAttnConnFlag, String empId, boolean isLMEnabled, 
		boolean isAutoPresentLateMark, boolean isAutoPresentAbsent, String fromDate, String toDate) {
		int numOfLateMarks = 0;
		try {
			if(isLMEnabled && !(isAutoPresentLateMark || isAutoPresentAbsent)) {
				// Get late marks from uploaded attendance for an employee
				if(uploadAttnConnFlag) { // Upload monthly attendance workflow is enabled
					if(uploadAttendance != null && uploadAttendance.length > 0) {
						for(int i = 0; i < uploadAttendance.length; i++) {
							if(empId.equals(String.valueOf(uploadAttendance[i][0]))) {
								numOfLateMarks = Integer.parseInt(String.valueOf(uploadAttendance[i][1]));
								break;
							}
						}
					}
				}
				
				// Get late marks from daily attendance for an employee
				else if(dailyConnected) { // Daily attendance work flow is enabled
					if(lateMarksHalfDays != null && lateMarksHalfDays.length > 0) {
						// Create calendar object for fromDate and toDate
						Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
						Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
						
						for(int i = 0; i < lateMarksHalfDays.length; i++) {
							if(empId.equals(String.valueOf(lateMarksHalfDays[i][0]))) {
								Calendar attDateCalendar = new Utility().getCalanderDate(String.valueOf(lateMarksHalfDays[i][1]));
								
								if((attDateCalendar.equals(fromDateCalendar) || attDateCalendar.after(fromDateCalendar)) && 
										(attDateCalendar.equals(toDateCalendar) || attDateCalendar.before(toDateCalendar))) {
									numOfLateMarks += Integer.parseInt(String.valueOf(lateMarksHalfDays[i][2]));
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in calLateMarks:" + e);
		}
		return numOfLateMarks;
	}
	
	/**
	 * Calculate late marks for an employee
	 * @param empId: Employee id whose late marks are to be calculated
	 * @return int as hours of late marks
	 */
	private int calLateMarksOnHoursBasis(String empId, boolean isLMEnabled, boolean isAutoPresentLateMark, boolean isAutoPresentAbsent, 
		String fromDate, String toDate) {
		int numOfLateMarks = 0;
		try {
			if(isLMEnabled && !(isAutoPresentLateMark || isAutoPresentAbsent)) {
				if(lateMarksHalfDays != null && lateMarksHalfDays.length > 0) {
					// Create calendar object for fromDate and toDate
					Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
					Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
					
					for(int i = 0; i < lateMarksHalfDays.length; i++) {
						if(empId.equals(String.valueOf(lateMarksHalfDays[i][0]))) {
							Calendar attDateCalendar = new Utility().getCalanderDate(String.valueOf(lateMarksHalfDays[i][1]));
							
							if((attDateCalendar.equals(fromDateCalendar) || attDateCalendar.after(fromDateCalendar)) && 
									(attDateCalendar.equals(toDateCalendar) || attDateCalendar.before(toDateCalendar))) {
								numOfLateMarks += Integer.parseInt(String.valueOf(lateMarksHalfDays[i][4]));
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in calLateMarks:" + e);
		}
		return numOfLateMarks;
	}

	/**
	 * Calculate Paid Leaves. First, get paid leaves either from leave application or from uploaded leaves and 
	 * then calculate paid leaves by adjusting late marks and half days
	 * @param leaveConnFlag: Whether leave application work flow is enabled or not
	 * @param uploadAttnConnFlag: Whether upload monthly attendance work flow is enabled or not
	 * @param paidLeaveIds: Leave Ids of paid leave types
	 * @param empId: Employee id whose paid leaves are to be calculated
	 * @param numOfLateMarks: Total no. of late marks
	 * @param numOfHalfDays: Total no. of half days
	 * @return Object[][] as paid leave details including late marks and half days adjusted
	 */
	private Object[][] calPaidLeavesOnDaysBasis(boolean leaveConnFlag, boolean uploadAttnConnFlag, String empId, String leavePolicyId, 
		int numOfLateMarks, int numOfHalfDays, int lmCount, double lmAdjustLeaveDays, String lmAdjustLeaveTypes, String hdDeductLeaveTypes) {
		Object[][] paidLeaveDetails = null;
		try {
			// Get Paid Leave Ids from Leave Policy
			ArrayList<String> paidLeaveIds = getLeaveIdsFromPolicy(leavePolicyId, "P");
			
			Object[][] leaveDetails = null;
			
			// both leave application workflow and upload monthly attendance work flow are enabled
			if(leaveConnFlag && uploadAttnConnFlag) {
				leaveDetails = getFromLeaveApplicationAndUploadedLeaves(paidLeaveIds, empId);
			}
			
			// leave application work flow is enabled
			else if(leaveConnFlag) {
				// get paid leave details from leave applications
				leaveDetails = getPaidLeavesFromLeaveApplicationOnDaysBasis(paidLeaveIds, empId);
			}
			
			// upload monthly attendance work flow is enabled
			else if(uploadAttnConnFlag) {
				leaveDetails = getFromUploadedLeaves(paidLeaveIds, empId); // get paid leave details from uploaded leaves
			}
			
			// none of the workflow is enabled
			else {
				leaveDetails = getLeaveBalanceDetailsOnDaysBasis(paidLeaveIds, empId); // get only leave balances details
			}
			
			// adjust late marks and half days into leave balances
			paidLeaveDetails = adjustLateMarksHalfDaysOnDaysBasis(leaveDetails, numOfLateMarks, numOfHalfDays, lmCount, 
				lmAdjustLeaveDays, lmAdjustLeaveTypes, hdDeductLeaveTypes);
		} catch(Exception e) {
			logger.error("Exception in calPaidLeaves:" + e);
		}
		return paidLeaveDetails;
	}
	
	private Object[][] calPaidLeavesOnHoursBasis(boolean leaveConnFlag, String empId, String leavePolicyId, int numOfLateMarksMins, 
		int numOfHalfDays, String lmDeductNonRegLeaveTypes, String hdDeductLeaveTypes) {
		Object[][] paidLeavesMinsDetails = null;
		try {
			// Get Paid Leave Ids from Leave Policy
			ArrayList<String> paidLeaveIds = getLeaveIdsFromPolicy(leavePolicyId, "P");
			
			Object[][] leaveMinsDetails = null;
			
			// leave application work flow is enabled
			if(leaveConnFlag) {
				// get paid leave details from leave applications
				leaveMinsDetails = getPaidLeavesFromLeaveApplicationOnHoursBasis(paidLeaveIds, empId);
			}
			
			// none of the workflow is enabled
			else {
				leaveMinsDetails = getLeaveBalanceDetailsOnHoursBasis(paidLeaveIds, empId); // get only leave balances details
			}
			
			// adjust late amrks and half days into leave balances
			paidLeavesMinsDetails = adjustLateMarksHalfDaysOnHoursBasis(leaveMinsDetails, numOfLateMarksMins, numOfHalfDays, 
				lmDeductNonRegLeaveTypes, hdDeductLeaveTypes);
		} catch(Exception e) {
			logger.error("Exception in calPaidLeavesOnHoursBasis:" + e);
		}
		return paidLeavesMinsDetails;
	}
	
	private double calPenaltyAdjustedDays(String empId, boolean leaveConnFlag) {
		double penaltyAdjustedDays = 0.0;
		try {
			if(leaveConnFlag) {
				if(leavePenalties != null && leavePenalties.length > 0) {
					for(int i = 0; i < leavePenalties.length; i++) {
						if(empId.equals(String.valueOf(leavePenalties[i][0]))) {
							penaltyAdjustedDays = Double.parseDouble(String.valueOf(leavePenalties[i][1]));
							
							break;
						}
					}
				}
			}			
		} catch(Exception e) {
			logger.error("Exception in calPenaltyAdjustedDays:" + e);
		}
		return penaltyAdjustedDays;
	}
	
	private double calPenaltyUnAdjustedDays(String empId, boolean leaveConnFlag) {
		double penaltyUnAdjustedDays = 0.0;
		try {
			if(leaveConnFlag) {
				if(leavePenalties != null && leavePenalties.length > 0) {
					for(int i = 0; i < leavePenalties.length; i++) {
						if(empId.equals(String.valueOf(leavePenalties[i][0]))) {
							penaltyUnAdjustedDays = Double.parseDouble(String.valueOf(leavePenalties[i][2]));
							
							break;
						}
					}
				}
			}
			
		} catch(Exception e) {
			logger.error("Exception in calPenaltyAdjustedDays:" + e);
		}
		return penaltyUnAdjustedDays;
	}
	
	/**
	 * Calculate salary days, deduct total unpaid leaves from total attendance days
	 * @param totalAttendanceDays: Sum of the days between fromDate and toDate
	 * @return double as total salary days
	 */
	private double calSalaryDaysOnDaysBasis(double totalAttendanceDays, double totalUnpaidLeaves, double penaltyUnAdjustedDays) {
		double numOfSalaryDays = 0.0;
		try {
			numOfSalaryDays = totalAttendanceDays - (totalUnpaidLeaves + penaltyUnAdjustedDays + numOfUnauthUnAdjLeaves);
			
			if(numOfSalaryDays < 0) {
				numOfSalaryDays = 0;
			}
		} catch(Exception e) {
			logger.error("Exception in calSalaryDays:" + e);
		}
		return numOfSalaryDays;
	}

	private int calSalaryDaysOnHoursBasis(int totalAttendanceMins, int totalUnpaidLeavesMins, int penaltyUnAdjustedMins, 
		int numOfUnauthUnAdjLeavesMins) {
		int numOfSalaryMins = 0;
		try {
			numOfSalaryMins = totalAttendanceMins - (totalUnpaidLeavesMins + penaltyUnAdjustedMins + numOfUnauthUnAdjLeavesMins);
			
			if(numOfSalaryMins < 0) {
				numOfSalaryMins = 0;
			}
		} catch(Exception e) {
			logger.error("Exception in calSalaryDaysOnHoursBasis:" + e);
		}
		return numOfSalaryMins;
	}
	
	/**
	 * Calculate total attendance days, days between fromDate and toDate
	 * @param fromDate: From where attendance is calculated
	 * @param toDate: To where attendance is calculated
	 * @param month: Current month selected
	 * @param year: Current year selected
	 * @return int as total attendance days
	 */
	private int calTotalAttendanceDays(String month, String year, String empJoinDate) {
		int totalAttendanceDays = 0;
		try {
			String startDate = "01-" + month + "-" + year;
			String endDate = getMonthDays(month, year) + "-" + month + "-" + year;
			
			Calendar startDateCalendar = new Utility().getCalanderDate(startDate);
			Calendar endDateCalendar = new Utility().getCalanderDate(endDate);
			Calendar empJoinDateCalendar = new Utility().getCalanderDate(empJoinDate);
			
			if(empJoinDateCalendar.after(startDateCalendar)) {
				totalAttendanceDays = (int)((endDateCalendar.getTime().getTime() - empJoinDateCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24)) + 1;
			} else {
				totalAttendanceDays = getMonthDays(month, year); // get days of month
			}
			
			/*// Create calendar object for fromDate and toDate
			Calendar fromDateCalendar = new Utility().getCalanderDate(fromDate);
			Calendar toDateCalendar = new Utility().getCalanderDate(toDate);
			
			// calculate days between from date and to date including both dates
			int diffDays = (int)((toDateCalendar.getTime().getTime() - fromDateCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24)) + 1;
			
			// if days between from date and to date is greater than days of month, then attendance days would be days of month
			if(diffDays > monthDays) {
				totalAttendanceDays = monthDays;
			} else {
				totalAttendanceDays = diffDays;
			}*/
		} catch(Exception e) {
			logger.error("Exception in calTotalAttendanceDays:" + e);
		}
		return totalAttendanceDays;
	}
	
	private Object[][] calUnauthorisedLeaves(Object[][] leaveDetails, String empId, String leavePolicyId, boolean dailyConnected, 
		boolean isLMInHrsEnabled) {
		Object[][] attendanceDetails = null;
		try {
			if(leaveDetails != null && leaveDetails.length > 0) {
				// initialise attendance details
				attendanceDetails = new Object[leaveDetails.length][8];
				
				for(int i = 0; i < leaveDetails.length; i++) {
					for(int j = 0; j < leaveDetails[i].length; j++) {
						attendanceDetails[i][j] = leaveDetails[i][j];
					}
					attendanceDetails[i][7] = 0.0;
				}
				
				double leaveUnauthDays = 0.0;
				double leaveUnauthAbsents = 0.0;
				String[] leaveUnauthLeaveTypes = null;
				
				if(leavePolicies != null && leavePolicies.length > 0) {
					if(leaveUnauthFlag) {
						for(int i = 0; i < leavePolicies.length; i++) {
							if(leavePolicyId.equals(String.valueOf(leavePolicies[i][2]))) {
								leaveUnauthDays = Double.parseDouble(String.valueOf(leavePolicies[i][4]));
								leaveUnauthAbsents = Double.parseDouble(String.valueOf(leavePolicies[i][5]));
								leaveUnauthLeaveTypes = String.valueOf(leavePolicies[i][6]).split(",");
								
								break;
							}
						}
					}
				}
				
				if(dailyConnected) {
					double numOfUnauthAbsents = 0.0;
					
					if(leaveUnauthFlag) {
						if(numOfAbsents > 0) {
							numOfUnauthAbsents = (numOfAbsents * leaveUnauthDays) / leaveUnauthAbsents;
						}
						
						if(isLMInHrsEnabled) {
							attendanceDetails = adjustUnauthorisedLeavesOnHoursBasis(attendanceDetails, empId, leaveUnauthDays, leaveUnauthAbsents, 
								leaveUnauthLeaveTypes, numOfUnauthAbsents);
						} else {
							attendanceDetails = adjustUnauthorisedLeavesOnDaysBasis(attendanceDetails, empId, leaveUnauthDays, leaveUnauthAbsents, 
								leaveUnauthLeaveTypes, numOfUnauthAbsents);
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in calUnauthorisedLeavesOnDaysBasis:" + e);
		}
		return attendanceDetails;
	}
	
	/**
	 * Calculate unpaid leaves
	 * @param leaveConnFlag: Leave application work flow is enabled or not
	 * @param uploadAttnConnFlag: Upload monthly attendance work flow is enabled or not
	 * @param empId: Employee Id whose unpaid leaves are to be calculated
	 * @param unPaidLeaveIds: Leave Ids of unpaid leave type from leave policy
	 */
	private void calUnPaidLeavesOnDaysBasis(boolean isAutoPresentAbsent, boolean leaveConnFlag, boolean uploadAttnConnFlag, String empId, 
		String leavePolicyId) {
		try {
			if(!isAutoPresentAbsent) {
				// Get Unpaid Leave Ids from Leave Policy
				ArrayList<String> unPaidLeaveIds = getLeaveIdsFromPolicy(leavePolicyId, "U");
				
				double unpaidLeaves = 0.0;
				
				/**
				 * Both leave application workflow and upload monthly attendance work flow are enabled
				 */
				if(leaveConnFlag && uploadAttnConnFlag) {
					// get unpaid leaves from leave application
					double applicationUnpaidLeaves = getUnpaidLeavesFromLeaveApplication(unPaidLeaveIds, empId);
					
					// get unpaid leaves from uploaded unpaid leaves
					double uploadedUnpaidLeaves = getUploadedLeaves(empId, "U");
					
					// get Maximum no. of unpaid leaves
					unpaidLeaves = Math.max(applicationUnpaidLeaves, uploadedUnpaidLeaves);
				}
				
				/**
				 * Leave application work flow is enabled
				 */
				else if(leaveConnFlag) {
					// get paid leaves from approved leave applications
					unpaidLeaves = getUnpaidLeavesFromLeaveApplication(unPaidLeaveIds, empId);
				}
				
				/**
				 * Upload monthly attendance work flow is enabled
				 */
				else if (uploadAttnConnFlag) {
					// add uploaded unpaid leaves in total unpaid leaves
					unpaidLeaves = getUploadedLeaves(empId, "U");
				}
				
				if(numOfAbsents >= unpaidLeaves) {
					numOfUnPaidLeaves += numOfAbsents - unpaidLeaves;
				} else {
					numOfUnPaidLeaves += unpaidLeaves;
				}
			} else {
				numOfUnPaidLeaves = 0;
			}
		} catch(Exception e) {
			logger.error("Exception in calUnPaidLeaves:" + e);
		}
	}
	
	private void calUnPaidLeavesOnHoursBasis(boolean isAutoPresentAbsent, boolean leaveConnFlag, String empId, String leavePolicyId) {
		try {
			if(!isAutoPresentAbsent) {
				// Get Unpaid Leave Ids from Leave Policy
				ArrayList<String> unPaidLeaveIds = getLeaveIdsFromPolicy(leavePolicyId, "U");
				
				/**
				 * Leave application work flow is enabled
				 */
				double unpaidLeaves = 0.0;
				if(leaveConnFlag) {
					// get paid leaves from approved leave applications
					unpaidLeaves = getUnpaidLeavesFromLeaveApplication(unPaidLeaveIds, empId);
				}
				
				if(numOfAbsents >= unpaidLeaves) {
					numOfUnPaidLeaves += numOfAbsents - unpaidLeaves;
				} else {
					numOfUnPaidLeaves += unpaidLeaves;
				}
			} else {
				numOfUnPaidLeaves = 0;
			}
		} catch(Exception e) {
			logger.error("Exception in calUnPaidLeavesOnHoursBasis:" + e);
		}
	}

	private ArrayList<String> getWeekOffDaysFromShift(String empId, String empBranch, boolean dailyConnected, boolean branchHoliDayFlag, 
		String fromDate, String toDate, String woDaysForWeek1, String woDaysForWeek2, String woDaysForWeek3, String woDaysForWeek4, 
		String woDaysForWeek5, String woDaysForWeek6) {
		ArrayList<String> weekOffDays = new ArrayList<String>();
		try {
			String fromDateString[] = fromDate.split("-");
			int fromDay = Integer.parseInt(fromDateString[0]);
			int fromMonth = Integer.parseInt(fromDateString[1]);
			int fromYear = Integer.parseInt(fromDateString[2]);
			
			Date dateFrom = new Date(fromYear - 1900, fromMonth - 1, fromDay - 1);
			Calendar fromCal = Calendar.getInstance();
			fromCal.setTime(dateFrom);
			
			String toDateString[] = toDate.split("-");
			int toDay = Integer.parseInt(toDateString[0]);
			int toMonth = Integer.parseInt(toDateString[1]);
			int toYear = Integer.parseInt(toDateString[2]);
			
			Date dateTo = new Date(toYear - 1900, toMonth - 1, toDay - 1);
			Calendar toCal = Calendar.getInstance();
			toCal.setTime(dateTo);
			
			ArrayList<String> totalWeekOffDays = new ArrayList<String>();
			
			while(fromCal.before(toCal) || fromCal.equals(toCal)) {
				fromCal.add(Calendar.DATE, 1);
				
				String day = fromCal.get(Calendar.DATE) < 10 ? "0" + fromCal.get(Calendar.DATE) : String.valueOf(fromCal.get(Calendar.DATE));
				String month = fromCal.get(Calendar.MONTH) < 10 ? "0" + (fromCal.get(Calendar.MONTH) + 1) : String.valueOf(fromCal.get(Calendar.MONTH) + 1);
				String year = String.valueOf(fromCal.get(Calendar.YEAR));
				String date = day + "-" + month + "-" + year;
				
				int weekOfMonth = fromCal.get(Calendar.WEEK_OF_MONTH);
				String[] weeklyOffDays = null;
				switch(weekOfMonth) {
					case 1 :
						if(woDaysForWeek1.length() > 0) {
							if(woDaysForWeek1.length() > 1) {
								weeklyOffDays = woDaysForWeek1.split(",");
							} else if(!(woDaysForWeek1.equals("null") || woDaysForWeek1.equals("") || woDaysForWeek1 == null)) {
								weeklyOffDays = new String[1];
								weeklyOffDays[0] = woDaysForWeek1;
							}
						}
						break;
					case 2 :
						if(woDaysForWeek2.length() > 0) {
							if(woDaysForWeek2.length() > 1) {
								weeklyOffDays = woDaysForWeek2.split(",");
							} else if(!(woDaysForWeek2.equals("null") || woDaysForWeek2.equals("") || woDaysForWeek2 == null)) {
								weeklyOffDays = new String[1];
								weeklyOffDays[0] = woDaysForWeek2;
							}
						}
						break;
					case 3 :
						if(woDaysForWeek3.length() > 0) {
							if(woDaysForWeek3.length() > 1) {
								weeklyOffDays = woDaysForWeek3.split(",");
							} else if(!(woDaysForWeek3.equals("null") || woDaysForWeek3.equals("") || woDaysForWeek3 == null)) {
								weeklyOffDays = new String[1];
								weeklyOffDays[0] = woDaysForWeek3;
							}
						}
						break;
					case 4 :
						if(woDaysForWeek4.length() > 0) {
							if(woDaysForWeek4.length() > 1) {
								weeklyOffDays = woDaysForWeek4.split(",");
							} else if(!(woDaysForWeek4.equals("null") || woDaysForWeek4.equals("") || woDaysForWeek4 == null)) {
								weeklyOffDays = new String[1];
								weeklyOffDays[0] = woDaysForWeek4;
							}
						}
						break;
					case 5 :
						if(woDaysForWeek5.length() > 0) {
							if(woDaysForWeek5.length() > 1) {
								weeklyOffDays = woDaysForWeek5.split(",");
							} else if(!(woDaysForWeek5.equals("null") || woDaysForWeek5.equals("") || woDaysForWeek5 == null)) {
								weeklyOffDays = new String[1];
								weeklyOffDays[0] = woDaysForWeek5;
							}
						}
						break;
					case 6 :
						if(woDaysForWeek6.length() > 0) {
							if(woDaysForWeek6.length() > 1) {
								weeklyOffDays = woDaysForWeek6.split(",");
							} else if(!(woDaysForWeek6.equals("null") || woDaysForWeek6.equals("") || woDaysForWeek6 == null)) {
								weeklyOffDays = new String[1];
								weeklyOffDays[0] = woDaysForWeek6;
							}
						}
						break;
					default : break;
				}
				
				if(weeklyOffDays != null && weeklyOffDays.length > 0) {
					for(int i = 0; i < weeklyOffDays.length; i++) {
						if(!(weeklyOffDays[i].equals("null") || weeklyOffDays[i].equals("") || weeklyOffDays[i] == null)) {
							int weekDay = Integer.parseInt(weeklyOffDays[i]);
							int dayOfWeek = fromCal.get(Calendar.DAY_OF_WEEK);
							
							if(weekDay == dayOfWeek) {
								totalWeekOffDays.add(date);
								break;
							}
						}
					}
				}
			}
			
			if(totalWeekOffDays != null && totalWeekOffDays.size() > 0) {
				for(int i = 0; i < totalWeekOffDays.size(); i++) {
					String woDay = totalWeekOffDays.get(i);
					boolean isWoDayIsHoliday = false;
					
					if(holidays != null && holidays.size() > 0) {
						for(int j = 0; j < holidays.size(); j++) {
							String holiday = holidays.get(j);
							if(woDay.equals(holiday)) {
								isWoDayIsHoliday = true;
								break;
							}
						}						
					}
					
					if(!isWoDayIsHoliday) {
						weekOffDays.add(woDay);
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getWeekOffDaysFromShift:" + e);
		}
		return weekOffDays;
	}
	
	/**
	 * Calculate weekly offs for an employee
	 * @param dailyConnected: Whether daily attendance work flow is enabled or not
	 * @param fromDate: From which date weekly offs are calculated
	 * @param toDate: To which date weekly offs are calculated
	 * @param empId: Employee id whose weekly offs are to be calculated
	 * @param empShift: From which shift weekly offs are taken
	 * @return int as number of weekly offs
	 */
	private int calWeeklyOff(boolean dailyConnected, String fromDate, String toDate, String empId, boolean isWOFixedEnabled, 
		boolean branchHoliDayFlag, String empBranch, String woDaysForWeek1, String woDaysForWeek2, String woDaysForWeek3, String woDaysForWeek4, 
		String woDaysForWeek5, String woDaysForWeek6, String month, String year, String empJoinDate) {
		int numOfWeekOffDays = 0;
		try {
			/**
			 * Get weekly offs from shift
			 */
			String strMonth = Integer.parseInt(month) < 10 ? "0" + month : month;
			fromDate = "01-" + strMonth + "-" + year;
			fromDate = getFromDate(fromDate, empJoinDate);
			toDate = getMonthDays(strMonth, year) + "-" + strMonth + "-" + year;
			
			ArrayList<String> weeklyOffsFromShift = getWeekOffDaysFromShift(empId, empBranch, dailyConnected, branchHoliDayFlag, fromDate, toDate, 
				woDaysForWeek1, woDaysForWeek2, woDaysForWeek3, woDaysForWeek4, woDaysForWeek5, woDaysForWeek6);
			
			if(weeklyOffsFromShift.size() > 0) {
				if(dailyConnected) {
					/**
					 * Get weekly offs from daily attendance
					 */
					if(weeklyOffsFromDailyAttendance != null && weeklyOffsFromDailyAttendance.length > 0) {
						ArrayList<String> attendanceWeekOffs = new ArrayList<String>(8);
						
						for(int i = 0; i < weeklyOffsFromDailyAttendance.length; i++) {
							String empIdFormDailyAttendance = String.valueOf(weeklyOffsFromDailyAttendance[i][0]);
							String woFormDailyAttendance = String.valueOf(weeklyOffsFromDailyAttendance[i][1]);
							
							if(empId.equals(empIdFormDailyAttendance)) {
								attendanceWeekOffs.add(woFormDailyAttendance);
							}
						}
						
						ArrayList<String> totalAttendanceWeekOffs = new ArrayList<String>();
						
						// copy weekly offs from daily attendance
						totalAttendanceWeekOffs = attendanceWeekOffs;
						
						if(attendanceWeekOffs.size() > 0) {
							for(int i = 0; i < weeklyOffsFromShift.size(); i++) {
								String woFromShift = weeklyOffsFromShift.get(i);
								boolean isWoExists = false;
								
								for(int j = 0; j < attendanceWeekOffs.size(); j++) {
									String woFromAttendance = attendanceWeekOffs.get(j);
									if(woFromShift.equals(woFromAttendance)) {
										isWoExists = true;
										break;
									}
								}
								
								if(!isWoExists) {
									totalAttendanceWeekOffs.add(woFromShift);
									extraWeeklyOffs++;
								}
							}
						}
						numOfWeekOffDays = totalAttendanceWeekOffs.size();
					}
				} else {
					numOfWeekOffDays = weeklyOffsFromShift.size();
				}
			}
		} catch(Exception e) {
			logger.error("Exception in calWeeklyOff:" + e);
		}
		return numOfWeekOffDays;
	}
	
	/**
	 * Get attendance settings data
	 * @return Object[][] as attendance settings data
	 */
	public Object[][] getAttendanceSettings() {
		return attendanceSettings;
	}
	
	/**
	 * Calculate the attendance for each and every employee
	 * @param employees: Contains the information of all employees
	 * @param fromDate: Date from which attendance is to be calculated
	 * @param toDate: Date up to which attendance is to be calculated
	 * @param month: Month for which attendance is to be calculated
	 * @param year: Year for which attendance is to be calculated
	 * @param branchHoliDayFlag: Branch wise holiday work flow is enabled or not
	 * @param leaveConnFlag: Leave Connection work flow is enabled or not
	 * @param dailyAttnConnFlag: Daily Attendance work flow is enabled or not
	 * @param uploadAttnConnFlag: Upload Monthly Attendance work flow is enabled or not
	 * @param leavePolicyData: Set leave policy information
	 * @return Object[][] as calculated attendance of all the employees
	 */
	public Object[][] getCalculatedAttendance(Object[][] employees, String fromDate, String toDate, String month, String year, 
		boolean branchHoliDayFlag, boolean leaveConnFlag, boolean dailyAttnConnFlag, boolean uploadAttnConnFlag, LeavePolicyData leavePolicyData) {
		try {
			Object[][] calculatedAttendnace = {};
			
			if(employees != null && employees.length > 0) {
				for(int i = 0; i < employees.length; i++) {
					String newFromDate = "";
					boolean dailyConnected = false;
					
					numOfPaidLeaves = 0.0; numOfUnPaidLeaves = 0.0; numOfPaidLateMarks = 0.0; numOfPaidHalfDays = 0.0; numOfUnPaidLateMarks = 0.0; 
					numOfUnPaidHalfDays = 0.0; numOfPaidLateMarkMins = 0; numOfUnPaidLateMarkMins = 0; numOfUnauthAdjLeaves = 0.0; 
					numOfUnauthUnAdjLeaves = 0.0; numOfAbsents = 0; extraHolidays = 0; extraWeeklyOffs = 0; workingHours = 0.0; 
					leaveUnauthFlag = false; holidays = null;
					
					// Get employee information
					String empId = String.valueOf(employees[i][0]);
					String empDiv = String.valueOf(employees[i][1]);
					String empDept = String.valueOf(employees[i][2]);
					String empDesg = String.valueOf(employees[i][3]);
					String empBranch = String.valueOf(employees[i][4]);
					String empType = String.valueOf(employees[i][5]);
					String empJoinDate = String.valueOf(employees[i][6]);
					
					boolean isAutoPresentLateMark = Boolean.parseBoolean(String.valueOf(employees[i][7]));
					boolean isAutoPresentHalfDay = Boolean.parseBoolean(String.valueOf(employees[i][8]));
					boolean isAutoPresentAbsent = Boolean.parseBoolean(String.valueOf(employees[i][9]));
					
					boolean isLMEnabled = Boolean.parseBoolean(String.valueOf(employees[i][10]));
					boolean isLMInNoEnabled = Boolean.parseBoolean(String.valueOf(employees[i][11]));
					int lmCount = Integer.parseInt(String.valueOf(employees[i][12]));
					double lmAdjustLeaveDays = Double.parseDouble(String.valueOf(employees[i][13]));
					String lmAdjustLeaveTypes = String.valueOf(employees[i][14]);
					
					boolean isLMInHrsEnabled = Boolean.parseBoolean(String.valueOf(employees[i][15]));
					String lmDeductNonRegLeaveTypes = String.valueOf(employees[i][16]);
					
					boolean isHDEnabled = Boolean.parseBoolean(String.valueOf(employees[i][17]));
					String hdDeductLeaveTypes = String.valueOf(employees[i][18]);
					
					boolean isWOFixedEnabled = Boolean.parseBoolean(String.valueOf(employees[i][19]));
					String woDaysForWeek1 = String.valueOf(employees[i][20]);
					String woDaysForWeek2 = String.valueOf(employees[i][21]);
					String woDaysForWeek3 = String.valueOf(employees[i][22]);
					String woDaysForWeek4 = String.valueOf(employees[i][23]);
					String woDaysForWeek5 = String.valueOf(employees[i][24]);
					String woDaysForWeek6 = String.valueOf(employees[i][25]);
					
					workingHours = Double.parseDouble(String.valueOf(employees[i][26]));
					
					// Get fromDate by comparing joinDate
					newFromDate = getFromDate(fromDate, empJoinDate);
					
					// Check whether daily attendance is connected or not
					//dailyConnected = isDailyAttendanceConnected(dailyAttnConnFlag, empId);
					dailyConnected = dailyAttnConnFlag;
					
					// get leave policy id
					String leavePolicyId = leavePolicyData.getLeavePolicyCode(empId, empDiv, empDept, empDesg, empBranch, empType);
					if(leavePolicies != null && leavePolicies.length > 0) {
						for(int j = 0; j < leavePolicies.length; j++) {
							if(leavePolicyId.equals(String.valueOf(leavePolicies[j][2]))) {
								leaveUnauthFlag = Boolean.parseBoolean(String.valueOf(leavePolicies[j][3]));
								
								break;
							}
						}
					}
					
					
					/**
					 * Calculate the attendance on the basis of two factors - on number of days and on hours basis. This 
					 * calculation methodology depends on a single factor i.e. 'isLMInHrsEnabled'. If this true, then calculate 
					 * attendance on hours basis, otherwise calculate attendnace on days basis.
					 */
					Object[][] attendance = null;
					
					// calculate attendance on hours basis
					if(isLMInHrsEnabled) {
						attendance = calculateAttendanceOnHoursBasis(empId, empBranch, leavePolicyId, empJoinDate, branchHoliDayFlag, leaveConnFlag, 
							dailyConnected, fromDate, newFromDate, toDate, month, year, isAutoPresentLateMark, isAutoPresentHalfDay, 
							isAutoPresentAbsent, isLMEnabled, lmDeductNonRegLeaveTypes, isHDEnabled, hdDeductLeaveTypes, isWOFixedEnabled, 
							woDaysForWeek1, woDaysForWeek2, woDaysForWeek3, woDaysForWeek4, woDaysForWeek5, woDaysForWeek6);
					}
					
					// calculate attendance on days basis
					else {
						attendance = calculateAttendanceOnDaysBasis(empId, empBranch, leavePolicyId, empJoinDate, branchHoliDayFlag, leaveConnFlag, 
							dailyConnected, uploadAttnConnFlag, fromDate, newFromDate, toDate, month, year, isAutoPresentLateMark, 
							isAutoPresentHalfDay, isAutoPresentAbsent, isLMEnabled, lmCount, lmAdjustLeaveDays, lmAdjustLeaveTypes, isHDEnabled, 
							hdDeductLeaveTypes, isWOFixedEnabled, woDaysForWeek1, woDaysForWeek2, woDaysForWeek3, woDaysForWeek4, woDaysForWeek5, 
							woDaysForWeek6);
					}
					
					// Combine both object by column wise (1) started by first record (0) of second object
					calculatedAttendnace = Utility.joinArrays(calculatedAttendnace, attendance, 1, 0);
				}
			}
			return calculatedAttendnace;
		} catch(Exception e) {
			logger.error("Exception in getCalculatedAttendance:" + e);
			return null;
		}		
	}
	
	private double getDays(int minutes) {
		double days = 0.0;
		try {
			days = (minutes / 60.0) / workingHours;
		} catch(Exception e) {
			logger.error("Exception in getDays:" + e);
		}
		return days;
	}
	
	public String getDaysAndRemainingMins(int minutes) {
		String daysAndRemainingMins = "0.0";
		try {
			double days = Math.floor((minutes / 60) / workingHours);
			int daysInMins = (int) (days * workingHours * 60);
			int remainingMins = minutes - daysInMins;
			
			daysAndRemainingMins = days + "," + remainingMins;
		} catch(Exception e) {
			logger.error("Exception in getDays:" + e);
		}
		return daysAndRemainingMins;
	}
	
	/**
	 * Get default from date from which attendance get calculated by comparing default from date and employee joining date and 
	 * returns that date which is greater
	 * @param fromDate: Default from date
	 * @param empJoinDate: Joining date of an employee
	 * @return String as updated default from date
	 */
	private String getFromDate(String fromDate, String empJoinDate) {
		try {
			/**
			 * Create calendar object for joining date and default from date
			 */
			Calendar fromCalendar = Calendar.getInstance();
			fromCalendar.setTime(Utility.getDate(fromDate));
			
			Calendar joinCalendar = Calendar.getInstance();
			joinCalendar.setTime(Utility.getDate(empJoinDate));
			
			/**
			 * If joining date is greater than default from date, then set default from date as joining date by returning it.
			 */
			if(joinCalendar.after(fromCalendar)) {
				return empJoinDate;
			} else {
				return fromDate;
			}
		} catch (Exception e) {
			logger.error("Exception in getFromDate:" + e);
			return fromDate;
		}
	}
	
	/**
	 * Get paid leave details by comparing approved leaves and uploaded leaves, if both work flows,leave application and uploaded 
	 * attendance, are enabled. If approved leaves
	 * @param paidLeaveIds: Paid leaves Ids from leave policy
	 * @param empId: Employee id whose paid leave details are to be calculated
	 * @return Object[][] as leave details by comparing the leaves from application and uploaded attendance
	 */
	private Object[][] getFromLeaveApplicationAndUploadedLeaves(ArrayList<String> paidLeaveIds, String empId) {
		Object[][] leaveDetails = null;
		try {
			// get leaves details from leave application
			Object[][] applicationLeaveDetails = getPaidLeavesFromLeaveApplicationOnDaysBasis(paidLeaveIds, empId);
			
			if(applicationLeaveDetails != null && applicationLeaveDetails.length > 0) {
				double applicationLeaves = 0.0;
				
				// get total approved leaves from leave application
				for(int i = 0; i < applicationLeaveDetails.length; i++) {
					applicationLeaves += Double.parseDouble(String.valueOf(applicationLeaveDetails[i][3]));
				}
				
				// get paid leaves from uploaded attendance
				double numOfUploadPaidLeaves = getUploadedLeaves(empId, "P"); // get paid leaves from uploaded attendance
				
				// if no. of uploaded leaves are more than approved leaves, then uploaded leaves, after deducting approved leaves, 
				// are to be adjusted in the leave balances
				if(numOfUploadPaidLeaves > applicationLeaves) {
					Object[][] extraUploadedLeaveDetails = new Object[applicationLeaveDetails.length][7];
					
					// get uploaded leaves after deducting approved leaves
					double extraUploadedLeaves = numOfUploadPaidLeaves - applicationLeaves;
					
					for(int i = 0; i < extraUploadedLeaveDetails.length; i++) {
						extraUploadedLeaveDetails[i][0] = empId;
						extraUploadedLeaveDetails[i][1] = applicationLeaveDetails[i][1]; // leaveId
						extraUploadedLeaveDetails[i][2] = applicationLeaveDetails[i][2]; // openingBalance
						extraUploadedLeaveDetails[i][3] = applicationLeaveDetails[i][3]; // approvedLeaves						
						extraUploadedLeaveDetails[i][4] = applicationLeaveDetails[i][4]; // lateMarkAdjusted
						extraUploadedLeaveDetails[i][5] = applicationLeaveDetails[i][5]; // halfDaysAdjusted
						extraUploadedLeaveDetails[i][6] = applicationLeaveDetails[i][6]; // closingBalance
					}
					
					// adjust remaining uploaded leaves
					leaveDetails = adjustUploadedLeaves(empId, extraUploadedLeaveDetails, extraUploadedLeaves);
				}
				
				// if no. of uploaded leaves are less than or equal to approved leaves, then consider approved leaves only
				else {
					leaveDetails = applicationLeaveDetails;
				}
			}
			
			// if leave details are not available, then adjust paid leaves from uploaded leaves
			else {
				// get leave details from uploaded attendance
				leaveDetails = getFromUploadedLeaves(paidLeaveIds, empId);
			}
		} catch(Exception e) {
			logger.error("Exception in getFromLeaveApplicationAndUploadedLeaves:" + e);
		}
		return leaveDetails;
	}

	/**
	 * Get paid leave details from uploaded leaves, adjust those paid leave as per the leave Ids specified in Attendance Setting 
	 * against balances, remaining paid leaves are to be converted into unpaid leaves
	 * @param paidLeaveIds: Leave Ids of paid leave types
	 * @param empId: Employee id whose paid leave details are to be calculated
	 * @return Object[][] as leave details
	 */
	private Object[][] getFromUploadedLeaves(ArrayList<String> paidLeaveIds, String empId) {
		Object[][] uploadedLeaves = null;
		try {
			// Get paid leaves from uploaded attendance
			double numOfUploadPaidLeaves = getUploadedLeaves(empId, "P");
			
			// get leave balances for paid leave Ids
			Object[][] paidLeaveBalances = getLeaveBalances(paidLeaveIds, empId);
			
			uploadedLeaves = adjustUploadedLeaves(empId, paidLeaveBalances, numOfUploadPaidLeaves);
		} catch(Exception e) {
			logger.error("Exception in getFromUploadedLeaves:" + e);
		}
		return uploadedLeaves;
	}
	
	public String getHours(int minutes) {
		String hours = "";
		try {
			int hrs = (int) Math.floor(minutes / 60);
			int mins = (int) Math.floor(minutes % 60);
			
			hours = Integer.parseInt(String.valueOf(hrs)) < 10 ? "0" + String.valueOf(hrs) : String.valueOf(hrs);
			hours += ":" + (Integer.parseInt(String.valueOf(mins)) < 10 ? "0" +  String.valueOf(minutes % 60) : 
				String.valueOf(minutes % 60));
		} catch(Exception e) {
			logger.error("Exception in getHours:" + e);
		}
		return hours;
	}
	
	/**
	 * Get only leave balances details
	 * @param paidLeaveIds: Leave Ids of paid leave types
	 * @param empId: Employee id whose leave details are to be taken from leave balances
	 * @return Object[][] as blank leave details including opening and closing leaves balances
	 */
	private Object[][] getLeaveBalanceDetailsOnDaysBasis(ArrayList<String> paidLeaveIds, String empId) {
		Object[][] leaveDetails = null;
		try {
			int numOfRows = 0;
			
			if(leavesAndBalances != null && leavesAndBalances.length > 0) {
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						if(paidLeaveIds.size() > 0) {
							for(int j = 0; j < paidLeaveIds.size(); j++) {
								String leaveId = paidLeaveIds.get(j); // leave Id from leave policy
								
								// leave code matches with paid leave code in policy
								if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
									numOfRows++; // Increment only when paid leave are to be calculated
									break;
								}
							}
						}
					}
				}
			}
			
			/**
			 * If records exist, then only get paid leaves details
			 */
			if(numOfRows > 0) {
				int rowNum = 0;
				leaveDetails = new Object[numOfRows][7];
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						for(int j = 0; j < paidLeaveIds.size(); j++) {
							String leaveId = paidLeaveIds.get(j); // leave Id from leave policy
							
							// leave code matches with paid leave code in policy
							if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
								// get leave details
								double openingBalance = Double.parseDouble(String.valueOf(leavesAndBalances[i][2]));
								double approvedLeaves = 0.0;
								double lateMarkAdjusted = 0.0;
								double halfDaysAdjusted = 0.0;
								double closingBalance = Double.parseDouble(String.valueOf(leavesAndBalances[i][4]));
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								leaveDetails[rowNum][0] = empId;
								leaveDetails[rowNum][1] = leaveId;
								leaveDetails[rowNum][2] = openingBalance;
								leaveDetails[rowNum][3] = approvedLeaves;
								leaveDetails[rowNum][4] = lateMarkAdjusted;
								leaveDetails[rowNum][5] = halfDaysAdjusted;
								leaveDetails[rowNum][6] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
								
								rowNum++;

								break;
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getBlankLeaveDetails:" + e);
		}
		return leaveDetails;
	}
	
	private Object[][] getLeaveBalanceDetailsOnHoursBasis(ArrayList<String> paidLeaveIds, String empId) {
		Object[][] leaveMinsDetails = null;
		try {
			int numOfRows = 0;
			
			if(leavesAndBalances != null && leavesAndBalances.length > 0) {
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						if(paidLeaveIds.size() > 0) {
							for(int j = 0; j < paidLeaveIds.size(); j++) {
								String leaveId = paidLeaveIds.get(j); // leave Id from leave policy
								
								// leave code matches with paid leave code in policy
								if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
									numOfRows++; // Increment only when paid leave are to be calculated
									break;
								}
							}
						}
					}
				}
			}
			
			/**
			 * If records exist, then only get paid leaves details
			 */
			if(numOfRows > 0) {
				int rowNum = 0;
				leaveMinsDetails = new Object[numOfRows][7];
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						for(int j = 0; j < paidLeaveIds.size(); j++) {
							String leaveId = paidLeaveIds.get(j); // leave Id from leave policy
							
							// leave code matches with paid leave code in policy
							if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
								// get leave details
								int openingBalanceMins = Integer.parseInt(String.valueOf(leavesAndBalances[i][5]));
								int approvedLeavesMins = 0;
								int lateMarkMinsAdjusted = 0;
								int halfDaysAdjusted = 0;
								int closingBalanceMins = Integer.parseInt(String.valueOf(leavesAndBalances[i][6]));
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								leaveMinsDetails[rowNum][0] = empId;
								leaveMinsDetails[rowNum][1] = leaveId;
								leaveMinsDetails[rowNum][2] = openingBalanceMins;
								leaveMinsDetails[rowNum][3] = approvedLeavesMins;
								leaveMinsDetails[rowNum][4] = lateMarkMinsAdjusted;
								leaveMinsDetails[rowNum][5] = halfDaysAdjusted;
								leaveMinsDetails[rowNum][6] = closingBalanceMins;
								
								rowNum++;

								break;
							}
						}
					}
				}
			}
		
		} catch(Exception e) {
			logger.error("Exception in getLeaveBalanceDetailsOnHoursBasis:" + e);
		}
		return leaveMinsDetails;
	}
	
	/**
	 * Get leave balances for paid leaves
	 * @param paidLeaveIds: Leave Ids of paid leave types
	 * @param empId: Employee id whose paid leave balances are to be calculated
	 * @return Object[][] as paid leave balances
	 */
	private Object[][] getLeaveBalances(ArrayList<String> paidLeaveIds, String empId) {
		Object[][] leaveBalances = null;
		try {
			if(leavesAndBalances != null && leavesAndBalances.length > 0) {
				int numOfRows = 0;
				for(int i = 0; i < leavesAndBalances.length; i++) {
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						for(int j = 0; j < paidLeaveIds.size(); j++) {
							// paid leave code matches with leave code in leave balances
							if(paidLeaveIds.get(j).equals(String.valueOf(leavesAndBalances[i][1]))) {
								numOfRows++;
								break;
							}
						}
					}
				}
				
				/**
				 * If records exist, then only get paid leaves balance details
				 */
				if(numOfRows > 0) {
					int rowNum = 0;
					leaveBalances = new Object[numOfRows][7];
					
					for(int i = 0; i < leavesAndBalances.length; i++) {
						if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
							for(int j = 0; j < paidLeaveIds.size(); j++) {
								// paid leave code matches with leave code in leave balances
								if(paidLeaveIds.get(j).equals(String.valueOf(leavesAndBalances[i][1]))) {
									leaveBalances[rowNum][0] = empId;
									leaveBalances[rowNum][1] = String.valueOf(leavesAndBalances[i][1]); // LEAVE ID
									leaveBalances[rowNum][2] = String.valueOf(leavesAndBalances[i][2]); // OPENING BALANCE
									leaveBalances[rowNum][3] = "0.0"; // adjustUploadPaidLeaves
									leaveBalances[rowNum][4] = "0.0"; // lateMarksAdjusted
									leaveBalances[rowNum][5] = "0.0"; // halfDaysAdjusted
									leaveBalances[rowNum][6] = String.valueOf(leavesAndBalances[i][4]); // CLOSING BALANCE
									
									rowNum++;
									
									break;
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getLeaveBalances:" + e);
		}
		return leaveBalances;
	}
	
	/**
	 * Get paid/unpaid leave id from leave policy
	 * @param leavePolicyId: Leave policy id
	 * @param leaveType: P (Paid) or U (Unpaid)
	 * @return ArrayList<String> as paid/unpaid leave Ids
	 */
	public ArrayList<String> getLeaveIdsFromPolicy(String leavePolicyId, String leaveType) {
		ArrayList<String> leaveIdsFromPolicy = new ArrayList<String>();
		try {
			if(leavePolicies != null && leavePolicies.length > 0) {
				for(int i = 0; i < leavePolicies.length; i++) {
					// leave policy id for an employee matches with policy ids in leavePolicies and leave type in leavePolicies matches with 
					// leaveType (P/U), then get leave id
					if(leavePolicyId.equals(String.valueOf(leavePolicies[i][2])) 
							&& leaveType.equals(String.valueOf(leavePolicies[i][1]))) {
						leaveIdsFromPolicy.add(String.valueOf(leavePolicies[i][0])); // added leave code
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getLeaveIdsFromPolicy:" + e);
		}
		return leaveIdsFromPolicy;
	}
	
	/**
	 * Getter for leavePolicies object
	 * @return Object[][] as leavePolicies
	 */
	public Object[][] getLeavePolicies() {
		return leavePolicies;
	}
	
	/**
	 * Get data of leaves and balances
	 * @return Object[][] as leaves and balances data
	 */
	public Object[][] getLeavesAndBalances() {
		return leavesAndBalances;
	}

	public int getMinutes(double days) {
		int minutes = 0;
		try {
			minutes = (int) (days * workingHours * 60);
		} catch(Exception e) {
			logger.error("Exception in getMinutes:" + e);
		}
		return minutes;
	}
	
	/**
	 * Get days of month
	 * @param month: Current Month
	 * @param year: Current Year
	 * @return int as days of month
	 */
	private int getMonthDays(String month, String year) {
		int monthDays = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			cal.setTime(Utility.getDate("01-" + month + "-" + year));
			monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			logger.error("Exception in getMonthDays:" + e);
		}
		return monthDays;
	}
	
	/**
	 * Get a converted number in divisible by 5 format. e.g. 1.6 = 1.5, 1.5 = 1.5, 1.3 = 1.0
	 * @param number: No. to convert
	 * @return double as new no. in converted format
	 */
	private double getNumberInRound(double number) {
		double roundedBalance = 0.0;
		try {
			// spilt the number
			String[] str = String.valueOf(number).replace(".", "#").split("#");
			
			// get one digit after decimal point
			double digitAfterDecimal = Double.parseDouble(String.valueOf(str[1].substring(0, 1)));
			
			/**
			 * If digit after decimal point is greator than or equal to 5, then return that digit as 5 only. Otherwise return a 
			 * digit which is before decimal point
			 */
			if(digitAfterDecimal >= 5) {
				roundedBalance = Double.parseDouble(str[0] + ".5"); // 1.6 = 1.5, 1.5 = 1.5
			} else {
				roundedBalance = Math.round(number); // e.g. 1.3 = 1.0
			}
		} catch(Exception e) {
			logger.error("Exception in getBalanceInRound:" + e);
		}
		return roundedBalance;
	}
	
	/**
	 * Get not adjusted half days
	 * @return double as non adjusted half days
	 */
	public double getNumOfUnPaidHalfDays() {
		return numOfUnPaidHalfDays;
	}
	
	/**
	 * @return the numOfUnPaidLateMarkMins
	 */
	public int getNumOfUnPaidLateMarkMins() {
		return numOfUnPaidLateMarkMins;
	}
	
	/**
	 * Get not adjusted late marks
	 * @return double as non adjusted late marks
	 */
	public double getNumOfUnPaidLateMarks() {
		return numOfUnPaidLateMarks;
	}
	
	/**
	 * Get paid leave details from leave applications
	 * @param policyLeaveIds: Leave Ids of paid/unpaid leave types
	 * @param empId: Employee id whose paid/unpaid leave details are to be calculated
	 * @return Object[][] as leave details
	 */
	private Object[][] getPaidLeavesFromLeaveApplicationOnDaysBasis(ArrayList<String> policyLeaveIds, String empId) {
		Object[][] leaveDetails = null;
		try {
			int numOfRows = 0;
			
			if(leavesAndBalances != null && leavesAndBalances.length > 0) {
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						if(policyLeaveIds.size() > 0) {
							for(int j = 0; j < policyLeaveIds.size(); j++) {
								String leaveId = policyLeaveIds.get(j); // leave Id from leave policy
								
								// leave code matches with paid leave code in policy
								if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
									// get approved paid leaves
									numOfPaidLeaves += Double.parseDouble(String.valueOf(leavesAndBalances[i][3]));
									
									// Increment only when paid leave are to be calculated
									numOfRows++;
									
									break;
								}
							}
						}
					}
				}
			}
			
			/**
			 * If records exist, then only get paid leaves details
			 */
			if(numOfRows > 0) {
				int rowNum = 0;
				leaveDetails = new Object[numOfRows][7];
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						for(int j = 0; j < policyLeaveIds.size(); j++) {
							String leaveId = policyLeaveIds.get(j); // leave Id from leave policy
							
							// leave code matches with paid leave code in policy
							if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
								// get leave details
								double openingBalance = Double.parseDouble(String.valueOf(leavesAndBalances[i][2]));
								double approvedLeaves = Double.parseDouble(String.valueOf(leavesAndBalances[i][3]));
								double lateMarkAdjusted = 0.0;
								double halfDaysAdjusted = 0.0;
								double closingBalance = Double.parseDouble(String.valueOf(leavesAndBalances[i][4]));
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								leaveDetails[rowNum][0] = empId;
								leaveDetails[rowNum][1] = leaveId;
								leaveDetails[rowNum][2] = openingBalance;
								leaveDetails[rowNum][3] = approvedLeaves;
								leaveDetails[rowNum][4] = lateMarkAdjusted;
								leaveDetails[rowNum][5] = halfDaysAdjusted;
								leaveDetails[rowNum][6] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
								
								rowNum++;

								break;
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getFromLeaveApplication:" + e);
		}
		return leaveDetails;
	}
	
	private Object[][] getPaidLeavesFromLeaveApplicationOnHoursBasis(ArrayList<String> policyLeaveIds, String empId) {
		Object[][] leaveMinsDetails = null;
		try {
			int numOfRows = 0;
			
			if(leavesAndBalances != null && leavesAndBalances.length > 0) {
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						if(policyLeaveIds.size() > 0) {
							for(int j = 0; j < policyLeaveIds.size(); j++) {
								String leaveId = policyLeaveIds.get(j); // leave Id from leave policy
								
								// leave code matches with paid leave code in policy
								if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
									// get approved paid leaves
									numOfPaidLeaves += Double.parseDouble(String.valueOf(leavesAndBalances[i][3]));
									
									// Increment only when paid leave are to be calculated
									numOfRows++;
									
									break;
								}
							}
						}
					}
				}
			}
			
			/**
			 * If records exist, then only get paid leaves details
			 */
			if(numOfRows > 0) {
				int rowNum = 0;
				leaveMinsDetails = new Object[numOfRows][7];
				for(int i = 0; i < leavesAndBalances.length; i++) {					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						for(int j = 0; j < policyLeaveIds.size(); j++) {
							String leaveId = policyLeaveIds.get(j); // leave Id from leave policy
							
							// leave code matches with paid leave code in policy
							if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
								// get leave details
								int openingBalanceMins = Integer.parseInt(String.valueOf(leavesAndBalances[i][5]));
								double approvedLeaves = Double.parseDouble(String.valueOf(leavesAndBalances[i][3]));
								int lateMarkMinsAdjusted = 0;
								int halfDaysAdjusted = 0;
								int closingBalanceMins = Integer.parseInt(String.valueOf(leavesAndBalances[i][6]));
								
								// create an object which is used to save data in HRMS_MONTH_ATT_DTL_<year> table
								leaveMinsDetails[rowNum][0] = empId;
								leaveMinsDetails[rowNum][1] = leaveId;
								leaveMinsDetails[rowNum][2] = openingBalanceMins;
								leaveMinsDetails[rowNum][3] = approvedLeaves;
								leaveMinsDetails[rowNum][4] = lateMarkMinsAdjusted;
								leaveMinsDetails[rowNum][5] = halfDaysAdjusted;
								leaveMinsDetails[rowNum][6] = closingBalanceMins;
								
								rowNum++;

								break;
							}
						}
					}
				}
			}
		
		} catch(Exception e) {
			logger.error("Exception in getPaidLeavesFromLeaveApplicationOnHoursBasis:" + e);
		}
		return leaveMinsDetails;
	}
	
	private Object[][] getRevisedAttendanceDetails(Object[][] calAttendanceDetails, boolean isLMInHrsEnabled) {
		Object[][] attendanceDetails = null;
		try {
			if(calAttendanceDetails != null && calAttendanceDetails.length > 0) {
				attendanceDetails = new Object[calAttendanceDetails.length][11];
				
				for(int i = 0; i < calAttendanceDetails.length; i++) {
					attendanceDetails[i][0] = calAttendanceDetails[i][0]; // ATT_EMP_CODE
					attendanceDetails[i][1] = calAttendanceDetails[i][1]; // ATT_LEAVE_CODE
					
					/**
					 * Calculate opening balance
					 */
					// calculation is on hours basis
					if(isLMInHrsEnabled) {
						// calculate total minutes
						int openingBalanceMins = Integer.parseInt(String.valueOf(calAttendanceDetails[i][2]));
						
						// divide total minutes into days and remaining minutes
						String[] openingBalDaysAndMins = getDaysAndRemainingMins(openingBalanceMins).split(",");
						
						// get days from minutes
						attendanceDetails[i][2] = openingBalDaysAndMins[0]; // ATT_LEAVE_AVAILABLE
						
						// calculate hours from remaining minutes
						attendanceDetails[i][3] = getHours(Integer.parseInt(openingBalDaysAndMins[1])); // ATT_LEAVE_AVAILABLE_HRS
					}
					
					// calculation is on days basis
					else {
						attendanceDetails[i][2] = calAttendanceDetails[i][2]; // ATT_LEAVE_AVAILABLE
						attendanceDetails[i][3] = "00:00"; // ATT_LEAVE_AVAILABLE_HRS
					}
					
					/**
					 * Calculate leaves adjusted
					 */
					attendanceDetails[i][4] = calAttendanceDetails[i][3]; // ATT_LEAVE_ADJUST
					
					/**
					 * Calculate late mark adjusted
					 */
					// calculation is on hours basis
					if(isLMInHrsEnabled) {
						// calculate total minutes
						int lateMarkMinsAdjusted = Integer.parseInt(String.valueOf(calAttendanceDetails[i][4]));
						
						// divide total minutes into days and remaining minutes
						String[] lateMarkDaysAndMins = getDaysAndRemainingMins(lateMarkMinsAdjusted).split(",");
						
						// get days from minutes
						attendanceDetails[i][5] = lateMarkDaysAndMins[0]; // ATT_LATEMARK_ADJUST
						
						// calculate hours from remaining minutes
						attendanceDetails[i][6] = getHours(Integer.parseInt(lateMarkDaysAndMins[1])); // ATT_LATEMARK_ADJUST_HRS
					}
					
					// calculation is on days basis
					else {
						attendanceDetails[i][5] = calAttendanceDetails[i][4]; // ATT_LATEMARK_ADJUST
						attendanceDetails[i][6] = "00:00"; // ATT_LATEMARK_ADJUST_HRS
					}
					
					/**
					 * Calculate half day adjusted
					 */
					attendanceDetails[i][7] = calAttendanceDetails[i][5]; // ATT_HALFDAY_ADJUST
					
					/**
					 * Calculate closing balance
					 */
					// calculation is on hours basis
					if(isLMInHrsEnabled) {
						// calculate total minutes
						int closingBalanceMins = Integer.parseInt(String.valueOf(calAttendanceDetails[i][6]));
						
						// divide total minutes into days and remaining minutes
						String[] closingBalDaysAndMins = getDaysAndRemainingMins(closingBalanceMins).split(",");
												
						// get days from minutes
						attendanceDetails[i][8] = closingBalDaysAndMins[0]; // ATT_LEAVE_BALANCE
						
						// calculate hours from remaining minutes
						attendanceDetails[i][9] = getHours(Integer.parseInt(closingBalDaysAndMins[1])); // ATT_LEAVE_BALANCE_HRS
					}
					
					// calculation is on days basis
					else {
						attendanceDetails[i][8] = calAttendanceDetails[i][6]; // ATT_LEAVE_BALANCE
						attendanceDetails[i][9] = "00:00"; // ATT_LEAVE_BALANCE_HRS
					}
					
					/**
					 * Calculate unauthorised adjusted leaves
					 */
					attendanceDetails[i][10] = calAttendanceDetails[i][7]; // ATT_UNAUTHORISED_ADJUST
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getRevisedAttendanceDetails:" + e);
		}
		return attendanceDetails;
	}
	
	private void getUnauthorisedAbsents(String empId, boolean dailyConnected) {
		try {
			if(dailyConnected) {
				/**
				 * Get total absent days from available daily attendance
				 */
				double availableAbsents = 0;
				if(unauthorisedLeaves != null && unauthorisedLeaves.length > 0) {
					for(int i = 0; i < unauthorisedLeaves.length; i++) {
						if(empId.equals(String.valueOf(unauthorisedLeaves[i][0]))) {
							availableAbsents = Double.parseDouble(String.valueOf(unauthorisedLeaves[i][1]));
							break;
						}
					}
				}
				
				/**
				 * Get absent days from not available daily attendance
				 */
				double notAvailableAbsents = 0;
				if(dailyAttendanceNotExist != null && dailyAttendanceNotExist.length > 0) {
					for(int i = 0; i < dailyAttendanceNotExist.length; i++) {
						if(empId.equals(String.valueOf(dailyAttendanceNotExist[i][0]))) {
							notAvailableAbsents = Double.parseDouble(String.valueOf(dailyAttendanceNotExist[i][1]));
							break;
						}
					}
				}
				
				/**
				 * Deduct holidays and weekly offs from not available daily attendance
				 */
				if(notAvailableAbsents > 0) {
					notAvailableAbsents -= extraHolidays + extraWeeklyOffs;
				}
				
				/**
				 * Calculate total absents as absents from avaiable daily attendance + not avaible daily attendance
				 */
				double totalAbsents = availableAbsents + notAvailableAbsents;
				
				/**
				 * Calculate exact absents
				 */
				if(totalAbsents > 0) {
					/**
					 * Get approved leaves
					 */
					double approvedLeaves = 0.0;					
					if(leaveDays != null && leaveDays.length > 0) {
						for(int i = 0; i < leaveDays.length; i++) {
							if(empId.equals(String.valueOf(leaveDays[i][0]))) {
								approvedLeaves = Double.parseDouble(String.valueOf(leaveDays[i][1]));
								break;
							}
						}
					}
					
					/**
					 * Deduct approved leaves from total absents
					 */
					numOfAbsents = (int) (totalAbsents - approvedLeaves);
				}
			}			
		} catch(Exception e) {
			logger.error("Exception in getUnauthorisedAbsents:" + e);
		}
	}
	
	/**
	 * Get no. of unpaid leaves from leave applications
	 * @param policyLeaveIds: Leave Ids of paid/unpaid leave types
	 * @param empId: Employee id whose paid/unpaid leave details are to be calculated
	 * @return Object[][] as leave details
	 */
	private double getUnpaidLeavesFromLeaveApplication(ArrayList<String> policyLeaveIds, String empId) {
		double unpaidLeaves = 0.0;
		try {
			if(leavesAndBalances != null && leavesAndBalances.length > 0) {
				for(int i = 0; i < leavesAndBalances.length; i++) {
					
					// employee id matches with employee id in approvedLeaves object
					if(empId.equals(String.valueOf(leavesAndBalances[i][0]))) {
						if(policyLeaveIds.size() > 0) {
							for(int j = 0; j < policyLeaveIds.size(); j++) {
								String leaveId = policyLeaveIds.get(j); // leave Id from leave policy
								
								// leave code matches with paid leave code in policy
								if(leaveId.equals(String.valueOf(leavesAndBalances[i][1]))) {
									// get approved unpaid leaves
									unpaidLeaves += Double.parseDouble(String.valueOf(leavesAndBalances[i][3]));
									break;
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getUnpaidLeavesFromLeaveApplication:" + e);
		}
		return unpaidLeaves;
	}
	
	/**
	 * Get no. of paid/unpaid leaves from uploaded attendance
	 * @param empId: Employee ID whose paid/unpaid leaves are to calculated
	 * @param leaveType: P(Paid), U(Unpaid)
	 * @return double as no. of uploaded leaves
	 */
	private double getUploadedLeaves(String empId, String leaveType) {
		double numOfUploadLeaves = 0.0;
		try {
			if(uploadAttendance != null && uploadAttendance.length > 0) {
				for(int j = 0; j < uploadAttendance.length; j++) {
					// employee id matches with employee in uploaded attendance
					if(empId.equals(String.valueOf(uploadAttendance[j][0]))) {
						if(leaveType.equals("P")) {
							// get total no. of uploaded paid leaves
							numOfUploadLeaves = Double.parseDouble(String.valueOf(uploadAttendance[j][3]));
						} else {
							// get total no. of uploaded unpaid leaves
							numOfUploadLeaves = Double.parseDouble(String.valueOf(uploadAttendance[j][4]));
						}
						break;
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getUploadedLeaves:" + e);
		}
		return numOfUploadLeaves;
	}
	
	/**
	 * Get no. of recoveryDays from uploaded attendance
	 * @param empId: Employee ID whose recoveryDays are to calculated
	 * @return double as no. of uploaded Recovery Days
	 */
	private double getUploadedRecoveryDays(String empId) {
		double numOfUploadRecoveryDays = 0.0;
		try {
			if(uploadAttendance != null && uploadAttendance.length > 0) {
				for(int j = 0; j < uploadAttendance.length; j++) {
					// employee id matches with employee in uploaded attendance
					if(empId.equals(String.valueOf(uploadAttendance[j][0]))) {
						
							numOfUploadRecoveryDays = Double.parseDouble(String.valueOf(uploadAttendance[j][5]));
							
						break;
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getUploadedRecoveryDays:" + e);
		}
		return numOfUploadRecoveryDays;
	}
	
	/**
	 * @return the workingHours
	 */
	public double getWorkingHours() {
		return workingHours;
	}
	
	/**
	 * Check whether attendance is already processed for the filters selected
	 * @param month: Month selected used as a filter to get already processed attendance
	 * @param year: Year selected used as a filter to get already processed attendance
	 * @param listOfFilters: Filters selected to get already processed attendance
	 * @return boolean as whether attendance is already processed or not
	 */
	public boolean isAttendanceProcessed(String month, String year, String[] listOfFilters) {
		boolean isAttendanceProcessed = false;
		try {
			String attendanceExistSql = " SELECT * FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year;
			attendanceExistSql = setSalaryLedgerFiletrs(listOfFilters, attendanceExistSql);
			Object[][] attendanceExistObj = getSqlModel().getSingleResult(attendanceExistSql);
			
			if(attendanceExistObj != null && attendanceExistObj.length > 0) {
				isAttendanceProcessed = true;
			}
		} catch(Exception e) {
			logger.error("Exception in isAttendanceProcessed:" + e);
		}
		return isAttendanceProcessed;
	}
	
	/**
	 * Check whether daily attendance is connected or not by calculating total daily attendance days. If days are greator than 0, 
	 * than daily attendance is connected in case of daily attendance is connected.
	 * @param dailyAttnConnFlag: Daily Attendance work flow is enabled or not
	 * @param empId: Employee whose daily attendance is to be calculated
	 * @return boolean as daily attendance is connected or not
	 */
	private boolean isDailyAttendanceConnected(boolean dailyAttnConnFlag, String empId) {
		boolean dailyConnected = false;
		try {
			if(dailyAttnConnFlag) { // Daily attendance workflow is enabled
				if(dailyAttendanceNotExist != null && dailyAttendanceNotExist.length > 0) {
					for(int i = 0; i < dailyAttendanceNotExist.length; i++) {
						
						// if empId matches, then daily attendance is connected
						if(empId.equals(String.valueOf(dailyAttendanceNotExist[i][0]))) {
							dailyConnected = true;
							break;
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in isDailyAttendanceConnected:" + e);
		}
		return dailyConnected;
	}
	
	/**
	 * Check whether table is created or not at backend for the year selected
	 * @param year: Year selected
	 * @return boolean as table is created or not
	 */
	private boolean isTableExists(String year) {
		boolean isTableExists = false;
		try {
			String tableExistsSql = " SELECT * FROM HRMS_MONTH_ATTENDANCE_" + year;
			getSqlModel().getSingleResult(tableExistsSql);
			isTableExists = true;
		} catch(Exception e) {
			logger.error("Exception in isTableExists:" + e);
		}
		return isTableExists;
	}
	
	/**
	 * Load attendance details from back end
	 * @param concatEmployeeIds: Employee Ids separated by comma
	 * @param leavePolicyData: Through leavePolicyData get policy code
	 * @param branchHoliDayFlag: Branch wise holiday work flow is enabled or not
	 * @param dailyAttnConnFlag: Daily attendance work flow is enabled or not
	 * @param uploadAttnConnFlag: Upload monthly attendance work flow is enabled or not
	 * @param fromDate: Date from which attendance details are to be loaded
	 * @param toDate: Date to which attendance details are to be loaded
	 * @param month: Month for which attendance details are to be loaded
	 * @param year: Year for which attendance details are to be loaded
	 * @param salaryStartMonth: Salary Start Month (Current/Previous) for which attendance details are to be loaded
	 */
	public void loadAttendanceDetails(String concatEmployeeIds, LeavePolicyData leavePolicyData, boolean branchHoliDayFlag, 
		boolean dailyAttnConnFlag, boolean uploadAttnConnFlag, String fromDate, String toDate, String month, String year, String salaryStartMonth,String defaultDaysFlag) {
		try {
			uploadAttendance = loadUploadAttendanceData(concatEmployeeIds, month, year, uploadAttnConnFlag,defaultDaysFlag);
			holidaysFromMaster = loadHolidaysFromMaster(branchHoliDayFlag, fromDate, toDate, month, year);
			
			holidaysFromDailyAttendance = loadHolidaysFromDailyAttendance(concatEmployeeIds, dailyAttnConnFlag, branchHoliDayFlag, fromDate, toDate, 
				month, year, salaryStartMonth);
			dailyAttendanceNotExist = loadDailyAttendanceNotExists(concatEmployeeIds, dailyAttnConnFlag, month, salaryStartMonth, year, fromDate, 
				toDate);
			
			weeklyOffsFromDailyAttendance = loadWeeklyOffsFromDailyAttendance(concatEmployeeIds, dailyAttnConnFlag, fromDate, toDate, month, year, 
				salaryStartMonth);
			lateMarksHalfDays = loadLateMarksHalfDays(concatEmployeeIds, dailyAttnConnFlag, fromDate, toDate, month, year, salaryStartMonth);
			
			leavePolicies = leavePolicyData.getLeavePolicyObj();
			setLeavePolicies(leavePolicies);
			
			leavesAndBalances = loadLeavesAndBalances(concatEmployeeIds, fromDate, toDate, month, year);
			setLeavesAndBalances(leavesAndBalances);
			
			leavePenalties = loadLeavePenalties(month, year, concatEmployeeIds);
			unauthorisedLeaves = loadUnauthorisedLeaves(dailyAttnConnFlag, month, year, salaryStartMonth, fromDate, toDate, 
				concatEmployeeIds);
			leaveDays = loadLeaveDaysExcludingHalfDays(fromDate, toDate, concatEmployeeIds, month, year);
		} catch(Exception e) {
			logger.error("Exception in loadAttendanceDetails:" + e);
		}
	}
	
	/**
	 * Load Attendance Settings
	 */
	public void loadAttendanceSettings() {
		try {
			String attendanceSettingsSql = " SELECT DECODE(CONF_LEAVE_CONNECT_FLAG, 'Y', 'true', 'N', 'false') AS LEAVE_CONN_FLAG, " +  
			" DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false') AS BRANCH_HOLIDAY_FLAG, " +  
			" DECODE(CONF_DAILY_ATT_CONNECT_FLAG, 'Y', 'true', 'N', 'false') AS DAILY_ATTN_CONN_FLAG, " +
			" CONF_SALARY_START_DATE AS SALARY_START_DATE, CONF_SALARY_START_FLAG AS SALARY_START_MONTH, " +
			" CONF_COMP_OFF AS COMP_OFF_LEAVE_TYPES, " +
			" DECODE(CONF_UPLOAD_MONTH_ATT_FLAG, 'Y', 'true', 'N', 'false') AS UPLOAD_MONTH_ATTN_FLAG, " +
			" CONF_PAIDLEAVES_LEAVEADJUST, NVL(CONF_DEFAULT_DAYS_FLAG,'A') FROM HRMS_ATTENDANCE_CONF ";
			attendanceSettings = getSqlModel().getSingleResult(attendanceSettingsSql);
			
			setAttendanceSettings(attendanceSettings);
		} catch(Exception e) {
			logger.error("Exception in loadAttendanceSettings:" + e);
		}
	}
	
	private Object[][] loadDailyAttendanceNotExists(String concatEmployeeIds, boolean dailyAttnConnFlag, String month, String salaryStartMonth, 
		String year, String fromDate, String toDate) {
		Object[][] dailyAttendanceObj = null;
		try {
			String dailyAttendanceSql = "";
			
			/**
			 * Daily attendance work flow is enabled
			 */
			if(dailyAttnConnFlag) {
				// If current month is Jan and salary start month is previous, then get holidays from both previous year and current year tables
				if(Integer.parseInt(month) == 1 && salaryStartMonth.equals("P")) {
					dailyAttendanceSql = " SELECT ATT_EMP_ID, " +
					" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 " +
					" ELSE((TO_DATE('" + toDate + "', 'DD-MM-YYYY') - (CASE WHEN EMP_REGULAR_DATE > TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " +
					" THEN EMP_REGULAR_DATE ELSE TO_DATE('" + fromDate + "', 'DD-MM-YYYY') END)) + 1) - COUNT(*) END AS ATTENDANCE_DAYS_NOT_EXIST " +
					" FROM ( " +
					" 	SELECT ATT_EMP_ID, ATT_DATE, AUTO_PRESENT_ABSENT, EMP_REGULAR_DATE " +
					"	FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					"	LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + ".ATT_EMP_ID) " +
					" 	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + ".ATT_EMP_ID) " +
					"	UNION ALL " +
					"	SELECT ATT_EMP_ID, ATT_DATE, AUTO_PRESENT_ABSENT, EMP_REGULAR_DATE " +
					"	FROM HRMS_DAILY_ATTENDANCE_" + year +
					"	LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					" 	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					" ) " +
					" WHERE ATT_DATE BETWEEN (CASE WHEN EMP_REGULAR_DATE > TO_DATE('" + fromDate + "', 'DD-MM-YYYY') THEN EMP_REGULAR_DATE " +
					" ELSE TO_DATE('" + fromDate + "', 'DD-MM-YYYY') END) AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" GROUP BY ATT_EMP_ID, AUTO_PRESENT_ABSENT, EMP_REGULAR_DATE ORDER BY ATT_EMP_ID ";
				} else {
					dailyAttendanceSql = " SELECT ATT_EMP_ID, " +
					" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 " +
					" ELSE((TO_DATE('" + toDate + "', 'DD-MM-YYYY') - (CASE WHEN EMP_REGULAR_DATE > TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " +
					" THEN EMP_REGULAR_DATE ELSE TO_DATE('" + fromDate + "', 'DD-MM-YYYY') END)) + 1) - COUNT(*) END AS ATTENDANCE_DAYS_NOT_EXIST " + 
					" FROM HRMS_DAILY_ATTENDANCE_" + year +
					" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					" WHERE ATT_DATE BETWEEN (CASE WHEN EMP_REGULAR_DATE > TO_DATE('" + fromDate + "', 'DD-MM-YYYY') THEN EMP_REGULAR_DATE " +
					" ELSE TO_DATE('" + fromDate + "', 'DD-MM-YYYY') END) AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" GROUP BY ATT_EMP_ID, AUTO_PRESENT_ABSENT, EMP_REGULAR_DATE ORDER BY ATT_EMP_ID ";
				}
			}
			dailyAttendanceObj = getSqlModel().getSingleResult(dailyAttendanceSql);
		} catch(Exception e) {
			logger.error("Exception in loadDailyAttendance:" + e);
		}
		return dailyAttendanceObj;
	}
	
	/**
	 * Get employees information, like employee id, branch, shift and joining date, who are in service and who are join on or 
	 * before toDate
	 * @param toDate: To which date employees are join
	 * @param listOfFilters: Contains list of filters selected from a page
	 * @return Object[][] as list of employees
	 */
	private Object[][] loadEmployeeList(String toDate, String[] listOfFilters) {
		Object[][] employeeObj = null;
		try {
			String employeeSql = " SELECT EMP_ID, EMP_DIV, EMP_DEPT, EMP_RANK, EMP_CENTER, EMP_TYPE, " +
			" TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY') AS EMP_JOIN_DATE, " +
			" NVL(DECODE(AUTO_PRESENT_LATE_MARK, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_LATE_MARK, " +
			" NVL(DECODE(AUTO_PRESENT_HALF_DAY, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_HALF_DAY, " +
			" NVL(DECODE(AUTO_PRESENT_ABSENT, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_ABSENT, " +
			" NVL(DECODE(SFT_LM_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_ISENABLED, " +
			" NVL(DECODE(SFT_LM_NUMBER_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_NUMBER_ISENABLED, " +
			" NVL(SFT_ADJUST_LM_COUNT, 0) AS SFT_ADJUST_LM_COUNT, NVL(SFT_ADJUST_LM_LEVDAYS, 0) AS SFT_ADJUST_LM_LEVDAYS, " +
			" NVL(SFT_ADJUST_LM_LEVTYPE, '') AS SFT_ADJUST_LM_LEVTYPE, " +
			" NVL(DECODE(SFT_LM_HRS_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_HRS_ISENABLED, " +
			" NVL(SFT_DED_NONREGL_LM_LEVTYPE, '') AS SFT_DED_NONREGL_LM_LEVTYPE, " +
			" NVL(DECODE(SFT_HD_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_HD_ISENABLED, " +
			" NVL(SFT_DED_HD_LEVTYPE, '') AS SFT_DED_HD_LEVTYPE, " +
			" NVL(DECODE(SFT_FIXED_WO_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_FIXED_WO_ISENABLED, " +
			" NVL(SHIFT_WEEK_1, '') AS SHIFT_WEEK_1, NVL(SHIFT_WEEK_2, '') AS SHIFT_WEEK_2, NVL(SHIFT_WEEK_3, '') AS SHIFT_WEEK_3, " +
			" NVL(SHIFT_WEEK_4, '') AS SHIFT_WEEK_4, NVL(SHIFT_WEEK_5, '') AS SHIFT_WEEK_5, NVL(SHIFT_WEEK_6, '') AS SHIFT_WEEK_6, " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
			" (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) AS SFT_WORK_HRS " +
			" FROM HRMS_EMP_OFFC " +
			" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
			" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
			" WHERE EMP_STATUS = 'S' AND EMP_REGULAR_DATE <= (CASE WHEN (SELECT CONF_EMP_JOIN_BEFORE FROM HRMS_ATTENDANCE_CONF) IS NULL " +
			" THEN TO_DATE('" + toDate + "', 'DD-MM-YYYY') ELSE TO_DATE(TO_CHAR((SELECT CONF_EMP_JOIN_BEFORE FROM HRMS_ATTENDANCE_CONF)) || '-' || " +
			" TO_CHAR(TO_DATE('" + toDate + "', 'DD-MM-YYYY'), 'MM-YYYY'), 'DD-MM-YYYY') END) ";
			employeeSql = setEmployeeOffciceFiletrs(listOfFilters, employeeSql); // set the filters
			employeeSql += " ORDER BY EMP_ID ";
			employeeObj = getSqlModel().getSingleResult(employeeSql);
		} catch(Exception e) {
			logger.error("Exception in getEmployeeList:" + e);
		}
		return employeeObj;
	}
	
	/**
	 * Load holidays from back end
	 * @param concatEmployeeIds: Employee Ids separated by comma
	 * @param dailyAttnConnFlag: Daily attendance work flow is enabled or not
	 * @param branchHoliDayFlag: Branch wise holiday work flow is enabled or not
	 * @param fromDate: Date from which holidays are to be loaded
	 * @param toDate: Date to which holidays are to be loaded
	 * @param month: Month for which holidays are to be loaded
	 * @param year: Year for which holidays are to be loaded
	 * @param salaryStartMonth: Salary Start Month (Current/Previous) for which attendance details are to be loaded
	 * @return Object[][] as holidays
	 */
	private Object[][] loadHolidaysFromDailyAttendance(String concatEmployeeIds, boolean dailyAttnConnFlag, boolean branchHoliDayFlag, 
		String fromDate, String toDate, String month, String year, String salaryStartMonth) {
		Object[][] holidayFromDailyAttendanceObj = null;
		try {
			String holidayFromDailyAttendanceSql = "";
			
			fromDate = "01-" + month + "-" + year;
			toDate = getMonthDays(month, year) + "-" + month + "-" + year;
			
			// Daily attendance work flow is enabled
			if(dailyAttnConnFlag) {
				// If current month is Jan and salary start month is previous, then get holidays from both previous year and current year tables
				if(Integer.parseInt(month) == 1 && salaryStartMonth.equals("P")) {
					holidayFromDailyAttendanceSql = " SELECT EMP_ID, HOLIDAY FROM  ( " +
					"	SELECT ATT_EMP_ID AS EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS HOLIDAY " +
					"	FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + " WHERE ATT_STATUS_ONE = 'HO' " +
					"	UNION ALL " +
					"	SELECT ATT_EMP_ID AS EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS HOLIDAY " +
					"	FROM HRMS_DAILY_ATTENDANCE_" + year + " WHERE ATT_STATUS_ONE = 'HO' " +
					" ) " +
					" WHERE TO_DATE(HOLIDAY, 'DD-MM-YYYY') BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " +					
					" AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " + 
					Utility.getConcatenatedIds("EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" ORDER BY EMP_ID, HOLIDAY ";
				}
				
				// get holidays from current year table
				else {
					holidayFromDailyAttendanceSql = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS HOLIDAY FROM HRMS_DAILY_ATTENDANCE_" + year + 
					" WHERE ATT_STATUS_ONE = 'HO' AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " +
					" AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " + 
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" ORDER BY ATT_EMP_ID, ATT_DATE ";
				}
			}
			holidayFromDailyAttendanceObj = getSqlModel().getSingleResult(holidayFromDailyAttendanceSql);
		} catch(Exception e) {
			logger.error("Exception in loadHolidays:" + e);
		}
		return holidayFromDailyAttendanceObj;
	}
	
	private Object[][] loadHolidaysFromMaster(boolean branchHoliDayFlag, String fromDate, String toDate, String month, String year) {
		Object[][] holidayFromMasterObj = null;
		try {
			String holidayFromMasterSql = "";
			
			fromDate = "01-" + month + "-" + year;
			toDate = getMonthDays(month, year) + "-" + month + "-" + year;
			
			// Branch wise holiday work flow is enabled
			if(branchHoliDayFlag) {
				holidayFromMasterSql = " SELECT CENTER_ID, TO_CHAR(HOLI_DATE, 'DD-MM-YYYY') AS HOLIDAY FROM HRMS_HOLIDAY_BRANCH " + 
				" WHERE HOLI_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
				" ORDER BY CENTER_ID, HOLI_DATE ";
			}
			
			// get holidays from master
			else {
				holidayFromMasterSql = " SELECT '', TO_CHAR(HOLI_DATE, 'DD-MM-YYYY') AS HOLIDAY FROM HRMS_HOLIDAY " +
				" WHERE HOLI_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
				" ORDER BY HOLI_DATE ";
			}			
			holidayFromMasterObj = getSqlModel().getSingleResult(holidayFromMasterSql);
		} catch (Exception e) {
			logger.error("Exception in loadHolidaysFromMaster:" + e);
		}
		return holidayFromMasterObj;
	}
	
	/**
	 * Load late marks and half days from back end
	 * @param concatEmployeeIds: Employee Ids separated by comma
	 * @param dailyAttnConnFlag: Daily attendance work flow is enabled or not
	 * @param fromDate: Date from which late marks and half days are to be loaded
	 * @param toDate: Date to which late marks and half days are to be loaded
	 * @param month: Month for which late marks and half days are to be loaded
	 * @param year: Year for which late marks and half days are to be loaded
	 * @param salaryStartMonth: Salary Start Month (Current/Previous) for which attendance details are to be loaded
	 * @return Object[][] as late marks and half days
	 */
	private Object[][] loadLateMarksHalfDays(String concatEmployeeIds, boolean dailyAttnConnFlag, String fromDate, String toDate, 
			String month, String year, String salaryStartMonth) {
		Object[][] lateMarksHalfDaysObj = null;
		try {
			String lateMarksHalfDaysSql = "";
			if(dailyAttnConnFlag) { // Daily attendance work flow is enabled
				// If current month is Jan and salary start month is previous, then get late marks and half days from both previous year and 
				// current year tables
				if(Integer.parseInt(month) == 1 && salaryStartMonth.equals("P")) {
					/*lateMarksHalfDaysSql = " SELECT ATT_EMP_ID, SUM(NVL(LATE_MARKS, 0)) AS LATE_MARKS, " +
					" SUM(NVL(HALF_DAYS, 0)) AS HALF_DAYS, CEIL(SUM(NVL(UNAUTHORIZED_LATE_MINS, 0))) AS UNAUTHORIZED_LATE_MINS " +
					" FROM ( " +
					" 	SELECT ATT_EMP_ID, ATT_DATE, " +
					// calculate late marks
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN 2 " +
					"	WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN('LC', 'EL') THEN 1 ELSE 0 END AS LATE_MARKS, " +
					// calculate half days
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' THEN 1 ELSE 0 END + " +
					"	CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN ATT_REG_STATUS_ONE = 'PR' AND " +
					"	ATT_REG_STATUS_TWO = 'HD' THEN 1 ELSE 0 END) ELSE 0 END AS HALF_DAYS, " +
					// calculate unauthorised late marks in minutes
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') AND " +
					"	ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') AND ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') AND " +
					"	ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP') " +
					"	THEN CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF ELSE 0 END + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF  AS UNAUTHORIZED_LATE_MINS " +
					
					" 	FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					" 	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + ".ATT_EMP_ID) " +
					" 	INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
					" 	WHERE ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY " +
					" 	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) " +
					" 	GROUP BY ATT_STATUS_ONE, SFT_LM_HRS_ISENABLED, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO, ATT_STATUS_TWO, ATT_EMP_ID, ATT_DATE, " +
					"	ATT_REG_STATUS_LATE, ATT_REG_STATUS_SWIPE, ATT_REG_LC_HRS, ATT_REG_STATUS_PERTIME, ATT_LATE_HRS, ATT_EARLY_HRS, " +
					"	SFT_DED_LM_IN_SLABS_OF, ATT_REG_EL_HRS " +
					
					" 	UNION ALL " +
					
					" 	SELECT ATT_EMP_ID, ATT_DATE, " +
					// calculate late marks
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN 2 " +
					"	WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN('LC', 'EL') THEN 1 ELSE 0 END AS LATE_MARKS, " +
					// calculate half days
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' THEN 1 ELSE 0 END + " +
					"	CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN ATT_REG_STATUS_ONE = 'PR' AND " +
					"	ATT_REG_STATUS_TWO = 'HD' THEN 1 ELSE 0 END) ELSE 0 END AS HALF_DAYS, " +
					// calculate unauthorised late marks in minutes
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') AND " +
					"	ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') AND ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') AND " +
					"	ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP') " +
					"	THEN CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF ELSE 0 END + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF  AS UNAUTHORIZED_LATE_MINS " +
					
					"	FROM HRMS_DAILY_ATTENDANCE_" + year +
					"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					"	INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
					"	WHERE ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY " +
					"	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) " +
					" 	GROUP BY ATT_STATUS_ONE, SFT_LM_HRS_ISENABLED, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO, ATT_STATUS_TWO, ATT_EMP_ID, ATT_DATE, " +
					"	ATT_REG_STATUS_LATE, ATT_REG_STATUS_SWIPE, ATT_REG_LC_HRS, ATT_REG_STATUS_PERTIME, ATT_LATE_HRS, ATT_EARLY_HRS, " +
					"	SFT_DED_LM_IN_SLABS_OF, ATT_REG_EL_HRS " +
					" ) " +
					" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) +
					" GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";*/
					
					
					lateMarksHalfDaysSql = " SELECT ATT_EMP_ID, ATT_DATE, LATE_MARKS, HALF_DAYS, " +
					" NVL(UNAUTHORIZED_LATE_MINS, 0) AS UNAUTHORIZED_LATE_MINS FROM (" +
					"	SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS ATT_DATE, " +
					// calculate late marks
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN 2 WHEN ATT_STATUS_ONE = 'PR' AND " +
					"	ATT_STATUS_TWO IN('LC', 'EL') THEN 1 ELSE 0 END AS LATE_MARKS, " +
					// calculate half days
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' THEN 1 ELSE 0 END + " +
					"	CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN ATT_REG_STATUS_ONE = 'PR' AND " +
					"	ATT_REG_STATUS_TWO = 'HD' THEN 1 ELSE 0 END) ELSE 0 END AS HALF_DAYS, " +
					// calculate unauthorised late marks in minutes
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') AND ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') AND " +
					"	ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') AND ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP') THEN " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF ELSE 0 END + CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF AS UNAUTHORIZED_LATE_MINS " +
					
					"	FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + ".ATT_EMP_ID) " +
					"	INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
					"	WHERE ATT_STATUS_ONE = 'PR' " +
					"	AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY " +
					"	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) " +
					"	GROUP BY ATT_STATUS_ONE, SFT_LM_HRS_ISENABLED, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO, ATT_STATUS_TWO, ATT_EMP_ID, ATT_DATE, " +
					"	ATT_REG_STATUS_LATE, ATT_REG_STATUS_SWIPE, ATT_REG_LC_HRS, ATT_REG_STATUS_PERTIME, ATT_LATE_HRS, ATT_EARLY_HRS, " +
					"	SFT_DED_LM_IN_SLABS_OF, ATT_REG_EL_HRS " +
					
					"	UNION ALL " +
					
					"	SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS ATT_DATE, " +
					// calculate late marks
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN 2 WHEN ATT_STATUS_ONE = 'PR' AND " +
					"	ATT_STATUS_TWO IN('LC', 'EL') THEN 1 ELSE 0 END AS LATE_MARKS, " +
					// calculate half days
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' THEN 1 ELSE 0 END + " +
					"	CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN ATT_REG_STATUS_ONE = 'PR' AND " +
					"	ATT_REG_STATUS_TWO = 'HD' THEN 1 ELSE 0 END) ELSE 0 END AS HALF_DAYS, " +
					// calculate unauthorised late marks in minutes
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') AND ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') AND " +
					"	ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') AND ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP') THEN " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * 	SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF ELSE 0 END + CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF + CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF  AS UNAUTHORIZED_LATE_MINS " +
					
					"	FROM HRMS_DAILY_ATTENDANCE_" + year +
					"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					"	INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
					"	WHERE ATT_STATUS_ONE = 'PR' " +
					"	AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY " +
					"	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) " +
					"	GROUP BY ATT_STATUS_ONE, SFT_LM_HRS_ISENABLED, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO, ATT_STATUS_TWO, ATT_EMP_ID, ATT_DATE, " +
					"	ATT_REG_STATUS_LATE, ATT_REG_STATUS_SWIPE, ATT_REG_LC_HRS, ATT_REG_STATUS_PERTIME, ATT_LATE_HRS, ATT_EARLY_HRS, " +
					"	SFT_DED_LM_IN_SLABS_OF, ATT_REG_EL_HRS" +
					" ) " +
					" WHERE TO_DATE(ATT_DATE, 'DD-MM-YYYY') BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) +
					" ORDER BY ATT_EMP_ID ";
				}
				
				// get late marks and half days from current year table
				else {
					/*lateMarksHalfDaysSql = " SELECT ATT_EMP_ID, SUM(NVL(LATE_MARKS, 0)) AS LATE_MARKS, " +
					" SUM(NVL(HALF_DAYS, 0)) AS HALF_DAYS, CEIL(SUM(NVL(UNAUTHORIZED_LATE_MINS, 0))) AS UNAUTHORIZED_LATE_MINS " +
					" FROM ( " +
					" 	SELECT ATT_EMP_ID, ATT_DATE, " +
					// calculate late marks
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN 2 " +
					"	WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN('LC', 'EL') THEN 1 ELSE 0 END AS LATE_MARKS, " +
					// calculate half days
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' THEN 1 ELSE 0 END + " +
					"	CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN ATT_REG_STATUS_ONE = 'PR' AND " +
					"	ATT_REG_STATUS_TWO = 'HD' THEN 1 ELSE 0 END) ELSE 0 END AS HALF_DAYS, " +
					// calculate unauthorised late marks in minutes
					"	CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') AND " +
					"	ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') AND ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') AND " +
					"	ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP') " +
					"	THEN CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF ELSE 0 END + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF + " +
					"	CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					"	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					"	SFT_DED_LM_IN_SLABS_OF  AS UNAUTHORIZED_LATE_MINS " +
					
					" 	FROM HRMS_DAILY_ATTENDANCE_" + year +
					" 	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					" 	INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
					" 	WHERE ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY " +
					" 	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) " +
					" ) " +
					" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) +
					" GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";*/
					
					
					lateMarksHalfDaysSql = " SELECT ATT_EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS ATT_DATE, " +
					// calculate late marks
					" CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN 2 WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN('LC', 'EL') " +
					" THEN 1 ELSE 0 END AS LATE_MARKS, " +
					// calculate half days
					" CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' THEN 1 ELSE 0 END + " +
					" CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN ATT_REG_STATUS_ONE = 'PR' AND ATT_REG_STATUS_TWO = 'HD' THEN 1 ELSE 0 END) " +
					" ELSE 0 END AS HALF_DAYS, " +
					// calculate unauthorised late marks in minutes
					" CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') AND ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') AND " +
					" ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') AND ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP') " +
					" THEN CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * SFT_DED_LM_IN_SLABS_OF + " +
					" CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * SFT_DED_LM_IN_SLABS_OF ELSE 0 END + " +
					" CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
					" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * SFT_DED_LM_IN_SLABS_OF + " +
					" CEIL((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 	" +
					" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0)) / SFT_DED_LM_IN_SLABS_OF) * " +
					" SFT_DED_LM_IN_SLABS_OF  AS UNAUTHORIZED_LATE_MINS " +
					
					" FROM HRMS_DAILY_ATTENDANCE_" + year +
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
					" WHERE ATT_STATUS_ONE = 'PR' AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND " +
					" TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					" AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY " +
					" WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) +
					" GROUP BY ATT_STATUS_ONE, SFT_LM_HRS_ISENABLED, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO, ATT_STATUS_TWO, ATT_EMP_ID, ATT_DATE, " +
					" ATT_REG_STATUS_LATE, ATT_REG_STATUS_SWIPE, ATT_REG_LC_HRS, ATT_REG_STATUS_PERTIME, ATT_LATE_HRS, ATT_EARLY_HRS, " +
					" SFT_DED_LM_IN_SLABS_OF, ATT_REG_EL_HRS ORDER BY ATT_EMP_ID ";
				}
				lateMarksHalfDaysObj = getSqlModel().getSingleResult(lateMarksHalfDaysSql);
			}
		} catch(Exception e) {
			logger.error("Exception in loadLateMarksHalfDays: " + e);
		}
		return lateMarksHalfDaysObj;
	}
	
	private Object[][] loadLeaveDaysExcludingHalfDays(String fromDate, String toDate, String concatEmployeeIds, String month, String year) {
		Object[][] leaveDaysObj = null;
		try {
			String leaveDaysSql = " SELECT HRMS_LEAVE_DTLHISTORY.EMP_ID, " +
			" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) END AS LEV_DAYS " +
			" FROM HRMS_LEAVE_DTLHISTORY " +
			" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_LEAVE_DTLHISTORY.EMP_ID) " +
			" WHERE LEAVE_DTL_STATUS = 'A' AND  LEAVE_FROM_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') "  
			+" AND HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS >= 1 " +
			Utility.getConcatenatedIds("HRMS_LEAVE_DTLHISTORY.EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
			" GROUP BY HRMS_LEAVE_DTLHISTORY.EMP_ID, AUTO_PRESENT_ABSENT ORDER BY HRMS_LEAVE_DTLHISTORY.EMP_ID ";
			
			leaveDaysObj = getSqlModel().getSingleResult(leaveDaysSql);
		} catch(Exception e) {
			logger.error("Exception in loadLeaveDaysExcludingHalfDays:" + e);
		}
		return leaveDaysObj;
	}

	private Object[][] loadLeavePenalties(String month, String year, String concatEmployeeIds) {
		Object[][] leavePenaltiesObj = null;
		try {
			String leavePenaltiesSql = " SELECT EMP_ID, CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 " +
			" ELSE SUM(NVL(LEAVE_PENALTY_ADJUST_DAYS, 0)) END AS LEAVE_PENALTY_ADJUST_DAYS, " +
			" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 " +
			" ELSE SUM(NVL(LEAVE_PENALTY_UNADJUST_DAYS, 0)) END AS LEAVE_PENALTY_UNADJUST_DAYS " +
			" FROM HRMS_LEAVE_DTL " +
			" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_LEAVE_DTL.EMP_ID) " +
			" WHERE TO_CHAR(LEAVE_FROM_DATE, 'MM-YYYY') = '" + month + "-" + year + "' AND LEAVE_DTL_STATUS = 'A' " + 
			Utility.getConcatenatedIds("EMP_ID", concatEmployeeIds) +
			" GROUP BY EMP_ID, AUTO_PRESENT_ABSENT ORDER BY EMP_ID ";
			leavePenaltiesObj = getSqlModel().getSingleResult(leavePenaltiesSql);
		} catch(Exception e) {
			logger.error("Exception in loadLeavePenalties:" + e);
		}
		return leavePenaltiesObj;
	}
	
	/**
	 * Load approved leaves and balances from back end
	 * @param concatEmployeeIds: Employee Ids separated by comma
	 * @param fromDate: Date from which approved leaves and balances are to be loaded
	 * @param toDate: Date to which approved leaves and balances are to be loaded
	 * @return
	 */
	private Object[][] loadLeavesAndBalances(String concatEmployeeIds, String fromDate, String toDate, String month, String year) {
		Object[][] approvedLeavesObj = null;
		try {
			String approvedLeavesSql = " SELECT HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, " +
			/**
			 * Opening Balance
			 */
			" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) END + " +
			" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) + NVL(LEAVE_CLOSING_BALANCE, 0) " +
			" ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END AS LEV_OPENING_BALANCE, " + 
			
			/**
			 * Leave Days
			 */
			" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) END AS LEV_DAYS, " + 
			
			/**
			 * Closing Balance
			 */
			" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) + NVL(LEAVE_CLOSING_BALANCE, 0) " +
			" ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END AS LEV_CLOSING_BALANCE, " + 
			
			/**
			 * Opening Balance in Minutes
			 */
			// calculate leave days in minutes
			" CEIL((CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) END * " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
			// calculate closing balance in minutes
			" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) + NVL(LEAVE_CLOSING_BALANCE, 0) " +
			" ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END * NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0 + " + 
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 1, 2)), 0) * 60 + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 4, 5)), 0)) AS LEV_OPENING_BALANCE_IN_MINS, " +
			
			/**
			 * Closing Balance in minutes
			 */
			// calculate leave days in minutes + closing balance in minutes
			" CEIL(CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN (CASE WHEN AUTO_PRESENT_ABSENT = 'Y' " +
			" THEN SUM(NVL(HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, 0)) + NVL(LEAVE_CLOSING_BALANCE, 0) ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END * " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 1, 2)), 0) * 60 + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 4, 5)), 0) " +
			// calculate closing balance in minutes
			" ELSE (NVL(LEAVE_CLOSING_BALANCE, 0) * NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 1, 2)), 0) * 60 + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 4, 5)), 0) END) AS LEV_CLOSING_BALANCE_IN_MINS " +
			
			" FROM HRMS_LEAVE_BALANCE " +
			" LEFT JOIN HRMS_LEAVE_DTLHISTORY ON(HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID " +
			" AND HRMS_LEAVE_DTLHISTORY.LEAVE_CODE = HRMS_LEAVE_BALANCE.LEAVE_CODE AND HRMS_LEAVE_DTLHISTORY.LEAVE_DTL_STATUS = 'A' " +
			" AND  LEAVE_FROM_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY')) " +
			" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID) " +
			" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID) " +
			" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
			" WHERE 1 = 1 " + Utility.getConcatenatedIds("HRMS_LEAVE_BALANCE.EMP_ID", concatEmployeeIds) +
			" GROUP BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, AUTO_PRESENT_ABSENT, LEAVE_CLOSING_BALANCE, " +
			" SFT_WORK_HRS, LEAVE_HRS_CLOSING_BALANCE " +
			" ORDER BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE ";
			
			approvedLeavesObj = getSqlModel().getSingleResult(approvedLeavesSql);
		} catch(Exception e) {
			logger.error("Exception in loadLeavesAndBalances: " + e);
		}
		return approvedLeavesObj;
	}
	
	private Object[][] loadUnauthorisedLeaves(boolean dailyAttnConnFlag, String month, String year, String salaryStartMonth, 
		String fromDate, String toDate, String concatEmployeeIds) {
		Object[][] absentsFromAttendanceObj = null;
		try {
			String absentsFromAttendanceSql = "";
			
			if(dailyAttnConnFlag || salaryStartMonth.equals("P")) { // Daily attendance work flow is enabled
				// If current month is Jan and salary start month is previous, 
				// then get holidays from both previous year and current year tables
				if(Integer.parseInt(month) == 1 && salaryStartMonth.equals("P")) {
					absentsFromAttendanceSql = " SELECT ATT_EMP_ID, NVL(COUNT(ATT_DATE), 0) AS ABSENT_DAYS FROM ( " +
					"	SELECT ATT_EMP_ID, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					"	WHERE (ATT_STATUS_ONE = 'AB' AND ATT_STATUS_TWO = 'AB') AND (ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') " +
					"	OR ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') OR ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP')) " +
					"	UNION ALL " +
					"	SELECT ATT_EMP_ID, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + year +
					"	WHERE (ATT_STATUS_ONE = 'AB' AND ATT_STATUS_TWO = 'AB') AND (ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') " +
					"	OR ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') OR ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP'))" +
					" ) " +
					" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";
				} else {
					absentsFromAttendanceSql = " SELECT ATT_EMP_ID, CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 " +
					" ELSE NVL(COUNT(*), 0) END AS ABSENT_DAYS " +
					" FROM HRMS_DAILY_ATTENDANCE_" + year +
					" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID) " +
					" WHERE (ATT_STATUS_ONE = 'AB' AND ATT_STATUS_TWO = 'AB') AND ATT_REG_STATUS_LATE IN('NR', 'PL', 'RL') AND " +
					" ATT_REG_STATUS_SWIPE IN('NR', 'PS', 'RS') AND ATT_REG_STATUS_PERTIME IN('NR', 'PP', 'RP') " +
					" AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" GROUP BY ATT_EMP_ID, AUTO_PRESENT_ABSENT ORDER BY ATT_EMP_ID ";
				}
				absentsFromAttendanceObj = getSqlModel().getSingleResult(absentsFromAttendanceSql);
			}
		} catch(Exception e) {
			logger.error("Exception in loadAbsentsFromAttendance:" + e);
		}
		return absentsFromAttendanceObj;
	}
	
	/**
	 * Load uploaded attendance data from back end
	 * @param concatEmployeeIds: Employee Ids separated by comma
	 * @param month: Month for which uploaded attendance is to be loaded
	 * @param year: Year for which uploaded attendance is to be loaded
	 * @param uploadAttnConnFlag: Upload monthly attendance work flow is enabled or not
	 * @return Object[][] as uploaded attendance data
	 */
	private Object[][] loadUploadAttendanceData(String concatEmployeeIds, String month, String year, boolean uploadAttnConnFlag,String defaultDaysFlag) {
		Object[][] uploadAttendanceObj = null;
		try {
			if(uploadAttnConnFlag) { // Uploaded monthly attendance work flow is enabled
				String uploadAttendanceSql = " SELECT EMP_ID, NVL(ATTN_LATE_MARKS, 0), NVL(ATTN_HALF_DAYS, 0), NVL(ATTN_PAID_LEAVES, 0), " +
				" NVL(ATTN_UNPAID_LEAVES, 0),NVL(ATTN_RECOVERY_DAYS,0) FROM HRMS_EMP_OFFC " +
				" LEFT JOIN HRMS_UPLOAD_ATTENDANCE_" + year + " ON(HRMS_EMP_OFFC.EMP_ID = HRMS_UPLOAD_ATTENDANCE_" + year + ".ATTN_EMP_ID " +
				" AND ATTN_MONTH = " + month + " AND ATTN_YEAR = " + year + ") " +
				" WHERE 1 = 1 " + Utility.getConcatenatedIds("EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
				" ORDER BY EMP_ID ";
				if(defaultDaysFlag.equals("Z")){
					
					uploadAttendanceSql = " SELECT EMP_ID, NVL(ATTN_LATE_MARKS, 0), NVL(ATTN_HALF_DAYS, 0), NVL(ATTN_PAID_LEAVES, 0), " +
					" NVL(ATTN_UNPAID_LEAVES, TO_CHAR(LAST_DAY (TO_DATE ('" + month + "-" + year + "','MM-YYYY')),'DD')),NVL(ATTN_RECOVERY_DAYS,0) FROM HRMS_EMP_OFFC " +
					" LEFT JOIN HRMS_UPLOAD_ATTENDANCE_" + year + " ON(HRMS_EMP_OFFC.EMP_ID = HRMS_UPLOAD_ATTENDANCE_" + year + ".ATTN_EMP_ID " +
					" AND ATTN_MONTH = " + month + " AND ATTN_YEAR = " + year + ") " +
					" WHERE 1 = 1 " + Utility.getConcatenatedIds("EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" ORDER BY EMP_ID ";
				}
				uploadAttendanceObj = getSqlModel().getSingleResult(uploadAttendanceSql);
			}
		} catch(Exception e) {
			logger.error("Exception in loadUploadAttendanceData:" + e);
		}
		return uploadAttendanceObj;
	}
	
	/**
	 * Load weekly offs from back end
	 * @param concatEmployeeIds: Employee Ids separated by comma
	 * @param dailyAttnConnFlag: Daily attendance work flow is enabled or not
	 * @param fromDate: Date from which weekly offs are to be loaded
	 * @param toDate: Date to which weekly offs are to be loaded
	 * @param month: Month for which weekly offs are to be loaded
	 * @param year: Year for which weekly offs are to be loaded
	 * @param salaryStartMonth: Salary Start Month (Current/Previous) for which attendance details are to be loaded
	 * @return Object[][] as weekly offs
	 */
	private Object[][] loadWeeklyOffsFromDailyAttendance(String concatEmployeeIds, boolean dailyAttnConnFlag, String fromDate, String toDate, 
			String month, String year, String salaryStartMonth) {
		Object[][] weekOffObj = null;
		try {
			String weekOffSql = "";
			
			fromDate = "01-" + month + "-" + year;
			toDate = getMonthDays(month, year) + "-" + month + "-" + year;
			
			if(dailyAttnConnFlag) { // Daily attendance work flow is enabled
				// If current month is Jan and salary start month is previous, then get weekly offs from both previous year and current year tables
				if(Integer.parseInt(month) == 1 && salaryStartMonth.equals("P")) {
					weekOffSql = " SELECT EMP_ID, WEEKLY_OFF FROM ( " +
					"	SELECT ATT_EMP_ID AS EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS WEEKLY_OFF " +
					"	FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + "	WHERE ATT_STATUS_ONE = 'WO' " +
					"	UNION ALL " +
					"	SELECT ATT_EMP_ID AS EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS WEEKLY_OFF " +
					"	FROM HRMS_DAILY_ATTENDANCE_" + year + "	WHERE ATT_STATUS_ONE = 'WO' " +
					" ) " +
					" WHERE TO_DATE(WEEKLY_OFF, 'DD-MM-YYYY') BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " +
					" AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " + 
					Utility.getConcatenatedIds("EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" ORDER BY EMP_ID, WEEKLY_OFF ";
				}
				
				// get weekly offs from current year table
				else {
					weekOffSql = " SELECT ATT_EMP_ID AS EMP_ID, TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS WEEKLY_OFF " +
					" FROM HRMS_DAILY_ATTENDANCE_" + year + " WHERE ATT_STATUS_ONE = 'WO' AND ATT_DATE BETWEEN " +
					" TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " + 
					Utility.getConcatenatedIds("ATT_EMP_ID", concatEmployeeIds) + // get employee ids separated by comma
					" ORDER BY ATT_EMP_ID ";
				}
			}			
			weekOffObj = getSqlModel().getSingleResult(weekOffSql);
		} catch(Exception e) {
			logger.error("Exception in loadWeeklyOffs:" + e);
		}
		return weekOffObj;
	}
	
	/**
	 * Reset global variables to null
	 */
	public void resetToNull() {
		try {
			attendanceSettings = null; uploadAttendance = null; holidaysFromMaster = null; holidaysFromDailyAttendance = null; 
			dailyAttendanceNotExist = null; weeklyOffsFromDailyAttendance = null; lateMarksHalfDays = null; leavePolicies = null; 
			leavesAndBalances = null; unauthorisedLeaves = null; leaveDays = null; holidays = null;
			
			numOfPaidLeaves = 0.0; numOfUnPaidLeaves = 0.0; numOfPaidLateMarks = 0.0; numOfPaidHalfDays = 0.0;
			numOfUnPaidLateMarks = 0.0; numOfUnPaidHalfDays = 0.0; numOfPaidLateMarkMins = 0; numOfUnPaidLateMarkMins = 0;
			numOfUnauthAdjLeaves = 0.0; numOfUnauthUnAdjLeaves = 0.0; numOfAbsents = 0; extraHolidays = 0; extraWeeklyOffs = 0; 
			workingHours = 0.0; leaveUnauthFlag = false;
		} catch(Exception e) {
			logger.error("Exception in resetToNull:" + e);
		}
	}
	
	/**
	 * Save attendance data
	 * @param year: Year for which attendance calculated is to be saved
	 * @param attendanceCode: Generated by saving attendance filters in HRMS_SALARY_LEDGER
	 * @param calculatedAttendnace: Contains attendance data
	 * @return boolean as whether attendance is saved successfully or not
	 */
	public boolean saveAttendance(String year, String attendanceCode, Object[][] calculatedAttendnace) {
		boolean isDataSaved = false;
		try {
			/**
			 * Insert into HRMS_MONTH_ATTENDANCE_<year>
			 */
			Object[][] saveAttendance = new Object[calculatedAttendnace.length][20]; // initially blank object to save attendance
			Object[][] saveAttendanceDetails = {}; // initially blank object to save attendance details
			
			/**
			 * Copy calculated attendance into an object. Last record in a row is having details regarding the leaves adjusted.
			 * Combine those records into one object
			 */
			for(int i = 0; i < calculatedAttendnace.length; i++) {
				saveAttendance[i][0] = calculatedAttendnace[i][0]; // ATTN_EMP_ID
				saveAttendance[i][1] = calculatedAttendnace[i][1]; // ATTN_HOLIDAY
				saveAttendance[i][2] = calculatedAttendnace[i][2]; // ATTN_WOFF
				saveAttendance[i][3] = calculatedAttendnace[i][3]; // ATTN_LATE_MARKS
				saveAttendance[i][4] = calculatedAttendnace[i][4]; // ATTN_LATE_MARKS_HRS
				saveAttendance[i][5] = calculatedAttendnace[i][5]; // ATTN_HALF_DAYS
				saveAttendance[i][6] = calculatedAttendnace[i][6]; // ATTN_PAID_LEVS
				saveAttendance[i][7] = calculatedAttendnace[i][7]; // ATTN_PAID_LEVS_HRS
				saveAttendance[i][8] = calculatedAttendnace[i][8]; // ATTN_PENALTY_ADJUSTED
				saveAttendance[i][9] = calculatedAttendnace[i][9]; // ATTN_UNPAID_LEVS
				saveAttendance[i][10] = calculatedAttendnace[i][10]; // ATTN_UNPAID_LEVS_HRS
				saveAttendance[i][11] = calculatedAttendnace[i][11]; // ATTN_PENALTY_UNADJUSTED
				saveAttendance[i][12] = calculatedAttendnace[i][12]; // ATTN_SYSTEM_UNPAID
				saveAttendance[i][13] = calculatedAttendnace[i][13]; // ATTN_DAYS
				saveAttendance[i][14] = calculatedAttendnace[i][14]; // ATTN_HRS
				saveAttendance[i][15] = calculatedAttendnace[i][15]; // ATTN_UNAUTH_ADJUSTED
				saveAttendance[i][16] = calculatedAttendnace[i][16]; // ATTN_UNAUTH_UNADJUSTED
				saveAttendance[i][17] = calculatedAttendnace[i][17]; // ATTN_SAL_DAYS
				saveAttendance[i][18] = calculatedAttendnace[i][18]; // ATTN_SAL_HRS
				
				saveAttendance[i][19] = calculatedAttendnace[i][21]; // ATTN_RECOVERY_DAYS
				
				if(!(calculatedAttendnace[i][19] == null || calculatedAttendnace[i][19].equals(null) || 
						calculatedAttendnace[i][19].equals("null") || calculatedAttendnace[i][19].equals(""))) {
					// combine all employees' attendance details data into one object
					Object[][] attendanceDetails = (Object[][])calculatedAttendnace[i][19];
					
					// Combine both object by column wise (1) started by first record (0) of second object
					saveAttendanceDetails = Utility.joinArrays(saveAttendanceDetails, attendanceDetails, 1, 0);
				}
			}			
			String saveAttendanceSql = " INSERT INTO HRMS_MONTH_ATTENDANCE_" + year + "(ATTN_CODE, ATTN_EMP_ID, ATTN_HOLIDAY, " +
			" ATTN_WOFF, ATTN_LATE_MARKS, ATTN_LATE_MARKS_HRS, ATTN_HALF_DAYS, ATTN_PAID_LEVS, ATTN_PAID_LEVS_HRS, ATTN_PENALTY_ADJUSTED, " +
			" ATTN_UNPAID_LEVS, ATTN_UNPAID_LEVS_HRS, ATTN_PENALTY_UNADJUSTED, ATTN_SYSTEM_UNPAID, ATTN_DAYS, " +
			" ATTN_HRS, ATTN_UNAUTH_ADJUSTED, ATTN_UNAUTH_UNADJUSTED, ATTN_SAL_DAYS, ATTN_SAL_HRS, ATTN_RECOVERY_DAYS) " +
			" VALUES (" + attendanceCode + ", ?, ?, ?, ?, TO_DATE(?, 'HH24:MI'), ?, ?, TO_DATE(?, 'HH24:MI'), ?, ?, " +
			" TO_DATE(?, 'HH24:MI'), ?, ?, ?, TO_DATE(?, 'HH24:MI'), ?, ?, ?, TO_DATE(?, 'HH24:MI'), ?) ";
			isDataSaved = getSqlModel().singleExecute(saveAttendanceSql, saveAttendance);
			
			/**
			 * Insert into HRMS_MONTH_ATT_DTL__<year> and update HRMS_LEAVE_BALANCE
			 */
			if(isDataSaved) { // if data is saved successfully into attendance, then proceed
				if(saveAttendanceDetails != null && saveAttendanceDetails.length > 0) {
					isDataSaved = saveAttendanceDetails(year, attendanceCode, saveAttendanceDetails);
				}
			}
		} catch(Exception e) {
			logger.error("Exception in saveAttendance:" + e);
			e.printStackTrace();
		}
		return isDataSaved;
	}
	
	/**
	 * Save attendance details
	 * @param year: Year for which attendance calculated is to be saved
	 * @param attendanceCode: Auto generated code after saving record in HRMS_SALARY_LEDGER
	 * @param saveAttendanceDetails: Contains calculated attendance details
	 * @return boolean as successfully data saved
	 */
	private boolean saveAttendanceDetails(String year, String attendanceCode, Object[][] saveAttendanceDetails) {
		boolean isDataSaved = false; // track the progress while saving the attendance details and updating the balances
		try {
			if(saveAttendanceDetails != null && saveAttendanceDetails.length > 0) {
				/**
				 * Insert into HRMS_MONTH_ATT_DTL_<year>
				 */				
				String saveAttendanceDetailSql = " INSERT INTO HRMS_MONTH_ATT_DTL_" + year + " (ATT_CODE, ATT_EMP_CODE, ATT_LEAVE_CODE, " +
				" ATT_LEAVE_AVAILABLE, ATT_LEAVE_AVAILABLE_HRS, ATT_LEAVE_ADJUST, ATT_LATEMARK_ADJUST, ATT_LATEMARK_ADJUST_HRS, " +
				" ATT_HALFDAY_ADJUST, ATT_LEAVE_BALANCE, ATT_LEAVE_BALANCE_HRS, ATT_UNAUTHORISED_ADJUST, ATT_MANUAL_ADJUST, " +
				" ATT_MANUAL_ADJUST_HRS) " +
				" VALUES(" + attendanceCode + ", ?, ?, ?, TO_DATE(?, 'HH24:MI'), ?, ?, TO_DATE(?, 'HH24:MI'), ?, ?, " +
				" TO_DATE(?, 'HH24:MI'), ?, 0, TO_DATE('00:00', 'HH24:MI')) ";
				isDataSaved = getSqlModel().singleExecute(saveAttendanceDetailSql, saveAttendanceDetails);
				
				/**
				 * Update HRMS_LEAVE_BALANCE
				 */
				if(isDataSaved) {
					Object[][] updateClosingBalances = new Object[saveAttendanceDetails.length][4];
					
					for(int i = 0; i < saveAttendanceDetails.length; i++) {
						updateClosingBalances[i][0] = String.valueOf(saveAttendanceDetails[i][8]); // LEAVE_CLOSING_BALANCE
						updateClosingBalances[i][1] = String.valueOf(saveAttendanceDetails[i][9]); // LEAVE_HRS_CLOSING_BALANCE
						updateClosingBalances[i][2] = String.valueOf(saveAttendanceDetails[i][0]); // EMP_ID
						updateClosingBalances[i][3] = String.valueOf(saveAttendanceDetails[i][1]); // LEAVE_CODE
					}
					String updateClosingBalanceSql = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = ?, " +
					" LEAVE_HRS_CLOSING_BALANCE = TO_DATE(?, 'HH24:MI') WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
					isDataSaved = getSqlModel().singleExecute(updateClosingBalanceSql, updateClosingBalances);
				}	
			}
		} catch(Exception e) {
			logger.error("Exception in saveAttendanceDetails:" + e);
		}
		return isDataSaved;
	}
	
	/**
	 * Set attendance settings data
	 * @param attendanceSettings: Contains data of attendance settings
	 */
	private void setAttendanceSettings(Object[][] attendanceSettings) {
		this.attendanceSettings = attendanceSettings;
	}
	
	/**
	 * Save atendance filters and generated attendance code
	 * @param month: Month for which attendance calculated is to be saved
	 * @param year: Year for which attendance calculated is to be saved
	 * @param listOfFilters: List of filters selected for calculation of attendance
	 * @param saveAttendance: Contains calculated attendance 
	 * @param saveAttendanceDetails: Contains calculated leave details
	 * @return String as message specifying whether attendance is properly saved or not
	 */
	private String setAttendanceToSave(String month, String year, String[] listOfFilters, Object[][] calculatedAttendnace) {
		String message = "";
		try {
			boolean isDataSaved = false; // track the progress while saving the attendance and its details
			
			// Get max + 1 of LEDGER_CODE from HRMS_SALARY_LEDGER
			String attendanceCodeSql = " SELECT NVL(MAX(LEDGER_CODE),0) + 1 FROM HRMS_SALARY_LEDGER ";
			Object[][] attendanceCodeObj = getSqlModel().getSingleResult(attendanceCodeSql);
			String attendanceCode = String.valueOf(attendanceCodeObj[0][0]);

			/**
			 * Insert into HRMS_SALARY_LEDGER
			 */
			Object[][] saveSalaryLedgerObj = new Object[1][8];
			saveSalaryLedgerObj[0][0] = attendanceCode;
			saveSalaryLedgerObj[0][1] = month;
			saveSalaryLedgerObj[0][2] = year;
			saveSalaryLedgerObj[0][3] = listOfFilters[0]; // LEDGER_BRN
			saveSalaryLedgerObj[0][4] = listOfFilters[1]; // LEDGER_DEPT
			saveSalaryLedgerObj[0][5] = listOfFilters[2]; // LEDGER_PAYBILL
			saveSalaryLedgerObj[0][6] = listOfFilters[3]; // LEDGER_EMPTYPE
			saveSalaryLedgerObj[0][7] = listOfFilters[4]; // LEDGER_DIVISION
			
			String saveSalaryLedgerSql = " INSERT INTO HRMS_SALARY_LEDGER(LEDGER_CODE, LEDGER_MONTH, LEDGER_YEAR, " +
			" LEDGER_BRN, LEDGER_DEPT, LEDGER_PAYBILL, LEDGER_EMPTYPE, LEDGER_DIVISION, LEDGER_STATUS) " +
			" VALUES(?, ?, ?, ?, ?, ?, ?, ?, 'ATTN_START') ";
			isDataSaved = getSqlModel().singleExecute(saveSalaryLedgerSql, saveSalaryLedgerObj);
			
			if(isDataSaved) {
				// save attendance in HRMS_MONTH_ATTENDANCE_<year>, leave details in HRMS_MONTH_ATT_DTL_<year>, 
				// update leave balances in HRMS_LEAVE_BALANCE
				isDataSaved = saveAttendance(year, attendanceCode, calculatedAttendnace);
			}
			
			/**
			 * Set the message whether attendance is saved or not
			 */
			if(isDataSaved) { // attendance is saved properly
				message = "Attendance processed successfully! \nPlease proceed to view and lock the attendance.";
			} else { // attendance is not saved properly
				message = "Attendance cannot be processed!";
			}
		} catch(Exception e) {
			logger.error("Exception in saveAttendance:" + e);
			message = "Attendance cannot be processed!";
		}
		return message;
	}
	
	/**
	 * Set the selected filters in WHERE condition in Sql query while getting list of employees
	 * @param listOfFilters: Contains list of filters 
	 * @param sqlQuery: Sql query need to be concatenated by filters in WHERE condition
	 * @return String as concatenated Sql query
	 */
	private String setEmployeeOffciceFiletrs(String[] listOfFilters, String sqlQuery) {
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				sqlQuery += " AND EMP_CENTER = " + listOfFilters[0];
			}
			
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				sqlQuery += " AND EMP_DEPT = " + listOfFilters[0];
			}
			
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				sqlQuery += " AND EMP_TYPE = " + listOfFilters[3];
			}
			
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				sqlQuery += " AND EMP_DIV = " + listOfFilters[4];
			}
			return sqlQuery;
		} catch (Exception e) {
			logger.error("Exception in setEmployeeOffciceFiletrs:" + e);
			return "";
		}
	}
	
	/**
	 * Calculate from date from which attendance is to be calculated
	 * @param dailyAttnConnFlag: Daily attendance work flow is enabled or not
	 * @param month: Current month selected
	 * @param year: Current year selected
	 * @param salaryStartDate: Salary start date selected from where attendance is calculated, 
	 * only if daily attendance work flow is enabled
	 * @param salaryStartMonth: Salary start month (Current/Previous) selected from where attendance is calculated, 
	 * only if daily attendance work flow is enabled
	 * @return String as calculated fromDate
	 */
	public String setFromDate(boolean dailyAttnConnFlag, String month, String year, String salaryStartDate, String salaryStartMonth) {
		String fromDate = "";
		try {
			fromDate = "01-" + month + "-" + year; // set default fromDate
			
			//if(dailyAttnConnFlag) { // Daily attendance work flow is enabled
				if(salaryStartMonth.equals("P")) { // Check that attendance is calculated from Previous Month
					String newMonth = "", newYear = "";
					
					/**
					 * If current month is Jan, then set new month as Dec and year by 1, else deduct month by 1 and set new year 
					 * same as current year
					 */
					if(month.equals("01")) {
						newMonth = "12";
						newYear = String.valueOf(Integer.parseInt(year) - 1);
					} else {
						newMonth = String.valueOf(Integer.parseInt(month) - 1);
						
						// if month is in between 1 to 9, then add 0 initially
						newMonth = Integer.parseInt(newMonth) < 10 ? "0" + newMonth : newMonth;
						newYear = year;
					}
					fromDate = salaryStartDate + "-" + newMonth + "-" + newYear; // set final from date
				}
			//}
		} catch(Exception e) {
			logger.error("Exception in setFromDate:" + e);
			return fromDate;
		}
		return fromDate;
	}
	
	/**
	 * Setter for leavePolicies object
	 * @param leavePolicies leavePolicies object
	 */
	public void setLeavePolicies(Object[][] leavePolicies) {
		this.leavePolicies = leavePolicies;
	}
	
	/**
	 * Set data of leaves and balances
	 * @param leavesAndBalances: Contains data of leaves and balances
	 */
	public void setLeavesAndBalances(Object[][] leavesAndBalances) {
		this.leavesAndBalances = leavesAndBalances;
	}	
	
	/**
	 * Set not adjusted half days
	 * @param numOfUnPaidLateMarks: Not adjusted half days
	 */
	private void setNumOfUnPaidHalfDays(double numOfUnPaidHalfDays) {
		this.numOfUnPaidHalfDays = numOfUnPaidHalfDays;
	}
	
	/**
	 * @param numOfUnPaidLateMarkMins the numOfUnPaidLateMarkMins to set
	 */
	public void setNumOfUnPaidLateMarkMins(int numOfUnPaidLateMarkMins) {
		this.numOfUnPaidLateMarkMins = numOfUnPaidLateMarkMins;
	}
	
	/**
	 * Set not adjusted late marks
	 * @param numOfUnPaidLateMarks: Not adjusted late marks
	 */
	private void setNumOfUnPaidLateMarks(double numOfUnPaidLateMarks) {
		this.numOfUnPaidLateMarks = numOfUnPaidLateMarks;
	}
	
	/**
	 * Set the selected filters in WHERE condition in Sql query while checking whether attendance is already processed or not
	 * @param listOfFilters: Contains list of filters selected from a page
	 * @param sqlQuery: Sql query need to be concatenated by filters in WHERE condition
	 * @return String as concatenated Sql query
	 */
	private String setSalaryLedgerFiletrs(String[] listOfFilters, String sqlQuery) {
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				sqlQuery += " AND LEDGER_BRN = " + listOfFilters[0];
			}
			
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				sqlQuery += " AND LEDGER_DEPT = " + listOfFilters[0];
			}
			
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				sqlQuery += " AND LEDGER_PAYBILL = " + listOfFilters[2];
			}
			
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				sqlQuery += " AND LEDGER_EMPTYPE = " + listOfFilters[3];
			}
			
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				sqlQuery += " AND LEDGER_DIVISION = " + listOfFilters[4];
			}
			return sqlQuery;
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
			return "";
		}
	}

	/**
	 * Calculate to date to which attendance is to be calculated
	 * @param dailyAttnConnFlag: Daily attendance work flow is enabled or not
	 * @param month: Current month selected
	 * @param year: Current year selected
	 * @param salaryStartDate: Salary start date selected up to which attendance is calculated, 
	 * only if daily attendance work flow is enabled
	 * @param salaryStartMonth: Salary start month (Current/Previous) selected up to which attendance is calculated, 
	 * only if daily attendance work flow is enabled
	 * @return String as calculated toDate
	 */
	public String setToDate(boolean dailyAttnConnFlag, String month, String year, String salaryStartDate, String salaryStartMonth) {
		String toDate = "";
		try {
			int monthDays = getMonthDays(month, year); // Calculate total days of a month
			toDate = monthDays + "-" + month + "-" + year; // set default toDate
			int endDate= Integer.parseInt(salaryStartDate);
			endDate -= 1;
			//if(dailyAttnConnFlag) { // daily attendance work flow is enabled
				if(salaryStartMonth.equals("P")) { // Check that attendance is calculated from Previous Month					
					toDate = endDate + "-" + month + "-" + year;
				}
			//}
		} catch(Exception e) {
			logger.error("Exception in setToDate:" + e);
			return toDate;
		}
		return toDate;
	}
	
	/**
	 * @param workingHours the workingHours to set
	 */
	public void setWorkingHours(double workingHours) {
		this.workingHours = workingHours;
	}
	
	/**
	 * Calculate total weekly off days for a week
	 * @param calendarDay: Specifies day on which week of month is calculated
	 * @param weeklyOffObj: Contains all six weeks information about week off days for a shift
	 * @return int as week off days
	 */
	private int weekenDays(Calendar currentDay, Object weeklyOffObj[][]) {
		int totalWeekOffDays = 0;
		try {
			if(weeklyOffObj != null && weeklyOffObj.length > 0) {
				int weekOfMonth = currentDay.get(java.util.Calendar.WEEK_OF_MONTH); // Week of month of specified date
				
				String[] weekDaysObj = null;
				// finds out the week of month and then splits into week days
				for (int weekNo = 0; weekNo < weeklyOffObj[0].length; weekNo++) {
					// Week Off days in shift should not be null
					if(!(String.valueOf(weeklyOffObj[0][weekNo]).equals("null") || String.valueOf(weeklyOffObj[0][weekNo]).equals(""))) {
						// starts from first week, compare with week number of given date
						if(weekNo + 1 == weekOfMonth) {
							// split into week off days
							weekDaysObj = String.valueOf(weeklyOffObj[0][weekNo]).split(",");
						}
					}
				}

				/**
				 * Calculate week offs by comparing day of week and week days calculated
				 */
				if(weekDaysObj != null && weekDaysObj.length > 0) {
					for(int i = 0; i < weekDaysObj.length; i++) {
						int weekOffDay = 0;
						try {
							weekOffDay = Integer.parseInt(String.valueOf(weekDaysObj[i])); // get week off day
						} catch (Exception e) {
							logger.error("Exception in weekOffDay:" + e);
							weekOffDay = 0;
						}
						
						/**
						 * compare weekOffDay with day of week of currentDay, if matching increment totalWeekOffDays by 1
						 */
						switch(weekOffDay) {
							case 1 :
								if(currentDay.get(Calendar.DAY_OF_WEEK) == 1) { // currentDay is Sunday
									totalWeekOffDays++;
								}
								break;
							case 2 :
								if(currentDay.get(Calendar.DAY_OF_WEEK) == 2) { // currentDay is Monday
									totalWeekOffDays++;
								}
								break;	
							case 3 :
								if(currentDay.get(Calendar.DAY_OF_WEEK) == 3) { // currentDay is Tuesday
									totalWeekOffDays++;
								}
								break;	
							case 4 :
								if(currentDay.get(Calendar.DAY_OF_WEEK) == 4) { // currentDay is Wednesday
									totalWeekOffDays++;
								}
								break;	
							case 5 :
								if(currentDay.get(Calendar.DAY_OF_WEEK) == 5) { // currentDay is Thursday
									totalWeekOffDays++;
								}
								break;
							case 6 :
								if(currentDay.get(Calendar.DAY_OF_WEEK) == 6) { // currentDay is Friday
									totalWeekOffDays++;
								}
								break;	
							case 7 :
								if(currentDay.get(Calendar.DAY_OF_WEEK) == 7) { // currentDay is Saturday
									totalWeekOffDays++;
								}
								break;	
							default : break;
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in weekenDays:" + e);
		}		
		return totalWeekOffDays;
	}
}