package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.IPFilter;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

public class IPFilterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(IPFilterModel.class);
	
	private String path;

	public String addBranchIpRanges(IPFilter ipFilter, String[] srNoBr, String[] branchfromIpAdd, String[] branchtoIpAdd, String[] brnCode, String[] brnName, HttpServletRequest request) {
		String message = "";
		
		try {
			HashMap mapdata = new HashMap();
			ArrayList<Object> list = new ArrayList<Object>();
			boolean isRangeAdded = true;
			
			if (srNoBr != null) {
				for (int i = 0; i < srNoBr.length; i++) {
					IPFilter bean = new IPFilter();
					bean.setBranchfromIpAdd(branchfromIpAdd[i]);
					bean.setBranchtoIpAdd(branchtoIpAdd[i]);
					bean.setBrnCode(brnCode[i]);
					bean.setBrnName(brnName[i]);
					list.add(bean);
					mapdata.put("" + i, "Q");
				}
			}
			
			request.setAttribute("dataBranch", mapdata);
			
			String[] newBrnFromIPAddRange = ipFilter.getBranchFrom().replace(".", "#").split("#");
			String[] newBrnToIPAddRange = ipFilter.getBranchTo().replace(".", "#").split("#");
			
			int newBrnFromIPAdd0 = Integer.parseInt(String.valueOf(newBrnFromIPAddRange[0]));
			int newBrnFromIPAdd1 = Integer.parseInt(String.valueOf(newBrnFromIPAddRange[1]));
			int newBrnFromIPAdd2 = Integer.parseInt(String.valueOf(newBrnFromIPAddRange[2]));
			int newBrnFromIPAdd3 = Integer.parseInt(String.valueOf(newBrnFromIPAddRange[3]));
			
			int newBrnToIPAdd0 = Integer.parseInt(String.valueOf(newBrnToIPAddRange[0]));
			int newBrnToIPAdd1 = Integer.parseInt(String.valueOf(newBrnToIPAddRange[1]));
			int newBrnToIPAdd2 = Integer.parseInt(String.valueOf(newBrnToIPAddRange[2]));
			int newBrnToIPAdd3 = Integer.parseInt(String.valueOf(newBrnToIPAddRange[3]));
			
			String newBrnFromIP = newBrnFromIPAdd0 + "." + newBrnFromIPAdd1 + "." + newBrnFromIPAdd2 + "." + newBrnFromIPAdd3;
			String newBrnToIP = newBrnToIPAdd0 + "." + newBrnToIPAdd1 + "." + newBrnToIPAdd2 + "." + newBrnToIPAdd3;
			
			IPFilter bean = new IPFilter();
			bean.setBranchfromIpAdd(newBrnFromIP);
			bean.setBranchtoIpAdd(newBrnToIP);
			bean.setBrnCode(ipFilter.getBranchCode());
			bean.setBrnName(ipFilter.getBranchName());
			
			System.out.println("newBrnFromIP-----------"+newBrnFromIP);
			
			System.out.println("vishu----------------------"+newBrnFromIP.replace(".", ""));
			
			long intNewBrnFromIP = Long.parseLong(newBrnFromIP.replace(".", ""));
			long intNewBrnToIP = Long.parseLong(newBrnToIP.replace(".", ""));
			
			if(srNoBr != null) {
				for (int i = 0; i < srNoBr.length; i++) {
					String[] oldBrnFromIPAddRange = String.valueOf(branchfromIpAdd[i]).replace(".", "#").split("#");
					String[] oldBrnToIPAddRange = String.valueOf(branchtoIpAdd[i]).replace(".", "#").split("#");
					
					long oldBrnFromIPAdd0 = Long.parseLong(String.valueOf(oldBrnFromIPAddRange[0]));
					long oldBrnFromIPAdd1 = Long.parseLong(String.valueOf(oldBrnFromIPAddRange[1]));
					long oldBrnFromIPAdd2 = Long.parseLong(String.valueOf(oldBrnFromIPAddRange[2]));
					long oldBrnFromIPAdd3 = Long.parseLong(String.valueOf(oldBrnFromIPAddRange[3]));
					
					long oldBrnToIPAdd0 = Long.parseLong(String.valueOf(oldBrnToIPAddRange[0]));
					long oldBrnToIPAdd1 = Long.parseLong(String.valueOf(oldBrnToIPAddRange[1]));
					long oldBrnToIPAdd2 = Long.parseLong(String.valueOf(oldBrnToIPAddRange[2]));
					long oldBrnToIPAdd3 = Long.parseLong(String.valueOf(oldBrnToIPAddRange[3]));
					
					String oldBrnFromIP = oldBrnFromIPAdd0 + "." + oldBrnFromIPAdd1 + "." + oldBrnFromIPAdd2 + "." + oldBrnFromIPAdd3;
					String oldBrnToIP = oldBrnToIPAdd0 + "." + oldBrnToIPAdd1 + "." + oldBrnToIPAdd2 + "." + oldBrnToIPAdd3;
					
					long intOldBrnFromIP = Long.parseLong(oldBrnFromIP.replace(".", ""));
					long intOldBrnToIP = Long.parseLong(oldBrnToIP.replace(".", ""));
					
					if((intNewBrnFromIP >= intOldBrnFromIP && intNewBrnFromIP <= intOldBrnToIP) || (intNewBrnToIP >= intOldBrnFromIP && intNewBrnToIP <= intOldBrnToIP)) {
						isRangeAdded = false;
						break;
					} else if(intNewBrnFromIP < intOldBrnFromIP && intNewBrnToIP > intOldBrnToIP) {
						isRangeAdded = false;
						break;
					}
				}
			}
			
			if(isRangeAdded) {
				list.add(bean);
			} else {
				message = "IP address range already available.";
			}
			
			ipFilter.setBranchList(list);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return message;
	}

	public void addEmployee(IPFilter ipFilter, String[] srNoEmp, String[] empId, String[] empName, String[] empToken, HttpServletRequest request) {
		ArrayList empList = null;
		
		try {
			empList = new ArrayList();
			if (srNoEmp != null) {
				for (int i = 0; i < srNoEmp.length; i++) {
					IPFilter bean = new IPFilter();
					bean.setEId(empId[i]);
					bean.setEToken(empToken[i]);
					bean.setEName(empName[i]);
					empList.add(bean);
				}
			}
			
			IPFilter bean = new IPFilter();
			bean.setEId(ipFilter.getEmpId());
			bean.setEToken(ipFilter.getEmpToken());
			bean.setEName(ipFilter.getEmpName());
			empList.add(bean);
			ipFilter.setEmpList(empList);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void addException(IPFilter ipFilter, String[] srNoEmp, String[] empId, String[] empName, String[] empToken, HttpServletRequest request) {
		try {
			HashMap mapdata = new HashMap();
			ArrayList<Object> list = new ArrayList<Object>();
			
			if (srNoEmp != null) {

				for (int i = 0; i < srNoEmp.length; i++) {
					IPFilter bean = new IPFilter();
					bean.setEId(empId[i]);
					bean.setEName(empName[i]);
					bean.setEToken(empToken[i]);
					list.add(bean);
					mapdata.put("" + i, "B");
				}
			}
			
			request.setAttribute("dataEmp", mapdata);
			
			IPFilter bean = new IPFilter();
			bean.setEId(ipFilter.getEmpId());
			bean.setEName(ipFilter.getEmpName());
			bean.setEToken(ipFilter.getEmpToken());
			list.add(bean);
			ipFilter.setEmpList(list);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String addIpRanges(IPFilter ipFilter, String[] srNo, String[] fromIpAdd, String[] toIpAdd, HttpServletRequest request) {
		String message = "";
		
		try {
			HashMap mapdata = new HashMap();
			ArrayList<Object> list = new ArrayList<Object>();
			boolean isRangeAdded = true;
			
			if(srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					IPFilter bean = new IPFilter();
					bean.setFromIpAdd(fromIpAdd[i]);
					bean.setToIpAdd(toIpAdd[i]);
					list.add(bean);
					mapdata.put("" + i, "G");
				}
			}
			
			request.setAttribute("data", mapdata);
						
			String[] newFromIPAddRange = ipFilter.getOrgFrom().replace(".", "#").split("#");
			String[] newToIPAddRange = ipFilter.getOrgTo().replace(".", "#").split("#");
			
			int newFromIPAdd0 = Integer.parseInt(String.valueOf(newFromIPAddRange[0]));
			int newFromIPAdd1 = Integer.parseInt(String.valueOf(newFromIPAddRange[1]));
			int newFromIPAdd2 = Integer.parseInt(String.valueOf(newFromIPAddRange[2]));
			int newFromIPAdd3 = Integer.parseInt(String.valueOf(newFromIPAddRange[3]));
			
			int newToIPAdd0 = Integer.parseInt(String.valueOf(newToIPAddRange[0]));
			int newToIPAdd1 = Integer.parseInt(String.valueOf(newToIPAddRange[1]));
			int newToIPAdd2 = Integer.parseInt(String.valueOf(newToIPAddRange[2]));
			int newToIPAdd3 = Integer.parseInt(String.valueOf(newToIPAddRange[3]));
			
			String newFromIP = newFromIPAdd0 + "." + newFromIPAdd1 + "." + newFromIPAdd2 + "." + newFromIPAdd3;
			String newToIP = newToIPAdd0 + "." + newToIPAdd1 + "." + newToIPAdd2 + "." + newToIPAdd3;
			
			IPFilter bean = new IPFilter();
			bean.setFromIpAdd(newFromIP);
			bean.setToIpAdd(newToIP);
			
			long intNewFromIP = Long.parseLong(newFromIP.replace(".", ""));
			long intNewToIP = Long.parseLong(newToIP.replace(".", ""));
			
			if(srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					String[] oldFromIPAddRange = String.valueOf(fromIpAdd[i]).replace(".", "#").split("#");
					String[] oldToIPAddRange = String.valueOf(toIpAdd[i]).replace(".", "#").split("#");
					
					int oldFromIPAdd0 = Integer.parseInt(String.valueOf(oldFromIPAddRange[0]));
					int oldFromIPAdd1 = Integer.parseInt(String.valueOf(oldFromIPAddRange[1]));
					int oldFromIPAdd2 = Integer.parseInt(String.valueOf(oldFromIPAddRange[2]));
					int oldFromIPAdd3 = Integer.parseInt(String.valueOf(oldFromIPAddRange[3]));
					
					int oldToIPAdd0 = Integer.parseInt(String.valueOf(oldToIPAddRange[0]));
					int oldToIPAdd1 = Integer.parseInt(String.valueOf(oldToIPAddRange[1]));
					int oldToIPAdd2 = Integer.parseInt(String.valueOf(oldToIPAddRange[2]));
					int oldToIPAdd3 = Integer.parseInt(String.valueOf(oldToIPAddRange[3]));
					
					String oldFromIP = oldFromIPAdd0 + "." + oldFromIPAdd1 + "." + oldFromIPAdd2 + "." + oldFromIPAdd3;
					String oldToIP = oldToIPAdd0 + "." + oldToIPAdd1 + "." + oldToIPAdd2 + "." + oldToIPAdd3;
					
					long intOldFromIP = Long.parseLong(oldFromIP.replace(".", ""));
					long intOldToIP = Long.parseLong(oldToIP.replace(".", ""));
					
					if((intNewFromIP >= intOldFromIP && intNewFromIP <= intOldToIP) || (intNewToIP >= intOldFromIP && intNewToIP <= intOldToIP)) {
						isRangeAdded = false;
						break;
					} else if(intNewFromIP < intOldFromIP && intNewToIP > intOldToIP) {
						isRangeAdded = false;
						break;
					}
				}
			}
			
			if(isRangeAdded) {
				list.add(bean);
			} else {
				message = "IP address range already available.";
			}
			
			ipFilter.setList(list);
		} catch (Exception e) {
			logger.error(e);
		}
		return message;
	}

	private Document buildDocument(IPFilter ipFilter, String[] empID) {
		Document document = null;
		
		try {

			Element root = null, ipApp = null, ipType = null, exception = null;
			String ipApplicable = ipFilter.getIpFilterFlag();
			
			if (ipApplicable.equals("true")) {
				ipApplicable = "Y";
			} else {
				ipApplicable = "N";
			}
			
			String brnType = ipFilter.getBranchWiseCheck();
			String orgType = ipFilter.getOrgWiseCheck();
			String type = "";
			
			if (brnType.equals("true")) {
				type = "B";
			}
			if (orgType.equals("true")) {
				type = "O";
			}
			
			String empId = "";
			if (empID != null && empID.length > 0) {
				for (int i = 0; i < empID.length; i++) {
					if (i < empID.length - 1) {
						empId += empID[i] + ",";
					} else {
						empId = empId + empID[i];
					}
				}
			}
			
			document = DocumentHelper.createDocument();
			root = document.addElement("IP_FILTER");
			ipApp = root.addElement("IP_APPLICABLE").addAttribute("applicable", ipApplicable);
			ipType = ipApp.addElement("TYPE").addAttribute("ipType", type);
			exception = ipApp.addElement("EXCEPTIONS").addAttribute("empIDs", empId);
		} catch (Exception e) {
			logger.info(e);
		}
		return document;
	}

	private Document buildDocumentForBrn(IPFilter ipFilter, String[] branchfromIpAdd, String[] branchtoIpAdd, String[] brnCode, String brnFile) {
		Document document = null;
		try {
			Element root = null, header = null, fromIp = null, toIp = null,header1=null;
			document = DocumentHelper.createDocument();
			root = document.addElement("BRANCH");
			
			for (int i = 0; i < brnCode.length; i++) {
				header = root.addElement("ID").addAttribute("brnCode", brnCode[i]);
				header1 = header.addElement("IP");
				fromIp = header1.addElement("FROM").addAttribute("fromIpAdd", branchfromIpAdd[i]);
				toIp = header1.addElement("TO").addAttribute("toIpAdd", branchtoIpAdd[i]);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return document;
	}

	private void buildDocumentForBrn1(IPFilter ipFilter, String[] branchfromIpAdd, String[] branchtoIpAdd, String[] brnCode, String brnFile) {
		Document document = null;
		Element root = null, header = null, fromIp = null, toIp = null;

		for (int i = 0; i < brnCode.length; i++) {
			this.path = brnFile;
			String str = String.valueOf(brnCode[i]);
			path += str + ".xml";

			/*
			 * if (f.exists()) { try { document = new
			 * XMLReaderWriter().parse(f); root = document.getRootElement(); }
			 * catch (Exception e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } }
			 */
			document = DocumentHelper.createDocument();
			root = document.addElement("BRANCH");
			for (int j = 0; j < brnCode.length; j++) {
				if (brnCode[i].equals(brnCode[j])) {
					header = root.addElement("IP");
					fromIp = header.addElement("FROM").addAttribute("fromIpAdd", branchfromIpAdd[j]);
					toIp = header.addElement("TO").addAttribute("toIpAdd", branchtoIpAdd[j]);
				}
			}
			try {
				new XMLReaderWriter().write(document, path);

			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	private Document buildDocumentForOrg(IPFilter ipFilter, String[] fromIpAdd, String[] toIpAdd) {
		Document document = null;
		try {

			Element root = null, header = null, fromIp = null, toIp = null;
			document = DocumentHelper.createDocument();
			root = document.addElement("ORGANISATION");

			if (fromIpAdd != null && fromIpAdd.length > 0) {
				for (int i = 0; i < fromIpAdd.length; i++) {
					header = root.addElement("IP");
					fromIp = header.addElement("FROM").addAttribute(
							"fromIpAdd", fromIpAdd[i]);
					toIp = header.addElement("TO").addAttribute("toIpAdd",
							toIpAdd[i]);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return document;
	}

	public void deleteBrnIp(IPFilter ipFilter, String[] srNo, String[] fromIpAdd, String[] toIpAdd, String[] brnCode, String[] brnName, String[] confChk, HttpServletRequest request) {
		try {
			HashMap mapdata = new HashMap();
			Object[][] quatData = new Object[fromIpAdd.length][4];
			ArrayList<Object> list = new ArrayList<Object>();
			
			if (srNo != null) {
				for (int i = 0; i < fromIpAdd.length; i++) {
					quatData[i][0] = fromIpAdd[i];
					quatData[i][1] = toIpAdd[i];
					quatData[i][2] = brnCode[i];
					quatData[i][3] = brnName[i];
				}
				
				for (int i = 0; i < fromIpAdd.length; i++) {
					for (int v = 0; v < 4; v++) {
						mapdata.put("" + i, quatData[i][v]);
					}
				}

				for (int j = 0; j < confChk.length; j++) {
					mapdata.remove(confChk[j]);
				}
				
				for (int i = 0; i < fromIpAdd.length; i++) {
					if (mapdata.containsKey("" + i)) {
						IPFilter bean = new IPFilter();
						bean.setBranchfromIpAdd(fromIpAdd[i]);
						bean.setBranchtoIpAdd(toIpAdd[i]);
						bean.setBrnCode(brnCode[i]);
						bean.setBrnName(brnName[i]);
						list.add(bean);
					}
				}
			}
			
			ipFilter.setBranchList(list);
			request.setAttribute("dataBranch", mapdata);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void deleteOrgIp(IPFilter ipFilter, String[] srNo, String[] fromIpAdd, String[] toIpAdd, String[] confChk, HttpServletRequest request) {
		try {
			HashMap mapdata = new HashMap();
			Object[][] quatData = new Object[fromIpAdd.length][2];
			ArrayList<Object> list = new ArrayList<Object>();
			
			if (srNo != null) {
				for (int i = 0; i < fromIpAdd.length; i++) {
					quatData[i][0] = fromIpAdd[i];
					quatData[i][1] = toIpAdd[i];
				}
				
				for (int i = 0; i < fromIpAdd.length; i++) {
					for (int v = 0; v < 2; v++) {
						mapdata.put("" + i, quatData[i][v]);
					}
				}

				for (int j = 0; j < confChk.length; j++) {
					mapdata.remove(confChk[j]);
				}
				
				for (int i = 0; i < fromIpAdd.length; i++) {
					if (mapdata.containsKey("" + i)) {
						IPFilter bean = new IPFilter();
						bean.setFromIpAdd(fromIpAdd[i]);
						bean.setToIpAdd(toIpAdd[i]);
						list.add(bean);
					}
				}
			}
			
			ipFilter.setList(list);
			request.setAttribute("dataEmp", mapdata);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void delException(IPFilter ipFilter, String[] srNoEmp, String[] empId, String[] empName, String[] empToken, String[] confChk, HttpServletRequest request) {
		try {
			HashMap mapdata = new HashMap();
			Object[][] empData = new Object[srNoEmp.length][3];
			ArrayList<Object> empList = new ArrayList<Object>();
			
			if (srNoEmp != null) {
				for (int i = 0; i < srNoEmp.length; i++) {
					empData[i][0] = empId[i];
					empData[i][1] = empName[i];
					empData[i][2] = empToken[i];
				}
				
				for (int i = 0; i < srNoEmp.length; i++) {
					for (int v = 0; v < 3; v++) {
						mapdata.put("" + i, empData[i][v]);
					}
				}

				for (int j = 0; j < confChk.length; j++) {
					mapdata.remove(confChk[j]);
				}
				
				for (int i = 0; i < srNoEmp.length; i++) {
					if (mapdata.containsKey("" + i)) {
						IPFilter bean = new IPFilter();
						bean.setEId(empId[i]);
						bean.setEName(empName[i]);
						bean.setEToken(empToken[i]);
						empList.add(bean);
					}
				}
			}
			ipFilter.setEmpList(empList);
			request.setAttribute("dataBranch", mapdata);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private Object[][] getDataFrmBrnXml(String fileName, IPFilter ipFilter, HttpServletRequest request) {
		String brnXmlFile =fileName + "\\xml\\IPFilter\\branch.xml ";
		File file = new File(brnXmlFile);
		Document document;
		Object[][] objData = null;
		
		try {
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//BRANCH");
			
			String brnNameQuery = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME)";		
			Object[][] brnNameQueryObj = getSqlModel().getSingleResult(brnNameQuery);
			
			int numOfBrn = 0;
			
			for (int i=0;i<brnNameQueryObj.length;i++) {
				List nodes1 = document.selectNodes("//BRANCH/ID[@brnCode=" + String.valueOf(brnNameQueryObj[i][0])+"]/IP");
				if(nodes1.size() > 0) {
					numOfBrn += nodes1.size();
				}
			}
			
			objData = new Object[numOfBrn][3];
			int cnt = 0;
			
			for (int i=0;i<brnNameQueryObj.length;i++) {
				List nodes1 = document.selectNodes("//BRANCH/ID[@brnCode="+String.valueOf(brnNameQueryObj[i][0])+"]/IP");
				
				if(nodes1.size()>0) {
					for (Iterator<Element> it = nodes1.iterator(); it.hasNext();) {
						Element data1 = (Element) it.next();
						objData[cnt][0] = String.valueOf(data1.element("FROM").attributeValue("fromIpAdd"));
						objData[cnt][1] = String.valueOf(data1.element("TO").attributeValue("toIpAdd"));
						objData[cnt][2] = String.valueOf(brnNameQueryObj[i][0]);
						cnt++;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return objData;
	}

	private Object[][] getDataFrmOrgXml(String fileName, IPFilter ipFilter, HttpServletRequest request) {
		String orgXmlFile = fileName + "\\xml\\IPFilter\\organisation.xml";
		File file = new File(orgXmlFile);
		Document document;
		Object[][] objData = null;
		
		try {
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//ORGANISATION/IP");
			objData = new Object[nodes.size()][2];
			int cnt = 0;
			
			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
				Element data = (Element) it.next();
				objData[cnt][0] = String.valueOf(data.element("FROM").attributeValue("fromIpAdd"));
				objData[cnt][1] = String.valueOf(data.element("TO").attributeValue("toIpAdd"));
				cnt++;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return objData;
	}

	private void getIPApplicable(String fileName, IPFilter ipFilter, HttpServletRequest request) {
		String fName = fileName + "\\xml\\IPFilter\\ipfilter.xml";
		File file = new File(fName);
		Document document;
		
		try {
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//IP_FILTER/IP_APPLICABLE");

			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
				Element data = (Element) it.next();
				String check = String.valueOf(data.attributeValue("applicable"));

				if (check.equals("Y")) {
					ipFilter.setIpFilterFlag("true");
				}
			}
			
			List Nodes = document.selectNodes("//IP_FILTER/IP_APPLICABLE/TYPE");

			for (Iterator<Element> it = Nodes.iterator(); it.hasNext();) {
				Element El = (Element) it.next();
				String ipType = String.valueOf(El.attributeValue("ipType"));

				if (ipType.equals("B")) {
					ipFilter.setBranchWiseCheck("true");
				}

				if (ipType.equals("O")) {
					ipFilter.setOrgWiseCheck("true");
				}
			}

			Nodes = document.selectNodes("//IP_FILTER/IP_APPLICABLE/EXCEPTIONS");
			String str = "";
			
			for (Iterator<Element> itr = Nodes.iterator(); itr.hasNext();) {
				Element Exception = (Element) itr.next();
				str = String.valueOf(Exception.attributeValue("empIDs"));
			}
			
			String query = "";
			if (str.length() > 0) {
				query = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID " + 
				" FROM HRMS_EMP_OFFC " + "  WHERE  EMP_ID IN(" + str + ")";
			}

			Object data[][] = getSqlModel().getSingleResult(query);
			HashMap mapdata = new HashMap();
			ArrayList<Object> list = new ArrayList<Object>();

			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					IPFilter bean = new IPFilter();
					bean.setEId(String.valueOf(data[i][2]));
					bean.setEName(String.valueOf(data[i][1]));
					bean.setEToken(String.valueOf(data[i][0]));
					list.add(bean);
					mapdata.put("" + i, "B");
				}
			}

			request.setAttribute("dataEmp", mapdata);
			ipFilter.setEmpList(list);

		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String getRecord(String fileName, IPFilter ipFilter, HttpServletRequest request) {
		String message = "";
		String fName = fileName + "\\xml\\IPFilter\\ipfilter.xml";
		File file = new File(fName);
		Document document;

		try {
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//IP_FILTER/IP_APPLICABLE/TYPE");
			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
				Element data = (Element) it.next();

				String ipType = String.valueOf(data.attributeValue("ipType"));

				if (ipType.equals("O")) {
					Object[][] result = getDataFrmOrgXml(fileName, ipFilter, request);
					HashMap mapdata = new HashMap();
					ArrayList<Object> list = new ArrayList<Object>();
					
					for (int i = 0; i < result.length; i++) {
						IPFilter bean = new IPFilter();
						bean.setFromIpAdd(String.valueOf(result[i][0]));
						bean.setToIpAdd(String.valueOf(result[i][1]));
						list.add(bean);
						mapdata.put("" + i, "G");
					}
					request.setAttribute("data", mapdata);
					ipFilter.setList(list);
				}
				 
				if (ipType.equals("B")) {
					Object[][] result = getDataFrmBrnXml(fileName, ipFilter, request);
					HashMap mapdata = new HashMap();
					ArrayList<Object> list = new ArrayList<Object>();
					
					for (int i = 0; i < result.length; i++) {
						IPFilter bean = new IPFilter();
						bean.setBranchfromIpAdd(String.valueOf(result[i][0]));
						bean.setBranchtoIpAdd(String.valueOf(result[i][1]));
						bean.setBrnCode(String.valueOf(result[i][2]));

						String brnNameQuery = "SELECT CENTER_NAME FROM HRMS_CENTER WHERE CENTER_ID=" + String.valueOf(result[i][2]) + 
						" ORDER BY UPPER(CENTER_NAME)";
						Object[][] brnNameQueryObj = getSqlModel().getSingleResult(brnNameQuery);

						bean.setBrnName(String.valueOf(brnNameQueryObj[0][0]));
						list.add(bean);
						mapdata.put("" + i, "Q");
					}
					request.setAttribute("dataBranch", mapdata);
					ipFilter.setBranchList(list); 
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		
		if (file.exists()) {
			getIPApplicable(fileName, ipFilter, request);
		} else {
			message = "File not found.";
		}
		return message;
	}

	public boolean save(IPFilter ipFilter, String fileName, HttpServletRequest request, String fileName1, String brnFile) {
		try {
			String empID[] = request.getParameterValues("eId");
			String fromIpAdd[] = request.getParameterValues("fromIpAdd");
			String toIpAdd[] = request.getParameterValues("toIpAdd");
			String branchfromIpAdd[] = request.getParameterValues("branchfromIpAdd");
			String branchtoIpAdd[] = request.getParameterValues("branchtoIpAdd");
			String brnCode[] = request.getParameterValues("brnCode");
			Document document = buildDocument(ipFilter, empID);
			
			try {
				new XMLReaderWriter().write(document, fileName);
			} catch (Exception e) {
				logger.error(e);
			}
			
			String orgType = ipFilter.getOrgWiseCheck();
			if (orgType.equals("true")) {
				Document document1 = buildDocumentForOrg(ipFilter, fromIpAdd, toIpAdd);
				
				try {
					new XMLReaderWriter().write(document1, fileName1);
				} catch (Exception e) {
					logger.error(e);
				}
			}
			
			String brnType = ipFilter.getBranchWiseCheck();
			if (brnType.equals("true")) {
				Document documentbranch = buildDocumentForBrn(ipFilter, branchfromIpAdd, branchtoIpAdd, brnCode, brnFile);
				
				try {
					new XMLReaderWriter().write(documentbranch, brnFile);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return true;
	}
}