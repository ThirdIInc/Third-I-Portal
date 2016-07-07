package org.struts.action.ApplicationStudio;

import java.io.File;
import java.util.TreeMap;
import org.paradyne.bean.ApplicationStudio.DataModification;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.DataModificationModel;
import org.struts.lib.ParaActionSupport;

public class DataModificationAction extends ParaActionSupport {

	DataModification dm;
	String poolDir = "";
	String fileName = "";
	String comfileName = "";

	public void prepare_local() throws Exception {
		dm = new DataModification();
		dm.setMenuCode(793);
	}

	public Object getModel() {
		return dm;
	}

	/**
	 * @return the dm
	 */
	public DataModification getDm() {
		return dm;
	}

	/**
	 * @param dm
	 *            the dm to set
	 */
	public void setDm(DataModification dm) {
		this.dm = dm;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		DataModificationModel model = new DataModificationModel();
		poolDir = (String) session.getAttribute("session_pool");
		fileName = getText("data_path") + "\\DataModification\\" + poolDir;

		model.initiate(context, session);
		System.out.println("entry to login profile");
		genderOnload();
		marriageOnload();
		bloodGroupOnload();
		payModeOnload();
		addressOnload();
		incrementOnload();
		zoneOnload();
		comboboxOnLoad();
		model.terminate();

	}

	public String save() {
		System.out.println("entry to save");
		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		boolean result = false;

		String modfile = fileName + "\\common\\gender.xml";
		String gendername[] = request.getParameterValues("genderTypeRow");
		String value[] = request.getParameterValues("genderTypeValue");
		System.out.println("length for value" + value.length);
		System.out.println("value" + value[0]);
		String firstletter[] = new String[gendername.length];
		String activetype[] = request.getParameterValues("genderTypeOp");
		System.out.println("length for acivetype" + activetype.length);
		String active[] = new String[gendername.length];

		for (int i = 0; i < gendername.length; i++) {

			// System.out.println(" name
			// ------------------------"+gendername[i]);
			firstletter[i] = value[i];
			System.out.println(" Rak name " + firstletter[i]);

			if (activetype[i] != null && !activetype[i].equals("")) {
				active[i] = "Y";
			} else
				active[i] = "N";
		}

		model.save(modfile, dm, gendername, firstletter, active);
		// addActionMessage("Record saved successfully");
		result = model.saveData(dm, "genderType", gendername, firstletter,
				active);
		if (result) {
			addActionMessage("Record saved successfully");
		} else {
			addActionMessage("Record cannot be saved");
		}
		genderOnload();
		model.terminate();

		return "success";
	}

	public String marriageSave() {
		System.out.println("entry to save");
		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		boolean result = false;
		poolDir = (String) session.getAttribute("session_pool");

		String modfile = fileName + "\\common\\marriage.xml";

		String marriage[] = request.getParameterValues("marriageRow");
		String value[] = request.getParameterValues("marriageValue");
		String marriageactive[] = request.getParameterValues("marriageOp");
		String firstletter[] = new String[marriage.length];
		String active[] = new String[marriage.length];

		for (int i = 0; i < marriage.length; i++) {

			System.out.println("  name ------------------------" + marriage[i]);
			firstletter[i] = value[i];
			System.out.println(" Rak name " + firstletter[i]);
			if (marriageactive[i] != null && !marriageactive[i].equals("")) {
				active[i] = "Y";
			} else
				active[i] = "N";

		}

		model.marriageSave(modfile, dm, marriage, firstletter, active);
		// addActionMessage("Record saved successfully");
		result = model.saveData(dm, "marriage", marriage, firstletter, active);
		if (result) {
			addActionMessage("Record saved successfully");
		} else {
			addActionMessage("Record cannot be saved");
		}
		marriageOnload();
		model.terminate();

		return "success";
	}

