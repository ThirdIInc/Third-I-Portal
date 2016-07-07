package org.paradyne.lib;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.JexlHelper;
import org.paradyne.model.common.UserProfileModel;

/**
 * @author Sunil
 * @date 29 Nov 2007
 */

/**
 * This class provides the features to enhance the functionalities
 */

public class Utility {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Utility.class);
	/**
	 * Arrays containing words for integers
	 */
	private static final String[] numNames = { "", " One", " Two", " Three",
			" Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten",
			" Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen",
			" Sixteen", " Seventeen", " Eighteen", " Nineteen" };

	private static final String[] tensNames = { "", " Twenty", " Thirty",
			" Fourty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety" };
	private static final String[] majorNames = { "", " Thousand", " Lakh",
			" Crores" };
	
	/*Added HSSFColor specific color codes to use with XLSGenerate class in old library*/
	
	public static final int GREY_80_PERCENT=63;
	public static final int INDIGO=62;
	public static final int PLUM=61;
	public static final int BROWN=60;
	public static final int OLIVE_GREEN=59;
	public static final int DARK_GREEN=58;
	public static final int SEA_GREEN=57;
	public static final int DARK_TEAL=56;
	public static final int GREY_40_PERCENT=55;
	public static final int BLUE_GREY=54;
	public static final int ORANGE=53;
	public static final int LIGHT_ORANGE=52;
	public static final int GOLD=51;
	public static final int LIME=50;
	public static final int AQUA=49;
	public static final int LIGHT_BLUE=48;
	public static final int LAVENDER=46;
	public static final int ROSE=45;
	public static final int PALE_BLUE=44;
	public static final int LIGHT_YELLOW=43;
	public static final int LIGHT_GREEN=42;
	public static final int LIGHT_TURQUOISE=41;
	public static final int SKY_BLUE=40;
	public static final int LIGHT_CORNFLOWER_BLUE=31;
	public static final int ROYAL_BLUE=30;
	public static final int CORAL=29;
	public static final int ORCHID=28;
	public static final int LEMON_CHIFFON=26;
	public static final int MAROON=25;
	public static final int CORNFLOWER_BLUE=24;
	public static final int GREY_50_PERCENT=23;
	public static final int GREY_25_PERCENT=22;
	public static final int TEAL=21;
	public static final int VIOLET=20;
	public static final int DARK_YELLOW=19;
	public static final int DARK_BLUE=18;
	public static final int GREEN=17;
	public static final int DARK_RED=16;
	public static final int TURQUOISE=15;
	public static final int PINK=14;
	public static final int YELLOW=13;
	public static final int BLUE=12;
	public static final int BRIGHT_GREEN=11;
	public static final int RED=10;
	public static final int WHITE=9;
	public static final int BLACK=8;
	
	/**
	 * Checks for the null value and if it finds it to be null then replaces it
	 * with blank.
	 * 
	 * @param result :-
	 *            Input String to be checked
	 * @return : - returns the checked string
	 */
	public static String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * Following method is added to check for the null in 2-dimensional Object
	 * Array if it finds null then replaces it with blank.
	 * 
	 * @param objArr : -
	 *            2-Dimensional Object[][] array to be checked
	 * @return :- 2-dimensional Object array with no null values
	 */
	public static Object[][] checkNullObjArr(Object objArr[][]) {
		try {
			for (int i = 0; i < objArr.length; i++) {
				for (int j = 0; j < objArr[0].length; j++) {
					if (String.valueOf(objArr[i][j]) == null
							|| String.valueOf(objArr[i][j]).equals(null)
							|| String.valueOf(objArr[i][j]).equals("null"))
						objArr[i][j] = "";
				}
			}
		} catch (Exception e) {
		}
		return objArr;
	}

	public static Object[][] checkNullObjArr(Object objArr[][], String data) {
		try {
			for (int i = 0; i < objArr.length; i++) {
				for (int j = 0; j < objArr[0].length; j++) {
					if (String.valueOf(objArr[i][j]) == null
							|| String.valueOf(objArr[i][j]).equals(null)
							|| String.valueOf(objArr[i][j]).equals("null"))
						objArr[i][j] = data;
				}
			}
		} catch (Exception e) {
		}
		return objArr;
	}

	/**
	 * Following method is added to check for the null in 2-dimensional String
	 * Array if it finds null then replaces it with blank.
	 * 
	 * @param objArr : -
	 *            2-Dimensional String[][] array to be checked
	 * @return :- 2-dimensional String array with no null values
	 */
	public static String[][] checkNullStrArr(String[][] objArr) {
		try {
			for (int i = 0; i < objArr.length; i++) {
				for (int j = 0; j < objArr[0].length; j++) {
					if (objArr[i][j] == null || objArr[i][j].equals(null)
							|| String.valueOf(objArr[i][j]).equals("null"))
						objArr[i][j] = "";
				}
			}
		} catch (Exception e) {
		}
		return objArr;
	}

	/**
	 * Converts integer into string
	 */
	/**
	 * @param number
	 * @return string for number
	 */
	public static String convert(int number) {
		if (number == 0) {
			return "zero";
		}
		String prefix = "";
		if (number < 0) {
			number = -number;
			prefix = "negative";
		}
		String soFar = "";
		int place = 0;
		int i = 0;
		do {
			if (i == 0 || i == 3) {
				int n = number % 1000;
				if (n != 0) {
					String s = convertLessThanOneHundred(n);
					soFar = s + majorNames[place] + soFar;
				}
				place++;
				number /= 1000;
				i++;
			} else {
				int n = number % 100;
				if (n != 0) {
					String s = convertLessThanOneHundred(n);
					soFar = s + majorNames[place] + soFar;
				}
				place++;
				number /= 100;
				i++;
			}
		} while (number > 0);
		return (prefix + soFar + " only").trim();
	}

	
	
	/**Shashikant Doke
	 * @param Converts double into string
	 * @return string for number
	 */
	public static String convert(double values) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		if (values == 0) {
			return "zero";
		}
		String prefix = "";
		if (values < 0) {
			values = -values;
			prefix = "negative";
		}
		
		String inWord = "";
		if(String.valueOf(values).contains(".")){
			
		}else{
			String strvalue=values+"0.00";
			values=Double.parseDouble(strvalue);
		}
		String valueString=formatter.format(values);
		valueString=valueString.replace(".", "#");
		String str[]=valueString.split("#");
		for (int k = 0; k < 2; k++) {
			String soFar = "";
			int number=Integer.parseInt(str[k]);
			int decimalNumber=Integer.parseInt(str[k]);
			int place = 0;
			int i = 0;
			do {
				if (i == 0 || i == 3) {
					int n = number % 1000;
					if (n != 0) {
						String s = convertLessThanOneHundred(n);
						soFar = s + majorNames[place] + soFar;
					}
					place++;
					number /= 1000;
					i++;
				} else {
					int n = number % 100;
					if (n != 0) {
						String s = convertLessThanOneHundred(n);
						soFar = s + majorNames[place] + soFar;
					}
					place++;
					number /= 100;
					i++;
				}
				
			} while (number > 0);
			if(k==0){
				inWord=soFar;
			}
			else{
				if(decimalNumber>0)
				inWord+=" Rupee And"+soFar+" Paise";
			}
		}
		
		
		return (prefix + inWord + " only").trim();
	}
	
	
	/**
	 * Converts integer into string which are less than 100
	 */
	/**
	 * @param number
	 * @return string for number
	 */
	private static String convertLessThanOneHundred(int number) {
		String soFar;
		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[(number % 10) - 1] + soFar;
			number /= 10;
		}
		if (number == 0) {
			return soFar;
		}
		
		//return numNames[number] + " hundred \t And \t " + soFar;
		if(soFar.equals("")){
			return numNames[number] + " hundred";
		}else{
			return numNames[number] + " hundred And" +soFar;
		}
		
	}

	public static String createTagWithAtributeValue(String tagName,
			String attributeValue) {
		return "<" + tagName + " value='" + attributeValue + "' />";

	}

	/**
	 * @param tagName
	 * @param tagValue
	 * @return
	 */
	public static String createTagWithValue(String tagName, String tagValue) {
		return "<" + tagName + ">" + tagValue + "</" + tagName + ">";

	}

	/**
	 * Removes the column, if values in all rows are blank or 0
	 */
	/**
	 * @param obj
	 * @return String[][] containing non-blank and non-zero columns
	 */
	public static String[][] deleteColumnIfZero(Object[][] obj) {
		Vector mainVect = new Vector();
		for (int i = 0; i < obj[0].length; i++) {
			boolean flag = false;
			try {
				/**
				 * Check whether value in a row is either blank or 0
				 */
				for (int j = 0; j < obj.length; j++) {
					if (null == obj[j][i]
							|| String.valueOf(obj[j][i]).trim().equals("0")
							|| String.valueOf(obj[j][i]).trim().equals("")
							|| String.valueOf(obj[j][i]).trim().equals("null")) {
					} else {
						flag = true;
						break;
					}
				} // end of for loop
			} catch (Exception e) {
				// logger.error(e);
			} // end of try-catch block

			/**
			 * Make a vector object containing non-blank and non-zero rows of
			 * column
			 */
			if (flag) {
				Vector childVect = new Vector();
				for (int k = 0; k < obj.length; k++) {
					childVect.add(obj[k][i]);
				}
				mainVect.add(childVect);
			}
		} // end of for loop

		/**
		 * Make values as null for some blank or zero value rows
		 */
		String[][] colData = new String[obj.length][mainVect.size()];
		for (int i = 0; i < mainVect.size(); i++) {
			Vector childVect = (Vector) mainVect.get(i);
			for (int j = 0; j < childVect.size(); j++) {
				try {
					if (String.valueOf(obj[j][i]).trim().equals("")
							|| String.valueOf(obj[j][i]).trim().equals("null")) {
						colData[j][i] = null;
					} else {
						colData[j][i] = String.valueOf(childVect.get(j));
					}
				} catch (Exception ae) {
					// logger.error(ae);
					colData[j][i] = null;
				} // end of try-catch block
			}
		} // end of for loop
		return colData;
	}

	/**
	 * Removes the column, if values in all rows are blank or 0
	 */
	/**
	 * @param obj
	 * @return String[][] containing non-blank and non-zero columns
	 */
	public static String[][] deleteColumnIfZeroWithHeader(Object[][] obj) {
		Vector mainVect = new Vector();
		for (int i = 0; i < obj[0].length; i++) {
			boolean flag = false;
			try {
				/**
				 * Check whether value in a row is either blank or 0
				 */
				for (int j = 1; j < obj.length; j++) {
					if (null == obj[j][i]
							|| String.valueOf(obj[j][i]).trim().equals("0")
							|| String.valueOf(obj[j][i]).trim().equals("")
							|| String.valueOf(obj[j][i]).trim().equals("null")) {
					} else {
						flag = true;
						break;
					}
				} // end of for loop
			} catch (Exception e) {
				// logger.error(e);
			} // end of try-catch block

			/**
			 * Make a vector object containing non-blank and non-zero rows of
			 * column
			 */
			if (flag) {
				Vector childVect = new Vector();
				for (int k = 0; k < obj.length; k++) {
					childVect.add(obj[k][i]);
				}
				mainVect.add(childVect);
			}
		} // end of for loop

		/**
		 * Make values as null for some blank or zero value rows
		 */
		String[][] colData = new String[obj.length][mainVect.size()];
		for (int i = 0; i < mainVect.size(); i++) {
			Vector childVect = (Vector) mainVect.get(i);
			for (int j = 0; j < childVect.size(); j++) {
				try {
					colData[j][i] = String.valueOf(childVect.get(j));
				} catch (Exception ae) {
					// logger.error(ae);
					colData[j][i] = null;
				} // end of try-catch block
			} // end of for loop
		} // end of for loop
		return colData;
	}

	/**
	 * Removes the rows, if values in all columns are 0 or 0.0
	 */
	/**
	 * @param obj
	 * @param startColumn
	 * @return String[][] not containing 0 or 0.0 rows
	 */
	public static String[][] deleteRowIfZero(String[][] obj, int startColumn) {
		Vector mainVect = new Vector();
		for (int i = 0; i < obj.length; i++) {
			boolean flag = false;
			/**
			 * Check whether value in a column is either 0 or 0.0
			 */
			for (int j = startColumn; j < obj[i].length; j++) {
				if (String.valueOf(obj[i][j]).trim().equals("0")
						|| String.valueOf(obj[i][j]).trim().equals("0.0")) {
				} else {
					flag = true;
					break;
				}
			} // end of for loop

			/**
			 * Make a vector object not containing 0 and 0.0 rows
			 */
			if (flag) {
				Vector childVect = new Vector();
				for (int j = 0; j < obj[0].length; j++) {
					childVect.add(obj[i][j]);
				}
				mainVect.add(childVect);
			}
		} // end of for loop

		/**
		 * Make values as null for some blank or zero value columns
		 */
		String[][] colData = new String[mainVect.size()][obj[0].length];
		for (int i = 0; i < mainVect.size(); i++) {
			Vector childVect = (Vector) mainVect.get(i);
			for (int j = 0; j < childVect.size(); j++) {
				try {
					colData[i][j] = String.valueOf(childVect.get(j));
				} catch (Exception ae) {
					// logger.error(ae);
					colData[i][j] = null;
				} // end of try-catch block
			}
		} // end of for loop
		return colData;

	}

	// for display the month in date format
	// eg. 05-04-2009 given the output as 05-may-2009
	public static String displayMonthInDate(String date1) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			java.util.Date dt1 = sdf.parse(date1);
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			String strDate = df.format(dt1);
			String df1 = strDate.replace(",", "");
			String df2 = df1.replace(" ", "-");
			String[] dateFor = df2.split("-");
			String finalDate = dateFor[1] + "-" + dateFor[0] + "-" + dateFor[2];
			return finalDate;
		} catch (Exception e) {
			return date1;
		}
	}

	public static String[] doPaging(String myPage, int empLength, int totalRec) {
		String[] returnStr = new String[5];
		try {
			// totalRec -: No. of records per page
			// fromTotRec -: Starting No. of record to show on a current page
			// toTotRec -: Last No. of record to show on a current page
			// pageNo -: Current page to show
			// totalPage -: Total No. of Pages

			/*
			 * String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM
			 * HRMS_SALARY_CONF "; Object[][] pagingObj =
			 * getSqlModel().getSingleResult(pagingSql); int totalRec =
			 * Integer.parseInt(String.valueOf(pagingObj[0][0]));
			 */

			double row1 = 0.0;
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int totalPage = 0;

			try {
				row1 = (double) empLength / totalRec;
				java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(row1);
				totalPage = Integer.parseInt(bigDecRow1.setScale(0,
						java.math.BigDecimal.ROUND_UP).toString());
			} catch (Exception e) {
				// logger.error(e.getMessage());
			}

			if (myPage == null || myPage.equals("null") || myPage.equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				returnStr[4] = "1";
			} else {
				pageNo = Integer.parseInt(myPage);

				if (pageNo > totalPage) {
					pageNo = totalPage;
				}

				if (pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				returnStr[4] = "";
			}

			returnStr[0] = String.valueOf(fromTotRec);
			returnStr[1] = String.valueOf(toTotRec);

			returnStr[2] = String.valueOf(totalPage);
			returnStr[3] = String.valueOf(pageNo);

		} catch (Exception e) {
			// logger.error(e.getMessage());
		}
		return returnStr;

	} // end of method doPaging()

	/**
	 * Converts formula into double value
	 */
	/**
	 * @param formula
	 * @return double as converted formula
	 */
	public static double expressionEvaluate(String formula) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		/**
		 * Expression -: Represents a single JEXL expression ExpressionFactory -:
		 * Creates Expression objects JexlContext -: Holds a Map of variables
		 * which are referenced in a JEXL expression JexlHelper -: Helper to
		 * create a context
		 */
		try {
			//formula=formatter.format(Double.parseDouble(formula));
			Expression e = ExpressionFactory.createExpression(formula);
			JexlContext cony = JexlHelper.createContext();

			// Returns evaluated expression with the variables contained in the
			// JexlContext
			return Double.parseDouble(String.valueOf(e.evaluate(cony)));
		} catch (Exception e) {
			// logger.error(e);
			return 0;
		}
	}

	/**
	 * Get Ids separated by comma in 'IN' expression in 'WHERE' condition while
	 * firing a query. Oracle doesn't accept more than 1000 records in 'IN'
	 * expression. To overcome this situation, set the Ids in 'IN' expression up
	 * to 1000 and append the remaining Ids in next 'IN' expression appended by
	 * 'OR'.
	 * 
	 * @param columnName:
	 *            Column name in a query on which 'IN' expression is applied
	 * @param concatenatedIds:
	 *            List of all Ids separated by comma
	 * @return String as Sql Query appended by 'IN' expression in 'WHERE'
	 *         condition
	 */
	public static String getConcatenatedIds(String columnName,
			String concatenatedIds) {
		String listOfConcatIds = "";
		try {
			listOfConcatIds = " AND ("; // Initially append in 'WHERE' condition

			/**
			 * If concatenated Ids are more than one, then only separate those
			 * Ids into multiple 'IN' expressions, otherwise add single Id in
			 * 'IN' expression directly.
			 */
			if (concatenatedIds.length() > 1) {
				String[] listOfIds = concatenatedIds.split(","); // split the
				// Ids in
				// String
				// object

				/**
				 * If total no. of Ids are greater than 1000, then take first
				 * 1000 Ids in 'IN' expression and remaining Ids in next 'IN'
				 * expression. If total no. of Ids are less, then there will be
				 * only one 'IN' expression with all appended Ids
				 */
				if (listOfIds != null && listOfIds.length > 1000) {
					int totalNumOfIds = listOfIds.length; // get total no. of
					// Ids

					/**
					 * Calculate total no. of iterations, used to iterate
					 * through Ids e.g. if total Ids = 1998, then total
					 * iterations = 1998 / 1000 = 2
					 */
					int totalIterations = (int) Math
							.ceil(totalNumOfIds / 1000.0);

					/**
					 * Get remaining Ids used in for loop to iterate through the
					 * Ids e.g. if total Ids = 1998, then remaining Ids = 1998 -
					 * 1000 = 998
					 */
					int remainingIds = (int) (((totalNumOfIds / 1000.0) - Math
							.floor(totalNumOfIds / 1000.0)) * 1000);

					int fromNumOfIds = 0, toNumOfIds = 0; // from counter and
					// to counter for
					// iterations
					int interationNo = 1; // initiate iteration no. to 1

					/**
					 * Iterate up to the total no. of iterations
					 */
					while (interationNo <= totalIterations) {
						listOfConcatIds += columnName + " IN("; // append 'IN'
						// expression
						// after each
						// iteration

						/**
						 * If current iteration no. is less than total
						 * iterations, then take next 1000 Ids. If not, then
						 * take remaining Ids
						 */
						if (interationNo < totalIterations) {
							toNumOfIds += 1000; // increment to counter by 1000
						} else {
							toNumOfIds += remainingIds; // increment to counter
							// by remaining Ids
						}

						/**
						 * Append the Ids to each other separate by comma. If
						 * current id no. is less than to counter by one, then
						 * don't append the Ids by comma, append only last Id in
						 * 'IN' expression
						 */
						for (int idNo = fromNumOfIds; idNo < toNumOfIds; idNo++) {
							if (idNo == (toNumOfIds - 1)) { // current Id no.
								// less than to
								// counter by one
								listOfConcatIds += listOfIds[idNo]; // 
							} else {
								listOfConcatIds += listOfIds[idNo] + ",";
							}
						}

						/**
						 * If current iteration no. is less than total
						 * iterations, then take next 1000 Ids. If not, then
						 * complete the 'IN' expression
						 */
						if (interationNo < totalIterations) {
							fromNumOfIds += 1000; // increment from counter by
							// 1000
							listOfConcatIds += ") OR "; // complete one 'IN'
							// expression and append
							// 'OR' for next 'IN'
						} else if (interationNo == totalIterations) { // current
							// iteration
							// no.
							// is
							// same
							// as
							// total
							// iterations
							listOfConcatIds += "))"; // complete the 'IN'
							// expression
						}
						interationNo++; // increment current iteration no.
					}
				} else { // append all Ids in single 'IN' expression
					listOfConcatIds += columnName + " IN(" + concatenatedIds
							+ "))";
				}
			} else { // append all Ids in single 'IN' expression
				listOfConcatIds += columnName + " IN(" + concatenatedIds + "))";
			}
		} catch (Exception e) {
			// logger.error("Exception in :" + e);
		}
		return listOfConcatIds;
	}

	/**
	 * Converts string into Date
	 */
	/**
	 * @param date -:
	 *            Specifies string in dd-mm-yyyy format
	 * @return Date object
	 */
	public static java.sql.Date getDate(String date) {
		String dateString[] = date.split("-");
		int day = Integer.parseInt(dateString[0]);
		int month = Integer.parseInt(dateString[1]);
		int year = Integer.parseInt(dateString[2]);
		return new java.sql.Date(year - 1900, month - 1, day);
	}

	/**
	 * Get pool name from properties file
	 */
	/**
	 * @return string as pool name
	 */
	public static String getPoolName() {
		java.util.ResourceBundle rb = java.util.ResourceBundle
				.getBundle("poolResource");
		return rb.getString("poolname");
	}

	/**
	 * Copy rows into columns
	 */
	/**
	 * @param a
	 * @return inversed Object[][]
	 */
	public static Object[][] inverse(Object[][] a) {
		int rows = a.length;// Get total no. of rows
		int cols = a[0].length;// Get total no. of columns

		Object[][] b = new Object[cols][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				b[j][i] = a[i][j];
			}
		}
		return b;
	}

	/**
	 * Append two Object[][]
	 */
	/**
	 * @param a -:
	 *            Specifies object containing data
	 * @param b -:
	 *            Specifies object containing data
	 * @param joinType -:
	 *            0: Column wise else: Row wise
	 * @param startNumber -:
	 *            Offset, from where data will be copied
	 * @return joint Object[][]
	 */
	public static Object[][] joinArrays(Object[][] a, Object[][] b,
			int joinType, int startNumber) {
		Object[][] c = null;

		/**
		 * Add column wise
		 */
		if (joinType == 0) {
			int row = (a.length > b.length) ? a.length : b.length;
			int col_a = a[0].length;// No. of columns in a object
			int col_b = b[0].length;// No. of columns in b object
			c = new Object[row][col_a + col_b - startNumber];

			/**
			 * Initialize the object with blank
			 */
			for (int k = 0; k < c.length; k++) {
				for (int l = 0; l < c[0].length; l++) {
					c[k][l] = "";
				}
			}

			/**
			 * Copy both objects
			 */
			for (int i = 0; i < row; i++) {
				/**
				 * Copy values of first object row wise
				 */
				try {
					for (int j = 0; j < col_a; j++) {
						c[i][j] = a[i][j];
						if (null == c[i][j]) {
							c[i][j] = "";
						}
					} // end of for loop
				} catch (Exception ex) {
					logger.error(ex);
				} // end of try-catch block

				/**
				 * Copy values of second object row wise started from
				 * startNumber
				 */
				try {
					for (int j = startNumber; j < col_b; j++) {
						c[i][col_a + j - startNumber] = b[i][j];
						if (null == c[i][col_a + j - startNumber]) {
							c[i][col_a + j - startNumber] = "";
						}
					} // end of for loop
				} catch (Exception ex) {
					logger.error(ex);
				} // end of try-catch block
			} // end of for loop
		} // end of if statement

		/**
		 * Add row wise. No. of columns for both objects must be same
		 */
		else {
			int rows_a = a.length;// No. of rows in a object
			int rows_b = b.length;// No. of rows in b object

			if (a.length == 0 && b.length == 0) {
				return a;
			} else if (a.length == 0) {
				return b;
			} else if (b.length == 0) {
				return a;
			}

			int col = a[0].length;// No. of column in a new object
			c = new Object[rows_a + rows_b - startNumber][col];

			/**
			 * Initialize the object with blank
			 */
			for (int k = 0; k < c.length; k++) {
				for (int l = 0; l < c[0].length; l++) {
					c[k][l] = "";
				}
			}

			/**
			 * Copy first object
			 */
			for (int i = 0; i < rows_a; i++) {
				for (int j = 0; j < col; j++) {
					try {
						c[i][j] = a[i][j];
						if (null == c[i][j]) {
							c[i][j] = "";
						}
					} catch (Exception e) {
						logger.error(e);
					} // end of try-catch block
				} // end of for loop
			} // end of for loop

			/**
			 * Copy second object started from startNumber
			 */
			for (int i = startNumber; i < rows_b; i++) {
				for (int j = 0; j < col; j++) {
					try {
						c[rows_a + i - startNumber][j] = b[i][j];
						if (null == c[rows_a + i - startNumber][j]) {
							c[rows_a + i - startNumber][j] = "";
						}
					} catch (Exception e) {
						logger.error(e);
					} // end of try-catch block
				} // end of for loop
			} // end of for loop
		}
		return c;
	}

	/**
	 * Returns month name
	 */
	/**
	 * @param mon
	 * @return string as month corresponding to int value
	 */
	public static String month(int mon) {
		DateFormatSymbols obj=new DateFormatSymbols();
		return obj.getMonths()[mon-1];
	}
	public static String monthShortName(int mon) {
		DateFormatSymbols obj=new DateFormatSymbols();
		return obj.getShortMonths()[mon-1];
	}
	
	public static Object[][] removeNullRows(Object[][] inputObj) {
		try {
			// //logger.info("inputObj length with null" + inputObj.length);
			Vector v = new Vector();

			if (inputObj == null) {

			} // end of if
			else if (inputObj.length == 0) {

			} // end of else if
			else {
				int totalCol = inputObj[0].length;
				for (int i = 0; i < inputObj.length; i++){
					boolean isNull=true;
					for (int j = 0; j < inputObj[i].length; j++){
						if (!(String.valueOf(inputObj[i][j]).equals("null")
								|| String.valueOf(inputObj[i][j]).equals(null) || String
								.valueOf(inputObj[i][j]).trim().equals(""))){
							isNull = false;
							break;
						}						
					}
					if(isNull) {
						v.add(inputObj[i]);
					}
				}	
				inputObj = new Object[v.size()][totalCol];
				for (int i = 0; i < inputObj.length; i++)
					inputObj[i] = (Object[]) v.get(i);
				// //logger.info("inputObj length without null" +
				// inputObj.length);
			} // end of else

		} catch (Exception e) {
			// logger.error("exception in removeNullRows", e);
		}
		return inputObj;
	}

	public static Object[][] removeNullRows(Object[][] inputObj, int index) {
		try {
			// //logger.info("inputObj length with null" + inputObj.length);
			Vector v = new Vector();

			if (inputObj == null) {

			} // end of if
			else if (inputObj.length == 0) {

			} // end of else if
			else {
				int totalCol = inputObj[0].length;
				for (int i = 0; i < inputObj.length; i++)
					if (!(String.valueOf(inputObj[i][index]).equals("null")
							|| String.valueOf(inputObj[i][index]).equals(null) || String
							.valueOf(inputObj[i][index]).trim().equals("")))
						v.add(inputObj[i]);
				inputObj = new Object[v.size()][totalCol];
				for (int i = 0; i < inputObj.length; i++)
					inputObj[i] = (Object[]) v.get(i);
				// //logger.info("inputObj length without null" +
				// inputObj.length);
			} // end of else

		} catch (Exception e) {
			// logger.error("exception in removeNullRows", e);
		}
		return inputObj;
	}

	/**
	 * Replace null with specified value
	 */
	/**
	 * @param strArr
	 * @param repalceValue
	 * @return replaced String[][]
	 */
	public static String[][] replaceNull(String[][] strArr, String repalceValue) {
		String[][] resultArr = null;
		if (strArr != null && strArr.length > 0) {
			resultArr = new String[strArr.length][strArr[0].length];
			for (int i = 0; i < strArr.length; i++) {
				for (int j = 0; j < strArr[0].length; j++) {
					resultArr[i][j] = strArr[i][j];// Copy one object into
					// other
					if (strArr[i][j] == null || strArr[i][j].equals("null")) {
						resultArr[i][j] = repalceValue;// Replace with null
					}
				} // end of for loop
			} // end of for loop
		} // end of if statement
		return resultArr;
	}

	/**
	 * Calculates the amount as per the formula
	 */

	/**
	 * Removes the blank or null values from object
	 */
	/**
	 * @param obj
	 * @return String[][] containing non-blank and non-null values
	 */
	public static String[][] scanData(Object[][] obj) {
		Vector mainVect = new Vector();
		for (int i = 0; i < obj.length; i++) {
			boolean flag = false;
			/**
			 * Check whether value in a row is either blank or null
			 */
			for (int j = 0; j < obj[i].length; j++) {
				if (null == obj[i][j]
						|| String.valueOf(obj[i][j]).trim().equals("")
						|| String.valueOf(obj[i][j]).trim().equals("null")
						|| String.valueOf(obj[i][j]).trim().equals(null)) {
				} else {
					flag = true;
					break;
				}
			} // end of for loop

			/**
			 * Make a vector object containing non-blank and non-null rows of
			 * column
			 */
			if (flag) {
				Vector childVect = new Vector();
				for (int j = 0; j < obj[0].length; j++) {
					childVect.add(obj[i][j]);
				}
				mainVect.add(childVect);
			}
		} // end of for loop

		/**
		 * Make values as null for some blank or null value rows
		 */
		String[][] colData = new String[mainVect.size()][obj[0].length];
		for (int i = 0; i < mainVect.size(); i++) {
			Vector childVect = (Vector) mainVect.get(i);
			for (int j = 0; j < childVect.size(); j++) {
				try {
					if (String.valueOf(obj[i][j]).trim().equals("")
							|| String.valueOf(obj[i][j]).trim().equals("null")) {
						colData[i][j] = null;
					} else {
						colData[i][j] = String.valueOf(childVect.get(j));
					}
				} catch (Exception ae) {
					// logger.error(ae);
					colData[i][j] = null;
				} // end of try-catch block
			} // end of for loop
		} // end of for loop
		return colData;
	}

	/**
	 * This method returns the new LinkedHashMap sorted with values. Values will
	 * be sorted as value of passed comparator if ascendingOrder is null or in
	 * order of passed ascendingOrder if it is not null. If null values exist
	 * they will be put in the last for true value of ascendingOrder or will be
	 * put on top of the returned LinkedHashMap for false value of
	 * ascendingOrder. If there are duplicate values they will come together at
	 * the values ordering order but ordering between same multiple values is
	 * ramdom. Passed Map will be intect.
	 * 
	 * @param inMap
	 *            Map to be sorted
	 * @param comparator
	 *            Values will be sorted as per passed Comparater
	 * @param ascendingOrder
	 *            Values will be sorted as per value of ascendingOrder
	 * @return LinkedHashMap Sorted new LinkedHashMap
	 */
	public static LinkedHashMap sortMapByValue(Map inMap,
			Comparator comparator, Boolean ascendingOrder) {
		int iSize = inMap.size();

		// Create new LinkedHashMap that need to be returned
		LinkedHashMap sortedMap = new LinkedHashMap(iSize);

		Collection values = inMap.values();
		ArrayList valueList = new ArrayList(values); // To get List of all
		// values in passed Map
		HashSet distinctValues = new HashSet(values); // To know the distinct
		// values in passed Map

		// Do handing for null values. remove them from the list that will be
		// used for sorting
		int iNullValueCount = 0; // Total number of null values present in
		// passed Map
		if (distinctValues.contains(null)) {
			distinctValues.remove(null);
			for (int i = 0; i < valueList.size(); i++) {
				if (valueList.get(i) == null) {
					valueList.remove(i);
					iNullValueCount++;
					i--;
					continue;
				}
			}
		}

		// Sort the values of the passed Map
		if (ascendingOrder == null) {
			// If Boolean ascendingOrder is null, use passed comparator for
			// order of sorting values
			Collections.sort(valueList, comparator);
		} else if (ascendingOrder.booleanValue()) {
			// If Boolean ascendingOrder is not null and is true, sort values in
			// ascending order
			Collections.sort(valueList);
		} else {
			// If Boolean ascendingOrder is not null and is false, sort values
			// in descending order
			Collections.sort(valueList);
			Collections.reverse(valueList);
		}

		// Check if there are multiple same values exist in passed Map (not
		// considering null values)
		boolean bAllDistinct = true;
		if (iSize != (distinctValues.size() + iNullValueCount))
			bAllDistinct = false;

		Object key = null, value = null, sortedValue;
		Set keySet = null;
		Iterator itKeyList = null;
		HashMap hmTmpMap = new HashMap(iSize);
		HashMap hmNullValueMap = new HashMap();

		if (bAllDistinct) {
			// There are no multiple same values in the passed map (without
			// consedring null)
			keySet = inMap.keySet();
			itKeyList = keySet.iterator();
			while (itKeyList.hasNext()) {
				key = itKeyList.next();
				value = inMap.get(key);

				if (value != null)
					hmTmpMap.put(value, key); // Prepare new temp HashMap with
				// value=key combination
				else
					hmNullValueMap.put(key, value); // Keep all null values in a
				// new temp Map
			}

			if (ascendingOrder != null && !ascendingOrder.booleanValue()) {
				// As it is descending order, Add Null Values in first place of
				// the LinkedHasMap
				sortedMap.putAll(hmNullValueMap);
			}

			// Put all not null values in returning LinkedHashMap
			for (int i = 0; i < valueList.size(); i++) {
				value = valueList.get(i);
				key = hmTmpMap.get(value);

				sortedMap.put(key, value);
			}

			if (ascendingOrder == null || ascendingOrder.booleanValue()) {
				// Add Null Values in the last of the LinkedHasMap
				sortedMap.putAll(hmNullValueMap);
			}
		} else {
			// There are some multiple values (with out considering null)
			keySet = inMap.keySet();
			itKeyList = keySet.iterator();
			while (itKeyList.hasNext()) {
				key = itKeyList.next();
				value = inMap.get(key);

				if (value != null)
					hmTmpMap.put(key, value); // Prepare new temp HashMap with
				// key=value combination
				else
					hmNullValueMap.put(key, value); // Keep all null values in a
				// new temp Map
			}

			if (ascendingOrder != null && !ascendingOrder.booleanValue()) {
				// As it is descending order, Add Null Values in first place of
				// the LinkedHasMap
				sortedMap.putAll(hmNullValueMap);
			}

			// Put all not null values in returning LinkedHashMap
			for (int i = 0; i < valueList.size(); i++) {
				sortedValue = valueList.get(i);

				// Search this value in temp HashMap and if found remove it
				keySet = hmTmpMap.keySet();
				itKeyList = keySet.iterator();
				while (itKeyList.hasNext()) {
					key = itKeyList.next();
					value = hmTmpMap.get(key);
					if (value.equals(sortedValue)) {
						sortedMap.put(key, value);
						hmTmpMap.remove(key);
						break;
					}
				}
			}

			if (ascendingOrder == null || ascendingOrder.booleanValue()) {
				// Add Null Values in the last of the LinkedHasMap
				sortedMap.putAll(hmNullValueMap);
			}
		}

		return sortedMap;
	}

	/**
	 * Calculates the amount as per the formula
	 */

	/**
	 * Calculates the amount as per the formula
	 */

	public static double twoDecimal(double Rval, int Rpl) {

		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return (double) tmp / p;

	}

	/**
	 * @param doublValue
	 * @return string as rounded number after decimal point up to 2 numbers.
	 */
	public static String twoDecimals(double doublValue) {
		return twoDecimals("" + doublValue);
	}

	/**
	 * @param string
	 * @return string as rounded number after decimal point up to 2 numbers.
	 */
	public static String twoDecimals(String string) {
		String str = "";
		String str1 = "";
		Vector strVec = new Vector();
		java.util.StringTokenizer stkr = new java.util.StringTokenizer(string,
				".");

		while (stkr.hasMoreElements()) {
			strVec.add(stkr.nextToken());
		}
		try {
			/**
			 * Precede number with 0, if there is only one number after decimal
			 * point
			 */
			int i = ((String) strVec.elementAt(1)).length();
			if (i == 1) {
				str1 = (String) strVec.elementAt(1) + "0";
			} else {
				str1 = ((String) strVec.elementAt(1)).substring(0, 2);
			}
			str = (String) strVec.elementAt(0) + "." + str1;
		} catch (Exception e) {
			// logger.error(e);
			char c = string.charAt(string.length() - 1);
			if (c == '.') {
				str = string + "00";
			} else {
				str = string + ".00";
			}
		} // end of try-catch block
		return str;
	}

	boolean debug = true;

	/**
	 * Returns true, if date is Sunday
	 */

	/**
	 * Adds alert messages
	 */
	/**
	 * @param context
	 * @param session
	 * @param applType
	 * @param applCode
	 * @param empCode
	 * @return blank string
	 */
	public String addAlert(ServletContext context, HttpSession session,
			String applType, String applCode, String empCode) {
		UserProfileModel userModel = new UserProfileModel();
		userModel.initiate(context, session);
		userModel.addAlertMessages(applType, applCode, empCode);
		userModel.terminate();
		return "";
	}

	/**
	 * Compare two dates
	 */
	/**
	 * @param date1
	 * @param date2
	 * @return int (if fromdate > todate return 1 ; if fromdate < todate return
	 *         -1; if fromdate == todate return 0 ; if invalid date format
	 *         return 9999 ;)
	 */
	public int checkDate(String date1, String date2) {
		try {
			String DATE_FORMAT = "dd-MM-yyyy";

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);

			/**
			 * Parses text from the beginning of the given string to produce a
			 * date. The method may not use the entire text of the given string
			 */
			java.util.Date dt1 = sdf.parse(date1);

			/**
			 * Gets a calendar using the default time zone and locale
			 */
			Calendar c1 = Calendar.getInstance();
			c1.setTime(dt1);// Sets Calendar's time with the given Date

			java.util.Date dt2 = sdf.parse(date2);

			Calendar c2 = Calendar.getInstance();
			c2.setTime(dt2);
			return c1.compareTo(c2);// Compares the time values (millisecond
			// offsets) represented by two Calendar
			// objects
		} catch (Exception ex) {
			// logger.error(ex);
		} // end of try-catch block
		return 9999;
	}

	/**
	 * Read the contents of the file
	 */

	/**
	 * Counts number of Saturdays and Sundays in a month
	 */
	/**
	 * @param first
	 * @param second
	 * @return int as total number of Saturdays and Sundays
	 */
	public int countNumberOfSaturdaysAndSundays(GregorianCalendar first,
			GregorianCalendar second) {
		int count = 0;
		Calendar currentcalendarday = first;
		Calendar lastcalendarday = second;
		while (!currentcalendarday.equals(lastcalendarday)) {// Day is not
			// same
			if (isOnSaturday(currentcalendarday)) {// Day is Saturday
				count++;
			}
			if (isOnSunday(currentcalendarday)) {// Day is Sunday
				count++;
			}
			currentcalendarday.add(Calendar.DATE, 1);// Add a day in Calendar
		}
		return count;
	}

	/**
	 * Counts number of 2nd and 4th Saturdays and Sundays in a month
	 */
	/**
	 * @param first
	 * @param second
	 * @return int as total number of 2nd and 4th Saturdays and Sundays
	 */

	public int countNumberOfSecSaturdaysAndSundays(GregorianCalendar first,
			GregorianCalendar second) {
		int count = 0;
		Calendar currentcalendarday = first;
		Calendar lastcalendarday = second;
		if (currentcalendarday.equals(lastcalendarday)) {// Day is same
			if (isOnSecSaturday(currentcalendarday)) {// Day is 2nd or 4th
				// Saturday
				count++;
			}
			if (isOnSunday(currentcalendarday)) {// Day is Sunday
				count++;
			}
		} else {
			while (!currentcalendarday.equals(lastcalendarday)) {// Day is
				// not same
				if (isOnSecSaturday(currentcalendarday)) {// Day is 2nd or 4th
					// Saturday
					count++;
				}
				if (isOnSunday(currentcalendarday)) {// Day is Sunday
					count++;
				}
				currentcalendarday.add(Calendar.DATE, 1);// Add a day in
				// Calendar
			} // end of while loop
			if (currentcalendarday.equals(lastcalendarday)) {// Day is same
				if (isOnSecSaturday(currentcalendarday)) {// Day is 2nd or 4th
					// Saturday
					count++;
				}
				if (isOnSunday(currentcalendarday)) {// Day is Sunday
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Counts number of Sundays in a month
	 */
	/**
	 * @param first
	 * @param second
	 * @return int number of Sundays
	 */
	public int countNumberOfSundays(GregorianCalendar first,
			GregorianCalendar second) {
		int count = 0;
		Calendar currentcalendarday = first;
		Calendar lastcalendarday = second;
		while (!currentcalendarday.equals(lastcalendarday)) {// Day is not
			// same
			if (isOnSunday(currentcalendarday)) {// Day is Sunday
				count++;
			}
			currentcalendarday.add(Calendar.DATE, 1);// Add a day in Calendar
		}
		return count;
	}

	/**
	 * Calculates the amount as per the formula
	 */
	/**
	 * @param empCode
	 * @param formula
	 * @param context
	 * @param session
	 * @return String as calculated amount
	 */
	public String generateFormula(String empCode, String formula,
			ServletContext context, HttpSession session) {
		UserProfileModel userModel = new UserProfileModel();
		userModel.initiate(context, session);
		String resulFormula = userModel.generateFormula(empCode, formula, "");
		userModel.terminate();
		return resulFormula;
	}

	/**
	 * @param empCode
	 * @param formula
	 * @param context
	 * @param session
	 * @param old
	 * @return String as calculated amount
	 */
	public String generateFormula(String empCode, String formula,
			ServletContext context, HttpSession session, String old) {
		UserProfileModel userModel = new UserProfileModel();
		userModel.initiate(context, session);
		String resulFormula = userModel.generateFormula(empCode, formula, old);
		userModel.terminate();
		return resulFormula;
	}

	/**
	 * @param credit_amount
	 * @param formula
	 * @param context
	 * @param session
	 * @return String as calculated amount
	 */
	public String generateFormulaPay(Object[][] credit_amount, String formula,
			ServletContext context, HttpSession session) {
		UserProfileModel userModel = new UserProfileModel();
		userModel.initiate(context, session);
		String resulFormula = userModel.generateFormulaPay(credit_amount,
				formula, "");
		userModel.terminate();
		return resulFormula;
	}

	/**
	 * @param empCode
	 * @param formula
	 * @param ledgerCode
	 * @param year
	 * @param context
	 * @param session
	 * @return String as calculated amount
	 */
	public String generateFormulaSal(String empCode, String formula,
			String ledgerCode, String year, ServletContext context,
			HttpSession session) {
		UserProfileModel userModel = new UserProfileModel();
		userModel.initiate(context, session);
		String resulFormula = userModel.generateFormulaSal(empCode, formula,
				ledgerCode, year);
		userModel.terminate();
		return resulFormula;
	}

	/**
	 * Returns Date in GregorianCalendar format
	 */
	/**
	 * @param datestring
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getCalanderDate(String datestring) {
		int day = Integer.parseInt(datestring.substring(0, datestring
				.indexOf("-")));
		int month = Integer.parseInt(datestring.substring(datestring
				.indexOf("-") + 1, datestring.lastIndexOf("-")));
		int year = Integer.parseInt(datestring.substring(datestring
				.lastIndexOf("-") + 1));
		return new GregorianCalendar(year, month - 1, day);
	}

	public int getCalanderDay(String datestring) {
		int day = Integer.parseInt(datestring.substring(0, datestring
				.indexOf("-")));
		int month = Integer.parseInt(datestring.substring(datestring
				.indexOf("-") + 1, datestring.lastIndexOf("-")));
		int year = Integer.parseInt(datestring.substring(datestring
				.lastIndexOf("-") + 1));
		return new GregorianCalendar(year, month - 1, day).DAY_OF_WEEK;
	}

	/**
	 * Returns true, if date is Saturday
	 */
	/**
	 * @param calendardate
	 * @return boolean
	 */
	public boolean isOnSaturday(Calendar calendardate) {
		if (debug) {
			if (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				// logger.info("Debug: " +
				// String.valueOf(calendardate.get(Calendar.DATE)) + "-"
				// + String.valueOf(calendardate.get(Calendar.MONTH) + 1) + "-"
				// + String.valueOf(calendardate.get(Calendar.YEAR))
				// + " is a SATURDAY.");
			}
		}
		return (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
	}

	/**
	 * Returns true, if date is 2nd Saturday
	 */
	/**
	 * @param calendardate
	 * @return
	 */

	public boolean isOnSecSaturday(Calendar calendardate) {
		if (debug) {
			if (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				// logger.info("Debug: " +
				// String.valueOf(calendardate.get(Calendar.DATE)) + "-"
				// + String.valueOf(calendardate.get(Calendar.MONTH) + 1) + "-"
				// + String.valueOf(calendardate.get(Calendar.YEAR))
				// + " is a SATURDAY.");
			}
		}
		return (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && (calendardate
				.get(Calendar.WEEK_OF_MONTH) == 2 || calendardate
				.get(Calendar.WEEK_OF_MONTH) == 4));
	}

	/**
	 * @param calendardate
	 * @return boolean
	 */
	public boolean isOnSunday(Calendar calendardate) {
		if (debug) {
			if (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				// logger.info("Debug: " +
				// String.valueOf(calendardate.get(Calendar.DATE)) + "-"
				// + String.valueOf(calendardate.get(Calendar.MONTH) + 1) + "-"
				// + String.valueOf(calendardate.get(Calendar.YEAR))
				// + " is a SUNDAY.");
			}
		}
		return (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
	}

	/**
	 * Combines two objects, if emp code is same
	 */
	/**
	 * @param currentData
	 * @param previousData
	 * @return Object[][] as combine data
	 */
	public Object[][] joinArrayByEmpCode(Object[][] currentData,
			Object[][] previousData) {
		Vector vectObj = new Vector();
		int countRow = 0;
		try {
			for (int i = 0; i < currentData.length; i++) {
				vectObj.add(currentData[i]);
				for (int j = 0; j < previousData.length; j++) {
					if (String.valueOf(currentData[i][0]).equals(
							String.valueOf(previousData[j][0]))) {// Emp code
						// is same
						vectObj.add(previousData[j]);
						countRow++;
					}
				} // end of for loop
			} // end of for loop
		} catch (Exception e) {
			// logger.error(e);
		} // end of try-catch block
		Object[][] resultObj = new Object[currentData.length + countRow][currentData[0].length];

		for (int i = 0; i < vectObj.size(); i++) {
			Object[] obj = (Object[]) vectObj.get(i);
			resultObj[i] = obj;// Copies both objects into new object
		}
		return resultObj;
	}

	/**
	 * @param filePath
	 * @return string as contents of file
	 * @throws java.io.IOException
	 */
	public String readFileAsString(String filePath) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {// Read characters into an
			// array till some input is
			// available
			String readData = String.valueOf(buf, 0, numRead);// Converts
			// character
			// into String
			fileData.append(readData);// Append string to character sequence
			buf = new char[1024];
		}
		reader.close();// Close the stream
		return fileData.toString();
	}

	/**
	 * Neglect Saturdays and Sundays, when holiday is available on that date
	 */
	/**
	 * @param holiday
	 * @return int as remaining holidays
	 */
	public int removeSecSatSunFromHolidayList(Object[][] holiday) {
		int count = 0;
		int actualHoliday = 0;

		if (holiday.length > 0) {
			for (int i = 0; i < holiday.length; i++) {
				Calendar currentcalendarday = getCalanderDate(String
						.valueOf(holiday[i][0]));
				if (isOnSecSaturday(currentcalendarday)) {// Day is Saturday
					count++;
				}
				if (isOnSunday(currentcalendarday)) {// Day is Sunday
					count++;
				}
			} // end of for loop
			actualHoliday = holiday.length - count;
		}
		return actualHoliday;
	}

	/**
	 * Neglect Sundays, when holiday is available on that date
	 */
	/**
	 * @param holiday
	 * @return int as remaining holidays
	 */
	public int removeSunFromHolidayList(Object[][] holiday) {
		int count = 0;
		int actualHoliday = 0;

		if (holiday != null && holiday.length > 0) {
			for (int i = 0; i < holiday.length; i++) {
				Calendar currentcalendarday = getCalanderDate(String
						.valueOf(holiday[i][0]));
				if (isOnSunday(currentcalendarday)) {// Day is Sunday
					count++;
				}
			} // end of for loop
			actualHoliday = holiday.length - count;
		}
		return actualHoliday;
	}

	public static String getFormulaCreditHeads(String formula) {

		String[] operant = formula.split("#");
		// logger.info(""+operant.length);

		int creditCount = 0;
		String creditStr = "";
		for (int i = 0; i < operant.length; i++) {
			if (operant[i].startsWith("C")) {
				String credit = operant[i].substring(1, operant[i].length());
				creditStr += credit + ",";
				creditCount++;
			}
		}
		String creditCodes = "0";
		try {
			creditCodes = creditStr.substring(0, creditStr.length() - 1);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return creditCodes;
	}

	/**
	 * return only days string if zero hours & zero minutes present in days(in
	 * days:hours:minute format) for e.g. passing "30d:00h:00m" to this method
	 * will return "30d"
	 */
	/**
	 * @param salDays
	 *            in 'days:hours:minute' format
	 * @return salDays String
	 */
	public static String getViewDays(String salDays) {
		String result = "";
		try {
			if (salDays.contains("d")) {

				Object[] daysData = salDays.split(":");

				double hrs = Double
						.parseDouble(String.valueOf(daysData[1]).substring(0,
								String.valueOf(daysData[1]).length() - 1));
				double mm = Double
						.parseDouble(String.valueOf(daysData[2]).substring(0,
								String.valueOf(daysData[2]).length() - 1));

				if (hrs == 0 && mm == 0)
					result = String.valueOf(daysData[0]).substring(0,
							String.valueOf(daysData[0]).length() - 1);
				else
					result = salDays;
			} else {
				result = salDays;
			}

		} catch (Exception e) {
			// logger.error("Exception getting getViewDays to split the days
			// object to hrs ---------"+e);

		}
		return result;
	}
	public static Object[][] intZero(Object[][] tempObj){
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "0";
				}
			}
		} catch (Exception e) {
			
		}
		return tempObj;
	}
	/**
	 * 
	 * @param salaObject 
	 * @param arrearsObject
	 * @param x - Reference Index/Matching Index
	 * @param colNo - Column values to be added
	 * @param totalCol - No of columns to be returned
	 * @return
	 */
	public static Object[][] consolidateArrearsObject(Object salaObject[][],
			Object arrearsObject[][], int x, int[] colNo, int totalCol) {
		Object[][] consolidatedObject = null;
		Object[][] arrearsNewObject = null;
		Object[][] salaNewObject = null;
		HashMap<String, Object[]> empMap = new HashMap<String, Object[]>();
		try {
			if (salaObject != null || salaObject.length > 0) {
				salaNewObject = new Object[salaObject.length][totalCol];
				salaNewObject=intZero(salaNewObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (arrearsObject != null || arrearsObject.length > 0) {
				arrearsNewObject = new Object[arrearsObject.length][totalCol];
				arrearsNewObject =intZero(arrearsNewObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			try {
				if (salaObject != null || salaObject.length > 0) {
					try {
					for (int j = 0; j < salaNewObject.length; j++) {
						try {
							for (int k = 0; k < salaNewObject[0].length; k++) {
								salaNewObject[j][k] = salaObject[j][k];
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					}catch (Exception e) {
						logger.error("Exception in salaNewObject" + e);
					}
					
					for (int i = 0; i < salaNewObject.length; i++) {
						if(i==1480){
							logger.info("");
						}
						if (empMap.containsKey(String
								.valueOf(salaNewObject[i][x]))) {
							Object[] temObj = empMap.get(String
									.valueOf(salaNewObject[i][x]));
							for (int j = 0; j < colNo.length; j++) {

								temObj[colNo[j]] = Double.parseDouble(String
										.valueOf(temObj[colNo[j]]))
										+ Double
												.parseDouble(String
														.valueOf(salaNewObject[i][colNo[j]]));
							}
							empMap.put(String.valueOf(salaNewObject[i][x]),
									temObj);
						} else {
							empMap.put(String.valueOf(salaNewObject[i][x]),
									salaNewObject[i]);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in salaNewObject" + e);
			}
			try {
				if (arrearsObject != null || arrearsObject.length > 0) {
					try {
						for (int j = 0; j < arrearsNewObject.length; j++) {
							try {
								for (int k = 0; k < arrearsNewObject[0].length; k++) {
									arrearsNewObject[j][k] = arrearsObject[j][k];
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					} catch (Exception e) {
						logger.error("Exception in arrearsNewObject" + e);
					}
					for (int i = 0; i < arrearsNewObject.length; i++) {
						if (empMap.containsKey(String
								.valueOf(arrearsNewObject[i][x]))) {
							Object[] temObj = empMap.get(String
									.valueOf(arrearsNewObject[i][x]));
							for (int j = 0; j < colNo.length; j++) {

								temObj[colNo[j]] = Double.parseDouble(String
										.valueOf(temObj[colNo[j]]))
										+ Double
												.parseDouble(String
														.valueOf(arrearsNewObject[i][colNo[j]]));
							}
							empMap.put(String.valueOf(arrearsNewObject[i][x]),
									temObj);
						} else {
							empMap.put(String.valueOf(arrearsNewObject[i][x]),
									arrearsNewObject[i]);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in arrearsNewObject" + e);
			}

			if (empMap.size() > 0) {
				consolidatedObject = new Object[empMap.size()][totalCol];
				int i = 0;
				for (Iterator<String> iter = empMap.keySet().iterator(); iter
						.hasNext();) {
					String empId = iter.next();

					consolidatedObject[i++] = empMap.get(empId);

				}
			}
			/*
			 * for (int j = 0; j < consolidatedObject.length; j++) { for (int k =
			 * 0; k < consolidatedObject[0].length; k++) {
			 * logger.info("consolidatedObject[==" + consolidatedObject[j][k]); } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consolidatedObject;
	}

	/**
	 * This method is used to transverse the object as row to column
	 * 
	 * @author Lakkichand_Golegaonkar
	 * @Date 19th Oct 2010
	 * @param inputObj
	 * @param uniqueCols
	 * @param headerColIndex
	 * @param valuesColIndex
	 * @param defaultValue
	 * @return transverse object
	 */
	public static Object[][] transverse(Object[][] inputObj, int[] uniqueCols,
			int headerColIndex, int valuesColIndex, String defaultValue,
			boolean showHeader, String[] uniqueColHeader) {

		Object[][] transObject = null;
		// Defines no of rows
		LinkedHashMap<String, HashMap> outerMap = new LinkedHashMap<String, HashMap>();
		// Defines no of columns to be transversed
		LinkedHashMap<String, String> headerMap = new LinkedHashMap<String, String>();
		Object headerObj[] = null;
		for (int i = 0; i < inputObj.length; i++) {
			String key = "";
			headerMap.put(String.valueOf(inputObj[i][headerColIndex]), String
					.valueOf(inputObj[i][headerColIndex]));
			for (int j = 0; j < uniqueCols.length; j++) {
				key += String.valueOf(inputObj[i][uniqueCols[j]]) + "~";
			}
			key = key.substring(0, key.length() - 1);
			if (outerMap.containsKey(key)) {
				HashMap<String, String> innerMap = outerMap.get(key);
				innerMap.put(String.valueOf(inputObj[i][headerColIndex]),
						String.valueOf(inputObj[i][valuesColIndex]));
				outerMap.put(key, innerMap);
			} else {
				HashMap<String, String> innerMap = new HashMap<String, String>();
				innerMap.put(String.valueOf(inputObj[i][headerColIndex]),
						String.valueOf(inputObj[i][valuesColIndex]));
				outerMap.put(key, innerMap);
			}
		}
		try {
			transObject = new Object[outerMap.size()][uniqueCols.length
					+ headerMap.size()];
			int rowNo = 0;
			String uniqueKeys = "";

			headerObj = new Object[headerMap.size()];
			int headCount = 0;
			for (Iterator headerItt = headerMap.keySet().iterator(); headerItt
					.hasNext();) {
				headerObj[headCount++] = headerMap.get(headerItt.next());
			}
			for (Iterator iterator = outerMap.keySet().iterator(); iterator
					.hasNext();) {
				uniqueKeys = (String) iterator.next();
				HashMap<String, String> innerMap = (HashMap<String, String>) outerMap
						.get(uniqueKeys);

				for (int i = 0; i < uniqueCols.length; i++) {
					transObject[rowNo][i] = uniqueKeys.split("~")[i];
				}
				String innerKey = "";
				int headerCount = headerMap.size();

				for (Iterator iterator1 = innerMap.keySet().iterator(); iterator1
						.hasNext();) {
					innerKey = (String) iterator1.next();

					for (int i = 0; i < headerObj.length; i++) {
						if (String.valueOf(headerObj[i]).equals(innerKey)) {
							transObject[rowNo][i + uniqueCols.length] = innerMap
									.get(innerKey);
						}

					}
				}
				rowNo++;
			}
			outerMap.clear();
			outerMap = null;
			transObject = checkNullObjArr(transObject, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (showHeader) {
				Object[][] column_headers = new Object[1][transObject[0].length];
				for (int i = 0; i < uniqueColHeader.length; i++) {
					column_headers[0][i] = uniqueColHeader[i];
				}
				for (int i = 0; i < headerObj.length; i++) {
					column_headers[0][i + uniqueColHeader.length] = headerObj[i];
				}
				transObject = Utility.joinArrays(column_headers, transObject,
						1, 0);
				System.out.println("transObject ... " + transObject.length);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return transObject;
	}

	/**
	 * * This method is used to shuffle the columns of two dimensional object
	 * 
	 * @author Lakkichand_Golegaonkar
	 * @param inputObj
	 * @param shuffleFromCol
	 * @param shuffleToCol
	 * @return
	 */
	public static Object[][] shuffleColumnObj(Object[][] inputObj,
			int[] shuffleFromCol, int[] shuffleToCol) {
		if (inputObj != null) {
			if (shuffleFromCol != null && shuffleToCol != null) {
				String tempValue = "";

				for (int i = 0; i < inputObj.length; i++) {
					for (int k = 0; k < shuffleFromCol.length; k++) {
						tempValue = String
								.valueOf(inputObj[i][shuffleFromCol[k]]);
						if (shuffleFromCol[k] < shuffleToCol[k]) {
							for (int j = shuffleFromCol[k]; j < shuffleToCol[k] ; j++) {
								inputObj[i][j] = inputObj[i][j + 1];
							}
						} else if (shuffleFromCol[k] > shuffleToCol[k]) {
							for (int j = shuffleToCol[k]; j < shuffleFromCol[k] ; j--) {
								inputObj[i][j] = inputObj[i][j - 1];
							}
						}
						inputObj[i][shuffleToCol[k]] = tempValue;
						

					}

				}
			}
		}

		return inputObj;
	}

	public static Object[] shuffleColumnObj(Object[] inputObj,
			int[] shuffleFromCol, int[] shuffleToCol) {

		if (inputObj != null) {
			if (shuffleFromCol != null && shuffleToCol != null) {
				String tempValue = "";
				for (int k = 0; k < shuffleFromCol.length; k++) {
					tempValue = String.valueOf(inputObj[shuffleFromCol[k]]);
					if (shuffleFromCol[k] < shuffleToCol[k]) {
						for (int j = shuffleFromCol[k]; j < shuffleToCol[k]; j++) {
							inputObj[j] = inputObj[j + 1];
						}
					} else if (shuffleFromCol[k] > shuffleToCol[k]) {
						for (int j = shuffleToCol[k]; j < shuffleFromCol[k] ; j--) {
							inputObj[j] = inputObj[j - 1];
						}
					}

					inputObj[shuffleToCol[k]] = tempValue;
				}
			}
		}
		return inputObj;
	}

	/**
	 * * * This method is used to group the objects for unique key inputs
	 * 
	 * @author Lakkichand_Golegaonkar
	 * @param inputObj
	 * @param uniqueCols
	 * @return group of objects
	 */
	public static Map getGroupObj(Object[][] inputObj, int[] uniqueCols) {

		LinkedHashMap<String, Vector> outerMap = new LinkedHashMap<String, Vector>();

		for (int i = 0; i < inputObj.length; i++) {
			String key = "";
			for (int j = 0; j < uniqueCols.length; j++) {
				key += String.valueOf(inputObj[i][uniqueCols[j]]) + "~";
			}
			key = key.substring(0, key.length() - 1);

			if (outerMap.containsKey(key)) {
				Vector innerVect = outerMap.get(key);
				innerVect.add(inputObj[i]);
				outerMap.put(key, innerVect);
			} else {
				Vector innerVect = new Vector();
				innerVect.add(inputObj[i]);
				outerMap.put(key, innerVect);
			}

		}
		String uniqueKeys = "";
		int rowNo = 0;
		LinkedHashMap<String, Object[][]> returnMap = new LinkedHashMap<String, Object[][]>();
		for (Iterator iterator = outerMap.keySet().iterator(); iterator
				.hasNext();) {
			uniqueKeys = (String) iterator.next();
			Vector<Object[][]> innerVector = (Vector<Object[][]>) outerMap
					.get(uniqueKeys);
			Object[][] vectObject = new Object[innerVector.size()][inputObj[0].length];
			for (int i = 0; i < innerVector.size(); i++) {
				vectObject[i] = innerVector.get(i);
			}

			returnMap.put(uniqueKeys, vectObject);
		}
		return returnMap;
	}

	/**
	 * 
	 * @param inputObj
	 * @param keyCols
	 * @return
	 */
	public static Map toMap(Object[][] inputObj, int[] keyCols) {
		LinkedHashMap<String, Object[]> linkedMap = new LinkedHashMap<String, Object[]>();
		for (int i = 0; i < inputObj.length; i++) {
			String key = "";
			for (int j = 0; j < keyCols.length; j++) {
				key += String.valueOf(inputObj[i][keyCols[j]]) + "~";
			}
			key = key.substring(0, key.length() - 1);
			linkedMap.put(key, inputObj[i]);

			Object[] obX = linkedMap.get(key);
			/*
			 * for (int j = 0; j < obX.length; j++) { System.out.println("obX
			 * value "+obX[j]); }
			 */

		}
		return linkedMap;
	}

	public static Object[][] toArray(Map map) {
		Object[][] outObj = new Object[map.size()][];
		int i = 0;
		for (Iterator iterator1 = map.keySet().iterator(); iterator1.hasNext();) {
			outObj[i++] = (Object[]) map.get(iterator1.next());
		}
		return (Object[][]) outObj;
	}

	/**
	 * 
	 * @param firstObj
	 * @param secondObj
	 * @param firstUniqueCol
	 * @param secondUniqueCol
	 * @param removeSecondObjCols
	 * @return
	 */
	public static Object[][] joinArrayWithUniqueCol(Object[][] firstObj,
			Object[][] secondObj, int firstUniqueCol, int secondUniqueCol,
			int[] removeSecondObjCols) {
		String DefaultValue = "";
		return joinArrayWithUniqueCol(firstObj, secondObj, firstUniqueCol,
				secondUniqueCol, removeSecondObjCols, DefaultValue);
	}

	public static Object[][] joinArrayWithUniqueCol(Object[][] firstObj,
			Object[][] secondObj, int firstUniqueCol, int secondUniqueCol,
			int[] removeSecondObjCols, String defaultValue) {

		LinkedHashMap firstMap = (LinkedHashMap) toMap(firstObj,
				new int[] { firstUniqueCol });
		LinkedHashMap secondMap = (LinkedHashMap) toMap(secondObj,
				new int[] { secondUniqueCol });
		String uniqueKey = "";
		for (Iterator iterator1 = firstMap.keySet().iterator(); iterator1
				.hasNext();) {
			uniqueKey = (String) iterator1.next();
			Object[] obj1 = (Object[]) firstMap.get(uniqueKey);
			Object[] obj2 = new Object[0];
			if (secondMap.containsKey(uniqueKey)) {
				obj2 = (Object[]) secondMap.get(uniqueKey);
			}
			try {
				if (obj2.length == 0) {
					obj2 = new Object[secondObj[0].length];
					for (int i = 0; i < obj2.length; i++) {
						obj2[i] = defaultValue;
					}
				}
			} catch (Exception e) {
			}
			Object[] obj3 = new Object[obj1.length + obj2.length];
			for (int i = 0; i < obj1.length; i++) {
				obj3[i] = obj1[i];
			}
			int j = 0;
			for (int i = obj1.length; i < obj1.length + obj2.length; i++) {
				obj3[i] = obj2[j++];
			}
			firstMap.put(uniqueKey, obj3);
		}

		Object[][] returnedObj = toArray(firstMap);
		// System.out.println("returnedObj_________length"+returnedObj[0].length);
		// return returnedObj;
		logger.info("removeSecondObjCols.length==="
				+ removeSecondObjCols.length);
		for (int j = 0; j < removeSecondObjCols.length; j++) {
			logger.info("firstObj[0].length=====" + firstObj[0].length);
			logger.info("removeSecondObjCols=====" + removeSecondObjCols[j]);
			removeSecondObjCols[j] = firstObj[0].length
					+ removeSecondObjCols[j];
		}
		secondObj = null;
		secondMap = null;
		firstObj = null;
		return removeColumns((Object[][]) returnedObj, removeSecondObjCols);
	}
	/**
	 * 
	 * @param inputObj
	 * @param removecols
	 * @return
	 */
	public static Object[][] removeColumns(Object[][] inputObj, int[] removecols) {
		Object[][] outputObj = new Object[inputObj.length][inputObj[0].length
				- removecols.length];
		for (int i = 0; i < inputObj.length; i++) {
			int columnCount = 0;
			for (int j = 0; j < inputObj[0].length; j++) {
				boolean flag = true;
				for (int j2 = 0; j2 < removecols.length; j2++) {
					if (j == removecols[j2]) {
						flag = false;
					}
				}
				if (flag) {
					outputObj[i][columnCount++] = inputObj[i][j];
				}

			}

		}
		return outputObj;
	}

	/**
	 * 
	 * @param inputObj
	 * @param removecols
	 * @return
	 */
	public static Object[] removeColumns(Object[] inputObj, int[] removecols) {
		Object[] outputObj = new Object[inputObj.length - removecols.length];
		int columnCount = 0;
		for (int i = 0; i < inputObj.length; i++) {
			boolean flag = true;
			for (int j2 = 0; j2 < removecols.length; j2++) {
				if (i == removecols[j2]) {
					flag = false;
				}
			}
			if (flag) {
				outputObj[columnCount++] = inputObj[i];
			}

		}
		return outputObj;
	}
	public static String[] removeColumns(String[] inputObj, int[] removecols) {
		String[] outputObj = new String[inputObj.length - removecols.length];
		int columnCount = 0;
		for (int i = 0; i < inputObj.length; i++) {
			boolean flag = true;
			for (int j2 = 0; j2 < removecols.length; j2++) {
				if (i == removecols[j2]) {
					flag = false;
				}
			}
			if (flag) {
				outputObj[columnCount++] = inputObj[i];
			}

		}
		return outputObj;
	}
	public static int[] removeColumns(int[] inputObj, int[] removecols) {
		int[] outputObj = new int[inputObj.length - removecols.length];
		int columnCount = 0;
		for (int i = 0; i < inputObj.length; i++) {
			boolean flag = true;
			for (int j2 = 0; j2 < removecols.length; j2++) {
				if (i == removecols[j2]) {
					flag = false;
				}
			}
			if (flag) {
				outputObj[columnCount++] = inputObj[i];
			}

		}
		return outputObj;
	}

	public static Object[] joinOneDimArray(Object[] obj1, Object[] obj2) {
		Object[] obj3 = new Object[obj1.length + obj2.length];
		for (int i = 0; i < obj1.length; i++) {
			obj3[i] = obj1[i];
		}
		for (int i = obj1.length; i < (obj1.length + obj2.length); i++) {
			obj3[i] = obj2[i - obj1.length];
		}
		return obj3;
	}
	
	/**
	 * @return a number within the range specified.
	 */
	public static int getRandomNumber(int range){
		Random random = new Random();
	    int randomInt = random.nextInt(range);
		return randomInt;
	}

	/**Method : getNameByKey.
	 * Purpose : this method is used to get  names  
	 * @param key : key
	 * @return String
	 * obj[I][0]=CODE
	 * obj[I][1]=NAME
	 */
	public static String getNameByKey(Object[][] obj,String key){
		String result="";
		try {
			if (key != null || key.length()>0) {
				key="0,"+key;
				String[]str=key.split(",");				
				if (obj != null && obj.length > 0 && str!=null && str.length>0) {
					for(int j = 0; j < str.length; j++) {
						for (int i = 0; i < obj.length; i++) {
							if(String.valueOf(str[j]).equals(String.valueOf(obj[i][0]))){
							result += String.valueOf(obj[i][1])+",";
							}
						}
					}	
					result = result.substring(0, result.length()-1);
				}			
			} 
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static LinkedHashMap sortHashMapByKey(HashMap passedMap) {
		   List mapKeys = new ArrayList(passedMap.keySet());
		  //List mapValues = new ArrayList(passedMap.values());
		    Collections.sort(mapKeys);

		   LinkedHashMap sortedMap =  new LinkedHashMap();

		 
		    Iterator keyIt = mapKeys.iterator();

		    while (keyIt.hasNext()) {
		        Object key = keyIt.next();
		        String comp1 = passedMap.get(key).toString();
		        sortedMap.put(comp1, comp1);
		   
		    }

		
		return sortedMap;
	}
	public static Object[][]to2DObject(Vector vector,int columns){
		Object[][] toObject=null;
		try {
			int vectCount=0;
			toObject=new Object[vector.size()/columns][columns];
			for (int i = 0; i < vector.size() / columns; i++) {
				for (int j = 0; j < columns; j++) {
					toObject[i][j]=vector.get(vectCount++);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toObject;
	}
}