<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AssetTypes" validate="true" id="paraFrm" name="paraFrm"
	theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Asset
					Category </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Asset Category</strong></td>
					<s:hidden name="assetCode" />
				</tr>
				<tr>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" name="assettype.name" id="assettype.name"
						ondblclick="callShowDiv(this);"><%=label.get("assettype.name")%></label>
					<font color="red">*</font> :</td>
					<td width="75%" colspan="3"><s:textfield name="assetname"
						size="30" maxlength="100" onkeypress="return allCharacters();"></s:textfield></td>
				</tr>

				<tr>
					<td width="20%" height="22" class="formtext"><label
						name="is.active" class="set" id="is.active"
						ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					:</td>
					<td><s:select name="isActive" list="#{'Y':'Yes','N':'No'}"
						cssStyle="width:151;z-index:5;" /></td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	 
</s:form>
<script>

	function saveFun()
	{	
		var fieldName = ["paraFrm_assetname"];
		var lableName = ["assettype.name"];
		var typeName = ["enter"];
	   	if(!validateBlank(fieldName,lableName,typeName))
	    	return false;
	        
	    if(!f9specialchars(fieldName))
	    {
			return false;
	    }
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='AssetTypes_save.action';
		document.getElementById("paraFrm").submit();
		
		return true;
		
	}
	function editFun()
	{
		return true;
	}

	function resetFun() {
		//document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AssetTypes_reset1.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AssetTypes_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'AssetTypes_delete.action';
			document.getElementById('paraFrm').submit();
		}
			
	}
</script>