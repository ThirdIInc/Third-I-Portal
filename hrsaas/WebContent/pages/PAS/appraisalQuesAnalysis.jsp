<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalQuesAnalysis" validate="true" id="paraFrm"
	theme="simple">
	
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
   
    <s:hidden name="frmDate"/>
	<s:hidden name="toDate"/>
	<s:hidden name='apprPeriod'></s:hidden>
   
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Question Analysis</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    
    
  		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"><input type="button" class="token"
						onclick="return callQuesReport();" value="Report"  />

					<input type='button' class="reset" onclick="return callReset();"
						theme="simple" value="Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
              
              
              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="appraisal.code"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font> :</td>
			  <td width="30%" colspan="1" height="20"><s:textfield name="apprCode" size="20" maxlength="25" readonly="true" /><s:hidden name="apprId" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraisalQuesAnalysis_f9AppCal.action');"></td>
			  <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="section"  id="section" ondblclick="callShowDiv(this);"><%=label.get("section")%></label> :</td>
			  <td width="30%" colspan="1" height="20"><s:textfield name="sectionName" size="20" maxlength="25" readonly="true" /><s:hidden name="sectionId" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraisalQuesAnalysis_f9Section.action');"></td>
      
            </tr>
            
            <tr>
              
               <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="question"  id="question" ondblclick="callShowDiv(this);"><%=label.get("question")%></label> <font color="red">*</font> :</td>
			   <td width="80%" colspan="3" height="20" ><s:textarea name="questionDesc" readonly="true" cols="75" rows="2" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callQuestion();"><s:hidden name='questionCategory'/><s:hidden name='questionCode'/></td> 
           </tr>
           
           <tr>
              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="report.type"  id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font> :</td>
			  <td width="80%" colspan="3" height="20"><s:select theme="simple" name="reportType" cssStyle="width:155" list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>
            </tr>
           
           
          
            </table></td>
          </tr>
      </table></td>
    </tr>
	</table>
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
function callQuestion(){
	if(document.getElementById('paraFrm_apprCode').value==''){
		alert('Please select '+document.getElementById('appraisal.code').innerHTML);
		document.getElementById('paraFrm_apprCode').focus();
		return false;
		
	}else{
		callsF9(500,325,'AppraisalQuesAnalysis_f9AppQues.action')
	}
}

function callReset(){
	
	document.getElementById('paraFrm_apprCode').value="";
	document.getElementById('paraFrm_frmDate').value="";
	document.getElementById('paraFrm_toDate').value="";
	document.getElementById('paraFrm_apprId').value="";
	document.getElementById('paraFrm_questionDesc').value="";
	document.getElementById('paraFrm_questionCode').value="";
	document.getElementById('paraFrm_questionCategory').value="";
	document.getElementById('paraFrm_sectionName').value="";
	document.getElementById('paraFrm_sectionId').value="";
	document.getElementById('paraFrm_reportType').value="";
	
}
function callQuesReport(){
	var fields =["paraFrm_apprCode"];
	var labels =["appraisal.code"];
	var flags =["select"];
	if(!validateBlank(fields,labels,flags)){
		return false;
	}else{
		if(document.getElementById('paraFrm_questionCode').value=="" 
		&& document.getElementById('paraFrm_sectionId').value==""){
			alert("Please select either "+document.getElementById('section').innerHTML+" or "+document.getElementById('question').innerHTML);
			return false;
		}else
		
		callReport('AppraisalQuesAnalysis_getReport.action');
	}
}
</script>