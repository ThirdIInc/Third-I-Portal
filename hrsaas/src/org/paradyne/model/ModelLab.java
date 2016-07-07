package org.paradyne.model;

public class ModelLab {
	public static String getModel(int id) {
		switch(id) {
		case 1 : return "org.paradyne.model.leave.LeaveModel";
		
		default : return "";
		
		}
	}
}