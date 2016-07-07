<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="ConfigureMailAlerts" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="hidReqAppr" /> 
		<s:hidden name="hidACOstatus" /> 
		<s:hidden name="hidSend2Appr" /> 
		<s:hidden name="hidSend2Empr" /> 
		<s:hidden name="hidreqHead" /> 
	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Configure Mail Alerts </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		 
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save"
				action="ConfigureMailAlerts_saveSetting" theme="simple"
				onclick="return myapplication();" value=" Save" /></td>
					<td width="22%" align="right">
				<!--	<div align="right"><font color="red">*</font> Indicates
					Required</div> -->
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table class="formbg" width="100%">
				<tr>
					<td width="50%" class="formth" colspan="2">Mail Events</td>
					<td width="25%" class="formth" colspan="2">MailTrigger Option</td>
				</tr>
				<%! int i=1,j=0;  %>
				<s:iterator value="eventList">
				<tr class="sortabletr">
					<td width="50%" class="sortabletd" colspan="2">
					<s:property value="eventName" /><s:hidden name="eventCode" />		
					</td>
					
					<td width="25%" class="sortabletd" colspan="1">
						<s:radio name='<%="conRadio"+i%>'  theme="simple" list="#{'A':'Auto Send','E':'Edit','V':'View'}" ></s:radio>
					<s:hidden name="hidConf" id='<%="hidConf"+i%>' /> 
					</td>
					
				</tr>
				<%i++; %>
				</s:iterator>
				<% j=i-1;i=1; %>
				
				<input type="hidden" name="noofRecords" id="paraFrm_noofRecords"  value="<%=j%>" /> 
			
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="left"><s:submit cssClass="save"
				action="ConfigureMailAlerts_saveSetting" theme="simple"
				onclick="return myapplication();" value=" Save" />
				
				</td>
		</tr>
	</table>
</s:form>
<script>
	callOnLoad();
	
	function myapplication()
	{
	  var totRecords=document.getElementById("paraFrm_noofRecords").value;
	  if(totRecords>0)
	  {
	     for(var i=1;i<=totRecords;i++)
	     { 
		   if(document.getElementById("paraFrm_conRadio"+i+"V").checked) 
		   document.getElementById("hidRadioConf"+i).value="V";
		   else if(document.getElementById("paraFrm_conRadio"+i+"E").checked) 
		   document.getElementById("hidRadioConf"+i).value="E";
		   else
		   document.getElementById("hidRadioConf"+i).value="A";
	     }
	  }
	  
	   return true;
	}
	  
	function callOnLoad()
	{
	try{
	
	  var totRecords=document.getElementById("paraFrm_noofRecords").value;
	  if(totRecords>0)
	  {
	for(var i=1;i<=totRecords;i++)
	{
	  var myoptvalue=document.getElementById("hidConf"+i).value;
	  
	  if(myoptvalue!="")
	  document.getElementById("paraFrm_conRadio"+i+myoptvalue).checked=true;
	  
	
	}
	}
	}catch(e)
	{
	//alert("--->"+e);
	}
		
	}
</script>