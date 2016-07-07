/**
 * 
 */
package org.paradyne.model.payroll.incometax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.ETds;
import org.paradyne.lib.ModelBase;

import com.tin.tds.FormValidator;
import com.tin.tds.bean.FileStatistics;
import com.tin.tds.uff.ReportGenerator;

/**
 * @author AA0517
 *
 */
public class ETdsModel extends ModelBase {

	/*public void getBtnValidate(HttpServletResponse response, String path, String quaYear) {
		System.out.println("getBtnValidate");
		StringBuffer str = null;
		boolean errorInFile=false;
		try {
			//Object obj = ((JButton)e.getSource()).getParent().getParent().getParent().getParent();
			// final MyDialog dlg = new MyDialog((JFrame)obj);
			//  dlg.setModal(true);
			// dlg.addWindowListener(new WindowAdapter() {

			System.out.println("getBtnValidate            1");
			
			String tab = "^";
			FormValidator obj_FormValidator = new FormValidator();
			String poolName=String.valueOf(session.getAttribute("session_pool"));
			String inFileName =path+ "\\datafiles\\" + poolName+"\\incometax\\write.txt";
		//	String inFileName = "C:\\write.txt";
			String errorFileName = path+ "\\datafiles\\" + poolName+"\\incometax";
			String hashFileName = null;
			String statisticFileName = null;
			String htmlErrorFileName = null;
			String userFriendlyFileBHDD = null;
			String userFriendlyFileCD = null;
			String uffNamingBHDD = null;
			String uffNamingCD = null;
			File addrFile = new File(inFileName);
			String fileName = addrFile.getName();
			int errorCode = 0;
			File errorDir = new File(errorFileName);
			if (errorFileName.length() == 0)
				errorCode = 8;
			if (errorCode == 0) {
				System.out.println("getBtnValidate            2");
				int i;
				for (i = inFileName.length() - 1; i > -1; i--) {
					char ch;
					if ((ch = inFileName.charAt(i)) == '.')
						break;
				}

				if (i > 0) {
					System.out.println("getBtnValidate            3");
					int j;
					for (j = i; j > -1; j--)
						if (inFileName.charAt(j) == '\\')
							break;

					hashFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, i) + ".fvu";
					htmlErrorFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, i) + "err.html";
					statisticFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, i) + ".html";
					userFriendlyFileBHDD = errorFileName + "\\"
							+ inFileName.substring(j + 1, i) + "_BH" + ".html";
					userFriendlyFileCD = errorFileName + "\\"
							+ inFileName.substring(j + 1, i) + "_CD" + ".html";
					uffNamingBHDD = inFileName.substring(j + 1, i) + "_BH"
							+ ".html";
					uffNamingCD = inFileName.substring(j + 1, i) + "_CD"
							+ ".html";
					errorFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, i) + ".err";
				} else {
					System.out.println("getBtnValidate            4");
					int j;
					for (j = inFileName.length() - 1; j > -1; j--)
						if (inFileName.charAt(j) == '\\')
							break;

					hashFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, inFileName.length())
							+ ".fvu";
					htmlErrorFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, inFileName.length())
							+ "err.html";
					statisticFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, inFileName.length())
							+ ".html";
					userFriendlyFileBHDD = errorFileName + "\\"
							+ inFileName.substring(j + 1, inFileName.length())
							+ "_BH" + ".html";
					userFriendlyFileCD = errorFileName + "\\"
							+ inFileName.substring(j + 1, inFileName.length())
							+ "_CD" + ".html";
					uffNamingBHDD = inFileName.substring(j + 1, inFileName
							.length())
							+ "_BH" + ".html";
					uffNamingCD = inFileName.substring(j + 1, inFileName
							.length())
							+ "_CD" + ".html";
					errorFileName = errorFileName + "\\"
							+ inFileName.substring(j + 1, inFileName.length())
							+ ".err";
				}
				errorFileName = errorFileName.replace('\\', '/');
				hashFileName = hashFileName.replace('\\', '/');
				htmlErrorFileName = htmlErrorFileName.replace('\\', '/');
				if (!errorDir.isDirectory()) {
					System.out
							.println("Error/Upload and Statistics Report File Path does not exist or \n File Name specified alongwith path instead of only path.");
					// txtErrorFilePath.setText("");
				} else if (addrFile.exists() && addrFile.length() > 0L) {
					System.out.println("getBtnValidate            5");
					obj_FormValidator.readFile(inFileName, errorFileName, 0);
					if (obj_FormValidator.obj_StringBuffer.errorBufferString
							.length() == 0) {
						Hash hash = new Hash();
						boolean paperReturnFileGenerated = false;
						int paperReturnIndiFlag = 0;
						int ret=hash.startProcessing(inFileName,hashFileName, 0, 9, paperReturnIndiFlag, response);
					    boolean paperRetWarFile = false;
                        boolean electronicRetWarFile = false;
                        System.out.println("ret                     :"+ret);
						int hashError = 0;
						//  FVU.logObj.info("Return Hash Code = " + hashError);
						if (hashError == 0) {   
                            FileGenerator obj_FileGenerator = new FileGenerator();
                            String hashFileLocation = hashFileName.substring(0, hashFileName.lastIndexOf("/"));
                            obj_FileGenerator.generateStatisticFile(obj_FormValidator, statisticFileName, fileName, "2.126");
                            boolean reportGenerated = false;
                            ReportGenerator rep = new ReportGenerator();
                            long l1 = System.currentTimeMillis();
                            long l2 = System.currentTimeMillis();
                            if(obj_FormValidator.obj_StringBuffer.panStatFileOpened)
                            {
                                String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                                StringBuffer panStatTableString = new StringBuffer();
                                panStatTableString.append(obj_FormValidator.obj_StringBuffer.panStatBufferString.toString());
                                panStatTableString.append(obj_FileGenerator.createPANStatTableStructureEnd(fileName));
                                panStatTableString.append(obj_FileGenerator.createPANStatFileFooter());
                                obj_FileGenerator.writeToFile(panStatFileName, panStatTableString.toString(), 0);
                            } else
                            if(!obj_FormValidator.obj_StringBuffer.panStatFileOpened && obj_FormValidator.obj_StringBuffer.getPanCounter() > 0)
                            {
                                String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                                StringBuffer panStatTableString = new StringBuffer();
                                panStatTableString.append(obj_FormValidator.obj_StringBuffer.panStatBufferString.toString());
                                panStatTableString.append(obj_FileGenerator.createPANStatTableStructureEnd(fileName));
                                panStatTableString.append(obj_FileGenerator.createPANStatFileFooter());
                                obj_FileGenerator.writeToFile(panStatFileName, panStatTableString.toString(), 0);
                            }
                            if(obj_FormValidator.obj_StringBuffer.getWarningCount() > 0)
                            {
                                paperReturnFileGenerated = true;
                                if(obj_FormValidator.obj_StringBuffer.paperRetWarFileOpened)
                                {
                                    String paperRetunWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                                    boolean appedHtmlErrorFileHeader = false;
                                    boolean appedHtmlErrorFileFooter = true;
                                    paperRetWarFile = true;
                                    electronicRetWarFile = false;
                                    StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringPaperReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                    obj_FileGenerator.writeToFile(paperRetunWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.paperRetWarFileOpened);
                                } else
                                {
                                    String paperRetunWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                                    boolean appedHtmlErrorFileHeader = true;
                                    boolean appedHtmlErrorFileFooter = true;
                                    paperRetWarFile = true;
                                    electronicRetWarFile = false;
                                    StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringPaperReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                    obj_FileGenerator.writeToFile(paperRetunWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.paperRetWarFileOpened);
                                }
                            }
                            if(obj_FormValidator.obj_StringBuffer.getElecWarningCount() > 0)
                            {
                                paperReturnFileGenerated = true;
                                if(obj_FormValidator.obj_StringBuffer.electronicRetWarFileOpened)
                                {
                                    String electronicReturnWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                                    boolean appedHtmlErrorFileHeader = false;
                                    boolean appedHtmlErrorFileFooter = true;
                                    paperRetWarFile = false;
                                    electronicRetWarFile = true;
                                    StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringElectronicReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                    obj_FileGenerator.writeToFile(electronicReturnWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.electronicRetWarFileOpened);
                                } else
                                {
                                    String electronicReturnWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                                    boolean appedHtmlErrorFileHeader = true;
                                    boolean appedHtmlErrorFileFooter = true;
                                    paperRetWarFile = false;
                                    electronicRetWarFile = true;
                                    StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringElectronicReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                    obj_FileGenerator.writeToFile(electronicReturnWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.electronicRetWarFileOpened);
                                }
                            }
                            Calendar cal22 = new GregorianCalendar();
                           
                            if(obj_FormValidator.obj_StringBuffer.getPanCounter() > 0)
                            {
                                if(paperReturnFileGenerated)
                                {
                                    if(reportGenerated)
                                    {
                                        if(paperRetWarFile)
                                            System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                        else
                                        if(electronicRetWarFile)
                                            System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                                    } else
                                    if(paperRetWarFile)
                                        System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                    else
                                    if(electronicRetWarFile)
                                        System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                                } else
                                if(reportGenerated)
                                    System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,User Friendly Report and  PAN Statistic Report Generated at \r\n" + hashFileLocation);
                                else
                                    System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report and  PAN Statistic Report Generated at \r\n" + hashFileLocation);
                            } else
                            if(paperReturnFileGenerated)
                            {
                                if(reportGenerated)
                                {
                                    if(paperRetWarFile)
                                        System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                    else
                                    if(electronicRetWarFile)
                                        System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                                } else
                                if(paperRetWarFile)
                                    System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                else
                                if(electronicRetWarFile)
                                    System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                            } else
                            if(reportGenerated)
                                System.out.println("File Validation Successful.\r\nUpload File,User Friendly Report and Return Statistic Report Generated at \r\n" + hashFileLocation);
                            else
                                System.out.println("File Validation Successful.\r\nUpload File and Return Statistic Report Generated at \r\n" + hashFileLocation);
                        } else
                        if(hashError != 0)
                        {
                            FileGenerator obj_FileGenerator = new FileGenerator();
                            StringBuffer FH_obj_StringBuffer = new StringBuffer();
                            int lineNumber = hash.getRecordNumber();
                            if(hashError == 3)
                                FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1021" + tab + "FVU Version is either Incorrect or NULL\n");
                            else
                            if(hashError == 4)
                                FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1024" + tab + "SAM Version is either Incorrect or NULL.\n");
                            else
                            if(hashError == 5)
                                FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1025" + tab + "SCM Version is either Incorrect or NULL.\n");
                            else
                            if(hashError == 9)
                                FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1026" + tab + "Mismatch of FVU File Level HashCode.\n");
                            else
                            if(hashError == 10)
                                FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1027" + tab + "Mismatch of SAM File Level HashCode.\n");
                            else
                            if(hashError == 11)
                                FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1028" + tab + "Mismatch of SCM File Level HashCode.\n");
                            else
                                FH_obj_StringBuffer.append(lineNumber + tab + "NA" + tab + "NA" + tab + "NA" + tab + "NA" + tab + "T-FV-1022" + tab + "Errors Found during Hash Validation.\n");
                            obj_FileGenerator.writeToFile(errorFileName, FH_obj_StringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                            boolean appedHtmlErrorFileHeader = true;
                            boolean appedHtmlErrorFileFooter = true;
                            paperRetWarFile = false;
                            electronicRetWarFile = false;
                            StringBuffer htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(FH_obj_StringBuffer.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                            obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                            String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                            File panStatFile = new File(panStatFileName);
                            if(panStatFile.exists())
                                panStatFile.delete();
                            String paperRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                            File paperReturnWarFile = new File(paperRetWarFileName);
                            if(paperReturnWarFile.exists())
                                paperReturnWarFile.delete();
                            String electronicRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                            File electronicReturnWarFile = new File(electronicRetWarFileName);
                            if(electronicReturnWarFile.exists())
                                electronicReturnWarFile.delete();
                            Calendar cal22 = new GregorianCalendar();
                            errorInFile=true;
                            System.out.println("Errors Found during Validation.\r\nError File and HTML Error File Generated\r\nOpen " + htmlErrorFileName + " for details");
                        }
                    } else
                    {
                        FileGenerator obj_FileGenerator = new FileGenerator();
                        StringBuffer htmlErrorFileStringBuffer = null;
                        if(obj_FormValidator.obj_StringBuffer.fileOpened)
                        {
                            boolean appedHtmlErrorFileHeader = false;
                            boolean appedHtmlErrorFileFooter = true;
                            boolean paperRetWarFile = false;
                            boolean electronicRetWarFile = false;
                            htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.errorBufferString.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                            obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                        } else
                        {
                            boolean appedHtmlErrorFileHeader = true;
                            boolean appedHtmlErrorFileFooter = true;
                            boolean paperRetWarFile = false;
                            boolean electronicRetWarFile = false;
                            htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.errorBufferString.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                            obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                        }
                        String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                        File panStatFile = new File(panStatFileName);
                        if(panStatFile.exists())
                            panStatFile.delete();
                        String paperRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                        File paperReturnWarFile = new File(paperRetWarFileName);
                        if(paperReturnWarFile.exists())
                            paperReturnWarFile.delete();
                        String electronicRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                        File electronicReturnWarFile = new File(electronicRetWarFileName);
                        if(electronicReturnWarFile.exists())
                            electronicReturnWarFile.delete();
                        Calendar cal22 = new GregorianCalendar();
                        errorInFile=true;
                        System.out.println("Errors Found during Validation.\r\nError File and HTML Error File Generated\r\nOpen " + htmlErrorFileName + " for details");
                    }
                    txtInputFileName.setText("");
                    txtErrorFilePath.setText("");
                    btnValidate.setEnabled(false);
                } else
                {
                    FileGenerator obj_FileGenerator = new FileGenerator();
                    StringBuffer FH_obj_StringBuffer = new StringBuffer();
                    FH_obj_StringBuffer.append("-" + tab + "NA" + tab + "NA" + tab + "NA" + tab + "NA" + tab + "T-FV-1020" + tab + "File does not exist or Empty File\n");
                    obj_FileGenerator.writeToFile(errorFileName, FH_obj_StringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                    boolean appedHtmlErrorFileHeader = true;
                    boolean appedHtmlErrorFileFooter = true;
                    boolean paperRetWarFile = false;
                    boolean electronicRetWarFile = false;
                    StringBuffer htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(FH_obj_StringBuffer.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                    obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                    String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                    File panStatFile = new File(panStatFileName);
                    if(panStatFile.exists())
                        panStatFile.delete();
                    errorInFile=true;
                    System.out.println("Errors Found during Validation.\r\nError File and HTML Error File Generated\r\nOpen " + htmlErrorFileName + " for details");
                    txtInputFileName.setText("");
                    txtErrorFilePath.setText("");
                    btnValidate.setEnabled(false);
                }
            } else
            {
                if(errorCode == 1 || errorCode == 8)
                    System.out.println("Input File Name with Path or \n Error/Upload and Statistics Report File Path not chosen");
                else
                if(errorCode == 2)
                    System.out.println("Invalid Input File Name. Length of Input File Name cannot be more than 12 characters (including extension).");
                else
                if(errorCode == 3)
                    System.out.println("Invalid Input File Name");
                else
                if(errorCode == 5)
                    System.out.println("Invalid File Name.\n  :  /  ?  >  <  *  \"  |  \\  _ ^ or Space, not allowed in File Name.");
                txtInputFileName.setText("");
                txtErrorFilePath.setText("");
                btnValidate.setEnabled(false);
            }
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			try {
				oStream = response.getOutputStream();
			} catch (Exception e) {
				System.out.println("--------------");
				e.printStackTrace();
			} //end of catch
			try {
				System.out.println("hashFileName  :"+hashFileName);
				System.out.println("htmlErrorFileName  :"+htmlErrorFileName);
				
				System.out.println("-errorInFile-------------"+errorInFile);
				if (errorInFile) {
					response.setHeader("Content-type","text/html");
					response.setContentType("text/html");
					response.setHeader("Content-disposition",
							"inline;filename=\"" +"error"+ quaYear+".html" + "\"");
					fsStream = new FileInputStream(htmlErrorFileName);
				}

				else {
					response.setHeader("Content-type", "application/msword");
					response.setHeader("Content-disposition",
							"inline;filename=\"" + quaYear+".fvu" + "\"");
					fsStream = new FileInputStream(hashFileName);
				}
				int iChar;
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				// TODO: handle exception
				
			}
			finally{
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
				
			}
			
		} catch (Exception o) {
			o.printStackTrace();
			System.out.println("Exception in  actionPerformed method  : " + o);
		}

	}*/
	
