package org.paradyne.lib.autoupdate;

import java.io.*;
import java.util.*;
import oracle.jdbc.driver.OraclePreparedStatement;
import java.sql.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.text.*;

public class AutoUpdate {
	
	public void startUpdate() {
		try {
			org.paradyne.lib.ftp.JakartaFtpWrapper ftp = new org.paradyne.lib.ftp.JakartaFtpWrapper();
			//create folder for peoplepower
			File ppUpdate = new File("C:\\peoplepowerupdates");
			if(!ppUpdate.exists()) { 
				ppUpdate.mkdir(); 
			}
			/*
			*		Create a log file
			*/
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
			String dateform = sd.format(new java.util.Date());
			File file = new File("C:\\peoplepowerupdates\\logs" + dateform + ".txt");
			PrintWriter logger1 = new PrintWriter(new FileWriter(file));
			
			String serverName = "192.168.25.34";
			if (ftp.connectAndLogin(serverName, "ftpuser", "Data@ccess")) {
				logger1.write("\nConnected to Server");
				try {
					/**
					 *		Download file having current version information
					 */
					ftp.changeWorkingDirectory("/ftp/peoplepower/");
					ftp.setPassiveMode(true);
					ftp.ascii();
					ftp.downloadFile("versions.xml","C:\\peoplepowerupdates\\versions.xml");
					logger1.write("\nfile download : Version.xml");
					/**
					 *		Read versions file to see whether new updates are available
					 */
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("C:\\peoplepowerupdates\\versions.xml"));
						doc.getDocumentElement().normalize();
						
						// get the root directory
						Element rootElmnt = (Element) doc.getElementsByTagName("root").item(0);
						Element rootNmElmnt = (Element) rootElmnt.getElementsByTagName("path").item(0);
						String rootDir = ((Node)rootNmElmnt.getChildNodes().item(0)).getNodeValue();
						
						// get the list of versions available
						NodeList nodeLst = doc.getElementsByTagName("version");
						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node fstNode = nodeLst.item(s);
							if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
								//get the status				
								Element fstNmElmnt = (Element) ((Element) fstNode).getElementsByTagName("status").item(0);
								String status = ((Node) fstNmElmnt.getChildNodes().item(0)).getNodeValue();
								
								/**
								*		If status is pending start migrating the Patch.
								*/
								if(status.equals("pending")) {									
									//get the folder name
									Element lstNmElmnt = (Element) ((Element) fstNode).getElementsByTagName("foldername").item(0);
									String foldername = ((Node) lstNmElmnt.getChildNodes().item(0)).getNodeValue();
									logger1.write("\nUpdate Avaialble For Date : "+ foldername);	
									
									//Create a logger file
									File file1 = new File("C:\\peoplepowerupdates\\"+foldername);
									if(!file1.exists()) { 
										file1.mkdir(); 
									}
									PrintWriter logger = new PrintWriter(new FileWriter(new File("C:\\peoplepowerupdates\\"+foldername+"\\log"+dateform+".txt")));
									
									//download database information file
									try {
										ftp.downloadFile("clients.properties","C:\\peoplepowerupdates\\clients.properties");
										logger1.println("Client Information file downloaded");
									} catch(Exception exce) {
										logger1.println("Client connection file not found :"+exce);
									}
									
									// Go to working folder
									ftp.changeWorkingDirectory("/ftp/peoplepower/"+foldername+"/");
									/*
									*		Start the schema updates
									*/
									try{
											boolean schemaUpdate = executeQueries(ftp,logger,foldername);
											
											/*
											*	if database is updated successfully then transfer the files
											*/
											if(schemaUpdate) {
												/*
										       	*	Update the files
										       	*/
										       	if(patchUpdate(ftp,rootDir,foldername,logger)) {
										       		logger.println("\nFile Transfer complete");
										       		((Node) fstNmElmnt.getChildNodes().item(0)).setNodeValue("done");

										       	} else {
										       		logger.println("\nFile Transfer could not be completed. \nPlease reupload the files.\nIf database is successfully updated, please remove query file from new patch.");
										       	}
											} else {
												logger.write("Files are not transfered as database update failed.");
											}
										
									} catch(Exception db) {
										logger.write("Error :"+db);
									} finally {
										logger.close();
									}
									try {
										logger1.println("uploading logger file for "+foldername);
										boolean uploadStatus = ftp.uploadFile("C:\\peoplepowerupdates\\"+foldername+"\\log"+dateform+".txt","log"+dateform+".txt");
										logger1.println("Logger upload status : "+uploadStatus);
									} catch(Exception e) {
										logger1.println("Logger file could not be uploaded");
										e.printStackTrace();
									}
								}
							}
						}
						
						try{
							TransformerFactory transformerfactory = TransformerFactory.newInstance();
				            Transformer transformer = transformerfactory.newTransformer();
				       		DOMSource domSource = new DOMSource(doc);
				            transformer.transform(domSource, new StreamResult(new FileOutputStream("C:\\peoplepowerupdates\\versions.xml")));			
				            
				            ftp.changeWorkingDirectory("/ftp/peoplepower/");
							boolean uploadStatus = ftp.uploadFile("C:\\peoplepowerupdates\\versions.xml","versions.xml");
							if (uploadStatus)logger1.println("Version file uploaded");
						} catch(Exception exc) {
							logger1.println("Version file could not be uploaded");
						}
				} catch (Exception ftpe) {
					logger1.println("\nError : "+ftpe);
				} finally {
					
				}
			} else {
				logger1.println("\nUnable to connect to server");
			}
			logger1.println("\nUpdate Finished");
			
			
			//upload the logger back
			try{
				
				logger1.println("Uploading the main logger file");
				logger1.close();
				ftp.uploadFile("C:\\peoplepowerupdates\\logs" + dateform+ ".txt","logs" + dateform+ ".txt");
				
			}catch(Exception except) {
				except.printStackTrace();
			} finally {
				
			}
			ftp.logout();
			ftp.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 		To execute queries
	 * 
	 */
	public boolean executeQueries(org.paradyne.lib.ftp.JakartaFtpWrapper ftp,PrintWriter  logger,String foldername) {
		try{
			ftp.downloadFile("query.properties","C:\\peoplepowerupdates\\"+foldername+"\\query.properties");
		}catch(Exception exce) {
			logger.println("Query file not found :"+exce);
			logger.println("Continue with file replacements");
			return true;
		}
		
		Properties props = new Properties();
		try{
			props.load(new FileInputStream("C:\\peoplepowerupdates\\clients.properties"));
		} catch (Exception e) {
			logger.println("Database connection information file not found");
			return false;
		}
		Connection conn = null;
		try {
			String strLine = "";
			String strLineQuery = "";
			try {
				BufferedReader queryReader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream("C:\\peoplepowerupdates\\"+foldername+"\\query.properties"))));
				while ((strLine = queryReader.readLine()) != null) {
					strLineQuery += strLine;
				} // end of while
				queryReader.close();
			} catch (Exception e) {// Catch exception if any
				logger.println("Error : Could not get Query file :" + e);
				logger.println("Exit : Query Processing");
				return false;
			} // end of catch
			
			/*
			 * 		Create the connection
			 */
			String[] clientSplit = props.getProperty("clients").split(",");
			
			try {
				Class.forName(props.getProperty("driver"));
				conn = java.sql.DriverManager.getConnection(props.getProperty("connectionString"),props.getProperty("clients"), props.getProperty("password"));
				conn.setAutoCommit(false);
				logger.println("Connection created successfully");
			} catch (Exception e) {
				logger.println("Database Connection could not be established : "+e);
				logger.println("Exit : Query Processing");
				return false;
			}
			
			for (int i = 0; i <= clientSplit.length-1; i++) {
				logger.println("client Name:" + clientSplit[i]);
				String strLineQueryes = strLineQuery.replaceAll("XXX.",clientSplit[i] + ".");
				String[] batchQueries = strLineQueryes.split(";");
				logger.println("No of Queries :"+(batchQueries.length));

				for (int j = 0; j <= batchQueries.length-1; j++) {
					String filePath1="";
					Vector<Object> clobData1 = new Vector<Object>();
					//logger.println("batchQueries - :" + batchQueries[j]);
					String[] splitedString=batchQueries[j].split("#CLOB#");
					if(splitedString!=null && splitedString.length>0){
						batchQueries[j]="";
						for (int k = 0; k < splitedString.length; k++) {
							if(k==1 || k==3) {
								filePath1=splitedString[k];
								batchQueries[j]+=" ? ";
								clobData1.add(getClob(filePath1));
							} else {
						 		batchQueries[j]+=splitedString[k];
							}
						}
					}
					OraclePreparedStatement stmt = (OraclePreparedStatement) conn.prepareStatement(
							batchQueries[j], ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					if(clobData1.size()>0) {
						for (int k = 0; k < clobData1.size(); k++) {
							stmt.setObject(k+1, clobData1.get(k).toString());	
						}
					}					
					stmt.executeUpdate();
					logger.println("Query No - "+j+" run successfully");
					stmt.close();
				} // end of loop
			} // end of loop
			conn.commit();
			conn.close();			
			return true;
		} catch (Exception e) {
			try {
				logger.println("FETAL ERROR : All the queries are rollbacked. Patch processing can not continue. Verify the logs and upload a new patch again.\n"+e);
				logger.println("Exit : Query Processing");
				conn.rollback();
				conn.close();
			}catch(Exception con) {
				logger.println("Error while closing connection :"+con);
			}
			logger.println("Error :" + e);
			return false;
		} // end of catch
	} // end of main method
	
	public StringBuffer getClob(String fileName){
		String strLine="";
		StringBuffer strLineQuery=new StringBuffer();
		try {	
			BufferedReader br1 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(fileName))));
			// Read File Line By Line
			while ((strLine=br1.readLine()) != null) {
				strLineQuery.append(strLine+" ");
			} // end of while
			br1.close();
		} catch (Exception e) {// Catch exception if any
			
		} // end of catch
		return strLineQuery;
	}
	
	/*
	 * 		To update files
	 * 
	 */
	public boolean patchUpdate(org.paradyne.lib.ftp.JakartaFtpWrapper ftp,String rootDir, String foldername, PrintWriter  logger) {
		try{
		/**
		*		Download the patch
		*/
		File patch = new File("C:\\peoplepowerupdates\\"+foldername);
		if(!patch.exists()) { 
			patch.mkdir(); 
		}
		try{
			ftp.downloadFile("instructions.xml","C:\\peoplepowerupdates\\"+foldername+"\\instructions.xml");
			logger.println("\nfile download : instructions.xml");
		}catch(Exception ex) {
			logger.println("Could not download instructions file :"+ex);
			return false;
		}	
		/**
		*		Read the instructions 
		*/
		Document ins = null;
		try {
			ins = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("C:\\peoplepowerupdates\\"+foldername+"\\instructions.xml"));
			ins.getDocumentElement().normalize();
		} catch(Exception ex) {
			logger.println("Error in Instructions file format : "+ex);
			return false;
		}
		/**
		*		Get the List of files to migrate.
		*/
		NodeList insLst = ins.getElementsByTagName("fileset");
		// List of filesets
		
		if(insLst.getLength() > 0) 
		for (int x = 0; x < insLst.getLength(); x++) {
			Node insNode = insLst.item(x);
			if (insNode.getNodeType() == Node.ELEMENT_NODE) {
				try{ 
					//get the file name
					Element nameNmElmnt = (Element)((Element) insNode).getElementsByTagName("name").item(0);
					String name = ((Node) nameNmElmnt.getChildNodes().item(0)).getNodeValue();
	
					//get the source
					Element srcNmElmnt = (Element)((Element) insNode).getElementsByTagName("source").item(0);
					String source = ((Node) srcNmElmnt.getChildNodes().item(0)).getNodeValue();
	
					//get the destination
					Element destNmElmnt = (Element)((Element) insNode).getElementsByTagName("destination").item(0);
					String destination = ((Node) destNmElmnt.getChildNodes().item(0)).getNodeValue();
	
					/**
					*		Copy the file to destination
					*/
					String dir = rootDir.trim().concat(destination.trim());
					File destfolder = new File(dir);
					if(!destfolder.exists()) {
						boolean dirBoolean = destfolder.mkdirs();
						if(!dirBoolean) {
							logger.write("Could not create destination folder");
						}
					}
					ftp.downloadFile(source+"\\"+name, dir+"\\"+name);
					logger.println("\nfile downloaded : from : "+source+"\\"+name+" - to : "+dir+"\\"+name);
				}catch(Exception ex) {
					logger.println("Error in File transfer : Please verify Name, source, Destination elements");
					return false;
				}
			}						
		}
		return true;
		}catch(Exception ex) {			
			return false;
		}
	}
}