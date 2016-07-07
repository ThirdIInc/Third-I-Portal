package org.paradyne.lib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class DateUtility {
	public String[][] getYearRange(String d1, String d2) {
		// 01-01-2007

		int y1 = Integer.parseInt(d1.substring(6, 10));
		int y2 = Integer.parseInt(d2.substring(6, 10));
		int diff = y2 - y1;
		int incr = y1;
		String data[][] = new String[diff + 1][2];
		for (int i = 0; i <= diff; i++) {
			// Start year
			if (i == 0) {
				data[i][0] = d1;
				if (y1 == y2) {
					data[i][1] = d2;
				} else {
					data[i][1] = "31-12-" + y1;
				}
			}

			else if (i == diff) {
				// end year
				data[i][0] = "01-01-" + y2;
				data[i][1] = d2;
			}

			else {
				// middle year
				data[i][0] = "01-01-" + (++incr);
				data[i][1] = "31-12-" + (incr);
			}

		}

		for (int j = 0; j < data.length; j++) {
			System.out.println("" + data[j][0] + "-----" + data[j][1]);
		}
		return data;

	}

	public String[][] getDateRange(String d1, String d2) {
		/*
		 * // 01-01-2007 Calendar c = Calendar.getInstance();
		 * c.setTime(Utility.getDate(settlDetails.getSepDate()));
		 */

		int y1 = Integer.parseInt(d1.substring(6, 10));
		int y2 = Integer.parseInt(d2.substring(6, 10));

		int diff = y2 - y1;
		int incr = y1;
		String data[][] = null;
		for (int i = 0; i <= diff; i++) {
			// Start year

			if (i == 0) {

				int firstMonth = Integer.parseInt(d1.substring(3, 5));
				System.out.println("firstMonth........" + firstMonth);
				int lastMonth = Integer.parseInt(d2.substring(3, 5));
				int totalMonths = lastMonth - firstMonth + 1;
				data = new String[totalMonths][4];
				for (int j = 0; j < totalMonths; j++) {

					if (j == 0) {
						System.out.println("in j==0");
						Calendar c = Calendar.getInstance();
						c.setTime(Utility.getDate(d1));
						System.out.println("get time...j=0...." + c.getTime());
						int days = Integer.parseInt(d1.substring(0, 2));
						int totalDays = c
								.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
						data[j][0] = String.valueOf(totalDays - days);
						data[j][1] = String.valueOf(totalDays);
						data[j][2] = String.valueOf(firstMonth);
						data[j][3] = String.valueOf(y1);
						if (totalMonths == 1) {
							int lastdays = Integer.parseInt(d2.substring(0, 2));
							data[j][0] = String.valueOf(lastdays - days);
							data[j][1] = String.valueOf(totalDays);
							data[j][2] = String.valueOf(firstMonth);
							data[j][3] = String.valueOf(y1);
						}
					} else if (j == totalMonths - 1) {
						System.out.println("in j==totalMonths-1");
						Calendar c = Calendar.getInstance();
						c.setTime(Utility.getDate(d2));
						int days = Integer.parseInt(d2.substring(0, 2));
						int totalDays = c
								.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
						data[j][0] = String.valueOf(days);
						data[j][1] = String.valueOf(totalDays);
						data[j][2] = String.valueOf(lastMonth);
						data[j][3] = String.valueOf(y2);

					} else {
						System.out.println("else");
						firstMonth++;
						Calendar c = Calendar.getInstance();
						c.setTime(Utility
								.getDate("01-" + firstMonth + "-" + y2));
						int totalDays = c
								.getActualMaximum(java.util.Calendar.MONTH);
						data[j][0] = String.valueOf(totalDays);
						data[j][1] = String.valueOf(totalDays);
						System.out.println("get time....." + c.getTime());

						data[j][2] = String.valueOf(firstMonth);
						data[j][3] = String.valueOf(y2);
					}

				}
			} else if (i == diff) {

			}

			/*
			 * if (i == 0) { data[i][0] = d1; if (y1 == y2) {
			 * 
			 * data[i][1] = d2; } else { data[i][1] = "31-12-" + y1; } }
			 * 
			 * else if (i == diff) { // end year data[i][0] = "01-01-" + y2;
			 * data[i][1] = d2; }
			 * 
			 * else { // middle year data[i][0] = "01-01-" + (++incr);
			 * data[i][1] = "31-12-" + (incr); }
			 */

		}

		for (int j = 0; j < data.length; j++) {
			System.out.println("" + data[j][0] + "-----" + data[j][1]);
		}
		return data;

	}

	public String[][] getRangeObject(String string, String string2, int i) {
		// TODO Auto-generated method stub
		String noticeDate = getNoticeDate(string, string2, i);
		System.out.println("noticeDate--------------" + noticeDate);
		String[][] years = getYearRange(string, noticeDate);

		Vector<String> arr = new Vector<String>();
		for (int j = 0; j < years.length; j++) {

			String[][] s = getDateRange(years[j][0], years[j][1]);
			for (int k = 0; k < s.length; k++) {
				arr.add(s[k][0]);
				arr.add(s[k][1]);
			}

		}
		String[][] days_monObj = new String[arr.size() / 2][2];
		int counter = 0;
		for (int j = 0; j < arr.size() / 2; j++) {
			days_monObj[j][0] = arr.get(counter++);
			days_monObj[j][1] = arr.get(counter++);
		}
		return days_monObj;
	}

	public String getNoticeDate(String string, String string2, int i) {
		// TODO Auto-generated method stub
		int year = Integer.parseInt(string.substring(6, 10));
		int month = Integer.parseInt(string.substring(3, 5));
		int day = Integer.parseInt(string.substring(0, 2));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c1 = Calendar.getInstance();
		c1.set(year, month - 1, day);// separation date

		System.out.println("Date is : " + sdf.format(c1.getTime()));
		if (string2.equals("M")) {
			c1.add(Calendar.MONTH, +i);// add months
		} else if (string2.equals("D")) {
			c1.add(Calendar.DATE, i-1);// add months
		}
		// substract 1 month
		System.out.println("Date + 3 month : " + sdf.format(c1.getTime()));
		return sdf.format(c1.getTime());
	}

	public String[][] getRangeofDates(String d1, String d2) {
		/*
		 * // 01-01-2007 Calendar c = Calendar.getInstance();
		 * c.setTime(Utility.getDate(settlDetails.getSepDate()));
		 */

		int y1 = Integer.parseInt(d1.substring(6, 10));
		int y2 = Integer.parseInt(d2.substring(6, 10));

		int diff = y2 - y1;
		int incr = y1;
		String data[][] = null;
		for (int i = 0; i <= diff; i++) {
			// Start year

			if (i == 0) {

				int firstMonth = Integer.parseInt(d1.substring(3, 5));
				int lastMonth = Integer.parseInt(d2.substring(3, 5));
				int totalMonths = lastMonth - firstMonth + 1;
				data = new String[totalMonths][3];
				for (int j = 0; j < totalMonths; j++) {

					if (j == 0) {
						System.out.println("in j==0");
						Calendar c = Calendar.getInstance();
						c.setTime(Utility.getDate(d1));
						int days = Integer.parseInt(d1.substring(0, 2));
						int totalDays = c
								.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
						System.out.println("in j==0 totalDays..." + totalDays);
						data[j][0] = d1;
						data[j][1] = totalDays + "-" + get2CharMonth(firstMonth) + "-" + y1;
						data[j][2] = String.valueOf(totalDays);
						if (totalMonths == 1) {
							int lastdays = Integer.parseInt(d2.substring(0, 2));
							data[j][0] = d1;
							data[j][1] = d2;
							data[j][2] = String.valueOf(totalDays);
						}
					} else if (j == totalMonths - 1) {
						System.out.println("in j==totalMonths-1");
						Calendar c = Calendar.getInstance();
						c.setTime(Utility.getDate(d2));
						int days = Integer.parseInt(d2.substring(0, 2));
						int totalDays = c
								.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
						System.out.println("in j==totalMonths-1 totalDays..."
								+ totalDays);
						firstMonth++;
						data[j][0] = "01-" + get2CharMonth(firstMonth) + "-" + y2;
						data[j][1] = d2;
						data[j][2] = String.valueOf(totalDays);

					} else {
						System.out.println("else");
						firstMonth++;
						Calendar c = Calendar.getInstance();
						c.setTime(Utility
								.getDate("01-" + get2CharMonth(firstMonth) + "-" + y2));
						int totalDays = c
								.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
						System.out.println("else totalDays..." + totalDays);
						data[j][0] = "01-" + get2CharMonth(firstMonth) + "-" + y2;
						data[j][1] = totalDays + "-" + get2CharMonth(firstMonth) + "-" + y2;
						data[j][2] = String.valueOf(totalDays);

					}

				}
			} else if (i == diff) {

			}
		}

		for (int j = 0; j < data.length; j++) {
			System.out.println("" + data[j][0] + "-----" + data[j][1] + "-----"
					+ data[j][2]);
		}
		return data;

	}

	public String[][] getDatesBetween(String d1, String d2) {
		// TODO Auto-generated method stub

		String[][] years = getYearRange(d1, d2);

		Vector<String> arr = new Vector<String>();
		for (int j = 0; j < years.length; j++) {

			String[][] s = getRangeofDates(years[j][0], years[j][1]);
			for (int k = 0; k < s.length; k++) {
				arr.add(s[k][0]);
				arr.add(s[k][1]);
				arr.add(s[k][2]);
			}

		}
		String[][] days_monObj = new String[arr.size() / 3][3];
		int counter = 0;
		for (int j = 0; j < arr.size() / 3; j++) {
			days_monObj[j][0] = arr.get(counter++);
			days_monObj[j][1] = arr.get(counter++);
			days_monObj[j][2] = arr.get(counter++);
		}
		return days_monObj;
	}

	public String[][] splitDatesObj(String d1, String d2,String firstORsecond) {
		/*
		 * // 01-01-2007 Calendar c = Calendar.getInstance();
		 * c.setTime(Utility.getDate(settlDetails.getSepDate()));
		 */
		
		//String[][]data = new String[2][2];
		String[][]data =null;
		System.out.println("d1........."+d1);
		System.out.println("d1........."+d2);
		int dayFirst = Integer.parseInt(d1.substring(0, 2));
		int daySecond = Integer.parseInt(d2.substring(0, 2));
		int monthFirst = Integer.parseInt(d1.substring(3, 5));
		int monthSecond = Integer.parseInt(d2.substring(3, 5));
		int y1 = Integer.parseInt(d1.substring(6, 10));
		int y2 = Integer.parseInt(d2.substring(6, 10));
		if(d1.equals(d2)){
			data = new String[1][2];
			data[0][0]=d1;
			data[0][1]=d1;
			
		}else if(firstORsecond.equals("F")){
				data = new String[2][2];
				data[0][0]=d1;
				data[0][1]=d1;
				data[1][0]=get2CharMonth(dayFirst+1)+"-"+monthFirst+"-"+y1;
				data[1][1]=d2;
			}
		else if(firstORsecond.equals("S")){
				data = new String[2][2];
				data[0][0]=d1;
				data[0][1]=get2CharMonth(daySecond-1)+"-"+monthSecond+"-"+y2;
				data[1][0]=d2;
				data[1][1]=d2;
			}
		else if(firstORsecond.equals("B")){
				if((daySecond-dayFirst)==1){
					data = new String[2][2];
					data[0][0]=d1;
					data[0][1]=d1;
					//second part
					data[1][0]=d2;
					data[1][1]=d2;
				}
				else{
					data = new String[3][2];
					data[0][0]=d1;
					data[0][1]=get2CharMonth(dayFirst+1)+"-"+monthFirst+"-"+y1;
					//second part
					data[1][0]=get2CharMonth(dayFirst+2)+"-"+monthFirst+"-"+y1;
					data[1][1]=get2CharMonth(daySecond-1)+"-"+monthSecond+"-"+y2;
					//3rd part
					data[2][0]=d2;
					data[2][1]=d2;
				}
			}
		else{
			data = new String[1][2];
			data[0][0]=d1;
			data[0][1]=d2;
			
		}
		for (int i = 0; i < data.length; i++) {
			System.out.println("data..................."+data[i][0]);
			System.out.println("data..................."+data[i][1]);
		}
		
		return data;
		
	}
	
	
	//Method added by VISHWAMBHAR this method is used for date split up 
	
	/**
	 * 
	 * @param d1  -----Date
	 * @param loopLength---upto end date
	 * @return  String[][]
	 */
	 
	public static String[][] splitDatesObj(String d1, int loopLength)
	{
		String [][] dateObj = null; ;
		try {
			String strFrmDate = "";
			dateObj = new String[loopLength][1];
			strFrmDate = d1;
			for (int i = 0; i < loopLength; i++) {
				if(i==0)
				{
					dateObj[i][0] = strFrmDate;
				}
				else
				{
					Calendar cal = Calendar.getInstance();
					cal.setTime(Utility.getDate(strFrmDate));
					cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					strFrmDate = sdf.format(cal.getTime());
					dateObj[i][0] = strFrmDate;
				}
			//	System.out.println("Date Split up ----------"+ strFrmDate);
	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateObj ;
	}

	public static void main(String args[]) {
		DateUtility c = new DateUtility();

		String noticeDate = c.getNoticeDate("27-07-2009", "D", 45);
		String[][] rangeObj = c.getDatesBetween("01-08-2009", "04-09-2009");
		for (int i = 0; i < rangeObj.length; i++) {
			for (int j = 0; j < rangeObj[0].length; j++) {
				System.out.println("rangeObj[i][j_______" + rangeObj[i][j]);
			}
		}

		Object[][] test = new Object[rangeObj.length][];
		for (int i = 0; i < test.length; i++) {
			test[i] = rangeObj[i];
		}
		for (int i = 0; i < test.length; i++) {
			System.out.println("obj........" + test[i][0]);
			System.out.println("obj........" + test[i][1]);
			System.out.println("obj........" + test[i][2]);
		}

		System.out
				.println("------------- Object------------------------------");

		for (int i = 0; i < rangeObj.length; i++) {
			String[] splitObj = rangeObj[i][1].split("-");
			if (i == 0) {
				System.out.print("----if----" + rangeObj[i][0].substring(0, 2));
				System.out.print("||" + splitObj[1]);
				// System.out.print("||"+rangeObj[i][1].substring(6,10));
				System.out.println("||" + splitObj[2] + "||" + rangeObj[i][2]);
			} else {
				System.out.println("--else----"
						+ rangeObj[i][1].substring(0, 2)
						+ "||"
						+ splitObj[1]
						+ "||"
						+ rangeObj[i][1].substring(rangeObj[i][1].length() - 4,
								rangeObj[i][1].length()) + "||"
						+ rangeObj[i][2]);
			}

		}

		System.out
				.println("------------- Object------------------------------");
	}
	public String get2CharMonth(int mm) {
		String month=""+mm;
		if(mm<10){
			month="0"+mm;
		}
		return month;
	}
}