<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
 
<s:form action="MailSettings" validate="true" id="paraFrm"
	theme="simple">
	
		<div id='div_Id' style='position:absolute; z-index:3; width:350px; height:120px; display:none; border:2px solid; top: 200px; left: 200px; padding: 10px;' class="formbg">
	<table width="100%">
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="93%" align="center" class="formth" style="cursor:move" onmouseout="Drag.end();" 
						onmouseover="Drag.init(document.getElementById('div_Id'), null, 0, 350, 0, 700);" >
							<b><label id="moduleName" style="cursor:move" /></b>
						</td>
						<td width="7%" align="center" border="1" class="formth" style="font-family:Arial; cursor: pointer" 
						onclick="hide_Div();">
							<b>X</b>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="23%" nowrap="nowrap">From mail Id :</td>
						<td><input type="text" name="fromMailId" id="frmId" size="35" /></td>
					</tr>
					<tr>
						<td width="23%" nowrap="nowrap">To mail Id :</td>
						<td><input type="text" name="toMailId" id="toId" size="35" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="50%" align="right"><input type="button" id="ctrlShow" value="Send Mail" class="token" onclick="callConnection();" /></td>
						<td><input id="ctrlShow" type="button" value="Close" class="token" onclick="hide_Div();"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
	
	
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" width="7%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%"><strong class="text_head">Mail
					Settings </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- button code starts-->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="save"
						value="    Save " onclick="return callSave()" /> 
						<input type="button" class="token"
						onclick="callBack();" value="     Back    " /></td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<!-- button code ends -->

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				
				<tr>

					<td colspan="1" width="25%"><label name="mailbox.servername"
						id="mailbox.servername" ondblclick="callShowDiv(this);"><%=label.get("mailbox.servername")%></label>
					<font color="red"> * </font>:</td>
					<td colspan="1" width="25%"><s:textfield size="35"
						name="mailServerName" /><s:hidden name="serverCode" /></td>
					<!-- Updated By Anantha lakshmi -->
					<td colspan="1" width="25%">
						<label name="mailbox.division" id="mailbox.division" ondblclick="callShowDiv(this);">
								<%=label.get("mailbox.division")%></label>
						<font color="red"> * </font>:
					</td>
					
					<td colspan="1" width="25%">
					    <s:hidden name="divisionCode" />
						<s:textarea cols="20" rows="4" name="division" />
						<img src="../pages/images/search2.gif" height="16" align="bottom" width="16" theme="simple" 
										onclick="callDiv();"  >
						 					
					</td>	
					<!-- Anantha Lakshmi -->
				</tr>
				
				<tr>
					<td width="75%" colspan="3" class="text_head"><strong><label
						name="inboundset" id="inboundset" ondblclick="callShowDiv(this);"><%=label.get("inboundset")%></label>
					</strong></td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label name="mailbox.server"
						id="mailbox.server" ondblclick="callShowDiv(this);"><%=label.get("mailbox.server")%></label>
					<font color="red"> * </font> :</td>
					<td colspan="2" width="75%"><s:textfield size="35"
						name="mailServer" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="mailbox.protocol"
						id="mailbox.protocol" ondblclick="callShowDiv(this);"><%=label.get("mailbox.protocol")%></label>
					<font color="red"> * </font> :</td>
				<!-- list="#{'POP2':'POP2','POP3':'POP3','IMAP':'IMAP'}"  -->
					<td width="75%" colspan="2"><s:select name="mailProtocol"
						headerKey="-1" headerValue="--Select--" cssStyle="width:202"
						list="#{'POP2':'POP2','POP3':'POP3','pop3/novalidate-cert':'POP3 (self-signed certificate)','pop3/notls':'POP3, no TLS','pop3/ssl':'POP3 over SSL','pop3/ssl/novalidate-cert':'POP3 over SSL (self-signed certificate)','IMAP':'IMAP','imap/novalidate-cert':'IMAP (self-signed certificate)','imap/notls':'IMAP, no TLS','imap/ssl':'IMAP over SSL','imap/ssl/novalidate-cert':'IMAP over SSL (self-signed certificate)'}"
						/></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="mailbox.port"
						id="mailbox.port" ondblclick="callShowDiv(this);"><%=label.get("mailbox.port")%></label>
					<font color="red"> * </font> :</td>
					<td colspan="2" width="75%"><s:textfield size="35"
						maxlength="4" name="mailPort" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td width="75%" colspan="3" class="text_head"><strong>
					<label name="outboundset" id="outboundset"
						ondblclick="callShowDiv(this);"><%=label.get("outboundset")%></label></strong></td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label name="mailbox.server"
						id="mailbox.server1" ondblclick="callShowDiv(this);"><%=label.get("mailbox.server")%></label>
					<font color="red"> * </font> :</td>
					<td colspan="2" width="75%"><s:textfield size="35"
						name="mailServerOut" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="mailbox.protocol"
						id="mailbox.protocol1" ondblclick="callShowDiv(this);"><%=label.get("mailbox.protocol")%></label><font
						color="red"> * </font> :</td>
					<td width="75%" colspan="2"><s:select name="mailProtocolOut"
						headerKey="-1" headerValue="--Select--" cssStyle="width:202"
						list="#{'SMTP':'SMTP','IMAP':'IMAP','SMTPSSL':'SMTP WITH SSL','SMTPTLS':'SMTP WITH TLS'}" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label name="mailbox.port"
						id="mailbox.port1" ondblclick="callShowDiv(this);"><%=label.get("mailbox.port")%>
					</label> <font color="red"> * </font> :</td>
					<td colspan="2" width="75%"><s:textfield size="35"
						maxlength="4" name="mailPortOut"
						onkeypress="return numbersOnly();" /></td>
				</tr>
				
				
				
				<tr>
					<td colspan="1" width="25%"><label name="auth.reqd"
						id="auth.reqd" ondblclick="callShowDiv(this);"><%=label.get("auth.reqd")%></label>
					:</td>
					<td colspan="2" width="75%"><s:checkbox name="authChk" onclick="return callCheckAuth();"/></td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label name="popbeforesmtp"
						id="popbeforesmtp" ondblclick="callShowDiv(this);"><%=label.get("popbeforesmtp")%>
					:</td>
					<td colspan="2" width="75%"><s:checkbox
						name="chkPBSmtp" id="chkPBSmtp"   /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label name="sendSysMail"
						id="sendSysMail" ondblclick="callShowDiv(this);"><%=label.get("sendSysMail")%></label>
					:</td>
					<td colspan="2" width="75%"><s:checkbox
						name="sendSysMailChk" id="sendSysMailChk"   /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label name="sameIdforAll"
						id="sameIdforAll" ondblclick="callShowDiv(this);"><%=label.get("sameIdforAll")%></label>
					:</td>
					<td colspan="2" width="75%"><s:checkbox
						name="useSystemMailIdForAll" id="useSystemMailIdForAll"   /></td>
				</tr>
				
				
				<tr>
					<td colspan="1" width="25%"><label name="samesetting" id="samesetting"
						ondblclick="callShowDiv(this);"><%=label.get("samesetting")%></label> :</td>
					<td colspan="2" width="75%">
					<input name="logonusingcheck" type="radio" id="radioCheckfirst"   onclick="setVal();"  />
					
					 </td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="logon" id="logon"
						ondblclick="callShowDiv(this);"><%=label.get("logon")%></label> :</td>
					<td colspan="2" width="75%">
					 <input name="logonusingcheck" type="radio" id="radioChecksecond"  
								 onclick="setVal();" />
								  
					 </td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="logonusername"
						id="logonusername" ondblclick="callShowDiv(this);"><%=label.get("logonusername")%></label>
					:</td>
					<td colspan="2" width="75%"><s:textfield size="35"
						name="logonUserName" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label name="logonpassword"
						id="logonpassword" ondblclick="callShowDiv(this);"><%=label.get("logonpassword")%></label>
					:</td>
					<td colspan="2" width="75%"><s:password size="35"
						name="logonPassword" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label name="noOfMailsLabel"
						id="noOfMailsLabel" ondblclick="callShowDiv(this);"><%=label.get("noOfMailsLabel")%></label>
					:</td>
					<td colspan="2" width="75%"><s:textfield name="numberOfMailsSend" size="35" maxlength="4" onkeypress="return numbersOnly();"/> </td>
				</tr>
				
				
			<!-- 	<tr>

					<td colspan="1" width="25%"><label name="mailbox.username"
						id="mailbox.username" ondblclick="callShowDiv(this);"><%=label.get("mailbox.username")%></label>
					:</td>
					<td colspan="2" width="75%"><s:textfield size="35"
						name="mailUsername" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="mailbox.password"
						id="mailbox.password" ondblclick="callShowDiv(this);"><%=label.get("mailbox.password")%></label>
					:</td>
					<td colspan="1" width="25%"><s:password theme="simple"
						size="35" name="mailPasswd" /></td>
					<td colspan="1" align="left"><input type="button"
						class="token" theme="simple" onclick="return testConnection();"
						value=" Test Connection" /></td>
				</tr>
 -->
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="3" class="text_head"><strong>
					<label name="sysmails" id="sysmails"
						ondblclick="callShowDiv(this);"><%=label.get("sysmails")%></label></strong></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="email.id"
						id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%>
					</label> <font color="red"> * </font> :</td>
					<td colspan="2" width="75%"><s:textfield theme="simple"
						size="35" name="sysEmail" /><s:hidden name="hiddenMailCode" /></td>

				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="email.password"
						id="email.password" ondblclick="callShowDiv(this);"><%=label.get("email.password")%>
					</label><font color="red"> * </font> :</td>
					<td colspan="2" width="75%"><s:password theme="simple"
						size="35" name="sysEmailPsswd" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="left"><s:submit cssClass="add"
						action="MailSettings_saveSysMail" theme="simple"
						onclick="return chkSysMail();" value="    Add" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="10%"><label name="sr.no" id="sr.no"
						ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
					<td class="formth" width="60%"><label name="email.id"
						id="email.id1" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></td>
					<td class="formth" width="30%">&nbsp;</td>
				</tr>
				<%
				int count = 0;
				%>

				<s:iterator value="sysMailList">
					<tr>
						<td class="td_bottom_border"><%=++count%></td>
						<td class="td_bottom_border"><s:hidden name="sysMailCode" /><s:property
							value="sysEmailId" /><s:hidden name="sysEmailId" /><s:hidden name="sysMailPassword"/> </td>
						<td class="td_bottom_border" align="center"><input
							type="button" class="rowEdit" title="Edit Record"
							 onclick="callForEdit('<%=count %>')" />&nbsp;&nbsp;
						<input type="button" class="rowDelete" title="Delete Record"
							onclick="callForDelete('<%=count %>');" />
							
						 
							<input type="button"
						class="token" theme="simple" onclick="return callFortestConnection('<%=count %>');"
						value=" Test Connection" />
						 
							</td>
				</s:iterator>
				<%
				count = 0;
				%>
			</table>
			</td>
		</tr>
		
		<!-- button code starts-->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="save"
						value="    Save " onclick="return callSave()" /> 
						<input type="button" class="token"
						onclick="callBack();" value="     Back    " /></td>

					<td width="22%">
					 
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<!-- button code ends -->
		

	</table>
	 <s:hidden name="hiddenCode" />
	 <s:hidden name="pbSmtp"/>
	 <s:hidden name="hiddenRadio" />
	 
