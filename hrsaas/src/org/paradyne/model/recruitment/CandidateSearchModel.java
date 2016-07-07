/**
 * 
 */
package org.paradyne.model.recruitment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.CandidateSearch;
import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author shashikant
 * 
 */
public class CandidateSearchModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandidateSearchModel.class);

	/**
	 * @Method retrieveInitialData
	 * @Purpose to retrieve all the details which are needed at the time of page
	 *          loading
	 * @param bean
	 */
	public void retrieveInitialData(CandidateSearch bean) {
		logger.info("inside retrieveInitialData");

		retrieveCity(bean); // retrieve the city details
		retrieveState(bean); // retrieve the state details
		retrieveCountry(bean); // retrieve the country details
		retrieveFunctionalArea(bean); // retrieve the functional area details
		retrieveIndustryType(bean); // retrieve the industry type details
		retrieveQualification(bean); // retrieve the qualification details
		retrieveSpecialization(bean); // retrieve the specialization details
		retrieveSkillSet(bean); // retrieve the skill details
	}

	/**
	 * @Method retrieveCity
	 * @Purpose to retrieve city details and set city name on the form
	 * @param bean
	 */
	public void retrieveCity(CandidateSearch bean) {
		logger.info("inside retrieveCity");

		HashMap<Object, Object> cityMap = new HashMap<Object, Object>();

		Object[][] cityData = getSqlModel().getSingleResult(getQuery(1));

		if (cityData != null && cityData.length != 0) {
			for (int i = 0; i < cityData.length; i++) {
				cityMap.put(String.valueOf(cityData[i][0]), String.valueOf(
						cityData[i][1]).trim());
			}
			// sort the cityMap as per the city names
			cityMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(cityMap, null, true);

			bean.setCityMap(cityMap);
			bean.setCityFlag("true");
		} else {
			bean.setCityFlag("flase");
		}
		bean.setCity("");
	}

	/**
	 * @Method retrieveState
	 * @Purpose to retrieve state details and set state name on the form
	 * @param bean
	 */
	public void retrieveState(CandidateSearch bean) {
		logger.info("inside retrieveState");

		HashMap<Object, Object> StateMap = new HashMap<Object, Object>();

		Object[][] stateData = getSqlModel().getSingleResult(getQuery(2));

		if (stateData != null && stateData.length != 0) {
			for (int i = 0; i < stateData.length; i++) {
				StateMap.put(String.valueOf(stateData[i][0]), String.valueOf(
						stateData[i][1]).trim());
			}
			// sort the stateMap as per the state names
			StateMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(StateMap, null, true);

			bean.setStateMap(StateMap);
			bean.setStateFlag("true");
		} else {
			bean.setStateFlag("flase");
		}
		bean.setState("");
	}

	/**
	 * @Method retrieveCountry
	 * @Purpose to retrieve Country details and set Country name on the form
	 * @param bean
	 */
	public void retrieveCountry(CandidateSearch bean) {
		logger.info("inside retrieveCountry");

		HashMap<Object, Object> countryMap = new HashMap<Object, Object>();

		Object[][] countryData = getSqlModel().getSingleResult(getQuery(3));

		if (countryData != null && countryData.length != 0) {
			for (int i = 0; i < countryData.length; i++) {
				countryMap.put(String.valueOf(countryData[i][0]), String
						.valueOf(countryData[i][1]).trim());
			}
			// sort the countryMap as per the country names
			countryMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(countryMap, null, true);

			bean.setCountryMap(countryMap);
			bean.setCountryFlag("true");
		} else {
			bean.setCountryFlag("flase");
		}
		bean.setCountry("");
	}

	/**
	 * @Method retrieveFunctionalArea
	 * @Purpose to retrieve Functional Area details and set Functional Area name
	 *          on the form
	 * @param bean
	 */
	public void retrieveFunctionalArea(CandidateSearch bean) {
		logger.info("inside retrieveFunctionalArea");

		HashMap<Object, Object> functionalAreaMap = new HashMap<Object, Object>();

		Object[][] functionalAreaData = getSqlModel().getSingleResult(
				getQuery(4));

		if (functionalAreaData != null && functionalAreaData.length != 0) {
			for (int i = 0; i < functionalAreaData.length; i++) {
				functionalAreaMap.put(String.valueOf(functionalAreaData[i][0]),
						String.valueOf(functionalAreaData[i][1]).trim());
			}
			// sort the functionalAreaMap as per the functional Area names
			functionalAreaMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(functionalAreaMap, null, true);

			bean.setFunctionalAreaMap(functionalAreaMap);
			bean.setFunctionalAreaFlag("true");
		} else {
			bean.setFunctionalAreaFlag("flase");
		}
		bean.setFunctionalArea("");
	}

	/**
	 * @Method retrieveIndustryType
	 * @Purpose to retrieve Industry Type details and set Industry Type name on
	 *          the form
	 * @param bean
	 */
	public void retrieveIndustryType(CandidateSearch bean) {
		logger.info("inside retrieveIndustryType");

		HashMap<Object, Object> industryTypeMap = new HashMap<Object, Object>();

		Object[][] industryTypeData = getSqlModel()
				.getSingleResult(getQuery(5));

		if (industryTypeData != null && industryTypeData.length != 0) {
			for (int i = 0; i < industryTypeData.length; i++) {
				industryTypeMap.put(String.valueOf(industryTypeData[i][0]),
						String.valueOf(industryTypeData[i][1]).trim());
			}
			// sort the industryTypeMap as per the industry Type names
			industryTypeMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(industryTypeMap, null, true);

			bean.setIndustryTypeMap(industryTypeMap);
			bean.setIndustryFlag("true");
		} else {
			bean.setIndustryFlag("flase");
		}
		bean.setIndustryType("");
	}

	/**
	 * @Method retrieveQualification
	 * @Purpose to retrieve Qualification details and set Qualification name on
	 *          the form
	 * @param bean
	 */
	public void retrieveQualification(CandidateSearch bean) {
		logger.info("inside retrieveQualification");

		HashMap<Object, Object> qualificationMap = new HashMap<Object, Object>();

		Object[][] qualificationData = getSqlModel().getSingleResult(
				getQuery(6));

		if (qualificationData != null && qualificationData.length != 0) {
			for (int i = 0; i < qualificationData.length; i++) {
				qualificationMap.put(String.valueOf(qualificationData[i][0]),
						String.valueOf(qualificationData[i][1]).trim());
			}
			// sort the qualificationMap as per the qualification names
			qualificationMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(qualificationMap, null, true);

			bean.setQualificationMap(qualificationMap);
			bean.setQualificationFlag("true");
		} else {
			bean.setQualificationFlag("flase");
		}
		bean.setQualification("");
	}

	/**
	 * @Method retrieveSpecialization
	 * @Purpose to retrieve Specialization details and set Specialization name
	 *          on the form
	 * @param bean
	 */
	public void retrieveSpecialization(CandidateSearch bean) {
		logger.info("inside retrieveSpecialization");

		HashMap<Object, Object> specializationMap = new HashMap<Object, Object>();

		Object[][] specializationData = getSqlModel().getSingleResult(
				getQuery(7));

		if (specializationData != null && specializationData.length != 0) {
			for (int i = 0; i < specializationData.length; i++) {
				specializationMap.put(String.valueOf(specializationData[i][0]),
						String.valueOf(specializationData[i][1]).trim());
			}
			// sort the specializationMap as per the specialization names
			specializationMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(specializationMap, null, true);

			bean.setSpecializationMap(specializationMap);
			bean.setSpecializationFlag("true");
		} else {
			bean.setSpecializationFlag("flase");
		}
		bean.setSpecialization("");
	}

	/**
	 * @Method retrieveSkillSet
	 * @Purpose to retrieve Skill details and set Skill name on the form
	 * @param bean
	 */
	public void retrieveSkillSet(CandidateSearch bean) {
		logger.info("inside retrieveSkillSet");

		HashMap<Object, Object> skillSetMap = new HashMap<Object, Object>();

		Object[][] skillSetData = getSqlModel().getSingleResult(getQuery(8));

		if (skillSetData != null && skillSetData.length != 0) {
			for (int i = 0; i < skillSetData.length; i++) {
				skillSetMap.put(String.valueOf(skillSetData[i][0]), String
						.valueOf(skillSetData[i][1]).trim());

				// logger.info("key ["+i+") "+skillSetData[i][0]+" values
				// "+skillSetData[i][1]);
			}
			// sort the skillSetMap as per the skill names
			skillSetMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(skillSetMap, null, true);

			bean.setSkillSetMap(skillSetMap);
			bean.setSkillFlag("true");
		} else {
			bean.setSkillFlag("flase");
		}
		bean.setSkillSet("");
		// bean.setSkillSet(String.valueOf(skillSetData[0][0])+","+String.valueOf(skillSetData[1][0]));
	}

	/**
	 * @Method save
	 * @Purpose to save search criteria details
	 * @param bean
	 * @return boolean
	 */
	public boolean save(CandidateSearch bean) {
		logger.info("in save model");

		// get search details in ann array
		Object[][] insertSearchData = new Object[1][26];
		insertSearchData[0][0] = bean.getKeywordSearch(); // key word
		insertSearchData[0][1] = bean.getSearchCriteria(); // search criteria

		if (bean.getShortListed().equals("true")) {
			insertSearchData[0][2] = "Y"; // SERACH_IN_SHORT
		} else {
			insertSearchData[0][2] = "N"; // SERACH_IN_SHORT
		}
		if (bean.getRejected().equals("true")) {
			insertSearchData[0][3] = "Y"; // SERACH_IN_REJECT
		} else {
			insertSearchData[0][3] = "N"; // SERACH_IN_REJECT
		}
		if (bean.getNewResume().equals("true")) {
			insertSearchData[0][4] = "Y"; // SERACH_IN_NEW
		} else {
			insertSearchData[0][4] = "N"; // SERACH_IN_NEW
		}

		insertSearchData[0][5] = bean.getRequisitionCode(); // reqs code
		insertSearchData[0][6] = bean.getSourceOfResume(); // source of resume
		insertSearchData[0][7] = bean.getPositioningDate(); // resume
															// positioning date
		insertSearchData[0][8] = bean.getFromDate(); // from date
		insertSearchData[0][9] = bean.getToDate(); // to date
		insertSearchData[0][10] = bean.getMinExperience(); // minimum exp
		insertSearchData[0][11] = bean.getMaxExperience(); // maximum exp
		insertSearchData[0][12] = bean.getFirstName(); // first name
		insertSearchData[0][13] = bean.getLastName(); // last name
		insertSearchData[0][14] = bean.getGender(); // gender
		insertSearchData[0][15] = bean.getMaritalStatus(); // marital status
		insertSearchData[0][16] = bean.getCity(); // city
		insertSearchData[0][17] = bean.getState(); // state
		insertSearchData[0][18] = bean.getCountry(); // country
		insertSearchData[0][19] = bean.getFunctionalArea(); // functional area
		insertSearchData[0][20] = bean.getIndustryType(); // industry type
		insertSearchData[0][21] = bean.getQualification(); // qualification
		insertSearchData[0][22] = bean.getSpecialization(); // specialization
		insertSearchData[0][23] = bean.getSkillSet(); // skill
		insertSearchData[0][24] = bean.getSortOn(); // sort on

		// if(bean.getEnableSearch().equals("true")){
		insertSearchData[0][25] = "Y"; // enable CV
		// }
		// else{
		// insertSearchData [0][25] = "N";
		// }
		boolean result = getSqlModel().singleExecute(getQuery(9),
				insertSearchData);

		return result;
	}

	/**
	 * @Method save
	 * @Purpose to get the details of the saved search criteria
	 * @param bean
	 */
	public void showSearchData(CandidateSearch bean) {
		logger.info("in showSearchData model");

		Object[][] searchData = getSqlModel().getSingleResult(getQuery(10),
				new Object[] { bean.getSearchCode() });

		if (searchData != null && searchData.length != 0) {
			bean.setSearchCriteria(checkNull(String.valueOf(searchData[0][0]))); // search
																					// criteria

			if (String.valueOf(searchData[0][1]).equals("Y")) {
				bean.setShortListed("true"); // Short Listed
			} else {
				bean.setShortListed("false");// Short Listed
			}

			if (String.valueOf(searchData[0][2]).equals("Y")) {
				bean.setRejected("true"); // rejected
			} else {
				bean.setRejected("false");// rejected
			}

			if (String.valueOf(searchData[0][3]).equals("Y")) {
				bean.setNewResume("true");// new
			} else {
				bean.setNewResume("false");// new
			}

			if (String.valueOf(searchData[0][1]).equals("Y")
					&& String.valueOf(searchData[0][2]).equals("Y")
					&& String.valueOf(searchData[0][3]).equals("Y")) {
				bean.setShowAll("true");// show all
			} else {
				bean.setShowAll("false");// show all
			}

			bean.setSourceOfResume(checkNull(String.valueOf(searchData[0][4])));// source
																				// of
																				// resume
			bean
					.setPositioningDate(checkNull(String
							.valueOf(searchData[0][5])));// resume
															// positioning date
			bean.setFromDate(checkNull(String.valueOf(searchData[0][6])));// from
																			// date
			bean.setToDate(checkNull(String.valueOf(searchData[0][7])));// to
																		// date
			bean.setFirstName(checkNull(String.valueOf(searchData[0][8])));// first
																			// name
			bean.setLastName(checkNull(String.valueOf(searchData[0][9])));// last
																			// name
			bean.setGender(checkNull(String.valueOf(searchData[0][10])));// gender
			bean.setMaritalStatus(checkNull(String.valueOf(searchData[0][11])));// marital
																				// status
			bean.setCity(checkNull(String.valueOf(searchData[0][12])));// city
			bean.setState(checkNull(String.valueOf(searchData[0][13])));// state
			bean.setCountry(checkNull(String.valueOf(searchData[0][14])));// country
			bean
					.setFunctionalArea(checkNull(String
							.valueOf(searchData[0][15])));// functional area
			bean.setIndustryType(checkNull(String.valueOf(searchData[0][16])));// industry
																				// type
			bean.setQualification(checkNull(String.valueOf(searchData[0][17])));// qualification
			bean
					.setSpecialization(checkNull(String
							.valueOf(searchData[0][18])));// specialization
			bean.setSkillSet(checkNull(String.valueOf(searchData[0][19])));// skill
			bean.setSortOn(checkNull(String.valueOf(searchData[0][20])));// sort
																			// on

			if (String.valueOf(searchData[0][21]).equals("Y")) {
				bean.setEnableSearch("true");// enable CV
			} else {
				bean.setEnableSearch("false");// enable CV
			}

			bean.setMinExperience(checkNull(String.valueOf(searchData[0][22])));// min
																				// exp
			bean.setMaxExperience(checkNull(String.valueOf(searchData[0][23])));// max
																				// exp

			bean.setSearchFlag("true");
		}
	}

	/**
	 * @Method save
	 * @Purpose to search the cand and display cand details in list as per the
	 *          selected search criteria
	 * @param bean
	 */

	public String getQuery(CandidateSearch bean) {
		String query = "SELECT DISTINCT HRMS_REC_CAND_DATABANK.CAND_CODE, CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME, "
				+ "NVL(CAND_EXP_YEAR, 0)||' Year '||NVL(CAND_EXP_MONTH, 0)||' Month', "
				+ "TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'), CAND_CURR_CTC, DECODE(CAND_GENDER, 'M', 'Male', 'F', 'Female','O','Other'), "
				+ "CAND_SHORT_STATUS,CAND_RESUME, CAND_EXP_YEAR, CAND_EXP_MONTH,CAND_POSTING_DATE, "
				+ "HRMS_REC_REQS_HDR.REQS_NAME, HRMS_RANK.RANK_NAME, "
				+ "HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "DECODE(CAND_REF_TYPE, 'A', 'All', 'E', 'Employee Referral','C','Job Consultant', 'O','On Line Application','D','Direct') "
				+ "FROM HRMS_REC_CAND_DATABANK "
				+ "LEFT JOIN HRMS_REC_CAND_POSTING ON (HRMS_REC_CAND_POSTING.POST_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE) "
				+ "INNER JOIN  HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE =  HRMS_REC_CAND_POSTING.POST_REQS_CODE)" 
				+ "LEFT JOIN  HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_JOB_DESC_CODE =  HRMS_RANK.RANK_ID)"
				
				+ "LEFT JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_CAND_DATABANK.CAND_POSTRESUME_EMPID)" 
				+ "LEFT JOIN HRMS_REC_CD_SKILLFUNCDTL SKILL ON (SKILL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE AND SKILL.CAND_FIELD_TYPE = 'S') "
				+ "LEFT JOIN HRMS_REC_CD_SKILLFUNCDTL FUN ON (FUN.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE AND FUN.CAND_FIELD_TYPE = 'F') "
				+ "LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON (HRMS_REC_CD_ADDRESSDTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
				+ "AND CAND_ADD_TYPE = 'C') "
				+ "LEFT JOIN HRMS_REC_CD_QUADTL ON (HRMS_REC_CD_QUADTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE) "
				+ "LEFT JOIN HRMS_REC_CD_EXPDTL ON (HRMS_REC_CD_EXPDTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE) "
				+ "WHERE 1 = 1 AND CAND_SHORT_STATUS NOT IN ('E')";

		// search in
		if (bean.getShortListed().equals("true")
				&& bean.getRejected().equals("true")
				&& bean.getNewResume().equals("true")) {
			query += " AND CAND_SHORT_STATUS IN ( 'S', 'R', 'N') ";// search
																	// all cands
																	// whose
																	// resume
																	// stauts is
																	// selected,
																	// rejected
																	// or new
		} else if (bean.getShortListed().equals("true")
				&& bean.getRejected().equals("true")) {
			query += " AND CAND_SHORT_STATUS IN ( 'S', 'R') "; // search only
																// those cands
																// whose resume
																// stauts is
																// selected or
																// rejected
		} else if (bean.getRejected().equals("true")
				&& bean.getNewResume().equals("true")) {
			query += " AND CAND_SHORT_STATUS IN ( 'R', 'N') "; // search only
																// those cands
																// whose resume
																// stauts is
																// rejected or
																// new
		} else if (bean.getShortListed().equals("true")
				&& bean.getNewResume().equals("true")) {
			query += " AND CAND_SHORT_STATUS IN ('S', 'N') "; // search only
																// those cands
																// whose resume
																// stauts is
																// selected or
																// new
		} else if (bean.getShortListed().equals("true")) {
			query += " AND CAND_SHORT_STATUS = 'S'";// search only those cands
													// whose resume stauts is
													// selected
		} else if (bean.getRejected().equals("true")) {
			query += " AND CAND_SHORT_STATUS = 'R'";// search only those cands
													// whose resume stauts is
													// rejected
		} else if (bean.getNewResume().equals("true")) {
			query += " AND CAND_SHORT_STATUS = 'N'";// search only those cands
													// whose resume stauts is
													// new
		}

		// requisition code
		/*
		 * if(!bean.getRequisitionCode().equals("")){ query += " AND
		 * POST_REQS_CODE = "+bean.getRequisitionCode(); }
		 */

		// source of resume
		if (bean.getSourceOfResume().equals("A")) {
			query += " AND CAND_REF_TYPE IN ( 'A','E','C' ,'O','D')  ";// search
																		// those
																		// cands
																		// whose
																		// source
																		// of
																		// resume
																		// stands
																		// in
																		// any
																		// category
		} else {
			query += " AND CAND_REF_TYPE = '" + bean.getSourceOfResume() + "' ";// search
																				// those
																				// cands
																				// whose
																				// source
																				// of
																				// resume
																				// stands
																				// in
																				// selected
																				// category
		}

		// resume positioning date
		if (bean.getPositioningDate().equals("O")) {
			// search those cands whose resume positioning date is equal to
			// fromDate
			query += " AND CAND_POSTING_DATE = TO_DATE('" + bean.getFromDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getPositioningDate().equals("B")) {
			// search those cands whose resume positioning date before fromDate
			query += " AND CAND_POSTING_DATE < TO_DATE('" + bean.getFromDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getPositioningDate().equals("A")) {
			// search those cands whose resume positioning date after fromDate
			query += " AND CAND_POSTING_DATE > TO_DATE('" + bean.getFromDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getPositioningDate().equals("OB")) {
			// search those cands whose resume positioning date on or before
			// fromDate
			query += " AND CAND_POSTING_DATE <= TO_DATE('" + bean.getFromDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getPositioningDate().equals("OA")) {
			// search those cands whose resume positioning date on or after
			// fromDate
			query += " AND CAND_POSTING_DATE >= TO_DATE('" + bean.getFromDate()
					+ "', 'DD-MM-YYYY')";
		} else if (bean.getPositioningDate().equals("F")) {
			// search those cands whose resume positioning date between fromDate
			// and toDate
			query += " AND (CAND_POSTING_DATE BETWEEN TO_DATE('"
					+ bean.getFromDate() + "', 'DD-MM-YYYY') ";
			if (!bean.getToDate().equals("")) { // bean.getToDate()!=null &&
				query += "AND TO_DATE('" + bean.getToDate()
						+ "', 'DD-MM-YYYY')) ";
			} else {
				query += "AND TO_DATE(to_char(sysdate,'dd-mm-yyyy'),'DD-MM-YYYY')) ";
			}
		}

		// Experience
		if (!bean.getMinExperience().equals("")
				&& !bean.getMaxExperience().equals("")) {
			double fullMinYear = Double.parseDouble(bean.getMinExperience());
			int minYear = (int) fullMinYear;
			int minMonth = (int) ((fullMinYear - minYear) * 12);

			double fullMaxYear = Double.parseDouble(bean.getMaxExperience());
			int maxYear = (int) fullMaxYear;
			int maxMonth = (int) ((fullMaxYear - maxYear) * 12);

			int minTotalMonth = (minYear * 12) + minMonth;
			int maxTotalMonth = (maxYear * 12) + maxMonth;
			// search those cands whose resume experience between minExp and
			// maxExp
			query += " AND ((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH) ) >="
					+ minTotalMonth + " AND "
					+ "((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH) ) <="
					+ maxTotalMonth;
		} else if (!bean.getMinExperience().equals("")) {

			double fullMinYear = Double.parseDouble(bean.getMinExperience());
			int minYear = (int) fullMinYear;
			int minMonth = (int) ((fullMinYear - minYear) * 12);
			int minTotalMonth = (minYear * 12) + minMonth;

			// search those cands whose resume experience is greater or equal to
			// minExp
			query += " AND ((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH) ) >="
					+ minTotalMonth;
		} else if (!bean.getMaxExperience().equals("")) {

			double fullMaxYear = Double.parseDouble(bean.getMaxExperience());
			int maxYear = (int) fullMaxYear;
			int maxMonth = (int) ((fullMaxYear - maxYear) * 12);
			int maxTotalMonth = (maxYear * 12) + maxMonth;

			// search those cands whose resume experience is less or equal to
			// maxExp
			query += " AND ((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH)) <="
					+ maxTotalMonth;

		}

		// first name
		if (!bean.getFirstName().equals("")) {
			query += " AND UPPER(CAND_FIRST_NAME) like '%"
					+ bean.getFirstName().toUpperCase() + "%'";
		}

		// last name
		if (!bean.getLastName().equals("")) {
			query += " AND UPPER(CAND_LAST_NAME) like '%"
					+ bean.getLastName().toUpperCase() + "%'";
		}

		// gender
		if (bean.getGender().equals("B")) {
			query += " AND ((CAND_GENDER = 'M') OR (CAND_GENDER = 'F')  OR (CAND_GENDER = 'O'))";
		} else if (!bean.getGender().equals("")) {
			query += " AND CAND_GENDER = '" + bean.getGender() + "'";
		}

		// marital status
		if (!bean.getMaritalStatus().equals("")) {
			query += " AND CAND_MART_STATUS = '" + bean.getMaritalStatus()
					+ "'";
		}

		// location
		if (!bean.getCity().equals("") && !bean.getState().equals("")
				&& !bean.getCountry().equals("")) {
			query += " AND ((CAND_ADD_CITY IN('" + bean.getCity()
					+ "') OR CAND_ADD_STATE IN(" + bean.getState() + ")"
					+ " OR CAND_ADD_COUNTRY IN(" + bean.getCountry()
					+ ")) AND CAND_ADD_TYPE = 'C')";
		} else if (!bean.getCity().equals("") && !bean.getState().equals("")) {
			query += " AND ((CAND_ADD_CITY IN('" + bean.getCity()
					+ "') OR CAND_ADD_STATE IN(" + bean.getState() + ")"
					+ " ) AND CAND_ADD_TYPE = 'C')";
		} else if (!bean.getCity().equals("") && !bean.getCountry().equals("")) {
			query += " AND ((CAND_ADD_CITY IN('" + bean.getCity()
					+ "') OR CAND_ADD_COUNTRY IN(" + bean.getCountry() + "))"
					+ " AND CAND_ADD_TYPE = 'C')";
		} else if (!bean.getState().equals("") && !bean.getCountry().equals("")) {
			query += " AND ((CAND_ADD_STATE IN(" + bean.getState() + ")"
					+ " OR CAND_ADD_COUNTRY IN(" + bean.getCountry()
					+ ")) AND CAND_ADD_TYPE = 'C')";
		} else if (!bean.getCity().equals("")) {
			query += " AND (CAND_ADD_CITY IN('" + bean.getCity()
					+ "') AND CAND_ADD_TYPE = 'C')";
		} else if (!bean.getState().equals("")) {
			query += " AND (CAND_ADD_STATE IN(" + bean.getState()
					+ ")  AND CAND_ADD_TYPE = 'C')";
		} else if (!bean.getCountry().equals("")) {
			query += " AND (CAND_ADD_COUNTRY IN(" + bean.getCountry()
					+ ")  AND CAND_ADD_TYPE = 'C')";
		}

		// functional Area or skill code
		/*
		 * if(!bean.getFunctionalArea().equals("") &&
		 * !bean.getSkillSet().equals("")){ query += " AND ((FUN.CAND_SF_CODE
		 * IN("+bean.getFunctionalArea()+") AND CAND_FIELD_TYPE = 'F') " +"AND
		 * (SKILL.CAND_SF_CODE IN("+bean.getSkillSet()+") AND CAND_FIELD_TYPE =
		 * 'S'))"; }
		 */

		// functional Area
		if (!bean.getFunctionalArea().equals("")) {
			query += " AND FUN.CAND_SF_CODE IN(" + bean.getFunctionalArea()
					+ ") ";
		}
		// skill code
		if (!bean.getSkillSet().equals("")) {
			query += " AND SKILL.CAND_SF_CODE IN(" + bean.getSkillSet() + ") ";
		}

		// industry type
		if (!bean.getIndustryType().equals("")) {
			query += " AND CAND_INDUS_TYPE IN(" + bean.getIndustryType() + ")";
		}

		// qualification and specialization
		/*
		 * if(!bean.getQualification().equals("") &&
		 * !bean.getSpecialization().equals("")){ query += " AND ((CAND_QUA_CODE
		 * IN("+bean.getQualification()+")) " +"AND (CAND_SPEC_CODE
		 * IN("+bean.getSpecialization()+")))"; }
		 */

		// qualification
		if (!bean.getQualification().equals("")) {
			query += " AND CAND_QUA_CODE IN(" + bean.getQualification() + ")";
		}
		// specialization
		if (!bean.getSpecialization().equals("")) {
			query += " AND CAND_SPEC_CODE IN(" + bean.getSpecialization() + ")";
		}

		// if reqs code is selected then search the cands as per the position
		// for that reqs
		if (!bean.getRequisitionCode().equals("")) {
			/*
			 * String requisitionCode = "";
			 * 
			 * //select all the reqs having same post as selected reqs String
			 * reqQuery = "SELECT REQS_CODE FROM HRMS_REC_REQS_HDR WHERE
			 * REQS_POSITION = "+bean.getPositionCode();
			 * 
			 * Object [][] reqCode = getSqlModel().getSingleResult(reqQuery);
			 * 
			 * if(reqCode != null && reqCode.length != 0){ for (int i = 0; i <
			 * reqCode.length; i++) { requisitionCode +=
			 * checkNull(String.valueOf(reqCode[i][0]))+","; } requisitionCode =
			 * requisitionCode.substring(0, requisitionCode.length()-1); } else
			 * requisitionCode = bean.getRequisitionCode();
			 * 
			 * query += " AND POST_REQS_CODE IN ("+requisitionCode+") AND
			 * HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN " +"(SELECT
			 * RESUME_CAND_CODE " +"FROM HRMS_REC_RESUME_BANK WHERE
			 * RESUME_REQS_CODE = "+bean.getRequisitionCode()+" " +"UNION SELECT
			 * DISTINCT EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL " +"WHERE
			 * EVAL_REQS_CODE = "+bean.getRequisitionCode()+" " +"UNION SELECT
			 * DISTINCT OFFER_CAND_CODE FROM HRMS_REC_OFFER " +"WHERE
			 * OFFER_REQS_CODE = "+bean.getRequisitionCode()+" " +"UNION SELECT
			 * DISTINCT APPOINT_CAND_CODE FROM HRMS_REC_APPOINT " +"WHERE
			 * APPOINT_REQS_CODE = "+bean.getRequisitionCode()+") ";
			 */
			/*query += "AND HRMS_REC_CAND_DATABANK.CAND_POSTRESUME_REQUISITIONID = "
					+ bean.getRequisitionCode();*/
			
			query += "AND HRMS_REC_CAND_POSTING.POST_REQS_CODE = "
				+ bean.getRequisitionCode();
		}

		// sort on
		if (bean.getSortOn().equals("RFA")) {
			// sort as per resume posting date in ascending order
			// query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY')
			// ASC";
			query += " ORDER BY TO_DATE(CAND_POSTING_DATE, 'DD-MM-YYYY') ASC";
		} else if (bean.getSortOn().equals("RFD")) {
			// sort as per resume posting date in descending order
			// query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY')
			// DESC";
			query += " ORDER BY TO_DATE(CAND_POSTING_DATE, 'DD-MM-YYYY') DESC";
		} else if (bean.getSortOn().equals("EA")) {
			// sort as per experience in ascending order
			// query += " ORDER BY CAND_EXP_YEAR ASC , CAND_EXP_MONTH ASC";
			query += " ORDER BY NVL(CAND_EXP_YEAR,0) , NVL(CAND_EXP_MONTH,0) ASC";
		} else if (bean.getSortOn().equals("ED")) {
			// sort as per experience in descending order
			// query += " ORDER BY CAND_EXP_YEAR DESC , CAND_EXP_MONTH DESC";
			query += " ORDER BY  NVL(CAND_EXP_YEAR,0) DESC ,NVL(CAND_EXP_MONTH,0) DESC  ";
		} else if (bean.getSortOn().equals("CA")) {
			// sort as per Current CTC in ascending order
			query += " ORDER BY CAND_CURR_CTC ASC";
		} else if (bean.getSortOn().equals("CD")) {
			// sort as per Current CTC in descending order
			query += " ORDER BY CAND_CURR_CTC DESC";
		} else {
			// sort as per resume posting date in descending order
			// query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY')
			// DESC";
			query += " ORDER BY TO_DATE(CAND_POSTING_DATE, 'DD-MM-YYYY') DESC";
		}

		return query;

	}

	public void searchCandidate(CandidateSearch bean) {

			String query =getQuery(bean);
			
		ArrayList<Object> candidateList = new ArrayList<Object>();
		Object[][] candidateData = getSqlModel().getSingleResult(query);

		// set the cand details in the list
		if (candidateData != null && candidateData.length != 0) {
			for (int i = 0; i < candidateData.length; i++) {
				CandidateSearch bean1 = new CandidateSearch();

				bean1.setCandidateCode(checkNull(String
						.valueOf(candidateData[i][0]))); // cand code
				bean1.setCandidateName(checkNull(String
						.valueOf(candidateData[i][1])));// cand name
				bean1.setCandExperience(checkNull(String
						.valueOf(candidateData[i][2])));// exp
				bean1.setPostedDate(checkNull(String
						.valueOf(candidateData[i][3])));// resume posted date
				bean1.setCtc(checkNull(String.valueOf(candidateData[i][4])));// current
																				// CTC
				bean1.setCandGender(checkNull(String
						.valueOf(candidateData[i][5])));// gender
				bean1.setStatus(checkNull(String.valueOf(candidateData[i][6])));// short
																				// listing
																				// status
				bean1.setResumeName(checkNull(String
						.valueOf(candidateData[i][7])));// resume

				candidateList.add(bean1);
			}
			bean.setCandidateList(candidateList);
			bean.setNoData("false");
		} else {
			bean.setNoData("true");
		}
	}

	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result
	 *            value of the data
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	/**
	 * @Method update
	 * @Purpose to update the search criteria
	 * @param bean
	 * @return boolean
	 */
	public boolean update(CandidateSearch bean) {
		// TODO Auto-generated method stub
		logger.info("in update model");

		Object[][] updateSearchData = new Object[1][27];
		updateSearchData[0][0] = bean.getKeywordSearch();
		updateSearchData[0][1] = bean.getSearchCriteria();

		if (bean.getShortListed().equals("true")) {
			updateSearchData[0][2] = "Y"; // SERACH_IN_SHORT
		} else {
			updateSearchData[0][2] = "N"; // SERACH_IN_SHORT
		}
		if (bean.getRejected().equals("true")) {
			updateSearchData[0][3] = "Y"; // SERACH_IN_REJECT
		} else {
			updateSearchData[0][3] = "N"; // SERACH_IN_REJECT
		}
		if (bean.getNewResume().equals("true")) {
			updateSearchData[0][4] = "Y"; // SERACH_IN_NEW
		} else {
			updateSearchData[0][4] = "N"; // SERACH_IN_NEW
		}

		updateSearchData[0][5] = bean.getRequisitionCode(); // reqs code
		updateSearchData[0][6] = bean.getSourceOfResume(); // source of resume
		updateSearchData[0][7] = bean.getPositioningDate(); // resume
															// positioning date
		updateSearchData[0][8] = bean.getFromDate(); // from date
		updateSearchData[0][9] = bean.getToDate(); // to date
		updateSearchData[0][10] = bean.getMinExperience(); // minExp
		updateSearchData[0][11] = bean.getMaxExperience(); // maxExp
		updateSearchData[0][12] = bean.getFirstName(); // first name
		updateSearchData[0][13] = bean.getLastName(); // last name
		updateSearchData[0][14] = bean.getGender(); // gender
		updateSearchData[0][15] = bean.getMaritalStatus(); // marital status
		updateSearchData[0][16] = bean.getCity(); // city
		updateSearchData[0][17] = bean.getState(); // state
		updateSearchData[0][18] = bean.getCountry(); // country
		updateSearchData[0][19] = bean.getFunctionalArea(); // functional area
		updateSearchData[0][20] = bean.getIndustryType(); // industry type
		updateSearchData[0][21] = bean.getQualification(); // qualification
		updateSearchData[0][22] = bean.getSpecialization(); // specialization
		updateSearchData[0][23] = bean.getSkillSet(); // skill
		updateSearchData[0][24] = bean.getSortOn(); // sort on

		// if(bean.getEnableSearch().equals("true")){
		updateSearchData[0][25] = "Y"; // enable CV
		// }
		// else{
		// updateSearchData [0][25] = "N";
		// }

		updateSearchData[0][26] = bean.getSearchCode(); // search code

		boolean result = getSqlModel().singleExecute(getQuery(11),
				updateSearchData);

		return result;
	}

	public void requisitionData(CandidateSearch candSearch) {
		// TODO Auto-generated method stub

		try {
			String qur = "SELECT  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,REQS_CODE, REQS_HIRING_MANAGER FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER)  where   REQS_CODE="
					+ candSearch.getRequisitionCode();
			Object[][] requData = getSqlModel().getSingleResult(qur);
			if (requData != null && requData.length > 0) {
				candSearch
						.setHiringManager("" + String.valueOf(requData[0][0]));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void getReport(CandidateSearch candSearch,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {

		try {
			String title = " Candidate Search Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Candidate Search Report");
			rds.setReportName(title);
			rds.setPageSize("landscape");
			// System.out.println("bean.getReportType() :
			// "+bean.getReportType());
			rds.setReportType("Xls");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);

			// For Report heading
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);

			/*String query = " SELECT DISTINCT HRMS_REC_CAND_DATABANK.CAND_CODE, CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
					+ "	NVL(CAND_EXP_YEAR, 0)||' Year '||NVL(CAND_EXP_MONTH, 0)||' Month',"
					+ "		TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'), CAND_CURR_CTC, DECODE(CAND_GENDER, 'M', 'Male', 'F', 'Female','O','Other'),"
					+ "		DECODE(CAND_SHORT_STATUS,'S','ShortList','R','Rejected','N','New Resume'),CAND_RESUME, CAND_EXP_YEAR, CAND_EXP_MONTH , TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY')"
					+ "		FROM HRMS_REC_CAND_DATABANK"
					+ "		LEFT JOIN HRMS_REC_CAND_POSTING ON (HRMS_REC_CAND_POSTING.POST_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ "		LEFT JOIN HRMS_REC_CD_SKILLFUNCDTL SKILL ON (SKILL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE AND SKILL.CAND_FIELD_TYPE = 'S')"
					+ "		LEFT JOIN HRMS_REC_CD_SKILLFUNCDTL FUN ON (FUN.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE AND FUN.CAND_FIELD_TYPE = 'F')"
					+ "		LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON (HRMS_REC_CD_ADDRESSDTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE"
					+ "		AND CAND_ADD_TYPE = 'C')"
					+ "		LEFT JOIN HRMS_REC_CD_QUADTL ON (HRMS_REC_CD_QUADTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE) "
					+ "		LEFT JOIN HRMS_REC_CD_EXPDTL ON (HRMS_REC_CD_EXPDTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE) "
					+ "		WHERE 1 = 1	";

			// search in
			if (candSearch.getShortListed().equals("true")
					&& candSearch.getRejected().equals("true")
					&& candSearch.getNewResume().equals("true")) {
				query += " AND CAND_SHORT_STATUS IN ( 'S', 'R', 'N') ";// search
																		// all
																		// cands
																		// whose
																		// resume
																		// stauts
																		// is
																		// selected,
																		// rejected
																		// or
																		// new
			} else if (candSearch.getShortListed().equals("true")
					&& candSearch.getRejected().equals("true")) {
				query += " AND CAND_SHORT_STATUS IN ( 'S', 'R') "; // search
																	// only
																	// those
																	// cands
																	// whose
																	// resume
																	// stauts is
																	// selected
																	// or
																	// rejected
			} else if (candSearch.getRejected().equals("true")
					&& candSearch.getNewResume().equals("true")) {
				query += " AND CAND_SHORT_STATUS IN ( 'R', 'N') "; // search
																	// only
																	// those
																	// cands
																	// whose
																	// resume
																	// stauts is
																	// rejected
																	// or new
			} else if (candSearch.getShortListed().equals("true")
					&& candSearch.getNewResume().equals("true")) {
				query += " AND CAND_SHORT_STATUS IN ('S', 'N') "; // search
																	// only
																	// those
																	// cands
																	// whose
																	// resume
																	// stauts is
																	// selected
																	// or new
			} else if (candSearch.getShortListed().equals("true")) {
				query += " AND CAND_SHORT_STATUS = 'S'";// search only those
														// cands whose resume
														// stauts is selected
			} else if (candSearch.getRejected().equals("true")) {
				query += " AND CAND_SHORT_STATUS = 'R'";// search only those
														// cands whose resume
														// stauts is rejected
			} else if (candSearch.getNewResume().equals("true")) {
				query += " AND CAND_SHORT_STATUS = 'N'";// search only those
														// cands whose resume
														// stauts is new
			}

			// requisition code
			
			 * if(!candSearch.getRequisitionCode().equals("")){ query += " AND
			 * POST_REQS_CODE = "+candSearch.getRequisitionCode(); }
			 

			// source of resume
			if (candSearch.getSourceOfResume().equals("A")) {
				query += " AND CAND_REF_TYPE IN ( 'A','E','C' ,'O','D')  ";// search
																			// those
																			// cands
																			// whose
																			// source
																			// of
																			// resume
																			// stands
																			// in
																			// any
																			// category
			} else {
				query += " AND CAND_REF_TYPE = '"
						+ candSearch.getSourceOfResume() + "' ";// search those
																// cands whose
																// source of
																// resume stands
																// in selected
																// category
			}

			// resume positioning date
			if (candSearch.getPositioningDate().equals("O")) {
				// search those cands whose resume positioning date is equal to
				// fromDate
				query += " AND CAND_POSTING_DATE = TO_DATE('"
						+ candSearch.getFromDate() + "', 'DD-MM-YYYY')";
			} else if (candSearch.getPositioningDate().equals("B")) {
				// search those cands whose resume positioning date before
				// fromDate
				query += " AND CAND_POSTING_DATE < TO_DATE('"
						+ candSearch.getFromDate() + "', 'DD-MM-YYYY')";
			} else if (candSearch.getPositioningDate().equals("A")) {
				// search those cands whose resume positioning date after
				// fromDate
				query += " AND CAND_POSTING_DATE > TO_DATE('"
						+ candSearch.getFromDate() + "', 'DD-MM-YYYY')";
			} else if (candSearch.getPositioningDate().equals("OB")) {
				// search those cands whose resume positioning date on or before
				// fromDate
				query += " AND CAND_POSTING_DATE <= TO_DATE('"
						+ candSearch.getFromDate() + "', 'DD-MM-YYYY')";
			} else if (candSearch.getPositioningDate().equals("OA")) {
				// search those cands whose resume positioning date on or after
				// fromDate
				query += " AND CAND_POSTING_DATE >= TO_DATE('"
						+ candSearch.getFromDate() + "', 'DD-MM-YYYY')";
			} else if (candSearch.getPositioningDate().equals("F")) {
				// search those cands whose resume positioning date between
				// fromDate and toDate
				query += " AND (CAND_POSTING_DATE BETWEEN TO_DATE('"
						+ candSearch.getFromDate() + "', 'DD-MM-YYYY') ";
				if (!candSearch.getToDate().equals("")) { // candSearch.getToDate()!=null
															// &&
					query += "AND TO_DATE('" + candSearch.getToDate()
							+ "', 'DD-MM-YYYY')) ";
				} else {
					query += "AND TO_DATE(to_char(sysdate,'dd-mm-yyyy'),'DD-MM-YYYY')) ";
				}
			}

			// Experience
			if (!candSearch.getMinExperience().equals("")
					&& !candSearch.getMaxExperience().equals("")) {
				double fullMinYear = Double.parseDouble(candSearch
						.getMinExperience());
				int minYear = (int) fullMinYear;
				int minMonth = (int) ((fullMinYear - minYear) * 12);

				double fullMaxYear = Double.parseDouble(candSearch
						.getMaxExperience());
				int maxYear = (int) fullMaxYear;
				int maxMonth = (int) ((fullMaxYear - maxYear) * 12);

				int minTotalMonth = (minYear * 12) + minMonth;
				int maxTotalMonth = (maxYear * 12) + maxMonth;
				// search those cands whose resume experience between minExp and
				// maxExp
				query += " AND ((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH) ) >="
						+ minTotalMonth
						+ " AND "
						+ "((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH) ) <="
						+ maxTotalMonth;
			} else if (!candSearch.getMinExperience().equals("")) {

				double fullMinYear = Double.parseDouble(candSearch
						.getMinExperience());
				int minYear = (int) fullMinYear;
				int minMonth = (int) ((fullMinYear - minYear) * 12);
				int minTotalMonth = (minYear * 12) + minMonth;

				// search those cands whose resume experience is greater or
				// equal to minExp
				query += " AND ((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH) ) >="
						+ minTotalMonth;
			} else if (!candSearch.getMaxExperience().equals("")) {

				double fullMaxYear = Double.parseDouble(candSearch
						.getMaxExperience());
				int maxYear = (int) fullMaxYear;
				int maxMonth = (int) ((fullMaxYear - maxYear) * 12);
				int maxTotalMonth = (maxYear * 12) + maxMonth;

				// search those cands whose resume experience is less or equal
				// to maxExp
				query += " AND ((NVL(CAND_EXP_YEAR, 0)*12)+(CAND_EXP_MONTH)) <="
						+ maxTotalMonth;

			}

			// first name
			if (!candSearch.getFirstName().equals("")) {
				query += " AND UPPER(CAND_FIRST_NAME) like '%"
						+ candSearch.getFirstName().toUpperCase() + "%'";
			}

			// last name
			if (!candSearch.getLastName().equals("")) {
				query += " AND UPPER(CAND_LAST_NAME) like '%"
						+ candSearch.getLastName().toUpperCase() + "%'";
			}

			// gender
			if (candSearch.getGender().equals("B")) {
				query += " AND ((CAND_GENDER = 'M') OR (CAND_GENDER = 'F')  OR (CAND_GENDER = 'O'))";
			} else if (!candSearch.getGender().equals("")) {
				query += " AND CAND_GENDER = '" + candSearch.getGender() + "'";
			}

			// marital status
			if (!candSearch.getMaritalStatus().equals("")) {
				query += " AND CAND_MART_STATUS = '"
						+ candSearch.getMaritalStatus() + "'";
			}

			// location
			if (!candSearch.getCity().equals("")
					&& !candSearch.getState().equals("")
					&& !candSearch.getCountry().equals("")) {
				query += " AND ((CAND_ADD_CITY IN('" + candSearch.getCity()
						+ "') OR CAND_ADD_STATE IN(" + candSearch.getState()
						+ ")" + " OR CAND_ADD_COUNTRY IN("
						+ candSearch.getCountry()
						+ ")) AND CAND_ADD_TYPE = 'C')";
			} else if (!candSearch.getCity().equals("")
					&& !candSearch.getState().equals("")) {
				query += " AND ((CAND_ADD_CITY IN('" + candSearch.getCity()
						+ "') OR CAND_ADD_STATE IN(" + candSearch.getState()
						+ ")" + " ) AND CAND_ADD_TYPE = 'C')";
			} else if (!candSearch.getCity().equals("")
					&& !candSearch.getCountry().equals("")) {
				query += " AND ((CAND_ADD_CITY IN('" + candSearch.getCity()
						+ "') OR CAND_ADD_COUNTRY IN("
						+ candSearch.getCountry() + "))"
						+ " AND CAND_ADD_TYPE = 'C')";
			} else if (!candSearch.getState().equals("")
					&& !candSearch.getCountry().equals("")) {
				query += " AND ((CAND_ADD_STATE IN(" + candSearch.getState()
						+ ")" + " OR CAND_ADD_COUNTRY IN("
						+ candSearch.getCountry()
						+ ")) AND CAND_ADD_TYPE = 'C')";
			} else if (!candSearch.getCity().equals("")) {
				query += " AND (CAND_ADD_CITY IN('" + candSearch.getCity()
						+ "') AND CAND_ADD_TYPE = 'C')";
			} else if (!candSearch.getState().equals("")) {
				query += " AND (CAND_ADD_STATE IN(" + candSearch.getState()
						+ ")  AND CAND_ADD_TYPE = 'C')";
			} else if (!candSearch.getCountry().equals("")) {
				query += " AND (CAND_ADD_COUNTRY IN(" + candSearch.getCountry()
						+ ")  AND CAND_ADD_TYPE = 'C')";
			}

			// functional Area or skill code
			
			 * if(!candSearch.getFunctionalArea().equals("") &&
			 * !candSearch.getSkillSet().equals("")){ query += " AND
			 * ((FUN.CAND_SF_CODE IN("+candSearch.getFunctionalArea()+") AND
			 * CAND_FIELD_TYPE = 'F') " +"AND (SKILL.CAND_SF_CODE
			 * IN("+candSearch.getSkillSet()+") AND CAND_FIELD_TYPE = 'S'))"; }
			 

			// functional Area
			if (!candSearch.getFunctionalArea().equals("")) {
				query += " AND FUN.CAND_SF_CODE IN("
						+ candSearch.getFunctionalArea() + ") ";
			}
			// skill code
			if (!candSearch.getSkillSet().equals("")) {
				query += " AND SKILL.CAND_SF_CODE IN("
						+ candSearch.getSkillSet() + ") ";
			}

			// industry type
			if (!candSearch.getIndustryType().equals("")) {
				query += " AND CAND_INDUS_TYPE IN("
						+ candSearch.getIndustryType() + ")";
			}

			// qualification and specialization
			
			 * if(!candSearch.getQualification().equals("") &&
			 * !candSearch.getSpecialization().equals("")){ query += " AND
			 * ((CAND_QUA_CODE IN("+candSearch.getQualification()+")) " +"AND
			 * (CAND_SPEC_CODE IN("+candSearch.getSpecialization()+")))"; }
			 

			// qualification
			if (!candSearch.getQualification().equals("")) {
				query += " AND CAND_QUA_CODE IN("
						+ candSearch.getQualification() + ")";
			}
			// specialization
			if (!candSearch.getSpecialization().equals("")) {
				query += " AND CAND_SPEC_CODE IN("
						+ candSearch.getSpecialization() + ")";
			}

			// if reqs code is selected then search the cands as per the
			// position for that reqs
			if (!candSearch.getRequisitionCode().equals("")) {
				String requisitionCode = "";

				// select all the reqs having same post as selected reqs
				String reqQuery = "SELECT REQS_CODE FROM HRMS_REC_REQS_HDR WHERE REQS_POSITION = "
						+ candSearch.getPositionCode();

				Object[][] reqCode = getSqlModel().getSingleResult(reqQuery);

				if (reqCode != null && reqCode.length != 0) {
					for (int i = 0; i < reqCode.length; i++) {
						requisitionCode += checkNull(String
								.valueOf(reqCode[i][0]))
								+ ",";
					}
					requisitionCode = requisitionCode.substring(0,
							requisitionCode.length() - 1);
				} else
					requisitionCode = candSearch.getRequisitionCode();

				query += " AND POST_REQS_CODE IN ("
						+ requisitionCode
						+ ") AND HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN "
						+ "(SELECT RESUME_CAND_CODE "
						+ "FROM HRMS_REC_RESUME_BANK WHERE RESUME_REQS_CODE = "
						+ candSearch.getRequisitionCode()
						+ " "
						+ "UNION SELECT DISTINCT EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL "
						+ "WHERE EVAL_REQS_CODE = "
						+ candSearch.getRequisitionCode()
						+ " "
						+ "UNION SELECT DISTINCT OFFER_CAND_CODE FROM HRMS_REC_OFFER "
						+ "WHERE OFFER_REQS_CODE = "
						+ candSearch.getRequisitionCode()
						+ " "
						+ "UNION SELECT DISTINCT APPOINT_CAND_CODE FROM HRMS_REC_APPOINT "
						+ "WHERE APPOINT_REQS_CODE = "
						+ candSearch.getRequisitionCode() + ") ";
			}

			// sort on
			if (candSearch.getSortOn().equals("RFA")) {
				// sort as per resume posting date in ascending order
				// query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY')
				// ASC";
				query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY') ASC";
			} else if (candSearch.getSortOn().equals("RFD")) {
				// sort as per resume posting date in descending order
				// query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY')
				// DESC";
				query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY') DESC";
			} else if (candSearch.getSortOn().equals("EA")) {
				// sort as per experience in ascending order
				// query += " ORDER BY CAND_EXP_YEAR ASC , CAND_EXP_MONTH ASC";
				query += " ORDER BY NVL(CAND_EXP_YEAR,0) , NVL(CAND_EXP_MONTH,0) ASC";
			} else if (candSearch.getSortOn().equals("ED")) {
				// sort as per experience in descending order
				// query += " ORDER BY CAND_EXP_YEAR DESC , CAND_EXP_MONTH
				// DESC";
				query += " ORDER BY  NVL(CAND_EXP_YEAR,0) DESC ,NVL(CAND_EXP_MONTH,0) DESC  ";
			} else if (candSearch.getSortOn().equals("CA")) {
				// sort as per Current CTC in ascending order
				query += " ORDER BY CAND_CURR_CTC ASC";
			} else if (candSearch.getSortOn().equals("CD")) {
				// sort as per Current CTC in descending order
				query += " ORDER BY CAND_CURR_CTC DESC";
			} else {
				// sort as per resume posting date in descending order
				// query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY')
				// DESC";
				query += " ORDER BY TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY') DESC";
			}*/
			
			String query =getQuery(candSearch);
				
			Object[][] expDetail = getSqlModel().getSingleResult(query);

			Object[][] objTabularData = new Object[expDetail.length][11];
			String[] columns = new String[] { "Sr. No.", "Requisition Title", "Cadidate name",
					"Source", "Reffered By", "Position",
					"Exp. in Yrs", "Posted Date", "CTC in Lacs", "Gender",
					"Short Listing Status" };
			int[] bcellAlign = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1 };
			int[] bcellWidth = new int[] { 2, 25, 25, 25, 25, 25, 20, 15, 10, 10, 25 };

			int count = 1;

			if (expDetail != null && expDetail.length > 0) {
				Object[][] expenseDtlDetName = new Object[1][1];
				expenseDtlDetName[0][0] = "Candidate Search Details ";
				TableDataSet expenseDtlDetNameTable = new TableDataSet();
				expenseDtlDetNameTable.setData(expenseDtlDetName);
				expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
				expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
				expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(expenseDtlDetNameTable);
				expenseDtlDetNameTable.setBlankRowsAbove(1);
				// expenseDtlDetNameTable.setBlankRowsBelow(1);
				for (int i = 0; i < expDetail.length; i++) {

					objTabularData[i][0] = count++;
					objTabularData[i][1] = String.valueOf(expDetail[i][11]); // requisition title
					objTabularData[i][2] = String.valueOf(expDetail[i][1]);
					objTabularData[i][3] = String.valueOf(expDetail[i][14]); // source
					objTabularData[i][4] = String.valueOf(expDetail[i][13]); // reffered by
					objTabularData[i][5] = String.valueOf(expDetail[i][12]); // position
					objTabularData[i][6] = String.valueOf(expDetail[i][2]);
					objTabularData[i][7] = String.valueOf(expDetail[i][3]);
					System.out.println("expDetail[i][3] = " + expDetail[i][3]);
					objTabularData[i][8] = String.valueOf(expDetail[i][4]);
					objTabularData[i][9] = String.valueOf(expDetail[i][5]);
					objTabularData[i][10] = String.valueOf(expDetail[i][6]);
					
					if(String.valueOf(objTabularData[i][10]).equals("S"))
					{
						objTabularData[i][10]="ShortList";
					}
					if(String.valueOf(objTabularData[i][10]).equals("R"))
					{
						objTabularData[i][10]="Rejected";
					}
					if(String.valueOf(objTabularData[i][10]).equals("N"))
					{
						objTabularData[i][10]="New Resume";
					}
					
				 

				}
			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			Object[][] expenseDtlDet = new Object[1][1];
			expenseDtlDet[0][0] = " ";
			TableDataSet expenseDtlDetTable = new TableDataSet();
			expenseDtlDetTable.setData(expenseDtlDet);
			expenseDtlDetTable.setCellAlignment(new int[] { 1 });
			expenseDtlDetTable.setCellWidth(new int[] { 100 });
			expenseDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0));
			expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
			rg.addTableToDoc(expenseDtlDetTable);
			/// expenseDtlDetTable.setBlankRowsAbove(1);
			expenseDtlDetTable.setBlankRowsBelow(1);

			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(columns);
			tdstable.setData(objTabularData);
			tdstable.setCellAlignment(bcellAlign);
			tdstable.setCellWidth(bcellWidth);
			tdstable.setBorder(true);
			tdstable.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(tdstable);

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} // End of Function for report

