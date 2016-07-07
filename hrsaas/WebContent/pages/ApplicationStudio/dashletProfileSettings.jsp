<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<s:form action="DashletProfileSettings" method="post"
	name="DashletProfileSettings" validate="true" id="paraFrm"
	theme="simple">
	<!-- Flags used For Cancel Button -->

	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Dashlet Profile Configuration </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="15%" colspan="1"><label name="profile" id="profile" ondblclick="callShowDiv(this);"><%=label.get("profile") %></label> : </td>
					<td width="85%" width="50%"><s:hidden name="profileID" />
					<s:textfield name="profileName" theme="simple" size="25"
							maxlength="50" readonly="true"/>
					<img src="../pages/common/css/default/images/search2.gif"
							width="16" height="15"
							onclick="javascript:searchFun();">			
					</td>
				</tr>
				</td>
			</table>

						<%
						Object[][] listObj = null;
								try {
								listObj = (Object[][]) request
								.getAttribute("listObj");
								}catch(Exception e)
								{
									e.printStackTrace();
								}
								//System.out.println("listObj====="+listObj.length);
								if (listObj != null && listObj.length > 0) {
						%>
			
		<tr>
			<td colspan="3">
			<table class="formbg" width="100%">

				<tr>
					<td class="formth" width="10%"><label name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no") %></label></td>
					<td class="formth" width="70%"><label name="available.dashlet" id="available.dashlet" ondblclick="callShowDiv(this);"><%=label.get("available.dashlet") %></label></td>
					<td align="center" class="formth" width="70%"><label name="activate" id="activate" ondblclick="callShowDiv(this);"><%=label.get("activate") %></label><input type="checkbox" name="activateAll" id="activateAll" onclick="checkAll();"></td>
				</tr>
				<%
										int count = 0;
									%>
				<%!int d1 = 0;%>
				<%
											try {

											for (int j = 0; j < listObj.length; j++) {
									%>

				<tr>
					<td width="10%" class="td_bottom_border"><%=++count%><input type="hidden"
						name="srNo" value="<%=count%>" /></td>

					<td class="td_bottom_border"><%=String.valueOf(listObj[j][1]) %><input type="hidden"
						value="<%= String.valueOf(listObj[j][0]) %>" name="dashcode" />
						<input type="hidden"
						value="<%= String.valueOf(listObj[j][1]) %>" name="dashName" /></td>
					<%if(listObj[j][2].equals("Y"))
						{%>
					<td width="10%" class="td_bottom_border" align="center"><input
						type="checkbox" class="checkbox" value="<%= j%>" id="confChk<%=j%>" checked="checked"
						name="confChk" onclick="checkForAll();" /></td>
						<%}else
						{ %>
						<td width="10%" class="td_bottom_border" align="center"><input
						type="checkbox" class="checkbox" value="<%= j%>" id="confChk<%=j%>"
						name="confChk" onclick="checkForAll();" /></td>	
					<%	}
					%>
				</tr>
				<%
									d1 = count;
										}
									count=0;
									} catch (Exception e) {

								}
								%>
				<%
									}
								
								%>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	checkForAll();
	var f9Fields= ["paraFrm_profileName"];
	var fieldName = ["paraFrm_profileName"];
	var labelName = ["profile"];
	var type = ['select'];
	function searchFun()
	{
		callsF9(500,300,"DashletProfileSettings_f9Action.action");
		
	}
	
	function saveFun()
	{
		if(!validateBlank(fieldName, labelName, type))
	    	return false;
	    if(!f9specialchars(f9Fields))
			return false;
		document.getElementById('paraFrm').action="DashletProfileSettings_save.action";
		document.getElementById('paraFrm').submit();
		return true;
	}
	
	function cancelFun()
	{
		document.getElementById('paraFrm').action="DashletProfileSettings_input.action";
		document.getElementById('paraFrm').submit();
	}
	
	function checkAll()
	{
		for(i=0;i < <%=d1%>;i++)
			document.getElementById('confChk'+i).checked = document.getElementById('activateAll').checked; 
	}
	
	function checkForAll()
	{
		var flag=true;
		for(i=0;i < <%=d1%>;i++)
			if(!document.getElementById('confChk'+i).checked)
			{
				flag=false;
				break;
			}
		document.getElementById('activateAll').checked = flag;
	}
	
</script>