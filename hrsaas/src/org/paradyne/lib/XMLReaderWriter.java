package org.paradyne.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Sunil
 * @date 30 Jan 2008
**/

/**
 * This class reads and writes the XML documents
**/

public class XMLReaderWriter {
	static Logger logger = Logger.getLogger(XMLReaderWriter.class);
	
	/**
	 * Reads the XML file
	**/
	/**
	 * @param url -: Specifies the File object
	 * @return Document object
	 * @throws Exception
	**/
	public Document parse(File url) throws Exception {
        SAXReader reader = new SAXReader();
        return reader.read(url);
    }
	
	/**
	 * Writes Document object to a file 
	**/
	/**
	 * @param document -: Specifies Document object to write
	 * @param fileName -: Specifies the file in which data will be written
	 * @throws IOException
	**/
	public void write(Document document, String fileName) throws IOException {
	    File f = null;
		try{
			f = new File(fileName);
			logger.info("getParent----------------------------"+f.getParent());
			if(!f.exists()){
				f = new File(f.getParent());
				boolean dir = f.mkdirs();
				logger.info("dir created----------------------------"+dir);
			}
			logger.info("file----------------------------");
		}catch(Exception e){
			logger.info("dir created----------------------------"+e);
		} //end of try-catch block
		XMLWriter writer = new XMLWriter(new FileWriter(fileName));
		writer.write(document);
		writer.close();
	}
	
	/**
	 * Writes Document object to a file 
	**/
	/**
	 * @param document -: Specifies Document object to write
	 * @param fileName -: Specifies the file in which data will be written
	 * @param path
	 * @param attrName
	 * @param value
	 * @throws IOException
	**/
	public void writeChild(Document document, String fileName, String path, String attrName, String value) 
	throws IOException {
		File f = null;
		try{
			f = new File(fileName);
			logger.info("getParent----------------------------"+f.getParent());
			if(!f.exists()){
				f = new File(f.getParent());
				boolean dir = f.mkdirs();
				logger.info("dir created----------------------------"+dir);
			}
			logger.info("file----------------------------");
		}catch(Exception e){
			logger.info("dir created----------------------------"+e);
		} //end of try-catch block
		XMLWriter writer = new XMLWriter( new FileWriter( fileName) );
	    writer.write( document );
	    writer.close();
	}
}