<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="HelpDeskTechMisReport" method="post" id="paraFrm">
<s:hidden name="reportAction" value='HelpDeskTechMisReport_RequestAnalysisReport.action'/>
<s:hidden name="reportType" />

	<table width="100%">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Request Management Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
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
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td>
					<table width="100%" cellspacing="2" border="0">
						<tr>
							<td colspan="3" class="formhead"><strong
								class="forminnerhead">Select  search criteria to
							generate report </strong></td>
						</tr>

																	
						<tr>
							<td width="23%" colspan="1"><label class="set" name="from_date" id="from_date" ondblclick="callShowDiv(this);"><%=label.get("from_date")%></label> 
							<font color="red">*</font> :
							</td>
							<td width="17%" ><s:textfield name="fromDate"
								size="10" onkeypress="return numbersWithHiphen();"
								theme="simple" maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="16">
							</s:a></td>
							<td width="17%"><label class="set" name="to_date" id="to_date" ondblclick="callShowDiv(this);"><%=label.get("to_date")%></label> 
							<font color="red">*</font> :
							</td>
							<td width="43%"><s:textfield name="toDate"
								size="10" onkeypress="return numbersWithHiphen();"
								theme="simple" maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>
 				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
 					
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table></td></tr>
	
	<tr>
	<table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
          <tr>
			<td >
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
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
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
	 </tr>
	
	</table>
	
</s:form>

<script type="text/javascript">

function callReport(type){	
	try{
	
	 
	   var fromDate	 = document.getElementById("paraFrm_fromDate").value;
		var toDate = document.getElementById("paraFrm_toDate").value;
	
	   if(fromDate=="" && toDate=="" ){
	   alert("Please select  search criteria to generate report ");
	   return false;
	   }
	   
	   if(!validateDate('paraFrm_fromDate', 'from_date')) {
			return false;
		}
		
	   if(!validateDate('paraFrm_toDate', 'to_date')) {
			return false;
		}
		
		if(fromDate != "" && toDate==""){
		alert("Please enter/select To Date");
		return false;
		}
		if(toDate != "" && fromDate==""){
		alert("Please enter/select From Date");
		return false;
		}
		if(fromDate != ""){
			if(!dateDifferenceEqual(fromDate, toDate, 'paraFrm_fromDate', 'from_date', 'to_date')){
				return false;
			}
		}
		
		
	    document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='HelpDeskTechMisReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}

function callReset(){
	document.getElementById('paraFrm_fromDate').value="";
	document.getElementById('paraFrm_toDate').value="";
	
}


</script>
