<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="SettingMaster" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="1" cellspacing="1"
		class="formbg">
		<tr>
			<td colspan="4" width="100%"><%@ include
				file="hrConfigHeader.jsp"%></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt"><strong class="text_head">Knowledge Information
					Settings </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%"><s:submit cssClass="save"
				action="SettingMaster_saveKnowledge" theme="simple"
				value=" Save"
				onclick="return callValidate('knowCatName','linkNameKs','linkKs','uploadKs','upload_ks');" />
				<s:submit cssClass="reset"
				action="SettingMaster_reset_ks" theme="simple" value=" Reset" /></td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
				<td colspan="1" width="15%"><label name="category" id="category" ondblClick="callShowDiv(this)"><%= label.get("category")%></label> <font color="red">*</font>
					:</td>
					<td colspan="1" width="85%"><s:select
								name="knowCatNmSelect" cssStyle="width:175" headerKey=""
								headerValue="---Select---" list="knowMap"
								onclick="callDisplayOther();" /></td>
				</tr>
				<tr id="catId" style="display: none;">
							<td colspan="1" width="15%"><label name="category"
								id="category" ondblclick="callShowDiv(this);"><%=label.get("category")%></label><font
								color="red">*</font> :</td>
							<td colspan="1" width="85%"><s:textfield size="40"
								name="knowCatName" /></td>
						</tr>
				<tr>
					<td colspan="1" width="15%"><label name="link.name" id="link.name" ondblClick="callShowDiv(this)"><%= label.get("link.name")%></label> <font color="red">*</font>
					:</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="linkNameKs" /> <s:hidden name="hiddenCode_Ks" /><s:hidden
						name="checkFlag_ks" /></td>
				</tr>

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><s:if test="checkFlag_ks">
						<input type="radio" name="chkKs" value="link_ks"
							onclick="chkRadio('link_ks','upload_ks','linkKs','uploadKs');"
							checked="checked" id="chkKs"><label name="link" id="link" ondblClick="callShowDiv(this)"><%= label.get("link")%></label> &nbsp;
				<input type="radio" name="chkKs" value="uploadDoc_ks" id="chkKs1"
							onclick="chkRadio('upload_ks','link_ks','linkKs','uploadKs');"><label name="upload.document" id="upload.document" ondblClick="callShowDiv(this)"><%= label.get("upload.document")%></label> &nbsp;
			</s:if> <s:else>
						<input type="radio" name="chkKs" value="link_ks" id="chkKs"
							onclick="chkRadio('link_ks','upload_ks','linkKs','uploadKs');"><label name="link" id="link" ondblClick="callShowDiv(this)"><%= label.get("link")%></label> &nbsp;
				<input type="radio" name="chkKs" value="uploadDoc_ks" id="chkKs1"
							checked="checked"
							onclick="chkRadio('upload_ks','link_ks','linkKs','uploadKs');"><label name="upload.document" id="upload.document" ondblClick="callShowDiv(this)"><%= label.get("upload.document")%></label> &nbsp;
			</s:else></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="upload_ks">
					<table>
						<tr>
							<td colspan="2" width="20%"><label name="link.upload" id="link.upload" ondblClick="callShowDiv(this)"><%= label.get("link.upload")%></label> <font color="red">*</font>:</td>
							<td>&nbsp;</td>
							<td colspan="1">&nbsp;<s:textfield size="26" name="uploadKs"
								readonly="true" /></td>
							<td colspan="1"><input type="button" class="token"
								name="Browse" value="Browse" onclick="uploadFile('uploadKs');" />&nbsp;&nbsp;</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="link_ks">
					<table>
						<tr>
							<td colspan="2" width="20%"><label name="link" id="link1" ondblClick="callShowDiv(this)"><%= label.get("link")%></label> <font color="red">*</font>:</td>
							<td colspan="2" align="left">&nbsp;<s:textfield size="29"
								name="linkKs" /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				
				<tr>
					<td width="20%"><label class="set" name="applDiv" id="applDiv"
						ondblclick="callShowDiv(this);"><%=label.get("applDiv")%></label>
					:</td>
					<td width="76%"><s:hidden name="knowDivCode"></s:hidden>
					<s:textarea name="knowDivName" cols="25" rows="2"
						readonly="true"></s:textarea> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callF9Function('paraFrm_knowDivName','paraFrm_knowDivCode'); ">
					</td>

				</tr>
				
				
				
				<tr>
					<td>&nbsp;<label name="active" id="active" ondblClick="callShowDiv(this)"><%= label.get("active")%></label> :</td>
					<td colspan="2" width="15%"><s:checkbox name="checkKnow"></s:checkbox></td>
				</tr>
			</table>
			<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td class="formth" width="20%" colspan="1"><label name="category" id="category1" ondblClick="callShowDiv(this)"><%= label.get("category")%></label></td>
					<td class="formth" width="30%" colspan="1"><label name="link.name" id="link.name1" ondblClick="callShowDiv(this)"><%= label.get("link.name")%></label></td>
					<td class="formth" width="25%" colspan="1"><label name="file.name" id="file.name" ondblClick="callShowDiv(this)"><%= label.get("file.name")%></label></td>
					<td class="formth" width="5%" colspan="1"><label name="active" id="active1" ondblClick="callShowDiv(this)"><%= label.get("active")%></label></td>
					<td class="formth" width="20%" colspan="1">&nbsp;</td>
				</tr>

				<s:iterator value="list_ksLink">
					<tr>
						<td class="td_bottom_border" width="20%" colspan="1"><s:hidden
							value="linkCode_Ks" /><s:property value="catName_Ks" /></td>
						<td class="td_bottom_border"  width="30%" colspan="1"><s:property value="linkName_Ks" /></td>
						<td class="td_bottom_border" width="25%" colspan="1"><s:property
							value="linkFile_Ks" /></td>
						<td class="td_bottom_border" width="5%" colspan="1"><s:property
							value="linkActive_Ks" /></td>
						<td class="td_bottom_border" width="20%" colspan="1" align="center"><input
							type="button" class="rowEdit" title="Edit Record"
							onclick="callForEdit('<s:property value="linkCode_Ks"/>','SettingMaster_editKs.action','hiddenCode_Ks')" />&nbsp;&nbsp;
						<input type="button" class="rowDelete" title="Delete Record"
							onclick="callForDelete('<s:property value="linkCode_Ks"/>','SettingMaster_deleteKs.action','hiddenCode_Ks')" />
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
	
	
function callF9Function(divName1,divCode1){    
   callsF9(500,325,'SettingMaster_f9applDiv.action?divName='+divName1+'&divCode='+divCode1);
 }
 
