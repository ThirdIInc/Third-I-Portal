<%@ taglib prefix="s" uri="/struts-tags"%>
 <%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="WarningLetter" method="post" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Warning Letter 
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
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
							<td width="78%" colspan="1">
								<input type="button" class="token"
									onclick="return callMisReport();"
									value="    View Report"  />
							
								<s:submit name="reset"  value="Reset" cssClass="token" action="WarningLetter_reset" theme="simple" /> 
								
								
								</td>
							<!-- <td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>-->
						</tr>
					</table>
					<label></label></td>
				</tr>





				
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								
								
								<tr>
									
									<td width="20%" colspan="1" ><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font color="red">*</font>:</td>
									<td  width="80%" colspan="3"><s:textfield theme="simple"  name="empToken" readonly="true" /><s:hidden theme="simple" name="empId" /><s:textfield
										theme="simple" name="empName" size="65" 
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'WarningLetter_f9type.action');">
									</td>
									

								</tr>
								
									<tr>
									
									<td width="20%" colspan="1" ><label  class = "set" name="template" id="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label> <font color="red">*</font>:</td>
									<td  width="80%" colspan="3"><s:hidden theme="simple" name="templateCode" /><s:textfield
										theme="simple" name="templateName" size="65" 
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'WarningLetter_f9Template.action');">
									</td>
									

								</tr>
								
								
								
								
								<!--	
								<tr>
									<td><label  class = "set" name="report.type" id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :</td>
									<td><s:select theme="simple" name="reportType"
										headerKey="Pdf" headerValue="Pdf"
										list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
										
								<td class="formtext"><label  name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
							    <td><label> <s:select name="status"  headerKey="-1" headerValue="--Select--"
								list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" />
							</label></td>
								</tr>
								
								
							-->
							
							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>




				



			
			</table>
			<br />


			<label></label></td>
		</tr>
	</table>

</s:form>
<script type="text/javascript">

	function callMisReport(){
	
  	var empid=document.getElementById('employee').innerHTML.toLowerCase();
  	var template=document.getElementById('template').innerHTML.toLowerCase();
 			if(document.getElementById('paraFrm_empName').value=="") {  
 				alert("Please select "+empid);
 				return false;
 				}
 				if(document.getElementById('paraFrm_templateName').value=="") {  
 				alert("Please select "+template);
 				return false;
 				}
 				
 				else {
 			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='WarningLetter_report.action';	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
	}
	
	}
	
	
function callReset(){
  	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='WarningLetter_clear.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="_self";
	}

</script>
