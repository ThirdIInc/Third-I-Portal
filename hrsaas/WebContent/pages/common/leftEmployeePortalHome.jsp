<%@page import="java.util.HashMap"%>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left">
		<table id="Table_01" width="230" border="0" cellpadding="0"
			cellspacing="0">


			<tr>
				<td valign="top">
				<table width="97%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="dashborder">
					<tr>
						<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" bgcolor="#f2f2f2">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="6%"><img
											src="../pages/mypage/images/icons/34.png" width="16"
											height="16" /></td>
										<td width="74%" class="dasheader">&nbsp;&nbsp;Apps</td>
										<td width="22%">
										<div align="right">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="67%">
												<div align="right"></div>
												</td>
												<td width="33%">
												<div align="right"></div>
												</td>
											</tr>
										</table>
										<a href="javascript:void(0);" class="link"></a></div>
										</td>
									</tr>
								</table>
								</td>
							</tr>

							<%
										HashMap<String, String> appluserName = null;
										HashMap<String, String> applPassword = null;

										try {
											appluserName = (HashMap<String, String>) request
											.getAttribute("applUserName");
											applPassword = (HashMap<String, String>) request
											.getAttribute("applPassword");
										} catch (Exception e) {
											appluserName = null;
											applPassword = null;
											e.printStackTrace();
										}
									%>
							<tr>
								<td height="18" colspan="2" nowrap="nowrap">
								<form STYLE="margin: 0px; padding: 0px;" name="imp_login"
									id="imp_login"
									action="http://mymail.glodyne.com/imp/redirect.php"
									method="post" target="_blank"><input type="hidden"
									id="imapuser" name="imapuser"
									value="<%=appluserName.get("1")%>" /> <input type="hidden"
									id="pass" name="pass" value="<%=applPassword.get("1")%>" /> <input
									type="hidden" id="new_lang" name="new_lang" value="en_US">
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/Mail.png"
								align="absmiddle" style="padding-left: 5px;
margin-bottom: 2px;
	margin-top: 2px;"
								>
								<a href="javascript:void(0);" name="Submit2" class="contlink"
									title="Check My Mails"
									onclick="return callAction('<%=appluserName.get("1")%>','1','imp_login');">Check
								My Mails</a></form>
								</td>
							</tr>
							<tr>

								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;" name="form1"
									method="post"
									action="http://empower.glodyne.com/empower/Default.aspx"
									id="form1" target="_blank"><input type="hidden"
									name="__VIEWSTATE" id="__VIEWSTATE"
									value="/wEPDwUJNTY3OTUxNjY1D2QWAgIDD2QWAgIBDxYCHgNzcmMFGEhhbmRsZXIuYXNoeD9JZD0xJlR5cGU9RGRkDxUohRC7vs9QuGEzdJewO1qUzNE=" />
								<input name="Username" type="hidden" id="Username" class="login"
									value="<%=appluserName.get("2")%>" /> <input name="Password"
									type="hidden" id="Password" class="login"
									value="<%=applPassword.get("2")%>" /> 
									<img src="<%=request.getContextPath()%>/pages/portal/images/icons/seles.gif" align="absmiddle"/>
									<a name="LoginButton"
									id="LoginButton" class="contlink" href="javascript:void(0);"
									title="Sales Management System"
									onclick="return callAction('<%=appluserName.get("2")%>','2','form1');">Sales
								Management System</a></form>
								</td>
							</tr>
							<tr height="25">
								<td height="18" colspan="2">
								
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/ser.png" 
								
								style="padding-left: 5px;
