/**
 * 
 */
package org.paradyne.model.FormulaBuilder;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.bean.FormulaBuilder.FormilaBuilder;
import org.paradyne.bean.admin.master.ProfTaxMaster;

import freemarker.log.Logger;

/**
 * @author ritac
 * 
 */
public class FormulaBuilderModel extends ModelBase {

	FormilaBuilder formilaBuilder = new FormilaBuilder();

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.lib.ModelBase.class);
	

	public void formulaDataList( FormilaBuilder bean, HttpServletRequest request) {
		
		String query = "SELECT FORMULA_ID,FORMULA_NAME FROM HRMS_FORMULABUILDER_HDR WHERE 1=1 ORDER BY FORMULA_ID";
		
		Object[][] objFormulaData = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),
				objFormulaData.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		logger.info(" pageIndex[0] ===" + pageIndex[0]);
		logger.info(" bean.getMyPage(1)[0] ===" + bean.getMyPage());

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		ArrayList<Object> list = new ArrayList<Object>();
		if (objFormulaData != null && objFormulaData.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				FormilaBuilder bean1 = new FormilaBuilder();
				bean1.setFormulaIdItt(String.valueOf(objFormulaData[i][0]));
				bean1.setFormulaNameItt(String.valueOf(objFormulaData[i][1]));
				list.add(bean1);
			}
		}
		bean.setIteratorlist(list);
		
	}
	
	public String addFormula(Object[] srNo, Object[] salC, Object[] salH,
			Object[] salT, Object[] salA, Object[] salP, int remove,
			FormilaBuilder formilaBuilder) {
		String prStr = "";

		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			System.out
					.println("Table List------------------------------------- "
							+ tableList.size());

			if (!formilaBuilder.getChkEdit().equals("")) {
				String res = editFormula(srNo, salC, salH, salT, salA, salP,
						formilaBuilder, 1);
				logger.info("res======" + res);
				if (res == "PriorityGreaterEdit") {
					prStr = res;
				}
				if (res == "noRemove") {
					prStr = res;
				}
				/*
				 * logger.info("Code Valueeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
				 * "+formilaBuilder.getChkEdit()); logger.info("Table
				 * List-------------------------------------
				 * "+tableList.size()); logger.info("After
				 * Parse======================================
				 * "+formilaBuilder.getSrNo());
				 * tableList.remove(Integer.parseInt(formilaBuilder.getChkEdit())-1);
				 */
			} else {
				if (srNo != null) // check whether list is empty or not
				{
					logger.info("srrrrrrrrrrr====" + salA[0]);

					for (int i = 0; i < srNo.length; i++) {
						FormilaBuilder frmBuild1 = new FormilaBuilder();
						frmBuild1.setSrNo(String.valueOf(i + 1));
						frmBuild1.setSalCode1(String.valueOf(salC[i]));
						frmBuild1.setSalHead1(String.valueOf(salH[i]));
						frmBuild1.setSalType1(String.valueOf(salT[i]));

						frmBuild1.setSalAmt1(String.valueOf(salA[i]));
						if (salT[i].equals("Fi")) {
							frmBuild1.setSalTy1("Fixed");
						}
						if (salT[i].equals("Fr")) {
							frmBuild1.setSalTy1("Formula");
						}
						if (salT[i].equals("Df")) {
							frmBuild1.setSalTy1("Difference");
						}
						frmBuild1.setSalPrior1(String.valueOf(salP[i]));
						// frmBuild1.setPriorCode(String.valueOf(salPr[i]));

						tableList.add(frmBuild1);

						// frmBuild1.setSalDetail("1");
					}

				}

				formilaBuilder.setSalCode1(formilaBuilder.getSalCode());
				logger.info("sallllllll===" + formilaBuilder.getSalCode());
				formilaBuilder.setSalHead1(formilaBuilder.getSalHead());
				logger.info("sallllllll===" + formilaBuilder.getSalHead());
				formilaBuilder.setSalType1(formilaBuilder.getSalType());
				Object[][] result = null;

				String form = formilaBuilder.getSalAmt();
				logger.info("formValue" + form);
				String[] formCodes = form.split("#");
				String strValue = "";
				int noCredits = 0;
				if (formilaBuilder.getSalType().equals("Fr")) {
					for (int i = 0; i < formCodes.length; i++) {
						if (formCodes[i].contains("C")) {
							if (strValue == "") {
								String credCode = formCodes[i].substring(1,
										formCodes[i].length());
								try {
									int isNo = Integer.parseInt(credCode);
									strValue = credCode;
									noCredits = noCredits + 1;

								} catch (Exception e) {

								}

							} else {
								String credCode = formCodes[i].substring(1,
										formCodes[i].length());

								try {
									int isNo = Integer.parseInt(credCode);
									noCredits = noCredits + 1;
									strValue = strValue + "," + credCode;

								} catch (Exception e) {

								}
							}

						}

					}
					logger.info("strValu------------------------e" + strValue);
					String prQue = "select CREDIT_PRIORITY from hrms_credit_head where CREDIT_PRIORITY >( "
							+ " select max(CREDIT_PRIORITY) from hrms_credit_head where CREDIT_code in("
							+ strValue
							+ ") "

							+ " )  and credit_code= "
							+ formilaBuilder.getSalCode();
					if (noCredits > 0) {
						result = getSqlModel().getSingleResult(prQue);
					} else
						result = new Object[1][1];
				}

				formilaBuilder.setSalAmt1(formilaBuilder.getSalAmt());

				if (formilaBuilder.getSalType().equals("Fi")) {
					formilaBuilder.setSalTy1("Fixed");
				}

				if (formilaBuilder.getSalType().equals("Df")) {
					formilaBuilder.setSalTy1("Difference");
				}

				formilaBuilder.setSalPrior1(formilaBuilder.getSalprior());
				// formilaBuilder.setPriorCode(formilaBuilder.getLengthp());
				formilaBuilder.setSrNo(String.valueOf(tableList.size() + 1));

				if (formilaBuilder.getSalType().equals("Fr")) {
					formilaBuilder.setSalTy1("Formula");
					if (result != null && result.length > 0) {
						tableList.add(formilaBuilder);
					} else {
						prStr = "PriorityGreater";
					}
				} else {
					tableList.add(formilaBuilder);
				}

				formilaBuilder.setFrmList(tableList);
				// formilaBuilder.setSalDetail("1");
			}

			// logger.info("getSrNo()================="+(Integer.parseInt(formilaBuilder.getChkEdit())-1));

			System.out
					.println("Table List------------------------------------- "
							+ tableList.size());

		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		return prStr;

	}

	public String editFormula(Object[] srNo, Object[] salC, Object[] salH,
			Object[] salT, Object[] salA, Object[] salP,
			FormilaBuilder formilaBuilder, int remove) {
		Object[][] result = null;
		String prStr = "";

		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) // check whether list is empty or not
			{
				logger.info("srrrrrrrrrrr====" + salA[0]);

				for (int i = 0; i < srNo.length; i++) {

					if (i == Integer.parseInt(formilaBuilder.getChkEdit()) - 1) {

						FormilaBuilder frmBuild_edit = new FormilaBuilder();

						String form = formilaBuilder.getSalAmt();
						logger.info("formValue" + form);
						String[] formCodes = form.split("#");
						String strValue = "";
						int noCredits = 0;
						if (formilaBuilder.getSalType().equals("Fr")) {
							for (int j = 0; j < formCodes.length; j++) {
								if (formCodes[j].contains("C")) {
									if (strValue == "") {
										String credCode = formCodes[j]
												.substring(1, formCodes[j]
														.length());
										try {
											int isNo = Integer
													.parseInt(credCode);
											strValue = credCode;
											noCredits = noCredits + 1;

										} catch (Exception e) {

										}

									} else {
										String credCode = formCodes[j]
												.substring(1, formCodes[j]
														.length());

										try {
											int isNo = Integer
													.parseInt(credCode);
											noCredits = noCredits + 1;
											strValue = strValue + ","
													+ credCode;

										} catch (Exception e) {

										}
									}

								}

							}
							logger.info("strValu------------------------e"
									+ strValue);
							String prQue = "SELECT CREDIT_PRIORITY FROM HRMS_CREDIT_HEAD WHERE CREDIT_PRIORITY >( "
									+ " SELECT MAX(CREDIT_PRIORITY) FROM HRMS_CREDIT_HEAD WHERE CREDIT_CODE IN("
									+ strValue
									+ ") "

									+ " )  AND CREDIT_CODE= "
									+ formilaBuilder.getSalCode();
							if (noCredits > 0) {
								result = getSqlModel().getSingleResult(prQue);
							} else
								result = new Object[1][1];
							if (result != null && result.length > 0) {

								frmBuild_edit.setSalCode1(formilaBuilder
										.getSalCode());
								logger.info("sallllllll==="
										+ formilaBuilder.getSalCode());
								frmBuild_edit.setSalHead1(formilaBuilder
										.getSalHead());
								logger.info("sallllllll==="
										+ formilaBuilder.getSalHead());
								frmBuild_edit.setSalType1(formilaBuilder
										.getSalType());

								if (formilaBuilder.getSalType().equals("Fi")) {
									frmBuild_edit.setSalTy1("Fixed");
								}
								if (formilaBuilder.getSalType().equals("Fr")) {
									frmBuild_edit.setSalTy1("Formula");
								}
								if (formilaBuilder.getSalType().equals("Df")) {
									frmBuild_edit.setSalTy1("Difference");
								}
								frmBuild_edit.setSalAmt1(formilaBuilder
										.getSalAmt());
								frmBuild_edit.setSrNo(String.valueOf(i + 1));
							} else {
								// //////////////////////
								frmBuild_edit.setSrNo(String.valueOf(i + 1));
								frmBuild_edit.setSalCode1(String
										.valueOf(salC[i]));
								frmBuild_edit.setSalHead1(String
										.valueOf(salH[i]));
								frmBuild_edit.setSalType1(String
										.valueOf(salT[i]));
								if (salT[i].equals("Fi")) {
									frmBuild_edit.setSalTy1("Fixed");
								}
								if (salT[i].equals("Fr")) {
									frmBuild_edit.setSalTy1("Formula");
								}
								if (salT[i].equals("Df")) {
									frmBuild_edit.setSalTy1("Difference");
								}
								frmBuild_edit.setSalAmt1(String
										.valueOf(salA[i]));
								frmBuild_edit.setSalPrior1(String
										.valueOf(salP[i]));
								prStr = "PriorityGreaterEdit";
								// ///////////////////////

							}

						} else {
							frmBuild_edit.setSalCode1(formilaBuilder
									.getSalCode());
							logger.info("sallllllll==="
									+ formilaBuilder.getSalCode());
							frmBuild_edit.setSalHead1(formilaBuilder
									.getSalHead());
							logger.info("sallllllll==="
									+ formilaBuilder.getSalHead());
							frmBuild_edit.setSalType1(formilaBuilder
									.getSalType());

							if (formilaBuilder.getSalType().equals("Fi")) {
								frmBuild_edit.setSalTy1("Fixed");
							}
							if (formilaBuilder.getSalType().equals("Fr")) {
								frmBuild_edit.setSalTy1("Formula");
							}
							if (formilaBuilder.getSalType().equals("Df")) {
								frmBuild_edit.setSalTy1("Difference");
							}
							frmBuild_edit
									.setSalAmt1(formilaBuilder.getSalAmt());
							frmBuild_edit.setSrNo(String.valueOf(i + 1));

						}
						tableList.add(frmBuild_edit);

						/*
						 * frmBuild1.setSalCode1(formilaBuilder.getSalCode());
						 * logger.info("sallllllll===" +
						 * formilaBuilder.getSalCode());
						 * frmBuild1.setSalHead1(formilaBuilder.getSalHead());
						 * logger.info("sallllllll===" +
						 * formilaBuilder.getSalHead());
						 * frmBuild1.setSalType1(formilaBuilder.getSalType());
						 * 
						 * 
						 * if (formilaBuilder.getSalType().equals("Fi")) {
						 * frmBuild1.setSalTy1("Fixed"); } if
						 * (formilaBuilder.getSalType().equals("Fr")) {
						 * frmBuild1.setSalTy1("Formula"); } if
						 * (formilaBuilder.getSalType().equals("Df")) {
						 * frmBuild1.setSalTy1("Difference"); }
						 * frmBuild1.setSalAmt1(formilaBuilder.getSalAmt());
						 * frmBuild1.setSrNo(String.valueOf(tableList.size() +
						 * 1));
						 */

					} else {
						FormilaBuilder frmBuild1 = new FormilaBuilder();
						frmBuild1.setSrNo(String.valueOf(i + 1));
						frmBuild1.setSalCode1(String.valueOf(salC[i]));
						frmBuild1.setSalHead1(String.valueOf(salH[i]));
						frmBuild1.setSalType1(String.valueOf(salT[i]));
						if (salT[i].equals("Fi")) {
							frmBuild1.setSalTy1("Fixed");
						}
						if (salT[i].equals("Fr")) {
							frmBuild1.setSalTy1("Formula");
						}
						if (salT[i].equals("Df")) {
							frmBuild1.setSalTy1("Difference");
						}
						frmBuild1.setSalAmt1(String.valueOf(salA[i]));
						frmBuild1.setSalPrior1(String.valueOf(salP[i]));
						tableList.add(frmBuild1);

					}

				}

				/*
				 * if (remove == 0) {
				 * logger.info("formilaBuilder.getChkEdit()==="+formilaBuilder.getChkEdit());
				 * if (!formilaBuilder.getChkEdit().equals("")) {
				 * 
				 *//**
					 * check for is this used in other's formula...??????
					 * 
					 * 
					 */
				/*
				 * String strValue=""; for (int i = 0; i < srNo.length - 1; i--) {
				 * String form=String.valueOf(salA[i]);
				 * logger.info("formValue"+form);
				 * 
				 * 
				 * int noCredits=0; String fixedVal=""; if
				 * (salT[i].equals("Fr")) { String[] formCodes=form.split("#");
				 * for (int j = 0; j < formCodes.length;j++) {
				 * 
				 * if(formCodes[j].contains("C")){ if(strValue==""){ String
				 * credCode=formCodes[j].substring(1,formCodes[j].length());
				 * try{ int isNo=Integer.parseInt(credCode); strValue =credCode;
				 * noCredits=noCredits+1;
				 * 
				 * }catch(Exception e){
				 *  }
				 * 
				 *  } else{ String
				 * credCode=formCodes[j].substring(1,formCodes[j].length());
				 * 
				 * try{ int isNo=Integer.parseInt(credCode);
				 * noCredits=noCredits+1; strValue = strValue +","+credCode;
				 * 
				 * }catch(Exception e){
				 *  } }
				 *  }
				 * 
				 * 
				 * 
				 * 
				 * if(strValue!="") {
				 * 
				 * if(strValue==salC[i]){ prStr="noRemove";
				 * logger.info("================="+prStr); }
				 *  }
				 *  }
				 *  } else{ logger .info("removeeeeee " +
				 * (Integer.parseInt(formilaBuilder .getChkEdit()) - 1));
				 * tableList.remove(Integer.parseInt(formilaBuilder
				 * .getChkEdit()) - 1); } if(salT[i].equals("Fi")) {
				 * logger.info("salC===="+salC[i]);
				 * 
				 * logger.info("strValue===="+strValue); if(strValue!="") {
				 * 
				 * if(strValue==salC[i]){ prStr="noRemove";
				 * logger.info("================="); } else{ logger
				 * .info("removeeeeee " + (Integer.parseInt(formilaBuilder
				 * .getChkEdit()) - 1));
				 * tableList.remove(Integer.parseInt(formilaBuilder
				 * .getChkEdit()) - 1); } } }
				 *  } }
				 *  }
				 */
				formilaBuilder.setFrmList(tableList);
				// formilaBuilder.setSalDetail("1");

			}
		} catch (Exception e) {
			// TODO: handle exception
			prStr = "";
		}
		return prStr;
	}

	public boolean removeList(Object[] srNo, Object[] salC, Object[] salH,
			Object[] salT, Object[] salA, Object[] salP,
			FormilaBuilder formilaBuilder, int remove, String serial)

	{
		logger.info("ssertt===" + serial);
		int serNo = (Integer.parseInt(serial) - 1);
		logger.info("serNo=========" + serNo);
		ArrayList<Object> tableList = new ArrayList<Object>();
		for (int i = 0; i < srNo.length; i++) {
			FormilaBuilder frmBuild1 = new FormilaBuilder();
			frmBuild1.setSrNo(String.valueOf(i + 1));
			frmBuild1.setSalCode1(String.valueOf(salC[i]));
			frmBuild1.setSalHead1(String.valueOf(salH[i]));
			frmBuild1.setSalType1(String.valueOf(salT[i]));
			if (salT[i].equals("Fi")) {
				frmBuild1.setSalTy1("Fixed");
			}
			if (salT[i].equals("Fr")) {
				frmBuild1.setSalTy1("Formula");
			}
			if (salT[i].equals("Df")) {
				frmBuild1.setSalTy1("Difference");
			}
			frmBuild1.setSalAmt1(String.valueOf(salA[i]));
			frmBuild1.setSalPrior1(String.valueOf(salP[i]));
			tableList.add(frmBuild1);
		}
		formilaBuilder.setFrmList(tableList);
		boolean flag = true;

		for (int k = serNo; k < srNo.length; k++) {
			int salcode = serNo;
			logger.info("formula    " + String.valueOf(salA[k]) + " ==  " + "C"
					+ (String.valueOf(salC[salcode])));
			int search = String.valueOf(salA[k]).indexOf(
					"C" + (String.valueOf(salC[salcode])));
			if (search == -1)
				flag = true;
			else {
				logger.info("cannot Remove ");
				flag = false;
				break;
			}
		}

		logger.info("flag========= " + flag);
		if (flag) {
			if (tableList.size() > 0) {
				if (!formilaBuilder.getChkEdit().equals("")) {
					logger
							.info("removeeeeee  "
									+ (Integer.parseInt(formilaBuilder
											.getChkEdit()) - 1));
					tableList.remove(serNo);
				}
			}
		}
		return flag;
	}

	public String save(FormilaBuilder frmBuild, Object[] srNo, Object[] salC,
			Object[] salH, Object[] salT, Object[] salA, String formulaName) {
		// TODO Auto-generated method stub
		try {

			boolean result = false;
			logger.info("IN modelllll");
			Object[][] saveObj = new Object[1][2];

			String query = " SELECT NVL(MAX(FORMULA_ID),0)+1 FROM HRMS_FORMULABUILDER_HDR";
			Object resultCode[][] = getSqlModel().getSingleResult(query);
			saveObj[0][0] = String.valueOf(resultCode[0][0]);
			saveObj[0][1] = formulaName;

			result = getSqlModel().singleExecute(getQuery(1), saveObj);
			frmBuild.setFrmbuildCode(String.valueOf(resultCode[0][0]));

			if (result){
				saveDetails(frmBuild, srNo, salC, salT, salA);
			}
			if (result){
				return "saved";
			} else {
				return "notsaved";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}

	}

	private boolean saveDetails(FormilaBuilder frmBuild, Object[] srNo,
			Object[] salC, Object[] salT, Object[] salA) {
		// TODO Auto-generated method stub
		try {
			boolean result = false;
			Object[][] detailData = new Object[1][5];
			if (srNo != null) { // check whether data is present in table list
				for (int i = 0; i < srNo.length; i++) {

					detailData[0][0] = frmBuild.getFrmbuildCode();
					logger.info("detaillllllll===" + detailData[0][0]);

					detailData[0][1] = salC[i];
					logger.info("salCsalCsalC==" + detailData[0][1]);
					detailData[0][2] = salT[i];
					logger.info("salTsalTsalT==" + detailData[0][2]);
					detailData[0][3] = salA[i];
					logger.info("salAsalAsalA==" + detailData[0][3]);
					detailData[0][4] = srNo[i];

					result = getSqlModel().singleExecute(getQuery(2),
							detailData);
				}
			}
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public String modify(FormilaBuilder frmBuild, Object[] srNo, Object[] salC,
			Object[] salH, Object[] salT, Object[] salA) {
		// TODO Auto-generated method stub
		logger.info("inside modifying  ");
		boolean result = false;
		try {

			Object[][] modObj = new Object[1][2];

			modObj[0][0] = frmBuild.getFrmbuildName();
			modObj[0][1] = frmBuild.getFrmbuildCode();

			result = getSqlModel().singleExecute(getQuery(3), modObj);
			Object[][] code = new Object[1][1];
			code[0][0] = frmBuild.getFrmbuildCode();

			getSqlModel().singleExecute(getQuery(4), code);

			if (result) {
				saveDetails(frmBuild, srNo, salC, salT, salA);
				return "modified";
			} else {
				return "notmodified";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public void getRecord(FormilaBuilder frmBuild, String formulaCode) {
		// TODO Auto-generated method stub
		try {
			
			String que = " SELECT SAL_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME,SAL_TYPE,SAL_FORMULA,SAL_ORDER,FORMULA_DTLCODE,CREDIT_PRIORITY FROM HRMS_FORMULABUILDER_DTL "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_FORMULABUILDER_DTL.SAL_CODE) "
					+ " WHERE FORMULA_ID=" + formulaCode + " Order by SAL_CODE";
			Object[][] data = getSqlModel().getSingleResult(que);

			logger.info("In add ********2:" + data.length);
			ArrayList<Object> frmbuildList = new ArrayList<Object>();

			for (int i = 0; i < data.length; i++) {
				FormilaBuilder frmBuild1 = new FormilaBuilder();
				char[] ids = new char[String.valueOf(data[i][3]).length()];
				
				frmBuild1.setSrNo(String.valueOf(i + 1));
				if (String.valueOf(data[i][0]).equals("")
						|| String.valueOf(data[i][0]).equals("null"))
					frmBuild1.setSalCode1("");
				else
					frmBuild1.setSalCode1(String.valueOf(data[i][0]));
				if (String.valueOf(data[i][1]).equals("")
						|| String.valueOf(data[i][1]).equals("null"))
					frmBuild1.setSalHead1("");
				else
					frmBuild1.setSalHead1(String.valueOf(data[i][1]));

				if (String.valueOf(data[i][2]).equals("")
						|| String.valueOf(data[i][2]).equals("null"))
					frmBuild1.setSalType1("");
				else
					frmBuild1.setSalType1(String.valueOf(data[i][2]));

				if (data[i][2].equals("Fi")) {
					frmBuild1.setSalTy1("Fixed");
				}
				if (data[i][2].equals("Fr")) {
					frmBuild1.setSalTy1("Formula");
				}
				if (data[i][2].equals("Df")) {
					frmBuild1.setSalTy1("Difference");
				}

				if (String.valueOf(data[i][3]).equals("")
						|| String.valueOf(data[i][3]).equals("null"))
					frmBuild1.setSalAmt1("");
				else
					frmBuild1.setSalAmt1(String.valueOf(data[i][3]));

				if (String.valueOf(data[i][4]).equals("")
						|| String.valueOf(data[i][4]).equals("null"))
					frmBuild1.setSalOrder("");
				else
					frmBuild1.setSalOrder(String.valueOf(data[i][4]));

				frmBuild1.setFrmdtlCode(String.valueOf(data[i][5]));
				frmBuild1.setSalPrior1(String.valueOf(data[i][6]));
				frmbuildList.add(frmBuild1);

			}
			// bean.setTableLength("0");
			frmBuild.setFrmList(frmbuildList);
			// frmBuild.setSalDetail("1");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String saveValidate(FormilaBuilder frmBuild, String[] srNo,
			String[] salC, String[] salA) {
		logger.info("in saveValidate===========================");
		// TODO Auto-generated method stub
		try {

			ArrayList<Object> listAmt = new ArrayList<Object>();

			for (int i = 0; i < salC.length; i++) {
				logger.info("salC.lengthsalC.length----" + salC.length);
				for (int j = 0; j < salA.length; j++) {
					logger.info("salA.length salA.length----"
							+ salA[0].charAt(4));
					if (salA[j].charAt(j) == 'C') {
						logger.info("cahrrrrrr----"
								+ String.valueOf(salA[j].charAt(j + 1)));
						// String[]
						// listAmt=Character.toString(String.valueOf(salA[j].charAt(j+1)));
						listAmt.add(String.valueOf(salA[j].charAt(j + 1)));
						for (int k = 0; k < listAmt.size(); k++) {
							if (!listAmt.get(k).equals(salC[i])) {
								logger.info("not equal");
							}
						}

					}
				}

			}

			return "";

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public boolean delFormula(FormilaBuilder frmBuild) {
		// TODO Auto-generated method stub

		Object addObj[][] = new Object[1][1];
		boolean result;
		addObj[0][0] = frmBuild.getFrmbuildCode();

		logger.info("ptcode=========" + addObj[0][0]);
		result = getSqlModel().singleExecute(getQuery(6), addObj);
		if (result) {

			result = getSqlModel().singleExecute(getQuery(4), addObj);
		}
		return result;

	}

	public void callForEdit(FormilaBuilder frmBuild, String formulaAutoId) {
		
		try {
			String query = "SELECT FORMULA_ID,FORMULA_NAME FROM HRMS_FORMULABUILDER_HDR WHERE FORMULA_ID = "
			+ formulaAutoId 
			+ " ORDER BY FORMULA_ID ";
			Object[][] formulaData = getSqlModel().getSingleResult(query);//  to get the record and update the data in double click in the list
			
			frmBuild.setFrmId(String.valueOf(formulaData[0][0]));
			frmBuild.setFrmbuildName(String.valueOf(formulaData[0][1]));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteSelectedRecord(String[] deleteCode) {
		boolean result = false;
		try {
			
			String deleteQuery;
			// TODO Auto-generated method stub
			if (deleteCode != null && deleteCode.length > 0) {
				for (int i = 0; i < deleteCode.length; i++) {
					
					if (deleteCode[i] != null && deleteCode[i] != "") {
						deleteQuery = " DELETE FROM HRMS_FORMULABUILDER_DTL WHERE FORMULA_ID="
								+ deleteCode[i];
						result = getSqlModel().singleExecute(deleteQuery);
						if (result) {
							deleteQuery = " DELETE FROM HRMS_FORMULABUILDER_HDR WHERE FORMULA_ID="
									+ deleteCode[i];
							result = getSqlModel().singleExecute(deleteQuery);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
