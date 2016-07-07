// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 01/10/2009 11:24:41 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Hash.java

package org.paradyne.model.payroll.incometax;

import java.io.*;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import com.tin.tds.bean.FileStatistics;
import com.tin.tds.util.NewStringTokenizer;
import com.tin.tds.util.Parameters;

// Referenced classes of package com.tin.tds.util:
//            Parameters, NewStringTokenizer

public class Hash
{

    public Hash()
    {
        fvuVersion = null;
        samVersion = null;
        scmVersion = null;
        br = null;
        recNo = 0;
    }

    public int startProcessing(String inputFileName, String outputHashFile, int utilityLevel, int fhFieldCount, int paperReturnIndiFlag, HttpServletResponse res)
    {
        this.inputFileName = inputFileName;
        outputFileName = outputHashFile;
        this.utilityLevel = utilityLevel;
        this.fhFieldCount = fhFieldCount;
        this.paperReturnIndiFlag = paperReturnIndiFlag;
        iErrCode = 0;
        try
        {
        	System.out.println("1");
            File file = new File(inputFileName);
            if(!file.exists())
            {
                iErrCode = 1;
                int i = iErrCode;
                System.out.println("2");
                return i;
            }
            br = new BufferedReader(new FileReader(file));
            recStr = br.readLine();
            if(recStr == null || recStr.length() == 0)
            {
                iErrCode = 2;
                int j = iErrCode;
                System.out.println("3");
                return j;
            }
            tokenizeFileHeader();
            if(iErrCode == 2)
            {
                recNo = 1;
                int k = iErrCode;
                System.out.println("4");
                return k;
            }
            switch(utilityLevel)
            {
            case 0: // '\0'
                processFVU(res);
                break;

            case 1: // '\001'
                processSAM(res);
                break;

            case 2: // '\002'
                processSCM(res);
                break;

            case 3: // '\003'
                processSVU();
                break;
            }
        }
        catch(Exception e)
        {
            recNo = 1;
            iErrCode = 12;
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(br != null)
                    br.close();
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }
        }
        return iErrCode;
    }

    private boolean processFVU(HttpServletResponse res)
    {
        boolean successTDSFile = false;
        boolean successCINFile = false;
        if(samVersion != null || samFLH > 0L || scmVersion != null || scmFLH > 0L)
        {
        	System.out.println("5");
            recNo = 1;
            iErrCode = 2;
            return false;
        }
        if(fvuVersion == null && fvuFLH == 0L)
        {
        	System.out.println("6");
            successTDSFile = processFVU_FileNotHashed(res);
            try
            {
                if(successTDSFile && FileStatistics.getCinUploadFlag() == 1)
                {
                    successCINFile = processCINFile(res);
                    if(successCINFile)
                        return true;
                    try
                    {
                        File f = new File(outputFileName);
                        f.delete();
                    }
                    catch(Exception exception) { }
                    return false;
                } else
                {
                    return successTDSFile;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }
        successTDSFile = processFVU_FileHashed();
        try
        {
            if(successTDSFile && FileStatistics.getCinUploadFlag() == 1)
            {
                successCINFile = processCINFile(res);
                return successCINFile;
            } else
            {
                return successTDSFile;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private boolean processSAM(HttpServletResponse res)
    {
        if(scmVersion != null || scmFLH > 0L || samVersion != null && samFLH > 0L || fvuVersion == null || fvuFLH == 0L)
        {
            recNo = 1;
            iErrCode = 2;
            return false;
        }
        if(samVersion == null && samFLH == 0L)
        {
            return processFVU_FileHashed();
        } else
        {
            recNo = 1;
            iErrCode = 2;
            return false;
        }
    }

    private boolean processSAM_FileHashed()
    {
        try
        {
            recNo = 1;
            if(Parameters.ignoreHashing)
                return true;
            recStr = recStr.substring(0, recStr.lastIndexOf('^', recStr.lastIndexOf('^', recStr.lastIndexOf('^') - 1) - 1));
            fileLevelHC = hashCode(recStr, true);
            if(!Parameters.ignoreRecordLevelHashing)
                while((recStr = br.readLine()) != null) 
                {
                    recNo++;
                    if(recStr.charAt(recStr.length() - 1) == '^')
                    {
                        iErrCode = 8;
                        return false;
                    }
                    try
                    {
                        long rlh = Long.parseLong(recStr.substring(recStr.lastIndexOf('^') + 1, recStr.length()).trim());
                        recStr = recStr.substring(0, recStr.lastIndexOf('^'));
                        if(rlh != hashCode(recStr, true))
                        {
                            iErrCode = 7;
                            return false;
                        }
                        fileLevelHC += (long)recNo * rlh;
                    }
                    catch(NumberFormatException e)
                    {
                        iErrCode = 6;
                        return false;
                    }
                }
            else
                while((recStr = br.readLine()) != null) 
                {
                    recNo++;
                    recStr = recStr.substring(0, recStr.lastIndexOf('^'));
                    fileLevelHC += (long)recNo * hashCode(recStr, true);
                }
            if(fileLevelHC != samFLH)
            {
                iErrCode = 10;
                return false;
            } else
            {
                return true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private boolean processSAM_FileNotHashed()
    {
        BufferedWriter bw = null;
        long pos = 0L;
        try
        {
            recNo = 1;
            bw = new BufferedWriter(new FileWriter(outputFileName));
            recStr = recStr.substring(0, recStr.lastIndexOf('^', recStr.lastIndexOf('^', recStr.lastIndexOf('^') - 1) - 1));
            recStr = recStr + Parameters.samVersion[0] + '^';
            pos = recStr.length();
            fileLevelHC = hashCode(recStr, false);
            bw.write(recStr);
            bw.write(get20digitHashCode(fileLevelHC));
            bw.write("^^");
            bw.write(10);
            while((recStr = br.readLine()) != null) 
            {
                recNo++;
                bw.write(recStr);
                bw.write(10);
                fileLevelHC += (long)recNo * Long.parseLong(recStr.substring(recStr.lastIndexOf('^') + 1, recStr.length()));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bw.close();
            }
            catch(IOException e2)
            {
                e2.printStackTrace();
                return false;
            }
        }
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(outputFileName, "rw");
            raf.seek(pos);
            raf.writeBytes(get20digitHashCode(fileLevelHC));
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                raf.close();
            }
            catch(IOException e2)
            {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean processSCM(HttpServletResponse res)
    {
        if(paperReturnIndiFlag == 2)
            return processSAM(res);
        if(samVersion == null || samFLH == 0L || fvuVersion == null || fvuFLH == 0L || scmVersion != null || scmFLH > 0L)
        {
            recNo = 1;
            iErrCode = 2;
            return false;
        } else
        {
            return processSAM_FileHashed();
        }
    }

    private boolean processSCM_FileNotHashed()
    {
        BufferedWriter bw = null;
        long pos = 0L;
        try
        {
            recNo = 1;
            bw = new BufferedWriter(new FileWriter(outputFileName));
            recStr = recStr.substring(0, recStr.lastIndexOf('^')) + Parameters.scmVersion[0] + '^';
            pos = recStr.length();
            fileLevelHC = hashCode(recStr, false);
            bw.write(recStr);
            bw.write(get20digitHashCode(fileLevelHC));
            bw.write(10);
            while((recStr = br.readLine()) != null) 
            {
                recNo++;
                bw.write(recStr);
                bw.write(10);
                fileLevelHC += (long)recNo * Long.parseLong(recStr.substring(recStr.lastIndexOf('^') + 1, recStr.length()));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bw.close();
            }
            catch(IOException e2)
            {
                e2.printStackTrace();
            }
        }
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(outputFileName, "rw");
            raf.seek(pos);
            raf.writeBytes(get20digitHashCode(fileLevelHC));
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        finally
        {
            try
            {
                raf.close();
            }
            catch(IOException e2)
            {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private boolean processSVU()
    {
        long fvuFileLevelHC = 0L;
        long scmFileLevelHC = 0L;
        boolean isTFC;
        if(recStr.indexOf("^T^") != -1)
            isTFC = true;
        else
            isTFC = false;
        try
        {
            if(!isTFC)
            {
                if(samVersion != null || samFLH > 0L || scmVersion != null || scmFLH > 0L)
                {
                    recNo = 1;
                    iErrCode = 2;
                    return false;
                }
            } else
            if(scmVersion == null || scmFLH == 0L)
            {
                recNo = 1;
                iErrCode = 2;
                return false;
            }
            recNo = 1;
            if(!Parameters.ignoreVersioning)
            {
                if(!isValidFVUVersion(fvuVersion))
                {
                    iErrCode = 3;
                    return false;
                }
                if(isTFC)
                {
                    if(!isValidSAMVersion(samVersion))
                    {
                        iErrCode = 4;
                        return false;
                    }
                    if(!isValidSCMVersion(scmVersion))
                    {
                        iErrCode = 5;
                        return false;
                    }
                }
            }
            if(Parameters.ignoreHashing)
                return true;
            int k = recStr.lastIndexOf('^');
            recStr = recStr.substring(0, k);
            scmFileLevelHC = hashCode(recStr, true);
            k = recStr.lastIndexOf('^', k - 1);
            k = recStr.lastIndexOf('^', k - 1);
            recStr = recStr.substring(0, k);
            k = recStr.lastIndexOf('^', k - 1);
            k = recStr.lastIndexOf('^', k - 1);
            recStr = recStr.substring(0, k);
            fvuFileLevelHC = hashCode(recStr, true);
            fileLevelHC = 0L;
            if(Parameters.ignoreRecordLevelHashing)
                while((recStr = br.readLine()) != null) 
                {
                    recNo++;
                    k = recStr.lastIndexOf('^');
                    long recLevelHC;
                    try
                    {
                        recLevelHC = Long.parseLong(recStr.substring(k + 1, recStr.length()));
                    }
                    catch(NumberFormatException e)
                    {
                        iErrCode = 6;
                        return false;
                    }
                    fileLevelHC += (long)recNo * recLevelHC;
                }
            else
                while((recStr = br.readLine()) != null) 
                {
                    recNo++;
                    k = recStr.lastIndexOf('^');
                    long recLevelHC;
                    try
                    {
                        recLevelHC = Long.parseLong(recStr.substring(k + 1, recStr.length()));
                    }
                    catch(NumberFormatException e)
                    {
                        iErrCode = 6;
                        return false;
                    }
                    recStr = recStr.substring(0, k);
                    long rlh = hashCode(recStr, true);
                    if(rlh != recLevelHC)
                    {
                        iErrCode = 7;
                        return false;
                    }
                    fileLevelHC += (long)recNo * recLevelHC;
                }
            fvuFileLevelHC += fileLevelHC;
            if(isTFC)
            {
                scmFileLevelHC += fileLevelHC;
                if(scmFileLevelHC != scmFLH)
                {
                    iErrCode = 11;
                    return false;
                }
            } else
            if(fvuFileLevelHC != fvuFLH)
            {
                iErrCode = 9;
                return false;
            }
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private boolean processFVU_FileNotHashed(HttpServletResponse res)
    {
        long rlh = 0L;
        recNo = 1;
        boolean retVal = true;
        BufferedWriter hf = null;
        RandomAccessFile raf = null;
        try
        {
        	System.out.println("7");
            hf = new BufferedWriter(new FileWriter(outputFileName));
            recStr = getFileHeader();
            hf.write(recStr);
            hf.write(94);
            fileLevelHC = hashCode(recStr, true);
            hf.write(get20digitHashCode(fileLevelHC));
            hf.write("^^^^");
            hf.write(10);
            while((recStr = br.readLine()) != null) 
            {
                recNo++;
                if(recStr.charAt(recStr.length() - 1) != '^')
                {
                    iErrCode = 8;
                    retVal = false;
                    break;
                }
                System.out.println("8");
                hf.write(recStr);
                rlh = hashCode(recStr, false);
                fileLevelHC += (long)recNo * rlh;
                hf.write(get20digitHashCode(rlh));
                hf.write(10);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(hf != null)
                {
                    hf.close();
                    if(!retVal)
                    {
                        File f = new File(outputFileName);
                        f.delete();
                    }
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        if(retVal)
            try
            {
                raf = new RandomAccessFile(outputFileName, "rw");
                raf.seek(fhString.length() + 3 + Parameters.maxHashCodeLength + fvuVersion.length());
                raf.writeBytes(get20digitHashCode(fileLevelHC));
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }
            finally
            {
                if(raf != null)
                    try
                    {
                        raf.close();
                    }
                    catch(IOException e2)
                    {
                        e2.printStackTrace();
                    }
            }
        return retVal;
    }

    private boolean processFVU_FileHashed()
    {
        try
        {
        	System.out.println("9");
            recNo = 1;
            if(!Parameters.ignoreHashing && !Parameters.ignoreRecordLevelHashing && fhHC != hashCode(fhString, true))
            {
                iErrCode = 6;
                return false;
            }
            if((utilityLevel == 0 || utilityLevel == 3) && !Parameters.ignoreVersioning && !isValidFVUVersion(fvuVersion))
            {
                iErrCode = 3;
                return false;
            }
            if(Parameters.ignoreHashing)
                return true;
            recStr = recStr.substring(0, recStr.lastIndexOf('^', recStr.lastIndexOf('^', recStr.lastIndexOf('^', recStr.lastIndexOf('^', recStr.lastIndexOf('^') - 1) - 1) - 1) - 1));
            fileLevelHC = hashCode(recStr, true);
            if(!Parameters.ignoreRecordLevelHashing)
                while((recStr = br.readLine()) != null) 
                {
                    recNo++;
                    if(recStr.charAt(recStr.length() - 1) == '^')
                    {
                        iErrCode = 8;
                        return false;
                    }
                    try
                    {
                        long rlh = Long.parseLong(recStr.substring(recStr.lastIndexOf('^') + 1, recStr.length()).trim());
                        recStr = recStr.substring(0, recStr.lastIndexOf('^'));
                        if(rlh != hashCode(recStr, true))
                        {
                            iErrCode = 7;
                            return false;
                        }
                        fileLevelHC += (long)recNo * rlh;
                    }
                    catch(NumberFormatException e)
                    {
                        iErrCode = 6;
                        return false;
                    }
                }
            else
                while((recStr = br.readLine()) != null) 
                {
                    recNo++;
                    recStr = recStr.substring(0, recStr.lastIndexOf('^'));
                    fileLevelHC += (long)recNo * hashCode(recStr, true);
                }
            if(fileLevelHC != fvuFLH)
            {
                iErrCode = 9;
                return false;
            } else
            {
                return true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public long hashCode(String recStr, boolean includeCaret)
    {
        long hc = 0L;
        int i;
        for(i = 0; i < recStr.length(); i++)
            hc += recStr.charAt(i) * (i + 1);

        if(includeCaret)
            hc += (i + 1) * 94;
        return hc;
    }

    public String get20digitHashCode(long hashCode)
    {
        String hashCodeStr = Long.toString(hashCode);
        StringBuffer sb = new StringBuffer();
        for(int i = hashCodeStr.length(); i < Parameters.maxHashCodeLength; i++)
            sb.append('0');

        sb.append(hashCodeStr);
        return sb.toString();
    }

    private String getFileHeader()
    {
    	System.out.println("10");
        StringBuffer sb = new StringBuffer(fhString);
        sb.append('^');
        sb.append(get20digitHashCode(hashCode(fhString, true)));
        sb.append('^');
        fvuVersion = Parameters.fvuVersion[0];
        sb.append(fvuVersion);
        return sb.toString();
    }

    private boolean isValidFVUVersion(String fvu)
    {
    	System.out.println("11");
        if(fvu == null)
            return false;
        for(int i = 0; i < Parameters.fvuVersion.length; i++)
            if(Parameters.fvuVersion[i].trim().equals(fvu.trim()))
                return true;

        return false;
    }

    private boolean isValidSAMVersion(String sam)
    {
        if(sam == null)
            return false;
        for(int i = 0; i < Parameters.samVersion.length; i++)
            if(Parameters.samVersion[i].trim().equals(sam.trim()))
                return true;

        return false;
    }

    private boolean isValidSCMVersion(String scm)
    {
        if(scm == null)
            return false;
        for(int i = 0; i < Parameters.scmVersion.length; i++)
            if(Parameters.scmVersion[i].trim().equals(scm.trim()))
                return true;

        return false;
    }

    private boolean processCINFile(HttpServletResponse res)
        throws IOException
    {
        String cinRecStr = "";
        String cinCheckStr = "";
        String cinHashString = "";
        File cinFile = new File(FileStatistics.getCinFileName());
        if(!cinFile.exists())
        {
            iErrCode = 14;
            return false;
        }
        BufferedReader cinbr = null;
        BufferedReader cinCheckBr = null;
        String challanCount = "";
        try
        {
            cinbr = new BufferedReader(new FileReader(cinFile));
            cinCheckBr = new BufferedReader(new FileReader(cinFile));
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        int cinrecNo = 0;
        long cinrlh = 0L;
        long cinFileLevelHC = 0L;
        cinCheckStr = cinCheckBr.readLine();
        if(cinCheckStr == null || cinCheckStr.length() == 0)
        {
            iErrCode = 15;
            return false;
        }
        StringTokenizer checkToken = new StringTokenizer(cinCheckStr, "^", true);
        if(checkToken.countTokens() != 3)
        {
            iErrCode = 13;
            return false;
        }
        String cinFilehashFromFile = "";
        try
        {
            while((cinRecStr = cinbr.readLine()) != null) 
            {
                if(cinRecStr.trim().equals(""))
                {
                    iErrCode = 13;
                    return false;
                }
                cinrecNo++;
                int k = cinRecStr.lastIndexOf("^");
                if(cinrecNo == 1)
                {
                    cinHashString = cinRecStr.substring(0, k);
                    challanCount = cinRecStr.substring(0, k);
                    cinFilehashFromFile = cinRecStr.substring(k + 1, cinRecStr.length());
                } else
                {
                    cinHashString = cinRecStr.substring(0, cinRecStr.length());
                }
                cinrlh = hashCode(cinHashString, false);
                cinFileLevelHC += (long)cinrecNo * cinrlh;
            }
            if(!cinFilehashFromFile.equals(get20digitHashCode(cinFileLevelHC)))
            {
                iErrCode = 13;
                return false;
            }
            try
            {
                if(Integer.parseInt(challanCount) != cinrecNo - 1)
                {
                    iErrCode = 16;
                    return false;
                } else
                {
                    return true;
                }
            }
            catch(NumberFormatException e)
            {
                e.printStackTrace();
            }
            iErrCode = 16;
            cinbr.close();
            cinCheckBr.close();
        }
        catch(IOException ioexception) { }
        return true;
    }

    private void tokenizeFileHeader()
        throws Exception
    {
        StringBuffer sb = null;
        try
        {
        	System.out.println("12");
            NewStringTokenizer st = new NewStringTokenizer(recStr, "^");
            sb = new StringBuffer();
            String tokstr = st.nextToken();
            int tok = 0;
            int fhHCIndex = fhFieldCount + 1;
            int fvuVersionIndex = fhHCIndex + 1;
            int fvuFLHIndex = fvuVersionIndex + 1;
            int samVersionIndex = fvuFLHIndex + 1;
            int samFLHIndex = samVersionIndex + 1;
            int scmVersionIndex = samFLHIndex + 1;
            int scmFLHIndex = scmVersionIndex + 1;
            for(; tokstr != null; tokstr = st.nextToken())
            {
                if(++tok < fhHCIndex)
                {
                    sb.append(tokstr);
                    if(tok != fhFieldCount)
                        sb.append('^');
                    if(tok != 2 || tokstr.equals("FH"))
                        continue;
                    iErrCode = 2;
                    break;
                }
                if(tok == fhHCIndex)
                    try
                    {
                        fhHC = Long.parseLong(tokstr);
                    }
                    catch(NumberFormatException e) { }
                else
                if(tok == fvuVersionIndex && tokstr.length() > 0)
                    fvuVersion = tokstr;
                else
                if(tok == fvuFLHIndex)
                    try
                    {
                        fvuFLH = Long.parseLong(tokstr);
                    }
                    catch(NumberFormatException e) { }
                else
                if(tok == samVersionIndex && tokstr.length() > 0)
                    samVersion = tokstr;
                else
                if(tok == samFLHIndex)
                    try
                    {
                        samFLH = Long.parseLong(tokstr);
                    }
                    catch(NumberFormatException e) { }
                else
                if(tok == scmVersionIndex && tokstr.length() > 0)
                    scmVersion = tokstr;
                else
                if(tok == scmFLHIndex)
                    try
                    {
                        scmFLH = Long.parseLong(tokstr);
                    }
                    catch(NumberFormatException numberformatexception) { }
            }

        }
        catch(Exception e)
        {
            iErrCode = 2;
        }
        finally
        {
            if(sb.length() > 0)
                fhString = sb.toString();
        }
    }

    public int getRecordNumber()
    {
        return recNo;
    }

    private String fhString;
    private long fileLevelHC;
    private long fhHC;
    private long fvuFLH;
    private long samFLH;
    private long scmFLH;
    private String fvuVersion;
    private String samVersion;
    private String scmVersion;
    private int utilityLevel;
    private int paperReturnIndiFlag;
    private String inputFileName;
    private String outputFileName;
    private String recStr;
    private int fhFieldCount;
    private BufferedReader br;
    private int iErrCode;
    public static final int FVU = 0;
    public static final int SAM = 1;
    public static final int SCM = 2;
    public static final int SVU = 3;
    private int recNo;
}