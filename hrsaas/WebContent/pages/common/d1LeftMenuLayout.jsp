

<table width="192" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top" align="left">

		<td>
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
				<td width="12%">
				<div align="center"><a href="javascript:void(0)"><img
					src="../pages/mypage/images/icons/settings.png" class="IconImage"
					title="Configurations" onclick="return callAction('D1C');" /></a></div>
				</td>
				<td width="88%"><a href="javascript:void(0)"
					onclick="return callAction('D1C');" class="servicelink"
					title="Configurations">Configurations</a><br/>
				<span class="smalltext">Perform Configurations</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td width="12%">
				<div align="center"><a href="javascript:void(0)"><img
					src="../pages/mypage/images/icons/process-32.png" /></a></div>
				</td>
				<td width="88%"><a href="javascript:void(0)"
					onclick="return callAction('D1A');" class="servicelink"
					title="Processes">Application</a><br />
				<span class="smalltext">Process candidate selection, screening, interview etc.</span></td> 
			</tr>

			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<tr>
				<td>
				<div align="center"><a href="javascript:void(0)"><img
					src="../pages/mypage/images/icons/settings_events.png" height="32"
					width="32" /></a></div>
				</td>
				<td nowrap="nowrap"><a onclick="return callAction('D1R');"
					title="Reports" href="javascript:void(0)" class="servicelink">Reports</a><br />
				<span class="smalltext">Generate Reports</span></td>
			</tr>
			<tr>
				<td colspan="2" height="4"></td>
			</tr>


			<tr>
				<td colspan="5"><img
					src="../pages/common/css/default/images/space.gif" width="5"
					height="100" /></td>
			</tr>

			<tr>
				<td colspan="2" style="height: 1px; background-color: #EBEBEB;">

				</td>
			</tr>
			<tr>
				<td width="12%">
				<div align="center"><img
					src="../pages/mypage/images/icons/message.png" /></div>
				</td>
				<td width="88%"><a href="javascript:void(0)"
					onclick="return callMyPageAction('Inbox');" class="servicelink"
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
					onclick="return callMyPageAction('myservices');"
					class="servicelink" title="My Services">My Services</a><br />
				<span class="smalltext">My self-services panel</span></td>
			</tr>

			<tr>
				<td colspan="2" height="4"></td>
			</tr>
			<!-- <tr>
				<td>
				<div align="center"><img
					src="../pages/mypage/images/icons/attendance.png" /></div>
				</td>
				<td nowrap="nowrap"><a
					onclick="return callMyPageAction('leaveattendance');"
					title="My Attendance" href="javascript:void(0)" class="servicelink">My
				Attendance</a><br />
				<span class="smalltext">Track my leave & attendance</span></td>
			</tr> -->

		</table>
		</td>


	</tr>
</table>
<script>
function callMyPageAction(id)
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
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action';
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
		
		
		
	}
	catch(e)
	{
		 //alert("Error  "+e);
	}
	}
function callAction(id)
{   
//alert(id);
	try{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/HomePage_getDecisionOneMenu.action?menuType='+id+'&mymessage_random='+Math.random();
		document.getElementById('paraFrm').submit();
		return true;
	}
	catch(e)
	{
		 //alert("Error  "+e);
	}
	 	
		return true;
}
</script>

