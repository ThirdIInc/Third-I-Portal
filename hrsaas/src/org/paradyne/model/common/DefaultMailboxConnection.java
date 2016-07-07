/*
 * @(#)MailboxConnection.java 1.00 2005/02/05
 *
 * Copyright (c) 2005, Stephan Sann
 *
 * 05.02.2005 ssann        Vers. 1.0     created
 */
package org.paradyne.model.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;
import javax.servlet.http.HttpServletRequest;

import de.lotk.yawebmail.application.Constants;
//import de.lotk.yawebmail.bean.DisplayMessageBean;
import de.lotk.yawebmail.bean.LoginDataBean;
// import de.lotk.yawebmail.bean.LoginDataBean;
import de.lotk.yawebmail.exceptions.AccessDeniedException;
import de.lotk.yawebmail.exceptions.ConnectionEstablishException;
import de.lotk.yawebmail.exceptions.LogoutException;
import de.lotk.yawebmail.exceptions.MailboxFolderException;
import de.lotk.yawebmail.exceptions.MessageDeletionException;
import de.lotk.yawebmail.exceptions.MessageMovementException;
import de.lotk.yawebmail.exceptions.MessageRetrieveException;
import de.lotk.yawebmail.util.JavamailUtils;
import org.paradyne.bean.common.HomePage;
import org.paradyne.bean.common.LoginBean;

import com.sun.mail.pop3.POP3Message;
import com.sun.mail.util.BASE64DecoderStream;

/**
 * Default-Implementierung des Interfaces MailboxConnection.
 * 
 * @author Stephan Sann
 * @version 1.0
 */
public class DefaultMailboxConnection extends ModelBase implements MailboxConnection {
	
