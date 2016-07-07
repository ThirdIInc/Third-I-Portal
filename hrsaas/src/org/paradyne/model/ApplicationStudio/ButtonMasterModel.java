package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.ButtonMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

public class ButtonMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ButtonMasterModel.class);
	
	private String alertMessage = "";

	public void getButtonMaster(String fileName, ButtonMaster bean,
			HttpServletRequest request) {
		Object[][] btnMastList = null;
		try {
			String btnFileName = fileName + "\\NavigationPanel\\buttons.xml";
			System.out.println("btnFileName----------------------"
					+ btnFileName);
			File btnFile = new File(btnFileName);
			if (btnFile.exists()) {
				Document document = new XMLReaderWriter().parse(btnFile);

				List btnNodes = document.selectNodes("//BUTTON_MASTER/BUTTON");
				btnMastList = new Object[btnNodes.size()][5];
				int cnt = 0;
				for (Iterator<Element> it = btnNodes.iterator(); it.hasNext();) {
					Element btnEl = (Element) it.next();
					btnMastList[cnt][0] = String.valueOf(btnEl
							.attributeValue("btnCode"));
					btnMastList[cnt][1] = String.valueOf(btnEl
							.attributeValue("btnName"));
					btnMastList[cnt][2] = String.valueOf(btnEl
							.attributeValue("btnOrder"));
					btnMastList[cnt][3] = String.valueOf(btnEl
							.attributeValue("btnFlag"));
					btnMastList[cnt][4] = String.valueOf(btnEl
							.attributeValue("enableAll"));

					cnt++;
				}
			}
			ArrayList list = new ArrayList();

			if (btnMastList != null && btnMastList.length > 0) {
				for (int i = 0; i < btnMastList.length; i++) {

					ButtonMaster button = new ButtonMaster();
					button.setButtonCode(String.valueOf(btnMastList[i][0]));
					button.setButtonName(String.valueOf(btnMastList[i][1]));
					button.setButtonOrder(String.valueOf(btnMastList[i][2]));
					if (String.valueOf(btnMastList[i][3]).equals("D")) {
						btnMastList[i][3] = "Delete";
					} else if (String.valueOf(btnMastList[i][3]).equals("I")) {
						btnMastList[i][3] = "Insert";
					} else if (String.valueOf(btnMastList[i][3]).equals("U")) {
						btnMastList[i][3] = "Update";
					} else if (String.valueOf(btnMastList[i][3]).equals("V")) {
						btnMastList[i][3] = "View";
					}
					button.setButtonFlag(String.valueOf(btnMastList[i][3]));
					if (String.valueOf(btnMastList[i][4]).equals("Y")) {
						btnMastList[i][4] = "Yes";
					} else {
						btnMastList[i][4] = "No";
					}
					button.setEnabledisable(String.valueOf(btnMastList[i][4]));
					list.add(button);
				}
			}
			bean.setButtonList(list);
		} catch (Exception e) {
			logger.info("Exception in getButtonMaster-------------------" + e);
		}

	}

	public void save(ButtonMaster buttonMaster, String fileName,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
			try {
			String[] buttonCode = request.getParameterValues("buttonCode");
			String[] buttonName = request.getParameterValues("buttonName");
			String[] buttonFlag = request.getParameterValues("buttonFlag");
			String[] enabledisable = request
					.getParameterValues("enabledisable");
			String[] buttonOrder = request.getParameterValues("buttonOrder");
			Document document = null;
			document = buildDocument(buttonCode, buttonName, buttonOrder,
					buttonFlag, enabledisable);
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
				new XMLReaderWriter().write(document, fileName);
			} else {
				new XMLReaderWriter().write(document, fileName);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private Document buildDocument(String[] buttonCode, String[] buttonName,
			String[] buttonOrder, String[] buttonFlag, String[] enabledisable) {
		// TODO Auto-generated method stub
		Document document = null;
		try {

			Element root = null;
			document = DocumentHelper.createDocument();
			root = document.addElement("BUTTON_MASTER");
			if (buttonCode != null && buttonCode.length > 0) {

				for (int i = 0; i < buttonCode.length; i++) {

					if (String.valueOf(buttonFlag[i]).equals("Delete")) {
						buttonFlag[i] = "D";
					} else if (String.valueOf(buttonFlag[i]).equals("Insert")) {
						buttonFlag[i] = "I";
					} else if (String.valueOf(buttonFlag[i]).equals("Update")) {
						buttonFlag[i] = "U";
					} else if (String.valueOf(buttonFlag[i]).equals("View")) {
						buttonFlag[i] = "V";
					}

					if (String.valueOf(enabledisable[i]).equals("No")) {
						enabledisable[i] = "N";
					} else if (String.valueOf(enabledisable[i]).equals("Yes")) {
						enabledisable[i] = "Y";
					}
					root.addElement("BUTTON").addAttribute("btnCode",
							String.valueOf(buttonCode[i])).addAttribute(
							"btnName", String.valueOf(buttonName[i]))
							.addAttribute("btnOrder",
									String.valueOf(buttonOrder[i]))
							.addAttribute("btnFlag",
									String.valueOf(buttonFlag[i]))
							.addAttribute("enableAll",
									String.valueOf(enabledisable[i]));

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in buildDocument------------------------- "
					+ e);
		}
		return document;
	}

	public void addList(ButtonMaster buttonMaster, String[] buttonCode,
			String[] buttonName, String[] buttonOrder, String[] buttonFlag,
			String[] enabledisable, int check) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Object> tableList = new ArrayList<Object>();
		logger.info("in new add");
		if (buttonCode != null && buttonCode != null) {
			for (int i = 0; i < buttonCode.length; i++) {
				ButtonMaster bean = new ButtonMaster();
				logger.info("_________________" + buttonName[i]);
				// bean.setSrNo(String.valueOf(i + 1));
				bean.setButtonCode(buttonCode[i]);
				bean.setButtonName(buttonName[i]);
				bean.setButtonOrder(buttonOrder[i]);
				if (String.valueOf(buttonFlag[i]).equals("D")) {
					buttonFlag[i] = "Delete";
				} else if (String.valueOf(buttonFlag[i]).equals("I")) {
					buttonFlag[i] = "Insert";
				} else if (String.valueOf(buttonFlag[i]).equals("U")) {
					buttonFlag[i] = "Update";
				} else if (String.valueOf(buttonFlag[i]).equals("V")) {
					buttonFlag[i] = "View";
				}
				bean.setButtonFlag(buttonFlag[i]);
				if (String.valueOf(enabledisable[i]).equals("Y")) {
					enabledisable[i] = "Yes";
				} else {
					enabledisable[i] = "No";
				}

				bean.setEnabledisable(enabledisable[i]);
				tableList.add(bean);
			}
		}
		if (check == 1) {
			ButtonMaster bean = new ButtonMaster();
			bean.setButtonCode(buttonMaster.getBtnCode());
			bean.setButtonName(buttonMaster.getBtnName());
			bean.setButtonOrder(buttonMaster.getBtnOrder());
			if (buttonMaster.getBtnFlag().equals("I")) {
				buttonMaster.setBtnFlag("Insert");
			}
			if (buttonMaster.getBtnFlag().equals("D")) {
				buttonMaster.setBtnFlag("Delete");
			}
			if (buttonMaster.getBtnFlag().equals("U")) {
				buttonMaster.setBtnFlag("Update");
			}
			if (buttonMaster.getBtnFlag().equals("V")) {
				buttonMaster.setBtnFlag("View");
			}
			bean.setButtonFlag(buttonMaster.getBtnFlag());
			if (buttonMaster.getEnableField().equals("Y")) {
				buttonMaster.setEnableField("Yes");
			}
			if (buttonMaster.getEnableField().equals("N")) {
				buttonMaster.setEnableField("No");
			}
			bean.setEnabledisable(buttonMaster.getEnableField());
			tableList.add(bean);
		}
		buttonMaster.setButtonList(tableList);

	}

	public void modList(ButtonMaster buttonMaster, String[] buttonCode,
			String[] buttonName, String[] buttonOrder, String[] buttonFlag,
			String[] enabledisable, int check) {
		try {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			ArrayList<Object> tableList = new ArrayList<Object>();

			if (buttonCode != null && buttonCode.length > 0) {
				for (int i = 0; i < buttonCode.length; i++) {
					ButtonMaster bean = new ButtonMaster();
					if (i == Integer.parseInt(buttonMaster.getHiddenEdit()) - 1) {
						bean.setButtonCode(buttonMaster.getBtnCode());
						bean.setButtonName(buttonMaster.getBtnName());
						bean.setButtonOrder(buttonMaster.getBtnOrder());
						if (buttonMaster.getBtnFlag().equals("I")) {
							buttonMaster.setBtnFlag("Insert");
						}
						if (buttonMaster.getBtnFlag().equals("D")) {
							buttonMaster.setBtnFlag("Delete");
						}
						if (buttonMaster.getBtnFlag().equals("U")) {
							buttonMaster.setBtnFlag("Update");
						}
						if (buttonMaster.getBtnFlag().equals("V")) {
							buttonMaster.setBtnFlag("View");
						}
						bean.setButtonFlag(buttonMaster.getBtnFlag());
						if (buttonMaster.getEnableField().equals("Y")) {
							buttonMaster.setEnableField("Yes");
						}
						if (buttonMaster.getEnableField().equals("N")) {
							buttonMaster.setEnableField("No");
						}
						bean.setEnabledisable(buttonMaster.getEnableField());
					} else {
						bean.setButtonCode(buttonCode[i]);
						bean.setButtonName(buttonName[i]);
						bean.setButtonOrder(buttonOrder[i]);
						if (String.valueOf(buttonFlag[i]).equals("D")) {
							buttonFlag[i] = "Delete";
						} else if (String.valueOf(buttonFlag[i]).equals("I")) {
							buttonFlag[i] = "Insert";
						} else if (String.valueOf(buttonFlag[i]).equals("U")) {
							buttonFlag[i] = "Update";
						} else if (String.valueOf(buttonFlag[i]).equals("V")) {
							buttonFlag[i] = "View";
						}
						bean.setButtonFlag(buttonFlag[i]);
						if (String.valueOf(enabledisable[i]).equals("Y")) {
							enabledisable[i] = "Yes";
						} else {
							enabledisable[i] = "No";
						}
						bean.setEnabledisable(enabledisable[i]);
					}
					tableList.add(bean);
				}
			}
			buttonMaster.setButtonList(tableList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in modList-----------------------" + e);
		}

	}

	public boolean deleteData(ButtonMaster buttonMaster, String[] buttonCode,
			String[] buttonName, String[] buttonFlag, String[] enabledisable,
			String[] buttonOrder) {
		try {
			// TODO Auto-generated method stub
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (buttonCode != null && buttonCode.length > 0) {
				for (int i = 0; i < buttonCode.length; i++) {
					ButtonMaster bean = new ButtonMaster();
					bean.setButtonCode(buttonCode[i]);
					bean.setButtonOrder(buttonOrder[i]);
					bean.setEnabledisable(enabledisable[i]);
					bean.setButtonName(buttonName[i]);
					bean.setButtonFlag(buttonFlag[i]);
					tableList.add(bean);
				}
				tableList
						.remove(Integer.parseInt(buttonMaster.getHiddenEdit()) - 1);
			}
			buttonMaster.setButtonList(tableList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in deleteData----------------------" + e);
		}
		return true;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

}
