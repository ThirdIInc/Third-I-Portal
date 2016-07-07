/*
 * @(#)MessageInfo.java	1.5 07/07/06
 *
 * Copyright 2001-2007 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.paradyne.model.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Used to store message information.
 */
public class MessageInfo {
    private Message message;
    private String path;
    private boolean textIsHtml = false;
    
    /**
     * Returns the bcc field.
     */
    public String getBcc() throws MessagingException {
        return formatAddresses(
            message.getRecipients(Message.RecipientType.BCC));
    }
    
    /**
     * Returns the body of the message (if it's plain text).
     */
    public String getBody() throws MessagingException, java.io.IOException {
        Object content = message.getContent();
        if (message.isMimeType("text/plain")) {
            return (String)content;
        } else if (message.isMimeType("multipart/alternative")) {
	    Multipart mp = (Multipart)message.getContent();
            int numParts = mp.getCount();
            for (int i = 0; i < numParts; ++i) {
                if (mp.getBodyPart(i).isMimeType("text/plain"))
                    return (String)mp.getBodyPart(i).getContent();
            }
            return "";   
        } else if (message.isMimeType("multipart/*")) { 
	    Multipart mp = (Multipart)content;
            if (mp.getBodyPart(0).isMimeType("text/plain"))
                return (String)mp.getBodyPart(0).getContent();
            else
                return "";
        } else
            return "";
    }
    
    /**
     * Returns the cc field.
     */
    public String getCc() throws MessagingException {
        return formatAddresses(
            message.getRecipients(Message.RecipientType.CC));
    }
    
    /**
     * Returns the date the message was sent (or received if the sent date
     * is null.
     */
    public String getDate() throws MessagingException {
        Date date;
        SimpleDateFormat df = new SimpleDateFormat("EE M/d/yy");
        if ((date = message.getSentDate()) != null)
            return (df.format(date));
        else if ((date = message.getReceivedDate()) != null)
            return (df.format(date));
        else
            return "";
     }
       
    /**
     * Returns the from field.
     */
    public String getFrom() throws MessagingException {
        return formatAddresses(message.getFrom());
    }

    /**
     * Returns the address to reply to.
     */
    public String getReplyTo() throws MessagingException {
	Address[] a = message.getReplyTo();
	if (a.length > 0)
	    return ((InternetAddress)a[0]).getAddress();
	else
	    return "";
    }
    
    /**
     * Returns the javax.mail.Message object.
     */
    public Message getMessage() {
        return message;
    }
    
    /**
     * Returns the message number.
     */
    public String getNum() {
        return (Integer.toString(message.getMessageNumber()));
    }
    
    /**
     * Returns the received date field.
     */
    public String getReceivedDate() throws MessagingException {
        if (hasReceivedDate())
            return (message.getReceivedDate().toString());
        else
            return "";
    }
    
    /**
     * Returns the sent date field.
     */
    public String getSentDate() throws MessagingException {
        if (hasSentDate())
            return (message.getSentDate().toString()); 
        else
            return "";
    }
    
    /**
     * Returns the subject field.
     */
    public String getSubject() throws MessagingException {
        if (hasSubject())
            return message.getSubject();
        else
            return "";
    }
    
    /**
     * Returns the to field.
     */
    public String getTo() throws MessagingException {
        return formatAddresses(
            message.getRecipients(Message.RecipientType.TO));
    }
    
    /**
     * Method for checking if the message has attachments.
     */
    public boolean hasAttachments() throws java.io.IOException, 
                                           MessagingException {
        boolean hasAttachments = false;
        if (message.isMimeType("multipart/*")) {
	    Multipart mp = (Multipart)message.getContent();
            if (mp.getCount() > 1)
                hasAttachments = true;
        }
            
        return hasAttachments;
    }
    
    
    public boolean hasAttachments1() throws java.io.IOException, 
    MessagingException {
    	 boolean hasAttachments = false;
       
         
         if (message.isMimeType("multipart/*")) {
         	        	          		 
 		    Multipart mp = (Multipart)message.getContent();
 		   if(mp.getContentType().contains("multipart/mixed") || mp.getContentType().contains("MULTIPART/mixed")){
 			  hasAttachments = true;
 			
 		  }
 		  else if( mp.getContentType().contains("multipart/alternative") || mp.getContentType().contains("MULTIPART/alternative")){
 			 hasAttachments = false;
 		  }
 		 else if( mp.getContentType().contains("multipart/related") || mp.getContentType().contains("MULTIPART/related")){
 			 hasAttachments = true;
 		  }
 		  else if (mp.getCount() > 1){
 	                hasAttachments = true;
 	          
 	        }
         }
       
         return hasAttachments;
}
    
    /**
     * Method for checking if the message has a bcc field.
     */
    public boolean hasBcc() throws MessagingException {
        return (message.getRecipients(Message.RecipientType.BCC) != null);
    }
    
    /**
     * Method for checking if the message has a cc field.
     */
    public boolean hasCc() throws MessagingException {
        return (message.getRecipients(Message.RecipientType.CC) != null);
    }
    
    /**
     * Method for checking if the message has a date field.
     */
    public boolean hasDate() throws MessagingException {
        return (hasSentDate() || hasReceivedDate());
    }
    
    /**
     * Method for checking if the message has a from field.
     */
    public boolean hasFrom() throws MessagingException {
        return (message.getFrom() != null);
    }
    
    /**
     * Method for checking if the message has the desired mime type.
     */
    public boolean hasMimeType(String mimeType) throws MessagingException {
        return message.isMimeType(mimeType);
    }
    
    /**
     * Method for checking if the message has a received date field.
     */
    public boolean hasReceivedDate() throws MessagingException {
        return (message.getReceivedDate() != null);
    }
    
