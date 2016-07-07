<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf' %>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraiserConfigurationReport" validate="true" id="paraFrm" theme="simple">
 <s:hidden name="reportAction" value='AppraiserConfigurationReport_genReport.action'/>
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
							<label  class = "set" name="appraiserconfigreport.heading"  id="appraiserconfigreport.heading" ondblclick="callShowDiv(this);"><%=label.get("appraiserconfigreport.heading")%></label> 
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
            <td>
	            <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
		            <tr>
		              <s:hidden name="sAppId"></s:hidden>
		              <s:hidden name="sAppStartDate"></s:hidden>
		              <s:hidden name="sAppEndDate"></s:hidden>
		              <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap">
		              	<label  class = "set" name="appraiserconfigreport.appraisalcode"  id="appraiserconfigreport.appraisalcode" ondblclick="callShowDiv(this);"><%=label.get("appraiserconfigreport.appraisalcode")%></label> 
		              	<font color="red"> * </font> :
		              </td>
					  <td width="40%" colspan="1" height="20" align="left">
					  	<s:textfield name="sAppCode" size="25" readonly="true" />
					  	<img src="../pages/images/recruitment/search2.gif" height="16"
							 align="absmiddle" width="17" theme="simple"
							 onclick="javascript:callsF9(500,325,'AppraiserConfigurationReport_f9SelectAppraisalCalendar.action'); ">
					  </td>
		              <td width="10%" colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraiserconfigreport.group"  id="appraiserconfigreport.group" ondblclick="callShowDiv(this);"><%=label.get("appraiserconfigreport.group")%></label>
		              </td>
					  <td width="40%" colspan="1" height="20">
					  	<s:textfield name="sGroup" size="25" readonly="true" /> <s:hidden name="sGroupId"></s:hidden> 
					  	<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple" 
						onclick="validateF9('f9SelectGroupName');">
					  </td> 
		            </tr>
		            
		            <tr>
			            <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap"> 
			            	<label  class = "set" name="appraiserconfigreport.appraiseename"  id="appraiserconfigreport.appraiseename" ondblclick="callShowDiv(this);"><%=label.get("appraiserconfigreport.appraiseename")%></label>
			            </td>
			            <td width="40%" colspan="1" height="20" align="left">
			            	<s:textfield name="appraiseeCode" size="10" readonly="true" /><s:textfield name="appraiseeName" readonly="true" size="25" /><s:hidden name="appraiseeId"></s:hidden>
			            	<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" 
			            	onclick="validateF9('f9SelectAppraiseeName');">
			            </td>
			            <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap"> 
			            	<label  class = "set" name="appraiserconfigreport.appraisername"  id="appraiserconfigreport.appraisername" ondblclick="callShowDiv(this);"><%=label.get("appraiserconfigreport.appraisername")%></label>
			            </td>
			            <td width="40%" colspan="1" height="20" align="left">
			            	<s:textfield name="appraiserCode" size="10" readonly="true" /><s:textfield name="appraiserName" readonly="true" size="25" /><s:hidden name="appraiserId"></s:hidden>
			            	<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" 
			            	onclick="validateF9('f9SelectAppraiserName');">
			            </td>
		            </tr>
		            
		            <tr>
			            <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap">
							<label  class = "set" name="appraiserconfigreport.phasename"  id="appraiserconfigreport.phasename" ondblclick="callShowDiv(this);"><%=label.get("appraiserconfigreport.phasename")%></label>		            
			            </td>
			            <td width="40%" colspan="1" height="20" align="left">
			            	<s:if test="phaseList">
			            		<s:select name="phaseName" list="phaseList" headerKey="" headerValue="--Select--" size="1" />
			            	</s:if>
			            	<s:else>
			            		<s:select name="phaseName" list="#{'0':'--Select--'}" size="1" />
			            	</s:else>
			            </td>
			            <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap"></td>
			            <td width="40%" colspan="1" height="20" align="left"></td>
		            </tr>
		            
	            </table>
            </td>
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
		var fields = ["paraFrm_sAppCode"];
		var labels = [document.getElementById("appraiserconfigreport.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		if(document.getElementById('paraFrm_phaseName').value=="")
		{
			alert("Please select "+document.getElementById('appraiserconfigreport.phasename').innerHTML.toLowerCase());
			return false;
		}
		
		callReport('AppraiserConfigurationReport_getReport.action');
		

		//document.getElementById("paraFrm_sAppId").value = '';
		//document.getElementById("paraFrm_sAppStartDate").value = '';
		//document.getElementById("paraFrm_sAppEndDate").value = '';
		//document.getElementById("paraFrm_sAppCode").value = '';
		//document.getElementById("paraFrm_sGroup").value = '';
		//document.getElementById("paraFrm_sGroupId").value = '';
		//document.getElementById("paraFrm_appraiseeCode").value = '';
		//document.getElementById("paraFrm_appraiseeName").value = '';
		//document.getElementById("paraFrm_appraiseeId").value = '';
		//document.getElementById("paraFrm_appraiserCode").value = '';
		//document.getElementById("paraFrm_appraiserName").value = '';
		//document.getElementById("paraFrm_appraiserId").value = '';
	}

	function resetFun()
	{
		document.getElementById("paraFrm_sAppId").value = '';
		document.getElementById("paraFrm_sAppStartDate").value = '';
		document.getElementById("paraFrm_sAppEndDate").value = '';
		document.getElementById("paraFrm_sAppCode").value = '';
		document.getElementById("paraFrm_sGroup").value = '';
		document.getElementById("paraFrm_sGroupId").value = '';
		document.getElementById("paraFrm_appraiseeCode").value = '';
		document.getElementById("paraFrm_appraiseeName").value = '';
		document.getElementById("paraFrm_appraiseeId").value = '';
		document.getElementById("paraFrm_appraiserCode").value = '';
		document.getElementById("paraFrm_appraiserName").value = '';
		document.getElementById("paraFrm_appraiserId").value = '';
	}
	
	function validateF9(action)
	{
		if(document.getElementById("paraFrm_sAppCode").value=="")
		{
			alert("Please select "+document.getElementById("appraiserconfigreport.appraisalcode").innerHTML.toLowerCase());
			//document.getElementById("paraFrm_sAppCode").focus();
			return false;
		}
				
		javascript:callsF9(500,325,'AppraiserConfigurationReport_'+action+'.action'); 
	}


	function callReport(type){

	var fields = ["paraFrm_sAppCode"];
		var labels = [document.getElementById("appraiserconfigreport.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		if(document.getElementById('paraFrm_phaseName').value=="")
		{
			alert("Please select "+document.getElementById('appraiserconfigreport.phasename').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById('paraFrm_report').value=type; 
		callReportCommon(type);		
}

function mailReportFun(type){
var fields = ["paraFrm_sAppCode"];
		var labels = [document.getElementById("appraiserconfigreport.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		if(document.getElementById('paraFrm_phaseName').value=="")
		{
			alert("Please select "+document.getElementById('appraiserconfigreport.phasename').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraiserConfigurationReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
function callReset(){
	
	document.getElementById("paraFrm_sAppId").value = '';
		document.getElementById("paraFrm_sAppStartDate").value = '';
		document.getElementById("paraFrm_sAppEndDate").value = '';
		document.getElementById("paraFrm_sAppCode").value = '';
		document.getElementById("paraFrm_sGroup").value = '';
		document.getElementById("paraFrm_sGroupId").value = '';
		document.getElementById("paraFrm_appraiseeCode").value = '';
		document.getElementById("paraFrm_appraiseeName").value = '';
		document.getElementById("paraFrm_appraiseeId").value = '';
		document.getElementById("paraFrm_appraiserCode").value = '';
		document.getElementById("paraFrm_appraiserName").value = '';
		document.getElementById("paraFrm_appraiserId").value = '';
	
}
</script>