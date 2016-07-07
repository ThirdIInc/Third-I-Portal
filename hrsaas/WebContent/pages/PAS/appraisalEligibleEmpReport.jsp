<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf' %>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="EligibleEmpReportForm" validate="true" id="paraFrm" theme="simple">
 
 <s:hidden name="report" />
 <s:hidden name="reportAction" value='AppraisalEligibleEmpReport_getReport.action' />
 <table width="100%" border="0" cellpadding="2" cellspacing="2">
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
							<label  class = "set" name="eligibleempreport.heading"  id="eligibleempreport.heading" ondblclick="callShowDiv(this);"><%=label.get("eligibleempreport.heading")%></label> 
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
	<td colspan="3">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="resetFun()">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
	</tr></table></td></tr>
	<tr>
	 <td>		
        <div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp"
			scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
			name="htmlReport" width="100%" height="200"></iframe></div>
	 </td>
	</tr>
	
	
	
	<tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<!-- <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /> -->
		   </td>
           <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
 	
 	<tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" id="reportBodyTable">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
              <s:hidden name="sAppId"></s:hidden>
              <s:hidden name="sAppStartDate"></s:hidden>
              <s:hidden name="sAppEndDate"></s:hidden>
              <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap">
              	<label  class = "set" name="eligibleempreport.appraisalcode"  id="eligibleempreport.appraisalcode" ondblclick="callShowDiv(this);"><%=label.get("eligibleempreport.appraisalcode")%></label> 
              	<font color="red"> * </font> :
              </td>
			  <td width="40%" colspan="1" height="20" align="left"><s:textfield name="sAppCode" size="25" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AppraisalEligibleEmpReport_f9SelectAppraisal.action'); "></td>
              <td width="25%" colspan="1" height="20" class="formtext">
             
              <s:property value="startDate"></s:property>   <s:property value="endDate"></s:property>
             </td>
			  <td width="25%" colspan="1" height="20"></td> 
            </tr>
        
            </table></td>
          </tr>
          
      </table></td>
    </tr>
      
    <tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<!-- <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /> -->
		   </td>
            <td width="22%"><div align="right">&nbsp;</td>
          </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
    <tr>
	  <td colspan="3">
	     <table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="resetFun();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
</table>
</s:form>
<SCRIPT LANGUAGE="JavaScript">
var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>
<script type="text/javascript">
	function reportFun()
	{
		var fields = ["paraFrm_sAppCode"];
		var labels = [document.getElementById("eligibleempreport.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		callReport('AppraisalEligibleEmpReport_getReport.action');
		document.getElementById("paraFrm_sAppCode").value = '';
	}

	function resetFun()
	{
		document.getElementById("paraFrm_sAppCode").value = '';
	}
	
	
	//Added by Tinshuk Banafar ...Begin....
	
	function callReport(type){	
	try{
	
	
	   var fields = ["paraFrm_sAppCode"];
		var labels = [document.getElementById("eligibleempreport.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
	 
	 var fields = ["paraFrm_sAppCode"];
		var labels = [document.getElementById("eligibleempreport.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraisalEligibleEmpReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
	//Added by Tinshuk Banafar ...End....
	
	
</script>