 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<%@ include file="../common/commonValidations.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<s:form action="SuperSetting" validate="true" id="paraFrm" target="main"
	theme="simple">
	<s:hidden name="parentMenu" />
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="2" class="formbg">
		<s:hidden name="checkFlag_hr" /> <s:hidden
				name="checkFlag_ql" /><s:hidden name="checkFlag_gs" />
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="93%" class="txt"><strong
							class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong><strong class="text_head"> Super
					Settings </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left: 2px;" width="78%">
					<div id="tabnav">
					<ul>
					<li>
					<a
						href="javascript:callDivLoad('HR');">
						<span>&nbsp;&nbsp;HrWorK&nbsp;&nbsp;</span></a>
					<a
						href="javascript:callDivLoad('GE');">
						<span align="center">&nbsp;&nbsp;Quick Information&nbsp;&nbsp;</span></a>
						<a
						href="javascript:callDivLoad('QL');">
						<span align="center">&nbsp;&nbsp;Quick Link&nbsp;&nbsp;</span></a></li></ul></td>
						<td width="22%" align="right"><div align="right"><font color="red">*</font> Indicates
					Required</div></td>
				</tr>
			</table>
			<s:hidden name="divFlag_HR" /><s:hidden name="divFlag_GE" /> <s:hidden
				name="divFlag_QL" /><s:hidden name="divFlag_AS" /> <s:hidden
				name="hiddenDivId" /> <s:if test="divFlag_HR">
				<div id="HR">
				<table class="formbg" width="100%">
					<tr>
						<td width="75%" class="txt" colspan="3"><strong class="text_head">HrWork
						Communication Settings </strong></td>
					</tr>
					<tr>
						<td colspan="3" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>
					<tr>
						<td colspan="1" width="15%"><label name="link.name" id="link.name" ondblclick="callShowDiv(this);"><%=label.get("link.name") %></label> <font color="red">*</font>
						:</td>
						<td colspan="1" width="85%"><s:textfield size="40"
							name="linkNameHr" /> <s:hidden name="hiddenCode_Hr" />
						</td>
					</tr>
					<tr>
						<td colspan="1">&nbsp;</td>
						<td colspan="3"><s:if test="checkFlag_hr">
							<input type="radio" name="chkHr" value="link_hr"
								onclick="chkRadio('link_hr','upload_hr','linkHr','uploadHr');"
								checked="checked"><label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link") %></label> :&nbsp;
					<input type="radio" name="chkHr" value="uploadDoc_hr"
								onclick="chkRadio('upload_hr','link_hr','linkHr','uploadHr');"><label name="upload.document" id="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document") %></label> &nbsp;
				</s:if> <s:else>
							<input type="radio" name="chkHr" value="link_hr"
								onclick="chkRadio('link_hr','upload_hr','linkHr','uploadHr');"><label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link") %></label> :&nbsp;
					<input type="radio" name="chkHr" value="uploadDoc_hr"
								checked="checked"
								onclick="chkRadio('upload_hr','link_hr','linkHr','uploadHr');"><label name="upload.document" id="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document") %></label> &nbsp;
				</s:else></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2" width="50%">
						<div id="upload_hr">
						<table>
							<tr>
								<td colspan="2" width="20%"><label name="link.upload" id="link.upload" ondblclick="callShowDiv(this);"><%=label.get("link.upload") %></label> <font color="red">*</font>:</td>
								<td colspan="1" align="left">&nbsp;<s:textfield size="26"
									name="uploadHr" readonly="true" /></td>
								<td colspan="1" align="left"><input type="button"
									class="token" name="Browse" value="Browse"
									onclick="uploadFile('uploadHr');" />&nbsp;&nbsp;</td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2" width="50%">
						<div id="link_hr">
						<table>
							<tr>
								<td colspan="2" width="20%"><label name="link" id="link1" ondblclick="callShowDiv(this);"><%=label.get("link") %></label> <font color="red">*</font>:</td>
								<td colspan="2" align="left">&nbsp;<s:textfield size="29"
									name="linkHr" /></td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;<label name="active" id="active" ondblclick="callShowDiv(this);"><%=label.get("active") %></label> :</td>
						<td colspan="2" width="15%"><s:checkbox name="checkHr"></s:checkbox></td>
					</tr>

					<tr>
						<td></td>
						<td><s:submit cssClass="add" action="SuperSetting_saveHrComm"
							theme="simple" value=" Add"
							onclick="return callValidate('linkNameHr','linkHr','uploadHr','upload_hr');" />
						&nbsp; <input type="button" class="reset" onclick="resetHr()"
							theme="simple" value=" Reset" /></td>

					</tr>
				</table>
				<table width="100%" class="formbg">

					<tr>
						<td class="formth" width="38%" colspan="1"><label name="link.name" id="link.name1" ondblclick="callShowDiv(this);"><%=label.get("link.name") %></label> </td>
						<td class="formth" width="37%" colspan="1"><label name="file.name" id="file.name" ondblclick="callShowDiv(this);"><%=label.get("file.name") %></label> </td>
						<td class="formth" width="5%" colspan="1"><label name="active" id="active1" ondblclick="callShowDiv(this);"><%=label.get("active") %></label> </td>
						<td class="formth" width="20%" colspan="1">&nbsp;</td>
					</tr>

					<s:iterator value="list_hrLink">
						<tr>
							<td class="sortableTD" width="38%" colspan="1"><s:hidden
								value="linkCode_Hr" /> <s:property value="linkName_Hr" /></td>
							<td class="sortableTD" width="37%" colspan="1"><s:property
								value="linkFile_Hr" /></td>
							<td class="sortableTD" width="5%" colspan="1"><s:property
								value="linkActive_Hr" /></td>
							<td class="sortableTD" width="20%" colspan="1" align="center"><input
								type="button" title="Edit Record" class="rowEdit"
								onclick="callForEdit('<s:property value="linkCode_Hr"/>','SuperSetting_editHr.action','hiddenCode_Hr')" />&nbsp;&nbsp;
							<input type="button" title="Delete Record" class="rowDelete"
								onclick="callForDelete('<s:property value="linkCode_Hr"/>','SuperSetting_deleteHr.action','hiddenCode_Hr')" />
							</td>
						</tr>
					</s:iterator>

				</table>
				</div>
			</s:if> <s:if test="divFlag_GE">
				<div id="GE">
				<table width="100%" class="formbg">
					<tr>
						<td width="75%" class="txt" colspan="3"><strong class="text_head">Quick Information
						Settings </strong></td>
					</tr>

					<tr>
						<td colspan="3" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>

					<tr>
						<td colspan="1" width="15%"><label name="link.name" id="link.name" ondblclick="callShowDiv(this);"><%=label.get("link.name") %></label>  <font color="red">*</font>
						:</td>
						<td colspan="1" width="85%"><s:textfield size="40"
							name="linkNameGs" /> <s:hidden name="hiddenCode_Gs" />
							</td>
					</tr>

					<tr>
						<td colspan="1">&nbsp;</td>
						<td colspan="1"><s:if test="checkFlag_gs">
							<input type="radio" name="chkGs" value="link_gs"
								onclick="chkRadio('link_gs','upload_gs','linkGs','uploadGs');"
								checked="checked"><label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link") %></label>  :&nbsp;
				<input type="radio" name="chkGs" value="uploadDoc_gs"
								onclick="chkRadio('upload_gs','link_gs','linkGs','uploadGs');"><label name="upload.document" id="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document") %></label>  &nbsp;
			</s:if> <s:else>
							<input type="radio" name="chkGs" value="link_gs"
								onclick="chkRadio('link_gs','upload_gs','linkGs','uploadGs');"><label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link") %></label>  :&nbsp;
				<input type="radio" name="chkGs" value="uploadDoc_gs"
								checked="checked"
								onclick="chkRadio('upload_gs','link_gs','linkGs','uploadGs');"><label name="upload.document" id="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document") %></label>  &nbsp;
			</s:else></td>
					</tr>

					<tr>
						<td></td>
						<td colspan="2" width="50%">
						<div id="upload_gs">
						<table>
							<tr>
								<td colspan="2" width="20%"><label name="link.upload" id="link.upload1" ondblclick="callShowDiv(this);"><%=label.get("link.upload") %></label>  <font color="red">*</font>:</td>
								<td>&nbsp;</td>
								<td colspan="1">&nbsp;<s:textfield size="26"
									name="uploadGs" readonly="true" /></td>
								<td colspan="1"><input type="button" class="token"
									name="Browse" value="Browse" onclick="uploadFile('uploadGs');" />&nbsp;&nbsp;

								</td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2" width="50%">
						<div id="link_gs">
						<table>
							<tr>
								<td colspan="2" width="20%"><label name="link" id="link1" ondblclick="callShowDiv(this);"><%=label.get("link") %></label>  <font color="red">*</font>:</td>
								<td colspan="2" align="left">&nbsp;<s:textfield size="29"
									name="linkGs" /></td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;<label name="active" id="active" ondblclick="callShowDiv(this);"><%=label.get("active") %></label>  :</td>
						<td colspan="2" width="15%"><s:checkbox name="checkGeneral"></s:checkbox></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td colspan="2" width="15%"><s:submit cssClass="add"
							action="SuperSetting_saveGeneral" theme="simple" value=" Add"
							onclick="return callValidate('linkNameGs','linkGs','uploadGs','upload_gs');" />&nbsp;
						<input type="button" class="reset" onclick="resetGs()"
							theme="simple" value=" Reset" /></td>
					</tr>
				</table>
				<table width="100%" class="formbg">
					<tr>
						<td class="formth" width="38%" colspan="1"><label name="link.name" id="link.name1" ondblclick="callShowDiv(this);"><%=label.get("link.name") %></label> </td>
						<td class="formth" width="37%" colspan="1"><label name="file.name" id="file.name" ondblclick="callShowDiv(this);"><%=label.get("file.name") %></label> </td>
						<td class="formth" width="5%" colspan="1"><label name="active" id="active1" ondblclick="callShowDiv(this);"><%=label.get("active") %></label> </td>
						<td class="formth" width="20%" colspan="1">&nbsp;</td>
					</tr>
					<s:iterator value="list_gsLink">
						<tr>

							<td class="sortableTD" width="38%" colspan="1"><s:hidden
								value="linkCode_Gs" /> <s:property value="linkName_Gs" /></td>
							<td class="sortableTD" width="37%" colspan="1"><s:property
								value="linkFile_Gs" /></td>
							<td class="sortableTD" width="5%" colspan="1"><s:property
								value="linkActive_Gs" /></td>
							<td class="sortableTD" width="20%" colspan="1" align="center"><input
								type="button" class="rowEdit" title="Edit Record"
								onclick="callForEdit('<s:property value="linkCode_Gs"/>','SuperSetting_editGs.action','hiddenCode_Gs')" />&nbsp;&nbsp;
							<input type="button" class="rowDelete" title="Delete Record"
								onclick="callForDelete('<s:property value="linkCode_Gs"/>','SuperSetting_deleteGs.action','hiddenCode_Gs')" />
							</td>
						</tr>
					</s:iterator>
				</table>

				</div>
			</s:if> <s:if test="divFlag_QL">
				<div id="QL">
				<table class="formbg" width="100%">
					<tr>
						<td width="75%" class="txt" colspan="3"><strong class="text_head">Quick
						Link Settings </strong></td>
					</tr>
					<tr>
						<td colspan="1" width="15%"><label name="link.name" id="link.name" ondblclick="callShowDiv(this);"><%=label.get("link.name") %></label>  <font color="red">*</font>
						:</td>
						<td colspan="1" width="85%"><s:textfield size="40"
							name="linkNameQl" onkeypress="return charactersOnly();"/> <s:hidden name="hiddenCode_Ql" />
							<s:hidden name="hiddenlinkPathQl" /><img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="callsF9(500,325,'SuperSetting_f9MenuLink.action');">
							</td>
					</tr>
					
					<tr>
						<td><label name="active" id="active" ondblclick="callShowDiv(this);"><%=label.get("active") %></label>  :</td>
						<td colspan="2" width="15%"><s:checkbox name="checkQuick"></s:checkbox></td>
					</tr>
					<tr>
						<td></td>
						<td><s:submit cssClass="add" action="SuperSetting_saveLink"
							theme="simple" value=" Add" 
							onclick="return callValidateQuickLink('linkNameQl','linkQl','uploadQl','upload_ql');" />&nbsp;
						<input type="button" class="reset" onclick="resetQl()"
							theme="simple" value=" Reset" /></td>

					</tr>
				</table>
				<table width="100%" class="formbg">
					<tr>
						<td class="formth" width="70%" colspan="1" nowrap="nowrap"><label name="link.name" id="link.name1" ondblclick="callShowDiv(this);"><%=label.get("link.name") %></label> </td>
						<td class="formth" width="10%" colspan="1"><label name="active" id="active1" ondblclick="callShowDiv(this);"><%=label.get("active") %></label> </td>
						<td class="formth" width="20%" colspan="1" align="center">&nbsp;</td>

					</tr>
					<s:iterator value="list_QlLink">
						<tr>
							<td class="sortableTD" width="70%" colspan="1" nowrap="nowrap"><s:hidden
								value="linkCode_Ql" /> <s:property value="linkName_Ql" /></td>
							<td class="sortableTD" width="10%" colspan="1"><s:property
								value="linkActive_Ql" /></td>
							<td class="sortableTD" width="20%" colspan="1" align="center"><input
								type="button"  class="rowEdit" title="Edit Record"
								onclick="callForEdit('<s:property value="linkCode_Ql"/>','SuperSetting_editQl.action','hiddenCode_Ql')" />&nbsp;&nbsp;
							<input type="button" class="rowDelete" title="Delete Record"
								onclick="callForDelete('<s:property value="linkCode_Ql"/>','SuperSetting_deleteQl.action','hiddenCode_Ql')" />
							</td>
						</tr>
					</s:iterator>
				</table>
				</div>
			</s:if> </td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

	var flag_hr = document.getElementById("paraFrm_checkFlag_hr").value;
	var flag_Ql = document.getElementById("paraFrm_checkFlag_ql").value;
	var flag_gs = document.getElementById("paraFrm_checkFlag_gs").value;
	callOnLoad();

    function uploadFile(fieldName) 
    {
		var data_path = "C:\\HrWorK\\dataFiles\\";
		var path = "upload";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		document.getElementById('paraFrm').target="main";
    }

    function chkRadio(divId,hideDivId,link,upload)
    {
		document.getElementById(divId).style.display = '';
		document.getElementById(hideDivId).style.display = 'none';
		document.getElementById('paraFrm_'+link).value = "";
		document.getElementById('paraFrm_'+upload).value = "";
	}
	
	function callOnLoad()
	{
		var divId = document.getElementById('paraFrm_hiddenDivId').value;
		if(divId == "HR")
		{
			if(flag_hr=="false"){
				document.getElementById('upload_hr').style.display = '';
				document.getElementById('link_hr').style.display = 'none';
				
			}else{
				document.getElementById('upload_hr').style.display = 'none';
				document.getElementById('link_hr').style.display = '';
			}
		}
	
		else if(divId == "GE")
		{
			if(flag_gs=="false")
			{
				document.getElementById('upload_gs').style.display = '';
				document.getElementById('link_gs').style.display = 'none';
			}
			else
			{
				document.getElementById('upload_gs').style.display = 'none';
				document.getElementById('link_gs').style.display = '';
			}
		}
	}
	
	function callValidateQuickLink()
	{
		if(trim(document.getElementById('paraFrm_hiddenlinkPathQl').value) == "")
		{
			document.getElementById('paraFrm_linkNameQl').value = "";
			alert("Please select "+document.getElementById('link.name').innerHTML.toLowerCase());
			return false;
		}
		if(trim(document.getElementById('paraFrm_linkNameQl').value) == "")
		{
			alert("Please enter name for selected "+document.getElementById('link.name').innerHTML.toLowerCase());
			return false;
		}
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
		var chkDiv = document.getElementById(divId).style.display;
		if(chkDiv == "none")
		{
			if(chkLinkName =="")
			{
				alert("Please enter "+document.getElementById('link').innerHTML.toLowerCase());
				document.getElementById("paraFrm_"+link).focus();
				return false;
			}
			var val  = document.getElementById("paraFrm_"+link).value;
			var value = LTrim(val);
			if(value=="")
			{
				alert("Please enter "+document.getElementById('link').innerHTML.toLowerCase());
				document.getElementById("paraFrm_"+link).value = "";
				document.getElementById("paraFrm_"+link).focus();
				return false;
			}
		}
		else
		{
			if(chkUploadName == "")
			{
			 	alert("Please submit your file. Click browse to submit");
			 	document.getElementById("paraFrm_"+upload).focus();
			 	return false;
			}
		}
		document.getElementById('paraFrm').target="main";		
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
	
	function callDivLoad(id)
	{
		document.getElementById('paraFrm').action = 'SuperSetting_showOnlyInfo.action';
		document.getElementById('paraFrm_hiddenDivId').value = id;
		document.getElementById('paraFrm').submit();
	}
	
	function resetHr(){
	
		document.getElementById('paraFrm_uploadHr').value="";
		document.getElementById('paraFrm_linkNameHr').value="";
		document.getElementById('paraFrm_linkHr').value="";
		document.getElementById('paraFrm_hiddenCode_Hr').value="";
		document.getElementById('paraFrm_checkFlag_hr').value="true";
		document.getElementById('paraFrm_checkHr').checked=false;
		}
	function resetQl(){	
		document.getElementById('paraFrm_linkNameQl').value="";
		document.getElementById('paraFrm_hiddenCode_Ql').value="";
		document.getElementById('paraFrm_checkQuick').checked=false;
		document.getElementById('paraFrm_hiddenlinkPathQl').value = "";
	}
	function resetGs(){
		document.getElementById('paraFrm_uploadGs').value="";
		document.getElementById('paraFrm_linkNameGs').value="";
		document.getElementById('paraFrm_linkGs').value="";
		document.getElementById('paraFrm_hiddenCode_Gs').value="";
		//document.getElementById('paraFrm_genType').value="";
		document.getElementById('paraFrm_checkFlag_gs').value="true";
		document.getElementById('paraFrm_checkGeneral').checked=false;
	
	}

</script>




 