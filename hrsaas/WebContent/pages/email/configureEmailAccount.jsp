<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>

	
	<%String accountsAdded=(String) request.getAttribute("accountsAdded");

if(accountsAdded!=null &&accountsAdded.length()>0){
%>	
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>	
		<tr>
			<td width="100%" colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Email Accounts</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
	
		<s:hidden name="hiddenCode" />
		<TR>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" >
				<tr>				
							<td class="formth" width="8%" colspan="1"><label
								name="check.name" id="check.name"
								ondblclick="callShowDiv(this);"><%=label.get("SrNo")%></label></td>
								<td width="35%" class="formth" colspan="1" ><label
								name="check.description" id="check.description"
								ondblclick="callShowDiv(this);"><%=label.get("IttAccountName")%></label></td>
							<td width="35%" class="formth" colspan="1" ><label
								name="check.description" id="check.description"
								ondblclick="callShowDiv(this);"><%=label.get("IttUserName")%></label></td>
							<td width="15%" class="formth" colspan="1"><label
								name="check.submit" id="check.submit"
								ondblclick="callShowDiv(this);"><%=label.get("server")%></label></td>
							<!--<td width="15%" class="formth" colspan="1"><label
								name="check.submit" id="check.submit"
								ondblclick="callShowDiv(this);"><%=label.get("password")%></label></td>
							-->
							<td width="18%" class="formth" colspan="1">&nbsp;
							</td>
							
							
<td width="10%" class="formth" colspan="1" > &nbsp;</td>
						</tr>
						<%
						try {
						%>
						<%int i=1; %>
						<%!int tot=0; %>
						<!--
						203.94.215.195:2400
						-->
						<s:iterator value="congEmailAcc.list">
							<s:form action="http://203.94.215.195:2400/webmail/imp/redirect.php" theme="simple" >						
							<tr>									
							<td  width="5%" class="sortableTD" colspan="1"><%=i %>
							<input type="hidden" name="ittHiddenCode" id="ittHiddenCode<%=i%>" value='<s:property value="ittHiddenCode" />'/>
							</td>
							<td width="35%" class="sortableTD" colspan="1" align="center"><s:property value="ittAccountName" /></td>
							<td width="35%" class="sortableTD" colspan="1" align="center"><s:property value="ittUserName" /></td>
							<s:hidden	name="ittServerName" />
							<td width="15%" class="sortableTD" colspan="1" align="center">
							
							<s:property value="ittServerName" />
							</td>
						<td width="18%" class="sortableTD" colspan="1" align="left">	
										<input type="hidden" name="server"  id="server<%=i%>" value='<s:property value="server" />' />
										  <input type="hidden" name="port"  id="port<%=i%>" value='<s:property value="port" />'/>
										  <input type="hidden" name="protocol"  id="protocol<%=i%>" value='<s:property value="protocol" />'/>
										  <input type="hidden" name="imapuser"  id="imapuser<%=i%>" value='<s:property value="imapuser" />'/>
										  
										   <input type="hidden" name="pass"  id="passimapuser<%=i%>" value='<s:property value="pass" />'/>
										 
										  <input type="hidden" name="smtphost"  id="smtphost<%=i%>" value='<s:property value="smtphost" />'/>
										   <input type="hidden" name="smtpport"  id="smtpport<%=i%>" value='<s:property value="smtpport" />'/>			
								
								<s:submit cssClass="token" value="Login" />	
								</td>
								
							</tr>
							</s:form>	
							<%i++; %>
						</s:iterator>
												
						<%
						
						tot=i;%>
						
						
						<%
							} catch (Exception e) {
							}
						%>
						
					
			</table>
			</td>
		</tr>
				 
		 
		  <s:hidden name="server"  />
		  <s:hidden name="port"  />
		  <s:hidden name="protocol"  />
		  <s:hidden name="imapuser"  />
		  <s:hidden name="pass"  />
		  <s:hidden name="smtphost"  />
		   <s:hidden name="smtpport"  />
		 <s:hidden name="new_lang" value="en_US" />
		 <s:hidden name="select_view" value="dimp" />
		 
		
		
		<tr>	<s:if test="%{congEmailAcc.viewFlag}">
							<td width="78%">
						<!--<s:submit cssClass="add" value="Add Account" action="CongEmailAccount_addAccount" />		
								
								--></td>
						</s:if>
					
					<td width="22%">&nbsp;</td>
				</tr>
	</table>
	<table height="600">
	<tr><td></td></tr></table>
	
	<%}else{ %>
	
		<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>	
		<tr>
			<td width="100%" colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Email Accounts</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		<td>
	
	<table>
						<tr><td colspan="4">
						There is no email account configured.<BR>
						Please add email account at My Configuration-><a href="#" onclick="addEmailAccount();">Configure Email Account</a>
						</td></tr>
						</table>
						</td>
						</tr>
						</table>
						<%} %>
						
	

