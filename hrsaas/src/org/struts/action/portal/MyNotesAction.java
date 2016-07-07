package org.struts.action.portal;

import org.paradyne.bean.portal.MyNotes;
import org.paradyne.model.portal.MyNotesModel;
import org.struts.lib.ParaActionSupport;

public class MyNotesAction extends ParaActionSupport {

	MyNotes bean = null;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new MyNotes();
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			MyNotesModel model = new MyNotesModel();
			model.initiate(context, session);
			System.out.println("In input Method----------------------");
			Object[][] dataObj = model.setData(bean);
			request.setAttribute("noteData", dataObj);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public MyNotes getBean() {
		return bean;
	}

	public void setBean(MyNotes bean) {
		this.bean = bean;
	}

	public void reset() {
		bean.setDescription("");
		bean.setHiddenNoteId("");
	}

	public String addNotes() {
		try {
			MyNotesModel model = new MyNotesModel();
			model.initiate(context, session);
			boolean result = false;

			String noteId = request.getParameter("noteCode");
			String note = request.getParameter("note");
			System.out.println("noteId             " + noteId);

			if (noteId.equals("")) {

				System.out.println("note             " + note);
				result = model.addNotes(bean, note);
				reset();
				/*if (result) {
					addActionMessage("Note added successfully");
					
				} else {
					addActionMessage("Note can not be added");
				}*/
			} else {
				result = model.updateNotes(bean, note, noteId);
				reset();
				/*if (result) {
					addActionMessage("Note modified successfully");
					
				} else {
					addActionMessage("Note can not be modified");
				}*/
			}

			Object[][] dataObj = model.setData(bean);
			request.setAttribute("noteData", dataObj);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	 

	 public String delete() {
		try {
			MyNotesModel model = new MyNotesModel();
			model.initiate(context, session);
			boolean result = model.deleteRecord(bean);
			/*if (result) {
				addActionMessage("Note deleted successfully.");
			} else {
				addActionMessage("Note can not be deleted ");
			}*/
			Object[][] dataObj = model.setData(bean);
			request.setAttribute("noteData", dataObj);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	} 

}
