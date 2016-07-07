<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="SettingMaster" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="2"
		class="formbg">
		<tr>
			<td colspan="3" width="100%"><%@ include
				file="hrConfigHeader.jsp"%></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Thought
					of the Day </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="1" width="25%"><label id="enter.thought" name="enter.thought" ondblclick="callShowDiv(this);"><%= label.get("enter.thought") %></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="65%"><s:textfield size="60"
						name="thougth" id="thougth" /> <s:hidden name="hiddenCode_th" />
					<s:submit cssClass="add" theme="simple"
						action="SettingMaster_addThougth" value="    Add"
						onclick="return addValidate();" /></td>
				</tr>

			</table>
		<tr>
			<td>
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="7%" colspan="1"><label id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%= label.get("sr.no") %></label></td>
					<td class="formth" width="65%" colspan="1"><label id="thought" name="thought" ondblclick="callShowDiv(this);"><%= label.get("thought") %></label></td>
					<td class="formth" width="10%" colspan="1"><label id="date" name="date" ondblclick="callShowDiv(this);"><%= label.get("date") %></label></td>
					<td class="formth" width="19%" colspan="1">&nbsp;</td>
				</tr>
				<%
				int j = 1;
				%>
				<s:iterator value="list_thougth">
					<tr>
						<td class="td_bottom_border" width="5%" colspan="1"><%=j++%></td>
						<td class="td_bottom_border" width="65%" colspan="1"><s:hidden
							value="thougthCode" /> <s:property value="thougthName" /></td>
						<td class="td_bottom_border" width="10%" colspan="1"><s:property
							value="thougthDate" /></td>
						<td class="td_bottom_border" width="20%" colspan="1" align="center"><input
							type="button" title="Edit Record" class="rowEdit"
							onclick="callForEdit('<s:property value="thougthCode"/>','SettingMaster_editThougth.action','hiddenCode_th')" />&nbsp;&nbsp;
						<input type="button" title="Delete Record" class="rowDelete"
							onclick="callForDelete('<s:property value="thougthCode"/>','SettingMaster_deleteThougth.action','hiddenCode_th')" />
						</td>
					</tr>

				</s:iterator>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	function addValidate()
	{
		var val = LTrim(document.getElementById('thougth').value);
		if(val=="")
		{
			alert("Please enter "+document.getElementById('enter.thought').innerHTML.toLowerCase());
			document.getElementById('thougth').value = "";
			document.getElementById('thougth').focus();
			return false;
		}
		return true;
	}
	function callForEdit(editCode,action,hiddenCodeTxt)
	{	
		document.getElementById("paraFrm_"+hiddenCodeTxt).value = editCode;
		document.getElementById('paraFrm').action=action;
		document.getElementById('paraFrm').submit();
	}
	
	function callForDelete(delCode,action,hiddenCodeTxt)
	{
		if(confirm("Are you sure want to delete?"))
		{	
			document.getElementById("paraFrm_"+hiddenCodeTxt).value = delCode;
			document.getElementById('paraFrm').action = action;
			document.getElementById('paraFrm').submit();
		}
	}
</script>