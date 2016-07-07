/**
 * 
 */
package org.paradyne.model.Training;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Training.TrainingCalendarBean;
import org.paradyne.bean.admin.master.CenterMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author ankita.wankhade
 *
 */
public class TrainingCalendarModel extends ModelBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/* generating the list onload */
	public void getTableData(TrainingCalendarBean trainingCalendar,
			HttpServletRequest request) {
		
		// get the training data to display in the list 

		try {
			Object[][] repData = getSqlModel().getSingleResult(getQuery(1));
			if (repData != null && repData.length > 0) {
				trainingCalendar.setTotalRecords(String.valueOf(repData.length)); // to display the total records inn the list  
				trainingCalendar.setModeLength("true");
				String[] pageIndex = Utility.doPaging(trainingCalendar.getMyPage(),
						repData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2]))); // to display  the total number of page 
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3]))); // to display the page number 
				if (pageIndex[4].equals("1"))
					trainingCalendar.setMyPage("1");
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					TrainingCalendarBean bean1 = new TrainingCalendarBean();
					bean1.setTrainingID(checkNull(String.valueOf(repData[i][0]))); // training ID
					bean1.setCourseName(checkNull(String.valueOf(repData[i][1]))); // course name
					bean1.setCutOffDate(checkNull(String.valueOf(repData[i][2]))); // enrollmnt cutoff date

					bean1.setScheduleStartDate(checkNull(String.valueOf(repData[i][3]))); // schedule start date
					bean1.setScheduleEndDate(checkNull(String.valueOf(repData[i][4])));//schedule end date
					List.add(bean1); // add the details in the list
				}//end of loop
				trainingCalendar.setCourseList(List);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	
	/* for selecting the data from list 
	public void calforedit(TrainingCalendarBean trainingCalendar) {

		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER where CENTER_ID= "
				+ trainingCalendar.getHiddencode();

		// to get the record in double clck for update
		Object[][] data = getSqlModel().getSingleResult(query);
		trainingCalendar.setTrainingID(String.valueOf(data[0][0])); // training id
		trainingCalendar.setCourseName(String.valueOf(data[0][1])); // course name

	}
	*/
/**
 *  to check the null value 
 * @param result
 * @return
 */
public String checkNull(String result) {
	if (result == null || result.equals("null")) {
		return "";
	}//end of if
	else {
		return result;
	}//end of else
}



}
