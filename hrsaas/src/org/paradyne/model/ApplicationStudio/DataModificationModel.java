/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.DataModification;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author AA0563
 *
 */
public class DataModificationModel extends ModelBase {

	public boolean save(String fileName, DataModification dm, String[] gendername,String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Document document = buildDocument(dm,gendername,firstletter,active);
		
		try {
			new XMLReaderWriter().write(document, fileName);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	private Document buildDocument(DataModification dm, String[] gendername,String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		
		Document document = null;
		Element header;
		Element data;
		Element root = null, gender = null;
		document = DocumentHelper.createDocument();
		//root = document.addElement("DATA");
		root = document.addElement("DATA").addAttribute("name", "gender");
		for (int i = 0; i < gendername.length; i++) {
			try {
				data = root.addElement("OPTION").addAttribute("name",
						"" + gendername[i].toString()).addAttribute("value",
								firstletter[i].toString()).addAttribute("active",
										active[i].toString());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return document;
	}
//for marriage
	public void marriageSave(String fileName, DataModification dm,
			String[] marriage, String[] firstletter,String[] active) {
     Document document = buildDocumentmarriage(dm,marriage,firstletter,active);
		
		try {
			new XMLReaderWriter().write(document, fileName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	private Document buildDocumentmarriage(DataModification dm, String[] marriage,String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		
		Document document = null;
		Element header;
		Element data;
		Element root = null, gender = null;
		document = DocumentHelper.createDocument();
		//root = document.addElement("DATA");
		root = document.addElement("DATA").addAttribute("name", "Marriage Status");
		for (int i = 0; i < marriage.length; i++) {
			try {
				data = root.addElement("OPTION").addAttribute("name",
						"" + marriage[i].toString()).addAttribute("value",
								firstletter[i].toString()).addAttribute("active",
										active[i].toString());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return document;
	}
//for Blood Group
	public void bloodGroupSave(String fileName, DataModification dm,
			String[] bloodgroup,String[] firstletter,String[] active) {
		  Document document = buildDocumentblood(dm,bloodgroup,firstletter,active);
			
			try {
				new XMLReaderWriter().write(document, fileName);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		
		
	}

	private Document buildDocumentblood(DataModification dm, String[] bloodgroup,String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		
		Document document = null;
		Element header;
		Element data;
		Element root = null, gender = null;
		document = DocumentHelper.createDocument();
		//root = document.addElement("DATA");
		root = document.addElement("DATA").addAttribute("name", "Blood Group");
		for (int i = 0; i < bloodgroup.length; i++) {
			try {
				data = root.addElement("OPTION").addAttribute("name",
						"" + bloodgroup[i].toString()).addAttribute("value",
								firstletter[i].toString()).addAttribute("active",
										active[i].toString());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return document;
	}

	public void payModeSave(String fileName, DataModification dm,
			String[] rowpaymode, String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		  Document document = buildDocumentpaymode(dm,rowpaymode,firstletter,active);
			
			try {
				new XMLReaderWriter().write(document, fileName);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		
	}

	private Document buildDocumentpaymode(DataModification dm, String[] rowpaymode,String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		
		Document document = null;
		Element header;
		Element data;
		Element root = null, gender = null;
		document = DocumentHelper.createDocument();
		//root = document.addElement("DATA");
		root = document.addElement("DATA").addAttribute("name", "PayMode");
		for (int i = 0; i < rowpaymode.length; i++) {
			try {
				data = root.addElement("OPTION").addAttribute("name",
						"" + rowpaymode[i].toString()).addAttribute("value",
								firstletter[i].toString()).addAttribute("active",
										active[i].toString());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return document;
	}

	public void addressModeSave(String fileName, DataModification dm,
			String[] rowaddressType, String[] firstletter,String[] active) {
		Document document = buildDocumentaddressmode(dm,rowaddressType,firstletter,active);
		
		try {
			new XMLReaderWriter().write(document, fileName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	private Document buildDocumentaddressmode(DataModification dm, String[] rowaddressType,String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		
		Document document = null;
		Element header;
		Element data;
		Element root = null, gender = null;
		document = DocumentHelper.createDocument();
		//root = document.addElement("DATA");
		root = document.addElement("DATA").addAttribute("name", "AddressMode");
		for (int i = 0; i < rowaddressType.length; i++) {
			try {
				data = root.addElement("OPTION").addAttribute("name",
						"" + rowaddressType[i].toString()).addAttribute("value",
								firstletter[i].toString()).addAttribute("active",
										active[i].toString());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return document;
	}

	public void incrementModeSave(String fileName, DataModification dm,
			String[] rowincrementType, String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		
			Document document = buildDocumentincrementmode(dm,rowincrementType,firstletter,active);
			
			try {
				new XMLReaderWriter().write(document, fileName);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		
	}
	
	
	private Document buildDocumentincrementmode(DataModification dm, String[] rowincrementType,String[] firstletter,String[] active) {
		// TODO Auto-generated method stub
		
		Document document = null;
		Element header;
		Element data;
		Element root = null, gender = null;
		document = DocumentHelper.createDocument();
		//root = document.addElement("DATA");
		root = document.addElement("DATA").addAttribute("name", "IncrementType");
		for (int i = 0; i < rowincrementType.length; i++) {
			try {
				data = root.addElement("OPTION").addAttribute("name",
						"" + rowincrementType[i].toString()).addAttribute("value",
								firstletter[i].toString()).addAttribute("active",
										active[i].toString());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return document;
}
	
	/**
	 * @author MANISH SAKPAL
	 * @param fileName
	 * @param dm
	 * @param rowZoneType
	 * @param firstletter
	 * @param active
	 */
	public void zoneModeSave(String fileName, DataModification dm,
			String[] rowZoneType, String[] firstletter,String[] active) {
			Document document = buildDocumentZonemode(dm,rowZoneType,firstletter,active);
			try {
				new XMLReaderWriter().write(document, fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * @author MANISH SAKPAL
	 * @param dm
	 * @param rowZoneType
	 * @param firstletter
	 * @param active
	 * @return
	 */
	private Document buildDocumentZonemode(DataModification dm,
			String[] rowZoneType, String[] firstletter, String[] active) {
		Document document = null;
		Element data;
		Element root = null;
		document = DocumentHelper.createDocument();
		//root = document.addElement("DATA");
		root = document.addElement("DATA").addAttribute("name", "Zone");
		for (int i = 0; i < rowZoneType.length; i++) {
			try {
				data = root.addElement("OPTION").addAttribute("name",
						"" + rowZoneType[i].toString()).addAttribute("value",
								firstletter[i].toString()).addAttribute("active",
										active[i].toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return document;
}
	
	
	public String[][] show(Document document, DataModification dm) throws Exception {
		System.out.println("entry to show method");
		// TODO Auto-generated method stub
		
		String[][] objData = null;
		
		
		List node1 = document.selectNodes("//DATA//OPTION");
		objData = new String[node1.size()][3];
		int count = 0;
		
		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();

			System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			System.out.println("attribute id----------------------"
					+ node2.attributeValue("active"));
			System.out.println("attribute id----------------------"
					+ node2.attributeValue("value"));
			
			System.out.println("found node2: " + node2.getData());
			objData[count][0] = node2.attributeValue("name");
			objData[count][1] = node2.attributeValue("active");
			objData[count][2] = node2.attributeValue("value");
			
			count++;
		}// end of loop
		return objData;
	}

	public String[][] showMarriage(Document document, DataModification dm) {
		// TODO Auto-generated method stub
		String[][] objData1 = null;
		List node1 = document.selectNodes("//DATA//OPTION");
		objData1 = new String[node1.size()][3];
		int count = 0;
		
		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();

			System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			System.out.println("attribute id----------------------"
					+ node2.attributeValue("active"));
			
			System.out.println("found node2: " + node2.getData());
			objData1[count][0] = node2.attributeValue("name");
			objData1[count][1] = node2.attributeValue("active");
			objData1[count][2] = node2.attributeValue("value");
			count++;
		}// end of loop
		return objData1;
	}
	
	public String[][] showBloodGroup(Document document, DataModification dm) {
		// TODO Auto-generated method stub
		String[][] objData2 = null;
		List node1 = document.selectNodes("//DATA//OPTION");
		objData2 = new String[node1.size()][3];
		int count = 0;
		
		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();

			System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			
			System.out.println("found node2: " + node2.getData());
			objData2[count][0] = node2.attributeValue("name");
			objData2[count][1] = node2.attributeValue("active");
			objData2[count][2] = node2.attributeValue("value");
			
			count++;
		}// end of loop
		return objData2;
	}
	public String[][] showPayMode(Document document, DataModification dm) {
		// TODO Auto-generated method stub
		String[][] objData3 = null;
		List node1 = document.selectNodes("//DATA//OPTION");
		objData3 = new String[node1.size()][3];
		int count = 0;
		
		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();

			System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			
			System.out.println("found node2: " + node2.getData());
			objData3[count][0] = node2.attributeValue("name");
			objData3[count][1] = node2.attributeValue("active");
			objData3[count][2] = node2.attributeValue("value");
			count++;
		}// end of loop
		return objData3;
	}
	public String[][] showAddress(Document document, DataModification dm) {
		// TODO Auto-generated method stub
		String[][] objData4 = null;
		List node1 = document.selectNodes("//DATA//OPTION");
		objData4 = new String[node1.size()][3];
		int count = 0;
		
		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();

			System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			
			System.out.println("found node2: " + node2.getData());
			objData4[count][0] = node2.attributeValue("name");
			objData4[count][1] = node2.attributeValue("active");
			objData4[count][2] = node2.attributeValue("value");
			
			count++;
		}// end of loop
		return objData4;
	}

	public String[][] showIncrement(Document document, DataModification dm) {
		// TODO Auto-generated method stub
		String[][] objData5 = null;
		List node1 = document.selectNodes("//DATA//OPTION");
		objData5 = new String[node1.size()][3];
		int count = 0;
		
		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();

			System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			
			System.out.println("found node2: " + node2.getData());
			objData5[count][0] = node2.attributeValue("name");
			objData5[count][1] = node2.attributeValue("active");
			objData5[count][2] = node2.attributeValue("value");
			
			count++;
		}// end of loop
		return objData5;
	}
	
	/**
	 * @author MANISH SAKPAL
	 * @param document
	 * @param dm
	 * @return
	 */
	public String[][] showZone(Document document, DataModification dm) {
		String[][] objData = null;
		List node1 = document.selectNodes("//DATA//OPTION");
		objData = new String[node1.size()][3];
		int count = 0;
		
		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();
			System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			System.out.println("found node2: " + node2.getData());
			objData[count][0] = node2.attributeValue("name");
			objData[count][1] = node2.attributeValue("active");
			objData[count][2] = node2.attributeValue("value");
			
			count++;
		}// end of loop
		return objData;
	}
	
	public  boolean  saveData(DataModification dm,String type, String[] name,String[] value,String[] active)
	{
		System.out.println("entry to save data method");
		boolean  flag1=false;
		String delQuery="DELETE FROM  HRMS_DATA_MODIFICATION  WHERE MOD_TYPE='"+type+"'";
		boolean flag=getSqlModel().singleExecute(delQuery);
		
		Object[][]delData=new Object[name.length][4];
		System.out.println("after delete  query_________");
		for (int i = 0; i < name.length; i++) {
			delData[i][0]=type;
			delData[i][1]=name[i];
			delData[i][2]=value[i];
			delData[i][3]=active[i];
			
			
		}
		 
		if(flag){
		String query1="INSERT INTO HRMS_DATA_MODIFICATION(MOD_TYPE,MOD_NAME,MOD_VALUE,MOD_ACTIVE)VALUES(?,?,?,?)";
		flag1=getSqlModel().singleExecute(query1,delData);
		}
		
		if(flag1==true)
			return flag1;
		else return flag1;
		
		
	}
	
	
	
	
	
	
}

