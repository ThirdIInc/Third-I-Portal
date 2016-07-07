
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@ taglib prefix="s" uri="/struts-tags"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>PeoplePower</title>



<link href="../pages/portal/images/dropdown-menu.css" media="screen"
	rel="stylesheet" type="text/css" />


<link href="../pages/portal/images/style.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" title="default-theme"
	href="<%=request.getContextPath()%>/pages/common/commonCSS.jsp" />




</head>

<body>
<div id="MainDiv" onblur="self.focus()"></div>
<!-- Code for Relogin window starts -->
<input type="hidden" name="divMovementHidden" id="divMovementHidden" />
<div id="optionDiv"
	style='position: absolute; z-index: 3; width: 470px; height: 120px; display: none; border: 2px solid; top: 150px; left: 250px; padding: 10px'
	class="formbg">



<TABLE WIDTH=276 BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD width="10" class="relogin"></TD>
		<TD width="262" class="relogin1"></TD>
		<TD width="10" class="relogin2"></TD>
	</TR>
	<%
			String pool = (String) session.getAttribute("poolId");
			String user = (String) session.getAttribute("lname");
		%>
	<TR>
		<TD class="relogin3">&nbsp;</TD>
		<TD class="reloginbg">
		<table width="100%" border="0" align="center" cellpadding="2"
			cellspacing="2">
			<tr>
				<td height="25" colspan="2">
				<div align="center"><span class="reloginstyle">
				<div id="timerDiv">Your session is about to expire in <span
					id="timerDiv2"></span>Min.<span id="timerDiv1"></span> Sec. Please
				Relogin</div>
				</span></div>
				</td>
			</tr>
			<tr>
				<td height="25"><span class="style1">Username</span></td>
				<td><input name="loginName" type="text" class="box" readonly
					value="<%=user%>" size="20" /> <input name="poolId" type="hidden"
					class="box" value="<%=pool%>" size="20" /></td>
			</tr>
			<tr>
				<td height="22" class="style1">Password</td>
				<td height="22" class="style1"><input name="loginPassword"
					type="password" class="box" value="" size="20" id="loginPassword" /></td>
			</tr>
			<tr>
				<td height="25" colspan="2">
				<div align="center"><input type="button" id="continue"
					value="Continue" class="token" onclick="return reLoginSubmit();" />
				<input type="button" onclick="logout();" value=" Logout "
					class="token" /></div>
				</td>
			</tr>
		</table>
		</TD>
		<TD class="relogin4"></TD>
	</TR>
	<TR>
		<TD class="relogin5">&nbsp;</TD>
		<TD class="relogin6">&nbsp;</TD>
		<TD class="relogin7">&nbsp;</TD>
	</TR>
</TABLE>
<input type="hidden" name="infoId" value="<%=pool%>" /> <s:hidden
	name="name" value="%{name}" /></div>

<!-- Code for Relogin window ends -->

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#FF6600">
	<tr>
		<td height="4px"></td>
	</tr>
</table>

<%
	String homeCode = "";
	try{
	if (session.getAttribute("homeCode") != null
			&& !("" + session.getAttribute("homeCode")).equals("null"))
		homeCode = String.valueOf(session.getAttribute("homeCode"));
	}catch(Exception e)
	{
		
	}
%>
<input type="hidden" id="hdMenuId" name="hdMenuCode"
	value="<%=homeCode%>" />

<%
	
	String cmpName = null;
		try{
	cmpName = (String) request.getAttribute("logo");
		}
		catch(Exception e)
		{
			
		}
	System.out.println("String cmpName =" + cmpName);