function uploadFile(fieldName) {
		var path="upload";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}
	
function chkRadio(divId,hideDivId,link,upload){
		document.getElementById(divId).style.display = '';
		document.getElementById(hideDivId).style.display = 'none';
		document.getElementById('paraFrm_'+link).value = '';
		document.getElementById('paraFrm_'+upload).value = '';
}
	
function callValidate(knowcat,name_link,link,upload,divId){
		var chkLink = document.getElementById("paraFrm_"+name_link).value;
		var chkUploadName = document.getElementById("paraFrm_"+upload).value;
		var chkLinkName = document.getElementById("paraFrm_"+link).value;
		var chkKnowCategory = document.getElementById("paraFrm_"+knowcat).value;		
		var chkLinkBlank = LTrim(chkLink);
		var knowSelect = document.getElementById('paraFrm_knowCatNmSelect').value;
		if(knowSelect=="Other" && chkKnowCategory==""){
			alert("Please Enter Category Name ");
			return false;
		}		
		if(chkKnowCategory=="" && knowSelect ==""){
			alert("Please select Category Name");
			return false;
	    }
		if(chkLink==""){
			alert("Please enter "+document.getElementById('link.name').innerHTML.toLowerCase());
			document.getElementById("paraFrm_"+name_link).focus();
			return false;
		}
		if(chkLinkBlank == ""){
				alert("Please enter "+document.getElementById('link.name').innerHTML.toLowerCase());
				document.getElementById("paraFrm_"+name_link).focus();
				return false;
			}
			
		var chkStart = chkLinkName.substring(0,4);
		if(chkStart.length!=0){
			if(!(chkStart=="http" || chkStart=="HTTP")){
				alert(document.getElementById('link').innerHTML.toLowerCase()+" should start with http ");
				return false;
			}
		}
		var chkDiv = document.getElementById(divId).style.display;
		if(chkDiv == "none"){
			if(chkLinkName ==""){
				alert("Please enter "+document.getElementById('link').innerHTML.toLowerCase());
				document.getElementById("paraFrm_"+link).focus();
				return false;
			}
			var val  = document.getElementById(link).value;
			var value = LTrim(val);
			if(value==""){
				alert("Please enter "+document.getElementById('link.name').innerHTML.toLowerCase());
				document.getElementById("paraFrm_"+link).value = "";
				document.getElementById("paraFrm_"+link).focus();
				return false;
			}
		}
		else{
			if(chkUploadName == ""){
				 alert("Please submit your file. Click browse to submit");
				 document.getElementById("paraFrm_"+upload).focus();
				 return false;
			}
		}
		document.getElementById('paraFrm').target="main"; 
		document.getElementById('paraFrm').target="";
		return true;
	}
	
	function callForEdit(editCode,action,hiddenCodeTxt){	
		document.getElementById("paraFrm_"+hiddenCodeTxt).value = editCode;
		document.getElementById('paraFrm').action=action;
		document.getElementById('paraFrm').submit();
	}
	
	function callForDelete(delCode,action,hiddenCodeTxt){
		if(confirm('<%=label.get("confirm.delete")%>')){	
			document.getElementById("paraFrm_"+hiddenCodeTxt).value = delCode;
			document.getElementById('paraFrm').action = action;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callOnLoad(){
		var flag = document.getElementById("paraFrm_checkFlag_ks").value;
		if(flag=="false"){
			document.getElementById('upload_ks').style.display = '';
			document.getElementById('link_ks').style.display = 'none';
		}
		else{
			document.getElementById('upload_ks').style.display = 'none';
			document.getElementById('link_ks').style.display = '';
		}
	}
	
	 function callDisplayOther(){
 		if(document.getElementById('paraFrm_knowCatNmSelect').value=="Other"){
 			document.getElementById('catId').style.display = '';
 		}else{
 			document.getElementById('catId').style.display = 'none';
 		}
		if(value ==""){
 			document.getElementById('catId').style.display = 'none';
 		}
 	}
</script>