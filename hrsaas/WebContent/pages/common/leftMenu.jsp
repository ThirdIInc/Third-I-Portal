 <link href="../pages/portal/images/style.css" rel="stylesheet"
 type="text/css" />
 
 <table width="180" border="0" cellspacing="5" cellpadding="1" id="mainTable" style="display:  none;">
	<tr valign="top" align="left">

	<td>
<table width="170" border="0" cellspacing="1" cellpadding="1">
	<tr>
		<td width="12%">
		<div align="center"><img
			src="../pages/mypage/images/icons/message.gif" width="34" height="26" /></div>
		</td>
		<td width="88%"><a
			href="javascript:void(0)" onclick="return callAction('Inbox');" class="link"><b>My Messages</b></a></strong><br />
		<span class="smalltext">Check my message Inbox</span></td>
	</tr>
	<tr>
		<td colspan="2" height="4"></td>
	</tr>
	<tr>
		<td>
		<div align="center"><img
			src="../pages/mypage/images/icons/consultant.png" width="40"
			height="40" /></div>
		</td>
		<td><a
			href="javascript:void(0)" onclick="return callAction('myservices');" class="link"><b>My Services</b></a></strong><br />
		<span class="smalltext">My self-services panel</span></td>
	</tr>
	<tr>
		<td colspan="2" height="4"></td>
	</tr>
	<tr>
		<td>
		<div align="center"><img
			src="../pages/mypage/images/icons/attendance.gif" width="34"
			height="34" /></div>
		</td>
		<td><a  onclick="return callAction('leaveattendance');"
			href="javascript:void(0)" class="link"><b>My Attendance</b></a></strong><br />
		<span class="smalltext">Track my leave &amp; attendance</span></td>
	</tr>
	<tr>
		<td colspan="2" height="4"></td>
	</tr>
	<tr>
		<td>
		<div align="center"><img
			src="../pages/mypage/images/icons/dashboard_basic_blue.gif"
			width="40" height="40" /></div>
		</td>
		<td><a href="javascript:void(0)" title="My Dashboard"><b>My
		Dashboard</b></a></strong><br />
		</span> <span class="smalltext">Monitor my dashboard</span></td>
	</tr>
	<tr>
		<td colspan="2" height="4"></td>
	</tr>
	<tr>
		<td>
		<div align="center"><img
			src="../pages/mypage/images/icons/myfavourite.gif" width="34"
			height="34" /></div>
		</td>
		<td><a href="javascript:void(0)"><b>My
		Favourites</b></a></strong><br />
		</span> <span class="smalltext">Manage my favourite links</span></td>
	</tr>
	<tr>
		<td colspan="2" height="4"></td>
	</tr>
	<tr>
		<td>
		<div align="center"><img
			src="../pages/mypage/images/icons/calendar.gif" width="34"
			height="39" /></div>
		</td>
		<td><a href="javascript:void(0)"><b>My
		Calendar</b></a></strong><br />
		</span> <span class="smalltext">Let me see my &amp; calendar</span></td>
	</tr>
	<tr>
		<td colspan="2" height="4"></td>
	</tr>
	<tr>
		<td>
		<div align="center"><img
			src="../pages/mypage/images/icons/dashboard.gif" width="34"
			height="33" /></div>
		</td>
		<td><a href="javascript:void(0)"><b>My
		Notes</b></a></strong><br />
		</span> <span class="smalltext">Manage my important notes</span></td>
	</tr>
	<tr>
		<td colspan="2" height="4"></td>
	</tr>
	<tr>
		<td><img src="../pages/mypage/images/icons/settings_events.png"
			width="40" height="40" /></td>
		<td><a href="javascript:void(0)"><b>My
		Analytics</b></a></strong><br />
		</span> <span class="smalltext">Download my reports</span></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</td>
<td width="1" bgcolor="#D9D9D9"><BR></td>

</tr></table>
<script>

function callAction(id)
{   



	//alert("id   "+id);
	try{
	alert(parent.frames[1]);
	parent.frames[1].style.width='0px';
	parent.frames[2].style.width='100%';
	if(id=="myservices")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_serviceData.action';
		document.getElementById('paraFrm').submit();
		return false;
		}
 
		if(id=="leaveattendance")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action';
		document.getElementById('paraFrm').submit();
		return false;
		}
 
		
		if(id=="Inbox")
		{
		 
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action?isClickOn=Input&hiddenMypageStatus=Inbox&ItemCount=I';
	 	//alert(document.getElementById('paraFrm').action);
		document.getElementById('paraFrm').submit();
		return false;
		}
	 
		
	}
	catch(e)
	{
		alert("Error  "+e);
	}
		return true;
}

 

if(window.screen.width == 1024)
{
try{
document.getElementById('mainTable').style.display = 'inline';
		}
	catch(e)
	{
		
	}
}
 

</script>



