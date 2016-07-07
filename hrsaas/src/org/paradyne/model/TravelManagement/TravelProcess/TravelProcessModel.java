/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelProcess;

import org.paradyne.lib.ModelBase;

/**
 * @author aa0431 Lakkichand Golegaonkar
 * @date 30th Dec 2010
 * 
 */
public class TravelProcessModel extends ModelBase {

	public double getApproximateBudget(String travelId) {
		double approximateAmt = 0.0d;
		/**
		 * TRavel application ... travel.... accomodation
		 */

		/*
		 * travel Soruce Travel Destinations can eb multiple
		 * 
		 * for each travel source and destination find in booking - for the same
		 * source, destination, travel modeclass average of all amt. if avg is
		 * zero - get mion expense amt from travel modeclass table.
		 * 
		 * multiply by number of travelers
		 * 
		 * for each accomodation number of days stay - and multiply by 2500.
		 * number of days - difference between from date and to date.
		 * 
		 * 
		 * add travel cost + accomodation cost
		 */

		double totalTravelAccomAmt = 0.0;
		double totalTravelAmt = 0.0;
		double totalAccomAmt = 0.0;

		/**
		 * ######### THIS BLOCK IS FOR GETTING THE NO OF TRAVELLERS #######
		 */
		String noOfTravellerQuery = " SELECT COUNT(*) FROM TMS_APP_EMPDTL WHERE APPL_ID="
				+ travelId;
		Object[][] noOfTravellerObj = getSqlModel().getSingleResult(
				noOfTravellerQuery);

		String noOfGuestQuery = " SELECT COUNT(*) FROM TMS_APP_GUEST_DTL WHERE APPL_ID="
				+ travelId;
		Object[][] noOfGuestObj = getSqlModel().getSingleResult(noOfGuestQuery);
		/**
		 * ######### THIS BLOCK IS FOR CALCULATING JOURNEY AMOUNT PER TRAVELLER
		 * #######
		 */
		String journeyDtlQuery = " SELECT JOURNEY_FROM, JOURNEY_TO,  JOURNEY_MODECLASS FROM TMS_APP_JOURNEY_DTL  WHERE APPL_ID="
				+ travelId;
		Object[][] journeyDtlObj = getSqlModel().getSingleResult(
				journeyDtlQuery);

		if (journeyDtlObj != null && journeyDtlObj.length > 0) {
			for (int i = 0; i < journeyDtlObj.length; i++) {
				String journeyAvailQuery = " SELECT NVL(AVG(TMS_BOOK_TRAVEL_ACTUALCOST),0) FROM TMS_BOOK_TRAVEL "
						+ " WHERE UPPER(TMS_BOOK_SOURCE)='"
						+ String.valueOf(journeyDtlObj[i][0]).toUpperCase()
						+ "' AND "
						+ " UPPER(TMS_BOOK_DESTINATION)='"
						+ String.valueOf(journeyDtlObj[i][1]).toUpperCase()
						+ "' AND "
						+ " TMS_BOOK_MODECLASS= "
						+ String.valueOf(journeyDtlObj[i][2])
						+ "  AND TMS_BOOK_TRAVEL_ACTUALCOST>0 ";

				Object[][] journeyAvailObj = getSqlModel().getSingleResult(
						journeyAvailQuery);
				if (journeyAvailObj != null && journeyAvailObj.length > 0
						&& !String.valueOf(journeyAvailObj[0][0]).equals("0")) {
					totalTravelAmt += Double.parseDouble(String
							.valueOf(journeyAvailObj[0][0]));

				} else {
					String minExpenseQuery = " SELECT NVL(CLASS_MIN_EXPENSE,0) FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_ID="
							+ String.valueOf(journeyDtlObj[i][2]);
					Object[][] minExpenseObj = getSqlModel().getSingleResult(
							minExpenseQuery);
					totalTravelAmt += Double.parseDouble(String
							.valueOf(minExpenseObj[0][0]));
				}
			}
		}

		double no_of_trvler = 0.0d;

		/**
		 * ######### THIS BLOCK IS FOR CALCULATING TOTAL JOURNEY AMOUNT #######
		 */
		if (noOfTravellerObj != null && noOfTravellerObj.length > 0) {
			no_of_trvler = Double.parseDouble(String
					.valueOf(noOfTravellerObj[0][0]));
		}

		/**
		 * ######### THIS BLOCK IS FOR CALCULATING TOTAL JOURNEY AMOUNT #######
		 * FOR GUEST
		 */
		if (noOfGuestObj != null && noOfGuestObj.length > 0) {
			no_of_trvler += Double.parseDouble(String
					.valueOf(noOfGuestObj[0][0]));
		}
		System.out.println("totalTravelAmt  " + totalTravelAmt);

		totalTravelAmt = no_of_trvler * totalTravelAmt;

		/**
		 * ######### THIS BLOCK IS FOR CALCULATING ACCOMMODATION AMOUNT PER
		 * TRAVELLER #######
		 */

		String noOfDaysQuery = "SELECT DECODE(LODGE_TODATE-LODGE_FROMDATE,0,1,LODGE_TODATE-LODGE_FROMDATE) FROM TMS_APP_LODGE_DTL WHERE APPL_ID="
				+ travelId;
		Object[][] noOfDaysObj = getSqlModel().getSingleResult(noOfDaysQuery);
		if (noOfDaysObj != null && noOfDaysObj.length > 0) {
			for (int i = 0; i < noOfDaysObj.length; i++) {
				totalAccomAmt += Double.parseDouble(String
						.valueOf(noOfDaysObj[i][0])) * 2500;
			}
		}

		/**
		 * ######### THIS BLOCK IS FOR CALCULATING TOTAL ACCOMMODATION AMOUNT
		 * #######
		 */

		totalAccomAmt = totalAccomAmt * no_of_trvler;

		System.out.println("totalAccomAmt  " + totalAccomAmt);

		totalTravelAccomAmt = totalTravelAmt + totalAccomAmt;

		return totalTravelAccomAmt;
	}