<script>
function callDelete(){

	var mess=confirm('Are you sure want to delete');
	if(mess){
			var hidCode="";
				var cnt=0;				
				var k='<%=tot%>';									
				for(var i=1;i<k;i++){							
				if(document.getElementById('chk'+i).checked==true){
				var id=document.getElementById('ittHiddenCode'+i).value;
					cnt=cnt+1;	
					hidCode +=id+",";			
				}	
			}				
			hidCode=hidCode.substring(0,eval(hidCode.length)-1);		
				if(cnt=='0'){
				alert('Please select atleast one checkBox');
				return false;
				}	


    document.getElementById('paraFrm_hiddenCode').value=hidCode;
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="CongEmailAccount_delete.action";
	document.getElementById('paraFrm').submit();
	}
	else{
	return false;
	}
	}
	
	
				function callEdit(){		
				var cnt=0;				
				var k='<%=tot%>';									
				for(var i=1;i<k;i++){							
				if(document.getElementById('chk'+i).checked==true){
				var id=document.getElementById('ittHiddenCode'+i).value;
					cnt=cnt+1;				
				}	
			}			
				
				
				if(cnt=='0'){
				alert('Please select atleast one checkBox');
				return false;
				}	
					if(eval(cnt)>eval(1)){
					alert('Please select only one checkBox');
					return false;
					}
					
						
			    document.getElementById('paraFrm_hiddenCode').value=id;
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action="CongEmailAccount_edit.action";
				document.getElementById('paraFrm').submit();		
	          }
	
		/*	function callLogin(id){		
		
			// document.getElementById('paraFrm_hiddenCode').value=id;
			  document.getElementById('paraFrm_server').value=document.getElementById('server'+id).value;
			   document.getElementById('paraFrm_port').value= document.getElementById('port'+id).value;
			   document.getElementById('paraFrm_protocol').value= document.getElementById('protocol'+id).value;
			 	document.getElementById('paraFrm_imapuser').value=document.getElementById('imapuser'+id).value;
			   document.getElementById('paraFrm_pass').value= document.getElementById('password'+id).value;
			  document.getElementById('paraFrm_smtphost').value=document.getElementById('smtphost'+id).value;
			document.getElementById('paraFrm_smtpport').value=document.getElementById('smtpport'+id).value;
			  document.getElementById('paraFrm_new_lang').value= 'en_US';
			  document.getElementById('paraFrm_select_view').value='dimp';
		
			var passData=document.getElementById('paraFrm_pass').value;
			
			if(passData==''){
			alert('Please enter password');
			return false;
			}
		
			 //http://203.94.215.195:2400/webmail/imp/redirect.php
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action="http://192.168.25.22/webmail/imp/redirect.php";//
				document.getElementById('paraFrm').submit();	
			
			
			}*/
		function addEmailAccount(){
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action="CongEmailAccount_addAccount.action";
				document.getElementById('paraFrm').submit();	
		}	
			function callLogin(id,code,serverIP,inPort,protocol,user,pass,outServerIP,outPort){		
				document.getElementById('paraFrm_hiddenCode').value=code;
								
			  	document.getElementById('paraFrm_server').value=serverIP;//document.getElementById('server'+id).value;
			   	document.getElementById('paraFrm_port').value=inPort;// document.getElementById('port'+id).value;
			   	document.getElementById('paraFrm_protocol').value=protocol;// document.getElementById('protocol'+id).value;			 	document.getElementById('paraFrm_imapuser').value=user;//document.getElementById('imapuser'+id).value;
			   	
			 	document.getElementById('paraFrm_pass').value=pass;		
			 	//alert(document.getElementById('paraFrm_pass').value);	 
			  	document.getElementById('paraFrm_smtphost').value=outServerIP;//document.getElementById('smtphost'+id).value;
				document.getElementById('paraFrm_smtpport').value=outPort;//document.getElementById('smtpport'+id).value;
			  	document.getElementById('paraFrm_new_lang').value= 'en_US';
			  	document.getElementById('paraFrm_select_view').value='dimp';							
				//getMyPassword('CongEmailAccount_getMyPassword.action?','paraFrm');
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action="CongEmailAccount_login.action";//
				document.getElementById('paraFrm').submit();	
				//document.getElementById('myframe').height='1000';
				
			
			
			}
	
	
 </script>
