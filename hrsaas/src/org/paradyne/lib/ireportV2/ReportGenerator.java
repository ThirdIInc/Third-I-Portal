/* Prakash Shetkar Nov 20, 2009 */

package org.paradyne.lib.ireportV2;

import com.itextpdf.text.*;
//import com.lowagie.text.Font;
import com.itextpdf.text.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class ReportGenerator {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReportGenerator.class);

	public static int PAGE_BREAK = 1;
	String repname, fileType, fName = "", fileName,reportPath;
	public static boolean SPLIT_LATE = false;
	ByteArrayOutputStream bout;
	int tdsSequence = 0;
	PDFGenerator pdfg;
	XLSGenerate xlsg;
	HtmlGenerator htmlg;
	TXTGenerator txtg;
	Document doc;
	
	ReportDataSet rdsGlob=null;
	HashMap<String, Object> dataMap = new HashMap<String, Object>(15);

	public ReportGenerator(ReportDataSet rdsDoc, HttpSession session, ServletContext context,HttpServletRequest request) {
		repname = rdsDoc.getReportName();
		fileName = rdsDoc.getFileName().replaceAll(" ", "");
		fileType = rdsDoc.getReportType();
		fName = fileName;
		rdsDoc=setReportProprties(rdsDoc,session,context,request);
		//Object headerObj=getReportHeader(rds,context,session);
		//Object footerObj=getReportFooter(rds,context,session);
		
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg = new PDFGenerator();
				//pdfg = new PDFGenerator();
				bout = new java.io.ByteArrayOutputStream();
				doc = pdfg.openDoc(rdsDoc, bout);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg = new TXTGenerator(rdsDoc);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg = new XLSGenerate(rdsDoc);
				//htmlg = new HtmlGenerator(rds);
			} else if(fileType.equalsIgnoreCase("doc")) {
				txtg = new TXTGenerator(rdsDoc);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg = new HtmlGenerator(rdsDoc);
			}
		} catch(Exception e) {
			logger.error("error in ReportGenerator :" + e);
		}
		this.rdsGlob=rdsDoc;
	}
	public ReportGenerator(ReportDataSet rdsDoc, HttpSession session, ServletContext context) {
		repname = rdsDoc.getReportName();
		fileName = rdsDoc.getFileName().replaceAll(" ", "");
		fileType = rdsDoc.getReportType();
		fName = fileName;
		//rds=setReportProprties(rds,session,context,request);
		
		//Object headerObj=getReportHeader(rds,context,session);
		//Object footerObj=getReportFooter(rds,context,session);
		
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg = new PDFGenerator();
				//pdfg = new PDFGenerator();
				bout = new java.io.ByteArrayOutputStream();
				doc = pdfg.openDoc(rdsDoc, bout);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg = new TXTGenerator(rdsDoc);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg = new XLSGenerate(rdsDoc);
				//htmlg = new HtmlGenerator(rds);
			} else if(fileType.equalsIgnoreCase("doc")) {
				txtg = new TXTGenerator(rdsDoc);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg = new HtmlGenerator(rdsDoc);
			}
		} catch(Exception e) {
			logger.error("error in ReportGenerator :" + e);
		}
		this.rdsGlob=rdsDoc;
	}
	
	/*private Object getReportFooter(ReportDataSet rds,ServletContext context,HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}*/

	private ReportDataSet setReportProprties(ReportDataSet rds,HttpSession session,ServletContext context,HttpServletRequest request) {
		ModelBase model=new ModelBase();
		String query="SELECT REP_MARGIN_LEFT, REP_MARGIN_RIGHT, REP_MARGIN_TOP, REP_MARGIN_BOTTOM, DECODE(REP_IS_LOGO,'Y','true','false'), REP_LOGO_ALIGN, DECODE(REP_IS_COMPANY_NAME,'Y','true','false'),"
			+" REP_COMPANY_NAME_FONT_STYLE, REP_COMPANY_NAME_FONT_SIZE, REP_COMPANY_NAME_FONT_FACE, REP_COMPANY_NAME_ALIGN,"
			+" decode(REP_IS_COMPANY_ADRESS,'Y','true','false'), REP_COMPANY_ADRESS_FONT_STYLE, REP_COMPANY_ADRESS_FONT_SIZE, REP_COMPANY_ADRESS_FONT_FACE, REP_HEADER_FONT_STYLE,"
			+" REP_HEADER_SIZE, REP_HEADER_FONT_FACE, REP_TABLE_HEADER_FONT_STYLE, REP_TABLE_HEADER_SIZE, REP_TABLE_HEADER_FONT_FACE, REP_TABLE_BODY_FONT_STYLE,"
			+" REP_TABLE_BODY_SIZE, REP_TABLE_BODY_FONT_FACE, nvl(REP_HEADER_IMG,''),nvl(REP_FOOTER_IMG,''),NVL(REP_FOOTER_TEXT,'')	FROM HRMS_REPORT_CONF";
		
		model.initiate(context, session);
		Object[][]dataObj=model.getSqlModel().getSingleResult(query);
		
		if(dataObj!=null && dataObj.length>0){
			rds.setMarginLeft(Float.parseFloat(String.valueOf(dataObj[0][0])));
			rds.setMarginRight(Float.parseFloat(String.valueOf(dataObj[0][1])));
			rds.setMarginBottom(Float.parseFloat(String.valueOf(dataObj[0][3])));
			rds.setMarginTop(Float.parseFloat(String.valueOf(dataObj[0][2])));
			
			rds.setLogoRequired(Boolean.parseBoolean(String.valueOf(dataObj[0][4])));
			rds.setLogoAlign(String.valueOf(dataObj[0][5]).charAt(0));
			
			rds.setCompanyNameRequired(Boolean.parseBoolean(String.valueOf(dataObj[0][6])));
			rds.setCompanyNameFontStyle(Integer.parseInt(String.valueOf(dataObj[0][7])));
			rds.setCompanyNameFontSize(Integer.parseInt(String.valueOf(dataObj[0][8])));
			rds.setCompanyNameFontFace(String.valueOf(dataObj[0][9]));			
			rds.setCompanyNameAlign(String.valueOf(dataObj[0][10]).charAt(0));
			
			rds.setCompanyAddressRequired(Boolean.parseBoolean(String.valueOf(dataObj[0][11])));
			rds.setCompanyAddressFontStyle(Integer.parseInt(String.valueOf(dataObj[0][12])));
			rds.setCompanyAddressFontSize(Integer.parseInt(String.valueOf(dataObj[0][13])));
			rds.setCompanyAddressFontFace(String.valueOf(dataObj[0][14]));

			rds.setReportHeaderFontStyle(Integer.parseInt(String.valueOf(dataObj[0][15])));
			rds.setReportHeaderFontSize(Integer.parseInt(String.valueOf(dataObj[0][16])));
			rds.setReportHeaderFontFace(String.valueOf(dataObj[0][17]));
			
			rds.setTableHeaderFontStyle(Integer.parseInt(String.valueOf(dataObj[0][18])));
			rds.setTableHeaderFontSize(Integer.parseInt(String.valueOf(dataObj[0][19])));
			rds.setTableHeaderFontFace(String.valueOf(dataObj[0][20]));
			
			rds.setTableBodyFontStyle(Integer.parseInt(String.valueOf(dataObj[0][21])));
			rds.setTableBodyFontSize(Integer.parseInt(String.valueOf(dataObj[0][22])));
			rds.setTableBodyFontFace(String.valueOf(dataObj[0][23]));
			
			if(!Utility.checkNull(String.valueOf(dataObj[0][24])).equals(""))
			rds.setHeaderImagePath("pages/Logo/"+ session.getAttribute("session_pool") + "/"
					+ String.valueOf(dataObj[0][24]));
			
			if(!Utility.checkNull(String.valueOf(dataObj[0][25])).equals(""))
			rds.setFooterImagePath("pages/Logo/"+ session.getAttribute("session_pool") + "/"
					+ String.valueOf(dataObj[0][25]));
			
			rds.setFooterText(Utility.checkNull(String.valueOf(dataObj[0][26])));
			
			rds.setImageRealPath(context.getRealPath("/"));
			rds.setImageHttpPath("http://" + request.getServerName() + ":"
					+ request.getServerPort()
					+ request.getContextPath()+"/");
			
			
			//rds.setShowPageNo(false);
		}
		String cmpnyLogoQuery = "SELECT COMPANY_CODE,NVL(COMPANY_LOGO,''),COMPANY_NAME,nvl(COMPANY_ADDRESS,''),'','',"
					+ " NVL(COMPANY_TELPHONE,''),NVL(COMPANY_WEBSITE,'') from HRMS_COMPANY";

		Object[][] logoObj = model.getSqlModel().getSingleResult(
				cmpnyLogoQuery);
		String logoPath = "";
		if (logoObj != null && logoObj.length > 0) {
			
		}
		if (rds.isLogoRequired()) {
			
			
			if (logoObj != null && logoObj.length > 0) {
				if (!String.valueOf(logoObj[0][1]).equals("")) {
					// String clientUser = (String)
					// session.getAttribute("session_pool");
					String filePath = "pages/Logo/"
							+ session.getAttribute("session_pool") + "/"
							+ String.valueOf(logoObj[0][1]);
					// rds.setLogoPath(filePath);
					logoPath = filePath;

				}
			}
			
		}
		String companyName=String.valueOf(logoObj[0][2]);
		String companyAddress="";//String.valueOf(logoObj[0][3]);
		companyAddress = "\n"+Utility.checkNull(String.valueOf(logoObj[0][3]))+ "\n"
				+ Utility.checkNull(String.valueOf(logoObj[0][4]))
				+ Utility.checkNull(String.valueOf(logoObj[0][5]));
		Object[][] divObj=null;
		if(rds.isCompanyNameRequired()){
			if(rds.getDivisionId()!=-1){
				String divQuery = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
					+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,''),NVL(DIV_LOGO,' ')  FROM HRMS_DIVISION where DIV_ID="
					+ rds.getDivisionId();

				 divObj = model.getSqlModel().getSingleResult(
						 divQuery);
				if(divObj!=null && divObj.length>0){
					if(!Utility.checkNull(String.valueOf(divObj[0][7])).equals("")){
						logoPath ="pages/Logo/"
							+ session.getAttribute("session_pool") + "/"
							+ String.valueOf(divObj[0][7]);
					}
					companyName =String.valueOf(divObj[0][0]);
					
				}
			}
		}
		if(rds.isCompanyAddressRequired()){
			if(divObj!=null && divObj.length>0){
				companyAddress ="\n"+Utility.checkNull(String.valueOf(divObj[0][2]))+"\n"+
				Utility.checkNull(String.valueOf(divObj[0][3]))+"\n"+
				Utility.checkNull(String.valueOf(divObj[0][4]))+"\n";
			}
		}else{
			companyAddress="";
		}
		String footerQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, EMP_RANK,RANK_NAME "
			+ " FROM HRMS_EMP_OFFC "
			+ "INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
			+ "WHERE EMP_ID=" + rds.getUserEmpId();

		
		// for credit emp type
		Object[][] generatedByObj = model.getSqlModel().getSingleResult(
			footerQuery);
		Date date = new Date();
		if(generatedByObj!=null && generatedByObj.length>0){
			rds.setGeneratedByText("Generated By : "+String.valueOf(generatedByObj[0][1])+" on "+date.toString());
		}
		if(!logoPath.equals(""))
		rds.setLogoPath(logoPath);
		rds.setCompanyName(companyName);
		rds.setCompanyAddress(companyAddress);
		return rds;
	}

	public ReportGenerator(ReportDataSet rdsDoc,String path,HttpSession session,ServletContext context,HttpServletRequest request) {
		repname = rdsDoc.getReportName();
		fileName = rdsDoc.getFileName().replaceAll(" ", "");
		fileType = rdsDoc.getReportType();
		fName = fileName;
		rdsDoc=setReportProprties(rdsDoc,session,context,request);
		checkFile(path);
		path +=rdsDoc.getFileName()+"."+fileType;
		reportPath=path;
		this.rdsGlob=rdsDoc;
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg = new PDFGenerator();
				bout = new java.io.ByteArrayOutputStream();
				doc = pdfg.openDoc(rdsGlob, path);
			} else if(fileType.equalsIgnoreCase("doc")) {
				txtg = new TXTGenerator(rdsGlob);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg = new XLSGenerate(rdsGlob);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg = new TXTGenerator(rdsGlob);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg = new HtmlGenerator(rdsGlob);
			}
		} catch(Exception e) {
			logger.error("error in ReportGenerator :" + e);
		}
	}
	public ReportGenerator(ReportDataSet rdsDoc,String path,HttpSession session,ServletContext context) {
		repname = rdsDoc.getReportName();
		fileName = rdsDoc.getFileName().replaceAll(" ", "");
		fileType = rdsDoc.getReportType();
		fName = fileName;
		//rds=setReportProprties(rds,session,context,request);
		checkFile(path);
		path +=rdsDoc.getFileName()+"."+fileType;
		reportPath=path;
		this.rdsGlob=rdsDoc;
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg = new PDFGenerator();
				bout = new java.io.ByteArrayOutputStream();
				doc = pdfg.openDoc(rdsGlob, path);
			} else if(fileType.equalsIgnoreCase("doc")) {
				txtg = new TXTGenerator(rdsGlob);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg = new XLSGenerate(rdsGlob);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg = new TXTGenerator(rdsGlob);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg = new HtmlGenerator(rdsGlob);
			}
		} catch(Exception e) {
			logger.error("error in ReportGenerator :" + e);
		}
	}
	public void checkFile(String path){
		File file = new File(path);
		if(!file.exists()){
			new File(path).mkdirs();
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

	public void addTableToDoc(org.paradyne.lib.ireportV2.TableDataSet tds) {
		dataMap.put(String.valueOf(tdsSequence), tds);
		tdsSequence++;
	}
	public void addLine(Vector lineVect) {
		lineVect=new Vector();
		lineVect.add(new BaseColor(93,93,93));
		dataMap.put(String.valueOf(tdsSequence), lineVect);
		tdsSequence++;
	}
	public void seperatorLine() {
		Vector lineVect=new Vector();
		lineVect.add(new BaseColor(93,93,93));
		dataMap.put(String.valueOf(tdsSequence), lineVect);
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
			}else if(fileType.equalsIgnoreCase("Doc")) {
				txtg.genFooter(obj);
			}else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg.genFooter(obj);
			}
		} catch(Exception e) {
			logger.error("Exception in createFooter:" + e);
		}
	}

	/*public void createHeader() {
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				pdfg.genHeader(obj);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg.genHeader(obj);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg.genHeader(obj);
			} else if(fileType.equalsIgnoreCase("Doc")) {
				txtg.genHeader(obj);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg.genHeader(obj);
			}
		} catch(Exception e) {
			logger.error("Exception in createHeader:" + e);
		}
	}*/
	

	private int getAlignment(char companyNameAlign) {
		switch (companyNameAlign) {
		case 'C':
			return 1;
		case 'L':
			return 0;
		case 'R':
			return 2;
		default:
			break;
		}
		return 0;
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
				response.setContentType("text/html");
				fName += ".html";
				response.setHeader("Content-Disposition", "inline; filename=\"" + fName + "\"");
				response.getOutputStream().write(String.valueOf(htmlg.createTableStrBuffer).getBytes());
				//changes for preview
				/* PrintWriter out = response.getWriter();
				out.write(String.valueOf(htmlg.createTableStrBuffer));
				out.close();*/
				
			} else if(fileType.equalsIgnoreCase("Txt")) {
				response.setContentType("application/msword");
				fName += ".doc";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				response.getOutputStream().write(txtg.elementTag.getBytes());
			} else if(fileType.equalsIgnoreCase("Doc")) {
				response.setContentType("application/msword");
				fName += ".doc";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				response.getOutputStream().write(txtg.elementTag.getBytes());
			} else if(fileType.equalsIgnoreCase("Xls")) {
				response.setContentType("application/vnd.ms-excel");
				//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				fName += ".xls";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				//response.getOutputStream().write(String.valueOf(htmlg.createTableStrBuffer).getBytes());
				response.getOutputStream().write(String.valueOf(xlsg.strExcelReport).getBytes());
			}
		} catch(Exception e) {
			logger.error("Exception in createReport:" + e);
		}
	}
	public void saveReport(HttpServletResponse response) {
		try {
			if(fileType.equalsIgnoreCase("Pdf")) {
				doc.close();

				response.setContentType("application/pdf");
				fName += ".pdf";
			} else if(fileType.equalsIgnoreCase("Html")) {
				//response.setContentType("application/html");
				fName += ".html";
				FileOutputStream out = new FileOutputStream(reportPath);
				out.write(String.valueOf(htmlg.createTableStrBuffer).getBytes());
				out.close();
			} else if(fileType.equalsIgnoreCase("Txt")) {
				//response.setContentType("application/msword");
				fName += ".doc";
				FileOutputStream out = new FileOutputStream(reportPath);
				out.write(String.valueOf(txtg.elementTag).getBytes());
				out.close();
			}else if(fileType.equalsIgnoreCase("Doc")) {
				//response.setContentType("application/msword");
				fName += ".doc";
				FileOutputStream out = new FileOutputStream(reportPath);
				out.write(String.valueOf(txtg.elementTag).getBytes());
				out.close();
			}
			else if(fileType.equalsIgnoreCase("Xls")) {
				//response.setContentType("application/vnd.ms-excel");
				fName += ".xls";
				 FileOutputStream out = new FileOutputStream(reportPath);
				 out.write(String.valueOf(xlsg.strExcelReport).getBytes());
			     out.close();

			}
			response.reset();
		} catch(Exception e) {
			logger.error("Exception in createReport:" + e);
			e.printStackTrace();
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
			
		}else if(fileType.equalsIgnoreCase("Doc")) {
			String imageTag = "<w:pict><v:shape id='_x0000_i1025' type='#_x0000_t75' style='width:56.25pt;height:56.25pt'>" +
			"<v:imagedata src='" + imagePath + "' /> " + "</v:shape> " + "</w:pict> ";
			return imageTag;
		} 
		else if(fileType.equalsIgnoreCase("Html")) {
			StringBuffer image = new StringBuffer();
			String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/pims/pages/common/embedImage.jsp?path=" + imagePath + "'";
			image.append("<img src='" + url + "' />");
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
		}else if(fileType.equalsIgnoreCase("Doc")) {
			String imageTag = "<w:pict><v:shape id='_x0000_i1025' type='#_x0000_t75' style='width:56.25pt;height:56.25pt'>" +
			"<v:imagedata src='" + imagePath + "' /> " + "</v:shape> " + "</w:pict> ";
			return imageTag;
		} 
		else if(fileType.equalsIgnoreCase("Html")) {
			StringBuffer image = new StringBuffer();
		//	String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/pims/pages/common/embedImage.jsp?path=" + imagePath + "'";
		//	image.append("<img src='" + url + "' />");
			return image;
		} else if(fileType.equalsIgnoreCase("Xls")) {
			StringBuffer image = new StringBuffer();
			image.append("<table width='100%' border='0' cellspacing='0' cellpadding='1'>");
			image.append("<td width='50%' colspan='2'> <img src='"+rdsGlob.getImageHttpPath()+imagePath+"'></td>");
			image.append("</tr></table>");
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
				pdfg.process(dataMap, doc,SPLIT_LATE);
			} else if(fileType.equalsIgnoreCase("Xls")) {
				xlsg.process(dataMap);
			} else if(fileType.equalsIgnoreCase("Txt")) {
				txtg.process(dataMap);
			} else if(fileType.equalsIgnoreCase("Doc")) {
				txtg.process(dataMap);
			} else if(fileType.equalsIgnoreCase("Html")) {
				htmlg.process(dataMap);
			}
		} catch(Exception e) {
			logger.error("Exception in process:" + e);
		}
	}
}