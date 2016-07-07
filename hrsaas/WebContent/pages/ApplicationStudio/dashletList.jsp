<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<s:form action="DefaultDashlet" method="post" name="DashletProfileSettings"
	validate="true" id="paraFrm" theme="simple">
	
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="93%" class="txt"><strong class="formhead">Dashlet List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
			</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="25%" colspan="1"><input type="button"
				class="add" name="Ok" value="  OK" onclick="setDashName();" />
				<input type="button"
				class="cancel" name="Close" value="  Close" onclick="window.close();" />
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
				<td width="80%" valign="top" class="formth"><label name="dashlet" id="dashlet"><%=label.get("dashlet") %></label></td>
				<td width="20%" valign="top" class="formth">Select</td>
				</tr>
				
				<%
					int y = 0;
				%>
				<%!
					int z = 0;%>
				<%
					Object[][] list = (Object[][])request.getAttribute("listObj");
					if(list != null && list.length > 0)
					{
						for(int i=0; i<list.length;i++)
						{
				%>
					<tr>
						<td class="td_bottom_border" width="80%"><%=list[i][1] %>
						<input type="hidden" name="dashCode" id="dashCode<%=y%>" value='<%=list[i][0] %>' /> 
						<input type="hidden" name="dashName" value='<%=list[i][1] %>'id="dashName<%=y%>" /> 
						</td>
						<td class="td_bottom_border" width="10%"><input type="radio"
							name="check" id="check<%=y%>" value='<s:property value="dashCode"/>' /></td>
					</tr>
					<%
						y++;
					%>
				<%
						}
					}
					z = y;
					y = 0;
				%>
				
			</table>
			</td>
		</tr>

		<tr>
			<td width="25%" colspan="1"><input type="button"
				class="add" name="Ok" value="  OK" onclick="setDashName();" />
				<input type="button"
				class="cancel" name="Close" value="  Close" onclick="window.close();" />
		</tr>
		
	</table>
</s:form>

<script>
	var id = opener.document.getElementById("hiddenDashId").value;
	function setDashName()
	{
		 var value ='<%=z%>';
		 var newVal = '';
		 var newCode = '';
		 var flag=false;
		 for(var i=0;i<value;i++)
		 {
			 if(document.getElementById('check'+i).checked)
			 {
		  		 newVal = document.getElementById('dashName'+i).value;
		  		 newCode = document.getElementById('dashCode'+i).value;
		  		 flag=true;
		  		 break;
		  	}
		 }
		 if(!flag)
		 {
		 	alert("Please select a "+document.getElementById("dashlet").innerHTML.toLowerCase());
		 	return false;
		 }
		 opener.document.getElementById('dashName'+id).value=newVal;
		 opener.document.getElementById('dashCode'+id).value = newCode;
		 window.close();
	}
</script>