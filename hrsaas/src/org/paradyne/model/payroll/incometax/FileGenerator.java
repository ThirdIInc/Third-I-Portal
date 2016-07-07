// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 30/09/2009 1:31:20 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FileGenerator.java

package org.paradyne.model.payroll.incometax;

import com.tin.tds.*;
import com.tin.tds.bean.FileStatistics;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.struts.action.recruitment.TestResultAction;

// Referenced classes of package com.tin.tds.util:
//            Log

public class FileGenerator
    implements FileValidatorInterface
{

    public FileGenerator()
    {
        cal = new GregorianCalendar();
        tab = "^";
        StructurallyinvalidPAN = false;
    }
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileGenerator.class);
    public void writeToFile(String ERROR_FILE, String text, int errRespFile)
        throws IOException
    {
        try
        {
            cal = new GregorianCalendar();
            if(text.trim().length() != 0)
            {
                logger.info(" Entering writeToFile() Method " + cal.getTime());
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ERROR_FILE, false)));
                out.println(text);
                out.close();
                cal = new GregorianCalendar();
                logger.info("Exiting writeToFile() Method " + cal.getTime());
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in writeToFile() method : " + e.toString());
            logger.info("Exception while writing into file - calling errorFileNotGenerated() method");
            logger.info("---Returned from errorFileNotGenerated() -- ");
        }
    }

    public void writeToFile(String fileName, String text, int errRespFile, boolean fileOpened)
        throws IOException
    {
        try
        {
            cal = new GregorianCalendar();
            if(text.trim().length() != 0)
            {
                PrintWriter out = null;
                if(fileOpened)
                    out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                else
                    out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
                out.println(text);
                out.close();
                cal = new GregorianCalendar();
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in writeError/RSPToFile method : " + e.toString());
            logger.info("Exception while writing into file - calling errorFileNotGenerated() method");
            logger.info("---Returned from errorFileNotGenerated() -- ");
        }
    }

    public void writeStatisticFile(String fileName, StringBuffer textBufferString, FileStatistics fStatistics, boolean fileOpenFlag, boolean printPanReport)
        throws IOException
    {
        try
        {
            cal = new GregorianCalendar();
            String text = textBufferString.toString();
            textBufferString = new StringBuffer();
            if(text.trim().length() != 0)
            {
                logger.info(" Entering writeStatisticFile() Method " + cal.getTime());
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, fileOpenFlag)));
                out.println(text);
                if(!printPanReport)
                {
                    out.close();
                    out.flush();
                    cal = new GregorianCalendar();
                    logger.info(" Exiting writeStatisticFile() Method " + cal.getTime());
                    return;
                }
                logger.info(" Exiting writeStatisticFile() Method " + cal.getTime());
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in writeStatisticFile() method : " + e.toString());
        }
    }

    public StringBuffer createProvisionalReceipt(FileStatistics pReceipt, StringBuffer toReturn)
    {
        boolean isTanChange = false;
        try
        {
            toReturn.append("<HTML> <HEAD><TITLE></TITLE></HEAD>");
            toReturn.append("<BODY>");
            if(pReceipt.getForm_No().equals("27EQ"))
                toReturn.append("<H3 ALIGN=CENTER> TCS - Provisional Receipt - Batch Number " + pReceipt.getBatch_No() + "</H3>");
            else
                toReturn.append("<H3 ALIGN=CENTER> TDS - Provisional Receipt - Batch Number " + pReceipt.getBatch_No() + "</H3>");
            toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'><TR><TD>");
            toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            toReturn.append("<TD WIDTH=150 ALIGN=RIGHT VALIGN=TOP TEXTCOLOR=\"#F4CE85\"><B>Receipt Number</B></TD>");
            if(pReceipt.getForm_No().equals("27EQ"))
            {
                if(pReceipt.getTransaction_Type().equals("C1") && !pReceipt.getTAN().equals(pReceipt.getLastTAN()))
                {
                    isTanChange = true;
                    toReturn.append("<TD WIDTH=425 ALIGN=LEFT VALIGN=TOP><B> Name of Collector</B></TD>");
                    toReturn.append("<TD WIDTH=425 ALIGN=LEFT VALIGN=TOP><B> New Name of Collector</B></TD>");
                } else
                {
                    toReturn.append("<TD WIDTH=850 ALIGN=LEFT VALIGN=TOP><B> Name of Collector</B></TD>");
                }
            } else
            if(pReceipt.getTransaction_Type().equals("C1") && !pReceipt.getTAN().equals(pReceipt.getLastTAN()))
            {
                isTanChange = true;
                toReturn.append("<TD WIDTH=425 ALIGN=LEFT VALIGN=TOP><B> Name of Deductor</B></TD>");
                toReturn.append("<TD WIDTH=425 ALIGN=LEFT VALIGN=TOP><B> New Name of Deductor</B></TD>");
            } else
            {
                toReturn.append("<TD WIDTH=850 ALIGN=LEFT VALIGN=TOP><B> Name of Deductor</B></TD>");
            }
            toReturn.append("</TR>");
            toReturn.append("<TR>");
            toReturn.append("<TD ALIGN=RIGHT VALIGN=TOP ><FONT COLOR=\"RED\"> <B> " + pReceipt.getReceipt_Number() + "</B></FONT></TD>");
            if(pReceipt.getTransaction_Type().equals("C1") && !pReceipt.getTAN().equals(pReceipt.getLastTAN()))
            {
                isTanChange = true;
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP> " + pReceipt.getName_of_Last_Deductor() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP> " + pReceipt.getName_of_Deductor() + " </TD>");
            } else
            {
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP> " + pReceipt.getName_of_Deductor() + " </TD>");
            }
            toReturn.append("</TR>");
            toReturn.append("</TABLE>");
            toReturn.append("<BR>");
            toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            toReturn.append("<TD WIDTH=150 ALIGN=LEFT VALIGN=TOP><B>Date</B></TD>");
            if(pReceipt.getTransaction_Type().equals("C1") && !pReceipt.getTAN().equals(pReceipt.getLastTAN()))
            {
                isTanChange = true;
                toReturn.append("<TD WIDTH=125 ALIGN=LEFT VALIGN=TOP><B> TAN</B></TD>");
                toReturn.append("<TD WIDTH=125 ALIGN=LEFT VALIGN=TOP><B> New TAN*</B></TD>");
            } else
            {
                toReturn.append("<TD WIDTH=250 ALIGN=LEFT VALIGN=TOP><B> TAN</B></TD>");
            }
            toReturn.append("<TD WIDTH=100 ALIGN=LEFT VALIGN=TOP ><B> AO Code</B></TD>");
            toReturn.append("<TD WIDTH=100 ALIGN=LEFT VALIGN=TOP><B> Form No.</B></TD>");
            toReturn.append("<TD WIDTH=100 ALIGN=LEFT VALIGN=TOP><B> Upload Type</B></TD>");
            toReturn.append("<TD WIDTH=150 ALIGN=LEFT VALIGN=TOP><B> Transaction Type </B></TD>");
            toReturn.append("<TD WIDTH=150 ALIGN=LEFT VALIGN=TOP><B> Financial Year and Periodicity</B></TD>");
            if(pReceipt.getTransaction_Type().equals("C1") || pReceipt.getTransaction_Type().equals("Y") || pReceipt.getTransaction_Type().equals("Y1"))
                toReturn.append("<TD WIDTH=150 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
            toReturn.append("</TR>");
            toReturn.append("<TR>");
            toReturn.append("<TD ALIGN=LEFT VALIGN=TOP> " + setDateFormat(pReceipt.getDate()) + "</TD>");
            if(pReceipt.getTransaction_Type().equals("C1") && !pReceipt.getTAN().equals(pReceipt.getLastTAN()))
            {
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP><B> " + pReceipt.getLastTAN() + "</B></TD>");
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP><B> " + pReceipt.getTAN() + "</B></TD>");
            } else
            {
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP><B> " + pReceipt.getTAN() + "</B></TD>");
            }
            toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > " + pReceipt.getAO_Code() + "</TD>");
            toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > " + pReceipt.getForm_No() + "</TD>");
            if(pReceipt.getUpload_Type().equals("R"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP >Regular (R)</TD>");
            else
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP >Correction (C)</TD>");
            if(pReceipt.getUpload_Type().equals("R"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP >NA</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("C1"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > C1-Correction in deductor's(collector's) details and/or statement related details</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("C9"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > C9-Correction in challan details (addition of challan/s)</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("C2"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > C2-Correction in deductor's(collector's) details and/or statement related and/or challan details</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("C3"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > C3-Correction in deductor's(collector's) details and/or statement related particulars and/or challan/deductee details</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("C4"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > C4-Correction in salary details of deductees</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("C5"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > C5-Correction in PAN of deductees/parties</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("X"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > X-Challan Cancellation</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("Y"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > Y-Statement Cancellation</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("Z"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > Z-Salary detail replacement</TD>");
            else
            if(pReceipt.getTransaction_Type().equals("Y1"))
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP > Y1-Statement Cancellation</TD>");
            try
            {
                String token = pReceipt.getFinancial_Year();
                toReturn.append("<TD ALIGN=LEFT VALIGN=TOP >" + token.substring(0, 4) + "-" + token.substring(4, token.length()) + "  " + pReceipt.getPeriodicity() + "</TD>");
            }
            catch(Exception e)
            {
                toReturn.append("<TD></TD>");
                logger.error("Exception in FileGenerator.java createProvisionalReceipt() Financial year is not in specified format  : " + e.toString());
            }
            if(pReceipt.getTransaction_Type().equals("C1") || pReceipt.getTransaction_Type().equals("Y") || pReceipt.getTransaction_Type().equals("Y1"))
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getUpload_Fee() + "</TD>");
            toReturn.append("</TR>");
            toReturn.append("</TABLE>");
            toReturn.append("<BR>");
            if(pReceipt.getTransaction_Type().equals("C1") || pReceipt.getTransaction_Type().equals("Y") || pReceipt.getTransaction_Type().equals("Y1"))
            {
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=1000>");
                toReturn.append(" </B><BR>");
                toReturn.append("Service Tax Registration No.:<B> AAACN2082NST001 - ST/MUM/DIV-III/BAS/2847 </B>");
                toReturn.append("<BR>");
                toReturn.append("<B>National Securities Depository Limited (e-TDS intermediary)</B>");
                toReturn.append("<BR>");
                if(isTanChange)
                {
                    toReturn.append("<B>");
                    toReturn.append("* TAN correction is subjected to approval from Income Tax Department");
                    toReturn.append("<BR><BR>");
                    toReturn.append("</B>");
                }
                toReturn.append("<B>");
                toReturn.append("<I>This is a computer generated Provisional Receipt, hence signature not required.");
                toReturn.append("</I>");
                toReturn.append("</B>");
                toReturn.append("</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
            } else
            if(pReceipt.getTransaction_Type().equals("C2") || pReceipt.getTransaction_Type().equals("X"))
            {
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=350 ALIGN=RIGHT VALIGN=TOP><B> No. of Challans/Transfer Vouchers</B></CENTER></TD>");
                toReturn.append("<TD WIDTH=400 ALIGN=RIGHT VALIGN=TOP><B> Total Challan/Transfer Voucher Amount (Rs.)</B></CENTER></TD>");
                toReturn.append("<TD WIDTH=250 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_challans() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Challan_Amount() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getUpload_Fee() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
                toReturn.append("<BR>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=1000>");
                toReturn.append(" </B><BR>");
                toReturn.append("Service Tax Registration No.:<B> AAACN2082NST001 - ST/MUM/DIV-III/BAS/2847 </B>");
                toReturn.append("<BR>");
                toReturn.append("<B>National Securities Depository Limited (e-TDS intermediary)</B>");
                toReturn.append("<BR>");
                if(isTanChange)
                {
                    toReturn.append("<B>");
                    toReturn.append("* TAN correction is subjected to approval from Income Tax Department");
                    toReturn.append("<BR><BR>");
                    toReturn.append("</B>");
                }
                toReturn.append("<B>");
                toReturn.append("<I>This is a computer generated Provisional Receipt, hence signature not required.");
                toReturn.append("</I>");
                toReturn.append("</B>");
                toReturn.append("</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
            } else
            if(pReceipt.getTransaction_Type().equals("C3"))
            {
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=250 ALIGN=RIGHT VALIGN=TOP><B> No. of Challans/Transfer Vouchers</B></CENTER></TD>");
                toReturn.append("<TD WIDTH=250 ALIGN=RIGHT VALIGN=TOP><B> Total Challan/Transfer Voucher Amount (Rs.)</B></CENTER></TD>");
                if(pReceipt.getForm_No().equals("27EQ"))
                {
                    toReturn.append("<TD WIDTH=200 ALIGN=RIGHT VALIGN=TOP><B> No. of Party Records</B></TD>");
                    toReturn.append("<TD WIDTH=200 ALIGN=RIGHT VALIGN=TOP><B> Total Value of Purchase (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=100 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                } else
                {
                    toReturn.append("<TD WIDTH=250 ALIGN=RIGHT VALIGN=TOP><B> No. of Deductee Records</B></TD>");
                    toReturn.append("<TD WIDTH=250 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                }
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_challans() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Challan_Amount() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_Deductee_Records() + "</TD>");
                if(pReceipt.getForm_No().equals("27EQ"))
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Value_of_Purchase() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getUpload_Fee() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
                toReturn.append("<BR>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                if(pReceipt.getTransaction_Type().equals("C3"))
                    toReturn.append("<TD valign=top width=250 ><B> Particulars </B></TD>");
                if(pReceipt.getForm_No().equals("27EQ"))
                {
                    toReturn.append("<TD valign=top width=250 style='text-align:right'><B> Amount of Collection / Debited (Rs.)</B></TD>");
                    toReturn.append("<TD valign=top width=250 style='text-align:right'><B> Total Income Tax Collected at Source (Rs.)</B></TD>");
                } else
                {
                    toReturn.append("<TD valign=top width=250 style='text-align:right'><B> Amount of Payment / Credit (Rs.)</B></TD>");
                    toReturn.append("<TD valign=top width=250 style='text-align:right'><B> Total Income Tax Deducted at Source (Rs.)</B></TD>");
                }
                toReturn.append("<TD valign=top width=250 style='text-align:right'><B> Total Tax Deposited as per Deductee Annexure (Rs.)</B></TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ><B>Added (a)</B></TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Amount_paid_credited_Add_Mode() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Tax_Deducted_Add_Mode() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Amount_deposited_Add_Mode() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ><B>Updated (b)</B></TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Amount_paid_credited_Updt_Mode() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Tax_Deducted_Updt_Mode() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Amount_deposited_Updt_Mode() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ><B>Deleted (c)</B></TD>");
                toReturn.append("<TD  ALIGN=RIGHT >NA</TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Tax_Deducted_Del_Mode() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT >" + pReceipt.getTotal_Amount_deposited_Del_Mode() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ><B>Total (a + b + c)</B></TD>");
                double val1 = 0.0D;
                double val2 = 0.0D;
                double val3 = 0.0D;
                String outputValue = null;
                if(!pReceipt.getTotal_Amount_paid_credited_Add_Mode().equals("-"))
                    val1 = Double.parseDouble(pReceipt.getTotal_Amount_paid_credited_Add_Mode().trim());
                if(!pReceipt.getTotal_Amount_paid_credited_Updt_Mode().equals("-"))
                    val2 = Double.parseDouble(pReceipt.getTotal_Amount_paid_credited_Updt_Mode().trim());
                val3 = val1 + val2;
                val3 = (double)Math.round(val3 * 100D) / 100D;
                FieldValidation fvObj = new FieldValidation();
                outputValue = fvObj.changeNumberFormat(val3);
                toReturn.append("<TD  ALIGN=RIGHT >" + outputValue + "</TD>");
                val1 = 0.0D;
                val2 = 0.0D;
                val3 = 0.0D;
                double val4 = 0.0D;
                if(!pReceipt.getTotal_Tax_Deducted_Add_Mode().equals("-"))
                    val1 = Double.parseDouble(pReceipt.getTotal_Tax_Deducted_Add_Mode().trim());
                if(!pReceipt.getTotal_Tax_Deducted_Updt_Mode().equals("-"))
                    val2 = Double.parseDouble(pReceipt.getTotal_Tax_Deducted_Updt_Mode().trim());
                if(!pReceipt.getTotal_Tax_Deducted_Del_Mode().equals("-"))
                    val3 = Double.parseDouble(pReceipt.getTotal_Tax_Deducted_Del_Mode().trim());
                val4 = val1 + val2 + val3;
                val4 = (double)Math.round(val4 * 100D) / 100D;
                outputValue = null;
                outputValue = fvObj.changeNumberFormat(val4);
                toReturn.append("<TD  ALIGN=RIGHT >" + outputValue + "</TD>");
                val1 = 0.0D;
                val2 = 0.0D;
                val3 = 0.0D;
                val4 = 0.0D;
                if(!pReceipt.getTotal_Amount_deposited_Add_Mode().equals("-"))
                    val1 = Double.parseDouble(pReceipt.getTotal_Amount_deposited_Add_Mode().trim());
                if(!pReceipt.getTotal_Amount_deposited_Updt_Mode().equals("-"))
                    val2 = Double.parseDouble(pReceipt.getTotal_Amount_deposited_Updt_Mode().trim());
                if(!pReceipt.getTotal_Amount_deposited_Del_Mode().equals("-"))
                    val3 = Double.parseDouble(pReceipt.getTotal_Amount_deposited_Del_Mode().trim());
                val4 = val1 + val2 + val3;
                val4 = (double)Math.round(val4 * 100D) / 100D;
                outputValue = null;
                outputValue = fvObj.changeNumberFormat(val4);
                toReturn.append("<TD  ALIGN=RIGHT >" + outputValue + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
                toReturn.append("<BR>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=1000>");
                toReturn.append("<BR>");
                toReturn.append("Deficiency (ies): ");
                toReturn.append("<BR>");
                toReturn.append("1) <B>" + pReceipt.getNo_of_PAN_Applied() + " case(s)</B> where deductee(s) has applied for <B>'Permanent Account Number'</B> <BR>");
                toReturn.append("2) <B>" + pReceipt.getNo_of_PAN_NotAvailabble() + " case(s)</B> where <B>'Permanent Account Number'</B> is not available for deductee(s) <BR>");
                toReturn.append("3) <B>" + pReceipt.getNo_of_PAN_Invalid() + " case(s)</B> where <B>'Permanent Account Number'</B> of deductee(s) is incorrect <BR>");
                toReturn.append("<BR>");
                toReturn.append("Please remove the deficiency(ies) within <B>7 days</B> by filling a revised return.");
                toReturn.append("<BR>");
                toReturn.append("<BR>");
                toReturn.append("<B> Last Deductee (LD) Detail Record in Challan(s) : </B>");
                if(!pReceipt.getNo_of_LD().equals(""))
                {
                    StringTokenizer str = new StringTokenizer(pReceipt.getNo_of_LD(), "&");
                    int no_of_token = str.countTokens();
                    for(int i = 0; i < no_of_token; i++)
                        if(i < no_of_token - 1)
                            toReturn.append(str.nextToken() + ", ");
                        else
                            toReturn.append(str.nextToken());

                    toReturn.append(" (eg. LD1=2 means \"Last Deductee detail record no. of Challan 1 is 2\")");
                    toReturn.append(" <BR>");
                } else
                {
                    toReturn.append(" - <BR>");
                }
                toReturn.append(" </B><BR>");
                toReturn.append("Service Tax Registration No.:<B> AAACN2082NST001 - ST/MUM/DIV-III/BAS/2847 </B>");
                toReturn.append("<BR>");
                toReturn.append("<B>National Securities Depository Limited (e-TDS intermediary)</B>");
                toReturn.append("<BR>");
                if(isTanChange)
                {
                    toReturn.append("<B>");
                    toReturn.append("* TAN correction is subjected to approval from Income Tax Department");
                    toReturn.append("<BR><BR>");
                    toReturn.append("</B>");
                }
                toReturn.append("<B>");
                toReturn.append("<I>This is a computer generated Provisional Receipt, hence signature not required.");
                toReturn.append("</I>");
                toReturn.append("</B>");
                toReturn.append("</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
            } else
            if(pReceipt.getTransaction_Type().equals("C4") || pReceipt.getTransaction_Type().equals("Z"))
            {
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=450 ALIGN=RIGHT VALIGN=TOP><B> No. of Salary Detail Records</B></TD>");
                toReturn.append("<TD WIDTH=450 ALIGN=RIGHT VALIGN=TOP><B> Batch Total of - Gross Total Income as per Salary Detail (Rs.)</B></TD>");
                toReturn.append("<TD WIDTH=100 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_Salary_Detail() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Income_As_Per_SD() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getUpload_Fee() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=1000>");
                toReturn.append("<BR>");
                toReturn.append("Deficiency (ies): ");
                toReturn.append("<BR>");
                toReturn.append("1) <B>" + pReceipt.getNo_of_PAN_Applied() + " case(s)</B> where deductee(s) has applied for <B>'Permanent Account Number'</B> <BR>");
                toReturn.append("2) <B>" + pReceipt.getNo_of_PAN_NotAvailabble() + " case(s)</B> where <B>'Permanent Account Number'</B> is not available for deductee(s) <BR>");
                toReturn.append("3) <B>" + pReceipt.getNo_of_PAN_Invalid() + " case(s)</B> where <B>'Permanent Account Number'</B> of deductee(s) is incorrect <BR>");
                toReturn.append("<BR>");
                toReturn.append("Please remove the deficiency(ies) within <B>7 days</B> by filling a revised return.");
                toReturn.append("<BR>");
                toReturn.append("<BR>");
                if(!pReceipt.getNo_of_LS().equals(""))
                    toReturn.append("<B> Last Salary (LS) Details Record Number in Batch :  </B>  " + pReceipt.getNo_of_LS() + "<BR>");
                else
                    toReturn.append("<B> Last Salary (LS) Details Record Number in Batch :  </B> - <BR>");
                toReturn.append(" </B><BR>");
                toReturn.append("Service Tax Registration No.:<B> AAACN2082NST001 - ST/MUM/DIV-III/BAS/2847 </B>");
                toReturn.append("<BR>");
                toReturn.append("<B>National Securities Depository Limited (e-TDS intermediary)</B>");
                toReturn.append("<BR>");
                if(isTanChange)
                {
                    toReturn.append("<B>");
                    toReturn.append("* TAN correction is subjected to approval from Income Tax Department");
                    toReturn.append("<BR><BR>");
                    toReturn.append("</B>");
                }
                toReturn.append("<B>");
                toReturn.append("<I>This is a computer generated Provisional Receipt, hence signature not required.");
                toReturn.append("</I>");
                toReturn.append("</B>");
                toReturn.append("</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
            } else
            if(pReceipt.getTransaction_Type().equals("C5"))
            {
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                if(pReceipt.getForm_No().equals("24Q"))
                {
                    toReturn.append("<TD WIDTH=250 ALIGN=RIGHT VALIGN=TOP><B> No. of Challans/Transfer Vouchers</B></CENTER></TD>");
                    toReturn.append("<TD WIDTH=300 ALIGN=RIGHT VALIGN=TOP><B> No. of Deductee Records</B></TD>");
                    toReturn.append("<TD WIDTH=250 ALIGN=RIGHT VALIGN=TOP><B> No. of Salary Detail Records</B></TD>");
                } else
                {
                    toReturn.append("<TD WIDTH=400 ALIGN=RIGHT VALIGN=TOP><B> No. of Challans/Transfer Vouchers</B></CENTER></TD>");
                    toReturn.append("<TD WIDTH=400 ALIGN=RIGHT VALIGN=TOP><B> No. of Deductee Records</B></TD>");
                }
                toReturn.append("<TD WIDTH=200 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_challans() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_DD_C5() + "</TD>");
                if(pReceipt.getForm_No().equals("24Q"))
                {
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_SD_C5() + "</TD>");
                    logger.info("No_of_Salary_Detail : " + pReceipt.getNo_SD_C5());
                }
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getUpload_Fee() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
                toReturn.append("<BR>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=1000>");
                toReturn.append(" </B><BR>");
                toReturn.append("Service Tax Registration No.:<B> AAACN2082NST001 - ST/MUM/DIV-III/BAS/2847 </B>");
                toReturn.append("<BR>");
                toReturn.append("<B>National Securities Depository Limited (e-TDS intermediary)</B>");
                toReturn.append("<BR>");
                toReturn.append("<B><I>This is a computer generated Provisional Receipt, hence signature not required.</I></B>");
                toReturn.append("</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
            } else
            {
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                if(!pReceipt.getTransaction_Type().equals("C4") && !pReceipt.getTransaction_Type().equals("Z"))
                {
                    toReturn.append("<TD WIDTH=125 ALIGN=RIGHT VALIGN=TOP><B> No. of Challans/Transfer Vouchers</B></CENTER></TD>");
                    toReturn.append("<TD WIDTH=125 ALIGN=RIGHT VALIGN=TOP><B> Total Challan/Transfer Voucher Amount (Rs.)</B></CENTER></TD>");
                }
                if(pReceipt.getForm_No().equals("24Q"))
                {
                    toReturn.append("<TD WIDTH=100 ALIGN=RIGHT VALIGN=TOP><B> No. of Deductee Records</B></TD>");
                    toReturn.append("<TD WIDTH=125 ALIGN=RIGHT VALIGN=TOP><B> Amount of Payment / Credit (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=125 ALIGN=RIGHT VALIGN=TOP><B> Total Income Tax Deducted at Source (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=125 ALIGN=RIGHT VALIGN=TOP><B> Total Tax Deposited as per Deductee Annexure (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=75 ALIGN=RIGHT VALIGN=TOP><B> No. of Salary Detail Records</B></TD>");
                    toReturn.append("<TD WIDTH=150 ALIGN=RIGHT VALIGN=TOP><B> Batch Total of - Gross Total Income as per Salary Detail (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=50 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                } else
                if(pReceipt.getForm_No().equals("27EQ"))
                {
                    toReturn.append("<TD WIDTH=100 ALIGN=RIGHT VALIGN=TOP><B> No. of Party Records</B></TD>");
                    toReturn.append("<TD WIDTH=150 ALIGN=RIGHT VALIGN=TOP><B> Amount Received / Debited (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=150 ALIGN=RIGHT VALIGN=TOP><B> Total Income Tax Collected at Source (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=150 ALIGN=RIGHT VALIGN=TOP><B> Total Tax Deposited as per Deductee Annexure (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=125 ALIGN=RIGHT VALIGN=TOP><B> Total Value of Purchase (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=75 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                } else
                {
                    toReturn.append("<TD WIDTH=100 ALIGN=RIGHT VALIGN=TOP><B> No. of Deductee Records</B></TD>");
                    toReturn.append("<TD WIDTH=125 ALIGN=RIGHT VALIGN=TOP><B> Amount of Payment / Credit (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=200 ALIGN=RIGHT VALIGN=TOP><B> Total Income Tax Deducted at Source (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=225 ALIGN=RIGHT VALIGN=TOP><B> Total Tax Deposited as per Deductee Annexure (Rs.)</B></TD>");
                    toReturn.append("<TD WIDTH=100 ALIGN=RIGHT VALIGN=TOP><B> Upload Fee (Rs.)</B></TD>");
                }
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_challans() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Challan_Amount() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_Deductee_Records() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Amount_paid_credited_Org_Mode() + "</TD>");
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Tax_Deducted_Org_Mode() + "</TD>");
                if(pReceipt.getForm_No().equals("24Q"))
                {
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Amount_deposited_Org_Mode() + "</TD>");
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getNo_of_Salary_Detail() + "</TD>");
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Income_As_Per_SD() + "</TD>");
                } else
                if(pReceipt.getForm_No().equals("27EQ"))
                {
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Amount_deposited_Org_Mode() + "</TD>");
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Value_of_Purchase() + "</TD>");
                } else
                {
                    toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getTotal_Amount_deposited_Org_Mode() + "</TD>");
                }
                toReturn.append("<TD  ALIGN=RIGHT VALIGN=TOP>" + pReceipt.getUpload_Fee() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                toReturn.append("<TD WIDTH=1000>");
                toReturn.append("<BR>");
                toReturn.append("Deficiency (ies): ");
                toReturn.append("<BR>");
                toReturn.append("1) <B>" + pReceipt.getNo_of_PAN_Applied() + " case(s)</B> where deductee(s) has applied for <B>'Permanent Account Number'</B> <BR>");
                toReturn.append("2) <B>" + pReceipt.getNo_of_PAN_NotAvailabble() + " case(s)</B> where <B>'Permanent Account Number'</B> is not available for deductee(s) <BR>");
                toReturn.append("3) <B>" + pReceipt.getNo_of_PAN_Invalid() + " case(s)</B> where <B>'Permanent Account Number'</B> of deductee(s) is incorrect <BR>");
                toReturn.append("<BR>");
                toReturn.append("Please remove the deficiency(ies) within <B>7 days</B> by filling a revised return.");
                toReturn.append("<BR>");
                toReturn.append("<BR>");
                toReturn.append("<B> Last Deductee (LD) Detail Record in Challan(s) : </B>");
                if(!pReceipt.getNo_of_LD().equals(""))
                {
                    StringTokenizer str = new StringTokenizer(pReceipt.getNo_of_LD(), "&");
                    int no_of_token = str.countTokens();
                    for(int i = 0; i < no_of_token; i++)
                        if(i < no_of_token - 1)
                            toReturn.append(str.nextToken() + ", ");
                        else
                            toReturn.append(str.nextToken());

                    toReturn.append(" (eg. LD1=2 means \"Last Deductee detail record no. of Challan 1 is 2\")");
                    toReturn.append(" <BR>");
                } else
                {
                    toReturn.append(" - <BR>");
                }
                if(pReceipt.getForm_No().equals("24Q"))
                    if(!pReceipt.getNo_of_LS().equals(""))
                        toReturn.append("<B> Last Salary (LS) Details Record Number in Batch :  </B>  " + pReceipt.getNo_of_LS() + "<BR>");
                    else
                        toReturn.append("<B> Last Salary (LS) Details Record Number in Batch :  </B> - <BR>");
                toReturn.append(" </B><BR>");
                toReturn.append("Service Tax Registration No.:<B> AAACN2082NST001 - ST/MUM/DIV-III/BAS/2847 </B>");
                toReturn.append("<BR>");
                toReturn.append("<B>National Securities Depository Limited (e-TDS intermediary)</B>");
                toReturn.append("<BR>");
                if(isTanChange)
                {
                    toReturn.append("<B>");
                    toReturn.append("* TAN correction is subjected to approval from Income Tax Department");
                    toReturn.append("<BR><BR>");
                    toReturn.append("</B>");
                }
                toReturn.append("<B><I>This is a computer generated Provisional Receipt, hence signature not required.</I></B>");
                toReturn.append("</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE>");
            }
            toReturn.append("</TD></TR></TABLE>");
            toReturn.append("<BR><BR><BR><BR>");
            toReturn.append("<span ><br clear=all style='page-break-before:always'></span>");
            toReturn.append("</BODY>");
            toReturn.append("</HTML>");
            logger.info("FileGenerator.java createProvisionalReceiptString() method is returning");
            return toReturn;
        }
        catch(Exception e)
        {
            logger.error("Exception in FileGenerator.java createProvisionalReceiptString() method : " + e.toString());
        }
        return null;
    }

    public FileStatistics getProvisionalDetail(String StringToParse, String Seperator)
    {
        FileStatistics fStatistics = new FileStatistics();
        logger.info("FileGenerator.java ParseString() method is called ");
        StringTokenizer batch = new StringTokenizer(StringToParse, Seperator);
        for(int counter = 0; batch.hasMoreTokens(); counter++)
        {
            String token = batch.nextToken();
            String name = token.substring(0, token.indexOf("="));
            String value = token.substring(token.indexOf("=") + 1, token.length());
            if(counter == 0)
                fStatistics.setBatch_No(value);
            else
            if(counter == 1)
                fStatistics.setNo_of_PAN_Applied(value);
            else
            if(counter == 2)
                fStatistics.setNo_of_PAN_Invalid(value);
            else
            if(counter == 3)
                fStatistics.setNo_of_PAN_NotAvailabble(value);
            else
            if(counter == 4)
                fStatistics.setLastTAN(value);
            else
            if(counter == 5)
                fStatistics.setTotal_Amount_paid_credited_Org_Mode(value);
            else
            if(counter == 6)
                fStatistics.setTotal_Amount_paid_credited_Updt_Mode(value);
            else
            if(counter == 7)
                fStatistics.setTotal_Amount_paid_credited_Add_Mode(value);
            else
            if(counter == 8)
                fStatistics.setTotal_Tax_Deducted_Org_Mode(value);
            else
            if(counter == 9)
                fStatistics.setTotal_Tax_Deducted_Updt_Mode(value);
            else
            if(counter == 10)
                fStatistics.setTotal_Tax_Deducted_Add_Mode(value);
            else
            if(counter == 11)
                fStatistics.setTotal_Tax_Deducted_Del_Mode(value);
            else
            if(counter == 12)
                fStatistics.setTotal_Amount_deposited_Org_Mode(value);
            else
            if(counter == 13)
                fStatistics.setTotal_Amount_deposited_Updt_Mode(value);
            else
            if(counter == 14)
                fStatistics.setTotal_Amount_deposited_Add_Mode(value);
            else
            if(counter == 15)
                fStatistics.setTotal_Amount_deposited_Del_Mode(value);
            else
            if(counter == 16)
                fStatistics.setTotal_Value_of_Purchase(value);
            else
            if(counter == 17)
                fStatistics.setNo_DD_C5(value);
            else
            if(counter == 18)
                fStatistics.setNo_SD_C5(value);
            else
            if(counter == 19)
                fStatistics.setNo_of_LS(value);
            else
                fStatistics.setNo_of_LD(fStatistics.getNo_of_LD() + "&" + token);
        }

        logger.info("FileGenerator.java ParseString() method is returning ");
        return fStatistics;
    }

    public ArrayList parseString(String toParse, String Seperator)
    {
        ArrayList aList = new ArrayList();
        for(StringTokenizer parsed = new StringTokenizer(toParse, Seperator); parsed.hasMoreTokens(); aList.add(parsed.nextElement()));
        return aList;
    }

    private String setDateFormat(String dateString)
    {
        String month = dateString.substring(0, 2);
        String date = dateString.substring(3, 5);
        String year = dateString.substring(6, 10);
        switch(Integer.parseInt(month.trim()))
        {
        case 1: // '\001'
            month = "January";
            break;

        case 2: // '\002'
            month = "February";
            break;

        case 3: // '\003'
            month = "March";
            break;

        case 4: // '\004'
            month = "April";
            break;

        case 5: // '\005'
            month = "May";
            break;

        case 6: // '\006'
            month = "June";
            break;

        case 7: // '\007'
            month = "July";
            break;

        case 8: // '\b'
            month = "August";
            break;

        case 9: // '\t'
            month = "September";
            break;

        case 10: // '\n'
            month = "October";
            break;

        case 11: // '\013'
            month = "November";
            break;

        case 12: // '\f'
            month = "December";
            break;
        }
        dateString = date + " " + month + " " + year;
        return dateString;
    }

    public FileStatistics getStatisticFileDetail(String StringToParse, String Seperator, FileStatistics fStatistics)
    {
        logger.info("FileGenerator.java getStatisticaFileDetail() method Called");
        StringTokenizer batch = new StringTokenizer(StringToParse, Seperator);
        for(int counter = 0; batch.hasMoreTokens(); counter++)
        {
            String token = batch.nextToken();
            if(counter == 0)
                fStatistics.setNo_of_challans(token);
            else
            if(counter == 1)
                fStatistics.setForm_No(token);
            else
            if(counter == 2)
                fStatistics.setTransaction_Type(token);
            else
            if(counter == 3)
                fStatistics.setTAN(token);
            else
            if(counter == 4)
                fStatistics.setLastTAN(token);
            else
            if(counter == 5)
                fStatistics.setPAN_of_TAN(token);
            else
            if(counter == 6)
                fStatistics.setAssmnt_Year(token);
            else
            if(counter == 7)
                fStatistics.setFinancial_Year(token);
            else
            if(counter == 8)
                fStatistics.setPeriodicity(token);
            else
            if(counter == 9)
                fStatistics.setName_of_Deductor(token);
            else
            if(counter == 10)
                fStatistics.setTotal_Challan_Amount(token);
            else
            if(counter == 11)
                fStatistics.setNo_of_Salary_Detail(token);
            else
            if(counter == 12)
                fStatistics.setTotal_Income_As_Per_SD(token);
            else
            if(counter == 13)
                fStatistics.setChallanCount(token);
            else
            if(counter == 14)
                fStatistics.setChallanAmount(token);
            else
            if(counter == 15)
                fStatistics.setTransferVoucherCount(token);
            else
            if(counter == 16)
                fStatistics.setTransferVoucherAmount(token);
            else
            if(counter == 17)
                fStatistics.setBookCashFlag(token);
            else
            if(counter == 18)
                fStatistics.setTotalCINMatchChallan(token);
            else
            if(counter == 19)
                fStatistics.setTotalCINNotMathcedChallan(token);
            else
            if(counter == 20)
                fStatistics.setTotalCINChallan(token);
            else
            if(counter == 21)
                fStatistics.setNo_of_Deductee_Records(token);
            else
            if(counter == 22)
                fStatistics.setNo_of_Valid_Pan(token);
            else
                fStatistics.setPAN_Report_Table(fStatistics.getPAN_Report_Table() + token);
        }

        logger.info("FileGenerator.java getStatisticaFileDetail() method is returning ");
        return fStatistics;
    }

    public FileStatistics getStatisticSalDetail(String StringToParse, String Seperator, FileStatistics fStatistics)
    {
        logger.info("FileGenerator.java getStatisticSalDetail() method Called");
        StringTokenizer sal = new StringTokenizer(StringToParse, Seperator);
        for(int counter = 0; sal.hasMoreTokens(); counter++)
        {
            String token = sal.nextToken();
            if(counter == 0)
                fStatistics.setNo_of_Sal_Valid_Pan(token);
            else
            if(counter == 1)
                fStatistics.setNo_of_sal_PAN_Applied(token);
            else
            if(counter == 2)
                fStatistics.setNo_of_Sal_PAN_NotAvailable(token);
            else
            if(counter == 3)
                fStatistics.setNo_of_sal_PAN_Invalid(token);
            else
                fStatistics.setPAN_Report_Table(fStatistics.getPAN_Report_Table() + token);
        }

        logger.info("FileGenerator.java getStatisticaFileDetail() method is returning ");
        return fStatistics;
    }

    private void createStatisticFile(FileStatistics fStatistics, String statisticFileName, boolean fileOpenFlag, boolean lastBatchOfFile, String fileName, String FVUVersion, boolean bsrPresent, 
            String bsr, int bsrCount)
    {
        try
        {
            StringBuffer toReturn = new StringBuffer();
            FileGenerator flgObj = new FileGenerator();
            logger.info("FileGenerator.java createStatisticFileString() method is called");
            boolean isTANChanged = false;
            boolean createTableForTANChange = false;
            toReturn.append("<HTML>");
            if(fStatistics.getForm_No().equals("27EQ"))
            {
                toReturn.append("<HEAD> <TITLE> FVU -TCS STATEMENT STATISTICS REPORT</TITLE></HEAD>");
                toReturn.append("<BODY>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse: collapse; border: none; mso-border-alt: solid windowtext .5pt; mso-padding-alt: 0in 5.4pt 0in 5.4pt'><TR><TD>");
                toReturn.append("<TABLE BORDER=0 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'><TR><TD><BR>");
                toReturn.append("<H3><CENTER> FVU -TCS STATEMENT STATISTICS REPORT - Batch Number " + fStatistics.getBatch_No() + "</CENTER></H3>");
            } else
            {
                toReturn.append("<HEAD> <TITLE> FVU -TDS STATEMENT STATISTICS REPORT </TITLE></HEAD>");
                toReturn.append("<BODY>");
                toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse: collapse; border: none; mso-border-alt: solid windowtext .5pt; mso-padding-alt: 0in 5.4pt 0in 5.4pt'><TR><TD>");
                toReturn.append("<TABLE BORDER=0 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'><TR><TD><BR>");
                toReturn.append("<H3><CENTER> FVU -TDS STATEMENT STATISTICS REPORT - Batch Number " + fStatistics.getBatch_No() + "</CENTER></H3>");
                toReturn.append("</TD></TR>");
            }
            toReturn.append("<TR>");
            toReturn.append("<TD>");
            if(fStatistics.getUpload_Type().equals("R"))
            {
                toReturn.append("You are advised to verify the details of your TAN at Income Tax Department\u2019s web-site (www.incometaxindia.gov.in) before submission of the statement.  If the data displayed is not updated then request for necessary changes by submitting \u2018Form for Changes or Corrections in TAN data for TAN allotted\u2019 along with the statement.  If such a request has already been filed, then please enclose a copy of acknowledgment of such request with the Form 27A.");
                toReturn.append("<BR><BR>The details in the report are as per the statement prepared by you. In case of any discrepancy in the details, rectify the statement accordingly. Thereafter, validate the rectified statement again through FVU.");
                toReturn.append("<BR><BR>The details provided in the physical Form 27A should match with the statistics report.");
                toReturn.append("<BR><BR>You can track the status of the challans as well as the statements furnished at www.tin-nsdl.com using TAN and Provisional Receipt Number.");
            } else
            {
                toReturn.append("The details in the report are as per the statement prepared by you. In case of any discrepancy in the details, rectify the statement accordingly.Thereafter, validate the rectified statement again through FVU.");
                toReturn.append("<BR><BR>The details provided in the physical Form 27A should match with the statistics report. This  report is required to be submitted along with Form 27A.");
                toReturn.append("<BR><BR>Furnish a copy of the Provisional Receipt issued for the corresponding regular statement along with Form 27A");
                toReturn.append("<BR><BR>You can track the status of the challans as well as the statements furnished at www.tin-nsdl.com using TAN and Provisional Receipt Number.");
            }
            toReturn.append("</TD>");
            toReturn.append("</TR>");
            toReturn.append("</TABLE>");
            toReturn.append("<BR><BR>");
            toReturn.append("<TABLE BORDER=.5pt CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            if(fStatistics.getForm_No().equals("27EQ"))
            {
                toReturn.append("<TD width=500 valign=top><B>   Name of Collector\t</B></CENTER></TD>");
                if(!fStatistics.getTransaction_Type().equals("C1") || fStatistics.getTransaction_Type().equals("C1") && fStatistics.getTAN().equals(fStatistics.getLastTAN()))
                    toReturn.append("<TD width=250 valign=top><B>   TAN of Collector </B></CENTER></TD>");
                else
                    createTableForTANChange = true;
            } else
            {
                toReturn.append("<TD width=400 valign=top><B>   Name of Deductor </B></CENTER></TD>");
                if(!fStatistics.getTransaction_Type().equals("C1") || fStatistics.getTransaction_Type().equals("C1") && fStatistics.getTAN().equals(fStatistics.getLastTAN()))
                    toReturn.append("<TD width=200 valign=top><B>   TAN of Deductor </B></CENTER></TD>");
                else
                    createTableForTANChange = true;
            }
            toReturn.append("</TR>");
            toReturn.append("<TR>");
            toReturn.append("<TD  ALIGN=LEFT width=500> " + fStatistics.getName_of_Deductor() + "</TD>");
            if(!fStatistics.getTransaction_Type().equals("C5"))
            {
                if(!fStatistics.getTransaction_Type().equals("C1") || fStatistics.getTransaction_Type().equals("C1") && fStatistics.getTAN().equals(fStatistics.getLastTAN()))
                    toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getTAN() + " </TD>");
                else
                    isTANChanged = true;
            } else
            {
                toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getLastTAN() + " </TD>");
            }
            toReturn.append("</TR>");
            toReturn.append("</TABLE><P>");
            if(createTableForTANChange)
            {
                toReturn.append("<TABLE BORDER=.5pt CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                if(fStatistics.getForm_No().equals("27EQ"))
                {
                    toReturn.append("<TD width=250 valign=top><B>   TAN of Collector </B></CENTER></TD>");
                    toReturn.append("<TD width=250 valign=top><B>   New TAN of Collector* </B></CENTER></TD>");
                } else
                {
                    toReturn.append("<TD width=250 valign=top><B>   TAN of Deductor </B></CENTER></TD>");
                    toReturn.append("<TD width=250 valign=top><B>   New TAN of Deductor* </B></CENTER></TD>");
                }
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getLastTAN() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getTAN() + " </TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE><P>");
            }
            toReturn.append("<TABLE BORDER=.5pt CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            if(fStatistics.getForm_No().equals("27EQ"))
                toReturn.append("<TD width=250 valign=top><B>   PAN of Collector </B></CENTER></TD>");
            else
                toReturn.append("<TD width=250 valign=top><B>   PAN of Deductor </B></CENTER></TD>");
            toReturn.append("<TD valign=top><B>   Form Number  </B></CENTER></TD>");
            toReturn.append("<TD valign=top><B>   Form Type  </B></CENTER></TD>");
            toReturn.append("</TR>");
            toReturn.append("<TR>");
            toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getPAN_of_TAN() + " </TD>");
            toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getForm_No() + " </TD>");
            if(fStatistics.getForm_Type().equals("SL1"))
                toReturn.append("<TD  ALIGN=LEFT width=250>Salary (Electronic)</TD>");
            else
            if(fStatistics.getForm_Type().equals("SL9"))
                toReturn.append("<TD  ALIGN=LEFT width=250>Salary (Paper)</TD>");
            else
            if(fStatistics.getForm_Type().equals("NS1"))
                toReturn.append("<TD  ALIGN=LEFT width=250>Non-Salary (Electronic)</TD>");
            else
            if(fStatistics.getForm_Type().equals("NS9"))
                toReturn.append("<TD  ALIGN=LEFT width=250>Non-Salary (Paper)</TD>");
            else
            if(fStatistics.getForm_Type().equals("TC1"))
                toReturn.append("<TD  ALIGN=LEFT width=250>TCS (Electronic)</TD>");
            else
            if(fStatistics.getForm_Type().equals("TC9"))
                toReturn.append("<TD  ALIGN=LEFT width=250>TCS (Paper)</TD>");
            toReturn.append("</TR>");
            toReturn.append("</TABLE><P>");
            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            toReturn.append("<TD valign=top><B>   Assessment Year  </B></CENTER></TD>");
            toReturn.append("<TD valign=top><B>   Financial Year  </B></CENTER></TD>");
            toReturn.append("<TD valign=top><B>   Quarter  </B></CENTER></TD>");
            toReturn.append("<TD valign=top><B>   Upload Type  </B></CENTER></TD>");
            toReturn.append("<TD valign=top><B>   Type of Correction  </B></CENTER></TD>");
            toReturn.append("</TR>");
            toReturn.append("<TR>");
            String assmntYear = fStatistics.getAssmnt_Year().substring(0, 4);
            String assmntYear2 = fStatistics.getAssmnt_Year().substring(4, 6);
            toReturn.append("<TD  ALIGN=LEFT width=150>" + assmntYear + "-" + assmntYear2 + " </TD>");
            String finanYear = fStatistics.getFinancial_Year().substring(0, 4);
            String finanYear2 = fStatistics.getFinancial_Year().substring(4, 6);
            toReturn.append("<TD  ALIGN=LEFT width=150>" + finanYear + "-" + finanYear2 + "</TD>");
            toReturn.append("<TD  ALIGN=LEFT width=150>" + fStatistics.getPeriodicity() + "</TD>");
            if(fStatistics.getUpload_Type().equals("R"))
                toReturn.append("<TD  ALIGN=LEFT width=160>Regular</TD>");
            else
                toReturn.append("<TD  ALIGN=LEFT width=160>Correction</TD>");
            if(fStatistics.getUpload_Type().equals("R"))
                toReturn.append("<TD  ALIGN=LEFT width=300>NA</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("C1"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C1-Correction in deductor's(collector's) details and/or statement related details</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("C9"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C9-Correction in challan details (addition of challan/s)</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("C2"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C2-Correction in deductor's(collector's) details and/or statement related and/or challan details</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("C3"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C3-Correction in deductor's(collector's) details and/or statement related particulars and/or challan/deductee details</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("C4"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C4-Correction in salary details of deductees</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("C5"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C5-Correction in PAN of deductees/parties</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("X"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> X-Challan Cancellation</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("Y"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> Y-Statement Cancellation</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("Z"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> Z-Salary detail replacement</TD>");
            else
            if(fStatistics.getTransaction_Type().equals("Y1"))
                toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> Y1-Statement Cancellation</TD>");
            toReturn.append("</TR>");
            toReturn.append("</TABLE><BR>");
            if(!fStatistics.getTransaction_Type().equals("C1") && !fStatistics.getTransaction_Type().equals("Y") && !fStatistics.getTransaction_Type().equals("Y1"))
            {
                toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                toReturn.append("<TR>");
                if(!fStatistics.getTransaction_Type().equals("C4") && !fStatistics.getTransaction_Type().equals("Z"))
                    if(fStatistics.getBookCashFlag().equals("C"))
                    {
                        toReturn.append("<TD valign=top width=250 style='text-align:left'><B> No. of Challans</B></TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD valign=top width=250 style='text-align:left'><B> Total Challan Amount (Rs.)</B></TD>");
                    } else
                    if(fStatistics.getBookCashFlag().equals("T"))
                    {
                        toReturn.append("<TD valign=top width=250 style='text-align:left'><B> No. of Transfer Vouchers</B></TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD valign=top width=250 style='text-align:left'><B> Total Transfer Voucher Amount (Rs.)</B></TD>");
                    } else
                    {
                        toReturn.append("<TD valign=top width=250 style='text-align:left'><B> No. of Challans</B></TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD valign=top width=250 style='text-align:left'><B> Total Challan Amount (Rs.)</B></TD>");
                        toReturn.append("<TD valign=top width=250 style='text-align:left'><B> No. of Transfer Vouchers</B></TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD valign=top width=250 style='text-align:left'><B> Total Transfer Voucher Amount (Rs.)</B></TD>");
                    }
                if(!fStatistics.getTransaction_Type().equals("C2") && !fStatistics.getTransaction_Type().equals("X") && !fStatistics.getTransaction_Type().equals("C4") && !fStatistics.getTransaction_Type().equals("Z"))
                {
                    if(fStatistics.getForm_No().equals("27EQ"))
                        toReturn.append("<TD valign=top width=250 style='text-align:left'><B> No. of Party Records</B></TD>");
                    else
                        toReturn.append("<TD valign=top width=250 style='text-align:left'><B> No. of Deductee Records</B></TD>");
                    if(fStatistics.getForm_No().equals("27EQ"))
                        toReturn.append("<TD valign=top width=250 style='text-align:right'><B> Total Value of Purchase (Rs.)</B></TD>");
                }
                if(fStatistics.getForm_No().equals("24Q") && (fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C4") || fStatistics.getTransaction_Type().equals("Z") || fStatistics.getTransaction_Type().equals("C5")) && fStatistics.getPeriodicity().equals("Q4"))
                    toReturn.append("<TD valign=top width=200 style='text-align:left'><B> No. of Salary Detail Records</B></TD>");
                if(fStatistics.getForm_No().equals("24Q") && (fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C4") || fStatistics.getTransaction_Type().equals("Z")) && fStatistics.getPeriodicity().equals("Q4"))
                    toReturn.append("<TD valign=top width=200 style='text-align:left'><B> Batch Total of - Gross Total Income as per Salary Detail (Rs.)</B></TD>");
                toReturn.append("</TR>");
                toReturn.append("<TR>");
                if(!fStatistics.getTransaction_Type().equals("C4") && !fStatistics.getTransaction_Type().equals("Z"))
                    if(fStatistics.getBookCashFlag().equals("C"))
                    {
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getChallanCount() + "</TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getChallanAmount() + "</TD>");
                    } else
                    if(fStatistics.getBookCashFlag().equals("T"))
                    {
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTransferVoucherCount() + "</TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTransferVoucherAmount() + "</TD>");
                    } else
                    {
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getChallanCount() + "</TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getChallanAmount() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTransferVoucherCount() + "</TD>");
                        if(!fStatistics.getTransaction_Type().equals("C5"))
                            toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTransferVoucherAmount() + "</TD>");
                    }
                if(!fStatistics.getTransaction_Type().equals("C2") && !fStatistics.getTransaction_Type().equals("X") && !fStatistics.getTransaction_Type().equals("C4") && !fStatistics.getTransaction_Type().equals("Z"))
                {
                    toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getNo_of_Deductee_Records() + "</TD>");
                    if(fStatistics.getForm_No().equals("27EQ"))
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Value_of_Purchase() + "</TD>");
                }
                if(fStatistics.getForm_No().equals("24Q") && (fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C4") || fStatistics.getTransaction_Type().equals("Z") || fStatistics.getTransaction_Type().equals("C5")) && fStatistics.getPeriodicity().equals("Q4"))
                    toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getNo_of_Salary_Detail() + "</TD>");
                if(fStatistics.getForm_No().equals("24Q") && (fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C4") || fStatistics.getTransaction_Type().equals("Z")) && fStatistics.getPeriodicity().equals("Q4"))
                    toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Income_As_Per_SD() + "</TD>");
                toReturn.append("</TR>");
                toReturn.append("</TABLE><P>");
                if((fStatistics.getTransaction_Type().equals("C2") || fStatistics.getTransaction_Type().equals("C3") || fStatistics.getTransaction_Type().equals("C9") || fStatistics.getUpload_Type().equals("R")) && !fStatistics.getBookCashFlag().trim().equals("T"))
                {
                    toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                    toReturn.append("<TR>");
                    toReturn.append("<TD valign=top width=200 ><B> No. of Challans (excluding nil) </B></TD>");
                    toReturn.append("<TD valign=top width=200 ><B> No. of Unmatched challan </B></TD>");
                    toReturn.append("<TD valign=top width=200 ><B> No. of Matched challan </B></TD>");
                    toReturn.append("</TR>");
                    toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotalCINChallan() + "</TD>");
                    if(FileStatistics.getCinUploadFlag() == 0 && Integer.parseInt(fStatistics.getTotalCINChallan()) > 0)
                        toReturn.append("<TD  ALIGN=CENTER >Challan not verified</TD>");
                    else
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotalCINNotMathcedChallan() + "</TD>");
                    if(FileStatistics.getCinUploadFlag() == 0 && Integer.parseInt(fStatistics.getTotalCINChallan()) > 0)
                        toReturn.append("<TD  ALIGN=CENTER >Challan not verified</TD>");
                    else
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotalCINMatchChallan() + "</TD>");
                    toReturn.append("</TABLE><P>");
                }
                if(fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C9") || fStatistics.getTransaction_Type().equals("C3"))
                {
                    toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                    toReturn.append("<TR>");
                    if(fStatistics.getTransaction_Type().equals("C3"))
                        toReturn.append("<TD valign=top width=200 ><B> Particulars </B></TD>");
                    if(fStatistics.getForm_No().equals("27EQ"))
                        toReturn.append("<TD valign=top width=200 style='text-align:left'><B> Amount Received / Debited (Rs.)</B></TD>");
                    else
                        toReturn.append("<TD valign=top width=200 style='text-align:left'><B> Amount of Payment / Credit (Rs.)</B></TD>");
                    if(fStatistics.getForm_No().equals("27EQ"))
                        toReturn.append("<TD valign=top width=200 style='text-align:left'><B> Total Income Tax Collected at Source (Rs.)</B></TD>");
                    else
                        toReturn.append("<TD valign=top width=200 style='text-align:left'><B> Total Income Tax Deducted at Source (Rs.)</B></TD>");
                    if(fStatistics.getForm_No().equals("27EQ"))
                        toReturn.append("<TD valign=top width=200 style='text-align:left'><B> Total Tax Deposited as per Party Annexure (Rs.)</B></TD>");
                    else
                        toReturn.append("<TD valign=top width=200 style='text-align:left'><B> Total Tax Deposited as per Deductee Annexure (Rs.)</B></TD>");
                    toReturn.append("</TR>");
                    toReturn.append("<TR>");
                    if(fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C9"))
                    {
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Amount_paid_credited_Org_Mode() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Tax_Deducted_Org_Mode() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Amount_deposited_Org_Mode() + "</TD>");
                    } else
                    if(fStatistics.getTransaction_Type().equals("C3"))
                    {
                        toReturn.append("<TR>");
                        toReturn.append("<TD  ><B>Added (a)</B></TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Amount_paid_credited_Add_Mode() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Tax_Deducted_Add_Mode() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Amount_deposited_Add_Mode() + "</TD>");
                        toReturn.append("</TR>");
                        toReturn.append("<TR>");
                        toReturn.append("<TD  ><B>Updated (b)</B></TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Amount_paid_credited_Updt_Mode() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Tax_Deducted_Updt_Mode() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Amount_deposited_Updt_Mode() + "</TD>");
                        toReturn.append("</TR>");
                        toReturn.append("<TR>");
                        toReturn.append("<TD  ><B>Deleted (c)</B></TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >NA</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Tax_Deducted_Del_Mode() + "</TD>");
                        toReturn.append("<TD  ALIGN=RIGHT >" + fStatistics.getTotal_Amount_deposited_Del_Mode() + "</TD>");
                        toReturn.append("</TR>");
                        toReturn.append("<TR>");
                        toReturn.append("<TD  ><B>Total (a + b + c)</B></TD>");
                        double val1 = 0.0D;
                        double val2 = 0.0D;
                        double val3 = 0.0D;
                        String outputValue = null;
                        if(!fStatistics.getTotal_Amount_paid_credited_Add_Mode().equals("-"))
                            val1 = Double.parseDouble(fStatistics.getTotal_Amount_paid_credited_Add_Mode().trim());
                        if(!fStatistics.getTotal_Amount_paid_credited_Updt_Mode().equals("-"))
                            val2 = Double.parseDouble(fStatistics.getTotal_Amount_paid_credited_Updt_Mode().trim());
                        val3 = val1 + val2;
                        val3 = (double)Math.round(val3 * 100D) / 100D;
                        FieldValidation fvObj = new FieldValidation();
                        outputValue = fvObj.changeNumberFormat(val3);
                        toReturn.append("<TD  ALIGN=RIGHT >" + outputValue + "</TD>");
                        val1 = 0.0D;
                        val2 = 0.0D;
                        val3 = 0.0D;
                        double val4 = 0.0D;
                        if(!fStatistics.getTotal_Tax_Deducted_Add_Mode().equals("-"))
                            val1 = Double.parseDouble(fStatistics.getTotal_Tax_Deducted_Add_Mode().trim());
                        if(!fStatistics.getTotal_Tax_Deducted_Updt_Mode().equals("-"))
                            val2 = Double.parseDouble(fStatistics.getTotal_Tax_Deducted_Updt_Mode().trim());
                        if(!fStatistics.getTotal_Tax_Deducted_Del_Mode().equals("-"))
                            val3 = Double.parseDouble(fStatistics.getTotal_Tax_Deducted_Del_Mode().trim());
                        val4 = val1 + val2 + val3;
                        val4 = (double)Math.round(val4 * 100D) / 100D;
                        outputValue = null;
                        outputValue = fvObj.changeNumberFormat(val4);
                        toReturn.append("<TD  ALIGN=RIGHT >" + outputValue + "</TD>");
                        val1 = 0.0D;
                        val2 = 0.0D;
                        val3 = 0.0D;
                        val4 = 0.0D;
                        if(!fStatistics.getTotal_Amount_deposited_Add_Mode().equals("-"))
                            val1 = Double.parseDouble(fStatistics.getTotal_Amount_deposited_Add_Mode().trim());
                        if(!fStatistics.getTotal_Amount_deposited_Updt_Mode().equals("-"))
                            val2 = Double.parseDouble(fStatistics.getTotal_Amount_deposited_Updt_Mode().trim());
                        if(!fStatistics.getTotal_Amount_deposited_Del_Mode().equals("-"))
                            val3 = Double.parseDouble(fStatistics.getTotal_Amount_deposited_Del_Mode().trim());
                        val4 = val1 + val2 + val3;
                        val4 = (double)Math.round(val4 * 100D) / 100D;
                        outputValue = null;
                        outputValue = fvObj.changeNumberFormat(val4);
                        toReturn.append("<TD  ALIGN=RIGHT >" + outputValue + "</TD>");
                        toReturn.append("</TR>");
                    }
                    toReturn.append("</TR>");
                    toReturn.append("</TABLE>");
                }
                if(fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C9") || fStatistics.getTransaction_Type().equals("C3") || fStatistics.getTransaction_Type().equals("C4") || fStatistics.getTransaction_Type().equals("Z"))
                    if(fStatistics.getForm_No().equals("27EQ"))
                    {
                        toReturn.append("<H4> Party PAN Details </H4>");
                        toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                        toReturn.append("<TR>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of Valid PAN </B></CENTER></TD>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Applied </B></CENTER></TD>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Not Available </B></CENTER></TD>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of Structurally Invalid PAN </B></CENTER></TD>");
                        toReturn.append("</TR>");
                        toReturn.append("<TR>");
                        toReturn.append("<TD  ALIGN=RIGHT width=195>" + fStatistics.getNo_of_Valid_Pan() + " </TD>");
                        if(!fStatistics.getNo_of_PAN_Applied().equals("-"))
                            toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Applied() + " </TD>");
                        else
                            toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                        if(!fStatistics.getNo_of_PAN_NotAvailabble().equals("-"))
                            toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_NotAvailabble() + " </TD>");
                        else
                            toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                        if(!fStatistics.getNo_of_PAN_Invalid().equals("-"))
                            toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Invalid() + " </TD>");
                        else
                            toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                        toReturn.append("</TR>");
                        toReturn.append("</TABLE>");
                    } else
                    if(fStatistics.getForm_No().equals("24Q") && fStatistics.getPeriodicity().equals("Q4"))
                    {
                        if(fStatistics.getTransaction_Type().equals("C4"))
                        {
                            toReturn.append("<H4> Salary PAN Details (Annexure II) </H4>");
                            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Valid PAN </B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Applied (PANAPPLIED)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Not Available (PANNOTAVBL)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Structurally Invalid PAN (PANINVALID)</B></CENTER></TD>");
                            toReturn.append("</TR>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD  ALIGN=RIGHT width=195>" + fStatistics.getNo_of_Sal_Valid_Pan() + " </TD>");
                            if(!fStatistics.getNo_of_PAN_Applied().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_sal_PAN_Applied() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_NotAvailabble().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_sal_PAN_NotAvailabble() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_Invalid().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_sal_PAN_Invalid() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            toReturn.append("</TR>");
                            toReturn.append("</TABLE>");
                        } else
                        if(fStatistics.getTransaction_Type().equals("C9") || fStatistics.getTransaction_Type().equals("C3"))
                        {
                            toReturn.append("<H4> Deductee PAN Details (Annexure I) </H4>");
                            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Valid PAN </B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Applied (PANAPPLIED)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Not Available (PANNOTAVBL)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Structurally Invalid PAN (PANINVALID)</B></CENTER></TD>");
                            toReturn.append("</TR>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD  ALIGN=RIGHT width=195>" + fStatistics.getNo_of_Valid_Pan() + " </TD>");
                            if(!fStatistics.getNo_of_PAN_Applied().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Applied() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_NotAvailabble().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_NotAvailabble() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_Invalid().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Invalid() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            toReturn.append("</TR>");
                            toReturn.append("</TABLE> <P>");
                        } else
                        {
                            toReturn.append("<H4> Deductee PAN Details (Annexure I) </H4>");
                            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Valid PAN </B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Applied (PANAPPLIED)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Not Available (PANNOTAVBL)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Structurally Invalid PAN (PANINVALID)</B></CENTER></TD>");
                            toReturn.append("</TR>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD  ALIGN=RIGHT width=195>" + fStatistics.getNo_of_Valid_Pan() + " </TD>");
                            if(!fStatistics.getNo_of_PAN_Applied().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Applied() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_NotAvailabble().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_NotAvailabble() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_Invalid().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Invalid() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            toReturn.append("</TR>");
                            toReturn.append("</TABLE><P>");
                            toReturn.append("<H4> Salary PAN Details (Annexure II) </H4>");
                            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Valid PAN </B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Applied (PANAPPLIED)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Not Available (PANNOTAVBL)</B></CENTER></TD>");
                            toReturn.append("<TD valign=top style='text-align:left'><B> No. of Structurally Invalid PAN (PANINVALID)</B></CENTER></TD>");
                            toReturn.append("</TR>");
                            toReturn.append("<TR>");
                            toReturn.append("<TD  ALIGN=RIGHT width=195>" + fStatistics.getNo_of_Sal_Valid_Pan() + " </TD>");
                            if(!fStatistics.getNo_of_PAN_Applied().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_sal_PAN_Applied() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_NotAvailabble().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_sal_PAN_NotAvailabble() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            if(!fStatistics.getNo_of_PAN_Invalid().equals("-"))
                                toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_sal_PAN_Invalid() + " </TD>");
                            else
                                toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                            toReturn.append("</TR>");
                            toReturn.append("</TABLE> <P>");
                        }
                    } else
                    {
                        toReturn.append("<H4> Deductee PAN Details (Annexure I) </H4>");
                        toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                        toReturn.append("<TR>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of Valid PAN </B></CENTER></TD>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Applied (PANAPPLIED)</B></CENTER></TD>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of PAN Not Available (PANNOTAVBL)</B></CENTER></TD>");
                        toReturn.append("<TD valign=top style='text-align:left'><B> No. of Structurally Invalid PAN (PANINVALID)</B></CENTER></TD>");
                        toReturn.append("</TR>");
                        toReturn.append("<TR>");
                        toReturn.append("<TD  ALIGN=RIGHT width=195>" + fStatistics.getNo_of_Valid_Pan() + " </TD>");
                        if(!fStatistics.getNo_of_PAN_Applied().equals("-"))
                            toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Applied() + " </TD>");
                        else
                            toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                        if(!fStatistics.getNo_of_PAN_NotAvailabble().equals("-"))
                            toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_NotAvailabble() + " </TD>");
                        else
                            toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                        if(!fStatistics.getNo_of_PAN_Invalid().equals("-"))
                            toReturn.append("<TD  ALIGN=RIGHT width=189>" + fStatistics.getNo_of_PAN_Invalid() + " </TD>");
                        else
                            toReturn.append("<TD  ALIGN=RIGHT width=189>- </TD>");
                        toReturn.append("</TR>");
                        toReturn.append("</TABLE><P>");
                    }
                if(bsrPresent)
                {
                    toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                    toReturn.append("<TR>");
                    toReturn.append("<TD ALIGN=LEFT width=581 ><B> Distinct Count of mismatch of Bank branch code in  e-TDS/TCS statement with the authorized bank branch code master </B></CENTER></TD>");
                    toReturn.append("<TD ALIGN=Right width=181 > " + bsrCount + "</CENTER></TD>");
                    toReturn.append("</TR>");
                    toReturn.append("</TABLE><P>");
                    toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
                    toReturn.append("<TR>");
                    toReturn.append("<TD ALIGN=LEFT width=762 ><B> Details of bank branch code not present in list of authorized bank branch code master</B></CENTER></TD>");
                    toReturn.append("</TR>");
                    toReturn.append("<TR>");
                    toReturn.append("<TD ALIGN=LEFT width=762 ><B> " + bsr + "</B></CENTER></TD>");
                    toReturn.append("</TR>");
                    toReturn.append("</TABLE>");
                }
                if(fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C9") || fStatistics.getTransaction_Type().equals("C3"))
                {
                    toReturn.append("<BR><B> Last Deductee (LD) Detail Record Number in Challan : </B>");
                    if(!fStatistics.getNo_of_LD().equals(""))
                    {
                        StringTokenizer str = new StringTokenizer(fStatistics.getNo_of_LD(), "&");
                        int no_of_token = str.countTokens();
                        for(int i = 0; i < no_of_token; i++)
                            if(i < no_of_token - 1)
                                toReturn.append(str.nextToken() + ", ");
                            else
                                toReturn.append(str.nextToken());

                        toReturn.append(" (eg. LD1=2 means \"Last Deductee detail record no. of Challan 1 is 2\")");
                    } else
                    {
                        toReturn.append(" - ");
                    }
                }
                if(fStatistics.getForm_No().equals("24Q") && fStatistics.getUpload_Type().equals("R") || fStatistics.getTransaction_Type().equals("C4") || fStatistics.getTransaction_Type().equals("Z"))
                {
                    toReturn.append("<BR>");
                    if(!fStatistics.getNo_of_LS().equals(""))
                        toReturn.append("<B> Last Salary (LS) Details Record Number in Batch : </B>" + fStatistics.getNo_of_LS() + " </B><BR>");
                    else
                        toReturn.append("<B> Last Salary (LS) Details Record Number in Batch </B>: - <BR>");
                }
            }
            if(isTANChanged)
                toReturn.append("* TAN correction is subjected to approval from Income Tax Department");
            toReturn.append("<BR> FVU Version : " + FVUVersion + " &nbsp;&nbsp; Input File Name : " + fileName);
            toReturn.append("<BR><BR>");
            toReturn.append("</TD></TR> </TABLE><BR><BR><BR>");
            if(lastBatchOfFile)
                toReturn.append("<span ><br clear=all style='page-break-before:always'></span>");
            toReturn.append("</BODY>");
            toReturn.append("</HTML>");
            flgObj.writeStatisticFile(statisticFileName, toReturn, fStatistics, fileOpenFlag, false);
            toReturn = new StringBuffer();
            logger.info("FileGenerator.java createStatisticFileString() method is returning");
            return;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception in FileGenerator.java createStatisticFileString() method : " + e.toString());
            return;
        }
    }

    public void generateStatisticFile(FormValidator obj_FrmValidator, String statFileName, String fileName, String FVUVersion)
    {
        try
        {
            logger.info("Inside generateStatisticFile Method ");
            ArrayList panDataList = parseString(obj_FrmValidator.getReceiptString().toString(), "~");
            Object panDataArray[] = panDataList.toArray();
            obj_FrmValidator.setReceiptString(new StringBuffer());
            ArrayList statisticDataList = parseString(obj_FrmValidator.getStatisticFileStrBuff().toString(), "\n");
            Object statisticDataArray[] = statisticDataList.toArray();
            obj_FrmValidator.setStatisticFileStrBuff(new StringBuffer());
            ArrayList statisticSalDataList = parseString(obj_FrmValidator.getStatisticSalStrBuff().toString(), "\n");
            Object statisticSalDataArray[] = statisticSalDataList.toArray();
            obj_FrmValidator.setStatisticSalStrBuff(new StringBuffer());
            ArrayList fileHeaderList = parseString((String)statisticDataArray[0], "^");
            Object fileHeaderArray[] = fileHeaderList.toArray();
            ArrayList bsrListToPrint = obj_FrmValidator.getBSRDetails();
            boolean fileOpenFlag = false;
            boolean lastBatchOfFile = true;
            for(int i = 0; i < panDataArray.length; i++)
            {
                FileStatistics fStatistics = getProvisionalDetail((String)panDataArray[i], "^");
                fStatistics.setForm_Type((String)fileHeaderArray[0]);
                fStatistics.setUpload_Type((String)fileHeaderArray[1]);
                fStatistics = getStatisticFileDetail((String)statisticDataArray[i + 1], "^", fStatistics);
                fStatistics = getStatisticSalDetail((String)statisticSalDataArray[i], "^", fStatistics);
                String distinctBSR = "  ";
                int distinctBSRCount = 0;
                boolean invalidBSRPresent = true;
                ArrayList finalList = new ArrayList();
                int countForDistinctBsr = 0;
                if(panDataArray.length - i == 1)
                    lastBatchOfFile = false;
                for(int n = 0; n < bsrListToPrint.size(); n++)
                {
                    String e = bsrListToPrint.get(n).toString();
                    StringTokenizer str = new StringTokenizer(e, "^");
                    String bsrCode = str.nextToken();
                    String batchNo = str.nextToken();
                    countForDistinctBsr = 0;
                    if(Integer.parseInt(batchNo) == i + 1)
                    {
                        if(finalList.size() == 0)
                            finalList.add(bsrCode);
                        for(int k = 0; k < finalList.size(); k++)
                            if(bsrCode.equals(finalList.get(k).toString()))
                                countForDistinctBsr++;

                        if(countForDistinctBsr == 0)
                            finalList.add(bsrCode);
                    }
                }

                if(finalList.size() == 0)
                {
                    invalidBSRPresent = false;
                } else
                {
                    distinctBSRCount = finalList.size();
                    distinctBSR = "";
                    for(int k = 0; k < finalList.size(); k++)
                        distinctBSR = distinctBSR + finalList.get(k).toString() + ", ";

                }
                distinctBSR = distinctBSR.substring(0, distinctBSR.length() - 2);
                createStatisticFile(fStatistics, statFileName, fileOpenFlag, lastBatchOfFile, fileName, FVUVersion, invalidBSRPresent, distinctBSR, distinctBSRCount);
                fileOpenFlag = true;
            }

        }
        catch(Exception e)
        {
            logger.error("generateStatisticFile Method : " + e.toString());
            e.printStackTrace();
            StringBuffer stringbuffer = new StringBuffer();
        }
    }

    private FileStatistics parseDelemitedErrorFile(String StringToParse, String Seperator)
    {
        FileStatistics fStatistics = new FileStatistics();
        int counter = 1;
        boolean NullField = false;
        boolean FieldFound = false;
        boolean Caret = true;
        int localCaretCounter = 0;
        for(StringTokenizer Obj_st = new StringTokenizer(StringToParse, Seperator, true); Obj_st.hasMoreTokens();)
        {
            String token = Obj_st.nextToken();
            NullField = false;
            FieldFound = false;
            if(token.equals("^") && Caret || token.trim().length() == 0)
            {
                NullField = true;
                FieldFound = true;
            }
            if(token.equals("^"))
            {
                Caret = true;
                token = "-";
                if(counter == 1 && localCaretCounter == 0)
                {
                    counter++;
                    fStatistics.setLine_No(token);
                }
                localCaretCounter++;
            } else
            {
                Caret = false;
                FieldFound = true;
            }
            if(counter > 7)
                break;
            if(FieldFound)
            {
                if(counter == 1)
                    fStatistics.setLine_No(token);
                else
                if(counter == 2)
                {
                    fStatistics.setBatch_No("Null");
                    fStatistics.set_Upload_Type(token);
                } else
                if(counter == 3)
                    fStatistics.set_Field_Name(token);
                else
                if(counter == 4)
                    fStatistics.setChallan_No(token);
                else
                if(counter == 5)
                    fStatistics.setDeductee_No(token);
                else
                if(counter == 6)
                    fStatistics.setError_Code(token);
                else
                if(counter == 7)
                    fStatistics.setError_Description(token);
                counter++;
            }
        }

        return fStatistics;
    }

    private FileStatistics parseDelemitedWarFile(String StringToParse, String Seperator)
    {
        FileStatistics fStatistics = new FileStatistics();
        int counter = 1;
        boolean NullField = false;
        boolean FieldFound = false;
        boolean Caret = true;
        int localCaretCounter = 0;
        for(StringTokenizer Obj_st = new StringTokenizer(StringToParse, Seperator, true); Obj_st.hasMoreTokens();)
        {
            String token = Obj_st.nextToken();
            NullField = false;
            FieldFound = false;
            if(token.equals("^") && Caret || token.trim().length() == 0)
            {
                NullField = true;
                FieldFound = true;
            }
            if(token.equals("^"))
            {
                Caret = true;
                token = "-";
                if(counter == 1 && localCaretCounter == 0)
                {
                    counter++;
                    fStatistics.setLine_No(token);
                }
                localCaretCounter++;
            } else
            {
                Caret = false;
                FieldFound = true;
            }
            if(counter > 6)
                break;
            if(FieldFound)
            {
                if(counter == 1)
                    fStatistics.setLine_No(token);
                else
                if(counter == 2)
                    fStatistics.setBatch_No(token);
                else
                if(counter == 3)
                    fStatistics.setChallan_No(token);
                else
                if(counter == 4)
                    fStatistics.setDeductee_No(token);
                else
                if(counter == 5)
                    fStatistics.setError_Code(token);
                else
                if(counter == 6)
                    fStatistics.setError_Description(token);
                counter++;
            }
        }

        return fStatistics;
    }

    private FileStatistics parseDelemitedElecWarFile(String StringToParse, String Seperator)
    {
        FileStatistics fStatistics = new FileStatistics();
        int counter = 1;
        boolean NullField = false;
        boolean FieldFound = false;
        boolean Caret = true;
        int localCaretCounter = 0;
        for(StringTokenizer Obj_st = new StringTokenizer(StringToParse, Seperator, true); Obj_st.hasMoreTokens();)
        {
            String token = Obj_st.nextToken();
            NullField = false;
            FieldFound = false;
            if(token.equals("^") && Caret || token.trim().length() == 0)
            {
                NullField = true;
                FieldFound = true;
            }
            if(token.equals("^"))
            {
                Caret = true;
                token = "-";
                if(counter == 1 && localCaretCounter == 0)
                {
                    counter++;
                    fStatistics.setLine_No(token);
                }
                localCaretCounter++;
            } else
            {
                Caret = false;
                FieldFound = true;
            }
            if(counter > 10)
                break;
            if(FieldFound)
            {
                if(counter == 1)
                    fStatistics.setLine_No(token);
                else
                if(counter == 2)
                    fStatistics.setRecord_Type(token);
                else
                if(counter == 3)
                    fStatistics.setBatch_No(token);
                else
                if(counter == 4)
                    fStatistics.setChallan_No(token);
                else
                if(counter == 5)
                    fStatistics.setDeductee_No(token);
                else
                if(counter == 6)
                    fStatistics.setChallan_Tender_date(token);
                else
                if(counter == 7)
                    fStatistics.setChallan_Serial_No(token);
                else
                if(counter == 8)
                    fStatistics.setBSR_Code(token);
                else
                if(counter == 9)
                    fStatistics.setError_Code(token);
                else
                if(counter == 10)
                    fStatistics.setError_Description(token);
                counter++;
            }
        }

        return fStatistics;
    }

    private StringBuffer createErrorFile(FileStatistics fStatistics, StringBuffer toReturn, boolean paperRetWarFile, boolean electronicRetWarFile)
    {
        try
        {
            if(paperRetWarFile)
            {
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT width=70> " + fStatistics.getLine_No() + "</TD>");
                toReturn.append(setRecordType(fStatistics));
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getBatch_No() + " </TD>");
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getChallan_No() + " </TD>");
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getDeductee_No() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=90> " + fStatistics.getError_Code() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=585> " + fStatistics.getError_Description() + " </TD>");
                toReturn.append("</TR>");
            } else
            if(electronicRetWarFile)
            {
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT width=70> " + fStatistics.getLine_No() + "</TD>");
                toReturn.append("<TD  ALIGN=LEFT width=85> " + fStatistics.getRecord_Type() + " </TD>");
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getBatch_No() + " </TD>");
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getChallan_No() + " </TD>");
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getDeductee_No() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=85> " + fStatistics.getChallan_Tender_date() + " </TD>");
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getChallan_Serial_No() + " </TD>");
                toReturn.append("<TD  ALIGN=RIGHT width=85> " + fStatistics.getBSR_Code() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=90> " + fStatistics.getError_Code() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=585> " + fStatistics.getError_Description() + " </TD>");
                toReturn.append("</TR>");
            } else
            {
                toReturn.append("<TR>");
                toReturn.append("<TD  ALIGN=RIGHT width=70> " + fStatistics.getLine_No() + "</TD>");
                toReturn.append(setRecordType(fStatistics));
                toReturn.append("<TD  ALIGN=LEFT width=200> " + fStatistics.get_Upload_Type() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=200> " + fStatistics.get_Field_Name() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=85> " + fStatistics.getChallan_No() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=85> " + fStatistics.getDeductee_No() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=675> " + fStatistics.getError_Code() + " " + fStatistics.getError_Description() + "</TD>");
                toReturn.append("</TR>");
            }
            return toReturn;
        }
        catch(Exception e)
        {
            logger.error("Exception in FileGenerator.java createErrorFileString() method : " + e.toString());
        }
        return null;
    }

    private StringBuffer createHtmlErrorFileHeader(StringBuffer toReturn, boolean paperRetWarFile, boolean electronicRetWarFile)
    {
        toReturn.append("<HTML>");
        if(paperRetWarFile)
        {
            toReturn.append("<HEAD> <TITLE> TDS/TCS PAPER STATEMENT WARNING FILE </TITLE></HEAD>");
            toReturn.append("<BODY>");
            toReturn.append("<H3><CENTER> TDS/TCS - PAPER STATEMENT WARNING FILE </CENTER></H3>");
            toReturn.append("<BR>This file is generated by FVU if values in relaxed fields are other then actual value. TIN-FC should verify fields mentioned below with value in paper statement. In case any mismatch is observed than data entry error should be rectified and file should be created and validated again using FVU. If values are as per paper statement then sent the upload file to upload centre.");
            toReturn.append("<BR><BR>");
            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            toReturn.append("<TD width=70 valign=top style='text-align:left' ><B>   Line no. </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Record Type </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Batch no. </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Challan Details Record no. </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Deductee/ Salary Detail no. </B></CENTER></TD>");
            toReturn.append("<TD width=90 valign=top><B>   Error Code </B></CENTER></TD>");
            toReturn.append("<TD width=585 valign=top><B>   Error Description </B></CENTER></TD>");
            toReturn.append("</TR>");
        } else
        if(electronicRetWarFile)
        {
            toReturn.append("<HEAD> <TITLE> e-TDS/TCS statement warning file </TITLE></HEAD>");
            toReturn.append("<BODY>");
            toReturn.append("<H3><CENTER><u> e-TDS/TCS statement warning file </u></CENTER></H3>");
            toReturn.append("<BR>e-TDS/TCS statement warning file is generated by FVU if :");
            toReturn.append("<BR>&nbsp &nbsp &nbsp &nbsp 1.\tThe value in the Bank Branch Code (BSR code) field of challan in the e-TDS/TCS statements is not present in the authorized list of collecting Bank Branch&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Codes in the FVU and/or");
            toReturn.append("<BR>&nbsp &nbsp &nbsp &nbsp 2.\tIf the PAN of the deductor is same as that of any deductee in the e-TDS/TCS statement.");
            toReturn.append("<BR>&nbsp &nbsp &nbsp &nbsp 3.\tIf the challans details of the statement do not match with the details uploaded by the bank.");
            toReturn.append("<BR>This file is generated to give an alert to the deductor about the records in which invalid values are mentioned.");
            toReturn.append("<BR><BR>");
            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            toReturn.append("<TD width=70 valign=top style='text-align:left' ><B>   Line no. in the text file </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Record Type </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Batch no. in the text file</B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Challan detail record no. </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Deductee/ Salary detail record no. </B></CENTER></TD>");
            toReturn.append("<TD width=90 valign=top><B>   Challan tender date </B></CENTER></TD>");
            toReturn.append("<TD width=90 valign=top><B>   Challan Serial no. </B></CENTER></TD>");
            toReturn.append("<TD width=90 valign=top><B>   BSR code </B></CENTER></TD>");
            toReturn.append("<TD width=95 valign=top><B>   Error Code </B></CENTER></TD>");
            toReturn.append("<TD width=585 valign=top><B>   Error Description </B></CENTER></TD>");
            toReturn.append("</TR>");
        } else
        {
            toReturn.append("<HEAD> <TITLE> TDS/TCS ERROR FILE </TITLE></HEAD>");
            toReturn.append("<BODY>");
            toReturn.append("<H3><CENTER> TDS/TCS - ERROR FILE </CENTER></H3>");
            toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
            toReturn.append("<TR>");
            toReturn.append("<TD width=70 valign=top style='text-align:right' ><B>   Line No </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Record Type </B></CENTER></TD>");
            toReturn.append("<TD width=200 valign=top><B>   Statement Type </B></CENTER></TD>");
            toReturn.append("<TD width=200 valign=top><B>   Field Name</B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Challan Details Record Number </B></CENTER></TD>");
            toReturn.append("<TD width=85 valign=top><B>   Deductee/ Salary Detail No. </B></CENTER></TD>");
            toReturn.append("<TD width=675 valign=top><B>   Error Code & Description </B></CENTER></TD>");
            toReturn.append("</TR>");
        }
        return toReturn;
    }

    private StringBuffer createHtmlErrorFileFooter(StringBuffer toReturn)
    {
        toReturn.append("</TABLE><BR>");
        toReturn.append("<BR><BR><BR>");
        toReturn.append("</BODY>");
        toReturn.append("</HTML>");
        return toReturn;
    }

    private StringBuffer createHtmlErrorFileFooter(StringBuffer toReturn, String inputFileName, boolean eWarnFl)
    {
        if(eWarnFl)
        {
            toReturn.append("</TABLE><BR>");
            toReturn.append("<BR> <B>Bank branch code not present in the list of authorized bank branches:</B>");
            toReturn.append("<BR> <P>The Bank Branch Code is provided on the challan counterfoil along with the tender date and challan serial no. by the collecting bank branch where TDS / TCS has been deposited.");
            toReturn.append("<BR> Deductor / Collector should verify the Bank Branch Code mentioned in the e-TDS/TCS statement with the bank branch code on the challan counterfoil provided by the bank.");
            toReturn.append("<BR> In case of mismatch, the bank branch codes as per the challan counterfoil should be mentioned in the e-TDS/TCS statement and the file should be revalidated through FVU.");
            toReturn.append("<BR> In case there is no mismatch then the .fvu file created by the FVU should be submitted to the TIN-FC or directly uploaded to NSDL at www.tin-nsdl.com in case you have availed of the online upload of TDS / TCS return. </P>");
            toReturn.append("<BR> <P><B>Same PAN in deductor and deductee PAN field.</P></B>");
            toReturn.append("<P>PAN of the deductor given in the deductor details should normally not appear in the deductee details.");
            toReturn.append("<BR>Deductor/Collector should verify the PAN in the deductee details and if the same has been correctly entered, then the .fvu file created by the FVU should be submitted to the TIN-FC or directly uploaded to NSDL at www.tin-nsdl.com in case you have availed of the online upload of TDS / TCS return.</P>");
        } else
        {
            toReturn.append("</TABLE><BR>");
        }
        toReturn.append("<BR> FVU Version : 2.128 &nbsp;&nbsp; Input File Name : " + inputFileName);
        toReturn.append("<BR><BR><BR>");
        toReturn.append("</BODY>");
        toReturn.append("</HTML>");
        return toReturn;
    }

    public StringBuffer generateHtmlErrorFile(String errorDataString)
    {
        try
        {
            ArrayList errorDataList = parseString(errorDataString, "\n");
            Object errorDataArray[] = errorDataList.toArray();
            StringBuffer htmlErrorFileStringBuffer = new StringBuffer();
            boolean paperRetWarFile = false;
            boolean electronicRetWarFile = false;
            htmlErrorFileStringBuffer = createHtmlErrorFileHeader(htmlErrorFileStringBuffer, paperRetWarFile, electronicRetWarFile);
            for(int i = 0; i < errorDataArray.length; i++)
            {
                FileStatistics fStatistics = parseDelemitedErrorFile((String)errorDataArray[i], "^");
                htmlErrorFileStringBuffer = createErrorFile(fStatistics, htmlErrorFileStringBuffer, paperRetWarFile, electronicRetWarFile);
            }

            htmlErrorFileStringBuffer = createHtmlErrorFileFooter(htmlErrorFileStringBuffer);
            return htmlErrorFileStringBuffer;
        }
        catch(Exception e)
        {
            logger.error(" Exception in FileGenerator.java generateHtmlErrorFile Method : " + e.toString());
        }
        StringBuffer htmlBuffer = new StringBuffer();
        return htmlBuffer;
    }

    public StringBuffer generateHtmlErrorFile(String errorDataString, boolean appedHtmlErrorFileHeader, boolean appedHtmlErrorFileFooter, boolean paperRetWarFile, String inputFileName, boolean electronicRetWarFile)
    {
        try
        {
            ArrayList errorDataList = parseString(errorDataString, "\n");
            Object errorDataArray[] = errorDataList.toArray();
            StringBuffer htmlErrorFileStringBuffer = new StringBuffer();
            if(appedHtmlErrorFileHeader)
                htmlErrorFileStringBuffer = createHtmlErrorFileHeader(htmlErrorFileStringBuffer, paperRetWarFile, electronicRetWarFile);
            for(int i = 0; i < errorDataArray.length; i++)
                if(paperRetWarFile)
                {
                    FileStatistics fStatistics = parseDelemitedWarFile((String)errorDataArray[i], "^");
                    htmlErrorFileStringBuffer = createErrorFile(fStatistics, htmlErrorFileStringBuffer, paperRetWarFile, electronicRetWarFile);
                } else
                if(electronicRetWarFile)
                {
                    FileStatistics fStatistics = parseDelemitedElecWarFile((String)errorDataArray[i], "^");
                    htmlErrorFileStringBuffer = createErrorFile(fStatistics, htmlErrorFileStringBuffer, paperRetWarFile, electronicRetWarFile);
                } else
                {
                    FileStatistics fStatistics = parseDelemitedErrorFile((String)errorDataArray[i], "^");
                    htmlErrorFileStringBuffer = createErrorFile(fStatistics, htmlErrorFileStringBuffer, paperRetWarFile, electronicRetWarFile);
                }

            if(appedHtmlErrorFileFooter)
                htmlErrorFileStringBuffer = createHtmlErrorFileFooter(htmlErrorFileStringBuffer, inputFileName, electronicRetWarFile);
            return htmlErrorFileStringBuffer;
        }
        catch(Exception e)
        {
            logger.error(" Exception in FileGenerator.java generateHtmlErrorFile Method : " + e.toString());
        }
        StringBuffer htmlBuffer = new StringBuffer();
        return htmlBuffer;
    }

    private StringBuffer setRecordType(FileStatistics fStatistics)
    {
        StringBuffer toReturn = new StringBuffer();
        if(fStatistics.getLine_No().equals("-") && fStatistics.getError_Code().equals("-"))
            toReturn.append("<TD  ALIGN=LEFT width=90> Metering </TD>");
        else
        if(fStatistics.getDeductee_No().equals("NA") && fStatistics.getChallan_No().equals("NA"))
        {
            StructurallyinvalidPAN = true;
            toReturn.append("<TD  ALIGN=LEFT width=90> File Level </TD>");
        } else
        if(!fStatistics.getDeductee_No().equals("-") && (fStatistics.getChallan_No().equals("-") || fStatistics.getChallan_No().equals("NA")))
            toReturn.append("<TD  ALIGN=LEFT width=90> Salary Detail </TD>");
        else
        if(!fStatistics.getDeductee_No().equals("-"))
            toReturn.append("<TD  ALIGN=LEFT width=90> Deductee </TD>");
        else
        if(!fStatistics.getChallan_No().equals("-"))
            toReturn.append("<TD  ALIGN=LEFT width=90> Challan </TD>");
        else
        if(!fStatistics.getError_Code().substring(0, 6).equals("T-FV-1"))
            toReturn.append("<TD  ALIGN=LEFT width=90> Batch </TD>");
        else
        if(!fStatistics.getBatch_No().equals("-") && !fStatistics.get_Upload_Type().equals("NA") && !fStatistics.getError_Code().substring(0, 6).equals("T-FV-1"))
            toReturn.append("<TD  ALIGN=LEFT width=90> Batch </TD>");
        else
            toReturn.append("<TD  ALIGN=LEFT width=90> File Header </TD>");
        return toReturn;
    }

    public String createPANStatFileHeader()
    {
        StringBuffer toReturn = new StringBuffer();
        toReturn.append("<HTML>");
        toReturn.append("<HEAD> <TITLE> TDS/TCS PAN STATISTICS FILE </TITLE></HEAD>");
        toReturn.append("<BODY>");
        return toReturn.toString();
    }

    public FileStatistics getPANStatBatchDetail(String StringToParse, String Seperator, FileStatistics fStatistics)
    {
        logger.info("FileGenerator.java getPANStatBatchDetail() method Called");
        StringTokenizer batch = new StringTokenizer(StringToParse, Seperator);
        for(int counter = 0; batch.hasMoreTokens(); counter++)
        {
            String token = batch.nextToken();
            if(counter == 0)
                fStatistics.setBatch_No(token);
            else
            if(counter == 1)
                fStatistics.setForm_No(token);
            else
            if(counter == 2)
                fStatistics.setTransaction_Type(token);
            else
            if(counter == 3)
                fStatistics.setTAN(token);
            else
            if(counter == 4)
                fStatistics.setLastTAN(token);
            else
            if(counter == 5)
                fStatistics.setPAN_of_TAN(token);
            else
            if(counter == 6)
                fStatistics.setAssmnt_Year(token);
            else
            if(counter == 7)
                fStatistics.setFinancial_Year(token);
            else
            if(counter == 8)
                fStatistics.setPeriodicity(token);
            else
            if(counter == 9)
                fStatistics.setName_of_Deductor(token);
            else
            if(counter == 10)
                fStatistics.setForm_Type(token);
        }

        logger.info("FileGenerator.java getStatisticaFileDetail() method is returning ");
        return fStatistics;
    }

    public String createPANStatTableStructureStart(String batchRecDataForPanStat)
    {
        FileStatistics fStatistics = new FileStatistics();
        getPANStatBatchDetail(batchRecDataForPanStat, "^", fStatistics);
        StringBuffer toReturn = new StringBuffer();
        toReturn.append("<TABLE BORDER=1 BORDERCOLOR=\"000000\" CELLSPACING=0 CELLPADDING=0 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'><TR><TD><BR>");
        toReturn.append("<H3><CENTER> TDS/TCS - PAN STATISTICS - Batch Number " + fStatistics.getBatch_No() + "</CENTER></H3>");
        toReturn.append("<BR>");
        toReturn.append("<TABLE BORDER=.5pt CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
        toReturn.append("<TR>");
        if(fStatistics.getForm_No().equals("27EQ"))
        {
            toReturn.append("<TD width=500 valign=top><B>   Name of Collector\t</B></CENTER></TD>");
            if(!fStatistics.getTransaction_Type().equals("C1") || fStatistics.getTransaction_Type().equals("C1") && fStatistics.getTAN().equals(fStatistics.getLastTAN()))
            {
                toReturn.append("<TD width=250 valign=top><B>   TAN of Collector </B></CENTER></TD>");
            } else
            {
                toReturn.append("<TD width=166 valign=top><B>   TAN of Collector </B></CENTER></TD>");
                toReturn.append("<TD width=168 valign=top><B>   New TAN of Collector* </B></CENTER></TD>");
            }
        } else
        {
            toReturn.append("<TD width=500 valign=top><B>   Name of Deductor </B></CENTER></TD>");
            if(!fStatistics.getTransaction_Type().equals("C1") || fStatistics.getTransaction_Type().equals("C1") && fStatistics.getTAN().equals(fStatistics.getLastTAN()))
            {
                toReturn.append("<TD width=250 valign=top><B>   TAN of Deductor </B></CENTER></TD>");
            } else
            {
                toReturn.append("<TD width=166 valign=top><B>   TAN of Deductor </B></CENTER></TD>");
                toReturn.append("<TD width=168 valign=top><B>   New TAN of Deductor* </B></CENTER></TD>");
            }
        }
        toReturn.append("</TR>");
        toReturn.append("<TR>");
        toReturn.append("<TD  ALIGN=LEFT width=500> " + fStatistics.getName_of_Deductor() + "</TD>");
        if(!fStatistics.getTransaction_Type().equals("C5"))
        {
            if(!fStatistics.getTransaction_Type().equals("C1") || fStatistics.getTransaction_Type().equals("C1") && fStatistics.getTAN().equals(fStatistics.getLastTAN()))
            {
                toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getTAN() + " </TD>");
            } else
            {
                toReturn.append("<TD  ALIGN=LEFT width=166> " + fStatistics.getLastTAN() + " </TD>");
                toReturn.append("<TD  ALIGN=LEFT width=168> " + fStatistics.getTAN() + " </TD>");
            }
        } else
        {
            toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getLastTAN() + " </TD>");
        }
        toReturn.append("</TR>");
        toReturn.append("</TABLE><P>");
        toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
        toReturn.append("<TR>");
        if(fStatistics.getForm_No().equals("27EQ"))
            toReturn.append("<TD width=250 valign=top><B>   PAN of Collector </B></CENTER></TD>");
        else
            toReturn.append("<TD width=250 valign=top><B>   PAN of Deductor </B></CENTER></TD>");
        toReturn.append("<TD valign=top><B>   Form Number  </B></CENTER></TD>");
        toReturn.append("<TD valign=top><B>   Form Type  </B></CENTER></TD>");
        toReturn.append("</TR>");
        toReturn.append("<TR>");
        toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getPAN_of_TAN() + " </TD>");
        toReturn.append("<TD  ALIGN=LEFT width=250> " + fStatistics.getForm_No() + " </TD>");
        if(fStatistics.getForm_Type().equals("SL1"))
            toReturn.append("<TD  ALIGN=LEFT width=250>Salary (Electronic)</TD>");
        else
        if(fStatistics.getForm_Type().equals("SL9"))
            toReturn.append("<TD  ALIGN=LEFT width=250>Salary (Paper)</TD>");
        else
        if(fStatistics.getForm_Type().equals("NS1"))
            toReturn.append("<TD  ALIGN=LEFT width=250>Non-Salary (Electronic)</TD>");
        else
        if(fStatistics.getForm_Type().equals("NS9"))
            toReturn.append("<TD  ALIGN=LEFT width=250>Non-Salary (Paper)</TD>");
        else
        if(fStatistics.getForm_Type().equals("TC1"))
            toReturn.append("<TD  ALIGN=LEFT width=250>TCS (Electronic)</TD>");
        else
        if(fStatistics.getForm_Type().equals("TC9"))
            toReturn.append("<TD  ALIGN=LEFT width=250>TCS (Paper)</TD>");
        toReturn.append("</TR>");
        toReturn.append("</TABLE><P>");
        toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
        toReturn.append("<TR>");
        toReturn.append("<TD valign=top><B>   Assessment Year  </B></CENTER></TD>");
        toReturn.append("<TD valign=top><B>   Financial Year  </B></CENTER></TD>");
        toReturn.append("<TD valign=top><B>   Quarter  </B></CENTER></TD>");
        toReturn.append("<TD valign=top><B>   Upload Type  </B></CENTER></TD>");
        toReturn.append("<TD valign=top><B>   Type of Correction </B></CENTER></TD>");
        toReturn.append("</TR>");
        toReturn.append("<TR>");
        String assmntYear = fStatistics.getAssmnt_Year().substring(0, 4);
        String assmntYear2 = fStatistics.getAssmnt_Year().substring(4, 6);
        toReturn.append("<TD  ALIGN=LEFT width=150>" + assmntYear + "-" + assmntYear2 + " </TD>");
        String finanYear = fStatistics.getFinancial_Year().substring(0, 4);
        String finanYear2 = fStatistics.getFinancial_Year().substring(4, 6);
        toReturn.append("<TD  ALIGN=LEFT width=150>" + finanYear + "-" + finanYear2 + "</TD>");
        toReturn.append("<TD  ALIGN=LEFT width=150>" + fStatistics.getPeriodicity() + "</TD>");
        if(fStatistics.getTransaction_Type().equals("-") || fStatistics.getTransaction_Type().equals("R1"))
            toReturn.append("<TD  ALIGN=LEFT width=160>Regular</TD>");
        else
            toReturn.append("<TD  ALIGN=LEFT width=160>Correction</TD>");
        if(fStatistics.getTransaction_Type().equals("-"))
            toReturn.append("<TD  ALIGN=LEFT width=300>NA</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("C1"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C1-Correction in deductor's(collector's) details and/or statement related details</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("C9"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C9-Correction in challan details (addition of challan/s)</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("C2"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C2-Correction in deductor's(collector's) details and/or statement related and/or challan details</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("C3"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C3-Correction in deductor's(collector's) details and/or statement related particulars and/or challan/deductee details</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("C4"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C4-Correction in salary details of deductees</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("C5"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> C5-Correction in PAN of deductees/parties</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("X"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> X-Challan Cancellation</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("Y"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> Y-Statement Cancellation</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("Z"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> Z-Salary detail replacement</TD>");
        else
        if(fStatistics.getTransaction_Type().equals("Y1"))
            toReturn.append("<TD  ALIGN=LEFT VALIGN=TOP width=300> Y1-Statement Cancellation</TD>");
        toReturn.append("</TR>");
        toReturn.append("</TABLE><BR>");
        toReturn.append("<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=000000 style='border-collapse:collapse; border:none;mso-border-alt:solid windowtext .5pt;mso-padding-alt:0in 5.4pt 0in 5.4pt'>");
        toReturn.append("<H4> List of PAN Deficiency</H4>");
        toReturn.append("<TR>");
        toReturn.append("<TD valign=top style='text-align:right'><B> Line Number </B></TD>");
        if(fStatistics.getForm_No().equals("27EQ"))
            toReturn.append("<TD valign=top style='text-align:right'><B> Party Detail Record Number </B></TD>");
        else
        if(fStatistics.getForm_No().equals("26Q") || fStatistics.getForm_No().equals("27Q"))
            toReturn.append("<TD valign=top style='text-align:right'><B> Deductee Detail Record Number </B></TD>");
        else
            toReturn.append("<TD valign=top style='text-align:right'><B> Deductee / Salary Detail Record Number </B></TD>");
        toReturn.append("<TD valign=top><B> PAN as mentioned in the statement </B></TD>");
        toReturn.append("<TD valign=top><B> Error  Description</B></TD>");
        toReturn.append("</TR>");
        return toReturn.toString();
    }

    public String createPANStatTableStructureEnd(String fileName)
    {
        StringBuffer toReturn = new StringBuffer();
        toReturn.append("</TABLE><BR>");
        toReturn.append("<BR> FVU Version : 2.128 &nbsp;&nbsp; Input File Name : " + fileName);
        toReturn.append("</TABLE></TR></TD><BR>");
        toReturn.append("<span ><br clear=all style='page-break-before:always'></span>");
        toReturn.append("<BR><BR>");
        return toReturn.toString();
    }

    public String createPANStatFileFooter()
    {
        StringBuffer toReturn = new StringBuffer();
        toReturn.append("</TABLE></TD><BR>");
        toReturn.append("<BR><BR><BR>");
        toReturn.append("</BODY>");
        toReturn.append("</HTML>");
        return toReturn.toString();
    }

    static Logger log1 = Logger.getLogger("TDSLogging");
    Calendar cal;
    String tab;
    public boolean StructurallyinvalidPAN;
    public String uploadType;

}