	public void getBtnValidate(HttpServletResponse response, String path, String quaYear, ETds eTds) 
    {
       
                    try
                    {
                    	boolean errorInFile=false;
                        String tab = "^";
                        FormValidator obj_FormValidator = new FormValidator();
                        FileStatistics.setCinUploadFlag(0);
                        FileStatistics.setCinFileName("");
                        String poolName=String.valueOf(session.getAttribute("session_pool"));
            			String inFileName =path+ "/datafiles/" + poolName+"/incometax/write.txt";
            		//	String inFileName = "C:/write.txt";
            			String errorFileName = path+ "/datafiles/" + poolName+"/incometax";
                       // String inFileName = txtInputFileName.getText().trim();
                       // String errorFileName = txtErrorFilePath.getText().trim();
            			String cinFileName = "";
            			if(eTds.getUploadChallanFileName().equals("") ||eTds.getUploadChallanFileName().equals(null)
            					||eTds.getUploadChallanFileName().equals("null")){
            				cinFileName = "";
            			} //end of if
            			else{
            				String challanFile = context.getRealPath("/")+ "pages/oo/"+session.getAttribute("session_pool")+"/pay/" +eTds.getUploadChallanFileName();
            				cinFileName = challanFile;
            			} //end of else
            			System.out.println("cinFileName==="+cinFileName);
                        int errorCodeCIN = 0;
                        String hashFileName = null;
                        String statisticFileName = null;
                        String htmlErrorFileName = null;
                        String userFriendlyFileBHDD = null;
                        String userFriendlyFileCD = null;
                        String uffNamingBHDD = null;
                        String uffNamingCD = null;
                        File addrFile = new File(inFileName);
                        String fileName = addrFile.getName();
                        int errorCode = checkFileFolderName(fileName, "I");
                        if(!fileName.endsWith(".txt") && !fileName.endsWith(".fvu"))
                            errorCode = 6;
                        if(errorCode == 0)
                            if(cinFileName.length() != 0)
                            {
                                FileStatistics.setCinUploadFlag(1);
                                File cinFile = new File(cinFileName);
                                String fileNameCIN = cinFile.getName();
                                FileStatistics.setCinFileName(cinFileName);
                                errorCodeCIN = checkFileFolderName(fileNameCIN, "C");
                                if(!fileNameCIN.endsWith(".csi"))
                                    errorCodeCIN = 12;
                                FileStatistics.setCinErrorCode(errorCodeCIN);
                                if(FileStatistics.getCinErrorCode() != 0)
                                {
                                    if(errorCodeCIN == 1 || errorCodeCIN == 8)
                                         System.out.println("CIN File Name with Path or \n Error/Upload and Statistics Report File Path not chosen");
                                    else
                                    if(errorCodeCIN == 2)
                                         System.out.println("Invalid CIN File Name. Length of CIN File Name cannot be more than 25 characters (including extension).");
                                    else
                                    if(errorCodeCIN == 3)
                                         System.out.println("Invalid CIN File Name");
                                    else
                                    if(errorCodeCIN == 5)
                                         System.out.println("Invalid CIN File Name.\n  :  /  ?  >  <  *  \"  |  \\  _ ^ or Space, not allowed in File Name.");
                                    else
                                    if(errorCodeCIN == 12)
                                         System.out.println("Extension of challan file should be csi.");
                                   /* txtInputFileName.setText("");
                                    txtErrorFilePath.setText("");
                                    txtCINFileName.setText("");
                                    btnValidate.setEnabled(false);*/
                                }
                            } else
                            {
                                FileStatistics.setCinUploadFlag(0);
                                FileStatistics.setCinFileName("");
                            }
                        File errorDir = new File(errorFileName);
                        if(errorFileName.length() == 0)
                            errorCode = 8;
                        if(errorCode == 0 && errorCodeCIN == 0)
                        {
                            int i;
                            for(i = inFileName.length() - 1; i > -1; i--)
                            {
                                char ch;
                                if((ch = inFileName.charAt(i)) == '.')
                                    break;
                            }

                            if(i > 0)
                            {
                                int j;
                                for(j = i; j > -1; j--)
                                    if(inFileName.charAt(j) == '/')
                                        break;

                                hashFileName = errorFileName + "/" + inFileName.substring(j + 1, i) + ".fvu";
                                htmlErrorFileName = errorFileName + "/" + inFileName.substring(j + 1, i) + "err.html";
                                statisticFileName = errorFileName + "/" + inFileName.substring(j + 1, i) + ".html";
                                userFriendlyFileBHDD = errorFileName + "/" + inFileName.substring(j + 1, i) + "_BH" + ".html";
                                userFriendlyFileCD = errorFileName + "/" + inFileName.substring(j + 1, i) + "_CD" + ".html";
                                uffNamingBHDD = inFileName.substring(j + 1, i) + "_BH" + ".html";
                                uffNamingCD = inFileName.substring(j + 1, i) + "_CD" + ".html";
                                errorFileName = errorFileName + "/" + inFileName.substring(j + 1, i) + ".err";
                            } else
                            {
                                int j;
                                for(j = inFileName.length() - 1; j > -1; j--)
                                    if(inFileName.charAt(j) == '/')
                                        break;

                                hashFileName = errorFileName + "/" + inFileName.substring(j + 1, inFileName.length()) + ".fvu";
                                htmlErrorFileName = errorFileName + "/" + inFileName.substring(j + 1, inFileName.length()) + "err.html";
                                statisticFileName = errorFileName + "/" + inFileName.substring(j + 1, inFileName.length()) + ".html";
                                userFriendlyFileBHDD = errorFileName + "/" + inFileName.substring(j + 1, inFileName.length()) + "_BH" + ".html";
                                userFriendlyFileCD = errorFileName + "/" + inFileName.substring(j + 1, inFileName.length()) + "_CD" + ".html";
                                uffNamingBHDD = inFileName.substring(j + 1, inFileName.length()) + "_BH" + ".html";
                                uffNamingCD = inFileName.substring(j + 1, inFileName.length()) + "_CD" + ".html";
                                errorFileName = errorFileName + "/" + inFileName.substring(j + 1, inFileName.length()) + ".err";
                            }
                            errorFileName = errorFileName.replace('\\', '/');
                            hashFileName = hashFileName.replace('\\', '/');
                            htmlErrorFileName = htmlErrorFileName.replace('\\', '/');
                            if(!errorDir.isDirectory())
                            {
                                 System.out.println("Error/Upload and Statistics Report File Path does not exist or \n File Name specified alongwith path instead of only path.");
                               // txtErrorFilePath.setText("");
                            } else
                            if(addrFile.exists() && addrFile.length() > 0L)
                            {
                                obj_FormValidator.readFile(inFileName, errorFileName, 0);
                                if(obj_FormValidator.obj_StringBuffer.errorBufferString.length() == 0)
                                {
                                    System.out.println("Field validation is successfull now starting hash validation");
                                    Hash hash = new Hash();
                                    boolean paperReturnFileGenerated = false;
                                    boolean paperRetWarFile = false;
                                    boolean electronicRetWarFile = false;
                                    int paperReturnIndiFlag = 0;
                                    int hashError = hash.startProcessing(inFileName, hashFileName, 0, 10, paperReturnIndiFlag,response);
                                    System.out.println("Return Hash Code = " + hashError);
                                    if(hashError == 0)
                                    {
                                        System.out.println("Hash Validation is sucessfull now started generating file");
                                        FileGenerator obj_FileGenerator = new FileGenerator();
                                        String hashFileLocation = hashFileName.substring(0, hashFileName.lastIndexOf("/"));
                                        obj_FileGenerator.generateStatisticFile(obj_FormValidator, statisticFileName, fileName, "2.128");
                                        System.out.println("Statistics file is created");
                                        boolean reportGenerated = false;
                                        ReportGenerator rep = new ReportGenerator();
                                        long l1 = System.currentTimeMillis();
                                        long l2 = System.currentTimeMillis();
                                        if(obj_FormValidator.obj_StringBuffer.panStatFileOpened)
                                        {
                                            String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                                            StringBuffer panStatTableString = new StringBuffer();
                                            panStatTableString.append(obj_FormValidator.obj_StringBuffer.panStatBufferString.toString());
                                            panStatTableString.append(obj_FileGenerator.createPANStatTableStructureEnd(fileName));
                                            panStatTableString.append(obj_FileGenerator.createPANStatFileFooter());
                                            obj_FileGenerator.writeToFile(panStatFileName, panStatTableString.toString(), 0, true);
                                        } else
                                        if(!obj_FormValidator.obj_StringBuffer.panStatFileOpened && obj_FormValidator.obj_StringBuffer.getPanCounter() > 0)
                                        {
                                            String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                                            StringBuffer panStatTableString = new StringBuffer();
                                            panStatTableString.append(obj_FormValidator.obj_StringBuffer.panStatBufferString.toString());
                                            panStatTableString.append(obj_FileGenerator.createPANStatTableStructureEnd(fileName));
                                            panStatTableString.append(obj_FileGenerator.createPANStatFileFooter());
                                            obj_FileGenerator.writeToFile(panStatFileName, panStatTableString.toString(), 0, false);
                                        }
                                        if(obj_FormValidator.obj_StringBuffer.getWarningCount() > 0)
                                        {
                                            paperReturnFileGenerated = true;
                                            if(obj_FormValidator.obj_StringBuffer.paperRetWarFileOpened)
                                            {
                                                String paperRetunWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                                                boolean appedHtmlErrorFileHeader = false;
                                                boolean appedHtmlErrorFileFooter = true;
                                                paperRetWarFile = true;
                                                electronicRetWarFile = false;
                                                StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringPaperReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                                obj_FileGenerator.writeToFile(paperRetunWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.paperRetWarFileOpened);
                                            } else
                                            {
                                                String paperRetunWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                                                boolean appedHtmlErrorFileHeader = true;
                                                boolean appedHtmlErrorFileFooter = true;
                                                paperRetWarFile = true;
                                                electronicRetWarFile = false;
                                                StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringPaperReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                                obj_FileGenerator.writeToFile(paperRetunWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.paperRetWarFileOpened);
                                            }
                                        }
                                        if(obj_FormValidator.obj_StringBuffer.getElecWarningCount() > 0)
                                        {
                                            paperReturnFileGenerated = true;
                                            if(obj_FormValidator.obj_StringBuffer.electronicRetWarFileOpened)
                                            {
                                                String electronicReturnWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                                                boolean appedHtmlErrorFileHeader = false;
                                                boolean appedHtmlErrorFileFooter = true;
                                                paperRetWarFile = false;
                                                electronicRetWarFile = true;
                                                StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringElectronicReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                                obj_FileGenerator.writeToFile(electronicReturnWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.electronicRetWarFileOpened);
                                            } else
                                            {
                                                String electronicReturnWarningFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                                                boolean appedHtmlErrorFileHeader = true;
                                                boolean appedHtmlErrorFileFooter = true;
                                                paperRetWarFile = false;
                                                electronicRetWarFile = true;
                                                StringBuffer htmlWarningFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.warningBufferStringElectronicReturn.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                                obj_FileGenerator.writeToFile(electronicReturnWarningFileName, htmlWarningFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.electronicRetWarFileOpened);
                                            }
                                        }
                                        Calendar cal22 = new GregorianCalendar();
                                        System.out.println("END TIME : " + cal22.getTime());
                                        if(obj_FormValidator.obj_StringBuffer.getPanCounter() > 0)
                                        {
                                            if(paperReturnFileGenerated)
                                            {
                                                if(reportGenerated)
                                                {
                                                    if(paperRetWarFile)
                                                         System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                                    else
                                                    if(electronicRetWarFile)
                                                         System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                                                } else
                                                if(paperRetWarFile)
                                                     System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                                else
                                                if(electronicRetWarFile)
                                                     System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                                            } else
                                            if(reportGenerated)
                                                 System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,User Friendly Report and  PAN Statistic Report Generated at \r\n" + hashFileLocation);
                                            else
                                                 System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report and  PAN Statistic Report Generated at \r\n" + hashFileLocation);
                                        } else
                                        if(paperReturnFileGenerated)
                                        {
                                            if(reportGenerated)
                                            {
                                                if(paperRetWarFile)
                                                     System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                                else
                                                if(electronicRetWarFile)
                                                     System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report,User Friendly Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                                            } else
                                            if(paperRetWarFile)
                                                 System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nPaper Statement Warning File Generated at \r\n" + hashFileLocation);
                                            else
                                            if(electronicRetWarFile)
                                                 System.out.println("File Validation Successful.\r\nUpload File,  Return Statistic Report,  PAN Statistic Report and \r\nElectronic Statement Warning File Generated at \r\n" + hashFileLocation);
                                        } else
                                        if(reportGenerated)
                                             System.out.println("File Validation Successful.\r\nUpload File,User Friendly Report and Return Statistic Report Generated at \r\n" + hashFileLocation);
                                        else
                                             System.out.println("File Validation Successful.\r\nUpload File and Return Statistic Report Generated at \r\n" + hashFileLocation);
                                    } else
                                    if(hashError != 0)
                                    {
                                        FileGenerator obj_FileGenerator = new FileGenerator();
                                        StringBuffer FH_obj_StringBuffer = new StringBuffer();
                                        int lineNumber = hash.getRecordNumber();
                                        if(hashError == 3)
                                            FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1021" + tab + "FVU Version is either Incorrect or NULL\n");
                                        else
                                        if(hashError == 4)
                                            FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1024" + tab + "SAM Version is either Incorrect or NULL.\n");
                                        else
                                        if(hashError == 5)
                                            FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1025" + tab + "SCM Version is either Incorrect or NULL.\n");
                                        else
                                        if(hashError == 9)
                                            FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1026" + tab + "Mismatch of FVU File Level HashCode.\n");
                                        else
                                        if(hashError == 10)
                                            FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1027" + tab + "Mismatch of SAM File Level HashCode.\n");
                                        else
                                        if(hashError == 11)
                                            FH_obj_StringBuffer.append("1" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1028" + tab + "Mismatch of SCM File Level HashCode.\n");
                                        else
                                        if(hashError == 13)
                                            FH_obj_StringBuffer.append("NA" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1029" + tab + "Invalid Challan Input file.\n");
                                        else
                                        if(hashError == 14)
                                            FH_obj_StringBuffer.append("NA" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1030" + tab + "CIN File Not Exists in required Path.\n");
                                        else
                                        if(hashError == 15)
                                            FH_obj_StringBuffer.append("NA" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1031" + tab + "Empty CIN File Uploaded.\n");
                                        else
                                        if(hashError == 16)
                                            FH_obj_StringBuffer.append("NA" + tab + "NA" + tab + "NA" + tab + tab + tab + "T-FV-1032" + tab + "Number Of challan in CIN File Header is not mathced with Number of challan present.\n");
                                        else
                                            FH_obj_StringBuffer.append(lineNumber + tab + "NA" + tab + "NA" + tab + "NA" + tab + "NA" + tab + "T-FV-1022" + tab + "Errors Found during Hash Validation.\n");
                                        obj_FileGenerator.writeToFile(errorFileName, FH_obj_StringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                                        boolean appedHtmlErrorFileHeader = true;
                                        boolean appedHtmlErrorFileFooter = true;
                                        paperRetWarFile = false;
                                        electronicRetWarFile = false;
                                        StringBuffer htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(FH_obj_StringBuffer.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                        obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                                        String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                                        File panStatFile = new File(panStatFileName);
                                        if(panStatFile.exists())
                                            panStatFile.delete();
                                        errorInFile=true;
                                        String paperRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                                        File paperReturnWarFile = new File(paperRetWarFileName);
                                        if(paperReturnWarFile.exists())
                                            paperReturnWarFile.delete();
                                        String electronicRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                                        File electronicReturnWarFile = new File(electronicRetWarFileName);
                                        if(electronicReturnWarFile.exists())
                                            electronicReturnWarFile.delete();
                                        Calendar cal22 = new GregorianCalendar();
                                        errorInFile=true;
                                        System.out.println("END TIME : " + cal22.getTime());
                                         System.out.println("Errors Found during Validation.\r\nError File and HTML Error File Generated\r\nOpen " + htmlErrorFileName + " for details");
                                    }
                                } else
                                {
                                    FileGenerator obj_FileGenerator = new FileGenerator();
                                    StringBuffer htmlErrorFileStringBuffer = null;
                                    if(obj_FormValidator.obj_StringBuffer.fileOpened)
                                    {
                                        boolean appedHtmlErrorFileHeader = false;
                                        boolean appedHtmlErrorFileFooter = true;
                                        boolean paperRetWarFile = false;
                                        boolean electronicRetWarFile = false;
                                        htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.errorBufferString.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                        obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                                    } else
                                    {
                                        boolean appedHtmlErrorFileHeader = true;
                                        boolean appedHtmlErrorFileFooter = true;
                                        boolean paperRetWarFile = false;
                                        boolean electronicRetWarFile = false;
                                        htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(obj_FormValidator.obj_StringBuffer.errorBufferString.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                        obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                                    }
                                    String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                                    File panStatFile = new File(panStatFileName);
                                    if(panStatFile.exists())
                                        panStatFile.delete();
                                    String paperRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Paper_Statement_Warning_File.html";
                                    File paperReturnWarFile = new File(paperRetWarFileName);
                                    if(paperReturnWarFile.exists())
                                        paperReturnWarFile.delete();
                                    String electronicRetWarFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_Electronic_Statement_Warning_File.html";
                                    File electronicReturnWarFile = new File(electronicRetWarFileName);
                                    if(electronicReturnWarFile.exists())
                                        electronicReturnWarFile.delete();
                                    Calendar cal22 = new GregorianCalendar();
                                    errorInFile=true;
                                    System.out.println("END TIME : " + cal22.getTime());
                                     System.out.println("Errors Found during Validation.\r\nError File and HTML Error File Generated\r\nOpen " + htmlErrorFileName + " for details");
                                }
                                /*txtInputFileName.setText("");
                                txtErrorFilePath.setText("");
                                if(cinFileName.length() != 0)
                                    txtCINFileName.setText("");
                                btnValidate.setEnabled(false);*/
                            } else
                            {
                                FileGenerator obj_FileGenerator = new FileGenerator();
                                StringBuffer FH_obj_StringBuffer = new StringBuffer();
                                FH_obj_StringBuffer.append("-" + tab + "NA" + tab + "NA" + tab + "NA" + tab + "NA" + tab + "T-FV-1020" + tab + "File does not exist or Empty File\n");
                                obj_FileGenerator.writeToFile(errorFileName, FH_obj_StringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                                boolean appedHtmlErrorFileHeader = true;
                                boolean appedHtmlErrorFileFooter = true;
                                boolean paperRetWarFile = false;
                                boolean electronicRetWarFile = false;
                                StringBuffer htmlErrorFileStringBuffer = obj_FileGenerator.generateHtmlErrorFile(FH_obj_StringBuffer.toString(), appedHtmlErrorFileHeader, appedHtmlErrorFileFooter, paperRetWarFile, fileName, electronicRetWarFile);
                                obj_FileGenerator.writeToFile(htmlErrorFileName, htmlErrorFileStringBuffer.toString(), 0, obj_FormValidator.obj_StringBuffer.fileOpened);
                                String panStatFileName = errorFileName.substring(0, errorFileName.length() - 4) + "_PAN_Statistics.html";
                                File panStatFile = new File(panStatFileName);
                                if(panStatFile.exists())
                                    panStatFile.delete();
                                 System.out.println("Errors Found during Validation.\r\nError File and HTML Error File Generated\r\nOpen " + htmlErrorFileName + " for details");
                                /*txtInputFileName.setText("");
                                txtErrorFilePath.setText("");
                                if(cinFileName.length() != 0)
                                    txtCINFileName.setText("");
                                btnValidate.setEnabled(false);*/
                            }
                        } else
                        {
                            if(errorCode == 1 || errorCode == 8)
                                 System.out.println("Input File Name with Path or \n Error/Upload and Statistics Report File Path not chosen");
                            else
                            if(errorCode == 2)
                                 System.out.println("Invalid Input File Name. Length of Input File Name cannot be more than 12 characters (including extension).");
                            else
                            if(errorCode == 3)
                                 System.out.println("Invalid Input File Name");
                            else
                            if(errorCode == 5)
                                 System.out.println("Invalid File Name.\n  :  /  ?  >  <  *  \"  |  \\  _ ^ or Space, not allowed in File Name.");
                            else
                            if(errorCode == 6)
                                 System.out.println("Invalid TDS/TCS input file name. Extension of the e-TDS/TCS file name should be txt/fvu.");
                            /*txtInputFileName.setText("");
                            txtErrorFilePath.setText("");
                            if(cinFileName.length() != 0)
                                txtCINFileName.setText("");
                            btnValidate.setEnabled(false);*/
                        }
                        
                        OutputStream oStream = null;
            			FileInputStream fsStream = null;
            			try {
            				oStream = response.getOutputStream();
            			} catch (Exception e) {
            				System.out.println("--------------");
            				e.printStackTrace();
            			} //end of catch
            			try {
            				System.out.println("hashFileName  :"+hashFileName);
            				System.out.println("htmlErrorFileName  :"+htmlErrorFileName);
            				
            				System.out.println("-errorInFile-------------"+errorInFile);
            				if (errorInFile) {
            					response.setHeader("Content-type","text/html");
            					response.setContentType("text/html");
            					response.setHeader("Content-disposition",
            							"inline;filename=\"" +"error"+ quaYear+".html" + "\"");
            					fsStream = new FileInputStream(htmlErrorFileName);
            				}

            				else {
            					response.setHeader("Content-type", "application/msword");
            					response.setHeader("Content-disposition",
            							"inline;filename=\"" + quaYear+".fvu" + "\"");
            					fsStream = new FileInputStream(hashFileName);
            				}
            				int iChar;
            				while ((iChar = fsStream.read()) != -1) {
            					oStream.write(iChar);
            				}
            			
            			} catch (FileNotFoundException e) {
            				e.printStackTrace();
            				// TODO: handle exception
            				
            			}
            			finally{
            				if (fsStream != null) {
            					fsStream.close();
            				}
            				if (oStream != null) {
            					oStream.flush();
            					oStream.close();
            				}
            				
            			}
                    }
                    catch(Exception o)
                    {
                    	System.out.println("Exception in  actionPerformed method  : " + o);
                    }
  
    }
	
	   int checkFileFolderName(String testName, String fileFlag)
	    {
	        if(testName.length() == 0)
	            return 1;
	        if(fileFlag.equals("C"))
	        {
	            if(testName.length() > 25)
	                return 2;
	        } else
	        if(testName.length() > 12)
	            return 2;
	        if(testName.trim().length() >= 5)
	        {
	            String subName = testName.substring(0, 4);
	            if(subName.equalsIgnoreCase("COM1.") || subName.equalsIgnoreCase("COM2.") || subName.equalsIgnoreCase("COM3.") || subName.equalsIgnoreCase("COM4.") || subName.equalsIgnoreCase("COM5.") || subName.equalsIgnoreCase("COM6.") || subName.equalsIgnoreCase("COM7.") || subName.equalsIgnoreCase("COM8.") || subName.equalsIgnoreCase("COM9.") || subName.equalsIgnoreCase("LTP1.") || subName.equalsIgnoreCase("LTP2.") || subName.equalsIgnoreCase("LTP3.") || subName.equalsIgnoreCase("LTP4.") || subName.equalsIgnoreCase("LTP5.") || subName.equalsIgnoreCase("LTP6.") || subName.equalsIgnoreCase("LTP7.") || subName.equalsIgnoreCase("LTP8.") || subName.equalsIgnoreCase("LTP9."))
	                return 3;
	        }
	        if(testName.trim().length() >= 4 && (testName.substring(0, 3).equalsIgnoreCase("NUL.") || testName.substring(0, 3).equalsIgnoreCase("CON.")))
	            return 3;
	        for(int localI = 0; localI < testName.length(); localI++)
	            if(testName.charAt(localI) == '/' || testName.charAt(localI) == ':' || testName.charAt(localI) == '*' || testName.charAt(localI) == '?' || testName.charAt(localI) == '"' || testName.charAt(localI) == '<' || testName.charAt(localI) == '>' || testName.charAt(localI) == '|' || testName.charAt(localI) == '\\' || testName.charAt(localI) == ' ' || testName.charAt(localI) == '_' || testName.charAt(localI) == '^')
	                return 5;

	        int alphaDigit = 3;
	        for(int localY = 0; localY < testName.length(); localY++)
	        {
	            if(!Character.isLetterOrDigit(testName.charAt(localY)))
	                continue;
	            alphaDigit = 0;
	            break;
	        }

	        return alphaDigit != 3 ? 0 : 3;
	    }


	}
