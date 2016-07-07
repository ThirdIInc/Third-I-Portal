<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.model.admin.master.PFParameterModel"%>

<%
	String [][]pfTabData=null;
	PFParameterModel model=new PFParameterModel();
	model.initiate(config.getServletContext(),session);
	try{
		pfTabData = model.getPFTabs();
	}catch(Exception e){
		e.printStackTrace();
	}
	model.terminate();

	%><table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td width="80%">
		<div id="tabnav" style="vertical-align: bottom">
		<ul>
			<%
		try{
		String [] linkName = new String[pfTabData.length];
		
		String [] actionName = new String [pfTabData.length];
		
		for (int i = 0; i < pfTabData.length; i++) { 
			linkName[i] =  pfTabData[i][0];
			actionName[i] =  pfTabData[i][1];
		}
			
         for (int i = 0; i < linkName.length; i++) { %>
			<li><a href="#" id='<%=linkName[i]+"tabId"%>' onclick="callPageTab('<%=actionName[i]%>')">
				<span><%=linkName[i] %></span></a></li>
			<% } %>
			<li><a href="#" id="configEmpTab" onclick="callPageTab('getConfigEmpScreen')">
				<span><%="Configure Employees For PF" %></span></a></li>
						<%}catch(Exception e){
							System.out.println("Error Occured "+e);
							e.printStackTrace();
						}%>
		</ul>
		</div>
		</td>
		<td align="right"><!--<s:submit cssClass="back" value="   Back " action='PFParameter_input' />-->	
		</td>
	</tr>
</table>
<script>

	function callPageTab(menuAction)
	{
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").action="PFParameter_"+menuAction+".action";
		document.getElementById("paraFrm").submit();	
	}
	
</script>