	public String bloodSave() {
		System.out.println("entry to save method");
		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		boolean result = false;
		poolDir = (String) session.getAttribute("session_pool");

		String modfile = fileName + "\\common\\Blood.xml";
		String value[] = request.getParameterValues("bloodGroupValue");
		String bloodgroup[] = request.getParameterValues("bloodGroupRow");
		System.out.println("length__________" + value.length);

		String firstletter[] = new String[bloodgroup.length];
		String activetype[] = request.getParameterValues("bloodGroupOp");
		String active[] = new String[bloodgroup.length];
		for (int i = 0; i < bloodgroup.length; i++) {

			System.out.println("  name ------------------------"
					+ bloodgroup[i]);
			firstletter[i] = value[i];
			System.out.println(" Rak name ____________" + firstletter[i]);
			if (activetype[i] != null && !activetype[i].equals("")) {
				active[i] = "Y";
			} else
				active[i] = "N";

		}

		model.bloodGroupSave(modfile, dm, bloodgroup, firstletter, active);
		// addActionMessage("Record saved successfully");
		result = model.saveData(dm, "BloodGroup", bloodgroup, firstletter,
				active);
		if (result) {
			addActionMessage("Record saved successfully");
		} else {
			addActionMessage("Record cannot be saved");
		}
		bloodGroupOnload();
		model.terminate();

		return "success";
	}

	public String paySave() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		boolean result = false;
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+= "\\common\\Paymode.xml";
		String modfile = fileName + "\\common\\Paymode.xml";

		String rowpaymode[] = request.getParameterValues("payModeRow");
		String value[] = request.getParameterValues("payModeValue");
		String firstletter[] = new String[rowpaymode.length];
		String activetype[] = request.getParameterValues("payModeOp");
		System.out.println("length for acivetype" + activetype.length);
		String active[] = new String[rowpaymode.length];

		for (int i = 0; i < rowpaymode.length; i++) {

			System.out.println("  name ------------------------"
					+ rowpaymode[i]);
			firstletter[i] = value[i];
			System.out.println(" Rak name " + firstletter[i]);//
			if (activetype[i] != null && !activetype[i].equals("")) {
				active[i] = "Y";
			} else
				active[i] = "N";

		}

		model.payModeSave(modfile, dm, rowpaymode, firstletter, active);
		// addActionMessage("Record saved successfully");
		result = model.saveData(dm, "payMode", rowpaymode, firstletter, active);
		if (result) {
			addActionMessage("Record saved successfully");
		} else {
			addActionMessage("Record cannot be saved");
		}

		payModeOnload();
		model.terminate();