margin-bottom: 2px;
	margin-top: 2px;"
								align="absmiddle" />
								
								<a name="Submit2"
									title="Servicedesk System" href="javascript:void(0);"
									class="contlink">Servicedesk System</a></td>
							</tr>
							<tr>
								<td height="18" colspan="2">

								<form STYLE="margin: 0px; padding: 0px;" name="serviceDesk"
									id="serviceDesk" method="post"
									action=" http://180.179.85.34:8085/cgi-bin/staff.cgi"
									target="_blank"><input type="hidden" name="username"
									class="itext" value="<%=appluserName.get("4")%>" /> <input
									type="hidden" name="password" class="itext"
									value="<%=applPassword.get("4")%>" /> <input type="hidden"
									name="do" value="pro_login" /> 
									
									<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
								
									<a name="Submit2"
									href="javascript:void(0);"  class="contlink"
									onclick="return callAction('<%=appluserName.get("4")%>','4','serviceDesk');"
									title="Servicedesk">Servicedesk</a></form>
								</td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;" name="helpDeskPerl"
									id="helpDeskPerl" method="post"
									action="http://imssupport.glodyne.com:1498/cgi-bin/staff.cgi"
									target="_blank"><input type="hidden" name="username"
									class="itext" value="<%=appluserName.get("5")%>" /> <input
									type="hidden" name="password" class="itext"
									value="<%=applPassword.get("5")%>" /> <input type="hidden"
									name="do" value="pro_login" /> 
										<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
									<a name="Submit2"
									href="javascript:void(0);" class="contlink"
									onclick="return callAction('<%=appluserName.get("5")%>','5','helpDeskPerl');"
									title="Help Desk - Perl.">Help Desk - Perl.</a></form>
								</td>
							</tr>

							<tr>
								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;" name="sapphire"
									id="sapphire" method="post"
									action="http://220.225.70.166/SapphireMSP/SapphireMSPLogin"
									target="_blank"><input name="UserID" id="UserID"
									type="hidden" value="<%=appluserName.get("6")%>"> <input
									name="UserPassword" id="UserPassword" type="hidden"
									value="<%=applPassword.get("6")%>">
									
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
									 <a name="Submit2"
									href="javascript:void(0);" class="contlink"
									onclick="return callAction('<%=appluserName.get("6")%>','6','sapphire');"
									title="Help Desk - sapphire">Help Desk - sapphire</a></form>
								</td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;"
									action="http://220.227.119.177:8088/india/index.php"
									method="post" name="DetailView" id="form" target="_blank">
								<input type="hidden" name="module" value="Users"> <input
									type="hidden" name="action" value="Authenticate"> <input
									type="hidden" name="return_module" value="Users"> <input
									type="hidden" name="return_action" value="Login"> <input
									type="hidden" size='20' id="user_name" name="user_name"
									value="<%=appluserName.get("7")%>" /> <input type="hidden"
									size='20' id="user_password" name="user_password"
									value="<%=applPassword.get("7")%>" /> 
									
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
									
									<a name="Submit2"
									class="contlink" href="javascript:void(0);"
									onclick="return callAction('<%=appluserName.get("7")%>','7','form');"
									title="Support Sales System">Support Sales System</a></form>

								</td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
								
								<a name="Submit2"
									href="javascript:void(0);" title="Supply Chain Management"
									class="contlink"> Supply Chain Management</a></td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;" name="aspnetForm"
									method="get" action="http://220.227.87.210/csm/frmLogin.aspx"
									id="aspnetForm" target="_blank"><input type="hidden"
									name="__VIEWSTATE" id="__VIEWSTATE"
									value="/wEPDwUKLTI2MjA4NTA1MA9kFgJmD2QWAgIDD2QWBmYPDxYCHgdWaXNpYmxlaGRkAgQQPCsADQEADxYCHwBoZGRkAgUPZBYEZg8PZBYEHgdvbkZvY3VzBRxEb0ZvY3VzKHRoaXMsJ2lucHV0dHh0ZmNzJyk7HgZvbkJsdXIFGERvQmx1cih0aGlzLCdpbnB1dHR4dCcpO2QCAQ8PZBYEHwEFHERvRm9jdXModGhpcywnaW5wdXR0eHRmY3MnKTsfAgUYRG9CbHVyKHRoaXMsJ2lucHV0dHh0Jyk7ZGTF60tvFtPutKF1oTnhURjsIUyswA==" />
								<input name="ctl00$ContentPlaceHolder1$txtUser" type="hidden"
									id="ctl00_ContentPlaceHolder1_txtUser"
									value="<%=appluserName.get("9")%>" /> <input
									name="ctl00$ContentPlaceHolder1$txtPass" type="hidden"
									id="ctl00_ContentPlaceHolder1_txtPass"
									value="<%=applPassword.get("9")%>" /> <input type="hidden"
									name="__EVENTVALIDATION" id="__EVENTVALIDATION"
									value="/wEWBAL+/sT+BgKlhNbxBQKqw6i0BAKwkIqMDemy5uKhJpSlIuhNi6AJ9NuXw9k6" />
							
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
								
								
								<a name="ctl00$ContentPlaceHolder1$imgLogin" class="contlink"
									href="javascript:void(0);"
									id="ctl00_ContentPlaceHolder1_imgLogin"
									onclick="return callAction('<%=appluserName.get("9")%>','9','aspnetForm');"
									title="Education">Education</a></form>
								</td>

							</tr>
							<tr>
								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;" name="peoplepower"
									id="peoplepower" method="post"
									action="http://220.225.70.166/PeoplePower/login.jsp"
									target="_blank"><input name="login" type="hidden"
									class="mcopy" value="<%=appluserName.get("10")%>"> <input
									name="pass" type="hidden" class="mcopy"
									value="<%=applPassword.get("10")%>" /> 
									
							<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
									<a name="Submit2"
									href="javascript:void(0);" class="contlink"
									onclick="return callAction('<%=appluserName.get("10")%>','10','peoplepower');"
									title="Peoplepower">Peoplepower</a></form>
								</td>
							</tr>
							<tr>

								<td height="18" colspan="2">
								
									<img src="<%=request.getContextPath()%>/pages/portal/images/icons/webone.jpg" 
								align="absmiddle"  	style="padding-left: 5px;
