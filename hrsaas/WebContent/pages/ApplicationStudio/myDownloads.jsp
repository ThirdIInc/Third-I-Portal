<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="MyDownloads" id="paraFrm" theme="simple" validate="true">
	<table width="100%">
		<tr>
			<td>
			<table width="100%">
				<s:hidden name="flag"></s:hidden>
				<s:hidden name="checkFlag" />
				<tr>
					<td colspan="6">
					<table class="formbg" width="100%" colspan="6">
						<tr>
							<td class="txt"><strong class="formhead"><img
								src="../pages/images/recruitment/review_shared.gif" width="25"
								height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Quick Download</strong></td>

							<td width="22%" align="right">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="6">
					<table width="100%" align="center">
						<tr>
							<td width="78%"><s:submit cssClass="save"
								action="MyDownloads_saveDownloads" theme="simple" value=" Save"
								onclick="return callValidate('categoryName','subCategoryName','linkName','link','uploadDocument','uploadDocumentDiv');" />
							&nbsp;&nbsp;&nbsp; <input type="button" name="btnName"
								value="Reset" onclick="callReset();" class="reset"></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<table class="formbg" width="100%">
						<tr>
							<td colspan="1" width="15%"><label name="categoryName"
								id="categoryName" ondblclick="callShowDiv(this);"><%=label.get("categoryName")%></label><font
								color="red">*</font> :</td>
							<td colspan="1" width="85%"><s:select
								name="categoryNameDropdown" cssStyle="width:175" headerKey=""
								headerValue="---Select---" list="tmap"
								onclick="callDisplayOtherText();" /></td>
						</tr>
						<tr id="catId" style="display: none;">
							<td colspan="1" width="15%"><label name="categoryName"
								id="categoryName" ondblclick="callShowDiv(this);"><%=label.get("categoryName")%></label><font
								color="red">*</font> :</td>
							<td colspan="1" width="85%"><s:textfield size="40"
								name="categoryName" /></td>
						</tr>

						<tr>
							<td colspan="1" width="15%"><label name="subCategoryName"
								id="subCategoryName" ondblclick="callShowDiv(this);"><%=label.get("subCategoryName")%></label><font
								color="red">*</font> :</td>
							<td colspan="1" width="85%"><s:select
								name="subCategoryNameDropDown" cssStyle="width:175" headerKey=""
								headerValue="---Select---" list="subcategorymap"
								onclick="callDisplayOtherTextSubCat();" /></td>
						</tr>

						<tr id="subcatId" style="display: none;">
							<td colspan="1" width="15%"><label name="subCategoryName"
								id="subCategoryName" ondblclick="callShowDiv(this);"><%=label.get("subCategoryName")%></label><font
								color="red">*</font> :</td>
							<td colspan="1" width="85%"><s:textfield size="40"
								name="subCategoryName" /></td>
						</tr>

						<tr>
							<td colspan="1" width="15%"><label name="linkName"
								id="linkName" ondblclick="callShowDiv(this);"><%=label.get("linkName")%></label><font
								color="red">*</font> :</td>
							<td colspan="1" width="85%"><s:textfield size="40"
								name="linkName" /> <s:hidden name="hiddenCode" /></td>
						</tr>

						<tr>
							<td colspan="1">&nbsp;</td>
							<td colspan="1"><s:if test="checkFlag">
								<input type="radio" name="chk" value="linkDiv"
									onclick="checkRadio('linkDiv','uploadDocumentDiv','link','uploadDocument','linkFile');">
								<label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link")%></label>
								<input type="radio" name="chk" value="uploadDocumentDoc"
									checked="checked"
									onclick="checkRadio('uploadDocumentDiv','linkDiv','link','uploadDocument','doc');">Upload Document &nbsp;
				           </s:if> <s:else>
								<input type="radio" name="chk" value="linkDiv" checked="checked"
									onclick="checkRadio('linkDiv','uploadDocumentDiv','link','uploadDocument','linkFile');">
								<label name="link" id="link" ondblclick="callShowDiv(this);"><%=label.get("link")%></label>
								<input type="radio" name="chk" value="uploadDocumentDoc"
									onclick="checkRadio('uploadDocumentDiv','linkDiv','link','uploadDocument','doc');">Upload Document &nbsp;
				          </s:else></td>
						</tr>

						<tr>
							<td></td>
							<td colspan="2" width="50%">
							<div id="uploadDocumentDiv">
							<table>
								<tr>
									<td colspan="2" width="16%"><label name="uploadDocument1"
										id="uploadDocument1" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label>
									<font color="red">*</font>:</td>
									<td>&nbsp;</td>
									<td>&nbsp; <s:hidden name="dataPath" /> <s:textfield
										size="27" name="uploadDocument" readonly="true" /></td>
									<td colspan="1"><input type="button" class="token"
										name="Browse1" value="Browse"
										onclick="uploadDocumentFile('uploadDocument');" />&nbsp;&nbsp;</td>
								</tr>
							</table>
							</div>
							</td>
						</tr>

						<tr>
							<td></td>
							<td colspan="2" colspan="2" width="50%">
							<div id="linkDiv">
							<table>
								<tr>
									<td width="16%"><label name="link1" id="link1"
										ondblclick="callShowDiv(this);"><%=label.get("link")%></label>
									<font color="red">*</font>:</td>
									<td>&nbsp;<s:textfield size="31" name="link" /></td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
 
								
						<tr>
							<td width="15%"><label class="set" name="division"
								id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td width="76%"><s:hidden name="downloadDivCode"/>
							<s:textarea name="downloadDivName" cols="36" rows="2"
								readonly="true"/><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callF9Function('paraFrm_downloadDivName','paraFrm_downloadDivCode'); ">
							</td>

						</tr>

						 <tr>
							<td width="15%"><label name="active" id="active"
								ondblclick="callShowDiv(this);"><%=label.get("active")%></label>
							:</td>
							<td colspan="2" width="15%"> 								
							<s:checkbox name="checkActive"></s:checkbox></td>
						</tr>
						<tr>
							<td colspan="3" valign="bottom" class="txt"><img
								src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
						</tr>

					</table>
				</tr>

				<tr>
					<table width="100%">
						<tr>
							<td class="formth" width="15%" colspan="1"><label
								name="cName" id="cName" ondblclick="callShowDiv(this);"><%=label.get("categoryName")%></label></td>
							<td class="formth" width="18%" colspan="1"><label
								name="scName" id="scName"
								ondblclick="callShowDiv(this);"><%=label.get("subCategoryName")%></label></td>
							<td class="formth" width="18%" colspan="1"><label
								name="lName" id="lName" ondblclick="callShowDiv(this);"><%=label.get("linkName")%></label></td>
							<td class="formth" width="15%" colspan="1"><label
								name="ldPath" id="ldPath" ondblclick="callShowDiv(this);"><%=label.get("linkDoc")%></label></td>
							<td class="formth" width="6%" colspan="1"><label
								name="active" id="active" ondblclick="callShowDiv(this);"><%=label.get("active")%></label></td>
							<td class="formth" width="22%" colspan="1"><label 
							    name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
							<td class="formth" width="6%" colspan="1">&nbsp;</td>
						</tr>

						<s:iterator value="listLink">
							<tr onmouseover="newRowColor(this);" onmouseout="oldRowColor(this);" height="20" class="sortableTD">
								<td class="td_bottom_border" width="15%" colspan="1"><s:property
									value="catName" /></td>
								<td class="td_bottom_border" width="18%" colspan="1"><s:property
									value="subCatName" /></td>
								<td class="td_bottom_border" width="18%" colspan="1"><s:hidden
									name="linkCode" /> <s:property value="dLinkName" /></td>
								<td class="td_bottom_border" width="15%" colspan="1"><s:property
									value="dLinkFile" /></td>
								<td class="td_bottom_border" width="6%" colspan="1"><s:property
									value="dLinkActive" /></td>
								<td class="td_bottom_border" width="22%" colspan="1"><s:hidden name="divCodeItt"/><s:property
									value="divNameItt" /></td>
								<td class="td_bottom_border" width="6%" colspan="2"
									align="left"><input type="button" class="rowEdit"
									title="Edit details"
									onclick="callForEdit('<s:property value="linkCode"/>')" />&nbsp;&nbsp;
								<input type="button" class="rowDelete" title="Delete record"
									onclick="callForDelete('<s:property value="linkCode"/>','MyDownloads_delete.action','hiddenCode')" />
								</td>
							</tr>
						</s:iterator>
						   
					</table>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
 
 
   function callDisplayOtherText(){
 		if(document.getElementById('paraFrm_categoryNameDropdown').value=="Other"){
 			document.getElementById('catId').style.display = '';
 		}else{
 			document.getElementById('catId').style.display = 'none';
 		}
		if(value ==""){
 			document.getElementById('catId').style.display = 'none';
 		}
 	}
 	
   function callDisplayOtherTextSubCat(){
 		if(document.getElementById('paraFrm_subCategoryNameDropDown').value=="Other"){
 			document.getElementById('subcatId').style.display = '';
 		}else{
 			document.getElementById('subcatId').style.display = 'none';
 		}
		if(value ==""){
 			document.getElementById('subcatId').style.display = 'none';
 		}
 	}

