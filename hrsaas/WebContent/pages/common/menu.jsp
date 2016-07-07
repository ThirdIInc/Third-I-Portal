

<table width="192" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top" align="left">

		<td>
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
				<td width="12%">
				<div align="center"><img
					src="../pages/mypage/images/icons/message.png" /></div>
				</td>
				<td width="88%"><a href="javascript:void(0)"
					onclick="return callAction('Inbox');" class="servicelink"
					title="My Messages">My Messages</a><br />
				<span class="smalltext">Check my message Inbox</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td width="12%">
				<div align="center"><img
					src="../pages/mypage/images/icons/consultant.png" /></div>
				</td>
				<td width="88%"><a href="javascript:void(0)"
					onclick="return callAction('myservices');" class="servicelink"
					title="My Services">My Services</a><br/>
				<span class="smalltext">My self-services panel</span></td> 
			</tr>
			 
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td>
				<div align="center"><img
					src="../pages/mypage/images/icons/attendance.png" /></div>
				</td>
				<td nowrap="nowrap"><a
					onclick="return callAction('leaveattendance');"
					title="My Attendance" href="javascript:void(0)" class="servicelink">My
				Attendance</a><br/>
				<span class="smalltext">Track my leave & attendance</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td>
				<div align="center"><img
					src="../pages/mypage/images/icons/dashboard_basic_blue.png"
					width="40" height="40" /></div>
				</td>
				<td><a href="javascript:void(0)"
					onclick="return callAction('mydashboard');" class="servicelink"
					title="My Dashboard">My Dashboard</a><br/>
				<span class="smalltext">Monitor my dashboard</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td>
				<div align="center"><img
					src="../pages/mypage/images/icons/myfavourite.gif" /></div>
				</td>
				<td><a href="javascript:void(0)" class="servicelink"
					onclick="return callAction('myFavourites');" title="My Favorites">My
				Favorites</a><br/>
				<span class="smalltext">Manage my favourite links</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td>
				<div align="center"><img
					src="../pages/mypage/images/icons/calendar.png" /></div>
				</td>
				<td><a href="javascript:void(0)" class="servicelink"
					onclick="return callAction('mytask');" title="My Task">My Task</a><br/>
				<span class="smalltext">Let me manage my tasks</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td>
				<div align="center"><img
					src="../pages/mypage/images/icons/dashboard.png" /></div>
				</td>
				<td><a href="javascript:void(0)" class="servicelink"
					onclick="return callAction('mynotes');" title="My Notes">My
				Notes</a><br />
				<span class="smalltext">Manage my important notes</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td><img src="../pages/mypage/images/icons/settings_events.png" /></td>
				<td><a href="javascript:void(0)" class="servicelink"
					onclick="return callAction('myanalytics');" title="My Analytics">My
				Analytics</a><br/>
				<span class="smalltext">Download my reports</span></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>


	</tr>
</table>
<script>

function callAction(id)
{   

	//alert("id   "+id);
	try{
	
	if(id=="myservices")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_serviceData.action?menuType=ES&mymessage_random='+Math.random();
		document.getElementById('paraFrm').submit();
		return true;
		}
 
		if(id=="leaveattendance")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action?showMyCardFlag=true';
		document.getElementById('paraFrm').submit();
		return true;
		}
 
		
		if(id=="Inbox")
		{
				 
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action?isClickOn=Input&hiddenMypageStatus=Inbox&ItemCount=I&clearStatus=Y';
	 	//alert(document.getElementById('paraFrm').action);
		document.getElementById('paraFrm').submit();
		return true;
		}
		
		if(id=="mynotes")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/MyNotes_input.action';
	 	document.getElementById('paraFrm').submit();
		return true;
		}
		
		if(id=="myanalytics")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_analyticsData.action?menuType=RT';
		document.getElementById('paraFrm').submit();
		return true;
		}
	 
		if(id=="mytask")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/WeekPlanner_newTask.action';
		document.getElementById('paraFrm').submit();
		return true;
		}
		
		if(id=="mydashboard")
		{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_dashBoard.action';
		document.getElementById('paraFrm').submit();
		return true;
		}
	 
	 
	 	if(id=="myFavourites")
		{
			document.getElementById('paraFrm').target = "_self";
		 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/MyFavourites_input.action';
			document.getElementById('paraFrm').submit();
			return true;
		}
		
	}
	catch(e)
	{
		 //alert("Error  "+e);
	}
	 	
		
 
		return true;
}
</script>

