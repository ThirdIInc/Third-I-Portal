package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.DefineSection;
import org.paradyne.lib.ModelBase;

public class DefineSectionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DefineSectionModel.class);

	public Object[][] getPhases(DefineSection bean) {

		Object[] apprCode = new Object[1];
		apprCode[0] = bean.getApprId();

		Object[][] phaseData = getSqlModel().getSingleResult(getQuery(1),
				apprCode);

		ArrayList<Object> list = new ArrayList<Object>();

		if (phaseData != null && phaseData.length != 0) {
			for (int i = 0; i < phaseData.length; i++) {
				DefineSection bean1 = new DefineSection();

				bean1.setPhaseCode(checkNull(String.valueOf(phaseData[i][0])));
				bean1.setPhaseName(checkNull(String.valueOf(phaseData[i][1])));

				list.add(bean1);

			}
		} // end of if

		logger.info("phaseData.length" + phaseData.length);
		bean.setPhaseList(list);
		return phaseData;

	}

	public void addSectionToList(DefineSection bean, HttpServletRequest request) {
		try {
			String[] sectionCodeObject = null;
			String[] sectionNameObject = null;

			String[] sectionName = request.getParameterValues("secName");
			String[] sectionCode = request.getParameterValues("secCode");
			Object[] phase = request.getParameterValues("phaseCode");

			Object[][] templateDtlObj = null;

			if (sectionCode != null) // check whether list is empty or not
			{
				//list is not empty

				Object[][] visibility = new Object[sectionName.length][phase.length];
				Object[][] rating = new Object[sectionName.length][phase.length];
				Object[][] comment = new Object[sectionName.length][phase.length];

				Object[] temp = null;
				for (int i = 0; i < sectionName.length; i++) {
					for (int j = 0; j < phase.length; j++) {
						temp = request.getParameterValues("h_" + i + "_" + j
								+ "_V");
						// logger.info("temp1 " + String.valueOf(temp[0]));
						visibility[i][j] = temp[0];
						temp = request.getParameterValues("h_" + i + "_" + j
								+ "_R");
						// logger.info("temp2 " + String.valueOf(temp[0]));
						rating[i][j] = temp[0];
						temp = request.getParameterValues("h_" + i + "_" + j
								+ "_C");
						// logger.info("temp3 " + String.valueOf(temp[0]));
						comment[i][j] = temp[0];
					}

				}

				sectionCodeObject = new String[sectionCode.length + 1];
				sectionNameObject = new String[sectionCode.length + 1];
				templateDtlObj = new Object[(sectionCode.length * phase.length)
						+ phase.length][5];
				int m = 0;
				for (int i = 0; i < sectionCode.length; i++) {

					sectionCodeObject[i] = sectionCode[i];
					sectionNameObject[i] = sectionName[i];

					// logger.info("sadasda"+secCode[i]);

					for (int j = 0; j < phase.length; j++) {
						templateDtlObj[m][0] = sectionCode[i];
						templateDtlObj[m][1] = phase[j];
						templateDtlObj[m][2] = String.valueOf(visibility[i][j]);
						templateDtlObj[m][3] = String.valueOf(rating[i][j]);
						templateDtlObj[m][4] = String.valueOf(comment[i][j]);

						m++;
					}

				}

				sectionCodeObject[sectionCode.length] = "a"
						+ (Integer.parseInt("" + sectionCode.length) + 1);
				sectionNameObject[sectionCode.length] = bean.getSectionName();

				for (int j = 0; j < phase.length; j++) {
					templateDtlObj[m][0] = "a"
							+ (Integer.parseInt("" + sectionCode.length) + 1);
					templateDtlObj[m][1] = phase[j];
					templateDtlObj[m][2] = "N";
					templateDtlObj[m][3] = "N";
					templateDtlObj[m][4] = "N";

					m++;
				}

			} // end of if
			else {
				templateDtlObj = new Object[phase.length][5];

				sectionCodeObject = new String[1];
				sectionNameObject = new String[1];

				sectionCodeObject[0] = "1";
				sectionNameObject[0] = bean.getSectionName();
				int m = 0;
				for (int j = 0; j < phase.length; j++) {

					templateDtlObj[m][0] = "1";
					templateDtlObj[m][1] = phase[j];
					templateDtlObj[m][2] = "N";
					templateDtlObj[m][3] = "N";
					templateDtlObj[m][4] = "N";

					m++;
				}

			}

			request.setAttribute("sectioncode", sectionCodeObject);
			request.setAttribute("sectionname", sectionNameObject);
			request.setAttribute("sectionData", templateDtlObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void shuffleList(DefineSection bean, String type, String SrNo,
			String[] sectioName, String[] sectionCode, Object[] phaseCode,
			Object[][] visibility, Object[][] rating, Object[][] comment,
			HttpServletRequest request) {
		try {

			Object[][] tempVisibility = new Object[1][phaseCode.length];
			Object[][] tempRating = new Object[1][phaseCode.length];
			Object[][] tempCommet = new Object[1][phaseCode.length];

			if (type.equals("up")) {
				if (Integer.parseInt(SrNo) > 0) {

					String tempCode = sectionCode[Integer.parseInt(SrNo) - 1];
					String tempName = sectioName[Integer.parseInt(SrNo) - 1];

					for (int j = 0; j < phaseCode.length; j++) {

						tempVisibility[0][j] = String
								.valueOf(visibility[Integer.parseInt(SrNo) - 1][j]);
						tempRating[0][j] = String.valueOf(rating[Integer
								.parseInt(SrNo) - 1][j]);
						tempCommet[0][j] = String.valueOf(comment[Integer
								.parseInt(SrNo) - 1][j]);

					}

					for (int i = 0; i < sectioName.length; i++) {

						if (i == Integer.parseInt(SrNo) - 1) {
							sectioName[i] = sectioName[i + 1];
							sectionCode[i] = sectionCode[i + 1];
						} else if (i == Integer.parseInt(SrNo)) {
							sectioName[i] = tempName;
							sectionCode[i] = tempCode;
						} else {
							sectioName[i] = sectioName[i];
							sectionCode[i] = sectionCode[i];
						}

						for (int j = 0; j < phaseCode.length; j++) {

							if (i == Integer.parseInt(SrNo) - 1) {
								visibility[i][j] = visibility[i + 1][j];
								rating[i][j] = rating[i + 1][j];
								comment[i][j] = comment[i + 1][j];

							} else if (i == Integer.parseInt(SrNo)) {

								visibility[i][j] = tempVisibility[0][j];
								rating[i][j] = tempRating[0][j];
								comment[i][j] = tempCommet[0][j];

							} else {

								visibility[i][j] = visibility[i][j];
								rating[i][j] = rating[i][j];
								comment[i][j] = comment[i][j];

							}

						}

					}

				}
			}

			else if (type.equals("down")) {
				if (Integer.parseInt(SrNo) < sectioName.length + 1) {

					String tempCode = sectionCode[Integer.parseInt(SrNo)];
					String tempName = sectioName[Integer.parseInt(SrNo)];

					for (int j = 0; j < phaseCode.length; j++) {

						tempVisibility[0][j] = String
								.valueOf(visibility[Integer.parseInt(SrNo)][j]);
						tempRating[0][j] = String.valueOf(rating[Integer
								.parseInt(SrNo)][j]);
						tempCommet[0][j] = String.valueOf(comment[Integer
								.parseInt(SrNo)][j]);

					}

					for (int i = 0; i < sectioName.length; i++) {

						if (i == Integer.parseInt(SrNo)) {
							sectioName[i] = sectioName[i + 1];
							sectionCode[i] = sectionCode[i + 1];
						} else if (i == Integer.parseInt(SrNo) + 1) {
							sectioName[i] = tempName;
							sectionCode[i] = tempCode;
						} else {
							sectioName[i] = sectioName[i];
							sectionCode[i] = sectionCode[i];
						}

						for (int j = 0; j < phaseCode.length; j++) {

							if (i == Integer.parseInt(SrNo)) {
								visibility[i][j] = visibility[i + 1][j];
								rating[i][j] = rating[i + 1][j];
								comment[i][j] = comment[i + 1][j];

							} else if (i == Integer.parseInt(SrNo) + 1) {

								visibility[i][j] = tempVisibility[0][j];
								rating[i][j] = tempRating[0][j];
								comment[i][j] = tempCommet[0][j];

							} else {
								visibility[i][j] = visibility[i][j];
								rating[i][j] = rating[i][j];
								comment[i][j] = comment[i][j];

							}

						}

					}
				}

			}

			Object[][] templateDtlObj = new Object[(sectionCode.length * phaseCode.length)
					+ phaseCode.length][5];
			int m = 0;
			for (int i = 0; i < sectioName.length; i++) {

				for (int j = 0; j < phaseCode.length; j++) {

					templateDtlObj[m][0] = sectionCode[i];
					templateDtlObj[m][1] = phaseCode[j];
					templateDtlObj[m][2] = String.valueOf(visibility[i][j]);
					templateDtlObj[m][3] = String.valueOf(rating[i][j]);
					templateDtlObj[m][4] = String.valueOf(comment[i][j]);

					m++;

				}
			}

			request.setAttribute("sectioncode", sectionCode);
			request.setAttribute("sectionname", sectioName);
			request.setAttribute("sectionData", templateDtlObj);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public boolean saveSection(DefineSection bean, Object[] sectionName,
			Object[] phase, Object[][] visibility, Object[][] rating,
			Object[][] comment, String[] priority) {
		try {
			Object[][] templateHdrObj = new Object[1][4];
			Object[][] templateDtlObj = new Object[1][7];

			boolean result1 = false;
			boolean result = false;
			int a = 1;
			for (int i = 0; i < sectionName.length; i++) {

				templateHdrObj[0][0] = bean.getApprId();
				templateHdrObj[0][1] = bean.getTemplateCode();
				templateHdrObj[0][2] = sectionName[i];
				templateHdrObj[0][3] = priority[i];

				a++;
				result = getSqlModel().singleExecute(getQuery(2),
						templateHdrObj);

				if (result) {

					Object[][] code = getSqlModel()
							.getSingleResult(getQuery(4));

					for (int j = 0; j < phase.length; j++) {

						templateDtlObj[0][0] = bean.getApprId();
						templateDtlObj[0][1] = bean.getTemplateCode();
						templateDtlObj[0][2] = code[0][0];
						templateDtlObj[0][3] = phase[j];
						templateDtlObj[0][4] = String.valueOf(visibility[i][j]);
						templateDtlObj[0][5] = String.valueOf(rating[i][j]);
						templateDtlObj[0][6] = String.valueOf(comment[i][j]);

						result1 = getSqlModel().singleExecute(getQuery(5),
								templateDtlObj);

					}
				}

			}

			return result1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void getSectionDetails(DefineSection bean, HttpServletRequest request) {
		Object[][] totalObj = null;
		Object[] parmaObj = new Object[2];
		parmaObj[0] = bean.getApprId();
		parmaObj[1] = bean.getTemplateCode();

		Object[] phaseObj = new Object[3];
		phaseObj[0] = bean.getApprId();
		phaseObj[1] = bean.getApprId();
		phaseObj[2] = bean.getTemplateCode();

		Object headerObj[][] = getSqlModel().getSingleResult(getQuery(7),
				parmaObj);

		/*Object [][]extraPhase = getSqlModel().getSingleResult(getQuery(10),
		phaseObj);*/
		if(headerObj!= null && headerObj.length >0){
		String[] sectionCode = new String[headerObj.length];
		String[] sectionName = new String[headerObj.length];
		for (int i = 0; i < headerObj.length; i++) {
			sectionCode[i] = "" + headerObj[i][0];
			sectionName[i] = "" + headerObj[i][1];

		 }


		logger.info("apprid" + String.valueOf(bean.getApprId()));
		logger.info("tempCode" + String.valueOf(bean.getTemplateCode()));

		Object[][] detailObj = getSqlModel().getSingleResult(getQuery(6),
				parmaObj);

		if (detailObj != null && detailObj.length != 0)
			bean.setAddFLag("false");

		logger.info("detailObj " + detailObj.length);

		/*if (extraPhase != null && extraPhase.length != 0){
		
		logger.info("extra phase added ");
		
		 totalObj = new Object[detailObj.length +(extraPhase.length * headerObj.length)][5];
		
		int dbPhases = detailObj.length / headerObj.length;
			
		int a=0;
		int b=0;
		for (int i = 0; i < headerObj.length; i++) {
				 
		 for(int j=0;j<dbPhases;j++){
		 
				 totalObj[a][0] = detailObj[b][0];
				 totalObj[a][1] = detailObj[b][1];
				 totalObj[a][2] = detailObj[b][2];
				 totalObj[a][3] = detailObj[b][3];
				 totalObj[a][4] = detailObj[b][4];
				 a++;	
				 b++;
		 }
		
			 for(int k=0;k<extraPhase.length;k++){
						
						 totalObj[a][0] =  detailObj[i][0];
						 totalObj[a][1] = extraPhase[k][0];
						 totalObj[a][2] = "N";
						 totalObj[a][3] = "N";
						 totalObj[a][4] = "N";
						 a++;
						
			 }
			
		}
		
		 for(int m=0;m<totalObj.length;m++){
			 
			 logger.info("totalObj m="+m+" 0"+totalObj[m][0]);
			 logger.info("totalObj m="+m+" 1"+totalObj[m][1]);
			 logger.info("totalObj m="+m+" 2"+totalObj[m][2]);
			 logger.info("totalObj m="+m+" 3"+totalObj[m][3]);
			 logger.info("totalObj m="+m+" 4"+totalObj[m][4]);
		 }
					
		}
		 */

		request.setAttribute("sectioncode", sectionCode);
		request.setAttribute("sectionname", sectionName);
		request.setAttribute("sectionData", detailObj);
		}
		/*if (extraPhase != null && extraPhase.length != 0)
			 request.setAttribute("sectionData", totalObj);
		 else
			 request.setAttribute("sectionData", detailObj);*/

	}

	public boolean updateSection(DefineSection bean, String[] sectionCode,
			Object[] sectionName, Object[] phase, Object[][] visibility,
			Object[][] rating, Object[][] comment, String[] priority) {

		try {

			boolean uHdrResult = false;
			boolean uDtlResult = false;
			boolean iHdrResult = false;
			boolean iDtlResult = false;
			boolean flag = false;

			Object[] parmaObj = new Object[2];
			parmaObj[0] = bean.getApprId();
			parmaObj[1] = bean.getTemplateCode();

			Object[][] dbSection = getSqlModel().getSingleResult(getQuery(7),
					parmaObj);

			Object[][] maxSecCode = getSqlModel().getSingleResult(getQuery(9));

			int maxCode = Integer.parseInt(String.valueOf(maxSecCode[0][0]));

			Object[][] updateDtlObj = new Object[dbSection.length
					* phase.length][7];
			Object[][] insertDtlObj = new Object[(sectionCode.length - dbSection.length)
					* phase.length][7];
			

			Object[][] updateHdrObj = new Object[dbSection.length][5];
			Object[][] insertHdrObj = new Object[sectionCode.length
					- dbSection.length][5];

			int l = 0;
			int m = 0, a = 0;

			for (int i = 0; i < sectionCode.length; i++) {

				abc: for (int k = 0; k < dbSection.length; k++) {

					if (String.valueOf(dbSection[k][0]).equals(
							String.valueOf(sectionCode[i]))) {
						flag = true;

						updateHdrObj[k][0] = sectionName[i];
						updateHdrObj[k][1] = priority[i];
						updateHdrObj[k][2] = sectionCode[i];
						updateHdrObj[k][3] = bean.getTemplateCode();
						updateHdrObj[k][4] = bean.getApprId();

						for (int j = 0; j < phase.length; j++) {
							logger.info("pahsesssss" + j);
							updateDtlObj[l][0] = String
									.valueOf(visibility[i][j]);
							updateDtlObj[l][1] = String.valueOf(rating[i][j]);
							updateDtlObj[l][2] = String.valueOf(comment[i][j]);
							updateDtlObj[l][3] = phase[j];
							updateDtlObj[l][4] = sectionCode[i];
							updateDtlObj[l][5] = bean.getTemplateCode();
							updateDtlObj[l][6] = bean.getApprId();

							l++;
						}

						break abc;
					} else {
						flag = false;
					}
				}

				if (flag == false) {

					int temp = maxCode + 1;
					int ord = i + 1;

					insertHdrObj[a][0] = bean.getApprId();
					insertHdrObj[a][1] = bean.getTemplateCode();
					insertHdrObj[a][2] = "" + temp;
					insertHdrObj[a][3] = sectionName[i];
					insertHdrObj[a][4] = priority[i];

					for (int j = 0; j < phase.length; j++) {
						insertDtlObj[m][0] = bean.getApprId();
						insertDtlObj[m][1] = bean.getTemplateCode();
						insertDtlObj[m][2] = "" + temp;
						insertDtlObj[m][3] = phase[j];
						insertDtlObj[m][4] = String.valueOf(visibility[i][j]);
						insertDtlObj[m][5] = String.valueOf(rating[i][j]);
						insertDtlObj[m][6] = String.valueOf(comment[i][j]);
						m++;
					}
					maxCode++;

					a++;
					ord = i;
				}
			}

			logger.info("in updateaaaaaaaaaaaa updateDtlObj length "
					+ updateDtlObj.length);
			logger.info("in updateaaaaaaaaaaaa insertDtlObj length "
					+ insertDtlObj.length);

			logger.info("in updateaaaaaaaaaaaa insertHdrObj length "
					+ insertHdrObj.length);
			logger.info("in updateaaaaaaaaaaaa updateHdrObj length "
					+ updateHdrObj.length);

			uDtlResult = getSqlModel().singleExecute(getQuery(8), updateDtlObj);

			uHdrResult = getSqlModel().singleExecute(getQuery(3), updateHdrObj);
			if (insertHdrObj != null && insertHdrObj.length != 0)
				iHdrResult = getSqlModel().singleExecute(getQuery(11),
						insertHdrObj);
			if (insertDtlObj != null && insertDtlObj.length != 0)
				iDtlResult = getSqlModel().singleExecute(getQuery(5),
						insertDtlObj);

			logger.info("updateDtlObj----------------" + uDtlResult);
			logger.info("updateHdrObj----------------" + uHdrResult);
			logger.info("insertDtlObj----------------" + iDtlResult);
			logger.info("insertHdrObj----------------" + iHdrResult);

			return uDtlResult;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateNew (DefineSection bean, String[] sectionCode,
			Object[] sectionName, Object[] phase, Object[][] visibility,
			Object[][] rating, Object[][] comment, String[] priority)
	{
		boolean result = false;
		
		getSqlModel().singleExecute(getQuery(13), new Object[][]{{bean.getApprId(),bean.getTemplateCode()}});
		
		getSqlModel().singleExecute(getQuery(14), new Object[][]{{bean.getApprId(),bean.getTemplateCode()}});
		
		result = saveSection(bean, sectionName, phase, visibility, rating, comment, priority);
				
		return result;
		
	}
	public void remove(DefineSection bean, String SrNo, String[] sectioName,
			String[] sectionCode, Object[] phaseCode, Object[][] visibility,
			Object[][] rating, Object[][] comment, HttpServletRequest request) {
		try {		
			
			String [] sectioName_temp = new String [sectioName.length - 1 ];
			String [] sectioCode_temp =  new String [sectionCode.length - 1];
			Object [][] visibility_temp = new Object[sectioName.length - 1 ][phaseCode.length];
			Object [][] rating_temp = new Object[sectioName.length - 1 ][phaseCode.length];
			Object [][] comment_temp = new Object[sectioName.length - 1 ][phaseCode.length];
			int a=0;
			
			for (int i = 0; i < sectioName.length; i++) {
				
							
				if (i != Integer.parseInt(SrNo)) {

					
					sectioName_temp[a] = sectioName[i];
					sectioCode_temp[a] = sectionCode[i];
				} 
				
				for (int j = 0; j < phaseCode.length; j++) {

					if (i != Integer.parseInt(SrNo)) {
						
						
						visibility_temp[a][j] = visibility[i][j];
						rating_temp[a][j] = rating[i][j];
						comment_temp[a][j] = comment[i][j];
											
					}	
				
				}
				if (i != Integer.parseInt(SrNo)) 		
						a++;
				

			}
			
			Object[][] templateDtlObj = new Object[(sectioCode_temp.length * phaseCode.length)
					+ phaseCode.length][5];
			int m = 0;
			for (int i = 0; i < sectioCode_temp.length; i++) {
				for (int j = 0; j < phaseCode.length; j++) {

					templateDtlObj[m][0] = sectioCode_temp[i];
					templateDtlObj[m][1] = phaseCode[j];
					templateDtlObj[m][2] = String.valueOf(visibility_temp[i][j]);
					templateDtlObj[m][3] = String.valueOf(rating_temp[i][j]);
					templateDtlObj[m][4] = String.valueOf(comment_temp[i][j]);

					m++;

				}
			}
			
			request.setAttribute("sectioncode", sectioCode_temp);
			request.setAttribute("sectionname", sectioName_temp);
			request.setAttribute("sectionData", templateDtlObj);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	public void checkAppraisalStart (DefineSection bean){
		
		Object [][] resultData = getSqlModel().getSingleResult(getQuery(12), new Object[]{bean.getApprId(),bean.getTemplateCode()});
		
		if (resultData != null && resultData.length != 0){
			
			bean.setLockAppraisal("true");
			logger.info("Appraisal locked dont allow any changes in template");
		}
		
		logger.info("Appraisal not started so allow any changes in template");
	}
	/*
	 * method name : checkNull purpose : check the string is null or not return
	 * type : String parameter : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}
}
