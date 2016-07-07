
<%@ taglib prefix="s" uri="/struts-tags"%>

 <script type="text/javascript" src="../pages/common/Ajax.js"></script>
 

<s:form action="RightTileAction" id="paraFrm" theme="simple"
	name="rightMyPage">

	<table width="170" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="dashborder">
						<tr>
							<td height="25" bgcolor="#f2f2f2" class="bottomborder">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="6%"><img
										src="../pages/mypage/images/icons/28.png" width="16"
										height="16" /></td>
									<td width="74%"><strong class="dasheader">Announcement
									</strong></td>
									<td width="22%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="67%">
											<div align="right"></div>
											</td>
											<td width="33%">
											<div align="right"><a href="javascript:void(0);"
												class="link"><img
												src="../pages/common/css/default/images/update1.gif"
												width="16" height="16" border="0" /></a></div>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="65"
								>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<%
									try {

									String[][] corpList = (String[][]) request
									.getAttribute("corpList");
							%>
								<%
										if (corpList != null && corpList.length > 0) {
										for (int i = 0; ((i < corpList.length) && i < 5); i++) {
							%>

								<tr>
									<td height="20" width="100%"><img align="absmiddle"
										src="../pages/common/css/default/images/dot.gif" /> 
									&nbsp;
									 <a 
										title="<%=corpList[i][0] %>" href="javascript:void(0);"
										class="contlink" onclick="show('<%=corpList[i][1] %>');">
									<%
 			try {
 			out.print(corpList[i][0].substring(0, 30) + "..");
 		} catch (Exception ex) {
 			out.println(corpList[i][0]);
 		}
 %> </a></td>
								</tr>

								<%
									}
									}
									if (corpList != null && corpList.length > 0) {
										for (int i = 0; i < 5 - corpList.length; i++) {
							%>

								<tr>
									<td height="20" width="100%"></td>
								</tr>
								<%
									}
									}

								} catch (Exception e) {

								}
							%>
								<tr>
									<td>&nbsp;</td>
									<td height="22" valign="bottom"><label>
									<div align="right"></div>
									</label></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>

					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="dashborder">
				<tr>
					<td height="25" bgcolor="#f2f2f2" class="bottomborder">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="6%"><img
								src="../pages/mypage/images/icons/34.png" width="16" height="16" /></td>
							<td width="74%" class="dasheader">Opinion Poll</td>
							<td width="22%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="67%">
									<div align="right"></div>
									</td>
									<td width="33%">
									<div align="right"><a href="javascript:void(0);"
										class="link"><img
										src="../pages/common/css/default/images/update1.gif"
										width="16" height="16" border="0" /></a></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<%
											Object[][] optionObj = (Object[][]) request
											.getAttribute("optionObj");
								%>
				<tr>
					<td>
					<%
									if (optionObj != null && optionObj.length > 0) {
									%> <s:hidden name="pollCode" id="pollCode" value="%{pollCode}" />
					<s:property value="quesName" /> <%
 }
 %>
					</td>
				</tr>

				<%
										if (optionObj != null && optionObj.length > 0) {
										int length = (optionObj.length) > 3 ? 3 : optionObj.length;
										for (int k = 0; k < length; k++) {
								%>
				<tr>
		<td height="22" valign="middle">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		<td valign="middle" width="2px">
		<input type="radio"
			value="<%=String.valueOf(optionObj[k][0]) %>" name="voteValue"
			onClick="setPollValue(this);" id="paraFrm_voteValue">
			</td><td valign="middle" align="left">
			 <span  
			class="blacklink"> &nbsp;<%=String.valueOf(optionObj[k][1])%> </span></td>
			</tr></table></td>
	</tr>
				<%
									}
									}
								%>

				<tr>
					<td height="27"><input type="hidden" name="optionValue"
						id="optionValue" />

					<div align="right"><img style="cursor: hand;"
						src="../pages/portal/images/submit.gif" width="51" height="24"
						onclick="submitPoll()" /></div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>

</s:form>
 
 
 

<script>




 
 //document.body.onload=parent.resizeRightPage(document.body.scrollHeight);
function show(id)
		{
		
		var wind = window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		}
		
		
		function setPollValue(id){
		document.getElementById('optionValue').value = id.value;
	}
	
	function submitPoll()
	{
		try{
		 
	 	var opt = document.getElementById('optionValue').value;
				
				if(opt == "") {
			alert('Please select an option');
			return false;
				}
				else{ 
			 
			retrievePollForEmployeePortal('RightTileAction_pollSave.action?random='+Math.random()+'&pollCode=' + document.getElementById('pollCode').value +'&optionCode=' + opt, 'rightMyPage');
			
				}
				
				
		}
		catch(e)
		{
			alert(e);
		}		
	
	}
	 
</script>