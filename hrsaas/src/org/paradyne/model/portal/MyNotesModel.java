package org.paradyne.model.portal;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.portal.MyNotes;
import org.paradyne.lib.ModelBase;

public class MyNotesModel extends ModelBase {

	public boolean addNotes(MyNotes bean, String note) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			Object[][] saveObj = new Object[1][2];
			saveObj[0][0] = bean.getUserEmpId();
			saveObj[0][1] = note;

			String query = " INSERT INTO HRMS_NOTES (NOTE_ID ,EMP_ID,NOTES_DESCRIPTION,NOTE_CREATED_DATE)VALUES((SELECT NVL(MAX(NOTE_ID),0)+1 FROM HRMS_NOTES),?,?,sysdate)";
			result = getSqlModel().singleExecute(query, saveObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public Object[][] setData(MyNotes bean) {
		Object[][] dataObj = null;
		try {
			String query = "  SELECT NOTE_ID ,EMP_ID,substr(NOTES_DESCRIPTION,0,80),to_char(NOTE_CREATED_DATE,'DD-MM-YYYY HH:MI:SS'), to_char(NOTE_UPDATED_DATE,'DD-MM-YYYY HH:MI:SS'),NOTES_DESCRIPTION FROM HRMS_NOTES WHERE EMP_ID="
					+ bean.getUserEmpId() + " ORDER BY NOTE_ID  DESC ";
			dataObj = getSqlModel().getSingleResult(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataObj;
	}

	 

	public boolean updateNotes(MyNotes bean,String note,String noteId) {
		boolean result = false;
		try {
			Object updateObj[][] = new Object[1][2];
			updateObj[0][0] = note;
			updateObj[0][1] = noteId;
			String updateQUery = "  update HRMS_NOTES set NOTES_DESCRIPTION=?, NOTE_UPDATED_DATE=sysdate "
					+ " where NOTE_ID=? ";
			result = getSqlModel().singleExecute(updateQUery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	 public boolean deleteRecord(MyNotes bean) {
		boolean result = false;
		try {
			String delQuery = "  delete from HRMS_NOTES  where NOTE_ID="
					+ bean.getHiddenEditCode().trim();
			result = getSqlModel().singleExecute(delQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} 

}