</s:form>

<script>

onload();

function onload()
{
		var check =document.getElementById('paraFrm_hiddenRadio').value;
		
		if(check=='S')
		{
				document.getElementById('radioCheckfirst').checked='true';
		}
		if(check=='D')
		{
				document.getElementById('radioChecksecond').checked='true';
		}
}

function setVal()
{
 	
 if(document.getElementById('radioCheckfirst').checked==true)
 {
 	document.getElementById('paraFrm_hiddenRadio').value="S";
 }
 if(document.getElementById('radioChecksecond').checked==true)
 {
 	document.getElementById('paraFrm_hiddenRadio').value="D";
 }
}

function callBack(){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/setting/MailSettings_callBack.action';	
		document.getElementById('paraFrm').submit();
}

function callFortestConnection(id)
	{	
	try{
	 
	  // alert("id"+id);
		document.getElementById("paraFrm_hiddenMailCode").value = id;
		document.getElementById('div_Id').style.display = '';
		document.getElementById('frmId').focus();
		}catch(e){ alert(e);}
	}
	
	 
	
	function callForEdit(id){
   		 //alert("+++++++++"+id);
   		document.getElementById('paraFrm_hiddenMailCode').value=id;
   		document.getElementById("paraFrm").action="MailSettings_editSysMail.action";
		document.getElementById("paraFrm").submit();
	   	return false;
  	
  		}
  		
  		function callForDelete(id)
	   {
	 	   //alert(id);
	 	   if(confirm("Are you sure want to delete?"))
		{
	 	document.getElementById('paraFrm_hiddenMailCode').value=id;
   		document.getElementById("paraFrm").action="MailSettings_deleteSysMail.action";
		document.getElementById("paraFrm").submit();
	   
   		}
   	}
  		
	 
	
	function callSave()
	{
	 var chkBoxStatus = document.getElementById('paraFrm_authChk').checked;
	 var chkBoxStatusforlogon = document.getElementById('radioChecksecond').checked;
	 
		var userName = document.getElementById('paraFrm_logonUserName').value;
		var pass = document.getElementById('paraFrm_logonPassword').value;
		var division=document.getElementById('paraFrm_division').value;
	 	
		try
		{
		
		
		
		if(document.getElementById('paraFrm_mailServerName').value == "")
		{
			alert("Please select "+document.getElementById('mailbox.servername').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mailServerName').focus();
			return false;
		}
		if(document.getElementById('paraFrm_division').value == "")
		{
			alert("Please select "+document.getElementById('mailbox.division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_division').focus();
			return false;
		}
		if(LTrim(document.getElementById('paraFrm_mailServer').value) == "")
		{
			alert("Please enter "+document.getElementById('mailbox.server').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mailServer').focus();
			return false;
		}
		if(document.getElementById('paraFrm_mailProtocol').value == "-1")
		{
	       alert("Please select "+document.getElementById('mailbox.protocol').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mailProtocol').focus();
			return false;
		}
		if(LTrim(document.getElementById('paraFrm_mailPort').value) == "")
		{
			alert("Please enter "+document.getElementById('mailbox.port').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mailPort').focus();
			return false;
		}
		
	 
		if(LTrim(document.getElementById('paraFrm_mailServerOut').value) == "")
		{
			alert("Please enter "+document.getElementById('mailbox.server').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mailServerOut').focus();
			return false;
		}
	
		if(document.getElementById('paraFrm_mailProtocolOut').value == "-1")
		{
			alert("Please select "+document.getElementById('mailbox.protocol').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mailProtocolOut').focus();
			return false;
		}
		
		if(LTrim(document.getElementById('paraFrm_mailPortOut').value) == "")
		{
			alert("Please enter "+document.getElementById('mailbox.port').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mailPortOut').focus();
			return false;
		}
		
		if(chkBoxStatusforlogon)
		{
			if(userName=="")
			{
					alert("Please enter "+document.getElementById('logonusername').innerHTML.toLowerCase());
					
					return false;
			}
			
			if(pass=="")
			{
					alert("Please enter "+document.getElementById('logonpassword').innerHTML.toLowerCase());
					
					return false;
			}
		}
			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action='<%=request.getContextPath()%>/setting/MailSettings_saveMailSetting.action';	
			document.getElementById('paraFrm').submit();
		}catch(e){ alert("e----------"+e);}
		
		return true;
	}
	
	
	function chkSysMail()
	{
		var fieldName = ['paraFrm_sysEmail','paraFrm_sysEmailPsswd'];
		var labelName = ['email.id','email.password'];
		var flag = ['Enter','Enter'];
		if(!validateBlank(fieldName,labelName,flag))
			return false;
		if(!validateEmail('paraFrm_sysEmail'))
			return false;
		return true;
	}
	
	
	
	function callCheckAuth()
	{
		try{
		var chkBoxStatus = document.getElementById('paraFrm_authChk').checked;
		if(!chkBoxStatus)
		{
			
			document.getElementById('paraFrm_mailUsername').disabled = true;
			document.getElementById('paraFrm_mailPasswd').disabled = true;
			document.getElementById('paraFrm_mailUsername').value = "";
		    document.getElementById('paraFrm_mailPasswd').value = "";
		}
		else
		{
			document.getElementById('paraFrm_mailUsername').disabled = false;
			document.getElementById('paraFrm_mailPasswd').disabled = false;
		}
	}catch(e){ alert("e-----------"+e); }
	}
	
	 
	
	function callReset(){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/setting/MailSettings_reset.action';	
		document.getElementById('paraFrm').submit();
}

function testConnection()
	{
	
	document.getElementById('div_Id').style.display = '';
	document.getElementById('frmId').focus();
	}
	
function hide_Div() {
		document.getElementById('div_Id').style.display = 'none';
	}
	
	function callConnection()
	{
	try
	{
	var frmMailId = document.getElementById('frmId').value;
	var toMailId = document.getElementById('toId').value;
	 
	if(frmMailId=="")
	{
			alert("Please enter from mail Id.");
			document.getElementById('frmId').focus();
			return false;
	}
	
	if(toMailId=="")
	{
			alert("Please enter to mail Id.");
				document.getElementById('toId').focus();
			return false;
	}
	
	var fromMailIdCheck=validateEmail('frmId');
  			if(!fromMailIdCheck){
  			return false;
  			}

	var toMailIdCheck=validateEmail('toId');
  			if(!toMailIdCheck){
  			return false;
  			}
	
		document.getElementById('paraFrm').action='MailSettings_testMailConnection.action';
		document.getElementById('paraFrm').submit();
		return true;
	}catch(e){	alert(e);}	
	}
	
	//onLoad();
	function onLoad()
	{
	try{
		if(document.getElementById('paraFrm_pbSmtp').value=="Y")
			document.getElementById('chkPBSmtp').checked=true;
		else
			document.getElementById('chkPBSmtp').checked=false;
		}catch(e){alert("val of e wil be__________"+e);}	
	}
	function callChkPBSmtp()
	{
		if(document.getElementById('chkPBSmtp').checked)
			document.getElementById('paraFrm_pbSmtp').value="Y";
		else
			document.getElementById('paraFrm_pbSmtp').value="N";
	}

	function callCheckAuth()
	{
		var chkBoxStatus = document.getElementById('paraFrm_authChk').checked;
		if(!chkBoxStatus)
		{
			
			document.getElementById('paraFrm_mailUsername').disabled = true;
			document.getElementById('paraFrm_mailPasswd').disabled = true;
			document.getElementById('paraFrm_mailUsername').value = "";
		    document.getElementById('paraFrm_mailPasswd').value = "";
		}
		else
		{
			document.getElementById('paraFrm_mailUsername').disabled = false;
			document.getElementById('paraFrm_mailPasswd').disabled = false;
		}
	}
	
	
	 function callDiv(){
		 try{
		    var id = document.getElementById('paraFrm_divisionCode').value;
		    window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
		 	document.getElementById("paraFrm").target="new";
			document.getElementById("paraFrm").action='MailSettings_intChecklist.action?id='+id; 
		  	document.getElementById("paraFrm").submit();
		  	document.getElementById("paraFrm").target="main";
		  }catch(e){
		  			//alert(e);
		  }
	  }

</script>