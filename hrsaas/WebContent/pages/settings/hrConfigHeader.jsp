 <table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td width="80%">
		<div id="tabnav" style="vertical-align: bottom">
		<ul>
			<%
		try{
		String [] linkName = {"Corporate Information&nbsp;&nbsp;","Knowledge Information&nbsp;&nbsp;","Quick Information&nbsp;&nbsp;",
								"Opinion Poll&nbsp;&nbsp;","Announcement&nbsp;&nbsp;","Thought&nbsp;&nbsp;"};
		
		String [] actionName = {"showCorporate","showKnowledge","showGeneralInfo","showPoll",
								"showCompanyComm","showThought"};
			
         for (int i = 0; i < linkName.length; i++) { %>
			<li><a href="#" onclick="callPage('<%=actionName[i]%>')">
				<span><%=linkName[i] %></span></a></li>
			<% } 
						}catch(Exception e){
							System.out.println("Error Occured "+e);
							e.printStackTrace();
						}%>
		</ul>
		</div>
		</td>
	</tr>
</table>
<script>

	function callPage(menuAction)
	{
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").action="SettingMaster_"+menuAction+".action";
		document.getElementById("paraFrm").submit();	
	}
	
</script>