%>
<table width="99%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td valign="bottom">
		<div style="width: 100%">

		<div style="float: left; width: 40%"><img
			src="../pages/common/css/default/images/logo.jpg" /></div>

		<div style="float: right; width: 60%">
		<table width="80%" height="51" border="0" align="right"
			cellpadding="0" cellspacing="0">


			<tr>
				<td width="72%" align="right">
				<div align="right" class="emptext"><strong><%=request.getAttribute("company_name")%></strong></div>
				</td>
				<td width="4%">&nbsp;</td>
				<td width="24%" align="right"><img style="padding: 2px;"
					src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
					height="50" /></td>
			</tr>
		</table>
		</div>


		<div
			style="height: 1px; background-color: #f2f2f2; float: left; width: 100%"></div>
		<div>

		<div style="float: left; width: 25%; margin: 0; padding: 0;"></div>
		<div style="float: right; width: 75%" id="menu"></div>
		</div>

		<div style="float: right; width: 60%">
		<table width="80%" border="0" align="right" cellpadding="0"
			cellspacing="0">


			<tr>
				<td width="72%" align="right"></td>
				<td width="4%">&nbsp;</td>
				<td width="24%" align="right"><a class="contlink"
					href="<%=request.getContextPath()%>/vendor/VendorLogin_logout.action"
					target="_top"> <b>Logout</b> </a></td>
			</tr>
		</table>
		</div>

		<div
			style="background-color: #6979ac; float: left; width: 100%; margin: 0; padding: 0;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25px" class="mainheader" align="left" width="30%">
				<%
				String partnerName="";
				try{
					partnerName=(String)request.getAttribute("partnerName");
				}
				catch(Exception e)
				{
					partnerName="";
				}
				%> <b> &nbsp;Welcome <%=partnerName %> !</b></td>
			</tr>
		</table>
		</div>


		<div style="float: left; width: 100%"><s:form
			action="VendorHomePage" id="paraFrm" theme="simple" name="mainForm">

			<%
				String homePage = "";
				if (session.getAttribute("homePage") != null)
					homePage = String.valueOf(session.getAttribute("homePage"));
				else
					homePage = "/common/HomePage_input.action";
			%>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="202" valign="top"><iframe frameborder="0"
								style="vertical-align: top; float: left;"
								src="<%=request.getContextPath()%>/vendor/VendorHomePage_create.action"
								name="contents" scrolling="no" marginwidth="0" height="450"
								width="200" marginheight="0" frameborder="0" vspace="0"
								style="float:top"> </iframe><iframe
								src="<%=request.getContextPath()%>/pages/common/hiddenFrame.jsp&app_random=<%=java.lang.Math.random()%>"
								name="hiddenFrame" scrolling="no" marginwidth="0"
								marginheight="0" frameborder="0" vspace="0" width="0" height="0">
							</iframe></td>
							<td valign="top" align="left"><iframe id="myframe"
								frameborder="0" style="float: left" allowtransparency="yes"
								scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
								name="main" width="800" height="0"
								style="vertical-align: top; float: right;"> </iframe></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

		</s:form></div>
		</td>
	</tr>
</table>
</body>
</html>


<script>
document.getElementById('myFrame').width=(window.screen.width-210);



function alertsize(pixels){
	document.body.style.offsetHeight="0px";
    pixels+=30;
  //alert('before'+ document.getElementById('myframe').style.height);
    document.getElementById('myframe').style.height=pixels+"px";
  //alert('after'+ document.getElementById('myframe').style.height);    
}


function callSubMenu(menuCode) {

try{
 alert('callSubMenu -- menuCode : '+menuCode);
var width = (window.screen.width-210);
		document.getElementById('myframe').width=width;
		document.getElementById('hdMenuId').value=menuCode;
		document.getElementById("paraFrm").target="contents";
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/vendor/VendorHomePage_create.action?menuCode='+menuCode;
		 alert('action Name  '+document.getElementById("paraFrm").action);
		document.getElementById("paraFrm").submit();	
}
catch(e)
{
	alert(e);
}
	
}

function callCenterPage(menuCode,menuLink){
 	 alert('callCenterPage -- menuLink : '+menuLink);
	//parent.frames[1].location=parent.frames[1].location;
	if(menuLink=='null') {
		menuLink='/vendor/VendorHomePage_input.action';
	}
	document.getElementById('hdMenuId').value=menuCode;
	document.getElementById("paraFrm").target="main";
	document.getElementById("paraFrm").action=menuLink;
	document.getElementById("paraFrm").submit();
}

function callCurrent(id, num) {
	 //alert("callCurrent : "+id);
		for(var i=0; i<num; i++) {
			document.getElementById('menuid'+(i+1)).className='li';
		}
		document.getElementById('menuid'+id).className='on';
	}
</script>