		 static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);
  // ----------------------------------------------------------------
	// Konstanten

 /* *//** serialVersionUID *//*
								 * private static final long serialVersionUID =
								 * -8364316467231432686L;
								 */

  // ---------------------------------------------------------
	// Instanz-Variablen

  /** Speichert die Login-Daten */
  // private LoginDataBean loginData = null;

  /** Speichert einen JavaMail-Store */
  private Store store = null;

  /** Speichert einen JavaMail-Folder */
  private Folder folder = null;

  /** Speichert den FolderSeparator */
  private Character separator = null;

  /** Speichert ein TreeSet mit allen Foldern des Accounts */
  private TreeSet allFolders = null;


  // ---------------------------------------------------------------
	// Konstruktor

  /**
	 * Initialisiert eine DefaultMailboxConnection-Instanz mit Login-Daten.
	 * 
	 * @param loginData
	 *            Die Login-Daten
	 */
  /*
	 * public DefaultMailboxConnection(LoginDataBean loginData) {
	 * 
	 * this.loginData = loginData; }
	 */


  // ---------------------------------------------------------- private
	// Methoden

  /**
	 * Erstellt einen javax.mail.Store gemaess Anforderungen und schreibt sie in
	 * die Instanz-Variable.
	 */
  private void createStore() {

 logger.info("-----------------------------------------------In CreateStore method of Default Mailbox connetion ---1");
	  // Get a Properties and Session object
    Properties props = JavamailUtils.getProperties();
    Session session = Session.getDefaultInstance(props, null);
    session.setDebug(false);

    try {
    	 logger.info("-----------------------------------------------In CreateStore method of Default Mailbox connetion ---2");
      String protocolId = "pop3";
      this.store = session.getStore(protocolId);
    }
    catch(NoSuchProviderException e) {

      throw(new RuntimeException("Unknown Protocol: "+e));
    }
  }

  /**
	 * Setzt den in der Instanzvariable folder gespeicherten Folder.
	 * 
	 * @param mode
	 *            Mode, in dem der Folder geoeffnet werden soll.
	 */
  private void openFolder(int mode) throws MailboxFolderException {

    // Versuchen den Folder zu oeffnen
    try {
    	
      this.folder.open(mode);
    }
    catch(MessagingException e) {

      throw(new MailboxFolderException(("Probleme beim Oeffnen des Folders " +
              this.folder.getName()), e, this.folder.getName()));
    }
  }

  /**
	 * Fuellt das private TreeSet mit allen Foldern des Accounts (Connection
	 * muss fuer diese Aktion geoeffnet sein).
	 */
  private void updateAllFoldersTreeSet() throws MessagingException {
    
    this.folder = store.getFolder("INBOX");

  }


  // ----------------------------------------------------- oeffentliche
	// Methoden

  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#setLoginData(de.lotk.yawebmail.bean.LoginDataBean)
	 */
  /*
	 * public void setLoginData(LoginDataBean loginData) {
	 * 
	 * this.loginData = loginData; }
	 */

  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#login()
	 */
  public void login() throws ConnectionEstablishException,
      AccessDeniedException {
	  logger.info("-------------------------------------in Login method of Default MailboxConn----1");
    // Ggf. Store erstellen
    if(this.store == null) {

      this.createStore();
    }
    logger.info("-------------------------------------in Login method of Default MailboxConn----2");
    // Login durchfuehren
    try {

   /*
	 * this.store.connect(this.loginData.getMailboxHost(),
	 * this.loginData.getMailboxPort(), this.loginData.getMailboxUser(),
	 * this.loginData.getMailboxPassword());
	 */
    
    	 this.store.connect("pop.secureserver.net",
                110, "prem1977@gmail.com",
                 "glodyne01");
    	

      // ggf. Separator setzen
      if(this.separator == null) {

        this.separator =
                new Character(this.store.getDefaultFolder().getSeparator());
      }
      logger.info("-------------------------------------in Login method of Default MailboxConn----3");
      // ggf. Liste mit allen Foldern des Accounts fuellen
      if(this.allFolders == null) {

        this.updateAllFoldersTreeSet();
      }
      logger.info("-------------------------------------in Login method of Default MailboxConn----4");
    }
    catch(AuthenticationFailedException afe) {

      throw(new AccessDeniedException("Autorisierung gescheitert.", afe));
    }
    catch(MessagingException me) {

      logger.info("Verbindung gescheitert.");
    }
  }

  
  public String login(HomePage home) throws ConnectionEstablishException,
  AccessDeniedException {
		  logger.info("-------------------------------------in Login method of Default MailboxConn----1");
		// Ggf. Store erstellen
		if(this.store == null) {
		
		  this.createStore();
		}
		logger.info("-------------------------------------in Login method of Default MailboxConn----2");
		// Login durchfuehren
		try {
		
		/*
		 * this.store.connect(this.loginData.getMailboxHost(),
		 * this.loginData.getMailboxPort(), this.loginData.getMailboxUser(),
		 * this.loginData.getMailboxPassword());
		 */
			LoginBean loginBean =(LoginBean)context.getAttribute("email");
			String serverName=loginBean.getHostName();
			int serverPort=Integer.parseInt(loginBean.getPortNo());
			String userId=home.getEmailUserId();
			String pwd=home.getEmailPwd();
			pwd=new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(pwd);
			logger.info("-------------------------------------in Login method of Default MailboxConn----serverName="+serverName);
			logger.info("-------------------------------------in Login method of Default MailboxConn----serverPort"+serverPort);
			logger.info("-------------------------------------in Login method of Default MailboxConn----userId="+userId);
			logger.info("-------------------------------------in Login method of Default MailboxConn----pwd"+pwd);
			try{
			 this.store.connect(serverName,
					 serverPort, userId,
					 pwd);
			}
			catch(AuthenticationFailedException afe) {
					System.out.println("----------in AuthenticationFailedException catch");
					//afe.printStackTrace();
			      return "A";
			    }
			    catch(MessagingException me) {
			    	System.out.println("----------in MessagingException catch");
					//me.printStackTrace();
			    	return "F";
			    }
			
			 logger.info("-------------------------------------in Login method of Default MailboxConn----3-------a");
		  // ggf. Separator setzen
		  if(this.separator == null) {
		
		    this.separator =
		            new Character(this.store.getDefaultFolder().getSeparator());
		  }
		  logger.info("-------------------------------------in Login method of Default MailboxConn----3");
		  // ggf. Liste mit allen Foldern des Accounts fuellen
		  if(this.allFolders == null) {
		
		    this.updateAllFoldersTreeSet();
		  }
		  logger.info("-------------------------------------in Login method of Default MailboxConn----4");
		  
		}
		catch(Exception afe) {
			System.out.println("----------Exception catch");
			//afe.printStackTrace();
		  return "F";
		}
		return "S";
}


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#login(java.lang.String)0
	 */
  public void login(String folderName) throws ConnectionEstablishException,
      AccessDeniedException, MailboxFolderException {

    this.login();
    this.changeFolder(folderName);
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#logout()
	 */
  public void logout() throws LogoutException {
	  logger.info("-------------------------------------in Logout method of Default MailboxConn----1");
	    // Ggf. Store erstellen
    // Ggf. Folder schliessen
    if((this.folder != null) && this.folder.isOpen()) {

      try {

        this.folder.close(true);
        this.allFolders=null;
      }
      catch(MessagingException me) {

        throw(new LogoutException("Problem beim Schliessen des Folders.", me));
      }

      this.folder = null;
    }

    // Ggf. Store schliessen
    if(this.store != null) {

      try {

        this.store.close();
      }
      catch(MessagingException me) {

        throw(new LogoutException("Problem beim Schliessen des Stores.", me));
      }

      this.store = null;
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#validateLoginData()
	 */
  public void validateLoginData() throws ConnectionEstablishException,
          AccessDeniedException {

    this.login();
    logger.info("-------------------------------------in validate LoginData method of Default MailboxConn----1");
    // Ggf. Store erstellen
    // Wenn es ein Problem beim Logout gibt, dann ist das nicht so tragisch...
    try {

      this.logout();
    }
    catch(LogoutException e) {

      // empty
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#getCurrentFolder()
	 */
  public Folder getCurrentFolder() throws MailboxFolderException {
logger.info("------------------------------------------ in getCurrent folder method");
    if(this.folder != null) {

      return(this.folder);
    }
    else {

      throw(new MailboxFolderException("Es existiert z.Zt. kein Folder.",
              null));
    }
  }


  /**
	 * Setzt die Instanzvariable folder auf den gewuenschten Folder
	 * 
	 * @param folderName
	 *            Name des gewuenschten Folders.
	 */
  public void changeFolder(String folderName) throws MailboxFolderException {

    try {

      if(Constants.LEERSTRING.equals(folderName)) {

        this.folder = this.store.getDefaultFolder();
      }
      else {

        this.folder = this.store.getFolder(folderName);
      }

      if(this.folder == null) {

        throw(new MailboxFolderException(("Invalid folder: " + folderName),
                null));
      }
    }
    catch(MessagingException me) {

      throw(new MailboxFolderException(("Probleme mit Folder: " + folderName),
              me, folderName));
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#getMessages()
	 */
  public Message[] getMessages(HomePage home,HttpServletRequest request) throws MailboxFolderException,
          MessageRetrieveException {

    Message[] messages = null;
    MessageInfo msginfo =new MessageInfo(); 

    // Folder updaten und oeffnen
   
    this.openFolder(Folder.READ_ONLY);

    // Messages holen
    try {
    
    	String[] index=new String[2];
    	index[0]="0";
    	index[1]="15";
      messages = this.folder.getMessages();
     
      ArrayList list =new ArrayList();
      
      HashMap afdata=new HashMap();
      int count=0;
      for (int i =  messages.length -1; i >=messages.length - 15 ; i--) {
    	  logger.info("=================================================In getmessages method mesg="+messages[i].getSubject());
    	  logger.info("=================================================In getmessages method mesg   desc= "+messages[i].getDescription());
    	  HomePage bean=new HomePage();
    	  if(String.valueOf(messages[i].getSubject()).equals("")||String.valueOf(messages[i].getSubject()).equals("null"))
    	  {
    		  bean.setSubject("noSubject");
    	  }
    	  else
    	  bean.setSubject(String.valueOf(messages[i].getSubject()));
    	  Address[] add=messages[i].getFrom();
   
    	  bean.setSender(String.valueOf(add[0]));
    	  Date d=messages[i].getSentDate();
    		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
    		if(d==null){
				  bean.setDate("");
			}
			else{
			String fordate = formater.format(d);
			bean.setDate(String.valueOf(fordate));
			}
    		 try {
					
 				
				  msginfo.setMessage(messages[i]);
				  bean.setAttChkFlag(msginfo.hasAttachments1());
				 
				  } catch (Exception e) {
						// TODO: handle exception
					}
    	  bean.setMsgCode(String.valueOf(i+1));
    	  int mailSize= Integer.parseInt(String.valueOf(messages[i].getSize()))/1024;
    	  bean.setMailSize(String.valueOf(mailSize));
    	  afdata.put(""+count,"A");
    	  list.add(bean);
    	  count++;
  	}
     double row = (double)messages.length/15.0;
     System.out.println("----------------row ="+row);
      BigDecimal value = new BigDecimal(row);
      System.out.println("----------------row Count"+value.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
      int rowCount =Integer.parseInt(value.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
      String[][] pageIndex=new String[rowCount][2];
     
		int pageSInd=0,pageEInd=15;
		
      for (int i = 0; i < pageIndex.length; i++) {
    	  
		pageIndex[i][0] = String.valueOf(pageSInd);
		pageIndex[i][1] = String.valueOf(pageEInd);
		pageSInd =pageSInd +15;
		pageEInd = pageEInd +15;
	}
      home.setNoOfMails(String.valueOf(messages.length));
      home.setMailList(list);
      request.setAttribute("data",afdata);
      request.setAttribute("msgData",messages.length);
      request.setAttribute("index",index);    
      request.setAttribute("pageIndex",pageIndex);    
    }
   
    catch(MessagingException me) {

      throw(new MessageRetrieveException(("Konnte Nachrichten aus Folder \"" +
              this.folder.getName() + "\" nicht beziehen."), me));
    }

    return(messages);
  }
  
  
	public Message[] getMessagesInd(HomePage home, HttpServletRequest request,String startInd,String endInd)
			throws MailboxFolderException, MessageRetrieveException {

		Message[] messages = null;
		MessageInfo msginfo =new MessageInfo(); 
		// Folder updaten und oeffnen

		this.openFolder(Folder.READ_ONLY);

		// Messages holen
		try {

			String[] index = new String[2];
			index[0] = String.valueOf(startInd);
			index[1] = String.valueOf(endInd);
			messages = this.folder.getMessages();
			
			if(Integer.parseInt(endInd)>messages.length){
				endInd=String.valueOf(messages.length);
			}
			if(Integer.parseInt(startInd)<0){
				startInd=String.valueOf(1);
			}
			
			ArrayList list = new ArrayList();

			HashMap afdata = new HashMap();
			int count = 0;
			for (int i = (messages.length - Integer.parseInt(startInd))-1; i >= messages.length - Integer.parseInt(endInd); i--) {
				logger
						.info("=================================================In getmessages method mesg="
								+ messages[i].getSubject());
				logger
						.info("=================================================In getmessages method mesg   desc= "
								+ messages[i].getDescription());
				HomePage bean = new HomePage();
				 if(String.valueOf(messages[i].getSubject()).equals("")||String.valueOf(messages[i].getSubject()).equals("null"))
		    	  {
		    		  bean.setSubject("noSubject");
		    	  }
		    	  else
		    	  bean.setSubject(String.valueOf(messages[i].getSubject()));
				Address[] add = messages[i].getFrom();

				bean.setSender(String.valueOf(add[0]));
				Date d = messages[i].getSentDate();
				SimpleDateFormat formater = new SimpleDateFormat(
						"dd/MM/yyyy - hh:mm");
				if(d==null){
					  bean.setDate("");
				}
				else{
				String fordate = formater.format(d);
				bean.setDate(String.valueOf(fordate));
				}
				try {
					
	 				
					  msginfo.setMessage(messages[i]);
					  bean.setAttChkFlag(msginfo.hasAttachments1());
					 
					  } catch (Exception e) {
							// TODO: handle exception
						}
				bean.setMsgCode(String.valueOf(i + 1));
				  int mailSize= Integer.parseInt(String.valueOf(messages[i].getSize()))/1024;
				  bean.setMailSize(String.valueOf(mailSize));
				afdata.put("" + count, "A");
				list.add(bean);
				count++;
			}
			
			double row = (double)messages.length/15.0;
		   
		      BigDecimal value = new BigDecimal(row);
		     
		      int rowCount =Integer.parseInt(value.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
		      String[][] pageIndex=new String[rowCount][2];
		     
				int pageSInd=0,pageEInd=15;
				
		      for (int i = 0; i < pageIndex.length; i++) {
		    	  
				pageIndex[i][0] = String.valueOf(pageSInd);
				pageIndex[i][1] = String.valueOf(pageEInd);
				pageSInd =pageSInd +15;
				pageEInd = pageEInd +15;
			}
			home.setNoOfMails(String.valueOf(messages.length));
			home.setMailList(list);
			request.setAttribute("data", afdata);
			request.setAttribute("msgData", messages.length);
			request.setAttribute("index", index);
			request.setAttribute("pageIndex",pageIndex);    
		}

		catch (MessagingException me) {

			throw (new MessageRetrieveException(
					("Konnte Nachrichten aus Folder \"" + this.folder.getName() + "\" nicht beziehen."),
					me));
		}

		return (messages);
	}
			  
  
  
  
  public Message[] getMessagesHome(HomePage home,HttpServletRequest request) throws MailboxFolderException,
  MessageRetrieveException {
		
		Message[] messages = null;
		MessageInfo msginfo =new MessageInfo(); 
		
		// Folder updaten und oeffnen
		
		this.openFolder(Folder.READ_ONLY);
		
		// Messages holen
		try {
			
		messages = this.folder.getMessages();
		System.out.println("--------------------------------- messages count"+folder.getMessageCount());
		System.out.println("---------------------------------unread messages count"+folder.getUnreadMessageCount());
		ArrayList list =new ArrayList();
		
		HashMap afdata=new HashMap();
		int count=0;
		if(messages.length<6)
		{
			for (int i =  messages.length -1; i >=0 ; i--) {
			  logger.info("=================================================In getmessages method mesg="+messages[i].getSubject());
			  logger.info("=================================================In getmessages method mesg   desc= "+messages[i].getDescription());
			  HomePage bean=new HomePage();
			  if(String.valueOf(messages[i].getSubject()).equals("")||String.valueOf(messages[i].getSubject()).equals("null"))
	    	  {
	    		  bean.setSubject("noSubject");
	    	  }
	    	  else
	    	  bean.setSubject(String.valueOf(messages[i].getSubject()));
			  Address[] add=messages[i].getFrom();
			
			  bean.setSender(String.valueOf(add[0]));
			  Date d=messages[i].getSentDate();
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
				String fordate = formater.format(d);
				try {
					
	 				
					  msginfo.setMessage(messages[i]);
					  bean.setAttChkFlag(msginfo.hasAttachments1());
					 
					  } catch (Exception e) {
							// TODO: handle exception
						}
			  bean.setDate(String.valueOf(fordate));
			  bean.setMsgCode(String.valueOf(i+1));
			  afdata.put(""+count,"A");
			  list.add(bean);
			  count++;
			}
		}
		else
		{
			for (int i =  messages.length -1; i >= messages.length-6; i--) {
				  logger.info("=================================================In getmessages method mesg="+messages[i].getSubject());
				  logger.info("=================================================In getmessages method mesg   desc= "+messages[i].getDescription());
				  HomePage bean=new HomePage();
				  if(String.valueOf(messages[i].getSubject()).equals("")||String.valueOf(messages[i].getSubject()).equals("null"))
		    	  {
		    		  bean.setSubject("noSubject");
		    	  }
		    	  else
		    	  bean.setSubject(String.valueOf(messages[i].getSubject()));
				  Address[] add=messages[i].getFrom();
				 
				  bean.setSender(String.valueOf(add[0]));
				  Date d=messages[i].getSentDate();
					SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
					if(d==null){
						  bean.setDate("");
					}
					else{
					String fordate = formater.format(d);
					bean.setDate(String.valueOf(fordate));
					}
				  bean.setMsgCode(String.valueOf(i+1));
				  try {
						
		 				
					  msginfo.setMessage(messages[i]);
					  bean.setAttChkFlag(msginfo.hasAttachments1());
					 
					  } catch (Exception e) {
							// TODO: handle exception
						}
				  afdata.put(""+count,"A");
				  list.add(bean);
				  count++;
				}
		}
		home.setNoOfMails(String.valueOf(messages.length));
		home.setMailList(list);
		request.setAttribute("data",afdata);
		}
		
		catch(MessagingException me) {
		
		throw(new MessageRetrieveException(("Konnte Nachrichten aus Folder \"" +
		      this.folder.getName() + "\" nicht beziehen."), me));
		}
		
		return(messages);
}
  
	  public Message[] getMessages() throws MailboxFolderException,
	  MessageRetrieveException {
	
		Message[] messages = null;
		
		
		// Folder updaten und oeffnen
		
		this.openFolder(Folder.READ_ONLY);
		
		// Messages holen
		try {
			
		messages = this.folder.getMessages();
		}
	
		
		catch(MessagingException me) {
		
		throw(new MessageRetrieveException(("Konnte Nachrichten aus Folder \"" +
		      this.folder.getName() + "\" nicht beziehen."), me));
		}
		
		return(messages);
	}



  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#getMessage(int)
	 */
  public Message getMessage(int messageNumber) throws MailboxFolderException,
      MessageRetrieveException {

    Message message = null;

    // Folder updaten und oeffnen
    this.openFolder(Folder.READ_ONLY);

    // Messages holen
    try {

      message = this.folder.getMessage(messageNumber);
    }
    catch(MessagingException me) {

      throw(new MessageRetrieveException(("Konnte Nachricht " + messageNumber +
              " aus Folder \"" + this.folder.getName() + "\" nicht beziehen."),
              me));
    }

    return(message);
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#getEnvelopes()
	 */
  public Message[] getEnvelopes() throws MailboxFolderException,
          MessageRetrieveException {
	  logger.info("-------------------------------------in getEnvelopes of Default MailboxConn----1");
	    // Ggf. Store erstellen
    try {

      // Wenn der aktuelle Folder der Default-Folder ist, leeres Array zurueck
      if(this.folder.getParent() == null) {

        return(new Message[0]);
      }

      // Messages beziehen
      Message[] messages = this.getMessages();

      // Envelopes laden
      FetchProfile fp = new FetchProfile();
      fp.add(FetchProfile.Item.ENVELOPE);
      this.folder.fetch(messages, fp);

      // Messages zurueckliefern
      return(messages);
    }
    catch(MessagingException me) {

      throw(new MessageRetrieveException(("Konnte Envelopes aus Folder \"" +
              this.folder.getName() + "\" nicht beziehen."), me));
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#setDeletedFlag(int)
	 */
  public void setDeletedFlag(int messageNumber) throws MailboxFolderException,
          MessageDeletionException {

    // TODO schoener machen!
    int[] uebergabe = new int[1];
    uebergabe[0] = messageNumber;

    this.setMultipleDeletedFlags(uebergabe);
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#setMultipleDeletedFlags(int[])
	 */
  public void setMultipleDeletedFlags(int[] messageNumbers)
          throws MailboxFolderException, MessageDeletionException {
	
	  logger.info("----------------------------------------------- In setMultipleDeleteFlags"+messageNumbers.length);
	  
    // Folder updaten und oeffnen
    this.openFolder(Folder.READ_WRITE);

    // Uebergebene Mails als expunged markieren
    for(int ww = 0; ww < messageNumbers.length; ww++) {

      try {

        this.folder.getMessage(messageNumbers[ww]).setFlag(Flags.Flag.DELETED,
                true);
      }
      catch(MessagingException me) {

        throw(new MessageDeletionException("Probleme beim DELETED-Flags setzen",
                 me));
      }
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#getFolderSeparator()
	 */
  public char getFolderSeparator() throws Exception {

    // Haben wir einen Folder am Start?
    if(this.separator != null) {

      return(this.separator.charValue());
    }

    // Sonst koennen wir nicht helfen.
    else {

      throw(new Exception("FolderSeparator nicht beziehbar."));
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#getAllFolders()
	 */
  public TreeSet getAllFolders() throws Exception {
logger.info("---------------------------------------------in getallFolders method");
    // Haben wir die Liste am Start?
    if(this.allFolders != null) {

      return(this.allFolders);
    }

    // Sonst koennen wir nicht helfen.
    else {

      throw(new Exception("Ordnerliste nicht beziehbar."));
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#createFolder(java.lang.String)
	 */
  public void createFolder(String folderName) throws MailboxFolderException {

    try {

      Folder newFolder = this.store.getFolder(folderName);

      if(! newFolder.exists()) {

        if(! newFolder.create(Folder.HOLDS_MESSAGES)) {

          throw(new Exception("Folder konnte nicht erstellt werden."));
        }

        // Liste aller Ordner aktualisieren
        this.updateAllFoldersTreeSet();
      }
    }
    catch(Exception e) {

      e.printStackTrace();
      throw(new MailboxFolderException(e.getMessage(), e, folderName));
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#deleteFolder(java.lang.String)
	 */
  public void deleteFolder(String folderName) throws MailboxFolderException {

    try {

      Folder folderToDelete = this.store.getFolder(folderName);

      if(folderToDelete.exists()) {

        if(! folderToDelete.delete(true)) {

          throw(new Exception("Folder konnte nicht geloescht werden."));
        }

        // Liste aller Ordner aktualisieren
        this.updateAllFoldersTreeSet();
      }
    }
    catch(Exception e) {

      e.printStackTrace();
      throw(new MailboxFolderException(e.getMessage(), e, folderName));
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.business.MailboxConnection#moveMessages(int[],
	 *      java.lang.String)
	 */
  public void moveMessages(int[] messageNumbers, String targetFolderName) throws
          MailboxFolderException, MessageMovementException {

    try {

      // Aktuellen Folder oeffnen und Array mit zu verschiebenden Messages
		// bauen
      this.openFolder(Folder.READ_WRITE);
      Message[] messages = new Message[messageNumbers.length];

      for(int ii = 0; ii < messageNumbers.length; ii++) {

        messages[ii] = this.folder.getMessage(messageNumbers[ii]);
      }

      // Messages in den Zielordner kopieren.
      Folder targetFolderObj = this.store.getFolder(targetFolderName);
      this.folder.copyMessages(messages, targetFolderObj);

      // Messages in diesem Ordner auf geloescht setzen
      for(int kk = 0; kk < messages.length; kk++) {

        messages[kk].setFlag(Flags.Flag.DELETED, true);
      }
    }
    catch(MessagingException e) {

      throw(new MessageMovementException("Problem beim Verschieben.", e));
    }
  }


  /*
	 * (non-Javadoc)
	 * 
	 * @see de.lotk.yawebmail.Lifecycle#destroy()
	 */
  public void destroy() {

    // Wenn es ein Problem beim Logout gibt, dann ist das nicht so tragisch...
    try {

      this.logout();
    }
    catch(LogoutException e) {

      // empty
    }

   // this.loginData = null;
  }

  public String getDesc(HomePage home,String msgNo,HttpServletRequest request)  throws MailboxFolderException,
  MessageRetrieveException {
	Message message = null;
		
	int mesgId= Integer.parseInt(msgNo);
	// Folder updaten und oeffnen
	
	
	
	// Messages holen
	try {
		this.openFolder(Folder.READ_ONLY);
		logger.info("----------------------------------------- msg Id"+mesgId);
	message = this.folder.getMessage(mesgId);
	
	request.setAttribute("message", message);
	
	/*StringBuffer mesg = readMail(message);
	
	//home.setMsgDesc(String.valueOf(mesg));
	
	request.setAttribute("message", mesg);*/
	
	
	/*try{
		String mess="";			
		BufferedInputStream stream = new BufferedInputStream(message.getInputStream());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
	      byte[] inBuf = new byte[4096];
	      int len = 0;
	      while((len = stream.read(inBuf)) > 0) {
	        baos.write(inBuf, 0, len);
	      }
	    stream.close();
	    
		home.setMsgDesc(baos.toString());	      

//part.setContent(baos.toString(), part.getContentType());
//addDisplayPart(part, displayParts, multiparts);
		
	}catch(Exception ex){
		
	}*/
	
	/*
	 * 
	 * String desc = message.getDescription();
	 * logger.info("---------------------------------------------------Msg
	 * Description "+desc); home.setMsgDesc(desc);
	 */
	home.setSubject(String.valueOf(message.getSubject()));
	  	Address[] add=message.getFrom();

	  	home.setSender(String.valueOf(add[0]));
	  Date d=message.getSentDate();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String fordate = formater.format(d);
		home.setDate(String.valueOf(fordate));
		
	
		return "S";
	}
	
	
	catch(MessagingException me) {
	
	return "F";
	}
	
  
	  
  }
  
  

  
  public static StringBuffer readMail(Message imessages) {

	 /* Store store = null;
	  Folder folder = null;
	  try {
	  Properties props = System.getProperties();
	  Session session = Session.getDefaultInstance(props, null);
	  store = session.getStore("pop3");
	  store.connect(POPServer, userName, passWord);
	  folder = store.getDefaultFolder();
	  if (folder == null)
	  throw new Exception("No default folder");
	  folder = folder.getFolder("INBOX");
	  if (folder == null)
	  throw new Exception("No POP3 INBOX");
	  folder.open(Folder.READ_ONLY);
	  Message[] imessages = folder.getMessages();
	  for (int i = 0; i < imessages.length; i++) {

	  String attachflag = "N";
	  String msgno = imessages.getMessageNumber()+"";
	  InternetAddress from [] =(InternetAddress []) imessages.getReplyTo();
	  String fromid = from [0]+"";
	  String subject = "";
	  if("".equals(imessages.getSubject())) {
	  subject ="no subject";
	  }else {
	  subject=imessages.getSubject();
	  }*/
	  StringBuffer messageBody =new StringBuffer();
	  try {
	  String attachflag = "N";
	  String size = imessages.getSize()+"";
	  String date = imessages.getSentDate()+"";

	 
	  String[] files ={};
	  StringBuffer filePath =new StringBuffer();
	  
	  
	  int lines = 0;
	  
	  
	  Part messagePart = imessages;
	  Part attachPart = imessages;
	  Object content = messagePart.getContent(); 
	  if (content instanceof Multipart) {
	  messagePart=((Multipart)content).getBodyPart(0); 
	  if(((Multipart)content).getCount()>1){
		  int cnt = 0;
		  for(int atcount=1;atcount<((Multipart)content).getCount();atcount++){
			  attachPart=((Multipart)content).getBodyPart(atcount);
			  String fileName = attachPart.getFileName();
			  if(fileName!=null) {
				  cnt++;
			  }
		  } 
	  files = new String[cnt];
	  for(int atcount=1;atcount<((Multipart)content).getCount();atcount++){
	  attachPart=((Multipart)content).getBodyPart(atcount);
	  String fileName = attachPart.getFileName();
	  if(fileName!=null) {
	  attachflag = "Y";
	  files[atcount-1] = fileName;
	 // filePath.append(writeFile(attachPart.getInputStream(),fileName,"c:/AttachmentFile"));
	  if (!"".equals(filePath.toString())) {
		  filePath.append(",");
	  	}
	  logger.info("filePath : "+filePath.toString());
	  }
	  } 
	  } 
	 

	  String contentType = messagePart.getContentType();
	  InputStream is = messagePart.getInputStream();
	  BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	  String inputLine;
	  if(contentType.startsWith("multipart/alternative")) { 
	  inputLine=reader.readLine(); 

	  while (inputLine != null) { 
	  inputLine=reader.readLine(); 
	  if(inputLine==null){
	  break;
	  } 
	  if(inputLine.startsWith("Content-Type")||inputLine.startsWith("charset")||inputLine.endsWith("iso-8859-1\"")||inputLine.startsWith("Content-Transfer")||inputLine.endsWith(">")) {
	  // lines = 1;
	  }else{
	  messageBody.append(inputLine+"<br>"); 
	  }
	  }
	  }else if (contentType.startsWith("text/plain")) {
	  while ((inputLine = reader.readLine()) != null) {
	  messageBody.append(inputLine+"<br>");
	  }
	  }else if (contentType.startsWith("text/html")) {
	  while ((inputLine = reader.readLine()) != null) {
	  messageBody.append(inputLine+"<br>");
	  }
	  }
	   logger.info("Message Body : \n"+messageBody);
	  reader.close();
	  is.close();

	  }

	  } catch (Exception ex) {
	  ex.printStackTrace();
	  } 
	  return messageBody;
  }
  
  
}
