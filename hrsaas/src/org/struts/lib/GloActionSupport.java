package org.struts.lib;

import java.util.Vector;
import javax.servlet.ServletRequest;

/**
 * @author Sunil
 * @date 6 Feb 2008
 **/
/**
 * Utility class which provides the functions to base action class
 */

public class GloActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.GloActionSupport.class);

	/**
	 * Gets records excluding blank rows
	 */
	/**
	 * @param obj -:
	 *            Specifies the records fetched by executing a query in f9
	 *            window
	 * @return String[][] as records excluding blank rows
	 */
	public static String[][] scanData(Object[][] obj) {
		Vector mainVect = new Vector();
		for (int i = 0; i < obj.length; i++) {
			boolean flag = false;

			/**
			 * Removes the blank rows
			 */
			for (int j = 0; j < obj[i].length; j++) {
				if (null == obj[i][j]
						|| String.valueOf(obj[i][j]).trim().equals("")
						|| String.valueOf(obj[i][j]).trim().equals("null")) {// Check
																				// any
																				// column
																				// in a
																				// row
																				// is
																				// blank
																				// or
																				// not
				} else {
					flag = true;
					break;
				}
			} // end of for loop
			if (flag) {// Check column is blank or not
				Vector childVect = new Vector();
				for (int j = 0; j < obj[0].length; j++) {
					childVect.add(obj[i][j]);
				} // end of for loop
				mainVect.add(childVect);
			}
		} // end of for loop

		/**
		 * Sets the records in Object[][] excluding blank rows
		 */
		String[][] colData = new String[mainVect.size()][obj[0].length];
		for (int i = 0; i < mainVect.size(); i++) {
			Vector childVect = (Vector) mainVect.get(i);
			for (int j = 0; j < childVect.size(); j++) {
				try {
					if (String.valueOf(obj[i][j]).trim().equals("")
							|| String.valueOf(obj[i][j]).trim().equals("null")) {
						colData[i][j] = "";
					} else {
						colData[i][j] = String.valueOf(childVect.get(j));
					}
				} catch (Exception ae) {
					colData[i][j] = null;
				} // end of try-catch block
			} // end of for loop
		} // end of for loop
		return colData;
	}

	/**
	 * Searches the given strings in the records fetched by executing a query in
	 * f9 window
	 */
	/**
	 * @param request -:
	 *            Specifies ServletRequest object
	 * @param obj -:
	 *            Specifies total records fetched by executing a query in f9
	 *            window
	 * @param columnIndex -:
	 *            Specifies column in which searchValue is searched
	 * @param searchValue -:
	 *            Specifies string entered to search
	 * @param searchSelect -:
	 *            Specifies option for searchValue
	 * @return String[][] as searched records
	 */
	public String[][] searchData(ServletRequest request, String[][] obj,
			int columnIndex, String searchValue, String searchSelect) {
		logger.info("Welcome for search : ");
		int indexSelected = 1;
		String[] new_f9SearchCodition = null;
		int[] new_f9SearchIndex = null;
		String[] new_f9SearchSelect = null;
		String[] new_f9SearchText = null;

		String hdClick = "N";

		new_f9SearchCodition = new String[indexSelected];
		new_f9SearchIndex = new int[indexSelected];
		new_f9SearchSelect = new String[indexSelected];
		new_f9SearchText = new String[indexSelected];
		new_f9SearchIndex[0] = columnIndex;
		new_f9SearchSelect[0] = searchSelect;
		new_f9SearchText[0] = searchValue;
		String[] f9SearchCodition = null;
			
		try {
			// and or values
			f9SearchCodition = request.getParameterValues("f9SearchCodition");

			// column options
			String[] f9SearchIndex1 = request
					.getParameterValues("f9SearchIndex1");
			logger.info("f9SearchIndex1 " + f9SearchIndex1.length);

			// search by option
			String[] f9SearchSelect1 = request
					.getParameterValues("f9SearchSelect1");
			logger.info("f9SearchSelect1 " + f9SearchSelect1.length);

			// search text
			String[] f9SearchText1 = request
					.getParameterValues("f9SearchText1");
			logger.info("f9SearchText1 " + f9SearchText1.length);

			// if advance clicked hdClick value becomes 'Y'
			hdClick = request.getParameter("hdClick");

			if (hdClick.equals("Y")) {
				indexSelected = f9SearchText1.length + 1;
			}
			
			new_f9SearchCodition = new String[indexSelected];
			new_f9SearchIndex = new int[indexSelected];
			new_f9SearchSelect = new String[indexSelected];
			new_f9SearchText = new String[indexSelected];

			for (int i = 0; i < indexSelected; i++) {
				// for the first row condition
				if (i == 0) {
					new_f9SearchIndex[i] = columnIndex;
					new_f9SearchSelect[i] = searchSelect;
					new_f9SearchText[i] = searchValue;
				}
				// for advance selected values
				else {
					new_f9SearchIndex[i] = Integer.parseInt(f9SearchIndex1[i - 1]);
					new_f9SearchSelect[i] = f9SearchSelect1[i - 1];
					new_f9SearchText[i] = f9SearchText1[i - 1];
				}
			} // end of for loop
			
		} catch (Exception e) {

		}

		Vector vector = new Vector();
		for (int i = 0; i < obj.length; i++) { // loop1 - for the rows
			boolean[] flag = new boolean[indexSelected];
			boolean finalFlag = false;
			for (int j = 0; j < flag.length; j++) { // loop2 - for the number of
													// options selected
				if (new_f9SearchText[j].equals("")) {
					flag[j] = true;
				} else {
					if (String.valueOf(new_f9SearchSelect[j]).equals("BW")) {// Begins
																				// With
						if (obj[i][new_f9SearchIndex[j]].toUpperCase()
								.startsWith(new_f9SearchText[j].toUpperCase())) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"ET")) {// Equal To
						if (obj[i][new_f9SearchIndex[j]].toUpperCase().equals(
								new_f9SearchText[j].toUpperCase())) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j])
							.equals("E")) {// Exat With
						if (obj[i][new_f9SearchIndex[j]].toUpperCase().equals(
								new_f9SearchText[j].toUpperCase())) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"CAV")) {// Contains all Values
						if (obj[i][new_f9SearchIndex[j]].toUpperCase().indexOf(
								new_f9SearchText[j].toUpperCase()) != -1) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"GT")) {// Greater than
						if (Double.valueOf(obj[i][new_f9SearchIndex[j]]) > Double
								.valueOf(new_f9SearchText[j])) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"NET")) {// Not Equal To
						if (!obj[i][new_f9SearchIndex[j]].toUpperCase().equals(
								new_f9SearchText[j].toUpperCase())) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"LT")) {// Less Than
						if (Double.valueOf(obj[i][new_f9SearchIndex[j]]) < Double
								.valueOf(new_f9SearchText[j])) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"DNBW")) {// Does not begin with
						if (!obj[i][new_f9SearchIndex[j]].toUpperCase()
								.startsWith(new_f9SearchText[j].toUpperCase())) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"IN")) {// Is Null
						if (obj[i][new_f9SearchIndex[j]].toUpperCase().equals(
								"NULL")) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"BT")) {// Between
						String[] a = new_f9SearchText[j].split("and");
						if (Double.parseDouble(obj[i][new_f9SearchIndex[j]]) > Double
								.parseDouble(a[0].trim())
								&& Double
										.parseDouble(obj[i][new_f9SearchIndex[j]]) < Double
										.parseDouble(a[1].trim())) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"NBT")) {// Not Between
						String[] a = new_f9SearchText[j].split("and");
						if (!(Double.parseDouble(obj[i][new_f9SearchIndex[j]]) > Double
								.parseDouble(a[0].trim()) && Double
								.parseDouble(obj[i][new_f9SearchIndex[j]]) < Double
								.parseDouble(a[1].trim()))) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"INN")) {// Is Not Null
						if (!obj[i][new_f9SearchIndex[j]].toUpperCase().equals(
								"NULL")) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"CNV")) {// Contains none of the values
						if (!(obj[i][new_f9SearchIndex[j]].toUpperCase()
								.indexOf(new_f9SearchText[j].toUpperCase()) != -1)) {
							flag[j] = true;
						}
					} else if (String.valueOf(new_f9SearchSelect[j]).equals(
							"INL")) {// Is Not Like.........
						if (!(obj[i][new_f9SearchIndex[j]].toUpperCase()
								.indexOf(new_f9SearchText[j].toUpperCase()) != -1)) {
							flag[j] = true;
						}
					} else {
						if (obj[i][new_f9SearchIndex[j]].toUpperCase().indexOf(
								new_f9SearchText[j].toUpperCase()) != -1) {
							flag[j] = true;
						}
					}
				} // end of else statement
				// ---added
				if (hdClick.equals("N") && flag[0]) {
					Vector resultVector = new Vector();
					for (int k = 0; k < obj[0].length; k++) {
						resultVector.add(obj[i][k]);
					} // end of for loop
					vector.add(resultVector);
				}
			} // end of for loop2
			try{
			if (!(hdClick.equals("N") && flag[0])) {
				// Get true/false for satisfying all search options
				finalFlag = getFinalFlag(flag, f9SearchCodition);
				if (finalFlag == true) {
					Vector resultVector = new Vector();
					for (int k = 0; k < obj[0].length; k++) {
						resultVector.add(obj[i][k]);
					} // end of for loop
					vector.add(resultVector);
				}
			}
			}catch(Exception e) {
				
			}
		} // end of for loop1

		String[][] result = new String[vector.size()][obj[0].length];
		for (int i = 0; i < vector.size(); i++) {
			Vector resultVector = (Vector) vector.get(i);
			for (int j = 0; j < resultVector.size(); j++) {
				try {
					result[i][j] = String.valueOf(resultVector.get(j));
				} catch (Exception ae) {
					result[i][j] = null;
				} // end of try-catch block
			} // end of for loop
		} // end of for loop
		return result;
	}

	/**
	 * Gets true if all the conditions, like ANDs and ORs, applied for search
	 * options in f9 window are satisfied
	 */
	/**
	 * @param flag -:
	 *            Specifies true/false value for every search option
	 * @param f9SearchCodition -:
	 *            Specifies AND/OR value selected in a page
	 * @return boolean as true/false for selected all the options
	 */
	public boolean getFinalFlag(boolean[] flag, String[] f9SearchCodition) {
		boolean finalFlag = false;
		for (int i = 0; i < f9SearchCodition.length; i++) {
			if (i == 0) {
				if (f9SearchCodition[i].equals("A")) {
					if (flag[i] && flag[i + 1]) {
						finalFlag = true;
					} else {
						finalFlag = false;
					}
				} else {
					if (flag[i] || flag[i + 1]) {
						finalFlag = true;
					} else {
						finalFlag = false;
					}
				}
			} else {
				if (f9SearchCodition[i].equals("A")) {
					if (finalFlag && flag[i + 1]) {
						finalFlag = true;
					} else {
						finalFlag = false;
					}
				} else {
					if (finalFlag || flag[i + 1]) {
						finalFlag = true;
					} else {
						finalFlag = false;
					}
				}
			}
		} // end of for loop
		return finalFlag;
	}
}