<!-- JSP file for front end for Transfer Application congEmailAcc== bean -->

<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="ChangePassword" validate="true" id="paraFrm"
	theme="simple">
	
	
	 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
    
    <s:hidden name="hiddenCode" />
    
    <tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Employee Configure Email Account
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
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
          <tr>
            <td width="78%">          
            		
            			<input type="button" class="save" 
					 value="    Save " onclick="return callSave()" />
						
				
					 <s:if test="bean.viewFlag">					 
					<input type="button" class="reset" onclick="callReset();"
						value="    Reset    " />
				    </s:if> 
						
										<input type="button" class="token" onclick="callBack();"
						value="     Back    " />
				
				
				</td>
				
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
                
                <tr>
                  <td width="24%" height="22" class="formtext">
                  <label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<font color="red">*</font></td>
                  <td height="22" colspan="4"><label>
                <s:hidden name="empId" theme="simple"  />
                <s:textfield name="empToken" theme="simple"  size="10"
				readonly="true" /><s:textfield 	 theme="simple" name="empName" size="81" readonly="true" />               
                              
                </label>
              </td>
                </tr>
                
                 <tr>
                  <td height="22" class="formtext"><label  class = "set" name="IttAccountName" id="IttAccountName" ondblclick="callShowDiv(this);"><%=label.get("IttAccountName")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="accountName" size="29"  />
                 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
                
                 
              <tr>
                  <td height="22" class="formtext"><label  class = "set" name="IttUserName" id="IttUserName" ondblclick="callShowDiv(this);"><%=label.get("IttUserName")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="userName" size="29"  /></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                  <tr>
                  <td height="22" class="formtext"><label  class = "set" name="password" id="password" ondblclick="callShowDiv(this);"><%=label.get("password")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:password	 name="userPassword" size="29"  /></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>  
                
                   <tr>
                  <td height="22" class="formtext"><label  class = "set" name="server" id="server" ondblclick="callShowDiv(this);"><%=label.get("server")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"> <s:select name="serverList"
				 list="serverMap" onchange="callServerList();"/></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                <s:hidden name="chkFlag"/>
                <s:hidden name="chkDisableFlag" />
                
              <s:if test="chkFlag">
              <s:if test="chkDisableFlag">
             <!--    <tr>
                  <td height="22" class="formtext"><label  class = "set" name="domainName" id="domainName" ondblclick="callShowDiv(this);"><%=label.get("domainName")%></label> :</td>
                  <td width="27%" height="22"><s:textfield	 name="serverName" size="29" readonly="true" />
                 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
              -->
                
                   <tr>
                  <td height="22" class="formtext"><label  class = "set" name="incomingServerIP" id="incomingServerIP" ondblclick="callShowDiv(this);"><%=label.get("incomingServerIP")%></label> :</td>
                  <td width="27%" height="22"><s:textfield	 name="inServerIP" size="29"  readonly="true"/>
                 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
                
                
                <tr>
                  <td height="22" class="formtext"><label  class = "set" name="inPort" id="inPort" ondblclick="callShowDiv(this);"><%=label.get("inPort")%></label> :</td>
                  <td width="27%" height="22"><s:textfield	 name="inServerPort" size="29"  readonly="true"/></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                  <tr>
                  <td height="22" class="formtext"><label  class = "set" name="inType" id="inType" ondblclick="callShowDiv(this);"><%=label.get("inType")%></label> :</td>
                  <td width="27%" height="22">
				<s:select name="serverType" disabled="true"
				 list="#{' ':'Select','pop3':'POP3','pop3/novalidate-cert':'POP3 (self-signed certificate)','pop3/notls':'POP3, no TLS','pop3/ssl':'POP3 over SSL','pop3/ssl/novalidate-cert':'POP3 over SSL (self-signed certificate)','imap':'IMAP','imap/novalidate-cert':'IMAP (self-signed certificate)','imap/notls':'IMAP, no TLS','imap/ssl':'IMAP over SSL','imap/ssl/novalidate-cert':'IMAP over SSL (self-signed certificate)'}" />
				 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
               
               <tr>
                  <td height="22" class="formtext"><label  class = "set" name="outGoingServerIP" id="outGoingServerIP" ondblclick="callShowDiv(this);"><%=label.get("outGoingServerIP")%></label> :</td>
                  <td width="27%" height="22"><s:textfield	 name="outServerIP" size="29" readonly="true" />
                 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
                
                
                <tr>
                  <td height="22" class="formtext"><label  class = "set" name="outPort" id="outPort" ondblclick="callShowDiv(this);"><%=label.get("outPort")%></label> :</td>
                  <td width="27%" height="22"><s:textfield	 name="outServerPort" size="29" readonly="true" /></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                
                </s:if>
                <s:else>
                
                <tr>
                  <td height="22" class="formtext"><label  class = "set" name="domainName" id="domainName" ondblclick="callShowDiv(this);"><%=label.get("domainName")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="serverName" size="29"  />
                 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
              
                
                   <tr>
                  <td height="22" class="formtext"><label  class = "set" name="incomingServerIP" id="incomingServerIP" ondblclick="callShowDiv(this);"><%=label.get("incomingServerIP")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="inServerIP" size="29"  />
                 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
                
                
                <tr>
                  <td height="22" class="formtext"><label  class = "set" name="inPort" id="inPort" ondblclick="callShowDiv(this);"><%=label.get("inPort")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="inServerPort" size="29" onkeypress="return numbersonly(this);" maxlength="10"/></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                  <tr>
                  <td height="22" class="formtext"><label  class = "set" name="inType" id="inType" ondblclick="callShowDiv(this);"><%=label.get("inType")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"> <s:select name="serverType"
				 list="#{'':'Select','pop3':'POP3','pop3/novalidate-cert':'POP3 (self-signed certificate)','pop3/notls':'POP3, no TLS','pop3/ssl':'POP3 over SSL','pop3/ssl/novalidate-cert':'POP3 over SSL (self-signed certificate)','imap':'IMAP','imap/novalidate-cert':'IMAP (self-signed certificate)','imap/notls':'IMAP, no TLS','imap/ssl':'IMAP over SSL','imap/ssl/novalidate-cert':'IMAP over SSL (self-signed certificate)'}" /></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
               
               <tr>
                  <td height="22" class="formtext"><label  class = "set" name="outGoingServerIP" id="outGoingServerIP" ondblclick="callShowDiv(this);"><%=label.get("outGoingServerIP")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="outServerIP" size="29"  />
                 </td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
                
                
                <tr>
                  <td height="22" class="formtext"><label  class = "set" name="outPort" id="outPort" ondblclick="callShowDiv(this);"><%=label.get("outPort")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="outServerPort" size="29"  onkeypress="return numbersonly(this);" maxlength="10"/></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                
       
                
                </s:else>
                 </s:if>
                <s:if test="chkFlag">
           <!--   <tr>
                  <td height="22" class="formtext"><label  class = "set" name="IttUserName" id="IttUserName" ondblclick="callShowDiv(this);"><%=label.get("IttUserName")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:textfield	 name="userName" size="29"  /></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                  <tr>
                  <td height="22" class="formtext"><label  class = "set" name="password" id="password" ondblclick="callShowDiv(this);"><%=label.get("password")%></label> :<font color="red">*</font></td>
                  <td width="27%" height="22"><s:password	 name="userPassword" size="29"  /></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>  
               -->
              
                <tr>
                  <td height="22" class="formtext"><label  class = "set" name="isOfficialMail" id="isOfficialMail" ondblclick="callShowDiv(this);"><%=label.get("isOfficialMail")%></label> :</td>
                  <td width="27%" height="22"><s:checkbox	 name="officialMailCheck"  /></td>
                  <td width="4%" height="22"></td>
                  <td width="17%" class="formtext"> </td>
                  <td width="28%"> </td>
               </tr>
                
                </s:if> 
                
                
                
                </table>
                </tr>
               
                </table>
                
                 
                </td>
                </tr>
                 <tr>
            <td width="78%">          
            		
            			<input type="button" class="save"
					 value="    Save " onclick="return callSave()" />
					
				
				
					 <s:if test="bean.viewFlag">					 
					<input type="button" class="reset" onclick="callReset();" 
						value="    Reset    " />
				    </s:if> 
						
						<input type="button" class="token" onclick="callBack();"
						value="     Back    " />	
				
				
				</td>           
          </tr>
               
    <s:hidden name="flagForCongMail" />
    <s:hidden name="serverName"/>
  </table>  
	</s:form>

