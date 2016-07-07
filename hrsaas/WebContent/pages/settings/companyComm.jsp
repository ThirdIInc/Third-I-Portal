<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="SettingMaster" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="4" width="100%"><%@ include
				file="/pages/settings/hrConfigHeader.jsp"%></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Announcement 
					  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						action="SettingMaster_saveCompanyComm" theme="simple"
						value=" Save"
						onclick="return callValidate('linkNameCms','linkCms','uploadCms','upload_cms');" />
					&nbsp;&nbsp; <s:submit cssClass="reset"
						action="SettingMaster_reset_cms" theme="simple" value=" Reset" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
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
					<td colspan="1" width="15%"><label name="link.name"
						id="link.name" ondblclick="callShowDiv(this);"><%=label.get("link.name")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="linkNameCms" /> <s:hidden name="hiddenCode_Cms" /><s:hidden
						name="checkFlag_cms" /></td>
				</tr>

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><s:if test="checkFlag_cms">
						<input type="radio" name="chkCms" value="link_Cms"
							onclick="chkRadio('link_cms','upload_cms','linkCms','uploadCms');"
							checked="checked">
						<label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link")%></label> &nbsp;
				<input type="radio" name="chkCms" value="uploadDoc_Cms"
							onclick="chkRadio('upload_cms','link_cms','linkCms','uploadCms');">
						<label name="upload.document" id="upload.document"
							ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label> &nbsp;
			</s:if> <s:else>
						<input type="radio" name="chkCms" value="link_Cms"
							onclick="chkRadio('link_cms','upload_cms','linkCms','uploadCms');">
						<label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link")%></label> &nbsp;
				<input type="radio" name="chkCms" value="uploadDoc_Cms"
							checked="checked"
							onclick="chkRadio('upload_cms','link_cms','linkCms','uploadCms');">
						<label name="upload.document" id="upload.document"
							ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label> &nbsp;
			</s:else></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="upload_cms">
					<table>
						<tr>
							<td colspan="2" width="20%"><label name="link.upload" id="link.upload"
								ondblclick="callShowDiv(this);"><%=label.get("link.upload")%></label>
							<font color="red">*</font>:</td>
							<td>&nbsp;</td>
							<td colspan="1">&nbsp;<s:textfield size="26"
								name="uploadCms" readonly="true" /></td>
							<td colspan="1"><input type="button" class="token"
								name="Browse" value="Browse" onclick="uploadFile('uploadCms');" />&nbsp;&nbsp;</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="link_cms">
					<table>
						<tr>
							<td colspan="2" width="20%"><label name="link" id="link1"
								ondblclick="callShowDiv(this);"><%=label.get("link")%></label>
							<font color="red">*</font>:</td>

							<td colspan="2" align="left">&nbsp;<s:textfield size="29"
								name="linkCms" /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				
				<tr>
					<td width="20%"><label class="set" name="applDiv" id="applDiv"
						ondblclick="callShowDiv(this);"><%=label.get("applDiv")%></label>
					:</td>
					<td width="76%"><s:hidden name="corpDivCode"></s:hidden>
					<s:textarea name="corpDivName" cols="25" rows="2"
						readonly="true"></s:textarea> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callF9Function('paraFrm_corpDivName','paraFrm_corpDivCode'); ">
					</td>

				</tr>
				
				<tr>
					<td>&nbsp;<label name="active" id="active"
						ondblclick="callShowDiv(this);"><%=label.get("active")%></label>
					:</td>
					<td colspan="2" width="15%"><s:checkbox name="checkComp"></s:checkbox></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td class="formth" width="50%" colspan="1"><label
						name="link.name" id="link.name1" ondblclick="callShowDiv(this);"><%=label.get("link.name")%></label></td>
					<td class="formth" width="25%" colspan="1"><label
						name="file.name" id="file.name" ><%=label.get("file.name")%></label></td>
					<td class="formth" width="5%" colspan="1"><label name="active"
						id="active1" ><%=label.get("active")%></label></td>
					<td class="formth" width="20%" colspan="1">&nbsp;</td>
				</tr>
				<s:iterator value="list_cmsLink">
					<tr>
						<td class="td_bottom_border" width="50%" colspan="1"><s:hidden
							value="linkCode_Cms" /> <s:property value="linkName_Cms" /></td>
						<td class="td_bottom_border" width="25%" colspan="1"><s:property
							value="linkFile_Cms" /></td>
						<td class="td_bottom_border" width="5%" colspan="1"><s:property
							value="linkActive_Cms" /></td>
						<td class="td_bottom_border" width="20%" colspan="1"
							align="center"><input type="button" class="rowEdit"
							title="Edit Record"
							onclick="callForEdit('<s:property value="linkCode_Cms"/>','SettingMaster_editCms.action','hiddenCode_Cms')" />&nbsp;&nbsp;
						<input type="button" class="rowDelete" title="Delete Record"
							onclick="callForDelete('<s:property value="linkCode_Cms"/>','SettingMaster_deleteCms.action','hiddenCode_Cms')" />
						</td>
					</tr>
				</s:iterator>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	callOnLoad();
	
		  function callF9Function(divName1,divCode1)
 	{   
 
   callsF9(500,325,'SettingMaster_f9applDiv.action?divName='+divName1+'&divCode='+divCode1);
 }
 
	function uploadFile(fieldName) 
	{
		var path="upload";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	function chkRadio(divId,hideDivId,link,upload)
	{
		document.getElementById(divId).style.display = '';
		document.getElementById(hideDivId).style.display = 'none';
		document.getElementById('paraFrm_'+link).value = '';
		document.getElementById('paraFrm_'+upload).value = '';
	}
	function callValidate(name_link,link,upload,divId)
	{
		var chkLink = document.getElementById("paraFrm_"+name_link).value;
		var chkUploadName = document.getElementById("paraFrm_"+upload).value;
		var chkLinkName = document.getElementById("paraFrm_"+link).value;
		var chkLinkBlank = LTrim(chkLink);
		if(chkLink=="")
		{
			alert("Please enter "+document.getElementById('link.name').innerHTML.toLowerCase());
			document.getElementById("paraFrm_"+name_link).focus();
			return false;
		}
		if(chkLinkBlank == "")
			{
				alert("Please enter "+document.getElementById('link.name').innerHTML.toLowerCase());
				document.getElementById("paraFrm_"+name_link).focus();
				return false;
			}
			
		var chkStart = chkLinkName.substring(0,4);
		if(chkStart.length!=0)
		{
			if(!(chkStart=="http" || chkStart=="HTTP"))
			{
				alert(document.getElementById('link.name').innerHTML.toLowerCase()+" should start with http ");
				return false;
			}
		}
		var chkDiv = document.getElementById(divId).style.display;
		if(chkDiv == "none")
		{
			if(chkLinkName =="")
			{
				alert("Please enter the "+document.getElementById('link').innerHTML.toLowerCase()+" Path/URL");
				document.getElementById("paraFrm_"+link).focus();
				return false;
			}
			var val  = document.getElementById(link).value;
			var value = LTrim(val);
			if(value=="")
			{
				alert("Please enter the "+document.getElementById('link').innerHTML.toLowerCase()+" Path/URL");
				document.getElementById("paraFrm_"+link).value = "";
				document.getElementById("paraFrm_"+link).focus();
				return false;
			}
		}
		else
		{
			if(chkUploadName == "")
			{
				 alert("Please Submit Your File. Click Browse to Submit");
				 document.getElementById("paraFrm_"+upload).focus();
				 return false;
			}
		}
		document.getElementById('paraFrm').target="main"; 
		document.getElementById('paraFrm').target="";
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
		if(confirm('<%=label.get("confirm.delete")%>'))
		{	
			document.getElementById("paraFrm_"+hiddenCodeTxt).value = delCode;
			document.getElementById('paraFrm').action = action;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callOnLoad()
	{
		var flag = document.getElementById("paraFrm_checkFlag_cms").value;
		if(flag=="false")
		{
			document.getElementById('upload_cms').style.display = '';
			document.getElementById('link_cms').style.display = 'none';
		}
		else
		{
			document.getElementById('upload_cms').style.display = 'none';
			document.getElementById('link_cms').style.display = '';
		}
	}
</script>