    /**
     * Method for checking if the message has a sent date field.
     */
    public boolean hasSentDate() throws MessagingException {
        return (message.getSentDate() != null);
    }
    
    /**
     * Method for checking if the message has a subject field.
     */
    public boolean hasSubject() throws MessagingException {
        return (message.getSubject() != null);
    }
    
    /**
     * Method for checking if the message has a to field.
     */
    public boolean hasTo() throws MessagingException {
        return (message.getRecipients(Message.RecipientType.TO) != null);
    }
    
    /**
     * Method for mapping a message to this MessageInfo class.
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Utility method for formatting msg header addresses.
     */
    private String formatAddresses(Address[] addrs) {
        if (addrs == null)
            return "";
        StringBuffer strBuf = new StringBuffer(getDisplayAddress(addrs[0]));
        for (int i = 1; i < addrs.length; i++) {
            strBuf.append(", ").append(getDisplayAddress(addrs[i]));
        }
        return strBuf.toString();
    }
    
    /**
     * Utility method which returns a string suitable for msg header display.
     */
    private String getDisplayAddress(Address a) {
        String pers = null;
        String addr = null;
        if (a instanceof InternetAddress &&
           ((pers = ((InternetAddress)a).getPersonal()) != null)) {
	    addr = pers + "  "+"&lt;"+((InternetAddress)a).getAddress()+"&gt;";
        } else 
            addr = a.toString();
        return addr;
    }
    
    
    public String getText(Part p) throws
			    MessagingException, IOException {
			if (p.isMimeType("text/*")) {
			String s = (String)p.getContent();
			System.out.println("--------------------------------- In if p.isMimeType ------");
			textIsHtml = p.isMimeType("text/html");
			return s;
			}
			
			if (p.isMimeType("multipart/alternative")) {
				System.out.println("--------------------------------- In if of multipart/alternative ------");
		
			Multipart mp = (Multipart)p.getContent();
			System.out.println("--------------------------------- No of Parts"+mp.getCount());
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
			    Part bp = mp.getBodyPart(i);
			    if (bp.isMimeType("text/plain")) {
			        if (text == null)
			            text = getText(bp);
			        System.out.println("--------------------------------- in under if of text.palin --text="+text);
			        continue;
			    } else if (bp.isMimeType("text/html")) {
			        String s = getText(bp);
			        if (s != null){
			        	System.out.println("--------------------------------- in under else if of text/html --text="+s);
			            return s;
			            
			            }
			    } else {
			        return getText(bp);
			    }
			}
			return text;
			} else if (p.isMimeType("multipart/*")) {
				 String s=null;
				System.out.println("--------------------------------- In else of multipart/*  ------");
			Multipart mp = (Multipart)p.getContent();
		
			System.out.println("--------------------------------- No of Parts"+mp.getCount());
			for (int i = 0; i < mp.getCount(); i++) {
				
			 s = getText(mp.getBodyPart(i));
			    System.out.println("---------------------------- part message"+s);
			    if (s != null)
			        return s;
			}
			}
			
			return null;
    	}
    
    public String getTextAttch(Part p) throws
    MessagingException, IOException {
					if (p.isMimeType("text/*")) {
					String s = (String)p.getContent();
					System.out.println("--------------------------------- In if p.isMimeType ------");
					textIsHtml = p.isMimeType("text/html");
					return s;
					}
					
					if (p.isMimeType("multipart/alternative")) {
						System.out.println("--------------------------------- In if of multipart/alternative ------");
					
					Multipart mp = (Multipart)p.getContent();
					System.out.println("--------------------------------- No of Parts"+mp.getCount());
					String text = null;
					for (int i = 0; i < mp.getCount(); i++) {
					    Part bp = mp.getBodyPart(i);
					    if (bp.isMimeType("text/plain")) {
					        if (text == null)
					            text = getText(bp);
					        System.out.println("--------------------------------- in under if of text.palin --text="+text);
					        continue;
					    } else if (bp.isMimeType("text/html")) {
					        String s = getText(bp);
					        if (s != null){
					        	System.out.println("--------------------------------- in under else if of text/html --text="+s);
					            return s;
					            
					            }
					    } else {
					        return getText(bp);
					    }
					}
					return text;
					} else if (p.isMimeType("multipart/*")) {
						 String s=null;
						System.out.println("--------------------------------- In else of multipart/*  ------");
					Multipart mp = (Multipart)p.getContent();
					
					System.out.println("--------------------------------- No of Parts"+mp.getCount());
					for (int i = 0; i < mp.getCount(); i++) {
						String filepath=path;
						 BodyPart part = mp.getBodyPart(i);
					      String disposition = part.getDisposition();  
					      if ((disposition != null) && (disposition.equals(Part.ATTACHMENT) || (disposition.equals(Part.INLINE))))
					      {   
					    	 System.out.println("Stram values::"+part.getInputStream()+"::Disposition::"+part.getDisposition());
					    	  if(filepath != null)
					    	  {
					    		 String filename=filepath+"/"+ part.getFileName();  
					    		System.out.println("filename path::"+filename);
					    		 File file = new File(filename);  
					    		 InputStream in=part.getInputStream();
					    		 FileOutputStream fos = new FileOutputStream(file); 
					       			 byte[] buf = new byte[1024];
					       			 int c=0;
					       			 while((c=in.read(buf)) != -1)
					       			 {
					       			 	fos.write(buf, 0, c);
					       			 }
					   			in.close();
					   			fos.close();
					    	 }
					     }
					
					 /* s = getText(mp.getBodyPart(i));
					    System.out.println("---------------------------- part message"+s);
					    if (s != null)
					        return s;*/
					}
					}
					
					return null;
					}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
					    
   

}

