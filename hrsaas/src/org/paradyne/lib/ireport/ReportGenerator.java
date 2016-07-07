/* Prakash Shetkar Nov 20, 2009 */

package org.paradyne.lib.ireport;

import com.lowagie.text.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportGenerator {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReportGenerator.class);

	public static int PAGE_BREAK = 1;
	String repname, fileType, fName = "", fileName;
	ByteArrayOutputStream bout;
	int tdsSequence = 0;
	PDFGenerator pdfg;
	XLSGenerate xlsg;
	HtmlGenerator htmlg;
	TXTGenerator txtg;
	Document doc;
	HashMap<String, Object> dataMap = new HashMap<String, Object>(15);

	public ReportGenerator(ReportDataSet rds) {
		repname = rds.getReportName();
		fileName = rds.getFileName().replaceAll(" ", "");
		fileType = rds.getReportType();
		fName = fileName;

		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg = new PDFGenerator();
				bout = new java.io.ByteArrayOutputStream();
				doc = pdfg.openDoc(rds, bout);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg = new TXTGenerator();
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg = new XLSGenerate(rds);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg = new TXTGenerator();
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg = new HtmlGenerator();
			}
		} catch(Exception e) {
			logger.error("error in ReportGenerator :" + e);
		}
	}

	public void addProperty(int i) {
		dataMap.put(String.valueOf(tdsSequence), "PAGE_BREAK");
		tdsSequence++;
	}

	public void addTableToDoc(HashMap<String, Object> map) {
		dataMap.put(String.valueOf(tdsSequence), map);
		tdsSequence++;
	}

	public void addTableToDoc(TableDataSet tds) {
		dataMap.put(String.valueOf(tdsSequence), tds);
		tdsSequence++;
	}

	public void createFooter(Object obj) {
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg.genFooter(obj);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg.genFooter(obj);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg.genFooter(obj);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg.genFooter(obj);
			}
		} catch(Exception e) {
			logger.error("Exception in createFooter:" + e);
		}
	}

	public void createHeader(Object obj) {
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg.genHeader(obj);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg.genHeader(obj);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg.genHeader(obj);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg.genHeader(obj);
			}
		} catch(Exception e) {
			logger.error("Exception in createHeader:" + e);
		}
	}
	
	/**
	 * Kiosk specific create report function
	 */
	public void createReportForKiosk(HttpServletResponse response) {
		try {
			if (fileType.equalsIgnoreCase("Pdf")) {
				doc.close();

				response.setContentType("application/pdf");
				fName += ".pdf";
				 
				response.getOutputStream().write(bout.toByteArray());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void createReport(HttpServletResponse response) {
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				doc.close();

				response.setContentType("application/pdf");
				fName += ".pdf";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				response.getOutputStream().write(bout.toByteArray());
			} else if(fileType.equalsIgnoreCase("Html")) {
				response.setContentType("application/html");
				fName += ".html";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				response.getOutputStream().write(String.valueOf(htmlg.createTableStrBuffer).getBytes());
			} else if(fileType.equalsIgnoreCase("Txt")) {
				response.setContentType("application/msword");
				fName += ".doc";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				response.getOutputStream().write(txtg.elementTag.getBytes());
			} else if(fileType.equalsIgnoreCase("Xls")) {
				response.setContentType("application/vnd.ms-excel");
				fName += ".xls";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				response.getOutputStream().write(String.valueOf(xlsg.strExcelReport).getBytes());
			}
		} catch(Exception e) {
			logger.error("Exception in createReport:" + e);
		}
	}

	public String getCurrentDate(String format) {
		// format = "dd-MM-yyyy"; format = "dd-MMMM-yyyy";
		Date date = new Date();
		SimpleDateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat(format);
		} catch(Exception e) {
			logger.error("Exception in getCurrentDate:" + e);
			format = "dd-MM-yyyy";
		}
		return formatter.format(date);
	}

	public String getCurrentTime(String format) {
		// format = "HH:mm:ss"; format = "HH:mm";
		Date date = new Date();
		SimpleDateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat(format);
		} catch(Exception e) {
			logger.error("Exception in getCurrentTime:" + e);
			format = "HH:mm:ss";
		}
		return formatter.format(date);
	}

	//public Object getImage(String imagePath, HttpServletRequest request) {
	public Object getImage(String imagePath, HttpServletRequest request) {
		if(fileType.equalsIgnoreCase("Pdf")) {
			try {
				return Image.getInstance(imagePath);

			} catch(FileNotFoundException e) {
				logger.error("FileNotFoundException ------------ error in getting image " + e);
			} catch(BadElementException e1) {
				logger.error("BadElementException ------------ error in getting image " + e1);
			} catch(MalformedURLException e1) {
				logger.error("MalformedURLException ------------ error in getting image " + e1);
			} catch(IOException e1) {
				logger.error("IOException ------------ error in getting image " + e1);
			} catch(Exception e) {
				logger.error("Exception ------------ error in getting image " + e);
			}
		} else if(fileType.equalsIgnoreCase("Txt")) {
			String imageTag = "<w:pict><v:shape id='_x0000_i1025' type='#_x0000_t75' style='width:56.25pt;height:56.25pt'>" +
			"<v:imagedata src='" + imagePath + "' /> " + "</v:shape> " + "</w:pict> ";
			return imageTag;
		} else if(fileType.equalsIgnoreCase("Html")) {
			StringBuffer image = new StringBuffer();
		//	String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/pims/pages/common/embedImage.jsp?path=" + imagePath + "'";
		//	image.append("<img src='" + url + "' />");
			return image;
		} else if(fileType.equalsIgnoreCase("Xls")) {
			StringBuffer image = new StringBuffer();
			image.append("IMG");
			return image;
		}
		return null;
	}
	
	public Object getImage(String imagePath) {
		if(fileType.equalsIgnoreCase("Pdf")) {
			try {
				return Image.getInstance(imagePath);

			} catch(FileNotFoundException e) {
				logger.error("FileNotFoundException ------------ error in getting image " + e);
			} catch(BadElementException e1) {
				logger.error("BadElementException ------------ error in getting image " + e1);
			} catch(MalformedURLException e1) {
				logger.error("MalformedURLException ------------ error in getting image " + e1);
			} catch(IOException e1) {
				logger.error("IOException ------------ error in getting image " + e1);
			} catch(Exception e) {
				logger.error("Exception ------------ error in getting image " + e);
			}
		} else if(fileType.equalsIgnoreCase("Txt")) {
			String imageTag = "<w:pict><v:shape id='_x0000_i1025' type='#_x0000_t75' style='width:56.25pt;height:56.25pt'>" +
			"<v:imagedata src='" + imagePath + "' /> " + "</v:shape> " + "</w:pict> ";
			return imageTag;
		} else if(fileType.equalsIgnoreCase("Html")) {
			StringBuffer image = new StringBuffer();
		//	String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/pims/pages/common/embedImage.jsp?path=" + imagePath + "'";
		//	image.append("<img src='" + url + "' />");
			return image;
		} else if(fileType.equalsIgnoreCase("Xls")) {
			StringBuffer image = new StringBuffer();
			image.append("IMG");
			return image;
		}
		return null;
	}

	public HashMap<String, Object> joinTableDataSet(Object tdsOne, Object tdsTwo, boolean horizontalFlag, int firstWidth) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(horizontalFlag) {
			map.put("Direction", "H");
			map.put("width", firstWidth);
			map.put("1", tdsOne);
			map.put("2", tdsTwo);
		} else {
			map.put("Direction", "V");
			map.put("1", tdsOne);
			map.put("2", tdsTwo);
		}
		return map;
	}

	public void process() {
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg.process(dataMap, doc);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg.process(dataMap);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg.process(dataMap);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg.process(dataMap);
			}
		} catch(Exception e) {
			logger.error("Exception in process:" + e);
		}
	}
}