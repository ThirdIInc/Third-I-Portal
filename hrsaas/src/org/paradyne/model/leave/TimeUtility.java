package org.paradyne.model.leave;
/**
 * SHASHIKANT DOKE
 * @author AA0476
 *
 */
public class TimeUtility {
	/**
	 * CONVERT HH:MM INTO MINUTS
	 * 
	 * @param result
	 * @return
	 */
	public static int getMinute(String result) {
		int min = 0;
		if (result == null || result.equals("null") || result.equals("")) {
			return min;
		}// end of if
		else {
			if (result.contains(":")) {
				String[] chk = result.split(":");
				try {
					min = Integer.parseInt(chk[0].trim()) * 60;
					min = min + Integer.parseInt(chk[1].trim());
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
			return min;
		}// end of else
	}
	/**
	 * CONVERT MINUTS INTO HH:MM FORMATE
	 * 
	 * @param result
	 * @return
	 */
	public static String getHH_MM(int minuts) {
		String hrs = "00";
		String minute = "00";
		int mm = 0;
		int hh = 0;
		try {
			hh = minuts / 60;
			mm = (minuts % 60);
			hrs = "" + hh;
			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (hrs + ":" + minute);
	}
	/**
	 * CONVERT H:M INTO HH:MM FORMATE
	 * 
	 * @param result
	 * @return
	 */
	public static String getHH_MM(String hh_mm) {
		String hrs = "00";
		String minute = "00";
		int mm = 0;
		int hh = 0;
		try {
			String[] data = hh_mm.split(":");
			hh = Integer.parseInt(String.valueOf(data[0]).trim());
			mm = Integer.parseInt(String.valueOf(data[1]).trim());
			hrs = "" + hh;
			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (hrs + ":" + minute);
	}
	/**
	 * CONVERT MINUTS INTO DAY HH:MM
	 * 
	 * @param result
	 * @return
	 */
	public static String getDAYS_HH_MM(int minuts, int shiftMinuts) {
		System.out.println();
		System.out.println("total min  " + minuts + "  ======  Shift min  "
				+ shiftMinuts);
		String days = "0 Days 00:00";
		String hrs = "00";
		String minute = "00";
		int dd = 0;
		int mm = 0;
		int hh = 0;
		float newDD = 0.0f;
		try {
			dd = minuts > shiftMinuts ? minuts / shiftMinuts : 0;
			minuts = dd > 0 ? (minuts - (dd * shiftMinuts)) : minuts;
			hh = minuts >= 60 ? minuts / 60 : 0;
			mm = hh > 0 ? minuts - (hh * 60) : minuts;
			/**
			 * IF HRS GRATER THAN HALF DAY CONVERT INTO HALF DAY
			 * 
			 */
			// dd=hh>=(shiftMinuts/2):dd
			newDD = dd;
			
			
			/*
			 * if((hh)>=(shiftMinuts/120)){ newDD=newDD+0.5f;
			 * hh=hh-(shiftMinuts/120);
			 * System.out.println("hh------------------------- : "+hh); }
			 */
			if ((hh * 60) >= (shiftMinuts / 2)) {
				newDD = newDD + 0.5f;
				hh = (hh * 60) - (shiftMinuts / 2);
				// hh=hh/60>0?hh/60:
				mm = mm + hh % 60;
				hh = hh / 60;				
				hh=(mm>=60)?(hh+1):hh;
				mm=(mm>=60)?(mm-60):mm;
			}

			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		days = newDD + " Days " + hrs + ":" + minute;
		return days;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
}
