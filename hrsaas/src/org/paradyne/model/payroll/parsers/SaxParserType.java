/**
 * @author venkatesh
 * @Date 17-07-2008
 */
package org.paradyne.model.payroll.parsers;

import java.io.IOException;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * This class is used to read the contents from XML file i.e., salaryZone_emptype
 */
public class SaxParserType extends DefaultHandler implements java.io.Serializable
{

    private String ElementName = "";
    
    public String typeCode=""; 
    public Object[][] resultObject =null;
   
    public Object[][] parse (InputSource xmlFile,String typeId) throws SAXException
    {
        SAXParser parser = new SAXParser ();
         parser.setContentHandler (this);
         resultObject = new Object[1][5];
       this.typeCode = typeId;
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
     * called <EMPTYPE> ... </EMPTYPE>, then this method is called when <ESIPARAM> tag is
     * Encountered while parsing the Current XML File. The AttributeList Parameter has
     * the list of all Attributes declared for the Current Element in the XML File.
    */
    public void startElement (String uri, String local, String qName, Attributes atts) throws SAXException
    {
        ElementName = local;
        
        
       if(local.equalsIgnoreCase("EMPTYPE"))
        {
    	   if( atts.getValue("code").trim().equals(typeCode.trim())){
             	
	    	   resultObject[0][0] = atts.getValue("code").trim();
	    	   resultObject[0][1] = atts.getValue("esi").trim();
	    	   resultObject[0][2] = atts.getValue("pt").trim();
	    	   resultObject[0][3] = atts.getValue("pf").trim();
	    	   try{
	    		   
	    	   if(atts.getValue("pfminamt")== null ||
	    			   atts.getValue("pfminamt").equals("null") ||
	    			   atts.getValue("pfminamt").trim().equals("") ){
	    		   
	    		   resultObject[0][4] = "N";
	    	   }else
	    		   resultObject[0][4] = atts.getValue("pfminamt").trim();
	    	   
	    	   }catch(Exception e){
	    		   
	    		   resultObject[0][4] = "N";
	    	   }
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
     * above explanation, this method is called when </EMPTYPE> tag is reached
    */
    public void endElement (String uri, String local, String qName) throws SAXException
    {
        
        if(local.equalsIgnoreCase("EMPTYPE"))
        {
          
        }
    }
    
 }
