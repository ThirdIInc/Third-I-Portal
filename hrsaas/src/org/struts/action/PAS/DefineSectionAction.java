package org.struts.action.PAS;

import org.paradyne.bean.PAS.DefineSection;

import org.paradyne.model.PAS.DefineSectionModel;
import org.struts.lib.ParaActionSupport;

public class DefineSectionAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DefineSectionAction.class);

	DefineSection defineSection;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		defineSection = new DefineSection();
		defineSection.setMenuCode(748);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return defineSection;
	}

	public DefineSection getDefineSection() {
		return defineSection;
	}

	public void setDefineSection(DefineSection defineSection) {
		this.defineSection = defineSection;
	}

	public String input() {
		getNavigationPanel(11);
		DefineSectionModel model = new DefineSectionModel();
		model.initiate(context, session);

		String tempCode = (String) request.getAttribute("tempCode");
		defineSection.setTemplateCode(tempCode);

		Object[][] phaseData = model.getPhases(defineSection);
		if (String.valueOf(defineSection.getResetFLag()).equals("true"))
			model.getSectionDetails(defineSection, request);

		logger.info("phase length action" + phaseData.length);
		model.checkAppraisalStart(defineSection);
		request.setAttribute("phaseCode", phaseData);
		model.terminate();
		return INPUT;
	}

	public String addSection() {
		request.setAttribute("tempCode", defineSection.getTemplateCode());
		getNavigationPanel(11);
		DefineSectionModel model = new DefineSectionModel();
		model.initiate(context, session);
		input();
		model.addSectionToList(defineSection, request);

		defineSection.setSecName("");

		model.terminate();
		return SUCCESS;
	}

	public String shuffleRow() {

		request.setAttribute("tempCode", defineSection.getTemplateCode());
		getNavigationPanel(11);
		DefineSectionModel model = new DefineSectionModel();
		model.initiate(context, session);

		String buttonType = request.getParameter("type");
		String SrNo = defineSection.getSrNo();
		String[] sectionName = request.getParameterValues("secName");
		String[] sectionCode = request.getParameterValues("secCode");
		Object[] phase = request.getParameterValues("phaseCode");

		Object[][] visibility = new Object[sectionName.length][phase.length];
		Object[][] rating = new Object[sectionName.length][phase.length];
		Object[][] comment = new Object[sectionName.length][phase.length];

		Object[] temp = null;
		for (int i = 0; i < sectionName.length; i++) {
			for (int j = 0; j < phase.length; j++) {
				temp = request.getParameterValues("h_" + i + "_" + j + "_V");
				logger.info("temp1 V" + String.valueOf(temp[0]));
				visibility[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_R");
				logger.info("temp2 R" + String.valueOf(temp[0]));
				rating[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_C");
				logger.info("temp3 C" + String.valueOf(temp[0]));
				comment[i][j] = temp[0];
			}

		}
		input();
		model.shuffleList(defineSection, buttonType, SrNo, sectionName,
				sectionCode, phase, visibility, rating, comment, request);
		defineSection.setSecName("");
		model.terminate();
		return SUCCESS;
	}

	public String reset() {
		getNavigationPanel(11);
		defineSection.setResetFLag("false");
		input();
		return SUCCESS;
	}

	public String save() {
		getNavigationPanel(11);

		boolean insert = false;
		boolean update = false;

		Object[] section = request.getParameterValues("secName");
		Object[] phase = request.getParameterValues("phaseCode");
		String[] sectionCode = request.getParameterValues("secCode");
		String[] priority = request.getParameterValues("priority");

		Object[][] visibility = new Object[section.length][phase.length];
		Object[][] rating = new Object[section.length][phase.length];
		Object[][] comment = new Object[section.length][phase.length];

		Object[] temp = null;
		for (int i = 0; i < section.length; i++) {
			for (int j = 0; j < phase.length; j++) {
				temp = request.getParameterValues("h_" + i + "_" + j + "_V");
				// logger.info("temp1 " + String.valueOf(temp[0]));
				visibility[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_R");
				// logger.info("temp2 " + String.valueOf(temp[0]));
				rating[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_C");
				// logger.info("temp3 " + String.valueOf(temp[0]));
				comment[i][j] = temp[0];
			}

		}

		DefineSectionModel model = new DefineSectionModel();
		model.initiate(context, session);

		if (defineSection.getAddFLag().equals("true")) {
			insert = model.saveSection(defineSection, section, phase,
					visibility, rating, comment, priority);
			if (insert)
				addActionMessage(getMessage("save"));
			else
				addActionMessage(getMessage("save.error"));

		} else {
			update = model.updateSection(defineSection, sectionCode, section,
					phase, visibility, rating, comment, priority);
			
			/*update = model.updateNew(defineSection, sectionCode, section,
					phase, visibility, rating, comment, priority);*/
			
			if (update) {
				addActionMessage(getMessage("update"));
			} else
				addActionMessage(getMessage("update.error"));

		}
		request.setAttribute("tempCode", defineSection.getTemplateCode());
		input();
		model.terminate();
		return SUCCESS;
	}

	public String saveAndNext() {
		getNavigationPanel(11);

		boolean insert = false;
		boolean update = false;

		Object[] section = request.getParameterValues("secName");
		Object[] phase = request.getParameterValues("phaseCode");
		String[] sectionCode = request.getParameterValues("secCode");
		String[] priority = request.getParameterValues("priority");

		Object[][] visibility = new Object[section.length][phase.length];
		Object[][] rating = new Object[section.length][phase.length];
		Object[][] comment = new Object[section.length][phase.length];

		Object[] temp = null;
		for (int i = 0; i < section.length; i++) {
			for (int j = 0; j < phase.length; j++) {
				temp = request.getParameterValues("h_" + i + "_" + j + "_V");
				// logger.info("temp1 " + String.valueOf(temp[0]));
				visibility[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_R");
				// logger.info("temp2 " + String.valueOf(temp[0]));
				rating[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_C");
				// logger.info("temp3 " + String.valueOf(temp[0]));
				comment[i][j] = temp[0];
			}

		}

		DefineSectionModel model = new DefineSectionModel();
		model.initiate(context, session);

		if (defineSection.getAddFLag().equals("true")) {
			insert = model.saveSection(defineSection, section, phase,
					visibility, rating, comment, priority);

		} else {
			update = model.updateSection(defineSection, sectionCode, section,
			phase, visibility, rating, comment, priority);
	
			/*update = model.updateNew(defineSection, sectionCode, section,
			phase, visibility, rating, comment, priority);*/

		}
		request.setAttribute("tempCode", defineSection.getTemplateCode());
		//input();
		model.terminate();
		return "next";

	}

	public String saveAndPrevious() {
		getNavigationPanel(11);

		boolean insert = false;
		boolean update = false;

		Object[] section = request.getParameterValues("secName");
		Object[] phase = request.getParameterValues("phaseCode");
		String[] sectionCode = request.getParameterValues("secCode");
		String[] priority = request.getParameterValues("priority");

		Object[][] visibility = new Object[section.length][phase.length];
		Object[][] rating = new Object[section.length][phase.length];
		Object[][] comment = new Object[section.length][phase.length];

		Object[] temp = null;
		for (int i = 0; i < section.length; i++) {
			for (int j = 0; j < phase.length; j++) {
				temp = request.getParameterValues("h_" + i + "_" + j + "_V");
				// logger.info("temp1 " + String.valueOf(temp[0]));
				visibility[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_R");
				// logger.info("temp2 " + String.valueOf(temp[0]));
				rating[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_C");
				// logger.info("temp3 " + String.valueOf(temp[0]));
				comment[i][j] = temp[0];
			}

		}

		DefineSectionModel model = new DefineSectionModel();
		model.initiate(context, session);

		if (defineSection.getAddFLag().equals("true")) {
			insert = model.saveSection(defineSection, section, phase,
					visibility, rating, comment, priority);

		} else {
			update = model.updateSection(defineSection, sectionCode, section,
			phase, visibility, rating, comment, priority);
	
			/*update = model.updateNew(defineSection, sectionCode, section,
			phase, visibility, rating, comment, priority);*/

		}
		request.setAttribute("tempCode", defineSection.getTemplateCode());
		//input();
		model.terminate();
		return "previous";

	}

	public String removeSection() {

		request.setAttribute("tempCode", defineSection.getTemplateCode());
		getNavigationPanel(11);

		DefineSectionModel model = new DefineSectionModel();
		model.initiate(context, session);

		String SrNo = defineSection.getSrNo();

		String[] sectionName = request.getParameterValues("secName");
		String[] sectionCode = request.getParameterValues("secCode");
		Object[] phase = request.getParameterValues("phaseCode");

		Object[][] visibility = new Object[sectionName.length][phase.length];
		Object[][] rating = new Object[sectionName.length][phase.length];
		Object[][] comment = new Object[sectionName.length][phase.length];

		Object[] temp = null;
		for (int i = 0; i < sectionName.length; i++) {
			for (int j = 0; j < phase.length; j++) {
				temp = request.getParameterValues("h_" + i + "_" + j + "_V");
				logger.info("temp1 V" + String.valueOf(temp[0]));
				visibility[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_R");
				logger.info("temp2 R" + String.valueOf(temp[0]));
				rating[i][j] = temp[0];
				temp = request.getParameterValues("h_" + i + "_" + j + "_C");
				logger.info("temp3 C" + String.valueOf(temp[0]));
				comment[i][j] = temp[0];
			}

		}
		input();
		model.remove(defineSection, SrNo, sectionName, sectionCode, phase,
				visibility, rating, comment, request);
		model.terminate();
		return SUCCESS;
	}

}