		return "success";
	}

	public String addressSave() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		boolean result = false;
		poolDir = (String) session.getAttribute("session_pool");

		String modfile = fileName + "\\common\\Address.xml";
		String rowaddressType[] = request.getParameterValues("addressTypeRow");
		String value[] = request.getParameterValues("addressTypeValue");
		String firstletter[] = new String[rowaddressType.length];
		String activetype[] = request.getParameterValues("addressTypeOp");
		System.out.println("length for acivetype" + activetype.length);
		String active[] = new String[rowaddressType.length];

		for (int i = 0; i < rowaddressType.length; i++) {

			System.out.println("  name ------------------------"
					+ rowaddressType[i]);
			firstletter[i] = value[i];
			System.out.println(" Rak name " + firstletter[i]);//
			if (activetype[i] != null && !activetype[i].equals("")) {
				active[i] = "Y";
			} else
				active[i] = "N";

		}

		model.addressModeSave(modfile, dm, rowaddressType, firstletter, active);
		// addActionMessage("Record saved successfully");
		result = model.saveData(dm, "addressType", rowaddressType, firstletter,
				active);
		if (result) {
			addActionMessage("Record saved successfully");
		} else {
			addActionMessage("Record cannot be saved");
		}

		addressOnload();

		model.terminate();

		return "success";
	}

	public String incrementSave() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		boolean result = false;
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+= "\\common\\Increment.xml";
		String modfile = fileName + "\\common\\Increment.xml";
		String rowincrementType[] = request
				.getParameterValues("incrementTypeRow");
		String value[] = request.getParameterValues("incrementTypeValue");
		String firstletter[] = new String[rowincrementType.length];
		String activetype[] = request.getParameterValues("incrementTypeOp");
		System.out.println("length for acivetype" + activetype.length);
		String active[] = new String[rowincrementType.length];

		for (int i = 0; i < rowincrementType.length; i++) {

			System.out.println("  name ------------------------"
					+ rowincrementType[i]);
			firstletter[i] = value[i];
			System.out.println(" Rak name " + firstletter[i]);//
			if (activetype[i] != null && !activetype[i].equals("")) {
				active[i] = "Y";
			} else
				active[i] = "N";

		}

		model.incrementModeSave(modfile, dm, rowincrementType, firstletter,
				active);
		// addActionMessage("Record saved successfully");
		result = model.saveData(dm, "incrementType", rowincrementType,
				firstletter, active);
		if (result) {
			addActionMessage("Record saved successfully");
		} else {
			addActionMessage("Record cannot be saved");
		}

		incrementOnload();

		model.terminate();

		return "success";
	}

	/**
	 * @author MANISH SAKPAL
	 * @return
	 */
	public String zoneSave() {
		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		boolean result = false;
		poolDir = (String) session.getAttribute("session_pool");
		String modfile = fileName + "\\common\\Zone.xml";
		String rowZoneType[] = request.getParameterValues("zoneRow");
		String value[] = request.getParameterValues("zoneValue");
		String firstletter[] = new String[rowZoneType.length];
		String activetype[] = request.getParameterValues("zoneOp");
		System.out.println("length for acivetype" + activetype.length);
		String active[] = new String[rowZoneType.length];

		for (int i = 0; i < rowZoneType.length; i++) {

			System.out.println("  name ------------------------"
					+ rowZoneType[i]);
			firstletter[i] = value[i];
			System.out.println(" Rak name " + firstletter[i]);//
			if (activetype[i] != null && !activetype[i].equals("")) {
				active[i] = "Y";
			} else
				active[i] = "N";

		}

		model.zoneModeSave(modfile, dm, rowZoneType, firstletter, active);
		result = model.saveData(dm, "zone", rowZoneType, firstletter, active);
		if (result) {
			addActionMessage("Record saved successfully");
		} else {
			addActionMessage("Record cannot be saved");
		}

		zoneOnload();
		model.terminate();
		return SUCCESS;
	}

	// for gender
	public String genderOnload() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		Object[][] result = null;

		String modfile = fileName + "\\common\\gender.xml";
		comfileName = getText("data_path")
				+ "\\DataModification\\Default\\common\\gender.xml";
		try {
			File file1 = new File(modfile);
			File file = new File(comfileName);
			if (!file1.exists()) {
				System.out.println("enrty to if");
				result = model.show(new XMLReaderWriter().parse(file), dm);
			} else {

				System.out.println("enrty to else");
				result = model.show(new XMLReaderWriter().parse(file1), dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("genderType", result);
		model.terminate();
		return "success";
	}

	// for marriage

	public String marriageOnload() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+= "\\common\\marriage.xml";
		String modfile = fileName + "\\common\\marriage.xml";
		comfileName = getText("data_path")
				+ "\\DataModification\\Default\\common\\marriage.xml";
		Object[][] result = null;
		try {
			File file1 = new File(modfile);
			File file = new File(comfileName);
			if (!file1.exists()) {
				result = model.showMarriage(new XMLReaderWriter().parse(file),
						dm);
			} else {

				result = model.showMarriage(new XMLReaderWriter().parse(file1),
						dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("marriage", result);
		model.terminate();
		return "success";
	}

	public String bloodGroupOnload() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+="\\common\\Blood.xml";
		String modfile = fileName + "\\common\\Blood.xml";
		comfileName = getText("data_path")
				+ "\\DataModification\\Default\\common\\Blood.xml";
		Object[][] result = null;
		try {
			File file1 = new File(modfile);
			File file = new File(comfileName);
			if (!file1.exists()) {
				result = model.showBloodGroup(
						new XMLReaderWriter().parse(file), dm);
			} else {

				result = model.showBloodGroup(new XMLReaderWriter()
						.parse(file1), dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("bloodGroup", result);
		model.terminate();
		return "success";
	}

	public String payModeOnload() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+= "\\common\\Paymode.xml";
		String modfile = fileName + "\\common\\Paymode.xml";
		comfileName = getText("data_path")
				+ "\\DataModification\\Default\\common\\Paymode.xml";
		Object[][] result = null;
		try {
			File file1 = new File(modfile);
			File file = new File(comfileName);
			if (!file1.exists()) {
				result = model.showPayMode(new XMLReaderWriter().parse(file),
						dm);
			} else {

				result = model.showPayMode(new XMLReaderWriter().parse(file1),
						dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("payMode", result);
		model.terminate();
		return "success";
	}

	public String addressOnload() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+= "\\common\\Address.xml";
		String modfile = fileName + "\\common\\Address.xml";
		comfileName = getText("data_path")
				+ "\\DataModification\\Default\\common\\Address.xml";
		Object[][] result = null;
		try {
			File file1 = new File(modfile);
			File file = new File(comfileName);
			if (!file1.exists()) {
				result = model.showAddress(new XMLReaderWriter().parse(file),
						dm);
			} else {

				result = model.showAddress(new XMLReaderWriter().parse(file1),
						dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("addressType", result);
		model.terminate();
		return "success";
	}

	public String incrementOnload() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+= "\\common\\Increment.xml";
		String modfile = fileName + "\\common\\Increment.xml";
		comfileName = getText("data_path")
				+ "\\DataModification\\Default\\common\\Increment.xml";
		Object[][] result = null;
		try {
			File file1 = new File(modfile);
			File file = new File(comfileName);
			if (!file1.exists()) {
				result = model.showIncrement(new XMLReaderWriter().parse(file),
						dm);
			} else {

				result = model.showIncrement(
						new XMLReaderWriter().parse(file1), dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("incrementType", result);
		model.terminate();
		return "success";
	}

	public String zoneOnload() {
		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		String modfile = fileName + "\\common\\Zone.xml";
		comfileName = getText("data_path")
				+ "\\DataModification\\Default\\common\\Zone.xml";
		Object[][] result = null;
		try {
			File file1 = new File(modfile);
			File file = new File(comfileName);
			if (!file1.exists()) {
				result = model.showZone(new XMLReaderWriter().parse(file), dm);
			} else {

				result = model.showZone(new XMLReaderWriter().parse(file1), dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("zone", result);
		model.terminate();
		return SUCCESS;
	}

	public void comboboxOnLoad() {

		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		poolDir = (String) session.getAttribute("session_pool");
		// fileName+= "\\common\\Increment.xml";
		// String modfile=fileName+ "\\common\\Increment.xml";
		/*
		 * comfileName=getText("data_path")+"\\Labels\\COMMON.properties"; File
		 * file =new File(comfileName);
		 */

		String gender = getMessage("gender");
		String marriage = getMessage("marital.status");

		String paymode = getMessage("pay.mode");
		String address = getMessage("type");
		String incrementtype = getMessage("incrType");
		String blood = getMessage("blood.group");
		String zone = getMessage("zone");

		TreeMap map = new TreeMap();
		map.put("genderType", gender);
		map.put("marriage", marriage);
		map.put("payMode", paymode);
		map.put("addressType", address);
		map.put("incrementType", incrementtype);
		map.put("bloodGroup", blood);
		map.put("zone", zone);
		dm.setMap(map);
		model.terminate();

	}

	public String remove() {

		/*
		 * String checkeddata[]=request.getParameterValues("hdeleteOp");
		 * System.out.println("length of checked data is"+checkeddata.length);
		 * DataModificationModel model= new DataModificationModel();
		 * model.initiate(context, session); for(int i=0;i<checkeddata.length;i++) {
		 * String query="SELECT COUNT(*) FROM HRMS_EMP_OFFC WHERE
		 * EMP_TYPE="+checkeddata[i]; Object[][]
		 * obj=model.getSqlModel().getSingleResult(query);
		 * 
		 *  }
		 */
		DataModificationModel model = new DataModificationModel();
		model.initiate(context, session);
		try {
			String removeRecord[] = request.getParameterValues("confChk");
			System.out.println("for remove------- " + removeRecord.length);
			String totalRecord[] = request.getParameterValues(dm.getDatalist()
					+ "Row");
			System.out
					.println("Combo Value -------------- " + dm.getDatalist());
			System.out.println("In------------ethtrrh--------- "
					+ totalRecord.length);
			String finalObj[][] = new String[totalRecord.length
					- removeRecord.length][1];
			int k = 0;
			int j = 0;
			for (int i = 0; i < totalRecord.length; i++) {
				if (k < removeRecord.length)
					if (i != Integer.parseInt(removeRecord[k]))
						finalObj[j++][0] = totalRecord[i];
					else
						k++;
				else
					finalObj[j++][0] = totalRecord[i];
			}
			request.setAttribute(dm.getDatalist(), finalObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return SUCCESS;
	}

}
