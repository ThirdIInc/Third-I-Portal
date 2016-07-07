package org.paradyne.model.LMS;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.LMS.WelfareInformationBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class WelfareInformationModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(WelfareInformationModel.class);

	public boolean save(WelfareInformationBean bean, HttpServletRequest request) {

		boolean result = false;
		try {
			System.out.println("IN SAVE ");

			Object[][] welfareInfoDetails = new Object[1][33];

			if (bean.getDrinkWaterFacilityFlag().equals("true"))// Drinking
				// Water
				// Facility
				// Checked
				welfareInfoDetails[0][0] = "Y";
			else
				welfareInfoDetails[0][0] = "N";

			/*
			 * 
			 * if (bean.getDrinkWaterFacility().equals("Y")) {
			 * welfareInfoDetails[0][1] = "Y"; // Drinking Water Facility
			 * Checked If Yes } else { welfareInfoDetails[0][1] = "N"; //
			 * Drinking Water Facility Checked If No }
			 */
			if (bean.getChildMindingFacilityFlag().equals("true"))// Child
				// minding
				// Facility
				// Checked
				welfareInfoDetails[0][1] = "Y";
			else
				welfareInfoDetails[0][1] = "N";

			/*
			 * if (bean.getChildMindingFacility().equals("Y")) {
			 * welfareInfoDetails[0][3] = "Y"; // Child minding Facility Checked
			 * If Yes } else { welfareInfoDetails[0][3] = "N"; // Child minding
			 * Facility Checked If No }
			 */
			if (bean.getLockerFacilityFlag().equals("true"))// Locker Facility
				// Checked
				welfareInfoDetails[0][2] = "Y";
			else
				welfareInfoDetails[0][2] = "N";

			/*
			 * if (bean.getLockerFacility().equals("Y")) {
			 * welfareInfoDetails[0][5] = "Y"; // Locker Facility Checked If Yes }
			 * else { welfareInfoDetails[0][5] = "N"; // Locker Facility Checked
			 * If No }
			 */

			if (bean.getMealFacilityFlag().equals("true"))// Meal Facility
				// Checked
				welfareInfoDetails[0][3] = "Y";
			else
				welfareInfoDetails[0][3] = "N";

			if (bean.getMealFacility().equals("F")) {
				welfareInfoDetails[0][4] = "F"; // Meal Facility Checked If Free
			} else {
				welfareInfoDetails[0][4] = "P"; // Meal Facility Checked If
				// Payable
			}

			if (bean.getMedicalFacilityFlag().equals("true"))// Medical
				// Service
				// Facility
				// Checked
				welfareInfoDetails[0][5] = "Y";
			else
				welfareInfoDetails[0][5] = "N";

			if (bean.getMedicalFacility().equals("F")) {
				welfareInfoDetails[0][6] = "F"; // Medical Service Facility
				// Checked If Free
			} else {
				welfareInfoDetails[0][6] = "P"; // Medical Service Facility
				// Checked If Payable
			}

			if (bean.getOnsiteAccomodationFlag().equals("true"))// On-Site
				// Accomodation
				// Checked
				welfareInfoDetails[0][7] = "Y";
			else
				welfareInfoDetails[0][7] = "N";

			if (bean.getOnsiteAccomodation().equals("F")) {
				welfareInfoDetails[0][8] = "F"; // On-Site Accomodation If Free
			} else {
				welfareInfoDetails[0][8] = "P"; // On-Site Accomodation If
				// Payable
			}

			if (bean.getFullTimeFlag().equals("true"))// Full Time Checked
				welfareInfoDetails[0][9] = "Y";
			else
				welfareInfoDetails[0][9] = "N";

			welfareInfoDetails[0][10] = bean.getFullTime().trim(); // full time

			if (bean.getOffsiteAccomodationFlag().equals("true"))// Off-Site
				// Accomodation
				// Checked
				welfareInfoDetails[0][11] = "Y";
			else
				welfareInfoDetails[0][11] = "N";

			if (bean.getOffsiteAccomodation().equals("F")) {
				welfareInfoDetails[0][12] = "F"; // Off-Site Accomodation If
				// Free
			} else {
				welfareInfoDetails[0][12] = "P"; // Off-Site Accomodation If
				// Payable
			}

			if (bean.getPartTimeFlag().equals("true"))// Part Time Checked
				welfareInfoDetails[0][13] = "Y";
			else
				welfareInfoDetails[0][13] = "N";

			welfareInfoDetails[0][14] = bean.getPartTime().trim(); // part time

			if (bean.getDocFullTimeFlag().equals("true"))// Doctor Full Time
				// Checked
				welfareInfoDetails[0][15] = "Y";
			else
				welfareInfoDetails[0][15] = "N";

			welfareInfoDetails[0][16] = bean.getDocFullTime().trim(); // Doctor
			// Full
			// Time

			if (bean.getNurseFullTimeFlag().equals("true"))// Nurse Full Time
				// Checked
				welfareInfoDetails[0][17] = "Y";
			else
				welfareInfoDetails[0][17] = "N";

			welfareInfoDetails[0][18] = bean.getNurseFullTime().trim(); // Nurse
			// Full
			// Time

			if (bean.getDocPartTimeFlag().equals("true"))// Doctor Part Time
				// Checked
				welfareInfoDetails[0][19] = "Y";
			else
				welfareInfoDetails[0][19] = "N";

			welfareInfoDetails[0][20] = bean.getDocPartTime().trim(); // Doctor
			// Part
			// Time

			if (bean.getNursePartTimeFlag().equals("true"))// Nurse Part Time
				// Checked
				welfareInfoDetails[0][21] = "Y";
			else
				welfareInfoDetails[0][21] = "N";

			welfareInfoDetails[0][22] = bean.getNursePartTime().trim(); // Nurse
			// Part
			// Time

			welfareInfoDetails[0][23] = bean.getNumberOfChangingRooms().trim(); // NumberOfChangingRooms
			welfareInfoDetails[0][24] = bean.getNumberOfRestRooms().trim(); // NumberOfRestRooms
			welfareInfoDetails[0][25] = bean.getNumberOfMensToilet().trim(); // NumberOfMensToilet
			welfareInfoDetails[0][26] = bean.getNumberOfWomensToilet().trim(); // NumberOfWomensToilet
			welfareInfoDetails[0][27] = bean.getNumberOfMensUrinals().trim(); // NumberOfMensUrinals
			welfareInfoDetails[0][28] = bean.getNumberOfClinics().trim(); // NumberOfClinics
			welfareInfoDetails[0][29] = bean.getNumberOfEmergencyRooms().trim(); // NumberOfEmergencyRooms
			welfareInfoDetails[0][30] = bean.getNumberOfAmbulance().trim(); // NumberOfAmbulance
			welfareInfoDetails[0][31] = bean.getNumberOfUniforms().trim(); // NumberOfUniforms
			welfareInfoDetails[0][32] = bean.getNumberOfRainCoats().trim(); // getNumberOfRainCoats

			for (int i = 0; i < welfareInfoDetails.length; i++) {
				for (int j = 0; j < welfareInfoDetails[i].length; j++) {
					logger.info("welfareInfoDetails[" + i + "][" + j + "]  "
							+ welfareInfoDetails[i][j]);
				}
			}

			String Query1 = "DELETE FROM HRMS_WELFARE_DTL  ";
			result = getSqlModel().singleExecute(Query1);
			if (result) {
				String Query = "INSERT INTO HRMS_WELFARE_DTL ( IS_DRINKING_WATER_FACILITY, IS_CHILD_MINDING_FACILITY, IS_LOCKER_FACILITY, "
						+ "IS_MEAL_FACILITY, MEAL_FACILITY_TYPE, IS_MEDICAL_SERV_FACILITY, MEDICAL_SERV_FACILITY, IS_ONSITE_ACCOMODATION, "
						+ "ONSITE_ACCOM_TYPE,IS_OFFICER_FULLTIME, NO_FULL_TIME_OFFICERS, IS_OFFSITE_ACCOMODATION, OFFSITE_ACCOM_TYPE, "
						+ " IS_OFFICER_PARTTIME, NO_PARTTIME_OFFICERS, IS_DOCTOR_FULLTIME, NO_FULLTIME_DOCTOR,IS_NURSE_FULLTIME, NO_FULLTIME_NURSE,  IS_DOCTOR_PARTTIME, NO_PARTTIME_DOCTOR, "
						+ "IS_NURSE_PARTTIME, NO_PARTTIME_NURSE, NO_CHANGING_ROOMS, NO_REST_ROOMS, "
						+ "NO_TOILET_MALE, NO_TOILET_FEMALE, NO_URINAL_MALE, NO_OF_CLINIC, NO_EMERGENCY_ROOMS, NO_OF_AMBULANCE, NO_OF_UNIFORM_SETS, "
						+ "NO_OF_RAINCOATS) VALUES ( ?, ?, ?, ?,?, ?, ?, ?,?,?,?, ?, ?, ?,?, ?, ?, ?,?,?,?, ?, ?, ?,?, ?, ?, ?,?,?,?,?,?) ";
				result = getSqlModel().singleExecute(Query, welfareInfoDetails);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public boolean update(WelfareInformationBean bean,
			HttpServletRequest request) {
		boolean result = false;
		try {

			// Enteries for Procedure Grievance end

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void showSetting(WelfareInformationBean bean) {
		try {
			String Query = " SELECT IS_DRINKING_WATER_FACILITY, IS_CHILD_MINDING_FACILITY, IS_LOCKER_FACILITY, "
					+ "IS_MEAL_FACILITY, MEAL_FACILITY_TYPE, IS_MEDICAL_SERV_FACILITY, MEDICAL_SERV_FACILITY, IS_ONSITE_ACCOMODATION, "
					+ "ONSITE_ACCOM_TYPE,IS_OFFICER_FULLTIME, NO_FULL_TIME_OFFICERS, IS_OFFSITE_ACCOMODATION, OFFSITE_ACCOM_TYPE, "
					+ " IS_OFFICER_PARTTIME, NO_PARTTIME_OFFICERS, IS_DOCTOR_FULLTIME, NO_FULLTIME_DOCTOR,IS_NURSE_FULLTIME, NO_FULLTIME_NURSE,  IS_DOCTOR_PARTTIME, NO_PARTTIME_DOCTOR, "
					+ "IS_NURSE_PARTTIME, NO_PARTTIME_NURSE, NO_CHANGING_ROOMS, NO_REST_ROOMS, "
					+ "NO_TOILET_MALE, NO_TOILET_FEMALE, NO_URINAL_MALE, NO_OF_CLINIC, NO_EMERGENCY_ROOMS, NO_OF_AMBULANCE, NO_OF_UNIFORM_SETS, "
					+ "NO_OF_RAINCOATS  " + "   FROM  HRMS_WELFARE_DTL  ";

			Object[][] data = getSqlModel().getSingleResult(Query);
			if (data != null && data.length > 0) {

				if (String.valueOf(data[0][0]).equals("Y")) {
					bean.setDrinkWaterFacilityFlag("true");
				}
				if (String.valueOf(data[0][1]).equals("Y")) {
					bean.setChildMindingFacilityFlag("true");
				}
				if (String.valueOf(data[0][2]).equals("Y")) {
					bean.setLockerFacilityFlag("true");
				}
				if (String.valueOf(data[0][3]).equals("Y")) {
					bean.setMealFacilityFlag("true");
				}
				
				
				bean.setMealFacility(String.valueOf(data[0][4]));
				System.out.println("data[0][4] = " + data[0][4]);

				if (String.valueOf(data[0][5]).equals("Y")) {
					bean.setMedicalFacilityFlag("true");
				}
				bean.setMedicalFacility(String.valueOf(data[0][6]));

				if (String.valueOf(data[0][7]).equals("Y")) {
					bean.setOnsiteAccomodationFlag("true");
				}
				bean.setOnsiteAccomodation(String.valueOf(data[0][8]));

				if (String.valueOf(data[0][9]).equals("Y")) {
					bean.setFullTimeFlag("true");
				}
				bean.setFullTime(String.valueOf(data[0][10]));

				if (String.valueOf(data[0][11]).equals("Y")) {
					bean.setOffsiteAccomodationFlag("true");
				}
				bean.setOffsiteAccomodation(String.valueOf(data[0][12]));

				if (String.valueOf(data[0][13]).equals("Y")) {
					bean.setPartTimeFlag("true");
				}
				bean.setPartTime(checkNull(String.valueOf(data[0][14])));

				if (String.valueOf(data[0][15]).equals("Y")) {
					bean.setDocFullTimeFlag("true");
				}
				bean.setDocFullTime(checkNull(String.valueOf(data[0][16])));

				if (String.valueOf(data[0][17]).equals("Y")) {
					bean.setNurseFullTimeFlag("true");
				}
				bean.setNurseFullTime(checkNull(String.valueOf(data[0][18])));

				if (String.valueOf(data[0][19]).equals("Y")) {
					bean.setDocPartTimeFlag("true");
				}
				bean.setDocPartTime(String.valueOf(data[0][20]));

				if (String.valueOf(data[0][21]).equals("Y")) {
					bean.setNursePartTimeFlag("true");
				}
				bean.setNursePartTime(checkNull(String.valueOf(data[0][22])));

				bean.setNumberOfChangingRooms(checkNull(String.valueOf(data[0][23])));
				bean.setNumberOfRestRooms(checkNull(String.valueOf(data[0][24])));
				bean.setNumberOfMensToilet(checkNull(String.valueOf(data[0][25])));
				bean.setNumberOfWomensToilet(checkNull(String.valueOf(data[0][26])));
				bean.setNumberOfMensUrinals(checkNull(String.valueOf(data[0][27])));
				bean.setNumberOfClinics(checkNull(String.valueOf(data[0][28])));
				bean.setNumberOfEmergencyRooms(checkNull(String.valueOf(data[0][29])));
				bean.setNumberOfAmbulance(checkNull(String.valueOf(data[0][30])));
				bean.setNumberOfUniforms(checkNull(String.valueOf(data[0][31])));
				bean.setNumberOfRainCoats(checkNull(String.valueOf(data[0][32])));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
