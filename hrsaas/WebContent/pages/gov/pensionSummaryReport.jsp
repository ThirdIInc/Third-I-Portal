<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
 <s:form action="PensionSummaryReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Pension Summary Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				
				<td>
				<s:if test="viewFlag">
				<input type="button" class="token"  onclick="check('PensionSummaryReport_getReport.action')" value="Generate Report"/>
				<input type="button" class="token"  onclick="resetField()" value="Reset"/>
				</s:if>
				</td>
				    
				<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						
								
					
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> <font color="red">*</font> :</td>
							<td colspan="1" width="30%">
							<s:select theme="simple" name="month" cssStyle="width:170"
							list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> <font color="red">*</font> :</td>
							<td>
							<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="29"  />
							</td>
						</tr>	
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> <font color="red">*</font> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="divCode" />
							<s:textfield name="divName" theme="simple"  readonly="true" maxlength="50" size="29" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'PensionSummaryReport_f9div.action');">	
							
							</td>
							
							<td colspan="1" width="20%"></td>
							<td>
								
							</td>
						</tr>	
						
						
						
						
						
					
						
							
						
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
		</td></tr></table>
		
		</s:form>
 <script>
 setCurrentYear('paraFrm_year');
 function callCheck(formAction){
 
	document.getElementById('paraFrm').action=formAction; 	
	document.getElementById('paraFrm').submit();
 
 }
 
function check(actionName)
 {
	 var month =document.getElementById("paraFrm_month").value;
	 var year =document.getElementById("paraFrm_year").value;
	 var division =document.getElementById("paraFrm_divCode").value;
	 //var rep = document.getElementById('paraFrm_reportType').value;
	
	 
	 if(month =='0'){
	 	alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
	 	document.getElementById('paraFrm_month').focus();
	 	return false;
	 }
	 if(year ==''){
	 	alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
	 	document.getElementById('paraFrm_year').focus();
	 	return false;
	 }
	 if(division =="")
	 {
	 	alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
	 	document.getElementById('paraFrm_divName').focus();
	 	return false;
	 }
	// if(rep=='0'){
	 	//	alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	 	//	return false;
		//	}

	callReport(actionName);
	}
	
	function resetField()
	{
	  document.getElementById("paraFrm_month").value='0';
	  document.getElementById("paraFrm_year").value="";
	  document.getElementById("paraFrm_divCode").value="";
	  document.getElementById('paraFrm_divName').value="";
		setCurrentYear('paraFrm_year');
	}


function callf9Dept(){
if(document.getElementById('paraFrm_deptFlag').checked){
	alert("Please uncheck the "+document.getElementById('department').innerHTML+" Wise flag");
	return false;
}

callsF9(500,325,'PensionSummaryReport_f9Dept.action');
return true;
}
function callf9Branch(){
if(document.getElementById('paraFrm_branchFlag').checked){
	alert("Please uncheck the "+document.getElementById('branch').innerHTML+" Wise flag");
	return false;
}
callsF9(500,325,'PensionSummaryReport_f9Branch.action');
return true;
}

</script>