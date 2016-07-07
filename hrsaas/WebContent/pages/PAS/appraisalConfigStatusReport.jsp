<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalConfigStatusReport" validate="true" id="paraFrm" theme="simple">
 	<s:hidden name="reportAction" value='AppraisalConfigStatusReport_genReport.action'/>
<s:hidden name="report" />
 <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<tr>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt">
							<strong class="text_head">
							Appraisal Configuration Status Report 
							</strong>
						</td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/help.gif"
							width="16" height="16" /></div>
						</td>
					</tr>

				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	  
	 <tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
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
            <s:hidden name="appStartDate" />
             <s:hidden name="appEndDate" />
             <s:hidden name="apprId" />
              <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap">
              	<label  class = "set" name="configstatus.appraisalcode"  id="configstatus.appraisalcode" ondblclick="callShowDiv(this);"><%=label.get("configstatus.appraisalcode")%></label> 
              	<font color="red"> * </font> :
              </td>
			  <td width="40%" colspan="1" height="20" align="left"><s:textfield name="apprCode" size="25" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AppraisalConfigStatusReport_f9SelectAppraisal.action'); "></td>
              <td width="25%" colspan="1" height="20" class="formtext">
                          
             </td>
			  <td width="25%" colspan="1" height="20"></td> 
            </tr>
        
            </table></td>
          </tr>
          
      </table></td>
    </tr>
      
   	<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		
		</tr>
		<tr>
			<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		</tr>
    
</table>
</s:form>

<script type="text/javascript">
	function reportFun()
	{
		var fields = ["paraFrm_apprId"];
		var labels = [document.getElementById("configstatus.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		callReport('AppraisalConfigStatusReport_getReport.action');
		document.getElementById("paraFrm_apprCode").value = '';
	}

	function resetFun()
	{
		document.getElementById("paraFrm_apprId").value = '';
		document.getElementById("paraFrm_appStartDate").value = '';
		document.getElementById("paraFrm_appEndDate").value = '';
		document.getElementById("paraFrm_apprCode").value = '';
	}
	
	function callReport(type){

	var fields = ["paraFrm_apprId"];
		var labels = [document.getElementById("configstatus.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		document.getElementById('paraFrm_report').value=type; 
		callReportCommon(type);		
}
function mailReportFun(type){
var fields = ["paraFrm_apprId"];
		var labels = [document.getElementById("configstatus.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraisalConfigStatusReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
function callReset(){
	
	document.getElementById("paraFrm_apprId").value = '';
		document.getElementById("paraFrm_appStartDate").value = '';
		document.getElementById("paraFrm_appEndDate").value = '';
		document.getElementById("paraFrm_apprCode").value = '';
	
}
</script>