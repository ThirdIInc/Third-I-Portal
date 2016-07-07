<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="CentralisedPanelSetting" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden  name="rowId" id="paraFrm_rowId" />
	<s:hidden name="eventFlag" />
	<s:hidden name="noofRecordsFlag" />
	<s:hidden name="dupModulecode" id="paraFrm_dupModulecode"/>
	
	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Centralised Panel for Mail Trigger </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		 
		
		
		<tr>
			<td>
			<table width="100%" border="0" class="formbg" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="30%" align="center" colspan="2">
					<label class="set"
								name="conf1" id="conf1" ondblclick="callShowDiv(this);"><%=label.get("conf1")%></label>
							:<font color="red">*</font>	&nbsp;&nbsp; 
							
							 <s:textfield
								theme="simple" name="moduleName" size="30"  readonly="true"
								/>
								<img
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'CentralisedPanelSetting_f9ModuleName.action')">
					</td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td width="30%" colspan="2"  align="center">
					<s:submit   cssClass="token"  name="View Events"  value="View Mail Trigger Events"  action="CentralisedPanelSetting_viewEvents" onclick="return myFun();"  theme="simple"/>
					</td></tr>
				
				
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<s:if test="noofRecordsFlag">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save"
				action="CentralisedPanelSetting_saveCentraliseSetting" theme="simple" onclick="return callSave();"
				 value=" Save" />
				 </td>
				</tr>
			</table>
			</s:if>
			</td>
		</tr>
		
		
		<tr>
			<td align="center">
			<s:if test="noofRecordsFlag">
			<table class="formbg" width="100%">
				<tr>
					<td width="30%" class="formth" colspan="1">Mail Events</td>
					<td width="10%" class="formth" colspan="1">Mail Enable 
					<input type="checkbox" class="checkbox"  id="checkAll"  name="checkAll" onclick="callCheckAll();" />
					</td>
					<td width="30%" class="formth" colspan="1">Template</td>
					<td width="30%" class="formth" colspan="1">MailTrigger Option</td>
				</tr>
				
				<%! int i=1,j=0;  %>
				<s:iterator value="eventList">
				
				<%
				out.println("");
				%>
				<tr class="sortabletr">
					<td width="30%" class="sortabletd" colspan="1">
					<s:property value="eventName" /><s:hidden name="eventCode" />		
					</td>
					
					<td width="10%" class="sortabletd" align="center" colspan="1">
					<input type="checkbox" class="checkbox"  id='<%="confChk"+i%>'   name="confChk" onclick="callCheck('<%=i%>')" />
					
					<s:hidden name="hidConf" id='<%="hidConf"+i%>' /> 
					</td>
					<td width="30%" class="sortabletd" colspan="1">
					<input type="hidden" name="<%="templateCode"+(i-1)%>"  id="<%="paraFrm_templateCode"+(i-1)%>" value="<s:property value="templateCode" />" />
					<input type="text" name="<%="templateName"+(i-1)%>"  id="<%="paraFrm_templateName"+(i-1)%>"  value="<s:property value="templateName" />"   size="25" readonly="true" />
					 <img src="../pages/images/recruitment/search2.gif"
									height="17" width="17"
									onclick="rowcallsF9('<s:property value="<%=""+(i-1)%>"/>');">
					
					</td>
					
					<td width="30%" class="sortabletd" colspan="1">
						<s:radio name='<%="conRadio"+i%>'  theme="simple" list="#{'A':'Auto Send','E':'Edit','V':'View'}" ></s:radio>
					<s:hidden name="hidRadioConf" id='<%="hidRadioConf"+i%>' /> 
					</td>
					
					
					
				</tr>
				<%i++; %>
				</s:iterator>
				<% j=i-1;i=1; %>
				
				<input type="hidden" name="noofRecords" id="paraFrm_noofRecords"  value="<%=j%>" /> 
				
			
			</table>
			</s:if>
			
			<s:elseif test="eventFlag"> <font color="red">No Mail Event Defined For Selected Module Name.</font>
			</s:elseif>
		
			</td>
		</tr>
		<tr>
			<td colspan="3" align="left">
			<s:if test="noofRecordsFlag">
			<s:submit cssClass="save"
				action="CentralisedPanelSetting_saveCentraliseSetting" theme="simple"  onclick="return callSave();"
				 value=" Save" />
				 </s:if>
				
				</td>
		</tr>
	</table>
	
	
