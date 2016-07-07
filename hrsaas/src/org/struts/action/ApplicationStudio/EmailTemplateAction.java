package org.struts.action.ApplicationStudio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.paradyne.bean.ApplicationStudio.EmailTemplate;
import org.paradyne.model.ApplicationStudio.EmailTemplateModel;
import org.struts.lib.ParaActionSupport;

public class EmailTemplateAction extends ParaActionSupport
{
  private static final long serialVersionUID = 1L;
  static Logger logger = Logger.getLogger(ParaActionSupport.class);

  private EmailTemplate template = null;

  public void prepare_local()
    throws Exception
  {
    this.template = new EmailTemplate();
    this.template.setMenuCode(733);
  }

  public Object getModel()
  {
    return this.template;
  }

  public EmailTemplate getTemplate()
  {
    return this.template;
  }

  public void setTemplate(EmailTemplate template)
  {
    this.template = template;
  }

  public String f9action()
    throws Exception
  {
    String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_EMAILTEMPLATE_HDR ORDER BY TEMPLATE_ID";
    String[] headers = { getMessage("template.code"), getMessage("template.name") };
    String[] headerWidth = { "20", "50" };
    String[] fieldNames = { "tempCode", "tempName" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "true";
    String submitToMethod = "EmailTemplate_setTemplate.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String setTemplate()
  {
    EmailTemplateModel model = new EmailTemplateModel();
    model.initiate(this.context, this.session);
    String tempCode = this.template.getTempCode();
    model.setTemplateData(request, this.template, "D");
   /* Object[][] defaultBodyTxt = model.setBodyText(tempCode);
    this.request.setAttribute("defaultBodyText", String.valueOf(defaultBodyTxt[0][0]));
    this.template.setHtmlcode(String.valueOf(defaultBodyTxt[0][0]));*/
    model.terminate();
    return "success";
  }

  public String deleteDtl()
    throws Exception
  {
    String[] code = this.request.getParameterValues("hdeleteOp");
    String[] queryName = this.request.getParameterValues("queryName");
    String[] srNo = this.request.getParameterValues("srNo");
    String[] field = this.request.getParameterValues("field");
    boolean result = false;
    EmailTemplateModel model = new EmailTemplateModel();
    model.initiate(this.context, this.session);
    result = model.delDtl(this.template, code, queryName);
    if (result) {
      addActionMessage(getMessage("delete"));
    }
    model.terminate();
    this.template.setHiddenEdit("");
    this.template.setHdeleteOp("");
    return "success";
  }

  public String update()
    throws Exception
  {
    EmailTemplateModel model = new EmailTemplateModel();
    model.initiate(this.context, this.session);
    String tempCodePara= request.getParameter("EditorDefault");
    //String tempCode = this.template.getTempCode();
    String[] srNo = this.request.getParameterValues("srNo");
    String[] queryName = this.request.getParameterValues("queryName");
    String[] queryType = this.request.getParameterValues("qtype");
    String[] noqueryparameter = this.request.getParameterValues("ittrnoqueryparameter");
    String[] queryparameter = this.request.getParameterValues("ittrqueryparameter");
    this.template.setHtmlcode(String.valueOf(tempCodePara));
    boolean result = false;
    if ((this.template.getTempCode() != "") && (!this.template.getTempCode().equals(null)) && 
      (!this.template.getTempCode().equals(""))) {
      logger.info("Template name ------------" + this.template.getTempName());
      result = model.update(this.template, srNo, queryName, queryType, noqueryparameter, queryparameter);
      model.setTemplateData(request, this.template, "D");
    }
    else {
      result = model.save(this.template, srNo, queryName, queryType, noqueryparameter, queryparameter);
    }
    /*Object[][] defaultBodyTxt = model.setBodyText(tempCode);
    this.request.setAttribute("defaultBodyText", String.valueOf(defaultBodyTxt[0][0]));*/
    model.terminate();
    if (result)
      addActionMessage(getMessage("save"));
    else {
      addActionMessage(getMessage("save.error"));
    }
    return "success";
  }

  public String reset()
    throws Exception
  {
    try
    {
      this.template.setHtmlcode("");
      this.template.setTempCode("");
      this.template.setTempContent("");
      this.template.setField("");
      this.template.setTemplateId("");
      this.template.setTempName("");
      this.template.setFromMailId("");
      this.template.setToMailId("");
      this.template.setSubject("");
      this.template.setQuerytype("");
      this.template.setQName("");
      this.template.setModuleCode("");
      this.template.setModuleName("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String addTableList() {
    return "";
  }

  public String add()
  {
    logger.info("Query Name=========" + this.template.getQName());
    String tempCode = this.template.getTempCode();
    String[] srNo = this.request.getParameterValues("srNo");
    String[] queryName = this.request.getParameterValues("queryName");
    String[] queryType = this.request.getParameterValues("qtype");
    String[] noqueryparameter = this.request.getParameterValues("ittrnoqueryparameter");
    String[] queryparameter = this.request.getParameterValues("ittrqueryparameter");
    String hiddenEdit = this.request.getParameter("hiddenEdit");
    String[] field = this.request.getParameterValues("field");
    EmailTemplateModel model = new EmailTemplateModel();
    model.initiate(this.context, this.session);
    String str = "";

    Object[][] defaultBodyTxt = model.setBodyText(tempCode);
    if(defaultBodyTxt != null && defaultBodyTxt.length >0){
    	this.request.setAttribute("defaultBodyText", String.valueOf(defaultBodyTxt[0][0]));
    	this.template.setHtmlcode(String.valueOf(defaultBodyTxt[0][0]));
    }    
    try
    {
      if (this.template.getHiddenEdit().equals(""))
      {
        EmailTemplate bean = model.addItem(this.template, srNo, queryName, queryType, 1, noqueryparameter, queryparameter);
        ArrayList al = bean.getList();
        String[] srNo1 = new String[al.size()];
        String[] queryName1 = new String[al.size()];
        String[] queryType1 = new String[al.size()];
        int counter = 0;
        for (Iterator iterator = al.iterator(); iterator.hasNext(); ) {
          EmailTemplate name = (EmailTemplate)iterator.next();
          srNo1[counter] = name.getSrNo();
          queryName1[counter] = name.getQueryName();
          queryType1[counter] = name.getQtype();
          counter++;
        }
        str = model.createTempList(this.template, srNo1, queryName1, queryType1, field);
      }
      else
      {
        EmailTemplate bean = model.moditem(this.template, srNo, queryName, queryType, 1, noqueryparameter, queryparameter);
        ArrayList al = bean.getList();
        String[] srNo1 = new String[al.size()];
        String[] queryName1 = new String[al.size()];
        String[] queryType1 = new String[al.size()];
        int counter = 0;
        for (Iterator iterator = al.iterator(); iterator.hasNext(); ) {
          EmailTemplate name = (EmailTemplate)iterator.next();
          srNo1[counter] = name.getSrNo();
          queryName1[counter] = name.getQueryName();
          queryType1[counter] = name.getQtype();
          counter++;
        }
        str = model.createTempList(this.template, srNo1, queryName1, queryType1, field);
      }
      addActionMessage(str);
      model.terminate();
      this.request.setAttribute("value", this.template.getQuerytype());
      this.template.setQName("");
      this.template.setSubcode("");
      this.template.setHiddenEdit("");
      this.template.setQuerytype("");
      this.template.setNoqueryparameter("");
      this.template.setQueryparameter("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String editQuery()
  {
    try
    {
      String[] srNo = this.request.getParameterValues("srNo");
      String[] queryName = this.request.getParameterValues("queryName");
      String[] field = this.request.getParameterValues("field");
      String[] queryType = this.request.getParameterValues("qtype");
      String[] noqueryparameter = this.request.getParameterValues("ittrnoqueryparameter");
      String[] queryparameter = this.request.getParameterValues("ittrqueryparameter");
      String tempCode = this.template.getTempCode();
      EmailTemplateModel model = new EmailTemplateModel();
      model.initiate(this.context, this.session);
      int rowEdited = 0;
      rowEdited = Integer.parseInt(this.template.getHiddenEdit()) - 1;
      String editQueryName = queryName[rowEdited];
      String editQueryType = queryType[rowEdited];
      String editNoQueryParm = noqueryparameter[rowEdited];
      String editQueryParm = queryparameter[rowEdited];
      this.template.setQName(editQueryName);
      this.template.setQuerytype(editQueryType);
      this.template.setNoqueryparameter(editNoQueryParm);
      this.template.setQueryparameter(editQueryParm);

      Object[][] defaultBodyTxt = model.setBodyText(tempCode);
      if(defaultBodyTxt!= null && defaultBodyTxt.length>0){
    	  this.request.setAttribute("defaultBodyText", String.valueOf(defaultBodyTxt[0][0]));
    	  this.template.setHtmlcode(String.valueOf(defaultBodyTxt[0][0]));
      }

      EmailTemplate bean = model.moditem(this.template, srNo, queryName, queryType, 1, noqueryparameter, queryparameter);
      ArrayList al = bean.getList();
      String[] srNo1 = new String[al.size()];
      String[] queryName1 = new String[al.size()];
      String[] queryType1 = new String[al.size()];
      int counter = 0;
      for (Iterator iterator = al.iterator(); iterator.hasNext(); ) {
        EmailTemplate name = (EmailTemplate)iterator.next();
        srNo1[counter] = name.getSrNo();
        queryName1[counter] = name.getQueryName();
        queryType1[counter] = name.getQtype();
        counter++;
      }
      model.createTempList(this.template, srNo1, queryName1, queryType1, field);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String deleteQuery() {
    try {
      String tempCode = this.template.getTempCode();
      String[] srNo = this.request.getParameterValues("srNo");
      String[] queryName = this.request.getParameterValues("queryName");
      String[] field = this.request.getParameterValues("field");
      String[] noqueryparameter = this.request.getParameterValues("ittrnoqueryparameter");
      String[] queryparameter = this.request.getParameterValues("ittrqueryparameter");
      EmailTemplateModel model = new EmailTemplateModel();
      model.initiate(this.context, this.session);
      boolean result = model.deleteQuery(srNo, queryName, this.template, noqueryparameter, queryparameter);

      if (result) {
        addActionMessage(getMessage("delete"));
      }

      Object[][] defaultBodyTxt = model.setBodyText(tempCode);
      if(defaultBodyTxt != null && defaultBodyTxt.length >0){
    	  this.request.setAttribute("defaultBodyText", String.valueOf(defaultBodyTxt[0][0]));
    	  this.template.setHtmlcode(String.valueOf(defaultBodyTxt[0][0]));
      }

      model.createTempListForDelete(this.template, srNo, queryName, field);
      this.template.setQueryName("");
      this.template.setHiddenEdit("");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String f9ModuleName() throws Exception
  {
    String query = " SELECT MODULE_CODE, MODULE_NAME FROM HRMS_MODULE order by MODULE_CODE ";
    String[] headers = { "Module Code", getMessage("modName") };

    String[] headerWidth = { "25", "50" };

    String[] fieldNames = { "moduleCode", "moduleName" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String getTemplateDetails()
  {
    EmailTemplateModel model = new EmailTemplateModel();
    model.initiate(this.context, this.session);
    String templateId = this.template.getTempCode();
    try {
      String dtlQuery = "SELECT QUERY_STRING, QUERY_TYPE FROM HRMS_EMAILTEMPLATE_DTL WHERE TEMPLATE_ID =" + 
        templateId + 
        " ORDER BY QUERY_ID";
      Object[][] dataObj = model.getSqlModel().getSingleResult(dtlQuery);
      int noOfQueryTxts = dataObj.length;
      if ((dataObj != null) && (dataObj.length > 0))
        createTemplateToDeploy(templateId, noOfQueryTxts, dataObj);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public void createTemplateToDeploy(String templateId, int noOfQueryTxts, Object[][] dataObj) {
    boolean success = false;
    String templateCode = templateId;

    String mainDirName = "EmailTemplate";
    int noOfTxt = noOfQueryTxts;
    EmailTemplateModel model = new EmailTemplateModel();
    model.initiate(this.context, this.session);
    try
    {
      String mainDir = "C:/Documents and Settings/AA0491/Desktop/" + mainDirName;
      File mainFolder = new File(mainDir);

      if (!mainFolder.exists()) {
        success = new File(mainDir).mkdir();
        System.out.println(">>>>>>>>>>>>>> Directory: " + mainDir + " created");
      }

      String strDirectory = mainDir + "/" + templateCode;
      File folder = new File(strDirectory);
      if (!folder.exists()) {
        success = new File(strDirectory).mkdir();
        System.out.println(">>>>>>>>>>>>>> Directory: " + strDirectory + " created");
      }

      String tempCodeDirectoy = strDirectory + "/queries";
      File tempCodefolder = new File(tempCodeDirectoy);
      String textFileContent = "";
      String dtlDataQuery = "SELECT QUERY_STRING FROM HRMS_EMAILTEMPLATE_DTL WHERE TEMPLATE_ID =" + 
        templateId + 
        " ORDER BY QUERY_ID";
      Object[][] dtlDataObj = model.getSqlModel().getSingleResult(dtlDataQuery);
      if ((dtlDataObj != null) && (dtlDataObj.length > 0)) {
        if (!tempCodefolder.exists()) {
          success = new File(tempCodeDirectoy).mkdir();
          System.out.println(">>>>>>>>>>>>>> Directory: " + tempCodeDirectoy + 
            " created");
          for (int i = 1; i <= noOfTxt; i++) {
            File txtFile = new File(tempCodeDirectoy + "/" + i + ".txt");
            if (!txtFile.exists()) {
              FileWriter fstream = new FileWriter(txtFile);

              System.out.println(">>>>>>>>>>>>>> File: " + i + ".txt created");
              BufferedWriter out = new BufferedWriter(fstream);

              textFileContent = String.valueOf(dtlDataObj[(i - 1)][0]);

              out.write(textFileContent);

              out.close();
            }
          }
        }
        else
        {
          for (int i = 1; i <= noOfTxt; i++) {
            File txtFile = new File(tempCodeDirectoy + "/" + i + ".txt");

            if (!txtFile.exists()) {
              FileWriter fstream = new FileWriter(txtFile);

              System.out.println(">>>>>>>>>>>>>> File: " + i + ".txt created");
              BufferedWriter out = new BufferedWriter(fstream);

              textFileContent = String.valueOf(dtlDataObj[(i - 1)][0]);

              out.write(textFileContent);

              out.close();
            }
          }

        }

      }

      File templateFile = new File(strDirectory + "/" + templateCode + ".txt");
      String templateContent = "";
      String tempBodyQuery = "SELECT TEMPLATE_DEFAULT_BODY FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_ID =" + 
        templateId;
      Object[][] bodyObj = model.getSqlModel().getSingleResult(tempBodyQuery);
      if ((bodyObj != null) && (bodyObj.length > 0)) {
        templateContent = String.valueOf(bodyObj[0][0]);
      }
      boolean isDeleteFile = false;
      if (!templateFile.exists())
      {
        FileWriter fstream = new FileWriter(templateFile);
        System.out.println(">>>>>>>>>>>>>> File: " + templateId + ".txt created");

        BufferedWriter out = new BufferedWriter(fstream);
        out.write(templateContent);

        out.close();
      }
      else {
        isDeleteFile = templateFile.delete();
        if (isDeleteFile) {
          FileWriter fstream = new FileWriter(templateFile);
          System.out.println(">>>>>>>>>>>>>> File: " + templateId + ".txt created");

          BufferedWriter out = new BufferedWriter(fstream);
          out.write(templateContent);

          out.close();
        } else {
          System.out.println("could not write to a file");
        }
      }

      File scriptFile = new File(strDirectory + "/SCRIPT.txt");
      String content = scriptContent(templateCode, noOfTxt, dataObj, mainDirName);
      boolean isDelete = false;
      if (!scriptFile.exists())
      {
        FileWriter fstream = new FileWriter(scriptFile);
        System.out.println(">>>>>>>>>>>>>> File: SCRIPT.txt created");

        BufferedWriter out = new BufferedWriter(fstream);
        out.write(content);

        out.close();
      }
      else {
        isDelete = scriptFile.delete();
        if (isDelete) {
          FileWriter fstream = new FileWriter(scriptFile);
          System.out.println("File: script.txt created");

          BufferedWriter out = new BufferedWriter(fstream);
          out.write(content);

          out.close();
        } else {
          System.out.println("could not write to a file");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String scriptContent(String templateCode, int noOfTxt, Object[][] dataObj, String mainDirName) {
    EmailTemplateModel model = new EmailTemplateModel();
    model.initiate(this.context, this.session);

    String content = "DELETE FROM XXX.HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_ID=" + 
      templateCode + 
      ";\n" + 
      "DELETE FROM XXX.HRMS_EMAILTEMPLATE_DTL WHERE TEMPLATE_ID=" + 
      templateCode + ";";

    String hdrDataQuery = "SELECT TO_CHAR(TEMPLATE_DATE,'DD-MM-YYYY'), TEMPLATE_NAME, TEMPLATE_DEFAULT_FROM_MAIL, TEMPLATE_DEFAULT_TO_MAIL, TEMPLATE_DEFAULT_SUBJECT  FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_ID =" + 
      templateCode;
    Object[][] hdrDataObj = model.getSqlModel().getSingleResult(hdrDataQuery);
    if ((hdrDataObj != null) && (hdrDataObj.length > 0)) {
      String insertHDRQuery = "INSERT INTO XXX.HRMS_EMAILTEMPLATE_HDR(TEMPLATE_ID,TEMPLATE_DATE,TEMPLATE_NAME,TEMPLATE_BODY,\nTEMPLATE_DEFAULT_BODY,TEMPLATE_FROM_MAIL,TEMPLATE_TO_MAIL,TEMPLATE_SUBJECT,TEMPLATE_DEFAULT_FROM_MAIL,TEMPLATE_DEFAULT_TO_MAIL,TEMPLATE_DEFAULT_SUBJECT)\nVALUES (" + 
        templateCode + 
        "," + 
        "TO_Date( '" + 
        String.valueOf(hdrDataObj[0][0]) + 
        "', 'DD-MM-YYYY')" + 
        "," + 
        "'" + 
        String.valueOf(hdrDataObj[0][1]) + 
        "'" + 
        "," + 
        "NULL" + 
        "," + 
        "#CLOB#E:\\DATABASE-QUERY\\DataBaseLogin\\" + 
        mainDirName + 
        "\\" + 
        templateCode + 
        "\\" + 
        templateCode + 
        ".txt#CLOB#" + 
        "," + 
        "NULL" + 
        "," + 
        "NULL" + 
        "," + 
        "NULL" + 
        "," + 
        "'" + 
        String.valueOf(hdrDataObj[0][2]) + 
        "'" + 
        "," + 
        "'" + 
        String.valueOf(hdrDataObj[0][3]) + 
        "'" + 
        "," + 
        "'" + 
        String.valueOf(hdrDataObj[0][4]) + "'" + ");";
      String insertDTLQuery = "";
      for (int i = 1; i <= noOfTxt; i++) {
        insertDTLQuery = insertDTLQuery + "INSERT INTO XXX.HRMS_EMAILTEMPLATE_DTL(QUERY_ID,QUERY_STRING,TEMPLATE_ID,QUERY_TYPE)\nVALUES (" + 
          i + 
          ", #CLOB#E:\\DATABASE-QUERY\\DataBaseLogin\\" + 
          mainDirName + 
          "\\" + 
          templateCode + 
          "\\queries\\" + 
          i + 
          ".txt#CLOB#," + 
          templateCode + 
          "," + 
          "'" + 
          String.valueOf(dataObj[(i - 1)][1]) + "'" + ");\n";
      }
      content = content + "\n\n" + insertHDRQuery + "\n\n" + insertDTLQuery;
    }
    return content;
  }
}