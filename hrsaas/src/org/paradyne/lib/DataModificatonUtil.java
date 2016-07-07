/**
 * 
 */
package org.paradyne.lib;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.lib.XMLReaderWriter;
import java.util.TreeMap;

public class DataModificatonUtil extends ModelBase {

	/**
	 * 
	 * @param path
	 * @param pooldir
	 * @param xmlfile(Gender-gender.xml,marriage-marriage.xml)
	 * @return TreeMap
	 * @throws Exception
	 */
	public TreeMap getGenderXml(String xmlfile) {
		//System.out.println("entry to getgenderxml method");
		String filename = null;
		String filename1 = null;
		ResourceBundle bundle = ResourceBundle.getBundle("globalMessages");
		String path = bundle.getString("data_path");
		String pooldir = (String) session.getAttribute("session_pool");

		filename = path + "\\DataModification\\" + pooldir + "\\common\\"
				+ xmlfile + ".xml";
		filename1 = path + "\\DataModification\\Default\\common\\" + xmlfile
				+ ".xml";
		//  System.out.println("in___+++++++++++++++++++"+ filename);
		TreeMap map = new TreeMap();
		File f = new File(filename);
		File f1 = new File(filename1);

		try {
			if (!f.exists()) {
				map = getShow(new XMLReaderWriter().parse(f1));
			} else {
				map = getShow(new XMLReaderWriter().parse(f));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;

	}

	public TreeMap getShow(Document document) {
		TreeMap map = new TreeMap();
		//System.out.println("entry to getshow method");
		String[][] objData = null;

		List node1 = document.selectNodes("//DATA//OPTION");
		objData = new String[node1.size()][3];
		int count = 0;

		for (Iterator iter = node1.iterator(); iter.hasNext();) {
			Element node2 = (Element) iter.next();

			/*System.out.println("attribute id----------------------"
					+ node2.attributeValue("name"));
			System.out.println("attribute id----------------------"
					+ node2.attributeValue("value"));
			System.out.println("attribute id----------------------"
					+ node2.attributeValue("active"));
			System.out.println("found node2: " + node2.getData());*/
			objData[count][0] = node2.attributeValue("value");
			objData[count][1] = node2.attributeValue("name");
			objData[count][2] = node2.attributeValue("active");

			count++;
		}// end of loop
		//System.out.println("length is+++++++++++++++++"+objData.length);

		for (int j = 0; j < objData.length; j++) {

			if (objData[j][2].equals("Y")) {

				map.put(String.valueOf(objData[j][0]), String
						.valueOf(objData[j][1]));

			}

		}
		return map;
	}

}
