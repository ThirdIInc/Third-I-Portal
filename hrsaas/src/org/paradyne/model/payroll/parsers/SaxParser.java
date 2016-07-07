/**
 * @author venkatesh
 * @Date 17-07-2008
 */
package org.paradyne.model.payroll.parsers;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * This class is used to read the contents from XML file i.e., ESI Parameter
 */
public class SaxParser extends DefaultHandler implements java.io.Serializable
{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SaxParser.class);
    private String ElementName = "";
    public Object[][] resultObject =null;
    private String esiEffDate="";
    public Object[][] parse (InputSource xmlFile,String esiEffDate) throws SAXException
    {
        SAXParser parser = new SAXParser ();
         parser.setContentHandler (this);
         resultObject = new Object[1][7];
         this.esiEffDate =esiEffDate;
        try
        {
            parser.parse (xmlFile);
        }
        catch (Exception e)
        {
        	logger.error(e.getMessage());
        }

        return  resultObject;
    }

    /**
     * Called when the starting of the Element is reached. For Example if we have Tag
     * called <ESIPARAM> ... </ESIPARAM>, then this method is called when <ESIPARAM> tag is
     * Encountered while parsing the Current XML File. The AttributeList Parameter has
     * the list of all Attributes declared for the Current Element in the XML File.
    */

    public void startElement (String uri, String local, String qName, Attributes atts) throws SAXException
    {
        ElementName = local;
        
        
       if(local.equalsIgnoreCase("ESIPARAM"))
        {
    	   if( atts.getValue("date").trim().equals(esiEffDate)){
    	   resultObject[0][0] = atts.getValue("debitCode").trim();
    	   resultObject[0][1] = atts.getValue("formula").trim();
    	   resultObject[0][2] = atts.getValue("percentage").trim();
    	   resultObject[0][3] = atts.getValue("date").trim();
    	   resultObject[0][4] = atts.getValue("gross").trim();
    	   resultObject[0][5] = atts.getValue("month_start").trim();
    	   resultObject[0][6] = atts.getValue("month_end").trim();
    	   }
        }
        
        
    }

    /**
     * While Parsing the XML file, if extra characters like space or enter Character
     * are encountered then this method is called. If you don't want to do anything
     * special with these characters, then you can normally leave this method blank.
    */

    public void characters (char[] text, int start, int length) throws SAXException
    {
        String Content = new String (text, start, length);
       
        if((Content != null && !Content.trim().equals("")))
        {
        }
        
    }

    /**
     * Called when the Ending of the current Element is reached. For example in the
     * above explanation, this method is called when </ESIPARAM> tag is reached
    */

    public void endElement (String uri, String local, String qName) throws SAXException
    {
        
        if(local.equalsIgnoreCase("ESIPARAM"))
        {
        
        }
    }
   
 }