<script>
	
	
	function callServerList(){
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="<%=request.getContextPath()%>/common/ChangePassword_setServerData.action";
	document.getElementById('paraFrm').submit();
	
	}
	
	
	
	function callSave(){ 
		var accountName=document.getElementById('paraFrm_accountName').value;
		var server=document.getElementById('paraFrm_serverList').value;
		try{
		var ittAccountName=document.getElementById('IttAccountName').innerHTML.toLowerCase();
		var ittUserName=document.getElementById('IttUserName').innerHTML.toLowerCase();
		var passName=document.getElementById('password').innerHTML.toLowerCase();
		var userName=document.getElementById('paraFrm_userName').value;
		var pass=document.getElementById('paraFrm_userPassword').value;
		}catch(e){
		
		}
		
		if(accountName==""){
		alert("Please enter "+ittAccountName);
		return false;	
		}	
		
		
		
		if(server==""){
		alert("Please select "+document.getElementById('server').innerHTML.toLowerCase());
		return false;	
		}
		if(server=='O'){
				if(document.getElementById('paraFrm_serverName').value==""){
				alert("Please enter "+document.getElementById('domainName').innerHTML.toLowerCase());
				return false;	
				}
				
				if(document.getElementById('paraFrm_inServerIP').value==""){
				alert("Please enter "+document.getElementById('incomingServerIP').innerHTML.toLowerCase());
				return false;	
				}
				
				if(document.getElementById('paraFrm_inServerPort').value==""){
				alert("Please enter "+document.getElementById('inPort').innerHTML.toLowerCase());
				return false;	
				}
			 	  	
			 	  	if(document.getElementById('paraFrm_serverType').value==""){
				alert("Please select "+document.getElementById('inType').innerHTML.toLowerCase());
				return false;	
				}
				
				  	if(document.getElementById('paraFrm_outServerIP').value==""){
				alert("Please enter "+document.getElementById('outGoingServerIP').innerHTML.toLowerCase());
				return false;	
				}
				
			 	  	  	if(document.getElementById('paraFrm_outServerPort').value==""){
				alert("Please enter "+document.getElementById('outPort').innerHTML.toLowerCase());
				return false;	
				}
			}
			
			if(userName==""){
		alert("Please enter "+ittUserName);
		return false;	
		}		
		
		if(pass==""){
		alert("Please enter "+passName);
		return false;	
		}	
			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/ChangePassword_save.action';	
			document.getElementById('paraFrm').submit();
	}
	
	
	  function callDelete(name) {
  			if(document.getElementById("paraFrm_trfId").value=="") {
  			alert("Please select the Transfer");
  			return false;
  			} else {
		
  			var conf=confirm("Are you sure to delete this record ? ");
  			if(conf) {
  			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/'+name;	
			document.getElementById('paraFrm').submit();	
  			
  			}
  	        }
  			}
	
	function numbersonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
function callReset(){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/ChangePassword_resetView.action';	
		document.getElementById('paraFrm').submit();
}

function callReset(){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/ChangePassword_resetView.action';	
		document.getElementById('paraFrm').submit();
}
function callBack(){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/ChangePassword_callOnLoad.action';	
		document.getElementById('paraFrm').submit();
}


	
	</script>
	<!-- action="ChangePassword_resetView" theme="simple" -->
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
