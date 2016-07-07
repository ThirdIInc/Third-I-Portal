/**
 * @author venkatesh
 * @Date 18-07-2008
 */
package org.paradyne.model.payroll.parsers;

import java.io.IOException;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * This class is used to read the contents from XML file i.e., PF Parameter
 */
public class SaxParserPf extends DefaultHandler implements java.io.Serializable
{

    private String ElementName = "";
    public boolean isPrincipleStarted = false;
    public Object[][] resultObject =null;
    private String pfEffDate="";
    public Object[][] parse (InputSource xmlFile,String pfEffDate) throws SAXException
    {
        SAXParser parser = new SAXParser ();
         parser.setContentHandler (this);
         resultObject = new Object[1][8];
         this.pfEffDate=pfEffDate;
        try
        {
            parser.parse (xmlFile);
        }
        catch (IOException e)
        {
            throw new SAXException (e);
        }

        return  resultObject;
    }


    /**
     * Called when the starting of the Element is reached. For Example if we have Tag
     * called <PFPARAM> ... </PFPARAM>, then this method is called when <ESIPARAM> tag is
     * Encountered while parsing the Current XML File. The AttributeList Parameter has
     * the list of all Attributes declared for the Current Element in the XML File.
    */
    public void startElement (String uri, String local, String qName, Attributes atts) throws SAXException
    {
        ElementName = local;
        
        
       if(local.equalsIgnoreCase("PFPARAM"))
        {
    	   if( atts.getValue("date").trim().equals(pfEffDate)){
    	   resultObject[0][0] = atts.getValue("debitCode").trim();
    	   resultObject[0][1] = atts.getValue("formula").trim();
    	   resultObject[0][2] = atts.getValue("percentage").trim();
    	   resultObject[0][3] = atts.getValue("date").trim();
    	   resultObject[0][4] = atts.getValue("emolAmt").trim();
    	   resultObject[0][5] = atts.getValue("deductCriteria").trim();
    	   resultObject[0][6] = atts.getValue("maxLimitCheck").trim();
    	   resultObject[0][7] = atts.getValue("emolMaxLimit").trim();
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
     * above explanation, this method is called when </PFPARAM> tag is reached
    */
    public void endElement (String uri, String local, String qName) throws SAXException
    {
        
        if(local.equalsIgnoreCase("PFPARAM"))
        {
           
        }
    }
   
 }