margin-bottom: 2px;
	margin-top: 2px;"/>
								
								
								<a href="javascript:void(0);"
									onclick="javascript:window.open('http://whizible.compulinkgroup.com/Default.aspx');"
								class="contlink"	  title="Project Management System">
								Project Management System</a></td>
							</tr>

							<tr>

								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;"
									action="http://220.225.70.163/glodynelms/login/index.php"
									method="post" name="learningCenter" id="learningCenter"
									target="_blank"><input type="hidden" name="username"
									value="<%=appluserName.get("12")%>" /> <input type="hidden"
									name="password" value="<%=applPassword.get("12")%>" /> 
									
										<img src="<%=request.getContextPath()%>/pages/portal/images/icons/learning.png" 
											style="padding-left: 5px;
margin-bottom: 2px;
	margin-top: 2px;"
								align="absmiddle" />
								
									<a
									name="Submit2" href="javascript:void(0);"
									onclick="return callAction('<%=appluserName.get("12")%>','12','learningCenter');"
									class="contlink"   title="Learning Center">Learning Center</a>
								</form>
								</td>
							</tr>

							<tr>

								<td height="18" colspan="2">
								
										<img src="<%=request.getContextPath()%>/pages/portal/images/icons/ser.png"  	style="padding-left: 5px;
margin-bottom: 2px;
	margin-top: 2px;"
								align="absmiddle" />
								
								<a name="Submit2" 
									href="javascript:void(0);"   class="contlink" 
									title="Astea Applications">Astea Applications</a></td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
								
								<a name="Submit2"
									href="javascript:void(0);" class="contlink"
									onclick="javascript:window.open('http://webone.decisionone.com/');"
									title="WebOne">WebOne</a></td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;" name="dispatchExtender"
									method="post"
									action="http://astea2.decisionone.com/D1AsteaExtender/forms/feportal/Dispatch/Default.aspx"
									id="dispatchExtender" target="_blank"><input
									type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
									value="/wEPDwUJMTYxNzM1NDExZGQw+uyWLEidHa5rIx31GY/oPScfQw==" />
								<input name="uiEmployeeID" type="hidden" id="uiEmployeeID"
									value="<%=appluserName.get("15")%>" /><input name="uiPassword"
									type="hidden" id="uiPassword"
									value="<%=applPassword.get("15")%>" /> <input type="hidden"
									name="__EVENTVALIDATION" id="__EVENTVALIDATION"
									value="/wEWBAKSoJzgDAKhy5C9CQL8lea9CAL6zZ3lAj8pCYCPoJqn1E8crlemYAgl8dku" />
									
							<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
								

								<a name="uiLogin" class="contlink"  id="uiLogin"
									href="javascript:void(0);"
									onclick="return callAction('<%=appluserName.get("15")%>','15','dispatchExtender');"
									title="Dispatch Extender">Dispatch Extender</a> <!--<input type="submit"
											name="uiLogin" value="Login" id="uiLogin" />
										--></form>

								</td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								
								<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								
								
								<a href="javascript:void(0);"
									onclick="javascript:window.open('http://pmaphqs019.decisionone.com/AsteaAlliance80/');"
									name="Submit2" class="contlink" title="Native Astea">Native
								Astea</a></td>
							</tr>
							<tr>
								<td height="18" colspan="2">
								<form STYLE="margin: 0px; padding: 0px;" name="nts"
									method="post"
									action="https://astea2.decisionone.com/D1AsteaPartOrder/Default.aspx"
									id="nts" target="_blank"><input type="hidden"
									name="__LASTFOCUS" id="__LASTFOCUS" value="" /> <input
									type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="" />
								<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT"
									value="" /> <input type="hidden" name="__VIEWSTATE"
									id="__VIEWSTATE"
									value="/wEPDwUJMTYxNzM1NDExZGQ9zWQ88F87GvvBAc6kKWoMcgrgfw==" />
								<input name="uiEmployeeID" type="hidden" id="uiEmployeeID"
									value="<%=appluserName.get("17")%>" /> <input
									name="uiPassword" type="hidden" id="uiPassword" value="value=" <%=applPassword.get("17")%>" />
								<input type="hidden" name="__EVENTVALIDATION"
									id="__EVENTVALIDATION"
									value="/wEWBALT2N2HCwKhy5C9CQL8lea9CAL6zZ3lAknZBu5ieS+n2VF/PHU6TlVBFXoM" />
									
									<img src="<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif" 
												style="padding-left: 20px;margin-left:10px;"
								align="absmiddle" />
								

								<a name="uiLogin" href="javascript:void(0);" class="contlink"
									onclick="return callAction('<%=appluserName.get("17")%>','17','nts');"
									title="NTS">NTS</a></form>

								</td>
							</tr>
							<tr>
								<td height="3px" colspan="2"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
		</table>

		</td>
	</tr>
</table>

<script>

 
function callAction(id,id1,id2)
{   
	 	if(id=="" || id=="null" || id==null)
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '../event/ConfigureApplCredential_input.action?applCode='+id1;
		document.getElementById('paraFrm').submit();
		return false;
		}
		else{
		document.getElementById(id2).submit();
		}
		return true;
}

</script>