</s:form>
<script>


	callOnLoad();
	
	
	function myFun()
	{
	 var modulename=document.getElementById("paraFrm_dupModulecode").value;
	if(modulename=="") 
	 {
	//  alert("Please Select the ModuleName.");
	  	alert("Please "+document.getElementById('conf1').innerHTML.toLowerCase()+".");
	  return false;
	 }
	
	}
	
	
	
	function callSave()
	{
	 var totRecords=document.getElementById("paraFrm_noofRecords").value;
	 	 
	 if(totRecords!=null && totRecords==0)
	 {
	   alert("No Mail Event Records Available for Save.");
	   return false;
	  }
	  else{
	    for(var i=1;i<=totRecords;i++)
	    {
	     
	      if(document.getElementById("confChk"+i).checked==true)
	      {
	         if(document.getElementById("paraFrm_templateCode"+(i-1)).value=="")
	         {
	         alert("Please Select the Template");
	         document.getElementById("paraFrm_templateName"+(i-1)).focus();
	         return false;
	         }
	       }     
	    
	    
	    }
	  
	  
	  }
	
	//	var totRecords=document.getElementById("paraFrm_noofRecords").value;
	  if(totRecords>0)
	  {
	     for(var j=1;j<=totRecords;j++)
	     { 
		   if(document.getElementById("paraFrm_conRadio"+j+"V").checked) 
		   document.getElementById("hidRadioConf"+j).value="V";
		   else if(document.getElementById("paraFrm_conRadio"+j+"E").checked) 
		   document.getElementById("hidRadioConf"+j).value="E";
		   else
		   document.getElementById("hidRadioConf"+j).value="A";
	     }
	  }
	  
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	function callCheck(i)
	{
	
	 var count=0;
	
	 
	 var totRecords=document.getElementById("paraFrm_noofRecords").value;
	
	if(document.getElementById("confChk"+i).checked==true)
	{ 
		document.getElementById("hidConf"+i).value="Y";
		
		for(var i=1;i<=totRecords;i++)
			{ 
			  if(document.getElementById("confChk"+i).checked==true)
			 { count++; }
			  else{
			   document.getElementById("checkAll").checked=false;
			   break;
			  }
			  
			}
			if(count==totRecords)
				 document.getElementById("checkAll").checked=true;
	 		else
	 		 document.getElementById("checkAll").checked=false;
			
	
	}
	 else
	  {
	  document.getElementById("hidConf"+i).value="N";
	  document.getElementById("checkAll").checked=false;
	  }
	  
	  
	  
	  
	  
	
	}
	
	function callCheckAll()
	{
	
	  var totRecords=document.getElementById("paraFrm_noofRecords").value;
	  if(totRecords>0)
	  {
			
			 if(document.getElementById("checkAll").checked)
			 {
			  for(var i=1;i<=totRecords;i++)
				{ 
				  document.getElementById("confChk"+i).checked=true;
				  document.getElementById("hidConf"+i).value="Y";
				}
			  }else{
			  
			 	 for(var i=1;i<=totRecords;i++)
					{ 
				  document.getElementById("confChk"+i).checked=false;
				  document.getElementById("hidConf"+i).value="N";
					}
			  
			  }	
	}
	
	
	}
	
	function callOnLoad()
	{
	try{
	 var count=0;
	 
	 if(document.getElementById("paraFrm_eventFlag").value)
	 {
	 var totRecords=document.getElementById("paraFrm_noofRecords").value;
	  if(totRecords>0)
	  {
			for(var i=1;i<=totRecords;i++)
			{ 
	  var myoptvalue=document.getElementById("hidConf"+i).value;
	  if(myoptvalue=="Y")
	 	{ document.getElementById("confChk"+i).checked=true;
	 	count++;
	  }
	  else
	  document.getElementById("confChk"+i).checked=false;
	  
	
	}
	if(count==totRecords)
	 document.getElementById("checkAll").checked=true;
	 else
	  document.getElementById("checkAll").checked=false;
	 
	
	}
	}
	
	if(totRecords>0)
	  {
	for(var j=1;j<=totRecords;j++)
	{
	  var myoptvalue=document.getElementById("hidRadioConf"+j).value;
	  
	  if(myoptvalue!="")
	  document.getElementById("paraFrm_conRadio"+j+myoptvalue).checked=true;
	  
	
	}
	}
	
	
	
	
	
	
	
	
	}catch(e)
	{
	//alert("--->"+e);
	}
		
	}
	
	function rowcallsF9(id)
    {
      document.getElementById('paraFrm_rowId').value=id; 
      callsF9(500,325,'CentralisedPanelSetting_f9action.action'); 
   
     
    }
</script>