function callReset(){
		document.getElementById('paraFrm').action='MyDownloads_reset.action';
		document.getElementById('paraFrm').submit();
}

function checkRadio(divId,hideDivId,link,uploadDocument,flagVal){
	document.getElementById(divId).style.display = '';
	document.getElementById(hideDivId).style.display = 'none';
	document.getElementById('paraFrm_'+link).value = '';
	document.getElementById('paraFrm_'+uploadDocument).value = '';
	document.getElementById('paraFrm_flag').value =flagVal;
}

 
 
 
function uploadDocumentFile_OLD(fieldName) 
{
 
	var dataPath = document.getElementById('paraFrm_dataPath').value;
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path=' + dataPath + '&field=' + 
	fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
}

 
 
function uploadDocumentFile(fieldName) 
	{
		var path="upload";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

function callValidate(categoryName,subCategoryName,name_link,link,uploadDocument,divId)
{   
 
	try {
	var checkFlagVar = document.getElementById('paraFrm_checkFlag').value; 
	var categoryDropdown = document.getElementById('paraFrm_categoryNameDropdown').value;
	var subCategoryDropdown = document.getElementById('paraFrm_subCategoryNameDropDown').value;
    var chkCategory=document.getElementById("paraFrm_"+categoryName).value;
    var chkSubCategory=document.getElementById("paraFrm_"+subCategoryName).value;
	var chkLink = document.getElementById("paraFrm_"+name_link).value;
	var chkuploadDocumentName = document.getElementById("paraFrm_"+uploadDocument).value;
	var chkLinkName = document.getElementById("paraFrm_"+link).value;
		
	if(categoryDropdown=="Other" && chkCategory==""){
	alert("Please enter Category Name");
			return false;
	}	
	
	if(subCategoryDropdown=="Other" && chkSubCategory==""){
	alert("Please enter Sub-Category Name");
	document.getElementById("paraFrm_"+categoryName).focus();
			return false;
	}
		
	if(chkCategory=="" && categoryDropdown ==""){
	alert("Please select Category Name");
		return false;
	}
	
	if(chkSubCategory=="" && subCategoryDropdown == ""){
	alert("Please select Sub-Category Name");
	 	return false;
	}
	
	if(chkLink=="" ) {
		alert("Please enter Link/Document Name");
		document.getElementById("paraFrm_"+name_link).focus();
		return false;
	}
	
	if (checkFlagVar == 'false' || checkFlagVar == '') {	
		var chkStart = chkLinkName.substring(0,4);
		if(chkStart.length!=0) {
			if(!(chkStart=="http" || chkStart=="HTTP")) {
				alert("Link Name should start with http ");
				return false;
			}
		}
	}	
	var linkVar=document.getElementById('paraFrm_link').value
	var flagVar=document.getElementById('paraFrm_flag').value;
	
	if(linkVar=="" && flagVar=="linkFile"){
	alert("Please enter Link Path/URL");
			document.getElementById("paraFrm_"+link).focus();
			return false;
	}
	var uploadDocument=document.getElementById('paraFrm_'+uploadDocument).value
	if(uploadDocument=="" && flagVar=="doc"){
	alert("Please select file to upload");
			//document.getElementById("paraFrm_"+uploadDocument).focus();
			return false;
	}
	
	return true;
		
	} catch(e) {	
		alert("Validation Error>>>>>"+e);
		return false;
	}
}

 callOnLoad();
 
function callOnLoad(){
   document.getElementById('paraFrm_flag').value ='linkFile';
   var flag = document.getElementById("paraFrm_checkFlag").value;
    if(flag=="false" || flag=="") {
    	document.getElementById('uploadDocumentDiv').style.display = 'none';
		document.getElementById('linkDiv').style.display = '';
	} else {
		document.getElementById('uploadDocumentDiv').style.display = '';
		document.getElementById('linkDiv').style.display = 'none';
	}
}

function callForEdit(editCode) {	
		document.getElementById('paraFrm').action='MyDownloads_edit.action?editCode='+editCode;
		document.getElementById('paraFrm').submit();
	}
	
function callForDelete(delCode,action,hiddenCodeTxt){
		if(confirm('<%=label.get("confirm.delete")%>'))
		{	
			document.getElementById("paraFrm_"+hiddenCodeTxt).value = delCode;
			document.getElementById('paraFrm').action = action;
			document.getElementById('paraFrm').submit();
		}
	}
	
function callF9Function(divName1,divCode1){   
   callsF9(500,325,'MyDownloads_f9applDiv.action?divName='+divName1+'&divCode='+divCode1);
 }
</script>