	// Expendeture

	public double getActualExpenditure(String travelClaimId) {
		double actualExpendature = 0.0d;

		String lodgeQuery = "SELECT SUM(NVL(TMS_BOOK_LODGE.LODGE_BOOKING_AMT,0))  FROM TMS_BOOK_LODGE  "
				+ " WHERE TRAVEL_APPL_ID=(SELECT EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="
				+ travelClaimId + ") GROUP BY TRAVEL_APPL_ID ";
		Object[][] lodgeObj = getSqlModel().getSingleResult(lodgeQuery);

		if (lodgeObj != null && lodgeObj.length > 0) {
			actualExpendature += Double.parseDouble(String
					.valueOf(lodgeObj[0][0]));
		}

		String travelQuery = " SELECT SUM(NVL(TMS_BOOK_TRAVEL.TMS_BOOK_TRAVEL_ACTUALCOST,0)) FROM TMS_BOOK_TRAVEL"
				+ " WHERE TRAVEL_APPL_ID=(SELECT EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="
				+ travelClaimId + ")  GROUP BY TRAVEL_APPL_ID";
		Object[][] travelObj = getSqlModel().getSingleResult(travelQuery);

		if (travelObj != null && travelObj.length > 0) {
			actualExpendature += Double.parseDouble(String
					.valueOf(travelObj[0][0]));
		}
		String updateQuery = "UPDATE TMS_CLAIM_APPL SET EXP_ACTUAL_EXPENDITURE=NVL(EXP_TOT_EXPAMT,0) +"
				+ actualExpendature + " WHERE EXP_APPID=" + travelClaimId;
		getSqlModel().singleExecute(updateQuery);
		return actualExpendature;

	}

	public String[] getSplitedActualExpenditure(String travelClaimId) {
		
		String[] splitedAmt = new String[3];
		splitedAmt[0] = "0.0";
		splitedAmt[1] = "0.0";
		splitedAmt[2] = "0.0";

		String lodgeQuery = " SELECT SUM(NVL(TMS_BOOK_LODGE.LODGE_BOOKING_AMT,0.0))  FROM TMS_BOOK_LODGE "
				+ " WHERE TRAVEL_APPL_ID=(SELECT EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="
				+ travelClaimId + ") GROUP BY TRAVEL_APPL_ID ";
		Object[][] lodgeObj = getSqlModel().getSingleResult(lodgeQuery);

		if (lodgeObj != null && lodgeObj.length > 0) {
			splitedAmt[0] = String.valueOf(lodgeObj[0][0]);
		}

		String travelQuery = " SELECT SUM(NVL(TMS_BOOK_TRAVEL.TMS_BOOK_TRAVEL_ACTUALCOST,0.0)) FROM TMS_BOOK_TRAVEL"
				+ " WHERE TRAVEL_APPL_ID=(SELECT EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APPID="
				+ travelClaimId + ") GROUP BY TRAVEL_APPL_ID";
		Object[][] travelObj = getSqlModel().getSingleResult(travelQuery);

		if (travelObj != null && travelObj.length > 0) {
			splitedAmt[1] = String.valueOf(travelObj[0][0]);
		}
		String claimQuery = " SELECT  NVL(EXP_TOT_EXPAMT,0.0) FROM TMS_CLAIM_APPL "
				+ " WHERE EXP_APPID=" + travelClaimId;
		Object[][] claimObj = getSqlModel().getSingleResult(claimQuery);
		if (claimObj != null && claimObj.length > 0) {
			splitedAmt[2] = String.valueOf(claimObj[0][0]);
		}
		return splitedAmt;
	}

	/**
	 * This method is used to update the rating of hotel
	 * 
	 * @param hotelId
	 * @param rating
	 */
	public void updateHotelRating(String travelClaimId) {

		try {
			String selRatingQuery = "SELECT HOTEL_ID,ROUND(AVG(RATING_VALUE)) FROM TMS_RATING WHERE CLAIM_ID="
					+ travelClaimId
					+ " AND RATING_VALUE>0 AND HOTEL_ID IS NOT NULL GROUP BY HOTEL_ID ";

			Object[][] selRatingObj = getSqlModel().getSingleResult(
					selRatingQuery);

			if (selRatingObj != null && selRatingObj.length > 0) {

				for (int j = 0; j < selRatingObj.length; j++) {

					String prevRatingQuery = "SELECT NVL(HOTEL_RATING_COUNT,1),NVL(HOTEL_AVERAGE_RATING,0),NVL(HOTEL_RATING_COUNT,1)*NVL(HOTEL_AVERAGE_RATING,1) FROM HRMS_TRAVEL_HOTEL_MASTER WHERE HOTEL_ID="
							+ String.valueOf(selRatingObj[j][0]);
					Object[][] prevRatingObj = getSqlModel().getSingleResult(
							prevRatingQuery);
					if (prevRatingObj != null && prevRatingObj.length > 0) {
						double exactRating = 0.0d;
						try {
							exactRating = (Double.parseDouble(String
									.valueOf(selRatingObj[j][1])) + Double
									.parseDouble(String
											.valueOf(prevRatingObj[j][1])))
									/ (Double.parseDouble(String
											.valueOf(prevRatingObj[j][1])) + 1);
						} catch (Exception e) {
							// TODO: handle exception
						}
						String updateAvgQuery = "UPDATE HRMS_TRAVEL_HOTEL_MASTER SET HOTEL_AVERAGE_RATING="
								+ exactRating
								+ " ,HOTEL_RATING_COUNT=NVL(HOTEL_RATING_COUNT,0)+1 "
								+ " WHERE HOTEL_ID="
								+ String.valueOf(selRatingObj[j][0]);
						getSqlModel().singleExecute(updateAvgQuery);
					}
				}
			}
		} catch (Exception e) {

		}

	}

}
